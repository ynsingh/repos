
<!--@filename viewempprofile.php  @author Manorama Pal(palseema30@gmail.com)
-->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
    <head>
        <title>Welcome to TANUVAS</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/style.css">
	<style type="text/css" media="print">
        @page {
                size: auto;   /* auto is the initial value */
                margin:0;  /* this affects the margin in the printer settings */
            }
        </style>
        <script type="text/javascript">
             function printDiv(printme) {
                var printContents = document.getElementById(printme).innerHTML; 
                //alert("printContents==="+printContents);
                var originalContents = document.body.innerHTML;      
                document.body.innerHTML = "<html><head><title></title></head><body style='width:100%;'><img src='<?php echo base_url(); ?>uploads/logo/logotanuvas.jpeg' alt='logo' style='width:100%;height:100px;'>"+" <div style='width:100%;height:100%;'>  " + printContents + "</div>"+"</body>";
                window.print();     
                document.body.innerHTML = originalContents;
            }
	</script>
    </head>
    <body>
        <?php $this->load->view('template/header'); ?>
        <table width="100%">
           <tr colspan="8">
<!--<td> -->
                <?php
                    echo "<td align=\"left\" width=\"33%\">";
                    echo "</td>";
                    echo "<td align=\"center\" width=\"34%\" style=\"font-size:16px\">";
                    echo "<b>View Employee Profile</b>";
                    echo "</td>";
                    echo "<td align=\"right\" width=\"33%\" style=\"font-size:16px\">";
                    $help_uri = site_url()."/help/helpdoc#ViewProfile";
                    echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                    echo "</td>";
		   // echo "</tr>";
		   // echo "</table>";
                ?>
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                <?php echo form_error('<div style="margin-left:30px;" class="isa_error">','</div>');?>
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
</tr>
        </table>
<?php $print=false;  $current="basic"; ?>

	<div id="printme" align="left" style="width:100%;">
        <div class="scroller_sub_page">
        <table width="100%" border=0>
                <tr style=" background-color:grey;width:100%;"> 
			<td colspan="10"><img src='<?php echo base_url(); ?>uploads/logo/print1.png' alt='print'  align="left" onclick="javascript:printDiv('printme')" style='width:30px;height:30px;' title="Click for print" >
			</td>
		</tr>
               	<tr> 
			<td valign="top">
			<table>
		<!--		<tr>
				<td align="center" colspan="10">
                		    <?php //if(!empty($record->emp_photoname)):;?>
		                        <img src="<?php //echo base_url('uploads/SIS/empphoto/'.$record->emp_photoname);?>"  alt="" v:shapes="_x0000_i1025" width="85" height="100">
                		    <?php //else:?>
		                        <img src="<?php //echo base_url('uploads/SIS/empphoto/'."empdemopic.png");?>"  id="output_image" v:shapes="_x0000_i1025" width="78" height="94"/>
                		    <?php //endif?>
                      
                		</td></tr>-->
				<tr>
					<td valign="top" width=170>
			                <?php 	
						include 'empprofiletab.php'; 
					?>
					</td>

				</tr>
			</table>
			</td>
			<td valign="top">
			<table  border=0>
                		<tr style=" background-color:grey;width:100%;"><td colspan="10">
		                    <p><b>Personal Information :</b></p>
                </td></tr>
                <tr></tr>
                <tr> 
                    <td><font color='Blue'>Employee PF No : </font></td>
                    <td><?php echo $record->emp_code;?></td> 
                    <td><font color='Blue'>Employee Name :</font></td>
                    <td><?php echo $record->emp_name ;?></td> 
                    <td><font color='Blue'>Fathers Name :</font></td>
                    <td colspan="2"><?php echo $record->emp_father; ?></td>
               
                </tr>
                <tr></tr>
                <tr> 
                    <td><font color='Blue'>Spouse Name :</font> </td>
                    <td><?php echo $record->emp_spousename ;?></td> 
                    <td><font color='Blue'>Gender :</font> </td>
                    <td><?php echo $record->emp_gender;?></td> 
                    <td><font color='Blue'>Community :</font></td>
                    <td  colspan="2"><?php echo $record->emp_community;?></td> 
               
                </tr>
                <tr></tr>
                <tr> 
                    <td><font color='Blue'>Religion :</font></td>
                    <td><?php echo $record->emp_religion;?></td>
                    <td><font color='Blue'>Caste : </font></td>
                    <td><?php echo $record->emp_caste; ?></td> 
                    <td><font color='Blue'>Whether Physically Handicapped :</font></td>
                    <td  colspan="2"><div>
			<?php if($record->emp_phstatus == 'yes'){
					echo "Yes";
				}else{
					echo "No";
				}
			?>
		<!--	<input type="radio" name="phstatus" value="yes" <?php // echo ($record->emp_phstatus == 'yes'?'checked="checked"':''); ?> >Yes &nbsp;&nbsp;&nbsp;
                        <input type="radio" name="phstatus" value="no" <?php // echo ($record->emp_phstatus == 'no'?'checked="checked"':'"checked"'); ?> >No
