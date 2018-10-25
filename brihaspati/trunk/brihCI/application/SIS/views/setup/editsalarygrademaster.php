<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name editsalarygrademaster.php 
  @author Om Prakash(omprakashkgp@gmail.com)
 -->

<html>
<title>editsalarygrademaster</title>
    <head>    
      <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
 <script>
        $(document).ready(function(){
     //           $("#sgname").hide();
                $("#slevel" ).hide();
                $("#sgpay").hide();
        
                 $('#paycomm').on('change',function(){
                        var pc= $('#paycomm').val();
                        if(pc == '6th'){
                              //  $("#sgname").show();
                                $("#sgpay").show();
                                $("#slevel").hide();
                        }
                        else{
                                $("#slevel").show();
                            //    $("#sgname").hide();
                                $("#sgpay").hide();
                        }
                  });


        });
 </script>   
    </head>
    <body>
        <?php $this->load->view('template/header'); ?>

<script>
        function goBack() {
        window.history.back();
        }
    </script>

      <table width="100%">
            <tr><td>
		<?php
        	   echo anchor('setup/displaysalarygrademaster', 'Salary Grade List', array('class' => 'top_parent'));
	        ?>

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
                echo "<td><label for=\"paycomm\" class=\"control-label\">Pay Commission:</label></td>";
                echo "<td>";
                echo "<select name=\"paycomm\" id=\"paycomm\" style=\"width:100%;\">";
                echo "<option value=".$sgm_pc.">".$sgm_pc."</option>";
                echo "<option value=\"\" disabled selected >------Select ---------------</option>";
                echo "<option value=\"6th\">6th</option>";
                echo "<option value=\"7th\">7th</option>";
                echo "</select>";
                echo "</td>";
                echo "</tr>";

       
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
            
            echo "<tr id='sgpay'>";
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

echo "<tr id='slevel'>";
               echo "<td>";
               echo form_label('Salary Level ','sgm_level');

                echo "</td>";
                echo "<td>";
                $level=$sgm_level['value'];
                    echo "<select name=\"sgm_level\"class=\"my_dropdown\" style=\"width:100%;\">";
                    echo "<option value=$level \"class=\"dropdown-item\">$level</option>";
                     echo "<option value=selected disabled \"class=\"dropdown-item\">------select------</option>";
                    echo "<option value=\"level-1\" class=\"dropdown-item\">level-1</option>";
                    echo "<option value=\"level-2\" class=\"dropdown-item\">level-2</option>";
                    echo "<option value=\"level-3\" class=\"dropdown-item\">level-3</option>";
                    echo "<option value=\"level-4\" class=\"dropdown-item\">level-4</option>";
                    echo "<option value=\"level-5\" class=\"dropdown-item\">level-5</option>";
                    echo "<option value=\"level-6\" class=\"dropdown-item\">level-6</option>";
                    echo "<option value=\"level-7\" class=\"dropdown-item\">level-7</option>";
                    echo "<option value=\"level-8\" class=\"dropdown-item\">level-8</option>";
                    echo "<option value=\"level-9\" class=\"dropdown-item\">level-9</option>";
                    echo "<option value=\"level-10\" class=\"dropdown-item\">level-10</option>";
                    echo "<option value=\"level-11\" class=\"dropdown-item\">level-11</option>";
                    echo "<option value=\"level-12\" class=\"dropdown-item\">level-12</option>";
                    echo "<option value=\"level-13\" class=\"dropdown-item\">level-13</option>";
                    echo "<option value=\"level-13A\" class=\"dropdown-item\">level-13A</option>";
                    echo "<option value=\"level-14\" class=\"dropdown-item\" >level-14</option>";
                    echo "<option value=\"level-15\" class=\"dropdown-item\">level-15</option>";
                    echo "<option value=\"level-16\" class=\"dropdown-item\">level-16</option>";
                    echo "<option value=\"level-17\" class=\"dropdown-item\">level-17</option>";
                    echo "<option value=\"level-18\" class=\"dropdown-item\">level-18</option>";
                    echo "</select>";

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

