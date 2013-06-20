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

	function get_selected_budgets()
        {
                $options = array();
                //$this->db->from('budgets')->order_by('budgetname', 'asc');
		$this->db->from('budgets');
		//$this->db->select('id, budgetname');
		$this->db->select('id, budgetname')->where('code LIKE', '____')->order_by('budgetname', 'asc');
                $budget_parent_q = $this->db->get();
		$counter = 0;
                foreach ($budget_parent_q->result() as $row)
                {
                       // $options[$row->id] = $row->budgetname;
			$options[$counter]['id'] = $row->id;
                        $options[$counter]['name'] = $row->budgetname;
			$counter++;
                }
                return $options;
        }

	function get_sub_budget()
	{
		$options = array();
                $this->db->from('budgets');
                //$this->db->select('id, group_id, budgetname')->where('code LIKE', '_____%')->order_by('budgetname', 'asc');
		$this->db->select('id, group_id, budgetname')->where('code LIKE', '_____%');
                $budget_parent_q = $this->db->get();
                $counter = 0;
                foreach ($budget_parent_q->result() as $row)
                {
			//$options[$counter]['group_id'] = $row->group_id;
                        $options[$counter]['name'] = $row->budgetname;
			$options[$counter]['id'] = $row->id;
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

	function get_child_budget($id)
	{
		$options = array();
                $this->db->from('budgets');
		$this->db->select('code')->where('id =', $id);
		$child_budget = $this->db->get();
	}

	/*function get_max_value($code)
	{
		$this->db->select('allocation_amount, reappropriation_amount')$this->db->from('budget_allocate')->where('code = ',$code);
		//$this->db->from('budget_allocate')->where('id = ',$id);
		//$this->db->select('allocation_amount, reappropriation_amount');
		$budget_allocate_q = $this->db->get();
		if ($budget_allocate_q->num_rows() > 0)
		{
			$row = $budget_allocate_q->result();
			if ($row->allocation_amount > $row->reappropriation_amount)
				$option = $row->allocation_amount;
			else
				$option = $row->reappropriation_amount;
		}
		return $option;
	}*/

	function get_selected_groups()
        {
                $options = array();
                $this->db->from('groups')->where('code LIKE', '40%')->order_by('name', 'asc');
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

	function get_groupid_budgetname()
	{
		$options = array();
		$options[0] = "(Please Select)";
                $this->db->from('budgets')->order_by('budgetname', 'asc');
		//$this->db->select("CONCAT_WS('_', id, budgetname)");
                $group_budget = $this->db->get();
                foreach ($group_budget->result() as $row)
                {
                        $options[$row->id] = $row->budgetname;
                }
                return $options;
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