<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<!--@name changepasswd.php 
  @author Deepika Chaudhary (chaudharydeepika88@gmail.com)
 -->

<html>
    <head>  
        <title>Change Password</title>  
        <?php $this->load->view('template/header'); ?>
        <!--h1>Welcome <?= $this->session->userdata('username') ?>  </h1-->
	<?php 
			if($this->session->userdata('id_role') == 1){
                                $this->load->view('template/menu');
                        }
                        if($this->session->userdata('id_role') == 2){
                                $this->load->view('template/facultymenu');
                        }
                        if($this->session->userdata('id_role') == 3){
                                $this->load->view('template/stumenu');
                        }
	?>
    </head>
<body>
<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
 <!--?php
            echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
            echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
            echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
            echo "Profile";
            echo "<span  style='padding: 8px 8px 8px 20px;'>";
            echo "|";
            echo "<span  style='padding: 8px 8px 8px 20px;'>";
            echo "Change Password";
            echo "</span>";
            echo "</table>";
?-->
 		    <?php
                    echo "<table style=\"padding: 20px 8px 8px 20px;\">";
                    echo "<tr valign=\"top\">";
                    echo "<td>";
                    $help_uri = site_url()."/help/helpdoc#ChangePassword";
		    echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;font-size:17px;margin-left:54%;position:absolute;margin-top:-1%\">Click for Help</b></a>";
                    echo "</td>";
                    echo "</tr>";
                    echo "</table>";
                    ?>

<table width="100%">
            <tr colspan="2">
                <td>
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
                <div> 
                <br>    
                           <form action="<?php echo site_url('profile/changepasswd');?>" method="POST" class="form-inline">
                        <table style="margin-left:30px;">
                            <tr>  
                                <td><label for="oldpassword" class="control-label"><b>Old Password</b></label></td>
                                <td><input type="password" name="oldpassword" class="form-control" size="30"><br></td>
				<td><?php echo form_error('oldpassword')?></td>
                            </tr>

				<tr>
                                <td><label for="newpassword" class="control-label"><b>New Password</b></label></td>
                                <td><input type="password" name="newpassword"  class="form-control" size="30" /><br></td>
                                <td><?php echo form_error('newpassword')?></td>
                            </tr>

			    <tr>
                                <td><label for="confirmpassword" class="control-label"><b>New Password (Confirm)</b></label></td>
                                <td><input type="password" name="confirmpassword"  class="form-control" size="30" /><br></td>
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
