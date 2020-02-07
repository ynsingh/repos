
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<!--@name helpdoc.php 
  @author Deepika Chaudhary (chaudharydeepika88@gmail.com)
 -->
<html>
<head>
<title>User Manual</title>  
        <?php $this->load->view('template/header'); ?>
        
 	<link rel="stylesheet" type="text/css" href="<?php echo base_url();?>assets/css/helpdoc.css">
</head>
<body>
	
	<div class="wrapper" width="100%"  style="background-color: #DDDDDD;"> 
	<div class="head">Brihaspati Staff Information System (BSIS)</div>
	<div class="content">
	<div style="margin-top:-14px;" class="sideleft">
        <div id="cssmenu">
        	<ul>
		<li><a href="#BrihaspatiStaffInformationSystem">Brihaspati Staff Information System (BSIS)</a></li>
	     <li><a href="#Dashboard">Dashboard</a></li>
	     <li class='has-sub'><a href="#Setup">Setup</a>
			<ul>
				<li><a href="#EmailSetting">Email Setting</a></li>
				<li><a href="#Role">Role</a></li>
				<li><a href="#Category">Category/Community</a></li>
				<li><a href="#StudyCenter">Study Center/Campus Name</a></li>
				<li><a href="#Authority">Authority/UCO</a></li>
				<li><a href="#Department">Department</a></li>
				<li><a href="#SalaryGradeMaster">Salary Grade Master</a></li>
           		<li><a href="#Designation">Designation</a></li>
	            <li><a href="#Scheme">Scheme</a></li>
	        	<li><a href="#DDO">DDO</a></li>
				<li><a href="#Subject">Subject</a></li>
				<li class='has-sub'><a href="#LeaveSetup">Leave Setup</a>
					<ul>
						<li><a href="#ViewLeaveType">View Leave Type</a></li>
					</ul>
				</li>
			</ul>
		</li>




	    <li class='has-sub'><a href="#Map">Map</a>
			<ul>
				<li><a href="#MapUserwithRole">Map User with Role</a></li>
			    <li><a href="#MapAuthorityandUser">Map Authority and User</a></li>
				<li><a href="#MapStudyCenterwithUO">Map Study Center with UO</a></li>
				<li><a href="#SetUO">Set UO</a></li>
				<li><a href="#SetHOD">Set HoD</a></li>
				<li><a href="#MapUserWithSocieties">Map User With Societies</a></li>
			</ul>
		</li>






	    <li class='has-sub'><a href="#StaffManagement">Staff Management</a>
	    	<ul>
	    		<li><a href="#StaffPosition">Staff Position</a></li>
	    		<li><a href="#StaffProfile">Staff Profile</a></li>
	    		<li><a href="#StaffTransferandPosting">Staff Transfer And Posting</a></li>
	    		<li><a href="#StaffLeaving">Staff Leaving</a></li>
	    		<li><a href="#RetiredEmployeeProfile">Retired Employee Profile</a></li>
	    		<li><a href="#RetiredEmployeeUpdate">Retired Employee update</a></li>
	    	</ul>
                  

	    <li class='has-sub'><a href="#PayrollManagement">Payroll Management</a>
			<ul>
				<li class='has-sub' ><a href="#SalarySetup">Salary Setup</a>
					<ul>
						<li><a href="#BankDetail">Bank Detail</a></li>
						<li><a href="#PayMatrix">Pay Matrix</a></li>
						<li><a href="#TaxSlab">Tax Slab</a></li>
						<li><a href="#SavingMasterDetail">Saving Master Detail</a></li>
						<li><a href="#HRAPlaces">HRA Places</a></li>
						<li><a href="#HRAGrade">HRA Grade</a></li>
						<li><a href="#RentFreeHRA">Rent Free HRA</a></li>
						<li><a href="#RentGradePercentage">Rent Grade Percentage</a></li>
						<li><a href="#CCAPlaces">CCA Places</a></li>
						<li><a href="#CCAGrades">CCA Grades</a></li>
						<li><a href="#SocietyMaster">Society Master</a></li>
					</ul>
				</li>
				<li class='has-sub'><a href="#SalaryHead">Salary Head</a>
					<ul>
						<li><a href="#ListSalaryHead">List Salary Head</a></li>
						<li><a href="#ListSalaryFormula">List Salary Formula</a></li>
						<li><a href="#SalaryHeadConfiguration">Salary Head Configuration</a></li>
						<li><a href="#SalaryHeadDefaultValues">Salary Head Default Values</a></li>
					</ul>
				</li>
				<li><a href="#SavingMasterRequest">Saving Master Request</a></li>
				<li><a href="#PayrollProfile">Payroll Profile</a></li>
				<li><a href="#PayrollLeaveEntry">Payroll Leave Entry</a></li>
				<li><a href="#PayrollTransferEntry">Payroll Transfer Entry</a></li>
				<li><a href="#SalaryProcessing">Salary Processing</a></li>
				<li><a href="#UnlockSalary">Unlock Salary</a></li>
				<li class='has-sub'><a href="#Report">Report</a>
					<ul>
						<li><a href="#SalarySlip">Salary Slip</a></li>
						<li><a href="#DepartmentWiseAbstract">Department Wise Abstract</a></li>
						<li><a href="#DDOWiseAbstract">DDO Wise Abstract</a></li>
						<li><a href="#DDOWiseScheduleBS">DDO Wise Schedule BankStatment</a></li>
						<li><a href="#DDOWiseSchedule">DDO Wise Schedule</a></li>
					</ul>
				</li>
			</ul>
		</li>






        <li class='has-sub'><a href="#Upload">Upload</a>
        	<ul>
        		<li><a href="#UploadLogo">Upload Logo</a></li>
        		<li><a href="#UploadDepartment">Upload Department</a></li>
		        <li><a href="#UploadDesignation">Upload Designation</a></li>
		       	<li><a href="#UploadScheme">Upload Scheme</a></li>
		        <li><a href="#UploadDDO">Upload DDO</a></li>
		        <li><a href="#UploadStaffPosition">Upload Staff Position</a></li>
		        <li><a href="#UploadStaffList">Upload Staff List</a></li>
		        <li><a href="#UploadStaffPhoto">Upload Staff Photo</a></li>
		        <li><a href="#UploadServiceData">Upload Service Data</a></li>
		        <li><a href="#UploadHODList">Upload HOD List</a></li>
		        <li><a href="#UploadUOList">Upload UO List</a></li>
		        <li><a href="#UploadTransferOrder">Upload Transfer Order</a></li>
		        <li><a href="#UploadSupportDocuments">Upload Support Document</a></li>
        	</ul>
        </li>
		  
			 
        <li class='has-sub'><a href="#Announcement">Announcement</a>
        	<ul>
        		<li><a href="#Announcements">Announcements</a></li>
        	</ul>
        </li>
                       
	    <li class='has-sub'><a href="#Reports">Reports</a>
	    	<ul>
	    		<li><a href="#ProfileCompletenessList">Profile Completeness List</a></li>
	    		<li><a href="#EmployeeList">Employee List</a></li>
	    		<li><a href="#RetiredEmployeeList">Retired Employee List</a></li>
	    		<li><a href="#DisciplineWiseReport">Discipline Wise Report</a></li>
	    		<li><a href="#DepartmentWiseStaffList">Department Wise Staff<br> List</a></li>
	    		<li><a href="#DesignationWiseStaffList">Designation Wise Staff List</a></li>
	    		<li><a href="#StaffVacancyPostWise">Staff Vacancy Post Wise</a></li>
	    		<li><a href="#StaffVacancyUOWise">Staff Vacancy UO Wise</a></li>
	    		<li><a href="#Position-Summary">Position-Summary</a></li>
	    		<li><a href="#StaffPositionwithNameUOWise">Staff Position with<br> Name UO Wise</a></li>
	    		<li><a href="#StaffPositionwithNamePostWise">Staff Position with Name Post Wise</a></li>
	    		<li><a href="#StaffSeniorityList">Staff Seniority List</a></li>
	    		<li><a href="#ListofHOD">List Of HOD</a></li>
	    		<li><a href="#ListofUO">List Of UO</a></li>
	    		<li><a href="#ViewEarnedLeave">View Earned Leave</a></li>
	    	</ul>
	    </li>


                    
	    <li class='has-sub'><a href="#Archives">Archives</a>
	    	<!--ul>
	    		<li><a href="#LogDetails">Log Detail</a></li>
	    		<li><a href="#AuthorityArchive">Authority Archives</a></li>
	    		<li><a href="#StaffPositionArchives">Staff Position Archives</a></li>
	    		<li><a href="#DDOArchive">DDO Archives</a></li>
	    		<li><a href="#SchemeArchive">Scheme Archive</a></li>
	    		<li><a href="#SalaryGradeMasterArchive">Salary Grade Master Archive</a></li>
	    		<li><a href="#SCwithUOArchive">Sc with UO Archive</a></li>
	    		<li><a href="#BankDetailArchive">Bank Detail Archive</a></li>
	    		<li><a href="#MapUserwithRoleArchive">Map User With Role <br> Archive</a></li>
	    		<li><a href="#MapSchemeWithDepartmentArchive">Map Scheme With <br>Department Archive</a></li>
	    		<li><a href="#DepartmentArchive">Department Archive</a></li>
	    		<li><a href="#AnnouncementArchive">Announcement Archive</a></li>
	    	</ul-->
	    </li>
	    <li class="has-sub"><a href="#Backups">Backups</a>
	    	<ul>
	    		<li><a href="#BackupList">Backup List</a></li>
	    	</ul>
	    </li>

	    <li class="has-sub"><a >Profile</a>
	    	<ul>
	    		<li><a href="#ViewProfile">View Profile</a></li>
				<li><a href="#ChangePassword">Change Password</a></li>
				<li><a href="#Logout">Logout</a></li>
			</ul>
		</li>
	</li>
