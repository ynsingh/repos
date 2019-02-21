<!--@name add_technicalprofile.php  @author Manorama Pal(palseema30@gmail.com) -->

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
               
                var counter = 0;
                $("#addrow1").on("click", function () {
                    var newRow = $("<tr>");
                    var cols = "";
          
                    cols += '<td><select name="shorthand[]" id="shand"><option value="">----- Select  ------</option>';
                    cols += '<option value="English Higher-shorthand">English Higher</option><option value="English Inter-shorthand">English Inter</option><option value="English Lower-shorthand">English Lower</option>';
                    cols += '<option value="Tamil Higher-shorthand">Tamil Higher</option><option value="Tamil Inter-shorthand">Tamil Inter</option><option value="Tamil Lower-shorthand">Tamil Lower</option></select> </td>';
                    cols += '<td><input type="text" name="board3[]" id="buniv3" placeholder="Board/university.." size="30"></td>';
                    cols += '<td><select name="result3[]" id="result3"><option value="">----- Select result ------</option>';
                    cols += '<option value="Pass">Pass</option><option value="Fail">Fail</option><option value="Discontinued">Discontinued</option></select></td>';
                    cols += '<td><input type="text" name="yopass3[]" value="" id="yopass3" class="form-control" size="30" /></td>';
                    cols += '<td><input type="text" name="discipline3[]" id="dpln3" placeholder="Discipline/Program" size="30"></td>';
                    cols += '<td><input type="button" class="ibtnDel btn btn-md btn-danger "  value="Delete"></td>';
                    newRow.append(cols);
                    $("#myTable2").append(newRow);
                    counter++;
          
                });
    
                $("#myTable2").on("click", ".ibtnDel", function (event) {
                    $(this).closest("tr").remove();       
                    counter -= 1;
                });
    
                $("#addrow2").on("click", function () {
                    var newRow = $("<tr>");
                    var cols = "";
                    cols += '<td><select name="typing[]" id="typing"><option value="">----- Select  ------</option>';
                    cols += '<option value="English Higher-typing">English Higher</option><option value="English Lower-typing">English Lower</option>';
                    cols += '<option value="Tamil Higher-typing">Tamil Higher</option><option value="Tamil Lower-typing">Tamil Lower</option></select>';
                    cols += '<td><input type="text" name="board4[]" id="buniv4" placeholder="Board/university.." size="30"></td>';
                    cols += '<td><select name="result4[]" id="result4"><option value="">----- Select result ------</option>';
                    cols += '<option value="Pass">Pass</option><option value="Fail">Fail</option><option value="Discontinued">Discontinued</option></select></td>';
                    cols += '<td><input type="text" name="yopass4[]" value="" id="yopass4" class="form-control" size="30" /></td>';
                    cols += '<td><input type="text" name="discipline4[]" id="dpln4" placeholder="Discipline/Program" size="30"></td>';
                    cols += '<td><input type="button" class="ibtnDel btn btn-md btn-danger "  value="Delete"></td>';          
                    newRow.append(cols);
                    $("#myTable3").append(newRow);
                    counter++;
                });
    
                $("#myTable3").on("click", ".ibtnDel", function (event) {
                    $(this).closest("tr").remove();       
                    counter -= 1
                });
                
                $('.keyup-numeric').keyup(function() {
                    $('span.error-keyup-1').hide();
                    var inputVal = $(this).val();
                    var numericReg = /^\d*[0-9](|.\d*[0-9]|,\d*[0-9])?$/;
                    if(!numericReg.test(inputVal)) {
                        $(this).after('<span class="error error-keyup-1"><font color="red">Numeric characters only.</font></span>');
                    }
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
                        echo anchor('report/viewfull_profile/'.$this->emp_id, 'View Profile ', array('class' => 'top_parent'));
                    }
                    echo "</td>";
            
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Add Technical Qualification Details</b>";
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
        <form id="myform" action="<?php echo site_url('empmgmt/add_technicalprofile/'.$this->emp_id);?>" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="empid" value="<?php echo  $this->emp_id ; ?>">
                <table style="width:100%; border:1px solid gray;" ><tr><td style="background-color:#0099CC; text-align:left; height:30px;" colspan=8">&nbsp;&nbsp; Add Technical Details</td></tr></table>
                <table  id="myTable1"  class="TFtable" align="center">
                <thead>
                        <tr>
                            <th>Technical</th>
                            <th>Board/University</th>
                            <th>Result</th>
                            <th>Year of Passing</th>
                            <th>Discipline</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><input type="text" name="diploma" id="diploma" value="Diploma" size="30" readonly>
                            </td>
                            <td>
                            <input type="text" name="board" id="buniv" placeholder="Board/university.." size="30">        
                            </td>
                            <td><select name="result" id="result">
                                <option value="">----- Select result ------</option>
                                <option value="Pass">Pass</option>
                                <option value="Fail">Fail</option>
                                <option value="Discontinued">Discontinued</option>
                            </select>
                            </td>
                            <td>
                                <input type="text" name="yopass" value="" class="keyup-numeric" placeholder="Year of passing.."  size="30" />
                            </td>
                       
                            <td>
                            <input type="text" name="discipline" id="dpln" placeholder="Discipline/Program" size="30">        
                            </td>
                           <!-- <td>
                                
                           <input type="button" class="btn btn-lg btn-block " id="addrow" value="Add Row" />
                            </td> -->
                           
                        </tr>
                        
                        <tr>
                            <td><input type="text" name="iti" id="iti" value="ITI" size="30" readonly>
                            </td>
                            <td>
                            <input type="text" name="board1" id="buniv1" placeholder="Board/university.." size="30">        
                            </td>
                            <td><select name="result1" id="result1">
                                <option value="">----- Select result ------</option>
                                <option value="Pass">Pass</option>
                                <option value="Fail">Fail</option>
                                <option value="Discontinued">Discontinued</option>
                            </select>
                            </td>
                            <td>
                                <input type="text" name="yopass1" value="" class="keyup-numeric" placeholder="Year of passing.." size="30" />
                            </td>
                       
                            <td>
                            <input type="text" name="discipline1" id="dpln1" placeholder="Discipline/Program" size="30">        
                            </td>
                        </tr>
                         <tr>
                            <td><input type="text" name="ccourse" id="ccourse" value="Certified Course" size="30" readonly>
                            </td>
                            <td>
                            <input type="text" name="board2" id="buniv2" placeholder="Board/university.." size="30">        
                            </td>
                            <td><select name="result2" id="result2">
                                <option value="">----- Select result ------</option>
                                <option value="Pass">Pass</option>
                                <option value="Fail">Fail</option>
                                <option value="Discontinued">Discontinued</option>
                            </select>
                            </td>
                            <td>
                                <input type="text" name="yopass2" value="" class="keyup-numeric" placeholder="Year of passing.." size="30" />
                            </td>
                       
                            <td>
                            <input type="text" name="discipline2" id="dpln2" placeholder="Discipline/Program" size="30">        
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <table  id="myTable2"  class="TFtable" align="center">
                        <tbody>
                         <tr>
                            <td><b>Shorthand</b><br/>
                                <select name="shorthand[]" id="shand">
                                <option value="">----- Select  ------</option>
                                <option value="English Higher-shorthand">English Higher</option>
                                <option value="English Inter-shorthand">English Inter</option>
                                <option value="English Lower-shorthand">English Lower</option>
                                <option value="Tamil Higher-shorthand">Tamil Higher</option>
                                <option value="Tamil Inter-shorthand">Tamil Inter</option>
                                <option value="Tamil Lower-shorthand">Tamil Lower</option>
                             
                            </select>
                            </td>
                            <td>
                            <input type="text" name="board3[]" id="buniv3" placeholder="Board/university.." size="30">        
                            </td>
                            <td><select name="result3[]" id="result3">
                                <option value="">----- Select result ------</option>
                                <option value="Pass">Pass</option>
                                <option value="Fail">Fail</option>
                                <option value="Discontinued">Discontinued</option>
                            </select>
                            </td>
                            <td>
                                <input type="text" name="yopass3[]" value="" class="keyup-numeric" placeholder="Year of passing.." size="30" />
                            </td>
                       
                            <td>
                            <input type="text" name="discipline3[]" id="dpln3" placeholder="Discipline/Program" size="30">        
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
                            <td><b>Typing</b><br/>
                                <select name="typing[]" id="typing">
                                <option value="">----- Select  ------</option>
                                <option value="English Higher-typing">English Higher</option>
                                <option value="English Lower-typing">English Lower</option>
                                <option value="Tamil Higher-typing">Tamil Higher</option>
                                <option value="Tamil Lower-typing">Tamil Lower</option>
                             
                                </select>
                            </td>
                            <td>
                            <input type="text" name="board4[]" id="buniv4" placeholder="Board/university.." size="30">        
                            </td>
                            <td><select name="result4[]" id="result4">
                                <option value="">----- Select result ------</option>
                                <option value="Pass">Pass</option>
                                <option value="Fail">Fail</option>
                                <option value="Discontinued">Discontinued</option>
                            </select>
                            </td>
                            <td>
                                <input type="text" name="yopass4[]" value="" class="keyup-numeric" placeholder="Year of passing.." size="30" />
                            </td>
                       
                            <td>
                            <input type="text" name="discipline4[]" id="dpln4" placeholder="Discipline/Program" size="30">        
                            </td>
                            <td>
                                
                           <input type="button" class="btn btn-lg btn-block " id="addrow2" value="Add Row" />
                            </td> 
                        </tr>
                        </tbody>
                    
                    <tr style="color:white;background-color:#0099CC; text-align:left; height:30px;">
                    <td colspan="6">
                    <button name="addtechprofile" id="addtechnical">Submit</button>
		    <button type="button" onclick="history.back();">Back</button>
                    </tr>    
                </table> 
                
        </form>
        <p> &nbsp; </p>
        <div align="center"> <?php $this->load->view('template/footer');?></div>
    </body>    
    </html>    
