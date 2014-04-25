<?php
	$this->load->library('session');
	$date1 = $this->session->userdata('date1');
	$date2 = $this->session->userdata('date2');
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
		echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"; ?>
		<input type='submit' value="GET" class='loading'>
		<?php echo "</p>";
		echo form_close();
	}
?>

<?php
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
		echo "<table border=0>";
		echo "<tr valign=\"top\">";

		/* Payment */	
		echo "<td width=\"" . $left_width . "\">";
		$this->db->from('groups')->where('parent_id', 4)->where('affects_gross !=', 1);
		$net_expense_list_q = $this->db->get();
		echo "<tr valign=\"top\">";
		echo "<td>";
		echo "<table border=0 cellpadding=5 class=\"simple-table profit-loss-table\" width=\"100%\">";
		echo "<thead><tr><th width=\"300\">Payment</th><th width=\"125\" align=\"right\">Current Year Amount<br>$curr_year</th><th width=\"125\" align=\"right\">Previous Year Amount<br>$prev_year</th></tr></thead>";
		foreach ($net_expense_list_q->result() as $row)
		{
			$net_expense = new Reportlist();
			$net_expense->init($row->id);
			$net_expense->account_st_short(0);
		}
		echo "</table>";
		echo "</td>";
			
		/* Receipt */
		echo "<td width=\"" . $right_width . "\">";
		echo "<table border=0 cellpadding=5 class=\"simple-table profit-loss-table\" width=\"100%\">";
		echo "<thead><tr><th width=\"300\">Receipt</th><th width=\"125\" align=\"right\">Current Year Amount<br>$curr_year</th><th width=\"125\" align=\"right\">Previous Year Amount<br>$prev_year</th></tr></thead>";
	
		$this->db->from('groups')->where('parent_id', 3)->where('affects_gross !=', 1);			
		$net_income_list_q = $this->db->get();
			
		foreach ($net_income_list_q->result() as $row)
		{
			$net_income = new Reportlist();
			$net_income->init($row->id);
			$net_income->account_st_short(0);
		}
		echo "</table>";
		echo "</td>";


		echo "</tr>";
		echo "</table>";
		echo "<br>";
	}
?>
<?php

	if ( ! $print_preview)
	{
		echo form_open('report/printpreview/paymentreceipt/');
		echo form_submit('submit', 'Print Preview');
		echo form_close();
		/*echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		echo form_open('report/download/paymentreceipt/');
		echo form_submit('submit', 'Download CSV');
		echo form_close();*/
	}
?>

