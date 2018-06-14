<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name viewfull_profile.php
@ author sumit saxena[sumitsesaxena@gmail.com]
@ author Manorama Pal[palseema30gmail.com] add view part for staff service particulars
 -->
<html>
<title>View Faculty list</title>
    <head>    
        <?php $this->load->view('template/header'); ?>
        
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <style type="text/css" media="print">
            @page {
                size: auto;   /* auto is the initial value */
                margin:0;  /* this affects the margin in the printer settings */
            }
        </style>
        <script>
      
            function printDiv(printme) {
                var printContents = document.getElementById(printme).innerHTML; 
                var originalContents = document.body.innerHTML;      
                document.body.innerHTML = "<html><head><title></title></head><body><img src='<?php echo base_url(); ?>uploads/logo/logotanuvas.jpeg' alt='logo' align='left' style='width:70%;height:100px;'>"+" <div style='width:70%;height:100%;'> " + printContents + "</div>"+"</body>";
                // document.body.style.fontSize = "x-small";
                //document.body.style. = "x-small";
                window.print();  
                // document.body.style.fontSize = "initial";
                document.body.innerHTML = originalContents;
            }
		 function goBack() {
        		window.history.go(-2);
	        }

        </script>

    </head>
    <body>
    <table style="width:100%;">
        <tr>
            <td>
                <img src='<?php echo base_url(); ?>uploads/logo/print1.png' alt='print'  onclick="javascript:printDiv('printme')" style='width:30px;height:30px;' title="Click for print" >  
                
            </td> 
	 <td align=right>
        <a href="#" onclick="goBack()"><img src='<?php echo base_url(); ?>uploads/icons/back1.png' title="Back"></a>
	</td>
        </tr>
    </table>        
    <div id="printme">   
      
<table style="width:100%;" border=0>
    <div align="left">
            
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                <?php echo form_error('<div class="isa_error">','</div>');?>
                
	        <?php if(isset($_SESSION['success'])){?>
                    <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                <?php
                };
                ?>
                 <?php if(isset($_SESSION['err_message'])){?>
                    <div  class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                <?php
                };
                ?>    
                  
        </div>
 
<tr>
<td valign="top" width=170>

<table border=1 class="TFtable">
		<tr>
			<?php if(!empty($data->emp_photoname)):?>
                            <td><img src="<?php echo base_url('uploads/SIS/empphoto/'.$data->emp_photoname);?>"  alt="" v:shapes="_x0000_i1025" style="width:100%;height:170px;" id="pimg"></td>
                         <?php else :?>
                            <td><img src="<?php echo base_url('uploads/SIS/empphoto/empdemopic.png');?>"  alt="" v:shapes="_x0000_i1025" style="width:100%;height:170px;" id="pimg"></td>
			 <?php endif;?>
			
			
		</tr>
		<tr>
			<td>Phone No. </br><?php echo $data->emp_phone;?></td>
		</tr>
		<tr>	
			<td>E-mail Id </br> <?php echo $data->emp_secndemail;?></td>
		</tr>
		<tr></tr>
		<tr><td height=60></td><tr>
    </table>
	   
</td>
<?php     $hdept=$this->sismodel->get_listspfic1('user_role_type','deptid','userid',$this->session->userdata('id_user'))->deptid; ?>
<td>		
	 <table style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
                        <tr style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
                            <td align=left colspan=4><b>Basic Profile Details</b></td>
                            <td align="right">
                                <?php
				$roleid=$this->session->userdata('id_role');
                                if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code))){
                                        echo anchor("staffmgmt/editempprofile/{$emp_id}","Edit",array('title' => 'Edit Details' , 'class' => 'red-link'));
                                }
