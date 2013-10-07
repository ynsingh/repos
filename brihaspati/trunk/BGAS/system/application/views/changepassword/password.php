<?php
	echo form_open('changepassword/index');

	echo "<p>";
	echo form_label('Old Password', 'old_password');
	echo "<br />";
	echo form_password($old_password);
	echo "</p>";
        
        echo "<p>";
	echo form_label('New Password', 'new_password');
	echo "<br />";
	echo form_password($new_password);
	echo "</p>";
        
        echo "<p>";
	echo form_label('New Password(confirm)', 'confirm_password');
	echo "<br />";
	echo form_password($confirm_password);
	echo "</p>";

        echo "<p>";
	echo form_submit('submit', 'Update');
	echo " ";
//	echo anchor('changepassword', 'Back', 'Back to changepassword');
	echo "</p>";

	echo form_close();
?>
