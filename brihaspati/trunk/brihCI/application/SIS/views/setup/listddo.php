<!--@name listddo.php 
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
 <table width="100%">
         <tr colspan="2">
         <?php
            echo "<td align=\"left\" width=\"33%\">";
            echo anchor('setup/newddo/', 'Add DDO', array('class' => 'top_parent'));
            echo "</td>";
            echo "<td align=\"center\" width=\"34%\">";
            echo "<b>DDO Details</b>";
            echo "</td>";
            echo "<td align=\"right\" width=\"33%\">";
            echo "</td>";
            ?>
      	  <div>
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
        <div class="scroller_sub_page">
        <table class="TFtable" >
            <thead>
                <tr>
		<th>Sr.No</th>
		<th> Campus </th>
		<th> Department </th>
		<th> Scheme </th>
		<th> DDO Code </th>
		<th> DDO Name </th>
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
	        <td><?php echo $this->common_model->get_listspfic1('study_center', 'sc_name', 'sc_id', $row->ddo_scid)->sc_name; ?> </td>
	        <td><?php echo $this->common_model->get_listspfic1('Department', 'dept_name', 'dept_id', $row->ddo_deptid)->dept_name; ?> </td>
		<td><?php echo $this->SIS_model->get_listspfic1('scheme_department', 'sd_name', 'sd_id', $row->ddo_schid)->sd_name; ?> </td>
		<td><?php echo $row->ddo_code ?> </td>
		<td><?php echo $row->ddo_name ?> </td>
		<td><?php echo anchor('setup/updateddo/' . $row->ddo_id , "Edit", array('title' => 'Edit Details' , 'class' => 'red-link')) ." " ?> </td>	
	   </tr>
	   <?php } 
	   }else{
  	   ?>  
           <tr><td colspan= "12" align="center"> No Records found...!</td></tr>
           <?php }?> 
	</tbody>
        </table>
        </div><!------scroller div------>
   </body>
   <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>
 
