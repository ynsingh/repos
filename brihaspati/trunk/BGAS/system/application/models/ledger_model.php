<?php

class Ledger_model extends Model {

var $ledgers = array();
	function Ledger_model()
	{
		parent::Model();
	}

	function get_all_ledgers()
	{
		$options = array();
		$options[0] = "(Please Select)";
		//$this->db->from('ledgers')->order_by('code', 'asc');
		$this->db->from('ledgers')->order_by('name', 'asc');
		$ledger_q = $this->db->get();
		foreach ($ledger_q->result() as $row)
		{
			$cd = $row->code;
			$nme = $row->name;
			//if(substr($cd, 0, 2) == 10)
			if(substr($cd, 0, 2) == $this->get_account_code('Liabilities and Owners Equity'))			
				$name = $nme." - L";
			//if(substr($cd, 0, 2) == 20)
			if(substr($cd, 0, 2) == $this->get_account_code('Assets'))
				$name = $nme." - A";
			//if(substr($cd, 0, 2) == 30)
			if(substr($cd, 0, 2) == $this->get_account_code('Incomes'))
				$name = $nme." - I";
			//if(substr($cd, 0, 2) == 40)
			if(substr($cd, 0, 2) == $this->get_account_code('Expenses'))
				$name = $nme." - E";

			//lines added by Priyanka
			//$new_id = $row->id."#".$row->code;
                        //$options[$new_id] = $name;
			//......
			//this line existed
			$options[$row->id] = $name;
		//	$options[$row->id] = $row->name;
		}
		return $options;
	}

	/** 
         * Returns code of the requested account, 
         * as specified in the 'groups' table
         * @author Priyanka Rawat <rpriyanka12@ymail.com>
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

	/* get all ledgers of selected date range */
	function get_all_ledgers1($date1 , $date2)
	{
		$options = array();
		$options[0] = "(Please Select)";
		$this->db->select('a.id, a.date, b.entry_id, b.ledger_id, c.id, c.name, c.code');
		$this->db->from('entries a, entry_items b, ledgers c')->where('a.id = b.entry_id')->where('b.ledger_id = c.id')->order_by('code', 'asc');
		$this->db->where('date >=', $date1);
		$this->db->where('date <=', $date2);
		$ledger = $this->db->get();
		/* check for dates */
		if( $date1 > $date2 )
		{
			$this->messages->add('TO ENTRY DATE should be larger than ENTRY DATE FROM.', 'success');
		}
		else {
			if( $ledger->num_rows() < 1 )
			{
				$this->messages->add('There is no trial balance statement between FROM & TO dates.', 'success');
			}
			foreach ($ledger->result() as $row)
			{
				$cd = $row->code;
				$nme = $row->name;
				//if(substr($cd, 0, 2) == 10)
				if(substr($cd, 0, 2) == $this->get_account_code('Liabilities and Owners Equity'))
					$name = $nme." - L";
				//if(substr($cd, 0, 2) == 20)
				if(substr($cd, 0, 2) == $this->get_account_code('Assets'))
					$name = $nme." - A";
				//if(substr($cd, 0, 2) == 30)
				if(substr($cd, 0, 2) == $this->get_account_code('Incomes'))
					$name = $nme." - I";
				//if(substr($cd, 0, 2) == 40)
				if(substr($cd, 0, 2) == $this->get_account_code('Expenses'))
					$name = $nme." - E";
				$options[$row->id] = $name." ( ".$cd." )" ;
			}
		}
		return $options;
	}	

	function get_all_ledgers_bankcash()
	{
		$options = array();
		$options[0] = "(Please Select)";
		//$this->db->from('ledgers')->where('type', 1)->order_by('code', 'asc');
		$this->db->from('ledgers')->where('type', 1)->order_by('name', 'asc');
		$ledger_q = $this->db->get();
		foreach ($ledger_q->result() as $row)
		{
			$cd = $row->code;
                        $nme = $row->name;
                        //if(substr($cd, 0, 2) == 10)
			if(substr($cd, 0, 2) == $this->get_account_code('Liabilities and Owners Equity'))
                                $name = $nme." - L";
                        //if(substr($cd, 0, 2) == 20)
			if(substr($cd, 0, 2) == $this->get_account_code('Assets'))
                                $name = $nme." - A";
                        //if(substr($cd, 0, 2) == 30)
			if(substr($cd, 0, 2) == $this->get_account_code('Incomes'))
                                $name = $nme." - I";
                        //if(substr($cd, 0, 2) == 40)
			if(substr($cd, 0, 2) == $this->get_account_code('Expenses'))
                                $name = $nme." - E";
                        $options[$row->id] = $name;

		//	$options[$row->id] = $row->name;
		}
		return $options;
	}

	function get_ledgers_bankcash($id)
        {
                $options = array();
                $this->db->from('ledgers')->where('type', 1)->where('id', $id);
                $ledger_q = $this->db->get();
                if ($ledger = $ledger_q->row())
                        return 1;
                else
                        return 0;

        }

