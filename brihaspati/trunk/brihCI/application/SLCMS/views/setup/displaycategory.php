<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name displaycategory.php 
  @author Om Prakash(omprakashkgp@gmail.com)
 -->

<html>
<title>View Category</title>
<head>    
    <?php $this->load->view('template/header'); ?>
    <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
    <?php $this->load->view('template/menu');?>
    <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css"> 	
</head>    
 <body>
<table width='100%' style="margin-left:2%;">
     <tr><td>
      <div align=left">
         <?php
            echo anchor('setup/category/', 'Add Category', array('class' => 'top_parent'));
	    $help_uri = site_url()."/help/helpdoc#ViewCategaryDetail";
	     echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:74%\">Click for Help</b></a>";
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
  </table>
  <table cellpadding="16" style="margin-left:2%;" class="TFtable">
        <thead >
        <tr align="center">
        <th>Sr.No</th>
        <th> Category Name </th>
        <th> Category Code </th>
        <th> Category Short Name </th>
        <th> Category Description </th>
        <th> Action </th>
        </thead>
	<tbody>
	     <?php
		$count = 0;
	        foreach ($this->result as $row)
                {
              ?>    
		     <?php if($row->cat_id >1){ ?>
		<tr align="center">
                    <td><?php echo ++$count; ?> </td>
                    <td><?php echo $row->cat_name ?> </td>
                    <td><?php echo $row->cat_code ?> </td>
                    <td><?php echo $row->cat_short ?></td>
		    <td><?php echo $row->cat_desc ?> </td>
             	    <td><?php //echo anchor('setup/deletecategory/' . $row->cat_id , "Delete", array('title' => 'Details' , 'class' => 'red-link' ,'onclick' => "return confirm('Are you sure you want to delete this category record... ')")); ?> &nbsp;&nbsp; <?php echo anchor('setup/editcategory/' . $row->cat_id , "Edit", array('title' => 'Edit Details' , 'class' => 'red-link')); ?>
	       </td>
               </tr>
	
 	  <?php }} ?>  
	</tbody>		            
    </table>
  </body>
 <div align="center"> <?php $this->load->view('template/footer');?></div>
</html>
      
