<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name examcentera.php 
  @author Deepika Chaudhary (chaudharydeepika88@gmail.com)
  @author Neha Khullar (nehukhullar@gmail.com)
 -->
<html>
    <head>
    <title>Exam Center Archive</title>    
        <?php $this->load->view('template/header'); ?>
        <!--h1>Welcome <?//= $this->session->userdata('username') ?>  </h1-->
        <?php //$this->load->view('template/menu');?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
    </head>
    <body>
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->
                    <!--?php
                    echo "<table>";
                    echo "<tr valign=\"top\">";
                    echo "<td>";
                    $help_uri = site_url()."/help/helpdoc#AuthorityArchive";
                    echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;margin-left:56%;position:absolute;\">Click for Help</b></a>";
                    echo "</td>";
                    echo "</tr>";
                    echo "</table>";
                    ?-->

<table width="100%">
            <tr colspan="2"><td>
            <?php
            echo "<td align=\"center\" width=\"100%\">";
            echo "<b>Exam Center Archive Details</b>";
            echo "</td>";
             ?>
                <?php echo validation_errors('<div class="isa_warning>','</div>');?>

                <?php if(isset($_SESSION['success'])){?>
                    <div class="isa_success"><?php echo $_SESSION['success'];?></div>

                <?php
                };
                ?>
                <?php if(isset($_SESSION['err_message'])){?>
                    <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>

                <?php
                };
                ?>
</div>
            </td></tr>
        </table>
        

<div class="scroller_sub_page">
        <table class="TFtable" >
            <thead>
               
<thead><th>Sr.No</th><th>Entrance Exam Center ID</th><th>Entrance Exam Center Code</th><th>Entrance Exam Center Name</th><th>Entrance Exam Center Address</th><th>Entrance Exam Center State</th><th>Entrance Exam Center City</th><th>Entrance Exam Center Incharge</th><th>Entrance Exam Center Number of Room</th><th>Entrance Exam Center Capacity in Room</th><th>Entrance Exam Center Total Capacity</th><th>Entrance Exam Center Contact No</th><th>Entrance Exam Center Contact Email</th><th>Entrance Exam Center Archiver Name</th><th>Entrance Exam Center Archive Date</th></tr></thead><tbody>
<?php
        $count =0;
        if( count($this->exresult) ):
        foreach ($this->exresult as $row)
        {
         ?>
             <tr>
            <td> <?php echo ++$count; ?> </td>
	    <td> <?php echo $row-> eeca_eecid ?></td>
            <td> <?php echo $row-> eeca_code ?></td>
            <td> <?php echo $row-> eeca_name ?></td>
            <td> <?php echo $row-> eeca_address ?></td>
            <td> <?php echo $this->common_model->get_listspfic1('states','name','id',$row->eeca_state)->name ; ?></td>
            <td> <?php echo $this->common_model->get_listspfic1('cities','name','id',$row->eeca_city)->name ; ?></td>
            <td> <?php echo $row-> eeca_incharge ?></td>
            <td> <?php echo $row-> eeca_noofroom ?></td>
            <td> <?php echo $row-> eeca_capacityinroom ?></td>
            <td> <?php echo $row-> eeca_totalcapacity ?></td>
            <td> <?php echo $row-> eeca_contactno ?></td>
            <td> <?php echo $row-> eeca_contactemail ?></td>
	    <td> <?php echo $row-> eeca_archivename ?></td>
            <td> <?php echo $row-> eeca_archivedate ?></td>
		</tr>
 <?php }; ?>
                <?php else : ?>
                    <td colspan= "15" align="center"> No Records found...!</td>
                <?php endif;?>

            </tbody>
        </table></div>
    </body>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

