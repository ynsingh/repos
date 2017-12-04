<!--@name updatescuo.php 
    @author Om Prakash(omprakashkgp@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
    <head>    
        <?php $this->load->view('template/header'); ?>
            <!--h1>Welcome <?= $this->session->userdata('username') ?>  </h1-->
        <?php $this->load->view('template/menu');?>

    </head>
    <body>
    <table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
	<script>
        function goBack() {
         window.history.back();
        }
        </script>

       <table width="100%">
          <tr colspan="2"><td>
          <div>
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
               </div>
            </td></tr>
        </table>
    <table>
      <form action="<?php echo site_url('map/updatescuo/' . $scuo_id);?>" method="POST" class="form-inline">
        <tr>
		<td> Campus Name </td>
                <td>
			<?php echo form_input($campusname); ?>
                </td>
        </tr>
        <tr>
                <td>UO Name</td>
                <td>
		       <select name="authority" id="authority" class="my_dropdown" style="width:300px;">
                	<option value="<?php echo $this->loginmodel->get_listspfic1('authorities', 'id', 'name', $authority["value"])->id; ?>"><?php echo $authority["value"]; ?></option>
                	<?php foreach($this->authorty as $uo): ?>
                    	<option value="<?php echo $uo->id; ?>"><?php echo $uo->name; ?></option>
                	<?php endforeach; ?></td>
                	</select>
                </td>
        </tr>
        <tr><td></td>
        <td>
             <button name "submit" >Update</button>
        </form>
             <?php echo "<button onclick=\"goBack()\" >Back</button>";?>
        </td>
        </tr>
             <?php echo form_hidden( 'scuo_id', $scuo_id );?>
     </table>
   </body>
   <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

