<!--
   @name adminstu_examschedule.php
   @author sumit saxena (sumitsesaxena@gmail.com)
   @author neha khullar (nehukhullar@gmail.com)

 --->

<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?><!DOCTYPE html>
<html>
<head>
<title>Exam Schedule</title>
<!--<link rel="shortcut icon" href="<?php //echo base_url('assets/images'); ?>/index.jpg">-->
	<link rel="icon" href="<?php echo base_url('uploads/logo'); ?>/nitsindex.png" type="image/png">	
<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/message.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.js" ></script>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery.datetimepicker.css"/>

<script>
  function getdeptlist(prgcatid){
		var prgcatid = prgcatid;
		//alert (branch);
                $.ajax({
                type: "POST",
                url: "<?php echo base_url();?>slcmsindex.php/adminstuexam/deptlist",
                data: {"prgcategoryid" : prgcatid},
                dataType:"html",
                success: function(data){
                $('#exmsche_deptid').html(data.replace(/^"|"$/g, ''));
                }
            }); 
        }
 function getprglist(combid){
	        var prgcate = $('#pcategory').val();
                var dept = $('#exmsche_deptid').val();
                var combid = prgcate+","+dept;
		//alert(combid);
                $.ajax({
                type: "POST",
                url: "<?php echo base_url();?>slcmsindex.php/adminstuexam/prgcatdept",
                data: {"prgcat_dept" : combid},
                dataType:"html",
                success: function(data){
                $('#prog_id').html(data.replace(/^"|"$/g, ''));
                }
             });
        }
 function getsublist(prgsem){
	        var progid = $('#prog_id').val();
                var sem = $('#exmsch_sem').val();
                var prgsem = progid+","+sem;
		//alert(combid);
                $.ajax({
                type: "POST",
                url: "<?php echo base_url();?>slcmsindex.php/adminstuexam/subjlist",
                data: {"prog_sem" : prgsem},
                dataType:"html",
                success: function(data){
                $('#exmsch_subid').html(data.replace(/^"|"$/g, ''));
                }
             });
        }

 function getpaperlist(prgsemsub){
	        var progid = $('#prog_id').val();
                var sem = $('#exmsch_sem').val();
		var subjid = $('#exmsch_subid').val();
                var prgsemsub = progid+","+sem+","+subjid;
		//alert(prgsemsub);
                $.ajax({
                type: "POST",
                url: "<?php echo base_url();?>slcmsindex.php/adminstuexam/paperlist",
                data: {"prog_semsubj" : prgsemsub},
                dataType:"html",
                success: function(data){
                $('#exmsch_paperid').html(data.replace(/^"|"$/g, ''));
                }
             });
        }



</script>
</head>

<body>
<?php $this->load->view('template/header'); ?>

 	<table style="font-size:18px;width:100%;">
            <tr>
                <?php
                echo "<td align=\"left\" width=\"33%\">"; 
                echo anchor('adminstuexam/exam_scheduleview/','View Exam Schedule',array('title'=>'Add Exam Schedule Detail','class' => 'top_parent'  ));
                echo "</td>";

                echo "<td align=\"center\" width=\"34%\">";
                echo "<b>Add Exam Schedule Details</b>";
                echo "</td>";

                echo "<td align=\"right\" width=\"33%\">";
               // $help_uri = site_url()."/help/helpdoc#ProgramFees";
               // echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                echo "</td>";
                ?>
             </tr>
           </table>
           <table style="width:100%;">
            <tr><td> 
		<div>
                <?php
                     echo validation_errors('<div class="isa_warning">','</div>');
                     echo form_error('<div  class="isa_error">','</div>');

                     if(isset($_SESSION['success'])){?>
                        <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                 <?php
                     };
                     if(isset($_SESSION['err_message'])){?>
                        <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>

                 <?php
                    };
                 ?>
                </div>
            </td></tr>
	</table>

<form action="<?php echo site_url('adminstuexam/exam_scheduleadd');?>" method="POST">

 <table style="width:100%;" align=right border=0>
	<!--<tr>
	<td width=170><label>Institute Name :</label> </td>
		<td> <select class="dropdown" name="examsch_center">
			<option disabled selected>Select Institute</option>
			<?php foreach($exam_center as $row){?>
				<option value="<?php echo $row->org_id;?>"><?php echo $row->org_name;?></option>
			<?php }?>
			</select>
	     </td>
	</tr> -->
	
	<tr><td>
        	<label for="text">Program Category :</label>
		</td><td>
			<select name="prgcategoryid" id="pcategory" class="dropdown" onchange="getdeptlist(this.value)">
			<option selected="true" disabled="disabled" style="font-size:18px;">Select Program Category</option>
				<?php 
				    foreach($pcategory as $row){
			        ?>
					<option value="<?php echo $row->prgcat_id;?>"><?php echo $row->prgcat_name;?>
				<?php  }?>
	  </select>
		
		</td>
		</tr>

	<tr>
		<td><label>Department Name :</label> </td>
		<td> <select class="dropdown" name="examsch_deptid" id="exmsche_deptid" onchange="getprglist(this.value)">
			<option disabled selected>Select Department Name</option>
			<?php //foreach($deptname as $dept){?>
				<option value="<?php //echo $dept->dept_id;?>"><?php //echo $dept->dept_name;?></option>
			<?php //}?>
		</select>
