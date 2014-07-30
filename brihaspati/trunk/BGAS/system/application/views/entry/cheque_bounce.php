<html>
<head>

<script>
$(document).ready(function(){
	$("#cheque_link").hide();
	$("#link").click(function(){
		$("#cheque_link").show();
		$("#link").hide();
  	});
 });
</script>


<?php
	$this->db->select('entry_no, name, ledger_id, bank_name, update_cheque_no')->from('cheque_print')->where('entry_no', $entry_id);
        $cheque_bounce = $this->db->get();
                foreach($cheque_bounce->result() as $row)
                {
                        $ledger_id=$row->ledger_id;
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
	echo " ";
	echo form_input($bank_name);
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
        echo form_input($amount);
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

        echo form_label('Payee Name', 'beneficiary_name');
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        echo " ";
        echo form_input($beneficiary_name);
        echo"</br>";

	
	echo "<p>";
        echo form_label('Cheque Type', 'cheque_type');
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        echo form_dropdown('cheque_type', $cheque_type, $active_cheque_type, "class = \"type_dropdown\"");
        echo "</p>";
	
	echo"<span id=\"cheque_link\">";
	$i=1;
	$this->db->select('new_cheque_no')->from('cheque_bounce_record')->where('entry_no', $entry_id);
        $cheque_bounce = $this->db->get();
        foreach($cheque_bounce->result() as $row)
        {
        	$old_cheque_no = $row->new_cheque_no;
		echo"$i.";
		echo"&nbsp;&nbsp;";	
		echo"Cheque No.";
		echo"$old_cheque_no";
		echo"<br>";
		$i++;
         }
	echo"</span>";
?>

<p id="link">Click to show all Bounced Cheque</p>

<?php
	echo"<p id=\"submit\">";
        echo"<br>";
        echo form_submit('submit', 'submit');
        echo " ";
        echo"</br>";
        echo"</p>";
        echo form_close();



?>

</body>
</html>
