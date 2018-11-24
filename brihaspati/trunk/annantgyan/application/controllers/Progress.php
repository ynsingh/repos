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
	
	public function resultsheet(){
		$usid  = $this->session->userdata['su_id'];
                $usemail= $this->session->userdata['su_emailid'];
		$crsid  = $this->session->userdata['crs_id'];
		$cdate = date('Y-m-d');

		$data['suid'] =$usid;
		$data['subid'] = $crsid ;
		$crsfexdate = $this->commodel->get_listspfic1('courseannouncement','crsann_fexamdate','crsann_crsid',$crsid)->crsann_fexamdate;
		if($crsfexdate < $cdate){
			$compdata ="";
			$whdata =array('subid' =>$crsid);
	                $examdata =   $this->commodel->get_distinctrecord('test','testid,testname,testcode,maxmarks',$whdata);
                        $i=0;
                        foreach($examdata as $row){
        	                $tstid = $row->testid;
                                $onerec['testid'] =  $tstid;
                                $onerec['testname'] =  $row->testname;
                                $onerec['testcode'] = $row->testcode;
                                $onerec['maxmarks'] = $row->maxmarks;

                                $whdata1 = array('st_subid' =>$crsid,'st_stdid'=>$usid,'st_testid' =>$tstid);
                                        // dupliacte
                                $isexist=$this->commodel->isduplicatemore('studenttest',$whdata1);
                                if($isexist){
                                        //get marks and set attempted
                                        $onerec['stmarks'] = $this->commodel->get_listspfic1a('studenttest','st_marks',$whdata1)->st_marks;
                                        $onerec['stattemp'] = "Appeared";
                                }
                                else{
                                        //set marks zero and set not attempted
                                        $onerec['stmarks'] = 0;
                                        $onerec['stattemp'] = "Not Appeared";
                               }
                               // add array  in array
                               $compdata[$i] = $onerec;
                               $i++;
                        }
			$data['studata'] =   $compdata;
		}
		$this->load->view('progress/resultsheet',$data);
	}
}
