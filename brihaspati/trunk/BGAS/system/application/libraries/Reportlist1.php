<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');
class Reportlist1
{
	function Reportlist1()
	{
		return;
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

	function init_led($led_id)
	{
                $CI =& get_instance();
                $CI->db->from('ledgers')->where('id', $led_id)->limit(1);
                $ledger_q = $CI->db->get();
                if($ledger_q->num_rows() > 0)
		{
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
			$this->children_groups[$counter] = new Reportlist1();
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
		//this->cr_total = float_ops($this->cr_total, $CI->Ledger_model->get_cr_total1($row->id), '+');
	
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

	//Method for display New MHRD format-2015 @kanchan
        function new_mhrd($id)
        {
		$diff = $this->income_expense_diff();
                $result1 = explode('#', $diff);
                $diff_total = -($result1[0]); 
                $counter = 0;
                $sum = 0;
                $liability_total1 = 0;
                $CI =& get_instance();
                $CI->db->select('name,code,id,parent_id')->from('groups')->where('parent_id',$id);
                $main = $CI->db->get();
                $main_result= $main->result();
                if($id == 2)
                {
                        foreach($main_result as $row)
                        {
                                $name = $row->name;
                                $code =$row->code;
                                $ledg_id = $row->id;
                                $liability = new Reportlist1();
                                $liability->init($row->id);
                                $liability_total = $liability->total;
                                $sum = $sum + $liability_total;
                                $CI->load->model('investment_model');
                                $result = $CI->investment_model->merge_Funds();
                                $value = explode('#', $result);
                                $liability_totalA = $value[0];
				$liability_total1 = ($liability_totalA + $diff_total);
                                if($name == 'Corpus')
                                $name = 'Corpus/Capital Funds';
                        if(($code!=  '1005') && ($code!= '1001') &&  ($code!= '1006'))
                        {
			echo "<tr class=\"tr-group\" width=\"30%\">";
                        echo "<td class=\"td-group\" width=\"30%\">";
                        echo "&nbsp;" .  $name;
                        echo "</td>";
                        echo "<td class=\"td-group\">";
                        $counter++;
                        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" . anchor_popup('report/new_schedule/' . $code . '/' . $counter, $counter, array('title' => $name, 'style' => 'color:#000000;text-decoration:none;'));
                        echo "</td>";
                        if($name!= 'Corpus/Capital Funds')
                        {
                        echo "<td align=\"right\">" . convert_amount_dc($liability_total) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                        }else{
                        echo "<td align=\"right\">" . convert_amount_dc($liability_total1) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>"; 
                        }
			echo "</tr>";
                        }//ifcode 
                        }//foreach
                }//if(id==2)
			
                if($id == 1)
                {
                        $counter = 3;
                        foreach($main_result as $row)
			 {
                                $name = $row->name;
                                $code =$row->code;
                                $ledg_id = $row->id;
                                $parent_id = $row->parent_id;
                                $asset = new Reportlist1();
                                $asset->init($row->id);
                                $asset_total = $asset->total;
                                $sum = $sum + $asset_total;
                                if($name == 'Investments')
				$name = 'Investments From Earmarked / Endowments Funds';
                                echo "<tr class=\"tr-group\" width=\"30%\">";
                                echo "<td class=\"td-group\" width=\"30%\">";
                                echo "&nbsp;" .  $name;
                                echo "</td>";
                                echo "<td class=\"td-group\">";
                                $counter++;
                                echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" . anchor_popup('report/new_schedule/' . $code . '/' . $counter, $counter, array('title' => $name, 'style' => 'color:#000000;text-decoration:none;'));
                                echo "</td>";
                                echo "<td align=\"right\">" . convert_amount_dc($asset_total) . "</td>";
                                echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
				echo "</tr>";
  			        $CI->db->select('id,name,code')->from('groups')->where('parent_id',$ledg_id);
             			$main1 = $CI->db->get();
             			foreach ($main1->result() as $row1)
             			{
				$group_name =  $row1->name;
                		$group_id = $row1->id;
                		$group_code = $row1->code;
                		$asset = new Reportlist1();
                		$asset->init($row1->id);
                		$asset_total = $asset->total;
                		if($name == 'Fixed Assets')
               			{
                		echo "<tr class=\"tr-ledger\" width=\"30%\">";
                        	echo "<td class=\"td-ledger\" width=\"30%\">";
                        	echo "&nbsp;" .  $group_name;
                        	echo "</td>";
                      		echo "<td></td>";
                        	echo "<td></td>";
                        	echo "<td></td>";
				echo "</tr>";
                		}
               			if(($name!= 'Fixed Assets') && ($name!= 'Current Assets') && ($name!= 'Loans Advances and Deposits'))
                		{
                                if($group_name == 'Corpus Fund Investments')
                                {
                                	$group_name = 'Investments Others';
                                        $counter = 6;
                                        echo "<tr class=\"tr-group\" width=\"30%\">";
                                        echo "<td class=\"td-group\" width=\"30%\">";
                                        echo "&nbsp;" .  $group_name;
                                        echo "</td>";
                                        echo "<td>";
                                        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" . anchor_popup('report/new_schedule/' . $group_code . '/' . $counter, $counter, array('title' => $group_name, 'style' => 'color:#000000;text-decoration:none;'));
                                        echo "</td>";
                                        echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                                        echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
					echo "</tr>";
                                        }//if
					}//if(name!='')
                                }//group foreach   
			}//child foreach
		}
	$this->curr_total = $sum; 
	}

	function income_expense_diff()
	{
        $income = new Reportlist1();
        $income->init('3');
        $total = $income->total;
        $expense = new Reportlist1();
        $expense->init('4');
        $total1 = $expense->total;
        $total = 0 - $total;
        $diff = $total - $total1;
        return $diff;
	}
 

	function get_liabilityschedule($code,$count)
	{
		$profit = $this->income_expense_diff();
		$sum = 0;
		$sum1 = 0;
		$CI = & get_instance();
                //Get current label.
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
                $CI =& get_instance();
            	$CI->load->model('ledger_model');
            	$id = $CI->ledger_model->get_group_id($code);
                $parent = $CI->ledger_model->get_group_name($id);
                $CI->db->select('name,code,id')->from('ledgers')->where('group_id',$id);
                $ledger_detail = $CI->db->get();
                $ledger_result = $ledger_detail->result();
        	foreach($ledger_result as $row)
        	{
			$ledg_name = $row->name;
                        $ledg_id =$row->id;
                        //echo "ledg=====$ledg_name";
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo "&nbsp;" .  $ledg_name;
                        echo "</td>";
                        $CI->load->model('investment_model');
                        $result = $CI->investment_model->newschedule1($ledg_id);
                        $value = explode('#',$result);
                        $dr_total1 = $value[0];
                        $sum = $sum+$dr_total1;
                        //echo "sum==$sum";
                        $cr_total1 = $value[1];
                        $sum1 = $sum1+$cr_total1;
                        //$total = float_ops($total, $pandl, '+');

                        //$profit = $this->income_expense_diff();
                        if($ledg_name == 'Balance of net income/expenditure transferred from I/E Account')
                        {
                        if($profit < 0)
                        echo "<td align=\"right\">" . convert_amount_dc(-$profit) . "</td>";
                        else
                        echo "<td align=\"right\">" . convert_amount_dc(+$dr_total1) . "</td>";
                        if($profit > 0 )
                        echo "<td align=\"right\">" . convert_amount_dc(-$profit) . "</td>";
                        else
                        echo "<td align=\"right\">" . convert_amount_dc(-$cr_total1) . "</td>";
                        }else{
                        echo "<td align=\"right\">" . convert_amount_dc(+$dr_total1) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(-$cr_total1) . "</td>";
                        }
                        echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";

                }//foreach
                $this->profit1 = $profit;
                $this->dr_total1 = $sum;
		$this->cr_total1 = $sum1;
	}
	
	function get_Assetschedule($code,$count)
        {
                $sum = 0;
		$counter = 1;
		$str1 = 'a';
                $CI = & get_instance();
                $CI->load->model('group_model');
                $id = $CI->group_model->get_group_id($code);
		$name = $CI->group_model->get_group_name($id);

	/*	if($name == 'Endowment Fund Investments')
		{
		$counter = 7;
		} */
                $CI->db->select('name,code,id')->from('groups')->where('parent_id',$id);
                $group_detail = $CI->db->get();
                $group_result = $group_detail->result();
                foreach($group_result as $row)
                {
                        $group_name = $row->name;
                        $group_id =$row->id;
			$group_code = $row->code;
			$asset = new Reportlist1();
                        $asset->init($row->id);
                        $asset_total = $asset->total;
                        $sum = $sum + $asset_total;
			echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
			echo "&nbsp;&nbsp;";
			echo $counter;
                        echo ".&nbsp;";
                        $counter++;

			if($group_name == 'Cash in Hand')
                        echo "&nbsp;" . anchor_popup('report/new_sub_schedule/' . $row->id . '/' . $row->name, $row->name, array('title' => $row->name, 'style' => 'color:#000000;text-decoration:none;font-weight:bold;')) . "(Subschedule)";
                        elseif($group_name == 'Corpus Fund Investments')
			echo "&nbsp;" . anchor_popup('report/new_sub_schedule/' . $row->id . '/' . $row->name, $row->name, array('title' => $row->name, 'style' => 'color:#000000;text-decoration:none;font-weight:bold;')) . "(Subschedule)";
			else
                        echo "&nbsp;" .  $group_name;
                        echo "</td>";
			if($name!= 'Investments')
			{
		 	echo "<td align=\"right\" colspan=\"2\">" . convert_amount_dc($asset_total) . "</td>";
			echo "<td align=\"right\" colspan=\"2\">" . convert_amount_dc(0) . "</td>";
			}else{
			echo "<td align=\"right\" colspan=\"2\"></td>";
                        echo "<td align=\"right\" colspan=\"2\"></td>";
			}
			$CI->load->model('ledger_model');
                	$CI->db->select('name,code,id')->from('ledgers')->where('group_id',$row->id);
                	$ledger_detail = $CI->db->get();
                	$ledger_result = $ledger_detail->result();
                	foreach($ledger_result as $row1)
                	{
                        $ledg_name = $row1->name;
                        $ledg_id =$row1->id;
                        echo "<tr class=\"tr-ledger\">";
                        echo "<td class=\"td-ledger\">";
			echo $str1;
			echo ')';
			$str1++;
                        echo "&nbsp;" .  $ledg_name;
                        echo "</td>";
			$total = $CI->ledger_model->get_balancesheet_ledger_balance($ledg_id);
			echo "<td align=\"right\" colspan=\"2\">" . convert_amount_dc($total) . "</td>";
			echo "<td align=\"right\" colspan=\"2\">" . convert_amount_dc(0) . "</td>";
		}//if
		$CI->db->select('name,code,id')->from('groups')->where('parent_id',$group_id);
                $children_groupdetail = $CI->db->get();
                $children_groupresult = $children_groupdetail->result();
                foreach($children_groupresult as $row2)
                {
                        $children_groupname = $row2->name;
                        $children_groupid =$row2->id;
			$asset = new Reportlist1();
                        $asset->init($row2->id);
                        $childasset_total = $asset->total;
			if($name!= 'Investments')
			{
                        $sum = $sum + $childasset_total;
			}
			//if($children_groupname == 'Stock in Hand')
			//{
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
			echo $str1;
			echo ')';
			$str1++;
                        echo "&nbsp;" .  $children_groupname;
                        echo "</td>";
			echo "<td align=\"right\" colspan=\"2\">". convert_amount_dc($childasset_total) . "</td>";
                        echo "<td align=\"right\" colspan=\"2\">". convert_amount_dc(0) . "</td>";
			if($children_groupname == 'Stock in Hand')
                        {
			$CI->db->select('name,code,id')->from('ledgers')->where('group_id',$children_groupid);
                	$children_ledgerdetail = $CI->db->get();
                	$children_ledgerresult = $children_ledgerdetail->result();
				foreach($children_ledgerresult as $ledger_row)
	                	{
                        	$children_ledgername = $ledger_row->name;
                        	$children_ledgerid =$ledger_row->id;
                        	echo "<tr class=\"tr-ledger\">";
                        	echo "<td class=\"td-ledger\">";
                        	echo "&nbsp;" .  $children_ledgername;
                        	echo "</td>";
				//$total = $CI->ledger_model->get_balancesheet_ledger_balance($children_ledgerid);
                        	echo "<td align=\"right\" colspan=\"2\"></td>";
                        	echo "<td align=\"right\" colspan=\"2\"></td>";

				}
			}//if
		}//childrengroup
		//}//ifname=='Ear And Endo'
		}//foreach group
		$this->curr_total1 = $sum;
		
	}//function get_Assetsch

	function sub_schedule7($code)
	{
		$counter = 1;
		$count1 = 1;
		$sum = 0;
		$CI =& get_instance();
		$CI->load->model('group_model');
		$id = $CI->group_model->get_group_id($code);
		$CI->db->select('id,name,code')->from('groups')->where('parent_id', $id);
		$group_detail = $CI->db->get();
                $group_result = $group_detail->result();
                foreach($group_result as $row)
                {
                        $group_id =$row->id;
			$group_name = $row->name;
			$group_code = $row->code;

			if($group_name == 'Cash in Hand')
			{
			$group_name = 'Savings Bank Accounts';
                        echo "<tr class=\"tr-ledger\" colspan=\"2\">";
                        echo "<td class=\"td-ledger\" colspan=\"2\">";
                        echo $this->numberToRoman($count1);
                        echo ".";
                        $count1++;
                        echo "<b>&nbsp;$group_name</b>";
                        echo "</td>";
			$asset = new Reportlist1();
                        $asset->init($group_id);
                        $total = $asset->total;
                        $sum = $sum+$total;
                        //echo "<td align=\"right\" colspan=\"9\">". convert_amount_dc($total) . "</td>";


				$CI->db->select('id,name,code')->from('groups')->where('parent_id', $group_id);
	                	$child_detail = $CI->db->get();
                		$child_result = $child_detail->result();
                		foreach($child_result as $row1)
                		{
                        	$child_id =$row1->id;
                        	$child_name = $row1->name;
                        	$CI->db->select('name,code,id')->from('ledgers')->where('group_id',$row1->id);
                        	$ledg_detail = $CI->db->get();
                        	$ledg_result = $ledg_detail->result();
                        	foreach($ledg_result as $row2)
                        	{
                        	$ledger_id =$row2->id;
                        	$ledger_name =$row2->name; 
				$asset = new Reportlist1();
				$asset->init_led($row2->id);
				$total = $asset->total;
				//$sum = $sum+$total;
                        	echo "<tr class=\"tr-ledger\" colspan=\"15\">";
                        	echo "<td class=\"td-ledger\" colspan=\"15\">";
                        	echo $counter;
				echo ".";
                        	$counter++;
                        	echo "&nbsp;" .  $ledger_name;
				echo "</td>";
				echo "<td align=\"right\" colspan=\"9\">". convert_amount_dc($total) . "</td>";
				}//foreach ledg
			}//if
		}//foreach childgroup
		elseif(($group_name!= 'Sundry Debtors') && ($group_name!= 'Imprest') && ($group_name!= 'Postage on Hand') && ($group_name!= 'Post Office') && ($group_name!= 'Other Current Assets')){
                        echo "<tr class=\"tr-ledger\" colspan=\"15\">";
                        echo "<td class=\"td-ledger\" colspan=\"15\">";
			echo $this->numberToRoman($count1);
                        echo ".";
                        $count1++;
			if($group_name == 'Bank')
			$group_name = 'Current Account';
                        echo "<b>&nbsp;$group_name</b>";
                        echo "</td>";
                        $asset = new Reportlist1();
                        $asset->init($group_id);
                        $total = $asset->total;
                        $sum = $sum+$total;
                        echo "<td align=\"right\" colspan=\"9\">". convert_amount_dc($total) . "</td>";

			if($group_name == 'Current Account')
			{
			$CI->db->select('name,code,id')->from('ledgers')->where('group_id',$row->id);
                        $ledg_detail1 = $CI->db->get();
                        $ledg_result1 = $ledg_detail1->result();
                        foreach($ledg_result1 as $row2)
                        {
                                $ledger_id =$row2->id;
                                $ledger_name =$row2->name;
                                $asset = new Reportlist1();
                                $asset->init_led($row2->id);
                                $total = $asset->total;
                             //   $sum = $sum+$total;
                                echo "<tr class=\"tr-ledger\" colspan=\"15\">";
                                echo "<td class=\"td-ledger\" colspan=\"15\">";
                                echo $counter;
                                echo ".";
                                $counter++;
                                echo "&nbsp;" .  $ledger_name;
                                echo "</td>";
                                echo "<td align=\"right\" colspan=\"9\">". convert_amount_dc($total) . "</td>";
			}
			} 
                 }//else 

		}//foreach group
		$this->curr_total = $sum;
	}

	

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
	}

    


	function FixedAsset_A($code,$count)
	{
		  //echo "count====$count";
                $sum = 0;
                $sum1 = 0;
                $sum2= 0;
                $sum3 = 0;
                $sum4 = 0;
                $sum5 = 0;
                $sum6 = 0;
                $sum7 = 0;
                $counter = 1;
                $net_total ="";
                $current_year_value = 0.00;
                $CI = & get_instance();
                //Get current label.
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

                $CI =& get_instance();
                $CI->load->model('group_model');
                $id = $CI->group_model->get_group_id($code);
                $CI->db->select('name,code,id')->from('groups')->where('parent_id',$id);
                $group_detail = $CI->db->get();
                foreach($group_detail->result() as $row)
                {
                        $g_id =$row->id;
                        $CI->db->select('name,code,id')->from('groups')->where('parent_id',$g_id);
                        $child_detail = $CI->db->get();
                        foreach($child_detail->result() as $row1)
                        {
                        	$group_id =$row1->id;
                        	$group_name = $row1->name;
                        	$CI->load->model('newschedules_model');
                        	$value = $CI->newschedules_model->fixed_asset($group_id);
                        	$opening_bal = $value[0];
				$opening_bal_dc = $value[1];
                        	$sum = $sum + $opening_bal;
              		        $dr_total = $value[2];
                        	$sum1 = $sum1 + $dr_total;
                     		$cr_total = $value[3];
                        	$sum2 = $sum2 + $cr_total;
               		        $closing_bal = $value[4];
                        	$sum3 = $sum3 + $closing_bal;

         	                $result1 = $CI->newschedules_model->get_old_asset_depvalue($group_id);
                        	$dep_op_balance = $result1[0];
                        	$sum4 = $sum4 + $dep_op_balance;
				$old_dep_amount = $result1[1];
				
				$result2 = $CI->newschedules_model->get_new_asset_depvalue($group_id);
                        	$new_dep_amount = $result2[1];

			//Adding opening balance for the ledger head.
                	$current_depreciation = ($old_dep_amount + $new_dep_amount);
			$sum5 = $sum5 + $current_depreciation;
			$total_depreciation = $dep_op_balance + $current_depreciation;
			$sum6 = $sum6 + $total_depreciation;
			$net_total = ($opening_bal + $dr_total) - $cr_total;
			$sum7 = $sum7 + $net_total;
			$current_year_value = $net_total;
			
                        if(($group_id!= 148) && ($group_id!= 149))
                        {
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo $counter;
                        $counter++;
                        echo "</td>";
			echo "<td>";
                        if($group_name == 'Others Fixed Assets')
                        echo "&nbsp;&nbsp;&nbsp;&nbsp;" . anchor_popup('report/new_sub_schedule/' . $row1->id . '/' . $row1->name, $row1->name, array('title' => $row1->name, 'style' => 'color:#000000;text-decoration:none;font-weight:bold;')) . "(Subschedule)";
                        elseif($group_name == 'Land')
                        echo "&nbsp;&nbsp;&nbsp;&nbsp;" . anchor_popup('report/new_sub_schedule/' . $row1->id . '/' . $row1->name, $row1->name, array('title' => $row1->name, 'style' => 'color:#000000;text-decoration:none;font-weight:bold;')) . "(Subschedule)";
                        else
                        echo "&nbsp;" .  $group_name;
                        echo "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc($opening_bal) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc($dr_total) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(-$cr_total) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc($closing_bal) . "</td>";
			if($dep_op_balance > 0){
                        echo "<td align=\"right\">" . convert_amount_dc(+$dep_op_balance) . "</td>";
			}else{
			echo "<td align=\"right\">" . convert_amount_dc(-$dep_op_balance) . "</td>";
			}
                        echo "<td align=\"right\">" . convert_amount_dc(+$current_depreciation) . "</td>";
                        echo "<td align=\"right\" width=\"9%\">";
                        echo "0.00";
                        echo "</td>";
                        echo "<td align=\"center\">";
                        echo money_format('%!i', convert_cur($total_depreciation));
                        echo "</td>";
			if($current_year_value > 0){
                        echo "<td align=\"right\">" . convert_amount_dc($current_year_value) . "</td>";
			}else{
			echo "<td align=\"right\">" . convert_amount_dc($current_year_value) . "</td>";
			}
                        echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>"; 
                        }//ifcondition
                    }//childgroup 
                }//foreach group  
                $this->opening_balance = $sum;
                $this->debit_total = $sum1;
                $this->credit_total = $sum2;
                $this->closing_balance = $sum3;
                $this->dep_opening_balance = $sum4;
                $this->current_depreciation_amount = $sum5;
                $this->total_depreciation = $sum6;
                $this->curr_amount = $sum7; 
        }

	function FixedAsset_B($code,$count)
	{
		$sum = 0;
                $sum1 = 0;
                $sum2 = 0;
                $sum3 = 0;
                $sum4 = 0;
                $sum5 = 0;
                $sum6 = 0;
                $sum7 = 0;
                $counter = 5;
                $net_total = 0;
                $CI = & get_instance();
                //Get current label.
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

                $CI =& get_instance();
                $CI->db->select('name,code,id')->from('groups')->where('id',149);
                $group_detail = $CI->db->get();
                $group_result = $group_detail->row();
                $group_id =$group_result->id;
                $group_name = $group_result->name;

                $CI->load->model('newschedules_model');
                $value = $CI->newschedules_model->fixed_asset($group_id, $count);
		$opening_bal = $value[0];
                $opening_bal_dc = $value[1];
                $sum = $sum + $opening_bal;
                $dr_total = $value[2];
               	$sum1 = $sum1 + $dr_total;
                $cr_total = $value[3];
                $sum2 = $sum2 + $cr_total;
                $closing_bal = $value[4];
                $sum3 = $sum3 + $closing_bal;

                $result1 = $CI->newschedules_model->get_old_asset_depvalue($group_id);
		$dep_op_balance = $result1[0];
                $sum4 = $sum4 + $dep_op_balance;
                $old_dep_amount = $result1[1];

		$result2 = $CI->newschedules_model->get_new_asset_depvalue($group_id);
		$new_dep_amount = $result2[1];

                        //Adding opening balance for the ledger head.
                        $current_depreciation = ($old_dep_amount + $new_dep_amount);
                        $sum5 = $sum5 + $current_depreciation;
                        $total_depreciation = $dep_op_balance + $current_depreciation;
                        $sum6 = $sum6 + $total_depreciation;
                        $net_total = ($opening_bal + $dr_total) - $cr_total;
                        $sum7 = $sum7 + $net_total;
                        $current_year_value = $net_total;

                	echo "<tr class=\"tr-group\">";
                	echo "<td class=\"td-group\" width=\"40\">";
                	echo $counter;
                	echo "</td>";
			if($count == "4"){
                	echo "<td class=\"td-group\" width=\"225\">";
                	if($group_name == 'Capital Work-In-Progress')
                	echo "&nbsp;&nbsp;&nbsp;&nbsp;" . anchor_popup('report/new_sub_schedule/' . $group_id . '/' .  $group_name, $group_name, array('title' => $group_name, 'style' => 'color:#000000;text-decoration:none;font-weight:bold;')) . "(Subschedule)";
                	else
                	echo "&nbsp;" .  $group_name;
                	echo '<b>(B)</b>';
                	echo "</td>";
			}else{
			echo "<td class=\"td-group\" width=\"225\">";
			echo "&nbsp;" .  $group_name;
                        echo "</td>";

			}
			
			echo "<td align=\"right\">" . convert_amount_dc($opening_bal) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc($dr_total) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(-$cr_total) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc($closing_bal) . "</td>";
			if($dep_op_balance > 0){
                        echo "<td align=\"right\">" . convert_amount_dc(+$dep_op_balance) . "</td>";
			}else{
			echo "<td align=\"right\">" . convert_amount_dc(-$dep_op_balance) . "</td>";
			}
                        echo "<td align=\"right\">" . convert_amount_dc(+$current_depreciation) . "</td>";
                        echo "<td align=\"right\" width=\"9%\">";
                        echo "0.00";
                        echo "</td>";
                        echo "<td align=\"center\">";
                        echo money_format('%!i', convert_cur($total_depreciation));
                        echo "</td>";
			if($current_year_value > 0){
                        echo "<td align=\"right\">" . convert_amount_dc($current_year_value) . "</td>";
			}else{
			echo "<td align=\"right\">" . convert_amount_dc($current_year_value) . "</td>";
			}
                        echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>"; 

                echo "</tr>";
                $this->opening_balance = $sum;
                $this->debit_total = $sum1;
                $this->credit_total = $sum2;
                $this->closing_balance = $sum3;
                $this->dep_opening_balance = $sum4;
                $this->current_depreciation_amount = $sum5;
                $this->total_depreciation = $sum6;
                $this->curr_amount = $sum7;
        }
	function FixedAsset_C($code,$count)
	{
		 $sum = 0;
                $sum1 = 0;
                $sum2 = 0;
                $sum3 = 0;
                $sum4 = 0;
                $sum5 = 0;
                $sum6 = 0;
                $sum7 = 0;
                $net_total = 0;

                $counter = 19;
                $CI =& get_instance();
                //$id = $CI->group_model->get_group_id($code);
                $CI->db->select('name,code,id')->from('ledgers')->where('group_id',152);
                $ledger_detail = $CI->db->get();
                foreach($ledger_detail->result() as $row)
                {
                	$ledg_id =$row->id;
                	$ledg_name = $row->name;
                	$CI->load->model('newschedules_model');
                	$result = $CI->newschedules_model->fixed_assetledg($ledg_id);
			$opening_bal = $result[0];
			$opening_bal_dc = $result[1];
                        $sum = $sum + $opening_bal;
              		$dr_total = $result[2];
                        $sum1 = $sum1 + $dr_total;
                     	$cr_total = $result[3];
                        $sum2 = $sum2 + $cr_total;
               		$closing_bal = $result[4];
                        $sum3 = $sum3 + $closing_bal;

         	        $result1 = $CI->newschedules_model->get_old_asset_depvalue1($ledg_id);
                        $dep_op_balance = $result1[0];
                        $sum4 = $sum4 + $dep_op_balance;
			$old_dep_amount = $result1[1];
				
			$result2 = $CI->newschedules_model->get_new_asset_depvalue1($ledg_id);
                        $new_dep_amount = $result2[1];

			//Adding opening balance for the ledger head.
                	$current_depreciation = ($old_dep_amount + $new_dep_amount);
			$sum5 = $sum5 + $current_depreciation;
			$total_depreciation = $dep_op_balance + $current_depreciation;
			$sum6 = $sum6 + $total_depreciation;
			$net_total = ($opening_bal + $dr_total) - $cr_total;
			$sum7 = $sum7 + $net_total;
			$current_year_value = $net_total;

                	echo "<tr class=\"tr-group\" colspan=\"2\">";
                	echo "<td class=\"td-group\">";
                	echo $counter;
                	$counter++;
                	echo "</td>";
                	echo "<td class=\"td-group\" width=\"225\">";
                	echo "&nbsp;" .  $ledg_name;
                	echo "</td>"; 

			echo "<td align=\"right\">" . convert_amount_dc($opening_bal) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc($dr_total) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(-$cr_total) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc($closing_bal) . "</td>";
			if($dep_op_balance > 0){
                        echo "<td align=\"right\">" . convert_amount_dc(+$dep_op_balance) . "</td>";
			}else{
			echo "<td align=\"right\">" . convert_amount_dc(-$dep_op_balance) . "</td>";
			}
                        echo "<td align=\"right\">" . convert_amount_dc(+$current_depreciation) . "</td>";
                        echo "<td align=\"right\" width=\"9%\">";
                        echo "0.00";
                        echo "</td>";
                        echo "<td align=\"center\">";
                        echo money_format('%!i', convert_cur($total_depreciation));
                        echo "</td>";
			if($current_year_value > 0){
                        echo "<td align=\"right\">" . convert_amount_dc($current_year_value) . "</td>";
			}else{
			echo "<td align=\"right\">" . convert_amount_dc($current_year_value) . "</td>";
			}
                        echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>"; 
			
                echo "</tr>";
                }//foreach 
                $this->opening_balance = $sum;
                $this->debit_total = $sum1;
                $this->credit_total = $sum2;
                $this->closing_balance = $sum3;
                $this->dep_opening_balance = $sum4;
                $this->current_depreciation_amount = $sum5;
                $this->total_depreciation = $sum6;
		$this->curr_amount = $sum7; 
        }

	function Fixed_Asset_Others($code,$count)
	{
		$counter = 1;
		$sum = 0;
		$sum1 = 0;
		$sum2 = 0;
		$sum3 = 0;
		$sum4 = 0;
		$sum5 = 0;
		$sum6 = 0;
		$sum7 = 0;

		$CI = & get_instance();
                //Get current label.
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
		$group_id = $CI->ledger_model->get_group_id($code);
		$CI->db->select('id,name')->from('ledgers')->where('group_id', $group_id);
		$query_r = $CI->db->get();
		
		foreach($query_r->result() as $row)
                {
                        $ledger_id = $row->id;
                        $ledger_name = $row->name;
			echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo $counter;
                        $counter++;
                        echo "</td>";
			echo "<td>";
                        echo "&nbsp;" .  $ledger_name;
                        echo "</td>";
			$CI->load->model('newschedules_model');
                	$result = $CI->newschedules_model->fixed_assetledg($ledger_id);
			$opening_bal = $result[0];
			$opening_bal_dc = $result[1];
                        $sum = $sum + $opening_bal;
              		$dr_total = $result[2];
                        $sum1 = $sum1 + $dr_total;
                     	$cr_total = $result[3];
                        $sum2 = $sum2 + $cr_total;
               		$closing_bal = $result[4];
                        $sum3 = $sum3 + $closing_bal;

         	        $result1 = $CI->newschedules_model->get_old_asset_depvalue1($ledger_id);
                        $dep_op_balance = $result1[0];
                        $sum4 = $sum4 + $dep_op_balance;
			$old_dep_amount = $result1[1];
				
			$result2 = $CI->newschedules_model->get_new_asset_depvalue1($ledger_id);
                        $new_dep_amount = $result2[1];

			//Adding opening balance for the ledger head.
                	$current_depreciation = ($old_dep_amount + $new_dep_amount);
			$sum5 = $sum5 + $current_depreciation;
			$total_depreciation = $dep_op_balance + $current_depreciation;
			$sum6 = $sum6 + $total_depreciation;
			$net_total = ($opening_bal + $dr_total) - $cr_total;
			$sum7 = $sum7 + $net_total;
			$current_year_value = $net_total;  

			echo "<td align=\"right\">" . convert_amount_dc($opening_bal) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc($dr_total) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(-$cr_total) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc($closing_bal) . "</td>";
			if($dep_op_balance > 0){
                        echo "<td align=\"right\">" . convert_amount_dc(+$dep_op_balance) . "</td>";
			}else{
			echo "<td align=\"right\">" . convert_amount_dc(-$dep_op_balance) . "</td>";
			}
                        echo "<td align=\"right\">" . convert_amount_dc(+$current_depreciation) . "</td>";
                        echo "<td align=\"right\" width=\"9%\">";
                        echo "0.00";
                        echo "</td>";
                        echo "<td align=\"center\">";
                        echo money_format('%!i', convert_cur($total_depreciation));
                        echo "</td>";
			if($current_year_value > 0){
                        echo "<td align=\"right\">" . convert_amount_dc($current_year_value) . "</td>";
			}else{
			echo "<td align=\"right\">" . convert_amount_dc($current_year_value) . "</td>";
			}
                        echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>"; 
			
                echo "</tr>";

                }//foreach 
                $this->opening_balance1 = $sum;
                $this->debit_total1 = $sum1;
                $this->credit_total1 = $sum2;
                $this->closing_balance1 = $sum3;
                $this->dep_opening_balance1 = $sum4;
                $this->current_depreciation_amount1 = $sum5;
                $this->total_depreciation1 = $sum6;
		$this->curr_amount1 = $sum7; 

	}

	function Plan_Fixed_Sub_ScheduleA($code,$count)
	{
		$counter = 1;
		$cr_plan_total = 0;
		$dr_plan_total = 0;
		$closing_sum = 0;
		$sum1 = 0;
		$sum2 = 0;
		$sum3 = 0;
		$sum4 = 0;
		$sum5 = 0;
		$sum6 = 0;
		$sum7 = 0;
		$current_dep_value4a = 0;
		$total_dep4a = 0;
		$dep_opening_value = 0;
		
		$CI =& get_instance();
                $CI->load->model('group_model');
                $id = $CI->group_model->get_group_id($code);
                $CI->db->select('name,code,id')->from('groups')->where('parent_id',$id);
                $group_detail = $CI->db->get();
                foreach($group_detail->result() as $row)
                {
                        $g_id =$row->id;
                        $CI->db->select('name,code,id')->from('groups')->where('parent_id',$g_id);
                        $child_detail = $CI->db->get();
                        foreach($child_detail->result() as $row1)
                        {
                        $group_id =$row1->id;
                        $group_name = $row1->name;
			$CI->load->model('newschedules_model');
	                $result4a = $CI->newschedules_model->plan_sub_schedule4($group_id);
			$opening_balance = $result4a[0];
			$sum1 = $sum1 + $opening_balance;
			$opening_bal_dc = $result4a[1];
                	$plan_dr_amount = $result4a[2];
			$dr_plan_total = $dr_plan_total + $plan_dr_amount;
			$plan_cr_amount = $result4a[3];
			$cr_plan_total = $cr_plan_total + $plan_cr_amount;
			$closing_bal = $result4a[6];
                        $sum2 = $sum2 + $closing_bal;

			$dep1 = $CI->newschedules_model->get_old_asset_depvalue($group_id);
                        $dep_op_balance = $dep1[2];
			$dep_opening_value  = $dep_opening_value + $dep_op_balance;
			$old_dep_amount = $dep1[3];
				
			$dep2 = $CI->newschedules_model->get_new_asset_depvalue($group_id);
                        $new_dep_amount = $dep2[3];

			//Adding opening balance for the ledger head.
                	$current_depreciation = ($old_dep_amount + $new_dep_amount);
			$sum3 = $sum3 + $current_depreciation;
			$total_depreciation = $dep_op_balance + $current_depreciation;
			$sum4 = $sum4 + $total_depreciation;
			$net_total = ($opening_balance + $plan_dr_amount) - $plan_cr_amount;
			$sum5 = $sum5 + $net_total;
			$current_year_value = $net_total;

                        if(($group_id!= 148) && ($group_id!= 149) && ($group_id!= 151))
                        {
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo $counter;
                        $counter++;
                        echo "</td>";
			echo "<td class=\"td-group\" width=\"225\">";
                	echo "&nbsp;" .  $group_name;
                	echo "</td>";

			echo "<td align=\"right\">" . convert_amount_dc($opening_balance) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc($plan_dr_amount) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(-$plan_cr_amount) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc($closing_bal) . "</td>";
			if($dep_op_balance > 0){
                        echo "<td align=\"right\">" . convert_amount_dc(+$dep_op_balance) . "</td>";
			}else{
			echo "<td align=\"right\">" . convert_amount_dc(-$dep_op_balance) . "</td>";
			}
                        echo "<td align=\"right\">" . convert_amount_dc(+$current_depreciation) . "</td>";
                        echo "<td align=\"right\" width=\"9%\">";
                        echo "0.00";
                        echo "</td>";
                        echo "<td align=\"center\">";
                        echo money_format('%!i', convert_cur($total_depreciation));
                        echo "</td>";
			if($current_year_value > 0){
                        echo "<td align=\"right\">" . convert_amount_dc($current_year_value) . "</td>";
			}else{
			echo "<td align=\"right\">" . convert_amount_dc($current_year_value) . "</td>";
			}
                        echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";

		 	}//ifcondition
                    }//childgroup
                }//foreach group
		$this->opening_bal1 = $sum1;
		$this->cr_plan_total1 = $cr_plan_total;
		$this->dr_plan_total1 = $dr_plan_total;
		$this->closing_sum1 = $sum2;
		$this->dep_opening = $dep_opening_value;
		$this->current_dep_total = $sum3;
		$this->total_depreciation = $sum4;
		$this->curr_amount = $sum5;

	}

	function Plan_Fixed_Sub_ScheduleB($id)
	{
		$counter = 17;
		$cr_plan_total = 0;
		$dr_plan_total = 0;
		$closing_sum = 0;
		$sum1 = 0;
		$sum2 = 0;
		$sum3 = 0;
		$sum4 = 0;
		$sum5 = 0;
		$sum6 = 0;
		$sum7 = 0;
		$current_dep_value4a = 0;
		$total_dep4a = 0;
		$dep_opening_value = 0;

		$CI =& get_instance();
                $CI->db->select('name,code,id')->from('groups')->where('id',$id);
                $group_detail = $CI->db->get();
                $group_result = $group_detail->row();
                $group_id =$group_result->id;
                $group_name = $group_result->name;

                echo "<tr class=\"tr-group\">";
                echo "<td class=\"td-group\" width=\"40\">";
                echo $counter;
                echo "</td>";
		echo "<td class=\"td-group\" width=\"225\">";
                echo "&nbsp;" .  $group_name . "<b>(B)</b>";
                echo "</td>";

		 	$result4a = $CI->newschedules_model->plan_sub_schedule4($group_id);
			$opening_balance = $result4a[0];
			$sum1 = $sum1 + $opening_balance;
			$opening_bal_dc = $result4a[1];
                	$plan_dr_amount = $result4a[2];
			$dr_plan_total = $dr_plan_total + $plan_dr_amount;
			$plan_cr_amount = $result4a[3];
			$cr_plan_total = $cr_plan_total + $plan_cr_amount;
			$closing_bal = $result4a[6];
                        $sum2 = $sum2 + $closing_bal;

			$dep1 = $CI->newschedules_model->get_old_asset_depvalue($group_id);
                        $dep_op_balance = $dep1[2];
			$dep_opening_value  = $dep_opening_value + $dep_op_balance;
			$old_dep_amount = $dep1[3];
				
			$dep2 = $CI->newschedules_model->get_new_asset_depvalue($group_id);
                        $new_dep_amount = $dep2[3];

			//Adding opening balance for the ledger head.
                	$current_depreciation = ($old_dep_amount + $new_dep_amount);
			$sum3 = $sum3 + $current_depreciation;
			$total_depreciation = $dep_op_balance + $current_depreciation;
			$sum4 = $sum4 + $total_depreciation;
			$net_total = ($opening_balance + $plan_dr_amount) - $plan_cr_amount;
			$sum5 = $sum5 + $net_total;
			$current_year_value = $net_total;

			echo "<td align=\"right\">" . convert_amount_dc($opening_balance) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc($plan_dr_amount) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(-$plan_cr_amount) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc($closing_bal) . "</td>";
			if($dep_op_balance > 0){
                        echo "<td align=\"right\">" . convert_amount_dc(+$dep_op_balance) . "</td>";
			}else{
			echo "<td align=\"right\">" . convert_amount_dc(-$dep_op_balance) . "</td>";
			}
                        echo "<td align=\"right\">" . convert_amount_dc(+$current_depreciation) . "</td>";
                        echo "<td align=\"right\" width=\"9%\">";
                        echo "0.00";
                        echo "</td>";
                        echo "<td align=\"center\">";
                        echo money_format('%!i', convert_cur($total_depreciation));
                        echo "</td>";
			if($current_year_value > 0){
                        echo "<td align=\"right\">" . convert_amount_dc($current_year_value) . "</td>";
			}else{
			echo "<td align=\"right\">" . convert_amount_dc($current_year_value) . "</td>";
			}
                        echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                echo "</tr>";
		
		$this->opening_bal2 = $sum1;
		$this->cr_plan_total2 = $cr_plan_total;
		$this->dr_plan_total2 = $dr_plan_total;
		$this->closing_sum2 = $sum2;
		$this->dep_opening2 = $dep_opening_value;
		$this->current_dep_total2 = $sum3;
		$this->total_depreciation2 = $sum4;
		$this->curr_amount2 = $sum5;
	}

	function Plan_Fixed_Sub_ScheduleC($id)
        {
                $counter = 18;
		$sum1 = 0;
		$sum2 = 0;
		$sum3 = 0;
		$sum4 = 0;
		$sum5 = 0;
		$dr_plan_total = 0;
		$cr_plan_total = 0;
		$dep_opening_value = 0;
                $CI =& get_instance();
                $CI->db->select('name,code,id')->from('ledgers')->where('group_id',$id);
                $ledger_detail = $CI->db->get();
                $ledger_result = $ledger_detail->result();
                foreach($ledger_result as $row)
                {
                	$ledg_id =$row->id;
                	$ledg_name = $row->name;
                	echo "<tr class=\"tr-group\" colspan=\"2\">";
               	 	echo "<td class=\"td-group\">";
                	echo $counter;
                	$counter++;
                	echo "</td>";
                	echo "<td class=\"td-group\" width=\"225\">";
                	echo "&nbsp;" .  $ledg_name;
                	echo "</td>";

			//$result4a = $CI->newschedules_model->plan_sub_schedule4($ledg_id);
			$result4a = $CI->newschedules_model->fixed_assetledg($ledg_id);
			$opening_balance = $result4a[0];
			$sum1 = $sum1 + $opening_balance;
			$opening_bal_dc = $result4a[1];
                	$plan_dr_amount = $result4a[5];
			$dr_plan_total = $dr_plan_total + $plan_dr_amount;
			$plan_cr_amount = $result4a[6];
			$cr_plan_total = $cr_plan_total + $plan_cr_amount;
			$closing_bal = $result4a[9];
                        $sum2 = $sum2 + $closing_bal;

			$dep3 = $CI->newschedules_model->get_old_asset_depvalue1($ledg_id); 
                        $dep_op_balance = $dep3[2];
			$dep_opening_value  = $dep_opening_value + $dep_op_balance;
			$old_dep_amount = $dep3[3];
			
			$dep4 = $CI->newschedules_model->get_new_asset_depvalue1($ledg_id);	
                        $new_dep_amount = $dep4[3];

			//Adding opening balance for the ledger head.
                	$current_depreciation = ($old_dep_amount + $new_dep_amount);
			$sum3 = $sum3 + $current_depreciation;
			$total_depreciation = $dep_op_balance + $current_depreciation;
			$sum4 = $sum4 + $total_depreciation;
			$net_total = ($opening_balance + $plan_dr_amount) - $plan_cr_amount;
			$sum5 = $sum5 + $net_total;
			$current_year_value = $net_total;

			echo "<td align=\"right\">" . convert_amount_dc($opening_balance) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc($plan_dr_amount) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(-$plan_cr_amount) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc($closing_bal) . "</td>";
			if($dep_op_balance > 0){
                        echo "<td align=\"right\">" . convert_amount_dc(+$dep_op_balance) . "</td>";
			}else{
			echo "<td align=\"right\">" . convert_amount_dc(-$dep_op_balance) . "</td>";
			}
                        echo "<td align=\"right\">" . convert_amount_dc(+$current_depreciation) . "</td>";
                        echo "<td align=\"right\" width=\"9%\">";
                        echo "0.00";
                        echo "</td>";
                        echo "<td align=\"center\">";
                        echo money_format('%!i', convert_cur($total_depreciation));
                        echo "</td>";
			if($current_year_value > 0){
                        echo "<td align=\"right\">" . convert_amount_dc($current_year_value) . "</td>";
			}else{
			echo "<td align=\"right\">" . convert_amount_dc($current_year_value) . "</td>";
			}
                        echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                }//foreach

		$this->opening_bal3 = $sum1;
		$this->cr_plan_total3 = $cr_plan_total;
		$this->dr_plan_total3 = $dr_plan_total;
		$this->closing_sum3 = $sum2;
		$this->dep_opening3 = $dep_opening_value;
		$this->current_dep_total3 = $sum3;
		$this->total_depreciation3 = $sum4;
		$this->curr_amount3 = $sum5;
	}

	function Nonplan_Fixed_Sub_ScheduleA($code,$count)
	{
		$counter = 1;
		$cr_nonplan_total = 0;
		$dr_nonplan_total = 0;
		$closing_sum = 0;
		$sum1 = 0;
		$sum2 = 0;
		$sum3 = 0;
		$sum4 = 0;
		$sum5 = 0;
		$sum6 = 0;
		$sum7 = 0;
		$current_dep_value4a = 0;
		$total_dep4a = 0;
		$dep_opening_value = 0;
		
		$CI =& get_instance();
                $CI->load->model('group_model');
                $id = $CI->group_model->get_group_id($code);
                $CI->db->select('name,code,id')->from('groups')->where('parent_id',$id);
                $group_detail = $CI->db->get();
                foreach($group_detail->result() as $row)
                {
                        $g_id =$row->id;
                        $CI->db->select('name,code,id')->from('groups')->where('parent_id',$g_id);
                        $child_detail = $CI->db->get();
                        foreach($child_detail->result() as $row1)
                        {
                        $group_id =$row1->id;
                        $group_name = $row1->name;
			$CI->load->model('newschedules_model');
	                $result4a = $CI->newschedules_model->plan_sub_schedule4($group_id);
			$opening_balance = $result4a[0];
			$sum1 = $sum1 + $opening_balance;
			$opening_bal_dc = $result4a[1];
                	$nonplan_dr_amount = $result4a[4];
			$dr_nonplan_total = $dr_nonplan_total + $nonplan_dr_amount;
			$nonplan_cr_amount = $result4a[5];
			$cr_nonplan_total = $cr_nonplan_total + $nonplan_cr_amount;
			$closing_bal = $result4a[7];
                        $sum2 = $sum2 + $closing_bal;

			$dep1 = $CI->newschedules_model->get_old_asset_depvalue($group_id);
                        $dep_op_balance = $dep1[5];
			$dep_opening_value  = $dep_opening_value + $dep_op_balance;
			$old_dep_amount = $dep1[4];
				
			$dep2 = $CI->newschedules_model->get_new_asset_depvalue($group_id);
                        $new_dep_amount = $dep2[4];

			//Adding opening balance for the ledger head.
                	$current_depreciation = ($old_dep_amount + $new_dep_amount);
			$sum3 = $sum3 + $current_depreciation;
			$total_depreciation = $dep_op_balance + $current_depreciation;
			$sum4 = $sum4 + $total_depreciation;
			$net_total = ($opening_balance + $nonplan_dr_amount) - $nonplan_cr_amount;
			$sum5 = $sum5 + $net_total;
			$current_year_value = $net_total;

                        if(($group_id!= 148) && ($group_id!= 149) && ($group_id!= 151))
                        {
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo $counter;
                        $counter++;
                        echo "</td>";
			echo "<td class=\"td-group\" width=\"225\">";
                	echo "&nbsp;" .  $group_name;
                	echo "</td>";

			echo "<td align=\"right\">" . convert_amount_dc($opening_balance) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc($nonplan_dr_amount) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(-$nonplan_cr_amount) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc($closing_bal) . "</td>";
			if($dep_op_balance > 0){
                        echo "<td align=\"right\">" . convert_amount_dc(+$dep_op_balance) . "</td>";
			}else{
			echo "<td align=\"right\">" . convert_amount_dc(-$dep_op_balance) . "</td>";
			}
                        echo "<td align=\"right\">" . convert_amount_dc(+$current_depreciation) . "</td>";
                        echo "<td align=\"right\" width=\"9%\">";
                        echo "0.00";
                        echo "</td>";
                        echo "<td align=\"center\">";
                        echo money_format('%!i', convert_cur($total_depreciation));
                        echo "</td>";
			if($current_year_value > 0){
                        echo "<td align=\"right\">" . convert_amount_dc($current_year_value) . "</td>";
			}else{
			echo "<td align=\"right\">" . convert_amount_dc($current_year_value) . "</td>";
			}
                        echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";

		 	}//ifcondition
                    }//childgroup
                }//foreach group
		$this->opening_bal1 = $sum1;
		$this->cr_plan_total1 = $cr_nonplan_total;
		$this->dr_plan_total1 = $dr_nonplan_total;
		$this->closing_sum1 = $sum2;
		$this->dep_opening = $dep_opening_value;
		$this->current_dep_total = $sum3;
		$this->total_depreciation = $sum4;
		$this->curr_amount = $sum5;

	}

	function Nonplan_Fixed_Sub_ScheduleB($id)
	{
		$counter = 17;
		$cr_nonplan_total = 0;
		$dr_nonplan_total = 0;
		$closing_sum = 0;
		$sum1 = 0;
		$sum2 = 0;
		$sum3 = 0;
		$sum4 = 0;
		$sum5 = 0;
		$sum6 = 0;
		$sum7 = 0;
		$current_dep_value4a = 0;
		$total_dep4a = 0;
		$dep_opening_value = 0;

		$CI =& get_instance();
                $CI->db->select('name,code,id')->from('groups')->where('id',$id);
                $group_detail = $CI->db->get();
                $group_result = $group_detail->row();
                $group_id =$group_result->id;
                $group_name = $group_result->name;

                echo "<tr class=\"tr-group\">";
                echo "<td class=\"td-group\" width=\"40\">";
                echo $counter;
                echo "</td>";
		echo "<td class=\"td-group\" width=\"225\">";
                echo "&nbsp;" .  $group_name . "<b>(B)</b>";
                echo "</td>";

		 	$result4a = $CI->newschedules_model->plan_sub_schedule4($group_id);
			$opening_balance = $result4a[0];
			$sum1 = $sum1 + $opening_balance;
			$opening_bal_dc = $result4a[1];
                	$nonplan_dr_amount = $result4a[4];
			$dr_nonplan_total = $dr_nonplan_total + $nonplan_dr_amount;
			$nonplan_cr_amount = $result4a[5];
			$cr_nonplan_total = $cr_nonplan_total + $nonplan_cr_amount;
			$closing_bal = $result4a[7];
                        $sum2 = $sum2 + $closing_bal;

			$dep1 = $CI->newschedules_model->get_old_asset_depvalue($group_id);
                        $dep_op_balance = $dep1[5];
			$dep_opening_value  = $dep_opening_value + $dep_op_balance;
			$old_dep_amount = $dep1[4];
				
			$dep2 = $CI->newschedules_model->get_new_asset_depvalue($group_id);
                        $new_dep_amount = $dep2[4];

			//Adding opening balance for the ledger head.
                	$current_depreciation = ($old_dep_amount + $new_dep_amount);
			$sum3 = $sum3 + $current_depreciation;
			$total_depreciation = $dep_op_balance + $current_depreciation;
			$sum4 = $sum4 + $total_depreciation;
			$net_total = ($opening_balance + $nonplan_dr_amount) - $nonplan_cr_amount;
			$sum5 = $sum5 + $net_total;
			$current_year_value = $net_total;

			echo "<td align=\"right\">" . convert_amount_dc($opening_balance) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc($nonplan_dr_amount) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(-$nonplan_cr_amount) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc($closing_bal) . "</td>";
			if($dep_op_balance > 0){
                        echo "<td align=\"right\">" . convert_amount_dc(+$dep_op_balance) . "</td>";
			}else{
			echo "<td align=\"right\">" . convert_amount_dc(-$dep_op_balance) . "</td>";
			}
                        echo "<td align=\"right\">" . convert_amount_dc(+$current_depreciation) . "</td>";
                        echo "<td align=\"right\" width=\"9%\">";
                        echo "0.00";
                        echo "</td>";
                        echo "<td align=\"center\">";
                        echo money_format('%!i', convert_cur($total_depreciation));
                        echo "</td>";
			if($current_year_value > 0){
                        echo "<td align=\"right\">" . convert_amount_dc($current_year_value) . "</td>";
			}else{
			echo "<td align=\"right\">" . convert_amount_dc($current_year_value) . "</td>";
			}
                        echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                echo "</tr>";
		
		$this->opening_bal2 = $sum1;
		$this->cr_plan_total2 = $cr_nonplan_total;
		$this->dr_plan_total2 = $dr_nonplan_total;
		$this->closing_sum2 = $sum2;
		$this->dep_opening2 = $dep_opening_value;
		$this->current_dep_total2 = $sum3;
		$this->total_depreciation2 = $sum4;
		$this->curr_amount2 = $sum5;
	}

	function Nonplan_Fixed_Sub_ScheduleC($id)
        {
                $counter = 18;
		$sum1 = 0;
		$sum2 = 0;
		$sum3 = 0;
		$sum4 = 0;
		$sum5 = 0;
		$dr_plan_total = 0;
		$cr_plan_total = 0;
		$dr_nonplan_total  = 0;
		$cr_nonplan_total = 0;
		$dep_opening_value = 0;
                $CI =& get_instance();
                $CI->db->select('name,code,id')->from('ledgers')->where('group_id',$id);
                $ledger_detail = $CI->db->get();
                $ledger_result = $ledger_detail->result();
                foreach($ledger_result as $row)
                {
                	$ledg_id =$row->id;
                	$ledg_name = $row->name;
                	echo "<tr class=\"tr-group\" colspan=\"2\">";
               	 	echo "<td class=\"td-group\">";
                	echo $counter;
                	$counter++;
                	echo "</td>";
                	echo "<td class=\"td-group\" width=\"225\">";
                	echo "&nbsp;" .  $ledg_name;
                	echo "</td>";

			//$result4a = $CI->newschedules_model->plan_sub_schedule4($ledg_id);
			$result4a = $CI->newschedules_model->fixed_assetledg($ledg_id);
			$opening_balance = $result4a[0];
			$sum1 = $sum1 + $opening_balance;
			$opening_bal_dc = $result4a[1];
                	$nonplan_dr_amount = $result4a[7];
			$dr_nonplan_total = $dr_nonplan_total + $nonplan_dr_amount;
			$nonplan_cr_amount = $result4a[8];
			$cr_nonplan_total = $cr_nonplan_total + $nonplan_cr_amount;
			$closing_bal = $result4a[10];
                        $sum2 = $sum2 + $closing_bal;

			$dep3 = $CI->newschedules_model->get_old_asset_depvalue1($ledg_id); 
                        $dep_op_balance = $dep3[2];
			$dep_opening_value  = $dep_opening_value + $dep_op_balance;
			$old_dep_amount = $dep3[4];
			
			$dep4 = $CI->newschedules_model->get_new_asset_depvalue1($ledg_id);	
                        $new_dep_amount = $dep4[4];

			//Adding opening balance for the ledger head.
                	$current_depreciation = ($old_dep_amount + $new_dep_amount);
			$sum3 = $sum3 + $current_depreciation;
			$total_depreciation = $dep_op_balance + $current_depreciation;
			$sum4 = $sum4 + $total_depreciation;
			$net_total = ($opening_balance + $nonplan_dr_amount) - $nonplan_cr_amount;
			$sum5 = $sum5 + $net_total;
			$current_year_value = $net_total;

			echo "<td align=\"right\">" . convert_amount_dc($opening_balance) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc($nonplan_dr_amount) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(-$nonplan_cr_amount) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc($closing_bal) . "</td>";
			if($dep_op_balance > 0){
                        echo "<td align=\"right\">" . convert_amount_dc(+$dep_op_balance) . "</td>";
			}else{
			echo "<td align=\"right\">" . convert_amount_dc(-$dep_op_balance) . "</td>";
			}
                        echo "<td align=\"right\">" . convert_amount_dc(+$current_depreciation) . "</td>";
                        echo "<td align=\"right\" width=\"9%\">";
                        echo "0.00";
                        echo "</td>";
                        echo "<td align=\"center\">";
                        echo money_format('%!i', convert_cur($total_depreciation));
                        echo "</td>";
			if($current_year_value > 0){
                        echo "<td align=\"right\">" . convert_amount_dc($current_year_value) . "</td>";
			}else{
			echo "<td align=\"right\">" . convert_amount_dc($current_year_value) . "</td>";
			}
                        echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                }//foreach

		$this->opening_bal3 = $sum1;
		$this->cr_plan_total3 = $cr_nonplan_total;
		$this->dr_plan_total3 = $dr_nonplan_total;
		$this->closing_sum3 = $sum2;
		$this->dep_opening3 = $dep_opening_value;
		$this->current_dep_total3 = $sum3;
		$this->total_depreciation3 = $sum4;
		$this->curr_amount3 = $sum5;

	} 

	function Fixed_Sub_ScheduleCi($code,$count)
	{
		$counter = 19;
                $CI =& get_instance();
                //$id = $CI->group_model->get_group_id($code);
                $CI->db->select('name,code,id')->from('ledgers')->where('group_id',152);
                $ledger_detail = $CI->db->get();
                $ledger_result = $ledger_detail->result();
                foreach($ledger_result as $row)
                {
                $ledg_id =$row->id;
                $ledg_name = $row->name;
                echo "<tr class=\"tr-group\" colspan=\"2\">";
                echo "<td class=\"td-group\">";
                echo $counter;
                $counter++;
                echo "</td>";
                echo "<td class=\"td-group\" width=\"225\">";

                if($ledg_name == 'Patents and Copyrights (Patents Granted)')
		 echo "&nbsp;&nbsp;&nbsp;&nbsp;" . anchor_popup('report/inner_sub_schedule/' . $row->id . '/' . $row->name, $row->name, array('title' => $row->name, 'style' => 'color:#000000;text-decoration:none;font-weight:bold;')) . "(Subschedule)";
                else
                echo "&nbsp;" .  $ledg_name;
                echo "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
			 echo "</tr>";
                }//foreach
        }
	
	function Current_liability($id,$code,$count)
	{
		$sum = 0;
		$counter = 1;
		$str1 = 'a';
		$CI =& get_instance();
		$CI->db->select('id,name,code')->from('groups')->where('parent_id',$id);
        	$main1 = $CI->db->get();
        	$main1_result = $main1->result();
        	foreach($main1_result as $row)
        	{
                	$group_id = $row->id;
                	$group_name = $row->name;
			$liability = new Reportlist1();
                        $liability->init($row->id);
                        $liability_total = $liability->total;
                        $sum = $sum + $liability_total;
                	echo "<tr class=\"tr-group\">";
                	echo "<td class=\"td-group\">";
                	echo $counter;
                	$counter++;
			if($group_name == 'Recipts Against Sponsored Projects')
                        echo "&nbsp;&nbsp;&nbsp;&nbsp;" . anchor_popup('report/new_sub_schedule/' . $row->id . '/' . $row->name, $row->name, array('title' => $row->name,  'style' => 'color:#000000;text-decoration:none;font-weight:bold;')) . "(Subschedule)";
                        elseif($group_name == 'UGC Sponsored Fellowship/Scholarships')
                        echo "&nbsp;&nbsp;&nbsp;&nbsp;" . anchor_popup('report/new_sub_schedule/' . $row->id . '/' . $row->name, $row->name, array('title' => $row->name, 'style' => 'color:#000000;text-decoration:none;font-weight:bold;')) . "(Subschedule)";  
			elseif($group_name == 'Unutilized Grants')
                        echo "&nbsp;&nbsp;&nbsp;&nbsp;" . anchor_popup('report/new_sub_schedule/' . $row->id . '/' . $row->name, $row->name, array('title' => $row->name, 'style' => 'color:#000000;text-decoration:none;font-weight:bold;')) . "(Subschedule)";  
			else
                	echo "&nbsp;&nbsp;" . $group_name;
                	echo "</td>";
			echo "<td align=\"right\" colspan=\"2\">" . convert_amount_dc($liability_total) . "</td>";
                        echo "<td align=\"right\" colspan=\"2\">" . convert_amount_dc(0) . "</td>";

			$CI->db->select('id,name,code')->from('groups')->where('parent_id', $group_id);
			$ledg_detail = $CI->db->get();
			$ledg_result = $ledg_detail->result();
			foreach($ledg_result as $row1)
			{
				$ledg_id = $row1->id;
				$ledg_name = $row1->name;
				$liability = new Reportlist1();
	                        $liability->init($row1->id);
        	                $liability_total = $liability->total;
                	        //$sum = $sum + $liability_total;
			
				echo "<tr class=\"tr-ledger\">";
	                	echo "<td class=\"td-ledger\">";
				echo $str1;
                        	echo ')';
                        	$str1++;
                		echo "&nbsp;&nbsp;" . $ledg_name;
                		echo "</td>";
				echo "<td align=\"right\" colspan=\"2\">" . convert_amount_dc($liability_total) . "</td>";
                        	echo "<td align=\"right\" colspan=\"2\">" . convert_amount_dc(0) . "</td>";

			}
		}//foreach
		$this->liability_total = $sum;
		
	}

	function Provision($id,$code,$count)
	{
		$counter = 1;
		$sum = 0;
		$sum1 = 0;
		$CI =& get_instance();
		$CI->db->select('id,name,code')->from('groups')->where('parent_id',$id);
        	$main_result = $CI->db->get();
        	$main_q = $main_result->result();
        	foreach($main_q as $row)
        	{
                $group_id = $row->id;
                $group_name = $row->name;
                echo "<tr class=\"tr-group\">";
                echo "<td class=\"td-group\">";
                echo $counter;
                $counter++;
                echo "&nbsp;&nbsp;" . $group_name;
                echo "</td>";
		$liability = new Reportlist1();
                $liability->init($row->id);
                $liability_total = $liability->total;
                $sum = $sum + $liability_total;

		echo "<td align=\"right\" colspan=\"2\">" . convert_amount_dc($liability_total) . "</td>";
                echo "<td align=\"right\" colspan=\"2\">" . convert_amount_dc(0) . "</td>";
		}//foreach
		$this->liability_total = $sum;
	}

	function subschedule_3a($id)
	{
		$counter = 1;
		$sum = 0;
		$project_name = "";
		$total_opening = 0;
		$op_balance_sum = 0;
		$op_balance_sum1 = 0;
		$rec_sum = 0;
		$exp_sum = 0;
		$total_sum = 0;
		$closing_sum = 0;
		$closing_sum1 = 0;		

		$CI =& get_instance();
        	$CI->load->model('newschedules_model');
        	$CI->db->select('id,code,name')->from('groups')->where('parent_id', $id);
        	$group_data1 = $CI->db->get();
		foreach($group_data1->result() as $row1)
        	{
            		$group_name1 = $row1->name;
            		$group_code1 = $row1->code;
            		$group_id1 = $row1->id;
            		$p_name = $CI->newschedules_model->get_project_name($group_name1);
            		if(!($p_name == $project_name))
            		{
                		$project_name = $p_name;         
                		echo "<tr class=\"tr-group\">";
                		echo "<td class=\"td-group\" align=\"left\">";
                		echo $counter;
                		$counter++;
                		echo "</td>";
                		echo "<td align=\"left\">";
                		echo $project_name;
                		echo "</td>";
		 		$opbalance = $CI->newschedules_model->get_op_balance($project_name,$id);
				
                		$op_balance = $opbalance[0];
				//$op_balance_sum = $op_balance_sum + $op_balance;
                		$op_balance_dc = $opbalance[1];

                		if($op_balance_dc == "D"){
					echo "<td>";
                                        echo "</td>";
                    			echo "<td align=\"right\">" . convert_amount_dc($op_balance) . "</td>";
					$op_balance_sum = $op_balance_sum + $op_balance;
                		}else{
                    			echo "<td align=\"right\">" . convert_amount_dc($op_balance) . "</td>";
					echo "<td>";
                                        echo "</td>";
					$op_balance_sum1 = $op_balance_sum1 + $op_balance;
                		}
				
				$rec_exp_total  = $CI->newschedules_model->get_exp_receipt_total($project_name,$id);
				$receipt_total = $rec_exp_total[0];
				$rec_sum = $rec_sum + $receipt_total;
				$expense_total = $rec_exp_total[1];
				$exp_sum = $exp_sum + $expense_total;
				if($op_balance > 0)
				{
				$total1 = float_ops($receipt_total, $op_balance, '+');
				$total_sum = $total_sum + $total1;
		                }else{
                	        $total1 = float_ops($receipt_total, $op_balance, '+');
				$total_sum = $total_sum + $total1;
				}

				echo "<td align=\"right\">" . convert_amount_dc(+$receipt_total) . "</td>";
				echo "<td align=\"right\">" . convert_amount_dc($total1) . "</td>";
				echo "<td align=\"right\">" . convert_amount_dc(-$expense_total) . "</td>";

				$closing_bal = $total1 - $expense_total;
	                        if($closing_bal > 0){
                                echo "<td></td>";
                                echo "<td align=\"right\">" . convert_amount_dc($closing_bal) . "</td>";
                                $closing_sum = $closing_sum + $closing_bal;
                                }else{
                                echo "<td align=\"right\">" . convert_amount_dc($closing_bal) . "</td>";
                                echo "<td></td>";
                                $closing_sum1 = $closing_sum1 + $closing_bal;
                                }

			}

		}//foreach
		$this->op_balance1 = $op_balance_sum;
		$this->op_balance2 = $op_balance_sum1;
		//$this->op_balance_dc = $op_balance_dc;
		$this->receipt_total1 = $rec_sum;
		$this->expense_total1 = $exp_sum;
		$this->sum_total = $total_sum;
		$this->closing_bal1 = $closing_sum;
		$this->closing_bal2 = $closing_sum1;

	}//main

	function subschedule_3b($id)
	{
		$counter = 1;
                $sum = 0;
		$dr_sum = 0;
		$cr_sum = 0;
		$op_bal_sum = 0;
		$op_bal_sum1 = 0;
                $project_name = "";
                $total_opening = 0;
                $closing_sum = 0;
		$closing_sum1 = 0;
                $CI =& get_instance();
                $CI->load->model('newschedules_model');
                $CI->db->select('id,code,name')->from('groups')->where('parent_id', $id);
                $group_data = $CI->db->get();
                foreach($group_data->result() as $row1)
                {
                        $group_name1 = $row1->name;
                        $group_code1 = $row1->code;
                        $group_id1 = $row1->id;

                        $p_name = $CI->newschedules_model->get_project_name($group_name1,$id);
                        if(!($p_name == $project_name))
                        {
                                $project_name = $p_name;
                                echo "<tr class=\"tr-group\">";
                                echo "<td class=\"td-group\" align=\"left\">";
                                echo $counter;
                                $counter++;
                                echo "</td>";
                                echo "<td align=\"left\">";
                                echo $project_name;
				$opbalance = $CI->newschedules_model->get_op_balance($project_name,$id);
                                $op_balance = $opbalance[0];
				//$op_bal_sum = $op_bal_sum + $op_balance;
                                $op_balance_dc = $opbalance[1];
				
				if($op_balance_dc == "D"){
					echo "<td>";
                                        echo "</td>";
                    			echo "<td align=\"right\">" . convert_amount_dc($op_balance) . "</td>";
					$op_bal_sum = $op_bal_sum + $op_balance;
                		}else{
                    			echo "<td align=\"right\">" . convert_amount_dc($op_balance) . "</td>";
					echo "<td>";
                                        echo "</td>";
					$op_bal_sum1 = $op_bal_sum1 + $op_balance;
                		}
			$value  = $CI->newschedules_model->get_exp_receipt_total($project_name,$id);
                        $dr_total = $value[0];
                        $dr_sum = $dr_sum + $dr_total;
                        $cr_total = $value[1];
                        $cr_sum = $cr_sum + $cr_total;
			echo "<td align=\"right\">" . convert_amount_dc(-$cr_total) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(+$dr_total) . "</td>";

			$cl_balance = $op_balance + $dr_total - $cr_total;
			if($cl_balance > 0){
				echo "<td></td>";
				echo "<td align=\"right\">" . convert_amount_dc($cl_balance) . "</td>";
				$closing_sum = $closing_sum + $cl_balance;
                                }else{
				echo "<td align=\"right\">" . convert_amount_dc($cl_balance) . "</td>";
				echo "<td></td>";
				$closing_sum1 = $closing_sum1 + $cl_balance;
				}
                                //$closing_sum = $closing_sum + $cl_balance;


			}
		}//foreach
		$this->opening_bal1 = $op_bal_sum;
		$this->opening_bal2 = $op_bal_sum1;
		$this->debit_total = $dr_sum;
		$this->credit_total = $cr_sum;
		$this->cl_bal1 = $closing_sum;
		$this->cl_bal2 = $closing_sum1;
	}

	function subschedule_3c()
	{
		$total_opening = "";
		$CI =& get_instance();
		$CI->load->model('newschedules_model');
		$id = $CI->Group_model->get_id('Unutilized Grants');
            	$CI->db->select('name,id')->from('groups')->where('parent_id',$id);
            	$query = $CI->db->get();
		$name = array();
		$n = 0;
            	$name[0] = "Balance B/F";
            	$name[1] = "Add: Receipts during the year";
		echo "<tr>";
	        echo "<td><strong>A.  Plan grants: Government of India</strong></td>";
		//do{
		if($name[0]){
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo "&nbsp;" .  $name[0];
                        echo "</td>";
			foreach($query->result() as $row)
  		        {
                        $group_id = $row->id;
                        $group_name = $row->name;
			if($group_id == "105")
			{
				$value = $CI->newschedules_model->get_subschedule3c($group_id,'plan');
				$total_opening = $value[0];
				$op_balance_dc = $value[1];
				//$total_dr = $value[2];
				if($op_balance_dc == 'C'){
                    			echo "<td align=\"right\">" . convert_amount_dc($total_opening) . "</td>";
                		}else{
                    			echo "<td align=\"right\">" . convert_amount_dc($total_opening) . "</td>";
                		}
				echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
			echo "</tr>";
			}//if(id=='')
			}//foreach
		}

		  if($name[1]){
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo "&nbsp;" .  $name[1];
                        echo "</td>";
                        foreach($query->result() as $row)
                        {
                        $group_id = $row->id;
                        $group_name = $row->name;
			if($group_id == "105")
                        {
                        	$value = $CI->newschedules_model->get_subschedule3c($group_id,'plan');
				$total_cr = -($value[2]);
				echo "<td align=\"right\">" . convert_amount_dc($total_cr) . "</td>";
				echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                        echo "</tr>";
                        }//if($id='')
			}
                }


		echo "<tr>";
                echo "<td align=\"right\">";
                echo "<strong> TOTAL(a)</strong>";
                echo "</td>";
		if(($total_opening > 0) && ($total_cr < 0)){
                $plan_total1 = $total_opening + $total_cr;
		echo "<td  align=\"right\">";
                echo "<strong>" . convert_amount_dc($plan_total1) . "</strong>";
                echo "</td>";
                }else{
		$plan_total1 = $total_opening + $total_cr;
                echo "<td  align=\"right\">";
                echo "<strong>" . convert_amount_dc($plan_total1) . "</strong>";
                echo "</td>";
                }

                echo "<td  align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
		echo "<tr>";
		//echo "</tr>";
		$n = 2;
		$name[2] = "Less Refunds";
                $name[3] = "Less: Utilized for Revenue Expenditure";
		$name[4] = "Less: Utilized for Capital expenditure";

		if($name[2]){
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo "&nbsp;" .  $name[2];
                        echo "</td>";
                        foreach($query->result() as $row)
                        {
                        $group_id = $row->id;
                        $group_name = $row->name;
			if($group_id == "105")
			{
                        	$value = $CI->newschedules_model->get_subschedule3c($group_id,'plan');
				$total_dr = $value[3];
				echo "<td align=\"right\">" . convert_amount_dc(+$total_dr) . "</td>";
                                echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";

                        echo "</tr>";

                        }//if(id='')
			}
                }
		
		if($name[3]){
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo "&nbsp;" .  $name[3];
                        echo "</td>";
                        foreach($query->result() as $row)
                        {
                        $group_id = $row->id;
                        $group_name = $row->name;
			if($group_id == '105')
			{
                        	$value = $CI->newschedules_model->get_subschedule3c($group_id,'plan');
				$total_revenue = $value[4];
				echo "<td align=\"right\">" . convert_amount_dc(+$total_revenue) . "</td>";
                                echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";

                        echo "</tr>";

                        }//if
			}
                }

		if($name[4]){
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo "&nbsp;" .  $name[4];
                        echo "</td>";
                        foreach($query->result() as $row)
                        {
                        $group_id = $row->id;
                        $group_name = $row->name;
			if($group_id == "105")
			{
                        	$value = $CI->newschedules_model->get_subschedule3c($group_id,'plan');
				$total_capital = $value[5];
				echo "<td align=\"right\">" . convert_amount_dc(+$total_capital) . "</td>";
                                echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";

                        //echo "</tr>";

                        }//if
			}
                } 
		echo "</tr>";
		echo "<tr>";
                echo "<td align=\"right\">";
                echo "<strong> TOTAL(b)</strong>";
                echo "</td>";
		if(($total_dr > 0) && ($total_revenue > 0) && ($total_capital > 0))
		{
		$plan_total2 = $total_dr + $total_revenue + $total_capital;
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($plan_total2) . "</strong>";
                echo "</td>";
		}else{
		$plan_total2 = $total_dr + $total_revenue + $total_capital;
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($plan_total2) . "</strong>";
                echo "</td>";
		}
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
	        echo "</tr>";
		echo "<td align=\"right\">";
                echo "Unutilized carried forward(a-b)";
                echo "</td>";
		if(($plan_total1 > 0) && ($plan_total2 > 0))
		{
		$total1 = $plan_total1 - $plan_total2;
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($total1) . "</strong>";
                echo "</td>";
		}else{
		$total1 = $plan_total1 + $plan_total2;
		
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($total1) . "</strong>";
                echo "</td>";
		}
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
                echo "</tr>";

		echo "<tr>";
                echo "<td><strong>B.  UGC grants: Plans</strong><td>";
		$n = 5;
		$name[5] = "Balance B/F";
                $name[6] = "Add: Receipts during the year";
		if($name[5]){
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo "&nbsp;" .  $name[5];
                        echo "</td>";
                        foreach($query->result() as $row)
                        {
                        $group_id = $row->id;
                        $group_name = $row->name;
			if($group_id == "104")
			{
                        	$value = $CI->newschedules_model->get_subschedule3c($group_id,'plan');
				$total_opening1 = $value[0];
				$op_balance_dc = $value[1];
				//$total_dr = $value[2];
				if($op_balance_dc == 'C'){
                    			echo "<td align=\"right\">" . convert_amount_dc($total_opening1) . "</td>";
                		}else{
                    			echo "<td align=\"right\">" . convert_amount_dc($total_opening1) . "</td>";
                		}
				echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
				echo "</tr>";
			}//if
                        }
                }

		if($name[6]){
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo "&nbsp;" .  $name[6];
                        echo "</td>";
                        foreach($query->result() as $row)
                        {
                        $group_id = $row->id;
                        $group_name = $row->name;
			if($group_id == "104")
                        {
                        	$value = $CI->newschedules_model->get_subschedule3c($group_id,'plan');
				$total_cr1 = (-$value[2]);
				echo "<td align=\"right\">" . convert_amount_dc($total_cr1) . "</td>";
                        	echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                        //echo "</tr>";
			}//if
                        }
                }


                echo "</tr>";
                echo "<tr>";
                echo "<td align=\"right\">";
                echo "<strong> TOTAL(c)</strong>";
                echo "</td>";
		if(($total_opening1 > 0) && ($total_cr1 < 0)){
                $plan_total3 = $total_opening1 + $total_cr1;
		echo "<td  align=\"right\">";
                echo "<strong>" . convert_amount_dc($plan_total3) . "</strong>";
                echo "</td>";
                }else{
		$plan_total3 = $total_opening1 + $total_cr1;
                echo "<td  align=\"right\">";
                echo "<strong>" . convert_amount_dc($plan_total3) . "</strong>";
                echo "</td>";
                }
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
                echo "</tr>";
		echo "<tr>";
		$n = 7;
                $name[7] = "Less Refunds";
                $name[8] = "Less: Utilized for Revenue Expenditure";
                $name[9] = "Less: Utilized for Capital expenditure";

		if($name[7]){
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo "&nbsp;" .  $name[7];
                        echo "</td>";
                        foreach($query->result() as $row)
                        {
                        $group_id = $row->id;
                        $group_name = $row->name;
			if($group_id == "104")
                        {
                        	$value = $CI->newschedules_model->get_subschedule3c($group_id,'plan');
				$total_dr1 = $value[3];
				echo "<td align=\"right\">" . convert_amount_dc(+$total_dr1) . "</td>";
                        	echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                        echo "</tr>";
			}//if
                        }
                }

		if($name[8]){
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo "&nbsp;" .  $name[8];
                        echo "</td>";
                        foreach($query->result() as $row)
                        {
                        $group_id = $row->id;
                        $group_name = $row->name;
			if($group_id == "104")
                        {
                        	$value = $CI->newschedules_model->get_subschedule3c($group_id,'plan');
				$total_revenue1 = $value[4];
				echo "<td align=\"right\">" . convert_amount_dc(+$total_revenue1) . "</td>";
                        	echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                        echo "</tr>";
			}//if
                        }
                }

		if($name[9]){
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo "&nbsp;" .  $name[9];
                        echo "</td>";
                        foreach($query->result() as $row)
                        {
                        $group_id = $row->id;
                        $group_name = $row->name;
			if($group_id == "104")
                        {
                        	$value = $CI->newschedules_model->get_subschedule3c($group_id,'plan');
				$total_capital1 = $value[5];
				echo "<td align=\"right\">" . convert_amount_dc(+$total_capital1) . "</td>";
                        	echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                        //echo "</tr>";
			}//if
                        }
                }


		echo "</tr>";
                echo "<tr>";
                echo "<td align=\"right\">";
                echo "<strong> TOTAL(d)</strong>";
                echo "</td>";
		if(($total_dr1 > 0) && ($total_revenue1 > 0) && ($total_capital1 > 0))
		{
		$plan_total4 = $total_dr1 + $total_revenue1 + $total_capital1;
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($plan_total4) . "</strong>";
                echo "</td>";
		}else{
		$plan_total4 = $total_dr1 + $total_revenue1 + $total_capital1;
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($plan_total4) . "</strong>";
                echo "</td>";
		}
		echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
		echo "</tr>";
		echo "<tr>";
		echo "<td align=\"right\">";
                echo "Unutilized carried forward(c-d)";
                echo "</td>";
		if(($plan_total3 > 0) && ($plan_total4 > 0))
		{
		$total2 = $plan_total3 - $plan_total4;
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($total2) . "</strong>";
                echo "</td>";
		}else{
		$total2 = $plan_total3 + $plan_total4;
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($total2) . "</strong>";
                echo "</td>";
		}
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
                echo "</tr>";

		echo "<tr>";
                echo "<td><strong>C.  UGC Grants Non Plan</strong><td>";
		$n = 10;
                $name[10] = "Balance B/F";
                $name[11] = "Add: Receipts during the year";

		if($name[10]){
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo "&nbsp;" .  $name[10];
                        echo "</td>";
                        foreach($query->result() as $row)
                        {
                        $group_id = $row->id;
                        $group_name = $row->name;
			if($group_id == "104")
                        {
                        	$value = $CI->newschedules_model->get_subschedule3c($group_id,'nonplan');
				$total_opening2 = $value[0];
				$op_balance_dc = $value[1];
				//$total_dr = $value[2];
				if($op_balance_dc == 'C'){
                    			echo "<td align=\"right\">" . convert_amount_dc($total_opening2) . "</td>";
                		}else{
                    			echo "<td align=\"right\">" . convert_amount_dc($total_opening2) . "</td>";
                		}
				echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                        echo "</tr>";
			}//if
                        }
                }

		if($name[11]){
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo "&nbsp;" .  $name[11];
                        echo "</td>";
                        foreach($query->result() as $row)
                        {
                        $group_id = $row->id;
                        $group_name = $row->name;
			if($group_id == "104")
                        {
                        	$value = $CI->newschedules_model->get_subschedule3c($group_id,'nonplan');
				$total_cr2 = (-$value[2]);
				echo "<td align=\"right\">" . convert_amount_dc($total_cr2) . "</td>";
                        	echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                        //echo "</tr>";
			}//if
                        }
                }


                echo "</tr>";
                echo "<tr>";
                echo "<td align=\"right\">";
                echo "<strong> TOTAL(e)</strong>";
                echo "</td>";
		if(($total_opening2 > 0) && ($total_cr2 < 0)){
                $plan_total5 = $total_opening2 + $total_cr2;
		echo "<td  align=\"right\">";
                echo "<strong>" . convert_amount_dc($plan_total5) . "</strong>";
                echo "</td>";
                }else{
		$plan_total5 = $total_opening2 + $total_cr2;
                echo "<td  align=\"right\">";
                echo "<strong>" . convert_amount_dc($plan_total5) . "</strong>";
                echo "</td>";
                }
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
                echo "</tr>";
                echo "<tr>";
		$n = 12;
                $name[12] = "Less Refunds";
                $name[13] = "Less: Utilized for Revenue Expenditure";
                $name[14] = "Less: Utilized for Capital expenditure";

		if($name[12]){
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo "&nbsp;" .  $name[12];
                        echo "</td>";
                        foreach($query->result() as $row)
                        {
                        $group_id = $row->id;
                        $group_name = $row->name;
			if($group_id == "104")
                        {
                        	$value = $CI->newschedules_model->get_subschedule3c($group_id,'nonplan');
				$total_dr2 = $value[3];
				echo "<td align=\"right\">" . convert_amount_dc(+$total_dr2) . "</td>";
                        	echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                        echo "</tr>";
			}//if
                        }
                }

		 if($name[13]){
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo "&nbsp;" .  $name[13];
                        echo "</td>";
                        foreach($query->result() as $row)
                        {
                        $group_id = $row->id;
                        $group_name = $row->name;
			if($group_id == "104")
                        {
                        	$value = $CI->newschedules_model->get_subschedule3c($group_id,'nonplan');
				$total_revenue2 = $value[4];
				echo "<td align=\"right\">" . convert_amount_dc(+$total_revenue2) . "</td>";
                        	echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                        echo "</tr>";
			}//if
                        }
                }

		 if($name[14]){
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo "&nbsp;" .  $name[14];
                        echo "</td>";
                        foreach($query->result() as $row)
                        {
                        $group_id = $row->id;
                        $group_name = $row->name;
			if($group_id == "104")
                        {
                        	$value = $CI->newschedules_model->get_subschedule3c($group_id,'nonplan');
				$total_capital2 = $value[5];
				echo "<td align=\"right\">" . convert_amount_dc(+$total_capital2) . "</td>";
                        	echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                        echo "</tr>";
			}//if
                        }
                }


                echo "</tr>";
                echo "<tr>";
                echo "<td align=\"right\">";
                echo "<strong> TOTAL(f)</strong>";
                echo "</td>";
		if(($total_dr2 > 0) && ($total_revenue2 > 0) && ($total_capital2 > 0))
		{
		$plan_total6 = $total_dr2 + $total_revenue2 + $total_capital2;
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($plan_total6) . "</strong>";
                echo "</td>";
		}else{
		$plan_total6 = $total_dr2 + $total_revenue2 + $total_capital2;
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($plan_total6) . "</strong>";
                echo "</td>";
		}
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
	        echo "</tr>";

		echo "<tr>";
                echo "<td align=\"right\">";
                echo "Unutilized carried forward(e-f)";
                echo "</td>";
		if(($plan_total5 > 0) && ($plan_total6 > 0))
		{
		$total3 = $plan_total5 - $plan_total6;
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($total3) . "</strong>";
                echo "</td>";
		}else{
		$total3 = $plan_total5 + $plan_total6;
		
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($total3) . "</strong>";
                echo "</td>";
		}
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
                echo "</tr>";
		
		echo "<tr>";
                echo "<td><strong>D.  Grants from State Govt.</strong><td>";
		$n = 15;
                $name[15] = "Balance B/F";
                $name[16] = "Add: Receipts during the year";

		if($name[15]){
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo "&nbsp;" .  $name[15];
                        echo "</td>";
                        foreach($query->result() as $row)
                        {
                        $group_id = $row->id;
                        $group_name = $row->name;
			if($group_id == "106")
                        {
                        	$value = $CI->newschedules_model->get_subschedule3c($group_id,'');
				$total_opening3 = $value[0];
				$op_balance_dc = $value[1];
				//$total_dr = $value[2];
				if($op_balance_dc == 'C'){
                    			echo "<td align=\"right\">" . convert_amount_dc($total_opening3) . "</td>";
                		}else{
                    			echo "<td align=\"right\">" . convert_amount_dc($total_opening3) . "</td>";
                		}
				echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                        echo "</tr>";
			}//if
                        }
                }

		 if($name[16]){
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo "&nbsp;" .  $name[16];
                        echo "</td>";
                        foreach($query->result() as $row)
                        {
                        $group_id = $row->id;
                        $group_name = $row->name;
			if($group_id == "106")
                        {
                        	$value = $CI->newschedules_model->get_subschedule3c($group_id,'');
				$total_cr3 = (-$value[2]);
				echo "<td align=\"right\">" . convert_amount_dc($total_cr3) . "</td>";
                        	echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                        echo "</tr>";
			}//if
                        }
                }


                echo "</tr>";
                echo "<tr>";
                echo "<td align=\"right\">";
                echo "<strong> TOTAL(g)</strong>";
                echo "</td>";
	 	if(($total_opening3 > 0) && ($total_cr3 < 0)){
                $plan_total7 = $total_opening3 + $total_cr3;
		echo "<td  align=\"right\">";
                echo "<strong>" . convert_amount_dc($plan_total7) . "</strong>";
                echo "</td>";
                }else{
		$plan_total7 = $total_opening3 + $total_cr3;
                echo "<td  align=\"right\">";
                echo "<strong>" . convert_amount_dc($plan_total7) . "</strong>";
                echo "</td>";
                }	
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
                echo "</tr>";
                echo "<tr>";
		$n = 17;
                $name[17] = "Less Refunds";
                $name[18] = "Less: Utilized for Revenue Expenditure";
                $name[19] = "Less: Utilized for Capital expenditure";

		if($name[17]){
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo "&nbsp;" .  $name[17];
                        echo "</td>";
                        foreach($query->result() as $row)
                        {
                        $group_id = $row->id;
                        $group_name = $row->name;
			if($group_id == "106")
                        {
                        	$value = $CI->newschedules_model->get_subschedule3c($group_id,'');
				$total_dr3 = $value[3];
				echo "<td align=\"right\">" . convert_amount_dc(+$total_dr3) . "</td>";
                        	echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                        echo "</tr>";
			}//if
                        }
                }

                if($name[18]){
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo "&nbsp;" .  $name[18];
                        echo "</td>";
                        foreach($query->result() as $row)
                        {
                        $group_id = $row->id;
                        $group_name = $row->name;
			if($group_id == "106")
                        {
                        	$value = $CI->newschedules_model->get_subschedule3c($group_id,'');
				$total_revenue3 = $value[4];
				echo "<td align=\"right\">" . convert_amount_dc(+$total_revenue3) . "</td>";
                        	echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                        echo "</tr>";
			}//if
                        }
                }

		if($name[19]){
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo "&nbsp;" .  $name[19];
                        echo "</td>";
                        foreach($query->result() as $row)
                        {
                        $group_id = $row->id;
                        $group_name = $row->name;
			if($group_id == "106")
                        {
                        	$value = $CI->newschedules_model->get_subschedule3c($group_id,'');
				$total_capital3 = $value[5];
				echo "<td align=\"right\">" . convert_amount_dc(+$total_capital3) . "</td>";
                        	echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                        echo "</tr>";
			}//if
                        }
                }


                echo "</tr>";
                echo "<tr>";
                echo "<td align=\"right\">";
                echo "<strong> TOTAL(h)</strong>";
                echo "</td>";
		if(($total_dr3 > 0) && ($total_revenue3 > 0) && ($total_capital3 > 0))
		{
		$plan_total8 = $total_dr3 + $total_revenue3 + $total_capital3;
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($plan_total8) . "</strong>";
                echo "</td>";
		}else{
		$plan_total8 = $total_dr3 + $total_revenue3 + $total_capital3;
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($plan_total8) . "</strong>";
                echo "</td>";
		}
		echo "</tr>";
                echo "<tr>";
                echo "<td align=\"right\">";
                echo "Unutilized carried forward(g-h)";
                echo "</td>";
	 	if(($plan_total7 > 0) && ($plan_total8 > 0))
		{
		$total4 = $plan_total7 - $plan_total8;
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($total4) . "</strong>";
                echo "</td>";
		}else{
		$total4 = $plan_total7 + $plan_total8;
		
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($total4) . "</strong>";
                echo "</td>";
		}	
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
		echo "</tr>";
		
		echo "<tr>";
		echo "<td align=\"right\">";
                echo "*Grand Total (A+B+C+D)";
                echo "</td>";
	
		if(($total1 > 0) && ($total2 > 0) && ($total3 > 0) && ($total4 > 0))
		{		
		$grand_total = $total1 + $total2 + $total3 + $total4;
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($grand_total) . "</strong>";
                echo "</td>";
		}else{
		$grand_total = $total1 + $total2 + $total3 + $total4;
		echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc($grand_total) . "</strong>";
                echo "</td>";
		}
		echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
                echo "</tr>"; 
	}
		
	function subschedule5($code)
	{
		$counter=1;
		$sum = 0;
		$CI = & get_instance();
                $CI->load->model('group_model');
                $id = $CI->group_model->get_group_id($code);
                $name = $CI->group_model->get_group_name($id);
                $CI->db->select('name,code,id')->from('groups')->where('parent_id',$id);
                $group_detail = $CI->db->get();
                $group_result = $group_detail->result();
                foreach($group_result as $row)
                {
                        $group_name = $row->name;
                        $group_id =$row->id;
                        $group_code = $row->code;
                        $asset = new Reportlist1();
                        $asset->init($row->id);
                        $asset_total = $asset->total;
                        $sum = $sum + $asset_total;
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\" align=\"center\">";
                        echo $counter;
                        $counter++;
			echo "</td>";
			echo "<td>";
			echo "&nbsp;" .  $group_name;
                        echo "</td>";
                        echo "<td align=\"right\" colspan=\"2\">" . convert_amount_dc($asset_total) . "</td>";
                        echo "<td align=\"right\" colspan=\"2\">" . convert_amount_dc(0) . "</td>";
		}//foreach
		$this->curr_total1 = $sum;
	}

	function schedule_template6($code,$count)
	{
		$counter=1;
                $sum = 0;
                $CI = & get_instance();
                $CI->load->model('group_model');
                $id = $CI->group_model->get_group_id($code);
                $name = $CI->group_model->get_group_name($id);
                $CI->db->select('name,code,id')->from('groups')->where('parent_id',$id);
                $group_detail = $CI->db->get();
                $group_result = $group_detail->result();
                foreach($group_result as $row)
                {
                        $group_name = $row->name;
			if($group_name == 'Corpus Central Government Securities')
			$group_name = 'Central Government Securities';
			elseif($group_name == 'Corpus State Government Securities')
                        $group_name = 'State Government Securities';
			elseif($group_name == 'Corpus Other approved Securities')
                        $group_name = 'Other approved Securities';
			elseif($group_name == 'Corpus Shares')
                        $group_name = 'Shares';
			elseif($group_name == 'Corpus Debentures and Bonds')
                        $group_name = 'Debentures and Bonds';
			elseif($group_name == '')
                        $group_name = 'Other Investment';

                        $group_id =$row->id;
                        $group_code = $row->code;
                        $asset = new Reportlist1();
                        $asset->init($row->id);
                        $asset_total = $asset->total;
                        $sum = $sum + $asset_total;
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
			echo "&nbsp;&nbsp;";
                        echo $counter;
                        echo ".&nbsp;";
                        $counter++;
                        echo "&nbsp;" .  $group_name;
                        echo "</td>";
                        echo "<td align=\"right\" colspan=\"2\">" . convert_amount_dc(0) . "</td>";
                        echo "<td align=\"right\" colspan=\"2\">" . convert_amount_dc(0) . "</td>";
                }//foreach
                $this->curr_total1 = $sum;


	}

    function income_exp_mhrdnew($id ,$type,$database)
    {
        $c =14;
        $counter=8;
        $total = "";
        $sum = "";
        $CI =& get_instance();
        $CI->db->from('settings');
        $detail = $CI->db->get();
        foreach ($detail->result() as $row)
        {
            $date_1 = $row->fy_start;
            $date_2 = $row->fy_end;
        }
        $fy_start=explode("-",$date_1);
        $fy_end=explode("-",$date_2);

        $curr_year = $fy_start[0] ."-" .$fy_end[0];
        $prev_year = ($fy_start[0]-1) ."-" . ($fy_end[0]-1);

        /*Get current label*/
        $current_active_account = $CI->session->userdata('active_account');

        $CI->db->select('name,code,id')->from('groups')->where('parent_id',$id);
        $main = $CI->db->get();
        $main_result= $main->result();

        foreach($main_result as $row)
        {
            $name = $row->name;
            $code =$row->code;
            $ledg_id = $row->id;
            echo "<tr class=\"tr-group\">";
            echo "<td class=\"td-group\">";
            echo "&nbsp;" .  $name;
            echo "</td>";
            echo "<td class=\"td-group\" align=\"center\">";
            if($id == 3 && $type == "view" && $database == "NULL" )
            {   
                $counter++;

                echo "&nbsp;" . anchor_popup('report2/IE_schedules/' . $code . '/' . $counter, $counter, array('title' => $name, 'style' => 'color:#000000;text-decoration:none;'));
                echo"</td>";
                $income = new Reportlist1();
                $income->init($ledg_id);
                $total = $income->total;
                $sum = $sum + $total;
                $total = 0 - $total;
                echo "<td align=\"right\">".money_format('%!i', convert_cur($total))."</td>";
                echo "<td align=\"right\">".money_format('%!i', convert_cur(0))."</td>";
                echo"</tr>";
            }
                  
            if($id == 4 && $type == "view" && $database == "NULL")
            {
                if($name == 'Depreciation'){
                    //echo "&nbsp;" . anchor_popup('report2/IE_schedules/' . $code . '/' . $c, 4, array('title' => $name, 'style' => 'color:#000000;text-decoration:none;'));
                    echo 4;
                }
                else{
                $c++;
                        echo "&nbsp;" . anchor_popup('report2/IE_schedules/' . $code . '/' . $c, $c, array('title' => $name, 'style' => 'color:#000000;text-decoration:none;'));
                }
                echo"</td>";
                $income = new Reportlist1();
                $income->init($ledg_id);
                $total = $income->total;
                $sum = $sum + $total;
                echo "<td align=\"right\">".money_format('%!i', convert_cur($total))."</td>";
                echo "<td align=\"right\">".money_format('%!i', convert_cur(0))."</td>";
                echo"</tr>";               
            }
        }        
    return $sum;
    }

    function get_exp_schedules($code,$type,$database,$count)
    {

        $CI = & get_instance();
        $sum1 = "";
        $sum = "";
        $str = 'a';
        $curr_sum_total = "";
        $curr_plan_sum_total = "";
        $curr_non_plan_sum_total = "";
        $curr_sum = "";
        $curr_plan_total = "";
        $curr_non_plan_total = "";
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
        $CI =& get_instance();
        $CI->load->model('ledger_model');
        $id = $CI->ledger_model->get_group_id($code);
        $parent = $CI->ledger_model->get_group_name($id);

        $CI->db->select('name,code,id')->from('groups')->where('parent_id',$id);
        //$CI->db->select('name,code,id')->from('ledgers')->where('group_id',$id);
        $main = $CI->db->get();
        $main_result= $main->result();
        $CI->db->select('name,code,id')->from('ledgers')->where('group_id',$id);
        $ledger_detail = $CI->db->get();
        $ledger_result = $ledger_detail->result();
        
        foreach($main_result as $row)
        {
            $cr_total = "";
            $dr_total = "";
            $plan_cr_total = "";
            $nonplan_cr_total = "";
            $plan_dr_total = "";
            $nonplan_dr_total = "";
            $plan_total ="";
            $non_plan_total =""; 
            $total = "";
            $group_name = $row->name;
            $group_id =$row->id;
            $group_code = $row->code;
            if(($type == 'view') && ($database == 'NULL'))
            {
                echo "<tr class=\"tr-group\">";
                echo "<td class=\"td-group\">";
                if($group_name == "Retirement and Terminal Benefits")
                    echo anchor_popup('report2/IE_schedules/' . $group_code . '/' . '15A', $str.") " .$group_name, array('title' => $group_name, 'style' => 'color:#000000;text-decoration:none;font-weight: bold;'));
                else
                echo $str.") ".  $group_name;
                echo "</td>";
                $str++;
                $CI->db->select('code')->from('groups')->where('id', $group_id);
                $code_result= $CI->db->get();
                $code = $code_result->row();
                $CI->db->select('id')->from('ledgers');
                foreach( $code as $code1){
                       $CI->db->like('code', $code1);
                 }
                $query_result =$CI->db->get();
                $no_row = $query_result->num_rows();

                $q_result = $query_result->result();
                foreach ($q_result as $row)
                {
                    $ledger_id = $row->id;
                    $CI->db->select('entry_items.amount as entry_items_amount, entry_items.dc as entry_items_dc,entry_items.id as entry_items_id,entries.sanc_type as sanc_type');
                    $CI->db->from('entries')->join('entry_items', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id);
                    $CI->db->where('date >=', $date1);
                    $CI->db->where('date <=', $date2);
                    $result11 =$CI->db->get();
                    $entry_result = $result11->result();
                    foreach($entry_result as $query_row)
                    {
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
                                    $plan_cr_total = $plan_cr_total + $sum;
                                }elseif($dc == "D"){
                                    $plan_dr_total = $plan_dr_total + $sum;
                                }

                            }elseif($sanc_type == "non_plan"){
                                if($dc == "C")
                                {
                                    $nonplan_cr_total = $nonplan_cr_total + $sum;
                                }elseif($dc == "D"){
                                    $nonplan_dr_total = $nonplan_dr_total + $sum;
                                }

                            }
                        }else{

                            if($dc == "C")
                            {
                                $cr_total = $cr_total + $sum;
                            }elseif($dc == "D"){
                                $dr_total = $dr_total + $sum;
                            }
                        }
                    }
                }
               
                $nonplan_dr_total = $nonplan_dr_total + $dr_total;
                $nonplan_cr_total = $nonplan_cr_total + $cr_total;

                $plan_total = $plan_dr_total - $plan_cr_total;
                $non_plan_total = $nonplan_dr_total - $nonplan_cr_total;

                $total = $plan_total + $non_plan_total; 
                echo "<td align=\"right\">". convert_amount_dc($plan_total). "</td>";
                echo "<td align=\"right\">". convert_amount_dc($non_plan_total). "</td>";
                echo "<td align=\"right\">". convert_amount_dc($total). "</td>";
                echo "<td align=\"right\">". convert_amount_dc(0). "</td>";
                echo "<td align=\"right\">". convert_amount_dc(0). "</td>";
                echo "<td align=\"right\">". convert_amount_dc(0). "</td>";
                $curr_sum = $curr_sum + $total;
                $curr_plan_total = $curr_plan_total + $plan_total;
                $curr_non_plan_total = $curr_non_plan_total + $non_plan_total;

                //echo "curr =$curr_sum plan = $curr_plan_total non = $curr_non_plan_total ";


                if ($parent == "Transportations Expenses")
                {
                    $CI->db->select('name,code,id')->from('ledgers')->where('group_id',$group_id);
                    $sub_groups = $CI->db->get();
                    $sub_group_result = $sub_groups->result();
                    foreach($sub_group_result as $row3)
                    {
                        $sub_g_name = $row3->name;
                        $sub_g_id = $row3->id;
                        echo "<tr class=\"tr-ledger\">";
                        echo "<td class=\"td-ledger\">";
                        echo "&nbsp;&nbsp;&nbsp;&nbsp" .  $sub_g_name;
                        echo "</td>";
                        $CI->load->model('ledger_model');
                        $sub_g_total = $CI->ledger_model->get_ledger_balance2($sub_g_id);
                        
                        $sub_plan_total = $sub_g_total['plan'];
                        $sub_non_plan_total = $sub_g_total['nonplan'];
                        
                        $sub_g_total = $sub_plan_total + $sub_non_plan_total;
                        echo "<td align=\"right\">" . convert_amount_dc($sub_plan_total) . "</td>"; 
                        echo "<td align=\"right\">". convert_amount_dc($sub_non_plan_total). "</td>";
                        echo "<td align=\"right\">". convert_amount_dc($sub_g_total). "</td>";
                        echo "<td align=\"right\">". convert_amount_dc(0). "</td>";
                        echo "<td align=\"right\">". convert_amount_dc(0). "</td>";
                        echo "<td align=\"right\">". convert_amount_dc(0). "</td>";                       
                    }
                }
            }
    
        }
        $curr_sum1 = "";
        $curr_plan_total1 = "";
        $curr_non_plan_total1 = "";
        foreach($ledger_result as $row1)
        {
            $total1 = "";
            $ledger_name = $row1->name;
            $ledger_id =$row1->id;
            if(($type == 'view') && ($database == 'NULL'))
            {
                echo "<tr class=\"tr-group\">";
                echo "<td class=\"td-group\">";                  
                echo $str.") ".$ledger_name;
                echo "</td>";
                $str++;
                $CI =& get_instance();
                $CI->load->model('ledger_model');
                $total1 = $CI->ledger_model->get_ledger_balance2($ledger_id);
                foreach ($total1 as $value){
                        $plan_total = $value['plan'];
                        $non_plan_total = $value['nonplan'];
                }
                $total1 = $plan_total + $non_plan_total;
                if($plan_total ==""){
                    echo "<td align=\"right\">". convert_amount_dc(0). "</td>";
                }else{
                    echo "<td align=\"right\">" . convert_amount_dc($plan_total) . "</td>";
                }
                if($non_plan_total == "")
                {
                    echo "<td align=\"right\">". convert_amount_dc($non_plan_total == ""). "</td>";
                }else{
                    echo "<td align=\"right\">". convert_amount_dc(0). "</td>";
                }
                echo "<td align=\"right\">". convert_amount_dc($total1). "</td>";
                echo "<td align=\"right\">". convert_amount_dc(0). "</td>";
                echo "<td align=\"right\">". convert_amount_dc(0). "</td>";
                echo "<td align=\"right\">". convert_amount_dc(0). "</td>";
                $curr_sum1 = $curr_sum1 + $total1;
                $curr_plan_total1 = $curr_plan_total1 + $plan_total;
                $curr_non_plan_total1 = $curr_non_plan_total1 + $non_plan_total;
            }           
        }
        $curr_sum_total = $curr_sum1 + $curr_sum;
        $curr_plan_sum_total = $curr_plan_total + $curr_plan_total1;
        $curr_non_plan_sum_total = $curr_non_plan_total + $curr_non_plan_total1;
        $this->curr_sum_total = $curr_sum_total;
        $this->curr_plan_sum_total = $curr_plan_sum_total;
        $this->curr_non_plan_sum_total = $curr_non_plan_sum_total;

