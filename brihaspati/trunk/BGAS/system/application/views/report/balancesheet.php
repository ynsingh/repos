<?php
	$this->load->library('session');
	$date1 = $this->session->userdata('date1');
	$date2 = $this->session->userdata('date2');
	$this->load->library('reportlist');
	$fy_start=explode("-",$date1);
	$fy_end=explode("-",$date2);

	$curr_year = '('.$fy_start[0] ."-" .$fy_end[0] .')';
	$prev_year = '(' . ($fy_start[0]-1) ."-" . ($fy_end[0]-1) .')';
	/* check for dates */
	if($date1 > $date2)
	{
		$this->messages->add('TO ENTRY DATE should be larger than ENTRY DATE FROM.', 'success');
	}
	else {
		if( $print_preview)
		echo "<table border=0 align=\"center\">";
		else
		echo "<table border=0 >";
		echo "<tr valign=\"top\">";

		$liability = new Reportlist();
		echo "<td width=\"" . $left_width . "\">";
		$liability->init(2);
		echo "<table border=0 cellpadding=5 class=\"simple-table balance-sheet-table\" width=\"100%\">";
		echo "<thead><tr><th width=\"300\">Liabilities and Owners Equity</th><th width=\"125\" align=\"right\">Current Year Amount<br>$curr_year</th><th width=\"125\" align=\"right\">Previous Year Amount<br>$prev_year</th></tr></thead>";
		$liability->account_st_short(0);
		echo "</table>";
		echo "</td>";
		$liability_total = -$liability->total;
		$old_liability_total = -$liability->total2;

		$asset = new Reportlist();
		echo "<td width=\"" . $right_width . "\">";
		$asset->init(1);
		echo "<table border=0 cellpadding=5 class=\"simple-table balance-sheet-table\" width=\"100%\">";
		echo "<thead><tr><th width=\"300\">Assets</th><th width=\"125\" align=\"right\">Current Year Amount<br>$curr_year</th><th width=\"125\" align=\"right\"> Previous Year Amount<br>$prev_year</th></tr></thead>";
		$asset->account_st_short(0);
		echo "</table>";
		echo "</td>";
		$asset_total = $asset->total;
		$old_asset_total = $asset->total2;
		echo "</tr>";
	
		$income = new Reportlist();
		$income->init(3);
		$expense = new Reportlist();
		$expense->init(4);

		$income_total = -$income->total;
		$old_income_total = -$income->total2;
		$expense_total = $expense->total;
		$old_expense_total = $expense->total2;
	}
	$pandl = float_ops($income_total, $expense_total, '-');
	$old_pandl = float_ops($old_income_total, $old_expense_total, '-');

	$diffop = $this->Ledger_model->get_diff_op_balance();
	$old_diffop = $this->Ledger_model->get_prev_year_diff_op_balance();
	//echo "</table>";
	/* Liability side */

	$total = $liability_total;
	$old_total = $old_liability_total;
	//echo "<table border=0>";
	echo "<tr valign=\"top\" class=\"total-area\">";
	echo "<td>";
	echo "<table border=0 cellpadding=5 class=\"balance-sheet-total-table\" width=\"100%\">";
	echo "<tr valign=\"top\">";
	echo "<td width=\"300\" class=\"bold\">Liability and Owners Equity Total</td>";
	echo "<td width=\"125\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($liability_total)) . "</td>";
	echo "<td width=\"125\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($old_liability_total)) . "</td>";
	echo "</tr>";
	
	/* If Profit then Liability side, If Loss then Asset side */
	if ($pandl != 0 || $old_pandl !=0)
	{
		if ($pandl > 0 || $old_pandl > 0)
		{
			$total = float_ops($total, $pandl, '+');
			$old_total = float_ops($old_total, $old_pandl, '+');
			echo "<tr valign=\"top\">";
			echo "<td class=\"bold\">Profit & Loss Account (Net Profit)</td>";
			echo "<td align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($pandl)) . "</td>";
			echo "<td align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($old_pandl)) . "</td>";
			echo "</tr>";
		} else {
			echo "<tr>";
			echo "<td>&nbsp;</td>";
			echo "<td>&nbsp;</td>";
			echo "</tr>";
		}
	}
	
	/* If Op balance Dr then Liability side, If Op balance Cr then Asset side */
	if ($diffop != 0 || $old_diffop != 0)
	{
		if ($diffop > 0 || $old_diffop > 0)
		{
			$total = float_ops($total, $diffop, '+');
			$old_total = float_ops($old_total, $old_diffop, '+');
			echo "<tr valign=\"top\">";
			echo "<td class=\"bold\">Diff in O/P Balance</td>";
			echo "<td align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($diffop)) . "</td>";
			echo "<td align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($old_diffop)) . "</td>";
			echo "</tr>";
		} else {
			echo "<tr>";
			echo "<td>&nbsp;</td>";
			echo "<td>&nbsp;</td>";
			echo "</tr>";
		}
	}

	echo "<tr valign=\"top\" class=\"tr-balance\">";
	echo "<td class=\"bold\">Total</td>";
	echo "<td align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($total)) . "</td>";
	echo "<td align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($old_total)) . "</td>";
	echo "</tr>";
	echo "</table>";
	echo "</td>";

	/* Asset side */

	$total = $asset_total;
	$old_total = $old_asset_total;
	echo "<td>";
	echo "<table border=0 cellpadding=5 class=\"balance-sheet-total-table\" width=\"100%\">";
	echo "<tr valign=\"top\">";
	echo "<td width=\"300\" class=\"bold\">Asset Total</td>";
	echo "<td width=\"125\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($asset_total)) . "</td>";
	echo "<td width=\"125\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($old_asset_total)) . "</td>";
	echo "</tr>";

	/* If Profit then Liability side, If Loss then Asset side */
	if ($pandl != 0 || $old_pandl != 0)
	{
		if ($pandl > 0 || $old_pandl > 0)
		{
			echo "<tr>";
			echo "<td>&nbsp;</td>";
			echo "<td>&nbsp;</td>";
			echo "</tr>";
		} else {
			$total = float_ops($total, -$pandl, '+');
			$old_total = float_ops($old_total, -$old_pandl, '+');
			echo "<tr valign=\"top\">";
			echo "<td class=\"bold\">Profit & Loss Account (Net Loss)</td>";
			echo "<td align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur(-$pandl)) . "</td>";
			echo "<td align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur(-$old_pandl))  . "</td>";
			echo "</tr>";
		}
	}

	/* If Op balance Dr then Liability side, If Op balance Cr then Asset side */
	if ($diffop != 0 || $old_diffop != 0)
	{
		if ($diffop > 0 || $old_diffop > 0)
		{
			echo "<tr>";
			echo "<td>&nbsp;</td>";
			echo "<td>&nbsp;</td>";
			echo "</tr>";
		} else {
			$total = float_ops($total, -$diffop, '+');
			$old_total = float_ops($old_total, -$old_diffop, '+');
			echo "<tr valign=\"top\">";
			echo "<td class=\"bold\">Diff in O/P Balance</td>";
			echo "<td align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur(-$diffop)) . "</td>";
			echo "<td align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur(-$old_diffop)) . "</td>";
			echo "</tr>";
		}
	}
	echo "<tr valign=\"top\" class=\"tr-balance\">";
	echo "<td class=\"bold\">Total</td>";
	echo "<td align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($total)) . "</td>";
	echo "<td align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($old_total)) . "</td>";
	echo "</tr>";
	echo "</table>";

	echo "</td>";
	echo "</tr>";
	echo "</table>";
	echo "<br>";
       	if ( ! $print_preview)
	{
		echo form_open('report/printpreview/balancesheet/');
		echo form_submit('submit', 'Print Preview');
		echo form_close();
		/*echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		echo form_open('report/download/balancesheet/');
		echo form_submit('submit', 'Download CSV');
		echo form_close();*/
	}
?>
