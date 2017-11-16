<!-------------------------------------------------------
    -- @name imp_date.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
       @author Deepika Chaudhary (chaudharydeepika88@gmail.com) 
--------------------------------------------------------->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>IGNTU:Important Data</title>
	<link rel="shortcut icon" href="<?php echo base_url('assets/images'); ?>/index.jpg">
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/message.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/studentNavbar.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/style.css">
</head>
<body>
<center>

<div>
	<div id="body">
	<?php  // $thisPage2="studentaddDetail"; 
		$this->load->view('template/header'); ?>
</br>
	<?php $this->load->view('enterence/enterence_head'); ?>
	<?php //if(isset($_SESSION)) {
        	//echo $this->session->flashdata('flash_data');
    	//} ?>
<!--------------------------------------------------------ERROR DISPLAY-------------------------------------------------------------->
<head>
<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
</head>
<?php
echo "<center>";

	if($this->session->flashdata('msg')){
echo "<div style='font-size:20px;text-align:center;background-color:#DFF2BF;width:50%;height:30px;color:green;'>";
	echo $this->session->flashdata('msg');
echo "<div>";	
}

echo "</center>";
?>
	<div align="left" style="margin-left:30px;width:1700px;font-size:18px;">
        <?php echo validation_errors('<div class="isa_warning">','</div>');?>
        <?php echo form_error('<div style="margin-left:30px;" class="isa_error">','</div>');?>
        <?php if(isset($_SESSION['success'])){?>
        <div class="alert alert-success"><?php echo $_SESSION['success'];?></div>
        <?php
    	 };
       	?>
	
        <?php if(isset($_SESSION['err_message'])){?>
             <div class="isa_error" ><div ><?php echo $_SESSION['err_message'];?></div></div>
        <?php
        };
	?>  
      </div>
<br>

	<div>
<div class="scroller_sub_page">
<table>
<tr>
<div align="left" style="margin-left:2%;">

<table cellpadding="16" style="font-size:17px;width:100%;" class="TFtable" >
<tr align="center">
<thead><th>Sr.No</th><th>Academic Year</th><th>Program Category</th><th>Program Name </th><th>Entrance Exam Fees </th> <th>Minimum Qualification </th><th>Entrance Exam Pattern</th><th>Entrance Exam Date</th><th>Start Date Of Online Application </th><th>Last Date Of Online Application</th><th>Last Late Of Application Received</th></tr></thead>
<?php
        $count =0;
        if( count($this->result) ):
        foreach ($this->result as $row)
        {
         ?>
             <tr align="center">
            <td> <?php echo ++$count; ?> </td>
            <td> <?php echo $row->admop_acadyear?></td>
            <td> <?php echo $this->commodel->get_listspfic1('programcategory','prgcat_name','prgcat_name',$row->admop_prgcat)->prgcat_name; ?>
            </td>
            <td> <?php echo $this->commodel->get_listspfic1('program','prg_name','prg_id',$row->admop_prgname_branch)->prg_name ;
                       echo "(";
                       echo $this->commodel->get_listspfic1('program','prg_branch','prg_id',$row->admop_prgname_branch)->prg_branch ;
                       echo ")";
                ?>
            </td>
            <td> <?php echo $row->admop_entexam_fees?></td>
            <td> <?php echo $row->admop_min_qual ?></td>
            <td> <?php echo $row->admop_entexam_patt ?></td>
            <td> <?php echo $row->admop_entexam_date ?></td>
            <td> <?php echo $row->admop_startdate ?></td>
            <td> <?php echo $row->admop_lastdate ?></td>
            <td> <?php echo $row->admop_app_received ?></td>

        <?php  // echo anchor('enterence/editadmissionopen/' . $row->admop_id , "Edit", array('title' => 'Edit Details' , 'class' => 'red-link')) . " ";

            echo "</td>";
            echo "</tr>";
}
else :
        echo "<tr>";
            echo "<td colspan= \"11\" align=\"center\"> No Records found...!</td>";
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
</div>
		<!--<table align=center border=1>
		<tr>
		<td> 
			<b>Advertisement Number</b>
		</td>
		<td>
			<b>Start date for online submission</b>
		</td>
		<td>
		<b>	Last date for online submission</b>
		</td>
		<td>
			<b>Last Date for Receipt of Printed Application</b>
		</td>
	
		</tr>
		<?php //foreach($this->dateres as $row){ ?>
		<tr>
		<?php
		//echo "<td>".$row->job_adverno	."</td><td>".	$row->job_startdateonlineform 	."</td><td>".$row->job_lastdateonlineform	."</td><td>".$row->job_lastdateformreach ."</td>";
		?>
		</tr>
		<?php //} ?>
		</table>-->

	
		<?php // echo "<table style='width:100%;margin-top:100px;' >";	
		//	echo //"<tr>"."<td>";
			//	$this->load->view('template/work_under'); 	
		//	echo //"</td>"."</tr>";
		//echo "</table>";
		?>
	</div>
<div>
<?php $this->load->view('template/footer'); ?>
</div>
</center>
</body>
</html>
