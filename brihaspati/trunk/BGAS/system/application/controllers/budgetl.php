<?php

class Budgetl extends Controller {
	function index()
	{
		$this->load->model('Budget_model');
		$this->template->set('page_title', 'Budget Heads');
		$this->template->set('nav_links', array('budget/add' => 'Add Budget', 'budget/reappro' => 'Budget Reappropriation'));
		//$this->template->set('nav_links', array('budget/add' => 'Add Budget'));

		/* Calculating difference in Opening Balance */
		$total_op = $this->Budget_model->get_diff_op_balance();
		if ($total_op > 0)
		{
			$this->messages->add('Difference in Opening Balance is Dr ' . convert_cur($total_op) . '.', 'error');
		} else if ($total_op < 0) {
			$this->messages->add('Difference in Opening Balance is Cr ' . convert_cur(-$total_op) . '.', 'error');
		}

		$this->template->load('template', 'budget/index');
		return;
	}
}

/* End of file account.php */
/* Location: ./system/application/controllers/account.php */
