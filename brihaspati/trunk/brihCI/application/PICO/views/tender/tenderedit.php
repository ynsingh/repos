<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Edit|Tender</title>
    <head>    
    <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <?php $this->load->view('template/header'); ?>
        
    </head>
    <body>
<!--<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>  check status of login-->
 <script type="text/javascript">
        function goBack() {
        window.history.back();
        
        
        
        }
        
        
        


</script>
   
        <table width="100%"> 
            <tr><td>   
              <div align="left">
                    <?php echo validation_errors('<div  class="isa_warning">','</div>');?>
                    <?php echo form_error('<div class="isa_error">','</div>');?></div>

                    <?php if(isset($_SESSION['success'])){?>
                        <div class="isa_success"><?php echo $_SESSION['success'];?></div>

                    <?php
                    }
                    ?>
                     <?php if(isset($_SESSION['err_message'])){?>
                        <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>

                    <?php
                    }
                    ?>
                </div>
        </td></tr>  
        </table>
      
        <table class="TFtable"> 
 
         <?php

            echo form_open('tender/tenderedit/' . $id,'class="form-inline"');
             ?>
             
                
                      <tr>
                <td><b>Tender Reference No:</b></td>
                <td><input  type="text"  name="tc_refno"  class="form-control" value="<?php echo $tc_refno['value'] ;?>" style="width:200;" /></td>
              
             	
         
                <td><b>Tender type:</b></td>
                <td><input  type="text"  name="tc_tentype"  class="form-control" value="<?php echo $tc_tentype['value'] ;?>" style="width:200;" />
                </td>
             
          
               
                <td><b>Form of contract:</b></td>
                <td><input  type="text"  name="tc_contractform"  class="form-control" value="<?php echo $tc_contractform ['value'] ;?>" style="width:200;" /></td>
              
          </tr>    
           
         
                <td><b>No. of Covers:</b></td>
                <td><input  type="text"  name="tc_coverid"  class="form-control" value="<?php echo $tc_coverid['value'] ;?>" style="width:200;" /></td>
              
         
        
                <td><b>Tender category:</b></td>
                <td><input  type="text"  name="tc_category"  class="form-control" value="<?php echo $tc_category['value'] ;?>" style="width:200;" /></td>
               
   
             
             
                <td><b>Allow Re-submission :</b></td>
                <td><input  type="text"  name="tc_allowresub"  class="form-control" value="<?php echo $tc_allowresub['value'] ;?>" style="width:200;" /></td>
               
               
              
         
         </tr>
         <tr>
                <td><b>Allow Withdrawal :</b></td>
                <td><input  type="text"  name="tc_allowwithdra"  class="form-control" value="<?php echo $tc_allowwithdra['value'] ;?>" style="width:200;" /></td>
             
        
                <td><b>Allow Offline Submission :</b></td>
                <td><input  type="text"  name="tc_allowoffline"  class="form-control" value="<?php echo $tc_allowoffline['value'] ;?>" style="width:200;" /></td>
               
          
                <td><b>Payment Mode:</b></td>
                <td><input  type="text"  name="tc_paymode"  class="form-control" value="<?php echo $tc_paymode['value'] ;?>" style="width:200;" /></td>
             
            
         </tr>  
           
         <tr>  
       
         <td><b>Offline:</b></td>
         <td><input  type="text"  name="tc_offlineinstrumentid"  class="form-control" value="<?php echo $tc_offlineinstrumentid['value'] ;?>" style="width:200;" /></td>
         
      
                
        <td><b>Online:</b></td>
        <td><input  type="text"  name="tc_onlinebankid"  class="form-control" value="<?php echo $tc_onlinebankid['value'] ;?>" style="width:200;" /></td>
               
              
        <td><b>Cover Content:</b></td>
        <td><input  type="text"  name="tc_covercontent"  class="form-control" value="<?php echo $tc_covercontent['value'] ;?>" style="width:200;" /></td>
               
         </tr>
         </table>
         <br> <br>
         
         <table class="TFtable">
            <tr> <td colspan="7">
               <h2 class="title">Work Item Details</h2>
                  
                 </td>           
            </tr>
            
            <tr>
                <td><b>Work Item Title:</b></td>
                <td><input  type="text"  name="tc_workitemtitle"  class="form-control" value="<?php echo $tc_workitemtitle['value'] ;?>" style="width:200;" /></td>
              
             	
         
                <td><b>Work Description:</b></td>
                <td><input  type="text"  name="tc_workdesc"  class="form-control" value="<?php echo $tc_workdesc['value'] ;?>" style="width:200;" />
                </td>
             
          
               
                <td><b>Prequal  Details:</b></td>
                <td><input  type="text"  name="tc_prequaldetails"  class="form-control" value="<?php echo $tc_prequaldetails['value'] ;?>" style="width:200;" /></td>
              
          </tr>    
           
         
                <td><b>Product Category:</b></td>
                <td><input  type="text"  name="tc_prodcatid"  class="form-control" value="<?php echo $tc_prodcatid['value'] ;?>" style="width:200;" /></td>
              
         
        
                <td><b>Product Sub Category:</b></td>
                <td><input  type="text"  name="tc_prodsubcat"  class="form-control" value="<?php echo $tc_prodsubcat['value'] ;?>" style="width:200;" /></td>
               
   
             
             
                <td><b>Contract Type:</b></td>
                <td><input  type="text"  name="tc_contracttype"  class="form-control" value="<?php echo $tc_contracttype['value'] ;?>" style="width:200;" /></td>
               
               
              
         
         </tr>
         <tr>
                <td><b>Tender Value:</b></td>
                <td><input  type="text"  name="tc_tendervalue"  class="form-control" value="<?php echo $tc_tendervalue['value'] ;?>" style="width:200;" /></td>
             
        
                <td><b>Bid Validity Days:</b></td>
                <td><input  type="text"  name="tc_bidvaliddays"  class="form-control" value="<?php echo $tc_bidvaliddays['value'] ;?>" style="width:200;" /></td>
               
          
                <td><b>Completion Period In Months:</b></td>
                <td><input  type="text"  name="tc_completionm"  class="form-control" value="<?php echo $tc_completionm['value'] ;?>" style="width:200;" /></td>
             
            
         </tr>  
           
         <tr>  
       
               <td><b>Location:</b></td>
               <td><input  type="text"  name="tc_location"  class="form-control" value="<?php echo $tc_location['value'] ;?>" style="width:200;" /></td>
         
      
               <td><b>Pincode:</b></td>
               <td><input  type="text"  name="tc_pincode"  class="form-control" value="<?php echo $tc_pincode['value'] ;?>" style="width:200;" /></td>
               
        
               <td><b>Pre Bid Meeting:</b></td>
               <td><input  type="text"  name="tc_prebidmeeting"  class="form-control" value="<?php echo $tc_prebidmeeting['value'] ;?>" style="width:200;" /></td>
       
        </tr>
        <tr>  
       
               <td><b>Pre Bid Meeting Place:</b></td>
               <td><input  type="text"  name="tc_prebidmeetplace"  class="form-control" value="<?php echo $tc_prebidmeetplace['value'] ;?>" style="width:200;" /></td>
         
      
               <td><b>Pre Bid Meting Address:</b></td>
               <td><input  type="text"  name="tc_prebidmeetadd"  class="form-control" value="<?php echo $tc_prebidmeetadd['value'] ;?>" style="width:200;" /></td>
               
        
               <td><b>BID Opening Place:</b></td>
               <td><input  type="text"  name="tc_bidopenplace"  class="form-control" value="<?php echo $tc_bidopenplace['value'] ;?>" style="width:200;" /></td>
       
        </tr>
        <tr>  
       
               <td><b>Inviting Officer:</b></td>
               <td><input  type="text"  name="tc_invitngofficer"  class="form-control" value="<?php echo $tc_invitngofficer['value'] ;?>" style="width:200;" /></td>
         
      
               <td><b>Inviting Officer Address:</b></td>
               <td><input  type="text"  name="tc_invitngoffadd"  class="form-control" value="<?php echo $tc_invitngoffadd['value'] ;?>" style="width:200;" /></td>
               
        
               <td><b>Inviting Officer Phone:</b></td>
               <td><input  type="text"  name="tc_invitngoffphone"  class="form-control" value="<?php echo $tc_invitngoffphone['value'] ;?>" style="width:200;" /></td>
       
        </tr>
        <tr>
        
               <td><b>Inviting Officer Email:</b></td>
               <td><input  type="text"  name="tc_invitngoffemail"  class="form-control" value="<?php echo $tc_invitngoffemail['value'] ;?>" style="width:200;" /></td>
               
                <td><b>Tender Class:</b></td>
               <td><input  type="text"  name="tc_tenderclass"  class="form-control" value="<?php echo $tc_tenderclass['value'] ;?>" style="width:200;" /></td> 
               
                <td><b>Tender Sub-Class:</b></td>
               <td><input  type="text"  name="tc_tendersubclass"  class="form-control" value="<?php echo $tc_tendersubclass['value'] ;?>" style="width:200;" /></td>  
        
        </tr>
       </table>
         
          <br> <br>
         
         <table class="TFtable">
            <tr> <td colspan="7">
               <h2 class="title">Fee Details</h2>
                  
                 </td>           
            </tr>
            
            <tr>
                <td><b>Tender fee:</b></td>
                <td><input  type="text"  name="tc_tenderfees"  class="form-control" value="<?php echo $tc_tenderfees['value'] ;?>" style="width:200;" /></td>
              
             	
         
                <td><b>Tender fee payable to:</b></td>
                <td><input  type="text"  name="tc_tenderfeespayble"  class="form-control" value="<?php echo $tc_tenderfeespayble['value'] ;?>" style="width:200;" />
                </td>
             
          
               
                <td><b>Tender fee payable at:</b></td>
                <td><input  type="text"  name="tc_tenderfeespaybleat"  class="form-control" value="<?php echo $tc_tenderfeespaybleat['value'] ;?>" style="width:200;" /></td>
              
          </tr>    
           
         
                <td><b>Processing fee:</b></td>
                <td><input  type="text"  name="tc_processingfees"  class="form-control" value="<?php echo $tc_processingfees['value'] ;?>" style="width:200;" /></td>
              
         
        
                <td><b>Surcharges:</b></td>
                <td><input  type="text"  name="tc_surcharge"  class="form-control" value="<?php echo $tc_surcharge['value'] ;?>" style="width:200;" /></td>
               
   
             
             
                <td><b>Other charges:</b></td>
                <td><input  type="text"  name="tc_othercharge"  class="form-control" value="<?php echo $tc_othercharge['value'] ;?>" style="width:200;" /></td>
               
               
              
         
         </tr>
         <tr>
                <td><b>EMD fee:</b></td>
                <td><input  type="text"  name="tc_emdfeesmode"  class="form-control" value="<?php echo $tc_emdfeesmode['value'] ;?>" style="width:200;" /></td>
             
        
                <td><b>EMD Amount(If fee is fixed):</b></td>
                <td><input  type="text"  name="tc_emdamount"  class="form-control" value="<?php echo $tc_emdamount['value'] ;?>" style="width:200;" /></td>
               
          
                <td><b>EMD Percentage(%)(If fee is percentage):</b></td>
                <td><input  type="text"  name="tc_emdpercentage"  class="form-control" value="<?php echo $tc_emdpercentage['value'] ;?>" style="width:200;" /></td>
             
            
         </tr>  
           
         <tr>  
       
               <td><b>EMD Exemption Allowed:</b></td>
               <td><input  type="text"  name="tc_emdexemption"  class="form-control" value="<?php echo $tc_emdexemption['value'] ;?>" style="width:200;" /></td>
         
      
               <td><b>EMD Exemption Percentage %(If Partial) :</b></td>
               <td colspan="3"><input  type="text"  name="tc_emdexemptionper"  class="form-control" value="<?php echo $tc_emdexemptionper['value'] ;?>" style="width:200;" /></td>
               
        
             
        </tr>
       
       </table>
		   <br> <br>
         
         <table class="TFtable">
            <tr> <td colspan="7">
               <h2 class="title">Critical Dates</h2>
                  
                 </td>           
            </tr>
            
            <tr>
                <td><b>Publishing Date:</b></td>
                <td><input  type="date"  name="tc_publishingdate"  class="form-control" value="<?php echo $tc_publishingdate['value'] ;?>" style="width:200;" /></td>
                <td><b>Time:</b><input  type="time"  name="tc_publishingdatet"  class="form-control" value="<?php echo $tc_publishingdatet['value'] ;?>" style="width:200;" /></td>
         
       
                 <td><b>Pre Bid Meeting Date:</b></td>
                <td><input  type="date"  name="tc_prebidmeetingdate"  class="form-control" value="<?php echo $tc_prebidmeetingdate['value'] ;?>" style="width:200;" /></td>
                <td><b>Time:</b><input  type="time"  name="tc_prebidmeetingdatet"  class="form-control" value="<?php echo $tc_prebidmeetingdatet['value'] ;?>" style="width:200;" /></td>
          
               
                
          </tr>    
           
         
           <tr>
                 
                 <td><b>Document Sale Start Date:</b></td>
                <td><input  type="date"  name="tc_docsalestartdate"  class="form-control" value="<?php echo $tc_docsalestartdate['value'] ;?>" style="width:200;" /></td>
                <td><b>Time:</b><input  type="time"  name="tc_docsalestartdatet"  class="form-control" value="<?php echo $tc_docsalestartdatet['value'] ;?>" style="width:200;" /></td>
           
                <td><b>Document Sale End Date:</b></td>
                <td><input  type="date"  name="tc_docsaleenddate"  class="form-control" value="<?php echo $tc_docsaleenddate['value'] ;?>" style="width:200;" /></td>
                <td><b>Time:</b><input  type="time"  name="tc_docsaleenddatet"  class="form-control" value="<?php echo $tc_docsaleenddatet['value'] ;?>" style="width:200;" /></td>
         
       
             
          
               
                
          </tr> 
          <tr>
          
                 <td><b>Seek Clarification Start Date:</b></td>
                <td><input  type="date"  name="tc_seekclailstartdate"  class="form-control" value="<?php echo $tc_seekclailstartdate['value'] ;?>" style="width:200;" /></td>
                <td><b>Time:</b><input  type="time"  name="tc_seekclailstartdatet"  class="form-control" value="<?php echo $tc_seekclailstartdatet['value'] ;?>" style="width:200;" /></td>
                
                
                <td><b>Seek Clarification End Date:</b></td>
                <td><input  type="date"  name="tc_seekclailenddate"  class="form-control" value="<?php echo $tc_seekclailenddate['value'] ;?>" style="width:200;" /></td>
                <td><b>Time:</b><input  type="time"  name="tc_seekclailenddatet"  class="form-control" value="<?php echo $tc_seekclailenddatet['value'] ;?>" style="width:200;" /></td>
         
        
          </tr> 
          <tr>
                <td><b>Bid Submission Start Date:</b></td>
                <td><input  type="date"  name="tc_bidsubstartdate"  class="form-control" value="<?php echo $tc_bidsubstartdate['value'] ;?>" style="width:200;" /></td>
                <td><b>Time:</b><input  type="time"  name="tc_bidsubstartdatet"  class="form-control" value="<?php echo $tc_bidsubstartdatet['value'] ;?>" style="width:200;" /></td>
         
       
             
          
               
                <td><b>Bid Submission End Date:</b></td>
                <td><input  type="date"  name="tc_bidsubenddate"  class="form-control" value="<?php echo $tc_bidsubenddate['value'] ;?>" style="width:200;" /></td>
                <td><b>Time:</b><input  type="time"  name="tc_bidsubenddatet"  class="form-control" value="<?php echo $tc_bidsubenddatet['value'] ;?>" style="width:200;" /></td>
          </tr> 
          <tr>
                <td><b>Bid Opening  Date:</b></td>
                <td><input  type="date"  name="tc_bidopeningdate"  class="form-control" value="<?php echo $tc_bidopeningdate['value'] ;?>" style="width:200;" /></td>
                <td><b>Time:</b><input  type="time"  name="tc_bidopeningdatet"  class="form-control" value="<?php echo $tc_bidopeningdatet['value'] ;?>" style="width:200;" /></td>
         
       
             
          
               
                <td colspan="3"></td>
                
          </tr> 
       
       </table>
       
     
         <br> <br>
         
         
   <table class="TFtable">
            <tr> <td colspan="7">
               <h2 class="title">Bid Opener Selection Details</h2>
                  
                 </td>           
            </tr>
            
    </table>
    
             <table class="TFtable">
              <tr>
            <thead><th>No</th><th>Name</th><th>Designation</th><th>Email</th></tr>
    
              <?php 
              $i=1;
		        foreach($bidoptender as $s){   
             ?>
            <tr>   
                <input  type="hidden" name="tc<?php echo $i ?>" value="<?php echo $s->tbos_tcid ;?>"  />
                <input  type="hidden" name="tbos<?php echo $i ?>" value="<?php echo $s->tbos_id ;?>"  />
                <td><input  type="text"  name="b<?php echo $i ?>"  class="form-control" value="<?php echo $s->tbos_bono ;?>" style="width:200;" /></td>
                <td><input  type="text"  name="n<?php echo $i ?>"  class="form-control" value="<?php echo $s->tbos_boname ;?>" style="width:200;" /></td>
                <td><input  type="text"  name="d<?php echo $i ?>"  class="form-control" value="<?php echo $s->tbos_bodesig ;?>" style="width:200;" /> </td>
                <td><input  type="text"  name="e<?php echo $i ?>"  class="form-control" value="<?php echo $s->tbos_boemail ;?>" style="width:200;" /></td>
            </tr> 
            
            <?php $i++; } ?>
 </table>

     <br> <br>
         
         
   <table class="TFtable">
            <tr> <td colspan="7">
               <h2 class="title">Upload Document Details</h2>
                  
                 </td>           
            </tr>
    </table>
            
            
             <table class="TFtable">
              <tr>
            <thead><th>Name</th><th>Description</th><th>Size</th><th>Type</th></tr>
     
          <?php 
               $j=1;
		        foreach($upldtender as $f){   
             ?>
          
            <tr>
            <input  type="hidden" name="tcc<?php echo $j ?>" value="<?php echo $f->tud_tcid ;?>"  />
               <input  type="hidden" name="tud<?php echo $j ?>" value="<?php echo $f->tud_id ;?>"  />
                <td><input  type="text"  name="f<?php echo $j ?>"  class="form-control" value="<?php echo $f->tud_filename ;?>" style="width:200;" /></td>
                <td><input  type="text"  name="de<?php echo $j ?>"  class="form-control" value="<?php echo $f->tud_desc ;?>" style="width:200;" /></td>
                <td><input  type="text"  name="si<?php echo $j ?>"  class="form-control" value="<?php echo $f->tud_filesize ;?>" style="width:200;" /> </td>
                <td><input  type="text"  name="ty<?php echo $j ?>"  class="form-control" value="<?php echo $f->tud_filetype ;?>" style="width:200;" /></td>
            </tr> 
            <?php $j++;  } ?>
 </table>

 <br> <br>  
       
     
<table class="TFtable">
            <tr> <td colspan="4">
               <h2 class="title">NIT Document Details</h2>
                  
                 </td>           
            </tr>
            
            <tr>
                <td><b>File Name:</b><?php echo $tc_nitdocfilename['value'] ; ?>
            
       
       
                <td><b>File Size:</b><?php $a=$tc_nitdocfilesize['value']/1024 ; 
                
                echo round($a,2) ?>KB
                
               </td>
               
                <td><b>File Type:</b><?php echo $tc_nitdoctype['value'] ; ?></td>
               <td><?php echo anchor('tender/tender_re/' . $id , "Re-upload", array('title' => 'change' , 'class' => 'red-link')); 	
                   ?></td>
                
          </tr> 
     
       </table>
           
             
        <table class="TFtable">   
        
          
        <tr>
        <?php            echo form_hidden('tc_id', $id);
                        
                    echo"<td >";
                    echo form_submit('submit', 'Update');
                   echo " ";   echo"</td>";
        ?>           
                   
                   <td colspan="4"></td>
             <td >          
             <button onclick=\"goBack()\" style="float:right; " >Back</button>
             </td>
       </tr>
        
       </table> 
          </form>
</body>
<p>&nbsp;</p>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>



