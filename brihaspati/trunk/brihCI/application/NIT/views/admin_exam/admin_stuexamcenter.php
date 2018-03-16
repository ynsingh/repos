<!--
 * @name adminstu_examschedule.php
   @author sumit saxena (sumitsesaxena@gmail.com)
 --->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>View Exam Center</title>
<!--<link rel="shortcut icon" href="<?php //echo base_url('assets/images'); ?>/index.jpg">-->
	<link rel="icon" href="<?php echo base_url('uploads/logo'); ?>/nitsindex.png" type="image/png">	
  <head>
   <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">

   <?php $this->load->view('template/header'); ?>

    <?php //$this->load->view('template/menu');?>
  </head>
 <body>
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->
<table width= "100%">
            <tr>
                <?php  
                  echo "<td align=\"left\" width=\"33%\">";
                  echo anchor('adminstuexam/stu_addexamcenter/', "Add  Exam Center", array('title' => 'Add   Exam Center  Detail','class' =>'top_parent'));
                  echo "</td>";
                  echo "<td align=\"center\" width=\"34%\">";
                  echo "<b>Exam Center Details</b>";
                  echo "</td>";
                  echo "<td align=\"right\" width=\"33%\">";
                  //$help_uri = site_url()."/help/helpdoc#ViewExamtype";
                  //echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                  echo "</td>";
                ?>
	</tr>
</table>
	<table width= "100%">
		<tr><td>
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
            </td></tr>
       </table>
<div class="scroller_sub_page">
<table class="TFtable" >
<tr>
<thead><th>Sr.No</th><th>Exam Center Code</th><th>Exam Center Name</th><th>Exam Center Address</th><th>Exam Center State</th><th>Exam Center City</th><th>Exam Center Incharge</th><th>    Exam Center Number of Room</th><th>    Exam Center Capacity in Room</th><th>    Exam Center Total Capacity</th><th>    Exam Center Contact No</th><th>    Exam Center Contact Email</th><th>Action</th></tr></thead>
<tbody>
 <?php
        $count =0;
        if( count($this->result) ){
	if(!empty($this->result)){
        foreach ($this->result as $row)
        {
         ?>
            <tr>
            <td> <?php echo ++$count; ?> </td>
            <td> <?php echo $row->sec_code; ?></td>
            <td> <?php echo $row->sec_name; ?></td>
	    <td> <?php echo $row->sec_address; ?></td>
	    <td> <?php echo $this->commodel->get_listspfic1('states','name','id',$row->sec_state)->name ; ?></td>
            <td> <?php echo $this->commodel->get_listspfic1('cities','name','id',$row->sec_city)->name ; ?></td>
	    <td> <?php echo $row->sec_incharge; ?></td>
            <td> <?php echo $row->sec_noofroom; ?></td>
	    <td> <?php echo $row->sec_capacityinroom; ?></td>
            <td> <?php echo $row->sec_totalcapacity; ?></td>
            <td> <?php echo $row->sec_contactno; ?></td>
            <td> <?php echo $row->sec_contactemail; ?></td>
            <td>
	    <?php
		echo anchor('adminstuexam/stu_editexamcenter/' . $row->sec_id  , "Edit", array('title' => 'Details' , 'class' => 'red-link')) . " ";
		 echo "</td>";
        }}
        else {
        echo "<tr>";
            echo "<td colspan= \"13\" align=\"center\" style='text-align:center;'> No Records found...!</td>";
        echo "</tr>";
        }}
           ?>
</tbody>
</tr>
</table>
</div><!------scroller div------>
</body>
<div align="center">  <?php $this->load->view('template/footer');?></div>
</html>
