
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name dispdepartment.php 
  @author Raju kamal (kamalraju8@gmail.com)
 -->
    <html>
    <head>
    <title> View Department</title> 
        <?php $this->load->view('template/header'); ?>
        <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
        <?php $this->load->view('template/menu');?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
    </head>    
    <body>

       <!-- <?//php
           echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
           echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
           echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
            echo "Setup";
            echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo "|";
            echo anchor('setup/dept/', "Add Department", array('title' => 'Add Detail' , 'class' => 'top_parent')) ." ";
            echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo "|";
            echo "<span  style='padding: 8px 8px 8px 20px;'>";
            echo "View Department";
            echo "</span>";
            echo "</td>";
            echo "</tr>";
            echo "</table>";
        ?>-->
	   <table style="margin-left:8px;">
	    <tr colspan="2"><td>
  	   <div>
	   <?php echo anchor('setup/dept','Add Department',array( 'class' => 'top_parent' ,'title'=>'Add Detail')); 
	   $help_uri = site_url()."/help/helpdoc#ViewDepartmentDetail";
           echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:78%\">Click for Help</b></a>";
           ?>
            <tr colspan="2"><td>    
                <div  style="margin-left:30px;width:1700px;">
                <?php echo validation_errors('<div style="margin-left:50px;" class="isa_warning>','</div>');?>
                <?php if(isset($_SESSION['success'])){?>
                    <div style="margin-left:30px;" class="isa_success"><?php echo $_SESSION['success'];?></div>
                <?php
                };
               	echo $this->session->flashdata('flash_data');
		echo $this->session->flashdata('error');
                ?>
            	</div>
        </td></tr>   
        <tr>
           <div align="left" style="margin-left:30px;">
          <table cellpadding="16" style="margin-left:30px;" class="TFtable">
          <thead>
          <tr align="center">
       <th>University Name</th><th>Campus Name</th><th>School Code</th><th>School Name</th><th>Department Code</th><th>Department Name</th><th>Department Nike Name</th><th>Department Description</th><th>Action</th><th></th></tr></thead>
                 <?php
                    foreach ($this->deptresult as $row)
                    {
                     
                        echo "<tr align=\"center\">";
			echo "<td>" . $this->common_model->get_listspfic1('org_profile','org_name','org_code',$row->dept_orgcode)->org_name. "</td>";
	      	        echo "<td>" . $this->common_model->get_listspfic1('study_center','sc_name','sc_code',$row->dept_sccode)->sc_name . "</td>";
                        echo "<td>" . $row->dept_schoolcode. "</td>";
                        echo "<td>" . $row->dept_schoolname . "</td>";
                        echo "<td>" . $row->dept_code . "</td>";
                        echo "<td>" . $row->dept_name . "</td>";
                        echo "<td>" . $row->dept_short. "</td>";
                        echo "<td>" . $row->dept_description. "</td>";
                        
                        echo "<td>" . anchor('setup/deletedept/' . $row->dept_id , "Delete", array('title' => 'Delete Details' , 'class' => 'red-link' ,'onclick' => "return confirm('Are you sure you want to delete this record')")) . " ";
                        echo "<td>" . anchor('setup/editdepartment/' . $row->dept_id , "Edit", array('title' => 'Edit Details' , 'class' => 'red-link')) . " ";
                        echo "</br>";
                        echo "</tr>";
                    }
                    echo "</table>";
                ?>
            </div>
        </tr>
    </table>    
    </body>   
  <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>
