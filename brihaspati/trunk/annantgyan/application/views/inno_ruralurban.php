<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?><!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Annant Gyan</title>
	
		<link href="<?php echo base_url('assets/css');?>/style.css" rel="stylesheet">
		
		<!--<link href="<?php //echo base_url('assets/css');?>/bootstrap.min1.css" rel="stylesheet">-->
	       <link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <!--<link href="<?php //echo base_url('assets/css');?>/bootstrap.min1.css" rel="stylesheet">-->
      <script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
      <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Oleo+Script:400,700" rel="stylesheet">
   	<link href="https://fonts.googleapis.com/css?family=Teko:400,700" rel="stylesheet">
   	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

		<script type="text/javascript">
	
!function($,n,e){var o=$();$.fn.dropdownHover=function(e){return"ontouchstart"in document?this:(o=o.add(this.parent()),this.each(function(){function t(e){o.find(":focus").blur(),h.instantlyCloseOthers===!0&&o.removeClass("open"),n.clearTimeout(c),i.addClass("open"),r.trigger(a)}var r=$(this),i=r.parent(),d={delay:100,instantlyCloseOthers:!0},s={delay:$(this).data("delay"),instantlyCloseOthers:$(this).data("close-others")},a="show.bs.dropdown",u="hide.bs.dropdown",h=$.extend(!0,{},d,e,s),c;i.hover(function(n){return i.hasClass("open")||r.is(n.target)?void t(n):!0},function(){c=n.setTimeout(function(){i.removeClass("open"),r.trigger(u)},h.delay)}),r.hover(function(n){return i.hasClass("open")||i.is(n.target)?void t(n):!0}),i.find(".dropdown-submenu").each(function(){var e=$(this),o;e.hover(function(){n.clearTimeout(o),e.children(".dropdown-menu").show(),e.siblings().children(".dropdown-menu").hide()},function(){var t=e.children(".dropdown-menu");o=n.setTimeout(function(){t.hide()},h.delay)})})}))},$(document).ready(function(){$('[data-hover="dropdown"]').dropdownHover()})}(jQuery,this);
		</script>

<style type="text/css">
	
/*Contact sectiom*/
.content-header{
  font-family: 'Oleo Script', cursive;
  color:#fcc500;
  font-size: 45px;
}

.section-content{
  text-align: center; 
 
}
#contact{
    -webkit-box-shadow: 5px 5px 15px 5px #000000; 
	box-shadow: 5px 5px 15px 5px #000000;
    font-family: 'Teko', sans-serif;
  padding-top: 0px;
  width: 100%;
  
 
  background: green; /* fallback for old browsers */
  background: -webkit-linear-gradient(to left,  #1e8449  , #28b463); /* Chrome 10-25, Safari 5.1-6 */
  background: linear-gradient(to left,  #28b463  ,  #1e8449  ); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
  color : #fff;    
}
.contact-section{
  padding-top: 40px;
}
.contact-section .col-md-6{
  width: 49%;
}

.form-line{
  border-right: 1px solid #B29999;
}

.form-group{
  margin-top: 10px;
}
label{
  font-size: 1.3em;
  line-height: 1em;
  font-weight: normal;
}
.form-control{
  font-size: 1.3em;
  color: #080808;
}
textarea.form-control {
    height: 135px;
   /* margin-top: px;*/
}

.submit{
  font-size: 1.1em;
  float: right;
  width: 150px;
  background-color: transparent;
  color: #fff;

}
</style>
<!-- Google Tag Manager -->
<script>(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':
new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],
j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=
'https://www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);
})(window,document,'script','dataLayer','GTM-5478WML');</script>
<!-- End Google Tag Manager -->
<!-- Global site ad - Google Ad -->
        <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
        <script>
          (adsbygoogle = window.adsbygoogle || []).push({
                google_ad_client: "ca-pub-2236648546875938",
                enable_page_level_ads: true
                });
        </script>
