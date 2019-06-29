
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
 

echo "<div>";
echo "<nav>";
echo "<ul class=\"sf-menu\" >";
//echo "<ul>";
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
						echo anchor('setup/displaycategory', 'Category/Community', array('title' => 'Category'));
					echo "</li>";
				/*	echo "<li>";
						echo anchor('setup/dispseatsetting', 'Seat Reservation', array('title' => 'Seat Reservation'));
					echo "</li>";
				*/
					echo "<li>";
						echo anchor('setup/viewsc', 'Study Center/Campus Name', array('title' => 'Study center'));
					echo "</li>";
					echo "<li>";
						echo anchor('setup2/authority', 'Authority/UCO', array('title' => 'Authority'));
					echo "</li>";				
					echo "<li>";
						echo anchor('setup/dispdepartment', 'Department', array('title' => 'Department'));
					echo "</li>";
                                       /* echo "<li>";
                                                echo anchor('setup/displaysalarygrademaster', 'Salary Grade Master/PayBand', array('title' => 'Salary Grade Master'));
                                        echo "</li>"; 
                                        */
					echo "<li>";
						echo anchor('setup2/designation', 'Designation', array('title' => 'Designation'));
					echo "</li>";

                    echo "<li>";
                        echo anchor('setup/displayscheme', 'Scheme', array('title' => 'Scheme'));
                    echo "</li>";

					echo "<li>";
						echo anchor('setup/listddo', 'DDO', array('title' => 'ddo'));
					echo "</li>";				
				/*	echo "<li>";
						echo anchor('setup/viewprogramcat', 'Program Category', array('title' => 'Program Category'));
					echo "</li>";
					echo "<li>";
						echo anchor('setup/viewprogram', 'Program and Seat', array('title' => 'Program and Seat'));
					echo "</li>";
				*/
					// echo "<li>";
					// 	echo anchor('setup/viewsubject', 'Subject', array('title' => 'Subject'));
					// echo "</li>";

/*						 echo "<li>";
                                               echo "<a href=" . ">Income Tax Setup</a>";
                                                echo "<ul>";
                                                        echo "<li>";
                                                                echo anchor('setup4/savingmaster', 'Saving Master Details', array('title' => 'Saving Master Details'));
                                                        echo "</li>";
                                                echo "</ul>";
                                        echo "</li>";
*/

/* 					echo "<li>";
						echo "<a href=" . ">Leave Setup</a>";
                                                echo "<ul>";
                                        		   echo "<li>";
		                                                echo anchor('leavemgmt/displayleavetype', 'View Leave Type', array('title' => 'Leave Type'));
                		                           echo "</li>"; 

							/*echo "<li>";
		                                                echo anchor('leavemgmt/earnedleave', 'View EL', array('title' => 'Leave Type'));
                		                           echo "</li>";
						echo "</ul>"; */
                      
               				echo "</li>";
				echo "</ul>";
			echo "</li>";
			echo "<li>";
				echo "<a href=" . ">Map</a>";
				echo "<ul>";
					echo "<li>";
						echo anchor('map/viewuserrole', 'Map User with Role', array('title' => 'Map User with Role'));
					echo "</li>";
                                 //        echo "<li>";
                                   //             echo anchor('map/viewschemedept', 'Map Scheme with Department', array('title' => 'Map Scheme with Department'));
                                     //   echo "</li>"; 
					echo "<li>";
						echo anchor('map/viewauthuser', 'Map Authority and User', array('title' => 'Map Authority and User'));
					echo "</li>";	
					echo "<li>";
						echo anchor('map/viewscuo', 'Map Study Center with UO', array('title' => 'Map Study center and UO'));
					echo "</li>";	
                                        echo "<li>";
						echo anchor('map/hodlist', 'Set HOD', array('title' => 'Set HOD'));
					echo "</li>";
                                        echo "<li>";
                                                echo anchor('map/uolist', 'Set UO', array('title' => 'Set UO'));
                                        echo "</li>";
                                        echo "<li>";
                                                echo anchor('map/societies', 'Map User with Societies', array('title' => 'Map User with Societies'));
                                        echo "</li>";

 
				/*	echo "<li>";
						echo anchor('map/viewddoucodeptsh', 'Map Campus,UO,DDO with Dept', array('title' => 'Map DDO with UCO'));
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
 */	
				
				echo "</ul>";
			echo "</li>";
