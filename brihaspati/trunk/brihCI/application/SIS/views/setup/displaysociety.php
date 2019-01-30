<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name displaysociety.php 
  @author Neha Khullar(nehukhullar@gmail.com)
 -->

<html>
<title>View Society</title>
<head>    
    <?php $this->load->view('template/header'); ?>
    
    <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">   
</head>
 <body>

<table width='100%'>
     <tr colspan="2"><td>
         <?php
            echo "<td align=\"left\" width=\"33%\">";
            echo anchor('setup/addsociety/', 'Add Society Master', array('class' => 'top_parent'));
            echo "</td>";
            ?>
            <?php
            echo "<td align=\"center\" width=\"34%\">";
            echo "<b>Society Details</b>";
            echo "</td>";
            echo "<td align=\"right\" width=\"33%\">";
            $help_uri = site_url()."/help/helpdoc#ViewSocietyDetail";
//            echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
            echo "</td>";
         ?>

        <div align="left" style="margin-left:0%;width:90%;">

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
        </div> </td>
    </tr>
  </table>
        <div class="scroller_sub_page">
        <table class="TFtable" >
        <thead>
        <tr>
        <th>Sr. No</th>
        <th> Society Name </th>
	<th> Society Code </th>
        <th> Society Address </th>
	<th> Society Purpose </th>
        <th> Society Remark </th>
        <th> Society Registration Date</th>
        <th> Action </th> 
        </thead>
        <tbody>
             <?php
                $count = 0;
                foreach ($this->result as $row)
                {
              ?>
                <tr>
                    <td><?php echo ++$count; ?> </td>
                    <td><?php echo $row->soc_sname ?></td>
		    <td><?php echo $row->soc_scode ?> </td>
                    <td><?php echo $row->soc_address ?> </td>
                    <td><?php echo $row->soc_purpose ?> </td>
		    <td><?php echo $row->soc_sremark ?> </td>
                    <td><?php  echo $row->soc_regdate ?> </td> 
		   <!-- <td><?php //echo $row->soc_creatordate ?> </td> -->
                     <?php if($row->soc_id >1){ ?>

		 <td> <?php  //echo anchor("setup/delete_fees/{$row->fm_id}","Delete",array('title' => 'Delete' , 'class' => 'red-link' ,'onclick' => "return confirm('Are you sure you want to delete this record')")); ?> &nbsp;&nbsp; <?php echo anchor('setup/editsociety/' . $row->soc_id  , "Edit", array('title' => 'Edit Details' , 'class' => 'red-link')); ?>
               </td>
                <?php } else {
                        echo "<td> </td>";
                }?>
               </tr>
          <?php } ?>
        </tbody>
    </table>
   </div><!------scroller div------>
  </body>
<p>&nbsp;</p>
 <div align="center"> <?php $this->load->view('template/footer');?></div>
</html>


