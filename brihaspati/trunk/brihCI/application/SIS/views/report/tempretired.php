
<!--
    @author Manorama Pal(palseema30@gmail.com) pdf report
    @author Akash Rathi(akash92y@gmail.com) html part  

-->

<html>
 <body> 
<!--     <img src="uploads/logo/logotanuvas.jpeg" alt="logo" style= " width:100%;height:80px; margin-bottom:15px; " > <br/> -->
<?php $this->load->view('template/pheader'); ?>

    <div class="scroller_sub_page">
                    <table class="TFtable" id="printme" >
                        <thead><tr>
                            <th valign="top" style="font-size:9;"><b>Sr.No</b></th>
                            <th valign="top" style="font-size:9;"></th>
                            <th valign="top" style="font-size:9;word-break:break-all; word-wrap:break-word;"><b><?php echo wordwrap('Employee Name',8,"\n",TRUE);?></b></th>
                            <th valign="top" style="font-size:9;"><b>Details</b></th>
                            <th valign="top" style="font-size:9;"><b>Designation</b></th>
                            <th valign="top" style="font-size:9;"><b>Doj / Dor</b></th>
                            <!-- <th>Reason</th> -->
                            <th valign="top" style="font-size:8;word-break:break-all; word-wrap:break-word;"><b><?php echo wordwrap('Contact Details',8,"\n",TRUE);?></b></th>
                   
                        </tr></thead>
                        <tbody>
                            <?php $serial_no = 1;?>
                            <?php if( count($records) ):  ?>
                            <?php foreach($records as $record){ ?>
                            <tr>
                                <td><?php echo $serial_no++; ?></td>
                                <?php //$img=$record->emp_code;?>
                                <?php $emphoto=$this->sismodel->get_listspfic1('employee_master','emp_photoname','emp_id',$record->emp_id)->emp_photoname;
                                if(!empty($emphoto)):?>
                                    <td><p><img src="<?php echo base_url('uploads/SIS/empphoto/'.$emphoto);?>"  alt="" v:shapes="_x0000_i1025" width="78" height="94"></p></td>
                                <?php else :?>
                                <td><p><img src="<?php echo base_url('uploads/SIS/empphoto/empdemopic.png');?>"  alt="" v:shapes="_x0000_i1025" width="78" height="94"></p></td>
                                <?php endif;?>
                                <td style="font-size:9;"><?php
                                    $empname=$this->sismodel->get_listspfic1('employee_master','emp_name','emp_id',$record->emp_id)->emp_name;
				    echo anchor("report/viewfull_profile/{$record->emp_id}",$empname ,array('title' => 'View Employee Profile' , 'class' => 'red-link'));
                                //    echo $empname."<br/>" ."("."PF No:".$record->emp_code.")"."<br/>".$record->emp_email;;
                                    echo "<br/>" ."("."PF No:".$record->emp_code.")"."<br/>".$record->emp_email;;
                                ?></td>
                                <td style="font-size:9;"><?php
                            
                                    $empscid=$this->sismodel->get_listspfic1('employee_master','emp_scid','emp_id',$record->emp_id)->emp_scid;
                                    $campus=$this->commodel->get_listspfic1('study_center','sc_name','sc_id',$empscid)->sc_name;
                                    $empuoid=$this->sismodel->get_listspfic1('employee_master','emp_uocid','emp_id',$record->emp_id)->emp_uocid;
                                    $authority=$this->lgnmodel->get_listspfic1('authorities','name','id' ,$empuoid)->name;
                                    $empdept=$this->sismodel->get_listspfic1('employee_master','emp_dept_code','emp_id',$record->emp_id)->emp_dept_code;
                                    $dept=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$empdept)->dept_name;
                                    $empschm=$this->sismodel->get_listspfic1('employee_master','emp_schemeid','emp_id',$record->emp_id)->emp_schemeid;
                                    $schm=$this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$empschm)->sd_name;
                                    $schmcode=$this->sismodel->get_listspfic1('scheme_department','sd_code','sd_id',$empschm)->sd_code;
                                    $subid=$this->sismodel->get_listspfic1('employee_master','emp_specialisationid','emp_id',$record->emp_id)->emp_specialisationid;
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
                                <td style="font-size:9;"><?php
                                    $empdesig=$this->sismodel->get_listspfic1('employee_master','emp_desig_code','emp_id',$record->emp_id)->emp_desig_code;
                                    $desig=$this->commodel->get_listspfic1('designation','desig_name','desig_id',$empdesig)->desig_name; 
                                    $sap=$record->emp_post;
                                    $payband=$this->sismodel->get_listspfic1('employee_master','emp_salary_grade','emp_id',$record->emp_id)->emp_salary_grade;
                                    $pbname=$this->sismodel->get_listspfic1('salary_grade_master','sgm_name','sgm_id',$payband)->sgm_name;
                                    $pay_max=$this->sismodel->get_listspfic1('salary_grade_master','sgm_max','sgm_id',$payband)->sgm_max;
                                    $pay_min=$this->sismodel->get_listspfic1('salary_grade_master','sgm_min','sgm_id',$payband)->sgm_min;
                                    $gardepay=$this->sismodel->get_listspfic1('salary_grade_master','sgm_gradepay','sgm_id',$payband)->sgm_gradepay;
                                    echo "<b>Designation:</b>".$desig."<br/>"."<b>Shown against post:</b>".$sap."<br/>"."<b>Payband:</b>".$pbname."(".$pay_min."-".$pay_max.")".$gardepay; 
                                    ?>
                                </td>
                                <td style="font-size:9;">
                                    <?php 
                                    $doj = date('d-m-Y H:i:s',strtotime($record->emp_doj));
                                    $dor = date('d-m-Y H:i:s',strtotime($record->emp_dor));
                                    $dob=$this->sismodel->get_listspfic1('employee_master','emp_dob','emp_id',$record->emp_id)->emp_dob;
                                    $dobnew=date('d-m-Y',strtotime($dob));
                                    echo  "<b>Dob:</b>".$dobnew."<br/>". "<b>Doj:</b>".$doj . "<br/>" ."<b>Dor:</b>"."<font color=green>".$dor."</font>"   ;
                                    ?>
                                </td>
                                <!--<td><?php
                                    
                                    //echo "<b>Reason:</b>".$record->sre_reason."<br/>"."<b>Date : </b>"."<font color=red>".date('d-m-Y H:i:s',strtotime($record->sre_reasondate))."</font>";
                                ?></td> -->
                                <td style="font-size:9;">
                                    <?php   
                                    $phoneno=$this->sismodel->get_listspfic1('employee_master','emp_phone','emp_id',$record->emp_id)->emp_phone; 
                                    $emailid=$this->sismodel->get_listspfic1('employee_master','emp_secndemail','emp_id',$record->emp_id)->emp_secndemail;
                                    $eadhar=$this->sismodel->get_listspfic1('employee_master','emp_aadhaar_no','emp_id',$record->emp_id)->emp_aadhaar_no; 
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
<!--            <img src="uploads/logo/logo23.png" alt="logo" style= " width:100%;height:30px; margin-top:30px; " > <br/>-->
<?php $this->load->view('template/footer'); ?>

        </body>    
    </html>
