<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name  displayscheme.php 
  @author Rekha Devi Pal(rekha20uly@gmail.com)
 -->

<html>
<title>displaytaxslab</title>
<head>    
    <?php $this->load->view('template/header'); ?>
    
    <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css"> 	
</head>    
 <body>

<table width="100%">
            <tr colspan="2">
         <?php
	    echo "<td align=\"left\" width=\"33%\">";
            echo anchor('setup/taxslab/', 'Add Tax Slab Master', array('class' => 'top_parent'));
            echo "<td align=\"center\" width=\"34%\">";
            echo "<b>Tax Slab Master Details</b>";
            echo "</td>";
            echo "<td align=\"right\" width=\"33%\">";
            echo "</td>";
	    //$help_uri = site_url()."/help/helpdoc#ViewslabDetail";
	     //echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:54%\">Click for Help</b></a>";
         ?>
        <div>
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
    </tr>
  </table>
        <div class="scroller_sub_page">
        <table class="TFtable" >
            <thead>
                <tr>
        <th>Sr.No</th>
        <th>Financial Year </th>
        <th>Tax Slab Name </th>
        <th>Tax Slab Start Value</th>
        <th>Tax Slab End Value </th>
        <th>Tax Slab Type </th>
        <th>Tax Slab Gender</th>
        <th>Tax Slab Percent </th>
        <th>Action</th> 
        </thead>
	<tbody>
	     <?php
		$count = 0;
	        foreach ($this->result as $row)
                {
              ?>    
		<tr>
                    <td><?php echo ++$count; ?> </td>
                    <td><?php echo $row->tsm_fy?> </td>
                    <td><?php echo $row->tsm_name?> </td>
                    <td><?php echo $row->tsm_startvalue ?> </td>
                    <td><?php echo $row->tsm_endvalue ?></td>
                    <td><?php echo $row->tsm_type?> </td>
                    <td><?php echo $row->tsm_gender ?> </td>
		    <td><?php echo $row->tsm_percent ?> </td>
             	    <td><?php echo anchor('setup/edittaxslab/' . $row->tsm_id , "Edit", array('title' => 'Edit Details' , 'class' => 'red-link')); ?>
	       </td>
               </tr>
 	  <?php } ?> 
</tbody>
        </table>
        </div><!------scroller div------> 
  </body>
 <div align="center"> <?php $this->load->view('template/footer');?></div>
</html>
      
