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
 <script>
        function goBack() {
        window.history.back();
        }
    </script>
<?php $this->load->view('template/header'); ?>
<table style="width:100%;">
        <tr><td>
                <?php
		
                echo "<td align=\"center\" width=\"34%;\" style='font-size:18px;'>";
                echo "<b>Edit Session / Exam / Marks-Submission Dates </b>";
                echo "</td>";
                echo "<td align=\"right\" width=\"33%\">";
                $help_uri = site_url()."/help/helpdoc#ViewStudentlistwithHead";
                //echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
		echo "</td>";	
                ?>
              </td></tr>
        </table>
</br>
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



<table > 
	<form action="<?php echo site_url('setup/set_datesetedit/');echo $id; ?>" method="POST">
	<tr>
		<td align=left><label>Academic year</label></td>
		<td align=left>
			<select name="editacad_year" style="height:30px;width:100%;">
				<option value="<?php echo $acady;?>"><?php echo $acady;?></option>
				<option disabled >Select Academic year</option>
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
		<td align=left>
			<select name="editsemester" style="height:30px;width:100%;">
				<option value="<?php echo $sem;?>"><?php echo $sem;?></option>
				<option disabled >Select Semester</option>
					<option value="July Semester"><?php echo "July Semester";?></option>
					<option value="January Semester"><?php echo "January Semester";?></option>
			</select>
		</td>
	</tr>
	<!--<tr>
		<td align=left><label>Institute Name</label></td>
		<td align=left>
			<select name="editcampus_code" style="height:30px;width:100%;">
			<?php //$campusname = $this->common_model->get_listspfic1('study_center','sc_name','sc_code',$campuscode)->sc_name;
            $campusname = $this->common_model->get_listspfic1('org_profile','org_name','org_id',$campuscode)->org_name;?>
				<option value="<?php echo $campuscode;?>"><?php echo $campusname;?></option>
				<option disabled >Select Campus Name</option>
				<?php foreach($campuslist as $data){?>
					<option value="<?php echo $data->sc_code;?>"><?php echo $data->sc_name;?></option>
				<?php }?>
			</select>
		</td>-->
	</tr>
	<tr>
		<td align=left><label>Session Start Date</label></td>
		<td align=left>
		
        	<input type="text" name="editsession_sdate" placeholder="Session Start Date" value="<?php echo $session_sdate; ?>" id="sdate" style="height:20px;font-size:18px;" size=20 />
        
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
		<input type="text" name="editsession_edate" placeholder="Session End Date" value="<?php echo $session_edate; ?>" id="edate" style="height:20px;font-size:18px;" size=20 />
        
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
	<input type="text" name="editexam_startdate" placeholder="Exam Start Date" value="<?php echo $exam_sdate; ?>" id="examsdate" style="height:20px;font-size:18px;" size=20 />
        
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
			<input type="text" name="editexam_enddate" placeholder="Exam End Date" value="<?php echo $exam_edate; ?>" id="examedate" style="height:20px;font-size:18px;" size=20 />
        
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
        		<input type="text" name="editform_sdate" placeholder="Form Submission Start Date" value="<?php echo $marks_sdate; ?>" id="formsdate" style="height:20px;font-size:18px;" size=20 />
        
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
        		<input type="text" name="editform_edate" placeholder="Form Submission End Date" value="<?php echo $marks_edate; ?>" id="formedate" style="height:20px;font-size:18px;" size=20 />
        
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
	<tr><td></td>
		<td align=left><input type="submit" name="editdates" value="Submit" style="height:30px;font-size:18px;">
</form>
		<input type="submit" name="Back" value="Back" onclick="goBack()" style="height:30px;font-size:18px;">		
		</td>
	</tr>
</table>


<?php $this->load->view('template/footer'); ?>
</body>
</html>
