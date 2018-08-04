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

<div class="fluid-container">
	<center><h2>Registered user details</h2></center>
	<div class="col-md-12" style="overflow: scroll;height: 500px;overflow-x: hidden;">
		
		<table class="table table-bordered">
			<thead style="font-size: 18px;">
				<tr  class="info">
					<th>Sr. No.</th><th>Name</th><th>Email-Id</th><th>Place</th><th>Nationality</th>
					<th>Contact</th><th>School/College</th><th>Reference person</th><th>Course Name</th>
					<!--<th>Fees Amount</th>--><th>Fees Amount</th><th>Payment Status</th>

				</tr>
			</thead>
			<tbody>
				<?php 
						$i=1;
						if(!empty($userdata)){
							foreach($userdata as $row){	
							if(!empty($row->ow_courseid)){	
								$couname = $this->commodel->get_listspfic1('courses','cou_name','cou_id',$row->ow_courseid)->cou_name;
							}		
						?>
				<tr>
					
								<td><?php echo $i++;?></td>
								<td><?php echo $row->ow_name;?></td>
								<td><?php echo $row->ow_email;?></td>
								<td><?php echo $row->ow_place;?></td>
								<td><?php echo $row->ow_nationality;?></td>

								<td><?php echo $row->ow_contact;?></td>
								<td><?php echo $row->ow_school;?></td>
								<td><?php echo $row->ow_rperson;?></td>
								<td><?php if(!empty($couname)){echo $couname;}?></td>
								<!--<td><?php //echo $row->ow_bankname;?></td>-->
								<td><?php echo $row->ow_amount;?></td>
								
								<td><?php echo $row->ow_paymentstatus;?></td>
								<?php //if($row->su_status == 'Verified'){?>
									<!--<td><span style="color:green;"><?php //echo $row->su_status;?></span></td>-->
								<?php //}else{?>
									<!--<td><span style="color:red;"><?php //echo 'Not Verified';?></span></td>-->
								<?php //}?>	
					
				</tr>
				<?php }}?>
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