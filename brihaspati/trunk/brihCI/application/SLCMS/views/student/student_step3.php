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
	<title>SLCMS:Upload Photo & Sign</title>
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
	<?php $this->load->view('template/header2'); ?>
	<div class="welcome"><h2>Welcome : <?php echo $email?></h2></div>
	<?php $this->load->view('student/stuStepshead');?>

<!--------------------------------------------------------------------------------------------------------------------------------------------------->
	<?php echo validation_errors('<div class="isa_warning">','</div>');?>
        <?php echo form_error('<div class="">','</div>');?>
        <?php 
	    if(!empty($_SESSION['success'])){	
		if(isset($_SESSION['success'])){?>
         <div class="isa_success" style="font-size:18px;"><?php echo $_SESSION['success'];?></div>
         <?php
          } };
         ?>
	
        <?php if(isset($_SESSION['err_message'])){?>
             <div class="isa_error"><div ><?php echo $_SESSION['err_message'];?></div></div>
        <?php
        };
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
