<?php

/* 
 * @name Forgotpassword.php
 * @author Deepika Chaudhary (chaudharydeepika88@gmail.com)
 */
defined('BASEPATH') OR exit('No direct script access allowed');

class Forgotpassword extends CI_Controller
    {
    function __construct() {
        parent::__construct();
	$this->load->model('Login_model',"logmodel");
        $this->load->model("Mailsend_model","mailmodel");
	$this->load->model('Common_model',"commodel");
	$this->load->helper('string');
        }
	public function forgotpass(){
/* Form validations */
                $this->form_validation->set_rules('username', 'User Name (Email)', 'trim|required|min_length[1]|max_length[255]');
		if ($_POST)
                {
                   $data['username']['value'] = $this->input->post('username', TRUE);
                }
                if ($this->form_validation->run() == FALSE)
                {
                        $this->load->view('forgotpassword/forgotpass');
                        return;
                }
		else
                {
                        $data_user_name = $this->input->post('username', TRUE);
/* check if user exist */
		$check = $this->logmodel->isduplicate('edrpuser','username',$data_user_name);
                if (!$check){
                        $this->logger->write_logmessage("error","User Account does not exists.!!!!". $data_user_name );
                       	$this->logger->write_dblogmessage("error","User Account does not exist" . $data_user_name);
                        $this->session->set_flashdata('err_message','User Account does not exists.!!!!');
                        redirect('forgotpassword/forgotpass');
                }else{
// create message
                        $randstr = random_string('alnum', 16);
                        $slug = md5($data_user_name . $randstr);
			$sub= 'Reset your Password';
		        $mess = 'To reset your password please click the link below and follow the instructions:'."\r\n". site_url('forgotpassword/resetpassword/'. $data_user_name .'/'. $slug) ."\r\n". 'If you did not request to reset your password then please just ignore this email and no changes will occur'."\r\n". 'Note: This reset code will expire after two days.';
                        // store key to database
                        $cdate = date("Y-m-d h:i:s");
			$d=strtotime("+2 Days");
                        $exdate = date("Y-m-d h:i:s", $d);
			$this->logmodel->deleterow('forgotPass','username',$data_user_name);
                        $insert_data = array(
                                'username' => $data_user_name,
                                'rkey'=> $slug,
                                'passdate' => $cdate,
                                'expdate' => $exdate,
                        );
			$emailflag=$this->logmodel->insertrec('forgotPass', $insert_data);
                        if ( ! $emailflag)
                        {
                                $this->logger->write_logmessage("error","Error addding User key for forgot password -". $data_user_name );
                                $this->logger->write_dblogmessage("error", "Error adding User key for forgot password" . $data_user_name);
                                $this->session->set_flashdata('err_message'.'Error in Forgot password....');
                                redirect('forgotpassword/forgotpass');
                                return;
                        }else{
				$mails=$this->mailmodel->mailsnd($data_user_name,$sub,$mess,'');
				if($mails){
                                $this->logger->write_logmessage("update","Please check your email to reset password.", "successfully" .$data_user_name);
                            	$this->logger->write_dblogmessage("update","Please check your email to reset password.", "successfully" .$data_user_name);
                                $this->session->set_flashdata("success", "Mail send to user for reset password...!!!");
				$this->load->view("welcome_message");
                                }
				else{
				$this->session->set_flashdata("err_message", 'Mail can not send to user email. So contact to administrator.!!');
				redirect('forgotpassword/forgotpass');
				}
		       redirect('welcome');

                   }
               }
	}
}//end function

	public function resetpassword(){
		$user_nm = $this->uri->segment(3);
                if(!$user_nm)
                {
			$this->logger->write_logmessage("error","Invalid reset code");                
			$this->load->view('forgotpassword/forgotpass'); 
                        return;
                }
                $hash = $this->uri->segment(4);
                if(!$hash)
                {
		$this->logger->write_logmessage("error","Invalid reset code"); 
		$this->load->view('forgotpassword/forgotpass');
                        return;
                }
	/* Check duplicate entry table in login database*/
		$checkv = $this->logmodel->get_listspfic1('forgotPass','rkey','username',$user_nm);
                if ( empty($checkv)){
                        $this->logger->write_logmessage("error", "User Account or key  does not exist" . $user_nm);
			$this->logger->write_dblogmessage("error", "Error adding User key for forgot password.".$user_nm);
                        $this->session->set_flashdata('err_message','User Account with reset key does not exists!!!');
                        redirect('forgotpassword/forgotpass');
                        return;
                }
                else
                {
                        // get the values from db and compare with hash
                        foreach($checkv as $row)
                        {
                                $ukey = $row;
                        }
                        // matched db rkey with hash
                        if($hash == $ukey)
                        {
				$this->usid=$user_nm;			
				$this->load->view('forgotpassword/resetpassword');
                                return;

			}else{
                                $this->logger->write_logmessage("error", "User Account with reset key either does not exists or does not match." . $user_nm);
                                $this->logger->write_dblogmessage("error", "User Account or key either does not exist or does not match" . $user_nm);
				$this->session->set_flashdata('err_message','User Account with reset key either does not exists or does not match');
                                redirect('forgotpassword/forgotpass');
                                return;
                        }
		}
	}//end function
   public function changepasswd(){
	$data_user_name = $this->input->post('user_name', TRUE);
        $this->usid=$data_user_name;

                /* Form validations */
                //$this->form_validation->set_rules('user_name', 'User Name (Email)', 'trim|required|min_length[1]|max_length[255]');
                $this->form_validation->set_rules('password', 'Password', 'trim|required|min_length[1]|max_length[255]');
                $this->form_validation->set_rules('cnfpassword', 'Confirm Password', 'trim|required|min_length[1]|max_length[255]|matches[password]');

                /* Re-populating form */
                if ($_POST)
                {
                        //$data['user_name']['value'] = $this->input->post('user_name', TRUE);
                        $data['password']['value'] = $this->input->post('password', TRUE);
                        $data['cnfpassword']['value'] = $this->input->post('cnfpassword', TRUE);
                }
                if ($this->form_validation->run() == FALSE)
                {
		        $this->load->view('forgotpassword/resetpassword',$data);
                        return;
                }
		else
                {
                        // get user name and both password
                        // check both are same 
                        $data_user_name = $this->input->post('user_name', TRUE);
                        $data_password = $this->input->post('password', TRUE);
                        $data_cnfpassword = $this->input->post('cnfpassword', TRUE);

                        // then reset the password and update in database
                        $update_data = array(
                                //'username' => $data_user_name,
                                'password'=> md5($data_cnfpassword),
                        );

			$pflag=$this->logmodel->updaterec('edrpuser', $update_data,'username',$data_user_name);
			// send the mail with  new  password, create message
                        $mess = 'Your password has been reset. The new password is '.$data_cnfpassword;
                        $sub = 'Your updated Password';
			 if ( ! $pflag) {
                                $this->logger->write_logmessage("error",'Error updating User Account - ' . $data_user_name);
                                $this->logger->write_dblogmessage("error", "Error updating password in User Account " . $data_user_name);
				$this->session->set_flashdata('err_message'.'Error updating User Account');
                                redirect('forgotpassword/resetpassword');
                                return;
                        }else{
                                 $this->logger->write_logmessage("success",'Your Password reset sucessfully and mail send. Now you can login with new password'. $data_user_name);
                                 $this->logger->write_dblogmessage("success", "Updated password in user account called " . $data_user_name );
				 $this->session->set_flashdata("success", 'Your Password reset sucessfully and mail send. Now you can login with new password!!!');

                                // delete the reset code from forgot password table.
				$forgetflag=$this->logmodel->deleterow('forgotPass', 'username', $data_user_name);
                                if(!$forgetflag){
                                        $this->logger->write_logmessage("error",'Error delete User reset key' . $data_user_name);
                                        $this->logger->write_dblogmessage("error", "Error deleting reset in User Account " . $data_user_name);
                                        $this->session->set_flashdata('err_message'.'Error deleting reset in User Account');
					redirect('forgotpassword/resetpassword');
                                }else{
                                        $this->logger->write_logmessage("success",'User reset key is removed from the system' . $data_user_name);
	                              	$this->logger->write_dblogmessage("success","User reset key is removed from the system" . $data_user_name);
                                }
                                // send mail to user
                                $this->mailmodel->mailsnd($data_user_name,$sub,$mess,'');
				redirect('welcome');
                                return;

			}
		}		
	}//end function
}//end class
