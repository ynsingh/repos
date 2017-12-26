
   <table class="table table-bordered" style="background-color:white;">
	<thead>
		<tr>
			<th colspan=19></th>
			
			<th colspan=13></th>
			<th colspan=6>High School Qualification  Details</th>
			<th colspan=6>Intermediate Qualification Details</th>
			<th colspan=6>Graduation Qualification  Details</th>
			<th colspan=6>Post Graduation Qualification Details</th>
			<th colspan=7>Any other Qualification Details</th>
			<th colspan=4>JEE Main Exam Details</th>
			<th colspan=4>CMAT Exam Details</th>
			<th colspan=4>GATE Exam Details</th>
			<th colspan=4>M.Phil Exam Details</th>
			<th colspan=4>NET Exam Details</th>
			<th colspan=4>SLET Exam Details</th>
		</tr>
	</thead>
		
        <thead>
            <tr>

		
                <th>Sr. No.</th>
		<th>Hall Ticket No.</th>
		<th>Application No</th>
		<th>Entrance Exam Center</th>
		<th>Study Center</th>
		<th>Program Category</th>
		<th>Program & Branch Applied</th>
		<th>Entrance Exam Center</th>
		<th>Name of The Applicant</th>
		<th>Email</th>
		<th>Mobile</th>
		<th>Gender</th>
		<th>Date of Birth</th>
		<th>Age</th>
		<th>Maritial Status</th>
		<th>Category</th>

		<th>Are You Physically Handicapped</th>
		<th>% of Disibility</th>
		<th>Disibility Type</th>
		
		<th>Religion</th>
		<th>Nationality</th>
		<th>Present State</th>

		<th>Address for Correspondence</th>
		<th>Permanant Address</th>
		<th>Father's Name</th>
		<th>Mother's Name</th>	
		<th>Father's Mobile No.</th>
		<th>Mother's Mobile No.</th>
		<th>Father's Occupation</th>
		<th>Mother's Occupation</th>
		<th>Father's Email-id</th>
		<th>Mother's Email-id</th>
		<!--<th colspan=55></th>-->
		
			<th>Name of Institute (10th)</th>
			<th>Board / University (10th)</th>
			<th>Year of Passing (10th)</th>
			<th>Marks Obtained (10th)</th>
			<th>Max Marks (10th)</th>
			<th>% of Marks (10th)</th>

			<th>Name of Institute (12th)</th>
			<th>Board / University (12th)</th>
			<th>Year of Passing (12th)</th>
			<th>Marks Obtained (12th)</th>
			<th>Max Marks (12th)</th>
			<th>% of Marks (12th)</th>

			<th>Name of Institute (Graduation)</th>
			<th>Board / University (Graduation)</th>
			<th>Year of Passing (Graduation)</th>
			<th>Marks Obtained (Graduation)</th>
			<th>Max Marks (Graduation)</th>
			<th>% of Marks (Graduation)</th>

			<th>Name of Institute (Post Graduation)</th>
			<th>Board / University (Post Graduation)</th>
			<th>Year of Passing (Post Graduation)</th>
			<th>Marks Obtained (Post Graduation)</th>
			<th>Max Marks (Post Graduation)</th>
			<th>% of Marks (Post Graduation)</th>

			<th>Qualification Name (Other)</th>
			<th>Name of Institute (Other)</th>
			<th>Board / University (Other)</th>
			<th>Year of Passing (Other)</th>
			<th>Marks Obtained (Other)</th>
			<th>Max Marks (Other)</th>
			<th>% of Marks (Other)</th>	
			
			<!--<th>Name of Institute (Mphil)</th>
			<th>Board / University (Mphil)</th>
			<th>Year of Passing (Mphil)</th>
			<th>Marks Obtained (Mphil)</th>
			<th>Max Marks (Mphil)</th>
			<th>% of Marks (Mphil)</th>
	
			<th>Name of Institute(UGC/NET/SET/SLET/GATE)</th>
			<th>Board / University(UGC/NET/SET/SLET/GATE)</th>
			<th>Year of Passing(UGC/NET/SET/SLET/GATE)</th>
			<th>Marks Obtained(UGC/NET/SET/SLET/GATE)</th>
			<th>Max Marks(UGC/NET/SET/SLET/GATE)</th>
			<th>% of Marks(UGC/NET/SET/SLET/GATE)</th>
			<th colspan=3></th>-->
			<th>JEE Main Roll No</th>
			<th>JEE Main Rank</th>
			<th>JEE Main Score</th>
			<th>JEE Main State</th>

			<th>CMAT Roll No</th>
			<th>CMAT Rank</th>
			<th>CMAT Score</th>
			<th>CMAT State</th>
		
			<th>GATE Roll No</th>
			<th>GATE Rank</th>
			<th>GATE Score</th>
			<th>GATE State</th>

			<th>M.Phil Roll No</th>
			<th>M.Phil Rank</th>
			<th>M.Phil Score</th>
			<th>M.Phil State</th>

			<th>NET Roll No</th>
			<th>NET Rank</th>
			<th>NET Score</th>
			<th>NET State</th>

			<th>SLET Roll No</th>
			<th>SLET Rank</th>
			<th>SLET Score</th>
			<th>SLET State</th>

		
		<th>Name of Post</th>	
		<th>Present Pay & Grade</th>
		<th>Nature of Appointment</th>
		<th>Date of Joining</th>
		<th>Remarks</th>
		<th>Previous Experience If Any ?</th>
		<th>Name of The University/Institute</th>
		<th>Subject For Which Registration</th>
		<th>Name of the Supervisor</th>
		<th>Period</th>
		<th>Topic of research</th>
		<th>Reasons for leaving</th>
		<th>Area(s) of interest for research</th>
		<th>Payment Mode</th>
		
		<th>Payment Date</th>
		<th>Amount </th>
		
		<th>Published</th>
		<!--<th colspan=30></th>
		<th colspan=3>Are You NET/UGC-JRF/CSIR-NET/CSIR-JRF/SET/SLET Qualified?</th>-->
		
            </tr>

        </thead>

        <tbody>
		<?php 
		$count = 1;
		if(!empty($applicant_data)){
			foreach($applicant_data as $data){
				$asmid = $data->asm_id;
		?>
				<tr>
					<td><?php echo $count++;?></td>
					<?php if(!empty($data->asm_applicationno)){?>
						<td><?php echo $data->asm_applicationno;?></td>
					<?php }else{?><td></td><?php }?>
		
					<?php if(!empty($asmid)){?>
						<td><?php echo $asmid; ?></td>
					<?php }else{?><td></td><?php }?>
					
					<td>University Exam</td>
					<?php $scid = $this->commodel->get_listspfic1('admissionstudent_master','asm_sccode','asm_id',$asmid)->asm_sccode;
					if(!empty($scid)){
					      $scname = $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$scid)->sc_name;
					}	
					?>
					<?php if(!empty($scname)){?>
						<td><?php echo $scname;?></td>
					<?php }else{?><td></td><?php }?>

					<?php $progbrid = $this->commodel->get_listspfic1('admissionstudent_master','asm_coursename','asm_id',$asmid)->asm_coursename;
					if(!empty($progbrid)){	
					      	$progname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$progbrid)->prg_name."( ".$this->commodel->get_listspfic1('program','prg_branch','prg_id',$progbrid)->prg_branch." )";

						$progcatname =  $this->commodel->get_listspfic1('program','prg_category','prg_id',$progbrid)->prg_category;
					}
					?>
					<?php if(!empty($progcatname)){?>
						<td><?php echo $progcatname;?></td>
					<?php }else{?><td></td><?php }?>

					<?php if(!empty($progname)){?>
						<td><?php echo $progname;?></td>
					<?php }else{?><td></td><?php }?>
					<?php $entexid = $this->commodel->get_listspfic1('admissionstudent_master','asm_enterenceexamcenter','asm_id',$asmid)-> 	asm_enterenceexamcenter;
					      $entexname = $this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_name','eec_id',$entexid)->eec_name;	
					?>
					<?php if(!empty($entexname)){?>
						<td><?php echo $entexname;?></td>
					<?php }else{?><td></td><?php }?>

					<?php if(!empty($data->asm_fname)){?>
						<td><?php echo $data->asm_fname;?></td>
					<?php }else{?><td></td><?php }?>

					<?php if(!empty($data->asm_email)){?>
						<td><?php echo $data->asm_email;?></td>
					<?php }else{?><td></td><?php }?>

					<?php if(!empty($data->asm_mobile)){?>
						<td><?php echo $data->asm_mobile;?></td>
					<?php }else{?><td></td><?php }?>

					<?php if(!empty($data->asm_gender)){?>
						<td><?php echo $data->asm_gender;?></td>
					<?php }else{?><td></td><?php }?>

					<?php if(!empty($data->asm_dob)){?>
						<td><?php echo $data->asm_dob;?></td>
					<?php }else{?><td></td><?php }?>

					<?php if(!empty($data->asm_age)){?>
						<td><?php echo $data->asm_age;?></td>
					<?php }else{?><td></td><?php }?>

					<?php if(!empty($data->asm_mstatus)){?>
						<td><?php echo $data->asm_mstatus;?></td>		
					<?php }else{?><td></td><?php }?>

					<?php if(!empty($data->asm_caste)){?>
						<td><?php echo $data->asm_caste;?></td>
					<?php }else{?><td></td><?php }?>

					<?php if(!empty($data->asm_phyhandicaped)){?>
						<td><?php echo $data->asm_phyhandicaped;?></td>
					<?php }else{?><td></td><?php }?>	
					<td></td>
					<td></td>
					<?php if(!empty($data->asm_religion)){?>
						<td><?php echo $data->asm_religion;?></td>
					<?php }else{?><td></td><?php }?>


					<?php if(!empty($data->asm_nationality)){?>
						<td><?php echo $data->asm_nationality;?></td>
					<?php }else{?><td></td><?php }?>

					<?php $pestate=$this->commodel->get_listspfic1('admissionstudent_parent','aspar_pstate','aspar_asmid',$asmid)->aspar_pstate;
					$coadd=$this->commodel->get_listspfic1('admissionstudent_parent','aspar_caddress','aspar_asmid',$asmid)->aspar_caddress;
					$cocity=$this->commodel->get_listspfic1('admissionstudent_parent','aspar_ccity','aspar_asmid',$asmid)->aspar_ccity;
					$costate=$this->commodel->get_listspfic1('admissionstudent_parent','aspar_cstate','aspar_asmid',$asmid)->aspar_cstate;				
					$cocountry=$this->commodel->get_listspfic1('admissionstudent_parent','aspar_ccountry','aspar_asmid',$asmid)->aspar_ccountry;

					$peadd=$this->commodel->get_listspfic1('admissionstudent_parent','aspar_paddress','aspar_asmid',$asmid)->aspar_paddress;
					$pecity=$this->commodel->get_listspfic1('admissionstudent_parent','aspar_pcity','aspar_asmid',$asmid)->aspar_pcity;
					$pestate=$this->commodel->get_listspfic1('admissionstudent_parent','aspar_pstate','aspar_asmid',$asmid)->aspar_pstate;				
					$pecountry=$this->commodel->get_listspfic1('admissionstudent_parent','aspar_pcountry','aspar_asmid',$asmid)->aspar_pcountry;
					?>
					<?php if(!empty($pestate)){?>
						<td><?php echo $pestate;?></td>
					<?php }else{?><td></td><?php }?>

					<?php if(!empty($cocity)){?>
						<td><?php echo $coadd.','.$cocity.','.$costate.','.$cocountry ;?></td>
					<?php }else{?><td></td><?php }?>
					
					<?php if(!empty($pecity)){?>
						<td><?php echo $peadd.','.$pecity.','.$pestate.','.$pecountry ;?></td>
					<?php }else{?><td></td><?php }?>

					<?php	 
					$stufname = $this->commodel->get_listspfic1('admissionstudent_parent','aspar_fathername','aspar_asmid',$asmid)->aspar_fathername;
					if(!empty($stufname)){?>
						<td><?php echo $stufname;?></td>
					<?php }else{?><td></td><?php }?>
		
					<?php	 
					$stumoname = $this->commodel->get_listspfic1('admissionstudent_parent','aspar_mothername','aspar_asmid',$asmid)->aspar_mothername;
					if(!empty($stumoname)){?>
						<td><?php echo $stumoname;?></td>
					<?php }else{?><td></td><?php }?>

					<?php	 
					$stufamono = $this->commodel->get_listspfic1('admissionstudent_parent','aspar_fatherphoneno','aspar_asmid',$asmid)->aspar_fatherphoneno;
					if(!empty($stufamono)){?>
						<td><?php echo $stufamono; ?></td>
					<?php }else{?><td></td><?php }?>	

					<?php	 
					$stumono = $this->commodel->get_listspfic1('admissionstudent_parent','aspar_motherphoneno','aspar_asmid',$asmid)->aspar_motherphoneno;
					if(!empty($stumono)){?>
						<td><?php echo $stumono?></td>
					<?php }else{?><td></td><?php }?>

					<?php	 
					$stufaoccu = $this->commodel->get_listspfic1('admissionstudent_parent','aspar_fatheroccupation','aspar_asmid',$asmid)->aspar_fatheroccupation;
					if(!empty($stufaoccu)){?>	
						<td><?php echo $stufaoccu;?></td>
					<?php }else{?><td></td><?php }?>

					<?php	 
					$stumooccu = $this->commodel->get_listspfic1('admissionstudent_parent','aspar_motheroccupation','aspar_asmid',$asmid)->aspar_motheroccupation;
					if(!empty($stumooccu)){?>
						<td><?php echo $stumooccu;?></td>
					<?php }else{?><td></td><?php }?>

					<td><?php echo $this->commodel->get_listspfic1('admissionstudent_parent','aspar_fatheremail','aspar_asmid',$asmid)->aspar_fatheremail;?></td>
					<td><?php echo $this->commodel->get_listspfic1('admissionstudent_parent','aspar_motheremail','aspar_asmid',$asmid)->aspar_motheremail;?></td>
				<?php
			$selectdata=array('asedu_institution','asedu_board','asedu_class','asedu_passingyear','asedu_marksobtained','asedu_maxmarks','asedu_percentage');
					$whdata = array('asedu_class' =>'10','asedu_asmid' => $asmid); 
					$datalist = $this->commodel->get_listspficemore('admissionstudent_education',$selectdata,$whdata);
					$anyedu=0;
					foreach($datalist as $highsc){
				?>					
				
					<td><?php echo $highsc->asedu_institution;?></td>
					<td><?php echo $highsc->asedu_board;?></td>
					<td><?php echo $highsc->asedu_passingyear;?></td>
					<td><?php echo $highsc->asedu_marksobtained;?></td>
					<td><?php echo $highsc->asedu_maxmarks;?></td>
					<td><?php echo $highsc->asedu_percentage;?></td>	
				<?php }$anyedu++;?>

				<?php
			$selectdata=array('asedu_institution','asedu_board','asedu_class','asedu_passingyear','asedu_marksobtained','asedu_maxmarks','asedu_percentage');
					$whdata = array('asedu_class' =>'12','asedu_asmid' => $asmid); 
					$datalist = $this->commodel->get_listspficemore('admissionstudent_education',$selectdata,$whdata);
					
					foreach($datalist as $highsc){
				?>					
					<td><?php echo $highsc->asedu_institution;?></td>
					<td><?php echo $highsc->asedu_board;?></td>
					<td><?php echo $highsc->asedu_passingyear;?></td>
					<td><?php echo $highsc->asedu_marksobtained;?></td>
					<td><?php echo $highsc->asedu_maxmarks;?></td>
					<td><?php echo $highsc->asedu_percentage;?></td>	
				<?php }$anyedu++;?>

				<?php
			$selectdata=array('asedu_institution','asedu_board','asedu_passingyear','asedu_marksobtained','asedu_maxmarks','asedu_percentage');
					$whdata = array('asedu_class' =>'Graduate','asedu_asmid' => $asmid); 
					$datalist = $this->commodel->get_listspficemore('admissionstudent_education',$selectdata,$whdata);
					
					if(!empty($datalist)){
					foreach($datalist as $highsc){
				?>					
					<td><?php echo $highsc->asedu_institution;?></td>
					<td><?php echo $highsc->asedu_board;?></td>
					<td><?php echo $highsc->asedu_passingyear;?></td>
					<td><?php echo $highsc->asedu_marksobtained;?></td>
					<td><?php echo $highsc->asedu_maxmarks;?></td>
					<td><?php echo $highsc->asedu_percentage;?></td>	
				<?php }$anyedu++;}else{?><td></td><td></td><td></td><td></td><td></td><td></td><?php }?>

			<?php
			$selectdata=array('asedu_institution','asedu_board','asedu_passingyear','asedu_marksobtained','asedu_maxmarks','asedu_percentage');
					$whdata = array('asedu_class' => 'Post Graduate','asedu_asmid' => $asmid); 
					$datalist = $this->commodel->get_listspficemore('admissionstudent_education',$selectdata,$whdata);
					
					if(!empty($datalist)){
					foreach($datalist as $highsc){
				?>					
					<td><?php echo $highsc->asedu_institution;?></td>
					<td><?php echo $highsc->asedu_board;?></td>
					<td><?php echo $highsc->asedu_passingyear;?></td>
					<td><?php echo $highsc->asedu_marksobtained;?></td>
					<td><?php echo $highsc->asedu_maxmarks;?></td>
					<td><?php echo $highsc->asedu_percentage;?></td>	
				<?php }$anyedu++;}else{?><td></td><td></td><td></td><td></td><td></td><td></td><?php }?>


			<?php
			$selectdata=array('asedu_institution','asedu_board','asedu_class','asedu_passingyear','asedu_marksobtained','asedu_maxmarks','asedu_percentage');
			//$whdata = array('asedu_class!='=>'10','asedu_class!='=>'12','asedu_class!='=>'Graduate','asedu_class!='=>'Post Graduate','asedu_asmid' => $asmid); 		
			$whdata=array('asedu_asmid' => $asmid);
			$datalist = $this->commodel->get_listspficemore('admissionstudent_education',$selectdata,$whdata);
			$i=1;$anyedu++;$flag=false;
					foreach($datalist as $highsc){
						if($anyedu == $i){
					?>
					<td><?php echo $highsc->asedu_class;?></td>					
					<td><?php echo $highsc->asedu_institution;?></td>
					<td><?php echo $highsc->asedu_board;?></td>
					<td><?php echo $highsc->asedu_passingyear;?></td>
					<td><?php echo $highsc->asedu_marksobtained;?></td>
					<td><?php echo $highsc->asedu_maxmarks;?></td>
					<td><?php echo $highsc->asedu_percentage;?></td>	
				<?php $flag = true;}   $i++;}
				if($flag == false){
				 ?><td></td><td></td><td></td><td></td><td></td><td></td><td></td><?php }
				?>

	<!-----------------------------------------Enterance exam detail start-------------------------------------------------->			
				<?php
					$selectexm=array('aseex_rollno','aseex_rank','aseex_score','aseex_state');
					$whedata = array('aseex_examname' =>'JEE main','aseex_asmid' => $asmid); 
					$jeedatalist = $this->commodel->get_listspficemore('admissionstudent_entrance_exam',$selectexm,$whedata);
					
					if(!empty($jeedatalist)){
					foreach($jeedatalist as $jee){
					
				?>					
					<td><?php echo $jee->aseex_rollno;?></td>
					<td><?php echo $jee->aseex_rank;?></td>
					<td><?php echo $jee->aseex_score;?></td>
					<td><?php echo $jee->aseex_state;?></td>
						
				<?php }}else{?><td></td><td></td><td></td><td></td><?php }?>

				<?php
					$selectc=array('aseex_rollno','aseex_rank','aseex_score','aseex_state');
					$whcdata = array('aseex_examname' =>'CMAT','aseex_asmid' => $asmid); 
					$cmatdatalist = $this->commodel->get_listspficemore('admissionstudent_entrance_exam',$selectc,$whcdata);
					
					if(!empty($cmatdatalist)){
					foreach($cmatdatalist as $cmat){
					
				?>					
					<td><?php echo $cmat->aseex_rollno;?></td>
					<td><?php echo $cmat->aseex_rank;?></td>
					<td><?php echo $cmat->aseex_score;?></td>
					<td><?php echo $cmat->aseex_state;?></td>
						
				<?php }}else{?><td></td><td></td><td></td><td></td><?php }?>

				<?php
					$selectgexm=array('aseex_rollno','aseex_rank','aseex_score','aseex_state');
					$whgdata = array('aseex_examname' =>'GATE','aseex_asmid' => $asmid); 
					$gatedatalist = $this->commodel->get_listspficemore('admissionstudent_entrance_exam',$selectgexm,$whgdata);
					
					if(!empty($gatedatalist)){
					foreach($gatedatalist as $gate){
					
				?>					
					<td><?php echo $gate->aseex_rollno;?></td>
					<td><?php echo $gate->aseex_rank;?></td>
					<td><?php echo $gate->aseex_score;?></td>
					<td><?php echo $gate->aseex_state;?></td>
						
				<?php }}else{?><td></td><td></td><td></td><td></td><?php }?>

				<?php
					$selectmexm=array('aseex_rollno','aseex_rank','aseex_score','aseex_state');
					$whmdata = array('aseex_examname' =>'M.Phill','aseex_asmid' => $asmid); 
					$mphilldatalist = $this->commodel->get_listspficemore('admissionstudent_entrance_exam',$selectmexm,$whmdata);
					
					if(!empty($mphilldatalist)){
					foreach($mphilldatalist as $mphill){
					
				?>					
					<td><?php echo $mphill->aseex_rollno;?></td>
					<td><?php echo $mphill->aseex_rank;?></td>
					<td><?php echo $mphill->aseex_score;?></td>
					<td><?php echo $mphill->aseex_state;?></td>
						
				<?php }}else{?><td></td><td></td><td></td><td></td><?php }?>
				<?php
					$selectnexm=array('aseex_rollno','aseex_rank','aseex_score','aseex_state');
					$whndata = array('aseex_examname' =>'NET','aseex_asmid' => $asmid); 
					$netdatalist = $this->commodel->get_listspficemore('admissionstudent_entrance_exam',$selectnexm,$whndata);
					
					if(!empty($netdatalist)){
					foreach($netdatalist as $net){
					
				?>					
					<td><?php echo $net->aseex_rollno;?></td>
					<td><?php echo $net->aseex_rank;?></td>
					<td><?php echo $net->aseex_score;?></td>
					<td><?php echo $net->aseex_state;?></td>
						
				<?php }}else{?><td></td><td></td><td></td><td></td><?php }?>

				<?php
					$selectsexm=array('aseex_rollno','aseex_rank','aseex_score','aseex_state');
					$whsdata = array('aseex_examname' =>'SLET','aseex_asmid' => $asmid); 
					$sletdatalist = $this->commodel->get_listspficemore('admissionstudent_entrance_exam',$selectsexm,$whsdata);
					
					if(!empty($sletdatalist)){
					foreach($sletdatalist as $slet){
					
				?>					
					<td><?php echo $slet->aseex_rollno;?></td>
					<td><?php echo $slet->aseex_rank;?></td>
					<td><?php echo $slet->aseex_score;?></td>
					<td><?php echo $slet->aseex_state;?></td>
						
				<?php }}else{?><td></td><td></td><td></td><td></td><?php }?>

				<?php   $select=array('asemp_officename','asemp_post','asemp_pay','asemp_grade','asemp_appoinmentnature','asemp_doj','asemp_remarks','asemp_experience','asemp_dol');
					$wdata = array('asemp_asmid' => $asmid); 
					$empdatalist = $this->commodel->get_listspficemore('admissionstudent_employment',$select,$wdata);
					if(!empty($empdatalist)){
					foreach($empdatalist as $row){
				?>
						<td><?php echo $row->asemp_post;?></td>
						<td><?php echo $row->asemp_pay.'( '.$row->asemp_grade.' )';?></td>
						<td><?php echo $row->asemp_appoinmentnature;?></td>
						<td><?php echo $row->asemp_doj;?></td>
						<td><?php echo $row->asemp_remarks;?></td>
						<td><?php echo $row->asemp_experience;?></td>
						<td><?php echo $row->asemp_officename;?></td>
						<td><?php ?></td>
						<td><?php ?></td>
						<td><?php ?></td>
						<td><?php ?></td>
						<td><?php ?></td>
						<td><?php ?></td>
				<?php }}else{?><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><?php }?>

				<?php   $selectfee=array('asfee_paymentmethod','asfee_installment_date','asfee_feeamount');
					$wfeedata = array('asfee_amid' => $asmid); 
					$feedatalist = $this->commodel->get_listspficemore('admissionstudent_fees',$selectfee,$wfeedata);
					if(!empty($feedatalist)){
					foreach($feedatalist as $row){
				if(!empty($row->asfee_paymentmethod)){?>
					<td><?php echo $row->asfee_paymentmethod;?></td>
				<?php }else{?><td></td><?php }?>
				<?php	
					$paydate =$this->commodel->get_listspfic1('admissionstudent_enterencestep','step4_date','admission_masterid',$asmid)->step4_date;
					if(!empty($paydate)){?>				
						<td><?php echo $paydate;?></td>
				<?php }else{?><td></td><?php }?>

				<?php if(!empty($row->asfee_feeamount)){?>
					<td><?php echo $row->asfee_feeamount;?></td>
				<?php }else{?><td></td><?php }?>
	
				<?php }}else{?><td></td><td></td><td></td><td></td><?php }?>
				
		</tr>
	<?php } 
			}?>
	</tbody>

    </table>

<?php	
	function filterData(&$str)
	{
		$str = preg_replace("/\t/", "\\t", $str);
		$str = preg_replace("/\r?\n/", "\\n", $str);
		if(strstr($str, '"')) $str = '"' . str_replace('"', '""', $str) . '"';
	}
	
	// file name for download
	$fileName = "Entrance Applicant Details" . date('Y-m-d') . ".xls";
	
	// headers for download
	header("Content-Disposition: attachment; filename=\"$fileName\"");
	header("Content-Type: application/vnd.excel");
	
	$flag = false;
	//foreach($result as $row) {
		if(!$flag) {
			// display column names as first row
			//echo implode("\t", array_keys($row)) . "\n";
			$flag = true;
		}
		// filter data
		//array_walk($row, 'filterData');
		//echo implode("\t", array_values($row)) . "\n";
	//}
	
	exit;
?>
