<!-------------------------------------------------------
    -- @name student_step1.php --	
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

<style type="text/css">
label{font-size:18px;}
input[type='text']{font-size:17px;height:30px;background-color:white;}
input[type='email']{font-size:17px;height:30px;background-color:white;}


tr td{font-size:15px;}
tr th{background:black;color:white;font-weight:bold;}
select{width:100%;font-size:17px;height:40px;}

</style>
<script type="text/javascript">

function change_getcat(){

	var xmlhttp = new XMLHttpRequest();

	xmlhttp.open("GET","<?php echo site_url('student/getcatbr'); ?>?catbranch="+document.getElementById("register_name").value,false);

	xmlhttp.send(null);
	
	document.getElementById("Actlocation").innerHTML = xmlhttp.responseText; 
	}

</script>
</head>
<body>


<div>
	<div id="body">
	<?php  // $thisPage2="studentaddDetail"; 
		$this->load->view('template/header'); ?>
	<nav> 	<h1>Welcome to IGNTU  </h1></nav>
	<?php //if(isset($_SESSION)) {
        	//echo $this->session->flashdata('flash_data');
    	//} ?>
<!--------------------------------------------------------ERROR DISPLAY-------------------------------------------------------------->

<?php
	/*if($this->session->flashdata('msg')){
		echo "<div align='left' class='isa_warning' style='margin-left:30px;width:1680px;font-size:18px;'>";
		echo $this->session->flashdata('msg');
		echo "</div>";
		echo "<div align='left' class='isa_warning' style='margin-left:30px;width:1680px;font-size:18px;'>";
		echo $this->session->flashdata('msg1');
		echo "</div>";
	}*/
?>
	<div align="left" style="margin-left:30px;width:1700px;font-size:18px;">
        <?php echo validation_errors('<div class="isa_warning">','</div>');?>
        <?php echo form_error('<div style="margin-left:30px;" class="">','</div>');?>
        <?php if(isset($_SESSION['success'])){?>
        <div class="alert alert-success"><?php echo $_SESSION['success'];?></div>
        <?php
    	 };
       	?>
	
        <?php if(isset($_SESSION['err_message'])){?>
             <div class="" style='margin-left:30px;width:1680px;font-size:18px;'><div ><?php echo $_SESSION['err_message'];?></div></div>
        <?php
        };
	?>  
	

      </div>
	</br> 
	<?php $this->load->view('student/stuStepshead');?>
	</br> 
<center>
	  <table  style="background-color:#f1f1f1;" align="">

