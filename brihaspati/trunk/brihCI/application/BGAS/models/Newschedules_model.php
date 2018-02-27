<?php
class Newschedules_model extends CI_Model {
function __construct() {
        parent::__construct();
    }


function newschedules_model()
{
        parent::Model();
}

	function startsWith($str1, $str2)
        {
                return !strncmp($str1, $str2, strlen($str2));
        }

	function fixed_asset($id)
	{
		$cr_amountdep = 0;
		$cr_amountded = 0;
		$cr_amount = 0;
               	$dr_amount = 0;
		$op_balance = 0;
		$op_bal = 0;
		$op_bal_cr = 0;
		$op_bal_dr = 0;
		$cl_balance = 0;
		$net_total = 0;
		$net_total = 0;
		$total = array();	
		$entry_date = "";
		
		$CI = & get_instance();
        	$CI->db->select('id,name,code,op_balance,op_balance_dc')->from('ledgers')->where('group_id', $id);
        	$ledgers_q = $CI->db->get();
        	foreach($ledgers_q->result() as $row2)
        	{
	                $ledger_id = $row2->id;
        	        $ledger_name = $row2->name;
                	$op_balance = $row2->op_balance;
			$op_balance_dc = $row2->op_balance_dc;

			if($op_balance_dc == 'C')
            		{
                		$op_bal_cr = $op_bal_cr + $op_balance;
            		}elseif($op_balance_dc == 'D'){
                		$op_bal_dr = $op_bal_dr + $op_balance;
            		}
			//new added lines by @kanchan
			$CI->db->select('entry_id,amount,dc,id,ledger_code')->from('entry_items')->where('ledger_id',$ledger_id);
                	$entry_items_q = $CI->db->get();
                	$entry_items_result = $entry_items_q->result();
                	foreach ($entry_items_result as $row3)
                	{
				$entryid = $row3->entry_id;
				//$entry_date = $row3->update_date;
				$ledger_code = $row3->ledger_code;

				if($row3->dc == 'C')
                                {
				$CI->db->select('ledger_code')->from('entry_items')->where('entry_id',$entryid);
                                $entryq = $CI->db->get();
                                foreach ($entryq->result() as $row4)
				{
					$led_code = $row4->ledger_code;
					if($this->startsWith($led_code, $ledger_code)){
					}
					else if($this->startsWith($led_code, '4007'))
					{
							$cr_amountdep = $row3->amount;
					}else{
					$cr_amountded = $row3->amount;
					}
                                }
				}//if
                                else
                                {
                                $dr_amount = $dr_amount + $row3->amount;
                                }
							
			}	
				
			}

			//Adding opening balance for the ledger head.
			$op_bal = $op_bal_dr - $op_bal_cr;
                        if($op_bal > 0){
                                $op_bal_dc = 'D';
                        }else{
                                $op_bal_dc = 'C';
                        }
			$cl_balance = ($op_bal + $dr_amount - $cr_amountded);
			//$net_total = ($op_balance + $dr_amount) - $cr_amount;
			$total[0] = $op_bal;
			$total[1] = $op_bal_dc;
			$total[2] = $dr_amount;
			$total[3] = $cr_amountded;
			$total[4] = $cl_balance;
			$total[5] = $cr_amountdep;
			return $total; 

	}//fixedgroup

