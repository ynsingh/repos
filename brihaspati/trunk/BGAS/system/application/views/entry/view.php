<table id="entry_info">
	<tr>
	<td id="td_first">Bill/Voucher Number : <span class="bold"><?php echo full_entry_number($entry_type_id, $cur_entry->number); ?></span></td>
	<td id="td_second">Bill/Voucher Date : <span class="bold"><?php echo date_mysql_to_php_display($cur_entry->date); ?></span></td>
	</tr>

	<tr>
	<td id="td_first">Forward Reference Id : <span class="bold" align="left"><?php echo $forward_reference_id; ?></span></td>
	<td id="td_second">Backward Reference Id : <span class="bold"><?php echo $backward_reference_id; ?></span></td>
	</tr>

	<tr>
	<td id="td_first">Vendor Voucher Number : <span class="bold" align="left"><?php echo $vendor_voucher_number; ?></span></td>
	<td id="td_second">Purchase Order Number : <span class="bold"><?php echo $purchase_order_number; ?></span></td>
	</tr>

<table border=0 cellpadding=5 class="simple-table entry-view-table" width="70%">
<thead><tr><th>Type</th><th>Ledger Account</th><th>Dr Amount</th><th>Cr Amount</th><th>Party Name</th><th>Party Address</th><th>Fund</th><th>Income/Expense Type</th></tr></thead>

<?php  	if ( ! defined('BASEPATH')) exit('No direct script access allowed');
	$odd_even = "odd";
	$fund = "";
	$entry_id = "";
	$type = "";
	$fund_id = "";
	$id ="";
	foreach ($cur_entry_ledgers->result()as $row)
	{
		//$no_of_row = $cur_entry_ledgers->num_rows();
		$id = $row->ledger_id;
		$ledger_code = $this->Ledger_model->get_ledger_code($row->ledger_id);
        	//$account_code = $this->Budget_model->get_account_code('Liabilities and Owners Equity');
		$temp = $this->Ledger_model->isFund($ledger_code);
		$entry_id = $row->id;
		$dc = $row->dc;
		if ($row->dc == "D")
		{
			if(!($temp))
			{
				$temp1 = $this->Ledger_model->isFundDeduct($row->ledger_id);
				//if(!($temp1))
				//{
					$query = $this->Ledger_model->get_type1($entry_id);
					$my_values = explode('#',$query);
					$type =$my_values[0];
					$name =$my_values[1];
					echo "<tr class=\"tr-" . $odd_even . "\">";
	            			echo "<td>" . convert_dc($row->dc) . "</td>";
					//display code of ledger added by @RAHUL
			        	echo "<td>" . $this->Ledger_model->get_code($row->ledger_id).'. '.$this->Ledger_model->get_name($row->ledger_id) . "</td>";
					echo "<td>Dr " . $row->amount . "</td>";
					echo "<td></td>";
					echo "<td> " . $this->Secunit_model->get_secunitname($row->secunitid) . "</td>";
					echo "<td> " . $this->Secunit_model->get_secunitaddress($row->secunitid) . "</td>";
					echo "<td> " . $name . "</td>";
					echo "<td> " . $type . "</td>";
				//}		
		     	}
		} 
		else 
		{	
			$type = $this->Ledger_model->get_type($row->ledger_id, $entry_id);
			echo "<tr class=\"tr-" . $odd_even . "\">";
                        echo "<td>" . convert_dc($row->dc) . "</td>";
                        echo "<td>" . $this->Ledger_model->get_code($row->ledger_id).'. '.$this->Ledger_model->get_name($row->ledger_id) . "</td>";
			echo "<td></td>";
			echo "<td>Cr " . $row->amount . "</td>";
			echo "<td> " . $this->Secunit_model->get_secunitname($row->secunitid) . "</td>";
			echo "<td> " . $this->Secunit_model->get_secunitaddress($row->secunitid) . "</td>";
			echo "<td>"."</td>";
			echo "<td>".$type."</td>";
			
		}
		echo "</tr>";
		$odd_even = ($odd_even == "odd") ? "even" : "odd";
 		//	}
	}
	$this->db->select('name,bank_name,ledger_id, update_cheque_no')->from('cheque_print')->where('entry_no',$row->entry_id);
        $ledger_q = $this->db->get();
	$no_of_row=$ledger_q->num_rows();
	if($no_of_row > 0){	
	foreach($ledger_q->result() as $row)
        {
        	$bank_name = $row->bank_name;
		$ledger_id = $row->ledger_id;
                $name= $row->name;
		$cheque_no= $row->update_cheque_no;
		$cheque[] =$cheque_no;
	}	
		$length=count($cheque);
	}
