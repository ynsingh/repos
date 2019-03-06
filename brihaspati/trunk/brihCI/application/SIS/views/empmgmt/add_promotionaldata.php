 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>
 <html>
    <title>Promotional Details</title>
    <head>
        <?php $this->load->view('template/header'); ?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/datepicker/jquery-ui.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-ui.js" ></script>
        <script>
            $(document).ready(function(){

                $('#DateofAGP,#DateofAL,#Datefrom,#Datesp,#Datesg').datepicker({
                    dateFormat: 'yy-mm-dd',
                    autoclose:true,
                    changeMonth: true,
                    changeYear: true,
                    yearRange: 'c-70:c+20',
               
                }).on('changeDate', function (ev) {
                    $(this).datepicker('hide');
                });


		$("#slevel").hide();
                $("#dlevel").hide();
                $("#sgpay").hide();
         	$("#dagp").hide();
        
                 $('#paycomm').on('change',function(){
                        var pc= $('#paycomm').val();
                        if(pc == '6th'){
                                $("#sgpay").show();
                                $("#dagp").show();
                                $("#slevel").hide();
                                $("#dlevel").hide();
                        }
                        else{
                                $("#dlevel").show();
                                $("#slevel").show();
                                $("#dagp").hide();
                                $("#sgpay").hide();
                        }
                  });


         	$("#pcom").hide();
         	$("#dojp").hide();
         	$("#dosp").hide();
         	$("#dosg").hide();
//         	$("#le").hide();

		$('#worktypeid').on('change',function(){
                        var wtid= $('#worktypeid').val();
                        if(wtid == "Teaching"){
		         	$("#pcom").show();
         			$("#dojp").hide();
		         	$("#dosp").hide();
         			$("#dosg").hide();
		         //	$("#le").hide();
                        }
                        else{
         			$("#pcom").hide();
                                $("#dagp").hide();
                                $("#sgpay").hide();
                                $("#slevel").hide();
                                $("#dlevel").hide();
		         	$("#dojp").show();
         			$("#dosp").show();
		         	$("#dosg").show();
         		//	$("#le").show();
                        }
                  });
 
		 /************************Employee Grade******************************************************************/
            
            $('#worktypeid').on('change',function(){
                var worktype = $(this).val();
              //  alert("comin ======="+worktype);
                if(worktype === ''){
                   $('#empgrade').prop('disabled',true);
                }
                else{
             
                    $('#empgrade').prop('disabled',false);
                    $.ajax({
                        url: "<?php echo base_url();?>sisindex.php/staffmgmt/getgradelist",
                        type: "POST",
                        data: {"wtype" : worktype},
                        dataType:"html",
                        success:function(data){
                            $('#empgrade').html(data.replace(/^"|"$/g, ''));
                            
                        },
                        error:function(data){
                            alert("error occur..!!");
                 
                        }
                                            
                    });
                }
            }); 
            /************************ closer Employee Grade******************************************************************/
          

            /************************select Designation on basis of Group*******************/
            $('#grpid').on('change',function(){
                var grp_id = $(this).val();
		var wrktype_id = $('#worktypeid').val();
		var wtg=grp_id+","+wrktype_id;
//		alert(wtg);
                if(grp_id == ''){
                    $('#desigid').prop('disabled',true);
                }
                else{
             
                    $('#desigid').prop('disabled',false);
                    $.ajax({
                      //  url: "<?php echo base_url();?>sisindex.php/staffmgmt/getdesiglist",
                        url: "<?php echo base_url();?>sisindex.php/jslist/getgwdesiglist",
                        type: "POST",
                       // data: {"group" : grp_id},
                        data: {"gwt" : wtg},
                        dataType:"html",
                        success:function(data){
//				alert("data==1="+data);
                            $('#desigid').html(data.replace(/^"|"$/g, ''));
                        },
                        error:function(data){
                            //alert("data in error part==="+data);
                            alert("error occur..!!");
                 
                        }
                                            
                    });
                }
	    });


	});