	function fixed_assetledg($ledg_id)
	{
		$cr_amount = 0.00;
                $dr_amount = 0.00;
		$op_bal_dr = 0;
		$op_bal_cr = 0;
		$op_balance4a = 0;
                $cl_balance4a = 0;
		$ledg_other_cr_ded = 0;
		$ledg_other_cr_dep = 0;
		$ledg_other_dr_total = 0;
		$ledg_plan_cr_ded = 0;
		$ledg_plan_cr_dep = 0;
                $ledg_plan_dr_total = 0;
                $ledg_nonplan_dr_total = 0;
               	$ledg_nonplan_cr_ded = 0;
		$ledg_nonplan_cr_dep = 0;

		//$ledg_plan_cr_total1 = 0;
		//$ledg_plan_dr_total1 = 0;
		//$ledg_nonplan_dr_total1 = 0;
		//$ledg_nonplan_cr_total1 = 0;
		
                $op_balance = "";
                $cl_balance = "";
		$net_total = "";
		$total2 = array();

                $CI = & get_instance();
                $CI->db->select('id,name,code,op_balance,op_balance_dc')->from('ledgers')->where('id', $ledg_id);
                $ledgers_q = $CI->db->get();
                foreach($ledgers_q->result() as $row2)
                {
                        $ledger_id = $row2->id;
                        $ledger_name = $row2->name;
                        $op_balance = $row2->op_balance;
			$op_balance_dc = $row2->op_balance_dc;

			if($op_balance_dc == 'C')
            		{
                		$op_bal_cr = $op_bal_cr + $op_balance;
            		}elseif($op_balance_dc == 'D'){
                		$op_bal_dr = $op_bal_dr + $op_balance;
            		}
			
			$CI->db->select('entry_items.entry_id as entry_id,entry_items.ledger_code as ledger_code,entry_items.amount as entry_items_amount, entry_items.dc as entry_items_dc,entry_items.id as entry_items_id,entries.sanc_type as sanc_type');
                        $CI->db->from('entries')->join('entry_items', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id);
                        $result12 =$CI->db->get();
                        $entry_result = $result12->result();
                        foreach($entry_result as $row5)
                        {
                                //print_r($query_row);
                                $dc = $row5->entry_items_dc;
                                $sum = $row5->entry_items_amount;
                                $entry_item_id =$row5->entry_items_id;
                                $sanc_type = $row5->sanc_type;
				$entry_id = $row5->entry_id;
				$ledger_code = $row5->ledger_code;				

				if($dc == 'C')
                                {
                                $cr_amount = $cr_amount + $sum;
                                }
                                else
                                {
                                $dr_amount = $dr_amount + $sum;
                                } 


                                if($sanc_type != "select")
                                {
                                        if($sanc_type == "plan" || $sanc_type == "plan_sfc_scheme")
                                        {
                                                if($dc == "C")
                                                {
						$CI->db->select('ledger_code')->from('entry_items')->where('entry_id',$entry_id);
                                                $entryq = $CI->db->get();
                                                foreach ($entryq->result() as $res_row)
                                                {
                                                $led_code = $res_row->ledger_code;
                                                if($this->startsWith($led_code, $ledger_code)){
                                                }
                                                elseif($this->startsWith($led_code, '4007'))
                                                {
                                                        $ledg_plan_cr_dep = $ledg_plan_cr_dep + $sum;
                                                        //$cr_amountdep = $row3->amount;
                                                }else{
                                                        $ledg_plan_cr_ded = $ledg_plan_cr_ded + $sum;
                                                        //$cr_amountded = $row3->amount;
                                                }
						}//foreach
						}//if
                                                elseif($dc == "D"){
                                                $ledg_plan_dr_total = $ledg_plan_dr_total + $sum;
                                                }
                                        }//ifsanc_type
                                elseif($sanc_type == "non_plan")
                                {
                                	if($dc == "C")
                                        {
					$CI->db->select('ledger_code')->from('entry_items')->where('entry_id',$entry_id);
                                        $entryq = $CI->db->get();
                                        foreach ($entryq->result() as $res_row)
                                        {
                                        $led_code = $res_row->ledger_code;
                                        if($this->startsWith($led_code, $ledger_code)){
                                        }
                                        else if($this->startsWith($led_code, '4007'))
                                       	{
                                    	$ledg_nonplan_cr_dep = $ledg_nonplan_cr_dep + $sum;
					}else{
                                        $ledg_nonplan_cr_ded = $ledg_nonplan_cr_ded + $sum;
                                        }
                                        }//foreach
                                        }//if 
					elseif($dc == "D"){
                                    	$ledg_nonplan_dr_total = $ledg_nonplan_dr_total + $sum;
                                	}
				}//sanc_type=nonplan
				elseif($sanc_type == "plan_other_scheme")
                                {
                                	//echo "sanc type======$sanc_type";
                                        if($dc == "C")
                                        {
                                        $CI->db->select('ledger_code')->from('entry_items')->where('entry_id',$entry_id);
                                        $entryq = $CI->db->get();
                                        foreach ($entryq->result() as $res_row)
                                        {
                                        $led_code = $res_row->ledger_code;
                                        if($this->startsWith($led_code, $ledger_code)){
                                        }
                                        else if($this->startsWith($led_code, '4007'))
                                        {
                                        $ledg_other_cr_dep = $ledg_other_cr_dep + $sum;
                                        }else{
                                        $ledg_other_cr_ded = $ledg_other_cr_ded + $sum;
                                        }
                                        }//foreach
                                        }//if 
                                        elseif($dc == "D"){
                                        $ledg_other_dr_total = $ledg_other_dr_total + $sum;
                                        }
                                }//sanc_type=other
				}//if(select)

			}//foreach
		}//foreach

		//Adding opening balance for the ledger head.

		$op_bal = $op_bal_dr - $op_bal_cr;
                if($op_bal > 0){
                	$op_bal_dc = 'D';
                        }else{
               		$op_bal_dc = 'C';
                        }
		$cl_balance = ($op_bal + $dr_amount - $cr_amount);
               	$cl_balance4a = $op_bal + $ledg_plan_dr_total - $ledg_plan_cr_ded;
		$cl_balance4b = $op_bal + $ledg_nonplan_dr_total - $ledg_nonplan_cr_ded;
		$cl_bal_ohter = $op_bal + $ledg_other_dr_total - $ledg_other_cr_ded;

		$total2[0] = $op_bal;
		$total2[1] = $op_bal_dc;
		$total2[2] = $dr_amount;
                $total2[3] = $cr_amount;
               	$total2[4] = $cl_balance;
		$total2[5] = $ledg_plan_dr_total;
		$total2[6] = $ledg_plan_cr_ded;
		$total2[7] = $ledg_nonplan_dr_total;
		$total2[8] = $ledg_nonplan_cr_ded;
		$total2[9] = $cl_balance4a;
		$total2[10] = $cl_balance4b;
		$total2[11] = -$ledg_plan_cr_dep;
		$total2[12] = -$ledg_nonplan_cr_dep;
		$total2[13] = $ledg_other_cr_dep;
		$total2[14] = $ledg_other_cr_ded;
		$total2[15] = $ledg_other_dr_total;
		$total2[16] = $cl_bal_ohter;
		
		return $total2; 
	}//fixed for children

