<!DOCTYPE html>
<html>
<head>
<title>Tender input form</title>
<!-- Including CSS File Here -->
<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/abhay.css">
<!-- Including JS File Here -->
 <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-abhay.js" ></script>
  <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
     <?php $this->load->view('template/header');
     
      ?>
     
     
     
 

</head>
<body>


     <table width="100%">
            <tr><td>
                <?php // echo anchor('pico', "View Detail ", array('title' => 'Add Detail' ,'class' =>'top_parent'));?>
                <?php
                 echo "<td align=\"right\">";
                 $help_uri = site_url()."/help/helpdoc#Role";
		 echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
		echo "</td>"
                 ?>
                <div  style="width:100%;">
                <?php echo validation_errors('<div class="isa_warning">','');?>
                <?php if(isset($_SESSION['success'])){?>
                <div class="isa_success"><?php echo $_SESSION['success'];?>
                <?php
                };
                ?>
                <?php if(isset($_SESSION['err_message'])){?>
                    <div class="isa_error"><?php echo $_SESSION['err_message'];?>
                <?php
                };
               ?>
              
             </td></tr>
        </table>


<fieldset id="first">
<form action="<?php echo site_url('tender/tenderbasic');?>" method="POST" class="form-inline">


<h2 class="title">Basic Details</h2>
<p class="subtitle">Step 1</p>
  
          <table class="TFtable">
            <tr>
                <td><label for="bd_trn" class="control-label">Tender Reference No:</label></td>
                <td><input type="text" name="bd_trn"  class="form-control" style="width:300 ;" /><br></td>
                <td>
                    <?php // echo form_error('bd_trn')?>
                </td>
             
         
                <td><label for="bd_tt" class="control-label">Tender type:</label></td>
                <td>
                <select name="bd_tt"  style="width:300 ;">
				    <option selected hidden value="">--option--</option>
				    <option value="open">		  Open  		</option>
				    <option value="limited">		Limited 		</option>
				    <option value="EOI">		  EOI   		</option>
				    <option value="auction">		Auction 		</option>
				    <option value="single">		 Single 		</option>
				    </select>
                
                
                
                
                
                <br></td>
                <td>
                    <?php //echo form_error('bd_tt')?>
                </td>
               
           </tr>
               
                <td><label for="bd_foc" class="control-label">Form of contract:</label></td>
                <td>
                    <select name="bd_foc"  style="width:300 ;" >
				    <option selected hidden value="">--option--</option>
				    <option value="work contract">Work contract</option>
				    <option value="auction">Auction</option>
				    <option value="service contract">Service contract</option>
				    <option value="buy">Buy</option>
				    <option value="empanelment">Empanelment</option>
				    <option value="sell">Sell</option>
				    <option value="buy & service">Buy & Service</option>
				    </select>
                
                
                <br></td>
                <td>
                    <?php //echo //form_error('bd_foc')?>
                </td>
             
           
         
                <td><label for="bd_noc" class="control-label">No. of Covers:</label></td>
                <td>   
                 <select name="bd_noc"  style="width:300 ;">
				    <option selected hidden value="">--option--</option>
				    <option value="1">1</option>
				    <option value="2">2</option>
				    <option value="3">3</option>
				    <option value="4">4</option>
				    </select>
                <br></td>
                <td>
                    <?php //echo //form_error('bd_noc')?>
                </td>
         </tr> 
        
                <td><label for="bd_tc" class="control-label">Tender category:</label></td>
                <td>    
                <select name="bd_tc"  style="width:300 ;">
				    <option selected hidden value="">--option--</option>
				    <option value="goods">Goods</option>
				    <option value="works">Works</option>
				    <option value="services">Services</option>
				
				    </select>
				    
                
                
                <br></td>
                <td>
                    <?php //echo //form_error('bd_tc')?>
                </td>
   
             
             
               <td><label for="bd_ars" class="control-label">Allow Re-submission :</label></td>
                <td>
                		<input type="radio" name="bd_ars" value="yes" >YES
                     <input type="radio" name="bd_ars" value="no" >NO
                
                <br></td>
               
               
                <td>
                    <?php //echo //form_error('bd_ars')?>
                </td>
         
         </tr>
         <tr>
                <td><label for="bd_aw" class="control-label">Allow Withdrawal :</label></td>
                <td><input type="radio" name="bd_aw" value="yes" >YES
                     <input type="radio" name="bd_aw" value="no" >NO
                     <br></td>
                <td>
                    <?php //echo //form_error('bd_aw')?>
                </td>
        
                <td><label for="bd_aos" class="control-label">Allow Offline Submission :</label></td>
                <td> <input type="radio" name="bd_aos" value="yes" >YES
                     <input type="radio" name="bd_aos" value="no" >NO
                     <br></td>
                <td>
                    <?php //echo //form_error('bd_aos')?>
                </td>
            </tr>
            <tr>   
            <td><label for="bd_pm" class="control-label">Payment Mode:</label></td>
                <td>   
                 <select name="bd_pm"  style="width:300 ;">
				    <option selected hidden value="">--option--</option>
				    <option value="offline">offline </option>
				    <option value="online">online </option>
				  
				    </select>
                <br></td>
                <td>
                    <?php //echo form_error('bd_pm')?>
                </td>
            
           </tr>
           </table>
           
          <table class="TFtable">

	<thead>
 		 		<tr>
 		 			<th>No. of cover</th>
 		 			<th>Cover contents<th>

 		 		</th>
 		 		</tr>	
 		 	</thead>
 		 	<tbody>
 		 		<tr>
 		 			<td >
 		 				<select name="">
 		 				<option name="select" value="disabled" selected="selected" disabled selected>----Select----</option>
 		 				<option value="">single cover</option>
 		 				<option value="">two cover</option>
 		 				<option value="">three cover</option>	
 		 			   <option value="">four cover</option>	
 		 				</select>

 		 			</td>
                  	
 		 			<td>
 		 				<input type="text" name="ten_con_des" placeholder="Give Description..." size="95" style="float:right;"> 
 		 			</td>
 		 			
 		 		</tr>
 		 		<br>
 		 		<br>
 		 		<br>
 		 		<tr>	

 		 	</tbody>

