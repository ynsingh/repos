<!-------------------------------------------------------
    -- @name adver_add_detail.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
--------------------------------------------------------->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');


?><!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>IGNTU:Advertisement detail</title>
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
input[type='text']{font-size:17px;height:30px;background-color:white;font-family:Times New Roman;font-weight:bold;}
input[type='email']{font-size:17px;height:30px;background-color:white;font-family:Times New Roman;font-weight:bold;}
input[type='textarea']

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
$("#LastDate").datepicker("option","minDate", selected)
}
});

$("#LastDate").datepicker({ 
dateFormat: 'yy/mm/dd',
numberOfMonths: 1,
onSelect: function(selected) {
$("#StartDate").datepicker("option","maxDate", selected)
}
}); 
});


</script>
</head>
<body>


<div>
	<div id="body">
	<?php  // $thisPage2="studentaddDetail"; 
		$this->load->view('template/header'); ?>
</br>
	<?php $this->load->view('template/menu'); ?>
	
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
                    echo anchor('hrmgmt/view_advertisement/', "View Advertisement" ,array('title' => 'View job advertisement' , 'class' => 'top_parent'));
                    //$help_uri = site_url()."/help/helpdoc#ViewEmailSetting";
                    //echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:74%\">Click for Help</b></a>";
          
           	 ?>
	</tr></td>
	</table>
<center>
	  <table  style="background-color:#f1f1f1;" align="">
	
<center>
<form action="<?php echo site_url('hrmgmt/add_advertisement'); ?>"  method="POST">

	<table style="width:65%;">
	   <tr>
		<td>	
			<label for="nnumber">Vacancy Code</label></br>
			<input type="text" name="Jvcode" placeholder="Enter vacancy code" value="<?php echo @$this->data['Jvcode'];?>">	
		<td/>
		<td>	
			<label for="nnumber">Advertisement No.</label></br>
			<input type="text" name="Jadvno" placeholder="Enter advertisement no." value="<?php echo @$this->data['Jadvno'];?>">	
		<td/>

		<td>	
			<label for="nnumber">Post code</label></br>
			<input type="text" name="Jpcode" placeholder="Enter post code." value="<?php echo @$this->data['Jpcode'];?>">	
		<td/>
		<td>	
			<label for="nnumber">Name of post</label></br>
			<input type="text" name="Jname" placeholder="Enter post name" value="<?php echo @$this->data['Jname'];?>">	
		<td/>
		<td>
			<label for="nnumber">Name of department</label></br>
			<input type="text" name="Jdept" placeholder="Enter post department" value="<?php echo @$this->data['Jdept'];?>">	
		<td/>
	</tr>
	<tr height="10"></tr>
	<tr>	
		<td>	
			<label for="nnumber">Vacancy for sc(In number)</label></br>
			<input type="text" name="Jvacsc" placeholder="Enter vacancy for sc" value="<?php echo @$this->data['Jvacsc'];?>">	
		<td/>
		<td>	
			<label for="nnumber">Vacancy for st(In number)</label></br>
			<input type="text" name="Jvacst" placeholder="Enter vacancy for st" value="<?php echo @$this->data['Jvacst'];?>">	
		<td/>	
		<td>	
			<label for="nnumber">Vacancy for obc(In number)</label></br>
			<input type="text" name="Jvacobc" placeholder="Enter vacancy for obc" value="<?php echo @$this->data['Jvacobc'];?>">	
		<td/>	
		<td>	
			<label for="nnumber">Vacancy for ur(In number)</label></br>
			<input type="text" name="Jvacur" placeholder="Enter vacancy for ur" value="<?php echo @$this->data['Jvacur'];?>">	
		<td/>
		<td>	
			<label for="nnumber">Vacancy for pwd(In number)</label></br>
			<input type="text" name="Jvacpwd" placeholder="Enter vacancy for pwd" value="<?php echo @$this->data['Jvacpwd'];?>">	
		<td/>

	    </tr>

	<tr height="10"></tr>
	<tr>
		
		
				
		<td>	
			<label for="nnumber">Pay band/Grade pay</label></br>
			<input type="text" name="Jgrade" placeholder="Enter post pay grade/garde pay" value="<?php echo @$this->data['Jgrade'];?>">	
		<td/>
		<td>	
			<label for="nnumber">Post emoluments</label></br>
			<input type="text" name="Jemolu" placeholder="Enter post emoluments" value="<?php echo @$this->data['Jemolu'];?>">		
		<td/>
		<td>	
			<label for="nnumber">Job age limit</label></br>
			<input type="text" name="Jagelimit" placeholder="Enter post age limit" value="<?php echo @$this->data['Jagelimit'];?>">		
		<td/>
		<td>	
			<label for="nnumber">Job group</label></br>
			<input type="text" name="Jgroup" placeholder="Enter post group" value="<?php echo @$this->data['Jgroup'];?>">		
		<td/>
		<td>	
			<label for="nnumber">Job Essential</label></br>	
			<textarea name="Jessen" rows="1" cols="19" placeholder="Enter post essential" value="<?php echo @$this->data['Jessen'];?>"></textarea>		
		<td/>
		
	    </tr>
		
		<tr height="10"></tr>	

	    <tr>	
		
		
		<td>	
			<label>Job experience</label></br>
			<textarea name="Jexper" rows="1" cols="19" placeholder="Enter post experience" value="<?php echo @$this->data['Jexper'];?>"></textarea>		
		<td/>
		<td>	
			<label for="nnumber">Job desirable</label></br>
			<textarea name="Jdesir" rows="1" cols="19" placeholder="Enter post desirable"value="<?php echo @$this->data['Jdesir'];?>" ></textarea>
		<td/>
		<td>	
			<label>Job responsible</label></br>
			<textarea name="Jrespon" rows="1" cols="19" placeholder="Enter post responsible" value="<?php echo @$this->data['Jrespon'];?>"></textarea>
		<td/>
		<td>	
			<label>Start date online form</label></br>
			<input type="text" name="Jsonline" id="StartDate" class="form-control"  value="<?php echo @$this->data['Jsonline'];?>" placeholder="Enter last date of online form"/>
		<td/>

		<td>	
			<label>Last date online form</label></br>
			<input type="text" name="Jlonline" id="LastDate" class="form-control"  value="<?php echo @$this->data['Jlonline'];?>" placeholder="Enter last date of online form"/>
		<td/>
		
	</tr>

	<tr height=10></tr>

	<tr>
		<td>	
			<label>Last date form reached</label></br>
			<input id="Jlastreach" type="text" name="Jlastreach" placeholder="Enter last date form reach" value="<?php echo @$this->data['Jlastreach']; ?>">

			<script>
				$('#Jlastreach').datepicker({
 				onSelect: function(value, ui) {
 			        console.log(ui.selectedYear)
       				var today = new Date(), 
         			dob = new Date(value), 
          			age = 2017-ui.selectedYear;
   				},
  	 			
    				dateFormat: 'yy/mm/dd',
				numberOfMonths: 1,
				});
			</script>	
		<td/>	
	</tr>	
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
