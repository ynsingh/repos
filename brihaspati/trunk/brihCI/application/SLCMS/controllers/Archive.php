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
		$this->load->model('login_model','logmodel'); 
		
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
        	$data['fmaresult'] = $this->common_model->get_list('fees_master_archive');
	        $this->logger->write_logmessage("view"," View fees list archive head wise", "Fees setting archive details...");
        	$this->logger->write_dblogmessage("view"," View fees list archive head wise", "Fees setting archive details...");
	        $this->load->view('archive/feesmastera',$data);
	}

	/** This function Display the Program Subject Paper archive records */
        public function prgsubpapa() {
        	$data['prgsubaresult'] = $this->common_model->get_list('subject_paper_archive');
	        $this->logger->write_logmessage("view"," View Subject paper archive ", "Program Subject paper archive details...");
        	$this->logger->write_dblogmessage("view"," View subject paper archive", "Program Subject Paper archive details...");
	        $this->load->view('archive/prgsubpapa',$data);
	}

	/** This function Display the semester rule list archive records */
        public function semrulea() {
        	$data['sraresult'] = $this->common_model->get_list('semester_rule_archive');
	        $this->logger->write_logmessage("view"," View semester rule archive ", "Semester rule archive details...");
        	$this->logger->write_dblogmessage("view"," View semester rule archive", "Semester rule archive details...");
	        $this->load->view('archive/semrulea',$data);
	}

	/** This function Display the Subject semester Program list archive records */
        public function subsema() {
        	$data['ssaresult'] = $this->common_model->get_list('subject_semester_archive');
	        $this->logger->write_logmessage("view"," View Subject semester Program archive ", "Subject semester Program archive details...");
        	$this->logger->write_dblogmessage("view"," View Subject semester Program archive", "Subject Semester Program archive details...");
	        $this->load->view('archive/subsema',$data);
	}
	/** This function Display the Authority list archive records */
        public function authoritya() {
        	$data['authresult'] = $this->logmodel->get_list('authority_archive');
	        $this->logger->write_logmessage("view"," View Authority archive ", "Authority archive details...");
        	$this->logger->write_dblogmessage("view"," View Authority archive", "Authority archive details...");
	        $this->load->view('archive/authoritya',$data);
	}
	 /** This function Display the Exam Center list archive records */
        public function examcentera() {
                $data['exresult'] = $this->common_model->get_list('admissionstudent_enterenceexamcentera');
                $this->logger->write_logmessage("view"," View Authority archive ", "Authority archive details...");
                $this->logger->write_dblogmessage("view"," View Authority archive", "Authority archive details...");
                $this->load->view('archive/examcentera',$data);
        }
        /*this function has been created for display the Department archive records */
        public function departmenta(){
                $data['deptaresult'] = $this->common_model->get_list('Department_archive');
                $this->logger->write_logmessage("view"," View Department archive ", "Department archive details...");
                $this->logger->write_dblogmessage("view"," View Department archive", "Department archive details...");
                $this->load->view('archive/departmenta',$data);
        }

/** This function Display the Announcement list archive records */
        public function announcementa() {
                $data['annoresult'] = $this->common_model->get_list('announcement_archive');
                $this->logger->write_logmessage("view"," View Announcement archive", "Announcement archive details...");
                $this->logger->write_dblogmessage("view"," View Announcement archive", "Announcement archive details...");
                $this->load->view('archive/announcementa',$data);
        }

}

