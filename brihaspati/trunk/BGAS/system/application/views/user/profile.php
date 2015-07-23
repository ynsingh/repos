<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<?php echo asset_url(); ?>css/profile.css">
</head>
<body>
	<div class="profile-main">

		<p> <bold><span>Welcome , <?php echo $first_name." ".$last_name ?> </span> </bold><p>
		<div class="container1">
			<!--<img src="<?php echo base_url(); ?>uploads/Profile_Pics/profile.png" width="150" height="150">
		-->
		</div>
		<div class="container2">
			<div class="heading1">
				<p>Login Information</p>
			</div>
			<div class="profile-content">
				<div class="left-content">
					<?php
						echo "<p>";
						echo "Currently Logged In As : ";
						echo "</p>";

						echo "<p>";
						echo "Current Role : ";
						echo "</p>";

						echo "<p>";
						echo "Currently Active Account : ";
						echo "</p>";

						echo "<p>";
						echo "Application version is: ";
						echo "</p>";
					?>				
				</div>
				<div class="right-content">
					<?php
					echo"<p>";
					echo "<strong>" .  $this->session->userdata('user_name') . "</strong>";
					echo " (";
					echo anchor('user/logout', 'Logout', array('title' => 'Logout', 'class' => 'anchor-link-a'));
					echo ")";
					echo"</p>";

					echo"<p>";
					echo "<strong>" .  $this->session->userdata('user_role') . "</strong>";
					echo"</p>";

					echo"<p>";
					echo "<strong>";
					if ($this->session->userdata('active_account'))
						echo $this->session->userdata('active_account');
					else
						echo "(None)";
					echo "</strong>";
					echo " (";
					echo anchor('user/account', 'Change', array('title' => 'Change Account', 'class' => 'anchor-link-a'));
					echo ")";
					echo"</p>";

					echo"<p>";
					echo "<strong>" .  $this->config->item('application_version') . "</strong>";
					echo"</p>";

					?>
				</div>
			</div>
			<div class="heading2">
				<div class="heading-col1">Other Information</div>
				<div class="heading-col2"><a href="edit_profile" title="Edit Profile"><img src="<?php echo asset_url();?>images/icons/edit.png" width="20" height="20"></a>
				</div>
			</div>
			<div class="profile-content1">
				<div class="left-content" >
					<?php
					echo "<p>";
					echo "Name";
					echo "</p>";

					echo "<p>";
					echo "Address";
					echo "</p>";

					echo "<p>";
					echo "Secondary Email";
					echo "</p>";

					echo "<p>";
					echo "Mobile No.";
					echo "</p>";

					echo "<p>";
					echo "Language";
					echo "</p>";
					
					?>
				</div>
				<div class="right-content">
					<?php
						if($profile_detail->num_rows() > 0)
						{
							foreach($profile_detail->result() as $row)
							{
								if($row->firstname != "" || $row->lastname != ""){
									echo "<p>";
									echo $row->firstname." ".$row->lastname;
									echo "</p>";
								}else{
									echo "<p>";
									echo "<br>";
									echo "</p>";
								}

								if($row->address != ""){
									echo "<p>";
									echo $row->address;
									echo "</p>";
								}else{
									echo "<p>";
									echo "<br>";
									echo "</p>";
								}

								if($row->secmail != ""){
									echo "<p>";
									echo $row->secmail;
									echo "</p>";
								}else{
									echo "<p>";
									echo "<br>";
									echo "</p>";
								}

								if($row->mobile != ""){
									echo "<p>";
									echo $row->mobile;
									echo "</p>";
								}else{
									echo "<p>";
									echo "<br>";
									echo "</p>";
								}

								if($row->lang != ""){
									echo "<p>";
									echo $row->lang;
									echo "</p>";
								}else{
									echo "<p>";
									echo "<br>";
									echo "</p>";
								}
								/*echo "<p>";
								echo $row->secmail;
								echo "</p>";

								echo "<p>";
								echo $row->mobile;
								echo "</p>";

								echo "<p>";
								echo $row->lang;
								echo "</p>";*/
							}
						}
					?>
				</div>
			</div>
		</div>
	</div>
</body>
</html>