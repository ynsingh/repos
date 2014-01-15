<?php

class Create extends Controller {

	function Create()
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
		$this->template->set('page_title', 'Create account');

		/* Form fields */
		$default_start = '01/04/';
		$default_end = '31/03/';
		if (date('n') > 3)
		{
			$default_start .= date('Y');
			$default_end .= date('Y') + 1;
		} else {
			$default_start .= date('Y') - 1;
			$default_end .= date('Y');
		}

		/* Form fields */
		$data['account_label'] = array(
			'name' => 'account_label',
			'id' => 'account_label',
			'maxlength' => '30',
			'size' => '30',
			'value' => '',
		);

		$data['org_name'] = array(
                        'name' => 'org_name',
                        'id' => 'org_name',
                        'maxlength' => '200',
                        'size' => '30',
                        'value' => '',
                );

		$data['unit_name'] = array(
                        'name' => 'unit_name',
                        'id' => 'unit_name',
                        'maxlength' => '200',
                        'size' => '30',
                        'value' => '',
                );


		$data['account_name'] = array(
			'name' => 'account_name',
			'id' => 'account_name',
			'maxlength' => '100',
			'size' => '40',
			'value' => '',
		);
		$data['account_address'] = array(
			'name' => 'account_address',
			'id' => 'account_address',
			'rows' => '4',
			'cols' => '47',
			'value' => '',
		);
		$data['account_email'] = array(
			'name' => 'account_email',
			'id' => 'account_email',
			'maxlength' => '100',
			'size' => '40',
			'value' => '',
		);
		$data['fy_start'] = array(
			'name' => 'fy_start',
			'id' => 'fy_start',
			'maxlength' => '11',
			'size' => '11',
			'value' => $default_start,
		);
		$data['fy_end'] = array(
			'name' => 'fy_end',
			'id' => 'fy_end',
			'maxlength' => '11',
			'size' => '11',
			'value' => $default_end,
		);
		$data['account_currency'] = array(
			'name' => 'account_currency',
			'id' => 'account_currency',
			'maxlength' => '10',
			'size' => '10',
			'value' => '',
		);
		$data['account_date_options'] = array(
			'dd/mm/yyyy' => 'Day / Month / Year',
			'mm/dd/yyyy' => 'Month / Day / Year',
			'yyyy/mm/dd' => 'Year / Month / Day',
		);
		$data['account_date'] = 'dd/mm/yyyy';
		$data['account_timezone'] = 'UTC';

		$data['chart_account_options'] = array(
			'minimal' => 'minimal',
			'standard' => 'standard',
			'mhrd' => 'MHRD Format',
		);
		$data['chart_account'] = 'minimal';

		$data['database_name'] = array(
			'name' => 'database_name',
			'id' => 'database_name',
			'maxlength' => '100',
			'size' => '40',
			'value' => '',
		);

		$data['database_username'] = array(
			'name' => 'database_username',
			'id' => 'database_username',
			'maxlength' => '100',
			'size' => '40',
			'value' => '',
		);

		$data['database_password'] = array(
			'name' => 'database_password',
			'id' => 'database_password',
			'maxlength' => '100',
			'size' => '40',
			'value' => '',
		);

		$data['database_host'] = array(
			'name' => 'database_host',
			'id' => 'database_host',
			'maxlength' => '100',
			'size' => '40',
			'value' => 'localhost',
		);

		$data['database_port'] = array(
			'name' => 'database_port',
			'id' => 'database_port',
			'maxlength' => '100',
			'size' => '40',
			'value' => '3306',
		);
		$data['create_database'] = FALSE;

		/* Form validations */
		$this->form_validation->set_rules('account_label', 'Label', 'trim|required|min_length[2]|max_length[30]|alpha_numeric');
		$this->form_validation->set_rules('org_name', 'Organisation Name', 'trim|required|min_length[3]|max_length[200]|alpha_numeric');
		$this->form_validation->set_rules('unit_name', 'Unit Name', 'trim|required|min_length[2]|max_length[200]|alpha_numeric');
		$this->form_validation->set_rules('account_name', 'Account Name', 'trim|required|min_length[2]|max_length[100]');
		$this->form_validation->set_rules('account_address', 'Account Address', 'trim|max_length[255]');
		$this->form_validation->set_rules('account_email', 'Account Email', 'trim|valid_email');
		if ($_POST)
		{
			$this->config->set_item('account_date_format', $this->input->post('account_date', TRUE));
			$this->form_validation->set_rules('fy_start', 'Financial Year Start', 'trim|required|is_date');
			$this->form_validation->set_rules('fy_end', 'Financial Year End', 'trim|required|is_date');
		}
		$this->form_validation->set_rules('account_currency', 'Currency', 'trim|max_length[10]');
		$this->form_validation->set_rules('account_date', 'Date', 'trim|max_length[10]');
		$this->form_validation->set_rules('account_timezone', 'Timezone', 'trim|max_length[6]');