</ul>
</div>
</div>

			<div class="sideright">
					<section id="BrihaspatiStaffInformationSystem">
					<div class="row-fluid">
						<h2>Brihaspati Staff Information System (BSIS)</h2>
					</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
This Web-based information system was designed to allow you to view and maintain your employee information throughout the year. You should use this system to maintain your biographical information on your employee record. Changes made on the Employee Information System will not update any other university biographical information such as student or applicant information.
								</p>
							</ol>
						</font>
					</div>
					</section>


					<section id="Dashboard">
						<div class="row-fluid">
							<h2>Dashboard</h2>
						</div>
						<div class="row-fluid">
							<font size="4">
								<ol>
									<p align="justify" STYLE="line-height: 150%">
									This page consists of details of University Profile, Program, Seat and Fees. The page is constantly updated on the basis of the activities carried out in the system. 
									</p>
								</ol>
							</font>						
						<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/dashboard.png"  width="100%">
						</div>
					</section>

					<section id="Setup">
						<div class="row-fluid">

							<h2>Setup</h2>
						</div>
						<section id="EmailSetting">
						<div class="row-fluid">
							<h2>Email Setting</h2>
                                                        <h2>Add Email Setting</h2>
						</div>


						<div class="row-fluid">
							<font size="4">
									<ol>
									<p align="justify" STYLE="line-height: 150%">	
						By Filling all email setting data we can setup outgoing email.<br>
						The format of email setting are given below:-
									</p>	
						<P ALIGN=JUSTIFY STYLE="margin-bottom: 0in; line-height: 150%">
						<table border="1px solid blue" style="width:700px">
						<tr>
						<td><B>Field</B></td>
						<td><B>Description</B></td>
						</tr>
						<tr>
						<td><B>Email protocol</B></td>
						<td>Select Email protocol Ex:-smtp, IMAP, POP</td>
						</tr>
						<tr>
						<td><B>Email Hostname</B></td>
						<td>Ex:-smtp.cc.iitk.ac.in or IP Address: 172.3.1.5</td>
						</tr>
						<tr>
						<td><B>Email Port</B></td>
						<td>Ex:-25</td>
						</tr>
						<tr>
						<td><B>Username</B></td>
						<td>Ex:-palseema30 or palseema30@gmail.com</td>
						</tr>
						<tr>
						<td><B>Password</B></td>
						<td> your-password</td>
						</tr>
						<tr>
						<td><B>Sendername</B></td>
						<td>Ex:-Brihaspati</td>
						</tr>
						</P>
						</table>					
								</ol>
							</font>
						<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/setup/addemail.png" width="100%">
						<h2>View Email</h2>
						<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/setup/viewemail.png"  width="100%">
						</div>
					</section>
				

<section id="Role">                                     
                                                <h2>Role</h2>
                                    
 					<h2>Add Role</h2>
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
From here Admin can add role by filling Role Name and Role Description.<br>
The format of Add Role are given below:-
<P ALIGN=JUSTIFY STYLE="margin-bottom: 0in; line-height: 150%">
						<table border="2" style="width:700px">
						<tr>
						<td><B>Field</B></td>
						<td><B>Description</B></td>
						</tr>
						<tr>
						<td><B>Role Name</B></td>
						<td>Admin, System Administrator</td>
						</tr>
						<tr>
						<td><B>Role Description</B></td>
						<td>System Admin a person who manages the operation of a computer system.</td>
						</tr>
						</table>
</P>

                                                                 </p>
                                                        </ol>
<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/setup/addrole.png"  width="100%">
<h2> View Role </h2>
<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/setup/viewrole.png"  width="100%">



                                                </font>
                                       
                                        </section>


				<section id="Category">
                                        <div class="row-fluid">
                                                <h2>Category</h2>
						</div>
                           
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
 Admin can add category by filling Category Name, Category Code, Category Short Name, Category Description.<br>
The format of Add category are given below:-
<P ALIGN=JUSTIFY STYLE="margin-bottom: 0in; line-height: 150%">
						<table border="2" style="width:700px">
						<tr>
						<td><B>Field</B></td>
						<td><B>Description</B></td>
						</tr>
						<tr>
						<td><B>Category Name</B></td>
						<td>Scheduled Tribe, Other Backward Class, General etc.</td>
						</tr>
						<tr>
						<td><B>Category Code</B></td>
						<td>01, 02, 03, st04, sc-5 etc.</td>
						</tr>
						<tr>
						<td><B>Category Short Name</B></td>
						<td>ST, SC, OBC, GEN, PH etc.</td>
						</tr>
						<tr>
						<td><B>Category Description </B></td>
						<td>Reserved Category, Unreserved Category.</td>
						</tr>

</table>
</P>
                                                                 </p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/setup/addcategory.png" width="100%">
<h2>View Category</h2>
<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/setup/viewcategory.png" width="100%">

                                        </div>
                                        </section>


			

 <section id="StudyCenter">
                                        <div class="row-fluid">
                                                <h2>Study Center</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
