<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<!--@name helpdoc.php 
  @author Deepika Chaudhary (chaudharydeepika88@gmail.com)
  @author Neha Khullar (nehukhullar@gmail.com) Modification 
 -->
<html>
<head>
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
	<div class="wrapper" width="100%"  style="background-color: #DDDDDD;"> 
	<div class="head">Online Admission System</div>
	<div class="content">
	<div class="sideleft">
        <div id="cssmenu">
        	<ul>
<li><a href="#AboutOnlineAdmissionSystem">About Online Admission System</a></li>
	     <li><a href="#Dashboard">Dashboard</a></li>
	     <li class='has-sub'><a href="#Setup">Setup</a>
			<ul>
			<li><a href="#EmailSetting">Email Setting</a></li>
			<li><a href="#Role">Role</a></li>
			<li><a href="#Category">Category</a></li>
			<li><a href="#SeatReservation">Seat Reservation</a></li>
			<li><a href="#StudyCenter">Study Center</a></li>
			<li><a href="#Department">Department</a></li>
			<li><a href="#Designation">Designation</a></li>
			<li><a href="#Authority">Authority</a></li>
                        <li><a href="#ProgramCategory">Program Category</a></li>
			<li><a href="#ProgramandSeat">Program and Seat</a></li>
			<li><a href="#Subject">Subject</a></li>
			<li><a href="#ProgramFees">Program Fees</a></li>
			<li><a href="#DegreeRule">Degree Rule</a></li>
			<li><a href="#SemesterRule">Semester Rule</a></li>
			<li><a href="#GradeMaster">Grade Master</a></li>
			</ul>
	    <li class='has-sub'><a href="#Map">Map</a>
			<ul>
			<li><a href="#MapUserwithRole">Map User with Role</a></li>
			<li><a href="#MapStudyCenterandProgramwithSeat">Map Study Center and Program with Seat</a></li>
			<li><a href="#MapProgramwithSubjectandPaper">Map Program with Subject and Paper</a></li>
			<li><a href="#MapSubjectandPaperwithTeacher">Map Subject and Paper with Teacher</a></li>
                        <li><a href="#MapSubjectandPaperwithPrerequisite">Map Subject and Paper with Prerequisite</a></li>
                        <li><a href="#MapSubjectandSemester">Map Subject and Sememster</a></li>
			</ul>
	    <li class='has-sub'><a href="#Upload">Upload</a>
			<ul>
			<li><a href="#UploadLogo">Upload Logo</a></li>
			<!--li><a href="#UploadStudentList">Upload Student List</a></li-->
			<li><a href="#UploadTeacherList">Upload Teacher List</a></li>
                        <li><a href="#UploadStaffList">Upload Staff List</a></li>
			</ul>
	    <li class='has-sub'><a href="#Reports">Reports</a>
                        <ul>
                        <li><a href="#StudentList">Student List</a></li>
                        <li><a href="#FacultyList">Faculty List</a></li>
                        <li><a href="#StaffList">Staff List</a></li>
                        </ul>
 	    <li class='has-sub'><a href="#Archivers">Archives</a>
                        <ul>
                        <li><a href="#FeesMasterArchive">Fees Master Archive</a></li>
                        <li><a href="#ProgramSubjectPaperArchive">Program Subject Paper Archive</a></li>
                        <li><a href="#SemesterRuleArchive">Semester Rule Archive</a></li>
                        <li><a href="#SubjectSemesterProgramwithDepartmentArchive">Subject Semester Program with Department Archive</a></li>
                        </ul>
	   <li class='has-sub'><a href="#AuditTrails">Audit Trails</a>
                        <ul>
                        <li><a href="#LogDetails">Log Details</a></li>
                        </ul>	  
	    <li class='has-sub'><a href="#Profile">Profile</a>
			<ul>
			<li><a href="#ViewProfile">View Profile</a></li>
			<li><a href="#ChangePassword">Change Password</a></li>
			<li><a href="#Logout">Logout</a></li>
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
						<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/dashboard.png" height="120%" width="100%">
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
						<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/addemail.png" height="80%" width="100%">
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
					<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/viewemail.png" height="50%" width="100%">

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
<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/addrole.png" height="55%" width="100%">
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
<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/viewrole.png" height="60%" width="100%">
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
<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/addcategory.png" height="90%" width="100%">
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
<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/viewcategory.png" height="60%" width="100%">
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
<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/addseat.png" height="75%" width="100%">
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
<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/viewseat.png" height="50%" width="100%">
                                                </font>
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
<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/addstudycenter.png" height="150%" width="100%">
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
<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/viewstudycenter.png" height="60%" width="100%">
                                                </font>
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
<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/adddepartment.png" height="90%" width="100%">
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
<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/viewdeptment.png" height="50%" width="100%">
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
<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/adddesignation.png" height="75%" width="100%">
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
<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/viewdesignation.png" height="50%" width="100%">
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
<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/addauthority.png" height="75%" width="100%">
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
<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/viewauthority.png" height="80%" width="100%">
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
<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/programcategory.png" height="70%" width="100%">
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
<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/viewprogramcategory.png" height="50%" width="100%">
                                        </div>
                                        </section>
			<section id="ProgramandSeat">
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
<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/addprog.png" height="100%" width="100%">
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
<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/viewprog.png" height="60%" width="100%">
                                                </font>
                                        </div>
                                        </section>


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
<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/addsub.png" height="90%" width="100%">
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
<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/viewsub.png" height="60%" width="100%">
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
<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/fees.png" height="110%" width="100%">
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
                                         <img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/viewfees.png" height="50%" width="100%">      
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
<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/degreerule.png" height="110%" width="100%">
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
<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/viewdegreerule.png" height="50%" width="100%">      
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
                                         <img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/semesterrule.png" height="90%" width="100%">       </font>

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
                                         <img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/viewsemesterrule.png" height="45%" width="100%">       </font>

                                         </div>
                                    </section>

