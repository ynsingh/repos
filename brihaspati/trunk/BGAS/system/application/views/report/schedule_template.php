<?php
	$i = 0;
	$old_cr_total = 0;
        $old_dr_total = 0;
        $old_total = 0;
	$opening_balance = 0;
	//$this->load->library('balanceSheet');
	//$liability = new BalanceSheet();
	$this->load->library('reportlist');
        $object = new Reportlist();
	
	echo "<table border=0 class=\"simple-table balance-sheet-table\" >";
        echo "<thead><tr><th></th><th align=\"center\" colspan=\"2\">Current Year</th><th align=\"center\" colspan=\"2\">Previous Year</th></tr></thead>";
	if($id != '')
                $i = $id;

	$object->init($i);
	//get opening balance
	$object->calculate_op_balance('new','schedule');
	$opening_balance = $object->opening_balance;
	$dr_total = $object->dr_total;
        $cr_total = $object->cr_total;
	$object->calculate_op_balance('old', 'schedule');
	$opening_balance_prev = $object->opening_balance_prev;
	$old_dr_total = $object->old_dr_total;
	$old_cr_total = $object->old_cr_total;
	//Fetch opening balance from db
	echo "<tr>";
		echo "<td width=40%>";
			echo "Balance as at the beginning of the year";
        	echo "</td>";
	
		if($cr_total > $dr_total){
			echo "<td width=15%>";
			echo "</td>";
	
			echo "<td width=15% align=\"right\">";
				echo convert_cur($opening_balance);
	        	echo "</td>";
		}else{
			echo "<td width=15% align=\"right\">";
				echo convert_cur($opening_balance);
                        echo "</td>";

                        echo "<td width=15%>";
                        echo "</td>";
		}

		if($old_cr_total > $old_dr_total){
                        echo "<td width=15%>";
                        echo "</td>";
        
                        echo "<td width=15% align=\"right\">";
                                echo convert_cur($opening_balance_prev);
                        echo "</td>";
                }else{
                        echo "<td width=15% align=\"right\">";
                                echo convert_cur($opening_balance_prev);
                        echo "</td>";

                        echo "<td width=15%>";
                        echo "</td>";
                }

	echo "</tr>";
	
//	$object->schedule(0);
	$object->callToSchedule(1);
	$cr_total = $object->cr_total;
	//$cr_total = $cr_total + $object->credit_total;
        $dr_total = $object->dr_total;
        //$dr_total = $dr_total + $object->debit_total;
	if(!strncmp($object->code, '10', strlen('10')))
		$total = $cr_total - $dr_total;
	else
	        $total = $dr_total - $cr_total;
	$object->getPreviousYearDetails();	
	//$object->previous_year_data(0);
	$object->callToOldSchedule(1);
	$old_cr_total = $object->old_cr_total;
        $old_dr_total = $object->old_dr_total;
        //$old_total = $old_cr_total - $old_dr_total;
	if(!strncmp($object->code, '10', strlen('10')))
		$old_total = $old_cr_total - $old_dr_total;
	else
	        $old_total = $old_dr_total - $old_cr_total;

	//Display total for the given schedule
	echo "<tr>";
                echo "<td width=40% class=\"bold\">";
                        echo "TOTAL";
                echo "</td>";

                echo "<td width=15% align=\"right\">";
                        //echo $total;
			echo convert_cur($dr_total);
                echo "</td>";

                echo "<td width=15% align=\"right\">";
			echo convert_cur($cr_total);
                echo "</td>";

                echo "<td width=15%>";
			echo convert_cur($old_dr_total);
		echo "</td>";

                echo "<td width=15%>";
			echo convert_cur($old_cr_total);
                echo "</td>";
        echo "</tr>";
	//$liability->schedule();

	echo "<tr>";
                echo "<td width=40% class=\"bold\">";
                        echo "BALANCE AT THE YEAR-END";
                echo "</td>";

                echo "<td colspan =2 width=30% align=\"right\">";
		        echo convert_cur($total);
                        //echo $dr_total;
                echo "</td>";

               // echo "<td width=15% align=\"right\">";
                        //echo $cr_total;
               // echo "</td>";

                echo "<td  colspan = 2 width=30% align=\"right\">";
			echo convert_cur($old_total);
                echo "</td>";

                //echo "<td width=15%>";
                //echo "</td>";
        echo "</tr>";

	echo "</table>";

	//unset schedule() method's static values
	$object->schedule(null);
	$object->previous_year_data(null);
?>
