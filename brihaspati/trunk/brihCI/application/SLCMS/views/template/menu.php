
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
 

echo "<div>";
echo "<nav>";
echo "<ul class=\"sf-menu\">";

			echo "<li class=\"current\">";
//				echo "<a href=" . base_url() . ">Dashboard</a>";
				echo "<a href=" . site_url() ."/home> Dashboard</a>";
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
						echo anchor('setup/dispseatsetting', 'Seat Reservation', array('title' => 'Seat Reservation'));
					echo "</li>";
					echo "<li>";
						echo anchor('setup/viewsc', 'Study center', array('title' => 'Study center'));
					echo "</li>";
					echo "<li>";
						echo anchor('setup/dispdepartment', 'Department', array('title' => 'Department'));
					echo "</li>";
					echo "<li>";
						echo anchor('setup2/designation', 'Designation', array('title' => 'Designation'));
					echo "</li>";				
					echo "<li>";
						echo anchor('setup2/authority', 'Authority', array('title' => 'Authority'));
					echo "</li>";				
					echo "<li>";
						echo anchor('setup/viewprogramcat', 'Program Category', array('title' => 'Program Category'));
					echo "</li>";
					echo "<li>";
						echo anchor('setup/viewprogram', 'Program and Seat', array('title' => 'Program and Seat'));
					echo "</li>";
					echo "<li>";
						echo anchor('setup/viewsubject', 'Subject', array('title' => 'Subject'));
					echo "</li>";
					echo "<li>";
						echo anchor('setup/displayfees', 'Program Fees', array('title' => 'Program Fees'));
					echo "</li>";
					echo "<li>";
						echo anchor('setup2/examtype', 'Exam Types', array('title' => 'Exam Types'));
					echo "</li>";
					echo "<li>";
						echo anchor('setup2/degreerules', 'Degree Rules', array('title' => 'Degree Rules'));
					echo "</li>";
					echo "<li>";
						echo anchor('setup2/semesterrules', 'Semester Rules', array('title' => 'Semester Rules'));
					echo "</li>";
					echo "<li>";
						echo anchor('setup2/grademaster', 'Master Grade', array('title' => 'Master Grade'));
					echo "</li>";
				echo "</ul>";
			echo "</li>";
			echo "<li>";
				echo "<a href=" . ">Map</a>";
				echo "<ul>";
					echo "<li>";
						echo anchor('map/viewuserrole', 'Map User with Role', array('title' => 'Map User with Role'));
					echo "</li>";
					echo "<li>";
						echo anchor('map/viewuserauthority', 'Map User with Authority', array('title' => 'Map User with Authority'));
					echo "</li>";
					echo "<li>";
						echo anchor('map/viewscprgseat', 'Map Study Center and Program with Seat', array('title' => 'Map Study Center and Program with Seat'));
					echo "</li>";	
					echo "<li>";
						echo anchor('map/programsubject', 'Map Program with Subject and paper', array('title' => 'Map Program with Subject and paper'));
					echo "</li>";
					echo "<li>";
						echo anchor('map/listsubjectteacher', 'Map Subject and Paper with Teacher', array('title' => 'Map Subject and Paper with Teacher'));
					echo "</li>";
					echo "<li>";
						echo anchor('map/prerequisite', 'Map Subject and Paper with Prerequisite', array('title' => 'Map Subject and Paper with Prerequisite'));
					echo "</li>";
					echo "<li>";
						echo anchor('map/subjectsemester', 'Map Subject and Semester', array('title' => 'Map Subject and Semester'));
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
						echo anchor('upl/uploadstumerit', 'Upload Student Admission Merit List', array('title' => 'Upload Student Admission Merit List'));
					echo "</li>";
				/*	echo "<li>";
						echo anchor('upl/uploadstulist', 'Upload Student List', array('title' => 'Upload Student List'));
					echo "</li>";*/
				 	echo "<li>";
						echo anchor('upl/uploadtlist', 'Upload Teacher List', array('title' => 'Upload Teacher List'));
					echo "</li>";
     				       /* echo "<li>";
                                                echo anchor('upl/uploadslist', 'Upload Staff List', array('title' => 'Upload Staff List'));
                                        echo "</li>";	*/				
				echo "</ul>";
			echo "</li>";
			echo "<li>";
				echo "<a href=" . ">Reports</a>";
				echo "<ul>";
					echo "<li>";
						echo anchor('report/liststu', 'Student List', array('title' => 'Student List'));
					echo "</li>";
					echo "<li>";
						echo anchor('report/listfac', 'Faculty List', array('title' => 'Faculty List'));
					echo "</li>";
					echo "<li>";
						echo anchor('report/liststaff', 'Staff List', array('title' => 'Staff List'));
					echo "</li>";
					echo "<li>";
						echo anchor('report/admission_meritlist', 'Admission Merit List', array('title' => 'Admission Merit List'));
					echo "</li>";					
				echo "</ul>";
			echo "</li>";
			echo "<li>";
				echo "<a href=" . ">Result</a>";
				echo "<ul>";
					echo "<li>";
						echo anchor('result/resultdeclared', 'Result Declared', array('title' => 'Result Declared'));
					echo "</li>";
					echo "<li>";
						echo anchor('result/resultstopped', 'Result Stopped', array('title' => 'Result Stopped'));
					echo "</li>";
				echo "</ul>";
			echo "</li>";
			echo "<li>";
				echo "<a href=" . ">Reconcile</a>";
				echo "<ul>";
					echo "<li>";
						echo anchor('reconcile/fees_nonreconcile', 'Fees Reconcile', array('title' => 'Fees Reconcile'));
					echo "</li>";
				echo "</ul>";
			echo "</li>";
			echo "<li>";
				echo "<a href=" . ">Announcement</a>";
				echo "<ul>";
					echo "<li>";
						echo anchor('announcement/admission', 'Admission Announcement', array('title' => 'Admission Announcement'));
					echo "</li>";
					echo "<li>";
						echo anchor('announcement/result', 'Result Announcement', array('title' => 'Result Announcement'));
					echo "</li>";
					echo "<li>";
						echo anchor('announcement/general', 'General Announcement', array('title' => 'General Announcement'));
					echo "</li>";
				echo "</ul>";
			echo "</li>";
                        echo "<li>";
                                echo "<a href=" . ">Admission</a>";
                                echo "<ul>";
                                      echo "<li>";
                                      echo anchor('enterenceadmin/addexamcenter', 'Add Exam Center ', array('title' => 'Add Exam Center'));
                                      echo "</li>";
                                      echo "<li>";
                                      echo anchor('enterence/viewadmissionopen', 'Admission open ', array('title' => 'Admission open'));
                                      echo "</li>";
                                      echo "<li>";
                                      echo anchor('enterenceadmin/viewgraphicalreport', 'Graphical Report ', array('title' => 'Graphical Report'));
                                      echo "</li>";
                                      echo "<li>";
                                      echo anchor('enterenceadmin/viewnumericalreport', 'Numerical Report ', array('title' => 'Numerical Report'));
                                      echo "</li>";
                                      echo "<li>";
                                      echo anchor('enterenceadmin/viewstikerlist', 'Generate Stiker ', array('title' => 'Generate Stiker'));
                                      echo "</li>";
                                      echo "<li>";
                                      echo anchor('enterenceadmin/viewattendancesheet', 'Generate Attendance Sheet ', array('title' => 'Generate Attendance Sheet'));
                                      echo "</li>";
               		         echo "</ul>";
                        echo "</li>";		
			echo "<li>";
				echo "<a href=" . ">Archives</a>";
				echo "<ul>";
					echo "<li>";
						echo anchor('archive/feesmastera', 'Fees Master Archive', array('title' => 'Fees Master Archive'));
					echo "</li>";
					echo "<li>";
						echo anchor('archive/prgsubpapa', 'Program Subject Paper Archive', array('title' => 'Program Subject Paper Archive'));
					echo "</li>";
					echo "<li>";
						echo anchor('archive/semrulea', 'Semester Rule Archive', array('title' => 'Semester Rule Archive'));
					echo "</li>";
					echo "<li>";
						echo anchor('archive/subsema', 'Subject Semester Program with Department Archive', array('title' => 'Subject Semester Program with Department Archive'));
					echo "</li>";
					echo "<li>";
						echo anchor('archive/authoritya', 'Authority Archive', array('title' => 'Authority Archive'));
					echo "</li>";
				echo "</ul>";
			echo "</li>";
			echo "<li>";
				echo "<a href=" . ">Audit Trails</a>";
				echo "<ul>";
					echo "<li>";
						echo anchor('audittr/logdetail', 'Log Deatails', array('title' => 'Log Details'));
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
                                echo "<a href=" . ">Help</a>";
                        echo "<ul>";
                                        echo "<li>";
                                                echo anchor('help/helpdoc', 'User Manuual', array('title' => 'User Manuual'));
                                        echo "</li>";
                                        echo "</ul>";

			echo "<li>";
			echo anchor('home/logout', 'Logout', array('title' => 'Logout'));
			echo "</li>";
echo "</ul>";
echo "</nav>";
echo "</div>";

