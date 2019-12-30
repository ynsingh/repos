<!--@name add_servicedata.php  @author Manorama Pal(palseema30@gmail.com) -->
 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>
 <html>
    <title>Service Details</title>
    <head>
        <?php $this->load->view('template/header'); ?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/datepicker/jquery-ui.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-ui.js" ></script>
        <script>
            $(document).ready(function(){
		  $("#dagp").hide();
		  $("#satp").hide();

                $('#DateofAGP,#Datefrom,#Dateto').datepicker({
                    dateFormat: 'yy-mm-dd',
                    autoclose:true,
                    changeMonth: true,
                    changeYear: true,
                    yearRange: 'c-70:c+20',
               
                }).on('changeDate', function (ev) {
                    $(this).datepicker('hide');
                });

/*******uoc on the basis of campus*****************************************************************************/
            
            /*
            In future this code may be replace when either campusid added in the 
            autority or authority added in campus.*/
            $('#camp').on('change',function(){
                var sc_code = $(this).val();
                //alert(sc_code);
                if(sc_code == ''){
                    $('#uocid').prop('disabled',true);
                   
                }
                else{
                    $('#uocid').prop('disabled',false);
                    $.ajax({
                        url: "<?php echo base_url();?>sisindex.php/empmgmt/getuoclist",
                        type: "POST",
                        data: {"campusname" : sc_code},
                        dataType:"html",
                        success:function(data){
                            //alert("data==1="+data);
                            $('#uocid').html(data.replace(/^"|"$/g, ''));
                                                 
                        },
                        error:function(data){
                            //alert("data in error==="+data);
                            alert("error occur uoc list..!!");
                 
                        }
                    });
                }
            }); 
            /*****end of uoc***************************************************************************/
            /************************select department on basis of uoc and campus*******************/           
             $('#uocid').on('change',function(){
                var sc_code = $('#camp').val();
                var uoc_id = $('#uocid').val();
                var combid = sc_code+","+uoc_id;
               //alert("combid=="+combid);
                if(uoc_id == ''){
                    $('#scid').prop('disabled',true);
                }
                else{
             
                    $('#scid').prop('disabled',false);
                    $.ajax({
                        url: "<?php echo base_url();?>sisindex.php/empmgmt/getnewdeptlist",
                        type: "POST",
                        data: {"campuoc" : combid},
                        dataType:"html",
                        success:function(data){
                            
                            $('#scid').html(data.replace(/^"|"$/g, ''));
                       
                        },
                        error:function(data){
                            //alert("data in error==="+data);
                            alert("error occur dept list..!!");
                 
                        }
                                            
                    });
                }
            }); 

 /************************select schemes on the basis of department*******************/
                       
             $('#scid').on('change',function(){
                //var sc_code = $('#camp').val();
                //var uoc_id = $('#uocid').val();
                var dept_id = $('#scid').val();
                //var campuocdept = sc_code+","+uoc_id+","+dept_id;
                //alert("seema==="+sc_code+'uoc==='+uoc_id+"dept=="+dept_id+"comb=="+campuocdept);
                if(dept_id == ''){
                    $('#schmid').prop('disabled',true);
                }
                else{
             
                    $('#schmid').prop('disabled',false);
                    $.ajax({
                        url: "<?php echo base_url();?>sisindex.php/staffmgmt/getnewdeptschemelist",
                        type: "POST",
                       // data: {"combthree" : campuocdept},
                         data: {"combdept" : dept_id},
                        dataType:"html",
                        success:function(data){
                            //alert("ok===="); 
                            //alert("data==1="+data);
                            $('#schmid').html(data.replace(/^"|"$/g, ''));
                       
                        },
                        error:function(data){
                            //alert("data in error==="+data);
                            alert("error occur scheme list..!!");
                 
                        }
                                            
                    });
                }
            }); 
 	/*********************closer of scheme**************************************************/    
	/************************select DDO on basis of campus department schemes*******************/
            $('#schmid').on('change',function(){
                var sc_code = $('#camp').val();
               // var uoc_id = $('#uocid').val();
                var dept_id = $('#scid').val();
                var schm_id = $('#schmid').val();
                //var campuocdeptschm = sc_code+","+uoc_id+","+dept_id+","+schm_id;
                var campdeptschm = sc_code+","+dept_id+","+schm_id;
                //alert("seema==="+sc_code+'uoc==='+uoc_id+"dept=="+dept_id+"schmid==="+schm_id+"comb=="+campuocdeptschm);
                if(schm_id == ''){
                    $('#ddoid').prop('disabled',true);
                }
                else{
             
                    $('#ddoid').prop('disabled',false);
                    $.ajax({
                        url: "<?php echo base_url();?>sisindex.php/staffmgmt/getddolist",
                        type: "POST",
                        //data: {"combfour" : campuocdeptschm},
                        data: {"combthree" : campdeptschm},
                        dataType:"html",
                        success:function(data){
                            //alert("data==1="+data);
                            $('#ddoid').html(data.replace(/^"|"$/g, ''));
                       
                        },
                        error:function(data){
                            //alert("data in error part==="+data);
                            alert("error occur ddo list..!!");
                 
                        }
                                            
                    });
                }
            }); 
	/*********************closer of DDO********************************************/
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
                            alert("error occur desig list..!!");
                 
                        }
                                            
                    });
                }
	    });

            /************************closer for shown against the post*****************************************/
		 $("#payband").on('change',function(){
                        var leaveid = $(this).val();
                       //alert("seema======"+leaveid);
                        if(leaveid == ''){
                               $('#gradepay').prop('disabled',true);
                        }
                        else{
                         $('#gradepay').prop('disabled',false);
                            $.ajax({
                                url: "<?php echo base_url();?>sisindex.php/empmgmt/getgrade",
                                type: "POST",
                                data: {"payband" : leaveid},
                                dataType:"html",
                                success:function(data){
                                   var ldata=data;
			 $('#gradepay').val(ldata.replace(/\"/g,""));
                            },
                            error:function(data){
                                alert("error occur grade..!!");
                            }
                        });
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
                            alert("error occur grade list..!!");
                 
                        }
                                            
                    });
                }
            }); 
            /************************ closer Employee Grade******************************************************************/

