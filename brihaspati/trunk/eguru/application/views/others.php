<!DOCTYPE html>
<html>
<head>
        <title>EGURU | HOME</title>
        <link href="<?php echo base_url(); ?>/static/css/bootstrap.css" rel="stylesheet" type="text/css">
       
        <script src="<?php echo base_url(); ?>/static/js/jquery-1.10.2.js"></script>
        <script src="<?php echo base_url(); ?>/static/js/bootstrap.responsive.min.js" ></script>
    <script src="<?php echo base_url(); ?>/static/js/bootstrap.min.js"></script>
     <script src="<?php echo base_url(); ?>/static/js/jquery-1.10.2.js"></script>
        <script src="<?php echo base_url(); ?>/static/js/jquery.validate.js"></script>
        <script src="<?php echo base_url(); ?>/static/js/valid.js"></script>
        <script src="<?php echo base_url(); ?>/static/js/user_valid.js"></script>
    
    </head>
    <body >
<?php

include 'header_login.php';
?>
        <div id="myCarousel" class="carousel slide span8" style="width: 664px;height:439px ">
  <ol class="carousel-indicators">
    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
    <li data-target="#myCarousel" data-slide-to="1"></li>
    <li data-target="#myCarousel" data-slide-to="2"></li>
  </ol>
  <!-- Carousel items -->
  <div class="carousel-inner">
    <div class="active item">
        <img src="/static/img/Classroom.jpeg">
    </div>
    <div class="item">
        <img src="/static/img/Social.png">
    </div>
    <div class="item">
        <img src="/static/img/socialfact.png">
        <div class="carousel-caption">
            <h1>thumbail</h1>
        </div>
    </div>
  </div>
  <!-- Carousel nav -->
  <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
  <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
</div>
        <div class="span6">
            <form method="post" action="/eguru/other_register_submit/" class="well" style="width: 513px; ">
                <h2> Others Register</h2>
                <hr>
                <div  >
                 <ul style="list-style-type: none">
         <li>
             First Name-
             <input type="text" name="f_name" class="required"/>
             <br>
             </li>
             <li>
                  <br>
              Last Name-
             <input type="text" name="l_name"class="required"/>
             </li>
             <li>
                  <br>
             Email-
             <input type="text" name="email"class="required"/>
             </li>
              <li>
                  <br>
             Password-
             <input type="password" name="pass"class="required"/>
             </li>
              <li>
                  <br>
             Confirm Password-
             <input type="password" name="conf_pass"class="required"/>
             </li>
               <li>
                  <br>
             Designation-
             <input type="text" name="designation"class="required"/>
             </li>
             <li>
                  <br>
             Apply for-
             <input type="select" name=""class="required"/>
             </li> 
         </ul>
        <button class="btn btn-primary"> Sign Up</button>            
      </div>ss
            </form>
             </div> 
</body>
</html>
