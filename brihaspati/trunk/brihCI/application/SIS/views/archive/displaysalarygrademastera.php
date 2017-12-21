<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name  displaysalarygrademastera.php 
  @author Om Prakash (omprakashkgp@gmail.com) 
 -->

<html>
<title>Display Salary Grade Master Archive</title>
<head>    
    <?php $this->load->view('template/header'); ?>
    <?php $this->load->view('template/menu');?>
    <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css"> 	
</head>    
 <body>
<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
<table width='100%'>
	<tr colspan="2">
         <?php
            echo "<td align=\"center\" width=\"34%\">";
            echo "<b>Salary Grade Details</b>";
            echo "</td>";
         ?>
	<div align="left" style="margin-left:0%;width:95%;">
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
   </tr>
  </table>
        <div class="scroller_sub_page">
        <table class="TFtable" >
            <thead>
                <tr>
        <th>Sr.No</th>
        <th>AGP Code </th>
        <th>Salary Grade Name </th>
        <th>Salary Grade Max </th>
        <th>Salary Grade Min</th>
        <th>Salary Grade Pay Band </th>
        <th>Archiver's Name </th>
        <th>Archiver's Date </th>
        </thead></tr>
	<tbody>
	     <?php
		$count = 0;
	        foreach ($this->result as $row)
                {
              ?>    
		<tr>
                    <td><?php echo ++$count; ?> </td>
                    <td><?php echo $row->sgma_sgmid;?> </td>
                    <td><?php echo $row->sgma_name;?> </td>
                    <td><?php echo $row->sgma_max ;?> </td>
                    <td><?php echo $row->sgma_min ;?></td>
		    <td><?php echo $row->sgma_gradepay ?> </td>
                    <td><?php echo $this->logmodel->get_listspfic1('edrpuser', 'username', 'id', $row->sgma_archuserid)->username ?> </td>
                    <td><?php echo $row->sgma_archdate ?> </td>

	       </td>
               </tr>
 	  <?php } ?> 
	</tbody>
        </table>
        </div><!------scroller div------> 
  </body>
 <div align="center"> <?php $this->load->view('template/footer');?></div>
</html>
      
