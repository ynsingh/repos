<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>View|Inspection|Report</title>
  <head>
   <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
   <?php $this->load->view('template/header'); ?>
    
  </head>
 <body>
        
      <table width="100%">
            <tr>
                <?php 
		 echo "<td align=\"left\"width=\"33%\">";
	         //echo anchor('tender/tenderform', "Add Tender", array('title' => 'Add Detail','class' =>'top_parent'));
                 echo "</td>";
		 ?>
                 <?php
		 echo "<td align=\"center\" width=\"34%\">";
                 echo "<b><big>Reports</big></b>";
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
<thead><th>Sr.No</th><th>Report Details</th><th>Indenter Details</th><th>Supply Details</th><th>Item Details</th><th>Inspection Details</th>

<?php
$suname=$this->session->userdata['username'];
if((strcasecmp($suname,"admin"))==0)
{
//echo "<th>Action</th>";
}
?>

</tr></thead>
<tbody>
   <?php
        $count =0;
 
        foreach ($proposal as $row)
        { 
      
        
         
         ?>
         
             <tr>
            <td><b> <?php  
              
             echo ++$count; ?>.</b> <br>
            <br>
            <br>
            <br>
            <br>
            <br>
         
            </td> 
            
            <td> 
                  <b>Report No-:</b> <?php echo $row->inr_no ?><br>
            		<b>Date-:</b><?php echo $row->inr_nodate  ?>  <br>
            		<b>PO No-:</b><?php echo $row->inr_pono ?><br>
            		<b>Date-:</b><?php echo $row->inr_podate  ?><br><br><br>
      
            </td>
            
            <td>
                 <?php {
            			?>
            
                 <b>Indenter No-:</b> <?php echo $row->inr_indentno ?><br>
                 <b>Indenter Name-:</b><?php echo $row->inr_indentname ?><br>
                 <b>Indenter Dept.-:</b><?php 
                                                echo $row->inr_indentdept ?><br> 
                   <br><br><br>
                 
                 	<?php } ?>  
          
            </td>
            
            <td> 
                 <?php {
            			?>
            
                   <b>Supplier-:</b> <?php echo $row->inr_suppliername ?><br>
                   <b>Item ID-:</b><?php echo $row->inr_itemid ?><br>
                   <b>Item Name-:</b><?php echo $row->inr_itemname ?><br> 
                   <b>Quantity Supplied-:</b><?php echo $row->inr_itemqty ?> 
                     <br>
                     <br> 
                     <br>
          
          			<?php } ?>  
            </td>
            
            <td>
                 <?php
            			?>
                
                 <b>Challan No-:</b> <?php echo $row->inr_challanno ?><br>
                 <b>Challan Date-:</b><?php echo $row->inr_challandate ?><br>
                 <b>Quantity Approved-:</b><?php echo $row->inr_qtyapprov ?><br> 
                 <b>Quantity Rejected-:</b><?php echo $row->inr_qtyrej ?><br>
                 <br>
                 <br>
                 <br>
                 
                 	<?php } ?>  
                                   
            </td>    
            <td>  
            		<?php 
            			?>
            		<b>Status-:</b><?php echo $row->inr_irstatus  ?> <br>		
            		<b>Created By-:</b><?php echo $row->inr_creatorname.' Date-'.$row->inr_creatordate ?> <br>
            	   <b>Reason for Rejection-:</b><?php echo $row->inr_reasonforrej ?> <br>
            	   <b>Replacement-:</b><?php echo $row->inr_replacement ?> <br> <br><br>
       	         <?php ?>  
            </td>
           
	         
	      <td></td>
	    	  
          
</tbody>
</table>
</div><!------scroller div------>
</body>
<p>&nbsp;</p>
<div align="center">  <?php $this->load->view('template/footer');?></div>
</html>





