<!--@name staffposition.php 
  @author Om Prakash(omprakashkgp@gmail.com)
 -->
 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>
 <html>
    <head>    
        <?php $this->load->view('template/header'); ?>
            <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
        <?php $this->load->view('template/menu');?>
      <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css"> 
    </head>
    <body>
   <table style="padding: 8px 8px 8px 20px;">
     <tr><td>
 	<div align="left">
        <font color=blue size=4pt>
         <?php
            echo anchor('staffmgmt/newstaffposition/', ' Staff Position ', array('class' => 'top_parent'));
         ?>
        </div>
       </div>
       <div style="margin-left:10px;width:1700px;">
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
 <div align="left">
	<table cellpadding="11" style="margin-left:30px;" class="TFtable">
	<thead>
		<tr align="center">
		<th> Sr.No </th>
		<th> Campus Name </th>
		<th> U O Control </th>
		<th> Department Name </th>
		<th> Working Type </th>
		<th> Employee Type </th>
		<th> Employee Post </th>
		<th> Pay Band </th>
		<th> Method of Recruitment </th>
		<th> Sanction Strength Position </th>
		<th> Present Position </th>
		<th> Vacant Position </th>
		<th> Action </th>
	</thead>
	<tbody>
	<?php $count = 0;
	 if( count($this->result) ) {
		foreach ($this->result as $row)
		{
		?>    
			<tr align="center">
			 <td><?php echo ++$count; ?> </td>
                         <td><?php echo $this->commodel->get_listspfic1('study_center', 'sc_name', 'sc_id', $row->sp_campusid)->sc_name; ?> </td>
			 <td><?php echo $this->lgnmodel->get_listspfic1('authorities', 'name', 'id', $row->sp_uo)->name ?> </td>
			 <td><?php echo $this->commodel->get_listspfic1('Department', 'dept_name', 'dept_id', $row->sp_dept)->dept_name; ?> </td>
			 <td><?php echo $row->sp_tnt ?> </td>
			 <td><?php echo $row->sp_type ?> </td>
			 <td><?php echo $this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id', $row->sp_emppost)->desig_name ?> </td>
			 <td><?php echo $row->sp_scale ?> </td>
			 <td><?php echo $row->sp_methodRect ?> </td>
			 <td><?php echo $row->sp_sancstrenght ?> </td>
			 <td><?php echo $row->sp_position ?> </td>
			 <td><?php echo $row->sp_vacant ?> </td>
		        <td><?php echo anchor('staffmgmt/editstaffposition/' . $row->sp_id , "Edit", array('title' => 'Edit Details' , 'class' => 'red-link')) ?> </td> 	
		      </tr>
	<?php  } 
	}else{
  	?>  
        <tr><td colspan= "12" align="center"> No Records found...!</td></tr>
       <?php }?> 
       </tbody>
      </table>
     </div>
   </body>
   <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>
 
