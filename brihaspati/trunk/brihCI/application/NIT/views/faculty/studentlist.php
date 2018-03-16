<!--@filename studentlist.php  @author Manorama Pal(palseema30@gmail.com) 
    @filename studentlist.php  @author Neha Khullar(nehukhullar@gmail.com) -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Welcome to IGNTU</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
    </head>
    <body>
        <div>
            <?php $this->load->view('template/header'); ?>
         <!--   <h3>Welcome <?//= $this->session->userdata('username') ?></h3>-->
            <?php //$this->load->view('template/facultymenu');?>
        </div>
<table width="100%">
            <tr>
<?php
		    echo "<td align=\"left\" width=\"33%\">";
                    echo "</td>";

                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Student List</b>";
                    echo "</td>";

                    echo "<td align=\"right\" width=\"33%\">";
                    $help_uri = site_url()."/help/helpdocfaculty#StudentList";
                    echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                    echo "</td>";

                    ?>

        <form action="<?php echo site_url('facultyhome/studentlist');?>" method="POST" class="form-inline">
        <table style="width:100%">
            <tr style="font-weight:bold; background-color:lightslategray;">
                <td>  Select Program:</br>
                    <?php if (isset($filter) && !empty($search)) :?>
                        <select name="programname">
                    <?php else: ?>   
                        <select name="programname"  onchange="this.form.submit();">   
                    <?php endif ;?>   
		    <?php if(!empty($selprg_name)):
				$flg = True;
		    ?>
			
                    <option value="<?php $selprg_name;?>"><?php 
                        echo $this->cmodel->get_listspfic1('program','prg_name','prg_id',$selprg_name)->prg_name."&nbsp;"."(".
                        $this->cmodel->get_listspfic1('program','prg_branch','prg_id',$selprg_name)->prg_branch.")"        
                    ;?></option>
		    <?php else: 
		    	$flg = False;
		    ?>  
			<option value="" selected disabled >--------------Select Program------------</option>
		    <?php endif ;?>
		    <?php if($flg): ?>
                         <option value="" disabled >--------------Select Program------------</option>
                    <?php endif ;?>
                    <?php
                        foreach($prgsublist as $prgdata): ?>	
                            <option value="<?php echo $prgdata->pstp_prgid; ?>"><?php 
                                echo $this->cmodel->get_listspfic1('program','prg_name','prg_id',$prgdata->pstp_prgid)->prg_name."&nbsp;"."(".
                                $this->cmodel->get_listspfic1('program','prg_branch','prg_id',$prgdata->pstp_prgid)->prg_branch.")"     
                            ;?></option> 
 			<?php endforeach; ?>
                    </select>
                   
                </td>
		
                <td>Search By:</br>
                    <select name="field" required> 
                        <option selected="selected" disabled selected>--------------Filter By ------------</option>
                        <option value="sm_fname">Student Name</option>
                        <option value="sm_email">Email</option>
                        <option value="sm_enrollmentno">Enrollment No</option>
                    </select>
                </td>
                <td>Match String:</br>
                    <input type="text" name="search" value="" placeholder="Search..." size="25" >
                <!--</td>
                <td>-->
                    <input type="submit" name="filter" value="Search" />
                    <input type="hidden" name="prgname" value="<?php echo $selprg_name;?>">
                </td>
                <td >Academic Year:&nbsp;&nbsp <?php echo $academicyear;?></td>
               <td >Current Semester:&nbsp;&nbsp;<?php echo $semester;?></td>
                
            </tr>
        </table><br>   
        <table class="TFtable" >
            <thead>
                <tr>
                     <th>Sr.No</th>
                    <th>Student Name</th>
                    <th>Enrollment No</th>
                    <th>Roll No</th>
                    <th>Semester/Year</th>
                    <th>Aadhaar No</th>
                    <th>Department</th>
                    <th>Subject</th>
                    <th>Email Id</th>
                    <th>Mobile No</th>
                </tr>
            </thead>
            <tbody>
                
                <?php if( count($studentdetail) ):
                      $count=$this->uri->segment(3, 0);
                    foreach($studentdetail as $record){ ?>
                    <tr align="center"> 
                        <td><?php echo ++$count; ?></td> 
                        <td><?php
                            $fname=$this->cmodel->get_listspfic1('student_master','sm_fname ','sm_id',$record->sp_smid)->sm_fname;
                            $mname=$this->cmodel->get_listspfic1('student_master','sm_mname ','sm_id',$record->sp_smid)->sm_mname;
                            $lname=$this->cmodel->get_listspfic1('student_master','sm_lname ','sm_id',$record->sp_smid)->sm_lname;
                            echo $fname." " .$mname." " .$lname;
                            ;?>
                        </td>
                        <td><?php echo $this->cmodel->get_listspfic1('student_master','sm_enrollmentno','sm_id',$record->sp_smid)->sm_enrollmentno;?></td>
                        <td><?php 
			if(!empty($filprg_name)){
	
				$prgid = $filprg_name;
			}
			else{
				$prgid = $selprg_name;
			}//print_r($prgid);die;
			
                                $Rfield=array('senex_rollno');
				$Rstdata=array(
                                'senex_smid' =>$record->sp_smid,
                                'senex_prgid' =>$prgid,
                            	);
				  $stud_rollno = $this->cmodel->get_listspficemore('student_entry_exit',$Rfield,$Rstdata);
				
			    foreach($stud_rollno as $rno){
	                                echo $rno->senex_rollno;
                            }
                         /*       $Rstdata=array(
                                'senex_smid' =>$record->sp_smid,
                                'senex_prgid' =>$filprg_name,
                            );  
                            $stud_rollno = $this->cmodel->get_listspficemore('student_entry_exit',$Rfield,$Rstdata);
                            if(!empty($stud_rollno[0]->senex_rollno)){
                                echo $stud_rollno[0]->senex_rollno;
                            }
                            else{
                                 $Rstdata2=array(
                                'senex_smid' =>$record->sp_smid,
                                'senex_prgid' =>$selprg_name,
                            );  
                            $stud_rollno2 = $this->cmodel->get_listspficemore('student_entry_exit',$Rfield,$Rstdata2);
                            if(!empty($stud_rollno2[0]->senex_rollno)){
                                echo $stud_rollno2[0]->senex_rollno;
                            }
                                
                            }*/
                        ;?></td>
                        <td><?php 
                          $sfield=array('sp_deptid','sp_semester');
                                $stdntdata=array(
                                'sp_smid' =>$record->sp_smid,
                               // 'sp_sccode'   =>$this->campucode,
                                'sp_programid' =>$filprg_name,
                                'sp_acadyear' =>$academicyear,
                               // 'sp_semester'  =>$semester
                            );
                            $stud_prg_dept = $this->cmodel->get_listspficemore('student_program',$sfield,$stdntdata);
                            if(!empty($stud_prg_dept[0]->sp_semester)){
                              echo $stud_prg_dept[0]->sp_semester;
                            }
                            else
                            {
                                $stdntdata2=array(
                                'sp_smid' =>$record->sp_smid,
                               // 'sp_sccode'   =>$this->campucode,
                                'sp_programid' =>$selprg_name,
                                'sp_acadyear' =>$academicyear,
                                //'sp_semester'  =>$semester
                            );
                            $stud_prg_dept2 = $this->cmodel->get_listspficemore('student_program',$sfield,$stdntdata2);
                            if(!empty($stud_prg_dept2[0]->sp_semester)){
                               echo $stud_prg_dept2[0]->sp_semester;
                            }
                            }
                            ;?>
                        </td>
                        <td><?php echo $this->cmodel->get_listspfic1('student_master','sm_uid','sm_id',$record->sp_smid)->sm_uid;?></td>
                        <td>
                            <?php
			   	
                            if(! empty($record->sp_deptid)){
                            	 echo $this->cmodel->get_listspfic1('Department','dept_name','dept_id',$record->sp_deptid)->dept_name;
                            }
                            else{
                                echo $this->cmodel->get_listspfic1('Department','dept_name','dept_id',$stud_prg_dept[0]->sp_deptid)->dept_name;
                            }
                            ;?>
                        </td>
                        
                        <td><?php 
                            $subject='';
                        if(! empty($record->sp_subid1)){
                            if(!empty($record->sp_subid1)){
                            $sub1=$this->cmodel->get_listspfic1('subject','sub_name','sub_id',$record->sp_subid1)->sub_name;
                            $subject=$sub1;
                            }
                            if(!empty($record->sp_subid2)){
                            $sub2=$this->cmodel->get_listspfic1('subject','sub_name','sub_id',$record->sp_subid2)->sub_name;
                            $subject=$subject.",".$sub2;
                            }                            
                            if(!empty($record->sp_subid3)){
                            $sub3=$this->cmodel->get_listspfic1('subject','sub_name','sub_id',$record->sp_subid3)->sub_name;
                            $subject=$subject.",".$sub3;
                            }
                            if(!empty($record->sp_subid4)){
                            $sub4=$this->cmodel->get_listspfic1('subject','sub_name','sub_id',$record->sp_subid4)->sub_name;
                            $subject=$subject.",".$sub4;                          
                            }
                            if(!empty($record->sp_subid5)){
                            $sub5=$this->cmodel->get_listspfic1('subject','sub_name','sub_id',$record->sp_subid5)->sub_name;
                            $subject=$subject.",".$sub5;                        
                            }
                            if(!empty($record->sp_subid6)){
                            $sub6=$this->cmodel->get_listspfic1('subject','sub_name','sub_id',$record->sp_subid6)->sub_name;
                            $subject=$subject.",".$sub6;                          
                            }
                            if(!empty($record->sp_subid7)){
                            $sub7=$this->cmodel->get_listspfic1('subject','sub_name','sub_id',$record->sp_subid7)->sub_name;
                            $subject=$subject.",".$sub7;                           
                            }
                            if(!empty($record->sp_subid8)){
                            $sub8=$this->cmodel->get_listspfic1('subject','sub_name','sub_id',$record->sp_subid8)->sub_name;
                            $subject=$subject.",".$sub8;
                            }
                            if(!empty($record->sp_subid9)){
                            $sub9=$this->cmodel->get_listspfic1('subject','sub_name','sub_id',$record->sp_subid9)->sub_name;
                            $subject=$subject.",".$sub9;                            
                            }
                            if(!empty($record->sp_subid10)){
                            $sub10=$this->cmodel->get_listspfic1('subject','sub_name','sub_id',$record->sp_subid10)->sub_name;
                            $subject=$subject.",".$sub10;                           
                            }
                            echo $subject;
                        }
                        else{
                            $scid  = $this->cmodel->get_listspfic1('student_program','sp_sccode','sp_smid',$record->sp_smid)->sp_sccode;
                            $subfield=array('sp_subid1','sp_subid2','sp_subid3','sp_subid4','sp_subid5','sp_subid6','sp_subid7','sp_subid8','sp_subid9','sp_subid10');
                                $subdata=array(
                                'sp_smid' =>$record->sp_smid,
                                'sp_sccode'   =>$scid ,
                                'sp_programid' =>$filprg_name,
                                'sp_acadyear' =>$academicyear,
                               //	 'sp_semester'  =>$semester
                                        
                            );
                            $stud_prg_sub = $this->cmodel->get_listspficemore('student_program',$subfield,$subdata);
                            if(!empty($stud_prg_sub[0]->sp_subid1)){
                            $sub1=$this->cmodel->get_listspfic1('subject','sub_name','sub_id',$stud_prg_sub[0]->sp_subid1)->sub_name;
                            $subject=$sub1;
                            }
                            if(! empty($stud_prg_sub[0]->sp_subid2)){
                            $sub2=$this->cmodel->get_listspfic1('subject','sub_name','sub_id',$stud_prg_sub[0]->sp_subid2)->sub_name;
                            $subject=$subject.",".$sub2;
                            }                            
                            if(!empty($stud_prg_sub[0]->sp_subid3)){
                            $sub3=$this->cmodel->get_listspfic1('subject','sub_name','sub_id',$stud_prg_sub[0]->sp_subid3)->sub_name;
                            $subject=$subject.",".$sub3;
                            }
                            if(!empty($stud_prg_sub[0]->sp_subid4)){
                            $sub4=$this->cmodel->get_listspfic1('subject','sub_name','sub_id',$stud_prg_sub[0]->sp_subid4)->sub_name;
                            $subject=$subject.",".$sub4;                          
                            }
                            if(!empty($stud_prg_sub[0]->sp_subid5)){
                            $sub5=$this->cmodel->get_listspfic1('subject','sub_name','sub_id',$stud_prg_sub[0]->sp_subid5)->sub_name;
                            $subject=$subject.",".$sub5;                        
                            }
                            if(!empty($record->sp_subid6)){
                            $sub6=$this->cmodel->get_listspfic1('subject','sub_name','sub_id',$record->sp_subid6)->sub_name;
                            $subject=$subject.",".$sub6;                          
                            }
                            if(!empty($stud_prg_sub[0]->sp_subid7)){
                            $sub7=$this->cmodel->get_listspfic1('subject','sub_name','sub_id',$stud_prg_sub[0]->sp_subid7)->sub_name;
                            $subject=$subject.",".$sub7;                           
                            }
                            if(!empty($stud_prg_sub[0]->sp_subid8)){
                            $sub8=$this->cmodel->get_listspfic1('subject','sub_name','sub_id',$stud_prg_sub[0]->sp_subid8)->sub_name;
                            $subject=$subject.",".$sub8;
                            }
                            if(!empty($stud_prg_sub[0]->sp_subid9)){
                            $sub9=$this->cmodel->get_listspfic1('subject','sub_name','sub_id',$stud_prg_sub[0]->sp_subid9)->sub_name;
                            $subject=$subject.",".$sub9;                            
                            }
                            if(!empty($stud_prg_sub[0]->sp_subid10)){
                            $sub10=$this->cmodel->get_listspfic1('subject','sub_name','sub_id',$stud_prg_sub[0]->sp_subid10)->sub_name;
                            $subject=$subject.",".$sub10;                           
                            }
                            echo $subject;
                        }
                        ?>
                        </td>
                        
                        <td><?php echo $this->cmodel->get_listspfic1('student_master','sm_email','sm_id',$record->sp_smid)->sm_email;?></td>
                        <td><?php echo $this->cmodel->get_listspfic1('student_master','sm_mobile','sm_id',$record->sp_smid)->sm_mobile;?></td>
                    
                    </tr>
                 <?php };?>  
                <?php else : ?>
                <tr><td colspan= "10" align="center"> No Records found...!</td></tr>
                <?php endif;?>
            
            </tbody>  
        </table> 
       </form>
        <!--?= $this->pagination->create_links();?-->
        <div><?php $this->load->view('template/footer'); ?></div>  
    </body>
</html>
