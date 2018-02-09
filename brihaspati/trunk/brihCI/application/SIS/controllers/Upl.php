    <?php

/* 
 * @name Upl.php
 * @author Nagendra Kumar Singh(nksinghiitk@gmail.com)
 * @author Om Prakash (omprakashkgp@gmail.com)  upload csv file for staff profile registration in SIS
 * @author Manorama Pal(palseema30@gmail.com) upload csv file for staff tranfer orders and service particulars.
 * csv file for staff employee list,department list csv and upload zip folder for staff photo.
 */
 
defined('BASEPATH') OR exit('No direct script access allowed');


class Upl extends CI_Controller
{
    function __construct() {
        parent::__construct();
		$this->load->model('Common_model',"commodel");
		$this->load->model('Login_model',"lgnmodel"); 
		$this->load->model("Mailsend_model","mailmodel");
	        $this->load->model('SIS_model',"sismodel");
                
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
		redirect('welcome');
        }
    }

    public function index() {
        
        $this->uploadlogo();
        
    }
  
    // This function is used for upload logo
    public function uploadlogo(){
        // for clearing the previous sucess/error flashdata
        $array_items = array('success' => '', 'error' => '', 'warning' =>'');
        $this->session->set_flashdata($array_items);
        if(isset($_POST['uploadlogo'])) {
            $config = array(
		'upload_path' => "./uploads/logo/",
		'allowed_types' => "gif|jpg|png|jpeg|pdf",
		'overwrite' => TRUE,
		'max_size' => "2048000", // Can be set to particular file size , here it is 2 MB(2048 Kb)
		'max_height' => "768",
		'max_width' => "1024"
            );
            $this->load->library('upload', $config);

            if($this->upload->do_upload())
            {
		$data = array('upload_data' => $this->upload->data());
		$this->logger->write_logmessage("update","logo updated", "logo updated sucessfully");
		$this->logger->write_dblogmessage("update","logo updated", "logo updated sucessfully");
		$this->session->set_flashdata('success', 'Logo Successfully Updated.');
		redirect('upl/uploadlogo');
            }
            else
            {
		$error =  array('error' => $this->upload->display_errors());
		foreach ($error as $item => $value):
			$ferror = $item .":". $value;
		endforeach;
		$this->logger->write_logmessage("update","logo update error", $ferror);
		$this->logger->write_dblogmessage("update","logo update error", $ferror);
		$this->session->set_flashdata('error', $ferror);
		redirect('upl/uploadlogo');
            }
	}//close ifpost
	$this->load->view('upl/uploadlogo');	
    }

