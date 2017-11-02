<!-------------------------------------------------------
    -- @name admission_steps.php --	
    -- @author sumit saxena sumitsesaxena@gmail.com --
--------------------------------------------------------->
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/style.css">
<style>
.stepalign td{padding:10px 35px;}

</style>
</head>
<body>

<center>


<table class="steps">
<tr class="stepalign">

<?php 

	if (stripos($_SERVER['REQUEST_URI'],'step_zero') !== false) {
		echo "<td>Registration</td><td>Personnel</td><td>Education</td><td>Upload Enclosure</td> <td>Payment</td><td>Print Form</td>";
  	}  
	if (stripos($_SERVER['REQUEST_URI'],'step_one') !== false) {
		echo "<td class=\"active\" >Registration</td> <td>Personnel</td><td>Education</td><td>Upload Enclosure</td> <td>Payment</td><td>Print Form</td>";
 	} 
	if (stripos($_SERVER['REQUEST_URI'],'step_two') !== false) {
		echo "<td class=\"active\" >Registration</td> <td class=\"active\">Personnel</td><td>Education</td><td>Upload Enclosure</td><td>Payment</td><td>Print Form</td>";
 	} 
	if (stripos($_SERVER['REQUEST_URI'],'step_three') !== false) {
		echo "<td class=\"active\" >Registration</td> <td class=\"active\">Personnel</td><td class=\"active\">Education</td><td>Upload Enclosure</td><td>Payment</td><td>Print Form</td>";
 	} 
	if (stripos($_SERVER['REQUEST_URI'],'Payment') !== false) {
		echo "<td class=\"active\" >Registration</td> <td class=\"active\">Personnel</td><td class=\"active\">Education</td><td class=\"active\">Upload Enclosure</td><td>Payment</td><td>Print Form</td>";
 	} 

	if (stripos($_SERVER['REQUEST_URI'],'step_four') !== false) {
		echo "<td class=\"active\" >Registration</td> <td class=\"active\">Personnel</td><td class=\"active\">Education</td><td class=\"active\">Upload Enclosure</td><td>Payment</td><td>Print Form</td>";
 	} 
?> 

</tr>
</table>
</center>

</body>
</html>
