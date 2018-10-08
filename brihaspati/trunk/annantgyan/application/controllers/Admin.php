<?php
/******************************************************
* @name Admin.php(controller)    		      *
* @author Nagendra Kumar Singh(nksinghiitk@gmail.com) *
* @author Sumit Saxena(sumitsesaxena@gmail.com)       *
*******************************************************/

defined('BASEPATH') OR exit('No direct script access allowed');

class Admin extends CI_Controller {
	public function __construct(){
		parent::__construct();
		
		$this->load->model('Common_model',"commodel");
		$this->load->model("Mailsend_model","mailmodel");
		$this->load->library('form_validation');
		$this->load->library('session');
		$this->load->helper('cookie');
		$this->load->helper('string');
		$this->load->helper(array('url','form'));

		$this->load->library('email');
		
	}

	public function index()
	{
		
		$this->load->view('admin/admin_login');
	}


	public function admin_login() {		
		if($_POST) {			
			$this->form_validation->set_rules('ad_email', 'Email-Id', 'required');
			$this->form_validation->set_rules('ad_pwd', 'Password', 'required');				
			if ($this->form_validation->run() == FALSE) {	
					
			}else{	
				$result = $this->commodel->validate_adminuser($_POST);
				
		            	if(!empty($result)){
  					$wharray=array('userEmail' => $result->userEmail,'verification' => $result->verification, 'id' => $result->id);
  				}
				if(!empty($wharray)){
					$sdata = 'userEmail,firstName,lastName,verification,id';
					$udata=$this->commodel->get_listspficemore('admin',$sdata,$wharray);
				}				
				if(!empty($udata)){						
					foreach($udata as $row){
						$verify = $row->verification;
						$emailid = $this->input->post('ad_email');
						$pwd = $this->input->post('ad_pwd');
						if($verify == 'Verified'){
							
							$whdata=array('userEmail'=>$emailid,'password'=>$pwd);
							$crsexist = $this->commodel->isduplicatemore("admin",$whdata);
							if($crsexist == 1){
								
								$sdata = ['id' => $row->id,'su_id'=>$row->id,'userEmail' => $row->userEmail,'firstName' => $row->firstName,'su_name'=>$row->firstName, 'lastName' => $row->lastName];
								$this->session->set_userdata($sdata);

								redirect('admin/adminhome');
								$confmes = "Log-in Successfully !!";
       							$this->session->set_flashdata('success',$confmes);
							}else{
								redirect('admin');
								$confmes = "Username & password is wrong !!";
       							$this->session->set_flashdata('success',$confmes);
							}	
							
						}else {
							$this->session->set_flashdata("error",'Your email id is not verified.');						
						}	
					}//close foreach
				}else{				
						$this->session->set_flashdata("error",'Your email or password wrong.');					
				}				
			}//close validtion
	    }//close post
		$this->load->view('admin/admin_login');
	}
	function enquirylist(){
                if(isset($this->session->userdata['firstName'])){
                        $data['enquirydata'] = $this->commodel->get_orderlistspficemore('enquiry','*','','');
                        $this->load->view('admin/enquirylist',$data);
                }else{
                        $this->load->view('admin/admin_login');
                }
        }

	function adminhome(){
		if(isset($this->session->userdata['firstName'])){
			$data['userdata'] = $this->commodel->get_list('sign_up');
			$this->load->view('admin/admin_home',$data);
		}else{
			$this->load->view('admin/admin_login');
		}
	}

	function courselist(){
		if(isset($this->session->userdata['firstName'])){
			$data['userdata'] = $this->commodel->get_orderlistspficemore('courses','*','','');
			$this->load->view('admin/courselist',$data);
		}else{
			$this->load->view('admin/admin_login');
		}
	}

	function referallist(){
		if(isset($this->session->userdata['firstName'])){
			$data['userdata'] = $this->commodel->get_orderlistspficemore('referrel','*','','');
			$this->load->view('admin/referallist',$data);
		}else{
			$this->load->view('admin/admin_login');
		}
	}

