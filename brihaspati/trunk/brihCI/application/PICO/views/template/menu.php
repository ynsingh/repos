
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
					echo anchor('setup2/designation', 'Designation', array('title' => 'Designation'));
				echo "</li>";
		               	echo "<li>";
                      			echo anchor('setup/displayscheme', 'Scheme', array('title' => 'Scheme'));
                		echo "</li>";
				echo "<li>";
					echo anchor('setup/listddo', 'DDO', array('title' => 'ddo'));
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
			echo "</ul>";
		echo "</li>";
                echo "<li>";
                       	echo anchor('home', 'PICO Setup', array('title' => 'PICO Setup'));
			echo "<ul>";
                     		echo "<li>";
		               		echo anchor('picosetup/displaystore', 'Type of Store', array('title' => 'Type of Store'));
                      		echo "</li>";
                       		echo "<li>";
                       			echo anchor('picosetup/financialpowerdetails', 'Financial Power', array('title' => 'Financial Power'));
                      		echo "</li>";
                		echo "<li>";
                        		echo anchor('picosetup/purchasetypedetails', 'Purchase Type', array('title' => 'Purchase Type'));
                                echo "</li>";
                                echo "<li>";
                                        echo anchor('picosetup/purchasecommitteedetails', 'Purchase Committee Formation Rules', array('title' => 'Formation Rules'));
                                echo "</li>"; 
                                echo "<li>";
                                        echo anchor('picosetup/displaytypeoftender','Mode Of Tender', array('title' => 'Mode of Tender'));
                                echo "</li>";
                                echo "<li>";
                                        echo anchor('picosetup/displaycovertypedetails', 'Cover Type', array('title' => 'Cover Type'));
                                echo "</li>";
                                echo "<li>";
                                        echo anchor('setup/displaybankdetails', 'Bank List', array('title' => 'Bank List'));
                                echo "</li>";
                                echo "<li>";
                                        echo anchor('picosetup/displayvendor','Supplier', array('title' => 'Supplier'));
                                echo "</li>";
//                                echo "<li>";
  //                                      echo anchor('picosetup/itemtypedetails', 'Item List', array('title' => 'Item Type'));
    //                            echo "</li>";  
  //                              echo "<li>";
//                                        echo anchor('picosetup/displaycommitteeselection', 'Purchase Committee Selection', array('title' => 'Purchase Committee Selection'));
    //                            echo "</li>";
			echo "</ul>";
		echo "</li>";
		echo "<li>";
			echo "<a href=" . ">Intender Process</a>";
                        echo "<ul>";
                	        echo "<li>";
                        	        echo anchor('setup3/salhead_config', 'Specification', array('title' => 'Purchase Process'));
                                echo "</li>";
                                echo "<li>";
                                        echo anchor('setup3/salhead_config', 'Terms & Condition', array('title' => 'Purchase Process'));
                                echo "</li>";
                                echo "<li>";
                                	echo anchor('picosetup/displaycommitteeselection', 'Committee', array('title' => 'Purchase Committee Selection'));
                                echo "</li>";
                                echo "<li>";
                                        echo anchor('tender/tenderform', 'Tender Form', array('title' => 'Tender Form'));       
                                echo "</li>";
                //                echo "<li>";
                  //                      echo anchor('setup3/salhead_config', 'Create Indent', array('title' => 'Purchase Process'));
                    //            echo "</li>";
                        echo "</ul>";
		echo "</li>";
		echo "<li>";
			echo "<a href=" . ">Purchase Management</a>";
                        echo "<ul>";
                                echo "<li>";
                                        echo anchor('tender/tenderdisplay', 'Tenders List', array('title' => 'Tender List'));       
                                echo "</li>";
