<!--@filename staffprofile.php  @author Manorama Pal(palseema30@gmail.com)
re-engineering in add profile according to tanuvas structure - 16 OCT 2017 
-->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Welcome to TANUVAS</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/datepicker/jquery-ui.css">
        <script type="text/javascript" src="<//?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-ui.js" ></script>
        <script>
            $(document).ready(function(){
            var today = new Date();
            
            $('#StartDate,#Dateofassrexam,#Dateofhgp1,#Dateofphd,#Dateofbirth').datepicker({
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

           /*$("#Dateofassrexam").keyup(function () {
                if (this.value.match(/[^0-9]/g)) {
                    this.value = this.value.replace(/[^0-9^-]/g, '');
                }
            });*/
            
            /*************************************************************calculate date of retirement******************/
            $("#Dateofbirth").on('change',function(){
                var dob= $(this).val();
                var birthDate = new Date(dob);
                //alert(birthDate);
                var retDate = new Date(birthDate.getFullYear() + 60, birthDate.getMonth(), birthDate.getDate()-1);
                //alert(retDate);
                // var lastDayWithSlashes = (retDate.getFullYear()+ '/' + (retDate.getMonth() + 1)+'/' +retDate.getDate());
                var lastDayWithSlashes = new Date(retDate.getFullYear(), retDate.getMonth() + 1, 0);
                var lastDay = (lastDayWithSlashes.getFullYear()+ '/' + (lastDayWithSlashes.getMonth() +1)+ '/' + lastDayWithSlashes.getDate());
                //alert(lastDayWithSlashes);
                return $('#Dateofretir').val(lastDay);
               
            });
            /******************************close date of retirement********************************************************/
            
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
                        url: "<?php echo base_url();?>sisindex.php/staffmgmt/getuoclist",
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
                        url: "<?php echo base_url();?>sisindex.php/staffmgmt/getnewdeptlist",
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
            
            /*********************closer of department***************************************/
            
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
            /*********************closer of Designation****************************************************************/
            
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
         /*********************************************image preview***************************************************************/
        function preview_image(event) 
        {
            var reader = new FileReader();
            reader.onload = function()
            {
                var output = document.getElementById('output_image');
                output.src = reader.result;
            }
            reader.readAsDataURL(event.target.files[0]);
        }
         /*************************************************************************************************************************/      
        </script>
   
    </head>
   <body>
        <!--<div>-->
           
            <?php $this->load->view('template/header'); ?>
           
 
<!--        </div> -->
        <table style="margin-left:0%; "><tr><td>
        <?php echo anchor('staffmgmt/employeelist/', "View Employee List" ,array('title' => 'View Employee List ' , 'class' => 'top_parent'));
        $help_uri = site_url()."/help/helpdoc#StaffProfile";
        echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:54%\">Click for Help</b></a>";
?>
        </td></tr></table>
        <div align="left">
            
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
      <!-- <div> -->
        <!--<table style="margin-left:2%; margin-right:2%; width:97%; border:1px solid gray;" border=1 class="TFtable">-->
        <table style="margin-left:0%;border:1px solid gray;" class="TFtable">
            
            <?php echo form_open_multipart('staffmgmt/staffprofile','id="my_id"');?>
            <tr><thead><th style="background-color:#2a8fcf;text-align:left;height:40px;" colspan="4">&nbsp;&nbsp;Staff Profile Form</th></thead></tr>
            <div style="margin-left:10%;">
            <tr>
                <td><label for="campus" style="font-size:15px;">Campus Name <font color='Red'>*</font></label>
                    <div> <select id="camp" style="width:350px;" name="campus" required> 
                        <option selected="selected" disabled selected>--------Campus Name-----</option>
                       <?php foreach($this->campus as $camdata): ?>	
   				<option class="test" value="<?php echo $camdata->sc_id; ?>"><?php echo $camdata->sc_name; ?></option> 
 			<?php endforeach; ?>
                      
                    </select></div>
                </td> 
                <!--In future this code may be replace when either campusid added in the 
                authority or authority added in campus.-->
                <td><label for="uocontrol" style="font-size:15px;">University Officer Control<font color='Red'>*</font></label>
                    <div><select name="uocontrol" style="width:300px;"id="uocid" required> 
                        <option selected="selected" disabled selected>--------University Officer Control -----</option>
                       
                       <!-- <//?php foreach($this->uoc as $ucodata): ?>	-->
                            <!--option value="<//?php echo $ucodata->user_id; ?>"-->
                            <!--<option value="<//?php echo $ucodata->id; ?>"><//?php-
                            //echo $this->lgnmodel->get_listspfic1('','sc_name','sc_id',$record->emp_scid)->sc_name;
                //                $authiame=$this->lgnmodel->get_listspfic1('authorities', 'name', 'id',$ucodata->authority_id)->name;
                  //              $auofname=$this->lgnmodel->get_listspfic1('userprofile', 'firstname', 'userid',$ucodata->user_id)->firstname;
                    //            $auolname=$this->lgnmodel->get_listspfic1('userprofile', 'lastname', 'userid',$ucodata->user_id)->lastname;
                      //          echo $auofname." ".$auolname."( ".$authiame." )";
				echo $ucodata->name;
                            ?>
                            </option> -->
 			 <?php // endforeach; ?> 
                    </select></div>
                </td>
                <td><label for="department" style="font-size:15px;">Department<font color='Red'>*</font></label>
                    <div><select required name="department"  style="width:300px;" id="scid"> 
                        <option selected="selected" disabled selected >--------Select Department--------</option>
                       
                    </select></div>
                </td>
                <td><label for="schemecode" style="font-size:15px;">Scheme Name<font color='Red'>*</font></label>
                    <div><select required name="schemecode" id="schmid" style="width:300px;"> 
                        <option selected="selected" disabled selected>-----------Scheme Name -----------</option>
                        
                    </select><div>
                </td>
            </tr>
            <!--
            <tr style="height:10px;"></tr>-->
            <tr>
            <td><label for="ddo" style="font-size:15px;">Drawing and Disbursing Officer<font color='Red'>*</font></label>
                    <div><select name="ddo" id="ddoid" required style="width:350px;"> 
                        <option selected="selected" disabled selected>--------- Drawing and Disbursing Officer-----</option>
                    </select></div>
                </td>
                <td><label for="workingtype" style="font-size:15px;">Working Type<font color='Red'>*</font></label>
                        <div><select id="worktypeid" name="workingtype" required style="width:300px;"> 
                        <option selected="selected" disabled selected>------------- Working Type -------------</option>
                        <option value="Teaching">Teaching</option>
                        <option value="Non Teaching">Non Teaching</option>
                    </select></div>
                </td> 
                <td><label for="group" style="font-size:15px;">Group<font color='Red'>*</font></label>
                       <div><select name="group" id="grpid" required style="width:300px;"> 
                        <option selected="selected" disabled selected>------------ Select Group ---------</option>
                        <option value="A">A</option>
                        <option value="B">B</option>
                        <option value="C">C</option>
                        <option value="D">D</option>
                    </select></div>
                </td>
                <td><label for="designation" style="font-size:15px;">Designation<font color='Red'>*</font></label>
                    <div><select name="designation" id="desigid" required style="width:300px;"> 
                        <option selected="selected" disabled selected>------- Select Designation ---------</option>
                        <!--    <//?php foreach($this->desig as $desigdata): ?>	
                            <option value="<//?php echo $desigdata->desig_id; ?>"><//?php echo $desigdata->desig_name; ?></option> 
 			<//?php endforeach; ?>-->
                    </select></div>
                </td>
              
            </tr>
           <!--<tr style="height:10px;"></tr>-->
            <tr>
                <td><label for="emppost" style="font-size:15px;">Shown Against The Post<font></font></label>
                   <div><select name="emppost" id="emppostid" required style="width:300px;"> <!--<input type="text" id="emppostid" name="emppost" value="<//?php echo isset($_POST["emppost"]) ? $_POST["emppost"] : ''; ?>" placeholder="Employee Post..." size="35">-->
                    <!--<input type="text" id="emppost" name="emppost"  readonly placeholder="Employee Post..." size="35">-->
                    <option selected="selected" disabled selected>------------------ Select Post ------------------</option>
                    </select></div>
                </td>
                 <td><label for="pnp" style="font-size:15px;">Plan / Non Plan</label>
                    <div><select name="pnp" style="width:300px;"> 
                        <option value="">-------------- Plan/Non Plan ------------</option>
                        <option value="Plan">Plan</option>
                        <option value="Non-Paln">Non Plan</option>
                        <option value="GOI">GOI</option>
                        <option value="ICAR">ICAR</option>
                    </select></div>
                </td>
                <td><label for="emptype" style="font-size:15px;">Employee Type<font color='Red'>*</font></label>
                    <div><select id="emptypeid" name="emptype" required style="width:300px;"> 
                        <option selected="selected" disabled selected>-------- Select Employee Type ------</option>
                        <!--<option value="Permanent">Permanent</option>
                        <option value="Temporary">Temporary</option>-->
                    </select><div>
                </td> 
                <td><label for="empcode" style="font-size:15px;">Employee PF No<font color='Red'>*</font></label>
                    <div><input type="text" name="empcode" class="keyup-characters" value="<?php echo isset($_POST["empcode"]) ? $_POST["empcode"] : ''; ?>" placeholder="employee PF No..." size="27"  required pattern="[a-zA-Z0-9 ]+" required="required">
                </div></td>
              
            </tr>
            <!--<tr style="height:10px;"></tr>-->
            <tr>
                <td><label for="empname" style="font-size:15px;">Employee Name <font color='Red'>*</font></label>
                    <div><input type="text" name="empname"  class="keyup-characters" value="<?php echo isset($_POST["empname"]) ? $_POST["empname"] : ''; ?>" placeholder="employee name..." size="35" required="required">
                </div></td>
                <td><label for="fathername" style="font-size:15px;">Fathers Name</label>
                    <div><input type="text" name="fathername" class="keyup-characters" value="<?php echo isset($_POST["fathername"]) ? $_POST["fathername"] : ''; ?>" placeholder="Father Name..." size="30" >
                </div></td>
                <td><label for="orderno" style="font-size:15px;"> Application Order No</label>
                    <div><input type="text" name="orderno"  value="<?php echo isset($_POST["orderno"]) ? $_POST["orderno"] : ''; ?>" placeholder=" application order No..." size="27">
                </div></td>
                <td><label for="specialisation" style="font-size:15px;">Specialisation(Major Subject)</label>
                    <div><select name="specialisation" style="width:300px;"> 
                        <option value="0">----------- Major Subject -----------</option>
                        <?php foreach($this->subject as $subdata): ?>	
   				<option value="<?php echo $subdata->sub_id; ?>"><?php echo $subdata->sub_name; ?></option> 
 			<?php endforeach; ?>
                       
                    </select></div>
                </td>
               
            </tr>
            <!--<tr style="height:10px;"></tr>-->
            <tr>
                 <td><label for="gender" style="font-size:15px;">Gender</label>
                    <div><select name="gender" style="width:350px;"> 
                        <option value="">---------------- Select Gender ------------------</option>
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                        <option value="Transgender">Transgender</option>
                    </select></div>
                </td> 
                    
                <td><label for="community" style="font-size:15px;">Community</label>
                    <div><select name="community" style="width:300px;"> 
                        <option value="">----------- Select Community -----------</option>
                         <?php foreach($this->community as $communitydata): ?>	
   				<option value="<?php echo $communitydata->cat_name; ?>"><?php echo $communitydata->cat_name; ?></option> 
 			<?php endforeach; ?>
                    </select></div>
                </td> 
                                    
                 <td><label for="religion" style="font-size:15px;">Religion</label>
                    <div><select name="religion" style="width:300px;"> 
                        <option value="">---------- Select Religion -----------</option>
                        <option value="Hinduism">Hinduism</option>
                        <option value="Islam">Islam</option>
                        <option value="Sikhism">Sikhism</option>
                        <option value="Christianity">Christianity</option>
                        <option value=" Buddhism">Buddhism</option>
                        <option value="Jainism">Jainism</option>
                    </select><div>
                </td>
                <td><label for="caste" style="font-size:15px;">Caste</label>
                    <div><input type="text" name="caste" value="<?php echo isset($_POST["caste"]) ? $_POST["caste"] : ''; ?>" placeholder="Caste..." size="27" >
                </div></td>
                       
            </tr>
            <!--<tr style="height:10px;"></tr>-->
            <tr>
                <td><label for="payband" style="font-size:15px;">Pay Band<font color='Red'>*</font></label>
                    <div><select name="payband" required style="width:300px;"> 
                        <option selected="selected" disabled selected>------------------ Select Pay Band -------------</option>
                        <?php foreach($this->salgrd as $salgrddata): ?>	
                            <option value="<?php echo $salgrddata->sgm_id; ?>"><?php echo $salgrddata->sgm_name."(". $salgrddata->sgm_min."-".$salgrddata->sgm_max.")".$salgrddata->sgm_gradepay; ?>
                            </option> 
 			<?php endforeach; ?>
                       
                    </select></div>
                </td>
                <td><label for="basicpay" style="font-size:15px;">Basic Pay</label>
                    <div><input type="text" name="basicpay"  class="keyup-numeric" value="<?php echo isset($_POST["basicpay"]) ? $_POST["basicpay"] : ''; ?>" placeholder="Basic pay..." size="30" >
                </div></td> 
                                       
                <td><label for="emolution" style="font-size:15px;">Emolution</label>
                    <div><input type="text" name="emolution" class="keyup-numeric" value="<?php echo isset($_POST["emolution"]) ? $_POST["emolution"] : ''; ?>" placeholder="Emolution..." size="27" >
                </div></td> 
                <td><label for="empnhisidno" style="font-size:15px;">NHIS ID No</label>
                    <div><input type="text" name="empnhisidno" class="keyup-characters" value="<?php echo isset($_POST["empnhisidno"]) ? $_POST["empnhisidno"] : ''; ?>" placeholder="NHIS ID NO..." size="27">
                </div></td>
                
            </tr>
            <!--<tr style="height:10px;"></tr>-->
            <tr>
                <td><label for="phstatus" style="font-size:15px;">Whether Physically Handicapped<font color='Red'>*</font>  </label>
                <div><input type="radio" name="phstatus" value="yes">Yes &nbsp;&nbsp;&nbsp;
                <input type="radio" name="phstatus" value="no">No
                </div></td>
                <td><label for="phdetail" style="font-size:15px;">Details Of PH</label>
                <div><input type="text" name="phdetail" class="keyup-characters" value="<?php echo isset($_POST["phdetail"]) ? $_POST["phdetail"] : ''; ?>" placeholder="Details of PH..." size="30">
                </div></td>
                <td><label for="Sabgroup" style="font-size:15px;">Blood Group</label>
                   <div><select name="Sabgroup" class="form-control" id="register_name" style="width:300px;">
				<option value="">------- Select Blood Group ---------</option>
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
                <td><label for="DateofBirth" style="font-size:15px;">Date Of Birth<font color='Red'>*</font></label>
                    <div><input type="text" name="DateofBirth" id="Dateofbirth" value="<?php echo isset($_POST["DateofBirth"]) ? $_POST["DateofBirth"] : ''; ?>"  size="27" required="required">
                </div></td>     
                         
            </tr>
            <!--<tr style="height:10px;"></tr>-->
            <tr>
                <td><label for="dateofjoining" style="font-size:15px;">Date Of Appointment:<font color='Red'>*</font></label>
                    <div><input type="text" name="dateofjoining" value="<?php echo isset($_POST["dateofjoining"]) ? $_POST["dateofjoining"] : ''; ?>" id="StartDate"  size="35" required="required">
                </div></td>                 
                <td><label for="dateofretirement" style="font-size:15px;">Date Of Retirement</label>
                    <div><input type="text" name="dateofretirement" value="<?php echo isset($_POST["dateofretirement"]) ? $_POST["dateofretirement"] : ''; ?>" id="Dateofretir" class="form-control" size="30" />
                </div></td>
                <td><label for="phdstatus" style="font-size:15px;">Phd Status</label>
                    <div><select name="phdstatus" style="width:300px;"> 
                        <option value="">-------------- Phd Status -------------</option>
                        <option value="Complete">Complete</option>
                        <option value="Not Complete">Not Complete</option>
                    </select></div>
                </td>
                <td><label for="dateofphd" style="font-size:15px;">Date Of Phd Completion</label>
                    <div><input type="text" name="dateofphd" id="Dateofphd"  value="<?php echo isset($_POST["dateofphd"]) ? $_POST["dateofphd"] : ''; ?>" size="27" />
                </div></td>    
            </tr>
            <!--<tr style="height:10px;"></tr>-->
            <tr>
                <td><label for="assrexam" style="font-size:15px;">ASSR Exam</label>
                    <div><select name="assrexam" style="width:350px;"> 
                        <option value="">---------------- ASSR Exam Status -------------</option>
                        <option value="Passed">Passed</option>
                        <option value="Fail">Fail</option>
                    </select></div>
                </td>
                <td><label for="assrexamdate" style="font-size:15px;">Date Of ASSR Exam</label>
                    <div><input type="text" name="assrexamdate" id="Dateofassrexam" value="<?php echo isset($_POST["assrexamdate"]) ? $_POST["assrexamdate"] : ''; ?>"class="form-control" size="30" />
                <div></td>    
                
                <td><label for="dateofhgp" style="font-size:15px;">Date Of HGP</label>
                    <div><input type="text" name="dateofhgp" id="Dateofhgp1" value="<?php echo isset($_POST["dateofhgp"]) ? $_POST["dateofhgp"] : ''; ?>" class="form-control" size="28" />
                </div></td>
                
                <td><label for="panno" style="font-size:15px;">Pan No</label>
                    <div><input type="text" name="panno" id="txtPANNumber" MaxLength="10" value="<?php echo isset($_POST["panno"]) ? $_POST["panno"] : ''; ?>" placeholder="Pan No..." size="27" >
                </div></td> 
               
            </tr>
            <!--<tr style="height:10px;"></tr>-->
            <tr>
                <td><label for="bankname" style="font-size:15px;">Bank Name</label>
                    <div><input type="text" name="bankname" class="keyup-characters" value="<?php echo isset($_POST["bankname"]) ? $_POST["bankname"] : ''; ?>" placeholder="Bank Name..." size="35" >
                </div></td>
                <td><label for="ifsccode" style="font-size:15px;">IFSC Code</label>
                    <div><input type="text" name="ifsccode" class="keyup-characters" value="<?php echo isset($_POST["ifsccode"]) ? $_POST["ifsccode"] : ''; ?>" placeholder="IFSC CODE..." size="30" >
                </div></td>
                <td><label for="bankacno" style="font-size:15px;">Bank ACC No<font color='Red'>*</font></label>
                   <div><input type="text" name="bankacno" class="keyup-characters" value="<?php echo isset($_POST["bankacno"]) ? $_POST["bankacno"] : ''; ?>" placeholder="Bank Acc No..." size="28" required="required">
                </div></td>
                <td><label for="Aadharrno" style="font-size:15px;">Aadhaar No<font color='Red'>*</font></label>
                    <div><input type="text" name="Aadharrno" class="keyup-numeric" MaxLength="12" value="<?php echo isset($_POST["Aadharrno"]) ? $_POST["Aadharrno"] : ''; ?>" placeholder="Aadharr No..." size="27" required="required">
                </div></td>
                            
            </tr>
            <!--<tr style="height:10px;"></tr>-->
            <tr>
                
                <td><label for="emailid" style="font-size:15px;">E-Mail ID<font color='Red'>*</font></label>
                   <div><input type="text" name="emailid" class="keyup-email" value="<?php echo isset($_POST["emailid"]) ? $_POST["emailid"] : ''; ?>" placeholder="E-Mail ID..." size="35" required="required">
                </div></td>
                <td><label for="phonemobileno" style="font-size:15px;">Phone/Mobile</label>
                    <div><input type="text" name="phonemobileno" class="keyup-numeric" MaxLength="13" value="<?php echo isset($_POST["phonemobileno"]) ? $_POST["phonemobileno"] : ''; ?>" placeholder="Phone/Mobile No..." size="30" >
                </div></td>
                <td><label for="mothertongue" style="font-size:15px;">Mother Tongue</label>
                    <div><input type="text" name="mothertongue"  class="keyup-characters" value="<?php echo isset($_POST["mothertongue"]) ? $_POST["mothertongue"] : ''; ?>" placeholder="Mother Tongue..." size="28" >
                </div></td> 
                 <td><label for="nativity" style="font-size:15px;">Nativity</label>
                    <div><input type="text" name="nativity" class="keyup-characters" value="<?php echo isset($_POST["nativity"]) ? $_POST["nativity"] : ''; ?>" placeholder="Nativity..." size="28" >
                 </div></td>
            </tr>
            <tr>
                <td><label for="dateofprob" style="font-size:15px;">Date of Probation</label>
                    <div><input type="text" name="dateofprob" id="Dateofprob" value="<?php echo isset($_POST["dateofprob"]) ? $_POST["dateofprob"] : ''; ?>"class="form-control" size="30" />
                <div></td>
                 <td><label for="dateofregular" style="font-size:15px;">Date of Regularisation</label>
                    <div><input type="text" name="dateofregular" id="Dateofregular" value="<?php echo isset($_POST["dateofregular"]) ? $_POST["dateofregular"] : ''; ?>"class="form-control" size="30" />
                <div></td> 
                <td><label for="Qualification" style="font-size:15px;">Qualification</label>
                    <div><input type="text" name="qual" class="keyup-characters" value="<?php echo isset($_POST["qual"]) ? $_POST["qual"] : ''; ?>" placeholder="Qualification........" size="28" >
                </div></td>
                <td><label for="remarks" style="font-size:15px;">Remarks</label>
                    <div><textarea name="remarks" value="<?php echo isset($_POST["remarks"]) ? $_POST["remarks"] : ''; ?>"   rows="3" cols="40"  placeholder="Remarks......"></textarea>
                </div></td>
            </tr>
            <tr>
                
               
                <td><label for="Address" style="font-size:15px;">Address</label>
                    <div><textarea name="Address" value="<?php echo isset($_POST["Address"]) ? $_POST["Address"] : ''; ?>"   rows="5" cols="50"  placeholder="Address..."></textarea>
                </div></td>
                <td colspan="2"><label for="userfile" style="font-size:15px;">Upload Photo</label>
                   <div>
                        <input type="file" name='userfile' accept="image/*" onchange="preview_image(event)">
                        <!--<input type='file' name='userfile' size='20' class='upload-image' />-->
                    <!--<img id="output_image" v:shapes="_x0000_i1025" width="78" height="94"/>-->
                    <img src="<?php echo base_url('uploads/SIS/empphoto/'."empdemopic.png");?>"  id="output_image" v:shapes="_x0000_i1025" width="78" height="94"/></td>
                </div>
                </td>
            </tr>
            <tr style="background-color:#2a8fcf;text-align:left;">
            <td colspan="8">   
            <button name="staffprofile" >Submit</button>
            <input type="reset" name="Reset" value="Clear"/>
            </td>
           
            </tr>
            <!--</td></tr>-->
            </div>
            
        <!-- </table>
         </td></tr>   -->            
        </form> 
        </table> 
	<p> &nbsp; </p>
        <div><?php $this->load->view('template/footer'); ?></div> 
    </body>
</html>    
