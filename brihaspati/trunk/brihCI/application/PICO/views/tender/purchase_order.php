<?php defined('BASEPATH') OR exit('No direct script access allowed');

?>
<html>
<title>Purchase Order |Details Form</title>
 <!--
<head>    
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="IE=edge" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="<?php echo base_url('assets/images'); ?>/index.jpg">
<link rel="stylesheet" type="text/css" media="all" href="<?php echo base_url(); ?>assets/css/style.css" />
<link rel="stylesheet" type="text/css" media="all" href="<?php echo base_url(); ?>assets/css/menu.css" />
<link rel="stylesheet" type="text/css" media="all" href="<?php echo base_url();?>assets/css/message.css">

<div id="header">
<img src="<?php echo base_url(); ?>uploads/logo/logo1.png" alt="logo">

</div> 
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/datepicker/jquery-ui.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
     
 
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
<table>
<tr style="height:50%;width:100%"><b><big><big>Purchase Proposal Request Form</big></big></b></tr>
</table>
</head>

 -->
 <head>
   <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
   <?php $this->load->view('template/header'); ?>
    <br> 
     <table>
     <tr style="height:50%;width:100%"><b><big><big>Central Stores & Purchase Division<br>IIT Post Office Kanpur -208016</big></big></b></tr>
     </table>
  
 
  </head>
<body>

   

    <form id="myform" action="<?php echo site_url('tender/proposal_entry');?>" method="POST" class="form-inline" autocomplete="OFF" enctype="multipart/form-data">
		   <table class="TFtable">
   
   <tr>  <td style="float:left;width:50%;">E-mail:Institute Mail</td>
    <td style="width:50%;"> 
  <div style="float:right;width:50%;">Contact:<br>Tel:(0512)2596720
                                              <br>Fax:(0512)2597659</div>
