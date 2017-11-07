<!-------------------------------------------------------
    -- @name new_applicant.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
--------------------------------------------------------->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');


?><!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>IGNTU:Applicant Registration</title>
	<link rel="shortcut icon" href="<?php echo base_url('assets/images'); ?>/index.jpg">
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/message.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/studentNavbar.css">
	 <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">   

	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery-ui.css">
      	<script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-1.12.4.js" ></script>
      	<script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.js" ></script> 

<style type="text/css">
label{font-size:18px;}
input[type='text']{font-size:17px;height:30px;background-color:white;font-family:Times New Roman;font-weight:bold;width:95%;}
input[type='email']{font-size:17px;height:30px;background-color:white;font-family:Times New Roman;font-weight:bold;}

tr td{font-size:15px;}
tr th{background:black;color:white;font-weight:bold;}
select{width:100%;font-size:17px;height:40px;}
textarea{font-size:17px;font-family:Times New Roman;font-weight:bold;text-align:bottom;}
</style>
<script>$(document).ready(function(){
$("#StartDate").datepicker({
dateFormat: 'yy/mm/dd',
numberOfMonths: 1,
onSelect: function(selected) {
$("#EndDate").datepicker("option","minDate", selected)
}
});

$("#EndDate").datepicker({ 
dateFormat: 'yy/mm/dd',
numberOfMonths: 1,
onSelect: function(selected) {
$("#StartDate").datepicker("option","maxDate", selected)
}
}); 
});
</script>
<script>
function getpostlist(tl){
	        var tl=tl;
                $.ajax({
                type: "POST",
		url: "<?php echo base_url();?>hrmindex.php/carrier/getposlist",
                data: {"aadvt" : tl},
                dataType:"html",
                success: function(data){
                $('#apost').html(data.replace(/^"|"$/g, ''));
		}
		
             });
		
        }

function getldate(ld){
	        var ld=ld;
                $.ajax({
                type: "POST",
		url: "<?php echo base_url();?>hrmindex.php/carrier/getplastdate",
                data: {"aadvt" : ld},
                dataType:"html",
                success: function(data){
                $('#actoffdate').html(data.replace(/^"|"$/g, ''));
		}
		
             });
		
        }


function getdeptname(dept){
	        var dept=dept;
		$.ajax({
                type: "POST",
		url: "<?php echo base_url();?>hrmindex.php/carrier/getdepartment",
                data: {"apost" : dept},
                dataType:"html",
                success: function(data){
                $('#adept').html(data.replace(/^"|"$/g, ''));
		}
		
             });
		
        }

function getpcode(pc){
	        var pc=pc;
		$.ajax({
                type: "POST",
		url: "<?php echo base_url();?>hrmindex.php/carrier/getpost_code",
                data: {"apost" : pc},
                dataType:"html",
                success: function(data){
                $('#apcode').html(data.replace(/^"|"$/g, ''));
		}
		
             });
		
        }
</script>
</head>
<body>


<div>
	<div id="body">
	<?php  // $thisPage2="studentaddDetail"; 
		$this->load->view('template/header'); ?>
</br>
	<?php $this->load->view('carrier/applicant_head'); ?>
	
<!--------------------------------------------------------ERROR DISPLAY-------------------------------------------------------------->

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

	
	<table style="width:100%;text-align:left;margin-left:2%;">
		<tr><td style="font-size:18px;margin-left:100px;">
		<?php
                   // echo anchor('hrmgmt/view_advertisement/', "View Advertisement" ,array('title' => 'View job advertisement' , 'class' => 'top_parent'));
                    //$help_uri = site_url()."/help/helpdoc#ViewEmailSetting";
                    //echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:74%\">Click for Help</b></a>";
          
           	 ?>
	</tr></td>
	</table>
<h1>New Applicant Registration</h1>
<center>
	  <table  style="background-color:#f1f1f1;" align="">
	