</script> 
    </head>
    <body>
        <table width="100%">
            <tr>
                <?php
                    echo "<td align=\"left\" width=\"33%\">";
                    if($this->roleid == 4){
                        echo anchor('empmgmt/viewempprofile', 'View Profile ', array('class' => 'top_parent'));
                    }
                    else{
                        echo anchor('report/promotional_profile/'.$this->emp_id, 'View Promotional Profile ', array('class' => 'top_parent'));
                    }
                    echo "</td>";
            
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Add Promotional Details</b>";
                    echo "</td>";
                    echo "<td align=\"right\" width=\"33%\">";

                ?>
            </tr>
        </table>
        <table width="100%">
           <tr><td>
           <div>
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                <?php echo form_error('<div class="isa_error">','</div>');?>
                <?php if(isset($_SESSION['success'])){?>
                    <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                <?php  }; ?>
                <?php if  (isset($_SESSION['err_message'])){?>
                    <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                <?php }; ?>
            </div>
            </td></tr>
        </table>
        <div>							    
            <form id="myform" action="<?php echo site_url('empmgmt/add_promotionaldata/'.$this->emp_id);?>" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="empid" value="<?php echo  $this->emp_id ; ?>">
            <table style="width:100%; border:1px solid gray;" align="center" class="TFtable">
                <tr><thead><th  style="color:white;background-color:#0099CC; text-align:left; height:30px;" colspan=63">&nbsp;&nbsp; Add Promotional Details</th></thead></tr>
                <tr></tr><tr></tr>
		<tr>
			<td>Working Type<font color='Red'>*</font></td>
                        <td colspan=2><select id="worktypeid" name="workingtype" required style="width:350px;">
                        <option selected="selected" disabled selected>------------- Working Type -------------</option>
                        <option value="Teaching">Teaching</option>
                        <option value="Non Teaching">Non Teaching</option>
                    </select>
                </td>
	</tr>
	 <tr>
                <td><label for="empgrade">  Grade </label>
                        </td><td>
                        <div><select name="empgrade" id="empgrade"  style="width:350px;">
                        <option selected="selected" disabled selected >-------- Select Grade --------</option>
                        </select></div>
                </td>
        </tr>

	<tr>
                <td>Group<font color='Red'>*</font></td>
                <td colspan=2><select name="group" style="width:350px;" id="grpid" required>
		<option selected="selected" disabled selected>------------ Select Group ---------</option>
                        <option value="A">A</option>
                        <option value="B">B</option>
                        <option value="C">C</option>
                        <option value="D">D</option>
                </select>
                </td>
        </tr>
        <tr>
                    <td>Designation<font color='Red'>*</font></td>
                        <td colspan=2><select name="designation" id="desigid"  style="width:350px;" required> 
                            <option selected="selected" disabled selected>------- Select Designation ---------</option>
                        </select>
                    </td>
        </tr>
	<tr id=pcom>
                        <td><label for="paycomm" class="control-label">Pay Commission:</label></td>
                        <td>
                            <select name="paycomm" id="paycomm" style="width:350px;">
                                <option value="" disabled selected >------Select ---------------</option>
                                <option value="6th">6th</option>
                                <option value="7th">7th</option>
                            </select>
                        </td>
        </tr>

	 <tr id=slevel>
                <td>Academic Level<font color='Red'></font></td>
                <td colspan=2><select name="tlevel" style="width:350px;" id="lvel" >
                <option selected="selected" disabled selected>------------ Select Level---------</option>
                        <option value="Level-10">Level-10</option>
                        <option value="Level-11">Level-11</option>
                        <option value="Level-12">Level-12</option>
			<option value="Level-13A">Level-13A</option>
                        <option value="Level-14">Level-14</option>
                        <option value="Level-15">Level-15</option>
                </select>
                </td>
        </tr>		
                <tr id="dlevel">
                    <td>Date of Academic Level<font color='Red'></font></td>
                        <td colspan=2><input type="text" name="DateofAL" id="DateofAL" value="<?php echo isset($_POST["DateofAL"]) ? $_POST["DateofAL"] : ''; ?>"  size="40" >
                    </td>
                </tr>
	<tr id=sgpay>
	    <td>Academic Grade Pay<font color='Red'></font></td>
                    <td colspan=2><select name="payband" id="payband" style="width:350px;" > 
                        <option selected="selected" disabled selected>------------------ Select Pay Band -------------</option>
                        <option value="AGP-6000">AGP-6000</option>
                        <option value="AGP-7000">AGP-7000</option>
                        <option value="AGP-8000">AGP-8000</option>
                        <option value="AGP-9000">AGP-9000</option>
                        <option value="AGP-10000">AGP-10000</option>
                        <option value="HGP">HGP</option>
                        <?php //foreach($this->salgrd as $salgrddata): ?>	
                            <option value="<?php //echo $salgrddata->sgm_id; ?>"><?php //echo $salgrddata->sgm_name."(". $salgrddata->sgm_min."-".$salgrddata->sgm_max.")".$salgrddata->sgm_gradepay; ?>
                            </option> 
 			<?php //endforeach;?>
                       
                    </select>
                    </td>
                </tr>
               
                <tr id="dagp">
                    <td>Date of AGP<font color='Red'></font></td>
                        <td colspan=2><input type="text" name="DateofAGP" id="DateofAGP" value="<?php echo isset($_POST["DateofAGP"]) ? $_POST["DateofAGP"] : ''; ?>"  size="40" >
                    </td>
                </tr>
<!--
	<tr id=le>
                <td>Level of Entry<font color='Red'></font></td>
                <td colspan=2><select name="ntlevel" style="width:350px;" id="lvel" >
                <option selected="selected" disabled selected>------------ Select Level---------</option>
                        <option value="Level-1">Level-1</option>
                        <option value="Level-2">Level-2</option>
                        <option value="Level-3">Level-3</option>
                        <option value="Level-4">Level-4</option>
                        <option value="Level-5">Level-5</option>
                </select>
                </td>
        </tr>
-->
                <tr id=dojp>
                    <td>Date of Joing in the Post <font color='Red'></font></td>
                        <td><input type="text" name="Datefrom" id="Datefrom" value="<?php echo isset($_POST["Datefrom"]) ? $_POST["Datefrom"] : ''; ?>"  size="40" >
                        </td>
                </tr>
                <tr id=dosg>
                    <td>Date of Selection Grade<font color='Red'></font></td>
                        <td><input type="text" name="Datesg" id="Datesg" value="<?php echo isset($_POST["Datesg"]) ? $_POST["Datesg"] : ''; ?>"  size="40" >
                        </td>
                </tr>
                <tr id=dosp>
                    <td>Date of Special Grade<font color='Red'></font></td>
                        <td><input type="text" name="Datesp" id="Datesp" value="<?php echo isset($_POST["Datesp"]) ? $_POST["Datesp"] : ''; ?>"  size="40" >
                        </td>
                </tr>
                <tr></tr><tr></tr>
                <tr style="color:white;background-color:#0099CC; text-align:left; height:30px;">
                    <td colspan="3">
                	    <button name="addpromotdata" >Submit</button>
		    <!--input type="reset" name="Reset" value="Clear"/-->
			<button type="button" onclick="history.back();">Back</button>
                    </td>
                </tr>    
            </table>
            </form>
        </div>    
        <p> &nbsp; </p>
        <div align="center"> <?php $this->load->view('template/footer');?></div>
    </body>
</html>    
   
