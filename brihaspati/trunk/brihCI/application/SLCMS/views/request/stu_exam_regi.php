<!-------------------------------------------------------
    -- @name stu_exam_regi.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
--------------------------------------------------------->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');


?><!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>IGNTU:Semester registration</title>
	<link rel="shortcut icon" href="<?php echo base_url('assets/images'); ?>/index.jpg">
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/message.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/studentNavbar.css">
<link rel="stylesheet" type="text/css" media="all" href="<?php echo base_url(); ?>assets/css/Studentsteps.css" />
<style type="text/css">
label{font-size:18px;}
input[type='text']{font-size:17px;height:30px;background-color:white;}
input[type='email']{font-size:17px;height:30px;background-color:white;}


tr td{font-size:15px;}
	tr th{background:black;color:white;font-weight:bold;}
select{width:100%;font-size:17px;height:40px;}

</style>

</head>
<body>


<div>
	<div id="body">
	<?php  // $thisPage2="studentaddDetail"; 
		$this->load->view('template/header'); ?>
</br>
	<?php $this->load->view('template/stumenu'); ?>
	<?php //if(isset($_SESSION)) {
        	//echo $this->session->flashdata('flash_data');
    	//} ?>
<!--------------------------------------------------------ERROR DISPLAY-------------------------------------------------------------->
<?php
echo "<center>";

	if($this->session->flashdata('msg')){
echo "<div style='font-size:20px;text-align:center;background-color:#DFF2BF;width:50%;height:30px;color:green;'>";
	echo $this->session->flashdata('msg');
echo "<div>";	
}

echo "</center>";
?>
	<div align="left" style="margin-left:30px;width:1700px;font-size:18px;">
        <?php echo validation_errors('<div class="isa_warning">','</div>');?>
        <?php echo form_error('<div style="margin-left:30px;" class="isa_error">','</div>');?>
        <?php if(isset($_SESSION['success'])){?>
        <div class="alert alert-success"><?php echo $_SESSION['success'];?></div>
        <?php
    	 };
       	?>
	
        <?php if(isset($_SESSION['err_message'])){?>
             <div class="isa_error" ><div ><?php echo $_SESSION['err_message'];?></div></div>
        <?php
        };
	?>  
      </div>
<?php //$uid=($this->session->userdata['id_user']); print_r($uid);?>

	<!---<h1>Welcome <?= $this->session->userdata('username') ?>  </h1>-->
	<h1>Exam Registration</h1>
<center>
	  <table  style="background-color:#f1f1f1;" align="">

<center>
<form action="<?php echo site_url('request/exam_regi'); ?>"  method="POST">
	<table style="width:52%;">
		<tr>
			<td align="center"><b><div style="font-size:19px;margin-left:0px;">Session : </b><?php echo $cacadyer;?></div></td>		
		<td>
		</tr></td>
	</table>
</br></br>

<table style="width:52%;">
	<tr><td style="width:50%;">

	<table style="width:52%;">
	    <tr>	
		
		<td>
			<!---<span style="color:red;"><?php echo form_error('Sname');?></span>--->
			<label>Exam name</label></br>
			<input type="text" name="Exname" placeholder="Enter your exam name" value="<?php //echo $this->name;?>Final Exam" readonly>	
		<td/>		
		
		<td>	
			<label for="nnumber">Course</label></br>
			<input type="text" name="Excourse" placeholder="Enter your program name" value="<?php echo $this->pname;?>" readonly>		
		<td/>
		<td>	<!---<span style="color:red;"><?php echo form_error('Spincode');?></span>--->
			<label>Semester</label></br>
			<input type="text" name="Exsem" placeholder="Enter Your Pincode" MaxLength="6" value="<?php echo $noofsemester; ?>" readonly/>		
		<td/>
		
	</tr>

	<tr height=10></tr>

	<tr>	
		<td>
			
			<span style="color:red;"><?php echo form_error('Sname');?></span>
			<label>Student name</label></br>
			<input type="text" name="Exsname" placeholder="Enter your exam name" value="<?php echo $this->name;?>" readonly>	
		<td/>
		
		<td>	
			<!---<span style="color:red;"><?php echo form_error('Smothername');?></span>--->
			<label for="nnumber">Mother name</label></br>	
			<input type="text" name="Exmname" placeholder="Enter Mother Name" value="<?php echo $this->mname; ?>" readonly/>		
		<td/>
		<td>	
			<!---<span style="color:red;"><?php echo form_error('Sfathername');?></span>--->
			<label>Father Name</label></br>
			<input type="text" name="Exfname" placeholder="Enter Father Name" value="<?php echo $this->fathname; ?>" readonly/>		
		<td/>
		
		
	<tr>
		<tr height=10></tr>
	
	<tr>

		<td>	<!---<span style="color:red;"><?php echo form_error('Sgender');?></span>--->
			<label>Gender</label></br>
			<input type="text" name="Exgender" placeholder="Enter gender" value="<?php echo $this->gender;?>" readonly>	
			<!--<input type="text" name="Sgender" placeholder="Enter Gender" >--->		
		<td/>
		<td>
			
		<!---<span style="color:red;"><?php echo form_error('Sdob');?></span>-->

		<label>Date of birth</label></br>
		<input type="text" name="Exdob" placeholder="Enter date of birth" value="<?php echo $this->dob;?>" readonly>
		<td/>
		<td>	
			<!---<span style="color:red;"><?php echo form_error('Scategory');?></span>--->
			<label for="nnumber">Category</label></br>
			<input type="text" name="Excat" placeholder="Enter category" value="<?php echo $this->catename;?>" readonly>	
		<td/>
		
	<tr>
