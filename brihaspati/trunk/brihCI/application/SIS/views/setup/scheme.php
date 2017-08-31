<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name scheme.php 
  @author Rekha Devi Pal(rekha20july@gmail.com)
 -->


<html>
<title>Add Scheme</title>
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
           echo anchor('setup/displayscheme', 'Scheme List', array('class' => 'top_parent'));
	   $help_uri = site_url()."/help/helpdoc#Scheme";
	   echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:73%\">Click for Help</b></a>";
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
        <form action="<?php echo site_url('setup/scheme');?>" method="POST" class="form-inline">
          <table style="margin-left:1%">
            <tr>  
                <td><label for="sname" class="control-label"> Scheme Name :</label></td>
                <td>
                <input type="text" name="sname" class="form-control" size="40" /><br>
                </td>
 		<td><?php echo form_error('sname')?></td> 
            </tr>
            <tr> 
                <td>    
                <label for="scode" class="control-label">Scheme Code :</label>
                </td>
                <td>
                    <input type="text" name="scode" size="40" class="form-control"/> <br>
                </td>
 		<td><?php echo form_error('scode')?></td>
            </tr>
            <tr>
                <td>   
                    <label for="ssname" class="control-label">Scheme Short Name :</label>
                </td>
                <td>
                    <input type="text" name="ssname" size="40"  class="form-control"/> <br>
                </td>
		 <td><?php echo form_error('ssname')?></td>
            </tr>
            <tr>
                <td>   
                <label for="sdesc" class="control-label">Scheme Description :</label>
                </td>
                <td>
                    <input type="text" name="sdesc"  size="40"/> <br>
                </td>
 		<td><?php echo form_error('sdesc')?></td>
            </tr>
            <tr><td></td>
                <td colspan="2">   
                <button name="scheme" >Add Scheme </button>
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
      
