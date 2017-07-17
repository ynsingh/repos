<!--@name viewuserrole.php  @author kishore kr Shukla(kishore.shukla@gmail.com) -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
    <head>    
        <?php $this->load->view('template/header'); ?>
        <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
        <?php $this->load->view('template/menu');?>
         <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <style>
            thead th{
               
                background-color: #DCDCDC;
              }
		.tag_color{
			color:red;
		}
		
       </style>
    </head>    
    <body>
         <table style="margin-left:30px;"> 
          <tr colspan="2"><td> 
               <?php echo anchor('map/userroletype/', "Map with User Role List ", array('title' => 'Add Detail' , 'class' => 'top_parent'));?>
               <div  style="width:1700px;">
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
        <br/>
      <!-- <div class="panel panel-primary"> -->
            <table cellpadding="16" style="margin-left:30px;" class="TFtable">
            <thead >
            <tr align="center">
                <th>Sr.No</th>
                <th>User Name</th>
                <th>User Type</th>
                <th>Role Name</th>
                <th>Department Name</th>
                <th>Campus Name</th>
                <th>Action</th>
            </tr>
            <tr></tr>
            <tr></tr>
        </thead>
        <tbody>
                <?php $serial_no = 1;?>
                <?php foreach($this->result as $record){ ?>
                    <tr align="center">
                    <td><?php echo $serial_no++; ?></td>   
                    <td><?php echo $this->loginmodel->get_listspfic1('edrpuser','username','id',$record->userid)->username; ?></td>
                    <td><?php echo $record->usertype; ?></td>
                    <td><?php echo $this->commodel->get_listspfic1('role','role_name', 'role_id', $record->roleid)->role_name; ?></td>
                    <td><?php echo $this->commodel->get_listspfic1('Department','dept_name', 'dept_id',$record->deptid)->dept_name;?></td>
                    <td><?php echo $this->commodel->get_listspfic1('study_center','sc_name', 'sc_id',$record->scid)->sc_name; ?></td>
                     <?php if($record->userid==1 && $record->roleid ==1): ?> 
                    <td> <?php  echo anchor(current_url().'/#', "Delete",array('title' => 'Details' , 'class' => 'tag_color')); ?>&nbsp;
                    &nbsp;<?php  echo anchor(current_url().'/#',"Edit",array('title' => 'Details' , 'class' => 'tag_color')); ?></td>
		    <?php else : ?>	
                    <td> <?php  echo anchor("map/deleteuserrole/{$record->id}", "Delete",array('title' => 'Details' , 'class' => 'red-link' ,'onclick' => "return confirm('Are you sure you want to delete this record')")); ?>&nbsp;
                    &nbsp;<?php  echo anchor("map/edituserrole/{$record->id}","Edit",array('title' => 'Details' , 'class' => 'red-link')); ?></td>
			<?php endif ;?>
                     </tr>
                <?php }; ?>
         </tbody>
        </table> 
  <!-- </div>  -->     
    </body>  
   <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

