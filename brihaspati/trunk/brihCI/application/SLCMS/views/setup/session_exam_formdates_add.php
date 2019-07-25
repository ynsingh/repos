<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?><!DOCTYPE html>
<html>
<head>
<title>Session/Exam/Marks Submission Dates</title>
<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/message.css">
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>	
<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery-ui.min.css">
  				<script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.min.js" ></script>		
</head>

<body>
<?php $this->load->view('template/header'); ?>
<table style="width:100%;">
        <tr><td>
                <?php
		echo "<td align=\"left\" width=\"33%\" style='font-size:18px;'>";
                echo anchor('setup/set_datesview/', "View Session / Exam / Marks-Submission Dates", array('title' => 'Session / Exam / Marks-Submission Dates' , 'class' => 'top_parent'));
		echo "</td>";
                echo "<td align=\"center\" width=\"34%;\" style='font-size:18px;'>";
                echo "<b>Add Session / Exam / Marks-Submission Dates </b>";
                echo "</td>";
                echo "<td align=\"right\" width=\"33%\">";
                $help_uri = site_url()."/help/helpdoc#ViewStudentlistwithHead";
                //echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
		echo "</td>";	
                ?>
              </td></tr>
        </table>
<?php echo validation_errors('<div class="isa_warning">','</div>');?>
        <?php echo form_error('<div class="">','</div>');?>
        <?php 
	    if(!empty($_SESSION['success'])){	
		if(isset($_SESSION['success'])){?>
         <div class="isa_success" style="font-size:18px;"><?php echo $_SESSION['success'];?></div>
         <?php
          } };
         ?>
        <?php 
	   if(!empty($_SESSION['err_message'])){		
		if(isset($_SESSION['err_message'])){?>
        <div class="isa_error"><div ><?php echo $_SESSION['err_message'];?></div></div>
        <?php
         } };
	?>  


