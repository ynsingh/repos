<!--@name earnedleave.php 
  @author Shobhit Tiwari(tshobhit206@gmail.com)
  @author Ankur Singh (ankursingh206@gmail.com)
-->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>earnedleave</title>
  <head>
   <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
   <?php $this->load->view('template/header'); ?>
  </head>

<body>
	<table width="100%">
            <tr>
                <?php
                 echo "<td align=\"center\" width=\"13%\">";
					  echo "<b>Earned Leave Details</b>";
					  echo "</td>";
					  ?>
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
        	<table class="TFtable" >
            <thead>
						<tr>
                     <th>S.No</th>                    	
							<th>Leave Year</th>                   						
							<th>Leave Taken</th>
							<th>Leave Remaining</th>
                </tr>
				</thead>

				<tbody>
		  		<?php 

        			$count =0;
					if(!empty($this->fldata))
					{
              		 foreach ($this->fldata as $row)
        				{
         			?>
            		<tr>
						<td> <?php echo ++$count; ?> </td>
            	 	<td> <?php echo $row['ltyear'] ?></td>
					 	<td> <?php echo $row['lttaken'] ?></td>
					 	<td> <?php echo $row['ltremain'] ?></td>
            	 	</tr>
						<?php } } 
				 	else { 
             			echo "<td colspan=\"10\" align=\"center\"> No Records found...!</td>";
            		  }?>
				</tbody>
         </table>
       </div>
</body>
<div align="center">  <?php $this->load->view('template/footer');?></div>
</html>
                                                                                                                                                                                          

