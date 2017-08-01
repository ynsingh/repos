<?php
defined('BASEPATH') OR exit('No direct script access allowed');

?><!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Welcome to IGNTU</title>
	<link rel="shortcut icon" href="<?php echo base_url('assets/images'); ?>/index.jpg">
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/message.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/studentNavbar.css">
	 <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">   

	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery-ui.css">
      	<script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-1.12.4.js" ></script>
      	<script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.js" ></script> 

	<style type="text/css">

	/*::selection { background-color: #E13300; color: white; }
	::-moz-selection { background-color: #E13300; color: white; }*/
	tr td a{text-decoration:none;font-size:15px;color:black;font-weight:bold;}
	tr td{font-size:15px;}
	tr th{background:black;color:white;font-weight:bold;}
	input[type='text']{font-size:17px;height:30px;background-color:white;font-family:Times New Roman;font-weight:bold;}
	textarea{font-family:Times New Roman;font-weight:bold;font-size:17px;}
	</style>

</head>
<body>
<div>
	<div id="body">
	<?php $this->load->view('template/header'); ?>
	<nav> 	<h1>Welcome to IGNTU  </h1></nav>
	<?php if(isset($_SESSION)) {
        	echo $this->session->flashdata('flash_data');
    	} ?>
 	</br>
	<?php $this->load->view('carrier/applicant_head'); ?>
<?php
echo "<center>";

	if($this->session->flashdata('msg')){
echo "<div style='font-size:20px;text-align:center;background-color:#DFF2BF;width:50%;height:30px;color:green;'>";
	echo $this->session->flashdata('msg');
echo "<div>";	
}

echo "</center>";
?>
	<div align="left" style="margin-left:30px;width:1700px;font-size:18px;">
        <?php echo validation_errors('<div class="isa_warning">','</div>');?>
        <?php echo form_error('<div style="margin-left:30px;" class="isa_error">','</div>');?>
        <?php if(isset($_SESSION['success'])){?>
        <div class="alert alert-success"><?php echo $_SESSION['success'];?></div>
        <?php
    	 };
       	?>
	
        <?php if(isset($_SESSION['err_message'])){?>
             <div class="isa_error" ><div ><?php echo $_SESSION['err_message'];?></div></div>
        <?php
        };
	?>  
      </div>
	<form action="<?php echo site_url('carrier/applicant_step2'); ?>" method="POST">
