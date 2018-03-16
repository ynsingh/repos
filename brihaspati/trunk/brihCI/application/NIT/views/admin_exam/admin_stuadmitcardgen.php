<!-- -----------------------------------------------------
    -- @name admin_stu_admincardgen.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
    -- @author Neha Khullar(nehukhullar@gmail.com) -- Modifications
------------------------------------------------------- -->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?><!DOCTYPE html>
<html>
<head>
<title>Student Admit Card Generation</title>
<!--<link rel="shortcut icon" href="<?php //echo base_url('assets/images'); ?>/index.jpg">-->
	<link rel="icon" href="<?php echo base_url('uploads/logo'); ?>/nitsindex.png" type="image/png">	

	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>

	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/message.css">
        <link rel="stylesheet" type="text/css" media="all" href="<?php echo base_url(); ?>assets/css/style.css" />
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
	
</head>

<body>

<?php $this->load->view('template/header'); ?>

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
<table style="width:100%;font-size:20px;">
<tr>
	<td align=left><a href="<?php echo site_url('adminstuexam/admitcardgen');?>" style="text-decoration:none;" title="Click To Generate Admit Card Then Click To Download Admit Card"><b>Generate Admit Card</b></a></td>
	<td align=left><b>Student Admit Card Generation</b>
	</td>
</tr>
</table>
<?php //site_url();//echo base_url();?>
<div class="scroller_sub_page">
<table style="width:100%;" class="TFtable">
	<thead>
	<tr>
		<th>Sr. No.</th><!--<th>Center Name</th>--><th>Name of Exam</th><th>Student Name</th><th>Course Name</th><th>Roll No.</th><th>Enrollment No.</th>
		<th>Action</th>
	</tr>
	</thead>

	<tbody>
		<?php
			$orgid = 0;
			//$count = 1;
			if(!empty($stud_program)){
				foreach($stud_program as $row){
					$smid = $row->sp_smid;
					$deptid = $row->sp_deptid;
					$year = $currentacadyear;
					$stu_stp5 = $this->commodel->get_listspfic1('student_admissionstep','step5_status','student_masterid',$smid)->step5_status;
			$scid = $this->commodel->get_listspfic1('student_master','sm_sccode','sm_id',$smid)->sm_sccode;
				if($orgid != $scid){
					echo "<tr>";
					echo "<td colspan=13 style='text-align:center;font-size:18px;'>";
					echo "<b>University Name : </b>";
                      			echo $this->commodel->get_listspfic1('org_profile','org_name','org_id',$scid)->org_name;
					echo "</td>";
					echo "</tr>";
				$orgid = $scid; 
				$count =1;				
				}
		?>
		<tr>
			<td><?php echo $count++; ?></td>
			<?php //$scid = $this->commodel->get_listspfic1('student_master','sm_sccode','sm_id',$smid)->sm_sccode;?>
			<!--<td><?php //echo $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$scid)->sc_name;?></td>-->
			<td><?php echo $this->commodel->get_listspfic1('student_entry_exit','senex_entexamname','senex_smid',$smid)->senex_entexamname;?></td>
			<td><?php echo $this->commodel->get_listspfic1('student_master','sm_fname','sm_id',$smid)->sm_fname;?></td>
			<?php $prgid = $this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$smid)->sp_programid;?>
			<td><?php echo $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name.'( '. $this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch.' )';?></td>
			<td><?php echo $this->commodel->get_listspfic1('student_entry_exit','senex_rollno','senex_smid',$smid)->senex_rollno;?></td>
			<td><?php echo $this->commodel->get_listspfic1('student_master','sm_enrollmentno','sm_id',$smid)->sm_enrollmentno;?></td>
			<?php 
			$dirpath = 'uploads/SLCMS/adminstudent_exam/'.$year.'/admit_card/'.$deptid.'/'.$smid.'.pdf';
			//if($stu_stp5 == 1){
			if (file_exists($dirpath)) {
			?>
				<td><a href="<?php echo base_url($dirpath);?>" style="text-decoration:none;" download>Download Admit Card</a></td>
			<?php }else{?><td><b>No File To</br> Download !</b></td><?php }?>
		</tr>
		<?php }}else{ ?><td colspan=8 style="text-align:center;"><b>No Record Found !!!!</b></td><?php }?>
	</tbody>
</table>
</div>


<?php $this->load->view('template/footer'); ?>
</body>
</html>
