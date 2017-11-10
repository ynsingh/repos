<!--@filename employeelist.php  @author Manorama Pal(palseema30@gmail.com) 
    modification in gui - 16 OCT 2017 
-->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Welcome to TANUVAS</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">   
    </head>
    <body>
    <center>
        <div>
           
            <?php $this->load->view('template/header'); ?>
            <h3>Welcome <?= $this->session->userdata('username') ?></h3>
            <?php $this->load->view('template/menu');?>
        
        </div>
        <table style="width:70%;"><tr><td>
        <?php echo anchor('staffmgmt/staffprofile/', "Add Profile" ,array('title' => 'Add staff profile ' , 'class' => 'top_parent'));
	$help_uri = site_url()."/help/helpdoc#ViewEmployeeList";
        echo "<a target=\"_blanik\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:54%\">Click for Help</b></a>";
        ?>
        </td>
        
        </tr>
        </table></center>
        <div "style=width:70%;">
            
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
        <table cellpadding="16" class="TFtable" >
            <thead>
                <tr align="center">
                    <th>Sr.No</th>
                    <th></th>
                    <th>Employee Name</th>
                    <th>Campus Name</th>
                    <th>University Officer Control</th>
                    <th>Department Name</th>
                    <th>Scheme Name</th>
                    <th>Specialisation(Major Subject)</th>
                    <th>Designation</th>
                    <!--<th>Employee Post</th>-->
                   <!-- <th>Pay Band</th>-->
                    <th>E-Mail ID</th>
                    <th>Contact No</th>
                    <th>Aadhaar No</th>
                    <th>Action</th>
                    
                </tr>
            </thead>
            <tbody>
                <?php $serial_no = 1;?>
              <?php if( count($records) ):  ?>
                    <?php foreach($records as $record){ ?>
                        <tr align="center">
                            <td><?php echo $serial_no++; ?></td>
                            <?php //$img=$record->emp_code;?>
                            <?php if(!empty($record->emp_photoname)):?>
                            <td><p><img src="<?php echo base_url('uploads/SIS/empphoto/'.$record->emp_photoname);?>"  alt="" v:shapes="_x0000_i1025" width="78" height="94"></p></td>
                            <?php else :?>
                            <td><p><img src="<?php echo base_url('uploads/SIS/empphoto/empdemopic.jpeg');?>"  alt="" v:shapes="_x0000_i1025" width="78" height="94"></p></td>
                            <?php endif;?>
                            <td><?php echo $record->emp_name."<br/>" ."("."PF No:".$record->emp_code.")"; ?></td>
                            <td><?php echo $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$record->emp_scid)->sc_name; ?></td>
                            <td><?php echo $this->lgnmodel->get_listspfic1('authorities','name','id' ,$record->emp_uocid)->name; ?></td>
                            <td><?php echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$record->emp_dept_code)->dept_name; ?></td>
                            <?php if(!empty($record->emp_schemeid)):?>
                            <td><?php echo $this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$record->emp_schemeid)->sd_name; ?></td>
                            <?php else : ?>
                            <td></td>
                            <?php endif;?>
                            <?php if(!empty($record->emp_specialisationid)) :?>
                            <td><?php echo $this->commodel->get_listspfic1('subject','sub_name','sub_id',$record->emp_specialisationid)->sub_name; ?></td>
                            <?php else : ?>
                            <td></td>
                            <?php endif;?>
                            <td><?php echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$record->emp_desig_code)->desig_name; ?></td>
                           <!-- <td><?php //echo $record->emp_post; ?></td>-->
                           <!-- <td></td>-->
                            <td><?php echo $record->emp_email; ?></td>
                            <td><?php echo $record->emp_phone; ?></td>
                            <td><?php echo $record->emp_aadhaar_no; ?></td>
                            <td> <?php echo anchor("staffmgmt/editempprofile/{$record->emp_id}","View/Edit",array('title' => 'View/Edit Details' , 'class' => 'red-link')); ?></td>
                        </tr>
                    <?php }; ?>
                <?php else : ?>
                    <td colspan= "12" align="center"> No Records found...!</td>
                <?php endif;?>

            </tbody>
        </table>
        <div align="center">  <?php $this->load->view('template/footer');?></div>
        
    </body>    
</html>   