<center>
	<table style="margin-top:2%;width:90%;border:2px solid black;">

		<thead>
			<th colspan=8 style="background-color:#7e7e7e;color:white;font-size:22px;text-align:justify;">1. Qualification Detail</th>
		</thead>

		<thead style="font-size:20px;">
			<th>No.</th><th>Examination Degree</th><th>Subject/Specialization</th><th>Year</th><th>Division</th><th>% Of Marks</th>
			<th>University / College / Board</th><th>Distinction / Scholarship</th>
		</thead>
		
		<tbody>
			<?php //$this->eresult = $this->Common_model->get_listspfic1('student_master','sm_id');
			//$resu = $this->eresult;
			 //if(isset($resu)):
	  		 //$coun = count($resu);
	   		 //for($i = 0 ; $i<$coun ; $i++){ ?>	
				<input type="hidden" name="smid" value="<?php //echo $resu->sm_id; ?>" >
			<?php //} 
         		   //endif; ?>
		<tr>	
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;text-align:center;">1</td>
			<td><input type="text" placeholder="Examination Degree" name="Hexam" style="width:97%;" value="Matriculation/10th /Secondary" readonly></td>
			<td><input type="text" placeholder="Subject" name="Hsubject" style="width:97%;" value="<?php echo @$this->data['Hsubject']; ?>"></td>
			<td><input type="text" placeholder="Year" name="Hyear" style="width:97%; " value="<?php echo @$this->data['Hyear']; ?>" MaxLength="4"></td>
			<td><input type="text" placeholder="Division" name="HDivision" style=" width:97%;" value="<?php echo @$this->data['HDivision']; ?>"></td>
			<td><input type="text" placeholder="Marks" name="HMarks" style="width:97%; " value="<?php echo @$this->data['HMarks']; ?>"></td>
			<td><input type="text" placeholder="Univerity" name="HUniverity" style=" width:97%;" value="<?php echo @$this->data['HUniverity']; ?>"></td>
			<td><input type="text" placeholder="Scholarship" name="Hscholar" style=" width:97%;" value="<?php echo @$this->data['Hscholar']; ?>"></td>
			
			
		</tr>
		<tr>	
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;text-align:center;">2</td>
			<td><input type="text" placeholder="Examination Degree" name="Inexam" style="width:97%;" value="Intermediate /+2/ HS" readonly></td>
			<td><input type="text" placeholder="Subject" name="Isubject" style=" width:97%;" value="<?php echo @$this->data['Isubject']; ?>"></td>
			<td><input type="text" placeholder="Year" name="IYear" style="width:97%; " value="<?php echo @$this->data['IYear']; ?>"></td>
			<td><input type="text" placeholder="Division" name="IDivision" style=" width:97%;" value="<?php echo @$this->data['IDivision']; ?>"></td>
			<td><input type="text" placeholder="Marks" name="IMarks" style="width:97%; " value="<?php echo @$this->data['IMarks']; ?>"></td>
			<td><input type="text" placeholder="Univerity" name="IUniverity" style=" width:97%;" value="<?php echo @$this->data['IUniverity']; ?>"></td>
			<td><input type="text" placeholder="Scholarship" name="Ischolar" style="width:97%; " value="<?php echo @$this->data['Ischolar']; ?>"></td>
			
		</tr>
		<tr>	
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;text-align:center;">3</td>
			<td><input type="text" placeholder="Examination Degree" name="Gexam" style="width:97%;" value="Graduation /+3" readonly></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Subject" name="Gsubject" style="width:97%; " value="<?php echo @$this->data['Gsubject']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Year" name="GYear" style="width:97%; " value="<?php echo @$this->data['GYear']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Division" name="GDivision" style=" width:97%;" value="<?php echo @$this->data['GDivision']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Marks" name="GMarks" style="width:97%; " value="<?php echo @$this->data['GMarks']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Univerity" name="GUniverity" style="width:97%; " value="<?php echo @$this->data['GUniverity']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Scholarship" name="Gscholar" style=" width:97%;" value="<?php echo @$this->data['Gscholar']; ?>"></td>
		</tr>
		<tr>	
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;text-align:center;">4</td>
			<td><input type="text" placeholder="Examination Degree" name="Hoexam" style="width:97%;" value="Honours" readonly></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Subject" name="Hosubject" style=" width:97%;" value="<?php echo @$this->data['Hosubject']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Year" name="HoYear" style="width:97%; " value="<?php echo @$this->data['HoYear']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Division" name="HoDivision" style="width:97%; " value="<?php echo @$this->data['HoDivision']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Marks" name="HoMarks" style="width:97%; " value="<?php echo @$this->data['HoMarks']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Univerity" name="HoUniverity" style="width:97%; " value="<?php echo @$this->data['HoUniverity']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Scholarship" name="Hoscholar" style="width:97%; " value="<?php echo @$this->data['Hoscholar']; ?>"></td>
		</tr>
		<tr>	
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;text-align:center;">5</td>
			<td><input type="text" placeholder="Examination Degree" name="Pexam" style="width:97%;" value="Post Graduation" readonly></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Subject" name="Psubject" style="width:97%; " value="<?php echo @$this->data['Pcname']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Year" name="PYear" style=" width:97%;" value="<?php echo @$this->data['PYear']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Division" name="PDivision" style="width:97%; " value="<?php echo @$this->data['PDivision']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Marks" name="PMarks" style="width:97%; " value="<?php echo @$this->data['PMarks']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Univerity" name="PUniverity" style="width:97%; " value="<?php echo @$this->data['PUniverity']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Scholarship" name="Pscholar" style="width:97%; " value="<?php echo @$this->data['Pscholar']; ?>"></td>
		</tr>
		<tr>	
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;text-align:center;">6</td>
			<td rowspan=2><input type="text" placeholder="Examination Degree" name="Aexam" style="width:97%;" value="Any other qualification" readonly></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Subject" name="Asubject" style="width:97%; " value="<?php echo @$this->data['Asubject']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Year" name="AYear" style="width:97%; " value="<?php echo @$this->data['AYear']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Division" name="ADivision" style="width:97%; " value="<?php echo @$this->data['ADivision']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Marks" name="AMarks" style="width:97%; " value="<?php echo @$this->data['AMarks']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Univerity" name="AUniverity" style=" width:97%;" value="<?php echo @$this->data['AUniverity']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Scholarship" name="Ascholar" style="width:97%; " value="<?php echo @$this->data['Ascholar']; ?>"></td>
		</tr>
				
		</tbody>
		</table>
