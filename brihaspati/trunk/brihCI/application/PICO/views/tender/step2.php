<!DOCTYPE html>
<html>
<title>Work|Item|Details</title>

 <head>
   <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
  <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
     <?php $this->load->view('template/header'); ?>
   
 </head>    
 <body> 



     <table width="100%">
            <tr><td>
                <?php //echo anchor('', "View Detail ", array('title' => 'Add Detail' ,'class' =>'top_parent'));?>
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


<script>
               $(document).ready(function(){
                            $("#offpayrowl").hide();
    		              
    		      $('#prebid').on('change',function(){
                        var pt= $('#prebid').val();
                        if(pt == 'yes'){
                            $("#offpayrowl").show();}
                        else{
                            $("#offpayrowl").hide();}
               });
               
               $('#prebidd').on('change',function(){
                        var pt= $('#prebidd').val();
                        if(pt == 'no'){
                        	 $("#offpayrowl").hide(); }
                        else{
                        	 $("#offpayrowl").show(); }
               });               
               });

</script>





<form action="<?php echo site_url('tender/tenderwork/');?>" method="POST" class="form-inline"> 


<h2 class="title">Work Item Details</h2>
<p class="subtitle">Step 2</p>

          <table class="TFtable">
            <tr>
                <td><label for="wid_wit" class="control-label">Work Item Title.:</label></td>
                <td><input type="text" name="wid_wit"  class="form-control" style="width:300px ;" /><br></td>
                <td>
                    <?php echo form_error('wid_wit')?>
                </td>
                 <td><label for="wid_wd" class="control-label">Work Description.:</label></td>
                <td><input type="text" name="wid_wd"  class="form-control" style="width:300px ;" /><br></td>
                <td>
                    <?php echo form_error('wid_wd')?>
                </td>
             </tr>
            
            
          <tr>
              <td><label for="wid_pd" class="control-label">Prequal  Details.:</label></td>
                <td><input type="text" name="wid_pd"  class="form-control" style="width:300px ;" /><br></td>
                <td>
                    <?php echo form_error('wid_pd')?>
                </td>
         
                <td><label for="wid_pc" class="control-label">Product Category:</label></td>
                <td>
                <select  name="wid_pc"  style="width:317px ;" class="my_dropdown" >
				    <option selected hidden value="">--option--</option>
				    <option value="civil works">	         civil works          </option>
				    <option value="electrical works">     electrical works		</option>
				    <option value="fleet management">		fleet management 		</option>
				    <option value="computer systems">		computer systems		</option>
			
				    </select>
                
                
                
                
                
                <br></td>
                <td>
                    <?php echo form_error('wid_pc')?>
                </td>
                
          </tr>
          <tr>
               
                <td><label for="wid_psc" class="control-label">Product Sub Category.:</label></td>
                <td><input type="text" name="wid_psc"  class="form-control" style="width:300px ;" /><br></td>
                <td>
                    <?php echo form_error('wid_psc')?>
           
               
                <td><label for="wid_ct" class="control-label">Contract Type:</label></td>
                <td>
                    <select name="wid_ct"  style="width:317px ;" >
				    <option selected hidden value="">--option--</option>
				    <option value="tender">Tender</option>
				    <option value="empanelment">Empanelment</option>
				 
				    </select>
                
                
                <br></td>
                <td>
                    <?php echo form_error('wid_ct')?>
                </td>
           </tr>
           <tr>
         
                <td><label for="wid_tv" class="control-label">Tender Value :</label></td>
                <td>   
                 <select name="wid_tv"  style="width:317px ;">
				    <option selected hidden value="">--option--</option>
				    <option value="inr">INR</option>
				    <option value="us">US</option>
				    <option value="eur">EUR</option>
				
				    </select>
                <br></td>
                <td>
                    <?php echo form_error('wid_tv')?>
                </td>
         
        
                <td><label for="wid_bvd" class="control-label">Bid Validity Days:</label></td>
                <td>    
                <select  name="wid_bvd"  style="width:317px ;">
				    <option selected disabled value="">--option--</option>
				    <option value="120">120</option>
				    <option value="90">90</option>
				    <option value="60">60</option>
				    <option value="30">30</option>
				    </select>
				    
                
                
                <br></td>
                <td>
                    <?php echo form_error('wid_bvd')?>
                </td>
   
             </tr>
             <tr>
               <td ><label for="wid_cpm" class="control-label">Completion Period In Months :</label></td>
                <td >
                 <input type="text" name="wid_cpm"  class="form-control" style="width:300px ;" />
                 
                
                <br></td>
               
               
                <td>
                    <?php echo form_error('wid_cpm')?>
                </td>
         
       
                <td><label for="wid_l" class="control-label">Location.:</label></td>
                <td><input type="text" name="wid_l"  class="form-control" style="width:300px ;" /><br></td>
                <td>
                    <?php echo form_error('wid_l')?>
                </td>
               </tr>
               <tr> 
                
                 <td><label for="wid_p" class="control-label"> Pincode .:</label></td>
                <td><input type="text" name="wid_p"  class="form-control" style="width:300px ;" /><br></td>
                <td>
                    <?php echo form_error('wid_p')?>
                </td>
            
        
                <td><label for="wid_pbm" class="control-label">Pre Bid Meeting :</label></td>
                <td><input id="prebid" type="radio" name="wid_pbm" value="yes" >YES
                     <input id="prebidd" type="radio" name="wid_pbm" value="no" >NO
                     <br></td>
                <td>
                    <?php echo form_error('wid_pbm')?>
                </td>
        </tr>
        <tr></tr>
        <tr id="offpayrowl">

                <td id="lone"><label for="wid_pbmp" class="control-label">Pre Bid Meeting Place:</label></td>
                <td><input type="text" name="wid_pbmp"  class="form-control" style="width:300px ;" />
                <br></td>
                <td>
                    <?php echo form_error('wid_pbmp')?>
                </td>
                 <td><label for="wid_pbma" class="control-label">Pre Bid Meting Address:</label></td>
                <td><input type="text" name="wid_pbma"  class="form-control" style="width:300px ;" />
                <br></td>
                <td>
                    <?php echo form_error('wid_pbma')?>
                </td>