/*				if( !empty($servicedata) ){  
                             		foreach($servicedata as $record){
						$currscnme=$this->commodel->get_listspfic1('study_center', 'sc_name', 'sc_id', $record->empsd_campuscode)->sc_name;
						$curruonme=$this->lgnmodel->get_listspfic1('authorities', 'name', 'id', $record->empsd_ucoid)->name;
						$curruocode=$this->lgnmodel->get_listspfic1('authorities', 'code', 'id', $record->empsd_ucoid)->code;
                                 		$currdeptnme=$this->commodel->get_listspfic1('Department', 'dept_name', 'dept_id', $record->empsd_deptid)->dept_name;
						$currschnme=$this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$record->empsd_schemeid)->sd_name;
						$currdesnme=$this->commodel->get_listspfic1('designation','desig_name','desig_code',$record->empsd_desigcode)->desig_name;
                                    		$pbname=$this->sismodel->get_listspfic1('salary_grade_master','sgm_name','sgm_id',$record->empsd_pbid)->sgm_name;
	                                	$pbmax=$this->sismodel->get_listspfic1('salary_grade_master','sgm_max','sgm_id',$record->empsd_pbid)->sgm_max;
        	                	        $pbmin=$this->sismodel->get_listspfic1('salary_grade_master','sgm_min','sgm_id',$record->empsd_pbid)->sgm_min;
                		                $pbgp= $this->sismodel->get_listspfic1('salary_grade_master','sgm_gradepay','sgm_id',$record->empsd_pbid)->sgm_gradepay;
                                    		$currpband= $pbname."(".$pbmin."-".$pbmax.")".$pbgp;
					break;
					}
				}*/
                                ?>

                            </td>
                        <tr>
                </table>
           <table class="TFtable" style="width:100%;">
<tr><td colspan="4"><label for="Work Information" style="font-size:17px;color:#0099CC"><b>Work Information:</b></label></td> </tr>
			<tr>
				<td>
<?php   			echo	"<b>Campus Name</b> <br>";
			/*	if(!empty($currscnme)) 
					echo $currscnme;
				else*/
					echo $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$data->emp_scid)->sc_name; 
?>
				</td>
				<td>
<?php 				echo "<b>UO Name</b><br>";
					echo $this->lgnmodel->get_listspfic1('authorities', 'name', 'id',$data->emp_uocid)->name ."(".$this->lgnmodel->get_listspfic1('authorities', 'code', 'id',$data->emp_uocid)->code.")"; 
?>

				</td>
				<td>
<?php 				echo "<b>Department </b><br>";
					echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$data->emp_dept_code)->dept_name; 
?>
				</td>
				<td>
<?php 				echo "<b>Scheme Name</b><br>";
					echo $this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$data->emp_schemeid)->sd_name;
?>
				</td>
			</tr>
  			<tr>
                                <td>
<?php   echo    "<b>Drawing and Disbursing Officer</b> <br>".$this->sismodel->get_listspfic1('ddo','ddo_name','ddo_id',$data->emp_ddoid)->ddo_name; ?>
                                </td>
                                <td>

<?php   echo    "<b>Working Type</b> <br>".$data->emp_worktype; ?>
                                </td>
                                <td>
<?php   echo    "<b>Group</b> <br>".$data->emp_group ?>
                                </td>
                                <td>
<?php   			echo    "<b>Designation</b> <br>";
					echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$data->emp_desig_code)->desig_name; 
					if($this->headflag){
        		                        echo " & Head";
	                                }
?>
                                </td>
                        </tr>
			<tr>
                                <td>
<?php   echo    "<b>Shown against the Post</b> <br>".$data->emp_post; ?>
                                </td>
                                <td>

<?php   echo    "<b>Plan / Non Plan</b> <br>".$data->emp_pnp ?>
                                </td>
                                <td>
<?php   echo    "<b>Employee Type</b> <br>".$data->emp_type_code; ?>
                                </td>
  <td>
<?php   echo    "<b>Application Order No</b> <br>".$data->emp_apporderno; ?>
                                </td>
</tr>
<tr>
                                <td>
<?php   
		$splsub = $this->commodel->get_listspfic1('subject','sub_name','sub_id',$data->emp_specialisationid);
		echo    "<b>Specialisation(Major Subject)</b> <br>";
		if(!empty($splsub)){
		echo 	$splsub->sub_name;
		}
 ?>
                                </td>
