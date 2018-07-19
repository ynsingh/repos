<?php
/******************************************************
* @name Login.php(controller)    		      	  	  *
* @author Sumit Saxena(sumitsesaxena@gmail.com)       *
*******************************************************/
defined('BASEPATH') OR exit('No direct script access allowed');

class Login extends CI_Controller {

	/******************************************************************************/	
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
/******************************************************************************/
	public function signin() {		
		if($_POST) {			
			$this->form_validation->set_rules('username', 'Username', 'required');
			$this->form_validation->set_rules('password', 'Password', 'required');				
			if ($this->form_validation->run() == FALSE) {	
					
			}else{	
            	$result = $this->commodel->validate_user($_POST);
            	if(!empty($result)){
  					$wharray=array('su_string'=> $result->su_string,'su_status' => $result->su_status, 'su_id' => $result->su_id);
  				}
				if(!empty($wharray)){
					$udata=$this->commodel->get_listspficemore('sign_up','su_string,su_status,su_id,su_emailid,su_name',$wharray);
				}				
				if(!empty($udata)){						
					foreach($udata as $row){
						//print_r($row);die;
						$string = $row->su_string;
						//$ogid = $row->su_oguserid;
						$status = $row->su_status;
						//print_r($status);die;
						if($status == 'Verified'){
							$sdata = ['su_id' => $row->su_id,'su_emailid' => $row->su_emailid,'su_name' => $row->su_name];
							//print_r($sdata);die;
							$this->session->set_userdata($sdata);
							//when user come after registration
							$this->checkuct($row->su_id);
						}else {
							$this->session->set_flashdata("error",'Your email id is not verified.');						
						}	
					}//close foreach
				}else{				
						$this->session->set_flashdata("error",'Your email or password wrong.');					
				}				
			}//close validtion
	    }//close post
		$this->load->view('signin');
	}

    public function checkuct($suid)
    {
    	$whdata=array('uct_userid'=>$suid,'uct_type'=>"Student");
		$crsexist=$this->commodel->isduplicatemore("user_course_type",$whdata);
		if($crsexist){
			$count = $this->commodel->getnoofrows("user_course_type",$whdata);
			if($count == 1){
				redirect('login/usr_login');
			}else{
				redirect('login/course_login');
			}	
		}
		else{
			redirect('Course-Registration');
		}
    }

