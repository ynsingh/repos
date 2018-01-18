<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name dispdepartment.php  
  @author Neha Khullar (nehukhullar@gmail.com)
 -->
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
            echo "<b>Department Archive Details</b>";
            echo "</td>";
    	?>
             <?php echo validation_errors('<div class="isa_warning>','</div>');?>
              <?php echo form_error('<div class="isa_error">','</div>');?>
               <?php if(isset($_SESSION['success'])){?>
                    <div  class="isa_success"><?php echo $_SESSION['success'];?></div>
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
                <th>University Name</th>
                <th>Campus Name</th>
                <th>Authorities Name</th>
                <th>School/Faculty Code</th>
                <th>School/Faculty Name</th>
                <th>Department Code</th>
                <th>Department Name</th>
                <th>Department Nick Name</th>
                <th>Department Description</th>
                <th>Archiver Name</th>
                <th>Archive date</th>
                </tr>
            </thead>
            <tbody>
                    <?php $count =0?>
                    <?php foreach($this->deptaresult as $row) 
			{ 
		        echo "<tr>";
                        echo "<td>" . $this->common_model->get_listspfic1('org_profile','org_name','org_code',$row->depta_orgcode)->org_name. "</td>";
                        echo "<td>" . $this->common_model->get_listspfic1('study_center','sc_name','sc_code',$row->depta_sccode)->sc_name . "</td>";
                        echo "<td>". $this->logmodel->get_listspfic1('authorities','name','id',$row->depta_uoid)->name . "</td>";
                        echo "<td>" . $row->depta_schoolcode. "</td>";
                        echo "<td>" . $row->depta_schoolname . "</td>";
                        echo "<td>" . $row->depta_code . "</td>";
                        echo "<td>" . $row->depta_name . "</td>";
                        echo "<td>" . $row->depta_short. "</td>";
                        echo "<td>" . $row->depta_description. "</td>";
                        echo "<td>" . $row->creatorid. "</td>";
                        echo "<td>" . $row->createdate. "</td>";

                        //echo "<td>" . anchor('setup/deletedept/' . $row->dept_id , "Delete", array('title' => 'Delete Details' , 'class' => 'red-link' ,'onclick' => "return confirm('Are you sure you want to delete this record')")) . " ";
                       // echo "</br>";
                        echo "</tr>";
			}
		//	echo "</table>";
			?>	
            </tbody>
		</div>
          </table>
    </body>
 <p> &nbsp; </p>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

                                                                     
 


