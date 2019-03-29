
<?php

/* 
 * @name Upl2.php
 * @author Manorama Pal(palseema30@gmail.com) upload HOD list in csv format .
 */
 
defined('BASEPATH') OR exit('No direct script access allowed');


class Upl2 extends CI_Controller
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
        
       // $this->uploadlogo();
        
    }
    
    /*************************upload hod list csv file**********************************************/
    public function uploadhodlist(){
        // for clearing the success/error flashdata
        $array_items = array('success' => '', 'error' => '', 'warning' =>'');
        $this->session->set_flashdata($array_items);
        $error =array();
        if(isset($_POST['uplhodlist'])) {
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
                else{ 
                   
                    $flag=true;
                    $datal = array();
                    $uploadedfile = $_FILES['userfile']['tmp_name'];
                    $h = fopen($uploadedfile,"r");
                    fgetcsv($h); 		
                    $i=1;
                    while (false !== ($line = fgets($h)))
                    {
			$userid='';
                        
                        $datal = explode(",", $line);
                        $flag=false;
                        if (count($datal) >= 4){
                            $email= trim($datal[0]);
                            $pfno= trim($datal[1]);
                            $campus = trim($datal[2]);
                            $dept = trim($datal[3]);
                            $uo = trim($datal[4]);
                            $dfrom = trim($datal[5]);
                            $dto = trim($datal[6]);
                            $status ='Fulltime';
                            
                            
                            $campusid=$this->commodel->get_listspfic1('study_center','sc_id','sc_code',$campus)->sc_id;
                            $deptid=$this->commodel->get_listspfic1('Department','dept_id','dept_code',$dept)->dept_id;
                            $uoid=$this->lgnmodel->get_listspfic1('authorities','priority','code',$uo)->priority;
                            
                            if((!empty($campusid)) && (!empty($deptid))){
                             
                                $isdup= $this->lgnmodel->isduplicate('edrpuser','username',$email);
				$parts = explode("@", $email);
				$ename = $parts[0];	
                                $passwd=md5($ename);
                                if(!$isdup){
                                    
                                    $dataeu = array(
                                        'username'=> $email,
                                        'password'=> $passwd,
                                        'email'=> $email,
                                        'componentreg'=> '*',
                                        'mobile'=>'',
                                        'status'=>1,
                                        'category_type'=>'HOD',
                                        'is_verified'=>1
                                    );
                                    /*insert record in edrpuser table*/
                                    $userflageu=$this->lgnmodel->insertrec('edrpuser', $dataeu);
                                    $userid=$this->lgnmodel->get_listspfic1('edrpuser','id','username',$email)->id;
                                    if($userflageu){
                                        
                                       // insert into  user profile db1
                                        $dataup = array(
                                           'userid' => $userid,
                                           'firstname' => 'Head of the Department',
                                            'lang' => 'english',
                                            'mobile' => '',
                                            'status' => 1
                                        );
                                        $userflagup=$this->lgnmodel->insertrec('userprofile', $dataup);
//add closer if user exist
					}//ifuserflageu
				}//ifuserdupeu
				else{
					$userid=$this->lgnmodel->get_listspfic1('edrpuser','id','username',$email)->id;
				}
                                        // check for duplicate
                                        $duphod = array('hl_userid' => $userid, 'hl_scid' => $campusid,'hl_deptid'=> $deptid);
                                        $isduphod= $this->sismodel->isduplicatemore('hod_list',$duphod);
                                        if(!$isduphod){
                                            
                                            $usr =$this->session->userdata('username');
                                            $datahod = array(
                                                'hl_userid'=> $userid,
                                                'hl_empcode'=> $pfno,
                                                'hl_deptid'=> $deptid,
                                                'hl_scid'=> $campusid,
						'hl_uopid' => $uoid,
                                                'hl_datefrom'=> $dfrom,
                                                'hl_dateto'=> $dto,
                                                'hl_status'=> $status,
                                                'hl_creatorid'=> $usr,
                                                'hl_creatordate'=> date('y-m-d'),
                                                'hl_modifierid'=> $usr,
                                                'hl_modifydate'=> date('y-m-d'),
                                            );
                                            $hodlistflag=$this->sismodel->insertrec('hod_list', $datahod) ;
                                            if($hodlistflag){
                                                
                                                /* insert into user_role_type */
                                                $dataurt = array(
                                                    'userid'=> $userid,
                                                    'roleid'=> 5,
                                                    'deptid'=> $deptid,
                                                    'scid'=>  $campusid,
                                                    'usertype'=>'HoD'
                                                );
                                                $userflagurt=$this->sismodel->insertrec('user_role_type', $dataurt) ;
                                                if($userflagurt){
                                                    
                                                    $sub=' Registred as a HOD' ;
                                                    $mess="You are registered as a Hod. Your user id ".$email ." and password is ".$passwd ;
                                                    //$mails = $this->mailmodel->mailsnd($email, $sub, $mess,'','Sis');
                                                    $mails = '';
                                                    //$this->mailmodel->mailsnd('$_POST['emailid']', $sub, $mess,'','Sis');
                                                    //  mail flag check                         
                                                    if($mails){
                                                       
                                                        $error[] ="At row ". $i ." sufficient data and mail sent sucessfully";
                                                        $this->logger->write_logmessage("insert"," add hod edrpuser, userprofile,hod_list and user_role_type ", "record added successfully for.".$pfno ." ".$email );
                                                        $this->logger->write_dblogmessage("insert"," add staff edrpuser, userprofile, hod_list and user_role_type ", "record added successfully for.".$pfno ." ".$email );
                                                        $i++;
                                                    }
                                                    else{
                                                        
                                                        $error[] ="At row ". $i ." sufficient data and mail does not sent";
                                                        $this->logger->write_logmessage("insert"," add hod edrpuser,userprofile, hod_list and user role type ", "record added successfully for.".$pfno ." ".$email ." and mail does not sent");
                                                        $this->logger->write_dblogmessage("insert"," add hod edrpuser,userprofile, hod_list and user role type ", "record added successfully for.".$pfno ." ".$email." and mail does not sent" );
                                                        $i++;
                                                    }
                                                }//$userflagurt if
                                                else{
                                                    
                                                    //set the message for error in entering data in user role type
                                                    $this->logger->write_logmessage("insert"," Error in adding hod edrpuser,userprofile and user role type ", " data insert error . ".$pfno ." ".$email );
                                                    $this->logger->write_dblogmessage("insert"," Error in adding hod edrpuser,userprofile and user role type ", " data insert error . ".$pfno ." ".$email);
                                                    // delete edrp user data
                                                    $result = $this->lgnmodel->deleterow('edrpuser','id',$userid);
                                                    // delete user profile data
                                                    $result = $this->lgnmodel->deleterow('userprofile','userid',$userid);
                                                    $i++;
                                                }
                                            }//$hodlistflag
                                            else{
                                                
                                                //set the message for error in entering data in user profile table
                                                $this->logger->write_logmessage("insert"," Error in adding hod edrpuser,userprofile ", " data insert error . ".$pfno ." ".$email  );
                                                $this->logger->write_dblogmessage("insert"," Error in adding hod edrpuser,userprofile ", " data insert error . ".$pfno ." ".$email );
                                                // delete edrp user data
                                                $result = $this->lgnmodel->deleterow('edrpuser','id',$userid);
                                                $i++;
                                            }
                                                       // $this->session->set_flashdata('success', ' Hod list csv file uploaded successfully.');
                                        }// if $duphod
                                        else{
                                           
                                            //duplicate data
                                            $error[] ="At row".$i."duplicate data";
                                            $this->logger->write_logmessage("insert"," Error in Hod list csv file ", "At row".$i."duplicate data"  );
                                            $this->logger->write_dblogmessage("insert"," Error in Hod list csv file ", "At row".$i."duplicate data" );
                                             $i++;
                                        }//else duplicate check
                                       
//                                    }//ifuserflageu
  //                                  else{
                                        
                                        // set the message for error in entering data in edrp table
    //                                    $this->logger->write_logmessage("insert"," Error in adding staff edrpuser ", " data insert error . ".$pfno ." ".$email  );
      //                                  $this->logger->write_dblogmessage("insert"," Error in adding staff edrpuser ", "data insert error . ".$pfno ." ".$email );
        //                                $i++;
          //                          }
                                      
                               // }//close if is dupeu
                          //      else{
                                    
                            //        $error[] ="At row ". $i ." duplicate data";
                              //      $this->logger->write_logmessage("insert"," Error in adding staff edrpuser ", "At row".$i."duplicate data"  );
                                //    $this->logger->write_dblogmessage("insert"," Error in adding staff edrpuser ", "At row".$i."duplicate data" );
                                  //  $i++;
                               // } 
                            }// if emptychecks  
                            else{
                                
                                $error[] ="At row " .$i. " some fields are empty.";
                                $this->logger->write_logmessage("insert","2 Error in adding staff profile  ", "At row".$i."some fields are empty campusid=>".$campusid."deptid==>".$deptid );
                                $this->logger->write_dblogmessage("insert"," Error in adding staff profile ", "At row".$i."some fields are empty campusid=>".$campusid."deptid==>".$deptid);
                                $i++;
                            }
                        }//ifcount closer
                        else{
                            
                            //insufficient data
                            $error[] ="At row".$i."insufficient data";
			    $this->logger->write_logmessage("insert"," Error in Hod list csv file ", "At row".$i."insufficient data"  );
                            $this->logger->write_dblogmessage("insert"," Error in Hod list csv file ", "At row".$i."insufficient data" );
                            $i++;
                        }
                    }//while  closer 
                   // fclose($h);	
		    if($flag){
                        
		      	$this->session->set_flashdata('error', ' File without data');
			$this->load->view('upl/uploadhodlist');
                	return;
                    }else{
                        
                    	//display error of array
                    	foreach ($error as $item => $value):
                    	$ferror = $ferror ."</br>". $item .":". $value;
                    	endforeach;
                    	$this->session->set_flashdata('success', $ferror);
			$this->load->view('upl/uploadhodlist');
                	return;
                    }//else
			   
                }//correct extesion file else condition    
            }//if userfile
        }//buttonif condition
       
        $this->load->view('upl/uploadhodlist');
    }
    /*************************upload hod list csv file**********************************************/
    /*************************upload uo list csv file**********************************************/
    public function uploaduolist(){
        // for clearing the success/error flashdata
        $array_items = array('success' => '', 'error' => '', 'warning' =>'');
        $this->session->set_flashdata($array_items);
        $error =array();
        if(isset($_POST['upluolist'])) {
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
                    $this->load->view('upl/uploaduolist');
                    return;
                }
                else{ 
                   
                    $flag=true;
                    $datal = array();
                    $uploadedfile = $_FILES['userfile']['tmp_name'];
                    $h = fopen($uploadedfile,"r");
                    fgetcsv($h); 		
                    $i=1;
                    while (false !== ($line = fgets($h)))
                    {
			$userid='';
                        
                        $datal = explode(",", $line);
                        $flag=false;
                        if (count($datal) >= 4){
                            $email= trim($datal[0]);
                            $pfno= trim($datal[1]);
                            $uocode = trim($datal[2]);
                            $uoname = trim($datal[3]);
                            $dfrom = trim($datal[4]);
                            $dto = trim($datal[5]);
                            $status ='Fulltime';
                            
                            
                         //   $campusid=$this->commodel->get_listspfic1('study_center','sc_id','sc_code',$campus)->sc_id;
                         //   $deptid=$this->commodel->get_listspfic1('Department','dept_id','dept_code',$dept)->dept_id;
                            
                            if((!empty($uocode)) && (!empty($uoname))){
                             
                                $isdup= $this->lgnmodel->isduplicate('edrpuser','username',$email);
				$parts = explode("@", $email);
				$ename = $parts[0];	
                                $passwd=md5($ename);
                                if(!$isdup){
                                    
                                    $dataeu = array(
                                        'username'=> $email,
                                        'password'=> $passwd,
                                        'email'=> $email,
                                        'componentreg'=> '*',
                                        'mobile'=>'',
                                        'status'=>1,
                                        'category_type'=>'UO',
                                        'is_verified'=>1
                                    );
                                    /*insert record in edrpuser table*/
                                    $userflageu=$this->lgnmodel->insertrec('edrpuser', $dataeu);
                                    $userid=$this->lgnmodel->get_listspfic1('edrpuser','id','username',$email)->id;
                                    if($userflageu){
                                        
                                       // insert into  user profile db1
                                        $dataup = array(
                                           'userid' => $userid,
                                           'firstname' => 'University Officer',
                                            'lang' => 'english',
                                            'mobile' => '',
                                            'status' => 1
                                        );
                                        $userflagup=$this->lgnmodel->insertrec('userprofile', $dataup);
//add closer if user exist
					}//ifuserflageu
				}//ifuserdupeu
				else{
					$userid=$this->lgnmodel->get_listspfic1('edrpuser','id','username',$email)->id;
				}
                                        // check for duplicate
                                        $dupuo = array('ul_userid' => $userid, 'ul_uocode' => $uocode,'ul_uoname'=> $uoname);
                                        $isdupuo= $this->sismodel->isduplicatemore('uo_list',$dupuo);
                                        if(!$isdupuo){
					    $auoid=null;
                                            $auoid = $this->lgnmodel->get_listspfic1('authorities','id','code',$uocode)->id;
                                            $usr =$this->session->userdata('username');
                                            $datauo = array(
                                                'ul_userid'=> $userid,
                                                'ul_empcode'=> $pfno,
						'ul_authuoid' => $auoid,
                                                'ul_uocode'=> $uocode,
                                                'ul_uoname'=> $uoname,
                                                'ul_datefrom'=> $dfrom,
                                                'ul_dateto'=> $dto,
                                                'ul_status'=> $status,
                                                'ul_creatorid'=> $usr,
                                                'ul_creatordate'=> date('y-m-d'),
                                                'ul_modifierid'=> $usr,
                                                'ul_modifydate'=> date('y-m-d'),
                                            );
                                            $uolistflag=$this->sismodel->insertrec('uo_list', $datauo) ;
                                            if($uolistflag){
                                                
                                                /* insert into user_role_type */
                                                $dataurt = array(
                                                    'userid'=> $userid,
                                                    'roleid'=> 10,
                                                    'deptid'=> NULL,
                                                    'scid'=>  NULL,
                                                    'usertype'=>'UO'
                                                );
                                                $userflagurt=$this->sismodel->insertrec('user_role_type', $dataurt) ;
                                                if($userflagurt){
                                                    
                                                    $sub=' Registred as a UO' ;
                                                    $mess="You are registered as a Hod. Your user id ".$email ." and password is ".$passwd ;
                                                    //$mails = $this->mailmodel->mailsnd($email, $sub, $mess,'','Sis');
                                                    $mails = '';
                                                    //$this->mailmodel->mailsnd('$_POST['emailid']', $sub, $mess,'','Sis');
                                                    //  mail flag check                         
                                                    if($mails){
                                                       
                                                        $error[] ="At row ". $i ." sufficient data and mail sent sucessfully";
                                                        $this->logger->write_logmessage("insert"," add uo edrpuser, userprofile,uo_list and user_role_type ", "record added successfully for.".$pfno ." ".$email );
                                                        $this->logger->write_dblogmessage("insert"," add uo staff edrpuser, userprofile, uo_list and user_role_type ", "record added successfully for.".$pfno ." ".$email );
                                                        $i++;
                                                    }
                                                    else{
                                                        
                                                        $error[] ="At row ". $i ." sufficient data and mail does not sent";
                                                        $this->logger->write_logmessage("insert"," add uo edrpuser,userprofile, uo_list and user role type ", "record added successfully for.".$pfno ." ".$email ." and mail does not sent");
                                                        $this->logger->write_dblogmessage("insert"," add uo edrpuser,userprofile, uo_list and user role type ", "record added successfully for.".$pfno ." ".$email." and mail does not sent" );
                                                        $i++;
                                                    }
                                                }//$userflagurt if
                                                else{
                                                    
                                                    //set the message for error in entering data in user role type
                                                    $this->logger->write_logmessage("insert"," Error in adding uo edrpuser,userprofile and user role type ", " data insert error . ".$pfno ." ".$email );
                                                    $this->logger->write_dblogmessage("insert"," Error in adding uo edrpuser,userprofile and user role type ", " data insert error . ".$pfno ." ".$email);
                                                    // delete edrp user data
                                                    $result = $this->lgnmodel->deleterow('edrpuser','id',$userid);
                                                    // delete user profile data
                                                    $result = $this->lgnmodel->deleterow('userprofile','userid',$userid);
                                                    $i++;
                                                }
                                            }//$hodlistflag
                                            else{
                                                
                                                //set the message for error in entering data in user profile table
                                                $this->logger->write_logmessage("insert"," Error in adding uo edrpuser,userprofile ", " data insert error . ".$pfno ." ".$email  );
                                                $this->logger->write_dblogmessage("insert"," Error in adding uo edrpuser,userprofile ", " data insert error . ".$pfno ." ".$email );
                                                // delete edrp user data
                                                $result = $this->lgnmodel->deleterow('edrpuser','id',$userid);
                                                $i++;
                                            }
                                                       // $this->session->set_flashdata('success', ' Hod list csv file uploaded successfully.');
                                        }// if $duphod
                                        else{
                                           
                                            //duplicate data
                                            $error[] ="At row".$i."duplicate data";
                                            $this->logger->write_logmessage("insert"," Error in uo list csv file ", "At row".$i."duplicate data"  );
                                            $this->logger->write_dblogmessage("insert"," Error in uo list csv file ", "At row".$i."duplicate data" );
                                             $i++;
                                        }//else duplicate check
                                       
//                                    }//ifuserflageu
  //                                  else{
                                        
                                        // set the message for error in entering data in edrp table
    //                                    $this->logger->write_logmessage("insert"," Error in adding staff edrpuser ", " data insert error . ".$pfno ." ".$email  );
      //                                  $this->logger->write_dblogmessage("insert"," Error in adding staff edrpuser ", "data insert error . ".$pfno ." ".$email );
        //                                $i++;
          //                          }
                                      
                               // }//close if is dupeu
                          //      else{
                                    
                            //        $error[] ="At row ". $i ." duplicate data";
                              //      $this->logger->write_logmessage("insert"," Error in adding staff edrpuser ", "At row".$i."duplicate data"  );
                                //    $this->logger->write_dblogmessage("insert"," Error in adding staff edrpuser ", "At row".$i."duplicate data" );
                                  //  $i++;
                               // } 
                            }// if emptychecks  
                            else{
                                
                                $error[] ="At row " .$i. " some fields are empty.";
                                $this->logger->write_logmessage("insert","2 Error in adding staff profile  ", "At row".$i."some fields are empty uocode=>".$uocode."uo name==>".$uoname );
                                $this->logger->write_dblogmessage("insert"," Error in adding staff profile ", "At row".$i."some fields are empty uocode=>".$uocode."uo name==>".$uoname);
                                $i++;
                            }
                        }//ifcount closer
                        else{
                            
                            //insufficient data
                            $error[] ="At row".$i."insufficient data";
			    $this->logger->write_logmessage("insert"," Error in uo list csv file ", "At row".$i."insufficient data"  );
                            $this->logger->write_dblogmessage("insert"," Error in uo list csv file ", "At row".$i."insufficient data" );
                            $i++;
                        }
                    }//while  closer 
                   // fclose($h);	
		    if($flag){
                        
		      	$this->session->set_flashdata('error', ' File without data');
			$this->load->view('upl/uploaduolist');
                	return;
                    }else{
                        
                    	//display error of array
                    	foreach ($error as $item => $value):
                    	$ferror = $ferror ."</br>". $item .":". $value;
                    	endforeach;
                    	$this->session->set_flashdata('success', $ferror);
			$this->load->view('upl/uploaduolist');
                	return;
                    }//else
			   
                }//correct extesion file else condition    
            }//if userfile
        }//buttonif condition
       
        $this->load->view('upl/uploaduolist');
    }
    /*************************upload uo list csv file**********************************************/

}  

