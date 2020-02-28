    
    <!--@filename pprofile.php  @author Manorama Pal(palseema30@gmail.com) -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Welcome to Employee Pay Profile</title>
        
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/datepicker/jquery-ui.css">
 <!--      <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>-->
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-ui.js" ></script>
        <style>
        
            /*----- Tabs -----*/
            .tabs {
                width:100%;
                display:inline-block;
            }

            /*----- Tab Links -----*/
            /* Clearfix */
            .tab-links:after {
                display:block;
                clear:both;
                content:'';
                //width:100%;
            }

            .tab-links li {
            //    margin-left:0px 0px;
           //     float:left;
                list-style:none;
            }

            .tab-links a {
              //  padding:13px 20px;
              //  display:inline-block;
                border-radius:3px 3px 0px 0px;
                //background-color:grey;
              //  background:#7FB5DA;
                //font-size:15px;
               // font-weight:600;
                //color:white;
                //text-decoration: none;
               // transition:all linear 0.15s;
               //transition: 0.3s;
               background-color: #555;
                color: white;
                float: left;
                border: none;
                outline: none;
                cursor: pointer;
                padding: 14px 16px;
                font-size: 15px;
                text-decoration:none;
                transition: 0.3s; 
                display:inline-block;
               
            }

            .tab-links a:hover {
                background: lightseagreen;
                text-decoration:none;
            }

            li.active a, li.active a:hover {
                background:#fff;
                color:#4c4c4c;
            }

            /*----- Content of Tabs -----*/
            .tab-content {
               // padding:15px;
                border-radius:3px;
              //  box-shadow:-1px 1px 1px rgba(0,0,0,0.15);
              // background:#fff;
            //   background:#a7cce5;
               // background:lightgray;
            }

            .tab {
                display:none;
            }

            .tab.active {
                display:block;
            }
            
            
            .input-wrapper {
                width: 300px;
            }

            .item-list {
                display: none;
            }
            
        </style>
        
        <script>
            
            $(document).ready(function(){
           
		$("#psc").hide();
                $("#psc1").hide();
                $("#psc2").hide();
                $("#psc3").hide();
		$(".6thdisp").hide();
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
   //                        alert("datat==="+data);
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
                         //   var wrktype=empinput[5].replace(/"|"/g,"");
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

                            $('#pscale01').val(cps[0]);
                            $('#pscale').val(cps[0]);
			    var psgp=cps[1].split(')');	
			    $('#pscale21').val(psgp[0]);
			    $('#pscale2').val(psgp[0]);
			    var glev=psgp[1].split('-');
                            $('#pscale31').val(glev[0]);	
                            $('#pscale3').val(glev[0]);	
                            $('#pscale11').val(glev[1]);	
                            $('#pscale1').val(glev[1]);	

                            $('#bname').val(empinput[18].replace(/[[\]"|"]/g,""));
                            $('#ifsccode').val(empinput[19].replace(/[[\]"|"]/g,""));
                            $('#branch').val(empinput[20].replace(/[[\]"|"]/g,""));
                      //      $('#pcomm').val(empinput[21].replace(/[[\]"|"]/g,""));
                            var ppnh=(empinput[21].replace(/[[\]"|"]/g,"")).split('@');
				$('#pcomm').val(ppnh[0]);
				if(pcomm === '6th'){
					 $(".6thdisp").show();
				}else{
					 $(".6thdisp").hide();
				}
				$('#pcomm01').val(ppnh[0]);
				$('#panno').val(ppnh[1]);
				$('#nhisno').val(ppnh[2]);
 
                            var pcon=empinput[22].replace(/[[\]"|"]/g,"");
                            if(pcon ==='yes'){
                                $('#pcontri').prop("checked", true);
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
                            
                            var rfqemp=empinput[50].replace(/[[\]"|"]/g,"");
   	     		   // $('#rfqemp').val(empinput[50].replace(/[[\]"|"]/g,""));  
                            if(rfqemp ==='yes'){
                                $('#rfqemp').prop("checked", true);
                            } 
   	     		    $('#exhra').val(empinput[51].replace(/[[\]"|"]/g,""));
                            
   	     		    var qoemp=empinput[52].replace(/[[\]"|"]/g,""); 
                            if(qoemp ==='yes'){
                                $('#qoemp').prop("checked", true);
                            } 
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
                           // alert("seema==="+empinput[63]+"acctype==="+empinput[61]);
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
                    //	alert("seema----levv==="+val);
			if(levl == ''){
                        	$('#pscale2').prop('disabled',true);
                    	}else{
                            $('#pscale2').prop('disabled',false);
                        }
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
                    //}
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
                 
                jQuery(document).ready(function() {
                    jQuery('.tabs .tab-links a').on('click', function(e) {
                        var currentAttrValue = jQuery(this).attr('href');
                      //  alert("seema"+currentAttrValue);
                    
                        // Show/Hide Tabs
                        jQuery('.tabs ' + currentAttrValue).show().siblings().hide();

                        // Change/remove current tab to active
                        jQuery(this).parent('li').addClass('active').siblings().removeClass('active');

                        e.preventDefault();
                        
                    });
                });
                
                
                $('form,#ppearnings').submit(function(){ 
                    var pfno =  $.trim($('#emppfno').val());
                  //  alert("in html=pfno=="+pfno);
                    if(pfno === ''){ 
                        alert('Employee PF No. Text-field is empty !!!.');
                        return false;
                    }    
            
            
                });
                
                $('#salearheadtab6,#salearheadtab7,#salearheadtab8').on('click',function(){ 
                    var pfno =  $.trim($('#emppfno').val()); 
                    var currentAttrValue = $(this).attr('href');
                    //alert("currentAttrValue==="+currentAttrValue);
                    var combval=currentAttrValue+","+pfno;
                   // alert("pfnocombval=="+combval);
                    if(pfno !== ''){
                        $.ajax({
                            url: "<?php echo base_url();?>sisindex.php/jslist/getppdetail",
                            type: "POST",
                            data: {"pfshid" : combval},
                            dataType:"html",
                            success:function(data){
                                //alert("data=success=="+data);
                                if(currentAttrValue === '#tab6'){
					$('#ehtest').html(data.replace(/\\/gi,""));
                                }
				else{
                               // alert("data=success=in else part="+data) ;   
                                var empinput=data.split(",");
                                //alert("data=success=in else part="+empinput) ;  
                                
                                for (var i=0; i < empinput.length;i++){ 
                                    
                                    var token=(empinput[i].replace(/[[\]"|"]/g,"")).split('^');
                                    
                                    //alert("data=success=token2="+token[2]);
                        //            if(currentAttrValue === '#tab6'){
                          //              $('#headamtI'+i).val(token[2].replace(/[[\]"|"]/g,""));
                            //        }
                                    if(currentAttrValue ==='#tab7'){
                                     //   alert ("value of token0====="+token[0]);
                                        $('#headnumber'+i).val(token[0].replace(/[[\]"|"]/g,""));
                                        $('#headamt'+i).val(token[1].replace(/[[\]"|"]/g,""));
                                       // $('#totalinstall'+i).val(token[4].replace(/[[\]"|"]/g,""));
                                       // $('#installno'+i).val(token[5].replace(/[[\]"|"]/g,""));
                                        //$('#installamount'+i).val(token[6].replace(/[[\]"|"]/g,""));
                                    }
                                    
                                    if(currentAttrValue ==='#tab8'){
                                        
                                        $('#headnumberL'+i).val(token[0].replace(/[[\]"|"]/g,""));
                                        $('#headamtL'+i).val(token[1].replace(/[[\]"|"]/g,""));
                                        $('#totalinstallL'+i).val(token[2].replace(/[[\]"|"]/g,""));
                                        $('#installnoL'+i).val(token[3].replace(/[[\]"|"]/g,""));
                                        $('#installamountL'+i).val(token[4].replace(/[[\]"|"]/g,""));
                                    }
                              
                                }
			}//else
                            },
                            error:function(data){
                            //alert("data in error==="+data);
                                alert("error occur..!!");
                            }
                        });
                    }    
                });
                
                
                /******************************************start formula calculation on run time**********/
              //  $('#headamtF').click(function(){
             
             /*   $(document).on("click", ".headamtF", function() {
               //$('#headamtF').click(function(){
                //var k=0;
                var i=0;
                alert("seema hello====");
                     var hfor;
                    var pfno =  $.trim($('#emppfno').val());*/
                   /* $(".headF").each(function(){
                        hfor= $('#headF'+k).val();
                        alert("seema kkk="+k);
                         alert("seema formula=code="+pfno+","+hfor);
                         k++;  
                    });*/
                   /* $(".headamtI").each(function(){
                         hfor= $('#headF'+i).val();
                        alert("seema formula=code="+pfno+","+hfor); 
                  
                    //alert("seema kkkin lastend loop ="+k);
                    if(hfor !==''){
                       
                        var result = hfor.replace(/[{()}]/g, '');//match(/\((.*)\)/);
                         alert("match str==="+result);//[1]+"0000,"+result[0]);
                        //var strfmla2=explode("+", hfor);
                        //var strfmla3=explode("*",matches[3]);
                       // alert("matchstr888==="+strfmla2+","+strfmla3);
                        var str1=result.split("*");
                        alert("match str1==="+str1[0]+",oop=="+str1[1]);
                        var str2=str1[0].split("+");
                        alert("sencond=="+str2[0]+","+str2[1]);*/
                       // var i=0;
                    /*    var headval1;
                        var headval2;
                       // $(".headamtI").each(function(){
                            
                            var headcode= $.trim($('#ehcode'+i).text());
                            alert("hcode=="+headcode);
                            
                            //var combval=headcode+","+headval+","+pfno;
                            if(str2[0] !=='' ){
                            if(headcode === str2[0]){
                                headval1= $('#headamtI'+i).val();
                                alert("headval1=in 1st loop="+headval1);
                            } 
                            }
                            if(str2[1] !=='' ){
                            if(headcode === str2[1]){
                                headval2= $('#headamtI'+i).val();
                                alert("headval2=in 2nd loop="+headval2);
                            
                            } 
                             }
                        }        
                         i++;  
                        }); 
                   
                        //var rawfor=parseInt(headval1) + parseInt(headval2);
                        var rawfor=parseInt(headval1) + parseInt(headval2);
                        var finalval=rawfor * str1[1];
                        alert("rawfor=="+rawfor+"===finalval="+finalval+"str11==="+str1[1]);
                        $('#headamtF').val(finalval);
                        alert("value of ii==="+i); */
                   
                        
                   // }
                   
               // k++; 
               
                //} );
               
                
               //  k++;
                   
                   
              //  }) ;
                
                 /******************************************end formula calculation on run time**********/
                 
                 
                $('#tab8').on('change',function(){
                    var tcountloan=$.trim($('#tcount').val());
                    for (var i=0; i < tcountloan;i++){
                        var ttlintno =  $.trim($('#totalinstallL'+i).val());
                        if(ttlintno >0){
                            var headamt= $.trim($('#headamtL'+i).val());
                            var intallamt=$.trim($('#installamountL'+i).val());
                            var calck= ttlintno * intallamt;
                            if(calck != headamt){
                                alert("please check total intallment number.");
                                $('#totalinstallL'+i).css('border-color', 'red');
                                return false;
                            }    
                        }
                        
                    }
                });
                 
                 
            });
            
            function levelofpay(val){
                var pc= $('#pcomm').val();
                var wt= $('#wtype').val();
                var val = pc+","+wt;
    //          alert("seema==="+wt+"pc=="+pc);
              
                  $.ajax({
                    type: "POST",
                    url: "<?php echo base_url();?>sisindex.php/jslist/getlevelpay",
                    data: {"wt" : val},
                    dataType:"html",
                    success: function(data){
                     // alert("seema44==="+data);
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
        <input type="hidden" name="currentAttrValue">
    
        <?php $this->load->view('template/header'); ?>
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
                    <div><input type="text" name="emppfno" id="emppfno" value="<?php echo $emppfno; ?>" placeholder="Employee PF No"  required>    
                    </td>
		    <td>
			<input type="text" name="invalidpf" id="invalidpf" value="" style="text-decoration:none;border:0; font-size:20px;font-weight:bold;color:red; word-break: break-all;width:400px;"   readonly>
		    </td>
                </tr>
                </table>
           
            <!-- Tab links -->
            <div class="tabs">
            <table  width="100%"> 
            <ul class="tab-links">
                
               	<li class="active"><a href="#tab1">Personal/Work Details</a></li>
		<li><a href="#tab2">Employee Scale of Pay</a></li>
		<li><a href="#tab3">Bank Details</a></li>
		<li><a href="#tab4">HRA/CCA/Rent Details</a></li>
                <li><a href="#tab5">Others</a></li>
                <!--<li><a href="<?php //echo base_url();?>sisindex.php/payrollprofile/payprofile/I" id="tab5">Salary Earning Heads</a></li> -->
                <li><a href="#tab6" id="salearheadtab6">Salary Earning Heads</a></li>
		<li><a href="#tab7" id="salearheadtab7">Salary Subscription Deduction Heads</a></li>
		<li><a href="#tab8"id="salearheadtab8" >Salary Loan Heads</a></li>
            </ul>
            </table>    
                
                <div class="tab-content" >
                <!--<div id="PWD" class="tabcontent"> -->
                <?php echo form_open_multipart('payrollprofile/emppayprofile','id="my_id"');?>
                <div id="tab1" class="tab active">
                    <h3>Personal/Work Details</h3>
                    <p>
                        <table  width="100%" class="TFtable">
                        <tr><td colspan="5"><b> <hr/> </b></td></tr> 
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
                                <td > <b>Deduct UPF:</b><br>
                                    <input type="radio" name="dedupf" id="dedupf" value="yes">Yes &nbsp;&nbsp;&nbsp;
                                    <input type="radio" name="dedupf" id="dedupf"  checked value="no">No
                                </td>
                                <td > <b>Deduct CPS:</b><br>
                                    <input type="radio" name="pcontri" id="pcontri" value="yes">Yes &nbsp;&nbsp;&nbsp;
                                    <input type="radio" name="pcontri" id="pcontri" checked value="no">No
                                </td>
                                <td colspan=2> <b>Washing Allowance:</b><br>
                                    <input type="radio" name="washallw" id="washallw"  value="yes">Yes &nbsp;&nbsp;&nbsp;
                                    <input type="radio" name="washallw"  id="washallw" checked value="no">No
                                </td>

                            </tr>
                            <tr>
                            <td colspan="4">   
				<!--<input type="text" name="empid" id="empid" value="" > -->
                                <button name="pwdprofile" id="pwdprofile">Submit</button>
                                <input type="reset" name="Reset" value="Clear"/>
                            </td>
                            </tr>
                        </table>
         
                    </p>
                   <?php //echo form_close(); ?>
                </div>

                <!--    <div id="ESP" class="tabcontent"> -->
                <div id="tab2" class="tab">
                    <?php //echo form_open_multipart('payrollprofile/payprofile','id="my_id"');?>
                    <table  width="100%" class="TFtable" >
                        <h3></h3>
                        <tr><thead><th style="background-color:#0099CC;text-align:center;height:30px;color:white;font-size:15px;" colspan=4>Employee Scale of Pay</th></thead></tr>
                        <p> 
                         <tr><td colspan="5"><b> <hr/> </b></td></tr>    
		<!--	<tr id="6thdisp">-->
			<tr>
				<td> <b>Pay Commision:</b><br>
				<input type="text" id="pcomm01" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly>
				</td>
                            	<td class="6thdisp"> <b>Pay Band:</b><br>
				<input type="text" id="pscale01" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly>
				</td>
                            	<td> <b>Academic Level of Pay:</b><br>
				<input type="text" id="pscale11" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly>
				</td>
                            	<td> <b>Scale of Pay:</b><br> 
				<input type="text" id="pscale21" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly><br>
				</td><tr>
                            	<td colspan=4 class="6thdisp"> <b>Academic Grade Pay :</b><br>
				<input type="text" id="pscale31" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly><br>
				</td>
			</tr>
<!--			<tr id="7thdisp">
                                <td> <b>Pay Commision:</b><br>
				<input type="text" id="pcomm01" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly>
				</td>
                                <td> <b>Academic Level of Pay:</b><br>
				<input type="text" id="pscale11" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly>
				</td>
                                <td> <b>Scale of Pay:</b><br> 
				<input type="text" id="pscale21" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly><br>
				</td>
                        </tr>
-->		
			<tr><td colspan=4><font color="blue" size=5>If you want to change the values, please select below dropdown </font></td></tr>
                        <tr> 
                            <td> <b>Pay Commision:</b><br>
                            <div>
                                <select name="pcomm" id="pcomm" style="width:300px;" onchange="payband(this.value)">
                                    <option value="null" >------Select--------</option>
                                    <option value="6th">6th</option>
                                    <option value="7th">7th</option>
                                </select>
                            </div>
                            </td>
                            <td id="psc"> <b>Pay Band:</b><br>
                                <div>
                                <select name="pscale" id="pscale" style="width:300px;" >
                                <option disabled selected >------Select----------------</option>
                                </select>
                                </div>
                            </td>
                            <td id="psc1"> <b>Academic Level of Pay:</b><br>
                                <div>
                                    <select name="pscale1" id="pscale1" style="width:300px;" >
                                    <option disabled selected>------Select----------------</option>
                                    </select>
                                </div>
                            </td>    
                            <td id="psc2"> <b>Scale of Pay:</b><br>
                                <input type="text" id="pscale2" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly >
                            </td>
                        </tr>    
                        <tr>
                            <td colspan=4 id="psc3"> <b>Academic Grade Pay :</b><br>
				<input type="text" id="pscale3" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly>
                            </td>
                        </tr> 
                        </p> 
                        <tr>
                            <td colspan="4">   
				<!--<input type="text" name="empid" id="empid" value="" > -->
                           <!--      <button name="pwdprofile" id="pwdprofile">Submit</button> -->
                               <button name="espprofile" id="espprofile">Submit</button>
                                <input type="reset" name="Reset" value="Clear"/>
                            </td>
                        </tr>
                    </table> 
                    <?php //echo form_close(); ?>
                </div>

                <!--<div id="BDetails" class="tabcontent"> -->
                <div id="tab3" class="tab">
                    <?php //echo form_open_multipart('payrollprofile/payprofile','id="my_id"');?>
                    <table  width="100%" class="TFtable" >
                        <h3></h3>
                        <tr><thead><th style="background-color:#0099CC;text-align:center;height:30px;color:white;font-size:15px;" colspan=4>Bank Details</th></thead></tr>
                        <p>
                            <tr><td colspan="5"><b> <hr/> </b></td></tr> 
                            <tr>
                                <td> <b>Bank ACC No:</b><br><input type="text" name="accno" id="accno" value="" size="30" style=""></td>
                                <td> <b>Bank Name:</b><br><input type="text" name="bname" id="bname" value="" size="30" style=""></td>
                                <td> <b>Bank Branch:</b><br><input type="text" name="bbranch" id="branch" size="30" value="" style=""></td>
                                <td> <b>IFSC Code:</b><br><input type="text" name="ifsccode" id="ifsccode" size="30" value="" style=""></td>
                            </tr>
                            <tr>
                                <td> <b>MICR Code:</b><br><input type="text" name="micrcode" id="micrcode" value=""  size="30" style=""></td>
                                <td> <b>Account Type:</b><br>
                                <div>
                                    <select name="acctype" id="acctype" style="width:300px;" onchange="payband(this.value)">
                                        <option value="null" >------Select--------</option>
                                        <option value="Saving">Saving</option>
                                        <option value="Current">Current</option>
                                    </select>
                                </div>
                                </td>
                                <td colspan="2"> <b>Branch Address:</b><br><input type="text" name="bbadd" id="bbadd" value="" size="40"  style="height:50px;"></td>
                            </tr>
                            <tr>
                                <td> <b>Branch Phone No.:</b><br><input type="text" name="bbphone" id="bbphone" value=""  size="30" style=""></td>
                                <td colspan="4"> <b>Branch Email:</b><br><input type="text" name="bbemail" id="bbemail" size="30" value="" style=""></td>
                            </tr> 
                            <tr>
                            <td colspan="4">   
				<!--<input type="text" name="empid" id="empid" value="" > -->
                               <!--  <button name="pwdprofile" id="pwdprofile">Submit</button> -->
                                <button name="bankprofile" id="bankprofile">Submit</button> 
                                <input type="reset" name="Reset" value="Clear"/>
                            </td>
                            </tr>
                        </p>
                        
                    </table>
                    <?php // echo form_close(); ?>
                </div>

                <!--<div id="HCRDetails" class="tabcontent"> -->
                <div id="tab4" class="tab">
                    <?php //echo form_open_multipart('payrollprofile/payprofile','id="my_id"');?>
                    <table  width="100%" class="TFtable" >   
                        <h3></h3>
                        <tr><thead><th style="background-color:#0099CC;text-align:center;height:30px;color:white;font-size:15px;" colspan="4">HRA/CCA/Rent Details</th></thead></tr>
                        <p>
                        <tr><td colspan="5"><b> <hr/> </b></td></tr> 
                        <tr>
                            <td> <b>HRA Grade:</b><br>
                                <div>
                                <select name="hragrade" id="hragrade" style="width:300px;"> 
                                <option value="" >------------ HRA GRADE ---------</option>
                                    <?php foreach($hglist as $hgdata): ?>	
                                <option value="<?php echo $hgdata->hgc_id; ?>"><?php echo $hgdata->hgc_gradename;?></option> 
                                    <?php endforeach; ?>
                                </select>
                                </div>
                            </td>
                            <td> <b>CCA Grade :</b><br>
                                <div><select name="ccagrade" id="ccagrade" style="width:300px;"> 
                                    <option value="">------------ CCA GRADE ---------</option>
                                    <?php foreach($ccalist as $hgdata): ?>	
                                    <option value="<?php echo $hgdata->cgc_id; ?>"><?php echo $hgdata->cgc_gradename;?></option> 
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
                                <?php foreach($hglist as $hgdata): ?>
                                <option value="<?php echo $hgdata->hgc_id; ?>"><?php echo $hgdata->hgc_gradename;?>
                                </option>
                                <?php endforeach; ?>
                                </select></div>
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
                                <?php foreach($hglist as $hgdata): ?>	
                                <option value="<?php echo $hgdata->hgc_id; ?>"><?php echo $hgdata->hgc_gradename;?></option> 
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
                        </p>
                        <tr>
                            <td colspan="4">   
				<!--<input type="text" name="empid" id="empid" value="" > -->
                                <!-- <button name="pwdprofile" id="pwdprofile">Submit</button> -->
                                <button name="hcrprofile" id="hcrprofile">Submit</button>
                                <input type="reset" name="Reset" value="Clear"/>
                            </td>
                        </tr>
                    </table>
                    <?php //echo form_close(); ?>
                </div>
                <div id="tab5" class="tab">
                    <?php //echo form_open_multipart('payrollprofile/payprofile','id="my_id"');?>
                    <table  width="100%" class="TFtable" >
                        <h3></h3>
                        <tr><thead><th style="background-color:#0099CC;text-align:center;height:30px;color:white;font-size:15px;" colspan="4">Others</th></thead></tr>
                        <p>
                         <tr><td colspan="5"><b> <hr/> </b></td></tr>    
                        <tr>
                            <td><b> PAN No:</b><br><input type="text" name="panno" id="panno" value="" style="width:300px;"></td>
                            <td><b> Society:</b><br>
                                <div><select name="society" id="society" style="width:300px;"> 
                                    <option value="">------------ Society ---------</option>
                                    <?php foreach($society as $socdata): ?>	
                                    <option value="<?php echo $socdata->soc_id; ?>"><?php echo $socdata->soc_sname."(".$this->sismodel->get_listspfic1('society_master_list','soc_scode','soc_id',$socdata->soc_id)->soc_scode.")";?>
                                    </option> 
                                    <?php endforeach; ?>
                                </select></div>
                                </td>
                            <td><b> Society No:</b><br> <input type="text" name="societymember" id="socmem" value="" style="width:300px;"></td>
                            <td><b>Amount:</b><br><input type="text" name="socamount" id="socamount" value="" style="width:300px;"></td></p>
                        </tr>
                        </p>
                        <tr>
                            <td colspan="4">   
				<!--<input type="hidden" name="empid" id="empid" value="" > -->
                               <!-- <button name="pwdprofile" id="pwdprofile">Submit</button>--> 
                               <button name="otherprofile" id="otherprofile">Submit</button> 
                                <input type="reset" name="Reset" value="Clear"/>
                            </td>
                        </tr>
                    </table> 
                    <?php //echo form_close(); ?>
                </div>
               
                <?php //echo form_close(); ?>
            
                <!--<div id="SEHeads" class="tabcontent"> -->
                <div id="tab6" class="tab">
                    <p id="eartab6">
                    <?php //echo form_open_multipart('payrollprofile/payprofile','id="my_id"');?>
                    <table  width="100%" class="TFtable" >
                        <h3></h3>
                        <tr><thead><th style="background-color:#0099CC;text-align:center;height:30px;color:white;font-size:15px;" colspan="7">Salary Earning Heads</th></thead></tr>
                        <p></p>
                        <thead>
                <tr>
                    <th style="font-size:16px;">Sr.No</th>
                    <th style="font-size:16px;">Head Code</th>
                    <th style="font-size:16px;">Head Name</th>
                    <th style="font-size:16px;">Amount</th>
                    <th style="font-size:16px;">Increment</th>
                </tr>
            </thead>
		<tbody id="ehtest"> </tbody> 
                        <tr>
                            <td colspan="8">   
                                <button name="ppearnings" id="ppearnings">Submit</button>
                                <input type="reset" name="Reset" value="Clear"/>
                            </td>
                        </tr>
                    </table>
                    <?php // echo form_close(); ?>
                    </p>
                </div>
                
                <!--<div id="SSDHeads" class="tabcontent"> -->
                <div id="tab7" class="tab">
                    <?php //echo form_open_multipart('payrollprofile/payprofile','id="my_id"');?>
                    <table  width="100%" class="TFtable" >
                        <h3></h3>
                        <tr><thead><th style="background-color:#0099CC;text-align:center;height:30px;color:white;font-size:15px;" colspan="8">Salary Subscription Deduction Heads</th></thead></tr>
                        <p>
                        <tr><td colspan="8"></td></tr> 
                        <?php    $whdata=array('sh_type'=>'D');
                        $shdata['records']= $this->sismodel->get_orderlistspficemore('salary_head','*',$whdata,'');
                      //  print_r($shdata['records']);
                        ;?>
                        
                        
                <thead>
                    <tr>
                    <th style="font-size:16px;" >Sr.No</th>
                    <!--<th style="font-size:16px;">Head Code</th> -->
                    <th style="font-size:16px;">Head Name</th>
                    <th style="font-size:16px;"> Number</th>
                    <th style="font-size:16px;"> Amount</th>
                  <!--  <th style="font-size:16px;">Total No. of Installment</th>
                    <th style="font-size:16px;">No.of Installment</th>
                    <th style="font-size:16px;">Installment Amount</th>
		   -->
                </tr>
            </thead>
            <tbody>
                <?php $serial_no=1; $j=0;?>
                <?php if(count($shdata['records']) ):  ?>
                   
                    <?php foreach( $shdata['records'] as $recordded){ ?>
                   
                        <tr>
                        <?php if($recordded->sh_calc_type == 'Y'){ 
                                   
                                    $formula='';
                                    $formula1=$this->sismodel->get_listspfic1('salary_formula','sf_formula','sf_salhead_id',$recordded->sh_id);
                                    if(!empty($formula1)){
                                        $formula=$formula1->sf_formula;
                                    
                                    } else{
                                        $formula='';
                                    } 
                                }
                        ?>
                        <td><?php echo $serial_no++; ?></td>
                        <?php    if($recordded->sh_tnt == 'Common') : ?>    
                            <!--<td><?php //$hcode=$this->sismodel->get_listspfic1('salary_head','sh_code','sh_id',$recordded>sh_id)->sh_code;
                               // echo $hcode; ?></td> -->
                            <td><?php $shname=$this->sismodel->get_listspfic1('salary_head','sh_name','sh_id',$recordded->sh_id)->sh_name;
                                echo $shname;
                                        if(($recordded->sh_calc_type == 'Y') && !empty($formula)){
                                            echo "<font color=\"blue\">   ( $formula ) ";
                                                    
                                        }
                            ?></td>
                            <td>
                                <input type="text" class="headnumber" name="headnumber<?php echo $j;?>" id="headnumber<?php echo $j;?>"  value="<?php  ?>" >  
                            </td>
                            <td>
                                <input type="text" class="headamtD" name="headamtD<?php echo $j;?>" id="headamt<?php echo $j;?>"  value="<?php echo 0; ?>" >    
                                
                            </td>
                       <!--     <td>
                                <input type="text" class="totalinstall" name="totalinstall<?php echo $j;?>" id="totalinstall<?php echo $j;?>"  value="<?php echo 0; ?>" >  
                            </td>
                            <td>
                                <input type="text" class="installno" name="installno<?php echo $j;?>" id="installno<?php echo $j;?>"  value="<?php echo 0; ?>">  
                            </td>
                            <td>
                                <input type="text" class="installamount" name="installamount<?php echo $j;?>" id="installamount<?php echo $j;?>"  value="<?php echo 0; ?>" >  
                            </td> -->
                         <?php else :  /*for TNT case from script*/;?>
                            
                            <td><?php $shname=$this->sismodel->get_listspfic1('salary_head','sh_name','sh_id',$recordded->sh_id)->sh_name;
                                echo $shname; 
                            ?></td>
                            <td>
                                <input type="text" class="headnumber" name="headnumber<?php echo $j;?>" id="headnumber<?php echo $j;?>"  value="<?php  ?>" >  
                            </td>
                            <td>
                                <input type="text" class="headamtD" name="headamt<?php echo $j;?>" id="headamt<?php echo $j;?>"  value="<?php echo 0; ?>" >    
                            </td>
                          <!--  <td>
                                <input type="text" class="totalinstall" name="totalinstall<?php echo $j;?>" id="totalinstall<?php echo $j;?>"  value="<?php echo 0; ?>" >  
                            </td>
                            <td>
                                <input type="text" class="installno" name="installno<?php echo $j;?>" id="installno<?php echo $j;?>"  value="<?php echo 0; ?>">  
                            </td>
                            <td>
                                <input type="text" class="installamount" name="installamount<?php echo $j;?>" id="installamoun<?php echo $j;?>t"  value="<?php echo 0; ?>" >  
                            </td>
                           --> 
                         <?php endif  /*for TNT case from script*/;?> 
                        </tr>
                        <input type="hidden" name="sheadidded<?php echo $j;?>" value="<?php echo $recordded->sh_id ; ?>">  
                        
                    <?php $j++;}; ?>
                    
                <?php else : ?>
                 <tr>   <td colspan= "13" align="center"> No Records found...!</td></tr>
                <?php endif;?>
            <!-----------------------------------------------end both------------------------------>
           </tbody>  
                </p> 
                    <tr>
                        <td colspan="8"> 
                         <!--<input type="text" name="empid" id="empid" value="" >   -->
                         <input type="hidden" name="dedcount" id="tcount" value="<?php echo $j;?>">     
<!--                        <button name="pwdprofile" id="pwdprofile">Submit</button>-->
                        <button name="sdedprofile" id="sdedprofile">Submit</button>
                       <input type="reset" name="Reset" value="Clear"/>
                        </td>
                    </tr>
                </table>
               
                    <?php //echo form_close(); ?>
                </div>

               
                <div id="tab8" class="tab">
                    <?php //echo form_open_multipart('payrollprofile/payprofile','id="my_id"');?>
                    <table  width="100%" class="TFtable" >
                        <h3></h3>
                        <tr><thead><th style="background-color:#0099CC;text-align:center;height:30px;color:white;font-size:15px;" colspan="7">Salary Loan Heads</th></thead></tr>
                       
                        <tr><td colspan="5"></td></tr> 
                         <?php    
			$whdata=array('sh_type'=>'L');
                        $shdata['records']= $this->sismodel->get_orderlistspficemore('salary_head','*',$whdata,'');
                       
                        ;?>
                        
                        <thead>
                <tr>
                    <th style="font-size:16px;">Sr.No</th>
                    <!--<th style="font-size:16px;">Head Code</th> -->
                    <th style="font-size:16px;">Head Name</th>
                    <th style="font-size:16px;">Head Number</th>
                    <th style="font-size:16px;">Amount</th>
                    <th style="font-size:16px;">No.of Installment</th>
                    <th style="font-size:16px;">Total No. of Installment</th>
                    <th style="font-size:16px;">Installment Amount</th>
		   
                </tr>
            </thead>
            <tbody>
                <?php $serial_no=1;$k=0; ?>
                <?php if( count($shdata['records']) ):  ?>
                    <?php foreach($shdata['records'] as $recordloan){ ?>
                        <tr>
                        
                            <td><?php echo $serial_no++; ?></td>
                         <?php  if($recordloan->sh_tnt == 'Common') : ?>        
                            
                            <!--<td><?php $hcode=$this->sismodel->get_listspfic1('salary_head','sh_code','sh_id',$recordloan->sh_id)->sh_code;
                              //  echo $hcode; ?></td> -->
                            <td><?php $shname=$this->sismodel->get_listspfic1('salary_head','sh_name','sh_id',$recordloan->sh_id)->sh_name;
                                echo $shname; 
                               
                            ?></td>
                             <td>
                                <input type="text" class="headnumberL" name="headnumberL<?php echo $k;?>" id="headnumberL<?php echo $k;?>"  value="<?php  ?>" >    
                            </td>
                            <td>
                                <input type="text" class="headamtL" name="headamtL<?php echo $k;?>" id="headamtL<?php echo $k;?>"  value="<?php echo 0; ?>" >    
                            </td>
                            <td>
                                <input type="text" class="installnoL" name="installnoL<?php echo $k;?>" id="installnoL<?php echo $k;?>"  value="<?php echo 0; ?>" >  
                            </td>
                            <td>
                                <input type="text" class="totalinstallL" name="totalinstallL<?php echo $k;?>" id="totalinstallL<?php echo $k;?>"  value="<?php echo 0; ?>" >  
                            </td>
                            <td>
                                <input type="text" class="installamountL" name="installamountL<?php echo $k;?>" id="installamountL<?php echo $k;?>"  value="<?php echo 0; ?>" >  
                            </td>
                        <?php else :  /*for TNT case from script*/;?> 
                            <td><?php $shname=$this->sismodel->get_listspfic1('salary_head','sh_name','sh_id',$recordloan->sh_id)->sh_name;
                                echo $shname; 
                               
                            ?></td>
                            <td>
                                <input type="text" class="headnumberL" name="headnumberL<?php echo $k;?>" id="headnumberL<?php echo $k;?>"  value="<?php  ?>" >    
                            </td>
                            <td>
                                <input type="text" class="headamtL" name="headamtL<?php echo $k;?>" id="headamtL<?php echo $k;?>"  value="<?php echo 0; ?>" >    
                            </td>
                            <td>
                                <input type="text" class="installnoL" name="installnoL<?php echo $k;?>" id="installnoL<?php echo $k;?>"  value="<?php echo 0; ?>" >  
                            </td>
                            <td>
                                <input type="text" class="totalinstallL" name="totalinstallL<?php echo $k;?>" id="totalinstallL<?php echo $k;?>"  value="<?php echo 0; ?>" >  
                            </td>
                            <td>
                                <input type="text" class="installamountL" name="installamountL<?php echo $k;?>" id="installamountL<?php echo $k;?>"  value="<?php echo 0; ?>" >  
                            </td>   
                            
                        <?php endif  /*for TNT case from script*/;?>   
                        </tr>
                        <input type="hidden" name="sheadidloan<?php echo $k;?>" value=" <?php echo $recordloan->sh_id ; ?>" >  
                        
                    <?php $k++;}; ?>
                    
                <?php else : ?>
                 <tr>   <td colspan= "13" align="center"> No Records found...!</td></tr>
                <?php endif;?>
            <!-----------------------------------------------end both------------------------------>
		</tbody>  
                    <tr>
                            <td colspan="8">  
                                <!--<input type="text" name="empid" id="empid" value="" >-->
				<input type="hidden" name="loancount" id="tcount" value="<?php echo $k;?>">  
                       <!--          <button name="pwdprofile" id="pwdprofile">Submit</button> -->
                                <button name="sloanprofile" id="sloanprofile">Submit</button> 
                                <input type="reset" name="Reset" value="Clear"/>
                            </td>
                        </tr>
                    </table>
                                    
                </div>
                 <input type="hidden" name="empid" id="empid" value="" >   
                <?php echo form_close(); ?>  
                           
            </div>
        </div>           
    </body>
    <p>&nbsp;</p>
    <div><?php $this->load->view('template/footer'); ?></div>   
</html>    
