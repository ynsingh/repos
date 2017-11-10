<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name announcementa.php 
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
    </center>

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

        <table style="width:70%;">
            <tr colspan="2"><td>
            <div  style="margin-left:-06px;width:1600px;">

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
        </table></center>
        <table cellpadding="16" class="TFtable" >
            <thead>
                <tr align="center">
<thead><th>Sr.No</th><th>Announcement ID</th><th>Announcement Component Name</th><th>Announcement Type</th><th>Announcement Title</th><th>Announcement Description</th><th>Announcement Publish Date</th><th>Announcement Expiry Date</th><th>Announcement Remark</th><th>Announcement Archive Name</th><th>Announcement Archive Date</th></tr></thead>

<?php
        $count =0;
        if( count($this->annoresult) ):
        foreach ($this->annoresult as $row)
        {
         ?>
             <tr align="center">
<td> <?php echo ++$count; ?> </td>
	    <td> <?php echo $row-> annoa_anoid ?></td>
            <td> <?php echo $row-> anoua_cname ?></td>
            <td> <?php echo $row-> anoua_type ?></td>
            <td> <?php echo $row-> anoua_title ?></td>
            <td> <?php echo $row-> anoua_description?></td>
            <td> <?php echo $row-> anoua_publishdate ?></td>
            <td> <?php echo $row-> anoua_expdate ?></td>
            <td> <?php echo $row-> anoua_remark ?></td>
	    <td> <?php echo $row-> anoua_archivename ?></td>
            <td> <?php echo $row-> anoua_archivedate ?></td>
<?php }; ?>
                <?php else : ?>
                    <td colspan= "15" align="center"> No Records found...!</td>
                <?php endif;?>

            </tbody>
        </table>
    </body>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

