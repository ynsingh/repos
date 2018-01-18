<!--@name viewscuoa.php 
  @author Om Prakash(omprakashkgp@gmail.com)
 -->
 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>

 <html>
    <head>    
        <?php $this->load->view('template/header'); ?>
       
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css"> 
    </head>
    <body>

<table width="100%">
            <tr colspan="2"><td>
 	<?php
            echo "<td align=\"center\" width=\"100%\">";
            echo "<b>SC with UO Archive Details</b>";
            echo "</td>";
    ?>
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
		<th> Campus Name </th>
		<th> University Officer Control </th>
		<th> Archiver's Name </th> 
		<th> Archiver's Date </th> 
     </thead>
     <tbody>
	<?php $count = 0;
	if( count($this->result) ) {
	      foreach ($this->result as $row)
	      {
	 ?>    
	   <tr>
	        <td><?php echo ++$count; ?> </td>
	        <td><?php echo $this->common_model->get_listspfic1('study_center', 'sc_name', 'sc_id', $row->scuoa_scid)->sc_name; ?> </td>
	        <td><?php echo $this->logmodel->get_listspfic1('authorities', 'name', 'id', $row->scuoa_uoid)->name; ?> </td>
		<td><?php echo $this->logmodel->get_listspfic1('edrpuser', 'username', 'id', $row->scuoa_archuserid)->username ?> </td>
		<td><?php echo $row->scuoa_archdate ?> </td>
	   </tr>
	   <?php } 
	   }else{
  	   ?>  
           <tr><td colspan= "12" align="center"> No Records found...!</td></tr>
           <?php }?> 
          </tbody>
	</div><!------scroller div------>
    </table>
   </body>
<p> &nbsp; </p>
   <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>
 
