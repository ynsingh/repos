<!--@name newstaffposition.php
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
	
/*	function getdesignation(val){
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
*/
	function getdesig(val){
		var val=val;
		var wt = $('#tnt').val();
		var combid = val+","+wt;
		$.ajax({
			type:"POST",
			url: "<?php echo base_url();?>sisindex.php/jslist/getgwdesiglist",
			data: {"gwt" : combid},
			dataType:"html",
			success:function(data){
				$('#emppost').html(data.replace(/^"|"$/g, ''));
			}
		});
	}
	function getpayband(val){
		var desig=val;	
		var wt = $('#tnt').val();
		var group_id = $('#group').val();
		var combid =group_id+","+wt+","+desig;
		$.ajax({
                        type:"POST",
			url: "<?php echo base_url();?>sisindex.php/jslist/getgwdesigpaylist",
                        data: {"gwtdesig" : combid},
                        dataType:"html",
                        success:function(data){
                                $('#scale').html(data.replace(/^"|"$/g, ''));
                        }
                });
        }

		
/*	
	function getdesignpayband(val){
		var val=val;
	   	$.ajax({
		type: "POST",
		url: "<?php echo base_url();?>sisindex.php/staffmgmt/getdesigpaybandlist",
		data: {"group" : val},
		dataType:"html",
		success: function(data){
	//	alert(data);
		var pdata=data;
		var paydata=pdata.split(',');
			$('#emppost').html(paydata[0].replace(/\"|"/g,''));
			$('#scale').html(paydata[1].replace(/^"|"$/g, ''));
		}
	     });
	   }
*/
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

	function getsstypevalueper(val){
                var p = $('#p').val();
                var type = $('#type').val();
               // var ss=val;
                var ss = $('#ss').val();
                var ssper = $('#ssper').val();
                var pper = $('#pper').val();
                var combid = p+","+type+","+ss+","+ssper+","+pper;
                $.ajax({
                type: "POST",
                url: "<?php echo base_url();?>sisindex.php/staffmgmt/getsstypeper",
                data: {"sstype" : combid},
                dataType:"html",
                success: function(data){
        //      alert(data);
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
    <table width="100%">
   <!-- <table style="padding: 8px 8px 8px 20px;">-->
     <tr><td>
         <?php
            echo anchor('staffmgmt/staffposition', 'View Staff Position', array('class' => 'top_parent'));
	    echo "<td align=\"right\">";
	    $help_uri = site_url()."/help/helpdoc#StaffPosition";
            echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
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
   <form id="myform" action="<?php echo site_url('staffmgmt/newstaffposition');?>" method="POST" class="form-inline">
   <table style="width:100%; border:1px solid gray;" align="center" class="TFtable">
		<tr><thead><th  style="background-color:#2a8fcf;text-align:left;height:40px;" colspan="3">&nbsp;&nbsp;Staff Position Form</th></thead></tr>
   <!--<table style="margin-left:30px;"> -->
	<tr>
		<td><label for="campus" style="font-size:15px;"><font color='blue'>Campus Name</font><font color='Red'>*</font></label> 
          		<div><select required name="campus" id="campus" class="my_dropdown" style="width:338px;" onchange="getuoc(this.value)" >
                	<option value="" disabled selected >------Select----------------</option>
			<?php foreach($this->scresult as $datasp): ?>
                    	<option value="<?php echo $datasp->sc_id; ?>"<?php echo set_select('campus', $datasp->sc_id); ?>><?php echo $datasp->sc_name ."( ". $datasp->sc_code." )"; ?></option>
                	<?php endforeach; ?>
			</div>
		</td>
	<td><label for="uo" style="font-size:15px;"><font color='blue'>University Officer Control</font><font color='Red'> *</font></label>
          		<div><select required name="uo" id="uo" class="my_dropdown" style="width:338px;" >
                	<option value="" disabled selected >------Select----------------</option>
			</div>
		</td>
		<td><label for="dept" style="font-size:15px;"><font color='blue'>Department Name </font><font color='Red'> *</font></label>
          		<div><select required name="dept" id="dept" style="width:338px;" onchange="getschemename(this.value)" >
                	<option disabled selected >------Select----------------</option>
			</div>
		</td>
	</tr>
	
	<tr>
		<td><label for="schemecode" style="font-size:15px;"> <font color='blue'>Scheme Name</font> <font color='Red'> *</font> </label>
          	     <div><select required name="schemecode" id="schemecode" class="my_dropdown" style="width:338px;">
                	<option disabled selected >------Select----------------</option>
		     </div>
		</td>
		
		<td><label for="tnt" style="font-size:15px;"><font color='blue'> Working Type </font><font color='Red'> *</font> </label>
          	   <div><select name="tnt" id="tnt" class="my_dropdown" style="width:338px;" onchange="workingtype(this.value)">
               		<option value="" disabled selected >------Select----------------</option>
                	<option value="Teaching"<?php echo set_select('tnt', 'Teaching'); ?> class="dropdown-item"> Teaching </option>
                	<option value="Non Teaching"<?php echo set_select('tnt','Non Teaching'); ?> class="dropdown-item"> Non Teaching </option>
	           </div>
		</td>
		<td><label for="group" style="font-size:15px;"><font color='blue'> Group</font><font color='Red'> *</font> </label>
		    <!-- <div><select name="group" id="group" class="my_dropdown" style="width:338px;" onchange="getdesignpayband(this.value)" > -->
		    <div><select name="group" id="group" class="my_dropdown" style="width:338px;" onchange="getdesig(this.value)" >
                         <option value="" disabled selected >------Select----------------</option>
                         <option value="A" <?php echo set_select('group', 'A'); ?>class="dropdown-item"> A </option>
                         <option value="B" <?php echo set_select('group', 'B'); ?>class="dropdown-item"> B </option>
                         <option value="C" <?php echo set_select('group', 'C'); ?>class="dropdown-item"> C </option>
                         <option value="D" <?php echo set_select('group', 'D'); ?>class="dropdown-item"> D </option> 
		    </div>
		</td>
		
	</tr>	
	<tr>
		<td><label for="emppost" style="font-size:15px;"><font color='blue'>Employee Post</font> <font color='Red'> *</font> </label>
          	    <div><select name="emppost" id="emppost" class="my_dropdown" style="width:338px;" onchange="getpayband(this.value)">
                         <option value="" disabled selected >------Select----------------</option>
		    <div>
		</td>
			
		<td><label for="grouppost" style="font-size:15px;"><font color='blue'> Group Post</font> <font color='Red'> *</font> </label>
			<div><select name="grouppost" id="grouppost" class="my_dropdown" style="width:338px;">
               		 <option value="" disabled selected >------Select-----------------</option>
		    </div>
                </td>
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
		
		<td><label for="type" style="font-size:15px;"><font color='blue'> Post Type</font><font color='Red'> *</font> </label>
          	    <div><select name="type" id="type" class="my_dropdown" style="width:338px;" onchange="getemptype(this.value)" >
               		 <option value="" disabled selected >------Select----------------</option>
               		 <option value="Permanent"<?php echo set_select('type', 'Permanent'); ?> class="dropdown-item"> Permanent </option>
               		 <option value="Temporary"<?php echo set_select('type', 'Temporary'); ?> class="dropdown-item"> Temporary </option>
               		 <option value="PT"<?php echo set_select('type', 'PT'); ?> class="dropdown-item"> Both PT </option> 
                    </div>
               </td>
	</tr>	
	<tr>
	<!--	<td><label for="pnp" style="font-size:15px;"><font color='blue'> Plan / Non Plan</font><font color='Red'> *</font> </label>
		    <div><select name="pnp" id="" class="my_dropdown" style="width:338px;">
                        <option value="" disabled selected > -----Select---------------- </option> -->
             <!--           <option value="Plan" <?php //echo set_select('pnp', 'Plan'); ?>class="dropdown-item"> Plan </option>
                        <option value="Non Plan"<?php //echo set_select('pnp', 'Non Plan'); ?> class="dropdown-item"> Non Plan </option> 
                        <option value="GOI"<?php //echo set_select('pnp', 'GOI'); ?> class="dropdown-item"> GOI </option> 
                        <option value="ICAR"<?php //echo set_select('pnp', 'ICAR'); ?> class="dropdown-item"> ICAR </option> 
		     </div>	
		  </td> -->
		<td><label for="scale" style="font-size:15px;"><font color='blue'> Pay Band </font><font color='Red'> *</font> </label>
		<div><select name="scale" id="scale" class="my_dropdown" style="width:338px;">
                  <option selected="selected" disabled selected>--------Select-------------</option>
                 <!--   <?php foreach($this->salgrd as $salgrddata): ?>
                         <option value="<?php echo $salgrddata->sgm_name."(". $salgrddata->sgm_min."-".$salgrddata->sgm_max.")".$salgrddata->sgm_gradepay; ?>"<?php echo set_select('scale', $salgrddata->sgm_name."(". $salgrddata->sgm_min."-".$salgrddata->sgm_max.")".$salgrddata->sgm_gradepay);?>><?php echo $salgrddata->sgm_name."(". $salgrddata->sgm_min."-".$salgrddata->sgm_max.")".$salgrddata->sgm_gradepay; ?>
                          </option>
                  <?php endforeach; ?> -->
		</select> 
                    </div>
                 </td>
		<td> <label for="methodrect" style="font-size:15px;"> <font color='blue'>Method of Recruitment</font><font color='Red'> *</font> </label>
		   <div><select name="methodrect" id="" class="my_dropdown" style="width:338px;">
                	<option value="" disabled selected >------Select-----------------</option>
                	<option value="direct"<?php echo set_select('methodrect', 'direct'); ?> class="dropdown-item"> Direct </option> 
                	<option value="Promotion"<?php echo set_select('methodrect', 'Promotion'); ?> class="dropdown-item"> Promotion </option>
                	<option value="Deputation"<?php echo set_select('methodrect', 'Deputation'); ?> class="dropdown-item"> Deputation </option> 
		     </div>	
		</td>
	</tr>	
	<tr>
		<td><label for="ss" style="font-size:15px;"><font color='blue'>Position Sanction Strength</font> <font color='Red'>*</font> </label>
		<div><input type="text" name="ss" id="ss" class="keyup-numeric" size="30" value="<?php echo isset($_POST["ss"]) ? $_POST["ss"] : ''; ?>" placeholder="Position Sanction Strength..." required="required" oninput="getsstypevalue(this.value)" />
		   </div>
		</td>
		
		<td><label for="p" style="font-size:15px;"><font color='blue'>Position Present</font><font color='Red'> *</font> </label>
		   <div><input type="text" name="p" id="p" readonly size="30" value="<?php echo isset($_POST["p"]) ? $_POST["p"] : ''; ?>" placeholder="Position Present..." required="required" />
	           </div>	
		 </td>
		<td><label for="v" style="font-size:15px;"><font color='blue'>Position Vacant</font><font color='Red'> * </font></label>
		  <div><input type="text" name="v" id="v" readonly size="30" value="<?php echo isset($_POST["v"]) ? $_POST["v"] : ''; ?>" placeholder="Position Vacant..." required="required" />
		  </div>
		</td>
	</tr>
           
         <tr>
		<td><label for="ssper" style="font-size:15px;"><font color='blue'>Sanction Strength Permanent</font> <font color='Red'>*</font> </label>
		<div><input type="text" name="ssper" id="ssper" class="keyup-numeric" size="30" value="<?php echo isset($_POST["ssper"]) ? $_POST["ssper"] : ''; ?>" placeholder="Sanction Strength Permanent..." required="required" oninput="getsstypevalueper(this.value)"/><br>
		</div></td>
		
		<td><label for="pper" style="font-size:15px;"><font color='blue'> Position Permanent</font> <font color='Red'>*</font> </label>
		<div><input type="text" name="pper" id="pper" readonly size="30" value="<?php echo isset($_POST["pper"]) ? $_POST["pper"] : ''; ?>" placeholder="Position Permanent..." required="required" />
		</div>
		</td>
		
		<td><label for="vper" style="font-size:15px;"><font color='blue'> Vacancy Permanent </font><font color='Red'>* </font></label>
		<div><input type="text" name="vper" id="vper" readonly size="30" value="<?php echo isset($_POST["vper"]) ? $_POST["vper"] : ''; ?>" placeholder="Vacancy Permanent..." required="required" />
		</div>
		</td>
	</tr>
           

<tr>
		<td><label for="sstem" style="font-size:15px;"><font color='blue'> Sanction Strength Temporary </font><font color='Red'>*</font> </label>
		<div><input type="text" name="sstem" id="sstem" readonly size="30" value="<?php echo isset($_POST["sstem"]) ? $_POST["sstem"] : ''; ?>" placeholder="Sanction Strength Temporary..." required="required" />
		</div>
		</td>
		
		<td><label for="ptem" style="font-size:15px;"> <font color='blue'>Position Temporary</font><font color='Red'> *</font> </label>
		<div><input type="text" name="ptem" id="ptem" readonly size="30" value="<?php echo isset($_POST["ptem"]) ? $_POST["ptem"] : ''; ?>" placeholder="Position Temporary..." required="required" />
		</div>
		</td>
		
		<td><label for="vtem" style="font-size:15px;"> <font color='blue'>Vacancy Temporary</font><font color='Red'> *</font> </label>
		<div><input type="text" name="vtem" id="vtem" readonly size="30" value="<?php echo isset($_POST["vtem"]) ? $_POST["vtem"] : ''; ?>" placeholder="Vacancy Temporary..." required="required" />
		</div>
		</td>
	</tr> 

      <tr>
		<td><label for="address1" style="font-size:15px;"><font color='blue'> Address</font> </label>
		<div><input type="text" name="address1" class="form-control" size="30" value="<?php echo isset($_POST["address1"]) ? $_POST["address1"] : ''; ?>" placeholder="Address..." />
		</div>
		</td>
		<td><label for="ssdetail" style="font-size:15px;"><font color='blue'> Sanction Strength Detail</font> </label>
		<div><input type="text" name="ssdetail" class="form-control" size="30" value="<?php echo isset($_POST["ssdetail"]) ? $_POST["ssdetail"] : ''; ?>" placeholder="Sanction Strength Detail..." />
		</div>
		</td>
		
		<td><label for="remarks" style="font-size:15px;"><font color='blue'>Remarks </font> </label>
		<div><input type="text" name="remarks" class="form-control" size="30" value="<?php echo isset($_POST["remarks"]) ? $_POST["remarks"] : ''; ?>" placeholder="Remarks..." />
		</div>
		</td>
	</tr>	
        <tr style="background-color:#2a8fcf;text-align:left;height:40px;">
	            <td colspan="3">
                <button name="newstaffposition" >Submit</button>
                <button name="clear" >Clear</button>
            </td>
          </tr>
        </table>
      </form>
<p> &nbsp; </p>	
     </div>		
   </body>
  <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>
 
