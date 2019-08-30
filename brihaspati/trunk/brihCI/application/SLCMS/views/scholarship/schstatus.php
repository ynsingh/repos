<!--@name schstatus.php 
    @author Krishna Sahu(krishnasahu2406@gmail.com)
-->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
	<head>
 		<title>View Scholarship Details</title> 
 		<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/profile.css">
		<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
	</head>

<body>
   <?php $this->load->view('template/header'); ?>
		 <table width="100%">
          <tr><td>
             <div>
                
					 <?php echo "<td align=\"center\" width=\"34%\" style=\"font-size:16px\">";
                    	 echo "<b>Scholarship Status</b>";
                    	 echo "</td>";
					 ?>
                <?php
                echo "<td align=\"right\" width=\"34%\">";
                $help_uri = site_url()."/help/helpdoc#Role";
                echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                echo "</td>";
                ?>
                <div  style="width:100%;">
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
		  <div class="scroller_sub_page">
 		  <table class="TFtable" >
         <thead>
			<tr>
                    	<th>S.No</th>
                    	<th>&nbsp;&nbsp;Student &nbsp;&nbsp;Name</th>							
			<th>Details</th>			
			<th>Category&nbsp;&nbsp;</th>
			<th>Email-Id/Aadhaar Number</th>
			<th>Program Details</th>
			
			<th>Scholarship Name</th>
			<th>Reason for Rejecting</th>
			<th>Status</th>
			</tr>
	  </thead>
			 <tbody>
				<?php
		 			if( count($result) ):
        			$count =0;
               foreach ($result as $row)
        			{
 	$this->schname=$this->commodel->get_listspfic2('schapply','sa_id', 'sa_name');
	$suid=$this->commodel->get_listspfic1("schapply","sa_userid")->sa_userid;
	$Stuid=$this->commodel->get_listspfic1("student_master","sm_id","sm_userid",$suid)->sm_id;
	$this->cateid=$this->commodel->get_listspfic1('student_master','sm_category','sm_id',$Stuid)->sm_category;
	$this->ncid = $this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$Stuid)->sp_programid;
	$this->depid=$this->commodel->get_listspfic1('student_program','sp_deptid','sp_smid',$Stuid)->sp_deptid;
	$this->said=$this->commodel->get_listspfic1('schapply','sa_name','sa_name',$row->sa_name)->sa_name;	
	$this->fathname=$this->commodel->get_listspfic1('student_parent','spar_fathername','spar_smid',$Stuid)->spar_fathername;
        $this->gender=$this->commodel->get_listspfic1('student_master','sm_gender','sm_id',$Stuid)->sm_gender;
	$this->dob=$this->commodel->get_listspfic1('student_master','sm_dob','sm_id',$Stuid)->sm_dob;
	$this->dobb=explode(" ",$this->dob);
	$this->mobile=$this->commodel->get_listspfic1('student_master','sm_mobile','sm_id',$Stuid)->sm_mobile;
	$this->pname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$this->ncid)->prg_name;
	$this->depname=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$this->depid)->dept_name;
	$this->sem = $this->commodel->get_listspfic1('student_program','sp_semester','sp_smid',$Stuid)->sp_semester;
	$this->email= $this->commodel->get_listspfic1('student_master','sm_email','sm_id',$Stuid)->sm_email;
	$this->uid=$this->commodel->get_listspfic1('student_master','sm_uid','sm_id',$Stuid)->sm_uid;
         	?>
            <tr>
            	<td> <?php echo ++$count; ?> </td>
            	<td><?php echo $this->name=$this->commodel->get_listspfic1("student_master","sm_fname","sm_userid",$suid)->sm_fname; ?></td>
		<td><?php echo "<b>Father Name-: </b>".$this->fathname."<br/> "."<b>Gender-: </b>".$this->gender."<br/> "."<b>Date of Birth-: </b>".$this->dobb[0]."<br/> "."<b>Mobile/Phone No.-: </b>".$this->mobile; ?></td>
		
		<td><?php echo $this->catename=$this->commodel->get_listspfic1('category','cat_name','cat_id',$this->cateid)->cat_name; ?></td>
		<td><?php echo $this->email."<br/> "."<b>Aadhaar-: </b>".$this->uid; ?></td>
		
		<td><?php echo "<b>Program Name-: </b>".$this->pname."<br/> "."<b>Department Name-: </b>".$this->depname."<br/> "."<b>Semester-: </b>".$this->sem;  ?></td>
		
		<td><?php echo $this->saname=$this->commodel->get_listspfic1('scholarship','sch_name','sch_id',$this->said)->sch_name; ?></td>
		<td><?php echo $reason = $row->sa_rejres; ?></td>
            	<td> <?php $lst = $row->sa_status;
			if($lst == 0){
					?><font color="blue"><?php echo "Pending"; ?></font>
					<?php
					}
			if($lst == 1){
					?><font color="green"><?php echo "Approved"; ?></font>
					<?php
					}
			if($lst == 2){
					?><font color="red"><?php echo "Rejected"; ?></font>
					<?php
					}
 					?></td>
	         </tr>
				<?php
		        }
			   ?>
				<?php else : ?>
              <td colspan= "10" align="center"> No Records found...!</td>
            <?php endif;?>
			</tbody>
		</table></div>
</body>
<div align="center"> <?php $this->load->view('template/footer');?></div>
</html>
