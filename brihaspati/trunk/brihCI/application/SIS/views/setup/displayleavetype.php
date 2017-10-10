<!--@name displayleavetype.php 
  @author Rekha (rekha20july@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>displayleavetype</title>
  <head>
   <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
   <?php $this->load->view('template/header'); ?>
    <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
    <?php $this->load->view('template/menu');?>
  </head>
 <body>
    <!--<//?php
        echo "<table border=\"0\" align=\"left\" style=\"color: black;  border-collapse:collapse; border:1px;\">";
        echo "<tr style=\"text-align:left; \">";
        echo "<td style=\"padding: 8px 8px 8px 20px; decoration:none;\">";
        echo anchor('setup/role/', "Add Role", array('title' => 'Add Detail'));
        echo "</td>";
        echo "</tr>";
        echo "</table>";
        ?>--!>

      <table width="100%">
            <tr><td>
                <div align="left" style="margin-left:2%;">
                <?php echo anchor('setup/leavetype/', "Add Leave Type", array('title' => 'Add Detail','class' =>'top_parent'));?>
                 <?php
                 $help_uri = site_url()."/help/helpdoc#ViewRoleDetail";
		 echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:78%\">Click for Help</b></a>";
                 ?>
                <div  style="width:70%;margin-left:2%">
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
             </td></tr>
       </table>

<table width="100%">
<tr>
<div align="left" style="margin-left:2%;">
<table cellpadding="16" style="margin-left:2%;" class="TFtable" >
<tr align="center">
<thead>

                      <th>Sr.No</th>
                     <th>Leave Name</th>
                     <th>Leave Code</th>
                     <th>Leave Short Name</th>
                     <th>Leave Value</th>
                      <th>Action</th></tr></thead>

   <?php
        $count =0;
        //foreach ($query->result() as $row)
        foreach ($this->result as $row)
        {  
         ?>
             <tr align="center">
            <td> <?php echo ++$count; ?> </td> 
            <td> <?php echo $row->lt_name ?></td>
            <td> <?php echo $row->lt_code ?></td>
            <td> <?php echo $row->lt_short ?></td>
            <td> <?php echo $row->lt_value ?></td>
           <td> <?php echo anchor('setup/editleave/' . $row->lt_id , "Edit", array('title' => 'Details' , 'class' => 'red-link')) . " ";
	           echo "</td>";
            echo "</tr>";
          
        }
        echo "</table>";
        echo "</td>";
        echo "</tr>";
        echo "</table>";
          ?>
</div>
</tr>
</table>
</body>
<div align="center">  <?php $this->load->view('template/footer');?></div>
</html>





