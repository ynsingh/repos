<!--@name rejectedreq.php 
  @author Shobhit Tiwari(tshobhit206@gmail.com)
  @author Ankur Singh (ankursingh206@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>

<title>View Rejected Leave Requests</title>
  <head>
   <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
   <?php $this->load->view('template/header'); ?>
   </head>
<body>
    <table width="100%">
         <tr align="left">
             <td width="7%">
				 <?php echo anchor('leavemgmt/pendingreq/', "Pending Leave", array('title' => 'Pending Leave Requests' ,'class' =>'top_parent'));?>
				 </td>
				 <td width="7%">
				 <?php echo anchor('leavemgmt/approvedreq/', "Approved Leave", array('title' => 'Approved Leave Requests' ,'class' =>'top_parent'));?>
				 </td>
				 <?php
					 echo "<td align=\"center\">";
					 echo "<b>Rejected Leave Requests</b>";
					 echo "</td>";
				 ?>
				 <td align="right" width="15%">
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
   <table class="TFtable ">
            <thead>
					<tr>
                    	<th width="2%">S.No</th>
                    	<th width="2%">User Name</th>
							<th width="2%">Department</th>							
							<th width="2%">Leave Type</th>
							<th width="2%">From Date</th>
                     <th width="2%">To Date</th>
							<th width="2%">Days Requested</th>
			 				<th width="2%">No. of Leave Remaining</th>
							<th width="2%">Reason for Leave</th>
							<th width="2%">Status</th>
					</tr>
				</thead>
				<tbody>
          	<?php
        			$count =0;
					if(!empty($this->fldata)){
				   	foreach ($this->fldata as $row)
        			   {
         	?>
          	   <tr> 
			  	   <td> <?php echo ++$count; ?></td>
 					<?php echo " <td>";
                     echo $this->logmodel->get_listspfic1('userprofile','firstname','userid',$row['userid'])->firstname;
                     echo "&nbsp; ";
                     echo $this->logmodel->get_listspfic1('userprofile','lastname','userid',$row['userid'])->lastname;
                     echo "</td>";
						   echo " <td>";
                     echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$row['deptid'])->dept_name;
                     echo "</td>";
               ?>
				 	<td> <?php echo $this->sismodel->get_listspfic1('leave_type_master','lt_name','lt_id',$row['ltype'])->lt_name; ?>	</td> 
           	 	<td> <?php echo $row['frmdate']; ?> </td>
					<td> <?php echo $row['todate']; ?> </td>
					<td> <?php 
								if($row['appday']==0.5)	
									{
									echo "Half Day"; 
									}
								 else 
									{
									echo $row['appday']; 	
									}
							?></td>
					<td> <?php echo $row['lremain']; ?>	</td>
					<td> <?php echo $row['desc']; ?>	</td> 
					<td> <font color="red"><?php echo "Rejected"; ?></font></td>
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
