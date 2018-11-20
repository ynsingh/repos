<?php
/******************************************************
* @name Workshop.php(controller)    		      *
* @author Nagendra Kumar Singh(nksinghiitk@gmail.com) *
* @author Sumit Saxena(sumitsesaxena@gmail.com)       *
*******************************************************/

defined('BASEPATH') OR exit('No direct script access allowed');

class Workshop extends CI_Controller {

	/******************************************************************************/	
	public function __construct(){
		parent::__construct();
		$this->load->model("Mailsend_model","mailmodel");
		$this->load->model('Common_model',"commodel");
//		$this->load->library('form_validation');
		$this->load->library('session');
		$this->load->library('curl');
		$this->load->helper(array('url','form'));
		$this->load->helper('string');
		
	}

	public function courseenroll(){
		$cid = $this->uri->segment(3); 
		//$data['courfees'] = $this->commodel->get_listspfic1('courses','cou_fees','cou_id',$cid)->cou_fees;
		$cfees = $this->commodel->get_listspfic1('courses','cou_fees','cou_id',$cid)->cou_fees;
		$cdate = date('Y-m-d');
		if(($cid == 9 )|| ($cid == 10)||($cid == 11)){
			if ($cdate < "2019-01-01"){
				$cfees = $cfees / 2;
			}
		}
		$data['courfees'] = $cfees;
		$data['courseid'] = $cid;
		$data['crscode'] = $this->commodel->get_listspfic1('courses','cou_code','cou_id',$cid)->cou_code;
		$this->load->view('courseenroll',$data);
	}	
	
	public function ongoing_workshop()
	{
		$cid = $this->uri->segment(3); 
		//$data['courfees'] = $this->commodel->get_listspfic1('courses','cou_fees','cou_id',$cid)->cou_fees;
		$cfees = $this->commodel->get_listspfic1('courses','cou_fees','cou_id',$cid)->cou_fees;
		$cdate = date('Y-m-d');
		if(($cid == 9 )|| ($cid == 10)||($cid == 11)){
	                if ($cdate < "2019-01-01"){
        	                $cfees = $cfees / 2;
                	}
		}
                $data['courfees'] = $cfees;

		$data['cid'] = $cid;
			//$cid = $this->uri->segment(3); 
		if(isset($_POST['submit'])){
			$this->form_validation->set_rules('name','Name','trim|required|xss_clean');
       			$this->form_validation->set_rules('email','Email','trim|required|xss_clean|valid_email');
            		$this->form_validation->set_rules('place','Place','trim|required|xss_clean');
            		$this->form_validation->set_rules('nationality','Nationality','trim|required|xss_clean');
            		$this->form_validation->set_rules('contact','Contacat Number','trim|xss_clean|numeric');
			$this->form_validation->set_rules('amount','Amount','trim|required|xss_clean');
			$this->form_validation->set_rules('fatname','Father Name','trim|xss_clean');
			$this->form_validation->set_rules('designame','Father Designation','trim|xss_clean');
			$this->form_validation->set_rules('fcatname','Force Category','trim|xss_clean');
            
			$cid = $this->input->post('courseid');
			$data['courseid']=$cid;
			if($this->form_validation->run() == FALSE){
               			$this->load->view('courseenroll',$data);
               			return;		
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
				$fatname        = $this->input->post('fatname');
				$fdesigname     = $this->input->post('designame');
				$fcatname       = $this->input->post('fcatname');

				$data = array(
					'courseid'	=> $cid,
            				'amount' 	=> $amount,  
					'productinfo' 	=> $product_info,         
            				'firstname' 	=> $name,
            				'mailid' 	=> $mailid,
           				'phoneno' 	=> $phoneno,
           				'refperson'	=> $rperson,
           				'place'		=> $place,
           				'nationality'   => $nationality,
           				'fatname'	=> $fatname,
           				'designame'	=> $fdesigname,
           				'fcatname'	=> $fcatname,
				);
				/*
				 This is commented on 8 september 2018 for stop duplicate signup
				$datadup = array('su_emailid' => $mailid);
				$isexist=$this->commodel->isduplicatemore('sign_up',$datadup);
				if($isexist)	
				{
					$this->session->set_flashdata('error', 'Email id - '.$mailid .' already exist ');
               				$this->load->view('courseenroll',$data);
		//			$this->load->view('workshop/courseenroll',$data);
	                                return;
				}
				else{
					$this->session->set_flashdata('error', '');
					$this->load->view('courseenroll_confirm',$data);
					return;
				}
				 */
				if($cid == 12){
					if((empty($fatname)) ||(empty($fdesigname)) ||(empty($fcatname))){
						$this->session->set_flashdata('error',"Either Father name or Father designation or Force Category is empty. Kindly fill these details.");
						redirect('Course-Registration');
						return;
					}
				}
				$this->load->view('courseenroll_confirm',$data);
				return;
			}//else close
		}//close submit
		redirect('Course-Registration');
	}
	
