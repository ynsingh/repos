    <!--@filename empSalary.php  @author Manorama Pal(palseema30@gmail.com) -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Welcome to TANUVAS</title>
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
            <?php 
            echo "<td align=\"left\" width=\"33%\">";
            echo anchor('setup3/salaryhead/', "Add Salary Head" ,array('title' => 'View salary head ' , 'class' => 'top_parent'));
            echo "</td>";
            echo "<td align=\"center\" width=\"34%\">";
            echo "<b>Salary Head List</b>";
            echo "</td>";
            echo "<td align=\"right\" width=\"33%\">";
            $help_uri = site_url()."/help/helpdoc#SalaryHeadList";
            echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
            echo "</td>";
            ?>
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
           
            <table width="100%" border="0">
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
                    
                   /* $patten = '/[\*\/\+-]/';
                    $str='(BP+GP)*15';
                    $equation=preg_replace($patten,",",$str);
                    $equation=preg_replace("/[\(\)]/","",$equation);
                    echo"result===".$equation;*/
                     
                
                ?>
                <form action="<?php echo site_url('setup3/salaryprocess');?>" method="POST" enctype="multipart/form-data"> 
                <tr style="font-weight:bold;" >
                    
                    <td>Select Month 
                        <select name="month" id="month" style="width:300px;"> 
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
                    <td>Select Year
                        
                        
                        <select name="year" id="year" style="width:300px;" > 
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
                        <input type="submit" name="salpro" id="salpro" value="Load"/> 
                     
                    </td>
                   </form>
                    <form action="<?php echo site_url('setup3/copysalary');?>" method="POST" enctype="multipart/form-data"> 
                    <td> Process Salary for the current month:
                    <input type="submit" name="salcopy" id="salcopy" value="Salary Process" onclick="return confirm('Are you sure you want to copy previous month salary to current month?');"/> 
                    </td>
                    </form>
                   
                </tr>
            </table>
            <div class="scroller_sub_page">
            <table class="TFtable" >
                <thead><tr>
                    <!-- <th>Sr.No</th> -->
                    <th>Employee Name</th>
                    <th>Details</th>
                    <th>Designation</th>
                    <th>Total Salary</th>
                    <th>Status</th>
                    <th></th>
                    <th>Contact Details</th>
                    
                </tr></thead>
                <tbody>
                  
                    <?php $serial_no = 1;?>
                    <?php if( count($emplist) ):  ?>
                        <?php foreach($emplist as $record){ ?>
                            <tr>
                                
                                
                                
                                <td><?php  echo $serial_no++; ?>&nbsp;
                                <?php echo $record->emp_name."<br/>" ."("."<b>PF No: </b>".$record->emp_code.")"; ?></td>
                                <td><?php
                                    $sc=$this->commodel->get_listspfic1('study_center','sc_name','sc_id',$record->emp_scid)->sc_name;
                                    $uo=$this->lgnmodel->get_listspfic1('authorities','name','id' ,$record->emp_uocid)->name;
                                    $dept=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$record->emp_dept_code)->dept_name;
                                    if(!empty($record->emp_schemeid)){
                                        $schme=$this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$record->emp_schemeid)->sd_name;
                                        
                                    }
                                    echo "<b>campus-: </b>".$sc."<br/> "."<b>uo-: </b>".$uo."<br/> "."<b>dept-: </b>".$dept."<br/> "."<b>scheme-: </b>".$schme;
                                ?>
                                </td>
                                <td><?php echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$record->emp_desig_code)->desig_name; ?>
                                </td>
                                <td><?php 
                                        $selectfield ="sal_netsalary";
                                        $whdata = array('sal_empid' =>$record->emp_id,'sal_month' =>$selmonth,'sal_year' =>$selyear);
                                        $salval= $this->sismodel->get_orderlistspficemore('salary',$selectfield,$whdata,'');
                                        if(!empty($salval)){
                                            echo $netval=$salval[0]->sal_netsalary;
                                        }
                                        else{
                                            echo 0.0;
                                        }
                                    ;?>
                                </td>
                               <td></td>
                               <td><?php echo anchor("setup3/salaryslip/".$record->emp_id."/".$selmonth."/".$selyear,"salary slip",array('title' => 'View salary slip' , 'class' => 'red-link'));  ;?></td>
                                <td><?php
					$adhaar=$record->emp_aadhaar_no; 
                                        echo "<b>email Id-: </b>".$record->emp_email."<br/>"."<b>contact no-: </b>".$record->emp_phone."<br/>"."<b> aadhaar no-: </b>".$this->sismodel->mask($adhaar,null,strlen($adhaar)-4);
                                        
                                    ?>
                                    
                                </td>    
                            </tr>
                        <?php }; ?>
                            
                    <?php else : ?>
                        <td colspan= "13" align="center"> No Records found...!</td>
                    <?php endif;?> 
                        
                   <!--*****Records from ste************************************************** -->     
                     <?php // echo count($etranlist); ?>  
                    <?php if( !empty(($etranlist)) && count($etranlist)  ):   ?>
                     <tr> <td colspan="13"><?php echo "<b>Employees Salary of Transfer cases</b>"; ?></td></tr>
                        <?php foreach($etranlist as $recordste){ ?>
                            <tr>
                                
                                
                                
                                <td><?php  echo $serial_no++; ?>&nbsp;
                                <?php echo $recordste->emp_name."<br/>" ."("."<b>PF No: </b>".$recordste->emp_code.")"; ?></td>
                                <td><?php
                                    $sc=$this->commodel->get_listspfic1('study_center','sc_name','sc_id',$recordste->emp_scid)->sc_name;
                                    $uo=$this->lgnmodel->get_listspfic1('authorities','name','id' ,$recordste->emp_uocid)->name;
                                    $dept=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$recordste->emp_dept_code)->dept_name;
                                    if(!empty($record->emp_schemeid)){
                                        $schme=$this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$recordste->emp_schemeid)->sd_name;
                                        
                                    }
                                    echo "<b>campus-: </b>".$sc."<br/> "."<b>uo-: </b>".$uo."<br/> "."<b>dept-: </b>".$dept."<br/> "."<b>scheme-: </b>".$schme;
                                ?>
                                </td>
                                <td><?php echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$recordste->emp_desig_code)->desig_name; ?>
                                </td>
                                <td><?php 
                                        $selectfield ="sallt_netsalary";
                                        $whdata = array('sallt_empid' =>$recordste->emp_id,'sallt_month' =>$selmonth,'sallt_year' =>$selyear);
                                        $salval= $this->sismodel->get_orderlistspficemore('salary_lt',$selectfield,$whdata,'');
                                        if(!empty($salval)){
                                            $netval=$salval[0]->sallt_netsalary;
                                            echo (round($netval,2));
                                        }
                                        else{
                                            echo 0.0;
                                        }
                                    ;?>
                                </td>
                               <td></td>
                               <td><?php echo anchor("setup3/transfersalaryslip/".$recordste->emp_id."/".$selmonth."/".$selyear,"salary slip",array('title' => 'View salary slip' , 'class' => 'red-link'));  ;?></td>
                                <td><?php 
                                        echo "<b>email Id-: </b>".$recordste->emp_email."<br/>"."<b>contact no-: </b>".$recordste->emp_phone."<br/>"."<b> aadhaar no-: </b>".$recordste->emp_aadhaar_no;
                                        
                                    ?>
                                    
                                </td>    
                            </tr>
                        <?php }; ?>
                            
                    <?php else : ?>
                        <td colspan= "13" align="center"> No Records found...!</td>
                    <?php endif;?>   
                        
                </tbody>    
            </table>
        
        </div><!-- close scroller-->
         <p> &nbsp; </p>
        <div align="center">  <?php $this->load->view('template/footer');?></div>
    </body>
</html>    
