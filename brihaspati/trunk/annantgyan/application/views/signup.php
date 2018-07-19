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
<?php //$this->load->helper('string');
//echo random_string('alnum',12);?>
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

              <!--  <div id="loginbox" style="margin-top:30px;" class="mainbox  loginbox">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <div class="panel-title" style="font-size: 25px;">Sign In</div>
                           <!-- <div class="fpassword">
                                <a href="#">Forgot password?</a>
                            </div>--
                        </div>
                        <div style="padding-top:30px" class="panel-body">
                            <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
                            <form id="loginform" class="form-horizontal" action="#" method="post">
                                <div style="margin-bottom: 25px" class="input-group">
                                    <span class="input-group-addon">
                                        <i class="glyphicon glyphicon-user"></i>
                                    </span>
                                    <input id="login-username" type="text" class="form-control" name="username" value="" placeholder="username or email" required="">
                                </div>

                                <div style="margin-bottom: 25px" class="input-group">
                                    <span class="input-group-addon">
                                        <i class="glyphicon glyphicon-lock"></i>
                                    </span>
                                    <input id="login-password" type="password" class="form-control" name="password" placeholder="password" required="">
                                </div>
                                <div class="input-group">
                                    <div class="checkbox">
                                        <label>
                                            <input id="login-remember" type="checkbox" name="remember" value="1"> Remember me
                                        </label>
                                    </div>
                                </div>
                                <div style="margin-top:10px" class="form-group">
                                    <!-- Button --
                                    <div class="col-sm-12 controls">
                                        <a id="btn-login" href="#" class="btn btn-success btn-lg">Login </a>
                                       <!-- <a id="btn-fblogin" href="#" class="btn btn-primary">Login with Facebook</a> --
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-md-12 control">
                                        <div style="border-top: 1px solid#888; padding-top:15px; font-size:85%">
                                            Don't have an account!
                                            <a href="#" onClick="$('#loginbox').hide(); $('#signupbox').show()" style="font-size: 16px;">
                                                Sign Up Here
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>-->
            
            
                <div id="signupbox" style=" margin-top:50px" class="mainbox loginbox">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <div class="panel-title" style="font-size: 25px;">Sign Up</div>
                            <div style="float:right; font-size: 85%; position: relative; top:-10px">
                                <!--<a id="signinlink" href="#" onclick="$('#signupbox').hide(); $('#loginbox').show()"  style="font-size: -->
                            <a id="signinlink" href="<?php echo site_url('Sign-In');?>" style="font-size:18px;">Sign In</a>
                            </div>
                        </div>
                        <div class="panel-body">
                            <span id='message'></span>
                            <form id="signupform" class="form-horizontal" action="<?php echo site_url('login/signup');?>" method="post">
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
                                    <label class="col-md-3 col-sm-3 col-xs-3 control-label">Confirm Password</label>

                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                        <input type="password" class="form-control" name="conf_passwd" id="confirm_password" placeholder="Password"  onkeyup='check();'>
                                    </div>
                                </div>

                                 <div class="form-group">
                                    <label class="col-md-3 col-sm-3 col-xs-3 control-label">How to know</label>

                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                        <select name="how_known" style="width: 100%;">
                                               <option value="">Select</option>>
                                               <option value="Newpaper">Newpaper</option> 
                                               <option value="Self">Self</option>
                                               <option value="Magzine">Magzine</option>
                                               <option value="Friend">Friend</option>
                                               <option value="Other">Other</option>
                                                
                                        </select>    
                                    </div>
                                </div>


                                <div class="form-group">
                                    <!-- Button -->
                                    <div class="signup-btn">
                                        <input type="submit" name="signup" class="btn btn-info btn-lg" value="Register">
                                        <!--<button id="btn-signup btn-lg" type="button" class="btn btn-info">
                                            <i class="icon-hand-right"></i> &nbsp; Sign Up</button>-->
                                    </div>
                                </div>
                                <!--<div style="border-top: 1px solid #999; padding-top:20px" class="form-group">

                                    <div class="f-btn">
                                        <button id="btn-fbsignup" type="button" class="btn btn-primary">
                                            <i class="icon-facebook"></i>   Sign Up with Facebook</button>
                                    </div>
                                </div>-->
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            
        </div>
    </div>
</div>
</div>

<?php include 'template/footer.php';?>
</body>
</html>
