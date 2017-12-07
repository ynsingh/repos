<!-------------------------------------------------------
    -- @name student_step1.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
--------------------------------------------------------->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');


?><!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>IGNTU:Student Detail</title>
	<link rel="shortcut icon" href="<?php echo base_url('assets/images'); ?>/index.jpg">
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/message.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/studentNavbar.css">
		

<style type="text/css">
label{font-size:18px;}
input[type='text']{font-size:17px;height:40px;background-color:white;width:98%;}
input[type='email']{font-size:17px;height:40px;background-color:white;width:98%;}


tr td{font-size:15px;}

select{width:98%;height:40px;font-size:18px;font-size:18px;}

</style>

<script type="text/javascript">
/*
function change_getcat(){

	var xmlhttp = new XMLHttpRequest();

	xmlhttp.open("GET","<?php //echo site_url('student/getcatbr'); ?>?catbranch="+document.getElementById("register_name").value,false);

	xmlhttp.send(null);
	
	document.getElementById("Actlocation").innerHTML = xmlhttp.responseText; 
	}
*/
</script>
</head>
<body>


<div>
	<div id="body">
	<?php $this->load->view('template/header'); ?>
	<div class="welcome"><h2>Welcome : <?php echo $email?></h2></div>
	
<!--------------------------------------------------------ERROR DISPLAY-------------------------------------------------------------->


	<?php echo validation_errors('<div class="isa_warning">','</div>');?>
        <?php echo form_error('<div class="">','</div>');?>
        <?php if(isset($_SESSION[''])){?>
        <div class="isa_success"><?php echo $_SESSION[''];?></div>
        <?php
    	 };
       	?>
	
        <?php if(isset($_SESSION['err_message'])){?>
             <div class="isa_error"><div ><?php echo $_SESSION['err_message'];?></div></div>
        <?php
        };
	?>  
	

      </div>
	</br> 
	<?php $this->load->view('student/stuStepshead');?>
	</br> 
<center>
	  <table  style="background-color:#f1f1f1;" align="">

<center>
<form action="<?php echo site_url('Student/student_step1'); ?>"  method="POST">

	<table style="width:100%;">
		
	
	    <tr>	

		<td>	
			<label for="nnumber">Application number</label></br>
			<input type="text" name="Sanumber" placeholder="Enter Application Number" value="<?php echo $number;?>" readonly>	
		</td>

		<td>
			
			<label>Name of the applicant</label></br>
			<input type="text" name="Sname" placeholder="Enter your name" value="<?php echo $name;?>" readonly>	
		</td>		
		<td>	
			<label for="nnumber">Study Centers</label></br>
			
			<select name="Scenters" style="font-size:18px;">
				<option value="<?php echo $sccode; ?>"><?php echo $scname;?></option>
			</select>   
		
		
		</td>

		<td>	
			<label for="nnumber">Programme Category</label></br>
			<select name="Stypeprogramme"  style="font-size:18px;">
					<option value="<?php echo $prgcatid?>"><?php echo $prgcat; ?></option>
			</select>		
		</td>
		<td>	
			<label for="nnumber">Name of programme/course</label></br>
			<select name="Snameprogramme"  style="font-size:18px;">
				<option value="<?php echo $progid;?>"><?php echo $prgname.'('.$prgbranch.')'; ?></option>
	  		</select>
		</td>
	</tr>

				
	<tr height=10></tr>

	<tr>
		
		<!--<td>	
			<label for="nnumber">SelectBranch</label></br>
			<div id="Actlocation">			
			<select name="Sbranchname"  style="font-size:18px;">
				<option value="<?php echo $this->categid;?>"><?php echo $this->progname; ?></option>
			</select>	
			</div>
			
			
		</td>-->
		<td>	
			<label for="nnumber">Mother name</label></br>	
			<input type="text" name="Smothername" placeholder="Enter Mother Name" value="<?php echo $mname; ?>" readonly />		
		</td>

		<td>	
			<label>Father Name</label></br>
			<input type="text" name="Sfathername" placeholder="Enter Father Name" value="<?php echo $fname; ?>" readonly />		
		</td>

		<td>	
			<label for="nnumber">Category</label></br>
			<select name="Scategory"  style="font-size:18px;">
				<option value="<?php echo $categoryid; ?>"><?php echo $category; ?></option>
			</select>
		</td>
		<td>	
			<label>Gender</label></br>
			<select name="Sgender"  style="font-size:18px;">
				<option value="<?php echo $gender; ?>"><?php echo $gender; ?></option>			
			</select>  
		</td>
		
		<td>	
			 
		<label>Select date of birth</label></br>
		<input type="text" name="Sdob" placeholder="Enter Your Dob" value="<?php echo $dob; ?>" readonly>
		</td>

		
	<tr>
		<tr height=10></tr>
	<tr>	
		
		<td>	
			<label for="nnumber">Aadhar number</label></br>	
			<input type="text" name="Saadharnumber" placeholder="Enter Aadhar Number" MaxLength="12" value="<?php echo $aadhar; ?>"/>		
		</td>

		
	
		<td>	
			<label>Blood group</label></br>
		
			<select name="Sabgroup"  style="font-size:18px;" >
				<option value="" style="font-size:18px;">Select Blood Group</option>
				<option value="A+">A+</option>
				<option value="O+">O+</option>
				<option value="B+">B+</option>
				<option value="AB+">AB+</option>
				<option value="A-">A-</option>
				<option value="O-">O-</option>
				<option value="B-">B-</option>
				<option value="AB-">AB-</option>
				
	 		</select>		
		</td>
		<td>	
			<label>Religion</label></br>
			<input type="text"  name="Sreligion" placeholder="Enter Aadhar Number" value="<?php echo $religion; ?>" readonly/>
		</td>
		<td>	
			<label>Mobile/Phone no.</label></br>
			<input type="text" name="Smobile" placeholder="Enter Mobile Number" MaxLength="10"  value="<?php echo $mobile?>" readonly />		
		</td>
		<td>	
			<label>Email-Id</label></br>
	    <input type="email" name="Semail" placeholder="Enter Your Email-Id" value="<?php echo $email ?>" readonly/>		
		</td>

		
	<tr>

		<tr height=10></tr>
	<tr>
		<td>
			<label>Postal Address</label></br>
			<input type="text" name="Spaddress" placeholder="Enter Postal Address" value="<?php echo $paddress; ?>" readonly/>		
		</td>
	
		<!--<td>
			<label>District</label></br>
	    <input type="text" name="Sdist" placeholder="Enter Your District"  value="<?php echo @$this->data['Sdist']; ?>"/>		
		</td>
		<td>
			<label>Post Office</label></br>
	    <input type="text" name="Spost" placeholder="Enter Your post office" value="<?php echo @$this->data['Spost']; ?>"/>		
		</td>-->

		<td>	
			<label for="nnumber">City</label></br>	
			<input type="text" name="Scity" placeholder="Enter Your District/City" value="<?php echo $pcity; ?>" readonly/>		
		</td>
		<td>	
			<label>State</label></br>
			<input type="text" name="Sstate" placeholder="Enter Your State" value="<?php echo $pstate ?>" readonly/>		
		</td>
		<td>	
			<label>Country</label></br>
			<input type="text" name="Scountry" placeholder="Enter Your Country" value="<?php echo $pcountry; ?>" readonly/>		
		</td>

		<td>	
			<label>Pincode</label></br>
			<input type="text" name="Spincode" placeholder="Enter Your Pincode" MaxLength="6" value="<?php echo $ppincode; ?>" readonly/>		
		</td>
		
		
	</tr>
	<tr height=10></tr>
	<tr>
		<!-------write dropdown of department------------>
		<td>
				<label for="nnumber">Add Department</label></br>
				<select name="Sdepart"  style="font-size:18px;">
				<option value="">Select Department</option>
					<?php foreach($depresult as $row){?>
					<option value="<?php echo $row->dept_id; ?>"><?php echo $row->dept_name;?></option>
					<?php }?>	
	 			 </select>			
		</td>
	</tr>	
	
