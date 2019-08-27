 
<?php defined('BASEPATH') OR exit('No direct script access allowed');
/**
 * @name: Display Item List
 * @author: Nagendra Kumar Singh (nksinghiitk@gmail.com)
 */
?>

<html>
<title>Item List | Display</title>
  <head>
   <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
   <?php $this->load->view('template/header'); ?>
    
  </head>
 <body>
      <table width="100%">
            <tr>
                <?php 
                    echo "<td align=\"left\"width=\"33%\">";
                    echo anchor('itemaction/openitemtype/', "Add Item Details", array('title' => 'Item Type Form','class' =>'top_parent'));
                    echo "</td>";
                  ?>
                 <?php
                   echo "<td align=\"center\" width=\"34%\">";
                   echo "<b>Item Type Details</b>";
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
<!--	item_id,item_mtid,item_name,item_price,item_qty,item_desc,item_pono,item_challanno,item_challandate,item_transport,item_receivername'-->
<thead><th>Sr.No</th>
  <th>Material Type</th><th>Order Details</th><th>Item Details</th><th>Receiver Details</th><th>Action</th></tr></thead>
  <tbody>
   <?php
        $count =0;

        foreach($result as $row)
        {  
         ?>
          <tr>
            <td> <?php echo ++$count; ?> </td> 
            <td> <?php echo $this->picomodel->get_listspfic1('material_type','mt_name','mt_id',$row->item_mtid)->mt_name; ?></td>
	    <td> <?php 
		echo "PO no.:".$row->item_pono."</br>";
		echo "Ch. No.:".$row->item_challanno."</br>";
		echo "Ch Date:".$row->item_challandate."</br>";
		echo "Transport:".$row->item_transport;
		 ?></td>
	    <td> <?php 
		echo "Name :".$row->item_name."</br>";
		echo "Price :".$row->item_price."</br>";
		echo "Qty :".$row->item_qty."</br>";
		echo "Desc :".$row->item_desc;

		 ?></td>
            <td> <?php echo $row->item_receivername ?></td>

      <td>
         <?php  
      if ($row->item_id){
            
            echo "&nbsp; ";
            echo anchor('itemaction/deleteitemtype/' . $row->item_id , "Delete", array('title' => 'Delete' , 'class' => 'red-link')) . " ";
         //   echo "<br>";
         //   echo anchor('itemaction/edititemdetails/' . $row->item_id , "Modify", array('title' => 'Modify' , 'class' => 'red-link')) . " ";
            
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



