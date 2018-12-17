<?php

defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name Empffmgmt.php
 * @author Nagendra Kumar Singh(nksinghiitk@gmail.com) 
 * @author Manorama Pal (palseema30@gmail.com) Employee Profile, Service and Performance data, Academic qualification, technical qualification .
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

	$cdate = date('Y-m-d');
	$this->headflag="false";
	$empcode =$this->sismodel->get_listspfic1('employee_master','emp_code','emp_email', $currentuser)->emp_code;
	$hwdata = array('hl_empcode' =>$empcode, 'hl_dateto >=' =>$cdate );
	$this->headflag=$this->sismodel->isduplicatemore("hod_list",$hwdata);

	$fieldems="ems_empid,ems_vci_status,ems_vci_statchapter,ems_vci_statregno,ems_vci_statregdate,ems_vci_statvaliddate,ems_vci_alliregno,ems_vci_alliregdate,ems_vci_allivaliddate";
        $whdataems = array ('ems_empid' => $emp_id);
        $whorderems = '';
        $data['emsdata'] = $this->sismodel->get_orderlistspficemore('employee_master_support',$fieldems,$whdataems,$whorderems);


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

public function academic_profile() {

	//get id for employee to show data      
        $currentuser=$this->session->userdata('username');
        $emp_id = $this->sismodel->get_listspfic1('employee_master','emp_id','emp_email', $currentuser)->emp_id;
        $emp_data['emp_id']=$emp_id;

        //for adding head next to designation
        $cdate=date('Y-m-d');
        $this->headflag="false";
        $empcode =$this->sismodel->get_listspfic1('employee_master','emp_code','emp_id', $emp_id)->emp_code;
        $hwdata = array('hl_empcode' =>$empcode, 'hl_dateto >=' =>$cdate );
        $this->headflag=$this->sismodel->isduplicatemore("hod_list",$hwdata);


        //get all profile and service data
        $emp_data['data'] = $this->sismodel->get_listrow('employee_master','emp_id',$emp_id)->row();
        $selectfield="*";
        $whdata = array ('saq_empid' => $emp_id, 'saq_dgree LIKE'=> 'B%') ;
        $emp_data['ugraduate'] = $this->sismodel->get_orderlistspficemore('staff_academic_qualification',$selectfield,$whdata,'');

        $whdata = array ('saq_empid' => $emp_id, 'saq_dgree LIKE'=> 'M%') ;
        $emp_data['masters'] = $this->sismodel->get_orderlistspficemore('staff_academic_qualification',$selectfield,$whdata,'');
        //$str='B%,M%';
        $whdata = array ('saq_empid' => $emp_id,'saq_dgree NOT LIKE ' => 'B%','saq_dgree NOT LIKE ' => 'M%');
        $emp_data['schooledu'] = $this->sismodel->get_orderlistspficemore('staff_academic_qualification',$selectfield,$whdata,'');

        $whdata = array ('saq_empid' => $emp_id,'saq_dgree LIKE ' => 'P%','saq_dgree NOT LIKE ' => '%Diploma');
        $emp_data['doctrate'] = $this->sismodel->get_orderlistspficemore('staff_academic_qualification',$selectfield,$whdata,'');

        $whdata = array ('saq_empid' => $emp_id,'saq_dgree LIKE ' => '%Diploma');
        $emp_data['diploma'] = $this->sismodel->get_orderlistspficemore('staff_academic_qualification',$selectfield,$whdata,'');

	$this->load->view('empmgmt/academicprofile',$emp_data);
    }

public function technical_profile() {
	 //get id for employee to show data      
        $currentuser=$this->session->userdata('username');
        $emp_id = $this->sismodel->get_listspfic1('employee_master','emp_id','emp_email', $currentuser)->emp_id;
        $emp_data['emp_id']=$emp_id;
	
	//for adding head next to designation
        $cdate=date('Y-m-d');
        $this->headflag="false";
        $empcode =$this->sismodel->get_listspfic1('employee_master','emp_code','emp_id', $emp_id)->emp_code;
        $hwdata = array('hl_empcode' =>$empcode, 'hl_dateto >=' =>$cdate );
        $this->headflag=$this->sismodel->isduplicatemore("hod_list",$hwdata);

        //get all profile and service data
        $emp_data['data'] = $this->sismodel->get_listrow('employee_master','emp_id',$emp_id)->row();
        $selectfield="*";
        $whdata = array ('stq_empid' => $emp_id) ;
        $emp_data['technical'] = $this->sismodel->get_orderlistspficemore('staff_technical_qualification',$selectfield,$whdata,'');

	$this->load->view('empmgmt/technicalprofile',$emp_data);
  }

	

public function service_profile() {

        //get id for employee to show data      
	$currentuser=$this->session->userdata('username');
	$emp_id = $this->sismodel->get_listspfic1('employee_master','emp_id','emp_email', $currentuser)->emp_id;
//        $emp_id = $this->uri->segment(3);
        $emp_data['emp_id']=$emp_id;

        //for adding head next to designation
        $cdate=date('Y-m-d');
        $this->headflag="false";
        $empcode =$this->sismodel->get_listspfic1('employee_master','emp_code','emp_id', $emp_id)->emp_code;
        $hwdata = array('hl_empcode' =>$empcode, 'hl_dateto >=' =>$cdate );
        $this->headflag=$this->sismodel->isduplicatemore("hod_list",$hwdata);

        //get all profile and service data
        $emp_data['data'] = $this->sismodel->get_listrow('employee_master','emp_id',$emp_id)->row();
        $selectfield="*";
        $whdata = array ('empsd_empid' => $emp_id);
        $whorder = 'empsd_dojoin desc';
        $emp_data['servicedata'] = $this->sismodel->get_orderlistspficemore('employee_servicedetail',$selectfield,$whdata,$whorder);

        $this->load->view('empmgmt/service_profile',$emp_data);
  }

public function performance_profile() {

        //get id for employee to show data      
  //      $emp_id = $this->uri->segment(3);
	$currentuser=$this->session->userdata('username');
        $emp_id = $this->sismodel->get_listspfic1('employee_master','emp_id','emp_email', $currentuser)->emp_id;
        $emp_data['emp_id']=$emp_id;

        //for adding head next to designation
        $cdate=date('Y-m-d');
        $this->headflag="false";
        $empcode =$this->sismodel->get_listspfic1('employee_master','emp_code','emp_id', $emp_id)->emp_code;
        $hwdata = array('hl_empcode' =>$empcode, 'hl_dateto >=' =>$cdate );
        $this->headflag=$this->sismodel->isduplicatemore("hod_list",$hwdata);

        //get all profile and service data
        $emp_data['data'] = $this->sismodel->get_listrow('employee_master','emp_id',$emp_id)->row();
        $selectfield="*";
        $whdata = array ('empsd_empid' => $emp_id);
        $emp_data['performancedata'] = $this->sismodel->get_listrow('Staff_Performance_Data','spd_empid',$emp_id)->row();

        $this->load->view('empmgmt/performance_profile',$emp_data);
  }
public function leave_profile() {

        //get id for employee to show data      
	$currentuser=$this->session->userdata('username');
        $emp_id = $this->sismodel->get_listspfic1('employee_master','emp_id','emp_email', $currentuser)->emp_id;
        $emp_data['emp_id']=$emp_id;

        //for adding head next to designation
        $cdate=date('Y-m-d');
        $this->headflag="false";
        $empcode =$this->sismodel->get_listspfic1('employee_master','emp_code','emp_id', $emp_id)->emp_code;
        $hwdata = array('hl_empcode' =>$empcode, 'hl_dateto >=' =>$cdate );
        $this->headflag=$this->sismodel->isduplicatemore("hod_list",$hwdata);

        //get all profile and service data
        $emp_data['data'] = $this->sismodel->get_listrow('employee_master','emp_id',$emp_id)->row();
        //$empuserid =$this->sismodel->get_listspfic1('employee_master','emp_userid','emp_id', $emp_id)->emp_userid;
        $selectfield="la_id,la_type,granted_la_from_date,granted_la_to_date,la_taken,la_year,la_upfile";
        $whdata = array ('la_userid' => $emp_id,'la_status' =>'1');
        $whorder = "la_type asc,la_year desc";
//      get the id of these leave type
//      $orwhin = array('UEL on ML', 'EL', 'METERNITY LEAVE','EOL');
        $leaveid1 =$this->sismodel->get_listspfic1('leave_type_master','lt_id','lt_name', 'Unearned Leave on Medical Leave')->lt_id;
        $leaveid2 =$this->sismodel->get_listspfic1('leave_type_master','lt_id','lt_name', 'Earned Leave')->lt_id;
        $leaveid3 =$this->sismodel->get_listspfic1('leave_type_master','lt_id','lt_name', 'Meternity Leave')->lt_id;
        $leaveid4 =$this->sismodel->get_listspfic1('leave_type_master','lt_id','lt_name', 'Extra Ordinary Leave')->lt_id;
        $orwhin = array($leaveid1,$leaveid2,$leaveid3,$leaveid4);
        //for leave perticular
      //  $emp_data['leavedata'] = $this->sismodel->get_orderlistspficemoreorwh('leave_apply',$selectfield,$whdata,'la_type',$orwhin,'');
        $emp_data['leavedata'] = $this->sismodel->get_orderlistspficemore('leave_apply',$selectfield,$whdata,$whorder);

        $this->load->view('empmgmt/leave_profile',$emp_data);
  }
public function deputation_profile() {

        //get id for employee to show data      
  //      $emp_id = $this->uri->segment(3);
	$currentuser=$this->session->userdata('username');
        $emp_id = $this->sismodel->get_listspfic1('employee_master','emp_id','emp_email', $currentuser)->emp_id;
        $emp_data['emp_id']=$emp_id;

        //for adding head next to designation
        $cdate=date('Y-m-d');
        $this->headflag="false";
        $empcode =$this->sismodel->get_listspfic1('employee_master','emp_code','emp_id', $emp_id)->emp_code;
        $hwdata = array('hl_empcode' =>$empcode, 'hl_dateto >=' =>$cdate );
        $this->headflag=$this->sismodel->isduplicatemore("hod_list",$hwdata);

        //get all profile and service data
        $emp_data['data'] = $this->sismodel->get_listrow('employee_master','emp_id',$emp_id)->row();
        $selectfield="*";
        $whdata = array ('sdp_empcode' => $empcode);
        $emp_data['deputdata'] = $this->sismodel->get_orderlistspficemore('staff_deputation_perticulars',$selectfield,$whdata,'');

        $this->load->view('empmgmt/deputation_profile',$emp_data);
  }
public function deptexam_profile() {

        //get id for employee to show data      
//        $emp_id = $this->uri->segment(3);
	$currentuser=$this->session->userdata('username');
        $emp_id = $this->sismodel->get_listspfic1('employee_master','emp_id','emp_email', $currentuser)->emp_id;
        $emp_data['emp_id']=$emp_id;

        //for adding head next to designation
        $cdate=date('Y-m-d');
        $this->headflag="false";
        $empcode =$this->sismodel->get_listspfic1('employee_master','emp_code','emp_id', $emp_id)->emp_code;
        $hwdata = array('hl_empcode' =>$empcode, 'hl_dateto >=' =>$cdate );
        $this->headflag=$this->sismodel->isduplicatemore("hod_list",$hwdata);

        //get all profile and service data
        $emp_data['data'] = $this->sismodel->get_listrow('employee_master','emp_id',$emp_id)->row();
        $selectfield="*";
        $whdata = array ('sdep_empcode' => $empcode);
        $emp_data['deptexamdata'] = $this->sismodel->get_orderlistspficemore('staff_department_exam_perticulars',$selectfield,$whdata,'');

        $this->load->view('empmgmt/deptexam_profile',$emp_data);
  }

