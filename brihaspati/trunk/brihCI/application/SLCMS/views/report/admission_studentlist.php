<!-------------------------------------------------------
    -- @name admission_applicant.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
--------------------------------------------------------->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?><!DOCTYPE html>
<html moznomarginboxes mozdisallowselectionprint>
<head>
<meta charset="utf-8">
	<title>Download Student Admission Details</title>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery.datetimepicker.css"/>
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>

	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/message.css">

	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">

	
<script>
function myFunction() {
    window.print();
}

</script>

</head>

<body>
<?php  // $thisPage2="studentaddDetail"; 
		$this->load->view('template/header'); ?>
        <?php $this->load->view('template/menu');?>

<p>
<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
</p>

<center><span style="font-size:20px;"><b>Search Student Admission Details</b></span></center>

<table align=center style="width:100%;">
<tr>
        <?php echo validation_errors('<div class="isa_warning">','</div>');?>
        <?php echo form_error('<div style="" class="isa_success">','</div>');?>
        <?php if(isset($_SESSION['success'])){?>
        <td class="isa_success"><?php echo $_SESSION['success'];?></td>
        <?php
    	 };
       	?>
</tr>	<tr class="isa_error">
        <?php if(isset($_SESSION['err_message'])){?>
             <td style=''><?php echo $_SESSION['err_message'];?></td>
        <?php
        };
	?>  
</tr>
   </table>	
   </table>	

   <form action="<?php echo site_url('report/admission_student'); ?>" method="POST">
   	<table>
		<tr>
			<td>
				<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery-ui.min.css">
  				<script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.min.js" ></script>
        			<input type="text" name="stadate" placeholder="Start Date" value="<?php echo isset($_POST["sdate"]) ? $_POST["sdate"] : ''; ?>" id="sdate" />
        
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
	
			</td>	

			<td>
				
        		<input type="text" name="enddate" placeholder="End date" value="<?php echo isset($_POST["edate"]) ? $_POST["edate"] : ''; ?>" id="edate" />
        
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
	
			</td>	
	
			<td><input type="submit" name="search" value="Search" style="height:30px;"></td>
		
		</tr>
	</table>
   </form>	

<?php if (isset($student_data)) {?>
      <table class="TFtable" style="width:100%;">
	<thead>
		<tr>
			<th>Sr. No.</th>
			<th>Hall Ticket Number</th>
			<th>Student Name</th>
			<th>Father Name</th>
			<th>Mother Name</th>
			<th>Gender</th>
			<th>Category</th>
			<th>Admission Taken Category</th>
			<th>Religion</th>
			<th>Address</th>
			<th>Program Category</th>
			<th>Program( Branch ) Name</th>
			<th>Admission Date </th>
			<th>Admission Status</th>
		</tr>
	</thead>
	
	<tbody>
		<?php
		$count = 1;
		if(!empty($student_data)){
			$i=0;	
			foreach($student_data as $row){
			$asmid = $row->sas_studentmasterid;
			if($i == 0){
				$admrecrd = $asmid;
				$i++;
			}else{
				$admrecrd = $admrecrd.'-'.$asmid;
				}
			
			$pestate=$this->commodel->get_listspfic1('student_parent','spar_pstate','spar_smid',$asmid)->spar_pstate;
			$peadd=$this->commodel->get_listspfic1('student_parent','spar_paddress','spar_smid',$asmid)->spar_paddress;
			$pecity=$this->commodel->get_listspfic1('student_parent','spar_pcity','spar_smid',$asmid)->spar_pcity;
			$pestate=$this->commodel->get_listspfic1('student_parent','spar_pstate','spar_smid',$asmid)->spar_pstate;				
			$pecountry=$this->commodel->get_listspfic1('student_parent','spar_pcountry','spar_smid',$asmid)->spar_pcountry;
			$address = $peadd.','.$pecity.','.$pestate.','.$pecountry ;

			$catid = $this->commodel->get_listspfic1('student_master','sm_category','sm_id',$asmid)->sm_category;

			$prgcatid = $this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$asmid)->sp_programid;
			$prgcatname = $this->commodel->get_listspfic1('program','prg_category','prg_id',$prgcatid)->prg_category;
	
			$prgname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgcatid)->prg_name;
			$prgbranch = $this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgcatid)->prg_branch;
			$prgbranch = $prgname.'( '.$prgbranch.' )';
		?>
			<tr>
				<td><?php echo $count++;?></td>
				<td><?php echo $this->commodel->get_listspfic1('student_admissionstep','application_no','student_masterid',$asmid)->application_no;?></td>
				<td><?php echo $this->commodel->get_listspfic1('student_master','sm_fname','sm_id',$asmid)->sm_fname;?></td>
				<td><?php echo $this->commodel->get_listspfic1('student_parent','spar_fathername','spar_smid',$asmid)->spar_fathername;?></td>
				<td><?php echo $this->commodel->get_listspfic1('student_parent','spar_mothername','spar_smid',$asmid)->spar_mothername;?></td>
				<td><?php echo $this->commodel->get_listspfic1('student_master','sm_gender','sm_id',$asmid)->sm_gender;?></td>

				<td><?php echo $this->commodel->get_listspfic1('category','cat_name','cat_id',$catid)->cat_name;?></td>
				<td><?php echo $this->commodel->get_listspfic1('category','cat_name','cat_id',$catid)->cat_name;?></td>

				<td><?php echo $this->commodel->get_listspfic1('student_master','sm_religion','sm_id',$asmid)->sm_religion;?></td>
				<td><?php echo $address; ?></td>
				<td><?php echo $prgcatname; ?></td>
				<td><?php echo $prgbranch?></td>
				<td><?php echo $this->commodel->get_listspfic1('student_admissionstatus','sas_admissiondate','sas_studentmasterid',$asmid)->sas_admissiondate;?></td>
				<td><?php echo $this->commodel->get_listspfic1('student_admissionstatus','sas_admissionstatus','sas_studentmasterid',$asmid)->sas_admissionstatus;?></td>
				
			</tr>		
			
	<?php }	}else{ ?><td colspan=12 style="text-align:center;">No Record Found !!!!</td><?php }?>
			<tr>

				<td colspan=14 ><center>

<?php 
	
	//foreach($student_data as $row){
			
			//$asmid1 = $row->senex_smid;?>
				<!--<a href="<?php //echo site_url('downloads/admission_studentdw/');//echo $row->senex_smid.'/';?>" style="text-decoration:none;">-->
<?php //}?>

				<!--<a href="<?php //echo site_url('downloads/admission_studentdw/');echo $admrecrd;?>" style="text-decoration:none;"><?php //}?>

				<input type="button" style="font-weight:lighter;" value="Download"></a>-->
				</center>
				</td>
			</tr>
	</tbody>
</table>

<?php }?>

<div class="scroller_sub_page">
	
</div><!------scroller div------>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</body>
</html>
