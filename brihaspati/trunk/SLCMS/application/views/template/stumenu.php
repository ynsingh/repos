
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
 

echo "<div>";
echo "<nav>";
echo "<ul class=\"sf-menu\">";

			echo "<li class=\"current\">";
//				echo "<a href=" . base_url() . ">Dashboard</a>";
				echo "<a href=" . site_url() ."/studenthome> Dashboard</a>";
			echo "</li>";
			echo "<li>";
				echo "<a href=" . ">Fees</a>";
				echo "<ul>";
					echo "<li>";
						echo anchor('student/feesrecord', 'Fees Record', array('title' => 'Fees Record'));
					echo "</li>";
				echo "</ul>";
				
			echo "</li>";
			echo "<li>";
			//	echo anchor('setup', 'Setup', array('title' => 'Setup'));
				echo "<a href=" . ">Subject</a>";
				echo "<ul>";
					echo "<li>";
						echo anchor('student/subjectrecord', 'Subject Record ', array('title' => 'Subject Record'));
					echo "</li>";
				echo "</ul>";
			
			echo "</li>";
			echo "<li>";
				echo "<a href=" . ">Marks</a>";
				echo "<ul>";
					echo "<li>";
						echo anchor('student/marksrecord', 'Marks Record', array('title' => 'Marks Record'));
					echo "</li>";
				echo "</ul>";
			 
			echo "</li>";
			echo "<li>";
				echo "<a href=" . ">Grade</a>";
				echo "<ul>";
					echo "<li>";
						echo anchor('student/graderecord', 'Grade Card', array('title' => 'Display Grade Card'));
					echo "</li>";
				echo "</ul>";
			 
			echo "</li>";
			echo "<li>";
				echo "<a href=" . ">Request</a>";
				echo "<ul>";
					echo "<li>";
						echo anchor('request/semesterregi', 'Semester Registration', array('title' => 'Semester registration Form'));
					echo "</li>";
					echo "<li>";
						echo anchor('student/registrationexam', 'Exam Registration', array('title' => 'Semester Exam Form'));
					echo "</li>";
					echo "<li>";
						echo anchor('student/depositfees', 'Fees deposit', array('title' => 'Semester Fees Deposit'));
					echo "</li>";
				echo "</ul>";
			
			echo "</li>";
			echo "<li>";
				echo "<a href=" . ">Download</a>";
				echo "<ul>";
					echo "<li>";
						echo anchor('student/admitcard', 'Admit Card', array('title' => 'Downlaod admit Card'));
					echo "</li>";
					echo "<li>";
						echo anchor('student/markscard', 'Marks Card', array('title' => 'Download Marks Card'));
					echo "</li>";
					echo "<li>";
						echo anchor('student/gradecard', 'Grade Card', array('title' => 'Downlaod Grade Card'));
					echo "</li>";
				echo "</ul>";
				
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

