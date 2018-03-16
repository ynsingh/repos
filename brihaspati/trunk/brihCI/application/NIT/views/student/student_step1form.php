<!-------------------------------------------------------
    -- @name student_step1form.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
--------------------------------------------------------->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');?>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Student Detail</title>
	<!--<link rel="shortcut icon" href="<?php //echo base_url('assets/images'); ?>/index.jpg">-->
	<link rel="icon" href="<?php echo base_url('uploads/logo'); ?>/nitsindex.png" type="image/png">	
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/message.css">

	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/studentNavbar.css">

<style type="text/css">
label{font-size:18px;}
input[type='text']{font-size:17px;height:35px;background-color:white;width:98%;font-weight:bold;}
input[type='email']{font-size:17px;height:35px;background-color:white;width:98%;font-weight:bold;}

#filltoo{font-size:20px;}
tr td{font-size:15px;}
thead tr th{background-color:#38B0DE;color:white;font-weight:bold;font-size:15px;}
select{width:98%;font-size:17px;height:35px;font-weight:bold;}
input[type='number']{font-size:17px;height:35px;background-color:white;width:98%;font-weight:bold;}
input[type='percentage']{font-size:17px;height:35px;background-color:white;width:98%;font-weight:bold;}

</style>
<script>
 function getdegreename(branch){
		var branch = branch;
		//alert (branch);
                $.ajax({
                type: "POST",
                url: "<?php echo base_url();?>slcmsindex.php/student/degreelist",
		
                data: {"stu_prgcate" : branch},
                dataType:"html",
                success: function(data){
                $('#degree').html(data.replace(/^"|"$/g, ''));
                }
            }); 
        }
</script>
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
<body >


<div>
 	<div id="body">
	<?php $this->load->view('template/header2'); ?>
	<div class="welcome"><h2>Welcome : <?php echo $email?></h2></div></br>
	<?php $this->load->view('student/stuStepshead');?>
<!--------------------------------------------------------ERROR DISPLAY-------------------------------------------------------------->

<?php echo validation_errors('<div class="isa_warning">','</div>');?>
        <?php echo form_error('<div class="">','</div>');?>
        <?php 
	    if(!empty($_SESSION['success'])){	
		if(isset($_SESSION['success'])){?>
         <div class="isa_success" style="font-size:18px;"><?php echo $_SESSION['success'];?></div>
         <?php
          } };
         ?>
	
        <?php if(isset($_SESSION['err_message'])){?>
             <div class="isa_error"><div ><?php echo $_SESSION['err_message'];?></div></div>
        <?php
        };
	?>  	
<center>
<form action="<?php echo site_url('student/student_admissionform'); ?>"  method="POST">

	<table style="width:100%;">
		<thead><tr><th align=left colspan="8" style="font-size:22px;">Personal Details</th></tr></thead>
		<tr height=10></tr>
	    <tr>
		<td>	
			<label for="nnumber">Application Number</label></br>
			<input type="text" name="stu_addrollno" placeholder="Enter Application Number" value="<?php echo $number;?>" readonly>	
		</td>

		<td>	
			<!---<label for="ennumber">Study Centers</label></br>--->
			<label for="ennumber">Institute Name</label></br>
			<select name="stu_addcenter" style="height:37px;font-size:18px;font-weight:bold;" >
			<option  disabled selected>Select Institute</option>
			<?php foreach($stu_studycenter as $data){?>
				<option value="<?php echo $data->org_id;?>"><?php echo $data->org_name; ?></option>
			<?php }?>
			
			</select>   
		</td>

		<!---<td>	
			<label for="nnumber">Exam Center</label></br>
			<select name="stu_examcenter" style="height:37px;font-size:18px;font-weight:bold;">
			<option  disabled selected>Exam Center</option>
			<?php //foreach($stu_examcenter as $row){?>
				<option value="<?php //echo $row->sec_id;?>"><?php //echo $row->sec_name; ?></option>
			<?php //}?>		
		</td>-->
		<td>	
			<label for="nnumber">Programme Category</label></br>
			<select name="stu_addprgcate"  style="font-size:18px;font-weight:bold;">
				<!--
				<select name="stu_prgcate"  style="font-size:18px;"  onchange="getdegreename(this.value)" id="pcategory">
				<option  disabled selected>Select Program Category</option>
				<?php //foreach($stu_prgcat as $row1){?>
					<option value="<?php //echo $row1->prgcat_name;?>"><?php //echo $row1->prgcat_name; ?></option>
				<?php //}?>-->
				<?php foreach($prgcat as $row){
					$prgcatid = $row->prg_category;
					$prgcatname = $this->commodel->get_listspfic1('programcategory','prgcat_name','prgcat_id',$row->prg_category)->prgcat_name;
				?>
					<option value="<?php echo $prgcatid;?>"><?php echo $prgcatname; ?></option>
				<?php }?>
			</select>		
		</td>	
		<td>	
			<label for="nnumber">Programme</label></br>
			<select name="stu_addcourse" id="degree" style="font-size:18px;font-weight:bold;">
				<?php
				foreach($prgcat as $row){
					$prgcid = $row->prg_id;
					//$stu_prgname = $prgname;
					$stu_prgname = $this->commodel->get_listspfic1("program","prg_name","prg_id",$prgcid)->prg_name;
					$stu_prgbranch = $this->commodel->get_listspfic1("program","prg_branch","prg_id",$prgcid)->prg_branch;
				?>
					<option value="<?php echo $prgcid;?>"><?php echo $stu_prgname.'( '.$stu_prgbranch.' )';?></option>
				<?php }?>
	  		</select>
			<!--<select name="stu_addcourse" id="degree" style="font-size:18px;">
				<option selected="true" disabled="disabled">Select Programme</option>
	  		</select>
			<!--<select name="stu_addcourse"  style="font-size:18px;">
				<option  disabled selected>Select Program</option>
				<?php //foreach($stu_program as $row1){?>
					<option value="<?php //echo $row1->prg_id;?>"><?php //echo $row1->prg_name.'( '.$row1->prg_branch.' )'; ?></option>
				<?php //}?>
			</select>-->
			<!--<label for="nnumber">Course Applied For</label></br>
					
			<input type="text" name="stu_addcourse" placeholder="Enter Your Course Name" value="<?php //echo $couresename.'( '.$branchname.' )';?>" readonly/>-->
		</td>
	
	</tr>
				
	<tr height=10></tr>

	<tr>
		<td>
				<label for="nnumber">Department</label></br>
				<select name="stu_adddepart"  style="font-size:18px;font-weight:bold;">
				<option value="" disabled selected>Select Department</option>
					<?php foreach($stu_depresult as $row){?>
					<option value="<?php echo $row->dept_id; ?>"><?php echo $row->dept_name;?></option>
					<?php }?>	
	 			 </select>			
		</td>	
		<td>	
			<label for="nnumber">Applicant Name</label></br>	
			<input type="text" name="stu_addname" placeholder="Enter Your Name" value="<?php echo $name;?>"/>
		</td>

		<td>	
			<label for="nnumber">Email</label></br>	
			<input type="text" name="stu_addemail" placeholder="Enter Your Email" value="<?php echo $email;?>" readonly/>		
		</td>

		<td>	
			<label>Mobile/Phone No.</label></br>
			<input type="text" name="stu_addmobile" placeholder="Enter Mobile Number" MaxLength="12" pattern="/^+91(7\d|8\d|9\d)\d{9}$/" value="<?php echo isset($_POST["stu_addmobile"]) ? $_POST["stu_addmobile"] : ''; ?>" />				
		</td>

		


	</tr>
		<tr height=10></tr>
	<tr>
		<td>	
			<label>Gender</label></br>
			<select name="stu_addgender" style="height:37px;font-size:18px;font-weight:bold;">
 			<option value=""disabled selected>Select Gender</option>
				<option value="Male">Male</option>
				<option value="Female">Female</option>
				<option value="Transgender">Transgender</option>			
			</select>  
				
		</td>
		<td>	
			<label>Aadhar No.</label></br>
			<input type="text" name="stu_addaadhar" placeholder="Enter Aadhar Number" MaxLength="12" value="<?php echo isset($_POST["stu_addaadhar"]) ? $_POST["stu_addaadhar"] : ''; ?>" >				
		</td>


		<td>	
			 <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery-ui.min.css">
  			  <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.min.js" ></script>

		<label>Select Date Of Birth</label></br>
		<input id="dob" type="text" name="stu_adddob" placeholder="Enter Your Dob" value="<?php echo isset($_POST["stu_adddob"]) ? $_POST["stu_adddob"] : ''; ?>" readonly>

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
     				//defaultDate: '1yr',
    				yearRange: 'c-47:c+10',
				});
			</script>	
		</td>
		<td>	
			<label for="Co-ordinator Contact">Your Age</label></br>
			<textarea id="age" placeholder="Your Age" name="stu_addage" style="height:37px;font-size:15px;width:100%;font-weight:bold;" 
			 readonly required></textarea>	
			
		</td>
	</tr>
	
		<tr height=10></tr>
	<tr>
		<td>	
			<label>Category</label></br>
			<select name="stu_addcate" style="height:37px;font-size:18px;font-weight:bold;">
				<option selected="true" disabled>Select your category</option>
				<?php foreach($stu_categorylist as $row){?>
					<option value="<?php echo $row->cat_id; ?>"><?php echo $row->cat_name;?></option>
				<?php }?>	
			</select>
		</td>

		<td>
			<label>Select Religion</label></br>
			<select name="stu_addreligion" style="height:37px;font-size:18px;font-weight:bold;">
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
			<td>	
			<label>Maritial Status</label></br>
			<select name="stu_addmaritailst" style="height:37px;font-size:18px;font-weight:bold;">
 			<option value=""disabled selected>Select Maritial Status</option>
				<option value="Married">Married</option>
				<option value="Un-Married">Un-Married</option>			
			</select>  
				
		</td>
				<td>	
			<label>Nationality</label></br>
			<input type="text" name="stu_addnationality" value="<?php echo isset($_POST["stu_addnationality"]) ? $_POST["stu_addnationality"] : ''; ?>" placeholder="Enter Nationality"/>
		</td>


	</tr>
		
	<tr height=10></tr>
	<tr>
		<td>	
			<label>Are you physically handicapped ?</label></br>
			<select name="stu_adddisability" style="height:37px;font-size:18px;font-weight:bold;">
				<option selected="true" disabled>Select disability</option>
				<option value="Yes">Yes</option>	
				<option value="No">No</option>
			</select>
		</td>


		<td>
			<label>Reservation Type</label></br>
			<?php $this->load->view('enterence/multi_drop');?>
			<select name="basic[]" multiple="multiple" class="3col active" >
        			<option value="Diffrently Abled">Differently Abled</option>
        			<option value="Supernumerary Seats">Supernumerary Seats</option>
       				<option value="N.C.C. Cadets">N.C.C. Cadets</option>
				<option value="N.S.S. Volunteers">N.S.S. Volunteers</option>
        			<option value="Sports">Sports</option>
       				<option value="Wards / Dependants of Defence Personnel">Wards / Dependants of Defence Personnel</option>
				<option value="Kashmiri Migrants">Kashmiri Migrants</option>
        
   			</select>

    <script>
    $(function () {
        $('select[multiple].active.3col').multiselect({
            columns: 1,
            placeholder: 'Select Reservation',
            //search: true,
           // searchOptions: {
                //'default': 'Search States'
            //},
           // selectAll: true
        });

    });