	function couworkshoplist(){
		if(isset($this->session->userdata['firstName'])){
			$data['userdata'] = $this->commodel->get_orderlistspficemore('ongoingworkshop','*','','');
			$this->load->view('admin/admin_couworkshoplist',$data);
		}else{
			$this->load->view('admin/admin_login');
		}
	}
	
	function courseannouncement(){
		if(isset($this->session->userdata['firstName'])){
			$data['userdata'] = $this->commodel->get_orderlistspficemore('courseannouncement','*','','');
			$this->load->view('admin/courseannouncement',$data);
		}else{
			$this->load->view('admin/admin_login');
		}
	}

	function mapcoursecontent(){
		if(isset($this->session->userdata['firstName'])){
			$data['userdata'] = $this->commodel->get_orderlistspficemore('mapcontentmaterial','*','','');
			$this->load->view('admin/mapcoursecontent',$data);
		}else{
			$this->load->view('admin/admin_login');
		}
	}

	function  admin_addcourse(){
		if(isset($this->session->userdata['firstName'])){
		if(isset($_POST['submit'])){
			$this->form_validation->set_rules('cname','Course Name','trim|required|xss_clean');
       		$this->form_validation->set_rules('ccode','Course Code','trim|required|xss_clean');
            $this->form_validation->set_rules('cdesc','Course Description','trim|xss_clean');
            $this->form_validation->set_rules('celig','Course Eligibility','trim|xss_clean');
            $this->form_validation->set_rules('cfees','Course Fees','trim|xss_clean|numeric|required');
			
            if($this->form_validation->run() == FALSE){
            	redirect('admin/admin_addcourse');
            }else{

				$cname 		= $this->input->post('cname');
				$ccode 	    = $this->input->post('ccode');
				$cdesc 		= $this->input->post('cdesc');
				$celig      = $this->input->post('celig');
				$cfees 	    = $this->input->post('cfees');
				
				$insdata = array(
						    'cou_name'		=> $cname,
            				'cou_code' 		=> $ccode,  
							'cou_discipline' 	=> $cdesc,         
            				'cou_eligible ' 	=> $celig,
            				'cou_fees' 		=> $cfees,          				
        			);
				//print_r($insdata);die();
        		$insflag = $this->db->insert('courses',$insdata);
        		if($insflag){
        			redirect("admin/courselist");
        		}
        		
			}//else close
		}					
		$this->load->view('admin/admin_addcourse');
		}else{
                        $this->load->view('admin/admin_login');
                }

	}

	function  admin_addannouncecourse(){
		if(isset($this->session->userdata['firstName'])){		
		if(isset($_POST['submit'])){
			$this->form_validation->set_rules('cname','Course Name','trim|required|xss_clean');
       		$this->form_validation->set_rules('cdur_week','Course Duration','trim|required|xss_clean');
            $this->form_validation->set_rules('crsdate','Course Registartion Start Date','trim|xss_clean');
            $this->form_validation->set_rules('credate','Course Registartion End Date','trim|xss_clean');
            $this->form_validation->set_rules('csdate','Course  Start Date','trim|xss_clean');
            $this->form_validation->set_rules('cedate','Course End Date','trim|xss_clean');
            $this->form_validation->set_rules('cfdate','Course Feedback Date','trim|xss_clean');
            $this->form_validation->set_rules('ccdate','Course Certificate Date','trim|xss_clean');
            
            if($this->form_validation->run() == FALSE){
            	redirect('admin/admin_addannouncecourse');
            }else{

				$cname 		= $this->input->post('cname');
				$cdur 	    = $this->input->post('cdur_week');
				$crsdate 		= $this->input->post('crsdate');
				$credate      = $this->input->post('credate');
				$csdate 	    = $this->input->post('csdate');
				$cedate 	    = $this->input->post('cedate');
				$cfdate 	    = $this->input->post('cfdate');
				$ccdate 	    = $this->input->post('ccdate');
				
				$insdata = array(
						    'crsann_crsid'		=> $cname,
            				'crsann_duration' 		=> $cdur,  
							'crsann_regstart' 	=> $crsdate,         
            				'crsann_regend' 	=> $credate,
            				'crsann_crsstart' 		=> $csdate, 
            				'crsann_crsend'       => $cedate,   				
            				'crsann_feedbkdate'=> $cfdate, 
            				'crsann_certdate'=> $ccdate, 
            				'crsann_creatorid'=> $this->session->userdata('userEmail'), 
            				'crsann_createdate'=> date('Y-m-d'), 
        			);
				//print_r($insdata);die();
        		$insflag = $this->db->insert('courseannouncement',$insdata);
        		if($insflag){
        			redirect("admin/courseannouncement");
        		}
        		
			}//else close
		}				
		$data['couname'] = $this->commodel->get_list('courses');
		$this->load->view('admin/admin_addannouncecourse',$data);
		}else{
                        $this->load->view('admin/admin_login');
                }

	}