Admin can add study center by Select your University, Campus Code, Campus Name, Campus Nick Name, Address, Country, State, City, District, Pincode, Phone, Fax, Status, Start Date, Close Date, Website, Incharge, Mobile.<br>
The format of Add Study Center are given below:-
<P ALIGN=JUSTIFY STYLE="margin-bottom: 0in; line-height: 150%">
                                                <table border="2px solid black" style="width:700px">
                                                <tr>
                                                <td><B>Field</B></td>
                                                <td><B>Description</B></td>
                                                </tr>
                                                <tr>
                                                <td><B>Choose your University</B></td>
                                                <td>Ex:-Indira Gandhi National Tribal University</td>
                                                </tr>
                                                <tr>
                                                <td><B>Campus Code</B></td>
                                                <td>Ex:-CU001</td>
                                                </tr>
                                                <tr>
                                                <td><B>Campus Name</B></td>
                                                <td>Ex:-Regional Campus</td>
                                                </tr>
                                                <tr>
                                                <td><B>Campus Nick Name</B></td>
                                                <td>Ex:-RC</td>
                                                </tr>
                                                <tr>
                                                <td><B>Address</B></td>
                                                <td>Enter Address</td>
                                                </tr>
                                                <tr>
                                                <td><B>Country</B></td>
                                                <td>Select Country</td>
                                                </tr>
                                                <tr>
                                                <td><B>State</B></td>
                                                <td>Select State</td>
                                                </tr>
                                                <tr>
                                                <td><B>City</B></td>
                                                <td>Select City</td>
                                                </tr>
                                                <tr>
                                                <td><B>District</B></td>
                                                <td>Enter District</td>
                                                </tr>
						 <tr>
                                                <td><B>Pincode</B></td>
                                                <td>Enter Pincode</td>
                                                </tr>
                                                <tr>
                                                <td><B>Phone</B></td>
                                                <td>Enter Phone Number</td>
                                                </tr>
                                                <tr>
                                                <td><B>Fax</B></td>
                                                <td>Enter Fax Number</td>
                                                </tr>
                                                <tr>
                                                <td><B>Status</B></td>
                                                <td>Enter Status Ex:-Active, Inactive</td>
                                                </tr>
                                                <tr>
                                                <td><B>Start Date</B></td>
                                                <td>Select Start Date </td>
                                                </tr>
                                                <tr>
                                                <td><B>Close Date</B></td>
                                                <td>Select Close Date</td>
                                                </tr>
                                                <tr>
                                                <td><B>Website</B></td>
                                                <td>Enter Your Website</td>
                                                </tr>
                                                <tr>
                                                <td><B>Incharge</B></td>
                                                <td>Enter Incharge Name</td>
                                                </tr>
                                                <tr>
                                                <td><B>Mobile</B></td>
                                                <td>Enter Mobile Number</td>
                                                </tr>
                                                </table>
                                                </P>
                                                                 </p>
                                                        </ol>
                                                </font>
		<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/setup/addstudycenter.png"  width="100%">
		<h2>View Study Center</h2>
		<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/setup/viewstudycenter.png"  width="100%">

                                        </div>
                                        </section>

                                        <section id="Authority">
                                        <div class="row-fluid">
                                                <h2>Authority</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
                        Admin can add  Authority by enter Authority Name, Authority Nick Name,  Authority Email.<br>
                         The format of Add category are given below:-
                        <P ALIGN=JUSTIFY STYLE="margin-bottom: 0in; line-height: 150%">
                                                <table border="2px solid black" style="width:700px">
                                                <tr>
                                                <td><B>Field</B></td>
                                                <td><B>Description</B></td>
                                                </tr>
                                                <tr>
                                                <td><B> Authority Name</B></td>
                                                <td>Enter Authority Name Ex:-Director, Finance Officer, Deputy Registar Account, Assistant Registar Account</td>
                                                </tr>
                                                <tr>
                                                <td><B>Authority Nickname</B></td>
                                                <td>Enter Authority Nickname.Ex:-FO, DRA, ARA</td>
                                                </tr>
                                                <tr>
                                                <td><B>Authority Email</B></td>
                                                <td>Enter Authority Email. Ex:-aracc@iitk.ac.in, dracc@iitk.ac.in</td>
                                                </tr>
                                                 </table>
                                                </P>
                                                </p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/setup/addauthority.png"  width="100%">
<h2> View Authority</h2>
<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/setup/viewauthority.png" width="100%">
                                        </div>
                                        </section>



<section id="Department">
                                        <div class="row-fluid">
                                                <h2>Department</h2>
                                                 <h3>Add Department</h3>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
			Admin can add department by Choose your University, Choose your Campus, School Code, School Name, Department Code, Department Name, Department Nick Name and Department Description.<br>
                               The format of Add category are given below:-  
						<P ALIGN=JUSTIFY STYLE="margin-bottom: 0in; line-height: 150%">
						<table border="2px solid black" style="width:700px">
						<tr>
						<td><B>Field</B></td>
						<td><B>Description</B></td>
						</tr>
						<tr>
						<td><B>Choose your University</B></td>
						<td>Ex:-Indira Gandhi National Tribal University</td>
						</tr>
						<tr>
						<td><B>Choose your Campus</B></td>
						<td>Ex:-Indira Gandhi National Research Center</td>
						</tr>
						<tr>
						<td><B>School Code</B></td>
						<td>SBS</td>
						</tr>
						<tr>
						<td><B>School Name</B></td>
						<td>School of Basic Science </td>
						</tr>
						<tr>
						<td><B>Department Code</B></td>
						<td>Phy001</td>
						</tr>
						<tr>
						<td><B>Department Name</B></td>
						<td>Physics Department</td>
						</tr>
						<tr>
						<td><B>Department Nick Name</B></td>
						<td>Phy</td>
						</tr>
						<tr>
						<td><B>Department Description</B></td>
						<td>Department of Physics</td>
						</tr>
						</table>
						</P>                                


</p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/setup/adddepartment.png"  width="100%">
             <h2>View Department </h2>                           
                                       
<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/setup/viewdepartment.png" width="100%">
                                        </div>
                                        </section>



<section id="SalaryGradeMaster">
	<div class="row-fluid">
		<h2>Salary Grade Master</h2>
	</div>
	<div class="row-fluid">
		<font size="4">
			<ol>
				<p align="justify">
					Admin can add payband and can view .
				</p>
			</ol>
		</font>
		<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/setup/addsalarygrade1.png"  width="100%">
		<h3>View Salary Grade</h3>
		<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/setup/viewsalarygrade1.png"  width="100%">
		
		
		
	</div>
</section>



<section id="Designation">
                                        <div class="row-fluid">
                                                <h2>Designation</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
                        Admin can add Designation by enter Designation Name, Designation Code, Designation short, Designation Description.<br>
                         The format of Add category are given below:-
			<P ALIGN=JUSTIFY STYLE="margin-bottom: 0in; line-height: 150%">
                                                <table border="2px solid black" style="width:700px">
                                                <tr>
                                                <td><B>Field</B></td>
                                                <td><B>Description</B></td>
                                                </tr>
                                                <tr>
                                                <td><B>Designation Name</B></td>
                                                <td>Enter Designation Name Ex:-Manager, Assistant Manager</td>
                                                </tr>
                                                <tr>
                                                <td><B>Designation Code</B></td>
                                                <td>Enter Designation Code.Ex:-01,02</td>
                                                </tr>
                                                <tr>
                                                <td><B>Designation short</B></td>
                                                <td>Enter Designation short. Ex:-AM, AR</td>
                                                </tr>
                                                <tr>
                                                <td><B>Designation Description</B></td>
                                                <td>Enter Designation Description.</td>
                                                </tr>
						 </table>
                                                </P>
                                                </p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/setup/adddesignation.png"  width="100%">
