<?php
		$entry_amount='0';
		$this->db->select('entry_no, name, amount, ledger_id, bank_name, update_cheque_no')->from('cheque_print')->where('entry_no', $entry_id);
        	$cheque_bounce = $this->db->get();
                foreach($cheque_bounce->result() as $row)
                {
                        $ledger_id=$row->ledger_id;
			$bank_name1=$row->bank_name;
                }
		//Get amount when entry has been made initially(from entry_items)...
                $this->db->select('amount')->from('entry_items')->where('entry_id', $entry_id)->where('ledger_id', $ledger_id);
                $entry_items = $this->db->get();
                foreach($entry_items->result() as $row2)
                {
                        $entry_amount=$row2->amount;
                }
                //Get bank or cash id when entry has been made initially(from entry_items)...
                $this->db->select('ledger_id')->from('entry_items')->where('entry_id', $entry_id)->where('dc', 'C');
                $ledger = $this->db->get();
                foreach($ledger->result() as $row3)
                {
                        $id=$row3->ledger_id;
                }
		 //Get name from that id from ledgers table....
                $this->db->select('name')->from('ledgers')->where('id', $id);
                $ledger_name = $this->db->get();
                foreach($ledger_name->result() as $row4)
                {
                        $led_name=$row4->name;
                }
                if($bank_name1 == Null)
                {
                        $bank_name1=$led_name;
                }


	
	echo form_open_multipart('entry/cheque_print/'. $entry_type."/".$entry_id."/".$ledger_id);
//	echo form_open_multipart('entry/cheque/'. $entry_type."/".$entry_id."/".$current_cheque);
	echo"<br>";
        echo form_label('Printed Cheque Date', 'date');
        echo "&nbsp;";
        echo " ";
        echo form_input($date);
        echo"</br>";

	echo"<br>";
	echo form_label('Bounced Cheque No', 'print_cheque_no');
        echo "&nbsp;";
        echo " ";
        echo form_input($print_cheque_no);
        echo"</br>";

	echo"<br>";
	echo form_label('Cheque Bank Name', 'bank_name');
	echo "&nbsp;&nbsp;";
        $bank_name = array(
                        'name' => 'bank_name',
                        'id' => 'bank_name',
                        'maxlength' => '255',
                        'size' => '15',
                        'value' => $bank_name1,
			'readonly'=>'readonly',
                   );
        echo form_input($bank_name);

	echo"</br>";

	echo"<br>";
	echo form_label('Payee Name', 'beneficiary_name');
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        echo " ";
        echo form_input($beneficiary_name);
        echo"</br>";


/*	echo"<br>";
        echo form_label('Cheque Bounced', 'cheque_bounce');
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp";
        echo " ";
        echo form_input($cheque_bounce);
        echo"</br>";
*/
	echo"<br>";
        echo form_label('Cheque Amount', 'amount');
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        $amount1 = array(
                        'name' => 'amount',
                        'id' => 'amount',
                        'maxlength' => '11',
                        'size' => '15',
                        'value' => $entry_amount,
                        'readonly'=>'readonly',
                );
        echo form_input($amount1);

        echo"</br>";

	echo "<p>";
	echo "<span id=\"tooltip-target-1\">";
	echo form_label('New Cheque No', 'cheque_no');
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	echo " ";
	echo form_input($cheque_no);
	echo "</span>";
	echo "<span id=\"tooltip-content-1\">For modifying the cheque no.</span>";
	echo "</p>";

	echo "<p>";
        echo form_label('Cheque Type', 'cheque_type');
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        echo form_dropdown('cheque_type', $cheque_type, $active_cheque_type, "class = \"type_dropdown\"");
        echo "</p>";
	
	echo"<p id=\"submit\">";
        echo"<br>";
	echo form_submit('submit', 'Save');
        echo " ";
	echo "&nbsp;&nbsp;&nbsp;&nbsp;";
	echo form_submit('submit', 'Display Cheque');
        echo"</p>";
        echo form_close();



?>

