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

<form action="<?php echo site_url('tender/tendercritical');?>" method="POST" class="form-inline">


<h2 class="title">Critical Dates</h2>
<p class="subtitle">Step 4</p>

         <table class="TFtable">
            <tr>
                <td><label for="cd_pd" class="control-label">Publishing Date.:</label></td>
                <td><input type="date" name="cd_pd"  class="form-control" style="width:300 ;" /><br>
                    <input type="time" name="cd_pdt"  class="form-control" style="width:300 ;" /><br>
                
                
                </td>
                <td>
                    <?php echo form_error('cd_pd')?>
                    <?php echo form_error('cd_pdt')?>
                </td>
                
        
                <td><label for="cd_dssd" class="control-label">Document Sale Start Date.:</label></td>
                <td><input type="date" name="cd_dssd"  class="form-control" style="width:300 ;" /><br>
                    <input type="time" name="cd_dssdt"  class="form-control" style="width:300 ;" /><br>
                
                
                </td>
                <td>
                    <?php echo form_error('cd_dssd')?>
                    <?php echo form_error('cd_dssdt')?>
                </td>
                
           </tr>
            <tr>
                <td><label for="cd_dsed" class="control-label">Document Sale End Date.:</label></td>
                <td><input type="date" name="cd_dsed"  class="form-control" style="width:300 ;" /><br>
                    <input type="time" name="cd_dsedt"  class="form-control" style="width:300 ;" /><br>
                
                
                </td>
                <td>
                    <?php echo form_error('cd_dsed')?>
                    <?php echo form_error('cd_dsedt')?>
                </td>
                
         
                <td><label for="cd_scsd" class="control-label">Seek Clarification Start Date.:</label></td>
                <td><input type="date" name="cd_scsd"  class="form-control" style="width:300 ;" /><br>
                    <input type="time" name="cd_scsdt"  class="form-control" style="width:300 ;" /><br>
                
                
                </td>
                <td>
                    <?php echo form_error('cd_scsd')?>
                    <?php echo form_error('cd_scsdt')?>
                </td>
                
           </tr>
            <tr>
                <td><label for="cd_sced" class="control-label">Seek Clarification End Date.:</label></td>
                <td><input type="date" name="cd_sced"  class="form-control" style="width:300 ;" /><br>
                    <input type="time" name="cd_scedt"  class="form-control" style="width:300 ;" /><br>
                
                
                </td>
                <td>
                    <?php echo form_error('cd_sced')?>
                    <?php echo form_error('cd_scedt')?>
                </td>
          
                <td><label for="cd_pbmd" class="control-label">Pre Bid Meeting Date.:</label></td>
                <td><input type="date" name="cd_pbmd"  class="form-control" style="width:300 ;" /><br>
                    <input type="time" name="cd_pbmdt"  class="form-control" style="width:300 ;" /><br>
                
                
                </td>
                <td>
                    <?php echo form_error('cd_pbmd')?>
                    <?php echo form_error('cd_pbmdt')?>
                </td>
             </tr>   
         
                <td><label for="cd_bssd" class="control-label">Bid Submission Start Date.:</label></td>
                <td><input type="date" name="cd_bssd"  class="form-control" style="width:300 ;" /><br>
                    <input type="time" name="cd_bssdt"  class="form-control" style="width:300 ;" /><br>
                
                
                </td>
                <td>
                    <?php echo form_error('cd_bssd')?>
                    <?php echo form_error('cd_bssdt')?>
                </td>
                
          
                <td><label for="cd_bsed" class="control-label">Bid Submission End Date.:</label></td>
                <td><input type="date" name="cd_bsed"  class="form-control" style="width:300 ;" /><br>
                    <input type="time" name="cd_bsedt"  class="form-control" style="width:300 ;" /><br>
                
                
                </td>
                <td>
                    <?php echo form_error('cd_bsed')?>
                    <?php echo form_error('cd_bsedt')?>
                </td>
             </tr>
            <tr>    
         
                <td><label for="cd_bod" class="control-label">Bid Opening  Date.:</label></td>
                <td><input type="date" name="cd_bod"  class="form-control" style="width:300 ;" /><br>
                    <input type="time" name="cd_bodt"  class="form-control" style="width:300 ;" /><br>
                
                
                </td>
                <td>
                   <?php echo form_error('cd_bod')?>
                    <?php echo form_error('cd_bodt')?>
                </td>
                
         <td></td> <td></td>
       </tr> 
        <tr rowspan="2">
 		 			<td colspan="6"> 
 				<button name="cd">Next</button>
 				<button name="reset">Clear</button>
 				<input type="hidden" name="tid" value="<?php echo $tid ;?>" > 
 				 	</td> 
 				 
 		 		</tr>
 		 		<tr></tr>
 		 <tr></tr>	
 		 	</tbody>

</table>           
           
</form>

  
 <p><br></p>
    </div>
    </body>
<p>&nbsp;</p>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
    </html>
	
	

