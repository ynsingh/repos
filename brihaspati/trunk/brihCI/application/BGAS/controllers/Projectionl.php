<?php

class Projectionl extends CI_Controller {

function __construct() {
        parent::__construct();
	$this->load->model('Budget_model');
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('user/login');
        }
    }
	function index()
	{
		//$this->load->model('Budget_model');
		//$this->template->set('page_title', 'Budget Heads');
		//$this->template->set('nav_links', array('budget/add' => 'Add Budget', 'budget/reappro' => 'Budget Reappropriation'));
		$this->template->set('nav_links', array('budgetl' => 'Budget', 'budget/add' => 'Add Budget', 'budget/reappro' => 'Reappropriate Budget', 'projectionl' => 'Projection', 'projection/add' => 'Add Projection', 'projection/reappro' => 'Reappropriate Projection'));
		//code to search user defined text
		$data['search'] = '';
		$data['search_by'] = array(
			"Select" => "Select",
                        "code" => "Projection Code",
                        "projection_name"=> "Projection Name",
			"type"=> "Projection Type",
			"bd_balance"=> "B/D Balance",
			"unearned_proj"=> "Unearned Projection",
                );
		$data['search_by_active'] = '';

		$data['text'] = array(
			'name' => 'text',
			'id' => 'text',
			'maxlength' => '100',
			'size' => '40',
			'value' => '',
		);
		if ($_POST)
		{
			$data['search_by_active'] = $this->input->post('search_by', TRUE);
			//$data['search_by_active']['value'] = $this->input->post('search_by', TRUE);
			$data['text']['value'] = $this->input->post('text', TRUE);
		}

		$this->form_validation->set_rules('search_by', 'Search By', 'trim|required');
		$this->form_validation->set_rules('text', 'Text', 'trim|required');
		/* Validating form */

		if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('template', 'projection/index', $data);
			return;
		}
		else
		{
			$data_search_by = $this->input->post('search_by', TRUE);
			$data_text = $this->input->post('text', TRUE);
		}
		if($data_search_by == "Select")
		{
			$this->messages->add('Please select search type from drop down list.', 'error');
		}
		else {
			if($data_search_by == "bd_balance") {
				$balance=explode(',', $data_text);
				$data_text = implode("",$balance);
				if(! is_numeric($data_text)) {
					$this->messages->add('Please enter a numeric value.', 'error');
					redirect('projectionl/index');
				}
			}
			if( $data_search_by == "code" || $data_search_by == "type")
			{
				if(! ctype_alnum($data_text)) {
					$this->messages->add('Please enter alphanumeric value.', 'error');
					redirect('projectionl/index');
				}
			}
			$field = $data_search_by . '      ' . 'LIKE';
			$text = $data_text;
			if($data_search_by != "unearned_proj") {
				$this->db->from('projection')->where($field, '%' . $text . '%');
				$query = $this->db->get();
				if( $query->num_rows() < 1 )
				{
					$this->messages->add($text . ' is not found.', 'error');
					redirect('projectionl/index');
				}
			}
			else {
				$this->db->from('projection');
				$query = $this->db->get();
			}
			$data['query'] = $query;
			$data['search'] = $data_search_by;
			/* Calculating difference in Opening Balance */
	/*		$total_op = $this->Budget_model->get_diff_op_balance();
			if ($total_op > 0)
			{
				$this->messages->add('Difference in Opening Balance is Dr ' . convert_cur($total_op) . '.', 'error');
			} else if ($total_op < 0) {
				$this->messages->add('Difference in Opening Balance is Cr ' . convert_cur(-$total_op) . '.', 'error');
			}
	*/	}
		$this->template->load('template', 'projection/index', $data);
		return;
	}
}

/* End of file projectionl.php */
/* Location: ./system/application/controllers/projectionl.php */
