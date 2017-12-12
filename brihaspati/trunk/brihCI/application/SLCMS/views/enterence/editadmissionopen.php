<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
  <head>    
    <title>Edit Admission Open</title>
        <?php $this->load->view('template/header'); ?>
      
        <?php $this->load->view('template/menu');?>
		 <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
       		 <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.js" ></script>
		 <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery.datetimepicker.css"/>				
		 <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery-ui.min.css">
                 <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.min.js" ></script>

<script>
              function program(program){
                var program = program;
                $.ajax({
                type: "POST",
                url: "<?php echo base_url();?>slcmsindex.php/enterence/addadmissionopen",
                data: {"programcategory" : program},
                dataType:"html",
                success: function(data){
                $('#programname').html(data.replace(/^"|"$/g, ''));
                }
            }); 
        }
</script>
</head>
  <body>
<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>

<script>
        function goBack() {
        window.history.back();
        }
    </script>
<table width="100%">
<tr>
<?php //echo anchor('enterence/viewadmissionopen/', "Open Admission List" ,array('title' => 'Open Admission List' , 'class' => 'top_parent'));
	 echo "<td align=\"center\" width=\"100%\">";
         echo "<b>Update Entrance Admission Announcement Details</b>";
         echo "</td>";
?>
</tr>
</table>
		<table width="100%">
		<tr><td>
                <div>
		<?php echo validation_errors('<div class="isa_warning">','</div>');?>
                <?php echo form_error('<div class="isa_error">','</div>');?>
                    <?php if(isset($_SESSION['success'])){?>
                        <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                    <?php
                    };
                    ?>
                </div>
           </td></tr>
        </table>
        <table>
	<?php

           echo form_open('enterence/editadmissionopen/' . $id);
		echo "<tr>";
                echo "<td>";
	    echo form_label('Academic Year', '');
                echo "</td>";
                echo "<td>";
                $acdy =$admop_acadyear['value'];
                echo "<select name=\"admop_acadyear\" class=\"my_dropdown\" style=\"width:100%;\">";
                echo "<option value=\"$acdy\">$acdy</option>";
                echo "<option value=\" disabled selected \">------Select Academic year------</option>";

                      for($i = 2016; $i < date("Y")+10; $i++){
                          $j=$i+1;
                      echo '<option value="'.$i.' - '.$j.'">'.$i.' - '.$j.'</option>';
                      }
               echo " </select>";
               echo "</td>";
               echo "</tr>";
		
		 echo "<tr>";
                echo "<td>";
                echo form_label('Program Category', 'admop_prgcat');
                echo "</td>";
                echo "<td>";
                echo form_input($admop_prgcat);
                echo "</td>";
                echo "</tr>";

		echo "<tr>";
                echo "<td>";
                echo form_label('Program Name', 'admop_prgname_branch');
                echo "</td>";
                echo "<td>";
                echo form_input($admop_prgname_branch);
                echo "</td>";
                echo "</tr>";
/*		
*/
		echo "<tr>";
                echo "<td>";
                echo form_label('Minimum Qualification', 'admop_min_qual');
                echo "</td>";
                echo "<td>";
                echo form_input($admop_min_qual);
                echo "</td>";
                echo "</tr>";

		 echo "<tr>";
                echo "<td>";
                echo form_label('Entrance Exam Pattern', 'admop_entexam_patt');
                echo "</td>";
                echo "<td>";
                echo form_input($admop_entexam_patt);
                echo "</td>";
                echo "</tr>";

		
	?>
	<tr>
                       <td><label for="admop_entexam_date" class="control-label">Entrance Exam Date:</label></td>
                        <td><input type="text" name="admop_entexam_date" id="admop_entexam_date" value=<?php echo $admop_entexam_date['value'];?>  class="form-control" style="width:100%"/><br>
       			<?php echo form_error('admop_entexam_date');?>
			 <script src="<?php echo base_url();?>assets/js/jquery.datetimepicker.full.js"></script>
		      <script>
                        $.datetimepicker.setLocale('en');
                        $('#admop_entexam_date').datetimepicker({
                        dayOfWeekStart : 1,
                        lang:'en',
                        formatTime:'H:i',
                        formatDate:'Y-m-d',
                        });
                        $('#admop_entexam_date').datetimepicker();
                    </script>
                </tr>

                 <tr>
                     <td><label for="admop_startdate" class="control-label">Start Date Of Online Application:</label></td>
                     <td><input type="text" name="admop_startdate" id="admop_startdate" value=<?php echo $admop_startdate['value'];?> class="form-control" style="width:100%"><br>
                     <td><?php echo form_error('admop_startdate')?></td>
		 <script src="<?php echo base_url();?>assets/js/jquery.datetimepicker.full.js"></script>

		 <script>
                        $.datetimepicker.setLocale('en');
                        $('#admop_startdate').datetimepicker({
                        dayOfWeekStart : 1,
                        lang:'en',
                        formatTime:'H:i',
                        formatDate:'Y-m-d',
                        });
                        $('#admop_startdate').datetimepicker();
                    </script>
			
	
                 </tr>

                      <tr>
                       <td><label for="admop_lastdate" class="control-label">Last Date Of Online Application:</label></td>
		       <td><input type="text" name="admop_lastdate" id="admop_lastdate"  value=<?php echo $admop_lastdate['value'];?> class="form-control" style="width:100%"/><br>
                       <td><?php echo form_error('admop_lastdate')?></td>
			 <script src="<?php echo base_url();?>assets/js/jquery.datetimepicker.full.js"></script>
             	   <script>
                        $.datetimepicker.setLocale('en');
                        $('#admop_lastdate').datetimepicker({
                        dayOfWeekStart : 1,
                        lang:'en',
                        formatTime:'H:i',
                        formatDate:'Y-m-d',
                        });
                        $('#admop_lastdate').datetimepicker();
                    </script>
                   </tr>

                <tr>
                       <td><label for="admop_app_received" class="control-label">Last Date Of Application Received :</label></td>
                       <td><input type="text" name="admop_app_received" id="admop_app_received" value=<?php echo $admop_app_received['value'];?> class="form-control" style="width:100%"/><br>
                       <td><?php echo form_error('admop_app_received')?></td>
 			<script src="<?php echo base_url();?>assets/js/jquery.datetimepicker.full.js"></script>
                   <script>
                        $.datetimepicker.setLocale('en');
                        $('#admop_app_received').datetimepicker({
                        dayOfWeekStart : 1,
                        lang:'en',
                        formatTime:'H:i',
                        formatDate:'Y-m-d',
                        });
                        $('#admop_app_received').datetimepicker();
                    </script>

                         </td>
                       </tr>
                   </tr>

<?php
	 echo "<tr>";
                echo "<td>";
                echo "<td colspan=2>";
                    echo form_hidden('admop_id', $id);
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







