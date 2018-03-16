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
		<!--<img src="uploads/logo/logo.jpg" alt="logo" style="width:100%;height:70px;">-->
		<img src="uploads/logo/niitsikkim.png" alt="logo" style="width:100%;height:70px;">
<table style="width:100%;">
<tr><th>
	<center style="background-color:#38B0DE;color:white;font-size:20px;opacity:1.5;font-weight:bold;">Admission Application Form Details</center>
</th></tr></table>
	<table style="width:100%;">
		<tr>

		<td width=77%;>	

        <table style="width:100%;border:1px solid black;">
			<thead style="background-color:#38B0DE;color:white;font-size:18px;width:100%;"><tr><th colspan=5 style="text-align:justify;">Personal Details</th></tr></thead>
			
			
			<tr>
				<td>Name of the candidate :</td>
				<td><?php echo $this->sname;?></td>
				<td>Name of the Course :</td>
			        <td><?php echo $this->pname;?></td></tr>
			
			<tr>
				<td>Mother Name :</td>
				<td><?php echo $this->mname;?></td>
				<td>Father Name :</td>
				<td><?php echo $this->fname;?></td>
			</tr>

			<tr>
				<td >Gender :</td>
				<td><?php echo $this->gender;?></td>
				<td>Date of birth :</td>
				<td><?php echo $this->dob;?></td>
			</tr>

			<tr>
					<td>Mobile/Phone Number</td><td><?php echo $this->mobile;?></td>
				
					<td>E-mail</td><td><?php echo $this->email;?></td>
			</tr>
			<tr >
				<td>Category</td>
				<td>
					<?php echo $this->catename;//echo $this->Common_model->get_listspfic1('student_master','sm_category','sm_id',$id)->sm_category;?>
				</td>
				<td >Aadhar Number :</td>
				<td><?php echo $this->uid;?></td>
				
			</tr>
			<tr>
				
				<td>Blood Group :</td>
				<td ><?php echo $this->bgroup;?></td>
				<td>Roll Number :</td>
				<td ><?php echo $this->rollno;?></td>
			</tr>
		</table>
		</td>
		
		<td valign=top >
			<img src="<?php echo base_url('uploads/student_sign_photo/student_photo/'.$this->phresult); ?>" height=170 style="width:150px;">
		</td>
		</tr>
	</table>

		<table  style="width:100%;margin-top:30px;border:1px solid black;" class="TFtable" >
			<thead style="background-color:#38B0DE;color:white;font-size:18px;">
			<tr>
				<th><span style="float:left;">Address :</span></th>
				<th><span style="float:left;">Permanent Address :</span></th>
				<th colspan=2><span style="float:left;">Correspondence Address :</span></th>
			</tr>
			</thead>
<tbody>
				<tr>
					<td>H.No./Apartment</td><td><?php echo $this->padd; ?></td>
					<td colspan=2><?php echo $this->cadd;?></td>
				</tr>
				<tr>
					<td>Street/Village/Taluka/city</td><td><?php echo $this->pcity;?></td>
					<td colspan=2><?php echo $this->ccity;?></td>
				</tr>
				
				<tr>
					<td>State</td><td><?php echo $this->cstat;?></td>
					<td colspan=2><?php echo $this->cstat;?></td>
				</tr>
				<tr>
					<td>Pincode</td><td><?php echo $this->ppin;?></td>
					<td colspan=2><?php echo $this->cpin;?></td>
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
				<th style="text-align:justify;font-weight:bold;">Complition year</th>
				<th style="text-align:justify;font-weight:bold;">Passed/Appeared</th>
				<th style="text-align:justify;font-weight:bold;">Marks obtained</th>
				<th style="text-align:justify;font-weight:bold;">Max. marks</th>
				<th style="text-align:justify;font-weight:bold;">Percentage</th>
			</tr>	
			</thead>
						
			</thead>
						
				<tbody>
				<?php foreach ($this->seresult as $row) {?>				
				<tr><td><?php echo $row->sedu_class;?></td>
				<td><?php  echo $row->sedu_board; ?></td>
				<td><?php echo $row->sedu_passingyear; ?></td>
				<td><?php echo $row->sedu_resultstatus; ?></td>
				<td><?php echo $row->sedu_marksobtained; ?></td>
				<td><?php echo $row->sedu_maxmarks; ?></td>
				<td><?php echo $row->sedu_percentage; ?></td></tr>
				<?php }?>
								
			</tbody>
		</table>
		
		<table style="width:100%;margin-top:30px;border:1px solid black;">
			<thead style="background-color:#38B0DE;color:white;font-size:18px;"><tr><th colspan=6  style="text-align:justify;">
			<span>Fees Details</span></tr></th></thead>
			<thead id="acadhead2"><tr>
			<th style="text-align:justify;font-weight:bold;">Programme Name</th>
			<th style="text-align:justify;font-weight:bold;">Amount</th>
			<th style="text-align:justify;font-weight:bold;">Payment Method</th>
			<th style="text-align:justify;font-weight:bold;">Refference Number</th>
			<th style="text-align:justify;font-weight:bold;">Fees Id</th>
			<th style="text-align:justify;font-weight:bold;">Bank Name</th>
			</tr></thead>
			<tbody><tr>
			
				<td><?php echo $this->prog;?></td>
				<td><?php echo $this->amnt?></td>
				<td><?php echo $this->pmethod;?></td>
				<td><?php echo $this->rno;?></td>
				<td><?php echo $this->fid;?></td>
				<td><?php echo $this->bname; ?></td>
			
			</tr>
			</tbody>
		</table>
	
