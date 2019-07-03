<?php defined('BASEPATH') OR exit('No direct script access allowed');
 /**
 * @name: Type of Store Form
 * @author: Nagendra Kumar Singh (nksinghiitk@gmail.com)
 * @author: Shivam Kumar Singh  (shivam.iitk1@gmail.com)
 */
?>

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

 		 <table class="TFtable">
 		 	<!-- <caption>Type of Store</caption> -->
 		 	<!-- <thead>
 		 		<tr>
 		 			<th>Type of Store</th>
 		 			<th>Store Description<th>

 		 		</th>
 		 		</tr>	
 		 	</thead> -->
 		 	<tbody>
 		 		<tr>
                    <td><label for="mt_name" class="control-label">Type of Store: </label></td>
 		 			<td >
 		 				<select name="mt_name">
 		 				<option name="" disabled selected>----Select Category----</option>
 		 				<option value="Non-Consumable Store(NCS)">Non-Consumable Store(NCS)</option>
 		 				<option value="Limited Time Asset Stores(LTAS)">Limited Time Asset Stores(LTAS)</option>
 		 				<option value="Consumable Stores(CS)">Consumable Stores(CS)</option>	
 		 				</select>

 		 			</td>
                </tr>
                  
                <tr>
                    <td><label for="mt_desc" class="control-label">Store Description : </label></td>  	
 		 			<td>
                        <textarea name="mt_desc" style="width:500px;height:100px;" placeholder="Give Description..." ></textarea>
 		 				<!-- <input type="text" name="mt_desc" placeholder="Give Description..." size="60">  -->
 		 			</td>	
 		 		</tr>
 		 			
 		 		<tr>
                    <td></td>
 		 			<td>
 				 		<button name="submitstore">Submit Details</button>
 				 	<td> 
 		 		</tr>
 		 		
 		 	</tbody>
 		 		 	
 		 </table>	
 	</form>





</body>
<p>&nbsp;</p>
    <div align=left> <?php $this->load->view('template/footer');?></div>
</html>
