
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
 

echo "<div>";
echo "<nav>";
echo "<ul class=\"sf-menu\" >";
			echo "<li class=\"current\">";
				echo "<a href=" . site_url() ."/home> Dashboard</a>";
			echo "</li>";
			echo "<li>";
				echo "<a href=" . ">Setup</a>";
				echo "<ul>";
					echo "<li>";
						echo anchor('setup/dispemailsetting', 'Email Setting', array('title' => 'Email Setting'));
					echo "</li>";
					echo "<li>";
						echo anchor('setup/displayrole', 'Role', array('title' => 'Role'));
					echo "</li>";
					echo "<li>";
						echo anchor('setup/displaycategory', 'Category/Community', array('title' => 'Category'));
					echo "</li>";
					echo "<li>";
						echo anchor('setup/viewsc', 'Study Center/Campus Name', array('title' => 'Study center'));
					echo "</li>";
					echo "<li>";
						echo anchor('setup2/authority', 'Authority/UCO', array('title' => 'Authority'));
					echo "</li>";				
					echo "<li>";
						echo anchor('setup/dispdepartment', 'Department', array('title' => 'Department'));
					echo "</li>";
                                        echo "<li>";
                                                echo anchor('setup/displaysalarygrademaster', 'Salary Grade Master/PayBand', array('title' => 'Salary Grade Master'));
                                        echo "</li>"; 
					echo "<li>";
						echo anchor('setup2/designation', 'Designation', array('title' => 'Designation'));
					echo "</li>";				
                                        echo "<li>";
                                                echo anchor('setup/displayscheme', 'Scheme', array('title' => 'Scheme'));
                                        echo "</li>";
					echo "<li>";
						echo anchor('setup/listddo', 'DDO', array('title' => 'ddo'));
					echo "</li>";				
					echo "<li>";
						echo anchor('setup/viewsubject', 'Subject', array('title' => 'Subject'));
					echo "</li>";