</table>           
          <table class="TFtable"><td>
                    <button name="bd">Next</button>
                    <button name="reset">Clear</button>
                    <input name="bd" id="next_btn1" onclick="next_step1()" type="button"  value="Next" style="float:right;">
 		 			 
 			 <td>    
 			 </table>    
</form>           
</fieldset>



<fieldset id="second">
<form action="<?php echo site_url('tender/tenderwork');?>" method="POST" class="form-inline">


<h2 class="title">Work Item Details</h2>
<p class="subtitle">Step 2</p>

          <table class="TFtable">
            <tr>
                <td><label for="wid_wit" class="control-label">Work item tittle.:</label></td>
                <td><input type="text" name="wid_wit"  class="form-control" style="width:300 ;" /><br></td>
                <td>
                    <?php //echo form_error('wid_wit')?>
                </td>
                 <td><label for="wid_wd" class="control-label">Work description.:</label></td>
                <td><input type="text" name="wid_wd"  class="form-control" style="width:300 ;" /><br></td>
                <td>
                    <?php //echo form_error('wid_wd')?>
                </td>
             </tr>
            
            
          <tr>
              <td><label for="wid_pd" class="control-label">Prequal  Details.:</label></td>
                <td><input type="text" name="wid_pd"  class="form-control" style="width:300 ;" /><br></td>
                <td>
                    <?php //echo form_error('wid_pd')?>
                </td>
         
                <td><label for="wid_pc" class="control-label">Product Category:</label></td>
                <td>
                <select name="wid_pc"  style="width:300 ;">
				    <option selected hidden value="">--option--</option>
				    <option value="civil works">	         civil works          </option>
				    <option value="electrical works">     electrical works		</option>
				    <option value="fleet management">		fleet management 		</option>
				    <option value="computer systems">		computer systems		</option>
			
				    </select>
                
                
                
                
                
                <br></td>
                <td>
                    <?php //echo form_error('wid_pc')?>
                </td>
                
          </tr>
          <tr>
               
                <td><label for="wid_psc" class="control-label">Product sub category.:</label></td>
                <td><input type="text" name="wid_psc"  class="form-control" style="width:300 ;" /><br></td>
                <td>
                    <?php //echo form_error('wid_psc')?>
           
               
                <td><label for="wid_ct" class="control-label">Contract type:</label></td>
                <td>
                    <select name="wid_ct"  style="width:300 ;" >
				    <option selected hidden value="">--option--</option>
				    <option value="tender">Tender</option>
				    <option value="empanekment">Empanelment</option>
				 
				    </select>
                
                
                <br></td>
                <td>
                    <?php //echo form_error('wid_ct')?>
                </td>
           </tr>
           <tr>
         
                <td><label for="wid_tv" class="control-label">Tender value :</label></td>
                <td>   
                 <select name="wid_tv"  style="width:300 ;">
				    <option selected hidden value="">--option--</option>
				    <option value="inr">INR</option>
				    <option value="us">US</option>
				    <option value="eur">EUR</option>
				
				    </select>
                <br></td>
                <td>
                    <?php //echo form_error('wid_tv')?>
                </td>
         
        
                <td><label for="wid_bvd" class="control-label">Bid validity days:</label></td>
                <td>    
                <select name="wid_bvd"  style="width:300 ;">
				    <option selected hidden value="">--option--</option>
				    <option value="120">120</option>
				    <option value="90">90</option>
				    <option value="60">60</option>
				    <option value="30">30</option>
				    </select>
				    
                
                
                <br></td>
                <td>
                    <?php //echo form_error('wid_bvd')?>
                </td>
   
             </tr>
             <tr>
               <td><label for="wid_cpm" class="control-label">Completion period in months :</label></td>
                <td>
                 <input type="text" name="wid_cpm"  class="form-control" style="width:300 ;" />
                 
                
                <br></td>
               
               
                <td>
                    <?php //echo form_error('wid_cpm')?>
                </td>
         
       
                <td><label for="wid_l" class="control-label">Location.:</label></td>
                <td><input type="text" name="wid_l"  class="form-control" style="width:300 ;" /><br></td>
                <td>
                    <?php //echo form_error('wid_l')?>
                </td>
               </tr>
               <tr> 
                
                 <td><label for="wid_p" class="control-label"> Pincode .:</label></td>
                <td><input type="text" name="wid_p"  class="form-control" style="width:300 ;" /><br></td>
                <td>
                    <?php //echo form_error('wid_p')?>
                </td>
            
        
                <td><label for="wid_pbm" class="control-label">Pre bid meeting :</label></td>
                <td><input type="radio" name="wid_pbm" value="yes" >YES
                     <input type="radio" name="wid_pbm" value="no" >NO
                     <br></td>
                <td>
                    <?php //echo form_error('wid_pbm')?>
                </td>
        </tr>
        <tr>
                <td><label for="wid_bop" class="control-label">BID opening place :</label></td>
                <td><input type="text" name="wid_bop"  class="form-control" style="width:300 ;" />
                <br></td>
                <td>
                    <?php //echo form_error('wid_bop')?>
                </td>
               
                <td><label for="wid_tc" class="control-label">Tender class:</label></td>
               
               <td> <select name="wid_tc"  style="width:300 ;">
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
                    <?php //echo form_error('wid_tc')?>
                </td>
            </tr>
            <tr>

 <td><label for="wid_io" class="control-label">Inviting Officer :</label></td>
                <td><input type="text" name="wid_io"  class="form-control" style="width:300 ;" />
                <br></td>
                <td>
                    <?php //echo form_error('wid_io')?>
                </td>
                 <td><label for="wid_ioa" class="control-label">Inviting Officer Address  :</label></td>
                <td><input type="text" name="wid_ioa"  class="form-control" style="width:300 ;" />
                <br></td>
                <td>
                    <?php //echo form_error('wid_ioa')?>
                </td>
