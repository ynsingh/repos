<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name category.php 
  @author Om Prakash(omprakashkgp@gmail.com)
 -->


<html>
<title>Add Category</title>
 <head>    
	<?php $this->load->view('template/header'); ?>
	<h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
	<?php $this->load->view('template/menu');?>
 </head>    
   <body>
     <table> 
       <tr colspan="2"><td>
   	<div style="margin-left:10px;" >
       	<?php
           echo anchor('setup/displaycategory', 'Category List', array('class' => 'top_parent'));
	   $help_uri = site_url()."/help/helpdoc#Category";
	   echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:77%\">Click for Help</b></a>";
       	?>
    	</font>
   	</div>
        <div align="left" style="margin-left:30px;width:1700px;">
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
    <div align="left" style="margin-left:30px">
        <form action="<?php echo site_url('setup/category');?>" method="POST" class="form-inline">
          <table style="margin-left:30px">
            <tr>  
                <td><label for="cname" class="control-label"> Category Name :</label></td>
                <td>
                <input type="text" name="cname" class="form-control" size="40" /><br>
                </td>
 		<td><?php echo form_error('cname')?></td> 
		<td> Example : Scheduled Tribe, Other Backward Class, General etc. </td>
            </tr>
            <tr> 
                <td>    
                <label for="ccode" class="control-label">Category Code :</label>
                </td>
                <td>
                    <input type="text" name="ccode" size="40" class="form-control"/> <br>
                </td>
 		<td><?php echo form_error('ccode')?></td>
		<td>Example : 01, 02, 03, st04, sc-5 etc.</td>
            </tr>
            <tr>
                <td>   
                    <label for="csname" class="control-label">Category Short Name :</label>
                </td>
                <td>
                    <input type="text" name="csname" size="40"  class="form-control"/> <br>
                </td>
		 <td><?php echo form_error('csname')?></td>
		<td> Example : ST, SC, OBC, GEN, PH etc. </td>
            </tr>
            <tr>
                <td>   
                <label for="cdesc" class="control-label">Category Description :</label>
                </td>
                <td>
                    <input type="text" name="cdesc"  size="40"/> <br>
                </td>
 		<td><?php echo form_error('cdesc')?></td>
		<td> Example : Reserved Category, Unreserved Category. </td>
            </tr>
            <tr><td></td>
                <td colspan="2">   
                <button name="category" >Add Category </button>
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
      