<table style="width:100%;margin-top:30px;border:1px solid black;" >
<tr><td align=center>
<span style="font-size:17px;text-align:center;text-decoration:underline;"><b>Terms & Conditions</b></span><br>		
</td></tr>

<tr><td style="width:100%;">
<p>

<div style="margin-top:-350px;font-size:8px;width:100%;">
<span style="text-align:justify;font-size:14px;" ><b>Undertaking by the Students of IGNTU and Parent on Ragging </b></span><br>
I declare that I shall abide by the admission rules and regulations of IGNTU Amarkantak and follow the code of conduct for students. I acknowledge that the University has the power to take disciplinary action against me for non-compliance of the same. 
<br>
That I have read and understood the directive of the Hon’ble Supreme Court of India on anti ragging and UGC regulations on curbing the menace of ragging in the higher educational institutions, 2009.
<br>
That I understand the meaning of ragging and know that the ragging in any form is a punishable offence and the same is banned by the Court of Law. I understand that, in case I am involved in ragging, the case will be reported to the police and let the law take its own course and I will be summarily expelled from the University, if found guilty.
 <br>
That I have not been found or charged for my involvement in any kind of ragging in the past. However, I Undertake to face disciplinary action/legal proceeding expiation from the University if the above statement is found to be untrue or the facts are concealed and which are established at any stage in future. 
<br>
That I shall not resort to ragging in any form at any place and shall abide by the rules/ laws prescribed by the Courts, Government of India and the University authorities for the perfuse from time to time.
<br>
The University has No Tolerance Policy for Ragging by the students, whether inside or outside the premises of the University, should any incident of ragging be brought to the attention of any competent authorities, the University will verify the authenticity of the case and if any individual (s) are found guilty, they would be immediately terminated from their program and the University.

</div>
<br>
<span style="text-align:justify;font-size:14px;"><b>I have read the above Policy of the University and agree to abide by the same. </b></span>
<br>

</td>
</tr> 

<tr style="margin-top:-140px;"><td >
<div style="font-size:8px;width:100%;">
<span style="text-align:justify;font-size:14px;"><b>Declaration /undertaking from the student on Qualification and code of conduct </b></span>
<br>
I hereby declare that, the entries made by me in the Application Form and Admission Form are complete and true to the best of my knowledge and based on records. 
<br>
I hereby undertake to present the original documents immediately upon demand by the concerned authorities of the University. 
<br>
I further declare that, my admission may be cancelled, at any stage, if I am found ineligible and/or the information provided by me is found to be incorrect.
<br>
I hereby promise to abide by the admission rules and regulations, concerning discipline, attendance, etc. of the University, and also to follow the Code of Conduct prescribed for the students of the University, as in force from time to time and subsequent changes/modifications/amendment made thereto. I acknowledge that the University has the authority for taking punitive actions against me for violation and/or non-compliance of the same. 
<br>
I understand that, 75% attendance for each course is compulsory and I commit myself to adhere to the same. I also understand, in case my attendance falls short, for any reason, the Competent Authority of the University may take such punitive action against me, as may be deemed fit and proper. 
<br>
I hereby declare that, I will neither join in any coercive agitation/strike for the purpose of forcing the authorities of the University to solve any problem, nor I will participate in any activity which has a tendency to disturb the peace and tranquility of life of the University campus and/or its Hostel premises. 
<br>
I understand that as per rules and regulations of the University, I will not be permitted to possess or use any motorized vehicle inside the University campus, unless I am permitted to do so by a written prior authorization from the Dean (Students’ Welfare). 
<br>
I hereby declare that, I shall be solely responsible for my involvement in any kind of undesirable /in disciplinary activities outside the campus, and shall be liable for punishment as per the law of the land. I, further understand that, the University shall in no way provide any support to me and will not be held responsible for my any such action.
<br>
 
I hereby undertake to inform the University, about any changes in information submitted by me, in the Application Form and any other documents, including change in addresses and phone nos., from time to time.
</div>

</td></tr>

 
<tr style="margin-top:-80px;"><td>

<div style="font-size:8px;width:100%;">
<span style="text-align:justify;font-size:14px;"><b>Declaration from the Student on Physical Fitness </b></span><br>
I declare that I am not suffering from any serious/contagious ailment including psychology related symptoms. <br>
I also understand that the declaration on physical fitness submitted by me is correct. 
</div>
</td></tr>

<tr><td>

<div style="font-size:8px;width:100%;">
<span style="text-align:justify;font-size:14px;"><b>Declaration by the Candidate </b></span><br>

I have carefully read all the instructions for filling up the application form and other documents. I, solemnly declare that the forgoing information in the application form is complete, correct & true to the best of my knowledge and belief. I also certify that I am liable for disciplinary action by the University if any information given in found false/incorrect. 
</div>
</td></tr>

</table>
		
		
		<table id="" style="width:100%;">
		<tr>
		<td style="float:right;">
		<img src="<?php echo base_url('uploads/student_sign_photo/student_sign/'.$this->signresult); ?>" style="width:150px;height:50px;margin-left:75%;"></br>
		<label style="margin-left:75%;font-weight:bold;">Student Signature</label>
		</td>
		</tr>
		</table>

  </div>


    </body>  
</html>    
