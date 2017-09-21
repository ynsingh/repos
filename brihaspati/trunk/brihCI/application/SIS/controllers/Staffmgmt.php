<?php

defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name Staffmgmt.php
 * @author Manorama Pal (palseema30@gmail.com) Staff Profile,  Staff transfer and posting
 * @author Om Prakash (omprakashkgp@gmail.com) Staff Position
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

	$data['records'] = $this->sismodel->get_list('employee_master');
        $this->logger->write_logmessage("view"," view employee list" );
        $this->logger->write_dblogmessage("view"," view employee list");
        $this->load->view('staffmgmt/employeelist',$data);
    }

    public function staffprofile(){
        $this->subject= $this->commodel->get_listspfic2('subject','sub_id','sub_name');
        $this->orgcode=$this->commodel->get_listspfic1('org_profile','org_code','org_id',1)->org_code;
        $this->campus=$this->commodel->get_listspfic2('study_center','sc_id','sc_name','org_code',$this->orgcode);
        $this->uoc=$this->lgnmodel->get_list('authorities');
        $this->desig= $this->commodel->get_listspfic2('designation','desig_id','desig_name');
        $this->salgrd=$this->sismodel->get_list('salary_grade_master');
                    
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
            $this->form_validation->set_rules('emppost','Employeepost','trim|xss_clean');
            $this->form_validation->set_rules('gender','Gender','trim|xss_clean');
            $this->form_validation->set_rules('community','Community','trim|xss_clean');
            $this->form_validation->set_rules('religion','Religion','trim|xss_clean');
            $this->form_validation->set_rules('caste','Caste','trim|xss_clean|alpha');
            $this->form_validation->set_rules('workingtype','Workingtype','trim|xss_clean');
            
            $this->form_validation->set_rules('emptype','Employee Type','trim|xss_clean');
            $this->form_validation->set_rules('payband','PayBand','required|xss_clean');
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
            $this->form_validation->set_rules('emailid','E-Mail ID','trim|required|valid_email');
            $this->form_validation->set_rules('Address','Address','trim|xss_clean');
            
            $this->form_validation->set_rules('mothertongue','MotherTongue','trim|xss_clean');
            $this->form_validation->set_rules('nativity','Nativity','trim|xss_clean');
            $this->form_validation->set_rules('phonemobileno','Phone/Mobile','trim|xss_clean|numeric');
            
            //Repopulate forms value
           /* if($_POST){
		$this->data['empcode']['value']=$this->input->get_post('empcode',TRUE);
		$this->data['empname']=$this->input->get_post('empname');
		$this->data['specialisation']=$this->input->get_post('specialisation');
		$this->data['campus']=$this->input->get_post('campus');
                
	
            */    
            if($this->form_validation->run() == FALSE){
                $this->load->view('staffmgmt/staffprofile');
            }
            //if($this->form_validation->run()==TRUE){
            else{
                $bank_ifsccode=$_POST['bankname'].",".$_POST['ifsccode'];
            
                $data = array(
                    'emp_code'                =>$_POST['empcode'],
                   // 'emp_pfno'                =>$_POST['emppfno'],
                    'emp_name'                =>$_POST['empname'],
                    'emp_specialisationid'    =>$_POST['specialisation'],
                    
                    'emp_scid'                =>$_POST['campus'],
                    'emp_uocid'               =>$_POST['uocontrol'],
                    'emp_dept_code'         =>$_POST['department'],
                    'emp_schemeid'          =>$_POST['schemecode'],
                    'emp_desig_code'        =>$_POST['designation'],
                    'emp_post'              =>$_POST['emppost'],
                    
                    'emp_gender'            =>$_POST['gender'],
                    'emp_community'         =>$_POST['community'],
                    'emp_religion'          =>$_POST['religion'],
                    'emp_caste'             =>$_POST['caste'],
                  
                    
                    'emp_type_code'         =>$_POST['emptype'],
                    'emp_salary_grade'      =>$_POST['payband'],
                    'emp_basic'             =>$_POST['basicpay'],
                    'emp_emolution'         =>$_POST['emolution'],
                    'emp_nhisidno'         =>$_POST['empnhisidno'],
                    'emp_doj'               =>$_POST['dateofjoining'],
                    'emp_pnp'               =>$_POST['pnp'],
                    'emp_phd_status'        =>$_POST['phdstatus'],
                        
                    'emp_dateofphd'         =>$_POST['dateofphd'],
                    'emp_AssrExam_status'   =>$_POST['assrexam'],
                    'emp_dateofAssrExam'    =>$_POST['assrexamdate'],
                    'emp_dor'               =>$_POST['dateofretirement'],
                    'emp_dateofHGP'           =>$_POST['dateofhgp'],
                    'emp_pan_no'            =>$_POST['panno'],
                    
                    'emp_aadhaar_no'        =>$_POST['Aadharrno'],
                    'emp_bank_ifsc_code'    =>$bank_ifsccode,
                    'emp_bank_accno'        =>$_POST['bankacno'],
                    'emp_dob'               =>$_POST['DateofBirth'],
                    'emp_father'            =>$_POST['fathername'],
                    'emp_email'             =>$_POST['emailid'],
                    
                   'emp_address'           =>$_POST['Address'],
                    'emp_mothertongue'      =>$_POST['mothertongue'],
                    'emp_citizen'           =>$_POST['nativity'],
                    'emp_phone'             =>$_POST['phonemobileno']
                                        
                );
                //generate 10 digit random password
                $passwd=$this->commodel->randNum(10);
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
                    
                    $dataems = array(
                        'ems_code'              =>$_POST['empcode'],
                        'ems_working_type'      =>$_POST['workingtype']
			
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
                            'max_size' => "10000", // Can be set to particular file size 
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
                    $sub='User Registration in Staff information System' ;
                    $mess="Your registration is completed. The user id ".$_POST['emailid']." and password is ".$passwd ;
                    // $this->mailstoperson =$this->mailmodel->mailsnd('$_POST['emailid']', $sub, $mess,'','Sis');
                    //  mail flag check 	
                    if($this->mailstoperson){
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
        /*get detail of selected emplyee by passing id for edit*/
        $this->subject= $this->commodel->get_listspfic2('subject','sub_id','sub_name');
        $this->orgcode=$this->commodel->get_listspfic1('org_profile','org_code','org_id',1)->org_code;
        $this->campus=$this->commodel->get_listspfic2('study_center','sc_id','sc_name','org_code',$this->orgcode);
        $this->uoc=$this->lgnmodel->get_list('authorities');
        $this->desig= $this->commodel->get_listspfic2('designation','desig_id','desig_name');
        $this->salgrd=$this->sismodel->get_list('salary_grade_master');
        $empmaster_data=$this->sismodel->get_listrow('employee_master','emp_id', $id);
        $editemp_data['editdata'] = $empmaster_data->row();
        $this->load->view('staffmgmt/editempprofile',$editemp_data);     
        
    }
    /****************************  START OPEN EDIT FORM WITH DATA *************/
    
    /****************************  START UPDATE DATA *************************/
    public function update_profile($id)
    {
        if(isset($_POST['updateprofile'])) {
            /*Form Validation*/
            $this->form_validation->set_rules('empcode','EmployeeCode','trim|required|xss_clean|alpha_numeric|callback_isEmpPFNoExist');
            $this->form_validation->set_rules('empname','EmployeeName','trim|required|xssclean');
            $this->form_validation->set_rules('specialisation','Specialisation','trim|xss_clean');
            $this->form_validation->set_rules('campus','Campus','trim|required|xss_clean');
            $this->form_validation->set_rules('uocontrol','UniversityOfficerControl','trim|required|xss_clean');
            $this->form_validation->set_rules('department','Department','trim|required|xss_clean');
            
            $this->form_validation->set_rules('schemecode','Scheme Name','trim|required|x1ss_clean');
            $this->form_validation->set_rules('designation','Designation','trim|required|xss_clean');
            $this->form_validation->set_rules('emppost','Employeepost','trim|xss_clean');
            $this->form_validation->set_rules('gender','Gender','trim|xss_clean');
            $this->form_validation->set_rules('community','Community','trim|xss_clean');
            $this->form_validation->set_rules('religion','Religion','trim|xss_clean');
            $this->form_validation->set_rules('caste','Caste','trim|xss_clean|alpha');
            $this->form_validation->set_rules('workingtype','Workingtype','trim|xss_clean');
            
            $this->form_validation->set_rules('emptype','Employee Type','trim|xss_clean');
            $this->form_validation->set_rules('payband','PayBand','required|xss_clean');
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
            $this->form_validation->set_rules('emailid','E-Mail ID','trim|required|valid_email');
            $this->form_validation->set_rules('Address','Address','trim|xss_clean');
            
            $this->form_validation->set_rules('mothertongue','MotherTongue','trim|xss_clean');
            $this->form_validation->set_rules('nativity','Nativity','trim|xss_clean');
            $this->form_validation->set_rules('phonemobileno','Phone/Mobile','trim|xss_clean|numeric');
            
            $bankname=$this->input->post('bankname');
            $ifsccode= $this->input->post('ifsccode');       
            $data = array(
                /*'emp_name'                       => $this->input->post('empname'),*/
                'emp_specialisationid'           => $this->input->post('specialisation'),
                'emp_scid'                       => $this->input->post('campus'),
                'emp_uocid'                      => $this->input->post('uocontrol'),
                'emp_dept_code'                  => $this->input->post('department'),
                'emp_schemeid'                   => $this->input->post('schemecode'),
                'emp_desig_code'                 => $this->input->post('designation'),
                
                'emp_post'                       => $this->input->post('emppost'),
                'emp_gender'                     => $this->input->post('gender'),
                'emp_community'                  => $this->input->post('community'),
                'emp_religion'                   => $this->input->post('religion'),
                
                'emp_caste'                      => $this->input->post('caste'),
                'emp_worktype'                   => $this->input->post('workingtype'),
                'emp_type_code'                  => $this->input->post('emptype'),
                'emp_salary_grade'               => $this->input->post('payband'),
                
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
               // 'emp_bank_ifsc_code'             => $this->input->post('bankname'),
                'emp_bank_ifsc_code'             => $bankname.','.$ifsccode,
                'emp_dob'                        => $this->input->post('DateofBirth'),
                'emp_father'                     => $this->input->post('fathername'),
                
                'emp_address'                    => $this->input->post('Address'),
                'emp_mothertongue'               => $this->input->post('mothertongue'),
                'emp_citizen'                    => $this->input->post('nativity'),
                'emp_phone'                      => $this->input->post('phonemobileno'),
                'emp_name'                        => $this->input->post('empname'),
                              
                               
            );
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
                    'max_size' => "10000", // Can be set to particular file size 
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
            if(!upempdata_flag){
                $this->logger->write_logmessage("error","Error in update staff profile ", "Error in staff profile record update" );
                $this->logger->write_dblogmessage("error","Error in update staff profile", "Error in staff profile record update");
                $this->session->set_flashdata('err_message','Error in updating staff profile - ', 'error');
                $this->load->view('staffmgmt/editempprofile', $data);
            }
            else{
                $this->logger->write_logmessage("update","update staff profile ", " Employee record updated successfully ");
                $this->logger->write_dblogmessage("update","staff profile", "Employee record updated successfully");
                $this->session->set_flashdata('success', 'Employee data' .$msgphoto." ".'updated Successfully......'." "."["." "."Employee PF NO:"." ".$_POST['empcode']." and "."EmailId:"." ".$_POST['emailid']." "."]");
                redirect('staffmgmt/employeelist');
            }
        }//closeissetform    
    }
    /****************************  END UPDATE DATA ****************************/
    
    /****************************  START stafftransfer ****************************/
    function stafftransfer(){ 
   
        $this->usrlist=$this->sismodel->get_list('employee_master');;
        $this->uoc=$this->lgnmodel->get_list('authorities');
        $this->desig= $this->commodel->get_listspfic2('designation','desig_id','desig_name');
        if(isset($_POST['stafftransfer'])){
            /* Form validation*/
            $this->form_validation->set_rules('registrarname','RegistrarName','trim|required|xss_clean|alpha_numeric_spaces');
            $this->form_validation->set_rules('designation','Designation','trim|required|xss_clean');
            $this->form_validation->set_rules('usono','universitysancofficerno','trim|xss_clean');
            $this->form_validation->set_rules('rcno','RcNo','trim|required|xss_clean');
            $this->form_validation->set_rules('subject','Subject','trim|required|xss_clean');
            $this->form_validation->set_rules('referenceno','ReferenceNo','trim|required|xss_clean');
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
            $this->form_validation->set_rules('ttadetail','TTADetail','trim|required|xss_clean');
            $this->form_validation->set_rules('dateofrelief','Dateofrelief','trim|required|xss_clean');
            $this->form_validation->set_rules('expdoj','expecteddoj','trim|required|xss_clean');
            $this->form_validation->set_rules('postto','PostTo','trim|required|xss_clean');
            $this->form_validation->set_rules('emailsentto','EmailSentto','trim|required|xss_clean');
            if($this->form_validation->run() == FALSE){
                $this->load->view('staffmgmt/stafftransfer');
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
                    'uit_desig_from'                   => $this->input->post('designation'),
                
                    'uit_staffname'                    => $this->input->post('empname'),
                    'uit_workingpost_from'             => $this->input->post('postfrom'),
                    'uit_uoc_to'                       => $this->input->post('uocontrolto'),
                    'uit_dept_to'                      => $this->input->post('deptto'),
                    'uit_desig_to'                      => $this->input->post('desigto'),
                    'uit_post_to'                      => $this->input->post('postto'),
                    'uit_tta_detail'                   => $this->input->post('ttadetail'),
                
                    'uit_dateofrelief'                 => $this->input->post('dateofrelief'),
                    'uit_dateofjoining'                => $this->input->post('expdoj'),
                    'uit_email_sentto'                 => $this->input->post('emailsentto')
                
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
                    $emppfno=$this->sismodel->get_listspfic1('employee_master', 'emp_code', 'emp_id', $_POST['empname'])->emp_code;
                    $empname=$this->sismodel->get_listspfic1('employee_master', 'emp_name', 'emp_id', $_POST['empname'])->emp_name;
                    $deptto=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$_POST['deptto'])->dept_name; 
                    $this->orgname=$this->commodel->get_listspfic1('org_profile','org_name','org_id',2)->org_name;
                    $this->regname=$this->sismodel->get_listspfic1('user_input_transfer','uit_registrarname','uit_staffname',$id)->uit_registrarname;
                    $this->uitdesig=$this->sismodel->get_listspfic1('user_input_transfer','uit_desig','uit_staffname',$id)->uit_desig;
                    $mail_sent_to=$_POST['emailsentto'];
                   // $mailarray=explode(',',$mail_sent_to);
                    //if sucess send mail to user with transfer order copy 
                    $sub='Employee Transfer And Posting - Letter  ' ;
                    $mess='OFFICE ORDER<br/> Dear'.$empname.'This is to inform you that you will be transferred at'.$deptto.'with immediate effect.<br/>
                    Please find the attachment of transfer order copy<br/> Wish you all the best<br/>'.$this->orgname.'<br/>
                    '.$this->regname.'<br/>'.$this->uitdesig;
                    $attachment=transferordercopy($_POST['empname']);
                    // $this->mailstoperson =$this->mailmodel->mailsnd('$mail_sent_to', $sub, $mess,'$attachment','Sis');
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
                    redirect('staffmgmt/stafftransfer');
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
                $uocname=$this->lgnmodel->get_listspfic1('authorities', 'name', 'id',$detail->emp_uocid)->name;
                $deptname=$this->commodel->get_listspfic1('Department', 'dept_name', 'dept_id',$detail->emp_dept_code)->dept_name;
                $designame=$this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id',$detail->emp_desig_code)->desig_name;
            //    $values='uocfrom='. $detail->emp_uocid.', deptfrom=' .$detail->emp_dept_code.',desigfrom='. $detail->emp_desig_code.',postfrom='.$detail->emp_post;
                $values=$uocname.',' .$deptname.','. $designame.','.$detail->emp_post;
                     
            }
            $scid=$this->sismodel->get_listspfic1('employee_master', 'emp_scid', 'emp_id',$emp)->emp_scid;
            $deptcode=$this->commodel->get_listspfic1('study_center', 'sc_code', 'sc_id', $scid)->sc_code;
            $resultsc = $this->commodel->get_listrow('Department','dept_sccode', $deptcode);
            $dept_data = $resultsc->result();
            if(count($dept_data)>0){
                $dept_select_box = '';
                $dept_select_box.= '<option value="">-------Select Department --------';
                foreach($dept_data as $dept){
                        $dept_select_box.='<option value='.$dept->dept_id.'>'.$dept->dept_name;
                }
            }    
            echo json_encode($values.",".$dept_select_box);
                       
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
        $this->regname=$this->sismodel->get_listspfic1('user_input_transfer','uit_registrarname','uit_staffname',$id)->uit_registrarname;
        $this->uitdesig=$this->sismodel->get_listspfic1('user_input_transfer','uit_desig','uit_staffname',$id)->uit_desig;
        $this->data=$this->sismodel->get_listrow('user_input_transfer','uit_staffname',$id);
        $spec_data['detail'] = $this->data->row();
        $this->load->library('pdf');
        $this->pdf->load_view('staffmgmt/transferordercopy',$spec_data);
        $this->pdf->render();
        $this->pdf->stream("transferorder.pdf");
        
    }

//====================Staff Position===================================

  /*this function has been created for display the record of staff position */
  public function staffposition(){
        $this->result = $this->sismodel->get_list('staff_position');
        $this->load->view('staffmgmt/staffposition');
  }

  /*this function has been created for add new staff Position record */
  public function newstaffposition(){
        $this->scresult = $this->commodel->get_listspfic2('study_center','sc_id', 'sc_name');
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
                $this->form_validation->set_rules('ss','Position Sanction Strength','xss_clean|required|numeric');
                $this->form_validation->set_rules('p','Position Present','xss_clean|required|numeric');
                $this->form_validation->set_rules('v','Position Vacant','xss_clean|required|numeric');
                $this->form_validation->set_rules('ssper','Sanction Strength Permanent','xss_clean|required|numeric');
                $this->form_validation->set_rules('pper','Position Permanent','xss_clean|required|numeric');
                $this->form_validation->set_rules('vper','Vacancy Permanent','xss_clean|required|numeric');
                $this->form_validation->set_rules('sstem','Sanction Strength Temporary','xss_clean|required|numeric');
                $this->form_validation->set_rules('ptem','Position Temporary ','xss_clean|required|numeric');
                $this->form_validation->set_rules('vtem','Vacancy Temporary','xss_clean|required|numeric');
                $this->form_validation->set_rules('address1','Address','xss_clean|required');
                $this->form_validation->set_rules('ssdetail','Sanction Strength Detail','xss_clean|required');
                $this->form_validation->set_rules('remarks','Remarks','xss_clean|required');

       if($this->form_validation->run()==TRUE){

        $sptnt= $this->input->post("tnt");
        $spemppost = $this->input->post("emppost");
        $spss = $this->input->post("ss");
        $spp = $this->input->post("p");
        $spv = $this->input->post("v");
        $spssper = $this->input->post("ssper");
        $sppper = $this->input->post("pper");
        $spvper = $this->input->post("vper");
        $spsstem = $this->input->post("sstem");
        $spptem = $this->input->post("ptem");
        $spvtem = $this->input->post("vtem");

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

        $data['tnt']= array('name' => 'tnt', 'id' => 'tnt', 'maxlength' => '40', 'size' => '26', 'value' => $editsp_data->sp_tnt, 'readonly' => 'readonly' );

        $data['type']= array('name' => 'type', 'id' => 'type', 'maxlength' => '40', 'size' => '26', 'value' => $editsp_data->sp_type, 'readonly' => 'readonly' );

        $data['emppost'] = array('name' => 'emppost', 'id' => 'emppost', 'maxlength' => '40', 'size' => '26', 'value' => $this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id', $editsp_data->sp_emppost)->desig_name, 'readonly' => 'readonly' );

        $data['grouppost'] = array('name' => 'grouppost', 'id' => 'grouppost', 'maxlength' => '40', 'size' => '26', 'value' => $editsp_data->sp_grppost, 'readonly' => 'readonly' );

        $data['scale'] = array('name' => 'scale', 'id' => 'scale', 'maxlength' => '40', 'size' => '26', 'value' => $editsp_data->sp_scale, 'readonly' => 'readonly' );

        $data['methodrect'] = array('name' => 'methodrect', 'id' => 'methodrect', 'maxlength' => '40', 'size' => '26', 'value' => $editsp_data->sp_methodRect, 'readonly' => 'readonly' );

        $data['group'] = array('name' => 'group', 'id' => 'group', 'maxlength' => '40', 'size' => '26', 'value' => $editsp_data->sp_group, 'readonly' => 'readonly' );

        $data['uo'] = array('name' => 'uo', 'id' => 'uo', 'maxlength' => '40', 'size' => '26', 'value' => $this->lgnmodel->get_listspfic1('authorities', 'name', 'id', $editsp_data->sp_uo)->name, );

        $data['dept'] = array('name' => 'dept', 'id' => 'teachername', 'maxlength' => '40', 'size' => '26', 'value' => $this->commodel->get_listspfic1('Department', 'dept_name', 'dept_id', $editsp_data->sp_dept)->dept_name, 'readonly' => 'readonly');

        $data['address1'] = array('name' => 'address1', 'id' => 'address1', 'maxlength' => '40', 'size' => '26', 'value' => $editsp_data->sp_address1, );

        $data['campus'] = array('name' => 'campus', 'id' => 'campus', 'maxlength' => '40', 'size' => '26', 'value' => $this->commodel->get_listspfic1('study_center', 'sc_name', 'sc_id', $editsp_data->sp_campusid)->sc_name, 'readonly' => 'readonly' );

        $data['pnp'] = array('name' => 'pnp', 'id' => 'pnp', 'maxlength' => '40', 'size' => '26', 'value' => $editsp_data->sp_plan_nonplan, 'readonly' => 'readonly' );

        $data['schemecode'] = array('name' => 'schemecode', 'id' => 'schemecode', 'maxlength' => '40', 'size' => '26', 'value' => $this->sismodel->get_listspfic1('scheme_department', 'sd_name', 'sd_id', $editsp_data->sp_schemecode)->sd_name, 'readonly' => 'readonly' );

        $data['ss'] = array('name' => 'ss', 'id' => 'ss', 'maxlength' => '40', 'size' => '26', 'value' => $editsp_data->sp_sancstrenght, );

        $data['p'] = array('name' => 'p', 'id' => 'p', 'maxlength' => '40', 'size' => '26', 'value' => $editsp_data->sp_position, );

        $data['v'] = array('name' => 'v', 'id' => 'v', 'maxlength' => '40', 'size' => '26', 'value' => $editsp_data->sp_vacant, );

        $data['remarks'] = array('name' => 'remarks', 'id' => 'remarks', 'maxlength' => '26', 'size' => '26', 'value' => $editsp_data->sp_remarks, );

        $data['ssdetail'] = array('name' => 'ssdetail', 'id' => 'ssdetail', 'maxlength' => '40', 'size' => '26', 'value' => $editsp_data->sp_ssdetail, );

        $data['ssper'] = array('name' => 'ssper', 'id' => 'ssper', 'maxlength' => '40', 'size' => '26', 'value' => $editsp_data->sp_sspermanent, );

        $data['sstem'] = array('name' => 'sstem', 'id' => 'sstem', 'maxlength' => '40', 'size' => '26', 'value' => $editsp_data->sp_sstemporary, );

        $data['pper'] = array('name' => 'pper', 'id' => 'pper', 'maxlength' => '40', 'size' => '26', 'value' => $editsp_data->sp_pospermanent, );

        $data['ptem'] = array('name' => 'ptem', 'id' => 'ptem', 'maxlength' => '40', 'size' => '26', 'value' => $editsp_data->sp_postemporary, );

        $data['vper'] = array('name' => 'vper', 'id' => 'vper', 'maxlength' => '40', 'size' => '26', 'value' => $editsp_data->sp_vpermanenet, );

        $data['vtem'] = array('name' => 'vtem', 'id' => 'vtem', 'maxlength' => '40', 'size' => '26', 'value' => $editsp_data->sp_vtemporary, );

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
                $this->form_validation->set_rules('ss','Position Sanction Strength','xss_clean|required|numeric');
                $this->form_validation->set_rules('p','Position Present','xss_clean|required|numeric');
                $this->form_validation->set_rules('v','Position Vacant','xss_clean|required|numeric');
                $this->form_validation->set_rules('ssper','Sanction Strength Permanent','xss_clean|required|numeric');
                $this->form_validation->set_rules('pper','Position Permanent','xss_clean|required|numeric');
                $this->form_validation->set_rules('vper','Vacancy Permanent','xss_clean|required|numeric');
                $this->form_validation->set_rules('sstem','Sanction Strength Temporary','xss_clean|required|numeric');
                $this->form_validation->set_rules('ptem','Position Temporary ','xss_clean|required|numeric');
                $this->form_validation->set_rules('vtem','Vacancy Temporary','xss_clean|required|numeric');
                $this->form_validation->set_rules('address1','Address','xss_clean|required');
                $this->form_validation->set_rules('ssdetail','Sanction Strength Detail','xss_clean|required');
                $this->form_validation->set_rules('remarks','Remarks','xss_clean|required');
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

      $update_data = array(
                'sp_tnt'=> $tnt,
                'sp_type'=> $type,
                'sp_emppost'=> $this->commodel->get_listspfic1('designation', 'desig_id', 'desig_name', $emppost)->desig_id ,
                'sp_grppost'=> $grppost,
                'sp_scale'=> $scale,
                'sp_methodRect'=> $methodRect,
                'sp_group'=>$group,
                'sp_uo'=> $uo,
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
  //===================End of Staff Position ============================

}    
