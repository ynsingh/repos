<!-------------------------------------------------------
    -- @name adminstuadmitcardpdf.php.php.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
    -- @author neha khullar(nehukhullar@gmail.com)-- Modifications
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
	<!--<img src='<?php //echo base_url("uploads/logo/logo2.jpg");?>'  style="width:100%;height:70px;">-->
	<img src='<?php echo base_url("uploads/logo/niitsikkim.png");?>'  style="width:100%;height:90px;"><table style="font-size:18px;width:100%;text-align:center;" align=center>
	<tr>
		<td>Academic Session - <?php echo $currentacadyear;?><br>
		<b>Admit Card</b><br>
		<b>Note :</b> No correction / overwriting shall be accepted unless verified by the university authorities.</td>
	</tr>	
</table>
<table style="width:100%;" border=0>
	<tr>
		<td valign=top>
			<table style='width:100%;' border=0>
				<tr>
					<td  colspan=5><b>Center of Examination : </b><?php echo $scname;?></td>
				</tr>
				<tr>
					<td valign=top colspan=3><b>Name of Examinee : </b><?php echo $sname;?></td>
					<td valign=top><b>Course : </b><?php echo $coursename;?></td>
					<td valign=top><b>Semester : </b><?php echo $stu_sem;?></td>
				</tr>
				<tr>
					<td  colspan=5><b>Name of Examinee <br>(In Block Letters) : </b><?php echo strtoupper($sname);?></td>
				</tr>
				<tr>
					<td colspan=2><b>Roll Number : </b><?php echo $sturollno?></td>
					<td></td>
					<td colspan=2><b>Enrollment No. : </b><?php echo $stuenrollno;?></td>
				</tr>
			</table>		
		</td>
		<td valign=top width=80>
			<img src="<?php echo base_url('uploads/student_sign_photo/student_photo/'.$stuphoto);?>" height=80 style="width:100%;">	
		</td>
	</tr>
</table>

<table style='width:100%;' border=1>
	<tr>
		<td><b>Sr. No.</b></td><td><b>Paper Code</b></td><td><b>Title of Paper</b></td>
	</tr>
	<?php 
	$count=1;
	if(!empty($paper)){	
	foreach($paper as $data){
	
	?>	
	<tr>
		<td><?php echo $count++;?></td>
		<td><?php echo $data->subp_code;?></td>
		<td><?php echo $data->subp_name;?></td>	
	</tr>
	<?php }}?>
</table>

<table style='width:100%;margin-top:60px;'>
	
	<tr>
		<td>
		<img src="<?php echo base_url('uploads/student_sign_photo/student_sign/'.$stusign);?>" style="height:50px; width:170px;">	<br>
		Full Signature of the <br>Examinee</td>
		<td>
		<img src="<?php echo base_url('uploads/SLCMS/enterenceadmin_student/');?>" style="height:50px; width:170px;">	<br>
		Signature & Seal of Exam. Superintendent</td>
		
		<td align=right><img src="<?php echo base_url('uploads/SLCMS/enterenceadmin_student/coesign.png');?>" style="height:50px; width:170px;">
<br>Signature & Seal of Controller of Examination /RD</td>	
	</tr>
</table>
</body>

</html>

