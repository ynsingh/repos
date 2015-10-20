<?php

	echo form_open('admin/authorities/edit/' . $authority_id);
	
    echo "<p>";
	echo form_label('Authority Name', 'authority_name');
	echo "<br />";
	echo form_input($authority_name);
	echo "</p>";

	echo "<p>";
	echo form_label('Authority Nick Name', 'authority_nickname');
	echo "<br />";
	echo form_input($authority_nickname);
	echo "</p>";

	echo "<p>";
	echo form_label('Authority Email', 'authority_email');
	echo "<br />";
	echo form_input($authority_email);
	echo "</p>";

	echo "<p>";
	echo form_submit('submit', 'Update');
	echo " ";
	echo anchor('admin/authorities', 'Back', array('title' => 'Back to Authority List'));
	echo "</p>";

	echo form_close();
?>