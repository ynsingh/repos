<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?><!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Welcome </title>
	 <link rel="shortcut icon" href="<?php echo base_url('assets/images'); ?>/index.jpg">
	 <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/style.css">
	<style type="text/css">

	/*::selection { background-color: #E13300; color: white; }
	::-moz-selection { background-color: #E13300; color: white; }*/
	tr td a{text-decoration:none;font-size:15px;color:black;font-weight:bold;}
	
	</style>
</head>
<body>
<div>
	<div id="body">
	<?php $this->load->view('template/header'); ?>
	<nav> 	<h1>Welcome  </h1></nav>
	<?php if(isset($_SESSION)) {
        	echo $this->session->flashdata('flash_data');
    	} ?>
 	</br>
	<?php $this->load->view('template/welcome_head'); ?>
</br></br>
	<center>
    	<form action="<?= site_url('welcome') ?>" method="post">
        <table style="width:100%;" border=0>
		<tr>
		   <td width="770" align="center"><h2 style="color:#ff0000;"><span style="color:black;">Note : </span>Fees paid will not refunded or readjusted in any circumstances.</h2></td>
 		   <td align="center"><a href="">View Advertisement</a></br>
			</br><span style="font-size:15px;color:black;font-weight:bold;">New Applicant</span>
			</br><a href="<?php echo site_url('carrier/applicant_step1')?>" style="color:blue;">(Click Here)</a>
			</br></br><a href="">Applicant Status</a>
			</br></br><a href="">Applicant print</a></td>
		   <td>
	<table align="center">
		<tr>
		<td>
        	<label for="username">Username</label>
		</td>
		<td>		
        	<input type="text" name="username" />
		</td>
		</tr>
		<tr><td>
        	<label for="password">Password</label></td>
		<td>
        	<input type="password" name="password" /></td></tr>
		<tr><td></td>
		<td>
        	<button type="submit" style="font-size:17px;width:49%;">Login</button>
		
		<input type="reset" value="reset" style="font-size:17px;width:49%;"></td></tr>
		<tr><td></td>
		<td><a href=""><input type="button" value="Forget Password" style="font-size:17px;width:100%;"></a></td>
		</tr>
	</table>
	</td>
	</tr>
	</table>
    	</form>
</center>
	</div>
	<?php $this->load->view('template/footer'); ?>
	<!--<p class="footer">Page rendered in <strong>{elapsed_time}</strong> seconds. <?php echo  (ENVIRONMENT === 'development') ?  'CodeIgniter Version <strong>' . CI_VERSION . '</strong>' : '' ?></p>-->
</div>

</body>
</html>
