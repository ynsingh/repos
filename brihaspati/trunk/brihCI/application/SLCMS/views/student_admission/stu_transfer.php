<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name stu_transfer.php 
  @author Sumit Saxena[sumitsesaxena@gmail.com]

 -->
<html>
<title>Student Transfer</title>
    <head>    
        <?php $this->load->view('template/header'); ?>
        <?php $this->load->view('template/menu');?>
	
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
	<script>
	function getdeptname(branch){
		var branch = branch;
		//alert (branch);
                $.ajax({
                type: "POST",
                url: "<?php echo base_url();?>slcmsindex.php/admissionstu/deptlist",
                data: {"stu_prgname"  : branch},
                dataType:"html",
                success: function(data){
                $('#stu_departname').html(data.replace(/^"|"$/g, ''));
                }
            }); 
        }

	function geteligible(eligible){
		var eligible = eligible;
		//alert (eligible);
                $.ajax({
                type: "POST",
                url: "<?php echo base_url();?>slcmsindex.php/admissionstu/getminquali",
                data: {"stu_prgname"  : eligible},
                dataType:"html",
                success: function(data){
                $('#stu_eligble').html(data.replace(/^"|"$/g, ''));
                }
            }); 
        }
	</script>
	<style>
		input[type=text]{width:100%;}
		select{width:100%;}
	</style>	
    </head>
    <body>
<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
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
  
</br>
<center><span style="font-size:20px;"><b>Student Transfer</b></span></center>
	<form action="<?php echo site_url('admissionstu/student_transfer'); ?>"  method="POST">
		<table style="width:100%;">
			<tr>
				<td><input type="text" name="hallnumber" style="width:20%;" placeholder="Enter Hall Ticket Number" value="<?php echo isset($_POST["hallnumber"]) ? $_POST["hallnumber"] :  ''; ?>" required><input type="submit" name="hnsearch" value="Search"></td>
				<td></td>
			</tr>
		</table>
	</form>
	<?php //if(isset($getstuid)){?>
		<table style="width:100%;" border=0>
			<tr><td valign=top>

<!--------------------------------------------DISPLAY STUDENT SEARCH DATA------------------------------------------------------->
			<table style="width:100%;"  class="TFtable">
			<?php if(!empty($getstuid)){
				foreach($getstuid as $row){
			?>
					<tr>
						<td><b>Hall Ticket Number</b></td><td> <?php echo $this->commodel->get_listspfic1('student_admissionstatus','sas_hallticketno','sas_studentmasterid',$row->sas_studentmasterid)->sas_hallticketno;?></td>
					</tr>
					<tr>

						<td><b>Student Name  </td><td> <?php echo $this->commodel->get_listspfic1('student_master','sm_fname','sm_id',$row->sas_studentmasterid)->sm_fname;?></td>
					</tr>
					<tr>
						<?php $prgid=$this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$row->sas_studentmasterid)->sp_programid;
						if(!empty($prgid)){
							$pname=$this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name;
							$pbranch=$this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch;
?>
						<td><b>Program & Branch </b> </td><td> <?php echo $pname.'( '.$pbranch.' )';?></td>
					</tr>
					<?php }else{?><tr><td><b>Program & Branch</b>  </td><td>Program and branch record not available</td></tr>

					<?php }?>

					<?php
						$deptid=$this->commodel->get_listspfic1('student_program','sp_deptid','sp_smid',$row->sas_studentmasterid)->sp_deptid;
					if(!empty($deptid)){
						$deptname=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$deptid)->dept_name;
					?>
					<tr>
						<td><b>Department  </b></td><td> <?php echo $deptname;?></td>
					</tr>
					<?php }else{?><tr><td><b>Department</b>  </td><td>Department record not available</td></tr>

					<?php }?>	
					<tr>	
						<td><b> Taken Seats </b> </td><td> <?php echo $this->commodel->get_listspfic1('program','prg_seat','prg_id',$prgid)->prg_seat;?></td>
						
					</tr>
					<tr>
						<?php $whdata = array('sas_prgid' => $prgid);
						$leftseats = $this->commodel->get_listarry('student_admissionstatus','sas_studentmasterid',$whdata);					
		
						$seat = count($leftseats);?>
						<td><b>Seats Filled </b> </td><td> <?php echo $seat;?></td>
					</tr>
					<?php $whdata = array('admop_prgname_branch' => $prgid);
					$eligible = $this->commodel->get_distinctrecord('admissionopen','admop_min_qual',$whdata);
					//$eligible = $this->commodel->get_listspfic2('admissionopen','admop_min_qual','','admop_prgname_branch',$prgid,'admop_min_qual');
					//print_r($eligible);			
					?>	
					<tr>
						<?php 
							if(!empty($eligible)){
							foreach($eligible as $data){?>
							<td><b>Eligibility Criteria </b> </td><td> <?php echo $data->admop_min_qual; ?></td>
						<?php }}else{ ?><td>Eligibility Criteria  </td><td>Elgiblity Criteria Not Found</td><?php }?>
					</tr>

			<?php //}  }?>
			</table>

			</td>
			