<section id="GradeMaster">
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
                                         <img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/mastergrade.png" height="90%" width="100%">       
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
                                         <img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/viewgrade.png" height="90%" width="100%">       
					</font>

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
<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/maprole.png" height="100%" width="100%"> 
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
                                         <img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/viewmaprole.png" height="70%" width="100%">       
					</font>
                                         </div>
                                    </section>
			<section id="MapStudyCenterandProgramwithSeat">
                                        <div class="row-fluid">
                                                <h2>Map Study Center and Program with Seat</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
Add the number of seat for program in a campus.
                                                                 </p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/addmap.png" height="100%" width="100%">
                                         </div>
                                    </section>
<section id="ViewStudyCenterandProgramwithSeat">
                                        <div class="row-fluid">
                                                <h2>View Study Center and Program with Seat</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
Admin can view Study Center and Program with Seat after adding Campus Program Seat List Details. We can edit and delete this data.
                                                                 </p>
                                                        </ol>
                                         <img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/viewmapps.png" height="60%" width="100%">       </font>

                                         </div>
                                    </section>
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
<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/addsublist.png" height="110%" width="100%">       </font>
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
                                         <img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/viewsublist.png" height="50%" width="100%">      
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
 <img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/mapsubjectandpaperwithteacher.png" height="115%" width="100%">  
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
                                         <img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/viewsubtech.png" height="70%" width="100%">      
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
 <img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/mapsubjectandpaperwithprerequisite.png" height="100%" width="100%">  
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
                                         <img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/viewsubprerequisite.png" height="70%" width="100%">      
					 </font>
                                         </div>
                                    </section>
<section id="MapSubjectandSemester">
                                        <div class="row-fluid">
                                                <h2>Map Subject and Semester</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
This subject is related to particular subject<br>
The format of add subject paper list are given below:-
                                                <P ALIGN=JUSTIFY STYLE="margin-bottom: 0in; line-height: 150%">
                                                <table border="2px solid black" style="width:700px">
                                                <tr>
                                                <td><B>Field</B></td>
                                                <td><B>Description</B></td>
                                                </tr>
                                                <tr>
                                                <td><B>Department Name</B></td>
                                                <td>Select Department Name from drop down.</td>
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
 						<td><B>Semester/Year</B></td>
                                                <td>Select Semester/Year from drop down.</td>
                                                </tr>
                                                <tr>
						<td><B>Subject Name</B></td>
                                                <td>Select Subject Name from drop down.</td>
                                                </tr>
                                                <tr>
						<td><B>Subject Type</B></td>
                                                <td>Select Subject Type from drop down.</td>
                                                </tr>
                                                </tr>
						</P>
					</table>

                                                                 </p>
                                                        </ol>
                                                </font>
 <img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/mapsubjectandsemester.png" height="100%" width="100%">  
                                         </div>
                                    </section>
<section id="ViewMapSubjectandSemester">
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
                                         <img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/viewsubsem.png" height="70%" width="100%">      
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
				<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/uploadlogo.png" height="50%" width="100%">
                                                </font>
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
				<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/uploadteacher.png" height="50%" width="100%">
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
Neha,nehukhullar@gmail.com,01,04,01,9415938783<br>
<b>Note:-</b></br>
All information are mandatory except mobile number.
                                                                 </p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/uploadstaff.png" height="50%" width="100%">
                                         </div>
                                    </section>

		<!--section id="UploadStudentList">
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
                                         </div>
                                    </section-->


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
<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/viewfaculty.png" height="40%" width="100%">
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
<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/viewstaff.png" height="40%" width="100%">
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
<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/fmarchive.png" height="60%" width="100%">
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
<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/psparchive.png" height="60%" width="100%">
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
<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/srarchive.png" height="60%" width="100%">
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
<!--img src="<?php echo base_url(); ?>uploads/helpscreenshot/sspdarchive.png" height="60%" width="100%"-->
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
<!--img src="<?php echo base_url(); ?>uploads/helpscreenshot/sspdarchive.png" height="60%" width="100%"-->
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
<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/audittrails.png" height="130%" width="100%">
                                         </div>
                                    </section>

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
	<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/userprofile.png" height="130%" width="100%">
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
<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/changepassword.png" height="70%" width="100%">

                                         </div>
                                    </section>
					</div>

		</div>
	</div>
</body>
<div align="center"> <?php $this->load->view('template/footer');?></div>
</html>

