<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');
	if(!$print_preview) {
	echo form_open('report/depreciation');
	echo "<p>";
	echo form_label('Search By', 'search_by');
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	echo form_dropdown('search_by', $search_by, $search_by_active);
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	echo form_label('Text', 'text');
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	echo form_input($text);
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	echo form_submit('submit', 'Search');
	echo " ";
	echo "</p>";
	echo form_close();
	$field = $search . '      ' . 'LIKE';
	$text = $text['value'];
	}
?>
<?php
	$this->load->model('Ledger_model');
	$Dep_method=0;
	if ( ! $print_preview)
	{
		echo "<p>";
		echo "<span id=\"tooltip-target-1\">";
		$inputpreferences   = array( 'name'        => 'commentflag',
		                     'id'          => 'commentflag',
		                     'value'       => 'N',
				     'checked'     => $budget_over,
				                        
		                     );
		
		echo form_checkbox($inputpreferences) . " Straight Line Method"; 
		echo "</span>";
		echo "<span id=\"tooltip-content-1\">Calculate Depreciation With Staight Line Method.</span>";
		echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

		echo "<span id=\"tooltip-target-2\">";
		$inputpreferences1   = array( 'name'        => 'commentflag',
		                     'id'          => 'commentflag',
		                     'value'       => 'N',
		                     'checked'     => false,
		                     'disabled'    => 'disable',
		                    );
		echo form_checkbox($inputpreferences1 ) . " Double Decline Method";
		echo "</span>";
		echo "<span id=\"tooltip-content-2\">Calculate Depreciation With Double Decline Method.</span>";
		echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		echo "<span id=\"tooltip-target-3\">";
		 $inputpreferences2   = array( 'name'        => 'commentflag',
		                     'id'          => 'commentflag',
		                     'value'       => 'N',
		                     'checked'     => false,
		                     'disabled'    => 'disable',
		                    );
		echo form_checkbox($inputpreferences2) . " Sum-Of-Years-Digits Method";
		echo "</span>";
		echo "<span id=\"tooltip-content-3\">Calculate Depreciation With Sum-Of-Years-Digits Method.</span>";
		echo "</p>";
   	}
     	$gross_expense_total = 0;
	echo "<table border=0 cellpadding=5 class=\"simple-table balance-sheet-table\" width=\"80%\">";
	echo "<thead><tr><th>S.No</th><th>Asset Name</th><th>Date of Purchase</th><th>Total Cost</th><th>Dep.Amount</th><th>Current Value</th></tr></thead>";
	echo "<tbody>";
		$amount_total=0;
		$counter=0;
		$i=1;
		$check = 1;
		$account_code = $this->Budget_model->get_account_code('Fixed Assets');
		if($search == '') 
		{
			$this->db->select('name, id');
	                $this->db->from('ledgers')->where('code LIKE', $account_code.'%');
			$this->db->not_like('code', '20010101', 'after');	
			$this->db->group_by("name");

	                $gross_expense_list_q = $this->db->get();
	                foreach($gross_expense_list_q->result() as $row)
	                {
	                	$id=$row->name;
	                	$this->db->from('entry_items')->where('ledger_id', $row->id);
				$entry_details = $this->db->get();
				$this->db->select_sum('amount');
                                $this->db->from('entry_items')->where('ledger_id', $row->id);
                                $sum_amount = $this->db->get();
				foreach($sum_amount->result() as $row3)
                                {	$row3->amount;
				
				}
				if($entry_details->num_rows() > 1){
				}
				foreach($entry_details->result() as $row2)
	                	{
					$date=date_create($row2->update_date);
					
					if($entry_details->num_rows() > 1){
						$amount_total=$amount_total+$row2->amount;	
						$amount=$amount_total;
					}else{
				 		$amount=$row2->amount;
					}
					$this->db->from('entries')->where('id', $row2->entry_id);
                                	$entry_narration = $this->db->get();
					foreach($entry_narration->result() as $row4)
                                	{
						$narr=explode("@",$row4->narration);
						if($narr[0] == 'Depreciationrate'){
							
                                                $rate=explode("%",$narr[1]);
						 $life_time=explode(" ",$narr[2]);
						$value= $row3->amount * $rate[0]/(100*365);
                                		$tot_amount=$value * $life_time[0];
                                		$cur_value = $row3->amount - $tot_amount;
						}
                                	}


					if($counter!=1)
					{
				 	echo "<tr>";
	                                echo "<td>" . $i . '.' . "</td>";
	                                echo "<td>" . anchor('report/duplicate_entry/'. $row->name,$row->name , array('title' => $row->name . ' duplicate_entry', 'style' => 'color:#000000')) . "</td>";
	                                echo "<td>" . date_format($date,"Y-m-d") . "</td>";
	                                //$value =$this->Ledger_model->get_asset_amount($row1->ERPMIM_Item_Brief_Desc);
	                                //$my_values = explode('#',$value['key']);
					if($narr[0] != 'Depreciationrate'){
	                                echo "<td>".$row3->amount."</td>";
	                                echo "<td>" . 0  . "</td>";
	                                echo "<td>" . $row3->amount  . "</td>";
	                                echo "</tr>";
					}else{
					echo "<td>".$row3->amount."</td>";
                                        echo "<td>" . $tot_amount . "</td>";
                                        echo "<td>" . $cur_value  . "</td>";
					}
					}
					$counter++;
				 	$i++;	

			       }
			}
		        		//load database pico
                        		$logndb = $this->load->database('pico', TRUE);
                        		$this->logndb =& $logndb;
                        		$this->logndb->select('a.ERPMIM_ID, a.ERPMIM_Depreciation_Percentage, a.ERPMIM_Item_Brief_Desc, b.IRD_Rate, b.IR_Item_ID, b.IRD_WEF_Date');
	                        	$this->logndb->from('erpm_item_master a, erpm_item_rate b')->where('a.ERPMIM_ID  = b.IR_Item_ID ');
	                        	$this->logndb->group_by("ERPMIM_Item_Brief_Desc");
	                        	$user_data = $this->logndb->get();
					if($user_data == NULL){

		}
		else{
			foreach($user_data->result() as $row1)
                	{
				$ERPMIM_Item_Brief_Desc= $row1->ERPMIM_Item_Brief_Desc;
				$IRD_WEF_Date=$row1->IRD_WEF_Date;
				$IRD_Rate= $row1->IRD_Rate;
					
				echo "<tr>";
				echo "<td>" . $i . '.' . "</td>";
				echo "<td>" . anchor('report/duplicate_entry/'. $ERPMIM_Item_Brief_Desc,$ERPMIM_Item_Brief_Desc , array('title' => $ERPMIM_Item_Brief_Desc . ' duplicate_entry', 'style' => 'color:#000000')) . "</td>";
				echo "<td>" . $IRD_WEF_Date . "</td>";
				$value =$this->Ledger_model->get_asset_amount($row1->ERPMIM_Item_Brief_Desc);
				$my_values = explode('#',$value['key']);
				echo "<td>" . $my_values[0]  . "</td>";
				echo "<td>" . $my_values[1]  . "</td>";
				echo "<td>" . $my_values[2]  . "</td>";
				echo "</tr>";
				$i++;
			}
		}
				$this->logndb->close();
		//}
		}
		else {
			if($search == "Select")
			{
				$this->messages->add('Please select search type from drop down list.', 'error');
				redirect('report/depreciation');				
			}
			else {
				if($search == "ERPMIM_Item_Brief_Desc#name" || $search == "IRD_WEF_Date#update_date" || $search == "total_cost#amount"){
                                $val=explode("#",$field);
				/*$this->logndb->select('a.ERPMIM_ID, a.ERPMIM_Depreciation_Percentage, a.ERPMIM_Item_Brief_Desc, b.IRD_Rate, b.IR_Item_ID, b.IRD_WEF_Date');
                                $this->logndb->from('erpm_item_master a, erpm_item_rate b')->where('a.ERPMIM_ID  = b.IR_Item_ID ')->where($val[0], '%' . $b . '%');
                                $this->logndb->group_by("ERPMIM_Item_Brief_Desc");
                                $user_data = $this->logndb->get();
				print_r($user_data);*/

                                }
				$b = trim($text);
				if($search == "ERPMIM_Item_Brief_Desc#name"){
					$this->db->select('id, name');
		         		$this->db->from('ledgers')->where('code LIKE', $account_code.'%')->where($val[1], '%' . $b . '%');
					$this->db->not_like('code', '20010101', 'after');
				}else{
					$this->db->select('id, name');
                                	$this->db->from('ledgers')->where('code LIKE', $account_code.'%');
                                	$this->db->not_like('code', '20010101', 'after');
				}
		         	$gross_expense_list_q = $this->db->get();
		        	foreach($gross_expense_list_q->result() as $row)
		                {
					$user_data;
		                	$name=$row->name;
					if($search == "IRD_WEF_Date#update_date" || $search == "total_cost#amount"){
						$this->db->from('entry_items')->where('ledger_id', $row->id)->where($val[1], '%' . $b . '%');
                        			$entry_details = $this->db->get();
					}else{
						$this->db->from('entry_items')->where('ledger_id', $row->id);
                                        	$entry_details = $this->db->get();
					}
                        		foreach($entry_details->result() as $row2)
                        		{
                                 		$amount=$row2->amount;
                                 		echo "<tr>";
                                 		echo "<td>" . $i . '.' . "</td>";
                                 		echo "<td>" . anchor('report/duplicate_entry/'. $row->name,$row->name , array('title' => $row->name . ' duplicate_entry', 'style' => 'color:#000000')) . "</td>";
                                 		echo "<td>" . date_mysql_to_php_display($row2->update_date) . "</td>";
                                 		//$value =$this->Ledger_model->get_asset_amount($row1->ERPMIM_Item_Brief_Desc);
                                 		//$my_values = explode('#',$value['key']);
                                 		echo "<td>" . $row2->amount  . "</td>";
                                 		echo "<td>" . '0'  . "</td>";
                                 		echo "<td>" . $row2->amount  . "</td>";
                                 		echo "</tr>";
                               			 $i++;

		                        }

				}
					//load database pico
		                	$logndb = $this->load->database('pico', TRUE);
		                	$this->logndb =& $logndb;
					if($search != "total_cost" && $search != "dep_amount" && $search != "curr_value") {
					$val = explode('#',$field);
		                	$this->logndb->select('a.ERPMIM_ID, a.ERPMIM_Depreciation_Percentage, a.ERPMIM_Item_Brief_Desc, b.IRD_Rate, b.IR_Item_ID, b.IRD_WEF_Date');
		                	$this->logndb->from('erpm_item_master a, erpm_item_rate b')->where('a.ERPMIM_ID  = b.IR_Item_ID ')->where('a.'.$val[0],$b);
		                	//$this->logndb->group_by("ERPMIM_Item_Brief_Desc");
		                	$user_data = $this->logndb->get();
					}
					else {
				        	$this->logndb->select('a.ERPMIM_ID, a.ERPMIM_Depreciation_Percentage, a.ERPMIM_Item_Brief_Desc, b.IRD_Rate, b.IR_Item_ID, b.IRD_WEF_Date');
				        	$this->logndb->from('erpm_item_master a, erpm_item_rate b')->where('a.ERPMIM_ID  = b.IR_Item_ID ');
				        	$this->logndb->group_by("ERPMIM_Item_Brief_Desc");
				        	$user_data = $this->logndb->get();
					}
					if($user_data->num_rows() > 0){
						foreach($user_data->result() as $row1)
				                {
							$value =$this->Ledger_model->get_asset_amount($row1->ERPMIM_Item_Brief_Desc);
							$my_values = explode('#',$value['key']);
							$cost = $my_values[0];
							$depamt = $my_values[1];
							$curvalue = $my_values[2];
							$total_cost = '';
							$dep_amt = '';
							$curr_value = '';
							if($search != "total_cost") {
								$total_cost = $cost;
							}
							else {
								if(strstr($cost, $b)) {
									$total_cost = $cost;
								}
							}
							if($search != "dep_amount") {
								$dep_amt = $depamt;
							}
							else {
								if(strstr($depamt, $b)) {
									$dep_amt = $depamt;
								}
							}
							if($search != "curr_value") {
								$curr_value = $curvalue;
							}
							else {
								if(strstr($curvalue, $b)) {
									$curr_value = $curvalue;
								}
							}
							 $ERPMIM_Item_Brief_Desc= $row1->ERPMIM_Item_Brief_Desc;
							 $IRD_WEF_Date=$row1->IRD_WEF_Date;
							 $IRD_Rate= $row1->IRD_Rate;
							if($total_cost != NULL && $dep_amt != NULL && $curr_value != NULL && $ERPMIM_Item_Brief_Desc != NULL) {
								 echo "<tr>";
								 echo "<td>" . $i . '.' . "</td>";
								 echo "<td>" . anchor('report/duplicate_entry/'. $ERPMIM_Item_Brief_Desc,$ERPMIM_Item_Brief_Desc , array('title' => $ERPMIM_Item_Brief_Desc . ' duplicate_entry', 'style' => 'color:#000000')) . "</td>";
							 	 echo "<td>" . $IRD_WEF_Date . "</td>";
								 echo "<td>" . $total_cost  . "</td>";
								 echo "<td>" . $dep_amt  . "</td>";
								 echo "<td>" . $curr_value  . "</td>";
								 echo "</tr>";
								 $i++;
								$check++;
							}
						}
					}
					$this->logndb->close();
				//}
				if($check == "1" && $search == "ERPMIM_Item_Brief_Desc")
				{
					$this->messages->add($text . ' is not found.', 'error');
					redirect('report/depreciation');
				}
				if($check == "1" && $search == "IRD_WEF_Date")
				{
					$this->messages->add($text . ' is not found.', 'error');
					redirect('report/depreciation');
				}
			}
		}
					
/*	if($check == "1" && $search == "total_cost"){
		$this->messages->add($text . ' is not found.', 'error');
		redirect('report/depreciation');				
	}
	if($check == "1" && $search == "dep_amount"){
		$this->messages->add($text . ' is not found.', 'error');
		redirect('report/depreciation');				
	}
	if($check == "1" && $search == "curr_value"){
		$this->messages->add($text . ' is not found.', 'error');
		redirect('report/depreciation');				
	}*/
	echo "</tbody>";
        echo "</table>";
?>
