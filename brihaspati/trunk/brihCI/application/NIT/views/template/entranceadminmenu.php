
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
 

echo "<div>";
echo "<nav>";
echo "<ul class=\"sf-menu\">";

			echo "<li class=\"current\">";
				echo "<a href=" . site_url() ."/entranceadminhome> Dashboard</a>";
			echo "</li>";
                        echo "<li>";
                                echo "<a href=" . ">Entrance</a>";
                                echo "<ul>";
                                      echo "<li>";
                                      echo anchor('setup/displaycontact', 'Entrance Contact Us ', array('title' => 'Entrance Contact Us'));
                                      echo "</li>";
                                      echo "<li>";
                                      echo anchor('setup/viewentranceexamfees', 'Set Entrance Exam Fees ', array('title' => 'Set Entrance Exam Fees'));
                                      echo "</li>";
                                      echo "<li>";
                                      echo anchor('enterenceadmin/examcenter', 'Set Entrance Exam Center ', array('title' => 'Exam Center'));
                                      echo "</li>";
                                      echo "<li>";
                                      echo anchor('enterence/viewadmissionopen', 'Entrance Admission Announcement ', array('title' => 'Admission open'));
                                      echo "</li>";
				     // echo "<li>";
                                     // echo anchor('enterenceadmin/viewcenterallocationlist', 'Center Allocation ', array('title' => 'Center Allocation'));
                                     // echo "</li>";
				      echo "<li>";
                                      echo anchor('enterenceadmin/fees_nonreconcile', 'Entrance Fees Reconcile ', array('title' => 'Entrance Fees Reconcile'));
                                      echo "</li>";
				      echo "<li>";
                                      echo anchor('enterenceadmin/viewcentrollno', 'Roll No Generation ', array('title' => 'Center wise roll number generation'));
                                      echo "</li>";
                                      echo "<li>";
                                      echo anchor('enterenceadmin/viewhallticket', 'Generate Hall Ticket ', array('title' => 'Generate Hall Ticket'));
                                      echo "</li>";
                                      echo "<li>";
                                      echo anchor('enterenceadmin/viewstikerlist', 'Generate Sticker ', array('title' => 'Generate Sticker'));
                                      echo "</li>";
                                      echo "<li>";
                                      echo anchor('enterenceadmin/viewattendancesheet', 'Generate Attendance Sheet ', array('title' => 'Generate Attendance Sheet'));
                                      echo "</li>";
               		         echo "</ul>";
                        echo "</li>";		
			echo "<li>";
				echo "<a href=" . ">Reports</a>";
				echo "<ul>";
					echo "<li>";
						echo anchor('report/list_application', 'Filtered Student Application List', array('title' => 'Search Student Application List'));
 					echo "</li>";
                                      echo "<li>";
                                      echo anchor('enterenceadmin/viewgraphicalreport', 'Graphical Report ', array('title' => 'Graphical Report'));
                                      echo "</li>";
                                      echo "<li>";
                                      echo anchor('enterenceadmin/viewnumericalreport', 'Numerical Report ', array('title' => 'Numerical Report'));
                                      echo "</li>";
				echo "</ul>";
			echo "</li>";
			echo "<li>";
				echo "<a href=" . ">Downloads</a>";
				echo "<ul>";
					echo "<li>";
						echo anchor('downloads/enterance_applicantdw', 'Entrance Applicant Details', array('title' => 'Entrance Applicant Details'));
					echo "</li>";
				echo "</ul>";
  			echo "</li>";

       /*          	 echo "<li>";
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
	*/
			echo "<li>";
                                echo "<a href=" . ">Help</a>";
                        echo "<ul>";
                                        echo "<li>";
                                                echo anchor('help/helpdoc', 'User Manual', array('title' => 'User Manual'));
                                        echo "</li>";
                                        echo "</ul>";

			echo "<li>";
			echo anchor('home/logout', 'Logout', array('title' => 'Logout'));
			echo "</li>";
			
echo "</ul>";
echo "</nav>";
echo "</div>";