-->
                    </div> </td> 
               
                </tr>
                <tr></tr>
                <tr> 
                    <td><font color='Blue'>Details of PH :</font></td>
                    <td><?php echo $record->emp_phdetail; ?></td>
                    <td><font color='Blue'>Blood Group : </font></td>
                    <td><?php echo $record->emp_bloodgroup;?></td> 
                    <td><font color='Blue'>Date Of Birth :</font></td>
                    <td  colspan="2"><?php echo implode('-', array_reverse(explode('-', $record->emp_dob))); ?></td> 
               
                </tr>
                <tr></tr>
                <tr> 
                    <td><font color='Blue'>Pan No :</font></td>
                    <td><?php echo $record->emp_pan_no;?></td>
                    <td><font color='Blue'>Bank Name :</font> </td>
                    <td>
                        <?php 
                        $fulldata=$record->emp_bank_ifsc_code;
                        $bname=explode(",",$fulldata);  
                        echo $bname[0]; 
                        ;?>
                    </td> 
                    <td><font color='Blue'>IFSC Code :</font></td>
                    <td  colspan="2"><?php echo $bname[1]; ?></td> 
               
                </tr>
                <tr></tr>
                <tr> 
                    <td><font color='Blue'>Bank ACC No :</font></td>
                    <td><?php echo $record->emp_bank_accno; ?></td>
                    <td><font color='Blue'>Aadhaar No :</font> </td>
                    <td><?php echo $record->emp_aadhaar_no; ?></td> 
                    <!--<td>IFSC Code :</td>
                    <td> <?php //echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$record->emp_desig_code)->desig_name;?></td> 
                    <td>Bank ACC No :</td>
                    <td><?php //echo $record->emp_post; ?></td>-->
                    <td><font color='Blue'>Marital Status : </font></td>
                    <td colspan="2" ><?php echo $record->emp_maritalstatus; ?></td>
   
                </tr>
                <tr></tr>
                <tr></tr>
                <tr style=" background-color:grey;width:100%;"><td colspan="10">
                    <p><b>Communication Information:</b></p>
                <tr><td>
                <tr></tr>
                <tr>
		<td><font color='Blue'>Email Id:</font></td>
                <td><?php   echo $record->emp_secndemail; ?></td> 
                    <td><font color='Blue'>Phone/Mobile No :</font></td>
                    <td><?php echo $record->emp_phone; ?></td> 
                    <td><font color='Blue'>Residential Address:</font></td>
                    <td><?php echo $record->emp_address;?></td>
               
                </tr>
                
                <tr></tr>
                <tr></tr>
                <tr style=" background-color:grey;width:100%;"><td colspan="10">
                    <p><b>Educational Information:</b></p>
                </td></tr>    
                <tr></tr>
                <tr> 
                    <td><font color='Blue'>Qualification:</font></td>
                    <td><?php echo $record->emp_qual;?></td> 
                    <td><font color='Blue'>ASSR Exam:</font></td>
                    <td><?php echo $record->emp_AssrExam_status;?></td> 
                    <td><font color='Blue'>Date Of ASSR Exam:</font></td>
                    <td colspan="3"> <?php echo implode('-', array_reverse(explode('-', $record->emp_dateofAssrExam))) ;?></td> 
                   <!-- <td>Date Of Phd Completion :</td>
                    <td><?php echo $record->emp_post; ?></td>-->
               
                </tr>
                <tr></tr>
                <tr></tr>
                <tr style=" background-color:grey;width:100%;"><td colspan="10">   
                    <p><b>Work Information:</b></p>
                </td></tr>
                <tr></tr>
                <tr>
                    <td><font color='Blue'>Campus Name :</font></td>
                    <td>
