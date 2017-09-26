<!--@filename facultyhome.php  @author Manorama Pal(palseema30@gmail.com)  -->

 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
    <head> 
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/bootstrap.min.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
   
        <style>
   
            body {
                width:100%;
                height:100%;
                font-family:Arial;
                font-size:14px;
                background-color: #D0D0D0;
            }
            header, footer {
                min-height:50px;
                position:relative;
            }
            [role="contents"] {
                position:relative;
            }
            .mega-nav {
                background:#2a8fcf;
                border-radius:0;
                margin-bottom:0;
            }
            .mega-nav.navbar-default .navbar-nav > li > a {
                color:#fff;
                transition: all 200ms ease-out;
                -webkit-transition: all 200ms ease-out;
                -ms-transition: all 200ms ease-out;
            }
            .mega-nav.navbar-default .navbar-nav > li > a:hover {
                background:#0075b3;
                color:#fff;
                //background-color: #2a8fcf;
            } 
            .mega-nav.navbar-default .navbar-nav > li.dropdown > a:hover,
            .mega-nav.navbar-default .navbar-nav > li.dropdown > a:focus,
            .mega-nav.navbar-default .navbar-nav > li.dropdown > a:active{
                background:#0075b3;
                //background-color: #2a8fcf;
                color:#fff;
            } 
            #MainMenu {
                padding-left:0;
            }
            #MainMenu .menu-list li {
                //background:#0075b3;
                // color:red;
                transition: all 200ms ease-out;
                -webkit-transition: all 200ms ease-out;
                -ms-transition: all 200ms ease-out;
            }
            #MainMenu .menu-list li + li {
                border-left:1px solid rgba(255, 255, 255, 0.5);
            }

            .mega-nav.navbar-default .navbar-nav > li.menu-list .list-category a {
                // background-color: #2a8fcf;
                //color:#333;
                color:#fff;
    
            }
            .darkness {
                background: rgba(0, 0, 0, 0.7);
                display: none;
                position: absolute;
                top: 0;
                left: 0;
                height: 100%;
                width: 100%;
                z-index: 5;
            }  

        </style>
        <script type="text/javascript">
            $(document).ready(function () {
            //Hover Menu in Header
                $('ul.nav li.dropdown').hover(function () {
                $(this).find('.mega-dropdown-menu').stop(true, true).delay(200).fadeIn(200);
                $('.darkness').stop(true, true).fadeIn();
                }, function () {
                $(this).find('.mega-dropdown-menu').stop(true, true).delay(200).fadeOut(200);
                $('.darkness').stop(true, true).delay(200).fadeOut();
                });
            });   
        </script> 
    
    </head>
    <body>
 
    <nav class="navbar navbar-default mega-nav">
        <div>
        <div class="collapse navbar-collapse" id="MainMenu">
            <ul class="nav navbar-nav menu-list">
                <li><a href="<?php echo site_url(); ?>/facultyhome">Dashboard</a></li>
                <li class="dropdown list-category"><a href="#">Subject</a>
                    <ul class="dropdown-menu mega-dropdown-menu" style="background-color:#2a8fcf;">
                    <li><a href="<?php echo site_url(); ?>/SubprgList/subjectlist">Subject List with Program</a></li>
                    <li><a href="#">Choose Subject Paper for Academic year and Semester</a></li>    
                    </ul>
                </li>
                <li class="dropdown list-category"><a href="#">Student</a>
                    <ul class="dropdown-menu mega-dropdown-menu" style="background-color:#2a8fcf; color:red;">
                        <li><a href="<?php echo site_url();?>/Studenthome/studentlist">Student List</a></li>
                        <li> <a href="<?php echo site_url();?>/Studenthome/student_attendence">Attendance</a></li>
                        <li> <a href="<?php echo site_url();?>/Faculty/studentmarks">Marks</a></li>
                        <li><a href="#">Notice</a></li>
                    </ul>   
                </li>
                <li><a href="#">TimeTable</a></li>
                <li class="dropdown list-category" style="color:red;"><a href="#">Profile</a>
                    <ul class="dropdown-menu mega-dropdown-menu" style="background-color:#2a8fcf; color:red;">
		    <li>
			<?php echo anchor('profile/viewprofile', 'View Profile', array('title' => 'View Profile'));?>
				<!--<a href="#"> View Profile</a> -->
		    </li>
		    <li> 
			<?php echo anchor('profile/changepasswd', 'Change Password', array('title' => 'Change Password'));?>
			<!--	<a href="#">Change Password</a> -->
		    </li>
                    <li> <?php echo anchor('home/logout', 'Logout', array('title' => 'Logout'));?></li>
                </ul>   
                    
                </li>
		    <li class="dropdown list-category" style="color:red;"><a href="#">Help</a>
                    <ul class="dropdown-menu mega-dropdown-menu" style="background-color:#2a8fcf; color:red;">
                    <li>
                        <?php echo anchor('help/helpdocfaculty', 'User Mannual', array('title' => 'User Mannual'));?>
                    </ul>
                    </li>
                </li>
                <li><?php echo anchor('home/logout', 'Logout', array('title' => 'Logout'));?></li>
            </ul>
            <!--<form id="mega-search" class="navbar-form navbar-right">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Search...">
                    <span class="input-group-btn">
                        <button class="btn btn-default" type="button">
                            <i class="glyphicon glyphicon-search"></i>
                        </button>
                    </span>
                </div>
            </form>-->
        </div>
        </div>
    </nav>
    </body>
</html>
         
