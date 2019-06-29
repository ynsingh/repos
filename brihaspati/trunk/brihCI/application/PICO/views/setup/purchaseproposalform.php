<?php defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * @name: Financial Power Form
 * @author: Nagendra Kumar Singh (nksinghiitk@gmail.com)
 * @author: Shivam Kumar Singh  (shivam.iitk1@gmail.com)
 */

?>
<html>
<title>Purchase Proposal | Form</title>
 <head>    
        <?php $this->load->view('template/header'); 
              

        ?>

         <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/datepicker/jquery-ui.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-ui.js" ></script>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">

       
</head>
<script>
    $(document).ready(function(){
        var today = new Date();
            $('#Date,#IndentDate,#DateFrom,#DateTo').datepicker({
                dateFormat: 'yy/mm/dd',
                autoclose:true,
                changeMonth: true,
                changeYear: true,
                yearRange: 'c-30:c+10',
                // endDate: "today",
                // maxDate: today
            }).on('changeDate', function (ev) {
                $(this).datepicker('hide');
            });


            var counter =0 ;
            var count=1;

                $("#addrow1").on("click", function () {
                    var newRow = $("<tr>");
                    var cols = "";
                    
                  //  cols += '<td><input type="text" name="srno[]" id="srno" value="" size="3" readonly></td>';
                    cols += '<td><input type="text" name="desc[]" id="desc" size="35"></td>';
                    cols += '<td><input type="text" name="stock[]" id="stock"  size="10"></td>';
                    cols += '<td><input type="text" name="quantity[]" id="quantity" size="20"></td>';
                    cols += '<td><input type="text" name="price[]" id="price" size="5"></td>';
                    cols += '<td><input type="text" name="gst[]" id="gst" size="8"></td>';
                    cols += '<td><input type="text" name="total[]" id="total" size="8"></td>';
                    cols += '<td><input type="button" class="ibtnDel btn btn-md btn-danger "  value="Delete"></td>';
                    newRow.append(cols);
                    $("#myTable1").append(newRow);
                    counter++;
          
                });
    
                $("#myTable1").on("click", ".ibtnDel", function (event) {
                    $(this).closest("tr").remove();       
                    counter -= 1;
                });


                $("#addrow2").on("click", function () {
                    var newRow = $("<tr>");
                    var cols = "";

                    //cols += '<td><center><input type="text" name="srno2[]"  size="3" value="" readonly></center></td>';
                    cols += ' <td><center><input type="text" name="pp_budgetprojno[]"  size="30" ></center></td>';
                    cols += '<td><center><input type="text" name="pp_budgethead[]"  size="25" ></td>';
                    cols += '<td><center><input type="text" name="pp_budgetamount[]" size="15" ></center></td>';
                    cols += '<td><input type="button" class="ibtnDel btn btn-md btn-danger"  value="Delete"></td>';
                    newRow.append(cols);
                    $("#myTable2").append(newRow);
                    counter++;
          
                });
    
                $("#myTable2").on("click", ".ibtnDel", function (event) {
                    $(this).closest("tr").remove();       
                    counter -= 1;
                });


                $('#vendorname').on('change',function(){
                var worktype = $(this).val();
                //alert(worktype);
                if(worktype == ''){
                    $('#address').prop('disabled',true);
                   
                }
                else{
                    $('#address').prop('disabled',false);
                    $.ajax({

                        url: "<?php echo base_url();?>picoindex.php/picosetup/getsubpurchase",
                        type: "POST",
                        data: {"purchase" : worktype},
                        dataType:"html",
                        success:function(data){
                            //alert("data==1="+data);
                            $('#address').html(data.replace(/^"|"$/g, ''));
                                                 
                        },
                        error:function(data){
                            alert("data in error==="+data);
                            alert("error occur..!!");
                 
                        }
                    });
                }
            }); 


        });

   

    </script>

<table width="100%"> 
            <tr><td>   
              <div align="left">
                    <?php echo validation_errors('<div  class="isa_warning">','</div>');?>
                    <?php echo form_error('<div class="isa_error">','</div>');?></div>

                    <?php if(isset($_SESSION['success'])){?>
                        <div class="isa_success"><?php echo $_SESSION['success'];?></div>

                    <?php
                    };
                    ?>
                </div> </br> 
        </td></tr>  
        </table>
         
        
