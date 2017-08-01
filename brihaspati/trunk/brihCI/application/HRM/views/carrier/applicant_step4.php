<!-------------------------------------------------------
    -- @name applicant_step4.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
--------------------------------------------------------->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>IGNTU:API</title>
	<link rel="shortcut icon" href="<?php echo base_url('assets/images'); ?>/index.jpg">
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/message.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/studentNavbar.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/style.css">
	<style>
		input[type='text']{font-size:17px;height:30px;background-color:white;font-family:Times New Roman;font-weight:bold;}
		tr th{background:black;color:white;font-weight:bold;text-align:justify;}
		thead th{text-align:center;}
		tr td{font-size:18px;font-family:Times New Roman;font-weight:bold;}
	</style>
</head>
<body>


<div>
	<div id="body">
	<?php  // $thisPage2="studentaddDetail"; 
		$this->load->view('template/header'); ?>
<nav> 	<h1>Welcome to IGNTU  </h1></nav>
</br>
	<?php $this->load->view('carrier/applicant_head'); ?>
<!--------------------------------------------------------ERROR DISPLAY-------------------------------------------------------------->
<?php
echo "<center>";

	if($this->session->flashdata('msg')){
echo "<div style='font-size:20px;text-align:center;background-color:#DFF2BF;width:50%;height:30px;color:green;'>";
	echo $this->session->flashdata('msg');
echo "<div>";	
}

echo "</center>";
?>
	<div align="left" style="margin-left:30px;width:1700px;font-size:18px;">
        <?php echo validation_errors('<div class="isa_warning">','</div>');?>
        <?php echo form_error('<div style="margin-left:30px;" class="isa_error">','</div>');?>
        <?php if(isset($_SESSION['success'])){?>
        <div class="alert alert-success"><?php echo $_SESSION['success'];?></div>
        <?php
    	 };
       	?>
	
        <?php if(isset($_SESSION['err_message'])){?>
             <div class="isa_error" ><div ><?php echo $_SESSION['err_message'];?></div></div>
        <?php
        };
	?>  
      </div>

<h2>Application form for teaching position (Assistant professor)</h2>
<h3>(To be enclosed with the Application form)</h3>
<h1>Computation of API Score</h1>
<h3>(As on the last date of submission of Application Form)</h3>

<center>
	<table style="width:40%;">
		<tr>
			<td align="left"><label>Name of the Candidate :</label></br>
			 <input type="text" name="Aname" placeholder="Enter your name"></td>

			<td align="left"><label>Post Applied for :</label></br>
			 <input type="text" name="Apost" placeholder="Post applied"></td>
			<td align="left"><label>Department :</label></br>
			 <input type="text" name="Adept" placeholder="Department name"></td>
		</tr>	
