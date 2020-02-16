<!--@name add_recmethddata.php  @author Nagendra Kumar Singh(nksinghiitk@gmail.com) -->
 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>
 <html>
    <title>Staff Project Details</title>
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

//		 $('#prgtype').on('change',function(){
  //              var recmthd = $(this).val();
    //           // alert("redioval===="+redioval);
      //          if(recmthd != 'Training'){
        //            $('#dsubgrpid').prop('disabled',true);
          //      }
            //    else{
              //      $('#dsubgrpid').prop('disabled',false);
               // }
//            });


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
                        echo anchor('report/performance_profile/projects/'.$empprojdata->sppd_empid, 'View Profile ', array('class' => 'top_parent'));
                    }
                    echo "</td>";
            
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Update Staff Projects Details</b>";
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
            <form id="myform" action="<?php echo site_url('empmgmt/update_projdata/'.$id);?>" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="empid" value="<?php echo  $empprojdata->sppd_empid ; ?>">
            <table style="width:100%; border:1px solid gray;" align="center" class="TFtable">
                <tr><thead><th  style="color:white;background-color:#0099CC; text-align:left; height:30px;" colspan=63">&nbsp;&nbsp; Update Staff Project Details</th></thead></tr>
                <tr></tr><tr></tr>
                <tr>
                    <td>Title Of Project<font color='Red'>*</font></td>
		    <td>
                            <input type="text" name="projtitle" id="projtitle" value="<?php echo $empprojdata->sppd_ptitle;?>" size="40" required>
                    </td>
                </tr>
                <tr>
                    <td>Role In Project<font color='Red'></font></td>
		    <td>
                            <input type="text" name="role" id="role" value="<?php echo $empprojdata->sppd_prole;?>" size="40" >
                    </td>
                </tr>
                <tr>
                    <td>Funding Agency Name<font color='Red'></font></td>
		    <td>
                            <input type="text" name="fundname" id="fundname" value="<?php echo $empprojdata->sppd_pfundagency;?>" size="40" >
                    </td>
                </tr>
		<tr>
			<td>Funding Agency Type<font color='Red'></font></td>
                        <td><select id="fundtype" name="fundtype"  style="width:350px;">
			<?php if(!empty($empprojdata->sppd_agendytype)):;?>
                            <option value="<?php echo $empprojdata->sppd_agendytype;?>"><?php echo $empprojdata->sppd_agendytype;?></option>
                            <?php else:?>
                        <option selected="selected" disabled selected>-------------Funding Agency Type ---------</option>
                            <?php endif;?>
                        <option value="DBT">DBT</option>
                        <option value="ICAR">ICAR</option>
                        <option value="DST">DST</option>
                        <option value="GOI">GOI</option>
                        <option value="ICMR">ICMR</option>
                        <option value="TNGOVT">TNGOVT</option>
                        <option value="TNSCST">TNSCST</option>
                    </select>
                </td>
                </tr>
                <tr>
                    <td>Budget (in Lakhs)<font color='Red'></font></td>
		    <td>
                            <input type="text" name="budget" id="budget" value="<?php echo $empprojdata->sppd_budget;?>" size="40" >
                    </td>
                </tr>
                <tr>
                    <td>Duration in Years<font color='Red'></font></td>

		    <td>
                            <input type="text" name="duration" id="duration" value="<?php echo $empprojdata->sppd_duration;?>" size="40" >
                    </td>
                </tr>
                <tr>
                    <td>From Date<font color='Red'></font></td>
		    <td>
                            <input type="text" name="fdate" id="fdate" value="<?php echo $empprojdata->sppd_fromdate;?>" size="40" readonly>
                    </td>
                </tr>
                <tr>
                    <td>To date<font color='Red'></font></td>
		    <td>
                            <input type="text" name="todate" id="todate" value="<?php echo $empprojdata->sppd_todate;?>" size="40" readonly>
                    </td>
                </tr>
                <tr>
                    <td>Remarks<font color='Red'></font></td>
		    <td>
                            <input type="text" name="remark" id="remark" value="<?php echo $empprojdata->sppd_remark;?>" size="40" >
                    </td>
                </tr>
                <tr></tr><tr></tr>
                <tr style="color:white;background-color:#0099CC; text-align:left; height:30px;">
                    <td colspan="3">
                    <button name="editprojdata" >Submit</button>
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
   
