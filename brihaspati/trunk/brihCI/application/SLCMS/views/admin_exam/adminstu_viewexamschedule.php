<!--
 * @name adminstu_viewexamschedule.php
   @author sumit saxena (sumitsesaxena@gmail.com)
   @author neha khullar (nehukhullar@gmail.com)
 --->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>View Exam Center</title>
  <head>
   <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">

   <?php $this->load->view('template/header'); ?>

    <?php //$this->load->view('template/menu');?>
  </head>
 <body>
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->
<table style="font-size:18px;width:100%;">
            <tr>
                <?php  
                  echo "<td align=\"left\" width=\"33%\">";
                  echo anchor('adminstuexam/exam_scheduleadd/', "Add  Exam Schedule", array('title' => 'Add   Exam Schedule  Detail','class' =>'top_parent'));
                  echo "</td>";
                  echo "<td align=\"center\" width=\"34%\">";
                  echo "<b>Exam Schedule Details</b>";
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
<thead><th>Sr.No</th><th>Exam Center Name</th><th>Programme Category</th><th>Department Name</th><th>Programme Name</th><th>Session</th><th>Semester</th>
<th>Paper Name</th><th> Exam Name</th><th>Exam Date</th>
	<th>Exam Time</th><th>Action</th></thead>
<tbody>
 <?php
        $count =0;
        if( count($exam_schedulelist) ){
	if(!empty($exam_schedulelist)){
        foreach ($exam_schedulelist as $row)
        {
		$centerid = $row->exsc_centerid;
		$prgid = $row->exsc_prgid;
		$deptid = $row->exsc_deptid;
		$exdatetime = $row->exsc_examdatetime;
		$pieces = explode(" ", $exdatetime);
		$prgcatid = $row->exsc_progcatid;
		$subid = $row->exsc_subjectid;
		$paperid = $row->exsc_paperid;
		$exmname = $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$centerid)->sc_name ;
		//$prgcat = $this->commodel->get_listspfic1('programcategory','prgcat_name','prgcat_id',$prgcatid)->prgcat_name ;
		$deptname = $this->commodel->get_listspfic1('Department','dept_name','dept_id',$deptid)->dept_name ;
		$prgname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name.'( '.$this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch.' )' ;
		if(!empty($subid)){ $sbujname = $this->commodel->get_listspfic1('subject','sub_name','sub_id',$subid)->sub_name; }
		if(!empty($paperid)){ $papername = $this->commodel->get_listspfic1('subject_paper','subp_name','subp_id',$paperid)->subp_name ; }
		$examnameid = $row->exsc_examname;
		$exam_name = $this->commodel->get_listspfic1('examtype','exty_name','exty_id',$examnameid)->exty_name ;
         ?>
            <tr>
            <td> <?php echo ++$count; ?> </td>
            <td> <?php if(!empty($exmname)){ echo $exmname; }?></td>
	    <td> <?php echo $row->exsc_progcatid;?></td>	
            <td> <?php if(!empty($deptname)){ echo $deptname; } ?></td>
	    <td> <?php if(!empty($prgname)){ echo $prgname; }?></td>
	    <td> <?php echo $row->exsc_acadyear; ?></td>
            <td> <?php echo $row->exsc_semester ; ?></td>
	    	
	    <td> <?php if(!empty($sbujname)){echo  $sbujname; }?></td>
            <td> <?php if(!empty($papername)){echo $papername; } ?></td>	
	    <td> <?php if(!empty($exam_name)){echo $exam_name; } ?></td>
            <td> <?php echo $pieces[0];?></td>
	    <td> <?php echo $pieces[1]; ?></td>
	    
            
            <td>
	    <?php
		echo anchor('adminstuexam/exam_scheduleedit/'.$row->exsc_id  , "Edit", array('title' => 'Exam Schedule Details Edit' , 'class' => 'red-link')) . " ";
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
