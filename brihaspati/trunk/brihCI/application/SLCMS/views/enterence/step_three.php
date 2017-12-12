<!-------------------------------------------------------
    -- @name step_three.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
--------------------------------------------------------->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
//if (isset($this->session->userdata['amid'])) {
//$id = ($this->session->userdata['amid']);
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
<style>
#error{width:70%;text-align:left;font-size:18px;height:40px;color: #D8000C; padding:1px 1px 18px 1px;background-color: #FFBABA;border:1px ridge white;}
#fileList{list-style-type:none;font-size:14px;font-weight:bold;}
input[type='file']{font-size:15px;}
</style>
<script>
updateList = function() {
  var input = document.getElementById('files');
  var output = document.getElementById('fileList');

  output.innerHTML = '<ul>';
  for (var i = 0; i < input.files.length; ++i) {
    output.innerHTML += '<li>' + input.files.item(i).name + '</li>';
  }
  output.innerHTML += '</ul>';
}
</script>
</head>
<body>


<div>
	<div id="body">
	<?php  // $thisPage2="studentaddDetail"; 
		$this->load->view('template/header'); ?>
		<nav><h2><?php echo "Welcome ". $email;?></h2></nav>
</br>
	<?php $this->load->view('enterence/admission_steps');?>
	
<!--------------------------------------------------------ERROR DISPLAY-------------------------------------------------------------->
	<?php echo validation_errors('<div class="isa_warning">','</div>');?>
        <?php echo form_error('<div class="">','</div>');?>
        <?php if(isset($_SESSION['success'])){?>
        <div class="isa_success"><?php echo $_SESSION['success'];?></div>
        <?php
    	 };
       	?>
	
        <?php if(isset($_SESSION['err_message'])){?>
             <div class="isa_error"><div ><?php echo $_SESSION['err_message'];?></div></div>
        <?php
        };
	?>

</br></br>
	<form action="<?php echo site_url('enterence/step_three'); ?>" method="POST" enctype="multipart/form-data">
<center>		
		<table style="border:0px solid black;width:60%;" align=center>
			<tr>
				<td>
				<label><span id="star">*</span><b>Recent Photograph (Photo size should be 100kb and extension should be jpeg, jpg or png)</b></label></br>				
				<input type='file' name='photo' ></td></tr>
		<tr height=20></tr>
				<tr>				
				<td>
				<label><span id="star">*</span><b>Signature (Signature size should be 100kb and extension should be jpeg, jpg or png)</b></label></br>				
				<input type='file' name='sign' /></td>
		
			</tr>
<tr height=20></tr>
			<!----<tr><td>
				<label><span id="star">*</span><b>NOC/Declaration/Professional work experience/portfolio/academic/thesis<br>(Select multiple files size should be 500kb and photo extension jpeg , jpg , png or pdf)</b></label></br>				
				<input type="file" name="files[]" multiple="multiple" id="files" onchange="javascript:updateList()"/>
				<br><span style="font-size:15px;"><b>Selected Files :</b></span><div id="fileList"></div></td></tr>
<tr height=20></tr>--->

		</table>
		<table style="width:10%;">
<tr height=10></tr>
			<tr>
				<td><input type="submit" name="fileSubmit" value=" Upload " style="width:100%;height:35px;font-size:18px;"></td>
			</tr>
			
		</table>
		<table style="width:50%;">
			<tr>
				<td><h4><b>Note: Photo ,Signature file size is only 100 kb and extension jpeg , jpg or png.</b></h4></td>
			</tr>
		</table>
	
	</form>

</center>
<?php $this->load->view('template/footer'); ?>

<?php  //} 
//else{header("location:".base_url()."hrmindex.php");}?>
</body>
</html>
