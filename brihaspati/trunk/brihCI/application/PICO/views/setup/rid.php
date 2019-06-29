
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Add item</title>

 <head>
     <?php $this->load->view('template/header'); ?>
   
 </head>    
 <body> 
<!--<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>-->


     <table width="100%">
            <tr><td>
                <?php echo anchor('picosetup/displayrid/', "View rid Detail ", array('title' => 'Add Detail' ,'class' =>'top_parent'));?>
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
                <td><label for="rid_ppid" class="control-label">item ppid:</label></td>
                <td><input type="text" name="rid_ppid"  class="form-control" size="30" /><br></td>
                <td>
                    <?php echo form_error('rid_ppid')?>
                </td>
                <td>Example:xyz limited etc.</td>
            </tr>
            <tr>
                <td><label for="rid_itemdes" class="control-label">item des:</label></td>
                <td><input type="text" name="rid_itemdes" size="30"  class="form-control" /><br></td>
                <td>
                    <?php echo form_error('rid_itemdes')?>
                </td>
                <td>Example : house-123,new Delhi  .</td>
            </tr>
            <tr>
                <td><label for="rid_itemstock" class="control-label">item stock:</label></td>
                <td><input type="text" name="rid_itemstock"  class="form-control" size="30" /><br></td>
                <td>
                    <?php echo form_error('rid_itemstock')?>
                </td>
                <td>Example:Kanpur etc.</td>
            </tr>
            <tr>
                <td><label for="rid_itemqtyreq" class="control-label"> itemqtyreq:</label></td>
                <td><input type="text" name="rid_itemqtyreq" size="30"  class="form-control" /><br></td>
                <td>
                    <?php echo form_error('rid_itemqtyreq')?>
                </td>
                <td>Example :208017 .</td>
            </tr>
            <tr>
                <td><label for="rid_itemunitp" class="control-label">item unit p:</label></td>
                <td><input type="text" name="rid_itemunitp"  class="form-control" size="30" /><br></td>
                <td>
                    <?php echo form_error('rid_itemunitp')?>
                </td>
                <td>Example: XXXXX789 etc.</td>
            </tr>
            <tr>
                <td><label for="rid_itemgstapply" class="control-label">item gst apply:</label></td>
                <td><input type="text" name="rid_itemgstapply" size="30"  class="form-control" /><br></td>
                <td>
                    <?php echo form_error('rid_itemgstapply')?>
                </td>
                <td>Example : types .</td>
            </tr>
            <tr>
                <td><label for="rid_gst" class="control-label">gst:</label></td>
                <td><input type="text" name="rid_gst"  class="form-control" size="30" /><br></td>
                <td>
                    <?php echo form_error('rid_gst')?>
                </td>
                <td>Example:bts  etc.</td>
            </tr>
            <tr>
                <td><label for="rid_itemtotcost" class="control-label">item tot. cost:</label></td>
                <td><input type="text" name="rid_itemtotcost" size="30"  class="form-control" /><br></td>
                <td>
                    <?php echo form_error('rid_itemtotcost')?>
                </td>
                <td>Example :2017-06-24 .</td>
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

   