</tr>
      <tr>
               
              
                <td>
                   <input id="pre_btn1" onclick="prev_step1()" type="button" value="Previous">
                
                </td>  <td></td> <td></td>  <td></td><td>
          
             <input id="next_btn2" name="next" onclick="next_step2()" type="button" value="Next">
                <button name="wd" >next</button>
                </td>
           </tr>
           </table>

</form>
</fieldset>




<fieldset id="third">
<form action="<?php echo site_url('picosetup/tenderfee');?>" method="POST" class="form-inline">


<h2 class="title">FEE Details</h2>
<p class="subtitle">Step 3</p>

         <table class="TFtable">
            <tr>
            <td><label class="control-label" for="fd_tf">Tender fee </label></td>
            <td><input name="fd_tf" class="form-control" type="text" maxlength="255" value=""/> <br></td>
		          <td>
                    <?php echo form_error('fd_tf')?>
                </td>
		     </tr>
		     <tr>  
		     <td> <label class="control-label" for="fd_tfpt">Tender fee payable to </label></td>
		     <td>  <input  name="fd_tfpt" class="form-control" type="text" maxlength="255" value=""/> <br></td>
		     
		     
		       <td>
                    <?php echo form_error('fd_tfpt')?>
                </td>
           </tr>
           
           <tr>
		    <td> <label class="control-label" for="fd_tfpa">Tender fee payable at </label></td>
	       <td> <input  name="fd_tfpa" class="form-control" type="text" maxlength="255" value=""/> <br></td>
	       
	         <td>
                    <?php echo form_error('fd_tfpa')?>
                </td>
		    </tr>
		    
		    <tr>
          <td> <label class="control-label" for="fd_pf">Processing fee </label></td>
		    <td> <input  name="fd_pf" class="form-control" type="text" maxlength="255" value=""/> <br></td>
		    
		      <td>
                    <?php echo form_error('fd_pf')?>
                </td>
		   </tr>
		   
		   <tr>
         <td> <label class="control-label" for="fd_s">Surcharges </label></td>
         <td> <input  name="fd_s" class="form-control" type="text" maxlength="255" value=""/> <br></td>
           <td>
                    <?php echo form_error('fd_s')?>
                </td>
		 
		  </tr>
		  <tr>
		  <td> <label class="control-label" for="fd_oc">Other charges </label></td>
        <td> <input  name="fd_oc" class="form-control" type="text" maxlength="255" value=""/> <br></td>
        
          <td>
                    <?php echo form_error('fd_oc')?>
                </td>
		    </tr>
		    
		    
		    <tr>
	            <td><label for="fd_ef" class="control-label">EMD fee:</label></td>
                <td>
                		<input type="radio" name="fd_ef" value="fixed" >Fixed
                     <input type="radio" name="fd_ef" value="percentage" >Percentage
                     
                <br></td>
		    <td>
                    <?php echo form_error('fd_ef')?>
                </td>
		  
		   </tr>
		   
		   
		   
		   <tr>
		   <td><label class="control-label" for="fd_ap">if EMD fee is fixed
                                                 EMD Amount: </label></td>
         <td> <input  name="fd_ap" class="form-control" type="text" maxlength="255" value=""/><br></td> 
           <td>
                    <?php echo form_error('fd_ap')?>
                </td>

        </tr>
        <tr>
		  <td><label class="control-label" for="fd_ap">if EMD fee is percentage
                                                    EMD Percentage(%): </label></td>
        <td><input  name="fd_ap" class="form-control" type="text" maxlength="255" value=""/> <br></td>
        
          <td>
                    <?php echo form_error('fd_ap')?>
                </td>
	     </tr>
	     
	     
	     <tr>
	     <td><label for="fd_eea" class="control-label">EMD Exemption allowed:</label></td>
        <td>
                		<input type="radio" name="fd_eea" value="full" >Full
                     <input type="radio" name="fd_eea" value="partial" >Partial
                     <input type="radio" name="fd_eea" value="none" >None
                <br></td>
                
                  <td>
                    <?php echo form_error('fd_eea')?>
                </td>
       </tr>
		   
		 <tr>
		<td><label class="control-label" for="fd_eep">if EMD Exemption Allowed is Partial,
                                                 EMD Exemption Percentage % </label></td>
      <td> <input  name="fd_eep" class="form-control" type="text" maxlength="255" value=""/><br></td> 
      
                 <td>
                    <?php echo form_error('fd_eep')?>
                </td>         
      
	   </tr>
			  
       <tr>
				  <td>
	               <input id="pre_btn2" onclick="prev_step2()" type="button" value="Previous">
               </td>
               <td> <input id="next_btn3" name="next" onclick="next_step3()" type="button" value="Next">
 		 	    </td>
 	
 		 	   </tr>
 		 
 		 	
 		 		
 		 	

