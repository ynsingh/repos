<!---@name examcenter.php
@author Deepika Chaudhary (chaudharydeepika88@gmail.com)                                                                                               
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>View Exam Center</title>
  <head>
   <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
   <?php $this->load->view('template/header'); ?>
    <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
    <?php $this->load->view('template/menu');?>
  </head>
 <body>
<table width= "90%" style="margin-left:1%;">
            <tr><td>
                <div align="left">
                <?php  echo anchor('enterenceadmin/addexamcenter/', "Add  Exam Center", array('title' => 'Add   Exam Center  Detail','class' =>'top_parent'));
                 //$help_uri = site_url()."/help/helpdoc#ViewExamtype";
                 //echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:74%\">Click for Help</b></a>";
                ?>
                  </div>
                <div  style="width:90%;margin-left:2%">
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

<table>
<tr>
<div align="left" style="margin-left:2%;">
<table cellpadding="16" style="margin-left:2%;" class="TFtable" >
<tr align="center">
<thead><th>Sr.No</th><th>Entrance Exam Center Code</th><th>Entrance Exam Center Name</th><th>Entrance Exam Center Address</th><th>Entrance Exam Center State</th><th>Entrance Exam Center City</th><th>Entrance Exam Center Incharge</th><th>Entrance Exam Center Number of Room</th><th>Entrance Exam Center Capacity in Room</th><th>Entrance Exam Center Total Capacity</th><th>Entrance Exam Center Contact No</th><th>Entrance Exam Center Contact Email</th><th>Action</th></tr></thead>
 <?php
        $count =0;
        if( count($this->result) ):
        foreach ($this->result as $row)
        {
         ?>
             <tr align="center">
            <td> <?php echo ++$count; ?> </td>
            <td> <?php echo $row-> eec_code ?></td>
            <td> <?php echo $row-> eec_name ?></td>
	    <td> <?php echo $row-> eec_address ?></td>
	    <td> <?php echo $this->commodel->get_listspfic1('states','name','id',$row->eec_state)->name ; ?></td>
            <td> <?php echo $this->commodel->get_listspfic1('cities','name','id',$row->eec_city)->name ; ?></td>
	    <td> <?php echo $row-> eec_incharge ?></td>
            <td> <?php echo $row-> eec_noofroom ?></td>
	    <td> <?php echo $row-> eec_capacityinroom ?></td>
            <td> <?php echo $row-> eec_totalcapacity ?></td>
            <td> <?php echo $row-> eec_contactno ?></td>
            <td> <?php echo $row-> eec_contactemail ?></td>
            <td>
	    <?php
		echo anchor('enterenceadmin/editexamcenter/' . $row-> eec_id  , "Edit", array('title' => 'Details' , 'class' => 'red-link')) . " ";
		 echo "</td>";
           	 echo "</tr>";
        }
        else :
        echo "<tr>";
            echo "<td colspan= \"13\" align=\"center\"> No Records found...!</td>";
        echo "</tr>";
        endif;

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




