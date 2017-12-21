<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name  displayschemea.php 
  @author Om Prakash(omprakashkgp@gmail.com)
 -->

<html>
<title>Scheme Archive</title>
<head>    
    <?php $this->load->view('template/header'); ?>
    <?php $this->load->view('template/menu');?>
    <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css"> 	
</head>    
<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
 <table width="100%">
            <tr colspan="2">
         <?php
            echo "<td align=\"center\" width=\"34%\">";
            echo "<b>Scheme Details</b>";
            echo "</td>";
         ?>
       <div align="left" style="margin-left:0%;width:90%">
          <?php echo validation_errors('<div class="isa_warning">','</div>');?>
          <?php echo form_error('<div class="isa_error">','</div>');?>
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
            <thead>
                <tr>
        <th>Sr.No</th>
        <th>Department Name</th>
        <th>Scheme Name </th>
        <th>Scheme Code </th>
        <th>Scheme Short Name </th>
        <th>Scheme Description </th>
        <th> Archiver's Name </th>
        <th> Archiver's Date </th>
	</thead>
	<tbody>
	     <?php
		$count = 0;
	        foreach ($this->result as $row)
                {
              ?>    
		<tr>
                    <td><?php echo ++$count; ?> </td>
                    <td><?php echo $this->common_model->get_listspfic1('Department','dept_name', 'dept_id',$row->sda_deptid)->dept_name;?></td>
                    <td><?php echo $row->sda_name ?> </td>
                    <td><?php echo $row->sda_code ?> </td>
                    <td><?php echo $row->sda_short ?></td>
		    <td><?php echo $row->sda_desc ?> </td>
                    <td><?php echo $this->logmodel->get_listspfic1('edrpuser', 'username', 'id', $row->sda_archuserid)->username ?> </td>
                    <td><?php echo $row->sda_archdate ?> </td>

	       </td>
               </tr>
 	  <?php } ?>  
 </tbody>
        </table>
        </div><!------scroller div------>
  </body>
 <div align="center"> <?php $this->load->view('template/footer');?></div>
</html>
      
