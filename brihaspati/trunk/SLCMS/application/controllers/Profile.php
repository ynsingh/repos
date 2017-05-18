<?php

/* 
 * @name Profile.php
 * @author Deepika Chaudhary (chaudharydeepika88@gmail.com)
 */

defined('BASEPATH') OR exit('No direct script access allowed');


class Profile  extends CI_Controller
    {
    function __construct() {
        parent::__construct();
        $this->load->model("login_model");
        $this->load->model("mailsend_model");
        $this->load->model("common_model");
        if(empty($this->session->userdata('id_user'))) {
          $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('welcome');
         }
    }
public function changepasswd()
	{
	$data_user_name= $this->session->userdata('username');
	$passold=$this->login_model->getpassword($data_user_name)->password;

         /* Form validations */
	 $this->form_validation->set_rules('oldpassword', 'Old Password', 'trim|xss_clean|required');
         $this->form_validation->set_rules('newpassword', 'New Password', 'trim|xss_clean|required|min_length[5]|matches[confirmpassword]');
         $this->form_validation->set_rules('confirmpassword', 'New Password(Confirm)', 'trim|xss_clean|required');

         if ($this->form_validation->run() == FALSE)
         {
		$this->load->view('profile/changepasswd');
         }else{  
         	$oldpassword = $this->input->post('oldpassword', TRUE);
                $newpassword = $this->input->post('newpassword', TRUE);
                $confirmpassword = $this->input->post('confirmpassword', TRUE);
		if($passold != md5($oldpassword))
			{
		        $this->logger->write_logmessage("error","Old Password failure", "Old password does not match". $data_user_name );
                        $this->logger->write_dblogmessage("error","Old Password failure", "Old password does not match". $data_user_name);
			$this->session->set_flashdata('err_message','Your old password is not matching as per our record !!');
                        redirect('profile/changepasswd');
			}
		else{
                        $update_data = array(
                        'password'=>md5($newpassword),
                      	);
                	$pflag=$this->login_model->updaterec('edrpuser', $update_data,'username',$data_user_name);
			$sub='New Password';   
                        $mess='Your password has been changed. Your new password is '.$newpassword.' You can change it later on as per your convenience.';
	       		if ( ! $pflag)
                       	{
                       		$this->logger->write_logmessage("error","Error in update password ", "Error in change password update". $data_user_name );
	               		$this->logger->write_dblogmessage("error","Error in update password", "Error in change password update". $data_user_name);
                       		$this->session->set_flashdata('err_message'.'Error in change password....');
		       		redirect('profile/changepasswd');
                        }else{
                       		$this->logger->write_logmessage("update"," Password change", " successfully" .$data_user_name);
                       		$this->logger->write_dblogmessage("update","Password change", "successfully".$data_user_name );     
                                $this->mailsend_model->mailsnd($data_user_name,$sub,$mess,'');
                       		$this->session->set_flashdata("success", 'Password has been changed successfully !! Mail sent Sucessfully !!' );
                       		redirect ('profile/changepasswd');
                             }
                	}
        	}//else
     	   }//end function
}//end class
