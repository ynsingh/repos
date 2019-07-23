<?php defined('BASEPATH') OR exit('No direct script access allowed');

?>
<html>
<title>Purchase Proposal | Form</title>
 <head>    
        <?php $this->load->view('template/header'); 
              

        ?>

         <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/datepicker/jquery-ui.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
     
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">

       
</head>


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

   

    <form id="myform" action="<?php echo site_url('tender/proposal_entry');?>" method="POST" class="form-inline" autocomplete="OFF" enctype="multipart/form-data">
			<table class="TFtable" >
			    <tr>  <?php  foreach ($tcresult as $t) { ?>
					<td><label for="pp_gemrefno" class="control-label">Tender Ref No.: </label></td>
                	<td>
                	<input type="text" name="pp_refno"  value="<?php echo $t->tc_refno ?>" class="form-control" size="30" placeholder="" /><br>
                	<input type="hidden" name="pp_tid" value="<?php echo $t->tc_id ?>" />
                	</td>
                    <td><label for="pp_ddate">Date:<font color='Red'>*</font></label></td>
                    <td><input type="text" name="pp_ddate" id="Date" value="<?php echo date('d-m-Y'); ?>"  
                        size="30" ></td>
                     <?php } ?>   
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
                    <td colspan="3"><input type="text" name="pp_indentdate" id="IndentDate" value="<?php echo date('d-m-Y'); ?>" size="30" ></td>
                </tr>
                </tr>
                 <th colspan="4">
                    Intender's Details: 
                </th>
            <!--     <?php  foreach ($tcresult as $t) { ?> -->
               
                <tr>
                    <td><label for="pp_indentername" class="control-label">Name: </label></td>
                    <td>
                    <input type="text" name="pp_indentername" class="form-control" size="30" placeholder="Enter Your Name" /><br>  <!-- value="<?php echo $t->tc_tenderprepby  ?>" -->
                    </td>
                    <td><label for="pp_indenterid" class="control-label">PF No.: </label></td>
                    <td>
                    <input type="text" name="pp_indenterid"  class="form-control" size="30" placeholder="Enter PF No." /><br>
                    </td>
                </tr>
                <tr>
                    <td><label for="pp_indenteremail" class="control-label">E-mail ID: </label></td>
                    <td colspan="3">
                    <input type="email" name="pp_indenteremail"  class="form-control" size="30" placeholder="yourname@email.com" /><br>  <!-- value="<?php echo $t->tc_invitngoffemail ?>" -->
                    </td>

                </tr> 
              <!--    <?php } ?> -->
                <th colspan="4">
                    Type of Material:
                </th>   
                <tr>
					<td><label for="fp_authority" class="control-label">Type of Material:<font color='Red'>*</font> </label></td>
                	<td colspan="3">
                        <select name="pp_typeofmaterial" class="my_dropdown">
                     
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
            <th>Complete Description
            <br><font size="2">(Good/Services intended
            <br>to be procured.)</font></th><th>Quantity <br>(for Goods)</th><th>Price</th><th>GST Rate</th><th>Total(Inc. Taxes)</th>
            </thead>
            <tbody>    
                <tr>
                   
                    <td>   
                     <?php  foreach ($tcresult as $t) { ?> 
                      <textarea style="width:430px; height:100px; " style="font-weight:bold; " readonly name="desc"  id="desc" placeholder="Give Details......" ><?php
                      echo 'Tittle:'.$t->tc_workitemtitle.' '.'&'.' '.' ';
                      echo 'Work Description:'.$t->tc_workdesc ;
                       ?>
                    
                       </textarea>
                      
                      <?php
                        }
                        ?>
                    
                    
                    </td>
                 <?php  foreach ($taresult as $tt) { ?> 
                 <input type="hidden" name="pp_taid" value="<?php echo $tt->ta_id ?>" />
                    <td><input type="text" name="quantity" id="quantity" size="10"></td>
                    <td><input type="text" name="price" value="<?php echo $tt->ta_baseprice ?>"    id="price" size="5"></td>
                    <td><input type="text" name="gst"  value="<?php echo $tt->ta_gsttax ?>"     id="gst" size="3"></td>
                    <td><input type="text" name="pp_total"  value="<?php echo $tt->ta_totalprice ?>"    id="total" size="8"></td>
                    
                   
                      <?php
                        }
                        ?>
                </tr>
                
            </tbody>
           
        </table>
       <table class="TFtable">
        
        </table>        
        <table class="TFtable" id="myTable2">
            <tr><td colspan="5"><b><u>Budget Details:</u></b></td></tr>

            <tr>
            
                <th><center>Department/Project No.</center></th><th><center>Budget Head</center></th><th><center>Budget Amount</center></th></tr>
             <tr>
                    <!-- <td><center><input type="text" name="srno_1"  size="3" value="1" readonly></center></td> -->
                    <td><center><input type="text" name="pp_budgetprojno"  size="30" ></center></td>
                    <td><center><input type="text" name="pp_budgethead"  size="25" ></td>
                    <td><center><input type="text" name="pp_budgetamount" size="15" ></center></td>
                   
                </tr>
            
        </table>

        <table class="TFtable">
             <tr><th colspan="4"><u>Supplier:</u></th></tr>
              <tr>   <?php   foreach($result as $w ) { ?>
                     <input type="hidden" name="pp_vid" value="<?php echo $w->vendor_id ?>" />
                    <td><label for="vendor_companyname" id="vendorname" class="control-label">Name:<font color='Red'>*</font> </label></td>
                    <td>
                      <input  type="text"  name="vendor_companyname"  class="form-control" value="<?php echo $w->vendor_companyname ;?>" style="width:200;" />
                    </td>
                    <td><label for="vendor_address" id="address" class="control-label">Address:<font color='Red'>*</font> </label></td>
                    <td>
                      <input  type="text"  name="vendor_address"  class="form-control" value="<?php echo $w->vendor_address ;?>" style="width:200;" />
                    </td>  <?php  } ?>
                </tr>
            </table>


            <table class="TFtable">
                <tr>
                    <td><label for="fp_typeofpurch" class="control-label">Delivery Period: </label></td>
                    
                    <td>  
                        <input type="text" name="pp_Date" id="Date"   
                        size="30" placeholder="Period">&nbsp;&nbsp;
                         FROM:
                        <input type="date" name="pp_DateFrom" id="DateFrom"  
                        size="30" placeholder="From Date">&nbsp;&nbsp;
                         TO:                        
                        <input type="date" name="pp_DateTo" id="DateTo"   
                        size="30" placeholder="To Date">
                    </td>
                </tr>
                <tr >
                    <td><label for="pp_warranty">Warranty:</label></td>
                    <td>
                        <textarea name="pp_warranty" style="width:100%;height:100px;" placeholder="Give Warranty/Guarantee Descriptions here..."></textarea>
                    </td>
                </tr>
                <tr >
                    <td><label for="pp_guarantee">Guarantee:</label></td>
                    <td>
                        <textarea name="pp_guarantee" style="width:100%;height:100px;" placeholder="Give Warranty/Guarantee Descriptions here..."></textarea>
                    </td>
                </tr>
                <tr>
                    <td><label for="pp_payterm" class="control-label">Payment Terms: </label></td>
                    <td>
                         <textarea name="pp_payterm" style="width:100%;height:100px;" placeholder="Give Payment terms here..."></textarea>
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
                <button name="press">Submit Form</button>
                </td>
           		</tr>

			
    </form>

        <br>
</body>
<p>&nbsp;</p>
    <div align=left> <?php $this->load->view('template/footer');?></div>
</html>

