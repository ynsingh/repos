
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name editfees.php 
  @author Vijay (vijay.pal428@gmail.com)
 -->
 <html>
   <head>    
   <title>Edit Fees</title>
        <?php $this->load->view('template/header'); ?>
        
    </head>
    <body>
    <script>
	function goBack() {
    	window.history.back();
	}
    </script>

	<table style="margin-left:2%;width:100%;">
           <tr><td>
                <?php //echo anchor('setup/displayfees/', " Edit Program Fees" ,array('title' => ' Fees Configuration Detail ' , 'class' => 'top_parent'));?>
                <div style="margin-left:2%;">
                    <?php echo validation_errors('<div style="margin-left:2%;" class="isa_warning">','</div>');?>
                    <?php echo form_error('<div style="margin-left:2%;" class="isa_error">','</div>');?>

                    <?php if(isset($_SESSION['success'])){?>
                        <div style="margin-left:2%;" class="isa_success"><?php echo $_SESSION['success'];?></div>

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
	
      	<table style="padding: 8px 8px 8px 50px;">  
 
        <?php

           echo form_open('setup/editfees/' . $id);
            	echo "<tr>";
                echo "<td>";
                echo form_label('Program Name', 'fm_programid');
                echo "</td>";
                echo "<td>";
                echo form_input($fm_programid);
                echo "</td>";
                echo "<td>";
                echo "</td>";
           	echo "</tr>";
           	echo "<tr>";
                echo "<td>";
                echo form_label('Academic Year', '');
                echo "</td>";
                echo "<td>";
		$acdy =$fm_acadyear['value'];
		echo "<select name=\"fm_acadyear\" class=\"my_dropdown\" style=\"width:100%;\">";
                echo "<option value=\"$acdy\">$acdy</option>";
                echo "<option value=\" disabled selected \">------Select Academic year------</option>";

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
               echo form_label('Semester: ', 'fm_semester');
               echo "</td>";
               echo "<td>";
               echo form_input($fm_semester);
               echo "</td>";
               echo "</tr>";

               echo "<tr>";
               echo "<td>";
               echo form_label('Category: ', 'fm_category ');
               echo "</td>";
               echo "<td>";
               echo form_input($fm_category);
               echo "</td>";
               echo "</tr>";
        
               echo "<tr>";
               echo "<td>";
               echo form_label('Gender','fm_gender');
               
                echo "</td>";
                echo "<td>";
		$gen=$fm_gender['value'];
		    echo "<select name=\"fm_gender\"class=\"my_dropdown\" style=\"width:100%;\">";
		    echo "<option value=$gen class=\"dropdown-item\">$gen</option>";
                    echo "<option value=\"disabled selected\">------Select Gender------</option>";
                    echo "<option value=\"Male\" class=\"dropdown-item\">Male</option>";
                    echo "<option value=\"Female\" class=\"dropdown-item\">Female</option>";
                    echo "<option value=\"Transgender\" class=\"dropdown-item\" >Transgender</option>";
                    echo "</select>";

                echo "</td>";
                echo "</tr>";
            
                echo "<tr>";
                echo "<td>";
                echo form_label('Head', 'fm_head');
                echo "</td>";
                echo "<td>";
                   
                echo form_input($fm_head);
                echo "</td>";
            	echo "</tr>";
            	echo "<tr>";
                echo "<td>";
                echo form_label('Amount', 'fm_amount');
         
                echo "</td>";
                echo "<td>";
                echo form_input($fm_amount);
                echo "</td>";
            	echo "</tr>";
            
	   	echo "<tr>";
                echo "<td>";
                echo form_label('Description', 'fm_desc');
                echo "</td>";
                echo "<td>";
                echo form_input($fm_desc);
                echo "</td>";
            	echo "</tr>";

        
           	echo "<tr>";
		echo "<td>";
                echo "<td colspan=2>";
                    echo form_hidden('id', $id);
                    echo form_submit('submit', 'Update');
            	    echo form_close();
		    echo "<button onclick=\"goBack()\" >Back</button>";
		echo "</td>";
                echo "</td>";
            	echo "</tr>";
          ?>
        </table>
    </body>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
 </html>


	
