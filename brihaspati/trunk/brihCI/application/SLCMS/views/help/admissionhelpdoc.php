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
	    <li><a href="#NewStudentAdmission">New Student Admission</a></li>
	    <li><a href="#Registration">Registration</a></li>
	    <li><a href="#PersonelDetail">Personel Detail</a></li>
        <lI><a href="#TermsAndCondition">Terms And Condition</a></lI>
		<li><a href="#Upload">Upload</a></li>
		<li><a href="#CheckSubmitDocument">Check Submit Document</a></li>
		<li><a href="#Payment">Payment</a></li>
        <li><a href="#PrintForm">Print Form</a></li>  
    </ul>
    </div>
	</div>
 	    
 	    




                
              
                <div class="sideright">
					<section id="NewStudentAdmission">
					<div class="row-fluid">
						<h2>New Student Admission</h2>
					</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
New Student Can Proceed for Admission by Providing necssary Details.
First Step is student must got his/her <br> name in the merit list for provsional admission
. 
								</p>
							</ol>
						</font>
					</div>
					</section>


					<section id="Registration">
						<div class="row-fluid">
							<h2>Registration</h2>
						</div>
						<div class="row-fluid">
							<font size="4">
								<ol>
									<p align="justify" STYLE="line-height: 150%">
										First page is registraion detail page .
										here student can register  by providing necessary <br> details for admission process i.e.... <br>       
										JEE ROLL NO :-	<br>
										JEE APPLICATION NO <br>
										PROGRAM/COURSE:-<br>
										EMAIL:-<br>
										DATE OF BIRTH <br>

									</p>
								</ol>
							</font>						
						<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/new_student_admission/registration.png"  width="98%">
						<h2></h2>
						</div>
					</section>





					<section id="PersonelDetail">
						<div class="row-fluid">
							<h2> Personel Detail </h2>
						</div>
						<div class="row-fluid">
							<font size="4">
								<p align="justify" >
									after registation you will be forwared to personal detail page <br><br>
									Student have to verify the filled details and have to fill all the necessary detail <br>
									that include 	STUDENT PERSONEL DETAILS, FAMILIY DETAIL, EDUCATIONAL DETAILS
								</p>
							</font>
						<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/new_student_admission/personal_detail.png" width="98%">
						</div>
					</section>



						<section id="TermsAndCondition">
						<div class="row-fluid">
							<h2>Terms And Condition and Instruction</h2>
						</div>
						<div class="row-fluid">
							<font size="4">
								<p align="justify">after submitting personel deatil you will be forwarded to instruction page<br><br>
									Read all the instruction ,term and condition and if agree click on check box to confirm
								</p>
							</font>
					<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/new_student_admission/term_condition.png" width="98%">
						</div>
					</section>




					<section id="Upload">
						<div class="row-fluid">
							<h2>Uploads</h2>
						</div>
                     <div class="row-fluid">
							<font size="4">
								<p align="justify">
									After clicking on agree button 
									<br><br>you have to uploads recent passport size photo and your signature 
								</p>
							</font>
					<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/new_student_admission/uploads.png" width="98%">
						</div>
                  </section>



                  <section id="CheckSubmitDocument">
                  	<div class="row-fluid">
                  		<h2>Check Submit Document</h2>
                  	</div>
                     <div class="row-fluid">
							<font size="4">
								<p align="justify">
									After uploading you will be forwarded to check submit doc <br><br>
									here is check list of document you have to submit
								</p>
							</font>
					<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/new_student_admission/check_details.png" width="98%">
						</div>
					</section>


					<section id="Payment">
                  	<div class="row-fluid">
                  		<h2>Payment</h2>
                  	</div>
                     <div class="row-fluid">
							<font size="4">
								<p align="justify">
									after check list you will be forwarded to payment page <br><br>
									 here you have click on online page ----- then you be redirect to payment page ---<br><br>
									 after filling detail click on accept button to complete process
								</p>
							</font>
					<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/new_student_admission/fee_sub.png" width="98%">
					<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/new_student_admission/payment_pro1.png" width="98%">
					<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/new_student_admission/payment_pro2.png" width="98%">
						</div>
					</section>


					<section id="PrintForm">
                  	<div class="row-fluid">
                  		<h2>Print Form</h2>
                  	</div>
                     <div class="row-fluid">
							<font size="4">
								<p align="justify">
									After compeleting payment -- you can print form  
								</p>
							
					<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/new_student_admission/print_form.png" width="98%">
					<p align="justify">
									Then Click on Save. <br>
                                            Print your Application Form Details.  
								</p>
								<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/new_student_admission/print_form1.png" width="98%">
								<p align="justify">
									If You Want to go Student Home Page<br>.
                                   Then Click on Stuent Home Link<br>
                                   Stuent Home Page display on your screen.  
								</p>
								<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/new_student_admission/shown.png" width="98%">
						</div>
					</section>
					





				</div>

		</div>
	</div>
</body>
<div align="center"> <?php $this->load->view('template/footer');?></div>
</html>

