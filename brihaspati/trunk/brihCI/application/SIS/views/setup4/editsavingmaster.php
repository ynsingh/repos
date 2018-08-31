<!--@name editsavingmaster.php
@author Deepika Chaudhary (chaudharydeepika88@gmail.com)
 -->
<html>
 <title> Edit Email Setting</title>

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
                <?php echo anchor('setup4/dispsavingmaster/', "View Saving Master" ,array('title' => ' View Saving Master Detail ' , 'class' => 'top_parent'));?>
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
           echo form_open('setup4/editsavingmaster/' . $id);
echo "<tr>";
                echo "<td>";
                echo form_label('Financial Year', '');
                echo "</td>";
                echo "<td>";
                $acdy =$sm_fyear['value'];
                echo "<select name=\"sm_fyear\" class=\"my_dropdown\" style=\"width:100%;\">";
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
               echo form_label('U/S 80C: ', 'sm_80C');
               echo "</td>";
               echo "<td>";
               echo form_input($sm_80C);
               echo "</td>";
               echo "</tr>";
 echo "<tr>";
               echo "<td>";
               echo form_label('U/S 80CCD (1-B): ', 'sm_80CCD');
               echo "</td>";
               echo "<td>";
               echo form_input($sm_80CCD);
               echo "</td>";
               echo "</tr>";
 echo "<tr>";
               echo "<td>";
               echo form_label('U/S 80D: ', 'sm_80D');
               echo "</td>";
               echo "<td>";
               echo form_input($sm_80D);
               echo "</td>";
               echo "</tr>";
 echo "<tr>";
               echo "<td>";
               echo form_label('U/S 80DD: ', 'sm_80DD');
               echo "</td>";
               echo "<td>";
               echo form_input($sm_80DD);
               echo "</td>";
               echo "</tr>";
 echo "<tr>";
               echo "<td>";
               echo form_label('U/S 80E: ', 'sm_80E');
               echo "</td>";
               echo "<td>";
               echo form_input($sm_80E);
               echo "</td>";
               echo "</tr>";
 echo "<tr>";
               echo "<td>";
               echo form_label('U/S 80G: ', 'sm_80G');
               echo "</td>";
               echo "<td>";
               echo form_input($sm_80G);
               echo "</td>";
               echo "</tr>";

 echo "<tr>";
               echo "<td>";
               echo form_label('U/S 80GGA: ', 'sm_80GGA');
               echo "</td>";
               echo "<td>";
               echo form_input($sm_80GGA);
               echo "</td>";
               echo "</tr>";
 echo "<tr>";
               echo "<td>";
               echo form_label('U/S 80U:', 'sm_80U');
               echo "</td>";
               echo "<td>";
               echo form_input($sm_80U);
               echo "</td>";
               echo "</tr>";
 echo "<tr>";
               echo "<td>";
               echo form_label('U/S 24B: ', 'sm_24B');
               echo "</td>";
               echo "<td>";
               echo form_input($sm_24B);
               echo "</td>";
               echo "</tr>";
 echo "<tr>";
               echo "<td>";
               echo form_label('Other:', 'sm_other');
               echo "</td>";
               echo "<td>";
               echo form_input($sm_other);
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
<p>&nbsp;</p>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
 </html>