<tr height=10>
		<table style="width:80%;text-align:justify;border:1px solid black;">
			<thead>
			    <th colspan=8 style="background-color:#7e7e7e;color:white;font-size:22px;text-align:justify;">
				<input type="text" name="category" style="width:25%;" value="Category-III" readonly>
			   </th>
			</thead>
			<thead>
			    <th colspan=8 style="background-color:#7e7e7e;color:white;font-size:22px;text-align:justify;">
				<input type="text" name="part-a" style="width:25%;" value="PART-A : Research paper published in journals" readonly>
			   </th>
			</thead>
		
			<thead style="font-size:20px;">
				<th>Sr. No.</th><th>Activities</th><th>Maximum Score</th><th>Number of Articles</th><th>Score Claimed</th><th>Score Varified</th>
			</thead>

			<tbody>
				<tr>
					<td style="font-size:16px;background-color:#7e7e7e;color:white;font-weight:bold;text-align:center;">(i)</td>	
					<td colspan=5 style="font-size:18px;background-color:#7e7e7e;color:white;font-weight:bold;">
					
					<input type="text" name="parte-i" style="width:23%;" value="Papers with Impact Factor above 10" readonly>
					</td>
				</tr>
			
				<tr>
					<td></td>
					<td><input type="text" name="spaper" style="width:90%;" value="Single Author" readonly></td>
					<td><input type="text" name="perpaper" style="width:90%;" value="50 Per Paper" readonly></td>
					<td><input type="text"   placeholder="Number of articles" name="Pnumber" style="width:97%;" value="<?php echo @$this->data['Pnumber']; ?>"></td>
					<td><input type="text"   placeholder="Score claimed" name="Pscore" style="width:97%; " value="<?php echo @$this->data['Pscore']; ?>"></td>
					<td><input type="text"   placeholder="Score varified" name="Pvarified" style=" width:97%;" value="<?php echo @$this->data['Pvarified']; ?>"></td>
								
				</tr>

				<tr>
					<td></td>
					<td><input type="text" name="first" style="width:90%;" value="First /Corrseponding /Supervisor /Mentor" readonly></td>
					<td><input type="text" name="35per" style="width:90%;" value="35 Per Paper Equally Shared" readonly></td>
					<td><input type="text"   placeholder="Number of articles" name="Fnumber" style="width:97%;" value="<?php echo @$this->data['Fnumber']; ?>"></td>
					<td><input type="text"   placeholder="Score claimed" name="Fscore" style="width:97%; " value="<?php echo @$this->data['Fscore']; ?>"></td>
					<td><input type="text"   placeholder="Score varified" name="Fvarified" style=" width:97%;" value="<?php echo @$this->data['Fvarified']; ?>"></td>
								
				</tr>

				<tr>
					<td></td>
					<td><input type="text" name="rest" style="width:90%;" value="Rest of The Authors" readonly></td>
					<td><input type="text" name="15per" style="width:90%;" value="15 Per Paper Equally Shared" readonly></td>
					<td><input type="text"   placeholder="Number of articles" name="Rnumber" style="width:97%;" value="<?php echo @$this->data['Rnumber']; ?>"></td>
					<td><input type="text"   placeholder="Score claimed" name="Rscore" style="width:97%; " value="<?php echo @$this->data['Rscore']; ?>"></td>
					<td><input type="text"   placeholder="Score varified" name="Rvarified" style=" width:97%;" value="<?php echo @$this->data['Rvarified']; ?>"></td>
								
				</tr>

				<tr>
					<td style="font-size:16px;background-color:#7e7e7e;color:white;font-weight:bold;text-align:center;">(ii)</td>	
					<td colspan=5 style="font-size:18px;background-color:#7e7e7e;color:white;font-weight:bold;">
					<input type="text" name="parte-ii" style="width:23%;" value="Papers with Impact Factor above 10" readonly></td>
				</tr>
			
				<tr>
					<td></td>
					<td><input type="text" name="single" style="width:90%;" value="Single Author" readonly></td>
					<td><input type="text" name="45per" style="width:90%;" value="45 Per Paper" readonly></td>
					<td><input type="text"   placeholder="Number of articles" name="Imnumber" style="width:97%;" value="<?php echo @$this->data['Imnumber']; ?>"></td>
					<td><input type="text"   placeholder="Score claimed" name="Imscore" style="width:97%; " value="<?php echo @$this->data['Imscore']; ?>"></td>
					<td><input type="text"   placeholder="Score varified" name="Imvarified" style=" width:97%;" value="<?php echo @$this->data['Imvarified']; ?>"></td>
								
				</tr>

				<tr>
					<td></td>
					<td><input type="text" name="(ii)first" style="width:90%;" value="First /Corrseponding /Supervisor /Mentor" readonly></td>
					<td><input type="text" name="31per" style="width:90%;" value="31.5 Per Paper Equally Shared" readonly></td>
					<td><input type="text" placeholder="Number of articles" name="Conumber" style="width:97%;" value="<?php echo @$this->data['Conumber']; ?>"></td>
					<td><input type="text" placeholder="Score claimed" name="Coscore" style="width:97%; " value="<?php echo @$this->data['Coscore']; ?>"></td>
					<td><input type="text" placeholder="Score varified" name="Covarified" style=" width:97%;" value="<?php echo @$this->data['Covarified']; ?>"></td>
								
				</tr>

				<tr>
					<td></td>
					<td><input type="text" name="(ii)rest" style="width:90%;" value="Rest of The Authors" readonly></td>
					<td><input type="text" name="13per" style="width:90%;" value="13.5 Per Paper Equally Shared" readonly></td>
					<td><input type="text"  placeholder="Number of articles" name="Renumber" style="width:97%;" value="<?php echo @$this->data['Renumber']; ?>"></td>
					<td><input type="text"   placeholder="Score claimed" name="Rescore" style="width:97%; " value="<?php echo @$this->data['Rescore']; ?>"></td>
					<td><input type="text"   placeholder="Score varified" name="Revarified" style=" width:97%;" value="<?php echo @$this->data['Revarified']; ?>"></td>
								
				</tr>


				<tr>
					<td style="font-size:16px;background-color:#7e7e7e;color:white;font-weight:bold;text-align:center;">(iii)</td>	
					<td colspan=5 style="font-size:18px;background-color:#7e7e7e;color:white;font-weight:bold;">
						<input type="text" name="part-iii" style="width:23%;" value="Papers with Impact Factor 2 and 5" readonly>
					</td>
				</tr>
			
				<tr>
					<td></td>
					<td><input type="text" name="(iii)single" style="width:90%;" value="Single Author" readonly></td>
					<td><input type="text" name="40per" style="width:90%;" value="40 Per Paper" readonly></td>
					<td><input type="text"   placeholder="Number of articles" name="Impnumber" style="width:97%;" value="<?php echo @$this->data['Impnumber']; ?>"></td>
					<td><input type="text"   placeholder="Score claimed" name="Impscore" style="width:97%; " value="<?php echo @$this->data['Impscore']; ?>"></td>
					<td><input type="text"   placeholder="Score varified" name="Impvarified" style=" width:97%;" value="<?php echo @$this->data['Impvarified']; ?>"></td>
								
				</tr>

				<tr>
					<td></td>
					<td><input type="text" name="(iii)first" style="width:90%;" value="First /Corrseponding /Supervisor /Mentor" readonly></td>
					<td><input type="text" name="28per" style="width:90%;" value="28 Per Paper Equally Shared" readonly></td>
					<td><input type="text"   placeholder="Number of articles" name="Cornumber" style="width:97%;" value="<?php echo @$this->data['Cornumber']; ?>"></td>
					<td><input type="text"   placeholder="Score claimed" name="Corscore" style="width:97%; " value="<?php echo @$this->data['Corscore']; ?>"></td>
					<td><input type="text"   placeholder="Score varified" name="Corvarified" style=" width:97%;" value="<?php echo @$this->data['Corvarified']; ?>"></td>
								
				</tr>

				<tr>
					<td></td>
					<td><input type="text" name="(iii)rest" style="width:90%;" value="Rest of The Authors" readonly></td>
					<td><input type="text" name="12per" style="width:90%;" value="12 Per Paper Equally Shared" readonly></td>
					<td><input type="text"   placeholder="Number of articles" name="Renumber" style="width:97%;" value="<?php echo @$this->data['Renumber']; ?>"></td>
					<td><input type="text"   placeholder="Score claimed" name="Rescore" style="width:97%; " value="<?php echo @$this->data['Rescore']; ?>"></td>
					<td><input type="text"   placeholder="Score varified" name="Revarified" style=" width:97%;" value="<?php echo @$this->data['Revarified']; ?>"></td>
								
				</tr>
	

				<tr>
					<td style="font-size:16px;background-color:#7e7e7e;color:white;font-weight:bold;text-align:center;">(iv)</td>	
					<td colspan=5 style="font-size:18px;background-color:#7e7e7e;color:white;font-weight:bold;">
					<input type="text" name="part-iv" style="width:23%;" value="Papers with Impact Factor 1 and 2" readonly></td>
				</tr>
			
				<tr>
					<td></td>
					<td><input type="text" name="(iv)single" style="width:90%;" value="Single Author" readonly></td>
					<td><input type="text" name="35per" style="width:90%;" value="35 Per Paper" readonly></td>
					<td><input type="text"   placeholder="Number of articles" name="Impnumb" style="width:97%;" value="<?php echo @$this->data['Impnumb']; ?>"></td>
					<td><input type="text"   placeholder="Score claimed" name="Impsco" style="width:97%; " value="<?php echo @$this->data['Impsco']; ?>"></td>
					<td><input type="text"   placeholder="Score varified" name="Impvari" style=" width:97%;" value="<?php echo @$this->data['Impvari']; ?>"></td>
								
				</tr>

				<tr>
					<td></td>
					<td><input type="text" name="(iv)first" style="width:90%;" value="First /Corrseponding /Supervisor /Mentor" readonly></td>
					<td><input type="text" name="24per" style="width:90%;" value="24.5 Per Paper Equally Shared" readonly></td>
					<td><input type="text"   placeholder="Number of articles" name="Cornum" style="width:97%;" value="<?php echo @$this->data['Cornum']; ?>"></td>
					<td><input type="text"   placeholder="Score claimed" name="Corsc" style="width:97%; " value="<?php echo @$this->data['Corsc']; ?>"></td>
					<td><input type="text"   placeholder="Score varified" name="Corvari" style=" width:97%;" value="<?php echo @$this->data['Corvari']; ?>"></td>
								
				</tr>

				<tr>
					<td></td>
					<td><input type="text" name="(iv)rest" style="width:90%;" value="Rest of The Authors" readonly></td>
					<td><input type="text" name="(iv)10per" style="width:90%;" value="10.5 Per Paper Equally Shared" readonly></td>
					<td><input type="text"   placeholder="Number of articles" name="Renumb" style="width:97%;" value="<?php echo @$this->data['Renumb']; ?>"></td>
					<td><input type="text"   placeholder="Score claimed" name="Resc" style="width:97%; " value="<?php echo @$this->data['Resc']; ?>"></td>
					<td><input type="text"   placeholder="Score varified" name="Revari" style=" width:97%;" value="<?php echo @$this->data['Revari']; ?>"></td>
								
				</tr>


				<tr>
					<td style="font-size:16px;background-color:#7e7e7e;color:white;font-weight:bold;text-align:center;">(v)</td>	
					<td colspan=5 style="font-size:18px;background-color:#7e7e7e;color:white;font-weight:bold;">
					<input type="text" name="part-v" style="width:23%;" value="Papers with Impact Factor less than 1" readonly></td>
				</tr>
			
				<tr>
					<td></td>
					<td><input type="text" name="(v)single" style="width:90%;" value="Single Author" readonly></td>
					<td><input type="text" name="(v)30per" style="width:90%;" value="30 Per Paper" readonly></td>
					<td><input type="text"   placeholder="Number of articles" name="Impnum" style="width:97%;" value="<?php echo @$this->data['Impnum']; ?>"></td>
					<td><input type="text"   placeholder="Score claimed" name="Impsc" style="width:97%; " value="<?php echo @$this->data['Impsc']; ?>"></td>
					<td><input type="text"   placeholder="Score varified" name="Impvar" style=" width:97%;" value="<?php echo @$this->data['Impvar']; ?>"></td>
								
				</tr>

				<tr>
					<td></td>
					<td><input type="text" name="(v)first" style="width:90%;" value="First /Corrseponding /Supervisor /Mentor" readonly></td>
					<td><input type="text" name="(v)21per" style="width:90%;" value="21 Per Paper Equally Shared" readonly></td>
					<td><input type="text"   placeholder="Number of articles" name="Cornu" style="width:97%;" value="<?php echo @$this->data['Cornu']; ?>"></td>
					<td><input type="text"   placeholder="Score claimed" name="Cors" style="width:97%; " value="<?php echo @$this->data['Cors']; ?>"></td>
					<td><input type="text"   placeholder="Score varified" name="Corvar" style=" width:97%;" value="<?php echo @$this->data['Corvar']; ?>"></td>
								
				</tr>

				<tr>
					<td></td>
					<td><input type="text" name="(v)rest" style="width:90%;" value="Rest of The Authors" readonly></td>
					<td><input type="text" name="(v)9per" style="width:90%;" value="9 Per Paper Equally Shared" readonly></td>
					<td><input type="text"   placeholder="Number of articles" name="Renu" style="width:97%;" value="<?php echo @$this->data['Renumb']; ?>"></td>
					<td><input type="text"   placeholder="Score claimed" name="Resco" style="width:97%; " value="<?php echo @$this->data['Resco']; ?>"></td>
					<td><input type="text"   placeholder="Score varified" name="Revar" style=" width:97%;" value="<?php echo @$this->data['Revar']; ?>"></td>
								
				</tr>


				<tr>
					<td style="font-size:16px;background-color:#7e7e7e;color:white;font-weight:bold;text-align:center;">(vi)</td>	
					<td colspan=5 style="font-size:18px;background-color:#7e7e7e;color:white;font-weight:bold;">
					<input type="text" name="part-vi" style="width:23%;" value="Papers without Impact Factor" readonly></td>
				</tr>
			
				<tr>
					<td></td>
					<td><input type="text" name="(vi)single" style="width:90%;" value="Single Author" readonly></td>
					<td><input type="text" name="(vi)25per" style="width:90%;" value="25 Per Paper" readonly></td>
					<td><input type="text"   placeholder="Number of articles" name="Impnumer" style="width:97%;" value="<?php echo @$this->data['Impnumer']; ?>"></td>
					<td><input type="text"   placeholder="Score claimed" name="Impscr" style="width:97%; " value="<?php echo @$this->data['Impscr']; ?>"></td>
					<td><input type="text"   placeholder="Score varified" name="Impva" style=" width:97%;" value="<?php echo @$this->data['Impva']; ?>"></td>
								
				</tr>

				<tr>
					<td></td>
					<td><input type="text" name="(vi)first" style="width:90%;" value="First /Corrseponding /Supervisor /Mentor" readonly></td>
					<td><input type="text" name="(vi)17per" style="width:90%;" value="17.5 Per Paper Equally Shared" readonly></td>
					<td><input type="text"   placeholder="Number of articles" name="Cornum" style="width:97%;" value="<?php echo @$this->data['Cornum']; ?>"></td>
					<td><input type="text"   placeholder="Score claimed" name="Corsc" style="width:97%; " value="<?php echo @$this->data['Corsc']; ?>"></td>
					<td><input type="text"   placeholder="Score varified" name="Cscorvar" style=" width:97%;" value="<?php echo @$this->data['Cscorvar']; ?>"></td>
								
				</tr>

				<tr>
					<td></td>
					<td><input type="text" name="part-vi" style="width:90%;" value="Rest of The Authors" readonly></td>
					<td><input type="text" name="part-vi" style="width:90%;" value="7.5 Per Paper Equally Shared" readonly></td>
					<td><input type="text"   placeholder="Number of articles" name="Renum" style="width:97%;" value="<?php echo @$this->data['Renum']; ?>"></td>
					<td><input type="text"   placeholder="Score claimed" name="Res" style="width:97%; " value="<?php echo @$this->data['Res']; ?>"></td>
					<td><input type="text"   placeholder="Score varified" name="Reva" style=" width:97%;" value="<?php echo @$this->data['Reva']; ?>"></td>
								
				</tr>


			<tr>
					<td style="font-size:16px;background-color:#7e7e7e;color:white;font-weight:bold;text-align:center;">(vii)</td>	
					<td colspan=5 style="font-size:18px;background-color:#7e7e7e;color:white;font-weight:bold;">
					<input type="text" name="part-vii" style="width:23%;" value="Papers in other Reputed Journals" readonly></td>
				</tr>
			
				<tr>
					<td></td>
					<td><input type="text" name="(vii)single" style="width:90%;" value="Single Author" readonly></td>
					<td><input type="text" name="(vii)10per" style="width:90%;" value="10 Per Paper" readonly></td>
					<td><input type="text" placeholder="Number of articles" name="Impnu" style="width:97%;" value="<?php echo @$this->data['Impnu']; ?>"></td>
					<td><input type="text" placeholder="Score claimed" name="Imps" style="width:97%; " value="<?php echo @$this->data['Imps']; ?>"></td>
					<td><input type="text" placeholder="Score varified" name="Ipva" style=" width:97%;" value="<?php echo @$this->data['Ipva']; ?>"></td>
								
				</tr>

				<tr>
					<td></td>
					<td><input type="text" name="(vii)first" style="width:90%;" value="First /Corrseponding /Supervisor /Mentor" readonly></td>
					<td><input type="text" name="(vii)7per" style="width:90%;" value="7 Per Paper Equally Shared" readonly></td>
					<td><input type="text"   placeholder="Number of articles" name="Crnum" style="width:97%;" value="<?php echo @$this->data['Crnum']; ?>"></td>
					<td><input type="text"   placeholder="Score claimed" name="Crsc" style="width:97%; " value="<?php echo @$this->data['Crsc']; ?>"></td>
					<td><input type="text"   placeholder="Score varified" name="Corvar" style=" width:97%;" value="<?php echo @$this->data['Corvar']; ?>"></td>
								
				</tr>

				<tr>
					<td></td>
					<td><input type="text" name="(vii)rest" style="width:90%;" value="Rest of The Authors" readonly></td>
					<td><input type="text" name="(vii)3per" style="width:90%;" value="3 Per Paper Equally Shared" readonly></td>
					<td><input type="text"   placeholder="Number of articles" name="Renumb" style="width:97%;" value="<?php echo @$this->data['Renumb']; ?>"></td>
					<td><input type="text"   placeholder="Score claimed" name="Res" style="width:97%; " value="<?php echo @$this->data['Resr']; ?>"></td>
					<td><input type="text"   placeholder="Score varified" name="Rveri" style=" width:97%;" value="<?php echo @$this->data['Rveri']; ?>"></td>
								
				</tr>

				<tr>
					<td colspan=3></td>
					<td>Total API under category III(A) :</td>

					<td><input type="text"   placeholder="Total" name="Totalapi" style="width:97%; " value="<?php echo @$this->data['Totalapi']; ?>"></td>
					<td><input type="text"   placeholder="Total" name="Totalapicat" style=" width:97%;" value="<?php echo @$this->data['Totalapicat']; ?>"></td>
				</tr>
			


			</tbody>

		</table>
