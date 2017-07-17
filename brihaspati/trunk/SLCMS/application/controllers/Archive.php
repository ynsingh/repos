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

	/** This function Display the fees with headwise list archive records */
        public function feesmastera() {
        	$this->fmaresult = $this->common_model->get_list('fees_master_archive');
	        $this->logger->write_logmessage("view"," View fees list archive head wise", "Fees setting archive details...");
        	$this->logger->write_dblogmessage("view"," View fees list archive head wise", "Fees setting archive details...");
	        $this->load->view('archive/feesmastera');
	}

	/** This function Display the Program Subject Paper archive records */
        public function prgsubpapa() {
        	$this->prgsubaresult = $this->common_model->get_list('subject_paper_archive');
	        $this->logger->write_logmessage("view"," View Subject paper archive ", "Program Subject paper archive details...");
        	$this->logger->write_dblogmessage("view"," View subject paper archive", "Program Subject Paper archive details...");
	        $this->load->view('archive/prgsubpapa');
	}

	/** This function Display the semester rule list archive records */
        public function semrulea() {
        	$this->sraresult = $this->common_model->get_list('semester_rule_archive');
	        $this->logger->write_logmessage("view"," View semester rule archive ", "Semester rule archive details...");
        	$this->logger->write_dblogmessage("view"," View semester rule archive", "Semester rule archive details...");
	        $this->load->view('archive/semrulea');
	}

	/** This function Display the Subject semester Program list archive records */
        public function subsema() {
        	$this->ssaresult = $this->common_model->get_list('subject_semester_archive');
	        $this->logger->write_logmessage("view"," View Subject semester Program archive ", "Subject semester Program archive details...");
        	$this->logger->write_dblogmessage("view"," View Subject semester Program archive", "Subject Semester Program archive details...");
	        $this->load->view('archive/subsema');
	}
}
