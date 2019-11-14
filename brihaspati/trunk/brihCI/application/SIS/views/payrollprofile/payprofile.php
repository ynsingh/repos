    
    <!--@filename pprofile.php  @author Manorama Pal(palseema30@gmail.com) -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Payroll Profile</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/datepicker/jquery-ui.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-ui.js" ></script>
        <script>
            $(document).ready(function(){
           
		$("#psc").hide();
                $("#psc1").hide();
                $("#psc2").hide();
                $("#psc3").hide();
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
//                           alert("datat==="+data);
                            var empinput=data.split(",");
			    if(empinput.length < 2){
				$('#invalidpf').val(empinput[0].replace(/[[\]"|"]/g,""));
				empinput.length = 0;
			    }else{
				$('#invalidpf').val("");
			    }
//				alert(empinput[0].replace(/[[\]"|"]/g,""));
                            $('#campus').val(empinput[0].replace(/[[\]"|"]/g,""));
                            $('#uo').val(empinput[1].replace(/"|"/g,""));
                            $('#dept').val(empinput[2].replace(/"|"/g,""));
                            $('#schm').val(empinput[3].replace(/"|"/g,""));
                            $('#ddo').val(empinput[4].replace(/"|"/g,""));
				var wrktype=empinput[5].replace(/"|"/g,"");
                            $('#wtype').val(empinput[5].replace(/"|"/g,""));
                            $('#group').val(empinput[6].replace(/"|"/g,""));
			    var dsaf=empinput[7].replace(/"|"/g,"");
			    var dsa=dsaf.split('@');
                          //  $('#desig').val(empinput[7].replace(/"|"/g,""));
                            $('#desig').val(dsa[0]);
			    $('#sadesig').val(dsa[1]);

                            $('#etype').val(empinput[8].replace(/"|"/g,""));
                            $('#doj').val(empinput[9].replace(/"|"/g,""));
                            $('#empname').val(empinput[10].replace(/"|"/g,""));
                            $('#accno').val(empinput[11].replace(/"|"/g,""));
                            $('#addharno').val(empinput[12].replace(/"|"/g,""));
                            $('#dob').val(empinput[13].replace(/"|"/g,""));
                            $('#add').val(empinput[14].replace(/"|"/g,""));
                            $('#contact').val(empinput[15].replace(/"|"/g,""));
                            $('#dor').val(empinput[16].replace(/"|"/g,""));
                            //$('#pscale').val(empinput[17].replace(/[[\]"|"]/g,""));
			    var cps=empinput[17].replace(/[[\]"|"]/g,"").split('(');	

                            $('#pscale').val(cps[0]);
			    var psgp=cps[1].split(')');	
			    $('#pscale2').val(psgp[0]);
			    var glev=psgp[1].split('-');
                            $('#pscale3').val(glev[0]);	
                            $('#pscale1').val(glev[1]);	

                            $('#bname').val(empinput[18].replace(/[[\]"|"]/g,""));
                            $('#ifsccode').val(empinput[19].replace(/[[\]"|"]/g,""));
                            $('#branch').val(empinput[20].replace(/[[\]"|"]/g,""));
                      //      $('#pcomm').val(empinput[21].replace(/[[\]"|"]/g,""));
                            var ppnh=(empinput[21].replace(/[[\]"|"]/g,"")).split('@');
				$('#pcomm').val(ppnh[0]);
				$('#panno').val(ppnh[1]);
				$('#nhisno').val(ppnh[2]);
 
                            var pcon=empinput[22].replace(/[[\]"|"]/g,"");
                            if(pcon ==='yes'){
                                $('#pcontri').prop("checked", true);
                               // $('#pcontri1').is(':checked');
                            } 
                            //$('#pcontri').val(empinput[21].replace(/[[\]"|"]/g,""));
                            //$('#upfno').val(empinput[23].replace(/[[\]"|"]/g,""));
                            var pfno=empinput[23].replace(/[[\]"|"]/g,"");
//	alert(pfno);
				if((pfno.indexOf("V") === 0)||(pfno.indexOf("v") === 0)){
					$('#upfno').val(pfno.toUpperCase());
					$('#cpsno').val('');
				}
				else if((pfno.indexOf("C") === 0)||(pfno.indexOf("c") === 0)){
					$('#cpsno').val(pfno.toUpperCase());
					$('#upfno').val('');
				}
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

   	     		    $('#empid').val(empinput[49].replace(/[[\]"|"]/g,""));                            

   	     		    $('#rfqemp').val(empinput[50].replace(/[[\]"|"]/g,""));                            
   	     		    $('#exhra').val(empinput[51].replace(/[[\]"|"]/g,""));                            
   	     		    $('#qoemp').val(empinput[52].replace(/[[\]"|"]/g,""));                            
   	     		    $('#rentgrade').val(empinput[53].replace(/[[\]"|"]/g,""));                            
   	     		    $('#spfcgsno').val(empinput[54].replace(/[[\]"|"]/g,""));                            
   	     		    $('#spfcgs2no').val(empinput[55].replace(/[[\]"|"]/g,""));                            
   	     		    $('#fsfno').val(empinput[56].replace(/[[\]"|"]/g,""));                            
//$ems_nhisamt,$ems_bbemail,$ems_bbphone,$ems_bbadd,$ems_acctype,$ems_bbmicr,$ems_socamt,$ems_pli2amt, $ems_pli1amt,$ems_prd3amt,$ems_prd2amt,$ems_prd1amt, $fsfamt,$spfcgs2amt,$spfcgsamt
   	     		    $('#nhisamount').val(empinput[57].replace(/[[\]"|"]/g,""));                            
   	     		    $('#bbemail').val(empinput[58].replace(/[[\]"|"]/g,""));                            
   	     		    $('#bbphone').val(empinput[59].replace(/[[\]"|"]/g,""));                            
   	     		    $('#bbadd').val(empinput[60].replace(/[[\]"|"]/g,""));                            
   	     		    $('#acctype').val(empinput[61].replace(/[[\]"|"]/g,""));                            
   	     		    $('#micrcode').val(empinput[62].replace(/[[\]"|"]/g,""));                            
   	     		    $('#socamount').val(empinput[63].replace(/[[\]"|"]/g,""));                            
   	     		    $('#pli2amount').val(empinput[64].replace(/[[\]"|"]/g,""));                            
   	     		    $('#pli1amount').val(empinput[65].replace(/[[\]"|"]/g,""));                            
   	     		    $('#prd3amount').val(empinput[66].replace(/[[\]"|"]/g,""));                            
   	     		    $('#prd2amount').val(empinput[67].replace(/[[\]"|"]/g,""));                            
   	     		    $('#prd1amount').val(empinput[68].replace(/[[\]"|"]/g,""));                            
   	     		    $('#fsfamount').val(empinput[69].replace(/[[\]"|"]/g,""));                            
   	     		    $('#spfcgs2amount').val(empinput[70].replace(/[[\]"|"]/g,""));                            
   	     		    $('#spfcgsamount').val(empinput[71].replace(/[[\]"|"]/g,""));                            
		//	}
                        },
                        error:function(data){
                            alert("error occur..!!"+data);
                 
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
                            url: "<?php echo base_url();?>sisindex.php/jslist/getsocietyno",
                            type: "POST",
                            data: {"society" : soc},
                            dataType:"html",
                            success:function(data){
                            //    alert("data==="+data);
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
           	$('#pscale1').on('change',function(){
			var wty=$('#wtype').val();
			var pc= $('#pcomm').val();
			var levl= $('#pscale1').val();
			var val = wty+","+pc+","+levl;
		//	alert(val);
			if(levl == ''){
                        	$('#pscale2').prop('disabled',true);
                    	}else{
                        $('#pscale2').prop('disabled',false);
                        $.ajax({
                            url: "<?php echo base_url();?>sisindex.php/jslist/getpayscleagp",
                            type: "POST",
                            data: {"wtpcl" : val},
                            dataType:"html",
                            success:function(data){
                  //              alert("data==="+data);
				var sginput=data.split(",");
//                              alert(sginput[0].replace(/[[\]"|"]/g,""));
                            $('#pscale2').val(sginput[0].replace(/[[\]"|"]/g,""));
                            $('#pscale3').val(sginput[1].replace(/[[\]"|"]/g,""));
                            },
                            error:function(data){
                            //alert("data in error==="+data);
                                alert("error occur..!!");
                            }
                        });
                    }
		}); 
		
        
                 $('#pcomm').on('change',function(){
                        var pc= $('#pcomm').val();
                        if(pc == '6th'){
                                $("#psc").show();
                                $("#psc1").show();
                                $("#psc2").show();
                                $("#psc3").show();
                        }
                        else{
                                $("#psc").hide();
                                $("#psc1").show();
                                $("#psc2").show();
                                $("#psc3").hide();
                        }
                  });



            });
            
		function levelofpay(val){
                         var wt= $('#wtype').val();
//                      alert(wt);
//                       var val=val;
                         $.ajax({
                                type: "POST",
                                url: "<?php echo base_url();?>sisindex.php/jslist/getlevelpay",
                                data: {"wt" : wt},
                                dataType:"html",
                                success: function(data){
//              alert(data);
                                        $('#pscale1').html(data.replace(/^"|"$/g, ''));
                                }
                        });
                }

                function payband(val){
			levelofpay(val);
                        var pc= $('#pcomm').val();
                        var wt=$('#wtype').val();
                        var val = wt+","+pc;
//      alert(val);
                        $.ajax({
                                type: "POST",
                                url: "<?php echo base_url();?>sisindex.php/jslist/getpayband",
                                data: {"pcwt" : val},
                                dataType:"html",
                                success: function(data){
  //            alert(data);
                                        $('#pscale').html(data.replace(/^"|"$/g, ''));
                                }
                        });
                }
   
                   
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
		    <td>
				<input type="text" name="invalidpf" id="invalidpf" value="" style="text-decoration:none;border:0; font-size:20px;font-weight:bold;color:red; word-break: break-all;width:400px;"   readonly>
		    </td>
                </tr>
                </table>
            <?php echo form_open_multipart('payrollprofile/payprofile','id="my_id"');?>
                <table  width="100%" class="TFtable" >
                        <tr><td colspan="5"><b><span style="color:#0099CC;">Personal/Work Details</span></b></td></tr>
                        <tr>
                            <td> <b>Working Type:</b><br><input type="text" id="wtype" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                            <td> <b>Employee Name:</b><br><input type="text" id="empname" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                            <td> <b>Campus Name:</b><br><input type="text"  id="campus" value=""  style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                            <td> <b>UO:</b><br><input type="text"  id="uo" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                        </tr>
                        <tr>
                            <td> <b>Department:</b><br><input type="text" id="dept" value=""style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                            <td> <b>Scheme:</b><br><input type="text" id="schm" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                            <td> <b>DDO:</b><br><input type="text"  id="ddo" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                            <td> <b>Group :</b><br><input type="text"  id="group" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                        </tr>
                        <tr>
                            <td> <b>Designation:</b><br><input type="text" id="desig" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                            <td> <b>Shown Against the Post:</b><br><input type="text" id="sadesig" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
			    <td> <b>Employee type:</b><br><input type="text" id="etype" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                            <td> <b>E Aadhar No:</b><br><input type="text" id="addharno" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                        </tr>
                        <tr>
                            <td> <b>Date of joining :</b><br><input type="text"  id="doj" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                            <td> <b>Date of Retirement:</b><br><input type="text" id="dor" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                            <td> <b>Date of Birth:</b><br><input type="text" id="dob" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                            <td> <b>Contact No:</b><br><input type="text"  id="contact" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                        </tr>
                        <tr>
                            <td colspan=2> <b>Address:</b><br><input type="text" id="add" value="" style="text-decoration:none;border:0; word-break: break-all;width:600px;" readonly></td>
                            <td> <b>UPF No :</b><br><input type="text" name="upfno" id="upfno" value="" style="width:300px;" readonly></td>
                            <td colspan="1" style="width:300px;"> <b>CPS No:</b><br>
				<input type="text" name="cpsno" id="cpsno" value="" style="width:300px;" readonly>
                            </td>
                        </tr>
			<tr>
				 <td style="width:300px;"> <b>Deduct UPF:</b><br>
                                <input type="radio" name="dedupf" id="dedupf" value="yes">Yes &nbsp;&nbsp;&nbsp;
                                <input type="radio" name="dedupf" id="dedupf"  checked value="no">No
                            </td>
                            <td colspan="1" style="width:300px;"> <b>Deduct CPS:</b><br>
                                <input type="radio" name="pcontri" id="pcontri" value="yes">Yes &nbsp;&nbsp;&nbsp;
                                <input type="radio" name="pcontri" id="pcontri" checked value="no">No
                            </td>
				<td style="width:300px;"> <b>Washing Allowance:</b><br>
                                <input type="radio" name="washallw" id="washallw"  value="yes">Yes &nbsp;&nbsp;&nbsp;
                                <input type="radio" name="washallw"  id="washallw" checked value="no">No
                            </td>

			</tr>
                        <!--<tr><td colspan="5"><b> <hr/> <span style="color:#0099CC;">Work Details</span></b></td></tr> -->
                        <tr><td colspan="5"><b> <hr/> <span style="color:#0099CC;">Employee Scale of Pay</span></b></td></tr> 
                        
                        <tr>
                            <td> <b>Pay Commision:</b><br>
				<div>
					<select name="pcomm" id="pcomm" style="width:300px;" onchange="payband(this.value)">
        		                        <option value="null" >------Select--------</option>
	                        	        <option value="6th">6th</option>
                                		<option value="7th">7th</option>
	                            	</select>
				</div>
			<!--<input type="text" id="pcomm" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly>-->
			</td>
                            <td id="psc"> <b>Pay Band:</b><br>
				<div>
	                        <select name="pscale" id="pscale" style="width:300px;" >
        	                <option disabled selected >------Select----------------</option>
                	        </select>
                        	</div>

		<!--		<input type="text" id="pscale" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly>-->
				</td>
                            <td id="psc1"> <b>Academic Level of Pay:</b><br>
				<div>
                                <select name="pscale1" id="pscale1" style="width:300px;" >
                                <option disabled selected >------Select----------------</option>
                                </select>
                                </div>

<!--				<input type="text" id="pscale1" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly>-->
				</td>
                            <td id="psc2"> <b>Scale of Pay:</b><br><input type="text" id="pscale2" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly ></td>
                        </tr> 
                        <tr>
                            <td colspan=4 id="psc3"> <b>Academic Grade Pay :</b><br><input type="text" id="pscale3" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                      <!--      <td> <b>Pay Scale:</b><br><input type="text" id="pscale" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td> -->
                        </tr> 
                        <tr><td colspan="5"><b> <hr/> <span style="color:#0099CC;">Bank Details</span></b></td></tr> 
                        <tr>
                            <td> <b>Bank ACC No:</b><br><input type="text" name="accno" id="accno" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;"></td>
                            <td> <b>Bank Name:</b><br><input type="text" name="bname" id="bname" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;"></td>
                            <td> <b>Bank Branch:</b><br><input type="text" name="bbranch" id="branch" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;"></td>
                            <td> <b>IFSC Code:</b><br><input type="text" name="ifsccode" id="ifsccode" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;"></td>
			</tr><tr>
                            <td> <b>MICR Code:</b><br><input type="text" name="micrcode" id="micrcode" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;"></td>
                            <td> <b>Account Type:</b><br>
				 <div>
                                        <select name="acctype" id="acctype" style="width:300px;" onchange="payband(this.value)">
                                                <option value="null" >------Select--------</option>
                                                <option value="Saving">Saving</option>
                                                <option value="Current">Current</option>
                                        </select>
                                </div>
			<!--	<input type="text" name="acctype" id="acctype" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;">-->
				</td>
                            <td> <b>Branch Address:</b><br><input type="text" name="bbadd" id="bbadd" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;"></td>
                            <td> <b>Branch Phone No.:</b><br><input type="text" name="bbphone" id="bbphone" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;"></td>
			</tr><tr>
                            <td> <b>Branch Email:</b><br><input type="text" name="bbemail" id="bbemail" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;"></td>
                        </tr> 
                         
                        <tr><td colspan="5"><b> <hr/> <span style="color:#0099CC;">HRA | CCA | Rent Grade</span></b></td></tr> 
                        <tr>
                            <td> <b>HRA Grade:</b><br>
                            <div><select name="hragrade" id="hragrade" style="width:300px;"> 
                                    <option value="" >------------ HRA GRADE ---------</option>
                                    <?php foreach($this->hglist as $hgdata): ?>	
                                    <option value="<?php echo $hgdata->hgc_id; ?>"><?php echo $hgdata->hgc_gradename;?>
                                    </option> 
                                    <?php endforeach; ?>
                            </select></div>
                            </td>
                            <td> <b>CCA Grade :</b><br>
                                <div><select name="ccagrade" id="ccagrade" style="width:300px;"> 
                                    <option value="">------------ CCA GRADE ---------</option>
                                    <?php foreach($this->ccalist as $hgdata): ?>	
                                    <option value="<?php echo $hgdata->cgc_id; ?>"><?php echo $hgdata->cgc_gradename;?>
                                    </option> 
                                    <?php endforeach; ?>
                                </select></div>
                            </td>
                            <td style="width:300px;"><b> Eligible for Rent Free Quarters:</b><br>
                                <input type="radio" name="rfqemp" id="rfqemp" value="yes">Yes &nbsp;&nbsp;&nbsp;
                                <input type="radio" name="rfqemp" id="rfqemp" checked  value="no">No
                            </td>
                            <td> <b> Rent Free HRA Grade :</b><br>
				 <div><select name="exhra" id="exhra" style="width:300px;">
                                    <option value="" >------------ Rent Free HRA GRADE ---------</option>
                                    <?php foreach($this->hglist as $hgdata): ?>
                                    <option value="<?php echo $hgdata->hgc_id; ?>"><?php echo $hgdata->hgc_gradename;?>
                                    </option>
                                    <?php endforeach; ?>
                            </select></div>
<!--					<input type="text" name="exhra" id="exhra" value="" style="width:300px;">-->
				</td>
                        </tr>
                        <tr>
                            <td style="width:300px;"><b> Quarters Occupied:</b><br>
                                <input type="radio" name="qoemp" id="qoemp" value="yes">Yes &nbsp;&nbsp;&nbsp;
                                <input type="radio" name="qoemp" id="qoemp" checked  value="no">No
                            </td>
                            <td colspan=1> <b>Rent Grade:</b><br>
                            <div><select name="rentgrade" id="rentgrade" style="width:300px;"> 
                                    <option value="null" >------------ RENT GRADE ---------</option>
                                    <?php foreach($this->hglist as $hgdata): ?>	
                                    <option value="<?php echo $hgdata->hgc_id; ?>"><?php echo $hgdata->hgc_gradename;?>
                                    </option> 
                                    <?php endforeach; ?>
                            </select></div>
                            </td>
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
<!--
                        <tr>
                            <td style="width:300px;"><b> University Employee:</b><br>
                                <input type="radio" name="uniemp" id="uniemp" value="yes">Yes &nbsp;&nbsp;&nbsp;
                                <input type="radio" name="uniemp" id="uniemp" checked  value="no">No
                            </td>
                            <td colspan="1" style="width:300px;"> <b>Include in Summary:</b><br>
                                <input type="radio" name="incsumm" id="incsumm" value="yes">Yes &nbsp;&nbsp;&nbsp;
                                <input type="radio" name="incsumm" id="incsumm" checked  value="no">No
                            </td>
                        </tr> 
-->
                        <tr><td colspan="5"><b> <hr/> <span style="color:#0099CC;">PAN | LIC | PRD | ETC</span></b></td></tr> 
                        <!--<tr><td colspan="5"><b>  <hr/><span style="color:#0099CC;">LIC/PRD/PLI/Society Entry</span></b></td></tr> -->
                        <tr>
                            <td><b> FSF No:</b><br><input type="text" name="fsfno" id="fsfno" value="" style="width:300px;"></td>
                            <td><b>Amount:</b><br><input type="text" name="fsfamount" id="fsfamount" value="" style="width:300px;"></td>
                            <!--<td style="width:300px;"> <b>Washing Allowance:</b><br>
                                <input type="radio" name="washallw" id="washallw"  value="yes">Yes &nbsp;&nbsp;&nbsp;
                                <input type="radio" name="washallw"  id="washallw" checked value="no">No
                            </td>-->
                            <td><b> NHIS No:</b><br> <input type="text" name="nhisno" id="nhisno" value="" style="width:300px;"></td>
                            <td><b>Amount:</b><br><input type="text" name="nhisamount" id="nhisamount" value="" style="width:300px;"></td>
                        </tr>
                        <tr>
                            <td><b> SPF CGS No:</b><br><input type="text" name="spfcgsno" id="spfcgsno" value="" style="width:300px;"></td>
                            <td><b>Amount:</b><br><input type="text" name="spfcgsamount" id="spfcgsamount" value="" style="width:300px;"></td>
                            <td><b> SPF CGS 2000 No:</b><br><input type="text" name="spfcgs2no" id="spfcgs2no" value="" style="width:300px;"></td>
                            <td><b>Amount:</b><br><input type="text" name="spfcgs2amount" id="spfcgs2amount" value="" style="width:300px;"></td>
                        </tr>
                        <tr>
                            <td><b> LIC1 No:</b><br><input type="text" name="lic1no" id="lic1no" value="" style="width:300px;"></td>
                            <td><b>Amount:</b><br><input type="text" name="lic1amount" id="lic1amount" value="" style="width:300px;"></td>
                            <td><b> PRD1 No :</b><br><input type="text" name="prdno1" id="prdno1" value="" style="width:300px;"></td>
                            <td><b>Amount:</b><br><input type="text" name="prd1amount" id="prd1amount" value="" style="width:300px;"></td>
                            
                        </tr>
                        <tr>
                            <td><b> LIC2 No:</b><br><input type="text" name="lic2no" id="lic2no" value="" style="width:300px;"></td>
                            <td><b>Amount:</b><br><input type="text" name="lic2amount" id="lic2amount" value="" style="width:300px;"></td>
                            <td><b> PRD2 No :</b><br><input type="text" name="prdno2" id="prdno2" value="" style="width:300px;"></td>
                            <td><b>Amount:</b><br><input type="text" name="prd2amount" id="prd2amount" value="" style="width:300px;"></td>
                        </tr>
                        <tr>
                            <td><b> LIC3 No:</b><br><input type="text" name="lic3no" id="lic3no" value="" style="width:300px;"></td>
                            <td><b>Amount:</b><br><input type="text" name="lic3amount" id="lic3amount" value="" style="width:300px;"></td>
                            <td colspan="1"><b> PRD3 No :</b><br><input type="text" name="prdno3" id="prdno3" value="" style="width:300px;"></td>
                            <td><b>Amount:</b><br><input type="text" name="prd3amount" id="prd3amount" value="" style="width:300px;"></td>
                          
                        </tr>
                        <tr>
                            <td ><b> LIC4 No:</b><br><input type="text" name="lic4no" id="lic4no" value="" style="width:300px;"></td>
                            <td ><b>Amount:</b><br><input type="text" name="lic4amount" id="lic4amount" value="" style="width:300px;"></td>
                            <td colspan="1"><b> PLI1 No :</b><br><input type="text" name="plino1" id="plino1" value="" style="width:300px;"></td>
                            <td><b>Amount:</b><br><input type="text" name="pli1amount" id="pli1amount" value="" style="width:300px;"></td>
                          
                        </tr>
                        <tr>
                            <td><b> LIC5 No:</b><br><input type="text" name="lic5no" id="lic5no" value="" style="width:300px;"></td>
                            <td><b>Amount:</b><br><input type="text" name="lic5amount" id="lic5amount" value="" style="width:300px;"></td>
                            <td  colspan="1"><b> PLI2 No :</b><br><input type="text" name="plino2" id="plino2" value="" style="width:300px;"></td>
                            <td><b>Amount:</b><br><input type="text" name="pli2amount" id="pli2amount" value="" style="width:300px;"></td>
                        </tr>
                        <tr>
                            <td><b> PAN No:</b><br><input type="text" name="panno" id="panno" value="" style="width:300px;"></td>
                            <td><b> Society:</b><br>
                                <div><select name="society" id="society" style="width:300px;"> 
                                    <option value="">------------ Society ---------</option>
                                    <?php foreach($this->society as $socdata): ?>	
                                    <option value="<?php echo $socdata->soc_id; ?>"><?php echo $socdata->soc_sname."(".$this->sismodel->get_listspfic1('society_master_list','soc_scode','soc_id',$socdata->soc_id)->soc_scode.")";?>
                                    </option> 
                                    <?php endforeach; ?>
                                </select></div>
                                </td>
                            <td><b> Society No:</b><br> <input type="text" name="societymember" id="socmem" value="" style="width:300px;"></td>
                            <td><b>Amount:</b><br><input type="text" name="socamount" id="socamount" value="" style="width:300px;"></td>
                       <!--     <td style="width:300px;"> <b>Deduct UPF:</b><br>
                                <input type="radio" name="dedupf" id="dedupf" value="yes">Yes &nbsp;&nbsp;&nbsp;
                                <input type="radio" name="dedupf" id="dedupf"  checked value="no">No
                            </td>-->
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
