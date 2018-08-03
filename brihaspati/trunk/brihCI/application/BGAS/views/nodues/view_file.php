<?php
 	echo form_open('ckeditor');

        $var = getcwd().'/docs/BGAS/'.$file_name;

        $myfile = fopen($var, 'r') or die("Unable to open file!");

        while(!feof($myfile)) {
          echo fgets($myfile);
        }

        fclose($myfile);
?>
