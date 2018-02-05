<!--@name edit_emptype.php  @author Manorama Pal(palseema30@gmail.com) -->

 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>
 <html>
    <title>Salary Head</title>
    <head>
        <?php $this->load->view('template/header'); ?>
    <script>
        function goBack() {
            window.history.back();
        }
    </script>
    </head>
    <body>
    
        <table width="100%">
            <tr>
                <?php
                    echo "<td align=\"left\" width=\"33%\">";
                    echo anchor('setup3/employeetype_list/', "View Employee Type" ,array('title' => 'View Employee Type' , 'class' => 'top_parent'));
                    echo "</td>";
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Edit Employee Type Details</b>";
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
            <form id="myform" action="<?php echo site_url('setup3/edit_employeetype/'.$id);?>" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="id" value="<?php echo  $id ; ?>">
            <table>
                 <tr>
                	<td><label for="emptype_code" class="control-label">Employee Type Code:</label></td>
                	<td><input type="text" name="emptype_code" value="<?php echo $emptypedata->empt_code; ?>"  class="form-control" size="40" /><br></td>
	     	</tr>
                <tr>
                	<td><label for="emptype_name" class="control-label">Employee Type Name:</label></td>
                        <?php if(($emptypedata->empt_name == 'Temporary')||($emptypedata->empt_name == 'Permanent')||($emptypedata->empt_name == 'Regular') ):;?>
                        <td><input type="text" name="emptype_name" value="<?php echo $emptypedata->empt_name; ?>" readonly class="form-control" size="40" /><br></td>
                	<?php else:?>
                        <td><input type="text" name="emptype_name" value="<?php echo $emptypedata->empt_name; ?>" placeholder=" Employee Type Name" class="form-control" size="40" /><br></td>
                        <?php endif;?>
                </tr>
                <tr>
                	<td><label for="pfapplies" class="control-label">PF Applies:</label></td>
                        <td>
                            <select name="pfapplies" class="my_dropdown" style="width:100%;">
                                <?php if(!empty( $emptypedata->empt_pfapplies)):;?>
                                <option value="<?php echo $emptypedata->empt_pfapplies;?>">
                                     <?php
                                    if($emptypedata->empt_pfapplies == "Y"){
                                        echo "YES";
                                    }
                                    else{
                                        echo "NO";
                                    }
                                    ?>
                                </option>
                                <?php else:?>
                		<option value="" disabled selected >------Select ---------------</option>
                		<?php endif;?>
                                <option value="Y">Yes</option>
                		<option value="N">NO</option>
			    </select>
                        </td>
	     	</tr>
                <tr>
                	<td><label for="maxpf_limit" class="control-label">Max PF Limit:</label></td>
                        <td><input type="text" class="keyup-numeric"  name="maxpf_limit" value="<?php echo $emptypedata->empt_maxpflimit; ?>" class="form-control" size="40" /><br></td>
                        
	     	</tr>
                <tr>
                	<td><label for="emptype_sname" class="control-label">Employee Type Short Name:</label></td>
                	<td><input type="text" name="emptype_sname" value="<?php echo $emptypedata->empt_shortname; ?>"  class="form-control" size="40" /><br></td>
	     	</tr>
               
                <tr>
                    <td></td>
                    <td>
                    <button name="updateemptype">Update</button>
                    </form>    
                    <button onclick="goBack()" >Back</button>

                    </td>
                </tr>
                
            </table>
            
        </div>   
        
        <p> &nbsp; </p>
        <div align="center"> <?php $this->load->view('template/footer');?></div>   
    </body>   
</html>    
