<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Email - <?php echo $current_entry_type['name']; ?> Bill/Voucher Number <?php echo $entry_number; ?></title>
</head>
<body>
	<p><?php echo $this->config->item('account_name'); ?></p>
	<p><?php echo $this->config->item('account_address'); ?></p>
	<p><strong><?php echo $current_entry_type['name']; ?> Entry</strong></p>
	<p><?php echo $current_entry_type['name']; ?> Bill/Voucher Number : <strong><?php echo full_entry_number($entry_type_id, $entry_number); ?></strong></p>
	<p><?php echo $current_entry_type['name']; ?> Forward Reference Id : <strong><?php echo $forward_ref_id; ?></strong></p>
        <p><?php echo $current_entry_type['name']; ?> Backward Reference Id : <strong><?php echo $back_ref_id; ?></strong></p>
	<p><?php echo $current_entry_type['name']; ?> Bill/Voucher Date : <strong><?php echo $entry_date; ?></strong></p>
	<p><?php echo $current_entry_type['name']; ?> Vendor/Voucher No : <strong><?php echo $vendor_voucher_number; ?></strong></p>
	<table border=1 cellpadding=6>
		<thead>
			<tr><th align="left">Ledger Account</th><th>Dr Amount</th><th>Cr Amount</th></tr>
		</thead>
		<tbody>
		<?php
			$currency = $this->config->item('account_currency_symbol');
			foreach ($ledger_data as $id => $row)
			{
				echo "<tr>";
				if ($row['dc'] == "D")
				{
					echo "<td>By " . $row['name'] . "</td>";
				} else {
					echo "<td>&nbsp;&nbsp;To " . $row['name'] . "</td>";
				}
				if ($row['dc'] == "D")
				{
					echo "<td>" . $currency . " " . $row['amount'] . "</td>";
					echo "<td></td>";
				} else {
					echo "<td></td>";
					echo "<td>" . $currency . " " . $row['amount'] . "</td>";
				}
				echo "</tr>";
			}
			echo "<tr><td>Total</td><td>" . $currency . " " .  $entry_dr_total . "</td><td>" . $currency . " " . $entry_cr_total . "</td></tr>";
		?>
		  <?php
        $cheque='';
        $this->db->select('name,bank_name,update_cheque_no')->from('cheque_print')->where('entry_no',$entry_number);
        $ledger_q = $this->db->get();
        foreach($ledger_q->result() as $row)
        {
            $bank_name = $row->bank_name;
            $bank[] =$bank_name;
            $name= $row->name;
            $benif_name[] =$name;
            $cheque_no=$row->update_cheque_no;
            $cheque[] =$cheque_no;
        }
        $length=count($cheque);

        ?>

		</tbody>
	</table>
	 <?php
        if($ledger_q->num_rows() > 0){
            if( $cheque_no != NULL && $name != NULL)
                {
                for($i=0; $i<$length; $i++)
                {
                    if($cheque[$i] != 1){
                  //  echo"<br>";
                    echo"Bank Name : " . $bank[$i] . "<br>";
                    echo"Beneficiary Name : " . $benif_name[$i] . "<br>";
                    echo"Cheque No : " . $cheque[$i] . "<br>";
                    }
                }
                }
        }
        ?>

	<br />
	<p>Narration : <span class="value"><?php echo $entry_narration; ?></p>
	<br />
</body>
</html>
