<?php defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name: Item Type Form
 * @author: Nagendra Kumar Singh (nksinghiitk@gmail.com)
 * @author: Shivam Kumar Singh  (shivam.iitk1@gmail.com)
 */

?>
<html>
<title>Item List | Form</title>
 <head>    
        <?php $this->load->view('template/header'); ?>

        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
       
</head>

<table width="100%">
            <tr><td>
                <?php
                    echo anchor('picosetup/itemtypedetails','View Item Details', array('title' =>'Item List'));
                ?>
                 <?php
                   echo "<td align=\"center\" width=\"34%\">";
                   echo "</td>";
                   echo "<td align=\"right\" width=\"33%\">";
                   $help_uri = site_url()."/help/helpdoc#ViewRoleDetail";
                   echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                   echo "</td>";
                 ?>
            </td></tr> 
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

		<form action="<?php echo site_url('picosetup/insertitemtype');?>" method="POST" class="form-inline">
			<table class="">
			    <tr>
					<td><label for="item_id" class="control-label">Item ID :<font color='Red'>*</font></label></td>
                	<td>
                	<input type="text" name="item_id"  class="form-control" size="30" placeholder="Item ID" /><br>
                	</td>
                	<td>
                    <?php echo form_error()?>
                	</td>
                	<td>
                	  Example:
                	</td>
                </tr>

                <tr>
					<td><label for="item_mtid" class="control-label">Material ID :<font color='Red'>*</font></label></td>
                	<td>
                	<input type="text" name="item_mtid"  class="form-control" size="30" placeholder="Material ID" /><br>
                	</td>
                	<td>
                    <?php echo form_error()?>
                	</td>
                	<td>
                	   Example: 
                	</td>
                </tr>
                <tr>
					<td><label for="item_name" class="control-label">Item Name :<font color='Red'>*</font></label></td>
                	<td>
                	<input type="text" name="item_name"  class="form-control" size="30" placeholder="Item Name" /><br>
                	</td>
                	<td>
                    <?php echo form_error()?>
                	</td>
                	<td>
                	   Example: 
                	</td>
                </tr>
                <tr>
                    <td><label for="item_price" class="control-label">Item Price :<font color='Red'>*</font></label></td>
                    <td>
                    <input type="text" name="item_price"  class="form-control" size="30" placeholder="Item Price" /><br>
                    </td>
                    <td>
                    <?php echo form_error()?>
                    </td>
                    <td>
                       Example: 
                    </td>
                </tr>
                <tr>
					<td><label for="item_stock" class="control-label">Item Stock: <font color='Red'>*</font> </label></td>
                	<td>
                	<input type="text" name="item_stock"  class="form-control" size="30"  placeholder="Item Stock" /><br>
                	</td>
                	<td>
                    <?php echo form_error()?>
                	</td>
                	<td>
                	   Example: 
                	</td>
                </tr>
                <tr>
                </tr>
                <tr>
                <td></td>
                <td>
                <button name="item_type">Submit Form</button>
                </td>
           		</tr>

			</table>
		</form>

</body>
<p>&nbsp;</p>
    <div align=left> <?php $this->load->view('template/footer');?></div>
</html>