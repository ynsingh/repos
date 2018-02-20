<!--
 * @name adminstu_formverifie.php
   @author sumit saxena (sumitsesaxena@gmail.com)
 --->

<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?><!DOCTYPE html>
<html>
<head>
<title>Student Form Verification</title>
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>

	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/message.css">
        <link rel="stylesheet" type="text/css" media="all" href="<?php echo base_url(); ?>assets/css/style.css" />
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
	
</head>

<body>

<?php $this->load->view('template/header'); ?>
<table style="width:100%;font-size:20px;">
<tr>
	<!--<td align=left><a href="<?php echo site_url('adminstuexam/attendancesheetgenpdf');?>" style="text-decoration:none;"><b>Generate Exam-Attendence Sheet</b></a></td>-->
	<td style="text-align:center;"><b>Student Exam-Form verification</b>
	</td>
</tr>
</table>
<?php echo validation_errors('<div class="isa_warning">','</div>');?>
        <?php echo form_error('<div class="">','</div>');?>
        <?php 
	    if(!empty($_SESSION['success'])){	
		if(isset($_SESSION['success'])){?>
         <div class="isa_success" style="font-size:18px;"><?php echo $_SESSION['success'];?></div>
         <?php
          } };
         ?>
        <?php 
	   if(!empty($_SESSION['err_message'])){		
		if(isset($_SESSION['err_message'])){?>
        <div class="isa_error"><div ><?php echo $_SESSION['err_message'];?></div></div>
        <?php
         } };
	?>  

<table style="font-size:20px;">
<form action='<?php echo site_url('adminstuexam/formverifie'); ?>' method="POST">
	<tr>
		<td align=left><b>Verify Exam-Forms : </b></td>
		<td align=left>
			<select name="study_center" style="height:30px;width:100%;font-size:18px;" required>
				<option disabled selected>Select Study Center</option>
				<?php 
					foreach($sclist as $row){
				?>
						<option value="<?php echo $row->sc_id;?>"><?php echo $row->sc_name;?></option>
				<?php 	}?>
			</select>
		</td>
		<td align=left>
			<input type="submit" name="search" value="Search" style="font-size:18px;">
		</td>
		<tr>
			<td colspan=3 align=right><span style="font-size:12px;">Select study center then click search button to view all student forms.</span></td>
		</tr>
	</tr>
</form>

</table>


<div class="scroller_sub_page">
	<table style="width:100%;font-size:19px;" border=0 class=TFtable>
	<thead>
		<tr><th>Sr. No.</th><th>University Name</th><th>Programme Name</th><th>Action</th></tr>
	</thead>
	<tbody>
	<tr>
		<?php
			$year = $currentacadyear;
			$i =1;

		foreach($getverifie as $row){
			$prgid1=$row->sp_programid;
			$wharray = array('sp_programid' => $prgid1);
			$sdata = 'sp_smid';		
             		$getstuid = $this->commodel->get_listspficemore('student_program',$sdata,$wharray);

		foreach($getstuid as $row){
			$smid  = $row->sp_smid;
			$stcid = $this->commodel->get_listspfic1('student_program','sp_sccode','sp_smid',$smid)->sp_sccode;
			$cname = $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$stcid)->sc_name;
			$deptid = $this->commodel->get_listspfic1('student_program','sp_deptid','sp_smid',$smid)->sp_deptid;
			$prgid  = $this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$smid)->sp_programid;
			$prgname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name.'( '.$this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch.' )';
			
			}
		$formurl = 'uploads/SLCMS/adminstudent_exam/'.$year.'/form_verifiesheet/'.$stcid.'/'.$deptid.'/'.$prgid.'.pdf';
		if(file_exists($formurl)) {
		?>
		<td><?php echo $i++;?></td>
		<td><?php echo $cname;?></td>
		<td><?php echo $prgname;?></td>
		<td>

		<a href="<?php echo base_url($formurl);?>" title="Click To View Exam Form" target=_blank>
		Click To Open PDF
			<?php //echo $cname.'('.$prgname.')';?></br>
		<!--<embed src="<?php //echo base_url($formurl);?>" type="application/pdf"   height="350px" width="100%">-->
		</a>
		</td>
				
		<?php }//$i++;
		//if($i%4 == 0){?>
	</tr>
	<!--<tr>-->
		<?php }//} ?>
	<!--</tr>-->
	</tbody>	
	</table>
</div><!------scroller div------>

  
<?php $this->load->view('template/footer'); ?>
</body>
</html>

