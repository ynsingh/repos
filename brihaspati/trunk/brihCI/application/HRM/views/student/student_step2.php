<!-------------------------------------------------------
    -- @name student_step2.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
--------------------------------------------------------->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
if (isset($this->session->userdata['sm_id'])) {
$id = ($this->session->userdata['sm_id']);
//$firstname = ($this->session->userdata['sm_fname']);
//$applino = ($this->session->userdata['sm_applicationno']);
?><!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>IGNTU : Student crieteria</title>
	<link rel="shortcut icon" href="<?php echo base_url('assets/images'); ?>/index.jpg">

<style type="text/css">
label{font-size:18px;}
input[type='text']{font-size:17px;width:120%;height:30px;}
input[type='button']{font-size:16px;}
#step2{border:1px solid black;width:70%;}

</style>


</head>
<body>


<div>
	<div id="body">
	<?php //$thisPage2="studentCrieteria2"; 
		$this->load->view('template/header'); ?>
	<nav> 	<h1>Welcome to IGNTU  </h1></nav>
<?php

echo "<div class='col-lg-12'>";
echo "<table>";
echo "<tr>";
echo "<td style='width:350px;'>";
//echo "<h4>id". " <b>:</b> ". " " .$id."</h4>";
echo "</td>";

//echo "<td style='width:500px;'>";
//echo "<h4>Welcome". " <b>:</b> ". " " .$firstname." "."</h4>";
//echo "</td>";

//echo "<td>";
//echo "<h4>Application No.". " <b>:</b> ". " " .$applino." </h4>";
//echo "</td>";

echo "</tr>";
echo "</table>";
echo "</div>";
	
?>
	<?php //if(isset($_SESSION)) {
        	//echo $this->session->flashdata('flash_data');
    	//} ?>
 	<br><br>
		<?php $this->load->view('student/stuStepshead');?>
	<center>
</br>
<?php
echo "<center>";
echo "<div style='font-size:20px;text-align:center;width:50%;height:30px;'>";
	if($this->session->flashdata('msg')){
	echo $this->session->flashdata('msg');
	
}
echo "<div>";
echo "</center>";
?>	
	<div align="left" style="margin-left:0px;width:1700px;">
        <?php echo validation_errors('<div class="isa_warning">','</div>');?>
        <?php echo form_error('<div style="margin-left:0px;" class="isa_error">','</div>');?>
        <?php if(isset($_SESSION['success'])){?>
<center>
       <!---- <div class="alert alert-success" style="font-size:20px;text-align:center;background-color:#DFF2BF;width:50%;height:30px;"><?php echo $_SESSION['success'];?></div>---->
</center>
        <?php
    	 };
       	?>
        
      </div>

	<div id="step2">
	<table style="">
		<tr><td>
		<?php $this->load->view('student/studentCrieteria2.php');//include('student/studentCrieteria2.php');?>
		</td></tr>
	</table> 
<table style="">
<tr><td>
	<form action="<?php echo site_url('Student/student_step2'); ?>" method="POST">
		 <input type="checkbox" name="crieteria[]" value="Read" id="termsChkbx"><span style="font-size:16px;">I have read the condition all above and agree.</span>
			</br>
		<!--<input type="hidden" name="id" value="<?php echo $id; ?>">-->
		 <a href="" style="text-decoration:none;">
			<!---<input type='hidden' name="Sid" size='20' value="<?php echo $id;?>" readonly/>
			
			<input type='hidden' name="Sano" size='20' value="<?php //echo $applino?>" readonly/>
		
			<input type='hidden' name="Sname" size='20' value="<?php //echo $firstname; ?>" readonly/>--->

			<input type="submit" name="criteria" value="Next" style="width:40%;height:40px;font-size:22px;" id="sub1" disabled="disabled">
		</a>
	</form>
<script>
  document.getElementById('termsChkbx').addEventListener('click', function (e) {
  document.getElementById('sub1').disabled = !e.target.checked;
});

</script>

</td></tr>
</table>
</center>


</div>
<?php //$thisPage2="studentCrieteria2"; 
$this->load->view('template/footer'); ?>
	<!--<p class="footer">Page rendered in <strong>{elapsed_time}</strong> seconds. <?php echo  (ENVIRONMENT === 'development') ?  'CodeIgniter Version <strong>' . CI_VERSION . '</strong>' : '' ?></p>-->
</div>

<?php  } //else {  header("location:student/studentForm"); }
else{header("location:".base_url()."Student/Step0");}?>
</body>
</html>

