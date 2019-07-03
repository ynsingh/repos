
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Vendor | Form</title>

 <head>
     <?php $this->load->view('template/header'); ?>
   
 </head>    
 <body> 
<!--<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>-->


     <table width="100%">
            <tr><td>
                <?php echo anchor('picosetup/displayvendor/', "View vendor Detail ", array('title' => 'Add Detail' ,'class' =>'top_parent'));?>
                <?php
                 echo "<td align=\"right\">";
                 $help_uri = site_url()."/help/helpdoc#Role";
		 echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
		echo "</td>"
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
 
    <form action="<?php echo site_url('picosetup/vendor');?>" method="POST" class="form-inline">
            <table>
            <tr>
                <td><label for="vendor_companyname" class="control-label">Vendor companyname:</label></td>
                <td><input type="text" name="vendor_companyname"  class="form-control" size="30" /><br></td>
                <td>
                    <?php echo form_error('vendor_companyname')?>
                </td>
                <td>Example:xyz limited etc.</td>
            </tr>
            <tr>
                <td><label for="vendor_address" class="control-label">Vendor address:</label></td>
                <td><input type="text" name="vendor_address" size="30"  class="form-control" /><br></td>
                <td>
                    <?php echo form_error('vendor_address')?>
                </td>
                <td>Example : house-123,new Delhi  .</td>
            </tr>
            <tr>
                <td><label for="vendor_city" class="control-label">Vendor city:</label></td>
                <td><input type="text" name="vendor_city"  class="form-control" size="30" /><br></td>
                <td>
                    <?php echo form_error('vendor_city')?>
                </td>
                <td>Example:Kanpur etc.</td>
            </tr>
            <tr>
                <td><label for="vendor_pincode" class="control-label">Vendor pincode:</label></td>
                <td><input type="text" name="vendor_pincode" size="30"  class="form-control" /><br></td>
                <td>
                    <?php echo form_error('vendor_pincode')?>
                </td>
                <td>Example :208017 .</td>
            </tr>
            <tr>
                <td><label for="vendor_phone" class="control-label">Vendor phone:</label></td>
                <td><input type="phone" name="vendor_phone"  class="form-control" size="30" /><br></td>
                <td>
                    <?php echo form_error('vendor_phone')?>
                </td>
                <td>Example: XXXXX789 etc.</td>
            </tr>
            <tr>
                <td><label for="vendor_type" class="control-label">Vendor type:</label></td>
                <td><input type="text" name="vendor_type" size="30"  class="form-control" /><br></td>
                <td>
                    <?php echo form_error('vendor_type')?>
                </td>
                <td>Example : types .</td>
            </tr>
            <tr>
                <td><label for="vendor_blackliststatus" class="control-label">Vendor blackliststatus:</label></td>
                <td><input type="text" name="vendor_blackliststatus"  class="form-control" size="30" /><br></td>
                <td>
                    <?php echo form_error('vendor_blackliststatus')?>
                </td>
                <td>Example:bts  etc.</td>
            </tr>
            <tr>
                <td><label for="vendor_blacklistdate" class="control-label">Vendor blacklistdate:</label></td>
                <td><input type="date" name="vendor_blacklistdate" size="30"  class="form-control" /><br></td>
                <td>
                    <?php echo form_error('vendor_blacklistdate')?>
                </td>
                <td>Example :2017-06-24 .</td>
            </tr>
            
            
            <tr>
                <td></td><td>
                <button name="vendor" >Add Vendor</button>
                <button name="reset" >Clear</button>
                </td>
           </tr>
           </table>
    </form>
 <p><br></p>
    </div>
    </body>
<p>&nbsp;</p>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
    </html>

   