/***********************************************************************/
		$('#worktypeid').on('change',function(){
                        var wtid= $('#worktypeid').val();
                        if(wtid == "Teaching"){
                                $("#dagp").show();
                        }
                        else{
                                $("#dagp").hide();
                        }
                  });
/***********************************************************************/
          
		$('#workdeptid').on('change',function(){
	                var recmthd = $(this).val();
        	        if(recmthd == 'No'){
                	    $('#wcamp,#wuocid,#wdeptid').prop('disabled',false);
                	}
	                else{
        	            $('#wcamp,#wuocid,#wdeptid').prop('disabled',true);
                	}
            	});

 		$('#wcamp').on('change',function(){
                var sc_code = $(this).val();
                //alert(sc_code);
                if(sc_code == ''){
                    $('#wuocid').prop('disabled',true);
                }
                else{
                    $('#wuocid').prop('disabled',false);
                    $.ajax({
                        url: "<?php echo base_url();?>sisindex.php/empmgmt/getuoclist",
                        type: "POST",
                        data: {"campusname" : sc_code},
                        dataType:"html",
                        success:function(data){
                            //alert("data==1="+data);
                            $('#wuocid').html(data.replace(/^"|"$/g, ''));
                        },
                        error:function(data){
                            //alert("data in error==="+data);
                            alert("error occur..!!");
                        }
                    });
                }
		}); 

		$('#wuocid').on('change',function(){
                var sc_code = $('#wcamp').val();
                var uoc_id = $('#wuocid').val();
                var combid = sc_code+","+uoc_id;
               //alert("combid=="+combid);
                if(uoc_id == ''){
                    $('#wdeptid').prop('disabled',true);
                }
                else{
             
                    $('#wdeptid').prop('disabled',false);
                    $.ajax({
                        url: "<?php echo base_url();?>sisindex.php/empmgmt/getnewdeptlist",
                        type: "POST",
                        data: {"campuoc" : combid},
                        dataType:"html",
                        success:function(data){
                            
                            $('#wdeptid').html(data.replace(/^"|"$/g, ''));
                       
                        },
                        error:function(data){
                            //alert("data in error==="+data);
                            alert("error occur..!!");
                 
                        }
                                            
                    });
                }
            });

		

