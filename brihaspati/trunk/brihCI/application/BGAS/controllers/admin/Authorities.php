<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Authorities extends CI_Controller {
 function __construct() {
        parent::__construct();
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('user/login');
       }
    }

	function Authorities()
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
		$this->template->set('page_title', 'Manage Authorities');
		$this->template->set('nav_links', array('admin/authorities/add' => 'Add Authority'));
		//$data['users'] = array();
		$db1=$this->load->database('login', TRUE);
		$db1->select('*')->from('authorities'); 
		$query = $db1->get();
        $config['total_rows'] =$db1->count_all('authorities');
        $data['users']= $query;
		$this->template->load('admin_template', 'admin/authorities/index', $data);
		$db1->close();
		return;
	}

	
	function add()
	{
		//$this->load->library('validation');
                //$this->load->library('paymentreceipt');
		$this->template->set('page_title', 'Add Authority');

		$db1=$this->load->database('login', TRUE);

		/* Form fields */
		$data['authority_name'] = array(
			'name' => 'authority_name',
			'id' => 'authority_name',
			'maxlength' => '100',
			'size' => '40',
			'value' => '',
		);

		$data['authority_nickname'] = array(
			'name' => 'authority_nickname',
			'id' => 'authority_nickname',
			'maxlength' => '100',
			'size' => '40',
			'value' => '',
		);

		$data['authority_email'] = array(
			'name' => 'authority_email',
			'id' => 'authority_email',
			'maxlength' => '100',
			'size' => '40',
			'value' => '',
		);

		if ($_POST)
		{
			$data['authority_name']['value'] = $this->input->post('authority_name', TRUE);
			$data['authority_nickname']['value'] = $this->input->post('authority_nickname', TRUE);
			$data['authority_email']['value'] = $this->input->post('authority_email', TRUE);
		}

		
		$this->form_validation->set_rules('authority_name', 'Name Of Authority','trim|required');
		$this->form_validation->set_rules('authority_nickname', 'Nick Name for Authority','trim|required');
		$this->form_validation->set_rules('authority_email', 'Authority Email', 'trim|valid_email');
		

		/* Validating form */
		if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('admin_template', 'admin/authorities/add', $data);
			return;
		}
		else
		{
			$data_authority_name = $this->input->post('authority_name', TRUE);
			$data_authority_nickname = $this->input->post('authority_nickname', TRUE);
			$data_authority_email = $this->input->post('authority_email', TRUE);

			$insert_data = array(
	            'name' => $data_authority_name,
	            'nickname' => $data_authority_nickname,
	            'authority_email'=> $data_authority_email
		    );

			if ( ! $db1->insert('authorities', $insert_data))
        	{

                $db1->trans_rollback();
                $this->messages->add('Error addding Authority in authorities- ' . $data_authority_name . '.', 'error');
                $this->logger->write_message("error", "Error Adding Authority " . $data_authority_name);
				$this->template->load('admin_template', 'admin/authorities/add', '');
                return;
            }
			else{
				$db1->trans_complete();
			}
			$db1->close();
			redirect('admin/authorities/');
		}
		$this->template->load('admin_template', 'admin/authorities/add', $data);
		return;	
	}


	function edit($authority_id = 0)
	{
		//$this->load->library('validation');
		$this->template->set('page_title', 'Edit Authority');

		$db1=$this->load->database('login', TRUE);

		$db1->select('*');
		$db1->from('authorities');
		$db1->where('id',$authority_id);
		$auth_details = $db1->get();

		foreach($auth_details->result() as $row)
        {
	        $auth_name = $row->name;       
			$auth_nickname= $row->nickname;
			$auth_email = $row->authority_email;
        }


		/* Form fields */
		$data['authority_name'] = array(
			'name' => 'authority_name',
			'id' => 'authority_name',
			'maxlength' => '100',
			'size' => '40',
			'value' => $auth_name,
		);

		$data['authority_nickname'] = array(
			'name' => 'authority_nickname',
			'id' => 'authority_nickname',
			'maxlength' => '100',
			'size' => '40',
			'value' => $auth_nickname,
		);

		$data['authority_email'] = array(
			'name' => 'authority_email',
			'id' => 'authority_email',
			'maxlength' => '100',
			'size' => '40',
			'value' => $auth_email,
		);

		$data['authority_id'] = $authority_id;

		if ($_POST)
		{
			$data['authority_name']['value'] = $this->input->post('authority_name', TRUE);
			$data['authority_nickname']['value'] = $this->input->post('authority_nickname', TRUE);
			$data['authority_email']['value'] = $this->input->post('authority_email', TRUE);
		}

		
		$this->form_validation->set_rules('authority_name', 'Name Of Authority','trim|required');
		$this->form_validation->set_rules('authority_nickname', 'Nick Name for Authority','trim|required');
		$this->form_validation->set_rules('authority_email', 'Authority Email', 'trim|valid_email');
		

		/* Validating form */
		if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('admin_template', 'admin/authorities/edit', $data);
			return;
		}
		else
		{
			$data_authority_name = $this->input->post('authority_name', TRUE);
			$data_authority_nickname = $this->input->post('authority_nickname', TRUE);
			$data_authority_email = $this->input->post('authority_email', TRUE);

			$update_data = array(
	            'name' => $data_authority_name,
	            'nickname' => $data_authority_nickname,
	            'authority_email'=> $data_authority_email
		    );

			if ( ! $db1->where('id', $authority_id)->update('authorities', $update_data))
			{
				$db1->trans_rollback();
				$this->messages->add('Error in updating Authority - ' . $data_authority_name . '.', 'error');
				return;
			}
			else{
				$db1->trans_complete();
			}
			redirect('admin/authorities');
            return;
        }
        $db1->close();
		return;
	}

	function delete($authority_id)
	{
	
		$this->template->set('page_title', 'Delete user');
		/* Get the User details */
		$db1=$this->load->database('login', TRUE);

		$db1->select('name')->from('authorities')->where('id',$authority_id);
		$auth_q = $db1->get();
		$auth_data = $auth_q->row();
		if ($auth_q->num_rows() < 1)
		{
			$this->messages->add('Invalid Authority.', 'error');
			redirect('admin/authorities/');
			return;
		}else{
				 
			if ( ! $db1->delete('authorities', array('id' => $authority_id)))
	    	{
	    		$db1->trans_rollback();
	        	$this->messages->add('Error delete Authority from authorities - ' . $auth_data->name . '.', 'error');
	            $this->logger->write_message("error", "Error delete Authority from authorities " . $auth_data->name);
				redirect('admin/authorities/');
				return;
	    	}else{
	    		$db1->trans_complete();
	    	
            	redirect('admin/authorities');
            	return;
            }
        }

		$db1->close();
		redirect('admin/authorities');
		return;	
	}


	function auth_allocation()
	{

		$this->load->helper('file');
		$this->template->set('page_title', 'Manage Authorities');
		$this->template->set('nav_links', array('admin/authorities/add_authority' => 'Allocate Authority','admin/authorities/auth_archive' => 'Authority Archive'));
		//$data['users'] = array();
		$this->load->model('Authority_model');
		$this->load->model('User_model');

		$db1=$this->load->database('login', TRUE);
		$db1->select('*')->from('authority_map'); 
		$query = $db1->get();
        $config['total_rows'] =$db1->count_all('authority_map');
        $data['users']= $query;
		$db1->close();
		
		$this->template->load('admin_template', 'admin/authorities/authority_allocate',$data);
		return;
	}


	function add_authority()
	{
		//$this->load->library('validation');
		$this->load->model('Authority_model');
		$this->load->model('User_model');
		$this->template->set('page_title', 'Allocate Authority');

		$db1=$this->load->database('login', TRUE);

		/* Form fields */

		$data['authority']= $this->Authority_model->get_all_authorities();
		$data['authority_active']=0;
		$data['username']= $this->User_model->get_all_users();
		$data['username_active']=0;

		$data['auth_type'] = array(
			'select' => 'Select',
			'full' => 'Full Time',
			'acting' => 'Acting',
		);

		$data['auth_type_active']= 'select';

		/*$data['map_date'] = array(
			'name' => 'map_date',
			'id' => 'map_date',
			'maxlength' => '11',
			'size' => '11',
			'value' => date_today_php(),
		);

		$data['till_date'] = array(
			'name' => 'till_date',
			'id' => 'till_date',
			'maxlength' => '11',
			'size' => '11',
			'value' => date_today_php(),
		);

		$CI =& get_instance();

		$date1 = $CI->config->item('account_fy_start');
		$date2 = $CI->config->item('account_fy_end');

		$date=explode("-",$date1);
		$date3 = explode("-", $date2);
		$default_start = '01/04/'.$date[0];
		$default_end = '31/03/'.$date3[0];
		
		$curr_date = date_today_php();
		if($curr_date >= $default_end) {
			$default_end_date = $default_end;
		}
		else {
			$default_end_date = $curr_date;
		}*/
		

		$data['map_date'] = array(
			'name' => 'map_date',
			'id' => 'map_date',
			'maxlength' => '11',
			'size' => '11',
			'value' => '',
		);

		$data['till_date'] = array(
			'name' => 'till_date',
			'id' => 'till_date',
			'maxlength' => '11',
			'size' => '11',
			'value' => '',
		);

		/* Form validations */
		$this->form_validation->set_rules('map_date', 'Authority Allocation Date', 'trim|required|is_date|is_date_within_range');
		//$this->form_validation->set_rules('till_date', 'Till Date', 'trim|required|is_date|is_date_within_range');
		$this->form_validation->set_rules('till_date', 'Till Date', 'trim|required|is_date');

		/* Repopulating form */
		if ($_POST)
		{

			$data['authority_active'] = $this->input->post('authority', TRUE);
			$data['username_active'] = $this->input->post('username', TRUE);
			$data['auth_type_active'] = $this->input->post('auth_type', TRUE);
			$data['map_date']['value'] = $this->input->post('map_date', TRUE);
			$data['till_date']['value'] = $this->input->post('till_date', TRUE);
		}


		/* validating form */

		if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('admin_template', 'admin/authorities/add_authority', $data);
			return;
		}
		else
		{
			$data_authority = $this->input->post('authority', TRUE);
			$data_username = $this->input->post('username', TRUE);
			$data_auth_type = $this->input->post('auth_type', TRUE);
			$data_map_date = $this->input->post('map_date', TRUE);
			$data_till_date = $this->input->post('till_date', TRUE);

			if($data_authority == 0)
			{
				$this->messages->add('Please Select Authority From Dropdown List.', 'error');
				$this->template->load('admin_template', 'admin/authorities/add_authority', $data);
				return;
			}

			if($data_username == 0)
			{
				$this->messages->add('Please Select User Name From Dropdown List.', 'error');
				$this->template->load('admin_template', 'admin/authorities/add_authority', $data);
				return;
			}

			if($data_auth_type == 'select')
			{
				$this->messages->add('Please Select Authority Status From Dropdown List.', 'error');
				$this->template->load('admin_template', 'admin/authorities/add_authority', $data);
				return;
			}

			$data_map_date = date_php_to_mysql($data_map_date);
			$data_till_date = date_php_to_mysql($data_till_date);
	
			$db1=$this->load->database('login', TRUE);
			$insert_data = array(
				'authority_id' => $data_authority,
				'user_id' => $data_username,
				'map_date' => $data_map_date,
				'till_date' => $data_till_date,
				'authority_type' => $data_auth_type
			);
			
			$db1->trans_start();	
			if ( ! $db1->insert('authority_map', $insert_data))
        	{

                $db1->trans_rollback();
                $this->messages->add('Error Mapping Authority '. $data_authority .' with '. $data_username.'.', 'error');
                $this->logger->write_message("error", 'Error Mapping Authority '. $data_authority .' with '. $data_username.'.');
				$this->template->load('admin_template', 'admin/authorities/add_authority', '');
                return;
            }
			else{
				$db1->trans_complete();
			}
			$db1->close();
			redirect('admin/authorities/auth_allocation');
		}
		$this->template->load('admin_template', 'admin/authorities/add_authority', $data);
		return;	
	}

	function auth_archive()
	{

		$this->load->helper('file');
		$this->template->set('page_title', 'Authority Archive');
		//$this->template->set('nav_links', array('admin/authorities/add_authority' => 'Allocate Authority','admin/authorities/view_archive' => 'Authority Archive'));
		$data['authorities'] = array();
		$this->load->model('Authority_model');
		$this->load->model('User_model');

		$db1=$this->load->database('login', TRUE);
		$db1->select('*')->from('authority_archive'); 
		$query = $db1->get();
        $config['total_rows'] =$db1->count_all('authority_archive');
        $data['authorities']= $query;
		$db1->close();
		
		$this->template->load('admin_template', 'admin/authorities/authority_archive',$data);
		return;
	}

	function movearchive($id =0)
	{
		//$this->template->set('page_title', 'Delete user');
		/* Get the User details */
		$db1=$this->load->database('login', TRUE);

		$db1->select('*')->from('authority_map')->where('id',$id);
		$auth_q = $db1->get();
		$auth_data = $auth_q->row();
		if ($auth_q->num_rows() < 1)
		{
			$this->messages->add('Invalid Authority Mapping Entry.', 'error');
			redirect('admin/authorities/auth_allocation');
			return;
		}else{

			$authority_id = $auth_data->authority_id;
			$user_id = $auth_data->user_id;
			$map_date = $auth_data->map_date;
			$till_date = $auth_data->till_date;
			$auth_type = $auth_data->authority_type;
				 
			if ( ! $db1->delete('authority_map', array('id' => $id)))
	    	{
	    		$db1->trans_rollback();
	        	$this->messages->add('Error Moving Authority Mapping Entry to Authority Archive '.$id.' .', 'error');
	            $this->logger->write_message("error", 'Error Moving Authority Mapping Entry to Authority Archive '.$id.' .');
				redirect('admin/authorities/auth_allocation');
				return;
	    	}else{

	    		$db1->trans_complete();

	    		$insert_data = array(
				'authority_id' => $authority_id,
				'user_id' => $user_id,
				'map_date' => $map_date,
				'till_date' => $till_date,
				'authority_type' => $auth_type
				);
				
				$db1->trans_start();	
				if ( ! $db1->insert('authority_archive', $insert_data))
	        	{

	                $db1->trans_rollback();
	                $this->messages->add('Error Moving Authority Mapping Entry to Authority Archive '.$id.' .', 'error');
	                $this->logger->write_message("error", 'Error Moving Authority Mapping Entry to Authority Archive '.$id.' .');
					$this->template->load('admin_template', 'admin/authorities/auth_allocation', '');
	                return;
	            }
				else{
					$db1->trans_complete();
				}
				$db1->close();
	    	
            	redirect('admin/authorities/auth_allocation');
            	return;
            }
        }

		//$db1->close();
		redirect('admin/authorities/auth_allocation');
		return;
	}

	function edit_authority($id =0)
	{
		//$this->load->library('validation');
		$this->template->set('page_title', 'Edit Allocated Authority');

		$db1=$this->load->database('login', TRUE);

		$db1->select('*');
		$db1->from('authority_map');
		$db1->where('id',$id);
		$auth_details = $db1->get();

		foreach($auth_details->result() as $row)
        {
	        $auth_id = $row->authority_id;       
			$userid = $row->user_id;
			$map_date = $row->map_date;
			$till_date = $row->till_date;
			$auth_type = $row->authority_type;
        }

        //echo "till_date = $till_date map_date = $map_date";
        $this->load->model('Authority_model');
        $auth_name = $this->Authority_model->get_authority_name($auth_id);

        $this->load->model('User_model');
        $user_id = $this->User_model->get_user_email($userid);

        $map_date = date_mysql_to_php($map_date);
        $till_date = date_mysql_to_php($till_date);

        if($auth_type == "full")
        	$auth_type = "Full Time";
        elseif($auth_type == 'acting')
        	$auth_type = "Acting";

		/* Form fields */
		$data['authority_id'] = array(
			'name' => 'authority_id',
			'id' => 'authority_id',
			'maxlength' => '100',
			'size' => '40',
			'value' => $auth_name,
			'readonly' => true,
		);

		$data['authority_user'] = array(
			'name' => 'authority_user',
			'id' => 'authority_user',
			'maxlength' => '100',
			'size' => '40',
			'value' => $user_id,
			'readonly' => true,
		);

		$data['authority_type'] = array(
			'name' => 'authority_type',
			'id' => 'authority_type',
			'maxlength' => '100',
			'size' => '40',
			'value' => $auth_type,
			'readonly' => true,
		);

		$data['id'] = $id;

		$data['map_date'] = array(
			'name' => 'map_date',
			'id' => 'map_date',
			'maxlength' => '11',
			'size' => '11',
			'value' => $map_date,
		);

		$data['till_date'] = array(
			'name' => 'till_date',
			'id' => 'till_date',
			'maxlength' => '11',
			'size' => '11',
			'value' => $till_date,
		);

		/* Form validations */
		$this->form_validation->set_rules('map_date', 'Authority Allocation Date', 'trim|required|is_date|is_date_within_range');
		$this->form_validation->set_rules('till_date', 'Till Date', 'trim|required|is_date|is_date_within_range');

		/* Repopulating form */
		if ($_POST)
		{
			$data['map_date']['value'] = $this->input->post('map_date', TRUE);
			$data['till_date']['value'] = $this->input->post('till_date', TRUE);
		}


		/* validating form */

		if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('admin_template', 'admin/authorities/edit_authority', $data);
			return;
		}
		else
		{
			//$data_authority = $this->input->post('authority_id', TRUE);
			//$data_username = $this->input->post('authority_user', TRUE);
			//$data_auth_type = $this->input->post('authority_type', TRUE);
			$data_map_date = $this->input->post('map_date', TRUE);
			$data_till_date = $this->input->post('till_date', TRUE);

			$data_map_date = date_php_to_mysql($data_map_date);
			$data_till_date = date_php_to_mysql($data_till_date);

			$update_data = array(
	            'map_date' => $data_map_date,
	            'till_date' => $data_till_date
		    );

			if ( ! $db1->where('id', $id)->update('authority_map', $update_data))
			{
				$db1->trans_rollback();
				$this->messages->add('Error in updating Allocated Authority.', 'error');
				return;
			}
			else{
				$db1->trans_complete();
			}
			redirect('admin/authorities/auth_allocation');
            return;
        }
        $db1->close();
		return;
	}

	
}
/* End of file authorities.php */
/* Location: ./system/application/controllers/admin/authorities.php */
