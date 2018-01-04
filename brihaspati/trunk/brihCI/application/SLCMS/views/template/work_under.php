<!--
 * @name work_under.php
   @author sumit saxena (sumitsesaxena@gmail.com)
 --->

<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?><!DOCTYPE html>
<html>
<head>
<title>Sample Page</title>
<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/message.css">

<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/studentNavbar.css">	
<style>
body, html {
    
    
}

.bgimg {
    background-image: url('/w3images/forestbridge.jpg');
    height: 100%;
    background-position: center;
    background-size: cover;
    position: relative;
    color: black;
    font-family: "Courier New", Courier, monospace;
    font-size: 25px;
}

.topleft {
    position: absolute;
    top: 0;
    left: 16px;
}

.bottomleft {
    position: absolute;
    bottom: 0;
    left: 16px;
}

.middle {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    text-align: center;
}

hr {
    margin: auto;
    width: 40%;
}
</style>	
</head>

<body>

<?php $this->load->view('template/header'); ?>
</br></br></br>
<div class="bgimg" style="">

  <div class="middle">
    <h2>Work Under Construction</h2>
    <hr>
   <p>Some work is left.</p>
	<?php echo date('d-m-Y H:i');?>

  </div>
<?php $this->load->view('template/footer'); ?>
</body>
</html>

