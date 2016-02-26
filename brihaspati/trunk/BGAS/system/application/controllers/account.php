<?php

class Account extends Controller {
	function index()
	{
		$this->load->library('session');
		$this->load->model('Ledger_model');
		$this->template->set('page_title', 'Chart Of Accounts');
		//$this->template->set('nav_links', array('group/add' => 'Add Group','group/add_fund_group' => 'Add Fund Group', 'ledger/add' => 'Add Ledger'));
		$this->template->set('nav_links', array('group/add_fund_group' => 'Add Fund Group','group/add_spons_project' => 'Add Sponsored Project / Fellowship','ledger/add' => 'Add Ledger'));
		/* Calculating difference in Opening Balance */
		$total_op = $this->Ledger_model->get_diff_op_balance();
		if ($total_op > 0)
		{
			$this->messages->add('Difference in Opening Balance is Dr ' . convert_cur($total_op) . '.', 'error');
		} else if ($total_op < 0) {
			$this->messages->add('Difference in Opening Balance is Cr ' . convert_cur(-$total_op) . '.', 'error');
		}
		// code for search 
		$data['search'] = '';
		$data['search_by'] = array(
			"Select" => "Select",
                        "code" => "Account Code",
                        "name"=> "Account Name",
			"op_balance"=> "O/P Balance",
			"cl_balance"=> "C/L Balance",
                        "type"=> "Type",
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
			$data['search_by_active']['value'] = $this->input->post('search_by', TRUE);
			$data['text']['value'] = $this->input->post('text', TRUE);
		}
		/* Form validation */

		$this->form_validation->set_rules('search_by', 'Search By', 'trim|required');
		$this->form_validation->set_rules('text', 'Text', 'trim|required');
		/* Validating form */

		if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('template', 'account/index', $data);
			return;
		}
		else
		{
			$data_search_by = $this->input->post('search_by', TRUE);
			$data_text = $this->input->post('text', TRUE);
		}
		/*if(gmp_sign($data_text) == -1) {
			$this->messages->add('Text should be a positive value.', 'error');
			redirect('account/index');
		}*/
		if($data_search_by == "code")
		{
			$text = '';
			if($data_search_by == "code")
			{
				$text = 'Account Code';
			}
			if(! ctype_alnum($data_text)) {
				$this->messages->add($text . ' should be alphanumeric.', 'error');
				redirect('account/index');
			}
		}
		if($data_search_by == "type")
		{
			$text = '';
			if($data_search_by == "type")
			{
				$text = 'Type';
			}
			if(! ctype_alpha($data_text)) {
				$this->messages->add($text . ' should be alphabetic.', 'error');
				redirect('account/index');
			}
		}
		if($data_search_by == "op_balance" || $data_search_by == "cl_balance") {
			$balance=explode(',', $data_text);
			$data_text = implode("",$balance);
			$text = '';
			if($data_search_by == "op_balance") {
				$text = "O/P Balance";
			}
			if($data_search_by == "cl_balance") {
				$text = "C/L Balance";
			}
			if(! is_numeric($data_text)) {
				$this->messages->add($text . ' should be numeric.', 'error');
				redirect('account/index');
			}
		}
		$data['search'] = $data_search_by;
		$this->template->load('template', 'account/index', $data);
		return;
	}
}

/* End of file account.php */
/* Location: ./system/application/controllers/account.php */
