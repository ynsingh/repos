<!-------------------------------------------------------
    -- @name hallticket.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
--------------------------------------------------------->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?><!DOCTYPE html>
<html moznomarginboxes mozdisallowselectionprint>
<head>
<meta charset="utf-8">
	<title>IGNTU:Student Hall Ticket</title>
	<link rel="shortcut icon" href="<?php echo base_url('assets/images'); ?>/index.jpg">
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/message.css">
		<link rel="stylesheet" type="text/css" media="all" href="<?php echo base_url(); ?>assets/css/Studentsteps.css" />
<style>
table tr td{font-size:18px;}
thead th{font-size:18px;}
</style>
</head>

<body>
<?php  // $thisPage2="studentaddDetail"; 
		$this->load->view('template/header'); ?>
	<h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
        <?php $this->load->view('template/menu');?>
	
<center style="width:100%;">
	
	</br>

<table>

	<a href="<?php echo site_url('enterenceadmin/generatehallticket'); ?>" style="font-size:22px;font-weight:bold;">Generate Hall Ticket</a>

</table>

<table style="width:90%;margin-top:30px;" border=0>
<tr>
<?php 
$year=date('Y');
$i=0;
foreach($stud_master as $row){

?>
<td>
<a href="<?php echo base_url('uploads/SLCMS/enterenceadmin_student/'.$year.'/hallticket/'.$row->ca_asmid.'hallticket.pdf');?>" target=_blank>
See PDF
<embed src="<?php echo base_url('uploads/SLCMS/enterenceadmin_student/'.$year.'/hallticket/'.$row->ca_asmid.'hallticket.pdf');?>" type="application/pdf"   height="400px" width="400">
</a>	
</td>
<?php //}
$i++;
if($i%4 == 0){?>
</tr>
<tr>
<?php } 
}?>
</tr>
</table>
<!--<table style="width:40%;" border=0>
	<tr>
<?php //for($i=0;$i<=1;$i++){?>
	<td>
	
	</br>
<?php foreach($this->stud_master as $row){
	$asmid = $row->asm_id;
	$prgid = $row->asm_coursename;	
?>
	<table style="width:100%;" border=1>
	<tr>
			<td>Program : <?php echo $prgname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name.'('.$this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch.')';?></td>
			<td align=center>Program Code : <?php echo $prgid;?></td>
		</tr>
		<tr>
			<td>Candidate Name : <?php echo $row->asm_fname;?></td>
			<td align=center>Hall Ticket Number</br>
			<?php $rollno=$this->commodel->get_listspfic1('admissionstudent_centerallocation','ca_rollno','ca_asmid',$asmid);
				echo $rollno->ca_rollno;?></td>
		</tr>
		
		<tr>
			<td>Father Name: <?php echo $this->commodel->get_listspfic1('admissionstudent_parent','aspar_fathername','aspar_asmid',$asmid)->aspar_fathername;?>
</br></br>
			    Mother Name: <?php echo $this->commodel->get_listspfic1('admissionstudent_parent','aspar_mothername','aspar_asmid',$asmid)->aspar_mothername; ?></br></br>
			    Address: <?php echo $this->commodel->get_listspfic1('admissionstudent_parent','aspar_paddress','aspar_asmid',$asmid)->aspar_paddress.','.$this->commodel->get_listspfic1('admissionstudent_parent','aspar_pcity','aspar_asmid',$asmid)->aspar_pcity.','.$this->commodel->get_listspfic1('admissionstudent_parent','aspar_pstate','aspar_asmid',$asmid)->aspar_pstate.','.$this->commodel->get_listspfic1('admissionstudent_parent','aspar_pcountry','aspar_asmid',$asmid)->aspar_pcountry.','.$this->commodel->get_listspfic1('admissionstudent_parent','aspar_ppincode','aspar_asmid',$asmid)->aspar_ppincode;?>		
			</td>
	<?php
		$photo = $this->commodel->get_listspfic1('admissionstudent_uploaddata','asupd_photo','asupd_asmid',$asmid)->asupd_photo;
		$signature = $this->commodel->get_listspfic1('admissionstudent_uploaddata','asupd_signature','asupd_asmid',$asmid)->asupd_signature;

	?>
			<td align=center><img src="<?php echo base_url('uploads/SLCMS/enterence/'.$asmid.'/'.$photo); ?>" style="height:150px;width:83%;"></br><img src="<?php echo base_url('uploads/SLCMS/enterence/'.$asmid.'/'.$signature); ?>" style="height:50px;width:83%;"></td>
		</tr>

	</table>
	
	<table style="width:100%;" border=1>
		<thead><th>Exam Date & Time</th><th>Venue</th></thead>
		<tr>
		<td align=center><?php echo date('Y-m-d H:i:s')?></td>

		<td><?php $centerid = $this->commodel->get_listspfic1('admissionstudent_master','asm_enterenceexamcenter','asm_id',$asmid)->asm_enterenceexamcenter;
			echo $this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_address','eec_id',$centerid)->eec_address.','.$this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_city','eec_id',$centerid)->eec_city;?></td>	
		</tr>
	</table>

	<table style="width:100%;" border=1>
		<thead><th>Category</th><th>Gender</th></thead>
		<tr>
		<td align=center><?php echo $row->asm_caste; ?></td>
		<td align=center><?php echo $row->asm_gender;?></td>	
		</tr>
	</table>

	<table style="width:100%;" border=1>
		<tr>
			<td>Signature of Candidate</td>
			<td align=right>Controller of Examination</td>
		</tr>
	</table>
<?php } //}?>
</td>

</tr>


</table>--->

</center>

<div align="center">  <?php $this->load->view('template/footer');?></div>
</body>
</html>
