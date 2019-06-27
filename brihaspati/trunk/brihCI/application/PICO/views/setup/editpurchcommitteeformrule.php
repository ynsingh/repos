<?php defined('BASEPATH') OR exit('No direct script access allowed');
/**
* @name: Edit Purchase Committee Rule
* @author: Shivam Kumar Singh  (shivam.iitk1@gmail.com)
* @author: Nagendra Kumar Singh (nksinghiitk@gmail.com)
*/
?>


<html>
<title>Purchase Committee Rule | Edit</title>
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

            echo form_open('picosetup/editpurchasecommitteeformationrule/' . $id);

       
            echo "<tr>";
                echo "<td>";
                    echo form_label('Purchase Through', 'pcfr_purchasethrough');
                echo "</td>";
                echo "<td>";
                    echo form_input($pcfr_purchasethrough);
                echo "</td>";
               
            echo "</tr>";

            echo "<tr>";
                echo "<td>";
                    echo form_label('Estimated Purchase Price', 'pcfr_estpurchaseprice');
                echo "</td>";
                echo "<td>";
                    echo form_input($pcfr_estpurchaseprice);
                echo "</td>";
               
            echo "</tr>";

            echo "<tr>";
                echo "<td>";
                    echo form_label('1st Representative', 'pcfr_rep1');
                echo "</td>";
                echo "<td>";
                    echo form_input($pcfr_rep1);
                echo "</td>";

            echo "<tr>";
                echo "<td>";
                    echo form_label('2nd Representative', 'pcfr_rep2');
                echo "</td>";
                echo "<td>";
                    echo form_input($pcfr_rep2);
                echo "</td>";

            echo "<tr>";
                echo "<td>";
                    echo form_label('3rd Representative', 'pcfr_rep3');
                echo "</td>";
                echo "<td>";
                    echo form_input($pcfr_rep3);
                echo "</td>";       
            echo "</tr>";

            echo "<tr>";
                echo "<td>";
                    echo form_label('Reference', 'pcfr_reference');
                echo "</td>";
                echo "<td>";
                    echo form_input($pcfr_reference);
                echo "</td>";   
            echo "</tr>";

            echo "<tr>";
                echo "<td>";
                    echo form_label('Approving Authority', 'pcfr_appauth');
                    //echo "<br />";
                echo "</td>";
                echo "<td>";
                    echo form_input($pcfr_appauth);
                echo "</td>";
               
            echo "</tr>";
        
                echo "<td>";
                    echo form_hidden('pcfr_id', $id);
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


