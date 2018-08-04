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
	
<style type="text/css">
	#card{ background: green; /* fallback for old browsers */
  background: -webkit-linear-gradient(to left,  #1e8449  ,  #2ecc71 ); /* Chrome 10-25, Safari 5.1-6 */
  background: linear-gradient(to left,   #2ecc71   ,  #1e8449  ); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
  color : #fff;  
  border-radius: 20px;
  padding-left:50px; 

}
</style>	
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

<div class="container">
  <div class="col-md-12">
    <div class="panel panel-primary" id="bgstyle">
        <div class="panel-heading" style="font-size: 18px; border-radius: 15px 10px;">
          <a href="<?php echo site_url('Course-Registration');?>" style="color: white;">Online Certified Courses / Workshops Enrollment Link</a></div>
     
      </div>
    </div>
</div>

<?php include 'slider.php';?>

</br>
<div class="container ">

	
    <div class="col-md-12">
	 	<div class="panel panel-primary" id="bgstyle">

     	 	<div class="panel-heading" style="font-size: 18px; border-radius: 15px 10px; ">
     	 		<a href="<?php echo site_url('General-Subject-Related-Links');?>" style="color: white;" target=_blank>Online Video Lectures for IIT-JEE & Medical Entrance Exam</a></div>
     
    	</div>
    </div>

    <div class="col-md-12" id="card">
    	<p class="text-center" style="font-size: 22px;text-decoration: underline;color:black;"><b>ANNANT GYAN NEWS FORUM</b></p>

    	<table style="font-size: 18px;width:100%;">
        <tr>
          <td>
            <li>
              We have started enrollment process of different online certified courses/workshops through our website. It is open till 10 October 2018.
            </li>
<li>
We shall start the online certified courses/workshops on 11th October and end at 16 November 2018.
</li>
<li>
“Annant Gyan” is providing earning opportunity to all school teachers/ college professors/ instructors/ persons working in educational organizations/ financially weak graduates*/ financially weak students*/ schools/ colleges/ organizations and “Annant Gyan” marketing heads. Interested people can register themselves on recommender link on our website.
</li>

            </li>
          </td>
        </tr>

    <!--		<tr>
    			<ul>
    				<td><li>Online certified courses/workshops enrollment process is starting on 4<sup>th</sup> August 2018 & end at 10<sup>th</sup> October 2018.  </li></td>
    			</ul>
    		</tr>
    		<tr>
    			<ul>
    				<td><li>Online certified courses/ Workshops will start on 11<sup>th</sup> October 2018 to 16<sup>th</sup> November 2018. </td>
    			</ul>
    		</tr>
      -->
    		<!--<tr>
    			<ul>
    				<td><li>Workshop End Date </li></td>  <td>16<sup>th</sup>-Nov-2018</td>
    			</ul>
    		</tr>-->			

    		<tr>
    			<ul>
    				<td><li>Feedback on 17<sup>th</sup> November 2018.</td>
    			</ul>
    		</tr>
    				
    		</tr>	
    	</table>
    	</br>	
    	<p style="font-size: 18px;">For any query write to admin@annantgyan.com or annantgyan@gmail.com</p>
    </div>

</div>
</br></br>
</br></br>
<?php include 'template/footer.php';?>
</body>
</html>
