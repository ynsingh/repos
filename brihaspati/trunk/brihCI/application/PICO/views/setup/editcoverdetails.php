<?php defined('BASEPATH') OR exit('No direct script access allowed');
/**
 * @name: Edit Cover Type
 * @author: Nagendra Kumar Singh (nksinghiitk@gmail.com)
 * @author: Shivam Kumar Singh  (shivam.iitk1@gmail.com)
 */
?>


<html>
<title>Cover Type | Edit</title>
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

            echo form_open('picosetup/editcoverdetails/' . $id);

       
            echo "<tr>";
                echo "<td>";
                    echo form_label('Cover No.:','ct_coverno');
                echo "</td>";
                echo "<td>";
                    echo form_input($ct_coverno);
                echo "</td>";

                 echo "<td>";
                    echo form_label('Fixed Cover:', 'ct_coverfixed');
                echo "</td>";
                echo "<td>";
                    echo form_input($ct_coverfixed);
                echo "</td>";             
            echo "</tr>";

            echo "<tr>";
                echo "<td>";
                    echo form_label('Cover Type 1:','ct_cover1');
                echo "</td>";
                echo "<td>";
                    echo form_input($ct_cover1);
                echo "</td>";

                echo "<td>";
                    echo form_label('Cover Type 2:','ct_cover2');
                echo "</td>";
                echo "<td>";
                    echo form_input($ct_cover2);
                echo "</td>";
            echo "</tr>";

             echo "<tr>";
                echo "<td>";
                    echo form_label('Cover Type 3:','ct_cover3');
                echo "</td>";
                echo "<td>";
                    echo form_input($ct_cover3);
                echo "</td>";

                echo "<td>";
                    echo form_label('Cover Type 4:','ct_cover4');
                echo "</td>";
                echo "<td>";
                    echo form_input($ct_cover4);
                echo "</td>";
            echo "</tr>";

             echo "<tr>";
                echo "<td>";
                    echo form_label('Optional Cover:', 'ct_coveroptional');
                echo "</td>";
                echo "<td>";
                    echo form_input($ct_coveroptional);
                echo "</td>";    

                echo "<td>";
                    echo form_label('Cover Description:', 'ct_desc');
                echo "</td>";
                echo "<td>";
                    echo form_input($ct_desc);
                echo "</td>";       
            echo "</tr>";
        
                echo "<td>";
                    echo form_hidden('ct_id', $id);
                   echo"<td colspan=3>";
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


