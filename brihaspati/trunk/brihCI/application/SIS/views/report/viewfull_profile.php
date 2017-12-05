<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name viewfull_profile.php
@ author sumit saxena[sumitsesaxena@gmail.com]
@ author Manorama Pal[palseema30gmail.com] add view part for staff service particulars
 -->
<html>
<title>View Faculty list</title>
    <head>    
        <?php $this->load->view('template/header'); ?>
        <?php $this->load->view('template/menu');?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
    </head>
    <body>
<p>
<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
</p>

<table style="width:100%;" border=0>
<tr>
<td valign="top" width=170>

    <table border=1 class="TFtable">
		<tr>
			<?php if(!empty($data->emp_photoname)):?>
                            <td><img src="<?php echo base_url('uploads/SIS/empphoto/'.$data->emp_photoname);?>"  alt="" v:shapes="_x0000_i1025" style="width:100%;height:170px;"></td>
                         <?php else :?>
                            <td><img src="<?php echo base_url('uploads/SIS/empphoto/empdemopic.png');?>"  alt="" v:shapes="_x0000_i1025" style="width:100%;height:170px;"></td>
			 <?php endif;?>
			
			
		</tr>
		<tr>
			<td>Phone No. </br><?php echo $data->emp_phone;?></td>
		</tr>
		<tr>	
			<td>E-mail Id </br> <?php echo $data->emp_email;?></td>
		</tr>
		<tr></tr>
		<tr><td height=60></td><tr>
    </table>
	   
</td>

<td>		
	
           <table class="TFtable" style="width:100%;">
			<tr>
				<td>Emp No. :</td>
				<td><?php echo $data->emp_code;?></td>
				<td>Date Of Appointment :</td>
			        <td><?php echo $data->emp_doj;?></td>
			</tr>
			
			<tr>
				<td>Name :</td>
				<td><?php echo $data->emp_name;?></td>
				<td>Department :</td>
				<?php 
				//$deptid = $dep;
				$depname = $this->commodel->get_listspfic1('Department','dept_name','dept_id',$data->emp_dept_code)->dept_name ;?>
				<td><?php echo $depname;?></td>
			</tr>
			<tr>
				<td>Designation :</td>
				<?php 
				//$desigid = $desig;
				$depname = $this->commodel->get_listspfic1('designation','desig_name','desig_id',$data->emp_desig_code)->desig_name ;?>
				<td><?php echo $depname;?></td>
				<td>Date Of Regularization :</td>
			        <td><?php //echo $dor;?></td>
			</tr>
			
			<tr>
				<td>Community :</td>
				<td><?php echo $data->emp_community;?></td>
				<td>Date Of Completion Of Probation :</td>
				<td><?php //echo $doprobe; ?></td>
			</tr>
			<tr>
				<td>Caste :</td>
				<td><?php echo $data->emp_caste;?></td>
				<td>Religion :</td>
			        <td><?php echo $data->emp_religion;?></td>
			</tr>
			
			<tr>
				<td>Nativity :</td>
				<td><?php echo $data->emp_citizen;?></td>
				<td>Specialization :</td>
				<?php
				//$specialid = $specialize;
				if(!empty($data->emp_specialisationid)){
					$specialize = $this->commodel->get_listspfic1('subject','sub_name','sub_id',$data->emp_specialisationid)->sub_name;	
				}
                                else{
                                    $specialize="";
                                }
				?>
				<td><?php echo $specialize;?></td>
			</tr>
			<tr>
				
				<td>To Present Post :</td>
			        <td><?php echo $data->emp_post;?></td>
				<td>Qualification (PHD Completion):</td>
				<td><?php echo $data->emp_qual;?></td>
			</tr>
			
			<tr>
				
				<td>Date Of Birth :</td>
				<td><?php echo $data->emp_dob;?></td>
				<td>Date Of Retirement :</td>
				<td><?php echo $data->emp_dor;?></td>
			</tr>
			<tr>
				<td>ASRR :</td>
				<td><?php echo $data->emp_AssrExam_status;?></td>
				<td></td><td></td>
			</tr>
		</table>
		<table style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
			<tr style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;"><td align=left colspan=4><b>Service Particulars</b></td><tr>
		</table>
		<table class="TFtable" align="center">
                    <thead>
                        <tr>
                            <th>Place of working</th>
                            <th>Designation</th>
                            <th>AGP</th>
                            <th>Date of AGP</th>
                            <th>From</th>
                            <th>To</th>
                            <th>Total service (year,  month,  day)</th>
                        </tr>
                    </thead>
                    <tbody>
                        
                        <?php if( count($servicedata->result()) ):  ?>
                            <?php foreach($servicedata->result() as $record){;?>
                            <tr>
                                <td>
                                    
                                    <?php
                                    $cname=$this->commodel->get_listspfic1('study_center','sc_name','sc_code',$record->empsd_campuscode)->sc_name;
                                    echo $cname."&nbsp;"."(".$record->empsd_campuscode.")";
                                    ?>
                                </td>
                                <td>
                                    <?php echo $this->commodel->get_listspfic1('designation','desig_name','desig_code',$record->empsd_desigcode)->desig_name; ?>
                                </td>
                                 <td>
                                    <?php
                                    $pbname=$this->sismodel->get_listspfic1('salary_grade_master','sgm_name','sgm_id',$record->empsd_pbid)->sgm_name; 
                                    $pbmax=$this->sismodel->get_listspfic1('salary_grade_master','sgm_max','sgm_id',$record->empsd_pbid)->sgm_max;
                                    $pbmin=$this->sismodel->get_listspfic1('salary_grade_master','sgm_min','sgm_id',$record->empsd_pbid)->sgm_min;
                                    $pbgp= $this->sismodel->get_listspfic1('salary_grade_master','sgm_gradepay','sgm_id',$record->empsd_pbid)->sgm_gradepay;
                                    echo $pbname."(".$pbmin."-".$pbmax.")".$pbgp;
                                    ;?>
                                </td>
                                <td>
                                    <?php echo $record->empsd_pbdate; ?>
                                </td>
                                <td>
                                    <?php echo $record->empsd_dojoin; ?>
                                </td>
                                <td>
                                    <?php echo $record->empsd_dorelev; ?>
                                </td>
                                <td>
                                    <?php 
                                    $date1 = new DateTime($record->empsd_dojoin);
                                    $date2 = new DateTime($record->empsd_dorelev);
                                    $diff = $date1->diff($date2);
                                    echo $diff->y . " years, " . $diff->m." months, ".$diff->d." days "
                                    ;?>
                                </td>   
                            </tr>
                        <?php }; ?>
                        <?php else : ?>
                            <td colspan= "13" align="center"> No Records found...!</td>
                        <?php endif;?>
                    </tbody>    
		</table>
</td>
</tr>

</table>
 <p> &nbsp; </p>
<div align="center">  <?php $this->load->view('template/footer');?></div>
    </body>
</html>

