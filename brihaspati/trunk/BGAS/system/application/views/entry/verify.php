<!--<p>Bill/voucher Number : <span class="bold"><?php //echo full_entry_number($entry_type_id, $cur_entry->number); ?></span>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
Bill/Voucher Date : <span class="bold"><?php //echo date_mysql_to_php_display($cur_entry->date); ?></span>
</p>

<p>Forward Reference Id : <span class="bold"><?php //echo $forward_reference_id; ?></span>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
Backward Reference Id : <span class="bold"><?php //echo $backward_reference_id; ?></span>
</p>

<p>Vendor Voucher Number : <span class="bold"><?php //echo $cur_entry->vendor_voucher_number; ?></span>
</p>-->

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
        <td id="td_first">Vendor Voucher Number : <span class="bold" align="left"><?php echo $cur_entry->vendor_voucher_number; ?></span></td>
        <td id="td_second">Purchase Order Number : <span class="bold"><?php echo $cur_entry->purchase_order_no; ?></span></td>
        </tr>

<table border=0 cellpadding=5 class="simple-table entry-view-table" width="70%">
<thead><tr><th>Type</th><th>Ledger Account</th><th>Dr Amount</th><th>Cr Amount</th><th>Party Name</th><th>Party Address</th><th>Fund</th><th>Income/Expense Type</th></tr></thead>
<?php
	$odd_even = "odd";
	$fund = "";
	$entry_id = "";
	$type = "";
	$fund_id = "";
	$id = "";
	foreach ($cur_entry_ledgers->result() as $row)
	{
		$id = $row->ledger_id;
		$ledger_code = $this->Ledger_model->get_ledger_code($row->ledger_id);
		//$account_code = $this->Budget_model->get_account_code('Liabilities and Owners Equity');
		//$temp = $this->startsWith($ledger_code, $account_code);
		//$temp = !strncmp($ledger_code, $account_code, strlen($account_code));
		$temp = $this->Ledger_model->isFund($ledger_code);
		$entry_id = $row->id;
		$dc = $row->dc;
		if($row->dc == "D")
		{
			if(!($temp))
			{
				$temp1 = $this->Ledger_model->isFundDeduct($row->ledger_id);
                                //if(!($temp1))
				//{
					$query = $this->Ledger_model->get_type1($entry_id);
                                        $my_values = explode('#',$query);
                                        $type = $my_values[0];
                                        $fund = $my_values[1];
					echo "<tr class=\"tr-" . $odd_even . "\">";
					echo "<td>" . convert_dc($row->dc) . "</td>";
                        		echo "<td>" . $this->Ledger_model->get_code($row->ledger_id).". ".$this->Ledger_model->get_name($row->ledger_id) . "</td>";
					echo "<td>Dr " . $row->amount . "</td>";
                                	echo "<td></td>";
                                	echo "<td>" . $this->Secunit_model->get_secunitname($row->secunitid) . "</td>";
                                	echo "<td>".$this->Secunit_model->get_secunitaddress($row->secunitid)."</td>";
					if($fund == '')
					{
						echo "<td></td>";
					}
					else
					{
						echo "<td> " . $fund . "</td>";
					}
					if($type == '')
					{
						echo "<td></td>";
					}
					else
					{
                                        	echo "<td> " . $type . "</td>";
					}
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
			if($fund == '')
			{
				echo "<td></td>";
			}
			else
			{
				echo "<td>" . $fund . "</td>";
			}
			if($type == '')
			{
				echo "<td></td>";
			}
			else
			{
				echo "<td>" . $type . "</td>";
			}
		}
		echo "</tr>";
		$odd_even = ($odd_even == "odd") ? "even" : "odd";
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




		
	//	if($temp)
	//	{
	//		$query = $this->Ledger_model->get_type1($entry_id);
       //               	$my_values = explode('#',$query);
       //                 $type = $my_values[0];
        //           	$fund = $my_values[1];
                	//$fund = $this->Ledger_model->get_name($row->ledger_id);
                	//$entry_id = $row->id;
                	//$type = $this->Ledger_model->get_type($row->ledger_id, $entry_id);
                	//if($type == 'Revenue')
                        	//$type = 'Revenue Expenditure';
                	//elseif($type == 'Capital')
                        	//$type = 'Capital Expenditure';
                	//elseif($type == 'Accru')
                        	//$type = 'Accrued Income';
                	//elseif($type == 'Earn')
                        	//$type = 'Earned Income';
        //	}

//		if(!($temp && $row->dc == "D"))
//		{	
//			echo "<td>" . convert_dc($row->dc) . "</td>";
//			echo "<td>" . $this->Ledger_model->get_code($row->ledger_id).". ".$this->Ledger_model->get_name($row->ledger_id) . "</td>";
//			if ($row->dc == "D")
//			{
//				echo "<td>Dr " . $row->amount . "</td>";
//				echo "<td></td>";
//				echo "<td>" . $this->Secunit_model->get_secunitname($row->secunitid) . "</td>";
//				echo "<td>".$this->Secunit_model->get_secunitaddress($row->secunitid)."</td>";
//				if(!($this->Ledger_model->isFixedAsset($ledger_code)) || $this->Ledger_model->isExpense($ledger_code))
//				{
//                                	echo "<td> " . $fund . "</td>";
//                                	echo "<td> " . $type . "</td>";
//				}
//				else
//				{
//					echo "<td></td>";
//                                	echo "<td></td>";
//				}
//			} 
//			else 
//			{
//				echo "<td></td>";
//				echo "<td>Cr " . $row->amount . "</td>";
//				echo "<td>" . $this->Secunit_model->get_secunitname($row->secunitid) . "</td>";
//				echo "<td>".$this->Secunit_model->get_secunitaddress($row->secunitid)."</td>";
//                        	echo "<td></td>";
//                        	echo "<td>" . $type . "</td>";
//			}
//			echo "</tr>";
//		}
//	}
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
        <td id="td_first">Submitted By : <span class="bold"><?php echo $cur_entry->submitted_by; ?></span></td>
        <td id="td_second">Verified By : <span class="bold"><?php echo $cur_entry->verified_by; ?></span></td>
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
<!--<p>Narration : <span class="bold"><?php //echo $cur_entry->narration; ?></span></p>-->
<!--<p>Sanction Letter No. : <span class="bold"><?php //echo $cur_entry->sanc_letter_no; ?></span></p>-->
<!--<p>Sanction Letter Date : <span class="bold">-->
<?php
/*        $sanc_date  = $cur_entry->sanc_letter_date;
        $exp_date=explode(" ",$sanc_letter_date);
        if($exp_date[0] == "0000-00-00")
	{
                echo" ";
        }
        else
	{
                echo date_mysql_to_php($sanc_date);
        }*/
?>
<!--</span></p>
<p>Sanction Detail : <span class="bold">-->
<?php  
/*	$sanc_type = $cur_entry->sanc_type;
        if($sanc_type != 'select')
	{
        	$sanc_value = $cur_entry->sanc_value;
                if($sanc_value != "")
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
        }*/
?>
<!--</span></p>-->
<?php 
	echo  anchor('entry/verifyentry/' . $current_entry_type['label'] . "/" . $cur_entry->id , "Verify", array('title' => 'Verify ' . $current_entry_type['name'] . ' Entry', 'class' => 'red-link')) ;
	echo " | ";
	echo anchor('entry/show/' . $current_entry_type['label'], 'Back', array('title' => 'Back to ' .  $current_entry_type['name'] . ' Entries'));
	echo " | ";
	echo anchor('entry/edit/' .  $current_entry_type['label'] . "/" . $cur_entry->id, 'For Modification Click here', array('title' => 'Modification ' . $current_entry_type['name'] . ' Entry'));

