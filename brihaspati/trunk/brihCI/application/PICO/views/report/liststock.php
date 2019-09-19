 
<?php defined('BASEPATH') OR exit('No direct script access allowed');
/**
 * @name: Display Item stock List
 * @author: Nagendra Kumar Singh (nksinghiitk@gmail.com)
 */
?>

<html>
<title>Item Stock List | Display</title>
  <head>
   <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
   <?php $this->load->view('template/header'); ?>
    
  </head>
 <body>
      <table width="100%">
            <tr>
                <?php 
                    echo "<td align=\"left\"width=\"33%\">";
                  //  echo anchor('picosetup/openitemtype/', "Add Item Details", array('title' => 'Item Type Form','class' =>'top_parent'));
                    echo "</td>";
                  ?>
                 <?php
                   echo "<td align=\"center\" width=\"34%\">";
                   echo "<b>Item Stock Details</b>";
                   echo "</td>";
                   echo "<td align=\"right\" width=\"33%\">";
                   $help_uri = site_url()."/help/helpdoc#ViewRoleDetail";
               //    echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
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
<thead><th>Sr.No</th>
  <!-- <th>Item ID</th> -->
  <th>Material ID</th><th>Item Name</th><th>Item Stock</th><th>Description</th><th>Action</th></tr></thead>
  <tbody>
   <?php
        $count =0;

        foreach($sresult as $row)
        {  
         ?>
          <tr>
            <td> <?php echo ++$count; ?> </td> 
            <td> <?php echo $this->picomodel->get_listspfic1('material_type','mt_name','mt_id',$row->stock_mtid)->mt_name; ?></td>
            <td> <?php echo $row->stock_name ?></td>
            <td> <?php echo $row->stock_qty ?></td>
            <td> <?php echo $row->stock_desc ?></td>

      <td>
         <?php  
      if ($row->stock_id){
            
            echo "&nbsp; ";
//            echo anchor('report/deleteitemtype/' . $row->item_id , "Delete", array('title' => 'Delete' , 'class' => 'red-link')) . " ";
            echo "<br>";
  //          echo anchor('report/edititemdetails/' . $row->item_id , "Modify", array('title' => 'Modify' , 'class' => 'red-link')) . " ";
            
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



