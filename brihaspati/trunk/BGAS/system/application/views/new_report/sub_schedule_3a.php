<?php

	setlocale(LC_MONETARY, 'en_IN');
	$this->load->library('reportlist1');
	$this->load->library('reportlist');
	$this->load->library('session');
        $date1 = $this->session->userdata('date1');
        $date2 = $this->session->userdata('date2');
        $fy_start=explode("-",$date1);
        $fy_end=explode("-",$date2);
        $curr_year = '('.$fy_start[0] ."-" .$fy_end[0] .')';
        $prev_year = '(' . ($fy_start[0]-1) ."-" . ($fy_end[0]-1) .')';
	echo "<br>";
	echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"100%\">";
	echo "<thead><tr><th align=\"center\" width=\"5%\">&nbsp;&nbsp;<b>1.<br>Sr.No.</b></th><th align=\"center\" width=\"18%\">&nbsp;&nbsp;<b>2.<br>Name of the Project</b></th><th align=\"center\" colspan=\"2\" width=\"22%\"><b>Opening Balance</th><th align=\"center\" colspan=\"2\" width=\"15%\">&nbsp;&nbsp;<b>5.<br>Receipts/Recoveries
during the year</th><th align=\"center\" width=\"10%\">&nbsp;&nbsp;<b>6.<br>Total</b></th><th colspan=\"2\" width=\"22%\" align=\"left\">&nbsp;&nbsp;<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7.<br>Expenditure during the year</b></th><th align=\"center\" width=\"22%\"><b>Closing Balance</b></th></tr></thead>";
		echo "<tr><td colsapn=\"2\" width=\"5%\"></td><td colsapn=\"2\" width=\"18%\"></td><td align=\"center\"><b>&nbsp;&nbsp;3.<br>Credit</b></td><td align=\"center\"><b>&nbsp;&nbsp;4.<br>Debit</b></td><td colspan=\"2\"></td><td width=\"10%\"></td><td align=\"center\" width=\"20%\"></td><td align=\"center\" width=\"20%\"><b>8.<br>Credit</b></td><td align=\"center\" width=\"20%\"><b>9.<br>Debit</b></td></tr>";
		 $liability = new Reportlist();
                 $liability->init(93);
                 $liability->callToOpBalance('new', 'schedule');
                 $opening_balance = $liability->opening_balance;
                 $dr_total = $liability->dr_total;
                 $cr_total = $liability->cr_total;

		 $liability = new Reportlist1();
		 $liability->subschedule_3a();

                 echo "<td class=\"td-group\" align=\"center\">";
		 echo "<td align=\"right\" colspan=\"2\">" . convert_amount_dc($dr_total) . "</td>";
                 echo "<td align=\"right\" colspan=\"2\">" . convert_amount_dc($cr_total) . "</td>";
                 echo "</td>";

	 	echo "<tr>";
			echo "<td align=\"right\" width=\"10%\">";
                        echo "</td>";

                	echo "<td align=\"center\" width=\"15%\">";
                	echo "<strong> TOTAL</strong>";
                	echo "</td>";

                	echo "<td align=\"right\" width=\"10%\">";
                	echo "<strong>" . convert_amount_dc(0) . "</strong>";
                	echo "</td>";

                	echo "<td align=\"right\" width=\"10%\">";
                	echo "<strong>" . convert_amount_dc(0) . "</strong>";
                	echo "</td>";

			echo "<td colspan=\"2\" align=\"right\">";
                        echo "<strong>" . convert_amount_dc(0) . "</strong>";
                        echo "</td>";

                        echo "<td align=\"right\">";
                        echo "<strong>" . convert_amount_dc(0) . "</strong>";
                        echo "</td>";

			echo "<td align=\"right\" width=\"16%\">";
                        echo "<strong>" . convert_amount_dc(0) . "</strong>";
                        echo "</td>";

                        echo "<td align=\"right\">";
                        echo "<strong>" . convert_amount_dc(0) . "</strong>";
                        echo "</td>";

			echo "<td colspan=\"2\" align=\"right\">";
                        echo "<strong>" . convert_amount_dc(0) . "</strong>";
                        echo "</td>";
      		echo "</tr>";  
		echo "</table>";

?>
