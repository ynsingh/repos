<!--@name add_academicprofile.php  @author Manorama Pal(palseema30@gmail.com) -->

 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>
 <html>
    <title>Service Details</title>
    <head>
        <?php $this->load->view('template/header'); ?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/datepicker/jquery-ui.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-ui.js" ></script>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <style type="text/css">
          
        </style>
        <script>
            $(document).ready(function(){
               /* var today = new Date(); 
                $('#yopass,#yopass1,#yopass2,#yopass3,#yopass4,#yopass5,#yopass6').datepicker({
                    dateFormat: 'yy',
                    autoclose:true,
                    changeMonth: true,
                    changeYear: true,
                    yearRange: 'c-70:c',
                    endDate: "today",
                    maxDate: today
                }).on('changeDate', function (ev) {
                    $(this).datepicker('hide');
                });*/
                 
                   
            var counter = 0;

            $("#addrow").on("click", function () {
       
                var newRow = $("<tr>");
                var cols = "";
                cols += '<td><b>School Education</b><select name="stdclass[]" id="stdclass" ><option value="">----- Select ------</option><option value="8<sup>th</sup> std">8<sup>th</sup> std</option><option value="10<sup>th</sup> std/SSLC">10<sup>th</sup> std/SSLC</option><option value="Ten Plus Two/12<sup>th</sup>">Ten Plus Two/12<sup>th</sup></option></select></td>';
       
                cols += '<td><input type="text" name="board[]" id="buniv" placeholder="Board/university.." size="30"></td>';
                cols += '<td><select name="result[]" id="result" style="width:150px;"><option value="">-- Select result ---</option><option value="Pass">Pass</option><option value="Fail">Fail</option><option value="Discontinued">Discontinued</option></select></td>';
                cols +='<td><input type="text" name="yopass[]" value="" id="yopass" class="form-control" size="10" /></td>';
	        cols +='<td><select name="certtype[]" id="certtype" style="width:200px;"><option value="">--Select Certitficate Type --</option><option value="Provisional Degree">Provisional Degree</option><option value="Degree">Degree</option> </select> </td>';
                cols +='<td><input type="text" name="discipline[]" id="dpln" placeholder="Discipline" size="30"></td>';
                cols += '<td><input type="button" class="ibtnDel btn btn-md btn-danger "  value="Delete"></td>';
                newRow.append(cols);
                $("#myTable1").append(newRow);
                counter++;
    
      
            });
    
            $("#myTable1").on("click", ".ibtnDel", function (event) {
                $(this).closest("tr").remove();       
                counter -= 1
            });
    
            $("#addrow1").on("click", function () {
                var newRow = $("<tr>");
                var cols = "";
                  
                cols +='<td><b>Under Graduate (UG)</b><select name="degree[]" id="degree" >';
                cols +='<option value="">----- Select ------</option><option value="BA">BA</option>';
                cols +='<option value="BSc">BSc</option><option value="BCom">Bcom</option><option value="BCA">BCA</option><option value="BTech">BTech</option>';
                cols +='<option value="BE">BE</option><option value="BVSc">BVSc</option><option value="B.Lit">B.Lit.</option><option value="BLIS">BLIS</option>';
                cols +='<option value="BBA">BBA</option><option value="BFSc">BFSc</option><option value="BEd">BEd</option><option value="BPEd">BPEd</option>';
                cols +='<option value="BL">BL</option></select></td>';
            
                cols += '<td><input type="text" name="board1[]" id="buniv" placeholder="Board/university.." size="30"></td>';
                cols += '<td><select name="resul1t[]" id="result" style="width:150px;"><option value="">-- Select result --</option><option value="Pass">Pass</option><option value="Fail">Fail</option><option value="Discontinued">Discontinued</option></select></td>';
                cols +='<td><input type="text" name="yopass1[]" value="" id="yopass" class="form-control" size="10" /></td>';
	        cols +='<td><select name="certtype1[]" id="certtype1" style="width:200px;"><option value="">--Select Certitficate Type --</option><option value="Provisional Degree">Provisional Degree</option><option value="Degree">Degree</option> </select> </td>';
                cols +='<td><input type="text" name="discipline1[]" id="dpln" placeholder="Discipline" size="30"></td>';
                cols += '<td><input type="button" class="ibtnDel btn btn-md btn-danger "  value="Delete"></td>';
                newRow.append(cols);
                $("#myTable2").append(newRow);
                counter++;
            });
    
            $("#myTable2").on("click", ".ibtnDel", function (event) {
                $(this).closest("tr").remove();       
                counter -= 1
            });
    
            $("#addrow2").on("click", function () {
                var newRow = $("<tr>");
                var cols = "";
                  
                cols += '<td><b>Post Graduate (PG)</b><select name="pgdegree[]" id="pgdegree" ><option value="">----- Select ------</option>';
                cols += '<option value="MA">MA</option><option value="MSc">MSc</option><option value="MCom">Mcom</option><option value="MCA">MCA</option>';
                cols += '<option value="MTech">MTech</option><option value="ME">ME</option><option value="MVSc">MVSc</option><option value="M.Lit">M.Lit.</option>';
                cols += '<option value="MLIS">MLIS</option><option value="MBA">MBA</option><option value="MFSc">MFSc</option><option value="MEd">MEd</option>';
                cols += '<option value="MPEd">MPEd</option><option value="ML">ML</option></select></td>';
            
                cols += '<td><input type="text" name="board3[]" id="buniv" placeholder="Board/university.." size="30"></td>';
                cols += '<td><select name="result3[]" id="result" style="width:150px;"><option value="">-- Select result --</option><option value="Pass">Pass</option><option value="Fail">Fail</option><option value="Discontinued">Discontinued</option></select></td>';
                cols +='<td><input type="text" name="yopass3[]" value="" id="yopass" class="form-control" size="10" /></td>';
	        cols +='<td><select name="certtype3[]" id="certtype3" style="width:200px;"><option value="">--Select Certitficate Type --</option><option value="Provisional Degree">Provisional Degree</option><option value="Degree">Degree</option> </select> </td>';
                cols +='<td><input type="text" name="discipline3[]" id="dpln" placeholder="Discipline" size="30"></td>';
                cols += '<td><input type="button" class="ibtnDel btn btn-md btn-danger "  value="Delete"></td>';
                newRow.append(cols);
                $("#myTable3").append(newRow);
                counter++;
            });
     
    
            $("#myTable3").on("click", ".ibtnDel", function (event) {
                $(this).closest("tr").remove(); 
                counter -= 1
            });   
            
         
        
    
        });
    </script>
        
    </head>
    
    <body>
        <table width="100%">
            <tr>
                <?php
                    echo "<td align=\"left\" width=\"33%\">";
                    if($this->roleid == 4){
                        echo anchor('empmgmt/viewempprofile', 'View Profile ', array('class' => 'top_parent'));
                    }
                    else{
                        echo anchor('report/academic_profile/'.$this->emp_id, 'View Profile ', array('class' => 'top_parent'));
                    }
                    echo "</td>";
            
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Add Academic Qualification Details</b>";
                    echo "</td>";
                    echo "<td align=\"right\" width=\"33%\">";

                ?>
            </tr>
        </table>
        <table width="100%">
           <tr><td>
           <div>
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                <?php echo form_error('<div class="isa_error">','</div>');?>
                <?php if(isset($_SESSION['success'])){?>
                    <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                <?php  }; ?>
                <?php if  (isset($_SESSION['err_message'])){?>
                    <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                <?php }; ?>
            </div>
            </td></tr>
        </table>
        <form id="myform" action="<?php echo site_url('empmgmt/add_academicqualification/'.$this->emp_id);?>" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="empid" value="<?php echo  $this->emp_id ; ?>">
                <table style="width:100%; border:1px solid gray;" ><tr><td style="background-color:#0099CC; text-align:left; height:30px;" colspan=8">&nbsp;&nbsp; Add Academic Qualification Details</td></tr></table>
                <table  id="myTable1"  class="TFtable" align="center">
                <thead>
                        <tr>
                            <th>Academic</th>
                            <th>Board/University</th>
                            <th>Result</th>
                            <th>Year of Passing</th>
                            <th>Certificate Type</th>
                            <th>Discipline</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><b>School Education</b><br/>
                            <select name="stdclass[]" id="stdclass" >
                                <option value="">----- Select ------</option>
                                <option value="8<sup>th</sup> std">8<sup>th</sup> std</option>
                                <option value="10<sup>th</sup> std/SSLC">10<sup>th</sup> std/SSLC</option>
                                <option value="Ten Plus Two/12<sup>th</sup>">Ten Plus Two/12<sup>th</sup></option>
                            </select>
                            </td>
                            <td>
                            <input type="text" name="board[]" id="buniv" placeholder="Board/university.." size="30">        
                            </td>
                            <td><select name="result[]" id="result" style="width:150px;">
                                <option value="">--Select result --</option>
                                <option value="Pass">Pass</option>
                                <option value="Fail">Fail</option>
                                <option value="Discontinued">Discontinued</option>
                            </select>
                            </td>
                            <td>
                                <input type="text" name="yopass[]" value="" id="yopass" class="form-control" size="10" />
                            </td>
                       	    <td><select name="certtype[]" id="certtype" style="width:200px;">
                                <option value="">--Select Certitficate Type --</option>
                                <option value="Provisional Degree">Provisional Degree</option>
                                <option value="Degree">Degree</option>
                            </select>
                            </td>

                            <td>
                            <input type="text" name="discipline[]" id="dpln" placeholder="Discipline" size="30">        
                            </td>
                            <td>
                            <input type="button" class="btn btn-lg btn-block" id="addrow" class="addrow" value="Add Row" />
                            </td> 
                        </tr>
                        </tbody>
                        </table>
                        <table  id="myTable2"  class="TFtable" align="center">
                        <tbody>
                        <tr>
                            
                            <td><b>Under Graduate (UG)</b></br>
                            <select name="degree[]" id="degree1" >
                                <option value="">----- Select ------</option>
                                <option value="BA">BA</option>
                                <option value="BSc">BSc</option>
                                <option value="BCom">Bcom</option>
                                <option value="BCA">BCA</option>
                                <option value="BTech">BTech</option>
                                <option value="BE">BE</option>
                                <option value="BVSc">BVSc</option>
                                <option value="B.Lit">B.Lit.</option>
                                <option value="BLIS">BLIS</option>
                                <option value="BBA">BBA</option>
                                <option value="BFSc">BFSc</option>
                                <option value="Bed">Bed</option>
                                <option value="BPEd">BPEd</option>
                                <option value="BL">BL</option>
                            </select>
                            </td>
                            <td>
                            <input type="text" name="board1[]" id="buniv1" placeholder="Board/university.." size="30">        
                            </td>
                            <td><select name="result1[]" id="result1" style="width:150px;">
                                <option value="">--Select result--</option>
                                <option value="Pass">Pass</option>
                                <option value="Fail">Fail</option>
                                <option value="Discontinued">Discontinued</option>
                            </select>
                            </td>
                            <td>
                                <input type="text" name="yopass1[]" value="" id="yopass1" class="form-control" size="10" />
                            </td>
                       	    <td><select name="certtype1[]" id="certtype1" style="width:200px;">
                                <option value="">-- Select Certitficate Type --</option>
                                <option value="Provisional Degree">Provisional Degree</option>
                                <option value="Degree">Degree</option>
                            </select>
                            </td>
                            <td>
                            <input type="text" name="discipline1[]" id="dpln1" placeholder="Discipline" size="30">        
                            </td>
                            <td>
                                
                           <input type="button" class="btn btn-lg btn-block " id="addrow1" value="Add Row" />
                            </td> 
                        </tr>
                        </tbody>
                                                                
                    </table> 
                    <table  id="myTable3"  class="TFtable" align="center">    
                        <tbody>
                       <tr>
                            
                            <td><b>Post Graduate (PG)</b></br>
                            <select name="pgdegree[]" id="pgdegree" >
                                <option value="">----- Select ------</option>
                                <option value="MA">MA</option>
                                <option value="MSc">MSc</option>
                                <option value="MCom">Mcom</option>
                                <option value="MCA">MCA</option>
                                <option value="MTech">MTech</option>
                                <option value="ME">ME</option>
                                <option value="MVSc">MVSc</option>
                                <option value="M.Lit">M.Lit.</option>
                                <option value="MLIS">MLIS</option>
                                <option value="MBA">MBA</option>
                                <option value="MFSc">MFSc</option>
                                <option value="Med">Med</option>
                                <option value="MPEd">MPEd</option>
                                <option value="ML">ML</option>
                            </select>
                            </td>
                            <td>
                            <input type="text" name="board3[]" id="buniv3" placeholder="Board/university.." size="30">        
                            </td>
                            <td><select name="result3[]" id="result3" style="width:150px;">
                                <option value="">-- Select result --</option>
                                <option value="Pass">Pass</option>
                                <option value="Fail">Fail</option>
                                <option value="Discontinued">Discontinued</option>
                            </select>
                            </td>
                            <td>
                                <input type="text" name="yopass3[]" value="" id="yopass3" class="form-control" size="10" />
                            </td>
                       	    <td><select name="certtype3[]" id="certtype3" style="width:200px;">
                                <option value="">-- Select Certitficate Type --</option>
                                <option value="Provisional Degree">Provisional Degree</option>
                                <option value="Degree">Degree</option>
                            </select>
                            </td>
                            <td>
                            <input type="text" name="discipline3[]" id="dpln3" placeholder="Discipline" size="30">        
                            </td>
                            <td>
                                
                           <input type="button" class="btn btn-lg btn-block " id="addrow2" value="Add Row" />
                            </td> 
                        </tr>
                        </tbody>
                    </table>     
                      <table  id="myTable4"  class="TFtable" align="center"> 
                        <tbody>
                        <tr>
                            <td>
                                <input type="text" name="pgdiploma" value="P.G.Diploma" id="pgdip" class="form-control" readonly size="15" />
                            </td>
                            <td>
                            <input type="text" name="board2" id="buniv2" placeholder="Board/university.." size="30">        
                            </td>
                            <td><select name="result2" id="result2" style="width:150px;">
                                <option value="">-- Select result --</option>
                                <option value="Pass">Pass</option>
                                <option value="Fail">Fail</option>
                                <option value="Discontinued">Discontinued</option>
                            </select>
                            </td>
                            <td>
                                <input type="text" name="yopass2" value="" id="yopass2" class="form-control" size="10" />
                            </td>
                       	    <td><select name="certtype2" id="certtype2" style="width:200px;">
                                <option value="">-- Select Certitficate Type --</option>
                                <option value="Provisional Degree">Provisional Degree</option>
                                <option value="Degree">Degree</option>
                            </select>
                            </td>
                            <td>
                            <input type="text" name="discipline2" id="dpln2" placeholder="Discipline" size="30">        
                            </td>
                        </tr>    
                        <tr>
                            <td>
                                <input type="text" name="MPhil" value="MPhil" id="mphil" class="form-control" size="15" readonly/>
                            </td>
                            <td>
                            <input type="text" name="board4" id="buniv4" placeholder="Board/university.." size="30">        
                            </td>
                            <td><select name="result4" id="result4" style="width:150px;">
                                <option value="">-- Select result --</option>
                                <option value="Pass">Pass</option>
                                <option value="Fail">Fail</option>
                                <option value="Discontinued">Discontinued</option>
                            </select>
                            </td>
                            <td>
                                <input type="text" name="yopass4" value="" id="yopass4" class="form-control" size="10" />
                            </td>
                       	    <td><select name="certtype4" id="certtype4" style="width:200px;">
                                <option value="">-- Select Certitficate Type --</option>
                                <option value="Provisional Degree">Provisional Degree</option>
                                <option value="Degree">Degree</option>
                            </select>
                            </td>
                            <td>
                            <input type="text" name="discipline4" id="dpln4" placeholder="Discipline" size="30">        
                            </td>
                            
                        </tr>
                        <tr>
                            <td>
                                <input type="text" name="PhD" value="PhD" id="phd" class="form-control" size="15" readonly/>
                            </td>
                            <td>
                            <input type="text" name="board5" id="buniv5" placeholder="Board/university.." size="30">        
                            </td>
                            <td><select name="result5" id="resul5t" style="width:150px;">
                                <option value="">-- Select result --</option>
                                <option value="Pass">Pass</option>
                                <option value="Fail">Fail</option>
                                <option value="Discontinued">Discontinued</option>
                            </select>
                            </td>
                            <td>
                                <input type="text" name="yopass5" value="" id="yopass5" class="form-control" size="10" />
                            </td>
                       	    <td><select name="certtype5" id="certtype5" style="width:200px;">
                                <option value="">-- Select Certitficate Type --</option>
                                <option value="Provisional Degree">Provisional Degree</option>
                                <option value="Degree">Degree</option>
                            </select>
                            </td>
                            <td>
                            <input type="text" name="discipline5" id="dpln5" placeholder="Discipline" size="30">        
                            </td>
                           
                        </tr>
                        <!------------------------------------------------------------------------------------>
                         <tr>
                            <td>
                                <input type="text" name="PDF" value="PDF" id="pdf" class="form-control" size="15" readonly/>
                            </td>
                            <td>
                            <input type="text" name="board6" id="buniv6" placeholder="Board/university.." size="30">        
                            </td>
                            <td><select name="result6" id="result6" style="width:150px;">
                                <option value="">-- Select result --</option>
                                <option value="Pass">Pass</option>
                                <option value="Fail">Fail</option>
                                <option value="Discontinued">Discontinued</option>
                            </select>
                            </td>
                            <td>
                                <input type="text" name="yopass6" value="" id="yopass6" class="form-control" size="10" />
                            </td>
                       	    <td><select name="certtype6" id="certtype6" style="width:200px;">
                                <option value="">-- Select Certitficate Type --</option>
                                <option value="Provisional Degree">Provisional Degree</option>
                                <option value="Degree">Degree</option>
                            </select>
                            </td>
                            <td>
                            <input type="text" name="discipline6" id="dpln6" placeholder="Discipline" size="30">        
                            </td>
                           
                        </tr>
                        
                    </tbody>
                    <tr style="color:white;background-color:#0099CC; text-align:left; height:30px;">
                    <td colspan="6">
                    <button name="addacadrofile" id="adddynamic">Submit</button>
		    <button type="button" onclick="history.back();">Back</button>
                    </tr>    
                </table> 
                
        </form>
        <p> &nbsp; </p>
        <div align="center"> <?php $this->load->view('template/footer');?></div>
    </body>    
    </html>    
