<!--@name employeetype.php  @author Manorama Pal(palseema30@gmail.com) -->
 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>
 <html>
    <title>Salary Head</title>
    <head>
       <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <?php $this->load->view('template/header'); ?>
        
    <script> 
         
        /************************************Accepts only 0 – 9**********************************************/
        $(document).ready(function(){
            $('.keyup-numeric').keyup(function() {
                $('span.error-keyup-1').hide();
                var inputVal = $(this).val();
                var numericReg = /^\d*[0-9](|.\d*[0-9]|,\d*[0-9])?$/;
                if(!numericReg.test(inputVal)) {
                    $(this).after('<span class="error error-keyup-1"><font color="red">Numeric characters only.</font></span>');
                }
            });
         });    
            /************************************ close Accepts only 0 – 9**********************************************/
    </script>
    </head>
    <body><table width="100%">
            <tr>
                <?php
                    echo "<td align=\"left\" width=\"33%\">";
                    echo anchor('setup3/employeetype_list/', "View Employee Type" ,array('title' => 'View employee type ' , 'class' => 'top_parent'));
                    echo "</td>";
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Add Employee Type</b>";
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
            <form action="<?php echo site_url('setup3/employeetype');?>" method="POST" enctype="multipart/form-data">
            <table>
                <tr>
                	<td><label for="emptype_code" class="control-label">Employee Type Code:</label></td>
                	<td><input type="text" name="emptype_code" value="<?php echo isset($_POST["emptype_code"]) ? $_POST["emptype_code"] : ''; ?>" placeholder="Employee Type Name" class="form-control" size="40" /><br></td>
	     	</tr>
                <tr>
                	<td><label for="emptype_name" class="control-label">Employee Type Name:</label></td>
                	<td><input type="text" name="emptype_name" value="<?php echo isset($_POST["emptype_name"]) ? $_POST["emptype_name"] : ''; ?>" placeholder=" Employee Type Name" class="form-control" size="40" /><br></td>
	     	</tr>
                <tr>
                	<td><label for="pfapplies" class="control-label">PF Applies:</label></td>
                        <td>
                            <select name="pfapplies" class="my_dropdown" style="width:100%;">
                		<option value="" disabled selected >------Select ---------------</option>
                		<option value="Y">Yes</option>
                		<option value="N">NO</option>
			    </select>
                        </td>
	     	</tr>
                <tr>
                	<td><label for="maxpf_limit" class="control-label">Max PF Limit:</label></td>
                        <td><input type="text" class="keyup-numeric"  name="maxpf_limit" value="<?php echo isset($_POST["maxpf_limit"]) ? $_POST["maxpf_limit"] : '0'; ?>"   placeholder="PF Max Limit"  class="form-control" size="40" /><br></td>
                        
	     	</tr>
                <tr>
                	<td><label for="emptype_sname" class="control-label">Employee Type Short Name:</label></td>
                	<td><input type="text" name="emptype_sname" value="<?php echo isset($_POST["emptype_sname"]) ? $_POST["emptype_sname"] : ''; ?>"  placeholder=" Short Name"  class="form-control" size="40" /><br></td>
	     	</tr>
               
                <tr>
                    <td></td>
                    <td>
                    <button name="addemptype">Submit</button>
                    <input type="reset" name="Reset" value="Clear"/>
                    
                    </td>
            </tr>
                
            </table>
            </form>    
        </div>   
        
        <p> &nbsp; </p>
        <div align="center"> <?php $this->load->view('template/footer');?></div>   
    </body>   
</html>    