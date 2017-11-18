<?php defined('BASEPATH') OR exit('No direct script access allowed');
/*
    @name stu_hallticket.php  
    @author Sumit Saxena(sumitsesaxena@gmail.com)
    
*/

    defined('BASEPATH') OR exit('No direct script access allowed');
?>
<html>
<head>
<title>IGNTU - Download Hall Ticket</title>
    <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery.datetimepicker.css"/>
    <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
   
    <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
    <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.js" ></script>
    <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>

</head>
<body>

<center>
<?php
    $this->load->view('template/header'); ?></br>

   <?php $this->load->view('enterence/enterence_head');?>
	
     
	<div>
        <?php echo validation_errors('<div class="isa_warning">','</div>');?>
        <?php echo form_error('<div class="">','</div>');?>
        <?php if(isset($_SESSION['success'])){?>
        <div class="alert alert-success"><?php echo $_SESSION['success'];?></div>
        <?php
    	 };
       	?>
	
        <?php if(isset($_SESSION['err_message'])){?>
             <div class="isa_error"><div ><?php echo $_SESSION['err_message'];?></div></div>
        <?php
        };
	?>  

</center>

<center>

    <form action="<?php echo site_url('enterence/stu_hallticket'); ?>" method="POST" >
    <table style="border:0px solid black; margin-top:0px;width:40%;">
	<h2>Download Hall Ticket</h2>

        <tr><td>
        <label for="text">Email Id :</label></td>
        </td><td>

        <input type="text" name="dwapplicantemail" placeholder="Enter your email id" value="<?php echo isset($_POST["dwapplicantemail"]) ? $_POST["dwapplicantemail"] : ''; ?>" style="width:100%;height:35px;"/> <br>
        </td></tr>
    
        <tr><td>
        <label for="text">Mobile No :</label></td>
        </td><td>
        <input type="text" name="dwapplicantmobile" placeholder="Enter your mobile no" value="<?php echo isset($_POST["dwapplicantmobile"]) ? $_POST["dwapplicantmobile"] : ''; ?>" style="width:100%;height:35px;"  maxlength="12"/> <br>
        </td></tr>

        <tr><td>
        <label for="text">Date Of Birth :</label></td>
        <td>
       	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery-ui.min.css">
  	<script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.min.js" ></script>
	<input type="text" name="dwdateofbirth" placeholder="Select your dob"value="<?php echo isset($_POST["dwdateofbirth"]) ? $_POST["dwdateofbirth"] : ''; ?>" id="dob"  style="width:100%;height:35px;"/>

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
    				 yearRange: 'c-77:c+10',
				});
			</script>		
        </td>
        </tr>
        
        <tr><td>
        <label for="text">Program Name :</label></td>
        </td><td>
		<select name="dwapplicantprogram" class="form-control" id="register_name" style="width:100%;height:37px;font-size:18px;font-weight:bold;">
			<option  disabled selected>Program</option>
				<?php foreach($this->prgname as $data){?>
				<option value="<?php echo $data->prg_id;?>"><?php echo $data->prg_name.'('.$data->prg_branch.')'; ?></option>
				<?php }?>
	  		</select>
        </td></tr>
        <tr><td>
        <label for="text">Verification Code :</label></td>
        </td><td>
        <input type="text" name="dwapplicantvercode" placeholder="Enter verification code" value="<?php echo isset($_POST["dwapplicantvercode"]) ? $_POST["dwapplicantvercode"] : ''; ?>" maxlength="8" style="width:100%;height:35px;"/> <br>
        </td></tr>
      
        <tr>
        <td></td>
        <td>
        <button name="download" style="font-size:20px;">Submit</button>
        
        </td></tr>
    
    </table>
    </form>
<?php $this->load->view('template/footer'); ?>
</center>

</body>
</html>



