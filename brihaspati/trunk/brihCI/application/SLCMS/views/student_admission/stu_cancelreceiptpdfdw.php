<!-------------------------------------------------------
    -- @name stu_transferreceiptpdfdw.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
--------------------------------------------------------->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?>
<!DOCTYPE html>
<html>
<head>
<title>IGNTU - Download</title>
   


</head>
<body style="" >
		<img src="uploads/logo/logo2.jpg" alt="logo" style="width:100%;height:70px;">		


	
          		
<center>
	<div class="panel panel-primary" style="width:80%;">
      		<div class="panel-heading"><h4>Student Admission Cancel Receipt</h4></div>
      		<div class="panel-body">
	<table border=0 style="width:100%;" >
		
	<tr>
                <td align=center style="border:1px solid black;"><b>Hall Ticket Number : </b><?php echo $hallticketno;?></td>
		<td align=center style="border:1px solid black;"><b>Program Code :</b><?php echo date("Y-m-d H:i:s"); ?></td>
         </tr>

	<tr>
	<td valign=top colspan=2>
		<table border=0 style="width:60%;">
			<tr><td style="border:1px solid black;"><b>Candidate Name : </b></td><td style="border:1px solid black;"><?php echo $stu_name; ?></td></tr>
			<tr><td style="border:1px solid black;"><b>Father Name :</b></td><td style="border:1px solid black;"><?php echo $stu_fathername; ?></td></tr>
			<tr><td style="border:1px solid black;"><b>Student Prgrame : </b></td><td style="border:1px solid black;"><?php echo $stu_progname; ?></br></br></td></tr>
			<tr><td style="border:1px solid black;"><b>Student Paid Fees : </b></td><td style="border:1px solid black;"><?php echo $stu_fees; ?></td></tr>	
			<tr><td colspan=4>
		<table style="width:100%;"><tr><td>
<span style="text-align:left;font-size:18px;color:#4c8cc3;">Your Admission Is Cancelled.</span>		
</td></tr></table>	
</td></tr>							
		</table>

	</td>
	
	</tr>

        </table>
				
  </center>
				

</body>

</html>

