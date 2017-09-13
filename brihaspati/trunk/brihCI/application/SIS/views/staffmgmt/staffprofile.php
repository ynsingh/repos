<!--@filename staffprofile.php  @author Manorama Pal(palseema30@gmail.com) -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Welcome to TANUVAS</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery-ui.css">
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/stylecal.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
        
        <script>$(document).ready(function(){
            $("#StartDate").datepicker({
                dateFormat: 'yy/mm/dd',
                numberOfMonths: 1,
                autoclose: true
                       
            });
            $("#Dateofphd").datepicker({
                dateFormat: 'yy/mm/dd',
                numberOfMonths: 1,
                autoclose: true,
               
            });
           /* $("#Dateofretir").datepicker({
                dateFormat: 'yy/mm/dd',
                numberOfMonths: 1,
                autoclose: true,
               
            });*/
            /*************************************************************calculate date of retirement******************/
            $("#Dateofbirth").on('change',function(){
                var dob= $(this).val();
                var birthDate = new Date(dob);
               // alert(dob);
                //alert(birthDate);
                var retDate = new Date(birthDate.getFullYear() + 60, birthDate.getMonth(), birthDate.getDate()-1);
                //alert(retDate);
                var lastDayWithSlashes = retDate.getFullYear()+ '/' + (retDate.getMonth() + 1)+'/' +retDate.getDate();
                //alert(lastDayWithSlashes);
                return $('#Dateofretir').val(lastDayWithSlashes);
               
            });
            /******************************close date of retirement********************************************************/
            
            $("#Dateofassrexam").datepicker({
                dateFormat: 'yy/mm/dd',
                numberOfMonths: 1,
                autoclose: true,
               
            });
            $("#Dateofhgp").datepicker({
                dateFormat: 'yy/mm/dd',
                numberOfMonths: 1,
                autoclose: true,
               
            });
            $("#Dateofbirth").datepicker({
                dateFormat: 'yy/mm/dd',
                numberOfMonths: 1,
                autoclose: true,
               
            });
            
            /*******department of selected campus*******************************/
            
            $('#camp').on('change',function(){
                var sc_code = $(this).val();
               // alert(sc_code);
                if(sc_code == ''){
                    $('#scid').prop('disabled',true);
                }
                else{
             
                    $('#scid').prop('disabled',false);
                    $.ajax({
                        url: "<?php echo base_url();?>sisindex.php/staffmgmt/getdeptlist",
                        type: "POST",
                        data: {"campusname" : sc_code},
                        dataType:"html",
                        success:function(data){
                          //  alert(data);
                        $('#scid').html(data.replace(/^"|"$/g, ''));
                       
                        },
                        error:function(data){
                            alert("error occur..!!");
                 
                        }
                    });
                }
            }); 
            /*****end of department***************************/
            /************************select schemes of selected department****/
             $('#scid').on('change',function(){
                var schm_id = $(this).val();
               // alert("seema==="+schm_id);
                if(schm_id == ''){
                    $('#schmid').prop('disabled',true);
                }
                else{
             
                    $('#schmid').prop('disabled',false);
                    $.ajax({
                       // alert("seema=====");
                        url: "<?php echo base_url();?>sisindex.php/staffmgmt/getdeptschemelist",
                        type: "POST",
                        data: {"deptid" : schm_id},
                        dataType:"html",
                        success:function(data){
                           // alert("ok===="); 
                           //  alert("data==1="+data);
                            $('#schmid').html(data.replace(/^"|"$/g, ''));
                       
                        },
                        error:function(data){
                           // alert("data seema==="+data);
                            alert("error occur..!!");
                 
                        }
                                            
                    });
                }
            }); 
            
            /*********************close***select schemes of selected department****/
            
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
               
        </script>
       
        <style>
           
        </style>
    </head>
    <body>
        <div>
           
            <?php $this->load->view('template/header'); ?>
            <h3>Welcome <?= $this->session->userdata('username') ?></h3>
            <?php $this->load->view('template/menu');?>
        
        </div> 
        <table style="margin-left:1%;width:97%;"><tr><td>
        <?php echo anchor('staffmgmt/employeelist/', "View Employee List" ,array('title' => 'View Employee List ' , 'class' => 'top_parent'));
        $help_uri = site_url()."/help/helpdoc#StaffProfile";
        echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:70%\">Click for Help</b></a>";
?>
        </td></tr></table>
        <div align="left" style="margin-left:2%;width:97%;">
            
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
        <table style="margin-left:2%; margin-right:2%; width:97%; border:1px solid gray;" border=1 class="TFtable">
        
            <?php echo form_open_multipart('staffmgmt/staffprofile');?>
            <tr>
                <td>Upload Photo:</td><td colspan="7" ><input type='file' name='userfile' size='20' /></td>
               
            </tr>    
           <tr>
                
                <td >Employee PF No:</td><td> <input type="text" name="empcode" class="keyup-characters" value="<?php echo isset($_POST["empcode"]) ? $_POST["empcode"] : ''; ?>" placeholder="employee PF No..." size="25"  required pattern="[a-zA-Z0-9 ]+" required="required"></td>
                <td >Employee Name: </td><td><input type="text" name="empname"  class="keyup-characters" value="<?php echo isset($_POST["empname"]) ? $_POST["empname"] : ''; ?>" placeholder="employee name..." size="25" required="required"></td>
                <td>Father Name:</td><td> <input type="text" name="fathername" class="keyup-characters" value="<?php echo isset($_POST["fathername"]) ? $_POST["fathername"] : ''; ?>" placeholder="Father Name..." size="25" ></td>
                <td >Specialisation(Major Subject):</td><td>
                    <select name="specialisation"> 
                        <option value="0">--------Major Subject-----</option>
                        <?php foreach($this->subject as $subdata): ?>	
   				<option value="<?php echo $subdata->sub_id; ?>"><?php echo $subdata->sub_name; ?></option> 
 			<?php endforeach; ?>
                       
                    </select>
                </td>
                 
            </tr>
            <tr style="height:10px;"></tr>
            <tr>
                <td>Campus Name: </td><td width="10%"><select id="camp" name="campus" style="width:100%;" required> 
                        <option selected="selected" disabled selected>--------Campus Name-----</option>
                       <?php foreach($this->campus as $camdata): ?>	
   				<option class="test" value="<?php echo $camdata->sc_id; ?>"><?php echo $camdata->sc_name; ?></option> 
 			<?php endforeach; ?>
                      
                </select></td> 
                <td>University Officer Control:</td><td>
                    <select name="uocontrol" required> 
                        <option selected="selected" disabled selected>--------University Officer Control -----</option>
                       
                        <?php foreach($this->uoc as $ucodata): ?>	
                            <option value="<?php echo $ucodata->id; ?>"><?php echo $ucodata->name; ?></option> 
 			<?php endforeach; ?>
                    </select>
                </td>
                <td>Department:</td><td>
                    <select required name="department" id="scid"> 
                        <option disabled selected >--------Department -----</option>
                       
                    </select>
                </td>
                <td>Scheme Name:</td><td>
                    <select required name="schemecode" id="schmid"> 
                        <option disabled selected>--------Scheme Name -----</option>
                        
                    </select>
                </td>
                
            </tr>
            <tr style="height:10px;"></tr>
            <tr>
                <td>Designation:</td><td>
                    <select name="designation" required > 
                        <option selected="selected" disabled selected>--------Designation -----</option>
                            <?php foreach($this->desig as $desigdata): ?>	
                            <option value="<?php echo $desigdata->desig_id; ?>"><?php echo $desigdata->desig_name; ?></option> 
 			<?php endforeach; ?>
                    </select>
                </td>
                <!--<td >Employee Post:</td>-->
                <td >Shown against the Post:</td>
                <td> <input type="text" name="emppost" value="<?php echo isset($_POST["emppost"]) ? $_POST["emppost"] : ''; ?>" placeholder="Employee Post..." size="25"></td><br>
                <td>Working Type:</td><td>
                        <select name="workingtype"> 
                        <option value="">--------Working Type -----</option>
                        <option value="Teaching">Teaching</option>
                        <option value="NON-Teaching">NON Teaching</option>
                    </select>
                    </td> 
                <td>Employee Type:</td><td>
                    <select name="emptype"> 
                        <option value="">--------Employee Type -----</option>
                        <option value="Permanent">Permanent</option>
                        <option value="Temporary">Temporary</option>
                    </select>
                </td> 
                
            </tr> 
            <tr style="height:10px;"></tr>
            <tr>
                <td>Gender:</td><td><select name="gender"> 
                        <option value="">--------Gender ---------</option>
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                        <option value="Transgender">Transgender</option>
                    </select></td> 
                    
                <td>Community:</td><td><select name="community"> 
                        <option value="">--------Community ---------</option>
                        <option value="General ">General</option>
                        <option value="Other Backward Class">Other Backward Class</option>
                        <option value="Scheduled Caste">Scheduled Caste</option>
                        <option value="Scheduled Tribe">Scheduled Tribe</option>
                        
                    </select></td> 
                <td>Religion:</td><td><select name="religion"> 
                        <option value="">--------Religion ---------</option>
                        <option value="Hinduism">Hinduism</option>
                        <option value="Islam">Islam</option>
                        <option value="Sikhism">Sikhism</option>
                        <option value="Christianity">Christianity</option>
                        <option value=" Buddhism">Buddhism</option>
                        <option value="Jainism">Jainism</option>
                    </select></td>
                    <td>Caste:</td><td><input type="text" name="caste" value="<?php echo isset($_POST["caste"]) ? $_POST["caste"] : ''; ?>" placeholder="Caste..." size="25" ></td> 
            </tr>
            <tr style="height:10px;"></tr>
            <tr>
            
                <td>Pay Band:</td><td>
                    <select name="payband" required> 
                        <option selected="selected" disabled selected>--------Pay Band -----</option>
                        <?php foreach($this->salgrd as $salgrddata): ?>	
                            <option value="<?php echo $salgrddata->sgm_id; ?>"><?php echo $salgrddata->sgm_name."(". $salgrddata->sgm_min."-".$salgrddata->sgm_max.")".$salgrddata->sgm_gradepay; ?>
                            </option> 
 			<?php endforeach; ?>
                       
                    </select>
                </td>
                <td>Basic Pay:</td><td><input type="text" name="basicpay"  class="keyup-numeric" value="<?php echo isset($_POST["basicpay"]) ? $_POST["basicpay"] : ''; ?>" placeholder="Basic pay..." size="25" ></td> 
                <td>Emolution:</td><td><input type="text" name="emolution" class="keyup-numeric" value="<?php echo isset($_POST["emolution"]) ? $_POST["emolution"] : ''; ?>" placeholder="Emolution..." size="25" ></td> 
                <td >NHIS ID No:</td><td> <input type="text" name="empnhisidno" class="keyup-characters" value="<?php echo isset($_POST["empnhisidno"]) ? $_POST["empnhisidno"] : ''; ?>" placeholder="NHIS ID NO..." size="25"></td>
                 
            </tr>
            <tr style="height:10px;"></tr>
            <tr>
                <td>Date of Appointment:</td><td><input type="text" name="dateofjoining" value="<?php echo isset($_POST["dateofjoining"]) ? $_POST["dateofjoining"] : ''; ?>" id="StartDate"  size="25" required="required"></td>       
                <td>Plan / Non Plan:</td><td>
                    <select name="pnp"> 
                        <option value="">--------Plan/Non Plan -----</option>
                        <option value="Plan">Plan</option>
                        <option value="Non-Paln">Non Plan</option>
                    </select>
                </td>
                <td>Phd Status:</td><td>
                    <select name="phdstatus"> 
                        <option value="">--------Phd Status -----</option>
                        <option value="Complete">Complete</option>
                        <option value="Not Complete">Not Complete</option>
                    </select>
                </td>
                <td>Date of Phd Finished:</label></td>
                <td><input type="text" name="dateofphd" id="Dateofphd"  value="<?php echo isset($_POST["dateofphd"]) ? $_POST["dateofphd"] : ''; ?>" size="26" />
            </tr>
            <tr style="height:10px;"></tr>
            <tr>
            <td>ASSR Exam:</td><td>
                    <select name="assrexam"> 
                        <option value="">--------ASSR Exam-----</option>
                        <option value="Passed">Passed</option>
                        <option value="Fail">Fail</option>
                    </select>
                </td>
                <td>Date of ASSR Exam:</td>
                <td><input type="text" name="assrexamdate" id="Dateofassrexam" value="<?php echo isset($_POST["assrexamdate"]) ? $_POST["assrexamdate"] : ''; ?>"class="form-control" size="26" />
                <td>Date of Birth: </td><td><input type="text" name="DateofBirth" value="<?php echo isset($_POST["DateofBirth"]) ? $_POST["DateofBirth"] : ''; ?>" id="Dateofbirth" size="25" required="required"></td>
                <!--<td>Date of Retirement:</label></td>
                <td><input type="text" name="dateofretirement" value="<?php echo isset($_POST["dateofretirement"]) ? $_POST["dateofretirement"] : ''; ?>" id="Dateofretir" class="form-control" size="26" /></td>-->
                <td>Date of HGP:</label></td>
                <td><input type="text" name="dateofhgp" id="Dateofhgp" value="<?php echo isset($_POST["dateofhgp"]) ? $_POST["dateofhgp"] : ''; ?>" class="form-control" size="26" /></td>
            </tr>
            <tr style="height:10px;"></tr>
            <tr>
                <!--<td>Pan No:</td><td><input type="text" name="panno" MaxLength="10" value="<?php //echo isset($_POST["panno"]) ? $_POST["panno"] : ''; ?>" placeholder="Pan No..." size="25" onblur="ValidatePAN(this);"></td> -->
                <td>Pan No:</td><td><input type="text" name="panno" id="txtPANNumber" MaxLength="10" value="<?php echo isset($_POST["panno"]) ? $_POST["panno"] : ''; ?>" placeholder="Pan No..." size="25" ></td> 
                <td>Bank Name:</td><td><input type="text" name="bankname" class="keyup-characters" value="<?php echo isset($_POST["bankname"]) ? $_POST["bankname"] : ''; ?>" placeholder="Bank Name..." size="25" ></td>
                <td>IFSC Code:</td><td><input type="text" name="ifsccode" class="keyup-characters" value="<?php echo isset($_POST["ifsccode"]) ? $_POST["ifsccode"] : ''; ?>" placeholder="IFSC CODE..." size="25" ></td>
                <td>Bank ACC No:</td><td><input type="text" name="bankacno" class="keyup-characters" value="<?php echo isset($_POST["bankacno"]) ? $_POST["bankacno"] : ''; ?>" placeholder="Bank Acc No..." size="25" required="required"></td>
            </tr>
            <tr style="height:10px;"></tr>
            <tr>
                
                <td>Date of Retirement:</label></td>
                <td><input type="text" name="dateofretirement" value="<?php echo isset($_POST["dateofretirement"]) ? $_POST["dateofretirement"] : ''; ?>" id="Dateofretir" class="form-control" size="26" /></td>
                <!--<td><input type="text" name="dateofretirement" id="btnCalc" value="<?php //echo isset($_POST["dateofretirement"]) ? $_POST["dateofretirement"] : ''; ?>" id="Dateofretir" class="form-control" size="26" /></td>-->
                <td>Aadhaar No:</td><td><input type="text" name="Aadharrno" class="keyup-numeric" MaxLength="12" value="<?php echo isset($_POST["Aadharrno"]) ? $_POST["Aadharrno"] : ''; ?>" placeholder="Aadharr No..." size="25" required="required"></td>
                <td>E-Mail ID:</td><td><input type="text" name="emailid" class="keyup-email" value="<?php echo isset($_POST["emailid"]) ? $_POST["emailid"] : ''; ?>" placeholder="E-Mail ID..." size="25" required="required"></td>
                <td>Phone/Mobile:</td><td><input type="text" name="phonemobileno" class="keyup-numeric" value="<?php echo isset($_POST["phonemobileno"]) ? $_POST["phonemobileno"] : ''; ?>" placeholder="Phone/Mobile No..." size="25" ></td>
            </tr>
            <tr style="height:10px;"></tr>
            <tr>
                <td>Mother Tongue:</td><td><input type="text" name="mothertongue"  class="keyup-characters" value="<?php echo isset($_POST["mothertongue"]) ? $_POST["mothertongue"] : ''; ?>" placeholder="Mother Tongue..." size="25" ></td> 
                <td>Nativity:</td><td><input type="text" name="nativity" class="keyup-characters" value="<?php echo isset($_POST["nativity"]) ? $_POST["nativity"] : ''; ?>" placeholder="Nativity..." size="25" ></td>
                
                <td>Address:</td><td colspan="3"><input type="text" name="Address" class="keyup-characters" value="<?php echo isset($_POST["Address"]) ? $_POST["Address"] : ''; ?>" placeholder="Address..." size="25" ></td>
                
            </tr>
            <tr>
            <td colspan="8">   
            <button name="staffprofile" >Submit</button>
            <input type="reset" name="Reset" value="Clear"/>
            </td>
           
            </tr>
        </form>    
        </table>   
        </div>    
        <div><?php $this->load->view('template/footer'); ?></div> 
    </body>  
</html>    