</script>
		</td>	
		<td>	
			<label>Blood Group</label></br>
		
			<select name="stu_addabgroup"  style="font-size:18px;font-weight:bold;" >
				<option value="" style="font-size:18px;" selected disabled>Select Blood Group</option>
				<option value="A+">A+</option>
				<option value="O+">O+</option>
				<option value="B+">B+</option>
				<option value="AB+">AB+</option>
				<option value="A-">A-</option>
				<option value="O-">O-</option>
				<option value="B-">B-</option>
				<option value="AB-">AB-</option>
	 		</select>		
		</td>
	</tr>
	</table>
</br>
<table style="width:100%;" border=2>
	<thead><tr><th align=left style="font-size:22px;" colspan=3>Permanent Address</th>
		<th align=right colspan=2  style="font-size:18px;">
		
	</th>
	</tr>
	
	</thead>
	<thead></thead>
	<tr>
		<td>
		<table style="width:100%;">
				<tr><td style="background-color:black;color:white;font-size:20px;text-align:left;" colspan=3><b>Permanent Address</b></td></tr>
		<tr height=10></tr>
			<tr><td>
			<label for="nnumber">Street</label></br>
			<input type="text" name="stu_addpstreet" placeholder="Enter Your Street" id="street" value="<?php echo isset($_POST["stu_addpstreet"]) ? $_POST["stu_addpstreet"] : ''; ?>">
				   </td>
					
			<td><label for="nnumber">City</label></br>
			<input type="text" name="stu_addpcity" placeholder="Enter Your City" id="city" value="<?php echo isset($_POST["stu_addpcity"]) ? $_POST["stu_addpcity"] : ''; ?>"></td>
			
		
			<td><label for="nnumber">State</label></br>
			<input type="text" name="stu_addpstate" placeholder="Enter Your State" id="state" value="<?php echo isset($_POST["stu_addpstate"]) ? $_POST["stu_addpstate"] : ''; ?>"></td></tr><tr>
			<td><label for="nnumber">Postal Code</label></br>
			<input type="text" name="stu_addpcode" placeholder="Enter Your Postal Code" id="pcode" value="<?php echo isset($_POST["stu_addpcode"]) ? $_POST["stu_addpcode"] : ''; ?>"></td>
				<td><label for="nnumber">Country</label></br>
			<input type="text" name="stu_addpcountry" placeholder="Enter Your Country" id="country" value="<?php echo isset($_POST["stu_addpcountry"]) ? $_POST["stu_addpcountry"] : ''; ?>"></td>
		</table>
		</td>

		
	</tr>
	<tr height=20></tr>
	<tr>
		<td colspan=3 style="background-color:#38B0DE;color:white;font-weight:bold;font-size:20px;">Correspondence Address same as Permanent Address
		<input type="checkbox" value="" name="filltoo" id="filltoo" onclick="filladd()"/>
		</td>	
	</tr>
	<tr>
		<td colspan=3>
			<table style="width:100%;">
				<tr><td style="background-color:black;color:white;font-size:20px;text-align:left;"  colspan=3><b>Correspondence Address</b></td></tr>
		<tr height=10></tr>
				<tr><td>
					<label for="nnumber">Street</label></br>
					<input type="text" name="stu_addcostreet" placeholder="Enter Your Street" id="street1" value="<?php echo isset($_POST["stu_addcostreet"]) ? $_POST["stu_addcostreet"] : ''; ?>">
				   </td>
				
			<td><label for="nnumber">City</label></br>
			<input type="text" name="stu_addcocity" placeholder="Enter Your City" id="city1" value="<?php echo isset($_POST["stu_addcocity"]) ? $_POST["stu_addcocity"] : ''; ?>"></td>
			
		
			<td><label for="nnumber">State</label></br>
			<input type="text" name="stu_addcostate" placeholder="Enter Your State" id="state1" value="<?php echo isset($_POST["stu_addcostate"]) ? $_POST["stu_addcostate"] : ''; ?>"></td></tr><tr>
			<td><label for="nnumber">Postal Code</label></br>
			<input type="text" name="stu_addcocode" placeholder="Enter Your Postal Code" id="pcode1" value="<?php echo isset($_POST["stu_addcocode"]) ? $_POST["stu_addcocode"] : ''; ?>"></td>
				<td><label for="nnumber">Country</label></br>
			<input type="text" name="stu_addcocountry" placeholder="Enter Your Country" id="country1" value="<?php echo isset($_POST["stu_addcocountry"]) ? $_POST["stu_addcocountry"] : ''; ?>"></td>
		</table>
		</tr>	
	</tr>
