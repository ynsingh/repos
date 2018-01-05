    
<!--@filename stafftransferposting.php  @author Manorama Pal(palseema30@gmail.com) -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Welcome to TANUVAS</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/datepicker/jquery-ui.css">
        <!--<script type="text/javascript" src="<//?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>-->
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-ui.js" ></script>
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
                    //var sc_code = $('#camp').val();
                    var uoc_id = $('#uocid').val();
                    //var combid = sc_code+","+uoc_id;
                    //alert("combid=="+combid);
                    if(uoc_id == ''){
                        $('#scid').prop('disabled',true);
                    }
                    else{
             
                        $('#scid').prop('disabled',false);
                        $.ajax({
                            url: "<?php echo base_url();?>sisindex.php/staffmgmt/getuocdeptlist",
                            type: "POST",
                            data: {"campuoc" : uoc_id},
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
                    //var sc_code = $('#camp').val();
                    var uoc_id = $('#uocidto').val();
                    //var combid = sc_code+","+uoc_id;
                    //alert("combid=="+combid);
                    if(uoc_id == ''){
                        $('#scidto').prop('disabled',true);
                    }
                    else{
             
                        $('#scidto').prop('disabled',false);
                        $.ajax({
                            url: "<?php echo base_url();?>sisindex.php/staffmgmt/getuocdeptlist",
                            type: "POST",
                            data: {"campuoc" : uoc_id},
                            dataType:"html",
                            success:function(data){
                            
                                $('#scidto').html(data.replace(/^"|"$/g, ''));
                       
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
                       
                $('#emptypeto').on('change',function(){
                    var emp_type = $('#emptypeto').val();
                    if(emp_type == ''){
                        $('#desigidto,#postto').prop('disabled',true);
                    }
                    else{
             
                        $('#desigidto,#postto').prop('disabled',false);
                        $.ajax({
                            url: "<?php echo base_url();?>sisindex.php/staffmgmt/gettypedesiglist",
                            type: "POST",
                            data: {"emptypeid" : emp_type},
                            dataType:"html",
                            success:function(data){
                            
                                $('#desigidto').html(data.replace(/^"|"$/g, ''));
                                $('#postto').html(data.replace(/^"|"$/g, ''));
                                                      
                            },
                            error:function(data){
                            //alert("data in error==="+data);
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
                    //alert(empid);
                    if(empid == ''){
                       // $('#uocfrom').prop('disabled',true);
                        //$('#deptfrom').prop('disabled',true);
                        //$('#desigfrom').prop('disabled',true);
                        $('#postfrom').prop('disabled',true);
                        //$('#dep').prop('disabled',true);
                    }
                    else{
                         $('#postfrom').prop('disabled',false);
                        //$('#dep').prop('disabled',false);
                       //  alert(empid);
                        $.ajax({
                            url: "<?php echo base_url();?>sisindex.php/staffmgmt/getempdetail",
                            type: "POST",
                            data: {"employee" : empid},
                            dataType:"html",
                            success:function(data){
                               	var empdata=data;
				//var empinput=empdata.split(',');
                                //var val1 = empinput[0].replace(/\"/g,"");
                               //$('#uocfrom').val(val1.replace(/^"|"$/g, ''));
                               //$('#deptfrom').val(empinput[1].replace(/^"|"$/g, ''));
                               //$('#desigfrom').val(empinput[2].replace(/^"|"$/g, ''));
                              // $('#postfrom').val(empinput[3].replace(/^"|"$/g, ''));
                               //$('#dep').html(empinput[4].replace(/^"|"$/g, ''));
                                $('#postfrom').val(empdata.replace(/\"/g,""));
                               
                                
                            },
                            error:function(data){
                                alert("error occur..!!");
                 
                            }
                        });
                                               
                    }//else
               
                });  //method empname
                /**********************************End of empdetail on selection of employee ename script*********************************/
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
        <div align="left" style="margin-left:2%;">
            
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
        <!--<div>-->
            <table style="margin-left:0%;border:1px solid gray;width:100%;">
                <tr><thead><th  style="background-color:#2a8fcf;text-align:left;height:40px;" colspan="3">&nbsp;&nbsp;Staff Transfer and Posting Form</th></thead></tr>    
                
                <?php echo form_open_multipart('staffmgmt/stafftransfer');?>
                <!--<form action="<//?php echo site_url('staffmgmt/stafftransfer');?>" method="POST" class="form-inline">-->
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
                            <div><input type="text" name="referenceno" value="<?php echo isset($_POST["referenceno"]) ? $_POST["referenceno"] : ''; ?>" size="38" ></div>
                        </td> 
                        <td><label for="uocfrom" style="font-size:15px;">University Officer Control From<font color='Red'>*</font></label>
                            <!--<div><select name="uocontrol" style="width:300px;"id="uocid" required> 
                            <option selected="selected" disabled selected>---------------- University Officer Control ---------------</option> -->
                            <div><select name="uocfrom" id="uocid" required> 
                                <option value="">------- Select University Officer Control --------</option>
                                
                                <?php foreach($this->uoc as $ucodata): ?>	
                                    <option value="<?php echo $ucodata->id; ?>"><?php echo $ucodata->name; ?></option> 
                                <?php endforeach; ?>
                            </select></div>
                        </td>
                    </tr>
                    <tr>
                        <td><label for="department" style="font-size:15px;">Department From<font color='Red'>*</font></label>
                            <div><select required name="deptfrom"  id="scid"> 
                                <option selected="selected" disabled selected >----------------- Select Department --------------</option>
                            </select></div>
                        </td>
                        <td><label for="schemecode" style="font-size:15px;">Scheme Name From<font color='Red'>*</font></label>
                            <div><select required name="schemfrom" id="schmid"> 
                            <option selected="selected" disabled selected>-------------- Select Scheme ------------------</option>
                        
                            </select><div>
                        </td>
                        <td><label for="employeetype" style="font-size:15px;">Employee Type From<font color='Red'>*</font></label>
                            <div><select name="employeetype" id="emptype" required="required"> 
                                <option value="">------------ Select Employee Type ---------------</option>
                                <option value="Teaching">Teaching</option>
                                <option value="Non Teaching">Non Teaching</option>                     
                            </select></div>
                        </td>
                                               
                    </tr>
                    <tr>
                        <td><label for="designation" style="font-size:15px;">Designation From<font color='Red'>*</font></label>
                            <div><select name="desigfrom" id="desigid" required> 
                                <option selected="selected" disabled selected>-------------- Select Designation -----------------</option>
                                <!--    <//?php foreach($this->desig as $desigdata): ?>	
                                <option value="<//?php echo $desigdata->desig_id; ?>"><//?php echo $desigdata->desig_name; ?></option> 
                                <//?php endforeach; ?>-->
                                </select></div>
                        </td>
                        
                        
                        <td><label for="empname" style="font-size:15px;">Employee Name<font color='Red'>*</font></label>
                            <div><select name="empname" id="empnameid"> 
                                <option value="">--------- Select Employee Name --------------</option>
                               <!-- <//?php foreach($this->usrlist as $usrdata): ?>	
                                    <option value="<//?php echo $usrdata->emp_id; ?>"><//?php echo $usrdata->emp_name; ?></option> 
                                <//?php endforeach; ?> -->
                                                   
                            </select></div>
                        </td>
                         <td><label for="postfrom" style="font-size:15px;">Post From<font color='Red'>*</font></label>
                            <div><input type="text" name="postfrom" id="postfrom"  readonly class="keyup-characters" size="40"  required pattern="[a-zA-Z0-9 ]+" required="required"></div>
                        </td>
                    </tr>
                    <tr>
                        <td><label for="uocontrol" style="font-size:15px;">University Officer Control To<font color='Red'>*</font></label>
                            <div><select name="uocontrolto" id="uocidto" required> 
                                <option value="">------- Select University Officer Control ---------</option>
                                
                                <?php foreach($this->uoc as $ucodata): ?>	
                                    <option value="<?php echo $ucodata->id; ?>"><?php echo $ucodata->name; ?></option> 
                                <?php endforeach; ?>
                            </select></div>
                        </td>
                        <td><label for="dept" style="font-size:15px;">Department To<font color='Red'>*</font></label>
                            <div><select required name="deptto" id="scidto"> 
                               <option value="">----------------- Select Department ------------</option>
                            </select></div>
                        </td>
                        <td><label for="schemecode" style="font-size:15px;">Scheme Name To<font color='Red'>*</font></label>
                            <div><select required name="schemto" id="schmidto"> 
                            <option selected="selected" disabled selected>----------------- Select Scheme ------------------</option>
                        
                            </select><div>
                        </td>
                        
                    </tr>
                    <tr>
                        <td><label for="employeetype" style="font-size:15px;">Employee Type To<font color='Red'>*</font></label>
                            <div><select name="emptypeto" id="emptypeto" required="required"> 
                                <option value="">------------ Select Employee Type ---------------</option>
                                <option value="Teaching">Teaching</option>
                                <option value="Non Teaching">Non Teaching</option>                     
                            </select></div>
                        </td>
                        <td><label for="desigto" style="font-size:15px;">Designation To<font color='Red'>*</font></label>
                            <div><select required name="desigto" id="desigidto"> 
                               <option value="">--------------- Select Designation ---------------</option>
                              
                            </select></div>
                        </td>
                        <td><label for="postto" style="font-size:15px;">Post To<font color='Red'>*</font></label>
                            <div><select required name="postto" id="postto"> 
                               <option value="">--------------- Select Post ------------------------</option>
                            <!--<div><input type="text" name="postto" class="keyup-characters" value="<?php echo isset($_POST["postto"]) ? $_POST["postto"] : ''; ?>" size="40"  required pattern="[a-zA-Z0-9 ]+" required="required"></div>-->
                        </td>
                        <!--<td><label for="postfrom" style="font-size:15px;">Post From<font color='Red'>*</font></label>
                            <div><input type="text" name="postfrom" id="postfrom"  readonly class="keyup-characters" size="40"  required pattern="[a-zA-Z0-9 ]+" required="required"></div>
                        </td>-->
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
                    <!--<tr>
                        <td><label for="emailsentto" style="font-size:15px;">Email Sent To</label>
                            <div><textarea name="emailsentto" rows="5" cols="50" required pattern="[a-zA-Z0-9 ]+" required="required" placeholder="Enter text here...."></textarea></div>
                        </td>
                        
                        
                    </tr>-->
                    <!--<tr>
                        <td><label for="subject" style="font-size:15px;">Subject<font color='Red'>*</font></label>
                            <div><textarea name="subject" rows="5" cols="50" required pattern="[a-zA-Z0-9 ]+" required="required" placeholder="Enter text here...."></textarea><div>
                        </td>
                        <td><label for="ordercontent" style="font-size:15px;">Order Content<font color='Red'>*</font></label>
                            <div><textarea name="ordercontent" rows="5" cols="50" required pattern="[a-zA-Z0-9 ]+" required="required" placeholder="Enter text here...."></textarea>
                            </div>
                        </td>   
                        <td><label for="empname" style="font-size:15px;">Employee Name<font color='Red'>*</font></label>
                            <div><select name="empname" id="empname" class="empdet"> 
                                <option value="">-------------------- Select Employee Name --------------</option>
                                <?php foreach($this->usrlist as $usrdata): ?>	
                                    <option value="<?php echo $usrdata->emp_id; ?>"><?php echo $usrdata->emp_name; ?></option> 
                                <?php endforeach; ?>
                                                   
                            </select></div>
                        </td>
                    </tr>
                    <tr>
                        <td><label for="uocfrom" style="font-size:15px;">University Officer Control From<font color='Red'>*</font></label>
                            <div><input type="text" name="uocfrom" id="uocfrom" readonly class="keyup-characters" size="40"  required pattern="[a-zA-Z0-9 ]+" required="required"></div>
                        </td>
                        <td><label for="uocontrolto" style="font-size:15px;">University Officer Control To<font color='Red'>*</font></label>
                            <div><select name="uocontrolto" required> 
                                <option value="">------------ Select University Officer Control -------------</option>
                                
                                <?php foreach($this->uoc as $ucodata): ?>	
                                    <option value="<?php echo $ucodata->id; ?>"><?php echo $ucodata->name; ?></option> 
                                <?php endforeach; ?>
                            </select></div>
                        </td>
                        <td><label for="dateofrelief" style="font-size:15px;">Date of Relieve</label>
                            <div><input type="text" name="dateofrelief" id="Dateofrelief"  value="<?php echo isset($_POST["dateofrelief"]) ? $_POST["dateofrelief"] : ''; ?>" size="40" /></div>
                        </td>
                    </tr>
                    <tr>
                        <td><label for="deptfrom" style="font-size:15px;">Department From<font color='Red'>*</font></label>
                            <div><input type="text" name="deptfrom" id="deptfrom" readonly class="keyup-characters"  size="40"  required pattern="[a-zA-Z0-9 ]+" required="required"></div>
                        </td>
                        <td><label for="deptto" style="font-size:15px;">Department To<font color='Red'>*</font></label>
                            <div><select required name="deptto" id="dep"> 
                               <option value="">----------------------- Select Department ------------------</option>
                            </select></div>
                        </td>
                        <td><label for="expdoj" style="font-size:15px;">Expected Date of joining</label>
                            <div><input type="text" name="expdoj" id="expDateofjoin"  value="<?php echo isset($_POST["expDateofjoin"]) ? $_POST["expDateofjoin"] : ''; ?>" size="40" /></div>  
                        </td>    
                    </tr>
                    <tr>
                        <td><label for="desigfrom" style="font-size:15px;">Designation From<font color='Red'>*</font></label>
                            <div><input type="text" name="desigfrom" id="desigfrom"  class="keyup-characters"  size="40" readonly required pattern="[a-zA-Z0-9 ]+" required="required"></div>
                        </td>
                        <td colspan="2"><label for="desigto" style="font-size:15px;">Designation To<font color='Red'>*</font></label>
                            <div><select required name="desigto" > 
                               <option value="">--------------------- Select Designation -------------------</option>
                                <?php foreach($this->desig as $desigdata): ?>	
                                    <option value="<?php echo $desigdata->desig_id; ?>"><?php echo $desigdata->desig_name; ?></option> 
                                <?php endforeach; ?>
                            </select></div>
                        </td>
                        
                    </tr>
                    <tr>
                        <td><label for="postfrom" style="font-size:15px;">Post From<font color='Red'>*</font></label>
                            <div><input type="text" name="postfrom" id="postfrom"  readonly class="keyup-characters" size="40"  required pattern="[a-zA-Z0-9 ]+" required="required"></div>
                        </td>
                        <td colspan="2"><label for="postto" style="font-size:15px;">Post To<font color='Red'>*</font></label>
                            <div><input type="text" name="postto" class="keyup-characters" value="<?php echo isset($_POST["postto"]) ? $_POST["postto"] : ''; ?>" size="40"  required pattern="[a-zA-Z0-9 ]+" required="required"></div>
                           -->
                            <!-- <select required name="postto"> 
                              <option value="">-------Select Post-----</option>  
                       
                            </select>-->
                        <!--</td>
                    </tr>
                    <tr>
                        <td><label for="ttadetail" style="font-size:15px;">TTA Detail</label>
                            <div><textarea name="ttadetail" rows="5" cols="50" required pattern="[a-zA-Z0-9 ]+" required="required" placeholder="Enter text here...."></textarea></div>
                            </td>
                        
                        <td colspan="2"><label for="emailsentto" style="font-size:15px;">Email Sent To</label>
                        <div><textarea name="emailsentto" rows="5" cols="50" required pattern="[a-zA-Z0-9 ]+" required="required" placeholder="Enter text here...."></textarea></div>
                        </td> 
                    </tr>-->
                    <tr style="background-color:#2a8fcf;text-align:left;height:40px;">
                        <td colspan="6">   
                            <button name="stafftransfer" >Submit</button>
                            <input type="reset" name="Reset" value="Clear"/>
                        </td>
           
                    </tr>
                    
                </form>   
            </table>
        <!--</div> -->
	<p>&nbsp;</p>
        <div><?php $this->load->view('template/footer'); ?></div> 
    </body>    
</html>    