	function get_old_asset_depvalue($id)
	{
		//$dep_op_value = "";
		$dep_op_sum  = "";
		$old_curr_sum = "";
		$dep_opening_value1 = 0;
		$dep_opening_value2 = 0;
		$dep_opening_value3 = 0;
		$old_curr_plan = 0;
		$old_curr_nonplan = 0;
		$old_curr_other = 0;
                //$current_dep_value = "";
		//$old_current_value = "";
                $total_dep = 0.00;
		$old_asset_dep = array();

                $CI = & get_instance();
                $CI->db->select('id,name,code')->from('ledgers')->where('group_id', $id);
                $ledgers_q = $CI->db->get();
                $ledger_result = $ledgers_q->result();
                foreach($ledger_result as $row2)
                {
                        $ledger_id = $row2->id;
                        $ledger_name = $row2->name;
			$CI->db->select('asset_name,cost,depreciated_value,current_value,sanc_type')->from('old_asset_register')->where('asset_name', $ledger_name);
                        $asset = $CI->db->get();
                        $asset_result = $asset->result();
                        foreach ($asset_result as $row3)
                        {
                                $asset_name = $row3->asset_name;
				//echo "asset name =========$asset_name";
                                $dep_op_value = $row3->depreciated_value;
				$dep_op_sum = $dep_op_sum + $dep_op_value;
                                $old_current_amount = $row3->current_value;
				$old_curr_sum = $old_curr_sum + $old_current_amount;
				//echo "old current amount ====>>$old_current_amount";
                                $asset_cost = $row3->cost;
				$sanc_type = $row3->sanc_type;
				//echo "sanc_type====>>>> $sanc_type ";

				if($sanc_type != "select")
                        	{
                            		if($sanc_type == "plan" || $sanc_type == "plan_sfc_scheme")
                            		{
						$dep_opening_value1 = $dep_opening_value1 + $dep_op_value;
						$old_curr_plan = $old_curr_plan + $old_current_amount;
					}elseif($sanc_type == "non_plan"){
						$dep_opening_value2 = $dep_opening_value2 + $dep_op_value;
                                                $old_curr_nonplan = $old_curr_nonplan + $old_current_amount;
                            		}elseif($sanc_type == "plan_other_scheme"){
					 	$dep_opening_value3 = $dep_opening_value3 + $dep_op_value;
                                         	$old_curr_other = $old_curr_other + $old_current_amount;
					}
				}//if(!select)
			}//foreach old_asset
		}
		//echo "</br>out of function----------------$asset_name------------------";
		$old_asset_dep[0] = $dep_op_sum;
		$old_asset_dep[1] = $old_curr_sum;
		$old_asset_dep[2] = $dep_opening_value1;
		$old_asset_dep[3] = $old_curr_plan;
		$old_asset_dep[4] = $old_curr_nonplan;
		$old_asset_dep[5] = $dep_opening_value2;
		$old_asset_dep[6] = $dep_opening_value3;	
		$old_asset_dep[7] = $old_curr_other;

                return $old_asset_dep;

        }

	function get_new_asset_depvalue($id)
	{
		//$dep_op_value = "";
		$dep_op_sum = "";
		$new_curr_sum = "";
                $current_dep_value = "";
		$dep_opening_value1 = 0;
		$dep_opening_value2 = 0;
		$dep_opening_value3 = 0;
                $new_curr_plan = 0;
		$new_curr_nonplan = 0;
		$new_curr_other = 0;
		$new_current_value = "";
                $total_dep = 0.00;
                $new_asset_dep = array();

                $CI = & get_instance();
                $CI->db->select('id,name,code')->from('ledgers')->where('group_id', $id);
                $ledgers_q = $CI->db->get();
                $ledger_result = $ledgers_q->result();
                foreach($ledger_result as $row2)
                {
                        $ledger_id = $row2->id;
                        $ledger_name = $row2->name;

                        $CI->db->select('asset_name,cost,depreciated_value,current_value,sanc_type')->from('new_asset_register')->where('asset_name', $ledger_name);
                        $asset = $CI->db->get();
                        $asset_result = $asset->result();
                        foreach ($asset_result as $row4)
                        {
				//print_r($row4);
                                $asset_name = $row4->asset_name;
                                $dep_op_value = $row4->depreciated_value;
				$dep_op_sum = $dep_op_sum + $dep_op_value;
                                $new_current_amount = $row4->current_value;
				$new_curr_sum = $new_curr_sum  + $new_current_amount;
                                $asset_cost = $row4->cost; 
				$sanc_type = $row4->sanc_type;

                                if($sanc_type != "select")
                                {
                                       	if($sanc_type == "plan" || $sanc_type == "plan_sfc_scheme")
                                       	{
                                                $dep_opening_value1 = $dep_opening_value1 + $dep_op_value;
                                                $new_curr_plan = $new_curr_plan + $new_current_amount;
                                        }elseif($sanc_type == "non_plan"){
                                                $dep_opening_value2 = $dep_opening_value2 + $dep_op_value;
                                                $new_curr_nonplan = $new_curr_nonplan + $new_current_amount;

                                        }elseif($sanc_type == "plan_other_scheme"){
                                                $dep_opening_value3 = $dep_opening_value3 + $dep_op_value;
                                                $new_curr_other = $new_curr_other + $new_current_amount;
                                        }
                                }//if(!select)
                	}//foreach new_asset
		}
		$new_asset_dep[0] = $dep_op_sum;
                $new_asset_dep[1] = $new_curr_sum;
		$new_asset_dep[2] = $dep_opening_value1;
                $new_asset_dep[3] = $new_curr_plan;
		$new_asset_dep[4] = $new_curr_nonplan;
		$new_asset_dep[5] = $dep_opening_value2;
		$new_asset_dep[6] = $dep_opening_value3;
                $new_asset_dep[7] = $new_curr_other;


                return $new_asset_dep;
	}

