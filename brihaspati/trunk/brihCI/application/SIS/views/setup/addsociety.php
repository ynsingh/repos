<!--@author addsociety.php 
    @author Neha Khullar (nehukhullar@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Add Society</title>

 <head>
     <?php $this->load->view('template/header'); ?>
     
 </head>
                                  <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery-ui.css">
                                  <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-1.12.4.js" ></script>
                                  <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
                                  <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.js" ></script>
                                  <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>


<!--<//?php
        echo "<table border=\"0\" align=\"left\" style=\"color: black;  border-collapse:collapse; border:1px;\">";
        echo "<tr style=\"text-align:left; \">";
                echo "<td style=\"padding: 8px 8px 8px 20px; decoration:none;\">";
        echo anchor('setup/displayrole/', "View Role Detail ", array('title' => 'Add Detail'));
        echo "</td>";
        echo "</tr>";
        echo "</table>";
        ?>-->
<script>
$(document).ready(function(){
$("#StartDate").datepicker({
onSelect: function(value, ui) {
  console.log(ui.selectedYear)
  var today = new Date(), 
  dob = new Date(value), 
  age = 2017-ui.selectedYear;
  //$("#age").text(age);
                                },
                                //(set for show current month or current date)maxDate: '+0d',
                                
  changeMonth: true,
  changeYear: true,
  dateFormat: 'yy-mm-dd',
 // defaultDate: '1yr',
  yearRange: 'c-47:c+50',
});

$("#EndDate").datepicker({ 
onSelect: function(value, ui) {
 console.log(ui.selectedYear)
var today = new Date(), 
dob = new Date(value), 
age = 2017-ui.selectedYear;

//$("#age").text(age);
},
                                //(set for show current month or current date)maxDate: '+0d',
changeMonth: true,
changeYear: true,
dateFormat: 'yy-mm-dd',
//defaultDate: '1yr',
yearRange: 'c-47:c+50',
});
});
</script>

<body>
     <table width="100%">
            <tr><td>
                <div>
                <?php echo anchor('setup/displaysociety/', "View Society", array('title' => 'Add Detail' ,'class' =>'top_parent'));?>
                <?php
                 echo "<td align=\"right\">";
                 $help_uri = site_url()."/help/helpdoc#Role";
                 echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                 echo "</td>";
                 ?>
                <div  style="width:100%;">
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

    <tr>
    <div>
    <form action="<?php echo site_url('setup/addsociety');?>" method="POST" class="form-inline">
            <table>
              <tr>
                <td><label for="soc_code" class="control-label">Society Code <font color='Red'>*</font></label></td>
                <td>
                <input type="text" name="soc_code"  class="form-control" size="30" value="<?php echo isset($_POST["soc_code"]) ? $_POST["soc_code"] : ''; ?>" /><br>
                </td>

              <tr>
                <td><label for="soc_name" class="control-label">Society Name <font color='Red'>*</font></label></td>
                <td>
                <input type="text" name="soc_name" class="form-control" size="30" value="<?php echo isset($_POST["soc_name"]) ? $_POST["soc_name"] : ''; ?>" /><br>
 
                </td>

              <tr>
                <td>
                <label for="soc_remark" class="control-label">Society Remark:</label></td>
                <td>
                <input type="text" name="soc_remark" size="30"  class="form-control" value="<?php echo isset($_POST["soc_remark"]) ? $_POST["soc_remark"] : ''; ?>" /><br>

                </td>
      
            </tr>

            <tr>
                <td></td><td>
                <button name="addsociety" >Add Society</button>
                <input type="reset" name="Reset" value="Clear"/>
                </td>
           </tr>
           </table>
    </form>
 <p><br></p>
                                                                   
