<!--@name editdegreerule.php
    @author Nagendra Kumar Singh (nksinghiitk@gmail.com)                                                                   
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Edit degree Rule</title>
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
        <table style="margin-left:30px;">
                <tr colspan="2"><td>
                        <div style="width:150px;">
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
            echo form_open('setup2/editdegreerule/' . $id);
                echo "<tr>";
                echo "<td>";
                echo form_label('Choose Program', 'dr_prgname');
                echo "</td>";
                echo "<td>";
                echo form_input($dr_programname);
                echo "</td>";
                echo "<td>";

                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td>";
                echo form_label('Choose Branch', 'dr_prgbranch');
                echo "</td>";
                echo "<td>";
                echo form_input($dr_branchname);
                echo "</td>";
                echo "<td>";

                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td>";
                echo form_label('minimum credit', 'dr_mincredit');
                echo "</td>";
                echo "<td>";
                echo form_input($dr_mincredit);
                echo "</td>";
                echo "<td>";

                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td>";
                echo form_label('minimum subject credit','dr_minsubcredit');
                echo "</td>";
                echo "<td>";
                echo form_input($dr_minsubcredit);
                echo "</td>";
                echo "<td>";
                
                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td>";
                echo form_label('minimum thesis credit','dr_minthesiscredit');
                echo "</td>";
                echo "<td>";
                echo form_input($dr_minthesiscredit);
                echo "</td>";
                echo "<td>";
               
                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td>";
                echo form_label('minimum subject', 'dr_minsub');
                echo "</td>";
                echo "<td>";
                echo form_input($dr_minsub);
                echo "</td>";
                echo "<td>";

                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td>";
                echo form_label('minimum semester','dr_minsemester');
                echo "</td>";
                echo "<td>";
                echo form_input($dr_minsemester);
                echo "</td>";
                echo "<td>";

                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td>";
                echo form_label('minimum cpi','dr_mincpi');
                echo "</td>";
                echo "<td>";
                echo form_input($dr_mincpi);
                echo "</td>";
                echo "<td>";

                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td>";
                echo form_label('maximum credit', 'dr_maxcredit');
                echo "</td>";
                echo "<td>";
                echo form_input($dr_maxcredit);
                echo "</td>";
                echo "<td>";

                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td>";
                echo form_label('maximum semeter', 'dr_maxsemeter');
                echo "</td>";
                echo "<td>";
                echo form_input($dr_maxsemeter);
                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td>";
                echo "</td>";
                echo "<td>";
                echo form_hidden('dr_id', $id);
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

