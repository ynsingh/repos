<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name listfac.php 
  @author Kriti Srivastava (shona.kriti1996@gmail.com)
 -->
<html>
<title>View Students List</title>
<!--<link rel="shortcut icon" href="<?php //echo base_url('assets/images'); ?>/index.jpg">-->
	<link rel="icon" href="<?php echo base_url('uploads/logo'); ?>/nitsindex.png" type="image/png">	
    <head>    
        <?php $this->load->view('template/header'); ?>
        <?php //$this->load->view('template/menu');?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
    </head>
    <body>
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->
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
               <!-- <th>Address</th>-->
                <th>Email-Id</th>
                <th>Mobile No</th>
                <!--<th>Institute Name</th>-->
                <th>Department Name</th>
                <th>Program  Name(Branch)</th>
                <th>Current Semester</th>
                <!-- <th></th>-->
                </tr>
                <?php
			$scid=0;
                        if( count($this->tresult) ):
                                foreach($this->tresult as $row){
						
						$stufname = $this->logmodel->get_listspfic1('userprofile','firstname','userid',$row->userid)->firstname;
						$stulname = $this->logmodel->get_listspfic1('userprofile','lastname','userid',$row->userid)->lastname;
						$smid=$this->commodel->get_listspfic1('student_master','sm_id','sm_userid',$row->userid)->sm_id;
						$stu_fathername=$this->commodel->get_listspfic1('student_parent','spar_fathername','spar_smid',$smid)->spar_fathername;
						//$stu_add = $this->logmodel->get_listspfic1('userprofile','address','userid',$row->userid)->address;
						$stu_add = $this->commodel->get_listspfic1('student_parent','spar_paddress','spar_smid',$smid)->spar_paddress.','.$this->commodel->get_listspfic1('student_parent','spar_pcity','spar_smid',$smid)->spar_pcity.','.$this->commodel->get_listspfic1('student_parent','spar_pstate','spar_smid',$smid)->spar_pstate.','.$this->commodel->get_listspfic1('student_parent','spar_pcountry','spar_smid',$smid)->spar_pcountry;
						$username = $this->logmodel->get_listspfic1('edrpuser','username','id',$row->userid)->username;
						$stu_mobile = $this->logmodel->get_listspfic1('userprofile','mobile','userid',$row->userid)->mobile;
						//$stu_center = $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$row->scid)->sc_name;
						$stu_dept = $this->commodel->get_listspfic1('Department','dept_name','dept_id',$row->deptid)->dept_name;
						$prgid = $this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$smid)->sp_programid;
                  $prgname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name;
						$prgbranch = $this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch;

					/*if($scid !=$row->scid){
				?>
					<tr>
						<td colspan=10 style="font-size:18px;text-align:center;">
						<b>Institute Name :</b> 
                        			<?php
                          				echo  $this->commodel->get_listspfic1('org_profile','org_name','org_id',$row->scid)->org_name;
                        			?></td>
					</tr>
                     				<?php $scid =$row->scid;
						//$serial_no = 1;
					}	*/
					
                                        echo "<tr>";
                                        echo " <td>";
					if(!empty($stufname)){
                                        echo $stufname; }
                                       if(!empty($stulname)){ echo $stulname; }
					echo " </td>";
 					
                                        echo "<td align=\"center\">";
                                       if(!empty($stu_fathername)){ echo $stu_fathername; }
                                        echo "</td>";
                                       // echo " <td align=\"center\">";
                                        //if(!empty($stu_add)){ echo $stu_add; }
                                        //echo " </td>";
                                        echo " <td align=\"center\">";
                                        if(!empty($username)){ echo $username;}
                                        echo " </td>";
                                        echo " <td align=\"center\"> ";
                                        if(!empty($stu_mobile)){ echo $stu_mobile; }
                                        echo "</td>";
                                       // echo " <td align=\"center\">";
                                       // if(!empty($stu_center)){ echo $stu_center; }
                                      //  echo "</td>";
                                        echo " <td align=\"center\">";
                                        if(!empty($stu_dept)){ echo $stu_dept; }
                                        echo "</td>";
                                        echo " <td align=\"center\">";
                                      	if(!empty($stu_dept)){ echo $prgname; }
					echo " ( ";
                                        if(!empty($prgbranch)){ echo $prgbranch; }
					echo " ) ";
                                        echo "</td>";
                                        echo " <td>";
					$semester=$this->commodel->get_listspficarry('student_program','sp_semester','sp_smid ',$smid);
					foreach($semester as $row1){
						$sem= $row1->sp_semester;
					}
					if(!empty($sem)){ echo $sem; }
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

