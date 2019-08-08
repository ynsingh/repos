<!--@name displayrole.php
  @author kishore kr shukla (kishore.shukla@gmail.com)
-->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>View Role</title>
  <head>
   	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
  	<?php $this->load->view('template/header'); ?>
  	<?php //$this->load->view('template/menu');?>
  </head>
  <body>
    <!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?></td></tr></table>-->
      	<table width="100%">
            <tr><td>
                <?php 
                echo "<td align=\"left\" width=\"33%\">";
                echo anchor('setup/role/', "Add Role", array('title' => 'Add Detail','class' =>'top_parent'));
                echo "</td>"; 
 
                echo "<td align=\"center\" width=\"34%\">";
                echo "<b>Role Details</b>";
                echo "</td>";

                echo "<td align=\"right\" width=\"33%\">";
                $help_uri = site_url()."/help/helpdoc#ViewRoleDetail";
  	        echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                echo "</td>";
                ?>
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
	<table class="TFtable">
	<thead><th>Sr.No</th><th>Role Name</th><th>Role Description</th><th>Action</th></tr></thead>
	   <?php
        	$count =0;
  	      //foreach ($query->result() as $row)
        	foreach ($this->result as $row)
        	{  
         	?>
            <tr>
            <td> <?php echo ++$count; ?> </td> 
            <td> <?php echo $row->role_name ?></td>
            <td> <?php echo $row->role_desc ?></td>
	    <td>
            <?php  
		if ($row->role_id > 13){
	    	//	echo anchor('setup/delete_role/' . $row->role_id , "Delete", array('title' => 'Edit Details' , 'class' => 'red-link','onclick' => "return confirm('Are you sure you want to delete this record')")) . " ";
	    		echo "&nbsp; ";
            		echo anchor('setup/editrole/' . $row->role_id , "Edit", array('title' => 'Details' , 'class' => 'red-link')) . " ";
		}
            echo "</td>";
            echo "</tr>";
          
        }
          ?>
</table>
</div>
</body>
<div align="center">  <?php $this->load->view('template/footer');?></div>
</html>





