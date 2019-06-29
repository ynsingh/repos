<!--@name role.php
    @author kishore kr shukla (kishore.shukla@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Add Tender</title>

 <head>
     <?php $this->load->view('template/header'); ?>
   
 </head>    
 <body> 
<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
<!--<//?php
        echo "<table border=\"0\" align=\"left\" style=\"color: black;  border-collapse:collapse; border:1px;\">";
        echo "<tr style=\"text-align:left; \">";
		echo "<td style=\"padding: 8px 8px 8px 20px; decoration:none;\">";
        echo anchor('setup/displayrole/', "View Role Detail ", array('title' => 'Add Detail'));
        echo "</td>";
        echo "</tr>";
        echo "</table>";
        ?>-->

     <table width="100%">
            <tr><td>
                <?php echo anchor('setup/displaytypeoftender/', "View tender Detail ", array('title' => 'Add Detail' ,'class' =>'top_parent'));?>
                <?php
                 echo "<td align=\"right\">";
                 $help_uri = site_url()."/help/helpdoc#Role";
		 echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
		echo "</td>"
                 ?>
                <div  style="width:100%;">
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
 
    <form action="<?php echo site_url('setup/typeoftender');?>" method="POST" class="form-inline">
            <table>
            <tr>
                <td><label for="tt_name" class="control-label">tender Name:</label></td>
                <td>
                <input type="text" name="tt_name"  class="form-control" size="30" /><br>
                </td>
                <td>
                    <?php echo form_error('tt_name')?>
                </td>
                <td>
                   Example: Admin, System Administrator
                </td>

            </tr>
            <tr>
                <td>
                <label for="tt_desc" class="control-label">tender Description:</label>
                </td>
                <td>
                    <input type="text" name="tt_desc" size="30"  class="form-control" /> <br>
                </td>
                <td>
                    <?php echo form_error('tt_desc')?>
                </td>
                <td>
                    Example : System Admin a person who manages the operation of a computer system.
                </td>
            </tr>
            <tr>
                <td></td><td>
                <button name="typeoftender" >Add Role</button>
                <button name="reset" >Clear</button>
                </td>
           </tr>
           </table>
    </form>
 <p><br></p>
    </div>
    </body>
<p>&nbsp;</p>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
    </html>

   
