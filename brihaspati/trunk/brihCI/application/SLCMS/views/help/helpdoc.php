<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<!--@name helpdoc.php 
  @author Deepika Chaudhary (chaudharydeepika88@gmail.com)
  @author Neha Khullar (nehukhullar@gmail.com) Modification 
 -->
<html>
<head>
	<title>User Manual</title>
        <?php $this->load->view('template/header'); ?>
        <?php //$this->load->view('template/menu');?>
 	<link rel="stylesheet" type="text/css" href="<?php echo base_url();?>assets/css/helpdoc.css">
</head>
<body>
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->
<table width="100%">
            <?php
            echo "<td align=\"center\" width=\"100%\">";
            echo "<b>User Manual</b>";
            echo "</td>";
             ?>
</table>
	<div class="head">Online Admission System</div>
	<div class="wrapper" width="100%"  style="background-color: #DDDDDD;"> 
	<div class="content">
	<div class="sideleft">
        <div id="cssmenu">
        	<ul>
		<li><a href="#AboutOnlineAdmissionSystem">About Online Admission System</a></li>
	     <li><a href="#Dashboard">Dashboard</a></li>
	     <li class='has-sub'><a href="#Setup">Setup</a>
			<ul>
			<li><a href="#InitialSetting">Initial Setting</a>
				<ul>
				<li><a href="#MySQLRootPassword">My SQL Root Password </a></li>
				<li><a href="#EmailSetting">Email Setting</a></li>
				</ul>
			</li>
            <li><a href="#Role">Role</a></li>
			<li><a href="#Category">Category</a></li>
			<li><a href="#StudyCenter">Study Center</a></li>
			<li><a href="#Authority">Authority</a></li>
			<li><a href="#Department">Department</a></li>
			<li><a href="#SalaryGradeMaster">Salary Grade Master</a></li>
			<li><a href="#Designation">Designation</a></li>
			<li><a href="#ProgramSetting">Program  Setting</a>
				<ul>
					<li><a href="#ProgramCategory">Program Category</a></li>
					<li><a href="#ProgramandSeat">Program and Seat</a></li>
					<li><a href="#SeatReservation">Seat Reservation</a></li>
					<li><a href="#Subject">Subject</a></li>
					<li><a href="#ProgramFees">Program Fees</a></li>
				</ul>

			</li>
			<li><a href="#ExamTypes">Exam Type</a></li>
			<li><a href="#DegreeRule">Degree Rule</a></li>
			<li><a href="#SemesterRule">Semester Rule</a></li>
			<li><a href="#MasterGrade">Master Grade</a></li>
			<li><a href="#SetMasterDates">Set Master Dates</a></li>
			<li><a href="#Scholarship">Scholarship</a></li>
			</ul>

	    <li class='has-sub'><a href="#Map">Map</a>
			<ul>
			<li><a href="#MapUserwithRole">Map User with Role</a></li>
			<li><a href="#MapUserWithAuthority">Map User With Authority</a></li>
			<li><a href="#MapSubjectandSemester">Map Subject and Sememster</a></li>
			<li><a href="#MapProgramwithSubjectandPaper">Map Program with Subject and Paper</a></li>
			<li><a href="#MapSubjectandPaperwithTeacher">Map Subject and Paper with Teacher</a></li>
            <li><a href="#MapSubjectandPaperwithPrerequisite">Map Subject and Paper with Prerequisite</a></li>
           
			</ul>
	    <li class='has-sub'><a href="#Upload">Upload</a>
			<ul>
			<li><a href="#UploadLogo">Upload Logo</a></li>
			<li><a href="#UploadStudentAdmissionMeritList">Upload Student Admission Merit List</a></li>
			<li><a href="#UploadTeacherList">Upload Teacher List</a></li>
            </ul>
	    <li class='has-sub'><a href="#Entrance">Entrance</a>
                <ul>
                    <li><a href="#Reports"> Reports</a>
                        <ul>
                        	<li><a href="#FillteredStudentApplicationList">Filltered Student Application Lis</a></li>
                        	<li><a href="#GraphicalReport">Graphical Report</a></li>
                        	<li><a href="#NumericalReport">Numerical Report</a></li>
                        </ul>
                    </li>
                        <li><a href="#EntranceContactUs">Entrance Contact Us </a></li>
                        <li><a href="#SetEntranceExamFees">Set Entrance Exam Fees</a></li>
                        <li><a href="#SetEntranceExamCenter">Set Entrance Exam Center</a></li>
                        <li><a href="#EntranceAdmissionAnnouncement">Entrance Admission Announcement</a></li>
                        <li><a href="#EntranceFeeReconcile">Entrance Fees Reconcile </a></li>
                        <li><a href="#RollNoGeneration">Roll No Generation</a></li>
                        <li><a href="#GenerateHallTicket">Generates Hall Ticket</a></li>
                        <li><a href="#GeneratesStickers">Generates Stickers</a></li>
                        <li><a href="#GenerateAttendanceSheet">Generates Attendence Sheet</a></li>
                </ul>

        <li class="has-sub"><a href="#Admission">Admission</a>
        	<ul>
        		<li><a href="#Reports">Reports</a>
        			<ul>
        				<li><a href="#AdmissionMeritList">Admission Merit List</a></li>
        				<li><a href="#StudentAdmissionReports">Student Admission Reports</a></li>
        			</ul>
        		</li>
        		<li><a href="#AdmissionFeesReconcile">Admission Fees Reconcile</a></li>
        		<li><a href="#StudentDataVerification">Student Data Verification </a></li>
        		<li><a href="#StudentEnrollmentNumber">Student Enrollment Number</a></li>
        		<li><a href="#StudentDepartmentTransfer">Student Department Transfer</a></li>
        		<li><a href="#AdmissionCancellation">Admission Cancellation</a></li>
        		<li><a href="#StudentScholarshipRequest">Student Scholarship Request</a></li>
        	</ul>
        </li>  
        <li class="has-sub"><a href="#Exam">Exam</a>
        	<ul>
        		<li><a href="#SetupExamCenter">Setup Exam Center</a></li>
        		<li><a href="#ExamSchedule">Exam Schedule</a></li>
        		<li><a href="#AdmitCardGeneration">Admit Card Generation</a></li>
        		<li><a href="#AttendenceSheetGeneration">Attendence Sheet Generation</a></li>
        		<li><a href="#VerificationFormGeneration">Verification Form Generation</a></li>
        	</ul>

        </li>
        <li class="has-sub"><a href="#Result">Result</a>
        	<ul>
        		<li><a href="#MarksSubmission">Marks Submission</a></li>
        		<li><a href="#TabulationChart">TabulationChart</a></li>
        		<li><a href="#ResultScrutiny">Result Srcutiny</a></li>
        		<li><a href="#Result Declared">Result Declared</a></li>
        		<li><a href="#ResultStopped">ResultStopped</a></li>
        		<li><a href="#GEMandPrintGradeCaed">Gem And Print Grade Card</a></li>
        		<li><a href="#IssueGradeCard"> Issue Grade Card </a></li>
        	</ul>
        </li>
        <li class="has-sub"><a href="#Reports">Reports</a>
        	<ul>
        		<li><a href="#StudentList">Student List</a></li>
        		<li><a href="#FacultyList">Faculty List</a></li>
        		<li><a href="#StaffList">Staff List</a></li>
        		<li><a href="#AttendenceReport">Attendence Report</a></li>
        	</ul>

        </li>
        <li><a href="#Announcement">Announcement</a></li>  
 	    <li><a href="#Archivers">Archives</a></li>
 	    <li class="has-sub"><a href="#Downloads">Downloads</a>
 	    	<ul>
 	    		<li><a href="#EntranceApplicantDetailS">Entrance Applicant Detail</a></li>
 	    		<li><a href="#AdmissionApplicantDetail">Admission Applicant Detail</a></li>
 	    	</ul>
 	    </li>
            	  
	    <li class='has-sub'><a href="#">Profile</a>
			<ul>
			<li><a href="#ViewProfile">View Profile</a></li>
			<li><a href="#ChangePassword">Change Password</a></li>
			<li><a href="#Logout">Logout</a></li>
		</ul></li-->




             </ul>       
                </div>
            </div>
                <div class="sideright">
					<section id="AboutOnlineAdmissionSystem">
					<div class="row-fluid">
						<h2>About Online Admission System</h2>
					</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
