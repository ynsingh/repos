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
	echo "<tr><th align=\"left\"><b>Sr No.<br>&nbsp;&nbsp;1.</b></th><th align=\"center\"><b>2.<br>Name of the Sponsor</b></th><th align=\"center\" colspan=\"2\"><b>Opening Balance <br>As On 01.04.".$fy_start[0]."</b></th><th align=\"center\" colspan=\"2\"><b>Transactions <br>During the Year <br>".$curr_year."</b></th><th align=\"center\" colspan=\"2\"><b>Closing Balance <br>As On 31.03.".$fy_end[0]."</b></th></tr>";
        echo"</thead>";
        echo "<tr><td></td><td></td><td align=\"center\">Credit</td><td align=\"center\">Debit</td><td align=\"center\">Credit</td><td align=\"center\">Debit</td><td align=\"center\">Credit</td><td align=\"center\">Debit</td></tr>";

		$liability = new Reportlist1();
        	$liability->subschedule_3b('100');
		$op_balance_total1 = $liability->opening_bal1;
		$op_balance_total2 = -($liability->opening_bal2);
		$debit_total1 = $liability->debit_total;
		$credit_total1 = $liability->credit_total;
		$closing_balance1 = $liability->cl_bal1;
		$closing_balance2 = -($liability->cl_bal2);

	 	echo "<tr>";
                        echo "<td align=\"right\">";
                        echo "</td>";
                        echo "<td align=\"right\">";
                        echo "<strong>TOTAL</strong>";
                        echo "</td>";
			
		//if($op_balance_total < 0){
        	echo "<td align=\"right\">";
		echo "<strong>" . convert_amount_dc(-$op_balance_total2) . "</strong>"; 
		echo "</td>";		

                echo "<td align=\"right\">"; 
		echo "<strong>" . convert_amount_dc(+$op_balance_total1) . "</strong>";
		echo "</td>";

                        echo "<td align=\"right\">";
                        echo "<strong>" . convert_amount_dc(-$credit_total1) . "</strong>";
                        echo "</td>";

                        echo "<td align=\"right\">";
                        echo "<strong>" . convert_amount_dc(+$debit_total1) . "</strong>";
                        echo "</td>";

			//echo "<td></td>";
                        echo "<td align=\"right\">";
                        echo "<strong>" . convert_amount_dc(-$closing_balance2) . "</strong>";
                        echo "</td>";

                        echo "<td align=\"right\">";
                        echo "<strong>" . convert_amount_dc(+$closing_balance1) . "</strong>";
                        echo "</td>";
			//echo "<td></td>";
		echo "</tr>";
		echo "</table>";
?>

