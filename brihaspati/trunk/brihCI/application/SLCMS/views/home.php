<!DOCTYPE html>
<html>
    <head>
        <title>Welcome to IGNTU</title>
	<meta charset="utf-8">
	<title>IGNTU:offline payment</title>
<style>
body{font-family: "Helvetica Neue","Lucida Grande","Helvetica Neue",Arial,sans-serif;}
</style>
    </head>
    <body>

<div >
<div>
	<?php $this->load->view('template/header'); ?>
     	<h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
	<?php $this->load->view('template/menu'); ?>

	</div>
	<div align="center">
<?php
	echo "</br>";
	echo "<table width='70%'><tr align=center><td valign=\"top\">";
	echo "<table border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
        echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
        echo "<td style=\"padding: 8px 8px 8px 20px; text-align:center;\">";
	echo "University Profile";
        echo "</td>";
        echo "</tr>";
	echo "<tr>";
	echo "<td>";
        echo "<table width=\"50%\" border=\"0\" style=\"color: black; border-collapse:collapse;\">";
	echo "<table style=\"padding: 8px 8px 8px 20px;\">";
        echo "<tbody align=\"left\">";


      	echo "<tr><td style=\"padding: 8px 8px 8px 20px;\">University Code</td><td>";
	echo $this->result->org_code;
	echo "</td></tr>";
        echo "<tr><td style=\"padding: 8px 8px 8px 20px;\">University Name</td><td>";
	echo $this->result->org_name;
	echo "</td></tr>";
     	echo "<tr><td style=\"padding: 8px 8px 8px 20px;\">University Type</td><td>";
	echo $this->result->org_type;
	echo "</td></tr>";
        echo "<tr><td style=\"padding: 8px 8px 8px 20px;\">University Address</td><td>";
	echo $this->result->org_address1;
	echo "\t";
	echo $this->result->org_address2;echo ",\t";echo $this->result->org_city;echo "\t ";echo $this->result->org_state;echo ",\t"; echo $this->contryname->name; echo ",\t"; echo $this->result->org_pincode;
	echo "</td></tr>";
        echo "<tr><td style=\"padding: 8px 8px 8px 20px;\">University Email</td><td>";
	echo $this->result->org_email;
	echo "</td></tr>";
        echo "<tr><td style=\"padding: 8px 8px 8px 20px;\">University Fax</td><td>";
	echo $this->result->org_fax;
	echo "</td></tr>";
        echo "<tr><td style=\"padding: 8px 8px 8px 20px;\">University Phone</td><td>";
	echo $this->result->org_phone;
	echo "</td></tr>";
        echo "<tr><td style=\"padding: 8px 8px 8px 20px;\">University Affilation</td><td>";
	echo $this->result->org_affiliation;
	echo "</td></tr>";
        echo "<tr><td style=\"padding: 8px 8px 8px 20px;\">University Administrator</td><td>";
	echo $this->result->org_adminfn;echo "\t";echo $this->result->org_adminln;echo ",";echo "\t";echo $this->result->org_admindesig;
	echo "</td></tr>";
     	echo "</tbody>";
	echo "</table>";
	echo "</td>";
	echo "</tr>";
        echo "</table>";

	echo "</td><td valign=\"top\" >";

	echo "<table border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;  \">";
        echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
        echo "<td style=\"padding: 8px 8px 8px 20px; text-align:center;\">";
        echo "University Program and Seat";
        echo "</td>";
	echo "</tr>";
        echo "<tr>";
        echo "<td>";
        echo "<table width=\"100%\" border=\"0\" style=\"color: black; border-collapse:collapse;\">";
        echo "<table style=\"padding: 8px 8px 8px 20px;\">";
        echo "<tbody align=\"left\">";
	echo "<tr><td style=\"padding: 8px 8px 8px 20px;\"> <b>Campus Name</b></td><td style=\"padding: 8px 8px 8px 20px;\"><b>Program Category</b></td><td style=\"padding: 8px 8px 8px 20px;\"><b>Program Name</b></td><td style=\"padding: 8px 8px 8px 20px;\"><b>Program Branch</b></td> <td style=\"padding: 8px 8px 8px 20px;\"><b>Seat</b></td></tr>";
	foreach($this->prgseat as $row){
		$scname=$this->commodel->get_listspfic1('study_center','sc_name','sc_id',$row->prg_scid)->sc_name;
		echo "<tr><td style=\"padding: 8px 8px 8px 20px;\"> $scname </td><td style=\"padding: 8px 8px 8px 20px;\">$row->prg_category</td><td style=\"padding: 8px 8px 8px 20px;\">$row->prg_name</td> <td style=\"padding: 8px 8px 8px 20px;\">$row->prg_branch</td><td style=\"padding: 8px 8px 8px 20px;\">$row->prg_seat</td></tr>";
	}
        echo "</tbody>";
        echo "</table>";
        echo "</td>";
        echo "</tr>";
        echo "</table>";

	echo "</td><td valign=\"top\">";

        echo "<table border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
        echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
        echo "<td style=\"padding: 8px 8px 8px 20px; text-align:center;\">";
        echo "University Program and Fees";
        echo "</td>";
        echo "</tr>";
        echo "<tr>";
        echo "<td>";
        echo "<table width=\"100%\" border=\"0\" style=\"color: black; border-collapse:collapse;\">";
        echo "<table style=\"padding: 8px 8px 8px 20px;\">";
        echo "<tbody align=\"left\">";
	echo "<tr><td style=\"padding: 8px 8px 8px 20px;\"> <b>Program Name</b></td><td style=\"padding: 8px 8px 8px 20px;\"><b>Semester</b></td><td style=\"padding: 8px 8px 8px 20px;\"><b>Category</b></td> <td style=\"padding: 8px 8px 8px 20px;\"><b>Gender</b></td> <td style=\"padding: 8px 8px 8px 20px;\"><b> Fees</b></td></tr>";
	if(!empty($frecord)){
		foreach($frecord as $ta){
			echo "<tr>";
			foreach($ta as $ta1){
				echo "<td style=\"padding: 8px 8px 8px 20px;\">";
				echo $ta1;
				echo "</td>";
 			}
			echo "</tr>";
		}
	}
        echo "</tbody>";
        echo "</table>";
        echo "</td>";
        echo "</tr>";
        echo "</table>";

	echo "</td>";
	echo "</tr></table>";

?>

	</div>
	<?php $this->load->view('template/footer'); ?>
	
</div>
    </body>
</html>
