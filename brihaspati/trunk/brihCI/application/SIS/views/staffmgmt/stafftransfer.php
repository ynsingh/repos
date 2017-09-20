
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
            jQuery(document).ready(function(){
        jQuery('input.date').each(function(){
        jQuery(this).datepicker({dateFormat:'dd/mm/yy', changeMonth: true,    stepMonths: 12, showAnim:"slideDown" });
        jQuery('#ui-datepicker-div .ui-helper-hidden-accessible').css("position", "absolute !important");
        jQuery('#ui-datepicker-div').css('clip', 'auto');
         });
        </script> 
        <script>
            $(document).ready(function(){
                $("#Dateofrelief").datepicker({
                    dateFormat: 'yy/mm/dd',
                    numberOfMonths: 1,
                    autoclose: true,
                    changeMonth: true
                       
                });
                $("#expDateofjoin").datepicker({
                    dateFormat: 'yy/mm/dd',
                    numberOfMonths: 1,
                    autoclose: true,
                    changeMonth: true,
                    
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
                               // alert("seeema-===="+data);
                              	var empdata=data;
				//alert("seema2"+empdata);
                                var empinput=empdata.split(',');
                                //alert("empinput==="+empinput[0]+"===1=="+empinput[1]+"==2==="+empinput[2]+"==3==="+empinput[3]);
                               $('#uocfrom').val(empinput[0].replace(/^"|"$/g, ''));
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
           
               
           });
        </script>    
        
    </head>
    <body>
        <div>
           
            <?php $this->load->view('template/header'); ?>
            <h3>Welcome <?= $this->session->userdata('username') ?></h3>
            <?php $this->load->view('template/menu');?>
        
        </div>
         <table style="margin-left:1%;width:97%;"><tr><td>
        <?php echo anchor('staffmgmt/stafftransferlist/', "View Staff Tranfer List" ,array('title' => 'Staff Transfer List ' , 'class' => 'top_parent'));?>
        </td>
        <td>
        <!--<a href="<?php //echo site_url(); ?>/staffmgmt/mypdf">pdf</a>-->
        </td>

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
            <table style="margin-left:2%; margin-right:2%; width:97%; border:1px solid gray;" border=1 class="TFtable">
                <tr><thead><th  style="background-color:#2a8fcf;text-align:left;" colspan="6">&nbsp;&nbsp;Staff Transfer and Posting Form</th></thead></tr>    
                
                <?php echo form_open_multipart('staffmgmt/stafftransfer');?>    
                    <tr>
                        <td >Registrar Name:</td><td><input type="text" name="registrarname" class="keyup-characters" value="" size="40"  required pattern="[a-zA-Z0-9 ]+" required="required"></td>
                        <td >Designation:</td><td>
                            <select name="designation"> 
                                <option value="">---------------Designation------------</option>
                                <option value="Registrar">Registrar</option>
                                <option value="Registrar Incharge">Registrar Incharge</option>                     
                            </select>
                        </td>
                        <td >University Sanction Order No:</td><td> <input type="text" name="usono" class="keyup-characters" value="" size="30"  required pattern="[0-9 ]+" required="required"></td>
                    </tr>
                    <tr>
                        <td >RC No:</td><td> <input type="text" name="rcno" class="keyup-characters" value="" size="30"  required pattern="[a-zA-Z0-9 ]+" required="required"></td> 
                        <td >Reference No:</td><td> <input type="text" name="referenceno" class="keyup-characters" value="" size="30"  required pattern="[a-zA-Z0-9 ]+" required="required"></td> 
                        <td >Employee Type:</td><td>
                            <select name="employeetype"> 
                                <option value="">------------Employee Type-----------</option>
                                <option value="Teaching">Teaching</option>
                                <option value="NON-Teaching">NON Teaching</option>                     
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td >Subject:</td><td> <textarea name="subject" class="keyup-characters" rows="5" cols="50" required pattern="[a-zA-Z0-9 ]+" required="required">Enter text here...</textarea></td>
                        <td >Order Content:</td><td> <textarea name="ordercontent" class="keyup-characters" rows="5" cols="50" required pattern="[a-zA-Z0-9 ]+" required="required">Enter text here...</textarea></td>   
                        <td>Employee Name:</td><td>
                            <select name="empname" id="empname" class="empdet"> 
                                <option value="">--------Select Employee Name--------</option>
                                <?php foreach($this->usrlist as $usrdata): ?>	
                                    <option value="<?php echo $usrdata->emp_id; ?>"><?php echo $usrdata->emp_name; ?></option> 
                                <?php endforeach; ?>
                                                   
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td >University Officer Control From:</td><td> <input type="text" name="uocfrom" id="uocfrom" readonly class="keyup-characters" size="30"  required pattern="[a-zA-Z0-9 ]+" required="required"></td>
                        <td >University Officer Control To:</td><td colspan="3">
                            <select name="uocontrolto" required> 
                                <option value="">-----Select University Officer Control------</option>
                                
                                <?php foreach($this->uoc as $ucodata): ?>	
                                    <option value="<?php echo $ucodata->id; ?>"><?php echo $ucodata->name; ?></option> 
                                <?php endforeach; ?>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Department From:</td><td>
                            <input type="text" name="deptfrom" id="deptfrom" readonly class="keyup-characters"  size="30"  required pattern="[a-zA-Z0-9 ]+" required="required">
                        </td>
                        <td>Department To:</td><td colspan="3">
                            <select required name="deptto" id="dep"> 
                               <option value="">----------Select Department---------</option>
                       
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Designation From:</td><td>
                            <input type="text" name="desigfrom" id="desigfrom"  class="keyup-characters"  size="30" readonly required pattern="[a-zA-Z0-9 ]+" required="required">
                        </td>
                        <td>Designation To:</td><td colspan="3">
                            <select required name="desigto" > 
                               <option value="">----------Select Designation---------</option>
                                <?php foreach($this->desig as $desigdata): ?>	
                                    <option value="<?php echo $desigdata->desig_id; ?>"><?php echo $desigdata->desig_name; ?></option> 
                                <?php endforeach; ?>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Post From:</td><td>
                            <input type="text" name="postfrom" id="postfrom"  readonly class="keyup-characters" size="30"  required pattern="[a-zA-Z0-9 ]+" required="required">
                        </td>
                        <td>Post To:</td><td colspan="3">
                            <input type="text" name="postto" class="keyup-characters" value="" size="30"  required pattern="[a-zA-Z0-9 ]+" required="required">
                           <!-- <select required name="postto"> 
                              <option value="">-------Select Post-----</option>  
                       
                            </select>-->
                        </td>
                    </tr>
                    <tr>
                        <td >TTA Detail:</td><td> <textarea name="ttadetail" class="keyup-characters" rows="5" cols="50" required pattern="[a-zA-Z0-9 ]+" required="required">Enter text here...</textarea></td>
                        <td>Date of Relief:</label></td>
                        <td><input type="text" name="dateofrelief" id="Dateofrelief"  value="" size="30" />
                        <td>Expected Date of joining:</label></td>
                        <td><input type="text" name="expdoj" id="expDateofjoin"  value="" size="30" />    
                    </tr>
                    <tr>
                        <td >Email Sent To:</td><td colspan="6" style="float:left;"> <textarea name="emailsentto" class="keyup-characters" rows="5" cols="50" required pattern="[a-zA-Z0-9 ]+" required="required">Enter text here...</textarea></td> 
                    </tr>
                    <tr style="background-color:#2a8fcf;text-align:left;" >
                        <td colspan="6">   
                            <button name="stafftransfer" >Submit</button>
                            <input type="reset" name="Reset" value="Clear"/>
                        </td>
           
                    </tr>
                    
                </form>   
            </table>
        </div>    
    </body>    
</html>    
