<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');


 echo "<table border=0 cellpadding=5 class=\"simple-table balance-sheet-table\" width=\"80%\">";
 echo "<thead><tr><th>S.No</th><th>Asset Name</th><th>Date of Purchase</th><th>Cost</th><th>Dep.Amount</th><th>Current Value</th></tr></thead>";
        echo "<tbody>";
$i=1;
$sum=0;
@$val=$pico;
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
                                $date=date_create($row->date_of_purchase);
                               //$date2=Date("d F Y");
                                //$date3=date_create(" $IRD_WEF_Date");
                                //$date4=date_create("$date2");
                                //$diff=date_diff($date3,$date4);
                                //$day = $diff->format("%R%a days");
                                //$value= $IRD_Rate * $ERPMIM_Depreciation_Percentage/(100*365);
                                //$tot_amount=$value * $day;
                                //$cur_value = $IRD_Rate - $tot_amount;
                                echo "<tr>";
                                        echo "<td>" . $i . '.' . "</td>";
                                        echo "<td>" . $led_name . "</td>";
                                        //echo "<td>" .  $IRD_WEF_Date . "</td>";
                                /*if($cur_value <= 0)
                                {*/
                                        echo "<td>" . date_format($date,"Y-m-d")   . "</td>";
                                        echo "<td>" . $row->cost  . "</td>";
                                        echo "<td>" .  0  . "</td>";
					echo "<td>" . $row->cost  . "</td>";
                              /*  }
                                else
                                {
                                        echo "<td>" . $IRD_Rate  . "</td>";
                                        echo "<td>" . $tot_amount  . "</td>";
                                        echo "<td>" .  $cur_value  . "</td>";
                                        echo "</tr>";
                                        $sum+=$tot_amount;
                                        $i++;
                                }*/
					$i++;

			}
		}
	
	echo "</tbody>";
        echo "</table>";

	echo "<br/> ";
	echo anchor('report/depreciation', 'Back', array('title' => 'Back to user list'));

?>
