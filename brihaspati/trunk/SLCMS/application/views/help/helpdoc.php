<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<!--@name changepasswd.php 
  @author Deepika Chaudhary (chaudharydeepika88@gmail.com)
 -->
<html>
<head>
<title>User Manual</title>  
        <?php $this->load->view('template/header'); ?>
        <h1> Welcome <?= $this->session->userdata('username') ?></h1>
        <?php $this->load->view('template/menu');?>
 	<link rel="stylesheet" type="text/css" href="<?php echo base_url();?>assets/css/helpdoc.css">
</head>
<body>
	<div class="wrapper" width="100%"  style="background-color: #DDDDDD;"> 
	<div class="head">Online Addmision System</div>
	<div class="content">
	<div class="sideleft">
        <div id="cssmenu">
        	<ul>
<li><a href="#AboutOnlineAddmisionSystem">About Online Addmision System</a></li>
	     <li><a href="#Dashboard">Dashboard</a></li>
	     <li class='has-sub'><a href="#Setup">Setup</a>
			<ul>
			<li><a href="#EmailSetting">Email Setting</a></li>
			<li><a href="#Role">Role</a></li>
			<li><a href="#Category">Category</a></li>
			<li><a href="#SeatReservation">Seat Reservation</a></li>
			<li><a href="#StudyCenter">Study Center</a></li>
			<li><a href="#Department">Department</a></li>
			<li><a href="#ProgramandSeat">Program and Seat</a></li>
			<li><a href="#Subject">Subject</a></li>
			<li><a href="#ProgramFees">Program Fees</a></li>
			</ul>
	    <li class='has-sub'><a href="#Map">Map</a>
			<ul>
			<li><a href="#MapUserwithRole">Map User with Role</a></li>
			<li><a href="#MapStudyCenterandProgramwithSeat">Map Study Center and Program with Seat</a></li>
			<li><a href="#MapProgramwithSubjectandPaper">Map Program with Subject and Paper</a></li>
			<li><a href="#MapSubjectandPaperwithTeacher">Map Subject and Paper with Teacher</a></li>
			</ul>
	    <li class='has-sub'><a href="#Upload">Upload</a>
			<ul>
			<li><a href="#UploadLogo">Upload Logo</a></li>
			<li><a href="#UploadStudentList">Upload Student List</a></li>
			<li><a href="#UploadTeacherList">Upload Teacher List</a></li>
			</ul>
	    <li><a href="#Report">Report</a></li>
	    <li class='has-sub'><a href="#Profile">Profile</a>
			<ul>
			<li><a href="#ViewProfile">View Profile</a></li>
			<li><a href="#ChangePassword">Change Password</a></li>
			<li><a href="#Logout">Logout</a></li>
	             </ul>       
                </div>
            </div>
                <div class="sideright">
					<section id="AboutOnlineAddmisionSystem">
					<div class="row-fluid">
						<h2>About Online Addmision System</h2>
					</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
Online Admission System is to automate the Academic university admission structure and its related operation and functionality. The objective of the initiative is to provide support to the administration and admission seeking candidates by providing a faster, transparent and easy way of keeping records and use them for reference and further proceedings. 
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
						<img src="<?php echo base_url(); ?>uploads/helpscreenshot/dashboard.png" height="130%" width="100%">
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
						<img src="<?php echo base_url(); ?>uploads/helpscreenshot/addemail.png" height="110%" width="100%">
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
				Admin can also view email setting after adding email setting. Admin can edit and delete this data.
                                                                 </p>
                                                        </ol>
                                                </font>
					<img src="<?php echo base_url(); ?>uploads/helpscreenshot/viewemail.png" height="90%" width="100%">

                                        </div>
                                        </section>

<section id="Role">
                                        
                                                <h2>Role</h2>
                                        <section id="AddRole">
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
<img src="<?php echo base_url(); ?>uploads/helpscreenshot/addrole.png" height="80%" width="100%">
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
				Admin can view role after adding role details. Admin can edit and delete this data.
                                                                 </p>
                                                        </ol>
<img src="<?php echo base_url(); ?>uploads/helpscreenshot/viewrole.png" height="80%" width="100%">
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
<img src="<?php echo base_url(); ?>uploads/helpscreenshot/addcategory.png" height="100%" width="100%">
                                        </div>
                                        </section>


			<section id="ViewCategaryDetail">
                                        <div class="row-fluid">
                                                <h2>View Category Detail</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
				Admin can also view category after adding Category Detail. Admin can edit and delete this data.
                                                                 </p>
                                                        </ol>
