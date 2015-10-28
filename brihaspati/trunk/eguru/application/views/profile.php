<?php ?>
<html>
    <head>
        <title>EGURU | HOME</title>
        <link href="<?php echo base_url(); ?>/static/css/bootstrap.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link href="<?php echo base_url(); ?>/static/css/login.css" rel="stylesheet" type="text/css">
        <script src="<?php echo base_url(); ?>/static/js/jquery-1.10.2.js"></script>
        <!--<script src="/static/js/bootstrap.responsive.min.js" ></script>-->
        <script src="<?php echo base_url(); ?>/static/js/bootstrap.min.js"></script>
        <script src="<?php echo base_url(); ?>/static/js/jquery-1.10.2.js"></script>
        <script src="<?php echo base_url(); ?>/static/js/jquery.validate.js"></script>
        <script src="<?php echo base_url(); ?>/static/js/login/valid.js"></script>
        <script src="<?php echo base_url(); ?>/static/js/valid_user/user_valid.js"></script>

    </head>
    <body >
<nav class="navbar navbar-inverse" role="navigation">
 <div class="container">
      <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Abhishek</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">home</a></li>
        <li><a href="#">profile</a></li>
         <li><a href="#">Dashboard</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
          <ul class="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li class="divider"></li>
            <li><a href="#">Separated link</a></li>
            <li class="divider"></li>
            <li><a href="#">One more separated link</a></li>
          </ul>
        </li>
      </ul>
  </div>
</nav>
        <div>
            <img src="<?php echo base_url(); ?>/static/img/profile.jpg" style="height: 332px;">
            <form action="">
                <input type="file" name="file">
                <button type="button" class="btn btn-primary"> UPLOAD</button>
                </form>
        </div>
        <div class="panel panel-primary">
  <div class="panel-heading">
      <h3 class="panel-title"><b>About</b></h3>
    <span class="glyphicon glyphicon-pencil" style="float: right; top:-11px">Edit</span>
  </div>
  <div class="panel-body">
    Abhishek Tripathi
  </div>
</div>
    </body>
