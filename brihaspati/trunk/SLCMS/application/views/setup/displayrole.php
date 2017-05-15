<!--@name displayrole.php
  @author kishore kr shukla (kishore.shukla@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
  <head>
    <?php $this->load->view('template/header'); ?>
    <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
    <?php $this->load->view('template/menu');?>
  </head>
 <body>
    <!--<//?php
        echo "<table border=\"0\" align=\"left\" style=\"color: black;  border-collapse:collapse; border:1px;\">";
        echo "<tr style=\"text-align:left; \">";
        echo "<td style=\"padding: 8px 8px 8px 20px; decoration:none;\">";
        echo anchor('setup/role/', "Add Role", array('title' => 'Add Detail'));
        echo "</td>";
        echo "</tr>";
        echo "</table>";
        ?>--!>
      <table>
            <tr colspan="2"><td>
                <div align="left" style="margin-left:30px;">
                <?php echo anchor('setup/role/', "Add Role", array('title' => 'Add Detail','class' =>'top_parent'));?>
                <div  style="width:2000px;">
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

<table>
<tr>
<div align="left" style="margin-left:40px;">

   <?php
        echo "<table>";
        echo "<tr valign=\"top\">";
        echo "<td>";
        echo "<table border=0 cellpadding=15 style=\"padding: 8px 8px 8px 20px;\">";
        echo "<thead><tralign=\"left\"><th>Sr.No</th><th>Role Name</th><th>Role Description</th><th>Action</th><th></tr></thead>";
        $count =0;
        //foreach ($query->result() as $row)
        foreach ($this->result as $row)
        {  
         ?>
             <tr>
            <td> <?php echo ++$count; ?> </td> 
            <td> <?php echo $row->role_name ?></td>
            <td> <?php echo $row->role_desc ?></td>
           <?php
            echo "<td>" . anchor('setup/delete_role/' . $row->role_id , "Delete", array('title' => 'Edit Details' , 'class' => 'red-link','onclick' => "return confirm('Are you sure you want to delete this record')")) . " ";
            echo "<td>" . anchor('setup/editrole/' . $row->role_id , "Edit", array('title' => 'Details' , 'class' => 'red-link')) . " ";
            echo "</br>";
            echo "</tr>";
          
        }
        echo "</table>";
        echo "</td>";
        echo "</tr>";
        echo "</table>";
        ?>
</div>
</tr>
</table>
</body>
<div align="center">  <?php $this->load->view('template/footer');?></div>
</html>





