<!---@name addauthority.php                                                                                                                                                               
    @author Nagendra Kumar Singh (nksinghiitk@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Add Authority</title>

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
        echo anchor('setup/adddegreerules/', "View degree rule ", array('title' => 'Add Rule'));
        echo "</td>";
        echo "</tr>";
        echo "</table>";
        ?>-->

     <table width="90%">
            <tr><td>
                <div align="left" style="margin-left:2%;">
                <?php echo anchor('setup2/authority/', "View Authority list", array('title' => 'View Designation list' ,'class' =>'top_parent'));
		$help_uri = site_url()."/help/helpdoc#Authority";
                echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:70%\">Click for Help</b></a>";
		?>
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                 <?php if(isset($_SESSION['success'])){?>
                        <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                <?php
                };

                if(isset($_SESSION['err_message'])){?>
                <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                <?php
                };
               ?>
              </div>
        </div>
         </td></tr>
    </table>
    <div style="margin-left:2%;">
    <form action="<?php echo site_url('setup2/addauthority');?>" method="POST" class="form-inline">
            <table>
            <tr>
               <td><label for="code" class="control-label">Authority Code:</label></td>
               <td>
               <input type="text" name="code"  class="form-control" size="33" /><br>
               </td>
               <td>
                  <?php //echo form_error('dr_mincredit')?>
               </td>
            </tr>
            <tr>
               <td><label for="name" class="control-label">Authority Name:</label></td>
               <td>
               <input type="text" name="name"  class="form-control" size="33" /><br>
               </td>
               <td>
                  <?php //echo form_error('dr_mincredit')?>
               </td>
            </tr>
            <tr>
                <td><label for="nickname" class="control-label">Authority Nickname:</label></td>
                <td>
                <input type="text" name="nickname" class="form-control" size="33" /><br>
                </td>
                <td>
                    <?php //echo form_error('dr_minsubcredit')?>
                </td>
            </tr>
              <tr>
                <td><label for=" authority_email" class="control-label">Authority Email :</label></td>
                <td>
                <input type="text" name=" authority_email" class="form-control" size="33" /><br>
                </td>
              </tr>
              <tr>
		<td></td>
                <td>
                <button name="addauthority">Add Authority</button>
                <button name="reset" >Clear</button>

                </td>
            </tr>
            </table>
    </form>
    </div>
    <div align="left"> <?php $this->load->view('template/footer');?></div>
    </html>



