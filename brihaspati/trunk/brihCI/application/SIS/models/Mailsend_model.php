<?php
defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name: Mailsend_model
 * @author: Nagendra Kumar Singh (nksinghiitk@gmail.com)
 * @author: Manorama Pal (palseema30@gmail.com) mail function for sending monthly payslip as a attachment.
 */

class Mailsend_model extends CI_Model
{
 
    	function __construct() {
        	parent::__construct();
        	$this->load->database();
                $this->load->model('SIS_model',"sismodel");
    	}
	// used for sending mail with and without attachment. parameter are  
	// tomail, subject, message body, file name with complete path
	public function mailsnd($tomal, $sub, $mess,$attach='',$modulename=''){
		if(empty($modulename)){
			$modulename="All";
		}
		//getting the values from database
                //$this->emresult = $this->commodel->get_elist("email_setting");	
                $this->getmdetail= $this->commodel->get_listrow('email_setting','modulename',$modulename);
                if($this->getmdetail->num_rows()>0){
                    $this->emresult= $this->getmdetail->row(); 
                    //if (count($this->emresult) > 0) {
                    //if($this->emresult->num_rows()>0){
                    //The mail sending protocol.
                    //$config['protocol'] = 'smtp';
                    $config['protocol'] = $this->emresult->emailprotocol;
                    // SMTP Server Address for Gmail.
                    //$config['smtp_host'] = 'ssl://smtp.googlemail.com';
                    $config['smtp_host'] = $this->emresult->emailhost;
                    // SMTP Port - the port that you is required
                    //$config['smtp_port'] = 465;
                    $config['smtp_port'] = $this->emresult->emailport;
                    // SMTP Username like. (abc@gmail.com)
                    //$config['smtp_user'] = $sender_email;
                    $config['smtp_user'] = $this->emresult->username;
                    // SMTP Password like (abc***##)
                    //$config['smtp_pass'] = $user_password;
                    $config['smtp_pass'] = $this->emresult->password;
                    // Load email library and passing configured values to email library
                    $this->load->library('email', $config);
                    $sender_email=$this->emresult->username;
                    $username=$this->emresult->sendername;
                    // Sender email address
                    $this->email->from($sender_email, $username);

                    // Receiver email address.for single email
                    $this->email->to($tomal);
                    //send multiple email
//                  $this->email->to(abc@gmail.com,xyz@gmail.com,jkl@gmail.com);
                    // Subject of email
                    $this->email->subject($sub);
                    // Message in email
                    $this->email->message($mess);
                    // attachment in mail
                    if ($attach !=''){
			$this->email->attach($attach);
//                      $this->email->attach($buffer, 'attachment', 'report.pdf', 'application/pdf');
                    }
                    // It returns boolean TRUE or FALSE based on success or failure
		    if($this->email->send()){
			return true;
                    }
                    else{
			return false;
                    }
		}//close for if record exist
		return false;
	}
        
        /*****************************function for sending mail with attachment*************************/ 
        public function mailAttachment($empid,$emppfno,$month,$year,$case){
            $sub=" Monthly Pay Slip";
            $mess="Dear Madam / Sir, "."\r\n"." Please find ".$month." " . $year." pay slip as attachment"." \r\n"
                . " this is computer generated pay slip if found, any data mismatched \r\n please contact Payroll Admin"." \r\n \r\n \r\n"
                . " Regards "."\r\n"." Payroll Admin";
         
            $dwlattachment=$this->sismodel->payslipAttachment($empid,$month,$year,$case);
            $desired_dir = 'uploads/SIS/Payslip/'.$year.'/'.$month;
            $Attachpth=$desired_dir.'/'.$emppfno.'.pdf';
            //echo "Attachfile===".$Attachpth;
            //die();
            $this->empmailid=$this->sismodel->get_listspfic1('employee_master','emp_secndemail','emp_id',$empid)->emp_secndemail;
            if(!empty($this->empmailid)){
                $mails=$this->mailmodel->mailsnd($this->empmailid,$sub,$mess,$Attachpth);
                if($mails){
                    $this->logger->write_logmessage("update"," Salary slip generated", " successfully" .$this->empmailid);
                    $this->logger->write_dblogmessage("update","Salary slip generated", "successfully".$this->empmailid);     
                    $this->session->set_flashdata("success", '  Salary slip generated successfully !! Mail sent Sucessfully !! [ Email ID : '.$this->empmailid. ' ]' );
                }
                else{
                    $this->logger->write_logmessage("update","Salary slip generated", " successfully" .$this->empmailid);
                    $this->logger->write_dblogmessage("update","Salary slip generated", "successfully".$this->empmailid );     
                    $this->session->set_flashdata("success", ' Salary slip generated successfully !! Mail does not sent !!' );
                }
            }   
        }
    

    	function __destruct() {
        	$this->db->close();
    	}
}

