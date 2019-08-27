<?php defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name: Item Issue Form
 * @author: Nagendra Kumar Singh (nksinghiitk@gmail.com)
 */

?>
<html>
<title>Item Receive Form</title>
 <head>    
        <?php $this->load->view('template/header'); ?>

        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css"> 
<!--        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/bootstrap.min.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script> -->
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
        <script>
	$(document).ready(function(){
		 $('#mtid').on('change',function(){
                var mtype = $(this).val();
          //      alert("comin ======="+mtype);
                if(mtype === ''){
                   $('#itemname').prop('disabled',true);
                }
                else{
             
                    $('#itemname').prop('disabled',false);
                    $.ajax({
                        url: "<?php echo base_url();?>picoindex.php/itemaction/getitemlist",
                        type: "POST",
                        data: {"mtype" : mtype},
			dataType:"html",
                        success:function(data){
                            $('#itemname').html(data.replace(/^"|"$/g, ''));                  
                        },
                        error:function(data){
		//	alert(data.toSource());
                            alert("error occur..!!"+data);
                	     
                        }
                                            
                    });
                }
            }); 

		 $('#empid').on('change',function(){
                    var soc = $('#empid').val();
                   // alert("soc====="+soc);
                    if(soc == ''){
                        $('#deptname').prop('disabled',true);
                    }
                    else{
                        $('#deptname').prop('disabled',false);
                        $.ajax({
                            url: "<?php echo base_url();?>picoindex.php/jslist/getempdept",
                            type: "POST",
                            data: {"empid" : soc},
                            dataType:"html",
                            success:function(data){
                            //    alert("data==="+data);
                                $('#deptname').val(data.replace(/"|"/g,""));
                            },
                            error:function(data){
                            	alert("data in error==="+data);
                            //    alert("error occur..!!");
                            }
                        });
                    }
                });


                });
        </script> 
 
</head>

<table width="100%">
            <tr><td>
                <?php
                    echo anchor('itemaction/itemissuedetails','View Issued Item Details', array('title' =>'Item Issued List'));
                ?>
                 <?php
echo "<td align=\"center\" width=\"34%\">";
			echo "<b>Item Issue Form</b>";
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

		<form action="<?php echo site_url('itemaction/insertitemissued');?>" method="POST" class="form-inline">
		<table class="TFtable">
		<tr><td>	
			<label for="item_mtid" class="control-label">Material Type :<font color='Red'>*</font></label>
		</td><td>
			<select id="mtid" style="width:400px;" name="mtid" required>
                        <option selected="selected" disabled selected>--------Material Type-----</option>
                       <?php foreach($mtlist as $mdata): ?>
                                <option class="test" value="<?php echo $mdata->mt_id; ?>"><?php echo $mdata->mt_name; ?></option>
                        <?php endforeach; ?>
		    </select>
		</td></tr>
		<tr><td>	
                        <label for="item_name" class="control-label">Item Name(Available Qty) :<font color='Red'>*</font></label>
		</td><td>
			<div><select name="item_name" id="itemname"  style="width:350px;">
                        <option selected="selected" disabled selected >-------- Select Item Name --------</option>
                        </select></div>
<!--                	   <input type="text" name="item_name" id="itemname" class="form-control" size="30" placeholder="Item Name" /> -->
		</td></tr>
		<tr><td>	
                        <label for="item_stock" class="control-label">Item Quantity: <font color='Red'>*</font> </label>
		</td><td>
                	   <input type="text" name="item_qty"  id="itemqty" class="form-control" size="30"  placeholder="Item Qty" />
		</td></tr>
		<tr><td>	
                        <label for="item_stock" class="control-label">Item Description: <font color='Red'></font> </label>
		</td><td>
                	<textarea name="item_desc"  class="form-control" id="itemdesc" rows="3" cols="50"  placeholder="Item Description"></textarea>
		</td></tr>
		<tr><td>	
                        <label for="item_pono" class="control-label">Employee Name(PF Number): <font color='Red'>*</font> </label>
		</td><td>
			<select id="empid" style="width:350px;" name="empid" required>
                        <option selected="selected" disabled selected>--------Employee Name and PF-----</option>
                       <?php foreach($pflist as $pdata): ?>
                                <option class="test" value="<?php echo $pdata->emp_id; ?>"><?php echo $pdata->emp_name." ( ".$pdata->emp_code." )"; ?></option>
                        <?php endforeach; ?>
                    </select>

			<?php //print_r($pflist); ?>
                	  <!-- <input type="text" name="emppfno"  id="pfno"  class="form-control" size="30"  placeholder="Employee PF Number" /> -->
		</td></tr>
<!--
		<tr><td>	
                	        <label for="item_challanno" class="control-label">Employee Name: <font color='Red'>*</font> </label>
		</td><td>
                	   <input type="text" name="empname" id="empname"  class="form-control" size="30"  placeholder="Employee name" />
		</td></tr>
-->
		<tr><td>	
                        <label for="item_challandate" class="control-label">Employee Department: <font color='Red'>*</font> </label>
		</td><td>
                	   <input type="text" name="deptname"  id="deptname" class="form-control" size="30"  placeholder="Department Name" />
		</td></tr>
		<tr><td>	
                        <label for="item_stock" class="control-label">Received By with Dept.: <font color='Red'>*</font> </label>
		</td><td>
                  	<textarea name="item_recevied"  class="form-control" rows="3" cols="50"  placeholder="Item Recevied By"></textarea>
		</td></tr>
		<tr><td>	
			<button name="item_issue" type="submit" class="btn btn-default">Issue Item</button>
		</td><td>
		</td></tr>
		</table>
		</form>
</body>
<p>&nbsp;</p>
    <div align=left> <?php $this->load->view('template/footer');?></div>
</html>
