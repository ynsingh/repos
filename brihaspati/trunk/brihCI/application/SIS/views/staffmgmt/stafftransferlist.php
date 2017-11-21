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
            <?php $this->load->view('template/menu');?>
    <!--        <h3>Welcome <?= $this->session->userdata('username') ?></h3>-->
       <p>
<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
</p> 
        </div>
        <table ><tr><td>
        <?php echo anchor('staffmgmt/stafftransfer/', "Staff Tansfer and Posting" ,array('title' => 'staff transfer nad posting ' , 'class' => 'top_parent'));
	;?>
        </td>
        </tr>
        </table>
         <div align="left" style="width:70%;">
            
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
</center>
        <table class="TFtable" >
            <thead>
                <tr align="center">
                    <th>Sr.No</th>
                    <th>Employee Name</th>
                    <th>Date of Relieve</th>
                    <th>Expected Date of joining</th>
                    <th>University Officer Control</th>
                    <th>Department Name</th>
                    <th>Designation</th>
                    <th>Post</th>
                    <th>Tranfer Order Copy</th>
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
                 <?php if( count($records) ):  ?>   
                <td colspan="12">
                <?php echo form_open_multipart('upl/exporttransferorder');?>
                <div><input type='submit' name='exportdata' id="btnUpload"  value='export to excel'/></div> 
                </form>
                <?php endif;?>
                
            </td>
            </tbody>
            
        </table>
        <div align="center">  <?php $this->load->view('template/footer');?></div>
    </body>    
</html>        
