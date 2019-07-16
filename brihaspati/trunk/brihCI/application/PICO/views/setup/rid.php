
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Add|Item|Details</title>

 <head>
     <?php $this->load->view('template/header'); ?>
   
 </head>    
 <body> 
<!--<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>-->


     <table width="100%">
            <tr><td>
                <?php echo anchor('picosetup/displayrid/', "View Detail ", array('title' => 'Add Detail' ,'class' =>'top_parent'));?>
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
 
    <form action="<?php echo site_url('picosetup/rid');?>" method="POST" class="form-inline">
            <table>
            <tr>
                <td><label for="rid_ppid" class="control-label">Id:</label></td>
                <td><input type="text" name="rid_ppid"  class="form-control" size="30" /><br></td>
                <td>
                    <?php echo form_error('rid_ppid')?>
                </td>
                <td>Example:xyz.</td>
            </tr>
            <tr>
                <td><label for="rid_itemdes" class="control-label">Item Description:</label></td>
                <td><input type="text" name="rid_itemdes" size="30"  class="form-control" /><br></td>
                <td>
                    <?php echo form_error('rid_itemdes')?>
                </td>
                <td>Example : Computers  .</td>
            </tr>
            <tr>
                <td><label for="rid_itemstock" class="control-label">In Stock:</label></td>
                <td><input type="text" name="rid_itemstock"  class="form-control" size="30" /><br></td>
                <td>
                    <?php echo form_error('rid_itemstock')?>
                </td>
                <td>Example:50.</td>
            </tr>
            <tr>
                <td><label for="rid_itemqtyreq" class="control-label"> Quantity Required:</label></td>
                <td><input type="text" name="rid_itemqtyreq" size="30"  class="form-control" /><br></td>
                <td>
                    <?php echo form_error('rid_itemqtyreq')?>
                </td>
                <td>Example :28 .</td>
            </tr>
            <tr>
                <td><label for="rid_itemunitp" class="control-label">Unit Cost:</label></td>
                <td><input type="text" name="rid_itemunitp"  class="form-control" size="30" /><br></td>
                <td>
                    <?php echo form_error('rid_itemunitp')?>
                </td>
                <td>Example: 789 etc.</td>
            </tr>
            <tr>
                <td><label for="rid_itemgstapply" class="control-label">GST Apply:</label></td>
                <td><input type="text" name="rid_itemgstapply" size="30"  class="form-control" /><br></td>
                <td>
                    <?php echo form_error('rid_itemgstapply')?>
                </td>
                <td>Example :5 percent .</td>
            </tr>
            <tr>
                <td><label for="rid_gst" class="control-label">GST No:</label></td>
                <td><input type="text" name="rid_gst"  class="form-control" size="30" /><br></td>
                <td>
                    <?php echo form_error('rid_gst')?>
                </td>
                <td>Example:12MA1234567890A .</td>
            </tr>
            <tr>
                <td><label for="rid_itemtotcost" class="control-label">Total Cost:</label></td>
                <td><input type="text" name="rid_itemtotcost" size="30"  class="form-control" /><br></td>
                <td>
                    <?php echo form_error('rid_itemtotcost')?>
                </td>
                <td>Example :25000.</td>
            </tr>
            
            
            <tr>
                <td></td><td>
                <button name="rid" >Add item</button>
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

   
