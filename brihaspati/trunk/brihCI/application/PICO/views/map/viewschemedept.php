<!--@name viewschemedept.php  @author Rekha pal(rekha20july@gmail.com) -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
    <head>    
        <?php $this->load->view('template/header'); ?>
       
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
         <table style="margin-left:2%;width:100%"> 
          <tr><td> 
               <?php echo anchor('map/schemedept/', "Map Scheme with Department List ", array('title' => 'Add Detail' , 'class' => 'top_parent'));?>
               <?php
                 $help_uri = site_url()."/help/helpdoc#EmailSetting";
                 echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:70%\">Click for Help</b></a>";
                 ?>

               <div  style="margin-left:2%; width:90%;" >
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
            <table cellpadding="16" style="margin-left:2%;" class="TFtable">
            <thead >
            <tr align="center">
                <th>Sr.No</th>
                <th>Campus Name</th>
                <th>Department Name</th>
                <th>Scheme Name</th>
                <th>Action</th>
            </tr>
            <tr></tr>
            <tr></tr>
        </thead>
        <tbody>
                <?php $serial_no = 1;?>
                <?php foreach($this->result as $row){ ?>
                    <tr align="center">
                    <td><?php echo $serial_no++; ?></td>   
                    
                      <td><?php echo $this->commodel->get_listspfic1('study_center','sc_name', 'sc_id',$row->msd_scid)->sc_name;?></td>
                      <td><?php echo $this->commodel->get_listspfic1('Department','dept_name', 'dept_id',$row->msd_deptid)->dept_name;?></td>
                      <td><?php echo $this->sismodel->get_listspfic1('scheme_department','sd_name', 'sd_id',$row->msd_schmid)->sd_name;?></td>
                    <td><?php echo anchor('map/editschemedept/' . $row->msd_id , "Edit", array('title' => 'Edit Details' , 'class' => 'red-link')); ?>
               </td>
               </tr>
          <?php } ?>
        </tbody>
    </table>
  </body>
 <div align="center"> <?php $this->load->view('template/footer');?></div>
</html>

