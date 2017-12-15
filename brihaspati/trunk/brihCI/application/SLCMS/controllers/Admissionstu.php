<?php

/* 
 * @name Admission.php
 * @author Sumit Saxena (sumitsesaxena@gmail.com)
 */

defined('BASEPATH') OR exit('No direct script access allowed');


class Admissionstu extends CI_Controller
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

	public function student_transfer(){
		$data['stu_transfer']=$this->commodel->get_list('student_transfer');
		$data['prgresult'] = $this->commodel->get_listmore('program','prg_id,prg_name,prg_branch');	
		$hlno = $this->input->post('hallnumber',TRUE);
		
       		if(isset($_POST['hnsearch'])) 
      		 {    
			//search through hall ticket number and student master id
			if(!empty($hlno)){
				$selectdata = array('sas_studentmasterid');
				$record = array('sas_hallticketno'  => $hlno);
       				$getstuid = $this->commodel->get_listspficemore('student_admissionstatus',$selectdata,$record);
				$data['getstuid'] = $getstuid;
			}	
		}	
		$this->load->view('student_admission/stu_transfer',$data);
	}

	

//This function check for duplicate hallticket number 		   
    public function hallticketexist($smuid) {
        $is_exist = $this->commodel->isduplicate('student_transfer','st_hallticketno',$smuid);
	//print_r($is_exist);
        if ($is_exist)
        {
		$this->form_validation->set_message('hallticketexist','Hall Ticket Number' ." ".$smuid. " ".'is already exist check your hall ticket number again.');
            return false;
        }
        else {
            return true;
        }
    }

	public function addstudent_transfer(){
		$data['stu_transfer']=$this->commodel->get_list('student_transfer');
		if(isset($_POST['stu_submit'])) {

            		/*Form Validation*/
           		 $this->form_validation->set_rules('stu_hallticketno','Hall Ticket Number','trim|required|callback_hallticketexist');
			
           		$this->form_validation->set_rules('stu_prgname','Program Name','trim|required');
		 	 $this->form_validation->set_rules('stu_departname','Department','trim|required');
           	 	
           		 if($this->form_validation->run() == TRUE)
           	 	 {  
               			$smid = $this->input->post('stu_smid');
                		$hallno = $this->input->post('stu_hallticketno');
				
				$newprgid = $this->input->post('stu_prgname');
				$newdeptid = $this->input->post('stu_departname');
				$oldprgid = $this->input->post('stu_oldprgid');
				$olddeptid = $this->input->post('stu_olddeptid');

				
               			 $data = array(
                    			'st_hallticketno'	=>	$hallno,
                    			'st_smid'		=>	$smid,
                    			'st_progid'		=>	$oldprgid,
					'st_deptid'		=>	$olddeptid,

                   			'st_newprogid'		=>	$newprgid,
                    			'st_newdeptid'		=>	$newdeptid,
                    			'st_creatorid'		=>	$this->session->userdata('username'),
                    			'st_createdate'		=>	date('y-m-d'),
                		);
           
                		$trstu=$this->commodel->insertrec('student_transfer',$data);
				//update program , branch and department id in student program 
				$updata = array(
                    			
                   			'sp_programid'		=>	$newprgid,
					'sp_branch'		=>	$newprgid,
                    			'sp_deptid'		=>	$newdeptid,
                    			
                		);
				 $result=$this->commodel->updaterec('student_program', $updata,'sp_smid',$smid);

				//update program id in student admission status 
				$admiupdate = array(
                   			'sas_prgid'	=>	$newprgid,
                		);
				 $result1=$this->commodel->updaterec('student_admissionstatus',$admiupdate,'sas_studentmasterid',$smid);

				//update program id in student entry exit
				$stuentupdate = array(
                   			'senex_prgid'	=>	$newprgid,
                		);
				 $result2=$this->commodel->updaterec('student_entry_exit',$stuentupdate,'senex_smid',$smid);

				//update program id in student_attendance
				$stuentupdate = array(
                   			'satd_prgid'	=>	$newprgid,
					'satd_deptid'   =>	$newdeptid
                		);
				 $result2=$this->commodel->updaterec('student_attendance',$stuentupdate,'satd_smid',$smid);

               			if(!$result2)
               			{
                    			$this->logger->write_logmessage("error","Error  in transfer student", $hallno.$smid);
                    			$this->logger->write_dblogmessage("error","Error  in transfer student", $hallno.$smid);
                   			 $this->session->set_flashdata('err_message','Some Data Is Incorrect - ' .$hallno);
                    			redirect('admissionstu/addstudent_transfer');
                		}
                		else{
					$this->logger->write_logmessage("insert","ADD in transfer student".$hallno.$smid);
			
                    			$this->logger->write_dblogmessage("insert","ADD in transfer student" .$hallno.$smid);
                    			$this->session->set_flashdata("success", "This hall ticket number ".$hallno." student transfer successfully.");
                    			redirect("admissionstu/addstudent_transfer");
				}//database error check
	    
            		}//if validation
			else{
				
				}
       	 	}//ifpost   
	$this->load->view('student_admission/stu_transfer',$data);	
	}

	public function stu_admissioncancel(){
		$data['stu_cancel']=$this->commodel->get_list('student_admission_cancel');
		$hlno = $this->input->post('halltinumber',TRUE);
		if(isset($_POST['cancelsearch'])) 
      		 {    
			$this->form_validation->set_rules('halltinumber','Select Hall Ticket Number','trim|required');
			
			if(!empty($hlno)){
				$selectdata = array('sas_studentmasterid','sas_prgid');
				$record = array('sas_hallticketno'  => $hlno);
       				$getstuid = $this->commodel->get_listspficemore('student_admissionstatus',$selectdata,$record);
				//print_r($getstuid);die;
				$data['getstuid'] = $getstuid;
			}	
		}	
		$this->load->view('student_admission/stu_admission_cancel',$data);
	}
