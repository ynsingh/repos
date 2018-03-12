<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<!--@name helpdocstudent.php 
  @author Deepika Chaudhary (chaudharydeepika88@gmail.com)
 -->
<html>
<head>
<title>User Manual</title>  
        <?php $this->load->view('template/header'); ?>
<!--        <h1> Welcome <?//= $this->session->userdata('username') ?></h1>-->
        <?php// $this->load->view('template/stumenu');?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url();?>assets/css/helpdoc.css">
</head>
<body>
        <div class="wrapper" width="100%"  style="background-color: #DDDDDD;">
        <div class="head">Online Admission System (Student)</div>
        <div class="content">
        <div class="sideleft">
        <div id="cssmenu">
        <ul>
<li><a href="#AboutOnlineAdmissionSystem(Student)">About Online Admission System (Student)</a></li>
<li><a href="#Dashboard">Dashboard</a></li>
 <li class='has-sub'><a href="#Fees">Fees</a>
                        <ul>
                        <li><a href="#FeesRecord">Fees Record</a></li>
                        </ul>
<li class='has-sub'><a href="#Subject">Subject</a>
                        <ul>
                        <li><a href="#SubjectRecord">Subject Record</a></li>
                        </ul>
<li class='has-sub'><a href="#Marks">Marks</a>
                        <ul>
                        <li><a href="#MarksRecord">Marks Record</a></li>
                        </ul>
<li class='has-sub'><a href="#Grade">Grade</a>
                        <ul>
                        <li><a href="#GradeCard">Grade Card</a></li>
                        </ul>
<li class='has-sub'><a href="#Request">Request</a>
                        <ul>
                        <li><a href="#SemesterRegistration">Semester Registration</a></li>
                         <li><a href="#ExamRegistration">Exam Registration</a></li>
                        <li><a href="#FeesDeposit">Fees Deposit</a></li>
                        </ul>
<li class='has-sub'><a href="#Download">Download</a>
<ul>
                        <li><a href="#AdmissionForm">Admission Form</a></li>
                         <li><a href="#ExamForm">Exam From</a></li>
                        <li><a href="#FeesReceipt">Fees Receipt</a></li>
			<li><a href="#AdmitCard">Admit Card</a></li>
                         <li><a href="#MarksCard">Marks Card</a></li>
                        <li><a href="#GradeCard">Grade Card</a></li>
                        </ul>
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
                                        <section id="AboutOnlineAdmissionSystem(Student)">
                                        <div class="row-fluid">
                                                <h2><b>About Online Admission System (Student)</b></h2>
                                        </div>
                                        <div class="row-fluid">
                                                <font size="4">
                                                        <ol>
                                                                <p align="justify" STYLE="line-height: 150%">
An independent platform, highly scalable content-delivery tool for web based e-learning system !!
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
                                                                        This page consists of Student details. The page is continuously updated on the basis of the activities carried out in the system.
                                                                        </p>
                                                                </ol>
                                                        </font>
                                                <img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/dashboardstu.png" height="100%" width="100%">
                                                </div>
                                        </section>


<section id="FeesRecord">
                                                <div class="row-fluid">
                                                        <h2><b>Fees Record</b></h2>
                                                </div>
                                                <div class="row-fluid">
                                                        <font size="4">
                                                                <ol>
                                                                        <p align="justify" STYLE="line-height: 150%">
From here student can see fees details with Program Name(Branch), Semester, Academic Year, Fees Type, Fees Amount, Fees Mode, Bank Name/Payment Gateway, Reference Number. Student can download fees receipt.
                                                                        </p>
                                                                </ol>
                                                        </font>
                                                <img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/feesrecord.png" height="50%" width="100%">
                                                </div>
                                        </section>
<section id="SubjectRecord">
                                                <div class="row-fluid">
                                                        <h2><b>Subject Record</b></h2>
                                                </div>
                                                <div class="row-fluid">
                                                        <font size="4">
                                                                <ol>
                                                                        <p align="justify" STYLE="line-height: 150%">
From here student can see all subject details with Program Name(Branch), Semester, Academic Year, Subject List.
                                                                        </p>
                                                                </ol>
                                                        </font>
                                                <img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/subjectrecord.png" height="50%" width="100%">
                                                </div>
                                        </section>
<section id="Marks">
                                        <div class="row-fluid">
                                                <h2><b>Marks</b></h2>
                                        </div>
                                </section>
<section id="Grade">
                                        <div class="row-fluid">
                                                <h2><b>Grade</b></h2>
                                        </div>
                                </section>
<section id="Request">
                                        <div class="row-fluid">
                                                <h2><b>Request</b></h2>
                                        </div>
                                </section>
<section id="ExamRegistration">
                                                <div class="row-fluid">
                                                        <h2><b>Exam Registration</b></h2>
                                                </div>
                                                <div class="row-fluid">
                                                        <font size="4">
                                                                <ol>
                                                                        <p align="justify" STYLE="line-height: 150%">
From here student can see Exam details.
                                                                        </p>
                                                                </ol>
                                                        </font>
                                                <img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/examreg.png" height="200%" width="100%">
                                                </div>
                                        </section>

<section id="Download">
                                        <div class="row-fluid">
                                                <h2><b>Download</b></h2>
                                        </div>
                                </section>

	<section id="AdmissionForm">
                                                <div class="row-fluid">
                                                        <h2><b>Admission From</b></h2>
                                                </div>
                                                <div class="row-fluid">
                                                        <font size="4">
                                                                <ol>
                                                                        <p align="justify" STYLE="line-height: 150%">
From here student can download admission form by this link.
                                                                        </p>
                                                                </ol>
                                                        </font>
                                                <img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/admissionform.png" height="200%" width="100%">
                                                </div>
                                        </section>
<section id="ExamForm">
                                                <div class="row-fluid">
                                                        <h2><b>Exam From</b></h2>
                                                </div>
                                                <div class="row-fluid">
                                                        <font size="4">
                                                                <ol>
                                                                        <p align="justify" STYLE="line-height: 150%">
From here student can download Exam form by this link.
                                                                        </p>
                                                                </ol>
                                                        </font>
                                                <img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/examform.png" height="120%" width="100%">
                                                </div>
                                        </section>

<section id="FeesReciept">
                                                <div class="row-fluid">
                                                        <h2><b>Fees Reciept</b></h2>
                                                </div>
                                                <div class="row-fluid">
                                                        <font size="4">
                                                                <ol>
                                                                        <p align="justify" STYLE="line-height: 150%">
From here student can download fees reciept by this link.
                                                                        </p>
                                                                </ol>
                                                        </font>
                                                <img src="<?php echo base_url(); ?>uploads/SLCMS/helpscreenshot/feesreciept.png" height="100%" width="100%">
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
    User Profile displays the personal data associated with specific user. From here user see Login and Other Information.
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