</br>
<?php $this->load->view('carrier/applicant_step4_B');?>
<?php $this->load->view('carrier/applicant_step4_C');?>

<table style="width:80%;text-align:justify;border:1px solid black;margin-top:30px;">
			
			<thead>
			    <th colspan=8 style="margin-left:120px;background-color:#7e7e7e;color:white;font-size:22px;text-align:justify;">
				<input type="text" name="part-d" style="width:23%;" value="PART-D : Research Projects" readonly></th>
			</thead>
		
			<thead style="font-size:20px;">
				<th>Sr. No.</th><th>Activities</th><th>Maximum Score</th><th>Number</th><th>Score Reported</th><th>Score Varified</th>
			</thead>

			<tbody>
				<tr>
					<td style="font-size:16px;background-color:#7e7e7e;color:white;font-weight:bold;text-align:center;">(i)</td>	
					<td colspan=5 style="font-size:18px;background-color:#7e7e7e;color:white;font-weight:bold;">
					<input type="text" name="part-d(i)" style="width:23%;" value="Research Guidence" readonly></td>
				</tr>
			
				<tr>
					<td></td>
					<td>(a)<input type="text" name="part-dmphill" style="width:90%;" value="No. of M.Phill candidates awarded" readonly> </td>
					<td><input type="text" name="part-d5per" style="width:60%;" value="5 Per Candidate" readonly></td>
					<td><input type="text"   placeholder="Number of articles" name="Mphilnumb" style="width:97%;" value="<?php echo @$this->data['Mphilnumb']; ?>"></td>
					<td><input type="text"   placeholder="Score reported" name="Mphilscore" style="width:97%; " value="<?php echo @$this->data['Mphilscore']; ?>"></td>
					<td><input type="text"   placeholder="Score varified" name="Mphilvari" style=" width:97%;" value="<?php echo @$this->data['Mphilvari']; ?>"></td>
								
				</tr>


			
				<tr>
					<td></td>
					<td>(b)<input type="text" name="part-dphd" style="width:90%;" value="No. of P.hd candidates awarded" readonly></td>
					<td><input type="text" name="part-d15per" style="width:60%;" value="15 Per Candidate" readonly></td>
					<td><input type="text"   placeholder="Number of articles" name="phdnumb" style="width:97%;" value="<?php echo @$this->data['phdnumb']; ?>"></td>
					<td><input type="text"   placeholder="Score reported" name="phdscore" style="width:97%; " value="<?php echo @$this->data['phdscore']; ?>"></td>
					<td><input type="text"   placeholder="Score varified" name="phdvari" style=" width:97%;" value="<?php echo @$this->data['phdvari']; ?>"></td>
								
				</tr>

				<tr>
					<td></td>
					<td>(c)<input type="text" name="part-dphdth" style="width:90%;" value=" No. of P.hd Thesis Submitted but yet not" readonly></td>
					<td><input type="text" name="part-d10per" style="width:60%;" value="10 Per Candidate" readonly></td>
					<td><input type="text"   placeholder="Number of articles" name="Tnumber" style="width:97%;" value="<?php echo @$this->data['Tnumber']; ?>"></td>
					<td><input type="text"   placeholder="Score reported" name="Tscore" style="width:97%; " value="<?php echo @$this->data['Tscore']; ?>"></td>
					<td><input type="text"   placeholder="Score varified" name="Tvarified" style=" width:97%;" value="<?php echo @$this->data['Tvarified']; ?>"></td>
								
				</tr>


			<tr>
					<td colspan=3></td>
					<td>Total API under category III(D) :</td>

					<td><input type="text"   placeholder="Total" name="Totalapi" style="width:97%; " value="<?php echo @$this->data['Totalapi']; ?>"></td>
					<td><input type="text"   placeholder="Total" name="Totalapicat" style=" width:97%;" value="<?php echo @$this->data['Totalapicat']; ?>"></td>
				</tr>
		<tbody>
		</table>

