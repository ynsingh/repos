<!--@name disciplinewiselist.php 
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
       <div>
	<?php
        echo "<td align=\"center\" width=\"100%\">";
        echo "<b>Discipline Wise List Details</b>";
        echo "</td>";
            ?>
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
        <table class="TFtable" 
            <thead>
                <tr>
		<th> Sr.No </th>
		<th> Name </th>
		<th> Designation </th>
		<th> Department Name </th>
	</thead>
	<tbody>
	<?php  $count = 0;
		$sbid=0;
	 if(count($this->result)) {
		foreach ($this->result as $row) {
		   if($sbid!=$row->emp_specialisationid){
	             ?>
                     <tr>
			<?php echo "<td colspan=4><b>Major subject :";
			echo $this->commodel->get_listspfic1('subject','sub_name','sub_id' ,$row->emp_specialisationid)->sub_name; 
			echo "</td>";
			echo "</tr>";
			$sbid = $row->emp_specialisationid; 
			$count = 0;
		       } ?>
		     <tr>
			 <td><?php echo ++$count; ?> </td>
                         <td><?php echo $row->emp_name; ?> </td>
			 <td><?php echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$row->emp_desig_code)->desig_name; ?> </td>
			 <td><?php echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$row->emp_dept_code)->dept_name; ?> 
			( <?php echo $this->commodel->get_listspfic1('Department','dept_code','dept_id',$row->emp_dept_code)->dept_code; ?> ) </td>
		     </tr>
	<?php  }; 
	}else{
  	?>  
        <tr><td colspan= "13" align="center"> No Records found...!</td></tr>
       <?php }?>
	</tbody>
        </table>
        </div><!------scroller div------> 
   </body>
   <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>
 
