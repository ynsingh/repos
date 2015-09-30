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
	$this->load->model('Depreciation_model');
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
	echo "<table border=0 cellpadding=6 class=\"simple-table balance-sheet-table\" width=\"80%\">";
	echo "<thead><tr><th>Asset Name</th><th>Date of Purchase</th><th>Total Cost (Rs/-)</th><th>Dep.Amount (Rs/-)</th><th>Current Value (Rs/-)</th><th>Write Off Status</th></tr></thead>";
	echo "<tbody>";
	$CI =& get_instance();
	$amount_total=0;
	$counter=0;
	$count=0;
	$amount=0;
        $net_amount=0;
        $net_dep_amount=0;
        $net_dep_amount1=0;
        $tot_dep_amount=0;
	$i=1;
	$check = 1;
	$cost=0;
	$val=explode("#",$field);
	$b = trim($text);
	$today_date=date("Y-m-d");
	$fy_end_date=date_mysql_to_php_display($this->config->item('account_fy_end'));
        $d=strtotime($fy_end_date);
        $fy_end_date= date("Y-m-d", $d);

	$this->db->from('old_asset_register');
        $asset_register=$this->db->get();
	if($asset_register->num_rows() != 0){
	echo "<tr class=\"tr-balance\"><td colspan=\"4\"><b>Previous Year Assets (BGAS)</b></td><td></td></tr>";
	}
	$check_asset_register = $CI->Depreciation_model->is_asset_register_exist();
        if($check_asset_register == '1' && $today_date != $fy_end_date){//if2
		if($search == ''){
			$this->db->from('old_asset_register');
                       	$asset_register=$this->db->get();
		}else{
			if($search == "IRD_WEF_Date#date_of_purchase"){
			$exp_date=explode("/",$b);
			$b=$exp_date[2]."-".$exp_date[1]."-".$exp_date[0]." "."00:00:00";
		}
			$this->db->from('old_asset_register')->where($val[1], '%' . $b . '%');
                        $asset_register=$this->db->get();
			if($asset_register->num_rows() < 0){
				$this->messages->add('Entry not found.', 'error');
                                redirect('report/depreciation');
			}	
		}
                	foreach ($asset_register->result() as $row){
				
				$led_code = $CI->Depreciation_model->get_ledger_parent($row->asset_name);
                                $master_group_code = $CI->Depreciation_model->is_asset_in_depreciation_master_table($led_code);
				if($master_group_code == NULL){
                                        $this->db->select('name');
                                        $this->db->from('groups')->where('code =', $led_code);
                                        $group_result = $this->db->get();
                                        foreach ($group_result->result() as $row1){
                                                $group_name=$row1->name;
                                        }
                                        $master_group_code = $CI->Depreciation_model->is_asset_in_depreciation_master_table1($group_name);
                                }

                                $exp_dep_percentage=explode("#",$master_group_code);
				$date = date_mysql_to_php($row->date_of_purchase);
				$today_date1=date("Y/m/d");
				$date1=date_create($today_date1);
                                $date2=date_create($row->date_of_purchase);
                                $diff=date_diff($date1,$date2);
                                $no_of_days=$diff->format("%a days");
                                //how many years till now
                                $years=$no_of_days/365;
                                $check_year=is_int($years);//check year is in integer
                                $get_asset_used_years=explode(".", $years);
                                $days=$get_asset_used_years[0]*365;
                                $tot_day=$no_of_days-$days;
				///////////////////////////////////////////////////////////////////////////////////
				 for($i=0; $i<=$get_asset_used_years[0]; $i++){
                                        if($i == 0){
                                                $depepreciation_on_day=$row->cost*($tot_day/365)*($exp_dep_percentage[0]/100);
                                                $net_amount= $depepreciation_on_day;

                                        }elseif($i == 1 ){
						$val=($row->cost*$exp_dep_percentage[0])/100;
                                                $amount=$row->cost-$val;
                                                $depepreciation_on_day=$row->cost*($tot_day/365)*($exp_dep_percentage[0]/100);
                                                $net_amount= $depepreciation_on_day+$amount;

					}else{
						$val=($amount*$exp_dep_percentage[0])/100;
                                                $amount=$amount-$val;
                                                $depepreciation_on_day=$row->cost*($tot_day/365)*($exp_dep_percentage[0]/100);
                                                $net_amount= $depepreciation_on_day+$amount;
					}
				}
				/////////////////////////////////////////////////////////////////////////////////
				//if Duplicate entry.............
                                $this->db->from('old_asset_register')->where('asset_name',$row->asset_name);
                                $asset_register1=$this->db->get();
                                if($asset_register1->num_rows() > 1){
                                        $this->db->select_sum('cost');
                                        $this->db->from('old_asset_register')->where('asset_name',$row->asset_name);
                                        $asset_register2=$this->db->get();
                                        foreach ($asset_register2->result() as $row2){
                                        }
					$count++;
                                }


                                if($get_asset_used_years[0] == $exp_dep_percentage[1]){

                                        $this->db->trans_start();
                                        $update_data = array(
                                        	'asset_status'=>'1',
                                                );
                                                if ( ! $this->db->where('id', $row->id)->update('old_asset_register', $update_data))
                                                {
                                                        $this->db->trans_rollback();
                                                        $this->messages->add('Error updating Entry account.', 'error');
                                                }else {
                                                                $this->db->trans_complete();
                                                }

                                 }
                                 echo"<tr>";
					if($count == 1 || $count == 0){
                                        echo "<td>". $row->asset_name ."</td>";
                                        echo "<td>" . $date . "</td>";
					if($asset_register1->num_rows() > 1){
                                        echo "<td>".$row2->cost."</td>";
					}else{
					echo "<td>".$row->cost."</td>";

					}
					$trim_dep_value = substr($net_amount, 0 ,8);
					$curr_value=$row->cost-$trim_dep_value ;
					// Depreciation percentage is null show privious year deprection and add null 0 depreciation this year.
					if($exp_dep_percentage[0] == 0){
						$trim_dep_value=$row->depreciated_value;
						$curr_value=$row->current_value;
					}
                                        echo "<td>" . "$trim_dep_value" . "</td>";
                                        echo "<td>" . "$curr_value"  . "</td>";
					if($row->asset_status == '1'){
						echo "<td>";
						echo"Write Off Item";
						echo "</td>";
					}else{
						echo "<td>";
						echo"In Use";
						echo "</td>";

					}
					}
					if($count > 1 ){
						$count=0;
					}
                        }
		}//if2
			
		$this->db->from('new_asset_register');
                $asset_register=$this->db->get();
		if($asset_register->num_rows() != 0){

			echo "<tr class=\"tr-balance\"><td colspan=\"4\"><b>Current Year Assets (BGAS)</b></td><td></td></tr>";
                	echo"<tr>";

		}
		$this->db->from('new_asset_register');
                $asset_register=$this->db->get();
		if($search == ''){
			$this->db->from('new_asset_register');
                	$asset_register=$this->db->get();
                	}else{
				if($search == "IRD_WEF_Date#date_of_purchase"){
                	}
                        	$this->db->from('new_asset_register')->where($val[1], '%' . $b . '%');
                        	$asset_register=$this->db->get();
                }

		foreach ($asset_register->result() as $row){

			$narration=$row->narration;
                        $narr=explode("@",$narration);
                        $date = date_mysql_to_php($row->date_of_purchase); 
			$today_date1=date("Y/m/d");
			//If today date is 31-march then calculate depreciation.
                        $exp_today_date=explode("-",$today_date);
                        $fy_start_date = date_mysql_to_php($this->config->item('account_fy_start'));
                        $fy_st_date=explode("/",$fy_start_date);
                        $fy_year=$fy_st_date[2].$fy_st_date[2]+1;
                        $next_year=$fy_st_date[2]+1;

                        $date1=date_create($today_date1);
                        $date2=date_create($row->date_of_purchase);
                        $diff=date_diff($date1,$date2);
                        $no_of_days=$diff->format("%a days");
                        //how many years till now
                        $years=$no_of_days/365;
                        $check_year=is_int($years);//check year is in integer
                        $get_asset_used_years=explode(".", $years);
                        $days=$get_asset_used_years[0]*365;
                        $tot_day=$no_of_days-$days;

			if($narr[0] != 'DepreciationRate'){
				$led_code = $CI->Depreciation_model->get_ledger_parent($row->asset_name);
				$master_group_code = $CI->Depreciation_model->is_asset_in_depreciation_master_table($led_code);
				if($master_group_code == NULL){
					$this->db->select('name');
					$this->db->from('groups')->where('code =', $led_code);
                			$group_result = $this->db->get();
					foreach ($group_result->result() as $row1){
						$group_name=$row1->name;
					}	
					$master_group_code = $CI->Depreciation_model->is_asset_in_depreciation_master_table1($group_name);
				}
                        	$exp_dep_percentage=explode("#",$master_group_code);   
				$value= $row->cost * $exp_dep_percentage[0]/(100*365);
				$today_date1=date("Y/m/d");
				$dep_value_till_now=$no_of_days*$value;
				$curr_value=$row->cost-$dep_value_till_now;

				if($get_asset_used_years[0] == 0){

					for($i=0; $i<=$get_asset_used_years[0]; $i++){
	
						if($i == 0){
							$val=$row->cost*($no_of_days/365)*($exp_dep_percentage[0]/100);
							$net_dep_amount= $val;
						}
					}
				}

							$curr_value=$row->cost-$net_dep_amount;
							$this->db->from('new_asset_register')->where('asset_name',$row->asset_name);
                                			$asset_register1=$this->db->get();
                                			if($asset_register1->num_rows() > 1){
                                        			$this->db->select_sum('cost');
                                        			$this->db->from('new_asset_register')->where('asset_name',$row->asset_name);
                                        			$asset_register2=$this->db->get();
                                        			foreach ($asset_register2->result() as $row3){
                                        			}
                                        			$counter++;
                                			}
							if($counter == 0 || $counter == 1){
								echo"<tr>";
								echo "<td>";echo anchor('report/duplicate_entry/'. $row->asset_name ,$row->asset_name  , array('title' => $row->asset_name , 'style' => 'color:#000000')) ;
                                        			echo "</td>";
                                        			echo "<td>" . $date . "</td>";
								if($asset_register1->num_rows() > 1){
									echo "<td>".$row3->cost."</td>";
								}else{
                                        				echo "<td>".$row->cost."</td>";
								}
									$trim_dep_value = substr($net_dep_amount,0, 6);
									$trim_curr_value=substr($curr_value, 0 , 8);
									if($val == "0"){
                                                                                $trim_dep_value=0;
                                                                                $trim_curr_value=0;
                                                                        }

                                        				echo "<td>" . $trim_dep_value. "</td>";
                                        				echo "<td>" .$trim_curr_value. "</td>";
									if($row->asset_status == '1'){
										echo "<td>";
										echo"Write Off Item";
										echo "</td>";
                                        				}else{
										echo "<td>";
										echo"In Use";
										echo "</td>";
                                        				}
							}
						//	$counter=$counter-1;
					 					//If today date is 31-march then calculate depreciation.
                                                				$exp_today_date=explode("-",$today_date);
                                                				$fy_start_date = date_mysql_to_php($this->config->item('account_fy_start'));
                                                				$fy_st_date=explode("/",$fy_start_date);
                                                				$fy_year=$fy_st_date[2].$fy_st_date[2]+1;
										$last_fy_year=$fy_st_date[2]+1;
										if($exp_today_date[1] == '31' && $last_fy_year == $exp_today_date[0] ){
                                                					$next_year=$fy_st_date[2]+1;
                                                					$this->db->trans_start();
                                                					$insert_data = array(
                                                						'depreciated_value'=>$curr_value,
                                                        					'current_value'=>$amount,
                                                        					'Financial_year'=>$fy_year,
                                                	 				);
                                                         				if (  ! $this->db->where('id', $row->id)->update('new_asset_register', $insert_data))
                                                         				{
                                                                				$this->db->trans_rollback();
                                                                				$this->messages->add('Depreciation Percentage value is already added. ', 'error');
                                                                				return;
                                                                			}else {
                                                                				$this->db->trans_complete();
                                                         				}
										}
                                                                			$count++;
				}else{
					 $rate=explode("%",$narr[1]);
                                         $life_time=explode(" ",$narr[2]);
					 $value= $row->cost * $rate[0]/(100*365);
                                         $dep_value_till_now=$no_of_days*$value;
					 $curr_value=$row->cost-$dep_value_till_now;
					 if($get_asset_used_years[0] == 0){

					 for($i=1; $i<=$get_asset_used_years[0]; $i++){
						
                                                if($i == 1){
                                                        $val=$row->cost*($tot_day/365)*($rate[0]/100);
							$net_dep_amount1= $net_dep_amount1+$val;
                                                }
							$curr_value=$row->cost-$net_dep_amount1;
					}//for
					}else{
						$net_dep_amount1=$row->cost*($tot_day/365)*($exp_dep_percentage[0]/100);

					}
				 	$this->db->from('new_asset_register')->where('asset_name',$row->asset_name);
                                        $asset_register1=$this->db->get();
                                        if($asset_register1->num_rows() > 1){
                                                $this->db->select_sum('cost');
                                                $this->db->from('new_asset_register')->where('asset_name',$row->asset_name);
                                                $asset_register2=$this->db->get();
                                                foreach ($asset_register2->result() as $row4){
                                                }
                                        $counter++;
                                        }

					echo"<tr>";
                                        	echo "<td>";echo anchor('report/duplicate_entry/'. $row->asset_name ,$row->asset_name  , array('title' => $row->asset_name , 'style' => 'color:#000000')) ;
                                        	echo "</td>";
                                        	echo "<td>" . $date . "</td>";
                                        	echo "<td>".$row->cost."</td>";
						$trim_dep_value1 = substr($net_dep_amount1,0, 6);
						$trim_curr_value=substr($net_dep_amount1,0, 6);
						echo "<td>" . $trim_dep_value1. "</td>";
                                        	echo "<td>" .$trim_curr_value . "</td>";
						if($row->asset_status == '1'){
						echo "<td>";
						echo"Write Off Item";
						echo "</td>";
                                        	}else{
						echo "<td>";
						echo"In Use";
						echo "</td>";
                                        	}

					}

			//	}
						//If today date is 31-march then calculate depreciation.
						$exp_today_date=explode("-",$today_date);
						$fy_start_date = date_mysql_to_php($this->config->item('account_fy_start'));
                                                $fy_st_date=explode("/",$fy_start_date);
                                                $fy_year=$fy_st_date[2].$fy_st_date[2]+1;
						$next_year=$fy_st_date[2]+1;
						$last_fy_year=$fy_st_date[2]+1;
                                                if($exp_today_date[1] == '31' && $last_fy_year == $exp_today_date[0] ){
                                                        $this->db->trans_start();
                                                         $insert_data = array(
                                                                'depreciated_value'=>$tot_dep_amount,
                                                                'current_value'=>$amount,
                                                                'Financial_year'=>$fy_year,
                                                         );
                                                         if (! $this->db->where('id', $row->id)->update('new_asset_register', $insert_data))
                                                         {
                                                         	$this->db->trans_rollback();
                                                                $this->messages->add('Depreciation Percentage value is already added. ', 'error');
                                                                return;
                                                                }else {
                                                                $this->db->trans_complete();
                                                         }
                                                                $count++;
						}


        	        }
		  if($search == ''){
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
			 		echo "<tr class=\"tr-balance\"><td colspan=\"4\"><b>Current Year Assets (PICO)</b></td><td></td></tr>";
					foreach($user_data->result() as $row1){	
						$ERPMIM_Item_Brief_Desc= $row1->ERPMIM_Item_Brief_Desc;
						$IRD_WEF_Date=$row1->IRD_WEF_Date;
						$IRD_Rate= $row1->IRD_Rate;
						$timestamp = strtotime($IRD_WEF_Date); 
						$new_date = date('d/m/Y', $timestamp);
						echo "<tr>";
						echo "<td>" . anchor('report/duplicate_entry/'. $ERPMIM_Item_Brief_Desc,$ERPMIM_Item_Brief_Desc , array('title' => $ERPMIM_Item_Brief_Desc , 'style' => 'color:#000000')) . "</td>";
						echo "<td>" . $new_date . "</td>";
						$value =$this->Ledger_model->get_asset_amount($row1->ERPMIM_Item_Brief_Desc);
						$my_values = explode('#',$value['key']);
						$my_values1=substr($my_values[1],0, 6);
						$my_values2=substr($my_values[2],0, 6);
						echo "<td>" . $my_values[0]  . "</td>";
						echo "<td>" . $my_values1  . "</td>";
						echo "<td>" . $my_values2  . "</td>";
						//echo "<td class=\"td-actions\">" . anchor('report/depreciation' . $row->id , "In Use", array('title' => 'Edit Group', 'class' => 'red-link'));
						echo "<td>";
						echo"In Use";
						echo "</td>";
						echo "</tr>";
						$i++;
					}
				}
					//$this->logndb->close();
					//}
		}else {
	/*		if($search == "Select")
			{
				$this->messages->add('Please select search type from drop down list.', 'error');
				redirect('report/depreciation');				
			}
			else {
				if($search == "ERPMIM_Item_Brief_Desc#name" || $search == "IRD_WEF_Date#update_date" || $search == "total_cost#amount"){
                                $val=explode("#",$field);
                                }
				$b = trim($text);
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
							 	 echo "<td>" . date_format($IRD_WEF_Date,"d/m/Y"). "</td>";
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
			}*/
		}
echo"</table>";				
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
