<!--@name add_workarrangdata.php   -->
 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>
 <html>
    <title>Working Arrangement Details</title>
    <head>
        <?php $this->load->view('template/header'); ?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/datepicker/jquery-ui.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-ui.js" ></script>
        <script>
            $(document).ready(function(){
                $('#DateofAGP,#Datefrom,#Dateto').datepicker({
                    dateFormat: 'yy/mm/dd',
                    autoclose:true,
                    changeMonth: true,
                    changeYear: true,
                    yearRange: 'c-70:c+20',
               
                }).on('changeDate', function (ev) {
                    $(this).datepicker('hide');
                });
		
		$('#workdeptid').on('change',function(){
                var recmthd = $(this).val();
                if(recmthd == 'No'){
                    $('#camp,#uocid,#scid').prop('disabled',false);
                }
                else{
                    $('#camp,#uocid,#scid').prop('disabled',true);
                }
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
                        echo anchor('report/viewfull_profile/'.$this->emp_id, 'View Profile ', array('class' => 'top_parent'));
                    }
                    echo "</td>";
            
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Add Working Arrangement Details</b>";
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
            <form id="myform" action="<?php echo site_url('empmgmt/add_workarrangdata/'.$this->emp_id);?>" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="empid" value="<?php echo  $this->emp_id ; ?>">
            <table style="width:100%; border:1px solid gray;" align="center" class="TFtable">
                <tr><thead><th  style="color:white;background-color:#0099CC; text-align:left; height:30px;" colspan=63">&nbsp;&nbsp; Add Working Arrangement Details</th></thead></tr>
                <tr></tr><tr></tr>
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
                    <td>Campus Name <font color='Red'></font></td>
                        <td><select id="camp" style="width:350px;" name="campus" > 
                            <option selected="selected" disabled selected>--------Campus Name-----</option>
                            <?php foreach($this->campus as $camdata): ?>
			     <option class="test" value="<?php echo $camdata->sc_id; ?>"><?php echo $camdata->sc_name; ?></option>	
                            <?php endforeach; ?>
                        </select>
                    </td>
                </tr> 
		<tr>
 		<td>University Officer Control<font color='Red'></font></td>
		<td><select name="uocontrol" style="width:350px;"id="uocid" >
		<option selected="selected" disabled selected>--------University Officer Control -----</option>
		</select>
		</td>
		</tr>
		<tr>
		<td>Department<font color='Red'></font></td>
		<td><select name="department" style="width:350px;"id="scid" >
		<option selected="selected" disabled selected>--------Department-----</option>
		</select>
		</td>
	</tr>
<!--
<tr>
                <td>Scheme Name<font color='Red'></font></td>
                <td><select name="schemecode" style="width:350px;"id="schmid" required>
                <option selected="selected" disabled selected>--------Scheme Name-----</option>
                </select>
                </td>
        </tr>
<tr>
                <td>Drawing and Disbursing Officer<font color='Red'></font></td>
                <td><select name="ddo" style="width:350px;"id="ddoid" required>
                <option selected="selected" disabled selected>--------Drawing and Disbursing Officer-----</option>
                </select>
                </td>
       		</tr>
-->
                <tr></tr><tr></tr>
                <tr style="color:white;background-color:#0099CC; text-align:left; height:30px;">
                    <td colspan="3">
                    <button name="addservdata" >Submit</button>
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
   
