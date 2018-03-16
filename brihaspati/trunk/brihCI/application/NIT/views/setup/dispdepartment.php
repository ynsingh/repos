
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name dispdepartment.php 
  @author Raju kamal (kamalraju8@gmail.com)
 -->
    <html>
    <head>
    <title> View Department</title> 
		<!--<link rel="shortcut icon" href="<?php //echo base_url('assets/images'); ?>/index.jpg">-->
	<link rel="icon" href="<?php echo base_url('uploads/logo'); ?>/nitsindex.png" type="image/png">	

        <?php $this->load->view('template/header'); ?>
        <?php //$this->load->view('template/menu');?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
    </head>    
    <body>
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->


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
          <table  width="100%">
	   <tr><td>
          <?php
           echo "<td align=\"left\" width=\"33%\">";
           echo anchor('setup/dept','Add Department',array( 'class' => 'top_parent' ,'title'=>'Add Detail')); 
           echo "</td>";

           echo "<td align=\"center\" width=\"34%\">";
           echo "<b>Department Details</b>";
           echo "</td>";

           echo "<td align=\"right\" width=\"33%\">";
    	   $help_uri = site_url()."/help/helpdoc#ViewDepartmentDetail";
           echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
           echo "</td>";
           ?>
                <div>
                <?php echo validation_errors('<div class="isa_warning>','</div>');?>
                <?php if(isset($_SESSION['success'])){?>
                <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                <?php
                };
               	echo $this->session->flashdata('flash_data');
		echo $this->session->flashdata('error');
                ?>
            	</div>
        </td></tr>      
        </table>     
         <div class="scroller_sub_page">
          <table  class="TFtable">
          <thead>
          <th>Sr. No.</th><!--<th>Campus Name</th><th>Authorities Name</th>--><th>Deptt. Name</th><th>Deptt. Code</th><th>Deptt. Nick Name</th><th>Deptt. Description</th><th>Action</th></tr></thead>
                 <?php
		    $orid = 0;
		    $studycid=0;	
                    foreach ($this->deptresult as $row)
                    {
			$orgid = $this->common_model->get_listspfic1('org_profile','org_id','org_code',$row->dept_orgcode)->org_id;
			if($orid != $orgid){
		?>
			<!--<tr>
				<td colspan=10 style="font-size:18px;text-align:center;">
				<b>Institute Name :</b> 
                        	<?php
				if(!empty($orgid)){
                         		echo $this->common_model->get_listspfic1('org_profile','org_name','org_id',$orgid)->org_name;
				}
                        	?></td>
			</tr>-->
                     	<?php $orid =$orgid;
			$serial_no = 1;
			}
			//$campusid = $this->common_model->get_listspfic1('study_center','sc_id','sc_code',$row->dept_sccode)->sc_id;
			//if($studycid != $campusid){
		?>
			<!--<tr>
				<td colspan=10 style="font-size:18px;">
				<b>Campus Name :</b> 

                        	<?php
				//if(!empty($campusid)){
                         		//echo $this->common_model->get_listspfic1('study_center','sc_name','sc_id',$campusid)->sc_name;
				//}
                        	?></td>
			</tr>-->
                     	<?php //$studycid = $campusid;
			//
			//}
			
                        echo "<tr>";
			echo "<td>" .$serial_no++. "</td>";
			//echo "<td>" . $this->common_model->get_listspfic1('org_profile','org_name','org_code',$row->dept_orgcode)->org_name. "</td>";
	      	       // echo "<td>" . $this->common_model->get_listspfic1('study_center','sc_name','sc_code',$row->dept_sccode)->sc_name . "</td>";
                       // echo "<td>";
			//if(!empty($row->dept_uoid)){ 
			//	echo $this->login_model->get_listspfic1('authorities','name','id',$row->dept_uoid)-> name;
			//} 
			//echo "</td>";
			echo "<td>" . $row->dept_name . "</td>";
                        echo "<td>" . $row->dept_code . "</td>";
			echo "<td>" . $row->dept_short. "</td>";
			//echo "<td>" . $row->dept_schoolname . "</td>";	
                       // echo "<td>" . $row->dept_schoolcode. "</td>";
                        echo "<td>" . $row->dept_description. "</td>";
                        
         //               echo "<td>" . anchor('setup/deletedept/' . $row->dept_id , "Delete", array('title' => 'Delete Details' , 'class' => 'red-link' ,'onclick' => "return confirm('Are you sure you want to delete this record')")) . " ";
                        echo "<td>" . anchor('setup/editdepartment/' . $row->dept_id , "Edit", array('title' => 'Edit Details' , 'class' => 'red-link')) . " ";
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
