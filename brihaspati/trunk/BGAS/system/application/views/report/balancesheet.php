<?php

         if ( ! $print_preview)
	{	
	echo form_open('report/balancesheet/');
	echo "<p>";
	echo "<span id=\"tooltip-target-1\">";
	echo form_label('Entry Date From', 'entry_date1');
	echo " ";
	echo form_input_date_restrict($entry_date1);
	echo "</span>";
	echo "<span id=\"tooltip-content-1\">Date format is " . $this->config->item('account_date_format') . ".</span>";
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	echo "<span id=\"tooltip-target-2\">";
	echo form_label('To Entry Date', 'entry_date2');
	echo " ";
	echo form_input_date_restrict($entry_date2);
	echo "</span>";
	echo "<span id=\"tooltip-content-2\">Date format is " . $this->config->item('account_date_format') . ".</span>";
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	echo form_submit('submit', 'Get' . $this->template->set('nav_links', array('report/download/balancesheet' => 'Download CSV', 'report/printpreview/balancesheet' => 'Print Preview'))) ;
	echo " ";
	echo "</p>";
	echo form_close();
	}
?>
<?php
	$this->load->library('session');
	$date1 = $this->session->userdata('date1');
	$date2 = $this->session->userdata('date2');
	$this->load->library('accountlist');
	/* check for dates */
	if($date1 > $date2)
	{
		$this->messages->add('TO ENTRY DATE should be larger than ENTRY DATE FROM.', 'success');
	}
	else {
	echo "<table border=0>";
	echo "<tr valign=\"top\">";

	$liability = new Accountlist();
	echo "<td width=\"" . $left_width . "\">";
	$liability->init(2);

	echo "<table border=0 cellpadding=5 class=\"simple-table balance-sheet-table\" width=\"100%\">";
	echo "<thead><tr><th>Liabilities and Owners Equity</th><th align=\"right\">Amount</th></tr></thead>";
	$liability->account_st_short(0);
	echo "</table>";
	echo "</td>";
	$liability_total = -$liability->total;

	$asset = new Accountlist();
	echo "<td width=\"" . $right_width . "\">";
	$asset->init(1);
	echo "<table border=0 cellpadding=5 class=\"simple-table balance-sheet-table\" width=\"100%\">";
	echo "<thead><tr><th>Assets</th><th align=\"right\">Amount</th></tr></thead>";
	$asset->account_st_short(0);
	echo "</table>";
	echo "</td>";
	$asset_total = $asset->total;

	echo "</tr>";

	$income = new Accountlist();
	$income->init(3);
	$expense = new Accountlist();
	$expense->init(4);

	$income_total = -$income->total;
	$expense_total = $expense->total;

	$pandl = float_ops($income_total, $expense_total, '-');

	$diffop = $this->Ledger_model->get_diff_op_balance();

	/* Liability side */

	$total = $liability_total;

	echo "<tr valign=\"top\" class=\"total-area\">";
	echo "<td>";
	echo "<table border=0 cellpadding=5 class=\"balance-sheet-total-table\" width=\"100%\">";
	echo "<tr valign=\"top\">";
	echo "<td class=\"bold\">Liability and Owners Equity Total</td>";
	echo "<td align=\"right\" class=\"bold\">" . convert_cur($liability_total) . "</td>";
	echo "</tr>";

	/* If Profit then Liability side, If Loss then Asset side */
	if ($pandl != 0)
	{
		if ($pandl > 0)
		{
			$total = float_ops($total, $pandl, '+');
			echo "<tr valign=\"top\">";
			echo "<td class=\"bold\">Profit & Loss Account (Net Profit)</td>";
			echo "<td align=\"right\" class=\"bold\">" . convert_cur($pandl) . "</td>";
			echo "</tr>";
		} else {
			echo "<tr>";
			echo "<td>&nbsp;</td>";
			echo "<td>&nbsp;</td>";
			echo "</tr>";
		}
	}

	/* If Op balance Dr then Liability side, If Op balance Cr then Asset side */
	if ($diffop != 0)
	{
		if ($diffop > 0)
		{
			$total = float_ops($total, $diffop, '+');
			echo "<tr valign=\"top\">";
			echo "<td class=\"bold\">Diff in O/P Balance</td>";
			echo "<td align=\"right\" class=\"bold\">" . convert_cur($diffop) . "</td>";
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
	echo "<td align=\"right\" class=\"bold\">" . convert_cur($total) . "</td>";
	echo "</tr>";
	echo "</table>";
	echo "</td>";

	/* Asset side */

	$total = $asset_total;

	echo "<td>";
	echo "<table border=0 cellpadding=5 class=\"balance-sheet-total-table\" width=\"100%\">";
	echo "<tr valign=\"top\">";
	echo "<td class=\"bold\">Asset Total</td>";
	echo "<td align=\"right\" class=\"bold\">" . convert_cur($asset_total) . "</td>";
	echo "</tr>";

	/* If Profit then Liability side, If Loss then Asset side */
	if ($pandl != 0)
	{
		if ($pandl > 0)
		{
			echo "<tr>";
			echo "<td>&nbsp;</td>";
			echo "<td>&nbsp;</td>";
			echo "</tr>";
		} else {
			$total = float_ops($total, -$pandl, '+');
			echo "<tr valign=\"top\">";
			echo "<td class=\"bold\">Profit & Loss Account (Net Loss)</td>";
			echo "<td align=\"right\" class=\"bold\">" . convert_cur(-$pandl) . "</td>";
			echo "</tr>";
		}
	}

	/* If Op balance Dr then Liability side, If Op balance Cr then Asset side */
	if ($diffop != 0)
	{
		if ($diffop > 0)
		{
			echo "<tr>";
			echo "<td>&nbsp;</td>";
			echo "<td>&nbsp;</td>";
			echo "</tr>";
		} else {
			$total = float_ops($total, -$diffop, '+');
			echo "<tr valign=\"top\">";
			echo "<td class=\"bold\">Diff in O/P Balance</td>";
			echo "<td align=\"right\" class=\"bold\">" . convert_cur(-$diffop) . "</td>";
			echo "</tr>";
		}
	}

	echo "<tr valign=\"top\" class=\"tr-balance\">";
	echo "<td class=\"bold\">Total</td>";
	echo "<td align=\"right\" class=\"bold\">" . convert_cur($total) . "</td>";
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
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	echo form_open('report/download/balancesheet/');
	echo form_submit('submit', 'Download CSV');
	echo form_close();
	}
	}

