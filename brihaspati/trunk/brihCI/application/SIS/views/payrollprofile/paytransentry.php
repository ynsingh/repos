    

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
                          //  alert("datat==="+data);
                            var empinput=data.split(",");
                            $('#campus').val(empinput[0].replace(/[[\]"|"]/g,""));
                            $('#uo').val(empinput[1].replace(/"|"/g,""));
                            $('#dept').val(empinput[2].replace(/"|"/g,""));
                            $('#schm').val(empinput[3].replace(/"|"/g,""));
                            $('#ddo').val(empinput[4].replace(/"|"/g,""));
                            $('#wtype').val(empinput[5].replace(/"|"/g,""));
       //                     $('#group').val(empinput[6].replace(/"|"/g,""));
                            $('#desig').val(empinput[7].replace(/"|"/g,""));
      //                      $('#etype').val(empinput[8].replace(/"|"/g,""));
    //                        $('#doj').val(empinput[9].replace(/"|"/g,""));
                            $('#empname').val(empinput[10].replace(/"|"/g,""));
  //                          $('#accno').val(empinput[11].replace(/"|"/g,""));
//                            $('#addharno').val(empinput[12].replace(/"|"/g,""));
                        //    $('#dob').val(empinput[13].replace(/"|"/g,""));
                      //      $('#add').val(empinput[14].replace(/"|"/g,""));
                    //        $('#contact').val(empinput[15].replace(/"|"/g,""));
                  //          $('#dor').val(empinput[16].replace(/"|"/g,""));
                //            $('#pscale').val(empinput[17].replace(/[[\]"|"]/g,""));

              //              $('#bname').val(empinput[18].replace(/[[\]"|"]/g,""));
            //                $('#ifsccode').val(empinput[19].replace(/[[\]"|"]/g,""));
          //                  $('#branch').val(empinput[20].replace(/[[\]"|"]/g,""));
        //                    $('#pcomm').val(empinput[21].replace(/[[\]"|"]/g,""));
      //                      
    //                        var pcon=empinput[22].replace(/[[\]"|"]/g,"");
  //                          if(pcon ==='yes'){
//                                $('#pcontri').prop("checked", true);
                        //    } 
                      //      $('#upfno').val(empinput[23].replace(/[[\]"|"]/g,""));
                    //        $('#qtrno').val(empinput[24].replace(/[[\]"|"]/g,""));
                  //          $('#qtrtype').val(empinput[25].replace(/[[\]"|"]/g,""));
                //            var univemp=empinput[26].replace(/[[\]"|"]/g,"");
              //              if(univemp ==='yes'){
            //                    $('#uniemp').prop("checked", true);
                              
          //                  } 
        //                    var washA=empinput[27].replace(/[[\]"|"]/g,"");
      //                      if(washA ==='yes'){
    //                            $('#washallw').prop("checked", true);
                              
  //                          } 
//                            var Dupf=empinput[28].replace(/[[\]"|"]/g,"");
                            //if(Dupf ==='yes'){
                          //      $('#dedupf').prop("checked", true);
                              
                        //    } 
                      //      $('#hragrade').val(empinput[29].replace(/[[\]"|"]/g,""));
                    //        $('#ccagrade').val(empinput[30].replace(/[[\]"|"]/g,""));
                  //          var Isumm=empinput[31].replace(/[[\]"|"]/g,"");
                //            if(Isumm ==='yes'){
              //                  $('#incsumm').prop("checked", true);
            //                  
          //                  } 
        //                    $('#lic1no').val(empinput[32].replace(/[[\]"|"]/g,""));
      //                      $('#lic1amount').val(empinput[33].replace(/[[\]"|"]/g,""));
    //                        $('#lic2no').val(empinput[34].replace(/[[\]"|"]/g,""));
  //                          $('#lic2amount').val(empinput[35].replace(/[[\]"|"]/g,""));
//                            $('#lic3no').val(empinput[36].replace(/[[\]"|"]/g,""));
                      //      $('#lic3amount').val(empinput[37].replace(/[[\]"|"]/g,""));
                    //        $('#lic4no').val(empinput[38].replace(/[[\]"|"]/g,""));
                  //          $('#lic4amount').val(empinput[39].replace(/[[\]"|"]/g,""));
                //            $('#lic5no').val(empinput[40].replace(/[[\]"|"]/g,""));
              //              $('#lic5amount').val(empinput[41].replace(/[[\]"|"]/g,""));
            //                $('#prdno1').val(empinput[42].replace(/[[\]"|"]/g,""));
          //                  $('#prdno2').val(empinput[43].replace(/[[\]"|"]/g,""));
        //                    $('#prdno3').val(empinput[44].replace(/[[\]"|"]/g,""));
      //                      $('#plino1').val(empinput[45].replace(/[[\]"|"]/g,""));
    //                        $('#plino2').val(empinput[46].replace(/[[\]"|"]/g,""));
  //                          $('#society').val(empinput[47].replace(/[[\]"|"]/g,""));
