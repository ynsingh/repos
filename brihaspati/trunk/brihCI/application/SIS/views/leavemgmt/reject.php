<!--@name approve.php
    @author Shobhit Tiwari(tshobhit206@gmail.com)
    @author Ankur Singh (ankursingh206@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Reason For Rejecting Leave Request</title>
<head> 
     <?php $this->load->view('template/header'); ?>
</head>

<body>
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
					<?php
					 echo "<td align=\"center\" width=\"57%\">";
					 echo "<b>Reason For Rejecting Leave Request</b>";
					 echo "</td>";
					?>
		  </tr>
		  </table><br>
			 <?php
        	 echo form_open('leavemgmt/reject/' . $la_id);
			 ?>
        	<table>
			
		     <tr>
			<td><label for="lt_remarks" class="control-label">Reason for Rejecting: <font color='Red'> *</font> </label></td>         
         <td><textarea name="la_rejres"  class="form-control" size="30" rows="5" cols="44"/></textarea></td>
		</tr>
		<?php
           echo "<td>";
           echo form_hidden('la_id', $la_id);
           echo"<td>";
           echo form_submit('submit', 'Submit');
           echo " ";
           echo form_close();
			  echo "</td>";
           echo "</tr>";
			  ?>
			
</table>
</body>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

                                                                                                                                                                        