/***********************************************************************/

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
                        echo anchor('report/service_profile/'.$this->emp_id, 'View Profile ', array('class' => 'top_parent'));
                    }
                    echo "</td>";
            
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Add Service Details</b>";
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
            <form id="myform" action="<?php echo site_url('empmgmt/add_servicedata/'.$this->emp_id);?>" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="empid" value="<?php echo  $this->emp_id ; ?>">
            <table style="width:100%; border:1px solid gray;" align="center" class="TFtable">
                <tr><thead><th  style="color:white;background-color:#0099CC; text-align:left; height:30px;" colspan=63">&nbsp;&nbsp; Add Service Details</th></thead></tr>
                <tr></tr><tr></tr>
                <tr>
                    <td>Campus Name <font color='Red'>*</font></td>
                        <td colspan=2><select id="camp" style="width:350px;" name="campus" required> 
                            <option selected="selected" disabled selected>--------Campus Name-----</option>
                            <?php foreach($this->campus as $camdata): ?>
			     <option class="test" value="<?php echo $camdata->sc_id; ?>"><?php echo $camdata->sc_name; ?></option>	
                            <?php endforeach; ?>
                        </select>
                    </td>
                </tr> 
		<tr>
 		<td>University Officer Control<font color='Red'>*</font></td>
		<td colspan=2><select name="uocontrol" style="width:350px;"id="uocid" required>
		<option selected="selected" disabled selected>--------University Officer Control -----</option>
		</select>
		</td>
		</tr>
		<tr>
		<td>Department<font color='Red'>*</font></td>
		<td colspan=2><select name="department" style="width:350px;"id="scid" required>
		<option selected="selected" disabled selected>--------Department-----</option>
		</select>
		</td>
	</tr>
<tr>
                <td>Scheme Name<font color='Red'></font></td>
                <td colspan=2><select name="schemecode" style="width:350px;"id="schmid" >
                <option selected="selected" disabled selected>--------Scheme Name-----</option>
                </select>
                </td>
        </tr>