	function admin_coucontent(){
		if(isset($this->session->userdata['firstName'])){
			$data['couname'] = $this->commodel->get_list('courses');
			$this->load->view('admin/admin_couupload',$data);
		}else{
                        $this->load->view('admin/admin_login');
                }

	}
	function delete_cont($id){
		$cpath = $this->commodel->get_listspfic1('admin_conteupload','acu_contpath','acu_id',$id)->acu_contpath;
		$fname = $this->commodel->get_listspfic1('admin_conteupload','acu_filename','acu_id',$id)->acu_filename;
		//$file = "uploads/my_test_file.txt";
		$file = APPPATH .'../'. $cpath."/".$fname;
//			echo $file; die();
		if (is_readable($file) && unlink($file)) {
		    	$smsg= "The file has been deleted.";
		} else {
    			$fmsg= "The file was not found or not readable and could not be deleted.";
		}
		$contflag=$this->commodel->deleterow('admin_conteupload','acu_id', $id);
		if(!$contflag)
          	{
            		$this->logger->write_message("error", $fmsg." Error  in deleting content " ."[cont_id:" . $id . "]");
            		$this->logger->write_dbmessage("error", $fmsg." Error  in deleting content "." [cont_id:" . $id . "]");
            		$this->session->set_flashdata('err_message', $fmsg.' Error in Deleting content - ', 'error');
            		redirect('admin/upload_fileview');
           		return;
          	}
		else{
            		$this->logger->write_logmessage("delete", $smsg." Deleted   content " . "[cont_id:" . $id . "] deleted successfully.. " );
            		$this->logger->write_dblogmessage("delete",$smg. " Deleted content" ." [cont_id:" . $id . "] deleted successfully.. " );
            		$this->session->set_flashdata("success", $smsg.' Content Deleted successfully...' );
            		redirect('admin/upload_fileview');
        	}
        	$this->load->view('admin/upload_fileview',$data);
	}

