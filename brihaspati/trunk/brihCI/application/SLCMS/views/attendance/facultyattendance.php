<!--@filename attendencereport.php  @author Sharad Singh(sharad23nov@gmail.com) 
    faculty attanedance report
-->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Attendence Report</title>
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
         <table width=100%">
          <tr><td>
              <?php
                 echo "<td align=\"left\" width=\"50%\">";
                 echo anchor('Attendencereport/attendencereport/', "Student Attendance Report ", array('title' => 'Add Detail', 'class' =>'top_parent'));
                 echo "</td>";
                 echo "<td align=\"left\" width=\"50%\">";
                 echo "<b>Faculty Attendance</b>";
                 echo "</td>";
                 ?>

                <div>
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                 <?php if(isset($_SESSION['success'])){?>
                    <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                <?php
                };
                ?>
                <?php if(isset($_SESSION['err_message'])){?>
                        <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                <?php
                };
                ?>
                </div>
           </td></tr>
        </table>


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
        <form action="<?php echo site_url('Attendencereport/facultyattendance');?>" method="POST">
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
                <option selected="true" disabled>semester</option>
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
            </tr>
        </table>
        <br><br>

        <?php 
            if(!empty($prgid)){
            echo "<table align=\"center\">";
            //echo $prgid;
            $prname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name;
            $prbr = $this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch;
            $comprog = $prname."(".$prbr.")";
            echo "<tr><td width=\"\">";
            echo form_label('Program Name : ', 'prgname') ;
            echo "</td><td width=\"\">"; echo $comprog;
            echo "</td>";
            echo "<td width=\"\">";
            echo form_label('Semester : ', 'sem') ;
            echo "</td><td width=\"\">"; echo $sem;
            echo "</td></tr>";    
            echo "</table>";
            }
        ?>
        <br><br> 
        <table style="width:100%"; class="TFtable">
            <thead>
            <tr style="text-align: center;">
            <th>Sr.No</th>
            <th>Faculty Name</th>
            <th>Subject Name</th>
            <th>Paper Name</th>
            <th>Lecture held</th>
            <th>Required Lecture</th>
            <th>Difference</th>
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
            //print_r($prgsubpap);
            $srno = 1;
            foreach($prgsubpap as $row)
            {//1
                //echo "----Start----";echo "<br>";
                $prgid = $prgid;
                $sem = $sem;
                $acadyear = $academicyear;
                $sub = $row->pstp_subid;
                $pap = $row->pstp_papid;
                $facultyid = $row->pstp_teachid;
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
                //print_r($attendencedate);
                $attenddate = array();
                //$startdate = $startdate;

                //get total no of lecture in a subject/paper
                //echo sizeof($attendencedate);
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
            ?>
            <tr>
                <td><?php echo $srno; ?></td>
                <?php
                    $fname = $this->email=$this->loginmodel->get_listspfic1('userprofile','firstname','userid',$facultyid)->firstname;
                    $lname = $this->email=$this->loginmodel->get_listspfic1('userprofile','lastname','userid',$facultyid)->lastname;
                ?>
                <td><?php echo $fname." ".$lname; ?></td>
                <td><?php echo $this->commodel->get_listspfic1('subject','sub_name','sub_id',$sub)->sub_name; ?></td>
                <td><?php if (!empty($this->commodel->get_listspfic1('subject','sub_name','sub_id',$pap)->sub_name))
                 echo $this->commodel->get_listspfic1('subject','sub_name','sub_id',$pap)->sub_name; ?></td>
                <td><?php echo sizeof($attendencedate); ?></td>
            <?php
                //calculate credit of lecture.
                $subcr = $this->commodel->get_listspfic1('subject','sub_ext1','sub_id',$sub)->sub_ext1;        
            ?>
                
                <td><?php echo $reqlect = $subcr * 4 ?></td>
                <?php  sizeof($attendencedate); ?>
                <td><?php echo $reqlect - sizeof($attendencedate); ?></td>
                
            </tr>
            
            <?php
            $srno = $srno + 1;
            }//1
            ?>
             
                    
        
        </table>
    
        </form>
<!--<div>
</div>-->
        <div><?php $this->load->view('template/footer'); ?></div>
    </body>
</html>

