<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<!--@name changepasswd.php 
  @author Deepika Chaudhary (chaudharydeepika88@gmail.com)
 -->

<html>
    <head>  
        <title>Change Password</title>  
        <?php $this->load->view('template/header'); ?>
	<?php 
/*			if($this->session->userdata('id_role') == 1){
                                $this->load->view('template/menu');
                        }
                        if($this->session->userdata('id_role') == 2){
                                $this->load->view('template/facultymenu');
                        }
                        if($this->session->userdata('id_role') == 3){
                                $this->load->view('template/stumenu');
                        }
 */	?>
    </head>
<body>
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table> -->
 		    <?php
                    echo "<table width=\"100%\">";
                    echo "<tr>";
                    echo "<td align=\"left\" width=\"33%\">";
                    echo "<table style=\"width: 100%;\">";
                    echo "<tr valign=\"top\">";
                    echo "<td>";
                    echo "</td>";
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Change Password </b>";
                    echo "</td>";
                    echo "<td align=\"right\" width=\"33%\">";
                    $help_uri = site_url()."/help/helpdoc#ChangePassword";
		    echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                    echo "</td>";
                    echo "</tr>";
                    echo "</table>";
                    ?>

<table>
            <tr colspan="2">
                <td>
                    <div>  
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
                <div> 
                    
                           <form action="<?php echo site_url('profile/changepasswd');?>" method="POST" class="form-inline">
                        	<table>
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


			</table>
		</form>
	</div>
</body>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
</html>