<h2>View Designation</h2>
         
<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/setup/viewdesignation.png" width="100%">
                                        </div>
                                        </section>



			
  
<section id="Scheme">
                                        <div class="row-fluid">
                                                <h2>Scheme</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
Admin can add Scheme by filling Scheme Name, Scheme Code, Scheme Short, Scheme Description.
                                                                 </p>
						<P ALIGN=JUSTIFY STYLE="margin-bottom: 0in; line-height: 150%">
						<table border="3px solid black" style="width:700px">
						<tr>
						<th><B>Field</B></th>
						<th><B>Description</B></th>
						</tr>
						<tr>
						<td><B>Scheme Name</B></td>
						<td>MAIN</td>
						</tr>
						<tr>
						<td><B>Scheme Code</B></td>
						<td>El0023</td>
						</tr>
						<tr>
						<td><B>Scheme Short</B></td>
						<td>mio</td>
						</tr>
						<tr>
						<td><B>Scheme Description</B></td>
						<td>...........</td>
						</tr>
</table>
</P>

                                                        </ol>
<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/setup/addscheme.png" width="100%">
<h3>View Scheme</h3>

<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/setup/viewscheme.png" width="100%">
                                                </font>
                                       
                                        </div>
                                        </section>




                                        <section id="DDO">
                                        	<div class="row-fluid">
                                        		<h2>DDO</h2>
                                        	</div>
                                        	<div class="row-fluid">
                                        		<font size="4">
                                        			<ol>
                                        				<p align="justify">
                                        					admin can view DDO list
                                        				</p>
                                        			</ol>
                                        		</font>
                                     <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/setup/addddo.png" width="100%">
                                     <h3>View DDO</h3>
                                     <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/setup/viewddo.png" width="100%">
                                        		
                                        	</div>
                                        </section>


           <section id="Subject">
                                        <div class="row-fluid">
                                                <h2>Subject </h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
Admin can add Subject by filling Subject Name, Subject Code, Subject  Short, Subject  Description.
                                                                 </p>
						<P ALIGN=JUSTIFY STYLE="margin-bottom: 0in; line-height: 150%">
						<table border="3px solid black" style="width:700px">
						<tr>
						<th><B>Field</B></th>
						<th><B>Description</B></th>
						</tr>
						<tr>
						<td><B>Subject Name</B></td>
						<td>ENGLISH</td>
						</tr>
						<tr>
						<td><B>Subject  Code</B></td>
						<td>El0023</td>
						</tr>
						<tr>
						<td><B>Subject  Short</B></td>
						<td>eng</td>
						</tr>
						<tr>
						<td><B>Subject  Description</B></td>
						<td>...english is language........</td>
						</tr>
</table>
</P>

                                                        </ol>
<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/setup/addsubjectlist.png" width="100%">
<h3>View Subject</h3>
<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/setup/viewsubjectlist.png" width="100%">
                                                </font>
                                       
                                        </div>
                                        </section>




                                        <section id="ViewLeaveType">
                                        	<div class="row-fluid">
                                        		<h2>View Leave Type</h2>
                                        	</div>
                                        	<div class="row-fluid">
                                        		<font size="4">
                                        			<ol>
                                        				<p align="justify">
                                        					admin can view  Leave Type list
                                        				</p>
                                        			</ol>
                                        		</font>
                               <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/setup/addleavetype.png" width="100%">
                               <h3>View Leave type</h3>
                               <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/setup/viewleavetype.png" width="100%">
                                        		<br><br><br><br><br><br><br><br><br><br><br><br>
                                        	</div>
                                        </section>





			
<section id="MapUserwithRole">
                                        <div class="row-fluid">
                                        	<h2 > MAP</h2>
                                                <h2>Map User with Role</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
From here admin can Map User with Role by Choose your Campus, Choose your Department, Select your Role, Select User Type, Select Username.<br>
The format of Map User with Role list are given below:-
						<P ALIGN=JUSTIFY STYLE="margin-bottom: 0in; line-height: 150%">
                                                <table border="2px solid black" style="width:700px">
                                                <tr>
                                                <td><B>Field</B></td>
                                                <td><B>Description</B></td>
                                                </tr>
						<tr>
                                                <td><B>Choose your Campus</B></td>
                                                <td>Select Campus from dropdown.</td>
                                                </tr>
						<tr>
                                                <td><B>Choose your Department</B></td>
                                                <td>Select Department from dropdown.</td>
                                                </tr>
						<tr>
                                                <td><B>Select your Role</B></td>
                                                <td>Select Role from dropdown.</td>
                                                </tr>
						<tr>
                                                <td><B>User Type</B></td>
                                                <td>Select User Type from dropdown.</td>
                                                </tr>
						<tr>
                                                <td><B>Select Username</B></td>
                                                <td>Select Username from dropdown.</td>
                                                </tr>
						</table>
						</P>
                                                                 </p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/map/addmapuserwithrole.png"  width="100%"> 
<h2>View User With Role</h2>
<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/map/mapuserwithrole.png"  width="100%"> 
                                    
                                         </div>
                                    </section>


<section id="MapAuthorityandUser">
	<div class="row-fluid">
		<h2>Map Authority and User</h2>
	</div>
	<div class="row-fluid">
		<font size="4">
			<p align="justify">
				admin cav view authority with User List
			</p>
		</font>
		<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/map/mapuserandauthority.png"  width="100%">
		<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/map/viewmapuserandauthority.png"  width="100%">  
	</div>
</section>     

<section id="MapStudyCenterwithUO">
	<div class="row-fluid">
		<h2>Map Study Center with UO</h2>
	</div>
	<div class="row-fluid">
		<font size="4">
			<p align="justify">
				admin cav view Map Study Center with UO List
			</p>
		</font>
		<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/map/addmapstudycenterwithUO.png"  width="100%"> 
		<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/map/mapstudycenterwithUO.png"  width="100%"> 
	</div>
</section>    





    <section id="SetUO">
	<div class="row-fluid">
		<h2>Set UO</h2>
	</div>
	<div class="row-fluid">
		<font size="4">
			<p align="justify">
				admin cav view and add UO List
			</p>
		</font>
		<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/map/adduo.png"  width="100%"> 
	</div>
</section>                                       


<section id="SetHOD">
	<div class="row-fluid">
		<h2>Set HOD </h2>
	</div>
	<div class="row-fluid">
		<font size="4">
			<p align="justify">
				admin cav view and add HOD List
			</p>
		</font>
		<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/map/addhod.png"  width="100%"> 
	</div>
</section>        


<section id="MapUserWithSocieties">
	<div class="row-fluid">
		<h2>Map User With Societies</h2>
	</div>
	<div class="row-fluid">
		<font size="4">
			<p align="justify">
				adnim can add user society by adding society name , society head, secretary,treasure, members
			</p>
		</font>
		<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/map/mapwithsociety.png"  width="100%"> 
		<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/map/viewmapwithsociety.png"  width="100%">
		<br><br><br><br><br><br><br><br><br><br><br><br>
	</div>
</section>      





<section id="StaffPosition">
                                        <div class="row-fluid">
                                        	<h2>STAFF MANAGEMENT</h2>
                                        	<br>
                                                <h2>Staff Position</h2>
                                        </div>
                                        <div class="row-fluid">
                                        	<font size="4">
                                        		<p align="justify">
                                        			admin can view and edit staff position
                                        			Job position within a chain of command of an organization that has the responsibility of providing information and advice to personnel in the line position. See also line and staff management.<br>
