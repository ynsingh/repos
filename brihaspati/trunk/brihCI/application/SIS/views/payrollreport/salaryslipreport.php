<!--@name salaryslipreport.php 
    @author Manorama Pal (palseema30@gmail.com)
-->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title></title>
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
                position:fixed;
                
              //  bottom:8cm;
               // left:4cm;
                
                bottom:16cm;
                left:5cm;
                
               // width:10cm;
               // height:10cm;
               
                width:8cm;
                height:8cm;
                
                opacity:.1;
                
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
                      
            .page-break {
                display: block;
                page-break-before: always;
                size: A4 portrait;
            }

            
        </style>
    </head>
    <body style="border:1px solid black;margin-top:5px;">
        <?php 
      //  echo $cmonth= date('"F M"');die;
       // print_r($totalemp); die();
        for($i=0;$i<count($totalemp);$i+=2) {
         //   echo implode(" ",$emprecord) 
         
                // print_r($emprecord[$i]->sal_empid.$emprecord[$i+1]->sal_empid)
         //}
        ?>
        <div style="page-break-after:always;">
            
        <div id="watermark">
            <img src="uploads/logo/logowatermark.jpg" height="100%" width="100%" />
        </div>
        <div style="position:fixed;bottom:2cm;left:5cm; width:8cm;height:8cm;opacity:.1;">
            <img src="uploads/logo/logowatermark.jpg" height="100%" width="100%" />
        </div>
        <?php 
         //$countno =0;
         
        // for($i=0;$i<count($emprecord);$i+=2) {
         //   echo implode(" ",$emprecord) 
         
               //  print_r($emprecord[$i]->sal_empid.$emprecord[$i+1]->sal_empid)
         //}
      //  $i = 0;
       // while ($row = mysql_fetch_array($emprecord)) {
         //   echo"seeema". $emprecord->sal_empid;
           // die;
            
//}
       // $totalemp=json_decode(json_encode($emprecord),true);
      //  print_r($totalemp);
      //  $totalemp=(array_chunk($emprecord, 2, true));
       // foreach (array_chunk($emprecord, 2) as $group) : ?>
        <?php //foreach($group as $recordemp){
       // foreach($emprecord[$i] as $recordemp){
           
	//	print_r(count($group)); die;
      //  if($i % 2 == 0){
        //  echo "seema". $recordemp->sal_empid; 
        //  $countno +=1;
            ?>
        <div >
	<img src="uploads/logo/logotanuvas.jpeg" alt="logo" style="width:100%;height:70px;"><br/>
	<table style="width:100%;margin-top:10px;">
            <tr>
                <th>
            <center style="font-size:9;">PAYBILL FOR THE MONTH OF <b><?php 
            $month= $selmonth;
            $year= $selyear;
            
            echo date("F",strtotime($month)).", ".$year;?></b></center>
                </th>
            </tr>
        </table>
       <table style="width:100%;" class="TFtable">
            <?php// if($countno == 1){ ?>
             <?php 
             
                    
                    $empid=$totalemp[$i]->emp_id;
                  //  echo "seems3==".$empid;
                   // $empid=26;
                   // $case=$this->uri->segment(6,'');
                    $dupdata=array(
                    'sle_empid'                =>$empid,
                    'sle_month'                =>$month,
                    'sle_year'                 =>$year,   
                    );
                    $dupexists=$this->sismodel->isduplicatemore('salary_leave_entry',$dupdata);
                    
                    $trdata=array(
                    'ste_empid'                =>$empid,
                    'ste_month'                =>$month,
                    'ste_year'                 =>$year,   
                    );
                    $trferexists=$this->sismodel->isduplicatemore('salary_transfer_entry',$trdata);
                
                    $empname=$this->sismodel->get_listspfic1('employee_master','emp_name','emp_id',$empid)->emp_name;
                    $empcode=$this->sismodel->get_listspfic1('employee_master','emp_code','emp_id',$empid)->emp_code;
                   
                    $deptid=$deptid=$this->sismodel->get_listspfic1('employee_master','emp_dept_code','emp_id',$empid)->emp_dept_code;
                    $deptname=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$deptid)->dept_name;
                    $deptcode=$this->commodel->get_listspfic1('Department','dept_code','dept_id',$deptid)->dept_code;
                   
                    $ddoid=$this->sismodel->get_listspfic1('employee_master','emp_ddoid','emp_id',$empid)->emp_ddoid;
                    $ddoname=$this->sismodel->get_listspfic1('ddo','ddo_name','ddo_id',$ddoid)->ddo_name; 
                    $degid=$this->sismodel->get_listspfic1('employee_master','emp_desig_code','emp_id',$empid)->emp_desig_code;
                    $designame=$this->commodel->get_listspfic1('designation','desig_name','desig_id',$degid)->desig_name;
                    $desigcode=$this->commodel->get_listspfic1('designation','desig_code','desig_id',$degid)->desig_code;
                    
                    $pbid=$this->sismodel->get_listspfic1('employee_master','emp_salary_grade','emp_id',$empid)->emp_salary_grade;
                    $payband=$this->sismodel->get_listspfic1('salary_grade_master','sgm_name','sgm_id',$pbid)->sgm_name;
                    $pay_max=$this->sismodel->get_listspfic1('salary_grade_master','sgm_max','sgm_id',$pbid)->sgm_max;
                    $pay_min=$this->sismodel->get_listspfic1('salary_grade_master','sgm_min','sgm_id',$pbid)->sgm_min;
                    $gardepay=$this->sismodel->get_listspfic1('salary_grade_master','sgm_gradepay','sgm_id',$pbid)->sgm_gradepay;
                    $gardelevel=$this->sismodel->get_listspfic1('salary_grade_master','sgm_level','sgm_id',$pbid)->sgm_level;
                    $paycomm=$this->sismodel->get_listspfic1('employee_master','emp_paycomm','emp_id',$empid)->emp_paycomm;
                    $payscale=$gardelevel.":".$payband."(".$pay_min."-".$pay_max.")".$gardepay;
                    
                    $bkacc=$this->sismodel->get_listspfic1('employee_master','emp_bank_accno','emp_id',$empid)->emp_bank_accno;
                    $doj=$this->sismodel->get_listspfic1('employee_master','emp_doj','emp_id',$empid)->emp_doj;
                    $dor= $this->sismodel->get_listspfic1('employee_master','emp_dor','emp_id',$empid)->emp_dor;  
                   
                    $group=$this->sismodel->get_listspfic1('employee_master','emp_group','emp_id',$empid)->emp_group;
                    //$panno=$this->sismodel->get_listspfic1('employee_master','emp_pan_no','emp_id',$empid)->emp_pan_no;
                    $dob=$this->sismodel->get_listspfic1('employee_master','emp_dob','emp_id',$empid)->emp_dob;
                    //$sapost=$this->sismodel->get_listspfic1('employee_master','emp_post','emp_id',$empid)->emp_post;
                    $society=$this->sismodel->get_listspfic1('employee_master_support','ems_society','ems_empid',$empid)->ems_society;
                    $socname=$this->sismodel->get_listspfic1('society_master_list','soc_sname','soc_id',$society)->soc_sname;
                    $bkname=$this->sismodel->get_listspfic1('employee_master','emp_bankname','emp_id',$empid)->emp_bankname;
              
                    echo "<tr><td style=font-size:9;> <b>Emp.NO:</b></td><td style=font-size:9;><b>".$empcode."</b></td>";
                    echo "<td style=font-size:9;> <b>Grade:</b></td><td style=font-size:9;><b>".$group."</b></td>";
                    echo "<td style=font-size:9;> <b>DDO:</b></td><td style=font-size:9;><b>".$ddoname."</b></td></tr>";
                    
                    echo "<tr><td style=font-size:9;><b> Name:</b></td><td style=font-size:9;><b>".$empname."</b></td>";
                    echo "<td style=font-size:9;> <b>DOB:</b></td><td style=font-size:9;><b>".date('d-m-Y',strtotime($dob))."</b></td>";
                    echo "<td style=font-size:9;> <b>Days Paid:</b></td><td style=font-size:9;><b>"."0"."</b></td></tr>";
                    
                     echo "<tr><td style=font-size:9;> <b>Pay Scale:</b></td><td style=font-size:9;><b>".$payscale.":".$paycomm."</b></td>";
                    echo "<td style=font-size:9;> <b>DOJ:</b></td><td style=font-size:9;><b>".date('d-m-Y',strtotime($doj))."</b></td>";
                    echo "<td style=font-size:9;> <b>Bank A/c:</b></td><td style=font-size:9;><b>".$bkacc."</td></tr>";
                    
                    echo "<tr><td style=font-size:9;> <b>Designation:</b></td><td style=font-size:9;><b>".$designame."&nbsp;&nbsp;".$desigcode."</b></td>";
                    echo "<td style=font-size:9;> <b>DOR:</b></td><td style=font-size:9;><b>".date('d-m-Y',strtotime($dor))."</b></td>";
                    echo "<td style=font-size:9;> <b>Bank :</b></td><td style=font-size:9;><b>".$bkname."</b></td></tr>";
                    
                    
                    echo "<tr><td style=font-size:9;> <b>Department:</b></td><td style=font-size:9;><b>".$deptname."&nbsp;&nbsp;".$deptcode."</b></b></td>";
                    echo "<td style=font-size:9;> <b>UPF.NO:</b></td><td style=font-size:9;><b>".$empcode."</b></td>";
                    echo "<td style=font-size:9;> <b>Society Name:</b></td><td style=font-size:9;><b>".$socname."</b></td></tr>";               
                   
                  
            ;?>
            <hr>
            </table>
        
        
            <div style="clear:both; position:relative;">
                <?php 
                    $countin=count($incomes);
                    $halfin = $countin / 2; 
                   // print_r("partoooo===".$half."count==".$count);
                          
                    $partin1 = array_slice($incomes, 0, $halfin); 
                    $partin2 = array_slice($incomes, $halfin); 
                             
                 ?>
                <div class="column" style="left:5pt;width:33%;" >
                    
                <table width="100%" class="TFtable">
                       
                        <tr>
                            <td style="font-size:9;" width="30%"><b>EARNINGS</b></td>
                            
                        </tr>    
                        <?php foreach($partin1 as $inrecord){ ?>
                       <tr>
                            <td style="font-size:9;word-break:break-all; word-wrap:break-word;"><b><?php echo nl2br($inrecord->sh_code);?> </b> </td>
                           <td style="font-size:9; left:10px;"> <?php 
                                                                
                                if($dupexists == 1 || $trferexists == 1){
                                    $selsaldata ="saldlt_shamount";
                                    $whdata = array('saldlt_sheadid'=>$inrecord->sh_id,'saldlt_empid' =>$empid,'saldlt_month' =>$month,'saldlt_year' =>$year);
                                    $spec_data= $this->sismodel->get_orderlistspficemore('salarydata_lt',$selsaldata,$whdata,'');   
                                    if(!empty($spec_data)){
                                        $headamt=$spec_data[0]->saldlt_shamount;
                                        echo $headamt;
                                    }    
                                    else{
                                        echo  "0";       
                                    }
                                    
                                }
                                else{
                                    $selsaldata ="sald_shamount";
                                    $whdata = array('sald_sheadid'=>$inrecord->sh_id,'sald_empid' =>$empid,'sald_month' =>$month,'sald_year' =>$year);
                                    $spec_data= $this->sismodel->get_orderlistspficemore('salary_data',$selsaldata,$whdata,'');
                                    if(!empty($spec_data)){
                                        $headamt=$spec_data[0]->sald_shamount;
                                        echo $headamt;
                                    }    
                                    else{
                                        echo  "0";       
                                    }
                                }
                                
                                   
                                ?>
                                </td>
                        </tr> 
                        <?php };?>
                    </table> 
                    
                </div>
                <div class="column" style="left:120pt;width:33%;">
                <table width="100%" class="TFtable">
                     <tr>
                            <td style="font-size:9;" width="30%"><b>EARNINGS</b></td>
                            
                        </tr>        
                    <?php foreach($partin2 as $inrecord){ ?>
                       <tr>
                            <td style="font-size:9;width:0px; word-break:break-all; word-wrap:break-word;"><b><?php echo nl2br($inrecord->sh_code);?></b></td> 
                            <td style="font-size:9; left:10px;"><b><?php 
                                                                
                                if($dupexists == 1 || $trferexists == 1){
                                    $selsaldata ="saldlt_shamount";
                                    $whdata = array('saldlt_sheadid'=>$inrecord->sh_id,'saldlt_empid' =>$empid,'saldlt_month' =>$month,'saldlt_year' =>$year);
                                    $spec_data= $this->sismodel->get_orderlistspficemore('salarydata_lt',$selsaldata,$whdata,'');   
                                    if(!empty($spec_data)){
                                        $headamt=$spec_data[0]->saldlt_shamount;
                                        echo $headamt;
                                    }    
                                    else{
                                        echo "0";       
                                    }
                                    
                                }
                                else{
                                    $selsaldata ="sald_shamount";
                                    $whdata = array('sald_sheadid'=>$inrecord->sh_id,'sald_empid' =>$empid,'sald_month' =>$month,'sald_year' =>$year);
                                    $spec_data= $this->sismodel->get_orderlistspficemore('salary_data',$selsaldata,$whdata,'');
                                    if(!empty($spec_data)){
                                        $headamt=$spec_data[0]->sald_shamount;
                                        echo $headamt;
                                    }    
                                    else{
                                        echo "0";       
                                    }
                                }
                                
                                   
                                ?>
                                </b></td>
                        </tr> 
                        <?php };?>
                    </table>    
                </div> 
        
                <!--   ---------------------------end of Earnings -----------------------------    -->
                <?php 
                    $count=count($deduction);
                                             
                    $part1 = array_slice($deduction, 0, 11); 
                    $part2 = array_slice($deduction, 11,11); 
                    $part3 = array_slice($deduction, 21,-1); 
                             
                              
                 ?>
                
                <div class="column" style="margin-left:220pt;width:33%;" >
                   <table width="100%" class="TFtable"> 
                        <tr> 
                            <td style="font-size:9;" width="30%"><b>Deductions</b></td>
                            
                        </tr> 
                        <?php 
                            foreach($part1 as $dedrecord){
                             
                        ?>
                        <tr>
                            <td style="font-size:9;word-break:break-all; word-wrap:break-word;"><b><?php echo nl2br($dedrecord->sh_code) ;?></b></td> 
                            <td style="font-size:9;left:10px;"><b><?php 
                                 if($dupexists == 1 || $trferexists == 1){
                                    $selsaldata ="saldlt_shamount";
                                    $whdata = array('saldlt_sheadid'=>$dedrecord->sh_id,'saldlt_empid' =>$empid,'saldlt_month' =>$month,'saldlt_year' =>$year);
                                    $spec_data= $this->sismodel->get_orderlistspficemore('salarydata_lt',$selsaldata,$whdata,'');   
                                    if(!empty($spec_data)){
                                        $headamt=$spec_data[0]->saldlt_shamount;
                                        echo $headamt;
                                    }    
                                    else{
                                        echo "0";       
                                    }
                                    
                                }
                                else{
                            
                                    $selsaldata ="sald_shamount,";
                                    $whdata = array('sald_sheadid'=>$dedrecord->sh_id,'sald_empid' =>$empid,'sald_month' =>$month,'sald_year' =>$year);
                                    $spec_data= $this->sismodel->get_orderlistspficemore('salary_data',$selsaldata,$whdata,'');
                                    if(!empty($spec_data)){
                                        $headamt=$spec_data[0]->sald_shamount;
                                        echo $headamt;
                                    }    
                                    else{
                                        echo "0";       
                                    }
                                }    
                                ?> </b>
                                   
                            </td>
                        </tr>
                        <?php };?>
                       
                    </table>   
                </div>
                
                <div class="column" style="margin-left:320pt;width:33%;">
                  
                   <table width="100%" class="TFtable"> 
                        <tr> 
                            <td style="font-size:9;" width="30%"><b>Deductions</b></td>
                          
                        </tr> 
                        <?php foreach($part2 as $dedrecord1){
                            
                          //  $part=array_chunk($dedrecord,20,true);
                            
                        ?>
                        <tr>
                            <td style="font-size:9;word-break:break-all; word-wrap:break-word;"><b><?php echo nl2br($dedrecord1->sh_code) ;?> </b></td> 
                            <td style="font-size:9;left:10px;"><b><?php 
                                 if($dupexists == 1 || $trferexists == 1){
                                    $selsaldata ="saldlt_shamount";
                                    $whdata = array('saldlt_sheadid'=>$dedrecord1->sh_id,'saldlt_empid' =>$empid,'saldlt_month' =>$month,'saldlt_year' =>$year);
                                    $spec_data= $this->sismodel->get_orderlistspficemore('salarydata_lt',$selsaldata,$whdata,'');   
                                    if(!empty($spec_data)){
                                        $headamt=$spec_data[0]->saldlt_shamount;
                                        echo $headamt;
                                    }    
                                    else{
                                        echo "0";       
                                    }
                                    
                                }
                                else{
                            
                                    $selsaldata ="sald_shamount,";
                                    $whdata = array('sald_sheadid'=>$dedrecord1->sh_id,'sald_empid' =>$empid,'sald_month' =>$month,'sald_year' =>$year);
                                    $spec_data= $this->sismodel->get_orderlistspficemore('salary_data',$selsaldata,$whdata,'');
                                    if(!empty($spec_data)){
                                        $headamt=$spec_data[0]->sald_shamount;
                                        echo $headamt;
                                    }    
                                    else{
                                        echo "0";       
                                    }
                                }    
                                ?></b>
                            </td>
                        </tr>
                        <?php };?>
                       
                    </table>    
                </div>
                
                <div class="column" style="margin-left:420pt;width:33%;">
                  
                   <table width="100%" class="TFtable"> 
                        <tr> 
                            <td style="font-size:9;" width="30%"><b>Deductions</b></td>
                            
                        </tr> 
                        <?php foreach($part3 as $dedrecord2){
                            
                          //  $part=array_chunk($dedrecord,20,true);
                            
                        ?>
                        <tr>
                            <td style="font-size:9;word-break:break-all; word-wrap:break-word;"><b><?php echo nl2br($dedrecord2->sh_code) ;?> </b></td> 
                            <td style="font-size:9;left:10px;"><b><?php 
                                 if($dupexists == 1 || $trferexists == 1){
                                    $selsaldata ="saldlt_shamount";
                                    $whdata = array('saldlt_sheadid'=>$dedrecord2->sh_id,'saldlt_empid' =>$empid,'saldlt_month' =>$month,'saldlt_year' =>$year);
                                    $spec_data= $this->sismodel->get_orderlistspficemore('salarydata_lt',$selsaldata,$whdata,'');   
                                    if(!empty($spec_data)){
                                        $headamt=$spec_data[0]->saldlt_shamount;
                                        echo $headamt;
                                    }    
                                    else{
                                        echo "0";       
                                    }
                                    
                                }
                                else{
                            
                                    $selsaldata ="sald_shamount,";
                                    $whdata = array('sald_sheadid'=>$dedrecord2->sh_id,'sald_empid' =>$empid,'sald_month' =>$month,'sald_year' =>$year);
                                    $spec_data= $this->sismodel->get_orderlistspficemore('salary_data',$selsaldata,$whdata,'');
                                    if(!empty($spec_data)){
                                        $headamt=$spec_data[0]->sald_shamount;
                                        echo $headamt;
                                    }    
                                    else{
                                        echo "0";       
                                    }
                                }    
                                ?></b>
                            </td>
                        </tr>
                        <?php };?>
                       
                    </table>    
                </div>
              
            </div>
        
            <div style="position: fixed; left: 5px; bottom:510px; right: 0px; height: 70px;">
                <hr>
                <table width="100%" class="TFtable">
                    
                    <tr>
                        <?php
                            if($dupexists == 1 || $trferexists == 1){
                                
                                $selsaldata ="sallt_totalincome, sallt_totaldeduction, sallt_netsalary";
                                $whdata = array('sallt_empid' =>$empid,'sallt_month' =>$month,'sallt_year' =>$year);
                                $spec_data= $this->sismodel->get_orderlistspficemore('salary_lt',$selsaldata,$whdata,'');
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
                            }
                            else{
                        
                                $selsaldata ="sal_totalincome, sal_totaldeduction, sal_netsalary";
                                $whdata = array('sal_empid' =>$empid,'sal_month' =>$month,'sal_year' =>$year);
                                $spec_data= $this->sismodel->get_orderlistspficemore('salary',$selsaldata,$whdata,'');
                                if(!empty($spec_data)){
                                    $grosspay=$spec_data[0]->sal_totalincome;  
                                    $totalded=$spec_data[0]->sal_totaldeduction;
                                    $netpay=$spec_data[0]->sal_netsalary;
                                }
                                else{
                                    $grosspay=0; 
                                    $totalded=0;
                                    $netpay=0;       
                                }
                            }    
                           
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
          
        
        </div>
               
        <!--   ---------------------end of one employee salary slip ------------------------------------    -->  
       
        <!--   ---------------------end of second  and (change the  div  pt in style="left:5pt;") employee salary slip ---------------------   -->  
        
            <?php //}?>
        <?php // if($countno ==2){?>
        
        
        <div  style="position: fixed; left: 5px; bottom:520px; right: 0px;">
                   
	<img src="uploads/logo/logotanuvas.jpeg" alt="logo" style="width:100%;height:70px;"><br/>
	<table style="width:100%;margin-top:10px;">
            
            <tr>
                <th>
            <center style="font-size:9;">PAYBILL FOR THE MONTH OF <b><?php echo date("F",strtotime($month)) .", ".$year;?></b></center>
                </th>
            </tr>
        </table>
         <table style="width:100%;" class="TFtable">
             <?php 
             
                    $month=$selmonth;
                    $year= $selyear;
                    //$empid=23;
                    $empid=$totalemp[$i+1]->emp_id;
                    //$empid=$recordemp->sal_empid;
                    /*$case=$this->uri->segment(6,'');*/
                    $dupdata=array(
                    'sle_empid'                =>$empid,
                    'sle_month'                =>$month,
                    'sle_year'                 =>$year,   
                    );
                    $dupexists=$this->sismodel->isduplicatemore('salary_leave_entry',$dupdata);
                    
                    $trdata=array(
                    'ste_empid'                =>$empid,
                    'ste_month'                =>$month,
                    'ste_year'                 =>$year,   
                    );
                    $trferexists=$this->sismodel->isduplicatemore('salary_transfer_entry',$trdata);
                    
                
                    $empname=$this->sismodel->get_listspfic1('employee_master','emp_name','emp_id',$empid)->emp_name;
                    $empcode=$this->sismodel->get_listspfic1('employee_master','emp_code','emp_id',$empid)->emp_code;
                  
                    $deptid=$deptid=$this->sismodel->get_listspfic1('employee_master','emp_dept_code','emp_id',$empid)->emp_dept_code;
                    $deptname=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$deptid)->dept_name;
                    $deptcode=$this->commodel->get_listspfic1('Department','dept_code','dept_id',$deptid)->dept_code;
                   
                    $ddoid=$this->sismodel->get_listspfic1('employee_master','emp_ddoid','emp_id',$empid)->emp_ddoid;
                    $ddoname=$this->sismodel->get_listspfic1('ddo','ddo_name','ddo_id',$ddoid)->ddo_name; 
                    $degid=$this->sismodel->get_listspfic1('employee_master','emp_desig_code','emp_id',$empid)->emp_desig_code;
                    $designame=$this->commodel->get_listspfic1('designation','desig_name','desig_id',$degid)->desig_name;
                    $desigcode=$this->commodel->get_listspfic1('designation','desig_code','desig_id',$degid)->desig_code;
                    
                    $pbid=$this->sismodel->get_listspfic1('employee_master','emp_salary_grade','emp_id',$empid)->emp_salary_grade;
                    $payband=$this->sismodel->get_listspfic1('salary_grade_master','sgm_name','sgm_id',$pbid)->sgm_name;
                    $pay_max=$this->sismodel->get_listspfic1('salary_grade_master','sgm_max','sgm_id',$pbid)->sgm_max;
                    $pay_min=$this->sismodel->get_listspfic1('salary_grade_master','sgm_min','sgm_id',$pbid)->sgm_min;
                    $gardepay=$this->sismodel->get_listspfic1('salary_grade_master','sgm_gradepay','sgm_id',$pbid)->sgm_gradepay;
                    $gardelevel=$this->sismodel->get_listspfic1('salary_grade_master','sgm_level','sgm_id',$pbid)->sgm_level;
                    $paycomm=$this->sismodel->get_listspfic1('employee_master','emp_paycomm','emp_id',$empid)->emp_paycomm;
                    $payscale=$gardelevel.":".$payband."(".$pay_min."-".$pay_max.")".$gardepay;
                    
                    $bkacc=$this->sismodel->get_listspfic1('employee_master','emp_bank_accno','emp_id',$empid)->emp_bank_accno;
                    $doj=$this->sismodel->get_listspfic1('employee_master','emp_doj','emp_id',$empid)->emp_doj;
                    $dor= $this->sismodel->get_listspfic1('employee_master','emp_dor','emp_id',$empid)->emp_dor;  
                   
                    $group=$this->sismodel->get_listspfic1('employee_master','emp_group','emp_id',$empid)->emp_group;
                    
                    $dob=$this->sismodel->get_listspfic1('employee_master','emp_dob','emp_id',$empid)->emp_dob;
                    
                    $society=$this->sismodel->get_listspfic1('employee_master_support','ems_society','ems_empid',$empid)->ems_society;
                    $socname=$this->sismodel->get_listspfic1('society_master_list','soc_sname','soc_id',$society)->soc_sname;
                    $bkname=$this->sismodel->get_listspfic1('employee_master','emp_bankname','emp_id',$empid)->emp_bankname;
              
                    echo "<tr><td style=font-size:9;> <b>Emp.NO:</b></td><td style=font-size:9;><b>".$empcode."</b></td>";
                    echo "<td style=font-size:9;> <b>Grade:</b></td><td style=font-size:9;><b>".$group."</b></td>";
                    echo "<td style=font-size:9;> <b>DDO:</b></td><td style=font-size:9;><b>".$ddoname."</b></td></tr>";
                    
                    echo "<tr><td style=font-size:9;><b> Name:</b></td><td style=font-size:9;><b>".$empname."</b></td>";
                    echo "<td style=font-size:9;> <b>DOB:</b></td><td style=font-size:9;><b>".date('d-m-Y',strtotime($dob))."</b></td>";
                    echo "<td style=font-size:9;> <b>Days Paid:</b></td><td style=font-size:9;><b>"."0"."</b></td></tr>";
                    
                     echo "<tr><td style=font-size:9;> <b>Pay Scale:</b></td><td style=font-size:9;><b>".$payscale.":".$paycomm."</b></td>";
                    echo "<td style=font-size:9;> <b>DOJ:</b></td><td style=font-size:9;><b>".date('d-m-Y',strtotime($doj))."</b></td>";
                    echo "<td style=font-size:9;> <b>Bank A/c:</b></td><td style=font-size:9;><b>".$bkacc."</td></tr>";
                    
                    echo "<tr><td style=font-size:9;> <b>Designation:</b></td><td style=font-size:9;><b>".$designame."&nbsp;&nbsp;".$desigcode."</b></td>";
                    echo "<td style=font-size:9;> <b>DOR:</b></td><td style=font-size:9;><b>".date('d-m-Y',strtotime($dor))."</b></td>";
                    echo "<td style=font-size:9;> <b>Bank :</b></td><td style=font-size:9;><b>".$bkname."</b></td></tr>";
                    
                    
                    echo "<tr><td style=font-size:9;> <b>Department:</b></td><td style=font-size:9;><b>".$deptname."&nbsp;&nbsp;".$deptcode."</b></b></td>";
                    echo "<td style=font-size:9;> <b>UPF.NO:</b></td><td style=font-size:9;><b>".$empcode."</b></td>";
                    echo "<td style=font-size:9;> <b>Society Name:</b></td><td style=font-size:9;><b>".$socname."</b></td></tr>";               
                   
                  
            ;?>
            <hr>
            </table>
            <div style="clear:both; position:relative;">
                <?php 
                    $countin=count($incomes);
                    $halfin = $countin / 2; 
                   // print_r("partoooo===".$half."count==".$count);
                          
                    $partin1 = array_slice($incomes, 0, $halfin); 
                    $partin2 = array_slice($incomes, $halfin); 
                             
                 ?>
                <div class="column" style="left:5pt;width:33%;" >
                    
                <table width="100%" class="TFtable">
                       
                        <tr>
                            <td style="font-size:9;" width="30%"><b>EARNINGS</b></td>
                            
                        </tr>    
                        <?php foreach($partin1 as $inrecord){ ?>
                       <tr>
                            <td style="font-size:9;word-break:break-all; word-wrap:break-word;"><b><?php echo nl2br($inrecord->sh_code);?> </b> </td>
                           <td style="font-size:9; left:10px;"> <?php 
                                                                
                                if($dupexists == 1 || $trferexists == 1){
                                    $selsaldata ="saldlt_shamount";
                                    $whdata = array('saldlt_sheadid'=>$inrecord->sh_id,'saldlt_empid' =>$empid,'saldlt_month' =>$month,'saldlt_year' =>$year);
                                    $spec_data= $this->sismodel->get_orderlistspficemore('salarydata_lt',$selsaldata,$whdata,'');   
                                    if(!empty($spec_data)){
                                        $headamt=$spec_data[0]->saldlt_shamount;
                                        echo $headamt;
                                    }    
                                    else{
                                        echo 0;       
                                    }
                                    
                                }
                                else{
                                    $selsaldata ="sald_shamount";
                                    $whdata = array('sald_sheadid'=>$inrecord->sh_id,'sald_empid' =>$empid,'sald_month' =>$month,'sald_year' =>$year);
                                    $spec_data= $this->sismodel->get_orderlistspficemore('salary_data',$selsaldata,$whdata,'');
                                    if(!empty($spec_data)){
                                        $headamt=$spec_data[0]->sald_shamount;
                                        echo $headamt;
                                    }    
                                    else{
                                        echo 0;       
                                    }
                                }
                                
                                   
                                ?>
                                </td>
                        </tr> 
                        <?php };?>
                    </table> 
                    
                </div>
                <div class="column" style="left:120pt;width:33%;">
                <table width="100%" class="TFtable">
                     <tr>
                            <td style="font-size:9;" width="30%"><b>EARNINGS</b></td>
                            
                        </tr>        
                    <?php foreach($partin2 as $inrecord){ ?>
                       <tr>
                            <td style="font-size:9;width:0px; word-break:break-all; word-wrap:break-word;"><b><?php echo nl2br($inrecord->sh_code);?></b></td> 
                            <td style="font-size:9; left:10px;"><b><?php 
                                                                
                                if($dupexists == 1 || $trferexists == 1){
                                    $selsaldata ="saldlt_shamount";
                                    $whdata = array('saldlt_sheadid'=>$inrecord->sh_id,'saldlt_empid' =>$empid,'saldlt_month' =>$month,'saldlt_year' =>$year);
                                    $spec_data= $this->sismodel->get_orderlistspficemore('salarydata_lt',$selsaldata,$whdata,'');   
                                    if(!empty($spec_data)){
                                        $headamt=$spec_data[0]->saldlt_shamount;
                                        echo $headamt;
                                    }    
                                    else{
                                        echo "0";       
                                    }
                                    
                                }
                                else{
                                    $selsaldata ="sald_shamount";
                                    $whdata = array('sald_sheadid'=>$inrecord->sh_id,'sald_empid' =>$empid,'sald_month' =>$month,'sald_year' =>$year);
                                    $spec_data= $this->sismodel->get_orderlistspficemore('salary_data',$selsaldata,$whdata,'');
                                    if(!empty($spec_data)){
                                        $headamt=$spec_data[0]->sald_shamount;
                                        echo $headamt;
                                    }    
                                    else{
                                        echo "0";       
                                    }
                                }
                                
                                   
                                ?>
                                </b></td>
                        </tr> 
                        <?php };?>
                    </table>    
                </div> 
                <!--  ---------------------------end of Earnings -----------------------------   -->
                <?php 
                    $count=count($deduction);
                   // $half = $count / 3; 
                   // print_r("partoooo===".$half."count==".$count);
                          
                    $part1 = array_slice($deduction, 0, 11); 
                    $part2 = array_slice($deduction, 11,11); 
                    $part3 = array_slice($deduction, 21,-1); 
                             
                             
                 ?>
                
                
               
                <div class="column" style="margin-left:220pt;width:33%;" >
                    <table width="100%" class="TFtable"> 
                        <tr> 
                            <td style="font-size:9;" width="30%"><b>Deductions</b></td>
                            
                        </tr> 
                        <?php 
                            foreach($part1 as $dedrecord){
                             
                        ?>
                        <tr>
                            <td style="font-size:9;word-break:break-all; word-wrap:break-word;"><b><?php echo nl2br($dedrecord->sh_code) ;?></b></td> 
                            <td style="font-size:9;left:10px;"><b><?php 
                                 if($dupexists == 1 || $trferexists == 1){
                                    $selsaldata ="saldlt_shamount";
                                    $whdata = array('saldlt_sheadid'=>$dedrecord->sh_id,'saldlt_empid' =>$empid,'saldlt_month' =>$month,'saldlt_year' =>$year);
                                    $spec_data= $this->sismodel->get_orderlistspficemore('salarydata_lt',$selsaldata,$whdata,'');   
                                    if(!empty($spec_data)){
                                        $headamt=$spec_data[0]->saldlt_shamount;
                                        echo $headamt;
                                    }    
                                    else{
                                        echo "0";       
                                    }
                                    
                                }
                                else{
                            
                                    $selsaldata ="sald_shamount,";
                                    $whdata = array('sald_sheadid'=>$dedrecord->sh_id,'sald_empid' =>$empid,'sald_month' =>$month,'sald_year' =>$year);
                                    $spec_data= $this->sismodel->get_orderlistspficemore('salary_data',$selsaldata,$whdata,'');
                                    if(!empty($spec_data)){
                                        $headamt=$spec_data[0]->sald_shamount;
                                        echo $headamt;
                                    }    
                                    else{
                                        echo "0";       
                                    }
                                }    
                                ?> </b>
                                   
                            </td>
                        </tr>
                        <?php };?>
                       
                    </table>   
                </div>
                
                <div class="column" style="margin-left:320pt;width:33%;">
                  
                   <table width="100%" class="TFtable"> 
                        <tr> 
                            <td style="font-size:9;" width="30%"><b>Deductions</b></td>
                            
                        </tr> 
                        <?php foreach($part2 as $dedrecord1){
                            
                          //  $part=array_chunk($dedrecord,20,true);
                            
                        ?>
                        <tr>
                            <td style="font-size:9;word-break:break-all; word-wrap:break-word;"><b><?php echo nl2br($dedrecord1->sh_code) ;?> </b></td> 
                            <td style="font-size:9;left:10px;"><b><?php 
                                 if($dupexists == 1 || $trferexists == 1){
                                    $selsaldata ="saldlt_shamount";
                                    $whdata = array('saldlt_sheadid'=>$dedrecord1->sh_id,'saldlt_empid' =>$empid,'saldlt_month' =>$month,'saldlt_year' =>$year);
                                    $spec_data= $this->sismodel->get_orderlistspficemore('salarydata_lt',$selsaldata,$whdata,'');   
                                    if(!empty($spec_data)){
                                        $headamt=$spec_data[0]->saldlt_shamount;
                                        echo $headamt;
                                    }    
                                    else{
                                        echo "0";       
                                    }
                                    
                                }
                                else{
                            
                                    $selsaldata ="sald_shamount,";
                                    $whdata = array('sald_sheadid'=>$dedrecord1->sh_id,'sald_empid' =>$empid,'sald_month' =>$month,'sald_year' =>$year);
                                    $spec_data= $this->sismodel->get_orderlistspficemore('salary_data',$selsaldata,$whdata,'');
                                    if(!empty($spec_data)){
                                        $headamt=$spec_data[0]->sald_shamount;
                                        echo $headamt;
                                    }    
                                    else{
                                        echo "0";       
                                    }
                                }    
                                ?></b>
                            </td>
                        </tr>
                        <?php };?>
                       
                    </table>    
                </div>
                
                <div class="column" style="margin-left:420pt;width:33%;">
                  
                   <table width="100%" class="TFtable"> 
                        <tr> 
                            <td style="font-size:9;" width="30%"><b>Deductions</b></td>
                           
                        </tr> 
                        <?php foreach($part3 as $dedrecord2){
                            
                          //  $part=array_chunk($dedrecord,20,true);
                            
                        ?>
                        <tr>
                            <td style="font-size:9;word-break:break-all; word-wrap:break-word;"><b><?php echo nl2br($dedrecord2->sh_code) ;?> </b></td> 
                            <td style="font-size:9;left:10px;"><b><?php 
                                 if($dupexists == 1 || $trferexists == 1){
                                    $selsaldata ="saldlt_shamount";
                                    $whdata = array('saldlt_sheadid'=>$dedrecord2->sh_id,'saldlt_empid' =>$empid,'saldlt_month' =>$month,'saldlt_year' =>$year);
                                    $spec_data= $this->sismodel->get_orderlistspficemore('salarydata_lt',$selsaldata,$whdata,'');   
                                    if(!empty($spec_data)){
                                        $headamt=$spec_data[0]->saldlt_shamount;
                                        echo $headamt;
                                    }    
                                    else{
                                        echo "0";       
                                    }
                                    
                                }
                                else{
                            
                                    $selsaldata ="sald_shamount,";
                                    $whdata = array('sald_sheadid'=>$dedrecord2->sh_id,'sald_empid' =>$empid,'sald_month' =>$month,'sald_year' =>$year);
                                    $spec_data= $this->sismodel->get_orderlistspficemore('salary_data',$selsaldata,$whdata,'');
                                    if(!empty($spec_data)){
                                        $headamt=$spec_data[0]->sald_shamount;
                                        echo $headamt;
                                    }    
                                    else{
                                        echo "0";       
                                    }
                                }    
                                ?></b>
                            </td>
                        </tr>
                        <?php };?>
                       
                    </table>    
                </div>
              
            </div>
            <div style="position: fixed; left: 5px; bottom:2px; right: 0px; height: 70px;">
                <hr>
                <table width="100%" class="TFtable">
                    
                    <tr>
                        <?php
                            if($dupexists == 1 || $trferexists == 1){
                                
                                $selsaldata ="sallt_totalincome, sallt_totaldeduction, sallt_netsalary";
                                $whdata = array('sallt_empid' =>$empid,'sallt_month' =>$month,'sallt_year' =>$year);
                                $spec_data= $this->sismodel->get_orderlistspficemore('salary_lt',$selsaldata,$whdata,'');
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
                            }
                            else{
                        
                                $selsaldata ="sal_totalincome, sal_totaldeduction, sal_netsalary";
                                $whdata = array('sal_empid' =>$empid,'sal_month' =>$month,'sal_year' =>$year);
                                $spec_data= $this->sismodel->get_orderlistspficemore('salary',$selsaldata,$whdata,'');
                                if(!empty($spec_data)){
                                    $grosspay=$spec_data[0]->sal_totalincome;  
                                    $totalded=$spec_data[0]->sal_totaldeduction;
                                    $netpay=$spec_data[0]->sal_netsalary;
                                }
                                else{
                                    $grosspay=0; 
                                    $totalded=0;
                                    $netpay=0;       
                                }
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
        <?php //};?>
           <!-- <div style="position: fixed; left: 5px; bottom:30px; right: 0px; height: 30px;">
            <p>As it is computer generated slip, signature is not required. </p>  
            </div> -->
       
        </div> 
        
      <!--  $i++; -->
       
       
       <!-- <span style="page-break-before:always;"></span> -->
      </div>
        <?php }; ?> 
        <?php //endforeach; ?>  
    </body>    
</html>   
