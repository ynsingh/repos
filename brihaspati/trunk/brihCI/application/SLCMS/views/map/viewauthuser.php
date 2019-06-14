<!---@name viewauthuser.php @author Neha Khullar (nehukhullar@gmail.com) -->
 
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
   <head>    
        <?php $this->load->view('template/header'); ?>
        <?php //$this->load->view('template/menu');?>
         <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
    </head>
    <body>
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->

         <table width=100%">
          <tr><td>
              <?php  
                 echo "<td align=\"left\" width=\"33%\">";  
                 echo anchor('map/authusertype/', "Map Add Authority ", array('title' => 'Add Detail', 'class' =>'top_parent'));
                 echo "<td align=\"center\" width=\"34%\">";
                 echo "<b>Authority Details</b>";
                 echo "</td>";
                 echo "<td align=\"right\" width=\"33%\">";
                $help_uri = site_url()."/help/helpdoc#Authority";
                echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                echo "</td>";
                 ?>
    
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
        <div class="scroller_sub_page">
            <table cellpadding="0" class="TFtable">
            <thead><tr>
                <th>Sr.No</th>
                <th>Authority Name</th>
                <th>User Name</th>
                <th>From Date</th>
                <th>Till Date</th>
		<th>Authority Type</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
<?php
                     $count =0;
                     if( count($this->authuser) ):
                     foreach($this->authuser as $row){ ?>
                     <tr>
                     <td> <?php echo ++$count; ?> </td>
		       <td><?php echo $this->loginmodel->get_listspfic1('authorities','name','id',$row->authority_id)->name ?></td>
	<!--	<td><?php echo $this->loginmodel->get_listspfic1('edrpuser','username','id',$row->user_id)->username; ?></td>-->
                <?php  echo "<td>";
               if(!empty($this->loginmodel->get_listspfic1('userprofile', 'firstname', 'userid', $row->user_id))&&!empty($this->loginmodel->get_listspfic1('userprofile', 'lastname', 'userid', $row->user_id))){
                echo $this->loginmodel->get_listspfic1('userprofile', 'firstname', 'userid', $row->user_id)->firstname .' '.$this->loginmodel->get_listspfic1('userprofile', 'lastname', 'userid', $row->user_id)->lastname;}?>
                                                                                                                                        
                </td>                           
                <td> <?php echo $row->map_date ?></td>
                    	<td> <?php echo $row->till_date ?></td>
                    	<td> <?php echo $row->authority_type ?></td>
		        <td> <?php echo anchor ("map/editauthuser/{$row->id}","Edit",array('title' => 'Edit Details' , 'class' => 'red-link')); ?></td>
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