</table>


<table style="margin-top:30px;width:100%;border:2px solid black;" class="TFtable">
	<thead>
		<th colspan=9 style="margin-left:120px;background-color:#7e7e7e;color:white;font-size:22px;">Academic Details</th>
	</thead>
	<thead  style="background-color:#067eb7;color:white;font-size:20px;">
		<tr>
			<th>Class Name</th><th>Intitute Name</th><th>Board</th><th>Subject</th><th>Passing Year</th><th>Result Status</th><th>Max Marks</th><th>Marks Obtained</th><th>Total Marks</th>
		<tr>
	</thead>	
	<tbody>
		<?php  $i=1;
			if(!empty($studedu)){
			  foreach($studedu as $row){ ?>
		<tr>
			
			<td><input type="text" placeholder="Enter Class Name" name="classname<?php echo $i;?>1" value="<?php echo $row->asedu_class;?>" readonly></td>
			<td><input type="text" placeholder="Enter Institute Name" name="institutename<?php echo $i;?>2" value="<?php echo $row->asedu_institution;?>" readonly></td>
			<td><input type="text" placeholder="Enter Board Name" name="board<?php  echo $i;?>3" value="<?php echo $row->asedu_board;?>" readonly></td>
			<td><input type="text" placeholder="Enter Subject Name" name="subject<?php  echo $i;?>4" value="<?php echo $row->asedu_subject;?>" readonly></td>
			<td><input type="text" placeholder="Enter Passing Year" name="passingyear<?php  echo $i;?>5" value="<?php echo $row->asedu_passingyear;?>" readonly></td>
			<td><input type="text" placeholder="Enter Result Status" name="status<?php  echo $i;?>6" value="<?php echo $row->asedu_resultstatus;?>" readonly></td>
			<td><input type="text" placeholder="Enter Max Marks" name="maxmarks<?php  echo $i;?>7" value="<?php echo $row->asedu_maxmarks;?>" readonly></td>
			<td><input type="text" placeholder="Enter Marks Obtained" name="marksobtained<?php  echo $i;?>8" value="<?php echo $row->asedu_marksobtained;?>" readonly></td>
			<td><input type="text" placeholder="Enter Percentage" name="percentage<?php  echo $i;?>9" value="<?php echo $row->asedu_percentage;?>" readonly></td>
		</tr>
		<?php $i++;}}?>
	</tbody>
</table>
<br><br>
<table style="width:100%;" align=center>
		<tr>
			<td align=center><input type="submit" name="addstudent" value="Verify" style="width:9%;height:35px;font-size:18px;">
			<input type="reset" name="reset" value="Reset" style="width:9%;height:35px;font-size:18px;"></td>
		</tr>
</table>

</form>

</center>


<?php //$thisPage2="studentaddDetail";
	$this->load->view('template/footer'); ?>
</body>
</html>
