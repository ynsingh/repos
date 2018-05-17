<!--@name staffleavedetails.php 
  @author Shobhit Tiwari(tshobhit206@gmail.com)
  @author Ankur Singh (ankursingh206@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>

<title>View Leave Details</title>
  <head>
   <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
   <?php $this->load->view('template/header'); ?>
   </head>
<body>
    <table width="100%">
         <tr align="left">
             <td width="20%">
				 <?php echo anchor('leavemgmt/leavestatus/', "Leave Status", array('title' => 'View Leave Status' ,'class' =>'top_parent'));?>
				 </td>
				 
				 <?php
					 echo "<td align=\"center\">";
					 echo "<b>View Leave Details</b>";
					 echo "</td>";
				 ?>
				 <td align="right" width="21%">
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
                    	<th>Leave Name</th>
                    	<th>Leave Master Value</th>
							<th>Leave Description</th>
							<th>Leave Taken</th>							
							<th>Leave Remaining</th>
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
 					<td>  <?php echo $row['ltname']; ?></td>	
 					<td>  <?php echo $row['msval']; ?></td>
					<td>  <?php echo $row['lremarks']; ?></td>	
 					<td>  <?php echo $row['lttaken']; ?></td>	
 					<td>  <?php echo $row['ltremain']; ?></td>							
					</tr>
					<?php
					};}
					else {
                    echo "<td colspan=\"6\" align=\"center\"> No Records found...!</td>";
                    }?> 					
         	</tbody>
   </table>
        		</div>
</body>
<br><br>
<div align="center">  <?php $this->load->view('template/footer');?></div>
</html>
