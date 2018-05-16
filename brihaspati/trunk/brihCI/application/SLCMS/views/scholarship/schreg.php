<!-------------------------------------------------------
    -- @name schreg.php --	
    -- @author Krishna Sahu(krishnasahu2406@gmail.com) --
--------------------------------------------------------->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');


?><!DOCTYPE html>
<html lang="en">
<head>
	<?php $this->load->view('template/header'); ?>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
  
</head>
<body>


<?php
echo "<center>";

	if($this->session->flashdata('msg')){
echo "<div style='font-size:20px;text-align:center;background-color:#DFF2BF;width:50%;height:30px;color:green;'>";
	echo $this->session->flashdata('msg');
echo "<div>";	
}

echo "</center>";
?>
<table width="100%">
           <tr><td>
		<div>
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                <?php if(isset($_SESSION['success'])){?>
                <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                <?php
                };
                ?>
                <?php if(isset($_SESSION['error'])){?>
                    <div class="isa_error"><?php echo $_SESSION['error'];?></div>
                <?php
                };
               ?>
                            </div>
             </td></tr>
        </table>


<form action="<?php echo site_url('scholarship/schreg'); ?> "  method="POST" >
	<table  style="width:100%; border:1px solid gray;text-align:left;" >
		<tr><thead><th  style="background-color:#2a8fcf;text-align:left;height:40px;font-size:20px;" colspan="10">&nbsp;&nbsp;Scholarship Registration Form</th></thead></tr>
		<tr>	
		<td>
			
			
			<label for="sname" style="font-size:15px;">Student Name</label></br>
			<input type="text" size="30" name="schname" placeholder="Enter your exam name" value="<?php echo $this->name;?>" readonly/>	
		<td/>
		
		<td>	
			<!---<span style="color:red;"><?php echo form_error('Smothername');?></span>--->
			<label for="smname" style="font-size:15px;">Mother Name</label></br>	
			<input type="text" size="30" name="schmname" placeholder="Enter Mother Name" value="<?php echo $this->mname; ?>" readonly/>		
		<td/>
		<td>	
			<!---<span style="color:red;"><?php echo form_error('Sfathername');?></span>--->
			<label style="font-size:15px;">Father Name</label></br>
			<input type="text" size="30" name="schfname" placeholder="Enter Father Name" value="<?php echo $this->fathname; ?>" readonly/>		
		<td/>
		</tr>
		<tr>
	

		<td>	<!---<span style="color:red;"><?php echo form_error('Sgender');?></span>--->
			<label style="font-size:15px;">Gender</label></br>
			<input type="text" size="30" name="schgender" placeholder="Enter gender" value="<?php echo $this->gender;?>" readonly/>	
			<!--<input type="text" name="Sgender" placeholder="Enter Gender" >--->		
		<td/>

		
		<td>
			
		<!---<span style="color:red;"><?php echo form_error('Sdob');?></span>-->

		<label style="font-size:15px;">Date of Birth</label></br>
		<input type="text" size="30" name="schdob" placeholder="Enter date of birth" value="<?php echo $this->dobb[0];?>" readonly/>
		<td/>
		<td>	
			<!---<span style="color:red;"><?php echo form_error('Scategory');?></span>--->
			<label for="nnumber" style="font-size:15px;">Category</label></br>
			<input type="text" size="30" name="schcat" placeholder="Enter category" value="<?php echo $this->catename;?>" readonly/>	
		<td/>
		</tr>
		<tr>
	
		<td>	<!---<span style="color:red;"><?php echo form_error('Spincode');?></span>--->
			<label style="font-size:15px;">Semester</label></br>
			<input type="text" size="30" name="schsem" placeholder="Enter Your Pincode" MaxLength="6" value="<?php echo $this->sem; ?>" readonly/>		
		<td/>


		
		<td>	<!---<span style="color:red;"><?php echo form_error('Spaddress');?></span>--->
			<label style="font-size:15px;">Address</label></br>
			<input type="text" size="30" name="schaddress" placeholder="Enter Postal Address" value="<?php echo $this->schadd; ?>" readonly/>		
		<td/>
		

		
		<td>	<!---<span style="color:red;"><?php echo form_error('Scity');?></span>--->
			<label for="nnumber" style="font-size:15px;">City</label></br>	
			<input type="text" size="30" name="schcity" placeholder="Enter Your District/City" value="<?php echo $this->schcity; ?>" readonly/>		
		<td/>
		</tr><tr>
	
			<td>	<!---<span style="color:red;"><?php echo form_error('Sstate');?></span>--->
			<label style="font-size:15px;">State</label></br>
			<input type="text" size="30" name="schstate" placeholder="Enter Your State" value="<?php echo $this->schstat; ?>" readonly/>		
		<td/>
		
	
			<td>	<!---<span style="color:red;"><?php echo form_error('Spincode');?></span>--->
			<label style="font-size:15px;">Country</label></br>
			<input type="text" size="30" name="schcountry" placeholder="Enter Your Country" value="<?php echo $this->schcounname; ?>" readonly/>		
		<td/>
		<td>	<!---<span style="color:red;"><?php echo form_error('Spincode');?></span>--->
			<label style="font-size:15px;">Pincode</label></br>
			<input type="text" size="30" name="schpincode" placeholder="Enter Your Pincode" MaxLength="6" value="<?php echo $this->schpin; ?>" readonly/>		
		<td/>
		</tr><tr>
		<td>	<!---<span style="color:red;"><?php echo form_error('Smobile');?></span>--->
			<label style="font-size:15px;">Mobile/Phone no.</label></br>
			<input type="text" size="30" name="schmobile" placeholder="Enter Mobile Number" MaxLength="10" pattern="/^+91(7\d|8\d|9\d)\d{9}$/" value="<?php echo $this->mobile; ?>" readonly/>		
		<td/>
		<td>	<!---<span style="color:red;"><?php echo form_error('Saadharnumber');?></span>--->
			<label for="aadhar" style="font-size:15px;">Aadhaar Number</label></br>	
			<input type="text" size="30" name="Saadharnumber" placeholder="Enter Aadhaar Number" MaxLength="12" value="<?php echo $this->uid; ?>" readonly/>		
		<td/>
		
		<td>	<!--<span style="color:red;"><?php echo form_error('Semail');?></span>-->
			<label style="font-size:15px;">Email-Id</label></br>
	   		<input type="email" size="30" name="Semail" placeholder="Enter Your Email-Id"  value="<?php echo $this->email; ?>" readonly/>		
		<td/>
                </tr><tr>

		
		<td>
			<label for="deptname" style="font-size:15px;">Department Name</label></br>
			<input type="text" size="30" name="Sdepart" placeholder="Enter department" value="<?php echo $this->depname;?>" readonly/>			
		<td/>
		
		<td>	<!---<span style="color:red;"><?php echo form_error('Sreligion');?></span>--->
			<label style="font-size:15px;">Religion</label></br>
			<input type="text" size="30" name="Sreligion" placeholder="Enter religion" value="<?php echo $this->religname;?>" readonly/>	
		<td/>
		<td>	
			<label for="branch" style="font-size:15px;">Branch Name</label></br>
			<input type="text" size="30" name="Sbranch" placeholder="Enter your brach name" value="<?php echo $this->brname;?>" readonly/><td/>		
		
		</tr><tr>
		<td>	
			<label for="branch" style="font-size:15px;">Program Name</label></br>
			<input type="text" size="30" name="Sprog" placeholder="Enter your brach name" value="<?php echo $this->pname;?>" readonly/><td/>
		<!--<td>
			<label for="lastyerres" style="font-size:15px;">Last Year Result<font color='Red'>*</font></label></br>
			<select name="sa_lastyerres" style="width:338px;">
                        <option value=""disabled selected>---Select Last Year Result---</option>
                        
 				 <option value="PASS">PASS</option>
 				 <option value="FAIL">FAIL</option>
			</select>
                        		
		<td/>	-->	
		
		<td>
			<label style="font-size:15px;">Scholarship Name:<font color='Red'>*</font></label></br>
			<select name="sa_name" style="width:338px;">
                        <option value=""disabled selected>---Select Scholarship Name---</option>
                        <?php foreach($this->schname as $datas): ?>
                        <option value="<?php echo $datas->sch_id; ?>"><?php echo $datas->sch_name; ?></option>
                        <?php endforeach; ?>
                        </select>
                        
		<td/>
		
		<td>
			<label for="bname" style="font-size:15px;">Bank Name<font color='Red'>*</font></label></br>
			<input type="text" size="30" name="sa_bname" placeholder="Enter Bank Name" value="" />			
		<td/>	
		</tr><tr>
		<td>
			<label for="baccno" style="font-size:15px;">Acoount Number<font color='Red'>*</font></label></br>
			<input type="text" size="30" name="sa_accno" placeholder="Enter Account Number" value="" MaxLength="15" />			
		<td/>

		<td>
			<label for="bifscno" style="font-size:15px;">IFSC Code<font color='Red'>*</font></label></br>
			<input type="text" size="30" name="sa_ifscno" placeholder="Enter IFSC Number" value="" />			
		<td/>
		     
		<td>
			<label for="bbrname" style="font-size:15px;">Bank Branch Name<font color='Red'>*</font></label></br>
			<input type="text" size="30" name="sa_branch" placeholder="Enter Branch Name" value="" />			
		<td/>
	        </tr>
		<tr style="background-color:#2a8fcf;text-align:left;height:40px;">
	            <td colspan="10"><input type="submit" name="schreg" value="Submit"  "onclick="return confirm('Are you sure you want to submit Scholarship request')"></td>
	</tr>
		

	</table>

	

        

	
</form>
</br></br>

	
<?php 
$this->load->view('template/footer'); ?>
</body>
</html>
