<?php
	echo form_open('user/edit_profile');

	echo "<p>";
	echo form_label('First Name', 'first_name');
	echo "<br />";
	echo form_input($first_name);
	echo "</p>";

	echo "<p>";
	echo form_label('Last Name', 'last_name');
	echo "<br />";
	echo form_input($last_name);
	echo "</p>";

	echo "<p>";
	echo form_label('Address', 'address');
	echo "<br />";
	echo form_input($address);
	echo "</p>";

	echo "<p>";
	echo form_label('Secondary Email ID', 'sec_mail');
	echo "<br />";
	echo form_input($sec_mail);
	echo "</p>";

	echo "<p>";
	echo form_label('Mobile No.', 'mobile');
	echo "<br />";
	echo form_input($mobile);
	echo "</p>";

	echo "<p>";
	echo form_label('Language', 'lang');
	echo "<br />";
	echo form_input($lang);
	echo "</p>";

	echo "<p>";
	echo form_submit('submit', 'Update');
	echo "</p>";

	echo form_close();