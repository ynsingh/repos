<!--@name add_servicedata.php  @author Manorama Pal(palseema30@gmail.com) -->
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
                        echo anchor('report/disciplin_profile/'.$this->emp_id, 'View Profile ', array('class' => 'top_parent'));
                    }
                    echo "</td>";
            
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Add Disciplinary Action Details</b>";
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
            <form id="myform" action="<?php echo site_url('empmgmt/add_disciplindata/'.$this->emp_id);?>" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="empid" value="<?php echo  $this->emp_id ; ?>">
            <table style="width:100%; border:1px solid gray;" align="center" class="TFtable">
                <tr><thead><th  style="color:white;background-color:#0099CC; text-align:left; height:30px;" colspan=63">&nbsp;&nbsp; Add Disciplinary Action Details</th></thead></tr>
                <tr></tr><tr></tr>
                <tr>
			<td>Nature of Punishment<font color='Red'>*</font></td>
                        <td><select id="punishtypeid" name="punishtype" required style="width:350px;">
                        <option selected="selected" disabled selected>------------- Punishment Type -------------</option>
                        <option value="Censure">Censure</option>
                        <option value="Memo">Memo</option>
                        <option value="Suspension">Suspension</option>
                        <option value="Dismissal">Dismissal</option>
                        <option value="Removal from Service">Removal from Service</option>
                        <option value="Increment withheld">Increment withheld</option>
                    </select>
                </td>
                </tr>
                <tr>
                    <td>Reason<font color='Red'></font></td>

		    <td>
                            <input type="text" name="reason" id="reason" value="<?php //echo isset($_POST["gradename"]) ? $_POST["gradename"] : ''; ?>" size="40">
                    </td>
                </tr>
                <tr>
                    <td>Date of Issuing order<font color='Red'>*</font></td>
                        <td><input type="text" name="Datefrom" id="Datefrom" value="<?php echo isset($_POST["Datefrom"]) ? $_POST["Datefrom"] : ''; ?>"  size="40" required="required" >
                    </td>
                </tr>
                <tr>
                    <td>Date of Revoking the order<font color='Red'></font></td>
                        <td><input type="text" name="Dateto" id="Dateto" value="<?php echo isset($_POST["Dateto"]) ? $_POST["Dateto"] : ''; ?>"  size="40" >
                    </td>   
                </tr>
                <tr>
                    <td>Remarks<font color='Red'></font></td>

		    <td>
                            <input type="text" name="status" id="status" value="<?php //echo isset($_POST["gradename"]) ? $_POST["gradename"] : ''; ?>" size="40" >
                    </td>
                </tr>
                <tr></tr><tr></tr>
                <tr style="color:white;background-color:#0099CC; text-align:left; height:30px;">
                    <td colspan="3">
                    <button name="addservdata" >Submit</button>
		    <!--input type="reset" name="Reset" value="Clear"/-->
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
   
