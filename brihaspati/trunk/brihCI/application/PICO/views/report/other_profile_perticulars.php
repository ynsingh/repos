<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name other_profile_perticulars.php
@ author Nagendra Kumar Singh[nksinghiitk@gmail.com]
 -->
<!--<html>
<title>View profile perticulars</title>
    <head>    
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
    </head>
    <body> -->
<br>
<!--<table style="width:100%;" border=0>
 
<tr>
<?php     $hdept=$this->sismodel->get_listspfic1('user_role_type','deptid','userid',$this->session->userdata('id_user'))->deptid; ?>
<td>-->		
		<table style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
			<tr style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
                            <td align=left colspan=4><b>Leave Particulars</b></td>
                            <td align="right">
                                <?php 
                                if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code))||($roleid == 4)){
			//		echo anchor("empmgmt/add_leavepertdata/{$emp_id}"," Add ",array('title' => ' Add Leave Data' , 'class' => 'red-link'));
				}
				?>
				
                            </td>  
                        <tr>
		</table>
		<table class="TFtable" align="center">
                    <thead>
                        <tr>
                            <th>Nature of Leave</th>
                            <th>From Date</th>
                            <th>To Date</th>
			    <th>No. of Days</th>
				<th> </th>
                        </tr>
                    </thead>
                    <tbody>
                        
                        <?php if( !empty($leavedata) ):  ?>
                            <?php foreach($leavedata as $record){;
?>
                            <tr>
                                <td>
                                    
				<?php $sc=$this->commodel->get_listspfic1('study_center', 'sc_name', 'sc_id', $record->empsd_campuscode)->sc_name; 
				"&nbsp;"."(".$this->commodel->get_listspfic1('study_center', 'sc_code', 'sc_id', $record->empsd_campuscode)->sc_code.")";
				 if ($record->empsd_ucoid != 0) $uo=$this->lgnmodel->get_listspfic1('authorities', 'name', 'id', $record->empsd_ucoid)->name; 
                                 if ($record->empsd_deptid != 0)$dept=$this->commodel->get_listspfic1('Department', 'dept_name', 'dept_id', $record->empsd_deptid)->dept_name; 
				 $schme=$this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$record->empsd_schemeid)->sd_name;
				 $ddo=$this->sismodel->get_listspfic1('ddo','ddo_name','ddo_id',$record->empsd_ddoid)->ddo_name; 
				echo "<b>Campus-: </b>".$sc."<br/> "."<b>UO-: </b>".$uo."<br/> "."<b>Dept-: </b>".$dept."<br/> "."<b>Scheme-: </b>".$schme."</br> "."<b>DDO-: </b>".$ddo;
                                ?>
				</td>
                                <td>
                                    <?php 
				    $desig=$this->commodel->get_listspfic1('designation','desig_name','desig_code',$record->empsd_desigcode)->desig_name; 
				    $showagpost=$this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id', $record->empsd_shagpstid)->desig_name;
				    $group=$record->empsd_group;
				    $worktype=$record->empsd_worktype;
				    echo "<b>Designation-: </b>".$desig."<br/> "."<b>Show Again Post-: </b>".$showagpost."<br/> "."<b>Group-: </b>".$group."<br/> "."<b>Worktype-: </b>".$worktype;
                                    ?>
                               </td>