        //echo "total = $curr_sum_total plan= $curr_plan_sum_total nonplan = $curr_non_plan_sum_total";
    return;
    }

    function schedule17($code,$type,$database,$count,$var){

        $CI = & get_instance();
        $sum1 = "";
        $sum = "";
        $curr_sum_total = "";
        $curr_plan_sum_total = "";
        $curr_non_plan_sum_total = "";
        $curr_sum = "";
        $curr_plan_total = "";
        $curr_non_plan_total = "";
        $str = 'a';

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

        $CI->db->select('name,code,id')->from('groups')->where('parent_id',$id);
        //$CI->db->select('name,code,id')->from('ledgers')->where('group_id',$id);
        $main = $CI->db->get();
        $main_result= $main->result();
        $CI->db->select('name,code,id')->from('ledgers')->where('group_id',$id);
        $ledger_detail = $CI->db->get();
        $ledger_result = $ledger_detail->result();
        
        foreach($main_result as $row)
        {
            $cr_total = "";
            $dr_total = "";
            $plan_cr_total = "";
            $nonplan_cr_total = "";
            $plan_dr_total = "";
            $nonplan_dr_total = "";
            $plan_total ="";
            $non_plan_total =""; 
            $total = "";
            $group_name = $row->name;
            $group_id =$row->id;
            if(($type == 'view') && ($database == 'NULL'))
            {
                
                $CI->db->select('code')->from('groups')->where('id', $group_id);
                $code_result= $CI->db->get();
                $code = $code_result->row();
                $CI->db->select('id,name')->from('ledgers');
                foreach( $code as $code1){
                       $CI->db->like('code', $code1);
                 }
                $query_result =$CI->db->get();
                $no_row = $query_result->num_rows();

                $q_result = $query_result->result();
                foreach ($q_result as $row)
                {

                    $ledger_id = $row->id;
                    $ledger_name = $row->name;

                    $ledg_plan_cr_total = 0;
                    $ledg_plan_dr_total = 0;

                    $ledg_nonplan_dr_total = 0;
                    $ledg_nonplan_cr_total = 0;

                    $ledg_cr_total =0;
                    $ledg_dr_total = 0;

                    $ledg_plan_total = 0;
                    $ledg_non_plan_total = 0;

                    $ledg_total = 0;
                    $CI->db->select('entry_items.amount as entry_items_amount, entry_items.dc as entry_items_dc,entry_items.id as entry_items_id,entries.sanc_type as sanc_type');
                    $CI->db->from('entries')->join('entry_items', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id);
                    $CI->db->where('date >=', $date1);
                    $CI->db->where('date <=', $date2);
                    $result11 =$CI->db->get();
                    $entry_result = $result11->result();
                    foreach($entry_result as $query_row)
                    {

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
                                    $plan_cr_total = $plan_cr_total + $sum;
                                    $ledg_plan_cr_total = $ledg_paln_cr_total + $sum;
                                }elseif($dc == "D"){
                                    $plan_dr_total = $plan_dr_total + $sum;
                                    $ledg_plan_dr_total = $ledg_plan_dr_total + $sum;
                                }

                            }elseif($sanc_type == "non_plan"){
                                if($dc == "C")
                                {
                                    $nonplan_cr_total = $nonplan_cr_total + $sum;
                                    $ledg_nonplan_cr_total = $ledg_nonplan_cr_total + $sum;
                                }elseif($dc == "D"){
                                    $nonplan_dr_total = $nonplan_dr_total + $sum;
                                    $ledg_nonplan_cr_total = $ledg_nonplan_cr_total + $sum;
                                }

                            }
                        }else{

                            if($dc == "C")
                            {
                                $cr_total = $cr_total + $sum;
                                 $ledg_cr_total = $ledg_cr_total + $sum;
                            }elseif($dc == "D"){
                                $dr_total = $dr_total + $sum;
                                $ledg_dr_total = $ledg_dr_total + $sum;
                            }
                        }
                    }

                    $ledg_nonplan_dr_total = $ledg_nonplan_dr_total + $ledg_dr_total;
                    $ledg_nonplan_cr_total = $ledg_nonplan_cr_total + $ledg_cr_total;

                    $ledg_plan_total = $ledg_plan_dr_total - $ledg_plan_cr_total;
                    $ledg_non_plan_total = $ledg_nonplan_dr_total - $ledg_nonplan_cr_total;

                    $ledg_total = $ledg_plan_total + $ledg_non_plan_total; 
                    if(($group_name == 'Infrastructure Expenses' && $var == 'A') || ($group_name == 'Communication Expenses' && $var == 'B')){
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp".$str.") " .  $ledger_name;
                        echo "</td>";
                        $str++;
                        echo "<td align=\"right\">". convert_amount_dc($ledg_plan_total). "</td>";
                        echo "<td align=\"right\">". convert_amount_dc($ledg_non_plan_total). "</td>";
                        echo "<td align=\"right\">". convert_amount_dc($ledg_total). "</td>";
                        echo "<td align=\"right\">". convert_amount_dc(0). "</td>";
                        echo "<td align=\"right\">". convert_amount_dc(0). "</td>";
                        echo "<td align=\"right\">". convert_amount_dc(0). "</td>";
                    }
                }
                       
                $nonplan_dr_total = $nonplan_dr_total + $dr_total;
                $nonplan_cr_total = $nonplan_cr_total + $cr_total;

                $plan_total = $plan_dr_total - $plan_cr_total;
                $non_plan_total = $nonplan_dr_total - $nonplan_cr_total;

                $total = $plan_total + $non_plan_total; 
                if(($group_name != 'Infrastructure Expenses') && ($group_name != 'Communication Expenses') && ($var == 'C')){
                    echo "<tr class=\"tr-group\">";
                    echo "<td class=\"td-group\">";
                    echo "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp".$str.") " .  $group_name;
                    echo "</td>";
                    $str++;
                    echo "<td align=\"right\">". convert_amount_dc($plan_total). "</td>";
                    echo "<td align=\"right\">". convert_amount_dc($non_plan_total). "</td>";
                    echo "<td align=\"right\">". convert_amount_dc($total). "</td>";
                    echo "<td align=\"right\">". convert_amount_dc(0). "</td>";
                    echo "<td align=\"right\">". convert_amount_dc(0). "</td>";
                    echo "<td align=\"right\">". convert_amount_dc(0). "</td>";
                }
                $curr_sum = $curr_sum + $total;
                    $curr_plan_total = $curr_plan_total + $plan_total;
                    $curr_non_plan_total = $curr_non_plan_total + $non_plan_total;
                    //echo "curr =$curr_sum plan = $curr_plan_total non = $curr_non_plan_total ";
                //}           
            }
    
        }
        $curr_sum1 = "";
        $curr_plan_total1 = "";
        $curr_non_plan_total1 = "";
        foreach($ledger_result as $row1)
        {
            $total1 = "";
            $ledger_name = $row1->name;
            $ledger_id =$row1->id;
            if(($type == 'view') && ($database == 'NULL'))
            {
                echo "<tr class=\"tr-group\">";
                echo "<td class=\"td-group\">";                  
                echo "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp".$str.") ".$ledger_name;
                echo "</td>";
                $str++;
                $CI =& get_instance();
                $CI->load->model('ledger_model');
                $total1 = $CI->ledger_model->get_ledger_balance2($ledger_id);
                foreach ($total1 as $value){
                        $plan_total = $value['plan'];
                        $non_plan_total = $value['nonplan'];
                }
                $total1 = $plan_total + $non_plan_total;
                if($plan_total ==""){
                    echo "<td align=\"right\">". convert_amount_dc(0). "</td>";
                }else{
                    echo "<td align=\"right\">" . convert_amount_dc($plan_total) . "</td>";
                }
                if($non_plan_total == "")
                {
                    echo "<td align=\"right\">". convert_amount_dc($non_plan_total == ""). "</td>";
                }else{
                    echo "<td align=\"right\">". convert_amount_dc(0). "</td>";
                }
                echo "<td align=\"right\">". convert_amount_dc($total). "</td>";
                echo "<td align=\"right\">". convert_amount_dc(0). "</td>";
                echo "<td align=\"right\">". convert_amount_dc(0). "</td>";
                echo "<td align=\"right\">". convert_amount_dc(0). "</td>";
                $curr_sum1 = $curr_sum1 + $total1;
                $curr_plan_total1 = $curr_plan_total1 + $plan_total;
                $curr_non_plan_total1 = $curr_non_plan_total1 + $non_plan_total;
            }           
        }
        $curr_sum_total = $curr_sum1 + $curr_sum;
        $curr_plan_sum_total = $curr_plan_total + $curr_plan_total1;
        $curr_non_plan_sum_total = $curr_non_plan_total + $curr_non_plan_total1;
        $this->curr_sum_total = $curr_sum_total;
        $this->curr_plan_sum_total = $curr_plan_sum_total;
        $this->curr_non_plan_sum_total = $curr_non_plan_sum_total;

        //echo "total = $curr_sum_total plan= $curr_plan_sum_total nonplan = $curr_non_plan_sum_total";
    return;
    }

    function schedule9($code,$type,$database,$count,$var)
    {
        $CI = & get_instance();
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

        $sum = 0;
        $total1 = 0;
        $curr_year = $fy_start[0] ."-" .$fy_end[0];
        $prev_year = ($fy_start[0]-1) ."-" . ($fy_end[0]-1);
        $CI =& get_instance();
        $CI->load->model('ledger_model');
        $id = $CI->ledger_model->get_group_id($code);

        $CI->db->select('name,code,id')->from('ledgers')->where('group_id',$id);
        $ledger_detail = $CI->db->get();
        $ledger_result = $ledger_detail->result();

        $c =1;
        foreach($ledger_result as $row1)
        {
            $total = "";
            $ledger_code = $row1->code;
            $ledger_name = $row1->name;
            $ledger_id =$row1->id;

            $var1 = $CI->ledger_model->get_ledger_var($ledger_code);
            if(($type == 'view') && ($database == 'NULL'))
            {
                echo "<tr class=\"tr-group\">";
                if($var == $var1)
                {
                    echo "<td class=\"td-group\">";                  
                    echo "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp;".$c.".  ".$ledger_name;
                    echo "</td>";
                    
                    $total = $CI->ledger_model->get_ledger_balance1($ledger_id);
                    
                    echo "<td align=\"right\">". convert_amount_dc($total). "</td>";
                    echo "<td align=\"right\">". convert_amount_dc(0). "</td>";
                    $sum = $sum + $total;
                    $this->sum = $sum;
                    $c++;
                }                
            }           
        }
    $total1 = $sum + $total1;
    return $total1;
    }

    function get_schedule14($code,$type,$database,$count)
    {
        $CI = & get_instance();
        $num = 1;
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

        $sum = 0;
        $total1 = 0;
        $counter = 0;
        $curr_year = $fy_start[0] ."-" .$fy_end[0];
        $prev_year = ($fy_start[0]-1) ."-" . ($fy_end[0]-1);
        $CI =& get_instance();
        $CI->load->model('ledger_model');
        $id = $CI->ledger_model->get_group_id($code);

        $CI->db->select('name,code,id')->from('ledgers')->where('group_id',$id);
        $ledger_detail = $CI->db->get();
        $ledger_result = $ledger_detail->result();

        foreach($ledger_result as $row1)
        {
            $total = "";
            $ledger_code = $row1->code;
            $ledger_name = $row1->name;
            $ledger_id =$row1->id;
            if(($type == 'view') && ($database == 'NULL'))
            {
                echo "<tr class=\"tr-group\">";
               
                echo "<td class=\"td-group\">";                  
                echo "&nbsp&nbsp&nbsp;".$num.".  ".$ledger_name;
                echo "</td>";
                
                $total = $CI->ledger_model->get_ledger_balance1($ledger_id);
                
                echo "<td align=\"right\">". convert_amount_dc($total). "</td>";
                echo "<td align=\"right\">". convert_amount_dc(0). "</td>";
                $sum = $sum + $total;
                $this->sum = $sum;
                $num++;               
            }           
        }
    $total1 = $sum + $total1;
    return $total1;
    }

    function get_income_schedule($code,$type,$database,$count)
    {
        $i =0;
        $sum = 0;
        $sum1 =0;
        $CI = & get_instance();
        $c  = 1;
        $str = 'A';
        //Get current label.
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
        $CI =& get_instance();
        $CI->load->model('ledger_model');
        $id = $CI->ledger_model->get_group_id($code);
        $parent = $CI->ledger_model->get_group_name($id);
        $CI->db->select('name,code,id')->from('groups')->where('parent_id',$id);
    //  $CI->db->select('name,code,id')->from('ledgers')->where('group_id',$id);
        $main = $CI->db->get();
        $main_result= $main->result();
        $CI->db->select('name,code,id')->from('ledgers')->where('group_id',$id);
        $ledger_detail = $CI->db->get();
        $ledger_result = $ledger_detail->result();
        //$CI =& get_instance();
        //$CI->load->model('payment_model');
        //$db = $CI->payment_model->database_name();
                              //  echo "</tr>";   
        
        foreach($main_result as $row)
        {
            $name = $row->name;
            $group_id =$row->id;
            if(($type == 'view') && ($database == 'NULL'))
            {
                echo "<tr class=\"tr-group\">";
                if($count ==  13)
                { 
                    echo "<td class=\"td-group\" style = font-weight:bold;>";
                    echo $str.".&nbsp&nbsp" .  $name;
                }else{
                    echo "<td class=\"td-group\">";
                    echo "&nbsp&nbsp;".  $c.". ". $name;
                    $c++;
                }
                echo "</td>";
                $str++;
                $object = new Reportlist1();
                $object->init($group_id);
                $total = $object->total;
               echo "<td align=\"right\">". convert_amount_dc($total). "</td>";
               echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                $sum = $sum + $total;   

                if($name ==  "Income from Land and Building" || $name ==  "Interest on Investment" || $name ==  "Interest On Loans")
                {
                    $CI->db->select('name,code,id')->from('ledgers')->where('group_id',$group_id)->where('id !=' ,'123');
                    $sub_groups = $CI->db->get();
                    $sub_group_result = $sub_groups->result();
                    $num = 1;
                    foreach($sub_group_result as $row3)
                    {
                        $ledg_name = $row3->name;
                        $ledg_id = $row3->id;
                        echo "<tr class=\"tr-ledger\">";
                        echo "<td class=\"td-ledger\">";
                        if($name ==  "Income from Land and Building")
                        echo "&nbsp;&nbsp;&nbsp;&nbsp&nbsp&nbsp" . $num.". ". $ledg_name;
                        else
                        echo "&nbsp;&nbsp;&nbsp;&nbsp&nbsp&nbsp" . $ledg_name;
                        echo "</td>";
                        //$CI->load->model('ledger_model');
                        $ledg_total = $CI->ledger_model->get_ledger_balance1($ledg_id);
                        echo "<td align=\"right\">" . convert_amount_dc($ledg_total) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                        $num++;
                    }
                }                 
            }
        }
        if($count == 13){
                echo "<tr class=\"tr-group\" style = font-weight:bold;>";
                echo "<td class=\"td-group\">";                  
                echo "G. Others";
                echo "</td><td></td><td></td>";
                $num = 1;
        }
        foreach($ledger_result as $row1)
        {
            $ledger_name = $row1->name;
            $ledger_id =$row1->id;
            if(($type == 'view') && ($database == 'NULL'))
            {
                echo "<tr class=\"tr-group\">";
                echo "<td class=\"td-group\">"; 
                if($count == 13){
                    echo "&nbsp;&nbsp;&nbsp;&nbsp&nbsp&nbsp" .  $num.". ".$ledger_name;
                    $num ++;
                }else{               
                    echo "&nbsp&nbsp;".  $c.". " .$ledger_name;
                    $c++;
                }
                echo "</td>";
                $CI =& get_instance();
                $CI->load->model('ledger_model');
                $total1 = $CI->ledger_model->get_ledger_balance1($ledger_id);
                echo "<td align=\"right\">" . convert_amount_dc($total1) . "</td>";
                echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                $sum1 = $sum1 + $total1;
                $this->ledger_id = $ledger_id;
                
            }   
        }

        $curr_total= $sum + $sum1;
        //$prev_total = $prev_sum + $prev_sum1;
        $this->curr_total = $curr_total;
        //$this->prev_total = $prev_total;    
    }

    function schedule10($code,$type,$database,$count)
    {
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
        
        
        if(($type == 'view') && ($database == 'NULL'))
        {

            $CI->load->model('Group_model');
            $CI->load->model('Ledger_model');

            $group_id = $CI->Group_model->get_id('Grant/Subsidies and Donations');
            $CI->db->select('name,id')->from('ledgers')->where('group_id',$group_id);
            $query = $CI->db->get();
            $counter = $query->num_rows();
            
            $q_result = $query->result();
            $ledger_id = array();
            $x = 0;
            foreach($q_result as $row){
                $ledg_id = $row->id;
                $ledger_id[$x] = $ledg_id;
                $x++;
                //$op_balance = $CI->Ledger_model->get_op_balance($ledger_id);
                //$dr_total = $CI->Ledger_model->get_dr_total2($ledger_id);
                //$cr_total = $CI->Ledger_model->get_cr_total2($ledger_id);
                //$capital_total = $CI->Ledger_model->get_capital_exp_total($ledger_id);
                //$revenue_total = $CI->Ledger_model->get_revenue_exp_total($ledger_id);
            }
            //print_r($ledger_id);
            $name = array();
            $name[0] = "Balance B/F";
            $name[1] = "Add: Receipts during the year";
            $name[2] = "Total";
            $name[3] = "Less: Refund to UGC";
            $name[4] = "Balance";
            $name[5] = "Less: Utilised for Capital expenditure (A)";
            $name[6] = "Balance";
            $name[7] = "Less: utilized for Revenue Expenditure (B)";
            $name[8] = "Balance C/F (C)";            
            $groupplan = $CI->Ledger_model->get_schedule10_data($ledger_id[0],'plan');
            $groupnonplan = $CI->Ledger_model->get_schedule10_data($ledger_id[0],'nonplan');
            $groupspecific = $CI->Ledger_model->get_schedule10_data($ledger_id[0],'specific_sch');
            $group1plan = $CI->Ledger_model->get_schedule10_data($ledger_id[1],'plan');
		//print_r($group1plan);
            $group1nonplan = $CI->Ledger_model->get_schedule10_data($ledger_id[1],'nonplan');
            $group2plan = $CI->Ledger_model->get_schedule10_data($ledger_id[2],'plan');
            $group2nonplan = $CI->Ledger_model->get_schedule10_data($ledger_id[2],'nonplan');

            $a =0;
            do{
                $plantotal1 = 0;
                $plantotal2 = 0;
                $plantotal3 =0;

                $nonplantotal1 = 0;
                $nonplantotal2 = 0;
                $nonplantotal3 =0;
                $planspecifictotal = 0;
                $plan_total = 0;
                $non_plan_total = 0;
                $total = 0;

                if($a == 2 || $a == 4 || $a == 6 || $a == 8)
                echo "<tr class=\"tr-ledger\" style = font-weight:bold;>";
                else
                echo "<tr class=\"tr-group\">";
                $b =0;
                $c = ($counter * 2) + 6;
                do{
                    if($b == 0){
                    echo"<td>";
                    echo $name[$a];
                    }elseif($b == 1 || $b == (3 + $counter)){
                        echo "<td align=\"right\">"; 
                        if($b == 1){
                            echo money_format('%!i', convert_cur($groupplan[$a]));
                            $plantotal1 = $groupplan[$a];
                        }else{
                            echo money_format('%!i', convert_cur($groupnonplan[$a]));
                            $nonplantotal1 = $groupnonplan[$a];
                        }
                    }elseif($b == 2){
                        echo "<td align=\"right\">";
                        echo money_format('%!i', convert_cur($groupspecific[$a]));
                        $planspecifictotal = $groupspecific[$a];

                    }elseif($b == 3 || $b == (4 + $counter)){
                        echo "<td align=\"right\">";
                        if($b == 3){
                            echo money_format('%!i', convert_cur($group1plan[$a]));
                            $plantotal2 = $group1plan[$a];
                        }else{
                            echo money_format('%!i', convert_cur($group1nonplan[$a]));
                            $nonplantotal2 = $group1nonplan[$a];
                        }
                    }elseif($b == 4 || $b == (5 + $counter)){
                        echo "<td align=\"right\">";
                        if($b == 4){
                            echo money_format('%!i', convert_cur($group2plan[$a]));
                            $plantotal3 = $group2plan[$a];
                        }else{
                            echo money_format('%!i', convert_cur($group2nonplan[$a]));
                            $nonplantotal3 = $group2nonplan[$a];
                        }
                    }elseif($b == 5){
                        echo "<td align=\"right\">";
                        $plan_total = $plantotal1 + $plantotal2 + $plantotal3 +$planspecifictotal ;
                        echo money_format('%!i', convert_cur($plan_total));
                    }elseif($b == 9){
                        echo "<td align=\"right\">";
                        $non_plan_total = $nonplantotal1 + $nonplantotal2 + $nonplantotal3;
                        echo money_format('%!i', convert_cur($non_plan_total));
                    }elseif($b == 10){
                        echo "<td align=\"right\">";
                        $total = $non_plan_total + $plan_total;
                        echo money_format('%!i', convert_cur($total));
                    }else{
                        echo"<td>";
                    }
                    echo"</td>";
                    $b++;
                }while($b < $c);
                echo "</tr>";
                $a++;
            }while($a<9);
        }
    }


    function schedule15A($code,$count)
    {

        $CI =& get_instance();
        $CI->load->library('session');
        $date1 = $CI->session->userdata('date1');
        $date2 = $CI->session->userdata('date2');
        $total1 = 0;
        $total2 = 0;
        $total = 0;
        $total4 = 0;
       // $date3 = '2015-04-01 00:00:00';
        $CI->load->model('Group_model');
        $CI->load->model('Ledger_model');

        $provision_id = $CI->Group_model->get_id('Provision Received From Other Organisation For Retirement Benefits');
        $CI->db->select('id,name')->from('ledgers')->where('group_id',$provision_id);
        $query_result1 = $CI->db->get();

        $group_id = $CI->Group_model->get_id('Retirement and Terminal Benefits');
        $CI->db->select('id,name')->from('ledgers')->where('group_id',$group_id);
        $query_result = $CI->db->get();

        $pro_id = $CI->Group_model->get_id('Provision for Retirement Benefit');
        $CI->db->select('id,name')->from('ledgers')->where('group_id',$pro_id);
        $query_result2 = $CI->db->get();

        echo "<tr class=\"tr-group\">";
        echo "<td>"."Opening Balance as on   ". date_mysql_to_php_display($date2) . "</td>";
        $total_sum1 = array();
        $i = 0;
        $op_bal_cr =0;
        $op_bal_dr =0;
        foreach($query_result2->result() as $row2){
            $pro_ledg_id = $row2->id;
            $pro_ledg_name = $row2->name;
            $op_bal = $CI->Ledger_model->get_op_balance($pro_ledg_id);
            if($op_bal[1] =='C'){
                $op_bal[1] = 'Cr';
                $op_bal_cr - $op_bal_cr = $op_bal_cr + $op_bal[0];
            }
            elseif($op_bal[1] =='D'){
                $op_bal[1] = 'Dr';
                $op_bal_dr = $op_bal_dr + $op_bal[0];
            }

            $total1 = $op_bal_cr - $op_bal_dr;
            echo"<td align=\"right\">";
            if($op_bal[0] == 0)
            echo money_format('%!i', convert_cur($op_bal[0]));
            else
            echo $op_bal[1]." ".money_format('%!i', convert_cur($op_bal[0]));
            echo"</td>";
            //$total1 = $total1 + $op_bal_total;
            $total_sum1[$i] = $op_bal[0] ."-". $op_bal[1];
            $i++;  
        } 
        echo"<td align=\"right\">";
        if($total1 > 0)
        echo convert_amount_dc(-$total1);
        else
           echo convert_amount_dc($total1); 
        echo"</td>";
        echo "</tr>";

        echo "<tr class=\"tr-group\">";
        echo "<td>Addition : Capitalized value of Contributions Received from other Organizations</td>";
        //echo "</td>";
        $i = 0;
        $total_sum2 = array();
        foreach($query_result1->result() as $row1){
            $ledg_id = $row1->id;
            $ledg_bal = $CI->Ledger_model->get_ledger_balance1($ledg_id);
            echo"<td align=\"right\">";
            echo convert_amount_dc($ledg_bal);
            echo"</td>";
            $total2 = $total2 + $ledg_bal;
            $total_sum2[$i] = $ledg_bal;
            $i++;
        }
        echo"<td align=\"right\">";
        echo convert_amount_dc($total2);
        echo"</td>";
        echo "</tr>";

        echo "<tr class=\"tr-ledger\" style = font-weight:bold;>";
        $total3= 0;
        $total_sum3 = array();
        $sum_total3 = 0;
        echo "<td>Total (a)</td>";
        for($i =0 ; $i<=4 ;$i++)
        {
            $total_sum = explode('-', $total_sum1[$i]);
            $amount = $total_sum[0];
            $dc = $total_sum[1];
            if($dc == 'Cr')
            {
                $total_sum2[$i] = 0 - $total_sum2[$i];
                $total3 = $amount + $total_sum2[$i];
            }elseif($dc == 'Dr'){
                $total3 = $amount + $total_sum2[$i];
            }
            echo"<td align=\"right\">";
            echo money_format('%!i', convert_cur($total3));
            echo"</td>";
            $sum_total3 = $sum_total3 + $total3;
            $total_sum3[$i] = $total3;
        }
        echo"<td align=\"right\">";
        echo money_format('%!i', convert_cur($sum_total3));
        echo"</td>";
        echo "</tr>";

        echo "<tr class=\"tr-group\">";
        $total4 = 0;
        $i =0;
        echo "<td>Less: Actual Payment during the Year (b)</td>";
        foreach($query_result->result() as $row)
        {
            $ledger_id = $row->id;
            $name = $row->name;
            if($name == 'Payment of Guatuity' || $name == 'Payment of Leave Encashment' || $name =='Payment of Pension' || $name == 'Payment of DCRG'|| $name == 'Payment of Commutation')
            {
                $payment_bal = $CI->Ledger_model->get_ledger_balance1($ledger_id);
                echo"<td align=\"right\">";
                echo convert_amount_dc($payment_bal);
                echo"</td>";
                $total4 = $total4 + $payment_bal;
                $total_sum4[$i] = $payment_bal;
                $i++; 
            }
        } 
        echo"<td align=\"right\">";
        echo convert_amount_dc($total4);
        echo"</td>";
        echo "</tr>";

        echo "<tr class=\"tr-ledger\" style = font-weight:bold;>";
        $sum_total5 =0;
        $total_sum5 = array();
        $total5 =0;
        echo "<td>Balance Available on 31.03 c (a-b)</td>";
        for($i =0 ; $i<=4 ;$i++)
        {
            $sum_total5 = $total_sum3[$i] - $total_sum4[$i];
            echo"<td align=\"right\">";
            echo money_format('%!i', convert_cur($sum_total5));
            echo"</td>";
            $total5 = $total5 + $sum_total5;
            $total_sum5[$i] = $sum_total5;
        }
        echo"<td align=\"right\">";
        echo money_format('%!i', convert_cur($total5));
        echo"</td>";
        echo "</tr>";

        echo "<tr class=\"tr-group\">";
        $total6 = 0;
        $i =0;
        $total_sum6 = array();
        echo "<td>Provision required on 31.03 as per Actuarial Valuation (d)</td>";
        foreach($query_result2->result() as $row4)
        {
            $pro_id = $row4->id;
            $pro_bal1 = $CI->Ledger_model->get_ledger_balance1($pro_id);
            echo"<td align=\"right\">";
            echo convert_amount_dc($pro_bal1);
            echo"</td>";
            $total6 = $total6 + $pro_bal1;
            $total_sum6[$i] = $pro_bal1;
            $i++;
        }
        echo"<td align=\"right\">";
        echo convert_amount_dc($total6);
        echo"</td>";
        echo "</tr>";
        
        echo "<tr class=\"tr-ledger\" style = font-weight:bold;>";
        echo "<td>A. Provision to be made in the Current year (d -c)</td>";
        $total7 =0;
        $sum_total7 = 0;
        $total_sum7 = array();
        for($i =0 ; $i<=4 ;$i++)
        {
            $sum_total7 = (-$total_sum6[$i]) - $total_sum5[$i];
            echo"<td align=\"right\">";
            echo money_format('%!i', convert_cur($sum_total7));
            echo"</td>";
            $total7 = $total7+ $sum_total7;
            $total_sum7[$i] = $sum_total7;
        }
        echo"<td align=\"right\">";
        echo money_format('%!i', convert_cur($total7));
        echo"</td>";
        echo "</tr>";

        
        $counter = 'B';
        foreach ($query_result->result() as $row) 
        {
            $name = $row->name;
            $ledger_id = $row->id;
            if($name == 'Deposit Linked Insurance Payment' || $name == 'Medical Reimbursement to Retired Employee' || $name == 'Travel to Hometown on Retirement' || $name == 'Contribution to New Pension Scheme')
            {
                echo "<tr class=\"tr-group\">";
                echo "<td colspan=\"6\" >".$counter.". ".$name."</td>";
                $ledger_bal = $CI->Ledger_model->get_ledger_balance1($ledger_id);
                echo"<td align=\"right\">";
                echo money_format('%!i', convert_cur($ledger_bal));
                echo"</td>";
                echo "</tr>";
                $counter++;
                $total = $total + $ledger_bal;
            }
            
        }
        $total = $total + $total7;
        echo "<tr class=\"tr-ledger\" style = font-weight:bold;>";
        echo "<td colspan=\"6\">Total (A+B+C+D+E)</td>";
        echo"<td align=\"right\">";
        echo money_format('%!i', convert_cur($total));
        echo"</td>";
        echo "</tr>";
    }

	function designated_fundA($code,$type,$database,$count)
	{
		$op_balance = 0;
		$total_op_bal = 0;
		$fund_total = 0;
		$total_income = 0;
		$total_intrest = 0;
		$total_saving = 0;
		$net_total = 0;
		
	
		$CI =& get_instance();
		$CI->load->model('newschedules_model');
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

		$CI->load->model('Group_model');
            	$CI->load->model('Ledger_model');

            	$id = $CI->Group_model->get_id('Designated-Earmarked/Endowment Funds');
            	$CI->db->select('name,id')->from('groups')->where('parent_id',$id);
            	$query = $CI->db->get();
            	$counter = $query->num_rows();
            	$q_result = $query->result();
            	$group_id = array();
		$total1 = array();
                $i = 0;

            	$x = 0;
		$n = 0;
		$name = array();
            	$name[0] = " Opening balance";
            	$name[1] = " Additions during the year";
            	$name[2] = " Income from investments made of the funds";
            	$name[3] = " Accrued Interest on investments/Advances";
            	$name[4] = " Interest on Savings Bank a/c";
            	$name[5] = " Other additions (Specify nature)";

		if($name[0]){
			echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo "a)&nbsp;" . $name[0];
                        echo "</td>";
		foreach($q_result as $row)
                {
                	$group_id = $row->id;
                	$group_name = $row->name;
			$value = $CI->newschedules_model->schedule2($group_id);
                        $opening_balance = $value[0];
			$op_bal_dc = $value[8];
			$total_op_bal = $total_op_bal + $opening_balance;
			echo "<td align=\"right\">" . convert_amount_dc($opening_balance) . "</td>";
		}
		echo "<td align=\"right\">";
        	echo "<strong>" . convert_amount_dc($total_op_bal) . "</strong>";
        	echo "</td>";
		echo "<td align=\"right\">";
        	echo "<strong>" . convert_amount_dc(0) . "</strong>";
        	echo "</td>"; 
		}//if
		 if($name[1]){
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo "b)&nbsp;" . $name[1];
                        echo "</td>";
                foreach($q_result as $row)
                {
                        $group_id = $row->id;
                        $group_name = $row->name;
                        $value = $CI->newschedules_model->schedule2($group_id);
			$fund_addition = $value[1];
			$fund_total = $fund_total + $fund_addition;
                        echo "<td align=\"right\">" . convert_amount_dc(+$fund_addition) . "</td>";
                }
		//echo "fund_add======>$fund_addition=======fund_total=========>$fund_total";
		echo "<td align=\"right\">";
        	echo "<strong>" . convert_amount_dc(+$fund_total) . "</strong>";
       	 	echo "</td>";   
        	echo "<td align=\"right\">";
        	echo "<strong>" . convert_amount_dc(0) . "</strong>";
        	echo "</td>"; 
                }//if

		 if($name[2]){
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo "c)&nbsp;" . $name[2];
                        echo "</td>";
                foreach($q_result as $row)
                {
                        $group_id = $row->id;
                        $group_name = $row->name;
                        $value = $CI->newschedules_model->schedule2($group_id);
                        $fund_investment_income = $value[2];
                        $total_income = $total_income + $fund_investment_income;
                        echo "<td align=\"right\">" . convert_amount_dc(+$fund_investment_income) . "</td>";
                }
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(+$total_income) . "</strong>";
                echo "</td>";
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
                }//if

		if($name[3]){
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo "d)&nbsp;" . $name[3];
                        echo "</td>";
                foreach($q_result as $row)
                {
                        $group_id = $row->id;
                        $group_name = $row->name;
                        $value = $CI->newschedules_model->schedule2($group_id);
                        $accru_intrest = $value[3];
                        $total_intrest = $total_intrest + $accru_intrest;
                        echo "<td align=\"right\">" . convert_amount_dc(+$accru_intrest) . "</td>";
                }
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(+$total_intrest) . "</strong>";
                echo "</td>";
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
                }//if

		if($name[4]){
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo "e)&nbsp;" . $name[4];
                        echo "</td>";
                foreach($q_result as $row)
                {
                        $group_id = $row->id;
                        $group_name = $row->name;
                        $value = $CI->newschedules_model->schedule2($group_id);
                        $earned_intrest = $value[4];
                        $total_saving = $total_saving + $earned_intrest;
                        echo "<td align=\"right\">" . convert_amount_dc(+$earned_intrest) . "</td>";
                }
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(+$total_saving) . "</strong>";
                echo "</td>";
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
                }//if

		if($name[5]){
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo "f)&nbsp;" . $name[5];
                        echo "</td>";
                foreach($q_result as $row)
                {
                        $group_id = $row->id;
                        $group_name = $row->name;
                        $value = $CI->newschedules_model->schedule2($group_id);
                      //  $accru_intrest = $value[4];
                      //  $total_intrest = $total_intrest + $accru_intrest;
                        echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                }
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
                }//if
		
		echo "<tr>";
        	echo "<td class=\"bold\" align=\"center\">";
        	echo "TOTAL(A)";
        	echo "</td>";
		foreach($q_result as $row)
                {
                	$group_id = $row->id;
                        $group_name = $row->name;

			$value = $CI->newschedules_model->schedule2($group_id);
			$opening_balance = $value[0];
			$fund_addition = $value[1];
			$fund_investment_income = $value[2];
			$accru_intrest = $value[3];
			$earned_intrest = $value[4];

			//$other_addition = 0;
			$total = ($opening_balance + $fund_addition + $fund_investment_income + $accru_intrest + $earned_intrest); 
			echo "<td align=\"right\">";
		        echo "<strong>" . convert_amount_dc($total) . "</strong>";
        		echo "</td>";
			$net_total = $net_total + $total;
			

		}//foreach
		$this->net_total1 = $net_total;
	}

	function designated_fundB($code,$type,$database,$count)
	{
		$total_capital_exp = 0;
		$total_revenue_exp = 0;
		$net_total1 = 0;
		$net_total3 = 0;

		$CI =& get_instance();
                $CI->load->model('newschedules_model');
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

                $CI->load->model('Group_model');
                $CI->load->model('Ledger_model');

                $id = $CI->Group_model->get_id('Designated-Earmarked/Endowment Funds');
                $CI->db->select('name,id')->from('groups')->where('parent_id',$id);
                $query = $CI->db->get();
                $counter = $query->num_rows();
                $q_result = $query->result();
                $group_id = array();
                $x = 0;

		$n = 6;
		$name[6] = " Capital Expenditure";
                $name[7] = " Revenue Expenditure";

		if($name[6]){
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo "i)&nbsp;" . $name[6];
                        echo "</td>";
                foreach($q_result as $row)
                {
                        $group_id = $row->id;
                        $group_name = $row->name;
                        $value = $CI->newschedules_model->schedule2($group_id);
                        $capital_exp = $value[6];
                        $total_capital_exp = $total_capital_exp + $capital_exp;
                        echo "<td align=\"right\">" . convert_amount_dc(+$capital_exp) . "</td>";
                }
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(+$total_capital_exp) . "</strong>";
                echo "</td>";
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
                }//if

		if($name[7]){
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo "ii)&nbsp;" . $name[7];
                        echo "</td>";
                foreach($q_result as $row)
                {
                        $group_id = $row->id;
                        $group_name = $row->name;
                        $value = $CI->newschedules_model->schedule2($group_id);
                        $revenue_exp = $value[7];
                        $total_revenue_exp = $total_revenue_exp + $revenue_exp;
                        echo "<td align=\"right\">" . convert_amount_dc(+$revenue_exp) . "</td>";
                }
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(+$total_revenue_exp) . "</strong>";
                echo "</td>";
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
                }//if

		echo "<tr>";
                echo "<td class=\"bold\" align=\"center\">";
                echo "TOTAL(B)";
                echo "</td>";
                foreach($q_result as $row)
                {
                        $group_id = $row->id;
                        $group_name = $row->name;

                        $value = $CI->newschedules_model->schedule2($group_id);
			$capital_exp = $value[6];
			$revenue_exp = $value[7];
                        //$other_addition = 0;
                        $totalb = ($capital_exp + $revenue_exp);
                        echo "<td align=\"right\">";
                        echo "<strong>" . convert_amount_dc($totalb) . "</strong>";
                        echo "</td>";
                        $net_total1 = $net_total1 + $totalb;


                }//foreach
	
		echo "<td align=\"right\">";
        	echo "<strong>" . convert_amount_dc($net_total1) . "</strong>";
        	echo "</td>";

        	echo "<td align=\"right\">";
        	echo "<strong>" . convert_amount_dc(0) . "</strong>";
        	echo "</td>";
        	echo "</tr>";	

		echo "<tr>";
        	echo "<td class=\"bold\" align=\"center\">";
        	echo "Closing balance at the year end (A - B)";
        	echo "</td>";
		foreach($q_result as $row)
                {
                        $group_id = $row->id;
                        $group_name = $row->name;

                        $value = $CI->newschedules_model->schedule2($group_id);
                        $opening_balance = $value[0];
                        //$opening_data = $op_balance[0];
                        $fund_addition = $value[1];
                        $fund_investment_income = $value[2];
                        $accru_intrest = $value[3];
                        $earned_intrest = $value[4];
			$capital_exp = $value[6];
                        $revenue_exp = $value[7];

                        //$other_addition = 0;
                        $totalA = ($opening_balance + $fund_addition + $fund_investment_income + $accru_intrest + $earned_intrest);
			$totalB = ($capital_exp + $revenue_exp);
			
			$net_closing = $totalA - $totalB;
			echo "<td align=\"right\">";
        		echo "<strong>" . convert_amount_dc($net_closing) . "</strong>";
        		echo "</td>";
			$net_total3 = $net_total3 + $net_closing;

			}//foreach

                //$this->net_total2 = $net_total1;
		$this->net_total3 = $net_total3;

	}//f 

}

