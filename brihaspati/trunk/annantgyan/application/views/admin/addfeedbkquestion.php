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

<h2 class="text-center"> Add Feedback Questions  </h2>
<div class="container" >
<div class="col-md-3"></div>
<div class="col-md-16" id='card'> 
	<table style="font-size:18px;"><tr>
			<td><a href="<?php echo site_url('admin/courselist');?>">View Course List</a></td>
	</tr></table>
<?php 		$i=1; ?>
	<form action="<?php echo site_url('admin/addfeedbkquestion/'.$sid)?>" method="POST" enctype="multipart/form-data">
		<table>
			<tr>
				<td>
			<label for="crsname" class="cols-sm-2 control-label">Course Name</label>
			<br>
			<select name="cou_name"  style="width:350px;" >
				<option value="<php echo $sid;?>" > <?php echo $this->commodel->get_listspfic1('courses','cou_name','cou_id',$sid)->cou_name;?></option>
				<option value="" selected="" disabled="">Select Course Name</option>
				<?php if(!empty($couname)){
					foreach($couname as $row){	
				?>
					<option value="<?php echo $row->cou_id;?>"><?php echo $row->cou_name;?></option>
				<?php }}?>	
			</select>

			</td></tr>
<?php			for($i; $i<=$qreq; $i++){  ?>
			<tr><td>
			<label for="tstname" class="cols-md-2 control-label">Question<?php echo $i;?></label>
			<br>
			<textarea name="<?php echo "question".$i;?>" placeholder="Enter Question" style="width:350px;">
			</textarea>
			</td><td>
                        <label for="tstcode" class="cols-md-2 control-label">Option1</label>
                        <br>
                        <textarea name="<?php echo "optiona".$i;?>" placeholder="Enter Option1" style="width:150px;">
                        </textarea>
                        </td><td>
                        <label for="tstcode" class="cols-md-2 control-label">Option2</label>
                        <br>
                        <textarea name="<?php echo "optionb".$i;?>" placeholder="Enter Option2" style="width:150px;">
                        </textarea>
                        </td><td>
                        <label for="tstcode" class="cols-md-2 control-label">Option3</label>
                        <br>
                        <textarea name="<?php echo "optionc".$i;?>" placeholder="Enter Option3" style="width:150px;">
                        </textarea>
                        </td><td>
                        <label for="tstcode" class="cols-md-2 control-label">Option4</label>
                        <br>
                        <textarea name="<?php echo "optiond".$i;?>" placeholder="Enter Option4" style="width:150px;">
                        </textarea>

			</td>
<!--			<td>
			<label for="expans" class="cols-md-2 control-label">Answer Explanation</label>
			<br>
			<textarea name="<?php //echo "expans".$i;?>" placeholder="Enter Answer Explanationn" style="width:200px;">
			</textarea>
			</td> -->
			</tr>
<?php       		} ?>
				<tr> <td>&nbsp; </td> </tr>
				<tr>
					<td><input type="submit" name="cou_quest" class="btn btn-success submit" value="Submit"></td>
				</tr>
				
			</table>
<br><br>

	</form>
			
		</div>	
</div>
<br><br>

<?php $this->load->view('template/footer.php');?>
</body>
</html>
<?php }else{
$this->load->view('admin/admin_login');
}
?>
