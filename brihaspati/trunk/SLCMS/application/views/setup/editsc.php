<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name editsc.php 
  @author Rekha Devi Pal(rekha20july@gmail.com)
 -->

<html>
  <head>    
    <title>Edit Department</title>
        <?php $this->load->view('template/header'); ?>
        <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
        <?php $this->load->view('template/menu');?>

                                <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/stylecal.css">
                                  <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery-ui.css">
                                  <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-1.12.4.js" ></script>
                                  <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.js" ></script>
                                 <script>
                                $( function() {
                                $( "#datepicker" ).datepicker({dateFormat: 'yy/mm/dd'});
                                } );
                                </script>
                              <script>
                                $( function() {
                                $( "#datepicker1" ).datepicker({dateFormat: 'yy/mm/dd'});
                                } );
                              </script>

    </head>
    <body>
<script>
        function goBack() {
        window.history.back();
        }
    </script>
  <table>
   <font color=blue size=4pt>
   <div style="margin-left:30px; width:200px;">
      <br>
<div align="left">
<table style="margin-left:10px;">
<tr><td>
<?php echo anchor('setup/viewsc/', "Study Center List" ,array('title' => 'Study Center List' , 'class' => 'top_parent'));?>
</td></tr>
</table>
</div>


     <style="margin-left:0px;">
            <tr colspan="2"><td>
                <div style="margin-left:0px;width:1700px;">
                    <?php echo validation_errors('<div style="margin-left:50px;" class="isa_warning">','</div>');?>
                    <?php echo form_error('<div style="margin-left:30px;" class="isa_error">','</div>');?>

                    <?php if(isset($_SESSION['success'])){?>
                        <div style="margin-left:30px;" class="isa_success"><?php echo $_SESSION['success'];?></div>

                    <?php
                    };
                    ?>
                </div>
            </td></tr>
        </table>
        <table style="padding: 8px 8px 8px 50px;">

        <?php

           echo form_open('setup/editsc/' . $id);
                echo "<tr>";
                echo "<td>";
                echo form_label('University Name', 'orgprofile');
                echo "</td>";
                echo "<td>";
                echo form_input($orgprofile);
                echo "</td>";
                echo "<td>";
                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td>";
                echo form_label('Campus Code', 'institutecode');
                echo "</td>";
                echo "<td>";
                echo form_input($institutecode);
                echo "</td>";
                echo "<td>";
                echo "</td>";
                echo "</tr>";

               echo "<tr>";
                echo "<td>";
                echo form_label('Campus Name', 'name');
                echo "</td>";
                echo "<td>";
                echo form_input($name);
                echo "</td>";
                echo "<td>";
                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td>";
                echo form_label('Campus Nickname', 'nickname');
                echo "</td>";
                echo "<td>";
                echo form_input($nickname);
                echo "</td>";
                echo "<td>";
                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td>";
                 echo form_label('Address', 'address');
                echo "</td>";
                echo "<td>";
                echo form_input($address);
                echo "</td>";
                echo "<td>";
                echo "</td>";
                echo "</tr>";
                
                echo "<tr>";
                echo "<td>";
                echo form_label('Country', 'country');
                echo "</td>";
                echo "<td>";
                echo form_input($country);
                echo "</td>";
                echo "<td>";
                echo "</td>";
                echo "</tr>";
                
                echo "<tr>";
                echo "<td>";
                echo form_label('State', 'state');
                echo "</td>";
                echo "<td>";
                echo form_input($state);
                echo "</td>";
                echo "<td>";
                echo "</td>";
                echo "</tr>";

               echo "<tr>";
                echo "<td>";
                echo form_label('City', 'city');
                echo "</td>";
                echo "<td>";
                echo form_input($city);
                echo "</td>";
                echo "<td>";
                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td>";
                echo form_label('District', 'district');
                echo "</td>";
                echo "<td>";
                echo form_input($district);
                echo "</td>";
                echo "<td>";
                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td>";
                echo form_label('Pincode', 'pincode');
                echo "</td>";
                echo "<td>";
                echo form_input($pincode);
                echo "</td>";
                echo "<td>";
                echo "</td>";
                echo "</tr>";
                
              echo "<tr>";
                echo "<td>";
                echo form_label('Phone', 'phone');
                echo "</td>";
                echo "<td>";
                echo form_input($phone);
                echo "</td>";
                echo "<td>";
                echo "</td>";
                echo "</tr>";

               echo "<tr>";
                echo "<td>";
                echo form_label('Fax', 'fax');
                echo "</td>";
                echo "<td>";
                echo form_input($fax);
                echo "</td>";
                echo "<td>";
                echo "</td>";
                echo "</tr>";
               
                echo "<tr>";
                echo "<td>";
                echo form_label('Status', 'status');
                echo "</td>";
                echo "<td>";
                echo form_input($status);
                echo "</td>";
                echo "<td>";
                echo "</td>";
                echo "</tr>";
                 
          ?>

 <tr>
   <td><label for="startdate" class="control-label">Start Date:</label></td>
   <td><input type="text" name="startdate" id="datepicker" value=<?php echo $startdate['value'];?> class="form-control" size="40" /><br>
   <td><?php echo form_error('startdate')?></td>
   </td>
   </tr>
   <tr>
   <td><label for="closedate" class="control-label">Close Date:</label></td>
   <td><input type="text" name="closedate" id="datepicker1"  value=<?php echo $closedate['value'];?> class="form-control" size="40" /><br>
   <td><?php echo form_error('closedate')?></td>
   </td>
   </tr> 


<?php

               echo "<tr>";
                echo "<td>";
                echo form_label('Website', 'website');
                echo "</td>";
                echo "<td>";
                echo form_input($website);
                echo "</td>";
                echo "<td>";
                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td>";
                echo form_label('Incharge', 'incharge');
                echo "</td>";
                echo "<td>";
                echo form_input($incharge);
                echo "</td>";
                echo "<td>";
                echo "</td>";
                echo "</tr>";
                                  
               echo "<tr>";
                echo "<td>";
               echo form_label('Mobile', 'mobile');
                echo "</td>";
                echo "<td>";
                echo form_input($mobile);
                echo "</td>";
                echo "<td>";
                echo "</td>";
                echo "</tr>";

            
            echo "<tr>";
                   echo "<td>";
                   echo "</td>";
                   echo "<td>";
                   echo form_hidden('id', $id);
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

