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
  padding-bottom:  10px;
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
  font-size: 1.7em;
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

<div class="container">
	<div class="row">
		<div class="col-md-2"></div>
			<div class="col-md-7 col-sm-7">
				<section id="contact">
					<div class="section-content">
						<h1 class="section-header"> <span class="content-header wow fadeIn " data-wow-delay="0.2s" data-wow-duration="2s">Feeedback Form</span></h1>
				
					</div>
					<div class="contact-section">
						<div class="container">
			<?php 
			if($countq >0){
	if(!empty($fquestions)){
			?>
							<form action="<?php echo site_url('login/usrfeedback');?>" method="POST">
							<div class="col-md-6">
								<div class="col-sm-12 form-group">
		<table>
		<?php 
                $i=1;
                foreach ($fquestions as $result) :
        	?>
      	<tr>
        <td>
        <?php
		echo "<font size='5'>";
                echo "<p>";
                echo $i;
		echo "&nbsp;)&nbsp;";
                echo $result->fq_question;
		echo "</p>";
		if(!empty($result->fq_optiona)){
               // echo "<input type='radio' name='" .$result->fq_id. "' value='".$result->fq_optiona."' required>"."&nbsp;&nbsp;&nbsp;&nbsp;". $result->fq_optiona;
                echo "<input type='radio' name='" .$i. "' value='".$result->fq_optiona."' required>"."&nbsp;&nbsp;&nbsp;&nbsp;". $result->fq_optiona;
		echo "<br>";
		}
		if(!empty($result->fq_optionb)){
               // echo "<input type='radio' name='".$result->fq_id. "' value='".$result->fq_optionb."' required>"."&nbsp;&nbsp;&nbsp;&nbsp;". $result->fq_optionb;
                echo "<input type='radio' name='".$i. "' value='".$result->fq_optionb."' required>"."&nbsp;&nbsp;&nbsp;&nbsp;". $result->fq_optionb;
		echo "<br>";
		}
		if(!empty($result->fq_optionc)){
               // echo "<input type='radio' name='".$result->fq_id."' value='".$result->fq_optionc ."' required>"."&nbsp;&nbsp;&nbsp;&nbsp;". $result->fq_optionc;
                echo "<input type='radio' name='".$i."' value='".$result->fq_optionc ."' required>"."&nbsp;&nbsp;&nbsp;&nbsp;". $result->fq_optionc;
		echo "<br>";
		}
		if(!empty($result->fq_optiond)){
               // echo "<input type='radio' name='".$result->fq_id."' value='".$result->fq_optiond."'required>"."&nbsp;&nbsp;&nbsp;&nbsp;". $result->fq_optiond;
                echo "<input type='radio' name='".$i."' value='".$result->fq_optiond."'required>"."&nbsp;&nbsp;&nbsp;&nbsp;". $result->fq_optiond;
		echo "<br>";
		}
		echo "</font>";
                $i++;
        ?>
        </td>
      </tr>
  <?php endforeach ; ?>

	</table>
<!--	
        			        <label>Q 1. How do you rate your overall experience?</label>
                				<p>
                    <label class="radio-inline">
                    <input type="radio" name="experience" id="radio_experience" value="bad" >
                    Bad
                    </label>

                    <label class="radio-inline">
                    <input type="radio" name="experience" id="radio_experience" value="average" >
                    Average
                    </label>

                    <label class="radio-inline">
                    <input type="radio" name="experience" id="radio_experience" value="good" >
                    Good
                    </label>
                </p>
		</div>	
-->
			  			<div class="form-group">
			  				<label for ="description">Suggestion</label>
			  			 	<textarea  class="form-control" id="description" placeholder="Enter Your Message" name="su_sugge"></textarea>
			  			</div>
			  			<div>
			  				<input type="submit" name="submit" class="btn btn-pill btn-primary btn-lg " value="Submit">
			  			</div>
			  		</div>
			  		
				</form>
		<?php } 
		else 
		{ ?>

			<p>
			<font size=6>Thanks for submitting the feedback. </font>
			</p>
<?php		}
?>
		<?php }else{ ?>
			<p>
			<font size=6>Feedback Question Come Soon. </font>
			</p>
		<?php  } ?>
			</div>
	
		</section>
	</div>
</div>
</div>


<br><br>
<?php $this->load->view('template/footer.php');?>
</body>
</html>
