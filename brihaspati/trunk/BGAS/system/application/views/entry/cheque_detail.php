<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<?php echo link_tag(asset_url() . 'images/favicon.ico', 'shortcut icon', 'image/ico'); ?>
<link type="text/css" rel="stylesheet" href="<?php echo asset_url(); ?>css/printentry.css">
</head>
<body>
	        <?php
                echo"<table  width=\"500\">";
                echo"<tr>";
                $this->db->select('id, name')->from('settings');
                $ins_id = $this->db->get();
                foreach( $ins_id->result() as $row)
                {
                        $row1 = $row->name;
                }
                $this->upload_path= realpath(BASEPATH.'../uploads/logo');
                $file_list = get_filenames($this->upload_path);
                if ($file_list)
                {
                        foreach ($file_list as $row2)
                        {
                                $ext = substr(strrchr($row2, '.'), 1);
                                $my_values = explode('.',$row2);
                                if($my_values[0] == $row1)
                                {
                                echo"<td>";
                                echo img(array('src' => base_url() . "uploads/logo/" . $row1.'.'.$ext));
                                echo "<br/>";
                                echo"&nbsp;&nbsp;&nbsp;&nbsp;";
                                echo $this->config->item('account_ins_name');
                                echo"</td>";
                                }
                        }
                }
                else{
                echo "<br/>";
                echo "<br/>";
                echo "<br/>";
                echo "<br/>";
                echo "<p align=\"justify\">" . "&nbsp;" . $this->config->item('account_ins_name') . "</p>";
                }
                echo"<td>";
		echo"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                echo $this->config->item('account_name');
                echo"<br>";
		echo"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                echo $this->config->item('account_address');
                echo"<br>";
                echo "Cheque Detail";
                echo"</td>";
                echo"</tr>";
                echo"</table>";
        ?>

	<br>
	<?php  echo"<font  size=\"5\">"; echo" Brihaspati General Accounting System "; ?>
	<br>
	<br>
	<?php
	 	echo"<table width=\"500\" id=\"print-entry-table\">";
                echo"<thead>";
                echo"<tr class=\"tr-title\"><th>Payee Name</th><th>Bank Name</th><th>Amount</th><th>Cheque/DD/BT No</th><th>Cheque Print Date</th></tr>";
                echo"</thead>";
                echo"<tbody>";

		$new_cheque_no='';
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

	echo"</tbody>";
	echo"</table>";

?>
	<br>
       <input class="hide-print" type="button" onClick="window.print()" value="<?php  echo"print Details";
?>" >

</body>
</html>
