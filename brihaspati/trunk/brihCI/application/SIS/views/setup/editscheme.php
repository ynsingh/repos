<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name editscheme.php 
  @author Om Prakash(omprakashkgp@gmail.com)
 -->

<html>
<title>Edit Scheme</title>
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
            <tr colspan="2"><td>
            <?php
                echo anchor('setup/displayscheme', 'Scheme List', array('class' => 'top_parent'));
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
            echo form_open('setup/editscheme/' . $sd_id);
       
            echo "<tr>";
                echo "<td>";
                    echo form_label('Department Name', 'sd_deptid');
                echo "</td>";
                echo "<td><select name=\"sd_deptid\" class=\"my_dropdown\" style=\"width:100%;\">";

                echo "<option value=\"$sd_deptid[value]\">$sd_deptid[value]</option>"; ?>
                <?php foreach($this->deptresult as $datas): ?>
                  <option value="<?php echo $datas->dept_name; ?>"><?php echo $datas->dept_name; ?></option>
                <?php endforeach; ?>
                </select></td>
               </td>
               </tr>
             <?php
                echo "<td>";
                echo "</td>";
            echo "</tr>";

            echo "<tr>";
                echo "<td>";
                    echo form_label('Scheme Name', 'sname');
                echo "</td>";
                echo "<td>";
                    echo form_input($sname);
                echo "</td>";
                echo "<td>";
                echo "</td>";
            echo "</tr>";

            //echo "<p>";
            echo "<tr>";
                echo "<td>";
                   echo form_label('Scheme Code', 'scode');
                    //echo "<br />";
                echo "</td>";
                echo "<td>";
                    echo form_input($scode);
                echo "</td>";
                echo "<td>";
                   
                echo "</td>";
            echo "</tr>";
        
            echo "<tr>";
                echo "<td>";
                    echo form_label('Scheme Short Name', 'ssname');
                //echo "<br />";
                echo "</td>";
                echo "<td>";
                    echo form_input($ssname);
                echo "</td>";
                echo "<td>";
                echo "</td>";
            echo "</tr>";
            
            echo "<tr>";
                echo "<td>";
                    echo form_label('Scheme Description', 'sdesc');
                echo "</td>";
                echo "<td>";
                    //echo "<br />";
                    echo form_input($sdesc);
                echo "</td>";
                echo "<td>";
                    
                echo "</td>";
            echo "</tr>";
            //echo "</p>";
        
            // echo "<p>";
        
            echo "<tr>";
		echo "<td></td>";
                echo "<td>";
                    echo form_hidden('sd_id', $sd_id);
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

