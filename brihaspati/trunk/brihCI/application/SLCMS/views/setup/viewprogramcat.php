<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name viewprogramcat.php 
  @author Raju Kamal(kamalraju8@gmail.com)
 -->
<html>
<title>View Program Category</title>
<head>    
    <?php $this->load->view('template/header'); ?>
    <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
    <?php $this->load->view('template/menu');?>
    <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
</head>
 <body>
<center>
<table width="70%">
     <tr><td>
      <div>
         <?php
            echo anchor('setup/programcat/', 'Add Program Category', array('class' => 'top_parent'));
            $help_uri = site_url()."/help/helpdoc#ViewProgramCategory";
             echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:54%\">Click for Help</b></a>";
         ?>

        
       </div>
       <div style="margin-left:2%;">
          <?php echo validation_errors('<div class="isa_warning">','</div>');?>
          <?php echo form_error('<div class="isa_error">','</div>');?>
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
  <table cellpadding="16" class="TFtable">
        <thead >
        <tr align="center">
        <th>Sr.No</th>
        <th> Program Category Name </th>
        <th> Program Category Code </th>
        <th> Program Category Short Name </th>
        <th> Program Category Description </th>
        <th> Action </th>
        </thead>
        <tbody>
             <?php
                $count = 0;
                foreach ($this->result as $row)
                {
              ?>
                <tr align="center">
                    <td><?php echo ++$count; ?> </td>
                    <td><?php echo $row->prgcat_name ?> </td>
                    <td><?php echo $row->prgcat_code ?> </td>
                    <td><?php echo $row->prgcat_short ?></td>
                    <td><?php echo $row->prgcat_desc ?> </td>
                    <td><?php //echo anchor('setup/deleteprgcat/' . $row->prgcat_id , "Delete", array('title' => 'Details' , 'class' => 'red-link' ,'onclick' => "return confirm('Are you sure you want to delete this Program category record... ')")); ?> &nbsp;&nbsp; <?php echo anchor('setup/editprogramcat/' . $row->prgcat_id , "Edit", array('title' => 'Edit Details' , 'class' => 'red-link')); ?>
               </br>
               </tr>
          <?php } ?>
        </tbody>
    </table>
  </body>
 <div align="center"> <?php $this->load->view('template/footer');?></div>
</html>