	public function upload_file(){
//		if(isset($this->session->userdata['firstName'])){
	//echo "hello";die;	
		$data['couname'] = $this->commodel->get_list('courses');
		if(isset($_POST['cou_upload'])) {
			//echo "hello";die;	

			//$this->form_validation->set_rules('userfile', 'Upload Photo', 'trim|required|xss_clean');
			$this->form_validation->set_rules('cou_name', 'Upload Photo', 'trim|required|xss_clean');
			$this->form_validation->set_rules('cou_type', 'Upload Photo', 'trim|required|xss_clean');
			$this->form_validation->set_rules('cou_week', 'Upload Photo', 'trim|required|xss_clean');
			$this->form_validation->set_rules('cou_contname', 'Upload Photo', 'trim|required|xss_clean');
			$this->form_validation->set_rules('cou_seqno', 'Upload Photo', 'trim|required|xss_clean|numeric');
			
			if((isset($_FILES['userfile']))){
            	//echo "hello";die;	

            			$courseid = $this->input->post('cou_name');
				$type 	= $this->input->post('cou_type');
				$week 	= $this->input->post('cou_week');
				$content 	= $this->input->post('cou_contname');
				$seq_no 	= $this->input->post('cou_seqno');

                  //print_r($desired_dir);	die;	
	 	 		//upload photo in dir which is not accessble directly with size limit photo 200kb 
				$_FILES['userFile']['name'] = $_FILES['userfile']['name'];
               	 		$_FILES['userFile']['type'] = $_FILES['userfile']['type'];
                		$_FILES['userFile']['tmp_name'] = $_FILES['userfile']['tmp_name'];
                		//$_FILES['userFile']['error'] = $_FILES['userfile']['error'];
                		$_FILES['userFile']['size'] = $_FILES['userfile']['size'];
				
				if($_FILES['userfile']['size'] > 800000000) {
         				$errors[]='Photo size must be excately 4 MB';
      				}
				$name = str_replace(" ", "_", $_FILES['userfile']['name']);	
				//$name = $_FILES['userfile']['name'];
				
				$desired_dir = 'uploads/courses/';
                        	// Create directory if it does not exist
                        	if(is_dir($desired_dir)==false){
                              		mkdir("$desired_dir", 0700);
                        	}
                 		$desired_dir1 = 'uploads/courses/'.$courseid;
                        	// Create directory if it does not exist
                        	if(is_dir($desired_dir1)==false){
                              		mkdir("$desired_dir1", 0700);
                        	} 
                 		$desired_dir2 = 'uploads/courses/'.$courseid.'/'.$type;
                        	// Create directory if it does not exist
                        	if(is_dir($desired_dir2)==false){
                              		mkdir("$desired_dir2", 0700);
                        	}  	

               			$config['upload_path'] = $desired_dir2 ;
				
				if($type == 'document'){
					$config['max_size'] = '11000000';
              				$config['allowed_types'] = 'pdf|doc|docx|DOCX|DOC|xls|xlsx';
              			}
              			elseif($type == 'video'){
              				$config['remove_spaces'] = TRUE;
					$config['max_size'] = '800000000';
              				$config['allowed_types'] = 'mp4|3gp|mpeg|mpg|avi';
              			}
              		
				$config['file_name'] = $name;
				$config['overwrite'] = TRUE;
        	    					
               	 		$this->load->library('upload',$config);
               		 	$this->upload->initialize($config);
				
                		if($this->upload->do_upload('userfile')){
                   	 		$uploadData = $this->upload->data();
                   	 		$file = $uploadData['file_name'];
					
                		}else{
					if($type == 'document'){
                    				$file = '';
						$error =  array('err_message' => $this->upload->display_errors());
						//print_r($error);die;
						foreach ($error as $item => $value):
							$ferror = $ferror.$value;
						endforeach;
						$ferror=str_replace("\r\n","",$ferror);
						$simsg = "The permitted size of Document is 10 MB .";
						$ferror = $simsg.$ferror;
						$this->session->set_flashdata('error', $ferror);
						redirect('admin/admin_coucontent');
					}
					elseif($type == 'video'){
                    				$file = '';
						$error =  array('err_message' => $this->upload->display_errors());
						//print_r($error);die;
						foreach ($error as $item => $value):
							$ferror = $ferror.$value;
						endforeach;
						$ferror=str_replace("\r\n","",$ferror);
						$simsg = "The permitted size of Video File is 750 MB.";;
						$ferror = $simsg.$ferror;
						$this->session->set_flashdata('error', $ferror);
						redirect('admin/admin_coucontent');
					}
								
                		}
            		
				//Prepare array of user data
				$cdate = date('Y-m-d H:i:s');		
     	       			$userData = array(
        	        		 'acu_courseid'         	=>	$courseid,
					 'acu_weekname'         	=>	$week,
					 'acu_seqno'			=>  	$seq_no,
					 'acu_weekcontname' 		=>	$content,
					 'acu_contpath' 		=>  	$desired_dir2,
					 'acu_filetype'			=>	$type,
					 'acu_filename'			=>	$name,
					 'acu_createdate'		=>	$cdate,
					 'acu_creatorid'		=>	$this->session->userdata('userEmail'),
           	     		);
				
           			$insert = $this->db->insert('admin_conteupload',$userData);
				   	
				if($insert)
                	        {
                    			$this->session->set_flashdata('success', 'Your file successfully uploaded and data updated in databse.');
					redirect('admin/admin_coucontent');
                        	}
                    		else{
                        	 	$this->session->set_flashdata('error', 'Data is not updated in databse.');
					redirect('admin/admin_coucontent');
                        	}
            	
       			 	
	   		}
     	  	}
     		    $this->load->view('admin/admin_couupload',$data);
	}