	function get_old_asset_depvalue1($ledg_id)
	{
		$dep_op_value = "";
		$dep_opening_value1 = 0;
		$dep_opening_value2 = 0;
		$old_curr_plan = 0;
		$old_curr_nonplan = 0;
		$dep_op_value = "";
                $current_dep_value = "";
		$old_current_value = "";
                $total_dep = 0.00;
		$old_asset_dep = array();

                $CI = & get_instance();
                $CI->db->select('id,name,code')->from('ledgers')->where('id', $ledg_id);
                $ledgers_q = $CI->db->get();
                foreach($ledgers_q->result() as $row2)
                {
                        $ledger_id = $row2->id;
                        $ledger_name = $row2->name;
			$CI->db->select('asset_name,cost,depreciated_value,current_value,sanc_type')->from('old_asset_register')->where('asset_name', $ledger_name);
                        $asset = $CI->db->get();
                        $asset_result = $asset->result();
                        foreach ($asset_result as $row3)
                        {
                                $asset_name = $row3->asset_name;
                                $dep_op_value = $row3->depreciated_value;
                                $old_current_value = $row3->current_value;
                                $asset_cost = $row3->cost;
				$sanc_type = $row3->sanc_type;

				if($sanc_type != "select")
                        	{
                            		if($sanc_type == "plan")
                            		{
						$dep_opening_value1 = $dep_opening_value1 + $dep_op_value;
						$old_curr_plan = $old_curr_plan + $old_current_value;
					}elseif($sanc_type == "non_plan"){
						$dep_opening_value2 = $dep_opening_value2 + $dep_op_value;
                                                $old_curr_nonplan = $old_curr_nonplan + $old_current_value;

                            		}
				}//if(!select)
			}//foreach old_asset
                }
		$old_asset_dep[0] = $dep_op_value;
		$old_asset_dep[1] = $old_current_value;
		$old_asset_dep[2] = $dep_opening_value1;
		$old_asset_dep[3] = $old_curr_plan;
		$old_asset_dep[4] = $old_curr_nonplan;
		$old_asset_dep[5] = $dep_opening_value2;
                return $old_asset_dep;
        }

	function get_new_asset_depvalue1($ledg_id)
	{
		$dep_op_value = "";
		$dep_opening_value1 = 0;
		$dep_opening_value2 = 0;
                $new_curr_plan = 0;
		$new_curr_nonplan = 0;
		$dep_op_value = "";
                $current_dep_value = "";
		$new_current_value = "";
                $total_dep = 0.00;
                $new_asset_dep = array();

                $CI = & get_instance();
                $CI->db->select('id,name,code')->from('ledgers')->where('id', $ledg_id);
                $ledgers_q = $CI->db->get();
                foreach($ledgers_q->result() as $row2)
                {
                        $ledger_id = $row2->id;
                        $ledger_name = $row2->name;

                        $CI->db->select('asset_name,cost,depreciated_value,current_value,sanc_type')->from('new_asset_register')->where('asset_name', $ledger_name);
                        $asset = $CI->db->get();
                        $asset_result = $asset->result();
                        foreach ($asset_result as $row4)
                        {
				//print_r($row4);
                                $asset_name = $row4->asset_name;
                                $dep_op_value = $row4->depreciated_value;
                                $new_current_amount = $row4->current_value;
                                $asset_cost = $row4->cost; 
				$sanc_type = $row4->sanc_type;

                                if($sanc_type != "select")
                                {
                                       	if($sanc_type == "plan")
                                       	{
                                                $dep_opening_value1 = $dep_opening_value1 + $dep_op_value;
                                                $new_curr_plan = $new_curr_plan + $new_current_amount;
                                        }elseif($sanc_type == "non_plan"){
                                                $dep_opening_value2 = $dep_opening_value2 + $dep_op_value;
                                                $new_curr_nonplan = $new_curr_nonplan + $new_current_amount;

                                        }
                                }//if(!select)
                	}//foreach new_asset
		}
		$new_asset_dep[0] = $dep_op_value;
                $new_asset_dep[1] = $new_current_value;
		$new_asset_dep[2] = $dep_opening_value1;
                $new_asset_dep[3] = $new_curr_plan;
		$new_asset_dep[4] = $new_curr_nonplan;
		$new_asset_dep[5] = $dep_opening_value2;
                return $new_asset_dep;
	}


	function sub_schedule3a($group_id1)
	{
		$CI = & get_instance();
        	$CI->db->select('id,name,code,op_balance,op_balance_dc')->from('ledgers')->where('group_id', $group_id1);
        	$ledg_query = $CI->db->get();
		$op_balance_cr = 0;
        	$op_balance_dr = 0;
        	$op_balance = 0;
        	$opbalance = array();
        	foreach($ledg_query->result() as $row1)
        	{
            		$ledg_id = $row1->id;
            		$ledg_name = $row1->name;
			$ledg_op_balance = $row1->op_balance;
			$ledg_op_balance_dc = $row1->op_balance_dc;
            		if($ledg_op_balance_dc == 'C')
            		{
                	$op_balance_cr = $op_balance_cr + $ledg_op_balance;
            		}elseif($ledg_op_balance_dc == 'D'){
                	$op_balance_dr = $op_balance_dr + $ledg_op_balance;
            		}

        		$op_balance = $op_balance_dr - $op_balance_cr;
        		if($op_balance > 0){
            			$op_balance_dc = 'D';
        		}else{
            			$op_balance_dc = 'C';
        		}

        		$opbalance[0] = $op_balance;
        		$opbalance[1] = $op_balance_dc;
		}
        return $opbalance;
	}

    	function get_project_name($group_name1)
	{
        	$project_name = '';
        	if(strpos($group_name1,'Expenses') !== false)
        	{
            		$project_name = str_replace('Expenses','',$group_name1);
        	}elseif(strpos($group_name1,'Receipts') !== false){
            		$project_name = str_replace('Receipts','',$group_name1);
        	}elseif(strpos($group_name1,'Fixed Assets') !== false){
            		$project_name = str_replace('Fixed Assets','',$group_name1);
        	}

        return $project_name;
    	}

