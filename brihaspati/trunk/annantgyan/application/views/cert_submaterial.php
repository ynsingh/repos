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


<div class="container ">
	<h2 style="text-align: center;text-decoration:underline;"> General Useful Links </h2>
	<div class="col-md-12"  id="card">
	 	<p style="color: black;font-size: 18px;color: #79522f"> Learning Management System  <a href="http://brihaspati.nmeict.in/brihaspati/servlet/brihaspati/action/myLogin/username/guest/password/guest/lang/english" target=_blank style=";">    Brihaspati </a>  	 </p></br>
    </div>

    <div class="col-md-12"  id="card">
	 	<p style="color: #79522f;text-decoration:underline;font-size: 18px;"> E-Book (PDF)<a href="https://www.pdfdrive.net/" target=_blank style=";">   E-Books</a></p>
    </div>
    
    <div class="col-md-12"  id="card">
	 	<p style="color: #79522f;text-decoration:underline;font-size: 18px;"> Digital Libraries<a href="https://digilibraries.com/" target=_blank style=";">   Library</a></p>
	 		<img src="images/banner.jpg" style="width:100%;height:400px;">
    </div>


</div>
</br></br></br></br></br>

<?php include 'template/footer.php';?>
</body>
</html>