Admin can add staff position.  Enter the details in the given fields.<br>
Fill all information and now press the Submit Button for Staff Position

                                        		</p>
                                        	</font>
                         <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/staffmanagement/staffposition.png"  width="100%">
                         <h2>add staff position</h2> 
                       <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/staffmanagement/addstaffposition.png"  width="100%">
                                        	
                                        </div>
</section>


<section id="StaffProfile">
                                        <div class="row-fluid">
                                                <h2>Staff Profile</h2>
                                        </div>


                                        <div class="row-fluid">
                                                <h2>View Employee List</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
Admin can also View staff List after adding Staff details. 
                                                        </p>
                                                        </ol>
                               <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/staffmanagement/staffprofile.png"width="100%">       
					</font>
                                         </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
Admin can add Staff Profile:<br>
Enter the details in the given fields.<br>
Fill all information and now press the Submit Button for Staff Registration.
                                                        </p>
                                                        </ol>
             <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/staffmanagement/addemployee1.png"width="100%"> 
             <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/staffmanagement/addemployee2.png"width="100%"> <br>
             <br><br><br>

             <h2>View Detail</h2>
             <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/staffmanagement/editemployee1.png"width="100%">
             <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/staffmanagement/editemployee2.png"width="100%">
             <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/staffmanagement/editemployee3.png"width="100%">
             <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/staffmanagement/editemployee4.png"width="100%">
             <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/staffmanagement/editemployee5.png"width="100%">
             <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/staffmanagement/editemployee6.png"width="100%">
             <h3> Performance detail consist of more option </h3>
             <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/staffmanagement/editemployee6a.png"width="100%">
             <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/staffmanagement/editemployee6b.png"width="100%">
             <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/staffmanagement/editemployee6c.png"width="100%">
             <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/staffmanagement/editemployee6d.png"width="100%">
             <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/staffmanagement/editemployee6e.png"width="100%">


					</font>

                                     </div>

                                    </section>







		

<section id="StaffTransferandPosting">
                                        <div class="row-fluid">
                                                <h2>Staff Transfer and Posting</h2>
                                        </div>
                                        <div class="row-fluid">
                                        	<font size="4">
                                        		<ol>
                                        			<p align="justify">
                                        				tranfer and posting of employee can be add and edit by admin
                                        			</p>
                                        		</ol>
                                        		<h3>Staff Transfer List </h3>
                                        		 <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/staffmanagement/addstafftransfer.png" width="100%"> 
                                        		 <h3> Add and Edit staff transfer list</h3>
                   <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/staffmanagement/editstafftransfera.png" width="100%">
                   <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/staffmanagement/editstafftransferb.png" width="100%">
                   <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/staffmanagement/editstafftransferc.png" width="100%">
                   <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/staffmanagement/editstafftransferd.png" width="100%">
                                        		</font>
                                        	
                                        </div>
</section>



<section id="StaffLeaving">
	<div class="row-fluid">
		<h2>Staff Leaving </h2>updateretiredemployeeprofile.png
	</div>
	<div class="row-fluid">
		<font size="4">
			<p align="justify">
				staff leaivng detail are shown :
			</p>
			<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/staffmanagement/staffleaving.png" width="100%">
		</font>
		
	</div>
</section>


<section id="RetiredEmployeeProfile">
	<div class="row-fluid">
		<h2>Retired Employee Profile </h2>
	</div>
	<div class="row-fluid">
		<font size="4">
			<p align="justify">
				retired staff profile detail are shown :
			</p>
			<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/staffmanagement/retiredemployeeprofile.png" width="100%">
		</font>
		
	</div>
</section>


<!--section id="RetiredEmployeeUpdate">
	<div class="row-fluid">
		<h2>Retired Employee Update </h2>
	</div>
	<div class="row-fluid">
		<font size="4">
			<p align="justify">
				admin can add and update record here
			</p>
			<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/staffmanagement/updateretiredemployeeprofile.png" width="100%">
		</font>
		
	</div>
</section>


<!--section id="RetiredEmployee">
	<div class="row-fluid">
		<h2>Retired Employee Profile </h2>
	</div>
	<div class="row-fluid">
		<font size="4">
			<p align="justify">
				admin can add and update record here
			</p>
			<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/staffmanagement/updateretiredemployeeprofile.png" width="100%">
		</font>
		
	</div>
</section-->


<section id="BankDetail">
	<div class="row-fluid">
		<h2>PAYROLL MANAGEMENT</h2>
		<br>
		<h2>View And Add Bank Detail</h2>
	</div>
	<div class="row-fluid">
		<font size="4">
			<p align="justify">
				admin can add and update  bank record<br>
				filling up essential info <br>
			</p>
			<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/salarysetup/addbankdetail.png" width="100%">
			<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/salarysetup/viewbankdetail.png" width="100%">
		</font>
		
	</div>
</section>

<section id="PayMatrix">
	<div class="row-fluid">
		<h2>Pay Matrix </h2>
	</div>
	<div class="row-fluid">
		<font size="4">
			<p align="justify">
				<h3>add Pay Matrix</h3>
			<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/salarysetup/addpayymtrix.png" width="100%">
				
			<h3>View Pay Matrix</h3>
			<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/salarysetup/viewpayymtrix.png" width="100%">
		</font>
		
	</div>
</section>

<section id="TaxSlab">
	<div class="row-fluid">
		<h2>Tax Slab Master</h2>
	</div>
	<div class="row-fluid">
		<font size="4">
			<p align="justify">
				admin can add and view Tax Slab Record<br>
				
			</p>
	<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/salarysetup/addtaxslabmaster.png" width="100%">
	<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/salarysetup/viewtaxslabmaster.png" width="100%">
		</font>
		
	</div>
</section>

<section id="SavingMasterDetail">
	<div class="row-fluid">
		<h2>Saving Master Detail</h2>
	</div>
	<div class="row-fluid">
		<font size="4">
			<p align="justify">
				admin can view saving master request<br>
				
			</p>
			<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/salarysetup/savingmasterdetail.png" width="100%">
		</font>
		
	</div>
</section>

<section id="HRAPlaces">
	<div class="row-fluid">
		<h2>HRA Places</h2>
	</div>
	<div class="row-fluid">
		<font size="4">
			<p align="justify">
				admin can view HRA places <br>
				
			</p>
			<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/salarysetup/hraplacegrade.png" width="100%">
		</font>
		
	</div>
</section>

<section id="HRAGrade">
	<div class="row-fluid">
		<h2>HRA Grades</h2>
	</div>
	<div class="row-fluid">
		<font size="4">
			<p align="justify">
				admin can view , add , edit HRA grades <br>
				
			</p>
			<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/salarysetup/addhragrade.png" width="100%">
			<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/salarysetup/viewhragrade.png" width="100%">
		</font>
		
	</div>
</section>


<section id="RentFreeHRA">
	<div class="row-fluid">
		<h2>Rent Free HRA </h2>
	</div>
	<div class="row-fluid">
		<font size="4">
			<p align="justify">
				list of free HRA  <br>
				
			</p>
			<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/salarysetup/addhragrade.png" width="100%">
			<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/salarysetup/viewhragrade.png" width="100%">

		</font>
		
	</div>
</section>


