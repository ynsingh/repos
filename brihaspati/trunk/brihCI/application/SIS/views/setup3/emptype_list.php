<!--@filename emptype_list.php  @author Manorama Pal(palseema30@gmail.com) -->

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
	echo anchor('setup3/employeetype/', "Add Employee Type" ,array('title' => 'View Employee Type ' , 'class' => 'top_parent'));
        echo "</td>";
        echo "<td align=\"center\" width=\"34%\">";
        echo "<b>Employee Type List</b>";
        echo "</td>";
        echo "<td align=\"right\" width=\"33%\">";
	$help_uri = site_url()."/help/helpdoc#SalaryHeadList";
        echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
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
</tr>
        </table>
        <div class="scroller_sub_page">
        <table class="TFtable" >
            <form id="myform" action="<?php echo site_url('setup3/salaryhead_list/');?>" method="POST" enctype="multipart/form-data">
            <thead>
                <tr>
                    <th>Sr.No</th>
                    <th>Employee Type Code</th>
                    <th>Employee Type</th>
                    <th>Short Name</th>
                    <th>PF Applies</th>
                    <th>Max PF Limit</th>
                    <th>Action</th>
                    
                </tr>
            </thead>
            <tbody>
                <?php $serial_no = 1;?>
              <?php if( count($emptype_record) ):  ?>
                    <?php foreach($emptype_record as $record){ ?>
                        <tr>
                            <td><?php echo $serial_no++; ?></td>
                            
                            <td>
                                <?php echo $record->empt_code;?>
                            </td>    
                            <td><?php echo $record->empt_name; ?></td>
                            <td><?php echo $record->empt_shortname; ?></td>
                            <td><?php
                                if($record->empt_pfapplies == "Y"){
                                    echo "YES";
                                }
                                else{
                                    echo "NO";
                                }
                                ?>
                            </td>
                            <td><?php echo $record->empt_maxpflimit; ?></td>
                            <td> <?php echo anchor("setup3/edit_employeetype/{$record->empt_id}","Edit",array('title' => 'Edit Details' , 'class' => 'red-link')); ?><br/>
                            
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