<td>
<?php   
	 		    $payband=$this->sismodel->get_listspfic1('salary_grade_master','sgm_name','sgm_id',$data->emp_salary_grade)->sgm_name;
                            $pay_max=$this->sismodel->get_listspfic1('salary_grade_master','sgm_max','sgm_id',$data->emp_salary_grade)->sgm_max;
                            $pay_min=$this->sismodel->get_listspfic1('salary_grade_master','sgm_min','sgm_id',$data->emp_salary_grade)->sgm_min;
                            $gardepay=$this->sismodel->get_listspfic1('salary_grade_master','sgm_gradepay','sgm_id',$data->emp_salary_grade)->sgm_gradepay;

				echo    "<b>Pay Band</b> <br>";
					echo $payband."(".$pay_min."-".$pay_max.")".$gardepay;
?>
                                </td>
<td>
<?php   echo    "<b>Basic Pay</b> <br>".$data->emp_basic;?>
                                </td>
   
                                <td>
<?php   echo    "<b>Emolution</b> <br>".$data->emp_emolution;?>
                                </td>
</tr>
<tr>
                                <td>
<?php   echo    "<b>NHIS ID No</b> <br>".$data->emp_nhisidno;?>
                                </td>
  <td>
<?php   echo    "<b>Date of Appointment</b> <br>". implode('-', array_reverse(explode('-', $data->emp_doj)));?>
                                </td>
                                <td>
<?php   echo    "<b>Date of Retirement</b> <br>".implode('-', array_reverse(explode('-', $data->emp_dor)));?>
                                </td>
 <td>
<?php   echo    "<b>Date of Probation</b> <br>".implode('-', array_reverse(explode('-', $data->emp_doprobation)));?>
                                </td>
</tr>
<tr>
  <td>
<?php   echo    "<b>Date of Regularisation</b> <br>".implode('-', array_reverse(explode('-', $data->emp_doregular)));?>
                                </td>
 <td>
<?php  // echo    "<b>Date of HGP</b> <br>".date('d-m-Y', strtotime($data->emp_dateofHGP ));?>
<?php   echo    "<b>Date of HGP</b> <br>".implode('-', array_reverse(explode('-', $data->emp_dateofHGP)));?>
                               </td>
<td>
</td>
<td>
</td>
</tr>

<tr><td colspan="4"><label for="Personal Informatiom" style="font-size:17px;color:#0099CC"><b>Personal Infomation:</b></label></td> </tr>
<tr>
                                <td>
<?php   echo    "<b>Employee PF No</b> <br>".$data->emp_code; ?>
                                </td>
                       
                                <td>
<?php   echo    "<b>Employee Name</b> <br>".$data->emp_name ;?>
                                </td>
                                <td>

<?php   echo    "<b>Fathers Name</b> <br>".$data->emp_father; ?>
                                </td>
                              
                       
                                <td>
<?php   echo    "<b>Gender</b> <br>".$data->emp_gender;?>
                                </td>
 </tr>
			<tr>
                                <td>
<?php   echo    "<b>Community</b> <br>".$data->emp_community;?>
                                </td>
                                <td>
<?php   echo    "<b>Religion</b> <br>".$data->emp_religion;?>
                                </td>
                                <td>
<?php   echo    "<b>Caste</b> <br>".$data->emp_caste;?>
                                </td>
                                <td>
<?php   echo    "<b>Whether Physically handicapped</b> <br>".$data->emp_phstatus;?>
                                </td>
</tr>
<tr>
                                <td>
<?php   echo    "<b>Details of PH</b> <br>".$data->emp_phdetail;?>
                                </td>
                                <td>
<?php   echo    "<b>Blood Group</b> <br>".$data->emp_bloodgroup?>
                                </td>
                                <td>
<?php   echo    "<b>Date of Birth</b> <br>".implode('-', array_reverse(explode('-', $data->emp_dob)));?>
                                </td>
<td>
<?php   echo    "<b>Pan No</b> <br>".$data->emp_pan_no;?>
                                </td>
                        </tr>
<tr>
<td>
<?php 
	  $fulldata=$data->emp_bank_ifsc_code;
                    $bname=explode(",",$fulldata);
	  echo    "<b>Bank Name</b> <br>".$bname[0]; ?>
                                </td>
                                <td>
