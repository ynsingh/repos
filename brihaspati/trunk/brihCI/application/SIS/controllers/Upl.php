<?php

/* 
 * @name Upl.php
 * @author Nagendra Kumar Singh(nksinghiitk@gmail.com)
 * @author Om Prakash (omprakashkgp@gmail.com)  upload csv file for staff profile registration in SIS
 * 
 */
 
defined('BASEPATH') OR exit('No direct script access allowed');


class Upl extends CI_Controller
{
    function __construct() {
        parent::__construct();
		$this->load->model('Common_model',"commodel");
		$this->load->model('Login_model',"lgnmodel"); 
		$this->load->model("Mailsend_model","mailmodel");
	        $this->load->model('SIS_model',"sismodel");

        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
		redirect('welcome');
        }
    }

    public function index() {
        
        $this->uploadlogo();
        
    }
  
    // This function is used for upload logo
    public function uploadlogo(){
      // for clearing the previous sucess/error flashdata
      $array_items = array('success' => '', 'error' => '', 'warning' =>'');
      $this->session->set_flashdata($array_items);
      if(isset($_POST['uploadlogo'])) {
            $config = array(
		'upload_path' => "./uploads/logo/",
		'allowed_types' => "gif|jpg|png|jpeg|pdf",
		'overwrite' => TRUE,
		'max_size' => "2048000", // Can be set to particular file size , here it is 2 MB(2048 Kb)
		'max_height' => "768",
		'max_width' => "1024"
            );
            $this->load->library('upload', $config);

            if($this->upload->do_upload())
            {
		$data = array('upload_data' => $this->upload->data());
		$this->logger->write_logmessage("update","logo updated", "logo updated sucessfully");
		$this->logger->write_dblogmessage("update","logo updated", "logo updated sucessfully");
		$this->session->set_flashdata('success', 'Logo Successfully Updated.');
		redirect('upl/uploadlogo');
            }
            else
            {
		$error =  array('error' => $this->upload->display_errors());
		foreach ($error as $item => $value):
			$ferror = $item .":". $value;
		endforeach;
		$this->logger->write_logmessage("update","logo update error", $ferror);
		$this->logger->write_dblogmessage("update","logo update error", $ferror);
		$this->session->set_flashdata('error', $ferror);
		redirect('upl/uploadlogo');
            }
	}//close ifpost
	$this->load->view('upl/uploadlogo');	
    }

