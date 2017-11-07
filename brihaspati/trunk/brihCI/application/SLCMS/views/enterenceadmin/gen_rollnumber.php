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
	<h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
	<?php $this->load->view('template/menu');?>
	
<?php
echo "<center>";

	if($this->session->flashdata('msg')){
echo "<div style='font-size:20px;text-align:left;background-color:#DFF2BF;width:70%;height:30px;color:green;'>";
	echo $this->session->flashdata('msg');
echo "<div>";	
}

echo "</center>";
?>
<center>   
<h1>Search Center Wise / Program Wise Roll Number </h1>
	<form action="<?php echo site_url('enterenceadmin/search_rollnumber'); ?>" method="POST">
		<table style="width:50%;" border=0>
		<tr>
			<td style="height:35px;font-size:18px;"></td>
			<td align=right>
			<label for="nnumber"></label></br>
			<select name="ronoexamcenter" class="form-control" style="height:37px;font-size:18px;font-weight:bold;">

			<option selected="true" disabled="disabled" style="font-size:18px;">Select Entrance Exam Center</option>
				<?php 
					foreach($examcenter as $row): 
					if(!empty($row->ca_centername)){
				?>	
					<option value="<?php echo $row->ca_centername;?>"><?php echo $row->ca_centername; ?></option>
				<?php } endforeach; ?>
			</select>
			</td>
			<td align=left>
			<label for="nnumber"></label></br>
			<select name="ronoprg" class="form-control" style="height:37px;font-size:18px;font-weight:bold;">

			<option selected="true" disabled="disabled" style="font-size:18px;">Select Program</option>
				<?php foreach($prgname as $row): ?>	
					<option value="<?php echo $row->ca_prgid;?>"><?php echo $this->commodel->get_listspfic1('program','prg_name','prg_id',$row->ca_prgid)->prg_name.'('.$this->commodel->get_listspfic1('program','prg_branch','prg_id',$row->ca_prgid)->prg_branch.')'; ?></option>
				<?php endforeach; ?>
			</select>
			</td>
			<td align=left><label for="nnumber" style="visibility:hidden;">Enterence Exam Center</label></br>
			<input type="submit" name="searchsticker" value="Submit" style="height:35px;font-size:18px;"></td>
		</tr>
		
		</table>
	</form>  

	<table class="TFtable" style="border:2px solid #a8a8a8;opacity:5.9;">
		<thead><tr><th>Sr. No.</th><th>Student Name</th><th>Roll Number</th><th>Program</th><th>Center Name</th></tr></thead>
		<tbody align=center>
			<?php $count=1;
			if(!empty($this->getatt)){
			foreach($this->getatt as $row){
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
				<td><?php echo $row->ca_centername;?></td>
			</tr>
			<?php }}}?>
		</tbody>
	</table> 			
</center>     

  </body>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
</html>
      
