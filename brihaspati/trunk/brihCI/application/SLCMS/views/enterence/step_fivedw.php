<!--@filename step_fivedw.php  
 * @author sumit saxena(sumitsesaxena@gmail.com) *
-->

<html>
    <head>
        <title> </title>
	
    </head>
    <body>
	<?php $this->load->view('template/watermark.php');?>

	<div style="border:2px solid black;">
		<img src="uploads/logo/logo2.jpg" alt="logo" style="width:100%;height:70px;">
	<table style="width:100%;">
<tr><th>
	<center style="background-color:#38B0DE;color:white;font-size:20px;opacity:1.5;font-weight:bold;">Admission Form</center>
</th></tr></table>
	<table style="width:100%;">
		<tr><td>
		<img src="<?php echo base_url('uploads/SLCMS/enterence/'.$id.'/'.$photo); ?>" style="width:90px;height:80px;margin-left:80%;">
		</td></tr>
	</table>

        <table style="width:100%;">
			<thead style="background-color:#38B0DE;color:white;font-size:18px;width:100%;"><tr><th colspan=7 style="text-align:justify;">Personal Detail</th></tr></thead>
			
			<tr>
				<td>Name of the candidate :</td>
				<td><?php echo $name;?></td>
				<td>Name of the Course :</td>
			        <td><?php echo $prgname.'('.$prgbranch.')';?></td></tr>
			
			<tr>
				<td>Mother Name :</td>
				<td><?php echo $mname;?></td>
				<td>Father Name :</td>
				<td><?php echo $fname;?></td>
			</tr>
			<tr>
				<td>Mother Mobile Number :</td>
				<td><?php echo $mmo;?></td>
				<td>Father Mobile Number :</td>
				<td><?php echo $fmo;?></td>
			</tr>
			<tr>
				<td>Mother Occupation :</td>
				<td><?php echo $moccupation;?></td>
				<td>Father Occupation :</td>
				<td><?php echo $foccupation;?></td>
			</tr>

			<tr>
				<td >Gender :</td>
				<td><?php echo $gender;?></td>
				<td>Date of birth :</td>
				<td><?php echo $dob;?></td>
			</tr>

			<tr>
				<td>Mobile/Phone Number</td><td><?php echo $mobile;?></td>
				<td>E-mail</td><td><?php echo $email;?></td>
			</tr>
			<tr >
				<td>Category</td>
				<td >
					<?php echo $category;?>
				</td>
				<!--<td >Aadhar Number :</td>
				<td><?php echo $this->uid;?></td>-->
				
			</tr>
			<!---<tr>
				<td>Blood Group :</td>
				<td colspan=4><?php echo $this->bgroup;?></td>
			</tr>--->
		</table>

		<table  style="width:100%;margin-top:30px;" class="TFtable" id="padd">
			<thead style="background-color:#38B0DE;color:white;font-size:18px;">
			<tr>
				<th><span style="float:left;">Address</span></th>
				<th><span style="float:left;">Permanent Address</span></th>
				<th colspan=2><span style="float:left;">Correspondence Address</span></th>
			</tr>
			</thead>

			<tbody>
				<tr>
					<td>H.No./Apartment</td><td><?php echo $paddress; ?></td>
					<td colspan=2><?php echo $caddress;?></td>
				</tr>
				<tr>
					<td>Street/Village/Taluka/city</td><td><?php echo $pcity;?></td>
					<td colspan=2><?php echo $ccity;?></td>
				</tr>
				<!---<tr>
					<td>Post office</td><td><?php echo $this->ppost;?></td>
					<td colspan=2><?php echo $this->cpost;?></td>
				</tr>--->
				
				<tr>
					<td>State</td><td><?php echo $pstate;?></td>
					<td colspan=2><?php echo $cstate;?></td>
				</tr>
				<tr>
					<td>District</td><td><?php echo $pcountry;?></td>
					<td colspan=2><?php echo $ccountry;?></td>
				</tr>
				<tr>
					<td>Pincode</td><td><?php echo $ppincode;?></td>
					<td colspan=2><?php echo $cpincode;?></td>
				</tr>
				
			</tbody>	
		</table>

		<table style="width:100%;margin-top:30px;" class="TFtable">	
			<thead style="background-color:#38B0DE;color:white;font-size:18px;"><tr><th  style="text-align:justify;" colspan=7>
			Academic Detail
			</th></tr></thead>
			<thead>
			<tr>
				<th style="text-align:justify;font-weight:bold;">Programmes</th>
				<th style="text-align:justify;font-weight:bold;">Board/university</th>
				<th style="text-align:justify;font-weight:bold;">Complition year</th>
				<th style="text-align:justify;font-weight:bold;">Passed/Appeared</th>
				<th style="text-align:justify;font-weight:bold;">Marks obtained</th>
				<th style="text-align:justify;font-weight:bold;">Max. marks</th>
				<th style="text-align:justify;font-weight:bold;">Percentage</th>
			</tr>	
			</thead>
						
				<tbody>
				<?php foreach ($admission_academic as $row) {?>				
				<tr><td><?php echo $row->asedu_class;?></td>
				<td><?php  echo $row->asedu_board; ?></td>
				<td><?php echo $row->asedu_passingyear; ?></td>
				<td><?php echo $row->asedu_resultstatus; ?></td>
				<td><?php echo $row->asedu_marksobtained; ?></td>
				<td><?php echo $row->asedu_maxmarks; ?></td>
				<td><?php echo $row->asedu_percentage; ?></td></tr>
				<?php }?>
								
			</tbody>
		</table>

		</br>
		<table style="width:100%;margin-top:30px;">	
			<thead style="background-color:#38B0DE;color:white;font-size:18px;"><tr><th colspan=7  style="text-align:justify;">
			Enterance Exam Detail
			</th></tr></thead>
			<thead id="acadhead2">
			<tr>
				<th style="text-align:justify;font-weight:bold;">Exam Name</th>
				<th style="text-align:justify;font-weight:bold;">Roll Number</th>
				<th style="text-align:justify;font-weight:bold;">Exam Rank</th>
				<th style="text-align:justify;font-weight:bold;">Exam Score</th>
				<th style="text-align:justify;font-weight:bold;">Exam State</th>
				<!--<th><span style="float:left;">Exam Subjects</span></th>
				<th><span style="float:left;">Exam Passing Year</span></th>-->
			</tr>	
			</thead>
						
				<tbody>
				<?php foreach ($admission_entexm as $row) {?>				
				<tr><td><?php echo $row->aseex_examname;?></td>
				<td><?php  echo $row->aseex_rollno; ?></td>
				<td><?php echo $row->aseex_rank; ?></td>
				<td><?php echo $row->aseex_score; ?></td>
				<td><?php echo $row->aseex_state; ?></td>
				<!--<td><?php //echo $row->aseex_subject; ?></td>
				<td><?php //echo $row->aseex_passingyear; ?></td>
				-->
			</tr>
				<?php }?>
								
			</tbody>
		</table>


		<table style="width:100%;margin-top:30px;">	
			<thead style="background-color:#38B0DE;color:white;font-size:18px;"><tr><th colspan=7 style="text-align:justify;">
			Employement Detail
			</th></tr></thead>
			<thead id="acadhead2">
			<tr>
				<th style="text-align:justify;font-weight:bold;">Office Name</th>
				<th style="text-align:justify;font-weight:bold;">Post Name</th>
				<th style="text-align:justify;font-weight:bold;">Basic Pay</th>
				<th style="text-align:justify;font-weight:bold;">Appoinment Nature</th>
				<th style="text-align:justify;font-weight:bold;">Date of Joining</th>
				<!--<th><span style="float:left;">Exam Subjects</span></th>-->
				<th style="text-align:justify;font-weight:bold;">Remrks</th>
				<th style="text-align:justify;font-weight:bold;">Total Experience</th>
			</tr>	
			</thead>
						
				<tbody>
				<?php foreach ($admission_employment as $row) {?>				
				<tr><td><?php echo $row->asemp_officename;?></td>
				<td><?php  echo $row->asemp_post; ?></td>
				<td><?php echo $row->asemp_pay; ?></td>
				<td><?php echo $row->asemp_appoinmentnature; ?></td>
				<td><?php echo $row->asemp_doj; ?></td>
				<!--<td><?php //echo $row->asemp_dol; ?></td>-->
				<td><?php echo $row->asemp_remarks; ?></td>
				<td><?php echo $row->asemp_experience; ?></td>	
			</tr>
				<?php }?>
								
			</tbody>
		</table>

		<table style="width:100%;margin-top:30px;">
			<thead style="background-color:#38B0DE;color:white;font-size:18px;"><tr><th colspan=6  style="text-align:justify;">
			<span>Fees Detail</span></tr></th></thead>
			<thead id="acadhead2"><tr>
			<th style="text-align:justify;font-weight:bold;">Fees Name</th>
			<th style="text-align:justify;font-weight:bold;">Amount</th>
			<th style="text-align:justify;font-weight:bold;">Payment Method</th>
			<th style="text-align:justify;font-weight:bold;">Refference Number</th>
			<th style="text-align:justify;font-weight:bold;">Fees Id</th>
			<th style="text-align:justify;font-weight:bold;">Bank Name</th>
			</tr></thead>
			<tbody><tr>
			<?php foreach($admission_fees as $row){?>
				<td><?php echo $row->asfee_feename;?></td>
				<td><?php echo $row->asfee_feeamount;?></td>
				<td><?php echo $row->asfee_paymentmethod;?></td>
				<td><?php echo $row->asfee_referenceno;?></td>
				<td><?php echo $row->asfee_id;?></td>
				<td><?php echo $row->asfee_bankname; ?></td>
			<?php }?>
			</tr>
			</tbody>
		</table>

		<table id="" style="width:100%;">
			<tr>
			<td style="float:right;">
				<label style="margin-left:75%;font-weight:bold;">Student Signature</label></br>
				<img src="<?php echo base_url('uploads/SLCMS/enterence/'.$id.'/'.$signature); ?>" style="width:150px;height:50px;margin-left:75%;"></td>
				
			</tr>
		</table>

  </div>


    </body>  
</html>    
