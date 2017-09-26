<!-------------------------------------------------------
    -- @name enterence_step1.php --	
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
input[type='text']{font-size:17px;height:30px;background-color:white;width:97%;}
input[type='email']{font-size:17px;height:30px;background-color:white;}


tr td{font-size:15px;}
thead tr th{background-color:#38B0DE;color:white;font-weight:bold;font-size:15px;}
select{width:100%;font-size:17px;height:30px;font-weight:bold;}

</style>

<script type="text/javascript">
/*
function change_getcat(){

	var xmlhttp = new XMLHttpRequest();

	xmlhttp.open("GET","<?php //echo site_url('student/getcatbr'); ?>?catbranch="+document.getElementById("register_name").value,false);

	xmlhttp.send(null);
	
	document.getElementById("Actlocation").innerHTML = xmlhttp.responseText; 
	}
*/
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
	<?php //$this->load->view('student/stuStepshead');?>
	</br> 

<center>

<form action="<?php //echo site_url('Student/student_step1'); ?>"  method="POST">

	<table style="width:65%;margin-left:5%;">
		<thead><tr><th align=left colspan="8" style="font-size:22px;">Personal</th></tr></thead>
		<tr height=10></tr>
	    <tr>	
		<td>	
			<label for="nnumber">Course Applied for</label></br>
			<select name="entcouname" class="form-control" id="register_name" style="height:37px;font-size:18px;font-weight:bold;">
			<option  disabled selected>Courses</option>
				<?php foreach($this->prgname as $data){?>
				<option value="<?php echo $data->prg_id;?>"><?php echo $data->prg_name; ?></option>
				<?php }?>
	  		</select>
		<td/>
				
		<td>	<!---<span style="color:red;"><?php echo form_error('Scenters');?></span>--->
			<label for="ennumber">Study Centers</label></br>
			
			<select name="entcenter" class="form-control"  style="height:37px;font-size:18px;font-weight:bold;" >
 			<option  disabled selected>Study Centers</option>
			<?php 
			foreach($this->scresult as $datas1): ?>	
				<option value="<?php echo $datas1->sc_code; ?>"><?php echo $datas1->sc_name; ?></option>
			<?php endforeach; ?>
			</select>   
		<td/>

		<td>	<!---<span style="color:red;"><?php echo form_error('Stypeprogramme');?></span>--->
			<label for="nnumber">Enterence Exam Center</label></br>
			<select name="entexamcenter" class="form-control" style="height:37px;font-size:18px;font-weight:bold;">

			<option selected="true" disabled="disabled" style="font-size:18px;">Type of programme/courses</option>
					<?php //foreach($this->prgname as $progcat): ?>	
					<option value="<?php //echo $progcat->prgcat_id;?>"><?php //echo $progcat->prgcat_name; ?></option>
					<?php //endforeach; ?>
			</select>		
		<td/>
		<td>	
			<label for="nnumber">Applicant name</label></br>	
			<input type="text" name="entappliname" placeholder="Enter Your Name" value="<?php echo isset($_POST["entappliname"]) ? $_POST["entappliname"] : ''; ?>"/>
		<td/>
	</tr>
				
	<tr height=10></tr>

	<tr>
		<td>	
			<label for="nnumber">Email</label></br>	
			<input type="text" name="entemail" placeholder="Enter Your Email" value="<?php echo isset($_POST["entemail"]) ? $_POST["entemail"] : ''; ?>"/>		
		<td/>

		<td>	
			<label>Mobile/Phone no.</label></br>
			<input type="text" name="entmobile" placeholder="Enter Mobile Number" MaxLength="10" pattern="/^+91(7\d|8\d|9\d)\d{9}$/" value="<?php echo isset($_POST["entmobile"]) ? $_POST["entmobile"] : ''; ?>"/>				
		<td/>

		
		<td>	<!---<span style="color:red;"><?php echo form_error('Sgender');?></span>--->
			<label>Gender</label></br>
			<select name="entgender" class="form-control" style="height:37px;font-size:18px;font-weight:bold;">
 			<option value=""disabled selected>Select Gender</option>
				<option value="Male">Male</option>
				<option value="Female">Female</option>			
			</select>  
			<!--<input type="text" name="Sgender" placeholder="Enter Gender" >--->		
		<td/>

		<td>	<!---<span style="color:red;"><?php echo form_error('Sgender');?></span>--->
			<label>Maritial Status</label></br>
			<select name="entgender" class="form-control" style="height:37px;font-size:18px;font-weight:bold;width:100%;">
 			<option value=""disabled selected>Select Maritial Status</option>
				<option value="Married">Married</option>
				<option value="Un-Married">Un-Married</option>			
			</select>  
			<!--<input type="text" name="Sgender" placeholder="Enter Gender" >--->		
		<td/>

	</tr>
		<tr height=10></tr>
	<tr>
		<td>	
			<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery-ui.min.css">
  			  <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.min.js" ></script>

		<label>Select date of birth</label></br>
		<input id="dob" type="text" name="entdob" placeholder="Enter Your Dob" value="<?php echo isset($_POST["entdob"]) ? $_POST["entdob"] : ''; ?>">

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
		<td>	
			<label for="Co-ordinator Contact">Your Age</label></br>
			<textarea id="age" class="form-control" placeholder="Your Age" name="entage" style="height:31px;font-size:15px;width:97%;font-weight:bold;" 
			value="<?php echo isset($_POST["entage"]) ? $_POST["entege"] : ''; ?>" readonly required></textarea>	
		<td/>
		<td>	
			<label>Category</label></br>
			<select name="entcate" style="height:37px;font-size:18px;font-weight:bold;">
				<option selected="true" disabled>Select your category</option>
				<option value="SC">SC</option>
				<option value="ST">ST</option>
				<option value="OBC">OBC</option>
				<option value="General">General</option>
			</select>
		<td/>

		<td>	
			<label>Nationality</label></br>
			<input type="text" name="entnationality" value="<?php echo isset($_POST["entnationality"]) ? $_POST["entnationality"] : ''; ?>" placeholder="Enter Nationality"/>
		<td/>


	</tr>
	
		<tr height=10></tr>
	<tr>
		<td>	
			<label>Are you physically</br> handicapped ?</label></br>
			<select name="entdisability" style="height:37px;font-size:18px;font-weight:bold;">
				<option selected="true" disabled>Select disability</option>
				<option value="Yes">Yes</option>	
				<option value="No">No</option>
			</select>
		<td/>
	
		<td>
			<label>Select religion</label></br></br>
			<select name="entreligion" class="form-control"  style="height:37px;font-size:18px;font-weight:bold;">
				<option selected="true" disabled="disabled" style="font-size:18px;">Select Religion</option>
				<option value="HINDUISM">HINDUISM</option>
				<option value="ISLAM">ISLAM</option>
				<option value="CHRISTIANITY">CHRISTIANITY</option>
				<option value="SIKHISM">SIKHISM</option>
				<option value="BUDDHISM">BUDDHISM</option>
				<option value="JAINISM">JAINISM</option>
				<option value="ZOROASTRIANISM">ZOROASTRIANISM</option>
				<option value="judaism">JUDAISM</option>
	 		</select>
		</td>
	</tr>
		
	<tr height=10></tr>
	<tr>
	</tr>
	</table>

<table>
	
	<tr>
		<td></td>	
	</tr>
</table>
	
	<table style="width:65%;margin-left:5%;">
		<thead><tr><th align=left colspan="8" style="font-size:22px;">Permanant and Correspondence Address</th></tr></thead>

		<tr>
			<td align=center>
				<tr><td style="background-color:black;color:white;font-size:18px;text-align:center;" colspan="8">Permanant Address</td></tr>
		<tr height=10></tr>
				<tr><td>
					<label for="nnumber">Street</label></br>
					<input type="text" name="entstreet" placeholder="Enter Your Street" value="<?php echo isset($_POST["entstreet"]) ? $_POST["entstreet"] : ''; ?>">
				   </td>		
			<td><label for="nnumber">City</label></br>
			<input type="text" name="entpcity" placeholder="Enter Your City" value="<?php echo isset($_POST["entcity"]) ? $_POST["entcity"] : ''; ?>"></td>
			

			<td><label for="nnumber">State</label></br>
			<input type="text" name="entpstate" placeholder="Enter Your State" value="<?php echo isset($_POST["entstate"]) ? $_POST["entstate"] : ''; ?>"></td>
			<td><label for="nnumber">Postal Code</label></br>
			<input type="text" name="entpcode" placeholder="Enter Your Postal Code" value="<?php echo isset($_POST["entpcode"]) ? $_POST["entpcode"] : ''; ?>"></td>
			</tr>	
<tr height=10></tr>
			<tr>
				<td><label for="nnumber">Country</label></br>
			<input type="text" name="entpcountry" placeholder="Enter Your Country" value="<?php echo isset($_POST["entpcountry"]) ? $_POST["entpcountry"] : ''; ?>"></td>
			</tr>
			
			</td>


<tr height=10></tr>
			<td align=center>
				<tr><td  style="background-color:black;color:white;font-size:18px;text-align:center;" colspan="8">Correspondence Address</td></tr>
				<tr height=10></tr>
				<tr><td>
					<label for="nnumber">Street</label></br>
					<input type="text" name="entcostreet" placeholder="Enter Your Street" value="<?php echo isset($_POST["entcostreet"]) ? $_POST["entcostreet"] : ''; ?>">
				   </td>		
			<td><label for="nnumber">City</label></br>
			<input type="text" name="entcocity" placeholder="Enter Your City" value="<?php echo isset($_POST["entcocity"]) ? $_POST["entcocity"] : ''; ?>"></td>
			

			<td><label for="nnumber">State</label></br>
			<input type="text" name="entcostate" placeholder="Enter Your State" value="<?php echo isset($_POST["entcostate"]) ? $_POST["entcostate"] : ''; ?>"></td>
			<td><label for="nnumber">Postal Code</label></br>
			<input type="text" name="entcocode" placeholder="Enter Your Postal Code" value="<?php echo isset($_POST["entcocode"]) ? $_POST["entcocode"] : ''; ?>"></td>
			</tr>	
		<tr height=10></tr>
			<tr>
				<td><label for="nnumber">Country</label></br>
			<input type="text" name="entcocountry" placeholder="Enter Your Country" value="<?php echo isset($_POST["entcocountry"]) ? $_POST["entcocountry"] : ''; ?>"></td>
			</tr>
			
			</td>
		</tr>
		
	</table>
</br></br>
	<table style="width:65%;margin-left:5%;">
		<thead><tr><th align=left colspan="8" style="font-size:22px;">Family Detail</th></tr></thead>
		<tr height=10></tr>
		<tr>
		
			<td>	
			<label for="nnumber">Father's Name<label></br>
			<input type="text" name="entfathername" placeholder="Enter Father Name" value="<?php echo isset($_POST["entfathername"]) ? $_POST["entfathername"] : ''; ?>">	
		<td/>
		<td>	
			<label for="nnumber">Mother name</label></br>
			<input type="text" name="entmothername" placeholder="Enter Mother Name" value="<?php echo isset($_POST["entmothername"]) ? $_POST["entmothername"] : ''; ?>">	
		<td/>

		<td>	
			<label for="nnumber">Father's Mobile No.<label></br>
			<input type="text" name="entfathermono" placeholder="Enter father mo. no." value="<?php echo isset($_POST["entfathermono"]) ? $_POST["entfathermono"] : ''; ?>">	
		<td/>
		<td>	
			<label for="nnumber">Mother Mobile No.</label></br>
			<input type="text" name="entmothermono" placeholder="Enter mother mo. no." value="<?php echo isset($_POST["entmothermono"]) ? $_POST["entmothermono"] : ''; ?>">	
		<td/>
		
		</tr>

<tr height=10></tr>
		<tr>
		<td>	
			<label for="nnumber">Father's Occupation<label></br>
			<input type="text" name="entfatheroccu" placeholder="Enter father occupation" value="<?php echo isset($_POST["entfatheroccu"]) ? $_POST["entfatheroccu"] : ''; ?>">	
		<td/>
		<td>	
			<label for="nnumber">Mother Occupation</label></br>
			<input type="text" name="entmotheroccu" placeholder="Enter mother occupation" value="<?php echo isset($_POST["entmotheroccu"]) ? $_POST["entmotheroccu"] : ''; ?>">	
		<td/>
		<tr>

		</tr>
	</table>

</br></br>
<table style="width:10%;">
	<tr>
			<td><input type="submit" name="addstudent" value="Submit" style="width:100%;height:35px;font-size:18px;"></td>
			<td><input type="reset" name="reset" value="Reset" style="width:100%;height:35px;font-size:18px;"></td>
		</tr>
</table>



</form>

</center>


<?php //$thisPage2="studentaddDetail";
	$this->load->view('template/footer'); ?>
</body>
</html>
