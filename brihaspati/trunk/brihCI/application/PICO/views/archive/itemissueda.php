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
            echo "<b>Items Issue/Returns Archive Details</b>";
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
siia_stockid
siia_mtid
siia_name
siia_qty
siia_desc
siia_upstatus
siia_creatorname
siia_creatordate
-->
            <tbody>
                    <?php $count =0?>
                    <?php foreach($iia as $row) 
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
                        echo "<td>" . $row->siia_stockid. "</td>";
                        echo "<td>" . $row->siia_mtid . "</td>";
			echo "<td>" . $row->siia_name . "</br>";
			echo $row->siia_qty. "</br>";
			echo $row->siia_desc. "</br>";
			echo "</td>";
                        echo "<td>" . $row->siia_upstatus . "</td>";
//                        echo "<td>" . $row->tc_bydesig. "</td>";
                        echo "<td>" . $row->siia_creatorname. "</br>";
			echo $row->siia_creatordate;
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

                                                                     
 


