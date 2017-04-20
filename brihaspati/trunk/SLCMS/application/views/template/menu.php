
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
 
echo "<div id=\"header\">";
echo "<nav>";
echo "<ul class=\"sf-menu\">";
			echo "<li class=\"current\">";
				echo "<a href=" . base_url() . "title=\"Dashboard\">Dashboard</a>";
			echo "</li>";
			echo "<li>";
				echo anchor('setup', 'Setup', array('title' => 'Setup'));
				echo "<ul>";
					echo "<li>";
						echo anchor('add/emailsetting', 'Email Setting', array('title' => 'Email Setting'));
					echo "</li>";
					echo "<li>";
						echo anchor('add/role', 'Add Role', array('title' => 'Add Role'));
					echo "</li>";
					echo "<li>";
						echo anchor('add/seatreserve', 'Add Seat Reservation', array('title' => 'Add Seat Reservation'));
					echo "</li>";
					echo "<li>";
						echo anchor('add/sc', 'Add Study center', array('title' => 'Add Study center'));
					echo "</li>";
					echo "<li>";
						echo anchor('add/dept', 'Add Department', array('title' => 'Add Department'));
					echo "</li>";				
					echo "<li>";
						echo anchor('add/program', 'Add Program', array('title' => 'Add Program'));
					echo "</li>";
					echo "<li>";
						echo anchor('add/fees', 'Add Program Fees', array('title' => 'Add Program Fees'));
					echo "</li>";
					
				echo "</ul>";
			echo "</li>";
			echo "<li>";
				echo anchor('map', 'Map', array('title' => 'Map'));
				echo "<ul>";
					echo "<li>";
						echo anchor('map/userroletype', 'Map User with Role', array('title' => 'Map User with Role'));
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
				echo anchor('upload', 'Upload', array('title' => 'Upload')); 
				echo "<ul>";
					echo "<li>";
						echo anchor('upl/uploadlogo', 'Upload Logo', array('title' => 'Upload Logo'));
					echo "</li>";
					echo "<li>";
						echo anchor('upl/uploadstulist', 'Upload Student List', array('title' => 'Upload Student List'));
					echo "</li>";
					echo "<li>";
						echo anchor('upl/uploadteachlist', 'Upload Teacher List', array('title' => 'Upload Teacher List'));
					echo "</li>";
					
					
					
				echo "</ul>";
			echo "</li>";
			echo "<li>";
				echo anchor('report', 'Report', array('title' => 'Report')); 
			echo "</li>";
			echo "<li>";
				echo anchor('profile', 'Profile', array('title' => 'Profile')); 
			echo "</li>";
echo "</ul>";
echo "</nav>";
echo "</div>";

