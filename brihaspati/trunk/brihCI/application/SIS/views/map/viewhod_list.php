<!--@filename viewhod_list.php  @author Manorama Pal(palseema30@gmail.com) -->

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
	echo anchor('map/sethod/', "Add HOD" ,array('title' => ' Add HOD ' , 'class' => 'top_parent'));
        echo "</td>";
        echo "<td align=\"center\" width=\"34%\">";
        echo "<b>HOD List</b>";
        echo "</td>";
        echo "<td align=\"right\" width=\"33%\">";
	$help_uri = site_url()."/help/helpdoc#HODList";
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
            <form id="myform" action="<?php echo site_url('map/hod_list/');?>" method="POST" enctype="multipart/form-data">
            <thead>
                <tr>
                    <th>Sr.No</th>
                    <th>Hod Name</th>
                    <th>Campus Name</th>
                    <th>Department Name</th>
                    <th>Status</th>
                    <th>Date From</th>
                    <th>Date To</th>
                    <th>Action</th>
                    
                </tr>
            </thead>
            <tbody>
                <?php $serial_no = 1;
		$uoprid =0;
		
               if( count($records) ):  
                    foreach($records as $record){ 
			if($record->hl_uopid != $uoprid) {
                                $uoprid = $record->hl_uopid;
                                echo "<tr>";
                                echo "<td colspan=8>";
                                echo "<b>";
                                echo $this->loginmodel->get_listspfic1('authorities','name','priority',$record->hl_uopid)->name;
                                echo " ( ";
                                echo $this->loginmodel->get_listspfic1('authorities','code','priority',$record->hl_uopid)->code;
                                echo " ) ";
                                echo "</b>";
                                echo "</td>";
                                echo "</tr>";
                        }

 ?>                       <tr>
                            <td><?php echo $serial_no++; ?></td>
                            
                            <td>
                                <?php if(!empty($record->hl_empcode)):?>
                                <?php 
                                     $pfno=$this->sismodel->get_listspfic1('employee_master','emp_name','emp_code',$record->hl_empcode);
					if(!empty($pfno)){
                                   		echo  $pfno->emp_name;
					}
                                
                                ?>
                                <?php else : ?>
                                    <?php echo $emailid=$this->loginmodel->get_listspfic1('edrpuser','username','id',$record->hl_userid)->username;?>
                                <?php endif;?>
                            </td>    
                            <td><?php echo $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$record->hl_scid)->sc_name;?></td>
                            <td><?php echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$record->hl_deptid)->dept_name;?></td>
                            <td><?php echo $record->hl_status;?></td>
                            <td><?php echo $record->hl_datefrom;?></td>
                            <td><?php echo $record->hl_dateto;?></td>
                            <td> <?php echo anchor("map/edithod/{$record->hl_id}","Edit",array('title' => 'Edit Details' , 'class' => 'red-link')); ?><br/>
                            </td>
                            
                        </tr>
                        
                    <?php }; ?>
                    
                <?php else : ?>
                    <td colspan= "13" align="center"> No Records found...!</td>
                <?php endif;?>
               
		</tbody>
                </form>
        </table>
        </div><!------scroller div------>
        <p> &nbsp; </p>
        <div align="center">  <?php $this->load->view('template/footer');?></div>
        
    </body>    
</html>   

