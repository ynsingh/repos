<?php
	echo "<table width=\"100%\">";
        echo "<tr valign=\"top\">";

        echo "<td>";
        echo "<table width=\"100%\">";
        echo "<tr>";
	echo form_open_multipart('addparty/mulparty');

        echo "<td width=\"30%\">";
	echo "<p>";
        echo form_label('Upload Party File', 'partyfile');
        echo "   ";
        echo "<span id=\"tooltip-content-5\">Filr   should be txt file</span>";
        echo form_upload("userfile");
        echo "</p>";
        echo "<td>";
        echo "<td width=\"70%\">";
	echo " <b>The  file should be txt file and format of file is</b> <font color=\"blue\">party_name; party_role; party_address; account_email; mobile_number; bank_name; branch_name; bank_address; bank_account_number; ifsccode; pan_number; uid_number; tan_number; service_tax_number; vat_number; gst_number; opening_balance_type; opening_balance_amount.</font> <b>The party name and party role are mandatory.</b><br>Ex.<br>abc;faculty;;;;;;;;;;;;;;;;;;;";
        echo "<td>";
	
	echo "</tr>";
        echo "</table>";
        echo "</td>";

        echo "</tr>";
        echo "</table>";

	echo "<br />";	

	echo form_submit('upload', 'Upload');
        
?>
	<input type="button" onClick="window.history.back()" value="Back">
     	<?php echo form_close();
	echo "<br />";	
	echo "<br />";	
	if (!($Sr == "")){
		echo "<b> The result of uploaded file are :</b>";
		echo "<br />";
		foreach ($Sr as $row)
		{
			$msg_exp = explode( ":", $row );
			echo $msg_exp[0]. " . ";
			echo "&nbsp;";
			if(strpos($row,'successfully') !== false){
                      	echo "<font color=\"green\"> ".$msg_exp[1] ."</font>";
			echo "<br />";	

			}
			else{
                      	//echo $row;
                      	echo "<font color=\"red\"> ".$msg_exp[1] ."</font>";
			echo "<br />";	

			}
		}
	}
?>

