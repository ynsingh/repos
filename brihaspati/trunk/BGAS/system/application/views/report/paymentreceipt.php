<?php  if ( ! defined('BASEPATH')) exit('No direct script access allowed');
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
	$tot_op_bal='';
	$this->load->library('Paymentreceipt');
	$this->load->library('session');
	$date1 = $this->session->userdata('date1');
	$date2 = $this->session->userdata('date2');
	$cl_bal_bankcash = $this->session->userdata('cl_bal_bankcash');
	$this->db->from('settings');
	$value = $this->db->get();
	foreach($value->result() as $row) 
	{
		$fy_start=explode("-",$row->fy_start);
		$fy_end=explode("-",$row->fy_end);
	}
		$curr_year = '('.$fy_start[0] ."-" .$fy_end[0] .')';
		$prev_year = '(' . ($fy_start[0]-1) ."-" . ($fy_end[0]-1) .')';
        	$this->db->from('ledgers')->where('type', '1');
        	$op_balance = $this->db->get();
        	foreach ($op_balance->result() as $row)
			{
        		list ($opbalance, $optype) = $this->Ledger_model->get_op_balance($row->id); /* Opening Balance */
			$ledbalance = $this->Ledger_model->get_ledger_balance1($row->id); /* Ledger Balance */
        		if($optype == 'C')
			{
        			$opbalance=-$opbalance;
        		}
        			$tot_op_bal=$tot_op_bal+$opbalance;
        		}

	/* check for dates */
	if($date1 > $date2)
	{
		$this->messages->add('TO ENTRY DATE should be larger than ENTRY DATE FROM.', 'success');
	}
	else {
		//Previous code....
		/*$net_income_total = 0;
		$net_old_income_total = 0;
		$net_expense_total = 0;
		$net_old_expense_total = 0;
		$this->db->from('groups')->where('parent_id', 4)->where('affects_gross !=', 1);
		$net_expense_list_q = $this->db->get();*/
		/* Net P/L : Payment */
		$net_payment_total = 0;
		$net_prev_payment_total=0;
		
		/* Net P/L : Receipt */
                $net_receipt_total = 0;
		$net_prev_receipt_total=0;
		
		echo "<table>";
        	echo "<tr valign=\"top\">";
		// for receipts side
		echo "<td>";
		echo "<table border=0 cellpadding=5 class=\"simple-table profit-loss-table\" width=\"100%\">";
		echo "<thead><tr><th width=\"$left_width\">Receipt (Net)</th><th width=\"$right_width\" align=\"right\">Current Year Amount<br>$curr_year</th><th width=\"$right_width\" align=\"right\">Previous Year Amount<br>$prev_year</th></tr></thead>";
		echo "<tr class=\"tr-balance\"><td class=\"bold\" cellpadding=5>Bank Or Cash Opening Balance</td><td align=\"right\" class=\"bold\">" . convert_amount_dc($tot_op_bal) . "</td></tr>";
			$receipt = new Paymentreceipt();
                	$receipt->payment_receipt('Receipt', "view","NULL");
			$net_receipt_total = float_ops($net_receipt_total, $receipt->total, '+');
			$net_prev_receipt_total = float_ops($net_prev_receipt_total, $receipt->prev_total, '+');
		echo "</table>";
		echo "</td>";

		//for Payment side....
		echo "<td>";
		echo "<table border=0 cellpadding=5 class=\"simple-table profit-loss-table\" width=\"100%\">";
		echo "<thead><tr><th width=\"$left_width\">Payment (Net)</th><th width=\"$right_width\" align=\"right\">Current Year Amount<br>$curr_year</th><th width=\"$right_width\" align=\"right\">Previous Year Amount<br>$prev_year</th></tr></thead>";
                        $payment = new Paymentreceipt();
                        $payment->payment_receipt('Payment', "view","NULL");
                        $net_payment_total = float_ops($net_payment_total, $payment->total, '+');
			$net_prev_payment_total = float_ops($net_prev_payment_total, $payment->prev_total, '+');
			echo "<tr class=\"tr-balance\"><td class=\"bold\" cellpadding=5>Bank Or Cash Closing Balance</td><td align=\"right\" class=\"bold\">" . convert_amount_dc($ledbalance) . "</td></tr>";  
			echo "</table>";
			echo "</td>";//end of payment side....
			echo "</tr>";
		//Previous code
		/*$net_income_total = -$net_income_total; /* Converting to positive value since Cr */
		/*$net_old_income_total = -$net_old_income_total; /* Converting to positive value since Cr */

		/* Showing Net P/L : Payment */
		//$nettotal = $net_expense_total;
		//$net_old_total = $net_old_expense_total;
		//closing of bank and cash account....
		/*echo "<tr valign=\"top\" class=\"total-area\">";
		echo "<td>";
                echo "<table border=0 cellpadding=5 class=\"simple-table profit-loss-total-table\" width=\"100%\">";
                echo "<tr valign=\"top\" class=\"tr-balance\">";
                echo "<td width=\"$left_width\" class=\"bold\">Bank Or Cash Closing Balance</td>";
                echo "<td></td>";
                echo "<td></td>";
                echo "</tr>";
                echo "</table>";
                echo "</td>";

		echo "<td>";
                echo "<table border=0 cellpadding=5 class=\"simple-table profit-loss-total-table\" width=\"100%\">";
                echo "<tr valign=\"top\" class=\"tr-balance\">";
                echo "<td></td>";
                echo "<td></td>";
                echo "<td align=\"right\" class=\"bold\">".convert_amount_dc($ledbalance)."</td>";
                echo "</tr>";
                echo "</table>";
                echo "</td>";

		echo"</tr>";*/
		//end....

		//for Total balance....

		$net_receipt_total = $tot_op_bal + $net_receipt_total;
		$net_payment_total = $net_payment_total + $ledbalance;
		echo "<tr valign=\"top\" class=\"total-area\">";
		echo "<td>";
		echo "<table border=0 cellpadding=5 class=\"simple-table profit-loss-total-table\" width=\"100%\">";
		echo "<tr valign=\"top\" class=\"tr-balance\">";
		echo "<td width=\"$left_width\" class=\"bold\">Total</td>";
		echo "<td width=\"$right_width\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($net_receipt_total)) . "</td>";
		echo "<td width=\"$right_width\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($net_prev_receipt_total)) . "</td>";
		echo "</tr>";
		echo "</table>";
		echo "</td>";

		echo "<td>";
		echo "<table border=0 cellpadding=5 class=\"simple-table profit-loss-total-table\" width=\"100%\">";
		echo "<tr valign=\"top\" class=\"tr-balance\">";
		echo "<td width=\"$left_width\" class=\"bold\">Total</td>";
		echo "<td width=\"$right_width\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($net_payment_total)) . "</td>";
		echo "<td width=\"$right_width\" align=\"right\" class=\"bold\">" . money_format('%!i', convert_cur($net_prev_payment_total)) . "</td>";
		echo "</tr>";
		echo "</table>";
		echo "</td>";
		echo "</tr>";//end of Total balance....    */
		echo "</table>"; 
	}
	if(! $print_preview)
	{
		echo form_open('report/printpreview/paymentreceipt/');
		echo form_submit('submit', 'Print Preview');
		echo form_close();
	} 
?>