/*						 echo "<li>";
                                               echo "<a href=" . ">Income Tax Setup</a>";
                                                echo "<ul>";
                                                        echo "<li>";
                                                                echo anchor('setup4/savingmaster', 'Saving Master Details', array('title' => 'Saving Master Details'));
                                                        echo "</li>";
                                                echo "</ul>";
                                        echo "</li>";
*/
 					echo "<li>";
						echo "<a href=" . ">Leave Setup</a>";
                                                echo "<ul>";
                                        		   echo "<li>";
		                                                echo anchor('leavemgmt/displayleavetype', 'View Leave Type', array('title' => 'Leave Type'));
                		                           echo "</li>";
							/*echo "<li>";
		                                                echo anchor('leavemgmt/earnedleave', 'View EL', array('title' => 'Leave Type'));
                		                           echo "</li>";*/
						echo "</ul>";
               				echo "</li>";
				echo "</ul>";
			echo "</li>";
                        echo "<li>";
				echo "<a href=" . ">Payroll Management</a>";
				echo "<ul>";
					echo "<li>";
                                                echo "<a href=" . ">Salary Setup</a>";
                                                echo "<ul>";
                                                        echo "<li>";
                                		                echo anchor('setup/displaybankdetails', 'Bank Details', array('title' => 'Bank Details'));
                                                        echo "</li>";
                                                        echo "<li>";
                                		                echo anchor('setup4/displaypaymatrix', 'Pay Matrix', array('title' => 'Pay Matrix'));
                                                        echo "</li>";
                                                        echo "<li>";
                                                		echo anchor('setup/displaytaxslab', 'Tax Slab', array('title' => 'Tax Slab'));
                                                        echo "</li>";
                                                        echo "<li>";
                                                                echo anchor('setup4/savingmaster', 'Saving Master Details', array('title' => 'Saving Master Details'));
                                                        echo "</li>";
                                                        echo "<li>";
                                                		echo anchor('setup3/hra_placesgrade', 'HRA Places(Grade)', array('title' => 'HRA Places(Grade)'));
                                                        echo "</li>";
                                                        echo "<li>";
                                                		echo anchor('setup3/hra_grade', 'HRA Grade', array('title' => 'HRA Grade'));
                                                        echo "</li>";
                                                        echo "<li>";
                                                		echo anchor('setup3/rentfreehra', 'Rent Free HRA', array('title' => 'Rent Free HRA'));
                                                        echo "</li>";
                                                        echo "<li>";
                                                		echo anchor('setup3/rentrecovery', 'Rent Grade Percentage', array('title' => 'Rent Grade Percentage'));
                                                        echo "</li>";
                                                        echo "<li>";
                                                		echo anchor('setup3/cca_placesgrade', 'CCA Places(Grade)', array('title' => 'CCA Places(Grade)'));
                                                        echo "</li>";
                                                         echo "<li>";
                                                		echo anchor('setup3/cca_allowance', 'CCA Grade', array('title' => 'CCA Allowance Calculation'));
                                                        echo "</li>";
                                        		echo "<li>";
		                                                echo anchor('setup/displaysociety', 'Society Master', array('title' => 'Society Master'));
                		                        echo "</li>";
                                                echo "</ul>";
                                        echo "</li>";
                                        echo "<li>";
						echo "<a href=" . ">Salary Head</a>";
                                                echo "<ul>";
                                                        echo "<li>";
                		                                echo anchor('setup3/salaryhead_list', 'List Salary Head', array('title' => 'Salary Head'));
                                                        echo "</li>";
                                                        echo "<li>";
		                                                echo anchor('setup3/salaryformula_list', 'List Salary Formula', array('title' => 'Salary Formula'));
                                                        echo "</li>";
                                                        echo "<li>";
		                                                echo anchor('setup3/salhead_config', 'Salary Head Configuration', array('title' => 'Salary Head Configuration'));
							echo "</li>";
                                        		echo "<li>";
		                                                echo anchor('setup3/shdefaultvalue', 'Salary Head Default Values', array('title' => 'Salary Head Default Values'));
                		                        echo "</li>";
                                                echo "</ul>";
                                        echo "</li>";
					echo "<li>";
                                                echo anchor('setup4/pendingincomereq', 'Saving Master Request', array('title' => 'Saving Master Request'));
                                        echo "</li>";
                                     //   echo "<li>";
                                       //         echo anchor('payrollprofile/payprofile', 'Payroll Profile', array('title' => 'Payroll Profile'));
                                      //  echo "</li>";
                                        echo "<li>";
                                                echo anchor('payrollprofile/emppayprofile', 'Payroll Profile', array('title' => 'Payroll Profile'));
                                        echo "</li>";
                                        echo "<li>";
                                                echo anchor('payrollprofile/viewpayleaveentry', 'Payroll Leave Entry', array('title' => 'Payroll Leave Entry'));
                                        echo "</li>";
                                        echo "<li>";
                                                echo anchor('payrollprofile/viewpaytransentry', 'Payroll Transfer Entry', array('title' => 'Payroll Transfer Entry'));
                                        echo "</li>";
                                        echo "<li>";
                                                echo anchor('setup3redesign/salaryprocess', 'Salary Processing', array('title' => 'Salary Processing'));
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
						echo anchor('upl/uploadddolist', 'Upload DDO', array('title' => 'Upload DDO'));
					echo "</li>";
                                echo "</ul>";
				echo "</li>";
				echo "<li>";
                                echo "<a href=" . ">Announcement</a>";
                                echo "<ul>";
                                        echo "<li>";
                                                echo anchor('announcement/viewannouncement', 'Announcement', array('title' => 'Announcement'));
                                        echo "</li>";
                                echo "</ul>";
                        echo "</li>";

			echo "<li>";
				echo "<a href=" . ">Reports</a>";
				echo "<ul>";
                                        echo "<li>";
						echo anchor('report/profilecompleteness', 'Profile Completeness List', array('title' => 'View Employee Profile Completeness List'));
					echo "</li>";
                                        echo "<li>";
						echo anchor('report/viewprofile', 'Employee List', array('title' => 'View Employee List'));
					echo "</li>";
                                        
                                        echo "<li>";
						echo anchor('report/retiredemplist', 'Retired Employee List', array('title' => 'View Retired Employee List'));
					echo "</li>";
					echo "<li>";
						echo anchor('leavemgmt/viewela', 'View Earned Leave', array('title' => 'Earned Leave Details'));
					echo "</li>";
                                    echo "</ul>";
			echo "</li>";
			echo "<li>";
				echo "<a href=" . ">Archives</a>";
				echo "<ul>";
					echo "<li>";
						echo anchor('audittr/logdetail', 'Log Details', array('title' => 'Log Details'));
					echo "</li>";
					echo "<li>";
						echo anchor('archive/listddoa', 'DDO Archive', array('title' => 'DDO Archive'));
					echo "</li>";
					echo "<li>";
						echo anchor('archive/displaysalarygrademastera', 'Salary Grade Master Archive', array('title' => 'Salary Grade Master Archive'));
					echo "</li>";
					echo "<li>";
						echo anchor('archive/bankdetaila', 'Bank Details Archive', array('title' => 'Bank Details Archive'));
					echo "</li>";
					echo "<li>";
                                                echo anchor('archive/announcementa', 'Announcement Archive', array('title' => 'Announcement Archive'));
                                        echo "</li>";

				echo "</ul>";
			echo "</li>";
			echo "<li>";
				echo "<a href=" . ">Backups</a>";
				echo "<ul>";
					echo "<li>";
						echo anchor('backups/listbkupfile', 'Backups List', array('title' => 'Backups List'));
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
						echo anchor('profile/changeemppassword', 'Change Employee Password', array('title' => 'Change Employee Password'));
					echo "</li>";
				echo "</ul>";
			echo "</li>";
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
