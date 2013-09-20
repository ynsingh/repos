<?php
echo "<p>";
echo "<b>You are logged in as : </b>";
echo $this->session->userdata('user_name');
echo "</p>";

echo "<table border=0 cellpadding=5 class=\"simple-table manage-account-table\">";
echo "<thead><tr><th>Username</th><th>Email</th><th>Role</th><th>Status</th><th>Account</th><th></th></tr></thead>";
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


	 echo "<tr class=\"tr-" . $odd_even;

	 echo " tr-active";
	 echo "\">";
	 echo "<td>" . $user_name1 . "</td>";
         echo "<td>" . $user_email . "</td>";
         echo "<td>" . $user_role . "</td>";
         echo "<td>" . $user_status . "</td>";
	 echo "<td>" . $user_account . "</td>";
       
        echo "<td>" . anchor('admin/user/edit/'.$row->id, 'Edit', array('title' => 'Back to admin')); "</td>";
	echo "<td>" .anchor('admin/user/delete/'.$row->id, img(array('src' => asset_url() . "images/icons/delete.png", 'border' => '0', 'alt' => 'Delete Student')), array('class' => "confirmClick", 'title' => "Delete Student")) . "</td>";
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

