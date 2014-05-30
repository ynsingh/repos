<?php

class Manage extends Controller {

	function Manage()
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
		$this->template->set('page_title', 'Manage accounts');
		$this->template->set('nav_links', array('admin/create' => 'New account'));

		/* Getting list of files in the config - accounts directory */
		$data['accounts'] = array();
/*		$db1=$this->load->database('login', TRUE);
                $this->db1->select('dblable')->from('bgasAccData');
		if ($this->db1->get()->num_rows() < 1)
                        {
                                $this->messages->add('Problem with selection of account label.', 'error');
                                $this->template->load('admin_template', 'admin/manage', $data);
                                return;
                        }
		else
		{
			$alist=$this->db1->get();
			foreach ($alist->result() as $row){
	                        $dlable = $row->dblable;
				$data['accounts'][$dlable] = $dlable;
			}
			
		}
*/
/*		$accounts_list = get_filenames($this->config->item('config_path') . 'accounts');
		$data['accounts'] = array();
		if ($accounts_list)
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
//		$db1->close();
		$this->template->load('admin_template', 'admin/manage/index', $data);
		return;
	}

	function add()
	{
		$this->template->set('page_title', 'Add account');

		/* Form fields */
		$data['database_label'] = array(
			'name' => 'database_label',
			'id' => 'database_label',
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

		/* Repopulating form */
		if ($_POST)
		{
			$data['database_label']['value'] = $this->input->post('database_label', TRUE);
			$data['org_name']['value'] = $this->input->post('org_name', TRUE);
                        $data['unit_name']['value'] = $this->input->post('unit_name', TRUE);

			$data['database_name']['value'] = $this->input->post('database_name', TRUE);
			$data['database_username']['value'] = $this->input->post('database_username', TRUE);
			$data['database_password']['value'] = $this->input->post('database_password', TRUE);
			$data['database_host']['value'] = $this->input->post('database_host', TRUE);
			$data['database_port']['value'] = $this->input->post('database_port', TRUE);
		}

		/* Form validations */
		$this->form_validation->set_rules('database_label', 'Label', 'trim|required|min_length[2]|max_length[30]|alpha_numeric');
		$this->form_validation->set_rules('org_name', 'Organisation Name', 'trim|required|min_length[3]|max_length[200]|alpha_numeric');
                $this->form_validation->set_rules('unit_name', 'Unit Name', 'trim|required|min_length[2]|max_length[200]|alpha_numeric');
		$this->form_validation->set_rules('database_name', 'Database Name', 'trim|required');

		/* Validating form */
		if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('admin_template', 'admin/manage/add', $data);
			return;
		}
		else
		{
			$data_database_label = $this->input->post('database_label', TRUE);
			$data_database_label = strtolower($data_database_label);
			$data_org_name = $this->input->post('org_name', TRUE);
                        $data_unit_name = $this->input->post('unit_name', TRUE);
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
                                $this->template->load('admin_template', 'admin/manage/add', $data);
                                return;
                        }
//                        $db1->close();

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
                        //        $db1=$this->load->database('login', TRUE);
                                $db1->trans_start();
                                $insert_data = array(
                                        'organization'=> $data_org_name,
                                        'unit'=>  $data_unit_name,
                                        'databasename' =>  $data_database_name,
                                        'fyear' =>$fy,
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
                                        $this->template->load('admin_template', 'admin/create', $data);
                                        return;
                                } else {
                                        $db1->trans_complete();
                                        $this->messages->add('Added Values in bgasAccData table under login data base- ' . $data_database_name . '.', 'success');

                                }
                                $db1->close();

				$this->messages->add('Added account to list of active accounts.', 'success');
				redirect('admin/manage');
				return;
			/* Adding account settings to file. Code copied from manage controller */
//			$ini_file = $this->config->item('config_path') . "accounts/" . $data_database_label . ".ini";

