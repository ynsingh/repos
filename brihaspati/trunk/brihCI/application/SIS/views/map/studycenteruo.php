<!--@name studycenteruo.php
    @author Om Prakash(omprakashkgp@gmail.com)
 -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
    <head>    
        <?php $this->load->view('template/header'); ?>
            
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
    </head>
    <body>
    <table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
    <script>
	function getdepartment(val){
		var val=val;
	   	$.ajax({
		type: "POST",
		url: "<?php echo base_url();?>sisindex.php/map/getdeptlist",
		data: {"campusname" : val},
		dataType:"html",
		success: function(data){
		$('#deptname').html(data.replace(/^"|"$/g, ''));
		}
	     });
	   }
   </script>	
   <table width="100%">
     <tr colspan="2"><td>
         <?php
            echo anchor('map/viewscuo', 'List of Study Center with UO', array('class' => 'top_parent'));
         ?>
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
   <div> 
   <form id="myform" action="<?php echo site_url('map/studycenteruo');?>" method="POST" class="form-inline">
   <table>
 	<tr>
            <td>Campus Name :</td>
            <td>
            	<select name="campusname" id="campusname" class="my_dropdown" style="width:300px;" onchange="getdepartment(this.value)">
                <option value="" disabled selected >------Select Campus Name---------------</option>
		<?php foreach($this->scresult as $dataspt): ?>
		<option value="<?php echo $dataspt->sc_id; ?>"><?php echo $dataspt->sc_name; ?></option>
                <?php endforeach; ?>
           </td>
      </tr>
      <tr>
           <td>Authority Name :</td>
           <td>
           	<select name="authority" id="authority" class="my_dropdown" style="width:300px;" onchange="getbranchname(this.value)" >
                <option value="" disabled selected >------Select Authority Name--------------</option>
                <?php foreach($this->authresult as $data): ?>
                <option value="<?php echo $data->id; ?>"><?php echo $data->name; ?></option>
		<?php endforeach; ?>
	   </td>
       </tr>
        <tr>
	    <td></td>
            <td>
                <button name="studycenteruo" >Submit</button>
                <button name="clear" >Clear</button>
            </td>
	    <td></td>
          </tr>
        </table>
      </form>
     <p><br></p>
     </div>		
   </body>
<p> &nbsp;</p>
  <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>
 
