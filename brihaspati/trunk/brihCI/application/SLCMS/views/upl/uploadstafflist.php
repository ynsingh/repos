<!DOCTYPE html>
<!--@name liststaff.php 
  @author Nagendra Kumar Singh(nksinghiitk@gmail.com)
  @author Deepika Chaudhary (chaudharydeepika88@gmail.com)
  @author Malvika Upadhyay (chaudharydeepika88@gmail.com)
 -->
<html>
    <head>
        <title>Upload Staff List</title>
        
    </head>
    <body>

<div >
<div id="body">
        <?php $this->load->view('template/header'); ?>
     <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>

        <?php $this->load->view('template/menu'); ?>

        </div>
                   <?php
                    echo "<table style=\"padding: 20px 8px 8px 20px;\">";
                    echo "<tr valign=\"top\">";
                    echo "<td>";
                    $help_uri = site_url()."/help/helpdoc#UploadStaffList";
                    echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;font-size:15px;margin-left:43%;position:absolute;margin-top:-1%\">Click for Help</b></a>";
                    echo "</td>";
                    echo "</tr>";
                    echo "</table>";
                    ?>

           <div>
<?php
//      echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
//        echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
//       echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
//      echo "Upload"; 
//      echo "<span  style='padding: 8px 8px 8px 20px;'>";
//      echo "|"; 
//      echo "<span  style='padding: 8px 8px 8px 20px;'>";
//      echo "Upload Staff List";
//        echo "</td>";
//        echo "</tr>";
        echo "<tr>";
        echo "<td>";
        echo "<table width=\"100%\" border=\"0\" style=\"color: black; border-collapse:collapse;\">";
        echo "<table style=\"padding: 8px 8px 8px 20px;\">";
        echo "<tbody align=\"left\">";

        echo "<div align=\"left\">";
// display user message area
        if(isset($_SESSION)) {
                echo $this->session->flashdata('flash_data');
        }
        if((isset($_SESSION['success'])) && ($_SESSION['success'])!=''){
                echo "<div style=\"margin-left:30px;width:1700px;align:left;\" class=\"isa_success\">";
                echo $_SESSION['success'];
                echo "</div>";
        }
        if((isset($_SESSION['error'])) && (($_SESSION['error'])!='')){
                echo "<div style=\"margin-left:30px;width:1700px;align:left;\" class=\"isa_error\">";
                echo $_SESSION['error'];
                echo "</div>";
        }
        /*foreach ($error as  &$value):
                         echo  $value;
                        echo "</br>";
        endforeach;
*/
        echo "</div>";

         //echo $error;
        echo form_open_multipart('upl/uploadslist');
        echo "<tr><td style=\"padding: 8px 8px 8px 20px;\"><input type='file' name='userfile' size='20' />";
        echo "<input type='submit' name='uploadslist' value='upload' /> ";
        echo "</td></tr>";
        echo "</form>";

        //echo "<tr><td style=\"padding: 8px 8px 8px 20px;\">University Administrator</td><td>";

        echo "</tbody>";
        echo "</table>";
        echo "</td>";
        echo "</tr>";
        echo "</table>";
?>
        </div>
        <?php $this->load->view('template/footer'); ?>

</div>
    </body>
</html>

               