<td> 
                                    <?php
                                    $pbname=$this->sismodel->get_listspfic1('salary_grade_master','sgm_name','sgm_id',$record->empsd_pbid)->sgm_name; 
                                    $pbmax=$this->sismodel->get_listspfic1('salary_grade_master','sgm_max','sgm_id',$record->empsd_pbid)->sgm_max;
                                    $pbmin=$this->sismodel->get_listspfic1('salary_grade_master','sgm_min','sgm_id',$record->empsd_pbid)->sgm_min;
                                    $pbgp= $this->sismodel->get_listspfic1('salary_grade_master','sgm_gradepay','sgm_id',$record->empsd_pbid)->sgm_gradepay;
				    $dateofagp=implode('-', array_reverse(explode('-', $record->empsd_pbdate))); 
				    $gradepay=$record->empsd_gradepay;
                                    $level=$record->empsd_level;
                                    echo "<b>Pay Band-: </b>".$pbname."(".$pbmin."-".$pbmax.")".$pbgp."<br>"."<b>Grade Pay-: </b>".$gradepay."<br>"."<b>Level-: </b>" .$level."<br>"."<b>Date of AGP-: </b>".$dateofagp; ?>
                                </td>
                                <td>
				    <?php echo "<b>From-: </b>".$dojoin."<br>"."<b>To-: </b>".$dorelve;?>
                                </td>
                                <td>
                                <?php 
                                if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code))||($roleid == 4)){
			//			echo anchor("empmgmt/edit_leavepertdata/{$record->empsd_id}","Edit",array('title' => ' Edit Leave Data' , 'class' => 'red-link'));
					}
				?>
                                </td> 
                            </tr>
                        <?php }; ?>
                        <?php else : ?>
                            <td colspan= "13" align="center"> No Records found...!</td>
                        <?php endif;?>
                    </tbody>    
		</table>
<!--</td>
</tr><tr>		
<td>-->		
<br>
		<table style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
			<tr style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
                            <td align=left colspan=4><b>Deputation Particulars</b></td>
                            <td align="right">
                                <?php 
                                if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code))||($roleid == 4)){
					echo anchor("empmgmt/add_deputatdata/{$emp_id}"," Add ",array('title' => ' Add Deputation  Data' , 'class' => 'red-link'));
				}
				?>
				
                            </td>  
                        <tr>
		</table>
		<table class="TFtable" align="center">
                    <thead>
                        <tr>
                            <th>Deputation</th>
                            <th>Specification</th>
                            <th>From Date</th>
                            <th>To Date</th>
                            <th ></th>
                        </tr>
                    </thead>
                    <tbody>
                        
                        <?php if( !empty($deputdata) ):  ?>
                            <?php foreach($deputdata as $record){;
?>
                            <tr>
                                <td>
				<?php 
				 echo $record->sdp_deputation;
                                ?>
				</td>
                                <td>
				<?php 
					echo $record->sdp_specification;
                                    ?>
                               </td>
				<td> 
                                    <?php
				echo $record->sdp_fromdate;
				?>
                                </td>
                                <td>
				    <?php echo $record->sdp_todate; ?>
                                </td>
                                <td>
                                <?php 
                                if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code))||($roleid == 4)){
				//		echo anchor("empmgmt/edit_deputatdata/{$record->empsd_id}","Edit",array('title' => ' Edit Deputation Data' , 'class' => 'red-link'));
					}
				?>
                                </td> 
                            </tr>
                        <?php }; ?>
                        <?php else : ?>
                            <td colspan= "13" align="center"> No Records found...!</td>
                        <?php endif;?>
                    </tbody>    
		</table>
<!--</td>
</tr><tr>		
<td>-->
<br>		
		<table style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
			<tr style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
                            <td align=left colspan=4><b>Departmental Exam Passed Details</b></td>
                            <td align="right">
                                <?php 
                                if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code))||($roleid == 4)){
					echo anchor("empmgmt/add_deptexamdata/{$emp_id}"," Add ",array('title' => ' Add Departmental Exam Data' , 'class' => 'red-link'));
				}
				?>
				
                            </td>  
                        <tr>
		</table>
		<table class="TFtable" align="center">
                    <thead>
                        <tr>
                            <th>Department Exam</th>
                            <th>Date of Passing</th>
                            <th ></th>
                        </tr>
                    </thead>
                    <tbody>
                        
                        <?php if( !empty($deptexamdata) ):  ?>
                            <?php foreach($deptexamdata as $record){;
?>
                            <tr>
                                <td>
<?php 
				echo $record->sdep_examname;
                                ?>
				</td>
                                <td>
                                    <?php 
				    echo $record->sdep_passdate;
                                    ?>
                               </td>
                                <td>
                                <?php 
                                if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code))||($roleid == 4)){
				//		echo anchor("empmgmt/edit_deptexamdata/{$record->empsd_id}","Edit",array('title' => ' Edit Departmental Exam Data' , 'class' => 'red-link'));
					}
				?>
                                </td> 
                            </tr>
                        <?php }; ?>
                        <?php else : ?>
                            <td colspan= "13" align="center"> No Records found...!</td>
                        <?php endif;?>
                    </tbody>    
		</table>