</table>

</br></br>
	<table style="width:100%;">
		<thead><tr><th align=left colspan="8" style="font-size:22px;">Family Detail</th></tr></thead>
		<tr height=10></tr>
		<tr>	
		<td>	
			<label for="nnumber">Father's Name</label></br>
			<input type="text" name="stu_addfathername" placeholder="Enter Father Name" value="<?php echo $fathername; ?>" readonly/>
		</td>
				
		<td>	
			<label for="nnumber">Mother's Name</label></br>
			<input type="text" name="stu_addmothername" placeholder="Enter Mother Name" value="<?php echo isset($_POST["stu_addmothername"]) ? $_POST["stu_addmothername"] : ''; ?>"/>
		</td>

		<td>	
			<label for="nnumber">Father's Mobile No.</label></br>
			<input type="text" name="stu_addfathermono" placeholder="Enter father mo. no." MaxLength="12" pattern="/^+91(7\d|8\d|9\d)\d{9}$/" value="<?php echo isset($_POST["stu_addfathermono"]) ? $_POST["stu_addfathermono"] : ''; ?>"/>		
		</td>
		<td>	
			<label for="nnumber">Mother's Mobile No.</label></br>
			<input type="text" name="stu_addmothermono" placeholder="Enter mother mo. no." MaxLength="12" pattern="/^+91(7\d|8\d|9\d)\d{9}$/" value="<?php echo isset($_POST["stu_addmothermono"]) ? $_POST["stu_addmothermono"] : ''; ?>"/>	
		</td>
	</tr>


	<tr>	
		<td>	
			<label for="nnumber">Father's Occupation</label></br>
			<input type="text" name="stu_addfatheroccu" placeholder="Enter father occupation" value="<?php echo isset($_POST["stu_addfatheroccu"]) ? $_POST["stu_addfatheroccu"] : ''; ?>"/>
		</td>
				
		<td>	
			<label for="nnumber">Mother's Occupation</label></br>
			<input type="text" name="stu_addmotheroccu" placeholder="Enter mother occupation" value="<?php echo isset($_POST["stu_addmotheroccu"]) ? $_POST["stu_addmotheroccu"] : ''; ?>"/>
		</td>

	</tr>
		 
	</table>