/*
			echo "<li>";
				echo "<a href=" . ">Staff Management</a>"; 
				echo "<ul>";
                                        echo "<li>";
                                                echo anchor('staffmgmt/staffposition', 'Staff Position', array('title' => 'Staff Position'));
                                        echo "</li>";
                                        echo "<li>";
                                                echo anchor('staffmgmt/employeelist', 'Staff Profile', array('title' => 'Staff Profile'));
                                        echo "</li>";
                                        echo "<li>";
                                                echo anchor('staffmgmt/stafftransferlist', 'Staff Transfer and Posting', array('title' => 'Staff Transfer and posting'));
                                        echo "</li>";
                                        echo "<li>";
                                                echo anchor('staffmgmt/staffretirement', 'Staff Leaving', array('title' => 'Staff Retirement'));
                                        echo "</li>";
                                        echo "<li>";
						echo anchor('report/retiredemplist', 'Retired Employee Profile', array('title' => 'View Retired Employee List'));
//                                            echo anchor('staffmgmt/retiredstafflist', 'Retired Staff Profile', array('title' => 'Retired Staff Profile'));
                                        echo "</li>";
                                        echo "<li>";
						echo anchor('cronjob/autoretirement', 'Retire Employee Update', array('title' => 'Retired Employee'));
                                        echo "</li>";
                                        
                                echo "</ul>";
			echo "</li>";
                            echo "<li>";
                                echo "<a href=" . ">Retired Staff Proile</a>";
                                echo "<ul>";
                                        echo "<li>";
                                                echo anchor('staffmgmt/retiredstafflist', 'Retired Staff List', array('title' => 'Retired Staff List'));
                                                //echo anchor('chronecount/autoretirement', 'Retired Staff List', array('title' => 'Retired Staff List'));
                                        echo "</li>";
                                echo "</ul>";
                        echo "</li>";*/

                        echo "<li>";
                        echo anchor('home', 'PICO Management', array('title' => 'PICO Management'));
				//echo "<a href=" . ">PICO Management</a>";
	echo "<ul>";
					echo "<li>";
/*Type of Store*/
                        echo "<a href=".">Setup</a>";  

                        echo "<ul>";
                                            echo "<li>";
/*Create a form in setup folder*/               echo anchor('picosetup/displaystore', 'Type of Store', array('title' => 'Type of Store'));
                                            echo "</li>";
/*Financial Power*/
                                            echo "<li>";
                                            echo anchor('picosetup/financialpowerdetails', 'Financial Power', array('title' => 'Financial Power'));
                                            echo "</li>";
/*Purchase Type*/
                                    echo "<li>";
                                     echo anchor('picosetup/purchasetypedetails', 'Purchase Type', array('title' => 'Purchase Type'));
                                               

                                                
