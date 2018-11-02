<!--@filename staffprofile.php  @author Manorama Pal(palseema30@gmail.com)
re-engineering in add profile according to tanuvas structure - 16 OCT 2017 
-->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Welcome to TANUVAS</title>
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
            
            $('#StartDate,#StartDatevc,#Dateofhgp1,#Dateofphd,#Dateofbirth,#allvciregdate,#vciregdate').datepicker({
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
	    $('#Dateofprob,#Dateofregular,#asigndatefrom,#asigndateto,#leavedatefrom,#leavedateto,#allvcrvaliddate,#vcrvaliddate').datepicker({
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
		var grp_id =  $('#grpid').val();
		var wrktype_id = $('#worktypeid').val();

		if(((grp_id =='B') && (wrktype_id == "Non Teaching"))||((grp_id =='C') && (wrktype_id == "Non Teaching"))){
	                var retDate = new Date(birthDate.getFullYear() + 58, birthDate.getMonth(), birthDate.getDate()-1);
		}else{
			var retDate = new Date(birthDate.getFullYear() + 60, birthDate.getMonth(), birthDate.getDate()-1);
		}
                //alert(retDate);
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
				//asort(data);
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
              //  alert("selval===="+selval);
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
              //  alert("asignval===="+asignval);
                if(asignval === 'Others'){
                    $('#asignother').prop('disabled',false);
                                      
                }
                else{
                    $('#asignother').prop('disabled',true);
                   
                    
                    
                }
            }); 
            
           /**************** Additional Assignments closer*************************************************************************/
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
  <style>
.tooltip {
    position: relative;
/*    display: inline-block;*/
/*    border-bottom: 1px dotted black; */
}

.tooltip .tooltiptext {
    visibility: hidden;
    width: 120px;
    background-color: black;
    color: #fff;
    text-align: center;
    border-radius: 6px;
    padding: 5px 0;

    /* Position the tooltip */
    position: absolute; 
    z-index: 1;
}

.tooltip:hover .tooltiptext {
    visibility: visible;
}
</style> 
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
        <table width="100%" style="margin-left:0%;border:1px solid gray;" class="TFtable">
            
            <?php  echo form_open_multipart('staffmgmt/staffprofile','id="my_id"');?>
            <tr><thead><th style="background-color:#2a8fcf;text-align:left;height:40px;" colspan="4">&nbsp;&nbsp;Staff Profile Form</th></thead></tr>
            <div style="margin-left:10%;">
            <tr>
                <td><label for="campus" style="font-size:15px;"><font color='blue'>Campus Name</font> <font color='Red'>*</font></label>
                    <div> <select id="camp" style="width:300px;" name="campus" required> 
                        <option selected="selected" disabled selected>--------Campus Name-----</option>
                       <?php foreach($this->campus as $camdata): ?>	
   				<option class="test" value="<?php echo $camdata->sc_id; ?>"><?php echo $camdata->sc_name; ?></option> 
 			<?php endforeach; ?>
                      
                    </select></div>
                </td> 
                <!--In future this code may be replace when either campusid added in the 
                authority or authority added in campus.-->
                <td><label for="uocontrol" style="font-size:15px;"><font color='blue'>University Officer Control</font><font color='Red'>*</font></label>
                    <div><select name="uocontrol" style="width:300px;"id="uocid" required> 
                        <option selected="selected" disabled selected>--------University Officer Control -----</option>
                       
                    </select></div>
                </td>
                <td><label for="department" style="font-size:15px;"><font color='blue'>Department</font><font color='Red'>*</font></label>
                    <div><select required name="department"  style="width:300px;" id="scid"> 
                        <option selected="selected" disabled selected >--------Select Department--------</option>
                       
                    </select></div>
                </td>
                <td><label for="schemecode" style="font-size:15px;"><font color='blue'>Scheme Name</font><font color='Red'>*</font></label>
                    <div><select required name="schemecode" id="schmid" style="width:300px;"> 
                        <option selected="selected" disabled selected>-----------Scheme Name -----------</option>
                        
                    </select><div>
                </td>
            </tr>
            
            <tr>
            <td><label for="ddo" style="font-size:15px;"><font color='blue'>Drawing and Disbursing Officer</font><font color='Red'>*</font></label>
                    <div><select name="ddo" id="ddoid" required style="width:300px;"> 
                        <option selected="selected" disabled selected>--------- Drawing and Disbursing Officer-----</option>
                    </select></div>
                </td>
                <td><label for="workingtype" style="font-size:15px;"><font color='blue'>Working Type</font><font color='Red'>*</font></label>
                        <div><select id="worktypeid" name="workingtype" required style="width:300px;"> 
                        <option selected="selected" disabled selected>------------- Working Type -------------</option>
                        <option value="Teaching">Teaching</option>
                        <option value="Non Teaching">Non Teaching</option>
                    </select></div>
                </td> 
                <td><label for="group" style="font-size:15px;"><font color='blue'>Group</font><font color='Red'>*</font></label>
                       <div><select name="group" id="grpid" required style="width:300px;"> 
                        <option selected="selected" disabled selected>------------ Select Group ---------</option>
                        <option value="A">A</option>
                        <option value="B">B</option>
                        <option value="C">C</option>
                        <option value="D">D</option>
                    </select></div>
                </td>
                <td><label for="designation" style="font-size:15px;"><font color='blue'>Designation</font><font color='Red'>*</font></label>
                    <div><select name="designation" id="desigid" required style="width:300px;"> 
                        <option selected="selected" disabled selected>------- Select Designation ---------</option>
                        
                    </select></div>
                </td>
              
            </tr>
          
            <tr>
                <td><label for="emppost" style="font-size:15px;"><font color='blue'>Shown Against The Post</font><font></font></label>
                   <div><select name="emppost" id="emppostid" required style="width:300px;"> <!--<input type="text" id="emppostid" name="emppost" value="<//?php echo isset($_POST["emppost"]) ? $_POST["emppost"] : ''; ?>" placeholder="Employee Post..." size="35">-->
                    <!--<input type="text" id="emppost" name="emppost"  readonly placeholder="Employee Post..." size="35">-->
                    <option selected="selected" disabled selected>------------------ Select Post ------------------</option>
                    </select></div>
                </td>
                 <td><label for="pnp" style="font-size:15px;"><font color='blue'>Plan / Non Plan</font></label>
                    <div><select name="pnp" id="pnptypeid" style="width:300px;"> 
                         <option value="">-------------- Plan/Non Plan ------------</option>
            <!--            <option value="Plan">Plan</option>
                        <option value="Non-Paln">Non Plan</option>
                        <option value="GOI">GOI</option>
                        <option value="ICAR">ICAR</option>  -->
                    </select></div>
                </td>
                <td><label for="emptype" style="font-size:15px;"><font color='blue'>Employee Type</font><font color='Red'>*</font></label>
                    <div><select id="emptypeid" name="emptype" required style="width:300px;"> 
                        <option selected="selected" disabled selected>-------- Select Employee Type ------</option>
                        <!--<option value="Permanent">Permanent</option>
                        <option value="Temporary">Temporary</option>-->
                    </select><div>
                </td> 
                <td><label for="empcode" style="font-size:15px;"><font color='blue'>Employee PF No</font><font color='Red'>*</font></label>
                    <div><input type="text" name="empcode" class="keyup-characters" value="<?php echo isset($_POST["empcode"]) ? $_POST["empcode"] : ''; ?>" placeholder="employee PF No..." size="33"  required pattern="[a-zA-Z0-9 ]+" required="required">
                </div></td>
              
            </tr>
            <!--<tr style="height:10px;"></tr>-->
            <tr>
                <td><label for="empname" style="font-size:15px;"><font color='blue'>Employee Name</font> <font color='Red'>*</font></label>
                <div class="tooltip">
			
		<input type="text" name="empname"  class="keyup-characters" value="<?php echo isset($_POST["empname"]) ? $_POST["empname"] : ''; ?>" placeholder="Mohan H. M" size="33" required="required">
		<span class="tooltiptext">Mohan H. M</span>
                </div></td>
                <td ><label for="fathername" style="font-size:15px;"><font color='blue'>Fathers Name</font></label>
                    <div class="tooltip"><input type="text" name="fathername" class="keyup-characters" value="<?php echo isset($_POST["fathername"]) ? $_POST["fathername"] : ''; ?>" placeholder="Ram M. H. D" size="33" >
			<span class="tooltiptext">Ram M. H. D</span>
                </div></td>
                <td><label for="spousename" style="font-size:15px;"><font color='blue'>Spouse Name</font></label>
                    <div class="tooltip"><input type="text" name="spousename" class="keyup-characters" value="<?php echo isset($_POST["spousename"]) ? $_POST["spousename"] : ''; ?>" placeholder="Teetha S. P. R" size="33" >
		<span class="tooltiptext">Teetha S. P. R</span>
                </div></td>
                <td><label for="orderno" style="font-size:15px;"> <font color='blue'> Appointment Order No</font></label>
                    <div><input type="text" name="orderno"  value="<?php echo isset($_POST["orderno"]) ? $_POST["orderno"] : ''; ?>" placeholder=" application order No..." size="33">
                </div></td>
            </tr>
            <!--<tr style="height:10px;"></tr>-->
            <tr>
                <td><label for="specialisation" style="font-size:15px;"><font color='blue'>Specialisation(Major Subject)</font></label>
                    <div><select name="specialisation" style="width:300px;"> 
                        <option value="0">----------- Major Subject -----------</option>
                        <?php foreach($this->subject as $subdata): ?>	
   				<option value="<?php echo $subdata->sub_id; ?>"><?php echo $subdata->sub_name; ?></option> 
 			<?php endforeach; ?>
                       
                    </select></div>
                </td>
               
                 <td><label for="gender" style="font-size:15px;"><font color='blue'>Gender</font></label>
                    <div><select name="gender" style="width:300px;"> 
                        <option value="">---------------- Select Gender ------------------</option>
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                        <option value="Transgender">Transgender</option>
                    </select></div>
                </td> 
                    
                <td><label for="community" style="font-size:15px;"><font color='blue'>Community</font></label>
                    <div><select name="community" style="width:300px;"> 
                        <option value="">----------- Select Community -----------</option>
                         <?php foreach($this->community as $communitydata): ?>	
   				<option value="<?php echo $communitydata->cat_name; ?>"><?php echo $communitydata->cat_name; ?></option> 
 			<?php endforeach; ?>
                    </select></div>
                </td> 
                                    
                 <td><label for="religion" style="font-size:15px;"><font color='blue'>Religion</font></label>
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
            </tr>
            <!--<tr style="height:10px;"></tr>-->
            <tr>
                <td><label for="caste" style="font-size:15px;"><font color='blue'>Caste</font></label>
                    <div><input type="text" name="caste" value="<?php echo isset($_POST["caste"]) ? $_POST["caste"] : ''; ?>" placeholder="Caste..." size="33" >
                </div></td>
                       

                <td><label for="payband" style="font-size:15px;"><font color='blue'>Pay Band</font><font color='Red'>*</font></label>
                    <div><select name="payband" id="payband" required style="width:300px;"> 
                        <option selected="selected" disabled selected>------------------ Select Pay Band -------------</option>
                        <?php //foreach($this->salgrd as $salgrddata): ?>	
                      <!--      <option value="<?php //echo $salgrddata->sgm_id; ?>"><?php //echo $salgrddata->sgm_name."(". $salgrddata->sgm_min."-".$salgrddata->sgm_max.")".$salgrddata->sgm_gradepay; ?>
                            </option> -->
 			<?php //endforeach; ?>
                       
                    </select></div>
                </td>
		 <td><label for="newpayband" style="font-size:15px;"><font color='blue'>New Pay Band</font><font color='Red'></font></label>
                    <div><select name="newpayband"  id="newpayband" style="width:300px;">
                        <option selected="selected" disabled selected>--- Select New Pay Band ---</option>
<!--                        <?php //foreach($this->salgrdn as $salgrddatan): ?>
                            <option value="<?php //echo $salgrddatan->sgm_id; ?>"><?php //echo $salgrddatan->sgm_name."(". $salgrddatan->sgm_min."-".$salgrddatan->sgm_max.")".$salgrddatan->sgm_level; ?>
                            </option>
                        <?php //endforeach; ?>
-->
                    </select></div>
                </td>

                <td><label for="basicpay" style="font-size:15px;"><font color='blue'>Basic Pay</font></label>
                    <div><input type="text" name="basicpay"  class="keyup-numeric" value="<?php echo isset($_POST["basicpay"]) ? $_POST["basicpay"] : ''; ?>" placeholder="Basic pay..." size="33" >
                </div></td> 
                                       
            </tr>
            <!--<tr style="height:10px;"></tr>-->
            <tr>
                <td><label for="emolution" style="font-size:15px;"><font color='blue'>Emolution</font></label>
                    <div><input type="text" name="emolution" class="keyup-numeric" value="<?php echo isset($_POST["emolution"]) ? $_POST["emolution"] : ''; ?>" placeholder="Emolution..." size="33" >
                </div></td> 
                <td><label for="empnhisidno" style="font-size:15px;"><font color='blue'>NHIS ID No</font></label>
                    <div><input type="text" name="empnhisidno" class="keyup-characters" value="<?php echo isset($_POST["empnhisidno"]) ? $_POST["empnhisidno"] : ''; ?>" placeholder="NHIS ID NO..." size="33">
                </div></td>
                
                <td><label for="phstatus" style="font-size:15px;"><font color='blue'>Whether Physically Handicapped</font><font color='Red'>*</font>  </label>
                <div><input type="radio" name="phstatus" value="yes">Yes &nbsp;&nbsp;&nbsp;
                <input type="radio" name="phstatus" value="no">No
                </div></td>
                <td><label for="phdetail" style="font-size:15px;"><font color='blue'>Details Of PH</font></label>
                <div><input type="text" name="phdetail" class="keyup-characters" value="<?php echo isset($_POST["phdetail"]) ? $_POST["phdetail"] : ''; ?>" placeholder="Details of PH..." size="33">
                </div></td>
            </tr>
            <!--<tr style="height:10px;"></tr>-->
            <tr>
                <td><label for="Sabgroup" style="font-size:15px;"><font color='blue'>Blood Group</font></label>
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
                         
                <td><label for="dateofjoining" style="font-size:15px;"><font color='blue'>Date Of Appointment</font><font color='Red'>*</font></label>
                    <div><input type="text" name="dateofjoining" value="<?php echo isset($_POST["dateofjoining"]) ? $_POST["dateofjoining"] : ''; ?>" id="StartDate"  size="15" required="required">
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
			<label for="dateofjoiningvc" style="font-size:15px;"><font color='blue'>Date Of Appointment as VC</font><font color='Red'></font></label><br>
                    <input type="text" name="dateofjoiningvc" value="<?php echo isset($_POST["dateofjoiningvc"]) ? $_POST["dateofjoiningvc"] : ''; ?>" id="StartDatevc"  size="33" >
                  <!--      <select name="jsession" style="width:140px;" id="jsession" required>
                                <option selected="selected" disabled selected>Select Session</option>
                                <option value="Forenoon">Forenoon</option>
                                <option value="Afternoon">Afternon</option>
                        </select>-->
                     </div>
		<?php } ?>
                </td>
                <td><label for="DateofBirth" style="font-size:15px;"><font color='blue'>Date Of Birth</font><font color='Red'>*</font></label>
                    <div><input type="text" name="DateofBirth" id="Dateofbirth" value="<?php echo isset($_POST["DateofBirth"]) ? $_POST["DateofBirth"] : ''; ?>"  size="33" required="required">
                </div></td>     
            </tr>
            <!--<tr style="height:10px;"></tr>-->
            <tr>
                <td><label for="dateofretirement" style="font-size:15px;"><font color='blue'>Date Of Retirement</font></label>
                    <div><input type="text" name="dateofretirement" value="<?php echo isset($_POST["dateofretirement"]) ? $_POST["dateofretirement"] : ''; ?>" id="Dateofretir" class="form-control" size="33" />
                </div></td>
                <td><label for="dateofprob" style="font-size:15px;"><font color='blue'>Date of Probation</font></label>
                    <div><input type="text" name="dateofprob" id="Dateofprob" value="<?php echo isset($_POST["dateofprob"]) ? $_POST["dateofprob"] : ''; ?>"class="form-control" size="33" />
                <div></td>
                 <td><label for="dateofregular" style="font-size:15px;"><font color='blue'>Date of Regularisation</font></label>
                    <div><input type="text" name="dateofregular" id="Dateofregular" value="<?php echo isset($_POST["dateofregular"]) ? $_POST["dateofregular"] : ''; ?>"class="form-control" size="33" />
                <div></td> 
                <td><label for="assrexam" style="font-size:15px;"><font color='blue'>ASRR Exam</font></label>
                    <div><select name="assrexam" style="width:300px;"> 
                        <option value="">---------------- ASRR Exam Status -------------</option>
                        <option value="Passed">Passed</option>
			<option value="Not Qualified">Not Qualified</option>
                        <option value="Not Appeared">Not Appeared</option>
                        <option value="Not Registered">Not Registered</option>
                        <option value="Not Applicable">Not Applicable</option>
                    </select></div>
                </td>
            </tr>
            <!--<tr style="height:10px;"></tr>-->
            <tr>
                <td><label for="assrexamdate" style="font-size:15px;"><font color='blue'>ASRR Passed(MM-YYYY)</font></label>
                    <div><input type="text" name="assrexamdate" id="Dateofassrexam" value="<?php echo isset($_POST["assrexamdate"]) ? $_POST["assrexamdate"] : ''; ?>"class="form-control" size="33" />
                <div></td>    
                
                <td><label for="dateofhgp" style="font-size:15px;"><font color='blue'>Date Of HGP</font></label>
                    <div><input type="text" name="dateofhgp" id="Dateofhgp1" value="<?php echo isset($_POST["dateofhgp"]) ? $_POST["dateofhgp"] : ''; ?>" class="form-control" size="33" />
                </div></td>
                
                <td><label for="panno" style="font-size:15px;"><font color='blue'>Pan No</font></label>
                    <div><input type="text" name="panno" id="txtPANNumber" MaxLength="10" value="<?php echo isset($_POST["panno"]) ? $_POST["panno"] : ''; ?>" placeholder="Pan No..." size="33" >
                </div></td> 
               
                <td><label for="bankname" style="font-size:15px;"><font color='blue'>Bank Name</font></label>
                    <div><input type="text" name="bankname" class="keyup-characters" value="<?php echo isset($_POST["bankname"]) ? $_POST["bankname"] : ''; ?>" placeholder="Bank Name..." size="33" >
                </div></td>
            </tr>
            <!--<tr style="height:10px;"></tr>-->
            <tr>
                <td><label for="ifsccode" style="font-size:15px;"><font color='blue'>IFSC Code</font></label>
                    <div><input type="text" name="ifsccode" class="keyup-characters" value="<?php echo isset($_POST["ifsccode"]) ? $_POST["ifsccode"] : ''; ?>" placeholder="IFSC CODE..." size="33" >
                </div></td>
                <td><label for="bankacno" style="font-size:15px;"><font color='blue'>Bank ACC No</font><font color='Red'></font></label>
                   <div><input type="text" name="bankacno" class="keyup-characters" value="<?php echo isset($_POST["bankacno"]) ? $_POST["bankacno"] : ''; ?>" placeholder="Bank Acc No..." size="33" >
                </div></td>
                <td><label for="Aadharrno" style="font-size:15px;"><font color='blue'>Aadhaar No</font><font color='Red'></font></label>
                    <div><input type="text" name="Aadharrno" class="keyup-numeric" MaxLength="12" value="<?php echo isset($_POST["Aadharrno"]) ? $_POST["Aadharrno"] : ''; ?>" placeholder="Aadharr No..." size="33" >
                </div></td>
                            
                
                <td><label for="emailid" style="font-size:15px;"><font color='blue'>E-Mail ID</font><font color='Red'></font></label>
                   <div><input type="text" name="emailid" class="keyup-email" value="<?php echo isset($_POST["emailid"]) ? $_POST["emailid"] : ''; ?>" placeholder="E-Mail ID..." size="33" required="required">
                </div></td>
            </tr>
            <tr>
                <td><label for="phonemobileno" style="font-size:15px;"><font color='blue'>Phone/Mobile</font></label>
                    <div><input type="text" name="phonemobileno" class="keyup-numeric" MaxLength="13" value="<?php echo isset($_POST["phonemobileno"]) ? $_POST["phonemobileno"] : ''; ?>" placeholder="Phone/Mobile No..." size="33" >
                </div></td>
                <td><label for="mothertongue" style="font-size:15px;"><font color='blue'>Mother Tongue</font></label>
                    <div><input type="text" name="mothertongue"  class="keyup-characters" value="<?php echo isset($_POST["mothertongue"]) ? $_POST["mothertongue"] : ''; ?>" placeholder="Mother Tongue..." size="33" >
                </div></td> 
                 <td><label for="nativity" style="font-size:15px;"><font color='blue'>Nativity</font></label>
                    <div><input type="text" name="nativity" class="keyup-characters" value="<?php echo isset($_POST["nativity"]) ? $_POST["nativity"] : ''; ?>" placeholder="Nativity..." size="33" >
                 </div></td>
                <td><label for="Qualification" style="font-size:15px;"><font color='blue'>Qualification</font></label>
                    <div><input type="text" name="qual" class="keyup-characters" value="<?php echo isset($_POST["qual"]) ? $_POST["qual"] : ''; ?>" placeholder="Qualification........" size="33" >
                </div></td>
</tr><tr>
                <td><label for="empgrade" style="font-size:15px;"><font color='blue'> Grade </font> </label>
                        <div><select name="empgrade" id="empgrade"  style="width:300px;">
                        <option selected="selected" disabled selected >-------- Select Grade --------</option>        
			<!--<option value="">------------ Select Grade ---------</option>
                       	<option value="Career Advance (CA)">Career Advance (CA)</option>
                        <option value="Regular(R)">Regular (R)</option>
			<option value="Selection Grade (SG)">Selection Grade (SG)</option>
                        <option value="Special Grade (SplG)">Special Grade (SplG)</option> -->
			</select></div>
                </td>
		<td><label for="maritalstatus" style="font-size:15px;"><font color='blue'>Marital Status</font></label>
			<div><select name="maritalstatus" id="maritalstatus"  style="width:300px;">
                        <option selected="selected" disabled selected >-------- Marital Status --------</option>
                        <option value="Married">Married</option>
                        <option value="Unmarried">Unmarried</option>
                        <option value="Divorced">Divorced</option>
                        <option value="Widowed">Widowed</option>
                        </select>
                </div></td>
                <td><label for="seniorityno" style="font-size:15px;"><font color='blue'>Seniority No</font></label>
                    <div><input type="text" name="seniorityno" class="keyup-characters" value="<?php echo isset($_POST["seniorityno"]) ? $_POST["seniorityno"] : ''; ?>" placeholder="Seniority No...." size="33" >
                </div></td>
                <td><label for="secondary emailid" style="font-size:15px;"><font color='blue'>Secondary Email Id</font></label>
                <div><input type="text" name="secndemailid" class="keyup-email" value="<?php echo isset($_POST["secndemailid"]) ? $_POST["secndemailid"] : ''; ?>" placeholder="Secondary Email Id........" size="33" >
                </div></td>
		                         
            </tr>
            <tr>
		<td><label for="PhD Details " style="font-size:15px;"><b>Ph.D. Details:</b></label></td> </tr>
            <tr>
                <td><label for="phdstatus" style="font-size:15px;"><font color='blue'>Ph.D. Status</font></label>
                    <div><select name="phdstatus" style="width:300px;"> 
                        <option value="">-------------- Phd Status -------------</option>
                        <option value="Completed">Completed</option>
			<option value="Undergoing">Undergoing</option>
                        <option value="Not Registered">Not Registered</option>
                    </select></div>
                </td>
                <td><label for="dateofphd" style="font-size:15px;"><font color='blue'>Date Of Ph.D. Completion</font></label>
                    <div><input type="text" name="dateofphd" id="Dateofphd"  value="<?php echo isset($_POST["dateofphd"]) ? $_POST["dateofphd"] : ''; ?>" size="33" />
                </div></td>    
                <td><label for="Discipline " style="font-size:15px;"><font color='blue'>Discipline</font></label>
                    <div> <select id="phddiscipline" style="width:300px;" name="phddiscipline" required> 
                        <option selected="selected" disabled selected>--------Ph.D. Discipline-----</option>
                       <?php foreach($this->subject as $subdata): ?>	
   				<option class="test" value="<?php echo $subdata->sub_id; ?>"><?php echo $subdata->sub_name; ?></option> 
 			<?php endforeach; ?>
                      
                    </select></div>
                <!--<div><input type="text" name="phddiscipline" class="keyup-characters" value="<?php //echo isset($_POST["phddiscipline"]) ? $_POST["phddiscipline"] : ''; ?>" placeholder="PhD Discipline........" size="33" >
                </div>-->
		</td>
		 <td><label for="spl" style="font-size:15px;"><font color='blue'>Ph.D. Specialisation</font></label>
                <div><input type="text" name="phdsplname" class="keyup-characters" value="<?php echo isset($_POST["phdsplname"]) ? $_POST["phdsplname"] : ''; ?>" placeholder="Ph.D. Specialiation." size="33" >
                </div></td>
            </tr>
            <tr>
                <td><label for="phdtype" style="font-size:15px;"><font color='blue'>Ph.D. Type</font></label>
                    <div><select name="phdtype" style="width:300px;"> 
                        <option value="">-------------PhD type ----------</option>
                        <option value="Full time">Full time</option>
                        <option value="Part time">Part time</option>
                    </select></div>
                </td>
                <td><label for="InstName" style="font-size:15px;"><font color='blue'>University/Institution Name</font></label>
                <div><input type="text" name="phdinstname" class="keyup-characters" value="<?php echo isset($_POST["phdinstname"]) ? $_POST["phdinstname"] : ''; ?>" placeholder="Ph.D. University/Institution Name." size="33" >
                </div></td>
		 <td><label for="collegeName" style="font-size:15px;"><font color='blue'>College Name</font></label>
                <div><input type="text" name="phdcollname" class="keyup-characters" value="<?php echo isset($_POST["phdcollname"]) ? $_POST["phdcollname"] : ''; ?>" placeholder="Ph.D. College Name." size="33" >
                </div></td>
                <td><label for="univdeput" style="font-size:15px;"><font color='blue'>Whether Deputed by University</font></label>
                    <div><select name="univdeput" id="univdeput" style="width:300px;"> 
                        <option value="">--------- Select University deputed --------</option>
                        <option value="Yes">Yes</option>
                        <option value="No">No</option>
                    </select></div>
                </td>    
            </tr>
            <tr>
                <td><label for="udeput" style="font-size:15px;"><font color='blue'>If YES </font> </label>
                <div><input type="radio" name="udeput" id="udeput" value="withsalary">with Salary &nbsp;&nbsp;&nbsp;
                <input type="radio" name="udeput" id="udeput2" value="withoutsalary">without Salary
                </div></td>
                <td><label for="udeput" style="font-size:15px;"><font color='blue'>If NO (Tyoe of Leave availed for Ph.D.)</font>  </label>
                <div>
                    <select name="udt" id="udt" style="width:300px;"> 
                        <option value="">--------- Select Leave Type --------</option>
                        <?php foreach($this->leavedata as $ldata): ?>	
   				<option value="<?php echo $ldata->lt_id; ?>"><?php echo $ldata->lt_name."( ".$ldata->lt_code." )"; ?></option> 
 			<?php endforeach; ?>
                       
                    </select>   
                </div></td>
                <td><label for="leavedatefrom" style="font-size:15px;"><font color='blue'>Leave From</font></label>
                    <div><input type="text" name="leavedatefrom" id="leavedatefrom" value="<?php echo isset($_POST["leavedatefrom"]) ? $_POST["leavedatefrom"] : ''; ?>"class="form-control" size="33" />
                <div></td>
                <td><label for="leavedateto" style="font-size:15px;"><font color='blue'>Leave To</font></label>
                    <div><input type="text" name="leavedateto" id="leavedateto" value="<?php echo isset($_POST["leavedateto"]) ? $_POST["leavedateto"] : ''; ?>"class="form-control" size="33" />
                <div></td>
            </tr>
            <tr><td><label for="NET Details: " style="font-size:15px;"><b>NET Details</b></label></td></tr>
            <tr>
                <td><label for="netqual" style="font-size:15px;"><font color='blue'>Whether NET qualified</font>  </label>
                <div><input type="radio" name="netqual" id="netqual" value="Yes">Yes &nbsp;&nbsp;&nbsp;
                <input type="radio" name="netqual" id="netqual2" value="No">NO
                </div></td>
                <td>
                <div id="netqyes">
		    <label for="netqualyes" style="font-size:15px;"><font color='blue'>If Yes </font></label><br>
                    <select name="netqualyes" id="netqualyes" style="width:300px;"> 
                        <option value="">--------- Select Organiser--------</option>
                        <option value="ICAR">ICAR</option>
                        <option value="CSIR">CSIR</option>
                        <option value="UGC">UGC</option>
                        <option value="TNSET">INSET</option>
                    </select>   
                </div>
		<div id="netqno">
  		    <label for="netqualno" style="font-size:15px;"><font color='blue'>If No </font></label><br>
                    <select name="netqualno" id="netqualno" style="width:300px;"> 
                        <option value="">--------- Select Reason--------</option>
                        <option value="Exempted_by_Subject">Exempted by Subject</option>
                        <option value="Exempted_by_Year">Exempted by Year</option>
                        <option value="Entry_Level_Ph.D.">Entry Level Ph.D.</option>
                    </select>   
                </div>

		
		</td>
                <td><label for="passdate" style="font-size:15px;"><font color='blue'>Year of Passing(MM-YYYY)</font></label>
                    <div><input type="text" name="passyear" id="passyear" value="<?php echo isset($_POST["passyear"]) ? $_POST["passyear"] : ''; ?>" placeholder="Year of Passing Date" size="33" />
                <div></td>
                <td><label for="netdiscipline" style="font-size:15px;"><font color='blue'>Discipline</font></label>
                <div><input type="text" name="netdiscipline" id="netdiscipline" class="keyup-characters" value="<?php echo isset($_POST["netdiscipline"]) ? $_POST["netdiscipline"] : ''; ?>" placeholder="NET Discipline........" size="33" >
                </div></td>
            </tr>
            <tr>
            <td><label for="vci " style="font-size:15px;"><b>Veterinary Council (VC) Registration: </b></label></td>   
            </tr>
            <tr>
                <td><label for="VCI" style="font-size:15px;"></label>
                <div><input type="radio" name="vcrapp" id="vcrapp"  checked value="Applicable">Applicable &nbsp;&nbsp;&nbsp;
                    <input type="radio" name="vcrapp" id="vcrnoapp"  value="Not Applicable">Not Applicable
                </div></td>
            </tr>
            <tr></tr>
            <tr>
                <td><label for="chapter" style="font-size:15px;"><font color='blue'>Chapter</font></label>
                <div>
                    <select name="chapter" id="chapter" style="width:300px;"> 
                        <option value="">--------- Select State--------</option>
                        <?php foreach($this->states as $statesdata): ?>
                        <option value="<?php echo $statesdata->id; ?>"><?php echo $statesdata->name; ?></option>
                        <?php endforeach;?>
                    </select>   
                </div></td>
                <td><label for="vciregno" style="font-size:15px;"><font color='blue'>Registration No</font></label>
                <div><input type="text" name="vciregno" id="vciregno" class="keyup-characters" value="<?php echo isset($_POST["vciregno"]) ? $_POST["vciregno"] : ''; ?>" placeholder="Registration No........" size="33" >
                </div></td>
                <td><label for="vciregdate" style="font-size:15px;"><font color='blue'>Date of Registration</font></label>
                <div><input type="text" name="vciregdate" id="vciregdate" value="<?php echo isset($_POST["vciregdate"]) ? $_POST["vciregdate"] : ''; ?>" placeholder="Registration Date........" size="33" >
                </div></td>
                <td><label for="vcrvaliddate" style="font-size:15px;"><font color='blue'>Valid Upto</font></label>
                <div><input type="text" name="vcrvaliddate" id="vcrvaliddate" value="<?php echo isset($_POST["vcrvaliddate"]) ? $_POST["vcrvaliddate"] : ''; ?>" placeholder=" Validity Date........" size="33" >
                
            </tr>
            <tr>
            <td><label for="allindiavci " style="font-size:15px;"><b>All India Veterinary Council (VC) Registration: </b></label></td>   
            </tr>
            <tr>
                <td><label for="allvciregno" style="font-size:15px;"><font color='blue'>Registration No</font></label>
                <div><input type="text" name="allvciregno" id="allvciregno" class="keyup-characters" value="<?php echo isset($_POST["allvciregno"]) ? $_POST["allvciregno"] : ''; ?>" placeholder="VCI Registration No........" size="33" >
                </div></td>
                <td><label for="allvciregdate" style="font-size:15px;"><font color='blue'>Date of Registration</font></label>
                <div><input type="text" name="allvciregdate" id="allvciregdate" value="<?php echo isset($_POST["allvciregdate"]) ? $_POST["allvciregdate"] : ''; ?>" placeholder="VCI Registration Date........" size="33" >
                </div></td>
                <td colspan="2"><label for="allvcrvaliddate" style="font-size:15px;"><font color='blue'>Valid Upto</font></label>
                <div><input type="text" name="allvcrvaliddate" id="allvcrvaliddate" value="<?php echo isset($_POST["allvcrvaliddate"]) ? $_POST["allvcrvaliddate"] : ''; ?>" placeholder="VCI Validity Date........" size="33" >
                </div></td>
            </tr>
            <tr>
                <td><label for="addasign" style="font-size:15px;"><b>Additional Assignments:</b></label></td>
            </tr>
            <tr>
              <td><label for="asignname" style="font-size:15px;"><font color='blue'>Name of the Assignment</font> </label>
                <div>
                    <select name="asignname" id="asignname" style="width:300px;"> 
                        <option value="">--------- Select Assignment Name --------</option>
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
                <td><label for="asignother" style="font-size:15px;"><font color='blue'>Others</font></label>
                <div><input type="text" name="asignother" id="asignother" class="keyup-characters" value="<?php echo isset($_POST["asignother"]) ? $_POST["asignother"] : ''; ?>" placeholder="Others........" size="33" >
            <!--</tr>
            <tr>-->
                
                <td><label for="asigndatefrom" style="font-size:15px;"><font color='blue'>Date From</font></label>
                <div><input type="text" name="asigndatefrom" id="asigndatefrom" value="<?php echo isset($_POST["vasigndatefrom"]) ? $_POST["asigndatefrom"] : ''; ?>" placeholder="Date From........" size="33" >
                </div></td>
                <td><label for="asigndateto" style="font-size:15px;"><font color='blue'>Date To</font></label>
                <div><input type="text" name="asigndateto" id="asigndateto" value="<?php echo isset($_POST["asigndateto"]) ? $_POST["asigndateto"] : ''; ?>" placeholder="Date To ........" size="33" >
                </div></td>
            </tr>
            <tr>
                <td><label for="asignplace" style="font-size:15px;"><font color='blue'>Assignment Place</font></label>
                <div><input type="text" name="asignplace" class="keyup-characters" value="<?php echo isset($_POST["asignplace"]) ? $_POST["asignplace"] : ''; ?>" placeholder="Place........" size="33" >
                </div></td>
		<tr>
            <td><label for="pref" style="font-size:15px;"><b>Preferred Place of Working: </b></label></td>
            </tr>
		<tr>
		<td><label for="pref1" style="font-size:15px;"><font color='blue'> First Preference </font> <font color='Red'></font></label>
                    <div> <select id="ppwpref1" style="width:300px;" name="ppwpref1" >
                        <option selected="selected" disabled selected>-Preferred Place of Working--</option>
                       <?php foreach($this->campus as $camdata): ?>
                                <option class="test" value="<?php echo $camdata->sc_id; ?>"><?php echo $camdata->sc_name; ?></option>
                        <?php endforeach; ?>

                    </select></div>
                </td>
		<td><label for="pref2" style="font-size:15px;"><font color='blue'> Second Preference</font> <font color='Red'></font></label>
                    <div> <select id="ppwpref2" style="width:300px;" name="ppwpref2" >
                        <option selected="selected" disabled selected>-Preferred Place of Working--</option>
                       <?php foreach($this->campus as $camdata): ?>
                                <option class="test" value="<?php echo $camdata->sc_id; ?>"><?php echo $camdata->sc_name; ?></option>
                        <?php endforeach; ?>

                    </select></div>
                </td>
		<td><label for="pref3" style="font-size:15px;"><font color='blue'>Third Preference </font> <font color='Red'></font></label>
                    <div> <select id="ppwpref3" style="width:300px;" name="ppwpref3" >
                        <option selected="selected" disabled selected>-Preferred Place of Working--</option>
                       <?php foreach($this->campus as $camdata): ?>
                                <option class="test" value="<?php echo $camdata->sc_id; ?>"><?php echo $camdata->sc_name; ?></option>
                        <?php endforeach; ?>

                    </select></div>
                </td>

            </tr>
            <tr>
           <!-- </tr>
            <tr> -->
                <td><label for="remarks" style="font-size:15px;"><font color='blue'>Remarks</font></label>
                    <div><textarea name="remarks" value="<?php echo isset($_POST["remarks"]) ? $_POST["remarks"] : ''; ?>"   rows="2" cols="40"  placeholder="Remarks......"></textarea>
                </div></td>
                <td><label for="Address" style="font-size:15px;"><font color='blue'>Residential Address</font></label>
                    <div><textarea name="Address" value="<?php echo isset($_POST["Address"]) ? $_POST["Address"] : ''; ?>"   rows="4" cols="40"  placeholder="Address..."></textarea>
                </div></td>
                <td colspan=2><label for="userfile" style="font-size:15px;"><font color='blue'>Upload Photo</font></label>
                   <div>
                        <input type="file" name='userfile' accept="image/*" onchange="preview_image(event)">
                        <!--<input type='file' name='userfile' size='20' class='upload-image' />-->
                    <!--<img id="output_image" v:shapes="_x0000_i1025" width="78" height="94"/>-->
                    <img src="<?php echo base_url('uploads/SIS/empphoto/'."empdemopic.png");?>"  id="output_image" v:shapes="_x0000_i1025" width="78" height="94"/>
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
    </body>
	<p> &nbsp; </p>
     <div><?php $this->load->view('template/footer'); ?></div> 
</html>    
