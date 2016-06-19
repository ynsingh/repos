<?php

class Budget_model extends Model {

	function Budget_model()
	{
		parent::Model();
	}

	function get_all_groups($id = NULL)
        {
                $options = array();
                if ($id == NULL)
                        $this->db->from('groups')->where('id >', 0)->order_by('name', 'asc');
                else
                        $this->db->from('groups')->where('id >', 0)->where('id !=', $id)->order_by('name', 'asc');
                $group_parent_q = $this->db->get();
                foreach ($group_parent_q->result() as $row)
                {
                        $options[$row->id] = $row->name;
                }
                return $options;
        }
	
	function get_parent_code($parentCode)
	{
		$parent_code = '';
		$this->db->from('budgets');
		$this->db->select('code')->where('budgetname =', $parentCode);
		$code = $this->db->get();
		foreach($code->result() as $row)
			$parent_code = $row->code;
		return $parent_code;
	}
	
	/**
	 * search the code of parent budget
	 * for the given child budget.
	 * The parent budget is first searched 
	 * in ledgers table and if search is
	 * unsuccessful, than in groups table.
	 */
	function get_parent($chld_budget)
	{
		$var = '';
		$parent_id = '';
		$this->db->from('ledgers');
		$this->db->select('group_id')->where('name =', $chld_budget);
		$parentId = $this->db->get();
		foreach($parentId->result() as $row)
		{
		//	$this->logger->write_message("error", "Error Priyanka1 " . $row->parent_id);
			$parent_id = $row->group_id;
		}
		if($parent_id != '')
		{
			$this->db->from('groups');
                	$this->db->select('code')->where('id =', $parent_id);
                	$parentId = $this->db->get();
                	foreach($parentId->result() as $row)
                	{
                        	$var = $row->code;
                	}
		}
		else
		{
			$this->db->from('groups');
			$this->db->select('parent_id')->where('name =', $chld_budget);
			$parentId = $this->db->get();
			foreach($parentId->result() as $row)
			{
				$parent_id = $row->parent_id;
			}
			if($parent_id != '')
			{
				$this->db->from('groups');
                        	$this->db->select('code')->where('id =', $parent_id);
                        	$parentId = $this->db->get();
                        	foreach($parentId->result() as $row)
                        	{
                                	$var = $row->code;
                        	}
			}
		}
		return $var;
	}

	function get_selected_budgets()
        {
                $options = array();
                //$this->db->from('budgets')->order_by('budgetname', 'asc');
		$this->db->from('budgets');
		//$this->db->select('id, budgetname');
		$this->db->select('id, budgetname, code, bd_balance')->where('code LIKE', '____')->order_by('code', 'asc');
                $budget_parent_q = $this->db->get();
		$counter = 0;
                foreach ($budget_parent_q->result() as $row)
                {
                       // $options[$row->id] = $row->budgetname;
			$options[$counter]['id'] = $row->id;
                        $options[$counter]['name'] = $row->budgetname;
			$options[$counter]['code'] = $row->code;
			$options[$counter]['bd_balance'] = $row->bd_balance;
			$counter++;
                }
                return $options;
        }

	function get_sub_budget()
	{
		$options = array();
                $this->db->from('budgets');
                //$this->db->select('id, group_id, budgetname')->where('code LIKE', '_____%')->order_by('budgetname', 'asc');
		$this->db->select('id, code, group_id, budgetname')->where('code LIKE', '_____%')->order_by('id', 'asc');
                $budget_parent_q = $this->db->get();
                $counter = 0;
                foreach ($budget_parent_q->result() as $row)
                {
			//$options[$counter]['group_id'] = $row->group_id;
                        $options[$counter]['name'] = $row->budgetname;
			$options[$counter]['id'] = $row->id;
			$options[$counter]['code'] = $row->code;
			$this->db->from('budgets');
			$this->db->select('budgetname')->where('id =', $row->group_id);
			$budget_parent = $this->db->get();
			//$parent_budget = $budget_parent->result();
			//$budget_parent->result() as $parent_budget;
			foreach($budget_parent->result() as $parent_budget)
			{
				$options[$counter]['parent_budget'] = $parent_budget->budgetname;
			}
                        $counter++;
                }
                return $options;
	}

	function get_parent_budget($id)
	{
		$options = array();
		$counter = 0;
			$this->db->from('budgets');
                $this->db->select('id, budgetname, code, bd_balance')->where('group_id = ', $id)->order_by('id', 'asc');
			$child_budget = $this->db->get();
			foreach($child_budget->result() as $chld_budget)
                        {
				$options[$counter]['id'] = $chld_budget->id;
                                $options[$counter]['child_budget'] = $chld_budget->budgetname;
				$options[$counter]['code'] = $chld_budget->code;
				$options[$counter]['bd_balance'] = $chld_budget->bd_balance;
				$counter++;
                        }
		return $options;
	}

