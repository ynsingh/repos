<!DOCTYPE html>
<html>
<title>Fee|Details</title>

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
        

<script>
               $(document).ready(function(){
               //for fixed and percentage j query	
                            $("#offpayrowl").hide();
    		                   $("#offpayrow").hide();
    		                    $("#off").hide();
    		      $('#prebid').on('change',function(){
                        var pt= $('#prebid').val();
                        if(pt == 'fixed'){
                            $("#offpayrowl").show();
                            $("#offpayrow").hide(); 
                            }
                        else{
                           $("#offpayrowl").hide();
                            $("#offpayrow").show()
                            }
               });
               
               $('#prebidd').on('change',function(){
                        var pt= $('#prebidd').val();
                        if(pt == 'percentage'){
                        	 $("#offpayrow").show(); 
                        	 $("#offpayrowl").hide(); 
                        	 }
                        else{
                        	 $("#offpayrow").hide(); 
                        	  $("#offpayrowl").show();
                        	 }
               }); 
               
               
               //for full,partial,none
               				
               
                $('#a').on('change',function(){
                        var pt= $('#a').val();
                        if(pt == 'full'){
                          
                            $("#off").hide(); 
                            }
                        else{
                           $("#off").hide();
                          
                            }
               });
               
               $('#b').on('change',function(){
                        var pt= $('#b').val();
                        if(pt == 'partial'){
                        	 $("#off").show(); 
                        	
                        	 }
                        else{
                        	 $("#off").hide(); 
                        	
                        	 }
                });              
                        	 
               $('#c').on('change',function(){
                        var pt= $('#c').val();
                        if(pt == 'none'){
                        	 $("#off").hide(); 
                        	
                        	 }
                        else{
                        	 $("#off").hide(); 
                        	
                        	 }         	  
               }); 
               
                                  
               });

</script>

        
        
        
        

 <form action="<?php echo site_url('tender/tenderfee/');?>" method="POST" class="form-inline">


<h2 class="title">FEE Details</h2>
<p class="subtitle">Step 3</p>

         <table class="TFtable">
            <tr>
            <td><label class="control-label" for="fd_tf">Tender Fee :</label></td>
            <td><input name="fd_tf" class="form-control" type="text" maxlength="255" value=""/> <br></td>
		          <td>
                    <?php echo form_error('fd_tf')?>
                </td>
		     </tr>
		     <tr>  
		     <td> <label class="control-label" for="fd_tfpt">Tender Fee Payable To :</label></td>
		     <td>  <input  name="fd_tfpt" class="form-control" type="text" maxlength="255" value=""/> <br></td>
		     
		     
		       <td>
                    <?php echo form_error('fd_tfpt')?>
                </td>
           </tr>
           
           <tr>
		    <td> <label class="control-label" for="fd_tfpa">Tender Fee Payable At :</label></td>
	       <td> <input  name="fd_tfpa" class="form-control" type="text" maxlength="255" value=""/> <br></td>
	       
	         <td>
                    <?php echo form_error('fd_tfpa')?>
                </td>
		    </tr>
		    
		    <tr>
          <td> <label class="control-label" for="fd_pf">Processing Fee :</label></td>
		    <td> <input  name="fd_pf" class="form-control" type="text" maxlength="255" value=""/> <br></td>
		    
		      <td>
                    <?php echo form_error('fd_pf')?>
                </td>
		   </tr>
		   
		   <tr>
         <td> <label class="control-label" for="fd_s">Surcharges :</label></td>
         <td> <input  name="fd_s" class="form-control" type="text" maxlength="255" value=""/> <br></td>
           <td>
                    <?php echo form_error('fd_s')?>
                </td>
		 
		  </tr>
		  <tr>
		  <td> <label class="control-label" for="fd_oc">Other charges :</label></td>
        <td> <input  name="fd_oc" class="form-control" type="text" maxlength="255" value=""/> <br></td>
        
          <td>
                    <?php echo form_error('fd_oc')?>
                </td>
		    </tr>
		    
		    
		    <tr>
	            <td><label for="fd_ef" class="control-label">EMD fee :</label></td>
                <td>
                		<input id="prebid" type="radio" name="fd_ef" value="fixed" >Fixed
                     <input id="prebidd" type="radio" name="fd_ef" value="percentage" >Percentage
                     
                <br></td>
		    <td>
                    <?php echo form_error('fd_ef')?>
                </td>
		  
		   </tr>
		   
		   <tr></tr>
		   
		   <tr id="offpayrowl">
		   <td><label class="control-label" for="fd_a">
                                                 EMD Amount :</label></td>
         <td> <input  name="fd_a" class="form-control" type="text" maxlength="255" value=""/><br></td> 
           <td>
                    <?php echo form_error('fd_a')?>
                </td>
         
        </tr>
        <tr></tr>
        <tr id="offpayrow" >
		  <td><label class="control-label" for="fd_p">
                                                    EMD Percentage(%) :</label></td>
        <td><input  name="fd_p" class="form-control" type="text" maxlength="255" value=""/> <br></td>
        
          <td>
                    <?php echo form_error('fd_p')?>
                </td>
	     </tr>
	     
	     
	     <tr>
	     <td><label for="fd_eea" class="control-label">EMD Exemption Allowed :</label></td>
        <td>
                		<input id="a" type="radio" name="fd_eea" value="full" >Full
                     <input id="b" type="radio" name="fd_eea" value="partial" >Partial
                     <input id="c" type="radio" name="fd_eea" value="none" >None
                <br></td>
                
                  <td>
                    <?php echo form_error('fd_eea')?>
                </td>
       </tr>
		   <tr></tr>
		 <tr id="off">
		<td><label class="control-label" for="fd_eep">
                                                     Percentage % :</label></td>
      <td> <input  name="fd_eep" class="form-control" type="text" maxlength="255" value=""/><br></td> 
      
                 <td>
                    <?php echo form_error('fd_eep')?>
                </td>         
      
	   </tr>
			  
       <tr>
				  <td colspan="3">
	             <button name="fd">Next</button>
	             <button name="reset">Clear</button>
	             <input type="hidden" name="tid" value="<?php echo $tid ;?>">
               </td>
           
 		 	   </tr>
 		 
 		 	
 		 		
 		 	

</table>


</form>
 <p><br></p>
    
    </body>
<p>&nbsp;</p>
    <div align="center"> <?php $this->load->view('template/footer');?>
    </html>
	
	