</br>
	<!-------------------------------Education Detail--------------------------------------------------->

		<table style="width:100%;border:2px solid black;">
		<thead>
			<th colspan=11 style="margin-left:120px;background-color:#7e7e7e;color:white;font-size:22px;">Education Details</th>
		</thead>

		<thead style="font-size:20px;">
			<th>No.</th><th>Programmes</th><th>Course</th><th>Name of the Institution</th><th>Board or University</th><th>Subjects/Specialization</th>
<th>Year of Passing</th><th>Marks Obtained</th><th>Max. Marks</th><th>% of Marks</th><th>Passed or Appeared</th>
		</thead>
		
		<tbody>
			
		<tr>	
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;">1</td>
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;">10th</td>
			<td><input type="text"  placeholder="Highschool" name="stu_hcname" style="" value="<?php echo isset($_POST["stu_hcname"]) ? $_POST["stu_hcname"] : '10'; ?>" readonly></td>
			<td><input type="text" placeholder="Institute Name" name="stu_hinstitute" style=" " value="<?php echo isset($_POST["stu_hinstitute"]) ? $_POST["stu_hinstitute"] : ''; ?>"></td>
			<td><input type="text" placeholder="Board/University" name="stu_hboard" style=" " value="<?php echo isset($_POST["stu_hboard"]) ? $_POST["stu_hboard"] : ''; ?>"></td>
			<td><input type="text" placeholder="Subjects" name="stu_hsubject" style=" " value="<?php echo isset($_POST["stu_hsubject"]) ? $_POST["stu_hsubject"] : ''; ?>"></td>
			<td><input type="text" placeholder="Year" name="stu_hyear" style=" width:98%;" value="<?php echo isset($_POST["stu_hyear"]) ? $_POST["stu_hyear"] : ''; ?>"></td>

			<td>
			<input type="number" placeholder="Marks Obtained" class="1" id="myfirstnumber"  name="stu_hmobtain" value="<?php echo isset($_POST["stu_hmobtain"]) ? $_POST["stu_hmobtain"] : '';?>"/>
