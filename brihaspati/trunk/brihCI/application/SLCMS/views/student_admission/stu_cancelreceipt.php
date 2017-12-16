<!-------------------------------------------------------
    -- @name stu_cancelreceipt.php --	
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
 <link rel="stylesheet" type="text/css" media="all" href="<?php echo base_url(); ?>assets/css/message.css" />
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
		</div> 				</br>
 <?php echo validation_errors('<div class="isa_warning">','</div>');?>
        <?php echo form_error('<div class="">','</div>');?>
        <?php if(isset($_SESSION['success'])){?>
        <div class="isa_success"><?php echo $_SESSION['success'];?></div>
        <?php
    	 };
       	?>
		
<center>
	<div class="panel panel-primary" style="width:65%;">
      		<div class="panel-heading"><h4>Student Admission Cancellation Receipt</h4></div>
      		<div class="panel-body">
	<table border=0 style="width:100%;" >
		
	<tr>
                <td align=center style="border:1px solid black;">Hall Ticket Number : <?php echo $hallticketno;?></td>
		<td align=center style="border:1px solid black;">Program Code :<?php echo date("Y-m-d H:i:s"); ?></td>
         </tr>

	<tr>
	<td valign=top colspan=2>
		<table border=0 style="width:50%;" align=center>
			<tr><td width=170 style="border:1px solid black;"><b>Candidate Name : </b></td><td style="border:1px solid black;"><?php echo $stu_name; ?></td></tr>
			<tr><td style="border:1px solid black;"><b>Father Name :</b></td><td style="border:1px solid black;"><?php echo $stu_fathername; ?></td></tr>
			<tr><td style="border:1px solid black;"><b>Student Prgrame : </b></td><td style="border:1px solid black;"><?php echo $stu_progname; ?></br></br></td></tr>
			<tr><td style="border:1px solid black;"><b>Student Paid Fees : </b></td><td style="border:1px solid black;"><?php echo $stu_fees; ?></td></tr>	
										
		</table>
		

	</td>
	
	</tr>

        </table>
<table style="width:100%;"><tr><td>
<span style="text-align:left;font-size:18px;color:#4c8cc3;">Your Admission Is Cancelled.</span>		
</td></tr></table>		
	<table><tr><td>

<a href="<?php echo site_url('admissionstu/stu_cancelreceiptpdfdw/');echo $smid;?>" style="text-decoration:none;color:black;" id="b1"><input type="submit" value="Save" title="Click for save"  id="b1"></a>
</td>
<td>

 <input type="submit" value="Print" onclick="myFunction()" title="Click for print" id="b1">

</td>
<td>
<form action="<?php echo site_url('admissionstu/stu_admissioncancel'); ?>" method="POST">
 <input type="submit" name="submit" value="Back" title="Click for home" id="b1">
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

