<!--@name leavetype.php
    @author Rekha (rekha20july@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Leave Type</title>

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

     <table width="100%">
            <tr><td>
                <div align="left">
                <?php echo anchor('setup/displayleavetype/', "View Leave Type ", array('title' => 'Add Detail' ,'class' =>'top_parent'));?>
                <?php
                 $help_uri = site_url()."/help/helpdoc#Role";
		 echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:73%\">Click for Help</b></a>";
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
 
    <tr>
    <div>
    <form action="<?php echo site_url('setup/leavetype');?>" method="POST" class="form-inline">
            <table style="margin-left:1%;">
            <tr>
                <td><label for="lt_name" class="control-label">Leave Name:</label></td>
                <td>
                <input type="text" name="lt_name"  class="form-control" size="30" /><br>
                </td>

            </tr>

              <tr>
                <td><label for="lt_code" class="control-label">Leave Code:</label></td>
                <td>
                <input type="text" name="lt_code"  class="form-control" size="30" /><br>
                </td>

              <tr>
                <td><label for="lt_short" class="control-label">Leave Short Name:</label></td>
                <td>
                <input type="text" name="lt_short"  class="form-control" size="30" /><br>
                </td>
                 
            <tr>
                <td>
                <label for="lt_value" class="control-label">Leave Value:</label>
                </td>
                <td>
                    <input type="text" name="lt_value" size="30"  class="form-control" /> <br>
                </td>
            </tr>
             
            <tr>
                <td></td><td>
                <button name="leavetype" >Add Leave</button>
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

   
