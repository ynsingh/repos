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

          $('.keyup-numeric').keyup(function() {
                $('span.error-keyup-1').hide();
                var input = $(this).val();
                var numeric = /^\d*[0-9](|.\d*[0-9]|,\d*[0-9])?$/;
                if(!numeric.test(input)) {
                    $(this).after('<span class="error error-keyup-1"><br><font color="red">Numeric characters only.</font></span>');
                }
            });
   
      });	

   </script>	
   <table style="padding: 8px 8px 8px 20px;">
     <tr colspan="2"><td>
      <div align=left">
        <font color=blue size=4pt>
         <?php
            echo anchor('staffmgmt/staffposition', 'View Staff Position', array('class' => 'top_parent'));
	    $help_uri = site_url()."/help/helpdoc#StaffPosition";
            echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:70%\">Click for Help</b></a>";
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
          	<td><select required name="campus" id="campus" class="my_dropdown" style="width:300px;" onchange="getdepartment(this.value)">
                <option value="" disabled selected >------Select----------------</option>
		<?php foreach($this->scresult as $datasp): ?>
                    <option value="<?php echo $datasp->sc_id; ?>"<?php echo set_select('campus', $datasp->sc_id); ?>><?php echo $datasp->sc_name; ?></option>
                <?php endforeach; ?> </td>
		<td>&nbsp; </td>
		<td>University Officer Control </td>
          	<td><select required name="uo" id="uo" class="my_dropdown" style="width:300px;">
                <option value="" disabled selected >------Select----------------</option>
		<?php foreach($this->authorty as $uo): ?>
                    <option value="<?php echo $uo->id; ?>"<?php echo set_select('uo', $uo->id);?>><?php echo $uo->name; ?></option>
                <?php endforeach; ?></td>
		<td>&nbsp; </td>
		<td>Department Name </td>
          	<td><select required name="dept" id="dept" style="width:300px;" onchange="getschemename(this.value)" >
                <option disabled selected >------Select----------------</option>
		</td>
	</tr>	
	<tr>
		<td>Scheme Name  </td>
          	<td><select required name="schemecode" id="schemecode" class="my_dropdown" style="width:300px;">
                <option disabled selected >------Select----------------</option></td>
		<td>&nbsp; </td>
		<td>Plan / Non Plan </td>
		<td><select name="pnp" id="" class="my_dropdown" style="width:300px;">
                <option value="" disabled selected >------Select-----------------</option>
                <option value="Plan" <?php echo set_select('pnp', 'Plan'); ?>class="dropdown-item"> Plan </option>
                <option value="Non Plan"<?php echo set_select('pnp', 'Non Plan'); ?> class="dropdown-item"> Non Plan </option> <br></td>
		<td>&nbsp; </td>
		<td>GROUP </td>
		<td><select name="group" id="" class="my_dropdown" style="width:300px;">
                <option value="" disabled selected >------Select----------------</option>
                <option value="A" <?php echo set_select('group', 'A'); ?>class="dropdown-item"> A </option>
                <option value="B" <?php echo set_select('group', 'B'); ?>class="dropdown-item"> B </option>
                <option value="C" <?php echo set_select('group', 'C'); ?>class="dropdown-item"> C </option>
                <option value="D" <?php echo set_select('group', 'D'); ?> class="dropdown-item"> D </option>
                <option value="E" <?php echo set_select('group', 'E'); ?>class="dropdown-item"> E </option> <br></td>
	</tr>	
	<tr>
		<td>Working Type  </td>
          	<td><select name="tnt" id="" class="my_dropdown" style="width:300px;">
                <option value="" disabled selected >------Select----------------</option>
                <option value="Teaching"<?php echo set_select('tnt', 'Teaching'); ?> class="dropdown-item"> Teaching </option>
                <option value="Non Teaching"<?php echo set_select('tnt','Non Teaching'); ?> class="dropdown-item"> Non Teaching </option></td>
		<td>&nbsp; </td>
		<td>Employee TYPE  </td>
          	<td><select name="type" id="" class="my_dropdown" style="width:300px;">
                <option value="" disabled selected >------Select----------------</option>
                <option value="Permanent"<?php echo set_select('type', 'Permanent'); ?> class="dropdown-item"> Permanent </option>
                <option value="Temporary"<?php echo set_select('type', 'Temporary'); ?> class="dropdown-item"> Temporary </option></td>
		<td>&nbsp; </td>
		<td>Employee Post  </td>
          	<td><select name="emppost" id="" class="my_dropdown" style="width:300px;">
                <option value="" disabled selected >------Select----------------</option>
		<?php foreach($this->desigresult as $dataspd): ?>
                    <option value="<?php echo $dataspd->desig_id; ?>"<?php echo set_select('emppost', $dataspd->desig_id);?>><?php echo $dataspd->desig_name; ?></option>
                <?php endforeach; ?>
	</tr>	
	<tr>
		<td>Group Post </td>
		<td><select name="grouppost" id="" class="my_dropdown" style="width:300px;">
                <option value="" disabled selected >------Select-----------------</option>
                <option value="multitasking staff" <?php echo set_select('grouppost','multitasking staff'); ?> class="dropdown-item"> Multitasking staff </option></td>
		<td>&nbsp; </td>
		<td>Pay Band </td>
		<td><select name="scale" id="" class="my_dropdown" style="width:300px;">
                  <option selected="selected" disabled selected>--------Select-------------</option>
                    <?php foreach($this->salgrd as $salgrddata): ?>
                         <option value="<?php echo $salgrddata->sgm_name."(". $salgrddata->sgm_min."-".$salgrddata->sgm_max.")".$salgrddata->sgm_gradepay; ?>"<?php echo set_select('scale', $salgrddata->sgm_name."(". $salgrddata->sgm_min."-".$salgrddata->sgm_max.")".$salgrddata->sgm_gradepay);?>><?php echo $salgrddata->sgm_name."(". $salgrddata->sgm_min."-".$salgrddata->sgm_max.")".$salgrddata->sgm_gradepay; ?>
                          </option>
                  <?php endforeach; ?>
		</select></td>
		<td>&nbsp; </td>
		<td>Method of Recruitment </td>
		<td><select name="methodrect" id="" class="my_dropdown" style="width:300px;">
                <option value="" disabled selected >------Select-----------------</option>
                <option value="direct"<?php echo set_select('methodrect', 'direct'); ?> class="dropdown-item"> Direct </option> 
                <option value="Promotion"<?php echo set_select('methodrect', 'Promotion'); ?> class="dropdown-item"> Promotion </option><br>
                <option value="Deputation"<?php echo set_select('methodrect', 'Deputation'); ?> class="dropdown-item"> Deputation </option> <br></td>
	</tr>	
	<tr>
		<td>Position Sanction Strength <font color='Red'>*</font> </td>
		<td><input type="text" name="ss" id="ss" class="keyup-numeric" size="26" value="<?php echo isset($_POST["ss"]) ? $_POST["ss"] : ''; ?>" placeholder="Position Sanction Strength..." required="required" /><br></td>
		<td>&nbsp; </td>
		<td>Position Present<font color='Red'> *</font> </td>
		<td><input type="text" name="p" id="p" class="keyup-numeric" size="26" value="<?php echo isset($_POST["p"]) ? $_POST["p"] : ''; ?>" placeholder="Position Present..." required="required" /><br></td>
		<td>&nbsp; </td>
		<td>Position Vacant<font color='Red'> * </font></td>
		<td><input type="text" name="v" id="v" class="keyup-numeric" size="26" value="<?php echo isset($_POST["v"]) ? $_POST["v"] : ''; ?>" placeholder="Position Vacant..." required="required" /><br></td>
	<tr><tr>
		<td>Sanction Strength Permanent <font color='Red'>*</font> </td>
		<td><input type="text" name="ssper" id="ssper" class="keyup-numeric" size="26" value="<?php echo isset($_POST["ssper"]) ? $_POST["ssper"] : ''; ?>" placeholder="Sanction Strength Permanent..." required="required" /><br></td>
		<td>&nbsp; </td>
		<td>Position Permanent <font color='Red'>*</font> </td>
		<td><input type="text" name="pper" id="pper" class="keyup-numeric" size="26" value="<?php echo isset($_POST["pper"]) ? $_POST["pper"] : ''; ?>" placeholder="Position Permanent..." required="required" /><br></td>
		<td>&nbsp; </td>
		<td>Vacancy Permanent <font color='Red'>* </font></td>
		<td><input type="text" name="vper" id="vper" class="keyup-numeric" size="26" value="<?php echo isset($_POST["vper"]) ? $_POST["vper"] : ''; ?>" placeholder="Vacancy Permanent..." required="required" /><br></td>
	</tr><tr>
		<td>Sanction Strength Temporary <font color='Red'>*</font> </td>
		<td><input type="text" name="sstem" id="sstem" class="keyup-numeric" size="26" value="<?php echo isset($_POST["sstem"]) ? $_POST["sstem"] : ''; ?>" placeholder="Sanction Strength Temporary..." required="required" /><br></td>
		<td>&nbsp; </td>
		<td>Position Temporary<font color='Red'> *</font> </td>
		<td><input type="text" name="ptem" id="ptem" class="keyup-numeric" size="26" value="<?php echo isset($_POST["ptem"]) ? $_POST["ptem"] : ''; ?>" placeholder="Position Temporary..." required="required" /><br></td>
		<td>&nbsp; </td>
		<td>Vacancy Temporary<font color='Red'> *</font> </td>
		<td><input type="text" name="vtem" id="vtem" class="keyup-numeric" size="26" value="<?php echo isset($_POST["vtem"]) ? $_POST["vtem"] : ''; ?>" placeholder="Vacancy Temporary..." required="required" /><br></td>
	</tr><tr>
		<td>Address </td>
		<td><input type="text" name="address1" class="form-control" size="26" value="<?php echo isset($_POST["address1"]) ? $_POST["address1"] : ''; ?>" placeholder="Address..." /><br></td>
		<td>&nbsp; </td>
		<td>Sanction Strength Detail </td>
		<td><input type="text" name="ssdetail" class="form-control" size="26" value="<?php echo isset($_POST["ssdetail"]) ? $_POST["ssdetail"] : ''; ?>" placeholder="Sanction Strength Detail..." /><br></td>
		<td>&nbsp; </td>
		<td>Remarks  </td>
		<td><input type="text" name="remarks" class="form-control" size="26" value="<?php echo isset($_POST["remarks"]) ? $_POST["remarks"] : ''; ?>" placeholder="Remarks..." /><br></td>
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
 