    	function get_op_balance($project_name,$id)
    	{
		if($id == "93")
		{
        	$this->db->select('id')->from('groups')->where('name LIKE',$project_name.'%')->where('code LIKE','10040105%');
		}elseif($id == "100"){
		$this->db->select('id')->from('groups')->where('name LIKE',$project_name.'%')->where('code LIKE','10040107%');
		}
        	$groups = $this->db->get();
        	$op_bal_cr = 0;
        	$op_bal_dr = 0;
        	$op_bal = 0;
        	$op_balance1 = array();
        	foreach($groups->result() as $row)
        	{
        		$group_id = $row->id;
        		$opbalance = $this->sub_schedule3a($group_id);
        		$group_op_balance = $opbalance[0];
        		$group_op_balance_dc = $opbalance[1];
			if($group_op_balance_dc == 'C')
            		{
				
                		$op_bal_cr = $op_bal_cr + $group_op_balance;
            		}elseif($group_op_balance_dc == 'D'){
                		$op_bal_dr = $op_bal_dr + $group_op_balance;
            		}
        	}
        		$op_bal = $op_bal_dr - (-$op_bal_cr);
        		if($op_bal > 0){
            			$op_bal_dc = 'D';
        		}else{
            			$op_bal_dc = 'C';
        		}
        		$opbalance1[0] = $op_bal;
        		$opbalance1[1] = $op_bal_dc;
			

    	return $opbalance1;
    	}

