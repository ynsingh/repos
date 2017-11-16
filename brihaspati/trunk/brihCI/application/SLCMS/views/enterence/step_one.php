<!-------------------------------------------------------
    -- @name step_one.php --	
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
input[type='text']{font-size:17px;height:35px;background-color:white;width:99%;font-weight:bold;}
input[type='email']{font-size:17px;height:35px;background-color:white;width:98%;font-weight:bold;}

#filltoo{font-size:20px;}
tr td{font-size:15px;}
thead tr th{background-color:#38B0DE;color:white;font-weight:bold;font-size:15px;}
select{width:98%;font-size:17px;height:35px;font-weight:bold;}

</style>

<script type="text/javascript">
function filladd()
{
	 if(filltoo.checked == true) 
     	{
             var street11 =document.getElementById("street").value;
	     var city11 =document.getElementById("city").value;
	     var state11 =document.getElementById("state").value;
             var pcode11 =document.getElementById("pcode").value;
	     var country11 =document.getElementById("country").value; 
           
            var copystreet =street11;
            var copycity =city11;
            var copystate =state11;
	    var copypcode =pcode11;
            var copycount =country11;	
            
            document.getElementById("street1").value = copystreet;
            document.getElementById("city1").value = copycity;
            document.getElementById("state1").value = copystate;
	    document.getElementById("pcode1").value = copypcode;
            document.getElementById("country1").value = copycount;	
	 }
	 else if(filltoo.checked == false)
	 {
		 document.getElementById("street1").value='';
		 document.getElementById("city1").value='';
		 document.getElementById("state1").value='';
		 document.getElementById("pcode1").value='';
		 document.getElementById("country1").value='';
	 }
}
</script>
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
echo "<div style='font-size:20px;text-align:center;background-color:#DFF2BF;width:100%;height:40px;color:green;'>";
	echo $this->session->flashdata('msg');
echo "<div>";	
}


	if((isset($_SESSION['success'])) && ($_SESSION['success'])!=''){
		//echo "<div style=\"margin-left:30px;width:1700px;align:left;font-size:18px;height:10px;\" class=\"isa_success\">";
	echo "<table style=\"100%;font-size:18px;height:30px;border:1px solid white;\" class=\"isa_success\">";			
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
echo "</center>";
?>
<?php
echo "<center>";

	if($this->session->flashdata('msg')){
echo "<div style='font-size:20px;text-align:center;background-color:#DFF2BF;width:100%;height:30px;color:green;'>";
	echo $this->session->flashdata('msg');
echo "<div>";	
}

	if((isset($_SESSION['success'])) && ($_SESSION['success'])!=''){
		//echo "<div style=\"margin-left:30px;width:1700px;align:left;font-size:18px;height:10px;\" class=\"isa_success\">";
	echo "<table style=\"margin-left:30px;width:100%;font-size:18px;height:30px;border:1px solid white;\" class=\"isa_success\">";			
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
echo "</center>";
?>   
<center>
	<div align="left" style="width:100%;font-size:18px;">
        <?php echo validation_errors('<div class="isa_warning">','</div>');?>
        <?php echo form_error('<div style="margin-left:30px;height:30px;" class="">','</div>');?>
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
</center>
	</br> 
<center>
<form action="<?php echo site_url('enterence/step_one'); ?>"  method="POST">

	<table style="width:100%;">
		<thead><tr><th align=left colspan="8" style="font-size:22px;">Personal</th></tr></thead>
		<tr height=10></tr>
	    <tr>	
		<td>	
			<label for="nnumber">Course Applied for</label></br>
					
			<input type="text" name="entcouname" placeholder="Enter Your Email" value="<?php echo $prgname;?>" readonly/>
		<td/>
				
		<td>	<!---<span style="color:red;"><?php echo form_error('Scenters');?></span>--->
			<label for="ennumber">Study Centers</label></br>
			
			<select name="entcenter" style="height:37px;font-size:18px;font-weight:bold;" >
 			<option  disabled selected>Study Centers</option>
			<?php 
			foreach($this->scresult as $datas1): ?>	
				<option value="<?php echo $datas1->sc_code; ?>"><?php echo $datas1->sc_name; ?></option>
			<?php endforeach; ?>
			</select>   
		<td/>

		<td>	<!---<span style="color:red;"><?php echo form_error('Stypeprogramme');?></span>--->
			<label for="nnumber">Enterence Exam Center</label></br>
			<select name="entexamcenter" style="height:37px;font-size:18px;font-weight:bold;">

			<option selected="true" disabled="disabled" style="font-size:18px;">Type of programme/courses</option>
					<?php foreach($this->examcenter as $row): ?>	
					<option value="<?php echo $row->eec_id;?>"><?php echo $row->eec_name; ?></option>
					<?php endforeach; ?>
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
			<input type="text" name="entemail" placeholder="Enter Your Email" value="<?php echo $email;?>" readonly/>		
		<td/>

		<td>	
			<label>Mobile/Phone no.</label></br>
			<input type="text" name="entmobile" placeholder="Enter Mobile Number" MaxLength="10" pattern="/^+91(7\d|8\d|9\d)\d{9}$/" value="<?php echo $mobile; ?>" readonly/>				
		<td/>

		
		<td>	<!---<span style="color:red;"><?php echo form_error('Sgender');?></span>--->
			<label>Gender</label></br>
			<select name="entgender" style="height:37px;font-size:18px;font-weight:bold;">
 			<option value=""disabled selected>Select Gender</option>
				<option value="Male">Male</option>
				<option value="Female">Female</option>			
			</select>  
			<!--<input type="text" name="Sgender" placeholder="Enter Gender" >--->		
		<td/>

		<td>	<!---<span style="color:red;"><?php echo form_error('Sgender');?></span>--->
			<label>Maritial Status</label></br>
			<select name="entmaritial" style="height:37px;font-size:18px;font-weight:bold;width:100%;">
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
		<input id="dob" type="text" name="entdob" placeholder="Enter Your Dob" value="<?php echo $dob; ?>" readonly>

			<script>
				/*$('#dob').datepicker({
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
    				yearRange: 'c-47:c+50',
				});*/
			</script>	
		<td/>
		<td>	
			<label for="Co-ordinator Contact">Your Age</label></br>
			<!--<textarea id="age" class="form-control" placeholder="Your Age" name="entage" style="height:31px;font-size:15px;width:97%;font-weight:bold;" 
			value="<?php echo $age ?>" readonly required></textarea>--->	
			<input type="text" name="entage" placeholder="Enter Your Age" value="<?php echo $age; ?>" readonly>
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
			<select name="entreligion" style="height:37px;font-size:18px;font-weight:bold;">
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

<table style="width:100%;" border=2>
	<thead><tr><th align=left style="font-size:22px;" colspan=2>Parmanent and Correspondence Address</th>
		<th align=right colspan=2  style="font-size:18px;">
		<input type="checkbox" value="" name="filltoo" id="filltoo" onclick="filladd()"/>Correspondence Address same as Parmanent Address
	</th>
	</tr>
	
	</thead>
	<thead></thead>
	<tr>
		<td colspan=2>
		<table style="width:100%;">
				<tr><td style="background-color:black;color:white;font-size:20px;text-align:center;">Parmanent Address</td></tr>
		<tr height=10></tr>
			<tr><td>
			<label for="nnumber">Street</label></br>
			<input type="text" name="entpstreet" placeholder="Enter Your Street" id="street" value="<?php echo isset($_POST["entpstreet"]) ? $_POST["entpstreet"] : ''; ?>">
				   </td>
			</tr><tr>		
			<td><label for="nnumber">City</label></br>
			<input type="text" name="entpcity" placeholder="Enter Your City" id="city" value="<?php echo isset($_POST["entpcity"]) ? $_POST["entpcity"] : ''; ?>"></td>
			</tr><tr>
		
			<td><label for="nnumber">State</label></br>
			<input type="text" name="entpstate" placeholder="Enter Your State" id="state" value="<?php echo isset($_POST["entpstate"]) ? $_POST["entpstate"] : ''; ?>"></td></tr><tr>
			<td><label for="nnumber">Postal Code</label></br>
			<input type="text" name="entpcode" placeholder="Enter Your Postal Code" id="pcode" value="<?php echo isset($_POST["entpcode"]) ? $_POST["entpcode"] : ''; ?>"></td></tr>
			
	
			<tr>
				<td><label for="nnumber">Country</label></br>
			<input type="text" name="entpcountry" placeholder="Enter Your Country" id="country" value="<?php echo isset($_POST["entpcountry"]) ? $_POST["entpcountry"] : ''; ?>"></td>
			
			
			
</table>
		</td>

		<td>

		<table style="width:100%;">
				<tr><td style="background-color:black;color:white;font-size:20px;text-align:center;" >Correspondence Address</td></tr>
		<tr height=10></tr>
				<tr><td>
					<label for="nnumber">Street</label></br>
					<input type="text" name="entcostreet" placeholder="Enter Your Street" id="street1" value="<?php echo isset($_POST["entcostreet"]) ? $_POST["entcostreet"] : ''; ?>">
				   </td>
			</tr><tr>		
			<td><label for="nnumber">City</label></br>
			<input type="text" name="entcocity" placeholder="Enter Your City" id="city1" value="<?php echo isset($_POST["entcocity"]) ? $_POST["entcocity"] : ''; ?>"></td>
			</tr><tr>
		
			<td><label for="nnumber">State</label></br>
			<input type="text" name="entcostate" placeholder="Enter Your State" id="state1" value="<?php echo isset($_POST["entcostate"]) ? $_POST["entcostate"] : ''; ?>"></td></tr><tr>
			<td><label for="nnumber">Postal Code</label></br>
			<input type="text" name="entpcode" placeholder="Enter Your Postal Code" id="pcode1" value="<?php echo isset($_POST["entpcode"]) ? $_POST["entpcode"] : ''; ?>"></td></tr>
			
	
			<tr>
				<td><label for="nnumber">Country</label></br>
			<input type="text" name="entcocountry" placeholder="Enter Your Country" id="country1" value="<?php echo isset($_POST["entcocountry"]) ? $_POST["entcocountry"] : ''; ?>"></td>
			
			
			
</table>
		</td>		
	</tr>
</table>
	
	


</br></br>
	<table style="width:100%;">
		<thead><tr><th align=left colspan="8" style="font-size:22px;">Family Detail</th></tr></thead>
		<tr height=10></tr>
		<tr>	
		<td>	
			<label for="nnumber">Father's Name</label></br>
			<input type="text" name="entfathername" placeholder="Enter Father Name" value="<?php echo isset($_POST["entfathername"]) ? $_POST["entfathername"] : ''; ?>"/>
		<td/>
				
		<td>	
			<label for="nnumber">Mother name</label></br>
			<input type="text" name="entmothername" placeholder="Enter Mother Name" value="<?php echo isset($_POST["entmothername"]) ? $_POST["entmothername"] : ''; ?>"/>
		<td/>

		<td>	
			<label for="nnumber">Father's Mobile No.</label></br>
			<input type="text" name="entfathermono" placeholder="Enter father mo. no." MaxLength="10" pattern="/^+91(7\d|8\d|9\d)\d{9}$/" value="<?php echo isset($_POST["entfathermono"]) ? $_POST["entfathermono"] : ''; ?>"/>		
		<td/>
		<td>	
			<label for="nnumber">Mother Mobile No.</label></br>
			<input type="text" name="entmothermono" placeholder="Enter mother mo. no." MaxLength="10" pattern="/^+91(7\d|8\d|9\d)\d{9}$/" value="<?php echo isset($_POST["entmothermono"]) ? $_POST["entmothermono"] : ''; ?>"/>	
		<td/>
	</tr>


	<tr>	
		<td>	
			<label for="nnumber">Father's Occupation</label></br>
			<input type="text" name="entfatheroccu" placeholder="Enter father occupation" value="<?php echo isset($_POST["entfatheroccu"]) ? $_POST["entfatheroccu"] : ''; ?>"/>
		<td/>
				
		<td>	
			<label for="nnumber">Mother Occupation</label></br>
			<input type="text" <input type="text" name="entmotheroccu" placeholder="Enter mother occupation" value="<?php echo isset($_POST["entmotheroccu"]) ? $_POST["entmotheroccu"] : ''; ?>"/>
		<td/>

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
</br></br></br></br></br>

<?php //$thisPage2="studentaddDetail";
	$this->load->view('template/footer'); ?>
</body>
</html>