		$this->form_validation->set_rules('database_name', 'Database Name', 'trim|required');
		$this->form_validation->set_rules('database_username', 'Database Username', 'trim|required');

		/* Repopulating form */
		if ($_POST)
		{
			$data['account_label']['value'] = $this->input->post('account_label', TRUE);
			$data['org_name']['value'] = $this->input->post('org_name', TRUE);
			$data['unit_name']['value'] = $this->input->post('unit_name', TRUE);
			$data['account_name']['value'] = $this->input->post('account_name', TRUE);
			$data['account_address']['value'] = $this->input->post('account_address', TRUE);
			$data['account_email']['value'] = $this->input->post('account_email', TRUE);
			$data['fy_start']['value'] = $this->input->post('fy_start', TRUE);
			$data['fy_end']['value'] = $this->input->post('fy_end', TRUE);
			$data['account_currency']['value'] = $this->input->post('account_currency', TRUE);
			$data['account_date'] = $this->input->post('account_date', TRUE);
			$data['account_timezone'] = $this->input->post('account_timezone', TRUE);
			$data['chart_account'] = $this->input->post('chart_account', TRUE);

			$data['create_database'] = $this->input->post('create_database', TRUE);
			$data['database_name']['value'] = $this->input->post('database_name', TRUE);
			$data['database_username']['value'] = $this->input->post('database_username', TRUE);
			$data['database_password']['value'] = $this->input->post('database_password', TRUE);
			$data['database_host']['value'] = $this->input->post('database_host', TRUE);
			$data['database_port']['value'] = $this->input->post('database_port', TRUE);
		}

