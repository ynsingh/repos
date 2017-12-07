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
	<?php $this->load->view('template/header'); ?>
	<div class="welcome"><h2>Welcome : <?php echo $email?></h2></div>

	<?php $this->load->view('student/stuStepshead');?>
	<center>
</br>


	<div id="">
	<table style="width:95%;margin-top:-40px;">
		<tr><td>
		<?php $this->load->view('student/studentCrieteria2.php');//include('student/studentCrieteria2.php');?>
		</td></tr>
	</table> 
<table style="width:100%;">
<tr><td align=left>
	<form action="<?php echo site_url('Student/student_step2'); ?>" method="POST">
	 <input type="checkbox" name="crieteria[]" value="Read" id="termsChkbx">
	 <span style="font-size:16px;">I have read the condition all above and agree.</span>
	</br>
		<!--<input type="hidden" name="id" value="<?php echo $id; ?>">-->
		 <a href="" style="text-decoration:none;color:black;">
			<!---<input type='hidden' name="Sid" size='20' value="<?php echo $id;?>" readonly/>
			
			<input type='hidden' name="Sano" size='20' value="<?php //echo $applino?>" readonly/>
		
			<input type='hidden' name="Sname" size='20' value="<?php //echo $firstname; ?>" readonly/>--->

			<input type="submit" name="criteria" value="Next" style="width:10%;height:40px;font-size:22px;" id="sub1" disabled="disabled">
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

