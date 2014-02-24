<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<link type="text/css" rel="stylesheet" href="<?php echo asset_url(); ?>css/loading.css">
<?php
/* Dynamically adding css files from controllers 
if (isset($add_css))
{
	foreach ($add_css as $id => $row)
	{
		echo "<link type=\"text/css\" rel=\"stylesheet\" href=\"" . asset_url() . $row ."\">";
	}
}*/
?>
<script type="text/javascript">
$(document).ready(function() {
	$("#register_submit").click( function() {
	var overlay = $('<div id="overlay"></div>');
		overlay.show();
		overlay.appendTo(document.body);
		$('.popup').show();
	});
});
</script>
</head>
<body> 

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
		echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";?>
		<input type='submit' value="GET" id='register_submit'>
		<?php echo "</p>";
		echo form_close();
	}
?>

<?php
	$this->load->library('reportlist');

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
		echo "<thead><tr><th>Payment</th><th align=\"right\">Current Year Amount</th><th align=\"right\">Previous Year Amount</th></tr></thead>";
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
		echo "<thead><tr><th>Receipt</th><th align=\"right\">Current Year Amount</th><th align=\"right\">Previous Year Amount</th></tr></thead>";
	
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
<div class='popup'>
	<div class='cnt223'>
		<p>
			<img src="<?php echo base_url(); ?>images/loadingAnimation.gif" alt="loading...">
		</p>
	</div>
</div>
</body>
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
</html>
