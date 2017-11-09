<!---@name viewannouncement.php
@author Deepika Chaudhary (chaudharydeepika88@gmail.com)                                                                                               
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>View Announcement</title>
  <head>
   <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
   <?php $this->load->view('template/header'); ?>
    <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
    <?php $this->load->view('template/menu');?>
  </head>
 <body><center>
<table width= "70%">
            <tr><td>
                <div align="left">
                <?php  echo anchor('announcement/addannouncement/', "Add Announcement", array('title' => 'Add Announcement Detail','class' =>'top_parent'));
                 //$help_uri = site_url()."/help/helpdoc#ViewExamtype";
                 //echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:54%\">Click for Help</b></a>";
                ?>
                  </div>
                <div  style="width:90%;margin-left:2%">
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
       </table></center>
<table>
<tr>
<div align="left" style="margin-left:2%;">
<table cellpadding="16" class="TFtable" >
<tr align="center">
<thead><th>Sr.No</th><th>Announcement Component Name</th><th>Announcement Type</th><th>Announcement Title</th><th>Announcement Description</th><th>Announcement Publish Date</th><th>Announcement Expiry Date</th><th>Announcement Remark</th><th>Action</th></tr></thead>
<?php
        $count =0;
        if( count($this->annoresult) ):
        foreach ($this->annoresult as $row)
        {
         ?>
             <tr align="center">
            <td> <?php echo ++$count; ?> </td>
            <td> <?php echo $row-> anou_cname ?></td>
            <td> <?php echo $row-> anou_type ?></td>
            <td> <?php echo $row-> anou_title ?></td>
            <td> <?php echo $row-> anou_description?></td>
            <td> <?php echo $row-> anou_publishdate ?></td>
            <td> <?php echo $row-> anou_expdate ?></td>
            <td> <?php echo $row-> anou_remark ?></td>
            <td>
            <?php
                echo anchor('announcement/editannouncement/' . $row-> anou_id  , "Edit", array('title' => 'Details' , 'class' => 'red-link')) . " ";
                 echo "</td>";
                 echo "</tr>";
        }
        else :
        echo "<tr>";
            echo "<td colspan= \"13\" align=\"center\"> No Records found...!</td>";
        echo "</tr>";
        endif;

        echo "</table>";
        echo "</td>";
        echo "</tr>";
        echo "</table>";
           ?>

</div>
</tr>
</table>
</body>
<div align="center">  <?php $this->load->view('template/footer');?></div>
</html>


