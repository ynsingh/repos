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

 <script type="text/javascript">
window.fbAsyncInit = function() {
    // FB JavaScript SDK configuration and setup
    FB.init({
       appId      : '553303651751091', // FB App ID
      cookie     : true,  // enable cookies to allow the server to access the session
      xfbml      : true,  // parse social plugins on this page
      version    : 'v2.10' // use graph api version 2.10
    });
    
    // Check whether the user already logged in
    FB.getLoginStatus(function(response) {
        if (response.status === 'connected') {
            //display user data
          
            getFbUserData();
        }
    });
};

// Load the JavaScript SDK asynchronously
(function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
     js.src = "https://connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

// Facebook login with JavaScript SDK
function fbLogin() {

    FB.login(function (response) {
        if (response.authResponse) {
            // Get and display the user profile data
            getFbUserData();
        } else {
            document.getElementById('status').innerHTML = 'User cancelled login or did not fully authorize.';
        }
    }, {scope: 'email'});
}

// Fetch the user profile data from facebook
function getFbUserData(){
    FB.api('/me', {fields: 'id,first_name,last_name,email,link,gender,locale,picture,cover'},
    function (response) {
       document.getElementById('fbLink').setAttribute("onclick","fbLogout()");
       // document.getElementById('fbLink').innerHTML = 'Logout from Facebook';
        //document.getElementById('status').innerHTML = 'Thanks for logging in, ' + response.first_name + '!';
      
         //document.getElementById('userData').innerHTML = '<img src="'+response.cover.source+'" /><img src="'+response.picture.data.url+'"/>';
         //document.getElementById('userData').innerHTML = '<div>Name : ' +response.first_name+' '+response.last_name+'</div> <div>Email-Id :' +response.email+'</div><div>Gender :' +response.gender+ '</div><div> Locale:'+response.locale+'</div><div> Profile Link :<a target="_blank" href="'+response.link+'">click to view profile</a></div><div>Profile Pic: <img src="'+response.picture.data.url+'"/></div>';

         
       // document.getElementById('userData').innerHTML = '<div style="position: relative;"><img src="'+response.cover.source+'" /><img style="position: absolute; top: 90%; left: 25%;" src="'+response.picture.data.url+'"/></div><p><b>FB ID:</b> '+response.id+'</p><p><b>Name:</b> '+response.first_name+' '+response.last_name+'</p><p><b>Email:</b> '+response.email+'</p><p><b>Gender:</b> '+response.gender+'</p><p><b>Locale:</b> '+response.locale+'</p><p><b>Profile Link:</b> <a target="_blank" href="'+response.link+'">click to view profile</a></p>';
        //var1 = response.first_name+' '+response.last_name ;
       // var2 = response.email;
       // var3 = var1+' '+var2;
          // Save user data
         //  alert(var3);
        //saveUserData(response);
       // saveUserData(var3);
                var fname = response.first_name;
                var lname = response.last_name;
                var email = response.email;
                var uid = response.id;
                var gen = response.gender;
                var profilepic = response.picture.data.url;
               // var loca = response.locale;
               // var ucover = response.cover.source;
              //  var ulink = response.link;
               // var comb  = fname+","+lname+","+email+","+uid+","+gen+","+profilepic+","+loca+","+ucover;
               var comb  = fname+","+lname+","+email+","+uid+","+gen+","+profilepic;
              // alert(comb);
                  $.ajax({
                    
                      type : "POST",
                      url : "<?php echo base_url();?>login/udata", //enter the login controller URL here
                      cache: false,
                      dataType : "html",

                      data : {
                          "datacomb" : comb
                          },

                      success : function(data) {
                          // do something, e.g. hide the login form or whatever
                          window.location.assign('<?php echo base_url();?>welcome');
                       //   alert('success');
                          
                      },

                      error : function(data) {
                          // do something
                          alert('There is some problem to sign in.'); //window.location = '<?php //echo base_url();?>login/usr_login';
                      }
                  });
    });
}

// Save user data to the database
//function saveUserData(var3){
//alert(var3);
//$jsonCont = file_get_contents(userData);alert($jsonCont);
    //$.post("<?php //echo base_url('login/saveData'); ?>",{userData: JSON.stringify(var3)}, function(data){ return true; },"json");
    //alert(var3);
   // document.getElementById('status').innerHTML = 'Yor fb data insert in table.';
   // }

// Logout from facebook
function fbLogout() {
    FB.logout(function() {
        document.getElementById('fbLink').setAttribute("onclick","fbLogout()");
        document.getElementById('fbLink').innerHTML = 'Logout from Facebook';
        document.getElementById('fbLink').innerHTML = '<img src="<?php echo base_url('assets/img/flogin.png'); ?>"  style="height:40px;"/>';
        document.getElementById('userData').innerHTML = '';
        document.getElementById('status').innerHTML = 'You have successfully logout from Facebook.';
    });
}

function Logout() {
  FB.logout(function () { window.location.assign("<?php echo base_url();?>welcome"); });
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
                            <div class="panel-title" style="font-size: 25px;">Log-In</div>
                            <div class="fpassword" style="text-align: right;"> 
                                <a href="<?php echo site_url('forgotpassword/forgotpass');?>">Forgot password?</a>
                            </div>
                        </div>
                        <div style="padding-top:30px" class="panel-body">
                            <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
                            <form id="loginform" class="form-horizontal" action="<?php echo site_url('login/signin');?>" method="post" onSubmit="return validation();">
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
                                    <!-- Button -->
                                    <div class="col-sm-12 controls">
                                        <input type="submit" name="signin" class="btn btn-info" value="Login">
                                        <!--<input type="submit" name="signin" class="btn btn-primary" value="facebook login" onclick="fbLogin();" id="fbLink">--->
<!--                                        <a href="javascript:void(0);" onclick="fbLogin();" id="fbLink"><img src="<?php echo base_url('assets/img/flogin.png'); ?>" style="height:40px;"/></a> -->
                                       <!-- <a id="btn-login" href="#" class="btn btn-success btn-lg">Login </a>-->
                                       <!-- <a id="btn-fblogin" href="#" class="btn btn-primary">Login with Facebook</a> -->
                                       <!-- Display login status -->
<!--<div id="status"></div>

 Facebook login or logout button 
<a href="javascript:void(0);" onclick="fbLogin();" id="fbLink"><img src="<?php //echo base_url('assets/img/flogin.png'); ?>"/></a>-->

<!-- Display user profile data 
<div id="userData"></div>-->

                                    </div>
				</div>
<div class="form-group">
                                    <div class="col-md-12 control">
                                        <div style="border-top: 1px solid#888; padding-top:15px; font-size:85%">
                                            Don't have an activation/verification link!
                                           <!-- <a href="#" onClick="$('#loginbox').hide(); $('#signupbox').show()" style="font-size: 16px;">-->
                                            <a href="<?php echo site_url('forgotpassword/resendactlink');?>" style="font-size: 16px;">
                                                Get Link Here
                                            </a>
                                        </div>

                                <div class="form-group">
                                    <div class="col-md-12 control">
                                        <div style="border-top: 1px solid#888; padding-top:15px; font-size:85%">
                                            Don't have an account!
                                           <!-- <a href="#" onClick="$('#loginbox').hide(); $('#signupbox').show()" style="font-size: 16px;">-->
                                          <!--  <a href="<?php echo site_url('Sign-Up');?>" style="font-size: 16px;"> -->
                                            <a href="<?php echo site_url('Course-Registration');?>" style="font-size: 16px;">
                                                New Registration Here
                                            </a>
                                        </div>
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

<?php include 'template/footer.php';?>
</body>
</html>
