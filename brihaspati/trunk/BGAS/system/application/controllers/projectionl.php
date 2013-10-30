<?php

class Projectionl extends Controller {
	function index()
	{
		$this->load->model('Budget_model');
		//$this->template->set('page_title', 'Budget Heads');
		//$this->template->set('nav_links', array('budget/add' => 'Add Budget', 'budget/reappro' => 'Budget Reappropriation'));
		$this->template->set('nav_links', array('budgetl' => 'Budget', 'budget/add' => 'Add Budget', 'budget/reappro' => 'Reappropriate Budget', 'projectionl' => 'Projection', 'projection/add' => 'Add Projection', 'projection/reappro' => 'Reappropriate Projection'));
		
		/* Calculating difference in Opening Balance */
/*		$total_op = $this->Budget_model->get_diff_op_balance();
		if ($total_op > 0)
		{
			$this->messages->add('Difference in Opening Balance is Dr ' . convert_cur($total_op) . '.', 'error');
		} else if ($total_op < 0) {
			$this->messages->add('Difference in Opening Balance is Cr ' . convert_cur(-$total_op) . '.', 'error');
		}
*/
		$this->template->load('template', 'projection/index');
		return;
	}
}

/* End of file projectionl.php */
/* Location: ./system/application/controllers/projectionl.php */
