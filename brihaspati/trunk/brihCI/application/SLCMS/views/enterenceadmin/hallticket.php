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


</center>

<div align="center">  <?php $this->load->view('template/footer');?></div>
</body>
</html>
