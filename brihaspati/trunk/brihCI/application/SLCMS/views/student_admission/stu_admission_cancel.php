<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name stu_admission_cancel.php
  @author Sumit Saxena[sumitsesaxena@gmail.com]

 -->
<html>
<title>Student Admission Cancellation</title>
    <head>    
        <?php $this->load->view('template/header'); ?>
        <?php //$this->load->view('template/menu');?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
	
	<style>
		input[type=text]{width:60%;}
		select{width:100%;}
	</style>	
    </head>
    <body>
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table><br>-->
<center><span style="font-size:20px;"><b>Student Admission Cancellation</b></span></center>
 <?php echo validation_errors('<div class="isa_warning">','</div>');?>
        <?php echo form_error('<div class="">','</div>');?>
        <?php if(isset($_SESSION['success'])){?>
        <div class="isa_success"><?php echo $_SESSION['success'];?></div>
        <?php
    	 };
       	?>
	
        <?php if(isset($_SESSION['err_message'])){?>
             <div class="isa_error"><div ><?php echo $_SESSION['err_message'];?></div></div>
        <?php
        };
	?>  
  

	<form action="<?php echo site_url('adminadmissionstu/stu_admissioncancel'); ?>"  method="POST">
		<table style="width:100%;">
			<tr>
				<td><input type="text" name="halltinumber" style="width:20%;" placeholder="Enter Hall Ticket Number" value="<?php echo isset($_POST["halltinumber"]) ? $_POST["halltinumber"] :  ''; ?>" required><input type="submit" name="cancelsearch" value="Search"></td>
				<td></td>
			</tr>
		</table>
	</form>

<form action="<?php echo site_url('adminadmissionstu/stu_addadmissioncancel'); ?>" method="POST">
				
	<?php if(isset($getstuid)){?>
			<table style="width:100%;"  class="TFtable">
			<thead>
				<tr>
					<th>Hall Ticket Number  </th><th>Student Name </th><th>Prgrame & Branch</th><th>Department </th>
					<th>Taken Seats </th><th>Seats Filled </th>
				</tr>
			</thead>
			<?php if(!empty($getstuid)){
				foreach($getstuid as $row){
				$hallnumber = $this->commodel->get_listspfic1('student_admissionstatus','sas_hallticketno','sas_studentmasterid',$row->sas_studentmasterid)->sas_hallticketno;
			?>
					<tr>
						<td> <?php echo $hallnumber;?></td>
						<td> <?php echo $this->commodel->get_listspfic1('student_master','sm_fname','sm_id',$row->sas_studentmasterid)->sm_fname;?></td>
						<?php $prgid=$this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$row->sas_studentmasterid)->sp_programid;
						
					if(!empty($prgid)){							
							$pname=$this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name;
							$pbranch=$this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch;
						?>
						
						<td> <?php echo $pname.'( '.$pbranch.' )';?></td>
						
						
						<?php
						
							$deptid=$this->commodel->get_listspfic1('student_program','sp_deptid','sp_smid',$row->sas_studentmasterid)->sp_deptid;
						
							$deptname=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$deptid)->dept_name;
						?>
						<td> <?php echo $deptname;?></td>
					<?php }else{?><td>Program and branch record not available</td>
						<td>Department record not available</td>
					<?php }?>

						<td> <?php echo $this->commodel->get_listspfic1('program','prg_seat','prg_id',$prgid)->prg_seat;?></td>
						<?php $whdata = array('sas_prgid' => $prgid);
						$leftseats = $this->commodel->get_listarry('student_admissionstatus','sas_studentmasterid',$whdata);				
						$seat = count($leftseats);?>
						<td><?php echo $seat;?> </td>

		
					</tr>

					<tr>
						<td colspan=1>Reason </br><textarea name="stu_canreason" style="width:100%;" valus="<?php echo isset($_POST["stu_canreason"]) ? $_POST["stu_canreason"]  : ''; ?>"> </textarea></td>

						<td colspan=5 valign=top>Fees Refund Amount </br><input type="number" min="0" name="stu_canfeerefund" valus="<?php echo isset($_POST["stu_canfeerefund"]) ? $_POST["stu_canfeerefund"]  : ''; ?>" style="width:25%;height:50px;"> </textarea></td>
					</tr>
					
					<tr>
						<?php if(!empty($deptid)){?>
						<input type="hidden" name="stu_deptid" value="<?php echo $deptid;?>" readonly><?php }?>
						<input type="hidden" name="stu_smid" value="<?php echo $row->sas_studentmasterid;?>" readonly>
						<input type="hidden" name="stu_hallticketno" value="<?php echo $hallnumber;?>" readonly>
						<input type="hidden" name="stu_prgid" value="<?php echo $prgid;?>" readonly>
	
						<td colspan=6><center><input type="submit" name="stu_cancel" value="Admission Cancel"></enter></td>
					</tr>

			</table>
</form>
	<?php }}}?>	

	<table style="width:100%;" class="TFtable">
		<thead>
			<tr>
				<th>Sr. No.</th><th>Hall Ticket Number</th><th>Student Name</th><th>Program(Branch)</th><th>Reason</th><th>Fees Refund Amount</th>
				<th>Student Admission Status</th>
				<th>Cancel Date</th><th>Action</th>
			</tr>
			<tbody>
				<?php 
				$count=1;

				if(!empty($stu_cancel)){
					foreach($stu_cancel as $data){
				?>	
				<tr>
					<td><?php echo $count++; ?></td>
					<td><?php echo $data->sac_hallticketno;?></td>
					<td><?php echo $this->commodel->get_listspfic1('student_master','sm_fname','sm_id',$data->sac_smid)->sm_fname;?></td>
					<?php	$pname=$this->commodel->get_listspfic1('program','prg_name','prg_id',$data->sac_progid)->prg_name;
						$pbranch=$this->commodel->get_listspfic1('program','prg_branch','prg_id',$data->sac_progid)->prg_branch;?>	
					<td><?php echo $pname.'( '.$pbranch.' )'?></td>
					<td><?php echo $data->sac_reson;?></td>
					<td><?php echo $data->sac_feesrefundamount;?></td>
					<td><?php echo $this->commodel->get_listspfic1('student_admissionstatus','sas_admissionstatus','sas_studentmasterid',$data->sac_smid)->sas_admissionstatus;?></td>
					<td><?php echo $data->sac_canceldate;?></td>
					<td><a href="<?php echo site_url('adminadmissionstu/stu_cancelreceiptpdfdw/');echo $data->sac_smid;?>">Download</a></td>
				</tr>	
				<?php }}?>
			</tbody>
		</thead>
	</table>
	

 </div><?php $this->load->view('template/footer'); ?></div>
    </body>
</html>