<!-----------------------------------------------------------Research Degree------------------------------------------------------------->
		<table style="margin-top:50px;width:90%;border:2px solid black;">

		<thead>
			<th colspan=6 style="background-color:#7e7e7e;color:white;font-size:22px;text-align:justify;">2. Research Degree</th>
		</thead>

		<thead style="font-size:20px;">
			<th>No.</th><th>Degree</th><th>University</th><th>Date Of Submission</th><th>Date Of Award </th><th>Thesis</th>
			
		</thead>
		
		<tbody>
			<?php //$this->eresult = $this->Common_model->get_listspfic1('student_master','sm_id');
			//$resu = $this->eresult;
			 //if(isset($resu)):
	  		 //$coun = count($resu);
	   		 //for($i = 0 ; $i<$coun ; $i++){ ?>	
				<input type="hidden" name="smid" value="<?php //echo $resu->sm_id; ?>" >
			<?php //} 
         		   //endif; ?>
		<tr>	
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;text-align:center;">1</td>
			<td><input type="text" placeholder="" name="Mpexam" style="width:97%;" value="M.Phill" readonly></td>
			<td><input type="text" placeholder="University" name="Mpuniv" style="width:97%; " value="<?php echo @$this->data['Mpuniv']; ?>"></td>
			<td><input type="text" placeholder="Date of submission" id="dos" name="Mpsubmit" style=" width:97%;" value="<?php echo @$this->data['Mpsubmit']; ?>"></td>
			<script>
				$('#dos').datepicker({
 				onSelect: function(value, ui) {
 			       	console.log(ui.selectedYear)
       				var today = new Date(), 
         			dob = new Date(value), 
          			age = 2017-ui.selectedYear;
   				},
  	 			dateFormat: 'yy-m-d',
				timeFormat:'hh:m:s',
				numberOfMonths: 1,
				});
			</script>
			<td><input type="text" placeholder="Date of award" id="dow" name="Mpaward" style="width:97%; " value="<?php echo @$this->data['Mpaward']; ?>"></td>
			<script>
				$('#dow').datepicker({
 				onSelect: function(value, ui) {
 			       	console.log(ui.selectedYear)
       				var today = new Date(), 
         			dob = new Date(value), 
          			age = 2017-ui.selectedYear;
   				},
  	 			dateFormat: 'yy-mm-dd',
				numberOfMonths: 1,
				});
			</script>
			<td><input type="text" placeholder="Thesis" name="Mpthesis" style="width:97%; " value="<?php echo @$this->data['Mpthesis']; ?>"></td>
		</tr>
		<tr>	
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;text-align:center;">2</td>
			<td><input type="text" placeholder="" name="Phexam" style="width:97%;" value="Ph.D / D.phil" readonly></td>
			<td><input type="text" placeholder="University" name="Phuniv" style="width:97%; " value="<?php echo @$this->data['Phuniv']; ?>"></td>
			<td><input type="text" placeholder="Date of submission" id="dos1" name="Phsubmit" style="width:97%; " value="<?php echo @$this->data['Phsubmit']; ?>"></td>
			<script>
				$('#dos1').datepicker({
 				onSelect: function(value, ui) {
 			       	console.log(ui.selectedYear)
       				var today = new Date(), 
         			dob = new Date(value), 
          			age = 2017-ui.selectedYear;
   				},
  	 			dateFormat: 'yy-mm-dd',
				numberOfMonths: 1,
				});
			</script>
			<td><input type="text" placeholder="Date of award" id="dow1" name="Phaward" style="width:97%; " value="<?php echo @$this->data['Phaward']; ?>"></td>
			<script>
				$('#dow1').datepicker({
 				onSelect: function(value, ui) {
 			       	console.log(ui.selectedYear)
       				var today = new Date(), 
         			dob = new Date(value), 
          			age = 2017-ui.selectedYear;
   				},
  	 			dateFormat: 'yy-mm-dd',
				numberOfMonths: 1,
				});
			</script>
			<td><input type="text" placeholder="Thesis" name="Phthesis" style="width:97%; " value="<?php echo @$this->data['Phthesis']; ?>"></td>
		</tr>
		<tr>	
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;text-align:center;">3</td>
			<td><input type="text" placeholder="" name="Dlexam" style="width:97%;" value="D.sc / D.Litt" readonly></td>
			<td><input type="text" placeholder="University" name="Dluniv" style="width:97%; " value="<?php echo @$this->data['Dluniv']; ?>"></td>
			<td><input type="text" placeholder="Date of submission" id="dos2" name="Dlsubmit" style="width:97%; " value="<?php echo @$this->data['Dlsubmit']; ?>"></td>
			<script>
				$('#dos2').datepicker({
 				onSelect: function(value, ui) {
 			       	console.log(ui.selectedYear)
       				var today = new Date(), 
         			dob = new Date(value), 
          			age = 2017-ui.selectedYear;
   				},
  	 			dateFormat: 'yy-mm-dd',
				numberOfMonths: 1,
				});
			</script>
			<td><input type="text" placeholder="Date of award" id="dow2" name="Dlaward" style="width:97%; " value="<?php echo @$this->data['Dlaward']; ?>"></td>
			<script>
				$('#dow2').datepicker({
 				onSelect: function(value, ui) {
 			       	console.log(ui.selectedYear)
       				var today = new Date(), 
         			dob = new Date(value), 
          			age = 2017-ui.selectedYear;
   				},
  	 			dateFormat: 'yy-mm-dd',
				numberOfMonths: 1,
				});
			</script>
			<td><input type="text" placeholder="Thesis" name="Dlthesis" style="width:97%; " value="<?php echo @$this->data['Dlthesis']; ?>"></td>
			
		</tr>

		<tr>
			<td colspan=2 style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;text-align:center;text-align:center;">(i)</td>
			<td colspan=3 style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;">
			Wheather Ph.D awarded as per UGC Regulation(If Yes,Please give documentry proof)</td>
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;">
				<select name="PHDyorno">
					<option>Select Yes / NO</option>
					<option value="yes">Yes</option>
					<option value="no">No</option>
				</select>	
				  
		</tr>
		
		<tr>
			<td colspan=2 style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;text-align:center;text-align:center;">(ii)</td>
			<td colspan=3 style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;">
			Wheather qualified NET / SLET etc. (Conducted by UGC/CSIR/ICAR/State) (Indicated the date and attach documentry proof)</td>
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;">
				<select name="NETyorno">
					<option>Select Yes / NO</option>
					<option value="yes">Yes</option>
					<option value="no">No</option>
				</select>
			</td>
		</tr>

		</tbody>
		</table>

