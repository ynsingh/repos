
<!--@name edit_servicedata.php  @author Manorama Pal(palseema30@gmail.com) -->
 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>
 <html>
    <title>Service Details</title>
    <head>
        <?php $this->load->view('template/header'); ?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/datepicker/jquery-ui.css">
        <script type="text/javascript" src="<//?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
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

/********uoc on the basis of campus*****************************************************************************/          
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
                            alert("error occur..!!");
                 
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
                            alert("error occur..!!");
                 
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
                            alert("error occur..!!");
                 
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
                            alert("error occur..!!");
                 
                        }
                                            
                    });
                }
            }); 
        /*********************closer of DDO********************************************/
/************************select Designation on basis of Group*******************/
            $('#grpid').on('change',function(){
                var grp_id = $(this).val();
		var wt= $('#worktypeid').val();
		var gwt = grp_id+","+wt;
                if(grp_id == ''){
                    $('#desigid').prop('disabled',true);
                }
                else{
             
                    $('#desigid').prop('disabled',false);
                    $.ajax({
                        //url: "<?php echo base_url();?>sisindex.php/staffmgmt/getdesiglist",
			 url: "<?php echo base_url();?>sisindex.php/jslist/getgwdesiglist",
                        type: "POST",
                        //data: {"group" : grp_id},
                        data: {"gwt" : gwt},
                        dataType:"html",
                        success:function(data){
                            $('#desigid').html(data.replace(/^"|"$/g, ''));
                       
                        },
                        error:function(data){
                            //alert("data in error part==="+data);
                            alert("error occur..!!");
                 
                        }
                                            
                    });
                }
            });

 /************************select shown against the post value *****************************************************/
/*             $('#desigid').on('change',function(){
                var sc_code = $('#camp').val();
                var uoc_id = $('#uocid').val();
                var dept_id = $('#scid').val();
                //var schm_id = $('#schmid').val();
                var desig_id = $('#desigid').val();
               // var grp_id =  $('#grpid').val();
                var wrktype_id = $('#worktypeid').val();
                //var cudshmdesigwrktype = sc_code+","+uoc_id+","+dept_id+","+schm_id+","+desig_id+","+grp_id+","+wrktype_id;
                var cudshmdesigwrktype = sc_code+","+uoc_id+","+dept_id+","+desig_id+","+wrktype_id;
                //alert("comin script===bsix===="+cudshmdesigwrktype);
                if(desig_id == ''){
                    $('#emppostid').prop('disabled',true);
                }
                else{
                    $('#emppostid').prop('disabled',false);
                    $.ajax({
                        url: "<?php echo base_url();?>sisindex.php/staffmgmt/getemppostposition",
                        type: "POST",
                        data: {"combsix" : cudshmdesigwrktype},
                        dataType:"html",
                        success:function(data){
                           // alert("data===in script="+data);
                            var empdata=data;
                            var val1 = empdata.replace(/\"/g,"");
                            $('#emppostid').html(data.replace(/^"|"$/g, ''));
                            if(val1.trim() === "No vacancy"){
                               // var strmess = new String("Sorry, No vacancy available for this post");
                                alert('Sorry, No vacancy available for this post');
                               // alert(strmess.fontcolor( "red" ));
                               $('#my_id').submit();
                                   
                            }   
                                               
                        },
                        error:function(data){
                            //alert("data in error part==="+data);
                            alert("error occur..!!");
                 
                        }
                                            
                    });
                }
            }); 
*/
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
                                alert("error occur..!!");
                            }
                        });
                    }
                  });

/**********************************************************************************************/
		 $('#worktypeid').on('change',function(){
                        var wtid= $('#worktypeid').val();
                        if(wtid == "Teaching"){
                                $("#dagp").show();
                        }
                        else{
                                $("#dagp").hide();
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
                        
                        echo anchor('report/service_profile/'.$servicedata->empsd_empid, 'View Profile ', array('class' => 'top_parent'));
                    }
                    echo "</td>";
            
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Edit Service Details</b>";
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
            <form id="myform" action="<?php echo site_url('empmgmt/update_servicedata/'.$id);?>" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="id" value="<?php echo  $id ; ?>">
            <table style="width:100%; border:1px solid gray;" align="center" class="TFtable">
                <tr><thead><th style="color:white;background-color:#0099CC; text-align:left; height:30px;" colspan=63">&nbsp;&nbsp; Add Service Details</th></thead></tr>
                <tr></tr><tr></tr>
                <tr>
                    <td>Campus Name <font color='Red'>*</font></td>
                        <td><select id="camp" style="width:350px;" name="campus" required> 
                            <?php if(!empty($servicedata->empsd_campuscode)):;?>       
                            <option value="<?php echo $servicedata->empsd_campuscode;?>"><?php echo $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$servicedata->empsd_campuscode)->sc_name;?></option>    
                            <?php else:?>
                            <option selected="selected" disabled selected>--------Campus Name-----</option>
                            <?php endif;?>
                            <?php foreach($this->campus as $camdata): ?>	
   				<option class="test" value="<?php echo $camdata->sc_id; ?>"><?php echo $camdata->sc_name; ?></option> 
                            <?php endforeach; ?>
                      
                        </select>
                    </td>
                </tr>
<tr>
                    <td>University Officer Control<font color='Red'>*</font></td>
                        <td><select id="uocid" style="width:350px;" name="uocontrol" required>
                            <?php if(!empty($servicedata->empsd_ucoid)):;?>
                            <option value="<?php echo $servicedata->empsd_ucoid;?>"><?php echo $this->lgnmodel->get_listspfic1('authorities','name','id',$servicedata->empsd_ucoid)->name;?></option>
                            <?php else:?>
			<option value="">Select University Officer Control</option>
			<?php endif;?>
                        </select>
                    </td>
                </tr>
<tr>
                    <td>Department<font color='Red'>*</font></td>
                        <td><select id="scid" style="width:350px;" name="department" required>
                            <?php if(!empty($servicedata->empsd_deptid)):;?>
                            <option value="<?php echo $servicedata->empsd_deptid;?>"><?php echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$servicedata->empsd_deptid)->dept_name;?></option>
                            <?php else:?>
			  <option value="">Select Department</option>
                        <?php endif;?>

                        </select>
                    </td>
                </tr>
