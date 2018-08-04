
<!doctype>
<html>
<head>
<title>Admin Login</title>	

<link href="<?php echo base_url('assets/css');?>/admin_login.css" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">

<link rel="stylesheet" type="text/css" href="personal-style.css">
</head>

<body>
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
<br/>
<br/>
<br/>
<br/>

<center> <b id="login-name">Admin Login </b> </center>
	</br>
	<div class="row">
		
<div class="col-md-6 col-md-offset-3" id="login">  

<form action="<?php echo site_url('admin/admin_login');?>" method="POST">
	
<div class="form-group">
<label class="user"> UserName  </label>
<div class="input-group">
	<span class="input-group-addon" id="iconn"> <i class="glyphicon glyphicon-user"></i></span>
<input type="text" class="form-control" id="text1" name="ad_email" placeholder="username">
</div>
	
</div>

<div class="form-group">
<label class="user"> Password </label>
<div class="input-group">
	<span class="input-group-addon" id="iconn1"> <i class="glyphicon glyphicon-lock"></i></span>
<input type="password" class="form-control" id="text2" name="ad_pwd" placeholder=" Enter Password">
</div>
</div>

<div class="form-group">

<input type="submit" class="btn btn-success" name="submit" value="login" style="border-radius:0px;">
<input type="reset" class="btn btn-danger" value="reset" style="border-radius:0px;">

    </div>
    <br/><br/><br/>
   <!-- <a href="#" style="color: white; font-size: 15px; float: right; margin-right: 10px;"> Forget Password </a>
    <a href="#" style="color: white; font-size: 15px; float: right; margin-right: 10px;"> Register </a>-->







</form>





  </div>



	</div>










</div>














	</body>
	</html>