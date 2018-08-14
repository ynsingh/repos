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
	<table align="center"><tr><td>
	<h2 style="text-align: center;text-decoration:underline;">Online Certified Courses/Workshops </h2>
	</td>
	<td>&nbsp;</td>
	<td style="margin-left:60px;"> &nbsp;
	<a href="<?php echo base_url('docs'); ?>/Course&workshopcalender.pdf" target="_blank">
        <button type="button" class="btn btn-primary" align="left" id="signup1" title="Click to open Course Calendar." style="width:100%;">Course Calendar</button>
       </a>
	</td>
	<td>&nbsp;</td>
	<td style="margin-left:60px;"> &nbsp;
	<a href="<?php echo base_url('docs'); ?>/Structureofonlinecoursesworkshops.pdf" target="_blank">
        <button type="button" class="btn btn-primary" align="left" id="signup1" title="Click to open Course Structure." style="width:100%;">Course Structure</button>
       </a>
</td></tr></table>
<div class="col-md-3"></div>
	<!--<div class="col-md-12" style="/*background: white;padding: 4em; box-shadow: 5px 10px 18px #888888;*/" >-->
<!--
  <div class="row">
      <div class="col-md-6">
         <h3 style="text-align: center;">Course Structure</h3>
      </div> 
      <div class="col-md-6">
        <h3 style="text-align: center;">Open Course List</h3>
      </div>  
  </div>