</table>
</td>

<td style="float:left;margin:40px 0px 0px 50px;">

<img src="<?php echo base_url('uploads/student_sign_photo/student_photo/'.$this->phresult); ?>" height="140" width="130" style="float:right;"></td></tr>
</table>

<table style="">		
	<tr height=10></tr>
	<tr>
		<td>	<!---<span style="color:red;"><?php echo form_error('Spaddress');?></span>--->
			<label>Address</label></br>
			<input type="text" name="Exaddress" placeholder="Enter Postal Address" value="<?php echo $this->padd; ?>" readonly/>		
		<td/>

		<td>	<!--<span style="color:red;"><?php echo form_error('Semail');?></span>-->
			<label>District</label></br>
	    <input type="text" name="Exdist" placeholder="Enter Your District" style="height:30px;width:97%;" value="<?php echo $this->pdist; ?>" readonly/>		
		<td/>

		<td>	<!--<span style="color:red;"><?php echo form_error('Semail');?></span>--->
		    <label>Post Office</label></br>
	  	    <input type="text" name="Expost" placeholder="Enter Your post office" style="height:30px;width:98%;" value="<?php echo $this->ppost; ?>" readonly/>	
		<td/>
		<td>	<!---<span style="color:red;"><?php echo form_error('Scity');?></span>--->
			<label for="nnumber">City</label></br>	
			<input type="text" name="Excity" placeholder="Enter Your District/City" value="<?php echo $this->pcity; ?>" readonly/>		
		<td/>
		
	</tr>
		<tr height=10></tr>

		<tr>
			<td>	<!---<span style="color:red;"><?php echo form_error('Sstate');?></span>--->
			<label>State</label></br>
			<input type="text" name="Exstate" placeholder="Enter Your State" value="<?php echo $this->pstat; ?>" readonly/>		
		<td/>

			<td>	<!---<span style="color:red;"><?php echo form_error('Spincode');?></span>--->
			<label>Country</label></br>
			<input type="text" name="Excountry" placeholder="Enter Your Country" value="<?php echo $this->pcounname; ?>" readonly/>		
		<td/>
		<td>	<!---<span style="color:red;"><?php echo form_error('Spincode');?></span>--->
			<label>Pincode</label></br>
			<input type="text" name="Expincode" placeholder="Enter Your Pincode" MaxLength="6" value="<?php echo $this->ppin; ?>" readonly/>		
		<td/>
		<td>	<!---<span style="color:red;"><?php echo form_error('Smobile');?></span>--->
			<label>Mobile/Phone no.</label></br>
			<input type="text" name="Exmobile" placeholder="Enter Mobile Number" MaxLength="10" pattern="/^+91(7\d|8\d|9\d)\d{9}$/" value="<?php echo $this->mobile; ?>" readonly/>		
		<td/>
	</tr>
<tr height=10></tr>
	<tr>	
			
		<td>	<!--<span style="color:red;"><?php echo form_error('Semail');?></span>-->
			<label>Email-Id</label></br>
	    <input type="email" name="Exemail" placeholder="Enter Your Email-Id" style="height:30px;width:98%;" value="<?php echo $this->email; ?>" readonly/>		
		<td/>
		</tr>
