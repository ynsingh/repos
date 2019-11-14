
<!--@filename salformula_list.php  @author Manorama Pal(palseema30@gmail.com) -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Salary Formula</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">   
    </head>
    <body>
            <?php $this->load->view('template/header'); ?>
           
        <table width="100%"><tr colspan="2">
        <?php 
        echo "<td align=\"left\" width=\"33%\">";
         echo anchor('setup3/salaryhead_list/', "View Salary Head" ,array('title' => 'View salary head ' , 'class' => 'top_parent'));
	echo "</td>";
        echo "<td align=\"center\" width=\"34%\">";
        echo "<b>Salary Head Formula List</b>";
        echo "</td>";
        echo "<td align=\"right\" width=\"33%\">";
	$help_uri = site_url()."/help/helpdoc#SalaryHeadList";
//        echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
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
            <thead>
                <tr>
                    <th>Sr.No</th>
                    <th>Head Code</th>
                    <th>Head Name</th>
                    <th>Formula</th>
                    <th>Action</th>
                    
                </tr>
            </thead>
            <tbody>
                <?php $serial_no = 1;?>
              <?php if( count($formulrecord) ):  ?>
                    <?php foreach($formulrecord as $record){ ?>
                        <tr>
                            <td><?php echo $serial_no++; ?></td>
                            <td><?php echo $record->sh_code; ?></td>
                            <td><?php
                            echo $record->sh_name;
                            if(!empty($record->sh_tnt)){
                                echo " ( ".$record->sh_tnt." ) ";
                            }
                                    ; ?></td>
                            <td><?php
                                if(!empty($record->sf_formula)){
                                    echo $record->sf_formula; 
                                }    
                                else{
                                   echo anchor("setup3/add_salaryformula/{$record->sh_id}","Add Formula",array('title' => 'Add Formula' , 'class' => 'top_parent'));
                                }
                                ?>
                            </td>    
                            <td> <?php 
                                    if(!empty($record->sf_formula)){
                                        echo anchor("setup3/edit_salaryformula/{$record->sf_id}","Edit",array('title' => 'Edit Details' ,'class' => 'top_parent')); 
                                    }    
                                ?><br/>
                            </td>
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

