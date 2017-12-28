<!-------------------------------------------------------
    -- @name hallticketpdf.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
--------------------------------------------------------->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?>
<!DOCTYPE html>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body style="">
<?php //$this->load->view('template/watermark.php');?>				
<img src="uploads/logo/logo2.jpg" alt="logo" style="width:100%;height:70px;">
<center><h3>Hall Ticket For All India Entrance Examination - <?php echo $acadyear;?></h3></center>
					
<center>
	<table border=0 style="width:98%;" align=center>
	 <tr>
                  <td align=center style="border:1px solid black;"><b>Program : </b><?php echo $progname;?></td>
		<td align=center style="border:1px solid black;"><b>Program Code :</b><?php echo $prgid; ?></td>
         </tr>

	<tr>
	<td valign=top>
		<table border=0 style="width:100%;">
			<tr><td width=140 style="border:1px solid black;"><b>Candidate Name : </b></td><td style="border:1px solid black;"><?php echo $sname; ?></td></tr>
			<tr><td style="border:1px solid black;"><b>Father Name :</b></td><td style="border:1px solid black;"><?php echo $faname; ?></td></tr>
			<tr><td style="border:1px solid black;"><b> Mother Name : </b></td><td style="border:1px solid black;"><?php echo $moname; ?></br></br></td></tr>
			<tr><td style="border:1px solid black;"><b> Address : </b></td><td style="border:1px solid black;"><?php echo $padd.','.$pcity.','.$pstate.','.$pcountry; ?></td></tr>	
										
		</table>
		<table border=0  style="width:100%;">
			<tr><td width=140 style="border:1px solid black;"><b>Exam Date & Time :</b></td>
			<td style="border:1px solid black;" valign=top><?php echo $exmdate;?></td>
			</tr>
			<tr>
				<td style="border:1px solid black;"><b>Venue :</b></td>
				<td style="border:1px solid black;"><?php echo $venue;?></td>	
			</tr>
		</table>

		<table border=0  style="width:100%;">
			<tr><td width=140 style="border:1px solid black;"><b>Category :</b></td>
			<td style="border:1px solid black;"><?php  echo $caste;?></td>
			</tr>
			<tr>
				<td style="border:1px solid black;"><b>Gender :</b></td>
				<td style="border:1px solid black;"><?php echo $gender; ?></td>	
			</tr>
		</table>
	</td>
	<td width=150>
		<table style="" border=0 align=center>
			<tr><td align=center style="border:1px solid black;"><b>Hall Ticket Number :</b></br><?php echo $rollno;?></td></tr>
			 <tr><td align=center style="border:1px solid black;"><img src=<?php echo base_url('uploads/SLCMS/enterence/'.$asmid.'/'.$photo); ?> style="height:150px;width:170px; "></td></tr>
		 	<tr><td align=center style="border:1px solid black;"><img src=<?php echo base_url('uploads/SLCMS/enterence/'.$asmid.'/'.$signature); ?> style="height:50px; width:170px; "></td></tr>
		</table>
	</td>
	</tr>

        </table>
		<!---<table style="width:98%;" border=0  align=center>
			<tr><td align=center style="border:1px solid black;">Exam Date & Time</td>
			<td align=center style="border:1px solid black;">Venue</td></tr>
			<tr>
				<td align=center style="border:1px solid black;"><?php echo $exmdate;?></td>
				<td style="border:1px solid black;"><?php echo $venue;?></td>	
			</tr>
		</table>

		<table style="width:98%;" border=0  align=center>
			<tr><td align=center style="border:1px solid black;">Category</td>
			<td align=center style="border:1px solid black;">Gender</td></tr>
			<tr>
				<td align=center style="border:1px solid black;"><?php  echo $caste;?></td>
				<td align=center style="border:1px solid black;"><?php echo $gender; ?></td>	
			</tr>
		</table>--->

		<table style="width:98%;" border=0  align=center> 
			<tr>
				<td align=center><br><br><br>
				Signature of Candidate</br>(To be signed in the presence of the invigilator)</td>
				<td align=right><img src="<?php echo base_url('uploads/SLCMS/enterenceadmin_student/coesign.png');?>" style="height:50px; width:170px; ">
<br>Controller of Examinations</td>
			</tr>
		</table>
						
  </center>
</br>
					
					
<center>
	<div  style="width:98%;margin-top:10px;" >
	<span style=""><b>ATTENTION:</b></span>
            <p style="text-align:left;font-weight:normal;margin-top:-6px;">Note: Candidates who have applied online are hereby asked to bring the hard copy of the application form along with necessary certificates & Challan /DD and submit the same to the invigilator at the examination hall failing which they will not be allowed for the exam.</p>
                   
             <span style="margin-top:-12px;"><b>IMPORTANT INSTRUCTIONS TO THE CANDIDATES</b></span>
                 <ol style="text-align:justify;font-weight:normal;margin-top:-6px;" >
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
</body>

</html>

