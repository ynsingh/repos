<!-------------------------------------------------------
    -- @name stu_hallticketdw.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
--------------------------------------------------------->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?>
<!DOCTYPE html>
<html moznomarginboxes mozdisallowselectionprint>
<head>
<title>IGNTU - Download</title>
    <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/bootstrap.min.css">
    <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
    <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
    <link rel="stylesheet" type="text/css" media="all" href="<?php echo base_url(); ?>assets/css/Studentsteps.css" />	
    <link rel="stylesheet" type="text/css" media="print" href="<?php echo base_url(); ?>assets/css/studentStepmedia.css" />
 <link rel="stylesheet" type="text/css" media="all" href="<?php echo base_url(); ?>assets/css/style.css" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style>
tr td{font-size:22px;font-family: Times New Roman, Times, serif;padding:10px 10px 10px 10px;}

</style>
<script>
function myFunction() {
    window.print();
}

</script>
</head>
<body style="" >
		<div id="logo2">
			<img src="<?php echo base_url(); ?>uploads/logo/logo2.jpg" alt="logo">
		</div> 				
<?php
    //$this->load->view('template/header'); ?></br>

		
<center>
	<div class="panel panel-primary" style="width:80%;">
      		<div class="panel-heading"><h4>Hall Ticket For All India Enterance Examination - <?php echo $acadyear;?></h4></div>
      		<div class="panel-body">
	<table border=0 style="width:100%;"  align=center>
		
	<tr>
                <td align=center style="border:1px solid black;">Program : <?php echo $progname;?></td>
		<td align=center style="border:1px solid black;">Program Code :<?php echo $prgid; ?></td>
         </tr>

	<tr>
	<td valign=top>
		<table border=0 style="width:100%;">
			<tr><td width=170 style="border:1px solid black;"><b>Candidate Name : </b></td><td style="border:1px solid black;"><?php echo $sname; ?></td></tr>
			<tr><td style="border:1px solid black;"><b>Father Name :</b></td><td style="border:1px solid black;"><?php echo $faname; ?></td></tr>
			<tr><td style="border:1px solid black;"><b> Mother Name : </b></td><td style="border:1px solid black;"><?php echo $moname; ?></br></br></td></tr>
			<tr><td style="border:1px solid black;"><b> Address : </b></td><td style="border:1px solid black;"><?php echo $padd.','.$pcity.','.$pstate.','.$pcountry; ?></td></tr>	
										
		</table>
		<table border=0  style="width:100%;">
			<tr><td width=170 style="border:1px solid black;"><b>Exam Date & Time :</b></td>
			<td style="border:1px solid black;" valign=top><?php echo $exmdate;?></td>
			</tr>
			<tr>
				<td style="border:1px solid black;"><b>Venue :</b></td>
				<td style="border:1px solid black;"><?php echo $venue;?></td>	
			</tr>
		</table>

		<table border=0  style="width:100%;">
			<tr><td width=170 style="border:1px solid black;"><b>Category :</b></td>
			<td style="border:1px solid black;"><?php  echo $caste;?></td>
			</tr>
			<tr>
				<td style="border:1px solid black;"><b>Gender :</b></td>
				<td style="border:1px solid black;"><?php echo $gender; ?></td>	
			</tr>
		</table>
	</td>
	<td width=150 valign=top>
		<table style="" border=0 align=center>
			<tr><td align=center style="border:1px solid black;"><b>Hall Ticket Number :</b></br><?php echo $rollno;?></td></tr>
			 <tr><td align=center style="border:1px solid black;"><img src=<?php echo base_url('uploads/SLCMS/enterence/'.$asmid.'/'.$photo); ?> style="height:175px;width:170px; "></td></tr>
		 	<tr><td align=center style="border:1px solid black;"><img src=<?php echo base_url('uploads/SLCMS/enterence/'.$asmid.'/'.$signature); ?> style="height:50px; width:170px; "></td></tr>
		</table>
	</td>
	</tr>

        </table>
		<!---<table style="width:100%;" border=0  align=center>
			<tr><td align=center style="border:1px solid black;">Exam Date & Time</td>
			<td align=center style="border:1px solid black;">Venue</td></tr>
			<tr>
				<td align=center style="border:1px solid black;"><?php echo $exmdate;?></td>
				<td align=center style="border:1px solid black;" ><?php echo $venue;?></td>	
			</tr>
		</table>

		<table style="width:100%;" border=0  align=center>
			<tr><td align=center style="border:1px solid black;">Category</td>
			<td align=center style="border:1px solid black;">Gender</td></tr>
			<tr>
				<td align=center style="border:1px solid black;"><?php  echo $caste;?></td>
				<td align=center style="border:1px solid black;"><?php echo $gender; ?></td>	
			</tr>
		</table>-->

		<table style="width:98%;" border=0  align=center> 
			<tr>
				<td align=center><br><br><br>
				Signature of Candidate</br>(To be signed in the presence of the invigilator)</td>
				<td align=right><img src="<?php echo base_url('uploads/SLCMS/enterenceadmin_student/coesign.png');?>" style="height:50px; width:170px; ">
