<?php

/* 
 * @name Audittr.php
 * @author Nagendra Kumar Singh(nksinghiitk@gmail.com)  
 */
 
defined('BASEPATH') OR exit('No direct script access allowed');


class Audittr extends CI_Controller
{
	function __construct() {
        	parent::__construct();
		$this->load->model('common_model'); 
        	if(empty($this->session->userdata('id_user'))) {
	        	$this->session->set_flashdata('flash_data', 'You don\'t have access!');
			redirect('welcome');
        	}
    	}

    	public function index() {
        	$this->logdetail();
    	}

	/** This function Display the fees with headwise list records */
	public function logdetail() {
		$whdata1=array('level' => 1);
        	$this->logdresult1 = $this->common_model->get_listspficemore('logs','date,user,host_ip,message_title,message_desc',$whdata1);
		$whdata2=array('level' => 2);
        	$this->logdresult2 = $this->common_model->get_listspficemore('logs','date,user,host_ip,message_title,message_desc',$whdata2);
		$whdata3=array('level' => 3);
        	$this->logdresult3 = $this->common_model->get_listspficemore('logs','date,user,host_ip,message_title,message_desc',$whdata3);
//	        $this->logger->write_logmessage("view"," View log details", "log details details...");
  //      	$this->logger->write_dblogmessage("view"," View log details", "log details...");
	        $this->load->view('audittr/logdetail');
        }
}