Online Admission System is to operate the Academic university admission structure related to operation and functionality. The objective is to take initiative give support to the administration and admission seeking candidates by providing a faster, transparent and easy way of keeping records and use them for reference and further proceedings. 
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
									This page consists of University Profile, Program, Seat and Fees Details. The page is continuously updated on the basis of the activities carried out in the system. 
									</p>
								</ol>
							</font>						
						<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/dashboard.png"  width="100%">
						<h2></h2>
						</div>
					</section>
					<section id="ViewProfile">
						<div class="row-fluid">
							<h2>View profile</h2>
						</div>
						<div class="row-fluid">
							<font size="4">
								<p>
									admin can view profile from dashboard
								</p>
							</font>
							<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/viewprofile.png" width="100%">
						</div>
					</section>

						<section id="ChangePassword">
						<div class="row-fluid">
							<h2>Change Password</h2>
						</div>
						<div class="row-fluid">
							<font size="4">
								<p>
									admin can change password from dashboard
								</p>
							</font>
							<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/changepassword.png" width="100%">
						</div>
					</section>




					<section id="Setup">
						<div class="row-fluid">
							<h2>Setup</h2>
						</div>
						<section id="MySQLRootPassword">
						<div class="row-fluid">
							<h2>My SQL root Password</h2>
                        </div>

                      <div class="row-fluid">
                      	<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/setup/initialsetting/mysqlrootpassword.png" width="100%">
                      	
                      </div>
                  </section>
                  <section id="EmailSetting">
                  	<div class="row-fluid">
                  		<h2>Email setting</h2>
                  		<h2>Add email</h2>
                  	</div>
						<div class="row-fluid">
							<font size="4">
									<ol>
									<p align="justify" STYLE="line-height: 150%">	
						By entering all email setting data we can send outgoing email.<br>
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
						<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/setup/initialsetting/addemail.png" width="100%">
						</div>
					</section>


					<section id="ViewEmailSetting">
                                        <div class="row-fluid">
                                                <h2>View Email Setting</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
				Admin can view email setting after adding Email Details. Admin can edit and delete this data.
                                                                 </p>
                                                        </ol>
                                                </font>
					<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/setup/initialsetting/viewemail.png" width="100%">

                                        </div>
                                        </section>



<section id="Role">
                                        
                                                <h2>Role</h2>
                                        <section id="AddRole">
 					<h2>Add Role</h2>
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
Admin can add role by entering Role Name and Role Description.<br>
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
<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/setup/addrole.png" width="100%">
                                                </font>
                                       
                                        </section>
</section>




			<section id="ViewRoleDetail">
                                        <div class="row-fluid">
                                                <h2>View Role Detail</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
				Admin can view role after adding Role Details. Admin can edit and delete this data.
                                                                 </p>
                                                        </ol>
<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/setup/viewrole.png"  width="100%">
                                                </font>
                                        </div>
                                        </section>

				<section id="Category">
                                        <div class="row-fluid">
                                                <h2>Category</h2>
						</div>
                            <div class="row-fluid">                    
				 <h2>Add Category</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
Admin can add category by entering Category Name, Category Code, Category Short Name, Category Description.<br>
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
<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/setup/addcategory.png"  width="100%">
                                        </div>
                                        </section>


			<section id="ViewCategoryDetail">

                                        <div class="row-fluid">
                                                <h2>View Category Detail</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
				Admin can view category after adding Category Details. Admin can edit and delete this data.
                                                                 </p>
                                                        </ol>
<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/setup/viewcategory.png"  width="100%">
                                                </font>
                                        </div>
                                        </section>




                                                                      </section>
			<section id="StudyCenter">
                                        <div class="row-fluid">
                                                <h2>Study Center</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
Admin can add study center by entering your University, Campus Code, Campus Name, Campus Nick Name, Address, Country, State, City, District, Pincode, Phone, Fax, Status, Start Date, Close Date, Website, Incharge, Mobile.<br>
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
<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/setup/addstudycenter.png" width="100%">
                                        </div>
                                        </section>
<section id="ViewStudyCenter">
                                        <div class="row-fluid">
                                                <h2>View Study Center</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
				Admin can view study center after adding Study Center Details. Admin can edit and delete this data.
                                                                 </p>
                                                        </ol>
<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/setup/viewstudycenter.png"  width="100%">
                                                </font>
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
Admin can add Authority by entering Authority Name, Authority Nick Name,  Authority Email.<br>
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
<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/setup/addauthority.png" height="75%" width="100%">
                                        </div>
                                        </section>
<section id="ViewAuthority">
                                        <div class="row-fluid">
                                                <h2>View Authority</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
                                Admin can view authority after adding Authority Details. Admin can edit and delete this data.
                                                                 </p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/setup/viewauthority.png" height="80%" width="100%">
                                        </div>
                                        </section>

                                        <section id="Department">
                                        <div class="row-fluid">
                                                <h2>Department</h2>
                                                 <h2>Add Department</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
Admin can add department by entering your University, Choosing your Campus, School Code, School Name, Department Code, Department Name, Department Nick Name and Department Description.<br>
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
<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/setup/adddepartment.png"  width="100%">
                                        </div>
                                        </section>
<section id="ViewDepartmentDetail">
                                        <div class="row-fluid">
                                                <h2>View Department Detail</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
				Admin can view department after adding Department Details. Admin can edit and delete this data.
                                                                 </p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/setup/viewdepartment.png"  width="100%">
                                        </div>
                                        </section>

                                        <section id="SalaryGradeMaster">
                                        	<div class="row-fluid">
                                        		<h2>Salary Grade Master</h2>
                                        	</div>
                                        	<div class="row-fluid">
                                        		<font size="4">
                                        			<p align="justify">
                                        				admin can view salalry grade master
                                        			</p>
                                        		</font>
                                        		<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/setup/salaeygradedetail.png"  width="100%">

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
Admin can add Designation by entering Designation Name, Designation Code, Designation short, Designation Description.<br>
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
                                                <td><B>Designation Short</B></td>
                                                <td>Enter Designation Short. Ex:-AM, AR</td>
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
<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/setup/adddesignation.png"  width="100%">
                                        </div>
                                        </section>
<section id="ViewDesignation">
                                        <div class="row-fluid">
                                                <h2>View Designation</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
                                Admin can view Designation after adding Designation Details. Admin can edit and delete this data.
                                                                 </p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/setup/viewdesignation.png"  width="100%">
                                        </div>
                                        </section>
			

<section id="ProgramCategory">
                                        <div class="row-fluid">
                                                <h2>Program Category</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
