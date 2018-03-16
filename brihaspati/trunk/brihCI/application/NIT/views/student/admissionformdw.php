<!--@filename admissionformdw.php 
* @author sumit saxena(sumitsesaxena@gmail.com) *
-->

<html>
    <head>
        <title> </title>
	<style>
		.TFtable tr td{border:0px solid black;}	
	</style>
    </head>
    <body>
	<div style="border:2px solid black;">
		<!--<img src='<?php //echo base_url("uploads/logo/logo2.jpg");?>'  style="width:100%;height:70px;">-->
	<img src='<?php echo base_url("uploads/logo/niitsikkim.png");?>'  style="width:100%;height:90px;">
	<table style="width:100%;">
<tr><th>
	<center>Admission Form</center>
</th></tr></table>
        <table style="width:100%;" border=0 class="TFtable">
			<thead  style="background-color:#38B0DE;color:white;font-size:18px;"><tr><td colspan="4"><b>Personal Detail</b></td></tr></thead>
			<?php foreach ($this->pdetail as $row) {?>
			<tr>
				<td>Name of the candidate :</td>
				<td><?php echo $row->sm_fname;?></td>
				<td>Name of the course :</td>
			        <td><?php echo $this->pname;?></td></tr>
			
			<tr>
				<td>Mother name :</td>
				<td><?php echo $this->mname;?></td>
				<td>Father name :</td>
				<td><?php echo $this->fname;?></td>
			</tr>

			<tr>
				<td >Gender :</td>
				<td><?php echo $row->sm_gender;?></td>
				<td>Date of birth :</td>
				<td><?php echo $row->sm_dob;?></td>
			</tr>

			<tr>
					<td>Mobile/Phone number</td><td><?php echo $row->sm_mobile;?></td>
				
					<td>E-mail</td><td><?php echo $row->sm_email;?></td>
			</tr>
			<tr >
				<td>Category</td>
				<td >
					<?php echo $this->catename;//echo $this->Common_model->get_listspfic1('student_master','sm_category','sm_id',$id)->sm_category;?>
				</td>
				<td >Aadhar number :</td>
				<td><?php echo $row->sm_uid;?></td>
				
			</tr>
			<tr>
				
				<td>Blood group :</td>
				<td colspan=4><?php echo $row->sm_bloodgroup;?></td>
			</tr>
			<?php }?>
		</table>
	
	 <table style="width:100%;" class="TFtable" border=0>
			<thead  style="background-color:#38B0DE;color:white;font-size:18px;"><tr><td colspan="4"><b>Address Detail</b></td></tr></thead>
			<thead>
			<tr>
				<th style="text-align:justify;font-weight:bold;">Address</th>
				<th style="text-align:justify;font-weight:bold;">Permanent address</th>
				<th style="text-align:justify;font-weight:bold;">Correspondence address</th>
			</tr>
			</thead>

			<tbody>
				<?php  foreach ($this->adetail as $row) {?>
				<tr>
					<td>H.No./Apartment</td><td><?php echo $row->spar_paddress; ?></td>
					<td><?php echo $row->spar_caddress;?></td>
				</tr>
				<tr>
					<td>Street/Village/Taluka/city</td><td><?php echo $row->spar_pcity;?></td>
					<td><?php echo $row->spar_ccity;?></td>
				</tr>
				<tr>
					<td>Post office</td><td><?php echo $row->spar_ppostoffice;?></td>
					<td><?php echo $row->spar_cpostoffice;?></td>
				</tr>
				<tr>
					<td>District</td><td><?php echo $row->spar_pdistrict;?></td>
					<td><?php echo $row->spar_cdistrict;?></td>
				</tr>
				<tr>
					<td>State</td><td><?php echo $row->spar_pstate;?></td>
					<td><?php echo $row->spar_cstate;?></td>
				</tr>
				<tr>
					<td>Pincode</td><td><?php echo $row->spar_ppincode;?></td>
					<td><?php echo $row->spar_cpincode;?></td>
				</tr>
				<?php }?>
			</tbody>	
		</table>
<br>
         <div style="font-size:15px;">	
		<b>Note : GOI format of caste certificate should be submitted at the time of physical verification documents.</b>
		</div>	

		<table style="width:100%;margin-top:30px;" class="TFtable" border=0>	
			<thead style="background-color:#38B0DE;color:white;font-size:18px;"><tr><th style="text-align:justify;font-weight:bold;" colspan=7>
			Academic Detail
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
				<?php foreach ($this->edetail as $row) {?>				
				<tr>
				<td><?php echo $row->sedu_class;?></td>
				<td><?php echo $row->sedu_board; ?></td>
				<td><?php echo $row->sedu_passingyear; ?></td>
				<td><?php echo $row->sedu_resultstatus; ?></td>
				<td><?php echo $row->sedu_marksobtained; ?></td>
				<td><?php echo $row->sedu_maxmarks; ?></td>
				<td><?php echo $row->sedu_percentage; ?></td></tr>
				<?php }?>
								
			</tbody>
		</table>
	</br>
		<table class="TFtable"  style="width:100%;margin-top:30px;" border=0>
			<thead style="background-color:#38B0DE;color:white;font-size:18px;"><tr><th colspan=4 style="text-align:justify;font-weight:bold;">
			Fees Detail
			</th></tr></thead>
				<?php foreach ($this->fdetail as $row) {?>
				<tr>
					<td>Programme name:</td>
					<td><?php echo $this->pname; ?></td>
					<td>Amount:</td>
					<td><?php echo $row->sfee_feeamount;?></td>
				</tr>

				<tr>
					<td>Payment method:</td>
					<td><?php echo $row->sfee_paymentmethod;?></td>
					<td>Refference number:</td>
					<td><?php echo $row->sfee_referenceno;?></td>
				</tr>

				<tr>
					<td>Fees Id:</td>
					<td><?php echo $row->sfee_id;?></td>
					<td>Bank name:</td>
					<td><?php echo $row->sfee_bankname; ?></td>
				</tr>
	
				<?php }?>
		</table>
	  </div>

	<table style="width:100%;" id="">
		
