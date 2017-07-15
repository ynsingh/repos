<?php
/******************************************************
* @name Request.php(controller)    		      *
* @author Sumit Saxena(sumitsesaxena@gmail.com)       *
*******************************************************/

defined('BASEPATH') OR exit('No direct script access allowed');

class Request extends CI_Controller {
/******************************************************************************/	
	public function __construct(){
		parent::__construct();
		$this->db2 = $this->load->database('login', TRUE);
		$this->load->model('user_model');
		$this->load->model("common_model");
		$this->load->model("student_model");
		$this->load->model("login_model");
		$this->load->model("mailsend_model");
		$this->load->model("DateSem_model");
	}
/******************************************************************************/	
//$this->load->helper('url');

	/**
	 * Index Page for this controller.
	 *
	 * Maps to the following URL
	 * 		http://example.com/index.php/welcome
	 *	- or -
	 * 		http://example.com/index.php/welcome/index
	 *	- or -
	 * Since this controller is set as the default controller in
	 * config/routes.php, it's displayed at http://example.com/
	 *
	 * So any other public methods not prefixed with an underscore will
	 * map to /index.php/welcome/<method_name>
	 * @see https://codeigniter.com/user_guide/general/urls.html
	 */
	
	
	/*************************************************************************************************************/
	public function semesterregi(){
		$suid=$this->session->userdata('id_user');
		//print_r($suid);
		$Stuid=$this->common_model->get_listspfic1("student_master","sm_id","sm_userid",$suid)->sm_id;
		//print_r($Stuid);
           	//get current academic year
           	//$acadyear = $this->user_model->getcurrentAcadYear();
		$this->appno = $this->common_model->get_listspfic1('student_entry_exit','senex_entexamapplicationno','senex_smid',$Stuid)->senex_entexamapplicationno;
		$stud_prg_rec = $this->common_model->get_listrow('student_program','sp_smid',$Stuid);
           	$degree_id = $stud_prg_rec->row()->sp_programid;
           	$noofsemester = sizeof($stud_prg_rec->result());
		
		$cacadyer = $this->user_model->getcurrentAcadYear();
		//print_r($cacadyer);
            	$semestertype = $this->user_model->getcurrentSemester();
        	//print_r($semestertype);
            	$semesterrec = $this->student_model->get_semester_no($Stuid,$cacadyer);
           	$semsize = sizeof($semesterrec);
		$this->curresem = $semsize;
		//print_r($semsize);
		
            	if($semestertype == "Odd")
            	{
               		 if($semsize == 1)
                	{
                    		$semester = $noofsemester;
                	}
                	else
                	{
                    		$semester = $noofsemester + 1;
                    		$semester = "Please register in the semester ".$semester;
				//redirect('request/semesterregi');
                    		//echo message for semester registration
                	}
            	}

           	 if($semestertype == "Even")
           	 {
                	if($semsize == 2)
                	{
                  	  	$semester = $noofsemester;
                	}
                	else
                	{
                    		$semester = $noofsemester + 1;
                    		$semester = "Please register in the semester ".$semester;
				//redirect('request/semesterregi');
                	}
           	 }
		
		//student personnel detail
		$this->name=$this->common_model->get_listspfic1("student_master","sm_fname","sm_userid",$suid)->sm_fname;
		$this->sturollno = $this->common_model->get_listspfic1('student_entry_exit','senex_rollno','senex_smid',$Stuid)->senex_rollno;
		$this->mobile=$this->common_model->get_listspfic1('student_master','sm_mobile','sm_id',$Stuid)->sm_mobile;
		$this->email=$this->common_model->get_listspfic1('student_master','sm_email','sm_id',$Stuid)->sm_email;
		$this->dob=$this->common_model->get_listspfic1('student_master','sm_dob','sm_id',$Stuid)->sm_dob;
		$this->uid=$this->common_model->get_listspfic1('student_master','sm_uid','sm_id',$Stuid)->sm_uid;
		$this->bgroup=$this->common_model->get_listspfic1('student_master','sm_bloodgroup','sm_id',$Stuid)->sm_bloodgroup;
		$this->gender=$this->common_model->get_listspfic1('student_master','sm_gender','sm_id',$Stuid)->sm_gender;
		$this->religname=$this->common_model->get_listspfic1('student_master','sm_religion','sm_id',$Stuid)->sm_religion;

		//student parent and course detail
		$this->mname = $this->common_model->get_listspfic1('student_parent','spar_mothername','spar_smid',$Stuid)->spar_mothername;		
		$this->fathname=$this->common_model->get_listspfic1('student_parent','spar_fathername','spar_smid',$Stuid)->spar_fathername;
		
		$this->sem = $this->common_model->get_listspfic1('student_program','sp_semester','sp_smid',$Stuid)->sp_semester;
		
		$this->ncid = $this->common_model->get_listspfic1('student_program','sp_programid','sp_smid',$Stuid)->sp_programid;
		//get programe name
		$this->pname = $this->common_model->get_listspfic1('program','prg_name','prg_id',$this->ncid)->prg_name;
		$this->progid = $this->common_model->get_listspfic1('program','prg_id','prg_name',$this->pname)->prg_id;
		
		//get program category name
		$this->pcatid = $this->common_model->get_listspfic1('student_program','sp_pcategory','sp_smid',$Stuid)->sp_pcategory;
		$this->pcatname = $this->common_model->get_listspfic1('programcategory','prgcat_name','prgcat_id',$this->pcatid)->prgcat_name;

		//postal address detail
		$this->padd=$this->common_model->get_listspfic1('student_parent','spar_caddress','spar_smid',$Stuid)->spar_caddress;
		$this->pcity=$this->common_model->get_listspfic1('student_parent','spar_ccity','spar_smid',$Stuid)->spar_ccity;
		$this->ppost=$this->common_model->get_listspfic1('student_parent','spar_cpostoffice','spar_smid',$Stuid)->spar_cpostoffice;
		$this->pdist=$this->common_model->get_listspfic1('student_parent','spar_cdistrict','spar_smid',$Stuid)->spar_cdistrict;
		$this->pstat=$this->common_model->get_listspfic1('student_parent','spar_cstate','spar_smid',$Stuid)->spar_cstate;
		$this->ppin=$this->common_model->get_listspfic1('student_parent','spar_cpincode','spar_smid',$Stuid)->spar_cpincode;
		$this->pcounname=$this->common_model->get_listspfic1('student_parent','spar_ccountry','spar_smid',$Stuid)->spar_ccountry;
		
		//get student category
		$this->cateid=$this->common_model->get_listspfic1('student_master','sm_category','sm_id',$Stuid)->sm_category;
		$this->catename=$this->common_model->get_listspfic1('category','cat_name','cat_id',$this->cateid)->cat_name;

		//get study center
		$this->sccode=$this->common_model->get_listspfic1('student_master','sm_sccode','sm_id',$Stuid)->sm_sccode;
		$this->scname=$this->common_model->get_listspfic1('study_center','sc_name','sc_code',$this->sccode)->sc_name;
		//get branch name
		$this->brid=$this->common_model->get_listspfic1('student_program','sp_branch','sp_smid',$Stuid)->sp_branch;
		$this->brname=$this->common_model->get_listspfic1('program','prg_branch','prg_id',$this->brid)->prg_branch;
		//get department name
		$this->depid=$this->common_model->get_listspfic1('student_program','sp_deptid','sp_smid',$Stuid)->sp_deptid;
		$this->depname=$this->common_model->get_listspfic1('Department','dept_name','dept_id',$this->depid)->dept_name;

		//$sprogid = $this->common_model->get_listspfic1('student_program','sp_id','sp_smid',$Stuid)->sp_id;
		//print_r($sprogid);

		//$this->db->select('sp_id')->from('student_program')->where('sp_smid',$Stuid)->where('sp_semester',$semsize);
		//$spstid = $this->db->get()->row();
		$getspid = array('sp_smid' => $Stuid, 'sp_semester' => $semsize);
	    	$spstid = $this->common_model->get_listspficemore('student_program','sp_id',$getspid);
		
		//print_r($spstid);
		foreach($spstid as $stspid){
			$this->cusem = $this->common_model->get_listspfic1('student_program','sp_semester','sp_id',$stspid->sp_id)->sp_semester;
			//print_r($this->cusem);
		}
			$datawh = array('sp_smid' => $Stuid, 'sp_semester' => $semsize);
	    		$diffdate = $this->common_model->get_listspficemore('student_program','sp_programid,sp_semregdate',$datawh);
			//print_r($diffdate);
			foreach($diffdate as $spdate):
				$semdiffdate = $this->DateSem_model->diffdays($spdate->sp_semregdate);
				$spprgid = $spdate->sp_programid;
			endforeach;	
			//print_r($semdiffdate);
			$prgpattern=$this->common_model->get_listspfic1('program','prg_pattern','prg_id',$spprgid)->prg_pattern;
			$tmonth = $semdiffdate/30;
			//print_r($this->tdate);
			
			
			if((($tmonth > 5) && ($prgpattern == 'Semester')) || (($tmonth > 11) && ($prgpattern == 'Yearly')))
			{
	
				if(isset($_POST['register']))
				{	
					$this->form_validation->set_rules('Ssem','Semseter','trim|xss_clean|required');
					//Re-populate field
					if($_POST)
					{
						//$this->data['Stmobile']=$this->input->get_post('Stmobile');
						$this->data['Ssem']=$this->input->get_post('Ssem');
					}//re populate if close	
						if ($this->form_validation->run() == FALSE) {	
							//$message = '<h3>Your some detail is incorrect .</h3>';
	  						//$this->session->set_flashdata('msg',$message);
							//redirect('request/registartionsemester');
						}				
				else{
					//update in student_master
       	 				$this->db->trans_begin();

						$cdate = date("Y-m-d h:i:sa");					 	
			 			if($_POST['Ssem'] != $semsize){				
							//insert program data in student_program
							$progdata = array (
								'sp_smid'	=>	$Stuid,
								'sp_programid'	=>	$this->ncid,
								'sp_deptid'	=>	$this->depid,		
								'sp_sccode'	=>	$this->sccode,
								'sp_pcategory'	=>	$this->pcatid,
								'sp_branch'	=>	$this->brid,
								'sp_acadyear'	=>	$cacadyer,
								'sp_semregdate' =>	$cdate,
								'sp_semester'	=>      $_POST['Ssem']
								); 
			 				$this->db->insert('student_program',$progdata);
			 				$sprogramid = $this->db->insert_id();
						}
						else{
							$message1 = $semsize;
							$message = '<h3>You are already register in'." ". $semsize ." ". $prgpattern.'</h3>';
	  						$this->session->set_flashdata('msg',$message);
							redirect('request/semesterregi');
							}

						//insert smid and spid in student fees
						$feesdata = array (
							'sfee_smid'	=>	$Stuid,
							'sfee_spid'	=>	$sprogramid
				   			); 
							$this->db->insert('student_fees',$feesdata);

				//make transaction complete
        			$this->db->trans_complete();
			
	 				//check if transaction status TRUE or FALSE
        				if ($this->db->trans_status() === FALSE) {
			
            				//if something went wrong, rollback everything
            					$this->db->trans_rollback();
						$this->logger->write_logmessage("update", "Student registration not update record in student_master table.");
                    				$this->logger->write_dblogmessage("update", "Student registration not update record in student_master table.");
						$this->load->view('request/registartionsemester');
						//redirect('request/stufeesdetail');
           					//return FALSE;
      					 } else {	
            					//if everything went right, commit the data to the database
           					$this->db->trans_commit();
			 			$message = '<h3>You are semester registration successfully done.</h3>';
	  					$this->session->set_flashdata('msg',$message);			
						$this->logger->write_logmessage("update", "Student registration successfull update record in student_master table");
                    				$this->logger->write_dblogmessage("update", "Student registration successfull update record in student_master table" );
						redirect('request/stufeesdetail');
           		 			//return TRUE;
       			 		}
			}//close first else	
			}//if post close
		}//close semster difference if
		else{
			$message = '<h3>You can not register for next semester registration because you can not complete minimum duration.</h3>';
  			$this->session->set_flashdata('err_message',$message);
			redirect('studenthome');
		    }	
		
	$this->load->view('request/registartionsemester');
	}

	
	public function stufeesdetail(){
		$suid=$this->session->userdata('id_user');
		//print_r($suid);
		$this->Stuid=$this->common_model->get_listspfic1("student_master","sm_id","sm_userid",$suid)->sm_id;
		
		$stud_prg_rec = $this->common_model->get_listrow('student_program','sp_smid',$this->Stuid);
           	$degree_id = $stud_prg_rec->row()->sp_programid;
           	$noofsemester = sizeof($stud_prg_rec->result());
		
		$cacadyer = $this->user_model->getcurrentAcadYear();
		//print_r($cacadyer);
            	$semestertype = $this->user_model->getcurrentSemester();
        	//print_r($semestertype);
            	$semesterrec = $this->student_model->get_semester_no($this->Stuid,$cacadyer);
           	$semsize = sizeof($semesterrec);
		
		//$getmail = $this->common_model->get_elist('email_setting');
		//print_r($getmail);
		//$Sid = $this->session->userdata['sm_id'];
		//$totalfees = $_POST['totalfees'];
		
		$this->db->select('sp_id')->from('student_program')->where('sp_smid',$this->Stuid)->where('sp_semester',$semsize);
		$spid = $this->db->get()->row();
		//print_r($spid);
		foreach($spid as $stspid){
			$this->cusem = $this->common_model->get_listspfic1('student_program','sp_semester','sp_id',$stspid)->sp_semester;
			//print_r($this->cusem);
		}
		
		$this->appno=$this->common_model->get_listspfic1('student_entry_exit','senex_entexamapplicationno','senex_smid',$this->Stuid)->senex_entexamapplicationno;
		$this->sname=$this->common_model->get_listspfic1('student_master','sm_fname','sm_id',$this->Stuid)->sm_fname;
		$this->fname=$this->common_model->get_listspfic1('student_parent','spar_fathername','spar_smid',$this->Stuid)->spar_fathername;
		$this->gender=$this->common_model->get_listspfic1('student_master','sm_gender','sm_id',$this->Stuid)->sm_gender;
		$this->acadyear=$this->user_model->getcurrentAcadYearfadm();
		$this->prgid=$this->common_model->get_listspfic1('student_program','sp_programid','sp_smid',$this->Stuid)->sp_programid;
		$sarray='prg_name,prg_branch';	
		$wharray = array('prg_id' => $this->prgid);
    		$this->resultprg=$this->common_model->get_listarry("program",$sarray,$wharray);

		$this->catid=$this->common_model->get_listspfic1('student_master','sm_category','sm_id',$this->Stuid)->sm_category;
		$wharray = array('fm_programid' => $this->prgid, 'fm_semester' => $this->cusem);
		$sarray = 'fm_head,fm_amount';
		$wgenr = array('All', $this->gender);
		$wcateid = array('1', $this->catid);
		$this->db->select($sarray);
		$this->db->from('fees_master');
		$this->db->where_in('fm_gender',$wgenr);
		$this->db->where_in('fm_category',$wcateid);
		$this->db->where($wharray);
		$this->feesresult =  $this->db->get()->result();
	
				

		$this->load->view('request/student_feesdetail');
	}
	

