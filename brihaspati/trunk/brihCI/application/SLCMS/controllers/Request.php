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
//		$this->load->model('user_model');
        $this->load->model("user_model","usermodel");
		$this->load->model('Common_model',"commodel");
		$this->load->model("Student_model","stumodel");
		$this->load->model("Mailsend_model","mailmodel");
		$this->load->model("DateSem_model","datmodel");
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
		$Stuid=$this->commodel->get_listspfic1("student_master","sm_id","sm_userid",$suid)->sm_id;
		$this->appno = $this->commodel->get_listspfic1('student_entry_exit','senex_entexamapplicationno','senex_smid',$Stuid)->senex_entexamapplicationno;
		$stud_prg_rec = $this->commodel->get_listrow('student_program','sp_smid',$Stuid);
        $degree_id = $stud_prg_rec->row()->sp_programid;
        $noofsemester = sizeof($stud_prg_rec->result());

		
		$cacadyer = $this->usermodel->getcurrentAcadYear();
        $semestertype = $this->usermodel->getcurrentSemester();
        $semesterrec = $this->stumodel->get_semester_no($Stuid,$cacadyer);
//        $semsize = sizeof($semesterrec);
//		$this->curresem = $semsize;


		//student personnel detail
		$this->name=$this->commodel->get_listspfic1("student_master","sm_fname","sm_userid",$suid)->sm_fname;
		$this->sturollno = $this->commodel->get_listspfic1('student_entry_exit','senex_rollno','senex_smid',$Stuid)->senex_rollno;
		$this->mobile=$this->commodel->get_listspfic1('student_master','sm_mobile','sm_id',$Stuid)->sm_mobile;
		$this->email=$this->commodel->get_listspfic1('student_master','sm_email','sm_id',$Stuid)->sm_email;
		$this->dob=$this->commodel->get_listspfic1('student_master','sm_dob','sm_id',$Stuid)->sm_dob;
		$this->uid=$this->commodel->get_listspfic1('student_master','sm_uid','sm_id',$Stuid)->sm_uid;
		$this->bgroup=$this->commodel->get_listspfic1('student_master','sm_bloodgroup','sm_id',$Stuid)->sm_bloodgroup;
		$this->gender=$this->commodel->get_listspfic1('student_master','sm_gender','sm_id',$Stuid)->sm_gender;
		$this->religname=$this->commodel->get_listspfic1('student_master','sm_religion','sm_id',$Stuid)->sm_religion;
             
		//student parent and course detail
		$this->mname = $this->commodel->get_listspfic1('student_parent','spar_mothername','spar_smid',$Stuid)->spar_mothername;		
		$this->fathname=$this->commodel->get_listspfic1('student_parent','spar_fathername','spar_smid',$Stuid)->spar_fathername;
		
		$this->sem = $this->commodel->get_listspfic1('student_program','sp_semester','sp_smid',$Stuid)->sp_semester;
		
		$this->ncid = $this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$Stuid)->sp_programid;
		//get programe name
		$this->pname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$this->ncid)->prg_name;
		$this->progid = $this->commodel->get_listspfic1('program','prg_id','prg_name',$this->pname)->prg_id;
		
		//get program category name
		$this->pcatid = $this->commodel->get_listspfic1('student_program','sp_pcategory','sp_smid',$Stuid)->sp_pcategory;
		$this->pcatname = $this->commodel->get_listspfic1('programcategory','prgcat_name','prgcat_id',$this->pcatid)->prgcat_name;

		//postal address detail
		$this->padd=$this->commodel->get_listspfic1('student_parent','spar_caddress','spar_smid',$Stuid)->spar_caddress;
		$this->pcity=$this->commodel->get_listspfic1('student_parent','spar_ccity','spar_smid',$Stuid)->spar_ccity;
		$this->ppost=$this->commodel->get_listspfic1('student_parent','spar_cpostoffice','spar_smid',$Stuid)->spar_cpostoffice;
		$this->pdist=$this->commodel->get_listspfic1('student_parent','spar_cdistrict','spar_smid',$Stuid)->spar_cdistrict;
		$this->pstat=$this->commodel->get_listspfic1('student_parent','spar_cstate','spar_smid',$Stuid)->spar_cstate;
		$this->ppin=$this->commodel->get_listspfic1('student_parent','spar_cpincode','spar_smid',$Stuid)->spar_cpincode;
		$this->pcounname=$this->commodel->get_listspfic1('student_parent','spar_ccountry','spar_smid',$Stuid)->spar_ccountry;
		
		//get student category
		$this->cateid=$this->commodel->get_listspfic1('student_master','sm_category','sm_id',$Stuid)->sm_category;
		$this->catename=$this->commodel->get_listspfic1('category','cat_name','cat_id',$this->cateid)->cat_name;

		//get study center
		$this->sccode=$this->commodel->get_listspfic1('student_master','sm_sccode','sm_id',$Stuid)->sm_sccode;
		$this->scname=$this->commodel->get_listspfic1('study_center','sc_name','sc_code',$this->sccode)->sc_name;
		//get branch name
		$this->brid=$this->commodel->get_listspfic1('student_program','sp_branch','sp_smid',$Stuid)->sp_branch;
		$this->brname=$this->commodel->get_listspfic1('program','prg_branch','prg_id',$this->brid)->prg_branch;
		//get department name
		$this->depid=$this->commodel->get_listspfic1('student_program','sp_deptid','sp_smid',$Stuid)->sp_deptid;
		$this->depname=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$this->depid)->dept_name;

