<p>Bill/voucher Number : <span class="bold"><?php echo full_entry_number($entry_type_id, $cur_entry->number); ?></span>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
Bill/Voucher Date : <span class="bold"><?php echo date_mysql_to_php_display($cur_entry->date); ?></span>
</p>

<p>Forward Reference Id : <span class="bold"><?php echo $forward_reference_id; ?></span>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
Backward Reference Id : <span class="bold"><?php echo $backward_reference_id; ?></span>
</p>

<table border=0 cellpadding=5 class="simple-table entry-view-table">
<thead><tr><th>Type</th><th>Ledger Account</th><th>Dr Amount</th><th>Cr Amount</th></tr></thead>
<?php
foreach ($cur_entry_ledgers->result() as $row)
{
	$ledger_code = $this->Ledger_model->get_ledger_code($row->ledger_id);
        $account_code = $this->Budget_model->get_account_code('Liabilities and Owners Equity');
//        $temp = $this->startsWith($ledger_code, $account_code);
        $temp = !strncmp($ledger_code, $account_code, strlen($account_code));

	if(!($temp && $row->dc == "D")){	
		echo "<td>" . convert_dc($row->dc) . "</td>";
		echo "<td>" . $this->Ledger_model->get_name($row->ledger_id) . "</td>";
		if ($row->dc == "D")
		{
			echo "<td>Dr " . $row->amount . "</td>";
			echo "<td></td>";
		} else {
			echo "<td></td>";
			echo "<td>Cr " . $row->amount . "</td>";
		}
		echo "</tr>";
	}
}
?>
<tr class="entry-total"><td colspan=2><strong>Total</strong></td><td id=dr-total>Dr <?php echo $cur_entry->dr_total; ?></td><td id=cr-total">Cr <?php echo $cur_entry->cr_total; ?></td></tr>
<?php
if ($cur_entry->dr_total != $cur_entry->cr_total)
{
	$difference = $cur_entry->dr_total - $cur_entry->cr_total;
	if ($difference < 0)
		echo "<tr class=\"entry-difference\"><td colspan=2><strong>Difference</strong></td><td id=\"dr-diff\"></td><td id=\"cr-diff\">" . $cur_entry->cr_total . "</td></tr>";
	else
		echo "<tr class=\"entry-difference\"><td colspan=2><strong>Difference</strong></td><td id=\"dr-diff\">" .  $cur_entry->dr_total .  "</td><td id=\"cr-diff\"></td></tr>";
}
?>
</table>
<p>Narration :<span class="bold"><?php echo $cur_entry->narration; ?></span></p>

<?php 
	echo  anchor('entry/verifyentry/' . $current_entry_type['label'] . "/" . $cur_entry->id , "Verify", array('title' => 'Verify ' . $current_entry_type['name'] . ' Entry', 'class' => 'red-link')) ;
	echo " | ";
	echo anchor('entry/show/' . $current_entry_type['label'], 'Back', array('title' => 'Back to ' .  $current_entry_type['name'] . ' Entries'));
	echo " | ";
	echo anchor('entry/edit/' .  $current_entry_type['label'] . "/" . $cur_entry->id, 'For Modification Click here', array('title' => 'Modification ' . $current_entry_type['name'] . ' Entry'));