</td>     
</tr>     
  
			             <?php  foreach ($proposal as $p) { ?> 
               
                <tr>
             
                <td><b>PO No. :<?php echo $p->po_no; ?></b></td>
              
             
                <td ><div style="float:right;width:50%;"><?php echo '<b>Date:<b>'.date('d-m-Y'); ?></div></td>
                </tr> 
                <?php } ?> 
                  <tr>
                 <td colspan="2">
                  To<br> <?php  foreach ($result as $tttt) { ?> 
                      <?php echo $tttt->vendor_companyname; ?>   <br>
                                            <?php echo $tttt->vendor_address; ?>                     <br>	                  
                 </td>              
                 <?php } ?> 
                
                </tr>			         
			     
             
                
              
			 
                
             </table> 
             <br>
             <table><tr> <td>
             <b>Subject:</b>Purchase Order Against Your Quotation No: NIL Dated :
             
                    <br> <br>With Reference To Above Quotation Please Supply The Undermentioned Item(s) In Accordance With the Terms And Condition Laid Down Here With On An Attached Sheet.
                     <br>Please Acknowledge Reciept Of The Order And Confirm The Delivery Period. 
                  <td>   </tr>    
        </table>
        
        <br>
      

        <table  id="myTable1"  class="TFtable" align="center">
        <thead align="center">
           <tr>  <th colspan="6">Details of Required Items:</th></tr>         
          <tr>  <th>Complete Description<br>(Good/Services intended<br>to be procured.)</th><th>Quantity<br>Required</th><th>Unit Price</th><th>Total(Inc. Taxes)</th>
          </tr>      
        </thead>
        
        <tbody>    
                <tr>
                <td>   
                     <?php  foreach ($tcresult as $t) { ?> 
              <?php
                      echo '<b> </b>'.$t->tc_workitemtitle.' <br>'.''.'<br> '.' ';
                      echo '<b> </b>'.$t->tc_workdesc ;
                       ?> 
                </td>
                <td><?php echo $t->tc_quantity ?></td>
                <?php $q=$t->tc_quantity; } ?>
                <?php  foreach ($taresult as $tt) { ?> 
               
                <td><?php echo round($tt->ta_totalprice/$q,2); ?> </td>
                <td><?php echo $tt->ta_totalprice ?></td>
                    
                   
                   
                </tr>
                
            </tbody>
           
        </table>
        <br>
        
  <table class="TFtable">
                <tr>
                    <td><label for="fp_typeofpurch" class="control-label">Delivery Period: </label></td>
                    
                    <td>  
                      <?php echo $tt->ta_delivery.' Days  ' ?>
                     <!--     FROM:
                     <?php //echo date('d-m-Y') ?>
                         TO:                        
                      <?php //echo date('d-m-Y',strtotime('+'.$tt->ta_delivery.' days')) ?> -->
                    </td>
                </tr>
                <tr >
                    <td><label for="pp_warranty">Warranty Statement:</label></td>
                    <td>
                        <textarea name="pp_warranty" style="width:100%;height:100px;" readonly placeholder="Give Warranty/Guarantee Descriptions here..."><?php echo $tt->ta_warranty ?></textarea>
                    </td>
                </tr>
                <tr >
                    <td><label for="pp_guarantee">Guarantee Statement:</label></td>
                    <td>
                        <textarea name="pp_guarantee" style="width:100%;height:100px;" readonly placeholder="Give Warranty/Guarantee Descriptions here..."><?php echo $tt->ta_guarantee ?></textarea>
                    </td>
                </tr>
              
                
                
            </table>            
        <br>
     
        <table class="TFtable">
             <th colspan="3"><b>Payment Terms and Conditions are:</b></th>          
            <tr>
                <td>
                <input type="checkbox" name="" readonly value="" id="" checked="" style="width:18px;height:18px;"/ >1. 90% PAYMENT AGAINST DELIVERY AND BALANCE 10% AFTER INSPECTION AND APPROVAL.
                </td>
            </tr>  
             <tr>
                <td>
                <input type="checkbox" readonly name="" value="" id="" checked="" style="width:18px;height:18px;"/>2. Delivery must be completed within  <?php echo $tt->ta_delivery.' Days  ' ?> from the date of receipt of the order.
                         Penality @ 1% per week or part thereof subject to a maximum of 10% of the delivery price will be deducted from the balance payment if supply is not completed within stipulated period.
                         IIT kanpur reserves the right to cancel the purchase order without any prior notice if the item(s) is not supplied within stipulated delivery time.
                         
                </td>
            </tr>  
              <tr>
                <td>
                <input type="checkbox" name="" readonly value="" id="" checked="" style="width:18px;height:18px;"/>3. Please send Proforma Invoice in Triplicate to prepare the payment(s) and acceptance of PO & Terms and Conditions immediately.
                </td>
            </tr> 
             <tr>
                <td>
                <input type="checkbox" name="" readonly value="" id="" checked="" style="width:18px;height:18px;"/>4. Above prices are free delivery to the Institute.
                </td>
            </tr> 
             <tr>
                <td>
                <input type="checkbox" readonly name="" value="" id="" checked="" style="width:18px;height:18px;"/>5. The rates are inclusive of Goods & Services Tax.
                </td>
            </tr> 
                   <?php
                        }
                        ?>
        </table>
        <br>
      <table class="TFtable">
      <tr>    <?php  foreach ($orrder as $odr) { ?> 
       <tr>  <td style="float:left;width:50%;"><b>Approved by:</b>hii<br><br><b>Designation:</b></td>
    <td style="width:50%;"> 
  <div style="float:right;width:50%;">Yours Faithfully:<br><br>Dept.
                                              <br>Joint Registrar (S & P)<br>for Director</div>
</td>       
      </tr>
       <tr><td colspan="2"><b>Payment from:</b>
      <br><b>Dept:</b>        <?php $m=$this->common_model->get_listspfic1('Department','dept_name','dept_id',$odr->pp_deptid)->dept_name;    
       echo $m; ?></td></tr>
      
      
        </tr> 
      <tr><td colspan="2"><b>Material Type:</b><?php  
                     $n=$this->PICO_model->get_listspfic1('material_type','mt_name','mt_id',$odr->pp_materialtypeid)->mt_name;         
       echo $n ?>
      <br><b>Budget Head:</b><?php echo $odr->pp_budgethead ?></td></tr>
      
      
        </tr> 
        <tr>
                <td colspan="2" style="width:18px;height:18px;">Copy to:(1) Accounts Section (2) Receipt Unit (3) Bill Unit (4) Internal Audit 
                (5) <?php echo $odr->pp_indentername; ?>,Dept of <?php echo $m ?> . w.r.t   Purchase Indent No.:<?php echo $odr->pp_indenterid; ?> .
                </td>
            </tr> 
        <tr>
                <td colspan="2"  style="width:18px;height:18px;font-size:10">This is a computer generated invoice no actual signature is required.
                </td>
            </tr>
             <?php } ?>             
              </table>
    </form>

        <br>
</body>
<p>&nbsp;</p>
    <div align=left> <?php $this->load->view('template/footer');?></div>
</html>