Admin can add program category list by entering Program Category Name, Program Category Code, Program Category Short Name, Program Category Description.<br>
The format of Add Program Category are given below:-
                                                <P ALIGN=JUSTIFY STYLE="margin-bottom: 0in; line-height: 150%">
                                                <table border="2px solid black" style="width:700px">
                                                <tr>
                                                <td><B>Field</B></td>
                                                <td><B>Description</B></td>
                                                </tr>
                                                <tr>
                                                <td><B>Program Category Name</B></td>
                                                <td>Ex:-Under Graduate, Post Graduate, Diploma </td>
                                                </tr>
                                                <tr>
                                                <td><B>Program Category Code</B></td>
                                                <td>Ex:-01,02,03,04 </td>
                                                </tr>
                                                <tr>    
                                                <td><B>Program Category Short Name</B></td>
                                                <td>Ex:-UG, PG </td>
                                                </tr>
                                                <tr>    
                                                <td><B>Program Category Description</B></td>
                                                <td>Ex:-An undergraduate degree is a colloquial term for an academic degree taken by a person who has completed undergraduate courses.</td>
                                                </tr>
                                                </table>
                                                </P>

                                                                 </p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/setup/programsetting/addprogramcategory.png"  width="100%">
                                        </div>
                                        </section>
<section id="ViewProgramCategory">
                                        <div class="row-fluid">
                                                <h2>View Program Category</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
                                Admin can view program category after adding Program Category Details. Admin can edit and delete this data.
                                                                 </p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/setup/programsetting/viewprogramcategory.png" width="100%">
                                        </div>
                                        </section>
			

			\<section id="ProgramandSeat">
                                        <div class="row-fluid">
                                                <h2>Program and Seat</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
Admin can add program and seat by entering Program Category, Program Name, Program Branch, Total Seat, Program Code, Program Short, Program Description, Program Min Time and Program Max Time.<br>
The format of Add Program and fees are given below:-
                                                                 </p>
						<P ALIGN=JUSTIFY STYLE="margin-bottom: 0in; line-height: 150%">
						<table border="2px solid black" style="width:700px">
						<tr>
						<td><B>Field</B></td>
						<td><B>Description</B></td>
						</tr>
						<tr>
						<td><B>Program Category</B></td>
						<td> UG, PG, R, Dip etc </td>
						</tr>
						<tr>
						<td><B>Program Name</B></td>
						<td>Bachelor of Art, Master of Art etc </td>
						</tr>
						<tr>
						<td><B>Program Branch</B></td>
						<td>Physics, Math etc</td>
						</tr>
						<tr>
						<td><B>Total Seat</B></td>
						<td>50</td>
						</tr>
						<tr>
						<td><B>Program Code</B></td>
						<td>B.Tech001</td>
						</tr>
						<tr>
						<td><B>Program Short</B></td>
						<td>BT</td>
						</tr>
						<tr>
						<td><B>Program Description</B></td>
						<td>Bachelor of Technology (commonly abbreviated as B.Tech)</td>
						</tr>
						<tr>
						<td><B>Program Min Time</B></td>
						<td>4</td>
						</tr>
						<tr>
						<td><B>Program Max Time</B></td>
						<td>5</td>
						</tr>
						</table>
						</P>          
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/setup/programsetting/addprogramandseat.png" width="100%">
                                        </div>
                                        </section>

				<section id="ViewProgramandseatDetail">
                                        <div class="row-fluid">
                                                <h2>View Program and Seat Detail</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
				Admin can view program and seat after adding Program and Seat Details. Admin can edit and delete this data.
                                                                 </p>
                                                        </ol>
<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/setup/programsetting/viewprogramandseat.png" width="100%">
                                                </font>
                                        </div>
                                        </section>
                                        <section id="SeatReservation">
                                        <div class="row-fluid">
                                                <h2>Seat Reservation</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
Admin can add seat for student by entering select University, select Category, Percentage, Number of Seat.<br>
The format of Add seat are given below:-
<P ALIGN=JUSTIFY STYLE="margin-bottom: 0in; line-height: 150%">
						<table border="2" style="width:700px">
						<tr>
						<td><B>Field</B></td>
						<td><B>Description</B></td>
						</tr>
						<tr>
						<td><B>University</B></td>
						<td>Select University Ex:-Indira Gandhi National Tribal University</td>
						</tr>
						<tr>
						<td><B>Category</B></td>
						<td>Select Category Ex:-All, Gen, SC, ST etc.</td>
						</tr>
						<tr>
						<td><B>Percentage</B></td>
						<td>Enter Numeric Value</td>
						</tr>
						<tr>
						<td><B>Number of Seat</B></td>
						<td>Automatic Calculates</td>
						</tr>

								</table>
									</P>
                                                                 </p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/setup/programsetting/addseatreservation.png"  width="100%">
                                        </div>
                                        </section>
                                        <section id="ViewSeatSetting">
                                        <div class="row-fluid">
                                                <h2>View Seat Setting</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
				Admin can view seat after adding Seat Details. Admin can edit and delete this data.
                                                                 </p>
                                                        </ol>
<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/setup/programsetting/viewseatresevation.png"  width="100%">
                                                </font>
                                        </div>
          


			<section id="Subject">
                                        <div class="row-fluid">
                                                <h2>Subject</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
Admin can add subject by entering Subject Name, Subject Code, Subject Short, Subject Description.
                                                                 </p>
						<P ALIGN=JUSTIFY STYLE="margin-bottom: 0in; line-height: 150%">
						<table border="3px solid black" style="width:700px">
						<tr>
						<th><B>Field</B></th>
						<th><B>Description</B></th>
						</tr>
						<tr>
						<td><B>Subject Name</B></td>
						<td>English</td>
						</tr>
						<tr>
						<td><B>Subject Code</B></td>
						<td>El0023</td>
						</tr>
						<tr>
						<td><B>Subject Short</B></td>
						<td>Eng</td>
						</tr>
						<tr>
						<td><B>Subject Description</B></td>
						<td>The English Subject Centre supports the teaching and learning of English literature</td>
						</tr>
</table>
</P>

                                                        </ol>
<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/setup/programsetting/addsubject.png"  width="100%">
                                                </font>
                                        </div>
                                        </section>

<section id="ViewSubjectDetail">
                                        <div class="row-fluid">
                                                <h2>View Subject Detail</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
				Admin can view subject after adding Subject DetailS. Admin can edit and delete this data.
                                                                 </p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/setup/programsetting/viewsubject.png"  width="100%">
                                        </div>
                                        </section>

			<section id="ProgramFees">
                                        <div class="row-fluid">
                                                <h2>Program Fees</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
Admin can add program fees with head list by entering Program Name, Academic Year,Semester, Category, Gender, Head, Amount and Description. <br>                                
The format of add program fees with head list are given below:-
						<P ALIGN=JUSTIFY STYLE="margin-bottom: 0in; line-height: 150%">
						<table border="2px solid black" style="width:700px">
						<tr>
						<td><B>Field</B></td>
						<td><B>Description</B></td>
						</tr>
						<tr>
						<td><B>Program Name</B></td>
						<td>Select Program Ex:- Bachelor of Art, Master of Art etc  </td>
						</tr>
						<tr>
						<td><B>Academic Year</B></td>
						<td>Select Acedemic Year Ex:-2016-2017, 2017-2018 </td>
						</tr>
						<tr>
						<td><B>Semester</B></td>
						<td>Select Semester Ex:-2, 3, 4 </td>
						</tr>
						<tr>
						<td><B>Category</B></td>
						<td>Select Category Ex:OBC, SC, ST, General-</td>
						</tr>
						<tr>
						<td><B>Gender</B></td>
						<td>Select Gender Ex:-Male, Female</td>
						</tr>
						<tr>
						<td><B>Head</B></td>
						<td>Ex:-Tuition Fees</td>
						</tr>
						<tr>
						<td><B>Amount</B></td>
						<td>Enter Amount Ex:-2000,4000</td>
						</tr>
						<tr>
						<td><B>Description</B></td>
						<td>Enter Description</td>
						</tr>
						</table>
						</P>     
</p>     
                                                        </ol>
                                                </font>

                                        </div>
<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/setup/programsetting/addprogramfees.png"  width="100%">
                                       </section>
