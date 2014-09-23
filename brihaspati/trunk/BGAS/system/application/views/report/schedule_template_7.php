<?php

	setlocale(LC_MONETARY, 'en_IN');
        $this->load->library('balancesheet');
	$count = 1;
	$credit_total = 0;
	$debit_total = 0;
	$old_credit_total = 0;
	$old_debit_total = 0;

	echo "<table border=0 class=\"simple-table balance-sheet-table\" >";
        echo "<thead><tr><th></th><th align=\"center\" colspan=\"2\">CURRENT YEAR</th><th align=\"center\" colspan=\"2\">PREVIOUS YEAR</th></tr></thead>";
	
		foreach($loans_advances as $id => $row){
			$object = new Balancesheet();
			$object->init($row['id']);
			list($credit_amount, $debit_amount, $old_credit_amount, $old_debit_amount) = $object->loans_advances($count);
			$credit_total = $credit_total + $credit_amount;
			$debit_total = $debit_total + $debit_amount;
			$old_credit_total = $old_credit_total + $old_credit_amount;
			$old_debit_total = $old_debit_total + $old_debit_amount;
			$count++;
		}

		echo "<tr>";
			echo "<td>";
				echo "<strong> TOTAL</strong>";
			echo "</td>";

			echo "<td>";
				echo "<strong>" . convert_amount_dc($debit_total) . "</strong>";
			echo "</td>";

			echo "<td>";
				echo "<strong>" . convert_amount_dc(-$credit_total) . "</strong>";
			echo "</td>";

			echo "<td>";
				echo "<strong>" . convert_amount_dc($old_debit_total) . "</strong>";
			echo "</td>";

			echo "<td>";
				echo "<strong>" . convert_amount_dc(-$old_credit_total) . "</strong>";
			echo "</td>";
		echo "</tr>";
	echo "</table>";
?>
