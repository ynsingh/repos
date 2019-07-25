<!-------------------------------------------------------
    -- @name stu_admitcarddw.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
--------------------------------------------------------->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?>
<!DOCTYPE html>
<html>
<head>
<title></title>
  <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

</head>
<body style="">
<?php //$this->load->view('template/watermark.php');
	$this->load->view('template/header'); 
?>
	
<!--<img src='<?php //echo base_url("uploads/logo/niitsikkim.png");?>'  style="width:100%;height:90px;">-->
<!--<center>
	<div class="panel panel-primary">
      	<div class="panel-heading"><h4>Admit Card - <?php //echo $acadyer;?></h4></div>
      	<div class="panel-body">

	<table border=0 style="width:95%;" align=center>
	<tr >
                  <td align=center style="border:1px solid black;">Program : <?php echo $prgname.'( '.$brname.' )';?></td>
		 <td align=center style="border:1px solid black;">Program Code :<?php echo $prgid; ?></td>
         </tr>

	<tr>
	<td valign=top>
		<table border=0 style="width:100%;">
			<tr><td width=170 style="border:1px solid black;"><b>Candidate Name : </b></td><td style="border:1px solid black;"><?php //echo $name; ?></td></tr>
			<tr><td style="border:1px solid black;"><b>Father Name :</b></td><td style="border:1px solid black;"><?php //echo $fname; ?></td></tr>
			<tr><td style="border:1px solid black;"><b> Mother Name : </b></td><td style="border:1px solid black;"><?php //echo $mname; ?></br></br></td></tr>
			<tr><td style="border:1px solid black;" valign=top><b> Address : </b></td><td style="border:1px solid black;"><?php //echo $paddress?></td></tr>	
										
		</table>
		<table border=0  style="width:100%;">
			<tr><td width=170 style="border:1px solid black;"><b>Exam Name & Date / Time :</b></td>
			<?php //foreach($exam as $row){
				//$examdate = $row->exsc_examdatetime;
				//$examnameid = $row->exsc_examname;
				//$examname = $this->commodel->get_listspfic1('examtype','exty_name','exty_id',$examnameid)->exty_name;
				
			?>
				<td style="border:1px solid black;" valign=top><?php //echo $examname.' <b>/</b> '.$examdate;?></td>
			<?php //}?>
			</tr>
			<tr>
			<td style="border:1px solid black;" valign=top><b>Venue :</b></td>
			<?php //foreach($exam as $row1){
				//$exvenueid = $row1->exsc_centerid;
				///$examvenue = $this->commodel->get_listspfic1('student_examcenter','sec_name','sec_id',$exvenueid)->sec_name;
				//$examadd = $this->commodel->get_listspfic1('student_examcenter','sec_address','sec_id',$exvenueid)->sec_address;
				//$examcity = $this->commodel->get_listspfic1('student_examcenter','sec_city','sec_id',$exvenueid)->sec_city;
			?>
				<td style="border:1px solid black;"><?php //echo $examvenue.' , '.$examadd.' , '.$examcity;?></td>
			<?php //}?>	
			</tr>
		</table>

		<table border=0  style="width:100%;">
			<tr><td width=170 style="border:1px solid black;"><b>Category :</b></td>
			<td style="border:1px solid black;"><?php  //echo $caste;?></td>
			</tr>
			<tr>
				<td style="border:1px solid black;"><b>Gender :</b></td>
				<td style="border:1px solid black;"><?php //echo $gender; ?></td>	
			</tr>
		</table>
	</td>
	<td width=150 valign=top>
		<table style="" border=0 align=center>
			<tr><td align=center style="border:1px solid black;"><b>Roll No. :</b></br><?php //echo $rollno;?></td></tr>
			 <tr><td align=center style="border:1px solid black;"><img src=<?php //echo base_url('uploads/student_sign_photo/student_photo/'.'/'.$phresult); ?> style="height:175px;width:170px; "></td></tr>
		 	<tr><td align=center style="border:1px solid black;"><img src=<?php //echo base_url('uploads/student_sign_photo/student_sign/'.'/'.$signresult); ?> style="height:50px; width:170px; "></td></tr>
		</table>
	</td>
	</tr>

        </table>
		

		<table style="width:98%;" border=0  align=center> 
			<tr>
				<td align=center><br><br>
				
				<br>
				Signature of Candidate</br>(To be signed in the presence of the invigilator)</td>
				<td align=right><img src="<?php //echo base_url('uploads/SLCMS/enterenceadmin_student/coesign.png');?>" style="height:50px; width:170px; ">