<section id="ViewProgramFeeswithHead">
                                        <div class="row-fluid">
                                                <h2>View Program Fees with head</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
Admin can view campus program seat list after adding Program Fees with Head Details. We can edit and delete this data.
                                                                 </p>
                                                        </ol>
                                         <img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/setup/programsetting/viewprogramfees.png"  width="100%">      
					 </font>
                                         </div>
                                    </section>


                                    <section id="ExamTypes">
                                    	<div class="row-fluid">
                                    		<h2>Exam Types</h2>
                                    	</div>
                                    	<div class="row-fluid">
                                    		<font size="4">
                                    			<p>various type of exam can be setup

                                    			<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/setup/examtype.png"  width="100%"> 	
                                    			</p>
                                    		</font>
                                    		
                                    	</div>
                                    </section>


<section id="DegreeRule">
                                        <div class="row-fluid">
                                                <h2>Degree Rules</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
Admin can add Degree Rule by entering Programme, Choose Branch, Degree minimum Credit, Minimum Subject Credit, Minimum Thesis Credit, Minimum Subjects, Minimum Semesters, Minimum CPI, Maximum Credit, Maximum Semesters.<br>
The format of add Degree Rules with head list are given below:-
						<P ALIGN=JUSTIFY STYLE="margin-bottom: 0in; line-height: 150%">
						<table border="2px solid black" style="width:700px">
						<tr>
						<td><B>Field</B></td>
						<td><B>Description</B></td>
						</tr>
						<tr>
						<td><B>Choose Program</B></td>
						<td>Select Program Ex:- Batchlor of Art, Master of Art etc  </td>
						</tr>
						<tr>
						<td><B>Choose Branch</B></td>
						<td>Choose Branch from drop down.</td>
						</tr>
						<tr>
						<td><B>Degree Minimum Credit</B></td>
						<td>Enter Degree minimum Credit</td>
						</tr>
						<tr>
						<td><B>Minimum Subject Credit</B></td>
						<td>Enter Minimum Subject Credit</td>
						</tr>
						<tr>
						<td><B>Minimum Thesis Credit</B></td>
						<td>Enter Minimum Thesis Credit</td>
						</tr>
						<tr>
						<td><B>Minimum Subjects</B></td>
						<td>Enter Minimum Subjects</td>
						</tr>
						<tr>
						<td><B>Minimum Semesters</B></td>
						<td>Enter Minimum Semesters</td>
						</tr>
						<tr>
						<td><B>Minimum CPI</B></td>
						<td>Enter Minimum CPI</td>
						</tr>
						<tr>
						<td><B>Maximum Credit</B></td>
						<td>Enter Maximum Credit</td>
						</tr>
						<tr>
						<td><B>Maximum Semesters</B></td>
						<td>Enter Maximum Semesters</td>
						</tr>
						</table>

						</P>     
						</p>     
                                                        </ol>
                                                </font>

                                        </div>
<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/setup/adddegreerule.png" height="110%" width="100%">
                                       </section>
<section id="ViewDegreeRule">
                                        <div class="row-fluid">
                                                <h2>View Degree Rule</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
Admin can view degree rules after adding Degree Rules Details. We can edit and delete this data.
                                                                 </p>
                                                        </ol>
					 </font>
<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/setup/viewdegreerule.png" height="50%" width="100%">      
                                         </div>
                                    </section>

<section id="SemesterRule">
                                        <div class="row-fluid">
                                                <h2>Semester Rule</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
Admin can add semester rules by entering Program Name, Branch Name, Semester, Semester Minimum Credit, Semester Maximum Credit, Semester CPI.<br>
The format of add program fees with head list are given below:-
                                                <P ALIGN=JUSTIFY STYLE="margin-bottom: 0in; line-height: 150%">
                                                <table border="2px solid black" style="width:700px">
                                                <tr>
                                                <td><B>Field</B></td>
                                                <td><B>Description</B></td>
                                                </tr>
                                                <tr>
                                                <td><B>Program Name</B></td>
                                                <td>Select Program Ex:- Batchlor of Art, Master of Art etc  </td>
                                                </tr>
                                                 <tr>
                                                <td><B>Branch Name </B></td>
                                                <td>Select Branch Ex:- Math etc  </td>
                                                </tr>
					        <tr>
                                                <td><B>Semester </B></td>
                                                <td>Select Semester Ex:- 2, 3, 4 etc  </td>
                                                </tr>
                                                 <tr>
                                                <td><B>Semester Minimum Credit </B></td>
                                                <td>Enter Minimum Credit Ex:-Any Positive Number etc  </td>
                                                </tr>
                                                <tr>
                                                <td><B>Semester Maximum Credit </B></td>
                                                <td>Enter Maximum Credit Ex:- Any Positive Number etc  </td>
                                                </tr>
						<tr>
                                                <td><B>Semester CPI</B></td>
                                                <td>Enter Semester CPI Ex:- Any Desimal Value  etc  </td>
                                                </tr>
					        </table>
                                                </P>
					</p>
                                                        </ol>
                                         <img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/setup/addsemesterrule.png" width="100%">       </font>

                                         </div>
                                    </section>

<section id="ViewSemesterRule">
                                        <div class="row-fluid">
                                                <h2>View Semester Rule </h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
Admin can view semester rule after adding Semester Rule Details. Admin can edit and delete this data.
							</p>
                                                        </ol>
                                         <img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/setup/viewsemesterrule.png" height="45%" width="100%">       </font>

                                         </div>
                                    </section>

<section id="MasterGrade">
                                        <div class="row-fluid">
                                                <h2>Grade Master</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
Admin can add Grade Master by entering Grade Name, Grade Point, Grade Short, Grade Description.<br>
The format of add program fees with head list are given below:-
						<P ALIGN=JUSTIFY STYLE="margin-bottom: 0in; line-height: 150%">
                                                <table border="2px solid black" style="width:700px">
                                                <tr>
                                                <td><B>Field</B></td>
                                                <td><B>Description</B></td>
                                                </tr>
						<tr>
						<td><B>Grade Name</B></td>
						<td>Enter Grade Name Ex:-A, B, C, D, E etc </td>
						<tr>
                                                <td><B>Grade Point</B></td>
                                                <td>Enter Grade Point Ex:-10, 8, 6, 4, 2 etc </td>
                                                </tr>
						<tr>
                                                <td><B>Grade Short</B></td>
                                                <td>Enter Grade Short Ex:-Good, Fair, Poor etc. </td>
                                                </tr>
						<tr>
                                                <td><B>Grade Description</B></td>
                                                <td>Enter Grade Description</td>
                                                </tr>
                                                </tr>
						 </table>
                                                </P>
						</p>
                                                        </ol>
                                                       </font>
                                         <img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/setup/addgrade.png" width="100%">       
                                         </div>
                                    </section>
<section id="ViewGrade">
                                        <div class="row-fluid">
                                                <h2>View Grade </h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
Admin can view grade after adding Grade Details. Admin can edit and delete this data.
                                                        </p>
                                                        </ol>
                                         <img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/setup/viewgrade.png" height="90%" width="100%">       
					</font>

                                         </div>
                                    </section>
                                    <section id="SetMasterDates">
                                    	<div class="row-fluid">
                                    		<h2>Set Master Dates</h2>
                                    	</div>
                                    	<div class="row-fluid">
                                    		<font size="4">
                                    			 <img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/setup/addsetmasterdates.png" width="100%"> 
                                    		</font>
                                    		<h2>View Master Dates </h2>
                                    		<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/setup/viewsetmasterdates.png"  width="100%">
                                    	</div>
                                    	
                                    </section>

                                    <section id="Scholarship">
                                    	<div class="row-fluid">
                                         <h2>Scholarship</h2>
                                      	</div>
                                      	<div class="row-fluid">
                                      		<font size="4">
                                      			<p align="justify">
                                      				Admin can add various type of scholarship
                                      			</p>
                                      		</font>
                                      		<h2>Add Scholarship</h2>
                                      		<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/setup/addscholarship.png"  width="100%">
                                      		<h2>View Scholarship</h2>
                                      		<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/setup/viewscholarship.png"  width="100%">
                                      	</div>
                                    </section>







				<section id="MapUserwithRole">
                                        <div class="row-fluid">
                                                <h2>Map User with Role</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
