<!--@name listsubjectteacher.php 
  @author Om Prakash(omprakashkgp@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
    <head>    
        <?php $this->load->view('template/header'); ?>
        <?php $this->load->view('template/menu');?>
      <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css"> 
    </head>
    <body>
<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>

   <table width="100%;">
     <tr><td>
      <div>
         <?php
              echo "<td align=\"left\" width=\"33%\">";
              echo anchor('map/subjectteacher/', 'Map Subject and Paper With Teacher', array('class' => 'top_parent'));
              echo "</td>";
              echo "<td align=\"center\" width=\"34%\">";
              echo "<b>Subject and Paper with Teacher Details</b>";
              echo "</td>";
              echo "<td align=\"right\" width=\"33%\">";
	      $help_uri = site_url()."/help/helpdoc#ViewMapSubjectandPaperwithTeacher";
              echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
              echo "</td>";
           ?>
       </div>
       <div>
          <?php echo validation_errors('<div class="isa_warning">','</div>');?>
          <?php echo form_error('<div class="isa_error">','</div>');?>
          <?php if(isset($_SESSION['success'])){?>
              <div class="isa_success"><?php echo $_SESSION['success'];?></div>
              <?php
              };
      		?>
	    <?php if  (isset($_SESSION['err_message'])){?>
		<div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
	<?php
	};
	?>
       </div>
       </td></tr>
       </table>
       <div class="scroller_sub_page">
       <table class="TFtable">
	<thead>
		<th>Sr.No</th>
		<th> Campus </th>
		<th> Department </th>
		<th> Academic Year </th>
		<th> Program </th>
		<th> Branch </th>
		<th> Semester </th>
		<th> Subject </th>
		<th> Paper </th>
		<th> Teacher </th>
		<th> Action </th>
	</thead>
	<tbody>
	<?php $count = 0;
	 if( count($this->result) ) {
		 foreach ($this->result as $row)
		 {
	?>    
			<tr>
			    <td><?php echo ++$count; ?> </td>
			    <td><?php echo $this->commodel->get_listspfic1('study_center', 'sc_name', 'sc_id', $row->pstp_scid)->sc_name; ?> </td>
			    <td><?php echo $this->commodel->get_listspfic1('Department', 'dept_name', 'dept_id', $this->commodel->get_listspfic1('user_role_type', 'deptid', 'userid', $row->pstp_teachid)->deptid)->dept_name; ?> </td>
			    <td><?php echo $row->pstp_acadyear ?> </td>
			    <td><?php echo $this->commodel->get_listspfic1('program', 'prg_name', 'prg_id', $row->pstp_prgid)->prg_name; ?> </td>
			    <td><?php echo $this->commodel->get_listspfic1('program', 'prg_branch', 'prg_id', $row->pstp_prgid)->prg_branch; ?> </td>
			    <td><?php echo $row->pstp_sem ?> </td>
			    <td><?php echo $this->commodel->get_listspfic1('subject', 'sub_name', 'sub_id', $row->pstp_subid)->sub_name; ?> </td>
			    <td><?php echo $this->commodel->get_listspfic1('subject_paper', 'subp_name', 'subp_id', $row->pstp_papid)->subp_name; ?> </td>
			    <td><?php echo $this->loginmodel->get_listspfic1('userprofile', 'firstname', 'userid', $row->pstp_teachid)->firstname; ?> <?php echo $this->loginmodel->get_listspfic1('userprofile', 'lastname', 'userid', $row->pstp_teachid)->lastname; ?> </td>
			    <td><?php //echo anchor('map/deletepsteacher/' . $row->pstp_id , "Delete", array('title' => 'Details' , 'class' => 'red-link' ,'onclick' => "return confirm('Are you sure you want to delete this program subject teacher record... ')")) ?> &nbsp;&nbsp; <?php echo anchor('map/editsubjectteacher/' . $row->pstp_id , "Edit", array('title' => 'Edit Details' , 'class' => 'red-link')) ." " ?> </td>	
			</tr>
	<?php } 
	}else{
  	?>  
           <tr><td colspan= "12" align="center"> No Records found...!</td></tr>
       <?php }?> 
       </tbody>
         </table></div>
          </body>
   <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>
 