<!--<input type="text" placeholder="Marks obtained" name="Hmobtain" style="width:98%;" value="<?php //echo isset($_POST["Hmobtain"]) ? $_POST["Hmobtain"] : ''; ?>">-->

			</td>
			<td>
			<input type="number" placeholder="Max Marks" class="1" id="mysecondnumber" name="stu_hmmarks" value="<?php echo isset($_POST["stu_hmmarks"]) ? $_POST["stu_hmmarks"] : ''; ?>"/>
	<!--<input type="text" placeholder="Max Marks" name="Hmmarks" style=" width:98%;" value="<?php // echo isset($_POST["Hmmarks"]) ? $_POST["Hmmarks"] : ''; ?>">-->

			</td>
			<td>
			<input type="percentage" placeholder="%" class="2" id="mypercenttextbox" name="stu_hpercentage" value="<?php echo isset($_POST["stu_hpercentage"]) ? $_POST["stu_hpercentage"] : ''; ?>" readonly>
<!--<input type="text" placeholder="%" name="Hpercentage" value="P" style="width:98%; " value="<?php //echo isset($_POST["Hpercentage"]) ? $_POST["Hpercentage"] : ''; ?>">--->

			</td>
			<td><input type="text" placeholder="Passed/Appeared" name="stu_hpassed" style="" value="<?php echo isset($_POST["stu_hpassed"]) ? $_POST["stu_hpassed"] : ''; ?>"></td>
<script>
function ShowPercentage() {
    var FirstNumVal = 0 + Number($("#myfirstnumber").val());
    var SecondNumVal = 0 + Number($("#mysecondnumber").val());
  // $("#mypercenttextbox").val(Math.round(FirstNumVal/(SecondNumVal) * 100) / 100 + '%');
	$("#mypercenttextbox").val(Math.round(FirstNumVal/(SecondNumVal) * 100) + '%');
}

ShowPercentage();
$('input[type=number]').on('keyup', ShowPercentage);


</script>	

<script>
$( "#mysecondnumber" ).on('change',function(){ 
	if(parseInt($("#myfirstnumber").val()) > parseInt($("#mysecondnumber").val())) 
        { 

            alert("Obtained Marks Cannot Greater Than Max Marks.");
            $("#myfirstnumber").val('');
	 //   $("#mysecondnumber").val('');
	    $("#mypercenttextbox").val('');	
           // $("#myfirstnumber").focus();
            return false;
        }else{
            return true;    
        }
});
/*function Show() {
    //var marksscored = $(this).val();
    var marksscored = document.getElementById("myfirstnumber").value;
    var firstnumber = document.getElementById("mysecondnumber").value;
    if (parseInt(firstnumber)  < parseInt(marksscored) )
    {
        alert("Obtained Marks Cannot Greater Than Max Marks.");
        //$(this).val("");
	$("#myfirstnumber").val('');
	$("#mysecondnumber").val('');
	$("#mypercenttextbox").val('');			
    } else {
        // do something
    }
}*/
</script>
			
		</tr>
		<tr>	
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;">2</td>
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;">12th</td>
			<td><input type="text" placeholder="Intermediate" name="stu_icname" style=" " value="<?php echo isset($_POST["stu_icname"]) ? $_POST["stu_icname"] : '12'; ?>" readonly></td>
			<td><input type="text" placeholder="Institute name" name="stu_iinstitute" style=" " value="<?php echo isset($_POST["stu_iinstitute"]) ? $_POST["stu_iinstitute"] : ''; ?>"></td>
			<td><input type="text" placeholder="Bord/University" name="stu_iboard" style=" " value="<?php echo isset($_POST["stu_iboard"]) ? $_POST["stu_iboard"] : ''; ?>"></td>
			<td><input type="text" placeholder="Subjects" name="stu_isubject" style=" " value="<?php echo isset($_POST["stu_isubject"]) ? $_POST["stu_isubject"] : ''; ?>"></td>
			<td><input type="text" placeholder="Year" name="stu_iyear" style=" width:98%;" value="<?php echo isset($_POST["stu_iyear"]) ? $_POST["stu_iyear"] : ''; ?>"></td>
			
			<td>
			<input type="number" placeholder="Marks Obtained" class="1" id="myfirstnumber1" name="stu_imobtain" value="<?php echo isset($_POST["stu_imobtain"]) ? $_POST["stu_imobtain"] : ''; ?>"/>