<form action="<?php echo site_url('setup/set_datesadd'); ?>" method="POST">
<table > 
	<tr>
		<td align=left><label>Academic year</label></td>
		<td>
			<select name="acad_year" style="height:30px;width:100%;">
				<option disabled selected>Select Academic Year</option>
					<option value="<?php echo $currentacadyear;?>"><?php echo $currentacadyear;?></option>
					<option value="2019-2020">2019-2020</option>
					<option value="2020-2021">2020-2021</option>
					<option value="2022-2023">2022-2023</option>
					<option value="2024-2025">2024-2025</option>
			</select>
		</td>
	</tr>
	<tr>
		<td align=left><label>Semester</label></td>
		<td>
			<select name="semester" style="height:30px;width:100%;">
				<option disabled selected>Select Semester</option>
				
					<option value="July Semester"><?php echo "July Semester";?></option>
					<option value="January Semester"><?php echo "January Semester";?></option>
			</select>
		</td>
	</tr>
	<tr>
		<td align=left><label>Campus Name</label></td>
		<td>
			<select name="campus_code" style="height:30px;width:100%;">
				<option disabled selected>Select Campus</option>
				<?php foreach($campuslist as $data){?>
					<option value="<?php echo $data->sc_code;?>"><?php echo $data->sc_name;?></option>
				<?php }?>
			</select>
		</td>
	</tr>
	<tr>
		<td align=left><label>Session Start Date</label></td>
		<td>
		
        			<input type="text" name="session_sdate" placeholder="Session Start Date" value="<?php echo isset($_POST["session_sdate"]) ? $_POST["session_sdate"] : ''; ?>" id="sdate" style="height:20px;font-size:18px;" size=20 />
        
            			<script>
               				 $('#sdate').datepicker({
                			 onSelect: function(value, ui) {
                    			 	console.log(ui.selectedYear)
                    			 	var today = new Date(), 
                    			 	dob = new Date(value), 
                   				age = 2017-ui.selectedYear;
			                 },
                
                    			changeMonth: true,
                    			changeYear: true,
                    			dateFormat: 'yy-mm-dd',
                   
                   			yearRange: 'c-36:c+10',
                			});
            			</script>

		</td>
	<tr>
	<tr>
		<td align=left><label>Session End Date</label></td>
		<td align=left>
		<input type="text" name="session_edate" placeholder="Session End Date" value="<?php echo isset($_POST["session_edate"]) ? $_POST["session_edate"] : ''; ?>" id="edate" style="height:20px;font-size:18px;" size=20 />
        
            			<script>
               				 $('#edate').datepicker({
                			 onSelect: function(value, ui) {
                    			 	console.log(ui.selectedYear)
                    			 	var today = new Date(), 
                    			 	dob = new Date(value), 
                   				age = 2017-ui.selectedYear;
			                 },
                
                    			changeMonth: true,
                    			changeYear: true,
                    			dateFormat: 'yy-mm-dd',
                   
                   			 yearRange: 'c-36:c+10',
                			});
            			</script>

		</td>
	</tr>

	<tr>
		<td align=left><label>Exam Start Date </label></td>
		<td align=left>
	<input type="text" name="exam_startdate" placeholder="Exam Start Date" value="<?php echo isset($_POST["exam_startdate"]) ? $_POST["exam_startdate"] : ''; ?>" id="examsdate" style="height:20px;font-size:18px;" size=20 />
        
            			<script>
               				 $('#examsdate').datepicker({
                			 onSelect: function(value, ui) {
                    			 	console.log(ui.selectedYear)
                    			 	var today = new Date(), 
                    			 	dob = new Date(value), 
                   				age = 2017-ui.selectedYear;
			                 },
                
                    			changeMonth: true,
                    			changeYear: true,
                    			dateFormat: 'yy-mm-dd',
                   
                   			yearRange: 'c-36:c+10',
                			});
            			</script>
		</td>
	</tr>

	<tr>
		<td align=left><label>Exam End Date  </label></td>
		<td align=left>
			<input type="text" name="exam_enddate" placeholder="Exam End Date" value="<?php echo isset($_POST["exam_enddate"]) ? $_POST["exam_enddate"] : ''; ?>" id="examedate" style="height:20px;font-size:18px;" size=20 />
        
            			<script>
               				 $('#examedate').datepicker({
                			 onSelect: function(value, ui) {
                    			 	console.log(ui.selectedYear)
                    			 	var today = new Date(), 
                    			 	dob = new Date(value), 
                   				age = 2017-ui.selectedYear;
			                 },
                
                    			changeMonth: true,
                    			changeYear: true,
                    			dateFormat: 'yy-mm-dd',
                   
                   			 yearRange: 'c-36:c+10',
                			});
            			</script>
		</td>
	</tr>	

	<tr>
		<td align=left><label>Marks Submission Start Date : </label></td>
		<td align=left>
        		<input type="text" name="form_sdate" placeholder="Form Submission Start Date" value="<?php echo isset($_POST["form_sdate"]) ? $_POST["form_sdate"] : ''; ?>" id="formsdate" style="height:20px;font-size:18px;" size=20 />
        
            			<script>
               				 $('#formsdate').datepicker({
                			 onSelect: function(value, ui) {
                    			 	console.log(ui.selectedYear)
                    			 	var today = new Date(), 
                    			 	dob = new Date(value), 
                   				age = 2017-ui.selectedYear;
			                 },
                
                    			changeMonth: true,
                    			changeYear: true,
                    			dateFormat: 'yy-mm-dd',
                   
                   			yearRange: 'c-36:c+10',
                			});
            			</script>
		</td>	
	</tr>

		<tr>
		<td align=left><label>Marks Submission End Date : </label></td>
		<td align=left>
        		<input type="text" name="form_edate" placeholder="Form Submission End Date" value="<?php echo isset($_POST["form_edate"]) ? $_POST["form_edate"] : ''; ?>" id="formedate" style="height:20px;font-size:18px;" size=20 />
        
            			<script>
               				 $('#formedate').datepicker({
                			 onSelect: function(value, ui) {
                    			 	console.log(ui.selectedYear)
                    			 	var today = new Date(), 
                    			 	dob = new Date(value), 
                   				age = 2017-ui.selectedYear;
			                 },
                
                    			changeMonth: true,
                    			changeYear: true,
                    			dateFormat: 'yy-mm-dd',
                   
                   			yearRange: 'c-36:c+10',
                			});
            			</script>
		</td>	
	</tr>
<tr height=20></tr>
	<tr>
		<td></td>
		<td align=left><input type="submit" name="adddates" value="Submit" style="height:30px;font-size:18px;">
		<input type="reset" name="cancel" value="Cancel" style="height:30px;font-size:18px;"></td>
	</tr>
</table>
</form>

<?php $this->load->view('template/footer'); ?>
</body>
</html>