<tr><td>
<p>
<h4 style="text-align:left;" id=""><b>Undertaking by the Students of IGNTU and Parent on Ragging </b></h4>
<ol style="font-size:10px;">
<li>
I declare that I shall abide by the admission rules and regulations of IGNTU Amarkantak and follow the code of conduct for students. I acknowledge that the University has the power to take disciplinary action against me for non-compliance of the same. 
</li>
<li>
That I have read and understood the directive of the Hon’ble Supreme Court of India on anti ragging and UGC regulations on curbing the menace of ragging in the higher educational institutions, 2009.</li>
<li> 
That I understand the meaning of ragging and know that the ragging in any form is a punishable offence and the same is banned by the Court of Law. I understand that, in case I am involved in ragging, the case will be reported to the police and let the law take its own course and I will be summarily expelled from the University, if found guilty.
</li>
<li> 
That I have not been found or charged for my involvement in any kind of ragging in the past. However, I Undertake to face disciplinary action/legal proceeding expiation from the University if the above statement is found to be untrue or the facts are concealed and which are established at any stage in future. 
</li>
<li>
That I shall not resort to ragging in any form at any place and shall abide by the rules/ laws prescribed by the Courts, Government of India and the University authorities for the perfuse from time to time.
</li>
<li> 
The University has No Tolerance Policy for Ragging by the students, whether inside or outside the premises of the University, should any incident of ragging be brought to the attention of any competent authorities, the University will verify the authenticity of the case and if any individual (s) are found guilty, they would be immediately terminated from their program and the University.
</li>
</ol> 


<ol style="list-style-type:none;margin-top:-130px;" id=""><li>
<h4><b>I have read the above Policy of the University and agree to abide by the same. </b></h4>
</li></ol>
</td>
</tr> 

<tr><td>
<h4 style="text-align:left;margin-top:-10px;" id=""><b>Declaration /undertaking from the student on Qualification and code of conduct </b></h4>
<ol style="font-size:10px;" id="">
<li>
I hereby declare that, the entries made by me in the Application Form and Admission Form are complete and true to the best of my knowledge and based on records. 
</li>
<li>
I hereby undertake to present the original documents immediately upon demand by the concerned authorities of the University. 
</li>
<li>
I further declare that, my admission may be cancelled, at any stage, if I am found ineligible and/or the information provided by me is found to be incorrect.
</li>
</li><li> 
I hereby promise to abide by the admission rules and regulations, concerning discipline, attendance, etc. of the University, and also to follow the Code of Conduct prescribed for the students of the University, as in force from time to time and subsequent changes/modifications/amendment made thereto. I acknowledge that the University has the authority for taking punitive actions against me for violation and/or non-compliance of the same. 
</li>
<li>
I understand that, 75% attendance for each course is compulsory and I commit myself to adhere to the same. I also understand, in case my attendance falls short, for any reason, the Competent Authority of the University may take such punitive action against me, as may be deemed fit and proper. 
</li>
<li>
I hereby declare that, I will neither join in any coercive agitation/strike for the purpose of forcing the authorities of the University to solve any problem, nor I will participate in any activity which has a tendency to disturb the peace and tranquility of life of the University campus and/or its Hostel premises. 
</li>
<li>
I understand that as per rules and regulations of the University, I will not be permitted to possess or use any motorized vehicle inside the University campus, unless I am permitted to do so by a written prior authorization from the Dean (Students’ Welfare). 
</li>
<li>
I hereby declare that, I shall be solely responsible for my involvement in any kind of undesirable /in disciplinary activities outside the campus, and shall be liable for punishment as per the law of the land. I, further understand that, the University shall in no way provide any support to me and will not be held responsible for my any such action.
</li>
<li> 
I hereby undertake to inform the University, about any changes in information submitted by me, in the Application Form and any other documents, including change in addresses and phone nos., from time to time.
</li>
</ol>
</td></tr>

 
<tr><td>
<h4 style="text-align:left;" id="">Declaration from the Student on Physical Fitness </b></h4>
<ol style="font-size:10px;">
<li>
I declare that I am not suffering from any serious/contagious ailment including psychology related symptoms. 
</li><li>
I also understand that the declaration on physical fitness submitted by me is correct. 
</li></ol>
</td></tr>

<tr><td>

<h4 style="text-align:left;" id=""><b>Declaration by the Candidate </b></h4>
<p style="font-size:10px;" id="">
I have carefully read all the instructions for filling up the application form and other documents. I, solemnly declare that the forgoing information in the application form is complete, correct & true to the best of my knowledge and belief. I also certify that I am liable for disciplinary action by the University if any information given in found false/incorrect. 
</p>
</td></tr>
</p>
</table>
    </body>  
</html>    
