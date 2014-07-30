<?php
	$this->db->select('entry_no, name, amount, ledger_id, bank_name, update_cheque_no')->from('cheque_print')->where('entry_no', $entry_id);
        $cheque_bounce = $this->db->get();
	$no_of_row=$cheque_bounce->num_rows();
	if($no_of_row == 1)
	{
		foreach($cheque_bounce->result() as $row)
        	{
			$ledger_id=$row->ledger_id;
        	}
	echo form_open_multipart('entry/cheque_print/'. $entry_type."/".$entry_id."/". $ledger_id);
	echo"<br>";
        echo form_label('Date', 'date');
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        echo " ";
        echo form_input($date);
        echo"</br>";


	echo"<br>";
	echo form_label('Bank Name', 'bank_name');
	echo "&nbsp;&nbsp;";
	echo " ";
	echo form_input($bank_name);
	echo"</br>";

	echo"<br>";
	echo form_label('Beneficiary', 'beneficiary_name');
	echo "&nbsp;&nbsp;&nbsp;";
	echo " ";
        echo form_input($beneficiary_name);
	echo"</br>";

	echo"<br>";
        echo form_label('Amount', 'amount');
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        echo form_input($amount);
        echo"</br>";

	echo "<p>";
	echo "<span id=\"tooltip-target-1\">";
	echo form_label('Cheque No', 'cheque_no');
	echo "&nbsp;&nbsp;";
	echo " ";
	echo form_input($cheque_no);
	echo "</span>";
	echo "<span id=\"tooltip-content-1\">For modifying the cheque no.</span>";
	echo "</p>";

	
	echo "<p>";
        echo form_label('Cheque Type', 'cheque_type');
        echo "&nbsp;&nbsp;";
        echo form_dropdown('cheque_type', $cheque_type, $active_cheque_type, "class = \"type_dropdown\"");
        echo "</p>";

	echo"<p id=\"submit\">";	
	echo"<br>";
        echo form_submit('submit', 'verify');
        echo " ";
        echo"</br>";
	echo"</p>";
	echo form_close();
	
	echo"<span id=\"cheque_link\">";
	echo"</span>";
	}else{
        	echo "<table  border=0 cellpadding=6 class=\"simple-table account-table\">";
        	echo "<thead><tr><th>Cheque Status</th><th>Date</th><th>Bank Name </th><th>Payee Name</th><th>Amount</th><th>Cheque No</th><th>Cheque Type</th></tr></thead>";
        	foreach($cheque_bounce->result() as $row)
        	{
		$ledger_id=$row->ledger_id;
		$bank_name1=$row->bank_name;
		$update_cheque=$row->update_cheque_no;
		$amount=$row->amount;
		
		
		$name1=$row->name;
        	echo"<tr>";
			 $this->db->select('cheque_print_status, cheque_bounce_status')->from('cheque_print')->where('entry_no', $entry_id)->where('ledger_id', $ledger_id);
                        $ch_value = $this->db->get();
                        foreach($ch_value->result() as $row)
                        {
                                $cheque_print_status=$row->cheque_print_status;
                                $cheque_bounce_status = $row->cheque_bounce_status;

                        }
			if($cheque_print_status == 1 && $cheque_bounce_status == 1)
                        {
					$this->db->select(' new_cheque_no')->from('cheque_bounce_record')->where('entry_no', $entry_id);
                                        $cheque_bounce = $this->db->get();
                                        foreach($cheque_bounce->result() as $row3)
                                        {
                                                $old_cheque_no = $row3->new_cheque_no;
                                        }

			echo form_open_multipart('entry/cheque_print/'. $entry_type."/".$entry_id."/". $ledger_id);
			}else{
			echo form_open_multipart('entry/cheque_print/'. $entry_type."/".$entry_id."/".$ledger_id);
			}
			if($cheque_print_status == 1 || $cheque_bounce_status == 1)
			{
				echo " <td>";
                        	echo "<font color=\"red\">";
				echo anchor_popup('entry/cheque_detail/'.$entry_id.'/'. $ledger_id.'/'.$entry_type, 'Cheque Bounce Detail', array('title' => 'Print this ', 'width' => '600', 'height' => '600'));

				echo "</font>";
                        	echo"</td>";
			}else
			{
				echo " <td>";
                        	echo '<p style="color: blue; text-align: center">Cheque Not Printed</p>';
                        	echo"</td>";
			}
	
        		echo " <td>";
        		echo form_input($date);
        		echo"</td>";

        		echo "<td>";
			$bank_name = array(
                        		'name' => 'bank_name',
                        		'id' => 'bank_name',
                        		'maxlength' => '255',
                        		'size' => '15',
                        		'value' => $bank_name1,
                         );

        		echo form_input($bank_name);
        		echo"</td>";

        		echo "<td>";
			$beneficiary_name = array(
                                        'name' => 'beneficiary_name',
                                        'id' => 'beneficiary_name',
                                        'maxlength' => '255',
                                        'size' => '15',
                                        'value' => $name1,
                         );

        		echo form_input($beneficiary_name);
        		echo"</td>";

        		echo"<td>";
				 $amount1 = array(
                        		'name' => 'amount',
                       		 	'id' => 'amount',
                        		'maxlength' => '11',
		                        'size' => '15',
		                        'value' => $amount,
		                );
			echo form_input($amount1);

        		echo"</td>";
	
        		echo "<td>";
					 $cheque_no2 = array(
                                                'name' => 'cheque_no',
                                                'id' => 'cheque_no',
                                                'maxlength' => '15',
                                                'size' => '15',
                                                'value' => $update_cheque+1,
					);
        		echo form_input($cheque_no2);
        		echo "</td>";

        		echo"<td>";
        		echo form_dropdown('cheque_type', $cheque_type, $active_cheque_type, "class = \"type_dropdown\"");
        		echo "</td>";

        		echo"<td>";
        		echo"<p id=\"submit\">";
        		echo form_submit('submit', 'Verify');
        		echo"</p>";
        		echo form_close();
        		echo "</td>";
         	echo"<tr>";
        }
        	echo "</table>";

	}
?>

