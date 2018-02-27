<?php

	echo form_open('admin/authorities/edit_authority/' . $id);

	echo "<p>";
	echo form_label('Authority Name', 'authority_id');
	echo "<br />";
	echo form_input($authority_id);
	echo "</p>";
	
	echo "<p>";
	echo form_label('User Name', 'authority_user');
	echo "<br />";
	echo form_input($authority_user);
	echo "</p>";

	echo "<p>";
	echo form_label('Authority Type', 'authority_type');
	echo "<br />";
	echo form_input($authority_type);
	echo "</p>";

	echo "<p>";
	echo form_label('From Date', 'map_date');
	echo "<br />";
	//echo form_input_date($map_date);
	//echo form_input_date_restrict($map_date);
	echo form_input($map_date);
	echo "<br />";
	echo "<br />";
	echo "Please Enter Date in dd/mm/yyyy Format";
	echo "</p>";

	echo "<p>";
	echo form_label('Till Date', 'till_date');
	echo "<br />";
	//echo form_input_date($till_date);
	//echo form_input_date_restrict($till_date);
	echo form_input($till_date);
	echo "<br />";
	echo "<br />";
	echo "Please Enter Date in dd/mm/yyyy Format";
	echo "</p>";

	echo "<p>";
	echo form_submit('submit', 'Update');
	echo " ";
	echo anchor('admin/authorities/auth_allocation/', 'Back', array('title' => 'Back to Authority List'));
	echo "</p>";

	echo form_close();
?>