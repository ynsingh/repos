<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name  displayscheme.php 
  @author Rekha Devi Pal(rekha20uly@gmail.com)
 -->

<html>
<title>Display Scheme</title>
<head>    
    <?php $this->load->view('template/header'); ?>
    <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
    <?php $this->load->view('template/menu');?>
    <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css"> 	
</head>    
 <body>
<center>
<table width='70%'>
     <tr><td>
      <div align=left">
         <?php
            echo anchor('setup/scheme/', 'Add Scheme', array('class' => 'top_parent'));
	    $help_uri = site_url()."/help/helpdoc#ViewSchemeDetail";
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
        <th>Department Name</th>
        <th>Scheme Name </th>
        <th>Scheme Code </th>
        <th>Scheme Short Name </th>
        <th>Scheme Description </th>
        <th>Action </th>
        </thead>
	<tbody>
	     <?php
		$count = 0;
	        foreach ($this->result as $row)
                {
              ?>    
		<tr align="center">
                    <td><?php echo ++$count; ?> </td>
                    <td><?php echo $this->common_model->get_listspfic1('Department','dept_name', 'dept_id',$row->sd_deptid)->dept_name;?></td>
                    <td><?php echo $row->sd_name ?> </td>
                    <td><?php echo $row->sd_code ?> </td>
                    <td><?php echo $row->sd_short ?></td>
		    <td><?php echo $row->sd_desc ?> </td>
             	    <td><?php echo anchor('setup/editscheme/' . $row->sd_id , "Edit", array('title' => 'Edit Details' , 'class' => 'red-link')); ?>
	       </td>
               </tr>
 	  <?php } ?>  
	</tbody>		            
    </table>
  </body>
 <div align="center"> <?php $this->load->view('template/footer');?></div>
</html>
      