<tr height=30></tr>
<?php
 $compsubject = array();
    $elecsubject = array();
    for($i=0; $i<sizeof($subjectsem); $i++)
    {
        $subdata = $subjectsem[$i];
        $sub_data=explode('#',$subdata);
        $subid = $sub_data[0];
        $subtype = $sub_data[1];
        if($subtype == "Compulsory")
            $compsubject[] = $subid;
        else
            $elecsubject[] = $subid;
            
    }
?>
	<table class="TFtable" id="academic" style="width:52%;">	
			<thead id="styleTable"><th colspan=7>
			Details of earlier passed examination
			</th></thead>
			<thead id="acadhead2">
			<tr>
				<th><span style="float:left;">Programmes</span></th>
				<th><span style="float:left;">Year of passing</span></th>
				<th><span style="float:left;">Name of the board / university</span></th>
				
				<th><span style="float:left;">Marks Obtained / Total marks</span></th>
				<th><span style="float:left;">% of Marks</span></th>
				<th><span style="float:left;">Subjects</span></th>
			</tr>	
			</thead>
						
				<tbody>
				<?php foreach ($this->seresult as $row) {?>				
				<tr><td><?php echo $row->sedu_class;?></td>
				
				<td><?php echo $row->sedu_passingyear; ?></td>
				<td><?php echo $row->sedu_board; ?></td>
				
				<td><?php echo $row->sedu_marksobtained; ?></td>
				<td><?php echo $row->sedu_maxmarks; ?></td>
				<td><?php //echo $row->sedu_percentage; ?></td></tr>
				<?php }?>
								
			</tbody>
		</table>
	</br></br>
		<table class="TFtable" id="academic" style="width:52%;">	
			<thead id="styleTable"><th colspan=7>
			Title of the papers
			</th></thead>
			<thead id="acadhead2">
			<tr>
				<!--<th><span style="">S. No.</span></th>--->
				<th><span style="">Compulsory Papers</span></th>
				<th><span style="">Optional Papers</span></th>
				<th><span style="">Practical Papers</span></th>
			</tr>	
			</thead>
						
				<tbody align="center">
			<?php //$count=0;
    				echo "<tr>";	
					//echo"<td>";echo ++$count;echo "</td>";		
					 echo"<td>";
    						for($i = 0; $i<sizeof($compsubject); $i++)
    						{
        						$subjectdata = $this->commodel->get_listrow('subject','sub_id',$compsubject[$i]);
   							echo $subjectdata->row()->sub_name;echo "</br>";
    						}
  					echo"</td>";  

 					echo"<td>"; 
    						for($i = 0; $i<sizeof($elecsubject); $i++)
    						{
       							$elecsubjectdata = $this->commodel->get_listrow('subject','sub_id',$elecsubject[$i]);
        						echo $elecsubjectdata->row()->sub_name;echo "</br>";
    						}
  					echo"</td>";
   				echo "</tr>";
			?>
			</tbody>
		</table>
</br>
	<table style="width:50%;">
	<tr>
		<td  style="text-align:center;font-size:19px;"><p>Declaration</p></td>
	</tr><tr>
		<td style="text-align:justify;">I hereby affirm that i have filled this form myself, if it is discovered at any stage that i have given any false or incorrect information or concealed any fact or any fraudulent means have been used by me for appearing in the examination, I shall be liable to disciplinary action and my examination result may be cancelled. I shall abide all the rules and regulations of the university as prepared and enforced from time to time.</td></tr>
<tr>
<td><span style="margin-left:100px;">I shall abide all the rules and regulations of the university as prepared and enforced from time to time.</span></td>
	</tr></table>
	</br></br>
	<table style="width:54%;">
		<tr>
			<td><div style="float:left;">Date : <?php echo $this->cdate;?></br>Place : <?php echo $this->pcity;?></div></td>
			<td><div style="float:right"> <img src="<?php echo base_url('uploads/student_sign_photo/student_sign/'.$this->signresult); ?>" style="height:70px;width:200px;"> </div></td>
		</tr>
	</table>

	<tr height=30></tr>
	
	<tr>
		<td></td><td></td><td></td><td></td>
		<td><input type="submit" name="exm_regi" value="Submit" style="width:8%;height:35px;font-size:18px;"></td>
	</tr>
</table>	


</form>

</center>




<?php //$thisPage2="studentaddDetail";
	$this->load->view('template/footer'); ?>
</body>
</html>