<br>Controller of Examinations</td>
			</tr>
		</table>
		<?php //} ?>

		<center>
	<div  style="width:100%;margin-top:10px;" >
	<span style="font-size:20px;font-family: Times New Roman, Times, serif"><b>ATTENTION:</b></span></br>
            <p style="text-align:left;font-weight:normal;font-size:18px;text-align:justify;font-family: Times New Roman, Times, serif"><b>Note:</b> Candidates who have applied online are hereby asked to bring the hard copy of the application form along with necessary certificates & Challan /DD and submit the same to the invigilator at the examination hall failing which they will not be allowed for the exam.</p></br>
                   
             <span style="margin-top:-12px;font-size:20px;font-family: Times New Roman, Times, serif"><b>IMPORTANT INSTRUCTIONS TO THE CANDIDATES</b></span>
                 <ol style="text-align:justify;font-weight:normal;font-size:16px;font-family: Times New Roman, Times, serif" >
                      <li>
                           The candidates should reach the examination hall 30 minutes prior to commencement of the examination for submitting 					necessary enclosures along with the application form.
                       	 </li>
                        
                        <li>
                           	No candidate will be allowed to enter the examination hall after 30 minutes of commencement of the examination.
                        </li>
			 <li>
                          	 No candidate will be allowed to leave the hall 30 minutes prior to completion of the exam.
                        </li>
			<li>
                           The candidate shall carry into the examination hall only (i) Blue/Black BallPoint Pen (ii) Hall Ticket along with Valid Photo ID proof.
                       </li>
                        <li>
       				Carrying of Calculators, Mathematical/Log Tables, Pagers, Cell Phones, any other Electronic Gadgets and loose sheets of papers into the examination hall is strictly prohibited. However, candidates appearing for MSc and PhD in Chemistry and Biology, Betony,Zoology Bio­technology, Environmental Sciences are allowed to use scientific calculators only.
       
                        </li>
                       <li>
                           Adoption of any kind of unfair means and any act of impersonation at the time of test will render the applicant liable for invalidation of his/her OMR answer sheet. Further he/she will forfeit the claim of appearing for the test and will make him/her liable for criminal
action.
                        </li>
                       	<li>
                           	Issue of Hall Ticket and appearance at the test do not automatically entitle the candidate for admission to any program.
                       	</li>
                        <li>
                            	Hall Ticket must be preserved till the time of admission.
                        </li>
                               
                    </ol>
                			
</center>


		</div>
	</div>	
	<table><tr><td>

<a href="<?php echo site_url('enterence/stu_hallticketdwpdf'); ?>" style="text-decoration:none;color:black;" id="b1"><input type="submit" value="Save" title="Click for save"  id="b1"></a>
</td>
<td>

 <input type="submit" value="Print" onclick="myFunction()" title="Click for print" id="b1">

</td>
<td>
<form action="<?php echo site_url('enterence/home'); ?>" method="POST">
 <input type="submit" name="submit" value="Home" title="Click for home" id="b1">
</form>
</td>
</tr></table>				
  </center>
</br>
					
					
<div id="b1">
<?php
     $this->load->view('template/footer'); ?>
</body>
</div>
</html>

