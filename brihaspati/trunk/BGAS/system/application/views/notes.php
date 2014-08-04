<?php
	echo form_open('ckeditor');
	
	$var = getcwd().'/docs/notesToAccount.txt';

	$myfile = fopen($var, 'r') or die("Unable to open file!");

	while(!feof($myfile)) {
	  echo fgets($myfile);
	}

	fclose($myfile);

	echo form_submit('submit', 'Edit');		
	echo form_close();
?>
