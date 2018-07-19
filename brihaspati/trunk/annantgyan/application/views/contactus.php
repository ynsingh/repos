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

				<div class="container" id="card">
 					<div class="col-md-6 col-sm-6" >
                        <div class="contact-left">
                            <div class="address" style="font-size: 20px;">
                                <h5  style="font-size: 20px;color: #79522f;"><b>Address:</b></h5>
                                <p>
                                    Naya Bhojpur, District Buxar, Bihar-802133</p>
                            </div>
                            <div class="address address-mdl"  style="font-size: 20px;">
                                <h5  style="font-size: 20px;color: #79522f;"><b>Phone Number:</b></h5>
                                <p>
                                   7987828714</p>
                                
                              <h5  style="font-size: 20px;color: #79522f;"><b>Email:</b></h5>
                                <p>
                                    <a href="mailto:info@example.com">annantgyan@gmail.com</a>
                                </p>
                                
                            </div>
                        </div>
                    </div>

                    <div class="col-md-6">
                    	<div >
                    		 <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3598.733646468064!2d84.15199401541612!3d25.580526822318042!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x399278efe70290dd%3A0x83e80a6acf8b74a!2sAnnant+Gyan+Knowledge+and+Skills+Private+Limited!5e0!3m2!1sen!2sin!4v1527677241490" allowfullscreen style="height: 300px;width: 100%;"></iframe>
                    	</div>	 
                    </div>	
                </div>    	


<?php include 'template/footer.php';?>
</body>
</html>