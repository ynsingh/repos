<?php
	setlocale(LC_MONETARY, 'en_IN');

	if ( ! $print_preview)
	{
		echo form_open('report/profitandloss/');
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

	/**********************************************************************/
	/*********************** GROSS CALCULATIONS ***************************/
	/**********************************************************************/

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
		/* Gross P/L : Incomes */
		$gross_income_total = 0;
		$old_gross_income_total = 0;
		$this->db->from('groups')->where('parent_id', 3)->where('affects_gross' , 1);
		$gross_income_list_q = $this->db->get();
		echo "<td>";
		echo "<table border=0 cellpadding=5 class=\"simple-table profit-loss-table\" width=\"100%\">";
		echo "<thead><tr><th width=\"$left_width\">Incomes (Gross)</th><th width=\"$right_width\" align=\"right\">Current year Amount<br>$curr_year</th><th width=\"$right_width\" align=\"right\">Previous Year Amount<br>$prev_year</th></tr></thead>";
		foreach ($gross_income_list_q->result() as $row)
		{
			$gross_income = new Reportlist();
			$gross_income->init($row->id);
			$gross_income->account_st_short(0);
			$gross_income_total = float_ops($gross_income_total, $gross_income->total, '+');
		        $old_gross_income_total = float_ops($old_gross_income_total, $gross_income->total2, '+');
		}
		echo "</table>";
		echo "</td>";

		/* Gross P/L : Expenses */
		$gross_expense_total = 0;
		$old_gross_expense_total = 0;

		$this->db->from('groups')->where('parent_id', 4)->where('affects_gross', 1);
		$gross_expense_list_q = $this->db->get();
		echo "<td>";
		echo "<table border=0 cellpadding=5 class=\"simple-table profit-loss-table\" width=\"100%\">";
		echo "<thead><tr><th width=\"$left_width\">Expenditure (Gross)</th><th width=\"$right_width\" align=\"right\">Current Year Amount<br>$curr_year</th><th width=\"$right_width\" align=\"right\">Previous Year Amount<br>$prev_year</th></tr></thead>";
		foreach ($gross_expense_list_q->result() as $row)
		{
			$gross_expense = new Reportlist();
			$gross_expense->init($row->id);
			$gross_expense->account_st_short(0);
			$gross_expense_total = float_ops($gross_expense_total, $gross_expense->total, '+');
		        $old_gross_expense_total = float_ops($old_gross_expense_total, $gross_expense->total2, '+');
		}
		echo "</table>";
		echo "</td>";

		$gross_income_total = -$gross_income_total; /* Converting to positive value since Cr */
		$old_gross_income_total = -$old_gross_income_total; /* Converting to positive value since Cr */

		echo "</tr>";
		echo"<tr>";

		/* Calculating Gross P/L */
		$grosspl = float_ops($gross_income_total, $gross_expense_total, '-');
		$old_grosspl = float_ops($old_gross_income_total, $old_gross_expense_total, '-');

		/* Showing Gross P/L : Incomes  */
		$grosstotal = $gross_income_total;
		$old_grosstotal = $old_gross_income_total;

		echo "<td>";
		echo "<table border=0 cellpadding=5 class=\"simple-table profit-loss-total-table\" width=\"100%\">";
		echo "<tr valign=\"top\">";
		echo "<td width=\"$left_width\" class=\"bold\">Total Gross Incomes</td>";
		echo "<td width=\"$right_width\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($gross_income_total)) . "</td>";
		echo "<td width=\"$right_width\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($old_gross_income_total)) . "</td>";
		echo "</tr>";
		if ($grosspl > 0 || $old_grosspl > 0)
		{
			echo "<tr>";
			echo "<td>&nbsp;</td>";
			echo "<td>&nbsp;</td>";
			echo "</tr>";
		} else if ($grosspl < 0 || $old_grosspl < 0) {
			$grosstotal = float_ops($grosstotal, -$grosspl, '+');
		        $old_grosstotal = float_ops($old_grosstotal, -$old_grosspl, '+');

			echo "<tr valign=\"top\">";
			echo "<td width=\"$left_width\" class=\"bold\">Gross Deficit C/O</td>";
			echo "<td width=\"$right_width\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur(-$grosspl)) . "</td>";
		        echo "<td width=\"$right_width\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur(-$old_grosspl)) . "</td>";
			echo "</tr>";
		}
		echo "<tr valign=\"top\" class=\"tr-balance\">";
		echo "<td width=\"$left_width\" class=\"bold\">Total</td>";
		echo "<td width=\"$right_width\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($grosstotal)) . "</td>";
		echo "<td width=\"$right_width\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($old_grosstotal)) . "</td>";
		echo "</tr>";
		echo "</table>";
		echo "</td>";

		/* Showing Gross P/L : Expenses */
		$grosstotal = $gross_expense_total;
		$old_grosstotal = $old_gross_expense_total;

		echo "<td>";
		echo "<table border=0 cellpadding=5 class=\"simple-table profit-loss-total-table\" width=\"100%\">";
		echo "<tr valign=\"top\">";
		echo "<td width=\"$left_width\" class=\"bold\">Total Gross Expenditure</td>";
		echo "<td width=\"$right_width\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($gross_expense_total)) . "</td>";
		echo "<td width=\"$right_width\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($old_gross_expense_total)) . "</td>";
		echo "</tr>";
		if ($grosspl > 0 || $old_grosspl > 0)
		{
			$grosstotal = float_ops($grosstotal, $grosspl, '+');
		        $old_grosstotal = float_ops($old_grosstotal, $old_grosspl, '+');

			echo "<tr valign=\"top\">";
			echo "<td width=\"$left_width\" class=\"bold\">Gross Surplus C/O</td>";
			echo "<td width=\"$right_width\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($grosspl)) . "</td>";
		        echo "<td width=\"$right_width\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($old_grosspl)) . "</td>";
			echo "</tr>";
		} else if ($grosspl < 0 || $old_grosspl < 0) {
			echo "<tr>";
			echo "<td>&nbsp;</td>";
			echo "<td>&nbsp;</td>";
			echo "</tr>";
		}
		echo "<tr valign=\"top\" class=\"tr-balance\">";
		echo "<td width=\"$left_width\" class=\"bold\">Total</td>";
		echo "<td width=\"$right_width\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($grosstotal)) . "</td>";
		echo "<td width=\"$right_width\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($old_grosstotal)) . "</td>";
		echo "</tr>";
		echo "</table>";
		echo "</td>";

		echo "</tr>";

		echo "<tr><td>&nbsp;</td><td>&nbsp;</td></tr>";

		/**********************************************************************/
		/************************* NET CALCULATIONS ***************************/
		/**********************************************************************/
		echo "<tr valign=\"top\">";

		/* Net P/L : Incomes */
		$net_income_total = 0;
		$net_old_income_total = 0;
		$this->db->from('groups')->where('parent_id', 3)->where('affects_gross !=', 1);
		$net_income_list_q = $this->db->get();
		echo "<td>";
		echo "<table border=0 cellpadding=5 class=\"simple-table profit-loss-table\" width=\"100%\">";
		echo "<thead><tr><th width=\"$left_width\">Incomes (Net)</th><th width=\"$right_width\" align=\"right\">Current Year Amount<br>$curr_year</th><th width=\"$right_width\" align=\"right\">Previous Year Amount<br>$prev_year</th></tr></thead>";
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

		/* Net P/L : Expenses */
		$net_expense_total = 0;
		$net_old_expense_total = 0;
		$this->db->from('groups')->where('parent_id', 4)->where('affects_gross !=', 1);
		$net_expense_list_q = $this->db->get();
		echo "<td>";
		echo "<table border=0 cellpadding=5 class=\"simple-table profit-loss-table\" width=\"100%\">";
		echo "<thead><tr><th width=\"$left_width\">Expenditure (Net)</th><th width=\"$right_width\" align=\"right\">Current Year Amount<br>$curr_year</th><th width=\"$right_width\" align=\"right\">Previous Year Amount<br>$prev_year</th></tr></thead>";
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

		$net_income_total = -$net_income_total; /* Converting to positive value since Cr */
		$net_old_income_total = -$net_old_income_total; /* Converting to positive value since Cr */
		echo "</tr>";

		/* Calculating Net P/L */
		$netpl = float_ops(float_ops($net_income_total, $net_expense_total, '-'), $grosspl, '+');
		$old_netpl = float_ops(float_ops($net_old_income_total, $net_old_expense_total, '-'), $old_grosspl, '+');

		echo "<tr valign=\"top\" class=\"total-area\">";

		/* Showing Net P/L : Incomes */
		$nettotal = $net_income_total;
		$net_old_total = $net_old_income_total;
		echo "<td>";
		echo "<table border=0 cellpadding=5 class=\"simple-table profit-loss-total-table\" width=\"100%\">";
		echo "<tr valign=\"top\">";
		echo "<td width=\"$left_width\" class=\"bold\">Total Incomes</td>";
		echo "<td width=\"$right_width\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($nettotal)) . "</td>";
		echo "<td width=\"$right_width\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($net_old_total)) . "</td>";
		echo "</tr>";
		if ($grosspl > 0 || $old_grosspl > 0)
		{
			$nettotal = float_ops($nettotal, $grosspl, '+');
		        $net_old_total = float_ops($net_old_total, $old_grosspl, '+');

			echo "<tr valign=\"top\">";
			echo "<td width=\"$left_width\" class=\"bold\">Gross Surplus B/F</td>";
			echo "<td width=\"$right_width\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($grosspl)) . "</td>";
		        echo "<td width=\"$right_width\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($old_grosspl)) . "</td>";
			echo "</tr>";

		} else if ($grosspl < 0 || $old_grosspl < 0) {
			echo "<tr>";
			echo "<td>&nbsp;</td>";
			echo "<td>&nbsp;</td>";
			echo "</tr>";
		}
		if ($netpl > 0 || $old_netpl > 0)
		{
			echo "<tr>";
			echo "<td>&nbsp;</td>";
			echo "<td>&nbsp;</td>";
			echo "</tr>";
		} else if ($netpl < 0 || $old_netpl < 0) {

			$nettotal = float_ops($nettotal, -$netpl, '+');
			$net_old_total = float_ops($net_old_total, -$old_netpl, '+');
			echo "<tr valign=\"top\">";
			echo "<td width=\"$left_width\" class=\"bold\">Net Deficit</td>";
			echo "<td width=\"$right_width\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur(-$netpl)) . "</td>";
			echo "<td width=\"$right_width\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur(-$old_netpl)) . "</td>";
			echo "</tr>";
		}
		echo "<tr valign=\"top\" class=\"tr-balance\">";
		echo "<td width=\"$left_width\" class=\"bold\">Total</td>";
		echo "<td width=\"$right_width\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($nettotal)) . "</td>";
		echo "<td width=\"$right_width\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($net_old_total)) . "</td>";
		echo "</tr>";
		echo "</table>";
		echo "</td>";

		/* Showing Net P/L : Expenses */
		$nettotal = $net_expense_total;
		$net_old_total = $net_old_expense_total;

		echo "<td>";
		echo "<table border=0 cellpadding=5 class=\"simple-table profit-loss-total-table\" width=\"100%\">";
		echo "<tr valign=\"top\">";
		echo "<td width=\"$left_width\" class=\"bold\">Total Expenditure</td>";
		echo "<td width=\"$right_width\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($nettotal)) . "</td>";
		echo "<td width=\"$right_width\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($net_old_total)) . "</td>";
		echo "</tr>";
		if ($grosspl > 0 || $old_grosspl > 0)
		{
			echo "<tr>";
			echo "<td>&nbsp;</td>";
			echo "<td>&nbsp;</td>";
			echo "</tr>";
		} else if ($grosspl < 0 || $old_grosspl < 0) {
			$nettotal = float_ops($nettotal, -$grosspl, '+');
		        $net_old_total = float_ops($net_old_total, -$old_grosspl, '+');

			echo "<tr valign=\"top\">";
			echo "<td width=\"$left_width\" class=\"bold\">Gross Deficit B/F</td>";
			echo "<td width=\"$right_width\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur(-$grosspl)) . "</td>";
		        echo "<td width=\"$right_width\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur(-$old_grosspl)) . "</td>";

			echo "</tr>";
		}
		if ($netpl > 0 || $old_netpl > 0)
		{
			$nettotal = float_ops($nettotal, $netpl, '+');
		        $net_old_total = float_ops($net_old_total, $old_netpl, '+');
			echo "<tr valign=\"top\">";
			echo "<td width=\"$left_width\" class=\"bold\">Net Surplus /td>";
			echo "<td width=\"$right_width\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($netpl)) . "</td>";
		        echo "<td width=\"$right_width\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($old_netpl)) . "</td>";
			echo "</tr>";
		} else if ($netpl < 0 || $old_netpl < 0) {
			echo "<tr>";
			echo "<td>&nbsp;</td>";
			echo "<td>&nbsp;</td>";
			echo "</tr>";

		}
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
		echo form_open('report/printpreview/profitandloss/');
		echo form_submit('submit', 'Print Preview');
		echo form_close();
		/*echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		echo form_open('report/download/profitandloss/');
		echo form_submit('submit', 'Download CSV');
		echo form_close();*/
	}
?>
