<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name displayfees.php 
  @author Vijay (vijay.pal428@gmail.com)
 -->
<html>
<title>View Fees</title>
    <head>    
        <?php $this->load->view('template/header'); ?>
        <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
        <?php $this->load->view('template/menu');?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
    </head>
    <body>
        <table width="100%" style="margin-left:2%;">
            <tr><td>
	     <?php
            	 echo anchor('setup/fees/', "Add Program Fees with Head ",array('title' => 'fees Configuration Detail ' , 'class' => 'top_parent'));
		 $help_uri = site_url()."/help/helpdoc#ViewProgramFeeswithHead";
		 echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:67%\">Click for Help</b></a>";
            	?>
              </td></tr>
             <tr><td>
             <div  style="margin-left:2%;width:90%;">

                <?php echo validation_errors('<div class="isa_warning>','</div>');?>

                <?php if(isset($_SESSION['success'])){?>
                    <div style="margin-left:0.2%;" class="isa_success"><?php echo $_SESSION['success'];?></div>

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
        <table cellpadding="16" style="margin-left:2%;" class="TFtable" >
            <thead>
                <tr align="center">
		<th>Sr.No</th>
		<th>Program Name</th>
		<th>Academic Year</th>
		<th>Semester</th>
		<th>Category</th>
		<th>Gender</th>
		<th>Head</th>
		<th>Amount</th>
		<th>Description</th>
		<th>Action</th>
		 <!-- <th></th>-->
                </tr>
            </thead>
            <tbody>
                    <?php 
		     $count =0;
              	     if( count($this->fmresult) ): 
                     foreach($this->fmresult as $row){ ?>
                     <tr align="center">
		     <td> <?php echo ++$count; ?> </td>
			<?php  
			echo "<td>";
			echo  $row->fm_programid;
			//echo  $this->common_model->get_listspfic1('program','prg_name','prg_id',$row->fm_programid)->prg_name;
			echo "</td>";
			?>
			 <td> <?php echo $row->fm_acadyear ?></td>
            		 <td> <?php echo $row->fm_semester ?></td>
                         <?php  echo "<td>" . $this->common_model->get_listspfic1('category','cat_name','cat_id',$row->fm_category)->cat_name  . "</td>";?>
            		 <td> <?php echo $row->fm_gender ?></td>
           		 <td> <?php echo $row->fm_head ?></td>
            		 <td> <?php echo $row->fm_amount ?></td>
         		 <td> <?php echo $row->fm_desc ?></td>

                            <td> <?php // echo anchor("setup/delete_fees/{$row->fm_id}","Delete",array('title' => 'Delete' , 'class' => 'red-link' ,'onclick' => "return confirm('Are you sure you want to delete this record')")); ?>&nbsp;
                            &nbsp;<?php  echo anchor("setup/editfees/{$row->fm_id}","Edit",array('title' => 'Edit Details' , 'class' => 'red-link')); ?></td>
                        </tr>
                    <?php }; ?>
                <?php else : ?>
                    <td colspan= "10" align="center"> No Records found...!</td>
                <?php endif;?>

            </tbody>
        </table>
    </body>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

