<!--@filename stu_semmarksdw.pdf  @author sumit saxena(sumitsesaxena@gmail.com) -->

<html>
    <head>
        <title>Welcome </title>

	
<style>
      #watermark { position: fixed; bottom: 0px; top: 10%;left:25%;right: 0px; width: 300px; height: 300px; opacity: .3; }
    </style>
    </head>

    <body>
	<!--<div id="watermark"><img src="assets/images/logogray.jpg" height="100%" width="100%"></div>-->

	
		<img src='<?php echo base_url("uploads/logo/logo2.jpg");?>'  style="width:100%;height:70px;">
<!--	<img src='<?php //echo base_url("uploads/logo/niitsikkim.png");?>'  style="width:100%;height:90px;"> -->
	<table style="width:100%;">
<tr><th>
	<center>Student Marks</center>
</th></tr></table>
    <!--     <div style="">-->

	<table border=1 style="width:100%;">
	   <tr> <?php //echo $this->sfeeid;?>
               <th style="text-align:justify;background-color:#dbdbdb;color:black;font-size:18px;">Student Marks details</th>
            </tr>
	</table>
	<?php 
		$i= 0;
	?>
	<table style="width:100%;">
	<tr><td>	
        <table style="width:100%;">
	<tr>
	<?php 
		$i= 0;

		if(!empty($stumarks)){
		foreach($stumarks as $row){
			$smid = $row->smr_smid;
			$deptid = $row->smr_deptid;
			$prgid = $row->smr_prgid;
			$examid = $row->smr_extyid;
			$subid = $row->smr_subid;
			$paperid = $row->smr_papid;	
	?>
	<td>
            <tr>
            <td style="width:33%;">Name: <?php echo " ".$this->commodel->get_listspfic1('student_master','sm_fname','sm_id',$smid)->sm_fname; ?></td>
            <td style="width:33%;">Roll No: <?php echo " ".$this->commodel->get_listspfic1('student_entry_exit','senex_rollno','senex_smid',$prgid)->senex_rollno; ?> </td>
           <td style="width:33%;">Academic Year: <?php echo " ".$row->smr_acadyear;?></td>
           </tr>
	    <tr>
           <td>Program:<?php echo " ".$this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name;?> </td>
           <td>Branch & Semester:<?php  echo ' '.'( '. $this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch.' )'." & ". $row->smr_sem;?></td>
           <td>Department: <?php echo " ".$this->commodel->get_listspfic1('Department','dept_name','dept_id',$deptid)->dept_name ;?> </td>
	  </tr>
	   <tr>
            <td style="width:33%;">Subject: <?php echo " ".$this->commodel->get_listspfic1('subject','sub_name','sub_id',$subid)->sub_name;?></td>
            <td style="width:33%;">Paper: <?php echo " ".$this->commodel->get_listspfic1('subject_paper','subp_name','subp_id',$paperid)->subp_name;?></td>
           <td style="width:33%;">Exam Name: <?php echo " ".$this->commodel->get_listspfic1('examtype','exty_name','exty_id',$examid)->exty_name;?></td>
           </tr>
	   <tr>
            <td style="width:33%;">Max. Marks: <?php echo " ".$row->smr_mmmarks;?></td>
            <td style="width:33%;">Marks: <?php echo " ".$row->smr_marks;?> </td>
          
           </tr>	
	
	</table>
	</td>
          </tr> 
	<?php $i++;
	if($i%1 == 0){
?>
	
	<tr><td><p style="page-break-before: always;"><img src='<?php echo base_url("uploads/logo/logo2.jpg");?>'  style="width:100%;height:70px;"></p></td></tr>
<tr>
	
<?php  }}} ?>
</tr>	
	</table>
	
          


</body>  
</html>    