//		echo $getspid = array('sp_smid' => $Stuid, 'sp_semester' => $semsize);
		$getspid = array('sp_smid' => $Stuid, 'sp_semester' => $noofsemester);
	   	$spstid = $this->commodel->get_listspficemore('student_program','sp_id',$getspid);
		
		foreach($spstid as $stspid){
			$this->cusem = $this->commodel->get_listspfic1('student_program','sp_semester','sp_id',$stspid->sp_id)->sp_semester;
		}
//		$datawh = array('sp_smid' => $Stuid, 'sp_semester' => $semsize);
		$datawh = array('sp_smid' => $Stuid, 'sp_semester' => $noofsemester);
	    $diffdate = $this->commodel->get_listspficemore('student_program','sp_programid,sp_semregdate',$datawh);
		foreach($diffdate as $spdate):
		    $semdiffdate = $this->datmodel->diffdays($spdate->sp_semregdate);
			$spprgid = $spdate->sp_programid;
		endforeach;	
        echo $spprgid;
		echo $prgpattern=$this->commodel->get_listspfic1('program','prg_pattern','prg_id',$spprgid)->prg_pattern;
        echo "<br>";
		    $tmonth = $semdiffdate/30;
        // get semester no in which student have to be registered.

            /* get the total semester count in an academic year,if semester is even,there may be two 
             * records in an academic year ,if not so ask for current semester registraion.
             * if semester id Odd there will be 1 records in an academic year otherwise ask for 
             * semester registration.
             */
            $semesterrec = $this->stumodel->get_semester_no($suid,$cacadyear);
            $semsize = sizeof($semesterrec);

            if($semestertype == "Odd")
            {
                if($semsize == 1)
                {
                    $semester = $noofsemester;
                }
                else
                {
                    $semester = $noofsemester + 1;
                    //$semester = "Please register in the semester ".$semester;
                    //echo message for semester registration
                    // redirect('studenthome/studentsubject/');
                    // return;
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
                    //$semester = "Please register in the semester ".$semester;
                    // redirect('studenthome/studentsubject/');
                    // return;
                }
            }
            $this->cusem = $semester;

        /* calculate minimum months required to complete a semester or year. 
         * for yearly program, it should be greater than 11 months and semester 
         * system it should be months to register in a next year and semester 
         * as well as. 
         */
        					
		if((($tmonth > 5) && ($prgpattern == 'Semester')) || (($tmonth > 11) && ($prgpattern == 'Yearly')))
		{
		    if(isset($_POST['register']))
			{	
			    $this->form_validation->set_rules('Ssem','Semseter','trim|xss_clean|required');
				//Re-populate field
				if($_POST)
				{
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
					else
                    {
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
			 			$message = '<h3>Your registration successfully done.</h3>';
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
            //redirect('request/semesterregi');
		    }	
	$this->load->view('request/registartionsemester');
	}

	
	public function stufeesdetail(){
		$suid=$this->session->userdata('id_user');
        //get student id from student master
		$this->Stuid=$this->commodel->get_listspfic1("student_master","sm_id","sm_userid",$suid)->sm_id;
		//get program(semesters) of a student 
		$stud_prg_rec = $this->commodel->get_listrow('student_program','sp_smid',$this->Stuid);
        $degree_id = $stud_prg_rec->row()->sp_programid;
        $noofsemester = sizeof($stud_prg_rec->result());
        if($noofsemester == 1)
        {
		    $cacadyer = $this->usermodel->getcurrentAcadYearfadm();
        }
        else
            $cacadyer = $this->usermodel->getcurrentAcadYear();
        //$semestertype = $this->user_model->getcurrentSemester();
        //print_r($semestertype);
        //$semesterrec = $this->stumodel->get_semester_no($this->Stuid,$cacadyer);
        //echo $semsize = sizeof($semesterrec);
		$this->db->select('sp_id')->from('student_program')->where('sp_smid',$this->Stuid)->where('sp_semester',$noofsemester);
		$spid = $this->db->get()->row()->sp_id;
		/*foreach($spid as $stspid){
			$this->cusem = $this->commodel->get_listspfic1('student_program','sp_semester','sp_id',$stspid)->sp_semester;
			//print_r($this->cusem);
		}
		*/
		$this->appno=$this->commodel->get_listspfic1('student_entry_exit','senex_entexamapplicationno','senex_smid',$this->Stuid)->senex_entexamapplicationno;
		$this->sname=$this->commodel->get_listspfic1('student_master','sm_fname','sm_id',$this->Stuid)->sm_fname;
		$this->fname=$this->commodel->get_listspfic1('student_parent','spar_fathername','spar_smid',$this->Stuid)->spar_fathername;
		$this->gender=$this->commodel->get_listspfic1('student_master','sm_gender','sm_id',$this->Stuid)->sm_gender;
		//echo $this->acadyear=$this->user_model->getcurrentAcadYearfadm();
		$this->prgid=$this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$this->Stuid)->sp_programid;
		$sarray='prg_name,prg_branch';	
		$wharray = array('prg_id' => $this->prgid);
    	$this->resultprg=$this->commodel->get_listarry("program",$sarray,$wharray);

		$this->catid=$this->commodel->get_listspfic1('student_master','sm_category','sm_id',$this->Stuid)->sm_category;
		//$wharray = array('fm_programid' => $this->prgid, 'fm_semester' => $this->cusem);
		$wharray = array('fm_programid' => $this->prgid, 'fm_semester' => $noofsemester);
		$sarray = 'fm_head,fm_amount';
		$wgenr = array('All', $this->gender);
		$wcateid = array('1', $this->catid);
		$this->db->select($sarray);
		$this->db->from('fees_master');
		$this->db->where_in('fm_gender',$wgenr);
		$this->db->where_in('fm_category',$wcateid);
		$this->db->where($wharray);
		$this->feesresult =  $this->db->get()->result();
        print_r(sizeof($this->feesresult));
        $data['cacadyear'] = $cacadyer;
        $data['noofsemester'] = $noofsemester;	
				

		$this->load->view('request/student_feesdetail',$data);
	}
	

	public function stufeespayment(){
		$suid=$this->session->userdata('id_user');
		//print_r($suid);
		$Stuid=$this->commodel->get_listspfic1("student_master","sm_id","sm_userid",$suid)->sm_id;
		
		$email= $this->commodel->get_listspfic1('student_master','sm_email','sm_id',$Stuid)->sm_email;
		$name= $this->commodel->get_listspfic1('student_master','sm_fname','sm_id',$Stuid)->sm_fname;

		$stud_prg_rec = $this->commodel->get_listrow('student_program','sp_smid',$Stuid);
       	$degree_id = $stud_prg_rec->row()->sp_programid;
        $noofsemester = sizeof($stud_prg_rec->result());

        if($noofsemester == 1)
        {
            $cacadyer = $this->user_model->getcurrentAcadYearfadm();
        }
        else
            $cacadyer = $this->user_model->getcurrentAcadYear();
		
		//$cacadyer = $this->user_model->getcurrentAcadYear();
		//print_r($cacadyer);
       	$semestertype = $this->user_model->getcurrentSemester();
        	//print_r($semestertype);
       	$semesterrec = $this->stumodel->get_semester_no($Stuid,$cacadyer);
//       	$semsize = sizeof($semesterrec);
		
//		$this->db->select('sp_id')->from('student_program')->where('sp_smid',$Stuid)->where('sp_semester',$semsize);
		$this->db->select('sp_id')->from('student_program')->where('sp_smid',$Stuid)->where('sp_semester',$noofsemester);
		$spid = $this->db->get()->row();
		//print_r($spid);
		/*foreach($spid as $stspid){
			$this->cusem = $this->commodel->get_listspfic1('student_program','sp_semester','sp_id',$stspid)->sp_semester;
			//print_r($this->cusem);
		}
        */

		$this->gender=$this->commodel->get_listspfic1('student_master','sm_gender','sm_id',$Stuid)->sm_gender;
		//$this->acadyear=$this->user_model->getcurrentAcadYearfadm();
		$this->prgid=$this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$Stuid)->sp_programid;
		$this->catid=$this->commodel->get_listspfic1('student_master','sm_category','sm_id',$Stuid)->sm_category;
		//$wharray = array('fm_programid' => $this->prgid, 'fm_semester' => $this->cusem);
		$wharray = array('fm_programid' => $this->prgid, 'fm_semester' => $noofsemester);
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
                	       				$mails = $this->mailmodel->mailsnd($email, $sub, $mess);
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
	
	public function exam_regi(){
		$suid=$this->session->userdata('id_user');
		//print_r($suid);
		$Stuid=$this->commodel->get_listspfic1("student_master","sm_id","sm_userid",$suid)->sm_id;
		//print_r($Stuid);
           	//get current academic year
           	//$acadyear = $this->user_model->getcurrentAcadYear();
		$this->cdate = date('d-m-Y');
		$this->appno = $this->commodel->get_listspfic1('student_entry_exit','senex_entexamapplicationno','senex_smid',$Stuid)->senex_entexamapplicationno;
		$stud_prg_rec = $this->commodel->get_listrow('student_program','sp_smid',$Stuid);
           	$degree_id = $stud_prg_rec->row()->sp_programid;
           	$noofsemester = sizeof($stud_prg_rec->result());
		
		$this->cacadyer = $this->user_model->getcurrentAcadYear();
		//print_r($this->cacadyer);
            	$semestertype = $this->user_model->getcurrentSemester();
        	//print_r($semestertype);
            	$semesterrec = $this->stumodel->get_semester_no($Stuid,$this->cacadyer);
           	$semsize = sizeof($semesterrec);
		$this->curresem = $semsize;
		//print_r($semsize);
		
		$this->signresult = $this->commodel->get_listspfic1('student_master','sm_signature','sm_id',$Stuid)->sm_signature;
            	$this->seresult = $this->commodel->get_listrow('student_education','sedu_smid',$Stuid)->result();
		$this->phresult = $this->commodel->get_listspfic1('student_master','sm_photo','sm_id',$Stuid)->sm_photo;
		//student personnel detail
		$this->name=$this->commodel->get_listspfic1("student_master","sm_fname","sm_userid",$suid)->sm_fname;
		
		$this->mobile=$this->commodel->get_listspfic1('student_master','sm_mobile','sm_id',$Stuid)->sm_mobile;
		$this->email=$this->commodel->get_listspfic1('student_master','sm_email','sm_id',$Stuid)->sm_email;
		$this->dob=$this->commodel->get_listspfic1('student_master','sm_dob','sm_id',$Stuid)->sm_dob;
		$this->uid=$this->commodel->get_listspfic1('student_master','sm_uid','sm_id',$Stuid)->sm_uid;
		$this->bgroup=$this->commodel->get_listspfic1('student_master','sm_bloodgroup','sm_id',$Stuid)->sm_bloodgroup;
		$this->gender=$this->commodel->get_listspfic1('student_master','sm_gender','sm_id',$Stuid)->sm_gender;
		$this->religname=$this->commodel->get_listspfic1('student_master','sm_religion','sm_id',$Stuid)->sm_religion;

		//student parent and course detail
		$this->mname = $this->commodel->get_listspfic1('student_parent','spar_mothername','spar_smid',$Stuid)->spar_mothername;		
		$this->fathname=$this->commodel->get_listspfic1('student_parent','spar_fathername','spar_smid',$Stuid)->spar_fathername;
		
		$this->sem = $this->commodel->get_listspfic1('student_program','sp_semester','sp_smid',$Stuid)->sp_semester;
		
		$this->ncid = $this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$Stuid)->sp_programid;
		//get programe name
		$this->pname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$this->ncid)->prg_name;
		$this->progid = $this->commodel->get_listspfic1('program','prg_id','prg_name',$this->pname)->prg_id;
		
		//get program category name
		$this->pcatid = $this->commodel->get_listspfic1('student_program','sp_pcategory','sp_smid',$Stuid)->sp_pcategory;
		$this->pcatname = $this->commodel->get_listspfic1('programcategory','prgcat_name','prgcat_id',$this->pcatid)->prgcat_name;

		//postal address detail
		$this->padd=$this->commodel->get_listspfic1('student_parent','spar_caddress','spar_smid',$Stuid)->spar_caddress;
		$this->pcity=$this->commodel->get_listspfic1('student_parent','spar_ccity','spar_smid',$Stuid)->spar_ccity;
		$this->ppost=$this->commodel->get_listspfic1('student_parent','spar_cpostoffice','spar_smid',$Stuid)->spar_cpostoffice;
		$this->pdist=$this->commodel->get_listspfic1('student_parent','spar_cdistrict','spar_smid',$Stuid)->spar_cdistrict;
		$this->pstat=$this->commodel->get_listspfic1('student_parent','spar_cstate','spar_smid',$Stuid)->spar_cstate;
		$this->ppin=$this->commodel->get_listspfic1('student_parent','spar_cpincode','spar_smid',$Stuid)->spar_cpincode;
		$this->pcounname=$this->commodel->get_listspfic1('student_parent','spar_ccountry','spar_smid',$Stuid)->spar_ccountry;
		
		//get student category
		$this->cateid=$this->commodel->get_listspfic1('student_master','sm_category','sm_id',$Stuid)->sm_category;
		$this->catename=$this->commodel->get_listspfic1('category','cat_name','cat_id',$this->cateid)->cat_name;

		//get study center
		$this->sccode=$this->commodel->get_listspfic1('student_master','sm_sccode','sm_id',$Stuid)->sm_sccode;
		$this->scname=$this->commodel->get_listspfic1('study_center','sc_name','sc_code',$this->sccode)->sc_name;
		//get branch name
		$this->brid=$this->commodel->get_listspfic1('student_program','sp_branch','sp_smid',$Stuid)->sp_branch;
		$this->brname=$this->commodel->get_listspfic1('program','prg_branch','prg_id',$this->brid)->prg_branch;
		//get department name
		$this->depid=$this->commodel->get_listspfic1('student_program','sp_deptid','sp_smid',$Stuid)->sp_deptid;
		$this->depname=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$this->depid)->dept_name;

		//$sprogid = $this->commodel->get_listspfic1('student_program','sp_id','sp_smid',$Stuid)->sp_id;
		//print_r($sprogid);

		//$this->db->select('sp_id')->from('student_program')->where('sp_smid',$Stuid)->where('sp_semester',$semsize);
		//$spstid = $this->db->get()->row();
		$getspid = array('sp_smid' => $Stuid, 'sp_semester' => $semsize);
	    	$spstid = $this->commodel->get_listspficemore('student_program','sp_id',$getspid);
		
		//print_r($spstid);
		foreach($spstid as $stspid){
			$this->cusem = $this->commodel->get_listspfic1('student_program','sp_semester','sp_id',$stspid->sp_id)->sp_semester;
			//print_r($this->cusem);
		}
			$datawh = array('sp_smid' => $Stuid, 'sp_semester' => $semsize);
	    		$diffdate = $this->commodel->get_listspficemore('student_program','sp_programid,sp_semregdate',$datawh);
			//print_r($diffdate);
			foreach($diffdate as $spdate):
				$semdiffdate = $this->datmodel->diffdays($spdate->sp_semregdate);
				$spprgid = $spdate->sp_programid;
			endforeach;	
			//print_r($semdiffdate);
			$prgpattern=$this->commodel->get_listspfic1('program','prg_pattern','prg_id',$spprgid)->prg_pattern;
			$tmonth = $semdiffdate/30;
	if(isset($_POST['exm_regi']))
	{
		redirect('request/admit_card');
	}

	$this->load->view('request/stu_exam_regi');
	}
	
	public function admit_card(){
		$suid=$this->session->userdata('id_user');
		//print_r($suid);
		$Stuid=$this->commodel->get_listspfic1("student_master","sm_id","sm_userid",$suid)->sm_id;
		$this->cacadyer = $this->user_model->getcurrentAcadYear();
		$this->name=$this->commodel->get_listspfic1("student_master","sm_fname","sm_userid",$suid)->sm_fname;
		$this->enrono=$this->commodel->get_listspfic1("student_master","sm_enrollmentno","sm_userid",$suid)->sm_enrollmentno;
		$this->enrono=$this->commodel->get_listspfic1("student_master","sm_enrollmentno","sm_userid",$suid)->sm_enrollmentno;
		$this->rollno=$this->commodel->get_listspfic1("student_entry_exit","senex_rollno","senex_smid",$Stuid)->senex_rollno;
		//get study center
		$this->sccode=$this->commodel->get_listspfic1('student_master','sm_sccode','sm_id',$Stuid)->sm_sccode;
		$this->scname=$this->commodel->get_listspfic1('study_center','sc_name','sc_code',$this->sccode)->sc_name;
		$this->cacadyer = $this->user_model->getcurrentAcadYear();
		//print_r($this->cacadyer);
            	$semestertype = $this->user_model->getcurrentSemester();
        	//print_r($semestertype);
            	$semesterrec = $this->stumodel->get_semester_no($Stuid,$this->cacadyer);
           	$semsize = sizeof($semesterrec);
		$this->curresem = $semsize;
		$getspid = array('sp_smid' => $Stuid, 'sp_semester' => $semsize);
	    	$spstid = $this->commodel->get_listspficemore('student_program','sp_id',$getspid);
		$this->ncid = $this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$Stuid)->sp_programid;
		//get programe name
		$this->pname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$this->ncid)->prg_name;
		//print_r($spstid);
		foreach($spstid as $stspid){
			$this->cusem = $this->commodel->get_listspfic1('student_program','sp_semester','sp_id',$stspid->sp_id)->sp_semester;
			//print_r($this->cusem);
		}
		$this->phresult = $this->commodel->get_listspfic1('student_master','sm_photo','sm_id',$Stuid)->sm_photo;

		$this->load->view('request/stu_admit_card');
	}


}

?>
