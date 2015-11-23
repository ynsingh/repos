<p>Bill/voucher Number : <span class="bold"><?php echo full_entry_number($entry_type_id, $cur_entry->number); ?></span>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
Bill/Voucher Date : <span class="bold"><?php echo date_mysql_to_php_display($cur_entry->date); ?></span>
</p>

<p>Forward Reference Id : <span class="bold"><?php echo $forward_reference_id; ?></span>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
Backward Reference Id : <span class="bold"><?php echo $backward_reference_id; ?></span>
</p>

<p>Vendor Voucher Number : <span class="bold"><?php echo $cur_entry->vendor_voucher_number; ?></span>
</p>

<table border=0 cellpadding=5 class="simple-table entry-view-table">
<thead><tr><th>Type</th><th>Ledger Account</th><th>Dr Amount</th><th>Cr Amount</th><th>SecondaryUnit</th><th>Party Address</th><th>Fund</th><th>Income/Expense Type</th></tr></thead>
<?php
$fund = "";
$entry_id = "";
$type = "";
foreach ($cur_entry_ledgers->result() as $row)
{
	$ledger_code = $this->Ledger_model->get_ledger_code($row->ledger_id);
//        $account_code = $this->Budget_model->get_account_code('Liabilities and Owners Equity');
//        $temp = $this->startsWith($ledger_code, $account_code);
//        $temp = !strncmp($ledger_code, $account_code, strlen($account_code));
	$temp = $this->Ledger_model->isFund($ledger_code);

	if($temp){
                $fund = $this->Ledger_model->get_name($row->ledger_id);
                $entry_id = $row->id;
                $type = $this->Ledger_model->get_type($row->ledger_id, $entry_id);
                if($type == 'Revenue')
                        $type = 'Revenue Expenditure';
                elseif($type == 'Capital')
                        $type = 'Capital Expenditure';
                elseif($type == 'Accru')
                        $type = 'Accrued Income';
                elseif($type == 'Earn')
                        $type = 'Earned Income';
        }

	if(!($temp && $row->dc == "D")){	
		echo "<td>" . convert_dc($row->dc) . "</td>";
		echo "<td>" . $this->Ledger_model->get_name($row->ledger_id) . "</td>";
		if ($row->dc == "D")
		{
			echo "<td>Dr " . $row->amount . "</td>";
			echo "<td></td>";
			echo "<td>" . $this->Secunit_model->get_secunitname($row->secunitid) . "</td>";
			echo "<td></td>";
			if(!($this->Ledger_model->isFixedAsset($ledger_code)) || $this->Ledger_model->isExpense($ledger_code)){
                                echo "<td> " . $fund . "</td>";
                                echo "<td> " . $type . "</td>";

                        }else{
				 echo "<td></td>";
                                echo "<td></td>";
			}
		} else {
			echo "<td></td>";
			echo "<td>Cr " . $row->amount . "</td>";
			echo "<td>" . $this->Secunit_model->get_secunitname($row->secunitid) . "</td>";
			echo "<td></td>";
                        echo "<td></td>";
                        echo "<td></td>";
		}
		echo "</tr>";
	}
}
?>
<tr class="entry-total"><td colspan=2><strong>Total</strong></td><td id=dr-total>Dr <?php echo $cur_entry->dr_total; ?></td><td id=cr-total">Cr <?php echo $cur_entry->cr_total; ?></td><td></td><td></td><td></td><td></td></tr>
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
<p>Narration : <span class="bold"><?php echo $cur_entry->narration; ?></span></p>
<p>Sanction Letter No. : <span class="bold"><?php echo $cur_entry->sanc_letter_no; ?></span></p>
<p>Sanction Letter Date : <span class="bold"><?php
        $sanc_date  = $cur_entry->sanc_letter_date;
        $exp_date=explode(" ",$sanc_letter_date);
        if($exp_date[0] == "0000-00-00"){
                echo" ";
        }
        else{
                echo date_mysql_to_php($sanc_date);
        }
        ?></span></p>
<p>Sanction Detail : <span class="bold"><?php  $sanc_type = $cur_entry->sanc_type;
        if($sanc_type != 'select'){
                $sanc_value = $cur_entry->sanc_value;
                if($sanc_value != ""){
                        echo $cur_entry->sanc_type."  - ".$cur_entry->sanc_value;
                }else{
                        echo $cur_entry->sanc_type;
                }
        }else{
                echo "";
        }
        ?>
 
 </span></p>

<?php 
	echo  anchor('entry/verifyentry/' . $current_entry_type['label'] . "/" . $cur_entry->id , "Verify", array('title' => 'Verify ' . $current_entry_type['name'] . ' Entry', 'class' => 'red-link')) ;
	echo " | ";
	echo anchor('entry/show/' . $current_entry_type['label'], 'Back', array('title' => 'Back to ' .  $current_entry_type['name'] . ' Entries'));
	echo " | ";
	echo anchor('entry/edit/' .  $current_entry_type['label'] . "/" . $cur_entry->id, 'For Modification Click here', array('title' => 'Modification ' . $current_entry_type['name'] . ' Entry'));