	function get_exp_receipt_total($project_name,$id)
    	{
		//echo "id=========>$id";
		$dr_sum_total = "";
		$cr_sum_total = "";
		$total = array();
		$CI =&get_instance();
        	$CI->load->library('session');
        	$date1 = $CI->session->userdata('date1');
        	$date2 = $CI->session->userdata('date2');
		if($id == "93"){
        	$CI->db->select('id,name')->from('groups')->where('name LIKE',$project_name.'%')->where('code LIKE','10040105%'); 		 }elseif($id == "100"){
		$CI->db->select('id,name')->from('groups')->where('name LIKE',$project_name.'%')->where('code LIKE','10040107%');
		}
        	$groups = $CI->db->get();
        	foreach($groups->result() as $row)
        	{
            		$group_id = $row->id;
			$name = $row->name;
            		$CI->db->select('id,name')->from('ledgers')->where('group_id',$group_id); 
            		$ledgers = $CI->db->get();
            		foreach($ledgers->result() as $row1)
            		{
               	 		$ledger_id = $row1->id;
                		$CI->db->select('entry_items.amount as entry_items_amount, entry_items.dc as entry_items_dc,entry_items.id as entry_items_id');
                		$CI->db->from('entries')->join('entry_items', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id);
                		$CI->db->where('date >=', $date1);
                		$CI->db->where('date <=', $date2);
                		$entries =$CI->db->get();
                		$entry_result = $entries->result();
                		foreach($entry_result as $query_row)
                		{
                    			//$entry_item_id =$query_row->entry_items_id;
                    			$dc = $query_row->entry_items_dc;
                    			if($dc == "D"){
                        			$dr_sum = $query_row->entry_items_amount;
                        			$dr_sum_total = $dr_sum_total + $dr_sum;
                    			}else{
                        			$cr_sum = $query_row->entry_items_amount;
                        			$cr_sum_total = $cr_sum_total + $cr_sum;
					}
                		} 
            		} 
        	} 
		$total[0] = $dr_sum_total;
		$total[1] = $cr_sum_total;
		
	return $total;
    	}

	
	function plan_sub_schedule4($group_id)
	{
                $op_balance4a = 0;
                $cl_balance4a = 0;
		$op_bal_dr = 0;
		$op_bal_cr = 0;
                $dep_op_value = "";
		$ledg_other_cr_ded = 0;
		$ledg_other_cr_dep = 0;
		$ledg_other_dr_total = 0;
                $current_dep_value = "";
		$ledg_plan_cr_ded = 0;
		$ledg_plan_cr_dep = 0;
                $ledg_plan_dr_total = 0;
                $ledg_nonplan_dr_total = 0;
               	$ledg_nonplan_cr_ded = 0;
		$ledg_nonplan_cr_dep = 0;
		$total4a = array();

                $total_dep = "";
		$CI =& get_instance();
                $CI->db->select('id,name,code,op_balance,op_balance_dc')->from('ledgers')->where('group_id', $group_id);
                $ledgers_q = $CI->db->get();
                foreach($ledgers_q->result() as $row2)
                {
                        $ledger_id = $row2->id;
                        $ledger_name = $row2->name;
			$op_balance = $row2->op_balance;
			$op_balance_dc = $row2->op_balance_dc;
			if($op_balance_dc == 'C')
            		{
                		$op_bal_cr = $op_bal_cr + $op_balance;
            		}elseif($op_balance_dc == 'D'){
                		$op_bal_dr = $op_bal_dr + $op_balance;
            		}

			$CI->db->select('entry_items.amount as entry_items_amount, entry_items.dc as entry_items_dc,entry_items.id as entry_items_id,entries.sanc_type as sanc_type,entry_items.ledger_code as ledger_code,entry_items.entry_id as entry_id');
                    	$CI->db->from('entries')->join('entry_items', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id);
                    	$result12 =$CI->db->get();
                    	$entry_result = $result12->result();
                    	foreach($entry_result as $query_row)
                    	{
				//print_r($query_row);
                        	$dc = $query_row->entry_items_dc;
                        	$sum = $query_row->entry_items_amount;
                        	$entry_item_id =$query_row->entry_items_id;
                        	$sanc_type = $query_row->sanc_type;
				$ledger_code = $query_row->ledger_code;
				$entry_id = $query_row->entry_id;
				if($sanc_type != "select")
                        	{
                            		if($sanc_type == "plan" || $sanc_type == "plan_sfc_scheme")
                            		{
                                		if($dc == "C")
                                		{
						$CI->db->select('ledger_code')->from('entry_items')->where('entry_id',$entry_id);
                                		$entryq = $CI->db->get();
                                		foreach ($entryq->result() as $res_row)
                                		{
                                        	$led_code = $res_row->ledger_code;
                                        	if($this->startsWith($led_code, $ledger_code)){
                                        	}
                                       	 	else if($this->startsWith($led_code, '4007'))
                                        	{
							$ledg_plan_cr_dep = $ledg_plan_cr_dep + $sum;
                                                        //$cr_amountdep = $row3->amount;
                                        	}else{
							$ledg_plan_cr_ded = $ledg_plan_cr_ded + $sum;
                                        		//$cr_amountded = $row3->amount;
                                        	}
						}//foreach
						}//if 
						elseif($dc == "D"){
                                    		$ledg_plan_dr_total = $ledg_plan_dr_total + $sum;
						}
					}//ifsanc_type
				elseif($sanc_type == "non_plan")
				{
					 //echo "sanc type======$sanc_type";
                                	if($dc == "C")
                                	{
					$CI->db->select('ledger_code')->from('entry_items')->where('entry_id',$entry_id);
                                        $entryq = $CI->db->get();
                                        foreach ($entryq->result() as $res_row)
                                        {
                                        $led_code = $res_row->ledger_code;
                                        if($this->startsWith($led_code, $ledger_code)){
                                        }
                                        else if($this->startsWith($led_code, '4007'))
                                       	{
                                    	$ledg_nonplan_cr_dep = $ledg_nonplan_cr_dep + $sum;
					}else{
                                        $ledg_nonplan_cr_ded = $ledg_nonplan_cr_ded + $sum;
                                        }
                                        }//foreach
                                        }//if 
					elseif($dc == "D"){
                                    	$ledg_nonplan_dr_total = $ledg_nonplan_dr_total + $sum;
                                	}
				}//sanc_type=nonplan
				elseif($sanc_type == "plan_other_scheme")
                                {
                                         //echo "sanc type======$sanc_type";
                                        if($dc == "C")
                                        {
                                        $CI->db->select('ledger_code')->from('entry_items')->where('entry_id',$entry_id);
                                        $entryq = $CI->db->get();
                                        foreach ($entryq->result() as $res_row)
                                        {
                                        $led_code = $res_row->ledger_code;
                                        if($this->startsWith($led_code, $ledger_code)){
                                        }
                                        else if($this->startsWith($led_code, '4007'))
                                        {
                                        $ledg_other_cr_dep = $ledg_other_cr_dep + $sum;
                                        }else{
                                        $ledg_other_cr_ded = $ledg_other_cr_ded + $sum;
                                        }
                                        }//foreach
                                        }//if 
                                        elseif($dc == "D"){
                                        $ledg_other_dr_total = $ledg_other_dr_total + $sum;
                                        }
                                }//sanc_type=other
				}//ifselect
				//}//else
				//}//if(!select)
			}//foreach
		}//foreach
			$op_bal = $op_bal_dr - $op_bal_cr;
                        if($op_bal > 0){
                                $op_bal_dc = 'D';
                        }else{
                                $op_bal_dc = 'C';
                        }
                	$cl_balance4a = $op_bal + $ledg_plan_dr_total - $ledg_plan_cr_ded;
			$cl_balance4b = $op_bal + $ledg_nonplan_dr_total - $ledg_nonplan_cr_ded;
			$cl_bal_ohter = $op_bal + $ledg_other_dr_total - $ledg_other_cr_ded;

		$total4a[0] = $op_bal;
		$total4a[1] = $op_bal_dc;
		$total4a[2] = $ledg_plan_dr_total;
		$total4a[3] = $ledg_plan_cr_ded;
		$total4a[4] = $ledg_nonplan_dr_total;
		$total4a[5] = $ledg_nonplan_cr_ded;
		$total4a[6] = $cl_balance4a;
		$total4a[7] = $cl_balance4b;
		$total4a[8] = -$ledg_plan_cr_dep;
		$total4a[9] = -$ledg_nonplan_cr_dep;
		$total4a[10] = $ledg_other_cr_dep;
		$total4a[11] = $ledg_other_cr_ded;
		$total4a[12] = $ledg_other_dr_total;
		$total4a[13] = $cl_bal_ohter;

	return $total4a; 
	}

/*	function plan_old_depvalue($child_id)
	{
		$dep_op_value = "";
                $current_dep_value = "";
		$old_current_value = "";
                $total_dep = 0.00;
		$old_asset_dep = array();

                $CI = & get_instance();
                $CI->db->select('id,name,code')->from('ledgers')->where('group_id', $group_id);
                $ledgers_q = $CI->db->get();
                $ledger_result = $ledgers_q->result();
                foreach($ledger_result as $row2)
                {
                        $ledger_id = $row2->id;
                        $ledger_name = $row2->name;
			$CI->db->select('asset_name,cost,depreciated_value,current_value')->from('old_asset_register')->where('asset_name', $ledger_name);
                        $asset = $CI->db->get();
                        $asset_result = $asset->result();
                        foreach ($asset_result as $row3)
                        {
                                $asset_name = $row3->asset_name;
                                $dep_op_value = $row3->depreciated_value;
                                $old_current_value = $row3->current_value;
                                $asset_cost = $row3->cost;
			}//foreach old_asset
                }
		$old_asset_dep[0] = $dep_op_value;
		$old_asset_dep[1] = $old_current_value;
                return $old_asset_dep;

        } */

/*	function get_dep_value1($child_id)
        {
                $dep_op_value = "";
		$op_balance = 0;
		$dep_opening_bal = 0;
		$old_current_value = "";
                $current_dep_value = "";
		$new_current_value = "";
                $total_dep4a = 0.00;
		$asset_name = "";
		$current_dep_value4a = "";
		$total4a1 = array();
                $CI = & get_instance();
                $CI->db->select('id,name,code,op_balance,op_balance_dc')->from('ledgers')->where('group_id', $child_id);
                $ledgers_q4a = $CI->db->get();
                foreach($ledgers_q4a->result() as $row4a1)
                {
                        $ledger_id = $row4a1->id;
                        $ledger_name = $row4a1->name;
			$ledger_opbal = $row4a1->op_balance;
			$ledger_opbal_dc = $row4a1->op_balance_dc;
			$op_balance_cr = 0;
        		$op_balance_dr = 0;
        		$dep_opening_bal = 0;
                        $CI->db->select('asset_name,cost,depreciated_value,current_value,sanc_type')->from('old_asset_register')->where('asset_name', $ledger_name);
                        $asset4a1 = $CI->db->get();
                        foreach ($asset4a1->result() as $row3)
                        {
                                $asset_name = $row3->asset_name;
				$sanc_type = $row3->sanc_type;
                                $dep_op_value = $row3->depreciated_value;
                                $old_current_value = $row3->current_value;
                                $asset_cost = $row3->cost;

  		        	//Adding opening balance for the ledger head.
				//$op_balance = $dep_op_value;
                        	//$current_dep_value4a = $old_current_value + $new_current_value;
                        	//$total_dep4a = $op_balance + $current_dep_value4a; 
		}
		}//foreach
			$total4a1[0] = $dep_op_value;
			$total4a1[1] = $old_current_value;
			//$total4a1[2] = $total_dep4a;
                return $total4a1; 

        } */

