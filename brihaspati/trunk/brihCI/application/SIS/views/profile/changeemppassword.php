<!--@name changeemppassword.php 
   @author Om Prakash(omprakashkgp@gmail.com)
 -->
 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>
 <html>
   <head>    
        <?php $this->load->view('template/header'); ?>
       <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css"> 
   </head>
 <body> &nbsp;
   <table>
      <tr><td>
         <div>
          <?php echo validation_errors('<div class="isa_warning">','</div>');?>
          <?php echo form_error('<div class="isa_error">','</div>');?>
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

	</td>
      </tr>		
   </table>
 <form id="myform" action="<?php echo site_url('profile/changeemppassword');?>" method="POST" class="form-inline">
   <table style="width:100%; border:1px solid gray;" align="center" class="TFtable">
        <tr><thead><th  style="background-color:#2a8fcf;text-align:left;height:40px;" colspan="2">&nbsp;Change Employee Password Form</th></thead></tr>
	<tr>
	    <td> 
	        <label for="empemail" style="font-size:15px;"> Employee Email: <font color='Red'> * </font></label></td><td>
                <div><input type="text" name="empemail" id="empemail" size="40" value="<?php echo isset($_POST["empemail"]) ? $_POST["empemail"] : ''; ?>" placeholder="Employee Email..." />
	    </td>
        </tr>
	<tr>
	   <td>
              <label for="npasswd" style="font-size:15px;"> Password: <font color='Red'> * </font></label></td><td>
              <div><input type="password" name="npasswd" id="npasswd" size="40" value="<?php echo isset($_POST["npasswd"]) ? $_POST["npasswd"] : ''; ?>" placeholder="New Password..." />
	   </td>
	</tr>
<!--	<tr>
	   <td>
              <label for="renpasswd" style="font-size:15px;"> Re New Password: <font color='Red'> * </font></label></td><td>
              <div><input type="password" name="renpasswd" id="renpasswd" size="40" value="<?php //echo isset($_POST["renpasswd"]) ? $_POST["renpasswd"] : ''; ?>" placeholder="Re New Password..." required="required" />
	   </td>
	</tr> -->
       <tr style="background-color:#2a8fcf;text-align:left;height:40px;">
          <td colspan="2">
              <button name="changeemppassword" >Submit</button>
	      <input type="reset" name="Reset" value="Clear"/>
          </td>
       </tr>
  </table>  	
 </form>
 </body>
 <p> &nbsp; </p>
   <div align="center">  <?php $this->load->view('template/footer');?></div>
 </html>
