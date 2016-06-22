<?php	

	echo "<p>";
	echo "<b>You are logged in as : </b>";
	echo $this->session->userdata('user_name');
	echo "</p>";

	echo "<p align='right'>";
	echo anchor('admin', 'Back', array('title' => 'Back to admin'));
	echo "</p>";

	echo "<table border=0 cellpadding=9 class=\"simple-table manage-account-table\">";
	//new column Full Name in table added by @RAHUL
	echo "<thead><tr><th>Username</th><th>Email</th><th>Full Name</th><th>Role</th><th>Status</th><th colspan=\"2\">Account</th><th></th><th colspan=\"2\">Available action</th><th colspan=\"2\"></th></tr></thead>";
	echo "<tbody>";
	$odd_even = "odd";
	$user_id=0;
	foreach ($users->result()  as $row)
	{
		//	$ini_file = $this->config->item('config_path') . "users/" . $row . ".ini";
		/* Check if database ini file exists 
		if (get_file_info($ini_file))
		{
			/* Parsing database ini file 
			$active_users = parse_ini_file($ini_file);
			if ($active_users)
			{
				$username = isset($active_users['username']) ? $active_users['username'] : "-";
				$email = isset($active_users['email']) ? $active_users['email'] : "-";
				$accountname = isset($active_users['accounts']) ? $active_users['accounts'] : "-";
				$role = isset($active_users['role']) ? $active_users['role'] : "-";
				$status = isset($active_users['status']) ? $active_users['status'] : "-";
			}
		}*/
		$user_id=$row->id;
		$user_name1 = $row->username;
		$user_email = $row->email;
		$user_role =  $row->role;
		$user_status = $row->status;
		$user_account = $row->accounts;
		$user_type = $row->aggtype;
		$user_components = $row->componentreg;
		$v_verified = $row->isverified;

	 	echo "<tr class=\"tr-" . $odd_even;

	 	echo " tr-active";
	 	echo "\">";
	 	echo "<td>" . $user_name1 . "</td>";
	 	echo "<td>" . $user_email . "</td>";
         	echo "<td>" . $this->User_model->get_user_name($user_id) . "</td>";
	 	echo "<td>" . $user_role . "</td>";
	 	echo "<td>" . $user_status . "</td>";
	 	echo "<td>" . $user_account . "</td>";

		//added by @kanchan
		if($v_verified == 1)
		echo "<td>Verified</td>";
		else
		echo "<td>" . anchor('admin/user/verify/'.$row->id, 'Verfiy', array('title' => 'UnVerified')); "</td>";

       
    		echo "<td>" . anchor('admin/user/edit/'.$row->id, 'Edit', array('title' => 'Back to admin')); "</td>";
		if(($user_role!="guest") && ($user_name1!="admin"))
		{
    			echo "<td>" . anchor('admin/user/permission/'.$row->id, 'Assign Permission', array('title' => 'Back to admin')); "</td>";
		}else{
			echo "<td>"."</td>";
		}
		if($user_role == "guest")
		echo "<td>" ."</td> ";
    		if(($user_role=="administrator") ||($user_role=="manager")) 
		{
			if($user_type=='agg')
			{
            			echo "<td> " . anchor('admin/user/updateaggregator/'.$row->id, 'Update Aggregator', array('title' => 'Back to admin','class' => 'red-link')); "</td>";
			}
			else
			{
				echo "<td>" . anchor('admin/user/makeaggregator/'.$row->id, 'Make Aggregator', array('title' => 'Back to admin')); "</td>";
			}
		}

		if($user_components == 'BGAS'){
			echo "<td>" .anchor('admin/user/delete/'.$row->id, img(array('src' => asset_url() . "images/icons/delete.png", 'border' => '0', 'alt' => 'Delete Student')), array('class' => "confirmClick", 'title' => "You Want To Delete User From Entire System")) . "</td>";
		}else{
			echo "<td>" .anchor('admin/user/delete/'.$row->id, img(array('src' => asset_url() . "images/icons/delete.png", 'border' => '0', 'alt' => 'Delete Student')), array('class' => "confirmClick", 'title' => "Delete User")) . "</td>";
		}
//  echo "<td>" .anchor('admin/user/delete/', img(array('src' => asset_url() . "images/icons/delete.png", 'border' => '0', 'alt' => 'Delete Student')), array('class' => "confirmClick", 'title' => "Delete Student")) . "</td>";

/*	echo "<td>";
	switch ($role)
	{
		case "administrator": echo "administrator"; break;
		case "manager": echo "manager"; break;
		case "accountant": echo "accountant"; break;
		case "dataentry": echo "dataentry"; break;
		case "guest": echo "guest"; break;
		default: echo "(unknown)"; break;
	}
	echo "</td>";

	echo "<td>";
	switch ($status)
	{
		case 0: echo "Disabled"; break;
		case 1: echo "Active"; break;
		default: echo "(unknown)"; break;
	}
	echo "</td>";

	if ($this->session->userdata('user_name') == $row)
	{
		echo "<td>";
		echo anchor("admin/user/edit/" . $row, "Edit", array('title' => 'Edit User', 'class' => 'red-link'));
		echo "</td>";
	} else {
		echo "<td>";
		echo anchor("admin/user/edit/" . $row, "Edit", array('title' => 'Edit User', 'class' => 'red-link'));
		echo " &nbsp;" . anchor('admin/user/delete/' .  $row, img(array('src' => asset_url() . "images/icons/delete.png", 'border' => '0', 'alt' => 'Delete User', 'class' => "confirmClick", 'title' => "Delete User")), array('title' => 'Delete User')) . " ";
		echo "</td>";
	}*/

		echo "</tr>";
		$odd_even = ($odd_even == "odd") ? "even" : "odd";
	}
	echo "</tbody>";
	echo "</table>";
	echo "<br />";
	echo anchor('admin', 'Back', array('title' => 'Back to admin'));
?>
