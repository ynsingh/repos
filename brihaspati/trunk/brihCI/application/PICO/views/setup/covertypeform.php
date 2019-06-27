<?php defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * @name: Financial Power Form
 * @author: Nagendra Kumar Singh (nksinghiitk@gmail.com)
 * @author: Shivam Kumar Singh  (shivam.iitk1@gmail.com)
 */

?>
<html>
<title>Cover Type | Form</title>
 <head>    
        <?php $this->load->view('template/header'); ?>

        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
       
</head>

<table width="100%"> 

            <tr><td>
                <?php
                    echo anchor('picosetup/displaycovertypedetails/','View Cover Details', array('title'=>'Cover Details'));
                ?>
                <?php
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

		<form action="<?php echo site_url('picosetup/insertcoverform');?>" method="POST" class="form-inline">
			<table class="">
			    <tr>
					<td><label for="ct_coverno" class="control-label">Cover Number:<font color='Red'>*</font></label></td>
                	<td>
                	<input type="text" name="ct_coverno"  class="form-control" size="30" placeholder="Cover Number" /><br>
                	</td>
                	<td>
                    <?php echo form_error()?>
                	</td>
                	<td>
                	  Example:
                	</td>
                </tr>

                <tr>
					<td><label for="ct_coverfixed" class="control-label">Fixed Cover :<font color='Red'>*</font></label></td>
                	<td>
                	<input type="text" name="ct_coverfixed"  class="form-control" size="30" placeholder="Fixed Cover " /><br>
                	</td>
                	<td>
                    <?php echo form_error()?>
                	</td>
                	<td>
                	   Example: 
                	</td>
                </tr>
                <tr>
					<td><label for="ct_coveroptional" class="control-label">Optional Cover : </label></td>
                	<td>
                	<input type="text" name="ct_coveroptional"  class="form-control" size="30" placeholder="Optional Cover " /><br>
                	</td>
                	<td>
                    <?php echo form_error()?>
                	</td>
                	<td>
                	   Example: 
                	</td>
                </tr>
                <tr>
					<td><label for="ct_desc" class="control-label">Cover Description: </label></td>
                	<td>
                	<input type="text" name="ct_desc"  class="form-control" size="30"  placeholder="Cover Description" /><br>
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
                <button name="cov_type">Submit Form</button>
                </td>
           		</tr>

			</table>
		</form>

</body>
<p>&nbsp;</p>
    <div align=left> <?php $this->load->view('template/footer');?></div>
</html>