<?php   echo    "<b>IFSC Code</b> <br>".$bname[1]; ?>
                                </td>

                                <td>
<?php   echo    "<b>Bank ACC No</b> <br>".$data->emp_bank_accno;?>
                                </td>
                                <td>
<?php   echo    "<b>Aadhaar No</b> <br>".$data->emp_aadhaar_no;?>
                                </td>
</tr>
                              
                                <!--td>
<?php   //echo   <b>Phd Status</b> <br>".$data->emp_phd_status;?>
                                </td>
                                <td>
<?php  // echo  "<b>Date of Phd Completion</b> <br>".implode('-', array_reverse(explode('-', $data->emp_dateofphd)));?>
                                </td-->
<tr><td colspan="4"><label for="Educationcal Informatiom" style="font-size:17px;color:#0099CC"><b>Educational Infomation:</b></label></td> </tr>
 <td>
<?php   echo    "<b>Qualification</b> <br>".$data->emp_qual;?>
                                </td>
                                <td>
<?php   echo    "<b>ASSR Exam Status</b> <br>".$data->emp_AssrExam_status?>
                                </td>
                                <td>
<?php   echo    "<b>Date of ASSR Exam</b> <br>".implode('-', array_reverse(explode('-', $data->emp_dateofAssrExam))) ;?>
                                </td>
<td>
<?php   echo    "<b>Grade</b> <br>".$data->emp_grade;?>
</td>
</tr>
<tr><td colspan="4"><label for="Communication Informatiom" style="font-size:17px;color:#0099CC"><b>Communication Infomation:</b></label></td> </tr>  
<tr>                              
                                <td>
<!--?php   echo    "<b>E-Mail ID</b> <br>".$data->emp_email;?-->
<?php   echo    "<b>Email Id</b> <br>".$data->emp_secndemail;?>

                                </td>
                                <td>
<?php   echo    "<b>Phone/Mobile</b> <br>".$data->emp_phone;?>
                                </td>
 <td>
<?php   echo    "<b>Residential Address</b> <br>".$data->emp_address;?>
                                </td>
<td>
</td>
</tr>
<tr><td colspan="4"><label for="Other Informatiom" style="font-size:17px;color:#0099CC"><b>Other Infomation:</b></label></td> </tr>
<tr>
                                <td>
<?php   echo    "<b>Mother Tongue</b> <br>".$data->emp_mothertongue;?>
                                </td>
                                <td>
<?php   echo    "<b>Nativity</b> <br>".$data->emp_citizen;?>
                                </td> 
                                <td>
<?php   echo    "<b>Remarks</b> <br>".$data->emp_remarks;?>                                </td>
</td>
<td>
</td>
</tr>
<tr><td colspan="4"><label for="PhD Details " style="font-size:17px;color:#0099CC"><b>PhD Details:</b></label></td> </tr>
<tr><td><b>Phd Status</b><br><?php echo $data->emp_phd_status;?></td><td><b>Date of Phd Completion</b><br><?php   echo implode('-', array_reverse(explode('-', $data->emp_dateofphd)));?></td><td><b>Discipline</b><br><?php   echo    $data->emp_phddiscipline;?></td> <td><b>PhD Type</b><br><?php   echo    $data->emp_phdtype;?></td></tr><tr><td><b>Institute Name</b><br><?php echo$data->emp_phdinstname;?></td><td><b> Deputed by Unversity</b><br><?php
          $udep=$data->emp_phdunivdeput;
                    $udepnew=explode(",",$udep);
          echo    $udepnew[0];?>
