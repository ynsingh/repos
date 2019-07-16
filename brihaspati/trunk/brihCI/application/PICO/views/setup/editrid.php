<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Edit|Details</title>
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

            echo form_open('picosetup/editrid/' . $id);

       
            echo "<tr>";
                echo "<td>";
                    echo form_label('ID:', 'rid_ppid');
                echo "</td>";
                echo "<td>";
                    echo form_input($rid_ppid);
                echo "</td>";
                echo "<td>";
                    echo "Example:123 etc. ";
                echo "</td>";
            echo "</tr>";
            echo "<tr>";
                echo "<td>";
                    echo form_label('Item Description:', 'rid_itemdes');
                echo "</td>";
                echo "<td>";
                    echo form_input($rid_itemdes);
                echo "</td>";
                echo "<td>";
                    echo "Example :item details here.";
                echo "</td>";
            echo "</tr>";
            
            
            
            echo "<tr>";
                echo "<td>";
                    echo form_label('Stock:', 'rid_itemstock');
                echo "</td>";
                echo "<td>";
                    echo form_input($rid_itemstock);
                echo "</td>";
                echo "<td>";
                    echo "Example:30 Unit etc. ";
                echo "</td>";
            echo "</tr>";
            echo "<tr>";
                echo "<td>";
                    echo form_label('Required:', 'rid_itemqtyreq');
                echo "</td>";
                echo "<td>";
                    echo form_input($rid_itemqtyreq);
                echo "</td>";
                echo "<td>";
                    echo "Example : 20 Units .";
                echo "</td>";
            echo "</tr>";
            
            
            
            echo "<tr>";
                echo "<td>";
                    echo form_label('Unit Price:', 'rid_itemunitp');
                echo "</td>";
                echo "<td>";
                    echo form_input($rid_itemunitp);
                echo "</td>";
                echo "<td>";
                    echo "Example: 90 etc. ";
                echo "</td>";
            echo "</tr>";
            echo "<tr>";
                echo "<td>";
                    echo form_label('GST Apply:', 'rid_itemgstapply');
                echo "</td>";
                echo "<td>";
                    echo form_input($rid_itemgstapply);
                echo "</td>";
                echo "<td>";
                    echo "Example :Gst Of Item.";
                echo "</td>";
            echo "</tr>";
            
            
            
            echo "<tr>";
                echo "<td>";
                    echo form_label('GST no', 'rid_gst');
                echo "</td>";
                echo "<td>";
                    echo form_input($rid_gst);
                echo "</td>";
                echo "<td>";
                    echo "Example: GST. ";
                echo "</td>";
            echo "</tr>";
            echo "<tr>";
                echo "<td>";
                    echo form_label('Total Cost', 'rid_itemtotcost');
                echo "</td>";
                echo "<td>";
                    echo form_input($rid_itemtotcost);
                echo "</td>";
                echo "<td>";
                    echo "Example :Total cost 7000";
                echo "</td>";
            echo "</tr>";
        
               
               
               
               
               
               
               
               
               
               
                echo "<td>";
                    echo form_hidden('rid_id', $id);
                   echo"<td>";
                    echo form_submit('Submit','Update');
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



