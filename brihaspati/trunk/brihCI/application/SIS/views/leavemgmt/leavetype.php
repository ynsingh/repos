<!--@name leavetype.php
    @author Shobhit Tiwari(tshobhit206@gmail.com)
    @author Ankur Singh (ankursingh206@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Leave Type</title>

<head>
     <?php $this->load->view('template/header'); ?>
</head>
<body>

  <table width="100%">
            <tr>
               <div>
               <td> <?php echo anchor('leavemgmt/displayleavetype/', "View Leave Type ", array('title' => 'Leave Details' ,'class' =>'top_parent'));?></td>
					 <?php
					 echo "<td align=\"center\">";
					 echo "<b>Add Leave Type</b>";
					 echo "</td>";
					 ?>
                <?php
                 echo "<td align=\"right\" width=\"33%\">";
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
            </tr>
  </table>
  <br>

<div>
<form action="<?php echo site_url('leavemgmt/leavetype');?>" method="POST" class="form-inline">
  <table>
            <tr>
					<td><label for="lt_name" class="control-label">Leave Name: <font color='Red'> *</font> </label></td>              
					<td><input type="text" name="lt_name"  class="form-control" size="30"  value="<?php echo isset($_POST["lt_name"]) ? $_POST["lt_name"] : ''; ?>" /></td>
            </tr>
            <tr>
					<td><label for="lt_code" class="control-label">Leave Code: <font color='Red'> *</font> </label></td>               
               <td><input type="text" name="lt_code"  class="form-control" size="30"  value="<?php echo isset($_POST["lt_code"]) ? $_POST["lt_code"] : ''; ?>"/></td>
            <tr>
					<td><label for="lt_short" class="control-label">Short Name: <font color='Red'> *</font> </label></td>                 
               <td><input type="text" name="lt_short"  class="form-control" size="30" value="<?php echo isset($_POST["lt_short"]) ? $_POST["lt_short"] : ''; ?>"/></td>
            </tr>
            <tr> 
               <td><label for="lt_value" class="control-label">Leave Value: <font color='Red'> *</font> </label></td>                  
               <td><input type="text" name="lt_value" size="30"  class="form-control"  value="<?php echo isset($_POST["lt_value"]) ? $_POST["lt_value"] : '';?>"/></td>
            </tr>
           <tr>
              <td><label for="lt_remarks" class="control-label">Description: <font color='Red'> *</font> </label></td>         		
         		<td><input type="text" name="lt_remarks"  class="form-control" size="30" value="<?php echo isset($_POST["lt_remarks"])?$_POST["lt_remarks"]:'';?>"/></td>
			  </tr>
            <tr>
                <td></td>
                <td>  
                <button name="leavetype" >Add Leave</button>
                <input type="reset" value="Clear"/>
                
                </td>
            </tr>
  </table>
</form>
</div>

</body>
<div align="center"> <?php $this->load->view('template/footer');?></div>
</html>

                                                                                                                                                                                          
