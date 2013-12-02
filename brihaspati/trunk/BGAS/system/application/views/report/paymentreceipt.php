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
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

	echo form_submit('submit', 'Get');
	echo " ";
	echo "</p>";
	echo form_close();
	}
?>

<?php
	$this->load->library('accountlist');
	$left_width = "450";
	$right_width = "450";

	echo "<table border=0 width=\"70%\">";
	echo "<tr valign=\"top\">";

	/* Payment */	
	echo "<td width=\"" . $left_width . "\">";
	echo "<table border=0 cellpadding=5 class=\"simple-table profit-loss-table\" width=\"100%\">";
	echo "<thead><tr><th>Payment</th><th align=\"right\">Amount</th></tr></thead>";
	if( $date1 > $date2 )
	{
	$this->messages->add('TO ENTRY DATE should be larger than ENTRY DATE FROM.', 'success');
	}
	else {
	$this->db->select('a.id, a.date, b.entry_id, b.ledger_id, b.amount, b.dc, c.id, c.group_id, c.code, d.id, d.affects_gross, d.parent_id');
	$this->db->from('entries a, entry_items b, ledgers c, groups d')->where('a.id = b.entry_id')->where('b.ledger_id = c.id')->where('c.group_id = d.id')->where('parent_id', 4)->where('affects_gross !=', 1);
	$this->db->where('date >=', $date1);
	$this->db->where('date <=', $date2);				
	$net_expense_list_q = $this->db->get();
	if( $net_expense_list_q->num_rows() < 1 )
	{
	$this->messages->add('There is no Payment statement between FROM & TO date.', 'success');
	}		
	foreach ($net_expense_list_q->result() as $row)
	{
		$net_expense = new Accountlist();
		$net_expense->init($row->id);
		$net_expense->account_st_short(0);
	}
	}
	echo "</table>";
	echo "</td>";
		
	/* Receipt */
	echo "<td width=\"" . $right_width . "\">";
	echo "<table border=0 cellpadding=5 class=\"simple-table profit-loss-table\" width=\"100%\">";
	echo "<thead><tr><th>Receipt</th><th align=\"right\">Amount</th></tr></thead>";
	
	$this->db->select('a.id, a.date, b.entry_id, b.ledger_id, b.amount, b.dc, c.id, c.group_id, c.code, d.id, d.affects_gross, d.parent_id');
	$this->db->from('entries a, entry_items b, ledgers c, groups d')->where('a.id = b.entry_id')->where('b.ledger_id = c.id')->where('c.group_id = d.id')->where('parent_id', 3)->where('affects_gross !=', 1);
	$this->db->where('date >=', $date1);
	$this->db->where('date <=', $date2);				
	$net_income_list_q = $this->db->get();
	if( $date1 > $date2 )
	{
	$this->messages->add('TO ENTRY DATE should be larger than ENTRY DATE FROM.', 'success');
	}
	else {
	if( $net_income_list_q->num_rows() < 1 )
	{
	$this->messages->add('There is no Receipt statement between FROM & TO date.', 'success');
	}			
	foreach ($net_income_list_q->result() as $row)
	{
		$net_income = new Accountlist();
		$net_income->init($row->id);
		$net_income->account_st_short(0);
	}
	}

	echo "</table>";
	echo "</td>";

	echo "</tr>";
	echo "</table>";
	echo "<br>";
        if ( ! $print_preview)
	{
	echo form_open('report/printpreview/paymentreceipt/');
	echo form_submit('submit', 'Print Preview');
	echo form_close();
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	echo form_open('report/download/paymentreceipt/');
	echo form_submit('submit', 'Download CSV');
	echo form_close();
	}
