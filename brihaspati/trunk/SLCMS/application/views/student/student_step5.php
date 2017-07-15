<!-------------------------------------------------------
    -- @name student_step5.php --	
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

<?php

echo "<center>";
	if($this->session->flashdata('msg')){
	echo" <div style='font-size:20px;text-align:center;background-color:#DFF2BF;width:50%;height:30px;color:green;'>";
		echo $this->session->flashdata('msg');
	echo "<div>";
}
echo "</center>";
?>

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
		<div class="textname">Application Form</div>
		  <div id="photo">
			<img src="<?php echo base_url('uploads/student_sign_photo/student_photo/'.$this->phresult); ?>" >
		  </div>
		<table class="TFtable" id="personal">
			<thead id="styleTable"><th colspan=7>Personal Detail</th></thead>
			
			<tr>
				<td>Name of the candidate :</td>
				<td><?php echo $this->sname;?></td>
				<td>Name of the Course :</td>
			        <td><?php echo $this->pname;?></td></tr>
			
			<tr>
				<td>Mother Name :</td>
				<td><?php echo $this->mname;?></td>
				<td>Father Name :</td>
				<td><?php echo $this->fname;?></td>
			</tr>

			<tr>
				<td >Gender :</td>
				<td><?php echo $this->gender;?></td>
				<td>Date of birth :</td>
				<td><?php echo $this->dob;?></td>
			</tr>

			<tr>
					<td>Mobile/Phone Number</td><td><?php echo $this->mobile;?></td>
				
					<td>E-mail</td><td><?php echo $this->email;?></td>
			</tr>
			<tr >
				<td>Category</td>
				<td >
					<?php echo $this->catename;//echo $this->Common_model->get_listspfic1('student_master','sm_category','sm_id',$id)->sm_category;?>
				</td>
				<td >Aadhar Number :</td>
				<td><?php echo $this->uid;?></td>
				
			</tr>
			<tr>
				
				<td>Blood Group :</td>
				<td colspan=4><?php echo $this->bgroup;?></td>
			</tr>
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
					<td>H.No./Apartment</td><td><?php echo $this->padd; ?></td>
					<td colspan=2><?php echo $this->cadd;?></td>
				</tr>
				<tr>
					<td>Street/Village/Taluka/city</td><td><?php echo $this->pcity;?></td>
					<td colspan=2><?php echo $this->ccity;?></td>
				</tr>
				<tr>
					<td>Post office</td><td><?php echo $this->ppost;?></td>
					<td colspan=2><?php echo $this->cpost;?></td>
				</tr>
				<tr>
					<td>District</td><td><?php echo $this->pdist;?></td>
					<td colspan=2><?php echo $this->cdist;?></td>
				</tr>
				<tr>
					<td>State</td><td><?php echo $this->pstat;?></td>
					<td colspan=2><?php echo $this->cstat;?></td>
				</tr>
				<tr>
					<td>Pincode</td><td><?php echo $this->ppin;?></td>
					<td colspan=2><?php echo $this->cpin;?></td>
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
	
		<div id="gap">	
		<b>Note:GOI format of caste certificate should be submitted at the time of physical verification documents.</b>
		</div>	

		<table class="TFtable" id="academic">	
			<thead id="styleTable"><th colspan=7>
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
				<?php foreach ($this->seresult as $row) {?>				
				<tr><td><?php echo $row->sedu_class;?></td>
				<td><?php  echo $row->sedu_board; ?></td>
				<td><?php echo $row->sedu_passingyear; ?></td>
				<td><?php echo $row->sedu_resultstatus; ?></td>
				<td><?php echo $row->sedu_marksobtained; ?></td>
				<td><?php echo $row->sedu_maxmarks; ?></td>
				<td><?php echo $row->sedu_percentage; ?></td></tr>
				<?php }?>
								
			</tbody>
		</table>
		</br>
		<table class="TFtable" id="feegap">
			<thead id="styleTable"><th colspan=4 >
			<span >Fees Detail</span>
			</th></thead>
			<tr><td>Programme Name:</td><td><?php echo $this->prog; ?></td>
			<td>Amount:</td><td><?php echo $this->amnt;?></td><tr>
			<tr><td>Payment Method:</td><td><?php echo$this->pmethod;?></td>
			<td>Refference Number:</td><td><?php echo $this->rno;?></td><tr>
			<tr><td>Fees Id:</td><td><?php echo $this->fid;?></td>
			<td>Bank Name:</td><td><?php echo $this->bname; ?></td><tr>
		</table>
	</div>

	<div id="formbody2">
		</br>
		
		<table id="formbody2">
			<thead id="styleTable"><th colspan=7>Terms & Conditions</th></thead>
			<tbody>
				<tr><td>
					<?php $this->load->view('student/studentCrieteria2');?>
				</td></tr>
			</tbody>
		</table>

		<table id="sign">
			<tr>
			<td><div style="margin-top:100px;font-size:18px;visibility:hidden;">
				<b>(Admission for honour B.Pharm,B.ED.,PG,D.Pharm and PHd Pghgj at IGNTU/RCM)</b>
				 </div>
			</td>
			<td>
				<label class="signlabel">Student Signature</label></br>
				<img src="<?php echo base_url('uploads/student_sign_photo/student_sign/'.$this->signresult); ?>"></td>
				
			</tr>
			</table>

	<?php //}/*end for each*/ ?>
<table><tr><td>
 <input type="submit" value="Print Your Detail" onclick="myFunction()" title="Click for print" id="b1">
</td>
<td>
<form action="<?php echo site_url('Student/student_home'); ?>" method="POST">
	 <input type="submit" name="studentHome" title="Click for home page" value="Student Home" id="b2">
</form>	
</td></tr></table>
	</div>	

</center>
</div>
</page>
<!--------------------------------------------------------------------------------------------------------------------------------------------------->
<div id="foohide">
<?php $this->load->view('template/footer'); ?>
</div>
<?php  } //else {  header("location:student/student_step0"); }
else{header("location:".base_url()."Student/Step0");}?>
</body>
</html>