<!--</td>
</tr><tr>		
<td>-->
<br>		
		<table style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
			<tr style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
                            <td align=left colspan=4><b>Working Arrangement Particulars</b></td>
                            <td align="right">
                                <?php 
                                if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code))||($roleid == 4)){
			//		echo anchor("empmgmt/add_workarrangdata/{$emp_id}"," Add ",array('title' => ' Add Working Arrangement Data' , 'class' => 'red-link'));
				}
				?>
				
                            </td>  
                        <tr>
		</table>
		<table class="TFtable" align="center">
                    <thead>
                        <tr>
                            <th>Parent Details</th>
                            <th>Working Details</th>
                            <th ></th>
                        </tr>
                    </thead>
                    <tbody>
                        
                        <?php if( !empty($workarrangdata) ):  ?>
                            <?php foreach($workarrangdata as $record){;
?>
                            <tr>
                                <td>
                                    
			<?php 
				$sc=$this->commodel->get_listspfic1('study_center', 'sc_name', 'sc_id', $record->swap_ocampus)->sc_name; 
				"&nbsp;"."(".$this->commodel->get_listspfic1('study_center', 'sc_code', 'sc_id', $record->swap_ocampus)->sc_code.")";
				 if ($record->swap_ouo != 0) $uo=$this->lgnmodel->get_listspfic1('authorities', 'name', 'id', $record->swap_ouo)->name; 
                                 if ($record->swap_odept != 0)$dept=$this->commodel->get_listspfic1('Department', 'dept_name', 'dept_id', $record->swap_odept)->dept_name; 
//				 $schme=$this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$record->empsd_schemeid)->sd_name;
//				 $ddo=$this->sismodel->get_listspfic1('ddo','ddo_name','ddo_id',$record->empsd_ddoid)->ddo_name; 
				 echo "<b>Campus-: </b>".$sc."<br/> "."<b>UO-: </b>".$uo."<br/> "."<b>Dept-: </b>".$dept;
				 //."<br/> "."<b>Scheme-: </b>".$schme."</br> "."<b>DDO-: </b>".$ddo;
                                ?>
				</td>
                                <td>
                                    <?php 
				$sc=$this->commodel->get_listspfic1('study_center', 'sc_name', 'sc_id', $record->swap_wcampus)->sc_name; 
				"&nbsp;"."(".$this->commodel->get_listspfic1('study_center', 'sc_code', 'sc_id', $record->swap_wcampus)->sc_code.")";
				 if ($record->swap_wuo != 0) $uo=$this->lgnmodel->get_listspfic1('authorities', 'name', 'id', $record->swap_wuo)->name; 
                                 if ($record->swap_wdept != 0)$dept=$this->commodel->get_listspfic1('Department', 'dept_name', 'dept_id', $record->swap_wodept)->dept_name; 
//				 $schme=$this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$record->empsd_schemeid)->sd_name;
//				 $ddo=$this->sismodel->get_listspfic1('ddo','ddo_name','ddo_id',$record->empsd_ddoid)->ddo_name; 
				 echo "<b>Campus-: </b>".$sc."<br/> "."<b>UO-: </b>".$uo."<br/> "."<b>Dept-: </b>".$dept;
				 //."<br/> "."<b>Scheme-: </b>".$schme."</br> "."<b>DDO-: </b>".$ddo;
                                    ?>
                               </td>
                                <td>
                                <?php 
                                if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code))||($roleid == 4)){
				//		echo anchor("empmgmt/edit_workarrangdata/{$record->empsd_id}","Edit",array('title' => ' Edit Working Arrangement Data' , 'class' => 'red-link'));
					}
				?>
                                </td> 
                            </tr>
                        <?php }; ?>
                        <?php else : ?>
                            <td colspan= "13" align="center"> No Records found...!</td>
                        <?php endif;?>
                    </tbody>    
		</table>
