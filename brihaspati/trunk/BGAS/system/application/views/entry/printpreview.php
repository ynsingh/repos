<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
	<div id="print-entry-type"><span class="value"><?php echo $current_entry_type['name']; ?> Entry</span></div>
	<br />
	<div id="print-entry-number"><?php echo $current_entry_type['name']; ?> Bill/Voucher Number : <span class="value"><?php echo full_entry_number($entry_type_id, $entry_number); ?></span></div>
	<div id="print-entry-number"><?php echo $current_entry_type['name']; ?> Forward Reference Id : <span class="value"><?php echo $forward_ref_id; ?></span></div>
        <div id="print-entry-number"><?php echo $current_entry_type['name']; ?> Backward Reference Id : <span class="value"><?php echo $back_ref_id; ?></span></div>
	<div id="print-entry-number"><?php echo $current_entry_type['name']; ?> Bill/Voucher Date : <span class="value"><?php echo $entry_date; ?></span></div>
	<br />
	<table id="print-entry-table">
		<thead>
			<tr class="tr-title"><th>Ledger Account</th><th>Dr Amount</th><th>Cr Amount</th></tr>
		</thead>
		<tbody>
		<?php
			$currency = $this->config->item('account_currency_symbol');
			foreach ($ledger_data as $id => $row)
			{
				echo "<tr class=\"tr-ledger\">";
				if ($row['dc'] == "D")
				{
					echo "<td class=\"ledger-name item\">By " . $row['name'] . "</td>";
				} else {
					echo "<td class=\"ledger-name item\">&nbsp;&nbsp;To " . $row['name'] . "</td>";
				}
				if ($row['dc'] == "D")
				{
					echo "<td class=\"ledger-dr item\">" . $currency . " " . $row['amount'] . "</td>";
					echo "<td class=\"ledger-cr last-item\"></td>";
				} else {
					echo "<td class=\"ledger-dr item\"></td>";
					echo "<td class=\"ledger-cr last-item\">" . $currency . " " . $row['amount'] . "</td>";
				}
				echo "</tr>";
			}
			echo "<tr class=\"tr-total\"><td class=\"total-name\">Total</td><td class=\"total-dr\">" . $currency . " " .  $entry_dr_total . "</td><td class=\"total-cr\">" . $currency . " " . $entry_cr_total . "</td></tr>";
			
			$this->db->select('name,bank_name,cheque_no')->from('reconcilation')->where('entry_no',$row['id']);
         		$ledger_q = $this->db->get();
         		if ($ledger = $ledger_q->row())

		?>
		</tbody>
	</table>
	<br />
	<div id="print-entry-narration">Narration : <span class="value"><?php echo $entry_narration; ?></span></div>
	<div id="print-entry-narration">Submitted By : <span class="value"><?php echo $submitted_by; ?></span></div>
        <div id="print-entry-narration">Verified By : <span class="value"><?php echo $verified_by; ?></span></div>
	 <?php
        if( $current_entry_type['name'] == "Receipt" || $current_entry_type['name'] == "Payment" || $current_entry_type['name'] == "Contra")
        {
        echo "Bank Name :" . $ledger->name . "</br>"; 
        echo "Beneficiary Name :" . $ledger->bank_name . "</br>";
        echo "Cheque No :" . $ledger->cheque_no . "</br>";
        }
        ?>
	
	<br />
	<form>
	<input class="hide-print" type="button" onClick="window.print()" value="Print entry">
	</form>
</body>
</html>
