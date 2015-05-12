<?php

class Welcome extends Controller {

	function Welcome()
	{
		parent::Controller();
		return;
	}
	
	function index()
	{
		$this->load->model('Ledger_model');
		$this->load->library('accountlist');
		$this->template->set('page_title', 'Welcome to BGAS');
		$today_date=date("Y-m-d");
		$exp_today_date=explode("-",$today_date);
	        $date1= $this->config->item('account_fy_end');
          	$fy_end_date=explode("-",$date1);
		if((($exp_today_date[1]> $fy_end_date[1] ) && ($exp_today_date[0]== $fy_end_date[0]) && ($this->config->item('account_locked') == 0)) || (($exp_today_date[1]< $fy_end_date[1] )&& ($exp_today_date[0]> $fy_end_date[0])&&($this->config->item('account_locked') == 0))) {
                        $link = anchor('setting/cf', 'C/F Account', array('class' => 'anchor-link-a'));
			$this->messages->add('Kindly Carry Forword Your Account By Clicking '.$link.'.', 'success');
		}
		/* Bank and Cash Ledger accounts */
		$this->db->from('ledgers')->where('type', 1);
		$bank_q = $this->db->get();
		if ($bank_q->num_rows() > 0)
		{
			foreach ($bank_q->result() as $row)
			{
				$data['bank_cash_account'][] = array(
					'id' => $row->id,
					'name' => $row->name,
					'balance' => $this->Ledger_model->get_ledger_balance($row->id),
				);
			}
		} else {
			$data['bank_cash_account'] = array();
		}

		/* Calculating total of Assets, Liabilities, Incomes, Expenses */
		$asset = new Accountlist();
		$asset->init(1);
		$data['asset_total'] = $asset->total;

		$liability = new Accountlist();
		$liability->init(2);
		$data['liability_total'] = $liability->total;

		$income = new Accountlist();
		$income->init(3);
		$data['income_total'] = $income->total;

		$expense = new Accountlist();
		$expense->init(4);
		$data['expense_total'] = $expense->total;

		/* Getting Log Messages */
		$data['logs'] = $this->logger->read_recent_messages();
		$this->template->load('template', 'welcome', $data);
		return;
	}
}

/* End of file welcome.php */
/* Location: ./system/application/controllers/welcome.php */