<table style="width:80%;text-align:justify;border:1px solid black;margin-top:30px;">
			
			<thead>
			    <th colspan=8 style="margin-left:120px;background-color:#7e7e7e;color:white;font-size:22px;text-align:justify;">
				<input type="text" name="part-e" style="width:23%;" value="PART-E : Fellowship, Awards, and Invited lectures delivered in Conferences / Seminars" readonly></th>
			</thead>
		
			<thead style="font-size:20px;">
				<th>Sr. No.</th><th>Activities</th><th>Maximum Score</th><th>Number</th><th>Score Reported</th><th>Score Varified</th>
			</thead>

			<tbody>
				<tr>
					<td style="font-size:16px;background-color:#7e7e7e;color:white;font-weight:bold;text-align:center;">(i)</td>	
					<td colspan=5 style="font-size:18px;background-color:#7e7e7e;color:white;font-weight:bold;">
					<input type="text" name="part-e(i)" style="width:23%;" value="Fellowship and Awards" readonly></td>
				</tr>
			
				<tr>
					<td></td>
					<td>(a) <input type="text" name="(e)award" style="width:85%;" value="International Award / Fellowship" readonly></td>
					<td><input type="text" name="(e)15per" style="width:50%;" value="15 Per Award" readonly></td>
					<td><input type="text"   placeholder="Number of articles" name="Iwnumb" style="width:97%;" value="<?php echo @$this->data['Iwnumb']; ?>"></td>
					<td><input type="text"   placeholder="Score reported" name="Iwscore" style="width:97%; " value="<?php echo @$this->data['Iwscore']; ?>"></td>
					<td><input type="text"   placeholder="Score varified" name="Iwvari" style=" width:97%;" value="<?php echo @$this->data['Iwvari']; ?>"></td>
								
				</tr>
			
				<tr>
					<td></td>
					<td>(b) <input type="text" name="(e)interna" style="width:85%;" value="International Award / Fellowship" readonly></td>
					<td><input type="text" name="(e)10per" style="width:50%;" value="10 Per Award" readonly></td>
					<td><input type="text"   placeholder="Number of articles" name="Iwanumb" style="width:97%;" value="<?php echo @$this->data['Iwanumb']; ?>"></td>
					<td><input type="text"   placeholder="Score reported" name="Iwascore" style="width:97%; " value="<?php echo @$this->data['Iwascore']; ?>"></td>
					<td><input type="text"   placeholder="Score varified" name="Iwavari" style=" width:97%;" value="<?php echo @$this->data['Iwavari']; ?>"></td>
								
				</tr>

				<tr>
					<td></td>
					<td>(c) <input type="text" name="(e)state" style="width:85%;" value="State / University level" readonly></td>
					<td><input type="text" name="(e)5per" style="width:50%;" value="5 Per Award" readonly></td>
					<td><input type="text"   placeholder="Number of articles" name="Stnumber" style="width:97%;" value="<?php echo @$this->data['Stnumber']; ?>"></td>
					<td><input type="text"   placeholder="Score reported" name="Stscore" style="width:97%; " value="<?php echo @$this->data['Stscore']; ?>"></td>
					<td><input type="text"   placeholder="Score varified" name="Stvarified" style=" width:97%;" value="<?php echo @$this->data['Stvarified']; ?>"></td>
								
				</tr>


			<tr>
					<td style="font-size:16px;background-color:#7e7e7e;color:white;font-weight:bold;text-align:center;">(ii)</td>	
					<td colspan=5 style="font-size:18px;background-color:#7e7e7e;color:white;font-weight:bold;">
					<input type="text" name="(e)invited" style="width:23%;" value="Invited lectures / papers delivered in conferences / seminars" readonly></td>
				</tr>
			
				<tr>
					<td></td>
					<td>(a)<input type="text" name="(e)international" style="width:85%;" value="International" readonly> </td>
					<td><input type="text" name="(e)7lecture" style="width:85%;" value="7 Lecture / 5 Presentation" readonly></td>
					<td><input type="text"   placeholder="Number of articles" name="Inumb" style="width:97%;" value="<?php echo @$this->data['Inumb']; ?>"></td>
					<td><input type="text"   placeholder="Score reported" name="Iscore" style="width:97%; " value="<?php echo @$this->data['Iscore']; ?>"></td>
					<td><input type="text"   placeholder="Score varified" name="Ivari" style=" width:97%;" value="<?php echo @$this->data['Ivari']; ?>"></td>
								
				</tr>
			
				<tr>
					<td></td>
					<td>(b) <input type="text" name="(e)nation" style="width:85%;" value="National" readonly></td>
					<td><input type="text" name="(e)5lecture" style="width:85%;" value="5 Lecture / 3 Presentation" readonly></td>
					<td><input type="text"   placeholder="Number of articles" name="Nanumb" style="width:97%;" value="<?php echo @$this->data['Nanumb']; ?>"></td>
					<td><input type="text"   placeholder="Score reported" name="Nascore" style="width:97%; " value="<?php echo @$this->data['Nascore']; ?>"></td>
					<td><input type="text"   placeholder="Score varified" name="Navari" style=" width:97%;" value="<?php echo @$this->data['Navari']; ?>"></td>
								
				</tr>

				<tr>
					<td></td>
					<td>(c) <input type="text" name="(e)univ" style="width:85%;" value="State / University level" readonly></td>
					<td><input type="text" name="(e)3lecture" style="width:85%;" value="3 Lecture / 2 Presentation" readonly></td>
					<td><input type="text"   placeholder="Number of articles" name="Sunumber" style="width:97%;" value="<?php echo @$this->data['Sunumber']; ?>"></td>
					<td><input type="text"   placeholder="Score reported" name="Suscore" style="width:97%; " value="<?php echo @$this->data['Suscore']; ?>"></td>
					<td><input type="text"   placeholder="Score varified" name="Suvarified" style=" width:97%;" value="<?php echo @$this->data['Suvarified']; ?>"></td>
								
				</tr>


			<tr>
					<td colspan=3></td>
					<td>Total API under category III(E) :</td>

					<td><input type="text"   placeholder="Total" name="Totalapi" style="width:97%; " value="<?php echo @$this->data['Totalapi']; ?>"></td>
					<td><input type="text"   placeholder="Total" name="Totalapicat" style=" width:97%;" value="<?php echo @$this->data['Totalapicat']; ?>"></td>
				</tr>
		<tbody>
		</table>

