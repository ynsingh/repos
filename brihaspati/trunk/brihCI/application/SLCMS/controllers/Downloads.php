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
		$this->load->model("Student_model","stumodel");
        //if(empty($this->session->userdata('id_user'))) {
        //  $this->session->set_flashdata('flash_data', 'You don\'t have access!');
         //   redirect('welcome');
        // }
    }

	public function enterance_applicantdw(){
		$data['applicant_data'] = $this->commodel->get_list('admissionstudent_master');
				
		$this->load->view('enterenceadmin/enterance_applicantexceldw',$data);
	}

	/*public function enterance_student(){
		$date1 = $this->input->post('entstadate');
		$date2 = $this->input->post('entenddate');
	
		$admrecrd='';
		$data = array(
			'date1' => $date1,
			'date2' => $date2
			);
		if ($date1 == "" || $date2 == "") {
			$data['err_message'] = "Both date fields are required";
		} else {
			$result = $this->stumodel->show_data_by_date_range1($data);
			//print_r($result);die;
			$data['applicant_data']=$result;
		}
			$this->load->view('enterenceadmin/enterance_student', $data);
	}

	public function enterance_studentdw(){
		 $admrecrd1 = $this->uri->segment(3);
		
	   	 $data['entasmid']=explode("-",  $admrecrd1);	
				
		$this->load->view('enterenceadmin/enterance_applicantexceldw',$data);
		
	}*/


	public function admission_student(){
		$date1 = $this->input->post('stadate');
		$date2 = $this->input->post('enddate');
		$admrecrd='';
		/*$data = array(
			'date1' => $date1,
			'date2' => $date2
			);*/
		if ($date1 == "" || $date2 == "") {
			$data['err_message'] = "Both date fields are required";
		} else {
			//$result = $this->stumodel->show_data_by_date_range($data);
			$condition = "sas_admissiondate BETWEEN " . "'" . $date1 . "'" . " AND " . "'" . $date2 . "'";
			$sarray='sas_studentmasterid';
			//$wharray = array('prg_id' => $prgid);
			$result = $this->commodel->get_listarry('student_admissionstatus',$sarray,$condition);
			
			//print_r($result);die;
			$data['student_data']=$result;
		}
			$this->load->view('enterenceadmin/admission_applicant', $data);
	}

	public function admission_studentdw(){
		 $admrecrd1 = $this->uri->segment(3);
		 
	   	 $data['asmid']=explode("-",  $admrecrd1);	
				
		$this->load->view('enterenceadmin/admission_studentexceldw',$data);
		
	}

}//end class
