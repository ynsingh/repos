<!-------------------------------------------------------
    -- @name student_step3.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
--------------------------------------------------------->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');

if (isset($this->session->userdata['sm_id'])) {
$id = ($this->session->userdata['sm_id']);
//print_r($id);
//$firstname = ($this->session->userdata['sm_fname']);
//$applino = ($this->session->userdata['sm_applicationno']);
?><!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>IGNTU:Upload Photo & Sign</title>
	<link rel="shortcut icon" href="<?php echo base_url('assets/images'); ?>/index.jpg">
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/message.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/studentNavbar.css">

<style type="text/css">
label{font-size:18px;}
input[type='text']{font-size:17px;height:30px;background-color:white;}
#error{margin-left:30px;width:1700px;text-align:left;font-size:18px;height:40px;color: #D8000C; padding:1px 1px 18px 1px;background-color: #FFBABA;border:1px ridge white;}

tr td{font-size:15px;}
tr th{background:black;color:white;font-weight:bold;}
select{width:100%;font-size:17px;height:40px;}

</style>

</head>
<body>


<div>
	<div id="body">
	<?php $this->load->view('template/header'); ?>
	<nav> 	<h1>Welcome to IGNTU  </h1></nav>
	<?php $this->load->view('student/stuStepshead');?>
<?php

echo "<div class='col-lg-12'>";
echo "<table>";
echo "<tr>";
//echo "<td style='width:350px;'>";
//echo "<h4>id". " <b>:</b> ". " " .$id."</h4>";
//echo "</td>";

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
<!--------------------------------------------------------------------------------------------------------------------------------------------------->
<?php
echo "<center>";
echo "<div style='font-size:20px;text-align:center;width:50%;height:5px;'>";
	if($this->session->flashdata('msg')){
	echo $this->session->flashdata('msg');
	
}
echo "<div>";
echo "</center>";
	if((isset($_SESSION['success'])) && ($_SESSION['success'])!=''){
		echo "<div style=\"margin-left:30px;width:1700px;align:left;font-size:18px;height:10px;\" class=\"isa_success\">";
		echo $_SESSION['success'];
		echo "</div>";
	}
	if((isset($_SESSION['error'])) && (($_SESSION['error'])!='')){
		echo "<div id='error'>";
		echo '<div style="margin-left:40px;">'.$_SESSION['error'].'</div>';
		echo "</div>";
	}
?>

	<center>
	
	<div  style="font-size:20px;width:80%;">
	</div>

		<h1>Upload Your Photo & Signature</h1>
	</br>
	
	<form action="<?php echo site_url('Student/student_step3'); ?>" method="POST" enctype="multipart/form-data">
	<table>
		<tr>
		<td>
			<label style="font-size:20px;">Upload your photo : </label></td>
			<td><input type='file' name='userfile' size='20' style="font-size:20px;margin-left:0px;"/>
		</td>
		</tr>
		<tr style=""></tr>
		<tr>
		<td>
			<label style="font-size:20px;">Upload your signature : </label></td>
			<td><input type='file' name='userfile2' size='20' style="font-size:20px;"/>	
		</td>
		</tr>	
		<tr height=10></tr>
		<tr>
		  <td></td>
			<!--<input type='hidden' name="Uid" size='20' value="<?php echo $id;?>" readonly/>-->
			
			<!--<input type='hidden' name="Sano" size='20' value="<?php //echo $applino?>" readonly/> -->
		
			<!--<input type='hidden' name="Uname" size='20' 
value="<?php //echo $this->Common_model->get_listspfic1('student_master','sm_fname','sm_id',$id)->sm_fname; ?>" readonly/> -->
			
			<td><input type='submit' name='submitStep3' value='Upload' style="height:35px;font-size:18px;width:105px;"/></td>
			
		</tr>
		
	</table>	
	</form>
		<h3>Note : Photo size 200kb and signature size 20kb.</h3>
	<center>
<!--------------------------------------------------------------------------------------------------------------------------------------------------->

<?php $this->load->view('template/footer'); ?>
<?php  } //else {  header("location:student/student_step0"); }
else{header("location:".base_url()."Student/Step0");}	?>
</body>
</html>
