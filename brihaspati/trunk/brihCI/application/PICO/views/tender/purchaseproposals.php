<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<head>
<title>Tender|View|Proposal</title>
  
  <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
     <?php $this->load->view('template/header');
     
      ?>
     
 


</head>
<body>
 <script type="text/javascript">
        function goBack() {
        window.history.back();
        
        
        
        }
        
        
        


</script>

     <table width="100%">
     
       <tr>
                <?php 
		 echo "<td align=\"left\"width=\"33%\">";
	
	        // echo anchor('tender/tender_applied', "View Tenders Requests", array('title' => 'Proposal Detail','class' =>'top_parent'));
             echo "</td>";
		 ?>
                 <?php
		 echo "<td align=\"center\" width=\"34%\">";
                 echo "<b><big> Proposal Order </big></b>";
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
 
    <form action="<?php echo site_url('tender/orderconfirm');?>" method="POST" class="form-inline">


   <?php //print_r($tcresult);
		foreach($proposal as $res){   
    ?>
          <table class="TFtable">
            <tr> <td colspan="7">
               <h2 class="title">Proposal Details:</h2>
                  
                 </td>           
            </tr>
            
            <tr>
                <input type="hidden" name="pp_id" value="<?php echo $res->pp_id; ?>">
             	  <td><b>Proposal Reference No:</b></td>
                <td><?php echo $res->pp_gemrefno ; ?></td>
              
         
                <td><b>Tender Reference No:</b></td>
                <td><?php echo $res->pp_tenrefno ; ?>
                </td>
             
                <td><b>Tender details</b></td>
                <td>
                <?php 
                      echo anchor('tender/tenderview/' . $res->pp_tcid , "click", array('title' => 'VIEW Details' , 'class' => 'red-link')) . "<br><br>"; ?>                
                </td>
              
               
               
          
        </tr>
          
          
          <tr>    
         
                <td><b>Department Indent No.:</b></td>
                <td><?php echo $res->pp_deptindentno ; ?></td>
              
         
        
                <td><b>Department:</b></td>
                <td><?php echo $res->pp_deptid  ; ?></td>
               
   
             
             
                <td><b>Indent Date:</b></td>
                <td><?php echo $res->pp_indentdate ; ?></td>
               
               
              
         
         </tr>
         <tr>    
         
                <td><b>Name:</b></td>
                <td><?php echo $res->pp_indentername ; ?></td>
              
         
        
                <td><b>PF No.:</b></td>
                <td><?php echo $res->pp_indenterid  ; ?></td>
               
   
             
             
                <td><b>E-mail ID:</b></td>
                <td><?php echo $res->pp_indenteremail ; ?></td>
               
               
              
         
         </tr>
                      <?php  foreach($material as $m) { ?>
         <tr>    
         
                <td><b>Type of Material:</b></td>
                <td><?php echo $m->mt_name ; }?></td>
              
             <?php  foreach($tender_c as $tc) { ?>
        
                <td><b>Complete Description :</b></td>
                <td><?php  
                
                      echo 'Tittle:'.$tc->tc_workitemtitle.' '.'&'.' '.' ';
                      echo 'Work Description:'.$tc->tc_workdesc ;
                     
                
                
                
                ?></td>
               
   
             
             
                <td><b>Quantity:</b></td>
                <td><?php echo $tc->tc_quantity ; } ?></td>
               
               
              
         
         </tr>
            <?php  foreach($tender as $t) { ?>
          <tr>    
         
                <td><b>Price:</b></td>
                <td><?php echo $t->ta_baseprice ; ?></td>
              
         
        
                <td><b>GST Rate:</b></td>
                <td><?php echo $t->ta_gsttax  ; ?></td>
               
   
             
             
                <td><b>Total(Inc. Taxes):</b></td>
                <td><?php echo $res->pp_itemtotcost ; ?></td>
               
               
              <?php } ?>
         
         </tr>
          <tr>    
         
                <td><b>Department/Project No.:</b></td>
                <td><?php echo $res->pp_budgetdept ; ?></td>
              
         
        
                <td><b>Budget Head:</b></td>
                <td><?php echo $res->pp_budgethead  ; ?></td>
               
   
             
             
                <td><b>Budget Amount:</b></td>
                <td><?php echo $res->pp_budgetamt; ?></td>
               
               
              
         
         </tr>
           <?php  foreach($vendor as $v) { ?>
           <tr>    
         
                <td colspan="2"><b>Supplier detail</b></td>
              
              
         
        
                <td><b>Name:</b></td>
                <td><?php echo $v->vendor_name  ; ?></td>
               
   
             
             
                <td><b>Address:</b></td>
                <td><?php echo $v->vendor_address ; ?></td>
               
               <?php  }?>
              
         
         </tr>
          <tr>    
         
                <td><b>Delivery Period:</b></td>
                <td><?php echo $res->pp_deliveryperiod ; ?></td>
              
         
        
                <td><b>FROM:</b></td>
                <td><?php echo $res->pp_deliveryperiodfrom  ; ?></td>
               
   
             
             
                <td><b>TO:</b></td>
                <td><?php echo $res->pp_deliveryperiodto ; ?></td>
               
               
              
         
         </tr>
          <tr>    
         
                <td><b>Warranty:</b></td>
                <td><?php echo $res->pp_warranty ; ?></td>
              
         
        
                <td><b>Guarantee:</b></td>
                <td><?php echo $res->pp_guarantee  ; ?></td>
               
   
             
             
                <td><b>Payment Terms:</b></td>
                <td><?php echo $res->pp_payterm ; ?></td>
               
               
              
         
         </tr>
          
         <?php  } ?>
         </table>
         <br> 
         
         
   
         

  
          
</tbody>
</table>

<table class="TFtable" style="border-color:transparent;">
<?php //<tr id="offpayrowl" >?>
<tr>
<td>
<b>Purchase Order No.</b>
</td>
<td>
<input type="text" name="po_no"  class="form-control"  style="width:200 ;" />
</td>

<td>
<b>Order Date</b>
</td>
<td>
<input type="date" name="date"  class="form-control"  style="width:200 ;" />
</td>

</tr><tr>
<td>
<b>Comment</b>

</td>
	
 		 		
<td colspan="3">

 	  <textarea style="width:837px; height:100px; " style="font-weight:bold; "  name="comment"  id="comment" placeholder="Give Details......" >
 	  
 	  
 	  </textarea>	
</td>

</tr>
<tr>
<td colspan="4">
<button name="push" style="float:right;">Create Purchase Order </button>
</td>
</tr>

</table>
</form>

</div><!------scroller div------>
     <br> <br>
         
         
   
   
              

</div>
</div>
</body>
<br>
<br>
<p>&nbsp;</p>
  <div align="center"> <?php $this->load->view('template/footer');?>
</html>