//This function has been created for upload staff list in the system using csv file format
    public function uploadslist(){
        // for clearing the previous success/error flashdata
        $array_items = array('success' => '', 'error' => '', 'warning' =>'');
        $this->session->set_flashdata($array_items);
       // $error =array();
        if(isset($_POST['uploadslist'])) {
            $ferror='';
            if ( isset($_FILES["userfile"]))
            {
                $errors= array();                
                $file_name = $_FILES['userfile']['name'];
                $file_ext=strtolower(end((explode('.',$file_name))));

                $expensions= array("txt","csv");

                if(in_array($file_ext,$expensions)=== false){
                    $ferror="extension not allowed, please choose a txt or csv file.";
                    $this->session->set_flashdata('error', $ferror);
                    $this->load->view('upl/uploadstafflist');
                    return;
                }
                else{ 
                    $flag=true;
                    $datal = array();
                    $uploadedfile = $_FILES['userfile']['tmp_name'];
                    $h = fopen($uploadedfile,"r");
                    fgetcsv($h); 		
                    $i=1;
                    while (false !== ($line = fgets($h)))
                    {
                        $datal = explode(",", $line);
                        $flag=false;
                        //print_r($datal);
                        // if (count($datal) >= 13){
                        if (count($datal) == 18){
                            $pfno= trim($datal[0]);
                            $empname = trim($datal[1]);
                            $campus = trim($datal[2]);
                            $uoc = trim($datal[3]);
                       
                            $dept = trim($datal[4]);
                            $scheme = trim($datal[5]);
                            $ddocode=trim($datal[6]);
                            $work_type=trim($datal[7]);
                            $group=trim($datal[8]);
                            $desig = trim($datal[9]);
                            $sa_post=trim($datal[10]);
                            $emp_type=trim($datal[11]);
                            $pbcode = trim($datal[12]);
                            $dob = trim($datal[13]);
                            $doa = trim($datal[14]);
			    $email = trim($datal[15]);
			    if(empty($email)){
				    $email = $pfno .'@tanuvas.org.in';
			    }
                            $email1 = trim($email, " ");
                            $bankacc = trim($datal[16]);
                            $aadhar = trim($datal[17]);
                            $mobile ='';
                            $role = 4;
                        
                           // print_r('uocname=='.$uoc.'-----deptcode==='.$dept);
                            //die;
                           
                            if((!empty($campus)) && (!empty($uoc)) && (!empty($dept)) && (!empty($sa_post))){ //ifcondition for count empty checks
                                $campid='';$ucoid='';$deptid='';$sapostid='';
                                $gcampid=$this->commodel->get_listspfic1('study_center', 'sc_id', 'sc_code', $campus);
                                if(!empty($gcampid)){        
                                    $campid=$gcampid->sc_id;
                                }        
                                $gucoid=$this->lgnmodel->get_listspfic1('authorities', 'id', 'code', $uoc);
                                if(!empty($gucoid)){
                                  $ucoid=$gucoid->id;
                                }
                                $gdeptid=$this->commodel->get_listspfic1('Department', 'dept_id', 'dept_code', $dept);
                                if(!empty($gdeptid)){
                                  $deptid=$gdeptid->dept_id;
                                }
                                
                                $gsapostid=$this->commodel->get_listspfic1('designation', 'desig_id', 'desig_code',$sa_post);
                                if(!empty($gsapostid)){
                                    $sapostid=$gsapostid->desig_id;
                                }        
                              
                                if((!empty($campid)) && (!empty($ucoid)) && (!empty($deptid)) && (!empty($sapostid))){
                        
                                    /**************************check for vacancy available in position table************************************************************/
                        
                                    $vacancy=$this->checkvacancy($campid,$ucoid,$deptid,$sapostid,$work_type);
                        
                                    /**************************************************************************************/
                                    if($vacancy == 1){
                                        // check for duplicate
                                        $isdup= $this->lgnmodel->isduplicate('edrpuser','username',$email );
					if(!$isdup){
						if ((strpos($email, 'temp') === 0)||(strpos($email, $pfno) === 0)) {
   							$passwd = $pfno;
						}else{
                                            		//generate 10 digit random password
							$passwd=$this->commodel->randNum(10);
						}
                                            // generate the hash of password
                                            $password=md5($passwd);
                                            // insert data into edrp user db1
                                            $dataeu = array(
                                                'username'=> $email,
                                                'password'=> $password,
                                                'email'=> $email,
                                                'componentreg'=> '*',
                                                'mobile'=>$mobile,
                                                'status'=>1,
                                                'category_type'=>'Employee',
                                                'is_verified'=>1
                                            );
                                            /*insert record in edrpuser table*/
                                            $userflageu=$this->lgnmodel->insertrec('edrpuser', $dataeu);
                                            //get the insert id of edrp user
                                            $insertid= $this->lgnmodel->get_listspfic1('edrpuser','id','username',$email );
                                            $userid=$insertid->id;
                                            if($userflageu){
                                                // insert into  user profile db1
                                                $dataup = array(
                                                    'userid'=>$userid,
                                                    'firstname'=>$empname,
                                                    'lang'=> 'english',
                                                    'mobile'=>$mobile,
                                                    'status'=>1
                                                );
                                                $userflagup=$this->lgnmodel->insertrec('userprofile', $dataup);

                                                $emdupl = $this->sismodel->isduplicate('employee_master','emp_email', $email );
                                                if(!$emdupl){
                                   
                                                    $schemeid=$this->sismodel->get_listspfic1('scheme_department', 'sd_id', 'sd_code',$scheme)->sd_id;
                                                    $desigtid=$this->commodel->get_listspfic1('designation', 'desig_id', 'desig_code', $desig)->desig_id;
                                                    $sapostname=$this->commodel->get_listspfic1('designation', 'desig_name', 'desig_code', $sa_post)->desig_name;
                                                    $datawh=array('ddo_scid' => $campid,'ddo_deptid' => $deptid,'ddo_schid' => $schemeid,'ddo_code' => $ddocode);
                                                    $ddoid= $this->sismodel->get_listspficemore('ddo','ddo_id',$datawh);
                                                    if(!empty($ddoid)){
                                                        $ddofinal=$ddoid[0]->ddo_id;
                                                    }
                                                    if((!empty($schemeid)) && (!empty($desigtid) && ($sapostname)) && (!empty($ddoid))){
                                       
                                                        $dataem = array(
                                                            'emp_code'             => $pfno,
                                                            'emp_name'             => $empname,
                                                            'emp_specialisationid' => '',
                                        
                                                            'emp_dept_code'       => $deptid,
                                                            'emp_schemeid'        => $schemeid,
                                                            'emp_desig_code'      => $desigtid,
                                                            'emp_post'            => $sapostname,
                                        
                                                            'emp_gender'          =>'',
                                                            'emp_community'       =>'',
                                                            'emp_religion'        =>'',
                                                            'emp_caste'           =>'',
                                        
                                                            'emp_type_code'       => $emp_type,
                                                            'emp_salary_grade'    => $pbcode,
                                                            'emp_basic'           =>'',
                                                            'emp_emolution'       =>'',
                                                            'emp_nhisidno'        =>'',
                                                            'emp_doj'             => $doa,
                                                            'emp_pnp'             =>'',
                                                            'emp_phd_status'      =>'',
                                        
                                                            'emp_dateofphd'       =>'',
                                                            'emp_AssrExam_status' =>'',
                                                            'emp_dateofAssrExam'  =>'',
                                                            'emp_dor'             =>'',
                                                            'emp_dateofHGP'       =>'',
                                                            'emp_pan_no'          =>'',
                                       
                                                            'emp_aadhaar_no'      => $aadhar,
                                                            'emp_bank_ifsc_code'  =>',',
                                                            'emp_bank_accno'      => $bankacc,
                                                            'emp_dob'             => $dob,
                                                            'emp_father'          =>'',
                                                            'emp_email'           => $email,
                                        
                                                            'emp_address'         =>'',
                                                            'emp_mothertongue'    =>'',
                                                            'emp_citizen'         =>'',
                                                            'emp_phone'           =>'', 
                                        
                                                            'emp_worktype'        => $work_type,
                                                            'emp_scid'            => $campid,
                                                            'emp_uocid'           => $ucoid,
                                                            'emp_uocuserid'       => $ucoid,
                                                            'emp_ddouserid'       => $ddofinal,
                                                            'emp_ddoid'           => $ddofinal,
                                                            'emp_group'           => $group,
                                                            'emp_apporderno'      => '',
                                                            'emp_phstatus'        => '',
                                                            'emp_phdetail'        => '',
                                                            'emp_bloodgroup'      => '', 
                                                            'emp_doprobation'     => '', 
                                                            'emp_doregular'       => '', 
                                                            'emp_qual'            => '', 
                                                            'emp_remarks'         => '', 
                                                            'emp_photoname'       => ''  
                                        
                                                        );
                                                        /* insert record into  employe_emaster */
                                                        $this->sismodel->insertrec('employee_master', $dataem);
                                                        $this->logger->write_logmessage("insert", "data insert in employee_master table.");
                                                        $this->logger->write_dblogmessage("insert", "data insert in employee_master table." );
                                                        $dataems = array(
                                                            'ems_code'         =>$pfno,
                                                            //'ems_working_type' =>''
			
                                                        );
                                                        /* insert record into  employe_emaster_support */
                                                        $this->sismodel->insertrec('employee_master_support', $dataems);
                                                        $this->logger->write_logmessage("insert", "data insert in employee_master_support table.");
                                                        $this->logger->write_dblogmessage("insert", "data insert in employee_master_support table." );
                                            
                                 //                 $i++;
                                                        if($userflagup){
                                                        /* insert into user_role_type */
                                                            $dataurt = array(
                                                                'userid'=> $userid,
                                                                'roleid'=> $role,
                                                                'deptid'=> $deptid,
                                                                'scid'=>  $campid,
                                                                'usertype'=>'Employee'
                                                            );
                                                            $userflagurt=$this->sismodel->insertrec('user_role_type', $dataurt) ;
                                                            if($userflagurt){
                                                                $sub='Staff Profile Registration' ;
                                                                $mess="You are registered as a staff. Your user id ".$email ." and password is ".$passwd ;
                                                                //$mails = $this->mailmodel->mailsnd($email, $sub, $mess,'','Sis');
                                                                $mails = '';
                                                                //$this->mailmodel->mailsnd('$_POST['emailid']', $sub, $mess,'','Sis');
                                                                //  mail flag check                         
                                                                if($mails){
                                                                    $error[] ="At row ". $i ." sufficient data and mail sent sucessfully";
                                                                    $this->logger->write_logmessage("insert"," add staff edrpuser,profile, employee_master and user role type ", "record added successfully for.".$empname ." ".$email );
                                                                    $this->logger->write_dblogmessage("insert"," add staff edrpuser,profile, employee_master and user role type ", "record added successfully for.".$empname ." ".$email );
                                                                    $i++;
                                                                }
                                                                else{
                                                                    $error[] ="At row ". $i ." sufficient data and mail does sent";
                                                                    $this->logger->write_logmessage("insert"," add staff edrpuser,profile, employee_master and user role type ", "record added successfully for.".$empname ." ".$email ." and mail does sent");
                                                                    $this->logger->write_dblogmessage("insert"," add staff edrpuser,profile, employee_master and user role type ", "record added successfully for.".$empname ." ".$email." and mail does sent" );
                                                                    $i++;
                                                                }
                                                                /*************************************updating the staff position table*****************/
                   
                                                                $this->sismodel->updatestaffposition($campid,$ucoid,$deptid,$desigtid,$work_type,$emp_type) ;
                   
                                                                /*************************************close updating the staff position table*****************/
                                                            }//$userflagurt if
                                                            else{
                                                                //set the message for error in entering data in user role type
                                                                $this->logger->write_logmessage("insert"," Error in adding staff edrpuser,profile and user role type ", " data insert error . ".$empname ." ".$email );
                                                                $this->logger->write_dblogmessage("insert"," Error in adding staff edrpuser,profile and user role type ", " data insert error . ".$empname ." ".$email);
                                                                // delete edrp user data
                                                                $result = $this->lgnmodel->deleterow('edrpuser','id',$userid);
                                                                // delete user profile data
                                                                $result = $this->lgnmodel->deleterow('userprofile','userid',$userid);
                                                                $i++;
                                                            }
                                                        }//$userflagup
                                                        else{
                                                            //set the message for error in entering data in user profile table
                                                            $this->logger->write_logmessage("insert"," Error in adding staff edrpuser,profile ", " data insert error . ".$empname ." ".$email  );
                                                            $this->logger->write_dblogmessage("insert"," Error in adding staff edrpuser,profile ", " data insert error . ".$empname ." ".$email );
                                                            // delete edrp user data
                                                            $result = $this->lgnmodel->deleterow('edrpuser','id',$userid);
                                                            $i++;
                                                        }
                                                    } //second empty check if condition
                                                    else{
                                                        $error[] ="At row " .$i. " some fields are empty. or combination is not correct";
                                                        $this->logger->write_logmessage("insert"," Error in adding staff profile  ", "At row".$i."some fields are empty schemeid=>".$schemeid."desigid==>".$desigtid."shown against post name==>".$sapostname."ddoid==>".$ddocode);
                                                        $this->logger->write_dblogmessage("insert"," Error in adding staff profile ", "At row".$i."some fields are empty schemeid=>".$schemeid."desigid==>".$desigtid."shown against post name==>".$sapostname."ddoid==>".$ddocode);
                                                        // delete edrp user data
                                                        $result = $this->lgnmodel->deleterow('edrpuser','id',$userid);
                                                        // delete user profile data
                                                        $result = $this->lgnmodel->deleterow('userprofile','userid',$userid);
                                                        $i++;
                                                    }//second empty check else condition
                                                                            
                                                }//empdupl
                                                else{
                                                    $i++;
                                                }	   
                                            }//ifuserflageu
                                            else{
                                                // set the message for error in entering data in edrp table
                                                $this->logger->write_logmessage("insert"," Error in adding staff edrpuser ", " data insert error . ".$empname ." ".$email  );
                                                $this->logger->write_dblogmessage("insert"," Error in adding staff edrpuser ", "data insert error . ".$empname ." ".$email );
                                                $i++;
                                            }
                                      
                                        }//close if is dupeu
                                        else{
                                            $error[] ="At row ". $i ." duplicate data";
                                            $this->logger->write_logmessage("insert"," Error in adding staff edrpuser ", "At row".$i."duplicate data"  );
                                            $this->logger->write_dblogmessage("insert"," Error in adding staff edrpuser ", "At row".$i."duplicate data" );
                                            $i++;
                                        }
                                    }//if cond vcancy check
                                    else{
                                        $error[] ="At row " .$i. " No vacancy for this post.";
                                        $this->logger->write_logmessage("insert"," Error in adding staff profile (edrpuser) ", "At row".$i."No vacancy for this post"  );
                                        $this->logger->write_dblogmessage("insert"," Error in adding staff profile (edrpuser) ", "At row".$i."No vacancy for this post");
                                        $i++;
                                    }//else close for vacancy
                                }//emptychecks for 1st if before vacancy check
                                else
                                {
                                    $error[] ="At row " .$i. " some fields are empty. or combination is not correct";
                                    $this->logger->write_logmessage("insert"," Error in adding staff profile  ", "At row".$i."some fields are empty capusid=>".$campid."uoid==>".$ucoid."deptid==>".$deptid."shown against postid==>".$sapostid );
                                    $this->logger->write_dblogmessage("insert"," Error in adding staff profile ", "At row".$i."some fields are empty capusid=>".$campid."uoid==>".$ucoid."deptid==>".$deptid."shown against postid==>".$sapostid);
                                    $i++;
                            
                                }//closerof else emptychecks for  before vacancy check
                            }//ifcondition for count empty check
                            else{
                                $error[] ="At row " .$i. " some fields are empty. or combination is not correct";
                                $this->logger->write_logmessage("insert"," Error in adding staff profile  ", "At row".$i."some fields are empty campus=>".$campus."uoc==>".$uoc."dept==>".$dept."shown against post==>".$sa_post );
                                $this->logger->write_dblogmessage("insert"," Error in adding staff profile ", "At row".$i."some fields are empty campus=>".$campus."uoc==>".$uoc."deptd==>".$dept."shown against post==>".$sa_post);
                                $i++;
                        
                            }//closer else condition for count empty check
                        
                        }//count tokens
                        else{
                            //  insufficient data
                            $error[] ="At row " .$i. " insufficient data";
                            $this->logger->write_logmessage("insert"," Error in adding staff edrpuser ", "At row".$i."insufficient data"  );
                            $this->logger->write_dblogmessage("insert"," Error in adding staff edrpuser ", "At row".$i."insufficient data" );
                            //  $this->session->set_flashdata('error', ' insufficient data');
                            $i++;
                        }                     
                    }//while
                    if($flag){
                        $this->session->set_flashdata('error', ' File without data');
                        $this->load->view('upl/uploadstafflist');
                        return;
                    }
                    else{
                        // print_r($error);
                        foreach ($error as $item => $value):
                        $ferror = $ferror ."</br>". $item .":". $value;
                        endforeach;
                        //display error of array
                        //put ferror in log file.
                        $this->session->set_flashdata('success', $ferror);
                        $this->load->view('upl/uploadstafflist');
                        return;
                    }//else closer
                }//else for csv extension
            }//userfile checks
        }// check for pressing correct button
        $this->load->view('upl/uploadstafflist');
    }//function close
   
   //This function has been created for generating multiple transfer order using csv file format
    public function uploadtransferorder(){
        $array_items = array('success' => '', 'error' => '', 'warning' =>'');
       	$this->session->set_flashdata($array_items);
	$error =array();
        if(isset($_POST['importdata']))
        {
            $ferror='';
            $filename=$_FILES['userfile']['tmp_name'];
            $filesize=$_FILES['userfile']['size'];
         //   if($_FILES['userfile']['size'] > 0)
           // {
                $flag=true;
                $file = fopen($filename, "r");
                fgetcsv($file);
                $i=1;
                //while (($getData = fgetcsv($file, 10000, ",")) !== FALSE)
                while (false !== ($line = fgets($file)))
                {
                    $getData = explode(",", $line);
                    $flag=false;
                   // $colcount = count($getData);
                    //if($colcount >= 21){
                    if(count($getData) >= 21){
                        $empid=$this->sismodel->get_listspfic1('employee_master', 'emp_id', 'emp_name', $getData[0])->emp_id;
                        $datuit = array(
                            //'uit_staffname'         => $getData[0], //get id
                            'uit_staffname'         => $empid, 
                            'uit_registrarname'     => trim($getData[1]),
                            'uit_desig'             => trim($getData[2]),
                            'uit_uso_no'            => trim($getData[3]),
                            'uit_date'              => date('y-m-d'), 
                            'uit_rc_no'             => trim($getData[4]),
                            'uit_subject'           => trim($getData[5]), 
                            'uit_referenceno'       => trim($getData[6]),
                            'uit_ordercontent'      => trim($getData[7]),
                            'uit_emptype'           => trim($getData[8]),
                            'uit_uoc_from'          => trim($getData[9]),
                            'uit_uoc_to'            => trim($getData[10]), //getid
                            'uit_workdept_from'     => trim($getData[11]),
                            'uit_dept_to'           => trim($getData[12]), //getid
                            'uit_desig_from'        => trim($getData[13]),
                            'uit_desig_to'          => trim($getData[14]), ///getid
                            'uit_workingpost_from'  => trim($getData[15]),
                            'uit_post_to'           => trim($getData[16]),
                            'uit_tta_detail'        => trim($getData[17]),
                            'uit_dateofrelief'      => trim($getData[18]),
                            'uit_dateofjoining'     => trim($getData[19]),
                            'uit_email_sentto '     => trim($getData[20]),
                            
                        );
                        $usrinputtfr_flag=$this->sismodel->insertrec('user_input_transfer', $datuit);
                        if($usrinputtfr_flag){
                            $emppfno=$this->sismodel->get_listspfic1('employee_master', 'emp_code', 'emp_id', $empid)->emp_code;
                            $empname=$getData[0];
                            $deptto=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$getData[12])->dept_name; 
                            $this->orgname=$this->commodel->get_listspfic1('org_profile','org_name','org_id',1)->org_name;
                            //$this->regname=$this->input->post('registrarname');
                            //$this->uitdesig=$this->input->post('designation');
                            //$mail_sent_to=$_POST['emailsentto'];
                            $sub='Employee Transfer And Posting - Letter  ' ;
                            $mess='OFFICE ORDER<br/> Dear'.$empname.'This is to inform you that you will be transferred at'.$deptto.'with immediate effect.<br/>
                            Please find the attachment of transfer order copy<br/> Wish you all the best<br/>'.$this->orgname.'<br/>
                            '.$getData[1].'<br/>'.$getData[2];
                           // $this->load->library('../controllers/staffmgmt');
                            $attachment=$this->sismodel->gentransferordertpdf($empid);
                            // $this->mailstoperson =$this->mailmodel->mailsnd('$getData[20]', $sub, $mess,$attachment,'All');
                            $this->mailstoperson='';
                            if($this->mailstoperson){
                                //echo "in if part mail";
                                $error[] ="At row".$i."sufficient data and mail sent sucessfully";
                                $mailmsg='Transfer and Promotion order ....Mail send successfully';
                                $this->logger->write_logmessage("insert"," Transfer and Promotion order ",'mail send successfully  to '.$getData[20] );
                                $this->logger->write_dblogmessage("insert"," Transfer and Promotion order",'mail send successfully  to '.$getData[20]);
                            }
                            else{
                                //echo "in else part";
                                $mailmsg='Mail does not sent';
                                $this->logger->write_logmessage("insert"," Transfer and Promotion order", "Mail does not sent to ".$getData[20]);
                                $this->logger->write_dblogmessage("insert"," Transfer and Promotion order", "Mail does not sent to ".$getData[20]);
                            }//else close   
                            
                            $this->logger->write_logmessage("insert","Staff Transfer and Posting", " Employee transfer record insert successfully ");
                            $this->logger->write_dblogmessage("insert","Staff Transfer and Posting", "Employee transfer record insert successfully");
                            $this->session->set_flashdata('success', 'Employee transfer record insert successfully ......');
                        }//ifclose $usrinputtfr_flag
                        else{
                            $this->logger->write_logmessage("error","Error in Staff Transfer and Posting", "Error in Staff Transfer and Posting ".$empname );
                            $this->logger->write_dblogmessage("error","Error in Staff Transfer and Posting", "Error in Staff Transfer and Posting".$empname);
                        }
                       $i++; 
                    }//if count
                    else{
                        //	insufficient data
                            $error[] ="At row".$i."insufficient data";
                            $this->logger->write_logmessage("insert"," Error in adding user input transfer in payroll ", "At row".$i."insufficient data"  );
                            $this->logger->write_dblogmessage("insert"," Error in adding user input transfer in payroll ", "At row".$i."insufficient data" );
                           // $this->session->set_flashdata('error', "At row".$i."insufficient data");
			    $i++;
                    }
                                      
                }//while close
                if($flag){
                    $this->session->set_flashdata('error', ' File without data');
                    $this->load->view('upl/uploadtransferorder');
                    return;
                }else{
                    foreach ($error as $item => $value):
                        $ferror = $ferror ."</br>". $item .":". $value;
                    endforeach;
                    //display error of array
                    //put ferror in log file.
                    $this->session->set_flashdata('error', $ferror);
                    redirect('staffmgmt/stafftransferlist');
                  
                }
                
		fclose($file);	
	    //}//if
            /*else{
                foreach ($error as $item => $value):
        	    $ferror = $ferror ."</br>".$item .":". $value;
                endforeach;
                $this->session->set_flashdata('error', $ferror);
                $this->load->view('upl/uploadtransferorder');
                return;
            }*/
            
        }//$post close    
        $this->load->view('upl/uploadtransferorder');
        
    }//close function
    
    //This function has been created for export transfer orders  using csv file format
    public function exporttransferorder(){
        if(isset($_POST["exportdata"])){
		 
            header('Content-Type: text/csv; charset=utf-8');  
            header('Content-Disposition: attachment; filename=data.csv');  
            $output = fopen("php://output", "w");  
            fputcsv($output, array('uit_id','uit_registrarname', 'uit_desig', 'uit_uso_no','uit_date','uit_rc_no', 'uit_subject','uit_referenceno',
                'uit_ordercontent','uit_emptype','uit_uoc_from','uit_workdept_from','uit_desig_from','uit_staffname','uit_workingpost_from',
                'uit_uoc_to','uit_dept_to','uit_desig_to','uit_post_to','uit_tta_detail','uit_dateofrelief','uit_dateofjoining','uit_email_sentto')); 
           // this need to change  in  according to sis model
            $link = mysqli_connect("localhost", "root", "123456", "payroll");
            $query = "SELECT * from user_input_transfer ORDER BY uit_id DESC";  
            $result = mysqli_query($link, $query);  
           // echo "this is testing ".$result;
            //die;
            while($row = mysqli_fetch_assoc($result))  
            {  
                fputcsv($output, $row);  
            }  
           // $link->close();
            fclose($output);  
        }//if close  
    }//close function
    
    /*******************************This function has been created for upload staff service particulars ******************************/
    //public function servicedata($id=0){
    public function servicedata(){
        //echo "getting id=====".$id;
        //$emsdata['dataid']=$id;  
        $array_items = array('success' => '', 'error' => '', 'warning' =>'');
       $this->session->set_flashdata($array_items);
	$error =array();
        if(isset($_POST['servicerecord']))
        {
            $ferror='';
            $filename=$_FILES['userfile']['tmp_name'];
            $filesize=$_FILES['userfile']['size'];
            $flag=true;
            $file = fopen($filename, "r");
            fgetcsv($file);
            $i=1;
            while (false !== ($line = fgets($file)))
            {
                $getData = explode(",", $line);
                $flag=false;
               
                if(count($getData) == 7){
                  // if($id==0) {
                       
                    $id=$this->sismodel->get_listspfic1('employee_master', 'emp_id', 'emp_code', $getData[0])->emp_id;
                                           
                   //}
                  
                   $dataempsd = array(
                        'empsd_empid'       => $id,
                        'empsd_campuscode'  => trim($getData[1]),
                        'empsd_desigcode'   => trim($getData[2]),
                        'empsd_pbid'        => trim($getData[3]), 
                        'empsd_pbdate'      => trim($getData[4]),
                        'empsd_dojoin'      => trim($getData[5]), 
                        'empsd_dorelev'     => trim($getData[6]),
                    ); 
                    $empsdinput_flag=$this->sismodel->insertrec('employee_servicedetail', $dataempsd);
                    if($empsdinput_flag){
                        $this->logger->write_logmessage("insert","Employee service particulars", " Employee service records insert successfully ");
                        $this->logger->write_dblogmessage("insert","Employee service particulars", "Employee service records insert successfully");
                        $this->session->set_flashdata('success', 'Employee service records insert successfully ...........');
                    }//ifclose $empsdinput_flag
                    else{
                        $empname=$this->sismodel->get_listspfic1('employee_master', 'emp_name', 'emp_id', $id)->emp_name;
                        $this->logger->write_logmessage("error","Error in employee service particulars", "Error in employee service particulars ".$empname );
                        $this->logger->write_dblogmessage("error","Error in employee service particulars", "Error in employee service particulars ".$empname);
                        
                    }//else$empsdinput_flag
                    $i++;
                }//ifcount
                else{
                            echo "insufficient data";
                            $error[] ="At row".$i."insufficient data";
                            $this->logger->write_logmessage("insert"," Error in employee service particulars ", "At row".$i."insufficient data"  );
                            $this->logger->write_dblogmessage("insert"," Error in employee service particulars ", "At row".$i."insufficient data" );
                            $this->session->set_flashdata('error',"At row".$i."insufficient data");
                            $i++;
                }
            } //while
            if($flag){
                    $this->session->set_flashdata('error', ' File without data');
                    $this->load->view('upl/servicedata');
                    //redirect('upl/servicedata');
                    return;
            }else{
                foreach ($error as $item => $value):
                    $ferror = $ferror ."</br>". $item .":". $value;
                endforeach;
                //display error of array
                //put ferror in log file.
                $this->session->set_flashdata('error', $ferror);
                redirect('upl/servicedata');
                  
            }
                
            fclose($file);
            
        }//issetpost
        //$this->load->view('upl/servicedata',$emsdata);
        $this->load->view('upl/servicedata');
    }
    /******************************* closer upload staff service particulars ******************************/
    /******************************* vacancy available check from staff position *************************************/
    public function checkvacancy($campusid,$uoid,$deptid,$desigid,$worktype){
        $datawh=array('sp_campusid' => $campusid,'sp_uo' => $uoid, 'sp_dept' => $deptid,
                        'sp_emppost' => $desigid, 'sp_tnt' => $worktype);
        
        $emptype_data = $this->sismodel->get_listspficemore('staff_position', 'sp_vacant',$datawh);
        $vcyflag=false;
        if(!empty($emptype_data)){ 
            foreach($emptype_data as $empdata){
                if($empdata->sp_vacant > 0){ 
                   $vcyflag=true;
                   return $vcyflag; 
                }
                else{
                    $vcyflag=false;
                    return $vcyflag;
                }
            }//foreach
        }
        else{
            $vcyflag=false;
            return $vcyflag;
        }
    }
    /******************************* closer vacancy available check from staff position ******************************/
    
    /******************************* upload staff photo ******************************/
    public function uplstaffphoto(){
        if(isset($_POST['staffphoto']))
        {
            if ( isset($_FILES["userfile"]))
            {
                
                $filename = $_FILES["userfile"]["name"];
                $source = $_FILES["userfile"]["tmp_name"];
                $type = $_FILES["userfile"]["type"];
	
                $name = explode(".", $filename);
                $accepted_types = array('application/zip', 'application/x-zip-compressed', 'multipart/x-zip', 'application/x-compressed');
                foreach($accepted_types as $mime_type) {
                    if($mime_type == $type) {
			$okay = true;
			break;
                    } 
                }
	
                $continue = strtolower($name[1]) == 'zip' ? true : false;
                if(!$continue) {
                    $message = "The file you are trying to upload is not a .zip file. Please try again.";
                    $this->session->set_flashdata('error',$message);
                }else{
                $target="./uploads/SIS/empphoto/";
                $target_path = "./uploads/SIS/empphoto/".$filename;  // change this to the correct site path
                if(move_uploaded_file($source, $target_path)) {
                    $zip = new ZipArchive();
                    $x = $zip->open($target_path);
                    if ($x === true) {
			$zip->extractTo($target); // change this to the correct site path
                       	$zip->close();
                       
                        chmod($target_path, 0777);
			unlink($target_path);
                    }                
                    if (is_dir($target)){
                        if ($dh = opendir($target)){
                            while (($file = readdir($dh)) !== false){
                                $fname = basename($file); 
                                $filepart1 = explode('.', $fname);
                                $emrecord=$this->sismodel->get_listspfic2('employee_master','emp_id','emp_code');
                                foreach($emrecord as $empdata){
                                    if($empdata->emp_code == $filepart1[0]){
                                    /****update employee photo*****************************/
                                        $id=$empdata->emp_id;
                                        $data = array(
                                            'emp_photoname' => $fname
                                        ); 
                                        if(empty($empdata->emp_photoname)){
                                            $upempdata_flag=$this->sismodel->updaterec('employee_master',$data,'emp_id',$id);
                                        }
                                        /****update employee photo*****************************/
                                            
                                    }//match
                                        
                                }//foreach
                                //echo "filename:" . $file . "<br>".$fname;
                            }//while
                            closedir($dh);
                        }//if
                    }//ifdircheck
                
                $message = "Your .zip file was uploaded and unpacked.";
                $this->session->set_flashdata('success',$message);
                }//moveclose    
                else {	
                    $message = "There was a problem with the upload. Please try again.";
                    $this->session->set_flashdata('error',$message);
                }
     		}         
            } //userfile   
        }//bottonclick
        $this->load->view('upl/uploadphoto');
    }
    /******************************* closer upload staff photo******************************/

	 public function uploaddeglist(){
		 $array_items = array('success' => '', 'error' => '', 'warning' =>'');
		 $this->session->set_flashdata($array_items);
		 $error =array();
		 if(isset($_POST['uploaddeglist'])) {
			 $ferror='';
//			 echo "I am here"; die;
        	    	if ( isset($_FILES["userfile"]))
            		{
                		$errors= array();
                		$file_name = $_FILES['userfile']['name'];
                		$file_ext=strtolower(end((explode('.',$file_name))));
		                $expensions= array("txt","csv");
		                if(in_array($file_ext,$expensions)=== false){
                    			$ferror="extension not allowed, please choose a txt or csv file.";
			                $this->session->set_flashdata('error', $ferror);
			                $this->load->view('upl/uploaddeglist');
                    			return;
                		}
                		else{
                			$flag=true;
                			$datal = array();
	                		$uploadedfile = $_FILES['userfile']['tmp_name'];
        	        		$h = fopen($uploadedfile,"r");
                			fgetcsv($h);
                			$i=1;//$kk;
                			while (false !== ($line = fgets($h)))
                			{
						$datal = explode(",", $line);
	                    			$flag=false;
						if(count($datal) >= 6){
							$code = trim($datal[0]);
                            				$type = trim($datal[1]);
			                            	$subtype = trim($datal[2]);
                        			    	$name = trim($datal[3]);
			                            	$payscale = trim($datal[4]);
							$group = trim($datal[5]);
				//		$kk=$kk .'='.$code;
							$datadeg = array('desig_code' => $code, 'desig_name' => $name,'desig_payscale'=> $payscale);
							 // check for duplicate
                            				$isdup= $this->commodel->isduplicatemore('designation',$datadeg );
							if(!$isdup){
								$dataurt = array(
			                                           'desig_code'=> $code,
                        			                   'desig_type'=> $type,
			                                           'desig_subtype'=> $subtype,
                        			                   'desig_name'=> $name,
			                                           'desig_payscale'=> $payscale,
                        			                   'desig_group'=> $group
		                	                        );
                		        	                $userflagurt=$this->commodel->insertrec('designation', $dataurt) ;
                                                                $this->session->set_flashdata('success', 'Designation csv file uploaded successfully.');
							}else{
								//duplicate data
								$error[] ="At row".$i."duplicate data";
				                                $this->logger->write_logmessage("insert"," Error in adding designation list ", "At row".$i."duplicate data"  );
                                				$this->logger->write_dblogmessage("insert"," Error in adding designation list ", "At row".$i."duplicate data" );
							}
							$i++;
						}else{
								//insufficient data
								$error[] ="At row".$i."insufficient data";
					                        $this->logger->write_logmessage("insert"," Error in adding designation list ", "At row".$i."insufficient data"  );
								$this->logger->write_dblogmessage("insert"," Error in adding designation list ", "At row".$i."insufficient data" );
								$i++;
						}
					}//end of while
					//echo $kk; die;
					fclose($h);	
					if($flag){
			                    	$this->session->set_flashdata('error', ' File without data');
						$this->load->view('upl/uploaddeglist');
                    				return;
                			}else{
                    				//display error of array
                    				foreach ($error as $item => $value):
                    					$ferror = $ferror ."</br>". $item .":". $value;
                    				endforeach;
                    				$this->session->set_flashdata('success', $ferror);
						$this->load->view('upl/uploaddeglist');
                    				return;
                			}
				}
			}//end of user file
		 }//button pressed
		 $this->load->view('upl/uploaddeglist');
	  }

	 public function uploadschemelist(){
		 $array_items = array('success' => '', 'error' => '', 'warning' =>'');
		 $this->session->set_flashdata($array_items);
		 $error =array();
		 if(isset($_POST['uploadschemelist'])) {
			 $ferror='';
//			 echo "I am here"; die;
        	    	if ( isset($_FILES["userfile"]))
            		{
                		$errors= array();
                		$file_name = $_FILES['userfile']['name'];
                		$file_ext=strtolower(end((explode('.',$file_name))));
		                $expensions= array("txt","csv");
		                if(in_array($file_ext,$expensions)=== false){
                    			$ferror="extension not allowed, please choose a txt or csv file.";
			                $this->session->set_flashdata('error', $ferror);
			                $this->load->view('upl/uploadschemelist');
                    			return;
                		}
                		else{
                			$flag=true;
                			$datal = array();
	                		$uploadedfile = $_FILES['userfile']['tmp_name'];
        	        		$h = fopen($uploadedfile,"r");
                			fgetcsv($h);
                			$i=1;//$kk;
                			while (false !== ($line = fgets($h)))
                			{
						$datal = explode(",", $line);
	                    			$flag=false;
						if(count($datal) >= 4){
							$deptcode = trim($datal[0]);
							$deptid=$this->commodel->get_listspfic1('Department', 'dept_id', 'dept_code', $deptcode)->dept_id;;
                            				$shcode = trim($datal[1]);
			                            	$shname = trim($datal[2]);
                        			    	$shshort = trim($datal[3]);
				//		$kk=$kk .'='.$code;
							$datadeg = array('sd_deptid' => $deptid, 'sd_code' => $shcode,'sd_name'=> $shname);
							 // check for duplicate
                            				$isdup= $this->sismodel->isduplicatemore('scheme_department',$datadeg );
							if(!$isdup){
								$dataurt = array(
			                                           'sd_deptid'=> $deptid,
                        			                   'sd_code'=> $shcode,
			                                           'sd_short'=> $shshort,
                        			                   'sd_name'=> $shname
		                	                        );
                		        	                $userflagurt=$this->sismodel->insertrec('scheme_department', $dataurt) ;
                                                                $this->session->set_flashdata('success', 'Department scheme csv file uploaded successfully.');
							}else{
								//duplicate data
								$error[] ="At row".$i."duplicate data";
				                                $this->logger->write_logmessage("insert"," Error in adding scheme list ", "At row".$i."duplicate data"  );
                                				$this->logger->write_dblogmessage("insert"," Error in adding scheme list ", "At row".$i."duplicate data" );
							}
							$i++;
						}else{
								//insufficient data
								$error[] ="At row".$i."insufficient data";
					                        $this->logger->write_logmessage("insert"," Error in adding scheme list ", "At row".$i."insufficient data"  );
								$this->logger->write_dblogmessage("insert"," Error in adding scheme list ", "At row".$i."insufficient data" );
								$i++;
						}
					}//end of while
					//echo $kk; die;
					fclose($h);	
					if($flag){
			                    	$this->session->set_flashdata('error', ' File without data');
						$this->load->view('upl/uploadschemelist');
                    				return;
                			}else{
                    				//display error of array
                    				foreach ($error as $item => $value):
                    					$ferror = $ferror ."</br>". $item .":". $value;
                    				endforeach;
                    				$this->session->set_flashdata('success', $ferror);
						$this->load->view('upl/uploadschemelist');
                    				return;
                			}
				}
			}//end of user file
		 }//button pressed
		 $this->load->view('upl/uploadschemelist');
	}
	 public function uploadddolist(){
		 $array_items = array('success' => '', 'error' => '', 'warning' =>'');
		 $this->session->set_flashdata($array_items);
		 $error =array();
		 if(isset($_POST['uploadddolist'])) {
			 $ferror='';
//			 echo "I am here"; die;
        	    	if ( isset($_FILES["userfile"]))
            		{
                		$errors= array();
                		$file_name = $_FILES['userfile']['name'];
                		$file_ext=strtolower(end((explode('.',$file_name))));
		                $expensions= array("txt","csv");
		                if(in_array($file_ext,$expensions)=== false){
                    			$ferror="extension not allowed, please choose a txt or csv file.";
			                $this->session->set_flashdata('error', $ferror);
			                $this->load->view('upl/uploadddolist');
                    			return;
                		}
                		else{
                			$flag=true;
                			$datal = array();
	                		$uploadedfile = $_FILES['userfile']['tmp_name'];
        	        		$h = fopen($uploadedfile,"r");
                			fgetcsv($h);
                			$i=1;//$kk;
                			while (false !== ($line = fgets($h)))
                			{
						$datal = explode(",", $line);
	                    			$flag=false;
						if(count($datal) >= 5){
                            				$sccode = trim($datal[0]);
							$scid = $this->commodel->get_listspfic1('study_center', 'sc_id', 'sc_code', $sccode)->sc_id;;
							$deptcode = trim($datal[1]);
							$deptid=$this->commodel->get_listspfic1('Department', 'dept_id', 'dept_code', $deptcode)->dept_id;;
			                            	$shcode = trim($datal[2]);
							$shid=$this->sismodel->get_listspfic1('scheme_department', 'sd_id', 'sd_code',$shcode)->sd_id;
			                            	$ddocode = trim($datal[3]);
                        			    	$ddoname = trim($datal[4]);
				//		$kk=$kk .'='.$code;
							$datadeg = array('ddo_scid' => $scid, 'ddo_deptid' => $deptid,'ddo_schid'=> $shid,'ddo_code'=>$ddocode);
							 // check for duplicate
                            				$isdup= $this->sismodel->isduplicatemore('ddo',$datadeg );
							if(!$isdup){
								$dataurt = array(
                        			                   'ddo_scid'=> $scid,
			                                           'ddo_deptid'=> $deptid,
			                                           'ddo_schid'=> $shid,
                        			                   'ddo_code'=> $ddocode,
                        			                   'ddo_name'=> $ddoname
		                	                        );
                		        	                $userflagurt=$this->sismodel->insertrec('ddo', $dataurt) ;
                                                                $this->session->set_flashdata('success', 'DDO csv file uploaded successfully.');
							}else{
								//duplicate data
								$error[] ="At row".$i."duplicate data";
				                                $this->logger->write_logmessage("insert"," Error in adding ddo list ", "At row".$i."duplicate data"  );
                                				$this->logger->write_dblogmessage("insert"," Error in adding ddo list ", "At row".$i."duplicate data" );
							}
							$i++;
						}else{
								//insufficient data
								$error[] ="At row".$i."insufficient data";
					                        $this->logger->write_logmessage("insert"," Error in adding ddo list ", "At row".$i."insufficient data"  );
								$this->logger->write_dblogmessage("insert"," Error in adding ddo list ", "At row".$i."insufficient data" );
								$i++;
						}
					}//end of while
					//echo $kk; die;
					fclose($h);	
					if($flag){
			                    	$this->session->set_flashdata('error', ' File without data');
						$this->load->view('upl/uploadddolist');
                    				return;
                			}else{
                    				//display error of array
                    				foreach ($error as $item => $value):
                    					$ferror = $ferror ."</br>". $item .":". $value;
                    				endforeach;
                    				$this->session->set_flashdata('success', $ferror);
						$this->load->view('upl/uploadddolist');
                    				return;
                			}
				}
			}//end of user file
		 }//button pressed
		 $this->load->view('upl/uploadddolist');
	}
        