/*Minor Purchase*/
/*                                                    echo "<ul>";
                                                        echo "<li>";
                                                           echo anchor('payrollprofile/payprofile', 'Minor Purchase', array('title' => 'Minor Purchase')); 
                                                                echo "<ul>";
                                                                                echo "<li>";
                                                                                    echo anchor('payrollprofile/payprofile', 'Without Quotation', array('title' => 'Minor Purchase'));
                                                                                echo "</li>";
                                                                                echo "<li>";
                                                                                    echo anchor('payrollprofile/payprofile', 'Purchase Committee without Quotation', array('title' => 'Minor Purchase'));
                                                                                echo "</li>";
                                                                                echo "<li>";
                                                                                    echo anchor('payrollprofile/payprofile', 'Purchase Committee with Quotation', array('title' => 'Minor Purchase'));
                                                                                echo "</li>";
                                                                echo "</ul>"; 
                                                        echo "</li>";
*/
/*Medium Purchase*/
/*
                                                        echo "<li>";
                                                            echo "<a href=".">Medium Purchase</a>";
                                                                       echo "<ul>";
                                                                                echo "<li>";
                                                                                    echo anchor('payrollprofile/payprofile', 'Purchase Committee (Approved CA) Quotation', array('title' => 'Medium Purchase'));
                                                                                echo "</li>";
                                                                                echo "<li>";
                                                                                    echo anchor('payrollprofile/payprofile', 'Purchase Committee (Approved CA) Limited Tender', array('title' => 'Medium Purchase'));
                                                                                echo "</li>";
                                                                                
                                                                        echo "</ul>"; 
                                                        echo "</li>";
*/                                                       
/*Major Purchase*/
/*
                                                        echo "<li>";
                                                            echo "<a href=".">Major Purchase</a>";
                                                                      /*  echo "<ul>";
                                                                                echo "<li>";
                                                                                    echo anchor('payrollprofile/payprofile', 'Purchase Committee (Approved CA) Quotation', array('title' => 'Major Purchase'));
                                                                                echo "</li>";
                                                                                echo "<li>";
                                                                                    echo anchor('payrollprofile/payprofile', 'Tech Evaluation Committee (Approved CA)', array('title' => 'Major Purchase'));
                                                                                echo "</li>";    
                                                                        echo "</ul>"; */
/*                                                        echo "</li>";
                                                             
                                                    echo "</ul>";
                                         
                                    echo "</li>";

/*Mode of Tender*/

                                




                                      

                                         echo "<li>";
                                                echo anchor('picosetup/displayvendor','Vendor', array('title' => 'vendor '));
                                        echo "</li>";
                                        echo "<li>";
                                                echo anchor('picosetup/displaytypeoftender','Mode of Tender', array('title' => 'Mode of Tender'));
                                        echo "</li>";
													//testing
									//	echo "<li>";
                                      //          echo anchor('','test here...', array('title' => 'tender create'));
                                        //echo "</li>";
                                        echo "<li>";

                                                echo anchor('setup/displaybankdetails', 'Bank List', array('title' => 'Bank List'));
                                        echo "</li>";
                                        echo "<li>";
                                                echo anchor('picosetup/displayrid', 'Required Item', array('title' => 'req item'));
                                        echo "</li>"; 
                                   
                  //                      echo "<li>";
                      //                          echo anchor('tender/tenderbiddoc', 'Tender|Form ', array('title' => 'Tender'));
                    //                    echo "</li>";
                                        echo "<li>";
                                                echo anchor('tender/tenderform', 'Tender Form', array('title' => 'Tender'));       
                                        echo "</li>";

                                        echo "<li>";
                                                echo anchor('picosetup/displaycovertypedetails', 'Cover Type', array('title' => 'Cover Type'));
                                        echo "</li>";

                                        echo "<li>";
                                                echo anchor('picosetup/itemtypedetails', 'Item List', array('title' => 'Item Type'));
                                        echo "</li>";  

                                        echo "<li>";
                                                echo anchor('picosetup/purchasecommitteedetails', 'Purchase Committee Formation Rules', array('title' => 'Formation Rules'));
                                        echo "</li>"; 
/*Fees*/
                                        echo "<li>";
                                               echo anchor('payrollprofile/viewpaytransentry', 'Fees', array('title' => 'Fees'));
/*                                                    echo "<ul>";
                                                        echo "<li>";
                                                            echo anchor('payrollprofile/payprofile', 'Tender Fees', array('title' => 'Payroll Profile'));
                                                        echo "</li>";
                                                        echo "<li>";
                                                             echo anchor('payrollprofile/payprofile', 'Amount Cut', array('title' => 'Payroll Profile'));
                                                        echo "</li>";
                                                        echo "<li>";
                                                            echo anchor('payrollprofile/payprofile', '_______________', array('title' => 'Payroll Profile'));
                                                        echo "</li>"; 
                                                        echo "<li>";
                                                            echo anchor('payrollprofile/payprofile', 'Refund', array('title' => 'Payroll Profile'));
                                                        echo "</li>"; 
                                                        echo "<li>";
                                                            echo anchor('payrollprofile/payprofile', 'EMD Fees', array('title' => 'Payroll Profile'));
                                                        echo "</li>"; 
                                                        echo "<li>";
                                                            echo anchor('payrollprofile/payprofile', 'Performance Back  Guarantee', array('title' => 'Payroll Profile'));
                                                        echo "</li>"; 
                                                echo "</ul>";
*/    
                                        echo "</li>";


 /*Setup close tag*/echo "</ul>";   
                                    

                                       
