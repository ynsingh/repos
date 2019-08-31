<?php defined('BASEPATH') OR exit('No direct script access allowed');



?>
<html>
<title>Intender Specification Form </title>
 <head>    
        <?php $this->load->view('template/header'); 
              

        ?>
		<?php if(isset($_SESSION['success'])){?>
                        <div class="isa_success"><?php echo $_SESSION['success'];?></div>
					<?php
                    };
                    ?>
					<?php if(isset($_SESSION['err_message'])){?>
                        <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>

                    <?php
                    };
                    ?>
         <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/datepicker/jquery-ui.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-ui.js" ></script>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">

       
</head>
<script>
    $(document).ready(function(){
        var today = new Date();
            $('#EnquiryDate,#EnquiryLastDate,#DateFrom,#DateTo').datepicker({
                dateFormat: 'yy/mm/dd',
                autoclose:true,
                changeMonth: true,
                changeYear: true,
                yearRange: 'c-30:c+10',
                // endDate: "today",
                // maxDate: today
            }).on('changeDate', function (ev) {
                $(this).datepicker('hide');
            });
			});


            

   

    </script>

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

   

    <form id="myform" action="<?php echo site_url('Iprocess/specificationsform');?>" method="POST" class="form-inline" autocomplete="OFF" enctype="multipart/form-data">
			<table class="TFtable" >
			    <tr>
					
                    <td><label for="enquiry_date">Enquiry Date:<font color='Red'>*</font></label></td>
                    <td><input type="text" name="enquiry_date" id="EnquiryDate" value="<?php echo isset($_POST["EnquiryDate"]) ? $_POST["EnquiryDate"] : ''; ?>"  
                        size="30" ></td>
               
                    <td><label for="enquiry_no" class="control-label">Enquiry No.: </label></td>
                    <td>
                    <input type="text" name="enquiry_no"  class="form-control" size="30" placeholder="Enter Enquiry No." /><br>
                    </td>
					<tr>
					
                    <td><label for="enquiry_lastdate">Enquiry Last Date:<font color='Red'>*</font></label></td>
                    <td colspan="3"><input type="text" name="enquiry_lastdate" id="EnquiryLastDate" value="<?php echo isset($_POST["EnquiryLastDate"]) ? $_POST["EnquiryLastDate"] : ''; ?>"  
                        size="30" ></td></tr>
						
                    <tr><td colspan="4"><u><strong>Item Details</strong></u></td></tr>

				<tr><td><label for="item_name" class="control-label">Item Name: </label></td>
                    <td>
                    <input type="text" name="item_name"  class="form-control" size="30" placeholder="Enter Item Name" />
                    </td>
                   
                
                    <td><label for="item_quantity">Item Quantity:<font color='Red'>*</font></label></td>
                    <td><input type="text" name="item_quantity" id="Item Quantity"  size="30" ></td>
				</tr><tr>
                   
					
                    <td><label for="description_upload_filename" class="control-label">Description File Upload: </label></td>
                    <td colspan="3">
					
					 <input  type="file" name="description_upload_filename" id="description_upload_filename" >
                    
                 <tr><td colspan="4"><u><strong>Intender Details</strong></u></td></tr>
				 <td ><label for="name" class="control-label">Name:- </label></td>
                    <td colspan="3">
                    <input type="text" name="name"  class="form-control" size="30" placeholder="Enter Your Name" /><br>
                    </td></tr>
                <tr>
				 <td><label for="dept_id" class="control-label">Dept Id. </label></td>
                    <td>
                    <input type="text" name="dept_id"  class="form-control" size="30" placeholder="Enter Dept Id" /><br>
                    </td>
                    <td><label for="desig_id" class="control-label">Designation Id: </label></td>
<td>
                    <input type="text" name="desig_id"  class="form-control" size="30" placeholder="Enter Desig. Id" /><br>
                    </td>
					</tr>
					<tr>
                    <td><label for="email" class="control-label">E-mail ID: </label></td>
                    <td>
                    <input type="email" name="email"  class="form-control" size="30" placeholder="yourname@email.com" /><br>
                    </td>

                
					 <td><label for="phone" class="control-label">Phone</label></td>
                    <td>
                    <input type="text" name="phone"  class="form-control" size="30" placeholder="Enter Your number" /><br>
                    </td>
					</tr>
					<tr>
                    <td><label  for="terms_condition_desc" class="control-label">Terms and Condition File Upload</label></td>
                   
					 <td colspan="3">
                    <input  type="file" name="terms_condition_filename" id="terms_condition_filename" > 
                </td>
                    
        </table>

        
       

        </table> 

        
        <br>
                <tr>
                </tr>
                <tr>
                <td></td>
                <td>
                <button name="Ispecification">Submit Form</button>
                </td>
           		</tr>

			
    </form>

        <br>
</body>
<p>&nbsp;</p>
    <div align=left> <?php $this->load->view('template/footer');?></div>

</html>

