<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Balancesheet
{
	var $id = 0;
	var $name = "";
	var $code = "";
	var $status = 0;
	var $total = 0;
	var $total2 = 0;
	var $optype = "";
	var $opbalance = 0;
	var $opbalance_prev = 0;
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
        var $old_total = 0;
	var $opening_balance = 0;
	var $opening_balance_prev = 0;
	var $sum = 0;
	var $prevYearDB = "";
	var $db_username = "";
	var $db_password = "";
	var $host_name = "";
	var $port = "";

	function Balancesheet()
	{
		return;
	}

	/**
	 * Method for getting previous year's
	 * database name and other details.
	 * @author Priyanka Rawat <rpriyanka12@ymail.com>
	 */
	function getPreviousYearDetails()
        {
		$database = "";

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
			if($group_q->num_rows() > 0){
				$group = $group_q->row();
				$this->id = $group->id;
				$this->name = $group->name;
				$this->code = $group->code;
				$this->status = $group->status;
				//$this->schedule = $group->schedule;
				$this->total = 0;
				$this->total2 = 0;
			}else{
				$this->init_led($id);
			}
		}

		if($this->status==0){
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

	function init_led($led_id){
		$CI =& get_instance();
		$CI->db->from('ledgers')->where('id', $led_id)->limit(1);
                $ledger_q = $CI->db->get();
                if($ledger_q->num_rows() > 0){
  	              $ledger = $ledger_q->row();
                      $this->id = $ledger->id;
                      $this->name = $ledger->name;
                      $this->code = $ledger->code;
                      $this->total = $CI->Ledger_model->get_ledger_balance1($ledger->id);
		      list ($this->opbalance, $this->optype) = $CI->Ledger_model->get_op_balance($ledger->id);
                      $this->total2 = $CI->Ledger_model->get_balancesheet_old_ledger_balance($ledger->id);
		      list ($this->opbalance_prev, $this->optype) = $CI->Ledger_model->get_prev_year_op_balance($ledger->id);	
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
			$this->children_groups[$counter] = new Balancesheet();
			$this->children_groups[$counter]->init($row->id);
			$this->total = float_ops($this->total, $this->children_groups[$counter]->total, '+');
			$this->opbalance = float_ops($this->opbalance, $this->children_groups[$counter]->opbalance, '+');
			$this->total2 = float_ops($this->total2, $this->children_groups[$counter]->total2, '+');
			$this->opbalance_prev = float_ops($this->opbalance_prev, $this->children_groups[$counter]->opbalance_prev, '+');
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
			$this->opbalance = float_ops($this->opbalance, $this->children_ledgers[$counter]['opbalance'], '+');

			$this->children_ledgers[$counter]['total2'] = $CI->Ledger_model->get_balancesheet_old_ledger_balance($row->id);
			list ($this->children_ledgers[$counter]['opbalance_prev'], $this->children_ledgers[$counter]['optype']) = $CI->Ledger_model->get_prev_year_op_balance($row->id);
			$this->total2 = float_ops($this->total2, $this->children_ledgers[$counter]['total2'], '+');
			$this->opbalance_prev = float_ops($this->opbalance_prev, $this->children_ledgers[$counter]['opbalance_prev'], '+');

			$counter++;
		}
	}


	// function calculate_op_balance($year, $name){
	function calculate_op_balance($year){
                $op_balance = 0;
                $old_op_balance = 0;
		$isLedger = false;

                $CI =& get_instance();
                $CI->db->select('name');
                $CI->db->from('ledgers')->where('id', $this->id);
                $ledger_result = $CI->db->get();
                if($ledger_result->num_rows() > 0)
                        $isLedger = true;

                if($isLedger){
			$CI =& get_instance();
                        $CI->load->model('Ledger_model');
                        if($year == 'new'){
	                        list($opBal, $optype) = $CI->Ledger_model->get_op_balance($this->id);
                                $op_balance = $op_balance + $opBal;
                        }elseif($year == 'old'){
                                list($opBal, $optype) = $CI->Ledger_model->get_prev_year_op_balance($this->id);
                                $old_op_balance = $old_op_balance + $opBal;
                        }		
		}else{
	                if($year == null)
        	        {
                	        $op_balance = null;
                        	$old_op_balance = null;
                	}else{
	                       	foreach ($this->children_groups as $id => $data)
        	                {
                 	               $data->calculate_op_balance($year);
                        	}
	                        if (count($this->children_ledgers) > 0)
        	                {
                	                foreach ($this->children_ledgers as $id => $data)
                        	        {
                                	        //Get opening balance
                                        	$CI =& get_instance();
	                                        $CI->load->model('Ledger_model');
        	
		                                if($year == 'new'){
                	                                list($opBal, $optype) = $CI->Ledger_model->get_op_balance($data['id']);
                        	                        $op_balance = $op_balance + $opBal;
                                	        }elseif($year == 'old'){
	                                                list($opBal, $optype) = $CI->Ledger_model->get_prev_year_op_balance($data['id']);
        	                                        $old_op_balance = $old_op_balance + $opBal;
                	                        }
                        	        }
                        	}
	                }//else null
		}

                if($year == 'new')
			return $op_balance;
                elseif($year == 'old')
			return $old_op_balance;
        }

	//function additionsToFunds($fund_id, $type){
	function additionsToFunds($type){
		$sum = 0;
		$isLedger = false;

                $CI =& get_instance();
                $CI->db->select('name, group_id');
                $CI->db->from('ledgers')->where('id', $this->id);
                $ledger_result = $CI->db->get();
                if($ledger_result->num_rows() > 0){
			$ledger = $ledger_result->row();
                        $CI->db->select('name');
                        $CI->db->from('groups')->where('id', $ledger->group_id);
                        $group_result = $CI->db->get();
                        $group = $group_result->row();
                        if($group->name == 'Designated-Earmarked Funds' || $group->name == 'Restricted Funds')
	                        $isLedger = true;
		}

                if($isLedger){
			$CI =& get_instance();
        	        $CI->db->select('amount');
                	$CI->db->from('fund_management')->where('fund_id', $this->id);
	                $CI->db->where('type', $type);
        	        $income_result = $CI->db->get();
                	if($income_result->num_rows() > 0){
                        	//$income = $income_result->row();
				foreach($income_result->result() as $row){
	                        	$sum = $sum + $row->amount;
				}
				//$sum = $income->amount;
        	        }
		}else{
			if(count($this->children_groups) > 0){
				foreach ($this->children_groups as $id => $data)
        	        	{
                	        	//$this->counter++;
	                        	$sum = $sum + $data->additionsToFunds($type);
		                        //$this->counter--;
        		        }
			}

	                if(count($this->children_ledgers) > 0){
        	                //$this->counter++;
                	        foreach ($this->children_ledgers as $id => $data)
                        	{
					$CI =& get_instance();
	                                $CI->db->select('amount');
        	        		$CI->db->from('fund_management')->where('fund_id', $data['id']);
					$CI->db->where('type', $type);
		        	        $income_result = $CI->db->get();
					if($income_result->num_rows() > 0){
						//$income = $income_result->row();
						//$sum = $sum + $income->amount;
						foreach($income_result->result() as $row){
							$sum = $sum + $row->amount;
						}
					}
                	        }
                	}
		}

		return $sum;
	}

	
	function additionsToFundsPrev($type){
                $sum = 0;
		$isLedger = false;

                $this->getPreviousYearDetails();
                if($this->prevYearDB != ""){
			/* database connectivity for getting previous year opening balance */
                        $con = mysql_connect($this->host_name, $this->db_username, $this->db_password);
			if($con){//4
                       		$value = mysql_select_db($this->prevYearDB, $con);
                                
				$id = mysql_real_escape_string($this->id);
				$type = mysql_real_escape_string($type);
				
                                $cl = "select name from ledgers where id = '$id'";
         			$val = mysql_query($cl);
                                if($val != ''){//5
 	                               while($row = mysql_fetch_assoc($val))
                                       {
        	                       		if($row != null)       
							$isLedger = true;			
					}
				}

                	        if($isLedger){
					$cl = "select amount from fund_management where fund_id = '$id' and type = '$type'";
					$val = mysql_query($cl);
                                	if($val != ''){//5
                                       		while($row = mysql_fetch_assoc($val))
                                       		{
                                                	if($row != null)                 
                                                        	$sum = $sum + $row['amount'];
                                        	}
                                	}
                        	}else{
                                	if(count($this->children_groups) > 0){
                                        	foreach ($this->children_groups as $id => $data)
                                        	{
                                                	$sum = $sum + $data->additionsToFundsPrev($type);
                                        	}
                                	}

					if(count($this->children_ledgers) > 0){
                                                foreach ($this->children_ledgers as $id => $data)
        	                                {
							$id = mysql_real_escape_string($data['id']);
							$cl = "select amount from fund_management where fund_id = '$id' and type = '$type'";
							$val = mysql_query($cl);
                                        		if($val != ''){//5
		                                                while($row = mysql_fetch_assoc($val))
                		                                {
                                		                        if($row != null)
                                                		                $sum = $sum + $row['amount'];
                                                		}
                                        		}

                                                }
                                        }
                                }
                        }
                }
                return $sum;
        }

	function additionToFundsDonation(){
                $sum = 0;
		$isLedger = false;
		$CI =& get_instance();
		$CI->db->select('name, group_id');
		$CI->db->from('ledgers')->where('id', $this->id);
		$ledger_result = $CI->db->get();
		if($ledger_result->num_rows() > 0){
			$ledger = $ledger_result->row();
			$CI->db->select('name');
        	        $CI->db->from('groups')->where('id', $ledger->group_id);
	                $group_result = $CI->db->get();
			$group = $group_result->row();
			if($group->name == 'Designated-Earmarked Funds' || $group->name == 'Restricted Funds')
				$isLedger = true;
		}

		if($isLedger){
	                $CI->db->select('id, amount');
        	        $CI->db->from('entry_items')->where('ledger_id', $this->id);
                	$CI->db->where('dc', 'C');
	                $entry_result = $CI->db->get();
        	        if($entry_result->num_rows() > 0){
	        	        //$entry = $entry_result->row();
				foreach($entry_result->result() as $entry){
					$CI->db->from('fund_management');
					$CI->db->where('entry_items_id', $entry->id);
					$income_result = $CI->db->get();
		                        if($income_result->num_rows() < 1)
		                       		$sum = $sum + $entry->amount;
						//$sum = $entry->amount;
				}
	       	        }
		}else{
	                if(count($this->children_groups) > 0){
        	                foreach ($this->children_groups as $id => $data)
                	        {
                        	        //$this->counter++;
                                	$sum = $sum + $data->additionToFundsDonation();
	                                
        	                }
                	}

	                if(count($this->children_ledgers) > 0){
                	        foreach ($this->children_ledgers as $id => $data)
                        	{
                                	$CI =& get_instance();
	                                $CI->db->select('id, amount');
        	                        $CI->db->from('entry_items')->where('ledger_id', $data['id']);
                	                $CI->db->where('dc', 'C');
                        		$entry_result = $CI->db->get();
	                                if($entry_result->num_rows() > 0){
						//$entry = $entry_result->row();
						foreach($entry_result->result() as $entry){
	        	                        	$CI->db->from('fund_management');
	        	        	                $CI->db->where('entry_items_id', $entry->id);
        	                        		$income_result = $CI->db->get();
			                                if($income_result->num_rows() < 1)
                				                $sum = $sum + $entry->amount;     
						}   	                                
	                                }
        	                }
        	        }
		}

                return $sum;	
	}	

	function additionToFundsDonationPrev(){
                $sum = 0;
                $isLedger = false;
		$this->getPreviousYearDetails();

		if($this->prevYearDB != ""){
			/* database connectivity for getting previous year opening balance */
                        $con = mysql_connect($this->host_name, $this->db_username, $this->db_password);

			if($con){//4
	                        $value = mysql_select_db($this->prevYearDB, $con);
                                $id = mysql_real_escape_string($this->id);
                                
				$cl = "select name from ledgers where id = '$id'";
                                $val = mysql_query($cl);
                                if($val != ''){
          	                      while($row = mysql_fetch_assoc($val))
                                      {
                	                      if($row != null)
						$isLedger = true;
					}
				}

				if($isLedger){
					$cl = "select amount from entry_items where ledger_id = '$id' and dc = 'C'";
                        	        $val = mysql_query($cl);
                                	if($val != ''){
	                                      while($row = mysql_fetch_assoc($val))
        	                              {
                	                              if($row != null)
                        	                        $sum = $sum + $row['amount'];
                                	        }
	                                }				
				}else{
	        	                if(count($this->children_groups) > 0){
        	        	                foreach ($this->children_groups as $id => $data)
                	        	        {
                        	              	        $sum = $sum + $data->additionToFundsDonationPrev();
                                                }
        	                	}

	                	        if(count($this->children_ledgers) > 0){
        	                	        foreach ($this->children_ledgers as $id => $data)
                	                	{
							$id = mysql_real_escape_string($data['id']);
                                	        	$cl = "select amount from entry_items where ledger_id = '$id' and dc = 'C'";
                                			$val = mysql_query($cl);
		                                	if($val != ''){
	                		                      while($row = mysql_fetch_assoc($val))
        	                        		      {
                	                              			if($row != null)
				                                                $sum = $sum + $row['amount'];
                        			                }
                                			}
                                		}
                        		}
                		}
			}
		}
                return $sum;
        }

	function startsWith($str1, $str2)
        {
                return !strncmp($str1, $str2, strlen($str2));
        }
	
	/* Display print preview of all schedules*/
        function print_all_schedules($c =0)
        {
		$check = 0;
                $this->counter = $c;
		if($this->countDigits() == 4 && $this->id != 0 && $this->code > 100){
			foreach($this->children_groups as $id => $data)
                	{
				if($data->countDigits() == 6)
					$check++;
                	}

			if($check == 0){
				/* Get Balance of net income/(expenditure) for 'this' ledger head*/
	                        if($c == 2){
					$income = new Balancesheet();
			                $income->init(3);
			                $expense = new Balancesheet();
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
							$this->total = float_ops($this->total, $pandl, '+');
						else
							$this->total = float_ops($this->total, -$pandl, '+');
						if($old_pandl > 0)
				                        $this->total2 = float_ops($this->total2, $old_pandl, '+');
						else
				                        $this->total2 = float_ops($this->total2, -$old_pandl, '+');
					}
	                        }
			}

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
			echo "&nbsp;" . anchor_popup('report/schedule/' . $this->code . '/' . $this->counter, $this->counter, array('title' => $this->name, 'style' => 'color:#000000'));
				if($c == 2){
                                        $income = new Balancesheet();
                                        $income->init(3);
                                        $expense = new Balancesheet();
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
                                                        $this->total = float_ops($this->total, $pandl, '+');
                                                else
                                                        $this->total = float_ops($this->total, -$pandl, '+');
                                                if($old_pandl > 0)
                                                        $this->total2 = float_ops($this->total2, $old_pandl, '+');
                                                else
                                                        $this->total2 = float_ops($this->total2, -$old_pandl, '+');
                                        }
                        	}

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

	function schedule_five(){
		$counter = 1;
		$credit_total = 0.00;
		$debit_total = 0.00;
		$old_credit_total = 0.00;
		$old_debit_total = 0.00;
	
		foreach($this->children_groups as $id => $data){
			$credit_amount = 0.00;
	                $debit_amount = 0.00;
        	        $old_credit_amount = 0.00;
                	$old_debit_amount = 0.00;

			if(($this->name == 'Secured' && $data->name == 'Banks') || ($this->name == 'Secured' && $data->name == 'Financial Institutions') || ($this->name == 'Unsecured' && $data->name == 'Banks')){
				echo "<tr class=\"tr-group\">";
					echo "<td class=\"td-group\">";
						echo $counter;
						$counter++;
						echo "&nbsp;&nbsp;" . $data->name;
					echo "</td>";

					echo "<td></td>";
					echo "<td></td>";
					echo "<td></td>";
					echo "<td></td>";
				echo "</tr>";

/*				foreach($data->children_ledgers as $id => $row){
					echo "<tr class=\"tr-ledger\">";		
						echo "<td class=\"td-ledger\">";
							echo $row['name'];
						echo "</td>";

						echo "<td align=\"right\">";
							echo convert_amount_dc($row['total']);
						echo "</td>";

						echo "<td align=\"right\">";
							echo convert_amount_dc($row['total2']);
						echo "</td>";
				
					echo "</tr>";
				}*/

				if(count($data->children_ledgers) > 0){
				foreach($data->children_ledgers as $id => $row){
					$debit_amount = 0.00;
					$credit_amount = 0.00;
					$old_debit_amount = 0.00;
					$old_credit_amount = 0.00;

	                                $CI =& get_instance();
                                        $CI->db->select('id, amount, dc');
                                        $CI->db->from('entry_items')->where('ledger_id', $row['id']);
                                        $entry_items_q = $CI->db->get();
                                        if($entry_items_q->num_rows() > 0)
                                        {
         	                               $entry_items_result = $entry_items_q->result();
                                               foreach ($entry_items_result as $row1)
                                               {
                	                               if($row1->dc == 'C'){
                        	                               $credit_amount = $credit_amount + $row1->amount;
                                                               $credit_total = $credit_total + $row1->amount;
                                                       }else{
                                                               $debit_amount = $debit_amount + $row1->amount;
                                                               $debit_total = $debit_total + $row1->amount;
                                                       }
                                               }
                                        }

					//Adding opening balance for the ledger head.
					$credit_amount = $credit_amount + $row['opbalance'];
					$credit_total = $credit_total + $row['opbalance'];

                                        $this->getPreviousYearDetails();
                                        if($this->prevYearDB != "" ){//3
                                	        /* database connectivity for getting previous year opening balance */
                                                $con = mysql_connect($this->host_name, $this->db_username, $this->db_password);
                                                $op_balance = array();
                                                if($con){//4
                                        	        $value = mysql_select_db($this->prevYearDB, $con);
                                                        $id = mysql_real_escape_string($row['id']);
                                                        $cl = "select id, amount, dc from entry_items where ledger_id = '$id'";
                                                        $val = mysql_query($cl);
                                                        if($val != ''){//5
                                                	        while($row2 = mysql_fetch_assoc($val))
                                                                {//6
                                                        	        if($row2 != null){//7
                                                                	        if($row2['dc'] == 'C'){//12
                                                                        	        $old_credit_amount = $old_credit_amount + $row2['amount'];
                                                                                        $old_credit_total = $old_credit_total + $row2['amount'];
                                                                                }else{
                                                                                        $old_debit_amount = $old_debit_amount + $row2['amount'];
                                                                                        $old_debit_total = $old_debit_total + $row2['amount'];
                                                                                }
                                                                        }//7
                                                                 }//6
                                                        }//5
                                                 }//4
                                        }//3

					//Adding previous year's opening balance for the ledger head
                                        $old_credit_total = $old_credit_total + $row['opbalance_prev'];
                                        $old_credit_amount = $old_credit_amount + $row['opbalance_prev'];
                               	//}
					 echo "<tr class=\"tr-ledger\">";                
                                                echo "<td class=\"td-ledger\">";
                                                        echo "&nbsp;&nbsp;&nbsp;&nbsp;".$row['name'];
                                                echo "</td>";

		                                echo "<td align=\"right\">";
                		                        echo convert_amount_dc($debit_amount);
                                		echo "</td>";

		                                echo "<td align=\"right\">";
                		                        echo convert_amount_dc(-$credit_amount);
                                		echo "</td>";

		                                echo "<td align=\"right\">";
                		                        echo convert_amount_dc($old_debit_amount);
                                		echo "</td>";

		                                echo "<td align=\"right\">";
                		                        echo convert_amount_dc(-$old_credit_amount);
                                		echo "</td>";
					echo "</tr>";
				}//for
				}//if count
			}else{
				echo "<tr class=\"tr-group\">";
					echo "<td class=\"td-group\">";
						echo $counter;
						$counter++;
						echo "&nbsp;&nbsp;".$data->name;
					echo "</td>";
					
					if(count($data->children_ledgers) > 0){
					foreach($data->children_ledgers as $id => $row){
						$CI =& get_instance();
		                                $CI->db->select('id, amount, dc');
                		                $CI->db->from('entry_items')->where('ledger_id', $row['id']);
                                		$entry_items_q = $CI->db->get();
		                                if($entry_items_q->num_rows() > 0)
                		                {
                                		        $entry_items_result = $entry_items_q->result();
                                        		foreach ($entry_items_result as $row1)
                                        		{
		                                                if($row1->dc == 'C'){
									$credit_amount = $credit_amount + $row1->amount;
									$credit_total = $credit_total + $row1->amount;
								}else{
									$debit_amount = $debit_amount + $row1->amount;
									$debit_total = $debit_total + $row1->amount;
								}
							}
						}

						//Adding opening balance for the ledger head
						$credit_total = $credit_total + $row['opbalance'];
						$credit_amount = $credit_amount + $row['opbalance'];
	
						$this->getPreviousYearDetails();
                                		if($this->prevYearDB != "" ){//3
		                                        /* database connectivity for getting previous year opening balance */
                		                        $con = mysql_connect($this->host_name, $this->db_username, $this->db_password);
                                		        $op_balance = array();
		                                        if($con){//4
                		                                $value = mysql_select_db($this->prevYearDB, $con);
                                		                $id = mysql_real_escape_string($row['id']);
                                                		$cl = "select id, amount, dc from entry_items where ledger_id = '$id'";
		                                                $val = mysql_query($cl);
                		                                if($val != ''){//5
                                		                        while($row2 = mysql_fetch_assoc($val))
                                                		        {//6
                                                                		if($row2 != null){//7
                                                                                	if($row2['dc'] == 'C'){//12
                                                                                        	$old_credit_amount = $old_credit_amount + $row2['amount'];
                                                                                                $old_credit_total = $old_credit_total + $row2['amount'];
                                                                                        }else{
                                                                                                $old_debit_amount = $old_debit_amount + $row2['amount'];
                                                                                                $old_debit_total = $old_debit_total + $row2['amount'];
                                                                                        }
                                                                		}//7
                                                        		}//6
                                                		}//5
                                        		}//4
                                		}//3
						
						//Adding previous year's opening balance for the ledger head
                                                $old_credit_total = $old_credit_total + $row['opbalance_prev'];
                                                $old_credit_amount = $old_credit_amount + $row['opbalance_prev'];
					}//for
					}//if count
						echo "<td align=\"right\">";
							echo convert_amount_dc($debit_amount);
						echo "</td>";

						echo "<td align=\"right\">";
							echo convert_amount_dc(-$credit_amount);
						echo "</td>";

						echo "<td align=\"right\">";
                                        	        echo convert_amount_dc($old_debit_amount);
	                                        echo "</td>";

        	                                echo "<td align=\"right\">";
                	                                echo convert_amount_dc(-$old_credit_amount);
                        	                echo "</td>";
					echo "</tr>";
					//}//for
					//}//if count
			}
		}
		$this->dr_total = $debit_total;
		$this->cr_total = $credit_total;
		$this->old_dr_total = $old_debit_total;
		$this->old_cr_total = $old_credit_total;
	}	

	function current_liabilities($counter){
	
                $credit_total = 0.00;
                $debit_total = 0.00;
                $old_credit_total = 0.00;
                $old_debit_total = 0.00;

		if(($this->name == 'Sundry Creditors') || ($this->name == 'Interest accrued but not due on') || ($this->name == 'Statutory Liabilities') || ($this->name == 'Other current Liabilities')){
                                echo "<tr class=\"tr-group\">";
                                        echo "<td class=\"td-group\">";
                                                echo $counter;
                                                //$counter++;
                                                echo "&nbsp;&nbsp;" . $this->name;
                                        echo "</td>";

                                        echo "<td></td>";
                                        echo "<td></td>";
                                        echo "<td></td>";
                                        echo "<td></td>";
                                echo "</tr>";
		}else{
			echo "<tr class=\"tr-group\">";
                                        echo "<td class=\"td-group\">";
                                                echo $counter;
                                                //$counter++;
                                                echo "&nbsp;&nbsp;" . $this->name;
                                        echo "</td>";
		}

                foreach($this->children_groups as $id => $data){
                        $credit_amount = 0.00;
                        $debit_amount = 0.00;
                        $old_credit_amount = 0.00;
                        $old_debit_amount = 0.00;

				if(count($data->children_ledgers) > 0){
                                foreach($data->children_ledgers as $id => $row){

                                        $CI =& get_instance();
                                        $CI->db->select('id, amount, dc');
                                        $CI->db->from('entry_items')->where('ledger_id', $row['id']);
                                        $entry_items_q = $CI->db->get();
                                        if($entry_items_q->num_rows() > 0)
                                        {
                                               $entry_items_result = $entry_items_q->result();
                                               foreach ($entry_items_result as $row1)
                                               {
                                                       if($row1->dc == 'C'){
                                                               $credit_amount = $credit_amount + $row1->amount;
                                                               $credit_total = $credit_total + $row1->amount;
                                                       }else{
                                                               $debit_amount = $debit_amount + $row1->amount;
                                                               $debit_total = $debit_total + $row1->amount;
                                                       }
                                               }
                                        }

					//Adding opening balance for the ledger head.
                                        $credit_amount = $credit_amount + $row['opbalance'];
                                        $credit_total = $credit_total + $row['opbalance'];

                                        $this->getPreviousYearDetails();
                                        if($this->prevYearDB != "" ){//3
                                                /* database connectivity for getting previous year opening balance */
                                                $con = mysql_connect($this->host_name, $this->db_username, $this->db_password);
                                                $op_balance = array();
                                                if($con){//4
                                                        $value = mysql_select_db($this->prevYearDB, $con);
                                                        $id = mysql_real_escape_string($row['id']);
                                                        $cl = "select id, amount, dc from entry_items where ledger_id = '$id'";
                                                        $val = mysql_query($cl);
                                                        if($val != ''){//5
                                                                while($row2 = mysql_fetch_assoc($val))
                                                                {//6
                                                                        if($row2 != null){//7
                                                                                if($row2['dc'] == 'C'){//12
                                                                                        $old_credit_amount = $old_credit_amount + $row2['amount'];
                                                                                        $old_credit_total = $old_credit_total + $row2['amount'];
                                                                                }else{
                                                                                        $old_debit_amount = $old_debit_amount + $row2['amount'];
                                                                                        $old_debit_total = $old_debit_total + $row2['amount'];
                                                                                }
                                                                        }//7
                                                                 }//6
                                                        }//5
                                                 }//4
					}//3

					//Adding previous year's opening balance for the ledger head
                                        $old_credit_total = $old_credit_total + $row['opbalance_prev'];
                                        $old_credit_amount = $old_credit_amount + $row['opbalance_prev'];

                                }//for
                                }//if count

				if(($this->name == 'Sundry Creditors') || ($this->name == 'Interest accrued but not due on') || ($this->name == 'Statutory Liabilities') || ($this->name == 'Other current Liabilities')){
				echo "<tr class=\"tr-ledger\">";
                                        echo "<td class=\"td-ledger\">";
						if(($data->name == 'Receipts against sponsored projects') || ($data->name == 'Receipts against sponsored fellowships and scholarships') || ($data->name == 'Other Funds'))
							echo "&nbsp;&nbsp;&nbsp;&nbsp;" . anchor_popup('report/sub_schedule/' . $data->id . '/' . $data->name, $data->name, array('title' => $data->name, 'style' => 'color:#000000'));
						else
							echo "&nbsp;&nbsp;&nbsp;&nbsp;".$data->name;
                                        echo "</td>";

					echo "<td align=\"right\">";
		                                echo convert_amount_dc($debit_amount);
                	                echo "</td>";

                        	        echo "<td align=\"right\">";
                                	        echo convert_amount_dc(-$credit_amount);
	                                echo "</td>";

        	                        echo "<td align=\"right\">";
                	                        echo convert_amount_dc($old_debit_amount);
                        	        echo "</td>";

                                	echo "<td align=\"right\">";
                                        	echo convert_amount_dc(-$old_credit_amount);
		                        echo "</td>";
                                echo "</tr>";
				}
                }

			if(count($this->children_ledgers) > 0){
                                foreach($this->children_ledgers as $id => $row){

                                        $CI =& get_instance();
                                        $CI->db->select('id, amount, dc');
                                        $CI->db->from('entry_items')->where('ledger_id', $row['id']);
                                        $entry_items_q = $CI->db->get();
                                        if($entry_items_q->num_rows() > 0)
                                        {
                                               $entry_items_result = $entry_items_q->result();
                                               foreach ($entry_items_result as $row1)
                                               {
                                                       if($row1->dc == 'C'){
                                                               $credit_total = $credit_total + $row1->amount;
                                                       }else{
                                                               $debit_total = $debit_total + $row1->amount;
                                                       }
                                               }
                                        }

                                        //Adding opening balance for the ledger head.
                                        $credit_total = $credit_total + $row['opbalance'];

                                        $this->getPreviousYearDetails();
                                        if($this->prevYearDB != "" ){//3
                                                /* database connectivity for getting previous year opening balance */
                                                $con = mysql_connect($this->host_name, $this->db_username, $this->db_password);
                                                $op_balance = array();
                                                if($con){//4
                                                        $value = mysql_select_db($this->prevYearDB, $con);
                                                        $id = mysql_real_escape_string($row['id']);
                                                        $cl = "select id, amount, dc from entry_items where ledger_id = '$id'";
                                                        $val = mysql_query($cl);
                                                        if($val != ''){//5
                                                                while($row2 = mysql_fetch_assoc($val))
                                                                {//6
                                                                        if($row2 != null){//7
                                                                                if($row2['dc'] == 'C'){//12
                                                                                        $old_credit_total = $old_credit_total + $row2['amount'];
                                                                                }else{
                                                                                        $old_debit_total = $old_debit_total + $row2['amount'];
                                                                                }
                                                                        }//7
                                                                 }//6
                                                        }//5
                                                 }//4
                                        }//3

                                        //Adding previous year's opening balance for the ledger head
                                        $old_credit_total = $old_credit_total + $row['opbalance_prev'];
                                }//for
			}//if count
	
		if(!(($this->name == 'Sundry Creditors') || ($this->name == 'Interest accrued but not due on') || ($this->name == 'Statutory Liabilities') || ($this->name == 'Other current Liabilities'))){
                                        echo "<td align=\"right\">";
                                                echo convert_amount_dc($debit_total);
                                        echo "</td>";

                                        echo "<td align=\"right\">";
                                                echo convert_amount_dc(-$credit_total);
                                        echo "</td>";

                                        echo "<td align=\"right\">";
                                                echo convert_amount_dc($old_debit_total);
                                        echo "</td>";

                                        echo "<td align=\"right\">";
                                                echo convert_amount_dc(-$old_credit_total);
                                        echo "</td>";
                                echo "</tr>";
                }	

                $this->dr_total = $debit_total;
                $this->cr_total = $credit_total;
                $this->old_dr_total = $old_debit_total;
                $this->old_cr_total = $old_credit_total;
	}

	function provisions($counter){
		$credit_total = 0.00;
                $debit_total = 0.00;
                $old_credit_total = 0.00;
                $old_debit_total = 0.00;

		foreach($this->children_groups as $id => $data){
	                if(count($data->children_ledgers) > 0){
        	                foreach($data->children_ledgers as $id => $row){

                                        $CI =& get_instance();
                                        $CI->db->select('id, amount, dc');
                                        $CI->db->from('entry_items')->where('ledger_id', $row['id']);
                                        $entry_items_q = $CI->db->get();
                                        if($entry_items_q->num_rows() > 0)
                                        {
                                               $entry_items_result = $entry_items_q->result();
                                               foreach ($entry_items_result as $row1)
                                               {
                                                       if($row1->dc == 'C'){
                                                               $credit_total = $credit_total + $row1->amount;
                                                       }else{
                                                               $debit_total = $debit_total + $row1->amount;
                                                       }
                                               }
                                        }

                                        //Adding opening balance for the ledger head.
                                        $credit_total = $credit_total + $row['opbalance'];

                                        $this->getPreviousYearDetails();
                                        if($this->prevYearDB != "" ){//3
                                                /* database connectivity for getting previous year opening balance */
                                                $con = mysql_connect($this->host_name, $this->db_username, $this->db_password);
                                                $op_balance = array();
                                                if($con){//4
                                                        $value = mysql_select_db($this->prevYearDB, $con);
                                                        $id = mysql_real_escape_string($row['id']);
                                                        $cl = "select id, amount, dc from entry_items where ledger_id = '$id'";
                                                        $val = mysql_query($cl);
                                                        if($val != ''){//5
                                                                while($row2 = mysql_fetch_assoc($val))
                                                                {//6
                                                                        if($row2 != null){//7
                                                                                if($row2['dc'] == 'C'){//12
                                                                                        $old_credit_total = $old_credit_total + $row2['amount'];
                                                                                }else{
                                                                                        $old_debit_total = $old_debit_total + $row2['amount'];
                                                                                }
                                                                        }//7
                                                                 }//6
                                                        }//5
                                                 }//4
                                        }//3

                                        //Adding previous year's opening balance for the ledger head
                                        $old_credit_total = $old_credit_total + $row['opbalance_prev'];

                                }//for
	                }//if count
		}		

                        if(count($this->children_ledgers) > 0){
                                foreach($this->children_ledgers as $id => $row){

                                        $CI =& get_instance();
                                        $CI->db->select('id, amount, dc');
                                        $CI->db->from('entry_items')->where('ledger_id', $row['id']);
                                        $entry_items_q = $CI->db->get();
                                        if($entry_items_q->num_rows() > 0)
                                        {
                                               $entry_items_result = $entry_items_q->result();
                                               foreach ($entry_items_result as $row1)
                                               {
                                                       if($row1->dc == 'C'){
                                                               $credit_total = $credit_total + $row1->amount;
                                                       }else{
                                                               $debit_total = $debit_total + $row1->amount;
                                                       }
                                               }
                                        }

                                        //Adding opening balance for the ledger head.
                                        $credit_total = $credit_total + $row['opbalance'];

                                        $this->getPreviousYearDetails();
                                        if($this->prevYearDB != "" ){//3
                                                /* database connectivity for getting previous year opening balance */
                                                $con = mysql_connect($this->host_name, $this->db_username, $this->db_password);
                                                $op_balance = array();
                                                if($con){//4
                                                        $value = mysql_select_db($this->prevYearDB, $con);
                                                        $id = mysql_real_escape_string($row['id']);
                                                        $cl = "select id, amount, dc from entry_items where ledger_id = '$id'";
                                                        $val = mysql_query($cl);
                                                        if($val != ''){//5
                                                                while($row2 = mysql_fetch_assoc($val))
                                                                {//6
                                                                        if($row2 != null){//7
                                                                                if($row2['dc'] == 'C'){//12
                                                                                        $old_credit_total = $old_credit_total + $row2['amount'];
                                                                                }else{
                                                                                        $old_debit_total = $old_debit_total + $row2['amount'];
                                                                                }
                                                                        }//7
                                                                 }//6
                                                        }//5
                                                 }//4
                                        }//3

                                        //Adding previous year's opening balance for the ledger head
                                        $old_credit_total = $old_credit_total + $row['opbalance_prev'];

                                }//for
                        }//if count
                

		 echo "<tr class=\"tr-group\">";
	                 echo "<td class=\"td-group\">";
				echo $counter;
                                echo "&nbsp;&nbsp;" . $this->name;
                         echo "</td>";

                         echo "<td align=\"right\">";
                                 echo convert_amount_dc($debit_total);
                         echo "</td>";

                         echo "<td align=\"right\">";
                                 echo convert_amount_dc(-$credit_total);
                         echo "</td>";

                         echo "<td align=\"right\">";
                                 echo convert_amount_dc($old_debit_total);
                         echo "</td>";

                         echo "<td align=\"right\">";
                                 echo convert_amount_dc(-$old_credit_total);
                         echo "</td>";
                echo "</tr>";

		$this->dr_total = $debit_total;
                $this->cr_total = $credit_total;
                $this->old_dr_total = $old_debit_total;
                $this->old_cr_total = $old_credit_total;
	}

	function sub_schedule(){
		$counter = 1;
		$opening_dr = 0.00;
		$closing_dr = 0.00;
		$trans_dr = 0.00;
		$opening_cr = 0.00;
		$closing_cr = 0.00;
		$trans_cr = 0.00;

		if(count($this->children_ledgers) > 0){
		foreach($this->children_ledgers as $id => $row){

			$credit_total = 0.00;
			$debit_total = 0.00;
			$closing_bal = 0.00;

                	$CI =& get_instance();
                        $CI->db->select('id, amount, dc');
                        $CI->db->from('entry_items')->where('ledger_id', $row['id']);
                        $entry_items_q = $CI->db->get();
                        if($entry_items_q->num_rows() > 0)
                        {
                        	$entry_items_result = $entry_items_q->result();
                                foreach ($entry_items_result as $row1)
                                {
                                	if($row1->dc == 'C'){
                                        	$credit_total = $credit_total + $row1->amount;
						$trans_cr = $trans_cr + $row1->amount;
                                        }else{
                                                $debit_total = $debit_total + $row1->amount;
						$trans_dr = $trans_dr + $row1->amount;
                                        }
                                }
                        }

			//Adding opening balance for the ledger head.
                        $closing_bal = $credit_total + $row['opbalance'];
			$opening_cr = $opening_cr + $row['opbalance'];
			$closing_cr = $closing_cr + $closing_bal;
			$closing_dr = $closing_dr + $debit_total;

			echo "<tr class=\"tr-ledger\">";
	                        echo "<td class=\"td-ledger\" colspan=2 width=\"22%\">";
        	                        echo $counter;
					$counter++;
                	                echo ".&nbsp;&nbsp;" . $row['name'];
                        	echo "</td>";

	                        echo "<td align=\"right\" width=\"13%\">";
        	                        echo "0.00";
                	        echo "</td>";

                        	echo "<td align=\"right\" width=\"13%\">";
                                	echo convert_amount_dc(-$row['opbalance']);
                         	echo "</td>";

                         	echo "<td align=\"right\" width=\"13%\">";
                                	echo convert_amount_dc($debit_total);
                         	echo "</td>";

                         	echo "<td align=\"right\" width=\"13%\">";
                                	echo convert_amount_dc(-$credit_total);
                        	echo "</td>";

                         	echo "<td align=\"right\" width=\"13%\">";
                                	echo convert_amount_dc($debit_total);
                        	echo "</td>";

                         	echo "<td align=\"right\" width=\"13%\">";
                                	echo convert_amount_dc(-$closing_bal);
                        	echo "</td>";
	                echo "</tr>";
		}//for
		}//if

		echo "<tr>";
	                echo "<td colspan=2 width=\"22%\">";
				echo "<strong>TOTAL<strong>";
                        echo "</td>";

                        echo "<td align=\"right\" width=\"13%\">";
                                echo "<strong>0.00</strong>";
                        echo "</td>";

                        echo "<td align=\"right\" width=\"13%\">";
                                echo "<strong>".convert_amount_dc(-$opening_cr)."</strong>";
                        echo "</td>";

                        echo "<td align=\"right\" width=\"13%\">";
                                echo "<strong>".convert_amount_dc($trans_dr)."</strong>";
                        echo "</td>";

                        echo "<td align=\"right\" width=\"13%\">";
                                echo "<strong>".convert_amount_dc(-$trans_cr)."</strong>";
                        echo "</td>";

                        echo "<td align=\"right\" width=\"13%\">";
                                echo "<strong>".convert_amount_dc($closing_dr)."</strong>";
                        echo "</td>";

                        echo "<td align=\"right\" width=\"13%\">";
                                echo "<strong>".convert_amount_dc(-$closing_cr)."</strong>";
                        echo "</td>";
                echo "</tr>";
	}//function: sub_schedule

	function fixed_assets($count){
		$counter = 1;
                $net_dr = 0.00;
                $net_opening_bal = 0.00;
                $net_cr = 0.00;
                $net_total = 0.00;
                $net_current_year = 0.00;
                $net_previous_year = 0.00;	
		$dr_total = 0.00;
		$op_total = 0.00;
		$cr_total = 0.00;
		$year_end_value = 0.00;
		$year_end_total = 0.00;
		$current_year_total = 0.00;
		$previous_year_total = 0.00;
		$old_debit_total = 0.00;
		$old_credit_total = 0.00;
		$is_ledger = true;

		if($this->name == 'Land'){
			echo "<tr class=\"tr-group\">";
                                echo "<td class=\"td-group\" width=\"10%\">";
					echo $this->numberToRoman($count).". ".$this->name;
                                echo "</td>";

                                echo "<td align=\"right\" width=\"9%\">";
                                        echo "";
                                echo "</td>";

                                echo "<td align=\"right\" width=\"9%\">";
                                        echo "";
                                echo "</td>";

                                echo "<td align=\"right\" width=\"9%\">";
                                        echo "";
                                echo "</td>";

                                echo "<td align=\"right\" width=\"9%\">";
                                        echo "";
                                echo "</td>";

                                echo "<td align=\"right\" width=\"9%\">";
                                        echo "";
                                echo "</td>";

                                echo "<td align=\"right\" width=\"9%\">";
                                        echo "";
                                echo "</td>";
                                
				echo "<td align=\"right\" width=\"9%\">";
                                        echo "";
                                echo "</td>";

                                echo "<td align=\"right\" width=\"9%\">";
                                        echo "";
                                echo "</td>";

                                echo "<td align=\"right\" width=\"9%\">";
                                        echo "";
                                echo "</td>";

                                echo "<td align=\"right\" width=\"9%\">";
                                        echo "";
                                echo "</td>";
                        echo "</tr>";	
		}

		if(count($this->children_ledgers) > 0){
			$counter = 1;
			$is_ledger = false;

			foreach($this->children_ledgers as $id => $row){
				$debit_amount = 0.00;
				$credit_amount = 0.00;
				$old_debit_amount = 0.00;
				$old_credit_amount = 0.00;

				if($this->name == 'Land'){
					$cr_total = 0.00;
					$dr_total = 0.00;
					$old_debit_total = 0.00;
					$old_credit_total = 0.00;
				}				

				$CI =& get_instance();
                                $CI->db->select('id, amount, dc');
                                $CI->db->from('entry_items')->where('ledger_id', $row['id']);
                                $entry_items_q = $CI->db->get();
                                if($entry_items_q->num_rows() > 0)
                                {
	                                $entry_items_result = $entry_items_q->result();
                                        foreach ($entry_items_result as $row1)
                                        {
        	                                if($row1->dc == 'C'){
							$credit_amount = $credit_amount + $row1->amount;
							$net_cr = $net_cr + $row1->amount;
                                                }else{
							$debit_amount = $debit_amount + $row1->amount;
							$net_dr = $net_dr + $row1->amount;
                                                }
                                        }
                                }

				$cr_total = $cr_total + $credit_amount;
				$dr_total = $dr_total + $debit_amount;

                                //Adding opening balance for the ledger head.
                                $op_total = $op_total + $row['opbalance'];
				$net_opening_bal = $net_opening_bal + $row['opbalance'];
				//$year_end_value = $year_end_value + ($row['opbalance'] + $dr_total) - $cr_total;
				$year_end_value = $year_end_value + ($row['opbalance'] + $debit_amount) - $cr_total;
				$net_total = $net_total + ($row['opbalance'] + $debit_amount) - $cr_total;
				
				/**
				 * Calculate depreciated value of the asset
				 */
				//to be done using pico data
				//till then asset value will not be deducted.
				//So, year_end_total = year_end_value

				$year_end_total = $year_end_value;
				$net_current_year = $net_total;

                                $this->getPreviousYearDetails();
                                if($this->prevYearDB != "" ){//3
                         	       /* database connectivity for getting previous year opening balance */
                                       $con = mysql_connect($this->host_name, $this->db_username, $this->db_password);
                                       $op_balance = array();
                                       if($con){//4
                                 	      $value = mysql_select_db($this->prevYearDB, $con);
                                              $id = mysql_real_escape_string($row['id']);
                                              $cl = "select id, amount, dc from entry_items where ledger_id = '$id'";
                                              $val = mysql_query($cl);
                                              if($val != ''){//5
                                        	      while($row2 = mysql_fetch_assoc($val))
                                                      {//6
                                                	      if($row2 != null){//7
                                                        	      if($row2['dc'] == 'C'){//12
                                                                	      $old_credit_amount = $old_credit_amount + $row2['amount'];
                                                                      }else{
                                                                              $old_debit_amount = $old_debit_amount + $row2['amount'];
                                                                      }
                                                               }//7
                                                       }//6
                                               }//5
                                        }//4
                                }//3		
			
				//Adding previous year's opening balance for the ledger head
                                $previous_year_total = $previous_year_total + ($row['opbalance_prev'] + $old_debit_amount) - $old_credit_amount;	
				$net_previous_year = $net_previous_year + ($row['opbalance_prev'] + $old_debit_amount) - $old_credit_amount;
					
				if($this->name == 'Land'){
					echo "<tr> <class=\"tr-ledger\">";
		                                echo "<td class=\"td-ledger\" width=\"10%\">";
							echo "&nbsp;&nbsp;&nbsp;".$counter.".&nbsp;";
                		                        echo $row['name'];
							$counter++;
                                		echo "</td>";

						echo "<td align=\"right\" width=\"9%\">";
                		                        echo convert_amount_dc($row['opbalance']);
                                		echo "</td>";

		                                echo "<td align=\"right\" width=\"9%\">";
                		                        echo convert_amount_dc($dr_total);
                                		echo "</td>";

		                                echo "<td align=\"right\" width=\"9%\">";
                		                        echo convert_amount_dc(-$cr_total);
                                		echo "</td>";

		                                echo "<td align=\"right\" width=\"9%\">";
                		                        echo convert_amount_dc($row['opbalance'] + $dr_total - $cr_total);
                                		echo "</td>";

		                                echo "<td align=\"right\" width=\"9%\">";
                		                        echo "0.00";
                                		echo "</td>";

		                                echo "<td align=\"right\" width=\"9%\">";
                		                        echo "0.00";
                                		echo "</td>";

		                                echo "<td align=\"right\" width=\"9%\">";
                		                        echo "0.00";
                                		echo "</td>";

		                                echo "<td align=\"right\" width=\"9%\">";
                		                        echo "0.00";
                                		echo "</td>";

		                                echo "<td align=\"right\" width=\"9%\">";
                		                        echo convert_amount_dc($row['opbalance'] + $dr_total - $cr_total);
                                		echo "</td>";

		                                echo "<td align=\"right\" width=\"9%\">";	
                		                        echo convert_amount_dc($row['opbalance_prev'] + $old_debit_amount - $old_credit_amount);
                                		echo "</td>";
		                        echo "</tr>";
				}
	
			}//for child ledgers

		}

		if($is_ledger == true){
		$CI =& get_instance();
		$CI->db->from('ledgers');
		$CI->db->where('id', $this->id);
		$ledger_result = $CI->db->get();

		if($ledger_result->num_rows() > 0){

                                $debit_amount = 0.00;
                                $credit_amount = 0.00;
                                $old_debit_amount = 0.00;
                                $old_credit_amount = 0.00;

                                $CI =& get_instance();
                                $CI->db->select('id, amount, dc');
                                $CI->db->from('entry_items')->where('ledger_id', $this->id);
                                $entry_items_q = $CI->db->get();
                                if($entry_items_q->num_rows() > 0)
                                {
                                        $entry_items_result = $entry_items_q->result();
                                        foreach ($entry_items_result as $row1)
                                        {
                                                if($row1->dc == 'C'){
                                                        $credit_amount = $credit_amount + $row1->amount;
							$net_cr = $net_cr + $row1->amount;
                                                }else{
                                                        $debit_amount = $debit_amount + $row1->amount;
							$net_dr = $net_dr + $row1->amount;
                                                }
                                        }
                                }

                                $cr_total = $cr_total + $credit_amount;
                                $dr_total = $dr_total + $debit_amount;

                                //Adding opening balance for the ledger head.
				//list ($opbalance, $optype) = $CI->Ledger_model->get_op_balance($this->id);
                                $op_total = $op_total + $this->opbalance;
				$net_opening_bal = $net_opening_bal + $this->opbalance;
                                //$year_end_value = $year_end_value + ($row['opbalance'] + $dr_total) - $cr_total;
                                $year_end_value = $year_end_value + ($this->opbalance + $debit_amount) - $cr_total;
				$net_total = $net_total + ($this->opbalance + $debit_amount) - $cr_total;

                                /**
                                 * Calculate depreciated value of the asset
                                 */
                                //to be done using pico data
                                //till then asset value will not be deducted.
                                //So, year_end_total = year_end_value
				
				$year_end_total = $year_end_value;
				$net_current_year = $net_total;

                                $this->getPreviousYearDetails();
                                if($this->prevYearDB != "" ){//3
                                       /* database connectivity for getting previous year opening balance */
                                       $con = mysql_connect($this->host_name, $this->db_username, $this->db_password);
                                       $op_balance = array();
                                       if($con){//4
                                              $value = mysql_select_db($this->prevYearDB, $con);
                                              $id = mysql_real_escape_string($this->id);
                                              $cl = "select id, amount, dc from entry_items where ledger_id = '$id'";
                                              $val = mysql_query($cl);
                                              if($val != ''){//5
                                                      while($row2 = mysql_fetch_assoc($val))
                                                      {//6
                                                              if($row2 != null){//7
                                                                      if($row2['dc'] == 'C'){//12
                                                                              $old_credit_total = $old_credit_total + $row2['amount'];
										$old_credit_amount = $old_credit_amount + $row2['amount'];
                                                                      }else{
                                                                              $old_debit_total = $old_debit_total + $row2['amount'];
										$old_debit_amount = $old_debit_amount + $row2['amount'];
                                                                      }
                                                               }//7
                                                       }//6
                                               }//5
                                        }//4
                                }//3            

                                //Adding previous year's opening balance for the ledger head
				//list ($opbalance_prev, $optype_prev) = $CI->Ledger_model->get_prev_year_op_balance($this->id);
                                $previous_year_total = $previous_year_total + ($this->opbalance_prev + $old_debit_total) - $old_credit_total;
				$net_previous_year = $net_previous_year + ($this->opbalance_prev + $old_debit_total) - $old_credit_total;
		}
		}//if ledger
		
			if(!($this->name == 'Land')){
			echo "<tr class=\"tr-group\">";
                                echo "<td class=\"td-group\" width=\"10%\">";
                                        echo $this->numberToRoman($count).". ".$this->name;
                                echo "</td>";

                                echo "<td align=\"right\" width=\"9%\">";
                                        echo convert_amount_dc($op_total);
                                echo "</td>";

                                echo "<td align=\"right\" width=\"9%\">";
                                        echo convert_amount_dc($dr_total);
                                echo "</td>";

                                echo "<td align=\"right\" width=\"9%\">";
                                        echo convert_amount_dc(-$cr_total);
                                echo "</td>";

                                echo "<td align=\"right\" width=\"9%\">";
                                        echo convert_amount_dc($year_end_value);
                                echo "</td>";

                                echo "<td align=\"right\" width=\"9%\">";
                                        echo "0.00";
                                echo "</td>";

                                echo "<td align=\"right\" width=\"9%\">";
                                        echo "0.00";
                                echo "</td>";

                                echo "<td align=\"right\" width=\"9%\">";
                                        echo "0.00";
                                echo "</td>";

                                echo "<td align=\"right\" width=\"9%\">";
                                        echo "0.00";
                                echo "</td>";

                                echo "<td align=\"right\" width=\"9%\">";
                                        echo convert_amount_dc($year_end_total);
                                echo "</td>";

                                echo "<td align=\"right\" width=\"9%\">";
                                        echo convert_amount_dc($previous_year_total);
                                echo "</td>";
                        echo "</tr>";				
			}// if not 'Land'
	
		return array($net_dr, $net_cr, $net_total, $net_opening_bal, $net_current_year, $net_previous_year);
	}//function: fixed_assets()

	function numberToRoman($num) 
	{
		$n = intval($num);
		$result = '';
 
		$lookup = array('M' => 1000, 'CM' => 900, 'D' => 500, 'CD' => 400,
		     'C' => 100, 'XC' => 90, 'L' => 50, 'XL' => 40,
		     'X' => 10, 'IX' => 9, 'V' => 5, 'IV' => 4, 'I' => 1);
 
		foreach ($lookup as $roman => $value) 
     		{
         		$matches = intval($n / $value);
			$result .= str_repeat($roman, $matches);
			$n = $n % $value;
     		}
		
		return $result;
	}//function: numberToRoman()

	function current_assets_groups($count){
		
		$counter = 1;
		$credit_total = 0;
		$debit_total = 0;
		$old_credit_total = 0;
		$old_debit_total = 0;

		echo "<tr class=\"tr-group\">";
	                echo "<td class=\"td-group\">";
         	               echo $this->numberToRoman($count);
                               echo "&nbsp;&nbsp;" . $this->name;
                        echo "</td>";

                        echo "<td>";
                        echo "</td>";

                        echo "<td>";
                        echo "</td>";

                        echo "<td>";
                        echo "</td>";

                        echo "<td>";
                        echo "</td>";
                echo "</tr>";

		if(count($this->children_ledgers) > 0){
			foreach($this->children_ledgers as $id => $row){
				$credit_amount = 0;
		                $debit_amount = 0;
                		$old_credit_amount = 0;
		                $old_debit_amount = 0;

				$CI =& get_instance();
                                $CI->db->select('id, amount, dc');
                                $CI->db->from('entry_items')->where('ledger_id', $row['id']);
                                $entry_items_q = $CI->db->get();
                                if($entry_items_q->num_rows() > 0)
                                {
                                	$entry_items_result = $entry_items_q->result();
                                        foreach ($entry_items_result as $row1)
                                        {
                                        	if($row1->dc == 'C'){
							$credit_amount = $credit_amount + $row1->amount;
                                                	$credit_total = $credit_total + $row1->amount;
                                                }else{
							$debit_amount = $debit_amount + $row1->amount;
                                                        $debit_total = $debit_total + $row1->amount;
                                                }
                                        }
                                }

                                //Adding opening balance for the ledger head.
                                $debit_total = $debit_total + $row['opbalance'];
				$debit_amount = $debit_amount + $row['opbalance'];

                                $this->getPreviousYearDetails();
                                if($this->prevYearDB != "" ){//3
                                	/* database connectivity for getting previous year opening balance */
                                	$con = mysql_connect($this->host_name, $this->db_username, $this->db_password);
                                        $op_balance = array();
                                        if($con){//4
                                        	$value = mysql_select_db($this->prevYearDB, $con);
                                                $id = mysql_real_escape_string($row['id']);
                                                $cl = "select id, amount, dc from entry_items where ledger_id = '$id'";
                                                $val = mysql_query($cl);
                                                if($val != ''){//5
                                                	while($row2 = mysql_fetch_assoc($val))
                                                        {//6
                                                        	if($row2 != null){//7
                                                                	if($row2['dc'] == 'C'){//12
										$old_credit_amount = $old_credit_amount + $row2['amount'];
                                                                        	$old_credit_total = $old_credit_total + $row2['amount'];
                                                                        }else{
										$old_debit_amount = $old_debit_amount + $row2['amount'];
                                                                                $old_debit_total = $old_debit_total + $row2['amount'];
                                                                        }
                                                                }//7
                                                         }//6
                                                }//5
                                        }//4
                                 }//3
				//Adding previous year's opening balance for the ledger head
                                $old_debit_total = $old_debit_total + $row['opbalance_prev'];
				$old_debit_amount = $old_debit_amount + $row['opbalance_prev'];
		
				echo "<tr class=\"tr-ledger\">";
                        		echo "<td class=\"td-ledger\">";
                                		echo "&nbsp;&nbsp;&nbsp;&nbsp;";
		                                echo $counter. ". " . $row['name'];
						$counter++;
                		         echo "</td>";

		                         echo "<td align=\"right\">";
                		                 echo convert_amount_dc($debit_amount);
		                         echo "</td>";

                		         echo "<td align=\"right\">";
                                		 echo convert_amount_dc(-$credit_amount);
		                         echo "</td>";

                		         echo "<td align=\"right\">";
                                		 echo convert_amount_dc($old_debit_amount);
		                         echo "</td>";

                		         echo "<td align=\"right\">";
                                		 echo convert_amount_dc(-$old_credit_amount);
		                         echo "</td>";
                		echo "</tr>";
			}//for
		}//if

		return array($credit_total, $debit_total, $old_credit_total, $old_debit_total);
	}//function: current_assets_groups()
	
	function current_assets_ledgers($count){
		//$counter = 1;
                $credit_total = 0;
                $debit_total = 0;
                $old_credit_total = 0;
                $old_debit_total = 0;

		$CI =& get_instance();
                $CI->db->select('id, amount, dc');
                $CI->db->from('entry_items')->where('ledger_id', $this->id);
                $entry_items_q = $CI->db->get();

		if($entry_items_q->num_rows() > 0)
                {
  	              $entry_items_result = $entry_items_q->result();
                      foreach ($entry_items_result as $row1)
                      {
          	            if($row1->dc == 'C'){
                	            $credit_total = $credit_total + $row1->amount;
                            }else{
                                    $debit_total = $debit_total + $row1->amount;
                            }
                      }
                }

                //Adding opening balance for the ledger head.
                $debit_total = $debit_total + $this->opbalance;

                $this->getPreviousYearDetails();
                if($this->prevYearDB != "" ){//3
                	/* database connectivity for getting previous year opening balance */
                        $con = mysql_connect($this->host_name, $this->db_username, $this->db_password);
                        $op_balance = array();
                        if($con){//4
                        	$value = mysql_select_db($this->prevYearDB, $con);
                                $id = mysql_real_escape_string($this->id);
                                $cl = "select id, amount, dc from entry_items where ledger_id = '$id'";
                                $val = mysql_query($cl);
                                if($val != ''){//5
                                	while($row2 = mysql_fetch_assoc($val))
                                        {//6
                                        	if($row2 != null){//7
                                                	if($row2['dc'] == 'C'){//12
                                                        	$old_credit_total = $old_credit_total + $row2['amount'];
                                                        }else{
                                                                $old_debit_total = $old_debit_total + $row2['amount'];
                                                        }
                                                }//7
                                         }//6
                                }//5
                        }//4
                }//3
                
	        //Adding previous year's opening balance for the ledger head
                $old_debit_total = $old_debit_total + $this->opbalance_prev;

		echo "<tr class=\"tr-ledger\">";
	                echo "<td class=\"td-ledger\">";
         	               echo $this->numberToRoman($count);
                               echo "&nbsp;&nbsp;" . $this->name;
                        echo "</td>";

                        echo "<td align=\"right\">";
                               echo convert_amount_dc($debit_total);
                        echo "</td>";

                        echo "<td align=\"right\">";
                               echo convert_amount_dc(-$credit_total);
                        echo "</td>";

                        echo "<td align=\"right\">";
                               echo convert_amount_dc($old_debit_total);
                        echo "</td>";

                        echo "<td align=\"right\">";
                               echo convert_amount_dc(-$old_credit_total);
                        echo "</td>";
                echo "</tr>";

		return array($credit_total, $debit_total, $old_credit_total, $old_debit_total);
	}//function: current_assets_ledgers()

	 function loans_advances($count){

                $counter = 1;
                $credit_total = 0;
                $debit_total = 0;
                $old_credit_total = 0;
                $old_debit_total = 0;

                echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                               echo $this->numberToRoman($count);
                               echo "&nbsp;&nbsp;" . $this->name;
                        echo "</td>";

                        echo "<td>";
                        echo "</td>";

                        echo "<td>";
                        echo "</td>";

                        echo "<td>";
                        echo "</td>";

                        echo "<td>";
                        echo "</td>";
                echo "</tr>";

                if(count($this->children_ledgers) > 0){
                        foreach($this->children_ledgers as $id => $row){
                                $credit_amount = 0;
                                $debit_amount = 0;
                                $old_credit_amount = 0;
                                $old_debit_amount = 0;

                                $CI =& get_instance();
                                $CI->db->select('id, amount, dc');
                                $CI->db->from('entry_items')->where('ledger_id', $row['id']);
                                $entry_items_q = $CI->db->get();
                                if($entry_items_q->num_rows() > 0)
                                {
                                        $entry_items_result = $entry_items_q->result();
                                        foreach ($entry_items_result as $row1)
                                        {
                                                if($row1->dc == 'C'){
                                                        $credit_amount = $credit_amount + $row1->amount;
                                                        $credit_total = $credit_total + $row1->amount;
                                                }else{
                                                        $debit_amount = $debit_amount + $row1->amount;
                                                        $debit_total = $debit_total + $row1->amount;
                                                }
                                        }
                                }

                                //Adding opening balance for the ledger head.
                                $debit_total = $debit_total + $row['opbalance'];
                                $debit_amount = $debit_amount + $row['opbalance'];

                                $this->getPreviousYearDetails();
                                if($this->prevYearDB != "" ){//3
                                        /* database connectivity for getting previous year opening balance */
                                        $con = mysql_connect($this->host_name, $this->db_username, $this->db_password);
                                        $op_balance = array();
                                        if($con){//4
                                                $value = mysql_select_db($this->prevYearDB, $con);
                                                $id = mysql_real_escape_string($row['id']);
                                                $cl = "select id, amount, dc from entry_items where ledger_id = '$id'";
                                                $val = mysql_query($cl);
                                                if($val != ''){//5
                                                        while($row2 = mysql_fetch_assoc($val))
                                                        {//6
                                                                if($row2 != null){//7
                                                                        if($row2['dc'] == 'C'){//12
                                                                                $old_credit_amount = $old_credit_amount + $row2['amount'];
                                                                                $old_credit_total = $old_credit_total + $row2['amount'];
                                                                        }else{
                                                                                $old_debit_amount = $old_debit_amount + $row2['amount'];
                                                                                $old_debit_total = $old_debit_total + $row2['amount'];
                                                                        }
                                                                }//7
                                                         }//6
                                                }//5
                                        }//4
                                 }//3
                                //Adding previous year's opening balance for the ledger head
                                $old_debit_total = $old_debit_total + $row['opbalance_prev'];
                                $old_debit_amount = $old_debit_amount + $row['opbalance_prev'];

                                echo "<tr class=\"tr-ledger\">";
                                        echo "<td class=\"td-ledger\">";
                                                echo "&nbsp;&nbsp;&nbsp;&nbsp;";
                                                echo $counter. ". " . $row['name'];
                                                $counter++;
                                         echo "</td>";

                                         echo "<td align=\"right\">";
                                                 echo convert_amount_dc($debit_amount);
                                         echo "</td>";

                                         echo "<td align=\"right\">";
                                                 echo convert_amount_dc(-$credit_amount);
                                         echo "</td>";

                                         echo "<td align=\"right\">";
                                                 echo convert_amount_dc($old_debit_amount);
                                         echo "</td>";

                                         echo "<td align=\"right\">";
                                                 echo convert_amount_dc(-$old_credit_amount);
                                         echo "</td>";
                                echo "</tr>";
                        }//for
                }//if

                return array($credit_total, $debit_total, $old_credit_total, $old_debit_total);
        }//function: loans_advances()

	
}//BalanceSheet.php
?>
