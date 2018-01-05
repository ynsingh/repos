<!-------------------------------------------------------
    -- @name stu_feesoffline.php --	
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

//echo "</center>";
	//if((isset($_SESSION['success'])) && ($_SESSION['success'])!=''){
		//echo "<div style=\"margin-left:30px;width:1700px;align:left;\" class=\"isa_success\">";
		//echo $_SESSION['success'];
		//echo "</div>";
	//}
	//if((isset($_SESSION['error'])) && (($_SESSION['error'])!='')){
		//echo "<div style=\"margin-left:30px;width:1700px;align:left;\" class=\"isa_error\">";
		//echo $_SESSION['error'];
		//echo "</div>";
	//}
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

<form action="<?php echo site_url('request/stufeespayment'); ?>" method="POST">
	<table>
		<tr>	
		
		<td>Payble Fees :</td>
		<td><input type="text" name="payblefees" value="<?php echo $this->totalfees; ?>" readonly></td>
		</tr>

		<tr>	
		
		<td>Reference No. :</td>
		<td><input type="text" name="refNo" value="<?php echo @$this->data['refNo']; ?>"></td>
		</tr>
		<tr>
		<td>Bank Name :</td>
		<td><input type="text" name="bank" value="<?php echo @$this->data['bank']; ?>"></td>
		</tr><tr>
		<td>Amount :</td>
		<?php	
		/*$totalfees = '';
		//$this->progresult = $this->Common_model->get_list('fees_master');
		foreach($this->feesresult as $d2){
		$totalfees = $totalfees+$d2->fm_amount; } */?>
		<td><input type="text" name="amount" value="<?php @$this->data['amount'];//echo $totalfees;?>"></td></tr>
		<tr>
		<td>Fee type : </td>
		<td><!--<input type="text" name="ftype" value="<?php echo @$this->data['ftype']; ?>"> </td><td>Ex.1 semester.--->
			<select name="ftype" style="width:100%;height:30px;">
				<option selected="true" disabled="disabled">Select Fees Type</option>
				<option value="semfee">Semester fees</option>
				<option value="exmfee">Exam fees</option>
				<option value="finefee">Panality fees</option>
				<!---<option value="otherfee">Other fees</option>--->
			</select>
		</td>
		</tr>

		<tr>
		<td></td>
		<td>
		<!--<input type="hidden" name="fid" value="<?php //echo $id;?>">
		<input type="hidden" name="fname" value="<?php //echo $this->Common_model->get_listspfic1('student_master','sm_fname','sm_id',$id)->sm_fname;?>">
		<input type="hidden" name="semail" value="<?php //echo $this->Common_model->get_listspfic1('student_master','sm_email','sm_id',$id)->sm_email;?>">
		<input type="hidden" name="smobile" value="<?php //echo $this->Common_model->get_listspfic1('student_master','sm_mobile','sm_id',$id)->sm_mobile;?>">-->


		<input type="submit" name="payment" value="Submit" style="font-size:17px;">
		
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