	function schedule2($group_id)
	{
		$op_balance = 0;
		$dr_total = 0;
		$op_balance_dr = 0;
		$op_balance_cr = 0;
		$fund_add = "";
		$fund_income = "";
		$sum = 0;
		$sum1 = 0;
		$sum2 = 0;
		$sum3 = 0;
		$sum4 = 0;
		$sum5 = 0;
		$this->load->model('ledger_model');
		//$this->db->select('name,id')->from('groups')->where('parent_id',$group_id);
//                $query = $this->db->get();
  //              $q_result = $query->result();
//		$total = array();
  //              foreach($q_result as $row)
    //            {
      //          	$group_id = $row->id;
	//		$group_name = $row->name;
	//		print_r(" the name is ".$group_name." and id is ".$group_id);
			$this->db->select('name,id')->from('ledgers')->where('group_id',$group_id);
            		$query1 = $this->db->get();
            		$q_result1 = $query1->result();
            		foreach($q_result1 as $row1)
			{
                		$ledg_id = $row1->id;
				$ledg_name = $row1->name;
                                $this->db->select('fund_name,amount,type')->from('fund_management')->where('fund_id',$ledg_id);
                                $fund_q = $this->db->get();
                                foreach($fund_q->result() as $row2)
                                {
					$fund_type = $row2->type;
                                        $fund_amount = $row2->amount;
					$fund_add = $fund_add + $fund_amount;

					if($fund_type == "Investment")
					{
						$fund_income = $row2->amount;
						$sum = $sum + $fund_income;

					}elseif($fund_type == "Accru"){
						$fund_accru = $row2->amount;
                                                $sum1 = $sum1 + $fund_accru;

					}elseif($fund_type == "Earn"){
                                                $fund_earned = $row2->amount;
                                                $sum2 = $sum2 + $fund_earned;

                                        }elseif($fund_type == "Capital"){
                                                $capital_amount = $row2->amount;
                                                $sum4 = $sum4 + $capital_amount;

                                        }elseif($fund_type == "Revenue"){
                                                $revenue_amount = $row2->amount;
                                                $sum5 = $sum5 + $revenue_amount;
                                        }
				}
				$CI =& get_instance();
//				$CI->load->model('ledger_model');
				$op_balance = $CI->ledger_model->get_op_balance($ledg_id);
 	                       	$opening_data = $op_balance[0];
				$op_balance_dc = $op_balance[1];
				$cl_bal=$CI->ledger_model->get_ledger_balance($ledg_id);
				$sum3=$sum3+$cl_bal;
				if($op_balance_dc == 'C')
            			{
                			$op_balance_cr = $op_balance_cr + $opening_data;
            			}elseif($op_balance_dc == 'D'){
                			$op_balance_dr = $op_balance_dr + $opening_data;
            			}

                	}
	//	}
			
			$op_balance = $op_balance_dr - $op_balance_cr;
        		if($op_balance > 0){
            			$op_balance_dc = 'D';
        		}else{
            			$op_balance_dc = 'C';
        		}

			$total[0] = $op_balance;
                        $total[1] = $fund_add;
			$total[2] = $sum;
			$total[3] = $sum1;
			$total[4] = $sum2;
			$total[5] = $sum3;
			$total[6] = $sum4;
			$total[7] = $sum5;
			$total[8] = $op_balance_dc;

		return $total;

	}

/*	function get_capital_total($ledger_id)
	{
		//$CI =& get_insatnce();
		$this->load->library('session');
		//$CI->load->model('ledger_model');
        	$date1 = $this->session->userdata('date1');
        	$date2 = $this->session->userdata('date2');
                $non_plan_total = 0;
                $plan_total = 0;
		$this->db->select('group_id')->from('ledgers')->where('id', $ledger_id);
		$query1 = $this->db->get();
		$group_q = $query1->row();
		$group_id = $group_q->group_id;
		
		$this->db->select('name')->from('groups')->where('id', $group_id);
                $group_result = $this->db->get();
                $groups = $group_result->row(); 
		$group_name = $groups->name;

		$this->db->select('entry_items_id,amount')->from('fund_management')->where('fund_id',$ledger_id);
                $this->db->where('type','Capital');
                $this->db->where('date >=', $date1);
            	$this->db->where('date <=', $date2);
                $query_result2 = $this->db->get();
		foreach($query_result2->result() as $row1)
                {
			print_r($row1);
                	$entry_items_id = $row1->entry_items_id;
                       	$this->db->select('entries.sanc_type as sanc_type,entry_items.dc as entry_items_dc');
                	$this->db->from('entries')->join('entry_items', 'entries.id = entry_items.entry_id')->where('entry_items.id', $entry_items_id);
                	$query_result3 = $this->db->get();
                	foreach($query_result3->result() as $row2)
                        {
                      		$sanc_type = $row2->sanc_type;
                                $dc = $row2->entry_items_dc;
			}
		}





	}

	function get_revenue_total($ledger_id)
        {
                $this->load->library('session');
                $date1 = $this->session->userdata('date1');
                $date2 = $this->session->userdata('date2');
                $non_plan_total = 0;
                $plan_total = 0;



        } */


