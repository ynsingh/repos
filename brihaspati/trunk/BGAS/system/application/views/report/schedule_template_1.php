<?php
	$i = 0;
	$old_cr_total = 0;
        $old_dr_total = 0;
        $old_total = 0;
	$opening_balance = 0;
	//$this->load->library('balanceSheet');
	//$liability = new BalanceSheet();
	$this->load->library('reportlist');
        $liability = new Reportlist();

	echo "<table border=0 class=\"simple-table balance-sheet-table\" >";
        echo "<thead><tr><th></th><th align=\"center\" colspan=\"2\">Current Year</th><th align=\"center\" colspan=\"2\">Previous Year</th></tr></thead>";

	if($id != '')
                $i = $id;

	$liability->init($i);
        //get opening balance
        $liability->calculate_op_balance('new', 'schedule');
        $opening_balance = $liability->opening_balance;
        $dr_total = $liability->dr_total;
        $cr_total = $liability->cr_total;

        $liability->calculate_op_balance('old', 'schedule');
        $opening_balance_prev = $liability->opening_balance_prev;
        $old_dr_total = $liability->old_dr_total;
        $old_cr_total = $liability->old_cr_total;

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
	
	$liability->callToSchedule(1);
	//$liability->schedule(1);
	$cr_total = $liability->cr_total;
        $dr_total = $liability->dr_total;

        $total = $cr_total - $dr_total;
	$liability->getPreviousYearDetails();
	//$liability->previous_year_data(1);
	$liability->callToOldSchedule(1);
	$old_cr_total = $liability->old_cr_total;
        $old_dr_total = $liability->old_dr_total;

        $old_total = $old_cr_total - $old_dr_total;

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

	//Calculate net profit/loss for current year
	$income = new Reportlist();
        $income->init(3);
        $expense = new Reportlist();
        $expense->init(4);
        $income_total = -$income->total;
        $old_income_total = -$income->total2;
        $expense_total = $expense->total;
        $old_expense_total = $expense->total2;
        $pandl = float_ops($income_total, $expense_total, '-');
        $old_pandl = float_ops($old_income_total, $old_expense_total, '-');
        if ($pandl != 0 || $old_pandl !=0)
        {
        	if($pandl > 0){
                	$total = float_ops($total, $pandl, '+');	
			echo "<tr>";
                        echo "<td class=\"bold\">";
                                echo "Add: Balance of net income transferred from the Income and Expenditure Account";
                        echo "</td>";

			echo "<td>";
			echo "</td>";

                        echo "<td align=\"right\">";
                                echo convert_cur($pandl);
                        echo "</td>";

                        echo "<td>";
                        echo "</td>";
	
			echo "<td>";
                        echo "</td>";

	                echo "</tr>";
		}
                elseif($pandl < 0){
                        $total = float_ops($total, $pandl, '+');	
			echo "<tr>";
                        echo "<td class=\"bold\">";
				echo "Deduct: Balance of net expenditure transferred from the Income and Expenditure Account";
                        echo "</td>";

			echo "<td align=\"right\">";
				echo convert_cur(-$pandl);
                        echo "</td>";

                        echo "<td>";
                        echo "</td>";

                        echo "<td>";
                        echo "</td>";

			echo "<td>";
                        echo "</td>";
                        echo "</tr>";
		}

                if($old_pandl > 0){
                        $old_total = float_ops($old_total, $old_pandl, '+');
			echo "<tr>";
                        echo "<td class=\"bold\">";
                                echo "Add: Balance of net income transferred from the Income and Expenditure Account";
                        echo "</td>";

                        echo "<td>";
                        echo "</td>";

                        echo "<td>";
                        echo "</td>";

                        echo "<td>";
                        echo "</td>";

                        echo "<td align=\"right\">";
				echo convert_cur($old_pandl);
                        echo "</td>";

                        echo "</tr>";
		}
                elseif($old_pandl < 0){
                        $old_total = float_ops($old_total, $old_pandl, '+');
			echo "<tr>";
                        echo "<td class=\"bold\">";
                                echo "Deduct: Balance of net expenditure transferred from the Income and Expenditure Account";
                        echo "</td>";

                        echo "<td>";
                        echo "</td>";

                        echo "<td>";
                        echo "</td>";

                        echo "<td align=\"right\">";
                                echo convert_cur(-$old_pandl);
                        echo "</td>";

                        echo "<td>";
                        echo "</td>";
                        echo "</tr>";
		}
        }
                                

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
        $liability->schedule(null);
        $liability->previous_year_data(null);
?>
