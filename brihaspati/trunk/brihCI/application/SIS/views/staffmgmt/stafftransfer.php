    
<!--@filename stafftransferposting.php  @author Manorama Pal(palseema30@gmail.com) -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        
        <title>Welcome to TANUVAS</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/datepicker/jquery-ui.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-ui.js" ></script>
        <style>
            /*----- Tabs -----*/
            /* Style the tab */
            .tab {
                overflow: hidden;
                border: 1px solid #ccc;
                //  background-color: #f1f1f1;
                 background-color:#2a8fcf;
            }
       

            /*----- Tab Links -----*/
            /* Clearfix */
            tab-links:after {
                display:block;
                clear:both;
                content:'';
            }

            .tab-links li {
                margin:0px 5px;
                margin-left:40px; 
                float:left;
                list-style:none;
    
            }

            .tab-links a {
                background-color: inherit;
                float: left;
                border: none;
                outline: none;
                cursor: pointer;
                padding: 6px 0px;
                transition: 0.3s;
                font-size: 16px;
            }

            .tab-links a:hover {
                //background:#a7cce5;
                text-decoration:none;
            }

            li.active a, li.active a:hover {
                background:whitesmoke;
                color:#4c4c4c;
            }

            .tab {
                //display:none;
            }

            .tab.active {
                display:block;
            }
            .tabcolor{
                text-decoration:none;
                color:black;
                font-weight:bold; 
    
            }
            .bgli{
                background-color:whitesmoke;
            }
            .bgli2:hover{
                background-color:whitesmoke;
            }
        </style> 
        <script>
                        
            $(document).ready(function(){
                $("#Dateofrelief,#expDateofjoin").datepicker({
                    dateFormat: 'yy/mm/dd',
                    numberOfMonths: 1,
                    autoclose: true,
                    changeMonth: true,
                    changeYear: true,
                    yearRange: 'c-70:c+30'
                       
                });
                
                /************************select department on basis of uoc *******************/
                       
                $('#uocid').on('change',function(){
                    var sc_code = $('#campfrom').val();
                    var uoc_id = $('#uocid').val();
                    var combid = sc_code+","+uoc_id;
                    //alert("combid=="+combid);
                    if(uoc_id == ''){
                        $('#scid').prop('disabled',true);
                    }
                    else{
             
                        $('#scid').prop('disabled',false);
                        $.ajax({
                            url: "<?php echo base_url();?>sisindex.php/staffmgmt/getuocdeptlist",
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
                    var dept_id = $('#scid').val();
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
                
                /************************select designation on basis of employee type *******************/
                       
                $('#emptype').on('change',function(){
                    var emp_type = $('#emptype').val();
                    if(emp_type == ''){
                        $('#desigid').prop('disabled',true);
                    }
                    else{
             
                        $('#desigid').prop('disabled',false);
                        $.ajax({
                            url: "<?php echo base_url();?>sisindex.php/staffmgmt/gettypedesiglist",
                            type: "POST",
                            data: {"emptypeid" : emp_type},
                            dataType:"html",
                            success:function(data){
                            
                                $('#desigid').html(data.replace(/^"|"$/g, ''));
                       
                            },
                            error:function(data){
                            //alert("data in error==="+data);
                                alert("error occur..!!");
                            }
                                            
                        });
                    }
                }); 
            
                /*********************closer of designation***************************************/
                
                /************************select department on basis of uoc *******************/
                       
                $('#uocidto').on('change',function(){
                    var sc_code = $('#camp').val();
                    var uoc_id = $('#uocidto').val();
                    var combid = sc_code+","+uoc_id;
                    if(uoc_id == ''){
                        $('#scidto').prop('disabled',true);
                    }
                    else{
             
                        $('#scidto').prop('disabled',false);
                        $.ajax({
                            url: "<?php echo base_url();?>sisindex.php/staffmgmt/getuocdeptlist",
                            type: "POST",
                            data: {"campuoc" : combid},
                            dataType:"html",
                            success:function(data){
                            
                                $('#scidto').html(data.replace(/^"|"$/g, ''));
                       
                            },
                            error:function(data){
                           // alert("data in error==="+data);
                                alert("error occur..!!");
                            }
                                            
                        });
                    }
                }); 
            
                /*********************closer of department***************************************/
                
                /************************select schemes on the basis of department*******************/
                       
                $('#scidto').on('change',function(){
                    var dept_id = $('#scidto').val();
                    if(dept_id == ''){
                        $('#schmidto').prop('disabled',true);
                    }
                    else{
             
                        $('#schmidto').prop('disabled',false);
                        $.ajax({
                            url: "<?php echo base_url();?>sisindex.php/staffmgmt/getnewdeptschemelist",
                            type: "POST",
                            data: {"combdept" : dept_id},
                            dataType:"html",
                            success:function(data){
                                $('#schmidto').html(data.replace(/^"|"$/g, ''));
                       
                            },
                            error:function(data){
                            //alert("data in error==="+data);
                                alert("error occur..!!");
                 
                            }
                                            
                        });
                    }
                }); 
            
                /*********************closer of scheme**************************************************/
                
                /************************select designation on basis of employee type *******************/
                       
               /* $('#emptypeto').on('change',function(){
                    var emp_type = $('#emptypeto').val();
                    if(emp_type == ''){
                        $('#desigidto').prop('disabled',true);
                    }
                    else{
             
                        $('#desigidto').prop('disabled',false);
                        $.ajax({
                            url: "<// ?php echo base_url();?>sisindex.php/staffmgmt/gettypedesiglist",
                            type: "POST",
                            data: {"emptypeid" : emp_type},
                            dataType:"html",
                            success:function(data){
                            
                                $('#desigidto').html(data.replace(/^"|"$/g, ''));
                                                                                
                            },
                            error:function(data){
                                alert("error occur..!!");
                            }
                                            
                        });
                    }
                }); */
                $('#grpid').on('change',function(){
                    var grp_id = $(this).val();
		    var wrktype_id = $('#emptypeto').val();
		    var combid = grp_id+","+wrktype_id;
	//		alert(combid);
                    if(grp_id == ''){
                        $('#desigidto').prop('disabled',true);
                    }
                    else{
             
                        $('#desigidto').prop('disabled',false);
                        $.ajax({
                            url: "<?php echo base_url();?>sisindex.php/jslist/getgwdesiglist",
                            type: "POST",
                            data: {"gwt" : combid},
                            dataType:"html",
                            success:function(data){
                                $('#desigidto').html(data.replace(/^"|"$/g, ''));
                       
                            },
                            error:function(data){
                                //alert("data in error part==="+data);
                                alert("error occur..!!");
                 
                            }
                                            
                        });
                    }
                }); 

                /*********************closer of designation***************************************/
                
                /************************select employee on basis of uco,dept,scheme,employee type,designation *******************/
                       
                $('#desigid').on('change',function(){
                    var uoc_id = $('#uocid').val();
                    var dept_id = $('#scid').val();
                    //var schmid = $('#schmid').val();
                    var emp_type = $('#emptype').val();
                    var desig_id = $('#desigid').val();
                    var combfour = uoc_id+","+dept_id+","+emp_type+","+desig_id;
                    //alert("combfour==="+combfour);
                    if(emp_type == ''){
                        $('#empnameid').prop('disabled',true);
                    }
                    else{
             
                        $('#empnameid').prop('disabled',false);
                        $.ajax({
                            url: "<?php echo base_url();?>sisindex.php/staffmgmt/getempnamelist",
                            type: "POST",
                            data: {"uddempt" : combfour},
                            dataType:"html",
                            success:function(data){
                               // alert(data);
                                $('#empnameid').html(data.replace(/^"|"$/g, ''));
                       
                            },
                            error:function(data){
                            //alert("data in error==="+data);
                                alert("error occur..!!");
                            }
                                            
                        });
                    }
                }); 
            
                /*********************closer of employee name**********************************************************************/
                               
                /**********************************Start of empdetail on selection of employee ename script*********************************/
                
                $("#empnameid").on('change',function(){
                    var empid = $(this).val();
                    var transtype= "<?php echo $ttype;?>";
                    //alert(empid);
                    if(empid == ''){
                       // $('#uocfrom').prop('disabled',true);
                        //$('#deptfrom').prop('disabled',true);
                        //$('#desigfrom').prop('disabled',true);
                        $('#postfrom,emppt').prop('disabled',true);
                        //$('#dep').prop('disabled',true);
                    }
                    else{
                         $('#postfrom,emppt').prop('disabled',false);
                        //$('#dep').prop('disabled',false);
                       //  alert(empid);
                        $.ajax({
                            url: "<?php echo base_url();?>sisindex.php/staffmgmt/getempdetail",
                            type: "POST",
                            data: {"employee" : empid},
                            dataType:"html",
                            success:function(data){
                               	var empdata=data;
                               // alert("empdata=="+empdata);
				var empinput=empdata.split(',');
                                if(transtype === 'budgetpost'){
                                    $('#postfrom').val(empinput[0].replace(/\"/g,""));
                                    $('#emppt').val(empinput[1].replace(/\"/g,""));
                                    $('#postto').val(empinput[0].replace(/\"/g,""));
                                    $('#vtypeid').val(empinput[1].replace(/\"/g,""));
                                }
                                else{
                                    $('#postfrom').val(empinput[0].replace(/\"/g,""));
                                    $('#emppt').val(empinput[1].replace(/\"/g,""));
                                }
                               
                                
                            },
                            error:function(data){
                                alert("error occur..!!");
                 
                            }
                        });
                                               
                    }//else
               
                });  //method empname
                /**********************************End of empdetail on selection of employee ename script*********************************/
            
                /************************select shown against the post value *****************************************************/
                $('#desigidto').on('change',function(){
                    var sc_code = $('#camp').val();
                    var uoc_id = $('#uocidto').val();
                    var dept_id = $('#scidto').val();
                    var schm_id = $('#schmidto').val();
                    var desig_id = $('#desigidto').val();
                    var wrktype_id = $('#emptypeto').val();
                    var transtype= "<?php echo $ttype;?>";
                    var cudshmwrktype = sc_code+","+uoc_id+","+dept_id+","+schm_id+","+wrktype_id;
                   //    alert("vales===788==="+cudshmwrktype+"bbb==="+transtype);
                    if(desig_id == ''){
                        $('#postto').prop('disabled',true);
                    }
                    else{
                        $('#postto').prop('disabled',false);
                             var urlnew;
                        if((transtype === 'singletransfer')||(transtype === 'singlepromtion')||(transtype === 'multipletransfer')){
                           urlnew="<?php echo base_url();?>sisindex.php/staffmgmt/getemppostpositionnew";
                        }
                        else{
                            urlnew="<?php  echo base_url();?>sisindex.php/staffmgmt/getposttonew";
                        }    
                        $.ajax({
                            
                            url: urlnew,
                            type: "POST",
                            data: {"combfive" : cudshmwrktype},
                            dataType:"html",
                            success:function(data){
                                var empdata=data;
                                var val1 = empdata.replace(/\"/g,"");
                                $('#postto').html(data.replace(/^"|"$/g, ''));
                                if(val1.trim() === "No vacancy"){
                                    alert('Sorry, No vacancy available for this post');
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
            
            /************************select DDO on basis of campus department schemes*******************/
            $('#schmidto').on('change',function(){
                var sc_code = $('#camp').val();
                var dept_id = $('#scidto').val();
                var schm_id = $('#schmidto').val();
	//	alert("nks"+sc_code+"'"+dept_id+"'"+schm_id);
                var campdeptschm = sc_code+","+dept_id+","+schm_id;
	//	alert("nks"+campdeptschm);
               // alert("seema==="+sc_code+'uoc==='+uoc_id+"dept=="+dept_id+"schmid==="+schm_id+"comb=="+campuocdeptschm);
                if(schm_id == ''){
                    $('#ddoid').prop('disabled',true);
                }
                else{
             
                    $('#ddoid').prop('disabled',false);
                    $.ajax({
                        url: "<?php echo base_url();?>sisindex.php/staffmgmt/getddolist",
                        type: "POST",
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
            
                
            /************************Employee type******************************************************************/
            
            $('#postto').on('change',function(){
                var sc_code = $('#camp').val();
                var uoc_id = $('#uocidto').val();
                var dept_id = $('#scidto').val();
              //  var desig_id = $('#desigidto').val();
                var empost_id = $('#postto').val();
                var wrktype_id = $('#emptypeto').val();
                var cudshmpostwrktype = sc_code+","+uoc_id+","+dept_id+","+empost_id+","+wrktype_id;
               // alert("comin script===bsix===="+cudshmpostwrktype);
                if(empost_id == ''){
                   $('#vtypeid').prop('disabled',true);
                }
                else{
             
                    $('#vtypeid').prop('disabled',false);
                    $.ajax({
                        url: "<?php echo base_url();?>sisindex.php/staffmgmt/getemptypeposition",
                        type: "POST",
                        data: {"combfive" : cudshmpostwrktype},
                        dataType:"html",
                        success:function(data){
                            //alert("seema455==="+data);
                            
                            $('#vtypeid').html(data.replace(/^"|"$/g, ''));
                            
                        },
                        error:function(data){
                            //alert("data in error part==="+data);
                            alert("error occur..!!");
                 
                        }
                                            
                    });
                }
            }); 
            /************************ closer Employee type******************************************************************/
                
                /**Allows only letters, numbers and spaces. All other characters will return an error.******************************/
                $('.keyup-characters').keyup(function() {
                $('span.error-keyup-2').remove();
                var inputVal = $(this).val();
                var characterReg = /^\s*[a-zA-Z0-9,\s]+\s*$/;
                if(!characterReg.test(inputVal)) {
                    $(this).after('<span class="error error-keyup-2"><font color="red">No special characters allowed.</font></span>');
                }
                });
                /******************************************close Special characters***************************************/
            
            
            /************************select employee on basis of uco,dept,scheme,employee type,designation *******************/
                       
                $('#desigidto').on('change',function(){
                //alert("seema hello");
                    var uoc_id = $('#uocidto').val();
                    var dept_id = $('#scidto').val();
                    var emp_type = $('#emptypeto').val();
                    var desig_id = $('#desigidto').val();
                    var combfour = uoc_id+","+dept_id+","+emp_type+","+desig_id;
                   // alert("combfour==in to="+combfour);
                    if(emp_type == ''){
                        $('#empnto').prop('disabled',true);
                    }
                    else{
             
                        $('#empnto').prop('disabled',false);
                        $.ajax({
                            url: "<?php echo base_url();?>sisindex.php/staffmgmt/getempnamelist",
                            type: "POST",
                            data: {"uddempt" : combfour},
                            dataType:"html",
                            success:function(data){
                               // alert(data);
                                $('#empnto').html(data.replace(/^"|"$/g, ''));
                       
                            },
                            error:function(data){
                            //alert("data in error==="+data);
                                alert("error occur..!!");
                            }
                                            
                        });
                    }
                }); 
            
                /*********************closer of employee name**********************************************************************/
            
             /**********************************Start of empdetail by  PF NOscript*********************************/
                
                $("#emppfno").on('change',function(){
                    var pfno = $(this).val();
                    var transtype= "<?php echo $ttype;?>";
                    if(pfno === ''){
               //       $('#my_id').[0]reset(); 
                       // $('#campfrom,#uocid,#scid,#schmid,#emptype,#desigid,#empnameid,#postfrom,#emppt').prop('disabled',true);
                        
                    }
                    else{
                       // $('#campfrom,#uocid,#scid,#schmid,#emptype,#desigid,#empnameid,#postfrom,#emppt').prop('disabled',false);
                       
                        $.ajax({
                            url: "<?php echo base_url();?>sisindex.php/jslist/getempdata",
                            type: "POST",
                            data: {"emplypfno" : pfno},
                            dataType:"html",
                            success:function(data){
                                var empdata = data;
                               // alert("seee=in objecvtdata1="+empdata);
                                var empinput=empdata.split('^');
                                $('#campfrom').html(empinput[0].replace(/"|"/g,""));
                                $('#uocid').html(empinput[1]);
                                $('#scid').html(empinput[2]);
                                $('#schmid').html(empinput[3].replace(/"|"/g,""));
                                $('#emptype').val(empinput[4]);
                                $('#desigid').html(empinput[5].replace(/"|"/g,""));
                                $('#empnameid').html(empinput[6].replace(/"|"/g,""));
                                if(transtype === 'budgetpost'){
                                    $('#postfrom').val(empinput[7].replace(/\"/g,""));
                                    $('#emppt').val(empinput[8].replace(/\"/g,""));
                                    $('#postto').val(empinput[7].replace(/\"/g,""));
                                    $('#vtypeid').val(empinput[8].replace(/\"/g,""));
                                }
                                else{
                                    $('#postfrom').val(empinput[7].replace(/\"/g,""));
                                    $('#emppt').val(empinput[8].replace(/\"/g,""));
                                }
                               
                            },
                            error:function(data){
                                alert("error occur..!!");
                 
                            }
                        });
                                               
                    }//else
               
                });  //method empname
                /**********************************End of empdetail PF NO  script*********************************/
            
            });
    </script>
    </head>
    <body>
        <div>
           
            <?php $this->load->view('template/header'); ?>
            
        </div>
         <table ><tr><td>
        <?php echo anchor('staffmgmt/stafftransferlist/', "View Staff Transfer List" ,array('title' => 'Staff Transfer List ' , 'class' => 'top_parent'));?>
        </td>
        </tr>
        </table>
        <div align="left" style="margin-left:0px;">
            
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
                                       
        <table style="margin-left:0%;border:1px solid gray;width:100%;">
                <div class="tab">
                <ul class="tab-links">
                
                <?php if($this->uri->segment(3)== 'singletransfer'):?>    
                <li class="bgli"><?php echo anchor('staffmgmt/stafftransfer/singletransfer', "Single Transfer",array('title' => 'Single Transfer','id'=>'tab1','class' => 'top_parent','class' => 'tabcolor')); ?></li>
                <?php else :?>
                <li class="bgli2"><?php echo anchor('staffmgmt/stafftransfer/singletransfer', "Single Transfer",array('title' => 'Single Transfer','id'=>'tab1','class' => 'top_parent')); ?></li>
                <?php endif ?>
                <?php if($this->uri->segment(3)== 'singlepromtion'):?> 
                <li class="bgli"><?php echo anchor('staffmgmt/stafftransfer/singlepromtion',"Single Promotion",array('title' => 'Single Promotion','id'=>'tab2','class' => 'top_parent','class' => 'tabcolor')) ;?></li>
                <?php else :?>
                <li class="bgli2"><?php echo anchor('staffmgmt/stafftransfer/singlepromtion',"Single Promotion",array('title' => 'Single Promotion','id'=>'tab2','class' => 'top_parent')) ;?></li>
		<?php endif ?>
                <?php if($this->uri->segment(3)== 'mutual'):?> 
                <li class="bgli"><?php echo anchor('staffmgmt/stafftransfer/mutual',"Mutual Transfer Order",array('title' => 'Mutual Transfer Order','id'=>'tab3','class' => 'top_parent','class' => 'tabcolor')) ;?></li>
                <?php else :?>
                <li class="bgli2"><?php echo anchor('staffmgmt/stafftransfer/mutual',"Mutual Transfer Order",array('title' => 'Mutual Transfer Order','id'=>'tab3','class' => 'top_parent')) ;?></li>
                <?php endif ?>
                <?php if($this->uri->segment(3)== 'multipletransfer'):?>
		<li class="bgli"><?php echo anchor('staffmgmt/stafftransfer/multipletransfer',"Multiuple Transfer in Single Order",array('title' => 'Multiuple Transfer in Single Order','id'=>'tab4','class' => 'top_parent','class' => 'tabcolor')) ;?></li>
                <?php else :?>
                <li class="bgli2"><?php echo anchor('staffmgmt/stafftransfer/multipletransfer',"Multiuple Transfer in Single Order",array('title' => 'Multiuple Transfer in Single Order','id'=>'tab4','class' => 'top_parent')) ;?></li>
                <?php endif ?>
                <?php if($this->uri->segment(3)== 'workorder'):?>
                <li class="bgli"><?php echo anchor('staffmgmt/stafftransfer/workorder',"Work Order",array('title' => 'Work Order','id'=>'tab5','class' => 'top_parent','class' => 'tabcolor')) ;?></li>
                <?php else :?>
                <li class="bgli2"><?php echo anchor('staffmgmt/stafftransfer/workorder',"Work Order",array('title' => 'Work Order','id'=>'tab5','class' => 'top_parent')) ;?></li>
                <?php endif ?>
                <?php if($this->uri->segment(3)== 'budgetpost'):?>
                <li class="bgli"><?php echo anchor('staffmgmt/stafftransfer/budgetpost',"Transfer with Post and Budget",array('title' => 'Transfer with Post and Budget','id'=>'tab6','class' => 'top_parent','class' => 'tabcolor')) ;?></li>
                <?php else :?>
                <li class="bgli2"><?php echo anchor('staffmgmt/stafftransfer/budgetpost',"Transfer with Post and Budget",array('title' => 'Transfer with Post and Budget','id'=>'tab6','class' => 'top_parent')) ;?></li>
                <?php endif ?>
                </ul>
                </div>    
                <?php if($ttype !='multipletransfer' ) : ?>
               
                    <?php echo form_open_multipart('staffmgmt/stafftransfer/'.$ttype,'id="my_id"');?>   
                    <tr>
                        <td><label for="registrarname" style="font-size:15px;">Registrar Name<font color='Red'>*</font></label>
                           <div><input type="text" name="registrarname" class="keyup-characters" value="<?php echo isset($_POST["registrarname"]) ? $_POST["registrarname"] : ''; ?>" size="40"  required pattern="[a-zA-Z0-9 ]+" required="required">
                           </div>    
                        </td>
                        <td><label for="designation" style="font-size:15px;">Designation<font color='Red'>*</font></label>
                            <div><select name="designation" required="required"> 
                                <option value="">---------------- Select Designation -------------</option>
                                <option value="Registrar">Registrar</option>
                                <option value="Registrar Incharge">Registrar Incharge</option>                     
                            </select></div>
                        </td>
                        <td><label for="usono" style="font-size:15px;">University Sanction Order No<font color='Red'>*</font></label>
                            <div><input type="text" name="usono"  value="<?php echo isset($_POST["usono"]) ? $_POST["usono"] : ''; ?>" size="40"  required="required">
                            </div>    
                        </td>
                    </tr>
                    <tr>
                        <td ><label for="rcno" style="font-size:15px;">RC No<font color='Red'>*</font></label>
                            <div><input type="text" name="rcno" value="<?php echo isset($_POST["rcno"]) ? $_POST["rcno"] : ''; ?>" size="40"   required="required"></div>
                        </td> 
                        <td><label for="referenceno" style="font-size:15px;">Reference No</font></label>
                            <div><input type="text" name="referenceno" value="<?php echo isset($_POST["referenceno"]) ? $_POST["referenceno"] : ''; ?>" size="40" ></div>
                        </td> 
                        <td></td>
                    </tr>
                    <tr></tr>
                    <tr><td colspan="5"><hr></td></tr>
                    <tr><td><font color='blue'>
                        <?php if($ttype=='workorder'){
                              echo "Employee Working Arrangement from";  
                            }
                            if($ttype=='singlepromtion'){
                                echo  "Employee Promotion from";
                            }
                            if($ttype=='mutual'){
                               echo "Employee mutual Transfer from"; 
                            }
                            if($ttype=='singletransfer'){
                          
                                echo "Employee Single Transfer from";
                            }
                            if($ttype=='budgetpost'){ 
                                echo "Employee Transfer with Post and Budget from";
                            }                      
                            
                        ?>
                        </font></td>
                    </tr>
                    <tr><td colspan="5"><label for="" style="font-size:15px;">Employee PF No. <font color='Red'>*</font></label>
                        <div><input type="text" name="emppfno" id="emppfno" value="<?php echo isset($_POST["emppfno"]) ? $_POST["emppfno"] : ''; ?>" size="35"></div>       
                    </td></tr>
                    
                    <tr>
                        <!----------------------------------------------------employee pf no empty--------------------------------------------->
                        <td><label for="campus" style="font-size:15px;">Campus Name <font color='Red'>*</font></label>
                        <div>
                            <select  name="campusfrom" id="campfrom" required="required" style="width:350px;" > 
                                
                            <option value="">--------Campus Name-----</option>
                            <?php foreach($this->campus as $camdata): ?>	
   				<option  value="<?php echo $camdata->sc_id; ?>"><?php echo $camdata->sc_name; ?></option> 
                            <?php endforeach; ?> 
                            </select> 
                           
                        </div>
                        </td>
                        
                        <td><label for="uocfrom" style="font-size:15px;">University Officer Control From<font color='Red'>*</font></label>
                            
                            <div>
                                <select name="uocfrom" id="uocid"  style="width:350px;" required> 
                                <option value="">------- Select University Officer Control --------</option>
                                
                                <?php foreach($this->uoc as $ucodata): ?>	
                                    <option value="<?php echo $ucodata->id; ?>"><?php echo $ucodata->name; ?></option> 
                                <?php endforeach; ?>
                                </select>
                               
                            </div>
                        </td>
                        
                        <td><label for="department" style="font-size:15px;">Department From<font color='Red'>*</font></label>
                            <div>
                                <select required name="deptfrom"  id="scid" style="width:350px;"> 
                                <option value="">----------------- Select Department --------------</option>
                                </select>
                               
                            </div>
                        </td>
                    </tr> 
                    <tr>
                        <td><label for="schemecode" style="font-size:15px;">Scheme Name From<font color='Red'>*</font></label>
                            <div>
                                <select required name="schemfrom" id="schmid" style="width:350px;"> 
                                    <option value="">-------------- Select Scheme ------------------</option>
                                </select>
                                
                            </div>
                        </td>
                        
                        <td><label for="employeetype" style="font-size:15px;">Working Type From<font color='Red'>*</font></label>
                            <div>
                                <select name="employeetype" id="emptype"  style="width:350px;" required="required"> 
                                <option value="">------------ Select Employee Type ---------------</option>
                                <option value="Teaching">Teaching</option>
                                <option value="Non Teaching">Non Teaching</option>
                                </select>
                                
                            </div>
                        </td>
                                         
                        <td><label for="designation" style="font-size:15px;">Designation From<font color='Red'>*</font></label>
                            <div>
                                <select name="desigfrom" id="desigid" style="width:350px;" required> 
                                <option value="">-------------- Select Designation -----------------</option>
                                </select>
                               
                            </div>
                        </td>
                    </tr>
                    <tr>
                        
                        <td><label for="empname" style="font-size:15px;">Employee Name<font color='Red'>*</font></label>
                            <div>
                                <select name="empname" id="empnameid" style="width:350px;"> 
                                <option value="">--------- Select Employee Name --------------</option>
                                </select>
                              
                            </div>
                        </td>
                        <!---------------------------------------------------- closer employee pf no empty--------------------------------------------->
                    
                    
                         <td><label for="postfrom" style="font-size:15px;">Post From<font color='Red'>*</font></label>
                            <div><input type="text" name="postfrom" id="postfrom"  readonly class="keyup-characters" size="40"  required pattern="[a-zA-Z0-9 ]+" required="required"></div>
                        </td>
                         <td><label for="emppt" style="font-size:15px;">Employee Type<font color='Red'>*</font></label>
                             <div><input type="text" name="empptfrom" id="emppt"  readonly class="keyup-characters" size="40"  required pattern="[a-zA-Z0-9 ]+" required="required"></div>
                        </td>
                    </tr>
                    <tr></tr>
                    <tr><td colspan="5"><hr></td></tr>
                    <tr><td><font color='blue'>
                        <?php if($ttype=='workorder'){ 
                            echo  "Employee Working Arrangement To";
                        }
                        if($ttype=='singlepromtion'){
                           echo  "Employee Promotion To";
                        }
                        if($ttype=='mutual'){
                               echo "Employee mutual Transfer To"; 
                        }
                        if($ttype=='singletransfer'){
                            echo "Employee Single Transfer To";
                        } 
                        if($ttype=='budgetpost'){ 
                            echo "Employee Transfer with Post and Budget To";
                        }                      
                            
                        ?>
                        </font></td>
                    </tr>
                    <tr>
                        <td><label for="campus" style="font-size:15px;">Campus Name <font color='Red'>*</font></label>
                        <div> <select id="camp" style="width:350px;" name="campus" required> 
                            <option selected="selected" disabled selected>--------Campus Name-----</option>
                            <?php foreach($this->campus as $camdata): ?>	
   				<option class="test" value="<?php echo $camdata->sc_id; ?>"><?php echo $camdata->sc_name; ?></option> 
                            <?php endforeach; ?>
                      
                            </select></div>
                        </td> 
                        <td><label for="uocontrol" style="font-size:15px;">University Officer Control To<font color='Red'>*</font></label>
                            <div><select name="uocontrolto" id="uocidto" style="width:350px;" required> 
                                <option value="">------- Select University Officer Control ---------</option>
                                
                                <?php foreach($this->uoc as $ucodata): ?>	
                                    <option value="<?php echo $ucodata->id; ?>"><?php echo $ucodata->name; ?></option> 
                                <?php endforeach; ?>
                            </select></div>
                        </td>
                        <td><label for="dept" style="font-size:15px;">Department To<font color='Red'>*</font></label>
                            <div><select required name="deptto" id="scidto" style="width:350px;"> 
                               <option value="">----------------- Select Department ------------</option>
                            </select></div>
                        </td>
                        
                    </tr>
                    <tr>
                        <td><label for="schemecode" style="font-size:15px;">Scheme Name To<font color='Red'>*</font></label>
                            <div><select required name="schemto" id="schmidto" style="width:350px;"> 
                            <option selected="selected" disabled selected>----------------- Select Scheme ------------------</option>
                        
                            </select><div>
                        </td>
                        <td><label for="ddo" style="font-size:15px;">Drawing and Disbursing Officer To<font color='Red'>*</font></label>
                            <div><select name="ddo" id="ddoid" required style="width:350px;"> 
                            <option selected="selected" disabled selected>--------- Drawing and Disbursing Officer-----</option>
                            </select></div>
                        </td>
                        <td><label for="employeetype" style="font-size:15px;">Working Type To<font color='Red'>*</font></label>
                            <div><select name="emptypeto" id="emptypeto" style="width:350px;" required="required"> 
                                <option value="">------------ Select Employee Type ---------------</option>
                                <option value="Teaching">Teaching</option>
                                <option value="Non Teaching">Non Teaching</option>                     
                            </select></div>
                        </td>
                        
                    </tr>
                    <tr>
                        
                        <td><label for="group" style="font-size:15px;">Group To<font color='Red'>*</font></label>
                        <div><select name="group" id="grpid" required style="width:350px;"> 
                        <option selected="selected" disabled selected>------------ Select Group ---------</option>
                        <option value="A">A</option>
                        <option value="B">B</option>
                        <option value="C">C</option>
                        <option value="D">D</option>
                        </select></div>
                        </td>
                        <?php //echo $ttype ;?>
                        <td><label for="desigto" style="font-size:15px;">Designation To<font color='Red'>*</font></label>
                            <div><select required name="desigto" id="desigidto" style="width:350px;"> 
                               <option value="">--------------- Select Designation ---------------</option>
                            </select>
                            </div>
                        </td>
                        <td><label for="payband" style="font-size:15px;">Pay Band To<font color='Red'>*</font></label>
                        <div><select name="payband" required style="width:350px;"> 
                        <option selected="selected" disabled selected>------------------ Select Pay Band -------------</option>
                        <?php foreach($this->salgrd as $salgrddata): ?>	
                            <option value="<?php echo $salgrddata->sgm_id; ?>"><?php echo $salgrddata->sgm_name."(". $salgrddata->sgm_min."-".$salgrddata->sgm_max.")".$salgrddata->sgm_gradepay; ?>
                            </option> 
 			<?php endforeach; ?>
                       
                        </select></div>
                        </td>
                      
                    </tr>
                    <tr>
                        <?php if($ttype =='budgetpost') : ?> 
                        <td><label for="postto" style="font-size:15px;">Post To<font color='Red'>*</font></label>
                            <div><input type="text" name="postto" id="postto"  readonly class="keyup-characters" size="40"  required pattern="[a-zA-Z0-9 ]+" required="required"></div>
                        </td>
                         <td><label for="emppt" style="font-size:15px;">Employee Type<font color='Red'>*</font></label>
                             <div><input type="text" name="vacanttype" id="vtypeid"  readonly class="keyup-characters" size="40"  required pattern="[a-zA-Z0-9 ]+" required="required"></div>
                           
                        </td>
                        <?php else : ?>
                        
                        <td><label for="postto" style="font-size:15px;">Post To<font color='Red'>*</font></label>
                        <div>    
                            <select required name="postto" id="postto" style="width:350px;"> 
                               <option value="">--------------- Select Post ------------------------</option>
                            </select>   
                         
                        </div>       
                        </td>
                        <td><label for="emptype" style="font-size:15px;">Employee Type<font color='Red'>*</font></label>
                            <div><select id="vtypeid" name="vacanttype" required style="width:350px;"> 
                            <option selected="selected" disabled selected>-------- Select Employee Type ------</option>
                            
                            </select><div>
                        </td> 
                        <?php endif; ?>
                        
                        <?php if($ttype =='mutual'): ?>
                        <td><label for="empname" style="font-size:15px;">Employee Name for Mutual Tranfer<font color='Red'>*</font></label>
                            <div><select name="empmutual" id="empnto" style="width:350px;"> 
                                <option value="">--------- Select Employee Name --------------</option>
                              
                                                   
                            </select></div>
                        </td>
                        <?php endif;?>
                        
                                               
                    </tr>
                    <tr>
                        <td><label for="dateofrelief" style="font-size:15px;">Date of Relieve</label>
                            <div><input type="text" name="dateofrelief" id="Dateofrelief"  value="<?php echo isset($_POST["dateofrelief"]) ? $_POST["dateofrelief"] : ''; ?>" size="40" />
                            </div>
                        </td>
                        <td><label for="expdoj" style="font-size:15px;">Expected Date of joining</label>
                            <div><input type="text" name="expdoj" id="expDateofjoin"  value="<?php echo isset($_POST["expDateofjoin"]) ? $_POST["expDateofjoin"] : ''; ?>" size="40" /></div>  
                        </td> 
                        <td><label for="subject" style="font-size:15px;">Subject<font color='Red'>*</font></label>
                            <div><textarea name="subject" rows="5" cols="50" required pattern="[a-zA-Z0-9 ]+" required="required" placeholder="Enter text here...."></textarea><div>
                        </td>
                        
                                               
                    </tr>
                    <tr>
                        <td><label for="ordercontent" style="font-size:15px;">Order Content<font color='Red'>*</font></label>
                            <div><textarea name="ordercontent" rows="5" cols="50" required pattern="[a-zA-Z0-9 ]+" required="required" placeholder="Enter text here...."></textarea>
                            </div>
                        </td>
                        <td><label for="ttadetail" style="font-size:15px;">TTA Detail</label>
                            <div><textarea name="ttadetail" rows="5" cols="50"  placeholder="Enter text here...."></textarea></div>
                        </td>
                        <td><label for="emailsentto" style="font-size:15px;">Email Sent To</label>
                            <div><textarea name="emailsentto" rows="5" cols="50" required pattern="[a-zA-Z0-9 ]+" required="required" placeholder="Enter text here...."></textarea></div>
                        </td>
                    </tr> 
                    <tr style="background-color:#2a8fcf;text-align:left;height:40px;">
                        <td colspan="6">   
                            <button name="stafftransfer" >Submit</button>
                            <input type="reset" name="Reset" value="Clear"/>
                        </td>
           
            </tr>
            <input type="hidden" name="ttype" value="<?php echo $ttype;?>"/>                
            </form>  
            <?php else : ?>
                                           
               <table   style="font-size:13px;" > 
                        <tr><td align="left" ><b>Note : The file extension should be in csv. The format of Staff transfer order file is --</b>
                        </td></tr>
                        <tr><td align="left"><br/><b>Registrar Name &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;Designation &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; USO No &nbsp; RC No &nbsp; Reference No &nbsp;&nbsp;</b>
                          <br/> Dr. A B SINGH &nbsp; &nbsp;Registrar Incharge &nbsp;&nbsp;&nbsp;34567 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;BC8776 &nbsp;&nbsp; C898000 &nbsp;&nbsp;
                            <br/><br/>
                            <tr><td align="left"><b>Employee Transfer To Details:</b> </td></tr>    
                            <tr><td align="left"><b>Employee PF No. &nbsp;&nbsp;&nbsp;&nbsp &nbsp;&nbsp;Campus Code &nbsp;&nbsp; Authority(UCO)Code &nbsp;&nbsp; Department Code &nbsp;&nbsp;  Scheme Code &nbsp;&nbsp; DDO Code &nbsp;&nbsp; AGP Code &nbsp;&nbsp; Working Type &nbsp;&nbsp; Group &nbsp;&nbsp;Designation Code&nbsp;&nbsp;Shown against Post Code&nbsp;&nbsp; Employee Type </b> <br/>
                                V1234&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CU001&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;EO&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;MVC-AGB
                                &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;1007&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D149
                                &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;16 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Teaching
                                &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;A&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;6
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;8
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Permanent<br/>
                                <b>Date of relieve &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Date of Joining&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Subject
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Order Content&nbsp; &nbsp;&nbsp; &nbsp;TTA Detail &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Email Sent To</b>
                                <br/>2018-05-30 00:00:00 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2018-06-30 00:00:00 &nbsp;&nbsp;&nbsp;TRANSFER
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;order content&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;TTA Detail&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;palseema30@gmail.com
                            </td></tr>
                                                                                   
                            </td></tr>
           
                        </table>
                        <br/><table>
                        <?php echo form_open_multipart('multipletransfer/multitransfer');?>
                            <tr>
                                <td align="left">
                                    <span id="lblError" style="color: red;font-size:15px;"></span><br/>
                                    <div><label for="file" style="font-size:15px;"><b>Select File</b></label>
                                    <input type='file'  id="fileUpload" name='userfile' size='20' accept=".csv, text/csv" />
                                    <input type='submit' name='importdata' id="btnUpload"  value='upload'/></div>
                                </td> 
                                 
                            </tr>
                        </form>
                    </table><br/><br/>
                    <table class="TFtable">
                        <thead>
                        <tr>  
                            <th>Sr.No</th>
                            <th>Transfer Order(File Name)</th>
                            <th>Tranfer Order Copy</th>
                   
                        </tr>
                        </thead>
                        <tbody>
                            <?php $serial_no = 1;
                                $cyear=date("Y");
                                $files = array();
                                foreach (glob("./uploads/SIS/multitransferordercopy/$cyear/*.{pdf}",GLOB_BRACE) as $file) {
                                    $files[] = $file;
                                    //$filename = basename($file);
                                }
                            ?>
                            <?php if( count($files) ):  ?>
                                <?php foreach($files as $record){ ?>
                                <tr>
                                    <td><?php echo $serial_no++; ?></td>
                                    <td><?php echo basename($record);?></td>
                                    <td> <a id="downloadLink" href="<?php echo base_url('uploads/SIS/multitransferordercopy/'.$cyear)."/".basename($record);?>" target="_blank" 
                                            type="application/octet-stream" download="<?php base_url('uploads/SIS/transferorder/'.$cyear)."/".basename($record) ;?>">click here</a>.</p>  
                                        <?php // echo anchor("{$record}","View Transfer order copy",array('title' => 'View Transfer order' , 'class' => 'red-link')); ?></td>
                                </tr>
                                <?php }; ?>
                            <?php else : ?>
                                <td colspan= "12" align="center"> No Records found...!</td>
                            <?php endif;?>
                        </tbody>    
                        
                    </table>  
                        
             <?php endif;?>
                       
                                       
            </table>
                
        </body>    
  <p>&nbsp;</p>
  <div><?php $this->load->view('template/footer'); ?></div> 
</html>    
