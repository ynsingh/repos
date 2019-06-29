<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Edit Tender</title>
    <head>    
        <?php $this->load->view('template/header'); ?>
        
    </head>
    <body>
<!--<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>-->
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

            echo form_open('picosetup/edittypeoftender/' . $id);

       
            echo "<tr>";
                echo "<td>";
                    echo form_label('Tender Name', 'tt_name');
                echo "</td>";
                echo "<td>";
                    echo form_input($tt_name);
                echo "</td>";
                echo "<td>";
                    echo "Example: single tender , rate control etc. ";
                echo "</td>";
            echo "</tr>";
            echo "<tr>";
                echo "<td>";
                    echo form_label('Tender Description', 'tt_desc');
                echo "</td>";
                echo "<td>";
                    echo form_input($tt_desc);
                echo "</td>";
                echo "<td>";
                    echo "Example : Tender details like DOS & D control.";
                echo "</td>";
            echo "</tr>";
        
                echo "<td>";
                    echo form_hidden('tt_id', $id);
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



