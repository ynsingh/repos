<?php
/******************************************************
* @name Workshop.php(controller)    		      	  *
* @author Sumit Saxena(sumitsesaxena@gmail.com)       *
*******************************************************/

defined('BASEPATH') OR exit('No direct script access allowed');

class Workshop extends CI_Controller {

	/******************************************************************************/	
	public function __construct(){
		parent::__construct();
		$this->load->model("Mailsend_model","mailmodel");
		$this->load->model('Common_model',"commodel");
		$this->load->library('form_validation');
		$this->load->library('session');
		$this->load->library('curl');
		$this->load->helper(array('url','form'));
		$this->load->helper('string');
		
	}

	//https://test.payu.in/_payment
/******************************************************************************/
	public function courseenroll(){
		$cid = $this->uri->segment(3); 
		$data['courfees'] = $this->commodel->get_listspfic1('courses','cou_fees','cou_id',$cid)->cou_fees;
		$data['cid'] = $cid;
		$data['crscode'] = $this->commodel->get_listspfic1('courses','cou_code','cou_id',$cid)->cou_code;
		$this->load->view('courseenroll',$data);
	}	
	
	public function ongoing_workshop()
	{$cid = $this->uri->segment(3); 
		$data['courfees'] = $this->commodel->get_listspfic1('courses','cou_fees','cou_id',$cid)->cou_fees;
		$data['cid'] = $cid;
			//$cid = $this->uri->segment(3); 
		if(isset($_POST['submit'])){
			$this->form_validation->set_rules('name','Name','trim|required');
       			$this->form_validation->set_rules('email','Email','trim|required|xss_clean|valid_email');
            		$this->form_validation->set_rules('place','Place','trim|required|xss_clean');
            		$this->form_validation->set_rules('nationality','Nationality','trim|required|xss_clean');
            		$this->form_validation->set_rules('contact','Contacat Number','trim|xss_clean|numeric');
			$this->form_validation->set_rules('amount','Amount','trim|required|xss_clean');
            
            		if($this->form_validation->run() == FALSE){
               			$this->load->view('courseenroll',$data);
               			return;		
            	//redirect('workshop/courseenroll');
            		}else{

				$name 		= $this->input->post('name');
				$mailid 	= $this->input->post('email');
				$place 		= $this->input->post('place');
				$nationality    = $this->input->post('nationality');
				$phoneno 	= $this->input->post('contact');
				$product_info   = $this->input->post('product_info');//this is school name post
				$rperson        = $this->input->post('reperson');
				$amount         = $this->input->post('amount');
				$cid         	= $this->input->post('courseid');

				$data = array(
					'course_id'	=> $cid,
            				'amount' 	=> $amount,  
					'productinfo' 	=> $product_info,         
            				'firstname' 	=> $name,
            				'mailid' 	=> $mailid,
           				'phoneno' 	=> $phoneno,
           				'refperson'	=> $rperson,
           				'place'		=> $place,
           				'nationality'   => $nationality,
        			);
        			
			}//else close
					
								
		$this->load->view('courseenroll_confirm',$data);
		}
		//$this->load->view('courseenroll_confirm',$data);
	}
	
