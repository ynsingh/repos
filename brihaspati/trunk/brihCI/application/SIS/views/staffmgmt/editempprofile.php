
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
		 $("#dojvc").hide();
                $( "#netqno" ).hide();
            var today = new Date(); 
                  
            $('#StartDate,#StartDatevc,#Dateofhgp,#Dateofphd,#Dateofbirth,#allvciregdate,#vciregdate').datepicker({
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
            $('#Dateofprob,#Dateofregular,#asigndatefrom,#asigndateto,#vciregdate,#leavedatefrom,#leavedateto,#allvcrvaliddate,#vcrvaliddate').datepicker({
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
		var grp_id =  $('#grpid').val();
                var wrktype_id = $('#worktypeid').val();
                if(((grp_id =='B') && (wrktype_id == "Non Teaching"))||((grp_id =='C') && (wrktype_id == "Non Teaching"))){
                        var retDate = new Date(birthDate.getFullYear() + 58, birthDate.getMonth(), birthDate.getDate()-1);
                }else{
                        var retDate = new Date(birthDate.getFullYear() + 60, birthDate.getMonth(), birthDate.getDate()-1);
                }
		 var jdatevc = $('#StartDatevc').val();
                var desig_id = $('#desigid').val();
              if( desig_id == 1){
                var retDate = new Date(birthDate.getFullYear() + 70, birthDate.getMonth(), birthDate.getDate()-1);
                var jDate = new Date(jdatevc);
                var retDaten = new Date(jDate.getFullYear() + 3, jDate.getMonth(), jDate.getDate()-1);
                var retDatef = ((retDate > retDaten)?retDaten:retDate);
                var lastDayWithSlashes = new Date(retDatef.getFullYear(), retDatef.getMonth() + 1, retDatef.getDate());
                var lastDay = (lastDayWithSlashes.getFullYear()+ '-' + ((lastDayWithSlashes.getMonth() +1) < 10 ? '0' : '')+(lastDayWithSlashes.getMonth() +1)+ '-' + lastDayWithSlashes.getDate());

              }
              else{

                // var lastDayWithSlashes = (retDate.getFullYear()+ '/' + (retDate.getMonth() + 1)+'/' +retDate.getDate());
                var lastDayWithSlashes = new Date(retDate.getFullYear(), retDate.getMonth() + 1, 0);
                //var lastDay = (lastDayWithSlashes.getFullYear()+ '/' + (lastDayWithSlashes.getMonth() +1)+ '/' + lastDayWithSlashes.getDate());
                var lastDay = (lastDayWithSlashes.getFullYear()+ '-' + ((lastDayWithSlashes.getMonth() +1) < 10 ? '0' : '')+(lastDayWithSlashes.getMonth() +1)+ '-' + lastDayWithSlashes.getDate());
                }

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

	 /************************Plan NO PLan GOI ICAR******************************************************************/
            
            $('#emppostid').on('change',function(){
                var sc_code = $('#camp').val();
                var uoc_id = $('#uocid').val();
                var dept_id = $('#scid').val();
                var empost_id = $('#emppostid').val();
                var wrktype_id = $('#worktypeid').val();
                var cudshmpostwrktype = sc_code+","+uoc_id+","+dept_id+","+empost_id+","+wrktype_id;
                if(empost_id == ''){
                   $('#pnptypeid').prop('disabled',true);
                }
                else{
                    $('#pnptypeid').prop('disabled',false);
                    $.ajax({
                        url: "<?php echo base_url();?>sisindex.php/jslist/getemppnp",
                        type: "POST",
                        data: {"combfive" : cudshmpostwrktype},
                        dataType:"html",
                        success:function(data){
                            $('#pnptypeid').html(data.replace(/^"|"$/g, ''));
                        },
                        error:function(data){
                            //alert("data in error part==="+data);
                            alert("error occur..!!");
                        }
                    });
                }
            }); 
            /************************ close Plan NON PLAN ICAR GOI******************************************************************/
/************************Old payband start******************************************************************/
            
            $('#emppostid').on('change',function(){
                var desig_id = $('#desigid').val();
                var grp_id =  $('#grpid').val();
                var wrktype_id = $('#worktypeid').val();
                var cudshmpostwrktype = grp_id+","+wrktype_id+","+desig_id;
                if(desig_id == ''){
                   $('#payband').prop('disabled',true);
                }
                else{
                    $('#payband').prop('disabled',false);
                    $.ajax({
                        url: "<?php echo base_url();?>sisindex.php/jslist/getgwdesigpaylist",
                        type: "POST",
                        data: {"gwtdesig" : cudshmpostwrktype},
                        dataType:"html",
                        success:function(data){
                            $('#payband').html(data.replace(/^"|"$/g, ''));
                        },
                        error:function(data){
                            //alert("data in error part==="+data);
                            alert("error occur..!!");
                        }
                    });
                }
            }); 

         /************************close old payband******************************************************************/

         /************************new payband start ******************************************************************/
            
            $('#emppostid').on('change',function(){
                var desig_id = $('#desigid').val();
                var grp_id =  $('#grpid').val();
                var wrktype_id = $('#worktypeid').val();

                var cudshmpostwrktype = grp_id+","+wrktype_id+","+desig_id;
                if(desig_id == ''){
                   $('#newpayband').prop('disabled',true);
                }
                else{
                    $('#newpayband').prop('disabled',false);
                    $.ajax({
                        url: "<?php echo base_url();?>sisindex.php/jslist/getgwdesigpaylist",
                        type: "POST",
                        data: {"gwtdesig" : cudshmpostwrktype},
                        dataType:"html",
                        success:function(data){
                            $('#newpayband').html(data.replace(/^"|"$/g, ''));
                        },
                        error:function(data){
                            //alert("data in error part==="+data);
                            alert("error occur..!!");
                        }
                    });
                }
            }); 
         /************************close new payband******************************************************************/




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
         
          /*****************************************university deputed************************************************************ */
            
            $('#univdeput').on('change',function(){
                var selval = $(this).val();
               // alert("selval===="+selval);
                if(selval == 'Yes'){
                    $('#udt,#leavedatefrom,#leavedateto').prop('disabled',true);
                                      
                }
                else{
                   $('#udt,#leavedatefrom,#leavedateto').prop('disabled',false);
                   $('#udeput,#udeput2').prop('disabled',true);
                    
                    
                }
            }); 
            
            /****************university deputed closer***************************************************************************/    
           
            /*****************************************NET************************************************************ */
           
	     $('#netqual,#netqual2').on('change',function(){
                var redioval = $(this).val();
               // alert("redioval===="+redioval);
                if(redioval == 'No'){
                    $('#passyear,#netdiscipline').prop('disabled',true);
                   $( "#netqno" ).show();
                        $( "#netqyes" ).hide();

                }
                else{
                    $('#netqualyes,#passyear,#netdiscipline').prop('disabled',false);
                    $( "#netqno" ).hide();
                    $( "#netqyes" ).show();


                }
            });
	
 
           /****************NET closer*************************************************************************/
           
            /****************************************Additional Assignments************************************************************ */
            
            $('#asignname').on('change',function(){
                var asignval = $(this).val();
               // alert("asignval===="+asignval);
                if(asignval === 'Others'){
                    $('#asignother').prop('disabled',false);
                                      
                }
                else{
                    $('#asignother').prop('disabled',true);
                   
                    
                    
                }
            }); 
            
           /**************** Additional Assignments closer*************************************************************************/
            /************************Employee Grade******************************************************************/
            
            $('#worktypeid').on('change',function(){
                var worktype = $(this).val();
                //alert("comin ======="+worktype);
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
        
         /*****************************************vet council registration************************************************************ */
            
            $('#vcrapp,#vcrnoapp').on('change',function(){
                var vcrradioval = $(this).val();
               // alert("vcrradioval====="+vcrradioval);
                if(vcrradioval == 'Applicable'){
                    $('#chapter,#vciregno,#vciregdate,#vcrvaliddate').prop('disabled',false);
                    $('#allvciregno,#allvciregdate,#allvcrvaliddate').prop('disabled',false);
                                      
                }
                else{
                    $('#chapter,#vciregno,#vciregdate,#vcrvaliddate').prop('disabled',true);
                    $('#allvciregno,#allvciregdate,#allvcrvaliddate').prop('disabled',true);
                             
                }
            }); 
            
           /****************VCR closer*************************************************************************/
        
		  $('#desigid').on('change',function(){
                        var desigvcid= $('#desigid').val();
                        if(desigvcid == 1){
                                $("#dojvc").show();
                        }
                        else{
                                $("#dojvc").hide();
                        }
                  });


 
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
		<?php $current="basic"; 
			$emp_id=$id;
		  include 'eprofiletab.php';
		 ?>
        <?php //echo "testing ====>".$editdata->emp_type_code.$editdata->emp_gender.$editdata->emp_worktype;?>
        <!--<table style="margin-left:5%;width:90%; border:1px solid gray;" class="TFtable">-->
        <table width="100%" style="margin-left:0%;border:1px solid gray;" class="TFtable">
            
            <tr><thead><th style="background-color:#2a8fcf;text-align:left;height:40px;" colspan="4">&nbsp;&nbsp;Edit Basic Staff Profile</th></thead></tr>
            <?php echo form_open_multipart('staffmgmt/update_profile/' .$id);?>
            <input type="hidden" name="id" value="<?php echo $id ; ?>">
           <!--form method="post" action="<?php //echo base_url('staffmgmt/update_profile/',$editdata->emp_id);?>" -->           
            <tr>
                <td><label for="campus" style="font-size:15px;"><font color='Blue'>Campus Name</font> <font color='Red'>*</font></label>
                    <div><select id="camp" name="campus" style="width:300px;" > 
                        <option value="<?php echo $editdata->emp_scid;?>"><?php echo $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$editdata->emp_scid)->sc_name;?></option>
                        <?php foreach($this->campus as $camdata): ?>	
   				<option class="test" value="<?php echo $camdata->sc_id; ?>"><?php echo $camdata->sc_name; ?></option> 
 			<?php endforeach; ?>
                      
                    </select></div>
                </td>


                <td><label for="uocontrol" style="font-size:15px;"><font color='Blue'>University Officer Control</font><font color='Red'>*</font></label>
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
                       
                    </select></div>
                </td>
                <td><label for="department" style="font-size:15px;"><font color='Blue'>Department</font><font color='Red'>*</font></label>
                    <div><select required name="department" id="scid" style="width:300px;" readonly>
                        <option value="<?php echo $editdata->emp_dept_code;?>"><?php echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$editdata->emp_dept_code)->dept_name;?></option>
                    </select></div>
                </td>
                <td><label for="schemecode" style="font-size:15px;"><font color='Blue'>Scheme Name</font><font color='Red'>*</font></label>
                    <div><select required name="schemecode" id="schmid" style="width:300px;" readonly> 
                        <option value="<?php echo $editdata->emp_schemeid;?>"><?php echo $this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$editdata->emp_schemeid)->sd_name;?></option>
                    </select></div>
                </td>
            </tr>    
            <tr>
			<?php //echo $editdata->emp_ddouserid?>
                <td><label for="ddo" style="font-size:15px;"><font color='Blue'>Drawing and Disbursing Officer</font><font color='Red'>*</font></label>
                    <div><select name="ddo" id="ddoid" style="width:300px;" required readonly>
			<?php if(!empty($editdata->emp_ddouserid)):;?>
                        <option value="<?php echo $editdata->emp_ddouserid;?>" ><?php echo $this->sismodel->get_listspfic1('ddo','ddo_name','ddo_id',$editdata->emp_ddouserid)->ddo_name;?></option>    
                        <?php else:?>
			 <option selected="selected" disabled selected>--------- Drawing and Disbursing Officer-----</option>
			 <?php endif;?>
                       
                    </select></div>
                </td>
                <td><label for="workingtype" style="font-size:15px;"><font color='Blue'>Working Type</font><font color='Red'>*</font></label>
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
                <td><label for="group" style="font-size:15px;"><font color='Blue'>Group</font><font color='Red'>*</font></label>
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
                <td><label for="designation" style="font-size:15px;"><font color='Blue'>Designation</font><font color='Red'>*</font></label>
                    
                    <div><select name="designation" id="desigid" style="width:300px;" required  readonly> 
                        <?php if(!empty($editdata->emp_desig_code)):;?>				
                            <option value="<?php echo $editdata->emp_desig_code;?>"><?php echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$editdata->emp_desig_code)->desig_name;?></option>
                        <?php else:?>
                            <option selected="selected" disabled selected>------- Select Designation ---------</option>
                         <?php endif;?>
                           
                    </select></div>
                </td>
            </tr>    
            <tr>
                <td><label for="emppost" style="font-size:15px;"><font color='Blue'>Shown against the Post</font><font></font></label>
			<div><select name="emppost" id="emppostid" required style="width:300px;">
                    <?php if(!empty($editdata->emp_post)):;?>
			<!--<option value="<?php //echo $editdata->emp_post;?>"><?php //echo $editdata->emp_post;?></option>
                        -->
                        <option value="<?php 
                        echo $this->commodel->get_listspfic1('designation','desig_id','desig_name',$editdata->emp_post)->desig_id ?>">
                        <?php echo $editdata->emp_post;?></option>
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
                <td><label for="pnp" style="font-size:15px;"><font color='Blue'>Plan / Non Plan</font></label>
                    <div><select name="pnp" id="pnptypeid" style="width:300px;">
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
                <td><label for="emptype" style="font-size:15px;"><font color='Blue'>Employee Type</font></label>
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
                <td ><label for="empcode" style="font-size:15px;"><font color='Blue'>Employee PF No</font><font color='Red'>*</font></label>
                    <div><input type="text" name="empcode" class="keyup-characters" value="<?php echo $editdata->emp_code;?>" size="33"  required pattern="[a-zA-Z0-9 ]+" required="required" >
                    </div>
                </td>
            </tr>
            <tr>
                <td><label for="empname" style="font-size:15px;"><font color='Blue'>Employee Name</font><font color='Red'>*</font></label>
                    <div><input type="text" name="empname"  value="<?php echo $editdata->emp_name ;?>" placeholder="employee name..." size="33" required="required">
                    </div>
                </td>
                <td><label for="fathername" style="font-size:15px;"><font color='Blue'>Fathers Name</font></label>
                    <div><input type="text" name="fathername" class="keyup-characters" value="<?php echo $editdata->emp_father; ?>" placeholder="Fathers Name..." size="33" >
                    </div>    
                </td>
		<td><label for="spousename" style="font-size:15px;"><font color='Blue'>Spouse Name</font></label>
                    <div><input type="text" name="spousename" class="keyup-characters" value="<?php echo $editdata->emp_spousename; ?>" placeholder="Spouse Name..." size="33" >
                </div></td>
                <td><label for="orderno" style="font-size:15px;"><font color='Blue'> Appointment Order No</font></label>
                    <div><input type="text" name="orderno"  value="<?php echo $editdata->emp_apporderno ?>" placeholder="order No..." size="33">
                </div></td>
            </tr>
                    
            <tr>
                <td><label for="specialisation" style="font-size:15px;"><font color='Blue'>Specialisation(Major Subject)</font></label>
                    <div><select name="specialisation" style="width:300px;"> 
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
                
                <td><label for="gender" style="font-size:15px;"><font color='Blue'>Gender</font></label>
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
                    
                <td><label for="community" style="font-size:15px;"><font color='Blue'>Community</font></label>
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
                <td><label for="religion" style="font-size:15px;"><font color='Blue'>Religion</font></label>
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
            </tr>
            <tr>
                <td><label for="caste" style="font-size:15px;"><font color='Blue'>Caste</font></label>
                    <div><input type="text" name="caste" value="<?php echo $editdata->emp_caste; ?>" placeholder="Caste..." size="33" >
                    </div>    
                </td> 
            
                <td><label for="payband" style="font-size:15px;"><font color='Blue'>Pay Band</font><font color='Red'>*</font></label>
                    <div><select name="payband" id="payband" required style="width:300px;"> 
			
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
		<td><label for="newpayband" style="font-size:15px;"><font color='blue'>New Pay Band</font><font color='Red'></font></label>
                    <div><select name="newpayband" id="newpayband"  style="width:300px;">
		<?php	
			if(!empty($editdata->emp_salary_gradenew)){ ?>
			<option value="<?php echo $editdata->emp_salary_gradenew;?>">
			<?php
                            $payband=$this->sismodel->get_listspfic1('salary_grade_master','sgm_name','sgm_id',$editdata->emp_salary_gradenew)->sgm_name;
                            $pay_max=$this->sismodel->get_listspfic1('salary_grade_master','sgm_max','sgm_id',$editdata->emp_salary_gradenew)->sgm_max;
                            $pay_min=$this->sismodel->get_listspfic1('salary_grade_master','sgm_min','sgm_id',$editdata->emp_salary_gradenew)->sgm_min;
                            $gardepay=$this->sismodel->get_listspfic1('salary_grade_master','sgm_level','sgm_id',$editdata->emp_salary_gradenew)->sgm_level;
                            ;?>
                            <?php echo $payband."(".$pay_min."-".$pay_max.")".$gardepay;?></option>
		<?php } ?>
                   <!--     <option selected="selected" disabled selected> Select New Pay Band </option>-->
                        <?php foreach($this->salgrdn as $salgrddatan): ?>
                            <option value="<?php echo $salgrddatan->sgm_id; ?>"><?php echo $salgrddatan->sgm_name."(". $salgrddatan->sgm_min."-".$salgrddatan->sgm_max.")".$salgrddatan->sgm_level; ?>
                            </option>
                        <?php endforeach; ?>

                    </select></div>
                </td>

                <td><label for="basicpay" style="font-size:15px;"><font color='Blue'>Basic Pay</font></label>
                    <div><input type="text" name="basicpay"  class="keyup-numeric" value="<?php echo $editdata->emp_basic; ?>" placeholder="Basic pay..." size="33" >
                    </div>    
                </td> 
            </tr>
            <tr>
                <td><label for="emolution" style="font-size:15px;"><font color='Blue'>Emolution</font></label>
                    <div><input type="text" name="emolution" class="keyup-numeric" value="<?php echo $editdata->emp_emolution; ?>" placeholder="Emolution..." size="33" >
                    </div>    
                </td> 
                <td><label for="empnhisidno" style="font-size:15px;"><font color='Blue'>NHIS ID No</font></label>
                    <div><input type="text" name="empnhisidno" class="keyup-characters" value="<?php echo $editdata->emp_nhisidno; ?>" placeholder="NHIS ID NO..." size="33">
                    </div>    
                </td>
                <td><label for="phstatus" style="font-size:15px;"><font color='Blue'>Whether Physically handicapped</font></label>
                    <div><input type="radio" name="phstatus" value="yes" <?php echo ($editdata->emp_phstatus == 'yes'?'checked="checked"':''); ?> >Yes &nbsp;&nbsp;&nbsp;
                        <input type="radio" name="phstatus" value="no" <?php echo ($editdata->emp_phstatus == 'no'?'checked="checked"':''); ?> >No
                </div></td>
                <td><label for="phdetail" style="font-size:15px;"><font color='Blue'>Details of PH</font></label>
                <div><input type="text" name="phdetail" class="keyup-characters" value="<?php echo $editdata->emp_phdetail; ?>" placeholder="Details of PH..." size="33">
                </div></td>
            </tr>
            <tr>
                <td><label for="Sabgroup" style="font-size:15px;"><font color='Blue'>Blood Group</font></label>
                   <div><select name="Sabgroup" style="width:300px;">
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
                <td><label for="dateofjoining" style="font-size:15px;"><font color='Blue'>Date of Appointment</font><font color='Red'>*</font></label>
                    <div><input type="text" name="dateofjoining" value="<?php echo $editdata->emp_doj; ?>" id="StartDate"  size="15" required="required">
			<select name="jsession" style="width:140px;" id="jsession" required>
                                <option selected="selected" disabled selected>Select Session</option>
                                <option value="Forenoon">Forenoon</option>
                                <option value="Afternoon">Afternon</option>
                        </select>
                    </div>
                </td>       
		<td>
		 <?php
                        $loguname=$this->session->userdata('username');
                        if($loguname == 'admin' || $loguname =='rsection@tanuvas.org.in'){
                        
                        ?>
		 <div  id="dojvc">
		<label for="dateofjoiningvc" style="font-size:15px;"><font color='blue'>Date Of Appointment as VC</font><font color='Red'></font></label>
                    <input type="text" name="dateofjoiningvc" value="<?php echo $editdata->emp_dojvc; ?>" id="StartDatevc"  size="33" >
                  <!--      <select name="jsession" style="width:140px;" id="jsession" required>
                                <option selected="selected" disabled selected>Select Session</option>
                                <option value="Forenoon">Forenoon</option>
                                <option value="Afternoon">Afternon</option>
                        </select>-->
                     </div>
		<?php } ?>
                </td>
                <td><label for="DateofBirth" style="font-size:15px;"><font color='Blue'>Date of Birth</font><font color='Red'>*</font></label>
                    <div><input type="text" name="DateofBirth" value="<?php echo $editdata->emp_dob; ?>" id="Dateofbirth" size="33" required="required">
                    </div>    
                </td>
            </tr>
            <tr>

                <td ><label for="dateofretirement" style="font-size:15px;"><font color='Blue'>Date of Retirement</font></label>
                    <div><input type="text" name="dateofretirement" value="<?php echo $editdata->emp_dor; ?>" id="Dateofretir" class="form-control" size="33" />
                    </div>    
                </td>
                <td><label for="dateofprob" style="font-size:15px;"><font color='Blue'>Date of Probation</font></label>
                    <div><input type="text" name="dateofprob" id="Dateofprob" value="<?php echo $editdata->emp_doprobation;?>" size="33" />
                <div></td>
                 <td><label for="dateofregular" style="font-size:15px;"><font color='Blue'>Date of Regularisation</font></label>
                    <div><input type="text" name="dateofregular" id="Dateofregular" value="<?php echo $editdata->emp_doregular;?>"   size="33" />
                <div></td> 
<!--                <td><label for="phdstatus" style="font-size:15px;">Phd Status</label>
                    <div><select name="phdstatus" style="width:78%;">
                        <?php //if(!empty($editdata->emp_phd_status)):;?>   
                            <option value="<?php //echo $editdata->emp_phd_status;?>"><?php //echo $editdata->emp_phd_status;?></option>
                        <?php //else:?>
                            <option selected="true" disabled="disabled">--------- Select Phd Status ---------</option>
                        <?php //endif?>
                        <option value="Complete">Complete</option>
                        <option value="Not Complete">Not Complete</option>
                    </select><div>
                </td>
                <td><label for="dateofphd" style="font-size:15px;">Date of Phd Completion</label>
                    <div><input type="text" name="dateofphd" id="Dateofphd"  value="<?php echo $editdata->emp_dateofphd; ?>" size="27" />
                    </div>    
                </td>        -->
                <td><label for="assrexam" style="font-size:15px;"><font color='Blue'>ASRR Exam </font></label>
                    <div><select name="assrexam" style="width:300px;">
                          <?php if(!empty($editdata->emp_AssrExam_status)):;?>  
                        <option value="<?php echo $editdata->emp_AssrExam_status;?>"><?php echo $editdata->emp_AssrExam_status;?></option>
                        <?php else:?>
                            <option selected="true" disabled="disabled">----------- Select ASRR Exam Status ---------</option>
                        <?php endif?>
                        <option value="Passed">Passed</option>
                        <option value="Not Qualified">Not Qualified</option>
                        <option value="Not Appeared">Not Appeared</option>
                        <option value="Not Registered">Not Registered</option>
                        <option value="Not Applicable">Not Applicable</option>
                    </select></div>
                </td>
            </tr>
            <tr>
                <td><label for="assrexamdate" style="font-size:15px;"><font color='Blue'>ASRR Passed(MM-YYYY)</font></label>
                    <div><input type="text" name="assrexamdate" id="Dateofassrexam" value="<?php echo $editdata->emp_dateofAssrExam ;?>"class="form-control" size="33" />
                    </div>
                </td>    
                <td><label for="dateofhgp" style="font-size:15px;"><font color='Blue'>Date of HGP</font></label>
                    <div><input type="text" name="dateofhgp" id="Dateofhgp" value="<?php echo $editdata->emp_dateofHGP ; ?>" class="form-control" size="33" /></td>
                    </div>
                </td>
                <td><label for="panno" style="font-size:15px;"><font color='Blue'>Pan No</font></label>
                    <div><input type="text" name="panno" id="txtPANNumber" MaxLength="10" value="<?php echo $editdata->emp_pan_no;?>" placeholder="Pan No..." size="33" >
                    </div>    
                </td> 
                
                <?php 
                //    $fulldata=$editdata->emp_bank_ifsc_code;
                  //  $bname=explode("#",$fulldata);  
                    
                ;?>
                <td><label for="bankname" style="font-size:15px;"><font color='Blue'>Bank Name</font></label>
                    <div><input type="text" name="bankname" class="keyup-characters" value="<?php echo $editdata->emp_bankname ?>" placeholder="Bank Name..." size="33" >
                    </div>
                </td>
            </tr>
            <tr>
                <td><label for="ifsccode" style="font-size:15px;"><font color='Blue'>IFSC Code</font></label>
                    <div><input type="text" name="ifsccode" class="keyup-characters" value="<?php echo $editdata->emp_bank_ifsc_code; ?>" placeholder="IFSC CODE..." size="33" >
                    </div>
                </td>
                <td><label for="bankacno" style="font-size:15px;"><font color='Blue'>Bank ACC No</font><font color='Red'></font></label>
                    <div><input type="text" name="bankacno" class="keyup-characters" value="<?php echo $editdata->emp_bank_accno; ?>" placeholder="Bank Acc No..." size="33" required="required">
                    </div>
                </td>
                <td><label for="Aadharrno" style="font-size:15px;"><font color='Blue'>Aadhaar No</font><font color='Red'></font></label>
                    <div><input type="text" name="Aadharrno" class="keyup-numeric" MaxLength="12" value="<?php echo $editdata->emp_aadhaar_no; ?>" placeholder="Aadharr No..." size="33" required="required">
                    </div>    
                </td>
   
		<td><label for="secondary emailid" style="font-size:15px;"><font color='Blue'>Email Id</font></label>
                <div><input type="text" name="secndemailid" class="keyup-email" value="<?php echo $editdata->emp_secndemail; ?>" placeholder=" Email Id........" size="33" >
                </div></td>         
            </tr>
            <tr>
         <!--       <td><label for="emailid" style="font-size:15px;">E-Mail ID<font color='Red'>*</font></label>
                    <div><input type="text" name="emailid" class="keyup-email" value="<?php //echo $editdata->emp_email; ?>" placeholder="E-Mail ID..." size="35" required="required" readonly>
                    </div>
                </td> -->
                <td><label for="phonemobileno" style="font-size:15px;"><font color='Blue'>Phone/Mobile</font></label>
                    <div><input type="text" name="phonemobileno" class="keyup-numeric" MaxLength="13" value="<?php echo $editdata->emp_phone; ?>" placeholder="Phone/Mobile No..." size="33" >
                    </div>    
                </td>
                <td><label for="mothertongue" style="font-size:15px;"><font color='Blue'>Mother Tongue</font></label>
                    <div><input type="text" name="mothertongue"  class="keyup-characters" value="<?php echo $editdata->emp_mothertongue; ?>" placeholder="Mother Tongue..." size="33" >
                    </div>    
                </td>
                <td><label for="nativity" style="font-size:15px;"><font color='Blue'>Nativity</font></label>
                    <div><input type="text" name="nativity" class="keyup-characters" value="<?php echo $editdata->emp_citizen; ?>" placeholder="Nativity..." size="33" >
                    </div>    
                </td>
                <td><label for="Qualification" style="font-size:15px;"><font color='Blue'>Qualification</font></label>
                    <div><input type="text" name="qual" class="keyup-characters" value="<?php echo $editdata->emp_qual;?>" placeholder="Qualification........" size="33" >
                </div></td>
            </tr>
            <tr>

		<td ><label for="empgrade" style="font-size:15px;"><font color='Blue'> Grade </font> </label>
                        <div><select name="empgrade" id="empgrade"  style="width:300px;">
			<?php if(!empty($editdata->emp_grade)):;?>
                        <option value="<?php echo $editdata->emp_grade;?>"><?php echo $editdata->emp_grade;?></option>
                        <?php else:?>
                        <option value="">------------ Select Grade ------</option>
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
		<td><label for="maritalstatus" style="font-size:15px;"><font color='Blue'>Marital Status</font></label>
                        <div><select name="maritalstatus" id="maritalstatus"  style="width:300px;">
			<?php if(!empty($editdata->emp_maritalstatus)):;?>
                        <option value="<?php echo $editdata->emp_maritalstatus;?>"><?php echo $editdata->emp_maritalstatus;?></option>
                        <?php else:?>
                        <option selected="" >-------- Marital Status --------</option>
                        <?php endif?>
                        <option value="Married">Married</option>
                        <option value="Unmarried">Unmarried</option>
                        <option value="Divorced">Divorced</option>
                        <option value="Widowed">Widowed</option>
                        </select>
                </div></td>
                <td><label for="seniorityno" style="font-size:15px;"><font color='Blue'>Seniority No</font></label>
                    <div><input type="text" name="seniorityno" class="keyup-characters" value="<?php echo $editdata->emp_seniortyid; ?>" placeholder="Seniority No...." size="33" >
                </div></td>
		<td></td>
            </tr>
            <tr><td colspan="4"><label for="PhD Details " style="font-size:15px;"><b>Ph.D. Details:</b></label></td> </tr>
            <tr>
		<td><label for="phdstatus" style="font-size:15px;"><font color='Blue'>Ph.D. Status</font></label>
                    <div><select name="phdstatus" style="width:300px;">
                        <?php if(!empty($editdata->emp_phd_status)):;?>   
                            <option value="<?php echo $editdata->emp_phd_status;?>"><?php echo $editdata->emp_phd_status;?></option>
                        <?php else:?>
                            <option selected="true" disabled="disabled">--------- Select Phd Status ---------</option>
                        <?php endif?>
                        <option value="Completed">Completed</option>
                        <option value="Undergoing">Undergoing</option>
                        <option value="Not Registered">Not Registered</option>
                    </select><div>
                </td>
                <td><label for="dateofphd" style="font-size:15px;"><font color='Blue'>Date of Ph.D. Completion</font></label>
                    <div><input type="text" name="dateofphd" id="Dateofphd"  value="<?php echo $editdata->emp_dateofphd; ?>" size="33" />
                    </div>    
                </td>
                <td><label for="Discipline " style="font-size:15px;"><font color='Blue'>Discipline</font></label>
		<div> <select id="phddiscipline" style="width:300px;" name="phddiscipline" required>
			 <?php if(!empty($editdata->emp_phddiscipline)):?>
                        <option  value="<?php echo $editdata->emp_phddiscipline;?>"><?php echo $this->commodel->get_listspfic1('subject','sub_name','sub_id',$editdata->emp_phddiscipline)->sub_name;?></option>
                        <?php else:?>
                        <option selected="selected" disabled selected>--------Ph.D. Discipline-----</option>
                        <?php endif?>

                       <?php foreach($this->subject as $subdata): ?>
                                <option class="test" value="<?php echo $subdata->sub_id; ?>"><?php echo $subdata->sub_name; ?></option>
                        <?php endforeach; ?>

                    </select></div>
                <!--<div><input type="text" name="phddiscipline" class="keyup-characters" value="<?php //echo $editdata->emp_phddiscipline;?>" placeholder="PhD Discipline........" size="33" >
                </div> -->
		</td>
		<td><label for="spl" style="font-size:15px;"><font color='blue'>Ph.D. Specialisation</font></label>
                <div><input type="text" name="phdsplname" class="keyup-characters" value="<?php echo $editdata->emp_phdspecialisation;;?>" placeholder="Ph.D. Specialiation." size="33" >
                </div></td>

            </tr>
             <tr>
                <td><label for="phdtype" style="font-size:15px;"><font color='Blue'>Ph.D. Type</font></label>
                    <div><select name="phdtype" style="width:300px;"> 
                        <?php if(!empty($editdata->emp_phdtype)):;?>
                        <option value="<?php echo $editdata->emp_phdtype;?>"><?php echo $editdata->emp_phdtype;?></option>    
                        <?php else:?>
                        <option value="">-----------Select PhD type ----------</option>
                        <?php endif?>
                        <option value="Full time">Full time</option>
                        <option value="Part time">Part time</option>
                    </select></div>
                </td>
                <td><label for="InstName" style="font-size:15px;"><font color='Blue'>University/Institution Name</font></label>
                <div><input type="text" name="phdinstname" class="keyup-characters" value="<?php echo $editdata->emp_phdinstname; ?>" placeholder="PhD Institute Name........" size="33" >
                </div></td>
		 <td><label for="collegeName" style="font-size:15px;"><font color='blue'>College Name</font></label>
                <div><input type="text" name="phdcollname" class="keyup-characters" value="<?php echo $editdata->emp_phdcollege; ?>" placeholder="Ph.D. College Name." size="33" >
                </div></td>
                <td><label for="univdeput" style="font-size:15px;"><font color='Blue'>Whether Deputed by University</font></label>
                    <div><select name="univdeput" id="univdeput" style="width:300px;"> 
                        <?php $udep=$editdata->emp_phdunivdeput;
                            $udepnew=explode(",",$udep);
                        if(!empty($editdata->emp_phdunivdeput)):;?>
                        <option value="<?php echo $udepnew[0] ;?>"><?php echo $udepnew[0];?></option>        
                        <?php else:?>    
                        <option value="">--------- Select Unversity deputed --------</option>
                        <?php endif?>
                        <option value="Yes">Yes</option>
                        <option value="No">No</option>
                    </select></div>
                </td>    
            </tr>
             <tr>
                <td><label for="udeput" style="font-size:15px;"><font color='Blue'>If YES</font> </label>
                <div><input type="radio" name="udeput" id="udeput" value="withsalary" <?php if(!empty($editdata->emp_phdunivdeput)&& $udepnew[0]=== 'Yes'){echo ($udepnew[1] == 'withsalary'?'checked="checked"':'');} ?> >with Salary &nbsp;&nbsp;&nbsp;
                <input type="radio" name="udeput" id="udeput2" value="withoutsalary" <?php if(!empty($editdata->emp_phdunivdeput)&& $udepnew[0]=== 'Yes'){echo ($udepnew[1] == 'withoutsalary'?'checked="checked"':'');} ?> >without Salary
                </div></td>
                <td><label for="udeput" style="font-size:15px;"><font color='Blue'>If NO (Tyoe of Leave availed for Ph.D)</font> </label>
                <div>
                    <select name="udt" id="udt" style="width:300px;">
                        <?php if(!empty($editdata->emp_phdunivdeput) && $udepnew[0]=== 'No'):;?>
                        <option value="<?php echo $udepnew[1] ;?>"><?php 
                        $lname=$this->sismodel->get_listspfic1('leave_type_master','lt_name','lt_id',$udepnew[1])->lt_name;
                        $lcode=$this->sismodel->get_listspfic1('leave_type_master','lt_code','lt_id',$udepnew[1])->lt_code;
                        echo $lname."( ".$lcode." )";?></option>
                        <?php else:?>
                        <option value="">--------- Select Leave Type --------</option>
                        <?php endif?>
                        <?php foreach($this->leavedata as $ldata): ?>	
   				<option value="<?php echo $ldata->lt_id; ?>"><?php echo $ldata->lt_name."( ".$ldata->lt_code." )"; ?></option> 
 			<?php endforeach; ?>
                       
                    </select>   
                </div></td>
                <td><label for="leavedatefrom" style="font-size:15px;"><font color='Blue'>Leave From</font></label>
                    <div><input type="text" name="leavedatefrom" id="leavedatefrom" value="<?php if(!empty($editdata->emp_phdunivdeput)&& $udepnew[0]=== 'No'){echo $udepnew[2];} ?>"class="form-control" size="33" />
                <div></td>
                <td colspan=3><label for="leavedateto" style="font-size:15px;"><font color='Blue'>Leave To</font></label>
                    <div><input type="text" name="leavedateto" id="leavedateto" value="<?php if(!empty($editdata->emp_phdunivdeput)&& $udepnew[0]=== 'No'){echo $udepnew[2];} ?>"class="form-control" size="33" />
                <div></td>
            </tr>
             <tr><td colspan="4"><label for="NET Details: " style="font-size:15px;"><b>NET Details</b></label></td></tr>
            <tr>
                <?php $ntq=$editdata->emp_netqualified;
                    $ntqnew=explode(",",$ntq);
                   // echo $ntqnew[0].$ntqnew[1];
                ;?>
                <td><label for="netqual" style="font-size:15px;"><font color='Blue'>Whether NET Qualified </font> </label>
                <div><input type="radio" name="netqual" id="netqual" value="Yes" <?php echo ($ntqnew[0] == 'Yes'?'checked="checked"':''); ?>>Yes &nbsp;&nbsp;&nbsp;
                <input type="radio" name="netqual" id="netqual2" value="No" <?php echo ($ntqnew[0] == 'No'?'checked="checked"':''); ?>>NO
                </div></td>
		
		 <td>
                <div id="netqyes">
                    <label for="netqualyes" style="font-size:15px;"><font color='blue'>If Yes </font></label><br>
                    <select name="netqualyes" id="netqualyes" style="width:300px;">
                        <?php if(!empty($editdata->emp_netqualified) && $ntqnew[0] == 'Yes'):;?>
                        <option value="<?php echo $ntqnew[1] ;?>"><?php echo $ntqnew[1];?></option>
                        <?php else:?>
                        <option value="">--------- Select Organiser--------</option>
                        <?php endif?>
                        <option value="ICAR">ICAR</option>
                        <option value="CSIR">CSIR</option>
                        <option value="UGC">UGC</option>
                        <option value="TNSET">INSET</option>
                    </select>
                </div>
                <div id="netqno">
                    <label for="netqualno" style="font-size:15px;"><font color='blue'>If No </font></label><br>
                    <select name="netqualno" id="netqualno" style="width:300px;">
                        <?php if(!empty($editdata->emp_netqualified) && $ntqnew[0] == 'No'):;?>
                        <option value="<?php echo $ntqnew[1] ;?>"><?php echo $ntqnew[1];?></option>
                        <?php else:?>
                        <option value="">--------- Select Reason--------</option>
                        <?php endif?>
                        <option value="Exempted_by_Subject">Exempted by Subject</option>
                        <option value="Exempted_by_Year">Exempted by Year</option>
                        <option value="Entry_Level_Ph.D.">Entry Level Ph.D.</option>
                    </select>
                </div>
                </td>

                <td><label for="passdate" style="font-size:15px;"><font color='Blue'>Year of Passing</font></label>
                    <div><input type="text" name="passyear" id="passyear" value="<?php if(!empty($editdata->emp_netqualified) && $ntqnew[0] == 'Yes'){echo $editdata->emp_netpassingyear;} ?>" placeholder="Year of Passing Date " size="33" />
                <div></td>
                <td><label for="netdiscipline" style="font-size:15px;"><font color='Blue'>Discipline</font></label>
                <div><input type="text" name="netdiscipline" id="netdiscipline" class="keyup-characters" value="<?php if(!empty($editdata->emp_netqualified) && $ntqnew[0] == 'Yes'){echo $editdata->emp_netdiscipline ;} ?>" placeholder="NET Discipline........" size="33" >
                </div></td>
            </tr>
            <tr>
            <td colspan="4"><label for="vci " style="font-size:15px;"><b>Veterinary Council (VC) Registration: </b></label></td>   
            </tr>
            <?php  
                    if(!empty($editems)){
                	foreach($editems as $vcrdata){
                            $empid=$vcrdata->ems_empid;
                            $vcrstat = $vcrdata->ems_vci_status;
                            if($vcrdata->ems_vci_status =="Applicable"){
                                $vcrchapter = $vcrdata->ems_vci_statchapter;
                                $vcrregno = $vcrdata->ems_vci_statregno;
                                $vcrregdate = $vcrdata->ems_vci_statregdate;
                                $vcrvaliddate = $vcrdata->ems_vci_statvaliddate;
                           }
                           $alliregno = $vcrdata->ems_vci_alliregno;
                           $alliregdate= $vcrdata->ems_vci_alliregdate;
                           $allivaliddate= $vcrdata->ems_vci_allivaliddate;
                     
                   break; }}
                    
                ?>
            <tr>
                              
                <td colspan="4"><label for="VCI" style="font-size:15px;"></label>
                <div><input type="radio" name="vcrapp" id="vcrapp"  value="Applicable" <?php if(!empty($vcrstat)){ echo ($vcrstat == 'Applicable'?'checked="checked"':'');} ?>>Applicable &nbsp;&nbsp;&nbsp;
                    <input type="radio" name="vcrapp" id="vcrnoapp"  value="Not Applicable" <?php if(!empty($vcrstat)){ echo ($vcrstat == 'Not Applicable'?'checked="checked"':'');} ?>>Not Applicable
                </div></td>
            <!--       <td><label for="secondary emailid" style="font-size:15px;">Secondary Email Id</label>
                <div><input type="text" name="secndemailid" class="keyup-email" value="<?php //echo $editdata->emp_secndemail; ?>" placeholder="Secondary Email Id........" size="28" >
                </div></td>
            -->
            </tr>
            
            <tr>
                        
                <td><label for="chapter" style="font-size:15px;"><font color='Blue'>Chapter</font></label>
                <div>
                    <select name="chapter" id="chapter" style="width:300px;"> 
                        <?php if(!empty($vcrchapter)):;?>
                            <option value="<?php echo $vcrchapter;?>"><?php echo $lname=$this->commodel->get_listspfic1('states','name','id',$vcrchapter)->name;?></option>
                        <?php else:?>
                            <option value="">--------- Select State--------</option>
                        <?php endif?>
                        <?php foreach($this->states as $statesdata): ?>
                        <option value="<?php echo $statesdata->id; ?>"><?php echo $statesdata->name; ?></option>
                        <?php endforeach;?>
                    </select>   
                </div></td>
                <td><label for="vciregno" style="font-size:15px;"><font color='Blue'>Registration No</font></label>
                <div><input type="text" name="vciregno" id="vciregno" class="keyup-characters" value="<?php if(!empty($vcrregno)){ echo $vcrregno; }; ?>" placeholder=" Registration No........" size="33" >
                </div></td>
                <td><label for="vciregdate" style="font-size:15px;"><font color='Blue'>Date of Registration</font></label>
                <div><input type="text" name="vciregdate" id="vciregdate" value="<?php if(!empty($vcrregdate)){ echo $vcrregdate;} ?>" placeholder=" Registration Date........" size="33" >
                </div></td>
                <td><label for="vcrvaliddate" style="font-size:15px;"><font color='Blue'>Valid Upto </font></label>
                <div><input type="text" name="vcrvaliddate" id="vcrvaliddate" value="<?php if(!empty($vcrvaliddate)){echo $vcrvaliddate; }?>" placeholder=" Validity Date........" size="33" >
                </div></td>
            </tr>
            <tr>
            <td colspan="4"><label for="allindiavci " style="font-size:15px;"><b>All India Veterinary Council (VC) Registration: </b></label></td>   
            </tr>
            <tr>
                <td><label for="allvciregno" style="font-size:15px;"><font color='Blue'>Registration No</font></label>
                <div><input type="text" name="allvciregno" id="allvciregno" class="keyup-characters" value="<?php if(!empty($alliregno)){echo $alliregno; }?>" placeholder="VCI Registration No........" size="33" >
                </div></td>
                <td><label for="allvciregdate" style="font-size:15px;"><font color='Blue'>Date of Registration</font></label>
                <div><input type="text" name="allvciregdate" id="allvciregdate" value="<?php if(!empty($alliregdate)){ echo $alliregdate;} ?>" placeholder="VCI Registration Date........" size="33" >
                </div></td>
                <td colspan="2"><label for="allvcrvaliddate" style="font-size:15px;"><font color='Blue'><b>Valid Upto </font></b></label>
                <div><input type="text" name="allvcrvaliddate" id="allvcrvaliddate" value="<?php if(!empty($allivaliddate)){ echo $allivaliddate;} ?>" placeholder="VCI Validity Date........" size="33" >
                </div></td>
            </tr>
            <tr>
                <td colspan="4"><label for="addasign" style="font-size:15px;"><b>Additional Assignments:</b></label></td>
            </tr>
<?php		if(!empty($editasign)){
                	foreach($editasign as $recod){
				$aaname = $recod->aa_asigname;
				$aafrom = $recod->aa_asigperiodfrom;
				$aato = $recod->aa_asigperiodto;
				$aaplace = $recod->aa_place;
				
	 break; }}?>
            <tr>
              <td><label for="asignname" style="font-size:15px;"><font color='Blue'>Name of the Assignment</font> </label>
                <div>
                    <select name="asignname" id="asignname" style="width:300px;">
                        <?php //if(!empty($editasign->aa_asigname)):;?>
                        <?php if(!empty($aaname)):;?>
                        <?php //$aaname=$editasign->aa_asigname;
                        $aanew=$ntqnew=explode(",",$aaname);
                       // if($aanew[0] === 'Others'):;?>
                        <option value="<?php echo $aanew[0];?>"><?php echo $aanew[0];?></option>
                       <!-- <option value="<?php //echo $editasign->aa_asigname ;?>"><?php //echo $editasign->aa_asigname ;?></option> -->
                        <?php else:?>
                        <option value="">--------- Select Assignment Name --------</option>
                        <?php endif?>
                        <option value="warden">Warden</option>
                        <option value="Deputy Warden">Deputy Warden</option>
                        <option value="Guest House Incharge">Guest House Incharge</option>
                        <option value="NSS Corordinator">NSS Corordinator</option>
                        <option value="NCC Officer">NCC Officer</option>
                        <option value="Resident Veterinary Officer">Resident Veterinary Officer</option>
                        <option value="Canteen Incharge">Canteen Incharge</option>
                        <option value="Others">Others</option>
                    </select>   
                </div></td> 
                <td><label for="asignother" style="font-size:15px;"><font color='Blue'>Others</font></label>
                    
<!--                <div><input type="text" name="asignother" id="asignother" class="keyup-characters" value="<?php if(!empty($editasign->aa_asigname) && $aanew[0]=='Others'){ echo $aanew[1];} ?>" placeholder="Others........" size="28" >-->
                <div><input type="text" name="asignother" id="asignother" class="keyup-characters" value="<?php if(!empty($aaname) && $aanew[0]=='Others'){ echo $aanew[1];} ?>" placeholder="Others........" size="33" >
            <!--</tr>
            <tr>-->
                
                <td><label for="asigndatefrom" style="font-size:15px;"><font color='Blue'>Date From</font></label>
  <!--              <div><input type="text" name="asigndatefrom" id="asigndatefrom" value="<?php  if(!empty($editasign->aa_asigperiodfrom)){echo $editasign->aa_asigperiodfrom;} ?>" placeholder="Date From........" size="28" > -->
                <div><input type="text" name="asigndatefrom" id="asigndatefrom" value="<?php  if(!empty($aafrom)){echo $aafrom;} ?>" placeholder="Date From........" size="33" >
                </div></td>
                <td><label for="asigndateto" style="font-size:15px;"><font color='Blue'>Date To</font></label>
<!--                <div><input type="text" name="asigndateto" id="asigndateto" value="<?php if(!empty($editasign->aa_asigperiodto)){echo $editasign->aa_asigperiodto ;} ?>" placeholder="Date To ........" size="28" >-->
                <div><input type="text" name="asigndateto" id="asigndateto" value="<?php if(!empty($aato)){echo $aato ;} ?>" placeholder="Date To ........" size="33" >
                </div></td>
            </tr>
            <tr>
                <td colspan=4><label for="asignplace" style="font-size:15px;"><font color='Blue'>Assignment Place</font></label>
               <!-- <div><input type="text" name="asignplace" class="keyup-characters" value="<?php if(!empty($editasign->aa_place)){echo $editasign->aa_place;} ?>" placeholder="Place........" size="28" >-->
                <div><input type="text" name="asignplace" class="keyup-characters" value="<?php if(!empty($aaplace)){echo $aaplace;} ?>" placeholder="Place........" size="33" >
                </div></td>
<?php 
			$pref1 = $this->sismodel->get_listspfic1('employee_master_support','ems_pwplace1','ems_empid',$emp_id)->ems_pwplace1;
			if(!empty($pref1)){
				$prefloc1 = $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$pref1)->sc_name;
			}else{
				$prefloc1='';
			}
			$pref2 = $this->sismodel->get_listspfic1('employee_master_support','ems_pwplace2','ems_empid',$emp_id)->ems_pwplace2;
			if(!empty($pref2)){
				$prefloc2 = $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$pref2)->sc_name;
			}else{
				$prefloc2='';
			}
			$pref3 = $this->sismodel->get_listspfic1('employee_master_support','ems_pwplace3','ems_empid',$emp_id)->ems_pwplace3;
			if(!empty($pref3)){
				$prefloc3 = $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$pref3)->sc_name;
			}else{
				$prefloc3='';
			}
