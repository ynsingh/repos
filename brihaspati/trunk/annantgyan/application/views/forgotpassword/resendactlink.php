<?php
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
	
<script>
function myFunction1() {
    var myWindow = window.open("https://www.facebook.com/login.php?login_attempt=1&lwv=110","facebook", "width=1100,height=600");
}
</script>    

 <script type="text/javascript">
     var check = function() {
      if (document.getElementById('password').value ==
          document.getElementById('confirm_password').value) {
          document.getElementById('message').style.color = 'green';
          document.getElementById('message').innerHTML = 'Confirm Password is matched.';
      } else {
            document.getElementById('message').style.color = 'red';
          document.getElementById('message').innerHTML = 'Confirm Password not matched.';
      }
  }
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
<?php 

	$this->load->view('template/header');
//include '../template/header.php';
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

<div class="container">
	<div class="col-md-12">
<div class="login-form section text-center">
            <div class="container">
               <!-- <div class="row">
                         <div class="col-md-2"></div>    
                        <div class="col-md-7"  align=left>
                              <span style="font-size: 25px;text-decoration:underline;"> Sign in / Sign Up</span>
                         </div>   
                  </div>-->  
                <div class="col-md-2"></div>    
                <div class="col-md-7">

                <div id="loginbox" style="margin-top:30px;" class="mainbox  loginbox">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <div class="panel-title" style="font-size: 25px;">ReSend Activation/Verify Link</div>
                        </div>
                        <div style="padding-top:30px" class="panel-body">
                            <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
                            <form id="loginform" class="form-horizontal" action="<?php echo site_url('forgotpassword/resendactlink');?>" method="post" onSubmit="return validation();">
                                <div style="margin-bottom: 25px" class="input-group">
                                    <span class="input-group-addon">
                                        <i class="glyphicon glyphicon-user"></i>
                                    </span>
                                    <input id="login-username" type="text" class="form-control" name="username" value="" placeholder="username or email" required="">
                                </div>

                                <div style="margin-top:10px" class="form-group">
                                    <!-- Button -->
                                    <div class="col-sm-12 controls">
                                        <input type="submit" name="signin" class="btn btn-info" value="Submit">
<!--                                        <input type="submit" name="signin" class="btn btn-primary" value="facebook login" onclick="myFunction1()">-->
                                       <!-- <a id="btn-login" href="#" class="btn btn-success btn-lg">Login </a>-->
                                       <!-- <a id="btn-fblogin" href="#" class="btn btn-primary">Login with Facebook</a> -->
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-md-12 control">
                                        <!--<div class='btn-primary' style="font-size: 20px;cursor: pointer;"  onclick="myFunction1()" >
                                            <span style="" >facebook login</span>
                                     </div>-->
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            
            
               <!--- <div id="signupbox" style="display:none; margin-top:50px" class="mainbox loginbox">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <div class="panel-title" style="font-size: 25px;">Sign Up</div>
                            <div style="float:right; font-size: 85%; position: relative; top:-10px">
                                <a id="signinlink" href="#" onclick="$('#signupbox').hide(); $('#loginbox').show()"  style="font-size: 16px;">Sign In</a>
                            </div>
                        </div>
                        <div class="panel-body">
                            <span id='message'></span>
                            <form id="signupform" class="form-horizontal" action="<?php //echo site_url('login/signup');?>" method="post">
                                <div id="signupalert" style="display:none" class="alert alert-danger">
                                    <p>Error:</p>
                                    <span></span>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-3 col-sm-3 col-xs-3 control-label">Name</label>
                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                        <input type="text" class="form-control" name="name" placeholder="First Name">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 col-sm-3 col-xs-3 control-label">Email</label>
                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                        <input type="text" class="form-control" name="email" placeholder="Email Address">
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-md-3 col-sm-3 col-xs-3 control-label">Password</label>
                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                        <input type="password" class="form-control" id="password" name="passwd" placeholder="Password"
                                        onkeyup='check();'>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 col-sm-3 col-xs-3 control-label">Password</label>

                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                        <input type="password" class="form-control" name="conf_passwd" id="confirm_password" placeholder="Password"  onkeyup='check();'>
                                    </div>
                                </div>

                                 <div class="form-group">
                                    <label class="col-md-3 col-sm-3 col-xs-3 control-label">How to know</label>

                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                        <select name="how_known" style="width: 100%;">
                                               <option value="Newpaper">Newpaper</option> 
                                               <option value="Self">Self</option>
                                               <option value="Magzine">Magzine</option>
                                               <option value="Friend">Friend</option>
                                               <option value="Other">Other</option>
                                                
                                        </select>    
                                    </div>
                                </div>


                                <div class="form-group">
                                    <!-- Button --
                                    <div class="signup-btn">
                                        <input type="submit" name="signup" class="btn btn-info btn-lg">
                                        <!--<button id="btn-signup btn-lg" type="button" class="btn btn-info">
                                            <i class="icon-hand-right"></i> &nbsp; Sign Up</button>--
                                    </div>
                                </div>
                                <!--<div style="border-top: 1px solid #999; padding-top:20px" class="form-group">

                                    <div class="f-btn">
                                        <button id="btn-fbsignup" type="button" class="btn btn-primary">
                                            <i class="icon-facebook"></i>   Sign Up with Facebook</button>
                                    </div>
                                </div>--
                            </form>
                        </div>
                    </div>
                </div>
            </div>-->
            
        </div>
    </div>
</div>
</div>

<?php 
	$this->load->view('template/footer');
//	include '../template/footer.php';
?>
</body>
</html>
