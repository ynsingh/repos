<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name editcategory.php 
  @author Om Prakash(omprakashkgp@gmail.com)
 -->

<html>
<title>editsalarygrademaster</title>
    <head>    
        <?php $this->load->view('template/header'); ?>
        <!--h1>Welcome <?= $this->session->userdata('username') ?>  </h1-->
        <?php $this->load->view('template/menu');?>
    </head>
    <body>
<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
<script>
        function goBack() {
        window.history.back();
        }
    </script>

      <table>
            <tr colspan="2"><td>
                <div>
                    <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                    <?php echo form_error('<div class="isa_error">','</div>');?>

                    <?php if(isset($_SESSION['success'])){?>
                        <div class="isa_success"><?php echo $_SESSION['success'];?></div>

                    <?php
                    };
                    ?>
                        <?php if(isset($_SESSION['err_message'])){?>
                          <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                        <?php
                    };
		?>	  

                </div> 
            </td></tr>
        </table>
        <table>  
        <?php
            echo form_open('setup/editsalarygrademaster/' . $sgm_id);
       
            echo "<tr>";
                echo "<td>";
                    echo form_label('Salary Grade Master Name', 'sgm_name');
                echo "</td>";
                echo "<td>";
                    echo form_input($sgm_name);
                echo "</td>";
                echo "<td>";
                echo "</td>";
            echo "</tr>";

 		echo "<tr>";
                echo "<td>";
                    echo form_label('Salary Grade Master Max', 'sgm_max');
                //echo "<br />";
                echo "</td>";
                echo "<td>";
                    echo form_input($sgm_max);
                echo "</td>";
                echo "<td>";
                echo "</td>";
            echo "</tr>";
        
            echo "<tr>";
                echo "<td>";
                    echo form_label('Salary Grade Master Min', 'sgm_min');
                //echo "<br />";
                echo "</td>";
                echo "<td>";
                    echo form_input($sgm_min);
                echo "</td>";
                echo "<td>";
                echo "</td>";
            echo "</tr>";
            
            echo "<tr>";
                echo "<td>";
                    echo form_label('Salary Grade Master Gradepay', 'sgm_gradepay');
                echo "</td>";
                echo "<td>";
                    //echo "<br />";
                    echo form_input($sgm_gradepay);
                echo "</td>";
                echo "<td>";
                    
                echo "</td>";
            echo "</tr>";
            //echo "</p>";
        
            // echo "<p>";
        
            echo "<tr>";
		echo "<td></td>";
                echo "<td>";
                    echo form_hidden('sgm_id', $sgm_id);
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

