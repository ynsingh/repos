<!--@name staffpositiona.php 
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
    <center>
   <table "style= width;">
     <tr><td>
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
 </table></center>
 <div align="left">
	<table cellpadding="11" class="TFtable">
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
		<th> Archiver Name </th>
		<th> Archive Date </th>
	</thead>
	<tbody>
	<?php $count = 0;
	 if( count($this->result) ) {
		foreach ($this->result as $row)
		{
		?>    
			<tr align="center">
			 <td><?php echo ++$count; ?> </td>
                         <td><?php echo $this->common_model->get_listspfic1('study_center', 'sc_name', 'sc_id', $row->spa_campusid)->sc_name; ?> </td>
			 <td><?php echo $this->logmodel->get_listspfic1('authorities', 'name', 'id', $row->spa_uo)->name ?> </td>
			 <td><?php echo $this->common_model->get_listspfic1('Department', 'dept_name', 'dept_id', $row->spa_dept)->dept_name; ?> </td>
			 <td><?php echo $row->spa_tnt ?> </td>
			 <td><?php echo $row->spa_type ?> </td>
			 <td><?php echo $this->common_model->get_listspfic1('designation', 'desig_name', 'desig_id', $row->spa_emppost)->desig_name ?> </td>
			 <td><?php echo $row->spa_scale ?> </td>
			 <td><?php echo $row->spa_methodRect ?> </td>
			 <td><?php echo $row->spa_sancstrenght ?> </td>
			 <td><?php echo $row->spa_position ?> </td>
			 <td><?php echo $row->spa_vacant ?> </td>
			 <td><?php echo $this->logmodel->get_listspfic1('edrpuser', 'username', 'id', $row->spa_archuserid)->username ?> </td>
			 <td><?php echo $row->spa_archdate ?> </td>
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
 
