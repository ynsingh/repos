<!-------------------------------------------------------
    -- @name attendencepdf.php --	
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
<body style="">
<?php //$this->load->view('template/watermark.php');?>				
<img src="uploads/logo/logo2.jpg" alt="logo" style="width:100%;height:70px;">
<center><h3>Attendence Sheet For All India Enterance Examination - <?php echo $this->acadyear;?></h3></center>
					
<center>

	<table style="width:100%;border:1px solid black;" align=center>
		<?php 
			//print_r($getatt);
	      		foreach($getatt as $row){
				$centerid = $row->ca_centername;
				$centername = $this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_name','eec_id',$centerid)->eec_name;
				$centercode = $this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_code','eec_id',$centerid)->eec_code;
				break;
			}
			$location = $this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_city','eec_name',$centername)->eec_city;
			?>			
			<tr>
			<td align=center colspan=8 height=30 style="vertical-align: bottom;">Name of Exam Center - <?php  echo	$centername.'('.$location.')'.','.'('.$centercode.')';?></td>
			</tr>	
			<?php 
			$getatt = $this->commodel->array_multi_subsort($getatt, 'ca_prgid');
			//print_r($getatt1);
			//die;
			$count=1;
			$prgid1=0;
	      		foreach($getatt as $row){
				$asmid=$row->ca_asmid;
				$prgid=$row->ca_prgid;
				if($prgid != $prgid1 ){
			?>
			<tr>
			<td style="border:1px solid black height:4%;" colspan=8>&nbsp; </td>
			</tr>
			<tr>
			
			<td style="border:1px solid black;" colspan=4>
			Programme : <?php 
					echo $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name.'('.$this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch.')';
			?>
			</td>
			<td style="border:1px solid black;" colspan=4>Date & Time of Enterance Exam : <?php 
			echo $this->commodel->get_listspfic1('admissionopen','admop_entexam_date','admop_prgname_branch',$prgid)->admop_entexam_date;
			?></td>
			
			</tr>
			<tr>
				<td style="border:1px solid black;">S.No.</td>
				<td style="border:1px solid black;">Roll No./Application No.</td>
				<td style="border:1px solid black;">Name of Applicants</td>
				<td style="border:1px solid black;">Father's Name</td>
				<td style="border:1px solid black;">Programme & Subject's</td>
				<td style="border:1px solid black;">Photo of Applicant</td>
				<td style="border:1px solid black;">Applicant Signature</td>	
				<td style="border:1px solid black;">Investigator Signature</td>
			</tr>
			<?php $prgid1=$prgid;}
				
	      		?>
			
			<tr>
			
			<td style="border:1px solid black;" align=center><?php echo $count++;?></td>
			<td style="border:1px solid black;"><?php echo $row->ca_rollno;?></td>
			<td style="border:1px solid black;"><?php echo $this->commodel->get_listspfic1('admissionstudent_master','asm_fname','asm_id',$row->ca_asmid)->asm_fname;?></td>
			<td style="border:1px solid black;"><?php echo $this->commodel->get_listspfic1('admissionstudent_parent','aspar_fathername','aspar_asmid',$row->ca_asmid)->aspar_fathername;?></td>
			<td style="border:1px solid black;" style="border:1px solid black;"><?php echo $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name.'('.$this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch.')'.'-'.$this->commodel->get_listspfic1('admissionstudent_education','asedu_subject','asedu_asmid',$row->ca_asmid)->asedu_subject;?></td>
			<td style="border:1px solid black;">
			<?php $photo=$this->commodel->get_listspfic1('admissionstudent_uploaddata','asupd_photo','asupd_asmid',$row->ca_asmid)->asupd_photo; ?>
				<img src=<?php echo base_url('uploads/SLCMS/enterence/'.$asmid.'/'.$photo); ?> style="height:70px;width:80px; "></td>
			<td style="border:1px solid black;"></td>
			<td style="border:1px solid black;"></td>
			
		</tr>
		<?php }?>
		</table>
	
	</br>
	
</center>
</body>

</html>

