<?php
class newschedules_model extends Model {

function newschedules_model()
{
        parent::Model();
}
	function fixed_asset($group_id)
	{
		$cr_amount = 0.00;
               	$dr_amount = 0.00;
		$op_balance = "";
		$cl_balance = "";
		$net_total = "";
		$net_total = "";
		$CI = & get_instance();
        	$CI->db->select('id,name,code,op_balance')->from('ledgers')->where('group_id', $group_id);
        	$ledgers_q = $CI->db->get();
        	$ledger_result = $ledgers_q->result();
        	foreach($ledger_result as $row2)
        	{
	                $ledger_id = $row2->id;
        	        $ledger_name = $row2->name;
                	$op_balance = $row2->op_balance;
			$CI->db->select('id, amount, dc')->from('entry_items')->where('ledger_id', $ledger_id);
                	$entry_items_q = $CI->db->get();
                	$entry_items_result = $entry_items_q->result();
                	foreach ($entry_items_result as $row5)
                	{
                		if($row5->dc == 'C')
                		{
                		$cr_amount = $cr_amount + $row5->amount;
                		}
                		else
                		{
                		$dr_amount = $dr_amount + $row5->amount;
                		}
                	}//foreach
			
			//Adding opening balance for the ledger head.
	        	$op_balance = $row2->op_balance;
			$cl_balance = $op_balance+$dr_amount-$cr_amount;
			$net_total = ($op_balance + $dr_amount) - $cr_amount;
			}
			$total = $op_balance . "#" .  $dr_amount . "#" . $cr_amount . "#" . $cl_balance . "#" . $net_total;
			return $total; 

	}//fixedgroup

	function fixed_assetledg($ledg_id)
	{

		$cr_amount = 0.00;
                $dr_amount = 0.00;
                $op_balance = "";
                $cl_balance = "";
		$net_total = "";
                $CI = & get_instance();
                $CI->db->select('id,name,code,op_balance')->from('ledgers')->where('id', $ledg_id);
                $ledgers_q = $CI->db->get();
                $ledger_result = $ledgers_q->result();
                foreach($ledger_result as $row2)
                {
                        $ledger_id = $row2->id;
                        $ledger_name = $row2->name;
                        $op_balance = $row2->op_balance;
                 	$CI->db->select('id, amount, dc')->from('entry_items')->where('ledger_id', $ledger_id);
                        $entry_items_q = $CI->db->get();
                        $entry_items_result = $entry_items_q->result();
			foreach ($entry_items_result as $row5)
                        {
                        	if($row5->dc == 'C')
                        	{
                        	$cr_amount = $cr_amount + $row5->amount;
                        	}
                        	else
                        	{
                        	$dr_amount = $dr_amount + $row5->amount;
                        	} 
			}//foreach

                //Adding opening balance for the ledger head.
                $op_balance = $row2->op_balance;
                $cl_balance = $op_balance+$dr_amount-$cr_amount;
		$net_total = ($row2->op_balance + $dr_amount) - $cr_amount;
                }
		$total = $op_balance . "#" .  $dr_amount . "#" . $cr_amount . "#" . $cl_balance .  "#" .$net_total;
                return $total; 

	}//fixed for children

	function get_dep_value($group_id)
	{
		$dep_op_value = "";
                $current_dep_value = "";
                $total_dep = 0.00;

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

                        $CI->db->select('asset_name,cost,depreciated_value,current_value')->from('new_asset_register')->where('asset_name', $ledger_name);
                        $asset = $CI->db->get();
                        $asset_result = $asset->result();
                        foreach ($asset_result as $row4)
                        {
                                $asset_name = $row4->asset_name;
                                $dep_op_value = $row4->depreciated_value;
                                $new_current_value = $row4->current_value;
                                $asset_cost = $row4->cost; 

			//Adding opening balance for the ledger head.
                	$current_dep_value = $old_current_value + $new_current_value;
                	$total_dep = $dep_op_value + $current_dep_value;
                }//foreach old_asset
                }//foreach new_asset
		}
                $total = $dep_op_value . "#" . $current_dep_value . "#" .  $total_dep;
                return $total;
		
	}

	 function get_dep_value_ledg($ledg_id)
        {
                $dep_op_value1 = "";
                $current_dep_value1 = "";
                $total_dep1 = 0.00;

                $CI = & get_instance();
                $CI->db->select('id,name,code')->from('ledgers')->where('group_id', $ledg_id);
                $ledgers_q1 = $CI->db->get();
                $ledger_result1 = $ledgers_q1->result();
                foreach($ledger_result1 as $rowled)
                {
                        $ledg_id1 = $rowled->id;
                        $ledg_name1 = $rowled->name;
                        $CI->db->select('asset_name,cost,depreciated_value,current_value')->from('old_asset_register')->where('asset_name', $ledg_name1);
                        $asset1 = $CI->db->get();
                        $asset_result1 = $asset1->result();
                        foreach ($asset_result as $rowled1)
                        {
                                $asset_name1 = $rowled1->asset_name;
                                $dep_op_value1 = $rowled1->depreciated_value;
                                $old_current_value1 = $rowled1->current_value;
                                $asset_cost1 = $rowled1->cost;

                        $CI->db->select('asset_name,cost,depreciated_value,current_value')->from('new_asset_register')->where('asset_name', $ledg1_name);
                        $asset2 = $CI->db->get();
                        $asset_result2 = $asset2->result();
                        foreach ($asset_result2 as $rowled2)
                        {
                                $asset_name1 = $rowled2->asset_name;
                                $dep_op_value1 = $rowled2->depreciated_value;
                                $new_current_value1 = $rowled2->current_value;
                                $asset_cost1 = $rowled2->cost;

                        //Adding opening balance for the ledger head.
                        $current_dep_value1 = $old_current_value1 + $new_current_value1;
                        $total_dep1 = $dep_op_value1 + $current_dep_value1;
                }//foreach old_asset
                }//foreach new_asset
                }
                $total2 = $dep_op_value1 . "#" . $current_dep_value1 . "#" .  $total_dep1;
                return $total2;

        }




