<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name displaycontact.php 
  @author Rekha Devi Pal (rekha20july@gmail.com)
 -->
<html>
<title>View Contact Us</title>
    <head>    
        <?php $this->load->view('template/header'); ?>
        <?php $this->load->view('template/menu');?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
    </head>
    <body>
<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
            
            <!--table width="100%">

	     <?php
                 echo "<td align=\"left\" width=\"33%\">";
            	 echo anchor('setup/contact/', "Add Contact Us ",array('title' => 'Contact Us Configuration Detail ' , 'class' => 'top_parent'));
                 echo "</td>";
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Contact Us Details</b>";
                    echo "</td>";
                    echo "<td align=\"right\" width=\"33%\">";
                     // $help_uri = site_url()."/help/helpdoc#ViewContactUs";
                   // echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                    echo "</td>";

            ?>


              </td></tr>
             <tr><td>
             <div>

                <?php echo validation_errors('<div class="isa_warning>','</div>');?>

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
        </table-->


           <table width="100%;">
            <tr>
            <?php
                    echo "<td align=\"left\" width=\"33%\">";
                    echo anchor('setup/contact/', "Add Contact Us" ,array('title' => ' Add Contact Us Detail ' , 'class' => 'top_parent'));
                    echo "</td>";
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Contact Us Details</b>";
                    echo "</td>";
                    echo "<td align=\"right\" width=\"33%\">";
                    $help_uri = site_url()."/help/helpdoc#ViewContactUs";
                    //echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                    echo "</td>";
            ?>
            <div>

                <?php echo validation_errors('<div class="isa_warning>','</div>');?>
    
                <?php if(isset($_SESSION['success'])){?>
                    <div  class="isa_success"><?php echo $_SESSION['success'];?></div>
    
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
<div class="scroller_sub_page">


        <table class="TFtable">
            <thead><tr>
                    <th>Sr.No</th>
                    <th>Name</th>
                    <th>Email Id</th>
                    <th>Mobile No</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                    <?php 
		     $count =0;
              	     if( count($this->result) ): 
                     foreach($this->result as $row){ ?>
                     <tr>
		     <td> <?php echo ++$count; ?> </td>
                            <td><?php echo $row->ascu_name; ?></td>
                            <td><?php echo $row->ascu_emailid; ?></td>
                            <td><?php echo $row->ascu_phoneno; ?></td>

                            <td> <?php // echo anchor("setup/delete_fees/{$row->fm_id}","Delete",array('title' => 'Delete' , 'class' => 'red-link' ,'onclick' => "return confirm('Are you sure you want to delete this record')")); ?>&nbsp;
                            &nbsp;<?php  echo anchor("setup/editcontact/{$row->ascu_id}","Edit",array('title' => 'Edit Details' , 'class' => 'red-link')); ?></td>
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