<!--<input type="text"  placeholder="Marks obtained" name="Imobtain" style="width:98%;" value="<?php //echo isset($_POST["Imobtain"]) ? $_POST["Imobtain"] : ''; ?>">--->

</td>
			<td>
			<input type="number" placeholder="Max Marks" class="1" id="mysecondnumber1" name="stu_immarks" value="<?php echo isset($_POST["stu_immarks"]) ? $_POST["stu_immarks"] : ''; ?>"/>
<!--<input type="text" placeholder="Max Marks" name="Immarks" style=" width:98%;" value="<?php //echo isset($_POST["Immarks"]) ? $_POST["Immarks"] : ''; ?>">---></td>
			<td>
			<input type="percentage" placeholder="%" class="1" id="mypercenttextbox1" name="stu_ipercentage" value="<?php echo isset($_POST["stu_ipercentage"]) ? $_POST["stu_ipercentage"] : ''; ?>" readonly>
<!--<input type="text"  placeholder="%" name="Ipercentage" style="width:98%; " value="<?php //echo isset($_POST["Ipercentage"]) ? $_POST["Ipercentage"] : ''; ?>">--></td>
			<td><input type="text" placeholder="Passed/Appeared" name="stu_ipassed" style=" " value="<?php echo isset($_POST["stu_ipassed"]) ? $_POST["stu_ipassed"] : ''; ?>"></td>
<script>
function ShowPercentage() {
    var FirstNumVal = 0 + Number($("#myfirstnumber1").val());
    var SecondNumVal = 0 + Number($("#mysecondnumber1").val());
   // $("#mypercenttextbox1").val(Math.round(FirstNumVal/(SecondNumVal) * 100) / 100 + '%');
	$("#mypercenttextbox1").val(Math.round(FirstNumVal/(SecondNumVal) * 100) + '%');
}

ShowPercentage();
$('input[type=number]').on('keyup', ShowPercentage);
</script>
<script>
$( "#mysecondnumber1" ).on('change',function(){ 
	if(parseInt($("#myfirstnumber1").val()) > parseInt($("#mysecondnumber1").val())) 
        { 
            alert("Obtained Marks Cannot Greater Than Max Marks.");
            $("#myfirstnumber1").val('');
	   // $("#mysecondnumber1").val('');
	    $("#mypercenttextbox1").val('');	
            //$("#myfirstnumber1").focus();
            return false;
        }else{
            return true;    
        }
});
</script>	
		</tr>
		<tr>	
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;">3</td>
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;">Graduation</td>
			<td><input type="text" placeholder="Course Name" name="stu_gcname" style=" " value="<?php echo isset($_POST["stu_gcname"]) ? $_POST["stu_gcname"] : 'Under Graduate'; ?>" readonly></td>
			<td><input type="text"  placeholder="Institute Name" name="stu_ginstitute" style=" " value="<?php echo isset($_POST["stu_ginstitute"]) ? $_POST["stu_ginstitute"] : ''; ?>"></td>
			<td><input type="text"  placeholder="Bord/University" name="stu_gboard" style=" " value="<?php echo isset($_POST["stu_gboard"]) ? $_POST["stu_gboard"] : ''; ?>"></td>
			<td><input type="text"  placeholder="Subjects" name="stu_gsubject" style=" " value="<?php echo isset($_POST["stu_gsubject"]) ? $_POST["stu_gsubject"] : ''; ?>"></td>
			<td><input type="text"  placeholder="Year" name="stu_gyear" style="width:98%; " value="<?php echo isset($_POST["stu_gyear"]) ? $_POST["stu_gyear"] : ''; ?>"></td>

			<td>
			<input type="number" placeholder="Marks Obtained" class="1" id="myfirstnumber2" name="stu_gmobtain" value="<?php echo isset($_POST["stu_gmobtain"]) ? $_POST["stu_gmobtain"] : ''; ?>"/>
<!--<input type="text"  placeholder="Marks obtained" name="Gmobtain" style="width:98%;" value="<?php //echo isset($_POST["Gmobtain"]) ? $_POST["Gmobtain"] : ''; ?>">--></td>
			<td>
			<input type="number" placeholder="Max Marks" class="1" id="mysecondnumber2" name="stu_gmmarks" value="<?php echo isset($_POST["stu_gmmarks"]) ? $_POST["stu_gmmarks"] : ''; ?>"/>
<!--<input type="text"  placeholder="Max Marks" name="Gmmarks" style=" width:98%;" value="<?php //echo isset($_POST["Gmmarks"]) ? $_POST["Gmmarks"] : ''; ?>">--></td>
			<td>
			<input type="percentage" placeholder="%" class="1" id="mypercenttextbox2" name="stu_gpercentage" value="<?php echo isset($_POST["stu_gpercentage"]) ? $_POST["stu_gpercentage"] : ''; ?>" readonly>
