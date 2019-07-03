<?php defined('BASEPATH') OR exit('No direct script access allowed');
/**
 * @name: Edit Item List
 * @author: Nagendra Kumar Singh (nksinghiitk@gmail.com)
 * @author: Shivam Kumar Singh  (shivam.iitk1@gmail.com)
 */
?>


<html>
<title>Item List | Edit</title>
    <head>    
        <?php $this->load->view('template/header'); ?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
       
    </head>
    <body>

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
    
        <table class="TFtable"> 
 
         <?php

            echo form_open('picosetup/edititemdetails/' . $id);

       
            echo "<tr>";
                echo "<td>";
                    echo form_label('Material ID','item_mtid');
                echo "</td>";
                echo "<td>";
                    echo form_input($item_mtid);
                echo "</td>";
               
            echo "</tr>";

            echo "<tr>";
                echo "<td>";
                    echo form_label('Item Name','item_name');
                echo "</td>";
                echo "<td>";
                    echo form_input($item_name);
                echo "</td>";
               
            echo "</tr>";

            echo "<tr>";
                echo "<td>";
                    echo form_label('Item Price', 'item_price');
                echo "</td>";
                echo "<td>";
                    echo form_input($item_price);
                echo "</td>";
               
            echo "</tr>";

            echo "<tr>";
                echo "<td>";
                    echo form_label('Item Stock', 'item_stock');
                echo "</td>";
                echo "<td>";
                    echo form_input($item_stock);
                echo "</td>";       
            echo "</tr>";
        
                echo "<td>";
                    echo form_hidden('item_id', $id);
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