	function enroll_workshop(){

		if(isset($_POST['pay'])){

			$this->form_validation->set_rules('firstname','Name','trim|required');
       			$this->form_validation->set_rules('email','Email','trim|required|xss_clean|valid_email');
            		$this->form_validation->set_rules('place','Place','trim|required|xss_clean');
            		$this->form_validation->set_rules('nationality','Nationality','trim|required|xss_clean');
            		$this->form_validation->set_rules('contact','Contacat Number','trim|xss_clean|numeric|required');
			$this->form_validation->set_rules('amount','Amount','trim|required|xss_clean');
            
            		if($this->form_validation->run() == FALSE){
            			redirect('workshop/ongoing_workshop');
            		}else{
            			$firstname 	= $this->input->post('firstname');
				$mailid 	= $this->input->post('email');
				$place 		= $this->input->post('place');
				$nationality    = $this->input->post('nationality');
				$phoneno 	= $this->input->post('contact');
				$product_info   = $this->input->post('product_info');//this is school name post
				$rperson        = $this->input->post('reperson');
				$amount         = $this->input->post('amount');
				$cid         	= $this->input->post('courseid');
						//$crscode        = $this->input->post('coursecode');
            	    		$courname = $this->commodel->get_listspfic1('courses','cou_name','cou_id',$cid)->cou_name;
				$crscode = $this->commodel->get_listspfic1('courses','cou_code','cou_id',$cid)->cou_code;

				//insert record in ongoing table
				$owdata = array(
					'ow_name' 		=> $firstname,	
					'ow_email' 		=> $mailid,
					'ow_courseid' 		=> $cid,
					'ow_place' 		=> $place,
					'ow_nationality' 	=> $nationality,	
					'ow_contact' 		=> $phoneno,	
					'ow_school'		=> $product_info,	//variable name change school to pinfo for payU
					'ow_rperson' 		=> $rperson,	
					'ow_amount'		=> $amount,	
				);	
				//when user record exsist in signup table then data insert in  ongoingworkshop ,ongoingworkshop_pg,user_course_typeuser_course_type
				if(isset($this->session->userdata['su_id'])){
					$owinsert = $this->db->insert('ongoingworkshop', $owdata);
					$owid = $this->db->insert_id();
							
					$usid  = $this->session->userdata['su_id'];
					$coudata  = array('uct_userid'  => $usid ,'uct_courseid' => $cid, 'uct_type' => 'Student');
					//print_r($coudata);die;
					$pginsert = $this->db->insert('user_course_type', $coudata);
						/*	if($pginsert){
                        		 $confmes = "You are registered successfully in " .$courname. " .";
                       			 $this->session->set_flashdata('success',$confmes);
                 //      			 $purpose="Workshop";
                   //    			 $this->payment_req($purpose,$amount,$phoneno,$firstname,$mailid);

                        		 redirect('workshop/courseenroll_home');
                        	}
                        	else{
                        		$message = "Some detail is incorrect.";
                       			$this->session->set_flashdata('error', $message);
                       			redirect('workshop/enroll_workshop');
                        	} */
				}

				//when user record not exsist in signup table then data insert in  ongoingworkshop ,ongoingworkshop_pg,user_course_typeuser_course_type or sign_up
				else{
							
					$owdupdata = array(
						'ow_email' 		=> $mailid,
						'ow_courseid' 		=> $cid,
					);
        			
        				$result = $this->commodel->isduplicatemore('ongoingworkshop', $owdupdata);
        				if($result == 1)
        				{
            					$this->session->set_flashdata('error', 'Mail-id :- ' .$mailid.'  '. 'is already enrolled for this course - '.'" ' .$courname. '"');
            					redirect('workshop/courseenroll');
					}

					$owinsert = $this->db->insert('ongoingworkshop', $owdata);
					$owid = $this->db->insert_id();
							
					//insert record in sign up table
					$rstring = random_string('alnum',8);
					$signupdata = array (
						'su_userid'		=> $owid,
                                		'su_name' 		=> $firstname,
		                                'su_emailid' 		=> $mailid,
                		                'su_password' 		=> $password,
                                		//'su_confpassword' 	=> $cpawd,
		                                'su_status' 		=> 'Verified',
						'su_string'		=> $rstring 

                            		);
                            		$signinsert = $this->db->insert('sign_up',$signupdata);
					$signupid = $this->db->insert_id();

//					$suid = $this->commodel->get_listspfic1('sign_up','su_id','su_id',$signupid)->su_id;
					$suname = $this->commodel->get_listspfic1('sign_up','su_name','su_id',$signupid)->su_name;
					//print_r($suname);die;
					$userdata = ['su_id' => $signupid ,'su_name' => $suname];
					//print_r($userdata);die;
					$this->session->set_userdata($userdata);

					$coudata  = array('uct_userid'  => $signupid ,'uct_courseid' => $cid, 'uct_type' => 'Student');
					$pginsert = $this->db->insert('user_course_type', $coudata);

			/*		$subject = "Registered Successfully";
					$pawd = random_string('alnum',6);
					$erstring= $mailid.'---'.$rstring;
					$verifylink = base_url("login/verify/".$erstring);
				
					$message  = "<table width='50%'; style='border:1px solid #3A5896;color:black;font-size:18px;' align=center border=0>
					<tr><td></td></tr>
					<tr><td colspan=2><b>Your are registered successfully, Your details are given below</td></tr>
					<tr height=15><td colspan=2></td></tr>
					<tr><td width=370><b>Email: </b></td><td align=left>".$mailid."</td></tr> 
					<tr><td><b>Password : </b> </td><td align=left>".$pawd. "</td><tr>
					<tr><td><b>Course Name : </b> </td><td align=left>".$courname. "</td><tr>

					<tr><td><b>Verification link : </b></td><td align=left> <a href=".$verifylink.">Verify your registration.</a></td></tr>
					
					</table> " ;

					$mails=$this->mailmodel->mailsnd($mailid,$subject,$message,'');
			 */

                        /*	if($mails){
                        		$confmes = "You are registered successfully in " .$courname. " & Verification link sent to your registered email-id.";
                       			$this->session->set_flashdata('success',$confmes);
                       			

								redirect('workshop/courseenroll_home');
                        		
                        	}
                        	else{
                        		$message = "Verification link not sent successfully.".$mails;
                       			$this->session->set_flashdata('error', $message);
                       			//echo $this->email->print_debugger();
                        		redirect('workshop/enroll_workshop');
                        	}*/							
				}//else close						

				/*  if($cid == 1){
	                                redirect("https://imjo.in/fBVw9u");
        	                    }
                	            if($cid == 2){
                        	        redirect("https://imjo.in/HGPDer");
                            		}
                            if($cid == 3){
                                redirect("https://imjo.in/gBvKXY");
                            }
                            if($cid == 4){
                                redirect("https://imjo.in/kuFj4a");
                            }
                            if($cid == 5){
                                redirect("https://imjo.in/kuFj4a");
                            }
                            if($cid == 6){
                                redirect("https://imjo.in/WyRmED");
                            }*/
                            $purpose=$crscode."-Workshop-".$owid;
                            $resp=$this->payment_req($purpose,$amount,$phoneno,$firstname,$mailid);
                            //$response['payment_request']
                            $data = json_decode($resp,true);
                            //var_dump($data);
                            $site=$data["payment_request"]["longurl"];
                            //print_r($site); die();
                            header('HTTP/1.1 301 Moved Permanently');
                            header('Location:'.$site);
            		}				
		}

	//$this->load->view('courseenroll_confirm');
	}

