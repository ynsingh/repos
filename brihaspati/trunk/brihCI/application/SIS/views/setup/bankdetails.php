<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name bankdetails.php
  @author Neha Khullar(nehukhullar@gmail.com) 
-->


<html>
<title> Bank Details </title>
 <head>
    <?php $this->load->view('template/header'); ?>
    <h1>Welcome <?= $this->session->userdata('username') ?> </h1>
    <?php $this->load->view('template/menu');?>
    <link rel="stylesheet" type="text/css" href="<?php echo base_url();?>assets/css/tablestyle.css">

<?php
echo "</head>";
echo "<body>";
/*    echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
    echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
    echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
    echo "Map";
    echo "<span  style=\"padding: 8px 8px 8px 20px;\"> ";
    echo "|";
    echo anchor('setup/subject/', "Add Subject", array('title' => 'Add Detail' , 'class' => 'top_parent'));
    echo "<span  style=\"padding: 8px 8px 8px 20px;\"> ";
    echo "|";
    echo "<span  style=\"padding: 8px 8px 8px 20px;\">";
    echo "Subject List";
    echo "</span>";
    echo "</td>";
    echo "</tr>";
    echo "</table>";
    echo"</br>";
*/
?>
<br>
<div align="left">
<table style="margin-left:10px;">
<tr><td>
<?php echo anchor('setup/addbank', "Add Bank" ,array('title' => 'Add bankdetails' , 'class' => 'top_parent'));
$help_uri = site_url()."/help/helpdoc#Viewbank_profile";
echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:77%\">Click for Help</b></a>";
?>
</td></tr>
</table>
</div>

    <table>
    <tr colspan=2><td>
    <div  style="margin-left:1px;">

    <?php echo validation_errors('<div style="margin-left:30px;" class="isa_warning>','</div>');?>
    <?php echo form_error('<div style="margin-left:30px;" class="isa_error">','</div>');?>
     <?php if(isset($_SESSION['success'])){?>
       <div style="margin-left:30px" class="isa_success"><?php echo str_replace("%20"," ",$_SESSION['success']);?></div>
<?php    }
    if(isset($_SESSION['error']))
    {
?>        <div style="margin-left:30px"; class="isa_success">"<?php echo str_replace("%20"," ",$_SESSION['error']);?> </div>
<?php
}
echo "</div>";
echo "</td></tr>";
echo "</table>";
echo "<div style=\"margin-left:0px;\">";

/* form data */
echo "<table  cellpadding=13 style=\"margin-left:2%;\" class=\"TFtable\">";
echo "<thead><tr align=\"center\"><th>Sr. No</th><th>Bank Name</th><th>Bank Address</th><th>Bank Branch</th><th>Account No</th><th>Account Name</th><th>Account Type</th><th>IFSC Code</th><th>PAN No</th><th>TAN No</th><th>GST No</th><th>Action</th></thead>";

$srno = 0;
foreach ($this->result as $row)
    {
     
        $srno = $srno + 1;

        {
        ?>
 <tr align="center">
        <td><?php echo $srno;?></td>
        <td><?php echo $row->bank_name;?></td>
        <td><?php echo $row->bank_address;?></td>
        <td><?php echo $row->branch_name;?></td>
        <td><?php echo $row->account_number;?></td>
        <td><?php echo $row->account_name ;?></td>
        <td><?php echo $row->account_type;?></td>
        <td><?php echo $row->ifsc_code ;?></td>
        <td><?php echo $row->pan_number;?></td>
        <td><?php echo $row->tan_number;?></td>
        <td><?php echo $row->gst_number;?></td>
        <td> <?php echo anchor ('setup/editbankdetails/' . $row->id, "Edit", array('title' => 'Edit Bankdetails', 'class' => 'red-link'));?>&nbsp;
<?php

}
	  } 
    echo "</br>";
    echo "</tr>";

echo "</table>";
echo "<td>";
echo "</tr>";
echo "</table>";
  ?>
</div>
</tr>
</table>
</body>
<div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