</td>
<?php if((!empty($udepnew[0])) && ($udepnew[0] == "No")){ ?>
<td>
<?php  
echo    "<b>If NO (Type of Leave availed for Ph.D) </b> <br>";
echo    $this->sismodel->get_listspfic1('leave_type_master','lt_name','lt_id',$udepnew[1])->lt_name;?>
				 <td>
<?php  echo    "<b>Leave From</b> <br>".$udepnew[2];?>
                                </td>
</tr>
<tr>
<td>
<?php echo    "<b>Leave To</b> <br>".$udepnew[3];?>
</td>
<td>
</td>
<td>
</td>
<td>
</td>
</tr>
<?php } ?>
<tr><td colspan="4"><label for="PhD Details " style="font-size:17px;color:#0099CC"><b>NET Details:</b></label></td> </tr>
                                </td>
        <?php
          $ntq=$data->emp_netqualified;
                if(!empty($ntq)){
                    $ntqnew=explode(",",$ntq);
echo    "<td><b>NET qualified</b> <br>".$ntqnew[0]; ?>
</td><td><b>Organiser</b><br><?php   echo    $ntqnew[1];?></td><td><b>Year of Passing</b><br><?php   echo    implode('-', array_reverse(explode('-', $data->emp_netpassingyear)));?></td><td><b>Discipline</b><br><?php   echo     $data->emp_netdiscipline;}?></td></tr>
<tr><td colspan="4"><label for="PhD Details " style="font-size:17px;color:#0099CC"><b>Veterinary Council of india (VCI) Registration:</b></label></td> </tr>
<tr>
<td>
<?php   echo    "<b>Registration No</b> <br>".$data->emp_vciregno;?>
</td>
<td>
<?php   echo    "<b>Date of Registration</b> <br>".date('d-m-Y',strtotime($data->emp_vciregdate));?>
</td>
<td>
</td>
<td>
</td>
</tr>
                        </tr>
<tr><td colspan="4"><label for="PhD Details " style="font-size:17px;color:#0099CC"><b>Additional Assignments:</b></label></td> </tr>
                        <tr style="background:none repeat scroll 0 0 #9D9D9D";>
                            <td><b>Name of the Assignment</b></td>
                            <td><b>Date From</b></td>
                            <td><b>Date To</b></td>
                            <td><b>Assignment Place</b></td>
                        </tr>
<?php if( count($addassign->result()) ):
   foreach($addassign->result() as $recrd){
        if(!empty($recrd->aa_asigname)){

?>
<tr>
                                <td>
 <?php
                        if(substr($recrd->aa_asigname, 0, 7) === "Others,"){
                                $rstr=substr($recrd->aa_asigname, 7, strlen($recrd->aa_asigname));
                                echo $rstr;

                        }
                        else{
                                echo $recrd->aa_asigname;
                        }
                ?>
</td>
 <td>
<?php   echo  date('d-m-Y',strtotime($recrd->aa_asigperiodfrom));?>
</td>
<td>
<?php   echo  date('d-m-Y',strtotime($recrd->aa_asigperiodto));?>
</td>
<td>
<?php   echo $recrd->aa_place;?>
</td></tr>
 <?php }}; ?>
<tr>
                        <?php else : ?>
                            <td colspan= "13" align="center"> No Records found...!</td>
                        <?php endif;?>
</tr>
		</table>
		<table style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
                        <tr style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
                            <td align=left colspan=4><b></b></td>
                            <td align="right">
                                <?php
                                if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code))){
                                        echo anchor("staffmgmt/editempprofile/{$emp_id}","Edit",array('title' => 'Edit Details' , 'class' => 'red-link'));
                                }
                                ?>

                            </td>
                        <tr>
                </table>
