<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name editsalarygrademaster.php 
  @author Om Prakash(omprakashkgp@gmail.com)
 -->

<html>
<title>editsalarygrademaster</title>
<!--<link rel="shortcut icon" href="<?php //echo base_url('assets/images'); ?>/index.jpg">-->
	<link rel="icon" href="<?php echo base_url('uploads/logo'); ?>/nitsindex.png" type="image/png">	
    <head>    
        <?php $this->load->view('template/header'); ?>
       
    </head>
    <body>

<script>
        function goBack() {
        window.history.back();
        }
    </script>

      <table>
            <tr colspan="2"><td>
		<?php
        	   echo anchor('setup/displaysalarygrademaster', 'Salary Grade List', array('class' => 'top_parent'));
	        ?>

                <div align="left" style="margin-left:0%;width:95%;">
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
                    echo form_label('Salary Grade Name', 'sgm_name');
                echo "</td>";
                echo "<td>";
                    echo form_input($sgm_name);
                echo "</td>";
                echo "<td>";
                echo "</td>";
            echo "</tr>";

 		echo "<tr>";
                echo "<td>";
                    echo form_label('Salary Grade Max', 'sgm_max');
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
                    echo form_label('Salary Grade Min', 'sgm_min');
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
                    echo form_label('Salary Grade Pay Band', 'sgm_gradepay');
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
<p>&nbsp;</p>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

