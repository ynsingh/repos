
<!-------------------------------------------------------
    -- @name adminstu_formverifiepdf.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
--------------------------------------------------------->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?>
<!DOCTYPE html>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
	
<img src='<?php echo base_url("uploads/logo/logo2.jpg");?>'  style="width:100%;height:70px;">

<table style="font-size:18px;width:100%;text-align:center;" align=center>
	<tr>
		<td>Academic Session - <?php echo $currentacadyear;?><br>
		 <b>Attestation Card</b><br>
		<b>Note :</b> No correction / overwriting shall be accepted unless verified by the university authorities.</td>
	</tr>	
</table>


<table style="width:70%;" border=0>


<tr>
<?php
$i=0;
	if(!empty($stuget)){
		foreach($stuget as $data){
			$smid   = $data->sp_smid;
			$stcid = $this->commodel->get_listspfic1('student_program','sp_sccode','sp_smid',$smid)->sp_sccode;
			$deptid = $this->commodel->get_listspfic1('student_program','sp_deptid','sp_smid',$smid)->sp_deptid;
			$prgid = $this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$smid)->sp_programid;
			$cname = $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$stcid)->sc_name;
			$stuphoto = $this->commodel->get_listspfic1('student_master','sm_photo','sm_id',$smid)->sm_photo;
			$stu_sem = $this->commodel->get_listspfic1('student_program','sp_semester','sp_smid',$smid)->sp_semester;
			$stu_sem = $this->commodel->get_listspfic1('student_program','sp_semester','sp_smid',$smid)->sp_semester;
			$prgname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name.'( '.$this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch.' )';
			$stu_name = $this->commodel->get_listspfic1('student_master','sm_fname','sm_id',$smid)->sm_fname;
			$stu_enroll = $this->commodel->get_listspfic1('student_master','sm_enrollmentno','sm_id',$smid)->sm_enrollmentno;
			$stu_roll = $this->commodel->get_listspfic1('student_entry_exit','senex_rollno','senex_smid',$smid)->senex_rollno;
			 

?>
<td style="border:0px solid black;">

	<tr>
		<td valign=top >
			<table style='width:100%;' border=0>
				<tr>
					<td  colspan=2><b>Center of Examination : </b><?php echo $cname;?></td>
				</tr>
				<tr>
					<td valign=top width=180><b>Name of Examinee : </b><?php echo $stu_name;?></td>
					<td valign=top width=150><b>Course : </b><?php echo $prgname;?></td>
					<td valign=top width=70><b>Semester : </b><?php echo $stu_sem;?></td>
				</tr>
				<tr>
					<td  width=180><b>Name of Examinee <br>(In Block Letters) : </b><?php echo strtoupper($stu_name);?></td>
				</tr>
				<tr>
					<td width=200><b>Roll No. : </b><?php echo $stu_enroll;?></td>
				
					<td width=150><b>Enrollment No. : </b><?php echo $stu_roll ;?></td>
				</tr>
			
			</table>		
		</td>
		<td valign=top width=80>
			<img src="<?php echo base_url('uploads/student_sign_photo/student_photo/'.$stuphoto);?>" height=80 style="width:100%;">	
		</td>

	</tr>

	<tr>
		<td colspan=6>
		<!-------------------------------------student paper get------------------------------------------>
			<table style='width:100%;' border=1>
						<tr>
							<td><b>Sr. No.</b></td><td><b>Date of Exam</b></td><td><b>Title of Paper</b></td><td><b>Signature of the Examinee</b></td><td><b>Signature of Invigilator</b></td>
						</tr>
					<?php 
						$wheredata = array('exsc_prgid' => $prgid,'exsc_acadyear' =>$currentacadyear ,'exsc_centerid' => $stcid,'exsc_semester' => $stu_sem);
       						$sfield = 'exsc_examname,exsc_examdatetime,exsc_paperid';
        					$exam =  $this->commodel->get_listspficemore('studentexam_schedule',$sfield,$wheredata);
						foreach($exam as $record){
						$paperid = $record->exsc_paperid;
					?>
					<?php 
						$wheredata = array('subp_degree' => $prgid,'subp_sem' => $stu_sem,'subp_acadyear' => $currentacadyear,'subp_id' => $paperid);
       						$selectfield = 'subp_name';
        					$paper =  $this->commodel->get_listspficemore('subject_paper',$selectfield,$wheredata);
						$count=1;
						if(!empty($paper)){	
							foreach($paper as $data){
							$sub_name = $data->subp_name;
					?>	
					
							<tr>
								<td><?php echo $count++;?></td>
								<td>
								<?php echo $record->exsc_examdatetime;?>
								</td>
								<?php if(!empty($sub_name)){ ?>
									<td><?php echo $sub_name;?></td>
								<?php }?>	
								<td><?php if(!empty($stusign)){?>
		<img src="<?php echo base_url('uploads/student_sign_photo/student_sign/'.$stusign);?>" style="height:50px; width:170px;"><?php }else{?>
		<img src="<?php echo base_url('uploads/student_sign_photo/student_sign/');?>" style="height:50px; width:170px;">
		<?php }?>	<br>
								</td>
								<td><img src="<?php echo base_url('uploads/SLCMS/enterenceadmin_student/coesign.png');?>" style="height:50px; width:170px;"></td>
							</tr>
							
						<?php }}}?>
					
			   </table>	
			<!-------------------------------------student paper get end------------------------------------------>

		</td>
	</tr>
	
	<tr>
		<td colspan=6>
		<table style="width:100%;margin-top:50px;">
		<tr>
		<td>
		<?php if(!empty($stusign)){?>
		<img src="<?php echo base_url('uploads/student_sign_photo/student_sign/'.$stusign);?>" style="height:50px; width:170px;"><?php }else{?>
		<img src="<?php echo base_url('uploads/student_sign_photo/student_sign/');?>" style="height:50px; width:170px;">
		<?php }?>
		<br>
		Full Signature of the <br>Examinee</td>
		<td>
		<img src="<?php echo base_url('uploads/SLCMS/enterenceadmin_student/coesign');?>" style="height:50px; width:170px;">	<br>
		Signature & Seal of Exam. Superintendent</td>
		
		<td align=right><img src="<?php echo base_url('uploads/SLCMS/enterenceadmin_student/coesign.png');?>" style="height:50px; width:170px;">
<br>Signature & Seal of Controller of Examination /RD</td>	
	</tr></table>
		</td>
	</tr>
</td>

<?php $i++;
	if($i%1 == 0){?>
	</tr>
	<tr><td colspan=6><p style="page-break-before: always;"><img src='<?php echo base_url("uploads/logo/logo2.jpg");?>'  style="width:100%;height:70px;"></p></td></tr>
<tr>
<?php  }}} ?>
</tr>	


</table>




</body>

</html>

