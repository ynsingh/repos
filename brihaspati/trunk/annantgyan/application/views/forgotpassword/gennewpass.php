<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name resetpassword.php 
  @author Nagendra Kumar Singh (nksinghiitk@gmail.com)
 -->
<html  lang="en">
    <head>  
	<meta charset="utf-8">
	<title>Reset Password</title>  
	<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE, NO-STORE, must-revalidate">
    <META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE">
    <META HTTP-EQUIV="EXPIRES" CONTENT=0>

                <link href="<?php echo base_url('assets/css');?>/style.css" rel="stylesheet">
                
               <link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
      <script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
      <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

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
<?php
        $this->load->view('template/login_header');
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
        if(!empty($_SESSION['err_message'])){
        if(isset($_SESSION['err_message'])){?>
             <div class="alert alert-danger" style="font-size: 18px;"><?php echo $_SESSION['err_message'];?></div>
        <?php
        };
    }
    ?>
</div>

<div class="container">
        <div class="col-md-12">
<div class="login-form section text-center">
	    <div class="container">
<div class="col-md-2"></div>
                <div class="col-md-7">

                <div id="loginbox" style="margin-top:30px;" class="mainbox  loginbox">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <div class="panel-title" style="font-size: 25px;">Change Password</div>
			</div>
                        <div style="padding-top:30px" class="panel-body">
                            <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
			    <form id="loginform" class="form-horizontal" action="<?php echo site_url('forgotpassword/gennewpass');?>" method="post" onSubmit="return validation();">

<div class="form-group">
                                    <label class="col-md-3 col-sm-3 col-xs-3 control-label">User Name (Email)</label>
                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                        <input type="text" class="form-control" name="user_name" placeholder="Email Address" value="<?php echo $this->emailid;?>">
                                    </div>
                                </div>
				<div class="form-group">
                                    <label class="col-md-3 col-sm-3 col-xs-3 control-label">Old Password</label>
                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                        <input type="password" class="form-control" id="oldpassword" name="oldpassword" placeholder="Old Password" >
                                    </div>
                                </div>


                                <div class="form-group">
                                    <label class="col-md-3 col-sm-3 col-xs-3 control-label">New Password</label>
                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                        <input type="password" class="form-control" id="password" name="password" placeholder="New Password"
                                        onkeyup='check();'>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 col-sm-3 col-xs-3 control-label">Confirm New Password</label>

                                    <div class="col-md-9 col-sm-9 col-xs-9">
                                        <input type="password" class="form-control" name="cnfpassword" id="confirm_password" placeholder="Confirm New Password"  onkeyup='check();'>
                                    </div>
                                </div>
<div class="form-group">
                                    <!-- Button -->
                                    <div class="signup-btn">
                                        <input type="submit" name="newgenpass" class="btn btn-info btn-lg" value="Change Password">
                                        <!--<button id="btn-signup btn-lg" type="button" class="btn btn-info">
                                            <i class="icon-hand-right"></i> &nbsp; Sign Up</button>-->
                                    </div>
                                </div>
 </form>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
</div>

</body>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
</html>
                                               