</table>


</form>
</fieldset>


<fieldset id="fourth">
<form action="<?php echo site_url('picosetup/tendercritical');?>" method="POST" class="form-inline">


<h2 class="title">Critical Dates</h2>
<p class="subtitle">Step 4</p>

         <table class="TFtable">
            <tr>
                <td><label for="cd_pd" class="control-label">Publishing Date.:</label></td>
                <td><input type="date" name="cd_pd"  class="form-control" style="width:300 ;" /><br>
                    <input type="time" name="cd_pdt"  class="form-control" style="width:300 ;" /><br>
                
                
                </td>
                <td>
                    <?php echo form_error('cd_pd')?>
                    <?php echo form_error('cd_pdt')?>
                </td>
                
        
                <td><label for="cd_dssd" class="control-label">Document Sale Start Date.:</label></td>
                <td><input type="date" name="cd_dssd"  class="form-control" style="width:300 ;" /><br>
                    <input type="time" name="cd_dssdt"  class="form-control" style="width:300 ;" /><br>
                
                
                </td>
                <td>
                    <?php echo form_error('cd_dssd')?>
                    <?php echo form_error('cd_dssdt')?>
                </td>
                
           </tr>
            <tr>
                <td><label for="cd_dsed" class="control-label">Document Sale End Date.:</label></td>
                <td><input type="date" name="cd_dsed"  class="form-control" style="width:300 ;" /><br>
                    <input type="time" name="cd_dsedt"  class="form-control" style="width:300 ;" /><br>
                
                
                </td>
                <td>
                    <?php echo form_error('cd_dsed')?>
                    <?php echo form_error('cd_dsedt')?>
                </td>
                
         
                <td><label for="cd_scsd" class="control-label">Seek Clarification Start Date.:</label></td>
                <td><input type="date" name="cd_scsd"  class="form-control" style="width:300 ;" /><br>
                    <input type="time" name="cd_scsdt"  class="form-control" style="width:300 ;" /><br>
                
                
                </td>
                <td>
                    <?php echo form_error('cd_scsd')?>
                    <?php echo form_error('cd_scsdt')?>
                </td>
                
           </tr>
            <tr>
                <td><label for="cd_sced" class="control-label">Seek Clarification End Date.:</label></td>
                <td><input type="date" name="cd_sced"  class="form-control" style="width:300 ;" /><br>
                    <input type="time" name="cd_scedt"  class="form-control" style="width:300 ;" /><br>
                
                
                </td>
                <td>
                    <?php echo form_error('cd_sced')?>
                    <?php echo form_error('cd_scedt')?>
                </td>
          
                <td><label for="cd_pbmd" class="control-label">Pre Bid Meeting Date.:</label></td>
                <td><input type="date" name="cd_pbmd"  class="form-control" style="width:300 ;" /><br>
                    <input type="time" name="cd_pbmdt"  class="form-control" style="width:300 ;" /><br>
                
                
                </td>
                <td>
                    <?php echo form_error('cd_pbmd')?>
                    <?php echo form_error('cd_pbmdt')?>
                </td>
             </tr>   
         
                <td><label for="cd_bssd" class="control-label">Bid Submission Start Date.:</label></td>
                <td><input type="date" name="cd_bssd"  class="form-control" style="width:300 ;" /><br>
                    <input type="time" name="cd_bssdt"  class="form-control" style="width:300 ;" /><br>
                
                
                </td>
                <td>
                    <?php echo form_error('cd_bssd')?>
                    <?php echo form_error('cd_bssdt')?>
                </td>
                
          
                <td><label for="cd_bsed" class="control-label">Bid Submission End Date.:</label></td>
                <td><input type="date" name="cd_bsed"  class="form-control" style="width:300 ;" /><br>
                    <input type="time" name="cd_bsedt"  class="form-control" style="width:300 ;" /><br>
                
                
                </td>
                <td>
                    <?php echo form_error('cd_bsed')?>
                    <?php echo form_error('cd_bsedt')?>
                </td>
             </tr>
            <tr>    
         
                <td><label for="cd_bod" class="control-label">Bid Opening  Date.:</label></td>
                <td><input type="date" name="cd_bod"  class="form-control" style="width:300 ;" /><br>
                    <input type="time" name="cd_bodt"  class="form-control" style="width:300 ;" /><br>
                
                
                </td>
                <td>
                    <?php echo form_error('cd_bod')?>
                    <?php echo form_error('cd_bodt')?>
                </td>
                
         <td></td> <td></td>
       </tr>  <tr>
 		 			<td>
 				 	     <input id="pre_btn3" onclick="prev_step3()" type="button" value="Previous">
               <td></td> <td></td>  <td> <input id="next_btn4" name="next" onclick="next_step4()" type="button" value="Next">
 				 	<td> 
 		 		</tr>
 		 		
 		 	</tbody>