<tr>
                    <td>Scheme<font color='Red'></font></td>
			<td><select name="schemecode" style="width:350px;"id="schmid" >
                            <?php if(!empty($servicedata->empsd_schemeid)):;?>
                            <option value="<?php echo $servicedata->empsd_schemeid;?>"><?php echo $this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$servicedata->empsd_schemeid)->sd_name; ?></option>
                            <?php else:?>
                          <option value="">Select Scheme</option>
                        <?php endif;?>

                        </select>
                    </td>
                </tr>
		<tr>
		<td>Drawing and Disbursing Officer<font color='Red'></font></td>
		<td><select name="ddo" style="width:350px;"id="ddoid" >
                            <?php if(!empty($servicedata->empsd_ddoid)):;?>
                            <option value="<?php echo $servicedata->empsd_ddoid;?>"><?php echo $this->sismodel->get_listspfic1('ddo','ddo_name','ddo_id',$servicedata->empsd_ddoid)->ddo_name; ?></option>
                            <?php else:?>
                          <option value="">Select DDO</option>
                        <?php endif;?>

                        </select>
                    </td>
                </tr>
			<td>Working Type<font color='Red'>*</font></td>
                        <td><select id="worktypeid" name="workingtype" style="width:350px;">
                        <?php if(!empty($servicedata->empsd_worktype)):;?>
                        <option value="<?php echo $servicedata->empsd_worktype;?>"><?php echo $servicedata->empsd_worktype;?></option>
                        <?php else:?>
                        <option value="">-----------Select Working Type--------</option>
                        <?php endif;?>
                        <option value="Teaching">Teaching</option>
                        <option value="Non Teaching">Non Teaching</option>
                        </select>
                </td>

<tr>
                <td>Group<font color='Red'>*</font></td>
		 <td><select name="group" style="width:350px;" id="grpid" required>
                            <?php if(!empty($servicedata->empsd_group)):;?>
                            <option value="<?php echo $servicedata->empsd_group;?>"><?php echo $servicedata->empsd_group;?></option>
                            <?php else:?>
                          <option value="">Select DDO</option>
                        <?php endif;?>
                        <option value="A">A</option>
                        <option value="B">B</option>
                        <option value="C">C</option>
                        <option value="D">D</option>
  
                        </select> 
                    </td>   
                </tr>

		<tr>
		    <td>Designation<font color='Red'>*</font></td>
                        <td><select name="designation" id="desigid" required style="width:350px;"> 
                            <?php if(!empty($servicedata->empsd_desigcode)):;?> 
                            <option value="<?php echo $servicedata->empsd_desigcode;?>"><?php echo $this->commodel->get_listspfic1('designation','desig_name','desig_code',$servicedata->empsd_desigcode)->desig_name;?></option>    
                            <?php else:?>
                            <option selected="selected" disabled selected>------- Select Designation ---------</option>
                            <?php endif;?>
                        </select>
                    </td>
                </tr>

                <tr id="satp">
                <td>Shown Against The Post<font color='Red'></font></td>
<td><select name="emppost" id="emppostid"  style="width:350px;" >
                            <?php if(!empty($servicedata->empsd_shagpstid)):;?>
                            <option value="<?php echo $servicedata->empsd_shagpstid;?>"><?php echo $this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id', $servicedata->empsd_shagpstid)->desig_name ?></option>
                            <?php else:?>
                          <option value="">Select Shown Against The Post</option>
                        <?php endif;?>
                            
                        </select> 
                    </td>   
                </tr>