?>
<tr class="entry-total"><td colspan=2><strong>Total</strong></td><td id="dr-total"> Dr <?php echo $cur_entry->dr_total; ?></td><td id="cr-total">Cr <?php echo $cur_entry->cr_total; ?></td><td></td><td></td><td></td><td></td></tr>
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
</br>
<table id="entry_info">
	<tr>
	<td id="td_first">Narration :<span class="bold"><?php echo $cur_entry->narration; ?></span></td>	
	<td id="td_second">Tag :
	<?php 
		$cur_entry_tag = $this->Tag_model->show_entry_tag($cur_entry->tag_id);
		if ($cur_entry_tag == "")
			echo "(None)";
		else
			echo $cur_entry_tag;
	?>
	</td>
	</tr>

	<tr>
	<td id="td_first">Submitted By : <span class="bold"><?php echo $submitted_by; ?></span></td>
	<td id="td_second">Verified By : <span class="bold">
	<?php
		if($verified_by == "")
		{
			echo $verified_by;
		}
		else
		{
			$nme1 = explode(",", $verified_by);
           		$i = sizeof($nme1);
                	for($j=0; $j<$i; $j++)
                	{
                		echo $nme1[$j];
                        	echo "<br>";
            		}
		}
	?></span></td>
	</tr>

	<tr>
	<td id="td_first">Approved By : <span class="bold">
	<?php 
		/*Approved field added by @RAHUL */
		$this->db->select('id')->from('bill_voucher_create')->where('entry_id',$cur_entry->id);
		$ent_ry = $this->db->get();
		$ent_ry1 = $ent_ry->row();
		if($ent_ry->num_rows() > 0)
		{
			$ent_ry2 = $ent_ry1->id;
			$e_ntr = "Approved";
			$this->db->select('authority_name')->from('bill_approval_status')->where('bill_no',$ent_ry2)->where('status',$e_ntr);
			$ent_ry3 = $this->db->get();
			if($ent_ry3->num_rows() > 0)
			{
				foreach($ent_ry3->result() as $row_3)
				{
					$e_ntr1 = $row_3->authority_name;
					$authnme = explode('/',$e_ntr1);
					$authnme1[] = $authnme[0].")";
				}
			foreach($authnme1 as $key => $value)
			{
				echo $value. "&nbsp&nbsp&nbsp&nbsp";
			}
		}
		else
		{
			echo " ";
		}
	}
	else
	{
		echo " ";
	}
	?>
</span></td>
</tr>

<tr>
<td id="td_first">Sanction Letter No. :<span class="bold"><?php echo $cur_entry->sanc_letter_no; ?></span></td>
<td id="td_second">Sanction Detail : <span class="bold">
<?php 
	$sanc_type = $cur_entry->sanc_type;
	if($sanc_type != 'select')
	{
		$sanc_value = $cur_entry->sanc_value;
		if($sanc_value != "select")
		{
			echo $cur_entry->sanc_type."  - ".$cur_entry->sanc_value;
		}
		else
		{
			echo $cur_entry->sanc_type;
		}
	}
	else
	{
		echo "";	
	}
	//echo $cur_entry->sanc_type."  - ".$cur_entry->sanc_value;
?>
</span></td>
</tr>
<tr>
<td id="td_first">Sanction Letter Date :<span class="bold">

<?php
	$sanc_date  = $cur_entry->sanc_letter_date;
	$exp_date=explode(" ",$sanc_letter_date);
	if($exp_date[0] == "0000-00-00"){
		echo" ";
	}
	else{
		echo date_mysql_to_php($sanc_date);
	}
