<?php
	$this->load->library('session');
	$date1 = $this->session->userdata('date1');
	$date2 = $this->session->userdata('date2');

	if (! $print_preview)
	{
	echo form_open('report/trialbalance');
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

	echo form_submit('submit', 'Get');
	echo " ";
	echo "</p>";
	echo form_close();
	}

	$temp_dr_total = 0;
	$temp_cr_total = 0;
	
	echo "<table border=0 cellpadding=5 class=\"simple-table trial-balance-table\" width=\"$width\">";
	echo "<thead><tr><th>Ledger Account</th><th>O/P Balance</th><th>C/L Balance</th><th>Dr Total</th><th>Cr Total</th></tr></thead>";
	$this->load->model('Ledger_model');
	$all_ledgers = $this->Ledger_model->get_all_ledgers1($date1, $date2);

	$odd_even = "odd";
	foreach ($all_ledgers as $ledger_id => $ledger_name)
	{
		if ($ledger_id == 0) continue;
		echo "<tr class=\"tr-" . $odd_even . "\">";

		echo "<td>";
		echo  anchor('report/ledgerst/' . $ledger_id, $ledger_name, array('title' => $ledger_name . ' Ledger Statement', 'class' => 'anchor-link-a'));
		echo "</td>";

		echo "<td>";
		list ($opbal_amount, $opbal_type) = $this->Ledger_model->get_op_balance($ledger_id);
		echo convert_opening($opbal_amount, $opbal_type);
		echo "</td>";

		echo "<td>";
		$clbal_amount = $this->Ledger_model->get_ledger_balance($ledger_id);
		echo convert_amount_dc($clbal_amount);
		echo "</td>";

		echo "<td>";
		$dr_total = $this->Ledger_model->get_dr_total($ledger_id);
		if ($dr_total)
		{
			echo money_format('%!i', $dr_total);
			$temp_dr_total = float_ops($temp_dr_total, $dr_total, '+');
		} else {
			echo "0";
		}
		echo "</td>";
		echo "<td>";
		$cr_total = $this->Ledger_model->get_cr_total($ledger_id);
		if ($cr_total)
		{
			echo money_format('%!i', $cr_total);
			$temp_cr_total = float_ops($temp_cr_total, $cr_total, '+');
		} else {
			echo "0";
		}
		echo "</td>";
		echo "</tr>";
		$odd_even = ($odd_even == "odd") ? "even" : "odd";
	}
		echo "<tr class=\"tr-total\"><td colspan=\"3\">TOTAL ";
	if (float_ops($temp_dr_total, $temp_cr_total, '=='))
		echo "<img src=\"" . asset_url() . "images/icons/match.png\">";
	else
		echo "<img src=\"" . asset_url() . "images/icons/nomatch.png\">";
	echo "</td><td>Dr " . money_format('%!i', convert_cur($temp_dr_total)) . "</td><td>Cr " . money_format('%!i', convert_cur($temp_cr_total)) . "</td></tr>";
	echo "</table>";
	echo "<br>";
	if(! $print_preview)
	{
	echo form_open('report/printpreview/trialbalance/');
	echo form_submit('submit', 'Print Preview');
	echo form_close();
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	echo form_open('report/download/trialbalance/');
	echo form_submit('submit', 'Download CSV');
	echo form_close();
	}

