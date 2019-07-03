<?php defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * @name: Financial Power Form
 * @author: Nagendra Kumar Singh (nksinghiitk@gmail.com)
 * @author: Shivam Kumar Singh  (shivam.iitk1@gmail.com)
 */

?>
<html>
<title>Financial Power | Form</title>
 <head>    
        <?php $this->load->view('template/header'); 
              

        ?>

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

		<form action="<?php echo site_url('picosetup/insertfpform');?>" method="POST" class="form-inline">
			<table class="TFtable">
			    <tr>
					<td><label for="fp_typeofpurch" class="control-label">Type of Purchase: </label></td>
                	<td>
                	<!-- <input type="text" name="fp_typeofpurch"  class="form-control" size="30" placeholder="Type of Purchase" /> -->
                    <select name="fp_typeofpurch" class="my_dropdown" style="width:63%;">
                        <option selected="" disabled="" value="" style="width: 100% ;">--------------Select Option--------------</option>
                        <?php
                           foreach ($material as $row) {      
                        ?>
                        <option value="<?php echo $row->mt_name ?>"><?php echo $row->mt_name ?></option>
                        <?php
                        }
                        ?>
                    </select>
                	</td>
                	<td>
                	   Example: Category of Purchase
                	</td>
                </tr>

                <tr>
					<td><label for="fp_subtypepurch" class="control-label">Sub Purchase Type: </label></td>
                	<td>
                	<input type="text" name="fp_subtypepurch"  class="form-control" size="30" placeholder="Sub Purchase Type" />
                	</td>
                	<td>
                	   Example: Sub category of Purchase
                	</td>
                </tr>
                <tr>
					<td><label for="fp_authority" class="control-label">Authority:<font color='Red'>*</font> </label></td>
                	<td>
                	
                  
                    <select name="authority" class="my_dropdown" style="width:63%;">
                        <option selected="" disabled="" value="" style="width: 100% ;">--------------Select Authorities--------------</option>
                        <?php
                           foreach ($authresult as $row) {      
                        ?>
                        <option value="<?php echo $row->name ?>"><?php echo $row->name ?></option>
                        <?php
                        }
                        ?>
                    </select>
                	</td>
                	<td>
                	   Example: Administrator, Head of Department etc.
                	</td>
                </tr>
                <tr>
					<td><label for="fp_limit" class="control-label">Financial Limit: </label></td>
                	<td>
                	<input type="text" name="fp_limit"  class="form-control" size="30" placeholder="Financial Limit" />
                	</td>
                	<td>
                	   Example: 50500.00
                	</td>
                </tr>
                <tr>
					<td><label for="fp_desc" class="control-label">Item Description: </label></td>
                	<td>
                	<input type="text" name="fp_desc"  class="form-control" size="30"  placeholder="Item Description" />
                	<td>
                	   Example: Describe the type of item
                	</td>
                </tr>
                
               <tr>
                <td></td>
                <td> <button name="fp_power">Submit Details</button></td>
               </tr>
               
               

            
			</table>
		</form>

</body>
<p>&nbsp;</p>
    <div align=left> <?php $this->load->view('template/footer');?></div>
</html>