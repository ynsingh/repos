<!--@name role.php
    @author kishore kr shukla (kishore.shukla@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Add Role</title>
<!--<link rel="shortcut icon" href="<?php //echo base_url('assets/images'); ?>/index.jpg">-->
	<link rel="icon" href="<?php echo base_url('uploads/logo'); ?>/nitsindex.png" type="image/png">	
 <head>
     <?php $this->load->view('template/header'); ?>
     <?php //$this->load->view('template/menu');?>

 </head>    
 <body> 
<!--<//?php
        echo "<table border=\"0\" align=\"left\" style=\"color: black;  border-collapse:collapse; border:1px;\">";
        echo "<tr style=\"text-align:left; \">";
		echo "<td style=\"padding: 8px 8px 8px 20px; decoration:none;\">";
        echo anchor('setup/displayrole/', "View Role Detail ", array('title' => 'Add Detail'));
        echo "</td>";
        echo "</tr>";
        echo "</table>";
        ?>-->
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->
     <table width="100%" >
            <tr>
               <?php
                echo "<td align=\"left\" width=\"33%\">";
                echo anchor('setup/displayrole/', "View Role Detail ", array('title' => 'Add Detail' ,'class' =>'top_parent'));
                echo "</td>";
                 
                echo "<td align=\"center\" width=\"34%\">";
                echo "<b>Add Role Details</b>";
                echo "</td>";

               echo "<td align=\"right\" width=\"33%\">";
               $help_uri = site_url()."/help/helpdoc#Role";
	       echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
	       echo "</td>";
                 ?>
	   </tr>
           </table>
           <table width="100%">
           <tr><td>

                <div>
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
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
             </td></tr>
        </table>
 
    <form action="<?php echo site_url('setup/role');?>" method="POST" class="form-inline"> 
            <table>
            <tr>
                <td><label for="role_name" class="control-label">Role Name:</label></td>
                <td>
                <input type="text" name="role_name"  class="form-control" size="30" />
                </td>
                <td>
                   Example: Admin, System Administrator
                </td>

            </tr>
            <tr>
                <td>
                <label for="role_desc" class="control-label">Role Description:</label>
                </td>
                <td>
                    <input type="text" name="role_desc" size="30"  class="form-control" /> 
                </td>
                <td>
                    Example : System Admin a person who manages the operation of a computer system.
                </td>
            </tr>
            <tr>
                <td></td><td>
                <button name="role" >Add Role</button>
                <button name="reset" >Clear</button>
                </td>
           </tr>
     </table>
  </form>
    </body>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
    </html>

   