<table style="width:80%;text-align:justify;border:1px solid black;margin-top:30px;">
			
			<thead>
			    <th colspan=8 style="margin-left:120px;background-color:#7e7e7e;color:white;font-size:22px;text-align:justify;">
				<input type="text" name="part-f" style="width:32%;" value="PART-F : Development of E-learning delivery process / material" readonly></th>
			</thead>
		
			<thead style="font-size:20px;">
				<th>Sr. No.</th><th>Activities</th><th>Maximum Score</th><th>Number</th><th>Score Reported</th><th>Score Varified</th>
			</thead>

			<tbody>
				<tr>
					<td style="font-size:16px;background-color:#7e7e7e;color:white;font-weight:bold;text-align:center;">(i)</td>	
					<td colspan=5 style="font-size:18px;background-color:#7e7e7e;color:white;font-weight:bold;">
					<input type="text" name="(f)research" style="width:18%;" value="Research Guidence" readonly></td>
				</tr>
			
				<tr>
					<td></td>
					<td><input type="text" name="(f)research" style="width:50%;" value="Modules" readonly></td>
					<td><input type="text" name="(f)10per" style="width:50%;" value="10 Per Module" readonly></td>
					<td><input type="text"   placeholder="Number of articles" name="Mphilnumb" style="width:97%;" value="<?php echo @$this->data['Mphilnumb']; ?>"></td>
					<td><input type="text"   placeholder="Score reported" name="Mphilscore" style="width:97%; " value="<?php echo @$this->data['Mphilscore']; ?>"></td>
					<td><input type="text"   placeholder="Score varified" name="Mphilvari" style=" width:97%;" value="<?php echo @$this->data['Mphilvari']; ?>"></td>
								
				</tr>
			<tr>
					<td colspan=3></td>
					<td>Total API under category III(D) :</td>

					<td><input type="text"   placeholder="Total" name="Totalapi" style="width:97%; " value="<?php echo @$this->data['Totalapi']; ?>"></td>
					<td><input type="text"   placeholder="Total" name="Totalapicat" style=" width:97%;" value="<?php echo @$this->data['Totalapicat']; ?>"></td>
				</tr>
		<tbody>
		</table>

