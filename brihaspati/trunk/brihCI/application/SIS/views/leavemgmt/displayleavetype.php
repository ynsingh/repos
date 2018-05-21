<!--@name displayleavetype.php 
  @author Shobhit Tiwari(tshobhit206@gmail.com)
  @author Ankur Singh (ankursingh206@gmail.com)
-->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>displayleavetype</title>
  <head>
   <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
   <?php $this->load->view('template/header'); ?>
  </head>

<body>
	<table width="100%">
            <tr>
                <?php
                 echo "<td align=\"left\" width=\"10%\">";
                 echo anchor('leavemgmt/leavetype/', "Add Leave Type", array('title' => 'Add Detail','class' =>'top_parent'));
                 echo "</td>";
					  /*echo "<td align=\"left\" width=\"5%\">";
                 echo anchor('leavemgmt/viewela/', "View Earned Leave", array('title' => 'Detail','class' =>'top_parent'));
                 echo "</td>";*/
					  echo "<td align=\"left\" width=\"13%\">";
					  echo "<b>Leave Type Details</b>";
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
                     <th>Leave Name</th>
			    	      <th>Leave Code</th>
                     <th>Short Name</th>
							<th>Leave Description</th>
                     <th>Total no. of days</th>
                     <th>Action</th>
						</tr>
				</thead>

				<tbody>
		  			<?php
        			$count =0;
					if(!empty($this->result))
					 {
                foreach ($this->result as $row)
         		 {
        			 ?>
        		    <tr>
            	 <td> <?php echo ++$count; ?> </td>
            	 <td> <?php echo $row->lt_name ?></td>
            	 <td> <?php echo $row->lt_code ?></td>
            	 <td> <?php echo $row->lt_short ?></td>
					 <td> <?php echo $row->lt_remarks ?></td>
            	 <td> <?php echo $row->lt_value ?></td>
            	 <td> <?php echo anchor('leavemgmt/editleave/' . $row->lt_id , "Edit", array('title' => 'Details' , 'class' => 'red-link')) . " ";
                   		   echo "</td>"; 
		          echo "</tr>";

        			 }
					}
					else
					{
                    echo "<td colspan=\"7\" align=\"center\"> No Records found...!</td>";
               }
					?> 						
         	</tbody>
         </table>
       </div>
</body>
<div align="center">  <?php $this->load->view('template/footer');?></div>
</html>
                                                                                                                                                                                          

