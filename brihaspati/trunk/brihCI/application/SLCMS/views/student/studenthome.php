<!DOCTYPE html>
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
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
	<div align="center">
<?php if(isset($_SESSION['err_message'])){ ?>
             <?php 
			echo "<div style='font-size:16px;text-align:center;background-color: #FFBABA;width:80%;height:30px;color: #D8000C;'>";
			echo "<span style='margin-top:50px;'>";
			echo $_SESSION['err_message'];
			echo "<span>";
			echo "</div>";
		?>
        <?php
        };
	?>
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
	echo "<table align = \"center\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
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

  	echo "<tr><td style=\"padding: 8px 8px 8px 20px;\"><b>Name</b></td><td>";
	echo $compname; //name of the student
	echo " ";
//	echo $this->result->sm_lname;
	echo "</td></tr>";
	echo "<tr><td style=\"padding: 8px 8px 8px 20px;\"><b>Campus</b> </td><td>";
	echo $sc_name; //name of the campus
	echo "</td></tr>";
	echo "<tr><td style=\"padding: 8px 8px 8px 20px;\"><b>Department<b></td><td>";
	echo $dept_name; //dept of the student with study center
	echo "</td></tr>";
     	echo "<tr><td style=\"padding: 8px 8px 8px 20px;\"><b>Degree<b></td><td>";
	echo $degree_name; //degree of the student
	echo "</td></tr>";
        echo "<tr><td style=\"padding: 8px 8px 8px 20px;\"><b>Semester<b></td><td>";
	echo $semester; //year and semester of the student
	echo "</td></tr>";
        echo "<tr><td style=\"padding: 8px 8px 8px 20px;\"><b>Subject<b></td><td>";
	//echo $subject;//current subject of the student
	if(empty($subject))//current subject of the student
    {
        echo $submsg;
    }
    else
    {
        echo $subject;
    }
	echo "</td></tr>";
    echo "<tr><td style=\"padding: 8px 8px 8px 20px;\"><b> Email<b></td><td>";
	echo $stud_email;//email of the student
	echo "</td></tr>";
    echo "<tr><td style=\"padding: 8px 8px 8px 20px;\"><b>Phone<b></td><td>";
	echo $stud_phone; //phone number of the student
	echo "</td></tr>";
    echo "<tr><td style=\"padding: 8px 8px 8px 20px;\"><b>Address<b></td><td>";
	echo $student_address;//address of the student
	echo "</td></tr>";
    $studparam = $stid ."/".$acadyear."/".$semester;
    //just for test
    //echo "<tr><td style=\"padding: 8px 8px 8px 20px;\"><b>For Test<b></td><td>";
    //echo anchor('studenthome/studentsubject/'.$studparam, " Student Subject" ,array('title' => 'Student Subject' , 'class' => 'top_parent')); 
    //echo "</td></tr>";


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
	echo "<tr><td style=\"padding: 8px 8px 8px 20px;\"><b>Program Name</b></td><td>";
    echo $degree_name; //name of the program
	echo "</td></tr>";
	echo "<tr><td style=\"padding: 8px 8px 8px 20px;\"><b> Subject Name</b></td><td style=\"padding: 8px 8px 8px 20px;\"><b>Academic Year</b></td><td style=\"padding: 8px 8px 8px 20px;\"><b>Semester</b></td> <td style=\"padding: 8px 8px 8px 20px;\"><b>Qualify Status</b></td></tr>";
    if(count($studprogrec))
    {
    foreach($studprogrec as $row)
    {
        $sub1=$row->sp_subid1; $sub2=$row->sp_subid2; $sub3=$row->sp_subid3; $sub4=$row->sp_subid4; $sub5=$row->sp_subid5; $sub6=$row->sp_subid6; $sub7=$row->sp_subid7; $sub8=$row->sp_subid8; $sub9=$row->sp_subid9; $sub10=$row->sp_subid10;
            if(!empty ($sub1)){
            $sub1 = $this->commodel->get_listspfic1('subject','sub_name','sub_id',$row->sp_subid1)->sub_name;
            }
            if($sub2 !=0)
            $sub2 = $this->commodel->get_listspfic1('subject','sub_name','sub_id',$row->sp_subid2)->sub_name;
            if($sub3 !=0)
            $sub3 = $this->commodel->get_listspfic1('subject','sub_name','sub_id',$row->sp_subid3)->sub_name;
            if($sub4 != 0)
            $sub4 =  $this->commodel->get_listspfic1('subject','sub_name','sub_id',$row->sp_subid4)->sub_name;;
            if($sub5 != 0)
            $sub5 =  $this->commodel->get_listspfic1('subject','sub_name','sub_id',$row->sp_subid5)->sub_name;
            if($sub6 != 0)
            $sub6 =  $this->commodel->get_listspfic1('subject','sub_name','sub_id',$row->sp_subid6)->sub_name;
            if($sub7 != 0)
            $sub7 =  $this->commodel->get_listspfic1('subject','sub_name','sub_id',$row->sp_subid7)->sub_name;
            if($sub8 != 0)
            $sub8 =  $this->commodel->get_listspfic1('subject','sub_name','sub_id',$row->sp_subid8)->sub_name;
            if($sub9 != 0)
            $sub9 =  $this->commodel->get_listspfic1('subject','sub_name','sub_id',$row->sp_subid9)->sub_name;
            if($sub10 != 0)
            $sub10 =  $this->commodel->get_listspfic1('subject','sub_name','sub_id',$row->sp_subid10)->sub_name;;

        
            $subject_year = $sub1;
            if(!empty($sub2))
            $subject_year = $subject_year.",".$sub2;
            if(!empty($sub3))
                $subject_year = $subject_year.",".$sub3;
            if(!empty($sub4))
                $subject_year = $subject_year.",<br>".$sub4;
            if(!empty($sub5))
                $subject_year = $subject_year.",".$sub5;
            if(!empty($sub6))
                $subject_year = $subject_year.",".$sub6 ;
            if(!empty($sub7))
                $subject_year = $subject_year.",<br>".$sub7;
            if(!empty($sub8))
                $subject_year = $subject_year.",".$sub8;
            if(!empty($sub9))
                $subject_year = $subject_year.",".$sub9;
            if(!empty($sub10))
                $subject_year = $subject_year.",<br>".$sub10;
    echo "<tr><td style=\"padding: 8px 8px 8px 20px;\">";       
    echo $subject_year; 
	echo "</td><td style=\"padding: 8px 8px 8px 20px;\">";
    echo $row->sp_acadyear; //academic year
	echo "</td><td style=\"padding: 8px 8px 8px 20px;\">";
    echo $row->sp_semester; //semester
	echo "</td><td style=\"padding: 8px 8px 8px 20px;\"> ";
     //   echo $this->result->org_code; //subject qualify status
	echo "</td></tr>";
    }
    }
    else
    {
     echo "<tr><td>";  
     echo "No Records found"; 
     echo "</td></tr>";
        
    }
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
	echo "<tr><td style=\"padding: 8px 8px 8px 20px;\"><b>Program Name</b></td><td>";
    echo $degree_name; //name of the program
        echo "</td></tr>";
	echo "<tr><td style=\"padding: 8px 8px 8px 20px;\"><b> Academic Year</b></td><td style=\"padding: 8px 8px 8px 20px;\"><b>Semester</b></td><td style=\"padding: 8px 8px 8px 20px;\"><b>Fees Type</b></td> <td style=\"padding: 8px 8px 8px 20px;\"><b>Fees Amount</b></td> <td style=\"padding: 8px 8px 8px 20px;\"><b>Fees Status</b></td></tr>";
            if(sizeof($stud_fee_rec)!=0)
            foreach($stud_fee_rec as $row1){
                    echo"<tr><td style=\"padding: 8px 8px 8px 20px;\">";
                    echo $acadyear;
                    echo "</td><td style=\"padding: 8px 8px 8px 20px;\">";
                    echo $semester;
                    echo "</td>";
 
                    echo "<td style=\"padding: 8px 8px 8px 20px;\">";
                    echo $row1->sfee_feename;;
                    echo "</td><td style=\"padding: 8px 8px 8px 20px;\"> ";
                    echo $row1->sfee_feeamount;
                    echo "</td><td style=\"padding: 8px 8px 8px 20px;\"> ";
                    echo $row1->sfee_paymentmethod."  ".$row1->sfee_feespaidstatus;
                    echo "</td></tr>";
            }
            else
                    echo"<tr><td style=\"padding: 8px 8px 8px 20px;\">";
                    echo $acadyear;
                    echo "</td><td style=\"padding: 8px 8px 8px 20px;\">";
                    echo $semester;
                    echo "</td>";
                    echo "<td style=\"padding: 8px 8px 8px 20px;\">";        
                    echo $fees;
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
