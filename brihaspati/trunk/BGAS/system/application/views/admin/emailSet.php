<?php
        echo form_open('admin/emailSet');

        echo "<p>";
        echo form_label('Email Protocol', 'email_protocol');
        echo "<br />";
        echo form_dropdown('email_protocol', $email_protocol_options, $email_protocol);
        echo "</p>";

        echo "<p>";
        echo form_label('Email Hostname', 'email_host');
        echo "<br />";
        echo form_input($email_host);
        echo "</p>";

        echo "<p>";
        echo form_label('Email Port', 'email_port');
        echo "<br />";
        echo form_input($email_port);
        echo "</p>";

        echo "<p>";
        echo form_label('Email Username', 'email_username');
        echo "<br />";
        echo form_input($email_username);
        echo "</p>";

        echo "<p>";
        echo form_label('Email Password', 'email_password');
        echo "<br />";
        echo form_password($email_password);
        echo "<br />";
        echo "<span class=\"form-help-text\">Leave empty if you dont not want to change password</span>";
        echo "</p>";

        echo "<p>";
        echo form_submit('submit', 'Update');
        echo " ";
	echo anchor('admin', 'Back', array('title' => 'Back to admin'));
        echo "</p>";

        echo form_close();

