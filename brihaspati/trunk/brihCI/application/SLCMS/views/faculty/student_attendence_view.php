<!--@name student_attendence_view.php  @author sumit saxena(sumitsesaxena@gmail.com)
    @name student_attendence_view.php  @author neha khullar(nehukhullar@gmail.com) -->



<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
    <head>  
	<title>Welcome to IGNTU :Attendance</title>  
       
          <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <style>
		select{width:70%;}
            thead th{
               
                background-color: #DCDCDC;
              }
		.tag_color{
			color:red;
		}
		
       </style>

	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/bootstrap.min.css">
  	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
	
	<script>
	function getsemester(sem){
	        var sem = sem;
		//alert(sem);
                $.ajax({
                type: "POST",
		url: "<?php echo base_url();?>slcmsindex.php/facultyhome/semester_get",
                data: {"program_branch" : sem},
                dataType:"html",
                success: function(data){
                	$('#semester').html(data.replace(/^"|"$/g, ''));
		}
             });
        }

	function getsubject(combid){
		var prgid = $('#program_branch').val();
                var semid = $('#semester').val();
                var combid = prgid+","+semid;
		//alert(combid);
                $.ajax({
                type: "POST",
		url: "<?php echo base_url();?>slcmsindex.php/facultyhome/subject_get",
                data: {"semprg" : combid},
                dataType:"html",
                success: function(data){
                	$('#subjectname').html(data.replace(/^"|"$/g, ''));
		}
             });
        }

	function getpaper(prgsemsubid){
	        var prgid = $('#program_branch').val();
                var semid = $('#semester').val();
		var subid = $('#subjectname').val();
                var prgsemsubid = prgid+","+semid+","+subid;
		//alert(prgsemsubid);
                $.ajax({
                type: "POST",
		url: "<?php echo base_url();?>slcmsindex.php/facultyhome/paper_get",
                data: {"prg_sem_sub" : prgsemsubid},
                dataType:"html",
                success: function(data){
                	$('#papername').html(data.replace(/^"|"$/g, ''));
		}
             });
        }

	</script>
    </head>    
    <body>	
		 <?php $this->load->view('template/header'); ?>
          	<!--<h2>Welcome <?//= $this->session->userdata('username') ?>  </h2>-->
       		 <?php //$this->load->view('template/facultymenu');?>
<table width="100%">
            <tr>
		<?php
		    echo "<td align=\"left\" width=\"33%\">";
                    $help_uri = site_url()."/facultyhome/student_attendence";
                    echo "<a style=\"text-decoration:none\" href=$help_uri><b>Add Student Attendance</b></a>";
                    echo "</td>";
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Student Attendance List</b>";
                    echo "</td>";
                    echo "<td align=\"right\" width=\"33%\">";
                    //$help_uri = site_url()."/help/helpdocfaculty#StudentList";
                    //echo "<a href=$help_uri><b style=\"float:right;margin-top:1.6%\">Click for Help</b></a>";
                    echo "</td>";
                 ?>