<?php 
				echo $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$record->emp_scid)->sc_name. "( ". $this->commodel->get_listspfic1('study_center','sc_code','sc_id',$record->emp_scid)->sc_code ." )";
?>
                    </td>
                    <td><font color='Blue'>University Officer Control:</font></td>
		    <td>
		    <?php 
			$authname=$this->lgnmodel->get_listspfic1('authorities', 'name', 'id',$record->emp_uocid)->name;
                        $authcode=$this->lgnmodel->get_listspfic1('authorities', 'code', 'id',$record->emp_uocid)->code;
                        	echo  $authname." " . "( ".$authcode." )";    
?>
                    </td>
                    <td><font color='Blue'>Department :</font></td>
                    <td colspan="2"> 
<?php 
				echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$record->emp_dept_code)->dept_name . "( ". $this->commodel->get_listspfic1('Department','dept_code','dept_id',$record->emp_dept_code)->dept_code ." )";
?>
                    </td>
                    <!--<td>
                         <?php if(!empty($record->emp_photoname)):;?>
                        <img src="<?php echo base_url('uploads/SIS/empphoto/'.$record->emp_photoname);?>"  alt="" v:shapes="_x0000_i1025" width="85" height="100">
                    <?php else:?>
                        <img src="<?php echo base_url('uploads/SIS/empphoto/'."empdemopic.png");?>"  id="output_image" v:shapes="_x0000_i1025" width="78" height="94"/>
                    <?php endif?>    
                    </td>-->
                    
                </tr>
                <tr></tr>
                <tr> 
                    <td><font color='Blue'>Scheme Name : </font></td>
                    <td>
<?php 
				echo $this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$record->emp_schemeid)->sd_name . "( ". $this->sismodel->get_listspfic1('scheme_department','sd_code','sd_id',$record->emp_schemeid)->sd_code ." )";
?>
		</td> 
                    <td><font color='Blue'>Drawing and Disbursing Officer :</font></td>
                    <td> <?php echo $this->sismodel->get_listspfic1('ddo','ddo_name','ddo_id',$record->emp_ddoid)->ddo_name . "( ". $this->sismodel->get_listspfic1('ddo','ddo_code','ddo_id',$record->emp_ddoid)->ddo_code ." )";?></td> 
                    <td><font color='Blue'>Working Type :</font></td>
                    <td colspan="2"><?php echo $record->emp_worktype;?></td>
               
                </tr>
                <tr></tr>
                <tr> 
                    <td><font color='Blue'>Group : </font></td>
                    <td><?php echo $record->emp_group;?></td> 
                    <td><font color='Blue'>Designation :</font></td>
                    <td> 
<?php
				echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$record->emp_desig_code)->desig_name;
				if(($this->headflag)||($record->emp_head == "HEAD")){
				echo " & Head";
				}