			/* Check if database ini file exists */
//			if (get_file_info($ini_file))
//			{
//				$this->messages->add('Account with same label already exists.', 'error');
//				$this->template->load('admin_template', 'admin/manage/add', $data);
//				return;
//			}

//			$con_details = "[database]" . "\r\n" . "db_type = \"" . $data_database_type . "\"" . "\r\n" . "db_hostname = \"" . $data_database_host . "\"" . "\r\n" . "db_port = \"" . $data_database_port . "\"" . "\r\n" . "db_name = \"" . $data_database_name . "\"" . "\r\n" . "db_username = \"" . $data_database_username . "\"" . "\r\n" . "db_password = \"" . $data_database_password . "\"" . "\r\n";

//			$con_details_html = '[database]' . '<br />db_type = "' . $data_database_type . '"<br />db_hostname = "' . $data_database_host . '"<br />db_port = "' . $data_database_port . '"<br />db_name = "' . $data_database_name . '"<br />db_username = "' . $data_database_username . '"<br />db_password = "' . $data_database_password . '"<br />';
			// add entry in login database for generatig report

			/* Writing the connection string to end of file - writing in 'a' append mode */
//			if ( ! write_file($ini_file, $con_details))
//			{
//				$this->messages->add('Failed to add account settings file. Check if "' . $ini_file . '" file is writable.', 'error');
//				$this->messages->add('You can manually create a text file "' . $ini_file . '" with the following content :<br /><br />' . $con_details_html, 'error');
//				$this->template->load('admin_template', 'admin/manage/add', $data);
//				return;
//			} else {
//				$this->messages->add('Added account to list of active accounts.', 'success');
//				redirect('admin/manage');
//				return;
//			}
		}
		return;
	}
	
	function edit($database_label)
	{
		$this->template->set('page_title', 'Edit account');

//		$ini_file = $this->config->item('config_path') . "accounts/" . $database_label . ".ini";

		/* Form fields */
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
			'value' => '',
		);

		$data['database_port'] = array(
			'name' => 'database_port',
			'id' => 'database_port',
			'maxlength' => '100',
			'size' => '40',
			'value' => '',
		);
		$data['database_label'] = $database_label;

		/* Repopulating form */
		if ($_POST)
		{
			$data['database_host']['value'] = $this->input->post('database_host', TRUE);
			$data['database_port']['value'] = $this->input->post('database_port', TRUE);
			$data['database_name']['value'] = $this->input->post('database_name', TRUE);
			$data['database_username']['value'] = $this->input->post('database_username', TRUE);
			$data['database_password']['value'] = $this->input->post('database_password', TRUE);
		} else {

			/* check for database label exist */
                        $db1=$this->load->database('login', TRUE);
                        $db1->select('*')->from('bgasAccData')->where('dblable', $database_label);
			$query = $db1->get();
                        if ($query->num_rows() < 1)
                        {
                                $this->messages->add('Account with label ' . $database_label . ' does not exists.', 'error');
                                $this->template->load('admin_template', 'admin/manage', $data);
                                return;
                        }
			else{
				$data['org_name']='';
		                $data['unit_name']='';
				$data['database_host']='';
				$data['database_port']='';
				$data['database_name']='';
				$data['database_username']='';
				$data['database_password']='';
				foreach($query->result() as $row){
                                	$data['org_name']['value'] = $row -> organization;
                                	$data['unit_name'] ['value']= $row -> unit;
					$data['database_host']['value'] = $row -> hostname;
                        	        $data['database_port']['value'] = $row -> port;
                	                $data['database_name']['value'] = $row -> databasename;
        	                        $data['database_username'] ['value']= $row -> uname;
	                                $data['database_password']['value'] = $row -> dbpass;
                        	}

			}
			$db1->close();
		}
			/* Check if database ini file exists *
			if ( ! get_file_info($ini_file))
			{
				$this->messages->add('Account settings file labeled ' . $database_label . ' does not exists.', 'error');
				redirect('admin/manage');
				return;
			} else {
				/* Parsing database ini file *
				$active_accounts = parse_ini_file($ini_file);
				if ( ! $active_accounts)
				{
					$CI->messages->add('Invalid account settings file', 'error');
				} else {
					/* Check if all needed variables are set in ini file *
					if (isset($active_accounts['db_hostname']))
						$data['database_host']['value'] = $active_accounts['db_hostname'];
					else
						$CI->messages->add('Hostname missing from account settings file', 'error');
					
					if (isset($active_accounts['db_port']))
						$data['database_port']['value'] = $active_accounts['db_port'];
					else
						$CI->messages->add('Port missing from account settings file. Default MySQL port is 3306', 'error');

					if (isset($active_accounts['db_name']))
						$data['database_name']['value'] = $active_accounts['db_name'];
					else
						$CI->messages->add('Database name missing from account settings file', 'error');

					if (isset($active_accounts['db_username']))
						$data['database_username']['value'] = $active_accounts['db_username'];
					else
						$CI->messages->add('Database username missing from account settings file', 'error');

					if ( ! isset($active_accounts['db_password']))
						$CI->messages->add('Database password missing from account settings file', 'error');
				}
			}
		}

		/* Form validations */
		$this->form_validation->set_rules('database_name', 'Database Name', 'trim|required');

		// get the values from database and displayed
	/*	$db1=$this->load->database('login', TRUE);
		$db1->select('organization,unit')->from('bgasAccData')->where('databasename',($this->input->post('database_name', TRUE)));
		$query = $db1->get();
		$data['org_name']='';
		$data['unit_name']='';
		if ($query->num_rows() > 0){
			foreach($query->result() as $row){
				$data['org_name'] = $row -> organization;
				$data['unit_name'] = $row -> unit; 
			}
		}
		else{
			 $this->messages->add('Organization name and unit name not exist','error');
		}


		/* Validating form */
		if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('admin_template', 'admin/manage/edit', $data);
			return;
		}
		else
		{
			$data_database_type = 'mysql';
			$data_database_host = $this->input->post('database_host', TRUE);
			$data_database_port = $this->input->post('database_port', TRUE);
			$data_database_name = $this->input->post('database_name', TRUE);
			$data_database_username = $this->input->post('database_username', TRUE);
			$data_database_password = $this->input->post('database_password', TRUE);

			$tablebad="bgasAccData";
                        $db1=$this->load->database('login', TRUE);
                        $db1->trans_start();
                        $update_data = array(
	                        'databasename' =>  $data_database_name,
                                'uname' => $data_database_username,
                                'dbpass' => $data_database_password,
                                'hostname' => $data_database_host,
                                'port' => $data_database_port,
                        );
			if ( ! $db1->where('dblable', $database_label)->update($tablebad, $update_data))
                                {
                                        $db1->trans_rollback();
                                        $this->messages->add('Error in updating value in  bgasAccData table under login data base ' . $data_database_name . '.', 'error');
                                        $db1->close();
                                        $this->template->load('admin_template', 'admin/create', $data);
                                        return;
                                } else {
                                        $db1->trans_complete();
                                        $this->messages->add('Updating Values in bgasAccData table under login data base- ' . $data_database_name . '.', 'success');
					redirect('admin/manage');
					return;
                        }
                        $db1->close();
/*

			$ini_file = $this->config->item('config_path') . "accounts/" . $database_label . ".ini";

			$con_details = "[database]" . "\r\n" . "db_type = \"" . $data_database_type . "\"" . "\r\n" . "db_hostname = \"" . $data_database_host . "\"" . "\r\n" . "db_port = \"" . $data_database_port . "\"" . "\r\n" . "db_name = \"" . $data_database_name . "\"" . "\r\n" . "db_username = \"" . $data_database_username . "\"" . "\r\n" . "db_password = \"" . $data_database_password . "\"" . "\r\n";

			$con_details_html = '[database]' . '<br />db_type = "' . $data_database_type . '"<br />db_hostname = "' . $data_database_host . '"<br />db_port = "' . $data_database_port . '"<br />db_name = "' . $data_database_name . '"<br />db_username = "' . $data_database_username . '"<br />db_password = "' . $data_database_password . '"<br />';

			/* Writing the connection string to end of file - writing in 'a' append mode *
			if ( ! write_file($ini_file, $con_details))
			{
				$this->messages->add('Failed to edit account settings file. Check if "' . $ini_file . '" file is writable.', 'error');
				$this->messages->add('You can manually update the text file "' . $ini_file . '" with the following content :<br /><br />' . $con_details_html, 'error');
				$this->template->load('admin_template', 'admin/manage/edit', $data);
				return;
			} else {
				$this->messages->add('Updated account settings.', 'success');
				redirect('admin/manage');
				return;
			}
*/
		}
		return;
	}

	// deletion of an account as well database and taking backups of accounts	

	function delete($database_label)
	{

                $db1=$this->load->database('login', TRUE);
		
		//get database detail of account
		$db1->from('bgasAccData')->where('dblable', $database_label);
               	$accountdetail = $db1->get();
                foreach ($accountdetail->result() as $row)
                {
                        $databasehost=$row->hostname;
                        $dbname= $row->databasename;
                        $databaseport=$row->port;
                        $databaseusername=$row->uname;
                        $databasepassword=$row->dbpass;
                }
		
                $db1->close();

	        //call method for taking backup
	
		$this->backup_tables($databasehost,$databaseusername,$databasepassword,$dbname);
		redirect('admin/manage');
		
		return;
	}
	
	function backup_tables($databasehost,$databaseuser,$databasepassword,$dbname,$tables = '*')
	{
		$con = mysql_connect($databasehost,$databaseuser,$databasepassword);
		mysql_select_db($dbname,$con);

		//get all of the tables
		if($tables == '*')
		{	
			$tables = array();
			$result = mysql_query('SHOW TABLES');
			while($row = mysql_fetch_row($result))
			{
				$tables[] = $row[0];
			}
		}
		else
		{
			$tables = is_array($tables) ? $tables : explode(',',$tables);
		}		
		$return = "";
		//cycle through all the table in database $dbname
		foreach($tables as $table)
		{
			$result = mysql_query('SELECT * FROM '.$table);
			$num_fields = mysql_num_fields($result);
			$return.= 'DROP TABLE '.$table.';';
			$row2 = mysql_fetch_row(mysql_query('SHOW CREATE TABLE '.$table));
			$return.= "nn".$row2[1].";nn";

			while($row = mysql_fetch_row($result))
			{
				$return.= 'INSERT INTO '.$table.' VALUES(';
				for($j=0; $j<$num_fields; $j++)
				{
					$row[$j] = addslashes($row[$j]);
					$row[$j] = preg_replace("#n#","n",$row[$j]);
					if (isset($row[$j])) { $return.= '"'.$row[$j].'"' ; } else { $return.= '""'; }
					if ($j<($num_fields-1)) { $return.= ','; }
				}
				$return.= ");n";
			}
			$return.="nnn";
		}

		//save the backup file of deleted account

		$bpath=$this->config->item('backup_path');		
		$handle = fopen($bpath.$dbname.'-'.date("Y-m-d").'.sql','w+') or die('Can\'t open file');
		fwrite($handle,$return);
		fclose($handle);
		
		//drop the database
	
		$link = mysql_connect($databasehost, $databaseuser, $databasepassword);
		if (!$link) {
			die('Could not connect: ' . mysql_error());
		}
		$sql = 'DROP DATABASE '.$dbname;
		if (mysql_query($sql, $link)) {
    			echo "Database ".$dbname. " was successfully dropped\n";
		} else {
    			echo 'Error dropping database: ' . mysql_error() . "\n";
		}
		
		//delete the account record from bgasAccData table

		$db1=$this->load->database('login', TRUE);
		//$sqldel="DELETE from bgasAccData where databasename='$databaseuser'";
		//$result = mysql_query($sqldel);
	
		$db1->from('bgasAccData')->where('databasename', $dbname);	
		$accdetail = $db1->get();
		$dblable;	
		foreach ($accdetail->result() as $row)
                {
                        $dblable=$row->dblable;
                }
		$db1->from('bgasuser');
		$query1 = $db1->get();
                foreach($query1->result() as $row)
                {
			$id = $row->id;
	                $accname = $row->accounts;
			if (strpos($accname, $dblable) !== false)
			{
				$straccnew="";
				$dblablenew= $dblable.",";
				if (strpos($accname, $dblablenew) !== false)	
				{
					$straccnew=str_replace($dblable.",","",$accname);
				}
				else
				{
					$straccnew=str_replace($dblable,"",$accname);
				}
				$update_data = array('accounts' => $straccnew);

				$db1->where('id', $id)->update('bgasuser', $update_data);
			}
                
		}
                //delete the account record from bgasAccData table

                //$db1=$this->load->database('login', TRUE);
		//his->messages->add('value===>'.$dblable);
                $sqldel="DELETE from bgasAccData where dblable='$dblable'";
                $result = mysql_query($sqldel);

		$this->messages->add('Account <b>' .$dblable. '</b> has been deleted Successfully and backup of account is stored in backups directory');
	 	return;	
	}
	
}

/* End of file manage.php */
/* Location: ./system/application/controllers/admin/manage.php */
