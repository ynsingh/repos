<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name resetpassword.php 
  @author Deepika Chaudhary (chaudharydeepika88@gmail.com)
 -->
<html>
    <head>  
        <title>Reset Password</title>  
        <?php $this->load->view('template/header'); ?>
    </head>
<body>
<br>
<nav>   <h1>Welcome to IGNTU </h1></nav>
<br><br><br>
<table>
            <tr colspan="2">
                <td>
                    <div align="left" style="margin-left:30px;width:1700px;">
                    <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                   <?php echo form_error('<div style="margin-left:30px;" class="isa_error">','</div>');?>
                    <?php if(isset($_SESSION['success'])){?>
                    <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                    <?php
                    };
                    ?>
                    <?php if(isset($_SESSION['err_message'])){?>
                    <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                    <?php
                    };
                ?>
                </div>
            </td>
     </tr>
</table>
<tr>
  <div style="margin-left:30px;margin-top:-18px">
                <br>
                           <form action="<?php echo site_url('forgotpassword/changepasswd');?>" method="POST" class="form-inline">
                        <table style="margin-left:30px;">
                            <tr>
                                <td><label for="user_name" class="control-label"><b>User Name (Email)</b></label></td>
                                <td><input type="user_name" name="user_name"  class="form-control" value="<?php echo $this->usid;?>" size="30" readonly /> <br></td>
                                <td><?php echo form_error('user_name')?></td>
                            </tr>

                                <tr>
                                <td><label for="password" class="control-label"><b>New Password</b></label></td>
                                <td><input type="password" name="password"  class="form-control" size="30" /><br></td>
                                <td><?php echo form_error('newpassword')?></td>
                            </tr>

                            <tr>
                                <td><label for="cnfpassword" class="control-label"><b>New Password (Confirm)</b></label></td>
                                <td><input type="password" name="cnfpassword"  class="form-control" size="30" /><br></td>
                                <td><?php echo form_error('confirmpassword')?></td>
                            </tr>
                        <tr>
                                <td>
                                </td>
                                <td>
                                <button name="changepasswd"><b>Update Password</b></button>
                                </td>
                            </tr>


</tr>
</div>
</form>
</table>
</body>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
</html>                                               
