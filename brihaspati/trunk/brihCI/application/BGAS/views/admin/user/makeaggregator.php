<?php
        echo form_open('admin/user/aggregatoraccounts/'.$user_name);
        echo "<p>";
        echo form_label('Username ');
        echo " :  ".$user_name;
        echo "</p>";

        echo  "<p>";
        echo form_label('User Role ');
        echo " :  ".$role;
        
        echo "</p>";
//print_r($accounts);
        echo "<p>";
        echo form_label('Select accounts - You can select Multiple account by pressing CTRL', 'accounts[]');
        echo "<br />";
        echo form_multiselect('accounts[]', $accounts);
        //echo form_multiselect('accounts[]', $accounts, $accounts_active);
        //echo form_multiselect();
        echo "</p>";

        echo "<p>";
        echo form_submit('submit', 'Submit');
        echo " ";
        echo anchor('admin/user', 'Back', array('title' => 'Back to user list'));
        echo "</p>";

        echo form_close();

