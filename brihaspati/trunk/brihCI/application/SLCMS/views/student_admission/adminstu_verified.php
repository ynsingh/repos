<!-------------------------------------------------------
    -- @name stu_verified.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
--------------------------------------------------------->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?><!DOCTYPE html>
<html>
<head>
	<title>Student Data Verification</title>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/message.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/tablestyle.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/bootstrap.min.css">
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>

</head>

<body>
<?php $this->load->view('template/header'); ?>
<table style="width:100%;font-size:18px;">
	<tr>
		<td align=center><b>Student Verified Data</b></td>
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
	   if(!empty($_SESSION['success'])){		
		if(isset($_SESSION['err_message'])){?>
        <div class="isa_error"><div ><?php echo $_SESSION['err_message'];?></div></div>
        <?php
         } };
	?>  
</br>

   <form action="<?php echo site_url('adminadmissionstu/adminstu_verified'); ?>" method="POST">
   	<table style="width:80%;">
		<tr>
		<td align=left style="font-size:18px;">
			<a href="<?php echo site_url('adminadmissionstu/adminstu_nonverified/');?>" style="">Non Verified Student
			<a href="<?php echo site_url('adminadmissionstu/adminstu_verified/');?>" style=" margin-left:5%;">Verified Student
		</td>
			<td align=left>
				<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery-ui.min.css">
  				<script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.min.js" ></script>
        			<input type="text" name="stu_sdate" placeholder="Start Date" value="<?php echo isset($_POST["stu_sdate"]) ? $_POST["stu_sdate"] : ''; ?>" id="sdate" required style="height:30px;font-size:18px;" size=20 />
        
            			<script>
               				 $('#sdate').datepicker({
                			 onSelect: function(value, ui) {
                    			 	console.log(ui.selectedYear)
                    			 	var today = new Date(), 
                    			 	dob = new Date(value), 
                   				age = 2017-ui.selectedYear;
			                 },
                
                    			changeMonth: true,
                    			changeYear: true,
                    			dateFormat: 'yy-mm-dd',
                   
                   			yearRange: 'c-36:c+10',
                			});
            			</script>
	
						
        		<input type="text" name="stu_edate" placeholder="End Date" value="<?php echo isset($_POST["stu_edate"]) ? $_POST["stu_edate"] : ''; ?>" id="edate" required style="height:30px;font-size:18px;" size=20 />
        
            			<script>
               				 $('#edate').datepicker({
                			 onSelect: function(value, ui) {
                    			 	console.log(ui.selectedYear)
                    			 	var today = new Date(), 
                    			 	dob = new Date(value), 
                   				age = 2017-ui.selectedYear;
			                 },
                
                    			changeMonth: true,
                    			changeYear: true,
                    			dateFormat: 'yy-mm-dd',
                   
                   			 yearRange: 'c-36:c+10',
                			});
            			</script>
			<input type="submit" name="search" value="Search" style="height:30px;font-size:18px;"></td>
		
		</tr>
	</table>
   </form>
