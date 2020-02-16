<!--@name add_recmethddata.php  @author Nagendra Kumar Singh(nksinghiitk@gmail.com) -->
 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>
 <html>
    <title>Staff Student Guided Details</title>
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
                        echo anchor('report/performance_profile/stuguided/'.$this->emp_id, 'View Profile ', array('class' => 'top_parent'));
                    }
                    echo "</td>";
            
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Add Staff Student Guided Details</b>";
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
            <form id="myform" action="<?php echo site_url('empmgmt/add_stuguidata/'.$this->emp_id);?>" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="empid" value="<?php echo  $this->emp_id ; ?>">
            <table style="width:100%; border:1px solid gray;" align="center" class="TFtable">
                <tr><thead><th  style="color:white;background-color:#0099CC; text-align:left; height:30px;" colspan=63">&nbsp;&nbsp; Add Staff Student Guided Details</th></thead></tr>
                <tr></tr><tr></tr>
		<tr>
			<td>Degree<font color='Red'>*</font></td>
                        <td><select id="degree" name="degree" required style="width:350px;">
                        <option selected="selected" disabled selected>------------- Degree -----</option>
                        <option value="M.V.Sc.">M.V.Sc.</option>
                        <option value="M.Sc.">M.Sc.</option>
                        <option value="M.Tech">M.Tech</option>
                        <option value="M.Phil.">M.Phil.</option>
                        <option value="PhD">PhD</option>
                        <option value="PG Diploma">PG Diploma</option>
                        <option value="Others">Others</option>
                    </select>
                </td>
                </tr>
                <tr>
                    <td>Discipline<font color='Red'></font></td>
		    <td>
                            <input type="text" name="discipline" id="discipline" value="" size="40" >
                    </td>
                </tr>
                <tr>
                    <td>Student Name<font color='Red'></font></td>
		    <td>
                            <input type="text" name="stuname" id="stuname" value="" size="40" >
                    </td>
                </tr>
                <tr>
                    <td>Year<font color='Red'></font></td>
		    <td>
                            <input type="text" name="year" id="year" value="" size="40" >
                    </td>
                </tr>
                <tr>
                    <td>College/university<font color='Red'></font></td>
		    <td>
                            <input type="text" name="college" id="college" value="" size="40" >
                    </td>
                </tr>

	<tr>
                <td>Role<font color='Red'></font></td>
                <td><select name="role" style="width:350px;" id="role" >
		<option selected="selected" disabled selected>------------ Role---------</option>
                        <option value="Chairman">Chairman</option>
                        <option value="Member">Member</option>
                </select>
                </td>
        </tr>
                <tr></tr><tr></tr>
                <tr style="color:white;background-color:#0099CC; text-align:left; height:30px;">
                    <td colspan="3">
                    <button name="addstuguidata" >Submit</button>
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
   
