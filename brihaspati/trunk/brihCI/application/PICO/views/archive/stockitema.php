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
            echo "<b>Stock Items Archive Details</b>";
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
                <th>Stock Id</th>
                <th>Material Type</th>
                <th>Stock Details</th>
               <!-- <th>By Designation</th> -->
                <th>Status</th>
                <th>Archiver Detais</th>
              <!--  <th>Department Name</th>
                <th>Department Nick Name</th>
                <th>Department Description</th>
                <th>Archiver Name</th>
                <th>Archive date</th>-->
                </tr>
	    </thead>
<!--
stocka_stockid
stocka_mtid
stocka_name
stocka_qty
stocka_desc
stocka_upstatus
stocka_creatorname
stocka_creatordate
-->
            <tbody>
                    <?php $count =0?>
                    <?php foreach($sia as $row) 
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
                        echo "<td>" . $row->stocka_id. "</td>";
                        echo "<td>" . $this->picomodel->get_listspfic1('material_type','mt_name','mt_id',$row->stocka_mtid)->mt_name . "</td>";
			echo "<td>" . $row->stocka_name . "</br>";
			echo $row->stocka_qty. "</br>";
			echo $row->stocka_desc. "</br>";
			echo "</td>";
                        echo "<td>" . $row->stocka_upstatus . "</td>";
//                        echo "<td>" . $row->tc_bydesig. "</td>";
                        echo "<td>" . $row->stocka_creatorname. "</br>";
			echo $row->stocka_creatordate;
			echo "</td>";
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

                                                                     
 


