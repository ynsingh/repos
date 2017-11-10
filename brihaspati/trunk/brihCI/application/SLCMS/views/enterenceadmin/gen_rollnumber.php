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
<table align=center style="width:70%;">
<tr class="isa_success">
        <?php echo validation_errors('<div class="isa_warning">','</div>');?>
        <?php echo form_error('<div style="" class="isa_success">','</div>');?>
        <?php if(isset($_SESSION['success'])){?>
        <td><?php echo $_SESSION['success'];?></td>
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
</br></br>
<center>   
<table style="width:50%;">
	<tr><td></td></tr>
</table>
	<form action="<?php echo site_url('enterenceadmin/search_rollnumber'); ?>" method="POST">
		<table style="width:70%;" border=0>
		<tr>
			<td align=center><h2>Search Center Wise / Program Wise Roll Number </h2></td>
			
			<td align=right  valign="top">
			
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
      
