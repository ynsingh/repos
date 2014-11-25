<?php
	echo form_open('addparty/add');
	echo "<p>";
	echo form_label('Party Name', 'pname');
	echo "<br />";
	echo form_input($pname);
	echo "</p>";
	
	echo "<p>";
	echo "<span id=\"tooltip-target-1\">";
	echo form_label('Secondary Accounting UID', 'sacunitid');
	echo "<br />";
	echo "(Faculty - 00, Student - 01, Staff Clerical - 02, Technical staff - 03, Supplier - 04, Admin staff - 05, Contractor - 06, Service provider - 07)";
	echo "<br />";
	echo form_input($sacunitid);
	echo "</span>";
	echo "<span id=\"tooltip-content-1\">Secondary Account Unit should be ten digit and start with given code</span>";	
	echo "</p>";
	
	echo "<p>";
	echo "<span id=\"tooltip-target-2\">";
	echo form_label('Mobile Number', 'mnumber');
	echo "<br />";
	echo form_input($mnumber);
	echo "</span>";
	echo "<span id=\"tooltip-content-2\">Mobile Number should be ten digits</span>";	
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
	echo "<span id=\"tooltip-target-3\">";
        echo form_label('Bank Account Number', 'bacnumber');
        echo "<br />";
        echo form_input($bacnumber);
	echo "</span>";
	echo "<span id=\"tooltip-content-3\">Bank A/C Number should be between 13 and 17 digit</span>";	
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
	echo "<span id=\"tooltip-content-4\">IFSC code should be ten digit</span>";	
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
	echo "</p>";
	
	echo "<p>";
	echo "<span id=\"tooltip-target-6\">";
	echo form_label('TAN Number', 'tannum');
	echo "<br />";
	echo form_input($tannum);
	echo "</span>";
	echo "<span id=\"tooltip-content-6\">Tan should be 10 digits</span>";	
	echo "</p>";

	echo "<p>";
	echo "<span id=\"tooltip-target-7\">";
	echo form_label('Service Tax Number', 'stnum');
	echo "<br />";
	echo form_input($stnum);
	echo "</span>";
	echo "<span id=\"tooltip-content-7\">Service Tax Number should be 10 digit</span>";	
	echo "</p>";
	
	echo "<p>";
	echo "<span id=\"tooltip-target-8\">";
	echo form_label('VAT Number', 'vatnum');
	echo "<br />";
	echo form_input($vatnum);
	echo "</span>";
	echo "<span id=\"tooltip-content-8\">Vat Number should be 10 digit</span>";	
	echo "</p>";
	
	echo "<p>";
	echo "<span id=\"tooltip-target-9\">";
	echo form_label('GST Number', 'gstnum');
	echo "<br />";
	echo form_input($gstnum);
	echo "</span>";
	echo "<span id=\"tooltip-content-9\">GST Number should be 10 digit</span>";	
	echo "</p>";

	echo "<p>";
	echo form_label('Opening Balance', 'opbal');
	echo "<br />";
        echo form_dropdown_dc('op_balance_dc', $op_balance_dc);
        echo " ";
	echo form_input($opbal);
	echo "</p>";

	echo "<p>";
	echo form_submit('submit', 'Create');
	echo " ";
	echo anchor('addparty/add', 'Back', 'Back to addparty');
	echo "</p>";

	echo form_close();
?>
