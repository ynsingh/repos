<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Tender|Apply</title>

 <head>
  <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
     <?php $this->load->view('template/header'); ?>
   
 </head> 
 
 <body> 


     <table width="100%">
            <tr><td>
                <?php echo anchor('', "View Detail ", array('title' => 'Add Detail' ,'class' =>'top_parent'));?>
                <?php
                 echo "<td align=\"right\">";
                 $help_uri = site_url()."/help/helpdoc#Role";
		 echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
		echo "</td>"
                 ?>
                <div  style="width:100%;">
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
             </td></tr>
        </table>




<form action="<?php echo site_url('tender/tender_apply/');?>" method="POST" class="form-inline" enctype="multipart/form-data"> 



<?php 
		        foreach($result as $r){   
             ?>
      
          <table class="TFtable">
          <tr>
                <td><label for="" class="control-label">Tender Reference No:-</label></td>
                <td><input type="text" name="a_ref" value="<?php echo $tc_refno ?>" readonly style="width:300 ;" /><br></td>
                
           </tr>
            <tr>
                <td><label for="" class="control-label">Tender ID:-</label></td>
                <td><input type="text" name="a_id" value="<?php echo $tc_id ?>" readonly style="width:300 ;" /><br></td>
                
           </tr>
           <tr>
                <td><label for="" class="control-label">Tender Total Fees:-<br>( Tender + Processing + Surcharge + Other)</label></td>
                <td><input type="text" name="a_fee" value="<?php echo $r->tc_tenderfees+$r->tc_othercharge+$r->tc_processingfees+$r->tc_surcharge ?>" readonly style="width:300 ;" /><br></td>
                
           </tr>
           <tr>
                <td><label for="" class="control-label">EMD Fees/Percentage:-</label></td>
                <td><input type="text" name="a_emd" value="<?php 
                                                         if($r->tc_emdfeesmode == 'fixed')
                                                             {echo $r->tc_emdamount.' Inr' ;}
                                                         if($r->tc_emdfeesmode == 'percentage') 
                                                             {echo $r->tc_emdpercentage.'% ' ;}   
                                                      ?>" readonly style="width:300 ;" /><br></td>
                
           </tr>
            <tr>
                <td><label for="" class="control-label">EMD Exemption Allowed:-</label></td>
                <td><input type="text" name="a_emda" value="<?php 
                                                         if($r->tc_emdexemption == 'full')
                                                             { echo $r->tc_emdexemption ; }
                                                         if($r->tc_emdexemption == 'partial') 
                                                             {echo $r->tc_emdexemption.' And '.$r->tc_emdexemptionper.'% ' ;}  
                                                         if($r->tc_emdexemption == 'none') 
                                                             {echo $r->tc_emdexemption ;}       
                                                      ?>" readonly style="width:300 ;" /><br></td>
                
           </tr>
            <tr>      
                <td><label for="" class="control-label">Supplier ID:-</label></td>
                <td><input type="text" name="a_vid" value="5" style="width:300 ;" /><br></td>
                
                
           </tr>
           <tr>
                <td><label for="" class="control-label">Base Price <font color="red">*</font>:-</label></td>
                <td><input type="text" name="a_bp"  class="form-control" style="width:300 ;" /><br></td>
                
           </tr>
           <tr>      
                <td><label for="" class="control-label">GST  Tax  <font color="red">*</font>:-</label></td>
                <td><input type="text" name="a_gst"  class="form-control" style="width:300 ;" /><br></td>
                
                
           </tr>
           <tr>      
                <td><label for="" class="control-label">Total Price  <font color="red">*</font>:-</label></td>
                <td><input type="text" name="a_total"  class="form-control" style="width:300 ;" /><br></td>
                
                
           </tr>
           <tr>      
                <td><label for="" class="control-label">Warranty Statement  <font color="red">*</font>:-</label></td>
                <td><input type="text" name="a_warranty"  class="form-control" style="width:300 ;" /><br></td>
                
                
           </tr>
           <tr>      
                <td><label for="" class="control-label">Payment Statement  <font color="red">*</font>:-</label></td>
                <td><input type="text" name="a_payment"  class="form-control" style="width:300 ;" /><br></td>
                
                
           </tr>
           <tr>      
                <td><label for="" class="control-label">Delivery Period(In Days)  <font color="red">*</font>:-</label></td>
                <td><input type="text" name="a_delivery"  class="form-control" style="width:300 ;" /><br></td>
                
                
           </tr>
           <tr>      
                <td><label for="" class="control-label">Validity Period(In Days):-</label></td>
                <td><input type="text" name="a_validity"  class="form-control" style="width:300 ;" /><br></td>
                
                
           </tr>
          
    </table>
    <?php } ?>
    <br><br>
      <table class="TFtable">
              <tr>
            <thead><th>Upload Documents</th><th>Description</th><th>Size</th><th>Type</th></tr>
     
         
              <?php $i=1;
		        foreach($upload as $f){   
             ?>
      
          
            <tr>
                
                <td><?php echo $f->tud_filename ; ?></td>
                <td><?php echo $f->tud_desc ; ?></td>
                <td><?php echo $f->tud_filesize ; ?> </td>
                <td><?php echo $f->tud_filetype ; ?></td>
            </tr> 
            <tr>  
                    <input type="hidden" name="n_<?php echo $i ?>" value="<?php echo $f->tud_filename ; ?>" />
                    <input type="hidden" name="s_<?php echo $i ?>" value="<?php echo $f->tud_filesize ; ?>" />
                    <input type="hidden" name="t_<?php echo $i ?>" value="<?php echo $f->tud_filetype ; ?>" />
                    <td>Upload <b><?php echo $f->tud_filename ;?></b></td>
                    <td colspan="3"><input type="file" name="file_<?php echo $i ?>"  ></td>
            </tr>
            <?php   ++$i; } ?>
 </table>
    
          
  <table class="TFtable"> 
          <tr>
               
              
                  <td colspan="2">
                   
                
                
            
                  <button name="submit" >Apply</button>
              
                  
                  
        
                
         
                  </td>
           
          </tr>
          </table>


      </form>
  
  
  
  
 <p><br></p>
    </div>
    </body>
<p>&nbsp;</p>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
    </html>
	
	

