<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Edit Tender</title>
    <head>    
        <?php $this->load->view('template/header'); ?>
        
    </head>
    <body>
<!--<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>  check status of login-->
 <script>
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
                    };
                    ?>
                </div> </br> 
        </td></tr>  
        </table>
    
        <table> 
 
         <?php

            echo form_open('picosetup/editvendor/' . $id);

       
            echo "<tr>";
                echo "<td>";
                    echo form_label('vendor companyname', 'vendor_companyname');
                echo "</td>";
                echo "<td>";
                    echo form_input($vendor_companyname);
                echo "</td>";
                echo "<td>";
                    echo "Example: rk vendors etc. ";
                echo "</td>";
            echo "</tr>";
            echo "<tr>";
                echo "<td>";
                    echo form_label('vendor address', 'vendor_address');
                echo "</td>";
                echo "<td>";
                    echo form_input($vendor_address);
                echo "</td>";
                echo "<td>";
                    echo "Example : vendor address here.";
                echo "</td>";
            echo "</tr>";
            
            
            
            echo "<tr>";
                echo "<td>";
                    echo form_label('vendor city', 'vendor_city');
                echo "</td>";
                echo "<td>";
                    echo form_input($vendor_city);
                echo "</td>";
                echo "<td>";
                    echo "Example: delhi etc. ";
                echo "</td>";
            echo "</tr>";
            echo "<tr>";
                echo "<td>";
                    echo form_label('vendor pincode', 'vendor_pincode');
                echo "</td>";
                echo "<td>";
                    echo form_input($vendor_pincode);
                echo "</td>";
                echo "<td>";
                    echo "Example : 208017.";
                echo "</td>";
            echo "</tr>";
            
            
            
            echo "<tr>";
                echo "<td>";
                    echo form_label('vendor phone', 'vendor_phone');
                echo "</td>";
                echo "<td>";
                    echo form_input($vendor_phone);
                echo "</td>";
                echo "<td>";
                    echo "Example: xxxxxx7890 etc. ";
                echo "</td>";
            echo "</tr>";
            echo "<tr>";
                echo "<td>";
                    echo form_label('vendor type', 'vendor_type');
                echo "</td>";
                echo "<td>";
                    echo form_input($vendor_type);
                echo "</td>";
                echo "<td>";
                    echo "Example : vendor type.";
                echo "</td>";
            echo "</tr>";
            
            
            
            echo "<tr>";
                echo "<td>";
                    echo form_label('vendor b l s', 'vendor_blackliststatus');
                echo "</td>";
                echo "<td>";
                    echo form_input($vendor_blackliststatus);
                echo "</td>";
                echo "<td>";
                    echo "Example: vendor b l s. ";
                echo "</td>";
            echo "</tr>";
            echo "<tr>";
                echo "<td>";
                    echo form_label('vendor b l d', 'vendor_blacklistdate');
                echo "</td>";
                echo "<td>";
                    echo form_input($vendor_blacklistdate);
                echo "</td>";
                echo "<td>";
                    echo "Example : vendor b ld.";
                echo "</td>";
            echo "</tr>";
        
               
               
               
               
               
               
               
               
               
               
                echo "<td>";
                    echo form_hidden('vendor_id', $id);
                   echo"<td>";
                    echo form_submit('submit', 'Update');
                   echo " ";
       
            echo form_close();
            echo "<button onclick=\"goBack()\" >Back</button>";
            echo "</td>";
            echo "</tr>";
            echo"</td>";
 ?>
 
       </table> 
          
</body>
<p>&nbsp;</p>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>



