<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name displaycategory.php 
  @author Om Prakash(omprakashkgp@gmail.com)
 -->

<html>
<head>    
    <?php $this->load->view('template/header'); ?>
    <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
    <?php $this->load->view('template/menu');?>
</head>    
 <body>
   <table style="padding: 8px 8px 8px 20px;">
     <tr colspan="2"><td>
      <div align=left">
	<font color=blue size=4pt>
         <?php
            echo anchor('setup/category/', 'Add Category', array('class' => 'top_parent'));
         ?>
	</div>
       </div>
       <div style="margin-left:30px;width:1700px;">
          <?php echo validation_errors('<div style="margin-left:30px;" class="isa_warning">','</div>');?>
          <?php echo form_error('<div style="margin-left:30px;" class="isa_error">','</div>');?>
          <?php if(isset($_SESSION['success'])){?>
              <div style="margin-left:30px;" class="isa_success"><?php echo $_SESSION['success'];?></div>
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
  <table>
     <tr>  
        <div align="left" style="margin-left:30px">
	 <?php
               echo "<table border=0 cellpadding=11 style=\"padding: 8px 8px 8px 25px;\">";	
               echo "<thead><tr align=\"left\"><th>Sr. No</th><th> Category Name</th><th>Category Code</th><th>Category Short Name</th><th>Category Description </th><th>Action</th><th></th></tr></thead>";
		$count = 0;
	        foreach ($this->result as $row)
                {
                ?>    
		<tr>
                    <td><?php echo ++$count; ?> </td>
                    <td><?php echo $row->cat_name ?> </td>
                    <td><?php echo $row->cat_code ?> </td>
                    <td><?php echo $row->cat_short ?></td>
                    <td><?php echo $row->cat_desc ?> </td>
              <?php  echo "<td>" . anchor('setup/deletecategory/' . $row->cat_id , "Delete", array('title' => 'Details' , 'class' => 'red-link' ,'onclick' => "return confirm('Are you sure you want to delete this category record... ')")) . " ";
               echo "<td>" . anchor('setup/editcategory/' . $row->cat_id , "Edit", array('title' => 'Edit Details' , 'class' => 'red-link')) . " ";
               echo "</br>";
               echo "</tr>";
               }
               echo "</table>";
	?>

        </div> 
     </tr>     
   </table>     
  </body>
  <div align="center"> <?php $this->load->view('template/footer');?></div>
</html>
      
