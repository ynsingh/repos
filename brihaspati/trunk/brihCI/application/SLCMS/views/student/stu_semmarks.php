<!-- @author Sumit Saxena(sumitsesaxena@gmail.com) -->

<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?><!DOCTYPE html>
<html lang="en">
<head>
	<title>Semester Marks Detail</title>
	<meta charset="utf-8">
	<title>IGNTU:Student Fees Detail</title>
	<link rel="shortcut icon" href="<?php echo base_url('assets/images'); ?>/index.jpg">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/message.css">
	 <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">   

</head>
<body>

<div>
	<?php $this->load->view('template/header'); ?>
</div>

		<?php
                    echo "<table style=\"padding: 10px 0px 0px 10px;width:100%;font-size:18px;\">";
                    echo "<tr valign=\"top\">";
		    echo "<td  style='width:53%;' align=right>";
		    echo "<b>Marks Details</b>";
		    echo "</td>";		
                    echo "<td>";
                    $help_uri = site_url()."/help/helpdocstudent#SubjectRecord";
                    echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;\">Click for Help</b></a>";
                    echo "</td>";
                    echo "</tr>";
                    echo "</table>";
                    ?>

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
        
      <!-- <div class="panel panel-primary"> -->
            <table  class="TFtable">
            <thead >
            <tr align="center">
		<th>Sr. No.</th>
		<th>Department</th>
                <th>Program Name(Branch)</th>
                <th>Semester</th>
                <th>Academic Year</th>
		<th>Subject</th>
		<th>Paper</th>	
		
		<th>Exam Name</th>
		<!--<th>Grade</th>-->
		<th>Max. Marks</th>
		<th>Marks</th>	
		<th>Action</th>
            </tr>
       
        </thead>
        <tbody>
		<?php 
			$count=1;
			if(!empty($stumarks)){
			foreach($stumarks as $row){
			$deptid = $row->smr_deptid;
			$prgid = $row->smr_prgid;
			$examid = $row->smr_extyid;
			$subid = $row->smr_subid;
			$paperid = $row->smr_papid;	
		?>
		<tr>
			<td><?php echo $count++;?></td>
			<td><?php echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$deptid)->dept_name;?></td>
			<td><?php echo $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name .' '.'( '. $this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch.' )';?></td>
			<td><?php echo $row->smr_sem;?></td>
			<td><?php echo $row->smr_acadyear;?></td>
			<td><?php echo $this->commodel->get_listspfic1('subject','sub_name','sub_id',$subid)->sub_name;?></td>
			<td><?php echo $this->commodel->get_listspfic1('subject_paper','subp_name','subp_id',$paperid)->subp_name;?></td>
			<td><?php echo $this->commodel->get_listspfic1('examtype','exty_name','exty_id',$examid)->exty_name;?></td>
			<!---<td><?php //echo $row->smr_grade;?></td>-->
			<td><?php echo $row->smr_mmmarks;?></td>
			<td><?php echo $row->smr_marks;?></td>
			<td><a href="<?php echo site_url('studentrecord/marksrecorddw/');echo $row->smr_id;?>">Download</a></td>
		</tr>
		<?php }}?>
         </tbody>
        </table>

<?php $this->load->view('template/footer'); ?>

</body>
</html>
