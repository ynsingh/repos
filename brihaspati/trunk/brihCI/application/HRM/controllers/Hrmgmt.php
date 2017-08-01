<?php

/* 
 * @name Hrmgmt.php
 * @author Nagendra Kumar Singh(nksinghiitk@gmail.com)  
 */
 
defined('BASEPATH') OR exit('No direct script access allowed');


class Hrmgmt extends CI_Controller
{
	function __construct() {
        	parent::__construct();
		$this->load->model('hrmgmt_model','hrmmodel'); 
        	if(empty($this->session->userdata('id_user'))) {
	        	$this->session->set_flashdata('flash_data', 'You don\'t have access!');
			redirect('welcome');
        	}
    	}

    	public function index() {
        	$this->view_advertisement();
    	}

	/** This function Display the advertisement records */
	public function view_advertisement(){
		$this->job = $this->hrmmodel->get_list('job_open');
		$this->logger->write_logmessage("view"," Display the advertisement records", "Display the advertisement records...");
        	$this->logger->write_dblogmessage("view"," Display the advertisement records", "Display the advertisement records...");
		$this->load->view('hrmgmt/view_adver');
	}
	
	/** This function Add the advertisement records */
	public function add_advertisement(){
		if(isset($_POST['register'])) {
                 $this->form_validation->set_rules('Jvcode','Job vacancy code','trim|xss_clean|required');
                 $this->form_validation->set_rules('Jadvno','Job advertisement number','trim|xss_clean|required');
                 $this->form_validation->set_rules('Jpcode','Job post code','trim|xss_clean');
                 $this->form_validation->set_rules('Jname','Job post name','trim|xss_clean');
		 $this->form_validation->set_rules('Jdept','Job department name','trim|xss_clean');
	
		 $this->form_validation->set_rules('Jvacsc','Job sc vacancy','trim|xss_clean|required|is_natural');
                 $this->form_validation->set_rules('Jvacst','Job st vacancy','trim|xss_clean|required|is_natural');
                 $this->form_validation->set_rules('Jvacobc','Job obc vacancy','trim|xss_clean|is_natural');
                 $this->form_validation->set_rules('Jvacur','Job ur vacancy','trim|xss_clean|is_natural');
		 $this->form_validation->set_rules('Jvacpwd','Job pwd vancancy','trim|xss_clean|is_natural');
				
		 $this->form_validation->set_rules('Jgroup','Job group','trim|xss_clean|required');
                 $this->form_validation->set_rules('Jgrade','Job grade pay','trim|xss_clean|required');
                 $this->form_validation->set_rules('Jemolu','Job emoluments','trim|xss_clean');
                 $this->form_validation->set_rules('Jagelimit','Job age limit','trim|xss_clean|is_natural');

		 $this->form_validation->set_rules('Jessen','Job essential','trim|xss_clean|required');
                 $this->form_validation->set_rules('Jexper','Job experience','trim|xss_clean|required');
                 $this->form_validation->set_rules('Jdesir','Job desire','trim|xss_clean');
                 $this->form_validation->set_rules('Jrespon','Job resposibilty','trim|xss_clean');
		 $this->form_validation->set_rules('Jsonline','Last date online form','trim|xss_clean|required');
		 $this->form_validation->set_rules('Jlonline','Last date online form','trim|xss_clean|required');
                 $this->form_validation->set_rules('Jlastreach','Last date reached form','trim|xss_clean');

		/* Re-populating form */
       		 if ($_POST)
       		 {
           		$data['Jvcode']['value'] = $this->input->post('Jvcode');
            		$data['Jadvno']['value'] = $this->input->post('Jadvno', TRUE);
            		$data['Jpcode']['value'] = $this->input->post('Jpcode', TRUE);
           		$data['Jname']['value'] =  $this->input->post('Jname', TRUE);

			$data['Jdept']['value'] = $this->input->post('Jdept', TRUE);
            		$data['Jvacsc']['value'] = $this->input->post('Jvacsc', TRUE);
            		$data['Jvacst']['value'] = $this->input->post('Jvacst', TRUE);
           		$data['Jvacobc']['value'] = $this->input->post('Jvacobc', TRUE);

			$data['Jvacur']['value'] = $this->input->post('Jvacur', TRUE);
            		$data['Jvacpwd']['value'] = $this->input->post('Jvacpwd', TRUE);
            		$data['Jgroup']['value'] = $this->input->post('Jgroup', TRUE);
           		$data['Jemolu']['value'] = $this->input->post('Jemolu', TRUE);

			$data['Jagelimit']['value'] = $this->input->post('Jagelimit', TRUE);
            		$data['Jessen']['value'] = $this->input->post('Jessen', TRUE);
            		$data['Jexper']['value'] = $this->input->post('Jexper', TRUE);
           		$data['Jrespon']['value'] = $this->input->post('Jrespon', TRUE);
			$data['Jsonline']['value'] = $this->input->post('Jsonline', TRUE);
			$data['Jlonline']['value'] = $this->input->post('Jlonline', TRUE);
            		$data['Jlastreach']['value'] = $this->input->post('Jlastreach', TRUE);
            		$data['Jgrade']['value'] = $this->input->post('Jgrade', TRUE);
           		$data['Jdesir']['value'] = $this->input->post('Jdesir', TRUE);
       		 }

                 if($this->form_validation->run()==TRUE){
                 //echo 'form-validated';
			$totalvacancy = $_POST['Jvacsc']+$_POST['Jvacst']+$_POST['Jvacobc']+$_POST['Jvacur']+$_POST['Jvacpwd'];
                 	$data = array(
                		'job_vacnacycode'	=>	$_POST['Jvcode'],
	                	'job_adverno'		=>	$_POST['Jadvno'],
				'job_postcode'		=>	$_POST['Jpcode'],
				'job_nameofpost'	=>	$_POST['Jname'],
				'job_department'	=>	$_POST['Jdept'],

	                	'job_vacancysc'		=>	$_POST['Jvacsc'],
				'job_vacancyst'		=>	$_POST['Jvacst'],
				'job_vacancyobc'	=>	$_POST['Jvacobc'],
	                	'job_vacancyur'		=>	$_POST['Jvacur'],
				'job_vacancypwd'	=>	$_POST['Jvacpwd'],
				'job_vacancytotal'	=>	$totalvacancy,

				'job_gradepay'		=>	$_POST['Jgrade'],
				'job_emoluments'	=>	$_POST['Jemolu'],
	                	'job_agelimit'		=>	$_POST['Jagelimit'],
				'job_group'		=>	$_POST['Jgroup'],
				'job_essential'		=>	$_POST['Jessen'],

				'job_experience'		=>	$_POST['Jexper'],
	                	'job_desirable'			=>	$_POST['Jdesir'],
				'job_responsibles'		=>	$_POST['Jrespon'],
				'job_startdateonlineform'	=>	$_POST['Jsonline'],
				'job_lastdateonlineform'	=>	$_POST['Jlonline'],
				'job_lastdateformreach'		=>	$_POST['Jlastreach']
				
	                 );
        	        $rflag=$this->hrmmodel->insertrec('job_open', $data);
                	if (!$rflag)
                	{
                    		$this->logger->write_logmessage("insert","Add the advertisement records ", "Advertisement is not added ");
                    		$this->logger->write_dblogmessage("insert","Add the advertisement records", "Advertisement is not added ");
                    		$this->session->set_flashdata('err_message','Error in adding advertisement setting - '  , 'error');
                    		redirect('hrmgmt/add_advertisement');
                	}
                	else{
                    		$this->logger->write_logmessage("insert","Add the advertisement records added  successfully...");
                    		$this->logger->write_dblogmessage("insert","Add the advertisement records added  successfully...");
                    		$this->session->set_flashdata("success", "Advertisement add successfully...");
                    		redirect("hrmgmt/view_advertisement");
                	}
            	}//close if vallidation
        }//
		$this->load->view('hrmgmt/adver_add_detail');
	}



}

