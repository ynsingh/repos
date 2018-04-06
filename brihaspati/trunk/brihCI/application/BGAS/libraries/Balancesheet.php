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

	function __construct()
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
			//	$this->schedule = $group->schedule;
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
	function additionsToFunds($type)
	{
		$sum = 0;
		$isLedger = false;
                $CI =& get_instance();
                $CI->db->select('id,name, group_id');
                $CI->db->from('ledgers')->where('id', $this->id);
                $ledger_result = $CI->db->get();
                if($ledger_result->num_rows() > 0)
		{
			$ledger = $ledger_result->row();
			$ledger_id = $ledger->id;
			$ledger_name = $ledger->name;
                        $CI->db->select('name')->from('groups')->where('id', $ledger->group_id);
                        $group_result = $CI->db->get();
                        $group = $group_result->row();
                        if($group->name == 'Designated-Earmarked Funds' || $group->name == 'Restricted Funds')
	                $isLedger = true;
		}

                if($isLedger)
		{
			$CI =& get_instance();
        	        $CI->db->select('amount');
                	$CI->db->from('fund_management')->where('fund_id', $this->id);
	                $CI->db->where('type', $type);
        	        $income_result = $CI->db->get();
			$income = $income_result->result();
                	if($income_result->num_rows() > 0)
			{
                        	//$income = $income_result->row();
				foreach($income_result->result() as $row)
				{
	                        	$sum = $sum + $row->amount;
				}
				//$sum = $income->amount;
        	        }
		}
		else
		{
		if(count($this->children_groups) > 0)
		{
		foreach ($this->children_groups as $id => $data)
        	{
                //$this->counter++;
	        $sum = $sum + $data->additionsToFunds($type);
		//$this->counter--;
       		}
		}

	        if(count($this->children_ledgers) > 0)
		{
        	//$this->counter++;
               	foreach ($this->children_ledgers as $id => $data)
                {
			$CI =& get_instance();
	                $CI->db->select('amount');
        	        $CI->db->from('fund_management')->where('fund_id', $data['id']);
			$CI->db->where('type', $type);
		        $income_result = $CI->db->get();
			$income = $income_result->result();
			if($income_result->num_rows() > 0)
			{
			//$income = $income_result->row();
			//$sum = $sum + $income->amount;
			foreach($income_result->result() as $row)
			{
			$sum = $sum + $row->amount;
			$total = $sum;
			}
			}

            	}//foreach
                }//if
		}//else
		return $sum;
	}

	
	function additionsToFundsPrev($type){
                $sum = 0;
		$isLedger = false;

                $this->getPreviousYearDetails();
                if($this->prevYearDB != ""){
			/* database connectivity for getting previous year opening balance */
                        $con = mysqli_connect($this->host_name, $this->db_username, $this->db_password);
			if($con){//4
                       		$value = mysqli_select_db($con,$this->prevYearDB);
                                
				$id = mysqli_real_escape_string($this->id);
				$type = mysqli_real_escape_string($type);
				
                                $cl = "select name from ledgers where id = '$id'";
         			$val = mysqli_query($con,$cl);
                                if($val != ''){//5
 	                               while($row = mysqli_fetch_assoc($val))
                                       {
        	                       		if($row != null)       
							$isLedger = true;			
					}
				}

                	        if($isLedger){
					$cl = "select amount from fund_management where fund_id = '$id' and type = '$type'";
					$val = mysqli_query($con,$cl);
                                	if($val != ''){//5
                                       		while($row = mysqli_fetch_assoc($val))
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
							$id = mysqli_real_escape_string($data['id']);
							$cl = "select amount from fund_management where fund_id = '$id' and type = '$type'";
							$val = mysqli_query($con,$cl);
                                        		if($val != ''){//5
		                                                while($row = mysqli_fetch_assoc($val))
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

function additionToFundsDonation()
{
	$sum = 0;
	$isLedger = false;
	$CI =& get_instance();
	$CI->db->select('id,name, group_id');
	$CI->db->from('ledgers')->where('id', $this->id);
	$ledger_result = $CI->db->get();
	if($ledger_result->num_rows() > 0)
	{
		$ledger = $ledger_result->row();
		$ledger_name = $ledger->name;
		$ledger_id = $ledger->id;
		$ledger_groupid = $ledger->group_id; 

		$CI->db->select('name')->from('groups')->where('id', $ledger->group_id);
	        $group_result = $CI->db->get();
		$group = $group_result->row();
		if($group->name == 'Designated-Earmarked Funds') 
		//|| $group->name == 'Restricted Funds')  
		$isLedger = true;
		if($isLedger)
		{
	        	$CI->db->select('id, amount')->from('entry_items')->where('ledger_id', $ledger->id);
                	$CI->db->where('dc', 'C');
	                $entry_result = $CI->db->get();
        	        if($entry_result->num_rows() > 0)
			{
	        	$entry = $entry_result->result();
			foreach($entry  as $entry1)
			{
				$CI->db->from('fund_management');
				$CI->db->where('entry_items_id', $entry1->id);
				$income_result = $CI->db->get();
		                if($income_result->num_rows() < 1)
		                $sum = $sum + $entry1->amount;
				//$sum = $entry->amount;
				
			}
	       	        }//if
		}//ifledger  
		else
		{
	        if(count($this->children_groups) > 0)
		{
        	foreach ($this->children_groups as $id => $data)
          	{
                	//$this->counter++;
                        $sum = $sum + $data->additionToFundsDonation();
        	}
                }//if

	        if(count($this->children_ledgers) > 0)
		{
                foreach ($this->children_ledgers as $id => $data)
                {
			
			$ledger_id = $data['id'];
                        $CI =& get_instance();
	                $CI->db->select('id, amount');
        	        $CI->db->from('entry_items')->where('ledger_id', $data['id']);
                	$CI->db->where('dc', 'C');
                        $entry_result = $CI->db->get();
	                if($entry_result->num_rows() > 0)
			{
				//$entry = $entry_result->row();
				foreach($entry_result->result() as $entry)
				{
	        	        	$CI->db->from('fund_management');
	        	        	$CI->db->where('entry_items_id', $entry->id);
        	                        $income_result = $CI->db->get();
			                if($income_result->num_rows() < 1)
                			$sum = $sum + $entry->amount;    
				}   	                                
	              	}
       		}
		}
		}//else
		}  
                return $sum;	
	}	

	function additionToFundsDonationPrev(){
                $sum = 0;
                $isLedger = false;
		$this->getPreviousYearDetails();

		if($this->prevYearDB != ""){
			/* database connectivity for getting previous year opening balance */
                        $con = mysqli_connect($this->host_name, $this->db_username, $this->db_password);

			if($con){//4
	                        $value = mysqli_select_db($con,$this->prevYearDB);
                                $id = mysqli_real_escape_string($this->id);
                                
				$cl = "select name from ledgers where id = '$id'";
                                $val = mysqli_query($con,$cl);
                                if($val != ''){
          	                      while($row = mysqli_fetch_assoc($val))
                                      {
                	                      if($row != null)
						$isLedger = true;
					}
				}

				if($isLedger){
					$cl = "select amount from entry_items where ledger_id = '$id' and dc = 'C'";
                        	        $val = mysqli_query($con,$cl);
                                	if($val != ''){
	                                      while($row = mysqli_fetch_assoc($val))
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
							$id = mysqli_real_escape_string($data['id']);
                                	        	$cl = "select amount from entry_items where ledger_id = '$id' and dc = 'C'";
                                			$val = mysqli_query($con,$cl);
		                                	if($val != ''){
	                		                      while($row = mysqli_fetch_assoc($val))
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
//////////////////////////////

function schedule_five($id,$count,$code,$type,$database)
{
	$CI = & get_instance();
	$current_active_account = $CI->session->userdata('active_account');
	$counter = 1;
        $sum = "";
        $sum1 = "";
	$total = "";
	$sum2 = "";
	$dr_total1 = "";
	$cr_total1 = "";
        $count1 = "";
        $prev_sum ="";
        $prev_sum1 = "";
        $i = 0;
        $schedulelist2 = "";
        $schedulelist1 = "";
        $schedulelist3 = "";
        $CI->db->from('settings');
        $detail = $CI->db->get();
        foreach ($detail->result() as $row)
        {
        $date1 = $row->fy_start;
        $date2 = $row->fy_end;
        }
        $fy_start=explode("-",$date1);
        $fy_end=explode("-",$date2);
        $curr_year = $fy_start[0] ."-" .$fy_end[0];
        $prev_year = ($fy_start[0]-1) ."-" . ($fy_end[0]-1);
        $CI->load->model('payment_model');
        $db = $CI->payment_model->database_name();
	
	if($id == "12")
	{ 
	$CI->db->select('id,name,code')->from('groups')->where('parent_id',$id);
	$main = $CI->db->get();
	$main_result = $main->result();
	foreach($main_result as $row)
	{
		$group_name = $row->name;
		$group_id = $row->id;
		$code = $row->code;
	if(($type == "view") && ($database == "NULL")) 
	{
		echo "<tr class=\"tr-group\">";
                echo "<td class=\"td-group\">";
                echo $counter;
                $counter++;
                echo "&nbsp;&nbsp;" . $group_name;
		echo "</td>";
		$CI =& get_instance();
                $CI->load->model('investment_model');
		$result = $CI->investment_model->schedule_five1($group_id);
		$value = explode('#',$result); 
                $dr_total1 = $value[0];
                $sum1 = $sum1+$dr_total1;
		$cr_total1 = $value[1];
		$sum2 = $sum2+$cr_total1;  
		$op_balance = $value[2];

		//Adding opening balance for the ledger head.
                 $cr_total1 = $cr_total1 + $op_balance;
                 $sum2 = $sum2 + $op_balance;

		echo "<td align=\"right\">" . convert_amount_dc(+$dr_total1) . "</td>";
                echo "<td align=\"right\">" . convert_amount_dc(-$cr_total1) . "</td>";
	/* code for reading previous year data from xml */
           $acctpath= $this->upload_path1= realpath(BASEPATH.'../uploads/xml');
           $file_name="schedule".$count."-".$current_active_account."-".$prev_year.".xml";
           $tt=$acctpath."/".$file_name;
           if(file_exists($tt))
           {
           $doc = new DomDocument();
           $doc->formatOutput = true;
           $doc->load($tt);
           $xpath = new DomXPath($doc);
           $schedule1 = "schedule".$count;
           $schedule2 = $schedule1."_Name";

           $xpath->query("/".$schedule1."/".$schedule2."/Group_Name");
           $xpath->query("/".$schedule1."/".$schedule2."/Dr_Amount");
           $xpath->query("/".$schedule1."/".$schedule2."/Cr_Amount");
           $xpath->query("/".$schedule1."/".$schedule2."/Group_ID");
           $schedulenode1 = $xpath->query("/".$schedule1."/".$schedule2."/Group_Name");
           $schedulenode2 = $xpath->query("/".$schedule1."/".$schedule2."/Dr_Amount");
           $schedulenode3 = $xpath->query("/".$schedule1."/".$schedule2."/Cr_Amount");
           $schedulenode4 = $xpath->query("/".$schedule1."/".$schedule2."/Group_ID");
           $schedulelist1 = @$schedulenode1->item($i)->nodeValue;
           $schedulelist2 = @$schedulenode2->item($i)->nodeValue;
           $schedulelist3 = @$schedulenode3->item($i)->nodeValue;
           $schedulelist4 = @$schedulenode4->item($i)->nodeValue;
           }
           $prev_sum = $prev_sum+$schedulelist2;
           $prev_sum1 = $prev_sum1+$schedulelist3;
           $i++;
           if($schedulelist2 == 0)
           echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
           else
           echo "<td align=\"right\">" . convert_amount_dc($schedulelist2) . "</td>";
           if($schedulelist3 == 0)
           echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
           else
           echo "<td align=\"right\">" . convert_amount_dc(-$schedulelist3) . "</td>";
	}//ifview 
 
	if(($type == "CF") && ($database != "NULL"))
	{
		$t_name = "schedule".$count;
		$CI = & get_instance();
                $CI->load->model('investment_model');
                $result = $CI->investment_model->schedule_five1($group_id);
                $value = explode('#',$result);
                $dr_total1 = $value[0];
                $cr_total1 = $value[1];

		$data = $this->xml_creation($t_name,$group_id,$database,$group_name,$curr_year,$dr_total1,$cr_total1);		
	}
	}//foreach
    }//id=12
	elseif($id == "13")
	{
	$i = 7;
	$CI->db->select('id,name,code')->from('groups')->where('parent_id',$id);
        $main = $CI->db->get();
        $main_result = $main->result();
        foreach($main_result as $row)
        {
        	$group_name = $row->name;
                $group_id = $row->id;
                $code = $row->code;
        if(($type == "view") && ($database == "NULL"))
        {
                echo "<tr class=\"tr-group\">";
                echo "<td class=\"td-group\">";
                echo $counter;
                $counter++;
                echo "&nbsp;&nbsp;" . $group_name;
                echo "</td>";
                $CI =& get_instance();
                $CI->load->model('investment_model');
                $result = $CI->investment_model->schedule_five1($group_id);
                $value = explode('#',$result);
                $dr_total1 = $value[0];
                $sum1 = $sum1+$dr_total1;
                $cr_total1 = $value[1];
                $sum2 = $sum2+$cr_total1;
		$op_balance = $value[2];
		
		//Adding opening balance for the ledger head.
                 $cr_total1 = $cr_total1 + $op_balance;
                 $sum2 = $sum2 + $op_balance;

                echo "<td align=\"right\">" . convert_amount_dc(+$dr_total1) . "</td>";
                echo "<td align=\"right\">" . convert_amount_dc(-$cr_total1) . "</td>";

	/* code for reading previous year data from xml */
           $acctpath= $this->upload_path1= realpath(BASEPATH.'../uploads/xml');
           $file_name="schedule".$count."-".$current_active_account."-".$prev_year.".xml";
           $tt=$acctpath."/".$file_name;
           if(file_exists($tt))
           {
           $doc = new DomDocument();
           $doc->formatOutput = true;
           $doc->load($tt);
           $xpath = new DomXPath($doc);
           $schedule1 = "schedule".$count;
           $schedule2 = $schedule1."_Name";

           $xpath->query("/".$schedule1."/".$schedule2."/Group_Name");
           $xpath->query("/".$schedule1."/".$schedule2."/Dr_Amount");
           $xpath->query("/".$schedule1."/".$schedule2."/Cr_Amount");
           $xpath->query("/".$schedule1."/".$schedule2."/Group_ID");
           $schedulenode1 = $xpath->query("/".$schedule1."/".$schedule2."/Group_Name");
           $schedulenode2 = $xpath->query("/".$schedule1."/".$schedule2."/Dr_Amount");
           $schedulenode3 = $xpath->query("/".$schedule1."/".$schedule2."/Cr_Amount");
           $schedulenode4 = $xpath->query("/".$schedule1."/".$schedule2."/Group_ID");
           $schedulelist1 = @$schedulenode1->item($i)->nodeValue;
           $schedulelist2 = @$schedulenode2->item($i)->nodeValue;
           $schedulelist3 = @$schedulenode3->item($i)->nodeValue;
           $schedulelist4 = @$schedulenode4->item($i)->nodeValue;
           }
           $prev_sum = $prev_sum+$schedulelist2;
           $prev_sum1 = $prev_sum1+$schedulelist3;
           $i++;
           if($schedulelist2 == 0)
           echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
           else
           echo "<td align=\"right\">" . convert_amount_dc($schedulelist2) . "</td>";
           if($schedulelist3 == 0)
           echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
           else
           echo "<td align=\"right\">" . convert_amount_dc(-$schedulelist3) . "</td>";
	}//ifview
	if(($type == "CF") && ($database != "NULL"))
        {
                $t_name = "schedule".$count;
                $CI = & get_instance();
                $CI->load->model('investment_model');
                $result = $CI->investment_model->schedule_five1($group_id);
                $value = explode('#',$result);
                $dr_total1 = $value[0];
                $cr_total1 = $value[1];
                $data = $this->xml_creation($t_name,$group_id,$database,$group_name,$curr_year,$dr_total1,$cr_total1);
        }

  }//foreach

}//if id=13  
	$this->dr_total1 = $sum1;
	$this->cr_total1 = $sum2;
	$this->prev_sum = $prev_sum;
	$this->prev_sum1 = $prev_sum1;
}//main

///////////////////////////////////////////////////////

/*	function schedule_five(){
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
						print_r($data_name);
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
				}

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
                                 /*               $con = mysql_connect($this->host_name, $this->db_username, $this->db_password);
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
                		   /*                     $con = mysql_connect($this->host_name, $this->db_username, $this->db_password);
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
	}	*/

////////////////////////////////////@kanchan

function current_liabilities($id,$count,$code,$type,$database)
{
	$CI = & get_instance();
	$current_active_account = $CI->session->userdata('active_account');
        $counter = 1;
        $sum = 0;
        $sum1 = 0;
        $total = 0;
        $sum2 = 0;
        $dr_total = 0;
        $cr_total = 0;
	$dr_amount = 0.00;
	$cr_amount = 0.00;
        $count1 = 0;
        $prev_sum =0;
        $prev_sum1 = 0;
        $i = 0;
        $schedulelist2 = "";
        $schedulelist1 = "";
        $schedulelist3 = "";
        $CI->db->from('settings');
        $detail = $CI->db->get();
        foreach ($detail->result() as $row)
        {
        $date1 = $row->fy_start;
        $date2 = $row->fy_end;
        }
        $fy_start=explode("-",$date1);
        $fy_end=explode("-",$date2);
        $curr_year = $fy_start[0] ."-" .$fy_end[0];
        $prev_year = ($fy_start[0]-1) ."-" . ($fy_end[0]-1);
        $CI->load->model('payment_model');
        $db = $CI->payment_model->database_name();
	
	$CI->db->select('id,name,code')->from('groups')->where('parent_id',$id);
	$main1 = $CI->db->get();
	$main1_result = $main1->result();
	foreach($main1_result as $row)
	{
		$group_id = $row->id;
		$group_name = $row->name;
		if(($group_id != '50') && ($group_id != '51') && ($group_id != '53'))
                {
			echo "<tr class=\"tr-group\">";
                	echo "<td class=\"td-group\">";
                	echo $counter;
                	$counter++;
                	echo "&nbsp;&nbsp;" . $group_name;
                	echo "</td>"; 
			if(($type == 'view') && ($database == 'NULL'))
			{
			$CI = & get_instance();
                	$CI->load->model('investment_model');
                	$result01 = $CI->investment_model->current_liabilities1($group_id);
                	$value1 = explode('#', $result01);
                	$dr_total = $value1[0];
			$sum = $sum + $dr_total;
                	$cr_total = $value1[1];
			$sum1 = $sum1 + $cr_total;
			$op_balance = $value1[2];
		
			//Adding opening balance for the ledger head.
                        $cr_total = $cr_total + $op_balance;
			$sum1 = $sum1 + $op_balance;

			echo "<td align=\"right\">" . convert_amount_dc(+$dr_total) . "</td>";
                	echo "<td align=\"right\">" . convert_amount_dc(-$cr_total) . "</td>";


		/* code for reading previous year data from xml */
        	   	$acctpath= $this->upload_path1= realpath(BASEPATH.'../uploads/xml');
           		$file_name="schedule".$count."-".$current_active_account."-".$prev_year.".xml";
           		$tt=$acctpath."/".$file_name;
           		if(file_exists($tt))
           		{
           		$doc = new DomDocument();
           		$doc->formatOutput = true;
           		$doc->load($tt);
           		$xpath = new DomXPath($doc);
           		$schedule1 = "schedule".$count;
           		$schedule2 = $schedule1."_Name";

           		$xpath->query("/".$schedule1."/".$schedule2."/Group_Name");
           		$xpath->query("/".$schedule1."/".$schedule2."/Dr_Amount");
           		$xpath->query("/".$schedule1."/".$schedule2."/Cr_Amount");
           		$xpath->query("/".$schedule1."/".$schedule2."/Group_ID");
           		$schedulenode1 = $xpath->query("/".$schedule1."/".$schedule2."/Group_Name");
           		$schedulenode2 = $xpath->query("/".$schedule1."/".$schedule2."/Dr_Amount");
           		$schedulenode3 = $xpath->query("/".$schedule1."/".$schedule2."/Cr_Amount");
           		$schedulenode4 = $xpath->query("/".$schedule1."/".$schedule2."/Group_ID");
           		$schedulelist1 = @$schedulenode1->item($i)->nodeValue;
           		$schedulelist2 = @$schedulenode2->item($i)->nodeValue;
           		$schedulelist3 = @$schedulenode3->item($i)->nodeValue;
           		$schedulelist4 = @$schedulenode4->item($i)->nodeValue;
           		}
           		$prev_sum = $prev_sum+$schedulelist2;
           		$prev_sum1 = $prev_sum1+$schedulelist3;
           		$i++;
           		if($schedulelist2 == 0)
           		echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
           		else
           		echo "<td align=\"right\">" . convert_amount_dc($schedulelist2) . "</td>";
           		if($schedulelist3 == 0)
           		echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
           		else
           		echo "<td align=\"right\">" . convert_amount_dc(-$schedulelist3) . "</td>";
			echo "</tr>";
        		}//ifview

		if(($type == "CF") && ($database != "NULL"))
                {
                	$t_name = "schedule".$count;
                        $CI = & get_instance();
                        $CI->load->model('investment_model');
                        $result01 = $CI->investment_model->current_liabilities1($group_id);
                        $value1 = explode('#',$result01);
                        $dr_total = $value1[0];
                        $cr_total = $value1[1];
                        $data = $this->xml_creation($t_name,$group_id,$database,$group_name,$curr_year,$dr_total,$cr_total);
               }
		}//if
		else
		{
		if(($group_name == 'Sundry Creditors') || ($group_name == 'Interest accrued but not due on'))
		{
		echo "<tr class=\"tr-group\">";
                echo "<td class=\"td-group\">";
                echo $counter;
                $counter++;
                echo "&nbsp;&nbsp;" . $group_name;
                echo "</td>";
		echo "<td></td>";
                echo "<td></td>";
                echo "<td></td>";
                echo "<td></td>";
                echo "</tr>";
		$CI = & get_instance();
		$CI->db->select('id,name,code')->from('groups')->where('parent_id',$group_id);
		$group_q = $CI->db->get();
		$group_result = $group_q->result();
		foreach($group_result as $data)
		{
			$children_groupid = $data->id;
			$children_groupname = $data->name;
			$CI = & get_instance();
                	$CI->load->model('investment_model');
                	$result02 = $CI->investment_model->current_liabilitieledg($children_groupid);
                	$value02 = explode('#', $result02);
                	$dr_total = $value02[0];
                	$sum = $sum + $dr_total;
                	$cr_total = $value02[1];
                	$sum1 = $sum1 + $cr_total;
			$op_balance = $value02[2];

                        echo "<tr class=\"tr-ledger\">";
                        echo "<td class=\"td-ledger\">";
                        if(($data->name == 'Receipts against sponsored projects') || ($data->name == 'Receipts against sponsored fellowships and scholarships') || ($data->name == 'Other Funds'))
                        echo "&nbsp;&nbsp;&nbsp;&nbsp;" . anchor_popup('report/sub_schedule/' . $data->id . '/' . $data->name, $data->name, array('title' => $data->name, 'style' => 'color:#000000'));
                        else
                        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp" . $data->name;
                        echo "</td>";
			if(($type == 'view') && ($database == 'NULL'))
			{
			//Adding opening balance for the ledger head.
                         $cr_total = $cr_total + $op_balance;
			 $sum1 = $sum1 + $op_balance;

			echo "<td align=\"right\">" . convert_amount_dc(+$dr_total) . "</td>";
	                echo "<td align=\"right\">" . convert_amount_dc(-$cr_total) . "</td>";

		/* code for reading previous year data from xml */
           		$acctpath= $this->upload_path1= realpath(BASEPATH.'../uploads/xml');
           		$file_name="schedule".$count."-".$current_active_account."-".$prev_year.".xml";
           		$tt=$acctpath."/".$file_name;
           		if(file_exists($tt))
           		{
           		$doc = new DomDocument();
           		$doc->formatOutput = true;
           		$doc->load($tt);
           		$xpath = new DomXPath($doc);
           		$schedule1 = "schedule".$count;
           		$schedule2 = $schedule1."_Name";

           		$xpath->query("/".$schedule1."/".$schedule2."/Group_Name");
           		$xpath->query("/".$schedule1."/".$schedule2."/Dr_Amount");
           		$xpath->query("/".$schedule1."/".$schedule2."/Cr_Amount");
           		$xpath->query("/".$schedule1."/".$schedule2."/Group_ID");
           		$schedulenode1 = $xpath->query("/".$schedule1."/".$schedule2."/Group_Name");
           		$schedulenode2 = $xpath->query("/".$schedule1."/".$schedule2."/Dr_Amount");
           		$schedulenode3 = $xpath->query("/".$schedule1."/".$schedule2."/Cr_Amount");
           		$schedulenode4 = $xpath->query("/".$schedule1."/".$schedule2."/Group_ID");
           		$schedulelist1 = @$schedulenode1->item($i)->nodeValue;
           		$schedulelist2 = @$schedulenode2->item($i)->nodeValue;
           		$schedulelist3 = @$schedulenode3->item($i)->nodeValue;
           		$schedulelist4 = @$schedulenode4->item($i)->nodeValue;
           		}
           		$prev_sum = $prev_sum+$schedulelist2;
           		$prev_sum1 = $prev_sum1+$schedulelist3;
           		$i++;
           		if($schedulelist2 == 0)
           		echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
           		else
           		echo "<td align=\"right\">" . convert_amount_dc($schedulelist2) . "</td>";
           		if($schedulelist3 == 0)
           		echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
           		else
           		echo "<td align=\"right\">" . convert_amount_dc(-$schedulelist3) . "</td>";
			echo "</tr>";
        		}//ifview

			if(($type == "CF") && ($database != "NULL"))
                	{
                        $t_name = "schedule".$count;
                        $CI = & get_instance();
                	$CI->load->model('investment_model');
                	$result02 = $CI->investment_model->current_liabilitieledg($children_groupid);
                	$value02 = explode('#', $result02);
                	$dr_total = $value02[0];
                	$cr_total = $value02[1];
                        $data = $this->xml_creation($t_name,$children_groupid,$database,$children_groupname,$curr_year,$dr_total,$cr_total);
               		}
		}//foreach
		}//if
		}
		if($group_id == '53')
                {
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo $counter;
                        $counter++;
                        echo "&nbsp;&nbsp;" . $group_name;
                        echo "</td>";
		if(($type == 'view') && ($database == 'NULL'))
		{
			$CI = & get_instance();
			$CI->load->model('investment_model');
			$result_q = $CI->investment_model->current_liabgroup($group_id);
			$value_q = explode('#', $result_q);
			$dr_total = $value_q[0];
			$sum = $sum+$dr_total;
			$cr_total = $value_q[1];
			$sum1 = $sum1+$cr_total;
			$liability = new Balancesheet();
			$liability->init($group_id);
			$op_balance = $liability->opbalance;

			//Adding opening balance for the ledger head.
                         $cr_total = $cr_total + $op_balance;
			 $sum1 = $sum1 + $op_balance;

                        echo "<td align=\"right\">" . convert_amount_dc(+$dr_total) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(-$cr_total) . "</td>";

			 /* code for reading previous year data from xml */
           		$acctpath= $this->upload_path1= realpath(BASEPATH.'../uploads/xml');
           		$file_name="schedule".$count."-".$current_active_account."-".$prev_year.".xml";
           		$tt=$acctpath."/".$file_name;
           		if(file_exists($tt))
           		{
           		$doc = new DomDocument();
           		$doc->formatOutput = true;
           		$doc->load($tt);
           		$xpath = new DomXPath($doc);
           		$schedule1 = "schedule".$count;
           		$schedule2 = $schedule1."_Name";

           		$xpath->query("/".$schedule1."/".$schedule2."/Group_Name");
           		$xpath->query("/".$schedule1."/".$schedule2."/Dr_Amount");
           		$xpath->query("/".$schedule1."/".$schedule2."/Cr_Amount");
           		$xpath->query("/".$schedule1."/".$schedule2."/Group_ID");
           		$schedulenode1 = $xpath->query("/".$schedule1."/".$schedule2."/Group_Name");
           		$schedulenode2 = $xpath->query("/".$schedule1."/".$schedule2."/Dr_Amount");
           		$schedulenode3 = $xpath->query("/".$schedule1."/".$schedule2."/Cr_Amount");
           		$schedulenode4 = $xpath->query("/".$schedule1."/".$schedule2."/Group_ID");
           		$schedulelist1 = @$schedulenode1->item($i)->nodeValue;
           		$schedulelist2 = @$schedulenode2->item($i)->nodeValue;
           		$schedulelist3 = @$schedulenode3->item($i)->nodeValue;
           		$schedulelist4 = @$schedulenode4->item($i)->nodeValue;
           		}
           		$prev_sum = $prev_sum+$schedulelist2;
           		$prev_sum1 = $prev_sum1+$schedulelist3;
           		$i++;
           		if($schedulelist2 == 0)
           		echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
           		else
           		echo "<td align=\"right\">" . convert_amount_dc($schedulelist2) . "</td>";
           		if($schedulelist3 == 0)
           		echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
           		else
           		echo "<td align=\"right\">" . convert_amount_dc(-$schedulelist3) . "</td>";
        		}//ifview

			echo "</tr>";
		if(($type == "CF") && ($database != "NULL"))
	        {
                        $t_name = "schedule".$count;
                        $CI = & get_instance();
                        $CI->load->model('investment_model');
                        $result_q = $CI->investment_model->current_liabgroup($group_id);
                        $value_q = explode('#',$result_q);
                        $dr_total = $value_q[0];
                        $cr_total = $value_q[1];
                        $data = $this->xml_creation($t_name,$group_id,$database,$group_name,$curr_year,$dr_total,$cr_total);
               	}
              }//if
	}//foreach
	$this->dr_total1 = $sum;
	$this->cr_total1 = $sum1;
	$this->old_dr_total1 = $prev_sum;
	$this->old_cr_total1 = $prev_sum1;
}

function provisions($id,$count,$code,$type,$database)
{
	$CI = & get_instance();
	$current_active_account = $CI->session->userdata('active_account');
        $counter = 1;
        $sum = 0;
        $sum1 = 0;
        $total = 0;
        $sum2 = 0;
        $dr_total = 0;
        $cr_total = 0;
        $count1 = 0;
        $prev_sum =0;
        $prev_sum1 = 0;
        $i = 9;
        $schedulelist2 = "";
        $schedulelist1 = "";
        $schedulelist3 = "";
        $CI->db->from('settings');
        $detail = $CI->db->get();
        foreach ($detail->result() as $row)
        {
        $date1 = $row->fy_start;
        $date2 = $row->fy_end;
        }
        $fy_start=explode("-",$date1);
        $fy_end=explode("-",$date2);
        $curr_year = $fy_start[0] ."-" .$fy_end[0];
        $prev_year = ($fy_start[0]-1) ."-" . ($fy_end[0]-1);
	$CI->load->model('payment_model');
        $db = $CI->payment_model->database_name();

	$CI->db->select('id,name,code')->from('groups')->where('parent_id',$id);
	$main_result = $CI->db->get();
	$main_q = $main_result->result();
	foreach($main_q as $row)
	{
		$group_id = $row->id;
		$group_name = $row->name;
	if(($type == 'view') && ($database == 'NULL'))
        {
		echo "<tr class=\"tr-group\">";
                echo "<td class=\"td-group\">";
                echo $counter;
                $counter++;
                echo "&nbsp;&nbsp;" . $group_name;
                echo "</td>";
		$CI = & get_instance();
                $CI->load->model('investment_model');
                $result_a = $CI->investment_model->provision1($group_id);
		$value_a = explode('#', $result_a);
                $dr_total = $value_a[0];
                $sum = $sum + $dr_total;
                $cr_total = $value_a[1];
                $sum1 = $sum1 + $cr_total;
		$op_balance = $value_a[2];

		//Adding opening balance for the ledger head.
                $cr_total = $cr_total + $op_balance;
		$sum1 = $sum1 + $op_balance;

                echo "<td align=\"right\">" . convert_amount_dc(+$dr_total) . "</td>";
                echo "<td align=\"right\">" . convert_amount_dc(-$cr_total) . "</td>";

	/* code for reading previous year data from xml */
           $acctpath= $this->upload_path1= realpath(BASEPATH.'../uploads/xml');
           $file_name="schedule".$count."-". $current_active_account."-".$prev_year.".xml";
           $tt=$acctpath."/".$file_name;
           if(file_exists($tt))
           {
           $doc = new DomDocument();
           $doc->formatOutput = true;
           $doc->load($tt);
           $xpath = new DomXPath($doc);
           $schedule1 = "schedule".$count;
           $schedule2 = $schedule1."_Name";
           $xpath->query("/".$schedule1."/".$schedule2."/Group_Name");
           $xpath->query("/".$schedule1."/".$schedule2."/Dr_Amount");
           $xpath->query("/".$schedule1."/".$schedule2."/Cr_Amount");
           $xpath->query("/".$schedule1."/".$schedule2."/Group_ID");
           $schedulenode1 = $xpath->query("/".$schedule1."/".$schedule2."/Group_Name");
           $schedulenode2 = $xpath->query("/".$schedule1."/".$schedule2."/Dr_Amount");
           $schedulenode3 = $xpath->query("/".$schedule1."/".$schedule2."/Cr_Amount");
           $schedulenode4 = $xpath->query("/".$schedule1."/".$schedule2."/Group_ID");
           $schedulelist1 = @$schedulenode1->item($i)->nodeValue;
           $schedulelist2 = @$schedulenode2->item($i)->nodeValue;
           $schedulelist3 = @$schedulenode3->item($i)->nodeValue;
           $schedulelist4 = @$schedulenode4->item($i)->nodeValue;
           }
           $prev_sum = $prev_sum+$schedulelist2;
           $prev_sum1 = $prev_sum1+$schedulelist3;
           $i++;
           if($schedulelist2 == 0)
           echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
           else
           echo "<td align=\"right\">" . convert_amount_dc($schedulelist2) . "</td>";
           if($schedulelist3 == 0)
           echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
           else
           echo "<td align=\"right\">" . convert_amount_dc(-$schedulelist3) . "</td>";
           echo "</tr>";
	}//ifview
	if(($type == "CF") && ($database != "NULL"))
	{
		$t_name = "schedule".$count;
		$CI = & get_instance();
                $CI->load->model('investment_model');
                $result_a = $CI->investment_model->provision1($group_id);
                $value_a = explode('#', $result_a);
                $dr_total = $value_a[0];
                $cr_total = $value_a[1];
		$data = $this->xml_creation($t_name,$group_id,$database,$group_name,$curr_year,$dr_total,$cr_total);
	}
	}//foreach
		$this->dr_total1 = $sum;
		$this->cr_total1 = $sum1;
		$this->old_dr_total1 = $prev_sum;
		$this->old_cr_total1 = $prev_sum1;
}

/*function current_liabilities($counter)
{
	
	$credit_total = 0.00;
        $debit_total = 0.00;
        $old_credit_total = 0.00;
	$old_debit_total = 0.00;
	if(($this->name == 'Sundry Creditors') || ($this->name == 'Interest accrued but not due on') || ($this->name == 'Statutory Liabilities') || ($this->name == 'Other current Liabilities'))
	{
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
                foreach($this->children_groups as $id => $data)
		{
                        $credit_amount = 0.00;
                        $debit_amount = 0.00;
                        $old_credit_amount = 0.00;
                        $old_debit_amount = 0.00;
			if(count($data->children_ledgers) > 0)
			{
               		foreach($data->children_ledgers as $id => $row)
			{
                        	$CI =& get_instance();
                        	$CI->db->select('id, amount, dc');
                         	$CI->db->from('entry_items')->where('ledger_id', $row['id']);
                        	$entry_items_q = $CI->db->get();
                        	if($entry_items_q->num_rows() > 0)
                        	{
                                $entry_items_result = $entry_items_q->result();
                                foreach ($entry_items_result as $row1)
                                {
                                if($row1->dc == 'C')
				{
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
				

           /*                             $this->getPreviousYearDetails();
                                        if($this->prevYearDB != "" ){//3
                                                /* database connectivity for getting previous year opening balance */
          /*                                      $con = mysql_connect($this->host_name, $this->db_username, $this->db_password);
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
                                        $old_credit_amount = $old_credit_amount + $row['opbalance_prev']; */

         /*                       }//for
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
                	             //           echo convert_amount_dc($old_debit_amount);
                        	        	echo "</td>";
                                		echo "<td align=\"right\">";
                                       // 	echo convert_amount_dc(-$old_credit_amount);
		                        	echo "</td>";
                                		echo "</tr>";
					     }
                			}

			if(count($this->children_ledgers) > 0)
			{
                        foreach($this->children_ledgers as $id => $row)
			{
                        	$CI =& get_instance();
                        	$CI->db->select('id, amount, dc');
                         	$CI->db->from('entry_items')->where('ledger_id', $row['id']);
                        	$entry_items_q = $CI->db->get();
                        	if($entry_items_q->num_rows() > 0)
                       		{
                                $entry_items_result = $entry_items_q->result();
                                foreach ($entry_items_result as $row1)
                                {
                                if($row1->dc == 'C')
				{
                                $credit_total = $credit_total + $row1->amount;
                              	}else{
                                $debit_total = $debit_total + $row1->amount;
                                }
                              	}
                              	}

                                        //Adding opening balance for the ledger head.
                                        $credit_total = $credit_total + $row['opbalance'];

                                   /*     $this->getPreviousYearDetails();
                                        if($this->prevYearDB != "" ){//3
                                                /* database connectivity for getting previous year opening balance */
                                     /*           $con = mysql_connect($this->host_name, $this->db_username, $this->db_password);
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
                                        $old_credit_total = $old_credit_total + $row['opbalance_prev'];  */
          /*                      }//for
			}//if count
	
if(!(($this->name == 'Sundry Creditors') || ($this->name == 'Interest accrued but not due on') || ($this->name == 'Statutory Liabilities') || ($this->name == 'Other current Liabilities'))){
                                        	echo "<td align=\"right\">";
                                                echo convert_amount_dc($debit_total);
                                        	echo "</td>";
                                        	echo "<td align=\"right\">";
                                                echo convert_amount_dc(-$credit_total);
                                        	echo "</td>";
                                        	echo "<td align=\"right\">";
                                       //         echo convert_amount_dc($old_debit_total);
                                        	echo "</td>";
                                        	echo "<td align=\"right\">";
                                         //       echo convert_amount_dc(-$old_credit_total);
                                        	echo "</td>";
                                		echo "</tr>";
                			      }	

                $this->dr_total = $debit_total;
                $this->cr_total = $credit_total;
           //     $this->old_dr_total = $old_debit_total;
          //      $this->old_cr_total = $old_credit_total;
	}

function provisions($counter)
{
	$credit_total = 0.00;
	$debit_total = 0.00;
	$old_credit_total = 0.00;
	$old_debit_total = 0.00;
	foreach($this->children_groups as $id => $data)
	{
	if(count($data->children_ledgers) > 0)
	{
     	foreach($data->children_ledgers as $id => $row)
	{
        	$CI =& get_instance();
        	$CI->db->select('id, amount, dc');
         	$CI->db->from('entry_items')->where('ledger_id', $row['id']);
        	$entry_items_q = $CI->db->get();
       		if($entry_items_q->num_rows() > 0)
                {
                $entry_items_result = $entry_items_q->result();
                foreach ($entry_items_result as $row1)
                {
                if($row1->dc == 'C')
		{
                $credit_total = $credit_total + $row1->amount;
                }else{
                $debit_total = $debit_total + $row1->amount;
                     }
            	}
                }

                                        //Adding opening balance for the ledger head.
                                        $credit_total = $credit_total + $row['opbalance'];

                               /*         $this->getPreviousYearDetails();
                                        if($this->prevYearDB != "" ){//3
                                                /* database connectivity for getting previous year opening balance */
                                 /*               $con = mysql_connect($this->host_name, $this->db_username, $this->db_password);
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
                                        $old_credit_total = $old_credit_total + $row['opbalance_prev'];*/

                            /*    }//for
	                }//if count
		}		

                if(count($this->children_ledgers) > 0)
		{
                foreach($this->children_ledgers as $id => $row)
		{
                	$CI =& get_instance();
                        $CI->db->select('id, amount, dc');
                      	$CI->db->from('entry_items')->where('ledger_id', $row['id']);
                       	$entry_items_q = $CI->db->get();
                        if($entry_items_q->num_rows() > 0)
               		{
                        $entry_items_result = $entry_items_q->result();
                        foreach ($entry_items_result as $row1)
                   	{
                     	if($row1->dc == 'C')
			{
                       	$credit_total = $credit_total + $row1->amount;
                        }else{
                      	$debit_total = $debit_total + $row1->amount;
                              }
                     	}
                        }

                                        //Adding opening balance for the ledger head.
                                        $credit_total = $credit_total + $row['opbalance'];

                                     /*   $this->getPreviousYearDetails();
                                        if($this->prevYearDB != "" ){//3
                                                /* database connectivity for getting previous year opening balance */
                                       /*         $con = mysql_connect($this->host_name, $this->db_username, $this->db_password);
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
                                        $old_credit_total = $old_credit_total + $row['opbalance_prev'];  */

                              /*  }//for
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
              //   echo convert_amount_dc($old_debit_total);
                 echo "</td>";
                 echo "<td align=\"right\">";
            	// echo convert_amount_dc(-$old_credit_total);
                 echo "</td>";
                 echo "</tr>";

		$this->dr_total = $debit_total;
                $this->cr_total = $credit_total;
                $this->old_dr_total = $old_debit_total;
                $this->old_cr_total = $old_credit_total;
	}*/
   ////////////////////////////////@kanchan
function Investments($id,$type2,$count,$code,$database,$type,$i)
{//main
	$CI = & get_instance();
	$counter = 1;
	$sum1 = 0;
	$sum2 = 0;
	$sum3 = 0;
	$sum4 = 0;
	$prev_sum = 0;
	$prev_sum1 = 0;
	$prev_sum2 = 0;
	$prev_sum3 = 0;
	$schedulelist2 = "";
	$schedulelist3 = "";
	$CI =& get_instance();
	$current_active_account = $CI->session->userdata('active_account');
	$CI->db->from('settings');
	$detail = $CI->db->get();
	foreach ($detail->result() as $row)
	{
	$date1 = $row->fy_start;
	$date2 = $row->fy_end;
	}
	$fy_start=explode("-",$date1);
	$fy_end=explode("-",$date2);
	$curr_year = $fy_start[0] ."-" .$fy_end[0];
	$prev_year = ($fy_start[0]-1) ."-" . ($fy_end[0]-1);
	$CI->load->model('Payment_model');
	$db = $CI->Payment_model->database_name();
		
	if($type2 == "Earmarked Funds")
        {
		$CI->db->select('id,name')->from('groups')->where('parent_id',$id);
	        $groups_result1 = $CI->db->get();
        	$gr = $groups_result1->result();
        	foreach($gr as $row1)
        	{//1    
        	$invest_id = $row1->id;
        	$group_name = $row1->name;
		if(($type == "view") && ($database == "NULL"))
                {
        	echo "<tr class=\"tr-group\">";
        	echo "<td class=\"td-group\">";
		echo $counter;
        	$counter++;
                echo "&nbsp;&nbsp;" . $row1->name;
                echo "</td>";
		$CI =& get_instance();
		$CI->load->model('investment_model');   
		$result = $CI->investment_model->investment1($invest_id,$type2);  
		$value = explode('#',$result);

		$dr_total1 = $value[0];
		$sum1 = $sum1+$dr_total1;
		
		$cr_total2 = $value[1];
		$sum2 = $sum2+$cr_total2;

		echo "<td width=15% align=\"right\">";
                echo convert_amount_dc(+$dr_total1);
	        echo "</td>";
                echo "<td width=15% align=\"right\">";
         	echo convert_amount_dc(-$cr_total2);
		echo "</td>";

		/* code for reading previous year data from xml */
	  	$acctpath= $this->upload_path1= realpath(BASEPATH.'../uploads/xml');
      		$file_name="schedule".$count."-".$current_active_account."-".$prev_year.".xml";
           	$tt=$acctpath."/".$file_name;
           	if(file_exists($tt))
           	{
           	$doc = new DomDocument();
           	$doc->formatOutput = true;
           	$doc->load($tt);
           	$xpath = new DomXPath($doc);
           	$schedule1 = "schedule".$count;
           	$schedule2 = $schedule1."_Name";

           	$xpath->query("/".$schedule1."/".$schedule2."/Group_Name");
           	$xpath->query("/".$schedule1."/".$schedule2."/Dr_Amount");
           	$xpath->query("/".$schedule1."/".$schedule2."/Cr_Amount");
           	$xpath->query("/".$schedule1."/".$schedule2."/Group_ID");
           	$schedulenode1 = $xpath->query("/".$schedule1."/".$schedule2."/Group_Name");
           	$schedulenode2 = $xpath->query("/".$schedule1."/".$schedule2."/Dr_Amount");
           	$schedulenode3 = $xpath->query("/".$schedule1."/".$schedule2."/Cr_Amount");
           	$schedulenode4 = $xpath->query("/".$schedule1."/".$schedule2."/Group_ID");
           	$schedulelist1 = @$schedulenode1->item($i)->nodeValue;
           	$schedulelist2 = @$schedulenode2->item($i)->nodeValue;
           	$schedulelist3 = @$schedulenode3->item($i)->nodeValue;
           	$schedulelist4 = @$schedulenode4->item($i)->nodeValue;
           	}
           	$prev_sum = $prev_sum+$schedulelist2;
           	$prev_sum1 = $prev_sum1+$schedulelist3;
           	$i++;
           	if($schedulelist2 == 0)
           	echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
           	else
           	echo "<td align=\"right\">" . convert_amount_dc($schedulelist2) . "</td>";
           	if($schedulelist3 == 0)
           	echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
           	else
           	echo "<td align=\"right\">" . convert_amount_dc(-$schedulelist3) . "</td>";
        }//ifview 

		if(($type == "CF") && ($database != "NULL"))
               	{
                        $t_name = "schedule".$count;
                        $CI = & get_instance();
                        $CI->load->model('investment_model');
                        $result = $CI->investment_model->investment1($invest_id,$type2);
                        $value = explode('#',$result);
                        $dr_total1 = $value[0];
                        $cr_total2 = $value[1];
                        $data = $this->xml_creation($t_name,$invest_id,$database,$group_name,$curr_year,$dr_total1,$cr_total2);
               }
	}//foreach
	}//iftype
		else
		{
		$CI->db->select('id,name')->from('groups')->where('parent_id',$id);
                $groups_result1 = $CI->db->get();
                $gr = $groups_result1->result();
                foreach($gr as $row1)
                {//1    
                $invest_id = $row1->id;
                $group_name = $row1->name;
                if(($type == "view") && ($database == "NULL"))
                {
                echo "<tr class=\"tr-group\">";
                echo "<td class=\"td-group\">";
                echo $counter;
                $counter++;
                echo "&nbsp;&nbsp;" . $row1->name;
                echo "</td>";
                $CI =& get_instance();
                $CI->load->model('investment_model');
                $result = $CI->investment_model->investment1($invest_id,$type2);
                $value = explode('#',$result);
		$dr_total3 = $value[2];
                $sum3 = $sum3+$dr_total3;
        	$cr_total4 = $value[3];
	        $sum4 = $sum4+$cr_total4;

		echo "<td width=15% align=\"right\">";
		echo convert_amount_dc(+$dr_total3);
                echo "</td>";
                echo "<td width=15% align=\"right\">";
		echo convert_amount_dc(-$cr_total4);
		echo"</td>";
		/* code for reading previous year data from xml */
	   	$acctpath= $this->upload_path1= realpath(BASEPATH.'../uploads/xml');
           	$file_name="schedule".$count."-".$prev_year."-".".xml";
           	$tt=$acctpath."/".$file_name;
           	if(file_exists($tt))
           	{
           		$doc = new DomDocument();
           		$doc->formatOutput = true;
           		$doc->load($tt);
           		$xpath = new DomXPath($doc);
           		$schedule1 = "schedule".$count;
           		$schedule2 = $schedule1."_Name";

           		$xpath->query("/".$schedule1."/".$schedule2."/Group_Name");
           		$xpath->query("/".$schedule1."/".$schedule2."/Dr_Amount");
           		$xpath->query("/".$schedule1."/".$schedule2."/Cr_Amount");
           		$xpath->query("/".$schedule1."/".$schedule2."/Group_ID");
           		$schedulenode1 = $xpath->query("/".$schedule1."/".$schedule2."/Group_Name");
           		$schedulenode2 = $xpath->query("/".$schedule1."/".$schedule2."/Dr_Amount");
           		$schedulenode3 = $xpath->query("/".$schedule1."/".$schedule2."/Cr_Amount");
           		$schedulenode4 = $xpath->query("/".$schedule1."/".$schedule2."/Group_ID");
           		$schedulelist1 = @$schedulenode1->item($i)->nodeValue;
           		$schedulelist2 = @$schedulenode2->item($i)->nodeValue;
           		$schedulelist3 = @$schedulenode3->item($i)->nodeValue;
           		$schedulelist4 = @$schedulenode4->item($i)->nodeValue;
           		}
           		$prev_sum2 = $prev_sum2+$schedulelist2;
           		$prev_sum3 = $prev_sum3+$schedulelist3;
           		$i++;
           		if($schedulelist2 == 0)
           		echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
           		else
           		echo "<td align=\"right\">" . convert_amount_dc($schedulelist2) . "</td>";
           		if($schedulelist3 == 0)
           		echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
           		else
           		echo "<td align=\"right\">" . convert_amount_dc(-$schedulelist3) . "</td>";
        	}//ifview 

	       if(($type == "CF") && ($database != "NULL"))	
	       {
	       	        $t_name = "schedule".$count;
                        $CI = & get_instance();
	                $CI->load->model('investment_model');
        	        $result = $CI->investment_model->investment1($invest_id,$type2);
                	$value = explode('#',$result);
                	$dr_total3 = $value[2];
                	$cr_total4 = $value[3];
			$data = $this->xml_creation($t_name,$invest_id,$database,$group_name,$curr_year,$dr_total3,$cr_total4);
	       }  
	}//elseif
	
}//foreach
			$this->dr_total1 = $sum1;
			$this->cr_total1 = $sum2;
			$this->dr_total2 = $sum3;
			$this->cr_total2 = $sum4;
			$this->old_dr_total1 = $prev_sum;
			$this->old_cr_total1 = $prev_sum1;
			$this->old_dr_total2 = $prev_sum2;
			$this->old_cr_total2 = $prev_sum3; 
	}//main
//////////////////////////////////
function sub_schedule()
{
	$counter = 1;
	$opening_dr = 0.00;
	$closing_dr = 0.00;
	$trans_dr = 0.00;
	$opening_cr = 0.00;
	$closing_cr = 0.00;
	$trans_cr = 0.00;
	if(count($this->children_ledgers) > 0){
	foreach($this->children_ledgers as $id => $row)
	{
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
               }//foreach
       	}//if
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

///////////////////////////////

function fixed_assets($id,$count,$code,$type,$database)
{
	$CI = & get_instance();
	$current_active_account = $CI->session->userdata('active_account');
	$counter = 1;
	$count1 = 1;
	$sum = 0;
	$sum1 = 0;
	$opening_balance = 0.00;
	$total = 0;
	$total1 = 0;
	$total2 = 0;
	$total3 = 0;
	$total4 = 0;
	$schedulelist2 = "";
	$schedulelist3 = "";
	$prev_sum = 0;
	$i = 0;
	$net_dr = 0.00;
	$net_opening_bal = 0.00;
	$net_cr = 0.00;
	$net_total = 0.00;
	$net_current_year = 0.00;
	$dr_total = 0.00;
	$op_total = 0.00;
	$cr_total = 0.00;
	$year_end_value = 0.00;
	$year_end_total = 0.00;
	$current_year_total = 0.00;
	$CI->db->from('settings');
        $detail = $CI->db->get();
        foreach ($detail->result() as $row)
        {
        $date1 = $row->fy_start;
        $date2 = $row->fy_end;
        }
        $fy_start=explode("-",$date1);
        $fy_end=explode("-",$date2);
        $curr_year = $fy_start[0] ."-" .$fy_end[0];
        $prev_year = ($fy_start[0]-1) ."-" . ($fy_end[0]-1);
        $CI->load->model('payment_model');
        $db = $CI->payment_model->database_name();
	
	$CI->db->select('id,name,code')->from('groups')->where('parent_id', $id);
	$group_q = $CI->db->get();
	$group_result = $group_q->result();
	foreach($group_result as $row)
	{
	$group_id = $row->id;	
	$group_name = $row->name;
	$CI = & get_instance();
	$CI->db->select('id,name,code')->from('groups')->where('parent_id', $group_id);
	$main = $CI->db->get();
	$main_q = $main->result();
	foreach($main_q as $row1)
	{
	$children_groupid = $row1->id;
	$children_groupname = $row1->name;
	
	if(($type == "view") && ($database == "NULL"))
	{
	echo "<tr class=\"tr-group\">";
        echo "<td class=\"td-group\" width=\"9%\">";
	echo $this->numberToRoman($count1);
	echo "&nbsp;&nbsp;" . $children_groupname;
	$count1++;
        echo "</td>";
	
	$CI = & get_instance();
	$CI->load->model('investment_model');
	$result1 = $CI->investment_model->fixed_asset1($children_groupid);
	$value1 = explode("#", $result1);
	$credit_amount = $value1[0];
	$debit_amount = $value1[1];
	$cr_total = $value1[2];
	$dr_total = $value1[3];
	$op_total = $value1[4];
	$net_opening_bal = $value1[5];
	$year_end_value = $value1[6];
	$net_total = $value1[7];
	$op_balance = $value1[8];
	$net_dr = $value1[9];
	$net_cr = $value1[10];
        /**
        * Calculate depreciated value of the asset
        */
        //to be done using pico data
        //till then asset value will not be deducted.
        //So, year_end_total = year_end_value

        $year_end_total = $year_end_value;
        $net_current_year = $net_total;
	if(!($children_groupname == 'Land'))
	{
		echo "<td align=\"right\" width=\"9%\">";
        	echo convert_amount_dc(+$op_balance);
        	echo "</td>";

        	echo "<td align=\"right\" width=\"9%\">";
        	echo convert_amount_dc(+$dr_total);
        	echo "</td>";

        	echo "<td align=\"right\" width=\"9%\">";
        	echo convert_amount_dc(-$cr_total);
        	echo "</td>";

        	echo "<td align=\"right\" width=\"9%\">";
        	echo convert_amount_dc($op_balance + $dr_total - $cr_total);
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
        	echo convert_amount_dc($op_balance + $dr_total - $cr_total);
        	echo "</td>";
		$sum = $sum + $dr_total;
                $sum1 = $sum1 + $cr_total;
                $opening_balance = $opening_balance + $op_balance;
                $total1 = $op_balance+$dr_total-$cr_total;
                $total2 = $total2+$total1;
                $total3 = $op_balance+$dr_total-$cr_total;
                $total4 = $total4+$total3;
		}//if
		else
		{
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
		
                }//else
		
		/* code for reading previous year data from xml */
                        $acctpath= $this->upload_path1= realpath(BASEPATH.'../uploads/xml');
                        $file_name="schedule".$count."-".$current_active_account."-".$prev_year.".xml";
                        $tt=$acctpath."/".$file_name;
                        if(file_exists($tt))
                        {
                        $doc = new DomDocument();
                        $doc->formatOutput = true;
                        $doc->load($tt);
                        $xpath = new DomXPath($doc);
                        $schedule1 = "schedule".$count;
                        $schedule2 = $schedule1."_Name";
                        $xpath->query("/".$schedule1."/".$schedule2."/Group_Name");
                        $xpath->query("/".$schedule1."/".$schedule2."/Amount");
                        $xpath->query("/".$schedule1."/".$schedule2."/Group_ID");
                        $schedulenode1 = $xpath->query("/".$schedule1."/".$schedule2."/Group_Name");
                        $schedulenode2 = $xpath->query("/".$schedule1."/".$schedule2."/Amount");
                        $schedulenode3 = $xpath->query("/".$schedule1."/".$schedule2."/Group_ID");
                        $schedulelist1 = @$schedulenode1->item($i)->nodeValue;
                        $schedulelist2 = @$schedulenode2->item($i)->nodeValue;
                        $schedulelist3 = @$schedulenode3->item($i)->nodeValue;
                        }
                        $prev_sum = $prev_sum+$schedulelist2;
                        $i++;
                        if($schedulelist3 != 73)
                        echo "<td align=\"right\">" . convert_amount_dc(+$schedulelist2) . "</td>";
			else
			echo "<td> </td>";
                        echo "</tr>";
                        }//ifview               
         if(($type == "CF") && ($database != "NULL"))
         {
                $t_name = "schedule".$count;
                $CI = & get_instance();
                $CI->load->model('investment_model');
                $result1 = $CI->investment_model->fixed_asset1($children_groupid);
		$value1 = explode('#',$result1);
                $net_total = $value1[7];
                $CI->load->model('Payment_model');
                $data = $CI->Payment_model->xml_creation($t_name,$children_groupid,$database,$children_groupname,$curr_year,$net_total);
         }

		if($children_groupname == "Land")
		{
			$CI = & get_instance();
        		$CI->db->select('id,name,code,op_balance')->from('ledgers')->where('group_id', $children_groupid);
        		$ledgers_q = $CI->db->get();
        		$ledger_result = $ledgers_q->result();
			foreach($ledger_result as $row2)
        		{
                	$debit_amount = 0.00;
                	$credit_amount = 0.00;
                	$cr_total = 0.00;
                	$dr_total = 0.00;
                	$net_dr = 0.00;
                	$net_cr = 0.00;

                	$ledger_id = $row2->id;
                	$ledger_name = $row2->name;
                	$op_balance = $row2->op_balance;
			if(($type == "view") && ($database == "NULL"))
			{
			echo "<tr> <class=\"tr-ledger\">";
        		echo "<td class=\"td-ledger\" width=\"10%\">";
        		echo "&nbsp;&nbsp;&nbsp;".$counter.".&nbsp;";
        		echo $ledger_name;
        		$counter++;
        		echo "</td>";
			$CI = & get_instance();
                	$CI->db->select('id,amount,dc')->from('entry_items')->where('ledger_id', $ledger_id);
                	$entry_items_q = $CI->db->get();
                	$entry_items_result = $entry_items_q->result();
                	foreach($entry_items_result as $row3)
                	{
			$amount = $row3->amount;
                	if($row3->dc == 'C')
                	{
                	$credit_amount = $credit_amount + $row3->amount;
                	$net_cr = $net_cr + $row3->amount;
                	}
                	else
                	{
                	$debit_amount = $debit_amount + $row3->amount;
                	$net_dr = $net_dr + $row3->amount;
                	}
                	}//foreach

                	$cr_total = $cr_total + $credit_amount;
                	$dr_total = $dr_total + $debit_amount;

      			//Adding opening balance for the ledger head.
        		$op_balance = $row2->op_balance;
        		$op_total = $op_total + $row2->op_balance;
        		$net_opening_bal = $net_opening_bal + $row2->op_balance;
        		$year_end_value = $year_end_value + ($row2->op_balance + $debit_amount) - $cr_total;
	 		$net_total = $net_total + ($row2->op_balance + $debit_amount) - $cr_total;
			
			/**
		        * Calculate depreciated value of the asset
        		*/
        		//to be done using pico data
        		//till then asset value will not be deducted.
        		//So, year_end_total = year_end_value

        		$year_end_total = $year_end_value;
        		$net_current_year = $net_total;
			
			$total = ($op_balance + $dr_total - $cr_total);
			echo "<td align=\"right\" width=\"9%\">";
        		echo convert_amount_dc($op_balance);
                	echo "</td>";

                	echo "<td align=\"right\" width=\"9%\">";
                	echo convert_amount_dc(+$dr_total);
                	echo "</td>";

                	echo "<td align=\"right\" width=\"9%\">";
                	echo convert_amount_dc(-$cr_total);
                	echo "</td>";

                	echo "<td align=\"right\" width=\"9%\">";
                	echo convert_amount_dc($op_balance + $dr_total - $cr_total);
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
                	echo convert_amount_dc($op_balance + $dr_total - $cr_total);
                	echo "</td>";
			$sum = $sum + $dr_total;
			$sum1 = $sum1 + $cr_total;
			$opening_balance = $opening_balance + $op_balance;
			$total1 = $op_balance+$dr_total-$cr_total;
			$total2 = $total2+$total1;
			$total3 = $op_balance+$dr_total-$cr_total;
			$total4 = $total4+$total3;

		 	/* code for reading previous year data from xml */
	           	$acctpath= $this->upload_path1= realpath(BASEPATH.'../uploads/xml');
        	   	$file_name="schedule".$count."-".$current_active_account."-".$prev_year.".xml";
           	   	$tt=$acctpath."/".$file_name;
           	   	if(file_exists($tt))
           	   	{
           	   	$doc = new DomDocument();
           	   	$doc->formatOutput = true;
           		$doc->load($tt);
           		$xpath = new DomXPath($doc);
           		$schedule1 = "schedule".$count;
           		$schedule2 = $schedule1."_Name";
           		$xpath->query("/".$schedule1."/".$schedule2."/Group_Name");
           		$xpath->query("/".$schedule1."/".$schedule2."/Amount");
           		$xpath->query("/".$schedule1."/".$schedule2."/Group_ID");
           		$schedulenode1 = $xpath->query("/".$schedule1."/".$schedule2."/Group_Name");
           		$schedulenode2 = $xpath->query("/".$schedule1."/".$schedule2."/Amount");
           		$schedulenode3 = $xpath->query("/".$schedule1."/".$schedule2."/Group_ID");
           		$schedulelist1 = @$schedulenode1->item($i)->nodeValue;
           		$schedulelist2 = @$schedulenode2->item($i)->nodeValue;
           		$schedulelist3 = @$schedulenode3->item($i)->nodeValue;
           		}
           	//	$prev_sum = $prev_sum+$schedulelist2;
           		$i++;
           		if($schedulelist2 == 0)
           		echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
           		else
           		echo "<td align=\"right\">" . convert_amount_dc(+$schedulelist2) . "</td>";
			echo "</tr>";
		}//ifview 
		if(($type == "CF") && ($database != "NULL"))
                {
                        $t_name = "schedule".$count;
			$asset = new Balancesheet();
			$asset->init_led($ledger_id);
			$total = $asset->total;
                        $CI = & get_instance();
                        $CI->load->model('Payment_model');
                        $data = $CI->Payment_model->xml_creation($t_name,$ledger_id,$database,$ledger_name,$curr_year,$total);
                }
		}//foreach
		}//if Ledger
		
	}//foreach
	}//foreach
	$this->dr_amount = $sum;
	$this->cr_amount = $sum1;
	$this->opening_bal = $opening_balance;
	$this->total_amount = $total2;
	$this->current_amount = $total4;
	$this->net_previousYear = $prev_sum;

}//function fixed_asset()

//////////////////////////////////////////////////////////

//		if($is_ledger == true)	
//		{
/*		if(!($children_groupname == 'Land'))
		{
        		$children_groupid = $row1->id;
        		$children_groupname = $row1->name;

		 	echo "<tr class=\"tr-group\">";
                 	echo "<td class=\"td-group\" width=\"15%\">";
                 	echo $this->numberToRoman($count1).". ".$children_groupname;
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
        //       		echo convert_amount_dc($previous_year_total);
                 		echo "</td>";
                 		echo "</tr>";
                 }// if not 'Land' */



/*	if($ledgers_q->num_rows()  > 0){
	$counter = 1;
	$is_ledger = false;

	foreach($ledger_result as $row2){
	$ledger_id = $row2->id;
	$ledger_name = $row2->name;

	$debit_amount = 0.00;
	$credit_amount = 0.00;
	$old_debit_amount = 0.00;
	$old_credit_amount = 0.00;

//	if($children_groupname == 'Land'){
	$cr_total = 0.00;
	$dr_total = 0.00;
	$old_debit_total = 0.00;
	$old_credit_total = 0.00;
//	}

	$CI =& get_instance();
        $CI->db->select('id, amount, dc')->from('entry_items')->where('ledger_id', $row2->id);
        $entry_items_q = $CI->db->get();
        if($entry_items_q->num_rows() > 0)
        {
	$entry_items_result = $entry_items_q->result();
        foreach ($entry_items_result as $row3)
       	{
        if($row3->dc == 'C'){
	$credit_amount = $credit_amount + $row3->amount;
	$net_cr = $net_cr + $row3->amount;
        }else{
	$debit_amount = $debit_amount + $row3->amount;
	$net_dr = $net_dr + $row3->amount;
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

/*	$year_end_total = $year_end_value;
	$net_current_year = $net_total;


/*        $this->getPreviousYearDetails();
        if($this->prevYearDB != "" ){//3
        /* database connectivity for getting previous year opening balance */
/*        $con = mysql_connect($this->host_name, $this->db_username, $this->db_password);
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
	 $net_previous_year = $net_previous_year + ($row['opbalance_prev'] + $old_debit_amount) - $old_credit_amount;  */
					
/*	if($children_groupname == 'Land'){
	echo "<tr> <class=\"tr-ledger\">";
	echo "<td class=\"td-ledger\" width=\"10%\">";
	echo "&nbsp;&nbsp;&nbsp;".$counter.".&nbsp;";
    	echo $ledger_name;
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
//	}

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
				
 /*    $year_end_total = $year_end_value;
     $net_current_year = $net_total;

/*     $this->getPreviousYearDetails();
     if($this->prevYearDB != "" ){//3
     /* database connectivity for getting previous year opening balance */
/*     $con = mysql_connect($this->host_name, $this->db_username, $this->db_password);
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
      $net_previous_year = $net_previous_year + ($this->opbalance_prev + $old_debit_total) - $old_credit_total;*/
   /*   }
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
}//function: fixed_assets() */

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

/*	function current_assets_groups($count){
		
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
                           /*     	$con = mysql_connect($this->host_name, $this->db_username, $this->db_password);
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
                  /*      $con = mysql_connect($this->host_name, $this->db_username, $this->db_password);
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
	}//function: current_assets_ledgers()   */
////////////////////////////////
	
function get_schedule($count,$code,$type,$database)
{
	$counter = 1;
        $sum = 0;
	$count1 = 1;
        $sum1 = 0;
        $prev_sum =0;
        $prev_sum1 = 0;
        $i = 0;
        $schedulelist2 = "";
        $schedulelist1 = "";
        $schedulelist3 = "";
        $CI =& get_instance();
	$current_active_account = $CI->session->userdata('active_account');
        $CI->db->from('settings');
        $detail = $CI->db->get();
        foreach ($detail->result() as $row)
        {
        $date1 = $row->fy_start;
        $date2 = $row->fy_end;
        }
        $fy_start=explode("-",$date1);
        $fy_end=explode("-",$date2);

        $curr_year = $fy_start[0] ."-" .$fy_end[0];
        $prev_year = ($fy_start[0]-1) ."-" . ($fy_end[0]-1);
        $CI->load->model('ledger_model');
        $id = $CI->ledger_model->get_group_id($code);
        $parent = $CI->ledger_model->get_group_name($id);
        $CI->db->select('name,code,id,parent_id')->from('groups')->where('parent_id',$id);
        $main = $CI->db->get();
        $main_result= $main->result();
        $CI =& get_instance();
        $CI->load->model('payment_model');
        $db = $CI->payment_model->database_name();
        foreach($main_result as $row)
        {//1
                $name = $row->name;
		$group_id =$row->id;
		$p_id = $row->parent_id;
                if(($type == "view") && ($database == "NULL"))
                {
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo $this->numberToRoman($count1);
                        echo "&nbsp;&nbsp;" . $name;
			echo "</td>";
                        $count1++;
			$CI = & get_instance();
			$CI->load->model('investment_model');
			$result = $CI->investment_model->current_asset($group_id);
			$value01 = explode('#', $result);
			$dr_total = $value01[0];
			$cr_total = $value01[1];
                //        echo "</td>";
                        echo "<td> </td>";
                        echo "<td> </td>";
                      //  $count1 = 9;
                /* code for reading previous year data from xml */
                        $acctpath= $this->upload_path1= realpath(BASEPATH.'../uploads/xml');
                        $file_name="schedule".$count."-".$current_active_account."-".$prev_year.".xml";
                        $tt=$acctpath."/".$file_name;
                        if(file_exists($tt))
                        {
                                $doc = new DomDocument();
                                $doc->formatOutput = true;
                                $doc->load($tt);
                                $xpath = new DomXPath($doc);
                                $schedule1 = "schedule".$count;
                                $schedule2 = $schedule1."_Name";
                                $xpath->query("/".$schedule1."/".$schedule2."/Group_Name");
                                $xpath->query("/".$schedule1."/".$schedule2."/Amount");
                                $xpath->query("/".$schedule1."/".$schedule2."/Group_ID");
                                $schedulenode1 = $xpath->query("/".$schedule1."/".$schedule2."/Group_Name");
                                $schedulenode2 = $xpath->query("/".$schedule1."/".$schedule2."/Amount");
                                $schedulenode3 = $xpath->query("/".$schedule1."/".$schedule2."/Group_ID");  
				$schedulelist1 = @$schedulenode1->item($i)->nodeValue;
                                $schedulelist2 = @$schedulenode2->item($i)->nodeValue;
                                $schedulelist3 = @$schedulenode3->item($i)->nodeValue;
                        }
                                $i++;
                                echo "<td> </td>";
                                echo "<td> </td>";
                                echo "</tr>";
                        }//ifview 
                                if(($type == 'CF') && ($database != 'NULL'))
                                {
                                        $t_name = "schedule".$count;
					$CI = & get_instance();
                        		$CI->load->model('investment_model');
                        		$result = $CI->investment_model->current_asset($group_id);
                        		$value01 = explode('#', $result);
                        		$dr_total = $value01[0];
                        		$cr_total = $value01[1];
                                        $data = $this->xml_creation($t_name,$group_id,$database,$name,$curr_year,$dr_total,$cr_total);
                                }
                                        $CI->db->select('name,code,id,op_balance')->from('ledgers')->where('group_id',$group_id);
                                        $ledger_detail = $CI->db->get();
                                        $ledger_result = $ledger_detail->result();
                                        foreach($ledger_result as $row1)
                                        {//2
                                                $ledger_name = $row1->name;
                                                $ledger_id = $row1->id;
						$op_balance = $row1->op_balance;
                                                if(($type == "view") && ($database == "NULL"))
                                                {
                                                        echo "<tr class=\"tr-ledger\">";
                                                        echo "<td class=\"td-ledger\">";
                                                        echo "&nbsp;&nbsp;&nbsp;&nbsp;";
                                                        echo $counter. ". " . $ledger_name;
                                                        $counter++;
                                                        echo "</td>";
        						$CI = & get_instance();
                                                        $CI->load->model('ledger_model');
                                                        $dr_total = $CI->ledger_model->get_dr_total($ledger_id);
                                                        $cr_total = $CI->ledger_model->get_cr_total($ledger_id);

							//Adding opening balance for the ledger head
                					$dr_total = $dr_total + $op_balance;

                                                        echo "<td align=\"right\">" . convert_amount_dc(+$dr_total) . "</td>";
                                                        echo "<td align=\"right\">" . convert_amount_dc(-$cr_total) . "</td>";
                                                        $sum = $sum + $dr_total;
                                                        $sum1 = $sum1 + $cr_total;
                /* code for reading previous year data from xml */
                        $acctpath= $this->upload_path1= realpath(BASEPATH.'../uploads/xml');
                        $file_name="schedule".$count."-".$current_active_account."-".$prev_year.".xml";
                        $tt=$acctpath."/".$file_name;
                        if(file_exists($tt))
                        {
                                $doc = new DomDocument();
                                $doc->formatOutput = true;
                                $doc->load($tt);
                                $xpath = new DomXPath($doc);
                                $schedule1 = "schedule".$count;
                                $schedule2 = $schedule1."_Name";
                                $xpath->query("/".$schedule1."/".$schedule2."/Group_Name");
                                $xpath->query("/".$schedule1."/".$schedule2."/Dr_Amount");
                                $xpath->query("/".$schedule1."/".$schedule2."/Cr_Amount");
                                $xpath->query("/".$schedule1."/".$schedule2."/Group_ID");
                                $schedulenode1 = $xpath->query("/".$schedule1."/".$schedule2."/Group_Name");
                                $schedulenode2 = $xpath->query("/".$schedule1."/".$schedule2."/Dr_Amount");
                                $schedulenode3 = $xpath->query("/".$schedule1."/".$schedule2."/Cr_Amount");
                                $schedulenode4 = $xpath->query("/".$schedule1."/".$schedule2."/Group_ID");
                                $schedulelist1 = @$schedulenode1->item($i)->nodeValue;
                                $schedulelist2 = @$schedulenode2->item($i)->nodeValue;
                                $schedulelist3 = @$schedulenode3->item($i)->nodeValue;
                                $schedulelist4 = @$schedulenode4->item($i)->nodeValue;
                        }
                                $prev_sum = $prev_sum+$schedulelist2;
                                $prev_sum1 = $prev_sum1+$schedulelist3;
                                $i++;
        			if($schedulelist2 == 0)
                                echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                                else
                                echo "<td align=\"right\">" . convert_amount_dc($schedulelist2) . "</td>";
                                if($schedulelist3 == 0)
                                echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                                else
                                echo "<td align=\"right\">" . convert_amount_dc(-$schedulelist3) . "</td>";
                                echo "</tr>";
                }//ifview 
                        if(($type == 'CF') && ($database != 'NULL'))
                        {
                                $t_name = "schedule".$count;
                                $CI = & get_instance();
                                $CI->load->model('ledger_model');
                                $dr_total = $CI->ledger_model->get_dr_total($ledger_id);
                                $cr_total = $CI->ledger_model->get_cr_total($ledger_id);
                                $data = $this->xml_creation($t_name,$ledger_id,$database,$ledger_name,$curr_year,$dr_total,$cr_total);
                        }
                }//2
		
		if($group_id == '150')
		{
			$CI = & get_instance();
			$CI->db->select('id,name,code,op_balance')->from('ledgers')->where('group_id', $p_id);
			$result_query = $CI->db->get();
			$r = $result_query->result();
			foreach($r as $ledger)
			{
				$led_id = $ledger->id;
				$led_name = $ledger->name;
				$op_balance = $ledger->op_balance;
				if(($type == "view") && ($database == "NULL"))
                                {
                                	echo "<tr class=\"tr-ledger\">";
                                	echo "<td class=\"td-ledger\">";
                                	echo "&nbsp;&nbsp;&nbsp;&nbsp;";
                                        echo $counter. ". " . $led_name;
                                      	$counter++;
                                        echo "</td>";
                			$CI = & get_instance();
					$CI->load->model('investment_model');
					$result_r = $CI->investment_model->current_asset_A($led_id);
			                $value_b = explode('#',$result_r);
                			$dr_total = $value_b[0];
                			$sum = $sum+$dr_total;
                			$cr_total = $value_b[1];
                			$sum1 = $sum1+$cr_total;
                                        //Adding opening balance for the ledger head
                                        $dr_total = $dr_total + $op_balance;
					$sum = $sum + $op_balance;

                                      	echo "<td align=\"right\">" . convert_amount_dc(+$dr_total) . "</td>";
                                        echo "<td align=\"right\">" . convert_amount_dc(-$cr_total) . "</td>";
			
				 	/* code for reading previous year data from xml */
	                        	$acctpath= $this->upload_path1= realpath(BASEPATH.'../uploads/xml');
        	                	$file_name="schedule".$count."-".$current_active_account."-".$prev_year.".xml";
                	        	$tt=$acctpath."/".$file_name;
                        		if(file_exists($tt))
                        		{
                                	$doc = new DomDocument();
                                	$doc->formatOutput = true;
                                	$doc->load($tt);
                                	$xpath = new DomXPath($doc);
                                	$schedule1 = "schedule".$count;
                                	$schedule2 = $schedule1."_Name";
                                	$xpath->query("/".$schedule1."/".$schedule2."/Group_Name");
                                	$xpath->query("/".$schedule1."/".$schedule2."/Dr_Amount");
                                	$xpath->query("/".$schedule1."/".$schedule2."/Cr_Amount");
                                	$xpath->query("/".$schedule1."/".$schedule2."/Group_ID");
                                	$schedulenode1 = $xpath->query("/".$schedule1."/".$schedule2."/Group_Name");
                                	$schedulenode2 = $xpath->query("/".$schedule1."/".$schedule2."/Dr_Amount");
                                	$schedulenode3 = $xpath->query("/".$schedule1."/".$schedule2."/Cr_Amount");
                                	$schedulenode4 = $xpath->query("/".$schedule1."/".$schedule2."/Group_ID");
                                	$schedulelist1 = @$schedulenode1->item($i)->nodeValue;
                                	$schedulelist2 = @$schedulenode2->item($i)->nodeValue;
                                	$schedulelist3 = @$schedulenode3->item($i)->nodeValue;
                                	$schedulelist4 = @$schedulenode4->item($i)->nodeValue;
                        		}
                                	$prev_sum = $prev_sum+$schedulelist2;
                                	$prev_sum1 = $prev_sum1+$schedulelist3;
                                	$i++;
                                	if($schedulelist2 == 0)
                                	echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                                	else
                                	echo "<td align=\"right\">" . convert_amount_dc($schedulelist2) . "</td>";
                                	if($schedulelist3 == 0)
                                	echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                                	else
                                	echo "<td align=\"right\">" . convert_amount_dc(-$schedulelist3) . "</td>";
                                	echo "</tr>";
                		}//ifview 

				if(($type == 'CF') && ($database != 'NULL'))
                                {
                                        $t_name = "schedule".$count;
                                        $CI = & get_instance();
                                        $CI->load->model('investment_model');
                                        $result_r = $CI->investment_model->current_asset_A($led_id);
                                        $value_b = explode('#', $result_r);
                                        $dr_total = $value_b[0];
                                        $cr_total = $value_b[1];
                                        $data = $this->xml_creation($t_name,$led_id,$database,$led_name,$curr_year,$dr_total,$cr_total);
                                }
			}//led foreach	
		}//group foreach
        }//1
                $this->dr_total1 = $sum;
                $this->cr_total1 = $sum1;
                $this->old_dr_total = $prev_sum;
                $this->old_cr_total = $prev_sum1;
}//main 
                                                                                       
/////////////////////////////////@kanchan
function loans_advances($count,$code,$type,$database)
{//main
	$counter = 1;
	$sum = 0;
	$sum1 = 0;
	$count1 = 0;
	$prev_sum =0;
	$prev_sum1 = 0;
	$i = 0;
	$schedulelist2 = "";
	$schedulelist1 = "";
	$schedulelist3 = "";
	$CI =& get_instance();
	$current_active_account = $CI->session->userdata('active_account');
	$CI->db->from('settings');
        $detail = $CI->db->get();
        foreach ($detail->result() as $row)
        {
        $date1 = $row->fy_start;
        $date2 = $row->fy_end;
        }
        $fy_start=explode("-",$date1);
        $fy_end=explode("-",$date2);

        $curr_year = $fy_start[0] ."-" .$fy_end[0];
        $prev_year = ($fy_start[0]-1) ."-" . ($fy_end[0]-1);
        $CI->load->model('ledger_model');
        $id = $CI->ledger_model->get_group_id($code);
	$parent = $CI->ledger_model->get_group_name($id);
        $CI->db->select('name,code,id,parent_id')->from('groups')->where('parent_id',$id);
	$main = $CI->db->get();
        $main_result= $main->result();
	$CI =& get_instance();
        $CI->load->model('payment_model');
        $db = $CI->payment_model->database_name();
        foreach($main_result as $row)
        {//1
        	$name = $row->name;
        	$group_id =$row->id;
		if(($type == "view") && ($database == "NULL"))
		{
			echo "<tr class=\"tr-group\">";
        		echo "<td class=\"td-group\">";
        		echo $this->numberToRoman($count);
                	echo "&nbsp;&nbsp;" . $name;
			$count++;
			echo "</td>";
			$CI = & get_instance();
			$CI->load->model('investment_model');
			$result2 = $CI->investment_model->loan_advances1($group_id);
			$value2 = explode('#', $result2 );
			$dr_total = $value2[0];
			$cr_total = $value2[1];
               // 	echo "</td>";
			echo "<td> </td>";
			echo "<td> </td>";
			$count1 = 10;
		/* code for reading previous year data from xml */
                        $acctpath= $this->upload_path1= realpath(BASEPATH.'../uploads/xml');
                        $file_name="schedule".$count1."-". $current_active_account ."-".$prev_year.".xml";
                        $tt=$acctpath."/".$file_name;
                        if(file_exists($tt))
                        {
                                $doc = new DomDocument();
                                $doc->formatOutput = true;
                                $doc->load($tt);
                                $xpath = new DomXPath($doc);
                                $schedule1 = "schedule";
                                $schedule2 = $schedule1."_Name";
                                $xpath->query("/".$schedule1."/".$schedule2."/Group_Name");
                                $xpath->query("/".$schedule1."/".$schedule2."/Amount");
                                $xpath->query("/".$schedule1."/".$schedule2."/Group_ID");
                                $schedulenode1 = $xpath->query("/".$schedule1."/".$schedule2."/Group_Name");
                                $schedulenode2 = $xpath->query("/".$schedule1."/".$schedule2."/Dr_Amount");
				$schedulenode3 = $xpath->query("/".$schedule1."/".$schedule2."/Cr_Amount");
                                $schedulenode4 = $xpath->query("/".$schedule1."/".$schedule2."/Group_ID");
                                $schedulelist1 = @$schedulenode1->item($i)->nodeValue;
				$schedulelist2 = @$schedulenode2->item($i)->nodeValue;
                                $schedulelist3 = @$schedulenode3->item($i)->nodeValue;
				$schedulelist4 = @$schedulenode4->item($i)->nodeValue;
                        }
                                $prev_sum = $prev_sum+$schedulelist2;
                                $i++;
				echo "<td> </td>";
				echo "<td> </td>";
                                echo "</tr>";
                	}//ifview 
				$count1 = 10;
				if(($type == 'CF') && ($database != 'NULL'))
                		{
                			$t_name = "schedule".$count1;
					$CI = & get_instance();
		                        $CI->load->model('investment_model');
                		        $result2 = $CI->investment_model->loan_advances1($group_id);
                        		$value2 = explode('#', $result2 );
                        		$dr_total = $value2[0];
                        		$cr_total = $value2[1];
                        		$data = $this->xml_creation($t_name,$group_id,$database,$name,$curr_year,$dr_total,$cr_total);
                		}
					$CI->db->select('name,code,id,op_balance')->from('ledgers')->where('group_id',$group_id);
                			$ledger_detail = $CI->db->get();
                			$ledger_result = $ledger_detail->result();
                			foreach($ledger_result as $row1)
                			{//2
						$ledger_name = $row1->name;
						$ledger_id = $row1->id;
						$op_balance = $row1->op_balance;
		  				if(($type == "view") && ($database == "NULL"))
		  				{
							echo "<tr class=\"tr-ledger\">";
                        				echo "<td class=\"td-ledger\">";
                        				echo "&nbsp;&nbsp;&nbsp;&nbsp;";
                        				echo $counter. ". " . $ledger_name;
                        				$counter++;
							echo "</td>";
							$CI = & get_instance();
							$CI->load->model('ledger_model');
        						$dr_total = $CI->ledger_model->get_dr_total($ledger_id);
							$cr_total = $CI->ledger_model->get_cr_total($ledger_id);
							
							//Adding opening balance for the ledger head.
		                	                $dr_total = $dr_total + $op_balance;

                        				echo "<td align=\"right\">" . convert_amount_dc(+$dr_total) . "</td>";
							echo "<td align=\"right\">" . convert_amount_dc(-$cr_total) . "</td>";
                        				$sum = $sum + $dr_total;
							$sum1 = $sum1 + $cr_total;
							$count1 = 10;
		/* code for reading previous year data from xml */
			$acctpath= $this->upload_path1= realpath(BASEPATH.'../uploads/xml');
                        $file_name="schedule".$count1."-". $current_active_account ."-".$prev_year.".xml";
                        $tt=$acctpath."/".$file_name;
                        if(file_exists($tt))
                        {
                        	$doc = new DomDocument();
                             	$doc->formatOutput = true;
                                $doc->load($tt);
                                $xpath = new DomXPath($doc);
                                $schedule1 = "schedule10";
                                $schedule2 = $schedule1."_Name";
                                $xpath->query("/".$schedule1."/".$schedule2."/Group_Name");
                                $xpath->query("/".$schedule1."/".$schedule2."/Dr_Amount");
				$xpath->query("/".$schedule1."/".$schedule2."/Cr_Amount");
                                $xpath->query("/".$schedule1."/".$schedule2."/Group_ID");
                                $schedulenode1 = $xpath->query("/".$schedule1."/".$schedule2."/Group_Name");
                                $schedulenode2 = $xpath->query("/".$schedule1."/".$schedule2."/Dr_Amount");
				$schedulenode3 = $xpath->query("/".$schedule1."/".$schedule2."/Cr_Amount");
                                $schedulenode4 = $xpath->query("/".$schedule1."/".$schedule2."/Group_ID");
                                $schedulelist1 = @$schedulenode1->item($i)->nodeValue;
                                $schedulelist2 = @$schedulenode2->item($i)->nodeValue;
				$schedulelist3 = @$schedulenode3->item($i)->nodeValue;
                                $schedulelist4 = @$schedulenode4->item($i)->nodeValue;
                        }
				$prev_sum = $prev_sum+$schedulelist2;
				$prev_sum1 = $prev_sum1+$schedulelist3;
                                $i++;
                                if($schedulelist2 == 0)
                                echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                                else
                                echo "<td align=\"right\">" . convert_amount_dc($schedulelist2) . "</td>";
				if($schedulelist3 == 0)
				echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
				else
				echo "<td align=\"right\">" . convert_amount_dc(-$schedulelist3) . "</td>";
                                echo "</tr>";
		}//ifview 
			$count1 = 10;  
			if(($type == 'CF') && ($database != 'NULL'))
                        {
				$t_name = "schedule".$count1;
				$CI = & get_instance();
                                $CI->load->model('ledger_model');
                                $dr_total = $CI->ledger_model->get_dr_total($ledger_id);
                                $cr_total = $CI->ledger_model->get_cr_total($ledger_id);
                                $data = $this->xml_creation($t_name,$ledger_id,$database,$ledger_name,$curr_year,$dr_total,$cr_total);
			}
		}//2
	}//1
		$this->dr_total1 = $sum;
		$this->cr_total1 = $sum1;
		$this->old_dr_total = $prev_sum;
		$this->old_cr_total = $prev_sum1;
}//main
/////////////////////////////////////////////////////	
				
               	 	/*	if(count($this->children_ledgers) > 0){
                        	foreach($this->children_ledgers as $id => $row){
                                $credit_amount = 0;
                               	$debit_amount = 0;
                                $old_credit_amount = 0;
                                $old_debit_amount = 0;   */

                              /*  $CI =& get_instance();
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

                              /*  $this->getPreviousYearDetails();
                                if($this->prevYearDB != "" ){//3
                                        /* database connectivity for getting previous year opening balance */
                                /*        $con = mysql_connect($this->host_name, $this->db_username, $this->db_password);
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
                                $old_debit_amount = $old_debit_amount + $row['opbalance_prev']; */

                               /* echo "<tr class=\"tr-ledger\">";
                                        echo "<td class=\"td-ledger\">";
                                                echo "&nbsp;&nbsp;&nbsp;&nbsp;";
                                                echo $counter. ". " . $row['name'];
                                                $counter++;
                                         echo "</td>";

                                         echo "<td align=\"right\">";
                                                // echo convert_amount_dc($debit_amount);
                                         echo "</td>";

                                         echo "<td align=\"right\">";
                                               //  echo convert_amount_dc(-$credit_amount);
                                         echo "</td>";

                                         echo "<td align=\"right\">";
                                                // echo convert_amount_dc($old_debit_amount);
                                         echo "</td>";

                                         echo "<td align=\"right\">";
                                                // echo convert_amount_dc(-$old_credit_amount);
                                         echo "</td>";
                                echo "</tr>";
              //          }//for
            //    }//if

           //     return array($credit_total, $debit_total, $old_credit_total, $old_debit_total);*/
       // }//function: loans_advances()

//////////////////////////@kanchan
function xml_creation($type,$ledg_id,$database,$name,$curr_year,$dr_total,$cr_total)
{
	$CI =& get_instance();
 	if($type == "Liability")
        {
        	$liability = new Balancesheet();
                $liability->init($ledg_id);
               	$total = $liability->total;
	}elseif($type == "Assets")
		{
                $asset = new Balancesheet();
                $asset->init($ledg_id);
                $total = $asset->total;
                }
//	echo"total".$total;
 	$type1 =$type."_Name";
        $doc = new DOMDocument();
        $doc->formatOutput = true;
        $acctpath= $this->upload_path1= realpath(BASEPATH.'../uploads/xml');
        $file_name="";
        $file_name=$type."-".$database."-".$curr_year.".xml";
        $tt=$acctpath."/".$file_name;
        if(file_exists($tt))
        {
        	$doc->preserveWhiteSpace = false;
                $doc->load($tt);
                $type = $doc->firstChild;
                $type1 = $doc->createElement($type1);
                $group_name = $doc->createElement('Group_Name');
                $textNode = $doc->createTextNode($name);
                $group_name->appendChild($textNode);
                $type1->appendChild($group_name);

                $amount1 = $doc->createElement('Dr_Amount');
                $textNode2 = $doc->createTextNode($dr_total);
                $amount1->appendChild($textNode2);
                $type1->appendChild($amount1);
		
		$amount2 = $doc->createElement('Cr_Amount');
		$textNode2 = $doc->createTextNode($cr_total);
		$amount2->appendChild($textNode2);
		$type1->appendChild($amount2);

                $group_id = $doc->createElement('Group_ID');
                $textNode1 = $doc->createTextNode($ledg_id);
                $group_id->appendChild($textNode1);
                $type1->appendChild($group_id);

                $type->appendChild($type1);
                $ttt=$doc->saveXML();
                $handle = fopen($tt, "w");
                fwrite($handle, $ttt);
                fclose($handle);
                }else{
			 $r = $doc->createElement( $type );
                         $doc->appendChild( $r );
                         $b = $doc->createElement( $type1 );
                         $group_name = $doc->createElement( "Group_Name" );
                         $group_name->appendChild($doc->createTextNode($name));
                         $b->appendChild( $group_name );

                         $amount1 = $doc->createElement( "Dr_Amount");
                         $amount1->appendChild($doc->createTextNode($dr_total));
                         $b->appendChild( $amount1 );
			
			 $amount2 = $doc->createElement("Cr_Amount");
			 $amount2->appendChild($doc->createTextNode($cr_total));
			 $b->appendChild($amount2);

                         $group_id = $doc->createElement('Group_ID');
                         $textNode1 = $doc->createTextNode($ledg_id);
                         $group_id->appendChild($textNode1);
                         $b->appendChild( $group_id );

                         $r->appendChild( $b );
                         $doc->save($tt);
                         $doc->saveXML();
                    }
        }
}//BalanceSheet.php
?>
