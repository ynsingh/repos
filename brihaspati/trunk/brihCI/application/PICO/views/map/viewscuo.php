<!--@name viewscuo.php 
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
            <tr colspan="2">
         <?php
            echo "<td align=\"left\" width=\"33%\">";
            echo anchor('map/studycenteruo', 'Map Study Center with UO', array('class' => 'top_parent'));
            echo "</td>";
            echo "<td align=\"center\" width=\"34%\">";
            echo "<b>Map Study Center with UO Details</b>";
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
	
    <?php if  (isset($_SESSION['err_message'])){?>
		<div class="isa_error"><?php echo $_SESSION['err_message'];?></div>

	<?php
	};
	?>
 </div>
 </tr>
 </table>
        <div class="scroller_sub_page">
        <table class="TFtable" >
            <thead>
                <tr>
		<th>Sr.No</th>
		<th> Campus Name</th>
		<th> University Officer Control </th>
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
          
			    <td><?php
          if(!empty($this->commodel->get_listspfic1('study_center', 'sc_name', 'sc_id', $row->scuo_scid)->sc_name)){ 
          echo $this->commodel->get_listspfic1('study_center', 'sc_name', 'sc_id', $row->scuo_scid)->sc_name;
          }
           ?> </td>


			    <td><?php echo $this->loginmodel->get_listspfic1('authorities', 'name', 'id', $row->scuo_uoid)->name; ?> </td>
			    <td><?php echo anchor('map/updatescuo/' . $row->scuo_id , "Edit", array('title' => 'Edit Details' , 'class' => 'red-link')) ." " ?> </td>	
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
<p> &nbsp;</p>
   <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>
 
