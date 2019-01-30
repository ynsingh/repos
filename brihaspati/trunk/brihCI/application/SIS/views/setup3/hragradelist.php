
<!--@filename hragradelist.php  @author Manorama Pal(palseema30@gmail.com) -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Welcome to TANUVAS</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">   
    </head>
    <body>
            <?php $this->load->view('template/header'); ?>
           
        <table width="100%"><tr colspan="2">
            <?php 
            echo "<td align=\"left\" width=\"33%\">";
            echo anchor('setup3/add_hragrade/', "Add HRA Grade" ,array('title' => 'View HRA Grade ' , 'class' => 'top_parent'));
            echo "</td>";
            echo "<td align=\"center\" width=\"34%\">";
            echo "<b>HRA Grade List</b>";
            echo "</td>";
            echo "<td align=\"right\" width=\"33%\">";
            $help_uri = site_url()."/help/helpdoc#SalaryHeadList";
//            echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
            echo "</td>";
            ?>
            <div>     
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                <?php echo form_error('<div class="isa_error">','</div>');?>
                <?php
                if((isset($_SESSION['success'])) && ($_SESSION['success'])!=''){
                    echo "<div  class=\"isa_success\">";
                    echo $_SESSION['success'];
                    echo "</div>";
                }
                if((isset($_SESSION['err_message'])) && (($_SESSION['err_message'])!='')){
                    echo "<div  class=\"isa_error\">";
                    echo $_SESSION['err_message'];
                    echo "</div>";
                }
                ;?>
            </div>
        </tr></table>
        <div class="scroller_sub_page">
        <table class="TFtable" >
            
            <thead>
                <tr>
                    <th>Sr.No</th>
                    <th>Working Type</th>
                    <th>Pay Commission</th>
                    <th>Pay Scale</th>
                    <th>Pay Range</th>
                    <th>Grade</th>
                    <th>Amount</th>
                    <th>Action</th>
                    
                </tr>
            </thead>
            <tbody>
                <?php $serial_no = 1;?>
              <?php if( count($hragrade) ):  ?>
                    <?php foreach($hragrade as $record){ ?>
                        <tr>
                            <td><?php echo $serial_no++; ?></td>
                            <td><?php echo $record->hg_workingtype;?></td>
                            <td><?php echo $record->hg_paycomm;?></td>
                                <?php  $pname=$this->sismodel->get_listspfic1('salary_grade_master','sgm_name','sgm_id',$record->hg_payscaleid)->sgm_name;
                                    $min=$this->sismodel->get_listspfic1('salary_grade_master','sgm_min','sgm_id',$record->hg_payscaleid)->sgm_min;
                                    $max=$this->sismodel->get_listspfic1('salary_grade_master','sgm_max','sgm_id',$record->hg_payscaleid)->sgm_max;
                                    $gp=$this->sismodel->get_listspfic1('salary_grade_master','sgm_gradepay','sgm_id',$record->hg_payscaleid)->sgm_gradepay;
                                    $fullstr=$pname."( ".$min." - ".$max." )".$gp;
                
                                    $hragradename=$this->sismodel->get_listspfic1('hra_grade_city','hgc_gradename','hgc_id',$record->hg_gradeid)->hgc_gradename;
                                ?>
                            <td><?php echo $fullstr;?></td>    
                            <td><?php echo $record->hg_payrange; ?></td>
                            <td><?php echo $hragradename; ?></td>
                            <td><?php echo $record->hg_amount; ?></td>
                            <td> <?php // echo anchor("setup3/edit_hragrade/{$record->hg_id}","Edit",array('title' => 'Edit Details' , 'class' => 'red-link')); ?>
                                <a href='<?php echo site_url()."/setup3/edit_hragrade/".$record->hg_id;?>' title="Edit Details"><img src="<?php echo base_url('assets/sis/images/edit.png');?>"></a>&nbsp; 
                                <a href='<?php echo site_url()."/setup3/delete_hragrade/".$record->hg_id;?>' title="Delete" onclick="return confirm('Are you sure you want to delete this record?');"><img src="<?php echo base_url('assets/sis/images/delete3.jpg');?>"></a> 
                            </td>    
                            
                        </tr>
                        
                    <?php }; ?>
                    
                <?php else : ?>
                    <td colspan= "13" align="center"> No Records found...!</td>
                <?php endif;?>
               
		</tbody>
               
        </table>
        </div>
        <p> &nbsp; </p>
        <div align="center">  <?php $this->load->view('template/footer');?></div>
        
    </body>    
</html>   

