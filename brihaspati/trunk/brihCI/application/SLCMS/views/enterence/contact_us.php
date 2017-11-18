<!-------------------------------------------------------
    -- @name contact_us.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
--------------------------------------------------------->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>IGNTU:Contact Us</title>
	<link rel="shortcut icon" href="<?php echo base_url('assets/images'); ?>/index.jpg">
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/message.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/studentNavbar.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
	
<style>
.BG tr th{font-size:25px;text-align:left;background-color:#38B0DE;color:white;}
tr td{font-size:18px;}
</style>
</head>
<body>


<div>
	<div id="body">
	<?php  // $thisPage2="studentaddDetail"; 
		$this->load->view('template/header'); ?>
</br>
	<?php $this->load->view('enterence/enterence_head'); ?>
	<?php //if(isset($_SESSION)) {
        	//echo $this->session->flashdata('flash_data');
    	//} ?>
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
 <div>
               
                <table  class="TFtable">
		<!--<thead class="BG"><tr><th colspan=3> Contact Us</br>
		<span  style="font-size:17px;color:white;">In Case of any diffculty to access the Online Admission Portal and filling the online application form.Please Contact us at Email : admission@igntu.ac.in or Tel.No. given below:</span>		
		</th></tr>
		</thead>-->
		<thead class="BG" style="font-size:19px;"><tr><th>Sr. No.</th><th>Name of Person</th><th>Mobile No.</th></tr></thead>
                <tr>
			<td>1</td>
			<td>Sanjeev Singh</td>	
			<td>9425357989</td>
		</tr>
		<tr>
			<td>2</td>
			<td>Sarvesh Chandel</td>
			<td>7869194556</td>
		</tr>
		<tr>
			<td>3</td>
			<td>Baksi ji</td>
			<td>7504249869</td>
		</tr>
		<tr>
			<td>4</td>
			<td>Ramesh ji</td>
			<td>8093752650</td>
		</tr>
		<tr>
			<td>5</td>
			<td>Arvind Ji</td>
			<td>9893874608</td>
		</tr>
                </table>

        <div>



	</div>

<div style="margin-top:140px;">
<?php $this->load->view('template/footer'); ?>
</div>
</body>
</html>