<body>

   

    <form id="myform" action="" method="POST" class="form-inline" autocomplete="OFF" enctype="multipart/form-data">
			<table class="TFtable" >
			    <tr>
					<td><label for="pp_gemrefno" class="control-label">GeM Ref No.: </label></td>
                	<td>
                	<input type="text" name="pp_gemrefno"  class="form-control" size="30" placeholder="" /><br>
                	</td>
                    <td><label for="pp_ddate">Date:<font color='Red'>*</font></label></td>
                    <td><input type="text" name="pp_ddate" id="Date" value="<?php echo isset($_POST["Date"]) ? $_POST["Date"] : ''; ?>"  
                        size="30" ></td>
                </tr>
                <tr><td colspan="4"><u><strong>(A) To be filled by Indenter</strong></u></td></tr>
                <tr>
                    <td><label for="pp_deptindentno" class="control-label">Department Indent No.: </label></td>
                    <td>
                    <input type="text" name="pp_deptindentno"  class="form-control" size="30" placeholder="Department Indent No." /><br>
                    </td>
                    <td><label for="pp_deptid" class="control-label">Department: </label></td>
                    <td>
                        <select id="dept" name="pp_deptid" class="my_dropdown">
                            <option selected="" disabled="">------------Select Department------------</option>
                            <?php
                                foreach ($dept as $row){
                            ?>
                                    <option value="<?php echo $row->dept_id ?>"><?php echo $row->dept_name ?></option>
                                <?php
                                }
                                ?>
                        </select>
                    </td>
                <tr>
                    <td><label for="IndentDate">Indent Date:<font color='Red'>*</font></label></td>
                    <td colspan="3"><input type="text" name="pp_indentdate" id="IndentDate" value="<?php echo isset($_POST["pp_indentdate"]) ? $_POST["pp_indentdate"] : ''; ?>" size="30" ></td>
                </tr>
                </tr>
                <th colspan="4">
                    Intender's Details: 
                </th>
                <tr>
                    <td><label for="pp_indentername" class="control-label">Name: </label></td>
                    <td>
                    <input type="text" name="pp_indentername"  class="form-control" size="30" placeholder="Enter Your Name" /><br>
                    </td>
                    <td><label for="pp_indenterid" class="control-label">PF No.: </label></td>
                    <td>
                    <input type="text" name="pp_indenterid"  class="form-control" size="30" placeholder="Enter PF No." /><br>
                    </td>
                </tr>
                <tr>
                    <td><label for="pp_indenteremail" class="control-label">E-mail ID: </label></td>
                    <td colspan="3">
                    <input type="email" name="pp_indenteremail"  class="form-control" size="30" placeholder="yourname@email.com" /><br>
                    </td>

                </tr> 
                <th colspan="4">
                    Type of Material:
                </th>   
                <tr>
					<td><label for="fp_authority" class="control-label">Type of Material:<font color='Red'>*</font> </label></td>
                	<td colspan="3">
                        <select name="typeofmaterial" class="my_dropdown">
                        <option selected="" disabled="" value="" style="width: 100% ;">-------------------Select-----------------</option>
                        <?php
                           foreach ($material as $row) {      
                        ?>
                        <option value="<?php echo $row->mt_id ?>"><?php echo $row->mt_name ?></option>
                        <?php
                        }
                        ?>
                        <option value="Services">Services</option>
                        </select>
                        &nbsp;&nbsp;<i>(Please pick whichever is applicable)</i>
                	</td>
                </tr>
        </table>

        <table class="TFtable">
            <tr><th><u>Item Details of Required Items:</u></th></tr>
        </table>
       

        <table  id="myTable1"  class="TFtable" align="center">
             <thead align="center">
            <!-- <th>S.No.</th> -->
            <th><center>Complete Decription of Good/Services intended<br>to be procured</center></th><th>Stock held on date</th><th><center>Quantity Required</center></th><th><center>Unit Price</center></th><th>GST Rate Applicable</th><th>Total Cost with Taxes</th><th><center><font size="1">Action</font></center></th>
            </thead>
            <tbody>    
                <tr>
                    <!-- <td><input type="text" name="srno[]" id="srno" value="1" size="3" readonly></td> -->
                    <td><input type="text" name="desc[]" id="desc" size="35"></td>
                    <td><input type="text" name="stock[]" id="stock"  size="10"></td>
                    <td><input type="text" name="quantity[]" id="quantity" size="20"></td>
                    <td><input type="text" name="price[]" id="price" size="5"></td>
                    <td><input type="text" name="gst[]" id="gst" size="8"></td>
                    <td><input type="text" name="total[]" id="total" size="8"></td>
                    
                    <td>
                    <input type="button" class="btn btn-lg btn-block " id="addrow1" value="Add Row" />
                    </td> 
                </tr>
                
            </tbody>
            <table class="TFtable">
                <tr>
                    <td colspan="6"><b>Total Cost</b><input type="text" name="fp_limit" class="form-control" size="8" ></td>
                    </td>
                </tr>
            </table>
        </table>
       <table class="TFtable">
        
        </table>        
        <table class="TFtable" id="myTable2">
            <tr><td colspan="5"><b><u>Budget Details:</u></b></td></tr>

            <tr>
                <!-- <th><center>S.No.</center></th> -->
                <th><center>Department/Project No.</center></th><th><center>Budget Head</center></th><th><center>Budget Amount</center></th><th>Action</th></tr>
             <tr>
                    <!-- <td><center><input type="text" name="srno_1"  size="3" value="1" readonly></center></td> -->
                    <td><center><input type="text" name="pp_budgetprojno[]"  size="30" ></center></td>
                    <td><center><input type="text" name="pp_budgethead[]"  size="25" ></td>
                    <td><center><input type="text" name="pp_budgetamount[]" size="15" ></center></td>
                    <td>
                    <input type="button" class="btn btn-lg btn-block " id="addrow2" value="Add Row" />
                    </td> 
                </tr>
            
        </table>

        <table class="TFtable">
             <tr><th colspan="4"><u>Suggested Supplier:</u></th></tr>
              <tr>
                    <td><label for="vendor_companyname" id="vendorname" class="control-label">Name:<font color='Red'>*</font> </label></td>
                    <td>
                        <select name="vendor_companyname" id="vendorname"class="my_dropdown">
                        <option selected="" disabled="" value="" style="width: 100% ;">-------------------Select-----------------</option>
                        <?php
                           foreach ($ven as $row) {      
                        ?>
                        <option value="<?php echo $row->vendor_id ?>"><?php echo $row->vendor_companyname ?></option>
                        <?php
                        }
                        ?>
                        </select>
                    </td>
                    <td><label for="vendor_address" id="address" class="control-label">Address:<font color='Red'>*</font> </label></td>
                    <td>
                        <select name="vendor_address" class="my_dropdown">
                        <option selected="" disabled="" value="" style="width: 100% ;">-------------------Select-----------------</option>
                        <?php
                           foreach ($ven as $row) {      
                        ?>
                        <option value="<?php echo $row->vendor_id ?>"><?php echo $row->vendor_address ?></option>
                        <?php
                        }
                        ?>
                        </select>
                    </td>
                </tr>
            </table>


            <table class="TFtable">
                <tr>
                    <td><label for="fp_typeofpurch" class="control-label">Delivery Period: </label></td>
                    
                    <td>
                        <input type="text" name="DateFrom" id="DateFrom" value="<?php echo isset($_POST["DateFrom"]) ? $_POST["DateFrom"] : ''; ?>"  
                        size="30" placeholder="From Date">&nbsp;&nbsp;
                        <input type="text" name="DateTo" id="DateTo" value="<?php echo isset($_POST["DateTo"]) ? $_POST["DateTo"] : ''; ?>"  
                        size="30" placeholder="To Date">
                    </td>
                </tr>
                <tr >
                    <td><label for="pp_warranty">Warranty:</label></td>
                    <td>
                        <textarea name="pp_warranty" style="width:650px;height:100px;" placeholder="Give Warranty/Guarantee Descriptions here..."></textarea>
                    </td>
                </tr>
                <tr >
                    <td><label for="pp_guarantee">Guarantee:</label></td>
                    <td>
                        <textarea name="pp_guarantee" style="width:650px;height:100px;" placeholder="Give Warranty/Guarantee Descriptions here..."></textarea>
                    </td>
                </tr>
                <tr>
                    <td><label for="pp_payterm" class="control-label">Payment Terms: </label></td>
                    <td>
                         <textarea name="pp_payterm" style="width:650px;height:100px;" placeholder="Give Payment terms here..."></textarea>
                    </td>
                </tr>
            </table>    

        </table> 

        <table class="TFtable">
            <tr>
                <td>
                <input type="checkbox" name="" value="" id="" checked="" style="width:18px;height:18px;"/ >1. Certified that the goods/services intended to be purchased (as above) is/are not distributed through Central Store & Purchase Section.
                </td>
            </tr>  
             <tr>
                <td>
                <input type="checkbox" name="" value="" id="" checked="" style="width:18px;height:18px;"/>2. Certified that the Allocation exists for the above amount.
                </td>
            </tr>  
        </table>
        <br>
                <tr>
                </tr>
                <tr>
                <td></td>
                <td>
                <button name="pp_form">Submit Form</button>
                </td>
           		</tr>

			
    </form>

        <br>
</body>
<p>&nbsp;</p>
    <div align=left> <?php $this->load->view('template/footer');?></div>
</html>

