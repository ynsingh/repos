<?php

class User extends Controller {

	function User()
	{
		parent::Controller();

		/* Check access */
		if ( ! check_access('administer'))
		{
			$this->messages->add('Permission denied.', 'error');
			redirect('');
			return;
		}

		return;
	}

	function index()
	{
		$this->load->helper('file');
		$this->template->set('page_title', 'Manage users');
		$this->template->set('nav_links', array('admin/user/add' => 'Add user'));

		/* Getting list of files in the config - users directory 
		$users_list = get_filenames($this->config->item('config_path') . 'users');
        	$data['users'] = array();
		if ($users_list)
		{
			foreach ($users_list as $row)
			{
				/* Only include file ending with .ini 
				if (substr($row, -4) == ".ini")
				{
					$ini_label = substr($row, 0, -4);
					$data['users'][$ini_label] = $ini_label;
				}
			}
		}*/  
                $data['users'] = array();
		$db1=$this->load->database('login', TRUE);
                $db1->select('id,username,email,role,status,accounts,aggtype')->from('bgasuser');

		$query = $db1->get();
                $config['total_rows'] =$db1->count_all('bgasuser');

                $data['users']= $query;
		$user_id='';

		$this->template->load('admin_template', 'admin/user/index', $data);
		$db1->close();
		return;
	}

