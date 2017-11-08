<!-------------------------------------------------------
    -- @name admission_steps.php --	
    -- @author sumit saxena sumitsesaxena@gmail.com --
--------------------------------------------------------->
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/style.css">
	 <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/bootstrap.min.css">
    	 <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
    	 <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
	 <link rel="stylesheet" type="text/css" media="all" href="<?php echo base_url(); ?>assets/css/style.css" />
	 <link rel="stylesheet" type="text/css" media="all" href="<?php echo base_url(); ?>assets/css/firstmenu.css" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<style>
.stepalign td{padding:10px 35px;}
.breadcrumb li a {background-color:#cacaca;color:black;}
.breadcrumb li a:before { 
     content: " ";
    display: block;
    width: 0;
    height: auto;
    border-top: 50px solid transparent;
    border-bottom: 50px solid transparent;border-left: 30px solid black; position: absolute;
    top: 50%;
    margin-top: -50px;
    margin-left: 1px;
    left: 100%;
    z-index: 1;}
.breadcrumb li a:after {  
    content: " ";
    display: block;
    width: 0;
    height: 0;
    border-top: 50px solid transparent;
    border-bottom: 50px solid transparent;border-left: 30px solid #cacaca; position: absolute;
    top: 50%;
    margin-top: -50px;
    left: 100%;
    z-index: 2;}
</style>
</head>
<body>

<center>

<div class="container" style="width:60.3%;height:65px;font-size:13px;" >
	<div class="row">
		<?php if (stripos($_SERVER['REQUEST_URI'],'step_zero') !== false) {?>
			<ul class="breadcrumb ">
    				<li><a style="padding: 15px 0px 10px 30px;">Registration</a></li>
				<li><a style="padding: 15px 0 10px 45px;">Personnel</a></li>
				<li><a style="padding: 15px 0 10px 45px;">Education</a></li>
				<li><a style="padding: 15px 0 10px 45px;">Upload Enclosure</a></li>
				<li><a style="padding: 15px 0 10px 45px;">Payment</a></li>
				<li><a style="padding: 15px 20px 10px 45px;">Print Form</a></li>
			</ul>
		<?php }?>
		<?php if (stripos($_SERVER['REQUEST_URI'],'step_one') !== false) {?>
			<ul class="breadcrumb ">
    				<li class="active"><a style="padding: 15px 0px 10px 30px;">Registration</a></li>
				<li><a style="padding: 15px 0 10px 45px;">Personnel</a></li>
				<li><a style="padding: 15px 0 10px 45px;">Education</a></li>
				<li><a style="padding: 15px 0 10px 45px;">Upload Enclosure</a></li>
				<li><a style="padding: 15px 0 10px 45px;">Payment</a></li>
				<li><a style="padding: 15px 20px 10px 45px;">Print Form</a></li>
			</ul>
		<?php }?>
		<?php if (stripos($_SERVER['REQUEST_URI'],'step_two') !== false) {?>
			<ul class="breadcrumb ">
    				<li class="active"><a style="padding: 15px 0px 10px 30px;">Registration</a></li>
				<li class="active"><a style="padding: 15px 0 10px 45px;">Personnel</a></li>
				<li><a style="padding: 15px 0 10px 45px;">Education</a></li>
				<li><a style="padding: 15px 0 10px 45px;">Upload Enclosure</a></li>
				<li><a style="padding: 15px 0 10px 45px;">Payment</a></li>
				<li><a style="padding: 15px 20px 10px 45px;">Print Form</a></li>
			</ul>
		<?php }?>
		<?php if (stripos($_SERVER['REQUEST_URI'],'step_three') !== false) { ?>
			<ul class="breadcrumb ">
    				<li class="active"><a style="padding: 15px 0px 10px 30px;">Registration</a></li>
				<li class="active"><a style="padding: 15px 0 10px 45px;">Personnel</a></li>
				<li class="active"><a style="padding: 15px 0 10px 45px;">Education</a></li>
				<li><a style="padding: 15px 0 10px 45px;">Upload Enclosure</a></li>
				<li><a style="padding: 15px 0 10px 45px;">Payment</a></li>
				<li><a style="padding: 15px 20px 10px 45px;">Print Form</a></li>
			</ul>
		<?php }?>
		<?php if (stripos($_SERVER['REQUEST_URI'],'Payment') !== false) { ?>
			<ul class="breadcrumb ">
    				<li class="active" ><a style="padding: 15px 0px 10px 30px;">Registration</a></li>
				<li class="active" ><a style="padding: 15px 0 10px 45px;">Personnel</a></li>
				<li class="active" ><a style="padding: 15px 0 10px 45px;">Education</a></li>
				<li class="active" ><a style="padding: 15px 0 10px 45px;">Upload Enclosure</a></li>
				<li><a style="padding: 15px 0 10px 45px;">Payment</a></li>
				<li><a style="padding: 15px 20px 10px 45px;">Print Form</a></li>
			</ul>
		<?php }?>
		<?php if (stripos($_SERVER['REQUEST_URI'],'step_four') !== false) { ?>
			<ul class="breadcrumb ">
    				<li class="active"><a style="padding: 15px 0px 10px 30px;">Registration</a></li>
				<li class="active"><a style="padding: 15px 0 10px 45px;">Personnel</a></li>
				<li class="active"><a style="padding: 15px 0 10px 45px;">Education</a></li>
				<li class="active"><a style="padding: 15px 0 10px 45px;">Upload Enclosure</a></li>
				<li><a style="padding: 15px 0 10px 45px;">Payment</a></li>
				<li><a style="padding: 15px 20px 10px 45px;">Print Form</a></li>
			</ul>
		<?php }?>
		<?php if (stripos($_SERVER['REQUEST_URI'],'step_five') !== false) { ?>
			<ul class="breadcrumb ">
    				<li class="active"><a style="padding: 15px 0px 10px 30px;">Registration</a></li>
				<li class="active"><a style="padding: 15px 0 10px 45px;">Personnel</a></li>
				<li class="active"><a style="padding: 15px 0 10px 45px;">Education</a></li>
				<li class="active"><a style="padding: 15px 0 10px 45px;">Upload Enclosure</a></li>
				<li class="active"><a style="padding: 15px 0 10px 45px;">Payment</a></li>
				<li><a style="padding: 15px 20px 10px 45px;">Print Form</a></li>
			</ul>
		<?php }?>
	
	</div>
</div>

<!--<table class="steps">
<tr class="stepalign">

<?php 

	if (stripos($_SERVER['REQUEST_URI'],'step_zero') !== false) {
		echo "<td>Registration</td><td>Personnel</td><td>Education</td><td>Upload Enclosure</td> <td>Payment</td><td>Print Form</td>";
  	}  
	if (stripos($_SERVER['REQUEST_URI'],'step_one') !== false) {
		echo "<td class=\"active\" >Registration</td> <td>Personnel</td><td>Education</td><td>Upload Enclosure</td> <td>Payment</td><td>Print Form</td>";
 	} 
	if (stripos($_SERVER['REQUEST_URI'],'step_two') !== false) {
		echo "<td class=\"active\" >Registration</td> <td class=\"active\">Personnel</td><td>Education</td><td>Upload Enclosure</td><td>Payment</td><td>Print Form</td>";
 	} 
	if (stripos($_SERVER['REQUEST_URI'],'step_three') !== false) {
		echo "<td class=\"active\" >Registration</td> <td class=\"active\">Personnel</td><td class=\"active\">Education</td><td>Upload Enclosure</td><td>Payment</td><td>Print Form</td>";
 	} 
	if (stripos($_SERVER['REQUEST_URI'],'Payment') !== false) {
		echo "<td class=\"active\" >Registration</td> <td class=\"active\">Personnel</td><td class=\"active\">Education</td><td class=\"active\">Upload Enclosure</td><td>Payment</td><td>Print Form</td>";
 	} 

	if (stripos($_SERVER['REQUEST_URI'],'step_four') !== false) {
		echo "<td class=\"active\" >Registration</td> <td class=\"active\">Personnel</td><td class=\"active\">Education</td><td class=\"active\">Upload Enclosure</td><td>Payment</td><td>Print Form</td>";
 	} 
?> 

</tr>
</table>--->
</center>

</body>
</html>