	function get_allocation_amount($data_parent_id, $account)
	{
		$parent_amount = '';
		$parent_date = '';
		if($account == 'budgets')
			$this->db->from('budget_allocate');
		else
			$this->db->from('projection_allocate');
		$this->db->select_max('creation_date')->where('code =', $data_parent_id);
		$date_result = $this->db->get();
		foreach($date_result->result() as $date)
                {
                        $parent_date = $date->creation_date;
                }
		if($parent_date != NULL)
		{
			if($account == 'budgets')
				$this->db->from('budget_allocate');
			else
				$this->db->from('projection_allocate');
			$this->db->select('allocation_amount')->where('code =', $data_parent_id);
			$this->db->where('creation_date =', $parent_date);
			$parent_result = $this->db->get();
			foreach($parent_result->result() as $parent)
			{
				$parent_amount = $parent->allocation_amount;
			}
		}
		return $parent_amount;
	}

	function get_child_budgets($parentId, $account)
	{
		$child = array();
		$budget_id = 0;
		$counter = 0;
		if($account == 'budgets')
			$this->db->from('budgets');
		else
			$this->db->from('projection');
		$this->db->select('code')->where('group_id =', $parentId);
		$child_budget = $this->db->get();
		foreach($child_budget->result() as $row)
		{
			$child[$counter]['code'] = $row->code;
			$counter++;
		}
		return $child;
	}


	//function get_selected_groups()
	function get_selected_groups($account_name)
        {
                $options = array();

		//Get account code
		$account_code = $this->get_account_code($account_name);
		
		if($account_name == 'Expenses'){
			//Added 'Main Budget' as first value
			//to be displayed in the list
			$main_budget_code = 0;
			$this->db->from('budgets');
			$this->db->where('budgetname', 'Main Budget');
			$budgetq = $this->db->get();
			foreach($budgetq->result() as $row)
				$main_budget_code = $row->code;

			//$new_id = "50"."#"."Main Budget";
			$new_id = $main_budget_code."#"."Main Budget";
        	        $options[$new_id] = 'Main Budget';
		}

                $this->db->from('groups');
		//$this->db->where('code LIKE', '40%');
		$this->db->where('code LIKE', $account_code.'%');
		//$this->db->where('code NOT LIKE', '40');
		if($account_name == 'Expenses')
			$this->db->where('code NOT LIKE', $account_code);
		$this->db->where('status', '0')->order_by('name', 'asc');
                $group_code = $this->db->get();
                foreach ($group_code->result() as $row)
                {
                        $new_id = "$row->code"."#"."$row->name";
                        $options[$new_id] = $row->name;
                }

		//$this->db->from('ledgers')->where('code LIKE', '40%')->order_by('name', 'asc');
		$this->db->from('ledgers')->where('code LIKE', $account_code.'%')->order_by('name', 'asc');
                $group_code = $this->db->get();
                foreach ($group_code->result() as $row)
                {
                        $new_id = "$row->code"."#"."$row->name";
                        $options[$new_id] = $row->name;
                }
                return $options;
        }


	function get_all_budgets()
	{
		$options = array();
		$options[0] = "(Please Select)";
		$this->db->from('budgets')->order_by('budgetname', 'asc');
		$budget_q = $this->db->get();
		foreach ($budget_q->result() as $row)
		{
			$options[$row->id] = $row->budgetname;
		}
		return $options;
	}

	function get_budgets()
	{
		$budgets = array();
		$counter = 0;
		$main_budget_code = 0;
		$this->db->from('budgets');
                //$this->db->select('id, code, budgetname, bd_balance, group_id')->where('code =', '50');
                $this->db->select('id, code, budgetname, bd_balance, group_id')->where('budgetname', 'Main Budget');
		$budget_q = $this->db->get();
                foreach($budget_q->result() as $row)
                {
                        $budget[$counter]['id'] = $row->id;
                        $budget[$counter]['code'] = $row->code;
                        $budget[$counter]['budgetname'] = $row->budgetname;
                        $budget[$counter]['bd_balance'] = $row->bd_balance;
                        $budget[$counter]['group_id'] = $row->group_id;
			$main_budget_code = $row->code;
                        $counter++;
                }

		//get code for expense
		$expense_code = $this->get_account_code('Expenses');
		$this->db->from('budgets');
		//$this->db->select('id, code, budgetname, bd_balance, group_id')->where('code <>', '40');
		$this->db->select('id, code, budgetname, bd_balance, group_id')->where('code <>', $expense_code);
		//$this->db->where('code <>', '50')->order_by('code', 'asc');
		$this->db->where('code <>', $main_budget_code)->order_by('code', 'asc');
		$budget_q1 = $this->db->get();
		foreach($budget_q1->result() as $row)
		{
			$budget[$counter]['id'] = $row->id;
			$budget[$counter]['code'] = $row->code;
			$budget[$counter]['budgetname'] = $row->budgetname;
			$budget[$counter]['bd_balance'] = $row->bd_balance;
			$budget[$counter]['group_id'] = $row->group_id;
			$counter++;
		}
		return $budget;
	}