<br>Controller of Examinations</td>
			</tr>
		</table>
						
  </center>
</br>
					
					
<center>
	<div  style="width:96%;margin-top:10px;" >
	<span style="font-size:14px;"><b>ATTENTION:</b></span>
            <p style="text-align:left;font-weight:normal;margin-top:0px;font-size:12px;"><b>Note:</b> Candidates who have applied online are hereby asked to bring the hard copy of the application form along with necessary certificates & Challan /DD and submit the same to the invigilator at the examination hall failing which they will not be allowed for the exam.</p>
                   
             <span style="margin-top:-12px;font-size:14px;"><b>IMPORTANT INSTRUCTIONS TO THE CANDIDATES</b></span>
                 <ol style="text-align:justify;font-weight:normal;margin-top:0px;font-size:12px;" >
                      <li>
                           The candidates should reach the examination hall 30 minutes prior to commencement of the examination for submitting 					necessary enclosures along with the application form.
                       	 </li>
                        
                        <li>
                           	No candidate will be allowed to enter the examination hall after 30 minutes of commencement of the examination.
                        </li>
			 <li>
                          	 No candidate will be allowed to leave the hall 30 minutes prior to completion of the exam.
                        </li>
			<li>
                           The candidate shall carry into the examination hall only (i) Blue/Black BallPoint Pen (ii) Hall Ticket along with Valid Photo ID proof.
                       </li>
                        <li>
       				Carrying of Calculators, Mathematical/Log Tables, Pagers, Cell Phones, any other Electronic Gadgets and loose sheets of papers into the examination hall is strictly prohibited. However, candidates appearing for MSc and PhD in Chemistry and Biology, Betony,Zoology Bio­technology, Environmental Sciences are allowed to use scientific calculators only.
       
                        </li>
                       <li>
                           Adoption of any kind of unfair means and any act of impersonation at the time of test will render the applicant liable for invalidation of his/her OMR answer sheet. Further he/she will forfeit the claim of appearing for the test and will make him/her liable for criminal
action.
                        </li>
                       	<li>
                           	Issue of Hall Ticket and appearance at the test do not automatically entitle the candidate for admission to any program.
                       	</li>
                        <li>
                            	Hall Ticket must be preserved till the time of admission.
                        </li>
                               
                    </ol>
</div>
	</div>	
                			
</center>-->
<?php $dirpath = 'uploads/SLCMS/adminstudent_exam/'.$acadyer.'/admit_card/'.$deptid.'/'.$Stuid.'.pdf';
	if(file_exists($dirpath)) {

?>
	    <table style='width:100%;'>
	    <tr><td>

			<table style='width:100%;'>
				<tr><td align=left>

				<!--<a href="<?php //echo base_url($dirpath);?>" target=_blank style="font-size:20px;">View Admit Card</a>-->
				</td>
				
				</tr>
				<tr><td align=left colspan=2>
					<embed src="<?php echo base_url($dirpath);?>" type="application/pdf"   style='width:100%;height:600px;'>
				</td></tr>
				<tr>
					<td align=right>
						<a href="<?php echo base_url($dirpath);?>" target=_blank style="font-size:20px;" download>
							<img src='<?php echo base_url("uploads/logo/print1.png");?>' title="Download Admit Card">
						</a>
					</td>
				</tr>
			</table>

		  </td>
	
	</tr>
	</table>
<?php } else
  	{ //echo '<br><b>Your Admit Card Is Not Generated By Administrator.</b>';
	$this->session->set_flashdata('success','Your Admit Card Is Not Generated By Administrator.');	
	redirect('studenthome');	
		}

?>


<?php //$thisPage2="studentaddDetail";
	$this->load->view('template/footer'); ?>
</body>

</html>

