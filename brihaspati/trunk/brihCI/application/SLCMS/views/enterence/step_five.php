<!-------------------------------------------------------
    -- @name student_step5.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
--------------------------------------------------------->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
//if (isset($this->session->userdata['sm_id'])) {
//$id = ($this->session->userdata['sm_id']);
//print_r($id);
//$firstname = ($this->session->userdata['sm_fname']);
//$applino = ($this->session->userdata['sm_applicationno']);
?><!DOCTYPE html>
<!--hide extra text on print page <html moznomarginboxes mozdisallowselectionprint> use that code-->
<html moznomarginboxes mozdisallowselectionprint>
<head>
	<meta charset="utf-8">
	<title>IGNTU:Student Detail</title>
	<link rel="shortcut icon" href="<?php echo base_url('assets/images'); ?>/index.jpg">
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/studentNavbar.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
	<link rel="stylesheet" type="text/css" media="all" href="<?php echo base_url(); ?>assets/css/style.css" />
	<link rel="stylesheet" type="text/css" media="all" href="<?php echo base_url(); ?>assets/css/Studentsteps.css" />
	<link rel="stylesheet" type="text/css" media="print" href="<?php echo base_url(); ?>assets/css/studentStepmedia.css" />

<script>
function myFunction() {
    window.print();
}

</script>

</head>
<body>

<div>
<?php
/*
echo "<center>";
	if($this->session->flashdata('msg')){
	echo" <div style='font-size:20px;text-align:center;background-color:#DFF2BF;width:50%;height:30px;color:green;'>";
		echo $this->session->flashdata('msg');
	echo "<div>";
}
echo "</center>";*/
?>
</div>

<page size="A4">
	<div id="body">
	
</br></br>

<center>
	<div id="formbody">

	<div id="formbody1">
		<header>
		<div id="logo2">
			<img src="<?php echo base_url(); ?>uploads/logo/logo2.jpg" alt="logo">
		</div> 
		</header>
		<div class="textname">Admission Form Detail</div>
		  <div id="photo">
			<img src="<?php echo base_url('uploads/SLCMS/enterence/'.$id.'/'.$photo); ?>" >
		  </div>
		<table class="TFtable" id="personal">
			<thead id="styleTable"><th colspan=7  style="text-align:justify;">Personal Detail</th></thead>
			
			<tr>
				<td>Name of the candidate :</td>
				<td><?php echo $name;?></td>
				<td>Name of the Course :</td>
			        <td><?php echo $prgname.'('.$prgbranch.')';?></td></tr>
			
			<tr>
				<td>Mother Name :</td>
				<td><?php echo $mname;?></td>
				<td>Father Name :</td>
				<td><?php echo $fname;?></td>
			</tr>
			<tr>
				<td>Mother Mobile Number :</td>
				<td><?php echo $mmo;?></td>
				<td>Father Mobile Number :</td>
				<td><?php echo $fmo;?></td>
			</tr>
			<tr>
				<td>Mother Occupation :</td>
				<td><?php echo $moccupation;?></td>
				<td>Father Occupation :</td>
				<td><?php echo $foccupation;?></td>
			</tr>

			<tr>
				<td >Gender :</td>
				<td><?php echo $gender;?></td>
				<td>Date of birth :</td>
				<td><?php echo $dob;?></td>
			</tr>

			<tr>
				<td>Mobile/Phone Number</td><td><?php echo $mobile;?></td>
				<td>E-mail</td><td><?php echo $email;?></td>
			</tr>
			<tr >
				<td>Category</td>
				<td >
					<?php echo $category;?>
				</td>
				<!--<td >Aadhar Number :</td>
				<td><?php echo $this->uid;?></td>-->
				
			</tr>
			<!---<tr>
				<td>Blood Group :</td>
				<td colspan=4><?php echo $this->bgroup;?></td>
			</tr>--->
		</table>

		<table class="TFtable" id="padd">
			<thead>
			<tr>
				<th><span style="float:left;">Address</span></th>
				<th><span style="float:left;">Permanent Address</span></th>
				<th colspan=2><span style="float:left;">Correspondence Address</span></th>
			</tr>
			</thead>

			<tbody>
				<tr>
					<td>H.No./Apartment</td><td><?php echo $paddress; ?></td>
					<td colspan=2><?php echo $caddress;?></td>
				</tr>
				<tr>
					<td>Street/Village/Taluka/city</td><td><?php echo $pcity;?></td>
					<td colspan=2><?php echo $ccity;?></td>
				</tr>
				<!---<tr>
					<td>Post office</td><td><?php echo $this->ppost;?></td>
					<td colspan=2><?php echo $this->cpost;?></td>
				</tr>--->
				
				<tr>
					<td>State</td><td><?php echo $pstate;?></td>
					<td colspan=2><?php echo $cstate;?></td>
				</tr>
				<tr>
					<td>District</td><td><?php echo $pcountry;?></td>
					<td colspan=2><?php echo $ccountry;?></td>
				</tr>
				<tr>
					<td>Pincode</td><td><?php echo $ppincode;?></td>
					<td colspan=2><?php echo $cpincode;?></td>
				</tr>
				
			</tbody>	
		</table>
		<!---<table class="TFtable" id="subCat">

			<tr>
				<td width="360"></td><td>Defence<Input type="checkbox"></td><td>Sports<Input type="checkbox"></td>
				<td>NSS<Input type="checkbox"></td><td>NCC<Input type="checkbox"></td>
				<td>Kashmiri Migrants<Input type="checkbox"></td>
			</tr>
		</table>--->
	
		<!--<div id="gap">	
		<b>Note:GOI format of caste certificate should be submitted at the time of physical verification documents.</b>
		</div>-->	
