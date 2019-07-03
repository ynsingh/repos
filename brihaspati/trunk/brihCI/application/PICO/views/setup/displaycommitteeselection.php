 
<?php defined('BASEPATH') OR exit('No direct script access allowed');
/**
 * @name: Display Committee Selection
 * @author: Nagendra Kumar Singh (nksinghiitk@gmail.com)
 * @author: Shivam Kumar Singh  (shivam.iitk1@gmail.com) 
 */
?>

<html>
<title>Committee Selection | Details</title>
  <head>
   <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
   <?php $this->load->view('template/header'); ?>
    
  </head>
 <body>
      <table width="100%">
            <tr>
                <?php 
                    echo "<td align=\"left\"width=\"33%\">";
                    echo anchor('picosetup/opencommitteeselection/', "Add Purchase Committee Selection", array('title' => 'Purchase Committee Selection Form','class' =>'top_parent'));
                    echo "</td>";
                  ?>
                 <?php
                   echo "<td align=\"center\" width=\"34%\">";
                   echo "<b>Purchase Committee Selection Details</b>";
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
              <thead>
                  <th>Sr.No</th>
                  <th>Purchase Through</th>
                  <th>Estimated Price</th>
                  <th>Department</th>
                  <th>Convener</th>
                  <th>Representatives</th>
                  <th>Authority</th>
                  <th>Description</th>
                  <th>Action</th>
              </thead>
            </tr>
<tbody>
   <?php
        $count =0;
        
        foreach ($committee as $row)
        {  
         ?>
          <tr>
            <td> <?php echo ++$count; ?> </td> 
            <td> <?php echo $row->pc_purchasethrough ?></td>
            <td> <?php echo $row->pc_purchpricelimit ?></td>
            <td> <?php echo $row->pc_dept ?></td>
            <td> <?php echo $row->pc_convener ?></td>
            <td> 
                  <b>1.</b><?php echo $row->pc_rep1 ?><br>
                <?php 
                if(!empty($row->pc_rep2))
                  { ?>
                  <b>2.</b><?php echo $row->pc_rep2 ?><br>
                  <?php } ?>
                 <?php 
                if(!empty($row->pc_rep3))
                  { ?>
                  <b>3.</b><?php echo $row->pc_rep3 ?><br>
                  <?php } ?> 
                 <?php 
                if(!empty($row->pc_rep4))
                  { ?>
                  <b>4.</b><?php echo $row->pc_rep4 ?><br>
                  <?php } ?> 
                 <?php 
                if(!empty($row->pc_rep5))
                  { ?>
                  <b>5.</b><?php echo $row->pc_rep5 ?>
                  <?php } ?> 
                        
            </td>
            <td> <?php echo $row->pc_appauth ?></td>
            <td> <?php echo $row->pc_desc ?></td>
          

      <td>
         <?php  
    if ($row->pc_id){
          
          echo "&nbsp; ";
                echo anchor('picosetup/deletecommitteeselection/' . $row->pc_id, "Delete", array('title' => 'Delete' , 'class' => 'red-link')) . " ";
                echo "<br>";
               echo anchor('picosetup/editcommitteeselection/' . $row->pc_id, "Modify", array('title' => 'Modify' , 'class' => 'red-link')) . " ";
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