<section id="RentGradePercentage">
	<div class="row-fluid">
		<h2>Rent Grade Percentage </h2>
	</div>
	<div class="row-fluid">
		<font size="4">
			<p align="justify">
				list of grade percentage  <br>
				
			</p>
			<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/salarysetup/addrentgradepercentage.png" width="100%">
			<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/salarysetup/rentgradepercentage.png" width="100%">
		</font>
		
	</div>
</section>


<section id="CCAPlaces">
	<div class="row-fluid">
		<h2>CCA Places </h2>
	</div>
	<div class="row-fluid">
		<font size="4">
			<p align="justify">
				list of CCA Places  <br>
				
			</p>
			<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/salarysetup/ccaplacegrade.png" width="100%">
		</font>
		
	</div>
</section>

<section id="CCAGrades">
	<div class="row-fluid">
		<h2>CCA Grades </h2>
	</div>
	<div class="row-fluid">
		<font size="4">
			<p align="justify">
				list of CCA Grades and admin can add CCA Grades also <br>
				
			</p>
			<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/salarysetup/addccagrade.png" width="100%">
			<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/salarysetup/viewccagrade.png" width="100%">
		</font>
		
	</div>
</section>


<section id="SocietyMaster">
	<div class="row-fluid">
		<h2>Society Master </h2>
	</div>
	<div class="row-fluid">
		<font size="4">
			<p align="justify">
				list of Society Master and admin can add Society Master also <br>
				
			</p>
			<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/salarysetup/addsociety.png" width="100%">
			<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/salarysetup/viewsociety.png" width="100%">
		</font>
		
	</div>
</section>


<section id="ListSalaryHead">
	<div class="row-fluid">
		<h2> List Of Salary Head</h2>
	</div>
	<div class="row-fluid">
		<font size="5">
			<p align="justify">
				list of salary head
				<br>
				
			</p>
	<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/salaryhead/addlistsalaryhead.png" width="100%">
	<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/salaryhead/listsalaryhead.png" width="100%">
		</font>

		
	</div>
</section>




<section id="ListSalaryFormula">
	<div class="row-fluid">
		<h2>List of Salary Formula  </h2>
	</div>
	<div class="row-fluid">
		<font size="5">
			<p align="justify">
				list of salary Formula
				<br>
				
			</p>
			<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/salaryhead/listsalaryfomula.png" width="100%">
		</font>
		
	</div>
</section>




<section id="SalaryHeadConfiguration">
	<div class="row-fluid">
		<h2> Salary Head Configuration </h2>
	</div>
	<div class="row-fluid">
		<font size="5">
			<p align="justify">
				list of Salary Head Configuration
				<br>
				
			</p>
			<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/salaryhead/salaryheadconfiguration.png" width="100%">
		</font>
		
	</div>
</section>

<section id="SalaryHeadDefaultValues">
	<div class="row-fluid">
		<h2>Salary Head Defult Values </h2>
	</div>
	<div class="row-fluid">
		<font size="5">
			<p align="justify">
				Salary Head Defualt Values
				<br>
				
			</p>
			<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/salaryhead/salaryheaddefaultvalue.png" width="100%">
		</font>
		
	</div>
</section>

<section id="SavingMasterRequest">
	<div class="row-fluid">
		<h2>Saving Master Request </h2>
	</div>
	<div class="row-fluid">
		<font size="4">
			<p align="justify">
				Here saving master request detail list can be viewed
				
			</p>
		</font>
	<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/savingmasterrequest.png" width="100%">	
	</div>
</section>



<section id="PayrollProfile">
	<div class="row-fluid">
		<h2>PayRoll Profile </h2>
	</div>
	<div class="row-fluid">
		<font size="4">
			<p align="justify">
				payroll profile can viewed , added and can be edited
				
			</p>
		</font>
	<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/payrollprofile/payrollprofilea.png" width="100%">
	<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/payrollprofile/payrollprofileb.png" width="100%">
	<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/payrollprofile/payrollprofilec.png" width="100%">
	<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/payrollprofile/payrollprofiled.png" width="100%">
	<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/payrollprofile/payrollprofilee.png" width="100%">
	<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/payrollprofile/payrollprofilef.png" width="100%">
	<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/payrollprofile/payrollprofileg.png" width="100%">
	<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/payrollprofile/payrollprofileh.png" width="100%">
		

	</div>
</section>

<section id="PayrollLeaveEntry">
	<div class="row-fluid">
		<h2>Payroll Leave Entry </h2>
	</div>
	<div class="row-fluid">
		<font size="4">
			<p align="justify">
				payroll leave entries can be viewed and added
				
			</p>
		</font>
	<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/addpayrollleaveentry.png" width="100%">
	<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/viewpayrollleaveentry.png" width="100%">	
	</div>
</section>


<section id="PayrollTransferEntry">
	<div class="row-fluid">
		<h2>Payroll Transfer Entry </h2>
	</div>
	<div class="row-fluid">
		<font size="4">
			<p align="justify">
				payroll Transfer entries can be viewed and added
				
			</p>
		</font>
	<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/addpayrolltransferentry.png" width="100%">	
	<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/viewpayrolltransferentry.png" width="100%">
	</div>
</section>

<section id="SalaryProcessing">
	<div class="row-fluid">
		<h2>Salary Processing </h2>
	</div>
	<div class="row-fluid">
		<font size="4">
			<p align="justify">
				Salary Head list can be viewed and added in salary processing detail
				
			</p>
		</font>
	<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/salaryprocessing.png" width="100%">
	<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/salaryprocessing1.png" width="100%">
	<h3>Salary Processing slip editing</h3>
	<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/salaryprocessingeditslip.png" width="100%">	
	</div>
</section>


<section id="UnlockSalary">
	<div class="row-fluid">
		<h2>Unlock Salary </h2>
	</div>
	<div class="row-fluid">
		<font size="4">
			<p align="justify">
				Staff Salary Unlock  list can be viewed 
				
			</p>
		</font>
	<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/unlocksalary.png" width="100%">	
	</div>
</section>

<section id="SalarySlip">
	<div class="row-fluid">
		<h2>Salary Slip </h2>
	</div>
	<div class="row-fluid">
		<font size="4">
			
	<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/report/salaryslip.png" width="100%">	
	</div>
</section>

<section id="DepartmentWiseAbstract">
	<div class="row-fluid">
		<h2>Department Wise Abstract</h2>
	</div>
	<div class="row-fluid">
		<font size="4">
			
	<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/report/dept_wiseabstract.png" width="100%">	
	</div>
</section>



<section id="DDOWiseAbstract">
	<div class="row-fluid">
		<h2>DDO Wise Abstract</h2>
	</div>
	<div class="row-fluid">
		<font size="4">
			
	<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/report/ddo_wiseabstract.png" width="100%">	
	</div>
</section>



<section id="DDOWiseScheduleBS">
	<div class="row-fluid">
		<h2> DDO Wise Schedule Bank Statement </h2>
	</div>
	<div class="row-fluid">
		<font size="4">
			
	<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/report/ddo_wise_bank.png" width="100%">	
	</div>
</section>



<section id="DDOWiseSchedule">
	<div class="row-fluid">
		<h2>DDO Wise Schedule</h2>
	</div>
	<div class="row-fluid">
		<font size="4">
			
	<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/payrollmanagement/report/ddo_wise_sch.png" width="100%">	
	</div>
</section>






		
<section id="UploadLogo">
                                        <div class="row-fluid">
                                        	<h2>UPLOADS</h2> <br>
                                                <h2>Upload Logo</h2>
                                        </div>
                                        <div class="row-fluid">
                                        	<p fontsize="4" align="justify">
                                        		Administrators can upload their logo from here. Image must be a png, jpg format and size is 500 K.B.
                                        	</p>
                  <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/uploads/uploadlogo.png" width="100%">
                                        </div>
                         </section>