/*Supplier Registration*/
                                        echo "<li>";
                                                echo anchor('setup3/shdefaultvalue', 'Supplier Registration', array('title' => 'Salary Head Default Values'));
                                               // echo "<ul>";
                                               //          echo "<li>";
                                               //              echo anchor('setup3/salhead_config', 'Local Registered Firms', array('title' => 'Salary Head Configuration'));
                                               //          echo "</li>";
                                               //          echo "<li>";
                                               //              echo anchor('setup3/salhead_config', 'Outside Firms', array('title' => 'Salary Head Configuration'));
                                               //          echo "</li>";
                                               //          echo "<li>";
                                               //              echo anchor('setup3/salhead_config', 'Blacklist', array('title' => 'Salary Head Configuration'));
                                               //          echo "</li>";

                                               // echo "</ul>";
                                        echo "</li>";

/*Purchase Type*/
                                        echo "<li>";
                                                echo anchor('picosetup/openpurchaseproposalform', 'Purchase Proposal', array('title' => 'Purchase Proposal'));
                                        echo "</li>";
					                    
                                        echo "<li>";
                                                echo anchor('picosetup/', 'Purchase Approval via GeM', array('title' => 'Purchase Approval via GeM'));
                                        echo "</li>";
/*Purchase Process*/
                                        echo "<li>";
                                                echo "<a href=".">Purchase Process</a>";
                                                    echo "<ul>";

                                                        echo "<li>";
                                                        echo anchor('setup3/salhead_config', 'Specifiaction', array('title' => 'Purchase Process'));
                                                        echo "</li>";
                                                        echo "<li>";
                                                            echo anchor('setup3/salhead_config', 'Terms & Condition', array('title' => 'Purchase Process'));
                                                        echo "</li>";
                                                        echo "<li>";
                                                            echo anchor('setup3/salhead_config', 'Committee', array('title' => 'Purchase Process'));
                                                        echo "</li>";
                                                        echo "<li>";
                                                            echo anchor('setup3/salhead_config', 'Create Indent', array('title' => 'Purchase Process'));
                                                        echo "</li>";
                                                        echo "<li>";
                                                            echo anchor('setup3/salhead_config', 'Submit Website', array('title' => 'Purchase Process'));
                                                        echo "</li>";
                                                        echo "<li>";
                                                            echo anchor('setup3/salhead_config', 'Invite Quotation', array('title' => 'Purchase Process'));
                                                        echo "</li>";
                                                        echo "<li>";
                                                            echo anchor('setup3/salhead_config', 'Bid/Quote Opening', array('title' => 'Purchase Process'));
                                                        echo "</li>";
                                                        echo "<li>";
                                                            echo anchor('setup3/salhead_config', 'Comparative Statement', array('title' => 'Purchase Process'));
                                                        echo "</li>";
                                                        echo "<li>";
                                                            echo anchor('setup3/salhead_config', 'Purchase Order', array('title' => 'Purchase Process'));
                                                        echo "</li>";

                                                    echo "</ul>";
                                        echo "</li>";
                                       

                                        // echo "<li>";
                                        //         echo anchor('setup3/salaryprocess', 'Salary Processing', array('title' => 'Salary Processing'));
                                        // echo "</li>";

                                        echo "<li>";
                                             echo anchor('setup3/salaryprocess', 'Recieve & Store', array('title' => 'Recieve & Store'));
                                        echo "</li>";
    echo "</ul>";
  
			echo "</li>";
			echo "<li>";
