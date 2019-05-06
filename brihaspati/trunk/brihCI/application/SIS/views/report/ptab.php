<?php
        $roleid=$this->session->userdata('id_role');
        if($roleid == 5){
                $hdept=$this->sismodel->get_listspfic1('user_role_type','deptid','userid',$this->session->userdata('id_user'))->deptid;
                $hempcode=$this->sismodel->get_listspfic1('hod_list','hl_empcode','hl_userid',$this->session->userdata('id_user'))->hl_empcode;
                $hempid=$this->sismodel->get_listspfic1('employee_master','emp_id','emp_code',$hempcode)->emp_id;
		$deptuocid=$this->commodel->get_listspfic1('Department','dept_uoid','dept_id',$hdept)->dept_uoid;
        }
        $uname=$this->session->userdata('username');
        $rest = substr($uname, -21);
//print_r($data);	
	$flagffs=false;
                                 $flagcppm=false;
                                 $flagro=false;
                                 $flaguooff =false;
                                 $flaghod=false;
                                if(($this->session->userdata('username') == 'deanffsoffice@tanuvas.org.in')&&(!(in_array($emp_id, $uoempid)))&&($deptuocid == $data->emp_uocid)){
                                        $flagffs=true;
                                }
                                if(($this->session->userdata('username') == 'deancppmoffice@tanuvas.org.in')&&(!(in_array($emp_id, $uoempid))&&($deptuocid == $data->emp_uocid))){
                                        $flagcppm=true;
                                }
                                if(($this->session->userdata('username') == 'ro@tanuvas.org.in') && (in_array($emp_id, $uoempid))){
                                        $flagro=true;
                                }
                                if(($rest == 'office@tanuvas.org.in') && (in_array($emp_id, $hodempid))&&(!(in_array($emp_id, $uoempid)))&&($deptuocid == $data->emp_uocid)){
                                        $flaguooff =true;
                                }
                                if(($roleid == 5)&&($hdept == $data->emp_dept_code)&&($emp_id != $hempid)&&(!(in_array($emp_id, $uoempid)))){
                                        $flaghod=true;
                                }
//				echo "roleid= ".$roleid." ffs = ".$flagffs." cppm = ". $flagcppm ." ro = ".$flagro." uo = ".$flaguooff. " hod = ".$flaghod ;
//        if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code)&&($emp_id != $hempid)&&(!(in_array($emp_id, $uoempid))))||(($this->session->userdata('username') == 'ro@tanuvas.org.in') && (in_array($emp_id, $uoempid)))||(($rest == 'office@tanuvas.org.in') && (in_array($emp_id, $hodempid)))){
                                if(($roleid == 1)||($flagffs)||($flagcppm)||($flagro)||($flaguooff)||($flaghod)){
                			include 'eprofiletab.php'; 
			                echo "</tr><tr>";
			        }else{
?>
				<td valign="top" width=170>
			                <?php include 'profiletab.php'; ?>

				</td>
<?php } ?>

