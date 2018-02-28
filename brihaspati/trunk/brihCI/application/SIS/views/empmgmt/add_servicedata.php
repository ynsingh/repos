<!--@name add_servicedata.php  @author Manorama Pal(palseema30@gmail.com) -->
 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>
 <html>
    <title>Service Details</title>
    <head>
        <?php $this->load->view('template/header'); ?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/datepicker/jquery-ui.css">
        <script type="text/javascript" src="<//?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-ui.js" ></script>
        <script>
            $(document).ready(function(){
                $('#DateofAGP,#Datefrom,#Dateto').datepicker({
                    dateFormat: 'yy/mm/dd',
                    autoclose:true,
                    changeMonth: true,
                    changeYear: true,
                    yearRange: 'c-70:c+20',
               
                }).on('changeDate', function (ev) {
                    $(this).datepicker('hide');
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
                    echo "<b>Add Service Details</b>";
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
        <div> 
            <form id="myform" action="<?php echo site_url('empmgmt/add_servicedata/'.$this->emp_id);?>" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="empid" value="<?php echo  $this->emp_id ; ?>">
            <table style="width:100%; border:1px solid gray;" align="center" class="TFtable">
                <tr><thead><th  style="background-color: gray; text-align:left; height:30px;" colspan=63">&nbsp;&nbsp; Add Service Details</th></thead></tr>
                <tr></tr><tr></tr>
                <tr>
                    <td>Campus Name <font color='Red'>*</font></td>
                        <td><select id="camp" style="width:350px;" name="campus" required> 
                            <option selected="selected" disabled selected>--------Campus Name-----</option>
                            <?php foreach($this->campus as $camdata): ?>	
   				<option class="test" value="<?php echo $camdata->sc_code; ?>"><?php echo $camdata->sc_name; ?></option> 
                            <?php endforeach; ?>
                      
                        </select>
                    </td>
                </tr> 
                <tr>
                    <td>Designation<font color='Red'>*</font></td>
                        <td><select name="designation" id="desigid" required style="width:350px;"> 
                            <option selected="selected" disabled selected>------- Select Designation ---------</option>
                            <?php foreach($this->desig as $desigdata): ?>	
                                <option value="<?php echo $desigdata->desig_code; ?>"><?php echo $desigdata->desig_name; ?></option> 
                            <?php endforeach; ?>
                        </select>
                    </td>
                </tr>
                
                <tr>
                    <td>Pay Band<font color='Red'>*</font></td>
                    <td><select name="payband" required style="width:350px;"> 
                        <option selected="selected" disabled selected>------------------ Select Pay Band -------------</option>
                        <?php foreach($this->salgrd as $salgrddata): ?>	
                            <option value="<?php echo $salgrddata->sgm_id; ?>"><?php echo $salgrddata->sgm_name."(". $salgrddata->sgm_min."-".$salgrddata->sgm_max.")".$salgrddata->sgm_gradepay; ?>
                            </option> 
 			<?php endforeach; ?>
                       
                    </select>
                    </td>
                </tr>
                
                <tr>
                    <td>Date of AGP<font color='Red'></font></td>
                        <td><input type="text" name="DateofAGP" id="DateofAGP" value="<?php echo isset($_POST["DateofAGP"]) ? $_POST["DateofAGP"] : ''; ?>"  size="40" >
                    </td>
                </tr>
                <tr>
                    <td>Date From<font color='Red'>*</font></td>
                        <td><input type="text" name="Datefrom" id="Datefrom" value="<?php echo isset($_POST["Datefrom"]) ? $_POST["Datefrom"] : ''; ?>"  size="40" required="required">
                    </td>
                </tr>
                <tr>
                    <td>Date To<font color='Red'>*</font></td>
                        <td><input type="text" name="Dateto" id="Dateto" value="<?php echo isset($_POST["Dateto"]) ? $_POST["Dateto"] : ''; ?>"  size="40" required="required">
                    </td>   
                </tr>
                <tr></tr><tr></tr>
                <tr style="background-color:gray; text-align:left; height:30px;">
                    <td colspan="3">
                    <button name="addservdata" >Submit</button>
                    <button name="reset" >Clear</button>
			<button type="button" onclick="history.back();">Back</button>
                    </td>
                </tr>    
        
            </table>
            </form>
        </div>    
        <p> &nbsp; </p>
        <div align="center"> <?php $this->load->view('template/footer');?></div>
    </body>
</html>    
    
