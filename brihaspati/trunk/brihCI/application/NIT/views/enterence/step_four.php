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
	<link rel="stylesheet" type="text/css" media="all" href="<?php echo base_url(); ?>assets/css/Studentsteps.css" />
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/bootstrap.min.css">
<style>tr td {font-size:17px;font-weight:bold;}
</style>
</head>
<body>


<div>
	<div id="body">
	<?php $this->load->view('template/header'); ?>
	<nav><h2><?php echo "Welcome ". $this->email;?></h2></nav></br>
	<?php $this->load->view('enterence/admission_steps');?>

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
<!--<h1>Payment</h1>
	<table style="width:100%;" >
		<tr>
		<td> </br>
			<select name="paytype" style="width:20%;height:30px;"  id="dbType">
				<option selected="true" disabled="disabled">Select Payment Mode</option>
				<option value="offline">Offline payment</option>
				<option value="online">Online payment</option>
			</select>
		</td>
		</tr>

	</table>-->
</center>
<!--------------------------------------------------------------------------------------------------------------------------------------------------->
<script>
$('#dbType').on('change',function(){
    if( $(this).val()==="offline"){
    $("#otherType").show();
    $("#otherType1").hide();	
    }
    

if( $(this).val()==="online"){
    $("#otherType1").show();	
    $("#otherType").hide()
    }
    
});	
</script>
<!---------------------------------------------------online payment----------------------------------------------------------->
<!--<div id="otherType1" style="display:none;">-->
<h3>Online Payment</h3>
 <div class="container" style="text-align:left;">
            <div class="row">
                <div class="col-md-3"></div>  
                <div class="col-md-6">
                 <form action="<?= $action; ?>/_payment" method="post" id="payuForm" name="payuForm">
		  <!--<form action="<?php echo site_url('payumoney'); ?>" method="POST" id="payuForm" name="payuForm">--->
                        <input type="hidden" name="key" value="<?= $mkey ?>" />
                        <input type="hidden" name="hash" value="<?= $hash ?>"/>
                        <input type="hidden" name="txnid" value="<?= $tid ?>" />

                        <div class="form-group">
                            <label class="control-label">Total Payable Amount</label>
				<input class="form-control" name="amount" id="amount" value="<?= $amount; ?>" readonly/>
			</div>
                        <div class="form-group">
                            <label class="control-label">Your Name</label>
                            <input class="form-control" name="firstname" id="name" value="<?= $name; ?>" readonly/>
                        </div>
                        <div class="form-group">
                            <label class="control-label">Email</label>
                            <input class="form-control" name="email" id="mailid" value="<?= $mailid; ?>" readonly/>
                        </div>
                        <div class="form-group">
                            <label class="control-label">Phone</label>
                            <input class="form-control" name="phone" value="<?= $phoneno; ?>" readonly />
                        </div>
                        <div class="form-group">
                            <label class="control-label"> Program (Branch)</label>
                            <textarea class="form-control" name="productinfo" readonly><?= $productinfo; ?></textarea>
                        </div>
                        <div class="form-group">
                            <label class="control-label"> Fees Type</label>
			    <input class="form-control" type="text" name="address1" value="<?php echo 'Entrance Exam fees'; ?>" readonly>
                            <!--<input class="form-control" name="address1" value="<?= $address ?>" readonly/>     ---->
			   <!-- <select name="address1" class="form-control">
				<option selected="true" disabled="disabled">Select Fees Type</option>
				<option value="semfee">Semester fees</option>
				<option value="exmfee">Exam fees</option>
				<option value="finefee">Panality fees</option>
				</select>
-->
                        </div>
                        <div class="form-group">
                            <input name="surl" value="<?=$surl ?>" size="64" type="hidden" />
                            <input name="furl" value="<?=$furl ?>" size="64" type="hidden" />                             
                            <input type="hidden" name="service_provider" value="" size="64" /> 
                            <input name="curl" value="<?//= $cancel ?> " type="hidden" />
                        </div>
                        <div class="form-group text-center">
                        <input type="submit" value="Pay Now" class="btn btn-success" /></td>
                        </div>
                    </form>                                  
                </div>
                <div class="col-md-2"></div>
            </div>
        </div>    

<!--</div>-->

<!---------------------------------------------------offline payment----------------------------------------------------------->
<center>

<form action="<?php echo site_url('enterence/offlinePayment'); ?>" method="POST" name="myform">
<div id="otherType" style="display:none;">
<h3>Offline Payment</h3>		
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
		<td><input type="text" style="width:32%;" name="amount" value="<?php echo 300.00; ?>" readonly></td>
		<?php }?>
		<?php if($this->catname == "SC" || $this->catname == "ST"){?>
		<td><input type="text" style="width:32%;" name="amount" value="<?php echo 100.00; ?>" readonly></td>
		<?php }?>
		</tr>
		<tr>
		<td>Fee type : </td>
		<td><input type="text" style="width:32%;" name="ftype" value="<?php echo isset($_POST["ftype"]) ? $_POST["ftype"] : 'Entrance Exam fees'; ?>" readonly></td>
		<!--<td><!--<input type="text" name="ftype" value="<?php echo @$this->data['ftype']; ?>"> </td><td>Ex.1 semester.---
			<select name="ftype" style="width:33%;height:30px;">
				<option selected="true" disabled="disabled">Select Fees Type</option>
				<option value="semfee">Semester fees</option>
				<option value="exmfee">Exam fees</option>
				<option value="finefee">Panality fees</option>
				<!--<option value="otherfee">Other fees</option>--
			</select>
		</td> -->
		</tr>

</table>

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
</div>
</form>
</center>
<!--------------------------------------------------------------------------------------------------------------------------------------------------->

<?php $this->load->view('template/footer'); ?>
<?php  //} else {  header("location:student/student_step0"); }?>
</body>
</html>
