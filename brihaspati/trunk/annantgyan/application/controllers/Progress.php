<?php
/******************************************************
* @name Exam.php(controller)    		      *
* @author Nagendra Kumar Singh(nksinghiitk@gmail.com) *
*******************************************************/

defined('BASEPATH') OR exit('No direct script access allowed');

class Progress extends CI_Controller {

	/******************************************************************************/	
	public function __construct(){
		parent::__construct();
		$this->load->model("Mailsend_model","mailmodel");
		$this->load->model('Common_model',"commodel");
		if(empty($this->session->userdata('su_id'))) {
	            	$this->session->set_flashdata('flash_data', 'You don\'t have access!');
            		redirect('welcome');
        	}		
	}

	public function listrexam(){
		$usid  = $this->session->userdata['su_id'];
                $usemail= $this->session->userdata['su_emailid'];
		$crsid  = $this->session->userdata['crs_id'];
		$cdate = date('Y-m-d');
		$whdata = array ('st_subid' => $crsid, 'st_stdid' => $usid);
                $whorder = 'st_testid';
		$data['testrdata'] = $this->commodel->get_orderlistspficemore('studenttest','st_testid,st_correctlyanswered,st_marks',$whdata,$whorder);
		$data['subid'] = $crsid ;
		$this->load->view('progress/listrexam',$data);
	}

	public function answercopy(){
                $usid  = $this->session->userdata['su_id'];
                $usemail= $this->session->userdata['su_emailid'];
		$crsid  = $this->session->userdata['crs_id'];
		$tstid= $this->uri->segment(3);
		$cdate = date('Y-m-d');

		$whdata = array('testid'=>$tstid,'subid'=>$crsid,'su_id'=>$usid);
                $data['sansdata'] = $this->commodel->get_orderlistspficemore('studentquestion','quid,stdanswer',$whdata,'quid asc');
                $data['subid'] = $crsid ;
                $this->load->view('progress/answercopy',$data);
        }

}
