    
    <!--@filename pprofile.php  @author Manorama Pal(palseema30@gmail.com) -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Welcome to TANUVAS</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/datepicker/jquery-ui.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-ui.js" ></script>
        <script>
            $(document).ready(function(){
           
            /**********************************Start of empdetail by  PF NOscript*********************************/
                
                $("#emppfno").on('change',function(){
                    var pfno = $("#emppfno").val();
                    if(pfno!=''){
                      //  alert("23==="+pfno);
                        $.ajax({
                            url: "<?php echo base_url();?>sisindex.php/jslist/getempdata2",
                            type: "POST",
                            data: {"emplypfno" : pfno},
                            dataType:"html",
                            success:function(data){
                //            alert("datat==="+data);
                            var empinput=data.split(",");
                            $('#campus').val(empinput[0].replace(/[[\]"|"]/g,""));
                            $('#uo').val(empinput[1].replace(/"|"/g,""));
                            $('#dept').val(empinput[2].replace(/"|"/g,""));
                            $('#schm').val(empinput[3].replace(/"|"/g,""));
                            $('#ddo').val(empinput[4].replace(/"|"/g,""));
                            $('#wtype').val(empinput[5].replace(/"|"/g,""));
                            $('#group').val(empinput[6].replace(/"|"/g,""));
                            $('#desig').val(empinput[7].replace(/"|"/g,""));
                            $('#etype').val(empinput[8].replace(/"|"/g,""));
                            $('#doj').val(empinput[9].replace(/"|"/g,""));
                            $('#empname').val(empinput[10].replace(/"|"/g,""));
                            $('#accno').val(empinput[11].replace(/"|"/g,""));
                            $('#addharno').val(empinput[12].replace(/"|"/g,""));
                            $('#dob').val(empinput[13].replace(/"|"/g,""));
                            $('#add').val(empinput[14].replace(/"|"/g,""));
                            $('#contact').val(empinput[15].replace(/"|"/g,""));
                            $('#dor').val(empinput[16].replace(/"|"/g,""));
                            $('#pscale').val(empinput[17].replace(/[[\]"|"]/g,""));
	//			var bankifsc=empinput[18].replace(/[[\]"|"]/g,"");
	//			alert("bank full"+bankifsc);		
	//			var bankfull=bankifsc.split('');
                   //         $('#bname').val(bankfull[0].replace(/[[\]"|"]/g,""));
                     //       $('#ifsccode').val(bankfull[1].replace(/[[\]"|"]/g,""));
                       //     $('#branch').val(bankfull[2].replace(/[[\]"|"]/g,""));

                            $('#bname').val(empinput[18].replace(/[[\]"|"]/g,""));
                            $('#ifsccode').val(empinput[19].replace(/[[\]"|"]/g,""));
                            $('#branch').val(empinput[20].replace(/[[\]"|"]/g,""));
                            $('#pcomm').val(empinput[21].replace(/[[\]"|"]/g,""));
                            
                            var pcon=empinput[22].replace(/[[\]"|"]/g,"");
                            if(pcon ==='yes'){
                                $('#pcontri').prop("checked", true);
                               // $('#pcontri1').is(':checked');
                            } 
                            //$('#pcontri').val(empinput[21].replace(/[[\]"|"]/g,""));
                            $('#upfno').val(empinput[23].replace(/[[\]"|"]/g,""));
                            $('#qtrno').val(empinput[24].replace(/[[\]"|"]/g,""));
                            $('#qtrtype').val(empinput[25].replace(/[[\]"|"]/g,""));
                            var univemp=empinput[26].replace(/[[\]"|"]/g,"");
                            if(univemp ==='yes'){
                                $('#uniemp').prop("checked", true);
                              
                            } 
                           // $('#uniemp').val(empinput[25].replace(/[[\]"|"]/g,""));
                         //   $('#washallw').val(empinput[26].replace(/[[\]"|"]/g,""));
                            var washA=empinput[27].replace(/[[\]"|"]/g,"");
                            if(washA ==='yes'){
                                $('#washallw').prop("checked", true);
                              
                            } 
                           // $('#dedupf').val(empinput[27].replace(/[[\]"|"]/g,""));
                            var Dupf=empinput[28].replace(/[[\]"|"]/g,"");
                            if(Dupf ==='yes'){
                                $('#dedupf').prop("checked", true);
                              
                            } 
                            $('#hragrade').val(empinput[29].replace(/[[\]"|"]/g,""));
                            $('#ccagrade').val(empinput[30].replace(/[[\]"|"]/g,""));
                            var Isumm=empinput[31].replace(/[[\]"|"]/g,"");
                            if(Isumm ==='yes'){
                                $('#incsumm').prop("checked", true);
                              
                            } 
                          //  $('#incsumm').val(empinput[30].replace(/[[\]"|"]/g,""));
                            $('#lic1no').val(empinput[32].replace(/[[\]"|"]/g,""));
                            $('#lic1amount').val(empinput[33].replace(/[[\]"|"]/g,""));
                            $('#lic2no').val(empinput[34].replace(/[[\]"|"]/g,""));
                            $('#lic2amount').val(empinput[35].replace(/[[\]"|"]/g,""));
                            $('#lic3no').val(empinput[36].replace(/[[\]"|"]/g,""));
                            $('#lic3amount').val(empinput[37].replace(/[[\]"|"]/g,""));
                            $('#lic4no').val(empinput[38].replace(/[[\]"|"]/g,""));
                            $('#lic4amount').val(empinput[39].replace(/[[\]"|"]/g,""));
                            $('#lic5no').val(empinput[40].replace(/[[\]"|"]/g,""));
                            $('#lic5amount').val(empinput[41].replace(/[[\]"|"]/g,""));
                            $('#prdno1').val(empinput[42].replace(/[[\]"|"]/g,""));
                            $('#prdno2').val(empinput[43].replace(/[[\]"|"]/g,""));
                            $('#prdno3').val(empinput[44].replace(/[[\]"|"]/g,""));
                            $('#plino1').val(empinput[45].replace(/[[\]"|"]/g,""));
                            $('#plino2').val(empinput[46].replace(/[[\]"|"]/g,""));
                            $('#society').val(empinput[47].replace(/[[\]"|"]/g,""));
                            $('#socmem').val(empinput[48].replace(/[[\]"|"]/g,""));

                     /*       $('#pcomm').val(empinput[19].replace(/[[\]"|"]/g,""));
                            
                            var pcon=empinput[20].replace(/[[\]"|"]/g,"");
                            if(pcon ==='yes'){
                                $('#pcontri').prop("checked", true);
                               // $('#pcontri1').is(':checked');
                            } 
                            //$('#pcontri').val(empinput[21].replace(/[[\]"|"]/g,""));
                            $('#upfno').val(empinput[21].replace(/[[\]"|"]/g,""));
                            $('#qtrno').val(empinput[22].replace(/[[\]"|"]/g,""));
                            $('#qtrtype').val(empinput[23].replace(/[[\]"|"]/g,""));
                            var univemp=empinput[24].replace(/[[\]"|"]/g,"");
                            if(univemp ==='yes'){
                                $('#uniemp').prop("checked", true);
                              
                            } 
                           // $('#uniemp').val(empinput[25].replace(/[[\]"|"]/g,""));
                         //   $('#washallw').val(empinput[26].replace(/[[\]"|"]/g,""));
                            var washA=empinput[25].replace(/[[\]"|"]/g,"");
                            if(washA ==='yes'){
                                $('#washallw').prop("checked", true);
                              
                            } 
                           // $('#dedupf').val(empinput[27].replace(/[[\]"|"]/g,""));
                            var Dupf=empinput[26].replace(/[[\]"|"]/g,"");
                            if(Dupf ==='yes'){
                                $('#dedupf').prop("checked", true);
                              
                            } 
                            $('#hragrade').val(empinput[27].replace(/[[\]"|"]/g,""));
                            $('#ccagrade').val(empinput[28].replace(/[[\]"|"]/g,""));
                            var Isumm=empinput[29].replace(/[[\]"|"]/g,"");
                            if(Isumm ==='yes'){
                                $('#incsumm').prop("checked", true);
                              
                            } 
                          //  $('#incsumm').val(empinput[30].replace(/[[\]"|"]/g,""));
                            $('#lic1no').val(empinput[30].replace(/[[\]"|"]/g,""));
                            $('#lic1amount').val(empinput[31].replace(/[[\]"|"]/g,""));
                            $('#lic2no').val(empinput[32].replace(/[[\]"|"]/g,""));
                            $('#lic2amount').val(empinput[33].replace(/[[\]"|"]/g,""));
                            $('#lic3no').val(empinput[34].replace(/[[\]"|"]/g,""));
                            $('#lic3amount').val(empinput[35].replace(/[[\]"|"]/g,""));
                            $('#lic4no').val(empinput[36].replace(/[[\]"|"]/g,""));
                            $('#lic4amount').val(empinput[37].replace(/[[\]"|"]/g,""));
                            $('#lic5no').val(empinput[38].replace(/[[\]"|"]/g,""));
                            $('#lic5amount').val(empinput[39].replace(/[[\]"|"]/g,""));
                            $('#prdno1').val(empinput[40].replace(/[[\]"|"]/g,""));
                            $('#prdno2').val(empinput[41].replace(/[[\]"|"]/g,""));
                            $('#prdno3').val(empinput[42].replace(/[[\]"|"]/g,""));
                            $('#plino1').val(empinput[43].replace(/[[\]"|"]/g,""));
                            $('#plino2').val(empinput[44].replace(/[[\]"|"]/g,""));
                            $('#society').val(empinput[45].replace(/[[\]"|"]/g,""));
                            $('#socmem').val(empinput[46].replace(/[[\]"|"]/g,""));
*/
   				$('#empid').val(empinput[49].replace(/[[\]"|"]/g,""));                            
                        },
                        error:function(data){
                            alert("error occur..!!");
                 
                        }
                    });
                }    
                
                });  //method empname
                /**********************************End of empdetail PF NO  script*********************************/
                
                  
                /************************select society memebers on the basis of society*******************/
                       
                $('#society').on('change',function(){
                    var soc = $('#society').val();
                   // alert("soc====="+soc);
                    if(soc == ''){
                        $('#socmem').prop('disabled',true);
                    }
                    else{
             
                        $('#socmem').prop('disabled',false);
                        $.ajax({
                            url: "<?php echo base_url();?>sisindex.php/jslist/getsocietymembers",
                            type: "POST",
                            data: {"society" : soc},
                            dataType:"html",
                            success:function(data){
                                //alert("data==="+data);
                                $('#socmem').val(data.replace(/"|"/g,""));
                       
                            },
                            error:function(data){
                            //alert("data in error==="+data);
                                alert("error occur..!!");
                 
                            }
                                            
                        });
                    }
                }); 
            
                /*********************closer of scheme**************************************************/
            
            });
            
                      
    </script>    
    </head>
    <body>
        <?php $this->load->view('template/header'); ?>
        <table width="100%"><tr><td>
        <?php
           // echo anchor('payrollprofile/payprofile', "Payroll Profile" ,array('title' => 'Payroll Profile' , 'class' => 'top_parent'));
            
            ?>
        </td></tr>
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
               
                <table  width="100%" class="TFtable">
                
                <tr><thead><th style="background-color:#2a8fcf;text-align:left;height:40px;" colspan="4">&nbsp;&nbsp;Payroll Profile</th></thead></tr>
                <tr>
                    <td><label for="emppfno" style="font-size:15px;"><font>Employee PF No</font> <font color='Red'>*</font></label>
                    <div><input type="text" name="emppfno" id="emppfno" value="" placeholder="Employee PF No..."  required>    
                    </td>
                </tr>
                </table>
            <?php echo form_open_multipart('payrollprofile/payprofile','id="my_id"');?>
                <table  width="100%" class="TFtable" >
                        <tr><td colspan="5"><b><span style="color:#0099CC;">Personal Details</span></b></td></tr>
                        <tr>
                            <td> <b>Employee Name:</b><br><input type="text" id="empname" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                            <td> <b>Bank ACC No:</b><br><input type="text" name="accno" id="accno" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;"></td>
                            <td> <b>Bank Name:</b><br><input type="text" name="bname" id="bname" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;"></td>
                            <td> <b>IFSC Code:</b><br><input type="text" name="ifsccode" id="ifsccode" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;"></td>
                        </tr>
                        <tr>
                            <td> <b>Bank Branch:</b><br><input type="text" name="bbranch" id="branch" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;"></td>
                            <td> <b>E Aadhar No:</b><br><input type="text" id="addharno" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                            <td> <b>Date of Birth:</b><br><input type="text" id="dob" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                            <td> <b>Contact No:</b><br><input type="text"  id="contact" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                        </tr>
                        <tr>
                            <td colspan=2> <b>Address:</b><br><input type="text" id="add" value="" style="text-decoration:none;border:0; word-break: break-all;width:600px;" readonly></td>
                            <td colspan="2" style="width:300px;"> <b>Pension Contribution:</b><br>
                            <!--<input type="checkbox" name="pcontri" id="pcontri" value=""> -->
                                <input type="radio" name="pcontri" id="pcontri" value="yes">Yes &nbsp;&nbsp;&nbsp;
                                <input type="radio" name="pcontri" id="pcontri" checked value="no">No
                            </td>
                        </tr>
                        <tr><td colspan="5"><b> <hr/> <span style="color:#0099CC;">Work Details</span></b></td></tr> 
                        <tr>
                            <td> <b>Campus Name:</b><br><input type="text"  id="campus" value=""  style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                            <td> <b>UO:</b><br><input type="text"  id="uo" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                            <td> <b>Department:</b><br><input type="text" id="dept" value=""style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                             <td> <b>Scheme:</b><br><input type="text" id="schm" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                        </tr>
                        <tr>
                            <td> <b>DDO:</b><br><input type="text"  id="ddo" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                            <td> <b>Working Type:</b><br><input type="text" id="wtype" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                            <td> <b>Group :</b><br><input type="text"  id="group" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                            <td> <b>Designation:</b><br><input type="text" id="desig" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                        </tr>
                        
                        <tr>
                            <td> <b>Employee type:</b><br><input type="text" id="etype" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                            <td> <b>Date of joining :</b><br><input type="text"  id="doj" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                            <td> <b>Date of Retirement:</b><br><input type="text" id="dor" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                            <td> <b>Pay Commision:</b><br><input type="text" id="pcomm" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                        </tr> 
                        <tr>
                            <td> <b>Pay Scale:</b><br><input type="text" id="pscale" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                            <td> <b>UPF No :</b><br><input type="text" name="upfno" id="upfno" value="" style="width:300px;"></td>
                            <td> <b>Quater No :</b><br><input type="text" name="qtrno" id="qtrno" value="" style="width:300px;"></td>
                            <td> <b>Quarter type:</b><br>
                                <div><select name="qtrtype" id="qtrtype" style="width:300px;"> 
                                <option value="">------------ Quarter Type ---------</option>
                                <option value="Type1">Type 1</option>
                                <option value="Type2">Type 2</option>
                                <option value="Type3">Type 3</option>
                                <option value="Type4">Type 4</option>
                                <option value="Type5">Type 5</option>
                                <option value="Type6">Type 6</option>
                                </select></div>
                            </td>
                        </tr> 
                         
                        <tr>
                            <td style="width:300px;"><b> University Employee:</b><br>
                                <input type="radio" name="uniemp" id="uniemp" value="yes">Yes &nbsp;&nbsp;&nbsp;
                                <input type="radio" name="uniemp" id="uniemp" checked  value="no">No
                                <!--<input type="checkbox" name="uniemp"   id="uniemp" value=""> -->
                            </td>
                            <td style="width:300px;"> <b>Washing Allowance:</b><br>
                                <input type="radio" name="washallw" id="washallw"  value="yes">Yes &nbsp;&nbsp;&nbsp;
                                <input type="radio" name="washallw"  id="washallw" checked value="no">No
                                <!--<input type="checkbox" name="washallw" id="washallw"   value=""> -->
                            </td>
                            <td style="width:300px;"> <b>Deduct UPF:</b><br>
                                <input type="radio" name="dedupf" id="dedupf" value="yes">Yes &nbsp;&nbsp;&nbsp;
                                <input type="radio" name="dedupf" id="dedupf"  checked value="no">No
                                <!--<input type="checkbox" name="dedupf" id="dedupf"  value="">-->
                            </td>
                            <td> <b>HRA Grade:</b><br>
                            <div><select name="hragrade" id="hragrade" style="width:300px;"> 
                                    <option value="" >------------ HRA GRADE ---------</option>
                                    <?php foreach($this->hglist as $hgdata): ?>	
                                    <option value="<?php echo $hgdata->hgc_id; ?>"><?php echo $hgdata->hgc_gradename;?>
                                    </option> 
                                    <?php endforeach; ?>
                               
                            </select></div>
                            </td>
                        </tr>
                        <tr>
                            <td> <b>CCA Grade :</b><br>
                                <div><select name="ccagrade" id="ccagrade" style="width:300px;"> 
                                    <option value="">------------ CCA GRADE ---------</option>
                                    <?php foreach($this->hglist as $hgdata): ?>	
                                    <option value="<?php echo $hgdata->hgc_id; ?>"><?php echo $hgdata->hgc_gradename;?>
                                    </option> 
                                    <?php endforeach; ?>
                                </select></div>
                            </td>
                            <td colspan="3" style="width:300px;"> <b>Include in Summary:</b><br>
                                <input type="radio" name="incsumm" id="incsumm" value="yes">Yes &nbsp;&nbsp;&nbsp;
                                <input type="radio" name="incsumm" id="incsumm" checked  value="no">No
                            </td>
                        </tr> 
                        <tr><td colspan="5"><b>  <hr/><span style="color:#0099CC;">LIC/PRD/PLI/Society Entry</span></b></td></tr>
                        <tr>
                            <td><b> LIC1 No:</b><br><input type="text" name="lic1no" id="lic1no" value="" style="width:300px;"></td>
                            <td><b>Amount:</b><br><input type="text" name="lic1amount" id="lic1amount" value="" style="width:300px;"></td>
                            <td><b> PRD1 No :</b><br><input type="text" name="prdno1" id="prdno1" value="" style="width:300px;"></td>
                            
                            <td><b> Society:</b><br>
                                <div><select name="society" id="society" style="width:300px;"> 
                                    <option value="">------------ Society ---------</option>
                                    <?php foreach($this->society as $socdata): ?>	
                                    <option value="<?php echo $socdata->soc_id; ?>"><?php echo $socdata->soc_sname."(".$this->sismodel->get_listspfic1('society_master_list','soc_scode','soc_id',$socdata->soc_id)->soc_scode.")";?>
                                    </option> 
                                    <?php endforeach; ?>
                                   
                                </select></div>
                                <!--<br/><input type="text" name="society" id="society" value="">-->
                                </td>
                        </tr>
                        <tr>
                            <td><b> LIC2 No:</b><br><input type="text" name="lic2no" id="lic2no" value="" style="width:300px;"></td>
                            <td><b>Amount:</b><br><input type="text" name="lic2amount" id="lic2amount" value="" style="width:300px;"></td>
                            <td><b> PRD2 No :</b><br><input type="text" name="prdno2" id="prdno2" value="" style="width:300px;"></td>
                            <td><b> Society Member:</b><br> <input type="text" name="societymember" id="socmem" value="" style="width:300px;"></td>
                        </tr>
                        <tr>
                            <td><b> LIC3 No:</b><br><input type="text" name="lic3no" id="lic3no" value="" style="width:300px;"></td>
                            <td><b>Amount:</b><br><input type="text" name="lic3amount" id="lic3amount" value="" style="width:300px;"></td>
                            <td colspan="2"><b> PRD3 No :</b><br><input type="text" name="prdno3" id="prdno3" value="" style="width:300px;"></td>
                          
                        </tr>
                        <tr>
                            <td ><b> LIC4 No:</b><br><input type="text" name="lic4no" id="lic4no" value="" style="width:300px;"></td>
                            <td ><b>Amount:</b><br><input type="text" name="lic4amount" id="lic4amount" value="" style="width:300px;"></td>
                            <td colspan="2"><b> PLI1 No :</b><br><input type="text" name="plino1" id="plino1" value="" style="width:300px;"></td>
                          
                        </tr>
                        <tr>
                            <td><b> LIC5 No:</b><br><input type="text" name="lic5no" id="lic5no" value="" style="width:300px;"></td>
                            <td><b>Amount:</b><br><input type="text" name="lic5amount" id="lic5amount" value="" style="width:300px;"></td>
                            <td  colspan="2"><b> PLI2 No :</b><br><input type="text" name="plino2" id="plino2" value="" style="width:300px;"></td>
                        </tr>
                        <tr>
                            <td colspan="6">   
				 <input type="hidden" name="empid" id="empid" value="" >
                            <button name="pprofile" id="pprofile">Submit</button>
                            <input type="reset" name="Reset" value="Clear"/>
                            </td>
                        </tr>
                    </table>
              <!--  </form> -->
            </body>
            <p>&nbsp;</p>
        <div><?php $this->load->view('template/footer'); ?></div>     
</html>    