/******************************* upload staff position ******************************/
    public function uploadspositionlist(){
        // for clearing the previous success/error flashdata
        $array_items = array('success' => '', 'error' => '', 'warning' =>'');
        $this->session->set_flashdata($array_items);
        $error =array();
        if(isset($_POST['uploadsposition'])) {
            $ferror='';
            if ( isset($_FILES["userfile"]))
            {
                $errors= array();                
                $file_name = $_FILES['userfile']['name'];
                $file_ext=strtolower(end((explode('.',$file_name))));

                $expensions= array("txt","csv");

                if(in_array($file_ext,$expensions)=== false){
                    $ferror="extension not allowed, please choose a txt or csv file.";
                    $this->session->set_flashdata('error', $ferror);
                    $this->load->view('upl/uploadspositionlist');
                    return;
                }
                else{ 
                $flag=true;
                $dataspl = array();
                $uploadedfile = $_FILES['userfile']['tmp_name'];
                $h = fopen($uploadedfile,"r");
	        fgetcsv($h); 		
                $i=1;
                while (false !== ($line = fgets($h)))
                {
                    $dataspl = explode(",", $line);
                    $flag=false;
                    //print_r(count($dataspl));
                    if (count($dataspl) >=14 ){
                        $campus = trim($dataspl[0]);
                        $uoc = trim($dataspl[1]);
                        $dept = trim($dataspl[2]);
                        $scheme = trim($dataspl[3]);
                        $group=trim($dataspl[4]);
                        $emppost=trim($dataspl[5]);
                        $wtype=trim($dataspl[6]);
                        $gpost = trim($dataspl[7]);
                        $emptype=trim($dataspl[8]);
                        $pnplan=trim($dataspl[9]);
                        $pband = trim($dataspl[10]);
                        $mor = trim($dataspl[11]);
                        $pss = trim($dataspl[12]);
                        $p = trim($dataspl[13]);

			$emptypeflag=false;
	
			if($emptype== "Permanent"){
				$v = ($pss - $p);
				$ssp = $pss;
				$sst= 0;
				$pp = $p;
				$pt = 0;
				$vt = 0;
				$vp = ($pss-$p);	
			 $emptypeflag=true;
			}
			else if($emptype== "Temporary"){
				$v = ($pss - $p);
                        	$ssp = 0;
                        	$sst= $pss;
                        	$pp = 0;
                        	$pt = $p;
                       	 	$vt = ($pss-$p);
                        	$vp = 0;
				 $emptypeflag=true;
                        }
			else{
				$error[] ="At row " .$i. " employee type data missing ";
                        	$this->logger->write_logmessage("insert"," Error in adding staff Position ", "At row".$i." employee Type data missing "  );
                        	$this->logger->write_dblogmessage("insert"," Error in adding staff Position ", "At row".$i." employee Type data missing " );
                        	$i++;
			}
                        if($emptypeflag){
                        $campid=$this->commodel->get_listspfic1('study_center', 'sc_id', 'sc_code', $campus)->sc_id;
                        $uoid=$this->lgnmodel->get_listspfic1('authorities', 'id', 'code', $uoc)->id;
                        $deptid=$this->commodel->get_listspfic1('Department', 'dept_id', 'dept_code', $dept)->dept_id;
                        $postid=$this->commodel->get_listspfic1('designation', 'desig_id', 'desig_code',$emppost)->desig_id;
			$schid=$this->sismodel->get_listspfic1('scheme_department', 'sd_id', 'sd_code', $scheme)->sd_id;

                        $datadupposition = array('sp_tnt'=>$wtype , 'sp_type'=>$emptype , 'sp_emppost'=>$postid, 'sp_grppost'=>$gpost, 'sp_scale'=>$pband, 'sp_methodRect'=>$mor, 'sp_group'=>$group, 'sp_uo'=>$uoid, 'sp_dept'=>$deptid, 'sp_campusid'=>$campid, 'sp_plan_nonplan'=>$pnplan, 'sp_schemecode'=>$schid, 'sp_org_id'=> '1' );
 


                         // check for duplicate
		        $duppositionflag = $this->sismodel->isduplicatemore('staff_position', $datadupposition) ;
		        if(!$duppositionflag){

                         $dataposition = array(
		                'sp_tnt'=>$wtype,
                		'sp_type'=>$emptype,
                		'sp_emppost'=>$postid,
                		'sp_grppost'=>$gpost,
                		'sp_scale'=>$pband,
                		'sp_methodRect'=>$mor,
                		'sp_group'=>$group,
                		'sp_uo'=>$uoid,
                		'sp_dept'=>$deptid,
                		'sp_address1'=>'',
                		'sp_address2'=>'Null',
                		'sp_address3'=>'Null',
                		'sp_campusid'=>$campid,
                		'sp_per_temporary'=>'Null',
                		'sp_plan_nonplan'=>$pnplan,
                		'sp_schemecode'=>$schid,
                		'sp_sancstrenght'=>$pss,
                		'sp_position'=>$p,
                		'sp_vacant'=>$v,
                		'sp_remarks'=>'',
                		'sp_ssdetail'=> '',
                		'sp_sspermanent'=>$ssp,
                		'sp_sstemporary'=>$sst,
                		'sp_pospermanent'=>$pp,
                		'sp_postemporary'=>$pt,
                		'sp_vpermanenet'=>$vp,
                		'sp_vtemporary'=>$vt,
                		'sp_org_id'=> '1'
        		   );
 
                                /*insert record in staff_position table*/
				$positionflag = $this->sismodel->insertrec('staff_position', $dataposition) ;
                                                              
                                if($positionflag){
					$error[] ="At row " .$i. " sufficient data and registration successfully";
                                        $this->logger->write_logmessage("insert", "data insert in staff_position table.");
                                        $this->logger->write_dblogmessage("insert", "data insert in staff_position table." );
                                }//ifuserflageu
                                else{
                                    // set the message for error in entering data in staff_position table
                                    $this->logger->write_logmessage("insert","Error in adding staff_position ", "data insert error . " );
                                    $this->logger->write_dblogmessage("insert"," Error in adding staff_position ", "data insert error . " );
                                }
                                // $this->session->set_flashdata('success', ' sufficient data');        
                            }//close for is duplicate
                            else{
                                $error[] ="At row ". $i ." duplicate data";
                                $this->logger->write_logmessage("insert"," Error in adding staff Position ", "At row".$i."duplicate data"  );
                                $this->logger->write_dblogmessage("insert"," Error in adding staff Position ", "At row".$i."duplicate data" );
                            }
                            $i++;
			}
                    }//count tokens
                    else{
                        //  insufficient data
                        $error[] ="At row " .$i. " insufficient data";
                        $this->logger->write_logmessage("insert"," Error in adding staff Position ", "At row".$i."insufficient data"  );
                        $this->logger->write_dblogmessage("insert"," Error in adding staff Position ", "At row".$i."insufficient data" );
                        $i++;
                    }                     
                }//while
		fclose($h);	
                if($flag){
                    $this->session->set_flashdata('error', ' File without data');
                    $this->load->view('upl/uploadspositionlist');
                    return;
                }else{
                   // print_r($error);
                    foreach ($error as $item => $value):
                    $ferror = $ferror ."</br>". $item .":". $value;
                    endforeach;
                    //display error of array
                    //put ferror in log file.
                    $this->session->set_flashdata('success', $ferror);
                    $this->load->view('upl/uploadspositionlist');
                    return;
                }
		}
            }//userfile checks
        }// check for pressing correct button
        $this->load->view('upl/uploadspositionlist');
    }
 
    /******************************* close upload staff position ******************************/
    /*************************upload department csv file**********************************************/
    public function uploaddepartlist(){
        $array_items = array('success' => '', 'error' => '', 'warning' =>'');
        $this->session->set_flashdata($array_items);
        $error =array();
        if(isset($_POST['uploaddepartlist']))
        {
            
            $ferror='';
            if ( isset($_FILES["userfile"]))
            {
                $errors= array();
                $file_name = $_FILES['userfile']['name'];
                $file_ext=strtolower(end((explode('.',$file_name))));
                $expensions= array("txt","csv");
                if(in_array($file_ext,$expensions)=== false)
                {
                    $ferror="extension not allowed, please choose a txt or csv file.";
                    $this->session->set_flashdata('error', $ferror);
                    $this->load->view('upl/uploaddepartlist');
                    return;
                }
                else{
                    $flag=true;
                    $datal = array();
                    $uploadedfile = $_FILES['userfile']['tmp_name'];
                    $h = fopen($uploadedfile,"r");
                    fgetcsv($h);
                    $i=1;//$kk;
                    while (false !== ($line = fgets($h)))
                    {
                        $datal = explode(",", $line);
                        $flag=false;
                        if(count($datal) >= 6)
                        {
                            $ucode = trim($datal[0]);
                            $ccode = trim($datal[1]);
                            $authcode = trim($datal[2]);
                            $deptcode = trim($datal[3]);
                            $deptname = trim($datal[4]);
                            $deptnn = trim($datal[5]);
                            $authid = $this->lgnmodel->get_listspfic1('authorities','id','code',$authcode)->id;
                            $ddatacheck = array(
                                'dept_uoid'    =>$authid,
                                'dept_orgcode' =>strtoupper($ucode),
                                'dept_sccode'  => strtoupper($ccode),
                                'dept_code'    => strtoupper($deptcode),
                                'dept_name'    => ucwords(strtolower($deptname))
                            );
                            // check for duplicate
                            $isdup= $this->commodel->isduplicatemore('Department',$ddatacheck);
                            if(!$isdup){
                                $dataurt = array(
                                    'dept_name'       => $deptname,
                                    'dept_code'       => $deptcode,
                                    'dept_uoid'       => $authid,
                                    'dept_short'      => $deptnn,
                                    'dept_description'=>'',
                                    'dept_schoolname' =>'',
                                    'dept_schoolcode' =>'',
                                    'dept_sccode'     => $ccode,
                                    'dept_orgcode'    => $ucode
                                );
                                $userflagurt=$this->commodel->insertrec('Department', $dataurt) ;
                    		$this->session->set_flashdata('success', 'Department csv file uploaded successfully.');
                            }
                            else{
                                //duplicate data
                                $error[] ="At row ". $i ." duplicate data";
                                $this->logger->write_logmessage("insert"," Error in adding department list ", "At row".$i."duplicate data"  );
                                $this->logger->write_dblogmessage("insert"," Error in adding department list ", "At row".$i."duplicate data" );
                            }
                            $i++;
                        }//count
                        else{
                            //insufficient data
                            $error[] ="At row".$i."insufficient data";
                            $this->logger->write_logmessage("insert"," Error in adding department list ", "At row".$i."insufficient data"  );
                            $this->logger->write_dblogmessage("insert"," Error in adding department list ", "At row".$i."insufficient data" );
                            $i++;
                        }
                    }//end of while
                    fclose($h);	
                    if($flag){
                        $this->session->set_flashdata('error', ' File without data');
                        $this->load->view('upl/uploaddepartlist');
                        return;
                    }else{
                        //display error of array
                        foreach ($error as $item => $value):
                        $ferror = $ferror ."</br>". $item .":". $value;
                        endforeach;
                        $this->session->set_flashdata('error', $ferror);
                        $this->load->view('upl/uploaddepartlist');
                        return;
                    }
                }
            }//end of user file
        }//button pressed
        $this->load->view('upl/uploaddepartlist');
    }//method close
    /***********************closer of department csv file****************************************************/
       
}

