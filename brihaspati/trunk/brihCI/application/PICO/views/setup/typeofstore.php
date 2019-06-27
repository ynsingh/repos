<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
 


 <!--  @name typeofstore.php 
  @author: Shivam Kumar Singh(shivam.iitk1@gmail.com) -->



<html>
<title>Type of Store | Form</title>
 <head>    
        <?php $this->load->view('template/header'); ?>

        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
</head>

<table width="100%"> 
            <tr><td>   
              <div align="left">
                    <?php echo validation_errors('<div  class="isa_warning">','</div>');?>
                    <?php echo form_error('<div class="isa_error">','</div>');?></div>

                    <?php if(isset($_SESSION['success'])){?>
                        <div class="isa_success"><?php echo $_SESSION['success'];?></div>

                    <?php
                    };
                    ?>
                </div> </br> 
        </td></tr>  
        </table>
        
<body>

 	<form  action="<?php echo site_url('picosetup/insertstore');?>" method="POST" class="form-inline">
            <?php echo form_error('store')?>
 		 <table>
 		 	<!-- <caption>Type of Store</caption> -->
 		 	<thead>
 		 		<tr>
 		 			<th>Type of Store</th>
 		 			<th>Store Description<th>

 		 		</th>
 		 		</tr>	
 		 	</thead>
 		 	<tbody>
 		 		<tr>
 		 			<td >
 		 				<select name="store">
 		 				<option name="select" value="disabled" selected="selected" disabled selected>----Select----</option>
 		 				<option value="Non-Consumable Store(NCS)">Non-Consumable Store(NCS)</option>
 		 				<option value="Limited Time Asset Stores(LTAS)">Limited Time Asset Stores(LTAS)</option>
 		 				<option value="Consumable Stores(CS)">Consumable Stores(CS)</option>	
 		 				</select>

 		 			</td>
                  	
 		 			<td>
 		 				<input type="text" name="mt_desc" placeholder="Give Description..." size="60"> 
 		 			</td>
 		 			
 		 		</tr>
 		 		<br>
 		 		<br>
 		 		<br>
 		 		<tr>	
 		 		<tr>
 		 			<td>
 				 		<button name="submitstore">Submit</button>
 				 	<td> 
 		 		</tr>
 		 		
 		 	</tbody>
 		 		 	
 		 </table>	
 	</form>





</body>
<p>&nbsp;</p>
    <div align=left> <?php $this->load->view('template/footer');?></div>
</html>
