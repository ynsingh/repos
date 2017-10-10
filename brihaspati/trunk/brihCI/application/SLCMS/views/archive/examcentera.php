<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name examcentera.php 
  @author Deepika Chaudhary (chaudharydeepika88@gmail.com)
 -->
<html>
    <head>    
        <?php $this->load->view('template/header'); ?>
        <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
        <?php $this->load->view('template/menu');?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
    </head>
    <body>

                   <!--?php
                    echo "<table style=\"padding: 20px 8px 8px 20px;\">";
                    echo "<tr valign=\"top\">";
                    echo "<td>";
                    $help_uri = site_url()."/help/helpdoc#FeesMasterArchive";
                    echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;margin-left:39%;position:absolute;margin-top:-1%\">Click for Help</b></a>";
                    echo "</td>";
                    echo "</tr>";
                    echo "</table>";
                    ?-->

        <table style="margin-left:10px;">
            <tr colspan="2"><td>
            <div  style="margin-left:-06px;width:1793px;">

                <?php echo validation_errors('<div class="isa_warning>','</div>');?>

                <?php if(isset($_SESSION['success'])){?>
                    <div style="margin-left:30px;" class="isa_success"><?php echo $_SESSION['success'];?></div>

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
        <table cellpadding="16" style="margin-left:30px;" class="TFtable" >
            <thead>
                <tr align="center">
<thead><th>Sr.No</th><th>Enterance Exam Center ID</th><th>Enterance Exam Center Code</th><th>Enterance Exam Center Name</th><th>Enterance Exam Center Address</th><th>Enterance Exam Center State</th><th>Enterance Exam Center City</th><th>Enterance Exam Center Incharge</th><th>Enterance Exam Center Number of Room</th><th>Enterance Exam Center Capacity in Room</th><th>Enterance Exam Center Total Capacity</th><th>Enterance Exam Center Contact No</th><th>Enterance Exam Center Contact Email</th><th>Enterance Exam Center Archive Name</th><th>Enterance Exam Center Archive Date</th></tr></thead><tbody>
<?php
        $count =0;
        if( count($this->exresult) ):
        foreach ($this->exresult as $row)
        {
         ?>
             <tr align="center">
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
        </table>
    </body>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