	function get_all_ledgers_nobankcash()
	{
		$options = array();
		$options[0] = "(Please Select)";
		$this->db->from('ledgers')->where('type !=', 1)->order_by('name', 'asc');
		$ledger_q = $this->db->get();
		foreach ($ledger_q->result() as $row)
		{
			$cd = $row->code;
                        $nme = $row->name;
                        //if(substr($cd, 0, 2) == 10)
			if(substr($cd, 0, 2) == $this->get_account_code('Liabilities and Owners Equity'))
                                $name = $nme." - L";
                        //if(substr($cd, 0, 2) == 20)
			if(substr($cd, 0, 2) == $this->get_account_code('Assets'))
                                $name = $nme." - A";
                        //if(substr($cd, 0, 2) == 30)
			if(substr($cd, 0, 2) == $this->get_account_code('Incomes'))
                                $name = $nme." - I";
                        //if(substr($cd, 0, 2) == 40)
			if(substr($cd, 0, 2) == $this->get_account_code('Expenses'))
                                $name = $nme." - E";
                        $options[$row->id] = $name;

		//	$options[$row->id] = $row->name;
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
			$cd = $row->code;
                        $nme = $row->name;
                        //if(substr($cd, 0, 2) == 10)
			if(substr($cd, 0, 2) == $this->get_account_code('Liabilities and Owners Equity'))
                                $name = $nme." - L";
                        //if(substr($cd, 0, 2) == 20)
			if(substr($cd, 0, 2) == $this->get_account_code('Assets'))
                                $name = $nme." - A";
                        //if(substr($cd, 0, 2) == 30)
			if(substr($cd, 0, 2) == $this->get_account_code('Incomes'))
                                $name = $nme." - I";
                        //if(substr($cd, 0, 2) == 40)
			if(substr($cd, 0, 2) == $this->get_account_code('Expenses'))
                                $name = $nme." - E";
                        $options[$row->id] = $name;

		//	$options[$row->id] = $row->name;
		}
		return $options;
	}

