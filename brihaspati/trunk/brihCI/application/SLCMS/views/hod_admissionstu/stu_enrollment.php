<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>View Students List</title>
    <head>    
        <?php $this->load->view('template/header'); ?>
        <?php //$this->load->view('template/menu');?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
    </head>
    <body>
<?php echo validation_errors('<div class="isa_warning">','</div>');?>
        <?php echo form_error('<div class="">','</div>');?>
        <?php 
	    if(!empty($_SESSION['success'])){	
		if(isset($_SESSION['success'])){?>
         <div class="isa_success" style="font-size:18px;"><?php echo $_SESSION['success'];?></div>
         <?php
          } };
         ?>
        <?php 
	   if(!empty($_SESSION['err_message'])){		
		if(isset($_SESSION['err_message'])){?>
        <div class="isa_error"><div ><?php echo $_SESSION['err_message'];?></div></div>
        <?php
         } };
	?>  
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->
<table style="width:100%;">
        <tr><td>
		<td><a href="<?php echo site_url('hodadmissionstu/genenrollmentnumber/');?>" style="text-decoration:none;"><b>Generate Enrollment Number</b></a></td>
                <?php
                echo "<td align=\"center\" width=\"34%\">";
                echo "<b>Student List Details</b>";
                echo "</td>";
                echo "<td align=\"right\" width=\"33%\">";
                $help_uri = site_url()."/help/helpdoc#ViewStudentlistwithHead";
                echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
		echo "</td>";	
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
                <th>Enrollment No.</th>
                <th>Program  Name(Branch)</th>
                <th>Current Semester</th>
                </tr>
                <?php
                        if( count($getstuid) ):
				
                                foreach($getstuid as $row){
//					sp_smid,sp_programid,sp_semregdate
 echo "<tr>";
					$suserid=$this->commodel->get_listspfic1('student_master','sm_userid','sm_id',$row->sp_smid)->sm_userid;
					//$fname = $this->logmodel->get_listspfic1('userprofile','firstname','userid',$suserid)->firstname;
					//$laname = $this->logmodel->get_listspfic1('userprofile','lastname','userid',$suserid)->lastname;
					//if(!empty($fname)){
                                        echo " <td>";
                                        echo $this->commodel->get_listspfic1('student_master','sm_fname','sm_id',$row->sp_smid)->sm_fname;
                                       // echo $laname;
                                        echo " </td>";
					//}
					//else{ echo "<td></td>"; }
//                                      $smid=$this->commodel->get_listspfic1('student_master','sm_id','sm_userid',$row->userid)->sm_id;
                                        echo "<td align=\"center\">";
                                        echo $this->commodel->get_listspfic1('student_parent','spar_fathername','spar_smid',$row->sp_smid)->spar_fathername;
                                        echo "</td>";
					// echo $this->logmodel->get_listspfic1('userprofile','address','userid',$suserid)->address;
					
					$add = $this->commodel->get_listspfic1('student_parent','spar_paddress','spar_smid',$row->sp_smid)->spar_paddress;
					$addcity = $this->commodel->get_listspfic1('student_parent','spar_pcity','spar_smid',$row->sp_smid)->spar_pcity;
					$addstate = $this->commodel->get_listspfic1('student_parent','spar_pstate','spar_smid',$row->sp_smid)->spar_pstate;
					$addcountry = $this->commodel->get_listspfic1('student_parent','spar_pcountry','spar_smid',$row->sp_smid)->spar_pcountry;
					$addpincode = $this->commodel->get_listspfic1('student_parent','spar_ppincode','spar_smid',$row->sp_smid)->spar_ppincode;
					
                                        echo " <td align=\"center\">";
                                       	if(!empty($add)){
						echo $add.',';
					}
					if(!empty($addcity)){
						echo $addcity.',';
					}	
					if(!empty($addstate)){
						echo $addstate.',';
					}
					if(!empty($addcountry)){
						echo $addcountry.',';
					}
					if(!empty($addpincode)){
						echo $addpincode;
					}
					
                                        echo " </td>";
					
                                        echo " <td align=\"center\">";
                                       // echo $this->logmodel->get_listspfic1('edrpuser','username','id',$suserid)->username;
					echo $this->commodel->get_listspfic1('student_master','sm_email','sm_id',$row->sp_smid)->sm_email;
                                        echo " </td>";
                                        echo " <td align=\"center\"> ";
                                       // echo $this->logmodel->get_listspfic1('userprofile','mobile','userid',$suserid)->mobile;
					echo $this->commodel->get_listspfic1('student_master','sm_mobile','sm_id',$row->sp_smid)->sm_mobile;
                                        echo "</td>";
                                        echo " <td align=\"center\">";
					$scid = $this->commodel->get_listspfic1('student_master','sm_sccode','sm_id',$row->sp_smid)->sm_sccode;
                                        echo $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$scid)->sc_name;
                                        echo "</td>";
                                        echo " <td align=\"center\">";
					$deptid = $this->commodel->get_listspfic1('student_program','sp_deptid','sp_smid',$row->sp_smid)->sp_deptid;
                                        echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$deptid)->dept_name;
                                        echo "</td>";
                                        echo "</td>";
					$enrollno = $this->commodel->get_listspfic1('student_master','sm_enrollmentno','sm_id',$row->sp_smid)->sm_enrollmentno;
					if(!empty($enrollno)){
                                       		echo " <td align=\"center\">";
                                        	echo $enrollno;
                                        	echo "</td>";
					}else{
						echo "<td></td>";
					}
                                        echo " <td align=\"center\">";
                               //         $prgid=$this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$smid)->sp_programid;
                                        echo $this->commodel->get_listspfic1('program','prg_name','prg_id',$row->sp_programid)->prg_name;
                                        echo " ( ";
                                        echo $this->commodel->get_listspfic1('program','prg_branch','prg_id',$row->sp_programid)->prg_branch;
                                        echo " ) ";
                                        echo "</td>";
                                        echo " <td>";
					 $semester=$this->commodel->get_listspficarry('student_program','sp_semester','sp_smid ',$row->sp_smid);
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

