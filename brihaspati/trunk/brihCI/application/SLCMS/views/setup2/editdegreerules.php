<!--@name editdegreerule.php
    @author Nagendra Kumar Singh (nksinghiitk@gmail.com)                                                                   
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Edit degree Rule</title>
    <head>    
        <?php $this->load->view('template/header'); ?>
            <!--h1>Welcome <?= $this->session->userdata('username') ?>  </h1-->
        <?php $this->load->view('template/menu');?>
    </head>
    <body>
 <script>
        function goBack() {
        window.history.back();
        }
    </script>
<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>

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
        <table width="100%">
                <tr>
		<?php
                    echo "<td align=\"center\" width=\"100%\">";
                    echo "<b>Update Degree Rules Details</b>";
                    echo "</td>";
             ?>
          <tr>
   </table>
		<table width="100%">
                    <tr><td>
                        <div>
                        <?php echo validation_errors('<div  class="isa_warning">','</div>');?>
                        <?php echo form_error('<div class="isa_error">','</div>');?>
                        <?php if(isset($_SESSION['success'])){?>
                                <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                        <?php }; ?>
                        </div>
                </td></tr>
        </table>
        <table>
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
                echo form_label('Minimum Credit', 'dr_mincredit');
                echo "</td>";
                echo "<td>";
                echo form_input($dr_mincredit);
                echo "</td>";
                echo "<td>";

                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td>";
                echo form_label('Minimum Subject Credit','dr_minsubcredit');
                echo "</td>";
                echo "<td>";
                echo form_input($dr_minsubcredit);
                echo "</td>";
                echo "<td>";
                
                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td>";
                echo form_label('Minimum Thesis Credit','dr_minthesiscredit');
                echo "</td>";
                echo "<td>";
                echo form_input($dr_minthesiscredit);
                echo "</td>";
                echo "<td>";
               
                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td>";
                echo form_label('Minimum Subject', 'dr_minsub');
                echo "</td>";
                echo "<td>";
                echo form_input($dr_minsub);
                echo "</td>";
                echo "<td>";

                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td>";
                echo form_label('Minimum Semester','dr_minsemester');
                echo "</td>";
                echo "<td>";
                echo form_input($dr_minsemester);
                echo "</td>";
                echo "<td>";

                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td>";
                echo form_label('Minimum CPI','dr_mincpi');
                echo "</td>";
                echo "<td>";
                echo form_input($dr_mincpi);
                echo "</td>";
                echo "<td>";

                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td>";
                echo form_label('Maximum Credit', 'dr_maxcredit');
                echo "</td>";
                echo "<td>";
                echo form_input($dr_maxcredit);
                echo "</td>";
                echo "<td>";

                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td>";
                echo form_label('Maximum Semesters', 'dr_maxsemester');
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