	function get_groupid_budgetname($parent_name,$account)
	{
		$parent_id = '';
		if($account == 'budgets')
                	$this->db->from('budgets');
		else
			$this->db->from('projection');
		$this->db->select('id')->where('code =', $parent_name);
                $group_budget = $this->db->get();
                foreach ($group_budget->result() as $row)
                {
			$parent_id = $row->id;
                }
                return $parent_id;
	}

	function get_all_ledgers_bankcash()
	{
		$options = array();
		$options[0] = "(Please Select)";
		$this->db->from('ledgers')->where('type', 1)->order_by('name', 'asc');
		$ledger_q = $this->db->get();
		foreach ($ledger_q->result() as $row)
		{
			$options[$row->id] = $row->name;
		}
		return $options;
	}

	function get_all_ledgers_nobankcash()
	{
		$options = array();
		$options[0] = "(Please Select)";
		$this->db->from('ledgers')->where('type !=', 1)->order_by('name', 'asc');
		$ledger_q = $this->db->get();
		foreach ($ledger_q->result() as $row)
		{
			$options[$row->id] = $row->name;
		}
		return $options;
	}

	function get_all_ledgers_reconciliation()
	{
		$options = array();
		$options[0] = "(Please Select)";
		$this->db->from('ledgers')->where('reconciliation', 1)->order_by('name', 'asc');
		$ledger_q = $this->db->get();
		foreach ($ledger_q->result() as $row)
		{
			$options[$row->id] = $row->name;
		}
		return $options;
	}

	function get_name($budget_id)
	{
		$this->db->from('budgets')->where('id', $budget_id)->limit(1);
		$budget_q = $this->db->get();
		if ($budget = $budget_q->row())
			return $budget->name;
		else
			return "(Error)";
	}

	function get_entry_name($entry_id, $entry_type_id)
	{
		/* Selecting whether to show debit side Ledger or credit side Ledger */
		$current_entry_type = entry_type_info($entry_type_id);
		$ledger_type = 'C';

		if ($current_entry_type['bank_cash_ledger_restriction'] == 3)
			$ledger_type = 'D';

		$this->db->select('ledgers.name as name');
		$this->db->from('entry_items')->join('ledgers', 'entry_items.ledger_id = ledgers.id')->where('entry_items.entry_id', $entry_id)->where('entry_items.dc', $ledger_type);
		$ledger_q = $this->db->get();
		if ( ! $ledger = $ledger_q->row())
		{
			return "(Invalid)";
		} else {
			$ledger_multiple = ($ledger_q->num_rows() > 1) ? TRUE : FALSE;
			$html = '';
			if ($ledger_multiple)
				$html .= anchor('entry/view/' . $current_entry_type['label'] . "/" . $entry_id, "(" . $ledger->name . ")", array('title' => 'View ' . $current_entry_type['name'] . ' Entry', 'class' => 'anchor-link-a'));
			else
				$html .= anchor('entry/view/' . $current_entry_type['label'] . "/" . $entry_id, $ledger->name, array('title' => 'View ' . $current_entry_type['name'] . ' Entry', 'class' => 'anchor-link-a'));
			return $html;
		}
		return;
	}

	function get_opp_ledger_name($entry_id, $entry_type_label, $ledger_type, $output_type)
	{
		$output = '';
		if ($ledger_type == 'D')
			$opp_ledger_type = 'C';
		else
			$opp_ledger_type = 'D';
		$this->db->from('entry_items')->where('entry_id', $entry_id)->where('dc', $opp_ledger_type);
		$opp_entry_name_q = $this->db->get();
		if ($opp_entry_name_d = $opp_entry_name_q->row())
		{
			$opp_ledger_name = $this->get_name($opp_entry_name_d->ledger_id);
			if ($opp_entry_name_q->num_rows() > 1)
			{
				if ($output_type == 'html')
					$output = anchor('entry/view/' . $entry_type_label . '/' . $entry_id, "(" . $opp_ledger_name . ")", array('title' => 'View ' . ' Entry', 'class' => 'anchor-link-a'));
				else
					$output = "(" . $opp_ledger_name . ")";
			} else {
				if ($output_type == 'html')
					$output = anchor('entry/view/' . $entry_type_label . '/' . $entry_id, $opp_ledger_name, array('title' => 'View ' . ' Entry', 'class' => 'anchor-link-a'));
				else
					$output = $opp_ledger_name;
			}
		}
		return $output;
	}

