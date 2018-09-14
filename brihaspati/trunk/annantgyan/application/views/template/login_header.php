
<style>
#menu{
    background-color:  #23a923 ;
    color:white;
}

#menu ul{
    background-color:  #23a923 ;
    color: white;
}

#menu ul li a{
    
    color: black;
    font-size: 18px;
}




#menu ul li a:hover{
    background-color:white;
    color: black;
   
}
</style>
<!--<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>-->
<script type="text/javascript">
  
!function($,n,e){var o=$();$.fn.dropdownHover=function(e){return"ontouchstart"in document?this:(o=o.add(this.parent()),this.each(function(){function t(e){o.find(":focus").blur(),h.instantlyCloseOthers===!0&&o.removeClass("open"),n.clearTimeout(c),i.addClass("open"),r.trigger(a)}var r=$(this),i=r.parent(),d={delay:100,instantlyCloseOthers:!0},s={delay:$(this).data("delay"),instantlyCloseOthers:$(this).data("close-others")},a="show.bs.dropdown",u="hide.bs.dropdown",h=$.extend(!0,{},d,e,s),c;i.hover(function(n){return i.hasClass("open")||r.is(n.target)?void t(n):!0},function(){c=n.setTimeout(function(){i.removeClass("open"),r.trigger(u)},h.delay)}),r.hover(function(n){return i.hasClass("open")||i.is(n.target)?void t(n):!0}),i.find(".dropdown-submenu").each(function(){var e=$(this),o;e.hover(function(){n.clearTimeout(o),e.children(".dropdown-menu").show(),e.siblings().children(".dropdown-menu").hide()},function(){var t=e.children(".dropdown-menu");o=n.setTimeout(function(){t.hide()},h.delay)})})}))},$(document).ready(function(){$('[data-hover="dropdown"]').dropdownHover()})}(jQuery,this);
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
    js.src = "//connect.facebook.net/en_US/sdk.js";
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



function Logout() {
  FB.logout(function () { 
    
           
 window.location.assign("<?php echo base_url();?>login/logout"); 
  });
}
 
      
</script>

<style type="text/css">
  .active{background-color: white;color: :black;}
