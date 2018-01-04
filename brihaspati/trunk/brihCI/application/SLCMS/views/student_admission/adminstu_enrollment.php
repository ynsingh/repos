<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>View Students List</title>
    <head>    
        <?php $this->load->view('template/header'); ?>
        <?php //$this->load->view('template/menu');?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>	
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
<table style="width:100%;">
        <tr><td>
                <?php
                echo "<td align=\"center\" width=\"34%\">";
                echo "<b>Student Enrollment List Details</b>";
                echo "</td>";
                echo "<td align=\"right\" width=\"33%\">";
                $help_uri = site_url()."/help/helpdoc#ViewStudentlistwithHead";
                echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
		echo "</td>";	
                ?>
              </td></tr>
        </table>
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->

<form action="<?php echo site_url('adminadmissionstu/listenrolladminstu'); ?>" method="POST">
   	<table>
		<tr>
		<td valign=top>
<a href="<?php echo site_url('adminadmissionstu/genenrolladminstu/');?>" style="text-decoration:none;"><b>Generate All Student Enrollment Number</b></a>
</td>

			<td align=left>
				<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery-ui.min.css">
  				<script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.min.js" ></script>
        			<input type="text" name="stu_sdate" placeholder="Start Date" value="<?php echo isset($_POST["stu_sdate"]) ? $_POST["stu_sdate"] : ''; ?>" id="sdate" style="height:30px;font-size:18px;" size=20 />
        
            			<script>
               				 $('#sdate').datepicker({
                			 onSelect: function(value, ui) {
                    			 	console.log(ui.selectedYear)
                    			 	var today = new Date(), 
                    			 	dob = new Date(value), 
                   				age = 2017-ui.selectedYear;
			                 },
                
                    			changeMonth: true,
                    			changeYear: true,
                    			dateFormat: 'yy-mm-dd',
                   
                   			yearRange: 'c-36:c+10',
                			});
            			</script>
	
        		<input type="text" name="stu_edate" placeholder="End Date" value="<?php echo isset($_POST["stu_edate"]) ? $_POST["stu_edate"] : ''; ?>" id="edate" style="height:30px;font-size:18px;" size=20 />
        
            			<script>
               				 $('#edate').datepicker({
                			 onSelect: function(value, ui) {
                    			 	console.log(ui.selectedYear)
                    			 	var today = new Date(), 
                    			 	dob = new Date(value), 
                   				age = 2017-ui.selectedYear;
			                 },
                
                    			changeMonth: true,
                    			changeYear: true,
                    			dateFormat: 'yy-mm-dd',
                   
                   			 yearRange: 'c-36:c+10',
                			});
            			</script>
		
			<select name="stu_dept" style="font-size:18px;height:30px;">
				<option disabled selected>Select Department</option>
				<?php foreach($deptlist as $row){?>
					<option value="<?php echo $row->dept_id;?>"><?php echo $row->dept_name; ?></option>
				<?php }?>
			</select>
		
	
			<input type="submit" name="search" value="Search" style="height:30px;font-size:18px;">
		</td>
		
		</tr>
	</table>
   </form>


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
<!---------------------------------------All Data Show Without Search-------------------------------------------------->
                <?php
		//if(isset($getstuid)){
		if(!empty($getstuid)){
                        if( count($getstuid) ):
				
                                foreach($getstuid as $row){

				 echo "<tr>";
					$suserid=$this->commodel->get_listspfic1('student_master','sm_userid','sm_id',$row->sp_smid)->sm_userid;
					
                                        echo " <td>";
                                        echo $this->commodel->get_listspfic1('student_master','sm_fname','sm_id',$row->sp_smid)->sm_fname;
                                      
                                        echo " </td>";
					
                                        echo "<td align=\"center\">";
                                        echo $this->commodel->get_listspfic1('student_parent','spar_fathername','spar_smid',$row->sp_smid)->spar_fathername;
                                        echo "</td>";
					
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
                                       	echo $this->commodel->get_listspfic1('student_master','sm_email','sm_id',$row->sp_smid)->sm_email;
                                        echo " </td>";
                                        echo " <td align=\"center\"> ";
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
                                        echo $this->commodel->get_listspfic1('program','prg_name','prg_id',$row->sp_programid)->prg_name;
                                        echo " ( ";
                                        echo $this->commodel->get_listspfic1('program','prg_branch','prg_id',$row->sp_programid)->prg_branch;
                                        echo " ) ";
                                        echo "</td>";
                                        echo " <td>";
					echo $this->commodel->get_listspfic1('student_program','sp_semester','sp_smid ',$row->sp_smid)->sp_semester;
                                        echo "</td>";
                                        echo "</tr>";

                                };
                        else :
                                echo "<td colspan=\"16\" align=\"center\"> No Records found...!</td>";
                        endif;
		}//}
                ?>


            </thead>
        </table></div>

 </div><?php $this->load->view('template/footer'); ?></div>
    </body>
</html>

