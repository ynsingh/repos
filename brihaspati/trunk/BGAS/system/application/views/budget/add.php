<script type="text/javascript">
$(document).ready(function() {
	/* Show and Hide allowed_over */
	$('.group-parent').change(function() {
		if ($(this).val() == "3" || $(this).val() == "4") {
			$('.affects-gross').show();
		} else {
			$('.affects-gross').hide();
		}
	});
	$('.group-parent').trigger('change');
});
</script>
<?php
	echo form_open('budget/add');


/*	echo "<p>";
	echo form_label('Budget code', 'budget_code');
	echo "<br />";
	echo form_input($budget_code);
	echo "</p>";

	echo "<p>";
	echo form_label('Budget name', 'budget_name');
	echo "<br />";
	echo form_input($budget_name);
	echo "</p>";
*/
	echo "<p>";
        echo form_label('Budget name', 'group_expenses');
        echo "<br />";
        echo form_dropdown('group_expenses', $group_expenses, $group_expenses_active, "class = \"group-parent\"");
        echo "</p>";

	echo "<p>";
	echo form_label('Parent Budget', 'group_parent');
	echo "<br />";
	echo form_dropdown('group_parent', $group_parent, $group_parent_active, "class = \"group-parent\"");
	echo "</p>";

	echo "<p>";
        echo form_label('Budget type', 'budget_type');
        echo "<br />";
        echo form_input($budget_type);
        echo "</p>";

	/*echo "<p class=\"affects-gross\">";
	echo "<span id=\"tooltip-target-1\">";
	echo form_checkbox('affects_gross', 1, $affects_gross) . " Affects Gross Profit/Loss Calculations";
	echo "</span>";
	echo "<span id=\"tooltip-content-1\">If selected the Group account will affect Gross Profit and Loss calculations, otherwise it will affect only Net Profit and Loss calculations.</span>";
	echo "</p>";
	*/
	
	echo "<p>";
        echo "<span id=\"tooltip-target-1\">";
        echo form_checkbox('budget_over', 1, $budget_over) . " Allow over expenses";
        echo "</span>";
        echo "<span id=\"tooltip-content-1\">Select if expenses can exceed the budget.</span>";
        echo "</p>";	

	echo "<p>";
	echo form_submit('submit', 'Create');
	echo " ";
	echo anchor('budgetl', 'Back', 'Back to Budget Heads');
	echo "</p>";

	echo form_close();
