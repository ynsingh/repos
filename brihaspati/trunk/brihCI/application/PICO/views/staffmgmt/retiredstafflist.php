<!--@filename retiredstafflist.php  @author Manorama Pal(palseema30@gmail.com) -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <title>Welcome to TANUVAS</title>
    </head> 
    <body>
        <?php $this->load->view('template/header'); ?>
        <table width="100%"><tr style=" background-color: graytext;">
           <td align="center"><b>Retired Staff List</b> </td>  
        </tr></table>
        <table class="TFtable" >
            <thead>
                <tr>
                    <th>Sr.No</th>
                    <th></th>
                    <th>Employee Name</th>
                    <th>Details</th>
                    <th>Designation</th>
                    <th>Doj / Dor</th>
                    <th>Reason</th>
                    <th>Contact Details</th>
                    
                    
                </tr>
            </thead>
            <tbody>
                
                <?php $serial_no = 1;?>
              <?php if( count($records) ):  ?>
                    <?php foreach($records as $record){ ?>
                        <tr>
                            <td><?php echo $serial_no++; ?></td>
                            <?php //$img=$record->emp_code;?>
                            <?php $emphoto=$this->sismodel->get_listspfic1('employee_master','emp_photoname','emp_id',$record->sre_empid)->emp_photoname;
                            if(!empty($emphoto)):?>
                            <td><p><img src="<?php echo base_url('uploads/SIS/empphoto/'.$emphoto);?>"  alt="" v:shapes="_x0000_i1025" width="78" height="94"></p></td>
                            <?php else :?>
                            <td><p><img src="<?php echo base_url('uploads/SIS/empphoto/empdemopic.png');?>"  alt="" v:shapes="_x0000_i1025" width="78" height="94"></p></td>
                            <?php endif;?>
                            <td><?php
                                $empname=$this->sismodel->get_listspfic1('employee_master','emp_name','emp_id',$record->sre_empid)->emp_name;
                                echo $empname."<br/>" ."("."PF No:".$record->sre_empcode.")"."<br/>".$record->sre_empemailid;;
                            ?></td>
                            <td><?php
                            
                                $empscid=$this->sismodel->get_listspfic1('employee_master','emp_scid','emp_id',$record->sre_empid)->emp_scid;
                                $campus=$this->commodel->get_listspfic1('study_center','sc_name','sc_id',$empscid)->sc_name;
                                $empuoid=$this->sismodel->get_listspfic1('employee_master','emp_uocid','emp_id',$record->sre_empid)->emp_uocid;
                                $authority=$this->lgnmodel->get_listspfic1('authorities','name','id' ,$empuoid)->name;
                                $empdept=$this->sismodel->get_listspfic1('employee_master','emp_dept_code','emp_id',$record->sre_empid)->emp_dept_code;
                                $dept=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$empdept)->dept_name;
                                $empschm=$this->sismodel->get_listspfic1('employee_master','emp_schemeid','emp_id',$record->sre_empid)->emp_schemeid;
                                $schm=$this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$empschm)->sd_name;
				$schmcode=$this->sismodel->get_listspfic1('scheme_department','sd_code','sd_id',$empschm)->sd_code;
                                $subid=$this->sismodel->get_listspfic1('employee_master','emp_specialisationid','emp_id',$record->sre_empid)->emp_specialisationid;
                                if($subid >0){
                                    $subname=$this->commodel->get_listspfic1('subject','sub_name','sub_id',$subid)->sub_name;
                                    $subcode=$this->commodel->get_listspfic1('subject','sub_code','sub_id',$subid)->sub_code;
                                }
                                else{
                                    $subname='';
                                    $subcode='';
                                }
                                echo "<b>Campus : </b>".$campus."<br/> "."<b>Uo : </b>".$authority."<br/> "."<b>Dept : </b>".$dept."<br/> "."<b>Scheme : </b>".$schm
                                        ."(". $schmcode .")"."<br/>"."<b>Subject:</b>".$subname."(". $subcode .")";
                            ?></td>
                            <td><?php
                                $empdesig=$this->sismodel->get_listspfic1('employee_master','emp_desig_code','emp_id',$record->sre_empid)->emp_desig_code;
                                $desig=$this->commodel->get_listspfic1('designation','desig_name','desig_id',$empdesig)->desig_name; 
                                $payband=$this->sismodel->get_listspfic1('employee_master','emp_salary_grade','emp_id',$record->sre_empid)->emp_salary_grade;
                                $pbname=$this->sismodel->get_listspfic1('salary_grade_master','sgm_name','sgm_id',$payband)->sgm_name;
                                $pay_max=$this->sismodel->get_listspfic1('salary_grade_master','sgm_max','sgm_id',$payband)->sgm_max;
                                $pay_min=$this->sismodel->get_listspfic1('salary_grade_master','sgm_min','sgm_id',$payband)->sgm_min;
                                $gardepay=$this->sismodel->get_listspfic1('salary_grade_master','sgm_gradepay','sgm_id',$payband)->sgm_gradepay;
                                echo "<b>Designation:</b>".$desig."<br/>"."<b>Payband:</b>".$pbname."(".$pay_min."-".$pay_max.")".$gardepay; 
                                ?>
                            </td>
                            <td>
                                <?php 
                                $doj = date('d-m-Y H:i:s',strtotime($record->sre_doj));
                                $dor = date('d-m-Y H:i:s',strtotime($record->sre_dor));
                                $dob=$this->sismodel->get_listspfic1('employee_master','emp_dob','emp_id',$record->sre_empid)->emp_dob;
                                $dobnew=date('d-m-Y',strtotime($dob));
                               echo  "<b>Dob:</b>".$dobnew."<br/>". "<b>Doj:</b>".$doj . "<br/>" ."<b>Dor:</b>".$dor   ;
                                ?>
                            </td>
                            <td><?php
                                    
                            echo "<b>Reason:</b>".$record->sre_reason."<br/>"."<b>Date : </b>"."<font color=red>".date('d-m-Y H:i:s',strtotime($record->sre_reasondate))."</font>";
                            ?></td>
                            <td>
                                <?php   
                                    $phoneno=$this->sismodel->get_listspfic1('employee_master','emp_phone','emp_id',$record->sre_empid)->emp_phone; 
                                    $emailid=$this->sismodel->get_listspfic1('employee_master','emp_secndemail','emp_id',$record->sre_empid)->emp_secndemail;
                                    $eadhar=$this->sismodel->get_listspfic1('employee_master','emp_aadhaar_no','emp_id',$record->sre_empid)->emp_aadhaar_no; 
                                    echo "<b>Contact no:</b>".$phoneno ."<br/>"."<b>EmailId:</b>".$emailid."<br/>"."<b>e-Aadhar no:</b>".$this->sismodel->mask($eadhar,null,strlen($eadhar)-4);
                                ?>
                            </td>
                        </tr>
                    <?php }; ?>
                <?php else : ?>
                    <td colspan= "13" align="center"> No Records found...!</td>
                <?php endif;?> 
		</tbody>
        </table>
        </div><!------scroller div------>
        <p> &nbsp; </p>
        <div align="center">  <?php $this->load->view('template/footer');?></div>
    </body>    
</html>   
