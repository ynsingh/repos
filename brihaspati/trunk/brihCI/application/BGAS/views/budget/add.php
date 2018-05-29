<script type="text/javascript">
$(document).ready(function() {
	/* Show and Hide allowed_over */
	$('#budget').change(function() {
		if (($(this).is(':checked'))){
			$('.budget-expense').show();
		}
		else
		{
			$('.budget-expense').hide();
		}
	});

	/* Show and hideallowed over*/
	$('.budget-parent').change(function() {
		var name = $(".budget-parent").val();
		if(name.match("Main Budget$")){
                        $('.budget-exceed').hide();
                }
                else
                {
                        $('.budget-exceed').show();
                }
        });
	$('#budget').trigger('change');
	$('.budget-parent').trigger('change');
});
</script>
<?php
	echo form_open('budget/add');


	echo "<p>";
        echo form_label('Budget name', 'group_expenses');
        echo "<br />";
        echo form_dropdown('group_expenses', $group_expenses, $group_expenses_active, "class = \"budget-parent\"");
        echo "</p>";

	echo "<p>";
	echo form_label('Budget Amount', 'budget_amount');
	echo "<br />";
	echo form_input($budget_amount);
	echo "</p>";
		$options = array(
            "" => "Select",
            "Anually" => "Anually",
            "Quaterlly" => "Quaterlly",
		);
	echo "<p>";
        echo form_label('Budget type', 'budget_type');
        echo "<br />";
		echo form_dropdown('budget type', $options);
       // echo form_input($budget_type);
    echo "</p>";

	$data = array(
    	'name'        => 'budget_over',
    	'id'          => 'budget',
    	'value'       => '1',
    	'checked'     => $budget_over,
    	//'style'       => 'margin:10px',
    	);
	echo "<p class=\"budget-exceed\">";
	echo "<span id=\"tooltip-target-2\">";
	echo form_checkbox($data) . " Exceed expenses over budget";
	echo "</span>";
        echo "<span id=\"tooltip-content-2\">Select if expenses can exceed the budget.</span>";
	echo "</p>";

	echo "<p class=\"budget-expense\">";
	echo "<span id=\"tooltip-target-3\">";
        echo form_label('Exceed Expenses', 'budget_over_expense');
        echo "<br />";
        echo form_input($budget_over_expense);
	echo "</span>";
        echo "<span id=\"tooltip-content-3\">The value entered will be added in allocated budget, otherwise limit will be the amount of parent budget allocated. Also the amount can be in precentage or fixed/static.</span>";
        echo "</p>";
	
	echo "<p>";
	echo "<p>";
	echo form_submit('submit', 'Create');
	echo " ";
	echo anchor('budgetl', 'Back', 'Back to Budget Heads');
	echo "</p>";

	echo form_close();
?>
