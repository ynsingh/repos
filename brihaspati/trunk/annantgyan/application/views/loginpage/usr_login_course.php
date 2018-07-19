<?php
if(isset($this->session->userdata['su_name'])){

defined('BASEPATH') OR exit('No direct script access allowed');
?><!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Annant Gyan</title>
	<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE, NO-STORE, must-revalidate">
	<META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE">
	<META HTTP-EQUIV="EXPIRES" CONTENT=0>
	
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
			<?php //$this->load->view('template/login_header.php');
			?>

	</div>	

</div>	
<div id="menu">  
	<table style="width: 100%;"><tr><td align=center><span><h3>Select Course</h3></span></td>
		<td><a href="<?php echo site_url('login/usr_login');?>" style="color:white;font-size: 20px;"><span class="glyphicon glyphicon-dashboard"></span> Dashboard</a></td>
	</tr></table>

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
</br>

<div class="container">
	<div class="col-md-1">	</div>
<form action="<?php echo site_url('login/course_login');?>" method="POST" >
	<div class="col-md-7">
		<label style="font-size: 20px;color: #79522f;">Select Your Course :</label>
			<select name="cou_type" required="">
				<option value="" selected="true" disabled="disabled">Select Your Course</option>
				<?php
					if($course_data){
					foreach ($course_data as $row) {
						$cname = $this->commodel->get_listspfic1('courses','cou_name','cou_id',$row->uct_courseid)->cou_name;
						echo "<option value='$row->uct_courseid'>".$cname."</option>";
					}}else{
						foreach($course_data1 as $row1){
							echo "<option value='$row1->cou_id'>".$row1->cou_name."</option>";
						}
						//echo "<option value=''>".'You are not registered in any course.'."</option>";
					}
					//if($ustring == 'NULL'){
						//foreach($course_data1 as $row1){
							//$cname = $this->commodel->get_listspfic1('courses','cou_name','cou_id',$row->ow_courseid)->cou_name;
							//echo "<option value='$row1->cou_id'>".$row1->cou_name."</option>";
						//}	
					//}
				?>
			</select>

	</div>
	<div class="col-md-3">
			<input type="submit" name="submit" value="Submit" class="btn btn-success">
		</div>
	</form>	
</div>


<?php $this->load->view('template/footer.php');?>
</body>
</html>
<?php }else{
$this->load->view('signin');
}
?>