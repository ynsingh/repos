<?php
class Webhook extends CI_Controller {

        /******************************************************************************/
        public function __construct(){
                parent::__construct();
                $this->load->model("Mailsend_model","mailmodel");
                $this->load->model('Common_model',"commodel");
//                $this->load->library('form_validation');
  //              $this->load->library('session');
                $this->load->library('curl');
                $this->load->helper(array('url','form'));
                $this->load->helper('string');

	}

	public function index(){
		/*
			Basic PHP script to handle Instamojo RAP webhook.
		*/

		$data = $_POST;
		$mac_provided = $data['mac']; // Get the MAC from the POST data
		unset($data['mac']); // Remove the MAC key from the data.
		$ver = explode('.', phpversion());
		$major = (int) $ver[0];
		$minor = (int) $ver[1];
		if($major >= 5 and $minor >= 4){
			ksort($data, SORT_STRING | SORT_FLAG_CASE);
		}
		else{
			 uksort($data, 'strcasecmp');
		}
		// You can get the 'salt' from Instamojo's developers page(make sure to log in first): https://www.instamojo.com/developers
		// Pass the 'salt' without <>
		$mac_calculated = hash_hmac("sha1", implode("|", $data), "b6d389f975a446a2894e5c6634c9b001");
		if($mac_provided == $mac_calculated){
			 $payment_id = $data['payment_id'];
			 $status = $data['status'];
			 $amount = $data['amount'];
			 $purpose = $data['purpose'];
			 $mailid = $data['buyer'];

			 $paymentreq_id = $data['payment_request_id'];
			 //get the workshop code form purpose
			 $purpose=trim($purpose);
			 $parts = explode("-",$purpose);

			//update the data in ongoingworkshop and ongoingworkshop_pg
			$udata=array('ow_bankname' =>'InstaMojo', 'ow_referenceno' =>$payment_id, 'ow_paymentgateway' => 'InstaMojo','ow_paymentstatus'=>$status);
			$uflag=$this->commodel->updaterec('ongoingworkshop',$udata, 'ow_id', $parts[2]);

			$upgdata=array('ow_id'=>$parts[2], 'owpg_txnid'=>$payment_id,'owpg_pinfo'=>$purpose,'owpg_amount'=>$amount,'owpg_ftype'=>'Workshop','owpg_date'=>Date('Y-m-d'),'owpg_gw'=>'InstaMojo','owpg_status'=>$status,'owpg_txncode'=> $paymentreq_id,	'owpg_reason'=>' ');
			$upgflag=$this->commodel->insertrec('ongoingworkshop_pg',$upgdata);


			//getting email and password
			if (empty($mailid)||($mailid == "")){
				$mailid=$this->commodel->get_listspfic1('ongoingworkshop','ow_email','ow_id',$parts[2])->ow_email;
			}

			$pawd=$this->commodel->get_listspfic1('sign_up','su_password','su_emailid',$mailid)->su_password;
			$crsname = $this->commodel->get_listspfic1('courses','cou_name','cou_code',$parts[0])->cou_name;

			 if($data['status'] == "Credit"){
				//get start and end dates
				$couid = $this->commodel->get_listspfic1('user_course_type','uct_courseid','uct_userid',$parts[3])->uct_courseid;

				$whdata1=array('crsann_crsid' => $couid);
				$sdata1='crsann_crsstart,crsann_crsend,crsann_feedbkdate';
				$coursedate=$this->commodel->get_listspficemore('courseannouncement',$sdata1,$whdata1);
				//$cdate = date('Y-m-d');
				foreach($coursedate as $coudata){
    				$startdate = $coudata->crsann_crsstart;
    				$enddate   = $coudata->crsann_crsend;
    				$feedbackdate   = $coudata->crsann_feedbkdate;
    				//code for sending mail to user	
				$subject = "Registered Successfully";
                                     //   $pawd = random_string('alnum',6);
                                       // $erstring= $mailid.'---'.$rstring;
                                       // $verifylink = base_url("login/verify/".$erstring);

                                $message  = "<table width='50%'; style='border:1px solid #3A5896;color:black;font-size:18px;' align=center border=0>
                                        <tr><td></td></tr>
                                        <tr><td colspan=2><b>Welcome to “Annant Gyan”</td></tr>
                                        <tr><td colspan=2>You have successfully enrolled on online courses/ workshops of “Annant Gyan”. </td></tr>
                                        </table>

                                        <table  width='50%'; style='border:1px solid #3A5896;color:black;font-size:18px;' align=center border=0>
                                        <tr><td><b>Course start date: </b></td><td align=left>".$startdate."</td></tr>
                                        <tr><td><b>Course end date : </b> </td><td align=left>".$enddate. "</td><tr>
                                        <tr><td><b>Feedback date: </b> </td><td align=left>".$feedbackdate. "</td><tr>

                                        <tr><td><b>Email: </b></td><td align=left>".$mailid."</td></tr>
                                        <tr><td><b>Password : </b> </td><td align=left>".$pawd. "</td><tr>
                                        <tr><td><b>Course Name : </b> </td><td align=left>".$crsname." ( ".$purpose. " )</td><tr>
                                        <tr><td><b>Payment Status : </b></td><td align=left>" .$status."</a></td></tr>
                                        </table> 

                                        <table width='50%'; style='border:1px solid #3A5896;color:black;font-size:18px;' align=center border=0>
                                        	<tr><td align=left>".'Thanking you.'."</td></tr>
                                        	<tr><td align=left>".'Admin, Annant Gyan'. "</td><tr>
                                        	<tr><td align=left>".'www.annantgyan.com'."</td></tr>
                                        	<tr><td align=left>".'Mail id: annantgyan@gmail.com<br>
            										admin@annantgyan.com'. "</td><tr>
                                        </table>

                                        " ;
                            }//foreach close get start and end dates            	
                                $mails=$this->mailmodel->mailsnd($mailid,$subject,$message,'');


				//code for sending mail to admin
				$to = 'admin@annantgyan.com';
                                $subject = 'Website Payment Request ' .$data['buyer_name'].'';
                                $message = "<h1>Payment Details</h1>";
                                $message .= "<hr>";
                                $message .= '<p><b>ID:</b> '.$data['payment_id'].'</p>';
                                $message .= '<p><b>Amount:</b> '.$data['amount'].'</p>';
                                $message .= "<hr>";
                                $message .= '<p><b>Name:</b> '.$data['buyer_name'].'</p>';
                                $message .= '<p><b>Email:</b> '.$data['buyer'].'</p>';
                                $message .= '<p><b>Phone:</b> '.$data['buyer_phone'].'</p>';
                                $message .= '<p><b>Payment Status:</b> '.$data['status'].'</p>';

                                $message .= "<hr>";

                                $headers .= "MIME-Version: 1.0\r\n";
                                $headers .= "Content-Type: text/html; charset=ISO-8859-1\r\n";
                                // send email
                                $mails=$this->mailmodel->mailsnd($to,$subject,$message,'');

				$confmes = "You are registered successfully in " .$courname. " & mail sent to your registered email-id and Payment was successful.".$message;
			        $this->session->set_flashdata('success',$confmes);
				redirect('workshop/courseenroll_home');

 				// Payment was successful, mark it as successful in your database.
				 // You can acess payment_request_id, purpose etc here. 
 			}

 			else{
 				//update the failed transaction
				$message = "Payment was unsuccessful";
                       		$this->session->set_flashdata('error', $message);
                       		//echo $this->email->print_debugger();
                        	redirect('workshop/enroll_workshop');
				 // Payment was unsuccessful, mark it as failed in your database.
				 // You can acess payment_request_id, purpose etc here.
 			}
		}
		else{
			 echo "MAC mismatch";
		}
	}
}
?>
