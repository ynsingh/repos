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
                $db1->select('id,username,email,role,status,accounts')->from('bgasuser');

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
		$db1->close;

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

                                                        $this->messages->add('Added User Account - ' . $data_user_name . ' success');
			
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
                                                     
                                                redirect('admin/user/');

                                                 return;
                                                }
                                    

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
                                 
                             redirect('admin/user/');

                             return;
                                                }
		$db1->close();
	//	$ini_file = $this->config->item('config_path') . "users/" . $user_name . ".ini";
	//	$this->messages->add('Delete ' . $ini_file . ' file manually.', 'error');
		redirect('admin/user');
		return;
	}
}

/* End of file user.php */
/* Location: ./system/application/controllers/admin/user.php */
