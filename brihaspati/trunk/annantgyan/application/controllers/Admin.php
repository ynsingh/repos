<?php
/******************************************************
* @name Admin.php(controller)    		      	  *
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
								
								$sdata = ['id' => $row->id,'userEmail' => $row->userEmail,'firstName' => $row->firstName,'lastName' => $row->lastName];
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
	}

	function  admin_addannouncecourse(){
		
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
	}

	function admin_coucontent(){
		$data['couname'] = $this->commodel->get_list('courses');
		$this->load->view('admin/admin_couupload',$data);
	}

	public function upload_file(){
	//echo "hello";die;	
		$data['couname'] = $this->commodel->get_list('courses');
		if(isset($_POST['cou_upload'])) {
			//echo "hello";die;	

			//$this->form_validation->set_rules('userfile', 'Upload Photo', 'trim|required|xss_clean');
			//$this->form_validation->set_rules('cou_name', 'Upload Photo', 'trim|required|xss_clean');
		///	$this->form_validation->set_rules('cou_type', 'Upload Photo', 'trim|required|xss_clean');
			//$this->form_validation->set_rules('cou_week', 'Upload Photo', 'trim|required|xss_clean');
			///$this->form_validation->set_rules('cou_contname', 'Upload Photo', 'trim|required|xss_clean');
			///$this->form_validation->set_rules('cou_seqno', 'Upload Photo', 'trim|required|xss_clean|numeric');
			
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

				
				if($_FILES['userfile']['size'] > 4000) {
         				$errors[]='Photo size must be excately 4 MB';
					
      				}
				
				$name = $_FILES['userfile']['name'];
				
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
					$config['max_size'] = '100';
              		$config['allowed_types'] = 'pdf|doc|docx|DOCX|DOC|xls|xlsx';
              	}
              	elseif($type == 'video'){
              		$config['remove_spaces'] = TRUE;
					$config['max_size'] = '15000';
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
									$simsg = "The permitted size of Document is 100kb .";
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
									$simsg = "The permitted size of Video File is 5 mb.";;
									$ferror = $simsg.$ferror;
									$this->session->set_flashdata('error', $ferror);
									redirect('admin/admin_coucontent');
								}
								
                		}
            		
				//Prepare array of user data
					$cdate = date('Y-m-d H:i:s');
     	       			$userData = array(
        	        		 'acu_courseid'         =>		$courseid,
							 'acu_weekname'         =>		$week,
							 'acu_seqno'			=>  	$seq_no,
							 'acu_weekcontname' 	=>		$content,
							 'acu_contpath' 		=>  	$desired_dir2,
							 'acu_filetype'			=>		$type,
							 'acu_filename'			=>		$name,
							 'acu_createdate'		=>		$cdate,
							 'acu_creatorid'		=>		$this->session->userdata('userEmail'),
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
			
		$data['cou_data'] = $this->commodel->get_list('admin_conteupload');
		$this->load->view('admin/admin_couuploadview',$data);
	}

	public function logout(){
		
		$this->session->sess_destroy();
		//$this->load->view('signin');
		$confmes = "Logout Successfully !!";
        $this->session->set_flashdata('success',$confmes);

		redirect('admin');
	}
}
