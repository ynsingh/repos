
<!--@name viewscprgseat.php  @author Manorama Pal(palseema30@gmail.com) -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
    <head>    
        <?php $this->load->view('template/header'); ?>
            <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
        <?php $this->load->view('template/menu');?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">    
       <!-- <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/bootstrap.min.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>-->
        
    </head>    
    <body>
        
    
        <!--<//?php
            echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
            echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
            echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
            echo "Map";
            echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo "|";
            echo anchor('map/mapscprgseat/', "Map Study Center Program Seat ", array('title' => 'Add Detail' , 'class' => 'top_parent'));
            echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo "|";
            echo "<span  style='padding: 8px 8px 8px 20px;'>";
            echo "View Study Center Program Seat";
            echo "</span>";
            echo "</td>";
            echo "</tr>";
            echo "</table>";
        ?>-->
        <!-- </br>   -->
        <center> 
        <table width="70%"> 
           
            <tr><td> 
                 <?php echo anchor('map/mapscprgseat/', "Map Campus Program Seat ", array('title' => 'Add Detail' , 'class' => 'top_parent'));
		 $help_uri = site_url()."/help/helpdoc#ViewStudyCenterandProgramwithSeat";
		 echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:50%\">Click for Help</b></a>";
		 ?>
                <div style="margin-left:2%; width:90%;">
  
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
        </table></center>  
        
      <!-- <div class="panel panel-primary"> -->
            <table cellpadding="16" class="TFtable">
            <thead >
            <tr align="center">
                <th>Sr.No</th>
                <th>Campus Name</th>
                <th>Program Name</th>
                <th>Gender</th>
                <th>Academic Year</th>
                <th>Semester</th>
                <th>Total Seat</th>
                <!--<th>Creator Name</th>
                <th>Creation Date</th>
                <th>Modifier Name</th>
                <th>Modify Date</th>-->
                <th>Action</th>
               <!-- <th></th>-->
            </tr>
           <!-- <tr></tr>-->
        </thead>
        <tbody>
           <?php $serial_no = 1;?>
    <?php if( count($records) ): ?>
	<?php foreach($records as $record){ ?>
	    <tr align="center">
		
	    <td><?php echo $serial_no++; ?></td>   
	    <td><?php echo $this->mapmodel->get_studycenername($record->spsc_sc_code) ; ?></td>
	    <td><?php echo $this->mapmodel->get_Programseat($record->spsc_prg_id); ?></td>
	    <td><?php echo $record->spsc_gender; ?></td>
	    <td><?php echo $record->spsc_acadyear; ?></td>
	    <td><?php echo $record->spsc_sem; ?></td>
	    <td><?php echo $record->spsc_totalseat; ?></td>
	    <!-- <td><//?php echo $record->spsc_creatorid; ?></td>
	    <td><//?php echo $record->spsc_createdate; ?></td>
	    <td><//?php echo $record->spsc_modifierid; ?></td>
	    <td><//?php echo $record->psc_modifydate; ?></td> -->
	    <td> <?php  //echo anchor("map/deletescprgseat/{$record->spsc_id}","Delete",array('title' => 'Details' , 'class' => 'red-link' ,'onclick' => "return confirm('Are you sure you want to delete this record')")); ?>&nbsp;
	    &nbsp;<?php  echo anchor("map/editscprgseat/{$record->spsc_id}","Edit",array('title' => 'Details' , 'class' => 'red-link')); ?></td>
	     </tr>
	<?php }; ?>
    <?php else : ?>  
	     <tr>
	  <td colspan= "10" align="center"> No Records found...!</td>
	     </tr>
    <?php endif;?> 
                  
                    
            
        </tbody>
        
    </table> 
  <!-- </div>  -->     
    </body>  
   <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

