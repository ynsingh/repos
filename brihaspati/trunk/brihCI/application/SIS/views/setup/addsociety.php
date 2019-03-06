<!--@author addsociety.php 
    @author Neha Khullar (nehukhullar@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Add Society</title>

 <head>
     <?php $this->load->view('template/header'); ?>
                                  <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery-ui.css">
                                  <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-1.12.4.js" ></script>
                                  <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
                                  <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.js" ></script>
                                  <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
<script>
 var today = new Date();
$(document).ready(function(){
	$("#StartDate").datepicker({
		dateFormat: 'dd-mm-yy',
		autoclose:true,
                changeMonth: true,
                changeYear: true,
		yearRange: 'c-120:c+10',
		endDate: "today",
                maxDate: today
	}).on('changeDate', function (ev) {
          $(this).datepicker('hide');
        });
/**Allows only letters, numbers and spaces. All other characters will return an error.**/
            $('.keyup-characters').keyup(function() {
            $('span.error-keyup-2').remove();
            var inputVal = $(this).val();
            var characterReg = /^\s*[a-zA-Z0-9,\s]+\s*$/;
            if(!characterReg.test(inputVal)) {
                $(this).after('<span class="error error-keyup-2"><font color="red">No special characters allowed.</font></span>');
            }
            });
            /******************************************close Special characters***************************************/
            /************************************Accepts only 0 – 9**********************************************/
            $('.keyup-numeric').keyup(function() {
                $('span.error-keyup-1').hide();
                var inputVal = $(this).val();
                var numericReg = /^\d*[0-9](|.\d*[0-9]|,\d*[0-9])?$/;
                if(!numericReg.test(inputVal)) {
                    $(this).after('<span class="error error-keyup-1"><font color="red">Numeric characters only.</font></span>');
                }
            });
            /************************************ close Accepts only 0 – 9**********************************************/
            /**This is a standard regular expression, which is used to validate email addresses to ensure they follow the standard format:**/
            $('.keyup-email').keyup(function() {
                $('span.error-keyup-7').remove();
                var inputVal = $(this).val();
                var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
                if(!emailReg.test(inputVal)) {
                    $(this).after('<span class="error error-keyup-7"><font color="red">Invalid Email Format.</font></span>');
                }
            });
            /***************************************close*********************************************************************/
           /************************************************valid pan number**********************************************************************/
           $('#txtPANNumber').change(function (event) {     
                var regExp = /[a-zA-z]{5}\d{4}[a-zA-Z]{1}/; 
                var txtpan = $(this).val(); 
                    if( txtpan.match(regExp) ){ 
                       // alert('PAN match found');
                    }
                    else {
                        alert('Not a valid PAN number');
                        event.preventDefault(); 
                    } 
            });
           /*******************************************************close pan number method****************************************************************/

});
</script>
</head>
<body>
     <table width="100%">
            <tr><td>
                <div>
                <?php echo anchor('setup/displaysociety/', "View Society", array('title' => 'Add Detail' ,'class' =>'top_parent'));?>
                <?php
                 echo "<td align=\"right\">";
                 $help_uri = site_url()."/help/helpdoc#Role";
          //       echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                 echo "</td>";
                 ?>
                <div  style="width:100%;">
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
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
              </div>
             </td></tr>
            </table>

    <div>
    <form action="<?php echo site_url('setup/addsociety');?>" method="POST" class="form-inline">
            <table>
              <tr>
                <td><label for="soc_name" class="control-label">Society Name: <font color='Red'>*</font></label></td>
                <td>
                <input type="text" name="soc_name" class="form-control" size="30" placeholder="Write Name" value="<?php echo isset($_POST["soc_name"]) ? $_POST["soc_name"] : ''; ?>" />
		</td>
		</tr>
		
		 <tr>
                <td><label for="soc_code" class="control-label">Society Code: <font color='Red'>*</font></label></td>
                <td>
                <input type="text" name="soc_code"  class="form-control" size="30" placeholder="Write society code"  value="<?php echo isset($_POST["soc_code"]) ? $_POST["soc_code"] : ''; ?>" />
                </td>
                </tr>

		<tr>
                <td><label for="soc_regdate" class="control-label">Society Registration No:</label></td>
                <td><input type="text" name="soc_regno" id="regno" class="form-control" size="30" placeholder="Registartion no"  value="<?php echo isset($_POST["soc_regno"]) ? $_POST["soc_regno"] : ''; ?>"/>
                </td>
                </tr>

		<tr>
                <td><label for="soc_regdate" class="control-label">Society Registration Date:</label></td>
                <td><input type="text" name="soc_regdate" id="StartDate" class="form-control" size="30" placeholder="Start Date"  value="<?php echo isset($_POST["soc_regdate"]) ? $_POST["soc_regdate"] : ''; ?>"/>
                </td>
                </tr>
              <tr>
                <td>
                <label for="soc_address" class="control-label">Society Address:</label></td>
                <td>
                <input type="text" name="soc_address" size="30"  class="form-control" placeholder="Address" value="<?php echo isset($_POST["soc_address"]) ? $_POST["soc_address"] : ''; ?>" />
                </td>
		</tr>
		
		<tr>
                <td><label for="soc_purpose" class="control-label">Society Phone No: </label></td>
                <td>
                <input type="text" name="soc_phone" class="keyup-numeric" class="form-control" size="30" placeholder="Phone No"  value="<?php echo isset($_POST["soc_phone"]) ? $_POST["soc_phone"] : ''; ?>" />
                </td>
                </tr>
                
		<tr>
                <td><label for="soc_purpose" class="control-label">Society Mobile No: </label></td>
                <td>
                <input type="text" name="soc_mobile" class="keyup-numeric" class="form-control" size="30" placeholder="Mobile No" value="<?php echo isset($_POST["soc_mobile"]) ? $_POST["soc_mobile"] : ''; ?>" />
                </td>
                </tr>
		<tr>
                <td><label for="soc_purpose" class="control-label">Society Email: </label></td>
                <td>
                <input type="text" name="soc_email"  class="keyup-email" class="form-control" size="30" placeholder="Email "  value="<?php echo isset($_POST["soc_email"]) ? $_POST["soc_email"] : ''; ?>" />
                </td>
                </tr>
		<tr>
                <td><label for="soc_purpose" class="control-label">Society PAN No: </label></td>
                <td>
                <input type="text" name="soc_pan"  id="txtPANNumber" class="form-control" size="30" placeholder="PAN No" value="<?php echo isset($_POST["soc_pan"]) ? $_POST["soc_pan"] : ''; ?>" />
                </td>
                </tr>
		<tr>
                <td><label for="soc_purpose" class="control-label">Society TAN No: </label></td>
                <td>
                <input type="text" name="soc_tan" class="form-control" size="30" placeholder="TAN No" value="<?php echo isset($_POST["soc_tan"]) ? $_POST["soc_tan"] : ''; ?>" />
                </td>
                </tr>
		<tr>
                <td><label for="soc_purpose" class="control-label">Society GST No: </label></td>
                <td>
                <input type="text" name="soc_gst" class="form-control" size="30" placeholder="GST No" value="<?php echo isset($_POST["soc_gst"]) ? $_POST["soc_gst"] : ''; ?>" />
                </td>
                </tr>
		<tr>
                <td><label for="soc_purpose" class="control-label">Society Bank Name: </label></td>
                <td>
                <input type="text" name="soc_bname" class="keyup-characters" class="form-control" size="30" placeholder="Bank Name" value="<?php echo isset($_POST["soc_bname"]) ? $_POST["soc_bname"] : ''; ?>" />
                </td>
                </tr>
		<tr>
                <td><label for="soc_purpose" class="control-label">Society Bank A/C No : </label></td>
                <td>
                <input type="text" name="soc_bacno" class="form-control" size="30" placeholder="Bank A/C No" value="<?php echo isset($_POST["soc_bacno"]) ? $_POST["soc_bacno"] : ''; ?>" />
                </td>
                </tr>
		<tr>
                <td><label for="soc_purpose" class="control-label">Society Bank IFSC No: </label></td>
                <td>
                <input type="text" name="soc_bifsc" class="keyup-characters" class="form-control" size="30" placeholder="Bank IFSC No" value="<?php echo isset($_POST["soc_bifsc"]) ? $_POST["soc_bifsc"] : ''; ?>" />
                </td>
                </tr>
		<tr>
                <td><label for="soc_purpose" class="control-label">Society Bank MICR No: </label></td>
                <td>
                <input type="text" name="soc_bmicr" class="keyup-characters" class="form-control" size="30" placeholder="Bank MICR No" value="<?php echo isset($_POST["soc_bmicr"]) ? $_POST["soc_bmicr"] : ''; ?>" />
                </td>
                </tr>
		<tr>
                <td><label for="soc_purpose" class="control-label">Society Bank Branch: </label></td>
                <td>
                <input type="text" name="soc_bbranch" class="form-control" size="30" placeholder="Bank Branch" value="<?php echo isset($_POST["soc_bbranch"]) ? $_POST["soc_bbranch"] : ''; ?>" />
                </td>
                </tr>
		<tr>
                <td><label for="soc_purpose" class="control-label">Society Bank Account Type: </label></td>
                <td>
                <input type="text" name="soc_bactype" class="form-control" size="30" placeholder="Bank Acount Type" value="<?php echo isset($_POST["soc_bactype"]) ? $_POST["soc_bactype"] : ''; ?>" />
                </td>
                </tr>
 		<tr>
                <td>
                <label for="soc_remark" class="control-label">Society Remarks:</label></td>
                <td>
                <input type="text" name="soc_remark" size="30"  class="form-control" value="<?php echo isset($_POST["soc_remark"]) ? $_POST["soc_remark"] : ''; ?>" />
                </td>
		</tr>



            <tr>
	<td></td><td>
                <button name="addsociety" >Add Society</button>
                <input type="reset" name="Reset" value="Clear"/>
                </td>
           </tr>
           </table>
    </form>
</div>
</body>
        <p> &nbsp; </p>
     <div><?php $this->load->view('template/footer'); ?></div>
</html>
        
