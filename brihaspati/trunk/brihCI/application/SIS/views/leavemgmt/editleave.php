<!--@name editleave.php
    @author Shobhit Tiwari(tshobhit206@gmail.com)
    @author Ankur Singh (ankursingh206@gmail.com)
-->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Edit Leave</title>
  <head>    
    <?php $this->load->view('template/header'); ?>
  </head>
<body>
<script>
   function goBack() 
	{
   window.history.back();
   }
</script>
      
        <table width="100%">
          <tr><td>
            <div>
              <?php echo validation_errors('<div  class="isa_warning">','</div>');?>
              <?php echo form_error('<div class="isa_error">','</div>');?>
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
				<td width="44%"> <?php echo anchor('leavemgmt/displayleavetype/', "View Leave Type ", array('title' => 'Leave Details' ,'class' =>'top_parent'));?></td>
				
				<?php
				 echo "<td  align=\"left\">";
				 echo "<b>Edit Leave Type Details</b>";
				 echo "</td>";
				?>
				
		  </tr>
		  </table><br>
        <table>
 			<?php
	         echo form_open('leavemgmt/editleave/' . $lt_id);

            echo "<tr>";
                echo "<td>";
                    echo form_label('Leave Name', 'lt_name');
                echo "</td>";
                echo "<td>";
                    echo form_input($lt_name);
                echo "</td>";
            echo "</tr>";

            echo "<tr>";
                echo "<td>";
                    echo form_label('Leave Code', 'lt_code');
                echo "</td>";
                echo "<td>";
                    echo form_input($lt_code);
                echo "</td>";
            echo "</tr>";

          	echo "<tr>";
                echo "<td>";
                    echo form_label('Leave Short Name', 'lt_short');
                echo "</td>";
                echo "<td>";
                    echo form_input($lt_short);
                echo "</td>";
            echo "</tr>";

            echo "<tr>";
                echo "<td>";
                    echo form_label('Leave Value', 'lt_value');
                echo "</td>";
                echo "<td>";
                    echo form_input($lt_value);
                echo "</td>";
            echo "</tr>";

				echo "<tr>";
          		 echo "<td>";
          				echo form_label('Description', 'lt_remarks');
          		 echo "</td>";
          		 echo "<td>";
          		 		echo form_input($lt_remarks);
          		 echo "</td>";
          	echo "</tr>";
			
            echo "<tr>";
					 echo "<td>";
                	  echo form_hidden('lt_id', $lt_id);
					 echo "</td>";
                echo"<td>";
                	  echo form_submit('submit', 'Update');
                	  echo " ";
            	 	  echo form_close();
            	 	  echo "<button onclick=\"goBack()\" >Back</button>";
            	 echo "</td>";
            echo"</tr>";
 			 ?>
        </table>

</body>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

                                                                                                                                                                        
