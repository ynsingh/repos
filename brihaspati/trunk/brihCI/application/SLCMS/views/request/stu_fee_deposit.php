<!-------------------------------------------------------
    -- @name stu_fee_deposit.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
--------------------------------------------------------->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');

?><!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>IGNTU:Student fees detail</title>
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
#down,#down2,#down3{display:none;}
</style>
<script>

$(document).ready(function(){
  	$('#drop').on('change', function() {
     		if ( this.value == 'semfee')
      		{
        		$("#down").show();
			
     		}
     		else
      		{
        		$("#down").hide();
      		}
	});
	$('#drop').on('change', function() {
     		if (( this.value == 'exmfee')||( this.value == 'finefee'))
      		{
        		$("#down2").show();
     		}
		else
      		{
        		$("#down2").hide();
      		}
     		
	});
	
});

</script>

</head>
<body>


<div>
	<div id="body">
	<?php $this->load->view('template/header'); ?>
	</br>
	<?php $this->load->view('template/stumenu'); ?>
	
<!--------------------------------------------------------------------------------------------------------------------------------------------------->
<?php
echo "<center>";

	if($this->session->flashdata('msg')){
echo "<div style='font-size:20px;text-align:center;background-color:#DFF2BF;width:50%;height:30px;color:green;'>";
	echo $this->session->flashdata('msg');
echo "<div>";	
}

echo "</center>";

	if((isset($_SESSION['success'])) && ($_SESSION['success'])!=''){
		echo "<div style=\"margin-left:30px;width:1700px;text-align:left;font-size:18px;border:1px ridge white;\" class=\"isa_success\">";
		echo '<div style="margin-left:40px;">'.$_SESSION['success'].'</div>';
		echo "</div>";
	}
	if((isset($_SESSION['error'])) && (($_SESSION['error'])!='')){
		echo "<div style=\"margin-left:30px;width:1700px;align:left;\" class=\"isa_error\">";
		echo $_SESSION['error'];
		echo "</div>";
	}
?>

<?php //echo $this->email=$this->Common_model->get_listspfic1('student_master','sm_email','sm_id',$id)->sm_email;?>

<h1>Fees Deposit</h1>
<center>
<div id="form">
	<div id="text">Student Fees Detail</div>
	</br>
<table>
	<tr>
		<td>Student Id :</td><td><?php echo $this->Sid;?></td>
		<td  width="200"></td>
		
		<td>Applicaton Number :</td><td><?php echo $this->appno;?></td>
	</tr>
	<tr height="10"></tr>
	<tr>
		
		
		<td>Name :</td><td><?php echo $this->sname;?></td>
		<td></td>
		<td>Father Name :</td><td><?php echo $this->fname;?></td>
		
	</tr>
		<tr height="10"></tr>
	<tr>
		<td>Academic Year : </td>
		<td>
		<?php 
		 echo $cacadyer;
		  ?>
		</td>
		<td></td>
		<td>Semester : </td>
	
		<td><?php echo $noofsemester;?></td>
		
		
	</tr>
	<tr height="10"></tr>
	<tr >
	<td>Gender</td><td><?php echo $this->gender;?></td>
	<td></td>
	<td>Programme</td><td>
	<?php foreach($this->resultprg as $prresult){
		 echo $prresult->prg_name.'('.$prresult->prg_branch.')';
	 }?>
	</td>
	</tr>
</table>
	<?php //} //}?>
</br>
<!---<form action="<?php echo site_url('request/fees_deposit_paymentfees_deposit');?>" method="POST">--->
<form action="<?php echo site_url('request/fees_deposit_payment');?>" method="POST">
<?php $totalfees = '';?>
	<select name="ftype" style="width:21%;height:35px;" id="drop">
		<option selected="true" disabled="disabled">Select Fees Type</option>
		<option value="semfee">Semester fees</option>
		<option value="exmfee">Exam fees</option>
		<option value="finefee">Panality fees</option>
	</select></br></br>
		
	<input type="text" name="fees" placeholder="Enter Your Fees" value="<?php //echo $this->pcounname; ?>" id="down2" />
	
<table class="TFtable" id="down">
	<thead>
		<tr>		
		<th><span style="float:left;">Details</span></th>
		<th><span style="float:left;">Amount</span></th>
		</tr>
	</thead>
		
	<tbody>
	<?php	
		
		//$this->progresult = $this->Common_model->get_list('fees_master');
		foreach($this->feesresult as $d2){
		 ?>
		<tr>
		<td><?php echo $d2->fm_head;?></td>
		
		<td><?php echo $d2->fm_amount;?></td>
		<?php $totalfees = $totalfees+$d2->fm_amount;?>
		</tr>
		
	<?php } ?>
		<thead style="font-size:18px;"><tr><th>Total</th><th><span style="float:left;"><?php echo $totalfees;?></span></th></tr></thead>		
		<input type="hidden" value="<?php echo $totalfees;?>" name="totalfees">
	</tbody>

</table>
		
	</br></br>
	
		<input type="submit" value="Online payment" name="online_pay" style="font-size:18px;">
		<input type="submit" name="offline_pay" value="Offline payment"style="font-size:18px;">
	
	</form>

</div>
</center>

<!--------------------------------------------------------------------------------------------------------------------------------------------------->

<?php $this->load->view('template/footer'); ?>

</body>
</html>