	function get_subschedule3c($group_id,$type)
	{
		//$sum = 0;
		$op_bal_cr = 0;
        	$op_bal_dr = 0;
        	$op_bal = 0;
		$debit_total = 0;
		$credit_total = 0;
		//$capital_total = 0;
		//$revenue_total = 0;
		$total = array();
		$this->db->select('name,id')->from('ledgers')->where('group_id',$group_id);
                $query1 = $this->db->get();
		$query_q = $query1->result();
		foreach($query_q as $row1)
                {
                        $ledg_id = $row1->id;
                        $ledg_name = $row1->name;

			 //$capital1 = $this->get_capital_total($ledg_id);
			$d_total = array();
			$c_total = array();
			$capital_total = array();
			$revenue_total = array();

			$CI =& get_instance();
                        $CI->load->model('ledger_model');
                        $op_balance = $CI->ledger_model->get_op_balance($ledg_id);
			$opening_value = $op_balance[0];
			$op_balance_dc = $op_balance[1];
			if($type == "plan")
                        {
			$dr_total = $CI->ledger_model->get_dr_total2($ledg_id);
			$d_total = $dr_total['plan'];
			$debit_total = $debit_total + $d_total;
			$cr_total = $CI->ledger_model->get_cr_total2($ledg_id);
			$c_total = $cr_total['plan'];
			$credit_total = $credit_total + $c_total;
			$capital = $CI->ledger_model->get_capital_exp_total($ledg_id);
			$capital_total = $capital['plan'];
                        $revenue = $CI->ledger_model->get_revenue_exp_total($ledg_id);
			$revenue_total = $revenue['plan'];
			}
			else
			{
                        $dr_total = $CI->ledger_model->get_dr_total2($ledg_id);
                        $d_total = $dr_total['nonplan'];
                        $debit_total = $debit_total + $d_total;
                        $cr_total = $CI->ledger_model->get_cr_total2($ledg_id);
                        $c_total = $cr_total['nonplan'];
                        $credit_total = $credit_total + $c_total;
                        $capital = $CI->ledger_model->get_capital_exp_total($ledg_id);
                        $capital_total = $capital['nonplan'];
                        $revenue = $CI->ledger_model->get_revenue_exp_total($ledg_id);
                        $revenue_total = $revenue['nonplan'];
			}

			//echo "c_total======$c_total";
			if($op_balance_dc == 'C')
            		{
                		$op_bal_cr = $op_bal_cr + $opening_value;
            		}elseif($op_balance_dc == 'D'){
                		$op_bal_dr = $op_bal_dr + $opening_value;
            		}
		}
			$op_bal = $op_bal_dr - $op_bal_cr;
        		if($op_bal > 0){
            			$op_bal_dc = 'D';
        		}else{
            			$op_bal_dc = 'C';
        		}
			
		$total[0] = $op_bal;
		$total[1] = $op_bal_dc;
		$total[2] = $credit_total;
		$total[3] = $debit_total;
		$total[4] = $revenue_total;
		$total[5] = $capital_total;
	
		return $total;
	}

	//added by kanchan
	function get_fixedasset_ledger_list()
	{
		//$counter = 0;
		$options = array();
		$new_id = '--Please Select--';
                $options[$new_id] = '--Please Select--';

		$this->db->select('id,name')->from('ledgers')->where('code  Like','2001%');
		$ledg_res = $this->db->get();

		foreach($ledg_res->result() as $ledger)
		{	
			$new_id = $ledger->name;
			$options[$new_id] = $ledger->name;
			$options['id'] = $ledger->id;
			//$counter++;
		}
        	return $options; 
	}
	
	//added by @kanchan
	function get_Fixedledger_code($name)
        {
		$options = array();
        	$this->db->select('code');
                $this->db->from('ledgers')->where('name =', $name);
                $ledger_result = $this->db->get();
		foreach($ledger_result->result() as $led)
                {
                        $options['code'] = $led->code;
			
                }
                return $options; 
        }
	function get_dr_total($data_ledger_id)
	{
		
		$this->db->select('amount,dc')->from('entry_items')->where('ledger_id',$data_ledger_id);
                $res = $this->db->get();
                $resq = $res->result();
                foreach($resq as $row)
                {
			$amount = $row->amount;
			$dc = $row->dc;

			 if($dc == 'C')
                         {
                         $cr_total = $amount;
                         }else{
                         $dr_total = $amount;
			echo "dr total in model file ==== $dr_total";
                         }
		}
		echo "dr total in model file out of function==== $dr_total";
		return $dr_total;
	}

/*	function get_capital_total($ledger_id)
	{
		$this->load->library('session');
        	$date1 = $this->session->userdata('date1');
        	$date2 = $this->session->userdata('date2');
		$non_plan_total = 0;
		$plan_total = 0;
		$specific_total =0;
		$name = $this->get_ledger_name($ledger_id);	
	}

	function get_revenue_total()
	{

	} */



}//main
?>
