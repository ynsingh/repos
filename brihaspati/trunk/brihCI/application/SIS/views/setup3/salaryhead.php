
<!--@name salaryhead.php  @author Manorama Pal(palseema30@gmail.com) -->

 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>
 <html>
    <title>Salary Head</title>
    <head>
        <?php $this->load->view('template/header'); ?>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/datepicker/jquery-ui.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-ui.js" ></script>    
    </head>
    <script>
        $(document).ready(function(){
		$('#tnt').on('change',function(){
                var sht = $(this).val();
                if((sht == 'D')||(sht == 'L')){
			$( "#caltyp" ).hide();			
			$( "#taxble" ).hide();			
                }
		else{
			$( "#caltyp" ).show();			
			$( "#taxble" ).show();			
		}
            });

            $("#btnUpload").on('click',function(){
                var emptype= $('#emptnt').val(); 
                alert("emptyy==="+emptype);
                if( emptype === null || emptype === ''){
                    alert("Please select Employee type..!!");
                    return false;
                } 
               
            });
        });    
    </script>         
    <body><table width="100%">
            <tr>
                <?php
                    echo "<td align=\"left\" width=\"33%\">";
                    echo anchor('setup3/salaryhead_list/', "View Salary Head" ,array('title' => 'View salary head ' , 'class' => 'top_parent'));
                    echo "</td>";
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Add Salary Head</b>";
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
               <?php
                if((isset($_SESSION['success'])) && ($_SESSION['success'])!=''){
                    echo "<div  class=\"isa_success\">";
                    echo $_SESSION['success'];
                    echo "</div>";
                }
                if((isset($_SESSION['err_message'])) && (($_SESSION['err_message'])!='')){
                    echo "<div  class=\"isa_error\">";
                    echo $_SESSION['err_message'];
                    echo "</div>";
                }
                ;?>
                
            </div>
            </td></tr>
        </table>
        <div>
            <form action="<?php echo site_url('setup3/salaryhead');?>" method="POST" enctype="multipart/form-data">
            <table>
                <tr>
                	<td><label for="salh_type" class="control-label">Salary Head Type:</label></td>
                        <td>
                            <select name="salh_type" id="tnt" class="my_dropdown" style="width:100%;">
                		<option value="" disabled selected >------Select Salary Head type----------------</option>
                		<option value="I">Salary Earning Heads</option>
                		<option value="D">Salary Deduction-Subscription Heads</option>
                		<option value="L">Salary Loan Heads</option>
			    </select>
                        </td>
	     	</tr>
                <tr>
                    <td><label for="salhtnt" class="control-label">Post Type:</label></td>
                    <td>
                        <select name="salhtnt" id="emptnt" class="my_dropdown" style="width:100%;">
                        <option value="" disabled selected >------Select ---------------</option>
                	<option value="Common">Common</option>
                	<option value="Teaching">Teaching</option>
                	<option value="Non Teaching">Non Teaching</option>
			</select>
                    </td>
	     	</tr>
                <tr>
                	<td><label for="salh_code" class="control-label">Salary Head Code:</label></td>
                	<td><input type="text" name="salh_code" value="<?php echo isset($_POST["salh_code"]) ? $_POST["salh_code"] : ''; ?>" class="form-control" size="40" /><br></td>
	     	</tr>
                <tr>
                	<td><label for="salh_name" class="control-label">Salary Head Name:</label></td>
                	<td><input type="text" name="salh_name" value="<?php echo isset($_POST["salh_name"]) ? $_POST["salh_name"] : ''; ?>" class="form-control" size="40" /><br></td>
	     	</tr>
                <tr id="caltyp">
                	<td><label for="salh_caltype" class="control-label">Calculation Type:</label></td>
                        <td>
                            <select name="salh_caltype" id="calt" class="my_dropdown" style="width:100%;">
                		<option value="" disabled selected >------Select Calculation Type----------------</option>
                		<option value="Y">YES</option>
                		<option value="N">NO</option>
			    </select>
                        </td>
	     	</tr>
                <tr id="taxble">
                	<td><label for="salh_tax" class="control-label">Taxable:</label></td>
                        <td>
                            <select name="salh_tax" id="stax" class="my_dropdown" style="width:100%;">
                		<option value="" disabled selected >------Select ----------------</option>
                		<option value="Y">YES</option>
                		<option value="N">NO</option>
			    </select>  
                        </td>
	     	</tr>
               <!-- <tr>
                	<td><label for="salh_cat" class="control-label">Category:</label></td>
                        <td>
                            <select name="salh_cat" id="tnt" class="my_dropdown" style="width:100%;">
                		<option value="" disabled selected >-----Select Category----------------</option>
                                <option value="GS">General Salary</option>
                                <option value="GD">General Deduction</option>
                                <option value="GL">General Loan</option>
			    </select> 
                        </td>
	     	</tr> -->
                <tr>
                	<td><label for="salh_nickname" class="control-label">Salary Head Short Name:</label></td>
                	<td><input type="text" name="salh_nickname" value="<?php echo isset($_POST["salh_nickname"]) ? $_POST["salh_nickname"] : ''; ?>"   class="form-control" size="40" /><br></td>
	     	</tr>
                 <tr>
                	<td><label for="salh_desc" class="control-label">Salary Head Description:</label></td>
                	<td><input type="text" name="salh_desc"  value="<?php echo isset($_POST["salh_desc"]) ? $_POST["salh_desc"] : ''; ?>" class="form-control" size="40" /><br></td>
	     	</tr>
                
                <tr>
                    <td></td>
                    <td>
                    <button name="addsalaryhead" id="btnUpload">Submit</button>
                    <button name="reset" >Clear</button>

                    </td>
            </tr>
                
            </table>
            </form>    
        </div>   
        
        <p> &nbsp; </p>
        <div align="center"> <?php $this->load->view('template/footer');?></div>   
    </body>   
</html>    
