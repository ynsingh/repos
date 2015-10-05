<?php

	setlocale(LC_MONETARY, 'en_IN');
	$this->load->library('reportlist1');
	$this->load->library('session');
    	$date1 = $this->session->userdata('date1');
    	$date2 = $this->session->userdata('date2');
    	$fy_start=explode("-",$date1);
    	$fy_end=explode("-",$date2);
    	$curr_year = '('.$fy_start[0] ."-" .$fy_end[0] .')';
    	$prev_year = '(' . ($fy_start[0]-1) ."-" . ($fy_end[0]-1) .')';
	echo "<br>";
	echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"100%\">";
    	echo "<thead>";
    	echo "<tr><th align=\"left\">Sr No.</th><th align=\"center\">Name of the Project</th><th align=\"center\" colspan=\"2\">Opening Balance</th><th align=\"center\">Receipts/Recoveries during the year</th><th align=\"center\">Total</th><th align=\"center\">Expenditure during the year</th><th align=\"center\" colspan=\"2\">Closing Balance</th></tr>";
    	echo"</thead>";
    	echo "<tr><td></td><td></td><td align=\"center\">Credit</td><td align=\"center\">Debit</td><td></td><td></td><td></td><td align=\"center\">Credit</td><td align=\"center\">Debit</td></tr>";
    
    	$liability = new Reportlist1();
    	$liability->subschedule_3a('93');
	$opening_balance1 = $liability->op_balance1;
	$opening_balance2 = -($liability->op_balance2);
	$receipt_total = $liability->receipt_total1;
	$total = $liability->sum_total;
	$expense_total = $liability->expense_total1;
	$cl_balance1 = $liability->closing_bal1;
        $cl_balance2 = -($liability->closing_bal2);

	echo "<tr>";
	echo "<td align=\"right\" >";
        echo "</td>";

    	echo "<td align=\"left\">";
    	echo "<strong> TOTAL</strong>";
    	echo "</td>";

        	echo "<td align=\"right\">";
		echo "<strong>" . convert_amount_dc(-$opening_balance2) . "</strong>"; 
                echo "</td>";

                echo "<td align=\"right\">"; 
		echo "<strong>" . convert_amount_dc(+$opening_balance1) . "</strong>";
		echo "</td>";

    		echo "<td align=\"right\">";
    		echo "<strong>" . convert_amount_dc($receipt_total) . "</strong>";
    		echo "</td>";

    		echo "<td align=\"right\">";
    		echo "<strong>" . convert_amount_dc($total) . "</strong>";
    		echo "</td>";

		echo "<td align=\"right\">";
        	echo "<strong>" . convert_amount_dc($expense_total) . "</strong>";
        	echo "</td>";
		
                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(-$cl_balance2) . "</strong>";
		echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(+$cl_balance1) . "</strong>";
                echo "</td>";


    	echo "</tr>";  
	echo "</table>";

?>
