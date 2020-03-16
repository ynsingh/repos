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
	<div class="head">Online Enterance Form</div>
	<div class="wrapper" width="100%"  style="background-color: #DDDDDD;"> 
	<div class="content">
	<div class="sideleft">
    <div id="cssmenu">
    <ul>
	    
	    <li class="Has-sub"><a href="#Home">Home</a>
	    	<ul>
	    		<li><a href="#Registration">Registration</a></li>
	    		<li><a href="#PersonelDetail">Personel Detail</a></li>
	    		<li><a href="#EducationalDetail">Educational Detail</a></li>
	    		<li><a href="#Uploads">Uploads</a></li>
	    		<li><a href="#Payment">Payment</a></li>
	    		<li><a href="#PrintForm">Print Form</a></li>
	    	</ul>
	    </li>
	    <li><a href="#ImportantDate">Important Date</a></li>
        <lI><a href="#IncompleteForm">Incomplete Entrance Form</a></lI>
		<li><a href="#PrintEntranceForm">Print Entrance Form</a></li>
		<li><a href="#DownloadHallTicket">Download Hall icket</a></li>
		<li><a href="#ContactUs">Contact</a></li>
    </ul>
    </div>
	</div>
 	    
 	    




                
              
                <div class="sideright">

					<section id="Home">
					<div class="row-fluid">
						<h2>Entrance form home</h2>
					</div>
					<div class="row-fluid">
						<font size="4">
							<ol>
								<p align="justify" STYLE="line-height: 150%">
New Student can fill entrance form <br>
Click on Home Page</p>
<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/enterance_admission/home_studycenter.png"  width="98%">
				
				<p align="justify">
Select Study Center from drop down.<br>
After Select Study Center Courses apper on your Screen.
</p>
<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/enterance_admission/home_studycenter1.png"  width="98%">
				<p align="justify">
