<!--@filename attendencereport.php  @author Sharad Singh(sharad23nov@gmail.com) -->
<!--@filename attendencereport.php @author Neha khullar(nehukhullar@gmail.com)Modifications --> 

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Attendence Report</title>
		<!--<link rel="shortcut icon" href="<?php //echo base_url('assets/images'); ?>/index.jpg">-->
	<link rel="icon" href="<?php echo base_url('uploads/logo'); ?>/nitsindex.png" type="image/png">	

        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">

        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/bootstrap.min.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>

    <script>
    function getsemester(sem){
            var sem = sem;
                $.ajax({
                type: "POST",
                url: "<?php echo base_url();?>slcmsindex.php/attendencereport/semester_get",
                data: {"program_branch" : sem},
                dataType:"html",
                success: function(data){
                    $('#semester').html(data.replace(/^"|"$/g, ''));
                }
                });
    }
    function getsubject(val){
            var prg_id = $('#program_branch').val();
            var sem=val;
            var combid = prg_id+","+sem;
                $.ajax({
                type: "POST",
                url: "<?php echo base_url();?>slcmsindex.php/attendencereport/subject_get",
                data: {"prgbrnch" : combid},
                dataType:"html",
                success: function(data){
                    $('#subjectname').html(data.replace(/^"|"$/g, ''));
                }
             });
    }
    function getpaper(pap){
            var prg_id = $('#program_branch').val();
            var sem = $('#semester').val();
            var pap = pap;
            var combid = prg_id+","+sem+","+pap;
                $.ajax({
                type: "POST",
                url: "<?php echo base_url();?>slcmsindex.php/attendencereport/paper_get",
                data: {"subjectname" : combid},
                dataType:"html",
                success: function(data){
                    //alert(data);
                    $('#papername').html(data.replace(/^"|"$/g, ''));
                }
             });
    }
    </script>

    </head>
    <body>
        <div>
            <?php $this->load->view('template/header'); ?>
        </div>
        <br/>
        <br>

        <?php
                    echo "<table style=\"padding: 20px 8px 8px 20px;width:90%;\">";
                    echo "<tr valign=\"top\">";
                    echo "<td>";
                    //$help_uri = site_url()."/studenthome/student_attendence_view";
                    //echo "<a href=$help_uri><b style=\"margin-left:8%;position:absolute;margin-top:-1.6%\">View Student Attendence</b></a>";
                    echo "</td>";
                    echo "<td>";
                    //$help_uri = site_url()."/help/helpdocfaculty#StudentList";
                    //echo "<a href=$help_uri><b style=\"float:right;margin-top:-1.6%\">Click for Help</b></a>";
                    echo "</td>";
                    echo "</tr>";
                    echo "</table>";
        ?>

        <div align="left" style="margin-left:30px;width:90%;font-size:18px;">
            <?php echo validation_errors('<div class="isa_warning">','</div>');?>
            <?php echo form_error('<div style="margin-left:30px;" class="">','</div>');?>
            <?php if(isset($_SESSION['success'])){?>
        <div class="alert alert-success"><?php echo $_SESSION['success'];?></div>
        <?php
         };
        ?>

        <?php if(isset($_SESSION['err_message'])){?>
        <div class="" style='margin-left:30px;width:1680px;font-size:18px;'><div ><?php echo $_SESSION['err_message'];?></div>
        </div>
        <?php
        };
    ?>
        </div>

        <form action="<?php echo site_url('Attendencereport/attendencereport');?>" method="POST">
        <table style="width:100%;" >
            <tr style="font-weight:bold; background-color:lightslategray;">
                <td> <span style="color:white;margin-left:2px;">Select Program :</span>
                    <select name="program_branch" id="program_branch" onchange="getsemester(this.value);">
                    <option selected="true" disabled>Select Program</option>
                    <?php foreach($prgsublist as $prgdata): ?>
                        <option value="<?php echo $prgdata->prg_id; ?>">
                        <?php echo $prgdata->prg_name .'('.$prgdata->prg_branch.')';

                        //$this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgdata->pstp_prgid)->prg_branch.")" ;?>
                        </option>
                    <?php endforeach; ?>
                    </select>
                </td>

                <td><span style="color:white;">Semester :</span>
                <select name="semester" id="semester" onchange="getsubject(this.value);">
                <option selected="true" disabled>Semester</option>
                </select>
                </td>
