<html>
<head>
<script type = "text/javascript">
	function sum($i)
	{
	var cost = document.getElementById("cost"+$i).value;
	var dep_value = document.getElementById("depreciated_value"+$i).value;
	var current_value = cost - dep_value;
	//var current_value = parseInt(cost) - parseInt(dep_value);
	if (!isNaN(current_value)) {
                document.getElementById('current_value'+$i).value = current_value;
            }
	}
/*	$(document).ready(function() {
	$('.sanc-type').change(function() {	
        	var name = $('.sanc-type').val();
                if(name == "plan" || name == "non_plan"){
                        if(name == "plan"){
                                $('.plan').show();
                                $('.plan-label').show();
                                $('.non-plan').hide();
                                $('.non-plan-label').hide();
                        }
                        else
                        {
                                $('.non-plan').show();
                                $('.non-plan-label').show();
                                $('.plan').hide();
                                $('.plan-label').hide();
                        }
                }else{
                        $('.plan').hide();
                        $('.plan-label').hide();
                        $('.non-plan').hide();
                        $('.non-plan-label').hide();    
                }
	}) */
	  /* On page load initiate  triggers */
        //$('.sanc-type').trigger('change');
	
// });

</script>
</head>
</html> 

<?php  if ( ! defined('BASEPATH')) exit('No direct script access allowed');

	echo form_open('report/add_old_asset_value');
 	echo "<table border=0 id=\"my-table\" cellpadding=6 class=\"simple-table balance-sheet-table\" width=\"100%\">";
	echo "<thead><tr><th><b>Asset Name </th><th width=\"100\"><b>Date of Purchase</th><th><b>Sanction Type</th><th><b>Purchase Value(Rs/-)</th><th><b>Dep. Amount(Rs/-)</th><th><b>Current Value(Rs/-)</th><th><b>Fund/Project Name</th></tr></thead>";
	for($i = 1;$i<=5;$i++)
	{
	echo "<tr>";
	echo "<td id =\"asset_name\" size=\"20\">"; 
        echo form_dropdown('asset_name[' . $i .']',isset($asset_name[$i]) ? $asset_name[$i] : $asset_name);
 	echo "</td>";
	echo "<td id=\"date_of_purchase\" class=\"date_of_purchase\">";
	echo form_input_date_restrict('date_of_purchase[' . $i .']',isset($date_of_purchase[$i]) ? $date_of_purchase[$i] : "");
	echo "</td>";		

	echo "<td id =\"sanc-type\">";
	echo form_dropdown('sanc_type[' . $i .']',isset($sanc_type[$i]) ? $sanc_type[$i] : $sanc_type);
	//echo form_dropdown('sanc_type', $sanc_type, $active_sanc_type, "class = \"sanc-type\"");
	echo "</td>";
	//echo form_dropdown('sanc_type[' . $i .']',isset($sanc_type[$i]) ? $sanc_type[$i] : $sanc_type , $active_sanc_type, "class = \"sanc-type\"");
	
/*        $plan_attr = array('class' => 'plan-label');
        echo form_label('Plan', 'plan', $plan_attr);
        //echo form_dropdown('plan['. $i .']', isset($plan[$i]) ? $plan[$i] : $plan ,$active_sanc_type, "class = \"plan\"");
	echo form_dropdown('plan', $plan, $active_plan, "class = \"plan\"");
	

        $non_plan_attr = array('class' => 'non-plan-label');
        echo form_label('Non Plan', 'non_plan', $non_plan_attr);
	echo form_dropdown('non_plan', $non_plan, $active_non_plan, "class = \"non-plan\""); */
        //echo form_dropdown('non_plan['. $i .']', isset($non_plan[$i]) ? $non_plan[$i] :$non_plan, $active_non_plan, "class = \"non-plan\"");



	$cost = array(
        	'name' => 'cost[' . $i . ']',
                'id' => 'cost'.$i,
                'maxlength' => '10',
                'size' => '15',
                //'value' => isset($cost[$i]) ? $cost[$i] : "",
		'value' => '',
                        'class' => 'cost',
                );

		
	 $depreciated_value = array(
                'name' => 'depreciated_value[' . $i . ']',
                'id' => 'depreciated_value'.$i,
                'maxlength' => '10',
                'size' => '15',
		'value' => '',
                //'value' => isset($depreciated_value[$i]) ? $depreciated_value[$i] : "",
                        'class' => 'depreciated_value',
                );   

	 $current_value = array(
                'name' => 'current_value[' . $i . ']',
                'id' => 'current_value'.$i,
                'maxlength' => '10',
                'size' => '15',
		'value' => '',
                //'value' => isset($current_value[$i]) ? $current_value[$i] : "",
                        'class' => 'current_value',
                );

	echo "<td>";
	echo "<div oninput=\"sum($i)\">";
	echo form_input($cost);
	echo "</div>";
	echo  "</td>";

	
	echo "<td>";  
	echo "<div oninput=\"sum($i)\">";
	echo  form_input($depreciated_value);
	echo "</div>";
	echo "</td>";	

	echo "<td id=\"current_value\">";
	echo  form_input($current_value);
        echo "</td>";

	echo "<td>" . form_dropdown_fund('fund_list[' . $i . ']', isset($fund_list[$i]) ? $fund_list[$i] : 0) . "</td>";
	echo "</tr>";
	}
	echo "</table>";
	echo "<br />";
	echo form_submit('submit', 'Submit');
	echo "&nbsp;&nbsp;";
?>
 	<input type="button" onClick="window.history.back()" value="Back">
        <?php echo form_close();?>