</tr></table>
         <table width="100%"> 
          <tr><td> 
               <div>
               <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                <?php if(isset($_SESSION['success'])){?>
                   <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                <?php
                };
                ?>
                <?php if(isset($_SESSION['err_message'])){?>
                   <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                <?php
                };
                ?>        
        	</div>
        </td>
	</tr>  
        </table>
      <!--  <a href="<?php echo site_url(); ?>/studentrecord/mypdf">pdf</a>-->
      <!-- <div class="panel panel-primary"> -->

		<form action="<?php echo site_url('facultyhome/student_attendence_view');?>" method="POST">
        <table style="width:100%;" >
	
            <tr style="font-weight:bold; background-color:lightslategray;">

                <td> <span style="color:white;">Select Program :</span>
                    <select name="program_branch" id="program_branch" onchange="getsemester(this.value);">
			<option selected="true" disabled>Select Program</option>
			 <?php foreach($prgsublist as $prgdata): ?>	
                            <option value="<?php echo $prgdata->pstp_prgid; ?>">
				<?php echo $this->cmodel->get_listspfic1('program','prg_name','prg_id',$prgdata->pstp_prgid)->prg_name."&nbsp;"."(".
                                $this->cmodel->get_listspfic1('program','prg_branch','prg_id',$prgdata->pstp_prgid)->prg_branch.")" ;?></option> 
 			<?php endforeach; ?>
		
		    </select>
                </td>
                <td><span style="color:white;">Semester :</span>
			<select name="semester" id="semester" onchange="getsubject(this.value);" >
				<option selected="true" disabled>semester</option>
				<option value='1'>1</option>
				<option value='2'>2</option>
				<option value='3'>3</option>
				<option value='4'>4</option>	
				<option value='5'>5</option>
				<option value='6'>6</option>
				<option value='7'>7</option>
				<option value='8'>8</option>	
		   	</select>		
                </td>
                <td><span style="color:white;">Subject Name :</span>
                    <select name="subjectname" id="subjectname" onchange="getpaper(this.value);" > 
                        <option selected="selected" disabled selected>subject name</option>
		<?php 	
		//echo "<option selected='true' disabled>"."Select Subject"."</option>";	
		foreach($subject as $data){
			$subname = $this->cmodel->get_listspfic1('subject','sub_name','sub_id',$data->pstp_subid)->sub_name;
			$whdata = array('sub_id'  =>  $data->pstp_subid,'sub_program' => $data->pstp_prgid , 'sub_semester' => $data->pstp_sem);
			$sdata = array('sub_name','sub_id');
			$subname = $this->cmodel->get_listspficemore('subject',$sdata,$whdata);
		
		foreach($subname as $datas):   
			 echo "<option id='subjectname' value='$datas->sub_id'>"."$datas->sub_name"."</option>";
   		endforeach;
		
		}
		?>
	   </select>
                </td>
		<td><span style="color:white;">Subject Paper Name :</span>
                    <select name="papername" id="papername" onchange=""> 
                        <option selected="selected" disabled selected>subject paper name</option>
			<?php
			foreach($subject as $row){
				$selectfield=array('subp_name','subp_id');
				$data=array(
            				'subp_sub_id'   => $row->pstp_subid,
					'subp_id'	=> $row->pstp_papid,
					'subp_degree'	=> $row->pstp_prgid,
					'subp_acadyear' => $row->pstp_acadyear,
					'subp_sem'	=> $row->pstp_sem,
				
	        		);
				$papername = $this->cmodel->get_distinctrecord('subject_paper',$selectfield,$data);
		foreach($papername as $row):   
			 echo "<option id='papername' value='$row->subp_id'>"."$row->subp_name"."</option>";
   		endforeach;
		}
			?>
                    </select>
                </td>
		

		<td valign=bottom><input type="submit" name="search" value="Search" style="font-size:18px;" /></td>
            </tr>
	<tr>
		 <td colspan=12><span style="color:black;float:right;font-weight:bold;">Academic Year:&nbsp;&nbsp <?php echo $academicyear;?></span></td>
	</tr>
	

        </table><br/>

	
	</br>
        <table style="width:100%" class="TFtable" id='getrecord'>
            <thead>
                <tr style="text-align: center;">
                     <!--<th>Sr.No</th>
                    <th>Student Name</th>
                    <th>Enrollment No</th>
                    <th>Roll No</th>-->

		<th>Sr. No.</th>
                <th>Student Name</th>
		<th>Enrollment No</th>
                <th>Roll No</th>
                <th>Study Center</th>
                <th>Department</th>
                <th>Academic Year</th>
                <th>Program</th>
                <th>Semester</th>
                <th>Subject</th> 
                <th>Paper</th>
		<th>Attendance</th>
		<th>Class Type</th>
		<th>Attendance Date</th>
		    <!---<th style="width:20%;">Attendance </br>P(present) A(absent) L(leave)</th>-->
				
                </tr>
            </thead>
             <tbody align=center>
		               
		<?php $count=1;
		 if(!empty($getdata)){
		 foreach($getdata as $getname):
			$studname = $this->cmodel->get_listspfic1('student_master','sm_fname','sm_id',$getname->satd_smid)->sm_fname;
			$studenrollno = $this->cmodel->get_listspfic1('student_master','sm_enrollmentno','sm_id',$getname->satd_smid)->sm_enrollmentno;
			$studrollno = $this->cmodel->get_listspfic1('student_entry_exit','senex_rollno','senex_smid',$getname->satd_smid)->senex_rollno;
		?>
		<tr>
            	<td><?php echo $count++;?></td>
		<td><?php echo $studname; ?></td>
		<td><?php echo $studenrollno;?></td>
		<td><?php echo $studrollno;?></td>
		<?php

		//$scid = $this->cmodel->get_listspfic1('student_program','sp_sccode','sp_smid',$getname->satd_smid)->sp_sccode;
		$scid = $getname->satd_scid;
		if($scid > 0){
			$scname = $this->cmodel->get_listspfic1('study_center','sc_name','sc_id',$scid)->sc_name;
			if(!empty($scname)){
                    		 echo "<td>".$scname."</td>";
             		 }
               		 else{
               			 echo "<td></td>";
                	 }

		}
		else{ 
		echo "<td></td>";
		 }
		//$deptid = $this->cmodel->get_listspfic1('student_attendance','satd_deptid','satd_smid',$getname->satd_smid)->satd_deptid;
		$deptid = $getname->satd_deptid;
		echo "<td>".$deptname = $this->cmodel->get_listspfic1('Department','dept_name','dept_id',$deptid)->dept_name."</td>";

		//echo "<td>".$this->cmodel->get_listspfic1('student_attendance','satd_acadyear','satd_smid',$getname->satd_smid)->satd_acadyear."</td>";
		echo "<td>".$getname->satd_acadyear."</td>";
		//$progid = $this->cmodel->get_listspfic1('student_attendance','satd_prgid','satd_smid',$getname->satd_smid)->satd_prgid;
		$progid =  $getname->satd_prgid;
		echo "<td>".$prgname = $this->cmodel->get_listspfic1('program','prg_name','prg_id',$progid)->prg_name."(".$branchname = $this->cmodel->get_listspfic1('program','prg_branch','prg_id',$progid)->prg_branch.")"."</td>";

		//echo "<td>".$this->cmodel->get_listspfic1('student_attendance','satd_sem','satd_smid',$getname->satd_smid)->satd_sem."</td>";
		echo "<td>".$getname->satd_sem."</td>";
		//$sujectid = $this->cmodel->get_listspfic1('student_attendance','satd_subid','satd_smid',$getname->satd_smid)->satd_subid;
		$sujectid = $getname->satd_subid;
		echo "<td>".$subname = $this->cmodel->get_listspfic1('subject','sub_name','sub_id',$sujectid)->sub_name."</td>";

		//$paperid = $this->cmodel->get_listspfic1('student_attendance','satd_papid','satd_smid',$getname->satd_smid)->satd_papid;
		$paperid = $getname->satd_papid;
		if($paperid > 0){
			$papname = $this->cmodel->get_listspfic1('subject_paper','subp_name','subp_id',$paperid)->subp_name;
			if(!empty($papname)){
                    		 echo "<td>".$papname."</td>";
             		 }
               		 else{
               			 echo "<td></td>";
                	 }

		}
		else{ 
		echo "<td></td>";
		 }
		
		//echo "<td style='text-align:center;'>".$this->cmodel->get_listspfic1('student_attendance','satd_astatus','satd_smid',$getname->satd_smid)->satd_astatus."</td>";
		echo "<td style='text-align:center;'>".$getname->satd_astatus."</td>";
		//echo "<td style='text-align:center;'>".$this->cmodel->get_listspfic1('student_attendance','satd_classtype','satd_smid',$getname->satd_smid)->satd_classtype."</td>";
		echo "<td style='text-align:center;'>".$getname->satd_classtype."</td>";

		//echo "<td style='text-align:center;'>".$this->cmodel->get_listspfic1('student_attendance','satd_adate','satd_smid',$getname->satd_smid)->satd_adate."</td>";
		
		echo "<td style='text-align:center;'>".$getname->satd_adate."</td>";
		?>
		<!--<td> 
	<!--<form action="<?php echo site_url('Studenthome/student_attendence');?>" method="POST">---
			<select name="attendence<?php //echo $i;?>" class="second_box">
				<option value='P'>P</option>
				<option value='A'>A</option>
				<option value='L'>L</option>
			<select>
		</tr>--->
		
	<?php //$i++; 
		endforeach; }  

		else{  	
		?>
		
		 <tr><td colspan= "14" align="center"><center> <b>No Records found...!</b></center></td></tr>
		<?php }  ?>
		
		
		
            </tbody>  
        </table> 
	
	 </form> 


           <!--- <table class="TFtable">
            <thead>
            <tr>
                <th>Sr. No.</th>
                <th>Student Name</th>
                <th>Study Center</th>
                <th>Department</th>
                <th>Academic Year</th>
                <th>Program</th>
                <th>Semester</th>
                <th>Subject</th> 
                <th>Paper</th>
		<th>Attendance</th>
		<th>Class Type</th>
		<th>Attendance Date</th>
            </tr>
	</thead>
	<tbody>
	<?php $count=1;
	if( count($studatt) ):	
	    foreach($studatt as $row){
		//print_r($row);
            	echo "<tr>";
		echo "<td>".$count++ ."</td>";
                echo "<td>".$this->stuname = $this->cmodel->get_listspfic1('student_master','sm_fname','sm_id',$row->satd_smid)->sm_fname."</td>";
		$scid = $row->satd_scid;
		if($scid > 0){
			$scname = $this->cmodel->get_listspfic1('study_center','sc_name','sc_id',$scid)->sc_name;
			if(!empty($scname)){
                    		 echo "<td>".$scname."</td>";
             		 }
               		 else{
               			 echo "<td></td>";
                	 }

		}
		else{ 
		echo "<td></td>";
		 }
		echo "<td>".$deptname = $this->cmodel->get_listspfic1('Department','dept_name','dept_id',$row->satd_deptid)->dept_name."</td>";
		echo "<td>".$row->satd_acadyear."</td>";
		echo "<td>".$prgname = $this->cmodel->get_listspfic1('program','prg_name','prg_id',$row->satd_prgid)->prg_name."(".$branchname = $this->cmodel->get_listspfic1('program','prg_branch','prg_id',$row->satd_prgid)->prg_branch.")"."</td>";
		echo "<td>".$row->satd_sem."</td>";
		echo "<td>".$subname = $this->cmodel->get_listspfic1('subject','sub_name','sub_id',$row->satd_subid)->sub_name."</td>";
		//echo "<td>".$papname = $this->cmodel->get_listspfic1('subject_paper','subp_name','subp_id',$row->satd_papid)->subp_name."</td>";
		$paperid = $row->satd_papid;
		if($paperid > 0){
			$papname = $this->cmodel->get_listspfic1('subject_paper','subp_name','subp_id',$paperid)->subp_name;
			if(!empty($papname)){
                    		 echo "<td>".$papname."</td>";
             		 }
               		 else{
               			 echo "<td></td>";
                	 }

		}
		else{ 
		echo "<td></td>";
		 }
		echo "<td style='text-align:center;'>".$row->satd_astatus."</td>";
		echo "<td>".$row->satd_classtype."</td>";
		echo "<td>".$row->satd_adate."</td>";
	echo "</tr>";
		}
	?>
		<?php else : ?>
                <tr><td colspan= "12" align="center"> <b>No Records found...!</b></td></tr>
                <?php endif;?>
	 </tbody>
        </table> --->
  <!-- </div>  -->     
    </body>  
   <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

