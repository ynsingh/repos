<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<!--@name helpdocfaculty.php 
  @author Deepika Chaudhary (chaudharydeepika88@gmail.com)
 -->
<html>
<head>
<title>User Manual</title>  
        <?php $this->load->view('template/header'); ?>
        <h1> Welcome <?= $this->session->userdata('username') ?></h1>
        <?php $this->load->view('template/facultymenu');?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url();?>assets/css/helpdoc.css">
</head>
<body>
        <div class="wrapper" width="100%"  style="background-color: #DDDDDD;">
        <div class="head">Online Addmision System (Faculty)</div>
        <div class="content">
        <div class="sideleft">
        <div style="margin-top:10.5%;">
        <div id="cssmenu">
	<ul>
	     <li><a href="#AboutOnlineAddmisionSystem(Faculty)">About Online Addmision System (Faculty)</a></li>
             <li><a href="#Dashboard">Dashboard</a></li>
             <li class='has-sub'><a href="#Subject">Subject</a>
                        <ul>
                        <li><a href="#SubjectListwithProgram">Subject List with Paper</a></li>
                        <li><a href="#ChooseSubjectPaperforAcademicYearandSemester">Choose Subject Paper for Academic Year and Semester</a></li>
                        </ul>
             <li class='has-sub'><a href="#Student">Student</a>
                        <ul>
                        <li><a href="#StudentList">Student List</a></li>
                        <li><a href="#Attendance">Attendance</a></li>
			<li><a href="#Marks">Marks</a></li>
                        <li><a href="#Notice">Notice</a></li>
                        </ul>
	      <li><a href="#TimeTable">Time Table</a></li>
              <li class='has-sub'><a href="#Profile">Profile</a>
                        <ul>
                        <li><a href="#ViewProfile">View Profile</a></li>
                        <li><a href="#ChangePassword">Change Password</a></li>
                        <li><a href="#Logout">Logout</a></li>
                     </ul>

</ul>
</div>
            </div>
</div>
  <div class="sideright">
                                        <section id="AboutOnlineAddmisionSystem(Faculty)">
                                        <div class="row-fluid">
                                                <h2><b>About Online Addmision System (Faculty)</b></h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
A platform independent, highly scalable content-delivery tool for web based e-learning system !!
                                                                </p>
                                                        </ol>
                                                </font>
                                        </div>
                                        </section>
 <section id="Dashboard">
                                                <div class="row-fluid">
                                                        <h2><b>Dashboard</b></h2>
                                                </div>
                                                <div class="row-fluid">
                                                        <font size="4">
                                                                <ol>
                                                                        <p align="justify" STYLE="line-height: 150%">
                                                                        This page consists of details of User Profile, Course Details. The page is constantly updated on the basis of the activities carried out in the system.
                                                                        </p>
                                                                </ol>
                                                        </font>
                                                <img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/dashboardinst.png" height="120%" width="100%">
                                                </div>
                                        </section>

<section id="Subject">
                                        <div class="row-fluid">
                                                <h2><b>Subject</b></h2>
                                        </div>
                                </section>
<section id="SubjectListwithProgram">
                                                <div class="row-fluid">
                                                        <h2><b>Subject List with Program</b></h2>
                                                </div>
                                                <div class="row-fluid">
                                                        <font size="4">
                                                                <ol>
                                                                        <p align="justify" STYLE="line-height: 150%">
From here faculty can see all subject list with program with Category, Department, Program, Academic Year, Semester, Subject, Paper. 
                                                                        </p>
                                                                </ol>
                                                        </font>
<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/sublistprg.png" height="50%" width="100%">
                                                </div>
                                        </section>

<section id="ChooseSubjectPaperforAcademicYearandSemester">
                                        <div class="row-fluid">
                                                <h2><b>Choose Subject Paper for Academic Year and Semester</b></h2>
                                        </div>
                                </section>

<section id="Student">
                                        <div class="row-fluid">
                                                <h2><b>Student</b></h2>
                                        </div>
                                </section>
 <section id="StudentList">
                                        <div class="row-fluid">
                                                <h2><b>Student List</b></h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
From here can instructor see the list of student by select program.
                                                                </p>
                                                        </ol>
<img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/stulist.png" height="50%" width="100%">
                                                </font>
                                        </div>
                                        </section>
<section id="Attendence">
                                        <div class="row-fluid">
                                                <h2><b>Attendence</b></h2>
                                        </div>
                                </section>
<section id="Marks">
                                        <div class="row-fluid">
                                                <h2><b>Marks</b></h2>
                                        </div>
                                </section>
<section id="Notice">
                                        <div class="row-fluid">
                                                <h2><b>Notice</b></h2>
                                        </div>
                                </section>


 <section id="Profile">
                                        <div class="row-fluid">
                                                <h2><b>Profile</b></h2>
                                        </div>
                                </section>
                        <section id="ViewProfile">
                                        <div class="row-fluid">
                                                <h2><b>View Profile</b></h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
    User Profile display of personal data associted with specific user. From here user see Login Information and Other Information.
                                                                 </p>
                                                        </ol>
                                                </font>
        <img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/userprofile.png" height="130%" width="100%">
                                         </div>
                                    </section>
<section id="ChangePassword">
                                        <div class="row-fluid">
                                                <h2><b>Change Password</b></h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
    Password can be changed from the Change Password link. Each user must enter the old password and new password after then Retype New Password for the user.
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

