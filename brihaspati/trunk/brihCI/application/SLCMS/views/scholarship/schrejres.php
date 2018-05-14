<!--@name approve.php
    @author Krishna Sahu(krishnasahu2406@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Reason For Rejecting Scholarship Request</title>
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
					 echo "<b>Reason For Rejecting Scholarship Request</b>";
					 echo "</td>";
					?>
		  </tr>
		  </table><br>
			 <?php
        	 echo form_open('scholarship/schrejres/' . $sa_id);
			 ?>
        	<table>
			
		     <tr>
         <td><label for="sa_rejres" class="control-label">Reason for Rejecting:</label></td>
         <td><textarea name="sa_rejres"  class="form-control" size="30" rows="5" cols="44"/></textarea></td>
		</tr>
		<?php
           echo "<td>";
           echo form_hidden('sa_id', $sa_id);
           echo"<td>";?>
          <input type="Submit" name="rejected" value="Reject"  />
          <?php echo " ";
           echo form_close();
			  echo "</td>";
           echo "</tr>";
			  ?>
			
</table>
</body>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

                                                                                                                                                                        
