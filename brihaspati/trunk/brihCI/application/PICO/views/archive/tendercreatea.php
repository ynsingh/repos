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
            echo "<b>Tender Create Archive Details</b>";
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
                <th> Tender Details</th>
                <th>Tender Refernece No</th>
                <th>Status</th>
                <th>Created By</th>
                <th>By Designation</th>
                <th>Date</th>
              <!--  <th>Department Name</th>
                <th>Department Nick Name</th>
                <th>Department Description</th>
                <th>Archiver Name</th>
                <th>Archive date</th>-->
                </tr>
	    </thead>
<!-- archive_id	tc_id	tc_refno	tc_approvedstatus	tc_byname	tc_bydesig	archive_datetime -->

            <tbody>
                    <?php $count =0?>
                    <?php foreach($tca as $row) 
			{ 
		        echo "<tr>";
                     //   echo "<td>" . $this->common_model->get_listspfic1('org_profile','org_name','org_code',$row->depta_orgcode)->org_name. "</td>";
                       // echo "<td>" ;
		//	if(!empty($row->depta_sccode)){
			//	$res= $this->common_model->get_listspfic1('study_center','sc_name','sc_code',$row->depta_sccode);
			//	if(!empty($res)){
			//echo	$res->sc_name;
			//	}
		//	}
		//	echo "</td>";
                  //      echo "<td>". $this->logmodel->get_listspfic1('authorities','name','id',$row->depta_uoid)->name . "</td>";
                        echo "<td>" . $row->tc_id. "</td>";
                        echo "<td>" . $row->tc_refno . "</td>";
                        echo "<td>" . $row->tc_approvedstatus . "</td>";
                        echo "<td>" . $row->tc_byname . "</td>";
                        echo "<td>" . $row->tc_bydesig. "</td>";
                        echo "<td>" . $row->archive_datetime. "</td>";
//                        echo "<td>" . $row->creatorid. "</td>";
  //                      echo "<td>" . $row->createdate. "</td>";

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

                                                                     
 


