<!--@name displayrole.php
  @author Nagendra Kumar Singh (nksinghiitk@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>View Grade Master</title>
  <head>
   <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
   <?php $this->load->view('template/header'); ?>
    <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
    <?php $this->load->view('template/menu');?>
  </head>
 <body>
    <!--<//?php
        echo "<table border=\"0\" align=\"left\" style=\"color: black;  border-collapse:collapse; border:1px;\">";
        echo "<tr style=\"text-align:left; \">";
        echo "<td style=\"padding: 8px 8px 8px 20px; decoration:none;\">";
        echo anchor('setup/role/', "Add Role", array('title' => 'Add Detail'));
        echo "</td>";
        echo "</tr>";
        echo "</table>";
        ?>--!>

      <table width="100%" style="margin-left:2%;">
            <tr><td>
                <div align="left">
		<?php  echo anchor('setup2/addgrade/', "Add Grade", array('title' => 'Add Grade Detail','class' =>'top_parent'));
		?>
                 <?php
                 $help_uri = site_url()."/help/helpdoc#ViewGrade";
		 echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:74%\">Click for Help</b></a>";
                 ?>
                <div  style="width:90%;margin-left:2%">
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
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

<table>
<tr>
<div align="left" style="margin-left:2%;">
<table cellpadding="16" style="margin-left:2%;" class="TFtable" >
<tr align="center">
<thead><th>Sr.No</th><th>Grade Name</th><th>Grade Point</th><th>Grade Short</th><th>Grade Description</th><th>Action</th></tr></thead>

   <?php
        $count =0;
        foreach ($this->result as $row)
        {  
         ?>
             <tr align="center">
            <td> <?php echo ++$count; ?> </td> 
            <td> <?php echo $row->gm_gradename ?></td>
            <td> <?php echo $row->gm_gradepoint ?></td>
            <td> <?php echo $row->gm_short ?></td>
            <td> <?php echo $row->gm_desc ?></td>
	    <td>
	<?php  
		if($row->gm_id > 6){
	    		//echo anchor('setup2/deletegrade/' . $row->gm_id , "Delete", array('title' => 'Edit Details' , 'class' => 'red-link','onclick' => "return confirm('Are you sure you want to delete this record')")) . " ";
	    		echo "&nbsp; ";
			echo anchor('setup2/editgrade/' . $row->gm_id , "Edit", array('title' => 'Details' , 'class' => 'red-link')) . " ";
		}
            echo "</td>";
            echo "</tr>";
          
        }
        echo "</table>";
        echo "</td>";
        echo "</tr>";
        echo "</table>";
          ?>
</div>
</tr>
</table>
</body>
<div align="center">  <?php $this->load->view('template/footer');?></div>
</html>





