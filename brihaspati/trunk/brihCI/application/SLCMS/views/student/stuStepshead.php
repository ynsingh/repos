<!-------------------------------------------------------
    -- @name stuStudenthead.php --	
    -- @author Nagendra Kumar Singh(nksinghiitk@gmail.com) --
--------------------------------------------------------->
<html>
<head>
<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/studentNavbar.css">
<style>

.currentpage {
        font-weight:bold;
	/*color:#333333; background-color:#38B0DE;background-color:grey;*/  
	color:white;background-color:#38B0DE;
  	 }

.steps{background-color:#cacaca;width:50%;height:50px;}
.stepalign td{text-align:center;font-size:20px;}
.active {
    font-weight: bold;color:white;background-color:#38B0DE;
}

.active1 {
    font-weight: bold;color:white;background-color:black;
}

</style>
</head>
<body>

<center>


<table class="steps">
<tr class="stepalign">

<?php 
//echo	$stp1= $this->resstep->step1_status;
  //                      $stp2= $this->resstep->step2_status;
    //                    $stp3= $this->resstep->step3_status;
      //                  $stp4= $this->resstep->step4_status;
        //                $stp5= $this->resstep->step5_status;

	if (stripos($_SERVER['REQUEST_URI'],'Step0') !== false) {
		echo "<td>Registration</td><td>Personnel / Education Detail</td><td>Instructions</td><td>Upload File</td> <td>Payment</td><td>Print Form</td>";
  	}  
	//if (stripos($_SERVER['REQUEST_URI'],'student_step0')  !== false) {
		//echo "<td >Step 0</td> <td>Step 1</td><td>Instructions</td><td>Upload File</td> <td>Payment</td><td>Print Form</td> ";
 	//} 
	if (stripos($_SERVER['REQUEST_URI'],'student_step1') !== false) {
		echo "<td class=\"active\" >Registration</td> <td>Personnel / Education Detail</td><td>Instructions</td><td>Upload File</td> <td>Payment</td><td>Print Form</td>";
 	} 
	if (stripos($_SERVER['REQUEST_URI'],'student_step2') !== false) {
		echo "<td class=\"active\" >Registration</td> <td class=\"active\">Personnel / Education Detail</td><td>Instructions</td><td>Upload File</td><td>Payment</td><td>Print Form</td>";
 	} 
	if (stripos($_SERVER['REQUEST_URI'],'student_step3') !== false) {
		echo "<td class=\"active\" >Registration</td> <td class=\"active\">Personnel / Education Detail</td><td class=\"active\">Instructions</td><td>Upload File</td><td>Payment</td><td>Print Form</td>";
 	} 
	if (stripos($_SERVER['REQUEST_URI'],'Payment') !== false) {
		echo "<td class=\"active\" >Registration</td> <td class=\"active\">Personnel / Education Detail</td><td class=\"active\">Instructions</td><td class=\"active\">Upload File</td><td>Payment</td><td>Print Form</td>";
 	} 

	if (stripos($_SERVER['REQUEST_URI'],'student_step4') !== false) {
		echo "<td class=\"active\" >Registration</td> <td class=\"active\">Personnel / Education Detail</td><td class=\"active\">Instructions</td><td class=\"active\">Upload File</td><td>Payment</td><td>Print Form</td>";
 	} 
?> 

</tr>
</table>
</center>

</body>
</html>
