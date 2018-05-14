<!--@name approvedscholar.php 
  @author Krishna Sahu(krishnasahu2406@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>

<title>View Approved Scholarship Requests</title>
  <head>
   <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
   <?php $this->load->view('template/header'); ?>
  </head>
<body>
    <table width="100%">
         <tr align="left">
             	 <td width="10%">
		 <?php echo anchor('scholarship/schapplydet/', "Pending Scholarship", array('title' => 'Pending Scholarship Requests' ,'class' =>'top_parent'));?>
				 </td>
				 <td  width="10%"><?php echo anchor('scholarship/rejectedscholar/', "Rejected Scholarship", array('title' => 'Rejected Scholarship Requests' ,'class' =>'top_parent'));?>
				 </td>
				 <?php
					 echo "<td align=\"center\"width=\"27%\">";
					 echo "<b>Approved Scholarship Requests</b>";
					 echo "</td>";
				 ?>
				 <td align="right" width="20%">
             <?php
                $help_uri = site_url()."/help/helpdoc#ViewRoleDetail";
                echo "<a target=\"_blank\" href=$help_uri>Click for Help</a>";
             ?>
				 </td>
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
         </tr>
	</table>
	
         <div class="scroller_sub_page">
   				<table class="TFtable">
   	         <thead>
			<tr>
                    	<th>S.No</th>
                    	<th>&nbsp;&nbsp;Student &nbsp;&nbsp;Name</th>							
			<th>Details</th>			
			
			<th>Category&nbsp;&nbsp;&nbsp;&nbsp;</th>
			
			<th>Email-Id/Aadhaar Number</th>
			<th>Program Details</th>
			
			<th>Scholarship Name</th>
			<th>Status</th>
			</tr>
	  </thead>
					<tbody>
          	<?php
			$suid=$this->session->userdata('id_user');
                        
		       //$Stuid=$this->commodel->get_listspfic1("student_master","sm_id","sm_userid",$suid)->sm_id;
        			$count =0;
//			print_r($this->fldata); die;

					if(!empty($this->fldata)){

				   	foreach ($this->fldata as $row)
        			   {  ?>
					 
					<tr>
					<td><?php echo ++$count; ?></td>
						<td><?php echo $row['stname']; ?></td>
						<td><?php echo "<b>Father Name-: </b>".$row['stfname']."<br/> "."<b>Gender-: </b>".$row['stgender']."<br/> "."<b>Date of Birth-: </b>".$row['stdob']."<br/> "."<b>Mobile/Phone No.-: </b>".$row['stmobile']; ?>
						
						<td><?php echo $row['stcatename']; ?></td>
						<td><?php echo $row['stemail']."<br/> "."<b>Aadhaar-: </b>".$row['stuid']; ?>
						
						<td><?php echo "<b>Program Name-: </b>".$row['stpname']."<br/> "."<b>Department Name-: </b>".$row['stdepname']."<br/> "."<b>Semester-: </b>".$row['stsem']; ?></td>
						
						<td><?php echo $row['stsaname']; ?></td>

         	
					
				
					<td> <font color="green"><?php echo "Approved"; ?></font> </td>
					</tr>
					<?php
					};}
					else {
                    echo "<td colspan=\"10\" align=\"center\"> No Records found...!</td>";
                    }?> 					
         	</tbody>
   				</table>
        			</div>
</body>
<br><br>
<div align="center">  <?php $this->load->view('template/footer');?></div>
</html>