?>
		 <tr>
            <td colspan=4><label for="pref" style="font-size:15px;"><b>Preferred Place of Working: </b></label></td>
            </tr>
                <tr>
		 <td><label for="pref1" style="font-size:15px;"><font color='blue'>First Preference</font> <font color='Red'></font></label>
                    <div> <select id="ppwpref1" style="width:300px;" name="ppwpref1" >
			<option value="<?php echo $pref1;?>"><?php echo $prefloc1;?></option>
                       <?php foreach($this->campus as $camdata): ?>
                                <option class="test" value="<?php echo $camdata->sc_id; ?>"><?php echo $camdata->sc_name; ?></option>
                        <?php endforeach; ?>

                    </select></div>
                </td>
                <td><label for="pref2" style="font-size:15px;"><font color='blue'> Second Preference </font> <font color='Red'></font></label>
                    <div> <select id="ppwpref2" style="width:300px;" name="ppwpref2" >
			<option value="<?php echo $pref2;?>"><?php echo $prefloc2?></option>
                       <?php foreach($this->campus as $camdata): ?>
                                <option class="test" value="<?php echo $camdata->sc_id; ?>"><?php echo $camdata->sc_name; ?></option>
                        <?php endforeach; ?>

                    </select></div>
                </td>
                <td><label for="pref3" style="font-size:15px;"><font color='blue'> Third Preference </font> <font color='Red'></font></label>
                    <div> <select id="ppwpref3" style="width:300px;" name="ppwpref3" >
			<option value="<?php echo $pref3;?>"><?php echo $prefloc3;?></option>
                       <?php foreach($this->campus as $camdata): ?>
                                <option class="test" value="<?php echo $camdata->sc_id; ?>"><?php echo $camdata->sc_name; ?></option>
                        <?php endforeach; ?>

                    </select></div>
                </td>
		<td></td>
	</tr><tr>
                <td><label for="remarks" style="font-size:15px;"><font color='Blue'>Remarks</font></label>
                    <div><textarea name="remarks" rows="3" cols="40"  ><?php echo $editdata->emp_remarks;?></textarea>
                </div></td>
                <td><label for="Address" style="font-size:15px;"><font color='Blue'>Residential Address</font></label>
                    <div><textarea name="Address"  class="keyup-characters" rows="4" cols="40" pattern="[a-zA-Z0-9 ]+"><?php echo $editdata->emp_address;?></textarea>
                    </div>
                </td>    
                    <!--    <input type="text" name="Address" class="keyup-characters" value="<//?php echo $editdata->emp_address; ?>" placeholder="Address..." size="25" ></td>-->
                
                <td colspan=2><label for="userfile" style="font-size:15px;"><font color='Blue'>Upload Photo</font></label>
                    <div><input type='file' name='userfile'  accept="image/*" onchange="preview_image(event)" size='20' />
                    
                  <!--  </div>
                                            
                <div>-->
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
                    <img src="<?php echo base_url('uploads/SIS/empphoto/'.$editdata->emp_photoname);?>"  alt="" v:shapes="_x0000_i1025" width="78" height="94">
                        
                <?php else:?>
                    <img src="<?php echo base_url('uploads/SIS/empphoto/'."empdemopic.png");?>"  id="output_image" v:shapes="_x0000_i1025" width="78" height="94"/>
                <?php endif?>   
                 </div></td>    
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