<!-----------------------------------------------------------Teching / Professional / Research Employment------------------------------------------------------------->

<table style="margin-top:2%;width:90%;border:2px solid black;">

		<thead>
			<th colspan=9 style="background-color:#7e7e7e;color:white;font-size:22px;text-align:justify;">3. Name / Professional / Research Employment</th>
		</thead>

		<thead style="font-size:20px;">
		<tr>
			<th rowspan=2>No.</th>
			<th rowspan=2>Name / Address / Contact No. of Employer</th>
			<th rowspan=2 style="width:10%;">Post</th>
			<th rowspan=2 style="width:0%;">Present Pay Scale</th>
			<th rowspan=2 style="width:10%;">Basic Pay As on Date</th>
			<th rowspan=2 style="width:10%;">Total Gross Pay</th>
			<th colspan=2 style="width:20%;">Period of Employement</th>
			<th rowspan=2>Nature of Duties / Work</th>
		</tr>	
		<tr>
			<th colspan=1>From</th><th>To</th>
			</tr>
			
		</thead>
		
		<tbody>
			<?php //$this->eresult = $this->Common_model->get_listspfic1('student_master','sm_id');
			//$resu = $this->eresult;
			 //if(isset($resu)):
	  		 //$coun = count($resu);
	   		 //for($i = 0 ; $i<$coun ; $i++){ ?>	
				<input type="hidden" name="smid" value="<?php //echo $resu->sm_id; ?>" >
			<?php //} 
         		   //endif; ?>
		<tr>	
			<td style="background-color:#7e7e7e;color:white;font-weight:bold;text-align:center;">1</td>
			<td><textarea name="Eaddress" rows=1 style="width:95%;font-size:16px;" placeholder="Name/Address/Contact no." value="<?php echo @$this->data['Eaddress']; ?>"></textarea></td>
			<td><input type="text" placeholder="Post" name="Epost" style="width:95%;" value="<?php echo @$this->data['Epost']; ?>"></td>
			<td><input type="text" placeholder="Present Pay Scale" name="Epresentpay" style=" " value="<?php echo @$this->data['Epresentpay']; ?>"></td>
			<!--<td><input type="text" placeholder="Basic Pay Scale" name="Ebasicpay" style="width:95%;" value="<?php echo @$this->data['Ebasicpay']; ?>"></td>--->			<td><input type="text" name="Ebasicpay" placeholder="Basic Pay Scale" value="<?php echo @$this->data['Ebasicpay']; ?>"></td>
			<td><input type="text" placeholder="Total Gross Pay" name="Egrosspay" style=" width:95%;" value="<?php echo @$this->data['Egrosspay']; ?>"></td>
			<td><input type="text" id="periodfrom" placeholder="From" name="Eperiodfrom" style=" width:95%;" value="<?php echo @$this->data['Eperiodfrom']; ?>"></td>
			<script>
				$('#periodfrom').datepicker({
 				onSelect: function(value, ui) {
 			        console.log(ui.selectedYear)
       				var today = new Date(), 
         			dob = new Date(value), 
          			age = 2017-ui.selectedYear;
   				},
  	 			
    				dateFormat: 'yy-mm-dd',
				numberOfMonths: 1,
				});
			</script>
			<td><input type="text" id="periodto" placeholder="To" name="Eperiodto" style="width:95%; " value="<?php echo @$this->data['Eperiodto']; ?>"></td>
			<script>
				$('#periodto').datepicker({
 				onSelect: function(value, ui) {
 			        console.log(ui.selectedYear)
       				var today = new Date(), 
         			dob = new Date(value), 
          			age = 2017-ui.selectedYear;
   				},
  	 			
    				dateFormat: 'yy-mm-dd',
				numberOfMonths: 1,
				});
			</script>
			<td><input type="text" placeholder="Nature of Duties/Work" name="Enature" style=" " value="<?php echo @$this->data['Enature']; ?>"></td>
		</tr>

		<tr>	
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;text-align:center;">2</td>
			<td><textarea name="Eadd" rows=1 style="width:95%;font-size:16px;" placeholder="Name/Address/Contact no." value="<?php echo @$this->data['Eadd']; ?>"></textarea></td>
			<td><input type="text" placeholder="Post" name="Epos" style="width:95%;" value="<?php echo @$this->data['Epos']; ?>"></td>
			<td><input type="text" placeholder="Present Pay Scale" name="Epresent" style=" " value="<?php echo @$this->data['Epresent']; ?>"></td>
			<!--<td><input type="text" placeholder="Basic Pay Scale" name="Ebasicpay" style="width:95%;" value="<?php echo @$this->data['Ebasicpay']; ?>"></td>--->			<td><input type="text" id="Ebasicpay1" name="Ebasic" placeholder="Basic Pay Scale" value="<?php echo @$this->data['Ebasic']; ?>"></td>

			<td><input type="text" placeholder="Total Gross Pay" name="Egross" style=" width:95%;" value="<?php echo @$this->data['Egross']; ?>"></td>
			
			<td><input type="text" placeholder="From" id="perifrom" name="Eperifrom" style=" width:95%;" value="<?php echo @$this->data['Eperifrom']; ?>"></td>
			<script>
				$('#perifrom').datepicker({
 				onSelect: function(value, ui) {
 			        console.log(ui.selectedYear)MaxLength="12"
       				var today = new Date(), 
         			dob = new Date(value), 
          			age = 2017-ui.selectedYear;
   				},
  	 			
    				dateFormat: 'yy-mm-dd',
				numberOfMonths: 1,
				});
			</script>
			<td><input type="text" id="perito" placeholder="To" name="Eperito" style="width:95%; " value="<?php echo @$this->data['Eperito']; ?>"></td>
			<script>
				$('#perito').datepicker({
 				onSelect: function(value, ui) {
 			        console.log(ui.selectedYear)
       				var today = new Date(), 
         			dob = new Date(value), 
          			age = 2017-ui.selectedYear;
   				},
  	 			
    				dateFormat: 'yy-mm-dd',
				numberOfMonths: 1,
				});
			</script>
			<td><input type="text" placeholder="Nature of Duties/Work" name="Eduties" style=" " value="<?php echo @$this->data['Eduties']; ?>"></td>
		</tr>
		</tbody>
		</table>
