<?php
defined('BASEPATH') OR exit('No direct script access allowed');
if(isset($this->session->userdata['firstName'])){
?><!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Annant Gyan</title>
	
		<link href="<?php echo base_url('assets/css');?>/style.css" rel="stylesheet">
		<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
		<!--<link href="<?php //echo base_url('assets/css');?>/bootstrap.min1.css" rel="stylesheet">-->
	  	<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
      		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
	


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

<div class="container">
	<center><h2>View Student Exam List</h2></center>
	<div class="col-md-12" id='card'>
		
<!--		
		<table style="font-size:18px;"><tr>
			<td><a href="<?php //echo site_url('admin/upload_fileview');?>">View Upload Content</a></td>
			<td> &nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td><a href="<?php //echo site_url('admin/createexam');?>">Create Exam</a></td>
		</tr></table>
-->
		<form name="frm" id="frmid" action="<?php echo site_url('admin/displayqas')?>" method="POST" enctype="multipart/form-data">
			<table style="font-size:18px;"><tr><td>
                        <label for="crsname" class="cols-sm-2 control-label">Course Name</label>
                        <br>
                        <select name="cou_name"  style="width:200px;" >
                                <option value="" selected="" disabled="">Select Course Name</option>
                                <?php if(!empty($coudata)){
                                        foreach($coudata as $row){
                                ?>
                                        <option value="<?php echo $row->cou_id;?>"><?php echo $row->cou_name ."( ".$row->cou_code ." )";?></option>
                                <?php }}?>
			</select>
			</td><td>
				&nbsp;
			</td><td>
                         <label for="testname" class="cols-sm-2 control-label">Test Name</label>
                        <br>
                        <select name="testname"  style="width:200px;" >
                                <option value="" selected="" disabled="">Select Test Name</option>
                                <?php if(!empty($testdata)){
                                        foreach($testdata as $row){
                                ?>
                                        <option value="<?php echo $row->testid;?>"><?php echo $row->testname ."( ".$row->testcode ." )";?></option>
                                <?php }}?>
			</select>
			</td><td>
				&nbsp;
			</td><td>
			<br>
			<input type="submit" name="testres" class="btn btn-success submit" value="Submit">	
			</td></tr></table>
		</form>
		<table style="font-size:18px;"><tr>
		</tr></table>

		<table class="table table-bordered">
			<thead style="font-size: 18px;">
				<tr  class="info">
					<th>Sr. No.</th><th>Student Name</th>
					<!--<th>Course Week</th><th>Test Name(Test Code)</th><th>Test Description</th><th>Test Date</th><th>Test Time</th><th>Total Questions</th> <th>Total Students</th> -->
					<th> Actions</th>
				</tr>
			</thead>
			<tbody>
				<?php 
						$i=1;
						if(!empty($studata)){
							foreach($studata as $row){	
								$sname = $this->commodel->get_listspfic1('sign_up','su_name','su_id',$row->su_id)->su_name;
						?>
				<tr>
					
								<td><?php echo $i++;?></td>
								<td><?php echo $sname;?></td>
				<!--				<td><?php //echo $row->acu_weekname;?></td>
								<td><?php //echo $row->testname." ( ". $row->testcode." )";?></td>
								<td><?php //echo $row->testdesc;?></td>
								<td><?php //echo $row->testdate;?></td>
								<td><?php //echo $row->testfrom." - ".$row->testto   ;?></td>
								<td><?php //echo $row->totalquestions  ;?></td>
								<td><?php //echo $row->attemptedstudents ;?></td> -->
								<td><?php 
								$datadup = array('st_stdid' => $row->su_id, 'st_testid' => $testid,'st_subid' =>$subid);
						                $isexist=$this->commodel->isduplicatemore('studenttest',$datadup);
						                if(!$isexist){
									echo anchor('admin/studentquesans/' . $testid."/".$subid."/".$row->su_id , "View Question Answer") ;
						                }else{
						                        echo "Verified";
                						}

									//echo anchor('admin/addquestion/' . $row->testid."/".$row->subid , "Add Question") ;
//								echo "&nbsp;| ";
								//	echo anchor('admin/studentquesans/' . $testid."/".$subid."/".$row->su_id , "View Question Answer") ;
//								echo "&nbsp;| ";
									//echo anchor('admin/delete_test/' . $row->testid , "Delete", array('title' => 'Delete Details' , 'class' => 'red-link','onclick' => "return confirm('Are you sure you want to delete this record')")) . " ";
								echo "&nbsp; ";
						//		echo anchor(base_url().'/'. $row->acu_contpath.'/'.$row->acu_filename , "View", array('title' => 'View Details' , 'class' => 'red-link', 'target'=>'blank')) . " ";
								?> 
								</td>
								
				</tr>
				<?php 		}
						}
						else{ ?>
							<tr>
							<td colspan=10 align=center> No Records found</td>
							</tr>
					<?php
						} ?>

				
			</tbody>
		</table>
	</div>
	
</div>
</br></br></br></br>
<?php $this->load->view('template/footer.php');?>
</body>
</html>
<?php }else{
$this->load->view('admin/admin_login');
}
?>
