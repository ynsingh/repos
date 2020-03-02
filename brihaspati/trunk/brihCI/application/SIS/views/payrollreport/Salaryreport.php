<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name Salary Report.php 
  @author Nagendra Kumar Singh(nksinghiitk@gmail.com)
  @author Manorama Pal (palseema30@gmail.com)  
 -->
<html>
<title>View Salary Report</title>
    <head>   
        <style>
        
        </style>
        
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
	<script>
           function verify(){
                var year=document.getElementById("year").value;
                var month=document.getElementById("month").value;
                if((year == " " && month ==" " )){
                    alert("please select month and year for load the salary !!");
                    return false;
                };
            }  
        </script>
    </head>
    <body>
        <?php $this->load->view('template/header'); ?>
	  <table width="100%"><tr colspan="2">
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
        </tr></table>
		 <?php
                    //set the month array
                    $cmonth= date('M');
                    $formattedMonthArray = array(
                         "1" => "Jan", "2" => "Feb", "3" => "Mar", "4" => "Apr",
                         "5" => "May", "6" => "Jun", "7" => "Jul", "8" => "Aug",
                        "9" => "Sep", "10" => "Oct", "11" => "Nov", "12" => "Dec",
                    );
                    // set start and end year range
                    $cyear= date("Y");
                    $yearArray = range(2015,  $cyear);
                ?>

		<table width="100%" border="0">
                <form action="<?php echo site_url('payrollreport/salaryslipreport');?>" method="POST" enctype="multipart/form-data">
                <tr style="font-weight:bold;" >
                   <td> Select Department<br>
                        <select name="dept" id="dept" style="width:250px;" required>
                            <?php
                                if(!empty($deptsel)){ ?>
                            <option value="<?php echo "";?>"><?php echo $deptsel;?></option>
<?php                           }else{ ?>
                            <option value="<?php echo "";?>"><?php echo "Please select department";?></option>
<?php                           }
                                foreach ($combdata as $rec) {
                                    echo '<option  value="'.$rec->dept_id.'">'.$rec->dept_name.'</option>';
                                }
                            ?>
                        </select>

                   </td>
                    <td>Select Month <br>
                        <select name="month" id="month" style="width:250px;">
                            <?php if($selmonth!=$cmonth && (!empty($selmonth))):;?>
                            <option value="<?php echo $selmonth;?>"><?php echo $selmonth;?></option>
                            <?php else:;?>
                            <option value="<?php echo $selmonth;?>"><?php echo $selmonth;?></option> 
                            <?php endif;?>
                            <?php
                                foreach ($formattedMonthArray as $month) {
                                    echo '<option  value="'.$month.'">'.$month.'</option>';
                                }
                            ?>

                        </select>

                    </td>
		 <td>Select Year</br>


                        <select name="year" id="year" style="width:250px;" >
                            <?php if($selyear!=$cyear):;?>
                            <option value="<?php echo $selyear;?>"><?php echo $selyear;?></option>
                            <?php else:;?>
                            <option value="<?php echo $selyear;?>"><?php echo $selyear;?></option> 
                            <?php endif;?>
                            <?php
                                foreach ($yearArray as $year) {
                                    echo '<option value="'.$year.'">'.$year.'</option>';
                                }
                            ?>
                        </select>
                  </td>

                   <td>
                        <input type="submit" name="bulksal" id="bulksal" value="Search"/>

                    </td>
             </form>                          
                    <form action="<?php echo site_url('payrollreport/salaryslipreport/pdf/'.$deptid.'/'.$selyear.'/'.$selmonth); 
			?>">
               <td> <input type="submit" name="bulksal" value="" style="width:25px; height:25px;float:right;padding:2px; margin-right:10px;background-image:url('<?php echo base_url(); ?>assets/sis/images/pdf.jpeg')" title="Click for pdf"></td>
             </form>
                    
                    <!--<td><?php // echo anchor("payrollreport/salaryslipreport/pdf/".$deptid."/".$selyear."/".$selmonth,img(array('src'=>'assets/sis/images/pdf.jpeg','border'=>'1px','alt'=>'view ')),array('title' => 'save salary slip' , 'class' => 'red-link'));?></td> -->
                </tr>
	</table>
      
        <?php if(!empty($deptid)){
        
        // for($i=0;$i<count($totalemp);$i+=2) {   
            
        for($i=0;$i<count($totalemp);$i++) {
        
        ?>
     
    
	<table style="width:100%;margin-top:10px;">
            <tr>
                <th>
            <center style="font-size:12;">PAYBILL FOR THE MONTH OF <b><?php 
            $month= $selmonth;
            $year= $selyear;
            
            echo date("F",strtotime($month)).", ".$year;?></b></center>
                </th>
            </tr>
        </table>
        <table style="width:100%;" class="TFtable">
            <?php // if($countno == 1){ ?>
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
		if(!empty($ddoid)){
                    $ddoname=$this->sismodel->get_listspfic1('ddo','ddo_name','ddo_id',$ddoid)->ddo_name; 
			}else{
				$ddoname="";
			}
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
              
                    echo "<tr><td style=font-size:12;> <b>Emp.NO:</b></td><td style=font-size:12;><b>".$empcode."</b></td>";
                    echo "<td style=font-size:12;> <b>Grade:</b></td><td style=font-size:12;><b>".$group."</b></td>";
                    echo "<td style=font-size:12;> <b>DDO:</b></td><td style=font-size:12;><b>".$ddoname."</b></td></tr>";
                    
                    echo "<tr><td style=font-size:12;><b> Name:</b></td><td style=font-size:12;><b>".$empname."</b></td>";
                    echo "<td style=font-size:12;> <b>DOB:</b></td><td style=font-size:12;><b>".date('d-m-Y',strtotime($dob))."</b></td>";
                    echo "<td style=font-size:12;> <b>Days Paid:</b></td><td style=font-size:12;><b>"."0"."</b></td></tr>";
                    
                     echo "<tr><td style=font-size:12;> <b>Pay Scale:</b></td><td style=font-size:12;><b>".$payscale.":".$paycomm."</b></td>";
                    echo "<td style=font-size:12;> <b>DOJ:</b></td><td style=font-size:12;><b>".date('d-m-Y',strtotime($doj))."</b></td>";
                    echo "<td style=font-size:12;> <b>Bank A/c:</b></td><td style=font-size:12;><b>".$bkacc."</td></tr>";
                    
                    echo "<tr><td style=font-size:12;> <b>Designation:</b></td><td style=font-size:12;><b>".$designame."&nbsp;&nbsp;".$desigcode."</b></td>";
                    echo "<td style=font-size:12;> <b>DOR:</b></td><td style=font-size:12;><b>".date('d-m-Y',strtotime($dor))."</b></td>";
                    echo "<td style=font-size:12;> <b>Bank :</b></td><td style=font-size:12;><b>".$bkname."</b></td></tr>";
                    
                    
                    echo "<tr><td style=font-size:12;> <b>Department:</b></td><td style=font-size:12;><b>".$deptname."&nbsp;&nbsp;".$deptcode."</b></b></td>";
                    echo "<td style=font-size:12;> <b>UPF.NO:</b></td><td style=font-size:12;><b>".$empcode."</b></td>";
                    echo "<td style=font-size:12;> <b>Society Name:</b></td><td style=font-size:12;><b>".$socname."</b></td></tr>";               
                   
                  
            ;?>
            <hr>
            </table>
        <table class="TFtable">
            <tr>
                <td align="center" width="30%"><b>Earnings</b></td>
                <td align="center"  width="30%"><b>Deductions</b></td>
                <td align="center"  width="30%"><b>Deductions</b></td>
            </tr>
            <tr><td valign=top>
                <table>
                    <?php foreach($incomes as $inrecord){ ?>
                    <tr>
                        <td>
                         <b><?php echo nl2br($inrecord->sh_name);?> </b> 
                        </td>
                         <td>
                            <?php 
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
               </td>
               <td valign=top>
                   <table>
                       
                       <?php 
                     
                        $count=count($deduction);
                        $halfin = $count / 2; 
                        $part1 = array_slice($deduction, 0, $halfin); 
                        $part2 = array_slice($deduction, $halfin); 
                       
                       ?>
                       <?php foreach($part1 as $dedrecord){ ?>
                        <tr>
                            <td>
                            <b><?php echo nl2br($dedrecord->sh_name);?> </b> 
                            </td>
                            <td><?php 
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
               </td>
               <td valign=top>
                   <table>
                       <?php foreach($part2 as $dedrecord1){ ?>
                       <tr>
                           <td><b><?php echo nl2br($dedrecord1->sh_name);?> </b> </td>
                           <td><?php
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
               </td>
            </tr>
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
                <td><b>Gross Pay    <?php echo $grosspay;?></b></td>
                <td><b>Total Deduction:  <?php echo $totalded;?></b></td>
                <td><b>Net Pay: <?php echo $netpay;?></b></td>
                              
            </tr>
        </table>
               
        
        <?php }; ?> 
        <?php //endforeach;
         };
        ?>  
            
    </body>
<p> &nbsp; </p>
</html>

