 
<?php defined('BASEPATH') OR exit('No direct script access allowed');
/**
 * @name: Display Cover Details
 * @author: Nagendra Kumar Singh (nksinghiitk@gmail.com)
 * @author: Shivam Kumar Singh  (shivam.iitk1@gmail.com)
 */
?>

<html>
<title>Cover Type | Details</title>
  <head>
   <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
   <?php $this->load->view('template/header'); ?>
    
  </head>
 <body>
      <table width="100%">
            <tr>
                <?php 
                    echo "<td align=\"left\"width=\"33%\">";
                    echo anchor('picosetup/opencovertypeform/', "Add Cover Type", array('title' => 'Cover Type Form','class' =>'top_parent'));
                    echo "</td>";
                  ?>
                 <?php
                   echo "<td align=\"center\" width=\"34%\">";
                   echo "<b>Cover Type Details</b>";
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
<thead><th>Sr.No</th><th>Cover No.</th><th>Fixed Cover</th><th>Cover Type</th>


<th>
<?php  


echo "Optional Cover";

?>
</th><th>
<?php

echo "Cover Description";
?>
</th><th>Action</th></tr></thead>
<tbody>
<?php
        $count =0;
        
        foreach ($result as $row)
        {  
         ?>
             <tr>
            <td> <?php echo ++$count; ?> </td> 
            <td> <?php echo $row->ct_coverno ?></td>
            <td> <?php echo $row->ct_coverfixed ?></td>           
            <td>
                (a)<?php echo $row->ct_cover1 ?><br>

                 <?php if(!empty($row->ct_cover2)){ ?>
                    (b)<?php echo $row->ct_cover2 ?><br>
                <?php } ?>

                 <?php if(!empty($row->ct_cover3)){ ?>
                    (c)<?php echo $row->ct_cover3 ?><br>
                <?php } ?>

                 <?php if(!empty($row->ct_cover4)){ ?>
                    (d)<?php echo $row->ct_cover4 ?><br>
                <?php } ?>
            </td>

           
            <td> <?php echo $row->ct_coveroptional ?></td>
            <td> <?php echo $row->ct_desc ?></td>

      <td>
         <?php  
    if ($row->ct_id >=0){
          
          echo "&nbsp; ";
                echo anchor('picosetup/editcoverdetails/' . $row->ct_id , "Modify", array('title' => 'Modify' , 'class' => 'red-link')) . "<br><br> ";
                 echo "&nbsp; ";             
                echo anchor('picosetup/deletcovertype/' . $row->ct_id , "Delete", array('title' => 'Delete' , 'class' => 'red-link')) . " ";
                
             
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



