<!-- @author editsociety.php
    @author Neha Khullar (nehukhullar@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Edit Society</title>
    <head>    
        <?php $this->load->view('template/header'); ?>
            
    </head>
                                  <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery-ui.css">
                                  <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-1.12.4.js" ></script>
                                  <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
                                  <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.js" ></script>
                                  <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>


      <script>
        function goBack() {
        window.history.back();
        }
    </script>

         
      <!--<//?php
            echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
            echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
            echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
            echo "Setup";
            echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo "|";
            //echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo anchor('setup/displayrole/', "View Role Details", array('title' => 'Add Detail' , 'class' => 'top_parent'));
            echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo "|";
            echo "<span  style='padding: 8px 8px 8px 20px;'>";
            echo "Edit role";
            echo "</span>";
            echo "</td>";
            echo "</tr>";
            echo "</table>";
        ?>--!>

<script>
$(document).ready(function(){
$("#StartDate").datepicker({
onSelect: function(value, ui) {
  console.log(ui.selectedYear)
  var today = new Date(), 
  dob = new Date(value), 
  age = 2017-ui.selectedYear;
  //$("#age").text(age);
                                },
                                //(set for show current month or current date)maxDate: '+0d',
                                
  changeMonth: true,
  changeYear: true,
  dateFormat: 'yy-mm-dd',
 // defaultDate: '1yr',
  yearRange: 'c-47:c+50',
});

$("#EndDate").datepicker({ 
onSelect: function(value, ui) {
 console.log(ui.selectedYear)
var today = new Date(), 
dob = new Date(value), 
age = 2017-ui.selectedYear;

//$("#age").text(age);
},
                                //(set for show current month or current date)maxDate: '+0d',
changeMonth: true,
changeYear: true,
dateFormat: 'yy-mm-dd',
//defaultDate: '1yr',
yearRange: 'c-47:c+50',
});
});
</script>

<body>




        <table width="100%">
            <tr><td>
                   <?php echo anchor('setup/displaysociety/', "Society List" ,array('title' => 'Society List' , 'class' => 'top_parent'));
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Edit Society Details</b>";
                    echo "</td>";
                    echo "<td align=\"right\" width=\"33%\">";
                   ?>
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

        <table>

         <?php

            echo form_open('setup/editsociety/' . $soc_id);

          echo "<tr>";
                echo "<td>";
                    echo form_label('Society Name', 'soc_name');
                echo "</td>";
                echo "<td>";
                    echo form_input($soc_name);
                echo "</td>";
            echo "</tr>";
		
		echo "<td>";
                    echo form_label('Society Code', 'soc_code');
                echo "</td>";
                echo "<td>";
                    echo form_input($soc_code);
                echo "</td>";
            echo "</tr>";


                echo "<tr>";
                echo "<td>";
                    echo form_label('Society Address ', 'soc_address');
                 echo "</td>";
                echo "<td>";
                    echo form_input($soc_address);
                echo "</td>";
            echo "</tr>";
	
	   echo "<td>";

                    echo form_label('Society purpose', 'soc_purpose');
                echo "</td>";
                echo "<td>";
                    echo form_input($soc_purpose);
                echo "</td>";
            echo "</tr>";

	 echo "<tr>";
                echo "<td>";
                    echo form_label('Society Remark ', 'soc_remark');
                 echo "</td>";
                echo "<td>";
                    echo form_input($soc_remark);
                echo "</td>";
            echo "</tr>";





 ?>
 <tr>
 <td><label for="soc_regdate" class="control-label">Society Registration Date</label></td>
 <td><input type="text" name="soc_regdate" id="StartDate" class="form-control" size="40" placeholder="Start Date..." value=<?php echo $soc_regdate['value'];?> required="required"/><br>
</td>
</tr>
<?php


            /* echo "<tr>";
                echo "<td>";
                    echo form_label('Society Creatorid', 'soc_creatorid');
                echo "</td>";
                echo "<td>";
                    echo form_input($soc_creatorid);
                echo "</td>";
            echo "</tr>";
 

          echo "<tr>";
                echo "<td>";
                    echo form_label('Society Creatordate', 'soc_creatordate');
                echo "</td>";
                echo "<td>";
                    echo form_input($soc_creatordate);
                echo "</td>";
            echo "</tr>";
           */
                                                      
         /* echo "<tr>";
                echo "<td>";
                    echo form_label('Society Modifierid', 'soc_modifierid');
                    //echo "<br />";
                echo "</td>";
                echo "<td>";
                    echo form_input($soc_modifierid);
                echo "</td>";
            echo "</tr>";
           */
          
            echo "<td>";
                    echo form_hidden('soc_id', $soc_id);
                   echo "<td>";
                    echo form_submit('submit', 'Update');
                   echo " ";

            echo form_close();
            echo "<button onclick=\"goBack()\" >Back</button>";
            echo "</td>";
            echo "</tr>";
            echo "</td>";

     
 ?>

       </table>

</body>
<p>&nbsp;</p>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>


