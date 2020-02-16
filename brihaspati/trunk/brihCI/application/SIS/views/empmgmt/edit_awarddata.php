<!--@name add_recmethddata.php  @author Nagendra Kumar Singh(nksinghiitk@gmail.com) -->
 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>
 <html>
    <title>Staff Award Details</title>
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

	//	 $('#prgtype').on('change',function(){
          //      var recmthd = $(this).val();
            //   // alert("redioval===="+redioval);
              //  if(recmthd != 'Training'){
                //    $('#dsubgrpid').prop('disabled',true);
//                }
  //              else{
    //                $('#dsubgrpid').prop('disabled',false);
      //          }
        //    });


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
                        echo anchor('report/performance_profile/awards/'.$empawarddata->spad_empid, 'View Profile ', array('class' => 'top_parent'));
                    }
                    echo "</td>";
            
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Update Staff Award Details</b>";
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
            <form id="myform" action="<?php echo site_url('empmgmt/update_awarddata/'.$id);?>" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="empid" value="<?php echo  $empawarddata->spad_empid ; ?>">
            <table style="width:100%; border:1px solid gray;" align="center" class="TFtable">
                <tr><thead><th  style="color:white;background-color:#0099CC; text-align:left; height:30px;" colspan=63">&nbsp;&nbsp; Update Staff Award Details</th></thead></tr>
                <tr></tr><tr></tr>
		<tr>
			<td>Type Of Award<font color='Red'>*</font></td>
                        <td><select id="awardtype" name="awardtype" required style="width:350px;">
			<?php if(!empty($empawarddata->spad_awardtype)):;?>
                            <option value="<?php echo $empawarddata->spad_awardtype;?>"><?php echo $empawarddata->spad_awardtype;?></option>
                            <?php else:?>
                        <option selected="selected" disabled selected>------------- Type Of Award -----</option>
                            <?php endif;?>
                        <option value="Honor">Honor</option>
                        <option value="Award">Award</option>
                        <option value="Medal">Medal</option>
                        <option value="Fellowship">Fellowship</option>
                        <option value="Associateship">Associateship</option>
                    </select>
                </td>
                </tr>
                <tr>
                    <td>Title Of Award<font color='Red'></font></td>
		    <td>
                            <input type="text" name="awardtitle" id="awardtitle" value="<?php echo $empawarddata->spad_awardtitle ;?>" size="40" >
                    </td>
                </tr>

	<tr>
                <td>Awarded By<font color='Red'></font></td>
                <td><select name="awardedby" style="width:350px;" id="dsubgrpid" >
		<?php if(!empty($empawarddata->spad_awardby)):;?>
                            <option value="<?php echo $empawarddata->spad_awardby;?>"><?php echo $empawarddata->spad_awardby;?></option>
                            <?php else:?>
		<option selected="selected" disabled selected>------------ Awarded By---------</option>
                            <?php endif;?>
                        <option value="College">College</option>
                        <option value="University">University</option>
                        <option value="State">State</option>
                        <option value="National">National</option>
                        <option value="International">International</option>
                        <option value="Professional Body">Professional Body</option>
                </select>
                </td>
        </tr>
                <tr>
                    <td>Year<font color='Red'></font></td>

		    <td>
                            <input type="text" name="year" id="year" value="<?php echo $empawarddata->spad_year ;?>" size="40" >
                    </td>
                </tr>
                <tr>
                    <td>Details<font color='Red'></font></td>
		    <td>
                            <input type="text" name="details" id="details" value="<?php echo $empawarddata->spad_details ;?>" size="40" >
                    </td>
                </tr>
                <tr></tr><tr></tr>
                <tr style="color:white;background-color:#0099CC; text-align:left; height:30px;">
                    <td colspan="3">
                    <button name="editawarddata" >Submit</button>
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
   
