<!--@name editbankdetails.php
    @author Neha khullar(nehukhullar@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Edit bank detail</title>
    <head>    
        <?php $this->load->view('template/header'); ?>
          
    </head>
    <body>

 <script>
        function goBack() {
        window.history.back();
        }
    </script>


        <!--<//?php
            echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
            echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
            echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
            echo "Setup";
            echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo "|";
            //echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo anchor('setup/displayrole/', "View Role Details", array('title' => 'Add Detail' , 'class' => 'top_parent'));
            echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo "|";
            echo "<span  style='padding: 8px 8px 8px 20px;'>";
            echo "Edit role";
            echo "</span>";
            echo "</td>";
            echo "</tr>";
            echo "</table>";
        ?>--!>
        <table width="100%">
            <tr><td>
              <div>
                    <?php echo validation_errors('<div  class="isa_warning">','</div>');?>
                        <?php echo form_error('<div class="isa_error">','</div>');?></div>

                    <?php if(isset($_SESSION['success'])){?>
                        <div style="margin-left:30px;" class="isa_success"><?php echo $_SESSION['success'];?></div>

                    <?php
                    };
                    ?>
                </div>
        </td></tr>
        </table>

        <table>

         <?php

               echo form_open('setup/editbankdetails/' . $id);

             
                echo "<tr>";
                  echo "<td>";
                    echo form_label('Organisation', 'org_id');                    
                echo "</td>";
                echo "<td>";
                    echo form_input($org_id);
                echo "</td>";
                echo "</td>";                
                echo "<td>";
                echo "</td>";
            echo "</tr>";


           //echo "<p>";
            echo "<tr>";
                echo "<td>";
                    echo form_label('Bank Name ', 'bank_name');
                    //echo "<br />";
                echo "</td>";
                echo "<td>";
                    echo form_input($bank_name);
                echo "</td>";
                echo "</td>";
                echo "<td>";                    
                echo "</td>";
            echo "</tr>";

            //echo "<p>";
            echo "<tr>";
                echo "<td>";
                    echo form_label('Branch Name ', 'branch_name');
                    //echo "<br />";
                echo "</td>";
                echo "<td>";
                    echo form_input($branch_name);
                echo "</td>";
                echo "</td>";
                echo "<td>"; 
                echo "</td>";
            echo "</tr>";
      
            //echo "<p>";
             echo "<tr>";
                echo "<td>";
                    echo form_label('Bank Address ', 'bank_address');
                    //echo "<br />";
                echo "</td>";
                echo "<td>";
                    echo form_input($bank_address);
                echo "</td>";
                echo "</td>";
                echo "<td>";
                echo "</td>";
            echo "</tr>";

              //echo "<p>";
              echo "<tr>";
                echo "<td>";
                    echo form_label('IFSC Code ', 'ifsc_code');
                    //echo "<br />";
                echo "</td>";
                echo "<td>";
                    echo form_input($ifsc_code);
                echo "</td>";
                echo "</td>";
                echo "<td>";                    
                echo "</td>";
            echo "</tr>";

                //echo "<p>";
                echo "<tr>";
                echo "<td>";
                    echo form_label('Account Number ', 'account_number');
                    //echo "<br />";
                echo "</td>";
                echo "<td>";
                    echo form_input($account_number);
                echo "</td>";
                echo "</td>";
                echo "<td>";                    
                echo "</td>";
            echo "</tr>";

                //echo "<p>";
                echo "<tr>";
                echo "<td>";
                    echo form_label('Account Type ', 'account_type');
                    //echo "<br />";
                echo "</td>";
                echo "<td>";
                    echo form_input($account_type);
                echo "</td>";
                echo "</td>";
                echo "<td>";                    
                echo "</td>";
            echo "</tr>";


                 //echo "<p>";
                echo "<tr>";
                echo "<td>";
                    echo form_label('Account Name ', 'account_name');
                    //echo "<br />";
                echo "</td>";
                echo "<td>";
                    echo form_input($account_name);
                echo "</td>";
                echo "</td>";
                echo "<td>";                    
                echo "</td>";
            echo "</tr>";

                 //echo "<p>";
                echo "<tr>";
                echo "<td>";
                    echo form_label('PAN Number ', 'pan_number');
                    //echo "<br />";
                echo "</td>";
                echo "<td>";
                    echo form_input($pan_number);
                echo "</td>";
                echo "</td>";
                echo "<td>";                    
                echo "</td>";
            echo "</tr>";


                  //echo "<p>";
                echo "<tr>";
                echo "<td>";
                    echo form_label('TAN Number ', 'tan_number');
                    //echo "<br />";
                echo "</td>";
                echo "<td>";
                    echo form_input($tan_number);
                echo "</td>";
                echo "</td>";
                echo "<td>";                    
                echo "</td>";
            echo "</tr>";


                  //echo "<p>";
                echo "<tr>";
                echo "<td>";
                    echo form_label('GST Number ', 'gst_number');
                    //echo "<br />";
                echo "</td>";
                echo "<td>";
                    echo form_input($gst_number);
                echo "</td>";
                echo "</td>";
                echo "<td>";                    
                echo "</td>";
            echo "</tr>";



                 //echo "<p>";
                echo "<tr>";
                echo "<td>";
                    echo form_label('Aadhar Number ', 'aadhar_number');
                    //echo "<br />";
                echo "</td>";
                echo "<td>";
                    echo form_input($aadhar_number);
                echo "</td>";
                echo "</td>";
                echo "<td>";                    
                echo "</td>";
            echo "</tr>";


                //echo "<p>";
                echo "<tr>";
                echo "<td>";
                     echo form_label('Remark ', 'remark');
                    //echo "<br />";
                echo "</td>";
                echo "<td>";
                    echo form_input($remark);
                echo "</td>";
                echo "</td>";
                echo "<td>";
                echo "</td>";
            echo "</tr>";
  
           
            //echo "</p>";

            // echo "<p>";

            echo "<tr>";
                echo "<td></td>";
                echo "<td>";
                    echo form_hidden('Organisation', $id);
                    echo form_submit('submit', 'Update');
            echo form_close();
            echo "<button onclick=\"goBack()\" >Back</button>";
            echo "</td>";
            echo "</tr>";
            
 ?>

       </table>

</body>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>


