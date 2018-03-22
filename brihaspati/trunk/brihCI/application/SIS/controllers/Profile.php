<?php

/* 
 * @name Profile.php
 * @author Deepika Chaudhary (chaudharydeepika88@gmail.com)
 * @author Sharad Singh (sharad23nov@gmail.com) modify view profile
 * @author  Manorama Pal(palseema30@gmail.com) modify view profile
 * @author  Om Prakash(omprakashkgp@gmail.com) => Change Employee Password
 */

defined('BASEPATH') OR exit('No direct script access allowed');


class Profile  extends CI_Controller
    {
    function __construct() {
        parent::__construct();

		$this->load->model('Common_model',"commodel");
		$this->load->model('Login_model',"logmodel"); 
		$this->load->model("Mailsend_model","mailmodel");
                $this->load->model('SIS_model',"sismodel");
        if(empty($this->session->userdata('id_user'))) {
          $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('welcome');
         }
    }
public function changepasswd()
	{
	$data_user_name= $this->session->userdata('username');
	$passold=$this->logmodel->getpassword($data_user_name)->password;

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
                	$pflag=$this->logmodel->updaterec('edrpuser', $update_data,'username',$data_user_name);
			$sub='New Password';   
                        $mess='Your password has been changed. Your new password is '.$newpassword.' You can change it later on as per your convenience.';
	       		if ( ! $pflag)
                       	{
                       		$this->logger->write_logmessage("error","Error in update password ", "Error in change password update". $data_user_name );
	               		$this->logger->write_dblogmessage("error","Error in update password", "Error in change password update". $data_user_name);
                       		$this->session->set_flashdata('err_message'.'Error in change password....');
		       		redirect('profile/changepasswd');
			}else{

                                $mails=$this->mailmodel->mailsnd($data_user_name,$sub,$mess,'');
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
        $this->roleid=$this->session->userdata('id_role');
        $this->currentrole=$this->commodel->get_listspfic1('role','role_name','role_id',$this->roleid);
        $this->name=$this->logmodel->get_listspfic1('userprofile','firstname','userid',$this->session->userdata('id_user'));
        $this->lastn=$this->logmodel->get_listspfic1('userprofile','lastname','userid',$this->session->userdata('id_user'));
        $this->address=$this->logmodel->get_listspfic1('userprofile','address','userid',$this->session->userdata('id_user'));
	$this->secmail=$this->logmodel->get_listspfic1('userprofile','secmail','userid',$this->session->userdata('id_user'));
        $this->mobile=$this->logmodel->get_listspfic1('userprofile','mobile','userid',$this->session->userdata('id_user'));
        $this->email=$this->logmodel->get_listspfic1('edrpuser','email','id',$this->session->userdata('id_user'));
        $this->campusid=$this->sismodel->get_listspfic1('user_role_type','scid','userid',$this->session->userdata('id_user'))->scid;
       	$this->campusname=$this->commodel->get_listspfic1('study_center','sc_name','sc_id',$this->campusid);
        $this->orgcode=$this->commodel->get_listspfic1('study_center','org_code','sc_id',$this->campusid);
        $this->orgname=$this->commodel->get_listspfic1('org_profile','org_name','org_code',$this->orgcode->org_code);
        $this->load->view('profile/viewprofile');
}//end function
/* this function is used for update user profile */
	public function editprofile(){
		$id=$this->session->userdata('id_user');
		$profile_data_q=$this->logmodel->get_listrow('userprofile','userid', $id);
	        if ($profile_data_q->num_rows() < 1)
        	{
	        	redirect('profile/editprofile');
			return;
        	}
       		$profile_data = $profile_data_q->row();
 		/* Form fields */
          	$data['firstname'] = array(
            		'name' => 'firstname',
            		'id' => 'firstname',
            		'maxlength' => '50',
            		'size' => '40',
            		'value' => $profile_data->firstname,
            	);
            	$data['lastname'] = array(
            		'name' => 'lastname',
            		'id' => 'lastname',
            		'maxlength' => '50',
            		'size' => '40',
	    		'value' => $profile_data->lastname,
            	);
	    	$data['address'] = array(
            		'name' => 'address',
            		'id' => 'address',
            		'maxlength' => '50',
            		'size' => '40',
            		'value' => $profile_data->address,
	     	);
	        $data['secmail'] = array(
            		'name' => 'secmail',
            		'id' => 'secmail',
            		'maxlength' => '50',
            		'size' => '40',
            		'value' => $profile_data->secmail,
	     	);
	    	$data['mobile'] = array(
            		'name' => 'mobile',
            		'id' => 'mobile',
          		'maxlength' => '50',
           		'size' => '40',
            		'value' => $profile_data->mobile,
            	);
		$data['id'] = $id;
 		/*Form Validation*/

 		$this->form_validation->set_rules('firstname','First Name','trim|xss_clean|required');
 		$this->form_validation->set_rules('lastname','Last Name','trim|xss_clean|required');
        	$this->form_validation->set_rules('address','Address','trim|xss_clean|required');
 		$this->form_validation->set_rules('secmail','Secondary Email ID','trim|xss_clean|valid_email');
 		$this->form_validation->set_rules('mobile','Mobile','trim|xss_clean|required|max_length[12]|is_numeric');
		/* Re-populating form */
        	if ($_POST){
			$data['firstname']['value'] = $this->input->post('firstname', TRUE);
			$data['lastname']['value'] = $this->input->post('lastname', TRUE);
			$data['address']['value'] = $this->input->post('address', TRUE);
			$data['secmail']['value'] = $this->input->post('secmail', TRUE);
			$data['mobile']['value'] = $this->input->post('mobile', TRUE);
		}		
		if ($this->form_validation->run() ==FALSE )
      	        {
                	$this->session->set_flashdata(validation_errors(), 'error');
                	$this->load->view('profile/editprofile', $data);
			return;
       		 }else{
			$firstname = ucwords(strtolower($this->input->post('firstname', TRUE)));
			$lastname = ucwords(strtolower($this->input->post('lastname', TRUE)));
			$address = $this->input->post('address', TRUE);
			$secmail =$this->input->post('secmail', TRUE);
			$mobile = $this->input->post('mobile', TRUE);

		$logmessage = "";
        	        if($profile_data->firstname != $firstname)
            		$logmessage = $logmessage ." update first name " .$profile_data->firstname. " changed by " .$firstname;
	    		if($profile_data->lastname != $lastname)
            		$logmessage = $logmessage ." update last name" .$profile_data->lastname. " changed by " .$lastname;
	    		if($profile_data->address != $address)
            		$logmessage = $logmessage ." update address " .$profile_data->address. " changed by " .$address;
	    		if($profile_data->secmail != $secmail)
            		$logmessage = $logmessage ." update secondry mail " .$profile_data->secmail. " changed by " .$secmail;
	    		if($profile_data->mobile != $mobile)
            		$logmessage = $logmessage ." update mobile " .$profile_data->mobile. " changed by " .$mobile;

	       $update_data = array(
             		'firstname' => $firstname,
               		'lastname' => $lastname,
	      		'address' => $address,
               	        'secmail' => $secmail,
               		'mobile' => $mobile,
		);
		
		$profileflag=$this->logmodel->updaterec('userprofile', $update_data, 'userid', $id);
           		if(!$profileflag){
                		$this->logger->write_logmessage("error","Error in update profile", "Error in profile record update". $logmessage );
                		$this->logger->write_dblogmessage("error","Error in update profile ", "Error in profile record update". $logmessage );
                		$this->session->set_flashdata('err_message','Error updating profile - ' . $logmessage . '.', 'error');
                		$this->load->view('profile/editprofile', $data);
			return;
                	}
         	         else{
                		$this->logger->write_logmessage("update","Edit Profile", "Profile record updated successfully..". $logmessage );
               			$this->logger->write_dblogmessage("update","Edit Profile", "Profile record updated successfully..". $logmessage );
                		$this->session->set_flashdata('success','Profile record updated successfully...');
				redirect('profile/viewprofile');
			return;
        	        }
		}
		$this->load->view('profile/editprofile',$data);
	}//end function
        
 /* This function has been created for the change employee password */
    public function changeemppassword(){
	
         /* Form validations */
	    $this->form_validation->set_rules('empemail', 'Employee Email', 'trim|xss_clean|required|valid_email');
            $this->form_validation->set_rules('npasswd', 'Password', 'trim|xss_clean|required|min_length[5]');
          // $this->form_validation->set_rules('npasswd', 'Password', 'trim|xss_clean|required|min_length[5]|matches[renpasswd]');
          //  $this->form_validation->set_rules('renpasswd', 'Re Password', 'trim|xss_clean|required');
	 if($this->form_validation->run() == FALSE)
	 {

	   $this->load->view('profile/changeemppassword');
	 }
	 else{
	       $empemail = $this->input->post("empemail");
	       $npasswd = $this->input->post("npasswd");
		$roleid=$this->session->userdata('id_role');
        	$userid=$this->session->userdata('id_user');
        	$deptid = '';
        	$whdatad = array('userid' => $userid,'roleid' => $roleid);
        	$resu = $this->sismodel->get_listspficemore('user_role_type','deptid',$whdatad);
                foreach($resu as $rw){
                        $deptid=$rw->deptid;
                }
       // 	$whdata = '';
       // if (!empty($deptid))
         //       $whdata = array('emp_dept_code' => $deptid);
	
	     //  $renpasswd = $this->input->post("renpasswd");
	       $flagemail= $this->logmodel->isduplicate('edrpuser','username',$empemail );
	       if($flagemail == 0)
                   {
                      $this->session->set_flashdata('err_message','Your email Id is not matching as per our record !!');
                      redirect('profile/changeemppassword');
                   }
               else{
			if($roleid !=1){
				// get empid
				$empid = $this->logmodel->get_listspfic1('edrpuser','id','username',$empemail)->id;
				// check exist emp with dept
				$datawh = array('deptid' => $deptid,'userid'=>$empid);
				$flagedeptmail= $this->sismodel->isduplicatemore('user_role_type',$datawh);
				if($flagedeptmail == 0)
	                	{
        	              		$this->session->set_flashdata('err_message','This email Id does not matched as per our department record !!');
					$this->logger->write_dblogmessage("error","Error in update password", "Error in change password update". $empemail.' by '. $userid);
                	      		redirect('profile/changeemppassword');
                   		}
			}

                        $update_data = array(
                        'password'=>md5($npasswd),
                        );
                        $pflag=$this->logmodel->updaterec('edrpuser', $update_data,'username',$empemail);
                        $sub='New Password';
                        $mess='Your password has been changed. Your new password is '.$npasswd.' You can change it later on as per your convenience.';
                        if ( ! $pflag)
                        {
                                $this->logger->write_logmessage("error","Error in update password ", "Error in change password update". $empemail );
                                $this->logger->write_dblogmessage("error","Error in update password", "Error in change password update". $empemail);
                                $this->session->set_flashdata('err_message'.'Error in change password....');
                                redirect('profile/changeemppassword');
                        }else{
	
                             $mails=$this->mailmodel->mailsnd($empemail,$sub,$mess,'');
                             if($mails){
                                    $this->logger->write_logmessage("update"," Password change", " successfully" .$empemail);
                                    $this->logger->write_dblogmessage("update","Password change", "successfully".$empemail );
                                    $this->session->set_flashdata("success", 'Password has been changed successfully !! Mail sent Sucessfully !!' );
                                }
                                else{
                                     $this->logger->write_logmessage("update"," Password change", " successfully" .$empemail);
                                     $this->logger->write_dblogmessage("update","Password change", "successfully".$empemail );
                                     $this->session->set_flashdata("success", 'Password has been changed successfully !! Mail does not sent !!' );
                                }
                                redirect('profile/changeemppassword');
	            	    }
		      }
	       }//end validation
//	   $this->load->view('profile/changeemppassword');
       }//end function 
        
}//end class
