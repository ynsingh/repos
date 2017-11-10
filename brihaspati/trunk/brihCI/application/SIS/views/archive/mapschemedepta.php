<!--@name mapschemedepta.php  @author Rekha pal(rekha20july@gmail.com) -->

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
    <center>
         <table style="width:70%"> 
          <tr><td> 
               <?php echo anchor('map/schemedept/', "Map Scheme with Department List ", array('title' => 'Add Detail' , 'class' => 'top_parent'));?>
               <?php
                 $help_uri = site_url()."/help/helpdoc#EmailSetting";
                 echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:45%\">Click for Help</b></a>";
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
        </table> </center> 
        <br/>
      <!-- <div class="panel panel-primary"> -->
            <table cellpadding="16"  class="TFtable">
            <thead >
            <tr align="center">
                <th>Sr.No</th>
                <th>Campus Name</th>
                <th>Department Name</th>
                <th>Scheme Name</th>
                <th>Archiver's Name</th>
                <th>Archiver's Date</th>
            </tr>
            <tr></tr>
            <tr></tr>
        </thead>
        <tbody>
                <?php $serial_no = 1;?>
                <?php foreach($this->result as $row){ ?>
                    <tr align="center">
                    <td><?php echo $serial_no++; ?></td>   
                    
                      <td><?php echo $this->common_model->get_listspfic1('study_center','sc_name', 'sc_id',$row->msda_scid)->sc_name;?></td>
                      <td><?php echo $this->common_model->get_listspfic1('Department','dept_name', 'dept_id',$row->msda_deptid)->dept_name;?></td>
                      <td><?php echo $this->sismodel->get_listspfic1('scheme_department','sd_name', 'sd_id',$row->msda_schmid)->sd_name;?></td>
                      <td> <?php echo $row->msda_archuserid ?></td>
                    <td> <?php echo $row->msda_archdate ?></td>
               </tr>
          <?php } ?>
        </tbody>
    </table>
  </body>
 <div align="center"> <?php $this->load->view('template/footer');?></div>
</html>

