 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>
 <html>
    <title>Edit leave Details</title>
    <head>
        <?php $this->load->view('template/header'); ?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/datepicker/jquery-ui.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-ui.js" ></script>

	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery.datetimepicker.css"/>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery.datetimepicker.full.js"></script>
        <script>
            $(document).ready(function(){
	//		$("#filerow").hide();

                $('#Datefrom,#Dateto,#StartDate,#LastDate').datepicker({
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
                if(grp_id == ''){
                    $('#desigid').prop('disabled',true);
                }
                else{
             
                    $('#desigid').prop('disabled',false);
                    $.ajax({
                        url: "<?php echo base_url();?>sisindex.php/staffmgmt/getdesiglist",
                        type: "POST",
                        data: {"group" : grp_id},
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
	       $('#desigid').on('change',function(){
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

            /************************closer for payband*****************************************/
	//	$('#StartDate').on('change',function(){
          //              var wtid= $('#StartDate').val();
            //            if(wtid != ""){
              //                  $("#filerow").show();
                //        }
                  //      else{
                    //            $("#filerow").hide();
                      //  }
              //    });


            /************************closer for upload row display*****************************************/
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
                        echo anchor('report/leave_profile/'.$this->emp_id, 'View Profile ', array('class' => 'top_parent'));
                    }
                    echo "</td>";
            
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Add Leave Details</b>";
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
            <form id="myform" action="<?php echo site_url('empmgmt/edit_leavepertdata/'.$id);?>" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="empid" value="<?php echo  $this->emp_id ; ?>">
            <table style="width:100%; border:1px solid gray;" align="center" class="TFtable">
                <tr><thead><th  style="color:white;background-color:#0099CC; text-align:left; height:30px;" colspan=63">&nbsp;&nbsp; Add Leave Details</th></thead></tr>
                <tr></tr><tr></tr>
                <tr>
                    <td><label>Campus Name <font color='Red'>*</font></label></td>
                        <td><select id="camp" style="width:350px;" name="campus" required> 
                            <option selected="selected" disabled selected>--------Campus Name-----</option>
                            <?php foreach($this->campus as $camdata): ?>
			     <option class="test" value="<?php echo $camdata->sc_id; ?>"><?php echo $camdata->sc_name; ?></option>	
                            <?php endforeach; ?>
                        </select>
                    </td>
                </tr> 
		<tr>
 		<td><label>University Officer Control<font color='Red'>*</font></label></td>
		<td><select name="uocontrol" style="width:350px;"id="uocid" required>
		<option selected="selected" disabled selected>--------University Officer Control -----</option>
		</select>
		</td>
		</tr>
		<tr>
		<td><label>Department<font color='Red'>*</font></label></td>
		<td><select name="department" style="width:350px;"id="scid" required>
			<?php if(!empty($leavedata->la_deptid)):;?>
                            	<option value="<?php echo $leavedata->la_deptid;?>"><?php echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$leavedata->la_deptid)->dept_name?></option>
                            <?php else:?>
				<option selected="selected" disabled selected>--------Department-----</option>
                            <?php endif;?>
		</select>
		</td>
		</tr>
		<tr>
                        <td><label for="lt_remarks" class="control-label">Leave Type: <font color='Red'> *</font> </label></td>
                        <td>
                 		<select name="la_type" style="width:350px;" id="mySelect" required>
					 <?php if(!empty($leavedata->la_type)):;?>
                                <option value="<?php echo $leavedata->la_type;?>"><?php echo $this->sismodel->get_listspfic1('leave_type_master','lt_name','lt_id',$leavedata->la_type)->lt_name?></option>
                            <?php else:?>
                                <option selected="selected" disabled selected>--------Select Leave Type-----</option>
                            <?php endif;?>
			            <?php foreach($this->leaveresult as $datas)
                                        { ?>
                        		<option value="<?php echo $datas->lt_id; ?>"><?php echo $datas->lt_name; ?></option>
                                <?php } ?>
                                </select>
                        </td>
                </tr>
		<tr>
                        <td><label for="lt_remarks" class="control-label">Purpose/Comments: <font color='Red'> </font> </label></td>
		         <td><textarea name="la_desc"  class="form-control" size="30" rows="5" cols="44" value="<?php echo $leavedata->la_desc; ?>"/></textarea></td>
                </tr>
		<tr>
                    <td><label>Year<font color='Red'>*</font></label></td>

                    <td>
                            <input type="text" name="layear" id="layear" value="<?php echo $leavedata->la_year; ?>" size="40" required>
                    </td>
                </tr>

		<tr>
                        <td><label for="lt_remarks" class="control-label">From Date: <font color='Red'> </font> </label></td>
         		<td><input type="text" name="applied_la_from_date" id="StartDate" class="form-control" value="<?php echo $leavedata->applied_la_from_date; ?>"  style="width:350px" /></td>

            <script>
//               $.datetimepicker.setLocale('en');
  //             $('#StartDate').datetimepicker({
    //           dayOfWeekStart : 1,
      //         lang:'en',
        //       formatTime:'H:i',
          //     formatDate:'Y-m-d',
            //                            minDate:0,
              // });
               //step 5 for give minute duration
              // $('#StartDate').datetimepicker();
             </script>
                </tr>
                <tr>
                        <td><label for="lt_remarks" class="control-label">To Date: <font color='Red'> </font> </label></td>
         		<td><input type="text" name="applied_la_to_date" id="LastDate" class="form-control" value="<?php echo $leavedata->applied_la_to_date; ?>"style="width:350px" /></td>
                         <script>
                //                        $.datetimepicker.setLocale('en');
                  //                      $('#LastDate').datetimepicker({
                    //                    dayOfWeekStart : 1,
                      //                  lang:'en',
                        //                formatTime:'H:i',
                          ///              formatDate:'Y-m-d',
                             //           minDate:0,
                               //         });
                                        //step 5 for give minute duration
                                 //       $('#LastDate').datetimepicker();
                                 </script>
                </tr>
		<tr>
                    <td><label>No of Days<font color='Red'>*</font></label></td>

                    <td>
                            <input type="text" name="ladays" id="ladays" value="<?php echo $leavedata->la_days; ?>" size="40" required>
                    </td>
                </tr>
		<tr id="filerow">
		<td><label for="userfile" style="font-size:;"><font color=''>Upload Supporting Doc</font></label>
		</td>
                   <td>
                        <input type="file" name='userfile'>
			<?php if(!empty($leavedata->la_upfile)):;?>
				<td colspan="2">
		                <a href="<?php echo base_url().'uploads/SIS/empleave/'.$leavedata->la_upfile ; ?>"
                               target="_blank" type="application/octet-stream" download="<?php echo $leavedata->la_upfile ?>">Download the pdf</a>
				</td>
			<?php endif;?>
            </td>

		</td>
		</tr>
                <tr></tr><tr></tr>
                <tr style="color:white;background-color:#0099CC; text-align:left; height:30px;">
                    <td colspan="3">
                    <button name="editleavedata" >Submit</button>
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
   
