<?php
echo "<p>";
echo "<b>You are logged in as : </b>";
echo $this->session->userdata('user_name');
echo "</p>";

echo "<table border=0 cellpadding=9 class=\"simple-table manage-account-table\">";
echo "<thead><tr><th>Authority</th><th>User Name</th><th>User Email</th><th>From Date</th><th>Till Date</th><th>Type</th><th></th><th></th></tr></thead>";
echo "<tbody>";
$odd_even = "odd";


foreach ($users->result()  as $row)
{
	$id = $row->id;
	$authority_id=$row->authority_id;
	$user_id = $row->user_id;
	$map_date = $row->map_date;
	$till_date = $row->till_date;
	$auth_type = $row->authority_type;
	if($auth_type == "full")
		$auth_type = "Full Time";
	elseif($auth_type == 'acting')
		$auth_type = "Acting";

	 echo "<tr class=\"tr-" . $odd_even;

	 echo " tr-active";
	 echo "\">";
	 echo "<td>" . $this->Authority_model->get_authority_name($authority_id) . "</td>";
	 echo "<td>" . $this->User_model->get_user_name($user_id) . "</td>";
	 echo "<td>" . $this->User_model->get_user_email($user_id) . "</td>";
	 echo "<td>" . date_mysql_to_php_display($map_date). "</td>";
	 echo "<td>" . date_mysql_to_php_display($till_date). "</td>";
	 echo "<td>" . $auth_type. "</td>";
	 echo "<td>";
	 echo anchor('admin/authorities/edit_authority/'.$id, 'Edit', array('title' => 'Edit Authority'));
     echo "</td>";
	 echo "<td>";
	 echo anchor('admin/authorities/movearchive/'.$id, 'Move To Archive', array('title' => 'Move This Entry To Archive'));
     echo "</td>";

	echo "</tr>";
	$odd_even = ($odd_even == "odd") ? "even" : "odd";
}
echo "</tbody>";
echo "</table>";
echo "<br />";
echo anchor('admin', 'Back', array('title' => 'Back to Admin'));

?>