From here admin can Map User with Role by entering your Campus, Choose your Department, Select your Role, Select User Type, Select Username.<br>
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
<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/map/addmapuserwithrole.png"  width="100%"> 
                                         </div>
                                    </section>

<section id="ViewMapwithUserRoleList">
                                        <div class="row-fluid">
                                                <h2>View Map with User Role List</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
Admin can view map with user role list after adding Map User Role Details. Admin can edit and delete this data.
                                                        </p>
                                                        </ol>
                                         <img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/map/mapuserwithrole.png"  width="100%">       
					</font>
                                         </div>
                                    </section>


			<section id="MapUserWithAuthority">
                                        <div class="row-fluid">
                                                <h2>Map User With Authority</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
.
                                                                 </p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/map/addmapauthority.png" height="100%" width="100%">
<h2> View Map User With Authority</h2>
<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/map/viewmapauthority.png" height="100%" width="100%">
                                         </div>
                                    </section>


<
<section id="MapProgramwithSubjectandPaper">
                                        <div class="row-fluid">
                                                <h2>Map Program with Subject and Paper</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
Admin can Add subject paper list by entering Degree, Academic Year, Subject Name, Paper Category, Paper No, Paper Name, Paper Code, Paper Short Name, Paper Description.<br>
The format of add Program with Subject and Paperlist are given below:-
                                                <P ALIGN=JUSTIFY STYLE="margin-bottom: 0in; line-height: 150%">
                                                <table border="2px solid black" style="width:700px">
                                                <tr>
                                                <td><B>Field</B></td>
                                                <td><B>Description</B></td>
                                                </tr>
                                                <tr>
                                                <td><B>Degree</B></td>
                                                <td>Select Degree Ex:- Batchlor of Art, Master of Art etc  </td>
                                                </tr>
                                                <tr>
                                                <td><B>Academic Year</B></td>
                                                <td>Select Acedemic Year Ex:-2016-2017, 2017-2018 </td>
                                                </tr>
                                                <tr>
                                                <td><B>Subject Name</B></td>
                                                <td>Select Subject Name Ex:-English, Hindi </td>
                                                </tr>
                                                <tr>
                                                <td><B>Paper Category</B></td>
                                                <td>Select Paper Category Ex:Under Graduate, Post Graduate, Research, Diploma Course</td>
                                                </tr>
                                                <tr>
                                                <td><B>Paper No</B></td>
                                                <td>Enter Paper No. Ex:-1, 2</td>
                                                </tr>
                                                <tr>
                                                <td><B>Paper Name</B></td>
                                                <td>Enter Paper Name Ex:-Physics</td>
                                                </tr>
                                                <tr>
                                                <td><B>Paper Code</B></td>
                                                <td>Enter Paper Code Ex:-phy001</td>
                                                </tr>
						<tr>
                                                <td><B>Paper Short Name</B></td>
                                                <td>Enter Paper Short Name Ex:-phy</td>
                                                </tr>
                                                <tr>
                                                <td><B>Description</B></td>
                                                <td>Enter description Ex:-The branch of science concerned with the nature and properties of matter and energy.</td>
                                                </tr>
                                                </table>
                                                </P>

                                                                 </p>
                                                        </ol>
<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/map/addsubject.png"  width="100%">       </font>
                                                </font>
                                         </div>
                                    </section>
<section id="ViewSubjectPaperList">
                                        <div class="row-fluid">
                                                <h2>View Subject Paper List</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
Admin can view subject paper list after adding Subject Paper Details. We can edit and delete this data.
                                                                 </p>
                                                        </ol>
                                         <img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/map/viewsubject.png"  width="100%">      
					 </font>
                                         </div>
                                    </section>



                                   
<section id="MapSubjectandSemester">
                                        <div class="row-fluid">
                                                <h2>View Map Subject and Semester</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
Admin can view Map Subject and Semester after adding Subject and Semester Details. We can edit and delete this data.
                                                                 </p>
                                                        </ol>
                                         <img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/map/subjectandsemester.png" width="100%">      
					 </font>
                                         </div>
                                    </section>


			<section id="MapSubjectandPaperwithTeacher">
                                        <div class="row-fluid">
                                                <h2>Map Subject and Paper with Teacher</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
Admin can Map Subject and Paper With Teacher by entering Campus Name, Department Name, Academic Year, Program Name, Branch Name, Semester, Subject Name, Paper Name, Teacher Name.<br>
The format of add subject paper with teacher list are given below:-
                                                <P ALIGN=JUSTIFY STYLE="margin-bottom: 0in; line-height: 150%">
                                                <table border="2px solid black" style="width:700px">
                                                <tr>
                                                <td><B>Field</B></td>
                                                <td><B>Description</B></td>
                                                </tr>
                                                <tr>
                                                <td><B>Campus Name</B></td>
                                                <td>Select Campus Name from drop down.</td>
                                                </tr>
                                                <tr>
 						<td><B>Department Name</B></td>
                                                <td>Select Department Name from drop down.</td>
                                                </tr>
                                                <tr>
						<td><B>Academic Year</B></td>
                                                <td>Select Academic Year from drop down.</td>
                                                </tr>
                                                <tr>
						<td><B>Program Name</B></td>
                                                <td>Select Program Name from drop down.</td>
                                                </tr>
                                                <tr>
						<td><B>Branch Name</B></td>
                                                <td>Select Branch Name from drop down.</td>
                                                </tr>
                                                <tr>
						<td><B>Semester</B></td>
                                                <td>Select Semester from drop down.</td>
                                                </tr>
                                                <tr>
						<td><B>Subject Name</B></td>
                                                <td>Select Subject Name from drop down.</td>
                                                </tr>
                                                <tr>
						<td><B>Paper Name</B></td>
                                                <td>Select Paper Name from drop down.</td>
                                                </tr>
                                                <tr>
						<td><B>Teacher Name</B></td>
                                                <td>Select Teacher Name from drop down.</td>
                                                </tr>

						</P>
					</table>

                                                                 </p>
                                                        </ol>
                                                </font>
 <img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/map/addsubjectwithteacher.png" width="100%">  
                                         </div>
                                    </section>

<section id="ViewMapSubjectandPaperwithTeacher">
                                        <div class="row-fluid">
                                                <h2>View Map Subject and Paper with Teacher</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
Admin can view Subject and Paper with Teacher list after adding Subject and Paper with Teacher Details. We can edit and delete this data.
                                                                 </p>
                                                        </ol>
                                         <img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/map/viewsubjectwithteacher.png" width="100%">      
					 </font>
                                         </div>
                                    </section>

<section id="MapSubjectandPaperwithPrerequisite">
                                        <div class="row-fluid">
                                                <h2>Map Subject and Paper with Prerequisite</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
