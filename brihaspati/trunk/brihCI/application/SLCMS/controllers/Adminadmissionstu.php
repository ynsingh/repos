<?php

/* 
 * @name Admission.php
 * @author Sumit Saxena (sumitsesaxena@gmail.com)
 */

defined('BASEPATH') OR exit('No direct script access allowed');


class Adminadmissionstu extends CI_Controller
    {
    function __construct() {
        parent::__construct();
		$this->load->model("user_model","usermodel");
                $this->load->model('Common_model',"commodel");
		$this->load->model('dependrop_model','depmodel');
		$this->load->model("Mailsend_model","mailmodel");	
		$this->load->model("University_model","univmodel");
		$this->load->model("Login_model", "logmodel");
		$this->load->model("DateSem_model","datesemmodel");
	if(empty($this->session->userdata('id_user'))) {
          $this->session->set_flashdata('flash_data', 'You don\'t have access!');
           redirect('welcome');
         }
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
				$eligible = $this->input->post('stu_elicrie');

				$newcourse_name = $this->commodel->get_listspfic1('program','prg_name','prg_id',$newprgid)->prg_name;
				$newbranchname  = $this->commodel->get_listspfic1('program','prg_branch','prg_id',$newprgid)->prg_branch;	
				$oldcourse_name = $this->commodel->get_listspfic1('program','prg_name','prg_id',$oldprgid)->prg_name;
				$oldbranchname  = $this->commodel->get_listspfic1('program','prg_branch','prg_id',$oldprgid)->prg_branch;	
				$olddeptname = $this->commodel->get_listspfic1('Department','dept_name','dept_id',$olddeptid)->dept_name;
				$newdeptname = $this->commodel->get_listspfic1('Department','dept_name','dept_id',$newdeptid)->dept_name;
				$email = $this->commodel->get_listspfic1('student_master','sm_email','sm_id',$smid)->sm_email;
				$stuname = $this->commodel->get_listspfic1('student_master','sm_fname','sm_id',$smid)->sm_fname;
//				print_r($stuname);die;
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

 				
				//$upimg = "<img src=base_url('uploads/logo/logo1.jpg');  style='width:100%;height:70px;'>";
				//$upimg = '<input type="image" src="base_url("/uploads/logo/logo1.png")" alt="Submit" style="width:100%" height="80">';
				$upimg = '<input type="image" src="http://103.246.106.195/~brihaspati/brihCI/uploads/logo/logo1.png" alt="Submit" style="width:100%" height="80">';
				$mess = "
					<table width='50%'; style='border-radius:10px;background-color:#8470FF;color:white;font-size:18px;' align=center border=0>
					    <tr><td colspan=2>".$upimg."<hr></td></tr>
					    <tr><td colspan=2><b>Your Department/Program transfer has been approved by CoE.The details are given below. </td></tr>
					    <tr height=15><td colspan=2></td></tr>
					    <tr><td width=280><b>Hall Ticket Number : </b></td><td align=left>".$hallno."</td></tr> 
					    <tr><td><b>Old Department Name :</b> </td><td align=left>".$olddeptname. "</td><tr>
					    <tr><td><b>Old Program Name : </b> </td><td align=left>".$oldcourse_name ." ( ".$oldbranchname ." )</td></tr>
					    <tr><td><b>Current Department Name :</b></td><td align=left>".$newdeptname."</td></tr>
					    <tr><td><b>Current Program Name : </b></td><td align=left> ".$newcourse_name ." ( ".$newbranchname ." )</td></tr>
					</table> " ;
				//$mess = $this->load->view('sample');
				$sub = 'Department/Program Transfer Details';

                               //  $mess="Your Department/Program transfer has been approved by CoE.The details are given below - \n Hall Ticket Number - ".$hallno."\n \n Old Department Name- ".$olddeptname. "\n Old Program Name - ".$oldcourse_name ." ( ".$oldbranchname ." )\n\n Current Department Name- ".$newdeptname."\n Current Program Name - ".$newcourse_name ." ( ".$newbranchname ." ) " ;
				
                                 $mails = $this->mailmodel->mailsnd($email, $sub, $mess);
				
					    //  mail flag check 			
				 if($mails){
                                           $error[] ="mail sent sucessfully";
                        	           $this->logger->write_logmessage("insert","Student Transfer ".$stuname." successfull");
					   $this->logger->write_dblogmessage("insert","Student Transfer ".$stuname."successfull" );
				 }
				  else{
        	                          $error[] ="insufficient data and mail does sent";
	                                  $this->logger->write_logmessage("insert","Student Transfer ".$stuname."  not successfull");
				          $this->logger->write_dblogmessage("insert","Student Transfer  ".$stuname." not successfull" );

					}

               			if(!$result2)
               			{
                    			$this->logger->write_logmessage("error","Error  in transfer student", $hallno.$smid);
                    			$this->logger->write_dblogmessage("error","Error  in transfer student", $hallno.$smid);
                   			 $this->session->set_flashdata('err_message','Some Data Is Incorrect - ' .$hallno);
                    			redirect('adminadmissionstu/addstudent_transfer');
                		}
                		else{
					$this->logger->write_logmessage("insert","ADD in transfer student".$hallno.$smid);
			
                    			$this->logger->write_dblogmessage("insert","ADD in transfer student" .$hallno.$smid);
                    			$this->session->set_flashdata("success", "This hall ticket number ".$hallno." student transfer successfully.");
                    			redirect("adminadmissionstu/addstudent_transfer");
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
		 	//$this->form_validation->set_rules('stu_canfeerefund','Fees Refund','trim|required');
           	 	
           		 if($this->form_validation->run() == TRUE)
           	 	{  
               			$smid   = $this->input->post('stu_smid');
                		$hallno = $this->input->post('stu_hallticketno');
				$prgid  = $this->input->post('stu_prgid');
				$depid  = $this->input->post('stu_deptid');
				$reson  = $this->input->post('stu_canreason');
				$feesrefund = $this->input->post('stu_canfeerefund');
				$course_name = $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name;
				$branchname  = $this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch;	
				$deptname = $this->commodel->get_listspfic1('Department','dept_name','dept_id',$depid)->dept_name;
				$email = $this->commodel->get_listspfic1('student_master','sm_email','sm_id',$smid)->sm_email;
				$stuname = $this->commodel->get_listspfic1('student_master','sm_fname','sm_id',$smid)->sm_fname;
				//print_r($feesrefund.' '.$deptname);die;
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
				 $this->logger->write_logmessage("update","Update record in student program for cancel student", $hallno.$stuname);
                    		 $this->logger->write_dblogmessage("update","Update record in student program for cancel student", $hallno.$stuname);
				//update student fees when student admission cancel
				$updata = array(
                    			
                   			'sfee_feeamount'	=>	$newprgid,
                    			
                		);
				 $upfees=$this->commodel->updaterec('student_fees', $updata,'sfee_smid',$smid);
				 $this->logger->write_logmessage("update","Update record in student fees for cancel student", $hallno.$stuname);
                    		 $this->logger->write_dblogmessage("update","Update record in student fees for cancel student", $hallno.$stuname);	
				//update student admission status when student admission cancel
				$updata = array(
                    			
                   			'sas_admissionstatus'	=>	'Cancelled',
                    			
                		);
				 $upstustatus = $this->commodel->updaterec('student_admissionstatus', $updata,'sas_studentmasterid',$smid);
				 $this->logger->write_logmessage("update","Update record in student admission status for cancel student", $hallno.$stuname);
                    		 $this->logger->write_dblogmessage("update","Update record in student admission status fees for cancel student", $hallno.$stuname);
				 $upimg = '<input type="image" src="http://103.246.106.195/~brihaspati/brihCI/uploads/logo/logo1.png" alt="Submit" style="width:100%" height="80">';
				//$upimg = '<input type="image" src="base_url("/uploads/logo/logo1.png");" alt="Submit" style="width:100%" height="80">';

				 //mail function
				$mess = "<table width='50%'; style='border:1px solid #3A5896;background-color:#8470FF;color:white;font-size:18px;' align=center border=0>
					   <tr><td colspan=2>".$upimg."</br><hr></td></tr>
					   <tr><td colspan=2><b>Your admission has been cancelled .The details are given below. </td></tr>
					   <tr height=15><td colspan=2></td></tr>
					   <tr><td width=370><b>Hall Ticket Number : </b></td><td align=left>".$hallno."</td></tr> 
					   <tr><td><b>Department Name :</b> </td><td align=left>".$deptname. "</td><tr>
					   <tr><td><b>Program Name : </b> </td><td align=left>".$course_name ." ( ".$branchname ." )</td></tr>
					   <tr><td><b>Admission Cancellation Reason :</b></td><td align=left>".$reson."</td></tr>
					   <tr><td><b>Your Refunded Fees Amount : </b></td><td align=left> ".$feesrefund ."</td></tr>
					</table> " ;

				 $sub='Admission Cancelled' ;
                                // $mess="Your admission has been cancelled .The details are given below - \n Hall Ticket Number - ".$hallno."\n \n Department Name- ".$deptname." \n Program Name - ".$course_name ." ( ".$branchname ." ) \n Admission Cancellation Reason - ".$reson."\n Your Refunded Fees Amount - " .$feesrefund;
                                 $mails = $this->mailmodel->mailsnd($email, $sub, $mess);
					    //  mail flag check 			
				 if($mails){
                                           $error[] ="mail sent sucessfully";
                        	           $this->logger->write_logmessage("insert","Student Admission Cancelletion ".$stuname.$hallno."successfull");
					   $this->logger->write_dblogmessage("insert","Student Admission Cancelletion ".$stuname.$hallno." successfull" );
				 }
				  else{
        	                         $error[] ="insufficient data and mail does sent";
	                                 $this->logger->write_logmessage("insert",$hallno."Student Admission Cancelletion not successfull");
				         $this->logger->write_dblogmessage("insert",$hallno."Student Admission Cancelletion not successfull" );

					}
               			if(!$upstustatus)
               			{
                    			$this->logger->write_logmessage("error","Error  in student admission cancel", $hallno.$stuname);
                    			$this->logger->write_dblogmessage("error","Error  in student admission cancel", $hallno.$stuname);
                   			$this->session->set_flashdata('err_message','Some Data Is Incorrect - ' .$hallno);
                    			redirect('adminadmissionstu/stu_addadmissioncancel');
                		}
                		else{
					$this->logger->write_logmessage("insert","Insert in student admission cancel".$hallno.$stuname);
			
                    			$this->logger->write_dblogmessage("insert","Insert in student admission cancel" .$hallno.$stuname);
                    			$this->session->set_flashdata("success", "This hall ticket number ".$hallno." student admission is cancelled.");
                    			redirect("adminadmissionstu/stu_cancelreceipt/".$smid);
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
		$data['hallticketno']= $this->commodel->get_listspfic1('student_admission_cancel','sac_hallticketno','sac_smid',$smid)->sac_hallticketno;
		$data['stu_name']= $this->commodel->get_listspfic1('student_master','sm_fname','sm_id',$smid)->sm_fname;
		$data['stu_fathername']= $this->commodel->get_listspfic1('student_parent','spar_fathername','spar_smid',$smid)->spar_fathername;
		$prgid = $this->commodel->get_listspfic1('student_admission_cancel','sac_progid','sac_smid',$smid)->sac_progid;
		$data['stu_progname']= $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name.'( '.$this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch.' )';
		$data['stu_fees']= $this->commodel->get_listspfic1('student_admission_cancel','sac_feesrefundamount','sac_smid',$smid)->sac_feesrefundamount;

		$this->load->view('student_admission/stu_cancelreceipt',$data);
	}

	public function stu_cancelreceiptpdfdw(){
		$smid = $this->uri->segment(3);
		
         	//$data['smid']=$smid;	
		$data['hallticketno']= $this->commodel->get_listspfic1('student_admission_cancel','sac_hallticketno','sac_smid',$smid)->sac_hallticketno;
		$data['stu_name']= $this->commodel->get_listspfic1('student_master','sm_fname','sm_id',$smid)->sm_fname;
		$data['stu_fathername']= $this->commodel->get_listspfic1('student_parent','spar_fathername','spar_smid',$smid)->spar_fathername;

		$prgid = $this->commodel->get_listspfic1('student_admission_cancel','sac_progid','sac_smid',$smid)->sac_progid;
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

/****************************************************Admin admission Panel Work Start****************************************************************/
	

	public function listenrolladminstu(){
		$data['deptlist'] = $this->commodel->get_list('Department');
		$currentacadyear =$this->datesemmodel->getcurrentAcadYear();
                $semester = 1;
		if(isset($_POST['search'])) 
 		{
			$date1 = $this->input->post('stu_sdate');
			$date2 = $this->input->post('stu_edate');
			$department = $this->input->post('stu_dept',TRUE);
			if($date1 == '' && $date2 == '' && $department == ''){
				$this->session->set_flashdata('err_message', 'Please Select The Date or Department.');
				redirect('adminadmissionstu/listenrolladminstu');
			}else{
				if(($date1 != "" && $date2 == "") || ($date1 == "" && $date2 != "")){
					$this->session->set_flashdata('err_message', 'Both date fields are required.');
					redirect('adminadmissionstu/listenrolladminstu');
				}
				
				if(($date1 != "" && $date2 != "") && $department != ''){
					$wharray = array('sp_semregdate >=' =>$date1 , 'sp_semregdate <=' => $date2 ,'sp_deptid' => $department,'sp_acadyear' => $currentacadyear,'sp_semester' => $semester);
				}
				elseif(($date1 != "" && $date2 != "") && $department == ''){
					$wharray = array('sp_semregdate >=' =>$date1 , 'sp_semregdate <=' => $date2 ,'sp_acadyear' => $currentacadyear,'sp_semester' => $semester);	
				}
				elseif(($date1 == "" && $date2 == "") && $department != ''){
					$wharray = array('sp_deptid' => $department,'sp_acadyear' => $currentacadyear,'sp_semester' => $semester);		
				}
			
			}
		}
		else{
			$wharray = array('sp_acadyear' => $currentacadyear,'sp_semester' => $semester);
		}
		$selectdata = 'sp_smid,sp_programid,sp_semregdate';	
		$getstuid = $this->commodel->get_listspficemore('student_program',$selectdata,$wharray);
               	$data['getstuid'] = $getstuid;		
	
		
                $this->load->view('student_admission/adminstu_enrollment',$data);

        }
	
	public function genenrolladminstu(){
		$data['deptlist'] = $this->commodel->get_list('Department');
		//get the list of student  from student program  where enrollment number is null in student master
			$whdata = array('sm_enrollmentno' => NULL);
			$selectfield = 'sp_smid,sp_programid';
			$joincond = 'student_master.sm_id = student_program.sp_smid';
			$query = $this->commodel->get_jointbrecord('student_program',$selectfield,'student_master',$joincond,$whdata);
			
		//get all student
			foreach($query as $row){
				$Sid = $row->sp_smid;
				$prgid = $row->sp_programid;	
				// call the function from university model generat_rollnumber($tablename,$prgid,$field,$whfield,$Sid)
				$this->univmodel->generat_rollnumber('student_master',$prgid,'sm_enrollmentno','sm_id',$Sid);
				$this->logger->write_logmessage("update","Update record in student enrollment ", $prgid.' '.$Sid);
				$this->session->set_flashdata('success', 'Student Enrollment Number has been Successfully Generated .');
			}

		$currentacadyear =$this->datesemmodel->getcurrentAcadYear();
                $semester = 1;
		$selectdata = 'sp_smid,sp_programid,sp_semregdate';
                $whdata = array('sp_acadyear' => $currentacadyear,'sp_semester' => $semester);
		$getstuid = $this->commodel->get_listspficemore('student_program',$selectdata,$whdata);
                $data['getstuid'] = $getstuid;
		
                $this->load->view('student_admission/adminstu_enrollment',$data);
	}

	public function adminstu_nonverified(){
		$data['deptlist'] = $this->commodel->get_list('Department');
		if(isset($_POST['nonsearch'])) 
 		{	
			$date1 = $this->input->post('stu_sdate');
			$date2 = $this->input->post('stu_edate');
			$department = $this->input->post('stu_nondept');
		
			if($date1 == '' && $date2 == '' && $department == ''){
				$this->session->set_flashdata('err_message', 'Please Select The Date or Department.');
				redirect('adminadmissionstu/adminstu_nonverified');
			}else{
				if(($date1 != "" && $date2 == "") || ($date1 == "" && $date2 != "")){
					$this->session->set_flashdata('err_message', 'Both date fields are required.');
					redirect('adminadmissionstu/adminstu_nonverified');
				}
				$currentacadyear =$this->datesemmodel->getcurrentAcadYear();
                		$semester = 1;
				if(($date1 != "" && $date2 != "") && $department != ''){
					$wharray = array('sas_admissiondate >=' =>$date1 , 'sas_admissiondate <=' => $date2 , 'sas_admissionstatus' => 'Provisional','sp_deptid' => $department,'sp_acadyear' => $currentacadyear,'sp_semester' => $semester);
				}
				elseif(($date1 != "" && $date2 != "") && $department == ''){
					$wharray = array('sas_admissiondate >=' =>$date1 , 'sas_admissiondate <=' => $date2 , 'sas_admissionstatus' => 'Provisional','sp_acadyear' => $currentacadyear,'sp_semester' => $semester);	
				}
				elseif(($date1 == "" && $date2 == "") && $department != ''){
					$wharray = array('sp_deptid' => $department,'sas_admissionstatus' => 'Provisional','sp_acadyear' => $currentacadyear,'sp_semester' => $semester);		
				}
			
			}
		}
		else{
			$wharray = array('sas_admissionstatus' => 'Provisional');
		}		

		$sdata = 'sp_smid,sas_studentmasterid,sas_hallticketno';
		$joincondition = 'student_admissionstatus.sas_studentmasterid = student_program.sp_smid';
		$stusmid = $this->commodel->get_jointbrecord('student_program',$sdata,'student_admissionstatus',$joincondition,$wharray);
		/*$this->db->select('sp_smid,sas_studentmasterid,sas_hallticketno');
		$this->db->from('student_program');
		$this->db->join('student_admissionstatus','student_admissionstatus.sas_studentmasterid = student_program.sp_smid');
		$this->db->where($wharray);
		$stusmid = $this->db->get()->result();*/
		$data['stusmid'] = $stusmid;
		
		$this->load->view('student_admission/adminstu_nonverified',$data);
	}

	public function adminstu_verified(){
		$data['deptlist'] = $this->commodel->get_list('Department');
		if(isset($_POST['verisearch'])) 
 		{	
			$date1 = $this->input->post('stu_sdate');
			$date2 = $this->input->post('stu_edate');
			$department = $this->input->post('stu_veridept');
		
			if($date1 == '' && $date2 == '' && $department == ''){
				$this->session->set_flashdata('err_message', 'Please Select The Date or Department.');
				redirect('adminadmissionstu/adminstu_verified');
			}else{
				if(($date1 != "" && $date2 == "") || ($date1 == "" && $date2 != "")){
					$this->session->set_flashdata('err_message', 'Both date fields are required.');
					redirect('adminadmissionstu/adminstu_verified');
				}
				$currentacadyear =$this->datesemmodel->getcurrentAcadYear();
                		$semester = 1;
				if(($date1 != "" && $date2 != "") && $department != ''){
					$wharray = array('sas_admissiondate >=' =>$date1 , 'sas_admissiondate <=' => $date2 , 'sas_admissionstatus' => 'Confirmed','sp_deptid' => $department,'sp_acadyear' => $currentacadyear,'sp_semester' => $semester);
				}
				elseif(($date1 != "" && $date2 != "") && $department == ''){
					$wharray = array('sas_admissiondate >=' =>$date1 , 'sas_admissiondate <=' => $date2 , 'sas_admissionstatus' => 'Confirmed','sp_acadyear' => $currentacadyear,'sp_semester' => $semester);	
				}
				elseif(($date1 == "" && $date2 == "") && $department != ''){
					$wharray = array('sp_deptid' => $department,'sas_admissionstatus' => 'Confirmed','sp_acadyear' => $currentacadyear,'sp_semester' => $semester);		
				}
			
			}
		}
		else{
			$wharray = array('sas_admissionstatus' => 'Confirmed');
		}		
		$sdata = 'sp_smid,sas_studentmasterid,sas_hallticketno';
		$joincondi = 'student_admissionstatus.sas_studentmasterid = student_program.sp_smid';
		$stusmid = $this->commodel->get_jointbrecord('student_program',$sdata,'student_admissionstatus',$joincondi,$wharray);

		/*$this->db->select('sp_smid,sas_studentmasterid,sas_hallticketno');
		$this->db->from('student_program');
		$this->db->join('student_admissionstatus','student_admissionstatus.sas_studentmasterid = student_program.sp_smid');
		$this->db->where($wharray);
		$stusmid = $this->db->get()->result();*/
		$data['stusmid'] = $stusmid;

		$this->load->view('student_admission/adminstu_verified',$data);
	}

	public function adminstu_verifidata(){
		$smid = $this->uri->segment(3);
		$data['smid']=$smid;
		$hlno = $this->commodel->get_listspfic1('student_admissionstatus','sas_hallticketno','sas_studentmasterid',$smid)->sas_hallticketno;
		$data['hlno'] = $hlno;
		$progid = $this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$smid)->sp_programid;
		$data['progid']=$progid;
		$prgname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$progid)->prg_name;
		$prgbranch = $this->commodel->get_listspfic1('program','prg_branch','prg_id',$progid)->prg_branch;
		$data['prgname'] = $prgname;
		$data['prgbranch'] = $prgbranch;

		$stu_data = $this->commodel->get_listrow('student_master','sm_id',$smid)->row();
		if(!empty($stu_data)) {
			$name = $stu_data->sm_fname;
			$data['name']=$name;
			$dob = $stu_data->sm_dob;
			$data['dob'] = $dob;
			

			$prgcatid = $this->commodel->get_listspfic1('program','prg_category','prg_id',$progid)->prg_category;
			//$data['prgcat'] = $prgcat;
			//$prgcatid = $this->commodel->get_listspfic1('programcategory','prgcat_id','prgcat_name',$prgcat)->prgcat_id;
//			$data['prgcatid'] = $prgcatid;
			$prgcatname = $this->commodel->get_listspfic1('programcategory','prgcat_name','prgcat_id',$prgcatid)->prgcat_name;	
			$data['prgcatname'] = $prgcatname;
			$bloodgroup = $stu_data->sm_bloodgroup;
			$data['blgroup'] =$bloodgroup;
			$gender = $stu_data->sm_gender;
			$data['gender'] = $gender;
			$mobile = $stu_data->sm_mobile;
			$data['mobile'] = $mobile;
			$email = $stu_data->sm_email;
			$data['email'] = $email;
			$categoryid = $stu_data->sm_category;
			$data['categoryid'] = $categoryid;
			$categoryname = $this->commodel->get_listspfic1('category','cat_name','cat_id',$categoryid)->cat_name;
			$data['categoryname'] = $categoryname;
			//$rollno = $stu_data->sm_applicationno;
			//$data['rollno'] = $rollno;
			$sccode = $stu_data->sm_sccode;
			$data['sccode'] = $sccode;
			//$scname = $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$sccode)->sc_name;
			$scname = $this->commodel->get_listspfic1('org_profile','org_name','org_id',$sccode)->org_name;
			$data['scname'] = $scname;
			//$excode = $stu_data->sm_enterenceexamcenter;
			//$exname =  $this->commodel->get_listspfic1('adminadmissionstudent_enterenceexamcenter','eec_name','eec_id',$excode)->eec_name;	
			//$data['exname'] = $exname;
			
			//$age = $stu_data->sm_age;
			//$data['age'] = $age;
			$mastatus = $stu_data->sm_mstatus;
			$data['mastatus'] = $mastatus;
			$nationality = $stu_data->sm_nationality;
			$data['nationality'] = $nationality;
			$phyhandi = $stu_data->sm_phyhandicaped;
			$data['phyhandi'] = $phyhandi;
			$religion = $stu_data->sm_religion;
			$data['religion'] = $religion;
			$reservation = $stu_data->sm_reservationtype;
			$data['reservation'] = $reservation;
			$aadhar = $stu_data->sm_uid;
			$data['aadhar'] = $aadhar;
			

		}
		$studparent_admission = $this->commodel->get_listrow('student_parent','spar_smid',$smid)->row();
		
		if(!empty($studparent_admission)){
			$mname = $studparent_admission->spar_mothername;
			$data['mname'] = $mname;
			$fname =  $studparent_admission->spar_fathername;
			$data['fname'] = $fname;
			
			//get permanant address detail
			$paddress = $studparent_admission->spar_paddress;
			$data['paddress'] = $paddress;
			$pcity=$studparent_admission->spar_pcity;
			$data['pcity']=$pcity;
			$pstate = $studparent_admission->spar_pstate;
			$data['pstate'] = $pstate;
			$pcountry = $studparent_admission->spar_pcountry;
			$data['pcountry'] = $pcountry;
			$ppincode = $studparent_admission->spar_ppincode;	
			$data['ppincode'] = $ppincode;
			
		}

		$studedu = $this->commodel->get_listrow('student_education','sedu_smid',$smid)->result();
		$data['studedu'] = $studedu;
		
		if(isset($_POST['stuverify'])) {
		
		//student status update	
			$stusmid = $this->input->post('stu_smid');
			$stuhlno = $this->input->post('stu_hlno');

			$upstudata = array(
                 		'sas_admissionstatus'	=> 'Confirmed',
                		);
			//print_r($updata);die;
			//$upstatusconf = $this->commodel->updaterec('student_admissionstatus',$upstudata,'sas_studentmasterid',$smid);
			$sasid=$this->db->where('sas_studentmasterid',$stusmid);
			$upstatusconf = $this->db->update('student_admissionstatus', $upstudata);
		//	print_r($smid);die;
			$this->logger->write_logmessage("update","Update record in student admission status ", $data['hlno']);
               		$this->logger->write_dblogmessage("update","Update record in  student admission status ", $data['hlno']);
			
			if(!$upstatusconf)
               		{
                    		$this->logger->write_logmessage("error","Error  in student admission status".$stuhlno);
                    		$this->logger->write_dblogmessage("error","Error  in student admission status".$stuhlno);
                   		$this->session->set_flashdata('err_message','Some Data Is Incorrect - '.$stuhlno);
                    		redirect('adminadmissionstu/adminstu_verifiedata');
                	}
                	else{
				$this->logger->write_logmessage("insert","Insert in student admission cancel".$data['hlno']);
			        $this->logger->write_dblogmessage("insert","Insert in student admission cancel" .$stuhlno);
                    	//	$this->session->set_flashdata("success", "This hall ticket number ".$stuhlno." student verify successfully!");
			        $this->session->set_flashdata("success", "This Application number ".$stuhlno." student verify successfully!");
                    		redirect('adminadmissionstu/adminstu_nonverified');
				}
			
		}
		
		$this->load->view('student_admission/adminstu_verifiedata',$data);
	}


}//end class
