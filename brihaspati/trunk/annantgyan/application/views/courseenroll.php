<?php
defined('BASEPATH') OR exit('No direct script access allowed');
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
<?php //$chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%&*_";
//echo $password = substr( str_shuffle( $chars ), 0, 8 );?>
<div class="fluid-container">
	<div class="row">
		<div class="col-md-12">
			<img src="<?php echo base_url('images');?>/logo.png" class="img-responsive center-block">
		</div>
	</div>
	
	<div class="row">
			<?php include 'template/header.php';?>

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
	<div class="col-md-3"></div>
<div class="col-md-6" id="">
    	
	     <form action="<?php echo site_url('workshop/ongoing_workshop');?>" method="post"  id="card" > 
	       		
    			<div class="form-group">
    				<label for="name" class="cols-sm-2 control-label" style="font-size: 20px;text-decoration: underline;">Enrollment on course / workshop</label></br>
							<label for="name" class="cols-sm-2 control-label">Your Name</label>
							<div class="cols-sm-3">
								<div class="input-group">
									<span class="input-group-addon"><i class="glyphicon glyphicon-user" aria-hidden="true"></i></span>
									<input type="text" class="form-control" name="name" id="name"  placeholder="Enter your Name"  value="<?php echo $this->session->userdata['su_name']; ?>" readonly/>
								</div>
							</div>
						</div>

					<div class="form-group">
							<label for="email" class="cols-sm-2 control-label">Your Email</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="glyphicon glyphicon-envelope" aria-hidden="true"></i></span>
									<input type="text" class="form-control" name="email" id="email"  placeholder="Enter your Email" value="<?php echo $this->session->userdata['su_emailid']; ?>" readonly />
								</div>
							</div>
						</div>

					<div class="form-group">
							<label for="email" class="cols-sm-2 control-label">Your Place</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="glyphicon glyphicon-home" aria-hidden="true"></i></span>
									<input type="text" class="form-control" name="place" id="email"  placeholder="Enter your Place" value="<?php echo isset($_POST["place"]) ? $_POST["place"] : ''; ?>" required/>
								</div>
							</div>
						</div>	

					<div class="form-group">
							<label for="email" class="cols-sm-2 control-label">Your Nationality</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="glyphicon glyphicon-flag" aria-hidden="true"></i></span>
									<select name="nationality" style="width: 100%;" required>
										<option value="Indian">
											Indain
										</option>
										<option value="Foreign">
											Foreign
										</option>
									</select>
								</div>
							</div>
						</div>			

				<div class="form-group">
    				
							<label for="name" class="cols-sm-2 control-label">Contact Number</label>
							<div class="cols-sm-3">
								<div class="input-group">
									<span class="input-group-addon"><i class="glyphicon glyphicon-earphone" aria-hidden="true"></i></span>
									<input type="text" class="form-control" name="contact" id="name"  placeholder="Enter Your Contact Number" maxlength="10"  value="<?php echo isset($_POST["contact"]) ? $_POST["contact"] : ''; ?>" required/>
								</div>
							</div>
						</div>

				<div class="form-group">
    				
							<label for="name" class="cols-sm-2 control-label">School / College Name (Optional)</label>
							<div class="cols-sm-3">
								<div class="input-group">
									<span class="input-group-addon"><i class="glyphicon glyphicon-book"></i></span>
									<!--<input type="text" class="form-control" name="school" id="name"  placeholder="Enter your School / College" value="<?php //echo isset($_POST["school"]) ? $_POST["school"] : ''; ?>" />-->
									 <textarea class="form-control" name="product_info" rows="1"></textarea>
								</div>
							</div>
						</div>	

				<div class="form-group">
    				
							<label for="name" class="cols-sm-2 control-label">Referral Person (Optional)</label>
							<div class="cols-sm-3">
								<div class="input-group">
									<span class="input-group-addon"><i class="glyphicon glyphicon-user" aria-hidden="true"></i></span>
									<input type="text" class="form-control" name="reperson" id="name"  placeholder="Enter your Referral Person" value="<?php echo isset($_POST["reperson"]) ? $_POST["reperson"] : ''; ?>" />
								</div>
							</div>
						</div>	

				<div class="form-group">
    				
							<label for="name" class="cols-sm-2 control-label">Amount</label>
							<div class="cols-sm-3">
								<div class="input-group">
									<span class="input-group-addon"><i class="glyphicon glyphicon-credit-card" aria-hidden="true"></i></span>
									<input type="text" class="form-control" name="amount" id="name"  placeholder="Enter your amount" value="<?php echo isset($_POST["amount"]) ? $_POST["amount"] : ''; ?>" required/>
								</div>
							</div>
						</div>		
			<?php if(!empty($owid)){?>			
				<input type="text" name="og_id" value="<?php echo $owid;?>">
			<?php }?>	
         

				<div class="form-group ">
						<input type="hidden" name="courseid" value="<?php echo $cid;?>">
						<input type="submit" name="pay"  class="btn btn-success btn-lg btn-block login-button" value="Submit">
						<!--<button type="button" class="btn btn-primary btn-lg btn-block login-button" name="pay">Payment</button>-->
				</div>			

    	</form>
    </div>
 </div>   

</br>
</br>
</br>
</br>
</br>
<?php include 'template/footer.php';?>
</body>
</html>