<!--
                <td><span style="color:white;">Subject Name :</span>
                    <select name="subjectname" id="subjectname" onchange="getpaper(this.value);">
                        <option selected="selected" disabled selected>subject name</option>

                    </select>
                </td>
                <td><span style="color:white;">Subject Paper Name :</span>
                    <select name="papername" id="papername" onchange="">
                        <option selected="selected" disabled selected>subject paper name</option>
                    </select>
                </td>
-->
                    <td><input type="submit" name="search" value="Search" />
                </td>
        </table>
        <br><br>

        <?php 
        //print_r($prgsubpap);
        ?>
        <table style="width:100%"; class="TFtable">
            <thead>
            <tr style="text-align: center;">
            <th>Sr.No</th>
            <th>Student Name</th>
            <th>Enrollment No</th>
            <th>Roll No</th>
            <th>Total Lecture</th>
            <th>Attendance</th>
            </tr>
            </thead>

            <?php
/*
    get one by one subject/paper from subjectpaper array .
        then get total no of lecture (lectutedates) in subjectpaper,prgid,sem,acadyear in session.
            then get total no of students according in subjectpaper,prgid,sem,acadyear in session.
                then get student_attendence in date.
*/

            $alllecttotal = 0;
            $studenttotalattendance = array();
            //$count = 0;
            $testarr = array();
            $allstud = array();
            $alllect = array();
            foreach($prgsubpap as $row)
            {//1
		
                //echo "----Start----";echo "<br>";
                $prgid = $prgid;
                $sem = $sem;
                $acadyear = $academicyear;
                $sub = $row->pstp_subid;
                $pap = $row->pstp_papid;
                //echo "PrgId --->". $prgid. "Sem--->". $sem. "acadyear--->". $acadyear. "sub--->". $sub. "pap--->". $pap;
                //echo "<br>";
                $getalllecturedate = array('satd_adate');
                
                $wdata1 = array(
                'satd_prgid' => $prgid,
                'satd_sem' => $sem,
                'satd_subid' => $sub,
                'satd_papid' => $pap,
                'satd_acadyear' => $acadyear
                );
                $attendencedate = $this->commodel->get_distinctrecord('student_attendance',$getalllecturedate,$wdata1);
                $attenddate = array();
                //$startdate = $startdate;

                //get total no of lecture in a subject/paper

                foreach($attendencedate as $row)
                {
                    $attend_date = $row->satd_adate;
                    if ($attend_date >= $startdate && $attend_date <= $enddate)
                    {
                        $attenddate[] = $attend_date;
                        $testarr = $attend_date;
                    }
                }
                //echo "Attend Date-->";print_r($attenddate) ; echo "<br>"; 
                $alllecttotal = $alllecttotal + sizeof($attenddate);

                //get all student in a subject/paper,semesdeter, academic year and program

                $wheredata = "((sp_programid = $prgid) AND (sp_semester = $sem) AND (sp_acadyear = '$acadyear') AND ((sp_subid1 = $sub) OR (sp_subid2 = $sub) OR (sp_subid3 = $sub) OR (sp_subid4 = $sub) OR (sp_subid5 = $sub) OR (sp_subid6 = $sub) OR (sp_subid7 = $sub) OR (sp_subid8 = $sub) OR (sp_subid9 = $sub) OR (sp_subid10 = $sub)))";
                //print_r($wheredata);
                $students_smid = array('sp_smid');
                $stud_prg_data = $this->commodel->get_listspficemore('student_program',$students_smid,$wheredata);
                //echo "Student id in subject--->";print_r($stud_prg_data); echo "<br>";
                
                //get student one by one 
                foreach($stud_prg_data as $studdetails)
                {
                    $stud_id = $studdetails->sp_smid;
                    
                    //get student attendance in lecture date
                    $count = 0;
                    foreach($attenddate as $todate)
                    {
                        $todate;
                       // $studwhere = array('satd_prgid' => $prgid, 'satd_sem' => $sem, 'satd_subid' => $subjectid, 'satd_papid' => $paperid, 'satd_acadyear' => $academicyear, 'satd_adate' => $todate, 'satd_smid' => $stud_smid);
			$studwhere = array('satd_prgid' => $prgid, 'satd_subid' => $sub, 'satd_papid' => $pap, 'satd_acadyear' => $academicyear, 'satd_adate' => $todate, 'satd_smid' => $stud_id);
                        $attendencefield = 'satd_astatus';
                        $attendencevalue = $this->commodel->get_listspficemore('student_attendance',$attendencefield,$studwhere);
			
                        foreach($attendencevalue as $row)
                        {
                            $present = $row->satd_astatus;
                            if($present = "P")
                                $count = $count + 1;
                        }
                    }
                    if(in_array($stud_id, $allstud)) 
                    {
                        //get index of array
                        $position = get_array_index($allstud, $stud_id);
			
                        $getlectval = $alllect[$position];
                        $count = $count + $getlectval;
                        $alllect[$position] = $count;
                        
                    }
                    else
                    {
                        $allstud[] = $stud_id;
                        $alllect[] = $count;
                    }

                } 
            //echo "<br>----End----<br>";        
            }//1
            //print_r($allstud);
            $srn = 0;
            for($i = 0; $i < sizeof($allstud); $i++)
            {
            ?>    
                <tr>
                    <td><?php echo ++$srn;  ?></td>
                <?php
                $studentid = $allstud[$i];
                $name = $this->commodel->get_listspfic1('student_master','sm_fname ','sm_id',$studentid);
		
                if(!empty($name))
                {
                    $fname = $name->sm_fname;
                    $mname=""; $lname="";
                    if (!empty($name->sm_mname))    
                        $mname = $name->sm_mname;
                    if (!empty($name->sm_lname))
                        $lname = $name->sm_lname;
                    $fullname = $fname." " .$mname." " .$lname;
                }
                ?>
                    <td><?php echo $fullname; ?>
                <td><?php echo $this->commodel->get_listspfic1('student_master','sm_enrollmentno','sm_id',$studentid)->sm_enrollmentno;?></td>
                <td><?php
                            $Rfield=array('senex_rollno');
                            $Rstdata=array(
                                'senex_smid' =>$studentid,
                                'senex_prgid' =>$prgid,
                            );
                            $stud_rollno = $this->commodel->get_listspficemore('student_entry_exit',$Rfield,$Rstdata);
                            if(!empty($stud_rollno[0]->senex_rollno)){
                                echo $stud_rollno[0]->senex_rollno;
                            }
                            else{
                                 $Rstdata2=array(
                                'senex_smid' =>$studentid,
                                'senex_prgid' =>$prgid,
                            );
                                $stud_rollno2 = $this->commodel->get_listspficemore('student_entry_exit',$Rfield,$Rstdata2);
                                if(!empty($stud_rollno2[0]->senex_rollno)){
                                    echo $stud_rollno2[0]->senex_rollno;
                                }

                            }
                        ;?>
                </td>
                <td><?php echo $alllecttotal?></td>
                    <?php 
                        $lect = $alllect[$i];
                        if($alllecttotal > 0)
                        {
                            $attendper = $lect * 100/$alllecttotal;?>
                            <td> <?php echo $attendper."%" ;?></td>
                        <?php
                        }
                        else
                            $attendper = "No lecture held";
                        ?>
                            <td> <?php echo $attendper ;?></td>
                                        
                </tr>
                    
        <?php    
        }
        ?>
        
        </table>
    
        </form>
<div>
</div>
        <div><?php $this->load->view('template/footer'); ?></div>
    </body>
</html>

