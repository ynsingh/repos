<?php
defined('BASEPATH') OR exit('No direct script access allowed');
class Mailsend_model extends CI_Model
{
 
    	function __construct() {
        	parent::__construct();
        	$this->load->database();
    	}
	// used for sending mail with and without attachment. parameter are  
	// tomail, subject, message body, file name with complete path
	public function mailsnd($tomal, $sub, $mess,$attach=''){
		//getting the values from database

		$emresult = $this->commodel->get_elist("email_setting");	
		//foreach($emresult as $email){
		if(!empty($emresult) > 0) {
			//print_r($emresult);die;
		//if($this->emresult->num_rows()>0){
		//The mail sending protocol.
		//$config['protocol'] = 'smtp';
		$config['protocol'] = $emresult->emailprotocol;
		// SMTP Server Address for Gmail.
		//$config['smtp_host'] = 'ssl://smtp.googlemail.com';
		$config['smtp_host'] = $emresult->emailhost;
		// SMTP Port - the port that you is required
		//$config['smtp_port'] = 465;
		$config['smtp_port'] = $emresult->emailport;
		// SMTP Username like. (abc@gmail.com)
		//$config['smtp_user'] = $sender_email;
		$config['smtp_user'] = $emresult->username;
		// SMTP Password like (abc***##)
		//$config['smtp_pass'] = $user_password;
		$config['smtp_pass'] = $emresult->password;
		// Load email library and passing configured values to email library
		$this->load->library('email', $config);
		$sender_email=$emresult->username;
		$username=$emresult->sendername;
		// Sender email address
		$this->email->from($sender_email, $username);
		//add html content
		$this->email->set_mailtype("html");
		// Receiver email address.for single email
		$this->email->to($tomal);
		//send multiple email
//		$this->email->to(abc@gmail.com,xyz@gmail.com,jkl@gmail.com);
		// Subject of email
		$this->email->subject($sub);
		// Message in email
		$this->email->message($mess);
		// attachment in mail
		//if ($attach !=''){
		//	$this->email->attach($attach);
//		$this->email->attach($buffer, 'attachment', 'report.pdf', 'application/pdf');
		//}
		// It returns boolean TRUE or FALSE based on success or failure
		if($this->email->send()){
			return true;
		}
		else{
			//echo $this->email->print_debugger(); 
			//die;
			return false;
		}
		}//close for if record exist
	//	return false;
	}//}

    	function __destruct() {
        	$this->db->close();
    	}
}

