<?php

/* 
 * @name Downloads.php
 * @author Sumit Saxena (sumitsesaxena@gmail.com)
 */

defined('BASEPATH') OR exit('No direct script access allowed');


class Downloads extends CI_Controller
    {
    function __construct() {
        parent::__construct();
		  $this->load->model("user_model","usermodel");
                $this->load->model('Common_model',"commodel");
		$this->load->model('dependrop_model','depmodel');
        //if(empty($this->session->userdata('id_user'))) {
        //  $this->session->set_flashdata('flash_data', 'You don\'t have access!');
         //   redirect('welcome');
        // }
    }

	public function enterance_applicantdw(){
		$data['applicant_data'] = $this->commodel->get_list('admissionstudent_master');
				
		$this->load->view('enterenceadmin/enterance_applicantexceldw',$data);
	}

	

}//end class
