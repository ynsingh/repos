<?php

	setlocale(LC_MONETARY, 'en_IN');
	$this->load->library('balancesheet');

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

	$count = 1;
	foreach($fixed_assets as $id =>$row){
		$dr_amount = 0;
		$cr_amount = 0;
		$total_amount = 0;
		$opening_bal = 0;
		$current_amount = 0;
		$previous_amount = 0;

		$object = new Balancesheet();
        	$object->init($row['id']);
	        list($dr_amount, $cr_amount, $total_amount, $opening_bal, $current_amount, $previous_amount) = $object->fixed_assets($count);
		$net_dr = $net_dr + $dr_amount;
		$net_cr = $net_cr + $cr_amount;
		$net_total = $net_total + $total_amount;
		$net_opening_bal = $net_opening_bal + $opening_bal;
		$net_current_year = $net_current_year + $current_amount;
		$net_previous_year = $net_previous_year + $previous_amount;
		$count++;
	}

	echo "<tr> <class=\"tr-ledger\">";
                                echo "<td class=\"td-ledger\" width=\"10%\">";
                                        echo "<strong>TOTAL</strong>";
                                echo "</td>";

                                echo "<td align=\"right\" width=\"9%\">";
                                        echo "<strong>".convert_amount_dc($net_opening_bal)."</strong>";
                                echo "</td>";

                                echo "<td align=\"right\" width=\"9%\">";
                                        echo "<strong>".convert_amount_dc($net_dr)."</strong>";
                                echo "</td>";

                                echo "<td align=\"right\" width=\"9%\">";
                                        echo "<strong>".convert_amount_dc($net_cr)."</strong>";
                                echo "</td>";

                                echo "<td align=\"right\" width=\"9%\">";
                                        echo "<strong>".convert_amount_dc($net_total)."</strong>";
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
                                        echo "<strong>".convert_amount_dc($net_current_year)."</strong>";
                                echo "</td>";

                                echo "<td align=\"right\" width=\"9%\">";
                                        echo "<strong>".convert_amount_dc($net_previous_year)."</strong>";
                                echo "</td>";
                        echo "</tr>";
	echo "</table>";
?>
