<?php defined('BASEPATH') OR exit('No direct script access allowed');

?>
<html>
<title>Purchase Proposal |Details Form</title>

 <head>
   <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
   <?php $this->load->view('template/header'); ?>
<br>    
<table>
<tr style="height:50%;width:100%"><b><big><big>Purchase Proposal Request Form For Products Available On Government eMarketplace(GeM)</big></big></b></tr>
</table>
<table>
  <tr>
                <td>
                <?php
                    echo anchor('intender/gem_proposals/','Go Back', array('title'=>''));
                ?>
               
                </td>
            </tr>  
</table>
  </head>
  <br>
<body>

   

    <form id="myform" action="<?php echo site_url('tender/proposal_entry');?>" method="POST" class="form-inline" autocomplete="OFF" enctype="multipart/form-data">
			<table class="TFtable" >
			         
                  <tr>
                 <td colspan="4">
                  To<br>
                  Officer In-charge<br>
                  Central Store & Purchase Section<br>
                  IIT Kanpur<br>	                  
                 </td>              
                
                
                </tr>			         
			     
			      <?php foreach($proposal as $e) {  ?>
             
               <tr> 
					  <td><b>GeM Ref No.</b></td>
                 <td>
                 <?php echo $e->pp_gemrefno; ?>
                 </td>
                 <td><b>Date</b></td>
                 <td><?php echo $e->pp_indentdate; ?></td> 
               </tr>
              
                <tr>
                <td><b>Department Indent No.</b></td>
                <td><?php echo $e->pp_deptindentno; ?></td>
                   <?php $m=$this->common_model->get_listspfic1('Department','dept_name','dept_id',$e->pp_deptid)->dept_name;
                   
                            $n=$this->PICO_model->get_listspfic1('material_type','mt_name','mt_id',$e->pp_materialtypeid)->mt_name;                     
                    ?>
                   
                <td><b>Department</b></td>
                <td><?php echo $m; ?></td>
                </tr>
             
               
                <tr>
             
                <td><b>Indenter PF No.</b></td>
                <td><?php  $i=$e->pp_indenterid ; 
                           $in=$e->pp_indentername;
                echo $e->pp_indenterid; ?>
                </td>
              
                <td><b>Indent Date</b></td>
                <td ><?php echo $e->pp_indentdate; ?></td>
                </tr> 
           
                    
			       <tr> 
					 <td><b>Indenter Name</b></td>
                <td>
               <?php echo $e->pp_indentername; ?>
                </td>
                <td><b>Indenter Email</b></td>
                <td><?php echo $e->pp_indenteremail; ?></td> 
                </tr>
                     <?php } ?> 
             </table> 
             <br>
             <table class="TFtable">
              <tr> 
              <td><b>Type of Material:</b></td> 
              <td><?php echo $n; ?></td>               
             </tr>   
            
             
        </table>
        <br>
      

        <table  id="myTable1"  class="TFtable" align="center">
        <thead align="center">
           <tr>  <th colspan="6">Details of Required Items:</th></tr>         
          <tr>  <th>Complete Description<br>(Good/Services intended<br>to be procured.)</th><th>Quantity<br>Required</th><th>Stock Held On Date</th> <th>GST Rate</th><th>Unit Price</th><th>Total(Inc. Taxes)</th>
          </tr>      
        </thead>
        
        <tbody>    
                <?php  foreach ($required as $r) { ?> 
                <tr>
                <td>   
                <?php  echo $r->rid_itemdes;   ?> 
                </td>
               

          
                <td>     <?php  echo $r->rid_itemqtyreq;   ?> </td>
                <td>     <?php  echo $r->rid_itemstock;   ?> </td> 
                <td>
                         <?php  echo $r->rid_itemgstapply;   ?> 
                </td>
                <td>     <?php  echo $r->rid_itemunitp;   ?> </td>
                <td>     <?php  echo $r->rid_itemtotcost;   ?> </td>
                   
                </tr> 
                   
                     <?php
                        }
                        ?>
            
              
            </tbody>
           
        </table>
        <br>
            <table class="TFtable">
               <?php foreach($proposal as $e) {  ?>   <tr>
                    <td colspan="6"><b>Total Cost</b><input type="text" name="pp_total" class="form-control" readonly value="<?php echo $e->pp_itemtotcost ?>" size="8" ></td>
                    </td>
                </tr>  <?php } ?>
            </table>
            <br>
          <table class="TFtable" id="myTable2">
            <tr><td colspan="5"><b><u>Budget Details:</u></b></td></tr>

            <tr>
            
                <th><center>Department/Project No.</center></th><th><center>Budget Head</center></th><th><center>Budget Amount</center></th></tr>
             <?php foreach($budget as $b) {  ?>
                 <tr>   
                    <td>     <center><?php echo $b->bd_budgetprojno ;?> </center> </td>
                    <td>      <center><?php echo $b->bd_budgethead ;?> </center> </td>
                    <td>    <center>  <?php echo $b->bd_budgetamount ;?> </center> </td>
                  
                </tr>
             <?php }  ?>
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


          

    
            <br>
          <table class="TFtable">
                <tr>   <?php foreach($proposal as $e) {  ?>
                    <td><label  >Delivery Period: </label></td>
                    
                    <td>  
                      <?php echo $e->pp_deliveryperiod.' Days  ' ?>
                        &nbsp; FROM:
                      <?php echo $from ?>
                         TO:                        
                      <?php echo $to ?>
                    </td>
                </tr>
                <tr >
                    <td><label for="pp_warranty">Warranty Statement:</label></td>
                    <td>
                        <textarea name="pp_warranty" style="width:100%;height:100px;" readonly placeholder="Give Warranty/Guarantee Descriptions here..."><?php echo $e->pp_warranty ?></textarea>
                    </td>
                </tr>
                <tr >
                    <td><label for="pp_guarantee">Guarantee Statement:</label></td>
                    <td>
                        <textarea name="pp_guarantee" style="width:100%;height:100px;" readonly placeholder="Give Warranty/Guarantee Descriptions here..."><?php echo $e->pp_guarantee ?></textarea>
                    </td>
                </tr>
                <tr>
                    <td><label for="pp_payterm" class="control-label">Payment Terms: </label></td>
                    <td>
                         <textarea name="pp_payterm" style="width:100%;height:100px;" readonly placeholder="Give Payment terms here..."><?php echo $e->pp_payterm ?></textarea>
                    </td>
               <?php }  ?>   </tr>
                 
                
                
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
             
        </table>
        <br>

      <table  class="TFtable" >     <?php foreach($proposal as $e) {  ?>
         <tr><th colspan="2"><u>Budget Unit:</u></th></tr>
                <tr>
                    <td><label  class="control-label">Name: </label></td>
                    <td>
                       <?php echo $e->pp_budgetapprovedby ?>
                    </td>
               
                </tr>  
                <tr>
                    <td><label  class="control-label">Date: </label></td>
                    <td>
                       <?php echo $e->pp_budgetapproveddate ?>
                    </td>
               
                </tr>   
                   <tr>
                       <td><label  class="control-label">Funds Available: </label></td>
                       <td>
                       <?php echo $e->pp_budgetapproved ?>
                       </td>
               
                </tr>  
                 <tr>
                    <td><label  class="control-label">Funds Committed: </label></td>
                    <td>
                      <?php echo $e->pp_itemtotcost ?>
                    </td>
               
                </tr>
               <tr>
                    <td><label for="bu_comment" class="control-label">Remark: </label></td>
                    <td><textarea name="bu_comment"  readonly style="width:47%; height:200%">     <?php echo $e->pp_budgetcomment ?>  </textarea>                </td>
                  
                </tr>
                                 <?php }  ?>          
      </table>
     <br>
     <table  class="TFtable" >      <?php foreach($proposal as $e) { 
                                                                       ?>
         <tr><th colspan="2"><u>Internal Audit:</u></th></tr>
         <tr>
                    <td><label  class="control-label">Name: </label></td>
                    <td>
                       <?php echo $e->pp_auditapprovedby ?>
                    </td>
               
                </tr>  
                <tr>
                    <td><label  class="control-label">Date: </label></td>
                    <td>
                       <?php echo $e->pp_auditapproveddate ?>
                    </td>
               
                </tr>
                <tr>
                    <td><label class="control-label">Status: </label></td>
                    <td>
                        <?php echo $e->pp_auditapproved ?>
                    </td>
                  
                </tr>
               <tr>
                    <td><label class="control-label">Audit Observation: </label></td>
                    <td><textarea  readonly style="width:47%; height:200%">     <?php echo $e->pp_auditobservation ?>  </textarea>                </td>
                  
                </tr>
                               <?php }  ?>  
      </table>
      
        <br>
     <table  class="TFtable" >    <?php foreach($proposal as $e) {  ?>
         <tr><th colspan="2"><u>Expenditure Sanctioning Authority:</u></th></tr>
         <tr>
                    <td><label  class="control-label">Name: </label></td>
                    <td>
                       <?php echo $e->pp_expsanctionby ?>
                    </td>
               
                </tr>
                 <tr>
                    <td><label  class="control-label">Date: </label></td>
                    <td>
                       <?php echo $e->pp_expsanctiondate ?>
                    </td>
               
                </tr>
               <tr>
                    <td><label class="control-label">Status: </label></td>
                    <td>
                        <?php echo $e->pp_expsanctionstatus ?>
                    </td>
                  
                </tr>
                                 <?php }  ?>  
      </table>
    </form>

        <br>
</body>
<p>&nbsp;</p>
    <div align=left> <?php $this->load->view('template/footer');?></div>
</html>

