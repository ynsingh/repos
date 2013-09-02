<html lang="en_US">
<head>
<title>
</title>
</head>
<body>
<div id="upload">
<?php
//	echo $error;
	echo form_open_multipart('setting/logo');
	echo "<p>";
	echo form_label('Institute Name', 'ins_name');
	echo "<br />";
	echo form_input($ins_name);
	echo "</p>";
	echo form_label('Department Name', 'dept_name');
	echo "<br />";
	echo form_input($dept_name);
	echo "</p>";
	echo form_label('Unit Name', 'uni_name');
	echo "<br />";
	echo form_input($uni_name);
	echo "</p>";
	echo form_label('Institute Logo', 'logofile');
	echo "   ";
	echo form_upload("userfile");
	echo "<br />";
	echo form_submit ('upload', 'Upload');
	echo " ";
	echo anchor('setting', 'Back', 'Back to Settings');
	echo "</p>";

	echo form_close();
?>
</div>
</body>
<html>
