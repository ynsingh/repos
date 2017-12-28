<!DOCTYPE html>
<html>
    <head>
        <title>Welcome to IGNTU</title>
	
    </head>
    <body>
<div>
	<?php $this->load->view('template/header'); ?>
	<?php //$this->load->view('template/menu'); ?>
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->
</div>
 <?php
                    echo "<table width=\"100%\">";
                    echo "<tr>";
                    echo "<td align=\"left\" width=\"33%\">";
                    echo "</td>";
                    echo "<td align=\"center\" width=\"34%\" style=\"font-size:16px\">";
                    echo "<b>Upload Logo Details</b>";
                    echo "</td>";
                    echo "<td align=\"right\" width=\"33%\" style=\"font-size:16px\">";
                    $help_uri = site_url()."/help/helpdoc#UploadLogo";
                    echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                    echo "</td>";
                    echo "</tr>";
                    echo "</table>";
                    ?>

<?php
//	echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
//        echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
//        echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
//	echo "Upload"; 
//	echo "<span  style='padding: 8px 8px 8px 20px;'>";
//	echo "|"; 
//	echo "<span  style='padding: 8px 8px 8px 20px;'>";
//	echo "Upload University Logo";
//        echo "</td>";
//        echo "</tr>";
	echo "<tr>";
	echo "<td>";
        echo "<table width=\"100%\" border=\"0\" style=\"color: black; border-collapse:collapse;\">";
	echo "<table>";
        echo "<tbody>";

	echo "<div>";
// display user message area
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
	echo "</div>";

	//echo $error;
	echo form_open_multipart('upl/uploadlogo');
	echo "<tr><td style=\"\"><input type='file' name='userfile' size='20' />";
	echo "<input type='submit' name='uploadlogo' value='upload' /> ";
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
