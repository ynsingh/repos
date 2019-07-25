

      <table class="TFtable" style="width:100%;">
	<thead>
		<tr>
			<th>Sr. No.</th>
			<!--<th>Hall Ticket Number</th>-->
			<th>Application Number</th>
			<th>Student Name</th>
			<th>Father Name</th>
			<th>Mother Name</th>
			<th>Gender</th>
			<th>Category</th>
			<th>Admission Taken Category</th>
			<th>Religion</th>
			<th>Address</th>
			<th>Program Category</th>
			<th>Program( Branch ) Name</th>
		</tr>
	</thead>
	
	<tbody>
		<?php
		$count = 1;
		//if//(!empty($student_data)){	
			//foreach($student_data as $row){
			
			/*$asmid = $row->senex_smid;*/
			//print_r($asmid);
			foreach($asmid as $row){
			$adsmid = $row;
			//print_r($row);die;
			$pestate=$this->commodel->get_listspfic1('student_parent','spar_pstate','spar_smid',$adsmid)->spar_pstate;
			$peadd=$this->commodel->get_listspfic1('student_parent','spar_paddress','spar_smid',$adsmid)->spar_paddress;
			$pecity=$this->commodel->get_listspfic1('student_parent','spar_pcity','spar_smid',$adsmid)->spar_pcity;
			$pestate=$this->commodel->get_listspfic1('student_parent','spar_pstate','spar_smid',$adsmid)->spar_pstate;				
			$pecountry=$this->commodel->get_listspfic1('student_parent','spar_pcountry','spar_smid',$adsmid)->spar_pcountry;
			$address = $peadd.','.$pecity.','.$pestate.','.$pecountry ;

			$catid = $this->commodel->get_listspfic1('student_master','sm_category','sm_id',$adsmid)->sm_category;

			$prgcatid = $this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$adsmid)->sp_programid;
			$prgcatname = $this->commodel->get_listspfic1('program','prg_category','prg_id',$prgcatid)->prg_category;
	
			$prgname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgcatid)->prg_name;
			$prgbranch = $this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgcatid)->prg_branch;
			$prgbranch = $prgname.'( '.$prgbranch.' )';
		?>
			<tr>
				<td><?php echo $count++;?></td>
				<td><?php echo $this->commodel->get_listspfic1('student_admissionstep','application_no','student_masterid',$adsmid)->application_no;?></td>
				<td><?php echo $this->commodel->get_listspfic1('student_master','sm_fname','sm_id',$adsmid)->sm_fname;?></td>
				<td><?php echo $this->commodel->get_listspfic1('student_parent','spar_fathername','spar_smid',$adsmid)->spar_fathername;?></td>
				<td><?php echo $this->commodel->get_listspfic1('student_parent','spar_mothername','spar_smid',$adsmid)->spar_mothername;?></td>
				<td><?php echo $this->commodel->get_listspfic1('student_master','sm_gender','sm_id',$adsmid)->sm_gender;?></td>

				<td><?php echo $this->commodel->get_listspfic1('category','cat_name','cat_id',$catid)->cat_name;?></td>
				<td><?php echo $this->commodel->get_listspfic1('category','cat_name','cat_id',$catid)->cat_name;?></td>

				<td><?php echo $this->commodel->get_listspfic1('student_master','sm_religion','sm_id',$adsmid)->sm_religion;?></td>
				<td><?php echo $address; ?></td>
				<!--<td><?php echo $prgcatname; ?></td>-->
				<td><?php echo $this->commodel->get_listspfic1('programcategory','prgcat_name','prgcat_id',$progcatid)->prgcat_name; ?></td>
				<td><?php echo $prgbranch?></td>
				
			</tr>		
			
	<?php }	//}?>
			
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
	$fileName = "Student Admission Applicant Details" . date('Y-m-d') . ".xls";
	
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
