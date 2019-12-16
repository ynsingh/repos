<?php

/* 
 * @name Incometax.php
 * @author Deepika Chaudhary (chaudharydeepika88@gmail.com)
 */

defined('BASEPATH') OR exit('No direct script access allowed');


class Incometax extends CI_Controller
    {
    function __construct() {
        parent::__construct();
	$this->load->model('Login_model',"lgnmodel");
        $this->load->model('SIS_model',"sismodel");
	$this->load->model("user_model","usermodel");
	$this->load->model('Common_model',"commodel");
        if(empty($this->session->userdata('id_user'))) {
          $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('welcome');
         }
    }

 public function incometax(){
	$currentuser=$this->session->userdata('username');
	$whdata = array('usm_status'=>0);
	$empmaster_data=$this->sismodel->get_listrow('employee_master','emp_email', $currentuser);
	$data['empmaster']=$this->sismodel->get_listrow('user_saving_master','usm_creatorid', $currentuser)->row();
        $data['record'] = $empmaster_data->row();

	if(isset($_POST['incometax'])) {
		$this->form_validation->set_rules('usm_80C','U/S 80C','trim|xss_clean|required|numeric');
        	$this->form_validation->set_rules('usm_80CCD','U/S 80CCD (1-B)','trim|xss_clean|numeric');
        	$this->form_validation->set_rules('usm_80D','U/S 80D','trim|xss_clean|numeric');
        	$this->form_validation->set_rules('usm_80DD','U/S 80DD','trim|xss_clean|numeric');
        	$this->form_validation->set_rules('usm_80E','U/S 80E','trim|xss_clean|numeric');
       		$this->form_validation->set_rules('usm_80G','U/S 80G','trim|xss_clean|numeric');
        	$this->form_validation->set_rules('usm_80GGA','U/S 80GGA','trim|xss_clean|numeric');
        	$this->form_validation->set_rules('usm_80U','U/S 80U','trim|xss_clean|numeric');
        	$this->form_validation->set_rules('usm_24B','U/S 24B','trim|xss_clean|numeric');
 
		if($this->form_validation->run()==TRUE){
		    	$pfno=$this->sismodel->get_listspfic1('employee_master','emp_code','emp_email', $currentuser)->emp_code;
			//get the current financial year
 			$fyear=$this->usermodel->getcurrentFyYear();
		    	// get the empid
		    	$empid=$this->sismodel->get_listspfic1('employee_master','emp_id','emp_email', $currentuser)->emp_id;	
		    	$deptid=$this->sismodel->get_listspfic1('employee_master','emp_dept_code','emp_id', $empid)->emp_dept_code;	
                    
		     	$data = array(
		    		'usm_80C'=>$_POST['usm_80C'],
                    		'usm_80CCD'=>$_POST['usm_80CCD'],
                    		'usm_80D'=>$_POST['usm_80D'],
                    		'usm_80DD'=>$_POST['usm_80DD'],
                    		'usm_80E'=>$_POST['usm_80E'],
                    		'usm_80G'=>$_POST['usm_80G'],
                    		'usm_80GGA'=>$_POST['usm_80GGA'],
                    		'usm_80U'=>$_POST['usm_80U'],
                    		'usm_24B'=>$_POST['usm_24B'],
                    		'usm_modifierid'=>$this->session->userdata('username'),
                    		'usm_modifydate'=>date('y-m-d')
			);
			//check for duplicate
			$whdata= array ('usm_pfno' => $pfno,'usm_fyear'=>$fyear);
			$dup=$this->sismodel->isduplicatemore('user_saving_master',$whdata);	
			if(!$dup){
				$data['usm_pfno']=$pfno;
				$data['usm_empid']=$empid;
				$data['usm_deptid']=$deptid;
				$data['usm_fyear']=$fyear;
				$data['usm_creatorid']=$this->session->userdata('username');
				$data['usm_creatordate']=date('Y-m-d');
				$usersavingflag=$this->sismodel->insertrec('user_saving_master', $data) ;

				if ( ! $usersavingflag)
        	        	{
                	    		$this->logger->write_logmessage("error", "Error in adding income tax details.");
	                    		$this->logger->write_dblogmessage("error", "Error in adding income tax details.");
        	            		$this->session->set_flashdata("err_message",'Error in adding income tax details.');
                	    		redirect('incometax/incometax');
                		}
                		else{
	                    		$this->logger->write_logmessage("insert","user saving master record insert successfully");
        	            		$this->logger->write_dblogmessage("insert", "Add user saving master record");
                	    		$this->session->set_flashdata("success", "User Saving Master add successfully...");
	                    		redirect("incometax/incometax");
        	        	}
			}
			else{
	                        $usmid=$this->sismodel->get_listspficemore('user_saving_master','usm_id',$whdata);       
				foreach ($usmid as $row ){
                                $usmiid=$row->usm_id;
				}
				$updatesavingflag=$this->sismodel->updaterec('user_saving_master', $data,'usm_id',$usmiid) ;
                		if ( ! $updatesavingflag)
                		{
	                    		$this->logger->write_logmessage("error", "Error in update income tax details.");
        	            		$this->logger->write_dblogmessage("error", "Error in update income tax details.");
                	    		$this->session->set_flashdata("err_message",'Error in update income tax details.');
	                    		redirect('incometax/incometax');
                		}
                		else{
	                    		$this->logger->write_logmessage("update","user saving master record update successfully");
        	            		$this->logger->write_dblogmessage("update", "update user saving master record");
                	    		$this->session->set_flashdata("success", "User Saving Master Update Successfully...");
	                    		redirect("incometax/incometax");
        	                  }
	       }
	     }
          }
        $this->load->view('incometax/incometax',$data);
      }

   }