<center>
<form action="<?php echo site_url('carrier/applicant_step1'); ?>"  method="POST">

	

	<table style="width:65%;" class="" border=0>
	  <tr>
		<td>
			<label for="nnumber">Advt. No. IGNTU/Rec.Cell</label></br>
		    <select name="aadvt" style="width:100%;" id="aadvt" class="my_dropdown" onchange="getpostlist(this.value);getldate(this.value);">
			<option selected="true" disabled>Select Advt. No.</option>
			<?php foreach($this->advtno as $advt){?>
				<option value="<?php echo $advt->job_adverno;?>"><?php echo $advt->job_adverno;?></option>
			<?php }?>
		    </select>	
		</td>
	
		<td colspan=6>	</br>
			<label for="nnumber">Cut-off date for submission of complete application form is :</label>
			<!---<input type="text" name="actoffdate" id="cutoffdate" placeholder="Enter cut off date" style="margin-left:40px;width:31%;">-->
			<select name="actoffdate"  id="actoffdate" style="width:30%;margin-left:50px;" >
		   	</select>	
		<td/>

		<td>	
			<label for="nnumber">Date :</label></br>
			<?php $cdate = date('Y-m-d');?>
			<input type="text" name="acurrentdate" placeholder="Enter cut off date" value="<?php echo $cdate;?>" >	
		<td/>
		</tr>	 
	<tr height="10"></tr>
	  <tr>
		<td>	
			<label for="nnumber">Post applied</label></br>
			<!--<input type="text" name="Apost" placeholder="Enter applied post" value="<?php echo @$this->data['Apost'];?>">-->
		    <select name="apost"  id="apost" style="width:100%;" onchange="getdeptname(this.value);getpcode(this.value);">
			<option selected="true" disabled>Select post list</option>
		    </select>	
		<td/>
		<td>	
			<label for="nnumber">Department</label></br>
			<select name="adept"  id="adept" style="width:100%;">
		   	</select>
		<td/>
		<td>	
			<label for="nnumber">Post code</label></br>
			<select name="apcode"  id="apcode" style="width:100%;">
		   	</select>
		<td/>

		<td>	
			<label for="nnumber">Field of specialization</label></br>
			<input type="text" name="afieldspecia" placeholder="Enter specialization field" value="<?php echo @$this->data['afieldspecia'];?>">	
		<td/>
		<td>	
			<label for="nnumber">Applied under category</label></br>
			<input type="text" name="aucate" placeholder="Enter appliead category" value="<?php echo @$this->data['aucate'];?>">	
		<td/>
		
	</tr>
	<tr height="10"></tr>
	<tr>	
		<td>	
			<label for="nnumber">First name</label></br>
			<input type="text" name="afname" placeholder="Enter first name" value="<?php echo @$this->data['afname'];?>">	
		<td/>
		<td>	
			<label for="nnumber">Middle name</label></br>
			<input type="text" name="amname" placeholder="Enter middle name" value="<?php echo @$this->data['amname'];?>">	
		<td/>	
		<td>	
			<label for="nnumber">Last name</label></br>
			<input type="text" name="alname" placeholder="Enter last name" value="<?php echo @$this->data['alname'];?>">	
		<td/>	
		<td>	
			<label for="nnumber">Father's/Husband name</label></br>
			<input type="text" name="afathername" placeholder="Enter father name" value="<?php echo @$this->data['afathername'];?>">	
		<td/>
		<td>	
			<label for="nnumber">Mother name</label></br>
			<input type="text" name="amothername" placeholder="Enter mother name" value="<?php echo @$this->data['amothername'];?>">	
		<td/>

	    </tr>

	<tr height="10"></tr>
	<tr>
		<td>	
			<label for="nnumber">Aadhar number</label></br>
			<input type="text" name="aadharno" placeholder="Enter Aadhar number" value="<?php echo @$this->data['aadharno'];?>" MaxLength="12">	
		<td/>
		<td>	
			<label for="nnumber">Correspondence address</label></br>
			<textarea name="acadd" placeholder="Enter correspondence address" style="height:30px;width:95%;" value="<?php echo @$this->data['acadd'];?>"></textarea>		
		<td/>
		<td>	
			<label for="nnumber">Permanent address</label></br>
			<textarea name="apadd" placeholder="Enter permanant address" style="height:30px;width:95%;" value="<?php echo @$this->data['apadd'];?>" ></textarea>		
		<td/>
		<td>	
			<label for="nnumber">Date of birth</label></br>
			<input id="dob" type="text" name="adob" placeholder="Enter Your Dob" value="<?php echo @$this->data['adob']; ?>">

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
    				yearRange: 'c-67:c+30',
				});
			</script>		
		<td/>
		<td>	
			<label for="nnumber">Birth place</label></br>
			<input type="text" name="adobplace" placeholder="Enter birth place" value="<?php echo @$this->data['adobplace'];?>">		
		<td/>
		
	    </tr>
		
		<tr height="10"></tr>	

	    <tr>
		<td>	
			<label for="nnumber">Age as on cut-off date</label></br>	
			<input type="text" name="aagecutoff" placeholder="Enter age cut-off" value="<?php echo @$this->data['aagecutoff'];?>">			
		<td/>
	
		<td>	
			<label>Sex</label></br>
			<select name="agender" style="width:100%;">
				<option selected="true" disabled>Select your gender</option>
				<option value="male">Male</option>	
				<option value="female">Female</option>
			</select>	
		<td/>
		<td>	
			<label>Category</label></br>
			<select name="acate" style="width:100%;">
				<option v>Select your category</option>
				<option value="sc">SC</option>
				<option value="sc">ST</option>
				<option value="sc">OBC</option>
				<option value="sc">General</option>
			</select>
		<td/>
		<td>	
			<label>If person with disability</label></br>
			<select name="adisability" style="width:100%;">
				<option selected="true" disabled>Select disability</option>
				<option value="vh">VH</option>	
				<option value="oh">OH</option>
			</select>
		<td/>
		<td>	
			<label>Maritial status</label></br>
			<select name="amaritalstatus" style="width:100%;">
				<option selected="true" disabled>Select maritial status</option>
				<option value="married">Married</option>	
				<option value="un-married">Un-married</option>
			</select>
		<td/>
		
	</tr>
	<tr height=10></tr>
	<tr>
		<td>	
			<label>Nationality</label></br>
			<input type="text" name="anationality" value="<?php echo @$this->data['anationality'];?>" placeholder="Enter Nationality"/>
		<td/>

		<td>
			<label>Select religion</label></br>
			<select name="areligion" class="form-control"  style="width:100%;">
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
	
		<tr height=30></tr>
		<table style="width:15%;">
		<tr>
		<td><input type="submit" name="register" value="Submit" style="width:100%;height:35px;font-size:18px;"></td>
		<td><input type="reset" name="reset" value="Reset" style="width:100%;height:35px;font-size:18px;"></td>
		</tr>
		</table>

</table>	


</form>

</center>


<?php $this->load->view('template/footer'); ?>
</body>
</html>
