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
			echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo "&nbsp;" .  $name;
                        echo "</td>";
                        echo "<td class=\"td-group\">";
                        $counter++;
                        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" . anchor_popup('report/new_schedule/' . $code . '/' . $counter, $counter, array('title' => $name, 'style' => 'color:#000000'));
                        echo "</td>";
                        if($name!= 'Corpus/Capital Funds')
                        {
                        echo "<td align=\"right\" colspan=\"3\">" . convert_amount_dc($liability_total) . "</td>";
                        echo "<td align=\"right\" colspan=\"3\">" . convert_amount_dc(0) . "</td>";
                        }else{
                        echo "<td align=\"right\" colspan=\"3\">" . convert_amount_dc($liability_total1) . "</td>";
                        echo "<td align=\"right\" colspan=\"3\">" . convert_amount_dc(0) . "</td>";
                        }
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
                                echo "<tr class=\"tr-group\">";
                                echo "<td class=\"td-group\">";
                                echo "&nbsp;" .  $name;
                                echo "</td>";
                                echo "<td class=\"td-group\">";
                                $counter++;
                                echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" . anchor_popup('report/new_schedule/' . $code . '/' . $counter, $counter, array('title' => $name, 'style' => 'color:#000000'));
                                echo "</td>";
                                echo "<td align=\"right\" colspan=\"3\">" . convert_amount_dc($asset_total) . "</td>";
                                echo "<td align=\"right\" colspan=\"3\">" . convert_amount_dc(0) . "</td>";
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
                		echo "<tr class=\"tr-ledger\">";
                        	echo "<td class=\"td-ledger\">";
                        	echo "&nbsp;" .  $group_name;
                        	echo "</td>";
                      		echo "<td></td>";
                        	echo "<td colspan=\"3\"></td>";
                        	echo "<td colspan=\"3\"></td>";
                		}
               			if(($name!= 'Fixed Assets') && ($name!= 'Current Assets') && ($name!= 'Loans Advances and Deposits'))
                		{
                                if($group_name == 'Corpus Fund Investments')
                                {
                                	$group_name = 'Investments Others';
                                        $counter = 6;
                                        echo "<tr class=\"tr-group\">";
                                        echo "<td class=\"td-group\">";
                                        echo "&nbsp;" .  $group_name;
                                        echo "</td>";
                                        echo "<td>";
                                        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" . anchor_popup('report/new_schedule/' . $group_code . '/' . $counter, $counter, array('title' => $group_name, 'style' => 'color:#000000'));
                                        echo "</td>";
                                        echo "<td align=\"right\" colspan=\"3\">" . convert_amount_dc(0) . "</td>";
                                        echo "<td align=\"right\" colspan=\"3\">" . convert_amount_dc(0) . "</td>";
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
                        echo "&nbsp;" . anchor_popup('report/new_sub_schedule/' . $row->id . '/' . $row->name, $row->name, array('title' => $row->name, 'style' => 'color:#000000'));
                        elseif($group_name == 'Corpus Fund Investments')
			echo "&nbsp;" . anchor_popup('report/new_sub_schedule/' . $row->id . '/' . $row->name, $row->name, array('title' => $row->name, 'style' => 'color:#000000'));
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
                $group_result = $group_detail->result();
                foreach($group_result as $row)
                {
                        $g_id =$row->id;
                        $CI->db->select('name,code,id')->from('groups')->where('parent_id',$g_id);
                        $child_detail = $CI->db->get();
			 $child_result = $child_detail->result();
                        foreach($child_result as $row1)
                        {
                        $group_id =$row1->id;
                        $group_name = $row1->name;
                        $CI->load->model('newschedules_model');
                        $result = $CI->newschedules_model->fixed_asset($group_id,$count);
                        $value = explode('#', $result);
                        $opening_bal = $value[0];
                        $sum = $sum + $opening_bal;
                        $dr_total = $value[1];
                        $sum1 = $sum1 + $dr_total;
                        $cr_total = $value[2];
                        $sum2 = $sum2 + $cr_total;
                        $closing_bal = $value[3];
                        $sum3 = $sum3 + $closing_bal;
                        $net_total = $value[4];
                        //echo "net=========$net_total";
                        $sum7 = $sum7 + $net_total;
                        //echo "sum7=======$sum7";
                        $current_year_value = $net_total;
                        $result1 = $CI->newschedules_model->get_dep_value($group_id);
                        $value1 = explode('#', $result1);
                        $dep_op_balance = $value1[0];
                        $sum4 = $sum4 + $dep_op_balance;
                        $current_dep_amount = $value1[1];
                        $sum5 = $sum5 + $current_dep_amount;
                        $total_depreciation = $value1[2];
                        $sum6 = $sum6 + $total_depreciation;

                        if(($group_id!= 148) && ($group_id!= 149))
                        {
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo $counter;
                        $counter++;
                        echo "</td>";
			 echo "<td>";
                        //if($group_name == 'Others Fixed Assets')
                        //echo "&nbsp;&nbsp;&nbsp;&nbsp;" . anchor_popup('report/new_sub_schedule/' . $row1->id . '/' . $row1->name, $row1->name, array('title' => $row1->name, 'style' => 'color:#000000'));
                        //elseif($group_name == 'Land')
                        //echo "&nbsp;&nbsp;&nbsp;&nbsp;" . anchor_popup('report/new_sub_schedule/' . $row1->id . '/' . $row1->name, $row1->name, array('title' => $row1->name, 'style' => 'color:#000000'));
                        //else
                        echo "&nbsp;" .  $group_name;
                        echo "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(+$opening_bal) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(+$dr_total) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(-$cr_total) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(+$closing_bal) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(+$dep_op_balance) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(+$current_dep_amount) . "</td>";
                        echo "<td align=\"right\" width=\"9%\">";
                        echo "0.00";
                        echo "</td>";
                        echo "<td align=\"center\">";
                        echo money_format('%!i', convert_cur($total_depreciation));
                        echo "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(+$current_year_value) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                        }//ifcondition
                echo "</tr>";
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
                $counter = 18;
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
                $result = $CI->newschedules_model->fixed_asset($group_id, $count);
                $value = explode('#', $result);
                $opening_bal = $value[0];
                $sum = $sum + $opening_bal;
                $dr_total = $value[1];
		 $sum1 = $sum1 + $dr_total;
                $cr_total = $value[2];
                $sum2 = $sum2 + $cr_total;
                $closing_bal = $value[3];
                $sum3 = $sum3 + $closing_bal;
                //$dep_op_balance = $value[4];
                //$sum4 = $sum4 + $dep_op_balance;
                //$current_dep_amount = $value[5];
                //$sum5 = $sum5 + $current_dep_amount;
                //$total_depreciation = $value[6];
                $net_total = $value[4];
                $sum7 = $sum7 + $net_total;

                $current_year_value = $net_total;
                $result1 = $CI->newschedules_model->get_dep_value($group_id);
                $value1 = explode('#', $result1);
                $dep_op_balance = $value1[0];
                $sum4 = $sum4 + $dep_op_balance;
                $current_dep_amount = $value1[1];
                $sum5 = $sum5 + $current_dep_amount;
                $total_depreciation = $value1[2];
                $sum6 = $sum6 + $total_depreciation;


                echo "<tr class=\"tr-group\">";
                echo "<td class=\"td-group\" width=\"40\">";
                echo $counter;
                echo "</td>";
                echo "<td class=\"td-group\" width=\"225\">";
                //if($group_name == 'Capital Work-In-Progress')
                //echo "&nbsp;&nbsp;&nbsp;&nbsp;" . anchor_popup('report/new_sub_schedule/' . $group_id . '/' .  $group_name, $group_name, array('title' => $group_name, 'style' => 'color:#000000'));
                //else
                echo "&nbsp;" .  $group_name;
                echo '<b>(B)</b>';
                echo "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(+$opening_bal) . "</td>";
			 echo "<td align=\"right\">" . convert_amount_dc(+$dr_total) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(-$cr_total) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(+$closing_bal) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(+$dep_op_balance) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(+$current_dep_amount) . "</td>";
                        echo "<td align=\"right\" width=\"9%\">";
                        echo "0.00";
                        echo "</td>";
                        echo "<td align=\"center\">";
                        echo money_format('%!i', convert_cur($total_depreciation));
                        echo "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(+$current_year_value) . "</td>";
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
                $ledger_result = $ledger_detail->result();
                foreach($ledger_result as $row)
                {
                $ledg_id =$row->id;
                $ledg_name = $row->name;
                $CI->load->model('newschedules_model');
                $result = $CI->newschedules_model->fixed_assetledg($ledg_id,$count);
                $value = explode('#', $result);
                $opening_bal = $value[0];
                $sum = $sum + $opening_bal;
                $dr_total = $value[1];
                $sum1 = $sum1 + $dr_total;
                $cr_total = $value[2];
                $sum2 = $sum2 + $cr_total;
                $closing_bal = $value[3];
                $sum3 = $sum3 + $closing_bal;
                $net_total = $value[4];
                $sum7 = $sum7 + $net_total;
                $current_year_value = $net_total;

                $result1 = $CI->newschedules_model->get_dep_value_ledg($ledg_id);
                $value1 = explode('#', $result1);
                $dep_op_balance = $value1[0];
		 $sum4 = $sum4 + $dep_op_balance;
                $current_dep_amount = $value1[1];
                $sum5 = $sum5 + $current_dep_amount;
                $total_depreciation = $value1[2];
                $sum6 = $sum6 + $total_depreciation;

                echo "<tr class=\"tr-group\" colspan=\"2\">";
                 echo "<td class=\"td-group\">";
                echo $counter;
                $counter++;
                echo "</td>";
                echo "<td class=\"td-group\" width=\"225\">";
                echo "&nbsp;" .  $ledg_name;
                echo "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(+$opening_bal) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(+$dr_total) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(-$cr_total) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(+$closing_bal) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(+$dep_op_balance) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(+$current_dep_amount) . "</td>";
                        echo "<td align=\"right\" width=\"9%\">";
                        echo "0.00";
                        echo "</td>";
                        echo "<td align=\"center\">";
                        echo money_format('%!i', convert_cur($total_depreciation));
                        echo "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(+$current_year_value) . "</td>";
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
/*	function Fixed_Sub_Schedule($code,$count)
	{
		$counter = 1;
		$CI =& get_instance();
                $CI->load->model('group_model');
                $id = $CI->group_model->get_group_id($code);
                $CI->db->select('name,code,id')->from('groups')->where('parent_id',$id);
                $group_detail = $CI->db->get();
                $group_result = $group_detail->result();
                foreach($group_result as $row)
                {
                        $group_id =$row->id;
                        $CI->db->select('name,code,id')->from('groups')->where('parent_id',$group_id);
                        $child_detail = $CI->db->get();
                        $child_result = $child_detail->result();
                        foreach($child_result as $row1)
                        {
                        $child_id =$row1->id;
                        $child_name = $row1->name;
                        if(($child_id!= 148) && ($child_id!= 149))
                        {
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo $counter;
                        $counter++;
                        echo "</td>";
			echo "<td class=\"td-group\" width=\"225\">";
                	echo "&nbsp;" .  $child_name;
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
		 	}//ifcondition
                	echo "</tr>";
                        }//childgroup
                }//foreach group

	} */
	
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
			//if($group_name == 'Recipts Against Sponsored Projects')
                        //echo "&nbsp;&nbsp;&nbsp;&nbsp;" . anchor_popup('report/new_sub_schedule/' . $row->id . '/' . $row->name, $row->name, array('title' => $row->name, 'style' => 'color:#000000'));
                        //elseif($group_name == 'UGC Sponsored Fellowship/Scholarships')
                        //echo "&nbsp;&nbsp;&nbsp;&nbsp;" . anchor_popup('report/new_sub_schedule/' . $row->id . '/' . $row->name, $row->name, array('title' => $row->name, 'style' => 'color:#000000'));  
			//elseif($group_name == 'Unutilized Grants')
                        //echo "&nbsp;&nbsp;&nbsp;&nbsp;" . anchor_popup('report/new_sub_schedule/' . $row->id . '/' . $row->name, $row->name, array('title' => $row->name, 'style' => 'color:#000000'));  
			//else
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

/*	function sub_schedule_3c()
	{
		$CI =& get_instance();
		echo "<tr>";
	        echo "<td><strong>A.  Plan grants: Government of India</strong><td>";
        	echo "</tr>";
		echo "<tr>";
                echo "<td align=\"right\">";
                echo "<strong> TOTAL(a)</strong>";
                echo "</td>";
                echo "<td colspan=\"2\" align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
      		echo "</tr>";
		echo "<tr>";
		echo "</tr>";
		echo "<tr>";
                echo "<td align=\"right\">";
                echo "<strong> TOTAL(b)</strong>";
                echo "</td>";
                echo "<td colspan=\"2\" align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
	        echo "</tr>";
		echo "<td align=\"right\">";
                echo "Unutilized carried forward(a-b)";
                echo "</td>";
                echo "<td colspan=\"2\" align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
                echo "</tr>";

		echo "<tr>";
                echo "<td><strong>B.  UGC grants: Plans</strong><td>";
                echo "</tr>";
                echo "<tr>";
                echo "<td align=\"right\">";
                echo "<strong> TOTAL(c)</strong>";
                echo "</td>";
                echo "<td colspan=\"2\" align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
                echo "</tr>";
                echo "<tr>";
                echo "<td align=\"right\">";
                echo "<strong> TOTAL(d)</strong>";
                echo "</td>";
                echo "<td colspan=\"2\" align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
		echo "<td colspan=\"2\" align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
		echo "</tr>";
		echo "<tr>";
		echo "<td align=\"right\">";
                echo "Unutilized carried forward(c-d)";
                echo "</td>";
                echo "<td colspan=\"2\" align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
                echo "</tr>";

		echo "<tr>";
                echo "<td><strong>C.  UGC Grants Non Plan</strong><td>";
                echo "</tr>";
                echo "<tr>";
                echo "<td align=\"right\">";
                echo "<strong> TOTAL(e)</strong>";
                echo "</td>";
                echo "<td colspan=\"2\" align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
                echo "</tr>";
                echo "<tr>";
                echo "</tr>";
                echo "<tr>";
                echo "<td align=\"right\">";
                echo "<strong> TOTAL(f)</strong>";
                echo "</td>";
		echo "<tr>";
                echo "<td align=\"right\">";
                echo "Unutilized carried forward(e-f)";
                echo "</td>";
                echo "<td colspan=\"2\" align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
                echo "</tr>";
		
		echo "<tr>";
                echo "<td><strong>D.  Grants from State Govt.</strong><td>";
                echo "</tr>";
                echo "<tr>";
                echo "<td align=\"right\">";
                echo "<strong> TOTAL(g)</strong>";
                echo "</td>";
                echo "<td colspan=\"2\" align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
                echo "</tr>";
                echo "<tr>";
                echo "</tr>";
                echo "<tr>";
                echo "<td align=\"right\">";
                echo "<strong> TOTAL(h)</strong>";
                echo "</td>";
                echo "<tr>";
                echo "<td align=\"right\">";
                echo "Unutilized carried forward(g-h)";
                echo "</td>";
                echo "<td colspan=\"2\" align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
		echo "</tr>";
		
		echo "<tr>";
		echo "<td align=\"right\">";
                echo "*Grand Total (A+B+C+D)";
                echo "</td>";
                echo "<td colspan=\"2\" align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
                echo "</tr>";



	}
		
	function subschedule_3a()
	{
		$counter = 1;
		$CI =& get_instance();
		$CI->db->select('id,code,name')->from('groups')->where('parent_id', 93);
		$main_result = $CI->db->get();
                $main_q = $main_result->result();
                foreach($main_q as $row)
                {
                	$group_id = $row->id;
                	$group_name = $row->name;
                	echo "<tr class=\"tr-group\">";
                	echo "<td class=\"td-group\" align=\"center\">";
                	echo $counter;
                	$counter++;
			echo "</td>";
			echo "<td align=\"center\">";
                	echo "&nbsp;&nbsp;" . $group_name;
                	echo "</td>";

		}//foreach


	}    */
	
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
                if($counter == 10 || $counter == 12)
                echo $counter;
                else
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
                                    $plan_cr_total = $paln_cr_total + $sum;
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
                                    $plan_cr_total = $paln_cr_total + $sum;
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

                if($name ==  "Income from Land and Building")
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
                        echo "&nbsp;&nbsp;&nbsp;&nbsp&nbsp&nbsp" . $num.". ". $ledg_name;
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
}

