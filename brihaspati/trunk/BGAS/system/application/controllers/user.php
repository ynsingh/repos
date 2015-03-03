<?php

class User extends Controller {
	function index()
	{
		redirect('user/login');
		return;
	}

	function login()
	{
		$this->template->set('page_title', 'Login');
		$this->load->library('general');

		/* Create bgasuser table in login database*/
		$db1=$this->load->database('login', TRUE);
		/* check if table exist */
		$table="bgasuser";
		if($db1->query("SHOW TABLES LIKE '".$table."'")->num_rows()==1){
			//$this->messages->add('login database with user table exists.', 'success');
		}
		else{

			$setup_login = read_file('system/application/controllers/admin/createtable_bgasuser.sql');
	                $setup_login_array = explode(";", $setup_login);
        	        foreach($setup_login_array as $row)
                	{
                		if (strlen($row) < 5)
                        		continue;
	                        $db1->query($row);
        	                if ($db1->_error_message() != "")
                	        {
                        		$this->messages->add('Error initializing login database.'._error_message(), 'error');
                                	$this->template->load('user_template', 'user/login', $data);
	                                return;
        	                }
                	}
                	$this->messages->add('Initialized login database.', 'success');
		}

		/* If user already logged in then redirect to profile page */
		if ($this->session->userdata('user_name'))
			redirect('user/profile');

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
	
		/* Form validations */
		$this->form_validation->set_rules('user_name', 'User name', 'trim|required|min_length[1]|max_length[100]');
		$this->form_validation->set_rules('user_password', 'Password', 'trim|required|min_length[1]|max_length[100]');

		/* Re-populating form */
		if ($_POST)
		{
			$data['user_name']['value'] = $this->input->post('user_name', TRUE);
			$data['user_password']['value'] = $this->input->post('user_password', TRUE);
		}

		if ($this->form_validation->run() == FALSE)
                    {
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('user_template', 'user/login', $data);
			return;
		}
		else
		{
			$data_user_name = $this->input->post('user_name', TRUE);
			$data_user_password = $this->input->post('user_password', TRUE);
			$db_user_name='';
                        $user_password='';
                        $user_account='';
                        $user_role='';
			$user_status='';
			//connect with login database for authentication. The alias is login
                                $db1->from('bgasuser');
                                $db1->select('username,password,role,status,accounts')->where('username =', $data_user_name);

                                $user_name1 = $db1->get();
                                foreach($user_name1->result() as $row)
                                {
					$db_user_name = $row->username;
                                        $user_password = $row->password;
                                        $user_account = $row->accounts;
					$user_status = $row->status;
                                        $user_role= $row->role;
				}	

			/* Check user exist in ini/db file*/
			if($data_user_name != $db_user_name)
			{
				$this->messages->add('Authentication failed.', 'error');
				$this->template->load('user_template', 'user/login', $data);
				return;
			}

			/* Check user status if disabled then redirect to login page*/
			if($user_status != 1)
			{
				$this->messages->add('User disabled.', 'error');
				$this->template->load('user_template', 'user/login', $data);
				return;
			}
	
			/* Password verify */
			$data_user_password = md5($data_user_password);
                        if ($user_password == $data_user_password)
			{
				$this->messages->add('Logged in as ' . $data_user_name . '.', 'success');
				$this->session->set_userdata('user_name', $data_user_name);
				$this->session->set_userdata('user_role', $user_role);
				$this->session->set_userdata('active_account',$user_account);
                                                
				redirect('');
				return;
			} else {
				$this->session->unset_userdata('user_name');
				$this->session->unset_userdata('user_role');
				$this->session->unset_userdata('active_account');
				$this->messages->add('Authentication failed.', 'error');
				$this->template->load('user_template', 'user/login', $data);
				return;
			}
		}
		$db1->close();
		return;
	}

	function logout()
	{
		$this->session->unset_userdata('user_name');
		$this->session->unset_userdata('user_role');
		$this->session->unset_userdata('active_account');
		$this->session->sess_destroy();
		//$this->messages->add('Logged out.', 'success');
		redirect('user/login');
	}

	function account()
	{
		$this->template->set('page_title', 'Change Account');
		$this->load->library('general');
		$db1=$this->load->database('login', TRUE);
		/* Show manage accounts links if user has permission */
		if (check_access('administer'))
		{
			$this->template->set('nav_links', array('admin/create' => 'Create account', 'admin/manage' => 'Manage accounts'));
		}

		/* Check access */
		if ( ! ($this->session->userdata('user_name')))
		{
			$this->messages->add('Permission denied.', 'error');
			redirect('');
			return;
		}
		
		/* Currently active account */
		$data['active_account'] = $this->session->userdata('active_account');
		//$data['user_account'] = $this->session->userdata('user_account');
		
		/* Getting list of files in the config - accounts directory */
/*		$accounts_list = get_filenames($this->config->item('config_path') . 'accounts');*/
		$data['accounts'] = array();
                $db1->select('dblable')->from('bgasAccData');
		$list = $db1->get();

		//this is old line
                //if($list->num_rows() > 1)
                if($list->num_rows() > 0)
                       {
//                                $this->messages->add('Problem with selection of account label.', 'error');
  //                              $this->template->load('admin_template', 'admin/manage', $data);
    //                            return;
      //                  }
        //        else
          //      {
                        foreach($list->result() as $row){
                                $dlable = $row->dblable;
                                $data['accounts'][$dlable] = $dlable;
                        }
                        
                }
		

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
                $user_account_active = $this->session->userdata('active_account'); 
		/* Check user ini/sql db file */
		if ( ! $active_user = $this->general->check_user($this->session->userdata('user_name')))
		{
			redirect('user/profile');
			return;
		}
                
		/* Filter user access to accounts*/ 
		foreach($active_user->result() as $row)
                {
                        $user_account = $row->accounts;
                }

		if ($user_account != '*')
		{
			$valid_accounts = explode(",", $user_account);
			$data['accounts'] = array_intersect($data['accounts'], $valid_accounts);
//			$data['accounts'] = $valid_accounts;
		}
		
		/* Form validations */
		$this->form_validation->set_rules('account', 'Account', 'trim|required');

		/* Repopulating form */
		if ($_POST)
		{
			$data['active_account'] = $this->input->post('account', TRUE);
		}
		  

		/* Validating form : only if label name is not set from URL */
		if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('user_template', 'user/account', $data);
			return;
		} else {
			$data_active_account = $this->input->post('account', TRUE);

			/* Check for valid account */
			if ( ! array_key_exists($data_active_account, $data['accounts']))
			{
				$this->messages->add('Invalid account selected.', 'error');
				$this->template->load('user_template', 'user/account', $data);
				return;
			}

			if ( ! $this->general->check_account($data_active_account))
			{
				$this->template->load('user_template', 'user/account', $data);
				return;
			}

			/* Setting new account database details in session */
			$this->session->set_userdata('active_account', $data_active_account);
			$this->messages->add('Account changed.', 'success');
			redirect('');
		}
		return;
	}

	function profile()
	{
		$this->template->set('page_title', 'User Profile');

		/* Check access */
		if ( ! ($this->session->userdata('user_name')))
		{
			$this->messages->add('Permission denied.', 'error');
			redirect('');
			return;
		}

		$this->template->load('user_template', 'user/profile');
		return;
	}
}

/* End of file user.php */
/* Location: ./system/application/controllers/user.php */
