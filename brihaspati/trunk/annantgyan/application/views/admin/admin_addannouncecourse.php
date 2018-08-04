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
	 <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.js" ></script>


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

<h2 class="text-center"> Course Announcement</h2>
		
<div class="container">
<div class="col-md-3"></div>	
<div class='col-md-7' id='card'>	
	<table style="font-size:18px;"><tr>
			<td><a href="<?php echo site_url('admin/courseannouncement');?>">View Announced Course</a></td>
	</tr></table>
		
	<form action="<?php echo site_url('admin/admin_addannouncecourse')?>" method="POST">
		<div class="form-group">
			<labe<label for="cname" class="cols-sm-2 control-label">Course Name</label>
			<br>
			<select name="cname"  required="">
				<option value="" selected="" disabled="">Select Course Name</option>
				<?php if(!empty($couname)){
					foreach($couname as $row){	
				?>
					<option value="<?php echo $row->cou_id;?>"><?php echo $row->cou_name;?></option>
				<?php }}?>	
			</select>
			
			<br>
			<labe<label for="cduration" class="cols-sm-2 control-label">Course Duration</label>
			<br>
			<select name="cdur_week"  required="">
				<option value="" selected="" disabled="">Select Weeks</option>
				<option value="week 1">Week 1</option>
				<option value="week 2">Week 2</option>
				<option value="week 3">Week 3</option>
				<option value="week 4">Week 4</option>
				<option value="week 5">Week 5</option>
				<option value="week 6">Week 6</option>
			</select>	
			<br>
			
			<label for="crsdate " class="cols-sm-2 control-label">Course Registration Start Date</label>
			<input type="text" class="form-control" name="crsdate" id="crsdate"  placeholder="Enter Course Name" required/>
			        
            <script>
                $('#crsdate').datepicker({
                onSelect: function(value, ui) {
                    console.log(ui.selectedYear)
                    var today = new Date(), 
                    dob = new Date(value), 
                    age = 2017-ui.selectedYear;

//                $("#age").text(age);
                },
                
                    changeMonth: true,
                    changeYear: true,
                    dateFormat: 'yy-mm-dd',
                   // defaultDate: '1yr',
                    yearRange: 'c-36:c+10',
                });
            </script>
          
			<label for="credate " class="cols-sm-2 control-label">Course Registration End Date</label>
			<input type="text" class="form-control" name="credate" id="credate"  placeholder="Enter Course Name" required/>
			<script>
                $('#credate').datepicker({
                onSelect: function(value, ui) {
                    console.log(ui.selectedYear)
                    var today = new Date(), 
                    dob = new Date(value), 
                    age = 2017-ui.selectedYear;

//                $("#age").text(age);
                },
                
                    changeMonth: true,
                    changeYear: true,
                    dateFormat: 'yy-mm-dd',
                   // defaultDate: '1yr',
                    yearRange: 'c-36:c+10',
                });
            </script>
			<label for="csdate " class="cols-sm-2 control-label">Course Start Date</label>
			<input type="text" class="form-control" name="csdate" id="csdate"  placeholder="Enter Course Name" required/>
			<script>
                $('#csdate').datepicker({
                onSelect: function(value, ui) {
                    console.log(ui.selectedYear)
                    var today = new Date(), 
                    dob = new Date(value), 
                    age = 2017-ui.selectedYear;

//                $("#age").text(age);
                },
                
                    changeMonth: true,
                    changeYear: true,
                    dateFormat: 'yy-mm-dd',
                   // defaultDate: '1yr',
                    yearRange: 'c-36:c+10',
                });
            </script>
			<label for="cedate " class="cols-sm-2 control-label">Course End Date</label>
			<input type="text" class="form-control" name="cedate" id="cedate"  placeholder="Enter Course Name" required/>
			<script>
                $('#cedate').datepicker({
                onSelect: function(value, ui) {
                    console.log(ui.selectedYear)
                    var today = new Date(), 
                    dob = new Date(value), 
                    age = 2017-ui.selectedYear;

//                $("#age").text(age);
                },
                
                    changeMonth: true,
                    changeYear: true,
                    dateFormat: 'yy-mm-dd',
                   // defaultDate: '1yr',
                    yearRange: 'c-36:c+10',
                });
            </script>
			<label for="cfdate " class="cols-sm-2 control-label">Course Feedback Date</label>
			<input type="text" class="form-control" name="cfdate" id="cfdate"  placeholder="Enter Course Name" required/>
			<script>
                $('#cfdate').datepicker({
                onSelect: function(value, ui) {
                    console.log(ui.selectedYear)
                    var today = new Date(), 
                    dob = new Date(value), 
                    age = 2017-ui.selectedYear;

//                $("#age").text(age);
                },
                
                    changeMonth: true,
                    changeYear: true,
                    dateFormat: 'yy-mm-dd',
                   // defaultDate: '1yr',
                    yearRange: 'c-36:c+10',
                });
            </script>
			<label for="ccdate " class="cols-sm-2 control-label">Course Certificate Date</label>
			<input type="text" class="form-control" name="ccdate" id="ccdate"  placeholder="Enter Course Name" required/>
			<script>
                $('#ccdate').datepicker({
                onSelect: function(value, ui) {
                    console.log(ui.selectedYear)
                    var today = new Date(), 
                    dob = new Date(value), 
                    age = 2017-ui.selectedYear;

//                $("#age").text(age);
                },
                
                    changeMonth: true,
                    changeYear: true,
                    dateFormat: 'yy-mm-dd',
                   // defaultDate: '1yr',
                    yearRange: 'c-36:c+10',
                });
            </script>
			<br>
			<input type="submit" name="submit" class="btn btn-success submit" value="Submit">
		</div>					
	</form>
</div>
</div>

</br><br>
<?php $this->load->view('template/footer.php');?>
</body>

<?php }else{
$this->load->view('admin/admin_login');
}
?>