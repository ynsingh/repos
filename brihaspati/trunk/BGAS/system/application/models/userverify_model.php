<?php
class Userverify_model extends Model{

function Userverify_model()
{
        parent::Model();
}
	function get_hash_value($user_email)
	{
		$db1=$this->load->database('login', TRUE);
                $db1->select('verification_code')->from('edrpuser')->where('email', $user_email);
                $user_result = $db1->get();
                $query = $user_result->result();
                foreach($query as $row)
                {
                        $confirm_code1 = $row->verification_code;
                }
		return $confirm_code1;
	}
 	function verifyEmailAddress($verification_code)
	{  
		$db1=$this->load->database('login', TRUE);
  		$val1 = "update edrpuser set is_verified ='1',verification_code = 'NULL' WHERE verification_code='$verification_code'";
  		$res1 = $db1->query($val1);
		return $db1->affected_rows($res1); 
 	} 
	function send_Verification_Email($data_user_email,$data_user_name,$data_user_password,$confirm_code)
	{
                $db1=$this->load->database('login', TRUE);
                $db1->select('*')->from('emailSetting');
                $emaildata= $db1->get();
                $email_q = $emaildata->result();
                foreach($email_q as $row)
                {
                        $email_protocol = $row->email_protocol;
                        $email_host = $row->email_host;
                        $email_port = $row->email_port;
                        $email_username = $row->email_username;
                        $email_password = $row->email_password;
                }
                $db1->close();
                // Set SMTP Configuration
                $config = Array(
                'protocol' => $email_protocol,
                'smtp_host' => $email_host,
                'smtp_port' => $email_port,
                'smtp_user' => $email_username, // change it to yours
                'smtp_pass' => $email_password, // change it to yours
                'mailtype' => 'html',
                'charset' => 'utf-8',
                'wordwrap' => TRUE
                );
                // Load CodeIgniter Email library
                $this->load->library('Email', $config);
                // Sometimes you have to set the new line character for better result
                $this->email->set_newline("\r\n");
                 // Set your email information
                $this->email->from($config['smtp_user'],"brihspti");
                $this->email->to($data_user_email);
                $this->email->subject('Email Verification');
		$message = 
			/*-----------email body start-----------*/
                       ' Dear BGAS User,
			<br>
			This mail is being sent, Since
                        your  Account has been created.
			Here are your login details.<br>
                        Email: '.$data_user_name.'<br>
                        Password: '.$data_user_password.'<br>
			<br>
			<br>
                        Please click this link to activate your account:<br>
			'. site_url('user/verify/'.$data_user_name.'/'.$confirm_code);
                        /*-------------email body ends-------------*/
               	$this->email->message($message);
		if(!($this->email->send()))
                {
                        $this->messages->add('Please Set the correct Email Configuration Settings---' . 'error');
			redirect('admin/user/');
                        return FALSE;
                }
                else {
                        //$this->messages->add('Your Mail Sucessfully send!---'. ' success');
			$this->messages->add('Thankyou! for Added New User Account - ' . $user_password .' A Confirmation email has been sent to - ' .$data_user_email. ' Please click on Activation Link to activate your Account'. '.', ' success');
                         redirect('admin/user/');
                         return TRUE;
                }
        }//send_mail
}//main

