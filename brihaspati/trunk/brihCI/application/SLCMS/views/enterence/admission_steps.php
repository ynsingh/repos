<!-------------------------------------------------------
    -- @name admission_steps.php --	
    -- @author sumit saxena sumitsesaxena@gmail.com --
--------------------------------------------------------->
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/style.css">
<style>


</style>
</head>
<body>

<center>


<table class="steps">
<tr class="stepalign">

<?php 

	if (stripos($_SERVER['REQUEST_URI'],'step_zero') !== false) {
		echo "<td>Step 0</td><td>Step 1</td><td>Step 2</td><td>Step 3</td> <td>Step 4</td><td>Step 5</td>";
  	}  
	if (stripos($_SERVER['REQUEST_URI'],'step_one') !== false) {
		echo "<td class=\"active\" >Step 0</td> <td>Step 1</td><td>Step 2</td><td>Step 3</td> <td>Step 4</td><td>Step 5</td>";
 	} 
	if (stripos($_SERVER['REQUEST_URI'],'step_two') !== false) {
		echo "<td class=\"active\" >Step 0</td> <td class=\"active\">Step 1</td><td>Step 2</td><td>Step 3</td><td>Step 4</td><td>Step 5</td>";
 	} 
	if (stripos($_SERVER['REQUEST_URI'],'step_three') !== false) {
		echo "<td class=\"active\" >Step 0</td> <td class=\"active\">Step 1</td><td class=\"active\">Step 2</td><td>Step 3</td><td>Step 4</td><td>Step 5</td>";
 	} 
	if (stripos($_SERVER['REQUEST_URI'],'Payment') !== false) {
		echo "<td class=\"active\" >Step 0</td> <td class=\"active\">Step 1</td><td class=\"active\">Step 2</td><td class=\"active\">Step 3</td><td>Step 4</td><td>Step 5</td>";
 	} 

	if (stripos($_SERVER['REQUEST_URI'],'step_four') !== false) {
		echo "<td class=\"active\" >Step 0</td> <td class=\"active\">Step 1</td><td class=\"active\">Step 2</td><td class=\"active\">Step 3</td><td>Step 4</td><td>Step 5</td>";
 	} 
?> 

</tr>
</table>
</center>

</body>
</html>