	public function upload_fileview(){
		if(isset($this->session->userdata['firstName'])){			
			$data['cou_data'] = $this->commodel->get_list('admin_conteupload');
			$this->load->view('admin/admin_couuploadview',$data);
		}else{
                        $this->load->view('admin/admin_login');
                }

	}

	function createexam(){
                if(isset($this->session->userdata['firstName'])){
			$data['couname'] = $this->commodel->get_list('courses');
			if(isset($_POST['cou_exam'])){
				$this->form_validation->set_rules('cou_name', 'course name', 'trim|required|xss_clean');
				$this->form_validation->set_rules('cou_week', 'course week', 'trim|required|xss_clean');
				$this->form_validation->set_rules('test_name', 'test name', 'trim|required|xss_clean');
				$this->form_validation->set_rules('test_code', 'test code', 'trim|required|xss_clean');
				$this->form_validation->set_rules('test_desc', 'test description', 'trim|xss_clean');
				$this->form_validation->set_rules('test_date', 'test date', 'trim|required|xss_clean');
				$this->form_validation->set_rules('fmin', 'test from time', 'trim|required|xss_clean');
				$this->form_validation->set_rules('tmin', 'test time to', 'trim|required|xss_clean');
				$this->form_validation->set_rules('test_totalq', 'test total question', 'trim|required|xss_clean');
				if($this->form_validation->run() == FALSE){
					$this->load->view('admin/createexam',$data);
					return;
//                			redirect('admin/createexam');
				}else{
					$courseid          = $this->input->post('cou_name');
					$couweek           = $this->input->post('cou_week');
					$testname          = $this->input->post('test_name');
					$testcode          = $this->input->post('test_code');
					$testdesc          = $this->input->post('test_desc');
					$testdate          = $this->input->post('test_date');
					$testfromhrs          = $this->input->post('fmin');
					if($testfromhrs <= 9){
						$testfromhrs = "0".$testfromhrs;
					}
					$testfrommin          = $this->input->post('fsec');
					if($testfrommin <= 9){
                                                $testfrommin = "0".$testfrommin;
                                        }
					$testfrom          = $testfromhrs.":".$testfrommin.":00";
					$testtohrs            = $this->input->post('tmin');
					if($testtohrs <= 9){
                                                $testtohrs = "0".$testtohrs;
                                        }
					$testtomin            = $this->input->post('tsec');
					if($testtomin <= 9){
                                                $testtomin = "0".$testtomin;
                                        }
					$testto            = $testtohrs.":".$testtomin.":00";
					$testtotalq        = $this->input->post('test_totalq');

					$tdiff=$testto - $testfrom;
					$cdate = date('Y-m-d H:i:s');
					$userData = array(
						'testname' 		=>$testname,
						'testdesc' 		=>$testdesc,
						'testdate' 		=>$testdate,
						'testtime'		=>$tdiff,
						'subid' 		=>$courseid,
						'testfrom'		=>$testfrom,
						'testto' 		=>$testto,
						'duration' 		=>$tdiff,
						'totalquestions' 	=>$testtotalq,
						'testcode' 		=>$testcode,
					);
					$insert = $this->db->insert('test',$userData);
					//add the details of quiz in to admin_conteupload
/*					$userData = array(
                                         	'acu_courseid'                 =>      $courseid,
                                         	'acu_weekname'                 =>      $couweek,
                                         	'acu_seqno'                    =>      '',
                                         	'acu_weekcontname'             =>      "Exam",
                                         	'acu_contpath'                 =>      "exam",
                                         	'acu_filetype'                 =>      "file",
                                         	'acu_filename'                 =>      "squiz.php",
                                         	'acu_createdate'               =>      $cdate,
                                         	'acu_creatorid'                =>      $this->session->userdata('userEmail'),
                                	);

					$insert = $this->db->insert('admin_conteupload',$userData);

 */	                                if($insert)
        	                        {
                	                        $this->session->set_flashdata('success', 'Your test successfully updated in databse.');
                        	                redirect('admin/viewexam');
                                	}
                                	else{
                                        	$this->session->set_flashdata('error', 'Your test Data is not updated in databse.');
						$this->load->view('admin/createexam',$data);
						return;
                                        	//redirect('admin/createexam');
                                	}
				}


			}
                        $this->load->view('admin/createexam',$data);
                }else{
                        $this->load->view('admin/admin_login');
                }
        }


