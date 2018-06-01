<?php

defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name Empffmgmt.php
 * @author Manorama Pal (palseema30@gmail.com) Employee Profile, Service and Performance data.
 */

class Empmgmt extends CI_Controller
{
 
    function __construct() {
        parent::__construct();
        $this->load->model('Common_model',"commodel");
        $this->load->model('Login_model',"lgnmodel"); 
        $this->load->model('SIS_model',"sismodel");
        $this->load->model('Dependrop_model',"depmodel");
        $this->load->model("Mailsend_model","mailmodel");
	$this->load->helper('download');
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('welcome');
        }
    }
 
    public function index(){
        
    //$this->load->view('staffmgmt/staffprofile');    
    }
    /* Display Employee record */

    public function viewempprofile(){
        $currentuser=$this->session->userdata('username');
       	$empmaster_data=$this->sismodel->get_listrow('employee_master','emp_email', $currentuser);
        $data['record'] = $empmaster_data->row();
        $emp_id = $this->sismodel->get_listspfic1('employee_master','emp_id','emp_email', $currentuser)->emp_id;
        $data['emp_id']=$emp_id;
	$selectfield="*";
        $whdata = array ('empsd_empid' => $emp_id);
        $whorder = 'empsd_dojoin desc';
        $data['servicedata'] = $this->sismodel->get_orderlistspficemore('employee_servicedetail',$selectfield,$whdata,$whorder);
	$data['addassign'] = $this->sismodel->get_listrow('additional_assignments','aa_empid',$emp_id);
//        $data['servicedata'] = $this->sismodel->get_listrow('employee_servicedetail','empsd_empid',$emp_id);
        $data['performancedata'] = $this->sismodel->get_listrow('Staff_Performance_Data','spd_empid',$emp_id)->row();
       // echo $data['performancedata'];
        //die;
        $this->logger->write_logmessage("view"," view employee profile" );
        $this->logger->write_dblogmessage("view"," view employee profile");
        $this->load->view('empmgmt/viewempprofile',$data);
    }
    public function extstaffpro($empid) {
        $this->roleid=$this->session->userdata('id_role');
        $this->emp_id = $empid;
        if(isset($_POST['extstaffpro'])) {
            $this->form_validation->set_rules('national','National','trim|xss_clean|numeric');
            $this->form_validation->set_rules('international','International','trim|xss_clean|numeric');
            $this->form_validation->set_rules('university','University','trim|xss_clean|numeric');
            $this->form_validation->set_rules('research_national','No.of Research at National','trim|xss_clean|numeric');
            $this->form_validation->set_rules('research_international','No.of Research at International','trim|xss_clean|numeric');
            $this->form_validation->set_rules('popular_national','No.of Popular at National','trim|xss_clean|numeric');
            $this->form_validation->set_rules('popular_international','No.of Popular at International','trim|xss_clean|numeric');
            $this->form_validation->set_rules('presentation_national','No.of Presentation at National','trim|xss_clean|numeric');
            $this->form_validation->set_rules('presentation_international','No.of Presentation at International','trim|xss_clean|numeric');
            $this->form_validation->set_rules('noofproject','No.of Project Handled','trim|xss_clean|numeric');
            $this->form_validation->set_rules('fund','No.of Fund Outlay','trim|xss_clean|numeric');
            $this->form_validation->set_rules('training_attend_national','No.of Training Attended at National','trim|xss_clean|numeric');
            $this->form_validation->set_rules('training_attend_international','No.of Training Attended at International','trim|xss_clean|numeric');
            $this->form_validation->set_rules('training_conducted_national','No.of Training  Conducted at National','trim|xss_clean|numeric');
            $this->form_validation->set_rules('training_conducted_international','No.of Training Conducted at International','trim|xss_clean|numeric');
            $this->form_validation->set_rules('mvsc','No.of Students at MVSc','trim|xss_clean|numeric');
            $this->form_validation->set_rules('phd','No.of Students at PhD','trim|xss_clean|numeric');
            $this->form_validation->set_rules('others','No.of Students at Others','trim|xss_clean|numeric');
            $this->form_validation->set_rules('guestlect','No.of Guest Lecture','trim|xss_clean|numeric');
            $this->form_validation->set_rules('userfile','Select File','trim|xss_clean');

            if($this->form_validation->run()==TRUE){
                //$name = $_FILES['userfile']['name'];
                $name='';
                if(!empty($_FILES['userfile']['name'])){
                    
                    $newFileName = $_FILES['userfile']['name'];
                    $fileExt1 = explode('.', $newFileName);
                    $file_ext = end( $fileExt1);
                    $name = $empid.".".$file_ext; 
                }
                $data = array(
                    'spd_empid'                =>$empid,
                    'spd_int_award'            =>$_POST['national'],
                    'spd_nat_award'            =>$_POST['international'],
                    'spd_uni_award'            =>$_POST['university'],
                    'spd_res_pub_int'          =>$_POST['research_international'],
                    'spd_res_pub_nat'          =>$_POST['research_national'],
                    'spd_pop_pub_int'          =>$_POST['popular_international'],
                    'spd_pop_pub_nat'          =>$_POST['popular_national'],
                    'spd_pre_pub_int'          =>$_POST['presentation_international'],
                    'spd_pre_pub_nat'          =>$_POST['presentation_national'],
                    'spd_noof_project'         =>$_POST['noofproject'],
                    'spd_fund_outly_ofproject' =>$_POST['fund'],
                    'spd_tr_att_int'           =>$_POST['training_attend_international'],
                    'spd_tr_att_nat'           =>$_POST['training_attend_national'],
                    'spd_tr_con_int'           =>$_POST['training_conducted_international'],
                    'spd_tr_con_nat'           =>$_POST['training_conducted_national'],
                    'spd_mvsc_stu_guided'      =>$_POST['mvsc'],
                    'spd_phd_stu_guided'       =>$_POST['phd'],
                    'spd_others_stu_guided'    =>$_POST['others'],
                    'spd_no_ofguestlecture'    =>$_POST['guestlect'],
                    'spd_per_filename'         =>$name,
                );
                $extproflag=$this->sismodel->insertrec('Staff_Performance_Data', $data) ;
                $uplflag=false;
                if(!empty($name)){
                    $_FILES['userFile']['name'] = $_FILES['userfile']['name'];
                    $_FILES['userFile']['type'] = $_FILES['userfile']['type'];
                    $_FILES['userFile']['tmp_name'] = $_FILES['userfile']['tmp_name'];
                    $_FILES['userFile']['size'] = $_FILES['userfile']['size'];
                    $config['upload_path'] = 'uploads/SIS/perfattachment/';
                    $config['max_size'] = '20480000';
                    $config['allowed_types'] = 'pdf';
                    $config['file_name'] = $name;
                    $config['overwrite'] = TRUE;
                    $this->load->library('upload',$config);
                    $this->upload->initialize($config);

                    if($this->upload->do_upload('userfile')){
                        $uploadData = $this->upload->data();
                        $picture = $uploadData['file_name'];
                        $this->logger->write_logmessage("insert","upload staff performance data file ", "Staff Performance Data file attach successfully");
                        $uplflag=true;
                    }
                    else{
                        $picture = '';
                        $error =  array('error' => $this->upload->display_errors());
                        foreach ($error as $item => $value):
                            $ferror = $ferror.$value;
                        endforeach;
                        $ferror=str_replace("\r\n","",$ferror);
                        $simsg = "The permitted size of document is 20MB";
                        $ferror = $simsg.$ferror;
                        $this->logger->write_logmessage("insert","File upload error", $ferror);
                        $this->logger->write_dblogmessage("insert","File upload error", $ferror);
                        $uplflag=false;
                    }
                }
                if(!$uplflag)
                {
                    $this->logger->write_logmessage("insert","Staff Performance Data added", "Staff Performance Data successfully Added without attachment.");
                    $this->logger->write_dblogmessage("insert","Staff Performance Data added", "Staff Performance Data successfully Added without attachment.");
                    $this->session->set_flashdata('success', 'Staff Performance Data successfully Added with attachment.');
                    if($this->roleid == 4){
                        redirect("empmgmt/extstaffpro");
                    }
                    else{
                        redirect("report/viewfull_profile");
                    }    
                }
                else{
                    $empcode=$this->sismodel->get_listspfic1('employee_master','emp_code','emp_id',$empid)->emp_code;
                    $empemail=$this->sismodel->get_listspfic1('employee_master','emp_email','emp_id',$empid)->emp_email;
                    $this->logger->write_logmessage("insert","Staff Performance Data added", "Staff Performance Data successfully Added with attachment.");
                    $this->logger->write_dblogmessage("insert","Staff Performance Data added", "Staff Performance Data successfully Added with attachment.");
                    $this->session->set_flashdata('success', 'Staff Performance Data successfully Added without attachment.'."["." "."Employee PF NO:"." ".$empcode." and "."Username:"." ".$empemail." "."]");
                    if($this->roleid == 4){
                        redirect("empmgmt/viewempprofile");
                    }
                    else{
                       redirect("report/viewfull_profile/".$empid); 
                    }
                    
                }
            }//formtrue closer

        }//sumit button
        $this->load->view('empmgmt/extstaffpro');
    }//function close
    
    /*edit employee performance detail*/
    public function editextstaffpro($id) {
        $this->roleid=$this->session->userdata('id_role');
        $data['id'] = $id;
        $data['performancedata'] = $this->sismodel->get_listrow('Staff_Performance_Data','spd_empid',$id)->row();
        $this->load->view('empmgmt/editextstaffpro',$data);
        
    }
     /****************************  START OPEN EDIT FORM WITH DATA *************/
    
    /****************************  START UPDATE DATA *************************/
    public function updateextstaffpro($id) {
       // echo "updation===".$id;
        
        $sperf_dataquery=$this->sismodel->get_listrow('Staff_Performance_Data','spd_empid', $id);
        $edpref_data['performancedata'] = $sperf_dataquery->row();
        $edpref_data['id'] = $id;
       // $this->emp_id = $id;
        if(isset($_POST['updateextpro'])) {
            //$edpref_data['id'] = $id;
            $this->form_validation->set_rules('national','National','trim|xss_clean|numeric');
            $this->form_validation->set_rules('international','International','trim|xss_clean|numeric');
            $this->form_validation->set_rules('university','University','trim|xss_clean|numeric');
            $this->form_validation->set_rules('research_national','No.of Research at National','trim|xss_clean|numeric');
            $this->form_validation->set_rules('research_international','No.of Research at International','trim|xss_clean|numeric');
            $this->form_validation->set_rules('popular_national','No.of Popular at National','trim|xss_clean|numeric');
            $this->form_validation->set_rules('popular_international','No.of Popular at International','trim|xss_clean|numeric');
            $this->form_validation->set_rules('presentation_national','No.of Presentation at National','trim|xss_clean|numeric');
            $this->form_validation->set_rules('presentation_international','No.of Presentation at International','trim|xss_clean|numeric');
            $this->form_validation->set_rules('noofproject','No.of Project Handled','trim|xss_clean|numeric');
            $this->form_validation->set_rules('fund','No.of Fund Outlay','trim|xss_clean|numeric');
            $this->form_validation->set_rules('training_attend_national','No.of Training Attended at National','trim|xss_clean|numeric');
            $this->form_validation->set_rules('training_attend_international','No.of Training Attended at International','trim|xss_clean|numeric');
            $this->form_validation->set_rules('training_conducted_national','No.of Training  Conducted at National','trim|xss_clean|numeric');
            $this->form_validation->set_rules('training_conducted_international','No.of Training Conducted at International','trim|xss_clean|numeric');
            $this->form_validation->set_rules('mvsc','No.of Students at MVSc','trim|xss_clean|numeric');
            $this->form_validation->set_rules('phd','No.of Students at PhD','trim|xss_clean|numeric');
            $this->form_validation->set_rules('others','No.of Students at Others','trim|xss_clean|numeric');
            $this->form_validation->set_rules('guestlect','No.of Guest Lecture','trim|xss_clean|numeric');
            $this->form_validation->set_rules('userfile','Select File','trim|xss_clean');
            if ($this->form_validation->run() == FALSE)
            {
                
                $this->load->view('empmgmt/editextstaffpro',$edpref_data);
                return;
            }
            else
            {
                $int_award = $this->input->post('international', TRUE);
                $nat_award = $this->input->post('national', TRUE);
                $uni_award = $this->input->post('university', TRUE);
                $res_pub_int = $this->input->post('research_international', TRUE);
                $res_pub_nat = $this->input->post('research_national', TRUE);
                $pop_pub_int = $this->input->post('popular_international', TRUE);
                $pop_pub_nat = $this->input->post('popular_national', TRUE);
                $pre_pub_int = $this->input->post('presentation_international', TRUE);
                $pre_pub_nat = $this->input->post('presentation_national', TRUE);
                $noof_project = $this->input->post('noofproject', TRUE);
                $fund_outly_ofproject = $this->input->post('fund', TRUE);
                $tr_att_int = $this->input->post('training_attend_international', TRUE);
                $tr_att_nat = $this->input->post('training_attend_national', TRUE);
                $tr_con_int = $this->input->post('training_conducted_international', TRUE);
                $tr_con_nat = $this->input->post('training_conducted_national', TRUE);
                $mvsc_stu_guided = $this->input->post('mvsc', TRUE);
                $phd_stu_guided = $this->input->post('phd', TRUE);
                $others_stu_guided = $this->input->post('others', TRUE);
                $no_ofguestlecture = $this->input->post('guestlect', TRUE);
                $per_filename = $this->input->post('userfile', TRUE);
                //echo 'test=='.$edpref_data['performancedata']->spd_int_award."hhhh===".$int_award;
                //die;
                /*log messages*/
                
                $logmessage = "";
                if($edpref_data['performancedata']->spd_int_award != $int_award)
                    $logmessage = "Add Staff Performance Data " .$edpref_data['performancedata']->spd_int_award. " changed by " .$int_award;
                if($edpref_data['performancedata']->spd_nat_award != $nat_award)
                        $logmessage = "Add Staff Performance Data " .$edpref_data['performancedata']->spd_nat_award. " changed by " .$nat_award;
                if($edpref_data['performancedata']->spd_uni_award != $uni_award)
                        $logmessage = "Add Staff Performance Data " .$edpref_data['performancedata']->spd_uni_award. " changed by " .$uni_award;
                if($edpref_data['performancedata']->spd_res_pub_int != $res_pub_int)
                    $logmessage = "Add Staff Performance Data " .$edpref_data['performancedata']->spd_res_pub_int. " changed by " .$res_pub_int;
                if($edpref_data['performancedata']->spd_res_pub_nat != $res_pub_nat)
                        $logmessage = "Add Staff Performance Data " .$edpref_data['performancedata']->spd_res_pub_nat. " changed by " .$res_pub_nat;
                if($edpref_data['performancedata']->spd_pop_pub_int != $pop_pub_int)
                        $logmessage = "Add Staff Performance Data " .$edpref_data['performancedata']->spd_pop_pub_int. " changed by " .$pop_pub_int;
                if($edpref_data['performancedata']->spd_pop_pub_nat != $pop_pub_nat)
                        $logmessage = "Add Staff Performance Data " .$edpref_data['performancedata']->spd_pop_pub_nat. " changed by " .$pop_pub_nat;
                if($edpref_data['performancedata']->spd_pre_pub_int != $pre_pub_int)
                        $logmessage = "Add Staff Performance Data " .$edpref_data['performancedata']->spd_pre_pub_int. " changed by " .$pre_pub_int;
                if($edpref_data['performancedata']->spd_pre_pub_nat != $pre_pub_nat)
                        $logmessage = "Add Staff Performance Data " .$edpref_data['performancedata']->spd_pre_pub_nat. " changed by " .$pre_pub_nat;
                if($edpref_data['performancedata']->spd_noof_project != $noof_project)
                        $logmessage = "Add Staff Performance Data " .$edpref_data['performancedata']->spd_noof_project. " changed by " .$noof_project;
                if($edpref_data['performancedata']->spd_fund_outly_ofproject != $fund_outly_ofproject)
                        $logmessage = "Add Staff Performance Data " .$edpref_data['performancedata']->spd_fund_outly_ofproject. " changed by " .$fund_outly_ofproject;
                if($edpref_data['performancedata']->spd_tr_att_int != $tr_att_int)
                        $logmessage = "Add Staff Performance Data " .$edpref_data['performancedata']->spd_tr_att_int. " changed by " .$tr_att_int;
                if($edpref_data['performancedata']->spd_tr_att_nat != $tr_att_nat)
                        $logmessage = "Add Staff Performance Data " .$edpref_data['performancedata']->spd_tr_att_nat. " changed by " .$tr_att_nat;
                if($edpref_data['performancedata']->spd_tr_con_int != $spd_tr_con_int)
                        $logmessage = "Add Staff Performance Data " .$edpref_data['performancedata']->spd_tr_con_int. " changed by " .$tr_con_int;
                if($edpref_data['performancedata']->spd_tr_con_nat != $tr_con_nat)
                        $logmessage = "Add Staff Performance Data " .$edpref_data['performancedata']->spd_tr_con_nat. " changed by " .$tr_con_nat;
                if($edpref_data['performancedata']->spd_mvsc_stu_guided != $mvsc_stu_guided)
                        $logmessage = "Add Staff Performance Data " .$edpref_data['performancedata']->spd_mvsc_stu_guided. " changed by " .$mvsc_stu_guided;
                if($edpref_data['performancedata']->spd_phd_stu_guided != $phd_stu_guided)
                        $logmessage = "Add Staff Performance Data " .$edpref_data['performancedata']->spd_phd_stu_guided. " changed by " .$phd_stu_guided;
                if($edpref_data['performancedata']->spd_others_stu_guided != $others_stu_guided)
                        $logmessage = "Add Staff Performance Data " .$edpref_data['performancedata']->spd_others_stu_guided. " changed by " .$others_stu_guided;
                if($edpref_data['performancedata']->spd_no_ofguestlecture != $no_ofguestlecture)
                        $logmessage = "Add Staff Performance Data " .$edpref_data['performancedata']->spd_no_ofguestlecture. " changed by " .$no_ofguestlecture;
                if($edpref_data['performancedata']->spd_per_filename != $per_filename)
                        $logmessage = "Add Staff Performance Data " .$edpref_data['performancedata']->spd_per_filename. " changed by " .$per_filename;
                
                $new_name='';
                if(!empty($_FILES['userfile']['name'])){
                    
                    $newFileName = $_FILES['userfile']['name'];
                    $fileExt1 = explode('.', $newFileName);
                    $file_ext = end( $fileExt1);
                    $new_name = $id.".".$file_ext; 
                }    
                $update_data = array(

                    'spd_int_award'            =>$int_award,
                    'spd_nat_award'            =>$nat_award,
                    'spd_uni_award'            =>$uni_award,
                    'spd_res_pub_int'          =>$res_pub_int,
                    'spd_res_pub_nat'          =>$res_pub_nat,
                    'spd_pop_pub_int'          =>$pop_pub_int,
                    'spd_pop_pub_nat'          =>$pop_pub_nat,
                    'spd_pre_pub_int'          =>$pre_pub_int,
                    'spd_pre_pub_nat'          =>$pre_pub_nat,
                    'spd_noof_project'         =>$noof_project,
                    'spd_fund_outly_ofproject' =>$fund_outly_ofproject,
                    'spd_tr_att_int'           =>$tr_att_int,
                    'spd_tr_att_nat'           =>$tr_att_nat,
                    'spd_tr_con_int'           =>$tr_con_int,
                    'spd_tr_con_nat'           =>$tr_con_nat,
                    'spd_mvsc_stu_guided'      =>$mvsc_stu_guided,
                    'spd_phd_stu_guided'       =>$phd_stu_guided,
                    'spd_others_stu_guided'    =>$others_stu_guided,
                    'spd_no_ofguestlecture'    =>$no_ofguestlecture,
                    'spd_per_filename'         =>$new_name,
                );
                //echo "upload===".$update_data;
                //die;
                $msgfile='';
                if(!empty($_FILES['userfile']['name'])){
                    
                    $config['upload_path'] = 'uploads/SIS/perfattachment/';
                    $config['max_size'] = '20480000';
                    $config['allowed_types'] = 'pdf';
                    $config['file_name'] = $new_name;
                    $config['overwrite'] = TRUE;
                    $this->load->library('upload',$config);
                    if(! $this->upload->do_upload()){
                        $ferror='';
                        $error = array('error' => $this->upload->display_errors()); 
                        foreach ($error as $item => $value):
                        $ferror = $ferror ."</br>". $item .":". $value;
                        endforeach;
                        $simsg = "The permitted size of file is 20MB";
                        $ferror = $simsg.$ferror;
                        $this->logger->write_logmessage("uploadfile","file upload error", $ferror);
                        $this->logger->write_dblogmessage("uploadfile","file upload error", $ferror);
                        $this->session->set_flashdata('err_message', $ferror);
                        $this->load->view('empmgmt/editextstaffpro', $edpref_data);
                      
                    }
                    else { 
                        $upload_data=$this->upload->data();
                        $msgfile=" and Attachment" ;
                    } 
            
                }//userfileifcond
                $spdflag=$this->sismodel->updaterec('Staff_Performance_Data', $update_data, 'spd_empid', $id);
                if(!$spdflag)
                {
                    $this->logger->write_logmessage("error","Error in Staff Performance Data  update  ", "Error in Staff Performance Data  updation. $logmessage ." );
                    $this->logger->write_dblogmessage("error","Error in update Staff Performance Data ", "Error in Staff Performance Data updation. $logmessage ." );
                    $this->session->set_flashdata('err_message','Error in Staff Performance Data updation');
                    $this->load->view('empmgmt/editextstaffpro',$edpref_data);
                }
                else{
                    $this->roleid=$this->session->userdata('id_role');
                    $empcode=$this->sismodel->get_listspfic1('employee_master','emp_code','emp_id',$id)->emp_code;
                    $empemail=$this->sismodel->get_listspfic1('employee_master','emp_email','emp_id',$id)->emp_email;
                    $this->logger->write_logmessage("update","Edit Staff Performance Data", "Staff Performance Data updated successfully. $logmessage ." );
                    $this->logger->write_dblogmessage("update","Edit Staff Performance Data", "Staff Performance Data updated successfully. $logmessage ." );
                    $this->session->set_flashdata('success','Staff Performance Data ' .$msgfile.' updated successfully...'."["." "."Employee PF NO:"." ".$empcode." and "."Username:"." ".$empemail." "."]");
                    if($this->roleid == 4){
                        redirect('empmgmt/viewempprofile');
                    }
                    else{
                        //$this->load->view('report/viewfull_profile');
                        redirect('report/viewfull_profile/'.$id);
                    }
                                        
                }

            }//elseformvalidation

            
        }//closeissetform 
    }//function close
    
    /***********************************Start Add service detail******************************************/
    public function add_servicedata($empid) {
        $this->roleid=$this->session->userdata('id_role');
        $this->emp_id = $empid;
        $this->orgcode=$this->commodel->get_listspfic1('org_profile','org_code','org_id',1)->org_code;
        //$this->campus=$this->commodel->get_listspfic2('study_center','sc_code','sc_name','org_code',$this->orgcode);
	$this->campus=$this->commodel->get_listspfic2('study_center','sc_id','sc_name','org_code',$this->orgcode);
       //$this->desig= $this->commodel->get_listspfic2('designation','desig_code','desig_name');
        $this->salgrd=$this->sismodel->get_list('salary_grade_master');
 

        if(isset($_POST['addservdata'])) {	
            //form validation
            $this->form_validation->set_rules('campus','Campus','trim|required|xss_clean');
	    $this->form_validation->set_rules('uocontrol','UniversityOfficerControl','trim|xss_clean');
            $this->form_validation->set_rules('department','Department','trim|xss_clean');
	    $this->form_validation->set_rules('schemecode','Scheme Name','trim|xss_clean');
	    $this->form_validation->set_rules('ddo','Drawing and Disbursing Officer','trim|xss_clean');
	    $this->form_validation->set_rules('workingtype','Workingtype','trim|required|xss_clean');
	    $this->form_validation->set_rules('group','Group','trim|xss_clean');
            $this->form_validation->set_rules('designation','Designation','trim|required|xss_clean');
	    $this->form_validation->set_rules('emppost','Shown Against The Post','trim|required|xss_clean');
	    $this->form_validation->set_rules('level','Level','trim|required|xss_clean');
            $this->form_validation->set_rules('payband','PayBand','required|xss_clean');
            $this->form_validation->set_rules('DateofAGP','Date of AGP','trim|xss_clean');
            $this->form_validation->set_rules('gradepay','Grade Pay','trim|xss_clean');
            $this->form_validation->set_rules('Datefrom','Date From','trim|xss_clean|required');
            $this->form_validation->set_rules('Dateto','Date To','trim|xss_clean');
            if($this->form_validation->run() == FALSE){
                
                redirect('empmgmt/add_sevicedata');
            }//formvalidation
            else{
                $data = array(
                    'empsd_empid'           =>$empid,
                    'empsd_campuscode'      =>$_POST['campus'],
                    'empsd_ucoid'           =>$_POST['uocontrol'],
                    'empsd_deptid'          =>$_POST['department'],
                    'empsd_schemeid'        =>$_POST['schemecode'],
                    'empsd_ddoid'           =>$_POST['ddo'],
		    'empsd_worktype'        =>$_POST['workingtype'],
                    'empsd_group'           =>$_POST['group'],
                    'empsd_desigcode'       =>$_POST['designation'],
		    'empsd_shagpstid'       =>$_POST['emppost'],
		    'empsd_level'           =>$_POST['level'],
                    'empsd_pbid'            =>$_POST['payband'],
                    'empsd_gradepay'        =>$_POST['gradepay'],
                    'empsd_pbdate'          =>$_POST['DateofAGP'],
                    'empsd_dojoin'          =>$_POST['Datefrom'],
                    'empsd_dorelev'         =>$_POST['Dateto']
                );
                $servdataflag=$this->sismodel->insertrec('employee_servicedetail', $data) ;
                if(!$servdataflag)
                {
                    $this->logger->write_logmessage("error","Error in insert staff service record", "Error in insert staff service record." );
                    $this->logger->write_dblogmessage("error","Error in insert staff service record ", "Error in insert staff service record" );
                    $this->session->set_flashdata('err_message','Error in insert staff service record ');
                    $this->load->view('empmgmt/add_servicedata',$data);
                }
                else{
                    $this->roleid=$this->session->userdata('id_role');
                    $empcode=$this->sismodel->get_listspfic1('employee_master','emp_code','emp_id',$empid)->emp_code;
                    $empemail=$this->sismodel->get_listspfic1('employee_master','emp_email','emp_id',$empid)->emp_email;
                    $this->logger->write_logmessage("insert","Add Staff Service Data", "Staff Service record insert successfully." );
                    $this->logger->write_dblogmessage("insert","Add Staff Service Data", "Staff Service record insert successfully ." );
                    $this->session->set_flashdata('success','Service Data record insert successfully.'."["." "."Employee PF NO:"." ".$empcode." and "."Username:"." ".$empemail." "."]");
                    if($this->roleid == 4){
                        redirect('empmgmt/viewempprofile');
                    }
                    else{
                        redirect('report/viewfull_profile/'.$empid);
                    }
                                       
                }
            }//else
           
        }//ifpost button
        $this->load->view('empmgmt/add_servicedata');
    }//function close
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
         json_encode("in controller====".$aucoid->scuo_uoid);
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

