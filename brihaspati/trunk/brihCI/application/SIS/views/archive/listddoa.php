<!--@name listddoa.php 
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
    <table style="padding: 8px 8px 8px 20px;width:100%;">
     <tr><td>
      <div style="margin-left:2%;width:90%;">
          <?php echo validation_errors('<div class="isa_warning">','</div>');?>
          <?php echo form_error('<div class="isa_error">','</div>');?>
          <?php if(isset($_SESSION['success'])){?>
             <div class="isa_success"><?php echo $_SESSION['success'];?></div>
             <?php
             };
             ?>
      <?php if (isset($_SESSION['err_message'])){?>
             <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
	<?php
	};
	?>
   </div>
   </td></tr>
   </table>
   <div align="left">
   <table cellpadding="11" style="margin-left:2%;" class="TFtable">
     <thead >
	<tr align="center">
		<th>Sr.No</th>
		<th> Campus </th>
		<th> Department </th>
		<th> Scheme </th>
		<th> DDO Code </th>
		<th> DDO Name </th>
		<th> Archiver's Name </th> 
		<th> Archiver's Date </th> 
     </thead>
     <tbody>
	<?php $count = 0;
	if( count($this->result) ) {
	      foreach ($this->result as $row)
	      {
	 ?>    
	   <tr align="center">
	        <td><?php echo ++$count; ?> </td>
	        <td><?php echo $this->common_model->get_listspfic1('study_center', 'sc_name', 'sc_id', $row->ddoa_scid)->sc_name; ?> </td>
	        <td><?php echo $this->common_model->get_listspfic1('Department', 'dept_name', 'dept_id', $row->ddoa_deptid)->dept_name; ?> </td>
		<td><?php echo $this->sismodel->get_listspfic1('scheme_department', 'sd_name', 'sd_id', $row->ddoa_schid)->sd_name; ?> </td>
		<td><?php echo $row->ddoa_code ?> </td>
		<td><?php echo $row->ddoa_name ?> </td>
		<td><?php echo $this->logmodel->get_listspfic1('edrpuser', 'username', 'id', $row->ddoa_archuserid)->username ?> </td>
		<td><?php echo $row->ddoa_archdate ?> </td>
	   </tr>
	   <?php } 
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
 
