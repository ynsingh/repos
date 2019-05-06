<?php

defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name Staffhome.php
 * @author Nagendra Kumar Singh (nksinghiitk@gmail.com)
 * @Modification: Manorama Pal (palseema30@gmail.com)
 */

class Staffhome extends CI_Controller
{
 
    function __construct() {
        parent::__construct();
        $this->load->model("Login_model", "logmodel");
        $this->load->model("Common_model", "commodel");
        $this->load->model("User_model", "usrmodel");
        $this->load->model('SIS_model',"sismodel");
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('welcome');
        }
    }
 
    public function index() {
        /* set role id in session*/
	$data = [ 'id_role' => 4 ];
        $this->session->set_userdata($data);
        /* get logged user detail from different tables (firstname, lastname, email, campus name, org name, department name)
         * using login model and common model
         */
        /*$this->name=$this->login->get_listspfic1('userprofile','firstname','userid',$this->session->userdata('id_user'));
        $this->lastn=$this->login->get_listspfic1('userprofile','lastname','userid',$this->session->userdata('id_user'));
        $this->email=$this->login->get_listspfic1('edrpuser','email','id',$this->session->userdata('id_user'));
        $this->campusid=$this->cmodel->get_listspfic1('user_role_type','scid','userid',$this->session->userdata('id_user'))->scid;
        $this->campusname=$this->cmodel->get_listspfic1('study_center','sc_name','sc_id',$this->campusid);
        $this->orgcode=$this->cmodel->get_listspfic1('study_center','org_code','sc_id',$this->campusid);
        $this->orgname=$this->cmodel->get_listspfic1('org_profile','org_name','org_code',$this->orgcode->org_code);
        $this->dptid=$this->cmodel->get_depid('user_role_type',$this->session->userdata('id_user'),2);
        $this->deptname=$this->cmodel->get_listspfic1('Department','dept_name','dept_id',$this->dptid->deptid);*/
        /*get course Detail*/
       /*$selectfield=array('pstp_prgid','pstp_subid','pstp_papid','pstp_acadyear','pstp_sem');
        $this->admcyear=$this->usrmodel->getcurrentAcadYear();
        $data=array(
            'pstp_scid' =>$this->campusid,
            'pstp_teachid' => $this->session->userdata('id_user'),
            'pstp_acadyear' => $this->admcyear
           
        );
        $this->cdetail=$this->cmodel->get_listspficemore('program_subject_teacher',$selectfield,$data);*/
        
        $this->currentlog=$this->session->userdata('username');
        $this->roleid=$this->session->userdata('id_role');
        $this->currentrole=$this->commodel->get_listspfic1('role','role_name','role_id',$this->roleid);
/*
        $this->name=$this->logmodel->get_listspfic1('userprofile','firstname','userid',$this->session->userdata('id_user'));
        $this->lastn=$this->logmodel->get_listspfic1('userprofile','lastname','userid',$this->session->userdata('id_user'));
        $this->address=$this->logmodel->get_listspfic1('userprofile','address','userid',$this->session->userdata('id_user'));
	$this->secmail=$this->logmodel->get_listspfic1('userprofile','secmail','userid',$this->session->userdata('id_user'));
        $this->mobile=$this->logmodel->get_listspfic1('userprofile','mobile','userid',$this->session->userdata('id_user'));
        $this->email=$this->logmodel->get_listspfic1('edrpuser','email','id',$this->session->userdata('id_user'));
        $this->campusid=$this->sismodel->get_listspfic1('user_role_type','scid','userid',$this->session->userdata('id_user'))->scid;
        $this->campusname=$this->commodel->get_listspfic1('study_center','sc_name','sc_id',$this->campusid);
        $this->orgcode=$this->commodel->get_listspfic1('study_center','org_code','sc_id',$this->campusid);
        $this->orgname=$this->commodel->get_listspfic1('org_profile','org_name','org_code',$this->orgcode->org_code);
        $this->load->view('staffhome');
*/
	$data['deptname']="";
	$data['name']=$this->logmodel->get_listspfic1('userprofile','firstname','userid',$this->session->userdata('id_user'))->firstname;
        $data['lastn']=$this->logmodel->get_listspfic1('userprofile','lastname','userid',$this->session->userdata('id_user'))->lastname;
        $data['address']=$this->logmodel->get_listspfic1('userprofile','address','userid',$this->session->userdata('id_user'))->address;
        $data['secmail']=$this->logmodel->get_listspfic1('userprofile','secmail','userid',$this->session->userdata('id_user'))->secmail;
        $data['mobile']=$this->logmodel->get_listspfic1('userprofile','mobile','userid',$this->session->userdata('id_user'))->mobile;
        $data['email']=$this->logmodel->get_listspfic1('edrpuser','email','id',$this->session->userdata('id_user'))->email;
        $this->campusid=$this->sismodel->get_listspfic1('employee_master','emp_scid','emp_userid',$this->session->userdata('id_user'))->emp_scid;
//        $this->campusid=$this->sismodel->get_listspfic1('user_role_type','scid','userid',$this->session->userdata('id_user'))->scid;
        $data['campusname']=$this->commodel->get_listspfic1('study_center','sc_name','sc_id',$this->campusid)->sc_name;
        $data['orgcode']=$this->commodel->get_listspfic1('study_center','org_code','sc_id',$this->campusid)->org_code;
        $data['orgname']=$this->commodel->get_listspfic1('org_profile','org_name','org_code', $data['orgcode'])->org_name;
        $this->load->view('staffhome',$data);


    }
 
}