	function payment_req($purpose,$amount,$phone,$name,$email){
		$ch = curl_init();

		curl_setopt($ch, CURLOPT_URL, 'https://www.instamojo.com/api/1.1/payment-requests/');
		curl_setopt($ch, CURLOPT_HEADER, FALSE);
		curl_setopt($ch, CURLOPT_RETURNTRANSFER, TRUE);
		curl_setopt($ch, CURLOPT_FOLLOWLOCATION, TRUE);
		curl_setopt($ch, CURLOPT_HTTPHEADER,
            array("X-Api-Key:888b4d6c3ca3889e9362c2f74f62e002",
                  "X-Auth-Token:eafb959fd37284f0f3962175cd4ec1e1"));
				
		$payload = Array(
    		'purpose' => $purpose,
    		'amount' => $amount,
    		'phone' => $phone,
    		'buyer_name' => $name,
    		'redirect_url' => 'http://annantgyan.com/webhook',
    		'send_email' => true,
    		'webhook' => 'http://annantgyan.com/webhook',
    		'send_sms' => true,
    		'email' => $email,
    		'allow_repeated_payments' => false
		);
		curl_setopt($ch, CURLOPT_POST, true);
		curl_setopt($ch, CURLOPT_POSTFIELDS, http_build_query($payload));
		$response = curl_exec($ch);
		curl_close($ch); 

		//echo $response;
		return $response;
	}

	function payment($id){

		$ch = curl_init();

		curl_setopt($ch, CURLOPT_URL, 'https://www.instamojo.com/api/1.1/payment-requests/'.$id.'/');
		curl_setopt($ch, CURLOPT_HEADER, FALSE);
		curl_setopt($ch, CURLOPT_RETURNTRANSFER, TRUE);
		curl_setopt($ch, CURLOPT_FOLLOWLOCATION, TRUE);
		curl_setopt($ch, CURLOPT_HTTPHEADER,
			array("X-Api-Key:888b4d6c3ca3889e9362c2f74f62e002",
                  "X-Auth-Token:eafb959fd37284f0f3962175cd4ec1e1"));
        
		$response = curl_exec($ch);
		curl_close($ch); 

		echo $response;
	}


	// Payment request id ($prid), payment id ($pid) 
	function updatepg($prid,$pid){
		//print_r("expression"); die();
		$confmes = "You are registered successfully in " .$courname. " & Verification link sent to your registered email-id.";
        $this->session->set_flashdata('success',$confmes);
		redirect('workshop/courseenroll_home');
	}
	function courseenroll_home(){
		 $this->load->view('courseenroll_usr');
	}

}