?>
			</td> 
                    <td><font color='Blue'>Shown Against The Post :</font></td>
                    <td colspan="2"><?php echo $record->emp_post; ?></td>
               
                </tr>
                <tr></tr>
                <tr> 
                    <td><font color='Blue'>Plan / Non : </font></td>
                    <td><?php echo $record->emp_pnp;?></td> 
                    <td><font color='Blue'>Employee Type :</font></td>
                    <td> <?php echo $record->emp_type_code;?></td> 
                    <td><font color='Blue'>Application Order No:</font></td>
                    <td colspan="2"><?php echo $record->emp_apporderno ?></td>
               
                </tr>
                <tr></tr>
                <tr> 
                    <td><font color='Blue'>Specialisation(Major Subject) :</font> </td>
                    <td><?php 
                        if(!empty($record->emp_specialisationid)){
                            echo $this->commodel->get_listspfic1('subject','sub_name','sub_id',$record->emp_specialisationid)->sub_name;
                        }    
                        ?>
                    </td> 
                    <td><font color='Blue'>Pay Band :</font></td>
                    <td>  <?php
                            $payband=$this->sismodel->get_listspfic1('salary_grade_master','sgm_name','sgm_id',$record->emp_salary_grade)->sgm_name;
                            $pay_max=$this->sismodel->get_listspfic1('salary_grade_master','sgm_max','sgm_id',$record->emp_salary_grade)->sgm_max;
                            $pay_min=$this->sismodel->get_listspfic1('salary_grade_master','sgm_min','sgm_id',$record->emp_salary_grade)->sgm_min;
                            $gardepay=$this->sismodel->get_listspfic1('salary_grade_master','sgm_gradepay','sgm_id',$record->emp_salary_grade)->sgm_gradepay;
                        	echo $payband."(".$pay_min."-".$pay_max.")".$gardepay;
                        ?>    
                    </td> 
                    <td><font color='Blue'>Basic Pay :</font></td>
                    <td colspan="2"><?php echo $record->emp_basic; ?></td>
               
                </tr>
                <tr></tr>
                <tr> 
                    <td><font color='Blue'>Emolution: </font></td>
                    <td><?php echo $record->emp_emolution; ?></td> 
                    <td><font color='Blue'>NHIS ID No :</font></td>
                    <td> <?php echo $record->emp_nhisidno; ?></td> 
                    <td><font color='Blue'>Date Of Appointment :</font></td>
                    <td colspan="2"><?php echo implode('-', array_reverse(explode('-', $record->emp_doj)))." ".$record->emp_jsession; ?></td>
               
                </tr>
                <tr></tr>
                <tr> 
                    <td><font color='Blue'>Date Of Retirement: </font></td>
                    <td><?php echo implode('-', array_reverse(explode('-', $record->emp_dor))); ?></td> 
                    <td><font color='Blue'>Date of Probation :</font></td>
                    <td><?php echo implode('-', array_reverse(explode('-', $record->emp_doprobation)));?></td> 
                    <td><font color='Blue'>Date of Regularisation :</font></td>
                    <td colspan="2"><?php echo implode('-', array_reverse(explode('-', $record->emp_doregular)));?></td>               
                </tr>
                <tr></tr>
                <tr>
                    <td><font color='Blue'>Date Of HGP: </font></td>
		    <td><?php echo implode('-', array_reverse(explode('-', $record->emp_dateofHGP))); ?></td> 
	            <td><font color='Blue'>Seniority No: </font></td>
                    <td colspan="4"><?php echo $record->emp_seniortyid; ?></td>
                </tr> 
                <tr></tr>
                <tr></tr> 
                <tr style=" background-color:grey;width:100%;"><td colspan="10">
                    <p><b>Other Information :</b></p>
                </td></tr>
                <tr></tr>
                <tr> 
                    <td><font color='Blue'>Mother Tongue : </font></td>
                    <td><?php echo $record->emp_mothertongue; ?></td> 
                    <td><font color='Blue'>Nativity :</font></td>
                    <td><?php echo $record->emp_citizen; ?></td> 
                    <td><font color='Blue'>Remarks :</font></td>
                    <td colspan="2"><?php echo $record->emp_remarks;?></td>
		       
			</tr>
                <tr></tr>
                <tr></tr>
		 <tr style=" background-color:grey;width:100%;"><td colspan="10">
                    <p><b>PhD Details:</b></p>
                </td></tr>
		<tr></tr>
                <tr>
                  <td><font color='Blue'>Phd Status :</font></td>
                    <td><?php echo $record->emp_phd_status;?></td> 
                    <td><font color='Blue'>Date Of Phd Completion:</font></td>
                    <td><?php echo implode('-', array_reverse(explode('-', $record->emp_dateofphd))); ?></td>
		<td><font color='Blue'>Discipline:</font></td>
		<td><?php   echo $record->emp_phddiscipline;?></td>
                </tr><tr>
		<td><font color='Blue'>PhD Type:</font></td>
		<td><?php   echo $record->emp_phdtype;?></td>
		<td><font color='Blue'>Institute Name:</font></td>
		<td><?php   echo $record->emp_phdinstname;?></td>
		<?php
          	$udep=$record->emp_phdunivdeput;
                    $udepnew=explode(",",$udep);
		?>
		<?php if((!empty($udepnew[0])) && ($udepnew[0] == "No")){ ?>
         	<td><font color='Blue'>Deputed by Unversity:</font></td>
		<td><?php  echo $udepnew[0]; ?></td>
                </tr>
                <tr>
		<td><font color='Blue'>If NO (Type of Leave availed for Ph.D):</font></td>
		<td><?php echo $this->sismodel->get_listspfic1('leave_type_master','lt_name','lt_id',$udepnew[1])->lt_name;?></td>
		<td><font color='Blue'>Leave From:</font></td>
		<td><?php   echo $udepnew[2]; ?></td>
		<td><font color='Blue'>Leave To:</font></td>
		<td><?php   echo $udepnew[3]; ?></td>
		<?php } ?>
                </tr>
		<tr></tr>
                <tr></tr>
                 <tr style=" background-color:grey;width:100%;"><td colspan="10">
                    <p><b>NET Details:</b></p>
                </td></tr>
                <tr></tr>
                <tr>
		<?php
		$ntq=$record->emp_netqualified;
                if(!empty($ntq)){
                    $ntqnew=explode(",",$ntq);
		?>
		<td><font color='Blue'>NET qualified:</font></td>
		<td><?php   echo $ntqnew[0]; ?></td>
		<td><font color='Blue'>Organizar:</font></td>
		<td><?php echo $ntqnew[1]; ?></td>
		<td><font color='Blue'>Year of Passing:</font></td>
		<td><?php  echo implode('-', array_reverse(explode('-', $record->emp_netpassingyear)));?></td>
		</tr>
		<tr>
		<td><font color='Blue'>Discipline:</font></td>
		<td><?php   echo $record->emp_netdiscipline;}?></td>
		</tr>
		<tr></tr>
                <tr></tr>
                 <tr style=" background-color:grey;width:100%;"><td colspan="10">
                    <p><b>Veterinary Council (VC) Registration:</b></p>
                </td></tr>
                <tr></tr>
		<?php

        if(count($emsdata)){
                if(!empty($emsdata->ems_vci_status)){
                        if($emsdata->ems_vci_status == "Not Applicable"){
                                echo "<tr><td colspan=8> Not Applicable </td></tr>";
                        }else{  
                                echo "<tr>";
                                echo "<td> <font color='Blue'>Chapter</font></td> <td>".$emsdata->ems_vci_statchapter."</td>";
                                echo "<td> <font color='Blue'>Registration No</font></td> <td>".$emsdata->ems_vci_statregno."</td>";
                                echo "<td> <font color='Blue'>Date of Registration</font></td> <td>".date('d-m-Y',strtotime($emsdata->ems_vci_statregdate))."</td>";
                                echo "</tr>";
                                echo "<tr>";
                                echo "<td> <font color='Blue'>Validity Date</font></td> <td colspan=5>".date('d-m-Y',strtotime($emsdata->ems_vci_statvaliddate))."</td>";
                                echo "</tr>";
                                echo "<tr>";
                                echo "<td> <font color='Blue'>All India Registration No</font></td> <td>".$emsdata->ems_vci_alliregno."</td>";
                                echo "<td> <font color='Blue'>All India Date of Registration</font></td> <td>".date('d-m-Y',strtotime($emsdata->ems_vci_alliregdate))."</td>";
                                echo "<td> <font color='Blue'>All India Validity Date</font></td> <td>".date('d-m-Y',strtotime($emsdata->ems_vci_allivaliddate))."</td>";
                                echo "</tr>";
                
                        }
                }
        }

