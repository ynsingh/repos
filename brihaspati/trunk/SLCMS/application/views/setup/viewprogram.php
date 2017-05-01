<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<?php echo base_url();?>assets/css/message.css">
</head>
<body>
<div>
<?php $this->load->view('template/header.php');?>
<h1>Welcome <?= $this->session->userdata('username')?> </h1>
<?php $this->load->view('template/menu.php');?>
</div>
<?php
    echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
    echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
    echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
    echo "Setup";
    echo "<span  style='padding: 8px 8px 8px 20px;'> ";
    echo "|";
    echo anchor('setup/program/', "Add Program", array('title' => 'Add Detail' , 'class' => 'top_parent'));
    echo "<span  style='padding: 8px 8px 8px 20px;'> ";
    echo "|";
    echo "<span  style='padding: 8px 8px 8px 20px;'>";
    echo  "View Program";
    //echo "View Programs";
    echo "</span>";
    echo "</td>";
    echo "</tr>";
    echo "</table>";
?>
        <table> 
            <tr colspan="2"><td>    
                <div  style="margin-left:30px;width:1700px;">
                <?php echo validation_errors('<div style="margin-left:30px;" class="isa_warning>','</div>');?>
                <?php if(isset($_SESSION['success'])){?>
                    <div style="margin-left:30px;" class="isa_success"><?php echo $_SESSION['success'];?></div>
                <?php
                };
                ?>
            </div>
        </td></tr>   
        </table>   
    <div align="left" style="margin-left:30px;">
    <table border=0 cellpadding=5 style=\"padding: 8px 8px 8px 20px;\>
    <!--<table>-->
    <tr>
    <td><strong>Program Id</strong></td><td><strong>Program Category</strong></td><td><strong>Program Name</strong></td><td><strong>Program Branch</strong></td> <td><strong>Seat Available</strong></td><td><strong>Program Code</strong></td><td><strong>Program Short</strong></td><td><strong>Program Description</strong></td><td><strong>Program Min Time</strong></td><td><strong>Program Max Time</strong></td><td><strong>Creator Name</strong></td><td><strong>Creatoion Date</strong></td><td><strong>Edit/Delete</strong></td>
    </tr> 

    <?php  
         foreach($prgres as $row)  
         {?>
            <tr align='center'>
            <td><?php echo $row->prg_id;?></td>
            <td><?php echo $row->prg_category;?></td>
            <td><?php echo $row->prg_name;?></td>
            <td><?php echo $row->prg_branch;?></td>
            <td><?php echo $row->prg_seat;?></td>
            <td><?php echo $row->prg_code;?></td>
            <td><?php echo $row->prg_short;?></td>
            <td><?php echo $row->prg_desc;?></td>
            <td><?php echo $row->prg_mintime;?></td>
            <td><?php echo $row->prg_maxtime;?></td>
            <td><?php echo $row->creatorid;?></td>
            <td><?php echo $row->createdate;?></td>
            <td><?php echo anchor('setup/editprogram/' . $row->prg_id , "Edit", array('title' => 'Edit Program', 'class' => 'red-link'));echo " | "?> <?php echo anchor('setup/deleteprogram/' . $row->prg_id , "Delete", array('title' => 'Delete Program', 'class' => 'red-link'));?>
            </td>
    <?php        
         }?>  
    </tr>     
    </table>
    </div>    

<div>
<?php $this->load->view('template/footer.php');?>
</div>
</body>
</html>

