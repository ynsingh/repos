<?php

        echo form_open('admin/sqlAdmin/' );


        echo "<p>";
        echo form_label('MySQL Admin Name', 'sql_admin_name');
        echo "<br />";
        echo form_input($sql_admin_name);
        echo "</p>";



        echo "<p>";
        echo form_label('MySQL Admin Password', 'sql_admin_password');
        echo "<br />";
        echo form_password($sql_admin_password);
        echo "</p>";


        echo "<p>";
        echo form_submit('submit', 'Update');
        echo " ";
	echo anchor('admin', 'Back', array('title' => 'Back to admin'));
        echo "</p>";

        echo form_close();