<center>
<form action="<?php echo site_url('Student/student_step1'); ?>"  method="POST">

	<table style="width:65%;">
		
	
	    <tr>	

		<td>	
			<!---<span style="color:red;"><?php echo form_error('Sanumber');?></span>--->
			<label for="nnumber">Application number</label></br>
			<input type="text" name="Sanumber" placeholder="Enter Application Number" value="<?php echo $this->number;//$row->application_no;?>" readonly>	
		<td/>

		<td>
			<!---<span style="color:red;"><?php echo form_error('Sname');?></span>--->
			<label>Name of the applicant</label></br>
			<input type="text" name="Sname" placeholder="Enter your name" value="<?php echo $this->name;//$row->application_no;?>" readonly>	
		<td/>		
		<td>	<!---<span style="color:red;"><?php echo form_error('Scenters');?></span>--->
			<label for="nnumber">Study Centers</label></br>
			
			<select name="Scenters" class="form-control"  style="height:37px;font-size:18px;font-weight:bold;" >
 			<option  disabled selected>Study Centers</option>
                      
			<?php 
			
			foreach($this->scresult as $datas1): ?>	
				<option value="<?php echo $datas1->sc_code; ?>"><?php echo $datas1->sc_name; ?></option>
			<?php endforeach; ?>
			</select>   
		
		
		<td/>

		<td>	<!---<span style="color:red;"><?php echo form_error('Stypeprogramme');?></span>--->
			<label for="nnumber">Programme Category</label></br>
			<select name="Stypeprogramme" class="form-control" style="height:37px;font-size:18px;font-weight:bold;">

			<option selected="true" disabled="disabled" style="font-size:18px;">Type of programme/courses</option>
					<?php foreach($this->prgname as $progcat): ?>	
					<option value="<?php echo $progcat->prgcat_id;?>"><?php echo $progcat->prgcat_name; ?></option>
					<?php endforeach; ?>
			</select>		
		<td/>
		<td>	
			<label for="nnumber">Name of programme/course</label></br>
			<select name="Snameprogramme" class="form-control" id="register_name" style="height:37px;font-size:18px;font-weight:bold;">
				<?php //foreach($this->prgname as $progname): ?>	
					<option value="<?php echo $this->categid;?>"><?php echo $this->couname; ?></option>
				<?php //endforeach; ?>
	  		</select>
			<!---<input type="text" name="Snameprogramme" placeholder="Enter your name" value="<?php echo $this->couname;?>" readonly>--->	
		<td/>
	</tr>

				
	<tr height=10></tr>

	<tr>
		
		<td>	
			<label for="nnumber">SelectBranch</label></br>
			<div id="Actlocation">			
			<select name="Sbranchname" class="form-control"  style="height:37px;font-size:18px;font-weight:bold;">
			<!--<option selected="true" disabled="disabled" style="font-size:18px;">Select Branch Name</option>-->
					<?php //foreach( $this->prgbranch as $prog): ?>	
						<option value="<?php echo $this->categid;?>"><?php echo $this->progname; ?></option>
					<?php //endforeach; ?>
			</select>	
			</div>
			
			<!---<input type="text" name="Sbranchname" placeholder="Enter your branch name" value="<?php echo $this->progname;?>" readonly>-->
			
		<td/>
		<td>	
			<!---<span style="color:red;"><?php echo form_error('Smothername');?></span>--->
			<label for="nnumber">Mother name</label></br>	
			<input type="text" name="Smothername" placeholder="Enter Mother Name" value="<?php echo @$this->data['Smothername']; ?>"/>		
		<td/>

		<td>	
			<!---<span style="color:red;"><?php echo form_error('Sfathername');?></span>--->
			<label>Father Name</label></br>
			<input type="text" name="Sfathername" placeholder="Enter Father Name" value="<?php echo $this->fathname ?>"/>		
		<td/>

		<td>	
			<!---<span style="color:red;"><?php echo form_error('Scategory');?></span>--->
			<label for="nnumber">Category</label></br>
			<select name="Scategory" class="form-control" style="height:37px;font-size:18px;font-weight:bold;">

			<option selected="true" disabled="disabled" style="font-size:18px;">Category</option>
					<?php foreach($this->scatresult as $cate): ?>	
						<option value="<?php echo $cate->cat_id; ?>"><?php echo $cate->cat_name; ?></option>
					<?php endforeach; ?>
			</select>
		<td/>
		<td>	<!---<span style="color:red;"><?php echo form_error('Sgender');?></span>--->
			<label>Gender</label></br>
			<select name="Sgender" class="form-control" style="height:37px;font-size:18px;font-weight:bold;">
 			<option value=""disabled selected>Select Gender</option>
				<option value="Male">Male</option>
				<option value="Female">Female</option>			
			</select>  
			<!--<input type="text" name="Sgender" placeholder="Enter Gender" >--->		
		<td/>
		
		
	<tr>
		<tr height=10></tr>
	<tr>	
		<td>	<!---<span style="color:red;"><?php echo form_error('Sdob');?></span>--->
			  <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery-ui.min.css">
  			  <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.min.js" ></script>

		<label>Select date of birth</label></br>
		<input id="dob" type="text" name="Sdob" placeholder="Enter Your Dob" value="<?php echo @$this->data['Sdob']; ?>">

			<script>
				$('#dob').datepicker({
 				onSelect: function(value, ui) {
 			        console.log(ui.selectedYear)
       				var today = new Date(), 
         			dob = new Date(value), 
          			age = 2017-ui.selectedYear;

   				$("#age").text(age);
   				},
  	 			//(set for show current month or current date)maxDate: '+0d',
				
    				changeMonth: true,
    				changeYear: true,
    				dateFormat: 'yy-mm-dd',
     				defaultDate: '1yr',
    				yearRange: 'c-37:c+30',
				});
			</script>	
		<td/>

		<td>	<!---<span style="color:red;"><?php echo form_error('Saadharnumber');?></span>--->
			<label for="nnumber">Aadhar number</label></br>	
			<input type="text" name="Saadharnumber" placeholder="Enter Aadhar Number" MaxLength="12" value="<?php echo @$this->data['Saadharnumber']; ?>"/>		
		<td/>

		<!-------write dropdown of department------------>
		<td>
				<label for="nnumber">Add Department</label></br>
				<select name="Sdepart" class="form-control" id="register_name" style="height:37px;font-size:18px;font-weight:bold;">
					<option selected="true" disabled="disabled" style="font-size:18px;">Department</option>
					<?php 
				
					foreach($this->depresult as $progname): ?>	
					<option value="<?php echo $progname->dept_id; ?>"><?php echo $progname->dept_name;?></option>
					<?php endforeach; ?>
	 			 </select>			
		<td/>
	
		<td>	<!---<span style="color:red;"><?php echo form_error('Sabgroup');?></span>--->
			<label>Blood group</label></br>
			<!--<input type="text" name="Sabgroup" placeholder="Enter Blood Group" value="<?php echo @$this->data['Sabgroup']; ?>"/>--->
			<select name="Sabgroup" class="form-control" id="register_name" style="height:37px;font-size:18px;font-weight:bold;">
				<option selected="true" disabled="disabled" style="font-size:18px;">Select Blood Group</option>
				<option value="A+">A+</option>
				<option value="O+">O+</option>
				<option value="B+">B+</option>
				<option value="AB+">AB+</option>
				<option value="A-">A-</option>
				<option value="O-">O-</option>
				<option value="B-">B-</option>
				<option value="AB-">AB-</option>
				
	 		</select>		
		<td/>
		<td>	<!---<span style="color:red;"><?php echo form_error('Sreligion');?></span>--->
			<label>Religion</label></br>
			<!--<input type="text" name="Sreligion" placeholder="Enter Your Religion" value="<?php echo @$this->data['Sreligion']; ?>"/>--->
			<select name="Sreligion" class="form-control"  style="height:37px;font-size:18px;font-weight:bold;" id='drop'>
				<option selected="true" disabled="disabled" style="font-size:18px;">Select Religion</option>
				<option value="HINDUISM">HINDUISM</option>
				<option value="ISLAM">ISLAM</option>
				<option value="CHRISTIANITY">CHRISTIANITY</option>
				<option value="SIKHISM">SIKHISM</option>
				<option value="BUDDHISM">BUDDHISM</option>
				<option value="JAINISM">JAINISM</option>
				<option value="ZOROASTRIANISM">ZOROASTRIANISM</option>
				<option value="judaism">JUDAISM</option>
				<!---<option value="other">Other</option>--->
	 		</select>
			
	<!---<input type="text" name="Sabgroup" style="display:none" id='down' placeholder="Other Religion"/>--->
		<script>

			//$(document).ready(function(){
	  			//  $('#drop').on('change', function() {
     					// if ( this.value == 'other')
     					// {
		        		//	$("#down").slideToggle();
					//	//$("#drop").hide();
					// }
		      			// else
		      			 //  {
		       	 		//	$("#down").slideToggle();
		      			 /   }
		    		//});
		    	//});
		
