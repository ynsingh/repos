<!--
    @author Manorama Pal(palseema30@gmail.com) 
    @author Akash Rathi(akash92y@gmail.com) html part  

-->
<html>
    <head>
       
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
</head>
<body>
    <?php $this->load->view('template/pheader'); ?>
<!--    <img src="uploads/logo/logotanuvas.jpeg" alt="logo" style= " width:100%;height:80px; margin-bottom:15px; " > <br/>-->

<div> 
        <table class="TFtable" style="border-width:2px;border-color:black;border-style:solid;">
            <thead>
                <tr>
                    <th style="border-width:2px;font-size:8;">Sr.No.</th>
                    <th style="border-width:2px;font-size:8;"><?php echo wordwrap('Employee Name',8,"\n",TRUE)?></th>
			<th style="border-width:2px;font-size:8;word-break:break-all; word-wrap:break-word;">Basic </th><th style="border-width:2px;font-size:8;">Academic </th>
			<th style="border-width:2px;font-size:8;word-break:break-all; word-wrap:break-word;">Technical </th><th style="border-width:2px;font-size:8;word-break:break-all; word-wrap:break-word;"><?php echo wordwrap('Promot-ional',8,"\n",TRUE);?> </th>
			<th style="border-width:2px;font-size:8;word-break:break-all; word-wrap:break-word;">Service </th><th style="border-width:2px;font-size:8;"><?php echo wordwrap('Addional Assignment',8,"\n",TRUE);?> </th>
			<th style="border-width:2px;font-size:8;word-break:break-all; word-wrap:break-word;"><?php echo nl2br('Performance');?> </th><th style="border-width:2px;font-size:8;word-break:break-all; word-wrap:break-word;">Leave</th>
			<th style="border-width:2px;font-size:8;word-break:break-all; word-wrap:break-word;">Deputation</th><th style="border-width:2px;font-size:8;"><?php echo wordwrap('Departmental Exam',8,"\n",TRUE);?></th>
			<th style="border-width:2px;font-size:8;"><?php echo wordwrap('Working Arrangement',8,"\n",TRUE);?> </th><th style="border-width:2px;font-size:8;"><?php echo nl2br('Recruitment');?> </th>
			<th style="border-width:2px;font-size:8;"><?php echo wordwrap('Disciplinary Actions',8,"\n",TRUE);?></th>
                </tr>
            </thead>
           <?php // print_r($records); //echo $wtyp;echo $uoff; echo $dept;?>
           <tbody>
                <?php $serial_no = 1;
		$ouoid = 0;
		$odid = 0;
		
               if( !empty($records) ):  ?>
                    <?php foreach($records as $record){ 

			if($ouoid !=$record->emp_uocid){
			echo "<tr>";
			echo "<td colspan=15 style=\"text-align:center;border-width:2px;\">";
			echo " <b> UO CONTROL : ";
			echo $this->lgnmodel->get_listspfic1('authorities','name','id' ,$record->emp_uocid)->name;
			echo "</b></td>";
			echo "</tr>";
			$ouoid=$record->emp_uocid;
			}
			if($odid !=$record->emp_dept_code){
                        echo "<tr><td colspan=15 align=left style=\"border-width:2px;\"><b> Department : ";
			echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$record->emp_dept_code)->dept_name;
			echo " ( ". $this->commodel->get_listspfic1('Department','dept_code','dept_id',$record->emp_dept_code)->dept_code ." )";
                        echo "</b></td></tr>";
			$odid = $record->emp_dept_code;
			$serial_no = 1;
                        }
			echo "<tr style=\"border-width:2px;\">";
			echo "<td style=\"border-width:2px;\">". $serial_no++ ." </td>";
			echo "<td style=\"border-width:2px;\">";
			echo $record->emp_name." ( "."PF No:".$record->emp_code." )" ;
			echo "</td>";

			echo "<td style=\"border-width:2px;\">"; 
			echo "Yes";
			echo "</td>";

			echo "<td style=\"border-width:2px;\">";
				if($this->sismodel->isduplicate("staff_academic_qualification",'saq_empid',$record->emp_id))
					echo "Yes";
			echo "</td>";

			echo "<td style=\"border-width:2px;\">";
				if($this->sismodel->isduplicate("staff_technical_qualification",'stq_empid',$record->emp_id))
					echo "Yes";
			echo "</td>";

			echo "<td style=\"border-width:2px;\">";
				if($this->sismodel->isduplicate("staff_promotionals_details",'spd_empid',$record->emp_id))
					echo "Yes";
			echo "</td>";

			echo "<td style=\"border-width:2px;\">";
				if($this->sismodel->isduplicate("employee_servicedetail",'empsd_empid',$record->emp_id))
					echo "Yes";
			echo "</td>";

			echo "<td style=\"border-width:2px;\">";
				if($this->sismodel->isduplicate("additional_assignments",'aa_empid',$record->emp_id))
					echo "Yes";
			echo "</td>";

			echo "<td style=\"border-width:2px;\">";
				if($this->sismodel->isduplicate("Staff_Performance_Data",'spd_empid',$record->emp_id))
					echo "Yes";
			echo "</td>";

			echo "<td style=\"border-width:2px;\">";
				if($this->sismodel->isduplicate("leave_apply",'la_userid',$record->emp_id))
					echo "Yes";
			echo "</td>";

			echo "<td style=\"border-width:2px;\">";
				if($this->sismodel->isduplicate("staff_deputation_perticulars",'sdp_empcode',$record->emp_code))
					echo "Yes";
			echo "</td>";

			echo "<td style=\"border-width:2px;\">";
				if($this->sismodel->isduplicate("staff_department_exam_perticulars",'sdep_empcode',$record->emp_code))
					echo "Yes";
			echo "</td>";

			echo "<td style=\"border-width:2px;\">";
				if($this->sismodel->isduplicate("staff_working_arrangements_perticulars",'swap_empcode',$record->emp_code))
					echo "Yes";
			echo "</td>";

			echo "<td style=\"border-width:2px;\">";
				if($this->sismodel->isduplicate("staff_recruitment_perticulars",'srp_empcode',$record->emp_code))
					echo "Yes";
			echo "</td>";

			echo "<td style=\"border-width:2px;\">";
				if($this->sismodel->isduplicate("staff_disciplinary_actions_perticulars",'sdap_empcode',$record->emp_code))
					echo "Yes";
			echo "</td>";

?>
                        </tr>
                    <?php }; ?>
                <?php else : ?>
                    <td colspan= "15" align="center"> No Records found...!</td>
                <?php endif;?>
                </tbody> 
        </table> 
        </div> <!------scroller div------>
<!-- <img src="uploads/logo/logo23.png" alt="logo" style= " width:100%;height:30px; margin-top:30px; " > <br/>-->
<?php //$this->load->view('template/footer'); ?>
</body>
</html>