	public function signup()
	{
		if(isset($_POST['signup'])){
			$this->form_validation->set_rules('name','Name','trim|required');
       		$this->form_validation->set_rules('email','Email','trim|required|xss_clean|valid_email');
            $this->form_validation->set_rules('passwd','Password','trim|required|xss_clean');
            $this->form_validation->set_rules('conf_passwd','Confirm Password','trim|required|xss_clean');
            		//$this->form_validation->set_rules('how_known','How To Know','trim|xss_clean|numeric');

            if($this->form_validation->run() == FALSE){
               			// $this->load->view('enterence/step_zero',$data);
            	redirect('login/signup');
            }else{
            	$name = $this->input->post('name');
            	$email = $this->input->post('email');
            	$pawd = $this->input->post('passwd');
				$hknow = $this->input->post('how_known');
		//check for email exist
				$whdata=array('su_emailid'=>$email);
				$emailexist=$this->commodel->isduplicatemore("sign_up",$whdata);
				if($emailexist){
		    		$confmes = "Your Account is already exist.";
					$this->session->set_flashdata('error',$confmes);
					redirect('login/signup');
				}
				else{

            		/*$data1 = array(
							'su_emailid' => $email,
					);

        			$result = $this->commodel->isduplicatemore('sign_up', $data1);
        				if($result == 1)
        				{
            				$this->session->set_flashdata('error', 'Mail-id :-' .$email.'  '. 'is already exist.' );
            				redirect('login/signup');
						}*/

            		$rstring = random_string('alnum',8);
					$data = array (
						//'su_oguserid'		=> 'NULL',
                       	'su_name' 			=> $name,
	                    'su_emailid' 		=> $email,
        	            'su_password' 		=> $pawd,
                	               // 'su_confpassword' 	=> $cpawd,
                        'su_howtoknow' 		=> $hknow,
                        'su_string'			=> $rstring 
                    );
					$insert = $this->db->insert('sign_up',$data);
					$suid = $this->db->insert_id();
					$session_id = array(
						'su_id'=> $suid,
					);
					$this->session->set_userdata($session_id);
                   /* if(!$insert)
                    {
                        $message = "Your registration not successfully done.";
                        $this->session->set_flashdata('error', $message);
                        redirect('login/signup');
                        //write message
                    }
                    else
                    {*/
                       
                      //  $this->load->model("Mailsend_model","mailmodel");
                $subject = "Registered Successfully.";
			//$verifylink = base_url("login/verify/".$email.'/'.$rstring);
				$erstring= $email.'---'.$rstring;
				$verifylink = base_url("login/verify/".$erstring);
				//<table width='50%'; style='border:1px solid #3A5896;background-color:#8470FF;color:white;font-size:18px;' align=center border=0>
				$message  = "<table width='50%'; style='border:1px solid #3A5896;color:white;font-size:18px;' align=center border=0>
					<tr><td></td></tr>
					<tr><td colspan=2><b>Your are registered successfully, Your details are given below. </td></tr>
					<tr height=15><td colspan=2></td></tr>
					<tr><td width=370><b>Email: </b></td><td align=left>".$email."</td></tr> 
					<tr><td><b>Password : </b> </td><td align=left>".$pawd. "</td><tr>
					
					<tr><td><b>Verification link : </b></td><td align=left> <a href=".$verifylink." style='color:white;'>Verify your registration.</a></td></tr>
					
					</table> " ;

                       // $message = "Your are registered successfully, Your details are given below - \n Email -". $applicant_email ." \nMobile Number- ". $applicant_mobile  ." \nDate of Birth -". $applicant_dob . "\nProgram Name -". $prgname ." \nVerification code for further step is ".$generate_code.".";

                        	$mails=$this->mailmodel->mailsnd($email,$subject,$message,'');
                      // print_r($mails);die;	
                        	if($mails){
                        		 $confmes = "You are registered successfully & Verification link sent to your registered email-id.";
                       			 $this->session->set_flashdata('success',$confmes);
                        		 redirect('login/signin');
                        	}
                        	else{
                        		$message = "Verification link not sent successfully.".$mails;
                       			 $this->session->set_flashdata('error', $message);
                       			 //echo $this->email->print_debugger();
                        		redirect('login/signup');
                        	}
				}// close else if exist
            		}//else close	validation
         	}//post if close isset  
         $this->load->view('signup');
	}

	public function course_login(){
			$usid  = $this->session->userdata['su_id'];
			//$this->checkuct($usid);
			//get a specific course list
			$whdata=array('uct_userid'=>$usid);
			$data['course_data']=$this->commodel->get_listspficemore('user_course_type','uct_courseid',$whdata);

			//$oguid = $this->commodel->get_listspfic1('sign_up','su_userid','su_id',$usid)->su_userid;
			//$data['ustring'] = $this->commodel->get_listspfic1('sign_up','su_string','su_id',$usid)->su_string;
			
			$data['course_data1'] = $this->commodel->get_list('courses');
			

			//$wharray = array('ow_id'    => $oguid);
			//$data['course_data']= $this->commodel->get_listspficemore('ongoingworkshop','owpg_id',$wharray);
			//$data['course_data']= $this->commodel->get_listspficemore('ongoingworkshop','ow_courseid',$wharray);

		//	print_r($data['course_data'] );die;
				

			if(isset($_POST['submit'])){
				$courseid = $this->input->post('cou_type');
					// set course id in session

				//if($courseid == 1){	
				//	$usid  = $this->session->userdata['su_id'];
				//	$array = array('uct_userid' => $usid, 'uct_courseid' => $courseid , 'uct_type' => 'Teacher');
				//	$insert1 = $this->commodel->insertrec('user_course_type', $array);
				//	$confmes = "You are  course select successfully.";
                //    $this->session->set_flashdata('success',$confmes);
                //    redirect('login/usr_login');	
					
				//}else{
				//	$usid  = $this->session->userdata['su_id'];
				//	$array1 = array('uct_userid' => $usid, 'uct_courseid' => $courseid , 'uct_type' => 'Student');
				//	$insert = $this->commodel->insertrec('user_course_type', $array1);
				//	$confmes = "You are  course select successfully.";
                //    $this->session->set_flashdata('success',$confmes);
                    redirect('login/usr_login');
				//}	
			}
			
			$this->load->view('loginpage/usr_login_course',$data);
	}
// function for verify
	// get the values of email and rstring from url
	// match with database is duplicate more
	// if matched then update signup tabel status is verify and clear the string
	//return to login page
	
