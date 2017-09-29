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
                                            $mess="You are registered as a teacher. Your user id ".$email ." and password is ".$passwd ;
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
