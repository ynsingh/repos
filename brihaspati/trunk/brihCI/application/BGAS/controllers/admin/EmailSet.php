<?php

class EmailSet extends CI_Controller {

 function __construct() {
        parent::__construct();
        //if(empty($this->session->userdata('id_user'))) {
          // $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            //redirect('welcome');
       // }
    }

	function EmailSet()
	{
		parent::Controller();
	//	$this->load->model('Setting_model');

		/* Check access*/
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
		$this->template->set('page_title', 'Email Settings');
	//	$account_data = $this->Setting_model->get_current();

		/* Form fields */
		$data['email_protocol_options'] = array(
			'mail' => 'mail',
			'sendmail' => 'sendmail',
			'smtp' => 'smtp'
		);
		$data['email_host'] = array(
			'name' => 'email_host',
			'id' => 'email_host',
			'maxlength' => '100',
			'size' => '40',
			'value' => '',
		);
		$data['email_port'] = array(
			'name' => 'email_port',
			'id' => 'email_port',
			'maxlength' => '5',
			'size' => '5',
			'value' => '',
		);
		$data['email_username'] = array(
			'name' => 'email_username',
			'id' => 'email_username',
			'maxlength' => '100',
			'size' => '40',
			'value' => '',
		);
		$data['email_password'] = array(
			'name' => 'email_password',
			'id' => 'email_password',
			'maxlength' => '100',
			'size' => '40',
			'value' => '',
		);
		
		$db1=$this->load->database('login', TRUE);
                $db1->select('id,email_protocol,email_host,email_port,email_username,email_password')->from('emailSetting');
                $emaldata= $db1->get();		
		$oldPass='';
	 	foreach($emaldata->result() as $row)
                                {
			$data['email_protocol'] = ($row->email_protocol) ? print_value($row->email_protocol) : 'smtp';
			$data['email_host']['value'] = ($row->email_host) ? print_value($row->email_host) : '';
			$data['email_port']['value'] = ($row->email_port) ? print_value($row->email_port) : '';
			$data['email_username']['value'] = ($row->email_username) ? print_value($row->email_username) : '';
			$oldPass=$row->email_password;
		}
	//	$db1->close();
		/* Form validations */
		$this->form_validation->set_rules('email_protocol', 'Email Protocol', 'trim|required|min_length[2]|max_length[10]');
		$this->form_validation->set_rules('email_host', 'Mail Server Hostname', 'trim|max_length[255]');
		$this->form_validation->set_rules('email_port', 'Mail Server Port', 'trim|is_natural');
		$this->form_validation->set_rules('email_username', 'Email Username', 'trim|max_length[255]');
		$this->form_validation->set_rules('email_password', 'Email Password', 'trim|max_length[255]');

		/* Repopulating form */
		if ($_POST)
		{
			$data['email_protocol'] = $this->input->post('email_protocol', TRUE);
			$data['email_host']['value'] = $this->input->post('email_host', TRUE);
			$data['email_port']['value'] = $this->input->post('email_port', TRUE);
			$data['email_username']['value'] = $this->input->post('email_username', TRUE);
			$data['email_password']['value'] = $this->input->post('email_password', TRUE);
		}

		/* Validating form */
		if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('admin_template', 'admin/emailSet', $data);
			return;
		}
		else
		{
			$data_email_protocol = $this->input->post('email_protocol', TRUE);
			$data_email_host = $this->input->post('email_host', TRUE);
			$data_email_port = $this->input->post('email_port', TRUE);
			$data_email_username = $this->input->post('email_username', TRUE);
			$data_email_password = $this->input->post('email_password', TRUE);

			/* if password is blank then use the current password */
			if ($data_email_password == "")
				$data_email_password = $oldPass;

			/* Update settings */
			//$db1=$this->load->database('login', TRUE);
			$db1->trans_start();
			$update_data = array(
				'email_protocol' => $data_email_protocol,
				'email_host' => $data_email_host,
				'email_port' => $data_email_port,
				'email_username' => $data_email_username,
				'email_password' => $data_email_password,
			);
			if ( ! $db1->where('id', 1)->update('emailSetting', $update_data))
			{
				$db1->trans_rollback();
				$this->messages->add('Error updating email settings.', 'error');
				$this->logger->write_message("error", "Error updating email settings");
				$this->template->load('admin_template', 'admin/emailSet', $data);
				return;
			} else {
				$db1->trans_complete();
				$this->messages->add('Email settings updated.', 'success');
				$this->logger->write_message("success", "Updated email settings");
				redirect('admin/emailSet');
				return;
			}
			$db1->close();
		}
		return;
	}
}

/* End of file email.php */
/* Location: ./system/application/controllers/setting/email.php */
