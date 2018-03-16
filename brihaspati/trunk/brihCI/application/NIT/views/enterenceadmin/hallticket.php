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
</head>

<body>

<?php  // $thisPage2="studentaddDetail"; 
		$this->load->view('template/header'); ?>
	
        <?php //$this->load->view('template/menu');?>
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table> -->
<?php
echo "<center>";

	if($this->session->flashdata('msg')){
echo "<div style='font-size:20px;text-align:center;background-color:#DFF2BF;width:50%;height:40px;color:green;'>";
	echo $this->session->flashdata('msg');
echo "<div>";	
}

echo "</center>";
?>
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

<table>

	<a href="<?php echo site_url('enterenceadmin/generatehallticket'); ?>" style="font-size:22px;font-weight:bold;">Generate Hall Ticket</a>

</table>
<div class="scroller_sub_page">
<table style="width:100%;margin-top:30px;" border=0>
<tr>
<?php 
$year=date('Y');
$i=0;
if(!empty($stud_master)){
foreach($stud_master as $row){

?>
<td align=left>
<?php if(!empty($row->ca_rollno)){
	$hallurl = 'uploads/SLCMS/enterenceadmin_student/'.$year.'/hallticket/'.$row->ca_asmid.'hallticket.pdf';
	if(file_exists($hallurl)) {
?>
<a href="<?php echo base_url('uploads/SLCMS/enterenceadmin_student/'.$year.'/hallticket/'.$row->ca_asmid.'hallticket.pdf');?>" target=_blank>
View PDF</br>
<embed src="<?php echo base_url('uploads/SLCMS/enterenceadmin_student/'.$year.'/hallticket/'.$row->ca_asmid.'hallticket.pdf');?>" type="application/pdf"   height="400px" width="400">
</a>	
<?php }}?>
</td>
<?php //}
$i++;
if($i%3 == 0){?>
</tr>
<tr>
<?php } 
}
}?>
</tr>
</table>
</div><!------scroller div------>

<div>  <?php $this->load->view('template/footer');?></div>
</body>
</html>
