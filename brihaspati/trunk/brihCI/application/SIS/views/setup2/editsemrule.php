<!--@name editsemrule.php
    @author Nagendra Kumar Singh (nksinghiitk@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Edit Semester Rule</title>
    <head>    
        <?php $this->load->view('template/header'); ?>
           
    </head>
    <body>
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
        <table> 
        	<tr><td>    
                	<div>
                    	<?php echo validation_errors('<div  class="isa_warning">','</div>');?>
                    	<?php echo form_error('<div class="isa_error">','</div>');?>
	                <?php if(isset($_SESSION['success'])){?>			
                        	<div style="margin-left:30px;" class="isa_success"><?php echo $_SESSION['success'];?></div>
                    	<?php }; ?>
                	</div> </br> 
       	 	</td></tr>  
        </table>
        <table style="padding: 8px 8px 8px 30px;">  
        <?php
	    echo form_open('setup2/editsemrule/' . $id);
	     	echo "<tr>";
                echo "<td>";
                echo form_label('Program Name', 'semcr_prgname');
                echo "</td>";
                echo "<td>";
                echo form_input($semcr_programname);
                echo "</td>";
                echo "<td>";
                
                echo "</td>";
		echo "</tr>";

 		echo "<tr>";
                echo "<td>";
                echo form_label('Program Branch', 'semcr_prgbranch');
                echo "</td>";
                echo "<td>";
                echo form_input($semcr_branchname);
                echo "</td>";
                echo "<td>";
                
                echo "</td>";
                echo "</tr>";

            	echo "<tr>";
                echo "<td>";
                echo form_label('Semester', 'semcr_semester');
                echo "</td>";
                echo "<td>";
                echo form_input($semcr_semester);
                echo "</td>";
                echo "<td>";
                echo "Example: 1,2,3,4,5,6,7,8 etc ";
                echo "</td>";
		echo "</tr>";

		echo "<tr>";
                echo "<td>";
                echo form_label('Semester Minimum Credit', 'semcr_mincredit');
                echo "</td>";
                echo "<td>";
                echo form_input($semcr_mincredit);
                echo "</td>";
                echo "<td>";
                echo "Example: any positive number ";
                echo "</td>";
		echo "</tr>";

		echo "<tr>";
                echo "<td>";
                echo form_label('Semester Maximum Credit', 'semcr_maxcredit');
                echo "</td>";
                echo "<td>";
                echo form_input($semcr_maxcredit);
                echo "</td>";
                echo "<td>";
                echo "Example: any positive number";
                echo "</td>";
                echo "</tr>";

            	echo "<tr>";
                echo "<td>";
                echo form_label('Semester CPI', 'semcr_semcpi');
                echo "</td>";
                echo "<td>";
                echo form_input($semcr_semcpi);
                echo "</td>";
		echo "<td>";
		 echo "Example: any desimal number";
                echo "</td>";
		echo "</tr>";
		
            	echo "<tr>";
                echo "<td>";
                echo "</td>";
                echo "<td>";
                echo form_hidden('semcr_id', $id);
                echo form_submit('submit', 'Update');
                echo " ";
            	echo form_close();
            	echo "<button onclick=\"goBack()\" >Back</button>";
            	echo "</td>";
            	echo "</tr>";
 ?>
       </table> 
</body>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>



