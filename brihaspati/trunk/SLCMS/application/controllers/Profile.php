<?php

/* 
 * @name Profile.php
 * @author Deepika Chaudhary (chaudharydeepika88@gmail.com)
 * @author Sharad Singh (sharad23nov@gmail.com) modify view profile
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

                                $mails=$this->mailsend_model->mailsnd($data_user_name,$sub,$mess,'');
				if($mails){
		                       		$this->logger->write_logmessage("update"," Password change", " successfully" .$data_user_name);
                		       		$this->logger->write_dblogmessage("update","Password change", "successfully".$data_user_name );     
                       				$this->session->set_flashdata("success", 'Password has been changed successfully !! Mail sent Sucessfully !!' );
				}
                                else{
                       				$this->logger->write_logmessage("update"," Password change", " successfully" .$data_user_name);
                		       		$this->logger->write_dblogmessage("update","Password change", "successfully".$data_user_name );     
		                       		$this->session->set_flashdata("success", 'Password has been changed successfully !! Mail does not sent !!' );
                                }
                       		redirect ('profile/changepasswd');
                             }
                	}
        	}//else
     	   }//end function


public function viewprofile(){
        /* get logged user detail from different tables (firstname, lastname, email, address, mobile number, secondary email, campus name, org name)
         * using login model and common model
         */
	    $this->currentlog=$this->session->userdata('username');
        //$this->roleid=$this->common_model->get_listspfic1('user_role_type','roleid','userid');
        $this->roleid=$this->session->userdata('id_role');
        $this->currentrole=$this->common_model->get_listspfic1('role','role_name','role_id',$this->roleid);
        $this->name=$this->login_model->get_listspfic1('userprofile','firstname','userid');
        $this->lastn=$this->login_model->get_listspfic1('userprofile','lastname','userid');
        $this->address=$this->login_model->get_listspfic1('userprofile','address','userid');
	    $this->secmail=$this->login_model->get_listspfic1('userprofile','secmail','userid');
        $this->mobile=$this->login_model->get_listspfic1('userprofile','mobile','userid');
        $this->email=$this->login_model->get_listspfic1('edrpuser','email','id');
        $this->campusid=$this->common_model->get_listspfic1('user_role_type','scid','userid');
        $this->campusname=$this->common_model->get_listspfic1('study_center','sc_name','sc_id');
        $this->orgcode=$this->common_model->get_listspfic1('study_center','org_code','sc_id');
        $this->orgname=$this->common_model->get_listspfic1('org_profile','org_name','org_code');
        $this->load->view('profile/viewprofile');
}
 }//end class
