<?php

	echo form_open('admin/authorities/add_authority');

	echo "<p>";
	echo form_label('Authority Name', 'authority');
	echo "<br />";
	echo form_dropdown('authority' , $authority, $authority_active);
	echo "</p>";
	
	echo "<p>";
	echo form_label('User Name', 'username');
	echo "<br />";
	echo form_dropdown('username' , $username, $username_active);
	echo "</p>";

	echo "<p>";
	echo form_label('Authority Type', 'auth_type');
	echo "<br />";
	echo form_dropdown('auth_type' , $auth_type, $auth_type_active);
	echo "</p>";

	echo "<p>";
	echo form_label('From Date', 'map_date');
	echo "<br />";
	//echo form_input_date($map_date);
	echo form_input_date_restrict($map_date);
	echo "<br />";
	echo "<br />";
	echo "Please Enter Date in dd/mm/yyyy Format";
	echo "</p>";

	echo "<p>";
	echo form_label('Till Date', 'till_date');
	echo "<br />";
	//echo form_input_date($till_date);
	echo form_input_date_restrict($till_date);
	echo "<br />";
	echo "<br />";
	echo "Please Enter Date in dd/mm/yyyy Format";
	echo "</p>";

	echo "<p>";
	echo form_submit('submit', 'Add');
	echo " ";
	echo anchor('admin/authorities/auth_allocation/', 'Back', array('title' => 'Back to Authority List'));
	echo "</p>";

	echo form_close();

?>
