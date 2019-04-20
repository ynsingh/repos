<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name  displayscheme.php 
  @author Rekha Devi Pal(rekha20uly@gmail.com)
 -->

<html>
<title>Display Scheme</title>
<head>    
    <?php $this->load->view('template/header'); ?>
   
    <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css"> 	
</head>    

 <table width="100%">
        <tr colspan="2"><td>
         <?php
            echo "<td align=\"left\" width=\"33%\">";
            echo anchor('setup/scheme/', 'Add Scheme', array('class' => 'top_parent'));
            echo "</td>";
            echo "<td align=\"center\" width=\"34%\">";
            echo "<b>Scheme Details</b>";
            echo "</td>";
            echo "<td align=\"right\" width=\"33%\">";
	    $help_uri = site_url()."/help/helpdoc#ViewSchemeDetail";
	    echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
            echo "</td>";
         ?>
       <div align="left" style="margin-left:0%;width:95%">
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
        <th>Scheme Name (Code)</th>
        <!--<th>Scheme Code </th>-->
        <th>Scheme Short Name </th>
        <th>Scheme Description </th>
        <th>Action </th>
        </thead>
	<tbody>
	     <?php
		$count = 0;
	        foreach ($this->result as $row)
                {
              ?>    
		<tr>
                    <td><?php echo ++$count; ?> </td>
                    <td><?php echo $this->common_model->get_listspfic1('Department','dept_name', 'dept_id',$row->sd_deptid)->dept_name;?></td>
                    <td><?php echo $row->sd_name ;?>
	<!--		 </td>
                	    <td> -->
			<?php echo " ( ".$row->sd_code ." ) "; ?> </td>
                    <td><?php echo $row->sd_short; ?></td>
		    <td><?php echo $row->sd_desc; ?> </td>
             	    <td><?php echo anchor('setup/editscheme/' . $row->sd_id , "Edit", array('title' => 'Edit Details' , 'class' => 'red-link')); ?>
	       </td>
               </tr>
 	  <?php } ?>  
 </tbody>
        </table>
        </div><!------scroller div------>
  </body>
<p>&nbsp;</p>
 <div align="center"> <?php $this->load->view('template/footer');?></div>
</html>
      
