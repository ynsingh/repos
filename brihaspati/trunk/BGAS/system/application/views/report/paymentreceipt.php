<?php
	setlocale(LC_MONETARY, 'en_IN');

	if ( ! $print_preview)
	{
		echo form_open('report/paymentreceipt/');
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
		echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";?>
		<input type='submit' value="GET" class='loading'>
		<?php echo "</p>";
		echo form_close();
	}

	$this->load->library('reportlist');
	echo "<table>";
	echo "<tr valign=\"top\">";

	$this->load->library('session');
	$date1 = $this->session->userdata('date1');
	$date2 = $this->session->userdata('date2');

	$this->db->from('settings');
	$value = $this->db->get();
	foreach($value->result() as $row) {
		$fy_start=explode("-",$row->fy_start);
		$fy_end=explode("-",$row->fy_end);
	}
	$curr_year = '('.$fy_start[0] ."-" .$fy_end[0] .')';
	$prev_year = '(' . ($fy_start[0]-1) ."-" . ($fy_end[0]-1) .')';
	/* check for dates */
	if($date1 > $date2)
	{
		$this->messages->add('TO ENTRY DATE should be larger than ENTRY DATE FROM.', 'success');
	}
	else {

		/* Net P/L : Receipt */
		$net_income_total = 0;
		$net_old_income_total = 0;

		/* Net P/L : Payment */
		$net_expense_total = 0;
		$net_old_expense_total = 0;
		$this->db->from('groups')->where('parent_id', 4)->where('affects_gross !=', 1);
		$net_expense_list_q = $this->db->get();
		echo "<td>";
		echo "<table border=0 cellpadding=5 class=\"simple-table profit-loss-table\" width=\"100%\">";
		echo "<thead><tr><th width=\"$left_width\">Payment (Net)</th><th width=\"$right_width\" align=\"right\">Current Year Amount<br>$curr_year</th><th width=\"$right_width\" align=\"right\">Previous Year Amount<br>$prev_year</th></tr></thead>";
		foreach ($net_expense_list_q->result() as $row)
		{
			$net_expense = new Reportlist();
			$net_expense->init($row->id);
			$net_expense->account_st_short(0);
			$net_expense_total = float_ops($net_expense_total, $net_expense->total, '+');
			$net_old_expense_total = float_ops($net_old_expense_total, $net_expense->total2, '+');
		}
		echo "</table>";
		echo "</td>";

		$this->db->from('groups')->where('parent_id', 3)->where('affects_gross !=', 1);
		$net_income_list_q = $this->db->get();
		echo "<td>";
		echo "<table border=0 cellpadding=5 class=\"simple-table profit-loss-table\" width=\"100%\">";
		echo "<thead><tr><th width=\"$left_width\">Receipt (Net)</th><th width=\"$right_width\" align=\"right\">Current Year Amount<br>$curr_year</th><th width=\"$right_width\" align=\"right\">Previous Year Amount<br>$prev_year</th></tr></thead>";
		foreach ($net_income_list_q->result() as $row)
		{
			$net_income = new Reportlist();
			$net_income->init($row->id);
			$net_income->account_st_short(0);
			$net_income_total = float_ops($net_income_total, $net_income->total, '+');
			$net_old_income_total = float_ops($net_old_income_total, $net_income->total2, '+');
		}
		echo "</table>";
		echo "</td>";

		$net_income_total = -$net_income_total; /* Converting to positive value since Cr */
		$net_old_income_total = -$net_old_income_total; /* Converting to positive value since Cr */
		echo "</tr>";

		echo "<tr valign=\"top\" class=\"total-area\">";

		

		/* Showing Net P/L : Payment */
		$nettotal = $net_expense_total;
		$net_old_total = $net_old_expense_total;

		echo "<td>";
		echo "<table border=0 cellpadding=5 class=\"simple-table profit-loss-total-table\" width=\"100%\">";
		echo "<tr valign=\"top\" class=\"tr-balance\">";
		echo "<td width=\"$left_width\" class=\"bold\">Total</td>";
		echo "<td width=\"$right_width\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($nettotal)) . "</td>";
		echo "<td width=\"$right_width\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($net_old_total)) . "</td>";
		echo "</tr>";
		echo "</table>";
		echo "</td>";

		/* Showing Net P/L : Receipt */
		$nettotal = $net_income_total;
		$net_old_total = $net_old_income_total;
		echo "<td>";
		echo "<table border=0 cellpadding=5 class=\"simple-table profit-loss-total-table\" width=\"100%\">";
		echo "<tr valign=\"top\" class=\"tr-balance\">";
		echo "<td width=\"$left_width\" class=\"bold\">Total</td>";
		echo "<td width=\"$right_width\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($nettotal)) . "</td>";
		echo "<td width=\"$right_width\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($net_old_total)) . "</td>";
		echo "</tr>";
		echo "</table>";
		echo "</td>";


		echo "</tr>";
		echo "</table>";
	}
	if(! $print_preview)
	{
		echo form_open('report/printpreview/paymentreceipt/');
		echo form_submit('submit', 'Print Preview');
		echo form_close();
		/*echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		echo form_open('report/download/profitandloss/');
		echo form_submit('submit', 'Download CSV');
		echo form_close();*/
	}
?>
