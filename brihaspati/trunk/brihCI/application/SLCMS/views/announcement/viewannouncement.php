<!---@name viewannouncement.php
@author Deepika Chaudhary (chaudharydeepika88@gmail.com)                                                                                               
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>View Announcement</title>
  <head>
   <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">

   <?php $this->load->view('template/header'); ?>
    <?php $this->load->view('template/menu');?>
  </head>
<body>
<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
<table width= "100%">
<tr>
                <?php  
                 echo "<td align=\"left\" width=\"33%\">";
                 echo anchor('announcement/addannouncement/', "Add Announcement", array('title' => 'Add Announcement Detail','class' =>'top_parent'));
                 echo "</td>";
                 echo "<td align=\"center\" width=\"34%\">";
                 echo "<b>Announcement Details</b>";
                 echo "</td>";
                 echo "<td align=\"right\" width=\"33%\">";
                 $help_uri = site_url()."/help/helpdoc#ViewExamtype";
                 echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
		 echo "</td>";
                ?>
                </tr>
	</table>
	      <table width= "100%">
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
<div class="scroller_sub_page">
        <table class="TFtable" >
            <thead><th>Sr.No</th><th>Announcement Component Name</th><th>Announcement Type</th><th>Announcement Title</th><th>Announcement Description</th><th>Announcement Attachment</th><th>Announcement Publish Date</th><th>Announcement Expiry Date</th><th>Announcement Remark</th><th>Action</th></tr></thead>
<tbody>
<?php
        $count =0;
        if( count($this->annoresult) ):
        foreach ($this->annoresult as $row)
        {
         ?>
             <tr>
            <td> <?php echo ++$count; ?> </td>
            <td> <?php echo $row-> anou_cname ?></td>
            <td> <?php echo $row-> anou_type ?></td>
            <td> <?php echo $row-> anou_title ?></td>
            <td> <?php echo $row-> anou_description?></td>
	    <td><a href ="<?php echo base_url('uploads/announcement/'.$row->anou_attachment);?>"target=_blank><?php echo $row->anou_attachment;?></a></td>
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
           ?>
</tbody>
</table>
</div><!------scroller div------>
</body>
<div align="center">  <?php $this->load->view('template/footer');?></div>
</html>