</td>
	</tr>


	<tr>
		<td><label>Programme Name :</label> </td></td>
		<td> <select class="dropdown" name="examsch_prgid" id="prog_id">
			<option disabled selected>Select Programme</option>
			<?php //foreach($prgname as $data){?>
				<option value="<?php //echo $data->prg_id;?>"><?php //echo $data->prg_name.'( '.$data->prg_branch.' )';?></option>
			<?php //}?>
		</select>
</td>
	</tr>

	<tr>
		<td><label>Semester :</label> </td>
		<td> 	<select class="dropdown" name="examsch_sem" id="exmsch_sem" onchange="getsublist(this.value)">
				<option disabled selected>Select Semester</option>
				<option value="1" class="dropdown-item"> 1 </option>
                		<option value="2" class="dropdown-item"> 2 </option>
                		<option value="3" class="dropdown-item"> 3 </option>
                		<option value="4" class="dropdown-item"> 4 </option>
                		<option value="5" class="dropdown-item"> 5 </option>
               			<option value="6" class="dropdown-item"> 6 </option>
                		<option value="7" class="dropdown-item"> 7 </option>
                		<option value="8" class="dropdown-item"> 8 </option>
			</select>
		</td>
	</tr>

	<tr>
		<td><label>Subjects List:</label> </td></td>
		<td> <select class="dropdown" name="examsch_subid" id="exmsch_subid" onchange="getpaperlist(this.value)">
			<option disabled selected>Select Subject</option>
			<?php //foreach($prgname as $data){?>
				<option value="<?php //echo $data->prg_id;?>"><?php //echo $data->prg_name.'( '.$data->prg_branch.' )';?></option>
			<?php //}?>
		</select>
</td>
	</tr>

	<tr>
		<td><label>Papers List:</label> </td></td>
		<td> <select class="dropdown" name="examsch_paperid" id="exmsch_paperid">
			<option disabled selected>Select Paper</option>
			<?php //foreach($prgname as $data){?>
				<option value="<?php //echo $data->prg_id;?>"><?php //echo $data->prg_name.'( '.$data->prg_branch.' )';?></option>
			<?php //}?>
		</select>
</td>
	</tr>


	<tr>
		<td><label>Session :</label> </td>
		<td> <select class="dropdown" name="examsch_session">
			<option disabled selected>Select Session</option>
			<?php   $acadyear = array();
    				for($i = 2017; $i < date("Y")+10; $i++)
    				{
       					 $j=$i+1;
        				 $temp =  $i. "-" .$j;
        				 $acadyear = $temp;
    			?>
				<option value="<?php echo $acadyear;?>"><?php echo $acadyear;?></option>
			<?php } ?>
		</select>
</td>
	</tr>

	<tr>
		<td><label>Exam Name :</label></td>
		<td><!---<input type="text" name="examsch_name" size="37" placeholder="Enter Exam Name" value="<?php //echo isset($_POST["examsch_name"]) ? $_POST["examsch_name"] : ''; ?>" />--->
		
		<select class="dropdown" name="examsch_name">
			<option disabled selected>Select Exam Name</option>
			<?php foreach($exmname as $datas){?>
				<option value="<?php echo $datas->exty_id;?>"><?php echo $datas->exty_name;?></option>
			<?php }?>
		</select>
		</td>	
	</tr>


	 <tr>
                <td><label>Exam Date/Time:</label></td>
                <td><input type="text" name="examsch_datetime" id="ExamDate" placeholder="Enter Exam Date & Time" size="37" value="<?php echo isset($_POST["examsch_datetime"]) ? $_POST["examsch_datetime"] : ''; ?>"/>
		 <script src="<?php echo base_url();?>assets/js/jquery.datetimepicker.full.js"></script>
                 <script>
                        $.datetimepicker.setLocale('en');
                        $('#ExamDate').datetimepicker({
                        dayOfWeekStart : 1,
                        lang:'en',
                        formatTime:'H:i',
                        formatDate:'Y-m-d',
                        });
                        //step 5 for give minute duration
                        $('#ExamDate').datetimepicker();
                </script>
	
	</tr>	

	<tr>
		<td></td>
       	 	<td><input type="submit" name="addexamsch" value="Submit" style="height:35px;font-size:18px;">
		<input type="reset" value="Reset" style="height:35px;font-size:18px;"></td>
        </tr>

	
</table>

</form>

<?php $this->load->view('template/footer'); ?>
</body>
</html>