</br>
		<table class="TFtable" id="academic">	
			<thead id="styleTable"><th colspan=7 style="text-align:justify;">
			Academic Detail
			</th></thead>
			<thead id="acadhead2">
			<tr>
				<th><span style="float:left;">Programmes</span></th>
				<th><span style="float:left;">Board/University</span></th>
				<th><span style="float:left;">Year of completion</span></th>
				<th><span style="float:left;">Passed/Appeared</span></th>
				<th><span style="float:left;">Marks Obtained</span></th>
				<th><span style="float:left;">Max. Marks</span></th>
				<th><span style="float:left;">Percentage</span></th>
			</tr>	
			</thead>
						
				<tbody>
				<?php foreach ($admission_academic as $row) {?>				
				<tr><td><?php echo $row->asedu_class;?></td>
				<td><?php  echo $row->asedu_board; ?></td>
				<td><?php echo $row->asedu_passingyear; ?></td>
				<td><?php echo $row->asedu_resultstatus; ?></td>
				<td><?php echo $row->asedu_marksobtained; ?></td>
				<td><?php echo $row->asedu_maxmarks; ?></td>
				<td><?php echo $row->asedu_percentage; ?></td></tr>
				<?php }?>
								
			</tbody>
		</table>
	</br>
		<table class="TFtable" id="academic">	
			<thead id="styleTable"><th colspan=7 style="text-align:justify;">
			Enterance Exam Detail
			</th></thead>
			<thead id="acadhead2">
			<tr>
				<th><span style="float:left;">Exam Name</span></th>
				<th><span style="float:left;">Roll Number</span></th>
				<th><span style="float:left;">Exam Rank</span></th>
				<th><span style="float:left;">Exam Score</span></th>
				<th><span style="float:left;">Exam State</span></th>
				<!--<th><span style="float:left;">Exam Subjects</span></th>
				<th><span style="float:left;">Exam Passing Year</span></th>-->
			</tr>	
			</thead>
						
				<tbody>
				<?php foreach ($admission_entexm as $row) {?>				
				<tr><td><?php echo $row->aseex_examname;?></td>
				<td><?php  echo $row->aseex_rollno; ?></td>
				<td><?php echo $row->aseex_rank; ?></td>
				<td><?php echo $row->aseex_score; ?></td>
				<td><?php echo $row->aseex_state; ?></td>
				<!--<td><?php //echo $row->aseex_subject; ?></td>
				<td><?php //echo $row->aseex_passingyear; ?></td>
				-->
			</tr>
				<?php }?>
								
			</tbody>
		</table>

		<table class="TFtable" id="academic">	
			<thead id="styleTable"><th colspan=7 style="text-align:justify;">
			Employement Detail
			</th></thead>
			<thead id="acadhead2">
			<tr>
				<th><span style="float:left;">Office Name</span></th>
				<th><span style="float:left;">Post Name</span></th>
				<th><span style="float:left;">Basic Pay</span></th>
				<th><span style="float:left;">Appointment Nature</span></th>
				<th><span style="float:left;">Date of Joining</span></th>
				<!--<th><span style="float:left;">Exam Subjects</span></th>-->
				<th><span style="float:left;">Remrks</span></th>
				<th><span style="float:left;">Total Experience</span></th>
			</tr>	
			</thead>
						
				<tbody>
				<?php foreach ($admission_employment as $row) {?>				
				<tr><td><?php echo $row->asemp_officename;?></td>
				<td><?php  echo $row->asemp_post; ?></td>
				<td><?php echo $row->asemp_pay; ?></td>
				<td><?php echo $row->asemp_appoinmentnature; ?></td>
				<td><?php echo $row->asemp_doj; ?></td>
				<!--<td><?php //echo $row->asemp_dol; ?></td>-->
				<td><?php echo $row->asemp_remarks; ?></td>
				<td><?php echo $row->asemp_experience; ?></td>	
			</tr>
				<?php }?>
								
			</tbody>
		</table>

		</br>
		<table class="TFtable" id="feegap">
			<thead id="styleTable"><th colspan=6  style="text-align:justify;">
			<span>Fees Detail</span></th></thead>
			<thead id="acadhead2"><tr>
			<th><span style="float:left;">Fees Type</span></th>
			<th><span style="float:left;">Amount</span></th>
			<th><span style="float:left;">Payment Method</span></th>
			<th><span style="float:left;">Refference Number</span></th>
			<th><span style="float:left;">Fees Id</span></th>
			<th><span style="float:left;">Bank Name</span></th>
			</tr></thead>
			<tbody><tr>
			<?php foreach($admission_fees as $row){?>
				<td><?php echo $row->asfee_feename;?></td>
				<td><?php echo $row->asfee_feeamount;?></td>
				<td><?php echo $row->asfee_paymentmethod;?></td>
				<td style="width:20%;"><?php echo $row->asfee_referenceno;?></td>
				<td><?php echo $row->asfee_id;?></td>
				<td><?php echo $row->asfee_bankname; ?></td>
			<?php }?>
			</tr>
			</tbody>
		</table>
	</div>

	<div id="formbody2">
		</br>
		
		<!--<table id="formbody2">
			<thead id="styleTable"><th colspan=7>Terms & Conditions</th></thead>
			<tbody>
				<tr><td>
					<?php //$this->load->view('student/studentCrieteria2');?>
				</td></tr>
			</tbody>
		</table>--->

		<table id="sign" style="width:98%;">
			<tr>
			<td style="float:right;">
				<label class="signlabel">Student Signature</label></br>
				<img src="<?php echo base_url('uploads/SLCMS/enterence/'.$id.'/'.$signature); ?>" ></td>
				
			</tr>
		</table>

	<?php //}/*end for each*/ ?>
<table><tr><td>

<a href="<?php echo site_url('enterence/step_fivedw'); ?>" style="text-decoration:none;"><input type="submit" value="Save" title="Click for save"  id="b1"></a>
</td>
<td>

 <input type="submit" value="Print" onclick="myFunction()" title="Click for print" id="b1">

</td>
<td>
<form action="<?php echo site_url('enterence/home'); ?>" method="POST">
 <input type="submit" name="submit" value="Home" title="Click for home" id="b1">
</form>
</td>
</tr></table>
	</div>	

</center>
</div>
</page>
<!--------------------------------------------------------------------------------------------------------------------------------------------------->
<div id="foohide">
<?php $this->load->view('template/footer'); ?>
</div>
<?php // } //else {  header("location:student/student_step0"); }
//else{header("location:".base_url()."Student/Step0");}?>
</body>
</html>