<!--<input type="text"  placeholder="%" name="Gpercentage" style="width:98%; " value="<?php //echo isset($_POST["Gpercentage"]) ? $_POST["Gpercentage"] : ''; ?>">---></td>
			<td><input type="text"  placeholder="Passed/Appeared" name="stu_gpassed" style=" " value="<?php echo isset($_POST["stu_gpassed"]) ? $_POST["stu_gpassed"] : ''; ?>"></td>
<script>
function ShowPercentage() {
    var FirstNumVal = 0 + Number($("#myfirstnumber2").val());
    var SecondNumVal = 0 + Number($("#mysecondnumber2").val());
   // $("#mypercenttextbox2").val(Math.round(FirstNumVal/(SecondNumVal) * 100) / 100 + '%');
	$("#mypercenttextbox2").val(Math.round(FirstNumVal/(SecondNumVal) * 100) + '%');
}

ShowPercentage();
$('input[type=number]').on('keyup', ShowPercentage);


</script>
<script>
$( "#mysecondnumber2" ).on('change',function(){ 
	if(parseInt($("#myfirstnumber2").val()) > parseInt($("#mysecondnumber2").val())) 
        { 
            alert("Obtained Marks Cannot Greater Than Max Marks.");
            $("#myfirstnumber2").val('');
	   // $("#mysecondnumber2").val('');
	    $("#mypercenttextbox2").val('');	
           // $("#myfirstnumber2").focus();
            return false;
        }else{
            return true;    
        }
});
</script>
		</tr>
		<tr>	
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;">4</td>
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;">Post Graduation</td>
			<td><input type="text"  placeholder="Course Name" name="stu_pcname" style=" " value="<?php echo isset($_POST["stu_pcname"]) ? $_POST["stu_pcname"] : 'Post Graduate'; ?>" readonly></td>
			<td><input type="text"  placeholder="Institute Name" name="stu_pinstitute" style=" " value="<?php echo isset($_POST["stu_pinstitute"]) ? $_POST["stu_pinstitute"] : ''; ?>"></td>
			<td><input type="text"  placeholder="Bord/University" name="stu_pboard" style=" " value="<?php echo isset($_POST["stu_pboard"]) ? $_POST["stu_pboard"] : ''; ?>"></td>
			<td><input type="text"  placeholder="Subjects" name="stu_psubject" style=" " value="<?php echo isset($_POST["stu_psubject"]) ? $_POST["stu_psubject"] : ''; ?>"></td>
			<td><input type="text"  placeholder="Year" name="stu_pyear" style="width:98%; " value="<?php echo isset($_POST["stu_pyear"]) ? $_POST["stu_pyear"] : ''; ?>"></td>

			<td>
			<input type="number" placeholder="Marks Obtained" class="1" id="myfirstnumber3" name="stu_pmobtain" value="<?php echo isset($_POST["stu_pmobtain"]) ? $_POST["stu_pmobtain"] : ''; ?>"/>
<!--<input type="text"  placeholder="Marks obtained" name="Pmobtain" style="width:98%;" value="<?php //echo isset($_POST["Pmobtain"]) ? $_POST["Pmobtain"] : ''; ?>">--></td>
			<td>
			<input type="number" placeholder="Max Marks" class="1" id="mysecondnumber3" name="stu_pmmarks" value="<?php echo isset($_POST["stu_pmmarks"]) ? $_POST["stu_pmmarks"] : ''; ?>"/>
<!--<input type="text"  placeholder="Max Marks" name="Pmmarks" style=" width:98%;" value="<?php //echo isset($_POST["Pmmarks"]) ? $_POST["Pmmarks"] : ''; ?>">---></td>
			<td>
			<input type="percentage" placeholder="%" class="1" id="mypercenttextbox3" name="stu_ppercentage" value="<?php echo isset($_POST["stu_ppercentage"]) ? $_POST["stu_ppercentage"] : ''; ?>" readonly>
<!--<input type="text"  placeholder="%" name="Ppercentage" style="width:98%; " value="<?php //echo isset($_POST["Ppercentage"]) ? $_POST["Ppercentage"] : ''; ?>">--></td>
			<td><input type="text"  placeholder="Passed/Appeared" name="stu_ppassed" style=" " value="<?php echo isset($_POST["stu_ppassed"]) ? $_POST["stu_ppassed"] : ''; ?>"></td>
<script>
function ShowPercentage() {
    var FirstNumVal = 0 + Number($("#myfirstnumber3").val());
    var SecondNumVal = 0 + Number($("#mysecondnumber3").val());
   // $("#mypercenttextbox3").val(Math.round(FirstNumVal/(SecondNumVal) * 100) / 100 + '%');
 $("#mypercenttextbox3").val(Math.round(FirstNumVal/(SecondNumVal) * 100) + '%');
}

