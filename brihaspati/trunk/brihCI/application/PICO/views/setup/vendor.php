
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Supplier|Registration</title>

 <head>
 <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
     <?php $this->load->view('template/header'); ?>
   
 </head>    
 <body> 
<!--<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>-->


     <table width="100%">
            <tr><td>
		<?php 
			$sunme = $this->session->userdata['username'];
                	if((strcasecmp($sunme,"admin" )) == 0){
				echo anchor('picosetup/displayvendor/', "View Supplier Detail ", array('title' => 'Add Detail' ,'class' =>'top_parent'));
			}else{

				echo anchor('welcome/', "Login Page ", array('title' => 'Login Page' ,'class' =>'top_parent'));
			}
		?>
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
 
    <form action="<?php echo site_url('picosetup/vendor');?>" method="POST" class="form-inline" enctype="multipart/form-data" > 
            <table class="TFtable">
             <tr>
 		 			  <th colspan="6">Supplier Details</th>
 		 	
 		   	 </tr>	
            
            <tr>
            
                <td><label for="vendor_companyname" class="control-label">Firm's Name:</label></td>
                <td><input type="text" name="vendor_companyname"  placeholder="Example:X.Y.Z limited etc. " class="form-control" size="30" /><br></td>
                <td>
                    <?php echo form_error('vendor_companyname')?>
                </td>
                
          
                <td><label for="vendor_name" class="control-label">Owner's Name:</label></td>
                <td><input type="text" name="vendor_name" placeholder="Example: Abhay etc." class="form-control" size="30" /><br></td>
                <td>
                    <?php echo form_error('vendor_name')?>
                </td>
               
            </tr>
            <tr>
                <td><label for="vendor_address" class="control-label">Full Postal Address:</label></td>
                <td><input type="text" name="vendor_address" size="30" placeholder="Example : House-1523,new Delhi ." class="form-control" /><br></td>
                <td>
                    <?php echo form_error('vendor_address')?>
                </td>
              
           
                <td><label for="vendor_pincode" class="control-label"> Pincode:</label></td>
                <td><input type="text" name="vendor_pincode" size="30" placeholder="Example :208017." class="form-control" /><br></td>
                <td>
                    <?php echo form_error('vendor_pincode')?>
                </td>
           
            </tr>
             <tr>
                <td><label for="vendor_hqaddress" class="control-label">Head Office Address: </label></td>
                <td><input type="text" name="vendor_hqaddress" size="30" placeholder="HQ & Postal Is Same Address ,Copy it.." class="form-control" /><br></td>
                <td>
                    <?php echo form_error('vendor_hqaddress')?>
                </td>
               
           
                <td><label for="vendor_hqpincode" class="control-label"> Pincode:</label></td>
                <td><input type="text" name="vendor_hqpincode" size="30" placeholder="Example :208009 ." class="form-control" /><br></td>
                <td>
                    <?php echo form_error('vendor_hqpincode')?>
                </td>
           
            </tr>
            <tr>
                <td><label for="vendor_email" class="control-label">E-mail Address:</label></td>
                <td><input type="text" name="vendor_email" placeholder="Example:abhay***877@gmail.com etc." class="form-control" size="30" /><br></td>
                <td>
                    <?php echo form_error('vendor_email')?>
                </td>
            
           
                <td><label for="vendor_website" class="control-label">Website Address:</label></td>
                <td><input type="text" name="vendor_website" placeholder="Example:Kanpur etc." class="form-control" size="30" /><br></td>
                <td>
                    <?php echo form_error('vendor_website')?>
                </td>
              
            </tr>
            <tr>
                <td><label for="vendor_cpn" class="control-label">Contact Person's Name:</label></td>
                <td><input type="text" name="vendor_cpn"  placeholder="Example: Raj Kumar." class="form-control" size="30" /><br></td>
                <td>
                    <?php echo form_error('vendor_cpn')?>
                </td>
          
           

                <td><label for="vendor_phone" class="control-label">Phone No.:</label></td>
                <td><input type="phone" name="vendor_phone" placeholder="Example: 0512XX8578 etc." class="form-control" size="30" /><br></td>
                <td>
                    <?php echo form_error('vendor_phone')?>
                </td>
            
            </tr>
             <tr>

                <td><label for="vendor_mobile" class="control-label">Mobile No.:</label></td>
                <td><input type="phone" name="vendor_mobile" placeholder="Example: 831XXX8578 etc." class="form-control" size="30" /><br></td>
                <td>
                    <?php echo form_error('vendor_mobile')?>
                </td>
              
           

                <td><label for="vendor_fax" class="control-label">Fax No.:</label></td>
                <td><input type="phone" name="vendor_fax" placeholder="Example: 222XXX8888 etc." class="form-control" size="30" /><br></td>
                <td>
                    <?php echo form_error('vendor_fax')?>
                </td>
               
            </tr>
            <tr>
                <td><label for="vendor_city" class="control-label">City:</label></td>
                <td><input type="text" name="vendor_city" placeholder="Example:Kanpur etc." class="form-control" size="30" /><br></td>
                <td>
                    <?php echo form_error('vendor_city')?>
                </td>
                

                <td><label for="vendor_state" class="control-label">State:</label></td>
                <td><input type="text" name="vendor_state" placeholder="Example:Uttar Pradesh etc." class="form-control" size="30" /><br></td>
                <td>
                    <?php echo form_error('vendor_state')?>
                </td>
               
            </tr>
            
            </table>
            <br>
            <br>
            <br>
           <table class="TFtable"> 
             <tr>
 		 			  <th colspan="6">Registration Details(Sale tax,Shop Act,Excise)</th>
 		 	
 		   	 </tr>	
            
            <tr>
              
              
           
                <td><label for="vendor_gstno" class="control-label">GST No:</label></td>
                <td colspan="2"><input type="text" name="vendor_gstno" size="30" placeholder="Example:11AAAAA2222A1ZN." class="form-control" /><br></td>
              
          
            
                <td><label for="vendor_pan" class="control-label">PAN No:</label></td>
                <td colspan="2"><input type="text" name="vendor_pan" size="30" placeholder="Example:AAAAA2222A" class="form-control" /><br></td>
               
            </tr>
            <tr></tr>
            <tr>
                <td></td>
                <td>
                    <input type="file" name="f_gst" id="f_gst" > 
                </td>
               <td></td>
               <td></td>
                <td colspan="2">
                   <input type="file" name="f_pan" id="f_pan" > 
                </td>
            
            
            </tr>
              <tr>
                <td><label for="vendor_sarn" class="control-label">Shop Act<br>Registration No:</label></td>
                <td colspan="2"><input type="text" name="vendor_sarn" size="30" placeholder="Example:2020202#####"  class="form-control" /><br></td>
               
              
            
                <td><label for="vendor_ern" class="control-label">Excise <br>Registration No:</label></td>
                <td colspan="2"><input type="text" name="vendor_ern" size="30" placeholder="Example:99978#$@"  class="form-control" /><br></td>
              
              
            </tr>
            <tr></tr>
            <tr>
                <td></td>
                <td>
                    <input type="file" name="f_shop" id="f_shop" > 
                </td>
               <td></td>
               <td></td>
                <td colspan="2">
                   <input type="file" name="f_excise" id="f_excise" > 
                </td>
            
            
            </tr>
             <tr>
                <td><label for="vendor_ban" class="control-label">Bank Account No:</label></td>
                <td colspan="2"><input type="text" name="vendor_ban" size="30" placeholder="Example:A12345####" class="form-control" /><br></td>
              
              
               <td><label for="vendor_type" class="control-label">Manufacturer/Supplier:</label></td>
                <td>
                    	<select name="vendor_type"  class="form-control" style="width:340px;">
 		 				<option name="select" value="disabled" selected="selected" disabled >Select</option>
 		 				<option value="Manufacturer">Manufacturer</option>
 		 				<option value="Supplier">Supplier</option>
 		 				</select>                
                
                
                
                </td>
                <td>
                    <?php echo form_error('vendor_type')?>
                </td>
              
              
            </tr>
              <tr></tr>
            <tr>
                <td></td>
                <td colspan="2">
                    <input type="file" name="f_bank" id="f_bank" > 
                </td>
            
               <td colspan="3"><b><big>RULE:Upload File Size(Max-500 Kilo-Bytes) & Type(JPG & PDF) Only.</big></b></td>
            </tr>
            
            
            
            
            
             <tr>
                <td><label for="vendor_list" class="control-label">List Of Organizations <br><br>Previously Supplied:</label></td>
                <td><textarea style="width:340px; height:100px; " name="vendor_list" placeholder="Give list" ></textarea><br></td>
                <td>
                    <?php echo form_error('vendor_list')?>
                </td>
              
               <td><label for="vendor_supply[]" class="control-label">Item(s) Name <br><br>Want to Supply:</label></td>
                <td>
                    <input type="checkbox" name="vendor_supply[]"  value="Computer" />Computer             
                    <input type="checkbox" name="vendor_supply[]"  value="Glassware" />Glassware
                     <input type="checkbox" name="vendor_supply[]"  value="Liveries" />Liveries
                   
                   <br>
                    <input type="checkbox" name="vendor_supply[]"  value="Stationery" />Stationery
                   <input type="checkbox" name="vendor_supply[]"  value="Electronics" />Electronics
                    <input type="checkbox" name="vendor_supply[]" value="Chemical"  />Chemical
                     
                    <br>
                     <input type="checkbox" name="vendor_supply[]" value="Furniture"  />Furniture.
                   
                    <input type="checkbox" name="vendor_supply[]"  value="Medicines" />Medicines
                    <input type="checkbox" name="vendor_supply[]"  value="Scientific Equipment" />Scientific Equip.<br>
                
                
                </td>
                <td>
                    <?php echo form_error('vendor_supply[]')?>
                </td>
              
              
            </tr>
        
            
            <tr>
                <td colspan="5">
                <button name="vendor" >SUBMIT FORM</button>
                <button name="reset" >Clear</button>
                </td>
                <td><input type="hidden" name="vendor_blackliststatus"  value="NO" /><br></td>
             
                
           </tr>
           </table>
    </form>
 <p><br></p>
    </div>
    </body>
<p>&nbsp;</p>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
    </html>

   