public function workorder_profile() {

        //get id for employee to show data      
	$currentuser=$this->session->userdata('username');
        $emp_id = $this->sismodel->get_listspfic1('employee_master','emp_id','emp_email', $currentuser)->emp_id;
        $emp_data['emp_id']=$emp_id;

        //for adding head next to designation
        $cdate=date('Y-m-d');
        $this->headflag="false";
        $empcode =$this->sismodel->get_listspfic1('employee_master','emp_code','emp_id', $emp_id)->emp_code;
        $hwdata = array('hl_empcode' =>$empcode, 'hl_dateto >=' =>$cdate );
        $this->headflag=$this->sismodel->isduplicatemore("hod_list",$hwdata);

        //get all profile and service data
        $emp_data['data'] = $this->sismodel->get_listrow('employee_master','emp_id',$emp_id)->row();
        $selectfield="*";
        $whdata = array ('swap_empcode' => $empcode);
        $emp_data['workarrangdata'] = $this->sismodel->get_orderlistspficemore('staff_working_arrangements_perticulars',$selectfield,$whdata,'');

        $this->load->view('empmgmt/workorder_profile',$emp_data);
  }
public function recruit_profile() {

        //get id for employee to show data      
//        $emp_id = $this->uri->segment(3);
	$currentuser=$this->session->userdata('username');
        $emp_id = $this->sismodel->get_listspfic1('employee_master','emp_id','emp_email', $currentuser)->emp_id;
        $emp_data['emp_id']=$emp_id;

        //for adding head next to designation
        $cdate=date('Y-m-d');
        $this->headflag="false";
        $empcode =$this->sismodel->get_listspfic1('employee_master','emp_code','emp_id', $emp_id)->emp_code;
        $hwdata = array('hl_empcode' =>$empcode, 'hl_dateto >=' =>$cdate );
        $this->headflag=$this->sismodel->isduplicatemore("hod_list",$hwdata);

        //get all profile and service data
        $emp_data['data'] = $this->sismodel->get_listrow('employee_master','emp_id',$emp_id)->row();
        $selectfield="*";
        $whdata = array ('srp_empcode' => $empcode);
        $emp_data['recruitdata'] = $this->sismodel->get_orderlistspficemore('staff_recruitment_perticulars',$selectfield,$whdata,'');
        $this->load->view('empmgmt/recruit_profile',$emp_data);
  }
