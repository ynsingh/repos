<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name listfac.php 
  @author Kriti Srivastava (shona.kriti1996@gmail.com)
 -->
<html>
<title>View Students List</title>
    <head>    
        <?php $this->load->view('template/header'); ?>
        <?php $this->load->view('template/menu');?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
    </head>
    <body>
<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
<table style="width:100%;">
	<tr><td>
		<?php
                echo "<td align=\"left\" width=\"33%\">";
                echo "<td align=\"center\" width=\"34%\">";
                echo "<b>Student List Details</b>";
                echo "</td>";
                echo "<td align=\"right\" width=\"33%\">";
                $help_uri = site_url()."/help/helpdoc#ViewStudentlistwithHead";
                echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                ?>
              </td></tr>
	</table>
	<div class="scroller_sub_page">
        <table  class="TFtable" >
            <thead><tr>
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
                                        echo " <td>";
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
                                        echo " <td>";
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
        </table></div>

 </div><?php $this->load->view('template/footer'); ?></div>
    </body>
</html>

