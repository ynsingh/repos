<?php defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name: Item Receive Form
 * @author: Nagendra Kumar Singh (nksinghiitk@gmail.com)
 */

?>
<html>
<title>Item Receive Form</title>
 <head>    
        <?php $this->load->view('template/header'); ?>

        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css"> 
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/datepicker/jquery-ui.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-ui.js" ></script>
        <script>
                $(document).ready(function(){
                        $( "#asignother" ).hide();
                        $('#asigndatefrom,#asigndateto').datepicker({
                            dateFormat: 'yy/mm/dd',
                            autoclose:true,
                            changeMonth: true,
                            changeYear: true,
                            yearRange: 'c-70:c+20',
               
                        }).on('changeDate', function (ev) {
                            $(this).datepicker('hide');
                        });
                
                        $('#asignname').on('change',function(){
                                var recmthd = $(this).val();
                                if(recmthd == 'Others'){
                                        $( "#asignother" ).show();
                                }
                                else{
                                        $( "#asignother" ).hide();
                                }
                        });
                });
        </script> 
 
</head>

<table width="100%">
            <tr><td>
                <?php
                    echo anchor('itemaction/itemtypedetails','View Item Details', array('title' =>'Item List'));
                ?>
                 <?php
echo "<td align=\"center\" width=\"34%\">";
			echo "<b> Item Receive Form </b>";
                   echo "</td>";
                   echo "<td align=\"right\" width=\"33%\">";
                   $help_uri = site_url()."/help/helpdoc#ViewRoleDetail";
                   echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                   echo "</td>";
                 ?>
            </td></tr> 
            <tr><td colspan=3>   
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

		<form action="<?php echo site_url('itemaction/insertitemtype');?>" method="POST" class="form-inline">
		<table class="TFtable">
		<tr><td>	
			<label for="item_mtid" class="control-label">Material Type :<font color='Red'>*</font></label>
		</td><td>
			<select id="mtid" style="width:300px;" name="mtid" required>
                        <option selected="selected" disabled selected>--------Material Type-----</option>
                       <?php foreach($mtlist as $mdata): ?>
                                <option class="test" value="<?php echo $mdata->mt_id; ?>"><?php echo $mdata->mt_name; ?></option>
                        <?php endforeach; ?>
		    </select>
		</td></tr>
		<tr><td>	
                        <label for="item_pono" class="control-label">PO Number: <font color='Red'>*</font> </label>
		</td><td>
                	   <input type="text" name="item_pono"  class="form-control" size="30"  placeholder="Item PO Number" />
		</td></tr>
		<tr><td>	
                	        <label for="item_challanno" class="control-label">Challan Number: <font color='Red'>*</font> </label>
		</td><td>
                	   <input type="text" name="item_challan"  class="form-control" size="30"  placeholder="Item Challan Number" />
		</td></tr>
		<tr><td>	
                        <label for="item_challandate" class="control-label">Challan Date: <font color='Red'>*</font> </label>
		</td><td>
                	   <input type="text" name="item_challandate"  class="form-control" size="30"  placeholder="Item Challan Date" />
		</td></tr>
		<tr><td>	
                        <label for="item_name" class="control-label">Item Name :<font color='Red'>*</font></label>
		</td><td>
                	   <input type="text" name="item_name"  class="form-control" size="30" placeholder="Item Name" />
		</td></tr>
		<tr><td>	
                        <label for="item_price" class="control-label">Item Price :<font color='Red'>*</font></label>
		</td><td>
                        <input type="text" name="item_price"  class="form-control" size="30" placeholder="Item Price" />
		</td></tr>
		<tr><td>	
                        <label for="item_stock" class="control-label">Item Quantity: <font color='Red'>*</font> </label>
		</td><td>
                	   <input type="text" name="item_stock"  class="form-control" size="30"  placeholder="Item Stock" />
		</td></tr>
		<tr><td>	
                        <label for="item_stock" class="control-label">Item Description: <font color='Red'></font> </label>
		</td><td>
                	<textarea name="item_desc"  class="form-control" rows="3" cols="50"  placeholder="Item Description"></textarea>
		</td></tr>
		<tr><td>	
                        <label for="item_tp" class="control-label">Transport By: <font color='Red'></font> </label>
		</td><td>
                	<input type="text" name="item_transport"  class="form-control" size="30"  placeholder="Item Transport By" />
		</td></tr>
		<tr><td>	
                        <label for="item_stock" class="control-label">Received By with Dept.: <font color='Red'>*</font> </label>
		</td><td>
                  	<textarea name="item_recevied"  class="form-control" rows="3" cols="50"  placeholder="Item Recevied By"></textarea>
		</td></tr>
		<tr><td>	
			<button name="item_type" type="submit" class="btn btn-default">Add Item</button>
		</td><td>
		</td></tr>
		</table>
		</form>
</body>
<p>&nbsp;</p>
    <div align=left> <?php $this->load->view('template/footer');?></div>
</html>
