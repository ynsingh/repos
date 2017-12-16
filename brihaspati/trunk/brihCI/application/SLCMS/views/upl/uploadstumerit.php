<!DOCTYPE html>
<html>
    <head>
        <title>Welcome to IGNTU</title>
	
    </head>
    <body>

<div >
<div id="body">
	<?php $this->load->view('template/header'); ?>
	<?php $this->load->view('template/menu'); ?>

<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>



	</div>
		   <?php
                    echo "<td align=\"left\" width=\"33%\">";
                    echo "<table style=\"width:100%;\">";
                    echo "<tr valign=\"top\">";
                    echo "<td>";
                    echo "</td>";
                    echo "<td align=\"center\" width=\"34%\" style=\"font-size:16px\">";
                    echo "<b>Upload Student Admission Merit List Details</b>";
                    echo "</td>";
                    echo "<td align=\"right\" width=\"33%\" style=\"font-size:16px\">";
                    $help_uri = site_url()."/help/helpdoc#UploadStuMerit";
		    echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                    echo "</td>";
                    echo "</tr>";
                    echo "</table>";
                    ?>

	<div>
<?php
//	echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
//        echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
//       echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
//	echo "Upload"; 
//	echo "<span  style='padding: 8px 8px 8px 20px;'>";
//	echo "|"; 
//	echo "<span  style='padding: 8px 8px 8px 20px;'>";
//	echo "Upload Teacher List";
//        echo "</td>";
//        echo "</tr>";
	echo "<tr>";
	echo "<td>";
        echo "<table width=\"100%\" border=\"0\" style=\"color: black; border-collapse:collapse;\">";
	echo "<table>";
        echo "<tbody>";

	echo "<div align=\"left\">";
// display user message area
	if(isset($_SESSION)) {
                echo $this->session->flashdata('flash_data');
        }
	if((isset($_SESSION['success'])) && ($_SESSION['success'])!=''){
		echo "<div class=\"isa_success\">";
		echo $_SESSION['success'];
		echo "</div>";
	}
	if((isset($_SESSION['error'])) && (($_SESSION['error'])!='')){
		echo "<div class=\"isa_error\">";
		echo $_SESSION['error'];
		echo "</div>";
	}
	/*foreach ($error as  &$value):
                         echo  $value;
			echo "</br>";
        endforeach;
	 */
//	echo "<div>";
        echo "<div style=\"text-align:left;font-size:16px\">";
	echo "<b>";
	echo " Note :The file should be in csv or txt file. The format of admission merit list file is : ";
	echo "<br>";
	echo "application no,entrance exam name,hall ticket number,course name,branch name,student name ,student email,father name,marks,admission quota,category,meritlist no,last date of admission";
	echo "<br>";
	echo "123456,JEE,17009875,Master Of Art,Hindi,Ram Kumar,ramkumar@iitk.ac.in,Mohan Kumar,400,General,General,1,2017-08-27";
	echo "</b>";
	echo "</div>";
	echo "</div>";

	//echo $error;
	echo form_open_multipart('upl/uploadstumerit');
	echo "<tr><td><input type='file' name='userfile' size='20' />";
	echo "<input type='submit' name='uploadstumerit' value='upload' /> ";
	echo "</td></tr>";
	echo "</form>";
      	
        //echo "<tr><td style=\"padding: 8px 8px 8px 20px;\">University Administrator</td><td>";
	
	echo "</tbody>";
	echo "</table>";
	echo "</td>";
	echo "</tr>";
        echo "</table>";
        echo "</div>";
?>
	</div>
	<?php $this->load->view('template/footer'); ?>
	
</div>
    </body>
</html>
