<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class General {
	public function __construct()
        {
                return;
        }

	var $error_messages = array();

	/* Check format of config/accounts ini files */
	function check_account($account_name)
	{
		$CI =& get_instance();
		$logndb = $CI->load->database('login', TRUE);
                $this->logndb =& $logndb;
		$this->logndb->select('*')->from('bgasAccData')->where('dblable', $account_name);
		$acc_data = $this->logndb->get();
		if ($acc_data->num_rows() < 1)
                {
			$CI->messages->add('Either Account settings and database is missing OR Current active account is NULL so please select the account.', 'success');
                        return FALSE;
                }
	
		if ( ! $acc_data)
                {
                        $CI->messages->add('Invalid account data in database.', 'error');
                        return FALSE;
                }

                foreach($acc_data->result() as $row)
                {
                        $hostname = $row->hostname;
                        $port = $row->port;
                        $dbname = $row->databasename;
                        $dbuname = $row->uname;
                        $dbpassw= $row->dbpass;
                }

		$account_data = array(
			'db_hostname'=>$hostname,
			'db_port'=>$port,
			'db_name'=>$dbname,
			'db_username'=>$dbuname,
			'db_password'=>$dbpassw,

		);


/*		$ini_file = $CI->config->item('config_path') . "accounts/" . $account_name . ".ini";
	
		/* Check if database ini file exists *
		if ( ! get_file_info($ini_file))
		{
			$CI->messages->add('Account settings file is missing.', 'error');
			return FALSE;
		}

		/* Parsing database ini file *
		$account_data = parse_ini_file($ini_file);
		if ( ! $account_data)
		{
			$CI->messages->add('Invalid account settings.', 'error');
			return FALSE;
		}
*/
		/* Check if all needed variables are set in ini file */
		if ( ! isset($account_data['db_hostname']))
		{
			$CI->messages->add('Hostname missing from account settings file.', 'error');
			return FALSE;
		}
		if ( ! isset($account_data['db_port']))
		{
			$CI->messages->add('Port missing from account setting file. Default MySQL port is 3306.', 'error');
			return FALSE;
		}
		if ( ! isset($account_data['db_name']))
		{
			$CI->messages->add('Database name missing from account setting file.', 'error');
			return FALSE;
		}
		if ( ! isset($account_data['db_username']))
		{
			$CI->messages->add('Database username missing from account setting file.', 'error');
			return FALSE;
		}
		if ( ! isset($account_data['db_password']))
		{
			$CI->messages->add('Database password missing from account setting file.', 'error');
			return FALSE;
		}
		return $account_data;
	}

	/* Check for valid account database */
	function check_database()
	{
		$CI =& get_instance();

		/* Checking for valid database connection */
		if ($CI->db->conn_id)
		{
			/* Checking for valid database name, username, password */
			if ($CI->db->query("SHOW TABLES"))
			{
				/* Check for valid webzash database */
				if ($CI->uri->segment(1) != "update")
				{
					/* check for valid settings table */
					$valid_settings_q = mysqli_query($CI->db->conn_id,'DESC settings');
				//	$valid_settings_q = mysqli_query('DESC settings');
				//	print_r( $valid_settings_q);die;
					if ( ! $valid_settings_q)
					{
						$CI->messages->add('Invalid account database. Table settings missing in first.', 'error');
						return FALSE;
					}
					$this->check_database_version();

					$table_names = array('groups', 'ledgers', 'budgets', 'budget_allocate', 'entry_types', 'entries', 'entry_items', 'tags', 'logs', 'settings');
					foreach ($table_names as $id => $tbname)
					{
						$valid_db_q = mysqli_query($CI->db->conn_id,'DESC ' . $tbname);
						if ( ! $valid_db_q)
						{
							$CI->messages->add('Invalid account database. Table "' . $tbname . '" missing.', 'error');
							return FALSE;
						}
					}
				}
			} else {
				$CI->messages->add('Invalid database connection settings. Check whether the provided database name, username and password are valid.', 'error');
				return FALSE;
			}
		} else {
			$CI->messages->add('Cannot connect to database server. Check whether database server is running.', 'error');
			return FALSE;
		}
		return TRUE;
	}

	/* Check config/settings/general.ini file */
	function check_setting()
	{
		$CI =& get_instance();

		$setting_ini_file = $CI->config->item('config_path') . "settings/general.ini";

		/* Set default values */
		$CI->config->set_item('row_count', "20");
		$CI->config->set_item('log', "1");

		/* Check if general application settings ini file exists */
		if (get_file_info($setting_ini_file))
		{
			/* Parsing general application settings ini file */
			$cur_setting = parse_ini_file($setting_ini_file);
			if ($cur_setting)
			{
				if (isset($cur_setting['row_count']))
				{
					$CI->config->set_item('row_count', $cur_setting['row_count']);
				}
				if (isset($cur_setting['log']))
				{
				$CI->config->set_item('log', $cur_setting['log']);
				}
			}
		}
	}

	function check_user($user_name)
	{
		$CI =& get_instance();
		$logndb = $CI->load->database('login', TRUE);
		$this->logndb =& $logndb; 
			
    //$db=$this->database('login', TRUE);
	//db->load->database('login', TRUE);
	//$CI->db->from('bgasuser');
	//$CI->db->select('username,password,role,status,accounts')->where('username =', $user_name);
	//$user_data = $CI->db->get();

		// previous code 19june
		/* $this->logndb->from('bgasuser');
		$this->logndb->select('username,password,role,status,accounts')->where('username =', $user_name);
		$user_data = $this->logndb->get();
		
        foreach($user_data->result() as $row)
        {
        	$user_name1 = $row->username;
            $user_password = $row->password;
            $user_account = $row->accounts;
            $user_status = $row->status;
            $user_role= $row->role;
        }*/

        //$this->logndb->from('bgasuser');
        //$this->logndb->select('username,password,role,status,accounts')->where('username =', $user_name);
		//$this->logndb->select('id,username,password,status')->where('username =', $user_name);

		$this->logndb->select('edrpuser.id as id,edrpuser.username as username, edrpuser.password as password,edrpuser.status as status, bgasuserrolegroup.accounts as accounts, bgasuserrolegroup.role as role');
		$this->logndb->from('edrpuser')->join('bgasuserrolegroup', 'edrpuser.id = bgasuserrolegroup.userid')->where('edrpuser.username',$user_name);
		$user_data = $this->logndb->get();
        foreach($user_data->result() as $row)
        {
			$user_name1 = $row->username;
            		$user_password = $row->password;
		        $user_account = $row->accounts;
			$user_status = $row->status;
            		$user_role= $row->role;
            		$user_id = $row->id;

		}	
		//print_r($user_data);
		/*$this->logndb->select('role,accounts')->from('userrolegroup')->where('userid', $user_id);
		$query_result = $this->logndb->get();
		foreach($query_result->result() as $row)
        {
            $user_account = $row->accounts;
            $user_role= $row->role;
		}
		echo $user_account;*/
		//$user_data['accounts'] = $user_account;

		/* User validation */
//		$ini_file = $CI->config->item('config_path') . "users/" . $user_name . ".ini";

		/* Check if user data exists in sql database */
		if (!($user_data->num_rows() > 0) )
		{
			$CI->messages->add('User does not exists.', 'error');
			return FALSE;
		}

		/* Parsing user ini file */
//		$user_data = parse_ini_file($ini_file);
		if ( ! $user_data)
		{
			$CI->messages->add('Invalid user data.', 'error');
			return FALSE;
		}

		if ( ! isset($user_name1))
		{
			$CI->messages->add('Username missing from user data.', 'error');
			return FALSE;
		}
		if ( ! isset($user_password))
		{
			$CI->messages->add('Password missing from user data.', 'error');
			return FALSE;
		}
		if ( ! isset($user_status))
		{
			$CI->messages->add('Status missing from user data.', 'error');
			return FALSE;
		}
		if ( ! isset($user_role))
		{
			$CI->messages->add('Role missing from user data. Defaulting to "guest" role.', 'error');
			$user_data['role'] = $user_role;
			//$user_data['role'] = 'guest';
		}
		if ( ! isset($user_account))
		{
			$CI->messages->add('Accounts missing from user data.', 'error');
			$user_data['accounts'] = $user_account;
		}

		//$user_data['accounts'] = $user_account;
		return $user_data;
	}

	function check_database_version()
	{
		$CI =& get_instance();
		if ($CI->uri->segment(1) == "update")
			return;

		/* Loading account data */
		$CI->db->from('settings')->where('id', 1)->limit(1);
		$account_q = $CI->db->get();
		if ( ! ($account_d = $account_q->row()))
		{
			$CI->messages->add('Invalid account settings.', 'error');
			redirect('user/account');
			return;
		}

		if ($account_d->database_version < $CI->config->item('required_database_version'))
		{
			$CI->messages->add('You need to updated the account database before continuing. Click ' . anchor('update', 'here', array('ttile' => 'Click here to update account database')) . ' to update.', 'error');
			redirect('user/account');
			return;
		} else if ($account_d->database_version > $CI->config->item('required_database_version')) {
			$CI->messages->add('You need to updated the application version from <a href="http://webzash.org" target="_blank">http://webzash.org<a/> before continuing.', 'error');
			redirect('user/account');
			return;
		}
	}

	function setup_entry_types()
	{
		$CI =& get_instance();

		$CI->db->from('entry_types')->order_by('id', 'asc');
		$entry_types = $CI->db->get();
		if ($entry_types->num_rows() < 1)
		{
			$CI->messages->add('You need to create a entry type before you can create any entries.', 'error');
		}
		$entry_type_config = array();
		foreach ($entry_types->result() as $id => $row)
		{
			$entry_type_config[$row->id] = array(
				'label' => $row->label,
				'name' => $row->name,
				'description' => $row->description,
				'base_type' => $row->base_type,
				'numbering' => $row->numbering,
				'prefix' => $row->prefix,
				'suffix' => $row->suffix,
				'zero_padding' => $row->zero_padding,
				'bank_cash_ledger_restriction' => $row->bank_cash_ledger_restriction,
			);
		}
		$CI->config->set_item('account_entry_types', $entry_type_config);
	}
}

/* End of file General.php */
/* Location: ./system/application/libraries/General.php */
