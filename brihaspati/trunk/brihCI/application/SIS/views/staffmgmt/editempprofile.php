
<!--@filename editemployeeprofile.php  @author Manorama Pal(palseema30@gmail.com)
re-engineering in edit profile according to tanuvas structure - 16 OCT 2017 
-->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Welcome to TANUVAS</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/datepicker/jquery-ui.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-ui.js" ></script>
        <script>
            $(document).ready(function(){
            var today = new Date(); 
                  
            $('#StartDate,#Dateofassrexam,#Dateofhgp,#Dateofphd,#Dateofbirth').datepicker({
                dateFormat: 'yy/mm/dd',
                autoclose:true,
                changeMonth: true,
                changeYear: true,
                yearRange: 'c-70:c',
                endDate: "today",
                maxDate: today
            }).on('changeDate', function (ev) {
                $(this).datepicker('hide');
            });
            $('#Dateofprob,#Dateofregular').datepicker({
                dateFormat: 'yy/mm/dd',
                autoclose:true,
                changeMonth: true,
                changeYear: true,
                yearRange: 'c-70:c+20',
                //endDate: "today",
                //maxDate: today
            }).on('changeDate', function (ev) {
                $(this).datepicker('hide');
            });	
           
            /*************************************************************calculate date of retirement******************/
            $("#Dateofbirth").on('change',function(){
                var dob= $(this).val();
                var birthDate = new Date(dob);
               // alert(dob);
                var retDate = new Date(birthDate.getFullYear() + 60, birthDate.getMonth(), birthDate.getDate()-1);
                //var lastDayWithSlashes = retDate.getFullYear()+ '/' + (retDate.getMonth() + 1)+'/' +retDate.getDate();
                var lastDayWithSlashes = new Date(retDate.getFullYear(), retDate.getMonth() + 1, 0);
                var lastDay = (lastDayWithSlashes.getFullYear()+ '/' + (lastDayWithSlashes.getMonth() +1)+ '/' + lastDayWithSlashes.getDate());
                //alert(lastDayWithSlashes);
                return $('#Dateofretir').val(lastDay);
               
            });
            /******************************close date of retirement********************************************************/
                        
            /*******uoc on the basis of campus  ****************************************************************************/
            
            $('#camp').on('change',function(){
                var sc_code = $(this).val();
               // alert(sc_code);
                if(sc_code == ''){
                    $('#uocid').prop('disabled',true);
                }
                else{
             
                    $('#uocid').prop('disabled',false);
                    $.ajax({
                        url: "<?php echo base_url();?>sisindex.php/staffmgmt/getuoclist",
                        type: "POST",
                        data: {"campusname" : sc_code},
                        dataType:"html",
                        success:function(data){
                          //  alert(data);
                        $('#uocid').html(data.replace(/^"|"$/g, ''));
                       
                        },
                        error:function(data){
                            alert("error occur..!!");
                 
                        }
                    });
                }
            }); 
            /**************************** end of uoc ***************************************************************************/
            /************************select department on basis of uoc and campus*******************/
                       
             $('#uocid').on('change',function(){
                var sc_code = $('#camp').val();
                var uoc_id = $('#uocid').val();
                var combid = sc_code+","+uoc_id;
                if(uoc_id == ''){
                    $('#scid').prop('disabled',true);
                }
                else{
             
                    $('#scid').prop('disabled',false);
                    $.ajax({
                        url: "<?php echo base_url();?>sisindex.php/staffmgmt/getnewdeptlist",
                        type: "POST",
                        data: {"campuoc" : combid},
                        dataType:"html",
                        success:function(data){
                           // alert("data==1="+data);
                            $('#scid').html(data.replace(/^"|"$/g, ''));
                       
                        },
                        error:function(data){
                            //alert("data in error==="+data);
                            alert("error occur..!!");
                 
                        }
                                            
                    });
                }
            }); 
            
            /*********************closer of department***************************************/
            /************************select schemes on basis of uoc campus and department*******************/
                       
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
             /************************select DDO on basis of uoc campus department schemes*******************/
            $('#schmid').on('change',function(){
                var sc_code = $('#camp').val();
                //var uoc_id = $('#uocid').val();
                var dept_id = $('#scid').val();
                var schm_id = $('#schmid').val();
                var campuocdeptschm = sc_code+","+dept_id+","+schm_id;
                //alert("seema==="+sc_code+'uoc==='+uoc_id+"dept=="+dept_id+"schmid==="+schm_id+"comb=="+campuocdeptschm);
                if(schm_id == ''){
                    $('#ddoid').prop('disabled',true);
                }
                else{
             
                    $('#ddoid').prop('disabled',false);
                    $.ajax({
                        url: "<?php echo base_url();?>sisindex.php/staffmgmt/getddolist",
                        type: "POST",
                        data: {"combthree" : campuocdeptschm},
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
            /*********************closer of Designation****************************************************************/
            
            
            
            /************************select shown against the post value *****************************************************/
            $('#desigid').on('change',function(){
                var sc_code = $('#camp').val();
                var uoc_id = $('#uocid').val();
                var dept_id = $('#scid').val();
                //var schm_id = $('#schmid').val();
                var desig_id = $('#desigid').val();
                var wrktype_id = $('#worktypeid').val();
                //var cudshmdesigwrktype = sc_code+","+uoc_id+","+dept_id+","+schm_id+","+desig_id+","+wrktype_id;
                var cudshmdesigwrktype = sc_code+","+uoc_id+","+dept_id+","+desig_id+","+wrktype_id;
              //  alert("comin script===bsix===="+cudshmdesigwrktype);
                //var grp_id = $(this).val();
                if(desig_id == ''){
                    $('#emppostid').prop('disabled',true);
                //    $('#emptypeid').prop('disabled',true);
                }
                else{
             
                    $('#emppostid').prop('disabled',false);
                   // $('#emptypeid').prop('disabled',false);
                    $.ajax({
                        url: "<?php echo base_url();?>sisindex.php/staffmgmt/getemppostposition",
                        type: "POST",
                        data: {"combsix" : cudshmdesigwrktype},
                        dataType:"html",
                        success:function(data){
                            //alert("seemas"+data);
                            var empdata=data;
                           // alert("empdata"+empdata);
                     //       var empinput=empdata.split(',');
//                            var val1 = empinput[0].replace(/\"/g,"");
				var val1 = empdata.replace(/\"/g,"");
                            //alert("empinput=split===="+empinput[0]+","+empinput[1]);
                        //    $('#emppost').val(val1.replace(/^"|"$/g, ''));
			$('#emppostid').html(data.replace(/^"|"$/g, ''));
				if(val1.trim() === "No vacancy"){
					 alert('Sorry, No vacancy available for this post');
//                            var val2=$('#emppostid').val();
  //                          if(val2.trim() === "No vacancy"){
    //                            alert(" Sorry, No vacancy available for this post");
                               $('#my_id').submit();
                                   
                            }   
      //                      $('#emptypeid').html(empinput[1].replace(/^"|"$/g, ''));
                       
                        },
                        error:function(data){
                            //alert("data in error part==="+data);
                            alert("error occur..!!");
                 
                        }
                                            
                    });
                }
            }); 
             
            /************************closer for shown against the post*****************************************/
           /************************Employee type******************************************************************/
            
            $('#emppostid').on('change',function(){
                var sc_code = $('#camp').val();
                var uoc_id = $('#uocid').val();
                var dept_id = $('#scid').val();
                //var schm_id = $('#schmid').val();
                var empost_id = $('#emppostid').val();
                var wrktype_id = $('#worktypeid').val();
                //var cudshmpostwrktype = sc_code+","+uoc_id+","+dept_id+","+schm_id+","+empost_id+","+wrktype_id;
                var cudshmpostwrktype = sc_code+","+uoc_id+","+dept_id+","+empost_id+","+wrktype_id;
               // alert("comin script===bsix===="+cudshmpostwrktype);
                //var grp_id = $(this).val();
                if(empost_id == ''){
                   $('#emptypeid').prop('disabled',true);
                }
                else{
             
                    $('#emptypeid').prop('disabled',false);
                    $.ajax({
                        url: "<?php echo base_url();?>sisindex.php/staffmgmt/getemptypeposition",
                        type: "POST",
                        data: {"combfive" : cudshmpostwrktype},
                        dataType:"html",
                        success:function(data){
                            //alert("seema455==="+data);
                            
                            $('#emptypeid').html(data.replace(/^"|"$/g, ''));
                            
                        },
                        error:function(data){
                            //alert("data in error part==="+data);
                            alert("error occur..!!");
                 
                        }
                                            
                    });
                }
            }); 
            /************************ closer Employee type******************************************************************/ 
            /*****************************************validation for date of appiontment**************************************/
            $("#StartDate").on('change',function(){
                var dob = $('#Dateofbirth').val();
                var arr_dateText = dob.split("/");
                //alert("dob==="+dob);
                var day = arr_dateText[2];
                var month = arr_dateText[1];
                var year = arr_dateText[0];
                //alert("day=="+day+"month=="+month+"year=="+year);
                var mydate = new Date();
                mydate.setFullYear(year, month-1, day);
                //alert("mydate==2 time="+mydate);
                var age = 18;
                var doj = $('#StartDate').val()
                //alert("doj=="+doj);
                var joiningDate = new Date(doj);
                joiningDate.setFullYear(joiningDate.getFullYear() - age);    
                //alert("joiningDate=2====="+joiningDate);
                if(joiningDate < mydate)
                {
                    alert('Sorry, you must be atleast 18 years of age to apply.');
                    var dor = ' ';
                    return $('#StartDate').val(dor);
                }
                else{
                    //alert('in else part');
                    var dor = $('#StartDate').val();
                    return $('#StartDate').val(dor);
                    
                }
                                               
            });
            /******************************validation for date of appiontment********************************************************/
            
            /**Allows only letters, numbers and spaces. All other characters will return an error.**/
            $('.keyup-characters').keyup(function() {
            $('span.error-keyup-2').remove();
            var inputVal = $(this).val();
            var characterReg = /^\s*[a-zA-Z0-9,\s]+\s*$/;
            if(!characterReg.test(inputVal)) {
                $(this).after('<span class="error error-keyup-2"><font color="red">No special characters allowed.</font></span>');
            }
            });
            /******************************************close Special characters***************************************/
            /************************************Accepts only 0 – 9**********************************************/
            $('.keyup-numeric').keyup(function() {
                $('span.error-keyup-1').hide();
                var inputVal = $(this).val();
                var numericReg = /^\d*[0-9](|.\d*[0-9]|,\d*[0-9])?$/;
                if(!numericReg.test(inputVal)) {
                    $(this).after('<span class="error error-keyup-1"><font color="red">Numeric characters only.</font></span>');
                }
            });
            /************************************ close Accepts only 0 – 9**********************************************/
            /**This is a standard regular expression, which is used to validate email addresses to ensure they follow the standard format:**/
            $('.keyup-email').keyup(function() {
                $('span.error-keyup-7').remove();
                var inputVal = $(this).val();
                var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
                if(!emailReg.test(inputVal)) {
                    $(this).after('<span class="error error-keyup-7"><font color="red">Invalid Email Format.</font></span>');
                }
            });
            /***************************************close*********************************************************************/
           /************************************************valid pan number**********************************************************************/
           $('#txtPANNumber').change(function (event) {     
                var regExp = /[a-zA-z]{5}\d{4}[a-zA-Z]{1}/; 
                var txtpan = $(this).val(); 
               // if (txtpan.length == 10 ) { 
                    if( txtpan.match(regExp) ){ 
                       // alert('PAN match found');
                    }
                    else {
                        alert('Not a valid PAN number');
                        event.preventDefault(); 
                    } 
               // } 
                //else { 
                  //  alert('Please enter 10 digits for a valid PAN number');
                   // event.preventDefault(); 
                //} 

            });
           /*******************************************************close pan number method****************************************************************/
         
        });
        /*function myFunction() {
            window.print();
        }*/

        </script>   
    </head>
    <body>
        <div>
           
            <?php $this->load->view('template/header'); ?>
           
        
        </div>
        <table width="100%"><tr><td>
        <?php
            if($this->roleid == 4){
              echo anchor('empmgmt/viewempprofile', 'View Profile ', array('class' => 'top_parent'));  
            }
            else{
                echo anchor('staffmgmt/employeelist/', "View Employee List" ,array('title' => 'Employeen List ' , 'class' => 'top_parent'));
            }
            ?>
        </td>
        </tr>
        </table>
        <div align="left" width="100%">
            
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                <?php echo form_error('<div class="isa_error">','</div>');?>
                
	        <?php if(isset($_SESSION['success'])){?>
                    <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                <?php
                };
                ?>
                 <?php if(isset($_SESSION['err_message'])){?>
                    <div  class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                <?php
                };
                ?>    
                  
        </div>
        <div>
        <?php //echo "testing ====>".$editdata->emp_type_code.$editdata->emp_gender.$editdata->emp_worktype;?>
        <!--<table style="margin-left:5%;width:90%; border:1px solid gray;" class="TFtable">-->
        <table style="margin-left:0%;border:1px solid gray;" class="TFtable">
            
            <?php echo form_open_multipart('staffmgmt/update_profile/' .$id);?>
            <input type="hidden" name="id" value="<?php echo $id ; ?>">
            <tr><thead><th style="background-color:#2a8fcf;text-align:left;height:40px;" colspan="4">&nbsp;&nbsp;Edit Staff Profile</th></thead></tr>
           <!--form method="post" action="<?php //echo base_url('staffmgmt/update_profile/',$editdata->emp_id);?>" -->           
            <tr>
                <td><label for="campus" style="font-size:15px;">Campus Name <font color='Red'>*</font></label>
                    <div><select id="camp" name="campus" style="width:300px;" > 
                        <option value="<?php echo $editdata->emp_scid;?>"><?php echo $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$editdata->emp_scid)->sc_name;?></option>
                        <?php foreach($this->campus as $camdata): ?>	
   				<option class="test" value="<?php echo $camdata->sc_id; ?>"><?php echo $camdata->sc_name; ?></option> 
 			<?php endforeach; ?>
                      
                    </select></div>
                </td>


                <td><label for="uocontrol" style="font-size:15px;">University Officer Control<font color='Red'>*</font></label>
                    <div><select name="uocontrol" style="width:300px;" id="uocid" required readonly>

                        <option value="<?php echo $editdata->emp_uocuserid;?>">
                            <?php
                                $authname=$this->lgnmodel->get_listspfic1('authorities', 'name', 'id',$editdata->emp_uocid)->name;
                                $authcode=$this->lgnmodel->get_listspfic1('authorities', 'code', 'id',$editdata->emp_uocid)->code;
                               // $auofname=$this->lgnmodel->get_listspfic1('userprofile', 'firstname', 'userid',$editdata->emp_uocuserid)->firstname;
                                //$auolname=$this->lgnmodel->get_listspfic1('userprofile', 'lastname', 'userid',$editdata->emp_uocuserid)->lastname;
                                //echo $auofname." ".$auolname."( ".$authiame." )";
                                echo $authname."(".$authcode.")";?>
                            <!--echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$editdata->emp_dept_code)->dept_name;-->
                            </option>
                        <option  disabled >--------University Officer Control -----</option>
                       
                        <!--<//?php foreach($this->uoc as $ucodata): ?>	
                            <option value="<//?php echo $ucodata->id; ?>"><//?php echo $ucodata->name; ?></option> 
 			<//?php endforeach; ?>-->
                        <!--<//?php foreach($this->uoc as $ucodata): ?>	
                            <option value="<//?php echo $ucodata->user_id; ?>"><?php
                                //echo $this->lgnmodel->get_listspfic1('','sc_name','sc_id',$record->emp_scid)->sc_name;
                               /* $authiame=$this->lgnmodel->get_listspfic1('authorities', 'name', 'id',$ucodata->authority_id)->name;
                                $auofname=$this->lgnmodel->get_listspfic1('userprofile', 'firstname', 'userid',$ucodata->user_id)->firstname;
                                $auolname=$this->lgnmodel->get_listspfic1('userprofile', 'lastname', 'userid',$ucodata->user_id)->lastname;
                                echo $auofname." ".$auolname."( ".$authiame." )";*/
                          //      echo $authname."(".$authcode.")";
                            ?>
                            </option> 
 			<//?php endforeach; ?>-->
                    </select></div>
                </td>
                <td><label for="department" style="font-size:15px;">Department<font color='Red'>*</font></label>
                    <div><select required name="department" id="scid" style="width:300px;" readonly>
                        <option value="<?php echo $editdata->emp_dept_code;?>"><?php echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$editdata->emp_dept_code)->dept_name;?></option>
                    </select></div>
                </td>
                <td><label for="schemecode" style="font-size:15px;">Scheme Name<font color='Red'>*</font></label>
                    <div><select required name="schemecode" id="schmid" style="width:300px;" readonly> 
                        <option value="<?php echo $editdata->emp_schemeid;?>"><?php echo $this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$editdata->emp_schemeid)->sd_name;?></option>
                    </select></div>
                </td>
            </tr>    
            <tr>
			<?php //echo $editdata->emp_ddouserid?>
                <td><label for="ddo" style="font-size:15px;">Drawing and Disbursing Officer<font color='Red'>*</font></label>
                    <div><select name="ddo" id="ddoid" style="width:300px;" required readonly>
			<?php if(!empty($editdata->emp_ddouserid)):;?>
                        <option value="<?php echo $editdata->emp_ddouserid;?>" ><?php echo $this->sismodel->get_listspfic1('ddo','ddo_name','ddo_id',$editdata->emp_ddouserid)->ddo_name;?></option>    
                        <?php else:?>
			 <option selected="selected" disabled selected>--------- Drawing and Disbursing Officer-----</option>
			 <?php endif;?>
                        <!--<//?php foreach($this->ddo as $ddodata): ?>	
                            <option value="<//?php echo $ddodata->ddo_id; ?>"><//?php echo $ddodata->ddo_name . '(' . $ddodata->ddo_code .')'; ?></option> 
 			<//?php endforeach; ?>-->
                    </select></div>
                </td>
                <td><label for="workingtype" style="font-size:15px;">Working Type<font color='Red'>*</font></label>
                        <div><select id="worktypeid" name="workingtype" readonly style="width:300px;">
                        <?php if(!empty($editdata->emp_worktype)):;?>   
                        <option value="<?php echo $editdata->emp_worktype;?>"><?php echo $editdata->emp_worktype;?></option>
                        <?php else:?>
                        <option value="">-----------Select Working Type--------</option>
                        <?php endif;?>
                        <option value="Teaching">Teaching</option>
                        <option value="Non Teaching">Non Teaching</option>
                        </select></div>
                </td>
                <td><label for="group" style="font-size:15px;">Group<font color='Red'>*</font></label>
                       <div><select name="group" id="grpid" required style="width:300px;" readonly>
                        <?php if(!empty($editdata->emp_group)):;?>
                        <option value="<?php echo $editdata->emp_group;?>"><?php echo $editdata->emp_group;?></option>      
                        <?php else:?>
                        <option selected="selected" disabled selected>------------ Select Group ---------</option>
                        <?php endif;?>
                        <option value="A">A</option>
                        <option value="B">B</option>
                        <option value="C">C</option>
                        <option value="D">D</option>
                    </select></div>
                </td>
                <td><label for="designation" style="font-size:15px;">Designation<font color='Red'>*</font></label>
                    
                    <div><select name="designation" id="desigid" style="width:300px;" required  readonly> 
                        <?php if(!empty($editdata->emp_desig_code)):;?>				
                            <option value="<?php echo $editdata->emp_desig_code;?>"><?php echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$editdata->emp_desig_code)->desig_name;?></option>
                        <?php else:?>
                            <option selected="selected" disabled selected>------- Select Designation ---------</option>
                         <?php endif;?>
                            <!--  <//?php foreach($this->desig as $desigdata): ?>	
                            <option value="<//?php echo $desigdata->desig_id; ?>"><//?php echo $desigdata->desig_name; ?></option> 
 			<//?php endforeach; ?>-->
                    </select></div>
                </td>
            </tr>    
            <tr>
                <td><label for="emppost" style="font-size:15px;">Shown against the Post<font></font></label>
			<div><select name="emppost" id="emppostid" required style="width:300px;">
                    <?php if(!empty($editdata->emp_post)):;?>
			<option value="<?php echo $editdata->emp_post;?>"><?php echo $editdata->emp_post;?></option>
                        <?php else:?>
                            <option selected="selected" disabled selected>------- Select Designation ---------</option>
                         <?php endif;?>
			 </select></div>
<!--
                    <div><input type="text" id="emppost" name="emppost" value="<?php //echo $editdata->emp_post; ?>" readonly  size="35">
                    </div>
                    <?php //else:?>
                    <div><input type="text" id="emppost" name="emppost" placeholder="Employee Post..."readonly  size="35">
                    </div>
                    <?php //endif?>-->
                </td>
                <td><label for="pnp" style="font-size:15px;">Plan / Non Plan</label>
                    <div><select name="pnp" style="width:78%;">
                         <?php if(!empty($editdata->emp_pnp)):;?>   
                        <option value="<?php echo $editdata->emp_pnp;?>"><?php echo $editdata->emp_pnp;?></option>
                         <?php else:?>
                        <option value="">------ Select Plan / Non-Plan -----------</option>
                        <?php endif?>
                        <option value="Plan">Plan</option>
                        <option value="Non-Paln">Non-Plan</option>
                        <option value="GOI">GOI</option>
                        <option value="ICAR">ICAR</option>
                    </select></div>
                </td>
                <td><label for="emptype" style="font-size:15px;">Employee Type</label>
                    <div><select name="emptype" id="emptypeid" style="width:300px;" readonly>
                        <?php if(!empty($editdata->emp_type_code)):;?>
                        <option value="<?php echo $editdata->emp_type_code;?>"><?php echo $editdata->emp_type_code;?></option>
                        <?php else:?>
                        <option value="">------ Select Employee Type --------</option>
                        <?php endif?>
                       <!-- <option value="Permanent">Permanent</option>
                        <option value="Temporary">Temporary</option>-->
                    </select></div>
                </td>
                <td ><label for="empcode" style="font-size:15px;">Employee PF No<font color='Red'>*</font></label>
                    <div><input type="text" name="empcode" class="keyup-characters" value="<?php echo $editdata->emp_code;?>" size="27"  required pattern="[a-zA-Z0-9 ]+" required="required" >
                    </div>
                </td>
            </tr>
            <tr>
                <td><label for="empname" style="font-size:15px;">Employee Name<font color='Red'>*</font></label>
                    <div><input type="text" name="empname"  value="<?php echo $editdata->emp_name ;?>" placeholder="employee name..." size="35" required="required">
                    </div>
                </td>
                <td><label for="fathername" style="font-size:15px;">Fathers Name</label>
                    <div><input type="text" name="fathername" class="keyup-characters" value="<?php echo $editdata->emp_father; ?>" placeholder="Fathers Name..." size="30" >
                    </div>    
                </td>
                <td><label for="orderno" style="font-size:15px;"> Application Order No</label>
                    <div><input type="text" name="orderno"  value="<?php echo $editdata->emp_apporderno ?>" placeholder="order No..." size="27">
                </div></td>
                <td><label for="specialisation" style="font-size:15px;">Specialisation(Major Subject)</label>
                    <div><select name="specialisation" style="width:75%;"> 
                        <?php if(!empty($editdata->emp_specialisationid)):?>
                        <option  value="<?php echo $editdata->emp_specialisationid;?>"><?php echo $this->commodel->get_listspfic1('subject','sub_name','sub_id',$editdata->emp_specialisationid)->sub_name;?></option>
                        <?php else:?>
                        <option value="0">----------- Major Subject -----------</option>
                        <?php endif?>
                        <?php foreach($this->subject as $subdata): ?>	
   				<option value="<?php echo $subdata->sub_id; ?>"><?php echo $subdata->sub_name; ?></option> 
 			<?php endforeach; ?>
                       
                    </select></div>
                </td>
                
            </tr>
                    
            <tr>
                <td><label for="gender" style="font-size:15px;">Gender</label>
                    <div><select name="gender" style="width:300px;">
                        <?php if(!empty($editdata->emp_gender)):;?>
                        <option value="<?php echo $editdata->emp_gender;?>"><?php echo $editdata->emp_gender;?></option>
                        <?php else:?>
                        <option value="">--------------- Select Gender -------------------</option>
                        <?php endif?>
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                        <option value="Transgender">Transgender</option>
                    </select></div>
                </td> 
                    
                <td><label for="community" style="font-size:15px;">Community</label>
                    <div><select name="community" style="width:300px;">
                        <?php if(!empty($editdata->emp_community)):;?>
                        <option value="<?php echo $editdata->emp_community;?>"><?php echo $editdata->emp_community;?></option>
                        <?php else:?>
                        <option value="">----------- Select Community ----------</option>
                        <?php endif?>
                        <?php foreach($this->community as $communitydata): ?>	
   				<option value="<?php echo $communitydata->cat_name; ?>"><?php echo $communitydata->cat_name; ?></option> 
 			<?php endforeach; ?>
                    </select></div>
                </td> 
                <td><label for="religion" style="font-size:15px;">Religion</label>
                    <div><select name="religion" style="width:300px;">
                        <?php if(!empty($editdata->emp_religion)):;?>
                        <option value="<?php echo $editdata->emp_religion;?>"><?php echo $editdata->emp_religion;?></option>
                        <?php else:?>
                        <option value="">------------ Select Religion ---------</option>
                        <?php endif?>
                        <option value="Hinduism">Hinduism</option>
                        <option value="Islam">Islam</option>
                        <option value="Sikhism">Sikhism</option>
                        <option value="Christianity">Christianity</option>
                        <option value=" Buddhism">Buddhism</option>
                        <option value="Jainism">Jainism</option>
                    </select></div>
                </td>
                <td><label for="caste" style="font-size:15px;">Caste</label>
                    <div><input type="text" name="caste" value="<?php echo $editdata->emp_caste; ?>" placeholder="Caste..." size="27" >
                    </div>    
                </td> 
            </tr>
            <tr>
            
                <td><label for="payband" style="font-size:15px;">Pay Band<font color='Red'>*</font></label>
                    <div><select name="payband" required style="width:300px;"> 
			
                        <option value="<?php echo $editdata->emp_salary_grade;?>">
                            <?php
                            $payband=$this->sismodel->get_listspfic1('salary_grade_master','sgm_name','sgm_id',$editdata->emp_salary_grade)->sgm_name;
                            $pay_max=$this->sismodel->get_listspfic1('salary_grade_master','sgm_max','sgm_id',$editdata->emp_salary_grade)->sgm_max;
                            $pay_min=$this->sismodel->get_listspfic1('salary_grade_master','sgm_min','sgm_id',$editdata->emp_salary_grade)->sgm_min;
                            $gardepay=$this->sismodel->get_listspfic1('salary_grade_master','sgm_gradepay','sgm_id',$editdata->emp_salary_grade)->sgm_gradepay;
                            ;?>
                            <?php echo $payband."(".$pay_min."-".$pay_max.")".$gardepay;?></option>

                            <?php foreach($this->salgrd as $salgrddata): ?>	
                            <option value="<?php echo $salgrddata->sgm_id; ?>"><?php echo $salgrddata->sgm_name."(". $salgrddata->sgm_min."-".$salgrddata->sgm_max.")".$salgrddata->sgm_gradepay; ?>
                            </option> 
 			<?php endforeach; ?>
                       
                    </select></div>
                </td>
                <td><label for="basicpay" style="font-size:15px;">Basic Pay</label>
                    <div><input type="text" name="basicpay"  class="keyup-numeric" value="<?php echo $editdata->emp_basic; ?>" placeholder="Basic pay..." size="30" >
                    </div>    
                </td> 
                <td><label for="emolution" style="font-size:15px;">Emolution</label>
                    <div><input type="text" name="emolution" class="keyup-numeric" value="<?php echo $editdata->emp_emolution; ?>" placeholder="Emolution..." size="27" >
                    </div>    
                </td> 
                <td><label for="empnhisidno" style="font-size:15px;">NHIS ID No</label>
                    <div><input type="text" name="empnhisidno" class="keyup-characters" value="<?php echo $editdata->emp_nhisidno; ?>" placeholder="NHIS ID NO..." size="27">
                    </div>    
                </td>
            </tr>
            <tr>
                <td><label for="phstatus" style="font-size:15px;">Whether Physically handicapped</label>
                    <div><input type="radio" name="phstatus" value="yes" <?php echo ($editdata->emp_phstatus == 'yes'?'checked="checked"':''); ?> >Yes &nbsp;&nbsp;&nbsp;
                        <input type="radio" name="phstatus" value="no" <?php echo ($editdata->emp_phstatus == 'no'?'checked="checked"':''); ?> >No
                </div></td>
                <td><label for="phdetail" style="font-size:15px;">Details of PH</label>
                <div><input type="text" name="phdetail" class="keyup-characters" value="<?php echo $editdata->emp_phdetail; ?>" placeholder="Details of PH..." size="30">
                </div></td>
                <td><label for="Sabgroup" style="font-size:15px;">Blood Group</label>
                   <div><select name="Sabgroup" style="width:78%;">
                            <?php if(!empty($editdata->emp_bloodgroup)):;?>
                           <option value="<?php echo $editdata->emp_bloodgroup;?>"><?php echo $editdata->emp_bloodgroup;?></option
                            <?php else:?>
                            <option selected="true" disabled="disabled">------- Select Blood Group ---------</option>
                            <?php endif?>
				
                                <option value="A+">A+</option>
				<option value="O+">O+</option>
				<option value="B+">B+</option>
				<option value="AB+">AB+</option>
				<option value="A-">A-</option>
				<option value="O-">O-</option>
				<option value="B-">B-</option>
				<option value="AB-">AB-</option>
				
	 		</select></div>		
                </td>
                <td><label for="DateofBirth" style="font-size:15px;">Date of Birth<font color='Red'>*</font></label>
                    <div><input type="text" name="DateofBirth" value="<?php echo $editdata->emp_dob; ?>" id="Dateofbirth" size="27" required="required">
                    </div>    
                </td>
            </tr>
            <tr>
                <td><label for="dateofjoining" style="font-size:15px;">Date of Appointment:<font color='Red'>*</font></label>
                    <div><input type="text" name="dateofjoining" value="<?php echo $editdata->emp_doj; ?>" id="StartDate"  size="35" required="required">
                    </div>
                </td>       
                <td><label for="dateofretirement" style="font-size:15px;">Date of Retirement:</label>
                    <div><input type="text" name="dateofretirement" value="<?php echo $editdata->emp_dor; ?>" id="Dateofretir" class="form-control" size="30" />
                    </div>    
                </td>
                <td><label for="phdstatus" style="font-size:15px;">Phd Status</label>
                    <div><select name="phdstatus" style="width:78%;">
                        <?php if(!empty($editdata->emp_phd_status)):;?>   
                            <option value="<?php echo $editdata->emp_phd_status;?>"><?php echo $editdata->emp_phd_status;?></option>
                        <?php else:?>
                            <option selected="true" disabled="disabled">--------- Select Phd Status ---------</option>
                        <?php endif?>
                        <option value="Complete">Complete</option>
                        <option value="Not Complete">Not Complete</option>
                    </select><div>
                </td>
                <td><label for="dateofphd" style="font-size:15px;">Date of Phd Completion</label>
                    <div><input type="text" name="dateofphd" id="Dateofphd"  value="<?php echo $editdata->emp_dateofphd; ?>" size="27" />
                    </div>    
                </td>        
            </tr>
            
            <tr>
                <td><label for="assrexam" style="font-size:15px;">ASSR Exam Status</label>
                    <div><select name="assrexam" style="width:57%;">
                          <?php if(!empty($editdata->emp_AssrExam_status)):;?>  
                        <option value="<?php echo $editdata->emp_AssrExam_status;?>"><?php echo $editdata->emp_AssrExam_status;?></option>
                        <?php else:?>
                            <option selected="true" disabled="disabled">----------- Select ASSR Exam Status ---------</option>
                        <?php endif?>
                        <option value="Passed">Passed</option>
                        <option value="Fail">Fail</option>
                    </select></div>
                </td>
                <td><label for="assrexamdate" style="font-size:15px;">Date of ASSR Exam</label>
                    <div><input type="text" name="assrexamdate" id="Dateofassrexam" value="<?php echo $editdata->emp_dateofAssrExam ;?>"class="form-control" size="30" />
                    </div>
                </td>    
                <td><label for="dateofhgp" style="font-size:15px;">Date of HGP</label>
                    <div><input type="text" name="dateofhgp" id="Dateofhgp" value="<?php echo $editdata->emp_dateofHGP ; ?>" class="form-control" size="28" /></td>
                    </div>
                </td>
                <td><label for="panno" style="font-size:15px;">Pan No</label>
                    <div><input type="text" name="panno" id="txtPANNumber" MaxLength="10" value="<?php echo $editdata->emp_pan_no;?>" placeholder="Pan No..." size="27" >
                    </div>    
                </td> 
            </tr>
            
            <tr>
                
                <?php 
                    $fulldata=$editdata->emp_bank_ifsc_code;
                    $bname=explode(",",$fulldata);  
                    
                ;?>
                <td><label for="bankname" style="font-size:15px;">Bank Name</label>
                    <div><input type="text" name="bankname" class="keyup-characters" value="<?php echo $bname[0]; ?>" placeholder="Bank Name..." size="35" >
                    </div>
                </td>
                <td><label for="ifsccode" style="font-size:15px;">IFSC Code</label>
                    <div><input type="text" name="ifsccode" class="keyup-characters" value="<?php echo $bname[1]; ?>" placeholder="IFSC CODE..." size="30" >
                    </div>
                </td>
                <td><label for="bankacno" style="font-size:15px;">Bank ACC No<font color='Red'>*</font></label>
                    <div><input type="text" name="bankacno" class="keyup-characters" value="<?php echo $editdata->emp_bank_accno; ?>" placeholder="Bank Acc No..." size="28" required="required">
                    </div>
                </td>
                <td><label for="Aadharrno" style="font-size:15px;">Aadhaar No<font color='Red'>*</font></label>
                    <div><input type="text" name="Aadharrno" class="keyup-numeric" MaxLength="12" value="<?php echo $editdata->emp_aadhaar_no; ?>" placeholder="Aadharr No..." size="27" required="required">
                    </div>    
                </td>
            </tr>
            
            <tr>
            
                <td><label for="emailid" style="font-size:15px;">E-Mail ID<font color='Red'>*</font></label>
                    <div><input type="text" name="emailid" class="keyup-email" value="<?php echo $editdata->emp_email; ?>" placeholder="E-Mail ID..." size="35" required="required" readonly>
                    </div>
                </td>
                <td><label for="phonemobileno" style="font-size:15px;">Phone/Mobile</label>
                    <div><input type="text" name="phonemobileno" class="keyup-numeric" MaxLength="13" value="<?php echo $editdata->emp_phone; ?>" placeholder="Phone/Mobile No..." size="30" >
                    </div>    
                </td>
                <td><label for="mothertongue" style="font-size:15px;">Mother Tongue</label>
                    <div><input type="text" name="mothertongue"  class="keyup-characters" value="<?php echo $editdata->emp_mothertongue; ?>" placeholder="Mother Tongue..." size="28" >
                    </div>    
                </td>
                <td><label for="nativity" style="font-size:15px;">Nativity</label>
                    <div><input type="text" name="nativity" class="keyup-characters" value="<?php echo $editdata->emp_citizen; ?>" placeholder="Nativity..." size="28" >
                    </div>    
                </td>
            </tr>
            <tr>
                <td><label for="dateofprob" style="font-size:15px;">Date of Probation</label>
                    <div><input type="text" name="dateofprob" id="Dateofprob" value="<?php echo $editdata->emp_doprobation;?>" size="30" />
                <div></td>
                 <td><label for="dateofregular" style="font-size:15px;">Date of Regularisation</label>
                    <div><input type="text" name="dateofregular" id="Dateofregular" value="<?php echo $editdata->emp_doregular;?>"   size="30" />
                <div></td> 
                <td><label for="Qualification" style="font-size:15px;">Qualification</label>
                    <div><input type="text" name="qual" class="keyup-characters" value="<?php echo $editdata->emp_qual;?>" placeholder="Qualification........" size="28" >
                </div></td>

		<td><label for="empgrade" style="font-size:15px;"> Grade  </label>
                        <div><select name="empgrade" id="empgrade"  style="width:338px;">
			<?php if(!empty($editdata->emp_grade)):;?>
                        <option value="<?php echo $editdata->emp_grade;?>"><?php echo $editdata->grade;?></option>
                        <?php else:?>
                        <option value="">------------ Select Grade ---------</option>
                        <?php endif?>
			<?php if ($editdata->emp_worktype === 'Teaching'){?>
                        <option value="Career Advance (CA)">Career Advance (CA)</option>
                        <option value="Regular(R)">Regular (R)</option>
			<?php } else {?>
                        <option value="Selection Grade (SG)">Selection Grade (SG)</option>
                        <option value="Special Grade (SplG)">Special Grade (SplG)</option>
			<?php }?>
                    </select></div>
                </td>
            </tr>
            <tr>
                <td><label for="remarks" style="font-size:15px;">Remarks</label>
                    <div><textarea name="remarks" rows="3" cols="40"  ><?php echo $editdata->emp_remarks;?></textarea>
                </div></td>
                <td><label for="Address" style="font-size:15px;">Address</label>
                    <div><textarea name="Address"  class="keyup-characters" rows="5" cols="50" pattern="[a-zA-Z0-9 ]+"><?php echo $editdata->emp_address;?></textarea>
                    </div>
                </td>    
                    <!--    <input type="text" name="Address" class="keyup-characters" value="<//?php echo $editdata->emp_address; ?>" placeholder="Address..." size="25" ></td>-->
                
                <td><label for="userfile" style="font-size:15px;">Upload Photo:</label>
                    <div><input type='file' name='userfile'  accept="image/*" onchange="preview_image(event)" size='20' />
                    
                    </div>
                                            
                </td>
                <?php 
                /*
                $strphoto = 0;
                $files = array();
                foreach (glob("./uploads/SIS/empphoto/*.{jpg,jpeg,gif,png}",GLOB_BRACE) as $file) {
                    $files[] = $file;
                    $filename = basename($file); 
                    $filepart1 = explode('.', $filename);
                    if($filepart1[0] == $editdata->emp_code)
                    {
                        $strphoto = 1;
                      
                    }    
                  
                   
                */
                //if($strphoto == 1):;?>
                <?php if(!empty($editdata->emp_photoname)):;?>
                    <td colspan="2"><img src="<?php echo base_url('uploads/SIS/empphoto/'.$editdata->emp_photoname);?>"  alt="" v:shapes="_x0000_i1025" width="78" height="94"></td>
                <?php else:?>
                    <td colspan="2"><img src="<?php echo base_url('uploads/SIS/empphoto/'."empdemopic.png");?>"  id="output_image" v:shapes="_x0000_i1025" width="78" height="94"/></td>
                <?php endif?>   
            
            </tr>    
                
            </tr>
            <tr>
            <td colspan="8" style="background-color:#2a8fcf;">   
            <button name="updateprofile">Update</button>
            <input type="reset" name="Reset" value="Clear"/>
            </td>
           
            </tr>
            <?php //echo form_hidden('id', $editdata->emp_id);?>
           <?php echo form_close();?>
           <?php //endforeach; ?>
          
        </table> 
         <p> &nbsp; </p>   
        </div>    
        <div align="center">  <?php $this->load->view('template/footer');?></div>
    </body>
</html>