<!---------------------------------------------------------Reserch projects----------------------------------------------------------------->
		<table style="width:90%;margin-top:2%;border:2px solid black;">
		
		<thead>
			<th colspan=9 style="text-align:justify;background-color:#7e7e7e;color:white;font-size:22px;">4. Research Projects</th>
		</thead>
			<tr><td><textarea name="Rproject" rows=2 style="width:99%;font-size:16px;" placeholder="Research Projects" value="<?php echo @$this->data['Rproject']; ?>"></textarea></td>
		</tr>
		</table>
	</br>
<!---------------------------------------------------------Reserch guidence----------------------------------------------------------------->		
		<table style="width:90%;border:2px solid black;margin-top:2%;">
		<thead>
			<th colspan=9 style="text-align:justify;background-color:#7e7e7e;color:white;font-size:22px;">5. Research Guidence</th>
		</thead>
		<tbody>
			<tr>
				<td style="font-size:18px;"><b>Number of Thesis Supervised :</b> 
		<input type="text" placeholder="Number of Thesis Supervised" name="Resproject" style=" " value="<?php echo @$this->data['Resproject']; ?>"></td>
			</tr>
		</tbody>
		<table style="width:90%;margin-top:0px;border:2px solid black;">
			<thead style="font-size:18px;">
				<th></th><th></th><th>Awarded</th><th>Submitted</th><th>In Progress</th>
			</thaed>
			<tbody>
				<tr>
					<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;font-size:18px;text-align:center;">(i)</td>
					<td><input type="text" placeholder="" name="Resexam" style="width:97%;" value="PhD" readonly></td>
					<td><input type="text" placeholder="Awarded" name="Resaward" style="width:98%;" value="<?php echo @$this->data['Resaward']; ?>"></td>
					<td><input type="text" placeholder="Submitted" name="Resubmit" style="width:98%; " value="<?php echo @$this->data['Resubmit']; ?>"></td>
					<td><input type="text" placeholder="In Progress" name="Resprogress" style=" width:98%;" value="<?php echo @$this->data['Resprogress']; ?>"></td>
				</tr>

				<tr>
					<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;font-size:18px;text-align:center;">(ii)</td>					<td><input type="text" placeholder="" name="Mexam" style="width:97%;" value="M.Phil" readonly></td>
					<td><input type="text" placeholder="Awarded" name="Maward" style="width:98%;" value="<?php echo @$this->data['Maward']; ?>"></td>
					<td><input type="text" placeholder="Submitted" name="Msubmit" style="width:98%; " value="<?php echo @$this->data['Msubmit']; ?>"></td>
					<td><input type="text" placeholder="In Progress" name="Mprogress" style=" width:98%;" value="<?php echo @$this->data['Mprogress']; ?>"></td>
				</tr>
			</tbody>
		</table>

		</table>

