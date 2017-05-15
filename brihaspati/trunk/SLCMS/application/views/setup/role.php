<!--@name role.php
    @author kishore kr shukla (kishore.shukla@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
 <head>
     <?php $this->load->view('template/header'); ?>
     <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
     <?php $this->load->view('template/menu');?>
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
     <table>
            <tr colspan="2"><td>
                <div align="left" style="margin-left:40px;">
                <?php echo anchor('setup/displayrole/', "View Role Detail ", array('title' => 'Add Detail' ,'class' =>'top_parent'));?>
                <div  style="width:2000px;">
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
 
    <tr>
    <div style="margin-left:30px;">
    <form action="<?php echo site_url('setup/role');?>" method="POST" class="form-inline">
            <table style="margin-left:30px;">
            <tr>
                <td><label for="role_name" class="control-label">Role Name:</label></td>
                <td>
                <input type="text" name="role_name"  class="form-control" size="30" /><br>
                </td>
                <td>
                    <?php echo form_error('role_name')?>
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
                    <input type="text" name="role_desc" size="30"  class="form-control" /> <br>
                </td>
                <td>
                    <?php echo form_error('role_desc')?>
                </td>
                <td>
                    Example : System Admin a person who manages the operation of a computer system.
                </td>
            </tr>
            <tr>
                <td colspan="2">
                <button name="role" >Add Role</button>
                <button name="reset" >Clear</button>
                </td>
           </tr>
           </table>
    </form>
    </div>
    </tr>
    </table>
    </body>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
    </html>

   
