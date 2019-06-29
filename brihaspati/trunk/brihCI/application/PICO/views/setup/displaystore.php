<!--@name displaystore.php
  @author Shivam Kumar Singh(shivam.iitk1@gmail.com)
 -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>View Store | Details</title>
  <head>
   <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
   <?php $this->load->view('template/header'); ?>
    
  </head>
 <body>

   <!--  <//?php
        echo "<table border=\"0\" align=\"left\" style=\"color: black;  border-collapse:collapse; border:1px;\">";
        echo "<tr style=\"text-align:left; \">";
        echo "<td style=\"padding: 8px 8px 8px 20px; decoration:none;\">";
        echo anchor('setup/role/', "Add Role", array('title' => 'Add Detail'));
        echo "</td>";
        echo "</tr>";
        echo "</table>";
        ?> -->
        
      <table width="100%">
            <tr>
                <?php 
		                echo "<td align=\"left\"width=\"33%\">";
	                  echo anchor('picosetup/opentypeofstore/', "Add Store", array('title' => 'Add Store Type','class' =>'top_parent'));
                    echo "</td>";
		              ?>
                 <?php
		             echo "<td align=\"center\" width=\"34%\">";
                 echo "<b>Store Details</b>";
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
            </tr>
       </table>
        <div class="scroller_sub_page">
        <table class="TFtable" >
                <tr>
<thead><th>Sr.No</th><th>Type Of Store</th><th>Store Description</th><th>Action</th></tr></thead>
<tbody>
   <?php
        $count =0;
        //foreach ($query->result() as $row)
        foreach ($result as $row)
        {  
         ?>
         
             <tr>
            <td> <?php echo ++$count; ?> </td> 
            <td> <?php echo $row->mt_name ?></td>
            <td> <?php echo $row->mt_desc ?></td>
	    <td>
         <?php  
		if ($row->mt_id > 0){
	    		
	    		echo "&nbsp; ";
            		echo anchor('picosetup/deletestore/' . $row->mt_id , "Delete", array('title' => 'Delete' , 'class' => 'red-link')) . " ";
                echo "<br>";
                //echo anchor('picosetup/editstore/' . $row->mt_id , "Modify", array('title' => 'Modify' , 'class' => 'red-link')) . " ";
		    }
            echo "</td>";
            echo "</tr>";
          
        }
          ?>  
</tbody>
</table>
<br><br>
</div><!------scroller div------>
</body>
<p>&nbsp;</p>
<div align="center">  <?php $this->load->view('template/footer');?></div>
</html>





