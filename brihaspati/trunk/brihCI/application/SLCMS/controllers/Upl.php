<?php

/* 
 * @name Upl.php
 * @author Nagendra Kumar Singh(nksinghiitk@gmail.com)
 * @author Malvika Upadhyay (malvikaupadhyay644@gmail.com)
 * 
 */
 
defined('BASEPATH') OR exit('No direct script access allowed');


class Upl extends CI_Controller
{
    function __construct() {
        parent::__construct();
		$this->load->model('Common_model',"commodel");
		$this->load->model('Login_model',"logmodel"); 
		$this->load->model("Mailsend_model","mailmodel");
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
	//$array_items = array('success' => '', 'error' => '', 'warning' =>'');
	//$this->session->set_flashdata($array_items);

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
		//$error =  array('error' => $this->upload->display_errors());
		//foreach ($error as $item => $value):
		//	$ferror = $item .":". $value;
		//endforeach;
		//$this->logger->write_logmessage("update","logo update error", $ferror);
		//$this->logger->write_dblogmessage("update","logo update error", $ferror);
		//$this->session->set_flashdata('error', $ferror);
        $this->logger->write_logmessage("update","logo update error.");
        $this->logger->write_dblogmessage("update","logo update error.");
        $this->session->set_flashdata('error', 'Logo is not uploaded successfully.');
		redirect('upl/uploadlogo');
            }
	}//close ifpost
	$this->load->view('upl/uploadlogo');	
    }
    // This function is used for upload student list in the system

    public function uploadstulist(){
        // for clearing the previous sucess/error flashdata
	$array_items = array('success' => '', 'error' => '', 'warning' =>'');
        $this->session->set_flashdata($array_items);

        if(isset($_POST['uploadstulist'])) {

            if ( isset($_FILES["userfile"]))
            {
                $errors= array();
      		$file_name = $_FILES['userfile']['name'];
		$file_size = $_FILES['userfile']['size'];
      		$file_tmp = $_FILES['userfile']['tmp_name'];
      		$file_type = $_FILES['userfile']['type'];
      		$file_ext=strtolower(end((explode('.',$file_name))));
      
      		$expensions= array("txt","csv");
      
      		if(in_array($file_ext,$expensions)=== false){
                    $ferror="extension not allowed, please choose a txt or csv file.";
                    $this->session->set_flashdata('error', $ferror);
                    $this->load->view('upl/uploadstulist');
                    return;
      		}
      
      		//	if($file_size > 2097152) {
         	//		$errors[]='File size must be excately 2 MB';
      		//	}
			
		//	if ($_FILES["userfile"]["error"] > 0) {
            	//		echo "Return Code: " . $_FILES["userfile"]["error"] . "<br />";
        	//	}
        	else {
                    $flag=true;
                    $datal = array();
                    $uploadedfile = $_FILES['userfile']['tmp_name'];
                    $h = fopen($uploadedfile,"r");
                    while (false !== ($line = fgets($h)))
                    {       
                        $datal = explode(",", $line);
			$flag=false;
			//print_r($datal);
			if (count($datal) >= 5){
                            $name = $datal[0];
                            $email = $datal[1];
                            $dept = $datal[2];
                            $role = $datal[3];
                            $sc = $datal[4];
                            $mobile = $datal[5];
                            // insert into edrp user db1
                            // insert into  user profile db1
			    // insert into user last staus db1
                            // insert into user role type db
			    $this->logger->write_logmessage("update","logo updated", "logo updated sucessfully");
		            $this->logger->write_dblogmessage("update","logo updated", "logo updated sucessfully");
                            $this->session->set_flashdata('success', ' sufficient data');	
                            //	$this->session->set_flashdata('success', 'Logo Successfully Updated.');
                            $this->load->view('upl/uploadstulist');
                            return;
			}
			else{
                            //	insufficient data
				$this->session->set_flashdata('error', ' insufficient data');
				$this->load->view('upl/uploadstulist');
		                return;
			}
                    }
                    if($flag){
			$this->session->set_flashdata('error', ' File without data');
			$this->load->view('upl/uploadstulist');
		        return;
                    }
		}
            }//userfile checks
            else
            {
            	$error =  array('error' => $this->upload->display_errors());
	        foreach ($error as $item => $value):
                $ferror = $item .":". $value;
        	endforeach;
                $this->logger->write_logmessage("update","logo update error", $ferror);
	        $this->logger->write_dblogmessage("update","logo update error", $ferror);
        	$this->session->set_flashdata('error', $ferror);
                $this->load->view('upl/uploadstulist');
                return;
            }
        }// check for pressing correct button
        $this->load->view('upl/uploadstulist');
    }

    // upload student merit list
    public function uploadstumerit(){
	// for clearing the previous sucess/error flashdata
        $array_items = array('success' => '', 'error' => '', 'warning' =>'');
       	$this->session->set_flashdata($array_items);
	$error =array();
       	if(isset($_POST['uploadstumerit'])) {
            $ferror='';
            if ( isset($_FILES["userfile"]))
            {
		$errors= array();
      		$file_name = $_FILES['userfile']['name'];
		//	$file_size = $_FILES['userfile']['size'];
      		//	$file_tmp = $_FILES['userfile']['tmp_name'];
      		//	$file_type = $_FILES['userfile']['type'];
      		$file_ext=strtolower(end((explode('.',$file_name))));
      
      		$expensions= array("txt","csv");
      
      		if(in_array($file_ext,$expensions)=== false){
                    $ferror="extension not allowed, please choose a txt or csv file.";
        	    $this->session->set_flashdata('error', $ferror);
                    $this->load->view('upl/uploadstumerit');
                    return;
      		}
        	else {
                    $flag=true;
                    $datal = array();
                    $uploadedfile = $_FILES['userfile']['tmp_name'];
                    $h = fopen($uploadedfile,"r");
                    $i=1;
                    while (false !== ($line = fgets($h)))
                    {       
                        $datal = explode(",", $line);
			$flag=false;
	//		print_r($datal);
			//if (count($datal) >= 13){
                           // $appno = $datal[0];
                           // $entexamname = $datal[1];
                            //$entexamrollno = $datal[2];
                          //  $course_name = $datal[3];
                        //    $branchname = $datal[4];
                        //    $name = $datal[5];
                        //    $email = $datal[6];
                        //    $father_name = $datal[7];
                         //   $marks = $datal[8];
                         //   $admission_quota = $datal[9];
                         //   $category  = $datal[10];
                         //   $meritlist_no = $datal[11];
                         //   $lastdate_admission = $datal[12];
            if (count($datal) >= 15){
                            $jeemainno = $datal[0];
                $jeeappno = $datal[1];  
                            $entexamname = $datal[2];
                            $entexamrollno = $datal[3];
                            $course_name = $datal[4];
                            $branchname = $datal[5];
                            $name = $datal[6];
                $dob = $datal[7];   
                            $email = $datal[8];
                            $father_name = $datal[9];
                            $marks = $datal[10];
                            $admission_quota = $datal[11];
                            $category  = $datal[12];
                            $meritlist_no = $datal[13];
                            $lastdate_admission = $datal[14];


                            // check for duplicate
                           // $isdup= $this->commodel->isduplicate('admissionmeritlist','student_email',$email);
			 /* $isdup= $this->commodel->isduplicate('admissionmeritlist','application_no',$appno); */	$isdup= $this->commodel->isduplicate('admissionmeritlist','jeeapplication_no',$jeeappno);  
                            if(!$isdup){
				    	// insert into student merit list db
					$dataurt = array(
				          // 'application_no'=> $appno,
				           'jee_mainno'=> $jeemainno,
                       'jeeapplication_no'=> $jeeappno,
                           'entexamname'=> $entexamname,
				           'entexamrollno'=> $entexamrollno,
				           'course_name'=> $course_name,
				           'branchname'=> $branchname,
				           'student_name'=> $name,
                           'student_dob'=> $dob,
				           'student_email'=> $email,
				           'father_name'=> $father_name,
				           'marks'=> $marks,
				           'admission_quota'=> $admission_quota,
				           'category'=> $category,
				           'meritlist_no'=> $meritlist_no,
				           'lastdate_admission'=> $lastdate_admission,
				           'admission_taken'=> 'No'
        	    			);
					$userflagurt=$this->commodel->insertrec('admissionmeritlist', $dataurt) ;
					if($userflagurt){
                                            $sub='You are eligible for the admission' ;
					     $upimg = '<input type="image" src="http://103.246.106.195/~brihaspati/brihCI/uploads/logo/logo1.png" alt="Submit" style="width:100%" height="80">';

				 //mail function
				/*$mess = "<table width='50%'; style='border:1px solid #3A5896;background-color:#8470FF;color:white;font-size:18px;' align=center border=0>
					   <tr><td colspan=2>".$upimg."</br><hr></td></tr>
					   <tr><td colspan=2><b>Congrats!  Now you are eligible for taking the admission.</td></tr>
					   <tr><td colspan=2><b>Your admissions details are given below.</td></tr>	
					   <tr height=15><td colspan=2></td></tr>
					   <tr><td width=370><b>Hall Ticket Number : </b></td><td align=left>".$entexamrollno."</td></tr> 
					   <tr><td><b>Program Name : </b> </td><td align=left>".$course_name ." ( ".$branchname ." )</td></tr>
					   <tr><td><b>Merit list number :</b></td><td align=left>".$meritlist_no."</td></tr>
					   <tr><td><b>Last date of admission : </b></td><td align=left> ".$lastdate_admission ."</td></tr>
				   	   <tr><td colspan=2 align=right><a href='".site_url('Student/student_step0')."' style='color:yellow;' title='Click to link'>Click To Link For Admission</a></td><tr>
					</table> " ;*/

                    $mess = "<table width='50%'; style='border:1px solid #3A5896;background-color:#8470FF;color:white;font-size:18px;' align=center border=0>
                       <tr><td colspan=2>".$upimg."</br><hr></td></tr>
                       <tr><td colspan=2><b>Congrats!  Now you are eligible for taking the admission.</td></tr>
                       <tr><td colspan=2><b>Your admissions details are given below.</td></tr>  
                       <tr height=15><td colspan=2></td></tr>
                       <tr><td width=370><b>JEE Main Number : </b></td><td align=left>".$jeemainno."</td></tr> 
                       <tr><td><b>JEE Application Number : </b> </td><td align=left>".$jeeappno."</td></tr>
                       <tr><td><b>Merit list number :</b></td><td align=left>".$meritlist_no."</td></tr>
                       <tr><td><b>Last date of admission : </b></td><td align=left> ".$lastdate_admission ."</td></tr>
                       <tr><td colspan=2 align=right><a href='".site_url('Student/student_step0')."' style='color:yellow;' title='Click to link'>Click To Link For Admission</a></td><tr>
                    </table> " ;

                                           // $mess="Congrats!  Now you are eligible for taking the admission. \n Your admissions details are given below - \n Hall ticket number - ".$entexamrollno." \n Program name - ".$course_name ." ( ".$branchname ." )  \n  Merit list number - ".$meritlist_no." \n Last date of admission - ".$lastdate_admission." \n Kindly check the website - base_url()";
                                            $mails = $this->mailmodel->mailsnd($email, $sub, $mess);
					    //  mail flag check 			
					    if($mails){
                                	            $error[] ="At row".$i."sufficient data and mail sent sucessfully";
                        	                    $this->logger->write_logmessage("insert"," add student merit list ", "record added successfully for.".$name ." ".$email );
						    $this->logger->write_dblogmessage("insert"," add student merit list ", "record added successfully for.".$name ." ".$email );
					    }
					    else{
        	                                    $error[] ="At row".$i."sufficient data and mail does sent";
	                                            $this->logger->write_logmessage("insert"," add student merit list ", "record added successfully for.".$name ." ".$email ." and mail does sent");
						    $this->logger->write_dblogmessage("insert"," add student merit list ", "record added successfully for.".$name ." ".$email." and mail does sent" );
					    }
					}else{
                                            //set the message for error in entering data in student merit list
                                            $this->logger->write_logmessage("insert"," Error in adding student merit list ", " data insert error . ".$name ." ".$email );
                                            $this->logger->write_dblogmessage("insert"," Error in adding student merit list ", " data insert error . ".$name ." ".$email);
					}
                            }//close for is dup
                            else{
				$error[] ="At row".$i."duplicate data";
				$this->logger->write_logmessage("insert"," Error in adding student merit list ", "At row".$i."duplicate data"  );
	                        $this->logger->write_dblogmessage("insert"," Error in adding student merit list ", "At row".$i."duplicate data" );
			    }
                            $i++;
			}
			else{
                            //	insufficient data
                            $error[] ="At row".$i."insufficient data";
                            $this->logger->write_logmessage("insert"," Error in adding student merit list ", "At row".$i."insufficient data"  );
                            $this->logger->write_dblogmessage("insert"," Error in adding student merit list ", "At row".$i."insufficient data" );
                            //	$this->session->set_flashdata('error', ' insufficient data');
			    $i++;

			}
                    }
                    if($flag){
			$this->session->set_flashdata('error', ' File without data');
			$this->load->view('upl/uploadstumerit');
		        return;
		    }else{
                        //					print_r($error);
			foreach ($error as $item => $value):
			    $ferror = $ferror ."</br>". $item .":". $value;
                        endforeach;
			//display error of array
			//put ferror in log file.
			$this->session->set_flashdata('success', $ferror);	
	                $this->load->view('upl/uploadstumerit');
			return;
                    }
		}
            }//userfile checks
            else
            {
                //		$error = array( 'error' => $this->upload->display_errors());
		foreach ($error as $item => $value):
        	    $ferror = $ferror ."</br>".$item .":". $value;
                endforeach;
                $this->session->set_flashdata('error', $ferror);
                $this->load->view('upl/uploadstumerit');
                return;
            }
        }// check for pressing correct button
        $this->load->view('upl/uploadstumerit');
    }

    // upload teacher list
    public function uploadtlist(){
	// for clearing the previous sucess/error flashdata
        $array_items = array('success' => '', 'error' => '', 'warning' =>'');
       	$this->session->set_flashdata($array_items);
	$error =array();
       	if(isset($_POST['uploadtlist'])) {
            $ferror='';
            if ( isset($_FILES["userfile"]))
            {
		$errors= array();
      		$file_name = $_FILES['userfile']['name'];
		//	$file_size = $_FILES['userfile']['size'];
      		//	$file_tmp = $_FILES['userfile']['tmp_name'];
      		//	$file_type = $_FILES['userfile']['type'];
      		$file_ext=strtolower(end((explode('.',$file_name))));
      
      		$expensions= array("txt","csv");
      
      		if(in_array($file_ext,$expensions)=== false){
                    $ferror="extension not allowed, please choose a txt or csv file.";
        	    $this->session->set_flashdata('error', $ferror);
                    $this->load->view('upl/uploadteacherlist');
                    return;
      		}
      
      		//	if($file_size > 2097152) {
         	//		$errors[]='File size must be excately 2 MB';
      		//	}
				
		//	if ($_FILES["userfile"]["error"] > 0) {
            	//		echo "Return Code: " . $_FILES["userfile"]["error"] . "<br />";
        	//	}
        	else {
                    $flag=true;
                    $datal = array();
                    $uploadedfile = $_FILES['userfile']['tmp_name'];
                    $h = fopen($uploadedfile,"r");
                    $i=1;
                    while (false !== ($line = fgets($h)))
                    {       
                        $datal = explode(",", $line);
			$flag=false;
	//		print_r($datal);
			if (count($datal) >= 5){
                            $name = $datal[0];
                            $email = $datal[1];
                            $dept = $datal[2];
                            $role = $datal[3];
                            $sc = $datal[4];
			    if (count($datal) > 5)
                                $mobile = $datal[5];
                            else 
                            $mobile='';

                            // check for duplicate
                            $isdup= $this->logmodel->isduplicate('edrpuser','username',$email );
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
				$userflageu=$this->logmodel->insertrec('edrpuser', $dataeu) ;
				//get the insert id of edrp user
				$insertid= $this->logmodel->get_listspfic1('edrpuser','id','username',$email );
				$insid=$insertid->id;
                                //print_r("this is testing upload file===".$userflageu."and id-> " .$insid);
				if($userflageu){
                                    // insert into  user profile db1
                                    $dataup = array(
				  	'userid'=> $insid,
					'firstname'=> $name,
					'lang'=> 'english',
					'mobile'=>$mobile,
					'status'=>1
                                    );
				    $userflagup=$this->logmodel->insertrec('userprofile', $dataup) ;
                                    if($userflagup){
				    	// insert into user role type db
					$dataurt = array(
				           'userid'=> $insid,
				           'roleid'=> $role,
				            'deptid'=> $dept,
				            'scid'=> $sc,
                                            'usertype'=>'Faculty'
        	    			);
					$userflagurt=$this->commodel->insertrec('user_role_type', $dataurt) ;
					if($userflagurt){
                                            $sub='Teacher Registration' ;
                                            $mess="You are registered as a teacher.  Your user id ".$email ." and password is ".$passwd ;
                                            $mails = $this->mailmodel->mailsnd($email, $sub, $mess);
					    //  mail flag check 			
					    if($mails){
                                	            $error[] ="At row".$i."sufficient data and mail sent sucessfully";
                        	                    $this->logger->write_logmessage("insert"," add teacher edrpuser,profile and user role type ", "record added successfully for.".$name ." ".$email );
						    $this->logger->write_dblogmessage("insert"," add teacher edrpuser,profile and user role type ", "record added successfully for.".$name ." ".$email );
					    }
					    else{
        	                                    $error[] ="At row".$i."sufficient data and mail does sent";
	                                            $this->logger->write_logmessage("insert"," add teacher edrpuser,profile and user role type ", "record added successfully for.".$name ." ".$email ." and mail does sent");
						    $this->logger->write_dblogmessage("insert"," add teacher edrpuser,profile and user role type ", "record added successfully for.".$name ." ".$email." and mail does sent" );
					    }
					}else{
                                            //set the message for error in entering data in user role type
                                            $this->logger->write_logmessage("insert"," Error in adding teacher edrpuser,profile and user role type ", " data insert error . ".$name ." ".$email );
                                            $this->logger->write_dblogmessage("insert"," Error in adding teacher edrpuser,profile and user role type ", " data insert error . ".$name ." ".$email);
                                            // delete edrp user data
                                            $result = $this->logmodel->deleterow('edrpuser','id',$insid);
                                            // delete user profile data
                                            $result = $this->logmodel->deleterow('userprofile','userid',$insid);
					}
                                    }else{
					//set the message for error in entering data in user profile table
					$this->logger->write_logmessage("insert"," Error in adding teacher edrpuser,profile ", " data insert error . ".$name ." ".$email  );
                                	$this->logger->write_dblogmessage("insert"," Error in adding teacher edrpuser,profile ", " data insert error . ".$name ." ".$email );
					// delete edrp user data
					$result = $this->logmodel->deleterow('edrpuser','id',$insid);
				    }
				}else{
                                    // set the message for error in entering data in edrp table
                                    $this->logger->write_logmessage("insert"," Error in adding teacher edrpuser ", " data insert error . ".$name ." ".$email  );
                                    $this->logger->write_dblogmessage("insert"," Error in adding teacher edrpuser ", "data insert error . ".$name ." ".$email );
				}
			    	// $this->session->set_flashdata('success', ' sufficient data');	
                            }//close for is dup
                            else{
				$error[] ="At row".$i."duplicate data";
				$this->logger->write_logmessage("insert"," Error in adding teacher edrpuser ", "At row".$i."duplicate data"  );
	                        $this->logger->write_dblogmessage("insert"," Error in adding teacher edrpuser ", "At row".$i."duplicate data" );
			    }
                            $i++;
			}
			else{
                            //	insufficient data
                            $error[] ="At row".$i."insufficient data";
                            $this->logger->write_logmessage("insert"," Error in adding teacher edrpuser ", "At row".$i."insufficient data"  );
                            $this->logger->write_dblogmessage("insert"," Error in adding teacher edrpuser ", "At row".$i."insufficient data" );
                            //	$this->session->set_flashdata('error', ' insufficient data');
			    $i++;

			}
                    }
                    if($flag){
			$this->session->set_flashdata('error', ' File without data');
			$this->load->view('upl/uploadteacherlist');
		        return;
		    }else{
                        //					print_r($error);
			foreach ($error as $item => $value):
			    $ferror = $ferror ."</br>". $item .":". $value;
                        endforeach;
			//display error of array
			//put ferror in log file.
			$this->session->set_flashdata('success', $ferror);	
	                $this->load->view('upl/uploadteacherlist');
			return;
                    }
		}
            }//userfile checks
            else
            {
                //		$error = array( 'error' => $this->upload->display_errors());
		foreach ($error as $item => $value):
        	    $ferror = $ferror ."</br>".$item .":". $value;
                endforeach;
                $this->session->set_flashdata('error', $ferror);
                $this->load->view('upl/uploadteacherlist');
                return;
            }
        }// check for pressing correct button
        $this->load->view('upl/uploadteacherlist');
    }

 // upload staff list
    public function uploadslist(){
        // for clearing the previous sucess/error flashdata
        $array_items = array('success' => '', 'error' => '', 'warning' =>'');
        $this->session->set_flashdata($array_items);
        $error =array();
        if(isset($_POST['uploadslist'])) {
            $ferror='';
            if ( isset($_FILES["userfile"]))
            {
                $errors= array();
                $file_name = $_FILES['userfile']['name'];
                //      $file_size = $_FILES['userfile']['size'];
                //      $file_tmp = $_FILES['userfile']['tmp_name'];
                //      $file_type = $_FILES['userfile']['type'];
                $file_ext=strtolower(end((explode('.',$file_name))));

                $expensions= array("txt","csv");

                if(in_array($file_ext,$expensions)=== false){
                    $ferror="extension not allowed, please choose a txt or csv file.";
                    $this->session->set_flashdata('error', $ferror);
                    $this->load->view('upl/uploadstafflist');
                    return;
                }

                //      if($file_size > 2097152) {
                //              $errors[]='File size must be excately 2 MB';
                //      }

                //      if ($_FILES["userfile"]["error"] > 0) {
                //              echo "Return Code: " . $_FILES["userfile"]["error"] . "<br />";
                //      }
                else {
                    $flag=true;
                    $datal = array();
                    $uploadedfile = $_FILES['userfile']['tmp_name'];
                    $h = fopen($uploadedfile,"r");
                    $i=1;
                    while (false !== ($line = fgets($h)))
                    {
                        $datal = explode(",", $line);
                        $flag=false;
        //              print_r($datal);
                        if (count($datal) >= 5){
                            $name = $datal[0];
                            $email = $datal[1];
                            $dept = $datal[2];
                            $role = $datal[3];
                            $sc = $datal[4];
                            if (count($datal) > 5)
                                $mobile = $datal[5];
                             else
                            $mobile='';

                            // check for duplicate
                            $isdup= $this->logmodel->isduplicate('edrpuser','username',$email );
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
                                $userflageu=$this->logmodel->insertrec('edrpuser', $dataeu);
                                //get the insert id of edrp user
                                $insertid= $this->logmodel->get_listspfic1('edrpuser','id','username',$email );
                                $insid=$insertid->id;
                                //print_r("this is testing upload file===".$userflageu."and id-> " .$insid);
                                if($userflageu){
                                    // insert into  user profile db1
                                    $dataup = array(
                                        'userid'=> $insid,
                                        'firstname'=> $name,
                                        'lang'=> 'english',
                                        'mobile'=>$mobile,
                                        'status'=>1
                                    );
                                    $userflagup=$this->logmodel->insertrec('userprofile', $dataup) ;
                                    if($userflagup){
                                        // insert into user role type db
                                        $dataurt = array(
                                           'userid'=> $insid,
                                           'roleid'=> $role,
                                            'deptid'=> $dept,
                                            'scid'=> $sc,
                                            'usertype'=>'Staff'
                                        );
                                        $userflagurt=$this->commodel->insertrec('user_role_type', $dataurt) ;
                                        if($userflagurt){
                                            $sub='Staff  Registration' ;
                                            $mess="You are registered as a staff. Your user id ".$email ." and password is ".$passwd ;
                                            $mails = $this->mailmodel->mailsnd($email, $sub, $mess);
                                            //  mail flag check                         
                                            if($mails){
                                                       $error[] ="At row".$i."sufficient data and mail sent sucessfully";
                                                    $this->logger->write_logmessage("insert"," add staff edrpuser,profile and user role type ", "record added successfully for.".$name ." ".$email );
                                                    $this->logger->write_dblogmessage("insert"," add staff edrpuser,profile and user role type ", "record added successfully for.".$name ." ".$email );
                                            }
                                            else{
                                                    $error[] ="At row".$i."sufficient data and mail does sent";
                                                    $this->logger->write_logmessage("insert"," add staff edrpuser,profile and user role type ", "record added successfully for.".$name ." ".$email ." and mail does sent");
                                                    $this->logger->write_dblogmessage("insert"," add staff edrpuser,profile and user role type ", "record added successfully for.".$name ." ".$email." and mail does sent" );
                                            }
                                        }else{
                                            //set the message for error in entering data in user role type
                                            $this->logger->write_logmessage("insert"," Error in adding staff edrpuser,profile and user role type ", " data insert error . ".$name ." ".$email );
                                            $this->logger->write_dblogmessage("insert"," Error in adding staff edrpuser,profile and user role type ", " data insert error . ".$name ." ".$email);
                                            // delete edrp user data
                                            $result = $this->logmodel->deleterow('edrpuser','id',$insid);
                                            // delete user profile data
                                            $result = $this->logmodel->deleterow('userprofile','userid',$insid);
                                        }
                                    }else{
                                        //set the message for error in entering data in user profile table
                                        $this->logger->write_logmessage("insert"," Error in adding staff edrpuser,profile ", " data insert error . ".$name ." ".$email  );
                                        $this->logger->write_dblogmessage("insert"," Error in adding staff edrpuser,profile ", " data insert error . ".$name ." ".$email );
                                        // delete edrp user data
                                        $result = $this->logmodel->deleterow('edrpuser','id',$insid);
                                    }
                                }else{
                                    // set the message for error in entering data in edrp table
                                    $this->logger->write_logmessage("insert"," Error in adding staff edrpuser ", " data insert error . ".$name ." ".$email  );
                                    $this->logger->write_dblogmessage("insert"," Error in adding staff edrpuser ", "data insert error . ".$name ." ".$email );
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
                        //                                      print_r($error);
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
            else
            {
                //$error = array( 'error' => $this->upload->display_errors());
                foreach ($error as $item => $value):
                    $ferror = $ferror ."</br>".$item .":". $value;
                endforeach;
                $this->session->set_flashdata('error', $ferror);
                $this->load->view('upl/uploadstafflist');
                return;
            }
        }// check for pressing correct button
        $this->load->view('upl/uploadstafflist');
    }
}
