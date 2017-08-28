<!--@name newstaffposition.php
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
	function getdepartment(val){
		var val=val;
	   	$.ajax({
		type: "POST",
		url: "<?php echo base_url();?>sisindex.php/staffmgmt/getdeptlist",
		data: {"campusname" : val},
		dataType:"html",
		success: function(data){
		$('#dept').html(data.replace(/^"|"$/g, ''));
		}
	     });
	   }
	
	function getschemename(val){
		var val=val;
	   	$.ajax({
		type: "POST",
		url: "<?php echo base_url();?>sisindex.php/staffmgmt/getdeptschemelist",
		data: {"deptid" : val},
		dataType:"html",
		success: function(data){
		$('#schemecode').html(data.replace(/^"|"$/g, ''));
		}
	     });
	   }
	
	$(document).ready(function(){

            $("#v").click(function(){
                var val1=parseInt($("#ss").val());
                var val2=parseInt($("#p").val());
                
                $("#v").val(val1 - val2);
            });

            $("#vper").click(function(){
                var val1=parseInt($("#ssper").val());
                var val2=parseInt($("#pper").val());
                
                $("#vper").val(val1 - val2);
            });

            $("#sstem").click(function(){
                var val1=parseInt($("#ss").val());
                var val2=parseInt($("#ssper").val());
                
                $("#sstem").val(val1 - val2);
            });

            $("#ptem").click(function(){
                var val1=parseInt($("#p").val());
                var val2=parseInt($("#pper").val());
                
                $("#ptem").val(val1 - val2);
            });
      
	  $("#vtem").click(function(){
                var val1=parseInt($("#v").val());
                var val2=parseInt($("#vper").val());
                
                $("#vtem").val(val1 - val2);
            });
          
        });

   </script>	
   <table style="padding: 8px 8px 8px 20px;">
     <tr colspan="2"><td>
      <div align=left">
        <font color=blue size=4pt>
         <?php
            echo anchor('staffmgmt/staffposition', 'View Staff Position', array('class' => 'top_parent'));
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
   <form id="myform" action="<?php echo site_url('staffmgmt/newstaffposition');?>" method="POST" class="form-inline">
   <table style="margin-left:30px;">
	<tr>
		<td>Campus Name </td>
          	<td><select name="campus" id="campus" class="my_dropdown" style="width:300px;" onchange="getdepartment(this.value)">
                <option value="" disabled selected >------Select----------------</option>
		<?php foreach($this->scresult as $datasp): ?>
                    <option value="<?php echo $datasp->sc_id; ?>"><?php echo $datasp->sc_name; ?></option>
                <?php endforeach; ?> </td>
		<td>&nbsp; </td>
		<td>University Officer Control </td>
          	<td><select name="uo" id="" class="my_dropdown" style="width:300px;">
                <option value="" disabled selected >------Select----------------</option>
		<?php foreach($this->authorty as $uo): ?>
                    <option value="<?php echo $uo->id; ?>"><?php echo $uo->name; ?></option>
                <?php endforeach; ?></td>
		<td>&nbsp; </td>
		<td>Department Name </td>
          	<td><select name="dept" id="dept" class="my_dropdown" style="width:300px;" onchange="getschemename(this.value)" >
                <option value="" disabled selected >------Select----------------</option>
	</tr>	
	<tr>
		<td>Scheme Name  </td>
          	<td><select name="schemecode" id="schemecode" class="my_dropdown" style="width:300px;">
                <option value="" disabled selected >------Select----------------</option></td>
		<td>&nbsp; </td>
		<td>Plan / Non Plan </td>
		<td><select name="pnp" id="" class="my_dropdown" style="width:300px;">
                <option value="" disabled selected >------Select-----------------</option>
                <option value="Plan" class="dropdown-item"> Plan </option>
                <option value="Non Plan" class="dropdown-item"> Non Plan </option> <br></td>
		<td>&nbsp; </td>
		<td>GROUP </td>
		<td><select name="group" id="" class="my_dropdown" style="width:300px;">
                <option value="" disabled selected >------Select----------------</option>
                <option value="A" class="dropdown-item"> A </option>
                <option value="B" class="dropdown-item"> B </option>
                <option value="C" class="dropdown-item"> C </option>
                <option value="D" class="dropdown-item"> D </option>
                <option value="E" class="dropdown-item"> E </option> <br></td>
	</tr>	
	<tr>
		<td>Working Type  </td>
          	<td><select name="tnt" id="" class="my_dropdown" style="width:300px;">
                <option value="" disabled selected >------Select----------------</option>
                <option value="Teaching" class="dropdown-item"> Teaching </option>
                <option value="Non Teaching" class="dropdown-item"> Non Teaching </option></td>
		<td>&nbsp; </td>
		<td>Employee TYPE  </td>
          	<td><select name="type" id="" class="my_dropdown" style="width:300px;">
                <option value="" disabled selected >------Select----------------</option>
                <option value="Permanent" class="dropdown-item"> Permanent </option>
                <option value="Temporary" class="dropdown-item"> Temporary </option></td>
		<td>&nbsp; </td>
		<td>Employee Post  </td>
          	<td><select name="emppost" id="" class="my_dropdown" style="width:300px;">
                <option value="" disabled selected >------Select----------------</option>
		<?php foreach($this->desigresult as $dataspd): ?>
                    <option value="<?php echo $dataspd->desig_id; ?>"><?php echo $dataspd->desig_name; ?></option>
                <?php endforeach; ?>
	</tr>	
	<tr>
		<td>Group Post </td>
		<td><select name="grouppost" id="" class="my_dropdown" style="width:300px;">
                <option value="" disabled selected >------Select-----------------</option>
                <option value="multitasking staff" class="dropdown-item"> Multitasking staff </option></td>
		<td>&nbsp; </td>
		<td>Pay Band </td>
		<td><select name="scale" id="" class="my_dropdown" style="width:300px;">
                <option value="" disabled selected >------Select-----------------</option>
                <option value="PB1(15000-60000)4200" class="dropdown-item"> PB1(15000-60000)4200 </option>
                <option value="PB2(15000-60000)4600" class="dropdown-item"> PB2(15000-60000)4600 </option> <br>
                <option value="PB3(15000-60000)4800" class="dropdown-item"> PB3(15000-60000)4800 </option> <br></td>
		<td>&nbsp; </td>
		<td>Method of Recruitment </td>
		<td><select name="methodrect" id="" class="my_dropdown" style="width:300px;">
                <option value="" disabled selected >------Select-----------------</option>
                <option value="direct" class="dropdown-item"> Direct </option> 
                <option value="Promotion" class="dropdown-item"> Promotion </option><br>
                <option value="Deputation" class="dropdown-item"> Deputation </option> <br></td>
	</tr>	
	<tr>
		<td>Position Sanction Strength </td>
		<td><input type="text" name="ss" id="ss" class="form-control" size="26" /><br></td>
		<td>&nbsp; </td>
		<td>Position Present </td>
		<td><input type="text" name="p" id="p" class="form-control" size="26" /><br></td>
		<td>&nbsp; </td>
		<td>Position Vacant </td>
		<td><input type="text" name="v" id="v" class="form-control" size="26" /><br></td>
	<tr><tr>
		<td>Sanction Strength Permanent </td>
		<td><input type="text" name="ssper" id="ssper" class="form-control" size="26" /><br></td>
		<td>&nbsp; </td>
		<td>Position Permanent </td>
		<td><input type="text" name="pper" id="pper" class="form-control" size="26" /><br></td>
		<td>&nbsp; </td>
		<td>Vacancy Permanent </td>
		<td><input type="text" name="vper" id="vper" class="form-control" size="26" /><br></td>
	</tr><tr>
		<td>Sanction Strength Temporary </td>
		<td><input type="text" name="sstem" id="sstem" class="form-control" size="26" /><br></td>
		<td>&nbsp; </td>
		<td>Position Temporary </td>
		<td><input type="text" name="ptem" id="ptem" class="form-control" size="26" /><br></td>
		<td>&nbsp; </td>
		<td>Vacancy Temporary </td>
		<td><input type="text" name="vtem" id="vtem" class="form-control" size="26" /><br></td>
	</tr><tr>
		<td>Address </td>
		<td><input type="text" name="address1" class="form-control" size="26" /><br></td>
		<td>&nbsp; </td>
		<td>Sanction Strength Detail </td>
		<td><input type="text" name="ssdetail" class="form-control" size="26" /><br></td>
		<td>&nbsp; </td>
		<td>Remarks  </td>
		<td><input type="text" name="remarks" class="form-control" size="26" /><br></td>
	</tr>	
        <tr>
	 	<td></td>
            <td>
                <button name="newstaffposition" >Submit</button>
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
 
