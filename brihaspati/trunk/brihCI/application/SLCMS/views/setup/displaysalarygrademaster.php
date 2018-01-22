<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name  displayscheme.php 
  @author Rekha Devi Pal(rekha20uly@gmail.com)
  @author Manorama Pal(palseema30@gmail.com) add AGP Code 
 -->

<html>
<title>displaysalarygrademaster</title>
<head>    
    <?php $this->load->view('template/header'); ?>
    
    <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css"> 	
</head>    
 <body>

<table width='100%'>
	<tr colspan="2">
         <?php
	    echo "<td align=\"left\" width=\"33%\">";
            echo anchor('setup/salarygrademaster/', 'Add Salary Grade', array('class' => 'top_parent'));
            echo "</td>";
            echo "<td align=\"center\" width=\"34%\">";
            echo "<b>Salary Grade Details</b>";
            echo "</td>";
            echo "<td align=\"right\" width=\"33%\">";
	    $help_uri = site_url()."/help/helpdoc#ViewSchemeDetail";
	    echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
            echo "</td>";
         ?>
	<div align="left" style="margin-left:0%;width:95%;">
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
        <th>AGP Code </th>
        <th>Salary Grade Name </th>
        <th>Salary Grade Max </th>
        <th>Salary Grade Min</th>
        <th>Salary Grade Pay Band </th>
        <th> Action </th>
        </thead></tr>
	<tbody>
	     <?php
		$count = 0;
	        foreach ($this->result as $row)
                {
              ?>    
		<tr>
                    <td><?php echo ++$count; ?> </td>
                    <td><?php echo $row->sgm_id;?> </td>
                    <td><?php echo $row->sgm_name;?> </td>
                    <td><?php echo $row->sgm_max ;?> </td>
                    <td><?php echo $row->sgm_min ;?></td>
		    <td><?php echo $row->sgm_gradepay ?> </td>
             	    <td><?php echo anchor('setup/editsalarygrademaster/' . $row->sgm_id , "Edit", array('title' => 'Edit Details' , 'class' => 'red-link')); ?>
	       </td>
               </tr>
 	  <?php } ?> 
	</tbody>
        </table>
        </div><!------scroller div------> 
  </body>
<p>&nbsp;</p>
 <div align="center"> <?php $this->load->view('template/footer');?></div>
</html>
      