//                            $('#socmem').val(empinput[48].replace(/[[\]"|"]/g,""));
                              $('#empid').val(empinput[49].replace(/[[\]"|"]/g,""));
				
                        },
                        error:function(data){
                            alert("error occur..!!");
                 
                        }
                    });
                }    
                
                });  //method empname
                /**********************************End of empdetail PF NO  script*********************************/
            
            });
            
                      
    </script>    
    </head>
    <body>
        <?php $this->load->view('template/header'); ?>
        <table width="100%"><tr><td>
        <?php
		$roleid=$this->session->userdata('id_role');
        $uname=$this->session->userdata('username');
        if(($roleid == 1)||($uname == 'rsection@tanuvas.org.in')){
                echo anchor('payrollprofile/viewpaytransentry', "View Transfer Entry" ,array('title' => 'View staff Transfer Entry ' , 'class' => 'top_parent'));
        }
            
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
              
<?php
		//set the month array
                    $cmonth= date('M');

                    $formattedMonthArray = array(
                         "1" => "Jan", "2" => "Feb", "3" => "Mar", "4" => "Apr",
                         "5" => "May", "6" => "Jun", "7" => "Jul", "8" => "Aug",
                        "9" => "Sep", "10" => "Oct", "11" => "Nov", "12" => "Dec",
                    );

                    // set start and end year range
                    $cyear= date("Y");
                    $yearArray = range(2015,  $cyear);
?>
 
                <table  width="100%" class="TFtable">
                
                <tr><thead><th style="background-color:#2a8fcf;text-align:left;height:40px;" colspan="4">&nbsp;&nbsp;Payroll Transfer Entry</th></thead></tr>
			<tr style="font-weight:bold;">
                    <td><label for="emppfno" style="font-size:15px;font-weight:bold;"><font>Employee PF No</font> <font color='Red'>*</font></label>
                    <div><input type="text" name="emppfno" id="emppfno" value="" placeholder="Employee PF No..."  required>   </div> 
                    </td>
                </tr>
                </table>
            <?php echo form_open_multipart('payrollprofile/paytransentry','id="my_id"');?>
                <table  width="100%" class="TFtable" >
                        <tr><td colspan="5"><b><span style="color:#0099CC;">Personal Details</span></b></td></tr>
                        <tr>
                            <td> <b>Employee Name:</b><br><input type="text" id="empname" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                            <td> <b>Campus Name:</b><br><input type="text"  id="campus" value=""  style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                            <td> <b>UO:</b><br><input type="text"  id="uo" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                            <td> <b>Department:</b><br><input type="text" id="dept" value=""style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                        </tr>
                        <tr>
                            <td> <b>Scheme:</b><br><input type="text" id="schm" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                            <td> <b>DDO:</b><br><input type="text"  id="ddo" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                            <td> <b>Working Type:</b><br><input type="text" id="wtype" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                            <td> <b>Designation:</b><br><input type="text" id="desig" value="" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                        </tr>
                        <tr>
                            <td><b>Days:</b><br><input type="text" name="days" id="days" value="" style="width:300px;"></td>
                            <td><b>HRA From:</b><br><input type="text" name="hrafrom" id="hrafrom" value="" style="width:300px;"></td>
                            <td><b>HRA To:</b><br><input type="text" name="hrato" id="hrato" value="" style="width:300px;"></td>
                            <td><b>Transit:</b><br><input type="text" name="transit" id="transit" value="" style="width:300px;"></td>
                        </tr>
                        <tr>
				<td><b>Select Month</b> <br>
                        	<select name="month" id="month" style="width:300px;font-weight:bold;">
                            	<option value="" disabled selected>--------Select Month ----------</option>
                            	<?php
                                foreach ($formattedMonthArray as $month) {
                                    $selected = ($month == $cmonth) ? 'selected' : '';

                                    echo '<option  '.$selected.' value="'.$month.'">'.$month.'</option>';
                                }
                            ?>

                        </select>
                    </td>
                    <td><b>Select Year</b> <br>
                        <select name="year" id="year" style="width:300px;font-weight:bold;">
                            <option value="" disabled selected>--------Select Year ----------</option>
                            <?php 
                                foreach ($yearArray as $year) {
                                // if you want to select a particular year
                                $selected = ($year == $cyear) ? 'selected' : '';

                                echo '<option '.$selected.' value="'.$year.'">'.$year.'</option>';
                                }
                            ?>
                        </select>
			<td></td>
			<td></td>
                    </td>

                        </tr>
                        <tr>
                            <td colspan="6">   
                            <input type="hidden" name="empid" id="empid" value="" >
                            <button name="ptransent" id="ptransent">Submit</button>
                            <input type="reset" name="Reset" value="Clear"/>
                            </td>
                        </tr>
                    </table>
              <!--  </form> -->
            </body>
            <p>&nbsp;</p>
        <div><?php $this->load->view('template/footer'); ?></div>     
</html>    
