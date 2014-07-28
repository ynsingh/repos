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
/*
		$config['hostname'] = $login->hostname;
		$config['username'] = $login->uname;
		$config['password'] = $login->dbpass;
		$config['database'] = $login->databasename;
		$config['dbdriver'] = "mysql";
		$config['dbprefix'] = "";
		$config['pconnect'] = FALSE;
		$config['db_debug'] = TRUE;
		$config['cache_on'] = FALSE;
		$config['cachedir'] = "";
		$config['char_set'] = "utf8";
		$config['dbcollat'] = "utf8_general_ci";
		$CI =& get_instance();
		$database = $CI->load->database($config, 'TRUE');
		$CI->db->close();		
		}	*/

	//	return $database;
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

	function add_income_expense_sub_ledgers()
        {
                $CI =& get_instance();
                $CI->load->model('Ledger_model');
                $CI->db->from('ledgers')->where('group_id', $this->id);
                $child_ledger_q = $CI->db->get();
                $counter = 0;
                foreach ($child_ledger_q->result() as $row)
                {
                        if($row->name != 'Transfer Account'){
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
        }


	//function callToOpBalance($year, $name){
/*	function callToOpBalance($year){

                if($year == 'new'){
				$this->opening_balance = $this->calculate_op_balance($year);
                }
                elseif($year == 'old'){
				$this->opening_balance_prev = $this->calculate_op_balance($year);
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
*/		

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
                	$CI->db->from('income_from_investment')->where('fund_id', $this->id);
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
        	        		$CI->db->from('income_from_investment')->where('fund_id', $data['id']);
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
					$cl = "select amount from income_from_investment where fund_id = '$id' and type = '$type'";
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
							$cl = "select amount from income_from_investment where fund_id = '$id' and type = '$type'";
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
					$CI->db->from('income_from_investment');
					$CI->db->where('entry_id', $entry->id);
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
	        	                        	$CI->db->from('income_from_investment');
	        	        	                $CI->db->where('entry_id', $entry->id);
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

	
}
?>