	public function stufeespayment(){
		$suid=$this->session->userdata('id_user');
		//print_r($suid);
		$Stuid=$this->common_model->get_listspfic1("student_master","sm_id","sm_userid",$suid)->sm_id;
		
		$email= $this->common_model->get_listspfic1('student_master','sm_email','sm_id',$Stuid)->sm_email;
		$name= $this->common_model->get_listspfic1('student_master','sm_fname','sm_id',$Stuid)->sm_fname;

		$stud_prg_rec = $this->common_model->get_listrow('student_program','sp_smid',$Stuid);
           	$degree_id = $stud_prg_rec->row()->sp_programid;
           	$noofsemester = sizeof($stud_prg_rec->result());
		
		$cacadyer = $this->user_model->getcurrentAcadYear();
		//print_r($cacadyer);
            	$semestertype = $this->user_model->getcurrentSemester();
        	//print_r($semestertype);
            	$semesterrec = $this->student_model->get_semester_no($Stuid,$cacadyer);
           	$semsize = sizeof($semesterrec);
		
		//$getmail = $this->common_model->get_elist('email_setting');
		//print_r($getmail);
		//$Sid = $this->session->userdata['sm_id'];
		//$totalfees = $_POST['totalfees'];
		
		$this->db->select('sp_id')->from('student_program')->where('sp_smid',$Stuid)->where('sp_semester',$semsize);
		$spid = $this->db->get()->row();
		//print_r($spid);
		foreach($spid as $stspid){
			$this->cusem = $this->common_model->get_listspfic1('student_program','sp_semester','sp_id',$stspid)->sp_semester;
			//print_r($this->cusem);
		}

		$this->gender=$this->common_model->get_listspfic1('student_master','sm_gender','sm_id',$Stuid)->sm_gender;
		$this->acadyear=$this->user_model->getcurrentAcadYearfadm();
		$this->prgid=$this->common_model->get_listspfic1('student_program','sp_programid','sp_smid',$Stuid)->sp_programid;
		$this->catid=$this->common_model->get_listspfic1('student_master','sm_category','sm_id',$Stuid)->sm_category;
		$wharray = array('fm_programid' => $this->prgid, 'fm_semester' => $this->cusem);
		$sarray = 'fm_head,fm_amount';
		$wgenr = array('All', $this->gender);
		$wcateid = array('1', $this->catid);

		$this->db->select($sarray);
		$this->db->from('fees_master');
		$this->db->where_in('fm_gender',$wgenr);
		$this->db->where_in('fm_category',$wcateid);
		$this->db->where($wharray);
		$this->feesresult =  $this->db->get()->result();
		//print_r($this->feesresult);
		$this->totalfees = '';
		foreach($this->feesresult as $d2){
			$d2->fm_head;$d2->fm_amount;
			$this->totalfees = $this->totalfees+$d2->fm_amount;
			//print_r($this->totalfees);
		}
		
		if(isset($_POST['payment']))
		{
			$this->form_validation->set_rules('refNo','Reference number','trim|xss_clean|numeric|required');
            		$this->form_validation->set_rules('bank','Bank detail','trim|xss_clean|required');
            		$this->form_validation->set_rules('amount','Amount','trim|xss_clean|required');
            		$this->form_validation->set_rules('ftype','fees type field','trim|xss_clean|required');

			if($_POST){
				$this->data['refNo'] = $this->input->get_post('refNo');
				$this->data['bank']  = $this->input->get_post('bank');
				$this->data['amount']= $this->input->get_post('amount');
				$this->data['ftype']=$this->input->get_post('ftype');
			}

			if($this->form_validation->run() == FALSE){			
				$this->load->view('request/stu_feesoffline');
				return;
			}else{	
				//start the transaction
       	 			$this->db->trans_begin();		
				//update student fees table
				if(($_POST['amount']) == $this->totalfees)	
				{	
					$offline = array(
						'sfee_referenceno'   	=>	$_POST['refNo'],
                				'sfee_bankname'  	=>	$_POST['bank'],
                				'sfee_feeamount'  	=>	$_POST['amount'],
                				'sfee_feename'   	=>	$_POST['ftype'],
						'sfee_paymentmethod'    =>	'Offline'
                				);
					//print_r($data);
					foreach($spid as $stspid){
						$this->db->where('sfee_spid',$stspid);
						$this->db->where('sfee_smid',$Stuid);
						$this->db->update('student_fees', $offline); 
					}
				}
				else {				
					$message = '<h3>The payble fees and fees deposit in bank should be equal.</h3>';
	  				$this->session->set_flashdata('msg',$message);
					$this->logger->write_logmessage("update", "Offline payment  fees match error.");
	                    		$this->logger->write_dblogmessage("update", "Offline payment fees match error." );
					$this->load->view('request/stu_feesoffline');
					return;
				     }
				
				$this->logger->write_logmessage("update", "semester registration fees update in fees_master table.");
                    		$this->logger->write_dblogmessage("update", "semester registration fees update in fees_master table." );
				
				//make transaction complete
        			$this->db->trans_complete();
			
	 			//check if transaction status TRUE or FALSE
        			if ($this->db->trans_status() === FALSE) {
				
            			//if something went wrong, rollback everything
            				$this->db->trans_rollback();
					$this->logger->write_logmessage("update", "Student registration not update record in student_master table.");
                    			$this->logger->write_dblogmessage("update", "Student registration not update record in student_master table.");
					$this->load->view('request/stufeespayment');
					//redirect('request/stufeesdetail');
           				//return FALSE;
      				 } else {	
            				//if everything went right, commit the data to the database
           				$this->db->trans_commit();

					//if sucess send mail to user with login details 
		 					$sub='Student Semester Registration' ;
                        				$mess="Your registration is complete. Student email is ".$email." and name is ".$name ;
                	       				$mails = $this->mailsend_model->mailsnd($email, $sub, $mess);
							 //  mail flag check 			
							if($mails){
                        					$error[] ="At row".$i."sufficient data and mail sent sucessfully";
                        					$this->logger->write_logmessage("insert","semester registration fees submitted", "record added successfully for.".$name ." ".$email);
		      						$this->logger->write_dblogmessage("insert","semester registration fees submitted", "record added successfully for.".$name ." ".$email);
				    			}
							else{
        		       					$error[] ="At row".$i."sufficient data and mail does not sent";
		                				$this->logger->write_logmessage("insert"," semester registration fees not submitted", "record not added successfully for.".$name ." ".$email ." and mail does sent");
								$this->logger->write_dblogmessage("insert"," semester registration fees not submitted ", "record not added successfully for.".$name ." ".$email." and mail does sent");
			   				}

			 		$message = '<h3>Your offline payment successfull done.</h3>';
	  				$this->session->set_flashdata('msg',$message);			
					$this->logger->write_logmessage("update", "Student registration successfull update record in student_master table");
                    			$this->logger->write_dblogmessage("update", "Student registration successfull update record in student_master table" );
				 	redirect('studenthome/index'); 
           		 		//return TRUE;
       			 		}
	
			}//first else close

		}//close if post
		$this->load->view('request/stu_feesoffline');
	}


}

?>
