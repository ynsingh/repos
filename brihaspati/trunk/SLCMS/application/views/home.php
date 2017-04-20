<!DOCTYPE html>
<html>
    <head>
        <title>Welcome to IGNTU</title>
    </head>
    <body>

<div >
<div id="body">
	<?php $this->load->view('template/header'); ?>
     <h1>Welcome <?= $this->session->userdata('username') ?> <a href="<?= site_url('home/logout') ?>">Logout</a> </h1>
   <!--   <a href="<?= site_url('home/logout') ?>">Logout</a>-->
	
	<?php $this->load->view('template/menu'); ?>
	</div>
	
	<?php $this->load->view('template/footer'); ?>
	
</div>
    </body>
</html>