	public function verify(){
		$rstring = $this->uri->segment(3); 
		$parts = explode('---',$rstring);
		$email=$parts[0];
		$rastring=$parts[1];
		$whdata=array('su_emailid'=>$email, 'su_status'=>"Verified");
		$verifyacc=$this->commodel->isduplicatemore("sign_up",$whdata);
		if($verifyacc){
		    	$confmes = "Your Account is already verified.";
				$this->session->set_flashdata('success',$confmes);
				redirect('login/signin');
		}
		else{
			$data = array('su_emailid'    => $email,'su_string' => $rastring );
	                //verify the data existance filled by user
			$result = $this->commodel->isduplicatemore("sign_up",$data);
			if($result){
				$data1 = array(
					'su_status'  => 'Verified',
					'su_string'  => 'NULL'
				);
        			$result = $this->commodel->updaterec('sign_up', $data1,'su_emailid' ,$email);
        			$confmes = "Your email id is verified successfully, now you can use the system.";
					$this->session->set_flashdata('success',$confmes);
			}else{
				$confmes = "Either email id is wrong or url is tempered.";
				$this->session->set_flashdata('error',$confmes);
			}
		}
		redirect('login/signin');
//       		$this->load->view('verify');
	}

	public function usr_login(){
/*
		get the su_id
		get the course id
		check student role exist in user_course_type
		if exist then redirect to usr_login with course content
		else 
		back to fb course login or Course-Registration
*/	
	//	$fbid = $this->session->userdata['oauth_uid'];
		//print_r($fbid);
		//$this->name = $this->session->userdata['su_name'];
			 //print_r($id);die;
		//echo get_cookie('su_name'); 
		if(isset($this->session->userdata['su_name'])){
			$this->load->view('loginpage/usr_login');
		}
		elseif(isset(($this->session->userdata['first_name']))){
			//echo "hello";
			$this->load->view('loginpage/usr_login');
		}
		else{
			redirect('login/signin');
		}
		
		
	}

