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

	function getuoc(val){
		var val=val;
		//alert(val);
	   	$.ajax({
		type: "POST",
		url: "<?php echo base_url();?>sisindex.php/staffmgmt/getuoclist",
		data: {"campusname" : val},
		dataType:"html",
		success: function(data){
		$('#uo').html(data.replace(/^"|"$/g, ''));
		}
	     });
	   }

        function getschemename(val){
                var val=val;
	//	alert(val);
                $.ajax({
                type: "POST",
                url: "<?php echo base_url();?>sisindex.php/staffmgmt/getdeptscheme",
                data: {"deptid" : val},
                dataType:"html",
                success: function(data){
	//	alert(data);
                $('#schemecode').html(data.replace(/^"|"$/g, ''));
                }
             });
           }
	
	function getdesignation(val){
		var val=val;
	   	$.ajax({
		type: "POST",
		url: "<?php echo base_url();?>sisindex.php/staffmgmt/getdesiglist",
		data: {"group" : val},
		dataType:"html",
		success: function(data){
		$('#emppost').html(data.replace(/^"|"$/g, ''));
		}
	     });
	   }

	function workingtype(val){
		var val=val;
	   	$.ajax({
		type: "POST",
		url: "<?php echo base_url();?>sisindex.php/staffmgmt/getworkingtype",
		data: {"groupp" : val},
		dataType:"html",
		success: function(data){
	//	alert(data);
		$('#grouppost').html(data.replace(/^"|"$/g, ''));
		}
	     });
	   }

	function getemptype(val){
		var sc_code = $('#campus').val();
                var uoc_id = $('#uo').val();
                var dept_id = $('#dept').val();
                var sch_id = $('#schemecode').val();
                var group_id = $('#group').val();
                var tnt_id = $('#tnt').val();
                var emppost_id = $('#emppost').val();
		var type=val;
                var combid = sc_code+","+uoc_id+","+dept_id+","+sch_id+","+group_id+","+tnt_id+","+emppost_id+","+type;
          //      alert("combid=="+combid);
	   	$.ajax({
		type: "POST",
		url: "<?php echo base_url();?>sisindex.php/staffmgmt/getemptypevalue",
		data: {"emptype" : combid},
		dataType:"html",
		success: function(data){
	//	alert(data);
		var ssdata=data;
		var positiondata=ssdata.split(',');
			$('#p').val(positiondata[0].replace(/\"|"/g,''));
			$('#ss').val(positiondata[1].replace(/^"|"$/g, ''));
			$('#v').val(positiondata[2].replace(/^"|"$/g, ''));
			$('#ssper').val(positiondata[3].replace(/^"|"$/g, ''));
			$('#pper').val(positiondata[4].replace(/^"|"$/g, ''));
			$('#vper').val(positiondata[5].replace(/^"|"$/g, ''));
			$('#sstem').val(positiondata[6].replace(/^"|"$/g, ''));
			$('#ptem').val(positiondata[7].replace(/^"|"$/g, ''));
			$('#vtem').val(positiondata[8].replace(/^"|"$/g, ''));
		}
	     });
	   }
	
	function getsstypevalue(val){
		var p = $('#p').val();
                var type = $('#type').val();
		var ss=val;
                var combid = p+","+type+","+ss;
	   	$.ajax({
		type: "POST",
		url: "<?php echo base_url();?>sisindex.php/staffmgmt/getsstype",
		data: {"sstype" : combid},
		dataType:"html",
		success: function(data){
	//	alert(data);
		var ssdata=data;
		var positiondata=ssdata.split(',');
			$('#p').val(positiondata[0].replace(/\"|"/g,''));
			$('#v').val(positiondata[1].replace(/^"|"$/g, ''));
			$('#ssper').val(positiondata[2].replace(/^"|"$/g, ''));
			$('#pper').val(positiondata[3].replace(/^"|"$/g, ''));
			$('#vper').val(positiondata[4].replace(/^"|"$/g, ''));
			$('#sstem').val(positiondata[5].replace(/^"|"$/g, ''));
			$('#ptem').val(positiondata[6].replace(/^"|"$/g, ''));
			$('#vtem').val(positiondata[7].replace(/^"|"$/g, ''));
		}
	     });
	   }

	$(document).ready(function(){
                       
             $('#uo').on('change',function(){
                var sc_code = $('#campus').val();
                var uoc_id = $('#uo').val();
                var combid = sc_code+","+uoc_id;
                //alert("combid=="+combid);
                if(uoc_id == ''){
                    $('#dept').prop('disabled',true);
                }
                else{
             
                    $('#dept').prop('disabled',false);
                    $.ajax({
                        url: "<?php echo base_url();?>sisindex.php/staffmgmt/getnewdeptlist",
                        type: "POST",
                        data: {"campuoc" : combid},
                        dataType:"html",
                        success:function(data){
                            
                            $('#dept').html(data.replace(/^"|"$/g, ''));
                       
                        },
                        error:function(data){
                            //alert("data in error==="+data);
                            alert("error occur..!!");
                 
                        }
                                            
                    });
                }
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
		<td>Campus Name<font color='Red'> *</font> </td>
          	<td><select required name="campus" id="campus" class="my_dropdown" style="width:300px;" onchange="getuoc(this.value)" >
                <option value="" disabled selected >------Select----------------</option>
		<?php foreach($this->scresult as $datasp): ?>
                    <option value="<?php echo $datasp->sc_id; ?>"<?php echo set_select('campus', $datasp->sc_id); ?>><?php echo $datasp->sc_name; ?></option>
                <?php endforeach; ?> </td>
		<td>&nbsp; </td>
		<td>University Officer Control<font color='Red'> *</font> </td>
          	<td><select required name="uo" id="uo" class="my_dropdown" style="width:300px;">
                <option value="" disabled selected >------Select----------------</option>
		<td>&nbsp; </td>
		<td>Department Name<font color='Red'> *</font> </td>
          	<td><select required name="dept" id="dept" style="width:300px;" onchange="getschemename(this.value)" >
                <option disabled selected >------Select----------------</option>
		</td>
	</tr>	
	<tr>
		<td>Scheme Name <font color='Red'> *</font> </td>
          	<td><select required name="schemecode" id="schemecode" class="my_dropdown" style="width:300px;">
                <option disabled selected >------Select----------------</option></td>
		<td>&nbsp; </td>
		<td>Group<font color='Red'> *</font> </td>
		<td><select name="group" id="group" class="my_dropdown" style="width:300px;" onchange="getdesignation(this.value)" >
                <option value="" disabled selected >------Select----------------</option>
                <option value="A" <?php echo set_select('group', 'A'); ?>class="dropdown-item"> A </option>
                <option value="B" <?php echo set_select('group', 'B'); ?>class="dropdown-item"> B </option>
                <option value="C" <?php echo set_select('group', 'C'); ?>class="dropdown-item"> C </option>
                <option value="D" <?php echo set_select('group', 'D'); ?>class="dropdown-item"> D </option> <br></td>
		<td>&nbsp; </td>
		<td>Employee Post <font color='Red'> *</font> </td>
          	<td><select name="emppost" id="emppost" class="my_dropdown" style="width:300px;">
                <option value="" disabled selected >------Select----------------</option>
	</tr>	
	<tr>
		<td>Working Type <font color='Red'> *</font> </td>
          	<td><select name="tnt" id="tnt" class="my_dropdown" style="width:300px;" onchange="workingtype(this.value)">
                <option value="" disabled selected >------Select----------------</option>
                <option value="Teaching"<?php echo set_select('tnt', 'Teaching'); ?> class="dropdown-item"> Teaching </option>
                <option value="Non Teaching"<?php echo set_select('tnt','Non Teaching'); ?> class="dropdown-item"> Non Teaching </option></td>
		<td>&nbsp; </td>
		<td>Group Post <font color='Red'> *</font> </td>
		<td><select name="grouppost" id="grouppost" class="my_dropdown" style="width:300px;">
                <option value="" disabled selected >------Select-----------------</option>
	<!--    <option value="multitasking staff" <?php echo set_select('grouppost','multitasking staff'); ?> class="dropdown-item"> Multitasking staff </option>
                <option value="technical staff" <?php echo set_select('grouppost','technical staff'); ?> class="dropdown-item"> Technical staff </option>
                <option value="ministerial staff" <?php echo set_select('grouppost','ministerial staff'); ?> class="dropdown-item"> Ministerial staff </option>
                <option value="administrative staff" <?php echo set_select('grouppost','administrativ staff'); ?> class="dropdown-item"> Administrativ staff </option>
                <option value="officer" <?php echo set_select('grouppost','officer'); ?> class="dropdown-item"> Officer </option>
                <option value="technical officer" <?php echo set_select('grouppost','technical officer'); ?> class="dropdown-item"> Technical Officer </option>
                <option value="supporting staff" <?php echo set_select('grouppost','supporting staff'); ?> class="dropdown-item"> Supporting Staff </option>
                <option value="uo" <?php echo set_select('grouppost','uo'); ?> class="dropdown-item"> UO </option>
                <option value="professor" <?php echo set_select('grouppost','professor'); ?> class="dropdown-item"> Professor </option>
                <option value="associate professor" <?php echo set_select('grouppost','associate professor'); ?> class="dropdown-item"> Associate Professor </option>
                <option value="assistant professor" <?php echo set_select('grouppost','assistant professor'); ?> class="dropdown-item"> Assistant Professor </option>
                <option value="librarians " <?php echo set_select('grouppost','librarians'); ?> class="dropdown-item"> Librarians </option>
                <option value="physical director" <?php echo set_select('grouppost','physical director'); ?> class="dropdown-item"> Physical Director </option></td>
		-->
		<td>&nbsp; </td>
		<td>Employee Type<font color='Red'> *</font> </td>
          	<td><select name="type" id="type" class="my_dropdown" style="width:300px;" onchange="getemptype(this.value)" >
                <option value="" disabled selected >------Select----------------</option>
                <option value="Permanent"<?php echo set_select('type', 'Permanent'); ?> class="dropdown-item"> Permanent </option>
                <option value="Temporary"<?php echo set_select('type', 'Temporary'); ?> class="dropdown-item"> Temporary </option></td>
	</tr>	
	<tr>
		<td>Plan / Non Plan<font color='Red'> *</font> </td>
		<td><select name="pnp" id="" class="my_dropdown" style="width:300px;">
                <option value="" disabled selected >------Select-----------------</option>
                <option value="Plan" <?php echo set_select('pnp', 'Plan'); ?>class="dropdown-item"> Plan </option>
                <option value="Non Plan"<?php echo set_select('pnp', 'Non Plan'); ?> class="dropdown-item"> Non Plan </option> 
                <option value="GOI"<?php echo set_select('pnp', 'GOI'); ?> class="dropdown-item"> GOI </option> 
                <option value="ICAR"<?php echo set_select('pnp', 'ICAR'); ?> class="dropdown-item"> ICAR </option> <br></td>
		<td>&nbsp; </td>
		<td>Pay Band <font color='Red'> *</font> </td>
		<td><select name="scale" id="" class="my_dropdown" style="width:300px;">
                  <option selected="selected" disabled selected>--------Select-------------</option>
                    <?php foreach($this->salgrd as $salgrddata): ?>
                         <option value="<?php echo $salgrddata->sgm_name."(". $salgrddata->sgm_min."-".$salgrddata->sgm_max.")".$salgrddata->sgm_gradepay; ?>"<?php echo set_select('scale', $salgrddata->sgm_name."(". $salgrddata->sgm_min."-".$salgrddata->sgm_max.")".$salgrddata->sgm_gradepay);?>><?php echo $salgrddata->sgm_name."(". $salgrddata->sgm_min."-".$salgrddata->sgm_max.")".$salgrddata->sgm_gradepay; ?>
                          </option>
                  <?php endforeach; ?>
		</select></td>
		<td>&nbsp; </td>
		<td>Method of Recruitment<font color='Red'> *</font> </td>
		<td><select name="methodrect" id="" class="my_dropdown" style="width:300px;">
                <option value="" disabled selected >------Select-----------------</option>
                <option value="direct"<?php echo set_select('methodrect', 'direct'); ?> class="dropdown-item"> Direct </option> 
                <option value="Promotion"<?php echo set_select('methodrect', 'Promotion'); ?> class="dropdown-item"> Promotion </option><br>
                <option value="Deputation"<?php echo set_select('methodrect', 'Deputation'); ?> class="dropdown-item"> Deputation </option> <br></td>
	</tr>	
	<tr>
		<td>Position Sanction Strength <font color='Red'>*</font> </td>
		<td><input type="text" name="ss" id="ss" class="keyup-numeric" size="26" value="<?php echo isset($_POST["ss"]) ? $_POST["ss"] : ''; ?>" placeholder="Position Sanction Strength..." required="required" oninput="getsstypevalue(this.value)" /><br></td>
		<td>&nbsp; </td>
		<td>Position Present<font color='Red'> *</font> </td>
		<td><input type="text" name="p" id="p" readonly size="26" value="<?php echo isset($_POST["p"]) ? $_POST["p"] : ''; ?>" placeholder="Position Present..." required="required" /><br></td>
		<td>&nbsp; </td>
		<td>Position Vacant<font color='Red'> * </font></td>
		<td><input type="text" name="v" id="v" readonly size="26" value="<?php echo isset($_POST["v"]) ? $_POST["v"] : ''; ?>" placeholder="Position Vacant..." required="required" /><br></td>
	</tr>
           
         <tr>
		<td>Sanction Strength Permanent <font color='Red'>*</font> </td>
		<td><input type="text" name="ssper" id="ssper" readonly size="26" value="<?php echo isset($_POST["ssper"]) ? $_POST["ssper"] : ''; ?>" placeholder="Sanction Strength Permanent..." required="required" /><br></td>
		<td>&nbsp; </td>
		<td>Position Permanent <font color='Red'>*</font> </td>
		<td><input type="text" name="pper" id="pper" readonly size="26" value="<?php echo isset($_POST["pper"]) ? $_POST["pper"] : ''; ?>" placeholder="Position Permanent..." required="required" /><br></td>
		<td>&nbsp; </td>
		<td>Vacancy Permanent <font color='Red'>* </font></td>
		<td><input type="text" name="vper" id="vper" readonly size="26" value="<?php echo isset($_POST["vper"]) ? $_POST["vper"] : ''; ?>" placeholder="Vacancy Permanent..." required="required" /><br></td>
	</tr>
           

<tr>
		<td>Sanction Strength Temporary <font color='Red'>*</font> </td>
		<td><input type="text" name="sstem" id="sstem" readonly size="26" value="<?php echo isset($_POST["sstem"]) ? $_POST["sstem"] : ''; ?>" placeholder="Sanction Strength Temporary..." required="required" /><br></td>
		<td>&nbsp; </td>
		<td>Position Temporary<font color='Red'> *</font> </td>
		<td><input type="text" name="ptem" id="ptem" readonly size="26" value="<?php echo isset($_POST["ptem"]) ? $_POST["ptem"] : ''; ?>" placeholder="Position Temporary..." required="required" /><br></td>
		<td>&nbsp; </td>
		<td>Vacancy Temporary<font color='Red'> *</font> </td>
		<td><input type="text" name="vtem" id="vtem" readonly size="26" value="<?php echo isset($_POST["vtem"]) ? $_POST["vtem"] : ''; ?>" placeholder="Vacancy Temporary..." required="required" /><br></td>
	</tr> 

      <tr>
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
 
