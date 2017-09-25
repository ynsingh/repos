<?php

/* 
 * @name Archive.php
 * @author Nagendra Kumar Singh(nksinghiitk@gmail.com)
 * @author Om Prakash (omprakashkgp@gmail.com) Staff Position archive
 */
 
defined('BASEPATH') OR exit('No direct script access allowed');


class Archive extends CI_Controller
{
	function __construct() {
        	parent::__construct();
		$this->load->model('common_model'); 
		$this->load->model('login_model','logmodel'); 
	        $this->load->model('SIS_model',"sismodel");
	
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
	/** This function Display the Authority list archive records */
        public function authoritya() {
        	$this->authresult = $this->logmodel->get_list('authority_archive');
	        $this->logger->write_logmessage("view"," View Authority archive ", "Authority archive details...");
        	$this->logger->write_dblogmessage("view"," View Authority archive", "Authority archive details...");
	        $this->load->view('archive/authoritya');
	}
  	/*this function has been created for display the staff position archive records */
  	public function staffpositiona(){
        	$this->result = $this->sismodel->get_list('staff_position_archive');
	        $this->logger->write_logmessage("view"," View staff position archive ", "Staff position archive details...");
        	$this->logger->write_dblogmessage("view"," View staff position archive", "Staff position archive details...");
        	$this->load->view('archive/staffpositiona');
  	}

}

