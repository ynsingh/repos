<?php defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name: Purchase Committee Rules
 * @author: Shivam Kumar Singh  (shivam.iitk1@gmail.com)
 * @author: Nagendra Kumar Singh (nksinghiitk@gmail.com)
 */

?>
<html>
<title>Purchase Committee Rule| Form</title>
 <head>    
        <?php $this->load->view('template/header'); ?>

        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
    <table width="100%">
            <tr><td>
                 <?php 
                   
                    echo anchor('picosetup/purchasecommitteedetails/', "Go Back", array('title' => 'Purchase Committee Details','class' =>'top_parent'));
                
                  ?>
                 </td>
                 
           </tr> 
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
</head>
     
<body>

		<form action="<?php echo site_url('picosetup/insertpurchasecommitteerule');?>" method="POST" class="form-inline">
			<table class="TFtable">
                <tr>
                    <td><label for="pcfr_purchasethrough" class="control-label">Purchase Through: </label></td>
                    <td>
                    <select name="pcfr_purchasethrough" style="width: 73%;align: center;background-color:white;">
                        <option value="" disabled selected>---Option---</option>
                        <option value="Purchase Committee">Purchase Committee</option>
                        <option value="GeM">GeM</option>
                        <option value="Import">Import</option>
                    </select>
                    </td>
                    <td>
                    <i>Select from Dropdown</i>  
                    </td>
                   
                </tr>

			    <tr>
					<td><label for="pcfr_estpurchaseprice" class="control-label">Estimated Purchase Price:</label></td>
                	<td>
                	<input type="text" name="pcfr_estpurchaseprice"  class="form-control" size="30" placeholder="Estimated Purchase Price"/><br>
                	</td>
                	<td>
                	  Example: Above 2 Lakh  
                	</td>
                </tr>

                <tr>
					<td><label for="pcfr_rep1" class="control-label">1st Representative:<font color='Red'>*</font></label></td>
                	<td>
                	<input type="text" name="pcfr_rep1"  class="form-control" size="30" placeholder="1st Representative Designation" /><br>
                	</td>
                	<td>
                      Example: Director/ Financial Officer 
                    </td>
                </tr>

                <tr>
                    <td><label for="pcfr_rep2" class="control-label">2nd Representative: </label></td>
                    <td>
                    <input type="text" name="pcfr_rep2"  class="form-control" size="30" placeholder="2nd Representative Designation" /><br>
                    </td>
                    <td>
                      Example: Deputy Director  
                    </td>
                </tr>

                <tr>
                    <td><label for="pcfr_rep3" class="control-label">3rd Representative: </label></td>
                    <td>
                    <input type="text" name="pcfr_rep3"  class="form-control" size="30" placeholder="3rd Representative Designation" /><br>
                    </td>
                     <td>
                      Example: S&P Officer/ HOD   
                    </td>
                   
                </tr>
                

                <tr>
					<td><label for="pcfr_reference" class="control-label">Reference:</label></td>
                	<td>
                	<input type="text" name="pcfr_reference"  class="form-control" size="30" placeholder="Reference No" /><br>
                	</td>

                    <td>
                       Example: As per ABCD (4.5.2)
                    </td>
                </tr>
                
                <tr>
					<td><label for="pcfr_appauth" class="control-label">Approving Authority: <font color='Red'>*</font> </label></td>
                	<td>
                	<input type="text" name="pcfr_appauth"  class="form-control" size="30"  placeholder="Approving Authority" /><br>
                	</td>
                	
                	<td>
                	   Example: Finance Officer, Deputy Director
                	</td>
                </tr>
                
                <tr>
                <td></td>
                <td colspan="2">
                <button name="committee_rule">Submit Form</button>
                </td>
           		</tr>

			</table>
		</form>

</body>
<p>&nbsp;</p>
    <div align=left> <?php $this->load->view('template/footer');?></div>
</html>