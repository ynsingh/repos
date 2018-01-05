<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name editcategory.php 
  @author Om Prakash(omprakashkgp@gmail.com)
 -->

<html>
<title>edittaxslab</title>
    <head>    
        <?php $this->load->view('template/header'); ?>
      
    </head>
    <body>

<script>
        function goBack() {
        window.history.back();
        }
    </script>
<table width="100%">
<tr><td>
<?php echo anchor('setup/displaytaxslab/', "Tax Slab List" ,array('title' => 'Subject List' , 'class' => 'top_parent'));?>
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
            echo form_open('setup/edittaxslab/' . $tsm_id);


		echo "<tr>";
                echo "<td>";
                echo form_label('Financial Year', '');
                echo "</td>";
                echo "<td>";
                $tsmfy =$tsm_fy['value'];
                echo "<select name=\"tsm_fy\" class=\"my_dropdown\" style=\"width:100%;\">";
                echo "<option value=\"$tsmfy\">$tsmfy</option>";
                echo "<option value=\" disabled selected \">------Select Financial year------</option>";

                      for($i = 2016; $i < date("Y")+10; $i++){
                          $j=$i+1;
                      echo '<option value="'.$i.' - '.$j.'">'.$i.' - '.$j.'</option>';
                      }
               echo " </select>";
               echo "</td>";
               echo "<td>";
               echo "</td>";
               echo "</tr>";



       
            echo "<tr>";
                echo "<td>";
                    echo form_label('Tax Slab Name', 'tsm_name');
                echo "</td>";
                echo "<td>";
                    echo form_input($tsm_name);
                echo "</td>";
                echo "<td>";
                echo "</td>";
            echo "</tr>";

 		echo "<tr>";
                echo "<td>";
                    echo form_label('Tax Slab Start Value', 'tsm_startvalue');
                //echo "<br />";
                echo "</td>";
                echo "<td>";
                    echo form_input($tsm_startvalue);
                echo "</td>";
                echo "<td>";
                echo "</td>";
            echo "</tr>";
        
            echo "<tr>";
                echo "<td>";
                    echo form_label('Tax Slab End Value', 'tsm_endvalue');
                //echo "<br />";
                echo "</td>";
                echo "<td>";
                    echo form_input($tsm_endvalue);
                echo "</td>";
                echo "<td>";
                echo "</td>";
            echo "</tr>";
            
            /*echo "<tr>";
                echo "<td>";
                    echo form_label('Tax Slab Type', 'tsm_type');
                //echo "<br />";
                echo "</td>";
                echo "<td>";
                    echo form_input($tsm_type);
                echo "</td>";
                echo "<td>";
                echo "</td>";
            echo "</tr>";*/


              echo "<tr>";
               echo "<td>";
               echo form_label('Tax Slab Type','tsm_type');

                echo "</td>";
              echo "<td>";
                $tsmtype=$tsm_type['value'];
                    echo "<select name=\"tsm_type\"class=\"my_dropdown\" style=\"width:100%;\">";
                    echo "<option value=$tsmtype class=\"dropdown-item\">$tsmtype</option>";
                    echo "<option value=\"disabled selected\">------Select Slab Type------</option>";
                    echo "<option value=\"Normal\" class=\"dropdown-item\">Normal</option>";
                    echo "<option value=\"Senior Citizen\" class=\"dropdown-item\">Senior Citizen</option>";
                    echo "</select>";

                echo "</td>";
                echo "</tr>";

              echo "<tr>";
               echo "<td>";
               echo form_label('Tax Slab Gender','tsm_gender');

                echo "</td>";
              echo "<td>";
                $tsmgender=$tsm_gender['value'];
                    echo "<select name=\"tsm_gender\"class=\"my_dropdown\" style=\"width:100%;\">";
                    echo "<option value=$tsmgender class=\"dropdown-item\">$tsmgender</option>";
                    echo "<option value=\"disabled selected\">------Select Gender------</option>";
                    echo "<option value=\"Male\" class=\"dropdown-item\">Male</option>";
                    echo "<option value=\"Female\" class=\"dropdown-item\">Female</option>";
                    echo "<option value=\"Transgender\" class=\"dropdown-item\" >Transgender</option>";
                    echo "</select>";

                echo "</td>";
                echo "</tr>";


            echo "<tr>";
                echo "<td>";
                    echo form_label('Tax Slab Percent', 'tsm_percent');
                echo "</td>";
                echo "<td>";
                    //echo "<br />";
                    echo form_input($tsm_percent);
                echo "</td>";
                echo "<td>";

            echo "<tr>";
		echo "<td></td>";
                echo "<td>";
                    echo form_hidden('tsm_id', $tsm_id);
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

