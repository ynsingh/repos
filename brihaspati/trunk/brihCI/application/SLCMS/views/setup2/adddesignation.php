<!---@name adddesignation.php                                                                                                                                                               
    @author Nagendra Kumar Singh (nksinghiitk@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Add Designation</title>

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

     <table width="100%">
            <tr><td>
                <div align="left" style="margin-left:2%;width:90%;">
                <?php echo anchor('setup2/designation/', "View Designation list", array('title' => 'View Designation list' ,'class' =>'top_parent'));
		$help_uri = site_url()."/help/helpdoc#Designation";
                echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:70%\">Click for Help</b></a>";
 		?>
                <div>
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
    <form action="<?php echo site_url('setup2/adddesignation');?>" method="POST" class="form-inline">
            <table style="margin-left:2%;">
            <tr>
                <td><label for="desig_code" class="control-label">Designation Code:</label></td>
                <td>
                <input type="text" name="desig_code" class="form-control" size="33" /><br>
                </td>
                <td>
                    <?php //echo form_error('dr_minsubcredit')?>
                </td>
            </tr>
            <tr>
               <td><label for="desig_name" class="control-label">Designation Name:</label></td>
               <td>
               <input type="text" name="desig_name"  class="form-control" size="33" /><br>
               </td>
               <td>
                  <?php //echo form_error('dr_mincredit')?>
               </td>
            </tr>
              
               <tr>
                       <td><label for="desig_name" class="control-label">Designation Group :</label></td> 
                        <td>
                        <select name="desig_group" id="" class="my_dropdown" style="width:100%;">
                        <option value="" disabled selected >------Select Group------</option>
                        <option value="A" class="dropdown-item">A</option>
                        <option value="B" class="dropdown-item">B</option>
                        <option value="C" class="dropdown-item">C</option>
                        <option value="D" class="dropdown-item">D</option>
                        </select>
                        </td></tr>
            <tr>

                      
              <tr>
                <td><label for="desig_short" class="control-label">Designation short :</label></td>
                <td>
                <input type="text" name="desig_short" class="form-control" size="33" /><br>
                </td>
                <tr>
                <td><label for="desig_desc" class="control-label">Designation Description :</label></td>
                <td>
                <input type="text" name="desig_desc" class="form-control" size="33" /><br>
                </td>
                <td>
                    <?php //echo form_error('dr_minsubcredit')?>
                </td>
            </tr>
            <tr>
		<td></td>
                <td>
                <button name="adddesignation">Add Designation</button>
                <button name="reset" >Clear</button>

                </td>
            </tr>
            </table>
    </form>
    </div>
    <div align="left"> <?php $this->load->view('template/footer');?></div>
    </html>

                                                                                                                                                      
