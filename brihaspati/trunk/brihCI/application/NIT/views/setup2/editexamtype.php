<!--@name editexamtype.php
    @author Deepika Chaudhary (chaudharydeepika88@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Edit Exam</title>
    <head>    
        <?php $this->load->view('template/header'); ?>
            <!--Welcome <?//= $this->session->userdata('username') ?>  </h1-->
        <?php //$this->load->view('template/menu');?>
    </head>
    <body>
 <script>
        function goBack() {
        window.history.back();
        }
    </script>
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->
 <table width="100%">
            <tr>
 		<?php
                    echo "<td align=\"center\" width=\"100%\">";
                    echo "<b>Update Exam Type Details</b>";
                    echo "</td>";
            	?>
     </tr>
  </table>
		<table width="100%">
                <tr><td>
                 <div>
                    <?php echo validation_errors('<div  class="isa_warning">','</div>');?>
                    <?php echo form_error('<div class="isa_error">','</div>');?></div>

                    <?php if(isset($_SESSION['success'])){?>
                        <div style="margin-left:30px;" class="isa_success"><?php echo $_SESSION['success'];?></div>

                    <?php
                    };
                    ?>
                </div>
        </td></tr>
        </table>
<table>
         <?php

            echo form_open('setup2/editexamtype/' . $id);


            echo "<tr>";
                echo "<td>";
                    echo form_label('Exam Name', 'exty_name');
                echo "</td>";
                echo "<td>";
                    echo form_input($exty_name);
                echo "</td>";
                echo "<td>";
                    echo "Example: Internal Exam, External Exam, Practical, Viva-Voce";
                echo "</td>";
            echo "</tr>";

            //echo "<p>";
            echo "<tr>";
                echo "<td>";
                    echo form_label('Exam Description', 'exty_desc');
                    //echo "<br />";
                echo "</td>";
                echo "<td>";
                    echo form_input($exty_desc);
                echo "</td>";
                echo "<td>";
                    echo "Example : Exam, University Exam, Practical Exam, Oral Exam";
                echo "</td>";
            echo "</tr>";

                echo "<td>";
                    echo form_hidden('exty_id', $id);
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
