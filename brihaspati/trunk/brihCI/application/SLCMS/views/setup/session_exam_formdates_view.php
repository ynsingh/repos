<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Session/Exam/Marks Submission Dates</title>
<body>
<div>
<?php $this->load->view('template/header.php');?>

<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
</div>

<?php echo validation_errors('<div class="isa_warning">','</div>');?>
        <?php echo form_error('<div class="">','</div>');?>
        <?php 
	    if(!empty($_SESSION['success'])){	
		if(isset($_SESSION['success'])){?>
         <div class="isa_success" style="font-size:18px;"><?php echo $_SESSION['success'];?></div>
         <?php
          } };
         ?>
        <?php 
	   if(!empty($_SESSION['err_message'])){		
		if(isset($_SESSION['err_message'])){?>
        <div class="isa_error"><div ><?php echo $_SESSION['err_message'];?></div></div>
        <?php
         } };
	?>  

<table style="width:100%;">
        <tr><td>
                <?php
		echo "<td align=\"left\" width=\"33%\">";
                echo anchor('setup/set_datesadd/', "Add Session / Exam / Marks-Submission Dates", array('title' => 'Session / Exam / Marks-Submission Dates' , 'class' => 'top_parent'));
                echo "</td>";
                echo "<td align=\"center\" width=\"34%;\" style='font-size:18px;'>";
                echo "<b>View Session / Exam / Marks-Submission Dates </b>";
                echo "</td>";
                echo "<td align=\"right\" width=\"33%\">";
                $help_uri = site_url()."/help/helpdoc#ViewStudentlistwithHead";
                //echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
		echo "</td>";	
                ?>
              </td></tr>
        </table>

    <div class="scroller_sub_page">   
    <table  class="TFtable">
	   <thead>
	        <th>Sr.No.</th>
                <th>Campus Name</th>
		<th>Academic Year</th>
                <th>Semester</th>	
                <th>Session Start Date</th>
		<th>Session End Date</th>
		<th>Exam Start Date</th>
		<th>Exam End Date</th>
		<th>Marks-Submission Start Date</th>
		<th>Marks-Submission End Date</th>
		
		<th>Action</th>
	</thead>

    <?php  
        $count=0;
	if(!empty($getdatelist)){
         foreach($getdatelist as $row)  
         {
		
            $count = $count + 1;
    ?>
            <tr>
            <td><?php echo $count;?></td>
            <td><?php echo $this->common_model->get_listspfic1('study_center','sc_name','sc_code',$row->sed_campuscode)->sc_name;?></td>
	    <td><?php echo $row->sed_acadyear;?></td>
            <td><?php echo $row->sed_sem;?></td>	
            <td><?php echo $row->sed_sessionsdate;?></td>
            <td><?php echo $row->sed_sessionedate;?></td>
            <td><?php echo $row->sed_examsdate;?></td>
            <td><?php echo $row->sed_examedate;?></td>
            <td><?php echo $row->sed_formsubmitsdate;?></td>
            <td><?php echo $row->sed_formsubmitedate;?></td>
            <td><?php echo anchor('setup/set_datesetedit/' . $row->sed_id , "Edit", array('title' => 'Edit Program', 'class' => 'red-link'));?>
             <?php //echo anchor('setup/deleteprogram/' . $row->prg_id , "Delete", array('title' => 'Delete Program', 'class' => 'red-link','onclick' => "return confirm('Are you sure you want to delete this record')"));?>
            </td>
    <?php 
	}       
         }?>  
    </tr>     
    </table></div>
<div>
<?php $this->load->view('template/footer.php');?>
</div>
</body>
</html>

