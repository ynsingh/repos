<?php
defined('BASEPATH') OR exit('No direct script access allowed');
if(isset($this->session->userdata['firstName'])){
?><!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Annant Gyan</title>
		<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery.datetimepicker.css"/>
	        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery-ui.css">
		<link href="<?php echo base_url('assets/css');?>/style.css" rel="stylesheet">

		<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
		<!--<link href="<?php //echo base_url('assets/css');?>/bootstrap.min1.css" rel="stylesheet">-->
		<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
	      	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css" media="all" href="<?php echo base_url(); ?>assets/css/jsDatePick.css" />
<script type="text/javascript" src="<?php echo base_url(); ?>assets/js/jsDatePick.min.1.1.js"></script>
<!--
<link rel="stylesheet" type="text/css" media="all" href="<?php echo base_url(); ?>assets/css/jquery-clockpicker.css" />
<script type="text/javascript" src="<?php echo base_url(); ?>assets/js/jquery-clockpicker.js"></script>

<link rel="stylesheet" type="text/css" media="all" href="<?php echo base_url(); ?>assets/css/bootstrap-clockpicker.min.css" />
<script type="text/javascript" src="<?php echo base_url(); ?>assets/js/bootstrap-clockpicker.min.js"></script>

<script type="text/javascript">
$('.clockpicker').clockpicker();
</script>
-->

<script type="text/javascript">
        window.onload = function(){
                new JsDatePick({
                        useMode:2,
                        target:"tdate",
                        //limitToToday:true, 
                });
        };
</script>

		<script type="text/javascript">
	
!function($,n,e){var o=$();$.fn.dropdownHover=function(e){return"ontouchstart"in document?this:(o=o.add(this.parent()),this.each(function(){function t(e){o.find(":focus").blur(),h.instantlyCloseOthers===!0&&o.removeClass("open"),n.clearTimeout(c),i.addClass("open"),r.trigger(a)}var r=$(this),i=r.parent(),d={delay:100,instantlyCloseOthers:!0},s={delay:$(this).data("delay"),instantlyCloseOthers:$(this).data("close-others")},a="show.bs.dropdown",u="hide.bs.dropdown",h=$.extend(!0,{},d,e,s),c;i.hover(function(n){return i.hasClass("open")||r.is(n.target)?void t(n):!0},function(){c=n.setTimeout(function(){i.removeClass("open"),r.trigger(u)},h.delay)}),r.hover(function(n){return i.hasClass("open")||i.is(n.target)?void t(n):!0}),i.find(".dropdown-submenu").each(function(){var e=$(this),o;e.hover(function(){n.clearTimeout(o),e.children(".dropdown-menu").show(),e.siblings().children(".dropdown-menu").hide()},function(){var t=e.children(".dropdown-menu");o=n.setTimeout(function(){t.hide()},h.delay)})})}))},$(document).ready(function(){$('[data-hover="dropdown"]').dropdownHover()})}(jQuery,this);
		</script>
	
</head>
<body>

<div class="fluid-container">
	<div class="row">
		<div class="col-md-12">
			<img src="<?php echo base_url('images');?>/logo.png" class="img-responsive center-block">
		</div>
	</div>
	
	<div class="row">
			<?php $this->load->view('template/admin_header.php'); ?>

	</div>	

</div>	

<div class="container">  
<?php echo validation_errors('<div class="alert-warning"  style="font-size: 18px;" align=left>','</div>');?>
        <?php echo form_error('<div class="">','</div>');?>
        <?php 
        if(!empty($_SESSION['success'])){   
        if(isset($_SESSION['success'])){?>
         <div class="alert alert-success" style="font-size: 18px;"><?php echo $_SESSION['success'];?></div>
         <?php
          } };
         ?>
    
        <?php 
        if(!empty($_SESSION['error'])){
        if(isset($_SESSION['error'])){?>
             <div class="alert alert-danger" style="font-size: 18px;"><?php echo $_SESSION['error'];?></div>
        <?php
        };
    }   
    ?>  
</div> 

<h2 class="text-center">Create Exam</h2>
<div class="container" >
<div class="col-md-3"></div>
<div class="col-md-7" id='card'>
	<table style="font-size:18px;"><tr>
			<td><a href="<?php echo site_url('admin/viewexam');?>">View Exam</a></td>
	</tr></table>
		
	<form action="<?php echo site_url('admin/createexam')?>" method="POST" enctype="multipart/form-data">
			<label for="crsname" class="cols-sm-2 control-label">Course Name</label>
			<br>
			<select name="cou_name"  style="width:600px;" >
				<option value="" selected="" disabled="">Select Course Name</option>
				<?php if(!empty($couname)){
					foreach($couname as $row){	
				?>
					<option value="<?php echo $row->cou_id;?>"><?php echo $row->cou_name;?></option>
				<?php }}?>	
			</select>
			<br>

<!--			<select name="cou_type" >
				<option value="" selected="" disabled="">Select Content Type</option>
				<option value="document">Document</option>
				<option value="video">Video</option>
			</select>
-->
			<label for="crswk" class="cols-sm-2 control-label">Week No</label>
			<br>
			<select name="cou_week" style="width:600px;">
				<option value="" selected="" disabled="">Select Weeks</option>
				<option value="week 1">Week 1</option>
				<option value="week 2">Week 2</option>
				<option value="week 3">Week 3</option>
				<option value="week 4">Week 4</option>
				<option value="week 5">Week 5</option>
				<option value="week 6">Week 6</option>
			</select>	
			<br>
			<label for="tstname" class="cols-md-2 control-label">Test Name</label>
			<br>
			<input type="text" name="test_name" placeholder="Enter test name" style="width:600px;">
			<br>
			<label for="tstcode" class="cols-md-2 control-label">Test Code</label>
			<br>
			<input type="text" name="test_code" placeholder="Enter test code" style="width:600px;">
			<br>
			<label for="tstdesc" class="cols-md-2 control-label">Test Description</label>
			<br>
			<input type="text" name="test_desc" placeholder="Enter test description" style="width:600px;">
			<br>
			<label for="tstdate" class="cols-md-2 control-label">Test Date</label>
			<br>
			<input type="text" name="test_date" id="tdate" placeholder="Enter test date" style="width:600px;">
			<br>
<!--			<div class="input-group clockpicker" data-placement="left" data-align="top" data-autoclose="true">
    <input type="text" class="form-control" value="13:14">
    <span class="input-group-addon">
        <span class="glyphicon glyphicon-time"></span>
    </span>
</div> -->
			<label for="tstftime" class="cols-md-2 control-label">Test From Time</label>
			<br>
			<select name="fmin" style="width:300px;">
				<option value="" selected="" disabled="">Select Hours</option>
<?php				foreach (range(1, 24, 1) as $number) { 
                                echo "<option value=".$number.">".$number."</option>";
				 } ?>
			</select>
			<select name="fsec" style="width:300px;">
                                <option value="" selected="" disabled="">Select Minutes</option>
<?php				foreach (range(00, 60, 05) as $number) { 
                                echo "<option value=".$number.">".$number."</option>";
				 } ?>
                        </select>
			<!--<input type="text" name="test_from" placeholder="Enter test from time" style="width:600px;"> -->
			<br>
			<label for="tstttime" class="cols-md-2 control-label">Test To Time</label>
			<br>
			<select name="tmin" style="width:300px;">
				<option value="" selected="" disabled="">Select Hours</option>
<?php				foreach (range(1, 24, 1) as $number) { 
                                echo "<option value=".$number.">".$number."</option>";
				 } ?>
			</select>
			<select name="tsec" style="width:300px;">
                                <option value="" selected="" disabled="">Select Minutes</option>
<?php				foreach (range(00, 60, 05) as $number) { 
                                echo "<option value=".$number.">".$number."</option>";
				 } ?>
                        </select>
<!--			<input type="text" name="test_to" placeholder="Enter test to time" style="width:600px;">-->
			<br>
			<label for="tsttnoq " class="cols-md-2 control-label">Total No of Question</label>
			<br>
			<input type="text" name="test_totalq" placeholder="Enter total no of question" style="width:600px;">
			<br>
			<label for="tstmm " class="cols-md-2 control-label">Max Marks</label>
                        <br>
                        <input type="text" name="test_mm" placeholder="Enter Max Marks" style="width:600px;">
			<br>
<br>

	<!--	</div>
			</br>	</br>	
		<div class="col-md-7" id='card'> -->
			<table style="">
				<tr>
			<!--		<td><input type="text" name="cou_seqno" placeholder="Enter Content Show Sequence Number" ></td>
					<td width=10></td>
					<td><input type='file' name='userfile' size='20' style=""/></td> -->
					<td><input type="submit" name="cou_exam" class="btn btn-success submit" value="Submit"></td>
				</tr>
				
			</table>
<br><br>

	</form>
			
		</div>	
</div>

<?php $this->load->view('template/footer.php');?>
</body>
</html>
<?php }else{
$this->load->view('admin/admin_login');
}
?>
