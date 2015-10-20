<?php	
echo "<p>";
echo "<b>You are logged in as : </b>";
echo $this->session->userdata('user_name');
echo "</p>";

echo "<table border=0 cellpadding=9 class=\"simple-table manage-account-table\">";
echo "<thead><tr><th>Authority Name</th><th>Authority Nick Name</th><th>Authority Email</th><th></th><th></th></tr></thead>";
echo "<tbody>";
$odd_even = "odd";
$user_id=0;
foreach ($users->result()  as $row)
{

	$authority_id=$row->id;
	$authority_name = $row->name;
	$authority_nickname = $row->nickname;
	$authority_email = $row->authority_email;

	 echo "<tr class=\"tr-" . $odd_even;

	 echo " tr-active";
	 echo "\">";
	 echo "<td>" . $authority_name . "</td>";
	 echo "<td>" . $authority_nickname . "</td>";
	 echo "<td>" . $authority_email . "</td>";
       
    echo "<td>" . anchor('admin/authorities/edit/'.$row->id, 'Edit', array('title' => 'Edit Authority'));
    echo "</td>";

	echo "<td>" .anchor('admin/authorities/delete/'.$row->id, img(array('src' => asset_url() . "images/icons/delete.png", 'border' => '0', 'alt' => 'Delete Authority')), array('class' => "confirmClick", 'title' => "You Want To Delete Authority from System")) . "</td>";
	

	echo "</tr>";
	$odd_even = ($odd_even == "odd") ? "even" : "odd";
}
echo "</tbody>";
echo "</table>";
echo "<br />";
echo anchor('admin', 'Back', array('title' => 'Back to admin'));