/*
				echo "<a href=" . ">Upload</a>";
				echo "<ul>";
					echo "<li>";
						echo anchor('upl/uploadlogo', 'Upload Logo', array('title' => 'Upload Logo'));
					echo "</li>";
                                        echo "<li>";
						echo anchor('upl/uploaddepartlist', 'Upload Department', array('title' => 'Upload Department'));
					echo "</li>";
					echo "<li>";
						echo anchor('upl/uploaddeglist', 'Upload Designation', array('title' => 'Upload Designation'));
					echo "</li>";
					echo "<li>";
						echo anchor('upl/uploadschemelist', 'Upload Scheme', array('title' => 'Upload Scheme'));
					echo "</li>";
					echo "<li>";
						echo anchor('upl/uploadddolist', 'Upload DDO', array('title' => 'Upload DDO'));
					echo "</li>";
				/*	echo "<li>";
						echo anchor('upl/uploadstulist', 'Upload Student List', array('title' => 'Upload Student List'));
					echo "</li>";
				 	echo "<li>";
						echo anchor('upl/uploadtlist', 'Upload Teacher List', array('title' => 'Upload Teacher List'));
					echo "</li>";*/
/*                    
     				        echo "<li>";
                                                echo anchor('upl/uploadspositionlist', 'Upload Staff Position', array('title' => 'Upload Staff Position List'));
                                        echo "</li>";	
     				        echo "<li>";
                                                echo anchor('upl/uploadslist', 'Upload Staff List', array('title' => 'Upload Staff List'));
                                        echo "</li>";	
                                        echo "<li>";
                                                echo anchor('upl/uplstaffphoto', 'Upload Staff Photo', array('title' => 'Upload Staff Photo'));
                                        echo "</li>";	
                                        echo "<li>";
                                                echo anchor('upl/servicedata', 'Upload Service Data', array('title' => 'Upload Service Data'));
                                        echo "</li>";
                                        
                                        echo "<li>";
                                                echo anchor('upl2/uploadhodlist', 'Upload HOD List', array('title' => 'Upload HOD List'));
                                        echo "</li>";
                                        echo "<li>";
                                                echo anchor('upl2/uploaduolist', 'Upload UO List', array('title' => 'Upload UO List'));
                                        echo "</li>";
                                       
                                        echo "<li>";
                                                echo anchor('upl/uploadtransferorder', 'Upload Transfer Orders', array('title' => 'Upload Transfer Orders'));
                                        echo "</li>";
                                echo "</ul>";
				echo "</li>";
		/*		echo "<li>";
                                echo "<a href=" . ">Request</a>";
                                echo "<ul>";
      //                                  echo "<li>";
        //                                        echo anchor('leavemgmt/pendingreq', 'Leave Request', array('title' => 'Leave Request'));
          //                              echo "</li>";
					echo "<li>";
                                                echo anchor('setup4/pendingincomereq', 'Saving Master Request', array('title' => 'Saving Master Request'));
                                        echo "</li>";
                                echo "</ul>";
                        	echo "</li>";
*/
/*

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
		/*			echo "<li>";
						echo anchor('report/liststu', 'Student List', array('title' => 'Student List'));
					echo "</li>";
					echo "<li>";
						echo anchor('report/listfac', 'Faculty List', array('title' => 'Faculty List'));
					echo "</li>";
					echo "<li>";
						echo anchor('staffmgmt/employeelist', 'Staff List', array('title' => 'Staff List'));
					echo "</li>";	*/
