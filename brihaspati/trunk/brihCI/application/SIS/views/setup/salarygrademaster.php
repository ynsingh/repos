<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name salarygrademaster.php
  @author Rekha Devi Pal(rekha20july@gmail.com)
 -->


<html>
<title>Salary Grade Master</title>
 <head>    
	<?php $this->load->view('template/header'); ?>
	<h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
	<?php $this->load->view('template/menu');?>
 </head>    
   <body>
     <table width="100%"> 
       <tr><td>
   	<div align="left">
       	<?php
           echo anchor('setup/displaysalarygrademaster', 'Salary Grade List', array('class' => 'top_parent'));
	  // $help_uri = site_url()."/help/helpdoc#Scheme";
	   //echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:73%\">Click for Help</b></a>";
       	?>
    	</font>
   	</div>
        <div align="left" style="margin-left:2%;width:90%;">
        <?php echo validation_errors('<div class="isa_warning">','</div>');?>
        <?php echo form_error('<div class="isa_error">','</div>');?>
        <?php if(isset($_SESSION['success'])){?>
        <div class="alert alert-success"><?php echo $_SESSION['success'];?></div>
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
    <tr>  
    <div align="left" style="margin-left:1%">
        <form action="<?php echo site_url('setup/salarygrademaster');?>" method="POST" class="form-inline">
          <table style="margin-left:1%">
            <tr>  
                <td><label for="sgmname" class="control-label">Salary Grade Name :</label></td>
                <td>
                <input type="text" name="sgmname" class="form-control" size="40" /><br>
                </td>
 		<td><?php echo form_error('sgmname')?></td> 
            </tr>
            <tr> 
                <td>    
                <label for="sgmmax" class="control-label">Salary Grade Max :</label>
                </td>
                <td>
                    <input type="text" name="sgmmax" size="40" class="form-control"/> <br>
                </td>
 		<td><?php echo form_error('sgmmax')?></td>
            </tr>
            <tr>
                <td>   
                    <label for="sgmmin" class="control-label">Salary Grade Min :</label>
                </td>
                <td>
                    <input type="text" name="sgmmin" size="40"  class="form-control"/> <br>
                </td>
		 <td><?php echo form_error('sgmmin')?></td>
            </tr>
            <tr>
                <td>   
                <label for="sgmgradepay" class="control-label">Salary Grade Pay Band :</label>
                </td>
                <td>
                    <input type="text" name="sgmgradepay"  size="40"/> <br>
                </td>
 		<td><?php echo form_error('sgmgradepay')?></td>
            </tr>
            <tr><td></td>
                <td colspan="2">   
                <button name="salarygrademaster" >Add Salary Grade </button>
		<input type="reset" name="Reset" value="Clear"/>
                </td>
            </tr>
           </table>
        </form>
      </div> 
     </tr>     
    </table>     
  </body>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
</html>
      
