<!--@name schapplydet.php 
  @author Krishna Sahu (krishnasahu2406@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>

  <title>Scholar Applications</title>
  <head>
      <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
      <?php $this->load->view('template/header'); ?>

   </head>
<body>
    <table width="100%">
         <tr align="left">
             <td width="10%">
				 <?php echo anchor('scholarship/approvedscholar/', "Approved Scholarship", array('title' => 'Approved Scholarship Requests' ,'class' =>'top_parent'));?></td>
				 <td  width="10%"><?php echo anchor('scholarship/rejectedscholar/', "Rejected Scholarship", array('title' => 'Rejected Scholarship Requests' ,'class' =>'top_parent'));?></td>
				 <?php
					 echo "<td  align=\"center\"width=\"27%\">";
					 echo "<b>Scholarship Applications</b>";
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
   <table class="TFtable" cellspacing="30">
            <thead>
			<tr>
                    	<th>S.No&nbsp;&nbsp;</th>
			
                    	<th>Student Name&nbsp;&nbsp;</th>							
			<th>Details</th>			
			<th>Category&nbsp;&nbsp;&nbsp;&nbsp;</th>
			<th>Email-Id/Aadhaar Number</th>
			<th>Program Details</th>
			<th>Scholarship Name</th>
			<th>Action</th>
			</tr>
	  </thead>
				<tbody>
          	<?php
			//$suid=$this->session->userdata('id_user');
                        
		       //$Stuid=$this->commodel->get_listspfic1("student_master","sm_id","sm_userid",$suid)->sm_id;
        		
//			print_r($this->fldata); die;

					if(!empty($this->fldata)){
							$count =0;
				   	foreach ($this->fldata as $row)
        			   {

					?>
					<tr>
						<td><?php echo ++$count; ?></td>
						<td><?php echo $row['stname']; ?></td>
						<td><?php echo "<b>Father Name-: </b>".$row['stfname']."<br/> "."<b>Gender-: </b>".$row['stgender']."<br/> "."<b>Date of Birth-: </b>".$row['stdob']."<br/> "."<b>Mobile/Phone No.-: </b>".$row['stmobile']; ?>
						<td><?php echo $row['stcatename']; ?></td>
						<td><?php echo $row['stemail']."<br/> "."<b>Aadhaar-: </b>".$row['stuid']; ?></td>
						
						<td><?php echo "<b>Program Name-: </b>".$row['stpname']."<br/> "."<b>Department Name-: </b>".$row['stdepname']."<br/> "."<b>Semester-: </b>".$row['stsem']; ?></td>
						<td><?php echo $row['stsaname']; ?></td>
					<?php 
					echo "<td>";
					echo anchor('scholarship/schvarify/' . $row['sid']  , "Verify", array('title' => 'Details' , 'class' => 'red-link')) . " ";
					//echo anchor('scholarship/schvarify/' . $row->sm_userid  , "Verify", array('title' => 'Details' , 'class' => 'red-link')) . " ";
					echo "</td>";?>
				</tr>
					<?php
					}//for close
					}// if close
					else {
                    echo "<td colspan=\"14\" align=\"center\"> No Records found...!</td>";
                    }?> 					
         	</tbody>
   </table>
        		
</body>
<div align="center">  <?php $this->load->view('template/footer');?></div>
</html>
