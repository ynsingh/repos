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
        $data['servicedata'] = $this->sismodel->get_listrow('employee_servicedetail','empsd_empid',$emp_id);
        $this->logger->write_logmessage("view"," view employee profile" );
        $this->logger->write_dblogmessage("view"," view employee profile");
        $this->load->view('empmgmt/viewempprofile',$data);
    }
}//classcloser    
    
    