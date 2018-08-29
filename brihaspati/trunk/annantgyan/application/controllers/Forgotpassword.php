<?php

/*********************************************************** 
 * @name Forgotpassword.php                                *
 * @author Nagendra Kumar Singh  (nksinghiitk@gmail.com)   *
 ***********************************************************/
defined('BASEPATH') OR exit('No direct script access allowed');

class Forgotpassword extends CI_Controller
    {
    function __construct() {
	    parent::__construct();
                $this->load->library('email');
	        $this->load->model("Mailsend_model","mailmodel");
		$this->load->model('Common_model',"commodel");
		$this->load->helper('string');
    }

    public function resendactlink(){
	    	$this->form_validation->set_rules('username', 'User Name (Email)', 'trim|required|min_length[1]|max_length[255]');
	 	if ($_POST)
                {
                   $data['username']['value'] = $this->input->post('username', TRUE);
                }
		if ($this->form_validation->run() == FALSE)
                {
                        $this->load->view('forgotpassword/resendactlink');
                        return;
                }
	 	else
                {
                        $data_user_name = $this->input->post('username', TRUE);
			/* check if user exist */
			$whdata=array('su_emailid'=>$data_user_name,'su_status !='=>"Verified");
                	$check = $this->commodel->isduplicatemore('sign_up',$whdata);
                	if (!$check){
                        	$this->logger->write_logmessage("error","Either User Account does not exists or already activated/verified.!!!!". $data_user_name );
                        	$this->logger->write_dblogmessage("error","Either User Account does not exist or already activated/verified" . $data_user_name);
                        	$this->session->set_flashdata('error','Either User Account does not exists or already activated/verified.!!!!');
                        	redirect('forgotpassword/resendactlink');
                	}else{
				$rstring=$this->commodel->get_listspfic1('sign_up','su_string','su_emailid',$data_user_name)->su_string;
				$erstring= $email.'---'.$rstring;
				$verifylink = base_url("login/verify/".$erstring);
				$sub = "Activation link for the user account";
				$mess  = "<table width='50%'; style='border:1px solid #3A5896;background-color:#8470FF;color:white;font-size:18px;' align=center border=0>
                                        <tr><td></td></tr>
                                        <tr><td colspan=2><b>Your details are given below. </td></tr>
                                        <tr height=15><td colspan=2></td></tr>
                                        <tr><td width=370><b>Email: </b></td><td align=left>".$email."</td></tr>
                                        <tr><td><b>Password : </b> </td><td align=left>".$pawd. "</td><tr>

                                        <tr><td><b>Activation link : </b></td><td align=left> <a href=".$verifylink.">Activate your account</a>.</td></tr>

                                        </table> " ;

 				$mails=$this->mailmodel->mailsnd($data_user_name,$sub,$mess,'');
                                if($mails){
                                $this->logger->write_logmessage("update","Please check your email to activate account.", $data_user_name);
                                $this->logger->write_dblogmessage("update","Please check your email to activate account.", $data_user_name);
                                $this->session->set_flashdata("success", "Mail send to user for activate or verify account!");
//                              $this->load->view("Sign-In");
                                redirect('Sign-In');
                                }
                                else{
                                $this->session->set_flashdata("error", 'Mail can not send to user email. So contact to administrator.!!');
                                redirect('forgotpassword/resendactlink');
                                }
                       redirect('Sign-In');

                   }
               }
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
		$check = $this->commodel->isduplicate('sign_up','su_emailid',$data_user_name);
                if (!$check){
                        $this->logger->write_logmessage("error","User Account does not exists.!!!!". $data_user_name );
                       	$this->logger->write_dblogmessage("error","User Account does not exist" . $data_user_name);
                        $this->session->set_flashdata('error','User Account does not exists.!!!!');
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
			$this->commodel->deleterow('forgotPass','username',$data_user_name);
                        $insert_data = array(
                                'username' => $data_user_name,
                                'rkey'=> $slug,
                                'passdate' => $cdate,
                                'expdate' => $exdate,
                        );
			$emailflag=$this->commodel->insertrec('forgotPass', $insert_data);
                        if ( ! $emailflag)
                        {
                                $this->logger->write_logmessage("error","Error addding User key for forgot password -". $data_user_name );
                                $this->logger->write_dblogmessage("error", "Error adding User key for forgot password" . $data_user_name);
                                $this->session->set_flashdata('error'.'Error in Forgot password....');
                                redirect('forgotpassword/forgotpass');
                                return;
                        }else{
				// send mail to user
                              //  $secmail=$this->sismodel->get_listspfic1('employee_master','emp_secndemail','emp_email',$data_user_name)->emp_secndemail;
                                //if((!empty($secmail))&&($secmail != '')&&($secmail != null)){
                                  //      $mails=$this->mailmodel->mailsnd($secmail,$sub,$mess,'');
                                //}

				$mails=$this->mailmodel->mailsnd($data_user_name,$sub,$mess,'');
				if($mails){
                                $this->logger->write_logmessage("update","Please check your email to reset password.", "successfully" .$data_user_name);
                            	$this->logger->write_dblogmessage("update","Please check your email to reset password.", "successfully" .$data_user_name);
                                $this->session->set_flashdata("success", "Mail send to user for reset password...!!!");
//				$this->load->view("Sign-In");
				redirect('Sign-In');
                                }
				else{
				$this->session->set_flashdata("error", 'Mail can not send to user email. So contact to administrator.!!');
				redirect('forgotpassword/forgotpass');
				}
		       redirect('Sign-In');

                   }
               }
	}
	}//end function
	public function gennewpass(){
		$usid  = $this->session->userdata['su_id'];
		$this->emailid=$this->commodel->get_listspfic1('sign_up','su_emailid','su_id',$usid)->su_emailid;
		if(isset($_POST['newgenpass'])) {
			/* Form validations */
        	        $this->form_validation->set_rules('oldpassword', 'Old Password', 'trim|required|min_length[1]|max_length[255]');
                	$this->form_validation->set_rules('password', 'New Password', 'trim|required|min_length[1]|max_length[255]');
	                $this->form_validation->set_rules('cnfpassword', 'Confirm New Password', 'trim|required|min_length[1]|max_length[255]|matches[password]');
			 /* Re-populating form */
	                if ($_POST)
        	        {
                	        $data['user_name']['value'] = $this->input->post('user_name', TRUE);
                       		// $data['password']['value'] = $this->input->post('password', TRUE);
                        	//$data['cnfpassword']['value'] = $this->input->post('cnfpassword', TRUE);
                	}
	                if ($this->form_validation->run() == FALSE)
        	        {
                	        $this->load->view('forgotpassword/gennewpass',$data);
                        	return;
                	}
			else
                	{
				// get user name and old and both new password
				$data_user_name = $this->input->post('user_name', TRUE);
        	                $data_oldpassword = $this->input->post('oldpassword', TRUE);
                	        $data_password = $this->input->post('password', TRUE);
                        	$data_cnfpassword = $this->input->post('cnfpassword', TRUE);
				// check both are same
				if($data_password != $data_cnfpassword){
					$this->logger->write_logmessage("error","New and confirm Password mismatch", "New and confirm password does not match". $data_user_name );
                                                $this->logger->write_dblogmessage("error","New and confirm Password mismatich", "New and confirm password does not match". $data_user_name);
                                                $this->session->set_flashdata('err_message','Your New and confirm password is not matching !!');
                                                $this->load->view('forgotpassword/gennewpass',$data);
                                                return;
				}else{
					// check user name and old are matched if no return to change password page
					$passold=$this->commodel->get_listspfic1('sign_up','su_password','su_id',$usid)->su_password;
					if($passold != $data_oldpassword)
					{
						$this->logger->write_logmessage("error","Old Password failure", "Old password does not match". $data_user_name );
			                        $this->logger->write_dblogmessage("error","Old Password failure", "Old password does not match". $data_user_name);
        			                $this->session->set_flashdata('err_message','Your old password is not matching as per our record !!');
						$this->load->view('forgotpassword/gennewpass',$data);
	                        		return;
					}
					else{
						//if yes then update the password
						$update_data = array(
                                			//'su_password'=> md5($data_cnfpassword),
			                                'su_password'=> $data_cnfpassword,
                        			);

						$pflag=$this->commodel->updaterec('sign_up', $update_data,'su_emailid',$data_user_name);
						$mess = 'Your password has been reset. The new password is '.$data_cnfpassword;
			                        $sub = 'Your updated Password';
						if ( ! $pflag) {
			                                $this->logger->write_logmessage("error",'Error updating User Account - ' . $data_user_name);
                        			        $this->logger->write_dblogmessage("error", "Error updating password in User Account " . $data_user_name);
							$this->session->set_flashdata('err_message'.'Error updating User Account');
                        			        redirect('forgotpassword/gennewpass');
			                                return;
						}else{
				 			// send the mail
							$mails=$this->mailmodel->mailsnd($data_user_name,$sub,$mess,'');
							if($mails){
				                                 $this->logger->write_logmessage("success",'Your password reset sucessfully and mail send. Now you can login with new password'. $data_user_name);
        	                			         $this->logger->write_dblogmessage("success", "Updated password in user account called " . $data_user_name );
								 $this->session->set_flashdata("success", 'Your Password reset sucessfully and mail sent. Now you can login with new password!!!');
								 redirect('forgotpassword/gennewpass');									return;
							}
							else{
								$this->session->set_flashdata("success", 'Your Password reset sucessfully and mail does not sent. Now you can login with new password!!!');
								redirect('forgotpassword/gennewpass');
								return;
							}
						  }
					}
				}//else of password mismatch
			}//close else validation
		}//close of isset
		$this->load->view('forgotpassword/gennewpass');
                return;
	}

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
		$checkv = $this->commodel->get_listspfic1('forgotPass','rkey','username',$user_nm);
                if ( empty($checkv)){
                        $this->logger->write_logmessage("error", "User Account or key  does not exist" . $user_nm);
			$this->logger->write_dblogmessage("error", "Error adding User key for forgot password.".$user_nm);
                        $this->session->set_flashdata('error','User Account with reset key does not exists!!!');
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
				$this->session->set_flashdata('error','User Account with reset key either does not exists or does not match');
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
                $this->form_validation->set_rules('password', 'New Password', 'trim|required|min_length[1]|max_length[255]');
                $this->form_validation->set_rules('cnfpassword', 'Confirm New Password', 'trim|required|min_length[1]|max_length[255]|matches[password]');

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
                                //'su_password'=> md5($data_cnfpassword),
                                'su_password'=> $data_cnfpassword,
                        );

			$pflag=$this->commodel->updaterec('sign_up', $update_data,'su_emailid',$data_user_name);
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
                                 $this->logger->write_logmessage("success",'Your password reset sucessfully and mail send. Now you can login with new password'. $data_user_name);
                                 $this->logger->write_dblogmessage("success", "Updated password in user account called " . $data_user_name );
				 $this->session->set_flashdata("success", 'Your Password reset sucessfully and mail send. Now you can login with new password!!!');

                                // delete the reset code from forgot password table.
				$forgetflag=$this->commodel->deleterow('forgotPass', 'username', $data_user_name);
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
                            //    $secmail=$this->sismodel->get_listspfic1('employee_master','emp_secndemail','emp_email',$data_user_name)->emp_secndemail;
                              //  if((!empty($secmail))&&($secmail != '')&&($secmail != null)){
                                //        $mails=$this->mailmodel->mailsnd($secmail,$sub,$mess,'');
                               // }

                                // send mail to user
                                $this->mailmodel->mailsnd($data_user_name,$sub,$mess,'');
				redirect('Sign-In');
                                return;

			}
		}		
	}//end function
}//end class