<section id="UploadDepartment">
	<div class="row-fluid">
        <h2>Upload Department</h2>
    </div>
    <div class="row-fluid">
        <font size="4">
            <ol>
            	<p align="justify" STYLE="line-height: 150%">
				Administrators can upload department list from here. ,must be a png, jpg format and size is 500 K.B.
                </p>
            </ol>
				<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/uploads/deptlist.png"  width="100%">
        </font>
    </div>
</section>
                                    
			

<section id="UploadDesignation">
                                        <div class="row-fluid">
                                                <h2>Upload Designation</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
				Administrators can upload designation list from here. ,must be a png, jpg format and size is 500 K.B.
                                                                 </p>
                                                      </ol>
				<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/uploads/dsignlist.png"  width="100%">
                                                </font>
                                         </div>
                                    </section>



<section id="UploadScheme">
                                        <div class="row-fluid">
                                                <h2>Upload Seheme</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
				Administrators can upload scheme list from here. ,must be a png, jpg format and size is 500 K.B.
                                                                 </p>
                                                      </ol>
				<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/uploads/schemelist.png"  width="100%">
                                                </font>
                                         </div>
                                    </section>

<section id="UploadDDO">
                                        <div class="row-fluid">
                                                <h2>Upload DDO </h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
				Administrators can upload DDO list from here. ,must be a png, jpg format and size is 500 K.B.
                                                                 </p>
                                                      </ol>
				<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/uploads/ddolist.png"  width="100%">
                                                </font>
                                         </div>
                                    </section>




                                    <!--section id="UploadStaffPosition">
                                        <div class="row-fluid">
                                                <h2>Upload Staff Position </h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
				Administrators can upload Staff position  list from here. ,must be a png, jpg format and size is 500 K.B.
                                                                 </p>
                                                      </ol>
				<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/uploads/schemelist.png.png"  width="100%">
                                                </font>
                                         </div>
                                    </section-->

                                   

                                    <section id="UploadStaffPosition">
                                        <div class="row-fluid">
                                                <h2>Upload Staff Position</h2>
                                        </div>
                                        <div class="row-fluid">
                                        	<font>                                                
<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/uploads/staffpositionlist.png"  width="100%">
</font>
                                         </div>
                                    </section>









			
		<section id="UploadStaffList">
                                        <div class="row-fluid">
                                                <h2>Upload Staff List</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
Administrators can upload multiple staff at a time. The file should have ".csv" extension & proper format. The files contain name, email, departmentid, roleid, campusid and mobile no all separated by  comma.<br>
<b>Example</b><br>
<b>name,email,departmentid,roleid,campasid,mobileno</b><br>
kanchan,kanchan@gmail.com,01,04,01,9415938783<br>
<b>Note:-</b></br>
All information are mandatory except mobile number.
                                                                 </p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/uploads/stafflist.png"  width="100%">
                                         </div>
                                    </section>







                                   <section id="UploadStaffPhoto">
                                        <div class="row-fluid">
                                                <h2>Upload Staff Photo </h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
				Administrators can upload Staff photo from here. ,must be a png, jpg format and size is 500 K.B.
                                                                 </p>
                                                      </ol>
				<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/uploads/staffphoto.png"  width="100%">
                                                </font>
                                         </div>
                                    </section>





                                     <section id="UploadServiceData">
                                        <div class="row-fluid">
                                                <h2>Upload Service Data  </h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
				Administrators can upload Service data list from here. ,must be a png, jpg format and size is 500 K.B.
                                                                 </p>
                                                      </ol>
				<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/uploads/servicedata.png"  width="100%">
                                                </font>
                                         </div>
                                    </section>




                                     <section id="UploadHODList">
                                        <div class="row-fluid">
                                                <h2>Upload  HOD List </h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
				Administrators can upload HoD list from here. ,must be a png, jpg format and size is 500 K.B.
                                                                 </p>
                                                      </ol>
				<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/uploads/hodlist.png"  width="100%">
                                                </font>
                                         </div>
                                    </section>




                                     <section id="UploadUOList">
                                        <div class="row-fluid">
                                                <h2>Upload UO list</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
				Administrators can upload UO list from here. ,must be a png, jpg format and size is 500 K.B.
                                                                 </p>
                                                      </ol>
				<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/uploads/uolist.png"  width="100%">
                                                </font>
                                         </div>
                                    </section>




                                     <section id="UploadTransferOrder">
                                        <div class="row-fluid">
                                                <h2>Upload Transfer Order</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
				Administrators can upload Transfer Order list from here. ,must be a png, jpg format and size is 500 K.B.
                                                                 </p>
                                                      </ol>
				<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/uploads/transferorder.png"  width="100%">
                                                </font>
                                         </div>
                                    </section>


                                     <section id="UploadSupportDocuments">
                                        <div class="row-fluid">
                                                <h2>Upload Support Document </h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
				
                                                                 </p>
                                                      </ol>
				<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/uploads/doclist.png"  width="100%">
				<br><br><br><br><br><br><br><br>
                                                </font>
                                         </div>
                                    </section>

                                    <section id="Announcements">
                                        <div class="row-fluid">
                                                <h2>Announcements</h2>
                                        </div>
                                        <div class="4">
                                        	<font size="4">
                                        		<p align="justify">
                                        			adminstrator can add and edit announcement
                                        		</p>
                                        	</font>
                         <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/announcement.png"  width="100%">
                                        </div>

                                        </section>





<section id="ProfileCompletenessList">
                                        <div class="row-fluid">
                                        	<h2>REPORTS</h2> <br>
                                        	
                                                <h2>Profile Completeness List</h2>
                                        </div>
                                        <div class="row-fluid">
                                        	<font size="4">
                                        		<p align="justify">
                                        			admin can view report of profile completeness on the basis of WORKING TYPE ,UO DEPARTMENT <br>
                                        			once report generated admin can take out print of it or can save it in PDF formate
                                        		</p>
                                        	</font>
                         <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/reports/profilecomletenesslist.png"  width="100%">
                                        </div>

                                        </section>



<section id="EmployeeList">
                                        <div class="row-fluid">
                                                <h2>Employee List</h2>
                                        </div>

                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                        	<p align="justify">
                                                        		admin can view list of employee in alphabetical order
                                        			once list generated admin can take out print of it or can save it in PDF formate
                                                        	</p>
                        <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/reports/employeelist.png"  width="100%">                                
                                                        </ol>
                                                </font>

                                         </div>
                                    </section>




<section id="RetiredEmployeeList">
                                        <div class="row-fluid">
                                                <h2>Retired Employee List</h2>
                                        </div>

                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                        	<p align="justify">
                                                        		admin can view list  of Retired Employee on the basis of WORKING TYPE ,UO DEPARTMENT , MONTH , YEAR, SELECT SEARCH FIELD, STRING <br>
                                        			once report generated admin can take out print of it or can save it in PDF formate
                                                        	</p>
                        <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/reports/retiredemployeelist.png"  width="100%">                                
                                                        </ol>
                                                </font>

                                         </div>
                                    </section>



