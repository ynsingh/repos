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
	<?php $this->load->view('template/stumenu'); ?>

	</div>
	<div>
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
     //	echo $this->session->userdata('id_user'); 
	echo "<table><tr><td>";
	echo "<table border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
        echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
        echo "<td style=\"padding: 8px 8px 8px 20px; text-align:center;\">";
	echo "Profile";
        echo "</td>";
        echo "</tr>";
	echo "<tr>";
	echo "<td>";
        echo "<table width=\"100%\" border=\"0\" style=\"color: black; border-collapse:collapse;\">";
	echo "<table style=\"padding: 8px 8px 8px 20px;\">";
        echo "<tbody align=\"left\">";


      	echo "<tr><td style=\"padding: 8px 8px 8px 20px;\">Name</td><td>";
//	echo $this->result->sm_fname; //name of the student
	echo " ";
//	echo $this->result->sm_lname;
	echo "</td></tr>";
	echo "<tr><td style=\"padding: 8px 8px 8px 20px;\">Campus </td><td>";
//	echo $this->result->sp_sccode; //name of the campus
	echo "</td></tr>";
	echo "<tr><td style=\"padding: 8px 8px 8px 20px;\">Department</td><td>";
//	echo $this->deptid; //dept of the student with study center
	echo "</td></tr>";
     	echo "<tr><td style=\"padding: 8px 8px 8px 20px;\">Degree</td><td>";
//	echo $this->result->sp_programid; //degree of the student
	echo "</td></tr>";
        echo "<tr><td style=\"padding: 8px 8px 8px 20px;\">Semester</td><td>";
//	echo $this->result->org_address1; //year and semester of the student
	echo "</td></tr>";
        echo "<tr><td style=\"padding: 8px 8px 8px 20px;\">Subject</td><td>";
//	echo $this->result->org_affiliation;//current subject of the student
	echo "</td></tr>";
        echo "<tr><td style=\"padding: 8px 8px 8px 20px;\"> Email</td><td>";
//	echo $this->result->sm_email;//email of the student
	echo "</td></tr>";
        echo "<tr><td style=\"padding: 8px 8px 8px 20px;\">Phone</td><td>";
//	echo $this->result->sm_mobile; //phone number of the student
	echo "</td></tr>";
        echo "<tr><td style=\"padding: 8px 8px 8px 20px;\">Address</td><td>";
//	echo $this->result->org_phone;//address of the student
	echo "</td></tr>";
     	echo "</tbody>";
	echo "</table>";
	echo "</td>";
	echo "</tr>";
        echo "</table>";

	echo "</td><td valign=\"top\">";

	echo "<table border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
        echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
        echo "<td style=\"padding: 8px 8px 8px 20px; text-align:center;\">";
        echo "Program and Subject Status";
        echo "</td>";
        echo "</tr>";
        echo "<tr>";
        echo "<td>";
        echo "<table width=\"100%\" border=\"0\" style=\"color: black; border-collapse:collapse;\">";
        echo "<table style=\"padding: 8px 8px 8px 20px;\">";
	echo "<tbody align=\"left\">";
	echo "<tr><td style=\"padding: 8px 8px 8px 20px;\">Program Name</td><td>";
  //      echo $this->result->org_code; //name of the program
	echo "</td></tr>";
	echo "<tr><td style=\"padding: 8px 8px 8px 20px;\"> Subject Name</td><td style=\"padding: 8px 8px 8px 20px;\">Academic Year</td><td style=\"padding: 8px 8px 8px 20px;\">Semester</td> <td style=\"padding: 8px 8px 8px 20px;\">Qualify Status</td></tr><tr><td style=\"padding: 8px 8px 8px 20px;\">";
    //    echo $this->result->org_code; //name of the subject
	echo "</td><td style=\"padding: 8px 8px 8px 20px;\">";
      //  echo $this->result->org_code; //academic year
	echo "</td><td style=\"padding: 8px 8px 8px 20px;\">";
     //   echo $this->result->org_code; //semester
	echo "</td><td style=\"padding: 8px 8px 8px 20px;\"> ";
     //   echo $this->result->org_code; //subject qualify status
	echo "</td></tr>";
	echo "<tr><td style=\"padding: 8px 8px 8px 20px;\">";

        echo "</td></tr>";
        echo "</tbody>";
        echo "</table>";
        echo "</td>";
        echo "</tr>";
        echo "</table>";

	echo "</td><td valign=\"top\">";

        echo "<table border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
        echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
        echo "<td style=\"padding: 8px 8px 8px 20px; text-align:center;\">";
        echo "Program and Fees Status";
        echo "</td>";
        echo "</tr>";
        echo "<tr>";
        echo "<td>";
        echo "<table width=\"100%\" border=\"0\" style=\"color: black; border-collapse:collapse;\">";
        echo "<table style=\"padding: 8px 8px 8px 20px;\">";
	echo "<tbody align=\"left\">";
	echo "<tr><td style=\"padding: 8px 8px 8px 20px;\">Program Name</td><td>";
      //  echo $this->result->org_code; //name of the program
        echo "</td></tr>";
	echo "<tr><td style=\"padding: 8px 8px 8px 20px;\"> Academic Year</td><td style=\"padding: 8px 8px 8px 20px;\">Semester</td><td style=\"padding: 8px 8px 8px 20px;\">Fees Type</td> <td style=\"padding: 8px 8px 8px 20px;\">Fees Amount</td> <td style=\"padding: 8px 8px 8px 20px;\">Fees Status</td>";
	echo"	</tr><tr><td style=\"padding: 8px 8px 8px 20px;\">";
      //  echo $this->result->org_code; //academic year
        echo "</td><td style=\"padding: 8px 8px 8px 20px;\">";
      //  echo $this->result->org_code; //semester
        echo "</td><td style=\"padding: 8px 8px 8px 20px;\">";
      //  echo $this->result->org_code; //fees type ( semester fees exam fees etc
        echo "</td><td style=\"padding: 8px 8px 8px 20px;\"> ";
      //  echo $this->result->org_code; //fees amount
        echo "</td><td style=\"padding: 8px 8px 8px 20px;\"> ";
      //  echo $this->result->org_code; //fees status
        echo "</td></tr>";
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
