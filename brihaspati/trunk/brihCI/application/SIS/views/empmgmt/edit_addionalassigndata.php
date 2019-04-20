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
                        $( "#asignother" ).hide();
                        $('#asigndatefrom,#asigndateto').datepicker({
                            dateFormat: 'yy/mm/dd',
                            autoclose:true,
                            changeMonth: true,
                            changeYear: true,
                            yearRange: 'c-70:c+20',
               
                        }).on('changeDate', function (ev) {
                            $(this).datepicker('hide');
                        });
                
                        $('#asignname').on('change',function(){
                                var recmthd = $(this).val();
                                if(recmthd == 'Others'){
                                        $( "#asignother" ).show();
                                }
                                else{
                                        $( "#asignother" ).hide();
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
			$empid=$addassigndata->aa_empid;
//			$empid=$this->sismodel->get_listspfic1('employee_master','emp_id','emp_code',$empcode)->emp_id;
                        echo anchor('report/addionalassign_profile/'.$empid, 'View Addional Assignment Details ', array('class' => 'top_parent'));
                    }
                    echo "</td>";
            
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Edit Addional Assignment Details</b>";
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
            <form id="myform" action="<?php echo site_url('empmgmt/update_addionalassign/'.$id);?>" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="deid" value="<?php echo  $id ; ?>">
            <table style="width:100%; border:1px solid gray;" align="center" class="TFtable">
                <tr><thead><th  style="color:white;background-color:#0099CC; text-align:left; height:30px;" colspan=63">&nbsp;&nbsp; Update Addional Assignment Details</th></thead></tr>
                <tr></tr><tr></tr>

		 <tr>
              <td><label for="asignname" style="font-size:20px;"><font color=''>Name of the Assignment</font> </label>
                </td><td>
                <div>
                    <select name="asignname" id="asignname" style="width:300px;">
<?php                   if(!empty($addassigndata->aa_asigname)) { ?>
                        <option value="<?php echo $addassigndata->aa_asigname ;?>"><?php echo $addassigndata->aa_asigname  ; ?></option>
<?php                   } ?>

                        <option value="">--------- Select Assignment Name --------</option>
                        <option value="warden">Warden</option>
                        <option value="Deputy Warden">Deputy Warden</option>
                        <option value="Guest House Incharge">Guest House Incharge</option>
                        <option value="NSS Corordinator">NSS Corordinator</option>
                        <option value="NSS Program Officer">NSS Program Officer</option>
                        <option value="NCC Officer">NCC Officer</option>
                        <option value="Resident Veterinary Officer">Resident Veterinary Officer</option>
                        <option value="Canteen Incharge">Canteen Incharge</option>
                        <option value="Others">Others</option>
                    </select>
                </div></td>
                <td><input type="text" name="asignother" id="asignother" class="keyup-characters" value="<?php echo isset($_POST["asignother"]) ? $_POST["asignother"] : ''; ?>" placeholder="Others........" size="33" >
                </td>
            </tr>
            <tr>
                <td><label for="asignplace" style="font-size:20px;"><font color=''>Assignment Place</font></label>
                </td><td>
                <div><input type="text" name="asignplace" class="keyup-characters" value="<?php echo $addassigndata->aa_place; ?>" placeholder="Place........" size="33" >
                </div></td>
            </tr>
            <tr>

                <td><label for="asigndatefrom" style="font-size:20px;"><font color=''>Date From</font></label>
                </td><td>
                <div><input type="text" name="asigndatefrom" id="asigndatefrom" value="<?php echo $addassigndata->aa_asigperiodfrom; ?>" placeholder="Date From........" size="33" >
                </div></td>
            </tr>
            <tr>
                <td><label for="asigndateto" style="font-size:20px;"><font color=''>Date To</font></label>
                </td><td>
                <div><input type="text" name="asigndateto" id="asigndateto" value="<?php echo $addassigndata->aa_asigperiodto; ?>" placeholder="Date To ........" size="33" >
                </div></td>
            </tr>			

                <tr></tr><tr></tr>
                <tr style="color:white;background-color:#0099CC; text-align:left; height:30px;">
                    <td colspan="3">
                    	<button name="editaddionaldata" >Submit</button>
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
   
