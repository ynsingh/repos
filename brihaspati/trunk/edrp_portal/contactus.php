	<?php include("admin_header.php");?>
	<?php
	session_start();
	include('SMTPconfig.php');
	include('SMTPclass.php');
	error_reporting(E_ALL ^ E_NOTICE); // hide all basic notices from PHP

	//If the form is submitted

	if(isset($_POST['submitted']))
	{
		// require a name from user
		if(trim($_POST['contactName']) === '')
		{
			$nameError =  'Forgot your name!'; 
			$hasError = true;
		} else {
			$name = trim($_POST['contactName']);
		}

		// need valid email

		if(trim($_POST['email']) === '')  {
			$emailError = 'Forgot to enter in your e-mail address.';
			$hasError = true;

		} else if (!preg_match("/^[[:alnum:]][a-z0-9_.-]*@[a-z0-9.-]+\.[a-z]{2,4}$/i", trim($_POST['email']))) {
			$emailError = 'You entered an invalid email address.';
			$hasError = true;
	
		} else {
			$email = trim($_POST['email']);
		}


		// we need at least some content

		if(trim($_POST['comments']) === '') {

			$commentError = 'You forgot to enter a message!';

			$hasError = true;

		} else {

			if(function_exists('stripslashes')) {

				$comments = stripslashes(trim($_POST['comments']));

			} else {

				$comments = trim($_POST['comments']);
			}
		}

		// upon no failure errors let's email now!

		if(!isset($hasError)) {

			$emailTo = 'brihspti@iitk.ac.in';

			$subject = 'Submitted message from '.$name;

			//$sendCopy = trim($_POST['sendCopy']);

			$body = "Name: $name \n\nEmail: $email \n\nComments: $comments";

			//$headers = 'From: ' .' <'.$emailTo.'>' . "\r\n" . 'Reply-To: ' . $email;

			$from = $email;
			$to = $emailTo;
			$SMTPMail = new SMTPClient ($SmtpServer, $SmtpPort, $SmtpUser, $SmtpPass, $from, $to, $subject, $body);
			$SMTPChat = $SMTPMail->SendMail();

			mail($emailto,$subject,$body,$header);

			// set our boolean completion value to TRUE

		$emailSent = true;

        //if ($emailSent) 
		//{
		   //succes         
			
		    $_SESSION['message'] = "Your information has been sent successfully.  You will get response with in 48 hours.";
		    header("location: contactus.php");
		//}
	}
}
?>
	<!--show all messages-->
	    <!-- @begin contact -->
 <link href="css/contactstyle.css" rel="stylesheet" type="text/css" />
		<div id="contact" class="section">

			<div class="container content">		

			<?php if(isset($emailSent) && $emailSent == true) { ?>

		    

		    	<?php } else { ?>

		   			<div class="desc">

		   			<?php 
		            	if($_SESSION['message'] != "")
		            	{ ?>
					<p class="alert"><?php echo $_SESSION['message'];
						unset($_SESSION['message']);
								 ?></p>                    		

							<?php
		            	}
		            ?>
						<h2>Contact Us</h2>
		                     <div style="line-height:250%">					
					</div>

					<div id="contact-form">

						<?php if(isset($hasError) || isset($captchaError) ) { ?>

		                		<p class="alert">Error submitting the form</p>

		            <?php } ?>

                    

                    			

					<form id="contact-us" action="contactus.php" method="post">

						<div class="formblock">

							<label class="screen-reader-text">Name</label>

							<input type="text" name="contactName" id="contactName" value="<?php if(isset($_POST['contactName'])) echo $_POST['contactName'];?>" class="txt requiredField" />

							<?php if($nameError != '') { ?>

								<br /><span class="error"><?php echo $nameError;?></span> 

							<?php } ?>

						</div>

                        

						<div class="formblock">

							<label class="screen-reader-text">Email</label>

							<input type="text" name="email" id="email" value="<?php if(isset($_POST['email']))  echo $_POST['email'];?>" class="txt requiredField email" />

							<?php if($emailError != '') { ?>

								<br /><span class="error"><?php echo $emailError;?></span>

							<?php } ?>

						</div>

                        

						<div class="formblock">

							<label class="screen-reader-text">Message</label>

							 <textarea name="comments" id="commentsText" class="txtarea requiredField"><?php if(isset($_POST['comments'])) { if(function_exists('stripslashes')) { echo stripslashes($_POST['comments']); } else { echo $_POST['comments']; } } ?></textarea>

							<?php if($commentError != '') { ?>

								<br /><span class="error"><?php echo $commentError;?></span> 

							<?php } ?>

						</div>

                        

				<button name="submit" type="submit" class="subbutton">Send</button>

							<input type="hidden" name="submitted" id="submitted" value="contactus"/>

					</form>			

				</div>

				

			<?php } ?>

		</div>

	<div id ="contentpage">
	<div style="width:50%;float:right;margin-top:-40%;margin-right:-1%;line-height:214%;background-color:#D0E9FD">
	<p><font size="4" color="black">Yatindra Nath Singh<br> 

	Professor<br>

	Electrical Engineering Department,

	Indian Institute of Technology<br> 

	Room No.301B<br> 

	Phone: +91-512-[679/392/259]-7841, 7070 (Lab)<br>

	Mobile No:9452048451<br>

	Email : ynsingh@iitk.ac.in<br></p>

	</div>
	<div style="width:50%;float:right;margin-top:-17%;margin-right:-1%;line-height:230%;background-color:#D0E9FD">

	<p> Nagendra Kumar Singh<br> 

	Project Engineer<br>

	Electrical Engineering Department,

	Indian Institute of Technology<br> 

	Room No.301A<br> 

	Phone:+91-512-7841, 7070 (Lab)<br>

	Mobile No:919450136012<br>

	Email : nksinghiitk@gmail.com<br></p>
	</font>
	</div>
	</div>
	</body>
<?php include("footer.php");
?>
  