?>
<!--
                <tr>
		<td><font color='Blue'>Registration No</font></td>
		<td><?php   //echo $record->emp_vciregno; ?></td>
		<td><font color='Blue'>Date of Registration:</font></td>
		<td><?php //echo date('d-m-Y',strtotime($record->emp_vciregdate));?></td>
		</tr>
		<tr></tr>
-->
                <tr></tr>
                <tr style=" background-color:grey;width:100%;"><td colspan="10">
                    <p><b>Additional Assignments:</b></p>
                </td>
<!--		<td colspan="9" align="right">
            	</td>-->
                </tr>
                <tr></tr> 
                <tr>
		<?php if( count($addassign->result()) ):  ?>
		<td colspan="2"><b>Name of the Assignment</b></td>
                <td colspan="2"><b>Date From</b></td>
                <td colspan="2"><b>Date To</b></td>
		<td colspan="2"><b>Place</b></td>
                <tbody>
                            <?php foreach($addassign->result() as $recrd){
		if(!empty($recrd->aa_asigname)){?>
		<tr>
                                <td colspan="2">
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
		 <td colspan="2">
		<?php   echo  date('d-m-Y',strtotime($recrd->aa_asigperiodfrom));?>
		</td>
		<td colspan="2">
		<?php   echo  date('d-m-Y',strtotime($recrd->aa_asigperiodto));?>
		</td>
		<td colspan="2">
		<?php   echo $recrd->aa_place;?>
		</td>
		 <?php }}; ?>
                        <?php else : ?>
                            <td colspan= "13" align="center"> No Records found...!</td>
                        <?php endif;?>
                </tbody>
		</tr>

                <tr></tr>