<table style="width:80%;text-align:justify;border:1px solid black;margin-top:30px;">
			
			<thead>
			    <th colspan=8 style="margin-left:120px;background-color:#7e7e7e;color:white;font-size:22px;text-align:justify;">Total API</th>
			</thead>
		
			<thead style="font-size:20px;">
				<th>Category</th><th>III-(A)</th><th>III-(B)</th><th>III-(C)</th><th>III-(D)</th><th>III-(E)</th><th>III-(F)</th><th>Total API Acore</th>
			</thead>

			<tbody>
				<tr>
					<td width=120>Score Claimed</td>
					<td><input type="text" placeholder="III-(A)" name="API-A" style="width:97%;" value="<?php echo @$this->data['API-A']; ?>"></td>
					<td><input type="text" placeholder="III-(A)" name="API-B" style="width:97%;" value="<?php echo @$this->data['API-B']; ?>"></td>
					<td><input type="text" placeholder="III-(A)" name="API-C" style="width:97%;" value="<?php echo @$this->data['API-C']; ?>"></td>
					<td><input type="text" placeholder="III-(A)" name="API-D" style="width:97%;" value="<?php echo @$this->data['API-D']; ?>"></td>
					<td><input type="text" placeholder="III-(A)" name="API-E" style="width:97%;" value="<?php echo @$this->data['API-E']; ?>"></td>
					<td><input type="text" placeholder="III-(A)" name="API-F" style="width:97%;" value="<?php echo @$this->data['API-F']; ?>"></td>
					<td><input type="text" placeholder="Total API Acore" name="Atotal" style="width:97%;" value="<?php echo @$this->data['Atotal']; ?>"></td>
				</tr>
			<tr>
					<td>Score Verified</td>
					<td><input type="text" placeholder="III-(A)" name="API-A" style="width:97%;" value="<?php echo @$this->data['API-A']; ?>"></td>
					<td><input type="text" placeholder="III-(A)" name="API-B" style="width:97%;" value="<?php echo @$this->data['API-B']; ?>"></td>
					<td><input type="text" placeholder="III-(A)" name="API-C" style="width:97%;" value="<?php echo @$this->data['API-C']; ?>"></td>
					<td><input type="text" placeholder="III-(A)" name="API-D" style="width:97%;" value="<?php echo @$this->data['API-D']; ?>"></td>
					<td><input type="text" placeholder="III-(A)" name="API-E" style="width:97%;" value="<?php echo @$this->data['API-E']; ?>"></td>
					<td><input type="text" placeholder="III-(A)" name="API-F" style="width:97%;" value="<?php echo @$this->data['API-F']; ?>"></td>
					<td><input type="text" placeholder="Total API Acore" name="Atotal" style="width:97%;" value="<?php echo @$this->data['Atotal']; ?>"></td>
				</tr>
		<tbody>
		</table>

<?php $this->load->view('carrier/applicant_step4_note');?>
	<table style="width:20%;margin-top:20px;">
			<tr>
				<td><input type="submit" name="apisubmit" value="Submit" style="width:100%;height:35px;font-size:18px;"></td>
				<td><input type="reset" name="reset" value="Reset" style="width:100%;height:35px;font-size:18px;"></td>
			</tr>
		</table>

	</table>	
</center>

<?php $this->load->view('template/footer'); ?>
</body>
</html>
