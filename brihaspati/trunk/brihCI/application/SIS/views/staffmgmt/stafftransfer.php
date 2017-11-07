    
<!--@filename stafftransferposting.php  @author Manorama Pal(palseema30@gmail.com) -->

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
                $("#Dateofrelief").datepicker({
                    dateFormat: 'yy/mm/dd',
                    numberOfMonths: 1,
                    autoclose: true,
                    changeMonth: true,
                    changeYear: true,
                    yearRange: 'c-70:c+30'
                       
                });
                $("#expDateofjoin").datepicker({
                    dateFormat: 'yy/mm/dd',
                    numberOfMonths: 1,
                    autoclose: true,
                    changeMonth: true,
                    changeYear: true,
                    yearRange: 'c-70:c+30'
                    
                });
                
                /**********************************Start of empdetail on selection of employee ename script*********************************/
                
                $("#empname").on('change',function(){
                    var empid = $(this).val();
                    //alert(empid);
                    if(empid == ''){
                        $('#uocfrom').prop('disabled',true);
                        $('#deptfrom').prop('disabled',true);
                        $('#desigfrom').prop('disabled',true);
                        $('#postfrom').prop('disabled',true);
                        $('#dep').prop('disabled',true);
                    }
                    else{
                        $('#dep').prop('disabled',false);
                       //  alert(empid);
                        $.ajax({
                            url: "<?php echo base_url();?>sisindex.php/staffmgmt/getempdetail",
                            type: "POST",
                            data: {"employee" : empid},
                            dataType:"html",
                            success:function(data){
                               	var empdata=data;
				var empinput=empdata.split(',');
                                var val1 = empinput[0].replace(/\"/g,"");
                               //alert("empinput==="+empinput[0]+"===1=="+empinput[1]+"==2==="+empinput[2]+"==3==="+empinput[3]);
                               $('#uocfrom').val(val1.replace(/^"|"$/g, ''));
                               $('#deptfrom').val(empinput[1].replace(/^"|"$/g, ''));
                               $('#desigfrom').val(empinput[2].replace(/^"|"$/g, ''));
                               $('#postfrom').val(empinput[3].replace(/^"|"$/g, ''));
                               $('#dep').html(empinput[4].replace(/^"|"$/g, ''));
                                
                            },
                            error:function(data){
                                alert("error occur..!!");
                 
                            }
                        });
                                               
                    }//else
               
                }); //method empname
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
            <h3>Welcome <?= $this->session->userdata('username') ?></h3>
            <?php $this->load->view('template/menu');?>
        
        </div>
         <table style="margin-left:6.5%;width:85%;"><tr><td>
        <?php echo anchor('staffmgmt/stafftransferlist/', "View Staff Transfer List" ,array('title' => 'Staff Transfer List ' , 'class' => 'top_parent'));?>
        </td>
        <td>
        <!--<a href="<?php //echo site_url(); ?>/staffmgmt/mypdf">pdf</a>-->
        </td>

        </tr>
        </table>
        <div align="left" style="margin-left:6.5%;width:85%;">
            
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
            <table style="width:85%; border:3px solid gray;" align="center" class="TFtable">
                <tr><thead><th  style="background-color:#2a8fcf;text-align:left;height:40px;" colspan="3">&nbsp;&nbsp;Staff Transfer and Posting Form</th></thead></tr>    
                
                <?php echo form_open_multipart('staffmgmt/stafftransfer');?>    
                    <tr>
                        <td><label for="registrarname" style="font-size:15px;">Registrar Name<font color='Red'>*</font></label>
                           <div><input type="text" name="registrarname" class="keyup-characters" value="<?php echo isset($_POST["registrarname"]) ? $_POST["registrarname"] : ''; ?>" size="40"  required pattern="[a-zA-Z0-9 ]+" required="required">
                           </div>    
                        </td>
                        <td><label for="designation" style="font-size:15px;">Designation<font color='Red'>*</font></label>
                            <div><select name="designation" required="required"> 
                                <option value="">------------------ Select Designation --------------------</option>
                                <option value="Registrar">Registrar</option>
                                <option value="Registrar Incharge">Registrar Incharge</option>                     
                            </select></div>
                        </td>
                        <td><label for="usono" style="font-size:15px;">University Sanction Order No<font color='Red'>*</font></label>
                            <div><input type="text" name="usono" class="keyup-characters" value="<?php echo isset($_POST["usono"]) ? $_POST["usono"] : ''; ?>" size="40"  required pattern="[0-9 ]+" required="required">
                            </div>    
                        </td>
                    </tr>
                    <tr>
                        <td ><label for="rcno" style="font-size:15px;">RC No<font color='Red'>*</font></label>
                            <div><input type="text" name="rcno" class="keyup-characters" value="<?php echo isset($_POST["rcno"]) ? $_POST["rcno"] : ''; ?>" size="40"  required pattern="[a-zA-Z0-9 ]+" required="required"></div>
                        </td> 
                        <td><label for="referenceno" style="font-size:15px;">Reference No<font color='Red'>*</font></label>
                            <div><input type="text" name="referenceno" class="keyup-characters" value="<?php echo isset($_POST["referenceno"]) ? $_POST["referenceno"] : ''; ?>" size="40"  required pattern="[a-zA-Z0-9 ]+" required="required"></div>
                        </td> 
                        <td><label for="employeetype" style="font-size:15px;">Employee Type<font color='Red'>*</font></label>
                            <div><select name="employeetype" required="required"> 
                                <option value="">-------------------------- Employee Type ---------------</option>
                                <option value="Teaching">Teaching</option>
                                <option value="NON-Teaching">NON Teaching</option>                     
                            </select></div>
                        </td>
                    </tr>
                    <tr>
                        <td><label for="subject" style="font-size:15px;">Subject<font color='Red'>*</font></label>
                            <div><textarea name="subject" class="keyup-characters" rows="5" cols="50" required pattern="[a-zA-Z0-9 ]+" required="required" placeholder="Enter text here...."></textarea><div>
                        </td>
                        <td><label for="ordercontent" style="font-size:15px;">Order Content<font color='Red'>*</font></label>
                            <div><textarea name="ordercontent" class="keyup-characters" rows="5" cols="50" required pattern="[a-zA-Z0-9 ]+" required="required" placeholder="Enter text here...."></textarea>
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
                        <td><label for="dateofrelief" style="font-size:15px;">Date of Relief<font color='Red'>*</font></label>
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
                        <td><label for="expdoj" style="font-size:15px;">Expected Date of joining<font color='Red'>*</font></label>
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
                           <!-- <select required name="postto"> 
                              <option value="">-------Select Post-----</option>  
                       
                            </select>-->
                        </td>
                    </tr>
                    <tr>
                        <td><label for="ttadetail" style="font-size:15px;">TTA Detail<font color='Red'>*</font></label>
                            <div><textarea name="ttadetail" class="keyup-characters" rows="5" cols="50" required pattern="[a-zA-Z0-9 ]+" required="required" placeholder="Enter text here...."></textarea></div>
                            </td>
                        
                        <td colspan="2"><label for="emailsentto" style="font-size:15px;">Email Sent To</label>
                        <div><textarea name="emailsentto" rows="5" cols="50" required pattern="[a-zA-Z0-9 ]+" required="required" placeholder="Enter text here...."></textarea></div>
                        </td> 
                    </tr>
                    <tr style="background-color:#2a8fcf;text-align:left;height:40px;">
                        <td colspan="6">   
                            <button name="stafftransfer" >Submit</button>
                            <input type="reset" name="Reset" value="Clear"/>
                        </td>
           
                    </tr>
                    
                </form>   
            </table>
        </div> 
        <div><?php $this->load->view('template/footer'); ?></div> 
    </body>    
</html>    
