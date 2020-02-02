<?php defined('BASEPATH') OR exit('No direct script access allowed');
/**
 * @name: Edit Financial Power
 * @author: Nagendra Kumar Singh (nksinghiitk@gmail.com)
 * @author: Shivam Kumar Singh  (shivam.iitk1@gmail.com)
 */
?>


<html>
<title>Financial Power | Edit </title>
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

            echo form_open('picosetup/editfinancialpower/' . $id);

       
            echo "<tr>";
                echo "<td>";
                    echo form_label('Type of Purchase', 'fp_typeofpurch');
                echo "</td>";
                echo "<td>";
                    echo form_input($fp_typeofpurch);
                echo "</td>";
               
            echo "</tr>";
/*
            echo "<tr>";
                echo "<td>";
                    echo form_label(' Sub Type of Purchase', 'fp_subtypepurch');
                echo "</td>";
                echo "<td>";
                    echo form_input($fp_subtypepurch);
                echo "</td>";
               
            echo "</tr>";
 */
            echo "<tr>";
                echo "<td>";
                    echo form_label('Authority', 'authority');
                echo "</td>";
                echo "<td>";
                    echo form_input($authority);
                echo "</td>";
               
            echo "</tr>";

            echo "<tr>";
                echo "<td>";
                    echo form_label('Financial Limit', 'fp_limit');
                echo "</td>";
                echo "<td>";
                    echo form_input($fp_limit);
                echo "</td>";
                
            echo "</tr>";


  /*         
            echo "<tr>";
                echo "<td>";
                    echo form_label('Item Description', 'fp_desc');
                    //echo "<br />";
                echo "</td>";
                echo "<td>";
                    echo form_input($fp_desc);
                echo "</td>";
               
            echo "</tr>";
   */    
            echo "<tr>";
                echo "<td colspan=2>";
                    echo form_hidden('fp_id', $id);
                    echo form_submit('submit', 'Update');
                   echo " ";
       
            echo form_close();
            echo "<button onclick=\"goBack()\" >Back</button>";
            echo "</td>";
            echo "</tr>";
 ?>
 
       </table> 
          
</body>
<p>&nbsp;</p>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>


