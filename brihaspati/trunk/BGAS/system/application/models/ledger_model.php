<?php

class Ledger_model extends Model {

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
			if(substr($cd, 0, 2) == 10)
				$name = $nme." - L";
			if(substr($cd, 0, 2) == 20)
				$name = $nme." - A";
			if(substr($cd, 0, 2) == 30)
				$name = $nme." - I";
			if(substr($cd, 0, 2) == 40)
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
				if(substr($cd, 0, 2) == 10)
					$name = $nme." - L";
				if(substr($cd, 0, 2) == 20)
					$name = $nme." - A";
				if(substr($cd, 0, 2) == 30)
					$name = $nme." - I";
				if(substr($cd, 0, 2) == 40)
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
                        if(substr($cd, 0, 2) == 10)
                                $name = $nme." - L";
                        if(substr($cd, 0, 2) == 20)
                                $name = $nme." - A";
                        if(substr($cd, 0, 2) == 30)
                                $name = $nme." - I";
                        if(substr($cd, 0, 2) == 40)
                                $name = $nme." - E";
                        $options[$row->id] = $name;

		//	$options[$row->id] = $row->name;
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
                        if(substr($cd, 0, 2) == 10)
                                $name = $nme." - L";
                        if(substr($cd, 0, 2) == 20)
                                $name = $nme." - A";
                        if(substr($cd, 0, 2) == 30)
                                $name = $nme." - I";
                        if(substr($cd, 0, 2) == 40)
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

	/* get ledger balance for selected date */ 
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

	/* get ledger balance for balancesheet in selected date */ 
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

	/* Return debit total of selected date as positive value */
	function get_dr_total1($ledger_id)
	{
		$year_start = '';
		$year_end = '';
		$this->load->library('session');
		$this->db->from('settings');
		$detail = $this->db->get();
		foreach ($detail->result() as $row)
		{
			$year_start = $row->fy_start;
			$year_end = $row->fy_end;
		}
		$date1 = $this->session->userdata('date1');
		$date2 = $this->session->userdata('date2');
		$date1 = explode("-",$date1);
		$from_date = $year_start . "-". $date1[1] . "-" . $date1[2];
		$date = explode("-",$date2);
		$to_date = $year_end . "-" . $date[1] . "-" . $date[2];
		$this->db->select_sum('amount', 'drtotal')->from('entry_items')->join('entries', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->where('entry_items.dc', 'D');
		$this->db->where('date >=', $from_date);
	        $this->db->where('date <=', $to_date);
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
		$old_year = $date[0]-1;
		$from_date = $old_year."-".$date[1]."-".$date[2];

		/* get to date of previous year */
		$date2 = $this->session->userdata('date2');
		$date1=explode("-",$date2);
		$old_year = $date1[0]-1;
		$to_date = $old_year."-".$date1[1]."-".$date1[2];

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
		$old_year = $date[0]-1;
		$from_date = $old_year."-".$date[1]."-".$date[2];
		/* get to date of previous year */
		$date2 = $this->session->userdata('date2');
		$date1 = explode("-",$date2);
		$old_year = $date1[0]-1;
		$to_date = $old_year."-".$date1[1]."-".$date1[2];

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

	/* Return credit total of selected date as positive value */
	function get_cr_total1($ledger_id)
	{
		$year_start = '';
		$year_end = '';
		$this->load->library('session');
		$this->db->from('settings');
		$detail = $this->db->get();
		foreach ($detail->result() as $row)
		{
			$year_start = $row->fy_start;
			$year_end = $row->fy_end;
		}
		$date1 = $this->session->userdata('date1');
		$date2 = $this->session->userdata('date2');
		$date1 = explode("-",$date1);
		$from_date = $year_start . "-". $date1[1] . "-" . $date1[2];

		$date1 = explode("-",$date2);
		$to_date = $year_end . "-" . $date1[1] . "-" . $date1[2];
		$this->db->select_sum('amount', 'crtotal')->from('entry_items')->join('entries', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->where('entry_items.dc', 'C');
		$this->db->where('date >=', $from_date);
		$this->db->where('date <=', $to_date);
		$cr_total_q = $this->db->get();
		if ($cr_total = $cr_total_q->row())
			return $cr_total->crtotal;
		else
			return 0;
	}
	/* Return debit total of balancesheet of selected date as positive value */
	function get_balancesheet_dr_total($ledger_id)
	{
		$year_start = '';
		$year_end = '';
		$start_date = '';
		$default_start = '-04-01';
		$this->db->from('settings');
		$detail = $this->db->get();
		foreach ($detail->result() as $row)
		{
			$year_start = $row->fy_start;
			$year_end = $row->fy_end;
		}
		$start_date = $year_start . $default_start;
		$this->load->library('session');
		$date2 = $this->session->userdata('date2');
		$this->db->select_sum('amount', 'drtotal')->from('entry_items')->join('entries', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->where('entry_items.dc', 'D');
		$this->db->where('date >=', $start_date);
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
		/*starting date of previous financial year */
		$default_start = '-04-01';
		$default_start = $old_year_start . $default_start;

		/*date selected by user of previous financial year */
		$this->load->library('session');
		$date2 = $this->session->userdata('date2');
		$date1=explode("-",$date2);
		$old_date = $date1[0]-1;
		$date = $old_date."-".$date1[1]."-".$date1[2];

		/* database connectivity for getting previous year debit amount */

		$con = mysql_connect($host_name, $db_username, $db_password);
		$op_balance = array();
		if($con){
			$value = mysql_select_db($db_name, $con);
			$id = mysql_real_escape_string($ledger_id);
			$abc = "select entry_items.entry_id, sum(amount)from entry_items INNER JOIN entries ON entry_items.entry_id = entries.id where entry_items.ledger_id = '$id' and entry_items.dc = 'D' and entries.date >= '$default_start' and entries.date <= '$date'";
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
		$year_start = '';
		$year_end = '';
		$start_date = '';
		$this->db->from('settings');
		$detail = $this->db->get();
		foreach ($detail->result() as $row)
		{
			$year_start = $row->fy_start;
			$year_end = $row->fy_end;
		}
		$default_start = '-04-01';
		$start_date = $year_start . $default_start;
	
		$this->load->library('session');
		$date2 = $this->session->userdata('date2');
		$this->db->select_sum('amount', 'crtotal')->from('entry_items')->join('entries', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->where('entry_items.dc', 'C');
		$this->db->where('date >=', $start_date);
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

		/*starting date of previous financial year */
		$default_start = '-04-01';
		$default_start = $old_year_start . $default_start;

		/*date selected by user of previous financial year */
		$this->load->library('session');
		$date2 = $this->session->userdata('date2');
		$date1=explode("-",$date2);
		$old_year_end = $date1[0]-1;
		$date = $old_year_end."-".$date1[1]."-".$date1[2];

		/* database connectivity for getting previous year debit amount */

		$con = mysql_connect($host_name, $db_username, $db_password);
		$op_balance = array();
		if($con){
			$value = mysql_select_db($db_name, $con);
			$id = mysql_real_escape_string($ledger_id);
			$abc = "select entry_items.entry_id, sum(amount)from entry_items INNER JOIN entries ON entry_items.entry_id = entries.id where entry_items.ledger_id = '$id' and entry_items.dc = 'C' and entries.date >= '$default_start' and entries.date <= '$date'";
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
}
?>



