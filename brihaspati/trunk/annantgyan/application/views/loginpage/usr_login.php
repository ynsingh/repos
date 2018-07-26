<?php

//if(isset(($this->session->userdata['su_name'])) || (isset($this->session->userdata['first_name']))){
//if(($this->session->userdata['first_name']) || ($this->session->userdata['su_name'])){

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
   <!--<script src="<?php //echo base_url('assets/js');?>/course.js"></script>
        --<link href="<?php //echo base_url('assets/css');?>/bootstrap.min1.css" rel="stylesheet">-->
            
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
      $(document).ready(function () {

    $(".next-step").click(function (e) {

        var $active = $('.nav-tabs li.active');
        $active.next().removeClass('disabled');
        nextTab($active);

    });
    $(".prev-step").click(function (e) {

        var $active = $('.nav-tabs li.active');
        prevTab($active);

    });
});
function nextTab(elem) {
    $(elem).next().find('a[data-toggle="tab"]').click();
}
function prevTab(elem) {
    $(elem).prev().find('a[data-toggle="tab"]').click();
}   

function toggleIcon(e) {
    $(e.target)
        .prev('.panel-heading')
        .find(".more-less")
        .toggleClass('glyphicon-plus glyphicon-minus');
}
$('.panel-group').on('hidden.bs.collapse', toggleIcon);
$('.panel-group').on('shown.bs.collapse', toggleIcon);
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
			<?php $this->load->view('template/login_header.php');
			?>

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

<div class="container" style="margin-top: 10px;border:2px solid orange;border-radius: 15px 15px 15px 15px;" id="card">
	<div class="row">
		<div class="col col-sm-4">
    	    <ul class="nav nav-tabs nav-stacked text-center" role="tablist">
                <li role="presentation" class="active"><a href="#intro" aria-controls="home" role="tab" data-toggle="tab">Education in India</a> </li>
                <li role="presentation"><a href="#wcc" aria-controls="profile" role="tab" data-toggle="tab">Dr. APJ Human Values Video </a></li>
                <li role="presentation"><a href="#feedback" aria-controls="messages" role="tab" data-toggle="tab">Vision of Teacher</a></li>
                <li role="presentation"><a href="#ocg" aria-controls="messages" role="tab" data-toggle="tab">Indian School Education System </a></li>
                
            </ul>

           
		</div>
        <div class="col col-sm-8" style="font-size: 18px;">
            <div class="row tab-content">
                <div role="tabpanel" class="tab-pane fade active in" id="intro">
                <?php 
                    $intro = 'uploads/course/'.$couid.'/content/CH.18.pdf';
                    if(file_exists($intro)) {
                ?>    
                   <embed src="<?php echo base_url($intro);?>" type="application/pdf"   height="450px" width="100%">
                <?php 
                    }
                ?>    
                </div>
                <div role="tabpanel" class="tab-pane fade" id="wcc">
                    
                        <div class="video">
                           <iframe src="https://www.youtube.com/embed/VVsrsOaEcFQ?wmode=opaque" frameborder="0"  allowfullscreen  height="450px" width="100%"></iframe>
                        </div>
                    
                     
                </div>
                <div role="tabpanel" class="tab-pane fade" id="feedback">
                   <?php 
                    $feedback = 'uploads/course/'.$couid.'/content/JVC Vol 1.pdf';
                    if(file_exists($feedback)) {
                ?>    
                   <embed src="<?php echo base_url($feedback);?>" type="application/pdf"   height="450px" width="100%">
                <?php 
                    }
                ?>   
                </div>
                 <div role="tabpanel" class="tab-pane fade" id="ocg">
                  <?php 
                    $feedback = 'uploads/course/'.$couid.'/content/p1.pdf';
                    if(file_exists($feedback)) {
                ?>    
                   <embed src="<?php echo base_url($feedback);?>" type="application/pdf"   height="450px" width="100%">
                <?php 
                    }
                ?>   
                </div>
            </div>
		</div>
	</div>
    <div class="row" style="border:0px solid black;">
        <ul class="list-inline pull-right">
            <li><button type="button" class="btn btn-default prev-step"><span class="glyphicon glyphicon-chevron-left"></span> Previous</button></li>
            <li><button type="button" class="btn btn-default next-step">Next <span class="glyphicon glyphicon-chevron-right"></span></button></li>
           <!-- <li><button type="button" class="btn btn-primary ">Save</button></li>-->
        </ul>                
    </div>
</div>
      
</br></br></br></br></br>
<?php $this->load->view('template/footer.php');?>
</body>
</html>
<?php //}else{
//$this->load->view('signin');
//}
?>