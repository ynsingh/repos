
<!--@name prerequisite.php  @author Nagendra Kumar Singh(nksinghiitk@gmail.com) -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
<title>Subject prerequisite Program with Department List</title>
    <head>    
        <?php $this->load->view('template/header'); ?>
            <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
        <?php $this->load->view('template/menu');?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">    
    </head>    
    <body>
        
    
        <!--<//?php
            echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
            echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
            echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
            echo "Map";
            echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo "|";
            echo anchor('map/mapscprgseat/', "Map Study Center Program Seat ", array('title' => 'Add Detail' , 'class' => 'top_parent'));
            echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo "|";
            echo "<span  style='padding: 8px 8px 8px 20px;'>";
            echo "View Study Center Program Seat";
            echo "</span>";
            echo "</td>";
            echo "</tr>";
            echo "</table>";
        ?>-->
        <!-- </br>   --> 
        <table style="margin-left:2%;width:100%;"> 
           
            <tr><td> 
                 <?php echo anchor('map/mapsubpre/', "Map Subject with Prerequisite", array('title' => 'Add Detail' , 'class' => 'top_parent'));
		 $help_uri = site_url()."/help/helpdoc#";
		 echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:65%\">Click for Help</b></a>";
		 ?>
                <div  style="margin-left:2%;width:90%;">
  
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
        <br/>
      <!-- <div class="panel panel-primary"> -->
            <table cellpadding="16" style="margin-left:2%;" class="TFtable">
            <thead >
            <tr align="center">
                <th>Sr.No</th>
                <th>Department Name</th>
                <th>Program Name</th>
                <th>Program Branch</th>
                <th>Program Pattern</th>
                <th>Subject</th>
                <th>Prerequisite Subject </th>
                <th>Paper</th>
                <th>Prerequisite Paper</th>
               <!-- <th>Creation Date</th>
                <th>Modifier Name</th>
                <th>Modify Date</th>-->
                <th>Action</th>
               <!-- <th></th>-->
            </tr>
           <!-- <tr></tr>-->
        </thead>
        <tbody>
           <?php $serial_no = 1;?>
            <?php if( count($subprerec) ): ?>
                <?php foreach($subprerec as $row){ //print_r($row);?>
                    <tr align="center">
                    <td><?php echo $serial_no++; ?></td>   
                    <?php $deptid=$this->commodel->get_listspfic1('program','prg_deptid','prg_id',$row->spreq_prgid)->prg_deptid ?>
                    <td><?php echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$deptid)->dept_name ?></td>
                    <td><?php echo $this->commodel->get_listspfic1('program','prg_name','prg_id',$row->spreq_prgid)->prg_name ?></td>
                    <td><?php echo $this->commodel->get_listspfic1('program','prg_branch','prg_id',$row->spreq_prgid)->prg_branch ?></td>
                    <td><?php echo $this->commodel->get_listspfic1('program','prg_pattern','prg_id',$row->spreq_prgid)->prg_pattern ?></td>
                    <td><?php echo $this->commodel->get_listspfic1('subject','sub_name','sub_id',$row->spreq_subid)->sub_name ?></td>
		    <td><?php 
			if($row->spreq_depsubid != NULL)		
				echo $this->commodel->get_listspfic1('subject','sub_name','sub_id',$row->spreq_depsubid)->sub_name 
		    ?></td>
		    <td><?php 
			if($row->spreq_subpid != NULL)		
				echo $this->commodel->get_listspfic1('subject_paper','subp_name','subp_id',$row->spreq_subpid)->subp_name 
		    ?></td>
		    <td><?php 
			if($row->spreq_depsubpid != NULL)		
				echo $this->commodel->get_listspfic1('subject_paper','subp_name','subp_id',$row->spreq_depsubpid)->subp_name 
		    ?></td>
                    <!-- <td><//?php echo $record->spsc_creatorid; ?></td>
                    <td><//?php echo $record->spsc_createdate; ?></td>
                    <td><//?php echo $record->spsc_modifierid; ?></td>
                    <td><//?php echo $record->psc_modifydate; ?></td> -->
		    <td> <?php  
//		    	echo anchor("map/deletesubsem/{$row->subsem_id}","Delete",array('title' => 'Details' , 'class' => 'red-link' ,'onclick' => "return confirm('Are you sure you want to delete this record')")); 
		    	echo "&nbsp;&nbsp;";  
//			echo anchor("map/editsubsem/{$row->subsem_id}","Edit",array('title' => 'Details' , 'class' => 'red-link')); 
			echo "</td></tr>";
                 	}; //close for each loop
             	else : 
                    echo "<td colspan= \"16\" align=\"center\"> No Records found...!</td>";
            	 endif;?> 
        </tbody>
    </table> 
  <!-- </div>  -->     
    </body>  
   <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

