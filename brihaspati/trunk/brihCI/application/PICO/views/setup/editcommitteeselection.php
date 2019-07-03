<?php defined('BASEPATH') OR exit('No direct script access allowed');
/**
 * @name: Edit Purchase Committee Selection
 * @author: Nagendra Kumar Singh (nksinghiitk@gmail.com)
 * @author: Shivam Kumar Singh  (shivam.iitk1@gmail.com)
 */
?>


<html>
<title>Committee Selection | Edit </title>
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

            echo form_open('picosetup/editcommitteeselection/' . $id);

       
            echo "<tr>";
                echo "<td>";
                    echo form_label('Purchase Through', 'pc_purchasethrough');
                echo "</td>";
                echo "<td>";
                    echo form_input($pc_purchasethrough);
                echo "</td>";
               
            echo "</tr>";

            echo "<tr>";
                echo "<td>";
                    echo form_label('Estimated Price', 'pc_purchpricelimit');
                echo "</td>";
                echo "<td>";
                    echo form_input($pc_purchpricelimit);
                echo "</td>";
               
            echo "</tr>";

            echo "<tr>";
                echo "<td>";
                    echo form_label('Department', 'pc_dept');
                echo "</td>";
                echo "<td>";
                    echo form_input($pc_dept);
                echo "</td>";
               
            echo "</tr>";

            echo "<tr>";
                echo "<td>";
                    echo form_label('Convener', 'pc_convener');
                echo "</td>";
                echo "<td>";
                    echo form_input($pc_convener);
                echo "</td>";                
            echo "</tr>";

            echo "<tr>";
                echo "<td>";
                    echo form_label('1st Representative', 'pc_rep1');
                echo "</td>";
                echo "<td>";
                    echo form_input($pc_rep1);
                echo "</td>";                
            echo "</tr>";

            echo "<tr>";
                echo "<td>";
                    echo form_label('2nc Representative', 'pc_rep2');
                echo "</td>";
                echo "<td>";
                    echo form_input($pc_rep2);
                echo "</td>";                
            echo "</tr>";

            echo "<tr>";
                echo "<td>";
                    echo form_label('3rd Representative', 'pc_rep3');
                echo "</td>";
                echo "<td>";
                    echo form_input($pc_rep3);
                echo "</td>";                
            echo "</tr>";

            echo "<tr>";
                echo "<td>";
                    echo form_label('4th Representative', 'pc_rep4');
                echo "</td>";
                echo "<td>";
                    echo form_input($pc_rep4);
                echo "</td>";                
            echo "</tr>";

            echo "<tr>";
                echo "<td>";
                    echo form_label('5th Representative', 'pc_rep5');
                echo "</td>";
                echo "<td>";
                    echo form_input($pc_rep5);
                echo "</td>";                
            echo "</tr>";

           
            echo "<tr>";
                echo "<td>";
                    echo form_label('Authority','pc_appauth');
                    //echo "<br />";
                echo "</td>";
                echo "<td>";
                    echo form_input($pc_appauth);
                echo "</td>";               
            echo "</tr>";

            echo "<tr>";
                echo "<td>";
                    echo form_label('Description', 'pc_desc');
                    //echo "<br />";
                echo "</td>";
                echo "<td>";
                    echo form_input($pc_desc);
                echo "</td>";               
            echo "</tr>";
        
        
                echo "<td>";
                    echo form_hidden('pc_id', $id);
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