<br>
		<table style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
			<tr style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
                            <td align=left colspan=4><b>Service Particulars</b></td>
                            <td align="right">
                                <?php 
                                if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code))||($roleid == 4)){
					echo anchor("empmgmt/add_servicedata/{$emp_id}"," Add ",array('title' => ' Add Service Data' , 'class' => 'red-link'));
				}
				?>
				
                            </td>  
                        <tr>
		</table>
		<table class="TFtable" align="center">
                    <thead>
                        <tr>
                            <th>Campus Details</th>
                            <th>Designation Details</th>
                            <th>AGP/Grade Pay Details</th>
                            <th>From/To</th>
                            <th colspan="2">Total service (YY/MM/DD)</th>
                        </tr>
                    </thead>
                    <tbody>
                        
                        <?php if( !empty($servicedata) ):  ?>
                            <?php foreach($servicedata as $record){;
//print_r($record);
//die;
?>
                            <tr>
                                <td>
                                    
                                    <!--?php
                                    $cname=$this->commodel->get_listspfic1('study_center','sc_name','sc_code',$record->empsd_campuscode)->sc_name;
                                    echo $cname."&nbsp;"."(".$record->empsd_campuscode.")";
                                    ?-->
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
                                    <?php  $dojoin=implode('-', array_reverse(explode('-', $record->empsd_dojoin))); ?>
                                    <?php  $dorelve=implode('-', array_reverse(explode('-', $record->empsd_dorelev))); ?>
				    <?php echo "<b>From-: </b>".$dojoin."<br>"."<b>To-: </b>".$dorelve;?>
                                </td>
                                <td>
                                    <?php 
                                    $date1 = new DateTime($record->empsd_dojoin);
                                    $date2 = new DateTime($record->empsd_dorelev);
                                    $diff = $date1->diff($date2);
                                    echo "<b>&nbsp;&nbsp;".$diff->y . "&nbsp;&nbsp;&nbsp; " . $diff->m."&nbsp;&nbsp;&nbsp; ".$diff->d." "
                                    ;?>
                                </td>
                                <td>
                                <?php 
                                if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code))||($roleid == 4)){
						echo anchor("empmgmt/edit_servicedata/{$record->empsd_id}","Edit",array('title' => ' Edit Performance Data' , 'class' => 'red-link'));
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
<br>
                <table style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
			<tr style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
                            <td align=left colspan=4><b>Performance Details</b></td>
                            <td colspan="5" align="right">
                            <?php
				if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code))||($roleid == 4)){
                                	if(count($performancedata)){
                                    		echo anchor("empmgmt/editextstaffpro/{$emp_id}"," Edit ",array('title' => ' Edit Performance Data' , 'class' => 'red-link'));  
                                	}
                                    	else{
                                    		echo anchor("empmgmt/extstaffpro/{$emp_id}"," Add ",array('title' => ' Add Performance Data' , 'class' => 'red-link'));
                                	}    
				}
                            ?>
                        </td>
                        
                    </tr>
                
		</table>
                <table class="TFtable">
                    
                    <?php if(count($performancedata)):;?>
                    <tr style=" background-color:gray;width:100%;"><td colspan="5"><b>Awards and Medals : </b></td></tr>
                    <tr>
                        <th>Description</th>
                        <th colspan="5"> Number of Medals</th>
                        
                        <tbody>
                            <tr>
                                <td><?php echo "International";?></td>
                                <td colspan="5"><?php echo $performancedata->spd_int_award;?></td>
                            </tr>
                            <tr>
                                <td><?php echo "National";?></td>
                                <td colspan="5"><?php echo $performancedata->spd_nat_award;?></td>
                            </tr> 
                            <tr>
                                <td><?php echo "University";?></td>
                                <td colspan="5"><?php echo $performancedata->spd_uni_award;?></td>
                            </tr>   
                        </tbody>     
                    </tr>
                    <tr style=" background-color:gray;width:100%;"><td colspan="5"><b>Publications : </b></td></tr>
                    <tr>
                        <th>Description</th>
                        <th> National</th>
                        <th colspan="4"> International</th>
                        <tbody>
                            <tr>
                                <td><?php echo "Research";?></td>
                                <td><?php echo $performancedata->spd_res_pub_nat;?></td>
                                <td colspan="3"><?php echo $performancedata->spd_res_pub_int;?></td>
                            </tr>
                            <tr>
                                <td><?php echo "Popular";?></td>
                                <td><?php echo $performancedata->spd_pop_pub_nat;?></td>
                                <td colspan="3"><?php echo $performancedata->spd_pop_pub_int; ?></td>
                            </tr> 
                            <tr>
                                <td><?php echo "Presentation";?></td>
                                <td><?php echo $performancedata->spd_pre_pub_nat; ?></td>
                                <td colspan="3"><?php echo $performancedata->spd_pre_pub_int; ?></td>
                            </tr>   
                        </tbody>     
                    </tr>
                    <tr style=" background-color:gray;width:100%;"><td colspan="5"><b>Project handled : </b></td></tr>
                    <tr>
                        <th>Number of Projects handled</th>
                        <th colspan="4"> Fund outlay</th>
                       
                        <tbody>
                            <tr>
                                <td><?php echo $performancedata->spd_noof_project; ?></td>
                                <td colspan="4"><?php echo $performancedata->spd_fund_outly_ofproject; ?></td>
                            </tr>
                           
                        </tbody>     
                    </tr>
                    <tr style=" background-color:gray;width:100%;"><td colspan="5"><b>Training attended (Seminar / Symposium / Workshop etc.) : </b></td></tr>
                    <tr>
                        <th></th>
                        <th colspan="4"> Number of Trainings attended</th>
                       
                        <tbody>
                            <tr>
                                <td><?php echo "National"; ?></td>
                                <td colspan="4"><?php echo $performancedata->spd_tr_att_nat; ?></td>
                            </tr>
                            <tr>
                                <td><?php echo "International"; ?></td>
                                <td colspan="4"><?php echo $performancedata->spd_tr_att_int; ?></td>
                            </tr>
                        </tbody>     
                    </tr>
                    <tr style=" background-color:gray;width:100%;"><td colspan="5"><b>Training conducted (Seminar / Symposium / Workshop etc.) : </b></td></tr>
                    <tr>
                        <th></th>
                        <th colspan="4"> Number of Trainings conducted</th>
                       
                        <tbody>
                            <tr>
                                <td><?php echo "National"; ?></td>
                                <td colspan="4"><?php echo $performancedata->spd_tr_con_nat; ?></td>
                            </tr>
                            <tr>
                                <td><?php echo "International"; ?></td>
                                <td colspan="4"><?php echo $performancedata->spd_tr_con_int; ?></td>
                            </tr>
                        </tbody>     
                    </tr>
                    <tr style=" background-color:gray;width:100%;"><td colspan="5"><b>Students Guided : </b></td></tr>
                    <tr>
                        <th></th>
                        <th colspan="4"> Number of students guided</th>
                       
                        <tbody>
                            <tr>
                                <td><?php echo "MVSc"; ?></td>
                                <td colspan="4"><?php echo $performancedata->spd_mvsc_stu_guided; ?></td>
                            </tr>
                            <tr>
                                <td><?php echo "Phd"; ?></td>
                                <td colspan="4"><?php echo $performancedata->spd_phd_stu_guided; ?></td>
                            </tr>
                            <tr>
                                <td><?php echo "Others"; ?></td>
                                <td colspan="4"><?php echo $performancedata->spd_others_stu_guided; ?></td>
                            </tr>
                        </tbody>     
                    </tr>
                    <tr style=" background-color:gray;width:100%;"><td colspan="5"><b>Guest lecture delivered : </b></td></tr>
                    <tr>
                        <tbody>
                            <tr>
                                <td><?php echo "Number of Guest lecture delivered"; ?></td>
                                <td colspan="4"><?php echo $performancedata->spd_no_ofguestlecture; ?></td>
                            </tr>
                        </tbody>     
                    </tr>
                    <tr></tr>
                    <tr><td><b>File Name</b></td>
                        <td colspan="4">
			<?php  if (!empty($performancedata->spd_per_filename)){ ?>
                            <a href="<?php echo base_url().'uploads/SIS/perfattachment/'.$performancedata->spd_per_filename ; ?>"
                               target="_blank" type="application/octet-stream" download="<?php echo $performancedata->spd_per_filename ?>">Download the pdf</a>
			<?php } 
				else{
                                echo " No attachment found";
                                }
			?>
                        </td>
                    </tr>
                    <?php else : ?>
                    <td colspan= "7" align="center"> No Records found...!</td>
                    <?php endif;?>
                    
                </table>
		<table style="width:100%;">
        	<tr>
        	<td align=right>
		<a href="#" onclick="goBack()"><img src='<?php echo base_url(); ?>uploads/icons/back1.png' title="Back"></a>
        	</td>
        	</tr>
    		</table>
	<br>
</td>
</tr>


</table>
       
   </div>      
 <p> &nbsp; </p>
<div align="center">  <?php $this->load->view('template/footer');?></div>
    </body>
</html>

