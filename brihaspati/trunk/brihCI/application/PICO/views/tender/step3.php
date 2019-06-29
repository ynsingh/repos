<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Fee|Details</title>

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

      <form action="<?php echo site_url('tender/tenderfee/');?>" method="POST" class="form-inline">


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
		   <td><label class="control-label" for="fd_a">if EMD fee is fixed
                                                 EMD Amount: </label></td>
         <td> <input  name="fd_a" class="form-control" type="text" maxlength="255" value=""/><br></td> 
           <td>
                    <?php echo form_error('fd_a')?>
                </td>

        </tr>
        <tr>
		  <td><label class="control-label" for="fd_p">if EMD fee is percentage
                                                    EMD Percentage(%): </label></td>
        <td><input  name="fd_p" class="form-control" type="text" maxlength="255" value=""/> <br></td>
        
          <td>
                    <?php echo form_error('fd_p')?>
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
	             <button name="fd">Next</button>
	             <button name="reset">Clear</button>
	             <input type="hidden" name="tid" value="<?php echo $tid ;?>">
               </td>
               <td> 
 		 	    </td>
 	
 		 	   </tr>
 		 
 		 	
 		 		
 		 	

</table>


</form>
 <p><br></p>
    
    </body>
<p>&nbsp;</p>
    <div align="center"> <?php $this->load->view('template/footer');?>
    </html>
	
	

