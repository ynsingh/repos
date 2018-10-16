    
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
                            //alert("datat==="+data);
                            var empinput=data.split(',');
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
               
            <?php echo form_open_multipart('payrollprofile/payprofile','id="my_id"');?>
                <table  width="100%" class="TFtable">
                
                <tr><thead><th style="background-color:#2a8fcf;text-align:left;height:40px;" colspan="4">&nbsp;&nbsp;Payroll Profile</th></thead></tr>
                <tr>
                    <td><label for="emppfno" style="font-size:15px;"><font>Employee PF No</font> <font color='Red'>*</font></label>
                    <div><input type="text" name="emppfno" id="emppfno" value="" placeholder="Employee PF No..." size="33" required>    
                    </td>
                </tr>
                </table>
                <table  width="100%" class="TFtable">
                        <tr><td colspan="5"><b><span style="color:#0099CC;">Personal Details</span></b></td></tr>
                        <tr>
                            <td> Employee Name:<input type="text" id="empname" value="" style="text-decoration:none;border:0; word-break: break-all;" readonly></td>
                            <td> Bank Ac No:<input type="text"  id="accno" value="" style="text-decoration:none;border:0; word-break: break-all;" readonly></td>
                            <td>  E Aadhar No:<input type="text" id="addharno" value="" style="text-decoration:none;border:0; word-break: break-all;" readonly></td>
                        </tr>
                        <tr>
                            <td> Date of Birth:<input type="text" id="dob" value="" style="text-decoration:none;border:0; word-break: break-all;" readonly></td>
                            <td> Address:<input type="text" id="add" value="" style="text-decoration:none;border:0; word-break: break-all;" readonly></td>
                            <td> Contact details:<input type="text"  id="contact" value="" style="text-decoration:none;border:0; word-break: break-all;" readonly></td>
                        </tr>
                        <tr>
                            <td colspan="5"> Pension Contribution:
                            <!--<input type="checkbox" name="pcontri" id="pcontri" value=""> -->
                                <input type="radio" name="pcontri" value="yes">Yes &nbsp;&nbsp;&nbsp;
                                <input type="radio" name="pcontri" checked value="no">No
                
                            </td>
                        </tr>
                        <tr><td colspan="5"><b> <hr/> <span style="color:#0099CC;">Work Details</span></b></td></tr> 
                        <tr>
                            <td> Campus Name:<!--  <textarea id="campus" value="" rows="3" cols="40"  ></textarea> --><input type="text"  id="campus" value="" size="40" style="text-decoration:none;border:0; word-break: break-all;" readonly></td>
                            <td> University Officer Control:<input type="text"  id="uo" value="" style="text-decoration:none;border:0; word-break: break-all;" readonly></td>
                            <td> Department:<input type="text" id="dept" value=""style="text-decoration:none;border:0; word-break: break-all;" readonly></td>
                        </tr>
                        <tr>
                            <td> Scheme:<input type="text" id="schm" value="" style="text-decoration:none;border:0; word-break: break-all;" readonly></td>
                            <td> DDO:<input type="text"  id="ddo" value="" style="text-decoration:none;border:0; word-break: break-all;" readonly></td>
                            <td> Working Type:<input type="text" id="wtype" value="" style="text-decoration:none;border:0; word-break: break-all;" readonly></td>
                        </tr>
                        
                        <tr>
                            <td> Group :<input type="text"  id="group" value="" style="text-decoration:none;border:0; word-break: break-all;" readonly></td>
                            <td> Designation:<input type="text" id="desig" value="" style="text-decoration:none;border:0; word-break: break-all;" readonly></td>
                            <td> Employee type:<input type="text" id="etype" value="" style="text-decoration:none;border:0; word-break: break-all;" readonly></td>
                        </tr> 
                        <tr>
                            <td> Date of joining :<input type="text"  id="doj" value="" style="text-decoration:none;border:0; word-break: break-all;" readonly></td>
                            <td> Date of Retirement:<input type="text" id="dor" value="" style="text-decoration:none;border:0; word-break: break-all;" readonly>
                                
                            </td>
                            <td> Pay Scale:<input type="text" id="pscale" value="" style="text-decoration:none;border:0; word-break: break-all;" readonly></td>
                        </tr>
                        <tr>
                            <td> UPF No :<input type="text" name="upfno" id="upfno" value="" ></td>
                            <td> Quater No :<input type="text" name="qtrno" id="qtrno" value=""></td>
                            <td> Quarter type:
                                <div><select name="qtrtype" id="qtrtype" > 
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
                            <td> University Employee:
                                <input type="radio" name="uniemp" value="yes">Yes &nbsp;&nbsp;&nbsp;
                                <input type="radio" name="uniemp"  checked  value="no">No
                                <!--<input type="checkbox" name="uniemp"   id="uniemp" value=""> -->
                            </td>
                            <td> Washing Allowance:
                                <input type="radio" name="washallw" value="yes">Yes &nbsp;&nbsp;&nbsp;
                                <input type="radio" name="washallw" checked value="no">No
                                <!--<input type="checkbox" name="washallw" id="washallw"   value=""> -->
                            </td>
                            <td> Deduct UPF:
                                <input type="radio" name="dedupf" value="yes">Yes &nbsp;&nbsp;&nbsp;
                                <input type="radio" name="dedupf"  checked value="no">No
                                <!--<input type="checkbox" name="dedupf" id="dedupf"  value="">-->
                            </td>
                            
                        </tr>
                        <tr>
                            <td> HRA GRADE:
                            <div><select name="hragrade" id="hragrade" > 
                                    <option value="" >------------ HRA GRADE ---------</option>
                                    <?php foreach($this->hglist as $hgdata): ?>	
                                    <option value="<?php echo $hgdata->hgc_id; ?>"><?php echo $hgdata->hgc_gradename;?>
                                    </option> 
                                    <?php endforeach; ?>
                               
                            </select></div>
                            </td>
                            <td> CCA Grade :
                                <div><select name="ccagrade" id="ccagrade" > 
                                    <option value="">------------ CCA GRADE ---------</option>
                                    <?php foreach($this->hglist as $hgdata): ?>	
                                    <option value="<?php echo $hgdata->hgc_id; ?>"><?php echo $hgdata->hgc_gradename;?>
                                    </option> 
                                    <?php endforeach; ?>
                                   
                                </select></div>
                            </td>
                            <td> Include in Summary:
                                <input type="radio" name="incsumm" value="yes">Yes &nbsp;&nbsp;&nbsp;
                                <input type="radio" name="incsumm" checked  value="no">No
                                
                            </td>
                            
                        </tr> 
                        <tr><td colspan="5"><b>  <hr/><span style="color:#0099CC;">LIC/PRD/PLI/Society Entry</span></b></td></tr>
                        <tr>
                            <td> LIC1 No:<input type="text" name="lic1no" id="lic1no" value="">
                            Amount:<input type="text" name="lic1amount" id="lic1amount" value=""></td>
                            <td> PRD1 No :<input type="text" name="prdno1" id="prdno1" value=""></td>
                            
                            <td> Society:
                                <div><select name="society" id="society" > 
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
                            <td> LIC2 No:<input type="text" name="lic2no" id="lic1no" value="">
                            Amount:<input type="text" name="lic2amount" id="lic2amount" value=""></td>
                            <td> PRD2 No :<input type="text" name="prdno2" id="prdno2" value=""></td>
                            <td> Society Member:<br/> <input type="text" name="societymember" id="socmem" value=""></td>
                        </tr>
                        <tr>
                            <td> LIC3 No:<input type="text" name="lic3no" id="lic3no" value="">
                            Amount:<input type="text" name="lic3amount" id="lic3amount" value=""></td>
                            <td colspan="2"> PRD3 No :<input type="text" name="prdno3" id="prdno3" value=""></td>
                          
                        </tr>
                        <tr>
                            <td > LIC4 No:<input type="text" name="lic4no" id="lic4no" value="">
                            Amount:<input type="text" name="lic4amount" id="lic4amount" value=""></td>
                            <td colspan="2"> PLI1 No :<input type="text" name="plino1" id="plino1" value=""></td>
                          
                        </tr>
                        <tr>
                            <td> LIC5 No:<input type="text" name="lic5no" id="lic5no" value="">
                            Amount:<input type="text" name="lic5amount" id="lic5amount" value=""></td>
                            <td  colspan="2"> PLI2 No :<input type="text" name="plino2" id="plino2" value=""></td>
                          
                        </tr>
                     
                        <tr>
                            <td colspan="6">   
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