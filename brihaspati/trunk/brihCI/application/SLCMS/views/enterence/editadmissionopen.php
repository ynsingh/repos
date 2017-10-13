<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
  <head>    
    <title>Edit Admission Open</title>
        <?php $this->load->view('template/header'); ?>
        <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
        <?php $this->load->view('template/menu');?>
		 <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
       		 <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.js" ></script>
		<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery.datetimepicker.css"/>
				

<script>
$(document).ready(function(){
/*
    $(".form_datetime").datetimepicker({
        format: "dd MM yyyy - hh:ii",
        autoclose: true,
        todayBtn: true,
        pickerPosition: "bottom-left"
    });*/
/*$("#StartDate").datepicker({
dateFormat: 'yy-mm-dd',
numberOfMonths: 1,
onSelect: function(selected) {
$("#EndDate").datepicker("option","minDate", selected)
}
});

$("#EndDate").datepicker({ 
dateFormat: 'yy-mm-dd',
numberOfMonths: 1,
onSelect: function(selected) {
$("#StartDate").datepicker("option","maxDate", selected)
}
});
 $("#LastDate").datepicker({ 
dateFormat: 'yy-mm-dd',
numberOfMonths: 1,
onSelect: function(selected) {
$("#StartDate").datepicker("option","maxDate", selected)
}
}); 
 
});*/
</script>

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
<script>
        function goBack() {
        window.history.back();
        }
    </script>
  <table>
   <font color=blue size=4pt>
   <div style="margin-left:2%; width:100%;">
      <br>
<div align="left">
<table style="margin-left:2%;">
<tr><td>
<?php echo anchor('enterence/viewadmissionopen/', "Admission List" ,array('title' => 'Admission List' , 'class' => 'top_parent'));?>
</td></tr>
</table>
</div>
     <style="margin-left:2%;">
            <tr><td>
                <div style="margin-left:2%;width:90%;">
	    <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery-ui.min.css">
                          <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.min.js" ></script>
<?php echo validation_errors('<div style="margin-left:2%;" class="isa_warning">','</div>');?>
                    <?php echo form_error('<div style="margin-left:2%;" class="isa_error">','</div>');?>

                    <?php if(isset($_SESSION['success'])){?>
                        <div style="margin-left:2%;" class="isa_success"><?php echo $_SESSION['success'];?></div>

                    <?php
                    };
                    ?>
                </div>
            </td></tr>
        </table>
        <table style="padding: 8px 8px  8px 50px;">
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
               echo "<td>";
               echo "</td>";
               echo "</tr>";
		
		 echo "<tr>";
                echo "<td>";
                echo form_label('Program Category', 'admop_prgcat');
                echo "</td>";
                echo "<td>";
                echo form_input($admop_prgcat);
                echo "</td>";
                echo "<td>";
                echo "</td>";
                echo "</tr>";

		echo "<tr>";
                echo "<td>";
                echo form_label('Program Name', 'admop_prgname_branch');
                echo "</td>";
                echo "<td>";
                echo form_input($admop_prgname_branch);
                echo "</td>";
                echo "<td>";
                echo "</td>";
                echo "</tr>";
		
		 echo "<tr>";
                echo "<td>";
                echo form_label('Entrance Exam Fees', 'admop_entexam_fees');
                echo "</td>";
                echo "<td>";
                echo form_input($admop_entexam_fees);
                echo "</td>";
                echo "<td>";
                echo "</td>";
                echo "</tr>";
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
                            <td><input type="text" name="admop_entexam_date" id="admop_entexam_date" class="form-control" style="width:100%"/><br>
                            <td>Date Format:yy-mm-dd</td>		   
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
                        //step 5 for give minute duration
                        $('#admop_entexam_date').datetimepicker();
                    </script>
                </tr>

                 <tr>
                     <td><label for="admop_startdate" class="control-label">Start Date Of Online Application:</label></td>
                     <td><input type="text" name="admop_startdate" id="admop_startdate" class="form-control" style="width:100%"><br>
                     <td>Date Format:yy-mm-dd</td>
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
                        //step 5 for give minute duration
                        $('#admop_startdate').datetimepicker();
                    </script>
			
	
                 </tr>

                      <tr>
                       <td><label for="admop_lastdate" class="control-label">Last Date Of Online Application:</label></td>
		       <td><input type="text" name="admop_lastdate" id="admop_lastdate" class="form-control" style="width:100%"/><br>
		       <td> Date Format:yy-mm-yy </td>
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
                        //step 5 for give minute duration
                        $('#admop_lastdate').datetimepicker();
                    </script>
                   </tr>

                <tr>
                       <td><label for="admop_app_received" class="control-label">Last Date Of Application Received :</label></td>
                       <td><input type="text" name="admop_app_received" id="admop_app_received" class="form-control" style="width:100%"/><br>
                        <td> Date Format:yy-mm-dd </td>
                       <td><?php echo form_error('admop_app_received')?></td>

                   <script>
                        $.datetimepicker.setLocale('en');
                        $('#admop_app_received').datetimepicker({
                        dayOfWeekStart : 1,
                        lang:'en',
                        formatTime:'H:i',
                        formatDate:'Y-m-d',
                        });
                        //step 5 for give minute duration
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






