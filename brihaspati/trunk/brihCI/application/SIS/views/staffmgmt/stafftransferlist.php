<!--@filename stafftransferlist.php  @author Manorama Pal(palseema30@gmail.com) -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
    <head>
        <title>Welcome to TANUVAS</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">   
    </head>
    <body> 
            <?php $this->load->view('template/header'); ?>
          
<table width="100%">
            <tr colspan="2"> 
        <?php 
	echo "<td align=\"left\" width=\"33%\">";
	echo anchor('staffmgmt/stafftransfer/', "Staff Tansfer and Posting" ,array('title' => 'staff transfer nad posting ' , 'class' => 'top_parent'));
	echo "</td>";
        echo "<td align=\"center\" width=\"34%\">";
        echo "<b>Staff Tansfer and Posting Details</b>";
        echo "</td>";
        echo "<td align=\"right\" width=\"33%\">";
	echo "</td>";
	;?>
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
        <table cellpadding="16" class="TFtable" >
            <thead>
                <tr>  
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
                        <tr>
                            <td><?php echo $serial_no++; ?></td>
                            <td><?php $empname=$this->sismodel->get_listspfic1('employee_master','emp_name','emp_id',$record->uit_staffname)->emp_name;
                                      $pfno=$this->sismodel->get_listspfic1('employee_master','emp_code','emp_id',$record->uit_staffname)->emp_code;
                                      echo $empname."(" . $pfno .")"; 
                                ?>
                            </td>
                            <td><?php echo date('d-m-Y H:i:s',strtotime($record->uit_dateofrelief));?></td>
                            <td><?php echo date('d-m-Y H:i:s',strtotime($record->uit_dateofjoining));?></td>
                            <td><?php echo $this->lgnmodel->get_listspfic1('authorities','name','id' ,$record->uit_uoc_to)->name; ?></td>
                            <td><?php echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$record->uit_dept_to)->dept_name; ?></td>
                            <td><?php echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$record->uit_desig_to)->desig_name; ?></td>
                            <td><?php echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$record->uit_post_to)->desig_name; ?></td>
                            <td> <?php echo anchor("staffmgmt/transferordercopy/{$record->uit_id}","View Transfer order copy",array('title' => 'View Transfer order' , 'class' => 'red-link')); ?></td>
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
        </div><!------scroller div------>
        </table>
    </body> 
 <p> &nbsp; </p>   
        <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>        
