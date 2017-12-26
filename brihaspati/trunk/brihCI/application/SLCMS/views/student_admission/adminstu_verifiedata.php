<!-------------------------------------------------------
    -- @name stu_verifiedata.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
--------------------------------------------------------->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');


?><!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Student Verification Data</title>
	<link rel="shortcut icon" href="<?php echo base_url('assets/images'); ?>/index.jpg">
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>

<style type="text/css">
label{font-size:18px;}
input[type='text']{font-size:17px;height:40px;background-color:white;width:98%;}
input[type='email']{font-size:17px;height:40px;background-color:white;width:98%;}


tr td{font-size:15px;}

select{width:98%;height:40px;font-size:18px;font-size:18px;}

</style>


</head>
<body>


<div>
	<div>
	<?php $this->load->view('template/header'); ?>
	<table style="width:100%;">
	<tr>
		<td align=center style="font-size:18px;"><b>Verify Student Data</b></td>
	</tr>	
	</table>
<!--------------------------------------------------------ERROR DISPLAY-------------------------------------------------------------->


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
	

      </div>
	
	</br> 
<center>
	  <table  style="background-color:#f1f1f1;" align="">

<center>
<form action="<?php echo site_url('adminadmissionstu/adminstu_verifidata'); ?>"  method="POST">

	<table style="width:100%;">
		
	
	    <tr>	

		<td>	
			<label for="nnumber">Hall Ticket Number</label></br>
			<?php echo $hlno;?>
		</td>

		<td>
			
			<label>Name of the Applicant</label></br>
			<?php echo $name;?>
		</td>		
		<td>	
			<label for="nnumber">Study Centers</label></br>
			<?php echo $scname;?>
		</td>

		<td>	
			<label for="nnumber">Programme Category</label></br>
			<?php echo $prgcat; ?>
		</td>
		<td>	
			<label for="nnumber">Name of Programme/Course</label></br>
			<?php echo $prgname.'('.$prgbranch.')'; ?>
		</td>
	</tr>

				
	<tr height=10></tr>

	<tr>
		
		<td>	
			<label for="nnumber">Mother Name</label></br>	
			<?php echo $mname; ?>
		</td>

		<td>	
			<label>Father Name</label></br>
			<?php echo $fname; ?>
			
		</td>

		<td>	
			<label for="nnumber">Category</label></br>
			<?php echo $categoryname; ?>
			
		</td>
		<td>	
			<label>Gender</label></br>
			<?php echo $gender; ?>  
		</td>
		
		<td>	
			 
		<label>Date of Birth</label></br>
		<?php echo $dob; ?>
		</td>

		
	<tr>
		<tr height=10></tr>
	<tr>	
		
		<td>	
			<label for="nnumber">Aadhar Number</label></br>	
			<?php echo $aadhar; ?>		
		</td>

		<td>
			<label>Blood Group</label></br>
			<?php echo $blgroup;?>
		</td>
		<td>	
			<label>Religion</label></br>
			<?php echo $religion; ?>
		</td>
		<td>	
			<label>Mobile/Phone No.</label></br>
			<?php echo $mobile?>
		</td>
		<td>	
			<label>Email-Id</label></br>
	   		<?php echo $email ?>		
		</td>

		
	<tr>

		<tr height=10></tr>
		
		
	<tr>
		<td colspan=6>
			<table style="width:100%;border:2px black solid;">
				<thead>
				<th colspan=9 style="background-color:#7e7e7e;color:white;font-size:22px;text-align:left;">Address Details</th>
				</thead>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<label>Postal Address</label></br>
			<?php echo $paddress; ?>		
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
			<?php echo $pcity; ?>		
		</td>
		<td>	
			<label>State</label></br>
			<?php echo $pstate ?>		
		</td>
		<td>	
			<label>Country</label></br>
			<?php echo $pcountry; ?>
		</td>

		<td>	
			<label>Pincode</label></br>
			<?php echo $ppincode; ?>		
		</td>
		
		
	</tr>
	<tr height=10></tr>
	<tr>
		<!-------write dropdown of department---------
		<td>
				<label for="nnumber">Add Department</label></br>
				<select name="Sdepart"  style="font-size:18px;">
				<option value="">Select Department</option>
					<?php foreach($depresult as $row){?>
					<option value="<?php echo $row->dept_id; ?>"><?php echo $row->dept_name;?></option>
					<?php }?>	
	 			 </select>			
		</td>--->
	</tr>	
	
</table>


<table style="margin-top:30px;width:100%;border:2px solid black;" >
	<thead>
		<th colspan=9 style="background-color:#7e7e7e;color:white;font-size:22px;text-align:left;">Academic Details</th>
	</thead>
	<thead  style="background-color:#067eb7;color:white;font-size:20px;">
		<tr>
			<th>Class Name</th><th>Institute Name</th><th>Board</th><th>Subject</th><th>Passing Year</th><th>Result Status</th><th>Max Marks</th><th>Marks Obtained</th><th>Total Marks</th>
		<tr>
	</thead>	
	<tbody>
		<?php  $i=1;
			if(!empty($studedu)){
			  foreach($studedu as $row){ 
			if(!empty($row->sedu_institution)){			
		?>
		<tr>
			
			<td><input type="text" placeholder="Enter Class Name" name="classname<?php echo $i;?>1" value="<?php echo $row->sedu_class;?>" readonly></td>
			<td><input type="text" placeholder="Enter Institute Name" name="institutename<?php echo $i;?>2" value="<?php echo $row->sedu_institution;?>" readonly></td>
			<td><input type="text" placeholder="Enter Board Name" name="board<?php  echo $i;?>3" value="<?php echo $row->sedu_board;?>" readonly></td>
			<td><input type="text" placeholder="Enter Subject Name" name="subject<?php  echo $i;?>4" value="<?php echo $row->sedu_subject;?>" readonly></td>
			<td><input type="text" placeholder="Enter Passing Year" name="passingyear<?php  echo $i;?>5" value="<?php echo $row->sedu_passingyear;?>" readonly></td>
			<td><input type="text" placeholder="Enter Result Status" name="status<?php  echo $i;?>6" value="<?php echo $row->sedu_resultstatus;?>" readonly></td>
			<td><input type="text" placeholder="Enter Max Marks" name="maxmarks<?php  echo $i;?>7" value="<?php echo $row->sedu_maxmarks;?>" readonly></td>
			<td><input type="text" placeholder="Enter Marks Obtained" name="marksobtained<?php  echo $i;?>8" value="<?php echo $row->sedu_marksobtained;?>" readonly></td>
			<td><input type="text" placeholder="Enter Percentage" name="percentage<?php  echo $i;?>9" value="<?php echo $row->sedu_percentage;?>" readonly></td>
		</tr>
		<?php $i++;}}}?>
	</tbody>
</table>
<br><br>
<table style="width:100%;" align=center>
	<input type="hidden" name="stu_smid" value="<?php echo $smid;?>">
	<input type="hidden" name="stu_hlno" value="<?php echo $hlno;?>">	
		<tr>
			<td align=center><input type="submit" name="stuverify" value="Verify" style="width:9%;height:35px;font-size:18px;">
			<!--<input type="reset" name="reset" value="Reset" style="width:9%;height:35px;font-size:18px;"></td>-->
		</tr>
</table>

</form>

</center>


<?php //$thisPage2="studentaddDetail";
	$this->load->view('template/footer'); ?>
</body>
</html>
