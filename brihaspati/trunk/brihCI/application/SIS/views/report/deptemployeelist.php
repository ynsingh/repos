<!--@filename deptemployeelist.php  @author Nagendra Kumar Singh(nksinghiitk@gmail.com) 
-->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Welcome to TANUVAS</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">   
    </head>
    <body>
            <?php $this->load->view('template/header'); ?>
            
        <table width="100%"><tr colspan="2"><td>
	<?php
                    echo "<td align=\"center\" width=\"100%\">";
                    echo "<b>Department Wise Staff List Details</b>";
                    echo "</td>";
            ?>
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
                    <th>Sr.No</th>
                   <!-- <th></th> -->
                    <th>Employee Name</th>
                <!--    <th>Designation</th>
                    <th>University Officer Control</th>
                    <th>Department Name</th>
                    <th>Scheme Name</th>
                    <th>Specialisation(Major Subject)</th> -->
                    <th>Designation</th>
                    <!--<th>Employee Post</th>-->
                   <!-- <th>Pay Band</th>-->
                 <!--   <th>E-Mail ID</th>
                    <th>Contact No</th>
                    <th>Aadhaar No</th>
                    <th>Action</th>
                    -->
                </tr>
            </thead>
            <tbody>
                <?php $serial_no = 1;
		$ouoid = 0;
		$odid = 0;
		
               if( count($records) ):  ?>
                    <?php foreach($records as $record){
//                        print_r($record);
			if($ouoid !=$record->emp_uocid){
			echo "<tr>";
			echo "<td colspan=4 style=\"text-align:center;\">";
			echo " <b> UO CONTROL : ";
			echo $this->lgnmodel->get_listspfic1('authorities','name','id' ,$record->emp_uocid)->name;
			echo "</b></td>";
			echo "</tr>";
			$ouoid=$record->emp_uocid;
			}
			if($odid !=$record->emp_dept_code){
                        echo "<tr><td colspan=4 align=left><b> Department : ";
			echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$record->emp_dept_code)->dept_name;
			echo " ( ". $this->commodel->get_listspfic1('Department','dept_code','dept_id',$record->emp_dept_code)->dept_code ." )";
                        echo "</b></td></tr>";
			$odid = $record->emp_dept_code;
			$serial_no = 1;
                        }
			echo "<tr>";
			echo "<td>". $serial_no++ ." </td>";
			echo "<td> $record->emp_name</td>";
			echo "<td> $record->emp_post</td>";
?>
                        </tr>
                    <?php }; ?>
                <?php else : ?>
                    <td colspan= "13" align="center"> No Records found...!</td>
                <?php endif;?>
                </tbody>
        </table>
        </div><!------scroller div------>
	<p> &nbsp; </p>
        <div align="center">  <?php $this->load->view('template/footer');?></div>

    </body>
</html>
