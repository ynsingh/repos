<?php
	echo "<p>";
	$user_name1=$user_name['value'];
        echo form_label('username', 'user_name');
        //echo "<br />";
        //echo form_input($user_name);
	echo ":&nbsp;&nbsp;". $user_name1;
        echo "</p>";
	$user_email1=$user_email['value'];
        echo "<p>";
        echo form_label('Email', 'user_email');
	echo ":&nbsp;&nbsp;". $user_email1;
	//echo ":&nbsp;&nbsp;". $user_email1;
        //echo "<br />";
        //echo form_input($user_email);
        echo "</p>";
        echo "<br />";
        //echo "</p>";
//echo "<p>";
echo "<table border=0 cellpadding=5 class=\"simple-table balance-sheet-table\" width=\"25%\">";
        echo "<thead><tr><th>S.No</th><th>Account Name</th><th>Assign Permission</th><th></th></tr></thead>";
		//print_r($accounts);
		$max = sizeof($accounts);
		$data=array();
		$i=1;
		$counter=0;
		while($max>0)
		{
			$max=$max-1;

			//echo "max==>".$max."<br>";

			//echo "<tr>";
			$cnt=$counter+1;
			echo "<tr>";
			echo "<td>".$cnt."</td>";
			echo "<td>".$accounts[$counter]."</td>";
			$acname=$accounts[$counter];	
			echo "<td>".anchor('admin/user/assignpermission/'.$user_name1."/".$accounts[$counter], 'Get Account Heads'); "</td>";
			//echo "<td>".anchor('admin/user/allpermission/'.$user_name1."/".$accounts[$counter], 'Assign to All'); "</td>";
			echo "<td>".anchor('admin/user/allpermission/'.$user_name1."/".$accounts[$counter]."/".$user_email1, 'Assign to All'); "</td>";
			echo "</tr>";
			$counter=$counter+1;	
		}
		
//echo "<td>" . anchor('admin/user/edit/'.$row->id, 'Edit', array('title' => 'Back to admin')); "</td>";
//print_r(array_values($accounts));
;
        echo "<tbody>";
	//echo $accounts;
echo "</table>";
echo "</p>";

        echo "<p>";
//        echo form_submit('submit', 'Update');
        echo " ";
        echo anchor('admin/user', 'Back', array('title' => 'Back to user list'));
        echo "</p>";

        echo form_close();