ShowPercentage();
$('input[type=number]').on('keyup', ShowPercentage);
</script>
<script>
$( "#mysecondnumber3" ).on('change',function(){ 
	if(parseInt($("#myfirstnumber3").val()) > parseInt($("#mysecondnumber3").val())) 
        { 
            alert("Obtained Marks Cannot Greater Than Max Marks.");
            $("#myfirstnumber3").val('');
	   // $("#mysecondnumber3").val('');
	    $("#mypercenttextbox3").val('');	
          //  $("#myfirstnumber3").focus();
            return false;
        }else{
            return true;    
        }
});
</script>
		</tr>
		<tr>	
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;">5</td>
			<td style="margin-left:120px;background-color:#7e7e7e;color:white;font-weight:bold;">Any Other</td>
			<td><input type="text"  placeholder="Course Name" name="stu_acname" style=" " value="<?php echo isset($_POST["stu_acname"]) ? $_POST["stu_acname"] : ''; ?>"></td>
			<td><input type="text"  placeholder="Institute name" name="stu_ainstitute" style=" " value="<?php echo isset($_POST["stu_ainstitute"]) ? $_POST["stu_ainstitute"] : ''; ?>"></td>
			<td><input type="text"  placeholder="Bord/University" name="stu_aboard" style=" " value="<?php echo isset($_POST["stu_aboard"]) ? $_POST["stu_aboard"] : ''; ?>"></td>
			<td><input type="text"  placeholder="Subjects" name="stu_asubject" style=" " value="<?php echo isset($_POST["stu_asubject"]) ? $_POST["stu_asubject"] : ''; ?>"></td>
			<td><input type="text"  placeholder="Year" name="stu_ayear" style=" width:98%;" value="<?php echo isset($_POST["stu_ayear"]) ? $_POST["stu_ayear"] : ''; ?>"></td>

			<td>
			<input type="number" placeholder="Marks obtained" class="1" id="myfirstnumber4" name="stu_amobtain" value="<?php echo isset($_POST["stu_amobtain"]) ? $_POST["stu_amobtain"] : ''; ?>"/>
<!--<input type="text"  placeholder="Marks obtained" name="Amobtain" style="width:98%;" value="<?php //echo isset($_POST["Amobtain"]) ? $_POST["Amobtain"] : ''; ?>">--></td>
			<td>
			<input type="number" placeholder="Max Marks" class="1" id="mysecondnumber4" name="stu_ammarks" value="<?php echo isset($_POST["stu_ammarks"]) ? $_POST["stu_ammarks"] : ''; ?>"/>
<!--<input type="text"  placeholder="Max M." name="Ammarks" style=" width:98%;" value="<?php //echo isset($_POST["Ammarks"]) ? $_POST["Ammarks"] : ''; ?>">--></td>
			<td>
			<input type="percentage" placeholder="%" class="1" id="mypercenttextbox4" name="stu_apercentage" value="<?php echo isset($_POST["stu_apercentage"]) ? $_POST["stu_apercentage"] : ''; ?>" readonly>
<!--<input type="text"  placeholder="%" name="Apercentage" style="width:98%; " value="<?php //echo isset($_POST["Apercentage"]) ? $_POST["Apercentage"] : ''; ?>">--></td>
			<td><input type="text"  placeholder="Passed/Appeared" name="stu_apassed" style=" " value="<?php echo isset($_POST["stu_apassed"]) ? $_POST["stu_apassed"] : ''; ?>"></td>
<script>
function ShowPercentage() {
    var FirstNumVal = 0 + Number($("#myfirstnumber4").val());
    var SecondNumVal = 0 + Number($("#mysecondnumber4").val());
   // $("#mypercenttextbox4").val(Math.round(FirstNumVal/(SecondNumVal) * 100) / 100 + '%');
$("#mypercenttextbox4").val(Math.round(FirstNumVal/(SecondNumVal) * 100)  + '%');
}

ShowPercentage();
$('input[type=number]').on('keyup', ShowPercentage);
</script>
<script>
$( "#mysecondnumber4" ).on('change',function(){ 
	if(parseInt($("#myfirstnumber4").val()) > parseInt($("#mysecondnumber4").val())) 
        { 
            alert("Obtained Marks Cannot Greater Than Max Marks.");
            $("#myfirstnumber4").val('');
	  //  $("#mysecondnumber4").val('');
	    $("#mypercenttextbox4").val('');	
           // $("#myfirstnumber4").focus();
            return false;
        }else{
            return true;    
        }
});
</script>
		</tr>
		
		</tbody>
		</table>

</br>
<table style="width:10%;">
	<tr>
			<td><input type="submit" name="stu_addmission" value="Submit" style="width:100%;height:35px;font-size:18px;"></td>
			<td><input type="reset" name="reset" value="Reset" style="width:100%;height:35px;font-size:18px;"></td>
		</tr>
</table>

</form>

</center>


<?php $this->load->view('template/footer'); ?>
</body>
</html>
