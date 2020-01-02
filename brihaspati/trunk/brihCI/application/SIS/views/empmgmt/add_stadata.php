<!--@name add_recmethddata.php  @author Nagendra Kumar Singh(nksinghiitk@gmail.com) -->
 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>
 <html>
    <title>Staff Training Attended Details</title>
    <head>
        <?php $this->load->view('template/header'); ?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/datepicker/jquery-ui.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-ui.js" ></script>
        <script>
            $(document).ready(function(){

                $('#todate,#fdate').datepicker({
                    dateFormat: 'yy/mm/dd',
                    autoclose:true,
                    changeMonth: true,
                    changeYear: true,
                    yearRange: 'c-70:c+20',
               
                }).on('changeDate', function (ev) {
                    $(this).datepicker('hide');
                });

		 $('#prgtype').on('change',function(){
                var recmthd = $(this).val();
               // alert("redioval===="+redioval);
                if(recmthd != 'Training'){
                    $('#dsubgrpid').prop('disabled',true);
                }
                else{
                    $('#dsubgrpid').prop('disabled',false);
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
                        echo anchor('report/performance_profile/trainingattend/'.$this->emp_id, 'View Profile ', array('class' => 'top_parent'));
                    }
                    echo "</td>";
            
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Add Staff Training Attended Details</b>";
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
            <form id="myform" action="<?php echo site_url('empmgmt/add_stadata/'.$this->emp_id);?>" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="empid" value="<?php echo  $this->emp_id ; ?>">
            <table style="width:100%; border:1px solid gray;" align="center" class="TFtable">
                <tr><thead><th  style="color:white;background-color:#0099CC; text-align:left; height:30px;" colspan=63">&nbsp;&nbsp; Add Staff Training Attended Details</th></thead></tr>
                <tr></tr><tr></tr>
		<tr>
			<td>Type Of Programme<font color='Red'>*</font></td>
                        <td><select id="prgtype" name="prgtype" required style="width:350px;">
                        <option selected="selected" disabled selected>------------- Type Of Programme ---------</option>
                        <option value="Training">Training</option>
                        <option value="Symposium">Symposium</option>
                        <option value="Conference">Conference</option>
                        <option value="Seminar">Seminar</option>
                        <option value="Workshop">Workshop</option>
                        <option value="Meeting">Meeting</option>
                    </select>
                </td>

	<tr>
                <td>Sub Type Of Programme<font color='Red'></font></td>
                <td><select name="dsubgrp" style="width:350px;" id="dsubgrpid" >
		<option selected="selected" disabled selected>------------ Select Sub Type of Programme ---------</option>
                        <option value="CAFT">CAFT</option>
                        <option value="Orientation">Orientation</option>
                        <option value="Refresher Course">Refresher Course</option>
                        <option value="Summer School">Summer School</option>
                        <option value="Winter School">Winter School</option>
                        <option value="Short Course">Short Course</option>
                </select>
                </td>
        </tr>
                <tr>
                    <td>Level Of Programme<font color='Red'></font></td>
		    <td>
			<select id="prglevel" name="prglevel" required style="width:350px;">
                        <option selected="selected" disabled selected>------------- Level Of Programme ---------</option>
                        <option value="National">National</option>
                        <option value="International">International</option>
                    </select>
                    </td>
                </tr>
                <tr>
                    <td>Title Of Programme<font color='Red'></font></td>

		    <td>
                            <input type="text" name="prgtitle" id="prgtitle" value="" size="40" >
                    </td>
                </tr>
                <tr>
                    <td>Duration in Days<font color='Red'></font></td>

		    <td>
                            <input type="text" name="duration" id="duration" value="" size="40" >
                    </td>
                </tr>
                <tr>
                    <td>From Date<font color='Red'></font></td>
		    <td>
                            <input type="text" name="fdate" id="fdate" value="" size="40" readonly>
                    </td>
                </tr>
                <tr>
                    <td>To date<font color='Red'></font></td>
		    <td>
                            <input type="text" name="todate" id="todate" value="" size="40" readonly>
                    </td>
                </tr>
                <tr>
                    <td>Venue<font color='Red'></font></td>
		    <td>
                            <input type="text" name="venue" id="venue" value="" size="40" >
                    </td>
                </tr>
                <tr>
                    <td>Organised By<font color='Red'></font></td>
		    <td>
                            <input type="text" name="oby" id="oby" value="" size="40" >
                    </td>
                </tr>
                <tr>
                    <td>Sponsored By<font color='Red'></font></td>
		    <td>
                            <input type="text" name="sby" id="sby" value="" size="40" >
                    </td>
                </tr>
                <tr></tr><tr></tr>
                <tr style="color:white;background-color:#0099CC; text-align:left; height:30px;">
                    <td colspan="3">
                    <button name="addstadata" >Submit</button>
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
   
