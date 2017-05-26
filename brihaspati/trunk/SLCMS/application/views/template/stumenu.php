
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
				echo "<a href=" . ">Request</a>";
				echo "<ul>";
					echo "<li>";
						echo anchor('setup/dispemailsetting', 'Semester Registration', array('title' => 'Email Setting'));
					echo "</li>";
				echo "</ul>";
			
			echo "</li>";
			echo "<li>";
				echo "<a href=" . ">Fees</a>";
			/*	echo "<ul>";
					echo "<li>";
						echo anchor('map/userroletype', 'Map User with Role', array('title' => 'Map User with Role'));
					echo "</li>";
				echo "</ul>";
				*/
			echo "</li>";
			echo "<li>";
			//	echo anchor('setup', 'Setup', array('title' => 'Setup'));
				echo "<a href=" . ">Subject</a>";
			/*	echo "<ul>";
					echo "<li>";
						echo anchor('setup/dispemailsetting', 'Email Setting', array('title' => 'Email Setting'));
					echo "</li>";
				echo "</ul>";
			*/
			echo "</li>";
			echo "<li>";
				echo "<a href=" . ">Fees</a>";
			/*	echo "<ul>";
					echo "<li>";
						echo anchor('map/userroletype', 'Map User with Role', array('title' => 'Map User with Role'));
					echo "</li>";
				echo "</ul>";
				*/
			echo "</li>";
			echo "<li>";
				echo "<a href=" . ">Marks</a>";
			/*	echo "<ul>";
					echo "<li>";
						echo anchor('upl/uploadlogo', 'Upload Logo', array('title' => 'Upload Logo'));
					echo "</li>";
				echo "</ul>";
			 */
			echo "</li>";
			echo "<li>";
				echo "<a href=" . ">Grade</a>";
			/*	echo "<ul>";
					echo "<li>";
						echo anchor('upl/uploadlogo', 'Upload Logo', array('title' => 'Upload Logo'));
					echo "</li>";
				echo "</ul>";
			 */
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