//This function check for duplicate hallticket number 		   
    public function hallticketnoexist($smuid) {
        $is_exist = $this->commodel->isduplicate('student_admission_cancel','sac_hallticketno',$smuid);
	//print_r($is_exist);
        if ($is_exist)
        {
		$this->form_validation->set_message('hallticketnoexist','This Hall Ticket Number' ." ".$smuid. " ".'Student Admission Is Cancelled.');
            return false;
        }
        else {
            return true;
        }
    }

	public function stu_addadmissioncancel(){
		$data['stu_cancel']=$this->commodel->get_list('student_admission_cancel');
		
		if(isset($_POST['stu_cancel'])) {
		
            		/*Form Validation*/
			$this->form_validation->set_rules('stu_hallticketno','Hall Ticket Number','trim|callback_hallticketnoexist');
           		$this->form_validation->set_rules('stu_canreason','Reason','trim|required');
		 	$this->form_validation->set_rules('stu_canfeerefund','Fees Refund','trim|required');
           	 	
           		 if($this->form_validation->run() == TRUE)
           	 	{  
               			$smid   = $this->input->post('stu_smid');
                		$hallno = $this->input->post('stu_hallticketno');
				$prgid  = $this->input->post('stu_prgid');
				$reson  = $this->input->post('stu_canreason');
				$feesrefund = $this->input->post('stu_canfeerefund');
				
               			 $data = array(
                    			'sac_hallticketno'	=>	$hallno,
                    			'sac_smid'		=>	$smid,
                    			'sac_progid'		=>	$prgid,
					'sac_reson'		=>	$reson,

                   			'sac_feesrefundamount'	=>	$feesrefund ,
                    			'sac_feesrefundstatus'	=>	'Y',
					'sac_canceldate'        =>	date('y-m-d'),
                    			'sac_creatorid'		=>	$this->session->userdata('username'),
                    			'sac_createdate'	=>	date('y-m-d'),
                		);
           
                		$trstu=$this->commodel->insertrec('student_admission_cancel',$data);
				//update student program when student admission cancel
				$updata = array(
                    			
                   			'sp_programid'		=>	'',
                    			'sp_branch'		=>	'',
                    			
                		);
				 $upprog=$this->commodel->updaterec('student_program', $updata,'sp_smid',$smid);
				 $this->logger->write_logmessage("update","Update record in student program for cancel student", $hallno.$smid);
                    		 $this->logger->write_dblogmessage("update","Update record in student program for cancel student", $hallno.$smid);
				//update student fees when student admission cancel
				$updata = array(
                    			
                   			'sfee_feeamount'	=>	$newprgid,
                    			
                		);
				 $upfees=$this->commodel->updaterec('student_fees', $updata,'sfee_smid',$smid);
				 $this->logger->write_logmessage("update","Update record in student fees for cancel student", $hallno.$smid);
                    		 $this->logger->write_dblogmessage("update","Update record in student fees for cancel student", $hallno.$smid);	
				//update student admission status when student admission cancel
				$updata = array(
                    			
                   			'sas_admissionstatus'	=>	'Cancelled',
                    			
                		);
				 $upstustatus = $this->commodel->updaterec('student_admissionstatus', $updata,'sas_studentmasterid',$smid);
				 $this->logger->write_logmessage("update","Update record in student admission status for cancel student", $hallno.$smid);
                    		 $this->logger->write_dblogmessage("update","Update record in student admission status fees for cancel student", $hallno.$smid);	
               			if(!$upstustatus)
               			{
                    			$this->logger->write_logmessage("error","Error  in student admission cancel", $hallno.$smid);
                    			$this->logger->write_dblogmessage("error","Error  in student admission cancel", $hallno.$smid);
                   			$this->session->set_flashdata('err_message','Some Data Is Incorrect - ' .$hallno);
                    			redirect('admissionstu/stu_addadmissioncancel');
                		}
                		else{
					$this->logger->write_logmessage("insert","Insert in student admission cancel".$hallno.$smid);
			
                    			$this->logger->write_dblogmessage("insert","Insert in student admission cancel" .$hallno.$smid);
                    			$this->session->set_flashdata("success", "This hall ticket number ".$hallno." student admission is cancelled.");
                    			redirect("admissionstu/stu_cancelreceipt/".$smid);
				}//database error check
	    
            		}//if validation
			//else{
				
				//}
       	 	}//ifpost  

		

	$this->load->view('student_admission/stu_admission_cancel',$data); 
	}

	public function stu_cancelreceipt(){
		$smid = $this->uri->segment(3);
         	$data['smid']=$smid;	
		$data['hallticketno']= $this->commodel->get_listspfic1('student_admissionstatus','sas_hallticketno','sas_studentmasterid',$smid)->sas_hallticketno;
		$data['stu_name']= $this->commodel->get_listspfic1('student_master','sm_fname','sm_id',$smid)->sm_fname;
		$data['stu_fathername']= $this->commodel->get_listspfic1('student_parent','spar_fathername','spar_smid',$smid)->spar_fathername;
		$prgid = $this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$smid)->sp_programid;
		$data['stu_progname']= $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name.'( '.$this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch.' )';
		$data['stu_fees']= $this->commodel->get_listspfic1('student_admission_cancel','sac_feesrefundamount','sac_smid',$smid)->sac_feesrefundamount;

		$this->load->view('student_admission/stu_cancelreceipt',$data);
	}

	public function stu_cancelreceiptpdfdw(){
		$smid = $this->uri->segment(3);
		
         	//$data['smid']=$smid;	
		$data['hallticketno']= $this->commodel->get_listspfic1('student_admissionstatus','sas_hallticketno','sas_studentmasterid',$smid)->sas_hallticketno;
		$data['stu_name']= $this->commodel->get_listspfic1('student_master','sm_fname','sm_id',$smid)->sm_fname;
		$data['stu_fathername']= $this->commodel->get_listspfic1('student_parent','spar_fathername','spar_smid',$smid)->spar_fathername;
		$prgid = $this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$smid)->sp_programid;
		$data['stu_progname']= $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name.'( '.$this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch.' )';

		$data['stu_fees']= $this->commodel->get_listspfic1('student_admission_cancel','sac_feesrefundamount','sac_smid',$smid)->sac_feesrefundamount;

		$this->load->library('pdf');
       		$this->pdf->load_view('student_admission/stu_cancelreceiptpdfdw',$data);
        	$this->pdf->render();
        	$this->pdf->stream("admissionform.pdf");

		
	}

	//This function has been created for display the department on the basis of program & branch
	public function deptlist(){
			$prgid = $this->input->post('stu_prgname');
			//print_r($prgid);die;
			$sarray='prg_deptid';
			$wharray = array('prg_id' => $prgid);
			$department = $this->commodel->get_listarry('program',$sarray,$wharray);
		foreach($department as $datas):
				$deptid = $datas->prg_deptid; 
				$deptname=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$deptid)->dept_name;
				echo "<option  id='stu_departname' value='$deptid'>"."$deptname"."</option>";
  		endforeach;
	 }

	//This function has been created for display the elgible criteria on the basis of program & branch
	public function getminquali(){
			$prgid = $this->input->post('stu_prgname');
			//print_r($prgid);die;
			//$selectfield=array('admop_min_qual');
			//$whdata = array('admop_prgname_branch' => $prgid);
			//$eligible = $this->commodel->get_distinctrecord('admissionopen',$selectfield,$whdata);
			$eligible =  $this->commodel->get_listspfic2('admissionopen','','admop_min_qual','admop_prgname_branch',$prgid,'admop_min_qual');
		foreach($eligible as $datas):
				$eligible = $datas->admop_min_qual; 
				//echo "<option  id='stu_eligble' value='$eligible'>"."$eligible"."</option>";
				echo "<span id='stu_eligble'>$eligible</span>";
  		endforeach;
	 }



}//end class
