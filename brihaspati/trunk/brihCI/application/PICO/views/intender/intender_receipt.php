<?php defined('BASEPATH') OR exit('No direct script access allowed');

?>
<html>
<title>Inspection|Details Form</title>

 <head>
   <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
   <?php $this->load->view('template/header'); ?>
<br>    
<table>
<tr style="height:50%;width:100%"><b><big><big>Receipt Unit</big></big></b></tr>
</table>
  </head>
  <br>
<body>

   

    <form id="myform" action="<?php echo site_url('intender/receipt_entry');?>" method="POST" class="form-inline" autocomplete="OFF" enctype="multipart/form-data">
			<table class="TFtable" >
			         
                        
			     
			      <?php foreach($proposal as $e) {  ?>
             
                
                <tr>
              
                   <?php $m=$this->common_model->get_listspfic1('Department','dept_name','dept_id',$e->pp_deptid)->dept_name;
                     $i8=$m;
                       
                            $n=$this->PICO_model->get_listspfic1('material_type','mt_name','mt_id',$e->pp_materialtypeid)->mt_name;     
                             
                           $pn=$this->PICO_model->get_listspfic1('purchase_order','po_no','po_ppid',$e->pp_id)->po_no;       
                          $i2=$pn;   
                             $pd=$this->PICO_model->get_listspfic1('purchase_order','po_date','po_ppid',$e->pp_id)->po_date;                  
                          $i3=$pd;    
                            $in=$e->pp_indentername;              
                    ?>
                   
                <td><b>Department of :</b></td>
                <td><?php echo $m; ?></td>
                  <td><b></b></td>
                <td><?php  ?></td>
                </tr>
             <tr>    
                <td><b>For Attention of :</b></td>
                <td><?php echo $in; ?></td>
                  <td><b></b></td>
                <td><?php  ?></td>
                </tr>
                   
                <td><b>Purchase Indent No:</b></td>
                <td><?php echo $e->pp_indenterid; ?></td>
               
                <td><b>Dated:</b></td>
                <td ><?php echo $e->pp_indentdate; ?></td>
                </tr>
                  <tr>   <?php   foreach($result as $w ) { ?>
                   
                    <td><b>Name of Supplier:</b></td>
                    <td>
                       <?php $i9=$w->vendor_companyname ;
                              echo $i9;?>
                    </td>
                    <td><b>Address:</b></td>
                    <td>
                  <?php echo $w->vendor_address ;?>
                    </td>  <?php  } ?>
                </tr>                
                <tr>
                
             
                <td><b>PO No.:</b></td>
                <td><?php  $i=$e->pp_indenterid ; 
                           $i6=$i;
                           $in=$e->pp_indentername;
                           $i7=$in;
                echo $pn; ?>
                </td>
              
                <td><b>Dated:</b></td>
                <td ><?php $idate=$e->pp_indentdate; 
                         echo $pd;                         
                         ?></td>
                </tr> 
                <?php } 
             $whdata = array('item_pono' => $pn);
				 $fieldems="item_challanno,item_challandate,item_qty,item_id";
				 $whorderems = '';
				 $hey = $this->PICO_model->get_orderlistspficemore('items',$fieldems,$whdata,$whorderems);                
               foreach($hey as $h) {
                ?> 
                    
			       <tr> 
					 <td><b>R/R/Challan No</b></td>
                <td>
               <?php $i4=$h->item_challanno; 
               $i1=$h->item_id;
                     echo $i4;   ?>
                </td>
                <td><b>Dated :</b></td>
                <td>
               <?php 
               $qty=$h->item_qty;
                   $i5=$h->item_challandate; 
                  echo $i5; ?>
                </td> 
                </tr><?php } ?>
                 <tr> 
              <td ><b>Remarks:</b></td>
               <td colspan="3"><textarea name="ig" style="width:100%" ></textarea></td>
              </tr>
             </table> 
             <br>
             <table class="TFtable">
   <tr></tr>             
             <tr>             
               <td><br>Please arrange to inspect the following goods and return this inspection report along with requisition slip within 15 days or earlier from the receipt of the material 
                  failing which <br>it will be presumed that you have accepted the goods.
                  <br><br>If in unusual circumstances inspection cannot be made when within a period of 15 days,please advise the receipt unit of the stores purchase section the probable date,
                  when<br> inspection report will be sent.
                  <br><br>Please return one copy immediately duly signed giving receipt of the goods / items.
                  </td>               
             </tr>   
            
        </table>
        <br>
      

        <table  id="myTable1"  class="TFtable" align="center">
        <thead align="center">
           <tr>  <th colspan="6">Details of Required Items:</th></tr>         
          <tr>  <th>Complete Description<br>(Good/Services intended<br>to be procured.)</th><th>Quantity<br>Supplied</th><th>Quantity<br>Approved</th> <th>Quantity<br>Rejected</th><th>Reason For Rejection</th><th>Replacement Required</th>
          </tr>      
        </thead>
        
        <tbody>    
                <tr>
                <td>   
                     <?php  foreach ($tcresult as $t) { ?> 
              <?php
                      echo $t->tc_workitemtitle.' <br>'.''.'<br> '.' ';
                      echo $t->tc_workdesc ;
                      $ia=$t->tc_workitemtitle.','.' '.$t->tc_workdesc;
                      $ib=$qty;
                       ?> 
                </td>
                <td><?php echo $qty ?></td>
                <?php  } ?>
              
                <td><input type="text" name="ic" ></td>
                <td><input type="text" name="id" ></td>
                <td><textarea name="ie"></textarea></td>
                <td><textarea name="if"></textarea></td>
                    
               
                   
                </tr>
               </table>
               <br>
        <table class="TFtable">
                <tr>  <td style="float:left;width:100%;"><b>Dealing Assistant:</b>hii</td>
               <td colspan="4"></td>
                 <td style="width:50%;"> 
                                              <div style="float:right;width:50%;"><b>In-charge:</b>hii<br><br></div>
               </td>       
                </tr>
             <tr>  <td colspan="6">The material has been entered in the stock register page no....................</td></tr>
                <tr>  <td style="float:left;width:100%;"><b>Indenter:</b><?php echo $i7 ?></td>
               <td colspan="4"></td>
                 <td style="width:50%;"> 
                                              <div style="float:right;width:50%;"><b>Head of Dept.:</b>hii<br><br></div>
               </td>       
                </tr>
           
           
        </tbody>
           
        </table>
           <tr>
                </tr>
                <tr>
                <td></td>
                <td>
                  <input type="hidden" name="i1" value="<?php echo $i1; ?>" />                
                  <input type="hidden" name="i2" value="<?php echo $i2; ?>" /> 
                  <input type="hidden" name="i3" value="<?php echo $i3; ?>" /> 
                  <input type="hidden" name="i4" value="<?php echo $i4; ?>" /> 
                   
                    <input type="hidden" name="i5" value="<?php echo $i5; ?>" /> 
                    <input type="hidden" name="i6" value="<?php echo $i6; ?>" /> 
                    <input type="hidden" name="i7" value="<?php echo $i7; ?>" /> 
                    <input type="hidden" name="i8" value="<?php echo $i8; ?>" /> 
                    
                     <input type="hidden" name="i9" value="<?php echo $i9; ?>" /> 
                     <input type="hidden" name="ia" value="<?php echo $ia; ?>" /> 
                     <input type="hidden" name="ib" value="<?php echo $ib; ?>" /> 
                      
                     
                <button name="press">Submit</button>
                </td>
           		</tr>
<br>
      
    </form>

        <br>
</body>
<p>&nbsp;</p>
    <div align=left> <?php $this->load->view('template/footer');?></div>
</html>

