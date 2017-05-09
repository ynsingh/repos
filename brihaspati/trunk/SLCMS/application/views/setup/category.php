<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name category.php 
  @author Om Prakash(omprakashkgp@gmail.com)
 -->


<html>
<head>    
	<?php $this->load->view('template/header'); ?>
	<h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
	<?php $this->load->view('template/menu');?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url();?>assets/css/message.css">
</head>    
<body>

        <?php
            echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
            echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
            echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
            echo "Setup";
            echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo "|";
            echo anchor('setup/displaycategory/', "View Category", array('title' => 'Add Detail' , 'class' => 'top_parent'));
            echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo "|";
            echo "<span  style='padding: 8px 8px 8px 20px;'>";
            echo "Add Category";
            echo "</span>";
            echo "</td>";
            echo "</tr>";
            echo "</table>";
        ?>
  
   <table style="padding: 8px 8px 8px 20px;"> 
     
   <tr colspan="2"><td>    
    <div align="left" style="margin-left:30px;width:1700px;">
       <?php echo validation_errors('<div class="isa_warning">','</div>');?>
       <?php echo form_error('<div style="margin-left:30px;" class="isa_error">','</div>');?>
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
		<td> Example : Scheduled Tribe </td>
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
		<td> Example : This Category is belonging in Madhya Pradesh, India. </td>
            </tr>
            <tr>
                <td colspan="2">   
                <button name="category" >Add Category </button>
		<input type="reset" name="Reset" value="Reset"/>
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
      