	public function viewexam(){
                if(isset($this->session->userdata['firstName'])){
                        $data['test_data'] = $this->commodel->get_list('test');
                        $this->load->view('admin/viewexam',$data);
                }else{
                        $this->load->view('admin/admin_login');
                }

        }


	function delete_test($id){
		$contflag=$this->commodel->deleterow('test','testid', $id);
		$smsg= "The test has been deleted.";
                $fmsg= "The test was not found  and could not be deleted.";

                if(!$contflag)
                {
                        $this->logger->write_message("error", $fmsg." Error  in deleting content " ."[test_id:" . $id . "]");
                        $this->logger->write_dbmessage("error", $fmsg." Error  in deleting content "." [test_id:" . $id . "]");
                        $this->session->set_flashdata('err_message', $fmsg.' Error in Deleting content - ', 'error');
                        redirect('admin/viewexam');
                        return;
                }
                else{
                        $this->logger->write_logmessage("delete", $smsg." Deleted   content " . "[test_id:" . $id . "] deleted successfully.. " );
                        $this->logger->write_dblogmessage("delete",$smg. " Deleted content" ." [test_id:" . $id . "] deleted successfully.. " );
                        $this->session->set_flashdata("success", $smsg.' Content Deleted successfully...' );
                        redirect('admin/viewexam');
                }
                $this->load->view('admin/viewexam',$data);
        }
	
