<?php
	echo "<table width=\"80%\">";
        echo "<tr valign=\"top\">";

        echo "<td>";
        echo "<table width=\"40%\">";
        echo "<tr>";
	echo form_open_multipart('addparty/add');
        
	echo "<p>";
	echo form_label('Party Name', 'pname');
	echo "<br />";
	echo form_input($pname);
	echo "</p>";
	
	echo "<p>";
	echo "<span id=\"tooltip-target-1\">";
	echo form_label('Party Category', 'partyrole');
	echo "<br />";

	echo "<br />";
	//echo form_input($sacunitid);
	echo form_dropdown('partyrole', $partyrole);

	echo "</span>";
	echo "<span id=\"tooltip-content-1\">(Faculty - 00, Student - 01, Staff Clerical - 02, Technical staff - 03, Supplier - 04, <br>Admin staff - 05, Contractor - 06, Service provider - 07, Alumni/Doner - 08,<br> Author - 09, Illustrator - 10, Participant - 11, Invites/Speaker - 12, Officers - 13,<br> Others - 14 )</span>";	
	echo "</p>";
	
	echo "<p>";
	echo "<span id=\"tooltip-target-2\">";
	echo form_label('Employee Code/Roll Number', 'pfnumber');
	echo "<br />";
	echo form_input($pfnumber);
	echo "</span>";
	echo "<span id=\"tooltip-content-2\">PF Number should be alpha numeric</span>";	
	echo "</p>";
	
	echo "<p>";
	echo form_label('Account Email', 'accountemail');
	echo "<br />";
	echo form_input($accountemail);
	echo "</p>";

	echo "<p>";
        echo form_label('Address', 'address');
        echo "<br />";
        echo form_textarea($address);
        echo "</p>";
 	
	echo "<p>";
        echo "<span id=\"tooltip-target-5\">";
        echo form_label('UID Number', 'uidnum');
        echo "<br />";
        echo form_input($uidnum);
        echo "</span>";
        echo "<span id=\"tooltip-content-5\">UID  should be 12 digits</span>";
        echo form_upload("uidfile");
        echo "<br />";
        echo "</p>";
	
	echo "<p>";
        echo "<span id=\"tooltip-target-6\">";
        echo form_label('TAN Number', 'tannum');
        echo "<br />";
        echo form_input($tannum);
        echo "</span>";
        echo "<span id=\"tooltip-content-6\">Tan should be 10 to 15 digits</span>";   
        echo "</p>";

	echo "<p>";
        echo "<span id=\"tooltip-target-9\">";
        echo form_label('GST Number', 'gstnum');
        echo "<br />";
        echo form_input($gstnum);
        echo "</span>";
        echo "<span id=\"tooltip-content-9\">GST Number should be 20 digit</span>";     
        echo "</p>";

	echo "<p>";
        echo form_label('Opening Balance', 'opbal');
        echo "<br />";
        echo form_dropdown_dc('op_balance_dc', $op_balance_dc);
        echo " ";
        echo form_input($opbal);
        echo "</p>";

	echo "</tr>";
        echo "</table>";
        echo "</td>";

        echo "<td>";
        echo "<table width=\"40%\">";
        echo "<tr>";
	
	echo "<p>";
	echo "<span id=\"tooltip-target-3\">";
        echo form_label('Bank Account Number', 'bacnumber');
        echo "<br />";
        echo form_input($bacnumber);
	echo "</span>";
	echo "<span id=\"tooltip-content-3\">Bank A/C Number should be between 13 and 20 digit</span>";	
	echo form_upload("bankfile");
        echo "<br />";
        echo "</p>";
	
	echo "<p>";
	echo form_label('Bank Name', 'bankname');
	echo "<br />";
	echo form_input($bankname);
	echo "</p>";

	echo "<p>";
	echo form_label('Branch Name', 'branchname');
	echo "<br />";
	echo form_input($branchname);
	echo "</p>";

	echo "<p>";
	echo "<span id=\"tooltip-target-4\">";
	echo form_label('Bank IFSC Code', 'ifsccode');
	echo "<br />";
	echo form_input($ifsccode);
	echo "</span>";
	echo "<span id=\"tooltip-content-4\">IFSC code should be eleven digit</span>";	
	echo "</p>";

	echo "<p>";
	echo form_label('Bank Address', 'bankaddress');
	echo "<br />";
	echo form_textarea($bankaddress);
	echo "</p>";

	echo "<p>";
	echo "<span id=\"tooltip-target-5\">";
	echo form_label('PAN Number', 'pannum');
	echo "<br />";
	echo form_input($pannum);
	echo "</span>";
	echo "<span id=\"tooltip-content-5\">Pan  should be 10 digits</span>";	
        echo form_upload("panfile");
        echo "<br />";
	echo "</p>";
	echo "<p>";
        echo "<span id=\"tooltip-target-8\">";
        echo form_label('VAT Number', 'vatnum');
        echo "<br />";
        echo form_input($vatnum);
        echo "</span>";
        echo "<span id=\"tooltip-content-8\">Vat Number should be 20 digit</span>";
        echo "</p>";
	
	echo "<p>";
	echo "<span id=\"tooltip-target-7\">";
	echo form_label('Service Tax Number', 'stnum');
	echo "<br />";
	echo form_input($stnum);
	echo "</span>";
	echo "<span id=\"tooltip-content-7\">Service Tax Number should be 15 digit</span>";	
	echo "</p>";
	
	echo "<p>";
	echo "<span id=\"tooltip-target-2\">";
	echo form_label('Mobile Number', 'mnumber');
	echo "<br />";
	echo form_input($mnumber);
	echo "</span>";
	echo "<span id=\"tooltip-content-2\">Mobile Number should be ten/thirteen digits</span>";	
	echo "</p>";

	echo "</tr>";
        echo "</table>";
        echo "</td>";

        echo "</tr>";
        echo "</table>";

	echo "<br />";	

	echo form_submit('submit', 'Create');
        
?>
	<input type="button" onClick="window.history.back()" value="Back">
     	<?php echo form_close();?>