<tr>
                <td>Level<font color='Red'></font></td>
                 <td><select name="level" style="width:350px;" id="lvel" >
                            <?php if(!empty($servicedata->empsd_level)):;?>
                            <option value="<?php echo $servicedata->empsd_level;?>"><?php echo $servicedata->empsd_level;?></option>
                            <?php else:?>
                         <option value="">Select Level</option>
                         <?php endif;?>
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
                        </select>
                    </td>
                </tr>
 
                <tr>
                    <td>Pay Band<font color='Red'></font></td>
                    <td><select name="payband" id="payband"  style="width:350px;" onchange="gradelist(this.value)">
                        <?php if(!empty($servicedata->empsd_pbid)):;?>
                        <option value="<?php echo $servicedata->empsd_pbid;?>">
                            <?php
                            $payband=$this->sismodel->get_listspfic1('salary_grade_master','sgm_name','sgm_id',$servicedata->empsd_pbid)->sgm_name;
                            $pay_max=$this->sismodel->get_listspfic1('salary_grade_master','sgm_max','sgm_id',$servicedata->empsd_pbid)->sgm_max;
                            $pay_min=$this->sismodel->get_listspfic1('salary_grade_master','sgm_min','sgm_id',$servicedata->empsd_pbid)->sgm_min;
                            $gardepay=$this->sismodel->get_listspfic1('salary_grade_master','sgm_gradepay','sgm_id',$servicedata->empsd_pbid)->sgm_gradepay;
                            ;?>
                            <?php echo $payband."(".$pay_min."-".$pay_max.")".$gardepay;?></option>
                        <?php else:?>
                        <option selected="selected" disabled selected>------------------ Select Pay Band -------------</option>
                        <?php endif;?>
                        <?php foreach($this->salgrd as $salgrddata): ?>	
                            <option value="<?php echo $salgrddata->sgm_id; ?>"><?php echo $salgrddata->sgm_name."(". $salgrddata->sgm_min."-".$salgrddata->sgm_max.")".$salgrddata->sgm_gradepay; ?>
                            </option> 
 			<?php endforeach; ?>
                       
                    </select>
                    </td>
                </tr>
               <?php 
			if(!empty($servicedata->empsd_gradepay)){
				$gp =$servicedata->empsd_gradepay;
			}else if(!empty($gardepay)){
				$gp = $gardepay;
			}else{
				$gp="";
			}
		?> 
                <tr>
                    <td>Grade Pay<font color='Red'></font></td>
                        <td><input type="text" name="gradepay" id="gradepay" value="<?php echo $gp;?>"  size="40" readonly>
                    </td>
                </tr>
                <tr>
                    <td>Order No.<font color='Red'></font></td>
                        <td><input type="text" name="orderno" id="orderno" value="<?php echo $servicedata->empsd_orderno;?>"  size="40" >
                    </td>
                </tr>
                <tr id="dagp">
                    <td>Date of AGP<font color='Red'></font></td>
                        <td><input type="text" name="DateofAGP" id="DateofAGP" value="<?php echo $servicedata->empsd_pbdate;?>"  size="40" >
                    </td>
                </tr>
                <tr>
                    <td>Authority Type<font color='Red'></font></td>
			<td>
			<select name="huoauth" style="width:350px;" id="huoauth" >
<?php			if(!empty($servicedata->empsd_authority)){ ?>
				<option value="<?php echo $servicedata->empsd_authority;?>"><?php echo $servicedata->empsd_authority;?></option>
                       <?php }else{?>
	                	<option selected="selected" disabled selected>Select Value</option>
                       <?php }?>

                        <option value="Head">Head</option>
                        <option value="UO">UO</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Date From<font color='Red'>*</font></td>
                        <td><input type="text" name="Datefrom" id="Datefrom" value="<?php echo $servicedata->empsd_dojoin; ?>"  size="40" required="required">
			<select name="fsession" style="width:110px;" id="fsession" >
                <option selected="selected" disabled selected>Select Session</option>
                        <option value="Forenoon">Forenoon</option>
                        <option value="Afternoon">Afternon</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Date To<font color='Red'></font></td>
                        <td><input type="text" name="Dateto" id="Dateto" value="<?php echo $servicedata->empsd_dorelev; ?>"  size="40" >
			<select name="tsession" style="width:110px;" id="tsession" >
                <option selected="selected" disabled selected>Select Session</option>
                        <option value="Forenoon">Forenoon</option>
                        <option value="Afternoon">Afternon</option>
                        </select>
                    </td>   
                </tr>

		<tr>
            <td>Upload Attachment</td>
            <td><input type='file' name='userfile' size='20' style="font-size:15px;"/>
            <?php if(!empty($servicedata->empsd_filename)):;?>
            <td colspan="2">
		<a href="<?php echo base_url().'uploads/SIS/serviceattachment/'.$servicedata->empsd_filename ; ?>"
                               target="_blank" type="application/octet-stream" download="<?php echo $servicedata->empsd_filename ?>">Download the pdf</a>
            </td>
            <?php endif;?>  
            </td>
        </tr>

                <tr></tr><tr></tr>
                <tr style="background-color:#0099CC; text-align:left; height:30px;">
                    <td colspan="3">
                    <button name="editservdata" >Update</button>
                    <!--<button  onclick="goBack();">Back</button> -->
                    </td>
                </tr>    
        
            </table>
            </form>
        </div>    
        <p> &nbsp; </p>
        <div align="center"> <?php $this->load->view('template/footer');?></div>
    </body>
</html>    
    