	public function udata() { 
        //alert("in udata function");
        $fbcombdata = $this->input->post('datacomb');
        //$fbbtnsub = $this->input->post('btn');
        //print_r($combid);die;
        $fbparts = explode(',',$fbcombdata);
        $fbid = $fbparts[3];

        $num_data = count($fbparts);
        date_default_timezone_set("Asia/Calcutta");
        $cdate = date("Y-m-d H:i:s");
        $whdata = array('oauth_provider' => 'facebook', 'oauth_uid' => $fbid);
        $getudata = $this->commodel->get_listspficemore('users','id,signupid',$whdata);

        $num_data = count($getudata);
        if($num_data > 0){
              
        	if($_POST){
        		foreach($getudata as $row){
                	
                	//update FB user last logout date time in users table
        			$datawh1 = array('id' => $row->id);
					$datarec1 = array('modified' => $cdate);
    				$this->commodel->updaterecarry('users', $datarec1,$datawh1);	
                	// $whdata1 = array('lgd_usersid' => $row->id);
        			// $getudata1 = $this->commodel->get_listspficemore('loggedin_detail','lgd_id',$whdata1);
    					$userID=$row->signupid;
        			//update FB user last logout date time in loggedin table
        			$logged = array('lgd_signupid' => $userID,'lgd_usersid' => $row->id,'login_date' => $cdate );
                	 $insert = $this->commodel->insertrec('loggedin_detail',$logged);
        			//$datawh1 = array('lgd_usersid' => $row->id);
					//$datarec1 = array('login_detail' => $cdate);
    				//$this->commodel->updaterecarry('loggedin_detail', $datarec1,$datawh1);
        			
                }
                //set seesion when FB login second time and after login
                $fbdata = array('oauth_provider' => 'facebook','first_name' => $fbparts[0],'last_name' => $fbparts[1],'email' => $fbparts[2],'oauth_uid' => $fbparts[3],'gender' => $fbparts[4],'created' => $cdate,'modified' => $cdate);
                	$this->session->set_userdata($fbdata);	
                	$confmes = "You are successfully sign-in.";
                    $this->session->set_flashdata('success',$confmes);
            }    
              
        }else{
                //insert user data in users table
                $fbdata = array('oauth_provider' => 'facebook','first_name' => $fbparts[0],'last_name' => $fbparts[1],'email' => $fbparts[2],'oauth_uid' => $fbparts[3],'gender' => $fbparts[4],'picture' => $fbparts[5],'created' => $cdate,'modified' => $cdate);

                	$insert = $this->db->insert('users', $fbdata);
					$fbuserID = $this->db->insert_id();
					//set session when FB user login first time
					$this->session->set_userdata($fbdata);	

					//insert data in signup table	
                    $pwrstring = random_string('alnum',8);	 
                	$fullname = $fbparts[0].' '.$fbparts[1];
                	$fbsigndata = array('su_name' => $fullname,'su_emailid' => $fbparts[2],'su_userid'=>$fbuserID,'su_password' => $pwrstring,'su_status' => 'Verified','su_howtoknow' => 'facebook');

                	 $insert = $this->db->insert('sign_up', $fbsigndata);	
                	 $userID = $this->db->insert_id();

					//update user table
                	$datawh1 = array('id' => $fbuserID);
					$datarec1 = array('signupid' => $userID);
    				$this->commodel->updaterecarry('users', $datarec1,$datawh1);

					//insert data in logged in table	
                	 $logged = array('lgd_signupid' => $userID,'lgd_usersid' => $fbuserID,'login_date' => $cdate );
                	 $insert = $this->commodel->insertrec('loggedin_detail',$logged);

               $confmes = "You are successfully sign-in.";
                $this->session->set_flashdata('success',$confmes); 	 
			}
			$flname=$fbparts[0].' '.$fbparts[1];
			$sdata = ['su_id' => $userID,'su_emailid' => $fbparts[2],'su_name' => $flname ];
			$this->session->set_userdata($sdata);
            
    }

	public function logout(){
		date_default_timezone_set("Asia/Calcutta");
        $cdate = date("Y-m-d H:i:s");

        //get FB user id in session
		$fbid = $this->session->userdata['oauth_uid'];
		$uid = $this->commodel->get_listspfic1('users','id','oauth_uid',$fbid)->id;
		//update modified date in fb users table
		//$this->db->where('id', $uid);
       // $update = $this->db->update('users', array('modified' => $cdate));

		$datawh = array('id' => $uid);
		$datarec = array('modified' => $cdate);
    	$this->commodel->updaterecarry('users', $datarec,$datawh);
		//update FB logout detail in loggedin table
		$datawh1 = array('lgd_usersid' => $uid);
		$datarec1 = array('logout_date' => $cdate);
    	$this->commodel->updaterecarry('loggedin_detail', $datarec1,$datawh1);

		//$this->db->where('lgd_usersid', $uid);
       // $update = $this->db->update('loggedin_detail', array('logout_detail' => $cdate));

		$this->session->sess_destroy();
		//$this->load->view('signin');
		$confmes = "Logout Successfully !!";
        $this->session->set_flashdata('success',$confmes);

		redirect('login/signin');
	}
}