?></span></td>
</tr>
</table>
<br/>
<?php
	if($ledger_q->num_rows() > 0)
	{
        	if( $cheque_no != NULL && $name != NULL)
         	{
                	echo "<p>";
                        echo "Bank Name :" . $bank_name . "</br>";
                        echo "</p>";
                        echo "<p>";
                        echo "Beneficiary Name :" . $name . "</br>";
                        echo "</p>";
			for($i=0; $i<$length; $i++)
                	{
				if($cheque[$i] != 1)
				{
                        		echo "<p>";
                        		echo "Cheque/DD/BT No :" . $cheque[$i] . "</br>";
                        		echo "</p>";
				}
			}
                }
        }

?>
<?php 
	echo anchor('entry/show/' . $current_entry_type['label'], 'Back', array('title' => 'Back to ' .  $current_entry_type['name'] . ' Entries'));
	echo " | ";
//	echo anchor('entry/edit/' .  $current_entry_type['label'] . "/" . $cur_entry->id, 'Edit', array('title' => 'Edit ' . $current_entry_type['name'] . ' Entry'));
//	echo " | ";
//	echo anchor('entry/delete/' . $current_entry_type['label'] . "/" . $cur_entry->id, 'Delete', array('class' => "confirmClick", 'title' => "Delete entry", 'title' => 'Delete this ' . $current_entry_type['name'] . ' Entry'));
//	echo " | ";
	echo anchor_popup('entry/printpreview/' .  $current_entry_type['label'] . "/" . $cur_entry->id, 'Print', array('title' => 'Print this ' . $current_entry_type['name'] . ' Entry', 'width' => '600', 'height' => '600'));
	echo " | ";
	echo anchor_popup('entry/email/' .  $current_entry_type['label'] . "/" . $cur_entry->id, 'Email', array('title' => 'Email this ' . $current_entry_type['name'] . ' Entry', 'width' => '400', 'height' => '200'));
	echo " | ";
	echo anchor('entry/download/' .  $current_entry_type['label'] . "/" . $cur_entry->id, 'Download', array('title' => "Download entry", 'title' => 'Download this ' . $current_entry_type['name'] . ' Entry'));
	echo " | ";
    	echo anchor_popup('entry/pdf/' .  $current_entry_type['label'] . "/" . $cur_entry->id, 'pdf', array('title' => "Download in pdf", 'title' => 'Download this ' . $current_entry_type['name'] . ' Entry'));
	if($no_of_row >=2)
	{
		echo " | ";
 		echo anchor('entry/cheque/' .  $current_entry_type['label'] . "/" . $cur_entry->id, 'Print Cheque', array('title' => 'Print this ' . $current_entry_type['name'] . ' Entry', 'width' => '600', 'height' => '600'));
	}
	elseif($no_of_row > 0)
	{
		$this->db->select('cheque_print_status, cheque_bounce_status, No_of_bounce_cheque')->from('cheque_print')->where('ledger_id', $ledger_id)->where('entry_no',$cur_entry->id);
		$cheque_status = $this->db->get();
		foreach($cheque_status->result() as $row2)
		{
	        	$cheque_print_status = $row2->cheque_print_status;
	        	$cheque_bounce_status = $row2->cheque_bounce_status;
	        	$No_of_bounce_cheque = $row2->No_of_bounce_cheque;
		}
		//Print cheque initially.........
		if($cheque_print_status == 0 && $cheque_bounce_status == 0)
		{
			echo " | ";
	        	echo anchor('entry/cheque/' .  $current_entry_type['label'] . "/" . $cur_entry->id, 'Cheque/DD/BT', array('title' => 'Print this ' . $current_entry_type['name'] . ' Entry', 'width' => '600', 'height' => '600'));
	        	echo"<br>";
		}
		//Print cheque if bounced..........
		if($cheque_print_status == 1 && $cheque_bounce_status == 0 || $cheque_print_status == 1 && $cheque_bounce_status == 1)
		{
			echo " | ";
			echo anchor('entry/cheque_bounce/' .  $current_entry_type['label'] . "/" . $cur_entry->id, 'Cancle Cheque/DD/BT', array('title' => 'Print this ' . $current_entry_type['name'] . ' Entry', 'width' => '600', 'height' => '600'));
		}
	}
?>


