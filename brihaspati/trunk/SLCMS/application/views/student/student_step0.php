<!-------------------------------------------------------
    -- @name student_step0.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
--------------------------------------------------------->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?><!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Welcome to IGNTU</title>
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
	<link rel="shortcut icon" href="<?php echo base_url('assets/images'); ?>/index.jpg">

<style type="text/css">
label{font-size:18px;}
input[type='text']{font-size:17px;width:120%;height:30px;}
input[type='email']{font-size:17px;width:120%;height:30px;}
input[type='button']{font-size:16px;}
</style>
<script>
/*function change_getcat(){
	var xmlhttp = new XMLHttpRequest();

	xmlhttp.open("GET","<?php echo site_url('student/student_step0'); ?>?catbranch="+document.getElementById("register_name").value,false);

	xmlhttp.send(null);
	
	document.getElementById("getbranch").innerHTML = xmlhttp.responseText; 
	}*/

 function getbranchname(branch){
                var branch = branch;
                $.ajax({
                type: "POST",
                url: "<?php echo base_url();?>index.php/student/branchlist",
                data: {"Sprogramname" : branch},
                dataType:"html",
                success: function(data){
                $('#branchname').html(data);
                }
            }); 
        }

</script>
</head>
<body>


<div>
	<div id="body">
	<?php 
	// $thisPage="studentForm";
	 $this->load->view('template/header'); ?>
	<nav> 	<h1>Welcome to IGNTU  </h1></nav>
	<?php    //if(isset($_SESSION)) {
        	//echo $this->session->flashdata('flash_data');
    	//} ?>
 	<br><br>
		<?php $this->load->view('student/stuStepshead');?>

	<div align="left" style="margin-left:30px;width:1700px;">
        <?php echo validation_errors('<div class="isa_warning">','</div>');?>
        <?php echo form_error('<div style="margin-left:30px;" class="isa_error">','</div>');?>
        <?php if(isset($_SESSION['success'])){?>
        <div class="alert alert-success"><?php echo $_SESSION['success'];?></div>
        <?php
    	 };
       	?>
        <?php if(isset($_SESSION['err_message'])){?>
             <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
        <?php
        };
	?>  
      </div>

		<?php $this->load->view('student/studentCrieteria');?>
	<center>
<?php
echo "<center style='color:#B21f35;text-align:center;font-size:20px;'>";
	 
	if($this->session->flashdata('msg')){
	echo $this->session->flashdata('msg');
	
}

echo "</center>";
?>	


    	<form action="<?php echo site_url('student/student_step0'); ?>" method="POST" >
	   <table>
		<tr><td>
        	<label for="username">Application Number</label></td>
		</td><td>
		<?php //echo "<span style='color:red;font-size:18px;float:left;'>";echo form_error('Sanumber'); echo "</span>";?>
		<?php //if(isset($msg)){ 
			//echo $msg;
			//} ?>
        	<input type="text" name="Sanumber" placeholder="Enter Your Application No." autofocus/> <br>
		</td></tr>	
		<tr ><td>
        	<label for="text">Program/Courses</label>
		</td><td>
			<select name="Sprogramname" id="programname" class="form-control" id="register_name" onchange="getbranchname(this.value)" style="width:122%;height:40px;font-size:18px;font-weight:bold;">
			<option selected="true" disabled="disabled" style="font-size:18px;">programme/courses</option>
				<?php 
				$result=$this->stumodel->showCourse(); 
				if(isset($result)):
					$count = count($result);
					for($i = 0 ; $i<$count ; $i++){
			        ?>
				<option value="<?php echo $result[$i]->course_name;?>"><?php echo $result[$i]->course_name;?>
			<?php } endif; ?>
	  </select>
		<!---<select name="programname" id="programname"  style="width:122%;height:40px;font-size:18px;font-weight:bold;" onchange="getbranchname(this.value)" >
                <option value="" disabled selected >-------Select Program-------</option>
                <?php foreach($this->pnresult as $dataspt): ?>
               	 	<option value="<?php echo $dataspt->prg_name; ?>"><?php echo $dataspt->prg_name;?></option>
		<?php endforeach; ?>	
		</select>-->	
		</td>
		</tr>
		<tr><td>
			<!---<span style="color:red;"><?php echo form_error('Stypeprogramme');?></span>--->
			<label for="nnumber">Select Branch</label></br></td>
			<td>
				<!--<select id="getbranch" style="width:122%;height:40px;font-size:18px;font-weight:bold;" name="Sbranch">
					<option selected="true" disabled="disabled">Select branch</option>
				</select>--->
			
			<select name="Sbranchname" id="branchname"  style="width:122%;height:40px;font-size:18px;font-weight:bold;">
              		  <option value="" selected="true" disabled="disabled" >Select Branch</option>
			</select>
	
		</td></tr>
		<tr><td>
		<label for="text">Student Name</label>
		</td><td>
		<?php //echo "<span style='color:red;font-size:18px;float:left;'>";echo form_error('Sname'); echo "</span>";?>
		<?php //if(isset($msg)){ 
			//echo $msg;
			//} ?>
        	<input type="email" name="Semail" placeholder="Enter Your Email-id"/><br>
		</td></tr>
		<tr>
        	<td></td>
		<td>
		<input type="submit" value="Submit" name="login" style="font-size:20px;margin-left:0px;">
		<input type="reset" value="Clear" style="font-size:20px;margin-left:0px;">
		</td></tr>
		</table>
    	</form>
	<center>
	</div>
	<?php 
	 //$thisPage="studentForm";
	 $this->load->view('template/footer'); ?>
	<!--<p class="footer">Page rendered in <strong>{elapsed_time}</strong> seconds. <?php echo  (ENVIRONMENT === 'development') ?  'CodeIgniter Version <strong>' . CI_VERSION . '</strong>' : '' ?></p>-->
</div>

</body>
</html>
