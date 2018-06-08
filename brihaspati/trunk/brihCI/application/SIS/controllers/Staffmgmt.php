<?php

defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name Staffmgmt.php
 * @author Manorama Pal (palseema30@gmail.com) Staff Profile,  Staff transfer and posting
 * re-engineering in add profile and edit profile according to tanuvas structure - 16 OCT 2017  
 * @author Om Prakash (omprakashkgp@gmail.com) Staff Position , Staff Position Archive
 */

class Staffmgmt extends CI_Controller
{
 
    function __construct() {
        parent::__construct();
        $this->load->model('Common_model',"commodel");
        $this->load->model('Login_model',"lgnmodel"); 
        $this->load->model('SIS_model',"sismodel");
        $this->load->model('Dependrop_model',"depmodel");
        $this->load->model("Mailsend_model","mailmodel");
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('welcome');
        }
    }
 
    public function index(){
        
    //$this->load->view('staffmgmt/staffprofile');    
    }
    
    /* Display Employee record */

    public function employeelist(){
	$cdate = date('y-m-d');
	// add doris geater than current date and reason is null  in whdata
	$whdata = array ('emp_leaving' => NULL,'emp_dor>='=>$cdate);
        //  get role id and user id
        $rlid=$this->session->userdata('id_role');
        if ($rlid == 5){
                $usrid=$this->session->userdata('id_user');
                $deptid = '';
                $whdatad = array('userid' => $usrid,'roleid' => $rlid);
                $resu = $this->sismodel->get_listspficemore('user_role_type','deptid',$whdatad);
                foreach($resu as $rw){
                        $deptid=$rw->deptid;
                }
                $whdata['emp_dept_code'] = $deptid;
        }
	 $selectfield ="emp_id,emp_code,emp_photoname,emp_scid,emp_uocid,emp_dept_code,emp_schemeid,emp_specialisationid,emp_desig_code,emp_email,emp_phone,emp_aadhaar_no,emp_name,emp_worktype";
         $whorder = "emp_name asc,emp_dept_code asc,emp_desig_code asc";
         if(isset($_POST['filter'])) {
            //echo "ifcase post of filter";
            $wtype = $this->input->post('wtype');
	    $post  = $this->input->post('post');
	    $strin = $this->input->post('strin');
	    if((!empty($wtype)) && ($wtype != 'null') && ($wtype != ' ')){
                       $whdata['emp_worktype'] = $wtype;
            }
            if((!empty($post)) && ($post != 'null') && ($post != ' ') && ($post != "All")){
                        $whdata['emp_desig_code'] = $post;
            }else{
                        $post="All";
            }
	    // for string search
            if(!empty($strin)) {
                        $whdata['emp_name LIKE ' ] ='%'.$strin.'%';
            }
		 $this->wtyp = $wtype;
	         $this->desigm = $post;
	    	 $this->strin = $strin;
	         $data['records'] = $this->sismodel->get_orderlistspficemore('employee_master',$selectfield, $whdata,$whorder);
         }
         else{
         	$data['records']=$this->sismodel->get_orderlistspficemore('employee_master',$selectfield,$whdata,$whorder);
         }
	//$data['records'] = $this->sismodel->get_orderlistspficemore('employee_master','*',$whdata,'emp_dept_code asc,emp_desig_code asc');
//	$data['records'] = $this->sismodel->get_list('employee_master');
        $this->logger->write_logmessage("view"," view employee list" );
        $this->logger->write_dblogmessage("view"," view employee list");
        $this->load->view('staffmgmt/employeelist',$data);
    }

    public function staffprofile(){
        $this->subject= $this->commodel->get_listspfic2('subject','sub_id','sub_name');
        $this->orgcode=$this->commodel->get_listspfic1('org_profile','org_code','org_id',1)->org_code;
        $this->campus=$this->commodel->get_listspfic2('study_center','sc_id','sc_name','org_code',$this->orgcode);
       // $this->uoc=$this->lgnmodel->get_list('authorities');
        /*In future this code may be replace when either campusid added in the 
         authority or authority added in campus.*/
        //$this->uoc=$this->lgnmodel->get_list('authority_map');
        $this->desig= $this->commodel->get_listspfic2('designation','desig_id','desig_name');
        $this->salgrd=$this->sismodel->get_list('salary_grade_master');
        /*********************select category/community list*****************************************/
        $this->community=$this->commodel->get_listspfic2('category','cat_id','cat_name');
        $this->leavedata=$this->sismodel->get_list('leave_type_master');
        /**********************here we check that vacancy is available or not in staff position******************************************/
                    
        if(isset($_POST['staffprofile'])) {
      
            /*Form Validation*/
            $this->form_validation->set_rules('empcode','EmployeeCode','trim|required|xss_clean|alpha_numeric|callback_isEmpPFNoExist');
            //$this->form_validation->set_rules('emppfno','EmployeePFNO','trim|required|xss_clean|alpha_numeric');
            $this->form_validation->set_rules('empname','EmployeeName','trim|required|xss_clean');
            $this->form_validation->set_rules('specialisation','Specialisation','trim|xss_clean');
            $this->form_validation->set_rules('campus','Campus','trim|required|xss_clean');
            $this->form_validation->set_rules('uocontrol','UniversityOfficerControl','trim|required|xss_clean');
            $this->form_validation->set_rules('department','Department','trim|required|xss_clean');
            
            $this->form_validation->set_rules('schemecode','Scheme Name','trim|required|xss_clean');
            $this->form_validation->set_rules('designation','Designation','trim|required|xss_clean');
            $this->form_validation->set_rules('emppost','Employeepost','trim|required|xss_clean');
            $this->form_validation->set_rules('gender','Gender','trim|xss_clean');
            $this->form_validation->set_rules('community','Community','trim|xss_clean');
            $this->form_validation->set_rules('religion','Religion','trim|xss_clean');
            $this->form_validation->set_rules('caste','Caste','trim|xss_clean|alpha_numeric_spaces');
            $this->form_validation->set_rules('workingtype','Workingtype','trim|required|xss_clean');
            
            $this->form_validation->set_rules('emptype','Employee Type','trim|required|xss_clean');
            $this->form_validation->set_rules('payband','PayBand','trim|required|xss_clean');
            $this->form_validation->set_rules('basicpay','Basicpay','trim|xss_clean|numeric');
            $this->form_validation->set_rules('emolution','Emolution','trim|xss_clean|numeric');
            $this->form_validation->set_rules('empnhisidno','NHisIDno','trim|xss_clean');
            $this->form_validation->set_rules('dateofjoining','Date of Joining','trim|required|xss_clean');
            $this->form_validation->set_rules('pnp','Plan / Non Plan','trim|xss_clean');
            $this->form_validation->set_rules('phdstatus','Phd Status','trim|xss_clean');
            
            $this->form_validation->set_rules('Dateofphd','Date of Phd Finish','trim|xss_clean');
            $this->form_validation->set_rules('assrexam','AssrExam','trim|xss_clean');
            $this->form_validation->set_rules('assrexamdate','Date of AssrExam','xss_clean');
            $this->form_validation->set_rules('dateofretirement','Date of Retirement','trim|xss_clean');
            $this->form_validation->set_rules('dateofhgp','Date of HGP','trim|xss_clean');
            $this->form_validation->set_rules('panno','Pan No','trim|xss_clean|alpha_numeric');
            $this->form_validation->set_rules('Aadharrno','Aadhaar No','trim|required|xss_clean|numeric');
            
            $this->form_validation->set_rules('bankname','Bank Name','trim|xss_clean');
            $this->form_validation->set_rules('ifsccode','IFSC CODE','trim|xss_clean|alpha_numeric');
            $this->form_validation->set_rules('bankacno','Bank ACC No','trim|required|xss_clean|alpha_numeric');
            $this->form_validation->set_rules('DateofBirth','Date of Birth','trim|required|xss_clean');
            $this->form_validation->set_rules('fathername','Father Name','trim|xss_clean');
            $this->form_validation->set_rules('emailid','E-Mail ID','trim|xss_clean|required|valid_email');
            $this->form_validation->set_rules('Address','Address','trim|xss_clean');
            
            $this->form_validation->set_rules('mothertongue','MotherTongue','trim|xss_clean');
            $this->form_validation->set_rules('nativity','Nativity','trim|xss_clean');
            $this->form_validation->set_rules('phonemobileno','Phone/Mobile','trim|xss_clean|numeric');
            //some more  fields added on demand 
            $this->form_validation->set_rules('ddo','Drawing and Disbursing Officer','trim|xss_clean|required');
            $this->form_validation->set_rules('group','Group','trim|xss_clean|required');
            $this->form_validation->set_rules('orderno','Order No','trim|xss_clean');
            $this->form_validation->set_rules('phstatus','phstatus','trim|xss_clean');
            $this->form_validation->set_rules('phdetail','phdetail','trim|xss_clean|alpha_numeric_spaces');
            $this->form_validation->set_rules('Sabgroup','BloodGroup','trim|xss_clean');
            $this->form_validation->set_rules('dateofprob','Date of Probation','trim|xss_clean');
            $this->form_validation->set_rules('dateofregular','Date of Regularisation','trim|xss_clean');
            $this->form_validation->set_rules('qual','Qualification','trim|xss_clean');
            $this->form_validation->set_rules('remarks','Remarks','trim|xss_clean');
            $this->form_validation->set_rules('empgrade','Grade','trim|xss_clean');
            //more  fields added on demand
            $this->form_validation->set_rules('phddiscipline','Discipline','trim|xss_clean');
            $this->form_validation->set_rules('phdtype','phdtype','trim|xss_clean');
            $this->form_validation->set_rules('phdinstname','InstName','trim|xss_clean');
            $this->form_validation->set_rules('univdeput','univdeput','trim|xss_clean');
            $this->form_validation->set_rules('udeput','If YES','trim|xss_clean');
            $this->form_validation->set_rules('udt','If NO','trim|xss_clean');
            $this->form_validation->set_rules('leavedatefrom','Leave From','trim|xss_clean');
            $this->form_validation->set_rules('leavedateto','Leave To','trim|xss_clean');
            $this->form_validation->set_rules('netqual','NET qualified','trim|xss_clean');
            $this->form_validation->set_rules('netqualyes','NET Organiser','trim|xss_clean');
            $this->form_validation->set_rules('passyear','NET passyear','trim|xss_clean');
            $this->form_validation->set_rules('netdiscipline','NET Discipline','trim|xss_clean');
            $this->form_validation->set_rules('vciregno','VCIregno','trim|xss_clean');
            $this->form_validation->set_rules('vciregdate','VCIregdate','trim|xss_clean');
            $this->form_validation->set_rules('asignname','Assignment Name','trim|xss_clean');
            $this->form_validation->set_rules('asignother','Assignment Others','trim|xss_clean');
            $this->form_validation->set_rules('asigndatefrom','Assignment datefrom','trim|xss_clean');
            $this->form_validation->set_rules('asigndateto','Assignment dateto','trim|xss_clean');
            $this->form_validation->set_rules('asignplace','Assignment place','trim|xss_clean');
            $this->form_validation->set_rules('secndemailid','secondary emailid','trim|xss_clean|valid_email');
            
            
            //Repopulate forms value
            /* if($_POST){
		$this->data['empcode']['value']=$this->input->get_post('empcode',TRUE);
	    */    
            if($this->form_validation->run() == FALSE){
                
                $this->load->view('staffmgmt/staffprofile');
                //redirect('staffmgmt/staffprofile');
                return;
            }
            //if($this->form_validation->run()==TRUE){
            else{
                //$udval='';
                $pfno=0;
                $bank_ifsccode=$_POST['bankname'].",".$_POST['ifsccode'];
                $emppostname = $this->commodel->get_listspfic1('designation','desig_name','desig_id',$_POST['emppost'])->desig_name; 
               // $uocid=$this->lgnmodel->get_listspfic1('authority_map', 'authority_id', 'user_id',$_POST['uocontrol'])->authority_id;
                //$ddoid=$this->lgnmodel->get_listspfic1('authority_map', 'authority_id', 'user_id',$_POST['ddo'])->authority_id;
                //-----------change employee photo name ----------------//
                $empcode=$_POST['empcode'];
                if(!empty($_FILES['userfile']['name'])){
                $newFileName = $_FILES['userfile']['name'];
                $fileExt1 = explode('.', $newFileName);
                $file_ext = end( $fileExt1);
                $new_name = $empcode.".".$file_ext; 
		}
                else{
                   $new_name=''; 
                }
		$email = $_POST['emailid'];
		if(empty($email)){
                    $email = $empcode .'@tanuvas.org.in';
                }
                if(!empty($_POST['univdeput'])){
                    if($_POST['univdeput'] =='Yes'){
                        $udval=$_POST['univdeput'].",".$_POST['udeput'];
                    }
                    else{
                        $udval=$_POST['univdeput'].",".$_POST['udt'].",".$_POST['leavedatefrom'].",".$_POST['leavedateto'];
                       
                    }
                } 
                else{
                    $udval=NULL;
                }
                if(!empty($_POST['netqual'])){
                    if($_POST['netqual'] =='Yes'){
                        $netdetail=$_POST['netqual'].",".$_POST['netqualyes'];
                        $netpassyear = $_POST['passyear'];
                         $netdispln = $_POST['netdiscipline'];   
                    }
                    else{
                        $netdetail=$_POST['netqual'];
                        $netpassyear=NULL;
                        $netdispln=NULL;
                    }
                    
                }
                else{
                    $netdetail=NULL;
                    $netpassyear=NULL;
                    $netdispln=NULL;
                }

                //-------------------------------------------------------------
                $data = array(
                    'emp_code'                  =>$_POST['empcode'],
                   // 'emp_pfno'                =>$_POST['emppfno'],
                    'emp_name'                  =>$_POST['empname'],
                    'emp_specialisationid'      =>$_POST['specialisation'],
                    
                    'emp_scid'                  =>$_POST['campus'],
                    'emp_uocid'                 =>$_POST['uocontrol'],
                   // 'emp_uocid'                 =>$uocid,
                    'emp_dept_code'             =>$_POST['department'],
                    'emp_schemeid'              =>$_POST['schemecode'],
                    'emp_desig_code'            =>$_POST['designation'],
                    //'emp_post'                  =>$_POST['emppost'],
                    'emp_post'                  =>$emppostname,
                    
                    'emp_gender'                =>$_POST['gender'],
                    'emp_community'             =>$_POST['community'],
                    'emp_religion'              =>$_POST['religion'],
                    'emp_caste'                 =>$_POST['caste'],
                  
                    
                    'emp_type_code'             =>$_POST['emptype'],
                    'emp_salary_grade'          =>$_POST['payband'],
                    'emp_basic'                 =>$_POST['basicpay'],
                    'emp_emolution'             =>$_POST['emolution'],
                    'emp_nhisidno'              =>$_POST['empnhisidno'],
                    'emp_doj'                   =>$_POST['dateofjoining'],
                    'emp_pnp'                   =>$_POST['pnp'],
                    'emp_phd_status'            =>$_POST['phdstatus'],
                        
                    'emp_dateofphd'             =>$_POST['dateofphd'],
                    'emp_AssrExam_status'       =>$_POST['assrexam'],
                    'emp_dateofAssrExam'        =>$_POST['assrexamdate'],
                    'emp_dor'                   =>$_POST['dateofretirement'],
                    'emp_dateofHGP'             =>$_POST['dateofhgp'],
                    'emp_pan_no'                =>$_POST['panno'],
                    
                    'emp_aadhaar_no'            =>$_POST['Aadharrno'],
                    'emp_bank_ifsc_code'        =>$bank_ifsccode,
                    'emp_bank_accno'            =>$_POST['bankacno'],
                    'emp_dob'                   =>$_POST['DateofBirth'],
                    'emp_father'                =>$_POST['fathername'],
                    'emp_email'                 =>$email,
                    
                    'emp_address'               =>$_POST['Address'],
                    'emp_mothertongue'          =>$_POST['mothertongue'],
                    'emp_citizen'               =>$_POST['nativity'],
                    'emp_phone'                 =>$_POST['phonemobileno'],
                    //more field added
                    'emp_worktype'              =>$_POST['workingtype'],
                    'emp_uocuserid'             =>$_POST['uocontrol'],
                    'emp_ddouserid'             =>$_POST['ddo'],
                    'emp_ddoid'                 =>$_POST['ddo'],
                    'emp_group'                 =>$_POST['group'],
                    'emp_apporderno'            =>$_POST['orderno'],
                    'emp_phstatus'              =>$_POST['phstatus'],
                    'emp_phdetail'              =>$_POST['phdetail'],
                    'emp_bloodgroup'            =>$_POST['Sabgroup'], 
                    'emp_doprobation'           =>$_POST['dateofprob'], 
                    'emp_doregular'             =>$_POST['dateofregular'], 
                    'emp_qual'                  =>$_POST['qual'], 
                    'emp_remarks'               =>$_POST['remarks'],
                    'emp_grade'			=>$_POST['empgrade'],  
                    'emp_secndemail'            =>$_POST['secndemailid'],
                    'emp_phddiscipline'         =>$_POST['phddiscipline'],
                    'emp_phdtype'               =>$_POST['phdtype'],
                    'emp_phdinstname'           =>$_POST['phdinstname'],
                    'emp_phdunivdeput'          =>$udval,
                    'emp_netqualified'          =>$netdetail,
                    'emp_netpassingyear'        =>$netpassyear,
                    'emp_netdiscipline'         =>$netdispln,
                    'emp_vciregno'              =>$_POST['vciregno'],
                    'emp_vciregdate'            =>$_POST['vciregdate'],
                    'emp_photoname'             =>$new_name 
                        
                        
                );
		if ((strpos($email, 'temp') === 0)||(strpos($email, $pfno) === 0)) {
                       	$passwd = $empcode;
                }else{
                
                	//generate 10 digit random password
			$passwd=$this->commodel->randNum(10);
		}
                $isdupl= $this->lgnmodel->isduplicate('edrpuser','username',$_POST['emailid']);
		if(!$isdupl){

                    /* generate the hash of password */
                    $password=md5($passwd);
                    $dataedrpusr = array(
                        'username'=> $_POST['emailid'],
                        'password'=> $password,
                        'email'=> $_POST['emailid'],
                        'componentreg'=> '*',
                        'mobile'=>$_POST['phonemobileno'],
                        'status'=>1,
                        'category_type'=>'Employee',
                        'is_verified'=>1
                    );
                    /* insert record in edrpuser */
                    $this->lgnmodel->insertrec('edrpuser',$dataedrpusr);
                    $this->logger->write_logmessage("insert", "data insert in edrpuser table.");
                    $this->logger->write_dblogmessage("insert", "data insert in edrpuser table." );
                    
                    /*get user id from login (edrpuser table)*/
                    $getid= $this->lgnmodel->get_listspfic1('edrpuser','id','username',$_POST['emailid']);
                    $usrid=$getid->id;
                    $datausrpf = array(
                        'userid'=> $usrid,
                        'firstname'=>$_POST['empname'],
                        'lang'=> 'english',
                        'mobile'=>$_POST['phonemobileno'],
                        'status'=>1
                    );
                    /* insert record in userprofile table */
                    $this->lgnmodel->insertrec('userprofile', $datausrpf);
                    $this->logger->write_logmessage("insert", "data insert in userprofile table.");
                    $this->logger->write_dblogmessage("insert", "data insert in userprofile table." );
                }//edusr
                //check for duplicate record in sis (payroll)
                $emdupl= $this->sismodel->isduplicate('employee_master','emp_email',$_POST['emailid']);
                if(!$emdupl){
                        
                    /* insert record in  employeemaster */
                    $this->sismodel->insertrec('employee_master', $data);
                    $this->logger->write_logmessage("insert", "data insert in employee_master table.");
                    $this->logger->write_dblogmessage("insert", "data insert in employee_master table." );

                    $empid= $this->sismodel->get_listspfic1('employee_master','emp_id','emp_email',$_POST['emailid'])->emp_id;
                    /* insert record in service details , if uo then update in uolist table  and if hod then update in hod list table */
                    $desigcode=$this->commodel->get_listspfic1('designation','desig_code','desig_id',$_POST['designation'])->desig_code;
                    //$shownap=$this->commodel->get_listspfic1('designation','desig_id','desig_name',$_POST['emp_post'])->desig_id;
                    $this->sismodel->insertsdetail($empid,$_POST['campus'],$_POST['uocontrol'],$_POST['department'],$desigcode,
                    $_POST['schemecode'],$_POST['ddo'],$_POST['group'],$_POST['payband'],'',$_POST['emppost'],'','','');    
                                       
                    
                    if(!empty($_POST['asignname'])){
                        if($_POST['asignname'] == 'Others'){
                            $asignname=$_POST['asignname'].",".$_POST['asignother'];
                        }
                        else{
                            $asignname=$_POST['asignname'];
                        }
                    }
                    else{
                        $asignname=NULL;
                    }
                    $dataasign=array(
                        'aa_empid'              =>$empid,
                        'aa_asigname'           =>$asignname,
                        'aa_asigperiodfrom'     =>$_POST['asigndatefrom'],
                        'aa_asigperiodto'       =>$_POST['asigndateto'],
                        'aa_place'              =>$_POST['asignplace'],
                        'aa_creatorid'          =>$this->session->userdata('username'),
                        'aa_creatordate'        =>date('y-m-d'),
                        'aa_modifierid'         =>$this->session->userdata('username'),
                        'aa_modifydate'         =>date('y-m-d'),
                        
                    );
                    /* insert record in  additional assignments */
                    $this->sismodel->insertrec('additional_assignments', $dataasign);
                    $this->logger->write_logmessage("insert", "data insert in additional_assignments table.");
                    $this->logger->write_dblogmessage("insert", "data insert in additional_assignments table." );
                    
                    $dataems = array(
                       'ems_code'              =>$_POST['empcode'],
                      // 'ems_working_type'      =>$_POST['workingtype']
		    );
                    /* insert record in  employe_emaster_support */
                    $this->sismodel->insertrec('employee_master_support', $dataems);
                    $this->logger->write_logmessage("insert", "data insert in employee_master_support table.");
                    $this->logger->write_dblogmessage("insert", "data insert in employee_master_support table." );
                    
                    $getid= $this->lgnmodel->get_listspfic1('edrpuser','id','username',$_POST['emailid']);
                    $usrid=$getid->id;
                    $dataurt = array(
                        'userid'=> $usrid,
                        'roleid'=> 4,
                        'scid'  => $_POST['campus'],
                        'deptid'=> $_POST['department'],
                        'usertype'=>"Employee"
                    );
                    /* insert record in user_role_type */
                    $this->sismodel->insertrec('user_role_type',$dataurt);
                    $this->logger->write_logmessage("insert", "data insert in user_role_type table.");
                    $this->logger->write_dblogmessage("insert", "data insert in user_role_type table." );
                    /*************************************updating the staff position table*****************/
                   
                    $this->sismodel->updatestaffposition($_POST['campus'],$_POST['uocontrol'], $_POST['department'],$_POST['emppost'],$_POST['workingtype'],$_POST['emptype']) ;
                   
                    /*************************************close updating the staff position table*****************/
                    /* upload photo*/
                    $msg='';
                    $msgphoto='';
                    if(!empty($_FILES['userfile']['name'])){
                        $empcode=$_POST['empcode'];
                        $new_name = $empcode; 
                                                
                        $config = array(
                            'upload_path' =>  "./uploads/SIS/empphoto",
                            'allowed_types' => "gif|jpg|png|jpeg",
                            'overwrite' => TRUE,
                            'max_size' => "100000", // Can be set to particular file size 
                            'max_height' => "768",
                            'max_width' => "1024",
                            //'encrypt_name' => TRUE,
                            'file_name' => $new_name
                        );
                        $this->load->library('upload',$config);
                        if(! $this->upload->do_upload()){
                            $ferror='';
                            $error = array('error' => $this->upload->display_errors()); 
                            foreach ($error as $item => $value):
                               $ferror = $ferror ."</br>". $item .":". $value;
                            endforeach;
                            // $ferror=str_replace("\r\n","",$ferror);
                            $simsg = "The permitted size of Photo is 100kb";
                            $ferror = $simsg.$ferror;
                            $this->logger->write_logmessage("uploadphoto","photo upload in sis error", $ferror);
                            $this->logger->write_dblogmessage("uploadphoto","photo upload in sis error", $ferror);
                            $this->session->set_flashdata('err_message', $ferror);
                            $this->load->view('staffmgmt/staffprofile');
                      
                        }
                        else { 
                            $upload_data=$this->upload->data();
                            $msgphoto=" and photo" ;
                        } 
                    }//check for empphoto
                    //if sucess send mail to user with login details 
                    $sub='User Registration in Staff information System';
                    $mess="Your registration is completed. The user id ".$_POST['emailid']." and password is ".$passwd ."\r\n".'Kindly check with website:'."\r\n". site_url('welcome');
			$secmail=$this->sismodel->get_listspfic1('employee_master','emp_secndemail','emp_email',$_POST['emailid'])->emp_secndemail;
                                if((!empty($secmail))&&($secmail != '')&&($secmail != null)){
                                        $mails=$this->mailmodel->mailsnd($secmail,$sub,$mess,'');
                                }

                    $mailstoperson =$this->mailmodel->mailsnd($_POST['emailid'], $sub, $mess,'');
                    //  mail flag check 	
                    if($mailstoperson){
                        //echo "in if part mail";
                        $mailmsg='Please check your mail for username and password....Mail send successfully';
                        $this->logger->write_logmessage("insert"," add user profile in edrpuser,profile and user role type ",'mail send successfully  to '.$_POST['emailid'] );
                        $this->logger->write_dblogmessage("insert"," add user profile in edrpuser,profile and user role type ",'mail send successfully  to '.$_POST['emailid'] );
                    }
                    else{
                        //echo "in else part";
                        $mailmsg='Mail does not sent';
                        $this->logger->write_logmessage("insert"," add user profile in  edrpuser,userprofile employeemaster and user role type ", "Mail does not sent to ".$_POST['emailid']);
                        $this->logger->write_dblogmessage("insert"," add user profile in edrpuser,userprofile,employeemaster and user role type ", "Mail does not sent to ".$_POST['emailid']);
                    }
                    $this->logger->write_logmessage("insert", "record insert successfully.");
                    $this->logger->write_dblogmessage("insert", "record insert successfully." );
                    $this->session->set_flashdata('success','User profile'.$msgphoto.' add successfully.'.$mailmsg);
                    redirect('staffmgmt/employeelist');
                    
                } //emif  
                else{
                    $this->session->set_flashdata('err_message', 'User profile is already exist.');
                    redirect('staffmgmt/staffprofile');
                  
                }
                   
            }//else valid true form
            
        }//post
        
    $this->load->view('staffmgmt/staffprofile');    
    }
    
    /* This function has been created for get list of Department on the basis of campus */
    public function getdeptlist(){
        $scid = $this->input->post('campusname');
        $this->depmodel->getdeptlist_model($scid);
    }
    
    /* This function has been created for get list of schemes on the basis of  selected department */
    public function getdeptschemelist(){
        $deptid = $this->input->post('deptid');
       // echo json_encode("this is testing----".$dept);
        $this->depmodel->get_sschemelist($deptid);
    }
    public function isEmpPFNoExist(){
        
        $empcode = $this->input->post('empcode');
        if(!empty($empcode)){
            $is_exist= $this->sismodel->isduplicate('employee_master','emp_code',$empcode);
            if ($is_exist)
            {
                $this->form_validation->set_message('isEmpPFNoExist', 'Employee PF No ' . $empcode .' is already exist.');
                return false;
            }
            else {
                return true;
            } 
        }    
        
       
    }
    /****************************  START OPEN EDIT FORM WITH DATA *************/
    function editempprofile($id){
        $this->roleid=$this->session->userdata('id_role');
        /*get detail of selected emplyee by passing id for edit*/
        $this->subject= $this->commodel->get_listspfic2('subject','sub_id','sub_name');
        $this->orgcode=$this->commodel->get_listspfic1('org_profile','org_code','org_id',1)->org_code;
        $this->campus=$this->commodel->get_listspfic2('study_center','sc_id','sc_name','org_code',$this->orgcode);
        //$this->uoc=$this->lgnmodel->get_list('authorities');
        $this->uoc=$this->lgnmodel->get_list('authority_map');
        $this->ddo=$this->sismodel->get_list('ddo');
        $this->desig= $this->commodel->get_listspfic2('designation','desig_id','desig_name');
        $this->salgrd=$this->sismodel->get_list('salary_grade_master');
        /*********************select category/community list*****************************************/
        $this->community=$this->commodel->get_listspfic2('category','cat_id','cat_name');
        $this->leavedata=$this->sismodel->get_list('leave_type_master');
        $editemp_data['id'] = $id;
        $empmaster_data=$this->sismodel->get_listrow('employee_master','emp_id', $id);
        $editemp_data['editdata'] = $empmaster_data->row();
        /*********************************select additional assignments***********************************/
	$selectfield="*";
        $whdata = array ('aa_empid' => $id);
        $whorder = 'aa_asigperiodfrom desc';
        $editemp_data['editasign'] = $this->sismodel->get_orderlistspficemore('additional_assignments',$selectfield,$whdata,$whorder);
        $this->load->view('staffmgmt/editempprofile',$editemp_data);     
        
    }
    /****************************  START OPEN EDIT FORM WITH DATA *************/
    
    /****************************  START UPDATE DATA *************************/
    public function update_profile($id)
    {
        $this->roleid=$this->session->userdata('id_role');
        $editemp_data['id'] = $id;
        $empmaster_data=$this->sismodel->get_listrow('employee_master','emp_id', $id);
        $editemp_data['editdata'] = $empmaster_data->row();     
        if(isset($_POST['updateprofile'])) {
            /*Form Validation*/
            //$this->form_validation->set_rules('empcode','EmployeeCode','trim|required|xss_clean|alpha_numeric|callback_isEmpPFNoExist');
            $this->form_validation->set_rules('empcode','EmployeeCode','trim|required|xss_clean|alpha_numeric');
            $this->form_validation->set_rules('empname','EmployeeName','trim|required|xss_clean');
            $this->form_validation->set_rules('specialisation','Specialisation','trim|xss_clean');
// enabled by nks
            $this->form_validation->set_rules('campus','Campus','trim|required|xss_clean');
            $this->form_validation->set_rules('uocontrol','UniversityOfficerControl','trim|required|xss_clean');
            $this->form_validation->set_rules('department','Department','trim|required|xss_clean');           
           $this->form_validation->set_rules('schemecode','Scheme Name','trim|required|xss_clean');
            $this->form_validation->set_rules('designation','Designation','trim|required|xss_clean');
            $this->form_validation->set_rules('emppost','Employeepost','trim|xss_clean');
// enabled by nks
            $this->form_validation->set_rules('gender','Gender','trim|xss_clean');
            $this->form_validation->set_rules('community','Community','trim|xss_clean');
            $this->form_validation->set_rules('religion','Religion','trim|xss_clean');
            $this->form_validation->set_rules('caste','Caste','trim|xss_clean|alpha_numeric_spaces');
// enabled by nks
            $this->form_validation->set_rules('workingtype','Workingtype','trim|xss_clean');            
            $this->form_validation->set_rules('emptype','Employee Type','trim|xss_clean');
            $this->form_validation->set_rules('payband','PayBand','required|xss_clean');
// enabled by nks
            $this->form_validation->set_rules('basicpay','Basicpay','trim|xss_clean|numeric');
            $this->form_validation->set_rules('emolution','Emolution','trim|xss_clean|numeric');
            $this->form_validation->set_rules('empnhisidno','NHisIDno','trim|xss_clean');
            $this->form_validation->set_rules('dateofjoining','Date of Joining','trim|required|xss_clean');
            $this->form_validation->set_rules('pnp','Paln / Non Plan','trim|xss_clean');
            $this->form_validation->set_rules('phdstatus','Phd Status','trim|xss_clean');
            
            $this->form_validation->set_rules('Dateofphd','Date of Phd Finish','trim|xss_clean');
            $this->form_validation->set_rules('assrexam','AssrExam','xss_clean');
            $this->form_validation->set_rules('assrexamdate','Date of AssrExam','xss_clean');
            $this->form_validation->set_rules('dateofretirement','Date of Retirement','trim|xss_clean');
            $this->form_validation->set_rules('dateofhgp','Date of HGP','trim|xss_clean');
            $this->form_validation->set_rules('panno','Pan No','trim|alpha_numeric');
            $this->form_validation->set_rules('Aadharrno','Aadhaar No','trim|required|xss_clean|numeric');
            
            $this->form_validation->set_rules('bankname','Bank Name','trim|xss_clean');
            $this->form_validation->set_rules('ifsccode','IFSC CODE','trim|xss_clean|alpha_numeric');
            $this->form_validation->set_rules('bankacno','Bank ACC No','trim|required|xss_clean|alpha_numeric');
            $this->form_validation->set_rules('DateofBirth','Date of Birth','trim|required|xss_clean');
            $this->form_validation->set_rules('fathername','Father Name','trim|xss_clean');
            //$this->form_validation->set_rules('emailid','E-Mail ID','trim|required');
            $this->form_validation->set_rules('Address','Address','trim|xss_clean');
            
            $this->form_validation->set_rules('mothertongue','MotherTongue','trim|xss_clean');
            $this->form_validation->set_rules('nativity','Nativity','trim|xss_clean');
            $this->form_validation->set_rules('phonemobileno','Phone/Mobile','trim|xss_clean|numeric');
            
            //some extra field 
            $this->form_validation->set_rules('ddo','Drawing and Disbursing Officer','trim|xss_clean|required');
            $this->form_validation->set_rules('group','Group','trim|xss_clean|required');
            $this->form_validation->set_rules('orderno','Order No','trim|xss_clean');
            $this->form_validation->set_rules('phstatus','phstatus','trim|xss_clean');
            $this->form_validation->set_rules('phdetail','phdetail','trim|xss_clean|alpha_numeric_spaces');
            $this->form_validation->set_rules('Sabgroup','BloodGroup','trim|xss_clean');
            $this->form_validation->set_rules('dateofprob','Date of Probation','trim|xss_clean');
            $this->form_validation->set_rules('dateofregular','Date of Regularisation','trim|xss_clean');
            $this->form_validation->set_rules('qual','Qualification','trim|xss_clean');
            $this->form_validation->set_rules('remarks','Remarks','trim|xss_clean');
            $this->form_validation->set_rules('empgrade','Grade','trim|xss_clean');
            //more  fields added on demand
            $this->form_validation->set_rules('phddiscipline','Discipline','trim|xss_clean');
            $this->form_validation->set_rules('phdtype','phdtype','trim|xss_clean');
            $this->form_validation->set_rules('phdinstname','InstName','trim|xss_clean');
            $this->form_validation->set_rules('univdeput','univdeput','trim|xss_clean');
            $this->form_validation->set_rules('udeput','If YES','trim|xss_clean');
            $this->form_validation->set_rules('udt','If NO','trim|xss_clean');
            $this->form_validation->set_rules('leavedatefrom','Leave From','trim|xss_clean');
            $this->form_validation->set_rules('leavedateto','Leave To','trim|xss_clean');
            $this->form_validation->set_rules('netqual','NET qualified','trim|xss_clean');
            $this->form_validation->set_rules('netqualyes','NET Organiser','trim|xss_clean');
            $this->form_validation->set_rules('passyear','NET passyear','trim|xss_clean');
            $this->form_validation->set_rules('netdiscipline','NET Discipline','trim|xss_clean');
            $this->form_validation->set_rules('vciregno','VCIregno','trim|xss_clean');
            $this->form_validation->set_rules('vciregdate','VCIregdate','trim|xss_clean');
            $this->form_validation->set_rules('asignname','Assignment Name','trim|xss_clean');
            $this->form_validation->set_rules('asignother','Assignment Others','trim|xss_clean');
            $this->form_validation->set_rules('asigndatefrom','Assignment datefrom','trim|xss_clean');
            $this->form_validation->set_rules('asigndateto','Assignment dateto','trim|xss_clean');
            $this->form_validation->set_rules('asignplace','Assignment place','trim|xss_clean');
            $this->form_validation->set_rules('secndemailid','secondary emailid','trim|xss_clean|valid_email');

            if($this->form_validation->run() == FALSE){
                //redirect('staffmgmt/editempprofile/'.$id);
                $this->load->view('staffmgmt/editempprofile',$editemp_data);
                return;
               
            }//formvalidation
            else{
		
       	    $empoldcode=$this->sismodel->get_listspfic1('employee_master','emp_code','emp_id',$id)->emp_code;     
            $bankname=$this->input->post('bankname');
            $ifsccode= $this->input->post('ifsccode');
            $emppostname = $this->commodel->get_listspfic1('designation','desig_name','desig_id',$_POST['emppost'])->desig_name; 
            //$emp_post= $this->input->post('emppost');
            //$emppostid= $this->commodel->get_listspfic1('designation','desig_id','desig_namedesig_id',$emp_post)->desig_id;     
           // $uocuid= $this->input->post('uocontrol');  
            //$uocid=$this->lgnmodel->get_listspfic1('authority_map', 'authority_id', 'user_id',$uocuid)->authority_id;
            //$ddoid=$this->lgnmodel->get_listspfic1('authority_map', 'authority_id', 'user_id',$_POST['ddo'])->authority_id;
            $empcode= $this->input->post('empcode');
		if(!($empcode === $empoldcode )){
			$is_exist= $this->sismodel->isduplicate('employee_master','emp_code',$empcode);
	        	if ($is_exist)
            		{
				$this->session->set_flashdata('err_message', 'Employee PF No ' . $empcode .' is already exist.');
//				$this->load->view('staffmgmt/editempprofile',$editemp_data);
	                    	redirect('staffmgmt/employeelist');
				return;
            		}
		}
            if(!empty($_FILES['userfile']['name'])){
            $newFileName = $_FILES['userfile']['name'];
            $fileExt1 = explode('.', $newFileName);
            $file_ext = end( $fileExt1);
           // $empcode=$_POST['empcode'];
            $new_name = $empcode.".".$file_ext; 
            }
            else{
                $new_name=$this->sismodel->get_listspfic1('employee_master','emp_photoname','emp_id',$id)->emp_photoname;
            }
                /*'emp_name'                       => $this->input->post('empname'),*/
               // 'emp_bank_ifsc_code'             => $this->input->post('bankname'),
                /*----extra field added---------------------------------------------*/
            
            if(!empty($_POST['univdeput'])){
                if($_POST['univdeput'] =='Yes'){
                    $udval=$_POST['univdeput'].",".$_POST['udeput'];
                }
                else{
                    $udval=$_POST['univdeput'].",".$_POST['udt'].",".$_POST['leavedatefrom'].",".$_POST['leavedateto'];
                }
            } 
            else{
                $udval=NULL;
            }
            if(!empty($_POST['netqual'])){
                if($_POST['netqual'] =='Yes'){
                    $netdetail=$_POST['netqual'].",".$_POST['netqualyes'];
                    $netpass=$_POST['passyear'];
                    $netdpln=$_POST['netdiscipline'];
                }
                else{
                    $netdetail=$_POST['netqual'];
                    $netpass=NULL;
                    $netdpln=NULL;
                }
                    
            }
            else{
                $netdetail=NULL;
                $netpass=NULL;
                $netdpln=NULL;
            }
            
            $data = array(
		'emp_code'			=> $this->input->post('empcode'),
                'emp_specialisationid'           => $this->input->post('specialisation'),
// enabled by nks start
                'emp_scid'                       => $this->input->post('campus'),
                //'emp_uocuserid'                  => $this->input->post('uocontrol'),
                'emp_uocid'                      => $this->input->post('uocontrol'),
                //'emp_uocid'                      => $uocid,
                'emp_dept_code'                  => $this->input->post('department'),
                'emp_schemeid'                   => $this->input->post('schemecode'),
                'emp_desig_code'                 => $this->input->post('designation'),
                
                'emp_post'                       =>$emppostname,
// enabled by nks close
                'emp_gender'                     => $this->input->post('gender'),
                'emp_community'                  => $this->input->post('community'),
                'emp_religion'                   => $this->input->post('religion'),                
                'emp_caste'                      => $this->input->post('caste'),
// enabled by nks start
                'emp_worktype'                   => $this->input->post('workingtype'),
                'emp_type_code'                  => $this->input->post('emptype'),
                'emp_salary_grade'               => $this->input->post('payband'),
// enabled by nks     close           
                'emp_basic'                      => $this->input->post('basicpay'),
                'emp_emolution'                  => $this->input->post('emolution'),
                'emp_nhisidno'                   => $this->input->post('empnhisidno'),
                'emp_doj'                        => $this->input->post('dateofjoining'),
                
                'emp_pnp'                        => $this->input->post('pnp'),
                'emp_phd_status'                 => $this->input->post('phdstatus'),
                'emp_dateofphd'                  => $this->input->post('dateofphd'),
                'emp_AssrExam_status'            => $this->input->post('assrexam'),
                
                'emp_dateofAssrExam'             => $this->input->post('assrexamdate'),
                'emp_dor'                        => $this->input->post('dateofretirement'),
                'emp_dateofHGP'                  => $this->input->post('dateofhgp'),
                'emp_pan_no'                     => $this->input->post('panno'),
                
                'emp_aadhaar_no'                 => $this->input->post('Aadharrno'),
                'emp_bank_ifsc_code'             => $bankname.','.$ifsccode,
                'emp_dob'                        => $this->input->post('DateofBirth'),
                'emp_father'                     => $this->input->post('fathername'),
                
                'emp_address'                    => $this->input->post('Address'),
                'emp_mothertongue'               => $this->input->post('mothertongue'),
                'emp_citizen'                    => $this->input->post('nativity'),
                'emp_phone'                      => $this->input->post('phonemobileno'),
                'emp_name'                       => $this->input->post('empname'),
                'emp_bank_accno'		 => $this->input->post('bankacno'),
               // 'emp_ddouserid'                  => $this->input->post('ddo'),
//// enabled by nks start
                'emp_ddoid'                      => $this->input->post('ddo'),
                'emp_group'                      => $this->input->post('group'),
// enabled by nks close
                'emp_apporderno'                 => $this->input->post('orderno'),
                'emp_phstatus'                   => $this->input->post('phstatus'),
                'emp_phdetail'                   => $this->input->post('phdetail'),
                'emp_bloodgroup'                 => $this->input->post('Sabgroup'),
                'emp_doprobation'                => $this->input->post('dateofprob'), 
                'emp_doregular'                  => $this->input->post('dateofregular'), 
                'emp_qual'                       => $this->input->post('qual'), 
                'emp_remarks'                    => $this->input->post('remarks'), 
                'emp_photoname'                  => $new_name,
		'emp_grade'                      => $this->input->post('empgrade'), 
                'emp_secndemail'                 =>$_POST['secndemailid'],
                'emp_phddiscipline'              =>$_POST['phddiscipline'],
                'emp_phdtype'                    =>$_POST['phdtype'],
                'emp_phdinstname'                =>$_POST['phdinstname'],
                'emp_phdunivdeput'               =>$udval,
                'emp_netqualified'               =>$netdetail,
                'emp_netpassingyear'             =>$netpass,
                'emp_netdiscipline'              =>$netdpln,
                'emp_vciregno'                   =>$_POST['vciregno'],
                'emp_vciregdate'                 =>$_POST['vciregdate'],   
            );
//print_r($data);
            /* upload photo*/
            $msg='';
            if(!empty($_FILES['userfile']['name'])){
                $empcode=$_POST['empcode'];
                //$newFileName = $_FILES['userfile']['name'];
                // $fileExt1 = explode('.', $newFileName);
                //$file_ext = end( $fileExt1);
                $new_name = $empcode; 
                                                
                $config = array(
                    'upload_path' =>  "./uploads/SIS/empphoto",
                    'allowed_types' => "gif|jpg|png|jpeg",
                    'overwrite' => TRUE,
                    'max_size' => "100000", // Can be set to particular file size 
                    'max_height' => "768",
                    'max_width' => "1024",
                            //'encrypt_name' => TRUE,
                    'file_name' => $new_name
                );
                $this->load->library('upload',$config);
                if(! $this->upload->do_upload()){
                    $ferror='';
                    $error = array('error' => $this->upload->display_errors()); 
                    foreach ($error as $item => $value):
                    $ferror = $ferror ."</br>". $item .":". $value;
                    endforeach;
                    // $ferror=str_replace("\r\n","",$ferror);
                    $simsg = "The permitted size of Photo is 100kb";
                    $ferror = $simsg.$ferror;
                    $this->logger->write_logmessage("uploadphoto","photo upload in sis error", $ferror);
                    $this->logger->write_dblogmessage("uploadphoto","photo upload in sis error", $ferror);
                    $this->session->set_flashdata('err_message', $ferror);
                    $this->load->view('staffmgmt/staffprofile');
                      
                }
                else { 
                    $upload_data=$this->upload->data();
                    $msgphoto=" and photo" ;
                } 
            }//check for empphoto
            
            $upempdata_flag=$this->sismodel->updaterec('employee_master', $data,'emp_id',$id);
	    if(!empty($_POST['asignname'])){
                if($_POST['asignname'] == 'Others'){
                    $asignname=$_POST['asignname'].",".$_POST['asignother'];
                }
                else{
                    $asignname=$_POST['asignname'];
                }
            }
            else{
                $asignname=NULL;
            }
            
            
            /* update record in  additional assignments */
             $dupcheck = array(
                'aa_empid'     =>$id,   
                'aa_asigname'  =>$asignname,
                'aa_place'    =>$_POST['asignplace']      
            ); 
            $emidexits= $this->sismodel->isduplicatemore('additional_assignments',$dupcheck);
            if(! $emidexits)
            {
                $dataasign=array(
               'aa_empid'              =>$id,
               'aa_asigname'           =>$asignname,
               'aa_asigperiodfrom'     =>$_POST['asigndatefrom'],
               'aa_asigperiodto'       =>$_POST['asigndateto'],
               'aa_place'              =>$_POST['asignplace'],
               'aa_creatorid'          =>$this->session->userdata('username'),
               'aa_creatordate'        =>date('y-m-d'),
               'aa_modifierid'         =>$this->session->userdata('username'),
               'aa_modifydate'         =>date('y-m-d'),
                        
            );
                /* insert record in  additional assignments */
                    $this->sismodel->insertrec('additional_assignments', $dataasign);
                    $this->logger->write_logmessage("insert", "data insert in additional_assignments table.");
                    $this->logger->write_dblogmessage("insert", "data insert in additional_assignments table." );
            }
            else{
                $dataasign=array(
               //'aa_empid'              =>$empid,
               'aa_asigname'           =>$asignname,
               'aa_asigperiodfrom'     =>$_POST['asigndatefrom'],
               'aa_asigperiodto'       =>$_POST['asigndateto'],
               'aa_place'              =>$_POST['asignplace'],
              // 'aa_creatorid'          =>$this->session->userdata('username'),
               //'aa_creatordate'        =>date('y-m-d'),
               'aa_modifierid'         =>$this->session->userdata('username'),
               'aa_modifydate'         =>date('y-m-d'),
                        
            );
                $this->sismodel->updaterec('additional_assignments', $dataasign,'aa_empid',$id);
                $this->logger->write_logmessage("insert", "data insert in additional_assignments table.");
                $this->logger->write_dblogmessage("insert", "data insert in additional_assignments table." );
            }
            
            $mess = 'Your Employee data updated Successfully.';
            $sub = 'Employee Profile Updated';
	    $secmail=$this->sismodel->get_listspfic1('employee_master','emp_secndemail','emp_email',$_POST['emailid'])->emp_secndemail;
                  if((!empty($secmail))&&($secmail != '')&&($secmail != null)){
                         $mails=$this->mailmodel->mailsnd($secmail,$sub,$mess,'');
                  }
            $this->mailmodel->mailsnd($_POST['emailid'],$sub,$mess,'');
		
            /* insert record in service details with check duplicate , if uo then update in uolist table  and if hod then update in hod list table */
            $desigcode=$this->commodel->get_listspfic1('designation','desig_code','desig_id',$_POST['designation'])->desig_code;
           // $shownap=$this->commodel->get_listspfic1('designation','desig_id','desig_name',$_POST['emppost'])->desig_id;
            $this->sismodel->insertsdetail($id,$_POST['campus'],$_POST['uocontrol'],$_POST['department'],$desigcode,
            $_POST['schemecode'],$_POST['ddo'],$_POST['group'],$_POST['payband'],'',$_POST['emppost'],'','','');

            if(!upempdata_flag){
                $this->logger->write_logmessage("error","Error in update staff profile ", "Error in staff profile record update" );
                $this->logger->write_dblogmessage("error","Error in update staff profile", "Error in staff profile record update");
                $this->session->set_flashdata('err_message','Error in updating staff profile - ', 'error');
                $this->load->view('staffmgmt/editempprofile', $data);
            }
            else{
                $this->roleid=$this->session->userdata('id_role');
                $this->logger->write_logmessage("update","update staff profile ", " Employee record updated successfully ");
                $this->logger->write_dblogmessage("update","staff profile", "Employee record updated successfully");
                $this->session->set_flashdata('success', 'Employee data' .$msgphoto." ".'updated Successfully......'." "."["." "."Employee PF NO:"." ".$_POST['empcode']." and "."EmailId:"." ".$_POST['emailid']." "."]");
                if($this->roleid == 4){
                    redirect('empmgmt/viewempprofile');
                }
                else{
                    redirect('staffmgmt/employeelist');
                }    
            }
            }//form true    
        }//closeissetform    
    }
    /****************************  END UPDATE DATA ****************************/
    
    /****************************  START stafftransfer ****************************/
    function stafftransfer(){ 
    
        //$this->usrlist=$this->sismodel->get_list('employee_master');;
        $this->orgcode=$this->commodel->get_listspfic1('org_profile','org_code','org_id',1)->org_code;
        $this->campus=$this->commodel->get_listspfic2('study_center','sc_id','sc_name','org_code',$this->orgcode);
        $this->uoc=$this->lgnmodel->get_list('authorities');
        $this->salgrd=$this->sismodel->get_list('salary_grade_master'); 
        //$this->desig= $this->commodel->get_listspfic2('designation','desig_id','desig_name');
        if(isset($_POST['stafftransfer'])){
            /* Form validation*/
            $this->form_validation->set_rules('registrarname','RegistrarName','trim|required|xss_clean|alpha_numeric_spaces');
            $this->form_validation->set_rules('designation','Designation','trim|required|xss_clean');
            $this->form_validation->set_rules('usono','universitysancofficerno','trim|xss_clean');
            $this->form_validation->set_rules('rcno','RcNo','trim|required|xss_clean');
            $this->form_validation->set_rules('subject','Subject','trim|required|xss_clean');
            $this->form_validation->set_rules('referenceno','ReferenceNo','trim|xss_clean');
            $this->form_validation->set_rules('employeetype','EmployeeType','trim|required|xss_clean');
            $this->form_validation->set_rules('empname','Employee Name','trim|required|xss_clean');
            $this->form_validation->set_rules('uocfrom','uocFrom','trim|required|xss_clean');
            $this->form_validation->set_rules('uocontrolto','uocontrolTo','trim|required|xss_clean');
            $this->form_validation->set_rules('deptfrom','DepartmentFrom','trim|required|xss_clean');
            $this->form_validation->set_rules('deptto','DepartmentTo','trim|required|xss_clean');
            $this->form_validation->set_rules('desigfrom','DesignationFrom','trim|required|xss_clean');
            $this->form_validation->set_rules('desigto','DesignationTo','trim|required|xss_clean');
            $this->form_validation->set_rules('postfrom','PostFrom','trim|required|xss_clean');
            $this->form_validation->set_rules('postto','PostTo','trim|required|xss_clean');
            $this->form_validation->set_rules('ttadetail','TTADetail','trim|xss_clean');
            $this->form_validation->set_rules('dateofrelief','Dateofrelief','trim|xss_clean');
            $this->form_validation->set_rules('expdoj','expecteddoj','trim|xss_clean');
            //$this->form_validation->set_rules('postto','PostTo','trim|required|xss_clean');
            $this->form_validation->set_rules('emailsentto','EmailSentto','trim|required|xss_clean');
            $this->form_validation->set_rules('schemfrom','Scheme Name From','trim|xss_clean');
            $this->form_validation->set_rules('schemto','Scheme Name To','trim|xss_clean');
            $this->form_validation->set_rules('emptypeto','Employee Type To','trim|xss_clean');
           
            if($this->form_validation->run() == FALSE){
                redirect('staffmgmt/stafftransfer');
            }
            else{
                $data = array(
                    'uit_registrarname'                => $this->input->post('registrarname'),
                    'uit_desig'                        => $this->input->post('designation'),
                    'uit_uso_no'                       => $this->input->post('usono'),
                    'uit_date'                         => date('y-m-d'),
                    'uit_rc_no'                        => $this->input->post('rcno'),
                    'uit_subject'                      => $this->input->post('subject'),
                
                    'uit_referenceno'                  => $this->input->post('referenceno'),
                    'uit_ordercontent'                 => $this->input->post('ordercontent'),
                    'uit_emptype'                      => $this->input->post('employeetype'),
                    'uit_uoc_from'                     => $this->input->post('uocfrom'),
                    'uit_workdept_from'                => $this->input->post('deptfrom'),
                    'uit_desig_from'                   => $this->input->post('desigfrom'),
                
                    'uit_staffname'                    => $this->input->post('empname'),
                    'uit_workingpost_from'             => $this->input->post('postfrom'),
                    'uit_scid_from'                    => $this->input->post('campusfrom'),
                    'uit_scid_to'                      => $this->input->post('campus'),
                    'uit_uoc_to'                       => $this->input->post('uocontrolto'),
                    'uit_dept_to'                      => $this->input->post('deptto'),
                    'uit_desig_to'                     => $this->input->post('desigto'),
                    'uit_post_to'                      => $this->input->post('postto'),
                    'uit_tta_detail'                   => $this->input->post('ttadetail'),
                
                    'uit_dateofrelief'                 => $this->input->post('dateofrelief'),
                    'uit_dateofjoining'                => $this->input->post('expdoj'),
                    'uit_email_sentto'                 => $this->input->post('emailsentto'),
                    'uit_emptypeto'                    => $this->input->post('emptypeto'),
                    'uit_schm_from'                    => $this->input->post('schemfrom'),
                    'uit_schm_to'                      => $this->input->post('schemto'),
                    'uit_ddoid_to'                     => $this->input->post('ddo'),
                    'uit_group_to'                     => $this->input->post('group'),
                    'uit_paybandid_to'                 => $this->input->post('payband'),
                    'uit_vacanttype_to'                => $this->input->post('vacanttype'),
                
                );  
                
                $usrinputtfr_flag=$this->sismodel->insertrec('user_input_transfer', $data);
               
                /* write code for update staff_position table and staff_position_archive.*/
                if(!$usrinputtfr_flag){
                    $this->logger->write_logmessage("error","Error in Staff Transfer and Posting", "Error in Staff Transfer and Posting" );
                    $this->logger->write_dblogmessage("error","Error in Staff Transfer and Posting", "Error in Staff Transfer and Posting");
                    $this->session->set_flashdata('err_message','Error in Staff Transfer and Posting - ', 'error');
                    $this->load->view('staffmgmt/stafftransfer', $data);
                }
                else{
                    /* insert record in service details with check duplicate and  update in empprofile table table */
                    $id=$_POST['empname'];
                    $postto=$this->commodel->get_listspfic1('designation','desig_name','desig_id',$_POST['postto'])->desig_name;
                    $empdata = array(
                        'emp_dept_code'    => $_POST['deptto'],
                        'emp_desig_code'   => $_POST['desigto'],
                        'emp_post'         => $postto,
                        'emp_worktype'     => $_POST['emptypeto'],
                        'emp_salary_grade' => $_POST['payband'],
                        'emp_schemeid'     => $_POST['schemto'],
                        'emp_scid'         => $_POST['campus'] ,
                        'emp_uocid'        => $_POST['uocontrolto'],
                        'emp_uocuserid'    => $_POST['uocontrolto'],
                        'emp_ddouserid'    => $_POST['ddo'],
                        'emp_ddoid'        => $_POST['ddo'],
                        'emp_group'        => $_POST['group'],
                    
                    );
                    $upempdata_flag=$this->sismodel->updaterec('employee_master', $empdata,'emp_id',$id);
                    /****************************************insert record in service particular************************************************/
                    $desigcode=$this->commodel->get_listspfic1('designation','desig_code','desig_id',$_POST['desigto'])->desig_code;
                    // $shownap=$this->commodel->get_listspfic1('designation','desig_id','desig_name',$_POST['emppost'])->desig_id;
                    $this->sismodel->insertsdetail($id,$_POST['campus'],$_POST['uocontrolto'],$_POST['deptto'],$desigcode,
                    $_POST['schemto'],$_POST['ddo'],$_POST['group'],$_POST['payband'],'',$_POST['postto'],'','','');
                       
                    /*************************************updating the staff position table******************************************************************/
                    $postfrom=$this->commodel->get_listspfic1('designation','desig_id','desig_name',$_POST['postfrom'])->desig_id;
                    //descrease position and increase vacancy from old data(means from )
                    $this->sismodel->updatestaffposition2($_POST['campusfrom'],$_POST['uocfrom'],$_POST['deptfrom'],$postfrom,$_POST['employeetype'],$_POST['empptfrom'],$_POST['schemfrom']);
                    //increase in position and decrease vacancy from new data(means to)
                    $this->sismodel->updatestaffposition($_POST['campus'],$_POST['uocontrolto'], $_POST['deptto'],$_POST['postto'],$_POST['emptypeto'],$_POST['vacanttype']) ;
                   
                    /*************************************close updating the staff position table************************************************/
                    $emppfno=$this->sismodel->get_listspfic1('employee_master', 'emp_code', 'emp_id', $_POST['empname'])->emp_code;
                    $empname=$this->sismodel->get_listspfic1('employee_master', 'emp_name', 'emp_id', $_POST['empname'])->emp_name;
                    $deptto=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$_POST['deptto'])->dept_name; 
                    $this->orgname=$this->commodel->get_listspfic1('org_profile','org_name','org_id',1)->org_name;
                   // $this->regname=$this->sismodel->get_listspfic1('user_input_transfer','uit_registrarname','uit_staffname',$id)->uit_registrarname;
                    //$this->uitdesig=$this->sismodel->get_listspfic1('user_input_transfer','uit_desig','uit_staffname',$id)->uit_desig;
                    $this->regname=$this->input->post('registrarname');
                    $this->uitdesig=$this->input->post('designation');
                    $mail_sent_to=$_POST['emailsentto'];
                   // $mailarray=explode(',',$mail_sent_to);
                    //if sucess send mail to user with transfer order copy 
                    $sub='Employee Transfer And Posting - Letter  ' ;
                    $mess='OFFICE ORDER<br/> Dear'.$empname.'This is to inform you that you will be transferred at'.$deptto.'with immediate effect.<br/>
                    Please find the attachment of transfer order copy<br/> Wish you all the best<br/>'.$this->orgname.'<br/>
                    '.$this->regname.'<br/>'.$this->uitdesig;
                    $attachment=$this->sismodel->gentransferordertpdf($_POST['empname']);
                    $this->mailstoperson =$this->mailmodel->mailsnd($mail_sent_to, $sub, $mess,$attachment,'');
                   // $this->mailstoperson =$this->mailmodel->mailsnd('$mail_sent_to', $sub, $mess,'','Sis');
                    if($this->mailstoperson){
                        //echo "in if part mail";
                        $mailmsg='Transfer and Promotion order ....Mail send successfully';
                        $this->logger->write_logmessage("insert"," Transfer and Promotion order ",'mail send successfully  to '.$_POST['emailid'] );
                        $this->logger->write_dblogmessage("insert"," Transfer and Promotion order",'mail send successfully  to '.$_POST['emailid'] );
                    }
                    else{
                        //echo "in else part";
                        $mailmsg='Mail does not sent';
                        $this->logger->write_logmessage("insert"," Transfer and Promotion order", "Mail does not sent to ".$_POST['emailid']);
                        $this->logger->write_dblogmessage("insert"," Transfer and Promotion order", "Mail does not sent to ".$_POST['emailid']);
                    }
                    $this->logger->write_logmessage("insert","Staff Transfer and Posting", " Employee transfer record insert successfully ");
                    $this->logger->write_dblogmessage("update","Staff Transfer and Posting", "Employee transfer record insert successfully");
                    $this->session->set_flashdata('success', 'Employee transfer record insert successfully ......'." "."["." "."Employee PF NO:"." ".$emppfno." and "."Employee Name:"." ".$empname." "."]");
                    redirect('staffmgmt/stafftransferlist');
                }//elseof form validation
            }//else    
        }//ifpost
        $this->load->view('staffmgmt/stafftransfer');
       
    }
   
   /****************************  END stafftransfer ****************************/

    
    
    /* This function has been created for get employee detail on the basis of  selected employee name */
    public function getempdetail(){
        $emp= $this->input->post('employee');
        $emp_data=$this->sismodel->get_listrow('employee_master','emp_id',$emp);
        $empdetail = $emp_data->result();
        if(count($empdetail)>0){
            foreach($empdetail as $detail){
                //$uocname=$this->lgnmodel->get_listspfic1('authorities', 'name', 'id',$detail->emp_uocid)->name;
                //$deptname=$this->commodel->get_listspfic1('Department', 'dept_name', 'dept_id',$detail->emp_dept_code)->dept_name;
                //$designame=$this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id',$detail->emp_desig_code)->desig_name;
            //    $values='uocfrom='. $detail->emp_uocid.', deptfrom=' .$detail->emp_dept_code.',desigfrom='. $detail->emp_desig_code.',postfrom='.$detail->emp_post;
                //$values=$uocname.',' .$deptname.','. $designame.','.$detail->emp_post;
                $values=$detail->emp_post.",".$detail->emp_type_code;
                     
            }
            /*$scid=$this->sismodel->get_listspfic1('employee_master', 'emp_scid', 'emp_id',$emp)->emp_scid;
            $deptcode=$this->commodel->get_listspfic1('study_center', 'sc_code', 'sc_id', $scid)->sc_code;
            $resultsc = $this->commodel->get_listrow('Department','dept_sccode', $deptcode);
            $dept_data = $resultsc->result();
            if(count($dept_data)>0){
                $dept_select_box = '';
                $dept_select_box.= '<option value="">---------------------- Select Department -------------------';
                foreach($dept_data as $dept){
                        $dept_select_box.='<option value='.$dept->dept_id.'>'.$dept->dept_name;
                }
            }  */  
            //echo json_encode($values.",".$dept_select_box);
            echo json_encode($values);
                       
        }            
    
    }
    
    /* Display Employee Tansfer record */

    public function stafftransferlist(){

	$data['records'] = $this->sismodel->get_list('user_input_transfer');
        $this->logger->write_logmessage("view"," view staff tansfer and posting list" );
        $this->logger->write_dblogmessage("view"," view staff tansfer and posting list");
        $this->load->view('staffmgmt/stafftransferlist',$data);
    }
    
    /**
    * Get Download PDF File for transfer order copy
    * @return Response
    */
    public function transferordercopy($id){
        $this->orgname=$this->commodel->get_listspfic1('org_profile','org_name','org_id',1)->org_name;
        $this->orgaddres=$this->commodel->get_listspfic1('org_profile','org_address1','org_id',1)->org_address1;
        $this->orgpincode=$this->commodel->get_listspfic1('org_profile','org_pincode','org_id',1)->org_pincode;
        $this->regname=$this->sismodel->get_listspfic1('user_input_transfer','uit_registrarname','uit_id',$id)->uit_registrarname;
        $this->uitdesig=$this->sismodel->get_listspfic1('user_input_transfer','uit_desig','uit_id',$id)->uit_desig;
        $this->data=$this->sismodel->get_listrow('user_input_transfer','uit_id',$id);
        $spec_data['detail'] = $this->data->row();
        $this->load->library('pdf');
        $this->pdf->set_paper("A4", "portrait");
        $this->pdf->load_view('staffmgmt/transferordercopy',$spec_data);
        $this->pdf->render();
        $this->pdf->stream("transferorder.pdf");
        
    }