	function enroll_workshop(){

		if(isset($_POST['pay'])){

			$this->form_validation->set_rules('firstname','Name','trim|required|xss_clean');
       			$this->form_validation->set_rules('email','Email','trim|required|xss_clean|valid_email');
            		$this->form_validation->set_rules('place','Place','trim|required|xss_clean');
            		$this->form_validation->set_rules('nationality','Nationality','trim|required|xss_clean');
            		$this->form_validation->set_rules('contact','Contacat Number','trim|xss_clean|numeric|required');
			$this->form_validation->set_rules('amount','Amount','trim|required|xss_clean');
			$this->form_validation->set_rules('fatname','Father Name','trim|xss_clean');
			$this->form_validation->set_rules('designame','Father Designation','trim|xss_clean');
			$this->form_validation->set_rules('fcatname','Force Category','trim|xss_clean');
            
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
				$fatname        = $this->input->post('fatname');
				$fdesigname     = $this->input->post('designame');
				$fcatname       = $this->input->post('fcatname');
						//$crscode        = $this->input->post('coursecode');
            	    		$courname = $this->commodel->get_listspfic1('courses','cou_name','cou_id',$cid)->cou_name;
				$crscode = $this->commodel->get_listspfic1('courses','cou_code','cou_id',$cid)->cou_code;
				$cdate = date('Y-m-d');
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
					'ow_date'		=> $cdate,
				);	
				//when user record exsist in signup table then data insert in  ongoingworkshop ,ongoingworkshop_pg,user_course_typeuser_course_type
				if(isset($this->session->userdata['su_id'])){
					$owinsert = $this->db->insert('ongoingworkshop', $owdata);
					$owid = $this->db->insert_id();
							
					$usid  = $this->session->userdata['su_id'];
					$coudata  = array('uct_userid'  => $usid ,'uct_courseid' => $cid, 'uct_type' => 'Student');
					//print_r($coudata);die;
					$pginsert = $this->db->insert('user_course_type', $coudata);
				}

				//when user record not exsist in signup table then data insert in  ongoingworkshop ,ongoingworkshop_pg,user_course_typeuser_course_type or sign_up
				else{
							
				/*	$owdupdata = array(
						'ow_email' 		=> $mailid,
						'ow_courseid' 		=> $cid,
					);
        			
        				$result = $this->commodel->isduplicatemore('ongoingworkshop', $owdupdata);
        				if($result == 1)
        				{
            					$this->session->set_flashdata('error', 'Mail-id :- ' .$mailid.'  '. 'is already enrolled for this course - '.'" ' .$courname. '"');
            					redirect('workshop/courseenroll');
					}
				 */
					$owinsert = $this->db->insert('ongoingworkshop', $owdata);
					$owid = $this->db->insert_id();
							
					//insert record in sign up table
					$rstring = random_string('alnum',8);
					$signupdata = array (
						'su_userid'		=> $owid,
                                		'su_name' 		=> $firstname,
		                                'su_emailid' 		=> $mailid,
                		                'su_password' 		=> $rstring,
		                                'su_status' 		=> 'Verified',
						'su_string'		=> $rstring 

					);
					$datadup = array('su_emailid' => $mailid);
	                                $isexist=$this->commodel->isduplicatemore('sign_up',$datadup);
					if($isexist){
						$signupid = $this->commodel->get_listspfic1('sign_up','su_id','su_emailid',$mailid)->su_id;
					
					}else{
                            			$signinsert = $this->db->insert('sign_up',$signupdata);
						$signupid = $this->db->insert_id();
						//create insert data
						$uddata = array(
							'ud_userid' 		=> $signupid,
							'ud_fname' 		=> $fatname,
							'ud_fdesig' 		=> $fdesigname,
							'ud_fadcategory' 	=> $fcatname,
						);
						//insert entry in user details 
                            			$udinsert = $this->db->insert('userdetails',$uddata);
					}
					$suname = $this->commodel->get_listspfic1('sign_up','su_name','su_id',$signupid)->su_name;
					$userdata = ['su_id' => $signupid ,'su_emailid' => $mailid,'su_name' => $suname];
					$this->session->set_userdata($userdata);

					$coudata  = array('uct_userid'  => $signupid ,'uct_courseid' => $cid, 'uct_type' => 'Student');
					$couexist=$this->commodel->isduplicatemore('user_course_type',$coudata);
					if($couexist){
					
					}else{
						$uctinsert = $this->db->insert('user_course_type', $coudata);
					}

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
                            //$purpose=$crscode."-Workshop-".$owid.$signupid;
                            $purpose=$crscode."-Workshop-".$owid;
                            $resp=$this->payment_req($purpose,$amount,$phoneno,$firstname,$mailid);
                            //$response['payment_request']
                            $data = json_decode($resp,true);
			    //var_dump($data);
			   // $ida = $data["payment_request"]["id"];
			   // $owupd=array('ow_remark' => $ida);
			   // $owupdflag=$this->db->update('ongoingworkshop',$owupd,'ow_id',$owid);
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
	function payment_detail($paymentreq,$paymentid)
	{
		$ch = curl_init();
	    
		curl_setopt($ch, CURLOPT_URL, 'https://www.instamojo.com/api/1.1/payment-requests/'.$paymentreq.'/'.$paymentid.'/');
		curl_setopt($ch, CURLOPT_HEADER, FALSE);
		curl_setopt($ch, CURLOPT_RETURNTRANSFER, TRUE);
    		curl_setopt($ch, CURLOPT_FOLLOWLOCATION, TRUE);
    		curl_setopt($ch, CURLOPT_HTTPHEADER,
			array("X-Api-Key:888b4d6c3ca3889e9362c2f74f62e002", "X-Auth-Token:eafb959fd37284f0f3962175cd4ec1e1"));
/*    		$payload = Array(
		        'purpose' => 'XXXX',
        		'amount' => 'XXXX',
        		'phone' => 'XXXX',
        		'buyer_name' => 'XXXX',
        		'redirect_url' => 'http://XXXX/Success',
        		'send_email' => true,
        		'webhook' => 'http://XXXX/webhook',
        		'send_sms' => true,
        		'email' => 'XXXX@XXXX.XXXX',
        		'allow_repeated_payments' => false
		);
 */
    		$response = curl_exec($ch);
    		curl_close($ch); 

    		return $response;

	}

	function payment($id){

		$ch = curl_init();

		curl_setopt($ch, CURLOPT_URL, 'https://www.instamojo.com/api/1.1/payment-requests/'.$id.'/');
		curl_setopt($ch, CURLOPT_HEADER, FALSE);
		curl_setopt($ch, CURLOPT_RETURNTRANSFER, TRUE);
		curl_setopt($ch, CURLOPT_FOLLOWLOCATION, TRUE);
		curl_setopt($ch, CURLOPT_HTTPHEADER,
			array("X-Api-Key:888b4d6c3ca3889e9362c2f74f62e002", "X-Auth-Token:eafb959fd37284f0f3962175cd4ec1e1"));
        
		$response = curl_exec($ch);
		curl_close($ch); 

		return $response;
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