	function get_ledger_balance($ledger_id)
	{
		list ($op_bal, $op_bal_type) = $this->get_op_balance($ledger_id);

		$dr_total = $this->get_dr_total($ledger_id);
		$cr_total = $this->get_cr_total($ledger_id);

		$total = float_ops($dr_total, $cr_total, '-');
		if ($op_bal_type == "D")
			$total = float_ops($total, $op_bal, '+');
		else
			$total = float_ops($total, $op_bal, '-');

		return $total;
	}

	function get_op_balance($ledger_id)
	{
		$this->db->from('ledgers')->where('id', $ledger_id)->limit(1);
		$op_bal_q = $this->db->get();
		if ($op_bal = $op_bal_q->row())
			return array($op_bal->op_balance, $op_bal->op_balance_dc);
		else
			return array(0, "D");
	}

	function get_diff_op_balance()
	{
		/* Calculating difference in Opening Balance */
		$total_op = 0;
		$this->db->from('ledgers')->order_by('id', 'asc');
		$ledgers_q = $this->db->get();
		foreach ($ledgers_q->result() as $row)
		{
			list ($opbalance, $optype) = $this->get_op_balance($row->id);
			if ($optype == "D")
			{
				$total_op = float_ops($total_op, $opbalance, '+');
			} else {
				$total_op = float_ops($total_op, $opbalance, '-');
			}
		}
		return $total_op;
	}

