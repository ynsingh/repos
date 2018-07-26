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
		$this->load->helper(array('url','form'));
		$this->load->helper('string');
		
	}

	//https://test.payu.in/_payment
/******************************************************************************/
	public function courseenroll(){
		$cid = $this->uri->segment(3); 
		$data['courfees'] = $this->commodel->get_listspfic1('courses','cou_fees','cou_id',$cid)->cou_fees;
		$data['cid'] = $cid;

		$this->load->view('courseenroll',$data);
	}	
	
	public function ongoing_workshop()
	{
			//$cid = $this->uri->segment(3); 
		if(isset($_POST['submit'])){
			$this->form_validation->set_rules('name','Name','trim|required');
       		$this->form_validation->set_rules('email','Email','trim|required|xss_clean|valid_email');
            $this->form_validation->set_rules('place','Place','trim|required|xss_clean');
            $this->form_validation->set_rules('nationality','Nationality','trim|required|xss_clean');
            $this->form_validation->set_rules('contact','Contacat Number','trim|xss_clean|numeric');
			$this->form_validation->set_rules('amount','Amount','trim|required|xss_clean');
            
            if($this->form_validation->run() == FALSE){
               			// $this->load->view('enterence/step_zero',$data);
            	redirect('workshop/courseenroll');
            }else{

				$name 		    = $this->input->post('name');
				$mailid 	    = $this->input->post('email');
				$place 		    = $this->input->post('place');
				$nationality    = $this->input->post('nationality');
				$phoneno 	    = $this->input->post('contact');
				$product_info   = $this->input->post('product_info');//this is school name post
				$rperson        = $this->input->post('reperson');
				$amount         = $this->input->post('amount');
				$cid         	= $this->input->post('courseid');

				$ftype = 'Ongoing Workshop';
				$bname = 'PayuMoney';

				$MERCHANT_KEY	= MERCHANT_KEY;
				$SALT =	SALT;
				$txnid = substr(hash('sha256', mt_rand() . microtime()), 0, 20);
		
       				//optional udf values 
        			$udf1 = '';
        			$udf2 = '';
        			$udf3 = '';
        			$udf4 = '';
        			$udf5 = '';

					$hashSequence = "key|txnid|amount|productinfo|firstname|email|udf1|udf2|udf3|udf4|udf5||||||SALT";
		//key, txnid, amount, productinfo, firstname, email, phone, surl, furl, hash
					$hashstring = $MERCHANT_KEY . '|' . $txnid . '|' . $amount . '|' . $product_info . '|' . $name . '|' . $mailid . '|' . $udf1 . '|' . $udf2 . '|' . $udf3 . '|' . $udf4 . '|' . $udf5 . '|'.''.'|'.''.'|'.''.'|'.''.'|'.''.'|' . $SALT;
					//print_r($hashstring);die;
         			$hash = strtolower(hash('sha512', $hashstring));
					$success = site_url() .'workshop/payustatus'; //return to payustatus function   
        			$fail    = site_url() . 'workshop/payustatus'; //return to payustatus function
       	 			//$cancel  = base_url() . 'payumoney/payustatus';//return to payustatus function
					//for live change action  https://secure.payu.in)
	
					$data = array(
						   // 'owid'          => $owid, 
            				'mkey' 			=> $MERCHANT_KEY,
            				'tid' 			=> $txnid,
            				'hash' 			=> $hash,
            				'amount' 		=> $amount,  
							'productinfo' 	=> $product_info,         
            				'firstname' 	=> $name,
            				'mailid' 		=> $mailid,
           					'phoneno' 		=> $phoneno,
           					'refperson'		=> $rperson,
           					'place'			=> $place,
           					'nationality'   => $nationality,
           					//'address' 	=> $ftype,
							//'action' 		=> "https://test.payu.in", //for live change action  https://secure.payu.in
							'action' 		=> PAYU_BASE_URL,
           					'surl' 			=> $success,
           					'furl'			=> $fail,
            		   
        			);
        			$courname = $this->commodel->get_listspfic1('courses','cou_name','cou_id',$cid)->cou_name;
					$owdupdata = array(
								//'ow_name' 			=> $name,	
								'ow_email' 			=> $mailid,
								'ow_courseid' 		=> $cid,
								//'ow_place' 			=> $place,
								//'ow_nationality' 	=> $nationality,	
								//'ow_contact' 		=> $phoneno,	
								//'ow_school'			=> $product_info,	//variable name change school to pinfo for payU
								//'ow_rperson' 		=> $rperson,	
						
								//'ow_amount'			=> $amount,	
								//'ow_bankname' 		=> $bname,
								//'ow_paymentgateway' => 'online'	
							);
        			
        			$result = $this->commodel->isduplicatemore('ongoingworkshop', $owdupdata);
        					if($result == 1)
        					{
            					$this->session->set_flashdata('error', 'Mail-id :- ' .$mailid.'  '. 'is already enrolled for this course - '.'" ' .$courname. '"');
            					redirect('workshop/courseenroll');
							}
					//insert record in ongoing table
							$owdata = array(
								'ow_name' 			=> $name,	
								'ow_email' 			=> $mailid,
								'ow_courseid' 		=> $cid,
								'ow_place' 			=> $place,
								'ow_nationality' 	=> $nationality,	
								'ow_contact' 		=> $phoneno,	
								'ow_school'			=> $product_info,	//variable name change school to pinfo for payU
								'ow_rperson' 		=> $rperson,	
						
								'ow_amount'			=> $amount,	
								//'ow_bankname' 		=> $bname,
								//'ow_paymentgateway' => 'online'	
							);
						
					//when user record exsist in signup table then data insert in  ongoingworkshop ,ongoingworkshop_pg,user_course_typeuser_course_type
					if(isset($this->session->userdata['su_id'])){
							
							$owinsert = $this->db->insert('ongoingworkshop', $owdata);
							$owid = $this->db->insert_id();
							$data['owid'] = $owid;

							$owpgdata = array('ow_id'  => $owid );
							$pginsert = $this->db->insert('ongoingworkshop_pg', $owpgdata);

							$usid  = $this->session->userdata['su_id'];
							$coudata  = array('uct_userid'  => $usid ,'uct_courseid' => $cid, 'uct_type' => 'Student');
							$pginsert = $this->db->insert('user_course_type', $coudata);
					}

					//when user record not exsist in signup table then data insert in  ongoingworkshop ,ongoingworkshop_pg,user_course_typeuser_course_type or sign_up
					else{
							$owinsert = $this->db->insert('ongoingworkshop', $owdata);
							$owid = $this->db->insert_id();
							$data['owid'] = $owid;

							$owpgdata = array('ow_id'  => $owid );
							$pginsert = $this->db->insert('ongoingworkshop_pg', $owpgdata);

							//insert record exsist in sign up table
							$rstring = random_string('alnum',8);
							$signupdata = array (
								'su_userid'			=> $owid,
                                //'su_name' 			=> $firstname,
                               /// 'su_emailid' 		=> $mailid,
                                //'su_password' 		=> $password,
                                //'su_confpassword' 	=> $cpawd,
                                //'su_howtoknow' 		=> $hknow,
                                //'su_string'			=> $rstring 
                            );
                            $signinsert = $this->db->insert('sign_up',$signupdata);
							$signupid = $this->db->insert_id();

							$userdata = ['su_userid' => $owid];
							$this->session->set_userdata($userdata);

							$coudata  = array('uct_userid'  => $signupid ,'uct_courseid' => $cid, 'uct_type' => 'Student');
							$pginsert = $this->db->insert('user_course_type', $coudata);
							/*$result = $this->commodel->isduplicatemore('sign_up', $signupdata);
        					if($result == 1)
        					{
            					$this->session->set_flashdata('error', 'Mail-id :-' .$mailid.'  '. 'is already exist.');
            					redirect('Header/ongoingworkshop');
							}else{	
								  $signinsert = $this->db->insert('sign_up',$signupdata);
							}*/
							//$rstring = random_string('alnum',8);
							/*
							$insert = $this->db->insert('sign_up',$data);
							$suid = $this->db->insert_id();
							//set id in session	
							$sdata = ['su_id' => $suid];
							$this->session->set_userdata($sdata);*/

					
							/*$array1 = array('uct_userid' => $owid, 'uct_courseid' => $cid , 'uct_type' => 'Student');
							$insert = $this->commodel->insertrec('user_course_type', $array1);	
					
							$usid  = $this->session->userdata['su_id'];
							$coudata  = array('uct_userid'  => $usid ,'uct_courseid' => $cid, 'uct_type' => 'Student');
							$pginsert = $this->db->insert('user_course_type', $coudata);*/
					}//else close
					
								
		$this->load->view('courseenroll_confirm',$data);
		}//after validation else close
	}//post close
	}


	public function payustatus() {
		//echo "success";die;	
		$status = $this->input->post('status');

			$name = $this->input->post('firstname');
			//print_r($name);die;
			$ongwid = $this->input->post('address1');
		
		//echo "owid:";print_r($ongwid);die;
		
				$mailid = $this->input->post('email');
				$phoneno = $this->input->post('contact');
				//$school = $this->input->post('school');
				$rperson = $this->input->post('rperson');
				$amount = $this->input->post('amount');

				$txnid = $this->input->post('txnid');
        		$posted_hash = $this->input->post('hash');
        		$key = $this->input->post('key');
        		$productinfo = $this->input->post('productinfo');
		$cdate = date('Y-m-d');	
        	//$SALT = "eCwWELxi";	 //  Your salt
		$SALT =	SALT;
        	$add = $this->input->post('additionalCharges');
        	if(isset($add)) {
            		$additionalCharges = $this->input->post('additionalCharges');
            		$retHashSeq = $additionalCharges . '|' . $SALT . '|' . $status . '|||||||||||' . $mailid . '|' . $name . '|' . $productinfo . '|' . $amount . '|' . $txnid . '|' . $key;
            		//print_r($retHashSeq);die;
        	} else {

            		$retHashSeq = $SALT . '|' . $status . '|||||||||||' . $mailid . '|' . $name . '|' . $productinfo . '|' . $amount . '|' . $txnid . '|' . $key;
            		//print_r($retHashSeq);die;
        	}
          	$data['hash'] = hash("sha512", $retHashSeq);
          	$data['amount'] = $amount;
          	$data['txnid'] = $txnid;
          	$data['posted_hash'] = $posted_hash;
          	$data['status'] = $status;
          	if($status == 'success'){
	 		// update pg table
			$pgdata = array(
				//'ow_id' 	=> $owid,	
				'owpg_txnid' 	=> $txnid,	
				'owpg_pinfo' 	=> $productinfo,	
				'owpg_amount' 	=> $amount,	
				//'owpg_ftype'	=> $ftype,	
				'owpg_date' 	=> $cdate,	
				'owpg_gw'	=> 'payu',	
				'owpg_status'	=> $status,	
				//'aspg_txncode' 	=> ,
				//'aspg_reason' 	=>	
			);
			
			$pgupdate = $this->commodel->updaterec('ongoingworkshop_pg', $pgdata,'ow_id',$ongwid);

			$insertid = $this->db->insert_id(); 
			
	 		//call payment function for update record
		 	$pmathod = 'Online';
		 	$bank='PayuMoney';
		 		
		 	$this->payment($ongwid,$txnid,$amount,$bank,$status);
			//set the proper message
			//$message = '<h3>Online fees submit successfully.</h3>';
                	//$this->session->set_flashdata('msg',$message);
                	//$this->load->view('enterence/step_five', $data);   //this should go to step five for printing application page  with suitable message
         	}
         	else{
			// update pg table
			$pgdata = array(
				'ow_id' 	=> $ongwid,	
				'owpg_txnid' 	=> $txnid,	
				'owpg_pinfo' 	=> $productinfo,	
				'owpg_amount' 	=> $amount,	
				//'owpg_ftype'	=> $ftype,	
				'owpg_date' 	=> $cdate,	
				//'aspg_gw'	=> ,	
				'owpg_status'	=> $status,
				//'aspg_txncode' 	=> ,
				//'aspg_reason' 	=>	
			);
			$pgupdate=$this->commodel->insertrec('ongoingworkshop_pg', $pgdata);
			
			// set the status message
			$message = "Course enrollment successfully not done.";
            $this->session->set_flashdata('error', $message);
            redirect('Header/ongoingworkshop'); //this should go to confirmation page for retry to make payment with suitable message
         	}
     
   	 }

   	 public function payment($post1,$post2,$post3,$post4,$post5){
   	 		//print_r($post1);echo "<br>";print_r($post2);echo "<br>";print_r($post3);echo "<br>";print_r($post4);echo "<br>";print_r($post5);echo "<br>";die;

   	 	if(isset($this->session->userdata['su_id'])){
 			$owdata = array(
   	 	   				'owpg_id'   		=> $post1,
   	 	   				'ow_referenceno'    => $post2,
		 				'ow_amount'			=> $post3,	
						'ow_bankname' 		=> $post4,
						'ow_paymentstatus ' => $post5,

                	);
			$update = $this->commodel->updaterec('ongoingworkshop', $owdata,'ow_id',$post1);

			 if($update){
                    $confmes = "Your course enrollment successfully done.";
                    $this->session->set_flashdata('success',$confmes);
                    redirect('login/usr_login');
             }
             else{
               		$message = "Course enrollment successfully not done.".$mails;
                	$this->session->set_flashdata('error', $message);
                    //echo $this->email->print_debugger();
                    redirect('workshop/courseenroll');
             }

			redirect('workshop/courseenroll');
   	 	}
   	 	elseif(isset($this->session->userdata['su_userid'])){
   	 		//print_r($this->session->userdata['su_userid']);die;
   	 	
   	 		$suid = $this->session->userdata['su_userid'];

   	 		$email = $this->commodel->get_listspfic1('ongoingworkshop','ow_email','ow_id',$suid)->ow_email;
   	 		$name = $this->commodel->get_listspfic1('ongoingworkshop','ow_name','ow_id',$suid)->ow_name;
   	 		//$rstring = $this->commodel->get_listspfic1('sign_up','su_string','su_id',$uid)->su_string;
   	 		//$pawd = $this->commodel->get_listspfic1('sign_up','su_password ','su_id',$uid)->su_password ;

   	 	    $owdata = array(
   	 	   				'owpg_id'   		=> $post1,
   	 	   				'ow_referenceno'    => $post2,
		 				'ow_amount'			=> $post3,	
						'ow_bankname' 		=> $post4,
						'ow_paymentstatus' 	=> $post5,

                	);
			$update = $this->commodel->updaterec('ongoingworkshop', $owdata,'ow_id',$post1);

			//insert record exsist in sign up table
							$rstring = random_string('alnum',8);
							$signupdata = array (
								//'su_userid'			=> $owid,
                                'su_name' 			=> $name,
                                'su_emailid' 		=> $email,
                                'su_password' 		=> $rstring,
                                'su_status'			=> 'Verified',
                                //'su_howtoknow' 		=> $hknow,
                                //'su_string'			=> $rstring 
                            );
                            //$signinsert = $this->db->insert('sign_up',$signupdata);
                            $update = $this->commodel->updaterec('sign_up',$signupdata,'su_userid',$suid);
						//	$signupid = $this->db->insert_id();

						//	$userdata = ['su_userid' => $owid];
						//	$this->session->set_userdata($userdata);

					$subject = "Registered Successfully.";
					//$verifylink = base_url("login/verify/".$email.'/'.$rstring);
				//	$verifylink = base_url("login/verify/".$rstring);
					$message  = "<table width='50%'; style='border:1px solid #3A5896;background-color:#8470FF;color:white;font-size:18px;' align=center border=0>
					<tr><td></td></tr>
					<tr><td colspan=2><b>Your are registered successfully, Your details are given below. </td></tr>
					<tr height=15><td colspan=2></td></tr>
					<tr><td width=370><b>Username : </b></td><td align=left>".$email."</td></tr> 
					<tr><td><b>Password : </b> </td><td align=left>".$rstring. "</td><tr>
					
					
					</table> " ;

                        $mails=$this->mailmodel->mailsnd($email,$subject,$message,'');
                      // print_r($mails);die;	
                        if($mails){
                        		 $confmes = "Your course enrollment successfully done & username password send to your mail-id.";
                       			 $this->session->set_flashdata('success',$confmes);
                        		 redirect('login/signin');
                        }
                        else{
                        		$message = "Course enrollment successfully not done.".$mails;
                       			$this->session->set_flashdata('error', $message);
                       			// echo $this->email->print_debugger();
                        		redirect('workshop/courseenroll');
                        }

					
			}//elseif close

   	 }

	
}
