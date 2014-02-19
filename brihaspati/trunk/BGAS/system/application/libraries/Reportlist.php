<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Reportlist
{
	var $id = 0;
	var $name = "";
	var $code = "";
	var $status = 0;
	var $total = 0;
	var $total2 = 0;
	var $optype = "";
	var $opbalance = 0;
	var $schedule = 0;
	var $children_groups = array();
	var $children_ledgers = array();
	var $counter = 0;

	var $dr_total = 0;
        var $cr_total = 0;
        var $old_dr_total = 0;
        var $old_cr_total = 0;
        var $netpl = 0;
        var $netpl_old = 0;
	var $prevYearDB = "";
        var $old_total = 0;
	var $opening_balance = 0;
	var $opening_balance_prev = 0;
	//var $opening_balance_type = "";
	var $db_username = "";
	var $db_password = "";
	var $host_name = "";
	var $port = "";

	public static $temp_max = 0;
	public static $max_depth = 0;
	public static $csv_data = array();
	public static $csv_row = 0;

	function Reportlist()
	{
		return;
	}

	function getPreviousYearDetails()
        {
                $CI =& get_instance();
                $CI->db->from('settings')->where('id', 1);
                $settings_q = $CI->db->get();
                $settings= $settings_q->row();
                $ins_name = $settings->ins_name;
                $uni_name = $settings->uni_name;
		$date1 = explode("-", $settings->fy_start);
                $old_year_start = $date1[0]-1;
                $date1 = explode("-", $settings->fy_end);
                $old_year_end = $date1[0]-1;
                $previous_year = $old_year_start . "-" . $old_year_end;

                //$current_year = explode("-", $settings->fy_start);
                //$previous_year = $current_year[0]-1;
                $CI->db->close();

                //fetch previous year details
                $CI =& get_instance();
                $db = $CI->load->database('login', TRUE);
                $db->select('databasename, uname, dbpass, hostname, port');

                $db->from('bgasAccData')->where('organization', $ins_name)->where('unit', $uni_name)->where('fyear', $previous_year);
                $login_q = $db->get();
                if($login_q->num_rows()>0){
                        $login = $login_q->row();
                        $this->prevYearDB = $login->databasename;
			$this->db_username = $login->uname;
        		$this->db_password = $login->dbpass;
        		$this->host_name = $login->hostname;
        		$this->port = $login->port;
                }
                $CI->db->close();
        }

	function init($id)
	{
		$CI =& get_instance();
		if ($id == 0)
		{
			$this->id = 0;
			$this->name = "None";
			$this->total = 0;

		}
		 else {
			$CI->db->from('groups')->where('id', $id)->limit(1);
			$group_q = $CI->db->get();
			$group = $group_q->row();
			$this->id = $group->id;
			$this->name = $group->name;
			$this->code = $group->code;
			$this->status = $group->status;
			//$this->schedule = $group->schedule;
			$this->total = 0;
			$this->total2 = 0;
		}
		if($this->status==0)
		{
			$new_code = substr($this->code, 0, $this->code < 0 ? 3 : 2);
			if($new_code == 10 || $new_code == 20)
			{
				$this->add_sub_groups();
				$this->add_balancesheet_sub_ledgers();
			}
			elseif($new_code == 30 || $new_code == 40)
			{
				$this->add_sub_groups();
				$this->add_income_expense_sub_ledgers();
			}
		}
	}

	function add_sub_groups()
	{
		$CI =& get_instance();
		$CI->db->from('groups')->where('parent_id', $this->id);
		$child_group_q = $CI->db->get();
		$counter = 0;
		foreach ($child_group_q->result() as $row)
		{
			$this->children_groups[$counter] = new Reportlist();
			$this->children_groups[$counter]->init($row->id);
			$this->total = float_ops($this->total, $this->children_groups[$counter]->total, '+');

			$this->total2 = float_ops($this->total2, $this->children_groups[$counter]->total2, '+');
			$counter++;
		}
	}
	function add_balancesheet_sub_ledgers()
	{
		$CI =& get_instance();
		$CI->load->model('Ledger_model');
		$CI->db->from('ledgers')->where('group_id', $this->id);
		$child_ledger_q = $CI->db->get();
		$counter = 0;
		foreach ($child_ledger_q->result() as $row)
		{
			$this->children_ledgers[$counter]['id'] = $row->id;
			$this->children_ledgers[$counter]['code'] = $row->code;
			$this->children_ledgers[$counter]['name'] = $row->name;

			$this->children_ledgers[$counter]['total'] = $CI->Ledger_model->get_balancesheet_ledger_balance($row->id);
			list ($this->children_ledgers[$counter]['opbalance'], $this->children_ledgers[$counter]['optype']) = $CI->Ledger_model->get_op_balance($row->id);
			$this->total = float_ops($this->total, $this->children_ledgers[$counter]['total'], '+');

			$this->children_ledgers[$counter]['total2'] = $CI->Ledger_model->get_balancesheet_old_ledger_balance($row->id);
			list ($this->children_ledgers[$counter]['opbalance'], $this->children_ledgers[$counter]['optype']) = $CI->Ledger_model->get_prev_year_op_balance($row->id);
			$this->total2 = float_ops($this->total2, $this->children_ledgers[$counter]['total2'], '+');
			//$this->cr_total = float_ops($this->cr_total, $CI->Ledger_model->get_cr_total1($row->id), '+');
                        //$this->dr_total = float_ops($this->dr_total, $CI->Ledger_model->get_dr_total1($row->id), '+');

			$counter++;
		}
	}

	function add_income_expense_sub_ledgers()
	{
		$CI =& get_instance();
		$CI->load->model('Ledger_model');
		$CI->db->from('ledgers')->where('group_id', $this->id);
		$child_ledger_q = $CI->db->get();
		$counter = 0;
		foreach ($child_ledger_q->result() as $row)
		{
			$this->children_ledgers[$counter]['id'] = $row->id;
			$this->children_ledgers[$counter]['code'] = $row->code;
			$this->children_ledgers[$counter]['name'] = $row->name;

			$this->children_ledgers[$counter]['total'] = $CI->Ledger_model->get_ledger_balance1($row->id);
			list ($this->children_ledgers[$counter]['opbalance'], $this->children_ledgers[$counter]['optype']) = $CI->Ledger_model->get_op_balance($row->id);
			$this->total = float_ops($this->total, $this->children_ledgers[$counter]['total'], '+');

			$this->children_ledgers[$counter]['total2'] = $CI->Ledger_model->get_old_ledger_balance($row->id);
			list ($this->children_ledgers[$counter]['opbalance'], $this->children_ledgers[$counter]['optype']) = $CI->Ledger_model->get_prev_year_op_balance($row->id);
			$this->total2 = float_ops($this->total2, $this->children_ledgers[$counter]['total2'], '+');

			$counter++;
		}
	}

	/* Display Account list in Balance sheet and Profit and Loss st */
	function account_st_short($c = 0)
	{
		$this->counter = $c;
		if ($this->id != 0)
		{
			echo "<tr class=\"tr-group\">";
			echo "<td class=\"td-group\">";
			echo "&nbsp;" .  $this->name;
			echo "</td>";
			echo "<td align=\"right\">" . convert_amount_dc($this->total) . "</td>";
			echo "<td align=\"right\">" . convert_amount_dc($this->total2) . "</td>";
			echo "</tr>";
		}
		foreach ($this->children_groups as $id => $data)
		{
			$this->counter++;
			$data->account_st_short($this->counter);
			$this->counter--;
		}
		if (count($this->children_ledgers) > 0)
		{
			$this->counter++;
			foreach ($this->children_ledgers as $id => $data)
			{
				echo "<tr class=\"tr-ledger\">";
				echo "<td class=\"td-ledger\">";
				echo "&nbsp;" . anchor('report/ledgerst/' . $data['id'], $data['name'], array('title' => $data['name'] . ' Ledger Statement', 'style' => 'color:#000000'));
				echo "</td>";
				echo "<td align=\"right\">" . convert_amount_dc($data['total']) . "</td>";
				echo "<td align=\"right\">" . convert_amount_dc($data['total2']) . "</td>";
				echo "</tr>";
			}
			$this->counter--;
		}
	}

	/* Display new Balance Sheet*/
        function new_balance_sheet($c =0)
        {
		$check = 0;
                $this->counter = $c;
		if($this->countDigits() == 4 && $this->id != 0 && $this->code > 100){
			foreach($this->children_groups as $id => $data)
                	{
				if($data->countDigits() == 6)
					$check++;
                	}

			echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                       // echo $this->print_space($this->counter);
                        echo "&nbsp;" .  $this->name;
                        echo "</td>";
                        echo "<td class=\"td-group\">";
                        //echo "&nbsp;" .  $this->schedule;
                        //echo "&nbsp;" . $this->counter;
                        
			if($check == 0){
                        	//echo "&nbsp;" . anchor_popup('report/schedule/' . $this->code, $this->counter, array('title' => $this->name, 'style' => 'color:#000000'));
                        	echo "&nbsp;" . anchor_popup('report/schedule/' . $this->code . '/' . $this->counter, $this->counter, array('title' => $this->name, 'style' => 'color:#000000'));
				/* Get Balance of net income/(expenditure) for 'this' ledger head*/
	                        if($c == 2){
					$income = new Reportlist();
			                $income->init(3);
			                $expense = new Reportlist();
			                $expense->init(4);
					$income_total = -$income->total;
			                $old_income_total = -$income->total2;
			                $expense_total = $expense->total;
			                $old_expense_total = $expense->total2;
					$pandl = float_ops($income_total, $expense_total, '-');
				        $old_pandl = float_ops($old_income_total, $old_expense_total, '-');
					if ($pandl != 0 || $old_pandl !=0)
				        {
				                if($pandl > 0)
							$this->total = float_ops($this->total, -$pandl, '+');
						else
							$this->total = float_ops($this->total, $pandl, '+');
						if($old_pandl > 0)
				                        $this->total2 = float_ops($this->total2, -$old_pandl, '+');
						else
				                        $this->total2 = float_ops($this->total2, $old_pandl, '+');
					}
                        	        /*$this->calculate_netpl($this->id);
                	                $net_profit_loss = $this->calculate_netpl($this->id);
        	                        $this->netpl = $this->netpl + $net_profit_loss;*/
	                        }
			}
			/* Add opening balance to the total amount */
                        //$this->calculate_op_balance('new', 'balance_sheet');
                        //$this->calculate_op_balance('old', 'balance_sheet');

                        echo "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc($this->total) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc($this->total2) . "</td>";
                        echo "</tr>";
		}elseif($this->countDigits() == 6 && $this->id != 0 && $this->code > 100){
		
			echo "<tr>";
                        echo "<td class=\"td-group\">";
                       // echo $this->print_space($this->counter);
                        echo "&nbsp;" .  $this->name;
                        echo "</td>";
                        echo "<td class=\"td-group\">";
                        //echo "&nbsp;" .  $this->schedule;
                        //echo "&nbsp;" . $this->counter;
                        /* Get Balance of net income/(expenditure) for 'this' ledger head*/
                        /*if($c == 1){
                                $this->calculate_netpl($data['id']);
                                $net_profit_loss = $this->calculate_netpl($data['id']);
                                $this->netpl = $this->netpl + $net_profit_loss;
                        }*/

                        //echo "&nbsp;" . anchor_popup('report/schedule/' . $this->code, $this->counter, array('title' => $this->name, 'style' => 'color:#000000'));
			echo "&nbsp;" . anchor_popup('report/schedule/' . $this->code . '/' . $this->counter, $this->counter, array('title' => $this->name, 'style' => 'color:#000000'));
				if($c == 2){
                                        $income = new Reportlist();
                                        $income->init(3);
                                        $expense = new Reportlist();
                                        $expense->init(4);
                                        $income_total = -$income->total;
                                        $old_income_total = -$income->total2;
                                        $expense_total = $expense->total;
                                        $old_expense_total = $expense->total2;
                                        $pandl = float_ops($income_total, $expense_total, '-');
                                        $old_pandl = float_ops($old_income_total, $old_expense_total, '-');
                                        if ($pandl != 0 || $old_pandl !=0)
                                        {
                                                if($pandl > 0)
                                                        $this->total = float_ops($this->total, -$pandl, '+');
                                                else
                                                        $this->total = float_ops($this->total, $pandl, '+');
                                                if($old_pandl > 0)
                                                        $this->total2 = float_ops($this->total2, -$old_pandl, '+');
                                                else
                                                        $this->total2 = float_ops($this->total2, $old_pandl, '+');
                                        }
                                        /*$this->calculate_netpl($this->id);
                                        $net_profit_loss = $this->calculate_netpl($this->id);
                                        $this->netpl = $this->netpl + $net_profit_loss;*/
                        	}
			/* Add opening balance to the total amount */
                        //$this->calculate_op_balance('new', 'balance_sheet');
                        //$this->calculate_op_balance('old', 'balance_sheet');

                        echo "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc($this->total) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc($this->total2) . "</td>";
                        echo "</tr>";
		}

		foreach ($this->children_groups as $id => $data)
                {
                        $len = $data->countDigits();
                        if($len == 4){
				if($check == 0)
                                	$this->counter++;
                                $this->counter = $data->new_balance_sheet($this->counter);
                        }elseif($len == 6){
				//$this->counter++;
                                $this->counter = $data->new_balance_sheet($this->counter);
				$this->counter++;
			}
                        //$this->counter--;
                }

                //$this->counter = $this->counter + 1;
                return $this->counter;
        }

        function countDigits()
        {
                //preg_match_all( "/[0-9]/", $str, $arr );
                $search = '1234567890';
                $count = strlen($this->code) - strlen(str_replace(str_split($search), '', $this->code));
                return $count;
        }	

	function calculate_op_balance($year, $name)
	{
		foreach ($this->children_groups as $id => $data)
		{
                        $this->counter++;
                        $data->calculate_op_balance($this->counter, $name);
                        $this->counter--;
                }
                if (count($this->children_ledgers) > 0)
                {
                        //$this->counter++;
                        foreach ($this->children_ledgers as $id => $data)
			{
				//Get opening balance
                                $CI =& get_instance();
                                $CI->load->model('Ledger_model');
				if($year == 'new'){
	                                //list($opBal, $optype) = $CI->Ledger_model->get_prev_year_op_balance($data['id']);
	                                list($opBal, $optype) = $CI->Ledger_model->get_op_balance($data['id']);
        	                        $this->opening_balance = $this->opening_balance + $opBal;
					if($name == 'schedule'){
						if($optype == 'C')
							$this->cr_total = $this->cr_total + $opBal;
						elseif($optype == 'D')
                        	        		$this->dr_total = $this->dr_total + $opBal;
					}else
					{
						if($optype == 'C')
                                                        $this->total = $this->total - $opBal;
                                                elseif($optype == 'D')
                                                        $this->total = $this->total + $opBal;
					}

				}
				elseif($year == 'old'){
				        //list($opBal, $optype) = $CI->Ledger_model->get_prevToPrev_year_op_balance($data['id']);
				        list($opBal, $optype) = $CI->Ledger_model->get_prev_year_op_balance($data['id']);
                                        $this->opening_balance_prev = $this->opening_balance_prev + $opBal;
					if($name == 'schedule'){
	                                        if($optype == 'C')
        	                                        $this->old_cr_total = $this->old_cr_total + $opBal;
                	                        elseif($optype == 'D')
                        	                        $this->old_dr_total = $this->old_dr_total + $opBal;	
					}
					else
					{
						if($optype == 'C')
                                                        $this->total2 = $this->total2 - $opBal;
                                                elseif($optype == 'D')
                                                        $this->total2 = $this->total2 + $opBal;
					}
                                }
			}
		}
	}

	function startsWith($str1, $str2)
        {
                return !strncmp($str1, $str2, strlen($str2));
        }

	function callToSchedule($c = 0){
		$credit_total = 0;
		$debit_total = 0;
		list($credit_total, $debit_total) = $this->schedule($c);
		$this->cr_total = $this->cr_total + $credit_total;
		$this->dr_total = $this->dr_total + $debit_total;
	}

	function callToOldSchedule($c = 0){
                $old_credit_total = 0;
                $old_debit_total = 0;
                list($old_credit_total, $old_debit_total) = $this->previous_year_data($c);
                $this->old_cr_total = $this->old_cr_total + $old_credit_total;
                $this->old_dr_total = $this->old_dr_total + $old_debit_total;
        }

	/* Displays schedule */
        function schedule($c = 0)
        {
		static $credit_total = 0;
		static $debit_total = 0;

                $len = $this->countDigits();
                //if ($this->id != 0  && $len > 4)
		if($this->id != 0  && $len > 6)
                {
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        //echo $this->print_space($this->counter);
                        echo "&nbsp;" . "<b>" . $this->name . "</b>";
                        echo "</td>";

                        echo "</tr>";

                }
                foreach ($this->children_groups as $id => $data)
                {
                        $this->counter++;
                        $data->schedule($this->counter);
                        $this->counter--;
                }
                if (count($this->children_ledgers) > 0)
                {
                        //$this->counter++;
                        foreach ($this->children_ledgers as $id => $data)
                        {
                                /* Get Balance of net income/(expenditure) for 'this' ledger head*/
                                /*if($c == 1){
                                        $this->calculate_netpl($data['id']);
                                        $net_profit_loss = $this->calculate_netpl($data['id']);
                                        $this->netpl = $this->netpl + $net_profit_loss;
                                }*/

				echo "<tr class=\"tr-ledger\">";
	                                echo "<td class=\"td-ledger\">";
        	                                echo $this->print_space($this->counter);
                	                        echo "&nbsp;" . "<b>" . $data['name'] . "<b>";
                                        echo "</td>";
                                echo "</tr>";

                                $CI =& get_instance();
                                $CI->db->select('entry_id, id, amount, dc');
                                $CI->db->from('entry_items')->where('ledger_id', $data['id']);
                                $entry_items_q = $CI->db->get();
                                if($entry_items_q->num_rows() > 0)
                                {
                                        $entry_items_result = $entry_items_q->result();
                                        /*echo "<tr class=\"tr-ledger\">";
                                        echo "<td class=\"td-ledger\">";
                                        echo $this->print_space($this->counter);
                                        echo "&nbsp;" . "<b>" . $data['name'] . "<b>";
                                        echo "</td>";

                                        echo "</tr>";
					*/
                                        //$counter = 0;
                                        foreach ($entry_items_result as $row)
                                        {
                                                if($row->dc == 'C'){
                                                        $CI =& get_instance();
                                                        $CI->db->select('narration');
                                                        $CI->db->from('entries')->where('id', $row->entry_id);
                                                        $entries_q = $CI->db->get();
                                                        //$entries = $entries_q->row();
                                                        foreach($entries_q->result() as $entries){
                                                                $narration = $entries->narration;
								if($this->startsWith($data['code'], '10')){
                                                                echo "<tr class=\"tr-ledger\">";
                                                                echo "<td class=\"td-ledger\">";
                                                                echo $this->print_space($this->counter);
                                                                echo "&nbsp;" . "Add: " . $narration;
                                                                echo "</td>";

                                                                echo "<td>";
                                                                echo "</td>";

                                                                echo "<td align=\"right\">";
                                                                echo convert_amount_dc(-$row->amount);
                                                                //echo $row->amount;
                                                                echo "</td>";

                                                                echo "<td>";
                                                                echo "";
                                                                echo "</td>";

                                                                echo "<td>";
                                                                echo "";
                                                                echo "</td>";

                                                                echo "</tr>";
								}
								else{
								echo "<tr class=\"tr-ledger\">";
                                                                echo "<td class=\"td-ledger\">";
                                                                echo $this->print_space($this->counter);
                                                                echo "&nbsp;" . "Deduct: " . $narration;
                                                                echo "</td>";

                                                                echo "<td>";
                                                                echo "</td>";

                                                                echo "<td align=\"right\">";
                                                                echo convert_amount_dc(-$row->amount);
                                                                //echo $row->amount;
                                                                echo "</td>";

                                                                echo "<td>";
                                                                echo "";
                                                                echo "</td>";

                                                                echo "<td>";
                                                                echo "";
                                                                echo "</td>";

                                                                echo "</tr>";
                                                                }
                                                        }                                                        
							//$this->cr_total = $this->cr_total + $row->amount;
							$credit_total = $credit_total + $row->amount;
                                                }
                                                else{
                                                        $CI =& get_instance();
                                                        $CI->db->select('narration');
                                                        $CI->db->from('entries')->where('id', $row->entry_id);
                                                        $entries_q = $CI->db->get();
                                                        //$entries = $entries_q->row();
                                                        foreach($entries_q->result() as $entries){
                                                                $narration = $entries->narration;
								if($this->startsWith($data['code'], '10')){
                                	                                echo "<tr class=\"tr-ledger\">";
                                        	                        echo "<td class=\"td-ledger\">";
                                                		                echo $this->print_space($this->counter);
                                                                		echo "&nbsp;" . "Deduct: " . $narration;
	                                                                echo "</td>";

        	                                                        echo "<td align=\"right\">";
                        		                                        //echo $row->amount;
                	                	                                echo convert_amount_dc($row->amount);
                                                	                echo "</td>";
	
        	                                                        echo "<td>";
                	                                                echo "</td>";

                        	                                        echo "<td>";
                                		                                echo "";
                                                	                echo "</td>";

                                                        	        echo "<td>";
                                                                		echo "";
	                                                                echo "</td>";

        	                                                        echo "</tr>";
								}
								else{
									echo "<tr class=\"tr-ledger\">";
        	                                                        echo "<td class=\"td-ledger\">";
                	                                                echo $this->print_space($this->counter);
                        	                                        echo "&nbsp;" . "Add: " . $narration;
                                	                                echo "</td>";
	
        	                                                        echo "<td align=\"right\">";
                	                                                //echo $row->amount;
                        	                                        echo convert_amount_dc($row->amount);
                                	                                echo "</td>";
	
        	                                                        echo "<td>";
                	                                                echo "</td>";

                        		                                echo "<td>";
                                                		                echo "";
                                        	                        echo "</td>";

                                                                	echo "<td>";
		                                                                echo "";
                	                                                echo "</td>";

                        	                                        echo "</tr>";
								}
                                                        }
		                                        //$this->dr_total = $this->dr_total + $row->amount;
							$debit_total = $debit_total + $row->amount;                                               
                                                }
						/*$cr_total = $CI->Ledger_model->get_cr_total1($data['id']);
						//$this->cr_total = $this->cr_total + $cr_total;
					        $dr_total = $CI->Ledger_model->get_dr_total1($data['id']);
						//$this->dr_total = $this->dr_total + $dr_total;
						if($this->startsWith($data['code'], '10')){
							echo "rtertrt";
							$temp = $cr_total - $dr_total;
							$this->total = $this->total + $temp;
						}
					        else{
							echo "rtertrt>>>";
							$temp = $dr_total - $cr_total;
					                $this->total = $this->total + $temp;
							echo "<br>";
						}*/
                                        }

                                }
                        }                        //$this->counter--;
                }
                return array($credit_total, $debit_total);
        }

        function previous_year_data($c = 0)
        {
		$narration = '';
		$amount = 0;
		static $old_credit_total = 0;
		static $old_debit_total = 0;

                foreach ($this->children_groups as $id => $data)
                {
                        $this->counter++;
                        //$data->schedule($this->counter);
			$data->previous_year_data($this->counter);
                        $this->counter--;
                }
                if (count($this->children_ledgers) > 0)
                {//1
                        //$this->counter++;
                        foreach ($this->children_ledgers as $id => $data)
                        {//2
				/* Get Balance of net income/(expenditure) for 'this' ledger head*/
                                /*if($c == 1){
                                        $this->calculate_netpl($data['id']);
                                        $net_profit_loss = $this->calculate_netpl($data['id']);
                                        $this->netpl_old = $this->netpl_old + $net_profit_loss;
                                }*/
				
                		if($this->prevYearDB != "" ){//3
                        		/* database connectivity for getting previous year opening balance */
	                        	$con = mysql_connect($this->host_name, $this->db_username, $this->db_password);
	        	                $op_balance = array();
        	        	        if($con){//4
                	        	        $value = mysql_select_db($this->prevYearDB, $con);
                        	        	$id = mysql_real_escape_string($data['id']);
	                        	        $cl = "select entry_id, id, amount, dc from entry_items where ledger_id = '$id'";
        	                        	$val = mysql_query($cl);
	                	                if($val != ''){//5
        	                	                while($row = mysql_fetch_assoc($val))
                	                	        {//6
								if($row != null){//7
	                                                		//if($row->dc == 'C'){
									$con1 = mysql_connect($this->host_name, $this->db_username, $this->db_password);
                                                        		if($con1){//8
		                                                                $value = mysql_select_db($this->prevYearDB, $con1);
                		                                                $id1 = mysql_real_escape_string($row['entry_id']);
                                		                                $cl1 = "select narration from entries where id = '$id1'";
                                                		                $val1 = mysql_query($cl1);
                                                                		if($val1 != ''){//9
		                                                                        while($row1 = mysql_fetch_assoc($val1))
                		                                                        {//10
                                		                                                if($row1 != null){//11
                                                		                                        $narration = $row1['narration'];
	                                               							if($row['dc'] == 'C'){//12
														if($this->startsWith($data['code'], '10')){
						                                                                echo "<tr class=\"tr-ledger\">";
                                                						                echo "<td class=\"td-ledger\">";
						                	                                                echo $this->print_space($this->counter);
                                                							                echo "&nbsp;" . "Add: " . $narration;
							                                                        echo "</td>";

                                                						                echo "<td>";
						                                                                echo "</td>";
							
						                                                                echo "<td>";
														echo "</td>";
						        
								                                                echo "<td>";
                                                	        						        echo "";
						                                                                echo "</td>";

                                                						                echo "<td>";
															echo convert_amount_dc(-$row['amount']);
                                                        						        echo "</td>";

						                                                                echo "</tr>";
														}else{
														echo "<tr class=\"tr-ledger\">";
                                                                                                                echo "<td class=\"td-ledger\">";
                                                                                                                        echo $this->print_space($this->counter);
                                                                                                                        echo "&nbsp;" . "Deduct: " . $narration;
                                                                                                                echo "</td>";

                                                                                                                echo "<td>";
                                                                                                                echo "</td>";

                                                                                                                echo "<td>";
                                                                                                                echo "</td>";

                                                                                                                echo "<td>";
                                                                                                                        echo "";
                                                                                                                echo "</td>";

                                                                                                                echo "<td>";
                                                                                                                        echo convert_amount_dc(-$row['amount']);
                                                                                                                echo "</td>";

                                                                                                                echo "</tr>";
														}
														$old_credit_total = $old_credit_total + $row['amount'];
					                                                		}//12
                                                							else{//13
														if($this->startsWith($data['code'], '10')){
                                                        	                                	        echo "<tr class=\"tr-ledger\">";
                                                                						echo "<td class=\"td-ledger\">";
					                                	                                	echo $this->print_space($this->counter);
	                                        				        		                echo "&nbsp;" . "Deduct: " . $narration;
						                                                                echo "</td>";
				
                        						                                        echo "<td>";
                                	                                                                        echo "</td>";

                                        	                        					echo "<td>";
					        	                                                        echo "</td>";

                                        						                        echo "<td>";
						                	                                                //echo convert_amount_dc($row->amount);
															echo convert_amount_dc($row['amount']);
					                                        	                        echo "</td>";
		
                	                        					                        echo "<td>";
							                                                                echo "";
                                	                					                echo "</td>";

                                        						                        echo "</tr>";
														}else{
														echo "<tr class=\"tr-ledger\">";
                                                                                                                echo "<td class=\"td-ledger\">";
                                                                                                                        echo $this->print_space($this->counter);
                                                                                                                        echo "&nbsp;" . "Add: " . $narration;
                                                                                                                echo "</td>";

                                                                                                                echo "<td>";
                                                                                                                echo "</td>";

                                                                                                                echo "<td>";
                                                                                                                echo "</td>";

                                                                                                                echo "<td>";
                                                                                                                        //echo convert_amount_dc($row->amount);
                                                                                                                        echo convert_amount_dc($row['amount']);
                                                                                                                echo "</td>";

                                                                                                                echo "<td>";
                                                                                                                        echo "";
                                                                                                                echo "</td>";

                                                                                                                echo "</tr>";
														}
                                                        	                                                //$this->old_dr_total = $this->old_dr_total - $row->amount;
														$old_debit_total = $old_debit_total + $row['amount'];
                                                							}//13
												}//11
												mysql_close($con1);
                                                					}//10
                                                				}//9
                                        				}//8
                               					}//7
								// mysql_close($con);
                                                        }//6
							//	mysql_close($con);
                                                }//5
                                        }//4
                                }//3
                                //}
                        }//2
                }//1
		
		return array($old_credit_total, $old_debit_total);
        }//method


	/* Display chart of accounts view */
	function account_st_main($c = 0)
	{
		$this->counter = $c;
		if ($this->id != 0)
		{
			echo "<tr class=\"tr-group\">";
			echo "<td class=\"td-group\">";
			//$this->messages->add('Value of status field==>');
			//echo $this->status;
			//echo "Test";	
			if ($this->id <= 4)
				{echo "&nbsp;<strong>" .  $this->code . "</strong>";
				//echo "&nbsp;<strong>" .  $this->status . "</strong>";
				}
			else
				{echo "&nbsp;" .  $this->code;
				//echo "&nbsp;<strong>" .  $this->status . "</strong>";
				}
			echo "</td>";
			echo "<td class=\"td-group\">";
			echo $this->print_space($this->counter);
			if ($this->id <=4)
				echo "&nbsp;<strong>" .  $this->name. "</strong>";
			else
				echo "&nbsp;" .  $this->name;
			echo "</td>";
			echo "<td>Group Account</td>";
			echo "<td>-</td>";
			echo "<td>-</td>";
			if ($this->id <= 4)
			{
				echo "<td class=\"td-actions\"></tr>";
			} else {
				echo "<td class=\"td-actions\">" . anchor('group/edit/' . $this->id , "Edit", array('title' => 'Edit Group', 'class' => 'red-link'));
				$id1=$this->id;
				$status1=$this->status;
				if (  check_access('administer'))
				{
				if($this->status == 0)				
					echo " &nbsp;" . anchor('group/enabledisable/' . $id1 . "/" .  $status1, 'Hide',  array('title' => 'Edit Group', 'class' => 'red-link')) ;
				else
					echo " &nbsp;" . anchor('group/enabledisable/' . $id1 . "/" .  $status1, 'Unhide', array('title' => 'Edit Group', 'class' => 'red-link')) ;
				}
				echo " &nbsp;" . anchor('group/delete/' . $this->id, img(array('src' => asset_url() . "images/icons/delete.png", 'border' => '0', 'alt' => 'Delete group')), array('class' => "confirmClick", 'title' => "Delete Group")) . "</td>";
			}
			echo "</tr>";
		}
		foreach ($this->children_groups as $id => $data)
		{
			"$this->counter++ ";
			$data->account_st_main($this->counter);
			$this->counter--;
		}
		//} 
		if (count($this->children_ledgers) > 0)
		{
			$this->counter++;
			foreach ($this->children_ledgers as $id => $data)
			{
				echo "<tr class=\"tr-ledger\">";
				echo "<td class=\"td-ledger\">";
				echo "&nbsp;" . anchor('report/ledgerst/' . $data['id'], $data['code'], array('title' => $data['code'] . ' Ledger Statement', 'style' => 'color:#000000'));
				echo "</td>";
				echo "<td class=\"td-ledger\">";
				echo $this->print_space($this->counter);
				echo "&nbsp;" . anchor('report/ledgerst/' . $data['id'], $data['name'], array('title' => $data['name'] . ' Ledger Statement', 'style' => 'color:#000000'));
				echo "</td>";
				echo "<td>Ledger Account</td>";
				echo "<td>" . convert_opening($data['opbalance'], $data['optype']) . "</td>";
				echo "<td>" . convert_amount_dc($data['total']) . "</td>";
				echo "<td class=\"td-actions\">" . anchor('ledger/edit/' . $data['id'], 'Edit', array('title' => "Edit Ledger", 'class' => 'red-link'));
				echo " &nbsp;" . anchor('ledger/delete/' . $data['id'], img(array('src' => asset_url() . "images/icons/delete.png", 'border' => '0', 'alt' => 'Delete Ledger')), array('class' => "confirmClick", 'title' => "Delete Ledger")) . "</td>";
				echo "</tr>";
			}
			$this->counter--;
		}
	}

	function print_space($count)
	{
		$html = "";
		for ($i = 1; $i <= $count; $i++)
		{
			$html .= "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		}
		return $html;
	}
	
	/* Build a array of groups and ledgers */
	function build_array()
	{
		$item = array(
			'id' => $this->id,
			'name' => $this->name,
			'type' => "G",
			'total' => $this->total,
			'child_groups' => array(),
			'child_ledgers' => array(),
			'depth' => self::$temp_max,
		);
		$local_counter = 0;
		if (count($this->children_groups) > 0)
		{
			self::$temp_max++;
			if (self::$temp_max > self::$max_depth)
				self::$max_depth = self::$temp_max;
			foreach ($this->children_groups as $id => $data)
			{
				$item['child_groups'][$local_counter] = $data->build_array();
				$local_counter++;
			}
			self::$temp_max--;
		}
		$local_counter = 0;
		if (count($this->children_ledgers) > 0)
		{
			self::$temp_max++;
			foreach ($this->children_ledgers as $id => $data)
			{
				$item['child_ledgers'][$local_counter] = array(
					'id' => $data['id'],
					'name' => $data['name'],
					'type' => "L",
					'total' => $data['total'],
					'child_groups' => array(),
					'child_ledgers' => array(),
					'depth' => self::$temp_max,
				);
				$local_counter++;
			}
			self::$temp_max--;
		}
		return $item;
	}

	/* Show array of groups and ledgers as created by build_array() method */
	function show_array($data)
	{
		echo "<tr>";
		echo "<td>";
		echo $this->print_space($data['depth']);
		echo $data['depth'] . "-";
		echo $data['id'];
		echo $data['name'];
		echo $data['type'];
		echo $data['total'];
		if ($data['child_ledgers'])
		{
			foreach ($data['child_ledgers'] as $id => $ledger_data)
			{
				$this->show_array($ledger_data);
			}
		}
		if ($data['child_groups'])
		{
			foreach ($data['child_groups'] as $id => $group_data)
			{
				$this->show_array($group_data);
			}
		}
		echo "</td>";
		echo "</tr>";
	}

	function to_csv($data)
	{
		$counter = 0;
		while ($counter < $data['depth'])
		{
			self::$csv_data[self::$csv_row][$counter] = "";
			$counter++;
		}

		self::$csv_data[self::$csv_row][$counter] = $data['name'];
		$counter++;

		while ($counter < self::$max_depth + 3)
		{
			self::$csv_data[self::$csv_row][$counter] = "";
			$counter++;
		}
		self::$csv_data[self::$csv_row][$counter] = $data['type'];
		$counter++;

		if ($data['total'] == 0)
		{
			self::$csv_data[self::$csv_row][$counter] = "";
			$counter++;
			self::$csv_data[self::$csv_row][$counter] = "";
		} else if ($data['total'] < 0) {
			self::$csv_data[self::$csv_row][$counter] = "Cr";
			$counter++;
			self::$csv_data[self::$csv_row][$counter] = -$data['total'];
		} else {
			self::$csv_data[self::$csv_row][$counter] = "Dr";
			$counter++;
			self::$csv_data[self::$csv_row][$counter] = $data['total'];
		}

		if ($data['child_ledgers'])
		{
			foreach ($data['child_ledgers'] as $id => $ledger_data)
			{
				self::$csv_row++;
				$this->to_csv($ledger_data);
			}
		}
		if ($data['child_groups'])
		{
			foreach ($data['child_groups'] as $id => $group_data)
			{
				self::$csv_row++;
				$this->to_csv($group_data);
			}
		}
	}

	public static function get_csv()
	{
		return self::$csv_data;
	}
	
	public static function add_blank_csv()
	{
		self::$csv_row++;
		self::$csv_data[self::$csv_row] = array("", "");
		self::$csv_row++;
		self::$csv_data[self::$csv_row] = array("", "");
		return;
	}
	
	public static function add_row_csv($row = array(""))
	{
		self::$csv_row++;
		self::$csv_data[self::$csv_row] = $row;
		return;
	}

	public static function reset_max_depth()
	{
		self::$max_depth = 0;
		self::$temp_max = 0;
	}

	/*
	 * Return a array of sub ledgers with the object
	 * Used in CF ledgers of type Assets and Liabilities
	*/
	function get_ledger_ids()
	{
		$ledgers = array();
		if (count($this->children_ledgers) > 0)
		{
			foreach ($this->children_ledgers as $id => $data)
			{
				$ledgers[] = $data['id'];
			}
		}
		if (count($this->children_groups) > 0)
		{
			foreach ($this->children_groups as $id => $data)
			{
				foreach ($data->get_ledger_ids() as $row)
					$ledgers[] = $row;
			}
		}
		return $ledgers;
	}
}

