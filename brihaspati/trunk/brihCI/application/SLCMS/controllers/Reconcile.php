<?php

defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name Reconcile.php
 * @author Sumit Saxena(sumitsesaxena@gmail.com)[Fees Reconcile]  
 **/

class Reconcile extends CI_Controller
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
	//It gives all fees
	public function fees_reconcile_complete(){
		$whdata= array('sfee_feeamount >' => 0);
        	$this->stu_feedata = $this->commodel->get_listarry('student_fees','*',$whdata);
		$this->message ="All Fees Detail";
		$this->load->view('reconcile/fees_reconcile_complete');
		$this->logger->write_logmessage("view", "Reconcile all fees view.");
                $this->logger->write_dblogmessage("view", "Reconcile all fees view." );
	}

 	//It return all conciled fees
	public function fees_reconcile(){
		$userid = $this->session->userdata('id_user');
        	$whdata= array('sfee_feeamount >' => 0,'sfee_reconcilestatus' =>'Y');
		$this->message ="Reconcile Fees Detail";
        	$this->stu_feedata = $this->commodel->get_listarry('student_fees','*',$whdata);
		$this->load->view('reconcile/fees_reconcile_complete');
		$this->logger->write_logmessage("view", "Reconcile fees view.");
                $this->logger->write_dblogmessage("view", "Reconcile fees view." );
	}

	//It return all non-conciled fees
	public function fees_nonreconcile(){
		$who_reconame = $this->session->userdata('username');
        	$whdata= array('sfee_feeamount >' => 0,'sfee_reconcilestatus' =>'N');
        	$this->stu_feedata = $this->commodel->get_listarry('student_fees','*',$whdata);
		$sfee_id=$this->uri->segment(3);

		if(isset($_POST['nreconcile'])){

			$this->form_validation->set_rules('fees_check','You are not select student','trim|xss_clean');
			if($this->form_validation->run() == TRUE){
				$cdate = date('Y-m-d H:i:s');
				$fee = array(
						'sfee_reconcilestatus'  =>	$this->input->post('fees_check'),
                				'sfee_whoreconcile'  	=>	$who_reconame,
                				'sfee_reconciledate'  	=>	$cdate
                			);
								
				$this->commodel->updaterec('student_fees', $fee,'sfee_id',$sfee_id);
				$this->logger->write_logmessage("update", "Reconcile fee is done.");
	                    	$this->logger->write_dblogmessage("update", "Reconcile fee is done.");

				$flag = true;
				if($flag){
					$message = '<h3>Your data has been updated successfully !</h3>';
					$this->session->set_flashdata('msg',$message);
					redirect('reconcile/fees_nonreconcile');
				}
				else{
					$message = '<h3>Your data has not been updated successfully !</h3>';
					$this->session->set_flashdata('error',$message);
					$this->load->view('reconcile/fees_nonreconcile');
					}
			}//colse if validation
		}//close if isset

		$this->load->view('reconcile/fees_nonreconcile');
		$this->logger->write_logmessage("view","Non reconcile fee page show.");
                $this->logger->write_dblogmessage("view","Reconcile is update." );
	}

	public function nonreconcile_fee(){
			$whdata= array('sfee_feeamount >' => 0,'sfee_reconcilestatus' =>'N');
        		$this->stu_feedata = $this->commodel->get_listarry('student_fees','*',$whdata);
			$who_reconame = $this->session->userdata('username');
        		$sfee_id=$this->uri->segment(3);

				$cdate = date('Y-m-d H:i:s');
				$fee = array(
						'sfee_reconcilestatus'  =>	'Y',
                				'sfee_whoreconcile'  	=>	$who_reconame,
                				'sfee_reconciledate'  	=>	$cdate
                			);
								
				$this->commodel->updaterec('student_fees', $fee,'sfee_id',$sfee_id);
				$this->logger->write_logmessage("update", "Reconcile fee is done.");
	                    	$this->logger->write_dblogmessage("update", "Reconcile fee is done.");

				$flag = true;
				if($flag){
					$message = '<h3>Your data has been updated successfully !</h3>';
					$this->session->set_flashdata('msg',$message);
					redirect('reconcile/fees_nonreconcile');
				}
				else{
					$message = '<h3>Your data has not been updated successfully !</h3>';
					$this->session->set_flashdata('error',$message);
					$this->load->view('reconcile/fees_nonreconcile');
					}

		$this->load->view('reconcile/fees_nonreconcile');
		$this->logger->write_logmessage("view","Non reconcile fee page show.");
                $this->logger->write_dblogmessage("view","Non reconcile fee page show." );
	}//function close
	


}

