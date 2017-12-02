<!-- -----------------------------------------------------
    -- @name student_step5.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
------------------------------------------------------- -->
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
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/message.css">
<script>
function myFunction() {
    window.print();
}

</script>

</head>
<body>

<?php
echo "<center>";

	if($this->session->flashdata('msg')){
	echo "<div style='font-size:20px;text-align:center;background-color:#DFF2BF;width:50%;height:40px;color:green;'>";
	echo $this->session->flashdata('msg');
	echo "<div>";	
}
	if((isset($_SESSION['success'])) && ($_SESSION['success'])!=''){
		//echo "<div style=\"margin-left:30px;width:1700px;align:left;font-size:18px;height:10px;\" class=\"isa_success\">";
	echo "<table style=\"100%;font-size:18px;height:30px;border:1px solid white;\" class=\"isa_success\">";			
		echo "<tr>";
			echo "<td style='font-size:18px;float:left;'>";
				echo $_SESSION['success'];
			echo "</td>";
		echo "<tr>";
		//echo "</div>";
	echo "</table>";
	}
	if((isset($_SESSION['error'])) && (($_SESSION['error'])!='')){
		//echo "<div id='error'>";
		//echo '<div style="margin-left:40px;">'.$_SESSION['error'].'</div>';
		//echo "</div>";
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
<center>
	<div align="left" style="width:100%;font-size:18px;height:30px;">
        <?php echo validation_errors('<div class="isa_warning">','</div>');?>
        <?php echo form_error('<div style="margin-left:30px;" class="">','</div>');?>
        <?php if(isset($_SESSION[''])){?>
        <div class="alert alert-success"><?php echo $_SESSION[''];?></div>
        <?php
    	 };
       	?>
	
        <?php if(isset($_SESSION['err_message'])){?>
             <div class="isa_error" style='margin-left:30px;width:1680px;font-size:18px;'><div ><?php echo $_SESSION['err_message'];?></div></div>
        <?php
        };
	?>  
      </div>

<page size="A4">
	<div id="body">
<center>
	<div id="formbody">

	<div id="formbody1">
		<header>
		<div id="logo2">
			<img src="<?php echo base_url(); ?>uploads/logo/logo2.jpg" alt="logo">
		</div> 
		</header>
		<div class="textname">Entrance Application Form Details</div>
		<table width="100%" border=0>
		<tr><td>
			<table class="TFtable" id="personal" style="width:100%;border:0px;">
                        <tr>
                                <td>Course Applied For :</td>
                                <td><?php echo $prgname.'('.$prgbranch.')';?></td>
                        </tr>
                        <tr>
                                <td>Study Center :</td>
				<td><?php echo $scname;?></td>
			</tr><tr>
                                <td>Enterence Exam Center :</td>
                                <td><?php echo $exname ." ( ".$excode ." )";?></td>
                        </tr>
                	</table>
		</td><td>
			<div id="photo">
                        <img src="<?php echo base_url('uploads/SLCMS/enterence/'.$id.'/'.$photo); ?>" >
                  	</div>
		</td></tr>
		</table>	
		<table class="TFtable" id="personal" style="width:100%;">
			<thead id="styleTable"><th colspan=7  style="text-align:justify;">Personal Details</th></thead>
			
			
			<tr>	
				<td>Name of the candidate :</td>
				<td><?php echo $name;?></td>
				<td>E-mail :</td>
				<td><?php echo $email;?></td>
			</tr>
			<tr>
				<td>Mobile/Phone Number :</td>
				<td><?php echo $mobile;?></td>
				<td >Gender :</td>
				<td><?php echo $gender;?></td>
				
			</tr>
			<tr>
				<td>Date of birth :</td>
				<td><?php echo $dob;?></td>
				<td>Age :</td>
				<td><?php echo $age.'Years';?></td>
				
			</tr>
			<tr>
				<td>Maritial Status :</td>
				<td><?php echo $mastatus;?></td>
				<td>Category :</td>
				<td><?php echo $category;?></td>

			</tr>
			<tr>
				<td>Nationality :</td>
				<td><?php echo $nationality;?></td>
				<td>Are You Physically Handicapped ? :</td>
				<td><?php echo $phyhandi;?></td>
			</tr>
			<tr>
				<td valign=top>Religion :</td>
				<td valign=top><?php echo $religion;?></td>
				<td style="width:20%;" valign=top>Reservation :</td>
				<td style="width:30%;"><?php echo $reservation;?></td>
			</tr>	
			
			
			<tr>
				<td>Aadhar Number :</td>
				<td colspan=4><?php echo $aadhar;?></td>
			</tr>
		</table>
	
		<table class="TFtable" id="personal" style="width:100%;">
			<thead id="styleTable"><th colspan=7  style="text-align:justify;">Parent Informations</th></thead>
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
			
		</table>

		<table class="TFtable" id="padd" style="width:100%;">
			<thead id="styleTable"><th colspan=7  style="text-align:justify;">Address Details</th></thead>
			<thead>
			<tr>
				<th><span style="float:left;">Address</span></th>
				<th><span style="float:left;">Permanent Address</span></th>
				<th colspan=2><span style="float:left;">Correspondence Address</span></th>
			</tr>
			</thead>

			<tbody>
				<tr>
					<td>H.No./Apartment :</td><td><?php echo $paddress; ?></td>
					<td colspan=2><?php echo $caddress;?></td>
				</tr>
				<tr>
					<td>Street/Village/Taluka/city :</td><td><?php echo $pcity;?></td>
					<td colspan=2><?php echo $ccity;?></td>
				</tr>
				<!---<tr>
					<td>Post office</td><td><?php //echo $this->ppost;?></td>
					<td colspan=2><?php //echo $this->cpost;?></td>
				</tr>--->
				
				<tr>
					<td>State :</td><td><?php echo $pstate;?></td>
					<td colspan=2><?php echo $cstate;?></td>
				</tr>
				<tr>
					<td>Country :</td><td><?php echo $pcountry;?></td>
					<td colspan=2><?php echo $ccountry;?></td>
				</tr>
				<tr>
					<td>Pincode :</td><td><?php echo $ppincode;?></td>
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
		<table class="TFtable" id="academic" style="width:100%;">	
			<thead id="styleTable"><th colspan=7 style="text-align:justify;">
			Academic Details
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
	<?php if(!empty($admission_entexm)){ ?>
		<table class="TFtable" id="academic" style="width:100%;">	
			<thead id="styleTable"><th colspan=7 style="text-align:justify;">
			Enterance Exam Details
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
	<?php }?>

	<?php if(!empty($admission_employment)){ ?>
		<table class="TFtable" id="academic" style="width:100%;">	
			<thead id="styleTable"><th colspan=7 style="text-align:justify;">
			Employement Details
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
<?php }?>
		</br>
		<table class="TFtable" id="feegap" style="width:100%;">
			<thead id="styleTable"><th colspan=6  style="text-align:justify;">
			<span>Fees Details</span></th></thead>
			<thead id="acadhead2"><tr>
			<th><span style="float:left;">Fees Type</span></th>
			<th><span style="float:left;">Amount</span></th>
			<th><span style="float:left;">Payment Method</span></th>
			<th><span style="float:left;">Reference Number</span></th>
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
				<img src="<?php echo base_url('uploads/SLCMS/enterence/'.$id.'/'.$signature); ?>" ></br>
				<label class="signlabel">Student Signature</label></td>
				
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