/* This function has been created for get gradepay on the basis of payband */

    public function getgrade(){
        $combid = $this->input->post('payband');
        $parts = explode(',',$combid);
	$sgmid = $parts[0];
	$list = $this->sismodel->get_listspfic2('salary_grade_master','','sgm_gradepay','sgm_id',$sgmid,'sgm_gradepay');
		foreach($list as $datas): 
		$this->grade = $datas->sgm_gradepay;
			echo json_encode($this->grade);
  		endforeach;
    }

    /*get employee service detail*/
    
    public function edit_servicedata($id) {
        $this->roleid=$this->session->userdata('id_role');
       /* $this->roleid=$this->session->userdata('id_role');
        $this->emp_id = $empid;*/
        $this->orgcode=$this->commodel->get_listspfic1('org_profile','org_code','org_id',1)->org_code;
        $this->campus=$this->commodel->get_listspfic2('study_center','sc_id','sc_name','org_code',$this->orgcode);
        $this->desig= $this->commodel->get_listspfic2('designation','desig_code','desig_name');
        $this->salgrd=$this->sismodel->get_list('salary_grade_master');
        $data['id'] = $id;
        $data['servicedata'] = $this->sismodel->get_listrow('employee_servicedetail','empsd_id',$id)->row();
        $this->load->view('empmgmt/edit_servicedata',$data);
        
    }
    /****************************  START UPDATE DATA *************************/
    public function update_servicedata($id){
        $this->roleid=$this->session->userdata('id_role');
        $sperf_dataquery=$this->sismodel->get_listrow('employee_servicedetail','empsd_id', $id);
        $eds_data['servicedata'] = $sperf_dataquery->row();
        if(isset($_POST['editservdata'])) {
            //form validation
            $this->form_validation->set_rules('campus','Campus','trim|required|xss_clean');
	    $this->form_validation->set_rules('uocontrol','University Officer Control','trim|xss_clean');
	    $this->form_validation->set_rules('department','Department','trim|xss_clean');
            $this->form_validation->set_rules('schemecode','Scheme Name','trim|xss_clean');
	    $this->form_validation->set_rules('ddo','Drawing and Disbursing Officer','trim|xss_clean');
	    $this->form_validation->set_rules('workingtype','Workingtype','trim|xss_clean');
	    $this->form_validation->set_rules('group','Group','trim|xss_clean');
            $this->form_validation->set_rules('designation','Designation','trim|required|xss_clean');
	    $this->form_validation->set_rules('emppost','Shown Against The Post','trim|required|xss_clean');
	    $this->form_validation->set_rules('level','Level','trim|required|xss_clean');
            $this->form_validation->set_rules('payband','PayBand','trim|required|xss_clean');
            $this->form_validation->set_rules('gradepay','Grade Pay','trim|xss_clean');
            $this->form_validation->set_rules('DateofAGP','Date of AGP','trim|xss_clean');
            $this->form_validation->set_rules('Datefrom','Date From','trim|xss_clean|required');
            $this->form_validation->set_rules('Dateto','Date To','trim|xss_clean');
            if($this->form_validation->run() == FALSE){
                
                redirect('empmgmt/edit_sevicedata');
            }//formvalidation
            else{
                $campus = $this->input->post('campus', TRUE);
		$uocontrol=$this->input->post('uocontrol', TRUE);
                $department=$this->input->post('department', TRUE);
		$schemecode = $this->input->post('schemecode', TRUE);
                $ddo=$this->input->post('ddo', TRUE);
		$worktype=$this->input->post('workingtype', TRUE);
                $group=$this->input->post('group', TRUE);
                $desigc = $this->input->post('designation', TRUE);
		$emppost = $this->input->post('emppost', TRUE);
		$level = $this->input->post('level', TRUE);
                $payb = $this->input->post('payband', TRUE);
                $gradepay = $this->input->post('gradepay', TRUE);
                $dataofagp = $this->input->post('DateofAGP', TRUE);
                $datefrom = $this->input->post('Datefrom', TRUE);
                $dateto = $this->input->post('Dateto', TRUE);
                
                $logmessage = "";
                if($eds_data['servicedata']->empsd_campuscode != $campus)
                    $logmessage = "Edit Staff Service Data " .$eds_data['servicedata']->empsd_campuscode. " changed by " .$campus;
		if($eds_data['servicedata']->empsd_ucoid != $uocontrol)
                    $logmessage = "Edit Staff Service Data " .$eds_data['servicedata']->empsd_ucoid. " changed by " .$uocontrol;
		if($eds_data['servicedata']->empsd_deptid != $department)
                    $logmessage = "Edit Staff Service Data " .$eds_data['servicedata']->empsd_deptid. " changed by " .$department;
		 if($eds_data['servicedata']->empsd_schemeid != $schemecode)
                    $logmessage = "Edit Staff Service Data " .$eds_data['servicedata']->empsd_schemeid. " changed by " .$schemecode;
		if($eds_data['servicedata']->empsd_ddoid != $ddo)
                    $logmessage = "Edit Staff Service Data " .$eds_data['servicedata']->empsd_ddoid. " changed by " .$ddo;
		if($eds_data['servicedata']->empsd_worktype != $worktype)
                    $logmessage = "Edit Staff Service Data " .$eds_data['servicedata']->empsd_worktype. " changed by " .$worktype;
		if($eds_data['servicedata']->empsd_group != $group)
                    $logmessage = "Edit Staff Service Data " .$eds_data['servicedata']->empsd_group. " changed by " .$group;
                if($eds_data['servicedata']->empsd_desigcode != $desigc)
                        $logmessage = "Edit Staff Service Data " .$eds_data['servicedata']->empsd_desigcode. " changed by " .$desigc;
		if($eds_data['servicedata']-> empsd_shagpstid != $emppost)
                        $logmessage = "Edit Staff Service Data " .$eds_data['servicedata']->empsd_shagpstid. " changed by " .$emppost;
		if($eds_data['servicedata']->empsd_level != $level)
                        $logmessage = "Edit Staff Service Data " .$eds_data['servicedata']->empsd_level. " changed by " .$level;
                if($eds_data['servicedata']->empsd_pbid != $payb)
                        $logmessage = "Edit Staff Service Data " .$eds_data['servicedata']->empsd_pbid. " changed by " .$payb;
                if($eds_data['servicedata']->empsd_gradepay != $gradepay)
                        $logmessage = "Edit Staff Service Data " .$eds_data['servicedata']->empsd_gradepay. " changed by " .$gradepay;
                if($eds_data['servicedata']->empsd_pbdate != $dataofagp)
                    $logmessage = "Edit Staff Service Data " .$eds_data['servicedata']->empsd_pbdate. " changed by " .$dataofagp;
                if($eds_data['servicedata']->empsd_dojoin != $datefrom)
                        $logmessage = "Edit Staff Service Data " .$eds_data['servicedata']->empsd_dojoin. " changed by " .$datefrom;
                if($eds_data['servicedata']->empsd_dorelev != $dateto)
                        $logmessage = "Edit Staff Service Data " .$eds_data['servicedata']->empsd_dorelev. " changed by " .$dateto;
                
                $edit_data = array(
                    'empsd_campuscode'      =>$campus,
		    'empsd_ucoid'           =>$uocontrol,
                    'empsd_deptid'          =>$department,
		    'empsd_schemeid'        =>$schemecode,
		    'empsd_ddoid'           =>$ddo,
		    'empsd_worktype'        =>$worktype,
		    'empsd_group'           =>$group,
                    'empsd_desigcode'       =>$desigc,
		    'empsd_shagpstid'       =>$emppost,
		    'empsd_level'           =>$level,
                    'empsd_pbid'            =>$payb,
                    'empsd_gradepay'        =>$gradepay,
                    'empsd_pbdate'          =>$dataofagp,
                    'empsd_dojoin'          =>$datefrom,
                    'empsd_dorelev'         =>$dateto
                );
                $empserviceflag=$this->sismodel->updaterec('employee_servicedetail', $edit_data, 'empsd_id', $id);
                if(!$empserviceflag)
                {
                    $this->logger->write_logmessage("error","Error in update staff service record ", "Error in  update service record. $logmessage ." );
                    $this->logger->write_dblogmessage("error","Error in update staff service record ", "Error in update staff service record. $logmessage ." );
                    $this->session->set_flashdata('err_message','Error in update staff service record');
                    $this->load->view('empmgmt/edit_servicedata',$eds_data);
                }
                else{
                    $this->roleid=$this->session->userdata('id_role');
                    $this->currentlog=$this->session->userdata('username');
                    $empcode=$this->sismodel->get_listspfic1('employee_master','emp_code','emp_email',$this->currentlog)->emp_code;
                    $this->logger->write_logmessage("update","Edit Staff Service Data", "Staff Service Data updated successfully. $logmessage ." );
                    $this->logger->write_dblogmessage("update","Edit Staff service Data", "Staff Service Data updated successfully. $logmessage ." );
                    $this->session->set_flashdata('success','Service record updated successfully. '."["." "."Employee PF NO:"." ".$empcode." and "."Username:"." ".$this->currentlog." "."]");
                    
                    if($this->roleid == 4){
                        redirect('empmgmt/viewempprofile');
                    }
                    else{
			$uid = $this->sismodel->get_listspfic1('employee_servicedetail','empsd_empid','empsd_id',$id)->empsd_empid;
                        redirect('report/viewfull_profile/'.$uid);
                    }
                                        
                }
                
            }//formtrue
         
        }   
    }
    /****************************  Closer UPDATE DATA *************************/
                                                
}//classcloser    
    
    