</br>
<div class="scroller_sub_page">
<table style="width:100%;" class="TFtable">
	<thead>
		<tr>
			<th>Sr. No.</th><th>Hall Ticket Number</th><th>Student Name</th><th>DOB</th><th>Father Name</th><th>Mother Name</th>
			<th>Email-id</th><th>Mobile No.</th><th>Category</th><th>Program & Branch</th><th>Status</th>
		</tr>
	</thead>
	<tbody>
	<?php 
	$count = 1;	

	if(!empty($stusmid)){
		foreach($stusmid as $row){
			$smid = $row->sas_studentmasterid;
			$halltino = $row->sas_hallticketno;
	?>
		<tr>
			<?php if(!empty($halltino)){?><!-------------Check student record not in admission status----------------->
		
			<td><?php echo $count++;?></td>
			<td><?php echo $halltino;?></td>
			<td><?php echo $this->commodel->get_listspfic1('student_master','sm_fname','sm_id',$smid)->sm_fname;?></td>
			<td><?php echo $this->commodel->get_listspfic1('student_master','sm_dob','sm_id',$smid)->sm_dob;?></td>
			<td><?php echo $this->commodel->get_listspfic1('student_parent','spar_fathername','spar_smid',$smid)->spar_fathername;?></td>
			<td><?php echo $this->commodel->get_listspfic1('student_parent','spar_mothername','spar_smid',$smid)->spar_mothername;?></td>
			<td><?php echo $this->commodel->get_listspfic1('student_master','sm_email','sm_id',$smid)->sm_email;?></td>
			<td><?php echo $this->commodel->get_listspfic1('student_master','sm_mobile','sm_id',$smid)->sm_mobile;?></td>
			<?php $cateid = $this->commodel->get_listspfic1('student_master','sm_category','sm_id',$smid)->sm_category;?>
			<td><?php echo $this->commodel->get_listspfic1('category','cat_name','cat_id',$cateid)->cat_name;?></td>
			<?php $prgbranid = $this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$smid)->sp_programid;?>
			<td><?php echo $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgbranid)->prg_name.'( '.$this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgbranid)->prg_branch.')';?></td>
			<?php 
			$verif = $this->commodel->get_listspfic1('student_admissionstatus','sas_admissionstatus','sas_studentmasterid',$smid)->sas_admissionstatus;

			if($verif == "Provisional"){
			?>
			<!-- Trigger the modal with a button -->
			<td>
			<a href="<?php echo site_url('adminadmissionstu/adminstu_verifidata/').$smid; ?>" >
				<button type="button" class="btn btn-info btn-sm" style="font-size:15px;width:100%;"><b>Show Data</b></button></a></td>
			<?php }
			elseif($verif == "Confirmed"){
			?>
			<td>
			<button type="button" class="btn btn-success btn-sm" style="font-size:15px;width:100%;"><b>Verified</b></button>
			<!--<a href="<?php echo site_url('admissionstu/stu_verifidata/').$smid; ?>" ></a>--></td>
			<?php }?>
			
			<?php }else{?><td colspan=12></td><?php }?><!-------------Check student record not in admission status----------------->
		</tr>
	<?php }} ?>

<?php 
	$count = 1;
if(!empty($stu_smid)){
		foreach($stu_smid as $row){
		$smid = $row->sas_studentmasterid;
		$halltino = $row->sas_hallticketno;
		//print_r($newsmid);
		
	?>
		<tr>
			<?php if(!empty($halltino)){?><!-------------Check student record not in admission status----------------->
		
			<td><?php echo $count++;?></td>
			<td><?php echo $halltino;?></td>
			<td><?php echo $this->commodel->get_listspfic1('student_master','sm_fname','sm_id',$smid)->sm_fname;?></td>
			<td><?php echo $this->commodel->get_listspfic1('student_master','sm_dob','sm_id',$smid)->sm_dob;?></td>
			<td><?php echo $this->commodel->get_listspfic1('student_parent','spar_fathername','spar_smid',$smid)->spar_fathername;?></td>
			<td><?php echo $this->commodel->get_listspfic1('student_parent','spar_mothername','spar_smid',$smid)->spar_mothername;?></td>
			<td><?php echo $this->commodel->get_listspfic1('student_master','sm_email','sm_id',$smid)->sm_email;?></td>
			<td><?php echo $this->commodel->get_listspfic1('student_master','sm_mobile','sm_id',$smid)->sm_mobile;?></td>
			<?php $cateid = $this->commodel->get_listspfic1('student_master','sm_category','sm_id',$smid)->sm_category;?>
			<td><?php echo $this->commodel->get_listspfic1('category','cat_name','cat_id',$cateid)->cat_name;?></td>
			<?php $prgbranid = $this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$smid)->sp_programid;?>
			<td><?php echo $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgbranid)->prg_name.'( '.$this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgbranid)->prg_branch.')';?></td>
			<?php 
			$verif = $this->commodel->get_listspfic1('student_admissionstatus','sas_admissionstatus','sas_studentmasterid',$smid)->sas_admissionstatus;

			if($verif == "Provisional"){
			?>
			<!-- Trigger the modal with a button -->
			<td>
			<a href="<?php echo site_url('adminadmissionstu/adminstu_verifidata/').$smid; ?>" >
				<button type="button" class="btn btn-info btn-sm" style="font-size:15px;width:100%;"><b>Show Data</b></button></a></td>
			<?php }
			elseif($verif == "Confirmed"){
			?>
			<td>
			<button type="button" class="btn btn-success btn-sm" style="font-size:15px;width:100%;"><b>Verified</b></button>
			<!--<a href="<?php echo site_url('admissionstu/stu_verifidata/').$smid; ?>" ></a>--></td>
			<?php }?>
			
			<?php }else{?><td colspan=12></td><?php }?><!-------------Check student record not in admission status----------------->
		</tr>
	<?php }}?>		
	
	</tbody>
</table>
</div>  


<?php $this->load->view('template/footer'); ?>
</body>
</html>
