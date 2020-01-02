<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<head>
<title>Tender|View</title>
  
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
                  $suname=$this->session->userdata['username'];
                  if((strcasecmp($suname,"admin"))==0)
                  {
                   echo anchor('tender/tenderdisplay', "View Tenders", array('title' => 'Tender Detail','class' =>'top_parent'));
                  }  
                  echo "</td>";
		      
		           ?>
                <?php
		           echo "<td align=\"center\" width=\"34%\">";
                 echo "<b><big> Tender </big></b>";
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
 

   <?php //print_r($tcresult);
		foreach($tcresult as $res){   
    ?>
          <table class="TFtable">
            <tr> <td colspan="7">
               <h2 class="title">Basic Details</h2>
                  
                 </td>           
            </tr>
            
            <tr>
                <td><b>Tender Reference No:</b></td>
                <td><?php echo $res->tc_refno ; ?></td>
              
             	
         
                <td><b>Tender type:</b></td>
                <td><?php echo $res->tc_tentype ; ?>
                </td>
             
          
               
                <td><b>Form of contract:</b></td>
                <td><?php echo $res->tc_contractform  ; ?></td>
              
          </tr>    
           
         
                <td><b>No. of Covers:</b></td>
                <td><?php echo $res->tc_coverid ; ?></td>
              
         
        
                <td><b>Tender category:</b></td>
                <td><?php echo $res->tc_category ; ?></td>
               
   
             
             
                <td><b>Allow Re-submission :</b></td>
                <td><?php echo $res->tc_allowresub ; ?></td>
               
               
              
         
         </tr>
         <tr>
                <td><b>Allow Withdrawal :</b></td>
                <td><?php echo $res->tc_allowwithdra ; ?></td>
             
        
                <td><b>Allow Offline Submission :</b></td>
                <td><?php echo $res->tc_allowoffline ; ?></td>
               
          
                <td><b>Payment Mode:</b></td>
                <td><?php echo $res->tc_paymode ; ?></td>
             
            
         </tr>  
           
         <tr>  
       
         <td><b>Offline:</b></td>
         <td><?php echo $res->tc_offlineinstrumentid ; ?></td>
         
      
                
        <td><b>Online:</b></td>
        <td><?php echo $res->tc_onlinebankid ; ?></td>
               
              
        <td><b>Cover Content:</b></td>
        <td><?php echo $res->tc_covercontent ; ?></td>
               
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
                <td><?php echo $res->tc_workitemtitle ; ?></td>
              
             	
         
                <td><b>Work Description:</b></td>
                <td><?php echo $res->tc_workdesc ; ?>
                </td>
             
          
               
                <td><b>Prequal  Details:</b></td>
                <td><?php echo $res->tc_prequaldetails ; ?></td>
              
          </tr>    
           
         
                <td><b>Product Category:</b></td>
                <td><?php echo $res->tc_prodcatid ; ?></td>
              
         
        
                <td><b>Product Sub Category:</b></td>
                <td><?php echo $res->tc_prodsubcat ; ?></td>
               
   
             
             
                <td><b>Contract Type:</b></td>
                <td><?php echo $res->tc_contracttype ; ?></td>
               
               
              
         
         </tr>
         <tr>
                <td><b>Tender Value:</b></td>
                <td><?php echo $res->tc_tendervalue ; ?></td>
             
        
                <td><b>Bid Validity Days:</b></td>
                <td><?php echo $res->tc_bidvaliddays ; ?></td>
               
          
                <td><b>Completion Period In Months:</b></td>
                <td><?php echo $res->tc_completionm ; ?></td>
             
            
         </tr>  
           
         <tr>  
       
               <td><b>Location:</b></td>
               <td><?php echo $res->tc_location ; ?></td>
         
      
               <td><b>Pincode:</b></td>
               <td><?php echo $res->tc_pincode ; ?></td>
               
        
               <td><b>Pre Bid Meeting:</b></td>
               <td><?php echo $res->tc_prebidmeeting ; ?></td>
       
        </tr>
        <tr>  
       
               <td><b>Pre Bid Meeting Place:</b></td>
               <td><?php echo $res->tc_prebidmeetplace ; ?></td>
         
      
               <td><b>Pre Bid Meting Address:</b></td>
               <td><?php echo $res->tc_prebidmeetadd ; ?></td>
               
        
               <td><b>BID Opening Place:</b></td>
               <td><?php echo $res->tc_bidopenplace ; ?></td>
       
        </tr>
        <tr>  
       
               <td><b>Inviting Officer:</b></td>
               <td><?php echo $res->tc_invitngofficer ; ?></td>
         
      
               <td><b>Inviting Officer Address:</b></td>
               <td><?php echo $res->tc_invitngoffadd ; ?></td>
               
        
               <td><b>Inviting Officer Phone:</b></td>
               <td><?php echo $res->tc_invitngoffphone ; ?></td>
       
        </tr>
        <tr>
        
               <td><b>Inviting Officer Email:</b></td>
               <td><?php echo $res->tc_invitngoffemail ; ?></td>
               
                <td><b>Tender Class:</b></td>
               <td><?php echo $res->tc_tenderclass ; ?></td> 
               
                <td><b>Tender Sub-Class:</b></td>
               <td><?php echo $res->tc_tendersubclass ; ?></td>  
        
        </tr>
         <tr>

         <td><b>Quantity:</b></td>
         <td colspan="5"><?php echo $res->tc_quantity ; ?></td>
         
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
                <td><?php echo $res->tc_tenderfees ; ?></td>
              
             	
         
                <td><b>Tender fee payable to:</b></td>
                <td><?php echo $res->tc_tenderfeespayble ; ?>
                </td>
             
          
               
                <td><b>Tender fee payable at:</b></td>
                <td><?php echo $res->tc_tenderfeespaybleat ; ?></td>
              
          </tr>    
           
         
                <td><b>Processing fee:</b></td>
                <td><?php echo $res->tc_processingfees ; ?></td>
              
         
        
                <td><b>Surcharges:</b></td>
                <td><?php echo $res->tc_surcharge ; ?></td>
               
   
             
             
                <td><b>Other charges:</b></td>
                <td><?php echo $res->tc_othercharge ; ?></td>
               
               
              
         
         </tr>
         <tr>
                <td><b>EMD fee:</b></td>
                <td><?php echo $res->tc_emdfeesmode ; ?></td>
             
        
                <td><b>EMD Amount(If fee is fixed):</b></td>
                <td><?php echo $res->tc_emdamount ; ?></td>
               
          
                <td><b>EMD Percentage(%)(If fee is percentage):</b></td>
                <td><?php echo $res->tc_emdpercentage ; ?></td>
             
            
         </tr>  
           
         <tr>  
       
               <td><b>EMD Exemption Allowed:</b></td>
               <td><?php echo $res->tc_emdexemption ; ?></td>
         
      
               <td><b>EMD Exemption Percentage %(If Partial) :</b></td>
               <td colspan="3"><?php echo $res->tc_emdexemptionper ; ?></td>
               
        
             
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
                <td><?php echo $res->tc_publishingdate ; ?></td>
                <td><b>Time:</b><?php echo $res->tc_publishingdatet ; ?></td>
         
       
                 <td><b>Pre Bid Meeting Date:</b></td>
                <td><?php echo $res->tc_prebidmeetingdate ; ?></td>
                <td><b>Time:</b><?php echo $res->tc_prebidmeetingdatet ; ?></td>
          
               
                
          </tr>    
           
         
           <tr>
                 
                 <td><b>Document Sale Start Date:</b></td>
                <td><?php echo $res->tc_docsalestartdate ; ?></td>
                <td><b>Time:</b><?php echo $res->tc_docsalestartdatet ; ?></td>
           
                <td><b>Document Sale End Date:</b></td>
                <td><?php echo $res->tc_docsaleenddate ; ?></td>
                <td><b>Time:</b><?php echo $res->tc_docsaleenddatet ; ?></td>
         
       
             
          
               
                
          </tr> 
          <tr>
          
                 <td><b>Seek Clarification Start Date:</b></td>
                <td><?php echo $res->tc_seekclailstartdate ; ?></td>
                <td><b>Time:</b><?php echo $res->tc_seekclailstartdatet ; ?></td>
                
                
                <td><b>Seek Clarification End Date:</b></td>
                <td><?php echo $res->tc_seekclailenddate ; ?></td>
                <td><b>Time:</b><?php echo $res->tc_seekclailenddatet ; ?></td>
         
        
          </tr> 
          <tr>
                <td><b>Bid Submission Start Date:</b></td>
                <td><?php echo $res->tc_bidsubstartdate ; ?></td>
                <td><b>Time:</b><?php echo $res->tc_bidsubstartdatet ; ?></td>
         
       
             
          
               
                <td><b>Bid Submission End Date:</b></td>
                <td><?php echo $res->tc_bidsubenddate ; ?></td>
                <td><b>Time:</b><?php echo $res->tc_bidsubenddatet ; ?></td>
          </tr> 
          <tr>
                <td><b>Bid Opening  Date:</b></td>
                <td><?php echo $res->tc_bidopeningdate ; ?></td>
                <td><b>Time:</b><?php echo $res->tc_bidopeningdatet ; ?></td>
         
       
             
          
               
                <td colspan="3"></td>
                
          </tr> 
       
       </table>
       
         <?php  } ?>
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
		        foreach($tbosresult as $s){   
             ?>
      
          
            <tr>
                
                <td><?php echo $s->tbos_bono ; ?></td>
                <td><?php echo $s->tbos_boname ; ?></td>
                <td><?php echo $s->tbos_bodesig ; ?> </td>
                <td><?php echo $s->tbos_boemail ; ?></td>
            </tr> 
            <?php  } ?>
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
		        foreach($tudresult as $f){   
             ?>
      
          
            <tr>
                
                <td><?php echo $f->tud_filename ; ?></td>
                <td><?php echo $f->tud_desc ; ?></td>
                <td><?php echo $f->tud_filesize ; ?> </td>
                <td><?php echo $f->tud_filetype ; ?></td>
            </tr> 
            <?php  } ?>
 </table>

 <br> <br>  
       
        <table class="TFtable">
            <tr> <td colspan="4">
               <h2 class="title">NIT Document Details</h2>
                  
                 </td>           
            </tr>
            
            <tr>
                <td><b>File Name:</b><?php echo $res->tc_nitdocfilename ; ?> </td>
                 
       
                <td><b>File Size:</b><?php $a=$res->tc_nitdocfilesize/1024 ; 
                
                echo round($a,2) ?>KB
                
               </td>
               
                <td><b>File Type:</b><?php echo $res->tc_nitdoctype ; ?></td>
              <!--  <td><?php echo anchor('tender/tender_re/' . $res->tc_id , "Re-upload", array('title' => 'change' , 'class' => 'red-link')); 	
                   ?></td> -->
              
              <td>
              <button onclick="location.href='<?php echo base_url();?>uploads/PICO/tender/<?php echo $res->tc_refno.'/'. $res->tc_id.'/nit.'.$f->tud_filetype?>'">View</button></td>
                 
          </tr> 
     
       </table>
              
 
              
              

</div>
</div>
</body>
<br>
<br>
<p>&nbsp;</p>
  <div align="center"> <?php $this->load->view('template/footer');?>
</html>