	function add()
	{
		$this->load->library('validation');
		$this->template->set('page_title', 'Add user');

		/* Form fields */
		$data['user_name'] = array(
			'name' => 'user_name',
			'id' => 'user_name',
			'maxlength' => '100',
			'size' => '40',
			'value' => '',
		);

		$data['user_password'] = array(
			'name' => 'user_password',
			'id' => 'user_password',
			'maxlength' => '100',
			'size' => '40',
			'value' => '',
		);

		$data['user_email'] = array(
			'name' => 'user_email',
			'id' => 'user_email',
			'maxlength' => '100',
			'size' => '40',
			'value' => '',
		);

		$data['user_roles'] = array(
			"administrator" => "administrator",
			"manager"=> "manager",
			"accountant" => "accountant",
			"dataentry" => "dataentry",
			"guest" => "guest",
		);

		$data['active_user_role'] = "administrator";
		$data['user_status'] = TRUE;

		/* Accounts Form fields */
		$data['accounts_active'] = array('(All Accounts)');
		/* Getting list of files in the config - accounts directory */
		$accounts_list = get_filenames($this->config->item('config_path') . 'accounts');
		$data['accounts'] = array('(All Accounts)' => '(All Accounts)');

		$db1=$this->load->database('login', TRUE);
		$db1->select('dblable')->from('bgasAccData');
		$query = $db1->get();
                if ($query->num_rows() < 1)
                {
                	$this->messages->add('No Account exists.', 'error');
                        $this->template->load('admin_template', 'admin/manage/add', $data);
                        return;
                }
		else{
			foreach($query->result() as $row){
				$data['accounts'][$row -> dblable] = $row -> dblable;
			}
		}
		$db1->close();

/*		if ($accounts_list)
		{
			foreach ($accounts_list as $row)
			{
				/* Only include file ending with .ini *
				if (substr($row, -4) == ".ini")
				{
					$ini_label = substr($row, 0, -4);
					$data['accounts'][$ini_label] = $ini_label;
			}
			}
		}
*/
		/* Repopulating form */
		if ($_POST)
		{
			$data['user_name']['value'] = $this->input->post('user_name', TRUE);
			$data['user_password']['value'] = $this->input->post('user_password', TRUE);
			$data['user_email']['value'] = $this->input->post('user_email', TRUE);
			$data['active_user_role'] = $this->input->post('user_role', TRUE);
			$data['user_status'] = $this->input->post('user_status', TRUE);
			$data['accounts_active'] = $this->input->post('accounts', TRUE);
		}

		/* Form validations */
		//$this->form_validation->set_rules('user_name', 'Username', 'trim|required|min_length[2]|max_length[30]|alpha_numeric');
		$this->form_validation->set_rules('user_name', 'Username', 'trim|required|valid_email');

		$this->form_validation->set_rules('user_password', 'Password', 'trim|required');
		//$this->form_validation->set_rules('user_email', 'Email', 'trim|required|valid_email');
		$this->form_validation->set_rules('user_email', 'Email', 'trim|required|valid_email');

		$this->form_validation->set_rules('user_role', 'Role', 'trim|required');
		$this->form_validation->set_rules('user_status', 'Active', 'trim');

		/* Validating form */
		if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('admin_template', 'admin/user/add', $data);
			return;
		}
		else
		{
			$data_user_name = $this->input->post('user_name', TRUE);
			$data_user_password = $this->input->post('user_password', TRUE);
			$data_user_email = $this->input->post('user_email', TRUE);
			$data_user_role = $this->input->post('user_role', TRUE);
			$data_user_status = $this->input->post('user_status', TRUE);
			if ($data_user_status == 1)
				$data_user_status = 1;
			else
				$data_user_status = 0;
			$data_accounts = $this->input->post('accounts', TRUE);

			/* Forming account querry string */
			$data_accounts_string = '';
			if ( ! $data_accounts)
			{
				$this->messages->add('Please select account.', 'error');
				$this->template->load('admin_template', 'admin/user/add', $data);
				return;
			} else {
				if (in_array('(All Accounts)', $data_accounts))
				{
					$data_accounts_string = '*';
				} else {
					/* Filtering out bogus accounts */
					$data_accounts_valid = array_intersect($data['accounts'], $data_accounts);
					$data_accounts_string = implode(",", $data_accounts_valid);
				}
				$db1=$this->load->database('login', TRUE);
			//Check this user exist or not
			// if not then create account else skip
	//			{
          //                      $this->messages->add('Username already exists.', 'error');
            //                    $this->template->load('admin_template', 'admin/user/add', $data);
              //                  return;
	        //                }
			// Create new user
				$db1->trans_start();
				$insert_data = array(
                                                        'username' => $data_user_name,
                                                        'password'=>md5($data_user_password),
                                                        'email' => $data_user_email,
                                                        'role' =>$data_user_role,
                                                        'status' => $data_user_status,
                                                        'accounts'=>$data_accounts_string
                                                        );
				

                                                if ( ! $db1->insert('bgasuser', $insert_data))
                                                {

                                                        $db1->trans_rollback();
                                                        $this->messages->add('Error addding User Account - ' . $data_user_name . '.', 'error');

                                                        $this->logger->write_message("error", "Error adding User Account " . $data_user_name);
                                                        //$this->template->load('template', 'user/add');
							$this->template->load('admin_template', 'admin/user/add', '');
                                                        return;
                                                }
						else{
						$db1->trans_complete();

                                                        //$this->messages->add('Added User Account - ' . $data_user_name . ' success');
					 //added by @kanchan

                                        $message = "You are Added in Brihaspati General Accounting System- $data_user_name  && Your Role is---- $data_user_role";
                                        $subject = 'User Added';
                                        $CI =& get_instance();
                                        $CI->load->library('paymentreceipt');

                                        if($CI->paymentreceipt->send_mail($data_user_email,$subject,$message))
					$this->messages->add('Added User Account - ' . $data_user_name .  '----Mail Sucessfully send!---'. ' success');
			
				 redirect('admin/user/');

					return;	
					}

			}

			//$ini_file = $this->config->item('config_path') . "users/" . $data_user_name . ".ini";

			/* Check if user ini file exists 
			if (get_file_info($ini_file))
			{
				$this->messages->add('Username already exists.', 'error');
				$this->template->load('admin_template', 'admin/user/add', $data);
				return;
			}

			$user_details = "[user]" . "\r\n" . "username = \"" . $data_user_name . "\"" . "\r\n" . "password = \"" . $data_user_password . "\"" . "\r\n" . "email = \"" . $data_user_email . "\"" . "\r\n" . "role = \"" . $data_user_role . "\"" . "\r\n" . "status = \"" . $data_user_status . "\"" . "\r\n" . "accounts = \"" . $data_accounts_string . "\"" . "\r\n";
			$user_details_html = "[user]" . "<br />" . "username = \"" . $data_user_name . "\"" . "<br />" . "password = \"" . $data_user_password . "\"" . "<br />" . "email = \"" . $data_user_email . "\"" . "<br />" . "role = \"" . $data_user_role . "\"" . "<br />" . "status = \"" . $data_user_status . "\"" . "<br />" . "accounts = \"" . $data_accounts_string . "\"" . "<br />";
                      echo $user_details;
                      $this->messages->add('Tha values are ' . $data_user_name);
			/* Writing the connection string to end of file - writing in 'a' append mode 
			if ( ! write_file($ini_file, $user_details))
			{
				$this->messages->add('Failed to add user. Check if "' . $ini_file . '" file is writable.', 'error');
				$this->messages->add('You can manually create a text file "' . $ini_file . '" with the following content :<br /><br />' . $user_details_html, 'error');
				$this->template->load('admin_template', 'admin/user/add', $data);
				return;
			} else {
				$this->messages->add('Added user.', 'success');
				redirect('admin/user');
				return;
			}*/
		}
		$this->template->load('admin_template', 'admin/user/add', $data);
		$db1->close();
		return;
	}

	function edit($user_id =0)
	{
		$this->template->set('page_title', 'Edit user');
		$user_password='';
		$user_email='';
		$db1=$this->load->database('login', TRUE);
		$db1->from('bgasuser')->where('id', $user_id);
		$user_name1 = $db1->get();
		foreach($user_name1->result() as $row)
                                {
                             	$user_name = $row->username;       
				$user_password = $row->password;
				$user_email = $row->email;
                                }
		

		/* Form fields */
		$data['user_name'] = array(
                        'name' => 'user_name',
                        'id' => 'user_name',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => $user_name,
		
		);
		
		$data['user_password'] = array(
			'name' => 'user_password',
			'id' => 'user_password',
			'maxlength' => '100',
			'size' => '40',
			'value' => $user_password,
		);

		$data['user_email'] = array(
			'name' => 'user_email',
			'id' => 'user_email',
			'maxlength' => '100',
			'size' => '40',
			'value' => $user_email,
		);

		$data['user_roles'] = array(
			"administrator" => "administrator",
			"manager"=> "manager",
			"accountant" => "accountant",
			"dataentry" => "dataentry",
			"guest" => "guest",
		);

		$data['user_id'] = $user_id;
		
		$data['active_user_role'] = "";
		$data['user_status'] = TRUE;

		/* Accounts Form fields */
		$data['accounts_active'] = array('(All Accounts)');
		/*Getting list of files in the config - accounts directory */
		$accounts_list = get_filenames($this->config->item('config_path') . 'accounts');
		$data['accounts'] = array('(All Accounts)' => '(All Accounts)');

//		$db1=$this->load->database('login', TRUE);
                $db1->select('dblable')->from('bgasAccData');
                $query = $db1->get();
                if ($query->num_rows() < 1)
                {
                        $this->messages->add('No Account exists.', 'error');
                        $this->template->load('admin_template', 'admin/manage/edit', $data);
                        return;
                }
                else{
                        foreach($query->result() as $row){
                                $data['accounts'][$row -> dblable] = $row -> dblable;
                        }
                }
  //              $db1->close;
/*
		if ($accounts_list)
		{
			foreach ($accounts_list as $row)
			{
				//Only include file ending with .ini 
				if (substr($row, -4) == ".ini")
				{
					$ini_label = substr($row, 0, -4);
					$data['accounts'][$ini_label] = $ini_label;
				}
			}
		}
*/
		/* Repopulating form */
		if ($_POST)
		{
			$data['user_name']['value'] = $this->input->post('user_name', TRUE);
			$data['user_password']['value'] = $this->input->post('user_password', TRUE);
			$data['user_email']['value'] = $this->input->post('user_email', TRUE);
			$data['active_user_role'] = $this->input->post('user_role', TRUE);
			$data['user_status'] = $this->input->post('user_status', TRUE);
			$data['accounts_active'] = $this->input->post('accounts', TRUE);
		} else {
			/* Check if user ini file exists 
			if ( ! get_file_info($ini_file))
			{
				$this->messages->add('User file "' . $ini_file . '" does not exists.', 'error');
				redirect('admin/user');
				return;
			} else {
				/* Parsing user ini file 
				$active_users = parse_ini_file($ini_file);
				if ( ! $active_users)
				{
					$this->messages->add('Invalid user file.', 'error');
				} else {
					/* Check if all needed variables are set in ini file 
					if (isset($active_users['username']))
						$data['user_name'] = $user_name;
					else
						$this->messages->add('Username missing from user file.', 'error');

					if (isset($active_users['password']))
						$data['user_password']['value'] = $active_users['password'];
					else
						$this->messages->add('Password missing from user file.', 'error');

					if (isset($active_users['email']))
						$data['user_email']['value'] = $active_users['email'];
					else
						$this->messages->add('Email missing from user file.', 'error');

					if (isset($active_users['role']))
						$data['active_user_role'] = $active_users['role'];
					else
						$this->messages->add('Role missing from user file.', 'error');

					if (isset($active_users['status']))
						$data['user_status'] = $active_users['status'];
					else
						$this->messages->add('Status missing from user file.', 'error');

					if (isset($active_users['accounts']))
					{
						if ($active_users['accounts'] == "*")
						{
							$data['accounts_active'] = array('(All Accounts)');
						} else {
							$data['accounts_active'] = explode(",", $active_users['accounts']);
						}
					} else {
						$this->messages->add('Accounts missing from user file.', 'error');
					}
				}
			}*/
		}

		/* Form validations */
		$this->form_validation->set_rules('user_name', 'username', 'trim|required' . $user_id);
		$this->form_validation->set_rules('user_password', 'Password', 'trim|required' . $user_id);
		$this->form_validation->set_rules('user_email', 'Email', 'trim|required|valid_email' . $user_id);
		$this->form_validation->set_rules('user_role', 'Role', 'trim|required');
		$this->form_validation->set_rules('user_status', 'Active', 'trim');

		/* Validating form */
		if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('admin_template', 'admin/user/edit', $data);
			return;
		}
		else
		{
			$data_user_name = $this->input->post('user_name', TRUE);
			$data_user_password = $this->input->post('user_password', TRUE);
			$data_user_email = $this->input->post('user_email', TRUE);
			$data_user_role = $this->input->post('user_role', TRUE);
			$data_user_status = $this->input->post('user_status', TRUE);
			if ($data_user_status == 1)
				$data_user_status = 1;
			else
				$data_user_status = 0;
			$data_accounts = $this->input->post('accounts', TRUE);
			//print_r($data_accounts);
			$this->messages->add("Test=====>".$data_accounts); 
			/* Forming account querry string */
                        $data_accounts_string = '';
                        if ( ! $data_accounts)
                        {
                                $this->messages->add('Please select account.', 'error');
                                $this->template->load('admin_template', 'admin/user/edit', $data);
                                return;
                        } else {
                                if (in_array('(All Accounts)', $data_accounts))
                                {
                                        $data_accounts_string = '*';
                                } else {
                                        /* Filtering out bogus accounts */
                                        $data_accounts_valid = array_intersect($data['accounts'], $data_accounts);
                                        $data_accounts_string = implode(",", $data_accounts_valid);
                                }
                        }
			//echo $data_accounts_string;
			$this->messages->add($data_accounts_string);
                         $db1->trans_start();
                         $update_data = array(
                                             'username' => $data_user_name,						 
					    'password'=>md5($data_user_password),
                                            'email' => $data_user_email,
                                            'role' =>$data_user_role,
                                            'status' => $data_user_status,
					    'accounts' => $data_accounts_string,	
                                             );

                         if ( ! $db1->where('id', $user_id)->update('bgasuser', $update_data))
                           {

                                      $db1->trans_rollback();
                                      $this->messages->add('Error in updating User Account - ' .  $user_name . '.', 'error');

                                                       
                                                        return;
                                                }
                                                else{
                                                $db1->trans_complete();
						$this->messages->add('Update User Account - ' . $user_name . ' success');

                                                //added by @kanchan
                                        	//print_r($user_email);

                                        $data_user_email = $update_data['email'];
                                        $data_user_name = $update_data['username'];

                                        $message = "Your Account has been Updated/n .................Your username- $data_user_name   Password- $data_user_password Your Role- $data_user_role && Account- $data_accounts_string";
                                        $subject = 'User Updated';
                                        $CI =& get_instance();
                                        $CI->load->library('paymentreceipt');
                                        if($CI->paymentreceipt->send_mail($data_user_email,$subject,$message))
                                        {
                                                redirect('admin/user');
                                                //return; 
                                        }
    
                                           	}//else
                                    
                                        return;


			/* Forming account querry string 
			$data_accounts_string = '';
			if ( ! $data_accounts)
			{
				$this->messages->add('Please select account.', 'error');
				$this->template->load('admin_template', 'admin/user/edit', $data);
				return;
			} else {
				if (in_array('(All Accounts)', $data_accounts))
				{
					$data_accounts_string = '*';
				} else {
					/* Filtering out bogus accounts 
					$data_accounts_valid = array_intersect($data['accounts'], $data_accounts);
					$data_accounts_string = implode(",", $data_accounts_valid);
				}
			}

			//$ini_file = $this->config->item('config_path') . "users/" . $user_name . ".ini";

			$user_details = "[user]" . "\r\n" . "username = \"" . $user_name . "\"" . "\r\n" . "password = \"" . $data_user_password . "\"" . "\r\n" . "email = \"" . $data_user_email . "\"" . "\r\n" . "role = \"" . $data_user_role . "\"" . "\r\n" . "status = \"" . $data_user_status . "\"" . "\r\n" . "accounts = \"" . $data_accounts_string . "\"" . "\r\n";
			$user_details_html = "[user]" . "<br />" . "username = \"" . $user_name . "\"" . "<br />" . "password = \"" . $data_user_password . "\"" . "<br />" . "email = \"" . $data_user_email . "\"" . "<br />" . "role = \"" . $data_user_role . "\"" . "<br />" . "status = \"" . $data_user_status . "\"" . "<br />" . "accounts = \"" . $data_accounts_string . "\"" . "<br />";
              
			/* Writing the connection string to end of file - writing in 'a' append mode 
			if ( ! write_file($ini_file, $user_details))
			{
				$this->messages->add('Failed to edit user. Check if "' . $ini_file . '" file is writable.', 'error');
				$this->messages->add('You can manually edit the text file "' . $ini_file . '" with the following content :<br /><br />' . $user_details_html, 'error');
				$this->template->load('admin_template', 'admin/user/edit', $data);
				return;
			} else {
				$this->messages->add('Updated user.', 'success');
				redirect('admin/user');
				return;
			}*/
		}
		$db1->close();
		return;
	}


	function delete($user_id)
	{
		$this->template->set('page_title', 'Delete user');
	//	echo"$user_id";	
		/* Get the User details */
		$db1=$this->load->database('login', TRUE);
		$db1->from('bgasuser')->where('id', $user_id);
		$budget_q = $db1->get();
		if ($budget_q->num_rows() < 1)
		{
			$this->messages->add('Invalid Budget account.', 'error');
			redirect('admin/user/');
			return;
		} else {
			$budget_data = $budget_q->row();
			$user_email = $budget_data->email;
                        $user_name = $budget_data->username;
		}
			
			$db1->trans_start();

		         if ( ! $db1->delete('bgasuser', array('id' => $user_id)))
                             {

                                $db1->trans_rollback();
                            	$this->messages->add('Error delete User Account - ' . $budget_data->username . '.', 'error');

                                $this->logger->write_message("error", "Error delete User Account " . $budget_data->username);
                              
					  redirect('admin/user/');

                                    return;
                            }
                               else{
                             $db1->trans_complete();
			     $this->messages->add('delete User Account - ' . $budget_data->username . '.', 'success');

			     //added by @kanchan

                                        $message = "Your Account has been deleted from BGAS Account- $user_name";
                                        $subject = 'User Deleted';
                                        $CI =& get_instance();
                                        $CI->load->library('paymentreceipt');
                                        if($CI->paymentreceipt->send_mail($user_email,$subject,$message))
                                        {
                                                redirect('admin/user');
                                                return;
                                        }

                             redirect('admin/user/');

                             return;
                                                }
		$db1->close();
	//	$ini_file = $this->config->item('config_path') . "users/" . $user_name . ".ini";
	//	$this->messages->add('Delete ' . $ini_file . ' file manually.', 'error');
		redirect('admin/user');
		return;
	}
        function permission($user_id =0)
        {
                $user_id;
                $this->template->set('page_title', 'User Permission');
                $user_password='';
                $user_email='';
                $options;
                $data['accounts1'] = array('name' => 'user_name');
                $user_accounts = array();
                $db1=$this->load->database('login', TRUE);
                $db1->from('bgasuser')->where('id', $user_id);
                $user_name1 = $db1->get();
                foreach($user_name1->result() as $row)
                {
                        $user_name = $row->username;
                        if($user_name=='guest')
                        {
                                $this->messages->add('Permission denied.', 'error');
                                redirect('admin/user');
                                return;
                        }

                        $new_id = $row->accounts; echo "<br>";

                        // if user account is '*' then get all accounts 
                        $count=0;
                        if($new_id == '*')
                        {
                                $db1->from('bgasAccData');
                                $accname = $db1->get();
                                //print_r(sizeof($accname->result()));

                                foreach($accname->result() as $row1)
                                {
                                        $var1=$row1->dblable;
                                        $value[$count]=$row1->dblable;
                                        $count++;
                                }
                                $data['accounts'] = $value;

                        }
                        else
                        {
                                $options[$new_id] = $row->accounts; echo "<br>";
                                $my_values = explode(',',$new_id); echo "<br>";
                                //print_r($my_values);
                                $data['accounts'] = $my_values;
                        }
                        $user_email = $row->email;
                }



                /* Form fields */
                $data['user_name'] = array(
                        'name' => 'user_name',
                        'id' => 'user_name',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => $user_name,
                        'readonly' =>'readonly',

                );
                $data['user_email'] = array(
                        'name' => 'user_email',
                        'id' => 'user_email',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => $user_email,
                        'readonly' =>'readonly',
                );


                $data['user_id'] = $user_id;
                $data['user_status'] = TRUE;
                /* Accounts Form fields */
                $data['accounts_active'] = array('(All Accounts)');

                /* Repopulating form */
                if ($_POST)
                {
                        $data['user_name']['value'] = $this->input->post('user_name', TRUE);
                        //$data['user_password']['value'] = $this->input->post('user_password', TRUE);
                        $data['user_email']['value'] = $this->input->post('user_email', TRUE);
//                        $data['active_user_role'] = $this->input->post('user_role', TRUE);
                        $data['user_status'] = $this->input->post('user_status', TRUE);
                        $data['accounts_active'] = $this->input->post('accounts', TRUE);
                } 

                /* Form validations */
                $this->form_validation->set_rules('user_name', 'username', 'trim|required' . $user_id);
                $this->form_validation->set_rules('user_email', 'Email', 'trim|required|valid_email' . $user_id);
                $this->form_validation->set_rules('user_status', 'Active', 'trim');

                /* Validating form */
                if ($this->form_validation->run() == FALSE)
                {
                        $this->messages->add(validation_errors(), 'error');
                        $this->template->load('admin_template', 'admin/user/permission', $data);
                        return;
                }
                else
                {
                        $data_user_name = $this->input->post('user_name', TRUE);
                       // $data_user_password = $this->input->post('user_password', TRUE);
                        $data_user_email = $this->input->post('user_email', TRUE);
                        $data_user_role = $this->input->post('user_role', TRUE);
                        $data_user_status = $this->input->post('user_status', TRUE);
                        if ($data_user_status == 1)
                                $data_user_status = 1;
                        else
                                $data_user_status = 0;
                        $data_accounts = $this->input->post('accounts', TRUE);

                        /* Forming account querry string */
                        $data_accounts_string = '';
                        if ( ! $data_accounts)
                        {
                                $this->messages->add('Please select account.', 'error');
                                $this->template->load('admin_template', 'admin/user/permission', $data);
                                return;
                        } else {
                                if (in_array('(All Accounts)', $data_accounts))
                                {
                                        $data_accounts_string = '*';
                                } else {
                                        /* Filtering out bogus accounts */
                                        $data_accounts_valid = array_intersect($data['accounts'], $data_accounts);
                                        $data_accounts_string = implode(",", $data_accounts_valid);
                                }
                        }


                        }
                 $this->template->load('admin_template', 'admin/user/assignpermission');
        return;


        }
        function assignpermission($user_name,$accountname)
        {
                $this->load->library('general');
                $this->load->model('Ledger_model');
                $data['account_name'] = $accountname;
                //$ini_file = $this->config->item('config_path') . "accounts/" . $accountname . ".ini"; 

               /* $db1=$this->load->database('login', TRUE);
                $db1->from('bgasAccData')->where('dblable', $accountname);
                $accdetail = $db1->get();
                foreach ($accdetail->result() as $row)
                {
                        $databasehost=$row->hostname;
                        $databasename= $row->databasename;
                        $databaseport=$row->port;
                        $databaseusername=$row->uname;
                        $databasepassword=$row->dbpass;
                }
                $new_link = @mysql_connect($databasehost . ':' . $databaseport, $databaseusername, $databasepassword);
                if ($new_link)
                {
                        $db_selected = mysql_select_db($databasename, $new_link);
                        if ($db_selected) {

                        }
                }*/
                $data['user_name']=$user_name;
                $data['accountname'] = $accountname;
                $this->template->load('admin_template','/admin/user/assignpermission',$data);

        }
        function addpermission($user_name,$id,$accountname,$htype)
        {
                $data['accountname'] = $accountname;
                $data['id']=$id;
                $data['user_name']= $user_name;
                $data['htype']=$htype;
                $db1=$this->load->database('login', TRUE);
                $db1->from('bgasAccData')->where('dblable', $accountname);
                $accdetail = $db1->get();
                foreach ($accdetail->result() as $row)
                {
                        $databasehost=$row->hostname;
                        $dbname= $row->databasename;
                        $databaseport=$row->port;
                        $databaseusername=$row->uname;
                        $databasepassword=$row->dbpass;
                }
                $new_link = @mysql_connect($databasehost . ':' . $databaseport, $databaseusername, $databasepassword);
                if ($new_link)
                {
                        $db_selected = mysql_select_db($dbname, $new_link);
                        if ($db_selected) {

                        }
                }
                $query = "select name from groups where id='$id'";
                $result = mysql_query($query);
                $row = mysql_fetch_assoc($result);
                $headn = $row['name'];
                $data['name']=$headn;
                $this->template->load('admin_template','/admin/user/addpermission',$data);
                return;
        }
        function permitpermission($htype,$id)
        {
                if ($_POST)
                {
                        $data['account_name']['value'] = $this->input->post('account_name', TRUE);
                        $data['user_name']['value'] = $this->input->post('user_name', TRUE);
                        $data['head_name']['value'] = $this->input->post('head_name', TRUE);
                        //$data['account_name']['value'] = $this->input->post('account_name', TRUE);
                        $data['type']['value'] = $this->input->post('type', TRUE);
                }
                $this->form_validation->set_rules('account_name', 'Account Name', 'trim|required');
                $this->form_validation->set_rules('user_name', 'User Name', 'trim|required');
                $this->form_validation->set_rules('head_name', 'Head Name', 'trim|required');
                $this->form_validation->set_rules('type', '', 'trim|required');
                if ($this->form_validation->run() == FALSE)
                {
                        $this->messages->add(validation_errors(), 'error');
                        $this->template->load('admin_template', 'admin/user/addpermission', $data);
                        return;
                }
                else
                {
                        $account_name = $this->input->post('account_name', TRUE);
                        $user_name = $this->input->post('user_name', TRUE);
                        $head_name = $this->input->post('head_name', TRUE);
                        $type = $this->input->post('type', TRUE);

                        $db1=$this->load->database('login', TRUE);
                        $db1->from('bgasAccData')->where('dblable', $account_name);
                        $accdetail = $db1->get();
                        foreach ($accdetail->result() as $row)
                        {
                                $databasehost=$row->hostname;
                                $dbname= $row->databasename;
                                $databaseport=$row->port;
                                $databaseusername=$row->uname;
                                $databasepassword=$row->dbpass;
                        }
                        $new_link = @mysql_connect($databasehost . ':' . $databaseport, $databaseusername, $databasepassword);
                        if ($new_link)
                        {
                                $db_selected = mysql_select_db($dbname, $new_link);
                                if ($db_selected) {
					
                                }
                        }


                        $tt=1;
                        //$query = sprintf('INSERT INTO bgas_acl '.'(id,username,groupid,roleid,ptype,atype) '.'VALUES ('','$user_name','','','','')');
                        $query = "INSERT INTO bgas_acl"."(username,headid,roleid,ptype,atype)" . "VALUES ('$user_name','$id','1','$type','$htype')";
                        $result = mysql_query($query);
                        if (!$result) {

                               // $message  = 'Invalid query: ' . mysql_error() . "\n";
                               // $message .= 'Whole query: ' . $query;
                               // die($message);
                        }
                $data['accountname']=$account_name;
                $data['user_name']=$user_name;
                $this->messages->add('Permission assign to Group Head:- '.$head_name);
                $this->template->load('admin_template','/admin/user/assignpermission',$data);
                return;
                }


        }
        //assign permission to a user for all heads.
        function allpermission($user_name,$accountname,$user_email)
        {
                $this->load->library('general');
                $this->load->model('Ledger_model');
                $data['account_name'] = $accountname;
                $db1=$this->load->database('login', TRUE);

                $db1->from('bgasAccData')->where('dblable', $accountname);
                $accdetail = $db1->get();
                foreach ($accdetail->result() as $row)
                {
                        $databasehost=$row->hostname;
                        $dbname= $row->databasename;
                        $databaseport=$row->port;
                        $databaseusername=$row->uname;
                        $databasepassword=$row->dbpass;
                }
                $new_link = @mysql_connect($databasehost . ':' . $databaseport, $databaseusername, $databasepassword);
                //echo $new_link;
                if ($new_link)
                {
                        $db_selected = mysql_select_db($dbname, $new_link);
                                if ($db_selected) {


                                }
                }

                $type=3;
                //echo $query = "INSERT INTO bgas_acl"."(username,headid,roleid,ptype,atype)" . "VALUES ('$user_name','*','1','$type','grp')";
                $query = "INSERT INTO bgas_acl"."(username,headid,roleid,ptype,atype)" . "VALUES ('$user_name','*','1','$type','grp')";
                $result = mysql_query($query);
                $data['user_name']=$user_name;
                $data['accountname'] = $accountname;
                $data['user_email'] = $user_email;
                $this->messages->add('Permission Assign Succesfully to the Account - '.$accountname);
                redirect('/admin/user');
                return;
        }
        function removepermission($user_name,$id,$accountname,$name)
        {
                $this->load->library('general');
                $this->load->model('Ledger_model');
                $data['account_name'] = $accountname;
                $db1=$this->load->database('login', TRUE);

                $db1->from('bgasAccData')->where('dblable', $accountname);
                $accdetail = $db1->get();
                foreach ($accdetail->result() as $row)
                {
                        $databasehost=$row->hostname;
                        $dbname= $row->databasename;
                        $databaseport=$row->port;
                        $databaseusername=$row->uname;
                        $databasepassword=$row->dbpass;
                }
                $new_link = @mysql_connect($databasehost . ':' . $databaseport, $databaseusername, $databasepassword);
                //echo $new_link;
                if ($new_link)
                {
                        $db_selected = mysql_select_db($dbname, $new_link);
                        if ($db_selected) {

//                                }
 //               }

                		$type=3;

		                $query = "delete from bgas_acl where headid='$id' and username='$user_name'";
                		$result = mysql_query($query);
	                	$data['user_name']=$user_name;
	        	        $data['accountname'] = $accountname;
        	        	$data['user_email'] = $user_email;
                		$this->messages->add('Permission Remove Succesfully to the Code - '.$name);
		                $this->template->load('admin_template','/admin/user/assignpermission',$data);
				mysql_close($new_link);
                		return;
		
        		}
		}

	}
	function makeaggregator($user_id =0)
 	{
		$this->template->set('page_title', 'Assign Aggregator');
		$data['accounts1'] = array('name' => 'user_name');
                $user_accounts = array();
                $db1=$this->load->database('login', TRUE);
                $db1->from('bgasuser')->where('id', $user_id);
                $user_name1 = $db1->get();
		$data['accounts_active'] = array('(All Accounts)');
		$data['accounts'] = array('(All Accounts)' => '(All Accounts)');
                foreach($user_name1->result() as $row)
                {
                        $user_name = $row->username;
			$userrole = $row->role;
                        if($user_name=='guest')
                        {
                                $this->messages->add('Permission denied.', 'error');
                                redirect('admin/user');
                                return;
                        }
			$count=0;			
			$db1->from('bgasAccData');
                        $bgasacc=$db1->get();

                        foreach($bgasacc->result() as $row1)
                        {
                               $var1=$row1->dblable;
                               $value[$row1->dblable]=$row1->dblable;
                               $count++;
 
                        }
				
                                $data['accounts'] = $value;
				$data['role'] = $userrole;
				$data['user_name'] = $user_name;
		}
		$this->template->load('admin_template', 'admin/user/makeaggregator',$data);

	}




	function aggregatoraccounts($username)
	{
		if ($_POST)
		{
			$data['accounts'] = $this->input->post('accounts', TRUE);
		}
			$data_accounts = $this->input->post('accounts', TRUE);

			$data_accounts_string = '';
                        if ( ! $data_accounts)
                        {
                                $this->messages->add('Please select account.', 'error');
                                $this->template->load('admin_template', 'admin/user/edit', $data);
                                return;
                        } else {
                                /*if (in_array('(All Accounts)', $data_accounts))
                                {
                                        $data_accounts_string = '*';
                                } else {*/
                                        $data_accounts_valid = array_intersect($data['accounts'], $data_accounts);
                                        $data_accounts_string = implode(",", $data_accounts_valid);
					$insert_data=array('username'=>$username,'accounts'=>$data_accounts_string);
					$tablead="aggregateaccounts";
					
					//insert valuse in aggregateaccounts

					$db1=$this->load->database('login', TRUE);
					$db1->insert($tablead, $insert_data);

					$tablebgu="bgasuser";
					$update_data=array('aggtype'=>'agg');
					$db1->where('username',$username )->update('bgasuser', $update_data);
					$this->messages->add($username. " has been assigned as an aggreegator for the accounts==> ".$data_accounts_string);
					redirect('admin/user/');
	
                        }
			
		
	}

	function updateaggregator($user_id =0)
 	{
		$this->template->set('page_title', 'Manage Aggregator Accounts');
		$data['accounts1'] = array('name' => 'user_name');
                $user_accounts = array();
                $db1=$this->load->database('login', TRUE);
                $db1->from('bgasuser')->where('id', $user_id);
                $user_name1 = $db1->get();
		$data['accounts_active'] = array('(All Accounts)');
		$data['accounts'] = array('(All Accounts)' => '(All Accounts)');
                foreach($user_name1->result() as $row)
                {
                        $user_name = $row->username;
			$userrole = $row->role;
                        if($user_name=='guest')
                        {
                                $this->messages->add('Permission denied.', 'error');
                                redirect('admin/user');
                                return;
                        }
			$count=0;			
			$db1->from('bgasAccData');
                        $bgasacc=$db1->get();
			$i=0;
                        foreach($bgasacc->result() as $row1)
                        {
                               $var1=$row1->dblable;
                               $value[$i]=$row1->dblable;
                               $count++;
				$i++;
 
                        }
				
                                $data['accounts'] = $value;
				$data['role'] = $userrole;
				$data['user_name'] = $user_name;
		}

		$this->template->load('admin_template', 'admin/user/updateaggregator',$data);

	}
	function delaggact($username,$del)
	{
		$db1=$this->load->database('login', TRUE);
                $db1->from('aggregateaccounts')->where('username', $username);
                $agglist = $db1->get();
                foreach($agglist->result() as $row)
                {
                        $aggact = $row->accounts;
                }
        	$accarray = array();
	        $accarray = explode(",", $aggact);
        	//print_r($accarray);

	        //Create a merge file name.

        	$length1 = array();
	        $length2 = array();
        	$mergefile="";
		$acctpath= $this->upload_path1= realpath(BASEPATH.'../acct');

	        //$doc = new DomDocument;
        	for($i = 0 ; $i<sizeof($accarray); $i++)
        	{
                	$accname=$accarray[$i];
			$mergefile = $mergefile.$accname;

        	        $file_name1=$accname."_Liabilty.xml";
	                $tt1=$acctpath."/".$file_name1;
        	        $file_name2=$accname."_Assets.xml";
                	$tt2=$acctpath."/".$file_name2;
        	
        	        if (file_exists($tt1))
                	{
	                        unlink($tt1);
        	        }
                	if (file_exists($tt2))
			{
                        	unlink($tt2);
			}

		}
                $file_name1=$mergefile."_Liabilty.xml";
                $tt1=$acctpath."/".$file_name1;
                $file_name2=$mergefile."_Assets.xml";
                $tt2=$acctpath."/".$file_name2;

                if (file_exists($tt1))
		{
                        unlink($tt1);	
		}
                if (file_exists($tt2))
		{
                        unlink($tt2);
		}


		$lenstr = strlen($del);
		$result = substr_compare($aggact, $del, -$lenstr, $lenstr);
		$db1=$this->load->database('login', TRUE);
		if($result == 0)
		{
			if ( strcmp ( $aggact, $del) == 0 )
			{
				$del=$del;
				$db1->delete('aggregateaccounts', array('username' => $username));
				$db1->trans_complete();
				$deldata= array('aggtype' => "");
				$db1->where('username', $username)->update('bgasuser',$deldata);
				$this->messages->add('Update Aggregate  Account of - - ' . $username . ' success');
				redirect('admin/user/');
			}
			else
			{
				$del = ",".$del ;	
				$newagglist = str_replace ($del,"",$aggact);
	                        $update_data = array('accounts' => $newagglist);
			 	$db1->where('username', $username)->update('aggregateaccounts', $update_data);
                	        $db1->trans_complete();
                        	$this->messages->add('Update Aggregate  Account of - ' . $username . ' success');
                        	redirect('admin/user/');
			}
		}
		else
		{
                        $del = $del."," ;
                        $newagglist = str_replace ($del,"",$aggact);
                        $update_data = array('accounts' => $newagglist);
                        $db1->where('username', $username)->update('aggregateaccounts', $update_data);
                        $db1->trans_complete();
                        $this->messages->add('Update Aggregate  Account of - ' . $username . ' success');
                        redirect('admin/user/');

		}
	}

	function addaggact($username,$add)
	{
		$username;

		$db1=$this->load->database('login', TRUE);
                $db1->from('aggregateaccounts')->where('username', $username);
                $agglist = $db1->get();
                foreach($agglist->result() as $row)
                {
                        $aggact = $row->accounts;
                }
			
                        $add = ",".$add ;
			$newagglist = $aggact.$add;
			
                        $update_data = array('accounts' => $newagglist);
                        $db1=$this->load->database('login', TRUE);
                        $db1->where('username', $username)->update('aggregateaccounts', $update_data);
                        $db1->trans_complete();
                        $this->messages->add('Update Aggregate  Accounts of - ' . $username . ' success');
                        redirect('admin/user/');

	}


}
/* End of file user.php */
/* Location: ./system/application/controllers/admin/user.php */