The current subject depends on this subject.  This module set the subject depending<br>
The format of add Subject and Paper with Prerequisite list are given below:-
                                                <P ALIGN=JUSTIFY STYLE="margin-bottom: 0in; line-height: 150%">
                                                <table border="2px solid black" style="width:700px">
                                                <tr>
                                                <td><B>Field</B></td>
                                                <td><B>Description</B></td>
                                                </tr>
                                                <tr>
                                                <td><B>Program Name</B></td>
                                                <td>Select Program Name from drop down.</td>
                                                </tr>
                                                <tr>
 						<td><B>Branch Name</B></td>
                                                <td>Select Branch Name from drop down.</td>
                                                </tr>
                                                <tr>
						<td><B>Subject Name</B></td>
                                                <td>Select Subject Name from drop down.</td>
                                                </tr>
                                                <tr>
						<td><B>Prerequisite Subject Name</B></td>
                                                <td>Select Prerequisite Subject Name from drop down.</td>
                                                </tr>
 						<tr>
						<td><B>Subject Paper Name</B></td>
                                                <td>Select Subject Paper Name from drop down.</td>
                                                </tr>
						<tr>
						<td><B>Prerequisite Subject Paper Name</B></td>
                                                <td>Select Prerequisite Subject Paper Name from drop down.</td>
                                                </tr>
						</P>
					</table>

                                                                 </p>
                                                        </ol>
                                                </font>
 <img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/map/addsubjectpre.png" width="100%">  
                                         </div>
                                    </section>

<section id="ViewMapSubjectandPaperwithPrerequisite">
                                        <div class="row-fluid">
                                                <h2>View Map Subject and Paper with Prerequisite</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
Admin can view Subject and Paper with Prerequisite after adding Subject and Paper with Prerequisite Details. We can edit and delete this data.
                                                                 </p>
                                                        </ol>
                                         <img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/map/viewsubjectpre.png"  width="100%">      
					 </font>
                                         </div>
                                    </section>






			<section id="Upload">
                                        <div class="row-fluid">
                                                <h2>Upload</h2>
                                        </div>
                         </section>
			<section id="UploadLogo">
                                        <div class="row-fluid">
                                                <h2>Upload Logo</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
				Administrators can upload their logo from here. Image must be a png, jpg format and size is 500 K.B.
                                                                 </p>
                                                      </ol>
				<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/upload/uploadslogo.png"  width="100%">
                                                </font>
                                         </div>
                                    </section>
			


		<section id="UploadStudentAdmissionMeritList">
                                        <div class="row-fluid">
                                                <h2>Upload Student List</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
                                                                 </p>
                                                        </ol>
                                                </font>
                                                <img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/upload/uploadstudentmerit.png"  width="100%">
                                         </div>
                                    </section>


                                    <section id="UploadTeacherList">
                                        <div class="row-fluid">
                                                <h2>Upload Teacher List</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
 Administrators can upload multiple teacher at a time. The file should have ".csv" extension & proper format. The files contain name, email, departmentid, roleid, campusid and  mobile no all separated by  comma.<br>
