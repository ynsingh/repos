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
			'maxlength' => '255',
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
	
	function forgotpassword()
	{
		$this->template->set('page_title', 'Forgot Password');
		$this->load->helper('string');
		$this->load->helper('date');
		$this->load->library('paymentreceipt');
		/* Form fields */
                $data['user_name'] = array(
                        'name' => 'user_name',
                        'id' => 'user_name',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => '',
                );
		/* Form validations */
                $this->form_validation->set_rules('user_name', 'User name', 'trim|required|min_length[1]|max_length[255]');
		/* Re-populating form */
                if ($_POST)
                {
                        $data['user_name']['value'] = $this->input->post('user_name', TRUE);
		}
		 if ($this->form_validation->run() == FALSE)
                    {
                        $this->messages->add(validation_errors(), 'error');
                        $this->template->load('user_template', 'user/forgotpassword', $data);
                        return;
                }
		else
                {
                        $data_user_name = $this->input->post('user_name', TRUE);

		}
		/* Create bgasuser table in login database*/
                $db1=$this->load->database('login', TRUE);
                /* check if user exist */
		$db1->select('username')->from('bgasuser')->where('username =', $data_user_name);
		$query = $db1->get();
		if (!($query->num_rows() > 0)){
			$this->messages->add('User Account does not exists.'.$data_user_name, 'error');
                        $this->logger->write_message("error", "User Account does not exist" . $data_user_name);
			$this->template->load('user_template', 'user/forgotpassword', $data);
			$db1->close();
                        return;
		}
		else{
			// create message
			$randstr = random_string('alnum', 16);
			$slug = md5($data_user_name . $randstr);
			$message = 'To reset your password please click the link below and follow the instructions:'. site_url('user/resetpassword/'. $data_user_name .'/'. $slug) .' <br>If you did not request to reset your password then please just ignore this email and no changes will occur.<br><br> Note: This reset code will expire after two days.';
			$subject = 'Reset your Password';
			// send mail to user
			$this->paymentreceipt->send_mail($data_user_name,$subject,$message);
			// store key to database
			$cdate = date("Y-m-d h:i:s");
			$d=strtotime("+2 Days");
			$exdate =  date("Y-m-d h:i:s", $d);
			$db1->trans_start();
                        $insert_data = array(
                    		'username' => $data_user_name,
                                'rkey'=> $slug,
                                'passdate' => $cdate,
                                'expdate' => $exdate,
                         );
			if ( ! $db1->insert('forgotPass', $insert_data))
                        {
	                        $db1->trans_rollback();
                                $this->messages->add('Error addding User key for forgot password - ' . $data_user_name .'.', 'error');
                                $this->logger->write_message("error", "Error adding User key for forgot password " . $data_user_name . $cdate . $exdate . $slug );
				redirect('user/login');
	                        $db1->close();
                                return;
                         }
                         else{
	                         $db1->trans_complete();
				 $this->messages->add('Please check your email to reset password. '. $data_user_name, 'success');
	                         $this->logger->write_message("success", " Mail send to user for reset password" . $data_user_name);
				 redirect('user/login');
	                         $db1->close();
                                 return;
                        }
		}
	}

	function resetpassword()
        {
                $this->template->set('page_title', 'Reset Password');
                $this->load->helper('string');
                $this->load->helper('date');
                $this->load->library('paymentreceipt');
		$user_nm = $this->uri->segment(3);
		if(!$user_nm)
		{
			$this->messages->add('Invalid reset code ', 'error');
			$this->template->load('user_template', 'user/forgotpassword', $data);
                        return;
		}
		$hash = $this->uri->segment(4);
		if(!$hash) 
		{
			$this->messages->add('Invalid reset code', 'error');
			$this->template->load('user_template', 'user/forgotpassword', $data);
                        return;
		}

                /* Create bgasuser table in login database*/
                $db1=$this->load->database('login', TRUE);
		/* check if user exist */
                $db1->select('rkey')->from('forgotPass')->where('username =', $user_nm);
                $query = $db1->get();
                if (!($query->num_rows() > 0)){
                        $this->messages->add('User Account with reset key does not exists.'.$user_nm.'and'.$hash, 'error');
                        $this->logger->write_message("error", "User Account or key  does not exist" . $user_nm);
			redirect('user/forgotpassword');
                        $db1->close();
                        return;
                }
                else{
			// get the values from db and compare with hash
			foreach($query -> result() as $row){
				$ukey = $row->rkey;
			}
			// if matched then open password window
			if (!($hash == $ukey)){
				$this->messages->add('User Account with reset key either does not exists or does not match.', 'error');
                        	$this->logger->write_message("error", "User Account or key either does not exist or does not match" . $user_nm);
				redirect('user/forgotpassword');
        	                $db1->close();
	                        return;
			}
			else{
			// open password window
	                /* Form fields */
	        	        $data['user_name'] = array(
        	        	        'name' => 'user_name',
                	        	'id' => 'user_name',
	                	        'maxlength' => '100',
        	                	'size' => '40',
	                	        'value' => '',
					'readonly'=>'true',
	        	        );
		
				$data['password'] = array(
                	        	'name' => 'password',
	                        	'id' => 'password',
		                        'maxlength' => '100',
        		                'size' => '40',
                		        'value' => '',
		                );
	
				$data['cnfpassword'] = array(
        		                'name' => 'cnfpassword',
                        		'id' => 'cnfpassword',
	                        	'maxlength' => '100',
	        	                'size' => '40',
        	        	        'value' => '',
	        	        );

        	        /* Form validations */
	                	$this->form_validation->set_rules('user_name', 'User name', 'trim|required|min_length[1]|max_length[255]');
		                $this->form_validation->set_rules('password', 'Password', 'trim|required|min_length[1]|max_length[255]');
        		        $this->form_validation->set_rules('cnfpassword', 'Confirm Password', 'trim|required|min_length[1]|max_length[255]|matches[password]');

                	/* Re-populating form */
	                	if ($_POST)
	        	        {
        	        	        $data['user_name']['value'] = $this->input->post('user_name', TRUE);
                	        	$data['password']['value'] = $this->input->post('password', TRUE);
	                	        $data['cnfpassword']['value'] = $this->input->post('cnfpassword', TRUE);
	        	        }
        	        	if ($this->form_validation->run() == FALSE)
	        	        {
					$data['user_name']['value'] = $user_nm;
                        		$this->messages->add(validation_errors(), 'error');
		                        $this->template->load('user_template', 'user/resetpassword', $data);
        		                return;
                		}
	                	else
	                	{
                                         $db1->close();
					 redirect('user/login');
                                         return;
				}
			}
                }
        }


	function changepasswd()
        {
		$this->load->helper('string');
                $this->load->helper('date');
                $this->load->library('paymentreceipt');
		/* Form fields */
                $data['user_name'] = array(
                	'name' => 'user_name',
                        'id' => 'user_name',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => '',
                        'readonly'=>'true',
                );

                $data['password'] = array(
                	'name' => 'password',
                        'id' => 'password',
                        'maxlength' => '100',
                          'size' => '40',
                                'value' => '',
                        );

                        $data['cnfpassword'] = array(
                                'name' => 'cnfpassword',
                                'id' => 'cnfpassword',
                                'maxlength' => '100',
                                'size' => '40',
                                'value' => '',
                        );

                        /* Form validations */
                        $this->form_validation->set_rules('user_name', 'User name', 'trim|required|min_length[1]|max_length[255]');
                        $this->form_validation->set_rules('password', 'Password', 'trim|required|min_length[1]|max_length[255]');
                        $this->form_validation->set_rules('cnfpassword', 'Confirm Password', 'trim|required|min_length[1]|max_length[255]|matches[password]');

                        /* Re-populating form */
                        if ($_POST)
                        {
                                $data['user_name']['value'] = $this->input->post('user_name', TRUE);
                                $data['password']['value'] = $this->input->post('password', TRUE);
                                $data['cnfpassword']['value'] = $this->input->post('cnfpassword', TRUE);
                        }
                        if ($this->form_validation->run() == FALSE)
                        {
                                $this->messages->add(validation_errors(), 'error');
                                $this->template->load('user_template', 'user/resetpassword', $data);
                                return;
                        }
                        else
                        {
                                // get user name and both password
                                // check both are same 
                                $data_user_name = $this->input->post('user_name', TRUE);
                                $data_password = $this->input->post('password', TRUE);
				$data_cnfpassword = $this->input->post('cnfpassword', TRUE);
				
				/* Create bgasuser table in login database*/
		                $db1=$this->load->database('login', TRUE);

                                // then reset the password and update in database
                                $db1->trans_start();
                                $update_data = array(
                                        'username' => $data_user_name,
                                        'password'=> md5($data_cnfpassword),
                                 );
                                if ( ! $db1->where('username', $data_user_name)->update('bgasuser', $update_data))
                                {
                                        $db1->trans_rollback();
                                        $this->messages->add('Error updating User Account - ' . $data_user_name . '.', 'error');
                                        $this->logger->write_message("error", "Error updating password in User Account " . $data_user_name);
                                        $db1->close();
					redirect('user/login');
                                        return;
                                }
                                 else{
                                         $db1->trans_complete();
                                         $this->messages->add('Your Password reset sucessfully and mail send. Now you can login with new password'. ' success');
                                         $this->logger->write_message("success", "Updated password in user account called " . $data_user_name );
                                         // send the mail with  new  password, create message
                                         $message = 'Your password has been reset. The new password is '.$data_cnfpassword;
                                         $subject = 'Your updated Password';

                                         // delete the reset code from forgot password table.
					if ( ! $db1->where('username', $data_user_name)->delete('forgotPass'))
					{
						$db1->trans_rollback();
						$this->messages->add('Error delete User reset key - ' . $data_user_name . '.', 'error');
	                                        $this->logger->write_message("error", "Error deleting reset in User Account " . $data_user_name);
						$db1->close();
						redirect('user/login');
        	                                return;
					}
					else{
						$db1->trans_complete();
	                                        $this->messages->add('User reset key is removed from the system'. ' success');
						$this->logger->write_message("success", "User password reset key is removed from the system " . $data_user_name);
					}
	
                                         // send mail to user
                                         $this->paymentreceipt->send_mail($data_user_name,$subject,$message);
                                         $db1->close();
					 redirect('user/login');
                                         return;
                                }
                        }
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

                if($list->num_rows() > 0)
                       {
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
        function aggregatebudget()
        {
		$this->load->model('Budget_model');
                $this->load->helper('array');
                $this->template->set('page_title', 'Aggregate Budget');

                //get username.

                $username=$this->session->userdata('user_name');
                $db1=$this->load->database('login', TRUE);

                //get aggregateaccounts string of user.

                $db1->from('aggregateaccounts')->where('username', $username);
                $userrec = $db1->get();
                foreach($userrec->result() as $row)
                {
                        $acountlist=$row->accounts;

                }
                $accarray = array();
                $mergebudgetfile = "";
		$acctpath= $this->upload_path1= realpath(BASEPATH.'../acct');

                //get the account from aggregateaccounts to create mergeraccount.

                $accarray = explode(",", $acountlist);
                for($i = 0; $i<sizeof($accarray); $i++)
                {

                        $accname = $accarray[$i];
                        //creation of xml file
                        //$budgetfilename  = $accname."_budget.xml";
                        //create merge file.
                        $mergebudgetfile = $mergebudgetfile.$accname;
		}
	        for($i = 0 ; $i<sizeof($accarray); $i++)
	        {
        	        $accname=$accarray[$i];
	
        	        $file_name1=$accname."_budget.xml";
                	$ttdel=$acctpath."/".$file_name1;

                	//deletion of xml file .
	                if (file_exists($ttdel))
                	        unlink($ttdel);

		}

                $file_name1=$mergebudgetfile."_budget.xml";
                $ttdel1=$acctpath."/".$file_name1;

                if (file_exists($ttdel1))
                        unlink($ttdel1);


                //path for storing xm file.
                $totalbudget = 0.00;
                $budgetopen = 0.00;
		$totalconsume = 0.00;
		$budgetconsume = 0.00;


                //code for creating accname_budget.xml file of each accounts.
                for($i = 0; $i<sizeof($accarray); $i++)
                {

                        $accname = $accarray[$i];
                        //creation of xml file
                        $budgetfilename  = $accname."_budget.xml";
                        //create merge file.
                        //$mergebudgetfile = $mergebudgetfile.$accname;

                        $a = 2;
                        $counter = 0;


                        $budget_arr = array();
                        $final_budget = array();



                        //getting budget detail of account.

                        $budget_arr = $this->Budget_model->get_agg_budgets($accname);
		

                        foreach ($budget_arr as $id => $bud)
                        {

                                //print_r($budget_arr);
                                $name = 'budget_value'. "_" .$bud['id'];

                                $code = $bud['code'];
                                $budgetname = $bud['budgetname'];
                                $bd_balance = $bud['bd_balance'];
                                $group_id = $bud['group_id'];
                                $allowedover = $bud['allowedover'];
                                $consume_amount = $bud['consume_amount'];
				$noofdigit = strlen($code);

                                if($code == "50")
                                {
                                        $totalbudget = $totalbudget + $bd_balance;
					$totalconsume = $totalconsume + $consume_amount;
                                }
                                if($noofdigit == "4")
                                {
                                        $budgetopen = $budgetopen + $bd_balance;
					$budgetconsume = $budgetconsume + $consume_amount;
                                }
                                $doc = new DOMDocument();
                                $doc->formatOutput = true;
                                $tt=$acctpath."/".$budgetfilename;

                                if(file_exists($tt))
                                {
                                        $doc->preserveWhiteSpace = false;
                                        $doc->load($tt);
                                        $Budgets = $doc->firstChild;

                                        $Budget_Name = $doc->createElement('Budget_Name');

                                        $Code = $doc->createElement('Code');
                                        $textNode = $doc->createTextNode($code);
                                        $Code->appendChild($textNode);
                                        $Budget_Name->appendChild($Code);

                                        $Budgetname = $doc->createElement('Budgetname');
                                        $textNode = $doc->createTextNode($budgetname);
                                        $Budgetname->appendChild($textNode);
                                        $Budget_Name->appendChild($Budgetname);

                                        $Bd_balance = $doc->createElement('Bd_balance');
                                        $textNode = $doc->createTextNode($bd_balance);
                                        $Bd_balance->appendChild($textNode);
                                        $Budget_Name->appendChild($Bd_balance);

                                        $Group_id = $doc->createElement('Group_id');
                                        $textNode = $doc->createTextNode($group_id);
                                        $Group_id->appendChild($textNode);
                                        $Budget_Name->appendChild($Group_id);

                                        $Allowedover = $doc->createElement('Allowedover');
                                        $textNode = $doc->createTextNode($allowedover);
                                        $Allowedover->appendChild($textNode);
                                        $Budget_Name->appendChild($Allowedover);

                                        $Consume_amount = $doc->createElement('Consume_amount');
                                        $textNode = $doc->createTextNode($consume_amount);
                                        $Consume_amount->appendChild($textNode);
                                        $Budget_Name->appendChild($Consume_amount);
                                        $Budgets->appendChild($Budget_Name);

                                        $ttt=$doc->saveXML();
                                        $handle = fopen($tt, "w");
                                        fwrite($handle, $ttt);
                                        fclose($handle);

                                }
                                else
                                {
                                        $r = $doc->createElement( "Budgets" );
                                        $doc->appendChild( $r );
                                        $b = $doc->createElement( "Budget_Name" );

                                        $Code = $doc->createElement( "Code" );
                                        $Code->appendChild($doc->createTextNode($code));
                                        $b->appendChild( $Code );

                                        $Budgetname = $doc->createElement( "Budgetname");
                                        $Budgetname->appendChild($doc->createTextNode($budgetname));
                                        $b->appendChild( $Budgetname );

                                        $Bd_balance = $doc->createElement( "Bd_balance");
                                        $Bd_balance->appendChild($doc->createTextNode($bd_balance));
                                        $b->appendChild( $Bd_balance );

                                        $Group_id = $doc->createElement( "Group_id");
                                        $Group_id->appendChild($doc->createTextNode($group_id));
                                        $b->appendChild( $Group_id );

                                        $Allowedover = $doc->createElement( "Allowedover");
                                        $Allowedover->appendChild($doc->createTextNode($allowedover));
                                        $b->appendChild( $Allowedover );

                                        $Consume_amount = $doc->createElement( "Consume_amount");
                                        $Consume_amount->appendChild($doc->createTextNode($consume_amount));
                                        $b->appendChild( $Consume_amount );

                                        $r->appendChild( $b );

                                        $doc->save($tt);
                                        $doc->saveXML();


                                }
                                $counter++;

                        }

                }

                //merger of budget data of accounts.

                $length1 = array();

                for($i = 0 ; $i<sizeof($accarray); $i++)
                {
                        $accname=$accarray[$i];


                        $file_name1=$accname."_budget.xml";
                        $tt1=$acctpath."/".$file_name1;

                        $doc = new DomDocument;
                        if (file_exists($tt1))
                        {
                                $doc->load($tt1);

                        // count all Liability_Name elements
                        $len1 = $doc->getElementsByTagName('Budget_Name')->length;
                        $length1[$i] = $len1;
                        }
                }
                $max1=0;
                if(sizeof($length1)!==0)
                {
                        $max1 = max($length1);
                }
                //echo $max1;

                for($i=0; $i<=$max1; $i++)
                {
                        $sumbdbalance = 0;
                        $sumconsumeamt = 0;

                        for($j=0;$j<sizeof($accarray);$j++)
                        {
                                $accname1=$accarray[$j];
                                //echo $accname1=$accarray[$j+1];

                                $file_name=$accname1."_budget.xml";
                                $tt=$acctpath."/".$file_name;
                                $doc = new DomDocument();
                                $doc->formatOutput = true;
                                $doc->load($tt);
                                $xpath = new DomXPath($doc);

                                $budgetcode = $xpath->query("/Budgets/Budget_Name/Code");
                                $budgetname = $xpath->query("/Budgets/Budget_Name/Budgetname");
                                $budgetbdbalance = $xpath->query("/Budgets/Budget_Name/Bd_balance");
                                $budgetgroupid = $xpath->query("/Budgets/Budget_Name/Group_id");
                                $budgetallowedover = $xpath->query("/Budgets/Budget_Name/Allowedover");
                                $budgetconsumeamount = $xpath->query("/Budgets/Budget_Name/Consume_amount");

                                //echo "*** Acc Name ***".$file_name;

                                $Budgetcode = @$budgetcode->item($i)->nodeValue;
                                $Budgetname1 = @$budgetname->item($i)->nodeValue;

                                if($Budgetcode == "50")
                                {
					
                                        //$Budgetname1 = @$budgetname->item($i)->nodeValue;
                                        $Budgetbdbalance = @$budgetbdbalance->item($i)->nodeValue;
                                        $Budgetgroupid = @$budgetgroupid->item($i)->nodeValue;
                                        $Budgetallowedover = @$budgetallowedover->item($i)->nodeValue;
                                        $Budgetconsumeamount = @$budgetconsumeamount->item($i)->nodeValue;
                                        $sumbdbalance = $sumbdbalance+$Budgetbdbalance;
                                        $sumconsumeamt = $sumconsumeamt+$Budgetconsumeamount;
                                }
                                else
                                {
                                        //$Budgetname1 = @$budgetname->item($i)->nodeValue;
                                        $Budgetbdbalance = @$budgetbdbalance->item($i)->nodeValue;
                                        $Budgetgroupid = @$budgetgroupid->item($i)->nodeValue;
                                        $Budgetallowedover = @$budgetallowedover->item($i)->nodeValue;
                                        $Budgetconsumeamount = @$budgetconsumeamount->item($i)->nodeValue;
                                        $sumbdbalance = $sumbdbalance+$Budgetbdbalance;
                                        $sumconsumeamt = $sumconsumeamt+$Budgetconsumeamount;
                                }

                        }
				$Budgetname1."||".$sumbdbalance;
                                $doc = new DOMDocument();
                                $doc->formatOutput = true;

                                $file_name=$mergebudgetfile."_budget.xml";
                                $tt=$acctpath."/".$file_name;
                                if(file_exists($tt))
                                {

                                        $doc->preserveWhiteSpace = false;
                                        $doc->load($tt);
                                        $Budgets = $doc->firstChild;
                                        $Budget_Name = $doc->createElement('Budget_Name');

                                        $Code = $doc->createElement('Code');
                                        $textNode = $doc->createTextNode($Budgetcode);
                                        $Code->appendChild($textNode);
                                        $Budget_Name->appendChild($Code);
					$Budgetname1;
                                        $Budgetname = $doc->createElement('Budgetname');
                                        $textNode = $doc->createTextNode($Budgetname1);
                                        $Budgetname->appendChild($textNode);
                                        $Budget_Name->appendChild($Budgetname);

                                        $Bd_balance = $doc->createElement('Bd_balance');
                                        $textNode = $doc->createTextNode($sumbdbalance);
                                        $Bd_balance->appendChild($textNode);
                                        $Budget_Name->appendChild($Bd_balance);

                                        $Group_id = $doc->createElement('Group_id');
                                        $textNode = $doc->createTextNode($Budgetgroupid);
                                        $Group_id->appendChild($textNode);
                                        $Budget_Name->appendChild($Group_id);

                                        $Allowedover = $doc->createElement('Allowedover');
                                        $textNode = $doc->createTextNode($Budgetallowedover);
                                        $Allowedover->appendChild($textNode);
                                        $Budget_Name->appendChild($Allowedover);

                                        $Consume_amount = $doc->createElement('Consume_amount');
                                        $textNode = $doc->createTextNode($sumconsumeamt);
                                        $Consume_amount->appendChild($textNode);
                                        $Budget_Name->appendChild($Consume_amount);

                                        $Budgets->appendChild($Budget_Name);

                                        $ttt=$doc->saveXML();
                                        $handle = fopen($tt, "w");
                                        fwrite($handle, $ttt);
                                        fclose($handle);

                                }
                                else
                                {
                                        $r = $doc->createElement( "Budgets" );
                                        $doc->appendChild( $r );
                                        $b = $doc->createElement( "Budget_Name" );

                                        $Code = $doc->createElement( "Code" );
                                        $Code->appendChild($doc->createTextNode($Budgetcode));
                                        $b->appendChild( $Code );

                                        $Budgetname = $doc->createElement( "Budgetname");
                                        $Budgetname->appendChild($doc->createTextNode($Budgetname1));
                                        $b->appendChild( $Budgetname );

                                        $Bd_balance = $doc->createElement( "Bd_balance");
                                        $Bd_balance->appendChild($doc->createTextNode($sumbdbalance));
                                        $b->appendChild( $Bd_balance );

                                        $Group_id = $doc->createElement( "Group_id");
                                        $Group_id->appendChild($doc->createTextNode($Budgetgroupid));
                                        $b->appendChild( $Group_id );

                                        $Allowedover = $doc->createElement( "Allowedover");
                                        $Allowedover->appendChild($doc->createTextNode($Budgetallowedover));
                                        $b->appendChild( $Allowedover );

                                        $Consume_amount = $doc->createElement( "Consume_amount");
                                        $Consume_amount->appendChild($doc->createTextNode($sumconsumeamt));
                                        $b->appendChild( $Consume_amount );

					$r->appendChild( $b );
                                        $doc->save($tt);
                                        $doc->saveXML();
                                }

                }

                $data['accounts'] = $file_name;
                $data['max'] = $max1;
		$data['totalbudget'] = $totalbudget;
		$data['budgetopen'] = $budgetopen;
		$data['totalconsume'] = $totalconsume;
		$data['budgetconsume'] = $budgetconsume;
//                $this->template->load('user_template', 'user/aggregatereports/aggregatebudget',$data);
                $this->template->load('user_template', 'user/aggregatebudget',$data);
                return ;

        }
}

/* End of file user.php */
/* Location: ./system/application/controllers/user.php */
