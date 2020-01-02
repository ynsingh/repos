<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>View|Proposals</title>
  <head>
   <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
   <?php $this->load->view('template/header'); ?>
    
  </head>
 <body>
        
      <table width="100%">
            <tr>
                <?php 
		 echo "<td align=\"left\"width=\"33%\">";
	            echo "</td>";
		 ?>
                 <?php
		 echo "<td align=\"center\" width=\"34%\">";
                 echo "<b><big>Proposals </big></b>";
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
<thead><th>Sr.No</th><th>Proposal Details</th><th>Indenter Details</th><th>Item Details</th><th>Budget Details</th><th>Delivery details</th><th>Terms & Condition Details</th>

<?php
$suname=$this->session->userdata['username'];
if((strcasecmp($suname,"admin"))==0)
{
echo '<th>Links<th>';
} 
?>

</tr></thead>
<tbody>
   <?php
        $count =0;
   //print_r($result);
    $tcid='';
        foreach ($result as $row)
        { 
        
       
        
       
      
        
         
         ?>
         
             <tr>
            <td><b> <?php echo ++$count; ?>.</b> <br>
            <br>
            <br>
            <br>
            <br>
            <br><br> 
         
            </td> 
            
            <td> 
                  <b>Proposal Reference No-:</b> <?php echo $row->pp_gemrefno ?><br>
            		<b>Tender Reference No-:</b><?php echo $row->pp_tenrefno  ?>  <br>
            		<b>Vendor -:</b><?php echo $row->pp_vendorid ?><br>
            		<b></b><?php   ?><br><br><br><br>
      
            </td>
            
            <td>
         
            
                 <b>Indent Name-:</b> <?php echo $row->pp_indentername ?><br>
                 <b>Indent E-mail-:</b><?php echo $row->pp_indenteremail ?><br>
                 <b>PF No-:</b><?php echo $row->pp_indenterid ?><br> 
                 <?php $m=$this->common_model->get_listspfic1('Department','dept_name','dept_id',$row->pp_deptid)->dept_name;     ?>
                 <b>Department-:</b><?php echo $m ?><br>
                 <b>Department No-:</b><?php echo $row->pp_deptindentno ?><br>
                 <b>Indent Date-:</b><?php echo $row->pp_indentdate ?><br><br> 
                 
                
          
            </td>
            
            <td> 
             
                   <?php $n=$this->PICO_model->get_listspfic1('material_type','mt_name','mt_id',$row->pp_materialtypeid)->mt_name;    ?>
                   <b>Type Of Material-:</b> <?php echo $n ?><br>
                   <?php $o=$this->PICO_model->get_listspfic1('tender_create','tc_workitemtitle','tc_id',$row->pp_tcid)->tc_workitemtitle;    ?>
                   <b>Work item-:</b><?php echo $o?><br>
                   <?php $p=$this->PICO_model->get_listspfic1('tender_create','tc_workdesc','tc_id',$row->pp_tcid)->tc_workdesc;    ?>
                   <b>Work description-:</b><?php echo $p?><br>  
                   <?php $q=$this->PICO_model->get_listspfic1('tender_create','tc_quantity','tc_id',$row->pp_tcid)->tc_quantity;    ?>                 
                   <b>Quantity-:</b><?php echo $q ?><br> 
                   <?php $r=$this->PICO_model->get_listspfic1('tender_apply','ta_baseprice','ta_id',$row->pp_taid)->ta_baseprice;    ?>         
                   <b>Price-:</b><?php echo $r ?><br>
                   <?php $s=$this->PICO_model->get_listspfic1('tender_apply','ta_gsttax','ta_id',$row->pp_taid)->ta_gsttax;    ?>         
                   <b>GST-:</b><?php echo $s ?><br>
                   <b>Total-:</b><?php echo $row->pp_itemtotcost ?> 
                    <br>
          
          		
            </td>
            
            <td>
             
                 <b>Budget Project No.-:</b> <?php echo $row->pp_budgetprojno ?><br>
                 <b>Budget info-:</b><?php echo $row->pp_budgethead ?><br>
                 <b>Budget amount-:</b><?php echo $row->pp_budgetamt ?><br> 
                 <br> <br> <br> <br> 
                 
                
                                   
            </td>
            
            <td> 
               
            	  <b>Delivery Period-:</b><?php echo $row->pp_deliveryperiod ?> <br>
                 <b>Date From-:</b><?php echo $row->pp_deliveryperiodfrom;?><br>
                 <b>Date To-:</b><?php echo $row->pp_deliveryperiodto ?><br><br><br><br><br> 
                 
                 
            </td>
            
            
           
            <td>  
            
                 
            
            		<b>Warranty-:</b><?php echo $row->pp_warranty  ?> <br>		
            		<b>Guarantee:</b><?php echo $row->pp_guarantee ?> <br>
            	   <b>Pay terms-:</b><?php echo $row->pp_payterm ?> <br>
            	   <br><br><br>
       	       <br> 
            </td>
           
            <?php  
		       $suname=$this->session->userdata['username'];
	          if((strcasecmp($suname,"admin"))==0)
	    	   {	
     	    	   echo "<td>";

           
            echo "<br>";
            echo anchor('tender/proposal_view/' . $row->pp_id , "View details", array('title' => 'Dismiss' , 'class' => 'red-link'))." ";
            
            echo "<br><br>";
               if($row->pp_orderstatus=='') {
            echo anchor('tender/proposal_order/' . $row->pp_id , "Purchase order", array('title' => 'Dismiss' , 'class' => 'red-link' , 'onclick' => "return confirm('Are you sure you want to --Order Proposal-- ?');"))." ";
            }
            
                                                     }  
	         
	        
         }
          ?>
          
</tbody>
</table>
</div><!------scroller div------>
</body>
<p>&nbsp;</p>
<div align="center">  <?php $this->load->view('template/footer');?></div>
</html>





