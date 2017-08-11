<?php

defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name Staffmgmt.php
 * @author Manorama Pal (palseema30@gmail.com)
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
    
    /* Display Category record */

    public function employeelist(){

	$data['records'] = $this->sismodel->get_list('employee_master');
        $this->logger->write_logmessage("view"," view employee list" );
        $this->logger->write_dblogmessage("view"," view employee list");
        $this->load->view('staffmgmt/employeelist',$data);
    }

    public function staffprofile(){
        $this->subject= $this->commodel->get_listspfic2('subject','sub_id','sub_name');
        $this->orgcode=$this->commodel->get_listspfic1('org_profile','org_code','org_id',2)->org_code;
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
                        $newFileName = $_FILES['userfile']['name'];
                        $fileExt1 = explode('.', $newFileName);
                        $file_ext = end( $fileExt1);
                        $new_name = $empcode.".".$file_ext; 
                                                
                        $config = array(
                            'upload_path' =>  "./uploads/SIS/empphoto",
                            'allowed_types' => "gif|jpg|png|jpeg",
                            'overwrite' => TRUE,
                            'max_size' => "10000", // Can be set to particular file size , here it is 2 MB(2048 Kb)
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
                    // $this->mailstoperson =$this->mailmodel->mailstouser('kishore.shukla@gmail.com', $sub, $mess,'','Sis');
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

}    