</tr>
        
        
        
        
        <tr>
                <td><label for="wid_tc" class="control-label">Tender Class:</label></td>
               
               <td> <select name="wid_tc"  style="width:317px ;">
				    <option selected hidden value="">--option--</option>
				    <option value="a">A</option>
				    <option value="b">B</option>
				    <option value="c">C</option>
				    <option value="d">D</option>
				     <option value="e">E</option>
				    <option value="i">I</option>
				    <option value="ii">II</option>
				    <option value="iii">III</option>
				     <option value="iv">IV</option>
				    <option value="v">V</option>
				    <option value="other">OTHER</option>
				   
				    </select>
				     
				     <br></td>
                <td>
                    <?php echo form_error('wid_tc')?>
                </td>
           
                 <td><label for="wid_tsc" class="control-label">Tender Sub-class:</label></td>
                <td><input type="text" name="wid_tsc"  class="form-control" style="width:300px ;" />
                <br></td>
                <td>
                    <?php echo form_error('wid_tsc')?>
                </td>
           
           
           
           
           
            </tr>
            <tr>

 <td><label for="wid_io" class="control-label">Inviting Officer :</label></td>
                <td><input type="text" name="wid_io"  class="form-control" style="width:300px ;" />
                <br></td>
                <td>
                    <?php echo form_error('wid_io')?>
                </td>
                 <td><label for="wid_ioa" class="control-label">Inviting Officer Address  :</label></td>
                <td><input type="text" name="wid_ioa"  class="form-control" style="width:300px ;" />
                <br></td>
                <td>
                    <?php echo form_error('wid_ioa')?>
                </td>
</tr>


 <tr>

 <td><label for="wid_iop" class="control-label">Inviting Officer Phone:</label></td>
                <td><input type="text" name="wid_iop"  class="form-control" style="width:300px ;" />
                <br></td>
                <td>
                  <?php echo form_error('wid_iop')?> 
                </td>
                 <td><label for="wid_ioe" class="control-label">Inviting Officer Email:</label></td>
                <td><input type="text" name="wid_ioe"  class="form-control" style="width:300px ;" />
                <br></td>
                <td>
                    <?php echo form_error('wid_ioe')?>
                </td>
</tr>
            <tr>
                   
                     <td><label for="wid_bop" class="control-label">BID Opening Place :</label></td>
                <td><input type="text" name="wid_bop"  class="form-control" style="width:300px ;" />
                <br></td>
                <td colspan="4">
                    <?php echo form_error('wid_bop')?>
                </td>         
            
            </tr>
        


      <tr>
               
              
               <!--  <td>
                   
                
                </td>  <td></td> <td></td>  <td></td> --><td colspan="6">
          
            
                <button name="wd" >Next</button>
                <button name="reset">Clear</button>
                <input type="hidden" name="tid" value="<?php echo $tid ;?>" >               
         
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
	
	

