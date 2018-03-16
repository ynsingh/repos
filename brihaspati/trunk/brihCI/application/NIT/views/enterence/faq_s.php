<!-------------------------------------------------------
    -- @name faq_s.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
--------------------------------------------------------->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>IGNTU:Faq</title>
	<link rel="shortcut icon" href="<?php echo base_url('assets/images'); ?>/index.jpg">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/style.css">
	 <link rel="stylesheet" type="text/css" href="<?php echo base_url();?>assets/css/tablestyle.css">
</head>
<body>

<div id="body">
	<?php $this->load->view('template/header'); ?>
</br>
	<?php $this->load->view('enterence/enterence_head'); ?>
	
<!--------------------------------------------------------ERROR DISPLAY-------------------------------------------------------------->
<?php
echo "<center>";

	if($this->session->flashdata('msg')){
echo "<div style='font-size:20px;text-align:center;background-color:#DFF2BF;width:50%;height:30px;color:green;'>";
	echo $this->session->flashdata('msg');
echo "<div>";	
}

echo "</center>";
?>
	<div align="left" style="margin-left:30px;width:100%;font-size:18px;">
        <?php echo validation_errors('<div class="isa_warning">','</div>');?>
        <?php echo form_error('<div style="margin-left:30px;" class="isa_error">','</div>');?>
        <?php if(isset($_SESSION['success'])){?>
        <div class="alert alert-success"><?php echo $_SESSION['success'];?></div>
        <?php
    	 };
       	?>
	
        <?php if(isset($_SESSION['err_message'])){?>
             <div class="isa_error" ><div ><?php echo $_SESSION['err_message'];?></div></div>
        <?php
        };
	?>  
      </div>
	<table style="width:100%;margin-top:50px;">
		<tr>
			<td><?php $this->load->view('template/work_under');?></td>
		<tr>
	</table>
</div>

<div style="margin-top:0px;">
<?php $this->load->view('template/footer'); ?>
</div>
</body>
</html>
