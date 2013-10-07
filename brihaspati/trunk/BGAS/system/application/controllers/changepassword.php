<?php

class changepassword extends Controller {

	function changepassword()
	{
		parent::Controller();
              

		if ( ! check_access('change password'))
		{
			$this->messages->add('Permission denied.', 'error');
			redirect('changepassword');
			return;
		}


	}

	function index()
	{
		$this->template->set('page_title', 'Password Settings');
                $this->load->helper('file');
		 $data_user_name= $this->session->userdata('user_name');
		
		 $db1=$this->load->database('login', TRUE);
                 $db1->from('bgasuser');
                 $db1->select('password')->where('username', $data_user_name);
                 $user_name1 = $db1->get();
                 foreach($user_name1->result() as $row)
                 {
                  $user_password = $row->password;
                  }


	        
		$data['old_password'] = array(
			'name' => 'old_password',
			'id' => 'old_password',
			'maxlength' => '100',
			'size' => '40',
			'value' => $user_password,
		);

           	$data['new_password'] = array(
			'name' => 'new_password',
			'id' => 'new_password',
			'maxlength' => '100',
			'size' => '40',
			'value' => '',
		);

          	$data['confirm_password'] = array(
			'name' => 'confirm_password',
			'id' => 'confirm_password',
			'maxlength' => '100',
			'size' => '40',
			'value' => '',
		);

	

                if ($_POST)
		{
			
			$data['old_password']['value'] = $this->input->post('old_password', TRUE);
			$data['new_password']['value'] = $this->input->post('new_password', TRUE);
			$data['confirm_password']['value'] = $this->input->post('confirm_password', TRUE);
                }

                /* Form validations */
                  
                        $this->form_validation->set_rules('old_password', 'Old Password', 'trim|required');
                        $this->form_validation->set_rules('new_password', 'New Password', 'trim|required|min_length[6]|matches     [confirm_password]'); 
                        $this->form_validation->set_rules('confirm_password', 'Confirm Password', 'trim|required');
               
              
       

                /* Validating form */
                if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('template', 'changepassword/password', $data);
			return;
		}
    
		else
		{
		
		        $data_old_password = $this->input->post('old_password', TRUE);
                        $data_new_password = $this->input->post('new_password', TRUE);
                        $data_confirm_password = $this->input->post('confirm_password', TRUE);
			
			
                          if( $data_new_password==$data_confirm_password)
                          {
				
                	$db1->trans_start();
                        $update_data = array(
                                                      
                       'password'=>md5($data_new_password),
                                                      
                                                  
               	      );

                      if ( ! $db1->where('username', $data_user_name)->update('bgasuser', $update_data))
       	                    {

                              $db1->trans_rollback();
                              $this->messages->add('Error in change password Account - ' . $user_name->username . '.', 'error');

                                                       
                              $this->template->load('template', 'changepassword/password/', $data);
                              return;
                                }
                             else{
                                 $db1->trans_complete();
                                 $this->messages->add('Password Changed - ' . $user_name->username . ' success');
                               
                                 redirect('changepassword/');
                                 return;
                                  }
                                                             
			            return;

			  
		        	}     
				else
				{
                       		$this->messages->add('Confirm password does not match', 'error');
				redirect('changepassword');
				}
				}
		      		return;
			}
}
