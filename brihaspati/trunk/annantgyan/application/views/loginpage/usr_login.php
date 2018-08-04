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
<style type="text/css">
    #open{ background: green; /* fallback for old browsers */
  background: -webkit-linear-gradient(to left,  #1e8449  ,  #2ecc71 ); /* Chrome 10-25, Safari 5.1-6 */
  background: linear-gradient(to left,   #2ecc71   ,  #1e8449  ); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
  color : #fff;  
  border-radius: 0px;
  padding:20px 20px 0px 20px; 

}
</style>
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


jQuery(function ($) {
    var $active = $('#accordion .panel-collapse.in').prev().addClass('active');
    $active.find('a').append('<span class="glyphicon glyphicon-minus pull-right"></span>');
    $('#accordion .panel-heading').not($active).find('a').prepend('<span class="glyphicon glyphicon-plus pull-right"></span>');
    $('#accordion').on('show.bs.collapse', function (e)
    {
        $('#accordion .panel-heading.active').removeClass('active').find('.glyphicon').toggleClass('glyphicon-plus glyphicon-minus');
        $(e.target).prev().addClass('active').find('.glyphicon').toggleClass('glyphicon-plus glyphicon-minus');
    });
    $('#accordion').on('hide.bs.collapse', function (e)
    {
        $(e.target).prev().removeClass('active').find('.glyphicon').removeClass('glyphicon-minus').addClass('glyphicon-plus');
    });
});
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

<?php 

if('10-oct-2018'){ //echo $this->session->userdata['su_id'];?>
<div class="container">
    <div class="col-md-12" id="card">
        <p style="font-size: 20px;" class="text-center">Your Course Will be start at 10<sup>th</sup>-oct-2018.</p>
    </div>
    
</div>

<?php }else{
$whdata1=array('crsann_crsid' => $couid);
$sdata1='crsann_crsstart,crsann_crsend';
$coursedate=$this->commodel->get_listspficemore('courseannouncement',$sdata1,$whdata1);
$cdate = date('Y-m-d');
foreach($coursedate as $coudata){
    $startdate = $coudata->crsann_crsstart;
    $enddate = $coudata->crsann_crsend;
    $cdate = date('Y-m-d');
if($enddate >= $cdate){
    ?>
 <center><h3>Course :- <?php echo $this->commodel->get_listspfic1('courses','cou_name','cou_id',$couid)->cou_name;?></h3></center>
<div class="container" style="margin-top: 10px;border:2px solid orange;border-radius: 15px 15px 15px 15px;" id="card">
	<div class="row">
		<div class="col col-sm-12">

           
               
                            
            <div class="col-md-4">
                 <?php 
                      
            //    if(!empty($getuploadata)){
                   // foreach($getuploadata as $row){
                   // $weekname = $row->acu_weekname;
                                   
                ?>
                    <?php //echo $weekname.'<br>';?>

                                <ul class="nav nav-tabs nav-stacked" role="tablist">
                                    <li role="presentation" class="active">
                               <?php 
                               foreach($getuploadata as $data){
                                 echo '<br><div style="font-size:20px;" id="open">'.$data->acu_weekname.'</div>';

                                    $whdata1 = array('acu_courseid' => $couid,'acu_weekname' => $data->acu_weekname) ;
                                    $sdata1 = 'acu_weekcontname,acu_seqno,acu_contpath,acu_filename';
                                    $getcontname = $this->commodel->get_distinctrecord('admin_conteupload',$sdata1,$whdata1);

                                    foreach($getcontname as $row1){
                                        $fpath1 = $row1->acu_contpath;
                                        $fname1 = $row1->acu_filename;
                                        $fpathname1 = $fpath1.'/'.$fname1;
                                 ?>
                                 
                                 <a href="<?php echo base_url().'/'.$fpathname1?>" target="content" id="close">
                                        <?php echo $row1->acu_weekcontname;?>
                                 </a>       
                        <?php }}//}?>
                                </li>
                                </ul>
                <?php //}}//}?>
            </div>
                          
                        
            
                   <div class="col-md-8">  
                        <?php  if(file_exists($fpathname1)) {?> 
                            <div id="content"><iframe name="content" src="<?php echo base_url($fpathname1);?>" style="width:100%;height:500px;"></iframe></div>
                        <?php }?>    
                        </div>  
           
		</div>

        
            </div>
		</div>
	</div>
    <!--<div class="row" style="border:0px solid black;">
        <ul class="list-inline pull-right">
            <li><button type="button" class="btn btn-default prev-step"><span class="glyphicon glyphicon-chevron-left"></span> Previous</button></li>
            <li><button type="button" class="btn btn-default next-step">Next <span class="glyphicon glyphicon-chevron-right"></span></button></li>
           <!-- <li><button type="button" class="btn btn-primary ">Save</button></li>--
        </ul>                
    </div>-->
</div>
<?php }}}//elseif and foreach close?>      
</br></br></br></br></br>
<?php $this->load->view('template/footer.php');?>
</body>
</html>
<?php //}else{
//$this->load->view('signin');
//}
?>