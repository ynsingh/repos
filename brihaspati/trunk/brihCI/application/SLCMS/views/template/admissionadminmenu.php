
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
 

echo "<div>";
echo "<nav>";
echo "<ul class=\"sf-menu\">";

			echo "<li class=\"current\">";
				echo "<a href=" . site_url() ."/admissionadminhome> Dashboard</a>";
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
				echo "<a href=" . ">Admission</a>";
				echo "<ul>";
					echo "<li>";
						echo anchor('reconcile/fees_nonreconcile', 'Admission Fees Reconcile', array('title' => 'Admission Fees Reconcile'));
					echo "</li>";
					echo "<li>";
						echo anchor('adminadmissionstu/adminstu_nonverified', 'Student Data Verification', array('title' => 'Student Data Verification'));
					echo "</li>";
					echo "<li>";
						echo anchor('adminadmissionstu/listenrolladminstu', 'Student Enrollment Number', array('title' => 'Student Enrollment Number'));
					echo "</li>";
					echo "<li>";
						echo anchor('adminadmissionstu/student_transfer', 'Student Department Transfer', array('title' => 'Student Department Transfer'));
					echo "</li>";
					echo "<li>";
						echo anchor('adminadmissionstu/stu_admissioncancel', 'Admission Cancellation', array('title' => 'Admission Cancellation'));
					echo "</li>";
/*					echo "<li>";
						echo anchor('admissionstu/stu_feereimb', 'Admission Fees Reimbursement', array('title' => 'Admission Fees Reimbursement'));
					echo "</li>";
*/
				echo "</ul>";
			echo "</li>";				
			echo "<li>";
                                echo "<a href=" . ">Exam</a>";
                                echo "<ul>";
                                        echo "<li>";
						//echo anchor('exam/examcentercreation', 'Setup Exam Center', array('title' => 'Setup Exam Center'));
                                                echo anchor('welcome/work_underprocess', 'Setup Exam Center', array('title' => 'Setup Exam Center'));
                                        echo "</li>";
                                        echo "<li>";
						//echo anchor('exam/examschedele', 'Exam Schedule', array('title' => 'Exam Schedule'));
                                                echo anchor('welcome/work_underprocess', 'Exam Schedule', array('title' => 'Exam Schedule'));
                                        echo "</li>";
                                        echo "<li>";
                                                //echo anchor('adminstuexam/viewadmitcard', 'Admit Card Generation', array('title' => 'Admit Card Generation'));
						echo anchor('welcome/work_underprocess', 'Admit Card Generation', array('title' => 'Admit Card Generation'));
                                        echo "</li>";
                                        echo "<li>";
						//echo anchor('exam/attendancesheetgen', 'Attendance Sheet Generation', array('title' => 'Attendance Sheet Generation'));
                                                echo anchor('welcome/work_underprocess', 'Attandence Sheet Generation', array('title' => 'Attandence Sheet Generation'));
                                        echo "</li>";
                                        echo "<li>";
                                                //echo anchor('exam/verificationform', 'Verification Form Generation', array('title' => 'Verification Form Generation'));
						echo anchor('welcome/work_underprocess', 'Verification Form Generation', array('title' => 'Verification Form Generation'));
                                        echo "</li>";
                                echo "</ul>";
                        echo "</li>";
			echo "<li>";
				echo "<a href=" . ">Result</a>";
				echo "<ul>";
					echo "<li>";
						//echo anchor('result/markssubmission', 'Marks Submission', array('title' => 'Marks Submission'));
						echo anchor('welcome/work_underprocess', 'Marks Submission', array('title' => 'Marks Submission'));
					echo "</li>";
					//echo "<li>";
						echo anchor('Admin_studentresult/tabulationchart', 'Tabulation Chart', array('title' => 'Tabulation Chart'));
					//	echo anchor('welcome/work_underprocess', 'Tabulation Chart', array('title' => 'Tabulation Chart'));
					//echo "</li>";
					echo "<li>";
						//echo anchor('result/resultscrutiny', 'Result Scrutiny', array('title' => 'Result Scrutiny'));
						echo anchor('welcome/work_underprocess', 'Result Scrutiny', array('title' => 'Result Scrutiny'));
					echo "</li>";
					echo "<li>";
						//echo anchor('result/resultdeclared', 'Result Declared', array('title' => 'Result Declared'));
						echo anchor('welcome/work_underprocess', 'Result Declared', array('title' => 'Result Declared'));
					echo "</li>";
					echo "<li>";
						//echo anchor('result/resultstopped', 'Result Stopped', array('title' => 'Result Stopped'));
						echo anchor('welcome/work_underprocess', 'Result Stopped', array('title' => 'Result Stopped'));
					echo "</li>";
					echo "<li>";
						//echo anchor('result/gradecard', 'Gen and Print Grade Card', array('title' => 'Gen and Print Grade Card'));
						echo anchor('welcome/work_underprocess', 'Gen and Print Grade Card', array('title' => 'Gen and Print Grade Card'));
					echo "</li>";
					echo "<li>";
						//echo anchor('result/issuegradecard', 'Issue Grade Card', array('title' => 'Issue Grade Card'));
						echo anchor('welcome/work_underprocess', 'Issue Grade Card', array('title' => 'Issue Grade Card'));
					echo "</li>";
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
				/*	echo "<li>";
						echo anchor('report/listcenterwise', 'Exam Center Wise List', array('title' => 'Exam Center Wise List'));
					echo "</li>";
					echo "<li>";
						echo anchor('report/listprgcatwise', 'Program Category Wise List', array('title' => 'Program Category Wise List'));
					echo "</li>";
				 */
					echo "<li>";
						echo anchor('report/admission_meritlist', 'Admission Merit List', array('title' => 'Admission Merit List'));
					echo "</li>";					
					echo "<li>";
						echo anchor('report/list_application', 'Filtered Student Application List', array('title' => 'Search Student Application List'));
 					echo "</li>";
                             /*         echo "<li>";
                                      echo anchor('enterenceadmin/viewgraphicalreport', 'Graphical Report ', array('title' => 'Graphical Report'));
                                      echo "</li>";
                                      echo "<li>";
                                      echo anchor('enterenceadmin/viewnumericalreport', 'Numerical Report ', array('title' => 'Numerical Report'));
				      echo "</li>";*/
				      echo "<li>";
                                      echo anchor('report/admission_student', 'Student Admission Report', array('title' => 'Student Admission Report'));
                                      echo "</li>";
				echo "</ul>";
			echo "</li>";
			echo "<li>";
				echo "<a href=" . ">Downloads</a>";
				echo "<ul>";
					echo "<li>";
						echo anchor('downloads/enterance_applicantdw', 'Entrance Applicant Details', array('title' => 'Entrance Applicant Details'));
					echo "</li>";
					echo "<li>";
						echo anchor('downloads/admission_student', 'Admission Applicant Details', array('title' => 'Admission Applicant Details'));
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