/*
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
                                                echo anchor('report/disciplinewiselist', 'Discipline Wise Report', array('title' => 'Discipline Wise Report'));
                                        echo "</li>";
					echo "<li>";
                                                echo anchor('report/deptemployeelist', 'Department Wise Staff List', array('title' => 'Department Wise Staff List'));
                                        echo "</li>";
					echo "<li>";
                                               echo anchor('report/desigemployeelist', 'Designation Wise Staff List', array('title' => 'Designation Wise Staff List'));
                                        echo "</li>";
                                        echo "<li>";
						echo anchor('report/staffvacposition', 'Staff Vacancy Post Wise', array('title' => 'Staff Vacancy Post Wise'));
					echo "</li>";
                                        echo "<li>";
                                                echo anchor('report/staffstrengthlist', 'Staff Vacancy UO Wise', array('title' => 'Staff Vacancy UO Wise'));
                                        echo "</li>";
                                         echo "<li>";
						echo anchor('report/positionsummary', 'Position-Summary', array('title' => 'Position-Summary'));
					echo "</li>";
                                        echo "<li>";
						echo anchor('report/listofstaffposition', 'Staff Position with Name  UO Wise', array('title' => 'Staff Position with Name  UO Wise'));
					echo "</li>";	
                                        echo "<li>";
						echo anchor('report/positionvacancy', 'Staff Position with Name Post Wise', array('title' => 'Staff Position with Name Post Wise'));
					echo "</li>";
                                        echo "<li>";
						echo anchor('report/professorlist', 'Staff Seniority List', array('title' => 'Staff Seniority List'));
					echo "</li>";
                                        echo "<li>";
						echo anchor('report/hodlist', 'List of HOD', array('title' => 'List of HOD'));
					echo "</li>";
                                        echo "<li>";
						echo anchor('report/uolist', 'List of UO', array('title' => 'List of UO'));
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
				/*	echo "<li>";
						echo anchor('archive/feesmastera', 'Fees Master Archive', array('title' => 'Fees Master Archive'));
					echo "</li>";
					echo "<li>";
						echo anchor('archive/prgsubpapa', 'Program Subject Paper Archive', array('title' => 'Program Subject Paper Archive'));
					echo "</li>";
					echo "<li>";
						echo anchor('archive/semrulea', 'Semester Rule Archive', array('title' => 'Semester Rule Archive'));
					echo "</li>";
					echo "<li>";
						echo anchor('archive/ddoucoa', 'DDO with UCO Archive', array('title' => 'DDO with UCO Archive'));
					echo "</li>";*/ 
/*
					echo "<li>";
						echo anchor('archive/authoritya', 'Authority Archive', array('title' => 'Authority Archive'));
					echo "</li>";
					echo "<li>";
						echo anchor('archive/staffpositiona', 'Staff Position Archive', array('title' => 'Staff Position Archive'));
					echo "</li>";
					echo "<li>";
						echo anchor('archive/listddoa', 'DDO Archive', array('title' => 'DDO Archive'));
					echo "</li>";
					echo "<li>";
						echo anchor('archive/displayschemea', 'Scheme Archive', array('title' => 'Scheme Archive'));
					echo "</li>";
					echo "<li>";
						echo anchor('archive/displaysalarygrademastera', 'Salary Grade Master Archive', array('title' => 'Salary Grade Master Archive'));
					echo "</li>";
					echo "<li>";
						echo anchor('archive/viewscuoa', 'SC with UO Archive', array('title' => 'SC with UO Archive'));
					echo "</li>";
					echo "<li>";
						echo anchor('archive/bankdetaila', 'Bank Details Archive', array('title' => 'Bank Details Archive'));
					echo "</li>";
                                        echo "<li>";
                                                echo anchor('archive/mapuserrolea', 'Map User With Role Archive', array('title' => 'Map User With Role Details Archive'));
                                        echo "</li>"; 
                                        echo "<li>";
                                                echo anchor('archive/mapschemedepta', 'Map Scheme With Department Archive', array('title' => 'Map Scheme With Department Details Archive'));
                                        echo "</li>";
                                        echo "<li>";
                                                echo anchor('archive/departmenta', 'Department Archive', array('title' => 'Department Archive'));
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
            
		/*	echo "<li>";
				echo "<a href=" . ">Audit Trails</a>";
				echo "<ul>";
					echo "<li>";
						echo anchor('audittr/logdetail', 'Log Details', array('title' => 'Log Details'));
					echo "</li>";
				echo "</ul>";
			echo "</li>";
*/
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
				/*	echo "<li>";
						echo anchor('home/logout', 'Logout', array('title' => 'Logout'));
					echo "</li>";							
				*/
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