</script>
		<td/>
		

		
	<tr>

		<tr height=10></tr>
	<tr>
		<td>	<!---<span style="color:red;"><?php echo form_error('Spaddress');?></span>--->
			<label>Postal Address</label></br>
			<input type="text" name="Spaddress" placeholder="Enter Postal Address" value="<?php echo @$this->data['Spaddress']; ?>"/>		
		<td/>
	
		<td>	<!--<span style="color:red;"><?php echo form_error('Semail');?></span>-->
			<label>District</label></br>
	    <input type="text" name="Sdist" placeholder="Enter Your District" style="height:30px;width:97%;" value="<?php echo @$this->data['Sdist']; ?>"/>		
		<td/>
		<td>	<!--<span style="color:red;"><?php echo form_error('Semail');?></span>-->
			<label>Post Office</label></br>
	    <input type="text" name="Spost" placeholder="Enter Your post office" style="height:30px;width:98%;" value="<?php echo @$this->data['Spost']; ?>"/>		
		<td/>

		<td>	<!---<span style="color:red;"><?php echo form_error('Scity');?></span>--->
			<label for="nnumber">City</label></br>	
			<input type="text" name="Scity" placeholder="Enter Your District/City" value="<?php echo @$this->data['Scity']; ?>"/>		
		<td/>
		<td>	<!---<span style="color:red;"><?php echo form_error('Sstate');?></span>--->
			<label>State</label></br>
			<input type="text" name="Sstate" placeholder="Enter Your State" value="<?php echo @$this->data['Sstate']; ?>"/>		
		<td/>
		
	<tr>
		
	<tr height=10></tr>
	<tr>
		<td>	<!---<span style="color:red;"><?php echo form_error('Spincode');?></span>--->
			<label>Country</label></br>
			<input type="text" name="Scountry" placeholder="Enter Your Country" value="<?php echo @$this->data['Scountry']; ?>"/>		
		<td/>

		<td>	<!---<span style="color:red;"><?php echo form_error('Spincode');?></span>--->
			<label>Pincode</label></br>
			<input type="text" name="Spincode" placeholder="Enter Your Pincode" MaxLength="6" value="<?php echo @$this->data['Spincode']; ?>"/>		
		<td/>
		
		<td>	<!---<span style="color:red;"><?php echo form_error('Smobile');?></span>--->
			<label>Mobile/Phone no.</label></br>
			<input type="text" name="Smobile" placeholder="Enter Mobile Number" MaxLength="10" pattern="/^+91(7\d|8\d|9\d)\d{9}$/" value="<?php echo @$this->data['Smobile']; ?>"/>		
		<td/>
		<td>	<!--<span style="color:red;"><?php echo form_error('Semail');?></span>-->
			<label>Email-Id</label></br>
	    <input type="email" name="Semail" placeholder="Enter Your Email-Id" style="height:30px;width:100%;" value="<?php echo $this->semail ?>" readonly/>		
		<td/>
	
	</tr>
	
	<table style="margin-top:50px;width:50%;border:2px solid black;">

		<thead>
			<th colspan=7 style="margin-left:120px;background-color:#7e7e7e;color:white;font-size:22px;">Academic Details</th>
		</thead>

		<thead style="font-size:20px;">
			<th>No.</th><th>Programmes</th><th>Course</th><th>Subjects/Specialization</th><th>Board/University</th><th>Year of completion</th>
			<th>Passed or appeared</th>
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
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;">1</td>
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;">10th</td>
			<td><input type="text" class="form-control" id="usr" placeholder="Highschool" name="Hcname" style=" " value="<?php echo @$this->data['Hcname']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Subjects" name="Hsubject" style=" " value="<?php echo @$this->data['Hsubject']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Bord/University" name="Hboard" style=" " value="<?php echo @$this->data['Hboard']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Year" name="Hyear" style=" " value="<?php echo @$this->data['Hyear']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Passed/Appeared" name="Hpassed" style=" " value="<?php echo @$this->data['Hpassed']; ?>"></td>
			
			
		</tr>
		<tr>	
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;">2</td>
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;">12th</td>
			<td><input type="text" class="form-control" id="usr" placeholder="Intermediate" name="Icname" style=" " value="<?php echo @$this->data['Icname']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Subjects" name="Isubject" style=" " value="<?php echo @$this->data['Isubject']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Bord/University" name="Iboard" style=" " value="<?php echo @$this->data['Iboard']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Year" name="Iyear" style=" " value="<?php echo @$this->data['Iyear']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Passed/Appeared" name="Ipassed" style=" " value="<?php echo @$this->data['Ipassed']; ?>"></td>
			
		</tr>
		<tr>	
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;">3</td>
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;">Graduation</td>
			<td><input type="text" class="form-control" id="usr" placeholder="Course Name" name="Gcname" style=" " value="<?php echo @$this->data['Gcname']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Subjects" name="Gsubject" style=" " value="<?php echo @$this->data['Gsubject']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Bord/University" name="Gboard" style=" " value="<?php echo @$this->data['Gboard']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Year" name="Gyear" style=" " value="<?php echo @$this->data['Gyear']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Passed/Appeared" name="Gpassed" style=" " value="<?php echo @$this->data['Gpassed']; ?>"></td>
		</tr>
		<tr>	
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;">4</td>
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;">Post Graduation</td>
			<td><input type="text" class="form-control" id="usr" placeholder="Course Name" name="Pcname" style=" " value="<?php echo @$this->data['Pcname']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Subjects" name="Psubject" style=" " value="<?php echo @$this->data['Psubject']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Bord/University" name="Pboard" style=" " value="<?php echo @$this->data['Pboard']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Year" name="Pyear" style=" " value="<?php echo @$this->data['Pyear']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Passed/Appeared" name="Ppassed" style=" " value="<?php echo @$this->data['Ppassed']; ?>"></td>
		</tr>
		<tr>	
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;">5</td>
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;">Any Other</td>
			<td><input type="text" class="form-control" id="usr" placeholder="Course Name" name="Acname" style=" " value="<?php echo @$this->data['Acname']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Subjects" name="Asubject" style=" " value="<?php echo @$this->data['Asubject']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Bord/University" name="Aboard" style=" " value="<?php echo @$this->data['Aboard']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Year" name="Ayear" style=" " value="<?php echo @$this->data['Ayear']; ?>"></td>
			<td><input type="text" class="form-control" id="usr" placeholder="Passed/Appeared" name="Apassed" style=" " value="<?php echo @$this->data['Apassed']; ?>"></td>
		</tr>
		
		</tbody>
		</table>
</br></br>
		<tr>
			<td><input type="submit" name="addstudent" value="Submit" style="width:8%;height:35px;font-size:18px;"></td>
			<td><input type="reset" name="reset" value="Reset" style="width:8%;height:35px;font-size:18px;"></td>
		</tr>


</table>

</form>

</center>


<?php //$thisPage2="studentaddDetail";
	$this->load->view('template/footer'); ?>
</body>
</html>
