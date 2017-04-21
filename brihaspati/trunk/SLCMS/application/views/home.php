<!DOCTYPE html>
<html>
    <head>
        <title>Welcome to IGNTU</title>
    </head>
    <body>

<div >
<div id="body">
	<?php $this->load->view('template/header'); ?>
     <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
   <!--   <a href="<?= site_url('home/logout') ?>">Logout</a>-->
	
	<?php $this->load->view('template/menu'); ?>


	</div>
	<div>
		//write university details
	</div>
	<?php $this->load->view('template/footer'); ?>
	
</div>
    </body>
</html>
