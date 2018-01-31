
<!-------------------------------------------------------
    -- @name adminstu_attsheetpdf.php --	
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
<div style="">	
	<img src='<?php echo base_url("uploads/logo/logo2.jpg");?>'  style="width:100%;height:70px;">
</div>

<table style="font-size:18px;width:100%;text-align:center;" align=center>
	<tr>
		<td><b>Academic Session - <?php echo $currentacadyear;?></b><br>
		 <b>Attendence Sheet</b><br>
	</tr>	
</table>


<table style="width:100%;" border=0>


<tr>
<?php
$i=0;
$count=1;
$count1=1;
	if(!empty($stugetsttsheet)){
		foreach($stugetsttsheet as $data){
			$smid   = $data->sp_smid;
			//$acadyear = $this->commodel->get_listspfic1('student_program','sp_acadyear','sp_smid',$smid)->sp_acadyear;
			$stcid = $this->commodel->get_listspfic1('student_program','sp_sccode','sp_smid',$smid)->sp_sccode;
			$deptid = $this->commodel->get_listspfic1('student_program','sp_deptid','sp_smid',$smid)->sp_deptid;
			$prgid = $this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$smid)->sp_programid;
			$cname = $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$stcid)->sc_name;
			$stusign = $this->commodel->get_listspfic1('student_master','sm_signature','sm_id',$smid)->sm_signature;
			$stu_sem = $this->commodel->get_listspfic1('student_program','sp_semester','sp_smid',$smid)->sp_semester;
			$prgname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name.'( '.$this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch.' )';
			$stu_name = $this->commodel->get_listspfic1('student_master','sm_fname','sm_id',$smid)->sm_fname;
			$stu_enroll = $this->commodel->get_listspfic1('student_master','sm_enrollmentno','sm_id',$smid)->sm_enrollmentno;
			$stu_roll = $this->commodel->get_listspfic1('student_entry_exit','senex_rollno','senex_smid',$smid)->senex_rollno;

?>
<td style="border:0px solid black;">

	<tr>
		<td valign=top>
			<table style='width:100%;' border=0>
				<tr>
					<td  colspan=5><b>Center of Examination : </b><?php echo $cname;?></td>
				<tr>
					<td valign=top><b>Program : </b><?php echo $prgname;?></td>
					<td valign=top><b>Semester : </b><?php echo $stu_sem;?></td>
				</tr>
			</table>		
		</td>
		

	</tr>
	
	<tr>
		<td valign=top>
			<table style='width:100%;' border=1>
			
				<tr>
					<td width=50><b>Sr. No.</b></td><td width=250><b>Exam Name</b></td><td><b>Exam Date & Time</b>
				</tr>
				
					
					<?php 
						$wheredata = array('exsc_prgid' => $prgid,'exsc_acadyear' =>$currentacadyear ,'exsc_centerid' => $stcid,'exsc_semester' => $stu_sem);
       						$sfield = 'exsc_examname,exsc_examdatetime';
        					$exam =  $this->commodel->get_listspficemore('studentexam_schedule',$sfield,$wheredata);
						foreach($exam as $record){
						$examid = $record->exsc_examname;
					?><tr>
							<td valign=top><?php echo $count++;?></td>
							<td valign=top><!--<b>Name of Exam : </b>--><?php echo $this->commodel->get_listspfic1('examtype','exty_name','exty_id',$examid)->exty_name ;?></td>
							<td valign=top><!--<b>Date of Exam: </b>--><?php echo $record->exsc_examdatetime;?></td>
					 </tr>
					<?php }?>
				
			</table>		
		</td>
	</tr>


		<tr>
		<td>
		<!-------------------------------------student subject & paper get------------------------------------------>
			<table style='width:100%;' border=1>
			
						<tr>
							<td width=50><b>Sr. No.</b></td><td width=250><b>Subject Name</b></td><td><b>Paper Name</b>
						</tr>
						<?php 
							$wheredata = array('sub_program' => $prgid,'sub_semester' => $stu_sem);
       							$selectfield = 'sub_name,sub_id';
        						$subjname =  $this->commodel->get_listspficemore('subject',$selectfield,$wheredata);
							foreach($subjname as $data1){	
							$subid = $data1->sub_id;
						?>
						<?php 
							$wheredata = array('subp_degree' => $prgid,'subp_sem' => $stu_sem,'subp_sub_id' => $subid);
       							$selectfield = 'subp_name';
        						$paper =  $this->commodel->get_listspficemore('subject_paper',$selectfield,$wheredata);
							foreach($paper as $data){
						?>
						<tr>
							<td valign=top><?php echo $count1++;?></td>
								
								<td><?php echo $data1->sub_name;?></td>
							
								<td><?php echo $data->subp_name;?></td>
							
						</tr>
						<?php } }?>
			   </table>	
			<!-------------------------------------student subject & paper get end------------------------------------------>

		</td>
	</tr>

	<tr>
		<td>
			<table style='width:100%;' border=1>
						<tr>
							<!--<td><b>Sr. No.</b></td>--><td><b>Name of Student</b></td><td><b>Roll No.</b></td><td><b>Enrollment No.</b></td><td><b>Sign of Student</b></td>
						</tr>
							<tr>
								<!--<td valign=top><?php //echo $count++;?></td>-->
								<td valign=top><?php echo $stu_name;?></td>
								<td valign=top><?php echo $stu_roll;?></td>	
								<td valign=top><?php echo $stu_enroll;?></td>
								<td><img src="<?php echo base_url('uploads/student_sign_photo/student_sign/'.$stusign);?>" style="width:100%;height:40px;"></td>
							</tr>
			   </table>	
		</td>
	</tr>
	
	<tr>
		<td >
		<table style="width:100%;margin-top:50px;">
		<tr>
		<td>
		<img src="<?php echo base_url('uploads/student_sign_photo/student_sign/'.$stusign);?>" style="height:50px; width:170px;">	<br>
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
	if($i%1 == 0){
?>
	</tr>
	<tr><td><p style="page-break-before: always;"><img src='<?php echo base_url("uploads/logo/logo2.jpg");?>'  style="width:100%;height:70px;"></p></td></tr>
<tr>
	
<?php  }}} ?>
</tr>	


</table>




</body>

</html>

