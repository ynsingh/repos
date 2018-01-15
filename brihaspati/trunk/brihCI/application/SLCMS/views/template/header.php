<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="IE=edge" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" type="text/css" media="all" href="<?php echo base_url(); ?>assets/css/style.css" />
<link rel="stylesheet" type="text/css" media="all" href="<?php echo base_url(); ?>assets/css/menu.css" />
<link rel="stylesheet" type="text/css" media="all" href="<?php echo base_url();?>assets/css/message.css">

<div id="header">
<img src="<?php echo base_url(); ?>uploads/logo/logo1.png" alt="logo">
</div> 
<?php 
	$role = $this->session->userdata('id_role');
	if($role == 1){
		$this->load->view('template/menu'); 
	}
	if($role == 2){
		$this->load->view('template/facultymenu'); 
	}
	if($role == 3){
		$this->load->view('template/stumenu'); 
	}
	if($role == 4){
		$this->load->view('template/staffmenu'); 
	}
	if($role == 5){
		$this->load->view('template/hodmenu'); 
	}
	if($role == 6){
		$this->load->view('template/coemenu'); 
	}
	if($role == 7){
		$this->load->view('template/aomenu'); 
	}
	if($role == 8){
		$this->load->view('template/entranceadminmenu'); 
	}
	if($role == 9){
		$this->load->view('template/admissionadminmenu'); 
	}
	if (($this->session->userdata('username')) !=''){
?>

<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
<?php } ?>