//                                echo "<li>";
  //                                      echo anchor('picosetup/displayrid', 'Required Item Details', array('title' => 'Item Details'));
    //                            echo "</li>"; 
                                echo "<li>";
     		                        echo anchor('setup3/salhead_config', 'Submit Website', array('title' => 'Purchase Process'));
                                echo "</li>";
      //                          echo "<li>";
        //                              	echo anchor('setup3/salhead_config', 'Invite Quotation', array('title' => 'Purchase Process'));
          //                      echo "</li>";
                                echo "<li>";
                                        echo anchor('tender/tender_apply_list', 'Tender Apply', array('title' => 'Tender Apply'));       
                                echo "</li>";
				echo "<li>";
                                        echo anchor('tender/tender_applied', 'Tender Requests', array('title' => 'Tender Request'));       
                                echo "</li>";
            //                    echo "<li>";
              //                       	echo anchor('setup3/salhead_config', 'Bid/Quote Opening', array('title' => 'Purchase Process'));
                //                echo "</li>";
                                echo "<li>";
                                        echo anchor('tender/l1_applicants', 'Comparative Statement', array('title' => 'L1 Applicants List'));
                                echo "</li>";
                                echo "<li>";
                                      	echo anchor('picosetup/', 'Purchase Proposal', array('title' => 'Purchase Proposal'));
                                echo "</li>";
                                echo "<li>";
                                        echo anchor('picosetup/openpurchaseproposalform', 'Purchase Approval via GeM', array('title' => 'Purchase Approval via GeM'));
				echo "</li>";
                                echo "<li>";
                                     	echo anchor('setup3/salhead_config', 'Purchase Order', array('title' => 'Purchase Process'));
                                echo "</li>";
                	        echo "<li>";
                                echo "</li>";
			echo "</ul>";
		echo "</li>";
		echo "<li>";
			echo "<a href=" . ">Recieve & Store</a>";
                        echo "<ul>";
                	        echo "<li>";
                                      	echo anchor('itemaction/openitemtype', 'Item Received', array('title' => 'Item Received'));
                                echo "</li>";
                	        echo "<li>";
                                      	echo anchor('itemaction/issueitem', 'Item Issued', array('title' => 'Item Issued'));
                                echo "</li>";
                	        echo "<li>";
                                      	echo anchor('itemaction/returnitem', 'Item Return', array('title' => 'Item Return'));
                                echo "</li>";
                	        echo "<li>";
                                      	echo anchor('picosetup/', 'Inspection Report', array('title' => 'Item Inspection Report'));
                                echo "</li>";
                	        echo "<li>";
                                      	echo anchor('picosetup/', 'Payment Order', array('title' => 'Item Payment Order'));
                                echo "</li>";
                	        echo "<li>";
                                      	echo anchor('picosetup/', 'Item Transfer', array('title' => 'Item Transfer'));
                                echo "</li>";
                	        echo "<li>";
                                      	echo anchor('picosetup/', 'Item Write Off', array('title' => 'Item Write Off'));
                                echo "</li>";
                	        echo "<li>";
                                      	echo anchor('picosetup/', 'Item Auction', array('title' => 'Item Auction'));
                                echo "</li>";
                        echo "</ul>";
                echo "</li>";
					/*
			echo "<li>";

				echo "<a href=" . ">Upload</a>";
                                echo "<ul>";
                                        echo "<li>";
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
 */
		echo "<li>";
			echo "<a href=" . ">Reports</a>";
		 	echo "<ul>";
                                echo "<li>";
                                        echo anchor('picosetup/displayvendor','Supplier', array('title' => 'Supplier'));
                                echo "</li>";
                                echo "<li>";
                                        echo anchor('tender/tenderdisplay', 'Tenders', array('title' => 'Tender View'));       
                                echo "</li>";
                                echo "<li>";
                                        echo anchor('picosetup/itemtypedetails', 'Item List', array('title' => 'Item Type'));
                                echo "</li>";  
                                echo "<li>";
                                        echo anchor('picosetup/stocklist', 'Stock List', array('title' => 'Stock Item List'));
                                echo "</li>";  
                                echo "<li>";
                                        echo anchor('picosetup/issueditemlist', 'Issued List', array('title' => 'Issued Item List'));
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
						echo anchor('archive/tendercreatea', 'Tender Create Archive', array('title' => 'Tender Create Archive'));
					echo "</li>";
					echo "<li>";
						echo anchor('archive/tenderapplya', 'Tender Apply Archive', array('title' => 'Tender Apply Archive'));
					echo "</li>";
					echo "<li>";
						echo anchor('archive/vendera', 'Vendor Archive', array('title' => 'Vendor Archive'));
                                        echo "</li>";
					echo "<li>";
						echo anchor('archive/stockitema', 'Stock Item Archive', array('title' => 'Stock Item Archive'));
                                        echo "</li>";
					echo "<li>";
						echo anchor('archive/itemissueda', 'Item Issued/Return Archive', array('title' => 'Item Issued/Return Archive'));
                                        echo "</li>";
/*
					echo "<li>";
                                                echo anchor('archive/announcementa', 'Announcement Archive', array('title' => 'Announcement Archive'));
                                        echo "</li>";
 */
				echo "</ul>";
			echo "</li>";
/*			echo "<li>";
				echo "<a href=" . ">Backups</a>";
				echo "<ul>";
					echo "<li>";
						echo anchor('backups/listbkupfile', 'Backups List', array('title' => 'Backups List'));
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
			/*          echo anchor('help/helpdoc', 'User Manual', array('title' => 'User Manual'));*/
                                        echo "</li>";
                                        echo "</ul>";

			echo "<li>";
			echo anchor('home/logout', 'Logout', array('title' => 'Logout'));
			echo "</li>";
echo "</ul>";
echo "</nav>";
echo "</div>";

