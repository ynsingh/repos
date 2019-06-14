
<!--@name subjectsemester.php  @author Nagendra Kumar Singh(nksinghiitk@gmail.com) -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
<title>Subject Semester Program with Department List</title>
    <head>    
            <?php $this->load->view('template/header'); ?>
        <?php //$this->load->view('template/menu');?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">   
       <!-- <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/bootstrap.min.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>-->
        
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
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->
        <table width="100%;"> 
           
            <tr><td> 
                 <?php
                    echo "<td align=\"left\" width=\"33%\">";
//                    echo anchor('map/mapsubsem/', "Map Subject Semester Program Department ", array('title' => 'Add Detail' , 'class' => 'top_parent'));
                    echo "</td>";
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Subject Semester Program Department Details</b>";
                    echo "</td>";
                    echo "<td align=\"right\" width=\"33%\">";
//		 $help_uri = site_url()."/help/helpdoc#ViewMapSubjectandSemester";
//		 echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                 echo "</td>";
		 ?>
                <div>
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
        <div class="scroller_sub_page">
            <table  class="TFtable">
            <thead>
                <th>Sr.No</th>
                
                <th>Prog Name</th>
                <th>Prog Branch</th>
                <th>Prog Pattern</th>
                <th>Sem/Year</th>
                <th>Sub Name</th>
                <th>Subject Type</th>
                <!--<th>Creator Name</th>
                <th>Creation Date</th>
                <th>Modifier Name</th>
                <th>Modify Date</th>
                <th>Action</th>-->
               <!-- <th></th>-->
           <!-- <tr></tr>-->
        </thead>
        <tbody>
           <?php $serial_no = 1;?>
            <?php if( count($subsemrec) ): ?>
                <?php 
				$pre="p";
				foreach($subsemrec as $row){ ?>
                    <tr>
                   
                    <?php 	$z=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$row->subp_id)->dept_name;
								if(!($pre==$z)){
									echo "<tr><td colspan=8 style=\"text-align:center; font-weight:bold\">";
									echo $z;
									echo "</tr></td>";
								}
								$pre=$z;
								//echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$row->subp_dept)->dept_name ?></td>
                     <td><?php echo $serial_no++; ?></td>   
					<td><?php echo $this->commodel->get_listspfic1('program','prg_name','prg_id',$row->subp_degree)->prg_name ?></td>
                    <td><?php echo $this->commodel->get_listspfic1('program','prg_branch','prg_id',$row->subp_degree)->prg_branch ?></td>
                    <td><?php echo $this->commodel->get_listspfic1('program','prg_pattern','prg_id',$row->subp_degree)->prg_pattern ?></td>
                    <td><?php echo $row->subp_sem; ?></td>
                    <td><?php echo $this->commodel->get_listspfic1('subject','sub_name','sub_id',$row->subp_sub_id)->sub_name ?></td>
                    <td><?php echo $row->subp_subtype; ?></td>
                    <!-- <td><//?php echo $record->spsc_creatorid; ?></td>
                    <td><//?php echo $record->spsc_createdate; ?></td>
                    <td><//?php echo $record->spsc_modifierid; ?></td>
                    <td><//?php echo $record->psc_modifydate; ?></td> -->
		     <?php  
		     //echo "<td>";
//		    	echo anchor("map/deletesubsem/{$row->subsem_id}","Delete",array('title' => 'Details' , 'class' => 'red-link' ,'onclick' => "return confirm('Are you sure you want to delete this record')")); 
		    //	echo "&nbsp;&nbsp;";  
			//echo anchor("map/editsubsem/{$row->subsem_id}","Edit",array('title' => 'Details' , 'class' => 'red-link')); 
//			echo "</td>";
			echo "</tr>";
                 	}; //close for each loop
             	else : 
                    echo "<td colspan= \"10\" align=\"center\"> No Records found...!</td>";
            	 endif;?> 
        </tbody>
    </table>
    </body>  
   <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

