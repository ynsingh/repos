<!--@name viewuserrole.php  @author kishore kr Shukla(kishore.shukla@gmail.com) -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
    <head>    
        <?php $this->load->view('template/header'); ?>
       
        <?php //$this->load->view('template/menu');?>
         <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
    </head>    
    <body>
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->
         <table style="width:100%"> 
          <tr><td> 
               <?php
                   echo "<td align=\"left\" width=\"33%\">"; 
                   echo anchor('map/userroletype/', "Map with User Role List ", array('title' => 'Add Detail' , 'class' => 'top_parent'));
                   echo "</td>";
                   echo "<td align=\"center\" width=\"34%\">";
                   echo "<b>User Role List Details</b>";
                   echo "</td>";
                   echo "<td align=\"right\" width=\"33%\">";               
                $help_uri = site_url()."/help/helpdoc#EmailSetting";
                echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                echo "</td>";
                ?>
               </div>
               <div>
               <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                <?php if(isset($_SESSION['success'])){?>
                   <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                <?php
                };
                ?>
                <?php if(isset($_SESSION['err_message'])){?>
                   <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                <?php
                };
                ?>        
        	</div>
        </td></tr>  
        </table>  
      <!-- <div class="panel panel-primary"> -->
	<div class="scroller_sub_page">
            <table cellpadding="0" class="TFtable">
            <thead >
            <tr>
                <th>Sr.No</th>
                <th>User Name</th>
                <th>User Type</th>
                <th>Role Name</th>
                <th>Department Name</th>
                <th>Campus Name</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
		
                <?php $serial_no = 1;?>
                <?php foreach($this->result as $record){ ?>
                    <tr>
                    <td><?php echo $serial_no++; ?></td>   
                    <td><?php echo $this->loginmodel->get_listspfic1('edrpuser','username','id',$record->userid)->username; ?></td>
                    <td><?php echo $record->usertype; ?></td>
                    <td><?php echo $this->commodel->get_listspfic1('role','role_name', 'role_id', $record->roleid)->role_name; ?></td>
		    <td><?php 
				if(!empty( $this->commodel->get_listspfic1('Department','dept_name', 'dept_id',$record->deptid))){
					echo $this->commodel->get_listspfic1('Department','dept_name', 'dept_id',$record->deptid)->dept_name;
	    			}
		?></td>
			<td><?php 
				if(!empty($this->commodel->get_listspfic1('study_center','sc_name', 'sc_id',$record->scid))){
					echo $this->commodel->get_listspfic1('study_center','sc_name', 'sc_id',$record->scid)->sc_name; 
				}
			?></td>
                    <?php if($record->userid==1 && $record->roleid ==1): ?> 
                    <td> <?php  //echo anchor(current_url().'/#', "Delete",array('title' => 'Details' , 'class' => 'tag_color')); ?>&nbsp;
                    &nbsp;<?php  //echo anchor(current_url().'/#',"Edit",array('title' => 'Details' , 'class' => 'tag_color')); ?></td>
		    <?php else : ?>	
                    <td> <?php  //echo anchor("map/deleteuserrole/{$record->id}", "Delete",array('title' => 'Details' , 'class' => 'red-link' ,'onclick' => "return confirm('Are you sure you want to delete this record')")); ?>&nbsp;
                    &nbsp;<?php  echo anchor("map/edituserrole/{$record->id}","Edit",array('title' => 'Details' , 'class' => 'red-link')); ?></td>
			<?php endif ;?>
                     </tr>
                <?php }; ?>
         </tbody>
        </table> 
</div><!------scroller div------>
  <!-- </div>  -->     
    </body>  
   <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

