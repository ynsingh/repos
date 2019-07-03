 
<?php defined('BASEPATH') OR exit('No direct script access allowed');
/**
 * @name: Display Purchase Committee Formation Rule
 * @author: Shivam Kumar Singh  (shivam.iitk1@gmail.com)
 * @author: Nagendra Kumar Singh (nksinghiitk@gmail.com)
  */
?>

<html>
<title>Purchase Committee | Rules</title>
  <head>
   <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
   <?php $this->load->view('template/header'); ?>
    
  </head>
 <body>
      <table width="100%">
          <tr><td>
               <?php
                    echo anchor('picosetup/openpurchasecommitteeformrule','Add Purchase Committee Rule', array('title' =>'Item List','class'=>'top_parent'));
                ?>
                 <?php
                   echo "<td align=\"center\" width=\"34%\">";
                   echo "<b><u>Purchase Committee Rules</u></b>";
                   echo "</td>";
                   echo "<td align=\"right\" width=\"33%\">";
                   $help_uri = site_url()."/help/helpdoc#ViewRoleDetail";
                   echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                   echo "</td>";
                 ?>
               </td>
    
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
  <br>

      <table class="TFtable" >
        
      <tr>
<thead><th>Sr.No</th><th>Purchase Through</th><th>Estimated Purchase Price</th><th>Representation</th><th>Reference</th><th>Action</th></tr></thead>
<tbody>
   <?php
        $count =0;
        
        foreach ($result as $row)
        {  
         ?>
             <tr>
            <td> <?php echo ++$count; ?> </td>
            <td> <?php echo $row->pcfr_purchasethrough ?></td> 
            <td> <?php echo $row->pcfr_estpurchaseprice ?></td>
            <td> 
              (i).<?php echo $row->pcfr_rep1 ?><br> 
              <?php 
              if(!empty($row->pcfr_rep2 ))
              {
                ?> 
                (ii).<?php  echo $row->pcfr_rep2 ?>
              <?php 
              } 
              ?> <br>
               <?php 
              if(!empty($row->pcfr_rep3 ))
              {
                ?> 
               (iii).<?php echo $row->pcfr_rep3 ?>
             <?php 
              } 
              ?> 
            </td>
            <td> <?php echo $row->pcfr_reference ?></td>
            

      <td>
         <?php  
    if ($row->pcfr_id){
          
          echo "&nbsp; ";
                echo anchor('picosetup/editpurchasecommitteeformationrule/' . $row->pcfr_id , "Modify", array('title' => 'Modify' , 'class' => 'red-link')) . " ";
                echo "<br>";
                echo anchor('picosetup/deletepurchasecommitteerule/' . $row->pcfr_id , "Delete", array('title' => 'Delete' , 'class' => 'red-link')) . " ";
               
        }
            echo "</td>";
            echo "</tr>";
          
        }
          ?>  
</tbody>
</table>
 
<!-- <table class="TFtable">
<caption><strong><u>Competent Authority to approve the Purchase Committee</u></strong></caption>
<tr><thead><th>Sr.No</th><th>Financial Limit</th><th>Approving Authority</th><th>Action</th></tr></thead>
<?php
        $count =0;
        
        foreach ($result as $row)
        {  
         ?>
             <tr>
            <td> <?php echo ++$count; ?> </td> 
            <td> <?php echo $row->item_id ?></td>
            <td> <?php echo $row->item_mtid ?></td>
      <td>
         <?php  
    if ($row->item_id){
          
          echo "&nbsp; ";
                echo anchor('picosetup/deleteitemtype/' . $row->item_id , "Delete", array('title' => 'Delete' , 'class' => 'red-link')) . " ";
               // echo anchor('picosetup/editfinancialpower/' . $row->item_id , "Modify", array('title' => 'Modify' , 'class' => 'red-link')) . " ";
        }
            echo "</td>";
            echo "</tr>";
          
        }
          ?>  

</table> -->
<br><br>
</div><!------scroller div------>
</body>
<p>&nbsp;</p>
<div align="center">  <?php $this->load->view('template/footer');?></div>
</html>



