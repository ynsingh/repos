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
                	echo "<tr class=\"tr-group\">";
                	echo "<td class=\"td-group\">";
               	 	echo "&nbsp;" .  $ledg_name;
                	echo "</td>";
			$CI->load->model('investment_model');
	                $result = $CI->investment_model->newschedule1($ledg_id);
        	        $value = explode('#',$result);
                	$dr_total1 = $value[0];
                	$sum = $sum+$dr_total1;
                	$cr_total1 = $value[1];
                	$sum1 = $sum1+$cr_total1;
                	echo "<td align=\"right\">" . convert_amount_dc(+$dr_total1) . "</td>";
			echo "<td align=\"right\">" . convert_amount_dc(-$cr_total1) . "</td>";
			echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";

		}//foreach
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



/*	function FixedAsset_A($code,$count)
	{
		$sum = 0;
                $sum1 = 0;
                $counter = 1;
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
                        echo "<td>";

			if($child_name == 'Others Fixed Assets')
                        echo "&nbsp;&nbsp;&nbsp;&nbsp;" . anchor_popup('report/new_sub_schedule/' . $row1->id . '/' . $row1->name, $row1->name, array('title' => $row1->name, 'style' => 'color:#000000'));
                        else
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
	}
	
	function FixedAsset_B($code,$count)
	{
		$sum = 0;
                $sum1 = 0;
                $counter = 18;
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
                echo "<tr class=\"tr-group\">";
                echo "<td class=\"td-group\" width=\"40\">";
                echo $counter;
                echo "</td>";
                echo "<td class=\"td-group\" width=\"225\">";
		if($group_name == 'Capital Work-In-Progress')
                echo "&nbsp;&nbsp;&nbsp;&nbsp;" . anchor_popup('report/new_sub_schedule/' . $group_id . '/' . $group_name, $group_name, array('title' => $group_name, 'style' => 'color:#000000'));
                else
                echo "&nbsp;" .  $group_name;
		echo '<b>(B)</b>';
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
	} 

	function FixedAsset_C($code,$count)
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
	
	function Fixed_Sub_Schedule($code,$count)
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
                        echo "&nbsp;&nbsp;&nbsp;&nbsp;" . anchor_popup('report/new_sub_schedule/' . $row->id . '/' . $row->name, $row->name, array('title' => $row->name, 'style' => 'color:#000000'));
                        elseif($group_name == 'UGC Sponsored Fellowship/Scholarships')
                        echo "&nbsp;&nbsp;&nbsp;&nbsp;" . anchor_popup('report/new_sub_schedule/' . $row->id . '/' . $row->name, $row->name, array('title' => $row->name, 'style' => 'color:#000000'));  
			elseif($group_name == 'Unutilized Grants')
                        echo "&nbsp;&nbsp;&nbsp;&nbsp;" . anchor_popup('report/new_sub_schedule/' . $row->id . '/' . $row->name, $row->name, array('title' => $row->name, 'style' => 'color:#000000'));  
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

	function sub_schedule_3c()
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

}
