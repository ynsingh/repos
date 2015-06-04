<?php
	echo form_open('user/forgotpassword');

	echo "<p>";
	echo form_label('UserName (Email)', 'user_name');
	echo "<br />";
	echo form_input($user_name);
	echo "</p>";

	echo "<p>";
	echo form_submit('submit', 'Send Mail');
//	echo "</p>";

//	echo "<p>";
//	echo "<span class=\"form-help-text\">Hint : </span>";
//	echo "</p>";
	echo " ";
        echo anchor('', 'Back', array('title' => 'Back to Login'));
        echo "</p>";

	echo form_close();
?>
<?php	echo "</p>";



