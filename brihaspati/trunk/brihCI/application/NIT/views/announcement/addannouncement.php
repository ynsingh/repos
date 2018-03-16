<!--@name addannouncement.php
    @author Deepika Chaudhary (chaudharydeepika88@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Add Announcement</title>
<!--<link rel="shortcut icon" href="<?php //echo base_url('assets/images'); ?>/index.jpg">-->
	<link rel="icon" href="<?php echo base_url('uploads/logo'); ?>/nitsindex.png" type="image/png">	
 <head>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/datepicker/jquery-ui.css">
        <script type="text/javascript" src="<//?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-ui.js" ></script>

     <?php $this->load->view('template/header'); ?>
     <?php //$this->load->view('template/menu');?>
 </head>
 <body>
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table> -->
<script>
$( function() {
	   $( "#StartDate,#EndDate" ).datepicker({
		dateFormat: 'yy-mm-dd',
	   	minDate: 0
	   	});
	  	});
</script>
	<table width="100%">
        <tr>
		 <div>
                <?php 
                echo "<td align=\"left\" width=\"33%\">";
                echo anchor('announcement/viewannouncement', "View Announcement", array('title' => 'Add Detail' ,'class' =>'top_parent'));
                echo "</td>";

                echo "<td align=\"center\" width=\"34%\">";
                echo "<b>Add Announcement Details</b>";
                echo "</td>";

                echo "<td align=\"right\" width=\"33%\">";
                 $help_uri = site_url()."/help/helpdoc#Role";
                 echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
		echo "</td>";
                 ?>    
           </div>             
           </tr>
           </table>
           <table width="100%">
           <tr><td>
		<div>
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                <?php if(isset($_SESSION['success'])){?>
                <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                <?php
                };
                ?>
                <?php if(isset($_SESSION['error'])){?>
                    <div class="isa_error"><?php echo $_SESSION['error'];?></div>
                <?php
                };
               ?>
                            </div>
             </td></tr>
        </table>
    <div>
    <form action="<?php echo site_url ('announcement/addannouncement');?>" method="POST" enctype="multipart/form-data">
 <table><tr>
                <td><label for="anou_cname" class="control-label">Announcement Component Name:</label></td>
                <td>
		<select name="anou_cname" id="" class="my_dropdown" style="width:96%;">
		<option value="" disabled selected >------Select Announcement Component Name------</option>
		<option value="SLCMS" class="dropdown-item">SLCMS</option>
                <option value="HRM" class="dropdown-item">HRM</option>
                <option value="SIS" class="dropdown-item">SIS</option>
                </td>
         </tr>
               	      <tr><td><label for="anou_type" class="control-label">Announcement Type:</label></td> 
                        <td>
                        <select name="anou_type" id="" class="my_dropdown" style="width:96%;">
                        <option value="" disabled selected >------Select Announcement Type------</option>
                        <option value="Addmision" class="dropdown-item">Addmision</option>
                        <option value="Acadmic" class="dropdown-item">Acadmic</option>
                        <option value="Exam" class="dropdown-item">Exam</option>
                        <option value="Rent" class="dropdown-item">Rent</option>
                        <option value="General" class="dropdown-item">General</option>
                        </select>
                        </td></tr>
		 <tr>
                <td><label for="anou_title" class="control-label">Announcement Title</label></td>
                <td>
                <input type="text" name="anou_title"  class="form-control" size="31" />
                </td>
            	</tr>
		 <tr>
                <td><label for="anou_description" class="control-label">Announcement Description:</label></td>
		<td><textarea rows= "" cols="45" name="anou_description" size="50" > </textarea></td>
                </tr>
		<tr>
	       <td><label for="anou_attachment" class="control-label">Upload Attachment:</label></td>
               <td><input type='file' name='userfile' size='20' style="font-size:15px;"/></td>
                </tr>
		<tr>
                <td><label for="anou_publishdate" class="control-label">Announcement Publish Date:</label></td>
                <td><input type="text" name="anou_publishdate" id="StartDate" class="form-control" size="31"  value="<?php echo isset($_POST["anou_publishdate"]) ? $_POST["anou_publishdate"] : ''; ?>" required="required"/></td>
                </tr>
		<tr>
                <td><label for="anou_expdate" class="control-label">Announcement Expiry Date:</label></td>
                <td><input type="text" name="anou_expdate" id="EndDate" class="form-control" size="31"  value="<?php echo isset($_POST["anou_expdate"]) ? $_POST["anou_expdate"] : ''; ?>" required="required"/></td>
                </tr>
                <tr>
                <td><label for="anou_remark" class="control-label">Announcement Remark:</label></td>
                <td><textarea rows= "" cols="45" name="anou_remark" size="50" > </textarea></td>
               </tr>

                <tr>
                <td></td><td>
                <button name="addannouncement" >Add Announcement</button>
                <button name="reset" >Clear</button>
                </td>
           </tr>
           </table>
    </form>
    </div>
   </body>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
    </html>