<!--------------------------------------------ADD ALL STUDENT DATA OF TRANSFER------------------------------------------------------->	
			<td valign=top>
			<form action="<?php echo site_url('admissionstu/addstudent_transfer'); ?>" method="POST">
				<input type="hidden" name="stu_smid" value="<?php echo $row->sas_studentmasterid;?>" readonly>
				<input type="hidden" name="stu_oldprgid" value="<?php echo $prgid;?>">
				<input type="hidden" name="stu_olddeptid" value="<?php echo $deptid; ?>">
				<input type="hidden" name="stu_elicrie" value="<?php echo $data->admop_min_qual;; ?>">
				<table style="width:100%;" class="TFtable">
					<tr>
						
						<td><b>Hall Ticket Number</b> </br><input type="text" name="stu_hallticketno" value="<?php echo $this->commodel->get_listspfic1('student_admissionstatus','sas_hallticketno','sas_studentmasterid',$row->sas_studentmasterid)->sas_hallticketno;?>" readonly></td>

						<td><b>Student Name </b> </br><input type="text" name="stu_stuname" value="<?php echo $this->commodel->get_listspfic1('student_master','sm_fname','sm_id',$row->sas_studentmasterid)->sm_fname;?>" readonly></td>
	

						<td><b>Program & Branch </b> </br>
							<select name="stu_prgname" id="stu_prgname" onchange="getdeptname(this.value);geteligible(this.value);">
								<option value="" disabled selected >Select Program</option>
               							 <?php foreach($prgresult as $dataspt): ?>
               								 <option value="<?php echo $dataspt->prg_id?>"><?php echo $dataspt->prg_name.'('.$dataspt->prg_branch.')'; ?></option>
               							 <?php endforeach; ?>
							</select>
	
						</td>
						<td><b>Department</b> </br>
							<select name="stu_departname" id="stu_departname">
								<option disabled selected >Select Department</option>
							</select>
						</td>
						
					</tr>

					
					<tr>
						<td><b> Taken Seats </b></br>
						<?php $takesetas=$this->commodel->get_listspfic1('program','prg_seat','prg_id',$prgid)->prg_seat;?>
							<input type="text" name="stu_seattaken" value="<?php echo $takesetas; ?>" readonly>
						</td>

						<td><b>Seats Filled</b> </br>
						<?php 
	
						$whdata = array('sas_prgid' => $prgid);
						$leftseats = $this->commodel->get_listarry('student_admissionstatus','sas_studentmasterid',$whdata);				
		
						$seat = count($leftseats);	
						?>
							<input type="text" name="stu_seatleft" value="<?php echo $seat;?>" readonly>
						</td>
					
						<td><b>Eligibility Crieteria</b> </br>
						<!--<select name="stu_eligble" id="stu_eligble"><option>select min qualification</option></select>-->
						<span id='stu_eligble'></span>
						
						<td colspan=2></td>
					</tr>
					
					<?php if($takesetas > $seat){?>	
					<tr>
						<td colspan=6><center><input type="submit" name="stu_submit" value="Transfer"></enter></td>
					</tr>
					<?php }?>
					<tr><td height=28 colspan=6></td></tr>
				</table>
			</form>
			</td>	
	
		</tr>

		</table>

	<?php }}//}?>

<!------------------------------------------DISPLAY ALL STUDENT DATA OF TRANSFER------------------------------------------------------->	

	<table class="TFtable" style="width:100%">
		<thead>
			<tr>
				<th>Sr. No.</th><th>Hall Ticket Number</th><th>Student Name</th>
				<th>Old Program(Branch)</th><th>Old Department</th><th>New Program(Branch)</th><th>New Department</th><th>Transfer Date</th>
			</tr>
		</thead>	
		<tbody>
			<?php 
				$count=1;
				if(!empty($stu_transfer)){
				foreach($stu_transfer as $data){
			?>
			<tr>
				<td><?php echo $count++;?></td>
				<td><?php echo $data->st_hallticketno;?></td>
				<?php $sname = $this->commodel->get_listspfic1('student_master','sm_fname','sm_id',$data->st_smid)->sm_fname; ?>
				<td><?php echo $sname;?></td>
				<td><?php echo $this->commodel->get_listspfic1('program','prg_name','prg_id',$data->st_progid)->prg_name.'( '.$this->commodel->get_listspfic1('program','prg_branch','prg_id',$data->st_progid)->prg_branch.' )';?></td>
				<?php $olddeptid = $this->commodel->get_listspfic1('program','prg_deptid','prg_deptid',$data->st_deptid)->prg_deptid; ?>
				<td><?php echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$olddeptid)->dept_name; ?></td>
				<td><?php echo $this->commodel->get_listspfic1('program','prg_name','prg_id',$data->st_newprogid)->prg_name.'( '.$this->commodel->get_listspfic1('program','prg_branch','prg_id',$data->st_newprogid)->prg_branch.' )'; ?></td>
				<?php $olddeptid = $this->commodel->get_listspfic1('program','prg_deptid','prg_deptid',$data->st_newdeptid)->prg_deptid; ?>
				<td><?php echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$olddeptid)->dept_name; ?></td>
				<td><?php echo $data->st_createdate; ?></td>
			</tr>
			<?php }}?>
		</tbody>
	</table>
	

 </div><?php $this->load->view('template/footer'); ?></div>
    </body>
</html>

