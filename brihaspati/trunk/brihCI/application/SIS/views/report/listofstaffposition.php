<!--@filename listofstaffposition.php  @author Nagendra Kumar Singh(nksinghiitk@gmail.com) 
	@author Om Prakash (omprakashkgp@gmail.com)
-->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Welcome to TANUVAS</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">   
    </head>
    <body>
            <?php $this->load->view('template/header'); ?>
            <?php $this->load->view('template/menu');?>
            <!--<h3>Welcome <?= $this->session->userdata('username') ?></h3>-->
<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
        <table width="100%"><tr colspan="2"><td>
        <?php //echo anchor('staffmgmt/staffprofile/', "Add Profile" ,array('title' => 'Add staff profile ' , 'class' => 'top_parent'));
        //$help_uri = site_url()."/help/helpdoc#ViewEmployeeList";
       // echo "<a target=\"_blanik\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:54%\">Click for Help</b></a>";
        ?>
        <div>
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                <?php echo form_error('<div class="isa_error">','</div>');?>

                <?php if(isset($_SESSION['success'])){?>
                    <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                <?php
                };
                ?>
                 <?php if(isset($_SESSION['err_message'])){?>
                    <div  class="isa_error"><?php echo $_SESSION['err_message'];?></div>
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
                   <!-- <th>Sr.No</th>-->
                    <th>SS</th> 
                    <th>P</th> 
                    <th>V</th> 
                    <th>Name of The Employee </th>
                    <th>Designation</th>
                </tr>
            </thead>
            <tbody>
                <?php $count = 0; 
		$ouoid = 0;
		$odid = 0;
		$nop = 0;	
               if( count($records) ):  ?>
                    <?php foreach($records as $record){
//                        print_r($record);
			if($ouoid !=$record->sp_uo){
			echo "<tr>";
			echo "<td colspan=5 style=\"text-align:center;\">";
			echo " <b> UO CONTROL : ";
			echo "&nbsp;&nbsp;";
			echo $this->lgnmodel->get_listspfic1('authorities','name','id' ,$record->sp_uo)->name;
			echo "</b></td>";
			echo "</tr>";
			$ouoid=$record->sp_uo;
			}
			if($odid !=$record->sp_dept){
                        echo "<tr><td colspan=5 align=left><b> Department : ";
			echo "&nbsp;&nbsp;";
			echo $this->commodel->get_listspfic1('Department','dept_code','dept_id',$record->sp_dept)->dept_code;
			echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			echo  $this->sismodel->get_listspfic1('employee_master','emp_address','emp_uocid',$record->sp_uo)->emp_address;
                        echo "</b></td></tr>";
			$odid = $record->sp_dept;
                        }
			if($nop != $record->sp_emppost){
                        echo "<tr><td colspan=5 align=left><b> Name of The Post : ";
			echo $record->sp_grppost;
			//echo  $this->commodel->get_listspfic1('designation','desig_name','desig_id', $record->sp_emppost)->desig_name; 
			echo "</b></td>";
			echo "</tr>";
			//$nop = $record->sp_emppost; 
			$nop = 0; 
			$count = 0;
			}
			echo "<tr>";
			//echo "<td>". ++$count ." </td>";
			echo "<td> $record->sp_sancstrenght</td>";
			echo "<td> $record->sp_position</td>";
			echo "<td> $record->sp_vacant</td>";
			echo "<td>";
			echo  $this->sismodel->get_listspfic1('employee_master','emp_name','emp_uocid',$record->sp_uo)->emp_name;
			echo "</td>";
			echo "<td>";
			echo  $this->sismodel->get_listspfic1('employee_master','emp_post','emp_uocid',$record->sp_uo)->emp_post;
			echo "</td>";
			
?>
                        </tr>
                    <?php }; ?>
                <?php else : ?>
                    <td colspan= "13" align="center"> No Records found...!</td>
                <?php endif;?>
                </tbody>
        </table>
        </div><!------scroller div------>
        <div align="center">  <?php $this->load->view('template/footer');?></div>

    </body>
</html>
