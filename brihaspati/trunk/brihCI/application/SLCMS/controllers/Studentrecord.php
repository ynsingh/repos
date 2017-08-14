<?php

defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name Studentrecord.php
 * @author Nagendra Kumar Singh (nksinghiitk@gmail.com)
 */

class Studentrecord extends CI_Controller
{
 
    function __construct() {
        parent::__construct();
        $this->load->model("Login_model", "logmodel");
        $this->load->model("Common_model", "commodel");
        $this->load->model("User_model", "usrmodel");
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('welcome');
        }
    }
 
    public function index() {
	    /* get role id and user id from session*/
	    $userid = $this->session->userdata('id_user');
	    $roleid = $this->session->userdata('id_role');
    }
    public function subjectrecord()
    {
	$this->result = $this->commodel->get_list('student_program');
        $this->logger->write_logmessage("view"," View Student program and  subject", "View Student program and  subject");
        $this->logger->write_dblogmessage("view"," View Student program and  subject", "View Student program and  subject");
        $this->load->view('student/subjectrecord',$this->result);
     }

 
}

