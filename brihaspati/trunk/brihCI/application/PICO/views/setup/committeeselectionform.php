<?php defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * @name: Purchase Committee Selection Form
 * @author: Nagendra Kumar Singh (nksinghiitk@gmail.com)
 * @author: Shivam Kumar Singh  (shivam.iitk1@gmail.com)
 */

?>
<html>
<title>Committee Selection | Form</title>
 <head>    
        <?php $this->load->view('template/header'); ?>

        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
       
</head>

<table width="100%"> 

            <tr>
                <td>
                <?php
                    echo anchor('picosetup/displaycommitteeselection/','Go Back', array('title'=>'Cover Details'));
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
                    }
			?>
			<?php if(isset($_SESSION['err_message'])){?>
			<div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
		 <?php
                	    }
			?>

                </div> </br> 
        </td></tr>  
        </table>
        
<body>

		<form action="<?php echo site_url('picosetup/insertpurchselectioncomm');?>" method="POST" class="form-inline">
			<table class="TFtable">
                <tr>
                    <td><label for="pc_purchasethrough" class="control-label">Purchase Through:<font color='Red'>*</font></label></td>
                    <td>
                        <select name="pc_purchasethrough" style="width:47%;">
                            <option disabled selected>----Select Option----</option>
                            
                            <option value="Purchase Committee">Purchase Committee</option>
                            <option value="GeM">GeM</option>
                            <option value="Import">Import</option>
                        </select>
                            
                    </td> 
                </tr>

			    <tr>
					<td><label for="pc_purchpricelimit" class="control-label">Estimated Price:<font color='Red'>*</font></label></td>  
                	<td>
                        <select name="pc_purchpricelimit" class="my_dropdown" style="width:47%;">
                            <option selected="" disabled="">----Select Option----</option>
                            <?php foreach($result as $row)
                            {
                            ?>
                                <option value="<?php echo $row->pcfr_estpurchaseprice ?>"><?php echo $row->pcfr_estpurchaseprice ?></option>
                            <?php
                            }
                            ?>
                	</td>
                </tr>
                <tr>
                        <td><label for="pc_dept" class="control-label">Department:<font color='Red'>*</font></label></td>
                        <td>
                        <select name="pc_dept" class="my_dropdown" style="width:47%;">
                            <option selected="" disabled="">----Select Option----</option>
                            <?php foreach($dept as $row)
                            {
                            ?>
                                <option value="<?php echo $row->dept_name ?>"><?php echo $row->dept_name ?></option>
                            <?php
                            }
                            ?>
                        </td>
                </tr>

                <tr>
					<td><label for="pc_convener" class="control-label">Convener:<font color='Red'>*</font></label></td>
                	<td>
                	<input type="text" name="pc_convener"  class="form-control" size="38" placeholder="Convener" /><br>
                	</td>                	
                </tr>

                 <tr>
                    <td><label for="pc_rep1" class="control-label">1st Representative:<font color='Red'>*</font> </label></td>
                    <td>
                    <input type="text" name="pc_rep1"  class="form-control" size="38" placeholder="1st Representative" /><br>
                    </td>
                </tr>

                 <tr>
                     <td><label for="pc_rep2" class="control-label">2nd Representative: </label></td>
                    <td>
                    <input type="text" name="pc_rep2"  class="form-control" size="38" placeholder="2nd Representative" /><br>
                    </td> 
                </tr>

                <tr>
                     <td><label for="pc_rep3" class="control-label">3rd Representative: </label></td>
                    <td>
                    <input type="text" name="pc_rep3"  class="form-control" size="38" placeholder="3rd Representative" /><br>
                    </td> 
                </tr>

                 <tr>
                     <td><label for="pc_rep4" class="control-label">4th Representative: </label></td>
                    <td>
                    <input type="text" name="pc_rep4"  class="form-control" size="38" placeholder="4th Representative" /><br>
                    </td> 

                </tr>

                <tr>
                     <td><label for="pc_rep5" class="control-label">5th Representative: </label></td>
                    <td>
                    <input type="text" name="pc_rep5"  class="form-control" size="38" placeholder="5th Representative" /><br>
                    </td>                   
                </tr>

                <tr>
                    <td><label for="pc_appauth" class="control-label">Authority:<font color='Red'>*</font></label></td>
                    <td>
                    <select name="pc_appauth" class="my_dropdown" style="width:47%;">
                            <option selected="" disabled="">----Select Option----</option>
                            <?php foreach($result as $row)
                            {
                            ?>
                                <option value="<?php echo $row->pcfr_appauth ?>"><?php echo $row->pcfr_appauth ?></option>
                            <?php
                            }
                            ?>
                    </td>                   
                </tr>

                <tr>
					<td><label for="pc_desc" class="control-label">Description: </label></td>
                	<td>
                    <textarea name="pc_desc" value="" placeholder="Give Description Here...." style="width:300px;height:100px"></textarea>
            
                	</td>
                
                </tr>
             
                <tr>
                    <td></td>
                    <td>
                        <button name="purch_selection">Submit Form</button>
                    </td>
           		</tr>

			</table>
		</form>

</body>
<p>&nbsp;</p>
    <div align=left> <?php $this->load->view('template/footer');?></div>
</html>
