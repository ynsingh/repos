
<?php defined('BASEPATH') OR exit('No direct script access allowed');
/**
 * @name: Display Financial Power
 * @author: Nagendra Kumar Singh (nksinghiitk@gmail.com)
 * @author: Shivam Kumar Singh  (shivam.iitk1@gmail.com)
 */
?>

<html>
<title>Financial Power | Details</title>
  <head>
   <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
   <?php $this->load->view('template/header'); ?>
    
  </head>
 <body>
      <table width="100%">
            <tr>
                <?php 
                    echo "<td align=\"left\"width=\"33%\">";
                    echo anchor('picosetup/openfinancialpower/', "Add Financial Power", array('title' => 'Add Financial Power','class' =>'top_parent'));
                    echo "</td>";
                  ?>
                 <?php
                   echo "<td align=\"center\" width=\"34%\">";
                   echo "<b>Financial Power Details</b>";
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
<thead><th>Sr.No</th><th>Type of Purchase</th><th>Sub Purchase Type</th><th>Authority</th><th>Financial Limit<br>(Rs)</th><th>Item Description</th><th>Action</th></tr></thead>
<tbody>
   <?php
        $count =0;
        //foreach ($query->result() as $row)
        foreach ($this->result as $row)
        {  
         ?>
             <tr>
            <td> <?php echo ++$count; ?> </td> 
            <td> <?php echo $row->fp_typeofpurch ?></td>
            <td> <?php echo $row->fp_subtypepurch ?></td>
            <td> <?php echo $row->fp_authority ?></td>
            <td> <?php echo $row->fp_limit ?></td>
            <td> <?php echo $row->fp_desc ?></td>

      <td>
         <?php  
    if ($row->fp_id > 0){
          
          echo "&nbsp; ";
                echo anchor('picosetup/deleteauthority/' . $row->fp_id , "Delete", array('title' => 'Delete' , 'class' => 'red-link')) . " ";
                echo anchor('picosetup/editfinancialpower/' . $row->fp_id , "Modify", array('title' => 'Modify' , 'class' => 'red-link')) . " ";
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



