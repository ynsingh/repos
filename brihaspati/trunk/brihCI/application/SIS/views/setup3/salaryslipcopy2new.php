
<!--@filename salaryslipdcopy2.php  @author Manorama Pal(palseema30@gmail.com) -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title> </title>
	<style>
            .TFtable tr td{border:0px solid black;}	
                .table2 th,td {
                    border: 1px solid black;
                    border-collapse:separate;
                    border-spacing: 1px; 
            }
            
            /** 
            * Define the width, height, margins and position of the watermark.
            **/
            
            #watermark {
                position:   fixed;
                
                bottom:    8cm;
                left:      4cm;
                
                width:      10cm;
                height:     10cm;
                
                opacity:    .1;
            }
            
            
            .column {
                float: left;
                // width:300pt;
                //padding: 10px;
                position:absolute;
                // left:5pt;
                display:inline-block;
            //  height: 275px; /* Should be removed. Only for demonstration */
            }
        
            /* Clear floats after the columns */
            .row:after {
                content: "";
                display: table;
                clear: both;
            }
	</style>
    </head>
    <body style="border:1px solid black;margin-top:5px;">
        <div id="watermark">
        <img src="uploads/logo/logowatermark.jpg" height="100%" width="100%" />
        </div>
        <?php// $tcase="from";$tcase="transit"; $tcase="to";
        //if($tcase =="from" || $tcase =="transit" || $tcase =="to"){?>
	<div >
	<img src="uploads/logo/logotanuvas.jpeg" alt="logo" style="width:100%;height:70px;"><br/>
	<table style="width:100%;margin-top:10px;">
            <thead  style="background-color:#38B0DE;color:white;font-size:18px;"><tr><td colspan="8"></td></tr></thead>
            <tr>
                <th>
            <center style="font-size:9;">Salary Statement for the Month  <b><?php echo $month.", ".$year;?></b></center>
                </th>
            </tr>
        </table>
        </div>
       
        <div >
        <?php //$tcase="from";$tcase="transit"; $tcase="to";
            $empname=$this->sismodel->get_listspfic1('employee_master','emp_name','emp_id',$empid)->emp_name;
            $empcode=$this->sismodel->get_listspfic1('employee_master','emp_code','emp_id',$empid)->emp_code;
            $doj=$this->sismodel->get_listspfic1('employee_master','emp_doj','emp_id',$empid)->emp_doj;
            $dor= $this->sismodel->get_listspfic1('employee_master','emp_dor','emp_id',$empid)->emp_dor;
            $panno=$this->sismodel->get_listspfic1('employee_master','emp_pan_no','emp_id',$empid)->emp_pan_no;
            $dob=$this->sismodel->get_listspfic1('employee_master','emp_dob','emp_id',$empid)->emp_dob;
            $society=$this->sismodel->get_listspfic1('employee_master_support','ems_society','ems_empid',$empid)->ems_society;
        ?>
        <?php $tcase="from";if($tcase =="from"){ ;?>
        <table style="width:100%;" class="TFtable">
             <?php 
                   
                    $selsaldata ="sallt_scid,sallt_uoid,sallt_deptid,sallt_desigid,sallt_sapost,sallt_ddoid,sallt_schemeid,sallt_payscaleid,"
                        . "sallt_bankaccno, sallt_worktype, sallt_emptype,sallt_group, sallt_totalincome,sallt_totaldeduction,sallt_netsalary";
                    $whdata = array('sallt_empid' =>$empid,'sallt_month' =>$month,'sallt_year' =>$year,'sallt_type'=>"from");
                    $spec_data= $this->sismodel->get_orderlistspficemore('salary_lt',$selsaldata,$whdata,'');
                               
                    $empname=$this->sismodel->get_listspfic1('employee_master','emp_name','emp_id',$empid)->emp_name;
                    $empcode=$this->sismodel->get_listspfic1('employee_master','emp_code','emp_id',$empid)->emp_code;
                    $scid=$spec_data[0]->sallt_scid;
                    $scname=$this->commodel->get_listspfic1('study_center','sc_name','sc_id',$scid)->sc_name;
                    $uocid=$spec_data[0]->sallt_uoid;
                    $uoname=$this->lgnmodel->get_listspfic1('authorities','name','id',$uocid)->name;
                    $deptid=$spec_data[0]->sallt_deptid;
                    $deptname=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$deptid)->dept_name;
                    $schmid=$spec_data[0]->sallt_schemeid;
                    $schmname=$this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$schmid)->sd_name;
                    $ddoid=$spec_data[0]->sallt_ddoid;
                    $ddoname=$this->sismodel->get_listspfic1('ddo','ddo_name','ddo_id',$ddoid)->ddo_name; 
                    $degid=$spec_data[0]->sallt_desigid;
                    $designame=$this->commodel->get_listspfic1('designation','desig_name','desig_id',$degid)->desig_name;
                    $pbid=$spec_data[0]->sallt_payscaleid;
                    $payband=$this->sismodel->get_listspfic1('salary_grade_master','sgm_name','sgm_id',$pbid)->sgm_name;
                    $pay_max=$this->sismodel->get_listspfic1('salary_grade_master','sgm_max','sgm_id',$pbid)->sgm_max;
                    $pay_min=$this->sismodel->get_listspfic1('salary_grade_master','sgm_min','sgm_id',$pbid)->sgm_min;
                    $gardepay=$this->sismodel->get_listspfic1('salary_grade_master','sgm_gradepay','sgm_id',$pbid)->sgm_gradepay;
                    $paycomm=$this->sismodel->get_listspfic1('employee_master','emp_paycomm','emp_id',$empid)->emp_paycomm;
                    $payscale=$payband."(".$pay_min."-".$pay_max.")".$gardepay;
                    $bkacc=$spec_data[0]->sallt_bankaccno;
                    $wtype=$spec_data[0]->sallt_worktype;
                    $emptype=$spec_data[0]->sallt_emptype;
                    $group=$spec_data[0]->sallt_group;
                    $sapost=$spec_data[0]->sallt_sapost;
                                  
                    echo "<tr><td style=font-size:9;><b> Name:</b></td><td style=font-size:9;>".$empname."</td>";
                    echo "<td style=font-size:9;> <b>PF.NO:</b></td><td style=font-size:9;>".$empcode."</td>";
                    echo "<td style=font-size:9;> <b>Month:</b></td><td style=font-size:9;>".strtoupper($month).' - '.$year."</td></tr>";
                   
                    echo "<tr><td style=font-size:9;> <b>Campus:</b></td><td style=font-size:9;>".$scname."</td>";
                    echo "<td style=font-size:9;> <b>UO:</b></td><td style=font-size:9;>".$uoname."</td>";
                    echo "<td style=font-size:9;> <b>Department:</b></td><td style=font-size:9;>".$deptname."</td></tr>";
                    
                    echo "<tr><td style=font-size:9;> <b>Scheme:</b></td><td style=font-size:9;>".$schmname."</td>";
                    echo "<td style=font-size:9;> <b>DDO:</b></td><td style=font-size:9;>".$ddoname."</td>";
                    echo "<td style=font-size:9;> <b>Designation:</b></td><td style=font-size:9;>".$designame."</td></tr>";
                    
                    echo "<tr><td style=font-size:9;> <b>Working Type:</b></td><td style=font-size:9;>".$wtype."</td>";
                    echo "<td style=font-size:9;> <b>Employee type:</b></td><td style=font-size:9;>".$emptype."</td>";
                    echo "<td style=font-size:9;> <b>Group:</b></td><td style=font-size:9;>".$group."</td></tr>";
                    
                    echo "<tr><td style=font-size:9;> <b>Shown Against Post:</b></td><td style=font-size:9;>".$sapost."</td>";
                    echo "<td style=font-size:9;> <b>Pay Scale:</b></td><td style=font-size:9;>".$payscale."</td>";
                    echo "<td style=font-size:9;> <b>Pay Comm:</b></td><td style=font-size:9;>".$paycomm."</td></tr>";
                    
                    echo "<tr><td style=font-size:9;> <b>DOB:</b></td><td style=font-size:9;>".date('d-m-Y',strtotime($dob))."</td>";
                    echo "<td style=font-size:9;> <b>DOJ:</b></td><td style=font-size:9;>".date('d-m-Y',strtotime($doj))."</td>";
                    echo "<td style=font-size:9;> <b>DOR:</b></td><td style=font-size:9;>".date('d-m-Y',strtotime($dor))."</td></tr>";
                    
                    echo "<tr><td style=font-size:9;> <b>Society Name:</b></td><td style=font-size:9;>".$society."</td>";
                    echo "<td style=font-size:9;> <b>Bank A/c:</b></td><td style=font-size:9;>".$bkacc."</td>";
                    echo "<td style=font-size:9;> <b>PAN:</b></td><td style=font-size:9;>".$panno."</td></tr>";
                    
                    echo "<tr><td style=font-size:9;> <b>Days Present:</b></td><td style=font-size:9;>"."0"."</td>";
                    echo "<td style=font-size:9;> <b>Paid Leaves:</b></td><td style=font-size:9;>"."0"."</td>";
                    echo "<td style=font-size:9;> <b>Leave Without Pay:</b></td><td style=font-size:9;>"."0"."</td></tr>";
                    
            ;?>
            <hr>
            </table>
            <!-- ************************************************* from ******************************************************* -->
           
            <div style="clear:both; position:relative;">
                <div class="column" style="left:5pt;width:33%;" >
                    <table width="100%" class="TFtable" >
                        <tr>
                            <td style="font-size:9;" width="30%"><b>Earnings</b></td>
                            <td style="font-size:9;left:20px;" width="20%"><b>Amount(in Rs.)</b></td>
                        </tr>    
                        <?php foreach($incomes as $inrecord){ ?>
                        <tr>
                            <td style="font-size:9;word-break:break-all; word-wrap:break-word;"><?php echo nl2br($inrecord->sh_name);?></td> 
                            <td style="font-size:9; left:20px;"><?php 
                                $case=$this->uri->segment(6);
                                $selsaldata ="saldlt_shamount";
                                $whdata = array('saldlt_sheadid'=>$inrecord->sh_id,'saldlt_empid' =>$empid,'saldlt_month' =>$month,'saldlt_year' =>$year,'saldlt_type'=>"from");
                                $spec_data1= $this->sismodel->get_orderlistspficemore('salarydata_lt',$selsaldata,$whdata,'');   
                                if(!empty($spec_data1)){
                                    $headamt=$spec_data1[0]->saldlt_shamount;
                                    echo $headamt;
                                }    
                                else{
                                    echo "0";       
                                }
                          
                                ?>
                                </td>
                        </tr>
                        <?php };?>
                    </table>    
                </div>
                
                <?php 
                    $count=count($deduction);
                    $half = $count / 2; 
                   // print_r("partoooo===".$half."count==".$count);
                          
                    $part1 = array_slice($deduction, 0, $half); 
                    $part2 = array_slice($deduction, $half); 
                             
                 ?>
                
                <div class="column" style="margin-left:180pt;width:33%;" >
                    <table width="100%" class="TFtable"> 
                        <tr> 
                            <td style="font-size:9;" width="30%"><b>Deductions</b></td>
                            <td style="font-size:9;" width="20%"><b>Amount(in Rs.)</b></td> 
                        </tr> 
                        <?php foreach($part1 as $dedrecord){ ?>
                        <tr>
                            <td style="font-size:9;word-break:break-all; word-wrap:break-word;"><?php echo nl2br($dedrecord->sh_name);?></td> 
                            <td style="font-size:9;"><?php 
                                $selsaldata ="saldlt_shamount";
                                $whdata = array('saldlt_sheadid'=>$dedrecord->sh_id,'saldlt_empid' =>$empid,'saldlt_month' =>$month,'saldlt_year' =>$year,'saldlt_type'=>"from");
                                $spec_data2= $this->sismodel->get_orderlistspficemore('salarydata_lt',$selsaldata,$whdata,'');   
                                if(!empty($spec_data2)){
                                    $headamt=$spec_data2[0]->saldlt_shamount;
                                    echo $headamt;
                                }    
                                else{
                                    echo "0";       
                                }
                              
                                ?>
                            </td>
                        </tr>
                        <?php };?>
                       
                    </table>     
                </div>
                <div class="column" style="margin-left:350pt;width:33%;" >
                  
                   <table width="100%" class="TFtable"> 
                        <tr> 
                            <td style="font-size:9;" width="30%"><b>Deductions</b></td>
                            <td style="font-size:9;" width="20%"><b>Amount(in Rs.)</b></td> 
                        </tr> 
                        <?php foreach($part2 as $dedrecord1){?>
                        <tr>
                            <td style="font-size:9;word-break:break-all; word-wrap:break-word;"><?php echo nl2br($dedrecord1->sh_name) ;?></td> 
                            <td style="font-size:9;"><?php 
                                $selsaldata ="saldlt_shamount";
                                $whdata = array('saldlt_sheadid'=>$dedrecord1->sh_id,'saldlt_empid' =>$empid,'saldlt_month' =>$month,'saldlt_year' =>$year,'saldlt_type'=>"from");
                                $spec_data2= $this->sismodel->get_orderlistspficemore('salarydata_lt',$selsaldata,$whdata,'');   
                                if(!empty($spec_data2)){
                                    $headamt=$spec_data2[0]->saldlt_shamount;
                                    echo $headamt;
                                }    
                                else{
                                    echo "0";       
                                }
                              
                                ?>
                            </td>
                        </tr>
                        <?php };?>
                        
                        <?php
                        /*$licprdpli = array(
                            "LIC1" => "LIC1", "LIC2" => "LIC2", "LIC3" => "LIC3", "LIC4" => "LIC4",
                            "LIC5" => "LIC5", "PRD1" => "PRD1", "PRD2" => "PRD2", "PRD3" => "PRD3",
                            "PLI1" => "PLI1", "PLI2" => "PLI2",);?>
                        
                        <?php foreach ($licprdpli as $lpdpi) {?>
                            <tr><td style="font-size:9;"><?php echo $lpdpi ?></td>
                            
                            <td style="font-size:9;">
                                <?php 
                                
                                    $selsaldata ="saldlt_shamount";
                                    $whdata = array('saldlt_sheadid'=>$lpdpi,'saldlt_empid' =>$empid,'saldlt_month' =>$month,'saldlt_year' =>$year,'saldlt_type'=>"transit");
                                    $spec_data3= $this->sismodel->get_orderlistspficemore('salarydata_lt',$selsaldata,$whdata,'');   
                                    if(!empty($spec_data3)){
                                        $headamt=$spec_data3[0]->saldlt_shamount;
                                        echo $headamt;
                                    }    
                                    else{
                                        echo "0";       
                                    }
                                    
                               
                                ?>
                            </td></tr>       
                          <?php }*/ ;?>
                    </table>    
                </div>
              
            </div>
            
            <div style="position: fixed; left: 5px; bottom:100px; right: 0px; height: 70px;">
                <hr>
                <table width="100%" class="TFtable">
                    
                    <tr>
                        <?php
                                                          
                                if(!empty($spec_data)){
                                    $grosspay=$spec_data[0]->sallt_totalincome;  
                                    $totalded=$spec_data[0]->sallt_totaldeduction;
                                    $netpay=$spec_data[0]->sallt_netsalary;
                                }
                                else{
                                    $grosspay=0; 
                                    $totalded=0;
                                    $netpay=0;       
                                }
                            
                           //echo "===".$grosspay."===".$totalded."==".$netpay; 
                        ;?>
                        
                        <td style="font-size:9;" width="30%"><b>Gross Pay</b></td>
                        <td style="font-size:9;" width="20%"><b><?php echo $grosspay;?></b></td>
                        <td style="font-size:9;" width="30%"><b>Total Deduction:</b></td>
                        <td style="font-size:9;" width="20%"><b><?php echo $totalded;?></b></td>
                        <td style="font-size:9;" width="30%"><b>Net Pay:</b></td>
                        <td style="font-size:9;" width="20%"><b><?php echo $netpay;?></b></td>
                        <hr>
                    </tr>    
                   
                </table>     
            </div>
            <div style="position: fixed; left: 5px; bottom:50px; right: 0px; height: 30px;">
             <p>As it is computer generated slip, signature is not required. </p>  
            </div>
            <?php } ;?>
            <!-- ****************************************************************** from end *********************************** -->   
            </div>
    </body>
    
    
    <body>
        <div >
	<img src="uploads/logo/logotanuvas.jpeg" alt="logo" style="width:100%;height:70px;"><br/>
	<table style="width:100%;margin-top:10px;">
            <thead  style="background-color:#38B0DE;color:white;font-size:18px;"><tr><td colspan="8"></td></tr></thead>
            <tr>
                <th>
            <center style="font-size:9;">Salary Statement for the Month  <b><?php echo $month.", ".$year;?></b></center>
                </th>
            </tr>
        </table>
        </div>
        
        
        <div> 
            
            <!-- *************************************************transit case******************************************************* -->
            <?php $tcase="transit";if($tcase =="transit"){ ;?>
            <table style="width:100%;" class="TFtable">
             <?php 
                   
                    $selsaldata ="sallt_scid,sallt_uoid,sallt_deptid,sallt_desigid,sallt_sapost,sallt_ddoid,sallt_schemeid,sallt_payscaleid,"
                        . "sallt_bankaccno, sallt_worktype, sallt_emptype,sallt_group, sallt_totalincome,sallt_totaldeduction,sallt_netsalary";
                    $whdata = array('sallt_empid' =>$empid,'sallt_month' =>$month,'sallt_year' =>$year,'sallt_type'=>"transit");
                    $spec_data= $this->sismodel->get_orderlistspficemore('salary_lt',$selsaldata,$whdata,'');
                               
                    $empname=$this->sismodel->get_listspfic1('employee_master','emp_name','emp_id',$empid)->emp_name;
                    $empcode=$this->sismodel->get_listspfic1('employee_master','emp_code','emp_id',$empid)->emp_code;
                    $scid=$spec_data[0]->sallt_scid;
                    $scname=$this->commodel->get_listspfic1('study_center','sc_name','sc_id',$scid)->sc_name;
                    $uocid=$spec_data[0]->sallt_uoid;
                    $uoname=$this->lgnmodel->get_listspfic1('authorities','name','id',$uocid)->name;
                    $deptid=$spec_data[0]->sallt_deptid;
                    $deptname=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$deptid)->dept_name;
                    $schmid=$spec_data[0]->sallt_schemeid;
                    $schmname=$this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$schmid)->sd_name;
                    $ddoid=$spec_data[0]->sallt_ddoid;
                    $ddoname=$this->sismodel->get_listspfic1('ddo','ddo_name','ddo_id',$ddoid)->ddo_name; 
                    $degid=$spec_data[0]->sallt_desigid;
                    $designame=$this->commodel->get_listspfic1('designation','desig_name','desig_id',$degid)->desig_name;
                    $pbid=$spec_data[0]->sallt_payscaleid;
                    $payband=$this->sismodel->get_listspfic1('salary_grade_master','sgm_name','sgm_id',$pbid)->sgm_name;
                    $pay_max=$this->sismodel->get_listspfic1('salary_grade_master','sgm_max','sgm_id',$pbid)->sgm_max;
                    $pay_min=$this->sismodel->get_listspfic1('salary_grade_master','sgm_min','sgm_id',$pbid)->sgm_min;
                    $gardepay=$this->sismodel->get_listspfic1('salary_grade_master','sgm_gradepay','sgm_id',$pbid)->sgm_gradepay;
                    $paycomm=$this->sismodel->get_listspfic1('employee_master','emp_paycomm','emp_id',$empid)->emp_paycomm;
                    $payscale=$payband."(".$pay_min."-".$pay_max.")".$gardepay;
                    $bkacc=$spec_data[0]->sallt_bankaccno;
                    $doj=$this->sismodel->get_listspfic1('employee_master','emp_doj','emp_id',$empid)->emp_doj;
                    $dor= $this->sismodel->get_listspfic1('employee_master','emp_dor','emp_id',$empid)->emp_dor;  
                    $wtype=$spec_data[0]->sallt_worktype;
                    $emptype=$spec_data[0]->sallt_emptype;
                    $group=$spec_data[0]->sallt_group;
                    $panno=$this->sismodel->get_listspfic1('employee_master','emp_pan_no','emp_id',$empid)->emp_pan_no;
                    $dob=$this->sismodel->get_listspfic1('employee_master','emp_dob','emp_id',$empid)->emp_dob;
                    $sapost=$spec_data[0]->sallt_sapost;
                    $society=$this->sismodel->get_listspfic1('employee_master_support','ems_society','ems_empid',$empid)->ems_society;
               
                    echo "<tr><td style=font-size:9;><b> Name:</b></td><td style=font-size:9;>".$empname."</td>";
                    echo "<td style=font-size:9;> <b>PF.NO:</b></td><td style=font-size:9;>".$empcode."</td>";
                    echo "<td style=font-size:9;> <b>Month:</b></td><td style=font-size:9;>".strtoupper($month).' - '.$year."</td></tr>";
                   
                    echo "<tr><td style=font-size:9;> <b>Campus:</b></td><td style=font-size:9;>".$scname."</td>";
                    echo "<td style=font-size:9;> <b>UO:</b></td><td style=font-size:9;>".$uoname."</td>";
                    echo "<td style=font-size:9;> <b>Department:</b></td><td style=font-size:9;>".$deptname."</td></tr>";
                    
                    echo "<tr><td style=font-size:9;> <b>Scheme:</b></td><td style=font-size:9;>".$schmname."</td>";
                    echo "<td style=font-size:9;> <b>DDO:</b></td><td style=font-size:9;>".$ddoname."</td>";
                    echo "<td style=font-size:9;> <b>Designation:</b></td><td style=font-size:9;>".$designame."</td></tr>";
                    
                    echo "<tr><td style=font-size:9;> <b>Working Type:</b></td><td style=font-size:9;>".$wtype."</td>";
                    echo "<td style=font-size:9;> <b>Employee type:</b></td><td style=font-size:9;>".$emptype."</td>";
                    echo "<td style=font-size:9;> <b>Group:</b></td><td style=font-size:9;>".$group."</td></tr>";
                    
                    echo "<tr><td style=font-size:9;> <b>Shown Against Post:</b></td><td style=font-size:9;>".$sapost."</td>";
                    echo "<td style=font-size:9;> <b>Pay Scale:</b></td><td style=font-size:9;>".$payscale."</td>";
                    echo "<td style=font-size:9;> <b>Pay Comm:</b></td><td style=font-size:9;>".$paycomm."</td></tr>";
                    
                    echo "<tr><td style=font-size:9;> <b>DOB:</b></td><td style=font-size:9;>".date('d-m-Y',strtotime($dob))."</td>";
                    echo "<td style=font-size:9;> <b>DOJ:</b></td><td style=font-size:9;>".date('d-m-Y',strtotime($doj))."</td>";
                    echo "<td style=font-size:9;> <b>DOR:</b></td><td style=font-size:9;>".date('d-m-Y',strtotime($dor))."</td></tr>";
                    
                    echo "<tr><td style=font-size:9;> <b>Society Name:</b></td><td style=font-size:9;>".$society."</td>";
                    echo "<td style=font-size:9;> <b>Bank A/c:</b></td><td style=font-size:9;>".$bkacc."</td>";
                    echo "<td style=font-size:9;> <b>PAN:</b></td><td style=font-size:9;>".$panno."</td></tr>";
                    
                    echo "<tr><td style=font-size:9;> <b>Days Present:</b></td><td style=font-size:9;>"."0"."</td>";
                    echo "<td style=font-size:9;> <b>Paid Leaves:</b></td><td style=font-size:9;>"."0"."</td>";
                    echo "<td style=font-size:9;> <b>Leave Without Pay:</b></td><td style=font-size:9;>"."0"."</td></tr>";
                    
            ;?>
            <hr>
            </table>
            <div style="clear:both; position:relative;">
                <div class="column" style="left:5pt; width:33%;" >
                    <table width="100%" class="TFtable" >
                        <tr>
                            <td style="font-size:9;" width="30%"><b>Earnings</b></td>
                            <td style="font-size:9;left:20px;" width="20%"><b>Amount(in Rs.)</b></td>
                        </tr>    
                        <?php foreach($incomes as $inrecord){ ?>
                        <tr>
                            <td style="font-size:9;word-break:break-all; word-wrap:break-word;"><?php echo  nl2br($inrecord->sh_name);?></td> 
                            <td style="font-size:9; left:20px;"><?php 
                                $selsaldata ="saldlt_shamount";
                                $whdata = array('saldlt_sheadid'=>$inrecord->sh_id,'saldlt_empid' =>$empid,'saldlt_month' =>$month,'saldlt_year' =>$year,'saldlt_type'=>"transit");  
                                $spec_data1= $this->sismodel->get_orderlistspficemore('salarydata_lt',$selsaldata,$whdata,'');   
                                if(!empty($spec_data1)){
                                    $headamt=$spec_data1[0]->saldlt_shamount;
                                    echo $headamt;
                                }    
                                else{
                                    echo "0";       
                                }
                                                        
                                   
                                ?>
                                </td>
                        </tr>
                        <?php };?>
                    </table>    
                </div>
                 <?php 
                    $count=count($deduction);
                    $half = $count / 2; 
                   // print_r("partoooo===".$half."count==".$count);
                          
                    $part1 = array_slice($deduction, 0, $half); 
                    $part2 = array_slice($deduction, $half); 
                             
                 ?>             
                <div class="column" style="margin-left:180pt;width:33%;" >
                    <table width="100%" class="TFtable"> 
                        <tr> 
                            <td style="font-size:9;" width="30%"><b>Deductions</b></td>
                            <td style="font-size:9;" width="20%"><b>Amount(in Rs.)</b></td> 
                        </tr> 
                        <?php foreach($part1 as $dedrecord){ ?>
                        <tr>
                            <td style="font-size:9;word-break:break-all; word-wrap:break-word;"><?php echo nl2br($dedrecord->sh_name);?></td> 
                            <td style="font-size:9;"><?php 
                                $selsaldata ="saldlt_shamount";
                                $whdata = array('saldlt_sheadid'=>$dedrecord->sh_id,'saldlt_empid' =>$empid,'saldlt_month' =>$month,'saldlt_year' =>$year,'saldlt_type'=>"transit");
                                $spec_data2= $this->sismodel->get_orderlistspficemore('salarydata_lt',$selsaldata,$whdata,'');   
                                if(!empty($spec_data2)){
                                    $headamt=$spec_data2[0]->saldlt_shamount;
                                    echo $headamt;
                                }    
                                else{
                                    echo "0";       
                                }
                                    
                                ?>
                            </td>
                        </tr>
                        <?php };?>
                        
                    </table>   
                </div>        
                <div class="column" style="margin-left:350pt;width:33%;">
                    <table width="100%" class="TFtable"> 
                        <tr> 
                            <td style="font-size:9;" width="30%"><b>Deductions</b></td>
                            <td style="font-size:9;" width="20%"><b>Amount(in Rs.)</b></td> 
                        </tr> 
                        <?php foreach($part2 as $dedrecord1){ ?>
                        <tr>
                            <td style="font-size:9;word-break:break-all; word-wrap:break-word;"><?php echo nl2br($dedrecord1->sh_name);?></td> 
                            <td style="font-size:9;"><?php 
                                $selsaldata ="saldlt_shamount";
                                $whdata = array('saldlt_sheadid'=>$dedrecord1->sh_id,'saldlt_empid' =>$empid,'saldlt_month' =>$month,'saldlt_year' =>$year,'saldlt_type'=>"transit");
                                $spec_data2= $this->sismodel->get_orderlistspficemore('salarydata_lt',$selsaldata,$whdata,'');   
                                if(!empty($spec_data2)){
                                    $headamt=$spec_data2[0]->saldlt_shamount;
                                    echo $headamt;
                                }    
                                else{
                                    echo "0";       
                                }
                                    
                                ?>
                            </td>
                        </tr>
                        <?php };?>
                        <?php 
                        /*$licprdpli = array(
                            "LIC1" => "LIC1", "LIC2" => "LIC2", "LIC3" => "LIC3", "LIC4" => "LIC4",
                            "LIC5" => "LIC5", "PRD1" => "PRD1", "PRD2" => "PRD2", "PRD3" => "PRD3",
                            "PLI1" => "PLI1", "PLI2" => "PLI2",);?>
                        
                        <?php foreach ($licprdpli as $lpdpi) {?>
                            <tr><td style="font-size:9;"><?php echo $lpdpi ?></td>
                            <td style="font-size:9;">
                                <?php 
                                
                                    $selsaldata ="saldlt_shamount";
                                    $whdata = array('saldlt_sheadid'=>$lpdpi,'saldlt_empid' =>$empid,'saldlt_month' =>$month,'saldlt_year' =>$year,'saldlt_type'=>"transit");
                                    $spec_data3= $this->sismodel->get_orderlistspficemore('salarydata_lt',$selsaldata,$whdata,'');   
                                    if(!empty($spec_data3)){
                                        $headamt=$spec_data3[0]->saldlt_shamount;
                                        echo $headamt;
                                    }    
                                    else{
                                        echo "0";       
                                    }
                                    
                               
                                ?>
                            </td></tr>   
                                   
                          <?php }*/ ;?>
                    </table>     
                </div>
              
            </div>
            <div style="position: fixed; left: 5px; bottom:100px; right: 0px; height: 70px;">
                <hr>
                <table width="100%" class="TFtable">
                    
                    <tr>
                        <?php
                                if(!empty($spec_data)){
                                    $grosspay=$spec_data[0]->sallt_totalincome;  
                                    $totalded=$spec_data[0]->sallt_totaldeduction;
                                    $netpay=$spec_data[0]->sallt_netsalary;
                                }
                                else{
                                    $grosspay=0; 
                                    $totalded=0;
                                    $netpay=0;       
                                }
                            
                           //echo "===".$grosspay."===".$totalded."==".$netpay; 
                        ;?>
                        
                        <td style="font-size:9;" width="30%"><b>Gross Pay</b></td>
                        <td style="font-size:9;" width="20%"><b><?php echo $grosspay;?></b></td>
                        <td style="font-size:9;" width="30%"><b>Total Deduction:</b></td>
                        <td style="font-size:9;" width="20%"><b><?php echo $totalded;?></b></td>
                        <td style="font-size:9;" width="30%"><b>Net Pay:</b></td>
                        <td style="font-size:9;" width="20%"><b><?php echo $netpay;?></b></td>
                        <hr>
                    </tr>    
                   
                </table>     
            </div>
            
            <div style="position: fixed; left: 5px; bottom:50px; right: 0px; height: 30px;">
                
             <p>As it is computer generated slip, signature is not required. </p>  
            </div>
            <?php } ;?>
            </div>
        
        </body>
        
        <body>
            <div >
	<img src="uploads/logo/logotanuvas.jpeg" alt="logo" style="width:100%;height:70px;"><br/>
	<table style="width:100%;margin-top:10px;">
            <thead  style="background-color:#38B0DE;color:white;font-size:18px;"><tr><td colspan="8"></td></tr></thead>
            <tr>
                <th>
            <center style="font-size:9;">Salary Statement for the Month  <b><?php echo $month.", ".$year;?></b></center>
                </th>
            </tr>
        </table>
        </div>
            <div> 
            <!-- *************************************************to******************************************************* -->
            <?php $tcase="to"; if($tcase =="to"){ ;?>
            <table style="width:100%;" class="TFtable">
             <?php 
                   
                    $selsaldata ="sallt_scid,sallt_uoid,sallt_deptid,sallt_desigid,sallt_sapost,sallt_ddoid,sallt_schemeid,sallt_payscaleid,"
                        . "sallt_bankaccno, sallt_worktype, sallt_emptype,sallt_group, sallt_totalincome,sallt_totaldeduction,sallt_netsalary";
                    $whdata = array('sallt_empid' =>$empid,'sallt_month' =>$month,'sallt_year' =>$year,'sallt_type'=>"to");
                    $spec_data= $this->sismodel->get_orderlistspficemore('salary_lt',$selsaldata,$whdata,'');
                               
                    $empname=$this->sismodel->get_listspfic1('employee_master','emp_name','emp_id',$empid)->emp_name;
                    $empcode=$this->sismodel->get_listspfic1('employee_master','emp_code','emp_id',$empid)->emp_code;
                    $scid=$spec_data[0]->sallt_scid;
                    $scname=$this->commodel->get_listspfic1('study_center','sc_name','sc_id',$scid)->sc_name;
                    $uocid=$spec_data[0]->sallt_uoid;
                    $uoname=$this->lgnmodel->get_listspfic1('authorities','name','id',$uocid)->name;
                    $deptid=$spec_data[0]->sallt_deptid;
                    $deptname=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$deptid)->dept_name;
                    $schmid=$spec_data[0]->sallt_schemeid;
                    $schmname=$this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$schmid)->sd_name;
                    $ddoid=$spec_data[0]->sallt_ddoid;
                    $ddoname=$this->sismodel->get_listspfic1('ddo','ddo_name','ddo_id',$ddoid)->ddo_name; 
                    $degid=$spec_data[0]->sallt_desigid;
                    $designame=$this->commodel->get_listspfic1('designation','desig_name','desig_id',$degid)->desig_name;
                    $pbid=$spec_data[0]->sallt_payscaleid;
                    $payband=$this->sismodel->get_listspfic1('salary_grade_master','sgm_name','sgm_id',$pbid)->sgm_name;
                    $pay_max=$this->sismodel->get_listspfic1('salary_grade_master','sgm_max','sgm_id',$pbid)->sgm_max;
                    $pay_min=$this->sismodel->get_listspfic1('salary_grade_master','sgm_min','sgm_id',$pbid)->sgm_min;
                    $gardepay=$this->sismodel->get_listspfic1('salary_grade_master','sgm_gradepay','sgm_id',$pbid)->sgm_gradepay;
                    $paycomm=$this->sismodel->get_listspfic1('employee_master','emp_paycomm','emp_id',$empid)->emp_paycomm;
                    $payscale=$payband."(".$pay_min."-".$pay_max.")".$gardepay;
                    $bkacc=$spec_data[0]->sallt_bankaccno;
                    $doj=$this->sismodel->get_listspfic1('employee_master','emp_doj','emp_id',$empid)->emp_doj;
                    $dor= $this->sismodel->get_listspfic1('employee_master','emp_dor','emp_id',$empid)->emp_dor;  
                    $wtype=$spec_data[0]->sallt_worktype;
                    $emptype=$spec_data[0]->sallt_emptype;
                    $group=$spec_data[0]->sallt_group;
                    $panno=$this->sismodel->get_listspfic1('employee_master','emp_pan_no','emp_id',$empid)->emp_pan_no;
                    $dob=$this->sismodel->get_listspfic1('employee_master','emp_dob','emp_id',$empid)->emp_dob;
                    $sapost=$spec_data[0]->sallt_sapost;
                    $society=$this->sismodel->get_listspfic1('employee_master_support','ems_society','ems_empid',$empid)->ems_society;
               
                    echo "<tr><td style=font-size:9;><b> Name:</b></td><td style=font-size:9;>".$empname."</td>";
                    echo "<td style=font-size:9;> <b>PF.NO:</b></td><td style=font-size:9;>".$empcode."</td>";
                    echo "<td style=font-size:9;> <b>Month:</b></td><td style=font-size:9;>".strtoupper($month).' - '.$year."</td></tr>";
                   
                    echo "<tr><td style=font-size:9;> <b>Campus:</b></td><td style=font-size:9;>".$scname."</td>";
                    echo "<td style=font-size:9;> <b>UO:</b></td><td style=font-size:9;>".$uoname."</td>";
                    echo "<td style=font-size:9;> <b>Department:</b></td><td style=font-size:9;>".$deptname."</td></tr>";
                    
                    echo "<tr><td style=font-size:9;> <b>Scheme:</b></td><td style=font-size:9;>".$schmname."</td>";
                    echo "<td style=font-size:9;> <b>DDO:</b></td><td style=font-size:9;>".$ddoname."</td>";
                    echo "<td style=font-size:9;> <b>Designation:</b></td><td style=font-size:9;>".$designame."</td></tr>";
                    
                    echo "<tr><td style=font-size:9;> <b>Working Type:</b></td><td style=font-size:9;>".$wtype."</td>";
                    echo "<td style=font-size:9;> <b>Employee type:</b></td><td style=font-size:9;>".$emptype."</td>";
                    echo "<td style=font-size:9;> <b>Group:</b></td><td style=font-size:9;>".$group."</td></tr>";
                    
                    echo "<tr><td style=font-size:9;> <b>Shown Against Post:</b></td><td style=font-size:9;>".$sapost."</td>";
                    echo "<td style=font-size:9;> <b>Pay Scale:</b></td><td style=font-size:9;>".$payscale."</td>";
                    echo "<td style=font-size:9;> <b>Pay Comm:</b></td><td style=font-size:9;>".$paycomm."</td></tr>";
                    
                    echo "<tr><td style=font-size:9;> <b>DOB:</b></td><td style=font-size:9;>".date('d-m-Y',strtotime($dob))."</td>";
                    echo "<td style=font-size:9;> <b>DOJ:</b></td><td style=font-size:9;>".date('d-m-Y',strtotime($doj))."</td>";
                    echo "<td style=font-size:9;> <b>DOR:</b></td><td style=font-size:9;>".date('d-m-Y',strtotime($dor))."</td></tr>";
                    
                    echo "<tr><td style=font-size:9;> <b>Society Name:</b></td><td style=font-size:9;>".$society."</td>";
                    echo "<td style=font-size:9;> <b>Bank A/c:</b></td><td style=font-size:9;>".$bkacc."</td>";
                    echo "<td style=font-size:9;> <b>PAN:</b></td><td style=font-size:9;>".$panno."</td></tr>";
                    
                    echo "<tr><td style=font-size:9;> <b>Days Present:</b></td><td style=font-size:9;>"."0"."</td>";
                    echo "<td style=font-size:9;> <b>Paid Leaves:</b></td><td style=font-size:9;>"."0"."</td>";
                    echo "<td style=font-size:9;> <b>Leave Without Pay:</b></td><td style=font-size:9;>"."0"."</td></tr>";
                    
            ;?>
            <hr>
            </table>
            <div style="clear:both; position:relative;">
                <div class="column"style="left:5pt;width:33%;" >
                    <table width="100%" class="TFtable" >
                        <tr>
                            <td style="font-size:9;" width="30%"><b>Earnings</b></td>
                            <td style="font-size:9;left:20px;" width="20%"><b>Amount(in Rs.)</b></td>
                        </tr>    
                        <?php foreach($incomes as $inrecord){ ?>
                        <tr>
                            <td style="font-size:9;word-break:break-all; word-wrap:break-word;""><?php echo nl2br($inrecord->sh_name);?></td> 
                            <td style="font-size:9; left:20px;"><?php 
                                $case=$this->uri->segment(6);
                                $selsaldata ="saldlt_shamount";
                                $whdata = array('saldlt_sheadid'=>$inrecord->sh_id,'saldlt_empid' =>$empid,'saldlt_month' =>$month,'saldlt_year' =>$year,'saldlt_type'=>"to");     
                                $spec_data1= $this->sismodel->get_orderlistspficemore('salarydata_lt',$selsaldata,$whdata,'');   
                                if(!empty($spec_data1)){
                                    $headamt=$spec_data1[0]->saldlt_shamount;
                                    echo $headamt;
                                }    
                                else{
                                    echo "0";       
                                }
                             
                                ?>
                                </td>
                        </tr>
                        <?php };?>
                    </table>    
                </div>
                 <?php 
                    $count=count($deduction);
                    $half = $count / 2; 
                   // print_r("partoooo===".$half."count==".$count);
                          
                    $part1 = array_slice($deduction, 0, $half); 
                    $part2 = array_slice($deduction, $half); 
                             
                 ?>      
                <div class="column" style="margin-left:180pt;width:33%;" >
                    <table width="100%" class="TFtable"> 
                        <tr> 
                            <td style="font-size:9;" width="30%"><b>Deductions</b></td>
                            <td style="font-size:9;" width="20%"><b>Amount(in Rs.)</b></td> 
                        </tr> 
                        <?php foreach($part1 as $dedrecord){ ?>
                        <tr>
                            <td style="font-size:9;word-break:break-all; word-wrap:break-word;"><?php echo nl2br($dedrecord->sh_name);?></td> 
                            <td style="font-size:9;"><?php 
                               
                                $selsaldata ="saldlt_shamount";
                                $whdata = array('saldlt_sheadid'=>$dedrecord->sh_id,'saldlt_empid' =>$empid,'saldlt_month' =>$month,'saldlt_year' =>$year,'saldlt_type'=>"to");
                                $spec_data2= $this->sismodel->get_orderlistspficemore('salarydata_lt',$selsaldata,$whdata,'');   
                                if(!empty($spec_data2)){
                                    $headamt=$spec_data2[0]->saldlt_shamount;
                                    echo $headamt;
                                }    
                                else{
                                    echo "0";       
                                }
                              
                                ?>
                            </td>
                        </tr>
                        
                        <?php };?>
                        
                    </table>
                </div>
                <div class="column" style="margin-left:350pt;width:33%;" >
                    <table width="100%" class="TFtable"> 
                        <tr> 
                            <td style="font-size:9;" width="30%"><b>Deductions</b></td>
                            <td style="font-size:9;" width="20%"><b>Amount(in Rs.)</b></td> 
                        </tr> 
                        <?php foreach($part2 as $dedrecord1){ ?>
                        <tr>
                            <td style="font-size:9;word-break:break-all; word-wrap:break-word;"><?php echo nl2br($dedrecord1->sh_name);?></td> 
                            <td style="font-size:9;"><?php 
                               
                                $selsaldata ="saldlt_shamount";
                                $whdata = array('saldlt_sheadid'=>$dedrecord1->sh_id,'saldlt_empid' =>$empid,'saldlt_month' =>$month,'saldlt_year' =>$year,'saldlt_type'=>"to");
                                $spec_data2= $this->sismodel->get_orderlistspficemore('salarydata_lt',$selsaldata,$whdata,'');   
                                if(!empty($spec_data2)){
                                    $headamt=$spec_data2[0]->saldlt_shamount;
                                    echo $headamt;
                                }    
                                else{
                                    echo "0";       
                                }
                              
                                ?>
                            </td>
                        </tr>
                        
                        <?php };?>
                        <?php
                        /* $licprdpli = array(
                            "LIC1" => "LIC1", "LIC2" => "LIC2", "LIC3" => "LIC3", "LIC4" => "LIC4",
                            "LIC5" => "LIC5", "PRD1" => "PRD1", "PRD2" => "PRD2", "PRD3" => "PRD3",
                            "PLI1" => "PLI1", "PLI2" => "PLI2",);?>
                        
                        <?php foreach ($licprdpli as $lpdpi) {?>
                            <tr><td style="font-size:9;"><?php echo $lpdpi ?></td>
                            <td style="font-size:9;">
                                <?php 
                                
                                    $selsaldata ="saldlt_shamount";
                                    $whdata = array('saldlt_sheadid'=>$lpdpi,'saldlt_empid' =>$empid,'saldlt_month' =>$month,'saldlt_year' =>$year,'saldlt_type'=>"to"); 
                                    $spec_data3= $this->sismodel->get_orderlistspficemore('salarydata_lt',$selsaldata,$whdata,'');   
                                    if(!empty($spec_data3)){
                                        $headamt=$spec_data3[0]->saldlt_shamount;
                                        echo $headamt;
                                    }    
                                    else{
                                        echo "0";       
                                    }
                                    
                               
                                ?>
                            </td></tr>   
                                   
                          <?php }*/ ;?>
                    </table>     
                </div>
              
            </div>
            <div style="position: fixed; left: 5px; bottom:100px; right: 0px; height: 70px;">
                <hr>
                <table width="100%" class="TFtable">
                    
                    <tr>
                        <?php
                            
                                if(!empty($spec_data)){
                                    $grosspay=$spec_data[0]->sallt_totalincome;  
                                    $totalded=$spec_data[0]->sallt_totaldeduction;
                                    $netpay=$spec_data[0]->sallt_netsalary;
                                }
                                else{
                                    $grosspay=0; 
                                    $totalded=0;
                                    $netpay=0;       
                                }
                            
                           //echo "===".$grosspay."===".$totalded."==".$netpay; 
                        ;?>
                        
                        <td style="font-size:9;" width="30%"><b>Gross Pay</b></td>
                        <td style="font-size:9;" width="20%"><b><?php echo $grosspay;?></b></td>
                        <td style="font-size:9;" width="30%"><b>Total Deduction:</b></td>
                        <td style="font-size:9;" width="20%"><b><?php echo $totalded;?></b></td>
                        <td style="font-size:9;" width="30%"><b>Net Pay:</b></td>
                        <td style="font-size:9;" width="20%"><b><?php echo $netpay;?></b></td>
                        <hr>
                    </tr>    
                   
                </table>     
            </div>
            
            <div style="position: fixed; left: 5px; bottom:50px; right: 0px; height: 30px;">
             <p>As it is computer generated slip, signature is not required. </p>  
            </div> 
            <?php } ;?>
            <!-- ******************************************************************from end *********************************** -->     
                   
        </div>
    </body>
    
</html>    

