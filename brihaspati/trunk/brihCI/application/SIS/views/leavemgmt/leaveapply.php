<!--@name leaveapply.php
    @author Shobhit Tiwari(tshobhit206@gmail.com)
    @author Ankur Singh (ankursingh206@gmail.com)
-->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
	<head>
		<title>Leave Type</title>
		<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery.datetimepicker.css"/>
		<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
		<script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.js" ></script>
		<script>
		$(document).ready(function(){
		
	 		$("#mySelect").on('change',function(){
                    	var leaveid = $(this).val();
                   	if(leaveid == ''){
                       		$('#lrmain').prop('disabled',true);
                    	}
                    	else{
                         $('#lrmain').prop('disabled',false);
                        	$.ajax({
                            	url: "<?php echo base_url();?>sisindex.php/leavemgmt/myfunc",
                            	type: "POST",
                            	data: {"leavet" : leaveid},
                            	dataType:"html",
                            	success:function(data){
                               	var ldata=data;
                                $('#lrmain').val(ldata.replace(/\"/g,""));                                
                            },
                            error:function(data){
                                alert("error occur..!!");
                 
                            }
                        });                          
                    }
                });
     });
	
		</script>
	</head>

<body>
<?php $this->load->view('template/header'); ?>
     <table width="100%">
            <tr>
 					 <?php
					 echo "<td align=\"left\" width=\"15%\">";
                echo anchor('leavemgmt/leavestatus/', "View Leave Status", array('title' => 'View Status','class' =>'top_parent'));            
                echo "</td>";
					 echo "<td align=\"center\" width=\"70%\">";
					 echo "<b>Apply leave</b>";
					 echo "</td>";
					 ?>
                <?php
                 echo "<td align=\"right\">";
                 $help_uri = site_url()."/help/helpdoc#Role";
                 echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                 echo "</td>";
                 ?>
                <div  style="width:100%;">
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
					 </div>
                <?php if(isset($_SESSION['success'])){?>
                <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                <?php
                };
                ?>
                <?php if(isset($_SESSION['err_message'])){?>
                <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                <?php
                };
                ?>
            </tr>
     </table>
<br>
<form action="<?php echo site_url('leavemgmt/leaveapply'); ?>" method="POST" class="form-inline">
	<table>
		<tr>
			<td><label for="lt_remarks" class="control-label">Leave Type: <font color='Red'> *</font> </label></td>
			<td>
 	         <select name="la_type" style="width:100%;" id="mySelect"> 
            <option value=" ">---------Select Leave Type---------</option>
            <?php foreach($this->leaveresult as $datas)
						{ ?>
            		<option value="<?php echo $datas->lt_id; ?>"><?php echo $datas->lt_name; ?></option>	
				<?php } ?>
				</select>
			</td>
		<td>Leave Remaining : <input type="text" id="lrmain" readonly="readonly">			
		</tr>         
		<tr>
			<td><label for="lt_remarks" class="control-label">Purpose/Comments: <font color='Red'> *</font> </label></td>        
         <td><textarea name="la_desc"  class="form-control" size="30" rows="5" cols="44" value="<?php echo isset($_POST["la_desc"]) ? $_POST["la_desc"] : ''; ?>"/></textarea></td>
		</tr>
		<tr>
			<td><label for="lt_remarks" class="control-label">From Date: <font color='Red'> *</font> </label></td>         
         <td><input type="text" name="applied_la_from_date" id="StartDate" class="form-control" value="<?php echo isset($_POST["la_from_date"]) ? $_POST["la_from_date"] : ''; ?>"  style="width:100%" /></td>
		 	 	<script src="<?php echo base_url();?>assets/js/jquery.datetimepicker.full.js"></script>
           
            <script>
               $.datetimepicker.setLocale('en');
               $('#StartDate').datetimepicker({
               dayOfWeekStart : 1,
               lang:'en',
               formatTime:'H:i',
               formatDate:'Y-m-d',
					minDate:0,
               });
               //step 5 for give minute duration
               $('#StartDate').datetimepicker();
             </script>
		</tr>
		<tr>
			<td><label for="lt_remarks" class="control-label">To Date: <font color='Red'> *</font> </label></td>        
         <td><input type="text" name="applied_la_to_date" id="LastDate" class="form-control" style="width:100%" /></td>
		   	 <script>
					$.datetimepicker.setLocale('en');
					$('#LastDate').datetimepicker({
					dayOfWeekStart : 1,
					lang:'en',
					formatTime:'H:i',
					formatDate:'Y-m-d',
					minDate:0,
					});
					//step 5 for give minute duration
					$('#LastDate').datetimepicker();
				 </script>
		</tr>

		<tr>
         <td>
			<td align=\"center\">
			 <button name="leaveapply">Apply</button> 
			 <button name="reset" >Clear</button>
         </td>
			</td>
		</tr>
	</table>
</form>

</body>
<div align="center"> <?php $this->load->view('template/footer');?></div>
</html>
                                                                                                                                                                                          
