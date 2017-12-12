<!--@filename step_fivedw.php  
 * @author sumit saxena(sumitsesaxena@gmail.com) *
-->

<html>
    <head>
        <title> </title>
	
    </head>
    <body>
	<?php //$this->load->view('template/watermark.php');?>

	<div style="border:2px solid black;">
		<img src="uploads/logo/logo2.jpg" alt="logo" style="width:100%;height:70px;">
	<table style="width:100%;">
<tr><th>
	<center style="background-color:#38B0DE;color:white;font-size:20px;opacity:1.5;font-weight:bold;">Entrance Application Form Details</center>
</th></tr></table>
		<table width="100%" border=0>
                <tr><td>
                        <table class="TFtable" id="personal" style="width:100%;border:0px;">
                        <tr>
                                <td>Course Applied For :</td>
                                <td><?php echo $prgname.'('.$prgbranch.')';?></td>
                        </tr>
                        <tr>
                                <td>Study Center :</td>
                                <td><?php echo $scname;?></td>
                        </tr><tr>
                                <td>Entrance Exam Center :</td>
                                <td><?php echo $exname ." ( ".$excode ." )";?></td>
                        </tr>
                        </table>
                </td><td>
                        <div id="photo">
                        <img src="<?php echo base_url('uploads/SLCMS/enterence/'.$id.'/'.$photo); ?>" >
                        </div>
                </td></tr>
                </table>

        <table style="width:100%;border:1px solid black;">
			<thead style="background-color:#38B0DE;color:white;font-size:18px;width:100%;"><tr><th colspan=5 style="text-align:justify;">Personal Details</th></tr></thead>
			
			<tr>	
				<td>Name of the candidate :</td>
				<td><?php echo $name;?></td>
				<td>E-mail :</td>
				<td><?php echo $email;?></td>
			</tr>
			<tr>
				<td>Mobile/Phone Number :</td>
				<td><?php echo $mobile;?></td>
				<td >Gender :</td>
				<td><?php echo $gender;?></td>
				
			</tr>
			<tr>
				<td>Date of birth :</td>
				<td><?php echo $dob;?></td>
				<td>Age :</td>
				<td><?php echo $age.'Years';?></td>
				
			</tr>
			<tr>
				<td>Maritial Status :</td>
				<td><?php echo $mastatus;?></td>
				<td>Category :</td>
				<td><?php echo $category;?></td>

			</tr>
			<tr>
				<td>Nationality :</td>
				<td><?php echo $nationality;?></td>
				<td>Are You Physically Handicapped ? :</td>
				<td><?php echo $phyhandi;?></td>
			</tr>
			<tr>
				<td valign=top>Religion :</td>
				<td valign=top><?php echo $religion;?></td>
				<td style="width:20%;" valign=top>Reservation :</td>
				<td style="width:30%;"><?php echo $reservation;?></td>
			</tr>	
			<tr>
				<td>Aadhar Number :</td>
				<td colspan=4><?php echo $aadhar;?></td>
			</tr>
		</table>
		<table style="width:100%;margin-top:30px;border:1px solid black;">
			<thead style="background-color:#38B0DE;color:white;font-size:18px;width:100%;"><tr><th colspan=4 style="text-align:justify;">Parent Details</th></tr></thead>
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

		</table>

		<table  style="width:100%;margin-top:30px;border:1px solid black;" class="TFtable" id="padd">
			<thead style="background-color:#38B0DE;color:white;font-size:18px;">
			<tr>
				<th><span style="float:left;">Address :</span></th>
				<th><span style="float:left;">Permanent Address :</span></th>
				<th colspan=2><span style="float:left;">Correspondence Address :</span></th>
			</tr>
			</thead>

			<tbody>
				<tr>
					<td>H.No./Appartment :</td><td><?php echo $paddress; ?></td>
					<td colspan=2><?php echo $caddress;?></td>
				</tr>
				<tr>
					<td>Street/Village/Taluka/city :</td><td><?php echo $pcity;?></td>
					<td colspan=2><?php echo $ccity;?></td>
				</tr>
				<!---<tr>
					<td>Post office</td><td><?php //echo $this->ppost;?></td>
					<td colspan=2><?php //echo $this->cpost;?></td>
				</tr>--->
				
				<tr>
					<td>State :</td><td><?php echo $pstate;?></td>
					<td colspan=2><?php echo $cstate;?></td>
				</tr>
				<tr>
					<td>Country :</td><td><?php echo $pcountry;?></td>
					<td colspan=2><?php echo $ccountry;?></td>
				</tr>
				<tr>
					<td>Pincode :</td><td><?php echo $ppincode;?></td>
					<td colspan=2><?php echo $cpincode;?></td>
				</tr>
				
			</tbody>	
		</table>

		<table style="width:100%;margin-top:30px;border:1px solid black;" class="TFtable">	
			<thead style="background-color:#38B0DE;color:white;font-size:18px;"><tr><th  style="text-align:justify;" colspan=7>
			Academic Details
			</th></tr></thead>
			<thead>
			<tr>
				<th style="text-align:justify;font-weight:bold;">Programmes</th>
				<th style="text-align:justify;font-weight:bold;">Board/university</th>
				<th style="text-align:justify;font-weight:bold;">Completion year</th>
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
<?php if(!empty($admission_entexm)){ ?>
		<table style="width:100%;margin-top:30px;border:1px solid black;">	
			<thead style="background-color:#38B0DE;color:white;font-size:18px;"><tr><th colspan=7  style="text-align:justify;">
			Enterance Exam Details
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
<?php }?>


	<?php if(!empty($admission_employment)){ ?>
		<table style="width:100%;margin-top:30px;border:1px solid black;">	
			<thead style="background-color:#38B0DE;color:white;font-size:18px;"><tr><th colspan=7 style="text-align:justify;">
			Employement Details
			</th></tr></thead>
			<thead id="acadhead2">
			<tr>
				<th style="text-align:justify;font-weight:bold;">Office Name</th>
				<th style="text-align:justify;font-weight:bold;">Post Name</th>
				<th style="text-align:justify;font-weight:bold;">Basic Pay</th>
				<th style="text-align:justify;font-weight:bold;">Appoinment Nature</th>
				<th style="font-weight:bold;">Date of Joining</th>
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
	<?php }?>
		<table style="width:100%;margin-top:30px;border:1px solid black;">
			<thead style="background-color:#38B0DE;color:white;font-size:18px;"><tr><th colspan=6  style="text-align:justify;">
			<span>Fees Details</span></tr></th></thead>
			<thead id="acadhead2"><tr>
			<th style="text-align:justify;font-weight:bold;">Fees Name</th>
			<th style="text-align:justify;font-weight:bold;">Amount</th>
			<th style="text-align:justify;font-weight:bold;">Payment Method</th>
			<th style="text-align:justify;font-weight:bold;">Reference Number</th>
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
				<img src="<?php echo base_url('uploads/SLCMS/enterence/'.$id.'/'.$signature); ?>" style="width:150px;height:50px;margin-left:75%;"></br>
				<label style="margin-left:75%;font-weight:bold;">Student Signature</label></td>
				
			</tr>
		</table>

  </div>


    </body>  
</html>    
