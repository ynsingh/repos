
<!--@name subjectsemester.php  @author Nagendra Kumar Singh(nksinghiitk@gmail.com) -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
<title>Subject Semester Program with Department List</title>
    <head>    
            <?php      $this->load->view('template/header'); ?>
            
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
        <table style="margin-left:2%;width:100%;"> 
           
            <tr><td> 
                 <?php echo anchor('map/mapsubsem/', "Map Subject Semester Program Department ", array('title' => 'Add Detail' , 'class' => 'top_parent'));
		 $help_uri = site_url()."/help/helpdoc#";
		 echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:52%\">Click for Help</b></a>";
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
        <br/>
      <!-- <div class="panel panel-primary"> -->
            <table cellpadding="16" style="margin-left:2%;" class="TFtable">
            <thead >
            <tr align="center">
                <th>Sr.No</th>
                <th>Deptt. Name</th>
                <th>Prog Name</th>
                <th>Prog Branch</th>
                <th>Prog Pattern</th>
                <th>Sem/Year</th>
                <th>Sub Name</th>
                <th>Subject Type</th>
                <!--<th>Creator Name</th>
                <th>Creation Date</th>
                <th>Modifier Name</th>
                <th>Modify Date</th>-->
                <th>Action</th>
               <!-- <th></th>-->
            </tr>
           <!-- <tr></tr>-->
        </thead>
        <tbody>
           <?php $serial_no = 1;?>
            <?php if( count($subsemrec) ): ?>
                <?php foreach($subsemrec as $row){ ?>
                    <tr align="center">
                    <td><?php echo $serial_no++; ?></td>   
                    <td><?php echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$row->subsem_ext1)->dept_name ?></td>
                    <td><?php echo $this->commodel->get_listspfic1('program','prg_name','prg_id',$row->subsem_prgid)->prg_name ?></td>
                    <td><?php echo $this->commodel->get_listspfic1('program','prg_branch','prg_id',$row->subsem_prgid)->prg_branch ?></td>
                    <td><?php echo $this->commodel->get_listspfic1('program','prg_pattern','prg_id',$row->subsem_prgid)->prg_pattern ?></td>
                    <td><?php echo $row->subsem_semester; ?></td>
                    <td><?php echo $this->commodel->get_listspfic1('subject','sub_name','sub_id',$row->subsem_subid)->sub_name ?></td>
                    <td><?php echo $row->subsem_subtype; ?></td>
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
                    echo "<td colspan= \"10\" align=\"center\"> No Records found...!</td>";
            	 endif;?> 
        </tbody>
    </table> 
  <!-- </div>  -->     
    </body>  
   <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

