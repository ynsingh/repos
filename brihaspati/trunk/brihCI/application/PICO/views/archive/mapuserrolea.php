<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name  mapuserrolea.php
  @author Rekha Devi Pal (rekha20july@gmail.com)
 -->

<html>
    <head>    
        <?php $this->load->view('template/header'); ?>
        
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
    </head>
    <body>

<table width="100%">
            <tr colspan="2"><td>
	   <?php
            echo "<td align=\"center\" width=\"100%\">";
            echo "<b>Map User with Role Archive Details</b>";
            echo "</td>";
            ?>
                <?php echo validation_errors('<div class="isa_warning>','</div>');?>

                <?php if(isset($_SESSION['success'])){?>
                    <div style="margin-left:30px;" class="isa_success"><?php echo $_SESSION['success'];?></div>

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
        <table class="TFtable" >
            <thead>
             <tr>
             <th>Sr.No</th>
                <th>User Name</th>
                <th>User Type</th>
                <th>Role Name</th>
                <th>Department Name</th>
                <th>Campus Name</th>
                <th>Archiver Name</th>
                <th>Archive date</th>
      </thead>

               <tbody>
        <?php $count = 0;
        if( count($this->result) ) {
              foreach ($this->result as $row)
              {
         ?>
           <tr>
                <td><?php echo ++$count; ?> </td>

                    <td><?php echo $this->logmodel->get_listspfic1('edrpuser','username','id',$row->urta_userid)->username; ?></td>
                    <td><?php echo $row->urta_usertypea; ?></td>
                    <td><?php echo $this->common_model->get_listspfic1('role','role_name', 'role_id', $row->urta_roleida)->role_name; ?></td>
                    <td><?php echo $this->common_model->get_listspfic1('Department','dept_name', 'dept_id',$row->urta_deptida)->dept_name;?></td>
                    <td><?php echo $this->common_model->get_listspfic1('study_center','sc_name', 'sc_id',$row->urta_scida)->sc_name; ?></td>
                    <td> <?php echo $row->creatorid ?></td>
                    <td> <?php echo $row->creatordate ?></td>
</tr>
           <?php }
           }else{
           ?>
           <tr><td colspan= "12" align="center"> No Records found...!</td></tr>
           <?php }?>
     </tbody>
    </table>
   </div><!------scroller div------>
   </body>
<p> &nbsp; </p>
   <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

