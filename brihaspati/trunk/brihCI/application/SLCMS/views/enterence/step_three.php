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
		<nav> 	<h1>Welcome to IGNTU  </h1></nav>
</br>
	<?php $this->load->view('enterence/admission_steps');?>
	
<!--------------------------------------------------------ERROR DISPLAY-------------------------------------------------------------->
<?php
echo "<center>";

	if($this->session->flashdata('msg')){
echo "<div style='font-size:20px;text-align:center;background-color: #FFBABA;width:50%;height:30px;color:green;'>";
	echo $this->session->flashdata('msg');
echo "<div>";	
}


	if((isset($_SESSION['success'])) && ($_SESSION['success'])!=''){
		//echo "<div style=\"margin-left:30px;width:1700px;align:left;font-size:18px;height:10px;\" class=\"isa_success\">";
	echo "<table style=\"width:70%;font-size:18px;height:10px;border:1px solid white;\" class=\"isa_success\">";			
		echo "<tr>";
			echo "<td style='font-size:18px;float:left;'>";
				echo $_SESSION['success'];
			echo "</td>";
		echo "<tr>";
		//echo "</div>";
	echo "</table>";
	}
	if((isset($_SESSION['error'])) && (($_SESSION['error'])!='')){
	echo "<table id='error'>";			
		echo "<tr>";
			echo "<td style='font-size:18px;'>";
				echo $_SESSION['error'];
			echo "</td>";
		echo "<tr>";
		//echo "</div>";
	echo "</table>";
	}
echo "</center>";
?>

</br></br>
	<form action="<?php echo site_url('enterence/step_three'); ?>" method="POST" enctype="multipart/form-data">
<center>		
		<table style="border:0px solid black;width:50%;">
			<tr>
				<td>
				<label><b>Recent photograph (Photo size should be 100kb and photo extension jpeg , jpg or png)</b></label></br>				
				<input type='file' name='photo' ></td></tr>
		<tr height=20></tr>
				<tr>				
				<td>
				<label><b>Signature(Signature size should be 100kb and signature extension jpeg , jpg or png)</b></label></br>				
				<input type='file' name='sign' /></td>
		
			</tr>
<tr height=20></tr>
			<tr><td>
				<label><b>Professional work experience/portfolio/academic/thesis<br>(Select multiple files size should be 500kb and photo extension jpeg , jpg or png)</b></label></br>				
				<input type="file" name="files[]" multiple="multiple" id="files" onchange="javascript:updateList()"/>
				<br><span style="font-size:15px;"><b>Selected Files :</b></span><div id="fileList"></div></td></tr>
<tr height=20></tr>

		</table>
		<table style="width:10%;">
<tr height=10></tr>
			<tr>
				<td><input type="submit" name="fileSubmit" value=" Upload " style="width:100%;height:35px;font-size:18px;"></td>
			</tr>
			
		</table>
		<table style="width:50%;">
			<tr>
				<td><h2>Note: Photo ,Signature file size is only 100 kb or Enclosure size must be 500kb and extension jpeg , jpg or png.</h2></td>
			</tr>
		</table>
	
	</form>

</center>
<?php $this->load->view('template/footer'); ?>

<?php  //} 
//else{header("location:".base_url()."hrmindex.php");}?>
</body>
</html>
