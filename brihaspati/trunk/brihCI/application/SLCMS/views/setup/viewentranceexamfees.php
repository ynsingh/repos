<!---@name entranceexamfees.php                                                                                                 
  @author Neha KHullar (nehukhullar@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>View Entrance Exam Fees</title>
  <head>
   <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">

   <?php $this->load->view('template/header'); ?> 
    <?php //$this->load->view('template/menu');?>
  </head>
 <body>
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->


    <!--<//?php
        echo "<table border=\"0\" align=\"left\" style=\"color: black;  border-collapse:collapse; border:1px;\">";
        echo "<tr style=\"text-align:left; \">";
        echo "<td style=\"padding: 8px 8px 8px 20px; decoration:none;\">";
        echo anchor('setup/degreerules/', "Add degree rules", array('title' => 'Add degree rules'));
        echo "</td>";
        echo "</tr>";
        echo "</table>";
        ?>-->
   <table width= "100%">
            <tr>
                <div>
                <?php  
                echo "<td align=\"left\" width=\"33%\">";
                echo anchor('setup/addentranceexamfees/', "Add Entrance Exam Fees", array('title' => 'Add  Entrance Exam Fees Detail','class' =>'top_parent'));
                echo "</td>";
                echo "<td align=\"center\" width=\"34%\">";
                echo "<b>Entrance Exam Fees Details</b>";
                echo "</td>";
                echo "<td align=\"right\" width=\"33%\">";
                //$help_uri = site_url()."/help/helpdoc#ViewEntranceExamFees";
               // echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                echo "</td>";
                ?>
                </div>
                <div>
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
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
             </tr>
       </table>
       <div class="scroller_sub_page">
         <table class="TFtable">
            <thead><tr>
                <th> Sr.No</th>
                <th> Fees Name </th>
                <th> Category</th>
                <th> Gender</th>
                <th> Amount</th>
                <th> Action</th></tr></thead>
<tbody>
 <?php
        $count =0;
        if( count($result) ):
        foreach ($result as $row)
        {
         ?>
             <tr>
            <td> <?php echo ++$count; ?> </td>
            <td> <?php echo $row->aseefc_feename  ?></td>
	    <?php  echo "<td>" . $this->common_model->get_listspfic1('category','cat_name','cat_id',$row->aseefc_category)->cat_name  . "</td>";?>
            <td> <?php echo $row->aseefc_gender  ?></td>
            <td> <?php echo $row->aseefc_amount ?></td>
            
<td> <?php // echo anchor("setup/delete_fees/{$row->fm_id}","Delete",array('title' => 'Delete' , 'class' => 'red-link' ,'onclick' => "return confirm('Are you sure you want to delete this record')")); ?>&nbsp;
                            &nbsp;<?php  echo anchor("setup/editentranceexamfees/{$row->aseefc_id}","Edit",array('title' => 'Edit Details' , 'class' => 'red-link')); ?></td>
                        </tr>
                    <?php }; ?>
                <?php else : ?>
                    <td colspan= "10" align="center"> No Records found...!</td>
                <?php endif;?>

            </tbody>
        </table>
    </body>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

                                     