<!---------------------------------------------------------prizes / medals/awarded/honours----------------------------------------------------------------->
		<table style="width:90%;margin-top:2%;border:2px solid black;">
			<thead>
				<th colspan=9 style="background-color:#7e7e7e;color:white;font-size:22px;text-align:justify;">6. Prizes/Medals/Awards/Honours</th>
			</thead>
			<tbody>
				<tr>
					<td>
			<textarea name="Prizes" rows=2 style="width:99%;font-size:16px;" placeholder="Prizes/Medals/Awards/Honours" value="<?php echo @$this->data['Prizes']; ?>"></textarea>
					</td>
				</tr>	
			</tbody>	
			
		</table>

<!---------------------------------------------------------summary of experience----------------------------------------------------------------->
		<table style="width:90%;margin-top:2%;border:2px solid black;">
			<thead>
				<th colspan=9 style="text-align:justify;background-color:#7e7e7e;color:white;font-size:22px;">7. Summary of Experience / Performance</th>
			</thead>
			<thead style="font-size:18px;">
				<th>No.</th><th>Teaching Experience</th><th>From</th><th>To</th><th>Total Years & Months</th>
			</thead>

			<tbody>
				<tr>
					<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;font-size:18px;text-align:center;">(i)</td>
					<td><input type="text" placeholder="" name="Ugradute" style="width:97%;" value="Under Graduate" readonly></td>
					<td><input type="text" id="Ugfrom" placeholder="From" name="Ugfrom" style=" width:98%;" value="<?php echo @$this->data['Ugfrom']; ?>"></td>
					<script>
						$('#Ugfrom').datepicker({
 						onSelect: function(value, ui) {
 			       			 console.log(ui.selectedYear)
       						var today = new Date(), 
         					dob = new Date(value), 
          					age = 2017-ui.selectedYear;
   						},
  	 			
    						dateFormat: 'yy-mm-dd',
						numberOfMonths: 1,
						});
					</script>
					<td><input type="text" placeholder="To" id="Ugto" name="Ugto" style=" width:98%;" value="<?php echo @$this->data['Ugto']; ?>"></td>
					<script>
						$('#Ugto').datepicker({
 						onSelect: function(value, ui) {
 			       			 console.log(ui.selectedYear)
       						var today = new Date(), 
         					dob = new Date(value), 
          					age = 2017-ui.selectedYear;
   						},
  	 			
    						dateFormat: 'yy-mm-dd',
						numberOfMonths: 1,
						});
					</script>
					<td><input type="text" placeholder="Total year" name="Ugyear" style=" width:98%;" value="<?php echo @$this->data['Ugyear']; ?>"></td>
				</tr>

				<tr>
					<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;font-size:18px;text-align:center;">(ii)</td>
					<td><input type="text" placeholder="" name="Pgraduate" style="width:97%;" value="Post Graduate" readonly></td>
					<td><input type="text" placeholder="From" id="pgfrom" name="Pgfrom" style=" width:98%;" value="<?php echo @$this->data['Pgfrom']; ?>"></td>
					<script>
						$('#pgfrom').datepicker({
 						onSelect: function(value, ui) {
 			       			 console.log(ui.selectedYear)
       						var today = new Date(), 
         					dob = new Date(value), 
          					age = 2017-ui.selectedYear;
   						},
  	 			
    						dateFormat: 'yy-mm-dd',
						numberOfMonths: 1,
						});
					</script>
					<td><input type="text" placeholder="To" id="pgto" name="Pgto" style=" width:98%;" value="<?php echo @$this->data['Pgto']; ?>"></td>
					<script>
						$('#pgto').datepicker({
 						onSelect: function(value, ui) {
 			       			 console.log(ui.selectedYear)
       						var today = new Date(), 
         					dob = new Date(value), 
          					age = 2017-ui.selectedYear;
   						},
  	 			
    						dateFormat: 'yy-mm-dd',
						numberOfMonths: 1,
						});
					</script>
					<td><input type="text" placeholder="Total year" name="Pgyear" style=" width:98%;" value="<?php echo @$this->data['Pgyear']; ?>"></td>
				</tr>

				<tr>
					<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;font-size:18px;text-align:center;">(iii)</td>

					<td><input type="text" placeholder="" name="Tteach" style="width:97%;" value="Total Teaching Experience" readonly></td>
					<td><input type="text" placeholder="From" name="Tfrom" id="tfrom" style=" width:98%;" value="<?php echo @$this->data['Tfrom']; ?>"></td>
					<script>
						$('#tfrom').datepicker({
 						onSelect: function(value, ui) {
 			       			 console.log(ui.selectedYear)
       						var today = new Date(), 
         					dob = new Date(value), 
          					age = 2017-ui.selectedYear;
   						},
  	 			
    						dateFormat: 'yy-mm-dd',
						numberOfMonths: 1,
						});
					</script>
					<td><input type="text" placeholder="To" name="Tto" id="tto" style=" width:98%;" value="<?php echo @$this->data['Tto']; ?>"></td>
					<script>
						$('#tto').datepicker({
 						onSelect: function(value, ui) {
 			       			 console.log(ui.selectedYear)
       						var today = new Date(), 
         					dob = new Date(value), 
          					age = 2017-ui.selectedYear;
   						},
  	 			
    						dateFormat: 'yy-mm-dd',
						numberOfMonths: 1,
						});
					</script>
					<td><input type="text" placeholder="Total year" name="Tyear" style=" width:98%;" value="<?php echo @$this->data['Tyear']; ?>"></td>
				</tr>

				<tr>
					<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;font-size:18px;text-align:center;">(iv)</td>
					
					<td><textarea value="Tvpariti" name="Tvparti" style="width:97%;height:38px;" readonly>Participating in production of educational TV programme </textarea>

