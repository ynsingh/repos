<?php
/******************************************************
* @name Exam.php(controller)    		      *
* @author Nagendra Kumar Singh(nksinghiitk@gmail.com) *
*******************************************************/

defined('BASEPATH') OR exit('No direct script access allowed');

class Exam extends CI_Controller {

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

	public function listexam(){
		$usid  = $this->session->userdata['su_id'];
                $usemail= $this->session->userdata['su_emailid'];
		$crsid  = $this->session->userdata['crs_id'];
		$cdate = date('Y-m-d');
		$whdata = array ('subid' => $crsid, 'testdate <=' => $cdate);
                $whorder = 'testid';
		$data['testdata'] = $this->commodel->get_orderlistspficemore('test','testid,testcode,testname,testdate,subid',$whdata,$whorder);
		$data['subid'] = $crsid ;
		$this->load->view('exam/listexam',$data);
	}

	public function quiz(){
		$usid  = $this->session->userdata['su_id'];
		$usemail= $this->session->userdata['su_emailid'];
		$crsid  = $this->session->userdata['crs_id'];
		$tstid= $this->uri->segment(3);
		//	$data['cid']=$this->session->userdata();
		$cdate = date('Y-m-d');
		// get the testid from test where subject id  and date is today date and time between start and end
//		$whdata = array ('subid' => $crsid, 'testdate' => $cdate);
//		$whorder = 'testid';
//		$testids = $this->commodel->get_orderlistspficemore('test','testid',$whdata,$whorder);
//		foreach ($testids as $row){
//			$tstid = $row->testid;
//		}
		// get the question from question table  where test id and subject id
		$whdata = array ('subid' => $crsid, 'testid' => $tstid);
		$whorder = 'qid';
		$sfdata = 'qid,question,optiona,optionb,optionc,optiond';
		$data['questions'] = $this->commodel->get_orderlistspficemore('question',$sfdata,$whdata,$whorder);
		$data['testid'] = $tstid ;
		$data['subid'] = $crsid ;
		$this->load->view('exam/quiz',$data);
	}
	public function quizsubmit(){
		$crsid=$this->session->userdata['crs_id'];
		$tstid=$this->input->post("tid");
	//	echo $tstid; die();
		$whdata = array ('subid' => $crsid, 'testid' => $tstid);
                $whorder = 'qid';
                $sfdata = 'qid,correctanswer,marks';
		$questions = $this->commodel->get_orderlistspficemore('question',$sfdata,$whdata,$whorder);
		$data['subid'] = $crsid ;
		$cdate = date('Y-m-d');
		$usid  = $this->session->userdata['su_id'];
		if(isset($_POST['submit'])){
			$cans = 0;
			$tmarks = 0;
			foreach ($questions as $row){
				$ans = $this->input->post($row->qid);
				$stuquesdata = array(
					'su_id'      => $usid,
					'testid'	=> $tstid,
					'subid'	=> $crsid,
					'quid' 	=> $row->qid,
					'answered' =>'answered',
					'stdanswer' => $ans,
				);
				$insflag = $this->db->insert('studentquestion', $stuquesdata);
				if($ans ==  $row->correctanswer){
					$cans = $cans + 1;
					$tmarks = $tmarks + $row->marks;
				}
			}
			//
			$sansData = array(
                                        'st_testid'                     =>$tstid,
                                        'st_subid'                      =>$crsid,
                                        'st_stdid'                      =>$usid,
                                        'st_correctlyanswered'          =>$cans,
                                        'st_marks'                      =>$tmarks,
                                        'st_status'                     =>'',
                                        'st_endtime'                    =>'',
                                        'st_starttime'                  =>'',
                        );
                        $insert = $this->db->insert('studenttest',$sansData);
			//$this->load->view('exam/listexam');
			redirect('exam/listexam');
		}
	}//end of the function
}
