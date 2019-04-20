 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>
 <html>
    <title>Update deputation_profile Details</title>
    <head>
        <?php $this->load->view('template/header'); ?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/datepicker/jquery-ui.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-ui.js" ></script>
	<script>
		$(document).ready(function(){
			$('#Datefrom,#Dateto').datepicker({
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
			$empcode=$deputatdata->sdp_empcode;
			$empid=$this->sismodel->get_listspfic1('employee_master','emp_id','emp_code',$empcode)->emp_id;
                        echo anchor('report/deputation_profile/'.$empid, 'View deputation_profile Details ', array('class' => 'top_parent'));
                    }
                    echo "</td>";
            
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Edit Deputation Profile Details</b>";
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
            <form id="myform" action="<?php echo site_url('empmgmt/update_deputedata/'.$id);?>" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="deid" value="<?php echo  $id ; ?>">
            <table style="width:100%; border:1px solid gray;" align="center" class="TFtable">
                <tr><thead><th  style="color:white;background-color:#0099CC; text-align:left; height:30px;" colspan=63">&nbsp;&nbsp; Update  Deputation Profile Details</th></thead></tr>
                <tr></tr><tr></tr>
	 <tr>
                        <td>Deputation<font color='Red'>*</font></td>
                        <td><select id="deputationid" name="deputation" required style="width:350px;">
			<?php   if(!empty($deputatdata->sdp_deputation)) { ?>
                        <option value="<?php echo $deputatdata->sdp_deputation ;?>"><?php echo $deputatdata->sdp_deputation  ; ?></option>
			<?php                   }else{ ?>

                        <option selected="selected" disabled selected>------------- Deputation -------------</option>
			<?php                   } ?>
                        <option value="GOI">GOI</option>
                        <option value="TN Govt">TN Govt</option>
                        <option value="Other University">Other University</option>
                        <option value="Abroad">Abroad</option>
                    </select>
                </td>

                </tr>
                <tr>
                    <td>Specification<font color='Red'></font></td>

                    <td>
                            <input type="text" name="specify" id="specify" value="<?php echo $deputatdata->sdp_specification; ?>" size="40" >
                    </td>
                </tr>
                <tr>
                    <td>Date From<font color='Red'>*</font></td>
                        <td><input type="text" name="Datefrom" id="Datefrom" value="<?php echo $deputatdata->sdp_fromdate; ?>"  size="40" required="required" >
                    </td>
                </tr>
                <tr>
                    <td>Date To<font color='Red'></font></td>
                        <td><input type="text" name="Dateto" id="Dateto" value="<?php echo $deputatdata->sdp_todate; ?>"  size="40" >
                    </td>
                </tr>
	
                <tr></tr><tr></tr>
                <tr style="color:white;background-color:#0099CC; text-align:left; height:30px;">
                    <td colspan="3">
                    	<button name="editdeputedata" >Submit</button>
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
   
