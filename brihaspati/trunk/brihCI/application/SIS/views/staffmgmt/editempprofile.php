
<!--@filename editemployeeprofile.php  @author Manorama Pal(palseema30@gmail.com) -->

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
        <script>
            $(document).ready(function(){
            $("#StartDate").datepicker({
                dateFormat: 'yy/mm/dd',
                numberOfMonths: 1,
                //autoclose: true
                       
            });
            $("#Dateofphd").datepicker({
                dateFormat: 'yy/mm/dd',
                numberOfMonths: 1,
                //autoclose: true,
               
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
                //autoclose: true,
               
            });
            $("#Dateofhgp").datepicker({
                dateFormat: 'yy/mm/dd',
                numberOfMonths: 1,
                //autoclose: true,
               
            });
            $("#Dateofbirth").datepicker({
                dateFormat: 'yy/mm/dd',
                numberOfMonths: 1,
                //autoclose: true,
               
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
        
        function myFunction() {
            window.print();
        }

        </script>   
    </head>
    <body>
        <div>
           
            <?php $this->load->view('template/header'); ?>
            <h3>Welcome <?= $this->session->userdata('username') ?></h3>
            <?php $this->load->view('template/menu');?>
        
        </div>
        <table style="margin-left:1%;width:97%;"><tr><td>
        <?php echo anchor('staffmgmt/employeelist/', "View Employee List" ,array('title' => 'Employeen List ' , 'class' => 'top_parent'));?>
        </td>
<!--        <td>
        <a href="<?php //echo site_url(); ?>/staffmgmt/mypdf">pdf</a>
        </td>
-->
        </tr>
        </table>
        <div align="left" style="margin-left:2%;width:95%;">
            
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
        <table style="margin-left:2%; margin-right:2%; width:97%; border:1px solid gray;" border=1 class="TFtable">
        <?php //foreach ($editemp_data as $data):  ?>
            
            <?php echo form_open_multipart('staffmgmt/update_profile/'.$editdata->emp_id);?>
           <!--form method="post" action="<?php //echo base_url('staffmgmt/update_profile/',$editdata->emp_id);?>" -->           
            <tr>
                <td>Upload Photo:</td><td colspan="6"><input type='file' name='userfile' size='20' /></td>
                <td style="float:right;"><p><img src="<?php echo base_url('uploads/SIS/empphoto/'.$editdata->emp_code);?>"  alt="" v:shapes="_x0000_i1025" width="78" height="94"></p></td>
               
            </tr>    
           <tr>
                
                <td >Employee PF No:</td><td> <input type="text" name="empcode" class="keyup-characters" value="<?php echo $editdata->emp_code;?>" size="25"  required pattern="[a-zA-Z0-9 ]+" required="required" readonly></td>
                <td >Employee Name: </td><td><input type="text" name="empname"  value="<?php echo $editdata->emp_name ;?>" placeholder="employee name..." size="25" required="required"></td>
                <td>Father Name:</td><td> <input type="text" name="fathername" class="keyup-characters" value="<?php echo $editdata->emp_father; ?>" placeholder="Father Name..." size="25" ></td>
                <td >Specialisation(Major Subject):</td><td>
                    <select name="specialisation"> 
                        <?php if(!empty($editdata->emp_specialisationid)):?>
                        <option  value="<?php echo $editdata->emp_specialisationid;?>"><?php echo $this->commodel->get_listspfic1('subject','sub_name','sub_id',$editdata->emp_specialisationid)->sub_name;?></option>
                        <?php else:?>
                        <option value="0">--------Major Subject-----</option>
                        <?php endif?>
                        <?php foreach($this->subject as $subdata): ?>	
   				<option value="<?php echo $subdata->sub_id; ?>"><?php echo $subdata->sub_name; ?></option> 
 			<?php endforeach; ?>
                       
                    </select>
                </td>
                 
            </tr>
            
            
            <tr style="height:10px;"></tr>
            <tr>
                <td>Campus Name: </td><td width="10%"><select id="camp" name="campus" style="width:100%;" required> 
                        <option value="<?php echo $editdata->emp_scid;?>"><?php echo $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$editdata->emp_scid)->sc_name;?></option>
                       <?php foreach($this->campus as $camdata): ?>	
   				<option class="test" value="<?php echo $camdata->sc_id; ?>"><?php echo $camdata->sc_name; ?></option> 
 			<?php endforeach; ?>
                      
                </select></td> 
                <td>University Officer Control:</td><td>
                    <select name="uocontrol" required> 
                        <option value="<?php echo $editdata->emp_uocid;?>"><?php echo $this->lgnmodel->get_listspfic1('authorities','name','id',$editdata->emp_uocid)->name;?></option>
                       
                        <?php foreach($this->uoc as $ucodata): ?>	
                            <option value="<?php echo $ucodata->id; ?>"><?php echo $ucodata->name; ?></option> 
 			<?php endforeach; ?>
                    </select>
                </td>
                <td>Department:</td><td>
                    <select required name="department" id="scid"> 
                        <option value="<?php echo $editdata->emp_dept_code;?>"><?php echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$editdata->emp_dept_code)->dept_name;?></option>
                       
                    </select>
                </td>
                <td>Scheme Name:</td><td>
                    <select required name="schemecode" id="schmid"> 
                        <option value="<?php echo $editdata->emp_schemeid;?>"><?php echo $this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$editdata->emp_schemeid)->sd_name;?>"</option>
                        
                    </select>
                </td>
                
            </tr>
            <tr style="height:10px;"></tr>
            <tr>
                <td>Designation:</td><td>
                    <select name="designation" required > 
                        <option value="<?php echo $editdata->emp_desig_code;?>"><?php echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$editdata->emp_desig_code)->desig_name;?></option>
                            <?php foreach($this->desig as $desigdata): ?>	
                            <option value="<?php echo $desigdata->desig_id; ?>"><?php echo $desigdata->desig_name; ?></option> 
 			<?php endforeach; ?>
                    </select>
                </td>
                <td >Shown against the Post:</td>
                <td> <input type="text" name="emppost" value="<?php echo $editdata->emp_post; ?>" placeholder="Employee Post..." size="25"></td><br>
                
                <td>Working Type:</td><td>
                        <select name="workingtype">
                         <?php if(!empty($editdata->emp_worktype)):;?>   
                        <option value="<?php echo $editdata->emp_worktype;?>"><?php echo $editdata->emp_worktype;?></option>
                        <?php else:?>
                        <option value="">-------Select Working Type-----</option>
                        <?php endif;?>
                        <option value="Teaching">Teaching</option>
                        <option value="NON-Teaching">NON Teaching</option>
                    </select>
                    </td> 
                <td>Employee Type:</td><td>
                    <select name="emptype">
                        <?php if(!empty($editdata->emp_type_code)):;?>
                        <option value="<?php echo $editdata->emp_type_code;?>"><?php echo $editdata->emp_type_code;?></option>
                        <?php else:?>
                        <option value="">------Select Employee Type--</option>
                        <?php endif?>
                        <option value="Permanent">Permanent</option>
                        <option value="Temporary">Temporary</option>
                    </select>
                </td> 
                
            </tr> 
            <tr style="height:10px;"></tr>
            <tr>
                <td>Gender:</td><td><select name="gender">
                        <?php if(!empty($editdata->emp_gender)):;?>
                        <option value="<?php echo $editdata->emp_gender;?>"><?php echo $editdata->emp_gender;?></option>
                        <?php else:?>
                        <option value="">----SelectGender-----</option>
                        <?php endif?>
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                        <option value="Transgender">Transgender</option>
                    </select></td> 
                    
                <td>Community:</td><td><select name="community">
                        <?php if(!empty($editdata->emp_community)):;?>
                        <option value="<?php echo $editdata->emp_community;?>"><?php echo $editdata->emp_community;?></option>
                        <?php else:?>
                        <option value="">--------Select Community-----</option>
                        <?php endif?>
                        <option value="General">General</option>
                        <option value="Other Backward Class">Other Backward Class</option>
                        <option value="Scheduled Caste">Scheduled Caste</option>
                        <option value="Scheduled Tribe">Scheduled Tribe</option>
                        
                    </select></td> 
                <td>Religion:</td><td><select name="religion">
                        <?php if(!empty($editdata->emp_religion)):;?>
                        <option value="<?php echo $editdata->emp_religion;?>"><?php echo $editdata->emp_religion;?></option>
                        <?php else:?>
                        <option value="">--------Select Religion-----</option>
                        <?php endif?>
                        <option value="Hinduism">Hinduism</option>
                        <option value="Islam">Islam</option>
                        <option value="Sikhism">Sikhism</option>
                        <option value="Christianity">Christianity</option>
                        <option value=" Buddhism">Buddhism</option>
                        <option value="Jainism">Jainism</option>
                    </select></td>
                    <td>Caste:</td><td><input type="text" name="caste" value="<?php echo $editdata->emp_caste; ?>" placeholder="Caste..." size="25" ></td> 
            </tr>
            <tr style="height:10px;"></tr>
            <tr>
            
                <td>Pay Band:</td><td>
                    <select name="payband" required> 
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
                       
                    </select>
                </td>
                <td>Basic Pay:</td><td><input type="text" name="basicpay"  class="keyup-numeric" value="<?php echo $editdata->emp_basic; ?>" placeholder="Basic pay..." size="25" ></td> 
                <td>Emolution:</td><td><input type="text" name="emolution" class="keyup-numeric" value="<?php echo $editdata->emp_emolution; ?>" placeholder="Emolution..." size="25" ></td> 
                <td >NHIS ID No:</td><td> <input type="text" name="empnhisidno" class="keyup-characters" value="<?php echo $editdata->emp_nhisidno; ?>" placeholder="NHIS ID NO..." size="25"></td>
                 
            </tr>
            <tr style="height:10px;"></tr>
            <tr>
                <td>Date of Appointment:</td><td><input type="text" name="dateofjoining" value="<?php echo $editdata->emp_doj; ?>" id="StartDate"  size="25" required="required"></td>       
                <td>Paln / Non Plan:</td><td>
                    <select name="pnp"> 
                        <option value="<?php echo $editdata->emp_pnp;?>"><?php echo $editdata->emp_pnp;?></option>
                        <option value="Plan">Plan</option>
                        <option value="Non-Paln">Non Plan</option>
                    </select>
                </td>
                <td>Phd Status:</td><td>
                    <select name="phdstatus"> 
                        <option value="<?php echo $editdata->emp_phd_status;?>"><?php echo $editdata->emp_phd_status;?></option>
                        <option value="Complete">Complete</option>
                        <option value="Not Complete">Not Complete</option>
                    </select>
                </td>
                <td>Date of Phd Finished:</label></td>
                <td><input type="text" name="dateofphd" id="Dateofphd"  value="<?php echo $editdata->emp_dateofphd; ?>" size="26" />
            </tr>
            <tr style="height:10px;"></tr>
            <tr>
            <td>ASSR Exam:</td><td>
                    <select name="assrexam"> 
                        <option value="<?php echo $editdata->emp_AssrExam_status;?>"><?php echo $editdata->emp_AssrExam_status;?></option>
                        <option value="Passed">Passed</option>
                        <option value="Fail">Fail</option>
                    </select>
                </td>
                <td>Date of ASSR Exam:</td>
                <td><input type="text" name="assrexamdate" id="Dateofassrexam" value="<?php echo $editdata->emp_dateofAssrExam ;?>"class="form-control" size="26" />
                <td>Date of Birth: </td><td><input type="text" name="DateofBirth" value="<?php echo $editdata->emp_dob; ?>" id="Dateofbirth" size="25" required="required"></td>
                 <td>Date of HGP:</label></td>
                <td><input type="text" name="dateofhgp" id="Dateofhgp" value="<?php echo $editdata->emp_dateofHGP ; ?>" class="form-control" size="26" /></td>
            </tr>
            <tr style="height:10px;"></tr>
            <tr>
                <td>Pan No:</td><td><input type="text" name="panno" id="txtPANNumber" MaxLength="10" value="<?php echo $editdata->emp_pan_no;?>" placeholder="Pan No..." size="25" ></td> 
                <?php 
                    $fulldata=$editdata->emp_bank_ifsc_code;
                    $bname=explode(",",$fulldata);  
                    
                ;?>
                <td>Bank Name:</td><td><input type="text" name="bankname" class="keyup-characters" value="<?php echo $bname[0]; ?>" placeholder="Bank Name..." size="25" ></td>
                <td>IFSC Code:</td><td><input type="text" name="ifsccode" class="keyup-characters" value="<?php echo $bname[1]; ?>" placeholder="IFSC CODE..." size="25" ></td>
                <td>Bank ACC No:</td><td><input type="text" name="bankacno" class="keyup-characters" value="<?php echo $editdata->emp_bank_accno; ?>" placeholder="Bank Acc No..." size="25" required="required"></td>
            </tr>
            <tr style="height:10px;"></tr>
            <tr>
                
                <td>Date of Retirement:</label></td>
                <td><input type="text" name="dateofretirement" value="<?php echo $editdata->emp_dor; ?>" id="Dateofretir" class="form-control" size="26" /></td>
                <td>Aadhaar No:</td><td><input type="text" name="Aadharrno" class="keyup-numeric" MaxLength="12" value="<?php echo $editdata->emp_aadhaar_no; ?>" placeholder="Aadharr No..." size="25" required="required"></td>
                <td>E-Mail ID:</td><td><input type="text" name="emailid" class="keyup-email" value="<?php echo $editdata->emp_email; ?>" placeholder="E-Mail ID..." size="25" required="required" readonly></td>
                <td>Phone/Mobile:</td><td><input type="text" name="phonemobileno" class="keyup-numeric" MaxLength="10" value="<?php echo $editdata->emp_phone; ?>" placeholder="Phone/Mobile No..." size="25" ></td>
            </tr>
            <tr style="height:10px;"></tr>
            <tr>
                <td>Mother Tongue:</td><td><input type="text" name="mothertongue"  class="keyup-characters" value="<?php echo $editdata->emp_mothertongue; ?>" placeholder="Mother Tongue..." size="25" ></td> 
                <td>Nativity:</td><td><input type="text" name="nativity" class="keyup-characters" value="<?php echo $editdata->emp_citizen; ?>" placeholder="Nativity..." size="25" ></td>
                
                <td>Address:</td><td colspan="3"><input type="text" name="Address" class="keyup-characters" value="<?php echo $editdata->emp_address; ?>" placeholder="Address..." size="25" ></td>
                
            </tr>
            <tr>
            <td colspan="8">   
            <button name="updateprofile" >Update</button>
            <input type="reset" name="Reset" value="Clear"/>
            </td>
           
            </tr>
            <?php echo form_hidden('id', $editdata->emp_id);?>
           <?php echo form_close();?>
           <?php //endforeach; ?>
          
        </table> 
        </div>    
        <div align="center">  <?php $this->load->view('template/footer');?></div>
    </body>
</html>