	function get_name($ledger_id)
	{
		$this->db->from('ledgers')->where('id', $ledger_id)->limit(1);
		$ledger_q = $this->db->get();
		if ($ledger = $ledger_q->row())
			return $ledger->name;
		else
			return "(Error)";
	}
	// to search ledger account in entries
	function get_entry_name_match($entry_id, $entry_type_id, $text)
	{
		/* Selecting whether to show debit side Ledger or credit side Ledger */
		$current_entry_type = entry_type_info($entry_type_id);
		$ledger_type = 'C';

		if ($current_entry_type['bank_cash_ledger_restriction'] == 3)
			$ledger_type = 'D';
		$this->db->select('ledgers.name as name');
		$this->db->from('entry_items')->join('ledgers', 'entry_items.ledger_id = ledgers.id')->where('entry_items.entry_id', $entry_id)->where('entry_items.dc', $ledger_type)->where('ledgers.name LIKE', '%' . $text . '%');
		$ledger_q = $this->db->get();
		$html = '';
		if( $ledger_q->num_rows() == 1 ) {
			foreach ($ledger_q->result() as $ledger)
			{
				$html .= anchor('entry/view/' . $current_entry_type['label'] . "/" . $entry_id, $ledger->name, array('title' => 'View ' . $current_entry_type['name'] . ' Entry', 'class' => 'anchor-link-a'));
			}
			return $html;
		}
		return;
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
				{
				$html .= anchor('entry/view/' . $current_entry_type['label'] . "/" . $entry_id, "(" . $ledger->name . ")", array('title' => 'View ' . $current_entry_type['name'] . ' Entry', 'class' => 'anchor-link-a'));
				}
			else
				{
				$html .= anchor('entry/view/' . $current_entry_type['label'] . "/" . $entry_id, $ledger->name, array('title' => 'View ' . $current_entry_type['name'] . ' Entry', 'class' => 'anchor-link-a'));
			    	}
			return $html;
			}
		return;
	}

	/* get entry name with its ledger type of selected date range */
	function get_entry_name1($entry_id, $entry_type_id)
	{
		/* Selecting both to show debit side Ledger and credit side Ledger */
		$current_entry_type = entry_type_info($entry_type_id);
		$ledger_type = 'C';

		if ($current_entry_type['bank_cash_ledger_restriction'] > 1){
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
				{
					foreach($ledger_q->result() as $row)
					{
						$html .= anchor('entry/view/' . $current_entry_type['label'] . "/" . $entry_id, $row->name . ' - ' . $ledger_type . "<br>", array('title' => 'View ' . $current_entry_type['name'] . ' Entry', 'class' => 'anchor-link-a'));
					}
				}
			else{
				$html .= anchor('entry/view/' . $current_entry_type['label'] . "/" . $entry_id, $ledger->name . ' - ' . $ledger_type . "<br>", array('title' => 'View ' . $current_entry_type['name'] . ' Entry', 'class' => 'anchor-link-a'));
			    }
				$ledger_type = 'D';
				if ($current_entry_type['bank_cash_ledger_restriction'] > 1)
	
					$ledger_type = 'C';
					$this->db->select('ledgers.name as name');
				        $this->db->from('entry_items')->join('ledgers', 'entry_items.ledger_id = ledgers.id')->where('entry_items.entry_id', $entry_id)->where('entry_items.dc', $ledger_type);
				        $ledger_q = $this->db->get();

				        if ( ! $ledger = $ledger_q->row())
				        {
				                return "(Invalid)";
				        }
					else {
						 $ledger_multiple = ($ledger_q->num_rows() > 1) ? TRUE : FALSE;

				                if ($ledger_multiple)
						{
							foreach($ledger_q->result() as $row)
							{
							$html .= anchor('entry/view/' . $current_entry_type['label'] . "/" . $entry_id, $row->name . ' - ' . $ledger_type . "<br>", array('title' => 'View ' . $current_entry_type['name'] . ' Entry', 'class' => 'anchor-link-a'));
							}
						}
				                else
				                        $html .= anchor('entry/view/' . $current_entry_type['label'] . "/" . $entry_id, $ledger->name . ' - ' . $ledger_type, array('title' => 'View ' .  $current_entry_type['name'] . ' Entry', 'class' => 'anchor-link-a'));
					     }
			      }
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

	/* get ledger balance for selected date in current financial year*/ 
	function get_ledger_balance1($ledger_id)
	{
		list ($op_bal, $op_bal_type) = $this->get_op_balance($ledger_id);

		$dr_total = $this->get_dr_total1($ledger_id);
		$cr_total = $this->get_cr_total1($ledger_id);
		$total = float_ops($dr_total, $cr_total, '-');

		if ($op_bal_type == "D")
			$total = float_ops($total, $op_bal, '+');
		else
			$total = float_ops($total, $op_bal, '-');
		return $total;
	}
	/*get ledger balance of previous year for profit & loss and payment & receipt in selected date*/  
	function get_old_ledger_balance($ledger_id)
	{
		list ($op_bal, $op_bal_type) = $this->get_prev_year_op_balance($ledger_id);

		if($op_bal || $op_bal_type) {
			$dr_total = $this->get_old_dr_total($ledger_id);
			$cr_total = $this->get_old_cr_total($ledger_id);
			$total = float_ops($dr_total, $cr_total, '-');
			if ($op_bal_type == "D")
				$total = float_ops($total, $op_bal, '+');
			else
				$total = float_ops($total, $op_bal, '-');
			return $total;
		}
		else {
			$this->messages->add('Previous Year\'s data does not exist.', 'success');
			return 0;
		}
	}

	/* get ledger balance for balancesheet in selected date in current financial year */ 
	function get_balancesheet_ledger_balance($ledger_id)
	{
		list ($op_bal, $op_bal_type) = $this->get_op_balance($ledger_id);

		$dr_total = $this->get_balancesheet_dr_total($ledger_id);
		$cr_total = $this->get_balancesheet_cr_total($ledger_id);
		$total = float_ops($dr_total, $cr_total, '-');
		if ($op_bal_type == "D"){
			$total = float_ops($total, $op_bal, '+');
		}else {
			$total = float_ops($total, $op_bal, '-');
		}
		return $total;
	}

	/* get ledger balance of previous year for balancesheet in selected date */  
	function get_balancesheet_old_ledger_balance($ledger_id)
	{
		list ($op_bal, $op_bal_type) =$this->get_prev_year_op_balance($ledger_id);

		if($op_bal || $op_bal_type) {
			$dr_total = $this->get_balancesheet_old_dr_total($ledger_id);
			$cr_total = $this->get_balancesheet_old_cr_total($ledger_id);
			$total = float_ops($dr_total, $cr_total, '-');
			if ($op_bal_type == "D"){
				$total = float_ops($total, $op_bal, '+');
			}else {
				$total = float_ops($total, $op_bal, '-');
			}
			return $total;
		}
		else {
			return 0;
		}
			
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

	function get_op_balance1($ledger_id, $from_date, $to_date)
	{
		$this->db->select('a.id, a.date, b.entry_id, b.ledger_id, c.id, c.op_balance, c.op_balance_dc');
		$this->db->from('entries a, entry_items b, ledgers c')->where('a.id = b.entry_id')->where('b.ledger_id', $ledger_id);
		$this->db->where('date >=', $from_date);
		$this->db->where('date <=', $to_date);
		$op_bal_q = $this->db->get();
		if ($op_bal = $op_bal_q->row()) {
			return array($op_bal->op_balance, $op_bal->op_balance_dc);
		} else {
			return array(0, "D");
		}
	}
	/* get op_balance of previous year database */
	function get_prev_year_op_balance($ledger_id)
	{
		$ins_name = '';
		$uni_name = '';
		$db_name = '';
		$host_name = '';
		$db_username = '';
		$db_password = '';
		$date1 = '';
		$data = '';
		$old_year = '';
		$db_name ='';
		$old_year_start = '';
		$old_year_end = '';
		$port = '';

		$this->db->from('settings');
		$detail = $this->db->get();
		foreach ($detail->result() as $row)
		{
			$ins_name = $row->ins_name;
			$uni_name = $row->uni_name;
			$date1 = explode("-", $row->fy_start);
			$old_year_start = $date1[0]-1;
			$date1 = explode("-", $row->fy_end);
                        $old_year_end = $date1[0]-1;
		}
		$old_year = $old_year_start . "-" . $old_year_end;

		/* connectivity with login database for getting the previous year database name */
		$db1=$this->load->database('login', TRUE);
		$db1->from('bgasAccData')->where('organization', $ins_name)->where('unit', $uni_name)->where('fyear', $old_year);
		$db_name_q = $db1->get();
		$db1->close();
		foreach ($db_name_q->result() as $row)
		{
			$db_name = $row->databasename;
			$db_username = $row->uname;
			$db_password = $row->dbpass;
			$host_name = $row->hostname;
			$port = $row->port;
		}
		if( $db_name_q->num_rows() == 1 ) {
			/* database connectivity for getting previous year opening balance */
			$con = mysql_connect($host_name, $db_username, $db_password);
			$op_balance = array();
			if($con){
				$value = mysql_select_db($db_name, $con);
				$id = mysql_real_escape_string($ledger_id);
				$cl = "select * from ledgers where id = '$id' limit 1";
				$val = mysql_query($cl);
				if($val != ''){
					while($row = mysql_fetch_assoc($val)) 
					{
						$op_balance = array($row['op_balance'], $row['op_balance_dc']);
						mysql_close($con);		
						return $op_balance;
					}
				}
			}
		}
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

	/* get op_balance of previous year database */
	function get_prev_year_diff_op_balance()
	{
		$ins_name = '';
		$uni_name = '';
		$db_name = '';
		$host_name = '';
		$db_username = '';
		$db_password = '';
		$date1 = '';
		$data = '';
		$old_year_start = '';
		$old_year_end = '';
		$db_name ='';
		$old_year = '';
		$total_op = 0;

		$this->db->from('settings');
		$detail = $this->db->get();
		foreach ($detail->result() as $row)
		{
			$ins_name = $row->ins_name;
			$uni_name = $row->uni_name;
			$date1 = explode("-", $row->fy_start);
			$old_year_start = $date1[0]-1;
			$date1 = explode("-", $row->fy_end);
                        $old_year_end = $date1[0]-1;
		}
		
		$old_year = $old_year_start . "-" . $old_year_end;

		/* connectivity with login database for getting the previous year database name */
		$db1=$this->load->database('login', TRUE);
		$db1->from('bgasAccData')->where('organization', $ins_name)->where('unit', $uni_name)->where('fyear', $old_year);
		$db_name_q = $db1->get();
		$db1->close();
		foreach ($db_name_q->result() as $row)
		{
			$db_name = $row->databasename;
			$db_username = $row->uname;
			$db_password = $row->dbpass;
			$host_name = $row->hostname;
			$port = $row->port;
		}
		/* database connectivity for getting previous year opening balance */
		$con = mysql_connect($host_name, $db_username, $db_password);
		$op_balance = array();
		if($con){
			$value = mysql_select_db($db_name, $con);
			$cl = "select * from ledgers order by 'id'";
			$val = mysql_query($cl);
			if($val != ''){
				while($row = mysql_fetch_assoc($val)) 
				{
					list ($opbalance, $optype) = $this->get_prev_year_op_balance($row['id']);
					if ($optype == "D")
					{
						$total_op = float_ops($total_op, $opbalance, '+');
					} else {
						$total_op = float_ops($total_op, $opbalance, '-');
					}	
				}
			}
		}
		return $total_op;
		mysql_close($con);
	}

	/* Return debit total of selected date in current financial year as positive value */
	function get_dr_total1($ledger_id)
	{
		$this->load->library('session');
		$date1 = $this->session->userdata('date1');
		$date2 = $this->session->userdata('date2');

		$this->db->select_sum('amount', 'drtotal')->from('entry_items')->join('entries', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->where('entry_items.dc', 'D');
		$this->db->where('date >=', $date1);
	        $this->db->where('date <=', $date2);
		$dr_total_q = $this->db->get();
		if ($dr_total = $dr_total_q->row())
			return $dr_total->drtotal;
		else
			return 0;
	}

	/* Return debit total of previous year of selected date as positive value */
	function get_old_dr_total($ledger_id)
	{
		$ins_name = '';
		$uni_name = '';
		$from_date = '';
		$to_date = '';
		$old_year_start = '';
		$old_year_end = '';
		$old_year = '';
		$db_name ='';
		$host_name = '';
		$db_username = '';
		$db_password = '';
		$date1 = '';

		$this->db->from('settings');
		$detail = $this->db->get();
		foreach ($detail->result() as $row)
		{
			$ins_name = $row->ins_name;
			$uni_name = $row->uni_name;
			$date1 = explode("-", $row->fy_start);
			$old_year_start = $date1[0]-1;
			$date1 = explode("-", $row->fy_end);
			$old_year_end = $date1[0]-1;
		}
		$old_year = $old_year_start . "-" . $old_year_end;
		/* connectivity with login database for getting the previous year database name */
		$db1=$this->load->database('login', TRUE);
		$db1->from('bgasAccData')->where('organization', $ins_name)->where('unit', $uni_name)->where('fyear', $old_year);
		$db_name_q = $db1->get();
		$db1->close();
		foreach ($db_name_q->result() as $row)
		{
			$db_name = $row->databasename;
			$db_username = $row->uname;
			$db_password = $row->dbpass;
			$host_name = $row->hostname;
			$port = $row->port;
		}
		/* get from date of previous year */
		$this->load->library('session');
		$date1 = $this->session->userdata('date1');
		$date=explode("-",$date1);
		$old_year1 = $date[0]-1;
		$from_date = $old_year1."-".$date[1]."-".$date[2];

		/* get to date of previous year */
		$date2 = $this->session->userdata('date2');
		$date1=explode("-",$date2);
		$old_year1 = $date1[0]-1;
		$to_date = $old_year1."-".$date1[1]."-".$date1[2];

		/* database connectivity for getting previous year debit amount */
		$con = mysql_connect($host_name, $db_username, $db_password);
		$op_balance = array();
		if($con){
			$value = mysql_select_db($db_name, $con);
			$id = mysql_real_escape_string($ledger_id);
			$abc = "select entry_items.entry_id, sum(amount)from entry_items INNER JOIN entries ON entry_items.entry_id = entries.id where entry_items.ledger_id = '$id' and entry_items.dc = 'D' and entries.date >= '$from_date' and entries.date <= '$to_date'";
			$val = mysql_query($abc);
			if($val != ''){
				while($row = mysql_fetch_assoc($val)) 
				{
					$dr_total = $row['sum(amount)'];
					mysql_close($con);
					return $dr_total;
				}
			}
		}
	}

	/* Return credit total of previous year of selected date as positive value */
	function get_old_cr_total($ledger_id)
	{
		$ins_name = '';
		$uni_name = '';
		$from_date = '';
		$to_date = '';
		$old_year = '';
		$old_year_start = '';
		$old_year_end = '';
		$db_name ='';
		$db_name ='';
		$host_name = '';
		$db_username = '';
		$db_password = '';
		$date1 = '';

		$this->db->from('settings');
		$detail = $this->db->get();
		foreach ($detail->result() as $row)
		{
			$ins_name = $row->ins_name;
			$uni_name = $row->uni_name;
			$date1 = explode("-", $row->fy_start);
			$old_year_start = $date1[0]-1;
			$date1 = explode("-", $row->fy_end);
			$old_year_end = $date1[0]-1;
		}
		$old_year = $old_year_start . "-" . $old_year_end;

		/* connectivity with login database for getting the previous year database name */
		$db1=$this->load->database('login', TRUE);
		$db1->from('bgasAccData')->where('organization', $ins_name)->where('unit', $uni_name)->where('fyear', $old_year);
		$db_name_q = $db1->get();
		$db1->close();
		foreach ($db_name_q->result() as $row)
		{
			$db_name = $row->databasename;
			$db_username = $row->uname;
			$db_password = $row->dbpass;
			$host_name = $row->hostname;
			$port = $row->port;
		}

		/* get from date of previous year */
		$this->load->library('session');
		$date1 = $this->session->userdata('date1');
		$date=explode("-",$date1);
		$old_year1 = $date[0]-1;
		$from_date = $old_year1."-".$date[1]."-".$date[2];
		/* get to date of previous year */
		$date2 = $this->session->userdata('date2');
		$date1 = explode("-",$date2);
		$old_year1 = $date1[0]-1;
		$to_date = $old_year1."-".$date1[1]."-".$date1[2];

		/* database connectivity for getting previous year debit amount */
		$con = mysql_connect($host_name, $db_username, $db_password);
		$op_balance = array();
		if($con){
			$value = mysql_select_db($db_name, $con);
			$id = mysql_real_escape_string($ledger_id);
			$abc = "select entry_items.entry_id, sum(amount)from entry_items INNER JOIN entries ON entry_items.entry_id = entries.id where entry_items.ledger_id = '$id' and entry_items.dc = 'C' and entries.date >= '$from_date' and entries.date <= '$to_date'";
			$val = mysql_query($abc);
			if($val != ''){
				while($row = mysql_fetch_assoc($val)) 
				{
					$cr_total = $row['sum(amount)'];
					mysql_close($con);
					return $cr_total;
				}
			}			
		}
	}

	/* Return credit total of selected date as positive value in current financial year*/
	function get_cr_total1($ledger_id)
	{
		$this->load->library('session');
		$date1 = $this->session->userdata('date1');
		$date2 = $this->session->userdata('date2');
		$this->db->select_sum('amount', 'crtotal')->from('entry_items')->join('entries', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->where('entry_items.dc', 'C');
		$this->db->where('date >=', $date1);
		$this->db->where('date <=', $date2);
		$cr_total_q = $this->db->get();
		if ($cr_total = $cr_total_q->row())
			return $cr_total->crtotal;
		else
			return 0;
	}
	/* Return debit total of balancesheet of selected date as positive value */
	function get_balancesheet_dr_total($ledger_id)
	{
		$this->load->library('session');
		$date1 = $this->session->userdata('date1');
		$date2 = $this->session->userdata('date2');

		$this->db->select_sum('amount', 'drtotal')->from('entry_items')->join('entries', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->where('entry_items.dc', 'D');
		$this->db->where('date >=', $date1);
	        $this->db->where('date <=', $date2);
		$dr_total_q = $this->db->get();
		if ($dr_total = $dr_total_q->row())
			return $dr_total->drtotal;
		else
			return 0;
	}

	/* Return debit total of balancesheet of selected date for previous year as positive value */
	function get_balancesheet_old_dr_total($ledger_id)
	{
		$ins_name = '';
		$uni_name = '';
		$date1 = '';
		$date2 = '';
		$old_year_start = '';
		$old_year_end = '';
		$old_year = '';
		$db_name ='';
		$db_username ='';
		$db_password ='';
		$host_name ='';
		$port ='';
		$this->db->from('settings');
		$detail = $this->db->get();
		foreach ($detail->result() as $row)
		{
			$ins_name = $row->ins_name;
			$uni_name = $row->uni_name;
			$date1 = explode("-", $row->fy_start);
			$old_year_start = $date1[0]-1;
			$date1 = explode("-", $row->fy_end);
                        $old_year_end = $date1[0]-1;
		}
		$old_year = $old_year_start . "-" . $old_year_end;

		/* connectivity with login database for getting the previous year database name */
		$db1=$this->load->database('login', TRUE);
		$db1->from('bgasAccData')->where('organization', $ins_name)->where('unit', $uni_name)->where('fyear', $old_year);
		$db_name_q = $db1->get();
		$db1->close();
		foreach ($db_name_q->result() as $row)
		{
			$db_name = $row->databasename;
			$db_username = $row->uname;
			$db_password = $row->dbpass;
			$host_name = $row->hostname;
			$port = $row->port;
		}
		/*date selected by user of previous financial year */
		$this->load->library('session');
		$date1 = $this->session->userdata('date1');
		$date_1 = explode("-",$date1);
		$old_date = $date_1[0]-1;
		$date1 = $old_date."-".$date_1[1]."-".$date_1[2];
		$date2 = $this->session->userdata('date2');
		$date=explode("-",$date2);
		$old_date = $date[0]-1;
		$date2 = $old_date."-".$date[1]."-".$date[2];

		/* database connectivity for getting previous year debit amount */

		$con = mysql_connect($host_name, $db_username, $db_password);
		$op_balance = array();
		if($con){
			$value = mysql_select_db($db_name, $con);
			$id = mysql_real_escape_string($ledger_id);
			$abc = "select entry_items.entry_id, sum(amount)from entry_items INNER JOIN entries ON entry_items.entry_id = entries.id where entry_items.ledger_id = '$id' and entry_items.dc = 'D' and entries.date >= '$date1' and entries.date <= '$date2'";
			$val = mysql_query($abc);
			if($val != ''){
				while($row = mysql_fetch_assoc($val)) 
				{
					$dr_total = $row['sum(amount)'];
					mysql_close($con);
					return $dr_total;
				}
			}		
		}
	}

	/* Return credit total of balancesheet of selected date as positive value */
	function get_balancesheet_cr_total($ledger_id)
	{
		$this->load->library('session');
		$date2 = $this->session->userdata('date2');
		$date1 = $this->session->userdata('date1');

		$this->db->select_sum('amount', 'crtotal')->from('entry_items')->join('entries', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->where('entry_items.dc', 'C');
		$this->db->where('date >=', $date1);
		$this->db->where('date <=', $date2);
		$cr_total_q = $this->db->get();
		if ($cr_total = $cr_total_q->row())
			return $cr_total->crtotal;
		else
			return 0;
	}

	/* Return credit total of balancesheet of selected date for previous year as positive value */
	function get_balancesheet_old_cr_total($ledger_id)
	{
		$ins_name = '';
		$uni_name = '';
		$date1 = '';
		$old_year_start = '';
		$old_year_end = '';
		$old_year = '';
		$db_name ='';
		$db_username ='';
		$db_password ='';
		$host_name ='';
		$port ='';

		$this->db->from('settings');
		$detail = $this->db->get();
		foreach ($detail->result() as $row)
		{
			$ins_name = $row->ins_name;
			$uni_name = $row->uni_name;
			$date1 = explode("-", $row->fy_start);
			$old_year_start = $date1[0]-1;
			$date1 = explode("-", $row->fy_end);
			$old_year_end = $date1[0]-1;
		}
		$old_year = $old_year_start . "-" . $old_year_end;

		/* connectivity with login database for getting the previous year database name */
		$db1=$this->load->database('login', TRUE);
		$db1->from('bgasAccData')->where('organization', $ins_name)->where('unit', $uni_name)->where('fyear', $old_year);
		$db_name_q = $db1->get();
		$db1->close();
		foreach ($db_name_q->result() as $row)
		{
			$db_name = $row->databasename;
			$db_username = $row->uname;
			$db_password = $row->dbpass;
			$host_name = $row->hostname;
			$port = $row->port;
		}
		/*date selected by user of previous financial year */
		$this->load->library('session');

		$date1 = $this->session->userdata('date1');
		$date_1 = explode("-",$date1);
		$old_date = $date_1[0]-1;
		$date1 = $old_date."-".$date_1[1]."-".$date_1[2];

		$date2 = $this->session->userdata('date2');
		$date=explode("-",$date2);
		$old_date = $date[0]-1;
		$date2 = $old_date."-".$date[1]."-".$date[2];

		/* database connectivity for getting previous year debit amount */

		$con = mysql_connect($host_name, $db_username, $db_password);
		$op_balance = array();
		if($con){
			$value = mysql_select_db($db_name, $con);
			$id = mysql_real_escape_string($ledger_id);
			$abc = "select entry_items.entry_id, sum(amount)from entry_items INNER JOIN entries ON entry_items.entry_id = entries.id where entry_items.ledger_id = '$id' and entry_items.dc = 'C' and entries.date >= '$date1' and entries.date <= '$date2'";
			$val = mysql_query($abc);
			if($val != ''){
				while($row = mysql_fetch_assoc($val)) 
				{
					$cr_total = $row['sum(amount)'];
					mysql_close($con);
					return $cr_total;
				}
			}
		}
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
 	function get_numOfChild($id)
        {
                $num = 0;
		$sql = "SELECT id FROM ledgers WHERE group_id =?";
		$query = $this->db->query($sql, array($id));
		$num = $query->num_rows();
		return $num;

	}

	function get_ledger_code($id)
	{
		$this->db->select('code');
		$this->db->from('ledgers')->where('id =', $id);
		$ledger_result = $this->db->get();
		if ($ledger = $ledger_result->row())
                        return $ledger->code;
                else
                        return 0;
	}


	function get_asset_amount($id)
        {
                 $i=0;
                 $tot_value='';
                 $tot_cur_value='';
                 $tot_amt='';
                 $data=array();
                 /*load database pico*/
                 $logndb = $this->load->database('pico', TRUE);
                 $this->logndb =& $logndb;
                 $this->logndb->select('a.ERPMIM_ID, a.ERPMIM_Depreciation_Percentage, a.ERPMIM_Item_Brief_Desc, b.IRD_Rate, b.IR_Item_ID, b.IRD_WEF_Date');
                 $this->logndb->from('erpm_item_master a, erpm_item_rate b')->where('a.ERPMIM_ID  = b.IR_Item_ID ')->where('a.ERPMIM_Item_Brief_Desc ',$id );
                 $user_data = $this->logndb->get();

                 if($user_data->num_rows() > 0){
                         foreach($user_data->result() as $row1)
                         {
                                $ERPMIM_Item_Brief_Desc= $row1->ERPMIM_Item_Brief_Desc;
	                        $ERPMIM_Depreciation_Percentage= $row1->ERPMIM_Depreciation_Percentage;
                                $IRD_WEF_Date=$row1->IRD_WEF_Date;
                                $IRD_Rate= $row1->IRD_Rate;
                                $date2=Date("d F Y");
                                $date3=date_create(" $IRD_WEF_Date");
                                $date4=date_create("$date2");
                                $diff=date_diff($date3,$date4);
                                $day = $diff->format("%R%a days");
                                $value= $IRD_Rate * $ERPMIM_Depreciation_Percentage/(100*365);
                                $tot_amount=$value * $day;
                                $cur_value = $IRD_Rate - $tot_amount;
                                if($cur_value <= 0)
                                {
                                        $value=$IRD_Rate .'#'. $IRD_Rate .'#'. 0;
                                        $data['key']=$value;
                                        return $data;
                                 }
                                 else
                                 {
                                         $tot_value+=$tot_amount;
                                         $tot_cur_value+=$cur_value;
                                         $tot_amt+=$IRD_Rate;
                                         $i++;
                                  }
                            }
                    }

                $value1=$tot_amt .'#'. $tot_value .'#'. $tot_cur_value;
                $data['key']=$value1;
                return $data;

        }

	function get_other_ledger_name($entry_id, $entry_type_label, $ledger_type, $amount)
        {
                $output =false;
                $idarray1=array();
                $idarray2=array();
		$ledid1=$entry_id[0];
		$ledid2=$entry_id[1];
		$ltype1=$ledger_type[0];
		$ltype2=$ledger_type[1];
               	$this->db->from('entry_items')->where('ledger_id', $ledid1)->where('amount',$amount)->where('dc',$ltype1);
                $opp_entry_name_q = $this->db->get();
                foreach ($opp_entry_name_q->result() as $row)
                {
                	$idarray1[]=$row->entry_id;
                }
               	$this->db->from('entry_items')->where('ledger_id', $ledid2)->where('amount',$amount)->where('dc',$ltype2);
                $opp_entry_name = $this->db->get();
                foreach ($opp_entry_name->result() as $row)
                {
                	$idarray2[]=$row->entry_id;
                }
               	foreach ($idarray1 as $id1){
                       	foreach ($idarray2 as $id2){
                               	if($id1==$id2){
                                      $output=true;
                                      break;
                               	}
                       	}
               	}
                return $output;
        }
	function get_ledger_list($ledger_name)
	{
		//$ledgers = array();	
		$this->db->select('id');
		$this->db->from('groups')->where('name =', $ledger_name);
		if($ledger_name == 'Liabilities and Owners Equity')
                        $this->db->or_where('name = ', 'Sources of Funds');
		$query = $this->db->get();
		if($query->num_rows() > 0){
			$result = $query->row();
			$parent_id = $result->id;
		
			$this->db->select('name, id');
			$this->db->from('groups')->where('parent_id =', $parent_id);
			$query = $this->db->get();
			$this->ledgers[0] = "Group Name: Ledger Name";
			//$counter = 1;

			foreach($query->result() as $group)
			{
				$this->get_ledger_list($group->name);
				$this->db->select('name');
				$this->db->from('ledgers')->where('group_id =', $group->id);
				$query = $this->db->get();
			
				if($query->num_rows() > 0){
					foreach($query->result() as $ledger)			
					{
						$this->ledgers[$group->name.": ".$ledger->name] = $group->name.": ".$ledger->name;
						//$counter++;
					}
				}
//				else
//					$this->ledgers[$group->name.": "] = $group->name.": ";
			}
		}
		return $this->ledgers;
	}
        /** get only ledgers in which user is permitted.**/
        function get_all_ledgers_permission()
        {

                $this->load->library('Addsubgroup');
                //get logged in username
                $allgroup = array();
                //$options = array();
                //$options[0] = "(Please Select)";

                $data_user_name= $this->session->userdata('user_name');
                $this->db->from('bgas_acl')->where('username',$data_user_name)->where('atype','grp');
                $heads = $this->db->get();
                print_r(sizeof($heads));
                $options = array();
                $options[0] ="(Please Select)";
                foreach ($heads->result() as $row1)
                {
                        $headid=$row1->headid;
                        $allgroup = new Addsubgroup();
                        $allgroup->init($headid);
                        $value=array();
                        $this->db->from('ledgers')->where('group_id',$allgroup->id)->order_by('name', 'asc');
                                        $ledger_q = $this->db->get();
                                        foreach ($ledger_q->result() as $row)
                                        {
                                                $cd = $row->code;
                                                $nme = $row->name;
                                                if(substr($cd, 0, 2) == 10)
                                                        $name = $nme." - L";
                                                if(substr($cd, 0, 2) == 20)
                                                        $name = $nme." - A";
                                                if(substr($cd, 0, 2) == 30)
                                                        $name = $nme." - I";
                                                if(substr($cd, 0, 2) == 40)
                                                        $name = $nme." - E";
                                                //echo $row->id;
                                                $options[$row->id] = $name;
                                        }


                        foreach ($allgroup as $value)
                        {
                                foreach ((array)$value as $value1)
                                {
                                        $gid=@ $value1->id;
                                        $this->db->from('ledgers')->where('group_id',$gid)->order_by('name', 'asc');
                                        $ledger_q = $this->db->get();
                                        foreach ($ledger_q->result() as $row)
                                        {
                                                $cd = $row->code;
                                                $nme = $row->name;
                                                if(substr($cd, 0, 2) == 10)
                                                        $name = $nme." - L";
                                                if(substr($cd, 0, 2) == 20)
                                                        $name = $nme." - A";
                                                if(substr($cd, 0, 2) == 30)
                                                        $name = $nme." - I";
                                                if(substr($cd, 0, 2) == 40)
                                                        $name = $nme." - E";

                                                //echo $row->id;
                                                $options[$row->id] = $name;
                                        }
                                }
                        }
                }
                return $options;
        }


	function get_ledgers(){

		$funds = array();

		$income_code = $this->get_account_code('Liabilities and Owners Equity');
		
		$unrestricted_code = $this->get_account_code('Unrestricted Funds');
		$restricted_code = $this->get_account_code('Restricted Funds');
		$general_fund = $this->get_account_code('General Funds');
		$current_liab = $this->get_account_code('Current Liabilities And Provisions');

		//current liability should be part of fund list
		//$current_liab = $this->get_account_code('Current Liabilities-L');
                $provision = $this->get_account_code('Provision');

		$this->db->select('name, id, group_id, code');
		$this->db->from('ledgers');
		//$this->db->like('code', '10', 'after'); 
		$this->db->like('code', $income_code, 'after');
		$this->db->not_like('name', 'General', 'both');
		$this->db->not_like('name', 'Capital', 'both');
		$this->db->not_like('name', 'Income & Expenditure', 'both');
		$query = $this->db->get();

		$funds[0] = 'Select Fund';
		if($query->num_rows() > 0){
			foreach($query->result() as $ledger){
				if(($unrestricted_code != '') &&  ($restricted_code != '') && ($general_fund != '') && ($current_liab != '')){
					if($this->startsWith($ledger->code, $unrestricted_code) || $this->startsWith($ledger->code, $restricted_code) || $this->startsWith($ledger->code, $current_liab)){
						if(!($this->startsWith($ledger->code, $general_fund)))
							$funds[$ledger->id] = $ledger->name;
					}
				//}elseif(($current_liab != '') && ($provision != '')){
				}elseif($provision != ''){
					//if(!($this->startsWith($ledger->code, $current_liab)) && !($this->startsWith($ledger->code, $provision))){
					if(!($this->startsWith($ledger->code, $provision))){
						$funds[$ledger->id] = $ledger->name;
					}
				}
			}
		}

		return $funds;
	
	}

	function startsWith($str1, $str2)
        {
                return !strncmp($str1, $str2, strlen($str2));
        }

	function isFund($ledger_code){
                $flag = false;
                $income_code = $this->get_account_code('Liabilities and Owners Equity');

                $unrestricted_code = $this->get_account_code('Unrestricted Funds');
                $restricted_code = $this->get_account_code('Restricted Funds');

                $ownfund_code = $this->get_account_code('Own Funds');
                $reservesurplus_code = $this->get_account_code('Reserves and Surplus');
                $earn_code = $this->get_account_code('Earnmarked');
                $other_code = $this->get_account_code('Other Funds');
                $endowcode = $this->get_account_code('Endowment Funds-L');

//                $this->db->from('ledgers');
  //              $this->db->like('code', $income_code, 'after');
    //            $query = $this->db->get();
      //          if($query->num_rows() > 0){
        //                foreach($query->result() as $ledger){
			if($this->startsWith($ledger_code, $income_code)){
                                if(($unrestricted_code != '') &&  ($restricted_code != '')){
                                        if($this->startsWith($ledger_code, $unrestricted_code) || $this->startsWith($ledger_code, $restricted_code)){
                                                $flag = true;
                                        }
                                }elseif(($ownfund_code != '') && ($reservesurplus_code != '') && ($earn_code != '') && ($other_code != '') && ($endowcode != '')){
                                        if($this->startsWith($ledger_code, $ownfund_code) || $this->startsWith($ledger_code, $reservesurplus_code) || $this->startsWith($ledger_code, $earn_code) || $this->startsWith($ledger_code, $other_code) ||  $this->startsWith($ledger_code, $endowcode)){
                                                $flag = true;
                                        }
                                }
                        }
               // }
                return $flag;

	}
}
?>
