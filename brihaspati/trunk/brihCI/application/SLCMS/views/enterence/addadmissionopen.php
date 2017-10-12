<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Add admission open</title>

 <head>
     <?php $this->load->view('template/header'); ?>
     <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
     <?php $this->load->view('template/menu');?>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.js" ></script>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery.datetimepicker.css"/>

<script>
      function getprogramname(prg){
                var prg = prg;
                $.ajax({
                type: "POST",
                url: "<?php echo base_url();?>slcmsindex.php/enterence/programlist",
                data: {"programcategory" : prg},
	//	alert(data);
                dataType:"html",
                success: function(data){
                $('#programname').html(data.replace(/^"|"$/g, ''));
                }
            }); 
        }

</script>

   <style>
                .abc{
                    width:100%;

                }
            </style>
    </head>
    <body>
        <table width="100%" >

          <?php //echo form_error('<div style="margin-left:2%;" class="isa_error">','</div>');?>
            <tr><td>
                <div style="margin-left:2%;">
                <?php echo anchor('enterence/viewadmissionopen/', "Admission List ", array('title' => 'View Detail' , 'class' => 'top_parent'));
                $help_uri = site_url()."/help/helpdoc#";
                echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:65%\">Click for Help</b></a>";
		?>
		 </div>
                <div align="left" style="margin-left:2%;width:90%">

                <?php echo validation_errors('<div style="margin-left:2%;" class="isa_warning">','</div>');?>

                <?php if(isset($_SESSION['success'])){?>
                    <div style="margin-left:2%;" class="isa_success"><?php echo $_SESSION['success'];?></div>

                <?php
                };
                ?>
                 <?php if(isset($_SESSION['err_message'])){?>

                    <div style="margin-left:2%;"  class="isa_error"><?php echo $_SESSION['err_message'];?></div>

                <?php
                };
                ?>
		</div>
		</td></tr>
        </table>
        
	<table style="margin-left:2%;" >


           <form action="<?php echo site_url('Enterence/addadmissionopen');?>" method="POST" >
		 <td><label for="academicyear" class="control-label">  Academic Year :</label></td>
                <td>
                    <select name="academicyear" class="my_dropdown" style="width:70%;">
                    <option value="" disabled selected >------Academic year------</option>
                    <?php

                      for($i = 2016; $i < date("Y")+10; $i++){

                        $j=$i+1;
                        echo '<option value="'.$i.' - '.$j.'">'.$i.' - '.$j.'</option>';

                        }
                        ?>
                    </select>
		</td>
		 <tr><td><label for="programcategory" class="control-label">  Program Category: </label></td><td>
                        <select name="programcategory" id="programcategory" class="my_dropdown" style="width:70%;"onchange="getprogramname(this.value)">
                          <option value=""disabled selected>----Program Category----</option>
                        <?php foreach($this->prgcatresult as $datas): ?>
                        <option value="<?php echo $datas->prgcat_name;?>"><?php echo $datas->prgcat_name; ?></option>
                        <?php endforeach; ?>
                        </select>
                        </td>
			<td> Example : Under Graduate , Post Graduate , Diploma, etc.  </td>

		</tr>


			<tr><td><label for="programname" class="control-label"> Program Name:</label> </td><td>
                        <select name="programname" id="programname" class="my_dropdown"  style="width:70%;" > 
                        <option value=""disabled selected>---------Select program ---------</option>
		
                        <?php //foreach($this->prgresult as $datas): ?>
                      <!-- <option value="<?php //echo $datas->prg_id; ?>"><?php //echo $datas->prg_name."(".$this->commodel->get_listspfic1('program','prg_branch','prg_id',$datas->prg_id)->prg_branch.")";				 ?></option> -->
			<?php //endforeach; ?>
                        </select>
                        </td>
				<td> Example : BBA, BCA, MCA, M.SC etc.  </td>
			</tr>


			</tr>
                         <tr>
                            <td><label for="enterenceexamfees" class="control-label">Entrance Exam Fees:</label></td>
                            <td><input type="text" name="enterenceexamfees" id="enterenceexamfees" class="form-control" style="width:70%" > </td> 
                            <td>Note:  Fess of Entrance Exam Fees  </td>
                        </tr>


			 <tr>
                            <td><label for="minimumqualification" class="control-label">Minimum Qualification:</label></td>
                            <td><textarea rows= "" cols="70" name="minimumqualification" style="width:70%" > </textarea></td> 
			    <td>Note:  Minimum qualification of particular program.  </td>
			</tr>
			<tr>
                            <td><label for="entranceexampattern" class="control-label">Entrance Exam Pattern:</label></td>
                            <td><textarea rows= "" cols="70" name="entranceexampattern" style="width:70%" > </textarea> </td> 
                            <td>Note: Pattern Of Entrance Exam.  </td>
			</tr>

			 <tr>
                            <td><label for="entranceexamdate" class="control-label">Entrance Exam Date/Time:</label></td>
                            <td><input type="text" name="entranceexamdate" id="ExamDate" class="form-control" style="width:70%"/>
			     <td>Date Format:yy-mm-dd</td>
			  <?php //echo form_error('entranceexamdate');?>
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
                     <td><label for="startdateofonlineapplication" class="control-label">Start Date Of Online Application:</label></td>
                     <td><input type="text" name="startdateofonlineapplication" id="StartDate" class="form-control" style="width:70%" />
		     <td> Date Format:yy-mm-yy </td>
		 <script src="<?php echo base_url();?>assets/js/jquery.datetimepicker.full.js"></script>
                      <script>
                        $.datetimepicker.setLocale('en');
                        $('#StartDate').datetimepicker({
                        dayOfWeekStart : 1,
                        lang:'en',
                        formatTime:'H:i',
                        formatDate:'Y-m-d',
                        });
                        //step 5 for give minute duration
                        $('#StartDate').datetimepicker();
                    </script>

                 </tr>

                      <tr>
                       <td><label for="lastdateofonlineapplication" class="control-label">Last Date Of Online Application:</label></td>
                       <td><input type="text" name="lastdateofonlineapplication" id="LastDate" class="form-control" style="width:70%" />
		        <td> Date Format:yy-mm-yy </td>
		<script src="<?php echo base_url();?>assets/js/jquery.datetimepicker.full.js"></script>
		<script>
			$.datetimepicker.setLocale('en');
			$('#LastDate').datetimepicker({
			dayOfWeekStart : 1,
			lang:'en',
			formatTime:'H:i',
			formatDate:'Y-m-d',
			});
			//step 5 for give minute duration
			$('#LastDate').datetimepicker();
			</script>
                  </tr>

               	<tr>
                       <td><label for="lastdateofapplicationreceived" class="control-label">Last Date Of Application Received :</label></td>
                       <td><input type="text" name="lastdateofapplicationreceived" id="EndDate" class="form-control" style="width:70%" />
	       	        <td> Date Format:yy-mm-dd </td>
                        <script src="<?php echo base_url();?>assets/js/jquery.datetimepicker.full.js"></script>
                <script>
                        $.datetimepicker.setLocale('en');
                        $('#EndDate').datetimepicker({
                        dayOfWeekStart : 1,
                        lang:'en',
                        formatTime:'H:i',
                        formatDate:'Y-m-d',
                        });
                        //step 5 for give minute duration
                        $('#EndDate').datetimepicker();
                        </script>


                       </tr>
                   </tr>
		        <tr>
			<td></td>
                        <td>
			<button name="addadmissionopen">Submit</button>

                        <button name="clear">Clear</button>
                        </td>
                        </tr>
                    </form>
                  </div>
            </tr>	
        </table>
</body>
<div align="center">  <?php $this->load->view('template/footer');?></div>
</head>
</html>
	

