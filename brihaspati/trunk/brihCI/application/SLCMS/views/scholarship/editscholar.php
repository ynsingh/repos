<!--@name editscholar.php
    @author Krishna Sahu(krishnasahu2406@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Edit Scholarship</title>
    <head>    
        <?php $this->load->view('template/header'); ?>
           
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/datepicker/jquery-ui.css">
        <script type="text/javascript" src="<//?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-ui.js" ></script>
<script>
        function goBack() {
        window.history.back();
        }
    </script>
<script>
$( function() {
           $( "#StartDate,#EndDate" ).datepicker({
                dateFormat: 'yy-mm-dd',
                minDate: 0
              });
              });
</script>

    </head>

    <body>
<!--table id="uname" border="2"><tr><td align=center>WELCOME <?= $this->session->userdata('username') ?>  </td></tr></table-->
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
		  <tr><?php
					 echo "<td align=\"center\">";
					 echo "<b>Update Scholarship Details</b>";
					 echo "</td>";
					?>
		  </tr>
		  </table><br>
        <table>
 <?php

            echo form_open('scholarship/editscholar/' . $id);
		
	      echo "<tr>";
                echo "<td>";
                    echo form_label('Scholarship Type', 'sch_type');   
		echo "</td>";
                echo "<td>";
                    echo form_input($sch_type); ?>&nbsp;    Example: Academic, Athletic, etc
              <?php
                echo "</td>";
            echo "</tr>";


            echo "<tr>";
                echo "<td>";
                    echo form_label('Scholarship Code', 'sch_code');
                echo "</td>";
                echo "<td>";
                    echo form_input($sch_code);?>&nbsp;    Example: Example: S001, S002, etc
              <?php
                echo "</td>";
            echo "</tr>";


          echo "<tr>";
                echo "<td>";
                    echo form_label('Scholarship Name', 'sch_name');
                echo "</td>";
                echo "<td>";
                    echo form_input($sch_name);?>&nbsp;   Example: Cricket, Education, etc
              <?php
                echo "</td>";
            echo "</tr>";

                echo "<tr>";
                echo "<td>";
                    echo form_label('Description', 'sch_des');
                    //echo "<br />";
                echo "</td>";
                echo "<td>";
                    echo form_input($sch_des);
                echo "</td>";
            echo "</tr>";
       
            echo "<tr>";
                echo "<td>";
                    echo form_label('Provider', 'sch_provider');
                    //echo "<br />";
                echo "</td>";
                echo "<td>";
                    echo form_input($sch_provider);
                echo "</td>";
            echo "</tr>";
		echo "<tr>";
                echo "<td>";
                echo form_label('Year', '');
                echo "</td>";
                echo "<td>";
		$acdy =$sch_startyear['value'];
		echo "<select name=\"sch_startyear\" class=\"my_dropdown\" style=\"width:62%;\">";
                echo "<option value=\"$acdy\">$acdy</option>";
                echo "<option value=\" disabled selected \">------Select Scholarship Year------</option>";

                      for($i = 2016; $i < date("Y")+10; $i++){
                          $j=$i+1;
                      echo '<option value="'.$i.' - '.$j.'">'.$i.' - '.$j.'</option>';
                      }
               echo " </select>";
               echo "</td>";
               echo "<td>";
               echo "</td>";
               echo "</tr>";

    ?>
 <tr>
   <td><label for="sch_startdate" class="control-label">Start Date:</label></td>
   <td><input type="text" name="sch_startdate" id="StartDate" value=<?php echo $sch_startdate['value'];?> class="form-control" size="40" /><br>
   <td><?php echo form_error('sch_startdate')?></td>
   </td>
   </tr>
   <tr>
   <td><label for="sch_enddate" class="control-label">End Date:</label></td>
   <td><input type="text" name="sch_enddate" id="EndDate"  value=<?php echo $sch_enddate['value'];?> class="form-control" size="40" /><br>
   <td><?php echo form_error('sch_enddate')?></td>
   </td>
   </tr>

<?php
                echo "<td>";
                    echo form_hidden('sr_id', $id);
                   echo"<td>";
                    echo form_submit('submit', 'Update');
                   echo " ";
	    
            echo form_close();
            echo "<button onclick=\"goBack()\" >Back</button>";
            echo "</td>";
            echo "</tr>";
 echo"</td>";
 ?>

       </table>

</body>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

                                                                                                                                                                        
