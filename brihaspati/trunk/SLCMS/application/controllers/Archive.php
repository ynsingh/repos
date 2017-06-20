<?php

/* 
 * @name Archive.php
 * @author Nagendra Kumar Singh(nksinghiitk@gmail.com)  
 */
 
defined('BASEPATH') OR exit('No direct script access allowed');


class Archive extends CI_Controller
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
        	$this->feesmastera();
    	}

	/** This function Display the fees with headwise list records */
        public function feesmastera() {
        	$this->fmaresult = $this->common_model->get_list('fees_master_archive');
	        $this->logger->write_logmessage("view"," View fees list head wise", "Fees setting details...");
        	$this->logger->write_dblogmessage("view"," View fees list head wise", "Fees setting details...");
	        $this->load->view('archive/feesmastera');
        }
}