<b>Example</b><br>
<b>name,email,departmentid,roleid,campasid,mobileno</b><br>
deepika,deepika@gmail.com,01,02,01,9415938783<br>
<b>Note:-</b></br>
All information are mandatory except mobile number.			
                                                                 </p>
                                                      </ol>
				<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/upload/uploadteacherlist.png" width="100%">
                                                </font>
                                         </div>
                                    </section>


                                    <section id="FillteredStudentApplicationList">
                                    	<div class="row-fluid">
                                    		<h2>Entrance </h2><br>
                                    		<h2>Reports</h2>

                                    		<h2>Filltered Student Applcation List</h2>
                                    	</div>
                                    	<div class="row-fluid">
                                    		<font size="4">
                                    			<p align="justify">
                                    				Admin CAn view application list on the basis of PROGRAM CATEGORY,  EXAM CENTER, RELIGION ,BRANCH
                                    			</p>
                                    		</font>
                                    		<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/entrance/reports/filterstudentapplicationlist.png" width="100%">
                                    	</div>
                                    </section>

                                    <section id="GraphicalReport">
                                    	<div class="row-fluid">
                                    		<h2>Graphical reports</h2>
                                    	</div>
                                    	<div class="row-fluid">
                                    		<font size="4">
                                    			<p align="justify">
                                    				here admin can view graphical stats of data 
                                    			</p>
                                    		</font>
                                    		<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/entrance/reports/graphical.png" width="100%">
                                    	</div>
                                    	
                                    </section>

                                    <section id="NumericalReport">
                                    	<div class="row-fluid">
                                    		<h2> Numerical Reports</h2>
                                    	</div>
                                    	<div class="row-fluid">
                                    		<font size="4">
                                    			<p align="justify">
                                    				here admin can view numerical data of students stats 
                                    			</p>
                                    		</font>
                                    		<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/entrance/reports/numerical.png" width="100%">
                                    	</div>
                                    </section>


                                    <section id="SetEntranceExamFees">
                                    	<div class="row-fluid">
                                    		<h2>Set Entrance Exam Fees</h2>
                                        </div>
                                        <div class="row-fluid">
                                        	<font size="4">
                                        		<p align="justify">
                                        			admin can entrance exam fees 
                                        		</p>
                                        	</font>
                                        	<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/entrance/addentranceexamfee.png" width="100%">
                                        	<h2>View Entrance Exam Fee</h2>
                                        	<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/entrance/viewentranceexamfee.png" width="100%">
                                        </div>
                                    </section>


                                    <section id="SetEntranceExamCenter">
                                    	<div class="row-fluid">
                                    		<h2>Set Entrance Exam Center</h2>
                                    	</div>
                                    	<div class="row-fluid">
                                    		<font size="4">
                                    			<p align="justify">
                                    				admin can set exam center for entrance exam , and can view 
                                    			</p>
                                    		</font>
                                    		<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/entrance/addexamcenter.png" width="100%">
                                    		<h2>View Entrance Exam Center</h2>
                                    		<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/entrance/viewexamcenter.png" width="100%">

                                    	</div>
                                    </section>

                                    <section id="EntranceAdmissionAnnouncement">
                                    	<div class="row-fluid">
                                    		<h2>Entrance Admission Announcement </h2>
                                    	</div>
                                    	<div class="row-fluid">
                                    		<font size="4">
                                    			<p align="justify">
                                    				admin can set annnoucement for entrance exam
                                    			</p>
                                    		</font>
                                    		<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/entrance/addadmissionannouncement.png" width="100%">
                                    		<h2>View Entrance Admission Announcement</h2>
                                    		<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/entrance/admissionannouncement.png" width="100%">
                                    		
                                    	</div>
                                    </section>


                                    <section id="EntranceFeeReconcile">
                                    	<div class="row-fluid">
                                    		<h2>Entrance Fee Reconcile</h2>
                                    	</div>
                                    	<div class="row-fluid">
                                    		<font size="4">
                                    			<p align="justify">
                                    				Different list of fee reconcile can be view here
                                    			</p>
                                    		</font>
                                    		<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/entrance/feereconcile.png" width="100%">
                                    	</div>
                                    </section>

                                    <section id="RollNoGeneration">
                                    	<div class="row-fluid">
                                    		<h2>Roll No Generation</h2>
                                    	</div>
                                    	<div class="row-fluid">
                                    		<font size="4">
                                    			<p align="justify">
                                    				Select PROGRAM AND EXAM CENTER and generates roll no 
                                    			</p>
                                    		</font>
                                    		<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/entrance/rollnogeneration.png" width="100%">
                                    	</div>
                                    </section>

                                    <section id="GenerateHallTicket">
                                    	<div class="row-fluid">
                                    		<h2>Generate Hall ticket</h2>
                                    	</div>
                                    	<div class="row-fluid">
                                    		<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/entrance/generatehallticket.png" width="100%">
                                    	</div>
                                    </section>


                                       <section id="GeneratesStickers">
                                    	<div class="row-fluid">
                                    		<h2>Generate Stickers </h2>
                                    	</div>
                                    	<div class="row-fluid">
                                    		<font size="4">
                                    			<p align="justify">
                                    				select exam center
                                    			</p>
                                    		</font>
                                    		<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/entrance/stickers.png" width="100%">
                                    	</div>
                                    </section>


                                       <section id="GenerateAttendanceSheet">
                                    	<div class="row-fluid">
                                    		<h2>Generate Attendence Sheet</h2>
                                    	</div>
                                    	<div class="row-fluid">
                                    		<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/entrance/attendencesheet.png" width="100%">
                                    	</div>
                                    </section>



                                    <section id="AdmissionMeritList">
                                    	<div class="row-fluid">
                                    		<h2>Admission</h2><br>
                                    		<h2>Reports</h2><br>
                                    		<h2> Admission Merits List</h2>
                                    	</div>
                                    	<div class="row-fluid">
                                    		
                                    			<font size="4">
                                    				<p align="justify">
                                    					Admin Can View Merits List 
                                    				</p>
                                    			</font>
                                    			<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/admission/reports/admissionmeritlist.png" width="100%">
                                    		
                                    	</div>
                                    </section>


                                      <section id="StudentAdmissionReports">
                                    	<div class="row-fluid">
                                    		
                                    		<h2> Student Admission Repots</h2>
                                    	</div>
                                    	<div class="row-fluid">
                                    		
                                    			<font size="4">
                                    				<p align="justify">
                                    					Admin Can View Admission List
                                    				</p>
                                    			</font>
                                    			<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/admission/reports/studentadmissionreport.png" width="100%">
                                    		
                                    	</div>
                                    </section>


                                      <section id="AdmissionFeesReconcile">
                                    	<div class="row-fluid">
                                    		
                                    		<h2>  Admission fees Reconcile </h2>
                                    	</div>
                                    	<div class="row-fluid">
                                    		
                                    			<font size="4">
                                    				<p align="justify">
                                    					Admin Can View different  fees reconcile list
                                    				</p>
                                    			</font>
                                    			<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/admission/feereconcile.png" width="100%">
                                    		
                                    	</div>
                                    </section>




                                     <section id="StudentDataVerification">
                                    	<div class="row-fluid">
                                    		
                                    		<h2> Student Data Verification</h2>
                                    	</div>
                                    	<div class="row-fluid">
                                    		
                                    			<font size="4">
                                    				<p align="justify">
                                    					Select Start Date And End date and Department and You can View Verified And Non Verfied Student Data 
                                    				</p>
                                    			</font>
                                    			<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/admission/studentdataverification.png" width="100%">
                                    		<
                                    	</div>
                                    </section>

                                     <section id="StudentEnrollmentNumber">
                                    	<div class="row-fluid">
                                    		
                                    		<h2> Student Enrollment Number </h2>
                                    	</div>
                                    	<div class="row-fluid">
                                    		
                                    			<font size="4">
                                    				<p align="justify">
                                    					Select Start Date And End date and Department and You can Generates all Enrollment number 
                                    				</p>
                                    			</font>
                                    			<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/admission/enrollmentnumber.png" width="100%">
                                    		
                                    	</div>
                                    </section>

                                    <section id="StudentDepartmentTransfer">
                                    	<div class="row-fluid">
                                    		
                                    		<h2> Student Department Transfer </h2>
                                    	</div>
                                    	<div class="row-fluid">
                                    		
                                    			<font size="4">
                                    				<p align="justify">
                                    				 
                                    				</p>
                                    			</font>
                                    			<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/admission/dpartmenttransfer.png" width="100%">
                                    		
                                    	</div>
                                    </section>


                                    <section id="AdmissionCancellation">
                                    	<div class="row-fluid">
                                    		
                                    		<h2> Admission cancelation </h2>
                                    	</div>
                                    	<div class="row-fluid">
                                    		
                                    			<font size="4">
                                    				<p align="justify">
                                    					 
                                    				</p>
                                    			</font>
                                    			<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/admission/admisoncancel.png" width="100%">
                                    		
                                    	</div>
                                    </section>

                                    <section id="StudentScholarshipRequest">
                                    	<div class="row-fluid">
                                    		
                                    		<h2> Scholarship Request </h2>
                                    	</div>
                                    	<div class="row-fluid">
                                    		
                                    			<font size="4">
                                    				<p align="justify">
                                    					 Here admin Can See approved and rejected scholarship 
                                    				</p>
                                    			</font>
                                    			<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/admission/scholarshiprequest.png" width="100%">
                                    		</div>
                                    	</section>
                                    	


                                   <section id="SetupExamCenter">
                                    	<div class="row-fluid">
                                    		
                                    		<h2> Exam  </h2><br>
                                    		<h2> Setup Exam Center</h2>
                                    	</div>
                                    	<div class="row-fluid">
                                    		
                                    			<font size="4">
                                    				<p align="justify">
                                    					 Admin Can Setup  Exam Cente here By filling Daetail properly  
                                    				</p>
                                    			</font>
                                    			<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/exam/addexamcenter.png" width="100%">
                                    			<h2>View Exam Center</h2>
                                    			<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/exam/examcenter.png" width="100%">

                                    		</div>
                                    	</section>

                                    	<section id="ExamSchedule">
                                    	<div class="row-fluid">
                                    		
                                    		<h2> Exam Schedule </h2>
                                    	</div>
                                    	<div class="row-fluid">
                                    		
                                    			<font size="4">
                                    				<p align="justify">
                                    					 Exam Schedule can be entered here by submitting appropiate detail of INISTITUTE NAME , PROGRAM DEATIL ,SEMESTER etc.. 
                                    				</p>
                                    			</font>
                                    			<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/exam/examschedule.png" width="100%">
                                    			<h2>View Exam Schedule </h2>
                                    			<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/exam/viewexamschedule.png" width="100%">
                                    		</div>
                                    	</section>


                                    	 <section id="AdmitCardGeneration">
                                    	<div class="row-fluid">
                                    		
                                    		<h2> Admit card Generation </h2>
                                    	</div>
                                    	<div class="row-fluid">
                                    		
                                    			<font size="4">
                                    				<p align="justify">
                                    					 Here admin Can See generated admit cards
                                    				</p>
                                    			</font>
                                    			<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/exam/generateadmitcard.png" width="100%">
                                    		</div>
                                    	</section>

                                    	 <section id="AttendenceSheetGeneration">
                                    	<div class="row-fluid">
                                    		
                                    		<h2> Attendence Sheet Generation </h2>
                                    	</div>
                                    	<div class="row-fluid">
                                    		
                                    			<font size="4">
                                    				<p align="justify">
                                    					 Here admin Can See generated Attendence  sheet
                                    				</p>
                                    			</font>
                                    			<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/exam/attendencesheet.png" width="100%">
                                    		</div>
                                    	</section>

                                    	<section id="VerificationFormGeneration">
                                    	<div class="row-fluid">
                                    		
                                    		<h2> Verification Form Generation </h2>
                                    	</div>
                                    	<div class="row-fluid">
                                    		
                                    			<font size="4">
                                    				<p align="justify">
                                    					 Here admin Can See and Verify Exam Forms
                                    				</p>
                                    			</font>
                                    			<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/exam/formverification.png" width="100%">
                                    		</div>
                                    	</section>

                                    	<section id="MarksSubmission">
                                    	<div class="row-fluid">
                                    		
                                    		<h2> Marks Submission </h2>
                                    	</div>
                                    	<div class="row-fluid">
                                    		
                                    			<font size="4">
                                    				<p align="justify">
                                    					
                                    				</p>
                                    			</font>
                                    			<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/result/addmarks.png" width="100%">
                                    			<h2>View Marks Submission</h2>
                                    			<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/result/viewmarks.png" width="100%">
                                    		</div>
                                    			                                    		
                                    	</section>


                                    	<section id="TabulationChart">
                                    	<div class="row-fluid">
                                    		
                                    		<h2> tabulation Charts  </h2>
                                    	</div>
                                    	<div class="row-fluid">
                                    		
                                    			<font size="4">
                                    				<p align="justify">
                                    					
                                    				</p>
                                    			</font>
                                    			<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/result/tabulation.png" width="100%">
                                    			
                                    		</div>
                                    			                                    		
                                    	</section>
                                    	
                                    	<section id="StudentList">
                                    	<div class="row-fluid">
                                    		
                                    		<h2>Students List  </h2>
                                    	</div>
                                    	<div class="row-fluid">
                                    		
                                    			<font size="4">
                                    				<p align="justify">
                                    					Admin Can view student List Reports From Here 
                                    				</p>
                                    			</font>
                                    			<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/reports/studentlist.png" width="100%">
                                    			
                                    		</div>
                                    			                                    		
                                    	</section>


                                    	<section id="StaffList">
                                    	<div class="row-fluid">
                                    		
                                    		<h2>Staff List  </h2>
                                    	</div>
                                    	<div class="row-fluid">
                                    		
                                    			<font size="4">
                                    				<p align="justify">
                                    					Admin Can view Staff List Reports From Here 
                                    				</p>
                                    			</font>
                                    			<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/reports/stafflist.png" width="100%">
                                    			
                                    		</div>
                                    			                                    		
                                    	</section>

                                    	<section id="AttendenceReport">
                                    	<div class="row-fluid">
                                    		
                                    		<h2>Attendence Reports  </h2>
                                    	</div>
                                    	<div class="row-fluid">
                                    		
                                    			<font size="4">
                                    				<p align="justify">
                                    					Admin Can view  Attendence Reports From Here 
                                    				</p>
                                    			</font>
                                    			<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/reports/stafflist.png" width="100%">
                                    			
                                    		</div>
                                    			                                    		
                                    	</section>
                                    	
                                    	<section id="Announcement">
                                    	<div class="row-fluid">
                                    		
                                    		<h2>Announcement </h2>
                                    	</div>
                                    	<div class="row-fluid">
                                    		
                                    			<font size="4">
                                    				<p align="justify">
                                    					Admin Can Add announcement and can view list
                                    				</p>
                                    			</font>
                                    			<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/announcement/addannouncement.png" width="100%">
                                    			<h2>View Announcement</h2>
                                    			<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/announcement/viewannouncement.png" width="100%">
                                    		</div>
                                    			                                    		
                                    	</section>


                                    	<section id="Archives">
                                    	<div class="row-fluid">
                                    		
                                    		<h2>Archives </h2>
                                    	</div>
                                    	<div class="row-fluid">
                                    		
                                    			<font size="4">
                                    				
                                    			</font>
                                    			<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/archive.png" width="100%">
                                    			
                                    		</div>
                                    			                                    		
                                    	</section>
                                    	


                                    	<section id="EntranceApplicantDetailS">
                                    	<div class="row-fluid">
                                    		
                                    		<h2> Downloads </h2><br>
                                    		<h2>Entrance Applicant Deatils </h2>
                                    	</div>
                                    	<div class="row-fluid">
                                    		
                                    			<font size="4">
                                    				<p align="justify">
                                    					Admin Can Add downloads entrance Applicant detail
                                    				</p>
                                    			</font>
                                    			<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/downloads/entranceapplicant.png" width="100%">
                                    			
                                    			</div>
                                    			                                    		
                                    	</section>


                                    	<section id="AdmissionApplicantDetail">
                                    	<div class="row-fluid">
                                    		
                                    		
                                    		<h2>Admission Applicant Deatils </h2>
                                    	</div>
                                    	<div class="row-fluid">
                                    		
                                    			<font size="4">
                                    				<p align="justify">
                                    					Admin Can Add downloads admission  Applicant detail
                                    				</p>
                                    			</font>
                                    			<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/downloads/addmission.png" width="100%">
                                    			
                                    			</div>
                                    			                                    		
                                    	</section>
                                    	
                                   





