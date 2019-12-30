<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name profiletab.php
@ author Nagendra Kumar Singh[nksinghiitk@gmail.com]
 -->
<head>
<style>
/* Style the buttons */
.btn {
    border: none;
    outline: none;
    padding: 10px 16px;
    background-color: #f1f1f1;
    cursor: pointer;
    font-size: 18px;
}

/* Style the active class, and buttons on mouse-over */
 .btn1:hover {
    background-color: lightgreen;
    color: white;
}

.active, .btn {
    background-color: #666;
    color: white;
}
</style>
</head>

	<table border=1 class="TFtable">
		<tr>
			<?php if(!empty($data->emp_photoname)):?>
                            <td><img src="<?php echo base_url('uploads/SIS/empphoto/'.$data->emp_photoname);?>"  alt="" v:shapes="_x0000_i1025" style="width:100%;height:170px;" id="pimg"></td>
                         <?php else :?>
                            <td><img src="<?php echo base_url('uploads/SIS/empphoto/empdemopic.png');?>"  alt="" v:shapes="_x0000_i1025" style="width:100%;height:170px;" id="pimg"></td>
			 <?php endif;?>
			
			
		</tr>
		<tr>
			<td> <?php echo $data->emp_name;?></td>
		</tr>
		<tr>	
			<td><font color="blue">Designation</font> </br> <?php echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$data->emp_desig_code)->desig_name;?></td>
		</tr>
		<tr>
			<td><font color="blue">Phone No. </font></br><?php echo $data->emp_phone;?></td>
		</tr>
	<!--	<tr>	
			<td>E-mail Id </br> <?php echo $data->emp_secndemail;?></td>
		</tr>-->
    	</table>
	<br>
<div id="myDIV">
	<table border=1 class="TFtable">
                <tr>
			<?php if($current == 'basic') { ?>
				<td class=btn active>
			<?php }else{ ?>
				<td class=btn1>
			<?php } ?>
			<b>  <a href='<?php echo site_url()."/report/viewfull_profile/".$emp_id?>' style="font-size:17px;color:#0099CC;text-decoration: none">Basic Profile</a> </b></td>
		</tr>
                <tr>
			<?php if($current == 'academic') { ?>
				<td class=btn active>
			<?php }else{ ?>
				<td class=btn1>
			<?php } ?>
			<b>  <a href='<?php echo site_url()."/report/academic_profile/".$emp_id?>' style="font-size:17px;color:#0099CC;text-decoration: none">Academic Qualification</a> </b></td>
		</tr>
                <tr>
			<?php if($current == 'technical') { ?>
				<td class=btn active>
			<?php }else{ ?>
				<td class=btn1>
			<?php } ?>
			<b>  <a href='<?php echo site_url()."/report/technical_profile/".$emp_id?>' style="font-size:17px;color:#0099CC;text-decoration: none">Technical Qualification</a> </b></td>
		</tr>
                <tr>
			<?php if($current == 'promotional') { ?>
				<td class=btn active>
			<?php }else{ ?>
				<td class=btn1>
			<?php } ?>
			<b>  <a href='<?php echo site_url()."/report/promotional_profile/".$emp_id?>' style="font-size:17px;color:#0099CC;text-decoration: none">Promotional Details</a></b> </td>
		</tr>
                <tr>
			<?php if($current == 'service') { ?>
				<td class=btn active>
			<?php }else{ ?>
				<td class=btn1>
			<?php } ?>
			<b>  <a href='<?php echo site_url()."/report/service_profile/".$emp_id?>' style="font-size:17px;color:#0099CC;text-decoration: none">Service Particulars</a></b> </td>
		</tr>
                <tr>
			<?php if($current == 'addional') { ?>
				<td class=btn active>
			<?php }else{ ?>
				<td class=btn1>
			<?php } ?>
			<b>  <a href='<?php echo site_url()."/report/addionalassign_profile/".$emp_id?>' style="font-size:17px;color:#0099CC;text-decoration: none">Addional Assignment Particulars</a></b> </td>
		</tr>
                <tr>
			<?php if($current == 'perform') { ?>
				<td class=btn active>
			<?php }else{ ?>
				<td class=btn1>
			<?php } ?>
			<b>  <a href='<?php echo site_url()."/report/performance_profile/abs/".$emp_id?>' style="font-size:17px;color:#0099CC;text-decoration: none">Performance Details</a></b> </td>
		</tr>
		<?php if($roleid == 1){ ?>
                <tr>
			<?php if($current == 'leave') { ?>
				<td class=btn active>
			<?php }else{ ?>
				<td class=btn1>
			<?php } ?>
			<b>  <a href='<?php echo site_url()."/report/leave_profile/".$emp_id?>' style="font-size:17px;color:#0099CC;text-decoration: none">Leave Particulars</a></b> </td>
		</tr> 
	<?php }  ?>
                <tr>
			<?php if($current == 'deputation') { ?>
				<td class=btn active>
			<?php }else{ ?>
				<td class=btn1>
			<?php } ?>
			<b>  <a href='<?php echo site_url()."/report/deputation_profile/".$emp_id?>' style="font-size:17px;color:#0099CC;text-decoration: none">Deputation Particulars</a></b> </td>
		</tr>
                <tr>
			<?php if($current == 'deptexam') { ?>
				<td class=btn active>
			<?php }else{ ?>
				<td class=btn1>
			<?php } ?>
			<b>  <a href='<?php echo site_url()."/report/deptexam_profile/".$emp_id?>' style="font-size:17px;color:#0099CC;text-decoration: none">Departmental Exam Passed Details</a></b> </td>
		</tr>
                <tr>
			<?php if($current == 'workorder') { ?>
				<td class=btn active>
			<?php }else{ ?>
				<td class=btn1>
			<?php } ?>
			<b>  <a href='<?php echo site_url()."/report/workorder_profile/".$emp_id?>' style="font-size:17px;color:#0099CC;text-decoration: none">Working Arrangement Particulars</a></b> </td>
		</tr>
                <tr>
			<?php if($current == 'recruit') { ?>
				<td class=btn active>
			<?php }else{ ?>
				<td class=btn1>
			<?php } ?>
			<b>  <a href='<?php echo site_url()."/report/recruit_profile/".$emp_id?>' style="font-size:17px;color:#0099CC;text-decoration: none">Recruitment Particulars</a></b> </td>
		</tr>
                <tr>
			<?php if($current == 'disciplin') { ?>
				<td class=btn active>
			<?php }else{ ?>
				<td class=btn1>
			<?php } ?>
			<b>  <a href='<?php echo site_url()."/report/disciplin_profile/".$emp_id?>' style="font-size:17px;color:#0099CC;text-decoration: none">Disciplinary Action Details</a></b> </td>
		</tr>
	</table>
</div>
