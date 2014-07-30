<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Print - <?php echo $current_entry_type['name']; ?> Bill/Voucher Number <?php echo $entry_number; ?></title>
<?php echo link_tag(asset_url() . 'images/favicon.ico', 'shortcut icon', 'image/ico'); ?>
<link type="text/css" rel="stylesheet" href="<?php echo asset_url(); ?>css/printentry.css">
</head>
<body>
        <div id="print-account-name"><span class="value"><?php echo  $this->config->item('account_name'); ?></span></div>
        <div id="print-account-address"><span class="value"><?php echo $this->config->item('account_address'); ?></span></div>
	<br />
        <div id="print-entry-type"><span class="value"><?php echo $entry_type; ?> Entry</span></div>
        <br />
	 <table  width="500" id="print-entry-table"  cellpadding="30">
                <thead>
                       <tr class="tr-title"><th>Payee Name</th><th>Bank Name</th><th>Amount</th><th>Cheque No</th><th>Cheque Print Date</th></tr>
                </thead>
                <tbody>

<?php
		$new_cheque_no='';
                $this->db->select('id, name, update_cheque_no, bank_name, cheque_print_date, amount, entry_no')->from('cheque_print')->where('ledger_id', $ledger_id)->where('entry_no', $entry_id);
                $allvalue = $this->db->get();
                $no_of_row=$allvalue->num_rows();
                foreach($allvalue->result() as $row)
                {	
                        $cheque_no1 = $row->update_cheque_no;
                        $cheque_print_date =$row->cheque_print_date;
                        $bank_name=$row->bank_name;
			$amount=$row->amount;
                        $name=$row->name;
			echo"<tr>";
				echo"<td>";
				echo"$name";
				echo"</td>";
				echo"<td>";
                       	 	echo"$bank_name";
                        	echo"</td>";
				echo"<td>";
                                echo"$amount";
                                echo"</td>";
				echo"<td>";
                        	echo"$cheque_no1";
                        	echo"</td>";
				echo"<td>";
                        	echo $cheque_print_date;
                        	echo"</td>";
			echo"</tr>";
                }

 		$this->db->select('bank_name, name, new_cheque_no, amount, cheque_bounce_date')->from('cheque_bounce_record')->where('ledger_id', $ledger_id)->where('entry_no', $entry_id);
                $detail = $this->db->get();
                foreach($detail->result() as $row1)
                {
                        $bank_name = $row1->bank_name;
                        $name =$row1->name;
			$amount =$row1->amount;
                        $new_cheque_no=$row1->new_cheque_no;
			$cheque_bounce_date=$row1->cheque_bounce_date;
			echo"<tr>";
				echo"<td>";
				echo"$name";
				echo"</td>";
				echo"<td>";
                        	echo"$bank_name";
                        	echo"</td>";
				echo"<td>";
                                echo"$amount";
                                echo"</td>";
			 	echo"<td>";
				echo"$new_cheque_no";
                        	echo"</td>";
			 	echo"<td>";
                        	echo "$cheque_bounce_date";
                        	echo"</td>";
			echo"</tr>";
			
                }
?>
</tbody>
</table>
</body>
</html>
