<!-------------------------------------------------------
    -- @name step_two.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
--------------------------------------------------------->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');


?><!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>IGNTU:Student Detail</title>
	<link rel="shortcut icon" href="<?php echo base_url('assets/images'); ?>/index.jpg">
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/message.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/studentNavbar.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery-ui.min.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.min.js" ></script>

<style type="text/css">
label{font-size:18px;}
input[type='text']{font-size:17px;height:30px;background-color:white;}
input[type='email']{font-size:17px;height:30px;background-color:white;}


tr td{font-size:15px;}
tr th{background:#38B0DE;color:white;font-weight:bold;}
select{width:100%;font-size:17px;height:30px;font-weight:bold;}

</style>


</head>
<body>


<div>
	<div id="body">
	<?php  // $thisPage2="studentaddDetail"; 
		$this->load->view('template/header'); ?>
	<nav> 	<h1>Welcome to IGNTU  </h1></nav></br>
	<?php $this->load->view('enterence/admission_steps');?>
<!--------------------------------------------------------ERROR DISPLAY-------------------------------------------------------------->

<?php
echo "<center>";

	if($this->session->flashdata('msg')){
echo "<div style='font-size:20px;text-align:center;background-color:#DFF2BF;width:50%;height:30px;color:green;'>";
	echo $this->session->flashdata('msg');
echo "<div>";	
}

echo "</center>";
	if((isset($_SESSION['success'])) && ($_SESSION['success'])!=''){
		//echo "<div style=\"margin-left:30px;width:1700px;align:left;font-size:18px;height:10px;\" class=\"isa_success\">";
	echo "<table style=\"margin-left:30px;width:1700px;font-size:18px;height:10px;border:1px solid white;\" class=\"isa_success\">";			
		echo "<tr>";
			echo "<td style='font-size:18px;float:left;'>";
				echo $_SESSION['success'];
			echo "</td>";
		echo "<tr>";
		//echo "</div>";
	echo "</table>";
	}
	if((isset($_SESSION['error'])) && (($_SESSION['error'])!='')){
		//echo "<div id='error'>";
		//echo '<div style="margin-left:40px;">'.$_SESSION['error'].'</div>';
		//echo "</div>";
	echo "<table id='error'>";			
		echo "<tr>";
			echo "<td style='font-size:18px;'>";
				echo $_SESSION['error'];
			echo "</td>";
		echo "<tr>";
		//echo "</div>";
	echo "</table>";
	}
?>   
	<div align="left" style="margin-left:30px;width:1700px;font-size:18px;">
        <?php echo validation_errors('<div class="isa_warning">','</div>');?>
        <?php echo form_error('<div style="margin-left:30px;" class="">','</div>');?>
        <?php if(isset($_SESSION[''])){?>
        <div class="alert alert-success"><?php echo $_SESSION[''];?></div>
        <?php
    	 };
       	?>
	
        <?php if(isset($_SESSION['err_message'])){?>
             <div class="isa_error" style='margin-left:30px;width:1680px;font-size:18px;'><div ><?php echo $_SESSION['err_message'];?></div></div>
        <?php
        };
	?>  
   </div>
	</br> 
	<?php //$this->load->view('student/stuStepshead');?>
	</br> 
<center>
	  <table  style="background-color:#f1f1f1;" align="">

<center>
<form action="<?php echo site_url('enterence/step_two'); ?>"  method="POST">

	
	<table style="margin-top:20px;width:80%;border:2px solid black;">

		<thead>
			<th colspan=11 style="margin-left:120px;background-color:#7e7e7e;color:white;font-size:22px;">Education Details</th>
		</thead>

		<thead style="font-size:20px;">
			<th>No.</th><th>Programmes</th><th>Course</th><th>Name of the institution</th><th>Board or university</th><th>Subjects/Specialization</th>
<th>Year of passing</th><th>Marks obtained</th><th>Max. marks</th><th>% of Marks</th><th>Passed or Appeard</th>
		</thead>
		
		<tbody>
			
		<tr>	
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;">1</td>
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;">10th</td>
			<td><input type="text"  placeholder="Highschool" name="Hcname" style="" value="<?php echo isset($_POST["Hcname"]) ? $_POST["Hcname"] : ''; ?>"></td>
			<td><input type="text" placeholder="Institute name" name="Hinstitute" style=" " value="<?php echo isset($_POST["Hinstitute"]) ? $_POST["Hinstitute"] : ''; ?>"></td>
			<td><input type="text" placeholder="Bord/University" name="Hboard" style=" " value="<?php echo isset($_POST["Hboard"]) ? $_POST["Hboard"] : ''; ?>"></td>
			<td><input type="text" placeholder="Subjects" name="Hsubject" style=" " value="<?php echo isset($_POST["Hsubject"]) ? $_POST["Hsubject"] : ''; ?>"></td>
			<td><input type="text" placeholder="Year" name="Hyear" style=" width:90%;" value="<?php echo isset($_POST["Hyear"]) ? $_POST["Hyear"] : ''; ?>"></td>
			
			<td><input type="text" placeholder="Marks obtained" name="Hmobtain" style="width:90%;" value="<?php echo isset($_POST["Hmobtain"]) ? $_POST["Hmobtain"] : ''; ?>"></td>
			<td><input type="text" placeholder="Max Marks" name="Hmmarks" style=" width:90%;" value="<?php echo isset($_POST["Hmmarks"]) ? $_POST["Hmmarks"] : ''; ?>"></td>
			<td><input type="text" placeholder="%" name="Hpercentage" style="width:90%; " value="<?php echo isset($_POST["Hpercentage"]) ? $_POST["Hpercentage"] : ''; ?>"></td>
			<td><input type="text" placeholder="Passed/Appeared" name="Hpassed" style="" value="<?php echo isset($_POST["Hpassed"]) ? $_POST["Hpassed"] : ''; ?>"></td>
			
			
		</tr>
		<tr>	
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;">2</td>
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;">12th</td>
			<td><input type="text" placeholder="Intermediate" name="Icname" style=" " value="<?php echo isset($_POST["Ipassed"]) ? $_POST["Ipassed"] : ''; ?>"></td>
			<td><input type="text" placeholder="Institute name" name="Iinstitute" style=" " value="<?php echo isset($_POST["Iinstitute"]) ? $_POST["Iinstitute"] : ''; ?>"></td>
			<td><input type="text" placeholder="Bord/University" name="Iboard" style=" " value="<?php echo isset($_POST["Iboard"]) ? $_POST["Iboard"] : ''; ?>"></td>
			<td><input type="text" placeholder="Subjects" name="Isubject" style=" " value="<?php echo isset($_POST["Isubject"]) ? $_POST["Isubject"] : ''; ?>"></td>
			<td><input type="text" placeholder="Year" name="Iyear" style=" width:90%;" value="<?php echo isset($_POST["Iyear"]) ? $_POST["Iyear"] : ''; ?>"></td>
			
			<td><input type="text"  placeholder="Marks obtained" name="Imobtain" style="width:90%;" value="<?php echo isset($_POST["Imobtain"]) ? $_POST["Imobtain"] : ''; ?>"></td>
			<td><input type="text" placeholder="Max Marks" name="Immarks" style=" width:90%;" value="<?php echo isset($_POST["Immarks"]) ? $_POST["Immarks"] : ''; ?>"></td>
			<td><input type="text"  placeholder="%" name="Ipercentage" style="width:90%; " value="<?php echo isset($_POST["Ipercentage"]) ? $_POST["Ipercentage"] : ''; ?>"></td>
			<td><input type="text" placeholder="Passed/Appeared" name="Ipassed" style=" " value="<?php echo isset($_POST["Ipassed"]) ? $_POST["Ipassed"] : ''; ?>"></td>
			
		</tr>
		<tr>	
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;">3</td>
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;">Graduation</td>
			<td><input type="text" placeholder="Course Name" name="Gcname" style=" " value="<?php echo isset($_POST["Gcname"]) ? $_POST["Gcname"] : ''; ?>"></td>
			<td><input type="text"  placeholder="Institute name" name="Ginstitute" style=" " value="<?php echo isset($_POST["Ginstitute"]) ? $_POST["Ginstitute"] : ''; ?>"></td>
			<td><input type="text"  placeholder="Bord/University" name="Gboard" style=" " value="<?php echo isset($_POST["Gboard"]) ? $_POST["Gboard"] : ''; ?>"></td>
			<td><input type="text"  placeholder="Subjects" name="Gsubject" style=" " value="<?php echo isset($_POST["Gsubject"]) ? $_POST["Gsubject"] : ''; ?>"></td>
			<td><input type="text"  placeholder="Year" name="Gyear" style="width:90%; " value="<?php echo isset($_POST["Gyear"]) ? $_POST["Gyear"] : ''; ?>"></td>

			<td><input type="text"  placeholder="Marks obtained" name="Gmobtain" style="width:90%;" value="<?php echo isset($_POST["Gmobtain"]) ? $_POST["Gmobtain"] : ''; ?>"></td>
			<td><input type="text"  placeholder="Max Marks" name="Gmmarks" style=" width:90%;" value="<?php echo isset($_POST["Gmmarks"]) ? $_POST["Gmmarks"] : ''; ?>"></td>
			<td><input type="text"  placeholder="%" name="Gpercentage" style="width:90%; " value="<?php echo isset($_POST["Gpercentage"]) ? $_POST["Gpercentage"] : ''; ?>"></td>
			<td><input type="text"  placeholder="Passed/Appeared" name="Gpassed" style=" " value="<?php echo isset($_POST["Gpassed"]) ? $_POST["Gpassed"] : ''; ?>"></td>
		</tr>
		<tr>	
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;">4</td>
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;">Post Graduation</td>
			<td><input type="text"  placeholder="Course Name" name="Pcname" style=" " value="<?php echo isset($_POST["Pcname"]) ? $_POST["Pcname"] : ''; ?>"></td>
			<td><input type="text"  placeholder="Institute name" name="Pinstitute" style=" " value="<?php echo isset($_POST["Pinstitute"]) ? $_POST["Pinstitute"] : ''; ?>"></td>
			<td><input type="text"  placeholder="Bord/University" name="Pboard" style=" " value="<?php echo isset($_POST["Pboard"]) ? $_POST["Pboard"] : ''; ?>"></td>
			<td><input type="text"  placeholder="Subjects" name="Psubject" style=" " value="<?php echo isset($_POST["Psubject"]) ? $_POST["Psubject"] : ''; ?>"></td>
			<td><input type="text"  placeholder="Year" name="Pyear" style="width:90%; " value="<?php echo isset($_POST["Pyear"]) ? $_POST["Pyear"] : ''; ?>"></td>

			<td><input type="text"  placeholder="Marks obtained" name="Pmobtain" style="width:90%;" value="<?php echo isset($_POST["Pmobtain"]) ? $_POST["Pmobtain"] : ''; ?>"></td>
			<td><input type="text"  placeholder="Max Marks" name="Pmmarks" style=" width:90%;" value="<?php echo isset($_POST["Pmmarks"]) ? $_POST["Pmmarks"] : ''; ?>"></td>
			<td><input type="text"  placeholder="%" name="Ppercentage" style="width:90%; " value="<?php echo isset($_POST["Ppercentage"]) ? $_POST["Ppercentage"] : ''; ?>"></td>
			<td><input type="text"  placeholder="Passed/Appeared" name="Ppassed" style=" " value="<?php echo isset($_POST["Ppassed"]) ? $_POST["Ppassed"] : ''; ?>"></td>
		</tr>
		<tr>	
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;">5</td>
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;">Any Other</td>
			<td><input type="text"  placeholder="Course Name" name="Acname" style=" " value="<?php echo isset($_POST["Acname"]) ? $_POST["Acname"] : ''; ?>"></td>
			<td><input type="text"  placeholder="Institute name" name="Ainstitute" style=" " value="<?php echo isset($_POST["Ainstitute"]) ? $_POST["Ainstitute"] : ''; ?>"></td>
			<td><input type="text"  placeholder="Bord/University" name="Aboard" style=" " value="<?php echo isset($_POST["Aboard"]) ? $_POST["Aboard"] : ''; ?>"></td>
			<td><input type="text"  placeholder="Subjects" name="Asubject" style=" " value="<?php echo isset($_POST["Asubject"]) ? $_POST["Asubject"] : ''; ?>"></td>
			<td><input type="text"  placeholder="Year" name="Ayear" style=" width:90%;" value="<?php echo isset($_POST["Ayear"]) ? $_POST["Ayear"] : ''; ?>"></td>

			<td><input type="text"  placeholder="Marks obtained" name="Amobtain" style="width:90%;" value="<?php echo isset($_POST["Amobtain"]) ? $_POST["Amobtain"] : ''; ?>"></td>
			<td><input type="text"  placeholder="Max M." name="Ammarks" style=" width:90%;" value="<?php echo isset($_POST["Ammarks"]) ? $_POST["Ammarks"] : ''; ?>"></td>
			<td><input type="text"  placeholder="%" name="Apercentage" style="width:90%; " value="<?php echo isset($_POST["Apercentage"]) ? $_POST["Apercentage"] : ''; ?>"></td>
			<td><input type="text"  placeholder="Passed/Appeared" name="Apassed" style=" " value="<?php echo isset($_POST["Apassed"]) ? $_POST["Apassed"] : ''; ?>"></td>
		</tr>
		<!--<tr >
		<td colspan=11 style="background:#38B0DE;color:white;font-weight:bold;">	
			<label>Are You UGC-NET/UGC-JRF/CSIR-NET/CSIR-JRF/SET/SLET</label>
			<select name="eduugc_net" style="height:37px;font-size:18px;font-weight:bold;width:10%;">
				<option selected="true" disabled>Select Y/N</option>
				<option value="Yes">Yes</option>	
				<option value="No">No</option>
			</select>
		</td>
	</tr>-->
		</tbody>
		</table>
	<!---------------------------------Enterance exam file add--------------------------------------->
		<?php $this->load->view('enterence/entrance_exam');?>	
		
	<table style="margin-top:50px;width:80%;border:2px solid black;">
		<thead>
			<th colspan=11 style="margin-left:120px;background-color:#7e7e7e;color:white;font-size:22px;">Details if any employment</th>
		</thead>
		<thead style="font-size:20px;">
			<tr><th>Name of the university/institute</th><th>Name of post</th><th>Present pay & grade</th><th>Nature of appointment</th>
			<th>Date of joining</th><th>Remarks</th><th>Previous experience if any</th>
			</tr>
		</thead>
	<tbody>

		<tr>
			<td><input type="text" placeholder="Univerity" name="eduuni11" style="" value="<?php echo isset($_POST["eduuni11"]) ? $_POST["eduuni11"] : ''; ?>"></td>
			<td><input type="text" placeholder="Post" name="edupost12" style="" value="<?php echo isset($_POST["edupost12"]) ? $_POST["edupost12"] : ''; ?>"></td>
			<td><input type="text" placeholder="Prsent/Grade pay" name="edupay13" style="" value="<?php echo isset($_POST["edupay13"]) ? $_POST["edupay13"] : ''; ?>"></td>
			<td><input type="text" placeholder="Appointment" name="eduappoint14" style="" value="<?php echo isset($_POST["eduappoint14"]) ? $_POST["eduappoint14"] : ''; ?>"></td>
			<td><input type="text" id="doj" placeholder="Joining date" name="edujoin15" style="" value="<?php echo isset($_POST["edujoin15"]) ? $_POST["edujoin15"] : ''; ?>">
			<script>
				$('#doj').datepicker({
 				onSelect: function(value, ui) {
 			        console.log(ui.selectedYear)
       				var today = new Date(), 
         			dob = new Date(value), 
          			age = 2017-ui.selectedYear;

   				//$("#age").text(age);
   				},
  	 			//(set for show current month or current date)maxDate: '+0d',
				
    				changeMonth: true,
    				changeYear: true,
    				dateFormat: 'yy-mm-dd',
     				defaultDate: '1yr',
    				yearRange: 'c-37:c+30',
				});
			</script>	
			
			</td>
			<td><input type="text" placeholder="Remarks" name="eduremark16" style="" value="<?php echo isset($_POST["eduremark16"]) ? $_POST["eduremark16"] : ''; ?>"></td>
			<td><input type="text" placeholder="Previous experience" name="eduexpie17" style="" value="<?php echo isset($_POST["eduexpie17"]) ? $_POST["eduexpie17"] : ''; ?>"></td>
		</tr>	

		<tr>
			<td><input type="text" placeholder="Univerity" name="eduuni21" style="" value="<?php echo isset($_POST["eduuni21"]) ? $_POST["eduuni21"] : ''; ?>"></td>
			<td><input type="text" placeholder="Post" name="edupost22" style="" value="<?php echo isset($_POST["edupost22"]) ? $_POST["edupost22"] : ''; ?>"></td>
			<td><input type="text" placeholder="Prsent/Grade pay" name="edupay23" style="" value="<?php echo isset($_POST["edupay23"]) ? $_POST["edupay23"] : ''; ?>"></td>
			<td><input type="text" placeholder="Appointment" name="eduappoint24" style="" value="<?php echo isset($_POST["eduappoint24"]) ? $_POST["eduappoint24"] : ''; ?>"></td>
			<td><input type="text" id="doj2" placeholder="Joining date" name="edujoin25" style="" value="<?php echo isset($_POST["edujoin25"]) ? $_POST["edujoin25"] : ''; ?>">
			<script>
				$('#doj2').datepicker({
 				onSelect: function(value, ui) {
 			        console.log(ui.selectedYear)
       				var today = new Date(), 
         			dob = new Date(value), 
          			age = 2017-ui.selectedYear;

   				//$("#age").text(age);
   				},
  	 			//(set for show current month or current date)maxDate: '+0d',
				
    				changeMonth: true,
    				changeYear: true,
    				dateFormat: 'yy-mm-dd',
     				defaultDate: '1yr',
    				yearRange: 'c-37:c+30',
				});
			</script>
</td>
			<td><input type="text" placeholder="Remarks" name="eduremark26" style="<?php echo isset($_POST["eduremark26"]) ? $_POST["eduremark26"] : ''; ?>" value=""></td>
			<td><input type="text" placeholder="Previous experience" name="eduexpie27" style="<?php echo isset($_POST["eduexpie27"]) ? $_POST["eduexpie27"] : ''; ?>" value=""></td>
		</tr>

	</tbody>
</table>

</br></br>
<table style="margin-left:0px;font-size:22px;width:20%;">
		<tr>
			<td><input type="submit" name="addeducation" value="Submit" style="width:100%;height:35px;font-size:18px;"></td>
			<td><input type="reset" name="reset" value="Reset" style="width:100%;height:35px;font-size:18px;"></td>
		</tr>


</table>

</form>

</center>


<?php //$thisPage2="studentaddDetail";
	$this->load->view('template/footer'); ?>
</body>
</html>