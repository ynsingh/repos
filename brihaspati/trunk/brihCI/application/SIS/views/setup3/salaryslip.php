 <!--@filename empSalary.php  @author Manorama Pal(palseema30@gmail.com) -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Salary Slip</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-ui.js" ></script>
       <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script> -->
        <style>
            /* Create two equal columns that floats next to each other */
            .column {
                //float: left;
            width: 50%;
            //  padding: 10px;
            // height: 300px; /* Should be removed. Only for demonstration */
            }

            /* Clear floats after the columns */
            .row:after {
                content: "";
                display: table;
                clear: both;
            }
           .dggh td, th {
                border: 1px solid grey;
            }
        </style>
        <script>
        
            $(document).ready(function(){ 
            /*********************************************for Earnings ************************************/ 
                $(".headamtI").on("blur", function(){
                    var sum=0;
                    $(".headamtI").each(function(){
                        if($(this).val() != "")
                            sum += parseInt($(this).val());   
                          //  alert("seema-----"+sum);
                    });
                    $("#Tincome").val(sum);
                    var tincome = parseInt($("#Tincome").val());
                    var tdeduction = parseInt($("#Tdeduction").val());
                    var dfpay = tincome - tdeduction;
                    $("#netPay").val(dfpay);
                });
                    
                /*********************************************for Earnings end ************************************/ 
                
                /*********************************************for Deduction ************************************/  
                
                $(".headamtD").on("blur", function(){
                    var sumded=0;
                    $(".headamtD").each(function(){
                        if($(this).val() != "")
                            sumded += parseInt($(this).val());   
                           // alert("seema--ded---"+sumded);
                    });
    
                    $("#Tdeduction").val(sumded);
                    var tdeduction = parseInt($("#Tdeduction").val());
                    var tincome = parseInt($("#Tincome").val());
                    var dfpay = tincome - tdeduction;
                    $("#netPay").val(dfpay);
                });
                
                /*********************************************for Deduction end************************************/  
             
        }); 
               
        </script>    
    </head>
    <body>
        <?php $this->load->view('template/header'); ?>
        
        <table width="100%">
            <tr>
                <?php
                    echo "<td align=\"left\" width=\"33%\">";
                    echo anchor('setup3/salaryprocess/', "View Salary Processing Staff List" ,array('title' => 'View View Salary Processing Staff List' , 'class' => 'top_parent'));
                    echo "</td>";
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Salary Slip</b>";
                    echo "</td>";
                    echo "<td align=\"right\" width=\"33%\">";

                ?>
            </tr>
        </table>
        <table width="100%">
           <tr><td>
           <div>
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                <?php echo form_error('<div class="isa_error">','</div>');?>
               <?php
                if((isset($_SESSION['success'])) && ($_SESSION['success'])!=''){
                    echo "<div  class=\"isa_success\">";
                    echo $_SESSION['success'];
                    echo "</div>";
                }
                if((isset($_SESSION['err_message'])) && (($_SESSION['err_message'])!='')){
                    echo "<div  class=\"isa_error\">";
                    echo $_SESSION['err_message'];
                    echo "</div>";
                }
                ;?>
                
            </div>
            </td></tr>
        </table>
         <?php $empid=$this->uri->segment(3);
               $month=$this->uri->segment(4);
               $year=$this->uri->segment(5);
         
          
         ?>
       
            <table border="1" cellpadding=10 width="100%" bgcolor="#95a5a6"><tr><td>  
            <table  cellspacing="10" width="100%" bgcolor="#d5dbdb"> 
                <form action="<?php echo site_url('setup3/salaryslip/'.$empid.'/'.$month."/".$year);?>" method="POST" enctype="multipart/form-data"> 
                <input type="hidden" name="empid" value="<?php $empid; ?>">
                <input type="hidden" name="month" value="<?php $month; ?>">
                <input type="hidden" name="year" value="<?php echo $year; ?>">
               
                <tr>
                    <td><b>Name:</b><?php echo $this->sismodel->get_listspfic1('employee_master','emp_name','emp_id',$empid)->emp_name; ?></td>
                    <td><b>PF No:</b><?php echo $this->sismodel->get_listspfic1('employee_master','emp_code','emp_id',$empid)->emp_code; ?></td>
                    <td colspan="1"><b>Month/Year:</b><?php echo strtoupper($this->uri->segment(4)).'/'.$year;?></td>
                </tr>
                <tr>
                    <td><b>Campus:</b><?php $scid=$this->sismodel->get_listspfic1('employee_master','emp_scid','emp_id',$empid)->emp_scid;
                    echo $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$scid)->sc_name;?></td>
                    <td><b>UO Control:</b><?php $uocid=$this->sismodel->get_listspfic1('employee_master','emp_uocid','emp_id',$empid)->emp_uocid;
                    echo $this->lgnmodel->get_listspfic1('authorities','name','id',$uocid)->name;?></td>
                    <td colspan="1"><b>Department:</b><?php $deptid=$this->sismodel->get_listspfic1('employee_master','emp_dept_code','emp_id',$empid)->emp_dept_code;
                    echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$deptid)->dept_name;?></td>
                </tr>
                <tr>
                    <td><b>Scheme:</b><?php $shmid=$this->sismodel->get_listspfic1('employee_master','emp_schemeid','emp_id',$empid)->emp_schemeid;
                    echo $this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$shmid)->sd_name;?></td>
                    <td><b>DDO:</b><?php $ddoid=$this->sismodel->get_listspfic1('employee_master','emp_ddoid','emp_id',$empid)->emp_ddoid;
                    echo $this->sismodel->get_listspfic1('ddo','ddo_name','ddo_id',$ddoid)->ddo_name;?></td>
                    <td colspan="1"><b>Designation:</b><?php $deptid=$this->sismodel->get_listspfic1('employee_master','emp_desig_code','emp_id',$empid)->emp_desig_code;
                    echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$deptid)->desig_name;?></td>
                </tr>
                <tr>
                    <td><b>Bank Ac No:</b><?php echo $this->sismodel->get_listspfic1('employee_master','emp_bank_accno','emp_id',$empid)->emp_bank_accno;?></td>
                    <td><b>Pay Scale:</b><?php $pbid=$this->sismodel->get_listspfic1('employee_master','emp_salary_grade','emp_id',$empid)->emp_salary_grade;
                            $payband=$this->sismodel->get_listspfic1('salary_grade_master','sgm_name','sgm_id',$pbid)->sgm_name;
                            $pay_max=$this->sismodel->get_listspfic1('salary_grade_master','sgm_max','sgm_id',$pbid)->sgm_max;
                            $pay_min=$this->sismodel->get_listspfic1('salary_grade_master','sgm_min','sgm_id',$pbid)->sgm_min;
                            $gardepay=$this->sismodel->get_listspfic1('salary_grade_master','sgm_gradepay','sgm_id',$pbid)->sgm_gradepay;
                            $paycomm=$this->sismodel->get_listspfic1('employee_master','emp_paycomm','emp_id',$empid)->emp_paycomm;
                            echo $payband."(".$pay_min."-".$pay_max.")".$gardepay."/".$paycomm;
                            ;?>
                            
                    </td>
                    <td colspan="1"><b>DOB:</b><?php $dob=$this->sismodel->get_listspfic1('employee_master','emp_dob','emp_id',$empid)->emp_dob;
                        echo date('d-m-Y',strtotime($dob));?></td>
                </tr>
                <tr>
                    <td><b>DOJ:</b><?php $doj=$this->sismodel->get_listspfic1('employee_master','emp_doj','emp_id',$empid)->emp_doj;
                        echo date('d-m-Y',strtotime($doj));?></td>
                    <td><b>DOR:</b><?php $dor=$this->sismodel->get_listspfic1('employee_master','emp_dor','emp_id',$empid)->emp_dor;
                        echo date('d-m-Y',strtotime($dor));?></td>
                    <td colspan="1"><b>working type/Group:</b><?php $worktype=$this->sismodel->get_listspfic1('employee_master','emp_worktype','emp_id',$empid)->emp_worktype;
                        $group=$this->sismodel->get_listspfic1('employee_master','emp_group','emp_id',$empid)->emp_group;
                        $emptype=$this->sismodel->get_listspfic1('employee_master','emp_type_code','emp_id',$empid)->emp_type_code;
                        echo $worktype ."(". $group .") / ".$emptype?></td>
                </tr>
                      
            </table>
            </td></tr>
              <?php
                            $dupdata=array(
                                'sald_empid'                =>$empid,
                                'sald_month'                =>$month,
                                'sald_year'                 =>$year,   
                            );
                            $dupexists=$this->sismodel->isduplicatemore('salary_data',$dupdata);
                    ;?>  
                
            <tr><td>  
               
            <table class="dggh" width="50%" border="1"  heignt="50%" cellpadding=10 style="float:left;border-width: thin;border-spacing: 2px;border-style: none;" bgcolor="#95a5a6"> 
               <?php  $cmonth = date('M');
                    $cmthnum=date("m", strtotime($cmonth));
                    $cyear= date("Y"); 
                    $currentyd=$cyear.$cmthnum;// echo "both==".$currentyd;
                    
                    $selmonth = date("m",strtotime($month));
                    $selyear=$year;
                    $selmonyear=$selyear.$selmonth; //echo "both=3=".$selmonyear."==".$month;
                    if(!$dupexists){
                       // echo "case 1 if dupexists";
                        if($selmonyear < $currentyd){
                          //  echo " case 2 if selmonyear currentyd";
                            echo "<font color=\"red\"><font size=\"4\">Salary data does not exists....for the month ".( $month )."</font>";
                        }
                    else{
                        ?>
                 <tr>
                    <th colspan="2">Earnings</th>
                    
                </tr>
                <tr>
                    <th>Head</th>
                    <th>Amount</th>
                   
                </tr>
                <?php 
                
                        
                      //  echo " case 3 else selmonyear not currentyd===";
                        $nodata=array(
                                'sald_empid'                =>$empid,
                                //'sald_month'                =>$month,
                                'sald_year'                 =>$year,   
                            );
                            $empnodata=$this->sismodel->isduplicatemore('salary_data',$nodata);
                            
                            if(!$empnodata){
                             //   echo " case 4  if empnodata";
                        
                        ?>
               
                <tr>
                    
                   
                    
                    <?php $sumincome=0;$i=0;$j=0;$sumdeduction=0;$finalval=0;
                     
                    foreach($incomes as $incomedata){ ?>
                    
                    <tr>
                    <?php if($incomedata->sh_tnt == $worktype || $incomedata->sh_tnt == 'Common') :?>    
                    <td>
                        
                        <?php  echo $incomedata->sh_name/*"==id==".$incomedata->sh_id */;
                            if(in_array($incomedata->sh_id,$allowedhead)){
                            echo "<font color=\"red\">*</font>";
                        }
                       // }
                        ?>
                    </td>
                    <td><?php 
                           // echo $pbid."---".$incomedata->sh_id ;
                            $selectfield ="shdv_defaultvalue";
                            //$whorder = "shc_id asc";
                            $whdata = array('shdv_paybandid' =>$pbid,'shdv_salheadid' =>$incomedata->sh_id);
                            $headval= $this->sismodel->get_orderlistspficemore('salaryhead_defaultvalue',$selectfield,$whdata,'');        
                           // echo"seema===".$headval[0]->shdv_defaultvalue;
                            ?>
                        <?php if(!empty($headval) && in_array($incomedata->sh_id,$allowedhead)):?>
                        <?php  if($incomedata->sh_calc_type == 'Y'):
                            $formula1=$this->sismodel->get_listspfic1('salary_formula','sf_formula','sf_salhead_id',$incomedata->sh_id);
                            if(!empty($formula1)){
                            $formula=$formula1->sf_formula;
                           // echo "formula inside===".$formula.$incomedata->sh_id.$incomedata->sh_name;
                            preg_match('/(.*)\((.*?)\)(.*)/', $formula, $match);
                            //echo "in parenthesis inside: " . $match[2];
                          //  echo "before and after inside: " . $match[1] . $match[3] . "\n";
                            
                            $strfmla=explode("+",$match[2]);
                            $strfmla2=explode("*",$match[3]);
                            //echo "mjjh===".$strfmla2[1];
                            $sfield ="shdv_defaultvalue";
                            if(!empty($strfmla[0])){
                                $tok1id=$this->sismodel->get_listspfic1('salary_head','sh_id','sh_code',$strfmla[0])->sh_id;
                                $wdata = array('shdv_paybandid' =>$pbid,'shdv_salheadid' =>$tok1id);
                                $headval1= $this->sismodel->get_orderlistspficemore('salaryhead_defaultvalue',$sfield,$wdata,'');  
                                $headval1=$headval1[0]->shdv_defaultvalue;
                            }
                            else{
                                $headval1=0;
                            }
                            if(!empty($strfmla[1])){
                                $tok2id=$this->sismodel->get_listspfic1('salary_head','sh_id','sh_code',$strfmla[1])->sh_id;
                                $wdata = array('shdv_paybandid' =>$pbid,'shdv_salheadid' => $tok2id);
                                $headval2= $this->sismodel->get_orderlistspficemore('salaryhead_defaultvalue',$sfield,$wdata,''); 
                                $headval2=$headval2[0]->shdv_defaultvalue;
                            }
                            else{
                               $headval2=0; 
                            }
                            
                            $rawfor=$headval1 + $headval2 ;
                            //$rawfor=$headval1[0]->shdv_defaultvalue + $headval2[0]->shdv_defaultvalue ;
                            $finalval=$rawfor * $strfmla2[1];
                            }
                            else{
                                
                                $sfbp="shdv_defaultvalue";
                                $wdatabp = array('shdv_paybandid'=>$pbid,'shdv_salheadid' =>1);
                                $headbp= $this->sismodel->get_orderlistspficemore('salaryhead_defaultvalue',$sfbp,$wdatabp,'');
                                $bpamt=$headbp[0]->shdv_defaultvalue;
                                                              
                                
                                $ccaid=$this->sismodel->get_listspfic1('salary_head','sh_id','sh_code','CCA')->sh_id;
                                $hraid=$this->sismodel->get_listspfic1('salary_head','sh_id','sh_code','HRA')->sh_id;
                                if($incomedata->sh_id == $ccaid || $incomedata->sh_id == $hraid){
                                    if($incomedata->sh_id == $ccaid){
                                        $ccaamt=$this->sismodel->getcca_amount($bpamt,$paycomm);
                                     //   echo "====in vmbpamt===".$bpamt."=====in vmccaamt===".$ccaamt[0];
                                        
                                        $ccagrade=$this->sismodel->get_listspfic1('employee_master_support','ems_ccagrade','ems_empid',$empid);
                                        if(!empty($ccagrade)){
                                            
                                            $ccagrade= $ccagrade->ems_ccagrade;
                                            $sfield="cca_amount";
                                            $wdata = array('cca_payrange'=>$ccaamt[0],'cca_paycomm' =>$paycomm,'cca_gradeid' =>$ccagrade);
                                           // $wdata = array('cca_payscaleid' =>$pbid,'cca_workingtype' =>$worktype,'cca_gradeid' =>$ccagrade);
                                           // echo $pbid,$worktype,$ccagrade;
                                            $headvalc= $this->sismodel->get_orderlistspficemore('ccaallowance_calculation',$sfield,$wdata,'');  
                                            if(!empty($headvalc)){
                                                $headvalcca=$headvalc[0]->cca_amount;
                                            
                                            //echo"seema==".$headvalcca;
                                            //get cca grade from payrollprofile
                                                //$ccgrade=$this->sismodel->get_listspfic1('employee_master_support','ems_ccagrade','ems_empid',$empid)->ems_ccagrade;
                                            $finalval=$headvalcca;
                                            }
                                            else{
                                                $finalval=0;
                                            }
                                        }
                                        else{
                                            $finalval=0;
                                        }
                                    }
                                    if($incomedata->sh_id == $hraid){
                                        $rfhragrade=$this->sismodel->get_listspfic1('employee_master_support','ems_erfq','ems_empid',$empid);
                                        if(!empty($rfhragrade)&&($rfhragrade->ems_erfq == 'yes')){
                                            
                                            $hragrade=$this->sismodel->get_listspfic1('employee_master_support','ems_erfqhra','ems_empid',$empid);
                                        }
                                        else{
                                            $hragrade=$this->sismodel->get_listspfic1('employee_master_support','ems_hragrade','ems_empid',$empid);                     
                                        }
                                        $hraamt=$this->sismodel->gethra_amount($bpamt,$paycomm);
                                       // echo "====in vmbpamt===".$bpamt."=====in vmccaamt===".$hraamt[0];
                                        
                                        if(!empty($hragrade) || !empty($rfhragrade)){
                                            if($rfhragrade->ems_erfq == 'yes'){
                                                $hragrade= $hragrade->ems_erfqhra;   
                                                
                                                $sfield="rfh_amount";
                                                $wdata = array('rfh_payrange'=>$hraamt[0],'rfh_paycomm' =>$paycomm,'rfh_gradeid' =>$hragrade);
                                                // $wdata = array('hg_payscaleid' =>$pbid,'hg_workingtype' =>$worktype,'hg_gradeid' =>$hragrade);
                                                $headvalh= $this->sismodel->get_orderlistspficemore('rent_free_hra',$sfield,$wdata,'');  
                                                if(!empty($headvalh)){
                                                    $headvalhra=$headvalh[0]->rfh_amount;
                                                    $finalval=$headvalhra; 
                                                  //  echo "seemain hra". $finalval;
                                                }
                                                else{
                                                    $finalval=0;    
                                                }
                                            }
                                            else{
                                                $hragrade= $hragrade->ems_hragrade;
                                                
                                                $sfield="hg_amount";
                                                $wdata = array('hg_payrange'=>$hraamt[0],'hg_paycomm' =>$paycomm,'hg_gradeid' =>$hragrade);
                                                // $wdata = array('hg_payscaleid' =>$pbid,'hg_workingtype' =>$worktype,'hg_gradeid' =>$hragrade);
                                                $headvalh= $this->sismodel->get_orderlistspficemore('hra_grade',$sfield,$wdata,'');  
                                                if(!empty($headvalh)){
                                                    $headvalhra=$headvalh[0]->hg_amount;
                                                    $finalval=$headvalhra; 
                                                   // echo "seemain hra". $finalval;
                                                }
                                                else{
                                                    $finalval=0;    
                                                }
                                            }
                                           
                                        }
                                        else{
                                            $finalval=0;
                                        }
                                    
                                    }
                                }
                                else{
                                    $finalval=0;
                                }    
                            }
                            
                            
                        ?>
                        
                        <input type="text"  class="headamtI" name="headamtI<?php echo $i;?>" id="headamtI<?php echo $i;?>"  value="<?php echo $finalval;  ?>" >
                        <?php  $sumincome+=$finalval; // endif;?> 
                        <?php else :?>
                        <?php // endif; ?>
                        <?php $finalval=$headval[0]->shdv_defaultvalue; // if($incomedata->sh_calc_type == 'N' && $headval[0]->shdv_defaultvalue != 0):?>
                        <input type="text" class="headamtI" name="headamtI<?php echo $i;?>" id="headamtI<?php echo $i;?>"  value="<?php  echo $finalval;  ?>" >
                        <?php // $sumincome+=$headval[0]->shdv_defaultvalue;
                             $sumincome+=$finalval;
                        ?>
                        <?php endif;?>
                    <?php else :
                    // if($incomedata->sh_calc_type == 'N' && $headval[0]->shdv_defaultvalue == 0): ?>
                        <input type="text" class="headamtI" name="headamtI<?php echo $i;?>" id="headamtI"  value="<?php echo 0; ?>" >    
                    <?php // $sumincome+=$finalval; // endif;?> 
                    <?php endif;?>   
                    
                    </td>
                     <?php endif;?>  
                     </tr> 
                    <input type="hidden" name="sheadidin<?php echo $i;?>" value="<?php echo $incomedata->sh_id ; ?>">
                      <?php  //$sumincome+=$finalval; // endif;?> 
                    <?php  $i++; }?>
                    
                    <tr><td><b>Total Earning </b></td><td> <input type="text" id="Tincome" value="<?php echo $sumincome;?>" size="10" readonly></span></td></tr> 
                </table>
                
                <table class="dggh" border=1 width="50%" heignt="50%" cellpadding=10 style="float:right;border-width: thin;border-spacing: 2px;border-style: none;" bgcolor="#95a5a6">     
                    
                   <tr>
                    <th colspan="2">Deductions</th>
                    </tr>
                <tr>
                    <th>Head</th>
                    <th>Amount</th>
                   
                </tr>
                <tr>
                    
                    
                     <?php foreach($deduction as $deductdata){ ?>
                   
                        <tr>
                         <?php if($deductdata->sh_tnt == $worktype || $deductdata->sh_tnt == 'Common') :?>       
                        <td><?php echo $deductdata->sh_name/*."=for==".$deductdata->sh_id*/;
                            if(in_array($deductdata->sh_id,$allowedhead)){
                            echo "<font color=\"red\">*</font>" ;
                        }
                        ?>
                        </td>
                        <td>
                            <?php 
                           
                            ?>
                            <?php if(!empty($headval) && in_array($deductdata->sh_id,$allowedhead)): //echo "in allowed case";?>
                            
                            <?php  if($deductdata->sh_calc_type == 'Y'): 
                            $formula1=$this->sismodel->get_listspfic1('salary_formula','sf_formula','sf_salhead_id',$deductdata->sh_id);
                            if(!empty($formula1)){
                               // echo "in formula case";
                            $formula=$formula1->sf_formula;
                          //  echo "formula inside===".$formula.$deductdata->sh_id.$deductdata->sh_name;
                            preg_match('/(.*)\((.*?)\)(.*)/', $formula, $match);
                            //echo "in parenthesis inside: " . $match[2];
                            //echo "before and after inside: " . $match[1] . $match[3] . "\n";
                            
                            $strfmla=explode("+",$match[2]);
                            $strfmla2=explode("*",$match[3]);
                           //echo "===token==0==".$strfmla[0]."===token2==".$strfmla[1]."===mjjh===".$strfmla2[1];
                            $sfield ="shdv_defaultvalue";
                            if(!empty($strfmla[0])){
                                $tok1id=$this->sismodel->get_listspfic1('salary_head','sh_id','sh_code',$strfmla[0])->sh_id;
                                $wdata = array('shdv_paybandid' =>$pbid,'shdv_salheadid' =>$tok1id);
                                $headval1= $this->sismodel->get_orderlistspficemore('salaryhead_defaultvalue',$sfield,$wdata,'');
                                $headval1=$headval1[0]->shdv_defaultvalue;
                              //  echo "hval1inside===".$headval1. "\n";
                            }
                            else{
                                $headval1=0;
                            }
                            if(!empty($strfmla[1])){
                                $tok2id=$this->sismodel->get_listspfic1('salary_head','sh_id','sh_code',$strfmla[1])->sh_id;
                               // echo "tokwen2----".$tok2id;
                                $wdata = array('shdv_paybandid' =>$pbid,'shdv_salheadid' => $tok2id);
                                $headval2= $this->sismodel->get_orderlistspficemore('salaryhead_defaultvalue',$sfield,$wdata,''); 
                                $headval2=$headval2[0]->shdv_defaultvalue;
                                //echo "hval2inside===".$headval2. "\n";
                            }
                            else{
                               $headval2=0; 
                            }
                            
                            $rawfor=$headval1 + $headval2 ;
                           // echo "rawval===".$rawfor. "\n";
                            //echo "==val1===".$headval1."==val2===".$headval2. "\n";
                            $finalval=$rawfor * $strfmla2[1]. "\n";
                           // echo "finalval==".$finalval;
                            }
                            else{
                                //echo "==in  without formula==";
                                /********write code for the rent recovery if occupied quarter*************/
                                $rentid=$this->sismodel->get_listspfic1('salary_head','sh_id','sh_code','Rent')->sh_id; 
                               // echo "rentid==".$rentid; 
                                if($deductdata->sh_id == $rentid){
                                    //echo "in yes 1part==";
                                    $qtrocc=$this->sismodel->get_listspfic1('employee_master_support','ems_qoccupai','ems_empid',$empid);
                                    if(!empty($qtrocc) && $qtrocc->ems_qoccupai == 'yes'){
                                       // echo "in yes part=2=";
                                        $rentgrade=$this->sismodel->get_listspfic1('employee_master_support','ems_rentgrade','ems_empid',$empid);
                                        if(!empty($rentgrade)){ 
                                           // echo "in yes part=3=";
                                            $rentgradeid=$rentgrade->ems_rentgrade;
                                        
                                            $sfbp="shdv_defaultvalue";
                                            $wdatabp = array('shdv_paybandid'=>$pbid,'shdv_salheadid' =>1);
                                            $headbp= $this->sismodel->get_orderlistspficemore('salaryhead_defaultvalue',$sfbp,$wdatabp,'');
                                            $bpamt=$headbp[0]->shdv_defaultvalue;
                                            $hraamt=$this->sismodel->gethraper_amount($bpamt,$paycomm);
                                        
                                            $sfield="rr_percentage";
                                            $wdata = array('rr_payrange'=>$hraamt[0],'rr_paycomm' =>$paycomm,'rr_gradeid' =>$rentgradeid);
                                            $headvalh= $this->sismodel->get_orderlistspficemore('rent_recovery',$sfield,$wdata,'');  
                                            if(!empty($headvalh)){
                                                $rentrper=$headvalh[0]->rr_percentage;
                                            
                                            
                                                $rawrrcal=$bpamt*$rentrper;
                                                $headvalhra=$rawrrcal;
                                                //get cca grade from payrollprofile
                                                $finalval=$headvalhra; 
                                            }
                                            else{
                                                $finalval=0;  
                                            }
                                        }
                                        else{
                                            $finalval=0;   
                                        }
                                    }
                                    else{
                                        $finalval=0;
                                    }
                                                                        
                                } //ifrentrecoveery    
                                /**************************************************************************/
                                else{
                                   // echo "==in  without formula== last part";
                                    $finalval=0;
                                }
                            }
                            
                            
                        ?>
                        
                        <input type="text" class="headamtD" name="headamtD<?php echo $j;?>" id="headamtD<?php echo $j;?>"  value="<?php echo $finalval;  ?>" >
                        <?php  $sumdeduction+=$finalval; // endif;?> 
                        <?php else : //echo "in  without dowm11 formula=="?>
                            <?php $finalval=$headval[0]->shdv_defaultvalue;;?>
                            <input type="text"  class="headamtD" name="headamtD<?php echo $j;?>" id="headamtD<?php echo $j;?>"  value="<?php echo $finalval;  ?>" >
                            <?php $sumdeduction+=$finalval; //$sumdeduction+=$headval[0]->shdv_defaultvalue;?>
                        <?php endif;?>    
                            <?php else : //secho "in  without dowm12 formula=not allowed case="?>
                            
                            <input type="text"  class="headamtD" name="headamtD<?php echo $j;?>" id="headamtD" value="<?php echo 0; ?>" >
                            
                            <?php endif;?>
                        </td>
                        <?php endif;?>
                        </tr>
                          <input type="hidden" name="sheadidded<?php echo $j;?>" value="<?php echo $deductdata->sh_id ; ?>">   
                    <?php $j++; };?>
                        
                            <?php  $licprdpli = array(
                            "LIC1" => "LIC1", "LIC2" => "LIC2", "LIC3" => "LIC3", "LIC4" => "LIC4",
                            "LIC5" => "LIC5", "PRD1" => "PRD1", "PRD2" => "PRD2", "PRD3" => "PRD3",
                            "PLI1" => "PLI1", "PLI2" => "PLI2",);?>
                        
                            <?php foreach ($licprdpli as $lpdpi) {?>
                                <tr><td><?php echo $lpdpi ?></td>
                                <td><input type="text"  class="headamtD" name="headamtD<?php echo $j;?>"  id="headamtD"value="<?php echo 0  ?>" ></td></tr>   
                                <input type="hidden" name="sheadidded<?php echo $j;?>" value="<?php echo $lpdpi; ?>">   
                          <?php $j++; };?>
                                     
                        <tr><td><b>Total Deduction:</b></td><td><input type="text" id="Tdeduction" value="<?php echo $sumdeduction;?>" size="10" readonly></td></tr>  
                </tr> 
                </table>
                <!--<tr><td><b>Net Pay:</b><span id="netPay"> <?php // echo $sum_total = $sumincome - $sumdeduction;?></span> -->
                <tr><td><b>Net Pay:</b><input type="text" id="netPay" value="<?php echo $sum_total = $sumincome - $sumdeduction;?>" readonly >
                        <input type="hidden" name="incometotal" value="<?php echo $sumincome;?>">  
                        <input type="hidden" name="deductiontotal" value="<?php echo $sumdeduction;?>">
                        <input type="hidden" name="netpay" value="<?php echo $sum_total;?>" >
                 <input type="hidden" name="totalcount" id="tcount" value="<?php echo $i;?>">   
                 <input type="hidden" name="totalded" id="tcount" value="<?php echo $j;?>"> 
                <?php }  //closer if employee not exists in that year -->
                    else{ ?>
                        <?php //echo " case 5 else empnodata"; //max value part ;?>
                        <?php $sumincome=0;$i=0;$j=0;$sumdeduction=0;$finalval=0;
                    
                        foreach($incomes as $incomedata){ ?>
                        <tr>
                            <?php if($incomedata->sh_tnt == $worktype || $incomedata->sh_tnt == 'Common') :?>    
                            <td>
                        
                                <?php  echo $incomedata->sh_name/*."==id==".$incomedata->sh_id*/ ;
                                    if(in_array($incomedata->sh_id,$allowedhead)){
                                    echo "<font color=\"red\">*</font>";
                                }
                     
                                ?>
                            </td>
                            <td><?php 
                          
                                $selectfield ="sald_shamount";
                                //$whorder = "shc_id asc";
                                $whdata = array('sald_empid' =>$empid,'sald_sheadid' =>$incomedata->sh_id);
                                $headval= $this->sismodel->get_maxvalue('salary_data',$selectfield,$whdata);  
                               // $whdata = array('sald_empid' =>$empid,'sald_sheadid' =>$incomedata->sh_id,'sald_month'=>$month,'sald_year'=>$year);
                                //$headval= $this->sismodel->get_orderlistspficemore('salary_data',$selectfield,$whdata,''); 
                          
                                ?>
                                <?php if(!empty($headval) && in_array($incomedata->sh_id,$allowedhead)):?>
                                <?php $finalval=$headval[0]->sald_shamount;?>      
                                <input type="text" class="headamtI" name="headamtI<?php echo $i;?>" id="headamtI<?php echo $i;?>"  value="<?php  echo $finalval;  ?>" >
                                <?php $sumincome+=$finalval;?>
                                
                                <?php else : ?>
                                <input type="text" class="headamtI" name="headamtI<?php echo $i;?>" id="headamtI"  value="<?php echo 0; ?>" >    
                                <?php endif;?>
                            </td>
                            <?php endif;?> 
                     </tr> 
                    <input type="hidden" name="sheadidin<?php echo $i;?>" value="<?php echo $incomedata->sh_id ; ?>">
                    <?php  $i++; };?>
                    <tr><td><b>Total Earning </b></td><td> <input type="text" id="Tincome" value="<?php echo $sumincome;?>" size="10" readonly></span></td></tr>    
                    <table class="dggh" border=1 width="50%" heignt="50%" cellpadding=10 style="float:right;border-width: thin;border-spacing: 2px;border-style: none;" bgcolor=" #95a5a6">     
                    
                   <tr>
                    <th colspan="2">Deductions</th>
                    </tr>
                <tr>
                    <th>Head</th>
                    <th>Amount</th>
                   
                </tr>
                <tr>
                    
                    
                     <?php foreach($deduction as $deductdata){ ?>
                   
                        <tr>
                         <?php if($deductdata->sh_tnt == $worktype || $deductdata->sh_tnt == 'Common') :?>       
                        <td><?php echo $deductdata->sh_name/*."=for==".$deductdata->sh_id*/ ;
                            if(in_array($deductdata->sh_id,$allowedhead)){
                            echo "<font color=\"red\">*</font>" ;
                        }
                        ?>
                        </td>
                        <td>
                            <?php 
                                $selectfield ="sald_shamount";
                                //$whorder = "shc_id asc";
                                $whdata = array('sald_empid' =>$empid,'sald_sheadid' =>$deductdata->sh_id);
                                $headval= $this->sismodel->get_maxvalue('salary_data',$selectfield,$whdata);        
                            ?>
                            <?php if(!empty($headval) && in_array($deductdata->sh_id,$allowedhead)):?>
                            <?php $finalval=$headval[0]->sald_shamount;?>   
                            <input type="text"  class="headamtD" name="headamtD<?php echo $j;?>" id="headamtD<?php echo $j;?>"  value="<?php echo $finalval;  ?>" >
                            <?php $sumdeduction+=$finalval; ?>
                            <?php else : ?>
                            <input type="text"  class="headamtD" name="headamtD<?php echo $j;?>" id="headamtD" value="<?php echo 0; ?>" >
                            <?php endif;?>
                        </td>
                        <?php endif;?>
                        </tr>
                          <input type="hidden" name="sheadidded<?php echo $j;?>" value="<?php echo $deductdata->sh_id ; ?>">   
                    <?php $j++; };?>
                        
                            <?php  $licprdpli = array(
                            "LIC1" => "LIC1", "LIC2" => "LIC2", "LIC3" => "LIC3", "LIC4" => "LIC4",
                            "LIC5" => "LIC5", "PRD1" => "PRD1", "PRD2" => "PRD2", "PRD3" => "PRD3",
                            "PLI1" => "PLI1", "PLI2" => "PLI2",);?>
                        
                            <?php foreach ($licprdpli as $lpdpi) {?>
                                <tr><td><?php echo $lpdpi ?></td>
                                <?php 
                                    $selectfield ="sald_shamount";
                                    $whdata = array('sald_empid' =>$empid,'sald_sheadid' =>$lpdpi);
                                    $headval= $this->sismodel->get_maxvalue('salary_data',$selectfield,$whdata);
                                    $finalval=$headval[0]->sald_shamount;
                                                                   
                                ;?>    
                                <td><input type="text"  class="headamtD" name="headamtD<?php echo $j;?>"  id="headamtD"value="<?php echo $finalval ?>" ></td></tr>   
                                <?php $sumdeduction+=$finalval; ?>
                                <input type="hidden" name="sheadidded<?php echo $j;?>" value="<?php echo $lpdpi; ?>">   
                          <?php $j++; };?>
                                     
                        <tr><td><b>Total Deduction:</b></td><td><input type="text" id="Tdeduction" value="<?php echo $sumdeduction;?>" size="10" readonly></td></tr>  
                </tr> 
                </table>
                <!--<tr><td><b>Net Pay:</b><span id="netPay"> <?php // echo $sum_total = $sumincome - $sumdeduction;?></span> -->
                <tr><td><b>Net Pay:</b><input type="text" id="netPay" value="<?php echo $sum_total = $sumincome - $sumdeduction;?>" readonly >
                        <input type="hidden" name="incometotal" value="<?php echo $sumincome;?>">  
                        <input type="hidden" name="deductiontotal" value="<?php echo $sumdeduction;?>">
                        <input type="hidden" name="netpay" value="<?php echo $sum_total;?>" >
                 <input type="hidden" name="totalcount" id="tcount" value="<?php echo $i;?>">   
                 <input type="hidden" name="totalded" id="tcount" value="<?php echo $j;?>"> 
                 
                    <?php };?>
                 <?php  //closer max value part ;?>
                <?php  } /*closer of else year compare(<>) */ ;?> 
                <?php } //closer of if dup not exists  -->
                else{ //echo "else dupexistss" ?>
                             
                 <tr>
                    <th colspan="2">Earnings </th>
                   
                </tr>
                
                <tr>
                    <th>Head</th>
                    <th>Amount</th>
                   
                </tr>
                
                   
                        <?php $sumincome=0;$i=0;$j=0;$sumdeduction=0;$finalval=0;
                    
                        foreach($incomes as $incomedata){ ?>
                        <tr>
                            <?php if($incomedata->sh_tnt == $worktype || $incomedata->sh_tnt == 'Common') :?>    
                            <td>
                        
                                <?php  echo $incomedata->sh_name/*."==id==".$incomedata->sh_id*/ ;
                                    if(in_array($incomedata->sh_id,$allowedhead)){
                                    echo "<font color=\"red\">*</font>";
                                }
                     
                                ?>
                            </td>
                            <td><?php 
                          
                                $selectfield ="sald_shamount";
                                //$whorder = "shc_id asc";
                                //$whdata = array('sald_empid' =>$empid,'sald_sheadid' =>$incomedata->sh_id);
                               // $headval= $this->sismodel->get_maxvalue('salary_data',$selectfield,$whdata);  
                                $whdata = array('sald_empid' =>$empid,'sald_sheadid' =>$incomedata->sh_id,'sald_month'=>$month,'sald_year'=>$year);
                                $headval= $this->sismodel->get_orderlistspficemore('salary_data',$selectfield,$whdata,''); 
                          
                                ?>
                                <?php if(!empty($headval) && in_array($incomedata->sh_id,$allowedhead)):?>
                                <?php $finalval=$headval[0]->sald_shamount;?>      
                                <input type="text" class="headamtI" name="headamtI<?php echo $i;?>" id="headamtI<?php echo $i;?>"  value="<?php  echo $finalval;  ?>" readonly >
                                <?php $sumincome+=$finalval;?>
                                
                                <?php else : ?>
                                <input type="text" class="headamtI" name="headamtI<?php echo $i;?>" id="headamtI"  value="<?php echo 0; ?>" readonly>    
                                <?php endif;?>
                            </td>
                            <?php endif;?> 
                     </tr> 
                    <input type="hidden" name="sheadidin<?php echo $i;?>" value="<?php echo $incomedata->sh_id ; ?>">
                    <?php  $i++; };?>
                    <tr><td><b>Total Earning </b></td><td> <input type="text" id="Tincome" value="<?php echo $sumincome;?>" size="10" readonly></span></td></tr>    
                    <table  class="dggh" border=1 width="50%" heignt="50%" cellpadding=10 style="float:right;border-width: thin;border-spacing: 2px;border-style: none;" bgcolor=" #95a5a6">     
                    
                   <tr>
                    <th colspan="2">Deductions</th>
                    </tr>
                <tr>
                    <th>Head</th>
                    <th>Amount</th>
                   
                </tr>
                <tr>
                    
                    
                     <?php foreach($deduction as $deductdata){ ?>
                   
                        <tr>
                         <?php if($deductdata->sh_tnt == $worktype || $deductdata->sh_tnt == 'Common') :?>       
                        <td><?php echo $deductdata->sh_name/*."=for==".$deductdata->sh_id*/ ;
                            if(in_array($deductdata->sh_id,$allowedhead)){
                            echo "<font color=\"red\">*</font>" ;
                        }
                        ?>
                        </td>
                        <td>
                            <?php 
                                $selectfield ="sald_shamount";
                                //$whorder = "shc_id asc";
                                $whdata = array('sald_empid' =>$empid,'sald_sheadid' =>$deductdata->sh_id,'sald_month'=>$month,'sald_year'=>$year);
                                $headval= $this->sismodel->get_orderlistspficemore('salary_data',$selectfield,$whdata,'');        
                            ?>
                            <?php if(!empty($headval) && in_array($deductdata->sh_id,$allowedhead)):?>
                            <?php $finalval=$headval[0]->sald_shamount;?>   
                            <input type="text"  class="headamtD" name="headamtD<?php echo $j;?>" id="headamtD<?php echo $j;?>"  value="<?php echo $finalval;  ?>" readonly>
                            <?php $sumdeduction+=$finalval; ?>
                            <?php else : ?>
                            <input type="text"  class="headamtD" name="headamtD<?php echo $j;?>" id="headamtD" value="<?php echo 0; ?>" readonly>
                            <?php endif;?>
                        </td>
                        <?php endif;?>
                        </tr>
                          <input type="hidden" name="sheadidded<?php echo $j;?>" value="<?php echo $deductdata->sh_id ; ?>">   
                    <?php $j++; };?>
                        
                            <?php  $licprdpli = array(
                            "LIC1" => "LIC1", "LIC2" => "LIC2", "LIC3" => "LIC3", "LIC4" => "LIC4",
                            "LIC5" => "LIC5", "PRD1" => "PRD1", "PRD2" => "PRD2", "PRD3" => "PRD3",
                            "PLI1" => "PLI1", "PLI2" => "PLI2",);?>
                        
                            <?php foreach ($licprdpli as $lpdpi) {?>
                                <tr><td><?php echo $lpdpi ?></td>
                                <?php 
                                    $selectfield ="sald_shamount";
                                    $whdata = array('sald_empid' =>$empid,'sald_sheadid' =>$lpdpi,'sald_month'=>$month,'sald_year'=>$year);
                                    $headval= $this->sismodel->get_orderlistspficemore('salary_data',$selectfield,$whdata,'');
                                    $finalval=$headval[0]->sald_shamount;
                                                                   
                                ;?>    
                                <td><input type="text"  class="headamtD" name="headamtD<?php echo $j;?>"  id="headamtD"value="<?php echo $finalval ?>" readonly></td></tr>   
                                <?php $sumdeduction+=$finalval; ?>
                                <input type="hidden" name="sheadidded<?php echo $j;?>" value="<?php echo $lpdpi; ?>">   
                          <?php $j++; };?>
                                     
                        <tr><td><b>Total Deduction:</b></td><td><input type="text" id="Tdeduction" value="<?php echo $sumdeduction;?>" size="10" readonly></td></tr>  
                </tr> 
                </table>
                <!--<tr><td><b>Net Pay:</b><span id="netPay"> <?php // echo $sum_total = $sumincome - $sumdeduction;?></span> -->
                <tr><td><b>Net Pay:</b><input type="text" id="netPay" value="<?php echo $sum_total = $sumincome - $sumdeduction;?>" readonly >
                        <input type="hidden" name="incometotal" value="<?php echo $sumincome;?>">  
                        <input type="hidden" name="deductiontotal" value="<?php echo $sumdeduction;?>">
                        <input type="hidden" name="netpay" value="<?php echo $sum_total;?>" >
                 <input type="hidden" name="totalcount" id="tcount" value="<?php echo $i;?>">   
                 <input type="hidden" name="totalded" id="tcount" value="<?php echo $j;?>">    
                <?php } ;?>
               
                <button name="upsalhdval" id="btnUpload" style="align:right" onclick="return confirm('Are you sure you want to process salary?');">Update</button></span></td>   
                </tr> 
                </td></tr>
            </table>                      
        </form>    
    </body>    
</html>