//This function has been created for upload staff list in the system using csv file format
    public function uploadslist(){
      // for clearing the previous success/error flashdata
      $array_items = array('success' => '', 'error' => '', 'warning' =>'');
      $this->session->set_flashdata($array_items);
      $error =array();
      if(isset($_POST['uploadslist'])) {
            $ferror='';
            if ( isset($_FILES["userfile"]))
            {
                $errors= array();
                $file_name = $_FILES['userfile']['name'];
                $file_ext=strtolower(end((explode('.',$file_name))));

                $expensions= array("txt","csv");

                if(in_array($file_ext,$expensions)=== false){
                    $ferror="extension not allowed, please choose a txt or csv file.";
                    $this->session->set_flashdata('error', $ferror);
                    $this->load->view('upl/uploadstafflist');
                    return;
                }

                else {
                    $flag=true;
                    $datal = array();
                    $uploadedfile = $_FILES['userfile']['tmp_name'];
                    $h = fopen($uploadedfile,"r");
	            fgetcsv($h); 		
                    $i=1;
                    while (false !== ($line = fgets($h)))
                    {
                       $datal = explode(",", $line);
                       $flag=false;
                       //print_r($datal);
                       if (count($datal) >= 13){
                            $pfno= $datal[0];
                            $empname = $datal[1];
                            $campus = $datal[2];
                            $uoc = $datal[3];
                            $uocuserid = $datal[4];
                            $ddouserid = $datal[5];
                            $dept = $datal[6];
                            $scheme = $datal[7];
                            $desig = $datal[8];
                            $payband = $datal[9];
                            $doa = $datal[10];
                            $dob = $datal[11];
                            $bankacc = $datal[12];
                            $aadhar = $datal[13];
                            $email = trim($datal[14]);
                            $email1 = trim($email, " ");
                            $role = 4;
                            if (count($datal) > 15)
                                $mobile = $datal[15];
                            else
                            $mobile='';
                            // check for duplicate
                            $isdup= $this->lgnmodel->isduplicate('edrpuser','username',$email );
                            if(!$isdup){
                                 //generate 10 digit random password
                                 $passwd=$this->commodel->randNum(10);
                                 // generate the hash of password
                                 $password=md5($passwd);
                                 // insert data into edrp user db1
                                 $dataeu = array(
                                    'username'=> $email,
                                    'password'=> $password,
                                    'email'=> $email,
                                    'componentreg'=> '*',
                                    'mobile'=>$mobile,
                                    'status'=>1,
                                    'category_type'=>$role,
                                    'is_verified'=>1
                                 );
                                $userflageu=$this->lgnmodel->insertrec('edrpuser', $dataeu);
                                //get the insert id of edrp user
                                $insertid= $this->lgnmodel->get_listspfic1('edrpuser','id','username',$email );
                                $userid=$insertid->id;
                                if($userflageu){
                                // insert into  user profile db1
                                $dataup = array(
                                      'userid'=>$userid,
                                      'firstname'=>$empname,
                                      'lang'=> 'english',
                                      'mobile'=>$mobile,
                                      'status'=>1
                                  );
                                $userflagup=$this->lgnmodel->insertrec('userprofile', $dataup);

		                $emdupl = $this->sismodel->isduplicate('employee_master','emp_email', $email );
				if(!$emdupl){
				$dataem = array(
                            		'emp_code' => $pfno,
                            		'emp_name' => $empname,
                   			'emp_specialisationid' => '',
                	    		'emp_dept_code'       => $this->commodel->get_listspfic1('Department', 'dept_id', 'dept_name', $dept)->dept_id,
                    			'emp_schemeid'        => $this->sismodel->get_listspfic1('scheme_department', 'sd_id', 'sd_name', $scheme)->sd_id,
                    			'emp_desig_code'      => $this->commodel->get_listspfic1('designation', 'desig_id', 'desig_name', $desig)->desig_id,
                    			'emp_post'            =>'',

                    			'emp_gender'          =>'',
                    			'emp_community'       =>'',
                    			'emp_religion'        =>'',
                    			'emp_caste'           =>'',
				
					'emp_type_code'       =>'',
                    			'emp_salary_grade'    => $this->sismodel->get_listspfic1('salary_grade_master', 'sgm_id', 'sgm_name', $payband)->sgm_id,
                    			'emp_basic'           =>'',
                    			'emp_emolution'       =>'',
                    			'emp_nhisidno'        =>'',
                    			'emp_doj'             => $doa,
                    			'emp_pnp'             =>'',
                    			'emp_phd_status'      =>'',

	                    		'emp_dateofphd'       =>'',
        	            		'emp_AssrExam_status' =>'',
                	    		'emp_dateofAssrExam'  =>'',
                   			'emp_dor'             =>'',
                    			'emp_dateofHGP'       =>'',
                    			'emp_pan_no'          =>'',
                    	
					'emp_aadhaar_no'      => $aadhar,
                    			'emp_bank_ifsc_code'  =>',',
                    			'emp_bank_accno'      => $bankacc,
                    			'emp_dob'             => $dob,
                    			'emp_father'          =>'',
                    			'emp_email'           => $email,

                   			'emp_address'         =>'',
                    			'emp_mothertongue'    =>'',
                    			'emp_citizen'         =>'',
	                   		'emp_scid'            => $this->commodel->get_listspfic1('study_center', 'sc_id', 'sc_name', $campus)->sc_id,
        	            		'emp_uocid'           => $this->lgnmodel->get_listspfic1('authorities', 'id', 'name', $uoc)->id,
        	            		'emp_uocuserid'       => $uocuserid,
        	            		'emp_ddouserid'       => $ddouserid,
        		        	'emp_phone'           =>'' 

				 );
                    		/* insert record into  employe_emaster */
                                $this->sismodel->insertrec('employee_master', $dataem);
			        $this->logger->write_logmessage("insert", "data insert in employee_master table.");
                    		$this->logger->write_dblogmessage("insert", "data insert in employee_master table." );

                    		$dataems = array(
                        		'ems_code'         =>$pfno,
                        		'ems_working_type' =>''
					
		                    );
                    		/* insert record into  employe_emaster_support */
                    		$this->sismodel->insertrec('employee_master_support', $dataems);
                    		$this->logger->write_logmessage("insert", "data insert in employee_master_support table.");
                    		$this->logger->write_dblogmessage("insert", "data insert in employee_master_support table." );
				}		
                                if($userflagup){
                                /* insert into user_role_type */
                                $dataurt = array(
                                      'userid'=> $userid,
                                      'roleid'=> $role,
                                      'deptid'=> $this->commodel->get_listspfic1('Department', 'dept_id', 'dept_name', $dept)->dept_id,
                                      'scid'=> $this->commodel->get_listspfic1('study_center', 'sc_id', 'sc_name', $campus)->sc_id,
                                      'usertype'=>'Employee'
                                   );
                                 $userflagurt=$this->sismodel->insertrec('user_role_type', $dataurt) ;
                                 if($userflagurt){
                                        $sub='Staff Profile Registration' ;
                                        $mess="You are registered as a staff. Your user id ".$email ." and password is ".$passwd ;
                                        $mails = $this->mailmodel->mailsnd($email, $sub, $mess);
                                        //  mail flag check                         
                                        if($mails){
                                               $error[] ="At row".$i."sufficient data and mail sent sucessfully";
                                               $this->logger->write_logmessage("insert"," add staff edrpuser,profile, employee_master and user role type ", "record added successfully for.".$empname ." ".$email );
                                               $this->logger->write_dblogmessage("insert"," add staff edrpuser,profile, employee_master and user role type ", "record added successfully for.".$empname ." ".$email );
                                            }
                                            else{
                                               $error[] ="At row".$i."sufficient data and mail does sent";
                                               $this->logger->write_logmessage("insert"," add staff edrpuser,profile, employee_master and user role type ", "record added successfully for.".$empname ." ".$email ." and mail does sent");
                                               $this->logger->write_dblogmessage("insert"," add staff edrpuser,profile, employee_master and user role type ", "record added successfully for.".$empname ." ".$email." and mail does sent" );
                                            }
                                        }else{
                                            //set the message for error in entering data in user role type
                                            $this->logger->write_logmessage("insert"," Error in adding staff edrpuser,profile and user role type ", " data insert error . ".$empname ." ".$email );
                                            $this->logger->write_dblogmessage("insert"," Error in adding staff edrpuser,profile and user role type ", " data insert error . ".$empname ." ".$email);
                                            // delete edrp user data
                                            $result = $this->lgnmodel->deleterow('edrpuser','id',$userid);
                                            // delete user profile data
                                            $result = $this->lgnmodel->deleterow('userprofile','userid',$userid);
                                        }
                                    }else{
                                        //set the message for error in entering data in user profile table
                                        $this->logger->write_logmessage("insert"," Error in adding staff edrpuser,profile ", " data insert error . ".$empname ." ".$email  );
                                        $this->logger->write_dblogmessage("insert"," Error in adding staff edrpuser,profile ", " data insert error . ".$empname ." ".$email );
                                        // delete edrp user data
                                        $result = $this->lgnmodel->deleterow('edrpuser','id',$userid);
                                    }
                                }else{
                                    // set the message for error in entering data in edrp table
                                    $this->logger->write_logmessage("insert"," Error in adding staff edrpuser ", " data insert error . ".$empname ." ".$email  );
                                    $this->logger->write_dblogmessage("insert"," Error in adding staff edrpuser ", "data insert error . ".$empname ." ".$email );
                                }
                                // $this->session->set_flashdata('success', ' sufficient data');        
                            }//close for is dup
                            else{
                                $error[] ="At row".$i."duplicate data";
                                $this->logger->write_logmessage("insert"," Error in adding staff edrpuser ", "At row".$i."duplicate data"  );
                                $this->logger->write_dblogmessage("insert"," Error in adding staff edrpuser ", "At row".$i."duplicate data" );
                            }
                            $i++;
                        }
                        else{
                            //  insufficient data
                            $error[] ="At row".$i."insufficient data";
                            $this->logger->write_logmessage("insert"," Error in adding staff edrpuser ", "At row".$i."insufficient data"  );
                            $this->logger->write_dblogmessage("insert"," Error in adding staff edrpuser ", "At row".$i."insufficient data" );
                            //  $this->session->set_flashdata('error', ' insufficient data');
                            $i++;
                        }
                    }
                    if($flag){
                        $this->session->set_flashdata('error', ' File without data');
                        $this->load->view('upl/uploadstafflist');
                        return;
                    }else{
                        // print_r($error);
                        foreach ($error as $item => $value):
                        $ferror = $ferror ."</br>". $item .":". $value;
                        endforeach;
                        //display error of array
                        //put ferror in log file.
                        $this->session->set_flashdata('success', $ferror);
                        $this->load->view('upl/uploadstafflist');
                        return;
                    }
		}
            }//userfile checks
       }// check for pressing correct button
      $this->load->view('upl/uploadstafflist');
   }
}
