<!--@name add_rentrecovery.php  @author Manorama Pal(palseema30@gmail.com) -->
 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>
 <html>
    <title>Salary Head</title>
    <head>
       <!--<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script> -->
        <?php $this->load->view('template/header'); ?>
        
        <script> 
        </script>
    </head>
    <body>
        <table width="100%">
            <tr>
                <?php
                    echo "<td align=\"left\" width=\"33%\">";
                    echo anchor('setup3/rentrecovery/', "View Rent Recovery Percentage for Govt.Quarters" ,array('title' => 'View Rent Recovery Percentage for Govt.Quarters' , 'class' => 'top_parent'));
                    echo "</td>";
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Add Rent Recovery percentage for Govt.Quarters</b>";
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
        <form action="<?php echo site_url('setup3/add_rentrecovery');?>" method="POST" enctype="multipart/form-data">
            <table>
                <tr>
                	<td><label for="worktype" class="control-label">Working Type:</label></td>
                        <td>
                            <select name="worktype" id="worktype" class="my_dropdown" style="width:100%;">
                		<option value="" disabled selected >------Select ---------------</option>
                		<option value="Teaching">Teaching</option>
                		<option value="Non Teaching">Non Teaching</option>
			    </select>
                        </td>
	     	</tr>
		<tr>
                        <td><label for="paycomm" class="control-label">Pay Commission:</label></td>
                        <td>
                            <select name="paycomm" id="paycomm" class="my_dropdown" style="width:100%;">
                                <option value="" disabled selected >------Select ---------------</option>
                                <option value="6th">6th</option>
                                <option value="7th">7th</option>
                            </select>
                        </td>
                </tr>
                <tr>
                	<td><label for="payscale" class="control-label">Pay Scale:</label></td>
                        <td>
                            <select name="payscale" id="payscale" class="my_dropdown" style="width:100%;">
                		<option value="" disabled selected >------Select Pay Scale ---------------</option>
                                <?php foreach($this->salgrade as $sgdata): ?>	
   				<option value="<?php echo $sgdata->sgm_id; ?>"><?php echo $sgdata->sgm_name."( ".$sgdata->sgm_min." - ".$sgdata->sgm_max." )".$sgdata->sgm_gradepay; ?></option> 
                                <?php endforeach; ?>
                            </select>
			    </select>
                        </td>
	     	</tr>
		 <tr>
                    <td><label for="payrange" class="control-label">Pay Range:</label></td>
                    <td><input type="text" name="payrange" value="<?php echo isset($_POST["payrange"]) ? $_POST["payrange"] : ''; ?>" placeholder="Pay Range ( min - max)" size="30" /></td>
                </tr>
 
                <tr>
                	<td><label for="hragrade" class="control-label">HRA Grade:</label></td>
                        <td>
                            <select name="hragrade" id="hragrade" class="my_dropdown" style="width:100%;">
                		<option value="" disabled selected >------Select HRA Grade -------</option>
                                <?php foreach($this->hragrade as $hgcdata): ?>	
   				<option value="<?php echo $hgcdata->hgc_id; ?>"><?php echo $hgcdata->hgc_gradename;?></option> 
                                <?php endforeach; ?>
                            </select>
			    </select>
                        </td>
	     	</tr>
                <tr>
                    <td><label for="percentage" class="control-label">Rent Recovery Percentage(in %):</label></td>
                    <td><input type="text" name="percentage" value="<?php echo isset($_POST["percentage"]) ? $_POST["percentage"] : ''; ?>" placeholder="Rent Recovery Percentage..." size="30" /></td>
                </tr>
                <tr>
                    <td><label for="description" class="control-label">Description:</label></td>
                    <td><input type="text" name="Description" value="<?php echo isset($_POST["Description"]) ? $_POST["Description"] : ''; ?>" placeholder="Rent Recovery Description..." size="30" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                    <button name="add_rentrecovery">Submit</button>
                    <input type="reset" name="Reset" value="Clear"/>
                    </td>
                </tr>
            </table>    
        </form>
        <p> &nbsp; </p>
        <div align="center"> <?php $this->load->view('template/footer');?></div>
    </body>    
</html>    