<!--</td>
</tr><tr>		
<td>-->
<br>		
		<table style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
			<tr style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
                            <td align=left colspan=4><b>Recruitment Particulars</b></td>
                            <td align="right">
                                <?php 
                                if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code))||($roleid == 4)){
			//		echo anchor("empmgmt/add_recmethddata/{$emp_id}"," Add ",array('title' => ' Add Recruitment Method Data' , 'class' => 'red-link'));
				}
				?>
				
                            </td>  
                        <tr>
		</table>
		<table class="TFtable" align="center">
                    <thead>
                        <tr>
                            <th>Method Of Recruitment</th>
                            <th>Direct Category</th>
                            <th>Direct Details</th>
                            <th>Compassionate Deatils</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        
                        <?php if( !empty($recruitdata) ):  ?>
                            <?php foreach($recruitdata as $record){;
?>
                            <tr>
                                <td>
                                    
				<?php 
				echo $record->srp_methodrecrtmnt;
                                ?>
				</td>
                                <td>
                                    <?php 
				echo $record->srp_subcategory;
                                    ?>
                               </td>
<td> 
				<?php 
				echo $record->srp_detail;
                                    ?>
                                </td>
                                <td>
				    <?php 
				if ($record->srp_compassionname != 0) $cempname=$this->commodel->get_listspfic1('employee_master','emp_name','emp_code',$record->srp_compassionname)->emp_name;
				if ($record->srp_compassiondesig != 0) $desig=$this->commodel->get_listspfic1('designation','desig_name','desig_id',$record->srp_compassiondesig)->desig_name; 
                                 if ($record->srp_compassiondept != 0) $dept=$this->commodel->get_listspfic1('Department', 'dept_name', 'dept_id', $record->srp_compassiondept)->dept_name; 
				echo "<b>Compassion Name-: </b>".$cempname."<br>"."<b>Designation-: </b>".$desig."<br>"."<b>Department-: </b>". $dept;
				?>
                                </td>
                                <td>
                                <?php 
                                if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code))||($roleid == 4)){
						echo anchor("empmgmt/edit_recmethddata/{$record->empsd_id}","Edit",array('title' => ' Edit Recruitment Method Data' , 'class' => 'red-link'));
					}
				?>
                                </td> 
                            </tr>
                        <?php }; ?>
                        <?php else : ?>
                            <td colspan= "13" align="center"> No Records found...!</td>
                        <?php endif;?>
                    </tbody>    
		</table>
<!--</td>
</tr><tr>		
<td>-->
<br>		
		<table style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
			<tr style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
                            <td align=left colspan=4><b>Disciplinary Action Details</b></td>
                            <td align="right">
                                <?php 
                                if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code))||($roleid == 4)){
					echo anchor("empmgmt/add_disciplindata/{$emp_id}"," Add ",array('title' => ' Add Disciplinary Action Data' , 'class' => 'red-link'));
				}
				?>
				
                            </td>  
                        <tr>
		</table>
		<table class="TFtable" align="center">
                    <thead>
                        <tr>
                            <th>Nature of Punishment</th>
                            <th>Reason</th>
                            <th>Status</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        
                        <?php if( !empty($disciplinactdata) ):  ?>
                            <?php foreach($disciplinactdata as $record){;
?>
                            <tr>
                                <td>
			<?php 
				echo $record->sdap_punishnature;
                                ?>
				</td>
                                <td>
                                    <?php 
				    echo $record->sdap_punishreason;
                                    ?>
                               </td>
<td> 
                                    <?php
				echo $record->sdap_punishstatus; 
?>
                                </td>
                                <td>
                                <?php 
                                if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code))||($roleid == 4)){
				//		echo anchor("empmgmt/edit_disciplindata/{$record->empsd_id}","Edit",array('title' => ' Edit Disciplinary Action Data' , 'class' => 'red-link'));
					}
				?>
                                </td> 
                            </tr>
                        <?php }; ?>
                        <?php else : ?>
                            <td colspan= "13" align="center"> No Records found...!</td>
                        <?php endif;?>
                    </tbody>    
		</table>
<!--</td>
		
</tr>
</table>
    </body>
</html>

    -->   