public function disciplin_profile() {

        //get id for employee to show data      
//        $emp_id = $this->uri->segment(3);
	$currentuser=$this->session->userdata('username');
        $emp_id = $this->sismodel->get_listspfic1('employee_master','emp_id','emp_email', $currentuser)->emp_id;
        $emp_data['emp_id']=$emp_id;

        //for adding head next to designation
        $cdate=date('Y-m-d');
        $this->headflag="false";
        $empcode =$this->sismodel->get_listspfic1('employee_master','emp_code','emp_id', $emp_id)->emp_code;
        $hwdata = array('hl_empcode' =>$empcode, 'hl_dateto >=' =>$cdate );
        $this->headflag=$this->sismodel->isduplicatemore("hod_list",$hwdata);

        //get all profile and service data
        $emp_data['data'] = $this->sismodel->get_listrow('employee_master','emp_id',$emp_id)->row();
        $selectfield="*";
        $whdata = array ('sdap_empcode' => $empcode);
        $emp_data['disciplinactdata'] = $this->sismodel->get_orderlistspficemore('staff_disciplinary_actions_perticulars',$selectfield,$whdata,'');
        $this->load->view('empmgmt/disciplin_profile',$emp_data);
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
                        redirect("report/performance_profile/".$empid);
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
                       redirect("report/performance_profile/".$empid); 
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
                        redirect('report/performance_profile/'.$id);
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
	    $this->form_validation->set_rules('emppost','Shown Against The Post','trim|xss_clean');
	    $this->form_validation->set_rules('level','Level','trim|xss_clean');
            $this->form_validation->set_rules('payband','PayBand','trim|xss_clean');
            $this->form_validation->set_rules('DateofAGP','Date of AGP','trim|xss_clean');
            $this->form_validation->set_rules('gradepay','Grade Pay','trim|xss_clean');
            $this->form_validation->set_rules('orderno','Order No','trim|xss_clean');
            $this->form_validation->set_rules('Datefrom','Date From','trim|xss_clean|required');
            $this->form_validation->set_rules('Dateto','Date To','trim|xss_clean');
            $this->form_validation->set_rules('userfile','Select File','trim|xss_clean');
            if($this->form_validation->run() == FALSE){
                
                redirect('empmgmt/add_sevicedata');
            }//formvalidation
            else{
		 $name='';
                if(!empty($_FILES['userfile']['name'])){

                    $newFileName = $_FILES['userfile']['name'];
                    $fileExt1 = explode('.', $newFileName);
                    $file_ext = end( $fileExt1);
                    $name = $empid."_".$newFileName;
                }

		$desigcode=$this->commodel->get_listspfic1('designation','desig_code','desig_id',$_POST['designation'])->desig_code;
		if(empty($_POST['level'])){
			$level='';
		}else{
			$level=$_POST['level'];
		}
		if(empty($_POST['payband'])){
			$payb='';
		}else{
			$payb=$_POST['payband'];
		}
		if(empty($_POST['tsession'])){
			$ts='';
		}else{
			$ts=$_POST['tsession'];
		}
		
                $data = array(
                    'empsd_empid'           =>$empid,
                    'empsd_campuscode'      =>$_POST['campus'],
                    'empsd_ucoid'           =>$_POST['uocontrol'],
                    'empsd_deptid'          =>$_POST['department'],
                    'empsd_schemeid'        =>$_POST['schemecode'],
                    'empsd_ddoid'           =>$_POST['ddo'],
		    'empsd_worktype'        =>$_POST['workingtype'],
                    'empsd_group'           =>$_POST['group'],
                    'empsd_desigcode'       =>$desigcode,
		    'empsd_shagpstid'       =>$_POST['emppost'],
		    'empsd_level'           =>$level,
                    'empsd_pbid'            =>$payb,
                    'empsd_gradepay'        =>$_POST['gradepay'],
                    'empsd_orderno'        =>$_POST['orderno'],
                    'empsd_pbdate'          =>$_POST['DateofAGP'],
                    'empsd_dojoin'          =>$_POST['Datefrom'],
		    'empsd_fsession'	    =>$_POST['fsession'],
		    'empsd_dorelev'         =>$_POST['Dateto'],
		    'empsd_tsession'	    =>$ts,
		    'empsd_filename'	    => $name,
                );
                $servdataflag=$this->sismodel->insertrec('employee_servicedetail', $data) ;
		$uplflag=false;
                if(!empty($name)){
                    $_FILES['userFile']['name'] = $_FILES['userfile']['name'];
                    $_FILES['userFile']['type'] = $_FILES['userfile']['type'];
                    $_FILES['userFile']['tmp_name'] = $_FILES['userfile']['tmp_name'];
                    $_FILES['userFile']['size'] = $_FILES['userfile']['size'];
                    $config['upload_path'] = 'uploads/SIS/serviceattachment/';
                    $config['max_size'] = '2048000000';
                    $config['allowed_types'] = 'pdf';
                    $config['file_name'] = $name;
                    $config['overwrite'] = TRUE;
                    $this->load->library('upload',$config);
                    $this->upload->initialize($config);

                    if($this->upload->do_upload('userfile')){
                        $uploadData = $this->upload->data();
                        $picture = $uploadData['file_name'];
                        $this->logger->write_logmessage("insert","upload staff service data file ", "Staff Service Data file attach successfully");
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
                        redirect('report/service_profile/'.$empid);
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
	    $this->form_validation->set_rules('emppost','Shown Against The Post','trim|xss_clean');
	    $this->form_validation->set_rules('level','Level','trim|xss_clean');
            $this->form_validation->set_rules('payband','PayBand','trim|xss_clean');
            $this->form_validation->set_rules('gradepay','Grade Pay','trim|xss_clean');
            $this->form_validation->set_rules('orderno','Order No','trim|xss_clean');
            $this->form_validation->set_rules('huoauth','Authority Type','trim|xss_clean');
            $this->form_validation->set_rules('DateofAGP','Date of AGP','trim|xss_clean');
            $this->form_validation->set_rules('Datefrom','Date From','trim|xss_clean|required');
            $this->form_validation->set_rules('Dateto','Date To','trim|xss_clean');
	    $this->form_validation->set_rules('userfile','Select File','trim|xss_clean');

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
		$desigcode=$this->commodel->get_listspfic1('designation','desig_code','desig_id',$desigc)->desig_code;
		$emppost = $this->input->post('emppost', TRUE);
		$level = $this->input->post('level', TRUE);
                $payb = $this->input->post('payband', TRUE);
                $gradepay = $this->input->post('gradepay', TRUE);
                $orderno = $this->input->post('orderno', TRUE);
                $huoauth = $this->input->post('huoauth', TRUE);
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
                if($eds_data['servicedata']->empsd_desigcode != $desigcode)
                        $logmessage = "Edit Staff Service Data " .$eds_data['servicedata']->empsd_desigcode. " changed by " .$desigc;
		if($eds_data['servicedata']-> empsd_shagpstid != $emppost)
                        $logmessage = "Edit Staff Service Data " .$eds_data['servicedata']->empsd_shagpstid. " changed by " .$emppost;
		if($eds_data['servicedata']->empsd_level != $level)
                        $logmessage = "Edit Staff Service Data " .$eds_data['servicedata']->empsd_level. " changed by " .$level;
                if($eds_data['servicedata']->empsd_pbid != $payb)
                        $logmessage = "Edit Staff Service Data " .$eds_data['servicedata']->empsd_pbid. " changed by " .$payb;
                if($eds_data['servicedata']->empsd_gradepay != $gradepay)
                        $logmessage = "Edit Staff Service Data " .$eds_data['servicedata']->empsd_gradepay. " changed by " .$gradepay;
                if($eds_data['servicedata']->empsd_orderno != $orderno)
                        $logmessage = "Edit Staff Service Data " .$eds_data['servicedata']->empsd_orderno. " changed by " .$orderno;
                if($eds_data['servicedata']->empsd_authority != $huoauth)
                        $logmessage = "Edit Staff Service Data " .$eds_data['servicedata']->empsd_authority. " changed by " .$huoauth;
                if($eds_data['servicedata']->empsd_pbdate != $dataofagp)
                    $logmessage = "Edit Staff Service Data " .$eds_data['servicedata']->empsd_pbdate. " changed by " .$dataofagp;
                if($eds_data['servicedata']->empsd_dojoin != $datefrom)
                        $logmessage = "Edit Staff Service Data " .$eds_data['servicedata']->empsd_dojoin. " changed by " .$datefrom;
                if($eds_data['servicedata']->empsd_dorelev != $dateto)
                        $logmessage = "Edit Staff Service Data " .$eds_data['servicedata']->empsd_dorelev. " changed by " .$dateto;
               $new_name='';
                if(!empty($_FILES['userfile']['name'])){
                    
                    $newFileName = $_FILES['userfile']['name'];
                    $fileExt1 = explode('.', $newFileName);
                    $file_ext = end( $fileExt1);
                    $new_name = $id."_".$newFileName; 
                }    

                $edit_data = array(
                    'empsd_campuscode'      =>$campus,
		    'empsd_ucoid'           =>$uocontrol,
                    'empsd_deptid'          =>$department,
		    'empsd_schemeid'        =>$schemecode,
		    'empsd_ddoid'           =>$ddo,
		    'empsd_worktype'        =>$worktype,
		    'empsd_group'           =>$group,
                    'empsd_desigcode'       =>$desigcode,
		    'empsd_shagpstid'       =>$emppost,
		    'empsd_level'           =>$level,
                    'empsd_pbid'            =>$payb,
                    'empsd_gradepay'        =>$gradepay,
                    'empsd_orderno'        =>$orderno,
                    'empsd_pbdate'          =>$dataofagp,
                    'empsd_dojoin'          =>$datefrom,
                    'empsd_authority'          =>$huoauth,
		    'empsd_fsession'	    =>$_POST['fsession'],
		    'empsd_dorelev'         =>$dateto,
		    'empsd_tsession'	    =>$_POST['tsession'],
		    'empsd_filename'	    => $new_name,
                );
		
		$msgfile='';
                if(!empty($_FILES['userfile']['name'])){
                    
                    $config['upload_path'] = 'uploads/SIS/serviceattachment/';
                    $config['max_size'] = '2048000000';
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
                        $this->load->view('empmgmt/edit_servicedata', $edpref_data);
                      
                    }
                    else { 
                        $upload_data=$this->upload->data();
                        $msgfile=" and Attachment" ;
                    } 
            
                }//userfileifcond


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
                        redirect('report/service_profile/'.$uid);
                    }
                                        
                }
                
            }//formtrue
         
        }   
    }
    /****************************  Closer UPDATE DATA *************************/
    /***********************************Start Add service detail******************************************/
    public function add_disciplindata($empid) {
        $this->roleid=$this->session->userdata('id_role');
        $this->emp_id = $empid;
        $empcode=$this->sismodel->get_listspfic1('employee_master','emp_code','emp_id',$empid)->emp_code;
        $empuserid=$this->sismodel->get_listspfic1('employee_master','emp_userid','emp_id',$empid)->emp_userid;
        $empemail=$this->sismodel->get_listspfic1('employee_master','emp_email','emp_id',$empid)->emp_email;

        if(isset($_POST['addservdata'])) {	
            //form validation
            $this->form_validation->set_rules('punishtype','Nature of Punishment','trim|required|xss_clean');
	    $this->form_validation->set_rules('reason','Reason','trim|xss_clean');
            $this->form_validation->set_rules('status','Status','trim|xss_clean');
            $this->form_validation->set_rules('Datefrom','Date From','trim|xss_clean|required');
            $this->form_validation->set_rules('Dateto','Date To','trim|xss_clean');
            if($this->form_validation->run() == FALSE){
                
                redirect('empmgmt/add_disciplindata');
            }//formvalidation
            else{
                $data = array(
                    'sdap_userid'           	=>$empuserid,
                    'sdap_empcode'      	=>$empcode,
                    'sdap_punishnature'         =>$_POST['punishtype'],
                    'sdap_punishreason'         =>$_POST['reason'],
                    'sdap_punishstatus'        	=>$_POST['status'],
                    'sdap_fromdate'           	=>$_POST['Datefrom'],
		    'sdap_todate'        	=>$_POST['Dateto'],
                    'sdap_creatorid'           	=>$this->session->userdata('username'),
                    'sdap_creatordate'       	=>date('Y-m-d'),
		    'sdap_modifierid'       	=>$this->session->userdata('username'),
		    'sdap_modifydate'           =>date('Y-m-d'),
                );
                $servdataflag=$this->sismodel->insertrec('staff_disciplinary_actions_perticulars', $data) ;
                if(!$servdataflag)
                {
                    $this->logger->write_logmessage("error","Error in insert staff_disciplinary_actions_perticulars record", "Error in insert staff_disciplinary_actions_perticulars record." );
                    $this->logger->write_dblogmessage("error","Error in insert staff_disciplinary_actions_perticulars record ", "Error in insert staff_disciplinary_actions_perticulars record" );
                    $this->session->set_flashdata('err_message','Error in insert staff_disciplinary_actions_perticulars record ');
                    $this->load->view('empmgmt/add_disciplindata',$data);
                }
                else{
                    $this->logger->write_logmessage("insert","Add staff_disciplinary_actions_perticulars Data", "staff_disciplinary_actions_perticulars record insert successfully." );
                    $this->logger->write_dblogmessage("insert","Add staff_disciplinary_actions_perticulars Data", "staff_disciplinary_actions_perticulars record insert successfully ." );
                    $this->session->set_flashdata('success','staff_disciplinary_actions_perticulars record insert successfully.'."["." "."Employee PF NO:"." ".$empcode." and "."Username:"." ".$empemail." "."]");
                    if($this->roleid == 4){
                        redirect('empmgmt/viewempprofile');
                    }
                    else{
                        redirect('report/disciplin_profile/'.$empid);
                    }
                }
            }//else
        }//ifpost button
        $this->load->view('empmgmt/add_disciplindata');
    }//function close
    /***********************************Start Add service detail******************************************/
    public function add_recmethddata($empid) {
        $this->roleid=$this->session->userdata('id_role');
        $this->emp_id = $empid;
        $empcode=$this->sismodel->get_listspfic1('employee_master','emp_code','emp_id',$empid)->emp_code;
        $empuserid=$this->sismodel->get_listspfic1('employee_master','emp_userid','emp_id',$empid)->emp_userid;
        $empemail=$this->sismodel->get_listspfic1('employee_master','emp_email','emp_id',$empid)->emp_email;

        if(isset($_POST['addservdata'])) {	
            //form validation
            $this->form_validation->set_rules('recmthd','Method Of Recruitment','trim|required|xss_clean');
	    $this->form_validation->set_rules('dsubgrp','Sub Group','trim|xss_clean');
            $this->form_validation->set_rules('grpdetails','Sub Group Details','trim|xss_clean');
	    $this->form_validation->set_rules('compname','Compassion Name','trim|xss_clean');
	    $this->form_validation->set_rules('desig','Designation','trim|xss_clean');
	    $this->form_validation->set_rules('dept','Department','trim|xss_clean');
            if($this->form_validation->run() == FALSE){
                
                redirect('empmgmt/add_recmethddata');
            }//formvalidation
            else{
                $data = array(
                    'srp_userid'           =>$empuserid,
                    'srp_empcode'      =>$empcode,
                    'srp_methodrecrtmnt'           =>$_POST['recmthd'],
                    'srp_subcategory'          =>$_POST['dsubgrp'],
                    'srp_detail'        =>$_POST['grpdetails'],
                    'srp_compassionname'           =>$_POST['compname'],
		    'srp_compassiondesig'        =>$_POST['desig'],
                    'srp_compassiondept'           =>$_POST['dept'],
                    'srp_creatorid'       =>$this->session->userdata('username'),
		    'srp_creatordate'       =>date('Y-m-d'),
		    'srp_modifierid'           =>$this->session->userdata('username'),
                    'srp_modifydate'            =>date('Y-m-d'),
                );
                $servdataflag=$this->sismodel->insertrec('staff_recruitment_perticulars', $data) ;
                if(!$servdataflag)
                {
                    $this->logger->write_logmessage("error","Error in insert staff_recruitment_perticulars record", "Error in insert staff_recruitment_perticulars record." );
                    $this->logger->write_dblogmessage("error","Error in insert staff_recruitment_perticulars record ", "Error in insert staff_recruitment_perticulars record" );
                    $this->session->set_flashdata('err_message','Error in insert staff_recruitment_perticulars record ');
                    $this->load->view('empmgmt/add_recmethddata',$data);
                }
                else{
                    $this->logger->write_logmessage("insert","Add staff_recruitment_perticulars Data", "staff_recruitment_perticulars insert successfully." );
                    $this->logger->write_dblogmessage("insert","Add staff_recruitment_perticulars Data", "staff_recruitment_perticulars record insert successfully ." );
                    $this->session->set_flashdata('success','staff_recruitment_perticulars record insert successfully.'."["." "."Employee PF NO:"." ".$empcode." and "."Username:"." ".$empemail." "."]");
                    if($this->roleid == 4){
                        redirect('empmgmt/viewempprofile');
                    }
                    else{
                        redirect('report/recruit_profile/'.$empid);
                    }
                                       
                }
            }//else
           
        }//ifpost button
        $this->load->view('empmgmt/add_recmethddata');
    }//function close
    /***********************************Start Add service detail******************************************/
    public function add_leavepertdata($empid) {
        $this->roleid=$this->session->userdata('id_role');
	$this->leaveresult=$this->sismodel->get_listspfic2('leave_type_master','lt_id', 'lt_name');
        $this->emp_id = $empid;
        $empcode=$this->sismodel->get_listspfic1('employee_master','emp_code','emp_id',$empid)->emp_code;
        $empuserid=$this->sismodel->get_listspfic1('employee_master','emp_userid','emp_id',$empid)->emp_userid;
        $empemail=$this->sismodel->get_listspfic1('employee_master','emp_email','emp_id',$empid)->emp_email;
        $this->orgcode=$this->commodel->get_listspfic1('org_profile','org_code','org_id',1)->org_code;
        //$this->campus=$this->commodel->get_listspfic2('study_center','sc_code','sc_name','org_code',$this->orgcode);
	$this->campus=$this->commodel->get_listspfic2('study_center','sc_id','sc_name','org_code',$this->orgcode);
       //$this->desig= $this->commodel->get_listspfic2('designation','desig_code','desig_name');
  //      $this->salgrd=$this->sismodel->get_list('salary_grade_master');
 

        if(isset($_POST['addleavedata'])) {	
            //form validation
    //        $this->form_validation->set_rules('campus','Campus','trim|required|xss_clean');
//	    $this->form_validation->set_rules('uocontrol','UniversityOfficerControl','trim|xss_clean');
            $this->form_validation->set_rules('department','Department','trim|xss_clean|required');
            $this->form_validation->set_rules('la_type','la Type','trim|xss_clean|required');
            $this->form_validation->set_rules('layear','la year','trim|xss_clean');
            $this->form_validation->set_rules('ladays','la Days','trim|xss_clean|required');
            $this->form_validation->set_rules('la_desc','Description','trim|xss_clean');
            $this->form_validation->set_rules('applied_la_to_date','To date','trim|xss_clean');
            $this->form_validation->set_rules('applied_la_from_date','From Date','trim|xss_clean');
	   // $this->form_validation->set_rules('schemecode','Scheme Name','trim|xss_clean');
//	    $this->form_validation->set_rules('ddo','Drawing and Disbursing Officer','trim|xss_clean');
//	    $this->form_validation->set_rules('workingtype','Workingtype','trim|required|xss_clean');
//	    $this->form_validation->set_rules('group','Group','trim|xss_clean');
  //          $this->form_validation->set_rules('designation','Designation','trim|required|xss_clean');
//	    $this->form_validation->set_rules('emppost','Shown Against The Post','trim|required|xss_clean');
            if($this->form_validation->run() == FALSE){
                
                redirect('empmgmt/add_leavepertdata');
            }//formvalidation
            else{
//		$desigcode=$this->commodel->get_listspfic1('designation','desig_code','desig_id',$_POST['designation'])->desig_code;
		$lat=NULL;$laf=NULL;
		$ltype = $_POST['la_type'];
		$lat=$_POST['applied_la_to_date'];
                $laf=$_POST['applied_la_from_date'];
		$ldays = $_POST['ladays'];
		$cdate = date("Y-m-d");
		if(!empty($_FILES['userfile']['name'])){
			$parts = explode(" ",$laf);
		        $ldate = $parts[0];
			$attachfilename=$empid."_".$ltype."_".$ldate;
	                $newFileName = $_FILES['userfile']['name'];
        	        $fileExt1 = explode('.', $newFileName);
                	$file_ext = end( $fileExt1);
	                $new_name = $attachfilename.".".$file_ext;
                }
                else{
        	        $new_name='';
                }
                $data = array(
                    'la_userid'           =>$empid,
                    'la_deptid'          =>$_POST['department'],
                    'la_type'        =>$_POST['la_type'],
                    'la_year'        =>$_POST['layear'],
                    'applied_la_from_date'        =>$laf,
                    'applied_la_to_date'        =>$lat,
                    'la_days'        =>$ldays,
                    'granted_la_from_date'        =>$laf,
                    'granted_la_to_date'        =>$lat,
                    'la_taken'        =>$ldays,
                    'la_status'        =>'1',
                    'la_desc'        =>$_POST['la_desc'],
                    'la_rejres'        =>'',
		    'la_upfile' =>$new_name,
                );
                $servdataflag=$this->sismodel->insertrec('leave_apply', $data) ;
//		$insertid = $this->->insert_id();
		$msgphoto='';
		if(!empty($_FILES['userfile']['name'])){
		$config = array(
                            'upload_path' =>  "./uploads/SIS/empleave",
                            'allowed_types' => "gif|jpg|png|jpeg|pdf",
                            'overwrite' => TRUE,
                            'max_size' => "1000000", // Can be set to particular file size 
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
                            $simsg = "The permitted size of doc is 1000kb";
                            $ferror = $simsg.$ferror;
                            $this->logger->write_logmessage("uploaddoc","doc upload in sis error", $ferror);
                            $this->logger->write_dblogmessage("uploaddoc","doc upload in sis error", $ferror);
                            $this->session->set_flashdata('err_message', $ferror);

                        }
                        else {
                            $upload_data=$this->upload->data();
                            $msgphoto=" and doc" ;
                        }
		}

                if(!$servdataflag)
                {
                    $this->logger->write_logmessage("error","Error in insert staff leave record", "Error in insert staff leave record." );
                    $this->logger->write_dblogmessage("error","Error in insert staff leave record ", "Error in insert staff leave record" );
                    $this->session->set_flashdata('err_message','Error in insert staff leave record ');
                    $this->load->view('empmgmt/add_leavepertdata',$data);
                }
                else{
                    $this->roleid=$this->session->userdata('id_role');
                    $empcode=$this->sismodel->get_listspfic1('employee_master','emp_code','emp_id',$empid)->emp_code;
                    $empemail=$this->sismodel->get_listspfic1('employee_master','emp_email','emp_id',$empid)->emp_email;
                    $this->logger->write_logmessage("insert","Add Staff leave Data", "Staff leave record insert successfully." );
                    $this->logger->write_dblogmessage("insert","Add Staff leave Data", "Staff leave record insert successfully ." );
                    $this->session->set_flashdata('success','Leave Data record insert successfully.'."["." "."Employee PF NO:"." ".$empcode." and "."Username:"." ".$empemail." "."]".$msgphoto);
                    if($this->roleid == 4){
                        redirect('empmgmt/viewempprofile');
                    }
                    else{
                        redirect('report/leave_profile/'.$empid);
                    }
                                       
                }
            }//else
           
        }//ifpost button
        $this->load->view('empmgmt/add_leavepertdata');
    }//function close

	public function	edit_leavepertdata($id){
		$this->roleid=$this->session->userdata('id_role');
		$empid = $this->sismodel->get_listspfic1('leave_apply','la_userid','la_id',$id)->la_userid;

		$this->leaveresult=$this->sismodel->get_listspfic2('leave_type_master','lt_id', 'lt_name');
	        $this->orgcode=$this->commodel->get_listspfic1('org_profile','org_code','org_id',1)->org_code;
        	$this->campus=$this->commodel->get_listspfic2('study_center','sc_id','sc_name','org_code',$this->orgcode);
	
		$this->emp_id = $empid;
        	$data['id'] = $id;
	        $data['leavedata'] = $this->sismodel->get_listrow('leave_apply','la_id',$id)->row();
		if(isset($_POST['editleavedata'])) {
            		//form validation
		        $this->form_validation->set_rules('department','Department','trim|xss_clean|required');
		        $this->form_validation->set_rules('la_type','la Type','trim|xss_clean|required');
		        $this->form_validation->set_rules('layear','la year','trim|xss_clean');
		        $this->form_validation->set_rules('ladays','la Days','trim|xss_clean|required');
	            	$this->form_validation->set_rules('la_desc','Description','trim|xss_clean');
        	    	$this->form_validation->set_rules('applied_la_to_date','To date','trim|xss_clean');
	            	$this->form_validation->set_rules('applied_la_from_date','From Date','trim|xss_clean');
        	    	if($this->form_validation->run() == FALSE){

                		redirect('empmgmt/add_leavepertdata');
	        	}//formvalidation
            		else{
				$lat=NULL;$laf=NULL;
                		$ltype = $_POST['la_type'];
                		$lat=$_POST['applied_la_to_date'];
                		$laf=$_POST['applied_la_from_date'];
                		$ldays = $_POST['ladays'];
                		$cdate = date("Y-m-d");
                		if(!empty($_FILES['userfile']['name'])){
		                        $parts = explode(" ",$laf);
                		        $ldate = $parts[0];
		                        $attachfilename=$empid."_".$ltype."_".$ldate;
                		        $newFileName = $_FILES['userfile']['name'];
		                        $fileExt1 = explode('.', $newFileName);
                		        $file_ext = end( $fileExt1);
		                        $new_name = $attachfilename.".".$file_ext;
                		}
		                else{
                		        $new_name=$data['leavedata']->la_upfile;
		                }

				$ludata = array(
                    			'la_deptid'                 =>$_POST['department'],
                    			'la_type'                   =>$_POST['la_type'],
                    			'la_year'                   =>$_POST['layear'],
                    			'applied_la_from_date'      =>$laf,
                    			'applied_la_to_date'        =>$lat,
                    			'la_days'        	    =>$ldays,
                    			'granted_la_from_date'      =>$laf,
                    			'granted_la_to_date'        =>$lat,
                    			'la_taken'                  =>$ldays,
                    			'la_desc'                   =>$_POST['la_desc'],
                    			'la_upfile'                 =>$new_name,
                		);
                		$leavedataflag=$this->sismodel->updaterec('leave_apply', $ludata,'la_id',$id) ;
				$msgphoto='';
                		if(!empty($_FILES['userfile']['name'])){
                			$config = array(
                            			'upload_path' =>  "./uploads/SIS/empleave",
                            			'allowed_types' => "gif|jpg|png|jpeg|pdf",
                            			'overwrite' => TRUE,
                            			'max_size' => "1000000", // Can be set to particular file size 
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
                            			$simsg = "The permitted size of doc is 1000kb";
                            			$ferror = $simsg.$ferror;
                            			$this->logger->write_logmessage("uploaddoc","doc upload in sis error", $ferror);
                            			$this->logger->write_dblogmessage("uploaddoc","doc upload in sis error", $ferror);
                            			$this->session->set_flashdata('err_message', $ferror);

                        		}
                        		else {
                            			$upload_data=$this->upload->data();
                            			$msgphoto=" and doc" ;
                        		}
                		}

                		if(!$servdataflag)
                		{
                    			$this->logger->write_logmessage("error","Error in insert staff leave record", "Error in insert staff leave record." );
                    			$this->logger->write_dblogmessage("error","Error in insert staff leave record ", "Error in insert staff leave record" );
                    			$this->session->set_flashdata('err_message','Error in insert staff leave record ');
                    			$this->load->view('empmgmt/add_leavepertdata',$data);
                		}
                		else{

                    			$this->roleid=$this->session->userdata('id_role');
                    			$empcode=$this->sismodel->get_listspfic1('employee_master','emp_code','emp_id',$empid)->emp_code;
                    			$empemail=$this->sismodel->get_listspfic1('employee_master','emp_email','emp_id',$empid)->emp_email;
                    			$this->logger->write_logmessage("insert","Add Staff leave Data", "Staff leave record insert successfully." );
                    			$this->logger->write_dblogmessage("insert","Add Staff leave Data", "Staff leave record insert successfully ." );
                    			$this->session->set_flashdata('success','Leave Data record insert successfully.'."["." "."Employee PF NO:"." ".$empcode." and "."Username:"." ".$empemail." "."]".$msgphoto);
                    			if($this->roleid == 4){
                        			redirect('empmgmt/viewempprofile');
                    			}
                    			else{
                        			redirect('report/leave_profile/'.$empid);
 					}
                		}
            		}//else

        	}//ifpost button
        	$this->load->view('empmgmt/edit_leavepertdata',$data);
	}
    /***********************************Start Add service detail******************************************/
    public function add_deputatdata($empid) {
        $this->roleid=$this->session->userdata('id_role');
        $this->emp_id = $empid;
        $empcode=$this->sismodel->get_listspfic1('employee_master','emp_code','emp_id',$empid)->emp_code;
        $empuserid=$this->sismodel->get_listspfic1('employee_master','emp_userid','emp_id',$empid)->emp_userid;
        $empemail=$this->sismodel->get_listspfic1('employee_master','emp_email','emp_id',$empid)->emp_email;

        if(isset($_POST['addservdata'])) {	
            //form validation
            $this->form_validation->set_rules('deputation','Deputation','trim|required|xss_clean');
	    $this->form_validation->set_rules('specify','Specification','trim|xss_clean');
            $this->form_validation->set_rules('Datefrom','Date From','trim|xss_clean|required');
	    $this->form_validation->set_rules('Dateto','Date To','trim|xss_clean');
            if($this->form_validation->run() == FALSE){
                
                redirect('empmgmt/add_deputatdata');
            }//formvalidation
            else{
                $data = array(
                    'sdp_userid'           =>$empuserid,
                    'sdp_empcode'      	   =>$empcode,
                    'sdp_deputation'       =>$_POST['deputation'],
                    'sdp_specification'    =>$_POST['specify'],
                    'sdp_fromdate'         =>$_POST['Datefrom'],
                    'sdp_todate'           =>$_POST['Dateto'],
		    'sdp_creatorid'        =>$this->session->userdata('username'),
                    'sdp_creatordate'      =>date('Y-m-d'),
                    'sdp_modifierid'       =>$this->session->userdata('username'),
		    'sdp_modifydate'       =>date('Y-m-d'),
                );
                $servdataflag=$this->sismodel->insertrec('staff_deputation_perticulars', $data) ;
                if(!$servdataflag)
                {
                    $this->logger->write_logmessage("error","Error in insert staff_deputation_perticulars record", "Error in insert staff_deputation_perticulars record." );
                    $this->logger->write_dblogmessage("error","Error in insert staff_deputation_perticulars record ", "Error in insert staff_deputation_perticulars record" );
                    $this->session->set_flashdata('err_message','Error in insert staff_deputation_perticulars record ');
                    $this->load->view('empmgmt/add_deputatdata',$data);
                }
                else{
                    $this->logger->write_logmessage("insert","Add staff_deputation_perticulars Data", "Staff staff_deputation_perticulars insert successfully." );
                    $this->logger->write_dblogmessage("insert","Add staff_deputation_perticulars Data", "Staff_deputation_perticulars successfully ." );
                    $this->session->set_flashdata('success','staff_deputation_perticulars record insert successfully.'."["." "."Employee PF NO:"." ".$empcode." and "."Username:"." ".$empemail." "."]");
                    if($this->roleid == 4){
                        redirect('empmgmt/viewempprofile');
                    }
                    else{
                        redirect('report/deputation_profile/'.$empid);
                    }
                                       
                }
            }//else
           
        }//ifpost button
        $this->load->view('empmgmt/add_deputatdata');
    }//function close
    /***********************************Start Add service detail******************************************/
    public function add_deptexamdata($empid) {
        $this->roleid=$this->session->userdata('id_role');
        $this->emp_id = $empid;
        $empcode=$this->sismodel->get_listspfic1('employee_master','emp_code','emp_id',$empid)->emp_code;
        $empuserid=$this->sismodel->get_listspfic1('employee_master','emp_userid','emp_id',$empid)->emp_userid;
        $empemail=$this->sismodel->get_listspfic1('employee_master','emp_email','emp_id',$empid)->emp_email;
        if(isset($_POST['addservdata'])) {	
            //form validation
            $this->form_validation->set_rules('deptexam','Departmental Exam','trim|required|xss_clean');
	    $this->form_validation->set_rules('specify','Specify','trim|xss_clean');
            $this->form_validation->set_rules('Datefrom','Date Of Passing','trim|xss_clean|required');
            if($this->form_validation->run() == FALSE){
                
                redirect('empmgmt/add_deptexamdata');
            }//formvalidation
            else{
                $data = array(
                    'sdep_userid'           	=>$empuserid,
                    'sdep_empcode'     		=>$empcode,
                    'sdep_examname'           	=>$_POST['deptexam'],
                    'sdep_specification'        =>$_POST['specify'],
                    'sdep_passdate'        	=>$_POST['Datefrom'],
                    'sdep_creatorid'           	=>$this->session->userdata('username'),
		    'sdep_creatordate'        	=>date('Y-m-d'),
                    'sdep_modifierid'           =>$this->session->userdata('username'),
                    'sdep_modifydate`'       	=>date('Y-m-d'),
                );
                $servdataflag=$this->sismodel->insertrec('staff_department_exam_perticulars', $data) ;
                if(!$servdataflag)
                {
                    $this->logger->write_logmessage("error","Error in insert staff_department_exam_perticulars record", "Error in insert staff_department_exam_perticulars record." );
                    $this->logger->write_dblogmessage("error","Error in insert staff_department_exam_perticulars record ", "Error in insert staff_department_exam_perticulars record" );
                    $this->session->set_flashdata('err_message','Error in insert staff_department_exam_perticulars record ');
                    $this->load->view('empmgmt/add_deptexamdata',$data);
                }
                else{
                    $this->logger->write_logmessage("insert","Add staff_department_exam_perticulars Data", "staff_department_exam_perticulars record insert successfully." );
                    $this->logger->write_dblogmessage("insert","Add staff_department_exam_perticulars Data", "Staff_department_exam_perticulars record insert successfully ." );
                    $this->session->set_flashdata('success','Staff_department_exam_perticulars record insert successfully.'."["." "."Employee PF NO:"." ".$empcode." and "."Username:"." ".$empemail." "."]");
                    if($this->roleid == 4){
                        redirect('empmgmt/viewempprofile');
                    }
                    else{
                        redirect('report/deptexam_profile/'.$empid);
                    }
                                       
                }
            }//else
           
        }//ifpost button
        $this->load->view('empmgmt/add_deptexamdata');
    }//function close
    /***********************************Start Add service detail******************************************/
    public function add_workarrangdata($empid) {
        $this->roleid=$this->session->userdata('id_role');
        $this->emp_id = $empid;
        $empcode=$this->sismodel->get_listspfic1('employee_master','emp_code','emp_id',$empid)->emp_code;
        $empuserid=$this->sismodel->get_listspfic1('employee_master','emp_userid','emp_id',$empid)->emp_userid;
        $empemail=$this->sismodel->get_listspfic1('employee_master','emp_email','emp_id',$empid)->emp_email;
        $this->orgcode=$this->commodel->get_listspfic1('org_profile','org_code','org_id',1)->org_code;
	$this->campus=$this->commodel->get_listspfic2('study_center','sc_id','sc_name','org_code',$this->orgcode);
 

        if(isset($_POST['addservdata'])) {	
            //form validation
            $this->form_validation->set_rules('workdept','Is Working in Same Department','trim|required|xss_clean');
	    $this->form_validation->set_rules('campus','Campus Name','trim|xss_clean');
	    $this->form_validation->set_rules('uocontrol','UniversityOfficerControl','trim|xss_clean');
            $this->form_validation->set_rules('department','Department','trim|xss_clean');
	    $this->form_validation->set_rules('Datefrom','Date From','trim|xss_clean');
            $this->form_validation->set_rules('Dateto','Date To','trim|xss_clean');

/*	    $this->form_validation->set_rules('schemecode','Scheme Name','trim|xss_clean');
	    $this->form_validation->set_rules('ddo','Drawing and Disbursing Officer','trim|xss_clean'); */
            if($this->form_validation->run() == FALSE){
                
                redirect('empmgmt/add_workarrangdata');
            }//formvalidation
            else{
        $empcamp=$this->sismodel->get_listspfic1('employee_master','emp_scid','emp_id',$empid)->emp_scid;
        $empuo=$this->sismodel->get_listspfic1('employee_master','emp_uocid','emp_id',$empid)->emp_uocid;
        $empdept=$this->sismodel->get_listspfic1('employee_master','emp_dept_code','emp_id',$empid)->emp_dept_code;
		if(isset($_POST['campus'])){ $wcamp = $_POST['campus'];} else {$wcamp='';}
		if(isset($_POST['uocontrol'])){ $wuo = $_POST['uocontrol'];} else {$wuo='';}
		if(isset($_POST['department'])){ $wdept = $_POST['department'];} else {$wdept='';}
                $data = array(
                    'swap_userid'       =>$empuserid,
                    'swap_empcode'      =>$empcode,
                    'swap_ocampus'      =>$empcamp,
                    'swap_ouo'          =>$empuo,
                    'swap_odept'        =>$empdept,
                    'swap_wcampus'      =>$wcamp,
		    'swap_wuo'        	=>$wuo,
                    'swap_wdept'        =>$wdept,
                    'swap_fromdate'        =>$_POST['Datefrom'],
                    'swap_todate'        =>$_POST['Dateto'],
                    'swap_creatorid'    =>$this->session->userdata('username'),
		    'swap_creatordate'  =>date('Y-m-d'),
		    'swap_modifierid'   =>$this->session->userdata('username'),
                    'swap_modifydate'   =>date('Y-m-d'),
                );
                $servdataflag=$this->sismodel->insertrec('staff_working_arrangements_perticulars', $data) ;
                if(!$servdataflag)
                {
                    $this->logger->write_logmessage("error","Error in insert staff_working_arrangements_perticulars  record", "Error in insert  staff_working_arrangements_perticulars record." );
                    $this->logger->write_dblogmessage("error","Error in insert   staff_working_arrangements_perticulars record ", "Error in insert  staff_working_arrangements_perticulars record" );
                    $this->session->set_flashdata('err_message','Error in insert  staff_working_arrangements_perticulars record ');
                    $this->load->view('empmgmt/add_workarrangdata',$data);
                }
                else{
                    $this->logger->write_logmessage("insert","Add  staff_working_arrangements_perticulars Data", "Staff  staff_working_arrangements_perticulars insert successfully." );
                    $this->logger->write_dblogmessage("insert","Add  staff_working_arrangements_perticulars Data", "Staff_working_arrangements_perticulars record insert successfully ." );
                    $this->session->set_flashdata('success','Staff_working_arrangements_perticulars record insert successfully.'."["." "."Employee PF NO:"." ".$empcode." and "."Username:"." ".$empemail." "."]");
                    if($this->roleid == 4){
                        redirect('empmgmt/viewempprofile');
                    }
                    else{
                        redirect('report/workorder_profile/'.$empid);
                    }
                                       
                }
            }//else
           
        }//ifpost button
        $this->load->view('empmgmt/add_workarrangdata');
    }//function close
    /***********************************Start Add academic qualification******************************************/
    
    public function add_academicqualification($empid){
        $this->roleid=$this->session->userdata('id_role');
        $this->emp_id = $empid;
        if(isset($_POST['addacadrofile'])){
            //form validation
            $this->form_validation->set_rules('stdclass','stdclass','trim|xss_clean');
	    $this->form_validation->set_rules('board','Board University','trim|xss_clean');
	    $this->form_validation->set_rules('result','result','trim|xss_clean');
            $this->form_validation->set_rules('yopass','Year of Passing','trim|xss_clean');
	    $this->form_validation->set_rules('discipline','Discipline','trim|xss_clean');
            /*******************************sec2*******************************************/
            $this->form_validation->set_rules('degree1','Degree1','trim|xss_clean');
	    $this->form_validation->set_rules('board1','Board University','trim|xss_clean');
	    $this->form_validation->set_rules('result1','result','trim|xss_clean');
            $this->form_validation->set_rules('yopass1','Year of Passing','trim|xss_clean');
	    $this->form_validation->set_rules('discipline1','Discipline1','trim|xss_clean');
            
            /*******************************sec3*******************************************/
            $this->form_validation->set_rules('degree1','Degree1','trim|xss_clean');
	    $this->form_validation->set_rules('board1','Board University','trim|xss_clean');
	    $this->form_validation->set_rules('result1','result','trim|xss_clean');
            $this->form_validation->set_rules('yopass1','Year of Passing','trim|xss_clean');
	    $this->form_validation->set_rules('discipline1','Discipline1','trim|xss_clean');
            /*******************************sec4*******************************************/
            $this->form_validation->set_rules('pgdiploma','PGDiploma','trim|xss_clean');
	    $this->form_validation->set_rules('board2','Board University','trim|xss_clean');
	    $this->form_validation->set_rules('result2','result','trim|xss_clean');
            $this->form_validation->set_rules('yopass2','Year of Passing','trim|xss_clean');
	    $this->form_validation->set_rules('discipline2','Discipline1','trim|xss_clean');
            /*******************************sec5*******************************************/
            $this->form_validation->set_rules('pgdegree','PGDegree','trim|xss_clean');
	    $this->form_validation->set_rules('board3','Board University','trim|xss_clean');
	    $this->form_validation->set_rules('result3','result','trim|xss_clean');
            $this->form_validation->set_rules('yopass3','Year of Passing','trim|xss_clean');
	    $this->form_validation->set_rules('discipline3','Discipline1','trim|xss_clean');
            /*****************************sec6************************************************/
            $this->form_validation->set_rules('MPhil','MPhil','trim|xss_clean');
	    $this->form_validation->set_rules('board4','Board University','trim|xss_clean');
	    $this->form_validation->set_rules('result4','result','trim|xss_clean');
            $this->form_validation->set_rules('yopass4','Year of Passing','trim|xss_clean');
	    $this->form_validation->set_rules('discipline4','Discipline1','trim|xss_clean');
            /*****************************sec7************************************************/
            $this->form_validation->set_rules('PhD','PhD','trim|xss_clean');
	    $this->form_validation->set_rules('board5','Board University','trim|xss_clean');
	    $this->form_validation->set_rules('result5','result','trim|xss_clean');
            $this->form_validation->set_rules('yopass5','Year of Passing','trim|xss_clean');
	    $this->form_validation->set_rules('discipline5','Discipline1','trim|xss_clean');
            /*****************************sec8************************************************/
            $this->form_validation->set_rules('PDF','PDF','trim|xss_clean');
	    $this->form_validation->set_rules('board6','Board University','trim|xss_clean');
	    $this->form_validation->set_rules('result6','result','trim|xss_clean');
            $this->form_validation->set_rules('yopass6','Year of Passing','trim|xss_clean');
	    $this->form_validation->set_rules('discipline6','Discipline1','trim|xss_clean');
            if($this->form_validation->run() == FALSE){
            
                $this->load->view('empmgmt/add_academicprofile',$this->emp_id);
                return;
            }//formvalidation
            else{
                $academicflag=false;
                /************************************************/
                $stdc = $this->input->post('stdclass', TRUE);
                $buni = $this->input->post('board', TRUE);
                $result = $this->input->post('result', TRUE);
                $ypass = $this->input->post('yopass', TRUE);
                $dpln = $this->input->post('discipline', TRUE);
                
                foreach($stdc as $a => $b){
                // echo $stdc[$a].$buni[$a].$result[$a] . $ypass[$a]  . $dpln[$a] ;                     
                
                    $data = array(
                        'saq_empid'         =>$this->emp_id,
                        'saq_dgree'         =>$stdc[$a],
                        'saq_board_univ'    =>$buni[$a],
                        'saq_result'        =>$result[$a],
                        'saq_yopass'        =>$ypass[$a],
                        'saq_discipline'    =>$dpln[$a],
                        'saq_creatorid'     =>$this->session->userdata('username'),
                        'saq_creatordate'   =>date('Y-m-d'),
                        'saq_modifierid'    =>$this->session->userdata('username'),
                        'saq_modifydate'    =>date('Y-m-d'),
                    );
                    
                    if(!empty($stdc[$a])){
                        $dupcheck = array(
                        'saq_empid'  =>$this->emp_id,
                        'saq_dgree'  =>$stdc[$a],
                
                        ); 
                        $stddup = $this->sismodel->isduplicatemore('staff_academic_qualification', $dupcheck);
                        if(!$stddup){
                    
                            $academicflag=$this->sismodel->insertrec('staff_academic_qualification', $data);
                        }    
                    }
                }//coserforeach
                /*********************************************************/
                /************************************************/
                $degree = $this->input->post('degree', TRUE);
                $buni1 = $this->input->post('board1', TRUE);
                $result1 = $this->input->post('result1', TRUE);
                $ypass1 = $this->input->post('yopass1', TRUE);
                $dpln1 = $this->input->post('discipline1', TRUE);
                
                foreach($degree as $c => $b1){
                
                    $data1 = array(
                        'saq_empid'         =>$this->emp_id,
                        'saq_dgree'         =>$degree[$c],
                        'saq_board_univ'    =>$buni1[$c],
                        'saq_result'        =>$result1[$c],
                        'saq_yopass'        =>$ypass1[$c],
                        'saq_discipline'    =>$dpln1[$c],
                        'saq_creatorid'     =>$this->session->userdata('username'),
                        'saq_creatordate'   =>date('Y-m-d'),
                        'saq_modifierid'    =>$this->session->userdata('username'),
                        'saq_modifydate'    =>date('Y-m-d'),
                    );   
                    
                    if(!empty($degree[$c])){
                        $dupcheck = array(
                        'saq_empid'  =>$this->emp_id,
                        'saq_dgree'  =>$degree[$c],
                
                        ); 
                        $degreedup = $this->sismodel->isduplicatemore('staff_academic_qualification', $dupcheck);
                        if(!$degreedup){
                            $academicflag=$this->sismodel->insertrec('staff_academic_qualification', $data1);
                        }    
                    }
                }//closer foreach
                /**********************pgdiploma****************************************/
                $data2 = array(
                    'saq_empid'         =>$this->emp_id,
                    'saq_dgree'         =>$_POST['pgdiploma'],
                    'saq_board_univ'    =>$_POST['board2'],
                    'saq_result'        =>$_POST['result2'],
                    'saq_yopass'        =>$_POST['yopass2'],
                    'saq_discipline'    =>$_POST['discipline2'],
		    'saq_creatorid'     =>$this->session->userdata('username'),
                    'saq_creatordate'   =>date('Y-m-d'),
		    'saq_modifierid'    =>$this->session->userdata('username'),
                    'saq_modifydate'    =>date('Y-m-d'),
                );
                if(!empty($_POST['board2'])){
                    $dupcheck = array(
                        'saq_empid'  =>$this->emp_id,
                        'saq_dgree'  =>$_POST['pgdiploma'],
                
                    ); 
                    $diplomadup = $this->sismodel->isduplicatemore('staff_academic_qualification', $dupcheck);
                    if(!$diplomadup){
                        $academicflag=$this->sismodel->insertrec('staff_academic_qualification', $data2);
                    }    
                }
                /**********************pg degree****************************************/
                $pgdegree = $this->input->post('pgdegree', TRUE);
                $buni3 = $this->input->post('board3', TRUE);
                $result3 = $this->input->post('result3', TRUE);
                $ypass3 = $this->input->post('yopass3', TRUE);
                $dpln3 = $this->input->post('discipline3', TRUE);
                
                foreach($pgdegree as $a3 => $b3){
                        
                    $data3 = array(
                        'saq_empid'         =>$this->emp_id,
                        'saq_dgree'         =>$pgdegree[$a3],
                        'saq_board_univ'    =>$buni3[$a3],
                        'saq_result'        =>$result3[$a3],
                        'saq_yopass'        =>$ypass3[$a3],
                        'saq_discipline'    =>$dpln3[$a3],
                        'saq_creatorid'     =>$this->session->userdata('username'),
                        'saq_creatordate'   =>date('Y-m-d'),
                        'saq_modifierid'    =>$this->session->userdata('username'),
                        'saq_modifydate'    =>date('Y-m-d'),
                    );    
                
                    if(!empty($pgdegree[$a3])){
                        $dupcheck = array(
                        'saq_empid'  =>$this->emp_id,
                        'saq_dgree'  =>$pgdegree[$a3],
                
                        ); 
                        $pgdup = $this->sismodel->isduplicatemore('staff_academic_qualification', $dupcheck);
                        if(!$pgdup){
                            $academicflag=$this->sismodel->insertrec('staff_academic_qualification', $data3);
                        }    
                    }
                }//closerforeach
                /*********************mphil*****************************************/
                $data4 = array(
                    'saq_empid'         =>$this->emp_id,
                    'saq_dgree'         =>$_POST['MPhil'],
                    'saq_board_univ'    =>$_POST['board4'],
                    'saq_result'        =>$_POST['result4'],
                    'saq_yopass'        =>$_POST['yopass4'],
                    'saq_discipline'    =>$_POST['discipline4'],
		    'saq_creatorid'     =>$this->session->userdata('username'),
                    'saq_creatordate'   =>date('Y-m-d'),
		    'saq_modifierid'    =>$this->session->userdata('username'),
                    'saq_modifydate'    =>date('Y-m-d'),
                );
                if(!empty($_POST['board4'])){
                   // $mphilflag=$this->sismodel->insertrec('staff_academic_qualification', $data4);
                    $dupcheck = array(
                        'saq_empid'  =>$this->emp_id,
                        'saq_dgree'  =>$_POST['MPhil'],
                
                    ); 
                    $mphildup = $this->sismodel->isduplicatemore('staff_academic_qualification', $dupcheck);
                    if(!$mphildup){
                        $academicflag=$this->sismodel->insertrec('staff_academic_qualification', $data4);
                    }    
                }    
                /************************phd**************************************/
                $data5 = array(
                    'saq_empid'         =>$this->emp_id,
                    'saq_dgree'         =>$_POST['PhD'],
                    'saq_board_univ'    =>$_POST['board5'],
                    'saq_result'        =>$_POST['result5'],
                    'saq_yopass'        =>$_POST['yopass5'],
                    'saq_discipline'    =>$_POST['discipline5'],
		    'saq_creatorid'     =>$this->session->userdata('username'),
                    'saq_creatordate'   =>date('Y-m-d'),
		    'saq_modifierid'    =>$this->session->userdata('username'),
                    'saq_modifydate'    =>date('Y-m-d'),
                );
                if(!empty($_POST['board5'])){
                    //$phdflag=$this->sismodel->insertrec('staff_academic_qualification', $data5);
                    $dupcheck = array(
                        'saq_empid'  =>$this->emp_id,
                        'saq_dgree'  =>$_POST['PhD'],
                
                    ); 
                    $phddup = $this->sismodel->isduplicatemore('staff_academic_qualification', $dupcheck);
                    if(!$phddup){
                        $academicflag=$this->sismodel->insertrec('staff_academic_qualification', $data5);
                    }    
                    
                }
                /*************************pdf*************************************/
                $data6 = array(
                    'saq_empid'         =>$this->emp_id,
                    'saq_dgree'         =>$_POST['PDF'],
                    'saq_board_univ'    =>$_POST['board6'],
                    'saq_result'        =>$_POST['result6'],
                    'saq_yopass'        =>$_POST['yopass6'],
                    'saq_discipline'    =>$_POST['discipline6'],
		    'saq_creatorid'     =>$this->session->userdata('username'),
                    'saq_creatordate'   =>date('Y-m-d'),
		    'saq_modifierid'    =>$this->session->userdata('username'),
                    'saq_modifydate'    =>date('Y-m-d'),
                );
                if(!empty($_POST['board6'])){
                    //$pdfflag
                    $dupcheck = array(
                        'saq_empid'  =>$this->emp_id,
                        'saq_dgree'  =>$_POST['PDF'],
                
                    ); 
                    $pdfdup = $this->sismodel->isduplicatemore('staff_academic_qualification', $dupcheck);
                    if(!$pdfdup){
                        $academicflag=$this->sismodel->insertrec('staff_academic_qualification', $data6);
                    }    
                }
                if(!$academicflag)
                {
                    $this->logger->write_logmessage("error","Error in insert staff academic qualification  record", "Error in insert staff academic qualification  record." );
                    $this->logger->write_dblogmessage("error","Error in insert   staff academic qualification  record ", "Error in insert staff academic qualification  record " );
                    $this->session->set_flashdata('err_message','Error in insert  staff academic qualification  record or record is already exists');
                    redirect('empmgmt/add_academicqualification/'.$empid);
                }
                else{
                    $empcode=$this->sismodel->get_listspfic1('employee_master','emp_code','emp_id',$empid)->emp_code;
                    $empuserid=$this->sismodel->get_listspfic1('employee_master','emp_userid','emp_id',$empid)->emp_userid;
                    $empemail=$this->sismodel->get_listspfic1('employee_master','emp_email','emp_id',$empid)->emp_email;
                    $this->logger->write_logmessage("insert","Add  staff academic qualification  Data", "Staff academic qualification  records insert successfully." );
                    $this->logger->write_dblogmessage("insert","Add  staff academic qualification  Data", "Staff academic qualification  record insert successfully ." );
                    $this->session->set_flashdata('success',' Staff academic qualification record insert successfully.'."["." "."Employee PF NO:"." ".$empcode." and "."Username:"." ".$empemail." "."]");
                    if($this->roleid == 4){
                        redirect('empmgmt/viewempprofile');
                    }
                    else{
                        redirect('report/academic_profile/'.$empid);
                    }
                                       
                }
                
            }//else
            
	    
        }//submitbutton
        
        $this->load->view('empmgmt/add_academicprofile');
    }
    /***********************************closer Add academic qualification******************************************/
    
    /*********************************** Add Technical qualification******************************************/
    public function add_technicalprofile($empid){
        $this->roleid=$this->session->userdata('id_role');
        $this->emp_id = $empid;
        if(isset($_POST['addtechprofile'])){
            
            //form validation
            /*******************************sec1*******************************************/
            $this->form_validation->set_rules('diploma','Diploma','trim|xss_clean');
	    $this->form_validation->set_rules('board','Board University','trim|xss_clean');
	    $this->form_validation->set_rules('result','result','trim|xss_clean');
            $this->form_validation->set_rules('yopass','Year of Passing','trim|xss_clean');
	    $this->form_validation->set_rules('discipline','Discipline','trim|xss_clean');
            
            /*******************************sec2*******************************************/
            $this->form_validation->set_rules('iti','ITI','trim|xss_clean');
	    $this->form_validation->set_rules('board1','Board University','trim|xss_clean');
	    $this->form_validation->set_rules('result1','result','trim|xss_clean');
            $this->form_validation->set_rules('yopass1','Year of Passing','trim|xss_clean');
	    $this->form_validation->set_rules('discipline1','Discipline1','trim|xss_clean');
                              
            /**********************************sec3*****************************************/
            $this->form_validation->set_rules('ccourse','Certified Course','trim|xss_clean');
	    $this->form_validation->set_rules('board2','Board University','trim|xss_clean');
	    $this->form_validation->set_rules('result2','result','trim|xss_clean');
            $this->form_validation->set_rules('yopass2','Year of Passing','trim|xss_clean');
	    $this->form_validation->set_rules('discipline2','Discipline1','trim|xss_clean');
            
            
            /*******************************sec4*******************************************/
            $this->form_validation->set_rules('shorthand','Shorthand','trim|xss_clean');
	    $this->form_validation->set_rules('board3','Board University','trim|xss_clean');
	    $this->form_validation->set_rules('result3','result','trim|xss_clean');
            $this->form_validation->set_rules('yopass3','Year of Passing','trim|xss_clean');
	    $this->form_validation->set_rules('discipline3','Discipline1','trim|xss_clean');
            
            /*****************************sec5************************************************/
            $this->form_validation->set_rules('typing','typing','trim|xss_clean');
	    $this->form_validation->set_rules('board4','Board University','trim|xss_clean');
	    $this->form_validation->set_rules('result4','result','trim|xss_clean');
            $this->form_validation->set_rules('yopass4','Year of Passing','trim|xss_clean');
	    $this->form_validation->set_rules('discipline4','Discipline1','trim|xss_clean');
            
            /*****************************sec************************************************/
            if($this->form_validation->run() == FALSE){
            
                $this->load->view('empmgmt/add_technicalprofile',$this->emp_id);
                return;
            }// if form 
            else{
                $techflag=false;
                
                $data = array(
                    'stq_empid'         =>$this->emp_id,
                    'stq_dgree'         =>$_POST['diploma'],
                    'stq_board_univ'    =>$_POST['board'],
                    'stq_result'        =>$_POST['result'],
                    'stq_dopass'        =>$_POST['yopass'],
                    'stq_discipline'    =>$_POST['discipline'],
                    'stq_creatorid'     =>$this->session->userdata('username'),
                    'stq_creatordate'   =>date('Y-m-d'),
                    'stq_modifierid'    =>$this->session->userdata('username'),
                    'stq_modifydate'    =>date('Y-m-d'),
                );
                if(!empty($_POST['board'])){
                    $dupcheck = array(
                        'stq_empid'  =>$this->emp_id,
                        'stq_dgree'  =>$_POST['diploma'],
                
                    ); 
                    $dipdup = $this->sismodel->isduplicatemore('staff_technical_qualification', $dupcheck);
                    if(!$dipdup){
                        $techflag=$this->sismodel->insertrec('staff_technical_qualification', $data);
                    }    
                }
                /*******************************************************************************/
                 
                $data2 = array(
                    'stq_empid'         =>$this->emp_id,
                    'stq_dgree'         =>$_POST['iti'],
                    'stq_board_univ'    =>$_POST['board1'],
                    'stq_result'        =>$_POST['result1'],
                    'stq_dopass'        =>$_POST['yopass1'],
                    'stq_discipline'    =>$_POST['discipline1'],
                    'stq_creatorid'     =>$this->session->userdata('username'),
                    'stq_creatordate'   =>date('Y-m-d'),
                    'stq_modifierid'    =>$this->session->userdata('username'),
                    'stq_modifydate'    =>date('Y-m-d'),
                );
                if(!empty($_POST['board1'])){
                    $dupcheck = array(
                        'stq_empid'  =>$this->emp_id,
                        'stq_dgree'  =>$_POST['iti'],
                
                    ); 
                    $itidup = $this->sismodel->isduplicatemore('staff_technical_qualification', $dupcheck);
                    if(!$itidup){
                        $techflag=$this->sismodel->insertrec('staff_technical_qualification', $data2);
                    }    
                }
                /****************************************************************************************/
                
                $data3 = array(
                    'stq_empid'         =>$this->emp_id,
                    'stq_dgree'         =>$_POST['ccourse'],
                    'stq_board_univ'    =>$_POST['board2'],
                    'stq_result'        =>$_POST['result2'],
                    'stq_dopass'        =>$_POST['yopass2'],
                    'stq_discipline'    =>$_POST['discipline2'],
                    'stq_creatorid'     =>$this->session->userdata('username'),
                    'stq_creatordate'   =>date('Y-m-d'),
                    'stq_modifierid'    =>$this->session->userdata('username'),
                    'stq_modifydate'    =>date('Y-m-d'),
                );
                if(!empty($_POST['board2'])){
                    $dupcheck = array(
                        'stq_empid'  =>$this->emp_id,
                        'stq_dgree'  =>$_POST['ccourse'],
                
                    ); 
                    $ccoursedup = $this->sismodel->isduplicatemore('staff_technical_qualification', $dupcheck);
                    if(!$ccoursedup){
                        $techflag=$this->sismodel->insertrec('staff_technical_qualification', $data3);
                    }    
                }
                
                /***************************shorthand*********************************************************/
                $shorthand = $this->input->post('shorthand', TRUE);
                $buni3 = $this->input->post('board3', TRUE);
                $result3 = $this->input->post('result3', TRUE);
                $ypass3 = $this->input->post('yopass3', TRUE);
                $dpln3 = $this->input->post('discipline3', TRUE);
                
                foreach($shorthand as $a3 => $b3){
                    $data4 = array(
                        'stq_empid'         =>$this->emp_id,
                        'stq_dgree'         =>$shorthand[$a3],
                        'stq_board_univ'    =>$buni3[$a3],
                        'stq_result'        =>$result3[$a3],
                        'stq_dopass'        =>$ypass3[$a3],
                        'stq_discipline'    =>$dpln3[$a3],
                        'stq_creatorid'     =>$this->session->userdata('username'),
                        'stq_creatordate'   =>date('Y-m-d'),
                        'stq_modifierid'    =>$this->session->userdata('username'),
                        'stq_modifydate'    =>date('Y-m-d'),
                    );
                    if(!empty($shorthand[$a3])){
                        $dupcheck = array(
                        'stq_empid'  =>$this->emp_id,
                        'stq_dgree'  =>$shorthand[$a3],
                
                        ); 
                        $shorthdup = $this->sismodel->isduplicatemore('staff_technical_qualification', $dupcheck);
                        if(!$shorthdup){
                            $techflag=$this->sismodel->insertrec('staff_technical_qualification', $data4);
                        }    
                    }
                }//foreach    
                
                /**************************Typing**********************************************************/
                
                $typing = $this->input->post('typing', TRUE);
                $buni4 = $this->input->post('board4', TRUE);
                $result4 = $this->input->post('result4', TRUE);
                $ypass4 = $this->input->post('yopass4', TRUE);
                $dpln4 = $this->input->post('discipline4', TRUE);
                
                foreach($shorthand as $a4 => $b4){
                    $data5 = array(
                        'stq_empid'         =>$this->emp_id,
                        'stq_dgree'         =>$typing[$a4],
                        'stq_board_univ'    =>$buni4[$a4],
                        'stq_result'        =>$result4[$a4],
                        'stq_dopass'        =>$ypass4[$a4],
                        'stq_discipline'    =>$dpln4[$a4],
                        'stq_creatorid'     =>$this->session->userdata('username'),
                        'stq_creatordate'   =>date('Y-m-d'),
                        'stq_modifierid'    =>$this->session->userdata('username'),
                        'stq_modifydate'    =>date('Y-m-d'),
                    );
                    if(!empty($typing[$a4])){
                        $dupcheck = array(
                        'stq_empid'  =>$this->emp_id,
                        'stq_dgree'  =>$typing[$a4],
                
                        ); 
                        $typingdup = $this->sismodel->isduplicatemore('staff_technical_qualification', $dupcheck);
                        if(!$typingdup){
                            $techflag=$this->sismodel->insertrec('staff_technical_qualification', $data5);
                        }    
                    }
                }//foreach  
                if(!$techflag)
                {
                    $this->logger->write_logmessage("error","Error in insert staff technical qualification  record", "Error in insert staff technical qualification  record." );
                    $this->logger->write_dblogmessage("error","Error in insert   staff technical qualification  record ", "Error in insert staff technical qualification  record " );
                    $this->session->set_flashdata('err_message','Error in insert  staff technical qualification  record or record is already exists');
                    redirect('empmgmt/add_technicalprofile/'.$empid);
                }
                else{
                    $empcode=$this->sismodel->get_listspfic1('employee_master','emp_code','emp_id',$empid)->emp_code;
                    $empuserid=$this->sismodel->get_listspfic1('employee_master','emp_userid','emp_id',$empid)->emp_userid;
                    $empemail=$this->sismodel->get_listspfic1('employee_master','emp_email','emp_id',$empid)->emp_email;
                    
                    $this->logger->write_logmessage("insert","Add  staff technical qualification  Data", "Staff technical qualification  records insert successfully." );
                    $this->logger->write_dblogmessage("insert","Add  staff technical qualification  Data", "Staff technical qualification  record insert successfully ." );
                    $this->session->set_flashdata('success',' Staff technical qualification record insert successfully.'."["." "."Employee PF NO:"." ".$empcode." and "."Username:"." ".$empemail." "."]");
                    if($this->roleid == 4){
                        redirect('empmgmt/viewempprofile');
                    }
                    else{
                        redirect('report/technical_profile/'.$empid);
                    }
                                       
                }
                
            }//closer else
            
        }
        $this->load->view('empmgmt/add_technicalprofile');
    }  
    
    /*****************edit staff academic profile ********************************************************/
    public function edit_academicprofile($empid){
        $this->roleid=$this->session->userdata('id_role');
        $this->emp_id = $empid;
       // echo"seema1".$empid;
        $selectfield="*";
        $whdata = array ('saq_empid' => $this->emp_id);
        $data['academicdata'] = $this->sismodel->get_orderlistspficemore('staff_academic_qualification',$selectfield,$whdata,'');
        if(isset($_POST['updateacadrofile'])) {
            
            $this->form_validation->set_rules('stdclass','stdclass','trim|xss_clean');
	    $this->form_validation->set_rules('board','Board University','trim|xss_clean');
	    $this->form_validation->set_rules('result','result','trim|xss_clean');
            $this->form_validation->set_rules('yopass','Year of Passing','trim|xss_clean');
	    $this->form_validation->set_rules('discipline','Discipline','trim|xss_clean');
            
            $tsize = $this->input->post('totalsize', TRUE);
            for ($i=0; $i<$tsize ;$i++){    
                $entryid = $this->input->post('entryid'.$i, TRUE);
                $stdclass = $this->input->post('stdclass'.$i, TRUE);
                $board = $this->input->post('board'.$i, TRUE);
                $result = $this->input->post('result'.$i, TRUE);
                $yopass = $this->input->post('yopass'.$i, TRUE);
                $dspln = $this->input->post('discipline'.$i, TRUE);
                echo "entryid===".$entryid."class===".$stdclass."board==".$board."tsize==".$tsize;
               // die;
                $updata=array(
                    'saq_empid'         =>$this->emp_id,
                   // 'saq_dgree'         =>$_POST['stdclass'],
                    'saq_board_univ'    =>$board,
                    'saq_result'        =>$result,
                    'saq_yopass'        =>$yopass,
                    'saq_discipline'    =>$dspln,
		   // 'saq_creatorid'     =>$this->session->userdata('username'),
                    //'saq_creatordate'   =>date('Y-m-d'),
		    'saq_modifierid'    =>$this->session->userdata('username'),
                    'saq_modifydate'    =>date('Y-m-d'),
                    
                );
                $updateflag = $this->sismodel->updaterec('staff_academic_qualification',$updata,'saq_id',$entryid);
            }//tsize
           // die;
            if (!$updateflag)
            {
                $this->logger->write_logmessage("update","Trying to update staff academic qualification  record ", " staff academic qualification  record is not updated ".$etname);
                $this->logger->write_dblogmessage("update","Trying to update staff academic qualification  record", " staff academic qualification  record is not added ".$etname);
                $this->session->set_flashdata('err_message','Error in  update staff academic qualification  record - '  , 'error');
                redirect('empmgmt/edit_academicprofile',$data);
            }
            else{
                $this->logger->write_logmessage("update"," staff academic qualification record updated ", "staff academic qualification record updated successfully...");
                $this->logger->write_dblogmessage("update"," staff academic qualification record updated ", "staff academic qualification record updated  successfully...");
                $this->session->set_flashdata("success", "   academic qualification record updated successfully...");
                redirect("empmgmt/edit_academicprofile/".$this->emp_id);
            }
        }
        $this->load->view('empmgmt/edit_academicprofile',$data);
        
    }
    /*********************This function Delete records ****************************************************/
    
    public function delete_academicprofile($id) {
        $this->roleid=$this->session->userdata('id_role');
        $this->emp_id=$this->sismodel->get_listspfic1('staff_academic_qualification', 'saq_empid', 'saq_id',$id)->saq_empid;
        /* Deleting academicprofile Record */
        $delflag=$this->sismodel->deleterow('staff_academic_qualification','saq_id',$id);
        if (! delflag   )
        {   
            $this->logger->write_logmessage("delete", "Error in deleting staff academic qualification record" . " [id:" . $id . "]");
            $this->logger->write_dblogmessage("delete", "Error in deleting staff academic qualification record" . " [id:" . $id . "]");
            $this->session->set_flashdata('Error in deleting deleting staff academic qualification record - ');
            redirect('empmgmt/edit_academicprofile');
        }
        else{
         
            $this->logger->write_logmessage("delete", " Deleted staff academic qualification Record  ". " [id:" . $id . "]");
            $this->logger->write_dblogmessage("delete", "Deleted staff academic qualification Record  " . " [id:" . $id . "]");
            $this->session->set_flashdata("success", 'Record  Deleted successfully ...' );
            redirect('empmgmt/edit_academicprofile/'.$this->emp_id);
        }
        $this->load->view('empmgmt/edit_academicprofile');
        
    }
    
    /*************************************************************************************************************/
    
    /*****************edit staff technical profile ********************************************************/
    public function edit_technicalprofile($empid){
        $this->roleid=$this->session->userdata('id_role');
        $this->emp_id = $empid;
       // echo"seema1".$empid;
        $selectfield="*";
        $whdata = array ('stq_empid' => $this->emp_id);
        $data['technicaldata'] = $this->sismodel->get_orderlistspficemore('staff_technical_qualification',$selectfield,$whdata,'');
        if(isset($_POST['updateacadrofile'])) {
            
            $this->form_validation->set_rules('stdclass','stdclass','trim|xss_clean');
	    $this->form_validation->set_rules('board','Board University','trim|xss_clean');
	    $this->form_validation->set_rules('result','result','trim|xss_clean');
            $this->form_validation->set_rules('yopass','Year of Passing','trim|xss_clean');
	    $this->form_validation->set_rules('discipline','Discipline','trim|xss_clean');
            
            $tsize = $this->input->post('totalsize', TRUE);
            for ($i=0; $i<$tsize ;$i++){    
                $entryid = $this->input->post('entryid'.$i, TRUE);
                $stdclass = $this->input->post('stdclass'.$i, TRUE);
                $board = $this->input->post('board'.$i, TRUE);
                $result = $this->input->post('result'.$i, TRUE);
                $yopass = $this->input->post('yopass'.$i, TRUE);
                $dspln = $this->input->post('discipline'.$i, TRUE);
              //  echo "entryid===".$entryid."class===".$stdclass."board==".$board."tsize==".$tsize;
               // die;
                             
                $updata=array(
                    'stq_empid'         =>$this->emp_id,
                    'stq_board_univ'    =>$board,
                    'stq_result'        =>$result,
                    'stq_dopass'        =>$yopass,
                    'stq_discipline'    =>$dspln,
		    'stq_modifierid'    =>$this->session->userdata('username'),
                    'stq_modifydate'    =>date('Y-m-d'),
                    
                );
                $updateflag = $this->sismodel->updaterec('staff_technical_qualification',$updata,'stq_id',$entryid);
            }//tsize
            if (!$updateflag)
            {
                $this->logger->write_logmessage("update","Trying to update staff technical qualification  record ", " staff technical qualification  record is not updated ".$etname);
                $this->logger->write_dblogmessage("update","Trying to update staff technical qualification  record", " staff technical qualification  record is not added ".$etname);
                $this->session->set_flashdata('err_message','Error in  update staff technical qualification  record - '  , 'error');
                redirect('empmgmt/edit_technicalprofile',$data);
            }
            else{
                $this->logger->write_logmessage("update"," staff technical qualification record updated ", "staff technical qualification record updated successfully...");
                $this->logger->write_dblogmessage("update"," staff technical qualification record updated ", "staff technical qualification record updated  successfully...");
                $this->session->set_flashdata("success", "   technical qualification record updated successfully...");
                redirect("empmgmt/edit_technicalprofile/".$this->emp_id);
            }
        }
        $this->load->view('empmgmt/edit_technicalprofile',$data);
        
    }
    /**This function Delete records */
    public function delete_techprofile($id) {
        $this->roleid=$this->session->userdata('id_role');
        $this->emp_id=$this->sismodel->get_listspfic1('staff_technical_qualification', 'stq_empid', 'stq_id',$id)->stq_empid;
        /* Deleting academicprofile Record */
        $delflag=$this->sismodel->deleterow('staff_technical_qualification','stq_id',$id);
        if (! delflag   )
        {   
            $this->logger->write_logmessage("delete", "Error in deleting staff technical qualification record" . " [id:" . $id . "]");
            $this->logger->write_dblogmessage("delete", "Error in deleting staff technical qualification record" . " [id:" . $id . "]");
            $this->session->set_flashdata('Error in deleting deleting staff technical qualification record - ');
            redirect('empmgmt/edit_technicalprofile');
        }
        else{
         
            $this->logger->write_logmessage("delete", " Deleted staff technical qualification Record  ". " [id:" . $id . "]");
            $this->logger->write_dblogmessage("delete", "Deleted staff technical qualification Record  " . " [id:" . $id . "]");
            $this->session->set_flashdata("success", 'Record  Deleted successfully ...' );
            redirect('empmgmt/edit_technicalprofile/'.$this->emp_id);
        }
        $this->load->view('empmgmt/edit_technicalprofile');
        
    }//closer
    /**This function Delete records */
    public function delete_serviceprofile($id) {
        $this->roleid=$this->session->userdata('id_role');
        $usrid=$this->session->userdata('id_user');
        $this->emp_id=$this->sismodel->get_listspfic1('employee_servicedetail', 'empsd_empid', 'empsd_id',$id)->empsd_empid;
	if( $usrid == 1){
        /* Deleting academicprofile Record */
        $delflag=$this->sismodel->deleterow('employee_servicedetail','empsd_id',$id);
        if (! delflag   )
        {
            $this->logger->write_logmessage("delete", "Error in deleting employee_servicedetail record" . " [id:" . $id . "]");
            $this->logger->write_dblogmessage("delete", "Error in deleting employee_servicedetail record" . " [id:" . $id . "]");
            $this->session->set_flashdata("err_message",'Error in deleting deleting employee_servicedetail record - ');
            redirect('report/service_profile/'.$this->emp_id);
        }
        else{

            $this->logger->write_logmessage("delete", " Deleted employee_servicedetail Record  ". " [id:" . $id . "]");
            $this->logger->write_dblogmessage("delete", "Deleted employee_servicedetail Record  " . " [id:" . $id . "]");
            $this->session->set_flashdata("success", 'Record  Deleted successfully ...' );
            redirect('report/service_profile/'.$this->emp_id);
        }
	}
	else{
	    $lemail = $this->lgnmodel->get_listspfic1('edrpuser', 'username', 'id',$usrid)->username;
	    $this->logger->write_logmessage("delete", " User ". $lemail ." ( ".$usrid .") want to Delete employee_servicedetail Record  ". " [id:" . $id . "]");
            $this->logger->write_dblogmessage("delete", " User " .  $lemail ." ( ".$usrid .") want to Delete employee_servicedetail Record  " . " [id:" . $id . "]");
            $this->session->set_flashdata("err_message", 'Sorry. You do not have the right to delete the employee service record.' );
            redirect('report/service_profile/'.$this->emp_id);
	}
        $this->load->view('report/service_profile/'.$this->emp_id);

    }//closer 
    
    
    
}//classcloser    
    
    