	/* Return debit total as positive value */
	function get_dr_total($ledger_id)
	{
		$this->db->select_sum('amount', 'drtotal')->from('entry_items')->join('entries', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->where('entry_items.dc', 'D');
		$dr_total_q = $this->db->get();
		if ($dr_total = $dr_total_q->row())
			return $dr_total->drtotal;
		else
			return 0;
	}

	/* Return credit total as positive value */
	function get_cr_total($ledger_id)
	{
		$this->db->select_sum('amount', 'crtotal')->from('entry_items')->join('entries', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->where('entry_items.dc', 'C');
		$cr_total_q = $this->db->get();
		if ($cr_total = $cr_total_q->row())
			return $cr_total->crtotal;
		else
			return 0;
	}

	/* Delete reconciliation entries for a Ledger account */
	function delete_reconciliation($ledger_id)
	{
		$update_data = array(
			'reconciliation_date' => NULL,
		);
		$this->db->where('ledger_id', $ledger_id)->update('entry_items', $update_data);
		return;
	}

	/** 
	 * Returns code of the requested account, 
	 * as specified in the 'groups' table
	 */
	function get_account_code($account_name)
	{
		$this->db->from('groups');
                $this->db->select('code');
		$this->db->where('name =', $account_name);
		if($account_name == 'Expenses')
			$this->db->or_where('name = ', 'Expenditure'); 
		if($account_name == 'Liabilities and Owners Equity')
			$this->db->or_where('name = ', 'Sources of Funds');
		if($account_name == 'Assets')
			$this->db->or_where('name = ', 'Application of Funds');
                $group = $this->db->get();
                foreach($group->result() as $row)
			return $row->code;                			
	}

	function get_projection()
        {
                $projection = array();
                $counter = 0;
                $this->db->from('projection');
                $this->db->select('id, code, projection_name, bd_balance, group_id');
                $projection_q = $this->db->get();
                foreach($projection_q->result() as $row)
                {
                        $projection[$counter]['id'] = $row->id;
                        $projection[$counter]['code'] = $row->code;
                        $projection[$counter]['name'] = $row->projection_name;
                        $projection[$counter]['bd_balance'] = $row->bd_balance;
                        $projection[$counter]['group_id'] = $row->group_id;
                        $counter++;
                }
		return $projection;
	}

	/** 
         * Returns code of the requested budget or projection, 
         * as specified in the 'budgets' or 'projection' table
         * @author Priyanka Rawat <rpriyanka12@ymail.com>
         */
        function get_code($name, $table)
        {
                $this->db->from($table);
                $this->db->select('code');
		if($table == 'budgets'){
	                $this->db->where('budgetname =', $name);
		}elseif($table == 'projection'){
			$this->db->where('projection_name =', $name);
		}else{
			$this->db->where('name =', $name);
		}
                $query_r = $this->db->get();
                foreach($query_r->result() as $row)
                        return $row->code;
        }

	function check_budget($data_ledger_id,$cr_total,$dr_total,$data_amount,$data)
	{
		 $this->load->library('GetParentlist');
		 $this->db->from('ledgers')->where('id', $data_ledger_id);
                 $query_q = $this->db->get();
                 $query_n = $query_q->row();
                 $this->id = $query_n->id;
                 $this->code = $query_n->code;
                 $this->group_id = $query_n->group_id;
                 $ledg_code=$this->code;
                 $groupid=$this->group_id;

//		 $this->load->model('Budget_model');
                 $account_code = $this->get_account_code('Expenses');
                 $temp = $this->startsWith($ledg_code, $account_code);
                 if($temp){//01
                 //get budget amnt 
                      $parents;
                      $query1=$this->db->from('budgets')->where('code', $ledg_code)->get();

                       /**
                       * code for if particular head is not in budget,
                       * then made payment from parent which is present
                       * in budget.
                       */
                       if($query1->num_rows() > 0)
                       {
				$this->db->from('budgets')->where('code', $ledg_code);
                                $query_l = $this->db->get();
                                $query_l = $query_l->row();
                                $this->amt = $query_l->bd_balance;
                                $this->useamt = $query_l->consume_amount;
                                $this->allow=$query_l->allowedover;
                                $budgetamt=$this->amt;
                                $useamt=$this->useamt;
                                $allow=$this->allow;

                                if($budgetamt > $useamt)
				{//if1
                                	$available_amount=$budgetamt - $useamt ;//its wrong
                                     	/**  payment amount is greater than or equal to available amount **/
                                        if($data_amount > $available_amount)
                                        {
                                        /* check for allowed over expense*/
                                              if(($allow == -1) || ($allow == 0))
                                              {
                                      		     $this->messages->add('Budget is not sufficient to make this payment.','error');
                                                     //       $this->template->load('template', 'entry/add',$data);
                                                      return;
                                              }
                                              else
                                              {
                                                       /* check for payment amount by adding allowd over amount + consume amount */
					    		$available_amount = $budgetamt - $useamt + $allow;
                                                        if($data_amount >= $available_amount)
                                                        {
                                                        	$this->messages->add('Budget is not sufficient to make this payment.','error');
                                                                 //  $this->template->load('template', 'entry/add',$data);
                                                                 return;
                                                         }
                                                         else
                                                         {
                                                         /* Update budget table */
                                                       		  $sumamt=$data_amount + $useamt;
                                                                  $allow_left = $available_amount - $data_amount ;
                                                                  $update_data1 = array('consume_amount' => $sumamt, 'allowedover' => $allow_left);
                                                                  if ( ! $this->db->where('code', $ledg_code)->update('budgets', $update_data1))
                                                                  {
                                                                   	$this->db->trans_rollback();
                                                                        $this->messages->add('Error updating total expenses amount in budget.', 'error');
                                                                        $this->template->load('template', 'entry/add', $data);
                                                                        return;
                                                                  }
                                                                  $parents = new GetParentlist();
                                                                  $parents->init($groupid,$data_amount);
								  //$this->db->from('budgets')->where('code', '50');
                                                                  $this->db->from('budgets')->where('budgetname', 'Main Budget');
                                                                  $query_ll = $this->db->get();
                                                                  $query_ll = $query_ll->row();
                                                                  //$this->id = $query_l->id;
                                                                  $this->amt1 = $query_ll->bd_balance;
                                                                  $this->useamt1 = $query_ll->consume_amount;
                                                                  $update_data2 = $this->useamt1 + $data_amount;
                                                                  $update_data3 = array('consume_amount' => $update_data2);
                                                                  //if ( ! $this->db->where('code', '50')->update('budgets', $update_data3))
                                                                  if ( ! $this->db->where('budgetname', 'Main Budget')->update('budgets', $update_data3))
                                                                  {
                                                                  	$this->db->trans_rollback();
                                                                        $this->messages->add('Error updating total expenses amount in budget.', 'error');
                                                                        $this->template->load('template', 'entry/add', $data);
                                                                        return;
                                                                  }
                                                         }
                                              }
                                        }
					else
                                        {
                                        	$sumamt=$data_amount + $useamt;
                                                $update_data1 = array('consume_amount' => $sumamt );
                                                if (! $this->db->where('code', $ledg_code)->update('budgets', $update_data1))
                                                {
                                                	$this->db->trans_rollback();
                                                        $this->messages->add('Error updating total expenses amount in budget.', 'error');
                                                        $this->template->load('template', 'entry/add', $data);
                                                        return;
                                                }
                                                $parents = new GetParentlist();
                                                $parents->init($groupid,$data_amount);
                                                //$this->db->from('budgets')->where('code', '50');
                                             	$this->db->from('budgets')->where('budgetname', 'Main Budget');
                                                $query_ll = $this->db->get();
                                                $query_ll = $query_ll->row();
                                                $this->amt1 = $query_ll->bd_balance;
                                                $this->useamt1 = $query_ll->consume_amount;
                                                $update_data2 = $this->useamt1 + $data_amount;
                                                $update_data3 = array('consume_amount' => $update_data2);

                                                //if ( ! $this->db->where('code', '50')->update('budgets', $update_data3))
                                                if ( ! $this->db->where('budgetname', 'Main Budget')->update('budgets', $update_data3))
                                                {
                                                	$this->db->trans_rollback();
							$this->messages->add('Error updating total expenses amount in budget.', 'error');
                                                        $this->template->load('template', 'entry/add', $data);
                                                        return;
                                                }
                                        }
                                }//1    
                                /* consume amount is greater than allocated budget amount*/
                                if($useamt >= $budgetamt)
                                {//2
                                /* check for allowed over expenses */
                                	if(($allow == -1) || ($allow == 0))
                                        {
                                        	$this->messages->add('Budget is not sufficient to make this payment.','error');
                                                return;
                                        }
                                        /** get over consume amount and check with allowed left **/
                                        $overconsume_amount = $useamt - $budgetamt ;
                                        /* payment amount is greater than allowed over amount*/
                                        if($data_amount > $allow)
                                        {
                                        	$this->template->load('template', 'entry/add',$data);
                                                return;
                                        }
                                        /* payment amount is less than allowed over amount*/
                                        if($data_amount <= $allow)
                                        {
                                        	$overconsume_amount = $useamt - $budgetamt ;
                                                $available_amount = $allow ;
                                                $allowed_left = $allow - $data_amount;
                                                $consume_amount = $useamt + $data_amount;
						$update_data1 = array('consume_amount' => $consume_amount, 'allowedover' => $allowed_left);
                                                if ( ! $this->db->where('code', $ledg_code)->update('budgets', $update_data1))
                                                {
                                                	$this->db->trans_rollback();
                                                        $this->messages->add('Error updating total expenses amount in budget.', 'error');
                                                        $this->template->load('template', 'entry/add', $data);
                                                        return;
                                                }
                                                $parents = new GetParentlist();
                                                $parents->init($groupid,$data_amount);
                                                //$this->db->from('budgets')->where('code', '50');
                                                $this->db->from('budgets')->where('budgetname', 'Main Budget');
                                                $query_ll = $this->db->get();
                                                $query_ll = $query_ll->row();
                                                $this->amt1 = $query_ll->bd_balance;
                                                $this->useamt1 = $query_ll->consume_amount;
                                                $update_data2 = $this->useamt1 + $data_amount;
                                                $update_data3 = array('consume_amount' => $update_data2);

                                                //if ( ! $this->db->where('code', '50')->update('budgets', $update_data3))
                                                if ( ! $this->db->where('budgetname', 'Main Budget')->update('budgets', $update_data3))
                                                {
                                                	$this->db->trans_rollback();
                                                        $this->messages->add('Error updating total expenses amount in budget.', 'error');
                                                        $this->template->load('template', 'entry/add', $data);
 							return;
                                                }
                                        }
                                }//2
                       }else
                       {
                       		$parents_get ="";
                                $parents_get=$this->init1l($groupid,$data_amount,$data);
                       }
		}//01
	}

	function init1l($id,$data_amount,$data)
	{
                $parent_id = 0;
                $code = "";
                $this->load->library('GetParentlist');
                if ($id == 0)
                {

                        $id = 0;
                        $this->messages->add('Please Add atleast one parent group for this ledger entry for Payment','error');
                        redirect('/entry/add/payment');
                        return $id;

                }else{
                        $this->db->from('groups')->where('id', $id);
                        $group_q = $this->db->get();
                        $group = $group_q->row();
                        $this->parent_id = $group->parent_id;
                        $this->code = $group->code;
                        $this->db->from('budgets')->where('code', $this->code);
                        $query_l = $this->db->get();
                        $query_l = $query_l->num_rows();
                        if($query_l>0)
                        {

                                $budgetamt = 0;
                                $useamt = 0;
                                $allow = 0;
                                $ledg_code=$this->code;
                                $this->db->from('budgets')->where('code', $this->code);
                                $query_l = $this->db->get();
                                $query_l = $query_l->row();
				$this->amt = $query_l->bd_balance;
                                $this->useamt = $query_l->consume_amount;
                                $this->allow=$query_l->allowedover;
                                $budgetamt=$this->amt;
                                $useamt=$this->useamt;
                                $allow=$this->allow;

                                /* if alloted budget amount is more than consume amount*/

                                if($budgetamt > $useamt)

                                {//if1
                                        $available_amount=$budgetamt - $useamt ;//its wrong

                                        /**  payment amount is greater than or equal to available amount **/
                                        if($data_amount > $available_amount)
                                        {
                                                /* check for allowed over expense*/
                                                if(($allow == -1) || ($allow == 0))
                                                {
                                                        $this->messages->add('Budget is not sufficient to make this payment.','error');
                                                        //$this->template->load('template','entry/add',$data);
                                                        redirect('entry/add/payment');
                                                        //redirect
                                                        return ;
                                                }else
                                                {
                                                        /* check for payment amount by adding allowd over amount + consume amount */
                                                      $available_amount = $budgetamt - $useamt + $allow;
                                                      if($data_amount >= $available_amount)
                                                      {
                                                             $this->messages->add('Budget is not sufficient to make this payment.','error');
                                                             //$this->template->load('template', 'entry/add',$data);
                                                             redirect('entry/add');
                                                             return ;
                                                      }else{
                                                                /* Update budget table */
                                                             $sumamt=$data_amount + $useamt;
                                                             $allow_left = $available_amount - $data_amount ;
                                                             $update_data1 = array('consume_amount' => $sumamt, 'allowedover' => $allow_left);
                                                             	if ( ! $this->db->where('code', $ledg_code)->update('budgets', $update_data1))
                                                             	{
                                                                        $this->db->trans_rollback();
                                                                        $this->messages->add('Error updating total expenses amount in budget.', 'error');
                                                                        //$this->template->load('template', 'entry/add', $data);
                                                                        redirect('entry/add/payment');
                                                                        return ;
                                                             	 }
                                                             $parents = new GetParentlist();
                                                             $parents->init($groupid,$data_amount);
                                                             //$this->db->from('budgets')->where('code', '50');
                                                             $this->db->from('budgets')->where('budgetname', 'Main Budget');
                                                             $query_ll = $this->db->get();
                                                             $query_ll = $query_ll->row();
                                                             $this->amt1 = $query_ll->bd_balance;
							     $this->useamt1 = $query_ll->consume_amount;
                                                             $update_data2 = $this->useamt1 + $data_amount;
                                                                $update_data3 = array('consume_amount' => $update_data2);
                                                                //if ( ! $this->db->where('code', '50')->update('budgets', $update_data3))
                                                                if ( ! $this->db->where('budgetname', 'Main Budget')->update('budgets', $update_data3))
                                                                {
                                                                        $this->db->trans_rollback();
                                                                        $this->messages->add('Error updating total expenses amount in budget.', 'error');
                                                                        $this->template->load('template', 'entry/add', $data);
                                                                        redirect('entry/add/payment');
                                                                        return;
                                                                 }
                                                                return;
                                                      }
                                                }

                                        }
                                        else
                                        {
                                                $sumamt=$data_amount + $useamt;
                                                $update_data1 = array('consume_amount' => $sumamt );
                                                $parents = new GetParentlist();
                                                $parents->init($id,$data_amount);

                                                //$this->db->from('budgets')->where('code', '50');
                                                $this->db->from('budgets')->where('budgetname', 'Main Budget');
                                                $query_ll = $this->db->get();
                                                $query_ll = $query_ll->row();
                                                //$this->id = $query_l->id;
                                                $this->amt1 = $query_ll->bd_balance;
                                                $this->useamt1 = $query_ll->consume_amount;
                                                $update_data2 = $this->useamt1 + $data_amount;
                                                $update_data3 = array('consume_amount' => $update_data2);
                                                //echo "$update_data2"; 
                                                //if (!$this->db->where('code', '50')->update('budgets', $update_data3))
                                                if (!$this->db->where('budgetname', 'Main Budget')->update('budgets', $update_data3))
                                                {
                                                        //$this->messages->add("Test in Getparent 8==>");
                                                        $this->db->trans_rollback();
                                                        $this->messages->add('Error updating total expenses amount in budget.', 'error');
                                                        //$this->template->load('template', 'entry/add', $data);
                                                        redirect('entry/add/payment');
                                                        return;
                                                }

                                        }
                                        //$this->template->load('template', 'entry/add', $data);
                                        //return $id;
                                }//1    
				  /* consume amount is greater than allocated budget amount*/
                                if($useamt >= $budgetamt)
                                {//2
                                        /* check for allowed over expenses */
                                      if(($allow == -1) || ($allow == 0))
                                      {
                                                $this->messages->add('Budget is not sufficient to make this payment.','error');
                                                //$this->template->load('template', 'entry/add',$data);
                                                redirect('entry/add/payment');
                                                return;
                                      }
                                        /** get over consume amount and check with allowed left **/


                                      $overconsume_amount = $useamt - $budgetamt ;
                                        /* payment amount is greater than allowed over amount*/
                                      if($data_amount > $allow)
                                      {


                                                $this->messages->add('Budget is not sufficient to make this payment.','error');
                                                //$this->template->load('template', 'entry/add',$data);
                                                redirect('entry/add/payment');
                                                return;
                                      }
                                        /* payment amount is less than allowed over amount*/
                                      if($data_amount <= $allow)
                                      {
				      		$overconsume_amount = $useamt - $budgetamt ;
                                                $available_amount = $allow ;
                                                $allowed_left = $allow - $data_amount;
                                                $consume_amount = $useamt + $data_amount;
                                                $update_data1 = array('consume_amount' => $consume_amount, 'allowedover' => $allowed_left);
                                                if (!$this->db->where('code', $ledg_code)->update('budgets', $update_data1))
                                                {
                                                        $this->db->trans_rollback();
                                                        $this->messages->add('Error updating total expenses amount in budget.', 'error');
                                                        //$this->template->load('template', 'entry/add', $data);
                                                        redirect('entry/add/payment');
                                                        return;
                                                }
                                                $parents = new GetParentlist();
                                                $parents->init($groupid,$data_amount);
                                                //$this->db->from('budgets')->where('code', '50');
                                                $this->db->from('budgets')->where('budgetname', 'Main Budget');
                                                $query_ll = $this->db->get();
                                                $query_ll = $query_ll->row();
                                                //$this->id = $query_l->id;
                                                $this->amt1 = $query_ll->bd_balance;
                                                $this->useamt1 = $query_ll->consume_amount;
                                                $update_data2 = $this->useamt1 + $data_amount;
                                                $update_data3 = array('consume_amount' => $update_data2);
						//if(!$this->db->where('code', '50')->update('budgets', $update_data3))
                                                if(!$this->db->where('budgetname', 'Main Budget')->update('budgets', $update_data3))
                                                {
                                                        $this->db->trans_rollback();
                                                        $this->messages->add('Error updating total expenses amount in budget.', 'error');
                                                        //$this->template->load('template', 'entry/add', $data);
                                                        redirect('entry/add/payment');
                                                        return;
                                                }

						                                        

				      }
                                }//2
                //      return $id;
                        }
                        else{

                                $this->get_parent_groups($id,$data_amount,$data);

                        }
                return $id;
                }
        }//function

        function get_parent_groups($id1,$data_amount,$data)
        {

                $parent_groups = array();
                $this->db->from('groups')->where('id', $id1);
                $parent_group_q = $this->db->get();


                foreach ($parent_group_q->result() as $row)
                {
                        $row->parent_id;
                        $row->code;
                        $this->init1l($row->parent_id,$data_amount,$data);
                }
                //return $row->parent_id;
        }
	
	 function startsWith($str1, $str2)
        {
                return !strncmp($str1, $str2, strlen($str2));
        }	

	//Method for budgetaggregation for get budget information of an account @author-sharad23nov@yahoo.com		

       	function get_agg_budgets($accname)
       	{
                $CI =& get_instance();
		//get account detail. 

                $db1=$CI->load->database('login', TRUE);
                $db1->from('bgasAccData')->where('dblable', $accname);
                $db_name_q = $db1->get();
                foreach ($db_name_q->result() as $row)
                {
                        $db_name = $row->databasename;
                        $db_username = $row->uname;
                        $db_password = $row->dbpass;
                        $host_name = $row->hostname;
                        $port = $row->port;
                }
		try {
    			$dbcon = new PDO("mysql:host=$host_name;dbname=$db_name", $db_username, $db_password);
			/*** echo a message saying we have connected ***/
			//echo 'Connected to database';


	                $budget = array();
        	        $counter = 0;
                	$main_budget_code = 0;

			//get the value of main budget of an account.

			$mbudget = "select * from budgets where budgetname='Main Budget'";
			$stmt = $dbcon->query($mbudget);
			if($stmt != false) 
			{
				foreach ($stmt as $row)
				{
                                        $budget[$counter]['id'] = $row['id'];
                                        $budget[$counter]['code'] = $row['code'];
                                        $budget[$counter]['budgetname'] = $row['budgetname'];
                                        $budget[$counter]['bd_balance'] = $row['bd_balance'];
                                        $budget[$counter]['group_id'] = $row['group_id'];
                                        $budget[$counter]['allowedover'] = $row['allowedover'];
                                        $budget[$counter]['consume_amount'] = $row['consume_amount'];
					$counter++;
				}
			}

                	//get code for expense

	                $expense_code = '40';
//			$budgetquery =  "select * from budgets where code <> $expense_code AND code <> '50' order by code asc ";
//e			$budgetquery =  "select * from budgets where code <> $expense_code order by code asc ";
			$budgetquery =  "select * from budgets where budgetname <> 'Main Budget' order by code asc ";
                        $stmt = $dbcon->query($budgetquery);
                        if($stmt != false)
                        {
                                foreach ($stmt as $row)
                                {
                                        $budget[$counter]['id'] = $row['id'];
                                        $budget[$counter]['code'] = $row['code'];
                                        $budget[$counter]['budgetname'] = $row['budgetname'];
                                        $budget[$counter]['bd_balance'] = $row['bd_balance'];
                                        $budget[$counter]['group_id'] = $row['group_id'];
                                        $budget[$counter]['allowedover'] = $row['allowedover'];
                                        $budget[$counter]['consume_amount'] = $row['consume_amount'];
					$counter++;
                                }
			}
			//print_r($budget);	
			return $budget;
                }
                catch(PDOException $e)
                {
                        echo $e->getMessage();
                }


        }
}
