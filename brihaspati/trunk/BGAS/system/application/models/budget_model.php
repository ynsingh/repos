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

	function get_allocation_amount($data_parent_id)
	{
		$parent_amount = '';
		$parent_date = '';
		$this->db->from('budget_allocate');
		$this->db->select_max('creation_date')->where('code =', $data_parent_id);
		$date_result = $this->db->get();
		foreach($date_result->result() as $date)
                {
                        $parent_date = $date->creation_date;
                }
		if($parent_date != NULL)
		{
			$this->db->from('budget_allocate');
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

	function get_child_budgets($parentId)
	{
		$child = array();
		$budget_id = 0;
		$counter = 0;
		$this->db->from('budgets');
		$this->db->select('code')->where('group_id =', $parentId);
		$child_budget = $this->db->get();
		foreach($child_budget->result() as $row)
		{
			$child[$counter]['code'] = $row->code;
			$counter++;
		}
		return $child;
	}


	function get_selected_groups()
        {
                $options = array();

		//Added 'Main Budget' as first value
		//to be displayed in the list
		$new_id = "50"."#"."Main Budget";
                $options[$new_id] = 'Main Budget';

                $this->db->from('groups');
		$this->db->where('code LIKE', '40%');
		$this->db->where('status', '0')->order_by('name', 'asc');
                //$this->db->select('(SELECT name FROM groups WHERE code LIKE '40%' order by name ASC;)'); 
                $group_code = $this->db->get();
                foreach ($group_code->result() as $row)
                {
                        $new_id = "$row->code"."#"."$row->name";
                        $options[$new_id] = $row->name;
                        //$options[$row->id] = $row->name;
                }

		$this->db->from('ledgers')->where('code LIKE', '40%')->order_by('name', 'asc');
                //$this->db->select('(SELECT name FROM groups WHERE code LIKE '40%' order by name ASC;)'); 
                $group_code = $this->db->get();
                foreach ($group_code->result() as $row)
                {
                        $new_id = "$row->code"."#"."$row->name";
                        $options[$new_id] = $row->name;
                        //$options[$row->id] = $row->name;
                }
		//sort($options);
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
		$this->db->from('budgets');
                $this->db->select('id, code, budgetname, bd_balance, group_id')->where('code =', '50');
		$budget_q = $this->db->get();
                foreach($budget_q->result() as $row)
                {
                        $budget[$counter]['id'] = $row->id;
                        $budget[$counter]['code'] = $row->code;
                        $budget[$counter]['budgetname'] = $row->budgetname;
                        $budget[$counter]['bd_balance'] = $row->bd_balance;
                        $budget[$counter]['group_id'] = $row->group_id;
                        $counter++;
                }

		$this->db->from('budgets');
		$this->db->select('id, code, budgetname, bd_balance, group_id')->where('code <>', '40');
		$this->db->where('code <>', '50')->order_by('code', 'asc');
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

	function get_groupid_budgetname($parent_name)
	{
		$parent_id = '';
		//$options[0] = "(Please Select)";
                //$this->db->from('budgets')->order_by('budgetname', 'asc');
                $this->db->from('budgets');
		$this->db->select('id')->where('code =', $parent_name);
                $group_budget = $this->db->get();
                foreach ($group_budget->result() as $row)
                {
                        //$options[$row->id] = $row->budgetname;
		//	$this->logger->write_message("error", "Error Priyanka " . $row->id);
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
}
