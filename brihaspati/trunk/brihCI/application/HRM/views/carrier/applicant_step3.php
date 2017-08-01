<!-------------------------------------------------------
    -- @name applicant_step3.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
--------------------------------------------------------->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>IGNTU:Upload File</title>
	<link rel="shortcut icon" href="<?php echo base_url('assets/images'); ?>/index.jpg">
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/message.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/studentNavbar.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/style.css">
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
</br></br>
	<form action="<?php ?>" method="POST">
<center>		
		<table style="border:1px solid black;">
			<tr>
				<td>
				<label>Enclosure 1</label></br>				
				<input type='file' name='userfile' /></td>
				<td>
				<label>Enclosure 2</label></br>				
				<input type='file' name='userfile' /></td>
				<td>
				<label>Enclosure 3</label></br>				
				<input type='file' name='userfile' /></td>
				<td>
				<label>Enclosure 4</label></br>				
				<input type='file' name='userfile' /></td>
		
			</tr>
<tr height=20></tr>
			<tr>
				<td>
				<label>Enclosure 5</label></br>				
				<input type='file' name='userfile' /></td>
				<td>
				<label>Enclosure 6</label></br>				
				<input type='file' name='userfile' /></td>
				<td>
				<label>Enclosure 7</label></br>				
				<input type='file' name='userfile' /></td>
				<td>
				<label>Enclosure 8</label></br>				
				<input type='file' name='userfile' /></td>
		
			</tr>
<tr height=20></tr>
			<tr>
				<td>
				<label>Enclosure 9</label></br>				
				<input type='file' name='userfile' /></td>
				<td>
				<label>Enclosure 10</label></br>				
				<input type='file' name='userfile' /></td>
				<td>
				<label>Enclosure 11</label></br>				
				<input type='file' name='userfile' /></td>
				<td>
				<label>Enclosure 12</label></br>				
				<input type='file' name='userfile' /></td>
		
			</tr>
<tr height=20></tr>
			<tr>
				<td>
				<label>Enclosure 13</label></br>				
				<input type='file' name='userfile' /></td>
				<td>
				<label>Enclosure 14</label></br>				
				<input type='file' name='userfile' /></td>
				<td>
				<label>Enclosure 15</label></br>				
				<input type='file' name='userfile' /></td>
				<td>
				<label>Upload Declaration</label></br>				
				<input type='file' name='userfile' /></td>
		
			</tr>
<tr height=20></tr>
			<tr>
				<td>
				<label>Upload NOC</label></br>				
				<input type='file' name='userfile'/></td>

				<td>
				<label>Upload Photo</label></br>				
				<input type='file' name='userfile'/></td>

				<td>
				<label>Upload Signature</label></br>				
				<input type='file' name='userfile'/></td>
		
			</tr>
		</table>
		<table style="width:10%;">
			<tr>
				<td><input type="submit" name="upload" value="Upload file" style="width:100%;height:35px;font-size:18px;"></td>
				<!---<td><input type="reset" name="reset" value="Reset" style="width:100%;height:35px;font-size:18px;"></td>--->
			</tr>
		</table>
	
	</form>
</center>
<?php $this->load->view('template/footer'); ?>
</body>
</html>
