<!---@name exam type.php                                                                                                
  @author Nagendra Kumar Singh (nksinghiitk@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>View  Exam</title>
  <head>
   <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
   <?php $this->load->view('template/header'); ?>
   <?php //$this->load->view('template/menu');?>
  </head>
 <body>
    <!--<//?php
        echo "<table border=\"0\" align=\"left\" style=\"color: black;  border-collapse:collapse; border:1px;\">";
        echo "<tr style=\"text-align:left; \">";
        echo "<td style=\"padding: 8px 8px 8px 20px; decoration:none;\">";
        echo anchor('setup/degreerules/', "Add degree rules", array('title' => 'Add degree rules'));
        echo "</td>";
        echo "</tr>";
        echo "</table>";
        ?>--!>

<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->
            <table width= "100%">
            <tr><td>
            <div>
              <?php  
                   echo "<td align=\"left\" width=\"33%\">";
                   echo anchor('setup2/addexamtype/', "Add  Exam Type", array('title' => 'Add   Exam Type  Detail','class' =>'top_parent'));
                   echo "</td>";
                   echo "<td align=\"center\" width=\"34%\">";
                   echo "<b>Exam Type Details</b>";
                   echo "</td>";
                   echo "<td align=\"right\" width=\"33%\">";
		 $help_uri = site_url()."/help/helpdoc#ViewExamtype";
                echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                echo "</td>";
                ?>
                </div>
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
<table>
<table  class="TFtable" >
<thead><th>Sr.No</th><th>Exam Type </th><th>Description</th><th>Action</th></tr></thead>
 <?php
        $count =0;
        if( count($this->result) ):
        foreach ($this->result as $row)
        {
         ?>
           <tr>
            <td> <?php echo ++$count; ?> </td>
            <td> <?php echo $row-> exty_name ?></td>
            <td> <?php echo $row-> exty_desc ?></td>
            <td>
        <?php
              //  if($row->dr_id > 6){
            //           echo anchor('setup2/deleteauthority/' . $row-> id  , "Delete", array('title' => 'Edit Details' , 'class' => 'red-link','onclick' => "return confirm('Are you sure you want to delete this record')")) . " ";

                        echo anchor('setup2/editexamtype/' . $row-> exty_id  , "Edit", array('title' => 'Details' , 'class' => 'red-link')) . " ";
         //    }
            echo "</tr>";

        }
        else :
        echo "<tr>";
            echo "<td colspan= \"12\" align=\"center\"> No Records found...!</td>";
        echo "</tr>";
        endif;
           ?>
</table>
</body>
<div align="center">  <?php $this->load->view('template/footer');?></div>
</html>


