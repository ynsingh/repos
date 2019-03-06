 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>
 <html>
    <title>Update Departmental Exam Details</title>
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
			$empcode=$deptexamdata->sdep_empcode;
			$empid=$this->sismodel->get_listspfic1('employee_master','emp_id','emp_code',$empcode)->emp_id;
                        echo anchor('report/deptexam_profile/'.$empid, 'View Departmental Exam Profile ', array('class' => 'top_parent'));
                    }
                    echo "</td>";
            
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Edit Departmental Exam Details</b>";
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
            <form id="myform" action="<?php echo site_url('empmgmt/update_deptexamdata/'.$id);?>" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="deid" value="<?php echo  $id ; ?>">
            <table style="width:100%; border:1px solid gray;" align="center" class="TFtable">
                <tr><thead><th  style="color:white;background-color:#0099CC; text-align:left; height:30px;" colspan=63">&nbsp;&nbsp; Update Departmental Exam Details</th></thead></tr>
                <tr></tr><tr></tr>
                <tr>
                        <td>Departmental Exam<font color='Red'>*</font></td>
                        <td><select id="deptexam" name="deptexam" required style="width:350px;">
<?php			if(!empty($deptexamdata->sdep_examname)) { ?>
			<option value="<?php echo $deptexamdata->sdep_examname;?>"><?php echo $deptexamdata->sdep_examname ; ?></option>
<?php                   } ?>
                        <option>------------- Departmental Exam -------------</option>
                        <option value="ATS Part I">ATS Part I</option>
                        <option value="ATS Part II">ATS Part II</option>
                        <option value="Accountancy Lower">Accountancy Lower</option>
                        <option value="Accountancy Higher">Accountancy Higher</option>
                        <option value="Others">Others</option>
                    </select>
                </td>
                </tr>
                <tr>
                    <td>Specify<font color='Red'></font></td>
                    <td>
                            <input type="text" name="specify" id="specify" value="<?php echo $deptexamdata->sdep_specification; ?>" size="40">
                    </td>
                </tr>
                <tr>
                    <td>Date Of Passing<font color='Red'>*</font></td>
                        <td><input type="text" name="Datefrom" id="Datefrom" value="<?php echo $deptexamdata->sdep_passdate; ?>"  size="40" required="required" >
                    </td>
                </tr>
                <tr></tr><tr></tr>
                <tr style="color:white;background-color:#0099CC; text-align:left; height:30px;">
                    <td colspan="3">
                    <button name="editdeptexamdata" >Submit</button>
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
   
