<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Edit|Supplier</title>
    <head>    
    <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <?php $this->load->view('template/header'); ?>
        
    </head>
    <body>
<!--<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>  check status of login-->
 <script type="text/javascript">
        function goBack() {
        window.history.back();
        
        
        
        }
        
        
        


</script>
   
        <table width="100%"> 
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
                </div>
        </td></tr>  
        </table>
      
        <table class="TFtable"> 
 
         <?php

            echo form_open('picosetup/editvendor/' . $id,'class="form-inline"');

       
            echo "<tr>";
                echo "<td>";
                    echo form_label('Firm\'s Name:', 'vendor_companyname');
                echo "</td>";
                echo "<td>";
                    echo form_input($vendor_companyname);
                echo "</td>";
             
                echo "<td>";
                    echo form_label('Owner\'s Name:', 'vendor_name');
                echo "</td>";
                echo "<td>";
                    echo form_input($vendor_name);
                echo "</td>";
                
            echo "</tr>";
           
            echo "<tr>";
                echo "<td>";
                    echo form_label('Full Postal Address:', 'vendor_address');
                echo "</td>";
                echo "<td>";
                    echo form_input($vendor_address);
                echo "</td>";
                
                echo "<td>";
                    echo form_label('Pincode:', 'vendor_pincode');
                echo "</td>";
                echo "<td>";
                    echo form_input($vendor_pincode);
                echo "</td>";
              
            echo "</tr>";
            
            echo "<tr>";
                echo "<td>";
                    echo form_label('Head Office Address:', 'vendor_hqaddress');
                echo "</td>";
                echo "<td>";
                    echo form_input($vendor_hqaddress);
                echo "</td>";
             
                echo "<td>";
                    echo form_label('Pincode:', 'vendor_hqpincode');
                echo "</td>";
                echo "<td>";
                    echo form_input($vendor_hqpincode);
                echo "</td>";
              
            echo "</tr>";
            
            echo "<tr>";
                echo "<td>";
                    echo form_label('E-mail Address:', 'vendor_email');
                echo "</td>";
                echo "<td>";
                    echo form_input($vendor_email);
                echo "</td>";
             
                echo "<td>";
                    echo form_label('Website Address:', 'vendor_website');
                echo "</td>";
                echo "<td>";
                    echo form_input($vendor_website);
                echo "</td>";
               
            echo "</tr>";
           
            echo "<tr>";
                echo "<td>";
                    echo form_label('Contact Person\'s Name:', 'vendor_cpn');
                echo "</td>";
                echo "<td>";
                    echo form_input($vendor_cpn);
                echo "</td>";
             
                echo "<td>";
                    echo form_label('Phone No.:', 'vendor_phone');
                echo "</td>";
                echo "<td>";
                    echo form_input($vendor_phone);
                echo "</td>";
               
            echo "</tr>";
            
            echo "<tr>";
                echo "<td>";
                    echo form_label('Mobile No.:', 'vendor_mobile');
                echo "</td>";
                echo "<td>";
                    echo form_input($vendor_mobile);
                echo "</td>";
              
                echo "<td>";
                    echo form_label('Fax No.:', 'vendor_fax');
                echo "</td>";
                echo "<td>";
                    echo form_input($vendor_fax);
                echo "</td>";
             
            echo "</tr>";
            
            echo "<tr>";
                echo "<td>";
                    echo form_label('City:', 'vendor_city');
                echo "</td>";
                echo "<td>";
                    echo form_input($vendor_city);
                echo "</td>";
              
                echo "<td>";
                    echo form_label('State:', 'vendor_state');
                echo "</td>";
                echo "<td>";
                    echo form_input($vendor_state);
                echo "</td>";
              
            echo "</tr>";
            echo "<tr>";
                echo "<td>";
                    echo form_label('GST No:', 'vendor_gstno');
                echo "</td>";
                echo "<td>";
                    echo form_input($vendor_gstno);
                echo "</td>";
            
                echo "<td>";
                    echo form_label('PAN No:', 'vendor_pan');
                echo "</td>";
                echo "<td>";
                    echo form_input($vendor_pan);
                echo "</td>";
             
            echo "</tr>";
            echo "<tr>";
                echo "<td>";
                    echo form_label('Shop Act<br>Registration No:', 'vendor_sarn');
                echo "</td>";
                echo "<td>";
                    echo form_input($vendor_sarn);
                echo "</td>";
               
                echo "<td>";
                    echo form_label('Excise <br>Registration No:', 'vendor_ern');
                echo "</td>";
                echo "<td>";
                    echo form_input($vendor_ern);
                echo "</td>";
              
            echo "</tr>";
            echo "<tr>";
                echo "<td>";
                    echo form_label('Bank Account No:', 'vendor_ban');
                echo "</td>";
                echo "<td>";
                    echo form_input($vendor_ban);
                echo "</td>";
             
                echo "<td>";
                    echo form_label('Manufacturer/Supplier:', 'vendor_type');
                echo "</td>";
                echo "<td>";
                    echo form_input($vendor_type);
                echo "</td>";
              
            echo "</tr>";
            
            echo "<tr>";
                echo "<td>";
                    echo form_label('List Of Organizations<br><br>Previously Supplied:', 'vendor_list');
                echo "</td>";
                echo "<td>";
                    echo form_input($vendor_list);
                echo "</td>";
            
                echo "<td>";
                    echo form_label('Item(s) Name <br><br>Want to Supply:', 'vendor_supply');
                echo "</td>";
                echo "<td>";
                    echo form_input($vendor_supply);
                echo "</td>";
             
            echo "</tr>";
            
            echo "<tr>";
                echo "<td>";
                    echo form_label('Blacklist Status:', 'vendor_blackliststatus');
                echo "</td>";
                echo "<td width=340>";
                 
                echo form_dropdown('vendor_blackliststatus', $options = array('NO' =>'NO','YES' =>'YES'),$vendor_blackliststatus['value']);//'$vendor_blackliststatus'
                echo "</td>";
              
            //echo form_label('Blacklisted From:', 'vendor_blacklistdatefrom'); echo form_input($vendor_blacklistdatefrom);
               ?>
               
           
               <td>
                              
                
                <label for="vendor_blacklistdatefrom" class="control-label">Blacklist To:</label> 
                
                 
               </td>
               <td>
                     <input type="date" name="vendor_blacklistdatefrom" value="<?php echo $vendor_blacklistdatefrom['value'] ?>"  class="form-control" style="width:300 ;" /><br>  
               </td>
              
            </tr>
        
                  <tr>
                  <td>
                 
                  
                 <label for="vendor_blacklistdateto" class="control-label">Blacklist To:</label> 
               
               </td>
               <td>
               <input type="date" name='vendor_blacklistdateto' value="<?php echo $vendor_blacklistdateto['value'] ?>" class="form-control" style="width:300 ;" /><br>
               </td>
               
               
            
               <?php
         
                echo "<td>";
                    echo form_label('Blacklisted By:', 'vendor_blacklistby');
                echo "</td>";
                echo "<td>";
                 //   echo form_input($vendor_blacklistby);
                            echo $vendor_blacklistby['value'];
                echo "</td>";
               
            echo "</tr>";
               
               
               
               
        
               
               
                echo "<tr>";
                    echo form_hidden('vendor_id', $id);
                   echo"<td >";
                    echo form_submit('submit', 'Update');
                   echo " ";   echo"</td>";
                   echo"<td >";  echo"</td>";
                   echo"<td >";   echo"</td>";
           echo"<td >";
          
            echo form_close();
            echo "<button onclick=\"goBack()\" >Back</button>";
            
            
            echo "</td>";
            echo "</tr>";
          
 ?>
 
       </table> 
          </form>
</body>
<p>&nbsp;</p>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>