<section id="DisciplineWiseReport">
                                        <div class="row-fluid">
                                                <h2>Discipline Wise Report </h2>
                                        </div>

                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                        	<p align="justify">
                                                        		admin can view Discipline Wise Report on the basis of WORKING TYPE ,UO , DISICPLINE <br>
                                        			once report generated admin can take out print of it or can save it in PDF formate
                                                        	</p>
                        <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/reports/disicplinewise.png"  width="100%">                                
                                                        </ol>
                                                </font>

                                         </div>
                                    </section>



                                    <section id="DepartmentWiseStaffList">
                                        <div class="row-fluid">
                                                <h2>Department Wise Staff List</h2>
                                        </div>

                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                        	<p align="justify">
                                                        		admin can view list of Department Wise Staff  on the basis of WORKING TYPE ,UO ,DEPARTMENT <br>
                                        			once report generated admin can take out print of it or can save it in PDF formate
                                                        	</p>
                        <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/reports/deptwisestafflist.png"  width="100%">                                
                                                        </ol>
                                                </font>

                                         </div>
                                    </section>



                                    <section id="DesignationWiseStaffList">
                                        <div class="row-fluid">
                                                <h2>Designation Wise Staff List </h2>
                                        </div>

                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol><p align="justify">
                                                        	admin can view rlist of Designation Wise Staff on the basis of WORKING TYPE, DESIGNATION ,
                                                        	UO , DEPARTMENT <br>
                                        			once report generated admin can take out print of it or can save it in PDF formate
                                                        </p>

                        <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/reports/desigwisestafflist.png"  width="100%">                                
                                                        </ol>
                                                </font>

                                         </div>
                                    </section>



                                    <section id="StaffVacancyPostWise">
                                        <div class="row-fluid">
                                                <h2>Staff Vacancy Post Wise</h2>
                                        </div>

                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                        	<p align="justify">
                                                        		admin can list  report of Staff Vacancy Post Wise on the basis of WORKING TYPE ,UO ,POST  <br>
                                        			once report generated admin can take out print of it or can save it in PDF formate
                                                        	</p>
                        <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/reports/staffvacancypostwise.png"  width="100%">                                
                                                        </ol>
                                                </font>

                                         </div>
                                    </section>



                                    <section id="StaffVacancyUOWise">
                                        <div class="row-fluid">
                                                <h2>Staff Vacancy UO Wise</h2>
                                        </div>

                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol><p align="justify">
                                                        	admin can view list of Staff Vacancy UO Wise on the basis of WORKING TYPE ,UO DEPARTMENT <br>
                                        			once report generated admin can take out print of it or can save it in PDF formate
                                                        </p>
                        <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/reports/staffvacancyuowise.png"  width="100%">                                
                                                        </ol>
                                                </font>

                                         </div>
                                    </section>


                                    <section id="Position-Summary">
                                        <div class="row-fluid">
                                                <h2>Position-Summary</h2>
                                        </div>

                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                        	<p align="justify">
                                                        		admin can view list of Position-Summary on the basis of WORKING TYPE
                                                        		<br>
                                        			once report generated admin can take out print of it or can save it in PDF formate
                                                        	</p>
                        <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/reports/positionsummary.png"  width="100%">                                
                                                        </ol>
                                                </font>

                                         </div>
                                    </section>

                                    <section id="StaffPositionwithNameUOWise">
                                        <div class="row-fluid">
                                                <h2>Staff Position with Name UO Wise</h2>
                                        </div>

                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                        	<p align="justify">
                                                        		admin can view list of Staff Position with Name UO Wise on the basis of WORKING TYPE ,UO DEPARTMENT <br>
                                        			once report generated admin can take out print of it or can save it in PDF formate
                                                        	</p>
                        <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/reports/staffpositiouowise.png"  width="100%">                                
                                                        </ol>
                                                </font>

                                         </div>
                                    </section>


                                    <section id="StaffPositionwithNamePostWise">
                                        <div class="row-fluid">
                                                <h2>Staff Position with Name Post Wise</h2>
                                        </div>

                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                        	<p align="justify">
                                                        		admin can view list of Staff Position with Name Post Wise on the basis of WORKING TYPE, POST
                                                        		<br>
                                        			once report generated admin can take out print of it or can save it in PDF formate
                                                        	</p>
                        <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/reports/staffpositionpostwise.png"  width="100%">                                
                                                        </ol>
                                                </font>

                                         </div>
                                    </section>


                                    <section id="StaffSeniorityList">
                                        <div class="row-fluid">
                                                <h2>Staff Seniority List</h2>
                                        </div>

                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                        	<p align="justify">
                                                        		admin can view list of staff seniority list on the basis of WORKING TYPE, DESIGNATION, SERVICE CALCULATION DATE ,APPOINTMENT DATE ,AGP
                                                        		<br>
                                        			once report generated admin can take out print of it or can save it in PDF formate
                                                        	</p>
                        <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/reports/staffsenioritylist.png"  width="100%">                                
                                                        </ol>
                                                </font>

                                         </div>
                                    </section>



                                    <section id="ListofHOD">
                                        <div class="row-fluid">
                                                <h2>List Of HOD</h2>
                                        </div>

                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                        	<p align="justify">
                                                        		admin can view list of HOD on the basis of UO
                                                        		<br>
                                        			once report generated admin can take out print of it or can save it in PDF formate
                                                        	</p>
                        <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/reports/listhod.png"  width="100%">                                
                                                        </ol>
                                                </font>

                                         </div>
                                    </section>




                                    <section id="ListofUO">
                                        <div class="row-fluid">
                                                <h2>List Of UO</h2>
                                        </div>

                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                        	<p align="justify">
                                                        		admin can view list of UO
                                                        		<br>
                                        			once report generated admin can take out print of it or can save it in PDF formate
                                                        	</p>
                        <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/reports/listuo.png"  width="100%">                                
                                                        </ol>
                                                </font>

                                         </div>
                                    </section>
                                    


                                    <section id="ViewEarnedLeave">
                                        <div class="row-fluid">
                                                <h2>View Earned Leave</h2>
                                        </div>

                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                        <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/reports/viewearnedleavea.png"  width="100%">
                         <img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/reports/viewearnedleaveb.png"  width="100%">                                                                
                                                        </ol>
                                                </font>

                                         </div>
                                    </section>



<section id="Archives">
	<div class="row-fluid">
		<h2>Archives</h2>
	</div>
	<div class="row-fluid">
		<font size="4">
			<p align="justify">
				adminstrator can view diff data archives here
			</p>
		</font>
		<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/archives.png"  width="100%">
	</div>
</section>

<section id="BackupList">
	<div class="row-fluid">
		<h2>Backups</h2>
	</div>
	<div class="row-fluid">
		<font size="4">
			<p align="justify">
				adminstrator can view backup generated
			</p>
		</font>
		<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/backup.png"  width="100%">
	</div>
</section>



<section id="ViewProfile">
                                        <div class="row-fluid">
                                                <h2>Profile</h2>
                                        </div>
                                        <div class="row-fluid">
                                  <font size="4"><img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/profile/view.png"  width="100%">

                                        		
                                        	</font>
                                        	
                                        </div>
</section>



	
<section id="ChangePassword">
                                        <div class="row-fluid">
                                                <h2>Change Password</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
    Password can be changed from the Change Password link. Each user must enter the old password and new password after then Retype New Password for the user.
                                                                 </p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>helpimages/sishelpscreenshot/profile/change_profile.png"  width="100%">

                                         </div>
                                    </section>
                                    <section id="Profile">
                                    	<div></div>
                                    </section>

                                    
					</div>

		</div>
	</div>
</body>
<div align="center"> <?php $this->load->view('template/footer');?></div>
</html>

