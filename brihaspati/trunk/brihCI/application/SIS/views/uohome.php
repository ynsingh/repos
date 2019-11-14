
<!--@filename hodhome.php  @author Nagendra Kumar Singh(nksinghiitk@gmail.com) -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>


<html>
    <head>
        <title>Welcome to UO Home</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
    </head>
    <body>
        <?php //$this->load->view('template/header'); ?>
        <!--<h3>Welcome <//?= $this->session->userdata('username') ?></h3>-->
        <?php // $this->load->view('template/staffmenu');?>
       <!-- <table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username')?>  </td></tr></table>-->
        <?php $this->load->view('profile/viewprofile');?>

        <p> &nbsp; </p>
        </div><?php $this->load->view('template/footer'); ?></div>
    </body>
</html>
