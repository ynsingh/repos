    <!--@filename empSalary.php  @author Manorama Pal(palseema30@gmail.com)
    -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Employee Salary </title>
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
            <table width="100%" border="0">
            <!--    <form action="<?php echo site_url('setup3/salaryprocess');?>" method="POST" enctype="multipart/form-data"> -->
                <form action="<?php echo site_url('setup3redesign/salaryprocess');?>" method="POST" enctype="multipart/form-data"> 
                <tr style="font-weight:bold;" >
                   <td> Select Depratment<br>
                        <select name="dept" id="dept" style="width:250px;" required> 
                            <?php
				if(!empty($deptsel)){ ?>
                            <option value="<?php echo "";?>"><?php echo $deptsel;?></option>
<?php				}else{ ?>
                            <option value="<?php echo "";?>"><?php echo "Please select department";?></option>
<?php				}
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
                    
                   <td><br>
                        <input type="submit" name="salpro" id="salpro" value="Load"/> 
                     
                    </td>
                   </form>
		<?php  if(($sroleid == 5)&&($lckstus == 'N')){ ?>
                    <form name="lockfrm" action="<?php echo site_url('setup3redesign/locksalary');?>" method="POST" enctype="multipart/form-data"> 
		    <td> <br>
		    <input type="submit" name="sallock" id="sallock" value="Salary Lock" onclick="return confirm('Are you sure you want to Lock salary?');"/> 
                    </td>
		    </form>
		<?php }
		    if(($sroleid == 14)||($sroleid == 1)||(($sroleid == 5)&&($lckstus == 'N'))){ ?>
                    <!--<form action="<?php echo site_url('setup3/copysalary');?>" method="POST" enctype="multipart/form-data"> -->
                    <form action="<?php echo site_url('setup3redesign/copysalary');?>" method="POST" enctype="multipart/form-data"> 
                    <td><br> Process Salary for the current month:
                    <input type="submit" name="salcopy" id="salcopy" value="Salary Process" onclick="return confirm('Are you sure you want to copy previous month salary to current month?');"/> 
                    </td>
                    </form>
		<?php } ?>
                </tr>
            </table>
                   
            <div class="scroller_sub_page">
            <table class="TFtable" >
                <thead><tr>
                    <!-- <th>Sr.No</th> -->
                    <th>Employee Name</th>
		<?php  if(empty($deptsel)){
                    echo "<th>Details</th>";
		}
 ?>
                    <th>Designation</th>
                    <th>Total Salary</th>
                    <!--<th>Status</th> -->
                    <th></th>
                    <th>Contact Details</th>
                    <th></th>
                    <th></th>
                    
                </tr></thead>
                <tbody>
                <?php //print_r($etranlist);?>  
                              <?php  if(!empty($deptsel)){
					echo "<tr><td colspan=7><b>Department : ".$deptsel."</b></td></tr>";
				}
                            ?>    
                    <?php $serial_no = 1;
		//	print_r($emplist);
                  //   if( count($emplist) ):  
                     if( !empty($emplist) ):  
                         foreach($emplist as $record){ ?>
                        <?php // if(!in_array($record->emp_id,$etranlist)){;?>
                            <tr>
                                <td><?php  echo $serial_no++; ?>&nbsp;
                                <?php echo $record->emp_name."<br/>" ."("."<b>PF No: </b>".$record->emp_code.")"; 

                                    if(!empty($record->emp_schemeid)){
                                        $schme=$this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$record->emp_schemeid)->sd_name;
					if(!empty($deptsel)){
						echo "<br/> "."<b>scheme-: </b>".$schme;
					}
                                    }
				?></td>
                                <?php
				 if(empty($deptsel)){

                                    $sc=$this->commodel->get_listspfic1('study_center','sc_name','sc_id',$record->emp_scid)->sc_name;
                                    $uo=$this->lgnmodel->get_listspfic1('authorities','name','id' ,$record->emp_uocid)->name;
                                    $dept=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$record->emp_dept_code)->dept_name;
				    echo "<td>";
                                    echo "<b>campus-: </b>".$sc."<br/> "."<b>uo-: </b>".$uo."<br/> "."<b>dept-: </b>".$dept."<br/> "."<b>scheme-: </b>".$schme;
				    echo "</td>";
				}
                                ?>
                               
                                <td><?php echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$record->emp_desig_code)->desig_name; ?>
                                </td>
                                <td><?php 
                                        $selectfield ="sal_netsalary,sal_status";
                                        $whdata = array('sal_empid' =>$record->emp_id,'sal_month' =>$selmonth,'sal_year' =>$selyear);
					$whorder="sal_id desc";
                                        $salval= $this->sismodel->get_orderlistspficemore('salary',$selectfield,$whdata,$whorder);
                                        if(!empty($salval)){
                                          
                                            if($salval[0]->sal_status != 'pending'){
                                                echo $netval=$salval[0]->sal_netsalary;
                                            }
                                            else{
                                                echo "<font color=\"red\"><b> pending</b>";
                                            }
                                        }
                                        else{
                                            echo 0.0;
                                        }
                                    ;?>
                                </td>
                               <td></td>
                               
                                <td><?php
					$adhaar=$record->emp_aadhaar_no; 
                                        echo "<b>email Id-: </b>".$record->emp_email."<br/>"."<b>contact no-: </b>".$record->emp_phone."<br/>"."<b> aadhaar no-: </b>".$this->sismodel->mask($adhaar,null,strlen($adhaar)-4);
                                        
                                    ?>
                                    
                                </td> 
				<td><?php 

					if(($sroleid == 14)||($sroleid == 1)||(($sroleid == 5)&&($lckstus == 'N'))){
						echo anchor("setup3redesign/salaryslip/".$record->emp_id."/".$selmonth."/".$selyear,img(array('src'=>'assets/sis/images/edit.png','border'=>'0','alt'=>'update')),array('src'=>'assests/sis/images/edit.png','title' => 'update salary slip' , 'class' => 'red-link'));  
					}
				?></td>

                                <td><?php echo anchor("setup3redesign/salaryslipcopy/".$record->emp_id."/".$selmonth."/".$selyear,img(array('src'=>'assets/sis/images/pdf.jpeg','border'=>'0.1px','alt'=>'view ')),array('title' => 'save salary slip' , 'class' => 'red-link'));?></td>
                            </tr>
                        <?php }; ?>
                        <input type="hidden" name="typecase" value="normal">  
                        <?php //};?>
                    <?php else : ?>
                        <td colspan= "13" align="center">Either department is not selected or  no employee records found. So please try with department selection.!</td> 
                    <?php endif;?> 
                        
                   <!--*****start Records from ste and sle both************************************************** -->     

		<?php if( !empty($tlemplist)) :   ?>
                     <tr> <td colspan="13"><?php echo "<b>Employees Salary of both (Transfer and Leave) cases</b>"; ?></td></tr>
		<?php	foreach($tlemplist as $record){ ?>
                            <tr>
                                <td><?php  echo $serial_no++; ?>&nbsp;
                                <?php echo $record->emp_name."<br/>" ."("."<b>PF No: </b>".$record->emp_code.")"; 

                                    if(!empty($record->emp_schemeid)){
                                        $schme=$this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$record->emp_schemeid)->sd_name;
					if(!empty($deptsel)){
						echo "<br/> "."<b>scheme-: </b>".$schme;
					}
                                    }
				?></td>
                                <?php
				 if(empty($deptsel)){

                                    $sc=$this->commodel->get_listspfic1('study_center','sc_name','sc_id',$record->emp_scid)->sc_name;
                                    $uo=$this->lgnmodel->get_listspfic1('authorities','name','id' ,$record->emp_uocid)->name;
                                    $dept=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$record->emp_dept_code)->dept_name;
				    echo "<td>";
                                    echo "<b>campus-: </b>".$sc."<br/> "."<b>uo-: </b>".$uo."<br/> "."<b>dept-: </b>".$dept."<br/> "."<b>scheme-: </b>".$schme;
				    echo "</td>";
				}
                                ?>
                               
                                <td><?php echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$record->emp_desig_code)->desig_name; ?>
                                </td>
                                <td><?php 
                                        $selectfield ="sallt_netsalary,sallt_status";
                                        $whdata = array('sallt_empid' =>$record->emp_id,'sallt_month' =>$selmonth,'sallt_year' =>$selyear,'sallt_type'=>'from');
                                        $salval= $this->sismodel->get_orderlistspficemore('salary_lt',$selectfield,$whdata,'');
                                        $whdata1 = array('sallt_empid' =>$record->emp_id,'sallt_month' =>$selmonth,'sallt_year' =>$selyear,'sallt_type'=>'transit');
                                        $salval1= $this->sismodel->get_orderlistspficemore('salary_lt',$selectfield,$whdata1,'');
                                        $whdata2 = array('sallt_empid' =>$record->emp_id,'sallt_month' =>$selmonth,'sallt_year' =>$selyear,'sallt_type'=>'to');
                                        $salval2= $this->sismodel->get_orderlistspficemore('salary_lt',$selectfield,$whdata2,'');
                                        
                                        if(!empty($salval)){
                                            if($salval[0]->sallt_status != 'pending'){
                                                $netval=$salval[0]->sallt_netsalary;
                                                $netval1=$salval1[0]->sallt_netsalary;
                                                $netval2=$salval2[0]->sallt_netsalary;
                                                $total=$netval+$netval1+$netval2;
                                            //print_r($netval.",".$netval1.",".$netval2.",".$total);
                                                echo (round($total,2));
                                            }  
                                            else{
                                                echo "<font color=\"red\"><b> pending</b>";
                                            }
                                            
                                        }
                                        else{
                                            echo 0.0;
                                        }
                                                                
                                    ;?>
                                </td>
                               <td></td>
                               
                                <td><?php
					$adhaar=$record->emp_aadhaar_no; 
                                        echo "<b>email Id-: </b>".$record->emp_email."<br/>"."<b>contact no-: </b>".$record->emp_phone."<br/>"."<b> aadhaar no-: </b>".$this->sismodel->mask($adhaar,null,strlen($adhaar)-4);
                                        
                                    ?>
                                    
                                </td> 
                                <td><?php 
					//create new function for both trf and leave
					if(($sroleid == 14)||($sroleid == 1)||(($sroleid == 5)&&($lckstus == 'N'))){
                                        echo anchor("setup3redesign/tlsalaryslip/".$record->emp_id."/".$selmonth."/".$selyear."/tlcase",img(array('src'=>'assets/sis/images/edit.png','border'=>'0','alt'=>'update')),array('src'=>'assests/sis/images/edit.png','title' => 'update salary slip' , 'class' => 'red-link')); 
                                        }
                                    ;?>
                                
				<?php		// echo anchor("setup3redesign/salaryslip/".$record->emp_id."/".$selmonth."/".$selyear,img(array('src'=>'assets/sis/images/edit.png','border'=>'0','alt'=>'update')),array('src'=>'assests/sis/images/edit.png','title' => 'update salary slip' , 'class' => 'red-link')); 
					//}
				?></td>
                                <td><?php echo anchor("setup3redesign/salaryslipcopy/".$record->emp_id."/".$selmonth."/".$selyear."/transcase",img(array('src'=>'assets/sis/images/pdf.jpeg','border'=>'0.1px','alt'=>'view ')),array('title' => 'save salary slip' , 'class' => 'red-link'));?></td>
                            </tr>
                        <?php }; ?>

		<?php endif;?>


                   <!--*****end Records from ste and sle both************************************************** -->     
                        
                   <!--*****Records from ste************************************************** -->     
                     <?php // echo count($etranlist); ?>  
                    <?php if( !empty($etranlist)) :   ?>
                     <tr> <td colspan="13"><?php echo "<b>Employees Salary of Transfer cases</b>"; ?></td></tr>
                        <?php foreach($etranlist as $recordste){ ?>
                            <tr>
                                
                                
                                
                                <td><?php  echo $serial_no++; ?>&nbsp;
                                <?php echo "". $recordste->emp_name."<br/>" ."("."<b>PF No: </b>".$recordste->emp_code.")";
					if(!empty($record->emp_schemeid)){
                                        $schme=$this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$record->emp_schemeid)->sd_name;
                                        if(!empty($deptsel)){
                                                echo "<br/> "."<b>scheme-: </b>".$schme;
                                        }
                                    }

				 ?></td>
                                <?php
				
                                 if(empty($deptsel)){
                                    $sc=$this->commodel->get_listspfic1('study_center','sc_name','sc_id',$recordste->emp_scid)->sc_name;
                                    $uo=$this->lgnmodel->get_listspfic1('authorities','name','id' ,$recordste->emp_uocid)->name;
                                    $dept=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$recordste->emp_dept_code)->dept_name;
                                    if(!empty($record->emp_schemeid)){
                                        $schme=$this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$recordste->emp_schemeid)->sd_name;
                                        
                                    }
				    echo "<td>";
                                    echo "<b>campus-: </b>".$sc."<br/> "."<b>uo-: </b>".$uo."<br/> "."<b>dept-: </b>".$dept."<br/> "."<b>scheme-: </b>".$schme;
				    echo "</td>";
				}
                                ?>
                                
                                <td><?php echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$recordste->emp_desig_code)->desig_name; ?>
                                </td>
                                <td><?php 
                                        $selectfield ="sallt_netsalary,sallt_status";
                                        $whdata = array('sallt_empid' =>$recordste->emp_id,'sallt_month' =>$selmonth,'sallt_year' =>$selyear,'sallt_type'=>'from');
                                        $salval= $this->sismodel->get_orderlistspficemore('salary_lt',$selectfield,$whdata,'');
                                        $whdata1 = array('sallt_empid' =>$recordste->emp_id,'sallt_month' =>$selmonth,'sallt_year' =>$selyear,'sallt_type'=>'transit');
                                        $salval1= $this->sismodel->get_orderlistspficemore('salary_lt',$selectfield,$whdata1,'');
                                        $whdata2 = array('sallt_empid' =>$recordste->emp_id,'sallt_month' =>$selmonth,'sallt_year' =>$selyear,'sallt_type'=>'to');
                                        $salval2= $this->sismodel->get_orderlistspficemore('salary_lt',$selectfield,$whdata2,'');
                                        
                                        if(!empty($salval)){
                                            if($salval[0]->sallt_status != 'pending'){
                                                $netval=$salval[0]->sallt_netsalary;
                                                $netval1=$salval1[0]->sallt_netsalary;
                                                $netval2=$salval2[0]->sallt_netsalary;
                                                $total=$netval+$netval1+$netval2;
                                            //print_r($netval.",".$netval1.",".$netval2.",".$total);
                                                
                                                echo (round($total,2));
                                            }    
                                            else{
                                                echo "<font color=\"red\"><b> pending</b>";
                                            }
                                            
                                        }
                                        else{
                                            echo 0.0;
                                        }
                                    ;?>
                                </td>
                               <td></td>
                               
                                <td><?php 
                                        echo "<b>email Id-: </b>".$recordste->emp_email."<br/>"."<b>contact no-: </b>".$recordste->emp_phone."<br/>"."<b> aadhaar no-: </b>".$recordste->emp_aadhaar_no;
                                        
                                    ?>
                                    
                                </td> 
				<td><?php 
					if(($sroleid == 14)||($sroleid == 1)||(($sroleid == 5)&&($lckstus == 'N'))){
						echo anchor("setup3redesign/transfersalaryslip/".$recordste->emp_id."/".$selmonth."/".$selyear."/transcase",img(array('src'=>'assets/sis/images/edit.png','border'=>'0','alt'=>'update')),array('title' => 'update salary slip' , 'class' => 'red-link'));  
					}
				?></td>
                                <td><?php echo anchor("setup3redesign/salaryslipcopy/".$recordste->emp_id."/".$selmonth."/".$selyear."/transcase",img(array('src'=>'assets/sis/images/pdf.jpeg','border'=>'0.1px','alt'=>'view ')),array('title' => 'save salary slip' , 'class' => 'red-link'));  ;?></td>
                            </tr>
                    <?php } //else : ?>
                           <!-- <tr><td colspan= "13" align="center"> No Employee Transfer  Records found...!</td></tr> -->
                            <?php endif; 
				if(!empty($empleavelist)):
			?>
                     <tr> <td colspan="13"><?php echo "<b>Employees Salary of Leave cases</b>"; ?></td></tr>
                        <?php //}
				foreach($empleavelist as $recordsle){ ?> 
                            <tr>
                             
                                <td><?php  echo $serial_no++; ?>&nbsp;
                                <?php echo $recordsle->emp_name."<br/>" ."("."<b>PF No: </b>".$recordsle->emp_code.")";
					if(!empty($record->emp_schemeid)){
                                        $schme=$this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$record->emp_schemeid)->sd_name;
                                        if(!empty($deptsel)){
                                                echo "<br/> "."<b>scheme-: </b>".$schme;
                                        }
                                    }

				 ?></td>
                                <?php
					
                                 if(empty($deptsel)){
                                    $sc=$this->commodel->get_listspfic1('study_center','sc_name','sc_id',$recordsle->emp_scid)->sc_name;
                                    $uo=$this->lgnmodel->get_listspfic1('authorities','name','id' ,$recordsle->emp_uocid)->name;
                                    $dept=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$recordsle->emp_dept_code)->dept_name;
                                    if(!empty($record->emp_schemeid)){
                                        $schme=$this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$recordsle->emp_schemeid)->sd_name;
                                        
                                    }
				echo "<td>";
                                    echo "<b>campus-: </b>".$sc."<br/> "."<b>uo-: </b>".$uo."<br/> "."<b>dept-: </b>".$dept."<br/> "."<b>scheme-: </b>".$schme;
				echo "</td>";
				}
                                ?>
                               
                                <td><?php echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$recordsle->emp_desig_code)->desig_name; ?>
                                </td>
                                <td><?php 
                                        $selectfield ="sallt_netsalary,sallt_status";
                                        $whdata = array('sallt_empid' =>$recordsle->emp_id,'sallt_month' =>$selmonth,'sallt_year' =>$selyear);
                                        $salval= $this->sismodel->get_orderlistspficemore('salary_lt',$selectfield,$whdata,'');
                                        if(!empty($salval)){
                                            if($salval[0]->sallt_status != 'pending'){
                                                $netval=$salval[0]->sallt_netsalary;
                                                echo (round($netval,2));
                                            } 
                                            else{
                                                echo "<font color=\"red\"><b> pending</b>";
                                            }
                                        }
                                        else{
                                            echo 0.0;
                                        }
                                    ;?>
                                </td>
                               <td></td>
                               
                                <td><?php 
                                        echo "<b>email Id-: </b>".$recordsle->emp_email."<br/>"."<b>contact no-: </b>".$recordsle->emp_phone."<br/>"."<b> aadhaar no-: </b>".$recordsle->emp_aadhaar_no;
                                        
                                    ?>
                                    
                                </td> 
				<td><?php 
					if(($sroleid == 14)||($sroleid == 1)||(($sroleid == 5)&&($lckstus == 'N'))){
						echo anchor("setup3redesign/leavesalaryslip/".$recordsle->emp_id."/".$selmonth."/".$selyear."/leavcase",img(array('src'=>'assets/sis/images/edit.png','border'=>'0','alt'=>'update')),array('title' => 'update salary slip' , 'class' => 'red-link'));  
					}
				?></td>
                                <td><?php echo anchor("setup3redesign/salaryslipcopy/".$recordsle->emp_id."/".$selmonth."/".$selyear."/leavcase",img(array('src'=>'assets/sis/images/pdf.jpeg','border'=>'0.1px','alt'=>'view ')),array('title' => 'save salary slip' , 'class' => 'red-link'));  ;?></td>
                            </tr>
                                
                        <?php }; ?>   
                    <?php // else : ?>
                         <!--   <tr><td colspan= "13" align="center"> No Employee Leave Records found...!</td></tr> --> 
                    <?php  endif;?>   
                        
                </tbody>    
            </table>
        
        </div><!-- close scroller-->
         <p> &nbsp; </p>
        <div align="center">  <?php $this->load->view('template/footer');?></div>
    </body>
</html>    
