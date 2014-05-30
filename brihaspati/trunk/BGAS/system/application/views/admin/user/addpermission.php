<?php
// echo form_open('admin/user/assignpermission/'.$accountname);

        //echo form_open('group/edit/' . $group_id);
	echo form_open('admin/user/permitpermission/'.$htype."/".$id);
	//echo $id;
	//echo "<br>";
	//echo $accountname;
	//echo "<br>";
	//echo $htype;
	//echo "<br>";
//	echo $name;
	echo $name;
        echo "<p>";
        echo form_label('Account Name', 'account_name');
        echo "<br />";

        echo form_input('account_name',$accountname);
        echo "</p>";

        echo "<p>";
        echo form_label('User name', 'user_name');
        echo "<br />";


        echo form_input('user_name',$user_name);
        echo "</p>";

        echo "<p>";
        //echo form_label('Parent group', 'group_parent');
        echo form_label('Head Name', 'head_name');
        echo "<br />";
        //echo form_dropdown('group_parent', $group_parent, $group_parent_active, "class = \"group-parent\"");
        //echo form_dropdown('head_name', $head_name,  "class = \"group-parent\"");
	echo form_input('head_name',$name);
        echo "</p>";

        echo "<p class=\"affects-gross\">";
        echo "<span id=\"tooltip-target-1\">";
        //echo form_checkbox('affects_gross', 1, $affects_gross) . " Affects Gross Profit/Loss Calculations";
        echo form_checkbox('type',1) . "Cr";
        echo form_checkbox('type',2) . "Dr";
        echo form_checkbox('type',3) . "Both";
	
       /* echo "</span>";
        echo "<span id=\"tooltip-content-1\">If selected the Group account will affect Gross Profit and Loss calculations, otherwise it will affect only Net Profit and Loss calculations.</span>";*/
        echo "</p>";

        echo "<p>";
        echo form_hidden('head_type', $htype);
        echo form_hidden('id', $id);
        echo form_submit('submit', 'Submit');
        echo " ";

        //echo anchor('account', 'Back', 'Back to Chart of Accounts');
        echo "</p>";

        echo form_close();
?>

