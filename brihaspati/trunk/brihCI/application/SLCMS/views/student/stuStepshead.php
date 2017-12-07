<!-------------------------------------------------------
    -- @name stuStudenthead.php --	
    -- @author Nagendra Kumar Singh(nksinghiitk@gmail.com) --
--------------------------------------------------------->
<html>
<head>
<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/studentNavbar.css">
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
.currentpage {
        font-weight:bold;
	/*color:#333333; background-color:#38B0DE;background-color:grey;*/  
	color:white;background-color:#38B0DE;
  	 }

.steps{background-color:#cacaca;width:50%;height:50px;}
.stepalign td{text-align:center;font-size:20px;}
.active {
    font-weight: bold;color:white;background-color:#38B0DE;
}

.active1 {
    font-weight: bold;color:white;background-color:black;
}

</style>
</head>
<body>


<div class="container" style="width:100%;" align="left">
	<div class="row">
		<?php if (stripos($_SERVER['REQUEST_URI'],'Step0') !== false) { ?>
			<ul class="breadcrumb ">
    				<li class="new"><a style="padding: 15px 0px 10px 30px;">Registration</a></li>
				<li><a style="padding: 5px 0 10px 45px;">Personnel / Education Detail</a></li>
				<li><a style="padding: 15px 0 10px 45px;">Instructions</a></li>
				<li><a style="padding: 15px 0 10px 45px;">Upload Files</a></li>
				<li><a style="padding: 15px 0 10px 45px;">Payment</a></li>
				<li><a style="padding: 15px 20px 10px 45px;">Print Form</a></li>
			</ul>
		<?php }?>
		<?php if (stripos($_SERVER['REQUEST_URI'],'student_step1') !== false) { ?>
			<ul class="breadcrumb ">
    				<li class="active"><a style="padding: 15px 0px 10px 30px;">Registration</a></li>
				<li class="new"><a style="padding: 5px 0 10px 45px;">Personnel / Education Detail</a></li>
				<li><a style="padding: 15px 0 10px 45px;">Instructions</a></li>
				<li><a style="padding: 15px 0 10px 45px;">Upload Files</a></li>
				<li><a style="padding: 15px 0 10px 45px;">Payment</a></li>
				<li><a style="padding: 15px 20px 10px 45px;">Print Form</a></li>
			</ul>
		<?php }?>
		<?php if (stripos($_SERVER['REQUEST_URI'],'student_step2') !== false) { ?>
			<ul class="breadcrumb ">
    				<li class="active"><a style="padding: 15px 0px 10px 30px;">Registration</a></li>
				<li class="active"><a style="padding: 5px 0 10px 45px;">Personnel / Education Detail</a></li>
				<li class="new"><a style="padding: 15px 0 10px 45px;">Instructions</a></li>
				<li><a style="padding: 15px 0 10px 45px;">Upload Files</a></li>
				<li><a style="padding: 15px 0 10px 45px;">Payment</a></li>
				<li><a style="padding: 15px 20px 10px 45px;">Print Form</a></li>
			</ul>
		<?php }?>
		<?php if (stripos($_SERVER['REQUEST_URI'],'student_step3') !== false) { ?>
			<ul class="breadcrumb ">
    				<li class="active"><a style="padding: 15px 0px 10px 30px;">Registration</a></li>
				<li class="active"><a style="padding: 5px 0 10px 45px;">Personnel / Education Detail</a></li>
				<li class="active"><a style="padding: 15px 0 10px 45px;">Education</a></li>
				<li class="new"><a style="padding: 15px 0 10px 45px;">Upload Files</a></li>
				<li><a style="padding: 15px 0 10px 45px;">Payment</a></li>
				<li><a style="padding: 15px 20px 10px 45px;">Print Form</a></li>
			</ul>
		<?php }?>
		<?php if (stripos($_SERVER['REQUEST_URI'],'Payment') !== false) { ?>
			<ul class="breadcrumb ">
    				<li class="active" ><a style="padding: 15px 0px 10px 30px;">Registration</a></li>
				<li class="active" ><a style="padding: 5px 0 10px 45px;">Personnel / Education Detail</a></li>
				<li class="active" ><a style="padding: 15px 0 10px 45px;">Instructions</a></li>
				<li class="active" ><a style="padding: 15px 0 10px 45px;">Upload Files</a></li>
				<li class="new"><a style="padding: 15px 0 10px 45px;">Payment</a></li>
				<li><a style="padding: 15px 20px 10px 45px;">Print Form</a></li>
			</ul>
		<?php }?>
		<?php if (stripos($_SERVER['REQUEST_URI'],'student_step4') !== false) { ?>
			<ul class="breadcrumb ">
    				<li class="active"><a style="padding: 15px 0px 10px 30px;">Registration</a></li>
				<li class="active"><a style="padding: 5px 0 10px 45px;">Personnel / Education Detail</a></li>
				<li class="active"><a style="padding: 15px 0 10px 45px;">Instructions</a></li>
				<li class="active"><a style="padding: 15px 0 10px 45px;">Upload Files</a></li>
				<li class="new"><a style="padding: 15px 0 10px 45px;">Payment</a></li>
				<li><a style="padding: 15px 20px 10px 45px;">Print Form</a></li>
			</ul>
		<?php }?>
		<?php if (stripos($_SERVER['REQUEST_URI'],'student_step5') !== false) { ?>
			<ul class="breadcrumb ">
    				<li class="active"><a style="padding: 15px 0px 10px 30px;">Registration</a></li>
				<li class="active"><a style="padding: 5px 0 10px 45px;">Personnel / Education Detail</a></li>
				<li class="active"><a style="padding: 15px 0 10px 45px;">Instructions</a></li>
				<li class="active"><a style="padding: 15px 0 10px 45px;">Upload Files</a></li>
				<li class="active"><a style="padding: 15px 0 10px 45px;">Payment</a></li>
				<li class="new"><a style="padding: 15px 20px 10px 45px;">Print Form</a></li>
			</ul>
		<?php }?>
	
	</div>
</div>

<!---<table class="steps">
<tr class="stepalign">

<?php 
//echo	$stp1= $this->resstep->step1_status;
  //                      $stp2= $this->resstep->step2_status;
    //                    $stp3= $this->resstep->step3_status;
      //                  $stp4= $this->resstep->step4_status;
        //                $stp5= $this->resstep->step5_status;

	if (stripos($_SERVER['REQUEST_URI'],'Step0') !== false) {
		echo "<td>Registration</td><td>Personnel / Education Detail</td><td>Instructions</td><td>Upload File</td> <td>Payment</td><td>Print Form</td>";
  	}  
	//if (stripos($_SERVER['REQUEST_URI'],'student_step0')  !== false) {
		//echo "<td >Step 0</td> <td>Step 1</td><td>Instructions</td><td>Upload File</td> <td>Payment</td><td>Print Form</td> ";
 	//} 
	if (stripos($_SERVER['REQUEST_URI'],'student_step1') !== false) {
		echo "<td class=\"active\" >Registration</td> <td>Personnel / Education Detail</td><td>Instructions</td><td>Upload File</td> <td>Payment</td><td>Print Form</td>";
 	} 
	if (stripos($_SERVER['REQUEST_URI'],'student_step2') !== false) {
		echo "<td class=\"active\" >Registration</td> <td class=\"active\">Personnel / Education Detail</td><td>Instructions</td><td>Upload File</td><td>Payment</td><td>Print Form</td>";
 	} 
	if (stripos($_SERVER['REQUEST_URI'],'student_step3') !== false) {
		echo "<td class=\"active\" >Registration</td> <td class=\"active\">Personnel / Education Detail</td><td class=\"active\">Instructions</td><td>Upload File</td><td>Payment</td><td>Print Form</td>";
 	} 
	if (stripos($_SERVER['REQUEST_URI'],'Payment') !== false) {
		echo "<td class=\"active\" >Registration</td> <td class=\"active\">Personnel / Education Detail</td><td class=\"active\">Instructions</td><td class=\"active\">Upload File</td><td>Payment</td><td>Print Form</td>";
 	} 

	if (stripos($_SERVER['REQUEST_URI'],'student_step4') !== false) {
		echo "<td class=\"active\" >Registration</td> <td class=\"active\">Personnel / Education Detail</td><td class=\"active\">Instructions</td><td class=\"active\">Upload File</td><td>Payment</td><td>Print Form</td>";
 	} 
?> 

</tr>
</table>--->


</body>
</html>
