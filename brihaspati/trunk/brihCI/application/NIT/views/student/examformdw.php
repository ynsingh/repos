<!--@filename examformdw.php  
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
	<center>Exam Form</center>
</th></tr></table>
        <table style="width:100%;" class="TFtable">
			<thead  style="background-color:#38B0DE;color:white;font-size:18px;"><tr><td colspan="6"><b>Exam Form Detail</b></td></tr></thead>
			<?php foreach ($this->pdetail as $row) {?>
			<tr>
				<td style="">Name of exam :</td>
				<td>Final Exam<?php //echo $row->sm_fname;?></td>
				<td style="">Name of the course :</td>
			        <td><?php echo $this->pname;?></td>
			</tr>
			
			<tr>
				<td>Semester :</td>
				<td><?php echo $noofsemester;?></td>
				<td>Student name :</td>
				<td><?php echo $row->sm_fname;?></td>
			</tr>
			<tr>
				<td>Mother name :</td>
				<td><?php echo $this->mname;?></td>
				<td>Father name :</td>
				<td><?php echo $this->fathname;?></td>
			</tr>

			<tr>
				<td >Gender :</td>
				<td><?php echo $row->sm_gender;?></td>
				<td>Date of birth :</td>
				<td><?php echo $row->sm_dob;?></td>
				
			</tr>

			<tr>
				<td>Category :</td>
				<td ><?php echo $this->catename;?></td>
				<td>Mobile/Phone number :</td>
				<td><?php echo $row->sm_mobile;?></td>

			</tr>
			<tr>
				<td>E-mail :</td>
				<td><?php echo $row->sm_email;?></td>
				<td >Aadhar number :</td>
				<td><?php echo $row->sm_uid;?></td>
			</tr>

			<?php }?>
		</table>

		<table style="width:100%;margin-top:30px;" class="TFtable">	
			<thead style="background-color:#38B0DE;color:white;font-size:18px;"><tr><th style="text-align:justify;font-weight:bold;" colspan=7>
			Academic Detail
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
<?php
 $compsubject = array();
    $elecsubject = array();
    for($i=0; $i<sizeof($subjectsem); $i++)
    {
        $subdata = $subjectsem[$i];
        $sub_data=explode('#',$subdata);
        $subid = $sub_data[0];
        $subtype = $sub_data[1];
        if($subtype == "Compulsory")
            $compsubject[] = $subid;
        else
            $elecsubject[] = $subid;
            
    }
?>
		<table class="TFtable" id="" style="width:100%;">	
			<thead style="background-color:#38B0DE;color:white;font-size:18px;"><tr><th style="text-align:justify;font-weight:bold;" colspan=7>
			Title of the papers
			</th></tr></thead>
			<tbody align="">
			<?php 
					
    				echo "<tr>";
				echo "<td>"; echo "<b>Compulsary Papers :</b>";echo"</td>";
				echo "</tr>";
				echo "<tr>";	
				   echo "<td>";
    						for($i = 0; $i<sizeof($compsubject); $i++)
    						{
        						$subjectdata = $this->commodel->get_listrow('subject','sub_id',$compsubject[$i]);
   							echo $subjectdata->row()->sub_name.' '.'/'.' ';
    						}
  					echo"</td>";  
   				echo "</tr>";
			?>
			</tbody>
		</table>

		<table class="TFtable" id="" style="width:100%;">	
				<tbody align="">
			<?php 
    				echo "<tr>";	
					echo"<td>"; echo "<b>Optional Papers :</b>";echo"</td>";
					echo "</tr>";
					echo "<tr>";
 					echo "<td>"; 
    						for($i = 0; $i<sizeof($elecsubject); $i++)
    						{
       							$elecsubjectdata = $this->commodel->get_listrow('subject','sub_id',$elecsubject[$i]);
        						echo $elecsubjectdata->row()->sub_name.' '.'/'.' ';
    						}
  					echo "</td>";
   				echo "</tr>";
			?>
			</tbody>
		</table>


		<table class="TFtable" id="" style="width:100%;">	
				<tbody align="">
			<?php 
    				echo "<tr>";	
					echo"<td>"; echo "<b>Practical Papers :</b>";echo"</td>";
				echo "</tr>";
					echo "<tr>";
 					/*echo"<td>"; 
    						for($i = 0; $i<sizeof($elecsubject); $i++)
    						{
       							$elecsubjectdata = $this->commodel->get_listrow('subject','sub_id',$elecsubject[$i]);
        						echo $elecsubjectdata->row()->sub_name;echo "</br>";
    						}
  					echo"</td>";*/
   				echo "</tr>";
			?>
			</tbody>
		</table>
		
  </div>


    </body>  
</html>    
