 <!--@name updateddo.php
    @author Om Prakash(omprakashkgp@gmail.com)
 -->
 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>
 <html>
   <head>    
        <?php $this->load->view('template/header'); ?>
            <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
        <?php $this->load->view('template/menu');?>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
   </head>
   <body>
	<script>
           function goBack() {
        	window.history.back();
        }
	</script>
   </script>
   <table style="padding: 8px 8px 8px 20px;">
     <tr colspan="2"><td>
       <div style="margin-left:10px;width:1700px;">
          <?php echo validation_errors('<div class="isa_warning">','</div>');?>
             <?php echo form_error('<div class="isa_error">','</div>');?>
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
              </div></br>
           </td></tr>
     </table>
     <table style="margin-left:30px;">
     <form action="<?php echo site_url('setup/updateddo/' . $ddo_id);?>" method="POST" class="form-inline">
       <tr>
             <td> Campus Name </td>
             <td>
                 <?php echo form_input($campusname); ?>
             </td>
       </tr>
             <td> Department Name</td>
             <td>
                 <?php echo form_input($deptname); ?>
             </td>
       </tr>
       <tr>
             <td> Scheme Name </td>
             <td>
                 <?php echo form_input($schemecode); ?>
             </td>
       </tr>  
             <td> DDO Code </td>
             <td>
                 <?php echo form_input($ddocode); ?>
             </td>
       </tr>
       <tr>	
             <td> DDO Name </td>
             <td>
                 <?php echo form_input($ddoname); ?>
             </td>
       </tr>
       <tr>
             <td> Remark </td>
             <td>
                <?php echo form_input($remark); ?>
             </td>
       </tr>
       <tr>  <td></td>
             <td>
	     <button name "submit" >Update</button>
       </form>
	     <?php echo "<button onclick=\"goBack()\" >Back</button>";?>
	     </td>	
       </tr>
	     <?php echo form_hidden( 'ddo_id', $ddo_id );?>
   </table>
   </body>
   <div align="center">  <?php $this->load->view('template/footer');?></div>
 </html>
 
