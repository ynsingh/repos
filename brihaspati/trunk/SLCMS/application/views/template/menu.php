
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
 

echo "<div>";
echo "<nav>";
echo "<ul class=\"sf-menu\">";

			echo "<li class=\"current\">";
				echo "<a href=" . base_url() . ">Dashboard</a>";
			echo "</li>";
			echo "<li>";
			//	echo anchor('setup', 'Setup', array('title' => 'Setup'));
				echo "<a href=" . ">Setup</a>";
				echo "<ul>";
					echo "<li>";
						echo anchor('setup/dispemailsetting', 'Email Setting', array('title' => 'Email Setting'));
					echo "</li>";
					echo "<li>";
						echo anchor('setup/displayrole', 'Role', array('title' => 'Role'));
					echo "</li>";
					echo "<li>";
						echo anchor('setup/displaycategory', 'Category', array('title' => 'Category'));
					echo "</li>";
					echo "<li>";
						echo anchor('setup/seatreserve', 'Seat Reservation', array('title' => 'Seat Reservation'));
					echo "</li>";
					echo "<li>";
						echo anchor('setup/sc', 'Study center', array('title' => 'Study center'));
					echo "</li>";
					echo "<li>";
						echo anchor('setup/dept', 'Department', array('title' => 'Department'));
					echo "</li>";
					echo "<li>";
						echo anchor('setup/desig', 'Designation', array('title' => 'Designation'));
					echo "</li>";				
					echo "<li>";
						echo anchor('setup/viewprogram', 'Program and Seat', array('title' => 'Program and Seat'));
					echo "</li>";
					echo "<li>";
						echo anchor('setup/subject', 'Subject', array('title' => 'Subject'));
					echo "</li>";
					echo "<li>";
						echo anchor('setup/fees', 'Program Fees', array('title' => 'Program Fees'));
					echo "</li>";
				echo "</ul>";
			echo "</li>";
			echo "<li>";
				echo "<a href=" . ">Map</a>";
				echo "<ul>";
					echo "<li>";
						echo anchor('map/userroletype', 'Map User with Role', array('title' => 'Map User with Role'));
					echo "</li>";
					echo "<li>";
						echo anchor('map/seatprogramsc', 'Map Study Center and Program with Seat', array('title' => 'Map Study Center and Program with Seat'));
					echo "</li>";	
					echo "<li>";
						echo anchor('map/programsubject', 'Map Program with Subject', array('title' => 'Map Program with Subject'));
					echo "</li>";
					echo "<li>";
						echo anchor('map/subjectpaper', 'Map Subject with Paper', array('title' => 'Map Subject with Paper'));
					echo "</li>";
					echo "<li>";
						echo anchor('map/subjectteacher', 'Map Subject and Paper with Teacher', array('title' => 'Map Subject and Paper with Teacher'));
					echo "</li>";
					
					
				echo "</ul>";
			echo "</li>";
			echo "<li>";
				echo "<a href=" . ">Upload</a>";
				echo "<ul>";
					echo "<li>";
						echo anchor('upl/uploadlogo', 'Upload Logo', array('title' => 'Upload Logo'));
					echo "</li>";
					echo "<li>";
						echo anchor('upl/uploadstulist', 'Upload Student List', array('title' => 'Upload Student List'));
					echo "</li>";
					echo "<li>";
						echo anchor('upl/uploadtlist', 'Upload Teacher List', array('title' => 'Upload Teacher List'));
					echo "</li>";					
				echo "</ul>";
			echo "</li>";
			echo "<li>";
				echo "<a href=" . ">Reports</a>";
			echo "</li>";
			echo "<li>";
				echo "<a href=" . ">Profile</a>";
				 echo "<ul>";
					echo "<li>";
						echo anchor('profile/viewprofile', 'View Profile', array('title' => 'View Profile'));
					echo "</li>";
					echo "<li>";
						echo anchor('profile/changepasswd', 'Change Password', array('title' => 'Change Password'));
					echo "</li>";
					echo "<li>";
						echo anchor('home/logout', 'Logout', array('title' => 'Logout'));
					echo "</li>";							
				echo "</ul>";
			echo "</li>";
			echo "<li>";
			echo anchor('home/logout', 'Logout', array('title' => 'Logout'));
			echo "</li>";
echo "</ul>";
echo "</nav>";
echo "</div>";