</td>
					<td></td><td></td>
					<td><input type="text" placeholder="Total year" name="Tvprogyear" style="width:98%;" value="<?php echo @$this->data['Tvprogyear']; ?>"></td>
				</tr>

				<tr>
					<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;font-size:18px;text-align:center;">(v)</td>
<td><textarea value="Tvpariti" name="Tvterm" style="width:97%;height:38px;" readonly>Short Term / Continuing Education / Specialist Courses conducted</textarea>					
					<!--<input type="text" placeholder="" name="Tvterm" style="width:97%;" value="Short Term / Continuing Education / Specialist Courses conducted" readonly>---></td>
					<td></td><td></td>
					<td><input type="text" placeholder="Total year" name="Stprogyear" style=" width:98%;" value="<?php echo @$this->data['Tvprogyear']; ?>"></td>
				</tr>
			</tbody>
		</table>
			
		<table style="width:90%;margin-top:2%;border:2px solid black;">
			<thead>
				<th colspan=9 style="text-align:justify;background-color:#7e7e7e;color:white;font-size:22px;">8. Training courses and conference / seminar / workshop papers(please give the details of Annexure E)</th>
			</thead>
			<tbody>
				<tr>
					<td width=450><label>(a) Membership / Fellowship of professional socities :</label></br></br></br>
					<textarea name="8a" rows=2 style="width:95%;font-size:16px;" placeholder="Membership" value="<?php echo @$this->data['8a']; ?>"></textarea></td>
					
					<td width=450><label>(b) Other Activities Resposibilities:</label></br></br></br>
					<textarea name="8b" rows=2 style="width:95%;font-size:16px;" placeholder="Activities" value="<?php echo @$this->data['8b']; ?>"></textarea></td>
					
					<td width=450><label>(c) Are you willing to accept the initial salary or the grade ? </br>(if no, State what is the minimum salary acceptable with justificatin thereof):</label></br>
					<textarea name="8c" rows=2 style="width:95%;font-size:16px;" placeholder="Accept initial salary grade" value="<?php echo @$this->data['8c']; ?>"></textarea></td>
				</tr>

<tr height=10></tr>
				
		<tr height=10></tr>
				<tr>
					<td><label>(d)If appointed, what period would you require before joining the post ?</label></br></br>
					<textarea name="8d" rows=2 style="width:95%;font-size:16px;" placeholder="Period of joinig post" value="<?php echo @$this->data['8d']; ?>"></textarea></td>

					<td><label>(e)Any other relevant information , if not given above</label></br></br>
					<textarea name="8e" rows=2 style="width:95%;font-size:16px;" placeholder="Relevant Information" value="<?php echo @$this->data['8e']; ?>"></textarea></td>
				</tr>
