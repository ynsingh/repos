<!DOCTYPE html>
<html>
    <head>
        <title>Welcome to IGNTU</title>
	
    </head>
    <body>

<div >
<div>
	<?php $this->load->view('template/header'); ?>
     <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
  
	<?php $this->load->view('template/menu'); ?>

	</div>
		   <?php
                    echo "<table style=\"padding: 20px 8px 8px 20px;\">";
                    echo "<tr valign=\"top\">";
                    echo "<td>";
                    $help_uri = site_url()."/help/helpdoc#UploadTeacherList";
		    echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;font-size:15px;margin-left:43%;position:absolute;margin-top:-1%\">Click for Help</b></a>";
                    echo "</td>";
                    echo "</tr>";
                    echo "</table>";
                    ?>
<table>
<tr><td>
<?php 
echo "<div style=\"margin-left:30px;text-align:left;\">";
	echo "<b>";
	echo " Note :The file should be in csv or txt file. The format of upload teacher list . ";
	echo "<br>";
	//echo "application no,enterance exam name,enterance exam rollno,course name,branch name,student name ,student email,father name,marks,admission quota,category,meritlist no,last date of admission";
	echo "<br>";
	//echo "123456,JEE,17009875,Master Of Art,Hindi,Ram Kumar,ramkumar@iitk.ac.in,Mohan Kumar,400,General,General,1,2017-08-27";
	echo "name,email,departmentid,roleid,campusid,mobileno";
	echo "<br>";
	echo "deepika,deepika@gmail.com,01,02,01,9415938783";	
	echo "</b>";
	echo "</div>";
	echo "</div>";
?>
</td></tr>
</table>
	
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
	echo form_open_multipart('upl/uploadtlist');
	echo "<tr><td style=\"padding: 8px 8px 8px 20px;\"><input type='file' name='userfile' size='20' />";
	echo "<input type='submit' name='uploadtlist' value='upload' /> ";
	echo "</td></tr>";
	echo "</form>";
      	
        //echo "<tr><td style=\"padding: 8px 8px 8px 20px;\">University Administrator</td><td>";
	
	echo "</tbody>";
	echo "</table>";
	echo "</td>";
	echo "</tr>";
        echo "</table>";
?>
	
	<?php $this->load->view('template/footer'); ?>
	
</div>
    </body>
</html>
