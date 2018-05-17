<!--@name approve.php
    @author Shobhit Tiwari(tshobhit206@gmail.com)
    @author Ankur Singh (ankursingh206@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Approve Leave Request</title>
<head> 
     <?php $this->load->view('template/header'); ?>
	  <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
	  <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.js" ></script>
	  <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery.datetimepicker.css"/>
</head>

<body><!--<?php print_r($lrmain);?>-->
       <table width="100%">
           <tr><td>
             <div>
                  <?php echo validation_errors('<div  class="isa_warning">','</div>');?>
                  <?php echo form_error('<div class="isa_error">','</div>');?></div>
                  <?php if(isset($_SESSION['success'])){?>
                  <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                  <?php
                  };
                  ?>
             </div>
        		</td></tr>
        </table>
		  <table width="100%">
		  <tr>
				<td><?php echo anchor('leavemgmt/pendingreq/', "Pending Leave", array('title' => 'Pending Leave Requests' ,'class' =>'top_parent'));?></td>
					<?php
					 echo "<td align=\"left\" width=\"57%\">";
					 echo "<b>Leave Request Details</b>";
					 echo "</td>";
					?>
		  </tr>
		  </table><br>
        <table>
			<?php
        	 echo form_open('leavemgmt/approve/' . $la_id);
        	 echo "<tr>";
          echo "<td>";
          echo form_label('Leave Name', 'la_type');
       	 echo "</td>";
        	 echo "<td>";
          echo form_input($la_type);
          echo "</td>";?>
			 <td>
			 <label for="la_from_date" class="control-label">Leave Remaining : <?php echo $this->lrmain; ?></label>
			 </td>
          <?php
          echo "</tr>";
          echo "<tr>";
          echo "<td>";
          echo form_label('Leave Description', 'la_desc');
          echo "</td>";
          echo "<td>";
          echo form_input($la_desc);
          echo "</td>";
          echo "</tr>";
			?>
			<tr>
			   <td><label for="granted_la_from_date" class="control-label">Leave From Date </label></td>
				<td><input type="text" name="granted_la_from_date" id="StartDate" value="<?php echo $applied_la_from_date['value'];?>"   size="40" required="required"></td>
			   <td><?php echo form_error('applied_la_from_date')?></td>
    			<script src="<?php echo base_url();?>assets/js/jquery.datetimepicker.full.js"></script>
            <script>
               $.datetimepicker.setLocale('en');
               $('#StartDate').datetimepicker({
               dayOfWeekStart : 1,
               lang:'en',
               formatTime:'H:i',
               formatDate:'Y-m-d',
					minDate:0,
               });
               $('#StartDate').datetimepicker();
				</script>
				<tr>
				<td><label for="granted_la_to_date" class="control-label">Leave To Date </label></td>
   			<td><input type="text" name="granted_la_to_date" id="LastDate" value="<?php echo $applied_la_to_date['value'];?>" class="form-control" size="40" required="required" /></td>
   			<td><?php echo form_error('applied_la_to_date')?></td>
  				<script>
					$.datetimepicker.setLocale('en');
					$('#LastDate').datetimepicker({
					dayOfWeekStart : 1,
					lang:'en',
					formatTime:'H:i',
					formatDate:'Y-m-d',
					minDate:0,
					});
					$('#LastDate').datetimepicker();
				</script>
				</tr>
			  <?php
           echo "<td>";
           echo form_hidden('la_id', $la_id);
           echo"<td>";
           echo form_submit('submit', 'Approve');
           echo " ";
           echo form_close();
			  echo "</td>";
           echo "</tr>";
			  ?>
</table>
</body>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

                                                                                                                                                                        
