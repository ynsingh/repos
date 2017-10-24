<!--@name studycenteruo.php
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
 <script>
    </script>    
    </head>
    <body>
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
   <table style="padding: 8px 8px 8px 20px;">
     <tr colspan="2"><td>
      <div align=left">
        <font color=blue size=4pt>
         <?php
            echo anchor('map/viewscuo', 'List of Study Center with UO', array('class' => 'top_parent'));
         ?>
      </div>
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
        </div>
     </td></tr>
   </table>
   <div> 
   <form id="myform" action="<?php echo site_url('map/studycenteruo');?>" method="POST" class="form-inline">
   <table style="margin-left:30px;">
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
     </div>		
   </body>
  <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>
 
