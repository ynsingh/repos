 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>
 <html>
    <title>Update Recruitment Method Details</title>
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

                	$('#recmthdid').on('change',function(){
	                	var recmthd = $(this).val();
               // alert("redioval===="+redioval);
	                	if(recmthd == 'Promotion'){
        	            		$('#dsubgrpid,#grpdetails,#compname,#desig,#dept').prop('disabled',true);
                		}
                		else{
                    			$('#dsubgrpid,#grpdetails').prop('disabled',false);
                		}
            		});

                	$('#dsubgrpid').on('change',function(){
                		var subgrpn = $(this).val();
               // alert("redioval===="+redioval);
                		if(subgrpn == 'Compassionate'){
                    			$('#compname,#desig,#dept').prop('disabled',false);
                		}
                		else{
                    			$('#compname,#desig,#dept').prop('disabled',true);
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
			$empcode=$recmthdata->srp_empcode;
			$empid=$this->sismodel->get_listspfic1('employee_master','emp_id','emp_code',$empcode)->emp_id;
                        echo anchor('report/recruit_profile/'.$empid, 'View Recruitment Method Details ', array('class' => 'top_parent'));
                    }
                    echo "</td>";
            
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Edit Recruitment Method Details</b>";
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
            <form id="myform" action="<?php echo site_url('empmgmt/update_recmethdedata/'.$id);?>" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="recmthid" value="<?php echo  $id ; ?>">
            <table style="width:100%; border:1px solid gray;" align="center" class="TFtable">
                <tr><thead><th  style="color:white;background-color:#0099CC; text-align:left; height:30px;" colspan=63">&nbsp;&nbsp; Update  Recruitment Method Details</th></thead></tr>
                <tr></tr><tr></tr>
			
		<tr>
                        <td>Method Of Recruitment<font color='Red'>*</font></td>
                        <td><select id="recmthdid" name="recmthd" required style="width:350px;">
			<?php   if(!empty($recmthdata->srp_methodrecrtmnt)) { ?>
	                        <option value="<?php echo $recmthdata->srp_methodrecrtmnt ;?>"><?php echo $recmthdata->srp_methodrecrtmnt  ; ?></option>
                        <?php                   }else{ ?>
                        	<option selected="selected" disabled selected>------------- Method Of Recruitment -------------</option>
                        <?php                   } ?>

                        <option value="Direct">Direct</option>
                        <option value="Promotion">Promotion</option>
                    </select>
                </td>
        <tr>
                <td>Sub Group<font color='Red'></font></td>
                <td><select name="dsubgrp" style="width:350px;" id="dsubgrpid" >
		<?php   if(!empty($recmthdata->srp_subcategory)) { ?>
	                        <option value="<?php echo $recmthdata->srp_subcategory ;?>"><?php echo $recmthdata->srp_subcategory; ?></option>
                        <?php                   }else{ ?>
                		<option selected="selected" disabled selected>------------ Select Sub Group ---------</option>
                        <?php                   } ?>

                        <option value="Open Advertisement">Open Advertisement</option>
                        <option value="Through Employeement Exchange">Through Employeement Exchange</option>
                        <option value="Compassionate">Compassionate</option>
                        <option value="Land Acquisition">Land Acquisition</option>
                </select>
                </td>
        </tr>
                <tr>
                    <td>Sub Group Details<font color='Red'></font></td>
                    <td>
                            <input type="text" name="grpdetails" id="grpdetails" value="<?php echo $recmthdata->srp_detail; ?>" size="40" >
                    </td>
                </tr>
                <tr>
                    <td>Compassion Name<font color='Red'></font></td>

                    <td>
                            <input type="text" name="compname" id="compname" value="<?php echo $recmthdata->srp_compassionname; ?>" size="40" >
                    </td>
                </tr>
		 <tr>
                    <td>Designation<font color='Red'></font></td>

                    <td>
                            <input type="text" name="desig" id="desig" value="<?php echo $recmthdata->srp_compassiondesig; ?>" size="40" >
                    </td>
                </tr>
                <tr>
                    <td>Department<font color='Red'></font></td>

                    <td>
                            <input type="text" name="dept" id="dept" value="<?php echo $recmthdata->srp_compassiondept; ?>" size="40" >
                    </td>
                </tr>

                <tr></tr><tr></tr>
                <tr style="color:white;background-color:#0099CC; text-align:left; height:30px;">
                    <td colspan="3">
                    	<button name="editrecmthdata" >Submit</button>
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
   
