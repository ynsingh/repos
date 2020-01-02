<?php defined('BASEPATH') OR exit('No direct script access allowed');
 
?>
<html>
<title>Third Part|Gem-Form</title>
 <head>    
        <?php $this->load->view('template/header'); ?>

        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
    <table width="100%">
            <tr><td>
                 <?php 
                   
            
                  ?>
                 </td>
                 
           </tr> 
            <tr><td>   
              <div align="left">
                    <?php echo validation_errors('<div  class="isa_warning">','</div>');?>
                    <?php echo form_error('<div class="isa_error">','</div>');?></div>

                    <?php if(isset($_SESSION['success'])){?>
                        <div class="isa_success"><?php echo $_SESSION['success'];?></div>

                    <?php
                    };
                    ?>
                </div> </br> 
        </td></tr>  
    </table>
    <table>
  <tr>
                <td>
                <?php
                    echo anchor('intender/gem_proposals/','Go Back', array('title'=>''));
                ?>
               
                </td>
            </tr>  
</table>
</head>
     
<body>

		<form action="<?php echo site_url('intender/gem_audit_upload');?>" method="POST" class="form-inline">
			<table class="TFtable">
           

			    <tr>
					<td><label for="bu_head" class="control-label">You Are:</label></td>
                	<td>
                	<input type="text" name="bu_head" readonly class="form-control" size="30" value="<?php echo $this->session->userdata('username'); ?>" /><br>
                	</td>
                	<td>
                  Example:Action will be done under your name.
                	</td>
                </tr>

               
               <tr>
                    <td><label for="bu_comment" class="control-label">Observation: </label></td>
                    <td>       <textarea name="bu_comment" style="width:47%; height:200%"></textarea>                </td>
                    <td>
                   
                    </td>
                </tr>
               
                <tr>
                <td></td>
                <td colspan="2">
                <input type="hidden" name="pp_id" value="<?php echo $data; ?>">
                <button name="press">Submit Form</button>
                </td>
           		</tr>

			</table>
		</form>

</body>
<p>&nbsp;</p>
    <div align=left> <?php $this->load->view('template/footer');?></div>
</html>