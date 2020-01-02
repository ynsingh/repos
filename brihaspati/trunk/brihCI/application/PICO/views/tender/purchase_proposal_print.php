<?php defined('BASEPATH') OR exit('No direct script access allowed');

?>
<html>
<title>Purchase Proposal |Details Form</title>
 
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


 
<body>

   

    <form id="myform" action="<?php echo site_url('tender/proposal_entry');?>" method="POST" class="form-inline" autocomplete="OFF" enctype="multipart/form-data">
			<table class="TFtable" >
			         
                  <tr>
                 <td colspan="4">
                  To<br>
                  Officer In-charge<br>
                  Store & Purchase Section<br>
                  IIT Kanpur<br>	                  
                 </td>              
                
                
                </tr>			         
			     
             
                
                <tr>
                <td><b>Department Indent No.</b></td>
                <td>hii</td>
                <td><b>Department</b></td>
                <td>hii</td>
                </tr>
                <?php  foreach ($tcresult as $t) { ?> 
               
                <tr>
             
                <td><b>Indenter PF No.</b></td>
                <td>
                </td>
              
                <td><b>Indent Date</b></td>
                <td ><?php echo date('d-m-Y'); ?></td>
                </tr> 
                <?php } ?> 
                    
			       <tr> 
					 <td><b>Quotation Attached(y/n)</b></td>
                <td>
               
                </td>
                <td><b>GST/CDEC Required(y/n)</b></td>
                <td></td> 
                </tr>
                
             </table> 
             <br>
             <table class="TFtable">
              <tr> 
              <td><b>Type of Material:</b></td> 
              <td> here  </td>               
             </tr>   
             <tr>
              <td><b>Purchase Order Type</b></td>
              <td>Normal</td>   
             </tr>
        </table>
        <br>
      

        <table  id="myTable1"  class="TFtable" align="center">
        <thead align="center">
           <tr>  <th colspan="6">Details of Required Items:</th></tr>         
          <tr>  <th>Complete Description<br>(Good/Services intended<br>to be procured.)</th><th>Quantity<br>Required</th><th>Stock Held On Date</th> <th>Purpose</th><th>Unit Price</th><th>Total(Inc. Taxes)</th>
          </tr>      
        </thead>
        
        <tbody>    
                <tr>
                <td>   
                     <?php  foreach ($tcresult as $t) { ?> 
              <?php
                      echo '<b>Work Tittle:</b>'.$t->tc_workitemtitle.' <br>'.'&'.'<br> '.' ';
                      echo '<b>Work Description:</b>'.$t->tc_workdesc ;
                       ?> 
                </td>
                <td><?php echo $t->tc_quantity ?></td>
                <?php $q=$t->tc_quantity; } ?>
                <?php  foreach ($taresult as $tt) { ?> 
                <td> 50</td>
                <td>R&D Purpose</td>
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
                         FROM:
                      <?php echo date('d-m-Y') ?>
                         TO:                        
                      <?php echo date('d-m-Y',strtotime('+'.$tt->ta_delivery.' days')) ?>
                    </td>
                </tr>
                <tr >
                    <td><label for="pp_warranty">Warranty Statement:</label></td>
                    <td>
                        <textarea name="pp_warranty" style="width:100%;height:100px;"  placeholder="Give Warranty/Guarantee Descriptions here..."><?php echo $tt->ta_warranty ?></textarea>
                    </td>
                </tr>
                <tr >
                    <td><label for="pp_guarantee">Guarantee Statement:</label></td>
                    <td>
                        <textarea name="pp_guarantee" style="width:100%;height:100px;" placeholder="Give Warranty/Guarantee Descriptions here..."><?php echo $tt->ta_guarantee ?></textarea>
                    </td>
                </tr>
                <tr>
                    <td><label for="pp_payterm" class="control-label">Payment Terms: </label></td>
                    <td>
                         <textarea name="pp_payterm" style="width:100%;height:100px;" placeholder="Give Payment terms here..."><?php echo $tt->ta_payment ?></textarea>
                    </td>
                </tr>
                   <?php
                        }
                        ?>
                
                
            </table>            
        <br>
       <table class="TFtable">
              
        
        </table>        
        <table class="TFtable" id="myTable2">
            <tr><td colspan="5"><b><u>Budget Details:</u></b></td></tr>

            <tr>
            
                <th><center>Department/Project No.</center></th><th><center>Budget Head</center></th><th><center>Budget Amount</center></th></tr>
             <tr>    
                    <td></td>
                    <td></td>
                    <td></td>
                   
                </tr>
            
        </table>
