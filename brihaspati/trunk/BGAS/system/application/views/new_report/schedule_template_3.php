<?php
/*	setlocale(LC_MONETARY, 'en_IN');
        $this->load->library('reportlist1');
	
        $this->load->library('session');
        $date1 = $this->session->userdata('date1');
        $date2 = $this->session->userdata('date2');
        $fy_start = explode("-",$date1);
        $fy_end = explode("-",$date2);
        $curr_year = '('.$fy_start[0]."-".$fy_end[0].')';
        $prev_year = '('.($fy_end[0]-1)."-".($fy_end[0]-1).')';
	
        $dr_total = 0.00;
        $cr_total = 0.00;
        $old_dr_total = 0.00;
        $old_cr_total = 0.00;
        echo "<table border=0 class=\"simple-table balance-sheet-table\">";
        echo "<thead><tr><th></th><th align=\"center\" colspan=\"2\">CURRENT YEAR</th><th align=\"center\" colspan=\"2\">PREVIOUS YEAR</th></tr></thead>";
        echo "<tr>";
        echo "<td><strong>A.  CURRENT LIABILITIES</strong><td>";
        //echo "<td></td>";
        echo "</tr>";
		
	$liability = new Reportlist1();
	$liability->Current_liability(84,'100401',3);
	$liability_totalA = $liability->liability_total;

	echo "<tr>";
                echo "<td align=\"right\">";
                echo "<strong> TOTAL(A)</strong>";
                echo "</td>";

                echo "<td colspan=\"2\" align=\"right\">";
                echo "<strong>" . convert_amount_dc($liability_totalA) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
      echo "</tr>";


	echo "<tr>";
        echo "<td><strong>B.  PROVISIONS</strong></td>";
       	echo "<td></td>";
        echo "</tr>";
	$liability = new Reportlist1();
	$liability->Provision(109,'100402',3);
	$liability_totalB = $liability->liability_total;

	 echo "<tr>";
                echo "<td align=\"right\">";
                echo "<strong> TOTAL(B)</strong>";
                echo "</td>";

                echo "<td colspan=\"2\" align=\"right\">";
                echo "<strong>" . convert_amount_dc($liability_totalB) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
      echo "</tr>";

	 echo "<tr>";
                echo "<td align=\"center\">";
                echo "<strong> TOTAL(A+B)</strong>";
                echo "</td>";
		$net_total = $liability_totalA+$liability_totalB; 


                echo "<td colspan=\"2\" align=\"right\">";
                echo "<strong>" . convert_amount_dc($net_total) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
                echo "<strong>" . convert_amount_dc(0) . "</strong>";
                echo "</td>";
      	echo "</tr>";
	echo "</table>"; */

?>

