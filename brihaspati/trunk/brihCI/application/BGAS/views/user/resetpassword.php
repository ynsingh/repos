<?php
	echo form_open('user/changepasswd');

	echo "<p>";
	echo form_label('User Name (Email)', 'user_name');
	echo "<br />";
	echo form_input($user_name);
	echo "</p>";

	echo "<p>";
        echo form_label('Password', 'password');
        echo "<br />";
        echo form_password($password);
        echo "</p>";

	echo "<p>";
        echo form_label('Confirm Password', 'cnfpassword');
        echo "<br />";
        echo form_password($cnfpassword);
        echo "</p>";


	echo "<p>";
	echo form_submit('submit', 'Reset Password');
//	echo "</p>";

//	echo "<p>";
//	echo "<span class=\"form-help-text\">Hint : </span>";
//	echo "</p>";
        echo "</p>";

	echo form_close();
?>
<?php	echo "</p>";



