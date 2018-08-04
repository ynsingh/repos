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
	


		<script type="text/javascript">
	
!function($,n,e){var o=$();$.fn.dropdownHover=function(e){return"ontouchstart"in document?this:(o=o.add(this.parent()),this.each(function(){function t(e){o.find(":focus").blur(),h.instantlyCloseOthers===!0&&o.removeClass("open"),n.clearTimeout(c),i.addClass("open"),r.trigger(a)}var r=$(this),i=r.parent(),d={delay:100,instantlyCloseOthers:!0},s={delay:$(this).data("delay"),instantlyCloseOthers:$(this).data("close-others")},a="show.bs.dropdown",u="hide.bs.dropdown",h=$.extend(!0,{},d,e,s),c;i.hover(function(n){return i.hasClass("open")||r.is(n.target)?void t(n):!0},function(){c=n.setTimeout(function(){i.removeClass("open"),r.trigger(u)},h.delay)}),r.hover(function(n){return i.hasClass("open")||i.is(n.target)?void t(n):!0}),i.find(".dropdown-submenu").each(function(){var e=$(this),o;e.hover(function(){n.clearTimeout(o),e.children(".dropdown-menu").show(),e.siblings().children(".dropdown-menu").hide()},function(){var t=e.children(".dropdown-menu");o=n.setTimeout(function(){t.hide()},h.delay)})})}))},$(document).ready(function(){$('[data-hover="dropdown"]').dropdownHover()})}(jQuery,this);
		</script>

<script type="text/javascript">
	//$( document ).ready( function() {
   // $( "#signup1" ).click( function() {
    //    $( ".form1" ).toggle( 'slow' );
    //});
//});
</script>

<script type="text/javascript">
//	$( document ).ready( function() {
    //$( "#signup2" ).click( function() {
     //   $( ".form1" ).toggle( 'slow' );
   // });
//});
</script>		

<script type="text/javascript">
	//$( document ).ready( function() {
   // $( "#signup3" ).click( function() {
    //    $( ".form1" ).toggle( 'slow' );
    ////});
//});
</script>

<script type="text/javascript">
	//$( document ).ready( function() {
  //  $( "#signup4" ).click( function() {
   //     $( ".form1" ).toggle( 'slow' );
  //  });
//});
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
	<div align="justify" id="card">
		If you face any problem in enrollment process. Kindly feel free to contact Mr. N K Singh (nksinghiitk@gmail.com )(9450136012).
		<br>
		If you got error page in enrollment process, please take screenshot and send to nksinghiitk@gmail.com
	</div>
	<h2 style="text-align: center;text-decoration:underline;">Online Certified Courses/Workshops </h2>
	<div class="col-md-12" style="/*background: white;padding: 4em; box-shadow: 5px 10px 18px #888888;*/" >

<div class="col-md-1"></div>
    <div class="col-md-9" id="card">
    	<table style="font-size: 16px;width:100%;">
        <?php $i=1;foreach($course_data as $row){?>
    		<tr>
    		
    			<td>
    				
    		<b><?php echo $i++;?> .</b> <?php echo $row->cou_name; echo " <br> "; echo $row->cou_eligible; ?>     
    	   </br>
           ( <?php echo $row->cou_discipline; ?> )</br>
    			</td>

    			<td valign="top">
    				<a href="<?php echo site_url('workshop/courseenroll'); echo "/"; echo $row->cou_id;?>" >
    					<button type="button" class="btn btn-primary" align="left" id="signup1" title="Click to open enroll form." style="width:100%;">Enroll</button>
    				</a>
    			</td>
    		  
    		</tr>	
        
    		<tr height=20></tr>
           <?php }?> 
           <!-- <tr>
            
                <td>
                    
            <b>2.</b> Magical and Logical Power of Vedic Mathematics </br>(For diploma, BE, BBA, ME & MBA and Competitive Exam Students)
        </br>
(Fees for candidate -300/- resident in India & 100$ outside India)
                </td>

                <td valign="top">
                    <a href="<?php //echo site_url('Registration');?>">
                        <button type="button" class="btn btn-primary" align="left" id="signup5" title="Click to open enroll form.">Enroll</button>
                    </a>
                </td>
              
            </tr>   
            <tr height=20></tr>
    		<tr>
    		
    			<td>
    				<b>3.</b> Logical Power of Vedic Mathematics</br>
(For class 7 th to 12 th Standard Students)</br>
(Fees for candidate -250/- resident in India & 80$ outside India)
    			</td>

    			<td valign="top"><a href="<?php //echo site_url('Registration');?>">
    				<button type="button" class="btn btn-primary" align="left" id="signup2" title="Click to open enroll form.">Enroll</button>
    				</a>
    			</td>
    		  
    		</tr>	
<tr height=20></tr>
    		<tr>
    		
    			<td>
    			<b>4.</b> Applicaton of Vedic Mathematics for Kids</br>
(for Class 4 rd to 6 th Standard Students)</br>
(Fees for candidate -200/- resident in India & 60$ outside India)
    			</td>

    			<td valign="top"><a href="<?php //echo site_url('Registration');?>">
    				<button type="button" class="btn btn-primary" align="left" id="signup3" title="Click to open enroll form.">Enroll</button>
    				</a>
    			</td>
    		  
    		</tr>	
<tr height=20></tr>
    <!--		<tr>
    		
    			<td>
    		<b>5.</b> Mathematical Magic and Fun </br>(Open to all Fees for candidate -150/- resident in India & 50$ outside Indial)
    			</td>

    			<td valign="top"><a href="<?php //echo site_url('Registration');?>">
    				<button type="button" class="btn btn-primary" align="left" id="signup4" title="Click to open enroll form.">Enroll</button>
    				</a>
    			</td>
    		  
    		</tr>	-->

    		
    	</table>	
    </div>	
   
    		

	</div>
</div>

<br><br><br><br>    
<?php include 'template/footer.php';?>
</body>
</html>
