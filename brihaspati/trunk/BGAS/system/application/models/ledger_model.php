<?php
if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Ledger_model extends Model {

var $ledgers = array();
	function Ledger_model()
	{
		parent::Model();
	}

	function get_all_ledgers()
	{
		$options = array();
		$options[0] = "(Please Select):";
		//$this->db->from('ledgers')->order_by('code', 'asc');
		$this->db->from('ledgers')->order_by('name', 'asc');
		$ledger_q = $this->db->get();
		foreach ($ledger_q->result() as $row)
		{
			$cd = $row->code;
			$nme = $row->name;
			$des = $row->ledger_description;
			//if(substr($cd, 0, 2) == 10)
			if(substr($cd, 0, 2) == $this->get_account_code('Liabilities and Owners Equity')){			
				$name = $nme." - L";
				$name = $name.":".$des;
			}
			//if(substr($cd, 0, 2) == 20)
			if(substr($cd, 0, 2) == $this->get_account_code('Assets'))
			{
				$name = $nme." - A";
				$name = $name.":".$des;
			}
			//if(substr($cd, 0, 2) == 30)
			if(substr($cd, 0, 2) == $this->get_account_code('Incomes')){
				$name = $nme." - I";
				$name = $name.":".$des;
			}
			//if(substr($cd, 0, 2) == 40)
			if(substr($cd, 0, 2) == $this->get_account_code('Expenses')){
				$name = $nme." - E";
				$name = $name.":".$des;
			}

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
				echo $cd = $row->code;
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
		$options[0] = "(Please Select):";
		//$this->db->from('ledgers')->where('type', 1)->order_by('code', 'asc');
		$this->db->from('ledgers')->where('type', 1)->order_by('name', 'asc');
		$ledger_q = $this->db->get();
		foreach ($ledger_q->result() as $row)
		{
			$cd = $row->code;
                        $nme = $row->name;
                        $des = $row->ledger_description;
                        //if(substr($cd, 0, 2) == 10)
			if(substr($cd, 0, 2) == $this->get_account_code('Liabilities and Owners Equity'))
                          {
                                $name = $nme." - L";
                            	$name = $name.":".$des;
                        }
                        //if(substr($cd, 0, 2) == 20)
			if(substr($cd, 0, 2) == $this->get_account_code('Assets'))
                         {
                                $name = $nme." - A";
                         
                            $name = $name.":".$des;
                       }
                        //if(substr($cd, 0, 2) == 30)
			if(substr($cd, 0, 2) == $this->get_account_code('Incomes'))
                        {
                                $name = $nme." - I";
                            $name = $name.":".$des;
                        }
                        //if(substr($cd, 0, 2) == 40)
			if(substr($cd, 0, 2) == $this->get_account_code('Expenses'))
                         {
                                $name = $nme." - E";
                         
                            $name = $name.":".$des;
                       }
                        $options[$row->id] = $name;

		//	$options[$row->id] = $row->name;
		}
		return $options;
	}

     function get_all_ledgers_bankcash1()
        {
                $options = array();
                $options[0] = "(Please Select)";
                $this->db->from('ledgers')->where('code LIKE', '20%')->where('type', 1)->order_by('name', 'asc');
                $ledger_q = $this->db->get();
                foreach ($ledger_q->result() as $row)
                {
                        $new_id=$row->id."-".$row->name;
                        $options[$new_id]=$row->name;
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

    function isFundDeduct($ledger_id)
    {
    	$this->db->select('id')->from('fund_management')->where('fund_id',$ledger_id)->where('type','Revenue');
		$query_result = $this->db->get();
		if($query_result->num_rows() > 0)
			return true;
		else 
			return false;
    }

	function get_all_ledgers_nobankcash()
	{
		$options = array();
		$options[0] = "(Please Select) :";
		$this->db->from('ledgers')->where('type !=', 1)->order_by('name', 'asc');
		$ledger_q = $this->db->get();
		foreach ($ledger_q->result() as $row)
		{
			$cd = $row->code;
                        $nme = $row->name;
                        $des = $row->ledger_description;

                        //if(substr($cd, 0, 2) == 10)
			if(substr($cd, 0, 2) == $this->get_account_code('Liabilities and Owners Equity'))
            {
                                $name = $nme." - L";
                            $name = $name.":".$des;
             }
                        //if(substr($cd, 0, 2) == 20)
			if(substr($cd, 0, 2) == $this->get_account_code('Assets'))
              {
                                $name = $nme." - A";
                            $name = $name.":".$des;
               }
                        //if(substr($cd, 0, 2) == 30)
			if(substr($cd, 0, 2) == $this->get_account_code('Incomes'))
                {
                                $name = $nme." - I";
                            $name = $name.":".$des;
                 }
                        //if(substr($cd, 0, 2) == 40)
			if(substr($cd, 0, 2) == $this->get_account_code('Expenses'))
              {
                                $name = $nme." - E";
                                $name = $name.":".$des;
               }
                        $options[$row->id] = $name;

		//	$options[$row->id] = $row->name;
		}
		return $options;
	}

	function get_all_ledgers_reconciliation()
	{
		$options = array();
		$options[0] = "(Please Select):";
		$this->db->from('ledgers')->where('reconciliation', 1)->order_by('name', 'asc');
		$ledger_q = $this->db->get();
		foreach ($ledger_q->result() as $row)
		{
			$cd = $row->code;
                        $nme = $row->name;
                        $des = $row->ledger_description;
                        //if(substr($cd, 0, 2) == 10)
			if(substr($cd, 0, 2) == $this->get_account_code('Liabilities and Owners Equity'))
            {
                                $name = $nme." - L";
             					$name = $name.":".$des;
            }
                        //if(substr($cd, 0, 2) == 20)
			if(substr($cd, 0, 2) == $this->get_account_code('Assets'))
             {
                                $name = $nme." - A";
                                $name = $name.":".$des;
              }
                        //if(substr($cd, 0, 2) == 30)
			if(substr($cd, 0, 2) == $this->get_account_code('Incomes'))
             {
                                $name = $nme." - I";
                            $name = $name.":".$des;
              }
                        //if(substr($cd, 0, 2) == 40)
			if(substr($cd, 0, 2) == $this->get_account_code('Expenses'))
               {
                                $name = $nme." - E";
                                $name = $name.":".$des;
                }
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
		{
			return $ledger->name;
		}
		else
		{
			return "(Error)";
		}
	}

	//Program to get code of ledger from ledger id. added by @RAHUL
	function get_code($ledger_id)
        {
                $this->db->from('ledgers')->where('id', $ledger_id)->limit(1);
                $ledger_q = $this->db->get();
                if ($ledger = $ledger_q->row())
                {
                        return $ledger->code;
                }
                else
                {
                        return "(Error)";
                }
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

	function get_fund_capital($ledger_id)
	{
	       $this->db->select_sum('amount')->from('fund_management')->where('type', "Capital")->where('fund_id', $ledger_id);
		$total = $this->db->get();
		foreach($total->result() as $row){
			$balance = $row->amount;
		}
		return $balance;
	}

	function get_ledger_balance($ledger_id)
	{
		list ($op_bal, $op_bal_type) = $this->get_op_balance($ledger_id);

		$dr_total = $this->get_dr_total($ledger_id);
		$cr_total = $this->get_cr_total($ledger_id);
		$total = float_ops($dr_total, $cr_total, '-');
		if ($op_bal_type == "D")
		{
			$total = float_ops($total, $op_bal, '+');
		}
		else
		{
			$total = float_ops($total, $op_bal, '-');
		}
		return $total;
	}

	/* get ledger balance for selected date in current financial year*/ 
	function get_ledger_balance1($ledger_id)
	{
		list ($op_bal, $op_bal_type) = $this->get_op_balance($ledger_id);

		$dr_total = $this->get_dr_total1($ledger_id);
		$cr_total = $this->get_cr_total1($ledger_id);
		//echo $cr_total."==";
		$total = float_ops($dr_total, $cr_total, '-');

		if ($op_bal_type == "D")
			$total = float_ops($total, $op_bal, '+');
		else
			$total = float_ops($total, $op_bal, '-');
		return $total;
	}

	function get_closing_balance($ledger_id)
        {
                $this->load->library('session');
                $date1 = $this->session->userdata('date1');
                $date2 = $this->session->userdata('date2');

                list ($op_bal, $op_bal_type) = $this->get_op_closing_balance($ledger_id, $date1, $date2);

                $dr_total = $this->get_dr_total1($ledger_id);
                $cr_total = $this->get_cr_total1($ledger_id);
                //echo $cr_total."==";
                $total = float_ops($dr_total, $cr_total, '-');

                if ($op_bal_type == "D")
                        $total = float_ops($total, $op_bal, '+');
                else
                        $total = float_ops($total, $op_bal, '-');
                return $total;
        }


	/* get ledger balance for selected date in current financial year and with division of plan & nonplan @megha*/ 
	function get_ledger_balance2($ledger_id)
	{
		list ($op_bal, $op_bal_type) = $this->get_op_balance($ledger_id);

		$dr_total = $this->get_dr_total2($ledger_id);
		$cr_total = $this->get_cr_total2($ledger_id);

		$plan_dr_total = $dr_total['plan'];
		$non_plan_dr_total = $dr_total['nonplan'];
			//echo $non_plan_dr_total;
		
		$plan_cr_total = $cr_total['plan'];
		$non_plan_cr_total = $cr_total['nonplan'];
			//echo "== $non_plan_cr_total";
		
		$plan_total = float_ops($plan_dr_total, $plan_cr_total, '-');
		$non_plan_total = float_ops($non_plan_dr_total, $non_plan_cr_total, '-');
		//echo " $non_plan_total";
		
		if ($op_bal_type == "D")
			$non_plan_total = float_ops($non_plan_total, $op_bal, '+');
		else
			$non_plan_total = float_ops($non_plan_total, $op_bal, '-');

		$total = array('plan' => $plan_total,'nonplan'=> $non_plan_total );
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
		//echo "get_balancesheet_ledger_balance==>";
		//echo $ledger_id."==";
		list ($op_bal, $op_bal_type) = $this->get_op_balance($ledger_id);
		
		//print_r($this->get_op_balance($ledger_id));
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
		//echo $ledger_id."==";		
		$this->db->from('ledgers')->where('id', $ledger_id)->limit(1);
		$op_bal_q = $this->db->get();
		//print_r($op_bal_q->row());
		if ($op_bal = $op_bal_q->row())
		{
			//echo "<br>";
			//print_r($op_bal->op_balance."==".$op_bal->op_balance_dc);
			//return;
			return array($op_bal->op_balance, $op_bal->op_balance_dc);
		}
		else
			return array(0, "D");

	}

	function get_op_closing_balance($ledger_id, $from_date, $to_date)
	{
		$actual_date='';
		$flag=0;
		$fy_start_date=2015-04-01;
		$exp_from_date=explode("-",$from_date);
		$previous_date=$exp_from_date[2]-1;
                $month=$exp_from_date[1]-1;     
                $previous_month="0"."$month";
                        
		if($exp_from_date[2] == 01){
			if($previous_month == '01' || $previous_month == '05' || $previous_month == '07' || $previous_month == '08' || $previous_month == '10' || $previous_month == '12'){
				$actual_date=$exp_from_date[0]."-".$previous_month."-"."31";
			}elseif($previous_month == '02'){
				$day = "";
    				/*
      				since leap year falls ever 4 years so loop for 4 times 
    				*/
    				for($i=0; $i<4; $i++)
    				{
    					//get day timestamp for feburary 29 for this year
    					$day =  date("d", mktime(0, 0, 0, 2, 29, date("Y")+$i));	
    					/*
    					check if day equals 29. 
    					If day is 29 then it must be the leap year. if day is 01, then it not a leap year.
    					*/
    					if($day == 29)
    					{
    						$year = date("Y")+$i;
    						break;
    					}
    				}
				if( $year == $exp_from_date[0]){
					$actual_date=$exp_from_date[0]."-".$previous_month."-"."28";
				}else{
					$actual_date=$exp_from_date[0]."-".$previous_month."-"."29";
				}	
			}elseif($previous_month == '03'){	
				$flag=1;
				list ($op_bal, $op_bal_type) = $this->get_op_balance($ledger_id);	
				$actual_date="2015-04-01";
			}elseif($previous_month == '06' || $previous_month == '09' || $previous_month == '11' || $previous_month == '04'){
				$actual_date=$exp_from_date[0]."-".$previous_month."-"."30";
			}
		}elseif($exp_from_date[2] > '01' || $exp_from_date[2] < '31'){
			$previous_date=$exp_from_date[2]-1;
			$previous_date="0"."$previous_date";	
			$actual_date=$exp_from_date[0]."-".$exp_from_date[1]."-".$previous_date;
		}
		 if($flag != 1){
                 list ($op_bal, $op_bal_type) = $this->get_op_balance($ledger_id);
                }
		$this->db->select_sum('amount', 'drtotal')->from('entry_items')->join('entries', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->where('entry_items.dc', 'D');
                $this->db->where('date >=', $fy_start_date);
                $this->db->where('date <=', $actual_date);
                $dr_total_q = $this->db->get();
                if ($dr_total = $dr_total_q->row())
                        $dr_total=$dr_total->drtotal;
                else
                        $dr_total=0;
		$this->db->select_sum('amount', 'crtotal')->from('entry_items')->join('entries', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->where('entry_items.dc', 'C');
                $this->db->where('date >=', $fy_start_date);
                $this->db->where('date <=', $actual_date);
                $cr_total_q = $this->db->get();
                if ($cr_total = $cr_total_q->row())
                        $cr_total=$cr_total->crtotal;
                else
			$cr_total=0;

                $total = float_ops($dr_total, $cr_total, '-');

                if ($op_bal_type == "D")
                        $total = float_ops($total, $op_bal, '+');
                else
                        $total = float_ops($total, $op_bal, '-');
		$op_balance=abs($total);	
		if($total >= 0)
			$op_type="D";
		else 
			$op_type="C";
		return array($op_balance, $op_type);;
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
			$con = @mysql_connect($host_name, $db_username, $db_password);
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
		$con = @mysql_connect($host_name, $db_username, $db_password);
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

	function get_dr_total2($ledger_id)
	{
		$this->load->library('session');
		$date1 = $this->session->userdata('date1');
		$date2 = $this->session->userdata('date2');

		$this->db->select_sum('amount', 'drtotal')->from('entry_items')->join('entries', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->where('entry_items.dc', 'D')->where('entries.sanc_type','select');
		$this->db->where('date >=', $date1);
	    $this->db->where('date <=', $date2);
		$dr_total_q = $this->db->get();
		$dr_total = $dr_total_q->row();
		$select_dr_total = $dr_total->drtotal;

		$this->db->select_sum('amount', 'drtotal')->from('entry_items')->join('entries', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->where('entry_items.dc', 'D')->where('entries.sanc_type','non_plan');
		$this->db->where('date >=', $date1);
	    $this->db->where('date <=', $date2);
		$dr_total_q1 = $this->db->get();
		$dr_total1 = $dr_total_q1->row();
		$non_plan_dr_total = $dr_total1->drtotal;

		$this->db->select_sum('amount', 'drtotal')->from('entry_items')->join('entries', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->where('entry_items.dc', 'D')->where('entries.sanc_type','plan_sfc_scheme');
		$this->db->where('date >=', $date1);
	    $this->db->where('date <=', $date2);
		$dr_total_q3 = $this->db->get();
		$dr_total3 = $dr_total_q3->row();
		$specific_sch_dr_total = $dr_total3->drtotal;

		$this->db->select_sum('amount', 'drtotal')->from('entry_items')->join('entries', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->where('entry_items.dc', 'D')->where('entries.sanc_type','plan');
		$this->db->where('date >=', $date1);
	    $this->db->where('date <=', $date2);
		$dr_total_q2 = $this->db->get();
		$dr_total2 = $dr_total_q2->row();
		$plan_dr_total = $dr_total2->drtotal;

		$non_plan_dr_total = $non_plan_dr_total + $select_dr_total;

		//echo "nonplan dr total = $non_plan_dr_total";

		$dr_total = array('plan' => $plan_dr_total,'nonplan' => $non_plan_dr_total ,'specific_sch' => $specific_sch_dr_total);

		/*if( empty( $dr_total ) )
		{
		    return 0 ;
		}else{*/
			return $dr_total;
		//}
	}


	function get_cr_total2($ledger_id)
	{
		$this->load->library('session');
		$date1 = $this->session->userdata('date1');
		$date2 = $this->session->userdata('date2');

		$this->db->select_sum('amount', 'crtotal')->from('entry_items')->join('entries', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->where('entry_items.dc', 'C')->where('entries.sanc_type','select');
		$this->db->where('date >=', $date1);
	    $this->db->where('date <=', $date2);
		$cr_total_q = $this->db->get();
		$cr_total = $cr_total_q->row();
		$select_cr_total = $cr_total->crtotal;

		$this->db->select_sum('amount', 'crtotal')->from('entry_items')->join('entries', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->where('entry_items.dc', 'C')->where('entries.sanc_type','non_plan');
		$this->db->where('date >=', $date1);
	    $this->db->where('date <=', $date2);
		$cr_total_q1 = $this->db->get();
		$cr_total1 = $cr_total_q1->row();
		$non_plan_cr_total = $cr_total1->crtotal;

		$this->db->select_sum('amount', 'crtotal')->from('entry_items')->join('entries', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->where('entry_items.dc', 'C')->where('entries.sanc_type','plan');
		$this->db->where('date >=', $date1);
	    $this->db->where('date <=', $date2);
		$cr_total_q2 = $this->db->get();
		$cr_total2 = $cr_total_q2->row();
		$plan_cr_total = $cr_total2->crtotal;

		$this->db->select_sum('amount', 'crtotal')->from('entry_items')->join('entries', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->where('entry_items.dc', 'C')->where('entries.sanc_type','plan_sfc_scheme');
		$this->db->where('date >=', $date1);
	    $this->db->where('date <=', $date2);
		$cr_total_q3 = $this->db->get();
		$cr_total3 = $cr_total_q3->row();
		$specific_sch_cr_total = $cr_total3->crtotal;

		$non_plan_cr_total = $non_plan_cr_total + $select_cr_total;

		//echo "nonplan cr total = $non_plan_cr_total";		

		$cr_total = array('plan' => $plan_cr_total,'nonplan' => $non_plan_cr_total,'specific_sch' => $specific_sch_cr_total );

		/*if( empty( $cr_total ) )
		{
		    return 0 ;
		}else{*/
			return $cr_total;
		//}
	}


	function get_capital_exp_total($ledger_id)
	{

		$this->load->library('session');
        $date1 = $this->session->userdata('date1');
        $date2 = $this->session->userdata('date2');
		$non_plan_total = 0;
		$plan_total = 0;
		$specific_total =0;
		$name = $this->get_ledger_name($ledger_id);
		$this->db->select('name')->from('ledgers')->where('name LIKE','Grants'.'%');
		if (strpos($name,'UGC') !== false)
		{
			$this->db->where('name LIKE','%'.'UGC'.'%');	
		}elseif(strpos($name,'State Government') !== false) {

			$this->db->where('name LIKE','%'.'State Government'.'%');
		}elseif(strpos($name,'Government of India') !== false){

			$this->db->where('name LIKE','%'.'Government of India'.'%');
		}

		$this->db->where('code LIKE','10'.'%');
		$query_result = $this->db->get();
		$query_result1 = $query_result->result();
		foreach($query_result1 as $row)
		{
			$fundname = $row->name;
			$this->db->select('entry_items_id,amount')->from('fund_management')->where('fund_name',$fundname);
			$this->db->where('type','Capital');
			$this->db->where('date >=', $date1);
            $this->db->where('date <=', $date2);
			$query_result2 = $this->db->get();

			foreach($query_result2->result() as $row1)
			{
				$entry_items_id = $row1->entry_items_id;
				$this->db->select('entries.sanc_type as sanc_type,entry_items.dc as entry_items_dc');
            	$this->db->from('entries')->join('entry_items', 'entries.id = entry_items.entry_id')->where('entry_items.id', $entry_items_id);	
            	$query_result3 = $this->db->get();
            	foreach($query_result3->result() as $row2)
				{
					$sanc_type = $row2->sanc_type;
					$dc = $row2->entry_items_dc;
					if($sanc_type == 'select' || $sanc_type == 'non_plan'){
						$amount = $row1->amount;
						if($dc == "D"){
							 $non_plan_total = $non_plan_total + $amount;
                    				}else{
							 $non_plan_total = $non_plan_total + $amount;
						}
					}elseif($sanc_type == 'plan'){
						$amount = $row1->amount;
						if($dc == "D"){
							 $plan_total = $plan_total + $amount;
                                                }else{
							 $plan_total = $plan_total + $amount;
                                                }

					}elseif($sanc_type == 'plan_sfc_scheme'){
						$amount = $row1->amount;
						$specific_total = $specific_total + $amount;
					}
				}
			}
		}

		//echo $non_plan_total;
		$capital_total = array('plan' => $plan_total,'nonplan' => $non_plan_total,'specific_sch' => $specific_total);
		return $capital_total;
	}

	function get_revenue_exp_total($ledger_id)
	{

		$this->load->library('session');
        $date1 = $this->session->userdata('date1');
        $date2 = $this->session->userdata('date2');
		$non_plan_total = 0;
		$plan_total = 0;
		$specific_total =0;
		$name = $this->get_ledger_name($ledger_id);
		$this->db->select('name')->from('ledgers')->where('name LIKE','Grants'.'%');
		if (strpos($name,'UGC') !== false)
		{
			$this->db->where('name LIKE','%'.'UGC'.'%');	
		}elseif(strpos($name,'State Government') !== false) {

			$this->db->where('name LIKE','%'.'State Government'.'%');
		}elseif(strpos($name,'Government of India') !== false){

			$this->db->where('name LIKE','%'.'Government of India'.'%');
		}

		$this->db->where('code LIKE','10'.'%');
		$query_result = $this->db->get();
		$query_result1 = $query_result->result();
		
		foreach($query_result1 as $row)
		{
			$fundname = $row->name;
			$this->db->select('entry_items_id,amount')->from('fund_management')->where('fund_name',$fundname);
			$this->db->where('type','Revenue');
			$this->db->where('date >=', $date1);
            $this->db->where('date <=', $date2);
			$query_result2 = $this->db->get();

			foreach($query_result2->result() as $row1)
			{
				//print_r($row1);
				$entry_items_id = $row1->entry_items_id;
				$this->db->select('entries.sanc_type as sanc_type,entry_items.dc as entry_items_dc');
            	$this->db->from('entries')->join('entry_items', 'entries.id = entry_items.entry_id')->where('entry_items.id', $entry_items_id);	
            	$query_result3 = $this->db->get();
            	foreach($query_result3->result() as $row2)
				{
					$sanc_type = $row2->sanc_type;
					$dc = $row2->entry_items_dc;
					if($sanc_type == 'select' || $sanc_type == 'non_plan'){
						$amount = $row1->amount;
						if($dc == "D"){
                                                         $non_plan_total = $non_plan_total + $amount;
                                                }else{
                                                         $non_plan_total = $non_plan_total + $amount;
                                                }

					}elseif($sanc_type == 'plan'){
						$amount = $row1->amount;
						if($dc == "D"){
                                                         $plan_total = $plan_total + $amount;
                                                }else{
                                                         $plan_total = $plan_total + $amount;
                                                }
						 
					}elseif($sanc_type == 'plan_sfc_scheme'){
						$amount = $row1->amount;
						$specific_total = $specific_total + $amount;
					}
				}
			}
		}

		$revenue_total = array('plan' => $plan_total,'nonplan' => $non_plan_total ,'specific_sch' => $specific_total);
		return $revenue_total;

	}

	function get_schedule10_data($ledger_id, $type){

        $op_balance = $this->get_op_balance($ledger_id);
	//print_r($op_balance);
        $dr_total = $this->get_dr_total2($ledger_id);
        $cr_total = $this->get_cr_total2($ledger_id);
        $capital_total = $this->get_capital_exp_total($ledger_id);
        $revenue_total = $this->get_revenue_exp_total($ledger_id);
       // print_r($cr_total);
        if($type == 'plan'){
        	$plan = array();
	        $plan_total1 = $op_balance[0]+ $cr_total['plan'];
	        $plan_total2  = $plan_total1 - $dr_total['plan'];
	        $plan_total3 = $plan_total2 - $capital_total['plan'];
	        $plan_total4 = $plan_total3 - $revenue_total['plan'];

	        $plan[0] = $op_balance[0];
	        $plan[1] = $cr_total['plan'];
	        $plan[2] = $plan_total1;
	        $plan[3] = $dr_total['plan'];
	        $plan[4] = $plan_total2;
	        $plan[5] = $capital_total['plan'];
	        $plan[6] = $plan_total3;
	        $plan[7] = $revenue_total['plan'];
	        $plan[8] = $plan_total4;

	        return $plan;
	    }elseif($type == 'nonplan'){
	        $nonplan = array();
	        $nonplan_total1 = $op_balance[0]+ $cr_total['nonplan'];
	        $nonplan_total2  = $nonplan_total1 - $dr_total['nonplan'];
	        $nonplan_total3 = $nonplan_total2 - $capital_total['nonplan'];
	        $nonplan_total4 = $nonplan_total3 - $revenue_total['nonplan'];

	        $nonplan[0] = $op_balance[0];
	        $nonplan[1] = $cr_total['nonplan'];
	        $nonplan[2] = $nonplan_total1;
	        $nonplan[3] = $dr_total['nonplan'];
	        $nonplan[4] = $nonplan_total2;
	        $nonplan[5] = $capital_total['nonplan'];
	        $nonplan[6] = $nonplan_total3;
	        $nonplan[7] = $revenue_total['nonplan'];
	        $nonplan[8] = $nonplan_total4;

	        return $nonplan;
	    }elseif($type == 'specific_sch'){
	    	$specific_sch = array();
	    	$specific_total1 = 0 + $cr_total['specific_sch'];
	        $specific_total2  = $specific_total1 - $dr_total['specific_sch'];
	        $specific_total3 = $specific_total2 - $capital_total['specific_sch'];
	        $specific_total4 = $specific_total3 - $revenue_total['specific_sch'];

	    	$specific_sch[0] = 0;
	    	$specific_sch[1] = $cr_total['specific_sch'];
	    	$specific_sch[2] = $specific_total1;
	    	$specific_sch[3] = $dr_total['specific_sch'];
	    	$specific_sch[4] = $specific_total2;
	    	$specific_sch[5] = $capital_total['specific_sch'];
	    	$specific_sch[6] = $specific_total3;
	    	$specific_sch[7] = $revenue_total['specific_sch'];
	    	$specific_sch[8] = $specific_total4;
	    	return $specific_sch;
	    }
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
		$date2 = $this->session->userdata('date2');
		if($date1==NULL && $date2==NULL){
                	$date1=2016-04-01;
			$date2=2016-04-01;
                }
		else{
		$date=explode("-",$date1);
		$old_year1 = $date[0]-1;
		$from_date = $old_year1."-".$date[1]."-".$date[2];

		/* get to date of previous year */
		$date2 = $this->session->userdata('date2');
		$date1=explode("-",$date2);
		$old_year1 = $date1[0]-1;
		$to_date = $old_year1."-".$date1[1]."-".$date1[2];
		/* database connectivity for getting previous year debit amount */
		$con = @mysql_connect($host_name, $db_username, $db_password);
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
		$date2 = $this->session->userdata('date2');

		if($date1==NULL && $date2==NULL){
                        $date1=2016-04-01;
                        $date2=2016-04-01;
                }else{
		$date=explode("-",$date1);
		$old_year1 = $date[0]-1;
		$from_date = $old_year1."-".$date[1]."-".$date[2];
		/* get to date of previous year */
		$date2 = $this->session->userdata('date2');
		$date1 = explode("-",$date2);
		$old_year1 = $date1[0]-1;
		$to_date = $old_year1."-".$date1[1]."-".$date1[2];

		/* database connectivity for getting previous year debit amount */
		$con = @mysql_connect($host_name, $db_username, $db_password);
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
		{	$dr_total->drtotal;	
			return $dr_total->drtotal;
		}
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

		$con = @mysql_connect($host_name, $db_username, $db_password);
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
		{
			$cr_total->crtotal;
			return $cr_total->crtotal;
		}
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

		$con = @mysql_connect($host_name, $db_username, $db_password);
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
		{
			return $dr_total->drtotal;
		}	
		else
		{
			return 0;
		}
	}

	/* Return credit total as positive value */
	function get_cr_total($ledger_id)
	{
		$this->db->select_sum('amount', 'crtotal')->from('entry_items')->join('entries', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->where('entry_items.dc', 'C');
		$cr_total_q = $this->db->get();
		if ($cr_total = $cr_total_q->row())
		{
			return $cr_total->crtotal;
		}
		else
		{
			return 0;
		}
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

	 function get_ledger_id($code)
        {
                $this->db->select('id');
                $this->db->from('ledgers')->where('code =', $code);
                $ledger_result = $this->db->get();
                if ($ledger = $ledger_result->row())
                        return $ledger->id;
                else
                        return 0;
        }

        function get_id($name)
        {
                $this->db->select('id');
                $this->db->from('ledgers')->where('name =', $name);
                $ledger_result = $this->db->get();
                if ($ledger = $ledger_result->row())
                        return $ledger->id;
                else
                        return 0;
        }

        function get_ledger_name($id)
        {
                $this->db->select('name');
                $this->db->from('ledgers')->where('id =', $id);
                $ledger_result = $this->db->get();
                if ($ledger = $ledger_result->row())
                        return $ledger->name;
                else
                        return 0;
        }
    function get_ledger_description($code){
    	$this->db->select('ledger_description');
        $this->db->from('ledgers')->where('code =', $code);
        $ledger_result = $this->db->get();
        if ($ledger = $ledger_result->row())
                return $ledger->ledger_description;
        else
                return 0;
    }

	  function get_group_id($code)
        {
                $this->db->select('id');
                $this->db->from('groups')->where('code =', $code);
                $group_result = $this->db->get();
                if ($group = $group_result->row())
                        return $group->id;
                else
                        return 0;
        }

	function get_group_name($id)
	{
		$this->db->select('name')->from('groups')->where('id' , $id);
		$group_result = $this->db->get();
		if($group = $group_result->row())
			return $group->name;
		else
			return 0;
	}

	function get_asset_amount($id)
        {
                 $i=0;
                 $tot_value='';
                 $tot_cur_value='';
                 $tot_amt='';
		$net_dep_amount='';
		$amount='';
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
				$years=$day/365;
                        	$check_year=is_int($years);//check year is in integer
                        	$get_asset_used_years=explode(".", $years);
                        	$day_by_year=$get_asset_used_years[0]*365;
                        	$tot_day=$day-$day_by_year;
				if($get_asset_used_years[0]=='0'){
					$net_dep_amount=$IRD_Rate*($tot_day/365)*($ERPMIM_Depreciation_Percentage/100);

				}else{
					for($i=1; $i<=$get_asset_used_years[0]; $i++){
                                        	if($i == 1){
                                                	$val=($IRD_Rate*$ERPMIM_Depreciation_Percentage[0])/100;
                                                	$amount=$row->cost-$val;
                                                	$depepreciation_on_day=$IRD_Rate*($tot_day/365)*($ERPMIM_Depreciation_Percentage[0]/100);
                                                 	$net_dep_amount= $net_dep_amount+$val;
                                        	}else{
                                                	$val=($amount*$ERPMIM_Depreciation_Percentage[0])/100;
                                                	$depepreciation_on_day=$amount*($tot_day/365)*($ERPMIM_Depreciation_Percentage[0]/100);
                                                	$net_amount= $depepreciation_on_day+$val;
                                                	$amount=$amount-$net_amount;
                                                	$net_dep_amount= $net_dep_amount+$val;
                                        	}
					}
				}	
                            }
			 $tot_cur_value=$IRD_Rate-$net_dep_amount;
                    }

                $value1=$IRD_Rate .'#'. $net_dep_amount .'#'. $tot_cur_value;
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
//                print_r(sizeof($heads));
                $options = array();
                $options[0] ="(Please Select):";
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
                                                $des = $row->ledger_description;
                                                if(substr($cd, 0, 2) == 10){
                                                        $name = $nme." - L";
                                                        $name = $name.":".$des;
                                                }
                                                if(substr($cd, 0, 2) == 20){
                                                        $name = $nme." - A";
                                                        $name = $name.":".$des;
                                                }
                                                if(substr($cd, 0, 2) == 30){
                                                        $name = $nme." - I";
                                                        $name = $name.":".$des;
                                                }
                                                if(substr($cd, 0, 2) == 40)
                                                {
                                                        $name = $nme." - E";
                                                        $name = $name.":".$des;
                                                }
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
                                                $des = $row->ledger_description;
                                                if(substr($cd, 0, 2) == 10)
                                                {
                                                        $name = $nme." - L";
                                                        $name = $name.":".$des;
                                                }
                                                if(substr($cd, 0, 2) == 20){
                                                        $name = $nme." - A";
                                                        $name = $name.":".$des;
                                                }
                                                if(substr($cd, 0, 2) == 30){
                                                        $name = $nme." - I";
                                                        $name = $name.":".$des;
                                                }
                                                if(substr($cd, 0, 2) == 40){
                                                        $name = $nme." - E";
                                                        $name = $name.":".$des;
                                                }

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
		$query1= $query->result();
	//	print_r($query1);
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
				}else{
					$funds[$ledger->id] = $ledger->name;
				}
			}
		}

		return $funds;
	
	}
	function get_fund_ledgers()
	{
		$funds = array();
                $funds[0] = 'Select Fund';
		$this->db->select('name, id, group_id, code')->order_by('name', 'asc');
                $this->db->from('ledgers');
		$this->db->like('code', '10', 'after');
		$this->db->not_like('code', '1004', 'after');
		$this->db->not_like('code', '1005', 'after');
		$this->db->not_like('code', '1006', 'after');
		$query = $this->db->get();
		if($query->num_rows() > 0)
		{
                        foreach($query->result() as $ledger)
			{
				 $funds[$ledger->id] = $ledger->name;
			}
		}
		return $funds;
	}

	/* code for getting fund ledgers for all four type of formats @megha */
	function get_fund_ledgers1()
	{
		$funds = array();
        $funds[0] = 'Select Fund';

       	$capital_code = $this->get_account_code('Capital Funds');
       	$designated_ear_endowmt = $this->get_account_code('Designated-Earmarked/Endowment Funds');
       	$designated_earmarked = $this->get_account_code('Designated-Earmarked Funds');
       	$corpus_code = $this->get_account_code('Corpus');
       	$own_funds = $this->get_account_code('Own Funds');
       	$reserves_surplus = $this->get_account_code('Reserves and Surplus');
       	$earmarked = $this->get_account_code('Earmarked');
       	$endowment = $this->get_account_code('Endowment Funds-L');

       	if ($own_funds == "")
       	{
       		// for mhrd old format 
       		if($designated_earmarked != "")
       		{
       			$this->db->select('name, id, group_id, code')->order_by('name', 'asc');
                $this->db->from('ledgers');
				$this->db->like('code', '10', 'after');
				$this->db->not_like('code', '1003', 'after');
				$this->db->not_like('code', '1004', 'after');
				$this->db->not_like('code', '1005', 'after');
				$this->db->not_like('code', '1006', 'after');
				$this->db->not_like('code', '100102', 'after');
				//$this->db->not_like('code', '100101', 'after');
				$query = $this->db->get();
				if($query->num_rows() > 0)
				{
		            foreach($query->result() as $ledger){
						 $funds[$ledger->id] = $ledger->name;
					}
				}
       		}else{
       			$this->db->select('name, id, group_id, code')->order_by('name', 'asc');
                $this->db->from('ledgers');
				$this->db->like('code', '10', 'after');
				//$this->db->not_like('code', '1003', 'after');
				//$this->db->not_like('code', '1004', 'after');
				$this->db->not_like('code', '1005', 'after');
				$this->db->not_like('code', '1006', 'after');
				$this->db->not_like('name', 'Balance of net income/expenditure transferred from I/E Account');
				$query = $this->db->get();
				if($query->num_rows() > 0)
				{
		            foreach($query->result() as $ledger){
						 $funds[$ledger->id] = $ledger->name;
					}
				}
       		}
       	}else{
       		// for corporate format
       		$this->db->select('name, id, group_id, code')->order_by('name', 'asc');
            	$this->db->from('ledgers');
			$this->db->like('code', '10', 'after');
			//$this->db->not_like('code', '1003', 'after');
			//$this->db->not_like('code', '1004', 'after');
			$this->db->not_like('code', '1005', 'after');
			$this->db->not_like('code', '1006', 'after');
			$this->db->not_like('code', '1007', 'after');
			$query = $this->db->get();
			if($query->num_rows() > 0)
			{
	            foreach($query->result() as $ledger){
					 $funds[$ledger->id] = $ledger->name;
				}
			}
       	}
		return $funds;
	}

        function get_codewise_ledgers()
        {
                $options = array();
                $options[0] = 'Please Select';
                //$this->db->from('ledgers');
                $this->db->from('ledgers')->select('id,code,name')->where('code LIKE', '40%')->order_by('name', 'asc');
                $ledger_q = $this->db->get();
              //  $counter = 0;
                foreach ($ledger_q->result() as $row)
                {
                        $new_id = "$row->code"."."."$row->name";
                        $options[$new_id]=$row->name;

                }
                return $options;
        }
	
	//This function returns name of ledger according to its code. added by @RAHUL
	function get_idwise_ledgers($legd_id)
        {
                $this->db->from('ledgers')->where('code', $legd_id)->limit(1);
                $sparty_q = $this->db->get();
                if ($pname = $sparty_q->row())
                        return $pname->id;
                else
                        return;
        }
	
	//This function returns id of fund according to its name. added by @RAHUL
	function get_funding_id_wise_ledgers($fu_id)
        {
                $this->db->from('ledgers')->where('name', $fu_id)->limit(1);
                $spa_rty_q = $this->db->get();
                if ($pname = $spa_rty_q->row())
                        return $pname->id;
                else
                        return;
        }

	function startsWith($str1, $str2)
        {
                return !strncmp($str1, $str2, strlen($str2));
        }

	function isFund($ledger_code){
                $flag = false;
                $income_code = $this->get_account_code('Liabilities and Owners Equity');

                $general_code = $this->get_account_code('General Funds');
                $capital_code = $this->get_account_code('Capital Funds');

                $corpus_code = $this->get_account_code('Corpus');
                $reserve_code = $this->get_account_code('General Reserve');
                $earn_code = $this->get_account_code('Designated-Earmarked Funds');
//                $this->db->from('ledgers');
  //              $this->db->like('code', $income_code, 'after');
    //            $query = $this->db->get();
      //          if($query->num_rows() > 0){
        //                foreach($query->result() as $ledger){
			if($this->startsWith($ledger_code, $income_code)){
                                if(($general_code != '') &&  ($capital_code != '')){
                                        if($this->startsWith($ledger_code, $general_code) || $this->startsWith($ledger_code, $capital_code)){
                                                $flag = true;
                                        }
                                }elseif(($corpus_code != '') && ($reserve_code != '') && ($earn_code != '')){
                                        if($this->startsWith($ledger_code, $corpus_code) || $this->startsWith($ledger_code, $reserve_code) || $this->startsWith($ledger_code, $earn_code)){
                                                $flag = true;
                                        }
                                }
                        }
               //}
                return $flag;

	}
	//method for aggregation.
        /* get ledger balance for balancesheet in selected date in current financial year for aggregate */
        function get_balancesheet_ledger_balance_agg($ledger_id,$accname)
        {
			
		//echo "account name in get_balancesheet_ledger_balance_agg acc==>".$accname;
		//echo "<br>account name in get_balancesheet_ledger_balance_agg id==>".$ledger_id;
		//echo $ledger_id."==";
                list ($op_bal, $op_bal_type) = $this->get_op_balance_agg($ledger_id,$accname);
		//print_r($this->get_op_balance_agg($ledger_id,$accname));
                $dr_total = $this->get_balancesheet_dr_total_agg($ledger_id,$accname);
                $cr_total = $this->get_balancesheet_cr_total_agg($ledger_id,$accname);
                $total = float_ops($dr_total, $cr_total, '-');
                if ($op_bal_type == "D"){
                        $total = float_ops($total, $op_bal, '+');
                }else {
                        $total = float_ops($total, $op_bal, '-');
                }

                return $total;
        }

        function get_op_balance_agg($ledger_id,$accname)
        {
		//echo "Acc Name in get_op_balance_agg".$accname;
		$CI =& get_instance();
                $db1=$CI->load->database('login', TRUE);
                $db1->from('bgasAccData')->where('dblable', $accname);
                $accdetail = $db1->get();
                //print_r(sizeof($accdetail->result()));
                foreach ($accdetail->result() as $row)
			
                {
			
                        $databasehost=$row->hostname;
                        $databasename= $row->databasename;
                        $databaseport=$row->port;
                        $databaseusername=$row->uname;
                        $databasepassword=$row->dbpass;
			//echo "get_op_balance_agg===>".$databasehost.$databasename.$databaseport.$databaseusername.$databasepassword;
                }
                $new_link = @mysql_connect($databasehost . ':' . $databaseport, $databaseusername, $databasepassword);
                if ($new_link)
                {
                        $db_selected = mysql_select_db($databasename, $new_link);
                        if ($db_selected) {

//                        }
//                }
		//echo $ledger_id."==";
                $query = sprintf("SELECT * from ledgers where id=$ledger_id limit 1");
                $result = mysql_query($query);
                if (!$result) {
	                $message  = 'Invalid query: ' . mysql_error() . "\n";
                        $message .= 'Whole query: ' . $query;
                        die($message);
                }

		if($result != ''){
                	while($row = mysql_fetch_assoc($result))
                        {
				
                        	$op_balance = array($row['op_balance'], $row['op_balance_dc']);
				//echo "<br>";
				//print_r($row['op_balance']."==".$row['op_balance_dc']);
                                return $op_balance;
                        }
		}
		}
                }
        }
	
        /* Return debit total of balancesheet of selected date as positive value */
        function get_balancesheet_dr_total_agg($ledger_id,$accname)
        {
		$db_name ='';
                $db_username ='';
                $db_password ='';
                $host_name ='';
                $port ='';
		$db_name ='';

                $this->load->library('session');
		//echo "Date ===>";
                $date1 = $this->session->userdata('date1');
                $date2 = $this->session->userdata('date2');

                $CI =& get_instance();
                $db1=$CI->load->database('login', TRUE);
                $db1->from('bgasAccData')->where('dblable', $accname);
                $db_name_q = $db1->get();
		//$db1->close();
		foreach ($db_name_q->result() as $row)
                {
                        $db_name = $row->databasename;
                        $db_username = $row->uname;
                        $db_password = $row->dbpass;
                        $host_name = $row->hostname;
                        $port = $row->port;
                }
                $con = @mysql_connect($host_name, $db_username, $db_password);
                $op_balance = array();
                if($con){
                        $value = mysql_select_db($db_name, $con);
                        $id = mysql_real_escape_string($ledger_id);
                        //$abc = "select entry_items.entry_id, sum(amount)from entry_items INNER JOIN entries ON entry_items.entry_id = entries.id where entry_items.ledger_id = '$id' and entry_items.dc = 'D' and entries.date >= '$date1' and entries.date <= '$date2'";
                        $abc = "select entry_items.entry_id, sum(amount)from entry_items INNER JOIN entries ON entry_items.entry_id = entries.id where entry_items.ledger_id = '$id' and entry_items.dc = 'D'";
                        $val = mysql_query($abc);
                        if($val != ''){
                                while($row = mysql_fetch_assoc($val))
                                {
                                        $dr_total = $row['sum(amount)'];
                                        mysql_close($con);
					$dr_total;
                                        return $dr_total;
                                }
                        }
                }


//	$abc = "select entry_items.entry_id, sum(amount)from entry_items INNER JOIN entries ON entry_items.entry_id = entries.id where entry_items.ledger_id = '$id' and entry_items.dc = 'D' and entries.date >= '$date1' and entries.date <= '$date2'";
	
		
              /*  $this->db->select_sum('amount', 'drtotal')->from('entry_items')->join('entries', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->where('entry_items.dc', 'D');
                $this->db->where('date >=', $date1);
                $this->db->where('date <=', $date2);
                $dr_total_q = $this->db->get();
                if ($dr_total = $dr_total_q->row())
                        return $dr_total->drtotal;
                else
                        return 0;*/
		
        }

        /* Return credit total of balancesheet of selected date as positive value */
        function get_balancesheet_cr_total_agg($ledger_id,$accname)
        {
                $this->load->library('session');
                $date2 = $this->session->userdata('date2');
                $date1 = $this->session->userdata('date1');
                $db_name ='';
                $db_username ='';
                $db_password ='';
                $host_name ='';
                $port ='';
                $CI =& get_instance();
                $db1=$CI->load->database('login', TRUE);
                $db1->from('bgasAccData')->where('dblable', $accname);
                $db_name_q = $db1->get();
                //$db1->close();
                foreach ($db_name_q->result() as $row)
                {
                        $db_name = $row->databasename;
                        $db_username = $row->uname;
                        $db_password = $row->dbpass;
                        $host_name = $row->hostname;
                        $port = $row->port;
                }
                $con = @mysql_connect($host_name, $db_username, $db_password);
                $op_balance = array();
                if($con){
                        $value = mysql_select_db($db_name, $con);
                        $id = mysql_real_escape_string($ledger_id);
                        //$abc = "select entry_items.entry_id, sum(amount)from entry_items INNER JOIN entries ON entry_items.entry_id = entries.id where entry_items.ledger_id = '$id' and entry_items.dc = 'C' and entries.date >= '$date1' and entries.date <= '$date2'";
                        $abc = "select entry_items.entry_id, sum(amount)from entry_items INNER JOIN entries ON entry_items.entry_id = entries.id where entry_items.ledger_id = '$id' and entry_items.dc = 'C'";
                        $val = mysql_query($abc);
                        if($val != ''){
                                while($row = mysql_fetch_assoc($val))
                                {
					
                                        $cr_total = $row['sum(amount)'];
					//echo "||".$cr_total;
                                        mysql_close($con);
                                        return $cr_total;
                                }
                        }
                }


/*                $this->db->select_sum('amount', 'crtotal')->from('entry_items')->join('entries', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->where('entry_items.dc', 'C');
                $this->db->where('date >=', $date1);
                $this->db->where('date <=', $date2);
                $cr_total_q = $this->db->get();
                if ($cr_total = $cr_total_q->row())
                        return $cr_total->crtotal;
                else
                        return 0;
*/
		
	}
        /* get ledger balance for selected date in current financial year*/
        function get_ledger_balance1_agg($ledger_id,$accname)
        {
                list ($op_bal, $op_bal_type) = $this->get_op_balance_agg($ledger_id,$accname);

                $dr_total = $this->get_dr_total1_agg($ledger_id,$accname);
		//echo "==";
                $cr_total = $this->get_cr_total1_agg($ledger_id,$accname);
                $total = float_ops($dr_total, $cr_total, '-');

                if ($op_bal_type == "D")
                        $total = float_ops($total, $op_bal, '+');
                else
                        $total = float_ops($total, $op_bal, '-');
                return $total;
        }

        /* Return debit total of selected date in current financial year as positive value */
        function get_dr_total1_agg($ledger_id,$accname)
        {
                $this->load->library('session');
		$CI =& get_instance();
		$db1=$CI->load->database('login', TRUE);
                $db1->from('bgasAccData')->where('dblable', $accname);
                $db_name_q = $db1->get();
                //$db1->close();
                foreach ($db_name_q->result() as $row)
                {
                        $db_name = $row->databasename;
                        $db_username = $row->uname;
                        $db_password = $row->dbpass;
                        $host_name = $row->hostname;
                        $port = $row->port;
                }
                $con = @mysql_connect($host_name, $db_username, $db_password);
                $op_balance = array();
                if($con){
                        $value = mysql_select_db($db_name, $con);
                        $id = mysql_real_escape_string($ledger_id);
                        $abc = "select entry_items.entry_id, sum(amount)from entry_items INNER JOIN entries ON entry_items.entry_id = entries.id where entry_items.ledger_id = '$id' and entry_items.dc = 'D' ";
                        $val = mysql_query($abc);
                        if($val != ''){
                                while($row = mysql_fetch_assoc($val))
                                {

                                        $dr_total = $row['sum(amount)'];

                                        //mysql_close($con);
                                        return $dr_total;
                                }
                        }
                }
	/*	
                $this->db->select_sum('amount', 'drtotal')->from('entry_items')->join('entries', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->where('entry_items.dc', 'D');
                $dr_total_q = $this->db->get();
                if ($dr_total = $dr_total_q->row())
                        return $dr_total->drtotal;
                else
                        return 0;
	*/
        }
        /* Return credit total of selected date as positive value in current financial year*/
        function get_cr_total1_agg($ledger_id,$accname)
        {
                $this->load->library('session');
                /*$date1 = $this->session->userdata('date1');
                $date2 = $this->session->userdata('date2');
                $this->db->select_sum('amount', 'crtotal')->from('entry_items')->join('entries', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id)->where('entry_items.dc', 'C');
                $this->db->where('date >=', $date1);
                $this->db->where('date <=', $date2);
                $cr_total_q = $this->db->get();
                if ($cr_total = $cr_total_q->row())
                        return $cr_total->crtotal;
                else
                        return 0;
		*/
                $CI =& get_instance();
                $db1=$CI->load->database('login', TRUE);
                $db1->from('bgasAccData')->where('dblable', $accname);
                $db_name_q = $db1->get();
                //$db1->close();
                foreach ($db_name_q->result() as $row)
                {
                        $db_name = $row->databasename;
                        $db_username = $row->uname;
                        $db_password = $row->dbpass;
                        $host_name = $row->hostname;
                        $port = $row->port;
                }
                $con = @mysql_connect($host_name, $db_username, $db_password);
                $op_balance = array();
                if($con){
                        $value = mysql_select_db($db_name, $con);
                        $id = mysql_real_escape_string($ledger_id);
                        $abc = "select entry_items.entry_id, sum(amount)from entry_items INNER JOIN entries ON entry_items.entry_id = entries.id where entry_items.ledger_id = '$id' and entry_items.dc = 'C' ";

                        $val = mysql_query($abc);
                        if($val != ''){
                                while($row = mysql_fetch_assoc($val))
                                {

                                        $cr_total = $row['sum(amount)'];

                                        //mysql_close($con);
                                        return $cr_total;
                                }
                        }
                }

        }
	

	function isExpense($ledger_code){
		$flag = false; 
		$expense_code = $this->get_account_code('Expenditure');
		if($this->startsWith($ledger_code, $expense_code))
			$flag = true;

		return $flag;
	}

	function isAsset($ledger_code){
		$flag = false;
		$asset = $this->get_account_code('Application of Funds');
		if($this->startsWith($ledger_code, $asset))
			$flag = true;

		return $flag;
	}

	function get_type($ledger_id, $entry_id){
		$type = '';
		$this->db->select('type');
		$this->db->from('fund_management');
		$this->db->where('fund_id', $ledger_id);
		$this->db->where('entry_items_id', $entry_id);
		$type_query = $this->db->get();
		if($type_query->num_rows() > 0){
			$type_row = $type_query->row();
			$type = $type_row->type;
		}
		if($type == 'Revenue')
                        $type = 'Revenue Expenditure';
                elseif($type == 'Capital')
                        $type = 'Capital Expenditure';
                elseif($type == 'Accru')
                        $type = 'Accrued Income';
                elseif($type == 'Earn')
                        $type = 'Earned Income';

		return $type;	
	}

	function get_type1($entry_id){
                $type = '';
		$name ="";
                $this->db->select('type,fund_name');
                $this->db->from('fund_management');
                $this->db->where('entry_items_id', $entry_id);
                $type_query = $this->db->get();
                if($type_query->num_rows() > 0){
                        $type_row = $type_query->row();
                        $type = $type_row->type;
			$name =$type_row->fund_name;
                }
                if($type == 'Revenue')
                        $type = 'Revenue Expenditure';
                elseif($type == 'Capital')
                        $type = 'Capital Expenditure';
                elseif($type == 'Accru')
                        $type = 'Accrued Income';
                elseif($type == 'Earn')
                        $type = 'Earned Income';
		$new_id = $type."#".$name;
                return $new_id; 
        }


	function isFixedAsset($ledger_code){
		$flag = false;
                $fixed_asset_code = $this->get_account_code('Fixed Assets');
                if($this->startsWith($ledger_code, $fixed_asset_code))
                        $flag = true;

                return $flag;
	}

	
	
         function get_opp_tag_entry_name($entry_id, $entry_type_id)
        {
                // Selecting whether to show debit side Ledger or credit side Ledger 
                $current_entry_type = entry_type_info($entry_type_id);
                $ledger_type = 'C';

                if ($current_entry_type['bank_cash_ledger_restriction'] == 3)
                        $ledger_type = 'D';

                $this->db->select('ledgers.name as name');
                $this->db->from('entry_items')->join('ledgers', 'entry_items.ledger_id = ledgers.id')->where('entry_items.entry_id', $entry_id)->where('ledgers.type != 1');
                $ledger_q = $this->db->get();
                if ( ! $ledger = $ledger_q->row())
                {
                        return "(Invalid)";
                } else {
                        $ledger_multiple = ($ledger_q->num_rows() > 1) ? TRUE : FALSE;
                        $html = '';
                        if ($ledger_multiple)
                                {
                                $html .= $ledger->name ;
                                }
                        else
                                {
                                $html .= $ledger->name;
                                }
                        return $html;
                        }
                return ;
        } 
	
	
	function get_tag_entry_name($entry_id, $entry_type_id)
        {
                /* Selecting whether to show debit side Ledger or credit side Ledger */
                $current_entry_type = entry_type_info($entry_type_id);
                $ledger_type = 'C';

                if ($current_entry_type['bank_cash_ledger_restriction'] == 3)
                        $ledger_type = 'D';

                $this->db->select('ledgers.name as name');
                $this->db->from('entry_items')->join('ledgers', 'entry_items.ledger_id = ledgers.id')->where('entry_items.entry_id', $entry_id)->where('ledgers.type != 1');
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
                return ;
        }

	 function get_all_fund_ledgers()
        {
                $options = array();
                $options[0] = "(Please Select)";
		$desinated_code=$this->get_account_code('Designated-Earmarked Funds');
        	$this->db->select('name, id, code, op_balance')->from('ledgers')->where('code LIKE', '%' . $desinated_code . '%');
        	$query=$this->db->get();
                foreach ($query->result() as $row)
                {
                        $options[$row->id] = $row->name." - L";
                }
                return $options;
        }

	/** code for Aggregation **/

        function get_ledger_description_agg($code, $accname)
	{
/*    	        $this->db->select('ledger_description');
        	$this->db->from('ledgers')->where('code =', $code);
	        $ledger_result = $this->db->get();
        	if ($ledger = $ledger_result->row())
                	return $ledger->ledger_description;
        	else
        	        return 0;
*/
                $CI =& get_instance();
                $db1=$CI->load->database('login', TRUE);
                $db1->from('bgasAccData')->where('dblable', $accname);
                $accdetail = $db1->get();
                foreach ($accdetail->result() as $row)
                {
                        $db_name = $row->databasename;
                        $db_username = $row->uname;
                        $db_password = $row->dbpass;
                        $host_name = $row->hostname;
                        $port = $row->port;
                }
                try{
                        $dbcon = new PDO("mysql:host=$host_name;dbname=$db_name", $db_username, $db_password);
                        $mgroup = "select * from ledgers where code=$code";
                        $stmt = $dbcon->query($mgroup);

                        if($stmt != false)
                        {
                                foreach ($stmt as $row)
                                {
                                        $description = $row['ledger_description'];
                                }
                        }
                }
                catch(PDOException $e)
                {
                        echo $e->getMessage();
                }
                return $description;
    	}
        function get_ledger_balance_agg($ledger_id,$accname)
        {
		
                list ($op_bal, $op_bal_type) = $this->get_op_balance_agg($ledger_id,$accname);
		$ledger_id;
                $dr_total = $this->get_dr_total1_agg($ledger_id,$accname);
                $cr_total = $this->get_cr_total1_agg($ledger_id,$accname);
                $total = float_ops($dr_total, $cr_total, '-');
                if ($op_bal_type == "D")
                        $total = float_ops($total, $op_bal, '+');
                else
                        $total = float_ops($total, $op_bal, '-');
                return $total;
	
        }

    function get_ledger_var($code){

    	$var = "";

    	if($code == 300102 || $code == 300104 || $code == 300106)
    		$var = 'B';
    	elseif($code == 300123 || $code == 300124)
    		$var = 'D';
    	elseif($code == 300122 || $code == 300128)
    		$var = 'E';
    	elseif ($code == 300109 || $code == 300108 || $code == 300111 || $code == 300112 || $code == 300114 || $code == 300115 || $code == 300105 || $code == 300117 || $code == 300118 || $code == 300125)
    		$var = 'C';
    	else
    		$var = 'A';

    	return $var;
    } 

	function get_all_bank_cash_ledgers()
        {
		$counter=1;
                $options = array();
                $this->db->from('ledgers')->where('type', '1');
                $tag_q = $this->db->get();
                foreach ($tag_q->result() as $row)
                {
                        $options[$counter] = $row->name;
			$counter++;
                }
                return $options;
        }
/* get ledgers till date @sharad23nov@yahoo.com */
    function get_all_ledgers1_agg($date1,$date2,$accname)
    {
        
        $CI =& get_instance();
        $db1=$CI->load->database('login', TRUE);
        $db1->from('bgasAccData')->where('dblable', $accname);
        $accdetail = $db1->get();
        foreach ($accdetail->result() as $row)
        {
            $db_name = $row->databasename;
            $db_username = $row->uname;
            $db_password = $row->dbpass;
            $host_name = $row->hostname;
            $port = $row->port;
        }
        $dbcon = new PDO("mysql:host=$host_name;dbname=$db_name", $db_username, $db_password);
        try
        {
            $options = array();
            $options[0] = "(Please Select)";

            $ledgerrecord = "select a.id, a.date, b.entry_id, b.ledger_id, c.id, c.name, c.code  from entries a, entry_items b, ledgers c where a.id = b.entry_id AND b.ledger_id = c.id order by c.code" ;
//AND a.date >= $date1 AND a.date <=$date2 asc by c.code"; 
            $stmt = $dbcon->query($ledgerrecord);
            if($stmt != false)
            {
                foreach ($stmt as $row)
                {
                    $lcode = $row['code'];
                    $lname = $row['name'];
                    if(substr($lcode, 0, 2) == $this->get_account_code_agg('Liabilities and Owners Equity',$accname))
                        $name = $lname." - L";
                    if(substr($lcode, 0, 2) == $this->get_account_code_agg('Assets',$accname))
                        $name = $lname." - A";
                    if(substr($lcode, 0, 2) == $this->get_account_code_agg('Incomes',$accname))
                        $name = $lname." - I";
                    if(substr($lcode, 0, 2) == $this->get_account_code_agg('Expenses',$accname))
                        $name = $lname." - E";
                    $options[$row['id']] = $name." ( ".$lcode." )" ;
                }
                
            }
            return $options;
        }
        catch(PDOException $e)
        {
            echo $e->getMessage();
        }

    }

/* get account code according to name @sharad23nov@yahoo.com */        
    function get_account_code_agg($account_name,$accname)
    {
        $CI =& get_instance();
        $db1=$CI->load->database('login', TRUE);
        $db1->from('bgasAccData')->where('dblable', $accname);
        $accdetail = $db1->get();
        foreach ($accdetail->result() as $row)
        {
            $db_name = $row->databasename;
            $db_username = $row->uname;
            $db_password = $row->dbpass;
            $host_name = $row->hostname;
            $port = $row->port;
        }
        $dbcon = new PDO("mysql:host=$host_name;dbname=$db_name", $db_username, $db_password);
        try
        {
            if($account_name == 'Expenses')
                $account_name = 'Expenditure';
            if($account_name == 'Liabilities and Owners Equity')
                $account_name = 'Sources of Funds';
            if($account_name == 'Assets')
                $account_name = 'Application of Funds';
            
            $groupsel = "select code from groups where name = '$account_name'";
                        
            $stmt = $dbcon->query($groupsel);
            if($stmt != false)
            {
                foreach ($stmt as $row)
                {
                    return $row['code'];
                }
            }
        }
        catch(PDOException $e)
        {
            echo $e->getMessage();
        }

    }
}

?>