		/* Validating form */
		if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('admin_template', 'admin/create', $data);
			return;
		}
		else
		{
			$data_account_label = $this->input->post('account_label', TRUE);
			$data_account_label = strtolower($data_account_label);
			$data_org_name = $this->input->post('org_name', TRUE);
			$data_unit_name = $this->input->post('unit_name', TRUE);
			$data_account_name = $this->input->post('account_name', TRUE);
			$data_account_address = $this->input->post('account_address', TRUE);
			$data_account_email = $this->input->post('account_email', TRUE);
			$data_fy_start = date_php_to_mysql($this->input->post('fy_start', TRUE));
			$data_fy_end = date_php_to_mysql_end_time($this->input->post('fy_end', TRUE));
			$data_account_currency = $this->input->post('account_currency', TRUE);
			$data_account_date_form = $this->input->post('account_date', TRUE);
			/* Checking for valid format */
			if ($data_account_date_form == "dd/mm/yyyy")
				$data_account_date = "dd/mm/yyyy";
			else if ($data_account_date_form == "mm/dd/yyyy")
				$data_account_date = "mm/dd/yyyy";
			else if ($data_account_date_form == "yyyy/mm/dd")
				$data_account_date = "yyyy/mm/dd";
			else
				$data_account_date = "dd/mm/yyyy";
			$data_account_timezone = $this->input->post('timezones', TRUE);

			$data_chart_account = $this->input->post('chart_account', TRUE);

			$data_database_type = 'mysql';
			$data_database_host = $this->input->post('database_host', TRUE);
			$data_database_port = $this->input->post('database_port', TRUE);
			$data_database_name = $this->input->post('database_name', TRUE);
			$data_database_username = $this->input->post('database_username', TRUE);
			$data_database_password = $this->input->post('database_password', TRUE);

			/* check for database label exist */
                        $db1=$this->load->database('login', TRUE);
			$db1->select('dblable')->from('bgasAccData')->where('dblable', $data_account_label);
                        if ($db1->get()->num_rows() > 1)
                        {
                                $this->messages->add('Account with same label already exists.', 'error');
				$this->template->load('admin_template', 'admin/create', $data);
                                return;
                        }
			$db1->close();
		//	$ini_file = $this->config->item('config_path') . "accounts/" . $data_account_label . ".ini";

			/* Check if database ini file exists */
		/*	if (get_file_info($ini_file))
			{
				$this->messages->add('Account with same label already exists.', 'error');
				$this->template->load('admin_template', 'admin/create', $data);
				return;
			}
			/*

			/* Check if start date is less than end date */
			if ($data_fy_end <= $data_fy_start)
			{
				$this->messages->add('Financial start date cannot be greater than end date.', 'error');
				$this->template->load('admin_template', 'admin/create', $data);
				return;
			}

			if ($data_database_host == "")
				$data_database_host = "localhost";
			if ($data_database_port == "")
				$data_database_port = "3306";

			/* Creating account database */
			if ($this->input->post('create_database', TRUE) == "1")
			{
				$ini_file = $this->config->item('config_path') . "accounts/sqladmin.ini";
				if ( ! get_file_info($ini_file))
                                {       
                                	$this->messages->add('MySQL settings file label sqladmin.ini does not exists.', 'error');
                                        $this->messages->add('So you first set the MySQL administrator user name and password.', 'error');
                                        $this->template->load('admin_template', 'admin/create', $data);
                                        return;
                                }
                                else
                                {
                                        $data_sql_accounts = parse_ini_file($ini_file);
                                        $data_database_admin_username = $data_sql_accounts['sql_admin_name'];
                                        $data_database_admin_password = $data_sql_accounts['sql_admin_password'];

					$new_link = @mysql_connect($data_database_host . ':' . $data_database_port, $data_database_admin_username, $data_database_admin_password);
					if ($new_link)
					{
						/* Check if database already exists */
						$db_selected = mysql_select_db($data_database_name, $new_link);
						if ($db_selected) {
							mysql_close($new_link);
							$this->messages->add('Database already exists.', 'error');
							$this->template->load('admin_template', 'admin/create', $data);
							return;
						}
					
						/* Creating account database */
						$db_create_q = 'CREATE DATABASE ' . mysql_real_escape_string($data_database_name).'; ';
						$db_create_q1 = 'GRANT ALL ON '. mysql_real_escape_string($data_database_name).'.* TO '. mysql_real_escape_string($data_database_username).'@127.0.0.1 IDENTIFIED BY "'. mysql_real_escape_string($data_database_password).'"; ';
						$db_create_q2 = 'GRANT ALL ON '. mysql_real_escape_string($data_database_name).'.* TO '. mysql_real_escape_string($data_database_username).'@localhost IDENTIFIED BY "'. mysql_real_escape_string($data_database_password).'"; ';
						$eflag=true; $eflag1=true; $eflag2=true;
						if (mysql_query($db_create_q, $new_link))
						{
							$eflag=false;
							$this->messages->add('Created new account database.', 'success');
						} 
						if (mysql_query($db_create_q1, $new_link))
						{
							$eflag1=false;
							$this->messages->add('Granting permission to user to access new database  with local ip.', 'success');
						} 
						if (mysql_query($db_create_q2, $new_link))
						{
							$eflag2=false;
							$this->messages->add('Granting permission to user to access new database  with local name.', 'success');
						} 
						if ($eflag ||$eflag1  || $eflag2) {
							$this->messages->add('Error creating account database. ' . mysql_error(), 'error');
							$this->template->load('admin_template', 'admin/create', $data);
							return;
						}
						mysql_close($new_link);
					} else {
						$this->messages->add('Error connecting to database. ' . mysql_error(), 'error');
						$this->template->load('admin_template', 'admin/create', $data);
						return;
					}
				}
			}

			/* Setting database */
			$dsn = "mysql://${data_database_username}:${data_database_password}@${data_database_host}:${data_database_port}/${data_database_name}";
			$newacc = $this->load->database($dsn, TRUE);

			if ( ! $newacc->conn_id)
			{
				$this->messages->add('Error connecting to database.', 'error');
				$this->template->load('admin_template', 'admin/create', $data);
				return;
			} else if ($newacc->_error_message() != "") {
				$this->messages->add('Error connecting to database. ' . $newacc->_error_message(), 'error');
				$this->template->load('admin_template', 'admin/create', $data);
				return;
			} else if ($newacc->query("SHOW TABLES")->num_rows() > 0) {
				$this->messages->add('Selected database in not empty.', 'error');
				$this->template->load('admin_template', 'admin/create', $data);
				return;
			} else {
				/* Executing the database setup script */
				$setup_account = read_file('system/application/controllers/admin/schema.sql');
				$setup_account_array = explode(";", $setup_account);
				foreach($setup_account_array as $row)
				{
					if (strlen($row) < 5)
						continue;
					$newacc->query($row);
					if ($newacc->_error_message() != "")
					{
						$this->messages->add('Error initializing account database under schema.sql.'.$row, 'error');
						$this->messages->add('Error initializing account database under schema.sql.'.$newacc->_error_message(), 'error');
						$this->template->load('admin_template', 'admin/create', $data);
						return;
					}
				}
				$this->messages->add('Initialized account database.', 'success');

				/* Initial account setup */
				//if $data_chart_account is minimal
				if (($data_chart_account == 'minimal') || ($data_chart_account == '')){
					$setup_initial_data = read_file('system/application/controllers/admin/minimal.sql');
				}
				elseif ( $data_chart_account == 'mhrd'){
					$setup_initial_data = read_file('system/application/controllers/admin/mhrdedu.sql');
				}
					//else $data_chart_account is standard
				elseif ( $data_chart_account == 'standard'){
					$setup_initial_data = read_file('system/application/controllers/admin/initialize.sql');
				}
				$setup_initial_data_array = explode(";", $setup_initial_data);
				$newacc->trans_start();
				foreach($setup_initial_data_array as $row)
				{
					if (strlen($row) < 5)
						continue;
					$newacc->query($row);
					if ($newacc->_error_message() != "")
					{
						$this->messages->add('Error initializing basic accounts data.'.$newacc->_error_message(), 'error');
						$newacc->trans_rollback();
						$this->template->load('admin_template', 'admin/create', $data);
						return;
					}
				}
				$newacc->trans_complete();
				$this->messages->add('Initialized basic accounts data.', 'success');

				/* Adding account settings */
				$newacc->trans_start();
				if ( ! $newacc->query("INSERT INTO settings (id, name, address, email, fy_start, fy_end, currency_symbol, date_format, timezone, manage_inventory, account_locked, email_protocol, email_host, email_port, email_username, email_password, print_paper_height, print_paper_width, print_margin_top, print_margin_bottom, print_margin_left, print_margin_right, print_orientation, print_page_format, database_version, ins_name, dept_name, uni_name) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", array(1, $data_account_name, $data_account_address, $data_account_email, $data_fy_start, $data_fy_end, $data_account_currency, $data_account_date, $data_account_timezone, 0, 0, '', '', 0, '', '', 0, 0, 0, 0, 0, 0, '', '', 4, $data_org_name, '', $data_unit_name)))
				{
					$newacc->trans_rollback();
					$this->messages->add('Error adding account settings.', 'error');
					$this->template->load('admin_template', 'admin/create', $data);
					return;
				} else {
					$newacc->trans_complete();
					$this->messages->add('Added account settings.', 'success');
				}

				/* Adding org name unit name year and database name in login database */

				$date=Date("Y");
				$m = Date("m");
				if($m>3){
					$dy = $date + 1;
					$fy=$date."-".$dy;
				}
				else{
					$dy = $date - 1;
					$fy = $dy."-".$date;
				}
				$tablebad="bgasAccData";
				$db1=$this->load->database('login', TRUE);
                                $db1->trans_start();
                                $insert_data = array(
	                                'organization'=> $data_org_name,
                                        'unit'=>  $data_unit_name,
                                        'databasename' =>  $data_database_name,
                                        'fyear' => $fy,
                                        'uname' => $data_database_username,
                                        'dbpass' => $data_database_password,
                                        'hostname' => $data_database_host,
                                        'port' => $data_database_port,
                                        'dbtype' => $data_database_type,
                                        'dblable' => $data_account_label,
                                );

                                if ( ! $db1->insert($tablebad, $insert_data))
                                {
                                	$db1->trans_rollback();
                                        $this->messages->add('Error in Adding value in  bgasAccData table under login data base ' . $data_database_name . '.', 'error');
					$db1->close();
                                        $this->template->load('admin_template', 'admin/create', $data);
                                        return;
                                } else {
                                        $db1->trans_complete();
                                        $this->messages->add('Added Values in bgasAccData table under login data base- ' . $data_database_name . '.', 'success');

                                }
                                $db1->close();

				/* Adding account settings to file. Code copied from manage controller */
				/* below code is replaced with mysql code
				$con_details = "[database]" . "\r\n" . "db_type = \"" . $data_database_type . "\"" . "\r\n" . "db_hostname = \"" . $data_database_host . "\"" . "\r\n" . "db_port = \"" . $data_database_port . "\"" . "\r\n" . "db_name = \"" . $data_database_name . "\"" . "\r\n" . "db_username = \"" . $data_database_username . "\"" . "\r\n" . "db_password = \"" . $data_database_password . "\"" . "\r\n";

				$con_details_html = '[database]' . '<br />db_type = "' . $data_database_type . '"<br />db_hostname = "' . $data_database_host . '"<br />db_port = "' . $data_database_port . '"<br />db_name = "' . $data_database_name . '"<br />db_username = "' . $data_database_username . '"<br />db_password = "' . $data_database_password . '"<br />';
*/
				/* Writing the connection string to end of file - writing in 'a' append mode */
/*				if ( ! write_file($ini_file, $con_details))
				{
					$this->messages->add('Failed to create account settings file. Check if "' . $ini_file . '" file is writable.', 'error');
					$this->messages->add('You can manually create a text file "' . $ini_file . '" with the following content :<br /><br />' . $con_details_html, 'error');
				} else {
					$this->messages->add('Added account settings file to list of active accounts.', 'success');
				}

*/				redirect('admin');
				return;
			}
		}
		return;
	}
}

/* End of file create.php */
/* Location: ./system/application/controllers/admin/create.php */
