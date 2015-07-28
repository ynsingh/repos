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
		$db1->select('edrpuser.id as id,edrpuser.username as username,edrpuser.componentreg as componentreg, edrpuser.email as email,edrpuser.status as status,bgasuserrolegroup.accounts as accounts, bgasuserrolegroup.role as role,bgasuserrolegroup.aggtype as aggtype');
		$db1->from('edrpuser')->join('bgasuserrolegroup', 'edrpuser.id = bgasuserrolegroup.userid'); 
		
        //$db1->select('id,username,email,role,status,accounts,aggtype')->from('bgasuser');
		$query = $db1->get();
        $config['total_rows'] =$db1->count_all('edrpuser');

        $data['users']= $query;
		$user_id='';

		$this->template->load('admin_template', 'admin/user/index', $data);
		$db1->close();
		return;
	}

	function add()
	{
		$this->load->library('validation');
        	$this->load->library('paymentreceipt');
		$this->template->set('page_title', 'Add user');

		$db1=$this->load->database('login', TRUE);

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

		$data['mobile'] = array(
			'name' => 'mobile',
			'id' => 'mobile',
			'maxlength' => '10',
			'size' => '10',
			'value' => '',
		);

		$data['component_reg'] = array(
			'name' => 'component_reg',
			'id' => 'component_reg',
			'maxlength' => '100',
			'size' => '40',
			'value' => 'BGAS',
			'readonly'=>'true',
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
				$data['accounts'][$row ->dblable] = $row ->dblable;
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
			$data['mobile']['value'] = $this->input->post('mobile', TRUE);
			$data['component_reg']['value'] = $this->input->post('component_reg', TRUE);
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
		$this->form_validation->set_rules('mobile', 'Mobile', 'trim');
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
			$data_user_mobile = $this->input->post('mobile', TRUE);
			$data_user_components = $this->input->post('component_reg', TRUE);

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
			}else{
				
				if (in_array('(All Accounts)', $data_accounts))
				{
					$data_accounts_string = '*';
				} else {
					/* Filtering out bogus accounts */
					$data_accounts_valid = array_intersect($data['accounts'], $data_accounts);
					$data_accounts_string = implode(",", $data_accounts_valid);
				}


				/* check if username already exist*/
				$db1=$this->load->database('login', TRUE);
				$db1->select('id,mobile,componentreg');
				$db1->from('edrpuser')->where('username', $data_user_name);
				$query = $db1->get();
		        if (!($query->num_rows() < 1))
		        {
		        	foreach ($query->result() as $row) {
		        		
		        		$compo_reg = $row->componentreg;
		        		$registered_id = $row->id;
		        		$registered_mobile = $row->mobile;
		        	}

		        	$component_array = explode(',', $compo_reg);
		        	if(in_array('BGAS',$component_array))
		        	{
		        		$this->messages->add('User Account already exists for BGAS.', 'error');
		                $this->logger->write_message("error", "User Account already exist" . $data_user_name);
				        $this->template->load('admin_template', 'admin/user/add', $data);
		                return;	
		        	}else{

		        		$compo_reg = $compo_reg.",BGAS";
		        		$db1->trans_start();

		        		/* check mobile no. exist is same as entered */

		        		if($registered_mobile == $data_user_mobile){
		        			$update_data = array(
			                'componentreg' => $compo_reg	
							);
		        		}else{
		        			$update_data = array(
			                'componentreg' => $compo_reg,
			                'mobile' => $data_user_mobile
			                );
		        		}						

						if ( ! $db1->where('id', $registered_id)->update('edrpuser', $update_data))
						{
							$db1->trans_rollback();
							$this->messages->add('Error in updating User Account - ' .  $data_user_name . '.', 'error');
							return;
						}
						else{
							$db1->trans_complete();

							$insert_data = array(
		                        'userid' => $registered_id,
		                        'role' =>$data_user_role,
		                        'accounts'=>$data_accounts_string
	                    	);

							if ( ! $db1->insert('bgasuserrolegroup', $insert_data))
	                    	{

		                        $db1->trans_rollback();
		                        $this->messages->add('Error addding User Account in userrolegroup- ' . $data_user_name . '.', 'error');
		                        $this->logger->write_message("error", "Error adding User Account " . $data_user_name);
								$this->template->load('admin_template', 'admin/user/add', '');
		                        return;
		                    }
							else{
								$db1->trans_complete();
							}	
						}
						//added by @kanchan
		               /* $message = "You are Added in Brihaspati General Accounting System<br>Username- $data_user_name <br> && User Role is- $data_user_role  With Your Previous Password";
		                $subject = 'User Account created in BGAS ';
		                if($this->paymentreceipt->send_mail($data_user_email, $subject, $message))
						$this->messages->add('Added User Account - ' . $data_user_name .  'Mail Sucessfully send!---'. ' success');
				*/
						redirect('admin/user/');
						return;	
		        	}				
				}
				else{
					
					//added by megha
					$insert_data = array(
                        'username' => $data_user_name,
                        'password'=>md5($data_user_password),
                        'email' => $data_user_email,
                        //'role' =>$data_user_role,
                        'componentreg' => $data_user_components,
                        'mobile' => $data_user_mobile,
                        'status' => $data_user_status
                        //'accounts'=>$data_accounts_string
                    );


					$user_password = $data_user_password;		

                    if ( ! $db1->insert('edrpuser', $insert_data))
                    {

                        $db1->trans_rollback();
                        $this->messages->add('Error addding User Account - ' . $data_user_name . '.', 'error');

                        $this->logger->write_message("error", "Error adding User Account " . $data_user_name);
                        //$this->template->load('template', 'user/add');
						$this->template->load('admin_template', 'admin/user/add', '');
                        return;
                    }
					else{
						$bgasuser_id = $db1->insert_id();
						$db1->trans_complete();

						$insert_data1 = array(
	                        'userid' => $bgasuser_id,
	                        'role' =>$data_user_role,
	                        'accounts'=>$data_accounts_string
                    	);

						if ( ! $db1->insert('bgasuserrolegroup', $insert_data1))
                    	{
	                        $db1->trans_rollback();
	                        $this->messages->add('Error addding User Account in userrolegroup- ' . $data_user_name . '.', 'error');
	                        $this->logger->write_message("error", "Error adding User Account " . $data_user_name);
							$this->template->load('admin_template', 'admin/user/add', '');
	                        return;
	                    }
						else{
							$db1->trans_complete();
						}

						$insert_data2 = array(
	                        'userid' => $bgasuser_id,
	                        'mobile' => $data_user_mobile,
	                        'lang' => "English",
	                        'status'=> 1
                    	);

						if ( ! $db1->insert('userprofile', $insert_data2))
                    	{
	                        $db1->trans_rollback();
	                        $this->messages->add('Error addding User Account in userprofile- ' . $data_user_name . '.', 'error');
	                        $this->logger->write_message("error", "Error adding User Account " . $data_user_name);
							$this->template->load('admin_template', 'admin/user/add', '');
	                        return;
	                    }
						else{
							$db1->trans_complete();
						}

						$insert_data3 = array(
	                        'userid' => $bgasuser_id,
	                        'lastusedlang' => "English",
	                        'lastloginstatus' =>1,
	                        'status' => 1
                    	);

						if ( ! $db1->insert('userlaststatus', $insert_data3))
                    	{
	                        $db1->trans_rollback();
	                        $this->messages->add('Error addding User Account in userlaststatus- ' . $data_user_name . '.', 'error');
	                        $this->logger->write_message("error", "Error adding User Account " . $data_user_name);
							$this->template->load('admin_template', 'admin/user/add', '');
	                        return;
	                    }
						else{
							$db1->trans_complete();
						}

					}
					//added by @kanchan
	                $message = "<b>You are Added in Brihaspati General Accounting System</b><br>Username- $data_user_name<br>Your Role- $data_user_role <br> Your Password- $user_password";
	                $subject = 'User Account created in BGAS ';
	                if($this->paymentreceipt->send_mail($data_user_email, $subject, $message))
					$this->messages->add('Added User Account - ' . $data_user_name . ' success');
					redirect('admin/user/');
					return;	
				}
				$db1->close();
			}
		}
		$this->template->load('admin_template', 'admin/user/add', $data);
		return;

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
			}
		}
		$this->template->load('admin_template', 'admin/user/add', $data);
		return;*/
	}

	function edit($user_id =0)
	{
		$this->template->set('page_title', 'Edit user');
		$user_password='';
		$user_mobile ='';
		$user_components ='';
		$user_email='';
		$db1=$this->load->database('login', TRUE);
		$db1->select('edrpuser.username as username, edrpuser.email as email,edrpuser.password as password,edrpuser.mobile as mobile,edrpuser.componentreg as componentreg,bgasuserrolegroup.accounts as accounts, bgasuserrolegroup.role as role,bgasuserrolegroup.aggtype as aggtype');
		$db1->from('edrpuser')->join('bgasuserrolegroup', 'edrpuser.id = bgasuserrolegroup.userid');
		$db1->where('edrpuser.id',$user_id);
		$user_name1 = $db1->get();

		foreach($user_name1->result() as $row)
        {
	        $user_name = $row->username;       
			$user_password = $row->password;
			$user_email = $row->email;
			$user_account = $row->accounts;
			$user_role = $row->role;
			$user_mobile = $row->mobile;
			$user_components = $row->componentreg;
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

		$data['user_mobile'] = array(
			'name' => 'user_mobile',
			'id' => 'user_mobile',
			'maxlength' => '100',
			'size' => '40',
			'value' => $user_mobile,
		);

		$data['user_components'] = array(
			'name' => 'user_components',
			'id' => 'user_components',
			'maxlength' => '100',
			'size' => '40',
			'value' => $user_components,
			'readonly' => true,
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
                    $data['accounts'][$row ->dblable] = $row ->dblable;
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
			$data['user_components'] = $this->input->post('user_components', TRUE);
			$data['user_mobile'] = $this->input->post('user_mobile', TRUE);
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
		$this->form_validation->set_rules('user_mobile', 'Mobile No.', 'trim');
		$this->form_validation->set_rules('user_components', 'Components', 'trim|required');
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
			$data_user_mobile = $this->input->post('user_mobile', TRUE);
			$data_user_components = $this->input->post('user_components', TRUE);
			if ($data_user_status == 1)
				$data_user_status = 1;
			else
				$data_user_status = 0;
			$data_accounts = $this->input->post('accounts', TRUE);
			//print_r($data_accounts);
	//		$this->messages->add("Test=====>".$data_accounts); 
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
	//		echo "gjfgjfgjf$user_name";
			$this->messages->add($data_accounts_string);

			$db1->trans_start();
			$update_data = array(
				'username' => $data_user_name,						 
				'password'=>md5($data_user_password),
				'email' => $data_user_email,
				'status' => $data_user_status,
				//'accounts' => $data_accounts_string,
		                //'role' =>$data_user_role,
                		'componentreg' => $data_user_components,
		                'mobile' => $data_user_mobile,
         		        'status' => $data_user_status	
			);

			if ( ! $db1->where('id', $user_id)->update('edrpuser', $update_data))
			{
				$db1->trans_rollback();
				$this->messages->add('Error in updating User Account - ' .  $user_name . '.', 'error');
				return;
			}
			else{
				$db1->trans_complete();
				$update_data1 = array(
                		    'role' =>$data_user_role,
		                    'accounts'=>$data_accounts_string
            			);

				if ( ! $db1->where('userid',$user_id)->update('bgasuserrolegroup',$update_data1))
            			{	
                    		$db1->trans_rollback();
                    		$this->messages->add('Error in updating User Account - ' . $user_name . '.', 'error');
                    		return;
                		}
				else{
					$db1->trans_complete();
				}
					//$this->messages->add('Update User Account - ' . $user_name . ' success');

                //added by @kanchan   
				$message = "Your User Account data has been Updated.<br> Your updated Account- $data_accounts_string";
                $subject = 'User data Updated';
                $this->load->library('paymentreceipt');
                if($this->paymentreceipt->send_mail($data_user_email,$subject,$message))
                //{
			 $this->messages->add('Update User Account - ' . $user_name . ' success');
					//$this->messages->add('Mail Sucessfully send!-' . $user_name . ' success');
                    redirect('admin/user');
                    return; 
               	//}
		}//else	
                                    
            //return;


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
		/* Get the User details */
		$db1=$this->load->database('login', TRUE);

		$db1->select('username,password,email,componentreg');
		$db1->from('edrpuser');
		$db1->where('edrpuser.id',$user_id);
        //$user_name1 = $db1->get();

		//$db1->from('user')->where('id', $user_id);
		$user_q = $db1->get();
		if ($user_q->num_rows() < 1)
		{
			$this->messages->add('Invalid User.', 'error');
			redirect('admin/user/');
			return;
		} else {
			$user_data = $user_q->row();
			$user_email = $user_data->email;
            		$user_name = $user_data->username;
			$user_password = $user_data->password;
			$component_reg = $user_data->componentreg;
		}
			
		$component_array = explode(',', $component_reg);
		if(in_array('BGAS',$component_array) && (count($component_array) >1))
		{
			foreach (array_keys($component_array, 'BGAS') as $key) {
    			unset($component_array[$key]);
			}

			$component_string = implode(",", $component_array);
			$db1->trans_start();
			$update_data = array(
					'componentreg' => $component_string
			);

			if ( ! $db1->where('id', $user_id)->update('edrpuser', $update_data))
    		{
	            $db1->trans_rollback();
    		}else{
				$db1->trans_complete();

				if ( ! $db1->delete('bgasuserrolegroup', array('userid' => $user_id)))
		        {
	        		$db1->trans_rollback();
		        	$this->messages->add('Error delete User Account from userrolegroup- ' . $user_data->username . '.', 'error');
		            $this->logger->write_message("error", "Error delete User Account from userrolegroup " . $user_data->username);
					redirect('admin/user/');
					return;
	        	}else{
	        		$db1->trans_complete();
	        	}

	        	if ( ! $db1->delete('aggregateaccounts', array('username' => $user_name)))
		        {
	        		$db1->trans_rollback();
		        	$this->messages->add('Error delete User Account from aggregateaccounts- ' . $user_data->username . '.', 'error');
		            $this->logger->write_message("error", "Error delete User Account from userrolegroup " . $user_data->username);
					redirect('admin/user/');
					return;
	        	}else{
	        		$db1->trans_complete();
	        	}
		    }
		}elseif(in_array('BGAS',$component_array) && (count($component_array) == 1))
		{
			$db1->trans_start();
			if ( ! $db1->delete('edrpuser', array('id' => $user_id)))
	        {

	            $db1->trans_rollback();
	        	$this->messages->add('Error delete User Account - ' . $user_data->username . '.', 'error');
	            $this->logger->write_message("error", "Error delete User Account " . $user_data->username);
				redirect('admin/user/');
				return;
	        }
	        else{
	            $db1->trans_complete();
				 
				if ( ! $db1->delete('bgasuserrolegroup', array('userid' => $user_id)))
	        	{
	        		$db1->trans_rollback();
		        	$this->messages->add('Error delete User Account from userrolegroup- ' . $user_data->username . '.', 'error');
		            $this->logger->write_message("error", "Error delete User Account from userrolegroup " . $user_data->username);
					redirect('admin/user/');
					return;
	        	}else{
	        		$db1->trans_complete();
	        	}

	        	if ( ! $db1->delete('aggregateaccounts', array('username' => $user_name)))
		        {
	        		$db1->trans_rollback();
		        	$this->messages->add('Error delete User Account from aggregateaccounts- ' . $user_data->username . '.', 'error');
		            $this->logger->write_message("error", "Error delete User Account from userrolegroup " . $user_data->username);
					redirect('admin/user/');
					return;
	        	}else{
	        		$db1->trans_complete();
	        	}
	        }
	    }
    	$this->messages->add('delete User Account - ' . $user_data->username . '.', 'success');
		     //added by @kanchan
        $message = "<b>Your Account has been deleted from BGAS Account</b><br>Username- $user_name <br>Your Password- $user_password";
        $subject = 'User Deleted';
        $this->load->library('paymentreceipt');
        if($this->paymentreceipt->send_mail($user_email,$subject,$message))
        {
		//$this->messages->add('Mail Sucessfully send!-' . $user_name . ' success');
            	redirect('admin/user');
            	return;
        }
       // redirect('admin/user/');
       // return;
        	
		$db1->close();
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

                $db1->select('edrpuser.username as username,edrpuser.email as email,bgasuserrolegroup.accounts as accounts');
				$db1->from('edrpuser')->join('bgasuserrolegroup', 'edrpuser.id = bgasuserrolegroup.userid');
				$db1->where('edrpuser.id',$user_id);
	       		//$user_name1 = $db1->get();
                
               // $db1->from('user')->where('id', $user_id);
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
	    $db1->select('edrpuser.username as username,bgasuserrolegroup.role as role');
		$db1->from('edrpuser')->join('bgasuserrolegroup', 'edrpuser.id = bgasuserrolegroup.userid');
		$db1->where('edrpuser.id',$user_id);
      
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

						//$db1=$this->load->database('login', TRUE);
						$db1->select('id')->from('edrpuser')->where('username',$username);
						$query_result = $db1->get();
						foreach($query_result->result() as $row){
							$userid = $row->id;
						}

						//$tablebgu="user";
						$update_data=array('aggtype'=>'agg');
						$db1->where('userid',$userid )->update('bgasuserrolegroup', $update_data);
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

        $db1->select('edrpuser.username as username,bgasuserrolegroup.role as role');
		$db1->from('edrpuser')->join('bgasuserrolegroup', 'edrpuser.id = bgasuserrolegroup.userid');
		$db1->where('edrpuser.id',$user_id);
        $user_name1 = $db1->get();

        //$db1->from('user')->where('id', $user_id);
        //$user_name1 = $db1->get();
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

		$db1->select('id')->from('edrpuser')->where('username',$username);
		$query_result = $db1->get();
		foreach($query_result->result() as $row){
			$userid = $row->id;
		}
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
				$db1->where('userid', $userid)->update('bgasuserrolegroup',$deldata);
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