-->
<!--
<div   style="overflow: scroll;height:400px;overflow-x: hidden;">
<div class="col-md-6">
   
    <div class="row">
           <div class="col-md-12" id="card">
            <h4>1. Magical and Logical Power of Vedic Mathematics(In English) (For all School Teachers)
       (Workshop fee for national teachers-300/- & for international teachers 100$) </h4> 
   </br>
   <p style="font-size: 16px;margin-left: 40px;">
    Course duration: 11 October to 16 November 2018</br>
    Every week: (Monday to Friday)</br>
    <ol style="margin-left: 40px;font-size: 16px;">
        <li>Video lecture: 40 to 50 minutes /day</li>
        <li>Course/workshop content PDF</li>
        <li>Quiz – 2 Objective Questions/day  (2 marks)</li>
        <li>Weekly Assignment on Saturday (10 objective Questions) (10 marks)</li>
    </ol>
  
   <table style="margin-left: 40px;">
    <tr>
        <td> Quiz/day :</td>
        <td align="right">2x5x4 = 40 marks (40%)</td>
    </tr>   
    <tr>
        <td>Weekly Assignments:</td>
        <td  align="right">10x4 = 40 marks (40%)</td>
    </tr>   

            <td>Final Exam (20 objective questions) on 17 November</td>
            <td align="right">20 marks (20%)</td>
    </tr>   
            
            <td colspan="2" align="right">Total 100 marks</td>
    </tr>   
   </table>

     
                                                                                         
     </p>
    </div> 
    </div>    <!----first row close-----

    <div class="row">
                    <div class="col-md-12" id="card">
            <h4>2. Magical and Logical Power of Vedic Mathematics (Application based) (In English)     </br>   (For all diploma, BE, BBA, undergraduate and Competitive Exam Students)   </br> (Workshop fee for national students -300/- & for international students100$)   </h4> 
   </br>
 <p style="font-size: 16px;margin-left: 40px;">
    Course duration: 11 October to 16 November 2018</br>
    Every week: (Monday to Friday)</br>
    <ol style="margin-left: 40px;font-size: 16px;">
        <li>Video lecture: 40 to 50 minutes /day</li>
        <li>Course/workshop content PDF</li>
        <li>Quiz – 2 Objective Questions/day  (2 marks)</li>
        <li>Weekly Assignment on Saturday (10 objective Questions) (10 marks)</li>
    </ol>
  
   <table style="margin-left: 40px;">
    <tr>
        <td> Quiz/day :</td>
        <td align="right">2x5x4 = 40 marks (40%)</td>
    </tr>   
    <tr>
        <td>Weekly Assignments:</td>
        <td  align="right">10x4 = 40 marks (40%)</td>
    </tr>   

            <td>Final Exam (20 objective questions) on 17 November</td>
            <td align="right">20 marks (20%)</td>
    </tr>   
            
            <td colspan="2" align="right">Total 100 marks</td>
    </tr>   
   </table>

     
                                                                                         
     </p>
    </div>
    </div><!----second row close-----

    <div class="row">
        <div class="col-md-12" id="card">
            <h4>3. Logical Power of Vedic Mathematics (In English)      </br>  (For class 8th to 12th Standard Students)  </br> (Workshop fee for national students -250/- & for international students 80 $)    </h4> 
   </br>
  <p style="font-size: 16px;margin-left: 40px;">
    Course duration: 11 October to 16 November 2018</br>
    Every week: (Monday to Friday)</br>
    <ol style="margin-left: 40px;font-size: 16px;">
        <li>Video lecture: 35 to 45 minutes /day</li>
        <li>Course/workshop content PDF</li>
        <li>Quiz – 2 Objective Questions/day  (2 marks)</li>
        <li>Weekly Assignment on Saturday (10 objective Questions) (10 marks)</li>
    </ol>
  
   <table style="margin-left: 40px;">
    <tr>
        <td> Quiz/day :</td>
        <td align="right">2x5x4 = 40 marks (40%)</td>
    </tr>   
    <tr>
        <td>Weekly Assignments:</td>
        <td  align="right">10x4 = 40 marks (40%)</td>
    </tr>   

            <td>Final Exam (20 objective questions) on 17 November</td>
            <td align="right">20 marks (20%)</td>
    </tr>   
            
            <td colspan="2" align="right">Total 100 marks</td>
    </tr>   
   </table>

     
                                                                                         
     </p>
    </div>
    </div><!----third row close-----

    <div class="row">
        <div class="col-md-12" id="card">
            <h4> 4. Logical Power of Vedic Mathematics (In Hindi)</br>(For class 8th to 12th Standard Students) </br>(Workshop fee for national students -250/-)   </h4> 
   </br>
  <p style="font-size: 16px;margin-left: 40px;">
    Course duration: 11 October to 16 November 2018</br>
    Every week: (Monday to Friday)</br>
    <ol style="margin-left: 40px;font-size: 16px;">
        <li>Video lecture: 35 to 45 minutes /day</li>
        <li>Course/workshop content PDF</li>
        <li>Quiz – 2 Objective Questions/day  (2 marks)</li>
        <li>Weekly Assignment on Saturday (10 objective Questions) (10 marks)</li>
    </ol>
  
   <table style="margin-left: 40px;">
    <tr>
        <td> Quiz/day :</td>
        <td align="right">2x5x4 = 40 marks (40%)</td>
    </tr>   
    <tr>
        <td>Weekly Assignments:</td>
        <td  align="right">10x4 = 40 marks (40%)</td>
    </tr>   

            <td>Final Exam (20 objective questions) on 17 November</td>
            <td align="right">20 marks (20%)</td>
    </tr>   
            
            <td colspan="2" align="right">Total 100 marks</td>
    </tr>   
   </table>

     
                                                                                         
     </p>
    </div>
    </div><!----fourth row close-----

    <div class="row">
        <div class="col-md-12" id="card">
            <h4>   5. Application of Vedic Mathematics for Kids (In English) </br>(for Class 4th to 7th Standard Students)</br>(Workshop fee for national students -200/- & for international students 60$)   </h4> 
   </br>
  <p style="font-size: 16px;margin-left: 40px;">
    Course duration: 11 October to 16 November 2018</br>
    Every week: (Monday to Friday)</br>
    <ol style="margin-left: 40px;font-size: 16px;">
        <li>Video lecture: 25 to 35 minutes /day</li>
        <li>Course/workshop content PDF</li>
        <li>Quiz – 2 Objective Questions/day  (2 marks)</li>
        <li>Weekly Assignment on Saturday (10 objective Questions) (10 marks)</li>
    </ol>
  
   <table style="margin-left: 40px;">
    <tr>
        <td> Quiz/day :</td>
        <td align="right">2x5x4 = 40 marks (40%)</td>
    </tr>   
    <tr>
        <td>Weekly Assignments:</td>
        <td  align="right">10x4 = 40 marks (40%)</td>
    </tr>   

            <td>Final Exam (20 objective questions) on 17 November</td>
            <td align="right">20 marks (20%)</td>
    </tr>   
            
            <td colspan="2" align="right">Total 100 marks</td>
    </tr>   
   </table>

     
                                                                                         
     </p>
    </div>
    </div><!----fifth row close-----

    <div class="row">
        <div class="col-md-12" id="card">
            <h4> 6.  Application of Vedic Mathematics for Kids (In Hindi)  </br>(for Class 4th to 7th Standard Students)</br>   (Workshop fee for national students -200/- )  </h4> 
   </br>
  <p style="font-size: 16px;margin-left: 40px;">
    Course duration: 11 October to 16 November 2018</br>
    Every week: (Monday to Friday)</br>
    <ol style="margin-left: 40px;font-size: 16px;">
        <li>Video lecture: 25 to 35 minutes /day</li>
        <li>Course/workshop content PDF</li>
        <li>Quiz – 2 Objective Questions/day  (2 marks)</li>
        <li>Weekly Assignment on Saturday (10 objective Questions) (10 marks)</li>
    </ol>
  
   <table style="margin-left: 40px;">
    <tr>
        <td> Quiz/day :</td>
        <td align="right">2x5x4 = 40 marks (40%)</td>
    </tr>   
    <tr>
        <td>Weekly Assignments:</td>
        <td  align="right">10x4 = 40 marks (40%)</td>
    </tr>   

            <td>Final Exam (20 objective questions) on 17 November</td>
            <td align="right">20 marks (20%)</td>
    </tr>   
            
            <td colspan="2" align="right">Total 100 marks</td>
    </tr>   
   </table>

     
                                                                                         
     </p>
    </div>
    </div><!----sixth row close-----
