<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name forgotpass.php 
  @author Deepika Chaudhary (chaudharydeepika88@gmail.com)
 -->
<html>
    <head>  
        <title>Forgot Password</title>  
        <?php $this->load->view('template/header'); ?>
    </head>
<body>
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
                           <form action="<?php echo site_url('forgotpassword/forgotpass');?>" method="POST" class="form-inline">
                        <table style="margin-left:30px;">
                            <tr>
                                <td><label for="username" class="control-label"><b>User Name (Email)</b></label></td> 
                            </tr>
                            <tr>
                                <td><input type="username" name="username" class="form-control" size="30"><br></td>
                                <td><?php echo form_error('username')?></td>
                            </tr>
				<td>
                                <button name="forgotpass"><b>Send Mail</b></button>
				<button type="button" onclick="history.back();"><b>Back</b></button>
                                </td>
                            </tr>

</tr>
</div>
</form>
</table>
</body>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
</html>

