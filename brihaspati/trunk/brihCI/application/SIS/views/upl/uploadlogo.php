<!DOCTYPE html>
<html>
    <head>
        <title>Welcome to IGNTU</title>
	<?php $this->load->view('template/header'); ?>
        <?php $this->load->view('template/menu'); ?>	
    </head>
    <body>

<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
 <?php
                    echo "<table style=\"padding: 20px 8px 8px 20px;\">";
                    echo "<tr valign=\"top\">";
                    echo "<td>";
                    $help_uri = site_url()."/help/helpdoc#UploadLogo";
                    echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;font-size:17px;margin-left:54%;position:absolute;margin-top:-1%\">Click for Help</b></a>";
                    echo "</td>";
                    echo "</tr>";
                    echo "</table>";
                    ?>

	<div>
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
	echo "<table style=\"padding: 8px 8px 8px 20px;\">";
        echo "<tbody align=\"left\">";

	echo "<div align=\"left\">";
// display user message area
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
	echo "</div>";

	//echo $error;
	echo form_open_multipart('upl/uploadlogo');
	echo "<tr><td style=\"padding: 8px 8px 8px 20px;\"><input type='file' name='userfile' size='20' />";
	echo "<input type='submit' name='uploadlogo' value='upload' /> ";
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
