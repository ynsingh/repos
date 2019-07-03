
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
<title>Add Tender</title>

 <head>
     <?php $this->load->view('template/header'); ?>
     <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
 </head>    
 <body> 
     <table width="100%">
            <tr><td>
                <?php echo anchor('picosetup/displaytypeoftender/', "View tender Detail ", array('title' => 'Add Detail' ,'class' =>'top_parent'));?>
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
 
    <form action="<?php echo site_url('picosetup/tender_type');?>" method="POST" class="form-inline">
            <table class="TFtable">
            <tr>
                <td><label for="tt_name" class="control-label">Tender Name:</label></td>
                <td>
                <input type="text" name="tt_name"  class="form-control" size="30" /><br>
                </td>
                <td>
                    <?php echo form_error('tt_name')?>
                </td>
                <td>
                   Example:single tender , rate control etc.
                </td>

            </tr>
            <tr>
                <td>
                <label for="tt_desc" class="control-label">Tender Description:</label>
                </td>
                <td>
                    <input type="text" name="tt_desc" size="30"  class="form-control" /> <br>
                </td>
                <td>
                    <?php echo form_error('tt_desc')?>
                </td>
                <td>
                    Example :Tender details like DOS & D control.
                </td>
            </tr>
            <tr>
                <td></td>
                <td colspan="3">
                <button name="tender_type" >Add Tender</button>
                <button name="reset" >Clear</button>
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

   