<!-- Global site tag (gtag.js) - Google Analytics -->

<script async src="https://www.googletagmanager.com/gtag/js?id=UA-64641398-3"></script>

<script>

  window.dataLayer = window.dataLayer || [];

  function gtag(){dataLayer.push(arguments);}

  gtag('js', new Date());


  gtag('config', 'UA-64641398-3');

</script>
	
</head>


<body>
<!-- Google Tag Manager (noscript) -->
<noscript><iframe src="https://www.googletagmanager.com/ns.html?id=GTM-5478WML"
height="0" width="0" style="display:none;visibility:hidden"></iframe></noscript>
<!-- End Google Tag Manager (noscript) -->


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
        <?php echo form_error('<div class="alert-warning">','</div>');?>
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

<div class="container ">
	<h2 style="text-align: center;text-decoration:underline;">Innovative ideas for rural / urban area people</h2>
	<div class="col-md-12"  id="card">
	 	<p style="color: black;font-size: 18px;">
	 		We shall offer different innovative ideas for growth and development of rural/ urban area people as per their need. </br>
			“Annant Gyan Knowledge and Skills Private Limited” is providing the collaboration opportunity to interested individuals for national and self development. We shall provide our full technical and professional support to maintain the quality. Our offer is - </br>

		<ul style="font-size: 16px;">
			<li>To establish Annant Gyan skill development/ innovative training and research centers in different fields</li>
			<li>To open Annant Gyan schools/ colleges</li>
		</ul>
	 	</p>
	 	
	 	
	 		
	 	</p>
    </div>
    <div class="row">
    	<div class="col-md-12 col-sm-12">
    		<section id="contact">
			<div class="section-content">
				<h1 class="section-header"> <span class="content-header wow fadeIn " data-wow-delay="0.2s" data-wow-duration="2s"> Enquiry Form</span></h1>
				
			</div>
			<div class="contact-section">
			<div class="container">
				<form action="<?php echo site_url('login/usr_enquiry');?>" method="POST">
					<div class="col-md-6 form-line">
			  			<div class="form-group">
			  				<label for="exampleInputUsername">Your name</label>
					    	<input type="text" class="form-control" id="" placeholder=" Enter Name" name="usr_name">
				  		</div>
				  		<div class="form-group">
					    	<label for="exampleInputEmail">Email-Id</label>
					    	<input type="email" class="form-control" id="exampleInputEmail" placeholder=" Enter Email id" name="usr_email">
					  	</div>	
					  	<div class="form-group">
					    	<label for="telephone">Mobile No.</label>
					    	<input type="tel" class="form-control" id="telephone" placeholder=" Enter 10-digit mobile no." MaxLength="10" pattern="/^+91(7\d|8\d|9\d)\d{9}$/" name="usr_mobile">
			  			</div>
			  		</div>
			  		<div class="col-md-6">
			  			<div class="form-group">
			  				<label for ="description">Interested In</label>
			  			 	<select class="form-control" name="usr_interest">
			  			 		<option value="" selected="" disabled="">Select Any</option>
			  			 		<option value="To Open School">To Open School</option>
			  			 		<option value="To Open Skill Development Center">To Open Skill Development Center</option>
			  			 		<option value="To Open Training Center">To Open Training Center</option>
			  			 		<!--<option></option>-->
			  			 	</select>
			  			</div>

			  			<div class="form-group">
			  				<label for ="description">Other Query</label>
			  			 	<textarea  class="form-control" id="description" placeholder="Enter Your Message" name="usr_othqu"></textarea>
			  			</div>
			  			<div>
			  				<input type="submit" name="submit" class="btn btn-default submit" value="Send Enquiry">
			  			</div>
			  			
					</div>
				</form>
			</div>
		</section>
    	</div>
    	
    </div>

</div>
</br></br></br></br></br>

<?php include 'template/footer.php';?>
</body>
</html>
