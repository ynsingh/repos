<!-------------------------------------------------------
    -- @name registrationsemester.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
--------------------------------------------------------->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');


?><!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>IGNTU:Semester registration</title>
	<link rel="shortcut icon" href="<?php echo base_url('assets/images'); ?>/index.jpg">
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/message.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/studentNavbar.css">

<style type="text/css">
label{font-size:18px;}
input[type='text']{font-size:17px;height:30px;background-color:white;}
input[type='email']{font-size:17px;height:30px;background-color:white;}


tr td{font-size:15px;}
tr th{background:black;color:white;font-weight:bold;}
select{width:100%;font-size:17px;height:40px;}

</style>

</head>
<body>


<div>
	<div id="body">
	<?php  // $thisPage2="studentaddDetail"; 
		$this->load->view('template/header'); ?>
</br>
	<?php //$this->load->view('template/stumenu'); ?>
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
<?php //$uid=($this->session->userdata['id_user']); print_r($uid);?>

	<!---<h1>Welcome <?//= $this->session->userdata('username') ?>  </h1>-->
	<h1>Semester Registration</h1>
<center>
	  <table  style="background-color:#f1f1f1;" align="">

<center>
<form action="<?php echo site_url('Request/semesterregi'); ?>"  method="POST">

	<table style="width:65%;">
	    <tr>	
		<td>	
			<!---<span style="color:red;"><?php echo form_error('Sanumber');?></span>--->
			<label for="nnumber">Roll number</label></br>
			<input type="text" name="Sanumber" placeholder="Enter Roll Number" value="<?php echo $this->sturollno;?>" readonly>	
		<td/>
		<td>
			<!---<span style="color:red;"><?php echo form_error('Sname');?></span>--->
			<label>Student name</label></br>
			<input type="text" name="Sname" placeholder="Enter your name" value="<?php echo $this->name;?>" readonly>	
		<td/>		
		<td>	<!---<span style="color:red;"><?php echo form_error('Scenters');?></span>--->
			<label for="nnumber">Study Centers</label></br>
			<input type="text" name="Studecenter" placeholder="Enter your study center" value="<?php echo $this->scname;?>" readonly>	
		<td/>
		<td>	<!---<span style="color:red;"><?php echo form_error('Stypeprogramme');?></span>--->
			<label for="nnumber">Prgramme category</label></br>
			<input type="text" name="Sprotype" placeholder="Enter your program type" value="<?php echo $this->pcatname;?>" readonly>		
		<td/>
		<td>	
			<label for="nnumber">Name of programme/course</label></br>
			<input type="text" name="Sproname" placeholder="Enter your program name" value="<?php echo $this->pname;?>" readonly>		
		<td/>
	</tr>

	<tr height=10></tr>

	<tr>
		<td>	
			<label for="nnumber">Branch name</label></br>
			<input type="text" name="Sbranch" placeholder="Enter your brach name" value="<?php echo $this->brname;?>" readonly>		
		<td/>
		<td>	
			<!---<span style="color:red;"><?php echo form_error('Smothername');?></span>--->
			<label for="nnumber">Mother name</label></br>	
			<input type="text" name="Smothername" placeholder="Enter Mother Name" value="<?php echo $this->mname; ?>" readonly/>		
		<td/>
		<td>	
			<!---<span style="color:red;"><?php echo form_error('Sfathername');?></span>--->
			<label>Father Name</label></br>
			<input type="text" name="Sfathername" placeholder="Enter Father Name" value="<?php echo $this->fathname; ?>" readonly/>		
		<td/>
		<td>	
			<!---<span style="color:red;"><?php echo form_error('Scategory');?></span>--->
			<label for="nnumber">Category</label></br>
			<input type="text" name="Scate" placeholder="Enter category" value="<?php echo $this->catename;?>" readonly>	
		<td/>
		<td>	<!---<span style="color:red;"><?php echo form_error('Sgender');?></span>--->
			<label>Gender</label></br>
			<input type="text" name="Sgend" placeholder="Enter gender" value="<?php echo $this->gender;?>" readonly>	
			<!--<input type="text" name="Sgender" placeholder="Enter Gender" >--->		
		<td/>
	<tr>
		<tr height=10></tr>
	<tr>	
		<td>	<!---<span style="color:red;"><?php echo form_error('Sdob');?></span>--->

		<label>Date of birth</label></br>
		<input type="text" name="Sdob" placeholder="Enter date of birth" value="<?php echo $this->dob;?>" readonly>	
		<td/>


		<td>	<!---<span style="color:red;"><?php echo form_error('Saadharnumber');?></span>--->
			<label for="nnumber">Aadhar number</label></br>	
			<input type="text" name="Saadharnumber" placeholder="Enter Aadhar Number" MaxLength="12" value="<?php echo $this->uid; ?>" readonly/>		
		<td/>

		<!-------write dropdown of department------------>
		<td>
				<label for="nnumber">Department name</label></br>
				<input type="text" name="Sdepart" placeholder="Enter department" value="<?php echo $this->depname;?>" readonly>			
		<td/>
	
		<td>	<!---<span style="color:red;"><?php echo form_error('Sabgroup');?></span>--->
			<label>Blood group</label></br>
			<!--<input type="text" name="Sabgroup" placeholder="Enter Blood Group" value="<?php echo @$this->data['Sabgroup']; ?>"/>--->
			<input type="text" name="Sbname" placeholder="Enter blood group" value="<?php echo $this->bgroup;?>" readonly>			
		<td/>
		<td>	<!---<span style="color:red;"><?php echo form_error('Sreligion');?></span>--->
			<label>Religion</label></br>
			<input type="text" name="Sreligion" placeholder="Enter religion" value="<?php echo $this->religname;?>" readonly>	
		<td/>
	<tr>

		<tr height=10></tr>
	<tr>
		<td>	<!---<span style="color:red;"><?php echo form_error('Spaddress');?></span>--->
			<label>Address</label></br>
			<input type="text" name="Stcaddress" placeholder="Enter Postal Address" value="<?php echo $this->padd; ?>" readonly/>		
		<td/>
		
	
		<td>	<!--<span style="color:red;"><?php echo form_error('Semail');?></span>-->
			<label>District</label></br>
	    <input type="text" name="Stdist" placeholder="Enter Your District" style="height:30px;width:97%;" value="<?php echo $this->pdist; ?>" readonly/>		
		<td/>
		<td>	<!--<span style="color:red;"><?php echo form_error('Semail');?></span>-->
			<label>Post Office</label></br>
	    <input type="text" name="Stpost" placeholder="Enter Your post office" style="height:30px;width:98%;" value="<?php echo $this->ppost; ?>" readonly/>		
		<td/>

		<td>	<!---<span style="color:red;"><?php echo form_error('Scity');?></span>--->
			<label for="nnumber">City</label></br>	
			<input type="text" name="Stcity" placeholder="Enter Your District/City" value="<?php echo $this->pcity; ?>" readonly/>		
		<td/>
		<td>	<!---<span style="color:red;"><?php echo form_error('Sstate');?></span>--->
			<label>State</label></br>
			<input type="text" name="Ststate" placeholder="Enter Your State" value="<?php echo $this->pstat; ?>" readonly/>		
		<td/>
	<tr>
		
	<tr height=10></tr>
	<tr>
		<td>	<!---<span style="color:red;"><?php echo form_error('Spincode');?></span>--->
			<label>Country</label></br>
			<input type="text" name="Stcountry" placeholder="Enter Your Country" value="<?php echo $this->pcounname; ?>" readonly/>		
		<td/>

		<td>	<!---<span style="color:red;"><?php echo form_error('Spincode');?></span>--->
			<label>Pincode</label></br>
			<input type="text" name="Stpincode" placeholder="Enter Your Pincode" MaxLength="6" value="<?php echo $this->ppin; ?>" readonly/>		
		<td/>
		
		<td>	<!---<span style="color:red;"><?php echo form_error('Smobile');?></span>--->
			<label>Mobile/Phone no.</label></br>
			<input type="text" name="Stmobile" placeholder="Enter Mobile Number" MaxLength="10" pattern="/^+91(7\d|8\d|9\d)\d{9}$/" value="<?php echo $this->mobile; ?>" readonly/>		
		<td/>
		<td>	<!--<span style="color:red;"><?php echo form_error('Semail');?></span>-->
			<label>Email-Id</label></br>
	    <input type="email" name="Semail" placeholder="Enter Your Email-Id" style="height:30px;width:98%;" value="<?php echo $this->email; ?>" readonly/>		
		<td/>

		<td>	<!---<span style="color:red;"><?php echo form_error('Spincode');?></span>--->
			<label>Semester</label></br>
			<input type="text" name="Ssem" placeholder="Enter Your Pincode" MaxLength="6" value="<?php echo $this->cusem; ?>" />		
		<td/>
	</tr>
		<tr height=30></tr>
	<tr>
		<td></td><td></td><td></td><td></td>
		<td><input type="submit" name="register" value="Submit" style="width:80%;height:35px;font-size:18px;"></td>
	</tr>
</table>	


</form>

</center>


<?php //$thisPage2="studentaddDetail";
	$this->load->view('template/footer'); ?>
</body>
</html>
