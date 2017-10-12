<!-------------------------------------------------------
    -- @name step_four.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
--------------------------------------------------------->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
//if (isset($this->session->userdata['sm_id'])) {
//$id = ($this->session->userdata['sm_id']);
//print_r($id);
//$firstname = ($this->session->userdata['sm_fname']);
//$applino = ($this->session->userdata['sm_applicationno']);
?><!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>IGNTU:offline payment</title>
	<link rel="shortcut icon" href="<?php echo base_url('assets/images'); ?>/index.jpg">
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/message.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/studentNavbar.css">
<style>tr td {font-size:17px;font-weight:bold;}
</style>
</head>
<body>


<div>
	<div id="body">
	<?php $this->load->view('template/header'); ?>
	<nav> 	<h1>Welcome to IGNTU  </h1></nav></br>
	<?php $this->load->view('enterence/admission_steps');?>
	
<!--------------------------------------------------------------------------------------------------------------------------------------------------->
<?php
echo "<center>";

echo "<center>";

	if($this->session->flashdata('msg')){
		echo "<div style='font-size:18px;text-align:center;background-color: #FFBABA;width:50%;height:30px;color: #D8000C;'>";
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

      </div>
<div align="left" style="margin-left:30px;width:1700px;font-size:18px;">
       	 	<?php echo validation_errors('<div class="isa_warning">','</div>');?>
        	<?php echo form_error('<div style="margin-left:30px;" class="isa_error">','</div>');?>
       		<?php if(isset($_SESSION[''])){?>
        	<div class="alert alert-success"><?php echo $_SESSION[''];?></div>
       	 	<?php
    		 	};
       		?>
        	<?php if(isset($_SESSION['err_message'])){?>
        <div class="isa_error" style=""><span ><?php echo $_SESSION['err_message'];?></span></div>
        	<?php
        		};

		?>  
      </div>
<center>
<h1>Payment</h1>
	<table style="width:54%;" >
		<tr>
		<td>Payment type : </br>
			<select name="paytype" style="width:30%;height:30px;"  id="dbType">
				<option selected="true" disabled="disabled">Select Fees Type</option>
				<option value="offline">Offline payment</option>
				<option value="online">Online payment</option>
			</select>
		</td>
		</tr>

	</table>
<script>
$('#dbType').on('change',function(){
    if( $(this).val()==="offline"){
    $("#otherType").show();
    }
    

if( $(this).val()==="online"){
    $("#otherType").hide()
    }
    
});	
</script>

<form action="<?php echo site_url('enterence/offlinePayment'); ?>" method="POST" name="myform">
<div id="otherType" style="display:none;">		
<table style="width:54%;" >
<tr>	
		<td style="width:20%;">Reference No. :</td>
		<td><input type="text" style="width:32%;" name="refno" value="<?php echo isset($_POST["refno"]) ? $_POST["refno"] : ''; ?>"></td>
		</tr>
		<tr>
		<td>Bank Name :</td>
		<td><input type="text" style="width:32%;" name="bank" value="<?php echo isset($_POST["bank"]) ? $_POST["bank"] : ''; ?>"></td>
		</tr><tr>
		<td>Amount :</td>
		<?php $this->catname;
		if($this->catname == "General" || $this->catname == "OBC"){?>
		<td><input type="text" style="width:32%;" name="amount" value="<?php echo 300; ?>" readonly></td>
		<?php }?>
		<?php if($this->catname == "SC" || $this->catname == "ST"){?>
		<td><input type="text" style="width:32%;" name="amount" value="<?php echo 100; ?>" readonly></td>
		<?php }?>
		</tr>
		<tr>
		<td>Fee type : </td>
		<td><!--<input type="text" name="ftype" value="<?php echo @$this->data['ftype']; ?>"> </td><td>Ex.1 semester.--->
			<select name="ftype" style="width:33%;height:30px;">
				<option selected="true" disabled="disabled">Select Fees Type</option>
				<option value="semfee">Semester fees</option>
				<option value="exmfee">Exam fees</option>
				<option value="finefee">Panality fees</option>
				<option value="otherfee">Other fees</option>
			</select>
		</td>
		</tr>

</table>
</div>
</br>
<table>
<tr>
<td>
<span style="font-size:15px;"><b>Note : I hereby solemnly declare that the information submitted by me is true and correct.</b></span></br>
</td></tr>
<tr><td>
	<span style="font-size:15px;"><b>I understand that if any information given by me  as above is found to be incorrect at any stage my candidature for the course shall stand cancelled.</b></span></br>
</td></tr>
<tr><td>
<input type="checkbox" name="agree" style="font-size:17px;" value="agree" id="termsChkbx">I Agree
</td></tr>
</table>
<script>
  document.getElementById('termsChkbx').addEventListener('click', function (e) {
  document.getElementById('sub1').disabled = !e.target.checked;
});

function resetform() {
document.getElementById("myform").reset();
}
</script>
</br>

<table>
<tr>
		<td></td>
		<td> 
		<input type="submit" name="offline" value="Submit" style="font-size:17px;" id="sub1" disabled="disabled">
		<input type="reset"  name="refNo" value="Reset" style="font-size:17px;" onclick="resetform()"></td>
		</tr>
</table>

</form>
</center>
<!--------------------------------------------------------------------------------------------------------------------------------------------------->

<?php $this->load->view('template/footer'); ?>
<?php  //} else {  header("location:student/student_step0"); }?>
</body>
</html>