//====================Staff Position===================================

  /*this function has been created for display the record of staff position */
  public function staffposition(){
        $whdata = '';
	//  get role id and user id
	$rlid=$this->session->userdata('id_role');
        if ($rlid == 5){
                $usrid=$this->session->userdata('id_user');
		$deptid = '';
		$whdatad = array('userid' => $usrid,'roleid' => $rlid);
        	$resu = $this->sismodel->get_listspficemore('user_role_type','deptid',$whdatad);
                foreach($resu as $rw){
                        $deptid=$rw->deptid;
                }
                $whdata = array ('sp_dept' => $deptid);
                //array_push($whdata,'sp_dept' => $deptid);
        }
        if ($rlid == 10){
		// get uo authid
		// default is null and for VC,  R also null but others uo pass it in filter
                $usrname=$this->session->userdata('username');
//              print_r( $usrname); die;
                if(($usrname === 'vc@tanuvas.org.in')||($usrname === 'registrar@tanuvas.org.in')){
                }else{
                      $uoid=$this->lgnmodel->get_listspfic1('authorities','id','authority_email',$usrname)->id;
                      $whdata = array ('sp_uo' => $uoid);
                }
        }
        //print_r($whdata); die;
	 $selectfield ="sp_emppost,sp_campusid,sp_uo,sp_dept,sp_schemecode,sp_tnt,sp_type,sp_emppost,sp_scale,sp_methodRect,sp_sancstrenght,sp_position,sp_vacant,sp_id";
         $whorder = "sp_dept asc,sp_emppost asc";
        //$whdata = array('sp_uo'=> $uo);
         if(isset($_POST['filter'])) {
            //echo "ifcase post of filter";
            $wtype = $this->input->post('wtype');
            $post  = $this->input->post('post');
            if(!empty($post) && (!empty($deptid))){
		if($post != 'All'){
                	$whdata['sp_tnt']= $wtype;
                	$whdata['sp_emppost'] =$post;
		        if ($rlid != 5){
                		$whdata['sp_dept'] = $deptid;
			}
		}
		else{
                	$whdata['sp_tnt']= $wtype;
		        if ($rlid != 5){
                		$whdata['sp_dept'] = $deptid;
			}
		}

            }
	    elseif (!empty($post)){
		if($post != 'All'){
			$whdata['sp_tnt']= $wtype;
			$whdata['sp_emppost'] =$post;
		}
		else{
			$whdata['sp_tnt']= $wtype;
		}
	    }
		$this->wtyp = $wtype;
                $this->desigm = $post;	
 	 	$data['records'] = $this->sismodel->get_orderlistspficemore('staff_position',$selectfield, $whdata,$whorder);
         }
         else{
         	$data['records']=$this->sismodel->get_orderlistspficemore('staff_position',$selectfield,$whdata,$whorder);
         }

	//$this->result = $this->sismodel->get_orderlistspficemore('staff_position','*',$whdata,'sp_dept asc,sp_emppost asc');
        $this->logger->write_logmessage("view"," View staff position ", "Staff position details...");
        $this->logger->write_dblogmessage("view"," View staff position ", "Staff position details...");
        $this->load->view('staffmgmt/staffposition',$data);
  }

  /*this function has been created for add new staff Position record */
  public function newstaffposition(){
        $this->scresult = $this->commodel->get_listspfic2('study_center','sc_id,sc_code', 'sc_name');
        $this->desigresult = $this->commodel->get_listspfic2('designation','desig_id', 'desig_name');
        $this->authorty = $this->lgnmodel->get_list('authorities', 'id', 'name');
        $this->salgrd=$this->sismodel->get_list('salary_grade_master');

        if(isset($_POST['newstaffposition'])) {
                $this->form_validation->set_rules('campus','Campus Name','xss_clean|required');
                $this->form_validation->set_rules('uo','U O Control','xss_clean|required');
                $this->form_validation->set_rules('dept','Department Name','xss_clean|required');
                $this->form_validation->set_rules('schemecode','Scheme Name','xss_clean|required');
                $this->form_validation->set_rules('pnp','Plan / Non Plan','xss_clean|required');
                $this->form_validation->set_rules('group','Group','xss_clean|required');
                $this->form_validation->set_rules('tnt','Teaching non teaching ','xss_clean|required');
                $this->form_validation->set_rules('type','Employee Type','xss_clean|required');
                $this->form_validation->set_rules('emppost','Employee Post','xss_clean|required');
                $this->form_validation->set_rules('grouppost','Group Post','xss_clean|required');
                $this->form_validation->set_rules('scale','Grade Pay','xss_clean|required');
                $this->form_validation->set_rules('methodrect','Method of Recruitment','xss_clean|required');
                $this->form_validation->set_rules('ss','Position Sanction Strength','trim|xss_clean|required|numeric');
                $this->form_validation->set_rules('p','Position Present','trim|xss_clean|required|numeric');
                $this->form_validation->set_rules('v','Position Vacant','trim|xss_clean|required|numeric');
                $this->form_validation->set_rules('ssper','Sanction Strength Permanent','trim|xss_clean|required|numeric');
                $this->form_validation->set_rules('pper','Position Permanent','trim|xss_clean|required|numeric');
                $this->form_validation->set_rules('vper','Vacancy Permanent','trim|xss_clean|required|numeric');
                $this->form_validation->set_rules('sstem','Sanction Strength Temporary','trim|xss_clean|required|numeric');
                $this->form_validation->set_rules('ptem','Position Temporary ','trim|xss_clean|required|numeric');
                $this->form_validation->set_rules('vtem','Vacancy Temporary','trim|xss_clean|required|numeric');
                $this->form_validation->set_rules('address1','Address','xss_clean');
                $this->form_validation->set_rules('ssdetail','Sanction Strength Detail','xss_clean');
                $this->form_validation->set_rules('remarks','Remarks','xss_clean');

       if($this->form_validation->run()==TRUE){

        $sptnt= $this->input->post("tnt");
        $spemppostid = $this->input->post("emppost");
        $spemppost = $this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id', $spemppostid)->desig_name ;
        $spss = $this->input->post("ss");
        $spp = $this->input->post("p");
        $spv = $this->input->post("v");
        $spssper = $this->input->post("ssper");
        $sppper = $this->input->post("pper");
        $spvper = $this->input->post("vper");
        $spsstem = $this->input->post("sstem");
        $spptem = $this->input->post("ptem");
        $spvtem = $this->input->post("vtem");

	if($spss == 0 ) {
                $this->session->set_flashdata('err_message','The value of Position Sanction Strength should be greater than zero .');
                $this->load->view('staffmgmt/newstaffposition');
		return false; 
		}

	if($spss != $spp+$spv) {
                $this->session->set_flashdata('err_message','The value of Position Sanction Strength is not equals to sum of Position Present and Position Vacant .');
                $this->load->view('staffmgmt/newstaffposition');
		return false; 
		}

	if($spssper != $sppper+$spvper) {
                $this->session->set_flashdata('err_message','The value of Sanction Strength Permanent is not equals to sum of Position Permanent and Vacancy Permanent .');
                $this->load->view('staffmgmt/newstaffposition');
		return false; 
		}
	if($spsstem != $spptem+$spvtem){
                $this->session->set_flashdata('err_message','The value of Sanction Strength Temporary is not equals to sum of Position Temporary and Vacancy Temporary .');
                $this->load->view('staffmgmt/newstaffposition');
		return false; 
		}
	if($spss != $spssper+$spsstem){
                $this->session->set_flashdata('err_message','The value of Position Sanction Strength is not equals to sum of Sanction Strength Permanent and Sanction Strength Temporary .');
                $this->load->view('staffmgmt/newstaffposition');
		return false; 
		}
	if($spp != $sppper+$spptem){
                $this->session->set_flashdata('err_message','The value of Position Present is not equals to sum of Position Permanent and Position Temporary .');
                $this->load->view('staffmgmt/newstaffposition');
		return false; 
		}
	if($spv != $spvper+$spvtem){
                $this->session->set_flashdata('err_message','The value of Position Vacant is not equals to sum of Vacancy Permanent and Vacancy Temporary .');
                $this->load->view('staffmgmt/newstaffposition');
		return false; 
		}


        $datadupposition = array('sp_tnt'=>$_POST['tnt'], 'sp_type'=>$_POST['type'], 'sp_emppost'=>$_POST['emppost'], 'sp_grppost'=>$_POST['grouppost'], 'sp_scale'=>$_POST['scale'], 'sp_methodRect'=>$_POST['methodrect'], 'sp_group'=>$_POST['group'], 'sp_uo'=>$_POST['uo'], 'sp_dept'=>$_POST['dept'], 'sp_campusid'=>$_POST['campus'], 'sp_plan_nonplan'=>$_POST['pnp'], 'sp_schemecode'=>$_POST['schemecode'],
        'sp_org_id'=> '1' );

        $dataposition = array(
        'sp_tnt'=>$_POST['tnt'],
        'sp_type'=>$_POST['type'],
        'sp_emppost'=>$_POST['emppost'],
        'sp_grppost'=>$_POST['grouppost'],
        'sp_scale'=>$_POST['scale'],
        'sp_methodRect'=>$_POST['methodrect'],
        'sp_group'=>$_POST['group'],
        'sp_uo'=>$_POST['uo'],
        'sp_dept'=>$_POST['dept'],
        'sp_address1'=>$_POST['address1'],
        'sp_address2'=>'Null',
        'sp_address3'=>'Null',
        'sp_campusid'=>$_POST['campus'],
        'sp_per_temporary'=>'Null',
        'sp_plan_nonplan'=>$_POST['pnp'],
        'sp_schemecode'=>$_POST['schemecode'],
        'sp_sancstrenght'=>$_POST['ss'],
        'sp_position'=>$_POST['p'],
        'sp_vacant'=>$_POST['v'],
        'sp_remarks'=>$_POST['remarks'],
        'sp_ssdetail'=>$_POST['ssdetail'],
        'sp_sspermanent'=>$_POST['ssper'],
        'sp_sstemporary'=>$_POST['sstem'],
        'sp_pospermanent'=>$_POST['pper'],
        'sp_postemporary'=>$_POST['ptem'],
        'sp_vpermanenet'=>$_POST['vper'],
        'sp_vtemporary'=>$_POST['vtem'],
        'sp_org_id'=> '1'
        );

	$duppositionflag = $this->sismodel->isduplicatemore('staff_position', $datadupposition) ;
	if($duppositionflag == 1)
	{
                $this->session->set_flashdata("err_message", "Record is already exist with this combination......... ");
                redirect('staffmgmt/newstaffposition');
                return;
	}
    else{
	$positionflag = $this->sismodel->insertrec('staff_position', $dataposition) ;
        if(!$positionflag)
        {
                $this->logger->write_logmessage("insert"," Error in adding Staff Position ", " Data insert error .'Teaching /Non Teaching :' = $sptnt , 'Employee_Post' = $spemppost "  );
                $this->logger->write_dblogmessage("insert"," Error in adding Staff Position ", " Data insert error .'Teaching /Non Teaching :' = $sptnt , 'Employee_Post' = $spemppost " );
                $this->session->set_flashdata('err_message','Error in adding Staff Position - ' .  '.', 'error');
                $this->load->view('staffmgmt/newstaffposition');
        }
        else{
                $this->logger->write_logmessage("insert"," Staff Position ", "Record added successfully. 'Teaching /Non Teaching :' = $sptnt , 'Employee_Post :' = $spemppost  " );
                $this->logger->write_dblogmessage("insert"," Staff Position ", "Record added successfully. 'Teaching /Non Teaching :' = $sptnt, 'Employee_Post :' = $spemppost  " );
                $this->session->set_flashdata("success", "Record added successfully...'Teaching /Non Teaching :' = $sptnt, 'Employee_Post : ' = $spemppost ");
                redirect('staffmgmt/staffposition');
        }
      }
    }
   }
   $this->load->view('staffmgmt/newstaffposition');
   }

  /*This function has been created for update the staff position record */
  public function editstaffposition($sp_id){
        $this->authorty = $this->lgnmodel->get_list('authorities', 'id', 'name');
        $sp_data_q=$this->sismodel->get_listrow('staff_position','sp_id', $sp_id);
        if ($sp_data_q->num_rows() < 1)
        {
           redirect('staffmgmt/editstaffposition');
        }
        $editsp_data = $sp_data_q->row();

        /* Form fields */

        $data['tnt']= array('name' => 'tnt', 'id' => 'tnt', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_tnt, 'readonly' => 'readonly' );

        $data['type']= array('name' => 'type', 'id' => 'type', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_type, 'readonly' => 'readonly' );

        $data['emppost'] = array('name' => 'emppost', 'id' => 'emppost', 'maxlength' => '40', 'size' => '30', 'value' => $this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id', $editsp_data->sp_emppost)->desig_name, 'readonly' => 'readonly' );

        $data['grouppost'] = array('name' => 'grouppost', 'id' => 'grouppost', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_grppost, 'readonly' => 'readonly' );

        $data['scale'] = array('name' => 'scale', 'id' => 'scale', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_scale, 'readonly' => 'readonly' );

        $data['methodrect'] = array('name' => 'methodrect', 'id' => 'methodrect', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_methodRect, 'readonly' => 'readonly' );

        $data['group'] = array('name' => 'group', 'id' => 'group', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_group, 'readonly' => 'readonly' );

        $data['uo'] = array('name' => 'uo', 'id' => 'uo', 'maxlength' => '40', 'size' => '30', 'value' => $this->lgnmodel->get_listspfic1('authorities', 'name', 'id', $editsp_data->sp_uo)->name, 'readonly' => 'readonly' );

        $data['dept'] = array('name' => 'dept', 'id' => 'teachername', 'maxlength' => '40', 'size' => '30', 'value' => $this->commodel->get_listspfic1('Department', 'dept_name', 'dept_id', $editsp_data->sp_dept)->dept_name, 'readonly' => 'readonly');

        $data['address1'] = array('name' => 'address1', 'id' => 'address1', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_address1, );

        $data['campus'] = array('name' => 'campus', 'id' => 'campus', 'maxlength' => '40', 'size' => '30', 'value' => $this->commodel->get_listspfic1('study_center', 'sc_name', 'sc_id', $editsp_data->sp_campusid)->sc_name, 'readonly' => 'readonly' );

        $data['pnp'] = array('name' => 'pnp', 'id' => 'pnp', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_plan_nonplan, 'readonly' => 'readonly' );

        $data['schemecode'] = array('name' => 'schemecode', 'id' => 'schemecode', 'maxlength' => '40', 'size' => '30', 'value' => $this->sismodel->get_listspfic1('scheme_department', 'sd_name', 'sd_id', $editsp_data->sp_schemecode)->sd_name, 'readonly' => 'readonly' );

        $data['ss'] = array('name' => 'ss', 'id' => 'ss', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_sancstrenght, );

        $data['p'] = array('name' => 'p', 'id' => 'p', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_position, 'readonly' => 'readonly' );

        $data['v'] = array('name' => 'v', 'id' => 'v', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_vacant, 'readonly' => 'readonly' );

        $data['remarks'] = array('name' => 'remarks', 'id' => 'remarks', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_remarks, );

        $data['ssdetail'] = array('name' => 'ssdetail', 'id' => 'ssdetail', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_ssdetail, );

        $data['ssper'] = array('name' => 'ssper', 'id' => 'ssper', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_sspermanent, 'readonly' => 'readonly' );

        $data['sstem'] = array('name' => 'sstem', 'id' => 'sstem', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_sstemporary, 'readonly' => 'readonly' );

        $data['pper'] = array('name' => 'pper', 'id' => 'pper', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_pospermanent, 'readonly' => 'readonly' );

        $data['ptem'] = array('name' => 'ptem', 'id' => 'ptem', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_postemporary, 'readonly' => 'readonly' );

        $data['vper'] = array('name' => 'vper', 'id' => 'vper', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_vpermanenet, 'readonly' => 'readonly' );

        $data['vtem'] = array('name' => 'vtem', 'id' => 'vtem', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_vtemporary, 'readonly' => 'readonly' );

        $data['sp_id'] = $sp_id;

                $this->form_validation->set_rules('campus','Campus Name','xss_clean|required');
                $this->form_validation->set_rules('uo','U O Control','xss_clean|required');
                $this->form_validation->set_rules('dept','Department Name','xss_clean|required');
                $this->form_validation->set_rules('schemecode','Scheme Name','xss_clean|required');
                $this->form_validation->set_rules('pnp','Plan / Non Plan','xss_clean|required');
                $this->form_validation->set_rules('group','Group','xss_clean|required');
                $this->form_validation->set_rules('tnt','Teaching non teaching ','xss_clean|required');
                $this->form_validation->set_rules('type','Employee Type','xss_clean|required');
                $this->form_validation->set_rules('emppost','Employee Post','xss_clean|required');
                $this->form_validation->set_rules('grouppost','Group Post','xss_clean|required');
                $this->form_validation->set_rules('scale','Grade Pay','xss_clean|required');
                $this->form_validation->set_rules('methodrect','Method of Recruitment','xss_clean|required');
                $this->form_validation->set_rules('ss','Position Sanction Strength','trim|xss_clean|required|numeric');
                $this->form_validation->set_rules('p','Position Present','trim|xss_clean|required|numeric');
                $this->form_validation->set_rules('v','Position Vacant','trim|xss_clean|required|numeric');
                $this->form_validation->set_rules('ssper','Sanction Strength Permanent','trim|xss_clean|required|numeric');
                $this->form_validation->set_rules('pper','Position Permanent','trim|xss_clean|required|numeric');
                $this->form_validation->set_rules('vper','Vacancy Permanent','trim|xss_clean|required|numeric');
                $this->form_validation->set_rules('sstem','Sanction Strength Temporary','trim|xss_clean|required|numeric');
                $this->form_validation->set_rules('ptem','Position Temporary ','trim|xss_clean|required|numeric');
                $this->form_validation->set_rules('vtem','Vacancy Temporary','trim|xss_clean|required|numeric');
                $this->form_validation->set_rules('address1','Address','xss_clean');
                $this->form_validation->set_rules('ssdetail','Sanction Strength Detail','xss_clean');
                $this->form_validation->set_rules('remarks','Remarks','xss_clean');
        if ($this->form_validation->run() == TRUE)
	{
                $tnt = $this->input->post('tnt', TRUE);
                $type = $this->input->post('type', TRUE);
                $emppost = $this->input->post('emppost', TRUE);
                $grppost = $this->input->post('grouppost', TRUE);
                $scale = $this->input->post('scale', TRUE);
                $methodRect = $this->input->post('methodrect', TRUE);
                $group = $this->input->post('group', TRUE);
                $uo = $this->input->post('uo', TRUE);
                $dept = $this->input->post('dept', TRUE);
                $address = $this->input->post('address1', TRUE);
                $campusid = $this->input->post('campus', TRUE);
                $plannonplan = $this->input->post('pnp', TRUE);
                $schemecode = $this->input->post('schemecode', TRUE);
                $sancstrenght = $this->input->post('ss', TRUE);
                $position = $this->input->post('p', TRUE);
                $vacant = $this->input->post('v', TRUE);
                $remarks = $this->input->post('remarks', TRUE);
                $ssdetail = $this->input->post('ssdetail', TRUE);
                $sspermanent = $this->input->post('ssper', TRUE);
                $sstemporary = $this->input->post('sstem', TRUE);
                $pospermanent = $this->input->post('pper', TRUE);
                $postemporary = $this->input->post('ptem', TRUE);
                $vpermanenet = $this->input->post('vper', TRUE);
                $vtemporary = $this->input->post('vtem', TRUE);
		
	if($sancstrenght != $position+$vacant) {
                $this->session->set_flashdata('err_message','The value of Position Sanction Strength is not equals to sum of Position Present and Position Vacant .');
                $this->load->view('staffmgmt/editstaffposition', $data);
		return false; 
		}

	if($sspermanent != $pospermanent+$vpermanenet) {
                $this->session->set_flashdata('err_message','The value of Sanction Strength Permanent is not equals to sum of Position Permanent and Vacancy Permanent .');
                $this->load->view('staffmgmt/editstaffposition', $data);
		return false; 
		}
	if($sstemporary != $postemporary+$vtemporary){
                $this->session->set_flashdata('err_message','The value of Sanction Strength Temporary is not equals to sum of Position Temporary and Vacancy Temporary .');
                $this->load->view('staffmgmt/editstaffposition', $data);
		return false; 
		}
	if($sancstrenght != $sspermanent+$sstemporary){
                $this->session->set_flashdata('err_message','The value of Position Sanction Strength is not equals to sum of Sanction Strength Permanent and Sanction Strength Temporary .');
                $this->load->view('staffmgmt/editstaffposition', $data);
		return false; 
		}
	if($position != $pospermanent+$postemporary){
                $this->session->set_flashdata('err_message','The value of Position Present is not equals to sum of Position Permanent and Position Temporary .');
                $this->load->view('staffmgmt/editstaffposition', $data);
		return false; 
		}
	if($vacant != $vpermanenet+$vtemporary){
                $this->session->set_flashdata('err_message','The value of Position Vacant is not equals to sum of Vacancy Permanent and Vacancy Temporary .');
                $this->load->view('staffmgmt/editstaffposition', $data);
		return false; 
		}

        $instdataspa = array(
	'spa_spid'=>$sp_id,
        'spa_tnt'=> $editsp_data->sp_tnt,
        'spa_type'=> $editsp_data->sp_type,
        'spa_emppost'=> $editsp_data->sp_emppost,
        'spa_grppost'=> $editsp_data->sp_grppost,
        'spa_scale'=>$editsp_data->sp_scale,
        'spa_methodRect'=>$editsp_data->sp_methodRect,
        'spa_group'=>$editsp_data->sp_group,
        'spa_uo'=>$editsp_data->sp_uo,
        'spa_dept'=>$editsp_data->sp_dept,
        'spa_address1'=>$editsp_data->sp_address1,
        'spa_address2'=>'Null',
        'spa_address3'=>'Null',
        'spa_campusid'=>$editsp_data->sp_campusid,
        'spa_per_temporary'=>'Null',
        'spa_plan_nonplan'=>$editsp_data->sp_plan_nonplan,
        'spa_schemecode'=>$editsp_data->sp_schemecode,
        'spa_sancstrenght'=>$editsp_data->sp_sancstrenght,
        'spa_position'=>$editsp_data->sp_position,
        'spa_vacant'=>$editsp_data->sp_vacant,
        'spa_remarks'=>$editsp_data->sp_remarks,
        'spa_ssdetail'=>$editsp_data->sp_ssdetail,
        'spa_sspermanent'=>$editsp_data->sp_sspermanent,
        'spa_sstemporary'=>$editsp_data->sp_sstemporary,
        'spa_pospermanent'=>$editsp_data->sp_pospermanent,
        'spa_postemporary'=>$editsp_data->sp_postemporary,
        'spa_vpermanenet'=>$editsp_data->sp_vpermanenet,
        'spa_vtemporary'=>$editsp_data->sp_vtemporary,
        'spa_org_id'=> '1',
	'spa_archuserid'=>$this->session->userdata('id_user'),
	'spa_archdate'=>date('y-m-d')
        );

        $spflag=$this->sismodel->insertrec('staff_position_archive', $instdataspa);
        if(!$spflag)
         {
              $this->logger->write_dblogmessage("error","Error in insert staff position archive ", "Error in  staff position archive record insert" .$sp_id );
         }else{
              $this->logger->write_dblogmessage("insert","Insert staff position archive", "Record inserted in staff position archive successfully.." .$sp_id );
         }

      $update_data = array(
                'sp_tnt'=> $tnt,
                'sp_type'=> $type,
                'sp_emppost'=> $this->commodel->get_listspfic1('designation', 'desig_id', 'desig_name', $emppost)->desig_id ,
                'sp_grppost'=> $grppost,
                'sp_scale'=> $scale,
                'sp_methodRect'=> $methodRect,
                'sp_group'=>$group,
                'sp_uo'=> $this->lgnmodel->get_listspfic1('authorities', 'id', 'name', $uo)->id,
                'sp_dept'=> $this->commodel->get_listspfic1('Department', 'dept_id', 'dept_name', $dept)->dept_id, 
                'sp_address1'=>$address,
                'sp_address2'=>'Null',
                'sp_address3'=>'Null',
                'sp_campusid'=> $this->commodel->get_listspfic1('study_center', 'sc_id', 'sc_name', $campusid)->sc_id,
                'sp_per_temporary'=>'Null',
                'sp_plan_nonplan'=>$plannonplan,
                'sp_schemecode'=> $this->sismodel->get_listspfic1('scheme_department', 'sd_id', 'sd_name', $schemecode)->sd_id, 
                'sp_sancstrenght'=>$sancstrenght,
                'sp_position'=>$position,
                'sp_vacant'=>$vacant,
                'sp_remarks'=>$remarks,
                'sp_ssdetail'=>$ssdetail,
                'sp_sspermanent'=>$sspermanent,
                'sp_sstemporary'=>$sstemporary,
                'sp_pospermanent'=>$pospermanent,
                'sp_postemporary'=>$postemporary,
                'sp_vpermanenet'=>$vpermanenet,
                'sp_vtemporary'=>$vtemporary,
                'sp_org_id'=> '1'
            );

        $spflag=$this->sismodel->updaterec('staff_position', $update_data, 'sp_id', $sp_id);
        if(!$spflag)
            {
                $this->logger->write_logmessage("error","Error in updating Staff Position ", "Error in record updating. " );
                $this->logger->write_dblogmessage("error","Error in updating Staff Position ", "Error in record updating. " );
                $this->session->set_flashdata('err_message','Error in updating Staff Position ' . '.', 'error');
                $this->load->view('staffmgmt/editstaffposition', $data);
            }
            else{
                $this->logger->write_logmessage("update","Edit Staff Position", " Record updated successfully. " );
                $this->logger->write_dblogmessage("update","Edit Staff Position", "Record updated successfully. " );
                $this->session->set_flashdata('success',"Record updated successfully....." );
                redirect('staffmgmt/staffposition/');
          } 
	}
        $this->load->view('staffmgmt/editstaffposition', $data);
    }

 /* This function has been created for get list of schemes on the basis of  selected department */
    public function getdeptscheme(){
        $deptid = $this->input->post('deptid');
       // echo json_encode("this is testing----".$dept);
        $this->depmodel->get_deptschemelist($deptid);
    }

 /* This function has been created for get list of group post on the basis of  selected working type */
    public function getworkingtype(){
        $wtype = $this->input->post('groupp');
       // echo json_encode("this is testing----".$wtype);
	if ($wtype=='Teaching'){
            $uco_select_box ='';
            $uco_select_box.='<option value=>-------select Group Post--------';
            $uco_select_box.='<option value=UO> UO';
            $uco_select_box.='<option value=Professor> Professor';
            $uco_select_box.='<option value=AssociateProfessor> Associate Professor';
            $uco_select_box.='<option value=AssistantProfessor> Assistant Professor';
            $uco_select_box.='<option value=Librarians> Librarians';
            $uco_select_box.='<option value=PhysicalDirector> Physical Director';
        }
	else{
            $uco_select_box ='';
            $uco_select_box.='<option value= >-------select Group Post--------';
            $uco_select_box.='<option value=MultitaskingStaff> Multitasking staff';
            $uco_select_box.='<option value=TechnicalStaf> Technical staff';
            $uco_select_box.='<option value=MinisterialStaff> Ministerial staff';
            $uco_select_box.='<option value=AdministrativStaff> Administrativ staff';
            $uco_select_box.='<option value=Officer> Officer';
            $uco_select_box.='<option value=TechnicalOfficer> Technical Officer';
            $uco_select_box.='<option value=SupportingStaff> Supporting Staff';
            
	}
       echo json_encode($uco_select_box);
    }

 /* This function has been created for get position of Employee Type */
    public function getemptypevalue(){
       $emptype = $this->input->post('emptype');
       //echo "get emp type vale====1==>".$emptype;
       $parts = explode(',',$emptype);
       $datawh=array('sp_campusid' => $parts[0],'sp_uo' => $parts[1], 'sp_dept' => $parts[2], 'sp_schemecode'=> $parts[3],
			'sp_group' => $parts[4], 'sp_tnt' => $parts[5], 'sp_emppost' =>$parts[6], 'sp_type' =>$parts[7]);
       $emptype_data = $this->sismodel->get_listspficemore('staff_position', 'sp_position', $datawh);
       if(!empty($emptype_data)){
       foreach($emptype_data as $empdata){ 
//	echo "get emp type vale====2==>".$empdata->sp_position;
	 	$p = $empdata->sp_position;
		$ss= 0;
		$v='';	
        	$p1='';
        	$p2='';
        	$p3='';
        	$p4='';
        	$p5='';
        	$p6='';
	   }
         }
	else{
		$p=0;
		$ss=0;
		$v='';	
        	$p1='';
        	$p2='';
        	$p3='';
        	$p4='';
        	$p5='';
        	$p6='';
       }
	 echo json_encode($p.','.$ss.','.$v.','.$p1.','.$p2.','.$p3.','.$p4.','.$p5.','.$p6);
	
    }
 
 /* This function has been created for calculate position of Employee Type */
    public function getsstype(){
       $emptype = $this->input->post('sstype');
       $parts = explode(',',$emptype);
       if($parts[1]=='Permanent'){
	 	$p=$parts[0];
		$v=$parts[2]-$parts[0];	
        	$p1=$parts[2];
        	$p2=$parts[0];
        	$p3=$parts[2]-$parts[0];
        	$p4=0;
        	$p5=0;
        	$p6=0;
	    }
         elseif($parts[1]=='Temporary'){
	 	$p=$parts[0];
		$v=$parts[2]-$parts[0];	
        	$p1=0;
        	$p2=0;
        	$p3=0;
        	$p4=$parts[2];
        	$p5=$parts[0];
        	$p6=$parts[2]-$parts[0];
	    }
        else{	
	 	$p=0;
		$v='';	
        	$p1='';
        	$p2='';
        	$p3='';
        	$p4='';
        	$p5='';
        	$p6='';
	   }
	  echo json_encode($p.','.$v.','.$p1.','.$p2.','.$p3.','.$p4.','.$p5.','.$p6);
    }

  /*This function has been created for display Designation and PayBand on the Basis of Group */
  public function getdesigpaybandlist(){
	$group = $this->input->post('group');
	//echo "Om Prakash======>".$group;
        $datawh=array('desig_group' => $group);
        $desig = $this->commodel->get_listspficemore('designation','desig_id,desig_name,desig_payscale,desig_code',$datawh);
        $desig_select_box ='';
        $desig_select_box.='<option value="">----------Select Designation-----------';
        foreach($desig as $drecord){
        // echo "Om Prakash====2====>".$drecord->desig_id;   
            $desig_select_box.='<option value='.$drecord->desig_id.'>'.$drecord->desig_name.'('. $drecord->desig_code .')'.' ';
        }
        $payb_select_box ='';
        $payb_select_box.='<option value="">----------Select Payband-----------';
        foreach($desig as $drecord){
      //  echo "Om Prakash====3====>".$drecord->desig_payscale;   
            $payb_select_box.='<option value='.$drecord->desig_payscale.'>'.$drecord->desig_payscale ;
        }
        echo json_encode($desig_select_box.','.$payb_select_box);
  }

 //===================End of Staff Position ============================

   
   /* This function has been created for get list of uco on the basis of campus */
    /*In future this code may be replace when either campusid added in the 
     authority or authority added in campus.*/
    
    public function getuoclist(){
        $scid = $this->input->post('campusname');
        $auco_data = $this->sismodel->get_listrow('map_sc_uo','scuo_scid',$scid);
        $aucolist = $auco_data->result();
        $uco_select_box ='';
        $uco_select_box.='<option value="">-------University Officer Control--------';
        foreach($aucolist as $aucoid){
            $auoname=$this->lgnmodel->get_listspfic1('authorities', 'name', 'id',$aucoid->scuo_uoid)->name;
            $auocode=$this->lgnmodel->get_listspfic1('authorities', 'code', 'id',$aucoid->scuo_uoid)->code;
            /*$auouserid=$this->lgnmodel->get_listspfic1('authority_map', 'user_id', 'authority_id',$aucoid->cudsd_auoid)->user_id;
            $auofname=$this->lgnmodel->get_listspfic1('userprofile', 'firstname', 'userid',$auouserid)->firstname;
            $auolname=$this->lgnmodel->get_listspfic1('userprofile', 'lastname', 'userid',$auouserid)->lastname;
            $auoflname=$auofname." ".$auolname;
            $uco_select_box.='<option value='.$aucoid->cudsd_auoid.'>'.$auoflname.' ';*/
        // json_encode("in controller====".$aucoid->scuo_uoid);
            $uco_select_box.='<option value='.$aucoid->scuo_uoid.'>'.$auoname." (".$auocode.") ".' ';
        } 
        echo json_encode($uco_select_box);
    }
    
   
    /* This function has been created for get list of schemes on the basis of  selected campus and uco */
     
    public function getnewdeptlist(){
        $combid = $this->input->post('campuoc');
        //echo json_encode("combination===".$combid);
        $parts = explode(',',$combid); 
       // echo json_encode("this is test===".$parts[0].",".$parts[1]);
        $sccode=$this->commodel->get_listspfic1('study_center', 'sc_code', 'sc_id',$parts[0])->sc_code;
        //$datawh=array('cudsd_scid' => $parts[0],'cudsd_auoid' => $parts[1]);
        $datawh=array('dept_uoid' => $parts[1],'dept_sccode' => $sccode);
        //$comb_data = $this->sismodel->get_listspficemore('cudsdmap','cudsd_deptid',$datawh);
        $comb_data = $this->commodel->get_listspficemore('Department','dept_id,dept_name,dept_code',$datawh);
        //$comblist = $comb_data->result();
        $dept_select_box ='';
        $dept_select_box.='<option value="">-------Select Department--------';
        foreach($comb_data as $combdataid){
           // $deptname=$this->commodel->get_listspfic1('Department', 'dept_name', 'dept_id',$combdataid->cudsd_deptid)->dept_name;
            //$dept_select_box.='<option value='.$combdataid->cudsd_deptid.'>'.$deptname.' ';
            $dept_select_box.='<option value='.$combdataid->dept_id.'>'.$combdataid->dept_name.'('.$combdataid->dept_code.')'.' ';
            
        }
        echo json_encode($dept_select_box);
         
    }
     /* This function has been created for get list of schemes on the basis of  selected campus, uco and department */
    public function getnewdeptschemelist(){
        //$campuocdept = $this->input->post('combdept');
        //$parts = explode(',',$campuocdept);
        $campdept = $this->input->post('combdept');
        //$datawh=array('cudsd_scid' => $parts[0],'cudsd_auoid' => $parts[1],'cudsd_deptid' => $parts[2]);
        $datawh=array('sd_deptid' => $campdept);
        $comb_data = $this->sismodel->get_listspficemore('scheme_department','sd_id,sd_name,sd_code',$datawh);
        $schm_select_box ='';
        $schm_select_box.='<option value="">-------Select Scheme Name--------';
        foreach($comb_data as $combdataid){
            //$schmname=$this->sismodel->get_listspfic1('scheme_department', 'sd_name', 'sd_id',$aucoid->cudsd_schid)->sd_name;
            $schm_select_box.='<option value='.$combdataid->sd_id.'>'.$combdataid->sd_name.'('.$combdataid->sd_code.')'.' ';
            
        }
        echo json_encode($schm_select_box);
       // $this->depmodel->get_sschemelist($deptid);
    }
    /* This function has been created for get list of DDO on the basis of  selected campus, uco,department,schemes */
    public function getddolist(){
        //$campuocdeptschm = $this->input->post('combfour');
        $campdeptschm = $this->input->post('combthree');
       //$parts = explode(',',$campuocdeptschm);
        $parts = explode(',',$campdeptschm);
        //$datawh=array('cudsd_scid' => $parts[0],'cudsd_auoid' => $parts[1],'cudsd_deptid' => $parts[2],'cudsd_schid' => $parts[3]);
        $datawh=array('ddo_scid' => $parts[0],'ddo_deptid' => $parts[1],'ddo_schid' => $parts[2]);
        $comb_data = $this->sismodel->get_listspficemore('ddo','ddo_id,ddo_name,ddo_code',$datawh);
        $ddo_select_box ='';
        $ddo_select_box.='<option value="">-------Drawing and Disbursing Officer--------';
        foreach($comb_data as $combdataid){
            //$ddouserid=$this->lgnmodel->get_listspfic1('authority_map', 'user_id', 'authority_id',$combdataid->cudsd_ddoid)->user_id;
            //$ddofname=$this->lgnmodel->get_listspfic1('userprofile', 'firstname', 'userid',$ddouserid)->firstname;
            //$ddolname=$this->lgnmodel->get_listspfic1('userprofile', 'lastname', 'userid',$ddouserid)->lastname;
            //$ddoflname=$ddofname." ".$ddolname;
            //$ddo_select_box.='<option value='.$combdataid->cudsd_ddoid.'>'.$ddoflname.' ';
            $ddo_select_box.='<option value='.$combdataid->ddo_id.'>'.$combdataid->ddo_name. '(' .$combdataid->ddo_code. ')'.' ';
            
        }
        echo json_encode($ddo_select_box);
    }
    /* This function has been created for get list of Designation on the basis of  selected Group */
    public function getdesiglist(){
        $groups = $this->input->post('group');
        //echo json_encode("group=incontroller=".$groups);
        $datawh=array('desig_group' => $groups);
        $grp_data = $this->commodel->get_listspficemore('designation','desig_id,desig_name,desig_code',$datawh);
        //echo json_encode("grouplist==".$grp_data);
        $desig_select_box ='';
        $desig_select_box.='<option value="">----------Select Designation-----------';
        foreach($grp_data as $grprecord){
           // echo json_encode("grouplist==".$grprecord->desig_name);
            $desig_select_box.='<option value='.$grprecord->desig_id.'>'.$grprecord->desig_name.'('. $grprecord->desig_code .')'.' ';
            
        }
        echo json_encode($desig_select_box);
    }
    /* This function has been created for get the vacant shown against position */
    public function getemppostposition(){
        $combval = $this->input->post('combsix');
        	//echo json_encode("post=vaccancy==3=".$combval);
        $parts = explode(',',$combval);
        /******************Query for filteraion the post************************************/
        /*$datawh=array('sp_campusid' => $parts[0],'sp_uo' => $parts[1], 'sp_dept' => $parts[2],
                       'sp_schemecode'=> $parts[3],'sp_emppost' => $parts[4], 'sp_group' => $parts[5],'sp_tnt' => $parts[6]);*/
        $datawh=array('sp_campusid' => $parts[0],'sp_uo' => $parts[1], 'sp_dept' => $parts[2],
                       'sp_emppost' => $parts[3], 'sp_tnt' => $parts[4]);
        $emppost_data = $this->sismodel->get_listspficemore('staff_position','sp_vacant',$datawh);
        //echo json_encode("post====".$emppost_data);
        $emppost_select_box ='';
        $emppost_select_box.='<option value="">-------------- Select Post -----------------';
        if(!empty($emppost_data)){ 
        	//echo json_encode("post=vaccancy==1=".$emppost_data->sp_vacant);
           // foreach($emppost_data as $records){ 
             //   if($records->sp_vacant > 0){ 
                    /*$datawh2=array('sp_campusid' => $parts[0],'sp_uo' => $parts[1], 'sp_dept' => $parts[2],
                        'sp_schemecode'=> $parts[3],'sp_group' => $parts[5],'sp_tnt' => $parts[6]); */ 
                    $datawh2=array('sp_campusid' => $parts[0],'sp_uo' => $parts[1], 'sp_dept' => $parts[2],'sp_tnt' => $parts[4],'sp_vacant>' =>0); 
                    //$emppost_finaldata = $this->sismodel->get_listspficemore('staff_position', 'sp_emppost,sp_vacant',$datawh2);
                    $emppost_finaldata = $this->sismodel->get_distinctrecord('staff_position', 'sp_emppost',$datawh2);
                    foreach($emppost_finaldata as $empdata){
                        $emppost_name=$this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id',$empdata->sp_emppost)->desig_name;
                       // if($empdata->sp_vacant > 0){    
                            $emppost_select_box.='<option value='.$empdata->sp_emppost.'>'.$emppost_name.' ';
                        //}  
                    }//foreach    
              //  }//if
            //}//foreach    
        } //if close   
        else{
            $emppost_select_box='No vacancy';
        }
        //echo json_encode("post=22===".$emppost_select_box);
        echo json_encode($emppost_select_box);
                        
    }
       
    /***********************************Employee type from staff position*********************************************/   
    /* This function has been created for get the employee type vacant shown against position */
    public function getemptypeposition(){
        $combval = $this->input->post('combfive');
        $parts = explode(',',$combval);
      //  $uoid=$this->lgnmodel->get_listspfic1('authority_map', 'authority_id', 'user_id',$parts[1])->authority_id;
       // $emppost_id=$this->commodel->get_listspfic1('designation', 'desig_id', 'desig_name',$parts[4])->desig_id;
       //  echo json_encode("seema=27===".$emppost_id);
        /*$datawh=array('sp_campusid' => $parts[0],'sp_uo' => $parts[1], 'sp_dept' => $parts[2],
                        'sp_schemecode'=> $parts[3],'sp_emppost' => $parts[4], 'sp_tnt' => $parts[5]);*/
        $datawh=array('sp_campusid' => $parts[0],'sp_uo' => $parts[1], 'sp_dept' => $parts[2],
                        'sp_emppost' => $parts[3], 'sp_tnt' => $parts[4]);
        
        $emptype_data = $this->sismodel->get_listspficemore('staff_position', 'sp_type',$datawh);
        // echo json_encode("seema=77===".$emptype_data);
        $emptype_select_box ='';
        $emptype_select_box.='<option value="">------ Select Employee Type -----------';
        if(!empty($emptype_data)){ 
                      
            foreach($emptype_data as $empdata){
                // echo json_encode("seema=999===".$empdata);
              
                $emptype_select_box.='<option value='.$empdata->sp_type.'>'.$empdata->sp_type.' ';
              
            }//foreach
            
        } //if close   
        else{
            
            $emptype_select_box='No vacancy';
            
        }
        echo json_encode($emptype_select_box);
                        
    }
    /*********************************** closer Employee type from staff position*********************************************/   
  
    
    /* This function has been created for get list of department on the basis of  selected uco */
     
    public function getuocdeptlist(){
        $combid = $this->input->post('campuoc');
        $parts = explode(',',$combid);
        $sccode=$this->commodel->get_listspfic1('study_center', 'sc_code', 'sc_id',$parts[0])->sc_code;
        $datawh=array('dept_sccode'=> $sccode,'dept_uoid' => $parts[1]);
        $comb_data = $this->commodel->get_listspficemore('Department','dept_id,dept_name,dept_code',$datawh);
        $dept_select_box ='';
        $dept_select_box.='<option value="">------- Select Department ----------------------';
        foreach($comb_data as $combdataid){
            $dept_select_box.='<option value='.$combdataid->dept_id.'>'.$combdataid->dept_name.'('.$combdataid->dept_code.')'.' ';
            
        }
        echo json_encode($dept_select_box);
         
    }
    /************************** close of department uco ************************************************************************************/
    /* This function has been created for get list of designation on the basis of  selected employee type */
    public function gettypedesiglist(){
        $etpye = $this->input->post('emptypeid');
        $datawh=array('desig_type' => $etpye);
        $comb_data = $this->commodel->get_listspficemore('designation','desig_id,desig_name,desig_code',$datawh);
        $desig_select_box ='';
        $desig_select_box.='<option value="">------------- Select Designation -----------------';
        foreach($comb_data as $combdataid){
            $desig_select_box.='<option value='.$combdataid->desig_id.'>'.$combdataid->desig_name.'('.$combdataid->desig_code.')'.' ';
            
        }
        echo json_encode($desig_select_box);
         
    }
    /************************** close of designation on the basis of  uco and department **********************************************/
     public function getempnamelist(){
        $combfour = $this->input->post('uddempt');
        $parts = explode(',',$combfour);
       // echo "values====".$combfour ;  
        $datawh=array('emp_uocid' => $parts[0],'emp_dept_code' => $parts[1], 'emp_worktype' => $parts[2], 'emp_desig_code' => $parts[3]);
        $comb_data = $this->sismodel->get_listspficemore('employee_master','emp_id,emp_code,emp_name',$datawh);
        $emplst_select_box ='';
        $emplst_select_box.='<option value="">---------- Select Employee Name -------------';
        foreach($comb_data as $combdataid){
            $emplst_select_box.='<option value='.$combdataid->emp_id.'>'.$combdataid->emp_name.'('.$combdataid->emp_code.')'.' ';
            
        }
        echo json_encode($emplst_select_box);
         
    }
    
    /****************************staff retirement**********************************************************/
    public function staffretirement($selempid=0){
        $selectfield ="emp_id,emp_code,emp_name,emp_dept_code,emp_desig_code,emp_worktype,emp_email,emp_doj,emp_dor,emp_uocid,emp_schemeid,emp_scid,emp_photoname";
        $whorder = "emp_name  asc";
        $crit=$this->input->post('filter',TRUE);
        $data['search']=$crit;
        $data['selempid']=$selempid;
        if(isset($_POST['filter'])) {
           
            $wtype = $this->input->post('wtype');
            $uoff  = $this->input->post('uoff');
            $dept  = $this->input->post('dept');
            $desig  = $this->input->post('desig');
            $emp    = $this->input->post('emp');
            $data['selempid']=$emp;
            if($uoff!="All" && $dept!="All"){
                $whdata = array ('emp_worktype' => $wtype,'emp_uocid' => $uoff,'emp_dept_code' => $dept, 'emp_desig_code' =>$desig,'emp_id'=>$emp);
            }
            if($uoff =="All" && $dept!="All" ){
                $whdata = array ('emp_worktype' => $wtype,'emp_dept_code' => $dept, 'emp_desig_code' =>$desig,'emp_id'=>$emp);
            }
            if($uoff !="All" && $dept =="All" ){
                $whdata = array ('emp_worktype' => $wtype,'emp_uocid' => $uoff, 'emp_desig_code' =>$desig,'emp_id'=>$emp);
            }
            if($uoff =="All" && $dept == "All" ){
                $whdata = array ('emp_worktype' => $wtype,'emp_desig_code' =>$desig,'emp_id'=>$emp);
            }
		 $whdata['emp_leaving'] = NULL;
		 $whdata['emp_dor>='] = date('y-m-d');
            $data['empret'] = $this->sismodel->get_orderlistspficemore('employee_master',$selectfield,$whdata,$whorder);
                       
        }
        
        if(isset($_POST['update'])){
            $reason = $this->input->post('resret');
            $dateofleaving = $this->input->post('dateofleaving');
            $remark = $this->input->post('remark');
           
            $empcode=$this->sismodel->get_listspfic1('employee_master', 'emp_code', 'emp_id',$selempid)->emp_code;
            $empemail=$this->sismodel->get_listspfic1('employee_master', 'emp_email', 'emp_id',$selempid)->emp_email;
            $doj=$this->sismodel->get_listspfic1('employee_master', 'emp_doj', 'emp_id',$selempid)->emp_doj;
            $dor=$this->sismodel->get_listspfic1('employee_master', 'emp_dor', 'emp_id',$selempid)->emp_dor;
            $update_data = array(
               'sre_empid' => $selempid,
               'sre_empcode' => $empcode,
               'sre_empemailid' => $empemail,
               'sre_doj'  => $doj,
               'sre_dor'  => $dor,
               'sre_reason' => $reason,
               'sre_reasondate' => $dateofleaving,
	       'sre_remark' => $remark,	
               'sre_creatorid' => $this->session->userdata('username'),
               'sre_creatordate' => date('y-m-d'),
               'sre_modifierid' => $this->session->userdata('username'), 
               'sre_modifydate' => date('y-m-d')
            );
            $dupdetail=array(
               'sre_empid' => $selempid, 
               'sre_empcode' => $empcode,
               'sre_empemailid' => $empemail,
            );
            $dupdata = $this->sismodel->isduplicatemore('staff_retirement', $dupdetail);

            if($dupdata == 1 ){
                $this->session->set_flashdata("err_message", "Record is already exist . 'Employee PF No' = $empcode  , ' Email'= $empemail .");
                redirect('staffmgmt/staffretirement');
                return;
            }
            $retupdateflag=$this->sismodel->insertrec('staff_retirement', $update_data);
            if(!$retupdateflag)
            {
                $this->logger->write_logmessage("error"," Trying to staff retirement record", "staff retirement record is not added. Employee PF No' = $empcode  , ' Email'= $empemail");
                $this->logger->write_dblogmessage("error","Trying to staff retirement record", "staff retirement record is not added. Employee PF No' = $empcode  , ' Email'= $empemail ");
                $this->session->set_flashdata('err_message','Error staff retirement data - ' .' Employee PF No ='. $empcode .' Email='. $empemail. '.', 'error');
                $this->load->view('staffmgmt/staffretirement', $update_data);
            }
            else{
                /*update employeemaster table*/
               /* if($reason == 'Disqualify')
                {
                    $reason='Disqualified';
                } 
                if($reason == 'Expire'){
                    $reason='Expired';
                }
                if($reason == 'Resign'){
                   $reason =$reason.'ed'; 
                }*/
                $empup_data = array(
                  'emp_leaving' => $reason 
                );
                $empmasterflag=$this->sismodel->updaterec('employee_master', $empup_data, 'emp_id', $selempid);
                /*update staff position table on staff retirement*/
                $dept=$this->sismodel->get_listspfic1('employee_master', 'emp_dept_code', 'emp_id',$selempid)->emp_dept_code;
                $desig=$this->sismodel->get_listspfic1('employee_master', 'emp_desig_code', 'emp_id',$selempid)->emp_desig_code;
                $worktype=$this->sismodel->get_listspfic1('employee_master', 'emp_worktype', 'emp_id',$selempid)->emp_worktype;
                $emptype=$this->sismodel->get_listspfic1('employee_master', 'emp_type_code', 'emp_id',$selempid)->emp_type_code;
                $empscid=$this->sismodel->get_listspfic1('employee_master', 'emp_scid', 'emp_id',$selempid)->emp_scid;
                $empuocid=$this->sismodel->get_listspfic1('employee_master', 'emp_uocid', 'emp_id',$selempid)->emp_uocid;
                $empschmid=$this->sismodel->get_listspfic1('employee_master', 'emp_schemeid', 'emp_id',$selempid)->emp_schemeid;
             //   echo "==dept==".$dept."===desig===".$desig."==wtype===".$worktype."===etype===".$emptype."==scid===".$empscid."==uocid===".$empuocid."===schmid===".$empschmid;
                $upspdata_flag=$this->sismodel->updatestaffposition2($empscid,$empuocid,$dept,$desig,$worktype,$emptype,$empschmid);
                if(!$upspdata_flag){
                    $this->logger->write_logmessage("error","Error in update staff position ", "Error in staff position record update" );
                    $this->logger->write_dblogmessage("error","Error in update staff position", "Error in staff position record update");
                }
                else{
                    $this->logger->write_logmessage("update","update staff position ", "staff position record updated successfully ");
                    $this->logger->write_dblogmessage("update","staff position", "staff position record updated successfully");
                }
                $this->logger->write_logmessage("insert","staff retirement record insert " , "staff retirement record inserted successfully... ");
                $this->logger->write_dblogmessage("insert","staff retirement record insert" , "staff retirement record inserted successfully... ");
                $this->session->set_flashdata('success','staff retirement record inserted successfully '.' Employee PF No ='. $empcode .' Email='. $empemail);
                redirect('staffmgmt/staffretirement');
            }
        }//if update button
        $fields="sre_empid,sre_empcode,sre_empemailid,sre_doj,sre_dor,sre_reason,sre_reasondate";  
        $data['records'] = $this->sismodel->get_orderlistspficemore('staff_retirement',$fields,'','sre_reasondate asc');
        $this->logger->write_logmessage("view"," view staff retirement list" );
        $this->logger->write_dblogmessage("view"," view staff retirement list");
        $this->load->view('staffmgmt/staffretirement',$data);
    }
    /*******************************************closer staff retirement********************************/
    /*******************************************designation list********************************/
    public function getcombdesiglist(){
        $combthree = $this->input->post('wtuodept');
        $parts = explode(',',$combthree);
        if($parts[1]!="All" && $parts[2]!="All"){
            $datawh=array('emp_worktype' => $parts[0],'emp_uocid' => $parts[1],'emp_dept_code' => $parts[2]);
        }
        if($parts[1]== "All" && $parts[2]!="All" ){
            $datawh=array('emp_worktype' => $parts[0],'emp_dept_code' => $parts[2]);
        }
        if($parts[1] != "All" && $parts[2] =="All" ){
            $datawh=array('emp_worktype' => $parts[0],'emp_uocid' => $parts[1]);
        }
        if($parts[1] == "All" && $parts[2] == "All" ){
            $datawh=array('emp_worktype' => $parts[0]);
        }
        $comb_data = $this->sismodel->get_distinctrecord('employee_master','emp_desig_code',$datawh);
        $desig_select_box ='';
        $desig_select_box.='<option value="">------------- Select Designation ----------';
        foreach($comb_data as $combdataid){
            $designame=$this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id',$combdataid->emp_desig_code)->desig_name;
            $desigcode=$this->commodel->get_listspfic1('designation', 'desig_code', 'desig_id',$combdataid->emp_desig_code)->desig_code;
            $desig_select_box.='<option value='.$combdataid->emp_desig_code.'>'.$designame. '(' .$desigcode. ')'.' ';
                           
        }
        echo json_encode($desig_select_box);
         
    }
    /*******************************************closer designation list********************************/
    /*******************************************employee list********************************/
    public function getempnamelist2(){
        $combfour = $this->input->post('uddempt');
        $parts = explode(',',$combfour);
        //echo "values====".$combfour ;  
        if($parts[0]!="All" && $parts[1]!="All"){
            $datawh=array('emp_uocid' => $parts[0],'emp_dept_code' => $parts[1], 'emp_worktype' => $parts[2], 'emp_desig_code' => $parts[3],'emp_leaving' =>NULL);  
        }
        if($parts[0] == "All" && $parts[1]!="All" ){
            $datawh=array('emp_dept_code' => $parts[1], 'emp_worktype' => $parts[2], 'emp_desig_code' => $parts[3],'emp_leaving' =>NULL);  
        }
        if($parts[0]!= "All" && $parts[1] =="All" ){
            $datawh=array('emp_uocid' => $parts[0],'emp_worktype' => $parts[2], 'emp_desig_code' => $parts[3],'emp_leaving' =>NULL);     
        }
        if($parts[0] == "All" && $parts[1] =="All" ){
            $datawh=array('emp_worktype' => $parts[2], 'emp_desig_code' => $parts[3],'emp_leaving' =>NULL);  
        }
        $comb_data = $this->sismodel->get_listspficemore('employee_master','emp_id,emp_code,emp_name',$datawh);
        $emplst_select_box ='';
        $emplst_select_box.='<option value="">---------- Select Employee Name -------------';
        if(!empty($comb_data)){ 
            foreach($comb_data as $combdataid){
                $emplst_select_box.='<option value='.$combdataid->emp_id.'>'.$combdataid->emp_name.'('.$combdataid->emp_code.')'.' ';
            
            }
        }
        else{
            $emplst_select_box ='novalue';
        }
        echo json_encode($emplst_select_box);
    
    }
    /*******************************************closer employeenamelist********************************/
    
    /* This function has been created for get Grades on the basis of  selected working type */
     
    public function getgradelist(){
        $combid = $this->input->post('wtype');
        echo json_encode($combid);
        $grade_select_box ='';
        $grade_select_box.='<option value>------- Select Grade -----------------';
        if($combid == 'Teaching'){
            $grade_select_box.='<option value='.'Career'.''.'Advance(CA)'.'>'.'Career'.''.'Advance(CA)'.'';
            $grade_select_box.='<option value='.'Regular(R)'.'>'.'Regular(R)'.'';    
               
        }
        else{
            $grade_select_box.='<option value='.'Selection Grade(SG)'.'>'.'Selection Grade(SG)'.'';
            $grade_select_box.='<option value='.'Special Grade(SplG)'.'>'.'Special Grade(SplG)'.'';    
        }
        echo json_encode($grade_select_box);
         
    }
    
    /*******************************************closer grade list********************************/
    
    /* This function has been created for get the vacant shown against position */
    public function getemppostpositionnew(){
        $combval = $this->input->post('combfive');
        $parts = explode(',',$combval);
        /******************Query for filteraion the post************************************/
        $datawh=array('sp_campusid' => $parts[0],'sp_uo' => $parts[1], 'sp_dept' => $parts[2],
                       'sp_schemecode' => $parts[3], 'sp_tnt' => $parts[4]);
        $emppost_data = $this->sismodel->get_listspficemore('staff_position','sp_emppost,sp_vacant',$datawh);
        $emppost_select_box ='';
        $emppost_select_box.='<option value="">-------------- Select Post -----------------';
        if(!empty($emppost_data)){ 
        	
            foreach($emppost_data as $records){ 
                if($records->sp_vacant > 0){ 
                    $emppost_name=$this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id',$records->sp_emppost)->desig_name;
                    $emppost_select_box.='<option value='.$records->sp_emppost.'>'.$emppost_name.' ';
                  
                }//if
            }//foreach    
        } //if close   
        else{
            $emppost_select_box='No vacancy';
        }
        echo json_encode($emppost_select_box);
                        
    }
       
    /***********************************shown against position*********************************************/   
}    