</style>
 <div class="fluid-container">
  <?php  $request_uri= $_SERVER['REQUEST_URI'];?>
  <!----navbar----------------->
  <nav class="navbar navbar-default" role="navigation" data-spy="" data-offset-top="" id="menu">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
     <!--<a class="navbar-brand" href="#" style="color:black;"><span class="glyphicon glyphicon-user"></span> Name : <?php //echo $this->session->userdata['su_name'];?></a>-->
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

      <ul class="nav navbar-nav">
        <li><a href="<?php echo site_url('');?>"><span class="glyphicon glyphicon-home"></span>Home </a></li>

         <?php 
         $suid = $this->session->userdata['su_id'];
        @$hwnow = $this->commodel->get_listspfic1('user_course_type','uct_courseid','uct_userid',$suid)->uct_courseid;
         if($hwnow == ''){?> 
            <li><a href="<?php echo site_url('Course-Registration');?>" class="<?php if((strpos($request_uri,"Course-Home")!==false) || $request_uri=="" || $request_uri=="/"){echo "active";}?>"><span ></span>Courses Enrollment</a></li>
       <?php }else{?>
         <li><a href="<?php echo site_url('login/usr_login');?>" class="<?php if((strpos($request_uri,"login/usr_login")!==false) || $request_uri=="" || $request_uri=="/"){echo "active";}?>"><span ></span>Course Home</a></li>

       <?php }?>

          <li><a href="<?php echo site_url('login/usrcoustructure');?>"  class="<?php if((strpos($request_uri,"login/usrcoustructure")!==false) || $request_uri=="" || $request_uri=="/"){echo "active";}?>"><span ></span>Courses Structure</a></li>
          <li><a href="<?php echo site_url('login/usrcoucalender');?>" class="<?php if((strpos($request_uri,"login/usrcoucalender")!==false) || $request_uri=="" || $request_uri=="/"){echo "active";}?>"><span ></span>Courses Calendar</a></li>


	 <li class="dropdown">
		<a href="#" class="<?php if((strpos($request_uri,"discussion/")!==false) || $request_uri=="" || $request_uri=="/"){echo "active";}?>" data-toggle="dropdown" data-hover="dropdown"  data-close-others="false">Discussion<span class="caret"></span></a>
		<ul class="dropdown-menu">
	<!--		<li><a href="<?php //echo site_url('discussion/index');?>"><span ></span>Discussion List</a></li>--> 
			<li><a href="<?php echo site_url('discussion/viewDiscussion');?>"><span ></span>View Discussion</a></li>
			<li><a href="<?php echo site_url('discussion/newDiscussion');?>"><span ></span>New Discussion</a></li>
		</ul>
	</li>
			<!-- <li><a href="<?php echo site_url('discussion/index');?>" class="<?php if((strpos($request_uri,"discussion/")!==false) || $request_uri=="" || $request_uri=="/"){echo "active";}?>"><span ></span>Discussion List</a></li> -->
	<li class="dropdown">
		<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"  data-close-others="false">Exam<span class="caret"></span></a>

		<ul class="dropdown-menu">
		<li><a href="<?php //echo site_url('');?>"><span ></span>Practice Exams</a></li>
		<li><a href="<?php //echo site_url('');?>"><span ></span>Quiz / Assignment</a></li>
		</ul>
	</li>
        <li><a href="<?php //echo site_url('');?>"><span ></span>Progress</a></li>
        <li><a href="<?php echo site_url('login/usrfeedback');?>" class="<?php if((strpos($request_uri,"login/usrfeedback")!==false) || $request_uri=="" || $request_uri=="/"){echo "active";}?>"><span ></span>Feedback</a></li>

        <li><a href="<?php //echo site_url('');?>"><span ></span>Certificates</a></li>

        <li><a href="<?php echo site_url('login/usrfaq');?>" class="<?php if((strpos($request_uri,"login/usrfaq")!==false) || $request_uri=="" || $request_uri=="/"){echo "active";}?>"><span ></span>FAQ</a></li>
        
      </ul>
     
      <ul class="nav navbar-nav navbar-right">
        <!--<li><a href="#"><span class="glyphicon glyphicon glyphicon-paperclip"></span> Broucher</a></li>-->
        <?php if(isset($this->session->userdata['su_name'])){?>
         <li><a href="<?php echo site_url('login/usr_login');?>" class="<?php if((strpos($request_uri,"login/usr_login")!==false) || $request_uri=="" || $request_uri=="/"){echo "active";}?>">
          <span class="glyphicon glyphicon-user"></span>  <?php echo $this->session->userdata['su_name'];?></a></li> 
        <li><a href="<?php echo site_url('login/user_login_courseenroll');?>" class="<?php if((strpos($request_uri,"login/user_login_courseenroll")!==false) || $request_uri=="" || $request_uri=="/"){echo "active";}?>"><span ></span>Course Enroll</a></li>
       <?php }
           ?>

        <?php 
          $suid = $this->session->userdata['su_id'];
          @$hwnow = $this->commodel->get_listspfic1('sign_up','su_howtoknow','su_id',$suid)->su_howtoknow;
          
         if($hwnow == 'facebook'){?> 
           <li></li>
       <?php }else{?>
          <li><a href="<?php echo site_url('forgotpassword/gennewpass');?>" class="<?php if((strpos($request_uri,"forgotpassword/gennewpass")!==false) || $request_uri=="" || $request_uri=="/"){echo "active";}?>"><span class="glyphicon glyphicon-th"></span>Change Password</a></li>

       <?php }?>
          <?php if(isset($this->session->userdata['su_name'])){ ?>
             <li onclick="Logout();"><a href="<?php echo site_url('login/logout');?>" class="<?php if((strpos($request_uri,"login/logout")!==false) || $request_uri=="" || $request_uri=="/"){echo "active";}?>"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
             
          <?php }elseif(isset($this->session->userdata['su_id'])){//elseif(isset($this->session->userdata['first_name'])){?>
            <li onclick="Logout();"><a href="<?php echo site_url('login/logout');?>" class="<?php if((strpos($request_uri,"login/logout")!==false) || $request_uri=="" || $request_uri=="/"){echo "active";}?>"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                  <!-- <li  onclick="Logout();"><a href="javascript:void(0);" ><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>-->
                 <!--<li>
                  <input type="button" value="Logout" class="btn-success btn btn-lg" onclick="Logout();" id="fbsubmit" name="fblogout" />
                </li>-->
          <?php }?>
          
         </li>
        
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
</div>


