<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!-- editentranceexamfees.php 
  @author Neha Khullar  (vijay.pal428@gmail.com)
 -->
 <html>
   <head>    
   <title>Edit Entrance Exam Fees</title>
        <?php $this->load->view('template/header'); ?>
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
           <tr><td>
                <?php //echo anchor('setup/viewentranceexamfees/', " Edit Entrance Exam Fees" ,array('title' => ' Entrance Exam Fees Configuration Detail ' , 'class' => 'top_parent'));?>
                    <div>
                    <?php echo validation_errors('<div  class="isa_warning">','</div>');?>
                    <?php echo form_error('<div  class="isa_error">','</div>');?>

                    <?php if(isset($_SESSION['success'])){?>
                        <div  class="isa_success"><?php echo $_SESSION['success'];?></div>

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
<div>
<table>
<tr><td>
<?php echo anchor('setup/viewentranceexamfees/', "Edit Entrance Exam Fees" ,array('title' => 'Edit Fees' , 'class' => 'top_parent'));?>
</td></tr>
</table>
</div>

        <table>
    
        <?php

           echo form_open('setup/editentranceexamfees/' . $id);
                echo "<tr>";
                echo "<td>";
                echo form_label('Fees Name', 'aseefc_feename');
                echo "</td>";
                echo "<td>";
                echo form_input($aseefc_feename);
                echo "</td>";
                echo "</tr>";
                echo "<tr>";
                echo "<td>";
?>
 <tr>
                <td>Category</td>
                 <td><select name="aseefc_category" class="my_dropdown" style="width:100%;">
		<option value="<?php echo $aseefc_category['value'];?>"><?php echo$this->common_model->get_listspfic1('category','cat_name','cat_id',$aseefc_category['value'])->cat_name;?></option>;
                <?php foreach($this->entfeeresult as $datas): ?>
                   <option value="<?php echo $datas->cat_id; ?>"><?php echo $datas->cat_name; ?></option>
                   <?php endforeach; ?>
                </td>
                <td><?php echo form_error('aseefc_category')?></td>
               </td>
            </tr>
<?php
               echo "<tr>";
               echo "<td>";
               echo form_label('Gender','aseefc_gender');
               echo "</td>";
               echo "<td>";
                $gen=$aseefc_gender['value'];
                    echo "<select name=\"aseefc_gender\"class=\"my_dropdown\" style=\"width:100%;\">";
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
                echo form_label('Amount', 'aseefc_amount');
                echo "</td>";
                echo "<td>";
                echo form_input($aseefc_amount);
                echo "</td>";
                echo "</tr>";


                echo "<tr>";
                echo "<td>";
                echo "<td colspan=2>";
                    echo form_hidden('aseefc_id', $id);
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
                                                      
             
