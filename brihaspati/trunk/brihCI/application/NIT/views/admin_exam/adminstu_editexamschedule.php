<!--
 * @name adminstu_editexamschedule.php
   @author sumit saxena (sumitsesaxena@gmail.com)
 --->


<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Edit Exam Center</title>
<!--<link rel="shortcut icon" href="<?php //echo base_url('assets/images'); ?>/index.jpg">-->
	<link rel="icon" href="<?php echo base_url('uploads/logo'); ?>/nitsindex.png" type="image/png">	
    <head>
	        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.js" ></script>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery.datetimepicker.css"/>
        <?php $this->load->view('template/header'); ?>
      
        <?php //$this->load->view('template/menu');?>
    </head>
    <body>
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->
 <script>
        function goBack() {
        window.history.back();
        }
    </script>
<script>
function calculate() {
                var myBox1 = document.getElementById('box1').value;     
                var myBox2 = document.getElementById('box2').value;
                var result = document.getElementById('result'); 
                var myResult = myBox1 * myBox2;
                result.value = myResult;
        
        }
</script>
 <table width="100%">
            <tr>
		 <?php
                    echo "<td align=\"center\" width=\"100%\">";
                    echo "<b>Update Exam Schedule Details</b>";
                    echo "</td>";
            	?>
	</tr>
</table>
		<table width="100%">
            		<tr><td>
              			<div>
                    <?php echo validation_errors('<div  class="isa_warning">','</div>');?>
                    <?php echo form_error('<div class="isa_error">','</div>');?></div>

                    <?php if(isset($_SESSION['success'])){?>
                        <div class="isa_success"><?php echo $_SESSION['success'];?></div>

                    <?php
                    };
                    ?>
		<?php if(isset($_SESSION['err_message'])){?>
                        <div class="isa_success"><?php echo $_SESSION['err_message'];?></div>

                    <?php
                    };
                    ?>
                </div>
        </td></tr>
        </table>
<table>
         <?php

            echo form_open('adminstuexam/exam_scheduleedit/' . $id);
		?>
		<tr>
		<td><label class="control-label">Institute Name</label></td>
                <td>
                <select style="height:35px;width:100%" name="exmsch_center"  id="exmsch_center" >
                <option value="<?php echo $exmsch_center['value'];?>"><?php //echo $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$exmsch_center['value'])->sc_name ;
echo $this->commodel->get_listspfic1('org_profile','org_name','org_id',$exmsch_center['value'])->org_name ;?></option>;
		<option disabled>Select Institute Name</option>
                <?php foreach($exam_center as $datas): ?>
                	<option value="<?php echo $datas->org_id; ?>"><?php echo $datas->org_name; ?></option>
                <?php endforeach; ?>
                </select>
                </td></tr>
		<td><label  class="control-label">Program Category</label></td>
                <td>
                <select style="height:35px;width:100%" name="exmsch_progcat" >
                 <option value="<?php echo $exmsch_progcat['value'];?>"><?php echo $this->commodel->get_listspfic1('programcategory','prgcat_name','prgcat_id',$exmsch_progcat['value'])->prgcat_name;?></option>;
                </select>
                </td></tr>
		<tr>
		<td><label class="control-label">Department Name</label></td>
                <td>
                <select style="height:35px;width:100%" name="exmsch_dept" >
                <option value="<?php echo $exmsch_dept['value'];?>"><?php echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$exmsch_dept['value'])->dept_name ;?></option>;
		
                </select>
                </td></tr>
	
		<tr>
		<td><label class="control-label">Programme Name</label></td>
                <td>
                <select style="height:35px;width:100%" name="exmsch_prog" >
                <option value="<?php echo $exmsch_prog['value'];?>"><?php echo $this->commodel->get_listspfic1('program','prg_name','prg_id',$exmsch_prog['value'])->prg_name.'( '.$this->commodel->get_listspfic1('program','prg_branch','prg_id',$exmsch_prog['value'])->prg_branch.' )' ;?></option>;
		                
                </select>
                </td></tr>

		<tr>
		<td><label class="control-label">Semester</label></td>
                <td>
                <select style="height:35px;width:100%" name="exmsch_sem"  >
                <option value="<?php echo $exmsch_sem['value'];?>"><?php echo $exmsch_sem['value'];?></option>;
		
                </select>
                </td></tr>

		<tr>
		<td><label class="control-label">Subject Name</label></td>
                <td>
                <select style="height:35px;width:100%" name="exmsch_subj"  >
                <option value="<?php echo $exmsch_subj['value'];?>"><?php echo $this->commodel->get_listspfic1('subject','sub_name','sub_id',$exmsch_subj['value'])->sub_name ;?></option>;
		
                </select>
                </td></tr>

		<tr>
		<td><label class="control-label">Paper Name</label></td>
                <td>
                <select style="height:35px;width:100%" name="exmsch_paper"  >
                <option value="<?php echo $exmsch_paper['value'];?>"><?php echo $this->commodel->get_listspfic1('subject_paper','subp_name','subp_id',$exmsch_paper['value'])->subp_name ;?></option>;
		
                </select>
                </td></tr>

		<tr>
		<td><label class="control-label">Academic Year</label></td>
                <td>
                <select style="height:35px;width:100%" name="exmsch_session"  >
                <option value="<?php echo $exmsch_session['value'];?>"><?php echo $exmsch_session['value'];?></option>;
		
                </select>
                </td></tr>

		<tr>
		<td><label class="control-label">Exam Name</label></td>
                <td>
                <select style="height:35px;width:100%" name="exmsch_exname"  >
                <option value="<?php echo $exmsch_exname['value'];?>"><?php echo $this->commodel->get_listspfic1('examtype','exty_name','exty_id',$exmsch_exname['value'])->exty_name	 ;?></option>;
		<option disabled>Select Exam Name</option>
               <?php foreach($exmname as $datas){?>
				<option value="<?php echo $datas->exty_id;?>"><?php echo $datas->exty_name;?></option>
		<?php }?>
                </select>
                </td></tr>

		<?php
                /*echo "<td>";
                echo form_label('Exam Name', 'exmsch_exname');
                echo "</td>";
                echo "<td>";
                echo form_input($exmsch_exname);
                echo "</td>";
            	echo "</tr>";*/?>

		 <tr>
                <td><label>Exam Date/Time:</label></td>
                <td><input type="text" name="exmsch_date" id="ExamDate" placeholder="Enter Exam Date & Time" style="height:35px;width:100%" value="<?php echo $exmsch_date['value'] ; ?>"/>
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
	<?php
		
		echo "<td>";
                    echo form_hidden('exty_id', $id);
                   echo"<td>";
                    echo form_submit('submit', 'Update');
                   echo " ";

            echo form_close();
            echo "<button onclick=\"goBack()\" >Back</button>";
            echo "</td>";
            echo "</tr>";
            echo"</td>";
 ?>

       </table>
</body>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>
                              