<?php if($print){  ?>
                <tr style=" background-color:grey;width:100%;"><td colspan="10">
                    <p><b>Service Data :</b></p>
                    </td>
                  <!--  <td colspan="9" align="right">
                    <?php
                       // if(count($servicedata->result())){
                          //  echo anchor("empmgmt/editextstaffpro/{$emp_id}"," Edit ",array('title' => ' Edit Performance Data' , 'class' => 'red-link'));  
                        //}
                       //else{
                           // echo anchor("empmgmt/add_servicedata/{$emp_id}"," Add ",array('title' => ' Add Service Data' , 'class' => 'red-link'));
                        //}    
                    ?>
                    </td>-->
                </tr>
                <tr></tr>
                <tr>
                    <?php if( count($servicedata) ):  ?>
                            <td colspan="2"><b>Campus Details</b></td>
                            <td colspan="2"><b>Designation Details</b></td>
                            <td colspan="2"><b>AGP/Grade Pay Details</b></td>
                            <td colspan="2"><b>From/To</b></td>
                            <td colspan="2"><b>Total service (YY/MM/DD)</b></td>
                    <tbody>
                        
                            <?php foreach($servicedata as $record){;?>
                            <tr>
                                <td colspan="2">
                                    
                                    <?php
				 $sc=$this->commodel->get_listspfic1('study_center', 'sc_name', 'sc_id', $record->empsd_campuscode)->sc_name;
                                 "&nbsp;"."(".$this->commodel->get_listspfic1('study_center', 'sc_code', 'sc_id', $record->empsd_campuscode)->sc_code.")";
                                 if ($record->empsd_ucoid != 0) $uo=$this->lgnmodel->get_listspfic1('authorities', 'name', 'id', $record->empsd_ucoid)->name;
				 if ($record->empsd_deptid != 0)$dept=$this->commodel->get_listspfic1('Department', 'dept_name', 'dept_id', $record->empsd_deptid)->dept_name;
                                 $schme=$this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$record->empsd_schemeid)->sd_name; 
				 $ddo=$this->sismodel->get_listspfic1('ddo','ddo_name','ddo_id',$record->empsd_ddoid)->ddo_name; 
				echo "<b>Campus-: </b>".$sc."<br/> "."<b>UO-: </b>".$uo."<br/> "."<b>Dept-: </b>".$dept."<br/> "."<b>Scheme-: </b>".$schme."</br> "."<b>DDO-: </b>".$ddo;
                                ?>
                                </td>
                                <td colspan="2">
				<?php 
				$desig=$this->commodel->get_listspfic1('designation','desig_name','desig_code',$record->empsd_desigcode)->desig_name; 
                                $showagpost=$this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id', $record->empsd_shagpstid)->desig_name;
				$group=$record->empsd_group;
				$worktype=$record->empsd_worktype;
                                echo "<b>Designation-: </b>".$desig."<br/> "."<b>Show Again Post-: </b>".$showagpost."<br/> "."<b>Group-: </b>".$group."<br/> "."<b>Worktype-: </b>".$worktype;?>
				</td>

                                 <td colspan="2">
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

                                </td>
                                <td colspan="2">
				    <?php  $dojoin=implode('-', array_reverse(explode('-', $record->empsd_dojoin))); ?>
                                    <?php  $dorelve=implode('-', array_reverse(explode('-', $record->empsd_dorelev))); ?>
				    <?php echo "<b>From-: </b>".$dojoin." ".$record->empsd_fsession."<br>"."<b>To-: </b>".$dorelve ." " .$record->empsd_tsession;?>
                                </td>
                                <td colspan="2">
                                    <?php 
                                    $date1 = new DateTime($record->empsd_dojoin);
                                    $date2 = new DateTime($record->empsd_dorelev);
                                    $diff = $date1->diff($date2);
                                    echo "<b>&nbsp;&nbsp;&nbsp;&nbsp;".$diff->y . "&nbsp;&nbsp;&nbsp;" . $diff->m."&nbsp;&nbsp; ".$diff->d. "</b>"
                                    ;?>
                                    
                                    
                                </td>
                                <td >
                                <?php //echo anchor("empmgmt/edit_servicedata/{$record->empsd_id}","Edit",array('title' => ' Edit Performance Data' , 'class' => 'red-link'));?>
                                </td>   
                            </tr>
                        <?php }; ?>
                        
                    </tbody> 
                    <?php else : ?>
                            <td colspan= "8" align="center"> No Records found...!</td>
                    <?php endif;?>
		</tr>
                <tr></tr>
                <tr><td colspan="11">
                    <HR  COLOR="#6699FF" SIZE="2">
                </td></tr>
                <tr></tr>
                <tr><td>
                    <p><b>Performance Data :</b></p>
                    </td>
                    <td colspan="11" align="right">
                    <?php
                 /*       if(count($performancedata)){
                            echo anchor("empmgmt/editextstaffpro/{$emp_id}"," Edit ",array('title' => ' Edit Performance Data' , 'class' => 'red-link'));  
                        }
                        else{
                            echo anchor("empmgmt/extstaffpro/{$emp_id}"," Add ",array('title' => ' Add Performance Data' , 'class' => 'red-link'));
                        }    */
                    ?>
                    </td>
                </tr>
                <tr></tr>
                <?php if(count($performancedata)):;?>
                    <tr></tr> 
                    <tr style=" background-color:grey;width:100%;"><td colspan="11"><b>Awards and Medals : </b></td></tr>
                    <tr>
                        <td><b>Description</b></td>
                        <td> <b>Number of Medals</b></td>
                        
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
                    <tr style=" background-color:grey;width:100%;"><td colspan="11"><b>Publications : </b></td></tr>
                    <tr>
                        <td><b>Description</b></td>
                        <td><b> National</b></td>
                        <td colspan="4"> <b>International</b></td>
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
                    <tr style=" background-color:grey;width:100%;"><td colspan="11"><b>Project handled : </b></td></tr>
                    <tr>
                        <td><b>Number of Projects handled</b></td>
                        <td colspan="4"> <b>Fund outlay</b></td>
                       
                        <tbody>
                            <tr>
                                <td><?php echo $performancedata->spd_noof_project; ?></td>
                                <td colspan="4"><?php echo $performancedata->spd_fund_outly_ofproject; ?></td>
                            </tr>
                           
                        </tbody>     
                    </tr>
                    <tr style=" background-color:grey;width:100%;"><td colspan="11"><b>Training attended (Seminar / Symposium / Workshop etc.) : </b></td></tr>
                    <tr>
                        <td></td>
                        <td colspan="4"><b> Number of Trainings attended</b></td>
                       
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
                    <tr style=" background-color:grey;width:100%;"><td colspan="11"><b>Training conducted (Seminar / Symposium / Workshop etc.) : </b></td></tr>
                    <tr>
                        <td></td>
                        <td colspan="4"><b> Number of Trainings conducted</b></td>
                       
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
                    <tr style=" background-color:grey;width:100%;"><td colspan="11"><b>Students Guided : </b></td></tr>
                    <tr>
                        <td></td>
                        <td colspan="4"><b> Number of students guided</b></td>
                       
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
                    <tr style=" background-color:grey;width:100%;"><td colspan="11"><b>Guest lecture delivered : </b></td></tr>
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
                        <td>
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
                    <td colspan= "11" align="center"> No Records found...!</td>
                    <?php endif;?>
            
                <tr></tr>
                <tr><td colspan="11">
                    <HR  COLOR="#6699FF" SIZE="2">
                </td></tr> 
		</table>
<?php } ?>
		</td></tr>
		</table>        
         </div>   
 </div>   
        <p> &nbsp; </p>   
        </div><?php $this->load->view('template/footer'); ?></div>
    </body>
</html>