<img src="<?php echo base_url(); ?>uploads/helpscreenshot/viewcategory.png" height="80%" width="100%">
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
                                                                 </p>
                                                        </ol>
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
                                                                 </p>
                                                        </ol>
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
<img src="<?php echo base_url(); ?>uploads/helpscreenshot/adddepartment.png" height="120%" width="100%">
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
				Admin can view department after adding department Detail. Admin can edit and delete this data.
                                                                 </p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>uploads/helpscreenshot/viewdeptment.png" height="100%" width="100%">
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
Admin can add program and seat by filling Program Category, Program Name, Program Branch, Total Seat, Program Code, Program Short, Program Description, Program Min Time and Program Max Time.<br>
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
						<td>Batchlor of Art, Master of Art etc </td>
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
<img src="<?php echo base_url(); ?>uploads/helpscreenshot/addprog.png" height="120%" width="100%">
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
				Admin can also view program and seat after adding program and seat Detail. Admin can edit and delete this data.
                                                                 </p>
                                                        </ol>
<img src="<?php echo base_url(); ?>uploads/helpscreenshot/viewprog.png" height="90%" width="100%">
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
Admin can add subject by filling Subject Name, Subject Code, Subject Short, Subject Description.
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
						<td>The English Subject Centre supported the teaching and learning of English literature</td>
						</tr>
</table>
</P>

                                                        </ol>
<img src="<?php echo base_url(); ?>uploads/helpscreenshot/addsub.png" height="120%" width="100%">
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
				Admin can also view subject after adding subject Detail. Admin can edit and delete this data.
                                                                 </p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>uploads/helpscreenshot/viewsub.png" height="90%" width="100%">
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
Admin can add program fees with head list by adding program name, Academic Year,Semester, Category, Gender, Head, Amount and Description. <br>                                
The format of add program fees with head list are given below:-
						<P ALIGN=JUSTIFY STYLE="margin-bottom: 0in; line-height: 150%">
						<table border="2px solid black" style="width:700px">
						<tr>
						<td><B>Field</B></td>
						<td><B>Description</B></td>
						</tr>
						<tr>
						<td><B>Program name</B></td>
						<td>Select Program Ex:- Batchlor of Art, Master of Art etc  </td>
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
						<td>Enter description</td>
						</tr>
						</table>
						</P>     
</p>     
                                                        </ol>
                                                </font>

                                        </div>
<img src="<?php echo base_url(); ?>uploads/helpscreenshot/fees.png" height="120%" width="100%">
                                       </section>
<section id="ViewProgramFeeswithHead">
                                        <div class="row-fluid">
                                                <h2>View Program Fees with head</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
Admin can also View Campus Program Seat List after adding Program Fees with Head Detail. We can edit and delete this data.
                                                                 </p>
                                                        </ol>
                                         <img src="<?php echo base_url(); ?>uploads/helpscreenshot/viewfees.png" height="90%" width="100%">       </font>

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
                                                                 </p>
                                                        </ol>
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
Add the number of seat for program in set a campus.
                                                                 </p>
                                                        </ol>
                                                </font>
<img src="<?php echo base_url(); ?>uploads/helpscreenshot/addmap.png" height="100%" width="100%">
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
Admin can also view Study Center and Program with Seat after adding campus program seat list Detail. We can edit and delete this data.
                                                                 </p>
                                                        </ol>
                                         <img src="<?php echo base_url(); ?>uploads/helpscreenshot/viewmapps.png" height="90%" width="100%">       </font>

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
Admin can Add subject paper list by adding Degree, Academic Year, Subject Name, Paper Category, Paper No, Paper Name, Paper Code, Paper Short Name, Paper Description.<br>
The format of add subject paper list are given below:-
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
<img src="<?php echo base_url(); ?>uploads/helpscreenshot/addsublist.png" height="100%" width="100%">       </font>
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
Admin can also view subject paper list after adding subject paper Detail. We can edit and delete this data.
                                                                 </p>
                                                        </ol>
                                         <img src="<?php echo base_url(); ?>uploads/helpscreenshot/viewsublist.png" height="70%" width="100%">       </font>

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
                                                                 </p>
                                                        </ol>
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
				<img src="<?php echo base_url(); ?>uploads/helpscreenshot/uploadlogo.png" height="80%" width="100%">
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
deepika,deepika@gmail.com,01,02,03,9415938783<br>
<b>Note:-</b></br>
All information are mandatory except mobile number.			
                                                                 </p>
                                                      </ol>
				<img src="<?php echo base_url(); ?>uploads/helpscreenshot/uploadteacher.png" height="80%" width="100%">
                                                </font>
                                         </div>
                                    </section>

		<section id="UploadStudentList">
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
                                    </section>
		<section id="Report">
                                        <div class="row-fluid">
                                                <h2>Report</h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
                                                                 </p>
                                                        </ol>
                                                </font>
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
    User Profile display of personal data associted with specific user. From here user see Login Information and Other Information.
                                                                 </p>
                                                        </ol>
                                                </font>
	<img src="<?php echo base_url(); ?>uploads/helpscreenshot/userprofile.png" height="130%" width="100%">
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
<img src="<?php echo base_url(); ?>uploads/helpscreenshot/changepassword.png" height="80%" width="100%">

                                         </div>
                                    </section>
					</div>

		</div>
	</div>
</body>
<div align="center"> <?php $this->load->view('template/footer');?></div>
</html>

