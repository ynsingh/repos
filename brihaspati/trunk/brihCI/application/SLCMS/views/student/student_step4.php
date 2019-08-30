<!-------------------------------------------------------
    -- @name student_step4.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
--------------------------------------------------------->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
if (isset($this->session->userdata['sm_id'])) {
$id = ($this->session->userdata['sm_id']);
//$firstname = ($this->session->userdata['sm_fname']);
//$applino = ($this->session->userdata['sm_applicationno']);
?><!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>SLCMS:Student fees detail</title>
	<link rel="shortcut icon" href="<?php echo base_url('assets/images'); ?>/index.jpg">
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/message.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/studentNavbar.css">
	 <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">   
<script>
$(document).on('click', '.browse', function(){
  var file = $(this).parent().parent().parent().find('.file');
  file.trigger('click');
});
$(document).on('change', '.file', function(){
  $(this).parent().find('.form-control').val($(this).val().replace(/C:\\fakepath\\/i, ''));
});
</script>
<style type="text/css">
label{font-size:18px;}
input[type='text']{font-size:17px;height:30px;background-color:white;}


tbody tr td{font-size:18px;}
thead tr th{color:white;font-weight:bold;font-size:18px;}
select{width:100%;font-size:17px;height:40px;}

#text{background-color:#38B0DE;color:white;font-size:20px;font-weight:bold;opacity:1.5;height:30px;padding:5px;}
#form{ border:1px solid black;width:60%;}
</style>

</head>
<body>


<div>
	<div id="body">
	<?php $this->load->view('template/header2'); ?>
	<div class="welcome"><h2>Welcome : <?php echo $mailid;?></h2></div>
	<?php $this->load->view('student/stuStepshead');?>
<!--------------------------------------------------------------------------------------------------------------------------------------------------->
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
<h1>Fees Submission</h1>
<center>
<div id="form"  style="width:80%;">
	<div id="text">Student Details</div>
	</br>

<table >
	<tr>
		<td>Student Id :</td><td><?php echo $id;?></td>
		<td  width="200"></td>
		
		<td>Applicaton Number :</td><td><?php echo $this->appno;?></td>
	</tr>
	<tr height="10"></tr>
	<tr>
		
		
		<td>Name :</td><td><?php echo $name;?></td>
		<td></td>
		<td>Father Name :</td><td><?php echo $this->fname;?></td>
		
	</tr>
		<tr height="10"></tr>
	<tr>
		<td>Academic Year : </td>
		<td>
		<?php 
		 echo $this->acadyear;
		  ?>
		</td>
		<td></td>
		<td>Semester : </td>
	
		<td>1<sup>st</sup> Sem.</td>
		
		
	</tr>
	<tr height="10"></tr>
	<tr >
	<td>Gender :</td><td><?php echo $this->gender;?></td>
	<td></td>
	<td>Programme :</td><td>
	
	<?php echo $productinfo;?>
	</td>
	</tr>
</table>
	<?php //} //}?>
</br>

<table class="TFtable">
	<thead>
		<tr>		
		<th><span style="float:left;">Details :</span></th>
		
		<th><span style="float:left;">Amount :</span></th>
		
		</tr>
	</thead>
		
	<tbody>
	<?php	
		$amount = 0;
		//$this->progresult = $this->Common_model->get_list('fees_master');
		foreach($this->feesresult as $d2){
		 ?>
		<tr>
		<td><?php echo $d2->fm_head;?></td>
		
		<td><?php echo $d2->fm_amount;?></td>
		<?php $amount = $amount+$d2->fm_amount;?>
		</tr>
		
	<?php } ?>
		<thead style="font-size:18px;"><tr><th>Total :</th><th><span style="float:left;"><?php echo $amount;?></span></th></tr></thead>		
		
	</tbody>

</table>
	</br></br>
<form action="<?= $action; ?>/_payment" method="post" id="payuForm" name="payuForm">
	 <input type="hidden" name="key" value="<?= $mkey ?>" />
	 <input type="hidden" name="hash" value="<?= $hash ?>"/>
 	 <input type="hidden" name="txnid" value="<?= $tid ?>" />
	 <input type="hidden" name="amount" id="amount" value="<?= $amount; ?>" readonly/>
 	 <input type="hidden" name="firstname" id="name" value="<?= $name; ?>" readonly/>
 	 <input type="hidden" name="email" id="mailid" value="<?= $mailid; ?>" readonly/>
	 <input type="hidden" name="phone" value="<?= $phoneno; ?>" readonly />
     	 <textarea  name="productinfo" style="display:none;" readonly><?= $productinfo; ?></textarea>
 	 <input type="hidden" type="text" name="address1" value="<?php echo 'Semester fees'; ?>" readonly>

	 <div class="form-group">
                <input name="surl" value="<?=$surl ?>" size="64" type="hidden" />
                <input name="furl" value="<?=$furl ?>" size="64" type="hidden" />                             
                <input type="hidden" name="service_provider" value="" size="64" /> 
                <input name="curl" value="<?//= $cancel ?> " type="hidden" />
          </div>
	<?php if($amount){?>
	  <input type="submit" value="Online Payment" class="btn btn-success" style="font-size:18px;"/>		
	<?php }?>
		<!--<input type="hidden" name="totalfees" value="<?php echo $totalfees;?>" >
		<input type="submit" value="Online payment" style="font-size:18px;">
		<a href="<?php echo site_url('Student/offlinePayment');?>" style="text-decoration:none;" ><input type="button" value="Offline payment"style="font-size:18px;"></a>-->
</form>

</div>
</center>

<!--<h3>Online Payment</h3>
 <div class="container" style="text-align:left;">
            <div class="row">
                <div class="col-md-3"></div>  
                <div class="col-md-6">
                 <form action="<?= $action; ?>/_payment" method="post" id="payuForm" name="payuForm">
		  <--<form action="<?php echo site_url('payumoney'); ?>" method="POST" id="payuForm" name="payuForm">---
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
			    <input class="form-control" type="text" name="address1" value="<?php //echo 'Entrance Exam fees'; ?>" readonly>
                            <--<input class="form-control" name="address1" value="<?= $address ?>" readonly/>     ----
			   <-- <select name="address1" class="form-control">
				<option selected="true" disabled="disabled">Select Fees Type</option>
				<option value="semfee">Semester fees</option>
				<option value="exmfee">Exam fees</option>
				<option value="finefee">Panality fees</option>
				</select>
--
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
        </div> -->


<!--------------------------------------------------------------------------------------------------------------------------------------------------->

<?php $this->load->view('template/footer'); ?>
<?php  } //else {  header("location:student/student_step0"); }
else{header("location:".base_url()."Student/Step0");}?>
</body>
</html>
