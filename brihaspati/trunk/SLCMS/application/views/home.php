<!DOCTYPE html>
<html>
    <head>
        <title>Welcome to IGNTU</title>
    </head>
    <body>

<div >
<div id="body">
	<?php $this->load->view('template/header'); ?>
     	<h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
	<?php $this->load->view('template/menu'); ?>

	</div>
	<div align="center">
<?php
/*
	echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
        echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
        echo "<td style=\"padding: 8px 8px 8px 20px;\">";
	echo "Dashboard"; 
	echo "<span  style='padding: 8px 8px 8px 20px;'>";
	echo "|"; 
	echo "<span  style='padding: 8px 8px 8px 20px;'>";
	echo "University Profile";
        echo "</td>";
        echo "</tr>";
	echo "<tr>";
	echo "<td>";
        echo "<table width=\"100%\" border=\"0\" style=\"color: black; border-collapse:collapse;\">";
	echo "<table style=\"padding: 8px 8px 8px 20px;\">";
        echo "<tbody align=\"left\">";
*/
	echo "</br>";
	echo "<table><tr align=center><td>";
	echo "<table border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
        echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
        echo "<td style=\"padding: 8px 8px 8px 20px; text-align:center;\">";
	echo "University Profile";
        echo "</td>";
        echo "</tr>";
	echo "<tr>";
	echo "<td>";
        echo "<table width=\"100%\" border=\"0\" style=\"color: black; border-collapse:collapse;\">";
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
	echo $this->result->org_address2;echo ",\t";echo $this->result->org_city;echo "\t";echo $this->result->org_state;echo ",\t"; echo $this->contryname->country_name; echo ",\t"; echo $this->result->org_pincode;
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
	echo $this->result->org_adminfn;echo "\t";echo $this->result->org_adminln;echo "\t";echo $this->result->org_admindesig;
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
	echo "<tr><td style=\"padding: 8px 8px 8px 20px;\"> Campus Name</td><td style=\"padding: 8px 8px 8px 20px;\">Program Category</td><td style=\"padding: 8px 8px 8px 20px;\">Program Name</td> <td style=\"padding: 8px 8px 8px 20px;\">Seat</td></tr>";
	foreach($this->prgseat as $row){
		$scname=$this->commodel->get_listspfic1('study_center','sc_name','sc_id',$row->prg_scid)->sc_name;
		echo "<tr><td style=\"padding: 8px 8px 8px 20px;\"> $scname </td><td style=\"padding: 8px 8px 8px 20px;\">$row->prg_category</td><td style=\"padding: 8px 8px 8px 20px;\">$row->prg_name</td> <td style=\"padding: 8px 8px 8px 20px;\">$row->prg_seat</td></tr>";
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
	echo "<tr><td style=\"padding: 8px 8px 8px 20px;\"> Program Name</td><td style=\"padding: 8px 8px 8px 20px;\">Semester</td><td style=\"padding: 8px 8px 8px 20px;\">Gender</td> <td style=\"padding: 8px 8px 8px 20px;\">Category</td> <td style=\"padding: 8px 8px 8px 20px;\"> Fees</td></tr>";
	echo "<tr><td style=\"padding: 8px 8px 8px 20px;\">";

	echo "</td></tr>";
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