<tr height=10></tr>
				
			</tbody>
		</table>

		<table style="width:90%;margin-top:2%;border:2px solid black;">
			<thead>
				<th colspan=9 style="text-align:justify;background-color:#7e7e7e;color:white;font-size:22px;">9</th>
			</thead>
			<tbody>
				<tr>
					<td><label> (a) Has there be any break in your academic career ? if so, give details </label></br>
					<textarea name="9a"  style="width:97%;font-size:16px;" placeholder="Academic career" value="<?php echo @$this->data['9a']; ?>"></textarea></td>
					<td><label>(b) Have you been punished during your studies at college / University ? if so, give details</label></br>
					<textarea name="9b" style="width:95%;font-size:16px;" placeholder="Punished in college/university" value="<?php echo @$this->data['9b']; ?>"></textarea></td>

					<td><label>(c) Heve you been punished during your services or convicted by a court of law ? if so, give detail</label></br>
					<textarea name="9c" style="width:95%;font-size:16px;" placeholder="Punished during your service" value="<?php echo @$this->data['9c']; ?>"></textarea></td>
					
				</tr>


		<tr height=10></tr>
				<tr>
					<td><label>(d)Do you have any case pending against you in any court of law ? if yes, so give detail</label></br>
					<textarea name="9d" rows=2 style="width:96%;font-size:16px;" placeholder="Pending any case" value="<?php echo @$this->data['9d']; ?>"></textarea></td>
					<td><label>(e)Any other relevant information , if not given above</label></br></br>
					<textarea name="9e" rows=2 style="width:96%;font-size:16px;" placeholder="Relevant Information" value="<?php echo @$this->data['9e']; ?>"></textarea></td>
				</tr>

			</tbody>
		
		</table>


		<table style="width:90%;margin-top:2%;border:2px solid black;">
			<thead>
				<th colspan=9 style="text-align:justify;background-color:#7e7e7e;color:white;font-size:22px;">10. Give Names designation and address (Phone/Fax no./E-mail if any, of three reference not related to you .Reference should be persons with or under who have intimate knowledge of your work).</th>
			</thead>
			<tbody>
				<tr>
					<td style="width:17%;"><label>(i) Name and Address :</label></br>
					<textarea name="10ainame" style="width:95%;font-size:16px;" placeholder="Name / Designation and Address" value="<?php echo @$this->data['10ainame']; ?>"></textarea></td>
					<td style="width:14%;"><label>Mobile No.</label></br>
					<input type="text" name="10amobile" style="height:40px;" placeholder="Mobile number" value="<?php echo @$this->data['10amobile']; ?>" MaxLength="10">
					</td>

					<td><label>E-mail</label></br>
					<input type="text" name="10aemail" style="height:40px;" placeholder="E-mail" value="<?php echo @$this->data['10aemail']; ?>" >
					</td>

					<td><label>(ii) Name and Address :</label></br>
					<textarea name="10aiiname" rows=2 style="width:95%;font-size:16px;" placeholder="Name / Designation and Address" value="<?php echo @$this->data['10aiiname']; ?>"></textarea></td>
					<td style="width:14%;"><label>Mobile No.</label></br>
					<input type="text" name="10aiimobile" style="height:40px;" placeholder="Mobile number" value="<?php echo @$this->data['10aiimobile']; ?>" MaxLength="10">
					</td>

					<td ><label>E-mail</label></br>
					<input type="text" name="10aiiemail" style="height:40px;" placeholder="E-mail" value="<?php echo @$this->data['10aiiemail']; ?>">
					</td>
				
				</tr>
		<tr height=10></tr>
				<tr>
					
				</tr>
<tr height=10></tr>
			<tr>
					<td><label>(iii) Name and Address :</label></br>
					<textarea name="10aiiiname" rows=2 style="width:95%;font-size:16px;" placeholder="Name / Designation and Address" value="<?php echo @$this->data['10aiiiname']; ?>"></textarea></td>
					<td style="width:14%;"><label>Mobile No.</label></br>
					<input type="text" name="10aiiimobile" style="height:40px;" placeholder="Mobile number" value="<?php echo @$this->data['10aiiimobile']; ?>" MaxLength="10">
					</td>

					<td ><label>E-mail</label></br>
					<input type="text" name="10aiiiemail" style="height:40px;" placeholder="E-mail" value="<?php echo @$this->data['10aiiiemail']; ?>">
					</td>
					
				
				</tr>

				
			</tbody>
		
		</table>

		<table style="width:90%;margin-top:2%;border:2px solid black;">
			<thead>
				<th colspan=9 style="text-align:justify;background-color:#7e7e7e;color:white;font-size:22px;">11. Publication if any, with their titles .Please list your publications in bibliographic form they should be classified into acientific papers, Extensions publication (Booklets / Bulletins),Books (Authorized / Edited), Manuals , Book chapter ,Popular articles etc. (Reprint of papers be enclosed with the application).</th>
			</thead>
				
			<tbody>
				<tr>
					<td><textarea name="Publication" rows=2 style="width:99%;font-size:16px;" placeholder="Publications" value="<?php echo @$this->data['Publication']; ?>"></textarea></td>
				</tr>
			</tbody>

		</table>

		<table style="width:20%;margin-top:2%;">
		<tr>
			<td><input type="submit" name="education" value="Submit" style="width:100%;height:35px;font-size:18px;"></td>
			<td><input type="reset" name="reset" value="Reset" style="width:100%;height:35px;font-size:18px;"></td>
		</tr>
	</table>
</center>		
	</form>
	<?php $this->load->view('template/footer'); ?>
	<!--<p class="footer">Page rendered in <strong>{elapsed_time}</strong> seconds. <?php echo  (ENVIRONMENT === 'development') ?  'CodeIgniter Version <strong>' . CI_VERSION . '</strong>' : '' ?></p>-->
</body>
</html>
