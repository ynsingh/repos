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
        <script src="<?php echo base_url(); ?>/static/js/login/admin_login.js"></script>
    </head>
    <body style="background-image: url(/static/img/bg4.jpg)">
        <?php include 'header_login.php'; 
             if(isset($_SESSION['email']))
            header("Location:/eguru/");?>
        
        <form  method="POST" action="/admin/login" class="well span6 control-group"  style="margin-left: 454px ; margin-top:100px; box-shadow: 5px 5px 5px 5px; font-size: 16px;font-family: inherit; " id="admin_login">			<h2>SIGN IN </h2>
            <hr style="border-top:1px solid #333333;">
            <div class="control-group">
                <label style="font-size:19px;">Email</label>
                <input type ="email" class="span3" placeholder="Email" style="height:22px;width:206px" name="email"/>
            </div>
            <div class="control-group">			
                <label style="font-size:19px;">Password</label>
                <input type="Password" class="span3" placeholder="Password" style="height:22px;width:206px" name="password"/>
            </div>
            <div class="control-group">			
                <label style="font-size:19px;">Category</label>
                <select name="type" id="login_for">
                    <option value="none">NONE</option>
                    <option value="dept_hod">Department Incharge</option>
                    <option value="sub_hod">Subject Incharge</option>
                    <option value="admin">Admin</option>
                </select>
            </div>
            <br>
            <input type="submit" name="submit" value="LOGIN" class="btn btn-primary" style="padding: 10px 27px">
            <!--<button class="btn btn-warning btn-primary btn-samll"><i class="icon-trash icon-white"></i>  DELETE</button>-->
        </form>
    </body>
