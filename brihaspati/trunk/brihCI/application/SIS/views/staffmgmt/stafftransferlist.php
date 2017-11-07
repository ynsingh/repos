<!--@filename stafftransferlist.php  @author Manorama Pal(palseema30@gmail.com) -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
    <head>
        <title>Welcome to TANUVAS</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">   
    </head>
    <body>
        <div>
           
            <?php $this->load->view('template/header'); ?>
            <h3>Welcome <?= $this->session->userdata('username') ?></h3>
            <?php $this->load->view('template/menu');?>
        
        </div>
        <table style="margin-left:1%;width:97%;"><tr><td>
        <?php echo anchor('staffmgmt/stafftransfer/', "Staff Tansfer and Posting" ,array('title' => 'staff transfer nad posting ' , 'class' => 'top_parent'));
	;?>
        </td>
        </tr>
        </table>
         <div align="left" style="margin-left:2%;width:95%;">
            
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
        <table cellpadding="16" style="margin-left:2%;" class="TFtable" >
            <thead>
                <tr align="center">
                    <th>Sr.No</th>
                    <th>Employee Name</th>
                    <th>Date of Relief</th>
                    <th>Expected Date of joining</th>
                    <th>University Officer Control</th>
                    <th>Department Name</th>
                    <th>Designation</th>
                    <th>Post</th>
                    <th>Tranfer order copy</th>
                    <!--<th>Action</th>-->
                    
                </tr>
            </thead>
            <tbody>
                <?php $serial_no = 1;?>
              <?php if( count($records) ):  ?>
                    <?php foreach($records as $record){ ?>
                        <tr align="center">
                            <td><?php echo $serial_no++; ?></td>
                            <td><?php echo $this->sismodel->get_listspfic1('employee_master','emp_name','emp_id',$record->uit_staffname)->emp_name;; ?></td>
                            <td><?php echo $record->uit_dateofrelief;?></td>
                            <td><?php echo $record->uit_dateofjoining;?></td>
                            <td><?php echo $this->lgnmodel->get_listspfic1('authorities','name','id' ,$record->uit_uoc_to)->name; ?></td>
                            <td><?php echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$record->uit_dept_to)->dept_name; ?></td>
                            <td><?php echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$record->uit_desig_to)->desig_name; ?></td>
                            <td><?php echo $record->uit_post_to; ?></td>
                            <td> <?php echo anchor("staffmgmt/transferordercopy/{$record->uit_staffname}","View Transfer order copy",array('title' => 'View Transfer order' , 'class' => 'red-link')); ?></td>
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