<br>
        <table class="TFtable">
             <tr><th colspan="4"><u>Supplier:</u></th></tr>
              <tr>   <?php   foreach($result as $w ) { ?>
                   
                    <td><b>Name:</b></td>
                    <td>
             <?php echo $w->vendor_companyname ;?>
                    </td>
                    <td><b>Address:</b></td>
                    <td>
                  <?php echo $w->vendor_address ;?>
                    </td>  <?php  } ?>
                </tr>
            </table>


          

        </table> 
   <br>
        <table class="TFtable">
            <tr>
                <td>
                <input type="checkbox" name="" value="" id="" checked="" style="width:18px;height:18px;"/ >1. Certified that the goods/services intended to be purchased (as above) is/are not distributed through Central Store & Purchase Section.
                </td>
            </tr>  
             <tr>
                <td>
                <input type="checkbox" name="" value="" id="" checked="" style="width:18px;height:18px;"/>2. Certified that the Allocation exists for the above amount.
                </td>
            </tr>  
              <tr>
                <td>
                <input type="checkbox" name="" value="" id="" checked="" style="width:18px;height:18px;"/>3. Certified that the goods/services intended to be procured (as above) is/are not available on Government e-Marketplace (GeM) or the supply of the same through GeM is not available in Kanpur.
                </td>
            </tr> 
        </table>
        <br>
      <table  id="myTable1"  class="TFtable" align="center">
        <thead align="center">
                 
          <tr>  <td>-----------------------</td>
          <td>Upto 10000.00<br>       <br><br>(NC/LTAS/Cons/Services)<br><br><br><br></td>
          <td>10000.00 <br>Upto 5 Lacs<br><br>(NC/LTAS/Cons/Services)<br><br><br><br></td>
          <td>>5 Lacs <br>Upto 25 Lacs<br><br>(NC/LTAS/Cons/Services)<br><br><br><br></td>
          <td>>25 Lacs <br>Upto 50 Lacs<br><br>(NC/LTAS/Cons/Services)<br><br><br><br></td>
          <td>>50 Lacs<br>Upto 10 Crores<br><br>(NC/LTAS/Cons/Services)<br><br>Note:>10 Crore subject<br>to approval of BOG<br>is Required.</td>
          </tr>      
        </thead>
        
        <tbody>    
                <tr>
                <td><br><br><br><b>Name</b>
                <br>
                 hiiii                
                <br><br><br><br>
                    <b>Indenter</b><br><br>
                    <b>PF No</b><br><br>
                    <b>Phone No</b><br><br>
                     
                </td>
                <td><br><br><br>
                    <b>Officer In-charge</b><br></td>
                <td><br><br><br>
                    <b>Dean/Registrar/HOD/Libr/HOS/P.I</b><br></td>
                <td><br><br><br>
                    <b>DORD</b><br></td>
                <td><br><br><br>
                    <b>Dy. Director</b><br></td>
                <td><br><br><br>
                    <b>Director</b><br></td>
                    
                   
                   
                </tr>
                
            </tbody>
           
        </table>
    </form>

        <br>
</body>
<p>&nbsp;</p>
    <div align=left> <?php $this->load->view('template/footer');?></div>
</html>

