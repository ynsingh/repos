<?php

defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name Empffmgmt.php
 * @author Manorama Pal (palseema30@gmail.com) Employee Profile
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
        $data['servicedata'] = $this->sismodel->get_listrow('employee_servicedetail','empsd_empid',$emp_id);
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
            $this->form_validation->set_rules('national','National','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('international','International','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('university','University','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('research_national','No.of Research at National','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('research_international','No.of Research at International','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('popular_national','No.of Popular at National','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('popular_international','No.of Popular at International','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('presentation_national','No.of Presentation at National','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('presentation_international','No.of Presentation at International','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('noofproject','No.of Project Handled','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('fund','No.of Fund Outlay','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('training_attend_national','No.of Training Attended at National','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('training_attend_international','No.of Training Attended at International','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('training_conducted_national','No.of Training  Conducted at National','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('training_conducted_international','No.of Training Conducted at International','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('mvsc','No.of Students at MVSc','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('phd','No.of Students at PhD','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('others','No.of Students at Others','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('guestlect','No.of Guest Lecture','trim|xss_clean|required|numeric');
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
                    $config['max_size'] = '2048000';
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
                        $simsg = "The permitted size of document is 200kb";
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
            $this->form_validation->set_rules('national','National','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('international','International','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('university','University','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('research_national','No.of Research at National','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('research_international','No.of Research at International','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('popular_national','No.of Popular at National','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('popular_international','No.of Popular at International','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('presentation_national','No.of Presentation at National','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('presentation_international','No.of Presentation at International','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('noofproject','No.of Project Handled','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('fund','No.of Fund Outlay','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('training_attend_national','No.of Training Attended at National','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('training_attend_international','No.of Training Attended at International','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('training_conducted_national','No.of Training  Conducted at National','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('training_conducted_international','No.of Training Conducted at International','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('mvsc','No.of Students at MVSc','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('phd','No.of Students at PhD','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('others','No.of Students at Others','trim|xss_clean|required|numeric');
            $this->form_validation->set_rules('guestlect','No.of Guest Lecture','trim|xss_clean|required|numeric');
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
                    $config['max_size'] = '2048000';
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
                        $simsg = "The permitted size of file is 100kb";
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
                                                
}//classcloser    
    
    
