<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Critical|Dates</title>

 <head>
  <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
     <?php $this->load->view('template/header'); ?>
   
 </head>    
 <body> 
<!--
<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>-->


     <table width="100%">
            <tr><td>
                <?php echo anchor('', "View Detail ", array('title' => 'Add Detail' ,'class' =>'top_parent'));?>
                <?php
                 echo "<td align=\"right\">";
                 $help_uri = site_url()."/help/helpdoc#Role";
		 echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
		echo "</td>"
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
   


<form action="<?php echo site_url('tender/tenderupload');?>" method="POST" class="form-inline" enctype="multipart/form-data" >

<h2 class="title">Upload NIT Documents</h2>
<p class="subtitle"><b>Step 5</b></p>
        
        <table class="TFtable">
        
     <tr>  
     <td>  
    <input type="file" name="file_a" id="file_a" > 
     </td>
     </tr>
     
   
    
     <tr>
     
     <td>
    <input type="submit" value="Upload Image" name="submit">
    <input type="hidden" name="tid" value="<?php echo $tid ;?>" >
  </td>
	
	</tr>

		
       
       
       
    </table>


</form>

  
 <p><br></p>
    </div>
    </body>
<p>&nbsp;</p>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
    </html>
	