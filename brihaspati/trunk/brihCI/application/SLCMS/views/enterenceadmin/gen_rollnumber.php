<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name gen_rollnumber.php 
  * @author Sumit Saxena(sumitsesaxena@gmail.com) *
 -->


<html>
<title>Enterence Fees Reconcile</title>
 <head>    
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">

	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
<script>

</script>
 </head>    
   <body>

	<?php $this->load->view('template/header'); ?>
	<?php $this->load->view('template/menu');?>

<p>
<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
</p>	

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
<center>   

	<form action="<?php echo site_url('enterenceadmin/search_rollnumber'); ?>" method="POST">
		<table style="width:100%;" border=0>
		<tr>
			<td align=center><h2>Search Center Wise / Program Wise Roll Number </h2></td>
			
			<td align=right  valign="top">
			
			<select name="ronoexamcenter" class="form-control" style="height:37px;font-size:18px;font-weight:bold;">

			<option selected="true" disabled="disabled" style="font-size:18px;">Select Entrance Exam Center</option>
				<?php 
					foreach($examcenter as $row): 
					if(!empty($row->ca_centername)){
					$centerid = $row->ca_centername;
					$centername = $this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_name','eec_id',$centerid)->eec_name;
					$centercode = $this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_code','eec_id',$centerid)->eec_code;
				?>	
					<option value="<?php echo $centerid;?>"><?php echo $centername.'( '.$centercode.' )'; ?></option>
				<?php } endforeach; ?>
			</select>
			</td>
			<td align=left valign="top">
			
			<select name="ronoprg" class="form-control" style="height:37px;font-size:18px;font-weight:bold;">

			<option selected="true" disabled="disabled" style="font-size:18px;">Select Program</option>
				<?php foreach($prgname as $row): ?>	
					<option value="<?php echo $row->ca_prgid;?>"><?php echo $this->commodel->get_listspfic1('program','prg_name','prg_id',$row->ca_prgid)->prg_name.'('.$this->commodel->get_listspfic1('program','prg_branch','prg_id',$row->ca_prgid)->prg_branch.')'; ?></option>
				<?php endforeach; ?>
			</select>
			</td>
			<td align=left valign="top">
			<input type="submit" name="searchsticker" value="Submit" style="height:35px;font-size:18px;"></td>
		</tr>
		
		</table>
	</form>  
<div class="scroller_sub_page">
	<table class="TFtable" style="border:2px solid #a8a8a8;opacity:5.9;">
		<thead><tr><th>Sr. No.</th><th>Student Name</th><th>Roll Number</th><th>Program</th><th>Center Name</th></tr></thead>
		<tbody align=center>
			<?php $count=1;
			if(!empty($this->exmgetatt)){
			//if($this->exmgetatt == TRUE ){
			if(!($this->combigetatt) || ($this->combigetatt === FALSE)){
			foreach($this->exmgetatt as $row){
				$asmid = $row->ca_asmid;
				$rollno=$row->ca_rollno;
			if(!empty($rollno)){
			?>
			<tr>
				<td><?php echo $count++;?></td>
				<td><?php echo $this->commodel->get_listspfic1('admissionstudent_master','asm_fname','asm_id',$asmid)->asm_fname;?></td>
				<td><?php echo $rollno;?></td>
				<?php $prgid = $row->ca_prgid;?>
				<td><?php echo $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name.'('.$this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch.')';?></td>
				<td><?php $centerid = $row->ca_centername;
					echo $this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_name','eec_id',$centerid)->eec_name;?></td>
			</tr>
			<?php }}}}//}?>

			<?php $count=1;
			if(!empty($this->prggetatt)){
			if(!($this->combigetatt) || ($this->combigetatt === FALSE)){
				foreach($this->prggetatt as $row){
				$asmid = $row->ca_asmid;
				$rollno=$row->ca_rollno;
				if(!empty($rollno)){
			?>
			<tr>
				<td><?php echo $count++;?></td>
				<td><?php echo $this->commodel->get_listspfic1('admissionstudent_master','asm_fname','asm_id',$asmid)->asm_fname;?></td>
				<td><?php echo $rollno;?></td>
				<?php $prgid = $row->ca_prgid;?>
				<td><?php echo $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name.'('.$this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch.')';?></td>
				<td><?php $centerid = $row->ca_centername;
					echo $this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_name','eec_id',$centerid)->eec_name;?></td>
			</tr>
			<?php }}}}?>


			<?php $count=1;
			if(!empty($this->combigetatt)){
			
			foreach($this->combigetatt as $row){
				$asmid = $row->ca_asmid;
				$rollno=$row->ca_rollno;
			if(!empty($rollno)){
			?>
			<tr>
				<td><?php echo $count++;?></td>
				<td><?php echo $this->commodel->get_listspfic1('admissionstudent_master','asm_fname','asm_id',$asmid)->asm_fname;?></td>
				<td><?php echo $rollno;?></td>
				<?php $prgid = $row->ca_prgid;?>
				<td><?php echo $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name.'('.$this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch.')';?></td>
				<td><?php $centerid = $row->ca_centername;
					echo $this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_name','eec_id',$centerid)->eec_name;?></td>
			</tr>
			<?php }}} ?>

			
		</tbody>
	</table> 		
	</div>	<!------scroller div------>	

  </body>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
</html>
      
