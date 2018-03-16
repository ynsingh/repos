<!-------------------------------------------------------
    -- @name sem_fee_offline.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
--------------------------------------------------------->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');

?><!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>IGNTU:Student offline payment</title>
	<link rel="shortcut icon" href="<?php echo base_url('assets/images'); ?>/index.jpg">
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/message.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/studentNavbar.css">
<style>
tr td {font-size:17px;font-weight:bold;}
</style>

</head>
<body>


<div>
	<div id="body">
	<?php $this->load->view('template/header'); ?>
	</br>
	<?php //$this->load->view('template/stumenu'); ?>
	<?php //$this->load->view('student/stuStepshead');?>
	
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
        <div class="isa_error" style=""><span ><?php echo $_SESSION['err_message'];?></span></div>
        	<?php
        		};
		?>  
      </div>
<center>
<h1>Fees payment</h1>

<form action="<?php echo site_url('request/fees_deposit_payment'); ?>" method="POST">
	<table>
		<tr>	
		
		<td>Payble Fees :</td>
		 
		<td><input type="text" name="fees" value="<?php echo $fees;?>" readonly></td>
		</tr>

		<tr>	
		
		<td>Reference No. :</td>
		<td><input type="text" name="refNo" value="<?php echo $refNo; ?>"></td>
		</tr>
		<tr>
		<td>Bank Name :</td>
		<td><input type="text" name="bank" value="<?php echo $bank; ?>"></td>
		</tr><tr>
		<td>Amount :</td>
			
		<td><input type="text" name="amount" value="<?php echo $amount;//echo $totalfees;?>"></td></tr>
		<tr>
		<td>Fee type : </td>
		<td><!--<input type="text" name="ftype" value="<?php echo @$this->data['ftype']; ?>"> </td><td>Ex.1 semester.--->
			<select name="ftype" style="width:100%;height:30px;">
				<!--<option selected="true" disabled="disabled">Select Fees Type</option>--->
				<?php if($ftype == "semfee"){?>			
				<option value="semfee">Semester fees</option>
				<?php }?>
				<?php if($ftype == "exmfee"){ ?>
				<option value="exmfee">Exam fees</option>
				<?php }?>
				<?php if($ftype == "finefee"){?>
				<option value="finefee">Panality fees</option>
				<?php }?>
				<!---<option value="otherfee">Other fees</option>--->
			</select>
		</td>
		</tr>
		<tr>
		<td></td>
		<td>
		
<?php if($ftype == "semfee"){?>
	<input type="hidden" value="<?php echo $fees;?>" name="totalfees">
<?php }?>
		<input type="submit" name="sem_payment" value="Submit" style="font-size:17px;">
		
		<input type="submit" name="refNo" value="Clear" style="font-size:17px;"></td>
		</tr>
	</tr>
	</table>
<span style="font-size:15px;"><b>Note : If any cirumstances your fee detail is wrong your registration is cancelled.</b></span>

</form>
</center>
<!--------------------------------------------------------------------------------------------------------------------------------------------------->

<?php $this->load->view('template/footer'); ?>
<?php // } else {  header("location:student/student_step0"); }?>
</body>
</html>
