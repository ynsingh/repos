<?php

class SqlAdmin extends CI_Controller {
 function __construct() {
        parent::__construct();
        //if(empty($this->session->userdata('id_user'))) {
            //$this->session->set_flashdata('flash_data', 'You don\'t have access!');
           // redirect('welcome');
       // }
    }

        function SqlAdmin()
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
                $this->template->set('page_title', 'Add/Update MySQL Admin Password');

		$ini_file = $this->config->item('config_path') . "accounts/sqladmin.ini";
		 /* Form fields */
                $data['sql_admin_name'] = array(
                        'name' => 'sql_admin_name',
                        'id' => 'sql_admin_name',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => '',
                );

                $data['sql_admin_password'] = array(
                        'name' => 'sql_admin_password',
                        'id' => 'sql_admin_password',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => '',
                );

		/* Repopulating form */
                if ($_POST)
                {
                        $data['sql_admin_name']['value'] = $this->input->post('sql_admin_name', TRUE);
                        $data['sql_admin_password']['value'] = $this->input->post('sql_admin_password', TRUE);
                } else {
		/* Check if database ini file exists */
                        if ( ! get_file_info($ini_file))
                        {
                                $this->messages->add('MySQL settings file labeled sqladmin.ini does not exists.', 'error');
                              //  redirect('admin');
                               // return;
                        } else {
                                /* Parsing database ini file */
                                $data_sql_accounts = parse_ini_file($ini_file);
//                                $this->messages->add('MySQL settings file labeled sqladmin.ini exists.', 'error');
				if ( ! $data_sql_accounts)
                                {
                                        $this->messages->add('Invalid account settings file', 'error');
                                } else {
                                        /* Check if all needed variables are set in ini file */
                                        if (isset($data_sql_accounts['sql_admin_name']))
                                                $data['sql_admin_name']['value'] = $data_sql_accounts['sql_admin_name'];
                                        else
                                                $this->messages->add('Sql admin username missing from account settings file', 'error');
                                        
                                        if (isset($data_sql_accounts['sql_admin_password']))
                                                $data['sql_admin_password']['value'] = $data_sql_accounts['sql_admin_password'];
                                        else
                                                $this->messages->add('Sql admin Database password missing from account settings file', 'error');
                                }

			}
		}

		 /* Form validations */
                $this->form_validation->set_rules('sql_admin_name', 'MySQL Admin Name', 'trim|required');
//                $this->form_validation->set_rules('sql_admin_password', 'MySQL Admin Password', 'trim|required');

		 /* Validating form */
                if ($this->form_validation->run() == FALSE)
                {
                        $this->messages->add(validation_errors(), 'error');
                        $this->template->load('admin_template', 'admin/sqlAdmin', $data);
                        return;
                }
                else
                {
			$data_database_username = $this->input->post('sql_admin_name', TRUE);
                        $data_database_password = $this->input->post('sql_admin_password', TRUE);

			$ini_file = $this->config->item('config_path') . "accounts/sqladmin.ini";

                        $con_details = "[database]" . "\r\n" . "sql_admin_name = \"" . $data_database_username . "\"" . "\r\n" . "sql_admin_password = \"" . $data_database_password . "\"" . "\r\n";

                        $con_details_html = '[database]' . '<br />sql_admin_name = "' . $data_database_username . '"<br />sql_admin_password = "' . $data_database_password . '"<br />';

                        /* Writing the connection string to end of file - writing in 'a' append mode */
                        if ( ! write_file($ini_file, $con_details))
                        {
                                $this->messages->add('Failed to edit sql admin settings file. Check if "' . $ini_file . '" file is writable.', 'error');
                                $this->messages->add('You can manually update the text file "' . $ini_file . '" with the following content :<br /><br />' . $con_details_html, 'error');
                                $this->template->load('admin_template', 'admin/sqlAdmin', $data);
                                return;
			} else {
                                $this->messages->add('Updated account settings.', 'success');
                                redirect('admin');
                                return;
                        }
                }
                return;
        }
}
