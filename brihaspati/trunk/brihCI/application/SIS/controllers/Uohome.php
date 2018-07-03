<?php

defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name Uohome.php
 * @author Nagendra Kumar Singh (nksinghiitk@gmail.com)
 */

class Uohome extends CI_Controller
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
//	$data = [ 'id_role' => 10 ];
  //      $this->session->set_userdata($data);
        /* get logged user detail from different tables (firstname, lastname, email, campus name, org name, department name)
         * using login model and common model
         */
	$this->currentlog=$this->session->userdata('username');
        $this->roleid=$this->session->userdata('id_role');
        $this->currentrole=$this->commodel->get_listspfic1('role','role_name','role_id',$this->roleid);
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
        $this->load->view('uohome');
    }
 
}

