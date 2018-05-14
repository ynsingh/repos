<!--@name addscholartype.php
    @author Krishna Sahu (krishnasahu2406@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Add Scholar</title>

 <head>
     <?php $this->load->view('template/header'); ?>
     <?php //$this->load->view('template/menu');?>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/datepicker/jquery-ui.css">
        <script type="text/javascript" src="<//?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-ui.js" ></script>
 </head>
<script>
$( function() {
	   $( "#StartDate,#EndDate" ).datepicker({
		dateFormat: 'yy-mm-dd',
	   	minDate: 0
	   	});
	  	});
</script>
 <body>
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->
 <table width="100%">
            <tr>
                <?php 
                echo "<td align=\"left\" width=\"33%\">";
                echo anchor('scholarship/scholartype/', "View Scholarship Details ", array('title' => 'Add Detail' ,'class' =>'top_parent'));
                echo "</td>";

                echo "<td align=\"center\" width=\"34%\">";
                echo "<b>Add Scholarship Details</b>";
                echo "</td>";

                echo "<td align=\"right\" width=\"33%\">";
                $help_uri = site_url()."/help/helpdoc#ViewScholarshipDetail";
                echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                echo "</td>";
            ?>
             </div>
               </tr>
           </table>
           <table width="100%">
           <tr><td>
                <div>
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
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
              </div>
             </td></tr>
        </table>
<div>
    <form action="<?php echo site_url('scholarship/addscholar');?>" method="POST" class="form-inline">
    <table>
		<tr>
                <td><label for="sch_type" class="control-label">Scholarship Type:</label></td>
                <td>
                <input type="text" name="sch_type"  class="form-control" size="30" />&nbsp;    Example: Academic, Athletic, etc
                </td> 
                </tr>
		
                <tr>
                <td><label for="sch_code" class="control-label">Scholarship Code:</label></td>
                <td>
                <input type="text" name="sch_code"  class="form-control" size="30" />&nbsp;   Example: S001, S002, etc
                </td>

            </tr>
            <tr>
                <td><label for="sch_name" class="control-label">Scholarship Name:</label></td>
                <td>
                <input type="text" name="sch_name"  class="form-control" size="30" />&nbsp;    Example: Cricket, Education, etc
                </td>
               
            </tr>

            <tr>
                <td>
                <label for="sch_des" class="control-label">Description:</label>
                </td>
                <td>
                    <input type="text" name="sch_des" size="30"  class="form-control" /> 
                </td>
               
            </tr>
            <tr>
                <td><label for="sch_provider" class="control-label">Provider:</label></td>
                <td>
                <input type="text" name="sch_provider"  class="form-control" size="30" />
                </td>
               
            </tr>
             <?php
			echo "<td>";
	                echo form_label('Year:', 'sch_startyear');
        	        echo "</td>";
                	echo "<td>";
               		echo "<select name=\"sch_startyear\" class=\"my_dropdown\" style=\"width:60%;\">";
               		echo "<option value= disabled selected >------Select Scholarship Year------</option>";
	                for($i = 2016; $i < date("Y")+10; $i++){
        	        $j=$i+1;
	                echo '<option value="'.$i.' - '.$j.'">'.$i.' - '.$j.'</option>';
                        }
        	        echo " </select>";
			?>

             <tr>
                 <td><label for="sch_startdate" class="control-label">Start Date:</label></td>
                 <td><input type="text" name="sch_startdate" id="StartDate" class="form-control" size="30"  value="<?php echo isset($_POST["sch_startDate"]) ? $_POST["sch_startDate"] : ''; ?>"/><br></td>


            </tr>

            <tr>
                  <td><label for="sch_enddate" class="control-label">End Date:</label></td>
                  <td><input type="text" name="sch_enddate" id="EndDate" class="form-control" size="30"  value="<?php echo isset($_POST["sch_enddate"]) ? $_POST["sch_enddate"] : ''; ?>"/><br>
            </td>
            </tr>
            <tr>
                <td></td><td>
                <button name="addscholar" >Add Scholarship</button>
                <button name="reset" >Clear</button>
                </td>
           </tr>
                 </table>
    </form>
</div>
    </body>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
    </html>

