    <?php

/* 
 * @name Upl.php
 * @author Nagendra Kumar Singh(nksinghiitk@gmail.com)
 * @author Om Prakash (omprakashkgp@gmail.com)  upload csv file for staff profile registration in SIS
 * @author Manorama Pal(palseema30@gmail.com) upload csv file for staff tranfer orders and service particulars.
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
      $error =array();
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

                else {
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
                       if (count($datal) >= 13){
                            $pfno= $datal[0];
                            $empname = $datal[1];
                            $campus = $datal[2];
                            $uoc = $datal[3];
                            $uocuserid = $datal[4];
                            $ddouserid = $datal[5];
                            $dept = $datal[6];
                            $scheme = $datal[7];
                            $desig = $datal[8];
                            $payband = $datal[9];
                            $doa = $datal[10];
                            $dob = $datal[11];
                            $bankacc = $datal[12];
                            $aadhar = $datal[13];
                            $email = trim($datal[14]);
                            $email1 = trim($email, " ");
                            $role = 4;
                            if (count($datal) > 15)
                                $mobile = $datal[15];
                            else
                            $mobile='';
                            // check for duplicate
                            $isdup= $this->lgnmodel->isduplicate('edrpuser','username',$email );
                            if(!$isdup){
                                 //generate 10 digit random password
                                 $passwd=$this->commodel->randNum(10);
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
                                    'category_type'=>$role,
                                    'is_verified'=>1
                                 );
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
				$dataem = array(
                            		'emp_code' => $pfno,
                            		'emp_name' => $empname,
                   			'emp_specialisationid' => '',
                	    		'emp_dept_code'       => $this->commodel->get_listspfic1('Department', 'dept_id', 'dept_name', $dept)->dept_id,
                    			'emp_schemeid'        => $this->sismodel->get_listspfic1('scheme_department', 'sd_id', 'sd_name', $scheme)->sd_id,
                    			'emp_desig_code'      => $this->commodel->get_listspfic1('designation', 'desig_id', 'desig_name', $desig)->desig_id,
                    			'emp_post'            =>'',

                    			'emp_gender'          =>'',
                    			'emp_community'       =>'',
                    			'emp_religion'        =>'',
                    			'emp_caste'           =>'',
				
					'emp_type_code'       =>'',
                    			'emp_salary_grade'    => $this->sismodel->get_listspfic1('salary_grade_master', 'sgm_id', 'sgm_name', $payband)->sgm_id,
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
	                   		'emp_scid'            => $this->commodel->get_listspfic1('study_center', 'sc_id', 'sc_name', $campus)->sc_id,
        	            		'emp_uocid'           => $this->lgnmodel->get_listspfic1('authorities', 'id', 'name', $uoc)->id,
        	            		'emp_uocuserid'       => $uocuserid,
        	            		'emp_ddouserid'       => $ddouserid,
        		        	'emp_phone'           =>'' 

				 );
                    		/* insert record into  employe_emaster */
                                $this->sismodel->insertrec('employee_master', $dataem);
			        $this->logger->write_logmessage("insert", "data insert in employee_master table.");
                    		$this->logger->write_dblogmessage("insert", "data insert in employee_master table." );

                    		$dataems = array(
                        		'ems_code'         =>$pfno,
                        		'ems_working_type' =>''
					
		                    );
                    		/* insert record into  employe_emaster_support */
                    		$this->sismodel->insertrec('employee_master_support', $dataems);
                    		$this->logger->write_logmessage("insert", "data insert in employee_master_support table.");
                    		$this->logger->write_dblogmessage("insert", "data insert in employee_master_support table." );
				}		
                                if($userflagup){
                                /* insert into user_role_type */
                                $dataurt = array(
                                      'userid'=> $userid,
                                      'roleid'=> $role,
                                      'deptid'=> $this->commodel->get_listspfic1('Department', 'dept_id', 'dept_name', $dept)->dept_id,
                                      'scid'=> $this->commodel->get_listspfic1('study_center', 'sc_id', 'sc_name', $campus)->sc_id,
                                      'usertype'=>'Employee'
                                   );
                                 $userflagurt=$this->sismodel->insertrec('user_role_type', $dataurt) ;
                                 if($userflagurt){
                                        $sub='Staff Profile Registration' ;
                                        $mess="You are registered as a staff. Your user id ".$email ." and password is ".$passwd ;
                                        $mails = $this->mailmodel->mailsnd($email, $sub, $mess);
                                        //  mail flag check                         
                                        if($mails){
                                               $error[] ="At row".$i."sufficient data and mail sent sucessfully";
                                               $this->logger->write_logmessage("insert"," add staff edrpuser,profile, employee_master and user role type ", "record added successfully for.".$empname ." ".$email );
                                               $this->logger->write_dblogmessage("insert"," add staff edrpuser,profile, employee_master and user role type ", "record added successfully for.".$empname ." ".$email );
                                            }
                                            else{
                                               $error[] ="At row".$i."sufficient data and mail does sent";
                                               $this->logger->write_logmessage("insert"," add staff edrpuser,profile, employee_master and user role type ", "record added successfully for.".$empname ." ".$email ." and mail does sent");
                                               $this->logger->write_dblogmessage("insert"," add staff edrpuser,profile, employee_master and user role type ", "record added successfully for.".$empname ." ".$email." and mail does sent" );
                                            }
                                        }else{
                                            //set the message for error in entering data in user role type
                                            $this->logger->write_logmessage("insert"," Error in adding staff edrpuser,profile and user role type ", " data insert error . ".$empname ." ".$email );
                                            $this->logger->write_dblogmessage("insert"," Error in adding staff edrpuser,profile and user role type ", " data insert error . ".$empname ." ".$email);
                                            // delete edrp user data
                                            $result = $this->lgnmodel->deleterow('edrpuser','id',$userid);
                                            // delete user profile data
                                            $result = $this->lgnmodel->deleterow('userprofile','userid',$userid);
                                        }
                                    }else{
                                        //set the message for error in entering data in user profile table
                                        $this->logger->write_logmessage("insert"," Error in adding staff edrpuser,profile ", " data insert error . ".$empname ." ".$email  );
                                        $this->logger->write_dblogmessage("insert"," Error in adding staff edrpuser,profile ", " data insert error . ".$empname ." ".$email );
                                        // delete edrp user data
                                        $result = $this->lgnmodel->deleterow('edrpuser','id',$userid);
                                    }
                                }else{
                                    // set the message for error in entering data in edrp table
                                    $this->logger->write_logmessage("insert"," Error in adding staff edrpuser ", " data insert error . ".$empname ." ".$email  );
                                    $this->logger->write_dblogmessage("insert"," Error in adding staff edrpuser ", "data insert error . ".$empname ." ".$email );
                                }
                                // $this->session->set_flashdata('success', ' sufficient data');        
                            }//close for is dup
                            else{
                                $error[] ="At row".$i."duplicate data";
                                $this->logger->write_logmessage("insert"," Error in adding staff edrpuser ", "At row".$i."duplicate data"  );
                                $this->logger->write_dblogmessage("insert"," Error in adding staff edrpuser ", "At row".$i."duplicate data" );
                            }
                            $i++;
                        }
                        else{
                            //  insufficient data
                            $error[] ="At row".$i."insufficient data";
                            $this->logger->write_logmessage("insert"," Error in adding staff edrpuser ", "At row".$i."insufficient data"  );
                            $this->logger->write_dblogmessage("insert"," Error in adding staff edrpuser ", "At row".$i."insufficient data" );
                            //  $this->session->set_flashdata('error', ' insufficient data');
                            $i++;
                        }                     
                    }
                    if($flag){
                        $this->session->set_flashdata('error', ' File without data');
                        $this->load->view('upl/uploadstafflist');
                        return;
                    }else{
                        // print_r($error);
                        foreach ($error as $item => $value):
                        $ferror = $ferror ."</br>". $item .":". $value;
                        endforeach;
                        //display error of array
                        //put ferror in log file.
                        $this->session->set_flashdata('success', $ferror);
                        $this->load->view('upl/uploadstafflist');
                        return;
                    }
		}
            }//userfile checks
       }// check for pressing correct button
      $this->load->view('upl/uploadstafflist');
   }
   
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
                            'uit_registrarname'     => $getData[1],
                            'uit_desig'             => $getData[2],
                            'uit_uso_no'            => $getData[3],
                            'uit_date'              => date('y-m-d'), 
                            'uit_rc_no'             => $getData[4],
                            'uit_subject'           => $getData[5], 
                            'uit_referenceno'       => $getData[6],
                            'uit_ordercontent'      => $getData[7],
                            'uit_emptype'           => $getData[8],
                            'uit_uoc_from'          => $getData[9],
                            'uit_uoc_to'            => $getData[10], //getid
                            'uit_workdept_from'     => $getData[11],
                            'uit_dept_to'           => $getData[12], //getid
                            'uit_desig_from'        => $getData[13],
                            'uit_desig_to'          => $getData[14], ///getid
                            'uit_workingpost_from'  => $getData[15],
                            'uit_post_to'           => $getData[16],
                            'uit_tta_detail'        => $getData[17],
                            'uit_dateofrelief'      => $getData[18],
                            'uit_dateofjoining'     => $getData[19],
                            'uit_email_sentto '     => $getData[20],
                            
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
       //$this->session->set_flashdata($array_items);
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
                        'empsd_campuscode'  => $getData[1],
                        'empsd_desigcode'   => $getData[2],
                        'empsd_pbid'        => $getData[3], 
                        'empsd_pbdate'      => $getData[4],
                        'empsd_dojoin'      => $getData[5], 
                        'empsd_dorelev'     => $getData[6],
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
}
