<?php

	setlocale(LC_MONETARY, 'en_IN');
	$this->load->library('balancesheet');
	$this->load->library('session');
        $date1 = $this->session->userdata('date1');
        $date2 = $this->session->userdata('date2');
        $fy_start=explode("-",$date1);
        $fy_end=explode("-",$date2);
        $curr_year = '('.$fy_start[0] ."-" .$fy_end[0] .')';
        $prev_year = '(' . ($fy_start[0]-1) ."-" . ($fy_end[0]-1) .')';

	$net_dr = 0.00;
        $net_opening_bal = 0.00;
        $net_cr = 0.00;
        $net_total = 0.00;
        $net_current_year = 0.00;
        $net_previous_year = 0.00;

	echo "<table border=0 class=\"simple-table balance-sheet-table\">";
	echo "<thead><tr><th>DESCRIPTION</th><th align=\"center\" colspan=\"4\">GROSS BLOCK</th><th align=\"center\" colspan=\"4\">DEPRECIATION</th><th align=\"center\" colspan=\"2\">NET BLOCK</th></tr></thead>";

	echo "<tr>";
		echo "<td></td>";
		echo "<td>Cost/valuation<br>As at beginning<br>of the year</td>";
		echo "<td>Addition<br>s during<br> the year</td>";
		echo "<td>Deducti<br>ons<br>during<br>the year</td>";
		echo "<td>Cost/<br>valuation<br>at the<br>yearend</td>";
		echo "<td>As at the<br>beginning<br>of the year</td>";
		echo "<td>On<br>Additions<br>During the<br>year</td>";
		echo "<td>On<br>Deductio<br>ns<br>during<br>the year</td>";
		echo "<td>Total<br>up to the<br>year end</td>";
		echo "<td>As at the<br>current<br>year end</td>";
		echo "<td>As at the<br>previous<br>year end</td>";
		
	echo "</tr>";

//	$count = 1;
//	foreach($fixed_assets as $id =>$row){
		$dr_amount = 0;
		$cr_amount = 0;
		$total_amount = 0;
		$opening_bal = 0;
		$current_amount = 0;
		$previous_amount = 0;

		$asset = new Balancesheet();
		$asset->fixed_assets(14,7,'2001',"view","NULL");
		$dr_amount = $asset->dr_amount;
		$cr_amount = $asset->cr_amount;
		$total_amount = $asset->total_amount;
		$opening_bal = $asset->opening_bal;
		$current_amount = $asset->current_amount;
		$net_previousYear = $asset->net_previousYear;
        	//$object->init($row['id']);
	/*        list($dr_amount, $cr_amount, $total_amount, $opening_bal, $current_amount) = $asset->fixed_assets(14,7,"view","NULL");
		$net_dr = $net_dr + $dr_amount;
		$net_cr = $net_cr + $cr_amount;
		$net_total = $net_total + $total_amount;
		$net_opening_bal = $net_opening_bal + $opening_bal;
		$net_current_year = $net_current_year + $current_amount;
	//	$net_previous_year = $net_previous_year + $previous_amount;
	//	$count++;
	//	}*/

	echo "<tr> <class=\"tr-ledger\">";
                                echo "<td class=\"td-ledger\" width=\"10%\">";
                                        echo "<strong>TOTAL</strong>";
                                echo "</td>";

                                echo "<td align=\"right\" width=\"9%\">";
                                        echo "<strong>".convert_amount_dc($opening_bal)."</strong>";
                                echo "</td>";

                                echo "<td align=\"right\" width=\"9%\">";
                                        echo "<strong>".convert_amount_dc(+$dr_amount)."</strong>";
                                echo "</td>";

                                echo "<td align=\"right\" width=\"9%\">";
                                        echo "<strong>".convert_amount_dc(-$cr_amount)."</strong>";
                                echo "</td>";

                                echo "<td align=\"right\" width=\"9%\">";
                                        echo "<strong>".convert_amount_dc($total_amount)."</strong>";
                                echo "</td>";

                                echo "<td align=\"right\" width=\"9%\">";
                                        echo "<strong>0.00</strong>";
                                echo "</td>";

                                echo "<td align=\"right\" width=\"9%\">";
                                        echo "<strong>0.00</strong>";
                                echo "</td>";

                                echo "<td align=\"right\" width=\"9%\">";
                                        echo "<strong>0.00</strong>";
                                echo "</td>";

                                echo "<td align=\"right\" width=\"9%\">";
                                        echo "<strong>0.00</strong>";
                                echo "</td>";

                                echo "<td align=\"right\" width=\"9%\">";
                                        echo "<strong>".convert_amount_dc($current_amount)."</strong>";
                                echo "</td>";

                                echo "<td align=\"right\" width=\"9%\">";
                                        echo "<strong>".convert_amount_dc($net_previousYear)."</strong>";
                                echo "</td>";
                        echo "</tr>";
	echo "</table>";
?>
