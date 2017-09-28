
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?><!DOCTYPE html>
<?php echo $prg_id; ?>
<html lang="en">
<html>
<head>
        <meta charset="utf-8">
        <title>Welcome to IGNTU</title>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <link rel="shortcut icon" href="<?php echo base_url('assets/images'); ?>/index.jpg">
<style>
ul li{text-align:justify;}
</style>
</head>
<body>

        <?php
        $this->load->view('template/header'); ?>
        <nav>   <h1>Welcome to IGNTU  </h1></nav>
<br></br>
<center>
<table border="0" style="width:70%;">
<tr><td>
<b style="font-size:20px;">STEP 1 :-</b>
<span style="font-size:20px;">When we run our site admission.igntuonline.in we find the first screen like below:</span>
<ol style="font-size:16px;list-style:none;margin-left:50px;">
<li>
Welcome to Indra Gandhi National Tribal University,Amarkatnak Madhya Pradesh-484886
</li>
</tr>
</ol>

</tr></td>
<tr><td>
<b style="font-size:20px;">STEP 2 :</b>
<span style="font-size:20px;">Applicant can select any of the courses based on their eligibility criteria</span>
<ol style="font-size:13px;list-style:none">
<li>

</li>
</ol>
</td></tr>
<tr><td>
<b style="font-size:20px;">STEP 3:</b>
<ol style="font-size:16px;list-style:none">
<li> when applicant click on apply now the below screen will appear with the detailed information there will be a button APPLY NOW like below
</li></ol>
<ol style="list-style:none; font-size:16px;">
<li>Indra Gandhi National Tribal National University,Amarkantak,Madhya Pradesh-484887</li>
</ol>
<ol style="font-size:18px;list-style:none"><b>NOTE:</b>
<ul style="list-style:none;font-size:16px;"><li>
<b>a:</b>Applicant who had registered last year can use the same UserID and password.Click o forgot password if you do not remember the password</li>
<li>
<b>b:</b>Candidate have to apply for Hons or PG programme either IGNTU at Amarkantak or Regional Campus.Manipur as separate selection lists will prepared for IGNTU & RCM.Candidates are advised not to apply for both.
</li>
<b>c:</b>The student who have already done their Hons or PG Programme from IGNTU at Amarkantak or from RCM at Imphal are not allowed to do either Hons or PG programme in the same or in any other Department.</li></ul><br></br>
<li><b>Form Cost:</b>Rs.300 for GN and OBC students and 100 rs. for SC/ST students</li>
<br>
<li><b>Mode of Payment:</b>You ca pay online through Credit Cards,Debit Cards,Net Banking only</li>
<br>
<li style="text-align:justify;">Candidates are required to have a valid personal e-mailID and mobile number.They should be kept active till the completion of the programme,if admissions granted. in case a candidate does not have a valid personal e-mail ID,she/he should create a new email ID before online. Candidates are required to upload their recent photograph and signature.Before applying online a candidate have scanned(digital)image of his/her photograph and signature. Candidate can pay the application fee through bank challan.The bank challan generated after filling the online application form and she/he can deposite fees in te following univrsity's account No,State Bak of India through any nationalized bank (NEFT mode) on the next working day.one day after the payment is made, the candidate can take the print of the application forms using the username and password. The applicants are requested to submit the application forms and required documents at the time of admission.</li>
<br>
<li>The photo size should not be more than 500 KB. Allowed photo types:png,gif,jpg,jpeg.</li>
<br>
<li><b> The following are the four step that candidates have to folloe to apply for admiddion to PG programmes in following three steps:</b></li>
<ul style="list-style:none;">
<li><b>1.</b>FIll the aplication forms and upload passport size photograph.</li>
<li><b>2.</b>Make online payment and pay the fee.</li>
<li><b>3.</b>Download the hall tcket at an appropriate time.</li></ul>
</ol>
<ul style="list-style:none;font-size:17px">
<b>INSTRUCTION TO CANDIDATES FOR THE ONLINE REGISTRATION OF UG FORM </b></ul>
<ol style="list-style:none; font-size:16px;">
<li style="text-align:justify;"><b>10.</b><b>The candidate will be able to modify/correct the particular before clicking on the submit button on the description page.Once the form is submitted.Candidate particulars cannot be edited. Therefore,candidates shall verify and confirm all the information filled in the application form before clicking on the submit button.</b></li>
</br>
<li><b>11.Procedure for making payment of application fee</b></li>
<ul style= "list-style:none">
<li><b>a:</b>Application fee can be paid by credit card or Net Banking.</li>
<li><b>b:</b>Payment by Credit/Debit card, Net Banking:The candisate can pay the application fee only through Debit/Credit card/Net Banking.After successful payment candidate will be able to print the acknowledgment receipt on the profile page of the applicant account as well as the through the summary link email on the applicants emailID.Note that only the application receipt with payment status as paid will be processed further.hence you are requested to pay before the last date to avoid any inconvenience.</li>
<li><b>c:</b>After  the successful payment payment applicants a advised to keep a printout of te completed for future references.</li> 
<li><b>d:</b>Applicants are advised to visit university website and for the latest information they are advised to be in touc with the website of university form time to time. However candidates will be informed of the changes in the schedule if any,under unvoidable circumustances by SMS and email. Therefore the mobile no. snd e-mail address should be filled up carefully.</li>
<li><b>e:</b> all correspondence related to PG admission-2017 should be addressed to the Director of Admission,Indra Gandhi National Tribal University,Amarkantak,Madhya Pradesh-484887.</li>
<li><b>f:</b>The application no. printed on the computer generated Acknowledgment Page must be mentioned in all such correspondence.It is therefore essential to note down the application number printed on Acknowledge Page.</li>
</ul>
<li><b>12: The candidate who have not passed the qualifying examination can also apply for entrance examinations to various programms</b></li>

</ol>
</td></tr>
<div>
<center><table>
        <!--<form action="<?php echo site_url('Enterence/step_zero'); ?>" method="POST" >-->
        <tr><td>
        <a href="<?php echo site_url('enterence/step_zero/'.$prg_id);?>" style="text-decoration:none;">
	 <input type="button" value="Apply Now" name="login" style="font-size:20px;margin-left:0px;">
	</a>
        <input type="hidden" name="passprgname" value="<?php echo $prg_id;?>">
        <!-- <input type="button" value="close" style="font-size:20px;margin-left:0px;">--->
        </td></tr>
        </form>
</table>
</center>
</div>
</table>
</center>
<?php //$thisPage2="studentaddDetail";
	$this->load->view('template/footer'); ?>
</body>
</html>





