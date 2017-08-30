<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?><!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Welcome </title>
	 <link rel="shortcut icon" href="<?php echo base_url('assets/images'); ?>/index.jpg">
<!--	<style type="text/css">

	::selection { background-color: #E13300; color: white; }
	::-moz-selection { background-color: #E13300; color: white; }

	</style>
-->
</head>
<body>


<div>
	<div id="body">
	<?php $this->load->view('template/header'); ?>
	<nav> 	<h1>Welcome   </h1></nav>
	<?php if(isset($_SESSION)) {
        	echo $this->session->flashdata('flash_data');
    	} ?>
 	<br><br>
	<center>
    	<form action="<?= site_url('welcome') ?>" method="post">
        	<table>
		<tr><td>
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
        	<button type="submit" style="font-size:17px;">Login</button>
		
		<a href="<?php echo site_url('Student/student_step0');?>" style="text-decoration:none;" title="Click to open student detail form">
		<input type="button" value="New Student" style="font-size:17px;"></a></td></tr>
		</table>
    	</form>
</center>
	</div>
	<?php $this->load->view('template/footer'); ?>
	<!--<p class="footer">Page rendered in <strong>{elapsed_time}</strong> seconds. <?php echo  (ENVIRONMENT === 'development') ?  'CodeIgniter Version <strong>' . CI_VERSION . '</strong>' : '' ?></p>-->
</div>

</body>
</html>
