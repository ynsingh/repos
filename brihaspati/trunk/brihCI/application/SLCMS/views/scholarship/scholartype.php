<!---@name scholartype.php                                                                                                
  @author Krishna Sahu (krishnasahu2406@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>View  Scholar</title>
  <head>
   <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
   <?php $this->load->view('template/header'); ?>
   <?php //$this->load->view('template/menu');?>
  </head>
 <body>
            <table width= "100%">
             <tr><td>
            <div>
              <?php  
                   echo "<td align=\"left\" >";
                   echo anchor('scholarship/addscholar', "Add Scholarship", array('title' => 'Add Scholarship Type Details','class' =>'top_parent'));
                   echo "</td>";
		  
                   echo "</td>";
                   echo "<td align=\"center\" width=\"34%\">";
                   echo "<b>Scholarship Details</b>";
                   echo "</td>";
                   echo "<td align=\"right\" width=\"33%\">";
		 $help_uri = site_url()."/help/helpdoc#ViewScholartype";
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
<table  class="TFtable">
<thead><tr><th>Sr.No</th><th>Sholarship Type</th><th>Scholarship Code</th><th>Scholarship Name</th><th>Description</th><th>Provider</th><th>Year</th><th>Start Date</th><th>End Date</th><th>Action</th></tr></thead>
 <?php
        $count =0;
        if(count($result) ):
        foreach ($result as $row)
        {
         ?>
           <tr>
            <td> <?php echo ++$count; ?> </td>
	    <td> <?php echo $row-> sch_type ?></td>
	    <td><?php echo $row-> sch_code ?></td>
            <td> <?php echo $row-> sch_name ?></td>
            <td> <?php echo $row-> sch_des ?></td>
            <td> <?php echo $row-> sch_provider ?></td>
 	    <td> <?php echo $row-> sch_startyear ?></td>
            <td> <?php echo $row-> sch_startdate ?></td>
            <td> <?php echo $row-> sch_enddate ?></td>
            <td>
        <?php
              
	     
                        echo anchor('scholarship/editscholar/' . $row-> sch_id  , "Edit", array('title' => 'Details' , 'class' => 'red-link')) . " ";
        
	    echo "</td>";
            echo "</tr>";
        }
        else :
        echo "<tr>";
            echo "<td colspan= \"12\" align=\"center\"> No Records found...!</td>";
        echo "</tr>";
        endif;
           ?>
</div>
</table>
</body>
<div align="center">  <?php $this->load->view('template/footer');?></div>
</html>