<!--

		<section id="Report">
                                        <div class="row-fluid">
                                                <h2>Report</h2>
                                        </div>
                                        </section>
		<section id="FacultyList">
                                        <div class="row-fluid">
                                                <h2>Faculty List</h2>
                                        </div>

                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
 Admin can view list of faculty after uploading csv file.	
                                                                 </p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/viewfaculty.png" height="40%" width="100%">
                                         </div>
                                    </section>
<section id="StaffList">
                                        <div class="row-fluid">
                                                <h2>Staff List</h2>
                                        </div>

                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
 Admin can view list of staff after uploading csv file.	
                                                                 </p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/viewstaff.png" height="40%" width="100%">
                                         </div>
                                    </section>
<section id="Archives">
                                        <div class="row-fluid">
                                                <h2>Archives</h2>
                                        </div>
                                        </section>
<section id="FeesMasterArchive">
                                        <div class="row-fluid">
                                                <h2>Fees Master Archive</h2>
                                        </div>

                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
This module will display the earlier data which is not used now. 	
                                                                 </p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/fmarchive.png" height="60%" width="100%">
                                         </div>
                                    </section>
<section id="ProgramSubjectPaperArchive">
                                        <div class="row-fluid">
                                                <h2>Program Subject Papaer Archive</h2>
                                        </div>

                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
This module will display the earlier data which is not used now. 	
                                                                 </p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/psparchive.png" height="60%" width="100%">
                                         </div>
                                    </section>
<section id="SemesterRuleArchive">
                                        <div class="row-fluid">
                                                <h2>Semester Rule Archive</h2>
                                        </div>

                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
This module will display the earlier data which is not used now. 	
                                                                 </p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/srarchive.png" height="60%" width="100%">
                                         </div>
                                    </section>
<section id="SubjectSemesterProgramwithDeparmentArchive">
                                        <div class="row-fluid">
                                                <h2>Subject Semester Program with Deparment Archive</h2>
                                        </div>

                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
This module will display the earlier data  which is not used now. 	
                                                                 </p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>uploads/helpscreenshot/sspdarchive.png" height="60%" width="100%">
                                         </div>
                                    </section>
<section id="AuthorityArchive">
                                        <div class="row-fluid">
                                                <h2>Authority Archive</h2>
                                        </div>

                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
This module will display the earlier data which is not used now.
                                                                 </p>
                                                        </ol>
                                                </font>
<!--img src="<?php echo base_url(); ?>uploads/helpscreenshot/sspdarchive.png" height="60%" width="100%">
                                         </div>
                                    </section>

<section id="AuditTrails">
                                        <div class="row-fluid">
                                                <h2>Audit Trails</h2>
                                        </div>
<section id="LogDetails">
                                        <div class="row-fluid">
                                                <h2>Log Details</h2>
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
This page consists of View Audit Trails, Insert Audit Trails, Update Audit Trails and summary recent activity. The page is continuously updated on the basis of the activities carried out in the system.	
                                                                 </p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/audittrails.png" height="130%" width="100%">
                                         </div>
                                    </section-->

			<section id="Profile">
                                        <div class="row-fluid">
                                                <h2>Profile</h2>
                                        </div>
				</section>
			<section id="ViewProfile">
                                        <div class="row-fluid">
                                                <h2>View Profile</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
    User Profile displays the Personal Data associated with specific user. From here user see Login Information and Other Information.
                                                                 </p>
                                                        </ol>
                                                </font>
	<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/viewprofile.png"  width="100%">
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
    Password can be changed from Change Password link. Each user must enter the old password first and then enter new password after that Re-type New Password for the user.

                                                                 </p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/changepassword.png"  width="100%">

                                         </div>
                                    </section> -->
					</div>

		</div>
	</div>
</body>
<div align="center"> <?php $this->load->view('template/footer');?></div>
</html>