Click on Under Graduate and BBA Management..
</p>
<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/enterance_admission/home_studycenter2.png"  width="98%">
<p align="justify">
Click on Under Graduate and BBA Management..<br>
Instruction page is appear.
Click on Register Now
</p>
<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/enterance_admission/instruction_page.png"  width="98%">
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
										Enter your details and click on submit button. Verification code send to you over email.
										EMAIL:-<br>
										MOBILE NO: <br>
										DATE OF BIRTH <br>
										PROGRAM/COURSE:-<br>
										VERIFICATION CODE:-<br>
								</p>
				<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/enterance_admission/registration.png"  width="98%">
				                 <p align="justify" >
										Open your mail box find Verification code.
								 </p>
				<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/enterance_admission/open_email.png"  width="98%">
						
				                 <p align="justify" >
										Enter Verification Code. Click on Submit Button.
								 </p>
		<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/enterance_admission/email_verification.png"  width="98%">
							</font>	
							</ol>
							</font>
							</div>
						</section>					
				
					
					




					<section id="PersonelDetail">
						<div class="row-fluid">
							<h2> Personel Detail </h2>
						</div>
						<div class="row-fluid">
							<font size="4">
								<p align="justify" >
									After Submitting verification code. you are reach Personnel Page
								</p>
							</font>
				<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/enterance_admission/personel_detail.png" width="98%">
						</div>
					</section>



						<section id="EducationalDetail">
						<div class="row-fluid">
							<h2> Educational Detail</h2>
						</div>
						<div class="row-fluid">
							<font size="4">
								<p align="justify">Fill all the details and click the submit button.<br>
									After submitted Personal and family details. you redirected to Education Page.
								</p>
							</font>
				<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/enterance_admission/eduction_detail.png" width="98%">
				<p align="justify">F
					AFill all the Education details and click the submit button.<br><br>
									After Submitted Education Details
								</p>
						</div>
					</section>




					<section id="Upload">
						<div class="row-fluid">
							<h2>Uploads</h2>
						</div>
                     <div class="row-fluid">
							<font size="4">
								<p align="justify">
									 you transfer to Upload Enclosure Page. 
									<br>you have to uploads recent passport size photo and your signature 
								</p>
							</font>
					<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/enterance_admission/uploads.png" width="98%">
					</p>
							
						  Attach your photograph and signature. Click on upload button.
                            
					</p>
						</div>
                  </section>





					<section id="Payment">
                  	<div class="row-fluid">
                  		<h2>Payment</h2>
                  	</div>
                     <div class="row-fluid">
							<font size="4">
								<p align="justify">
									After upload photograph, signature. you are transfer to Payment Page.
								</p>
					<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/enterance_admission/pay1.png" width="98%">
					<p align="justify">
									Click on Pay Now.
								</p>
					<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/enterance_admission/pay2.png" width="98%">
					
						</div>
					</section>


					<section id="PrintForm">
                  	<div class="row-fluid">
                  		<h2>Print Form</h2>
                  	</div>
                     <div class="row-fluid">
							<font size="4">
								<p align="justify">
									After payment fees you transfer to Print Form Page.
									<li> Now you can print your admission application.</li>
									<li> Save your admission application.</li>

								</p>
					<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/enterance_admission/print_form.png" width="98%">
					<p align="justify">
									Print your admission application.
								</p>
				<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/enterance_admission/print_form1.png" width="98%">
					</div>
					</section>



					<section id="ImportantDate">
                  	<div class="row-fluid">
                  		<h2>Important Date</h2>
                  	</div>
                     <div class="row-fluid">
							<font size="4">
								<p align="justify">
									Want to see click on “Important Date”.
								</p>
					<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/enterance_admission/important_date.png" width="98%">
					</div>
					</section>
					





					<section id="IncompleteForm">
                  	<div class="row-fluid">
                  		<h2>Incomplete Form</h2>
                  	</div>
                     <div class="row-fluid">
							<font size="4">
								<p align="justify">
									Click on Incomplete Enterance Form. If your application form has not been complete.<br>
									 So you can complete the application by this link.
									</p>
				<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/enterance_admission/incomplete_form.png" width="98%">
					<p align="justify">
									Enter Your Details and Click on Submit Button.
								</p>
				<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/enterance_admission/incomplete_form1.png" width="98%">
					</div>
					</section>
					



					<section id="PrintEntranceForm">
                  	<div class="row-fluid">
                  		<h2>Print Entrance Form</h2>
                  	</div>
                     <div class="row-fluid">
							<font size="4">
								<p align="justify">
									Print your Complete Enterance Form.
								</p>
					<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/enterance_admission/compele_form_print.png" width="98%">
					<p align="justify">
									Enter your details. Click on Submit button.
								</p>
			<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/enterance_admission/compele_form_print1.png" width="98%">
			<p align="justify">
									After click in submit button. Enterance Form will be open..
								</p>
			<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/enterance_admission/compele_form_print2.png" width="98%">
					</div>
					</section>



					<section id="DownloadHallTicket">
                  	<div class="row-fluid">
                  		<h2>Download Hall Ticket</h2>
                  	</div>
                     <div class="row-fluid">
							<font size="4">
								<p align="justify">
									Click on Download Hall Ticket Link.
								</p>
					<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/enterance_admission/download_hall1.png" width="98%">
					<p align="justify">
									Enter your details. Click on Submit button.
								</p>
				<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/enterance_admission/download_hall2.png" width="98%">
				<p align="justify">
									After click in submit button. Hall Ticket will be open.
								</p>
				<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/enterance_admission/hall_ticket.png" width="98%">
					</div>
					</section>


					


					<section id="ContactUs">
                  	<div class="row-fluid">
                  		<h2>Contact Us</h2>
                  	</div>
                     <div class="row-fluid">
							<font size="4">
								<p align="justify">
									Want to see click on “Contact Us”
								</p>
					<img src="<?php echo base_url(); ?>helpimages/slcmshelpscreenshot/enterance_admission/contact_us.png" width="98%">
					</div>
					</section>
					

					
					




				</div>

		</div>
	</div>
</body>
<div align="center"> <?php $this->load->view('template/footer');?></div>
</html>

