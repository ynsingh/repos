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
  .active {
    color:black;background-color:white;
}
</style>
 <div class="fluid-container">
  
  <!----navbar-----------------
  <nav class="navbar navbar-default" role="navigation" data-spy="affix" data-offset-top="197" id="menu">---->
    <nav class="navbar navbar-default" role="navigation"  id="menu">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
    <!--  <a class="navbar-brand" href="#">Software Solutions</a>-->
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li><a href="<?php echo site_url('Welcome');?>"><span class="glyphicon glyphicon-home"></span> Home</a></li>
        
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"  data-close-others="false">
        <!--  <span class="glyphicon glyphicon glyphicon-user"></span> -->
          About Company
          <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="<?php echo site_url('About-Us');?>"><span class="glyphicon glyphicon glyphicon-chevron-right"></span> Overview & Objective</a></li>
            <li><a href="<?php echo site_url('Mission');?>"><span class="glyphicon glyphicon glyphicon-chevron-right"></span> Vision</a></li>
            <li><a href="<?php echo site_url('Vission');?>"><span class="glyphicon glyphicon glyphicon-chevron-right"></span> Mission</a></li>
            <li><a href="<?php echo site_url('Director/Founder-Message');?>"><span class="glyphicon glyphicon glyphicon-chevron-right"></span> Message from Founder & Director </a></li>
           <!-- <li><a href="<?php //echo site_url('');?>"><span class="glyphicon glyphicon glyphicon-chevron-right"></span> Company Board Members</a></li>
            <li><a href="<?php //echo site_url('');?>"><span class="glyphicon glyphicon glyphicon-chevron-right"></span> Technical Advisors </a></li>
            <li><a href="<?php //echo site_url('');?>"><span class="glyphicon glyphicon glyphicon-chevron-right"></span> Team Members </a></li>
          -->
          </ul>
        </li>


        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"  data-close-others="false">
         <!-- <span class="glyphicon glyphicon glyphicon-user"></span> -->
          Knowledge <sup>+</sup>
          <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="<?php echo site_url('Online-Certified-Crash-Course');?>"><span class="glyphicon glyphicon glyphicon-chevron-right"></span> Online certified crash courses</a></li>
            <li><a href="<?php echo site_url('Online-Certified-Workshop');?>"><span class="glyphicon glyphicon glyphicon-chevron-right"></span> Online certified workshop</a></li>
            <li><a href="<?php echo site_url('Certified-Fun-Learning-Courses/Workshop');?>"><span class="glyphicon glyphicon glyphicon-chevron-right"></span> Certified fun learning courses</a></li>
            <li><a href="<?php echo site_url('General-Subject-Related-Links');?>"><span class="glyphicon glyphicon glyphicon-chevron-right"></span> General Useful Links</a></li>
            
          </ul>
        </li>

        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"  data-close-others="false">
         <!-- <span class="glyphicon glyphicon glyphicon-user"></span> -->
         Skill <sup>+</sup>
          <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="<?php echo site_url('Time-And-Stress-Management-Skill');?>"><span class="glyphicon glyphicon glyphicon-chevron-right"></span> Time/Stress Mgmt.</a></li>
            <li><a href="<?php echo site_url('Effective-Communication-Skills');?>"><span class="glyphicon glyphicon glyphicon-chevron-right"></span> Effective Communication</a></li>
            <li><a href="<?php echo site_url('Interview-Preparation-Skill');?>"><span class="glyphicon glyphicon glyphicon-chevron-right"></span> Interview Preparaton</a></li>
            <li><a href="<?php echo site_url('Public-Speaking-Skills');?>"><span class="glyphicon glyphicon glyphicon-chevron-right"></span> Public Speaking</a></li>
            <li><a href="<?php echo site_url('Report-Papers-Thesis-Writing-Skills');?>"><span class="glyphicon glyphicon glyphicon-chevron-right"></span> Report/Paper/Project/Thesis Writing</a></li>
          </ul>
        </li>

        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"  data-close-others="false">
          <!--<span class="glyphicon glyphicon glyphicon-user"></span> 0-->
         Innovative Ideas or Projects <sup>+</sup>
          <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="<?php echo site_url('Basic-Innovative-Scientific-Projects');?>"><span class="glyphicon glyphicon glyphicon-chevron-right"></span> Fun Learning Scientific Projects</a></li>
            <li><a href="<?php echo site_url('Advance-Innovative-Scientific-Projects');?>"><span class="glyphicon glyphicon glyphicon-chevron-right"></span> Advance Scientific Projects</a></li>
            <li><a href="<?php echo site_url('Innovative-Projects-For-Undergraduate-Students');?>"><span class="glyphicon glyphicon glyphicon-chevron-right"></span> Innovative projects for undergraduate Students</a></li>

              <li><a href="<?php echo site_url('Innovative-Ideas-For-Postgraduate-Students');?>"><span class="glyphicon glyphicon glyphicon-chevron-right"></span> Innovative ideas for Postgraduate Students</a></li>

                <li><a href="<?php echo site_url('Innovative-Ideas-For-Rural-Urban-Area-People');?>"><span class="glyphicon glyphicon glyphicon-chevron-right"></span> Innovative ideas for rural/ urban area people</a></li>
            
          </ul>
        </li>

        <li><a href="#">Career</a></li>
        <!--<li><a href="<?php //echo site_url('');?>">Future Plan</a></li>-->
        

       <!-- <li><a href="#"> <span class="glyphicon glyphicon glyphicon-pencil"></span> Testimonial</a></li>-->
      </ul>
     
      <ul class="nav navbar-nav navbar-right">
        <!--<li><a href="#"><span class="glyphicon glyphicon glyphicon-paperclip"></span> Broucher</a></li>-->
        <li><a href="<?php echo site_url('welcome/referalenroll');?>"><span class="glyphicon  glyphicon-log-in"></span> Recommender Registration</a></li>

        <?php if(isset($this->session->userdata['su_id'])){?>
        	<!-- <li><a href="<?php //echo site_url('login/usr_login');?>"><span class="glyphicon glyphicon-dashboard"></span> Dashboard</a></li>-->
          <li><a href="<?php echo site_url('Course-Home');?>"><span ></span> Course Home</a></li>
          <li><a href="<?php echo site_url('login/usr_login');?>"><span class="glyphicon glyphicon-user"></span> <?php echo $this->session->userdata['su_name'];?></a></li> 

          <li onclick="Logout();"><a href="<?php echo site_url('login/logout');?>"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
         <?php }else{?> 
          
         <li><a href="<?php echo site_url('Sign-In');?>"><span class="glyphicon glyphicon-log-in"> </span> Login </a> </li>
        
        <!-- <li><a href="<?php //echo site_url('New-Registration');?>"><span class="glyphicon glyphicon-log-in"> </span> New Registration </a> </li> -->
         <li><a href="<?php echo site_url('Course-Registration');?>"><span class="glyphicon glyphicon-log-in"> </span> New Registration </a> </li>
         <?php }?>
            
          <li><a href="<?php echo site_url('Contact-Us');?>"><span class="glyphicon  glyphicon-earphone"></span> Contact Us</a></li>
        
        
       <!-- <li class="dropdown">
          <a href="<?php //echo site_url('');?> " class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="false">
          <span class="glyphicon glyphicon glyphicon-earphone"></span> 
          Contact</a>
         -- <ul class="dropdown-menu" role="menu">
            <li><a href="#"><span class="glyphicon glyphicon glyphicon-screenshot"></span> Sitemap</a></li>
            <li><a href="#"><span class="glyphicon glyphicon glyphicon-user"></span> Career</a></li>
            
          </ul>--
        </li>-->
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
</div>