</div>
-->
    <div class="col-md-6"  id="card">

    	<table style="font-size: 16px;width:100%;">
        <?php $i=1;
        if(!empty($course_data)){
        foreach($course_data as $row){
            $cid = $row->crsann_crsid;
            $sdate = $row->crsann_regstart;
            $edate = $row->crsann_regend;
            $cdate = date('Y-m-d');
            if(($edate >= $cdate) && ($sdate <= $cdate)){

            $wdata = array('cou_id' => $cid);
           // $sdata = '*';
            $cdata = $this->commodel->get_listspficemore('courses','*',$wdata);
           // print_r($cdata);die;
            ?>
    		<tr>
    		
    			<td>
    		<?php 
            if(!empty($cdata)){
                foreach($cdata as $row2){

            ?>		
		    <b><?php echo $i++;?> .</b> 
		<?php 
			echo "<b><font color=blue>";
			echo $row2->cou_name; 
			
	    		echo "</font></b> <br> "; 
			echo $row2->cou_eligible; 
		?>     
    	   </br>
           ( <?php echo $row2->cou_discipline; ?> )</br>

           <?php
            }}
           ?>
    			</td>

    			<td valign="top">
    				<a href="<?php echo site_url('workshop/courseenroll'); echo "/"; echo $row2->cou_id;?>" >
    					<button type="button" class="btn btn-primary" align="left" id="signup1" title="Click to open enroll form." style="width:100%;">Enroll</button>
    				</a>
    			</td>
    		  
    		</tr>	
        
    		<tr height=20></tr>
           <?php }}}?> 
	<tr><td colspan=2>
	<br>
                If you face any trouble in enrollment process. Kindly feel free to contact Mr. N. K. Singh, Email:nksinghiitk@gmail.com ,9450136012(M).

	</td></tr>	
    	</table>	
    </div>	
<!--   </div>-->
    		

<!--	<div align="justify" id="card">
		<br>
		<br>
		If you face any trouble in enrollment process. Kindly feel free to contact Mr. N. K. Singh, Email:nksinghiitk@gmail.com ,9450136012(M).
		If you got error page in enrollment process, please take screenshot and send to nksinghiitk@gmail.com 	</div>-->
<!--	</div>-->
</div>

<br><br><br><br>    
<?php include 'template/footer.php';?>
</body>
</html>