	public function addquestion($tid,$sid){
		if(isset($this->session->userdata['firstName'])){
			//get the total no of question in test
			$whdata = array('testid'=>$tid,'subid'=>$sid); 
			$totqres = $this->commodel->get_orderlistspficemore('test','totalquestions',$whdata,'');
			foreach ($totqres as $row){
				$totq = $row->totalquestions;
			}
			//get the total no of question available for test
			$totalqa= $this->commodel->getnoofrows('question',$whdata);
			// get the no of question needed
			$noq = $totq - $totalqa;

			$data['tid']=$tid;
			$data['sid']=$sid;
			$data['qreq']=$noq;
			$message[]='';	

			if(isset($_POST['cou_quest'])){
				$i=1;
			//	$this->form_validation->set_rules('cou_name', 'course name', 'trim|required|xss_clean');
				//      $this->form_validation->set_rules('cou_week', 'course week', 'trim|required|xss_clean');
				for($i;$i<=$noq;$i++){
	                                $this->form_validation->set_rules('question'.$i, 'question', 'trim|xss_clean');
        	                        $this->form_validation->set_rules('optiona'.$i, 'optiona', 'trim|xss_clean');
                	                $this->form_validation->set_rules('optionb'.$i, 'optionb', 'trim|xss_clean');
                        	        $this->form_validation->set_rules('optionc'.$i, 'optionc', 'trim|xss_clean');
                                	$this->form_validation->set_rules('optiond'.$i, 'optiond', 'trim|xss_clean');
	                                $this->form_validation->set_rules('correctans'.$i, 'correctans', 'trimxss_clean');
					$this->form_validation->set_rules('marks'.$i, 'marks', 'trim|xss_clean');
				}
                                if($this->form_validation->run() == FALSE){
                                        $this->load->view('admin/addquestion',$data);
                                        return;
//                                      redirect('admin/createexam');
                                }else{
                                        $testid          	= $tid;
					$subid            	= $sid;
					$i=1;
					for($i;$i<=$noq;$i++){
						$question          	= $this->input->post('question'.$i);
						if(!empty($question)){
                        		                $optiona          	= $this->input->post('optiona'.$i);
                	                	        $optionb          	= $this->input->post('optionb'.$i);
		                                        $optionc          	= $this->input->post('optionc'.$i);
                		                        $optiond          	= $this->input->post('optiond'.$i);
                                		        $correctanswer          = $this->input->post('correctans'.$i);
		                                        $marks         		= $this->input->post('marks'.$i);

							$cdate = date('Y-m-d H:i:s');
                                		        $userData = array(
                                                		'testid'              	=>$testid,
		                                                'subid'              	=>$subid,
                		                                'question'              =>$question,
                                		                'optiona'              	=>$optiona,
                                                		'optionb'               =>$optionb,
		                                                'optionc'              	=>$optionc,
                		                                'optiond'               =>$optiond,
                                		                'correctanswer'         =>$correctanswer,
                                                		'marks'        		=>$marks,
		                                        );
							$insert = $this->db->insert('question',$userData);
							
							if($insert)
		                                        {
                		                                $message[$i]='Your question '.$i.' successfully updated in databse.';
                                		        }
		                                        else{
                		                                $message[$i]='Your question '.$i.' Data is not updated in databse.';
                                		        }
						}//end of empty
					}//end of for internal
					$data['message']=$message;
                                        $this->session->set_flashdata('success', 'Your question successfully updated in databse.');
                                        redirect('admin/viewquestion/'.$testid."/".$subid,$data);
                                               // return;
                                }//else of validation
			}//end of submit
			$this->load->view('admin/addquestion',$data);
		}else{
                        $this->load->view('admin/admin_login');
                }
	}
	
	public function viewquestion($tid,$sid){
		if(isset($this->session->userdata['firstName'])){
			$data['tid']=$tid;
			$data['sid']=$sid;
			$whdata = array('testid'=>$tid,'subid'=>$sid);
                        $data['quest_data'] = $this->commodel->get_orderlistspficemore('question','*',$whdata,'');
                        $this->load->view('admin/viewquestion',$data);
                }else{
                        $this->load->view('admin/admin_login');
                }

        }
	
	function delete_quest($id){
                $contflag=$this->commodel->deleterow('question','qid', $id);
                $smsg= "The question has been deleted.";
                $fmsg= "The question was not found  and could not be deleted.";

                if(!$contflag)
                {
                        $this->logger->write_message("error", $fmsg." Error  in deleting content " ."[qid:" . $id . "]");
                        $this->logger->write_dbmessage("error", $fmsg." Error  in deleting content "." [qid:" . $id . "]");
                        $this->session->set_flashdata('err_message', $fmsg.' Error in Deleting content - ', 'error');
                        redirect('admin/viewexam');
                        return;
                }
                else{
                        $this->logger->write_logmessage("delete", $smsg." Deleted   content " . "[qid:" . $id . "] deleted successfully.. " );
                        $this->logger->write_dblogmessage("delete",$smg. " Deleted content" ." [qid:" . $id . "] deleted successfully.. " );
                        $this->session->set_flashdata("success", $smsg.' Content Deleted successfully...' );
                        redirect('admin/viewexam');
                }
                $this->load->view('admin/viewexam',$data);
        }


	public function logout(){
		
		$this->session->sess_destroy();
		//$this->load->view('signin');
		$confmes = "Logout Successfully !!";
        	$this->session->set_flashdata('success',$confmes);

		redirect('admin');
	}
}
