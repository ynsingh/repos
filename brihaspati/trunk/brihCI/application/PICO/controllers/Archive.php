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
		$this->load->model('common_model','commodel'); 
		$this->load->model('login_model','logmodel'); 
	        $this->load->model('SIS_model',"sismodel");
	        $this->load->model('PICO_model',"picomodel");
	
        	if(empty($this->session->userdata('id_user'))) {
	        	$this->session->set_flashdata('flash_data', 'You don\'t have access!');
			redirect('welcome');
        	}
    	}

    	public function index() {
        	$this->tendercreatea();
    	}

	/** This function Display the fees with headwise list archive records */
	public function tendercreatea() {
		$whorder='tc_id asc';
        	$data['tca'] = $this->picomodel->get_orderlistspficemore('tender_create_archive','*','',$whorder);
	        $this->logger->write_logmessage("view"," View tender list archive ", "Tender setting archive details...");
        	$this->logger->write_dblogmessage("view"," View tender list archive ", "tender setting archive details...");
	        $this->load->view('archive/tendercreatea',$data);
	}

	/** This function Display the Program Subject Paper archive records */
	public function tenderapplya() {
		$whorder='ta_id asc';
        	$data['taa']= $this->picomodel->get_orderlistspficemore('tender_apply_archive','*','',$whorder);
	        $this->logger->write_logmessage("view"," View tender_apply archive ", "tender_apply archive details...");
        	$this->logger->write_dblogmessage("view"," View tender_apply archive", "tender_apply archive details...");
	        $this->load->view('archive/tenderapplya',$data);
	}

	/** This function Display the semester rule list archive records */
	public function vendera() {
		$whorder='vendor_archive_id asc';
        	$data['va'] = $this->picomodel->get_orderlistspficemore('vendor_archive','*','',$whorder);
	        $this->logger->write_logmessage("view"," View  vendor archive ", "vendor archive details...");
        	$this->logger->write_dblogmessage("view"," View vendor  archive", "vendor archive details...");
	        $this->load->view('archive/vendera',$data);
	}

	/** This function Display the Subject semester Program list archive records */
	public function stockitema() {
		$whorder='stocka_stockid asc';
        	$data['sia'] = $this->picomodel->get_orderlistspficemore('stock_items_archive','*','',$whorder);
	        $this->logger->write_logmessage("view"," View stock_items archive ", "stock_items archive details...");
        	$this->logger->write_dblogmessage("view"," View stock_items archive", "stock_items archive details...");
	        $this->load->view('archive/stockitema',$data);
	}
  	/*this function has been created for display the staff position archive records */
	public function itemissueda(){
		$whorder='siia_stockid asc';
        	$data['iia'] = $this->picomodel->get_orderlistspficemore('stock_items_issued_archive','*','',$whorder);
	        $this->logger->write_logmessage("view"," View  stock_items_issued archive ", "stock_items_issued archive details...");
        	$this->logger->write_dblogmessage("view"," View stock_items_issued archive", "stock_items_issued archive details...");
        	$this->load->view('archive/itemissueda',$data);
	}


  	/*this function has been created for display the ddo archive records */
  	public function listddoa(){
        	$this->result = $this->sismodel->get_list('ddo_archive');
	        $this->logger->write_logmessage("view"," View ddo archive ", "DDO archive details...");
        	$this->logger->write_dblogmessage("view"," View ddo archive", "DDo archive details...");
        	$this->load->view('archive/listddoa');
  	}
  	/*this function has been created for display the map sc with uo archive records */
  	public function viewscuoa(){
        	$this->result = $this->sismodel->get_list('map_sc_uo_archive');
	        $this->logger->write_logmessage("view"," View map sc with uo archive ", "Mapping sc with uo archive details...");
        	$this->logger->write_dblogmessage("view"," View map sc with uo archive", "Mapping sc with uo archive details...");
        	$this->load->view('archive/viewscuoa');
  	}
  	/*this function has been created for display the scheme archive records */
  	public function displayschemea(){
        	$this->result = $this->sismodel->get_list('scheme_department_archive');
	        $this->logger->write_logmessage("view"," View scheme archive ", "Setup scheme archive details...");
        	$this->logger->write_dblogmessage("view"," View scheme archive", "Setup scheme archive details...");
        	$this->load->view('archive/displayschemea');
  	}
  	/*this function has been created for display the Salary grade master archive records */
  	public function displaysalarygrademastera(){
        	$this->result = $this->sismodel->get_list('salary_grade_master_archive');
	        $this->logger->write_logmessage("view"," View salary grade master archive ", "Setup salary grade master archive details...");
        	$this->logger->write_dblogmessage("view"," View salary grade master archive", "Setup salary grade master archive details...");
        	$this->load->view('archive/displaysalarygrademastera');
  	}
	/*this function has been created for display the bank deails archive records */
	public function bankdetaila() {
		$this->result = $this->sismodel->get_list('bankprofile_archive');
		$this->logger->write_logmessage("view"," View bankdetaila archive ", "bankdetail archive details...");
        	$this->logger->write_dblogmessage("view"," View bankdetaila archive", "bankdetail archive details...");
		$this->load->view('archive/bankdetaila');
	}
	/*this function has been created for display the Department archive records */
        public function departmenta(){
                $this->deptaresult = $this->commodel->get_list('Department_archive');
                $this->logger->write_logmessage("view"," View Department archive ", "Department archive details...");
                $this->logger->write_dblogmessage("view"," View Department archive", "Department archive details...");
                $this->load->view('archive/departmenta');
        }
      /*this function has been created for display the map scheme department archive records */
        public function mapschemedepta(){
                $this->result = $this->sismodel->get_list('map_scheme_department_archive');
                $this->logger->write_logmessage("view"," View map scheme department archive ", " Map Scheme Department archive details...");
                $this->logger->write_dblogmessage("view"," View  map scheme department archive", "Map Scheme Department archive details...");
                $this->load->view('archive/mapschemedepta');
	}


	/** This function Display the Authority list archive records */
        public function authoritya() {
        	$data['authresult'] = $this->logmodel->get_list('authority_archive');
	        $this->logger->write_logmessage("view"," View Authority archive ", "Authority archive details...");
        	$this->logger->write_dblogmessage("view"," View Authority archive", "Authority archive details...");
	        $this->load->view('archive/authoritya',$data);
	}
	/*this function has been created for display the user role type archive records */
	public function mapuserrolea(){
		$whorder ='urta_urtid asc';
                $data['result'] = $this->picomodel->get_orderlistspficemore('user_role_type_archive','*','',$whorder);
                $this->logger->write_logmessage("view"," View user role type archive ", "User Role Type archive details...");
                $this->logger->write_dblogmessage("view"," View user role type archive", "User Role Type archive details...");
                $this->load->view('archive/mapuserrolea',$data);
        }
     /** This function Display the Announcement list archive records */
        public function announcementa() {
                $data['annoresult'] = $this->commodel->get_list('announcement_archive');
                $this->logger->write_logmessage("view"," View Announcement archive", "Announcement archive details...");
                $this->logger->write_dblogmessage("view"," View Announcement archive", "Announcement archive details...");
                $this->load->view('archive/announcementa',$data);
        }

}

