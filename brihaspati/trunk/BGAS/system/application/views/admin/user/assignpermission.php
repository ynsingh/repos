<?php
 echo form_open('admin/user/permitpermission/');
	echo "<p>";
        echo "Account Name=>".$accountname;
        echo "<br />";
        echo "User Name=>".$user_name;
//	print_r($accounts);
   	echo "</p>";
	$this->load->library('headlist');
	echo "<table>";
	echo "<tr valign=\"top\">";
	$head = new Headlist();
	echo "<td>";
	echo "<table border=0 cellpadding=6 class=\"simple-table account-table\">";
	echo "<thead><tr><th>Account Code </th><th>Account Name</th><th>Assign Permission</th></tr></thead>";
	$head->init($accountname,$user_name,0);
	echo"<br>";
	echo "<br>";
	echo $head->account_st_main(-1);

	$CI =& get_instance();
	echo "</table>";
	echo "</td>";
	echo "</tr>";
	echo "</table>";
	
        echo "</table>";
        echo "</td>";
        echo "</tr>";
        echo "</table>";