</table>           
           
</form>
</fieldset>


<fieldset id="fifth">
<form action="<?php echo site_url('picosetup/tenderupload');?>" method="POST" class="form-inline">


<h2 class="title">Upload</h2>
<p class="subtitle"><b>Step 5</b></p>
         <table>

	
	
	<div id="form_container">
	
		<h1><a>uploads</a></h1>
		<form id="form_69923" class="appnitro" enctype="multipart/form-data" method="post" action="">
					<div class="form_description">
			<h2>file upload</h2>
			<p></p>
								
		<ul >
			
		<li id="li_1" >
		<label class="description" for="element_1">Upload a File </label>
		
		<input id="element_1" name="element_1" class="element file" type="file"/> 
		  
		</li>		<li id="li_2" >
		<label class="description" for="element_2">Upload a File </label>
		
		<input id="element_2" name="element_2" class="element file" type="file"/> 
		</li>
			
	 <li class="buttons">
                   <input id="pre_btn4" onclick="prev_step4()" type="button" value="Previous">
                   <input class="submit_btn" onclick="validation(event)" type="submit" value="Submit">
	</li>
	</ul>
		
</table>




</form>
</fieldset>

</div>
</div>
</body>
<br>
<br>
<p>&nbsp;</p>
  <div align="center"> <?php $this->load->view('template/footer');?>
</html>