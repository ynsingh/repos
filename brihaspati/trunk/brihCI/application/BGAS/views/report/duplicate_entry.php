<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');


 echo "<table border=0 cellpadding=5 class=\"simple-table balance-sheet-table\" width=\"80%\">";
 echo "<thead><tr><th>Asset Name</th><th>Date of Purchase</th><th>Cost</th><th>Dep.Amount</th><th>Current Value</th><th>Write Off Status</th><th>Sanction Type</th><th>Fund/Project Name</th></tr></thead>";
        echo "<tbody>";
$this->load->model('Depreciation_model');
$i=1;
$sum=0;
@$val=$pico;
$new_asset_register='';
$old_asset_register='';
$net_amount=0;
$fund_name='';
$CI =& get_instance();
		foreach ($detail->result()  as $row)
		{			
			if($val != 1){ 
				$ERPMIM_Item_Brief_Desc= $row->ERPMIM_Item_Brief_Desc;
	                        $ERPMIM_Depreciation_Percentage=$row->ERPMIM_Depreciation_Percentage;
	                        $IRD_WEF_Date=$row->IRD_WEF_Date;
	                        $IRD_Rate= $row->IRD_Rate;
	                        $date2=Date("d F Y");
	                        $date3=date_create(" $IRD_WEF_Date");
	                        $date4=date_create("$date2");
	                        $diff=date_diff($date3,$date4);
	                        $day = $diff->format("%R%a days");
	                        $value= $IRD_Rate * $ERPMIM_Depreciation_Percentage/(100*365);
	                        $tot_amount=$value * $day;
	                        $cur_value = $IRD_Rate - $tot_amount;
	                        echo "<tr>";
	                        	echo "<td>" . $i . '.' . "</td>";
					echo "<td>" . $ERPMIM_Item_Brief_Desc . "</td>";
		                        echo "<td>" .  $IRD_WEF_Date . "</td>";
				if($cur_value <= 0)
				{
					echo "<td>" . $IRD_Rate  . "</td>";
		                        echo "<td>" . $IRD_Rate  . "</td>";
		                        echo "<td>" .  0  . "</td>";
				}
				else
				{
		                        echo "<td>" . $IRD_Rate  . "</td>";
		                        echo "<td>" . $tot_amount  . "</td>";
		                        echo "<td>" .  $cur_value  . "</td>";
		                        echo "</tr>";
					$sum+=$tot_amount;
					$i++;
				}
			}else{
				$fund_name='';
				$led_code = $CI->Depreciation_model->get_ledger_parent($row->asset_name);
                                $master_group_code = $CI->Depreciation_model->is_asset_in_depreciation_master_table($led_code);
				$exp_dep_percentage=explode("#",$master_group_code);
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
				for($i=0; $i<=$get_asset_used_years[0]; $i++){
                                        if($i == 0){
                                                $net_amount=$row->cost*($tot_day/365)*($exp_dep_percentage[0]/100);
                                        }
                                }
				//Get Fund/Project name.....
                                $this->db->from('new_fund_asset_register')->where('asset_id',$row->id);
                                $funds=$this->db->get();
                                foreach ($funds->result() as $row1){
                                	$fund_name=$row1->fund_name;
                                }
                                        $this->db->from('new_sponsored_asset_register')->where('asset_id',$row->id);
                                        $project=$this->db->get();
                                        foreach ($project->result() as $row2){
                                 	       $fund_name=$row2->project_name;
                                        }

				$curr_value=$row->cost-$net_amount;
                                $trim_dep_value = substr($net_amount,0, 6);
	                        $trim_curr_value = substr($curr_value,0, 8);
                                $date=date_create($row->date_of_purchase);
                                echo "<tr>";
                                        echo "<td>" . $led_name . "</td>";
                                        echo "<td>" . date_format($date,"Y-m-d")   . "</td>";
                                        echo "<td>" . $row->cost  . "</td>";
                                        echo "<td>" . $trim_dep_value  . "</td>";
					echo "<td>" . $trim_curr_value  . "</td>";
					echo "<td>" . "In Use"  . "</td>";
                                        echo "<td>" . $row->sanc_type . "</td>";
                                        echo "<td>" . $fund_name . "</td>";


			}
		}
	
	echo "</tbody>";
        echo "</table>";

	echo "<br/> ";
	echo anchor('report/depreciation', 'Back', array('title' => 'Back to user list'));

?>