<tr>
                <td>Drawing and Disbursing Officer<font color='Red'></font></td>
                <td colspan=2><select name="ddo" style="width:350px;"id="ddoid" >
                <option selected="selected" disabled selected>--------Drawing and Disbursing Officer-----</option>
                </select>
                </td>
       		</tr>
		<tr>
			<td>Working Type<font color='Red'>*</font></td>
                        <td colspan=2><select id="worktypeid" name="workingtype" required style="width:350px;">
                        <option selected="selected" disabled selected>------------- Working Type -------------</option>
                        <option value="Teaching">Teaching</option>
                        <option value="Non Teaching">Non Teaching</option>
                    </select>
                </td>

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
<tr>
                        <td>Is Working in Same Department<font color='Red'>*</font></td>
                        <td><select id="workdeptid" name="workdept" required style="width:350px;">
                        <option selected="selected" disabled selected>------------- Is Working in Same Department -------------</option>
                        <option value="Yes">Yes</option>
                        <option value="No">No</option>
                    </select>
                </td>
                </tr>
                <tr>
                    <td>Working Campus Name <font color='Red'></font></td>
                        <td><select id="wcamp" style="width:350px;" name="wcampus" >
                            <option selected="selected" disabled selected>--------Campus Name-----</option>
                            <?php foreach($this->campus as $camdata): ?>
                             <option class="test" value="<?php echo $camdata->sc_id; ?>"><?php echo $camdata->sc_name; ?></option>
                            <?php endforeach; ?>
                        </select>
                    </td>
                </tr>
                <tr>
                <td>Working University Officer Control<font color='Red'></font></td>
                <td><select name="wuocontrol" style="width:350px;"id="wuocid" >
                <option selected="selected" disabled selected>--------University Officer Control -----</option>
                </select>
                </td>
                </tr>
                <tr>
                <td>Working Department<font color='Red'></font></td>
                <td><select name="wdepartment" style="width:350px;"id="wdeptid" >
                <option selected="selected" disabled selected>--------Department-----</option>
                </select>
                </td>
        </tr>
	<tr id="satp">

                    <td>Shown Against The Post<font color='Red'></font></td>
                        <td colspan=2><select name="emppost" id="emppostid"  style="width:350px;" >
                            <option selected="selected" disabled selected>-------Shown Against The Post---------</option>
                        </select>
                    </td>
                </tr>
	 <tr>
                <td>Level<font color='Red'></font></td>
                <td colspan=2><select name="level" style="width:350px;" id="lvel" >
                <option selected="selected" disabled selected>------------ Select Level---------</option>
                        <option value="Level-1">Level-1</option>
                        <option value="Level-2">Level-2</option>
                        <option value="Level-3">Level-3</option>
                        <option value="Level-4">Level-4</option>
			<option value="Level-5">Level-5</option>
                        <option value="Level-6">Level-6</option>
                        <option value="Level-7">Level-7</option>
                        <option value="Level-8">Level-8</option>
			<option value="Level-9">Level-9</option>
                        <option value="Level-10">Level-10</option>
                        <option value="Level-11">Level-11</option>
                        <option value="Level-12">Level-12</option>
			<option value="Level-13">Level-13</option>
                        <option value="Level-14">Level-14</option>
                        <option value="Level-15">Level-15</option>
                        <option value="Level-16">Level-16</option>
			<option value="Level-17">Level-17</option>
                        <option value="Level-18">Level-18</option>
                        <option value="Level-19">Level-19</option>
                        <option value="Level-20">Level-20</option>
                        <option value="Level-21">Level-21</option>
                        <option value="Level-22">Level-22</option>
                        <option value="Level-23">Level-23</option>
                        <option value="Level-24">Level-24</option>
                        <option value="Level-25">Level-25</option>
                        <option value="Level-26">Level-26</option>
                        <option value="Level-27">Level-27</option>
                        <option value="Level-28">Level-28</option>
                        <option value="Level-29">Level-29</option>
                        <option value="Level-30">Level-30</option>
                        <option value="Level-31">Level-31</option>
                        <option value="Level-32">Level-32</option>
                </select>
                </td>
        </tr>		
			<tr>
	    <td>Pay Band<font color='Red'></font></td>
                    <td colspan=2><select name="payband" id="payband" style="width:350px;" onchange="gradelist(this.value)"> 
                        <option selected="selected" disabled selected>------------------ Select Pay Band -------------</option>
                        <?php foreach($this->salgrd as $salgrddata): ?>	
                            <option value="<?php echo $salgrddata->sgm_id; ?>"><?php echo $salgrddata->sgm_name."(". $salgrddata->sgm_min."-".$salgrddata->sgm_max.")";
					 if($salgrddata->sgm_gradepay > 0) {echo $salgrddata->sgm_gradepay;}
				 ?>
                            </option> 
 			<?php endforeach;?>
                       
                    </select>
                    </td>
                </tr>
                
                <tr>
                    <td>Grade Pay<font color='Red'></font></td>
		    <td colspan=2>
                            <input type="text" name="gradepay" id="gradepay" value="<?php //echo isset($_POST["gradename"]) ? $_POST["gradename"] : ''; ?>" size="40" readonly>
                    </td>
                </tr>
                <tr>
                    <td>Order No<font color='Red'></font></td>

		    <td colspan=2>
                            <input type="text" name="orderno" id="orderno" value="<?php //echo isset($_POST["gradename"]) ? $_POST["gradename"] : ''; ?>" size="40" >
                    </td>
                </tr>
                <tr id="dagp">
                    <td>Date of AGP<font color='Red'></font></td>
                        <td colspan=2><input type="text" name="DateofAGP" id="DateofAGP" value="<?php echo isset($_POST["DateofAGP"]) ? $_POST["DateofAGP"] : ''; ?>"  size="40" >
                    </td>
                </tr>
                <tr>
                    <td>Date From<font color='Red'>*</font></td>
                        <td><input type="text" name="Datefrom" id="Datefrom" value="<?php echo isset($_POST["Datefrom"]) ? $_POST["Datefrom"] : ''; ?>"  size="40" required="required" >
                    
			 <select name="fsession" style="width:110px;" id="fsession" required>
                <option selected="selected" disabled selected>Select Session</option>
                        <option value="Forenoon">Forenoon</option>
                        <option value="Afternoon">Afternon</option>
                        </select></td>
                </tr>
                <tr>
                    <td>Date To<font color='Red'></font></td>
                        <td><input type="text" name="Dateto" id="Dateto" value="<?php echo isset($_POST["Dateto"]) ? $_POST["Dateto"] : ''; ?>"  size="40" >
			 <select name="tsession" style="width:110px;" id="tsession" >
               		 <option selected="selected" disabled selected>Select Session</option>
                        <option value="Forenoon">Forenoon</option>
                        <option value="Afternoon">Afternon</option>
                        </select></td>
                </tr>
        <tr>
            <td>Upload Attachment<br>(Max size 20MB, Allowed Type- pdf)</td>
            <td colspan=2><input type='file' name='userfile' size='20' style="font-size:15px;"/>
            </td>
        </tr>

                <tr></tr><tr></tr>
                <tr style="color:white;background-color:#0099CC; text-align:left; height:30px;">
                    <td colspan="3">
                    <button name="addservdata" >Submit</button>
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
   