/*	function plan_sub_schedule($child_id)
	{
	//	echo "child_id======$child_id";
		$cr_amount = 0.00;
                $dr_amount = 0.00;
                $op_balance = "";
                $cl_balance = "";
                $dep_op_value = "";
                $current_dep_value = "";
		$ledg_plan_cr_total = 0;
                $ledg_plan_dr_total = 0;
                $ledg_nonplan_dr_total = 0;
               	$ledg_nonplan_cr_total = 0;
                $ledg_cr_total =0;
                $ledg_dr_total = 0;
                $ledg_plan_total = 0;
               	$ledg_non_plan_total = 0;
                $ledg_total = 0;

                $total_dep = "";
		$CI =& get_instance();
                $CI->db->select('id,name,code,op_balance')->from('ledgers')->where('group_id', $child_id);
                $ledgers_q = $CI->db->get();
                $ledger_result = $ledgers_q->result();
                foreach($ledger_result as $row2)
                {
                        $ledger_id = $row2->id;
                        $ledger_name = $row2->name;
                        $op_balance = $row2->op_balance;

			$CI->db->select('entry_items.amount as entry_items_amount, entry_items.dc as entry_items_dc,entry_items.id as entry_items_id,entries.sanc_type as sanc_type');
                    	$CI->db->from('entries')->join('entry_items', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id);
                    	$result11 =$CI->db->get();
                    	$entry_result = $result11->result();
                    	foreach($entry_result as $query_row)
                    	{
				$plan_dr_total = 0;
				$plan_cr_total = 0;
                        	$dc = $query_row->entry_items_dc;
                        	$sum = $query_row->entry_items_amount;
                        	$entry_item_id =$query_row->entry_items_id;
                        	$sanc_type = $query_row->sanc_type;

				if($sanc_type != "select")
                        	{
                            		if($sanc_type == "plan")
                            		{
                                		if($dc == "C")
                                		{
                                    		//$plan_cr_total = $paln_cr_total + $sum;
                                    		$ledg_plan_cr_total = $ledg_plan_cr_total + $sum;
                                		}elseif($dc == "D"){
                                    		//$plan_dr_total = $plan_dr_total + $sum;
                                    		$ledg_plan_dr_total = $ledg_plan_dr_total + $sum;
						}
					}//if
                                //}//if(!select)
				elseif($sanc_type == "non_plan")
				{
                                	if($dc == "C")
                                	{
                                    	//$nonplan_cr_total = $nonplan_cr_total + $sum;
                                    	$ledg_nonplan_cr_total = $ledg_nonplan_cr_total + $sum;
					echo "nonplan_dr====$ledg_nonplan_cr_total";
                                	}elseif($dc == "D"){
                                    	//$nonplan_dr_total = $nonplan_dr_total + $sum;
                                    	$ledg_nonplan_cr_total = $ledg_nonplan_cr_total + $sum;
					echo "nonplan_cr=====$ledg_nonplan_cr_total";
                                	}
                            	}//elseif
				}//if(!select)



			}//foreach

		}//foreach

	} */


/*	function newschedule_2($childg_id)
	{
		$op_balance = 0.00;
                $CI =& get_instance();
		$CI->db->select('id,name,code,op_balance')->from('ledgers')->where('group_id', $childg_id);
                $ledgers = $CI->db->get();
                $ledgers_q = $ledgers->result();
                foreach($ledgers_q as $ledger1)
                {
			$ledg_id = $ledger1->id;
			$ledg_name = $ledger1->name;
			$op_balance = $ledger1->op_balance;
		}
               	//echo "op_b====$this->op_balance"; 
                return $op_balance;
	}//function */


}//main
?>
