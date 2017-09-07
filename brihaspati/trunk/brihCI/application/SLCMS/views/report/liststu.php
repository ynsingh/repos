<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name listfac.php 
  @author Kriti Srivastava (shona.kriti1996@gmail.com)
 -->
<html>
<title>View Students List</title>
    <head>    
        <?php $this->load->view('template/header'); ?>
        <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
        <?php $this->load->view('template/menu');?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
    </head>
    <body>
        <table style="margin-left:10px;">

            <tr colspan="2"><td>
             <?php
 //                echo anchor('setup/stulist/', "Add Students list ",array('title' => 'student list Detail ' , 'class' => 'top_parent'));
		echo " ";
                 $help_uri = site_url()."/help/helpdoc#ViewStudentlistwithHead";
                 echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:71%\">Click for Help</b></a>";
                ?>


            <div  style="margin-left:-06px;width:1793px;">

                <?php echo validation_errors('<div class="isa_warning>','</div>');?>

                <?php if(isset($_SESSION['success'])){?>
                    <div style="margin-left:30px;" class="isa_success"><?php echo $_SESSION['success'];?></div>

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
                <table cellpadding="16" style="margin-left:30px;" class="TFtable" >
            <thead>
                <tr align="left">
                <th>Student Name</th>
                <th>Father's Name</th>
                <th>Address</th>
                <th>Email-Id</th>
                <th>Mobile No</th>
                <th>Campus Name</th>
                <th>Department Name</th>
                <th>Program  Name(Branch)</th>
                <th>Current Semester</th>
                <!-- <th></th>-->
                </tr>
                <?php
                        if( count($this->tresult) ):
                                foreach($this->tresult as $row){
                                        echo "<tr>";
                                        echo " <td align=\"center\">";
                                        echo $this->logmodel->get_listspfic1('userprofile','firstname','userid',$row->userid)->firstname;
                                        echo $this->logmodel->get_listspfic1('userprofile','lastname','userid',$row->userid)->lastname;
					echo " </td>";
 					$smid=$this->commodel->get_listspfic1('student_master','sm_id','sm_userid',$row->userid)->sm_id;
                                        echo "<td align=\"center\">";
                                        echo $this->commodel->get_listspfic1('student_parent','spar_fathername','spar_smid',$smid)->spar_fathername;
                                        echo "</td>";
                                        echo " <td align=\"center\">";
                                        echo $this->logmodel->get_listspfic1('userprofile','address','userid',$row->userid)->address;
                                        echo " </td>";
                                        echo " <td align=\"center\">";
                                        echo $this->logmodel->get_listspfic1('edrpuser','username','id',$row->userid)->username;
                                        echo " </td>";
                                        echo " <td align=\"center\"> ";
                                        echo $this->logmodel->get_listspfic1('userprofile','mobile','userid',$row->userid)->mobile;
                                        echo "</td>";
                                        echo " <td align=\"center\">";
                                        echo $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$row->scid)->sc_name;
                                        echo "</td>";
                                        echo " <td align=\"center\">";
                                        echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$row->deptid)->dept_name;
                                        echo "</td>";
                                        echo " <td align=\"center\">";
                                        $prgid=$this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$smid)->sp_programid;
                                        echo $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name;
					echo " ( ";
                                        echo $this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch;
					echo " ) ";
                                        echo "</td>";
                                        echo " <td align=\"center\">";
					$semester=$this->commodel->get_listspficarry('student_program','sp_semester','sp_smid ',$smid);
					foreach($semester as $row1){
						$sem= $row1->sp_semester;
					}
					echo $sem;
                                        echo "</td>";
                                        echo "</tr>";

                                };
                        else :
                                echo "<td colspan=\"16\" align=\"center\"> No Records found...!</td>";
                        endif;

                ?>
            </thead>
        </table>
    </body>
</html>

