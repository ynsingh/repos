<!--@name newddo.php
    @author Om Prakash(omprakashkgp@gmail.com)
	  and Modification according to TANUVAS in Dec-2017
 -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
    <head>    
        <?php $this->load->view('template/header'); ?>
       
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
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
 
        function getschemename(val){
                var val=val;
                $.ajax({
                type: "POST",
                url: "<?php echo base_url();?>sisindex.php/map/getdeptschemelist",
                data: {"deptid" : val},
                dataType:"html",
                success: function(data){
                $('#schemecode').html(data.replace(/^"|"$/g, ''));
                }
             });
           }
    </script>	
   <table width="100%">
     <tr><td>
         <?php
          echo anchor('setup/listddo/', 'List of DDO', array('class' => 'top_parent'));
         ?>
          <div align="left" style="margin-left:0%;width:95%;">
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
    <form id="myform" action="<?php echo site_url('setup/newddo');?>" method="POST" class="form-inline">
    <table>
      <tr>
          <td>Campus Name :</td>
          <td>
               <select required name="campusname" id="campusname" class="my_dropdown" style="width:300px;" onchange="getdepartment(this.value)">
               <option value="" disabled selected >------Select ---------------</option>
		<?php foreach($this->scresult as $dataspt): ?>
	       <option value="<?php echo $dataspt->sc_id; ?>"><?php echo $dataspt->sc_name; ?></option>
                <?php endforeach; ?>
          </td>
         <!-- <td><?php //echo form_error('campusname')?></td>-->
      </tr>
      <tr>
          <td>Department Name :</td>
          <td>
               <select name="deptname" id="deptname" class="my_dropdown" style="width:300px;" onchange="getschemename(this.value)" >
               <option value="" disabled selected >------Select ---------------</option>
          </td>
          <!--<td><?php //echo form_error('deptname')?></td>-->
      </tr>
      <tr>
          <td>Scheme Name :</td>
          <td>
               <select name="schemecode" id="schemecode" class="my_dropdown" style="width:300px;" >
               <option value="" disabled selected >------Select --------------</option>
          </td>
	  <!--<td><?php //echo form_error('schemecode')?></td>-->	
      </tr>
      <tr>
	  <td>DDO Code :</td>
	  <td>
		<input type="text" name="ddocode"  size="26" value="<?php echo isset($_POST["ddocode"]) ? $_POST["ddocode"] : ''; ?>" placeholder="DDO Code..." required="required" /> <br>
	  </td>
          <!--<td><?php //echo form_error('ddocode')?></td>-->
          <td> Example : C6208 </td>
      </tr>
      <tr>
	  <td>DDO Name :</td>
	  <td>
		<input type="text" name="ddoname"  size="26" value="<?php echo isset($_POST["ddoname"]) ? $_POST["ddoname"] : ''; ?>" placeholder="DDO Name..." required="required" /> <br>
	  </td>
        <!--  <td><?php //echo form_error('ddoname')?></td>-->
          <td> Example : Om Prakash </td>
      </tr>
      <tr>
	  <td>Remark :</td>
	  <td>
		<input type="text" name="remark"  size="26" value="<?php echo isset($_POST["remark"]) ? $_POST["remark"] : ''; ?>" placeholder="Remark..." /> <br>
	  </td>	
      </tr>
      <tr>
	  <td></td>
          <td>
                <button name="newddo" >Submit</button>
		<input type="reset" name="Reset" value="Clear"/>
          </td>
	  <td></td>
      </tr>
    </table>
    </form>
 <p><br></p>
   </body>
<p>&nbsp;</p>
  <div align="center"><?php $this->load->view('template/footer'); ?></div>
</html>
